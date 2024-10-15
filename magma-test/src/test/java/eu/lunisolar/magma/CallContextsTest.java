/*
 * This file is part of "lunisolar-magma".
 *
 * (C) Copyright 2014-2023 Lunisolar (http://lunisolar.eu/).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package eu.lunisolar.magma;

import eu.lunisolar.magma.asserts.TestFlow;
import eu.lunisolar.magma.basics.exceptions.NestedException;
import eu.lunisolar.magma.func.CallContext;
import eu.lunisolar.magma.func.CallContexts;
import eu.lunisolar.magma.func.action.LAction;
import eu.lunisolar.magma.func.consumer.LBiConsumer;
import eu.lunisolar.magma.func.consumer.primitives.LIntConsumer;
import eu.lunisolar.magma.func.consumer.primitives.LLongConsumer;
import eu.lunisolar.magma.func.function.LBiFunction;
import eu.lunisolar.magma.func.supp.Be;
import eu.lunisolar.magma.func.supp.Have;
import eu.lunisolar.magma.func.supp.P;
import eu.lunisolar.magma.func.supp.check.Checks;
import eu.lunisolar.magma.func.supplier.LSupplier;
import eu.lunisolar.magma.func.tuple.LIntSingle;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Consumer;

import static eu.lunisolar.magma.CallContextShared.*;
import static eu.lunisolar.magma.asserts.TestFlow.test;
import static eu.lunisolar.magma.func.supp.check.Checks.attest;
import static eu.lunisolar.magma.func.supp.check.Checks.attestThrownBy;
import static java.util.concurrent.ForkJoinPool.commonPool;

public class CallContextsTest {

    @Test
    public void lockContextWithTimeout() {

        test().given(()-> new TestFlow.Stage() {
            final Lock lock = new ReentrantLock();
            final CallContext callContext = CallContexts.lockContext(lock, 100, TimeUnit.MILLISECONDS);
            LIntSingle.Mut<?> count = LIntSingle.atomicOf();
            Checks.Check<Throwable> thrown;
        }).precondition(stage -> {
            // We just need to get the lock in another thread, so the test execution thread will have timeout.
            commonPool().submit(() -> {
                stage.lock.lock();
                LLongConsumer.tryAccept(3000, Thread::sleep);
            });
        }).junction(flow -> flow
                .when(stage -> {
                    stage.thrown = attestThrownBy(() -> LAction.nestingExecute(stage.callContext, () -> {
                        stage.count.inc();
                    }));
                }).then(stage -> {
                    stage.thrown
                            .mustBeExactlyInstanceOf(NestedException.class)
                            .mustEx(Have::msgStartWithEx, "Lock acquisition timeout:")
                            .mustEx(Have::causeEx)
                            .check(Throwable::getCause, cause -> {
                                cause.mustBeExactlyInstanceOf(TimeoutException.class)
                                     .mustEx(Have::msgStartWithEx, "Lock acquisition timeout:");
                            });
                    attest(stage.count).check(LIntSingle::value, value -> value.mustBeEqual(0));
                })
        ).junction(flow -> flow
                .when(stage -> {
                    stage.thrown = attestThrownBy(() -> LAction.shovingExecute(stage.callContext, () -> {
                        stage.count.inc();
                    }));
                }).then(stage -> {
                    stage.thrown
                            .mustBeExactlyInstanceOf(TimeoutException.class)
                            .mustEx(Have::msgStartWithEx, "Lock acquisition timeout:")
                            .mustEx(Have::noCauseEx);
                    attest(stage.count).check(LIntSingle::value, value -> value.mustBeEqual(0));
                })
        );
    }

    @Test
    public void semaphoreContext() {

        test().given(()-> new TestFlow.Stage() {
            final Semaphore semaphore = new Semaphore(1);
            final CallContext callContext = CallContexts.semaphoreContext(1, semaphore, 100, TimeUnit.MILLISECONDS);
            LIntSingle.Mut<?> count = LIntSingle.atomicOf();
            Checks.Check<Throwable> thrown;
        }).precondition(stage -> {
            LIntConsumer.tryAccept(1, stage.semaphore::acquire);
        }).junction(flow -> flow
                .when(stage -> {
                    stage.thrown = attestThrownBy(() -> LAction.nestingExecute(stage.callContext, () -> {
                        stage.count.inc();
                    }));
                }).then(stage -> {
                    stage.thrown
                            .mustBeExactlyInstanceOf(NestedException.class)
                            .mustEx(Have::msgStartWithEx, "Semaphore acquisition timeout:")
                            .mustEx(Have::causeEx)
                            .check(Throwable::getCause, cause -> {
                                cause.mustBeExactlyInstanceOf(TimeoutException.class)
                                     .mustEx(Have::msgStartWithEx, "Semaphore acquisition timeout:");
                            });
                    attest(stage.count).check(LIntSingle::value, value -> value.mustBeEqual(0));
                })
        ).junction(flow -> flow
                .when(stage -> {
                    stage.thrown = attestThrownBy(() -> LAction.shovingExecute(stage.callContext, () -> {
                        stage.count.inc();
                    }));
                }).then(stage -> {
                    stage.thrown
                            .mustBeExactlyInstanceOf(TimeoutException.class)
                            .mustEx(Have::msgStartWithEx, "Semaphore acquisition timeout:")
                            .mustEx(Have::noCauseEx);
                    attest(stage.count).check(LIntSingle::value, value -> value.mustBeEqual(0));
                })
        );
    }

    @DataProvider(name = "simpleTest")
    public static Object[][] test1() {return new Object[][]{{"3", "4"}, {"2", "3"}};}

    @Test(dataProvider = "simpleTest")
    public void merge_executesStartsAndEndsInOrder(String a1, String a2) {

        // given
        l().clear();
        var ctx = CallContexts.merge(CallContexts.ctx(i1, f1), CallContexts.ctx(i2, f2));

        // when
        var result = LBiFunction.nestingApply(ctx, a1, a2, FUNC);

        // then
        attest(l()).mustAEx(P::containExactlyEx, i1Log, i2Log, a1, a2, f2Log, f1Log);
    }

    @DataProvider(name = "exceptionHandling")
    public static Object[][] exceptionHandling() {
        return exceptionHandlingCases();
    }

    @Test(dataProvider = "exceptionHandling")
    public void merge_exceptionHandling_shoving(
            LSupplier<Object> init1, LBiConsumer<Object, Throwable> finish1,
            LSupplier<Object> init2, LBiConsumer<Object, Throwable> finish2,
            LBiFunction<String, String, String> function,
            Consumer<Checks.Check<Throwable>> exChecker,
            String... expectedLog
    ) {
        // given
        l().clear();
        var ctx = CallContexts.merge(CallContexts.ctx(init1, finish1), CallContexts.ctx(init2, finish2));

        // when
        var check = attestThrownBy(() -> {
            LBiFunction.shovingApply(ctx, ARG1, ARG2, function);
        });

        // then
        exChecker.accept(check);
        attest(l()).mustAEx(P::containExactlyEx, expectedLog);
    }

    @Test(dataProvider = "exceptionHandling")
    public void merge_exceptionHandling_nesting(
            LSupplier<Object> init1, LBiConsumer<Object, Throwable> finish1,
            LSupplier<Object> init2, LBiConsumer<Object, Throwable> finish2,
            LBiFunction<String, String, String> function,
            Consumer<Checks.Check<Throwable>> exChecker,
            String... expectedLog
    ) {
        // given
        l().clear();
        var ctx = CallContexts.merge(CallContexts.ctx(init1, finish1), CallContexts.ctx(init2, finish2));

        // when
        var check = attestThrownBy(() -> {
            LBiFunction.nestingApply(ctx, ARG1, ARG2, function);
        });

        // then
        if (check.value() instanceof Error) {
            exChecker.accept(check);
        } else {
            check.mustEx(Be::exactlyInstanceOfAnyEx, NestedException.class, MyError.class)
                 .mustEx(Have::noSuppressedEx)
                 .mustEx(Have::causeEx)
                 .check(Throwable::getCause, exChecker::accept);
        }

        attest(l()).mustAEx(P::containExactlyEx, expectedLog);
    }

    @Test
    public void merge_supplier_init_exception_returned_not_thrown() {
        // given
        var capturedL = l();
        capturedL.clear();
        var ctx1 = CallContexts.merge(CallContexts.ctx(() -> new UnsupportedOperationException("unsupported"), obj -> {/*NOOP*/}));

        // when
        var check = attestThrownBy(() -> LSupplier.shovingGet(ctx1, Object::new));

        // then
        check.mustEx(Be::instanceOfEx, UnsupportedOperationException.class)
             .mustEx(Have::msgEqualEx, "unsupported")
             .mustEx(Have::noCauseEx)
             .mustEx(Have::noSuppressedEx);
    }

    @DataProvider(name = "simpleTest_statesData")
    public static Object[][] simpleTest_statesData() {
        return CallContextShared.simpleTest_statesData();
    }

    @Test(dataProvider = "simpleTest_statesData")
    public void merge_state(String state1Value, String state2Value) {

        // given
        l().clear();
        var ctx = CallContexts.merge(CallContexts.ctx(() -> {
            l().add(i1Log);
            return state1Value;
        }, (state, e) -> {
            l().add(f1Log);
            attest(state).mustBeSame(state1Value);
        }), CallContexts.ctx(() -> {
            l().add(i2Log);
            return state2Value;
        },  (state, e) -> {
            l().add(f2Log);
            attest(state).mustBeSame(state2Value);
        }));

        // when
        var result = LBiFunction.nestingApply(ctx, "1", "2", FUNC);

        // then
        attest(l()).mustAEx(P::containExactlyEx, i1Log, i2Log, "1", "2", f2Log, f1Log);
    }


}
