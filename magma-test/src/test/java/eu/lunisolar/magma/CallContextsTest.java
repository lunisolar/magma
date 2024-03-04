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
import eu.lunisolar.magma.func.consumer.primitives.LIntConsumer;
import eu.lunisolar.magma.func.consumer.primitives.LLongConsumer;
import eu.lunisolar.magma.func.supp.Have;
import eu.lunisolar.magma.func.supp.check.Checks;
import eu.lunisolar.magma.func.tuple.LIntSingle;
import org.testng.annotations.Test;

import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static eu.lunisolar.magma.asserts.TestFlow.test;
import static eu.lunisolar.magma.func.supp.check.Checks.attest;
import static eu.lunisolar.magma.func.supp.check.Checks.attestThrownBy;
import static java.util.concurrent.ForkJoinPool.commonPool;

public class CallContextsTest {

    @Test
    public void lockContextWithTimeout() {

        test().given(new TestFlow.Stage() {
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

        test().given(new TestFlow.Stage() {
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

}
