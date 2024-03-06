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

import eu.lunisolar.magma.basics.exceptions.NestedException;
import eu.lunisolar.magma.basics.exceptions.X;
import eu.lunisolar.magma.func.AsyncCallContext;
import eu.lunisolar.magma.func.CallContexts;
import eu.lunisolar.magma.func.action.LAction;
import eu.lunisolar.magma.func.consumer.LBiConsumer;
import eu.lunisolar.magma.func.consumer.LConsumer;
import eu.lunisolar.magma.func.function.LBiFunction;
import eu.lunisolar.magma.func.function.LFunction;
import eu.lunisolar.magma.func.operator.binary.LBinaryOperator;
import eu.lunisolar.magma.func.supp.Be;
import eu.lunisolar.magma.func.supp.Have;
import eu.lunisolar.magma.func.supp.P;
import eu.lunisolar.magma.func.supp.check.Checks;
import eu.lunisolar.magma.func.supp.value.LValue;
import eu.lunisolar.magma.func.supplier.LSupplier;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;

import static eu.lunisolar.magma.CallContextShared.*;
import static eu.lunisolar.magma.func.supp.check.Checks.attest;
import static eu.lunisolar.magma.func.supp.check.Checks.attestThrownBy;

public class CallContextTest {

    @DataProvider(name = "simpleTest")
    public static Object[][] test1() {return new Object[][]{{"3", "4"}, {"2", "3"}};}

    @Test(dataProvider = "simpleTest")
    public void simpleTest(String a1, String a2) {

        // given
        l().clear();
        var ctx = CallContexts.ctx(i1, f1);

        // when
        var result = LBiFunction.nestingApply(ctx, a1, a2, FUNC);

        // then
        attest(result).mustEx(Be::equalEx, a1 + a2);
        attest(l()).mustAEx(P::containExactlyEx, i1Log, a1, a2, f1Log);
    }

    @Test(dataProvider = "simpleTest")
    public void simpleTest_consumer(String a1, String a2) {

        // given
        l().clear();
        var ctx = CallContexts.ctx(i1, f1);

        // when
        LConsumer.nestingAccept(ctx, a1, str -> l().add(str));

        // then
        attest(l()).mustAEx(P::containExactlyEx, i1Log, a1, f1Log);
    }

    @Test(dataProvider = "simpleTest")
    public void combine_executesStartsAndEndsInOrder(String a1, String a2) {

        // given
        l().clear();
        var ctx1 = CallContexts.ctx(i1, f1);
        var ctx2 = CallContexts.ctx(i2, f2);

        // when
        var result = LBiFunction.nestingApply(ctx1, ctx2, a1, a2, FUNC);

        // then
        attest(l()).mustAEx(P::containExactlyEx, i1Log, i2Log, a1, a2, f2Log, f1Log);
    }

    @DataProvider(name = "exceptionHandling")
    public static Object[][] exceptionHandling() {
        return exceptionHandlingCases();
    }

    @Test(dataProvider = "exceptionHandling")
    public void combine_exceptionHandling_shoving(
            LSupplier<Object> init1, LBiConsumer<Object, Throwable> finish1,
            LSupplier<Object> init2, LBiConsumer<Object, Throwable> finish2,
            LBiFunction<String, String, String> function,
            Consumer<Checks.Check<Throwable>> exChecker,
            String... expectedLog
    ) {
        // given
        l().clear();
        var ctx1 = CallContexts.ctx(init1, finish1);
        var ctx2 = CallContexts.ctx(init2, finish2);

        // when
        var check = attestThrownBy(() -> {
            LBiFunction.shovingApply(ctx1, ctx2, ARG1, ARG2, function);
        });

        // then
        exChecker.accept(check);
        attest(l()).mustAEx(P::containExactlyEx, expectedLog);
    }

    @Test(dataProvider = "exceptionHandling")
    public void combine_exceptionHandling_nesting(
            LSupplier<Object> init1, LBiConsumer<Object, Throwable> finish1,
            LSupplier<Object> init2, LBiConsumer<Object, Throwable> finish2,
            LBiFunction<String, String, String> function,
            Consumer<Checks.Check<Throwable>> exChecker,
            String... expectedLog
    ) {
        // given
        l().clear();
        var ctx1 = CallContexts.ctx(init1, finish1);
        var ctx2 = CallContexts.ctx(init2, finish2);

        // when
        var check = attestThrownBy(() -> {
            LBiFunction.nestingApply(ctx1, ctx2, ARG1, ARG2, function);
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
    public void asyncSupplier() {
        // given
        LSupplier<Thread> function = () -> Thread.currentThread();
        l().clear();
        var asyncCtx = AsyncCallContext.ctx(call -> CompletableFuture.runAsync(call::execute));

        // when
        var unitTestThread = function.get();
        var futureThread   = LSupplier.asyncGet(asyncCtx, function);

        // then
        attest(futureThread.join()).mustEx(Be::notSameEx, unitTestThread);
    }

    @Test
    public void asyncSupplier_exception() {
        // given
        LSupplier<Thread> function  = () -> {throw X.unsupported("unsupported");};
        var               capturedL = l();
        capturedL.clear();
        var asyncCtx = AsyncCallContext.ctx(call -> CompletableFuture.runAsync(call::execute));
        var ctx1     = CallContexts.ctx(() -> capturedL.add(i1Log), () -> capturedL.add(f1Log));
        var future   = LSupplier.asyncGet(asyncCtx, function);

        // when
        var check = attestThrownBy(future::get);

        // then
        check.mustEx(Be::instanceOfEx, ExecutionException.class)
             .mustEx(Have::msgEqualEx, "java.lang.UnsupportedOperationException: unsupported")
             .mustEx(Have::noSuppressedEx)
             .mustEx(Have::causeEx)
             .check(Throwable::getCause, __ -> __
                     .mustEx(Be::instanceOfEx, UnsupportedOperationException.class)
                     .mustEx(Have::msgEqualEx, "unsupported")
                     .mustEx(Have::noCauseEx)
                     .mustEx(Have::noSuppressedEx));
    }

    @Test
    public void supplier_init_exception_returned_not_thrown() {
        // given
        var capturedL = l();
        capturedL.clear();
        var ctx1     = CallContexts.ctx(() -> new UnsupportedOperationException("unsupported"), obj -> {/*NOOP*/});

        // when
        var check = attestThrownBy(() -> LSupplier.shovingGet(ctx1, Object::new));

        // then
        check.mustEx(Be::instanceOfEx, UnsupportedOperationException.class)
             .mustEx(Have::msgEqualEx, "unsupported")
             .mustEx(Have::noCauseEx)
             .mustEx(Have::noSuppressedEx);
    }

    @Test
    public void asyncConsumer() {
        // given
        LConsumer<List<String>> function = list -> {
            list.add("F");
        };
        l().clear();
        var capturedL = l();
        var otherL    = LValue.<List<String>>objValue(null);
        var asyncCtx  = AsyncCallContext.ctx(call -> CompletableFuture.runAsync(call::execute));
        var ctx1 = CallContexts.ctx(() -> {
            capturedL.add(i1Log);
            l().clear();
            l().add(i1Log); // ThreadLocal means it will go to different list.
            otherL.value(l());
        }, () -> {
            capturedL.add(f1Log);
            l().add(f1Log); // ThreadLocal means it will go to different list.
        });

        // when
        var futureThread = LConsumer.asyncAccept(asyncCtx, ctx1, l(), function);
        futureThread.join();// making sure is actually ended

        // then

        attest(l()).mustAEx(P::containExactlyEx, i1Log, "F", f1Log);
        attest(otherL.value()).mustAEx(P::containExactlyEx, i1Log, f1Log);
    }

    @Test(dataProvider = "simpleTest")
    public void asyncBinaryOperator(String a1, String a2) {
        // given
        LBinaryOperator<String> function = (_1, _2) -> {
            l().add("F");
            return _1 + _2;
        };
        l().clear();
        var capturedL = l();
        var otherL    = LValue.<List<String>>objValue(null);
        var asyncCtx  = AsyncCallContext.ctx(call -> CompletableFuture.runAsync(call::execute));
        var ctx1 = CallContexts.ctx(() -> {
            capturedL.add(i1Log);
            l().clear();
            l().add(i1Log); // ThreadLocal means it will go to different list.
            otherL.value(l());
        }, () -> {
            capturedL.add(f1Log);
            l().add(f1Log); // ThreadLocal means it will go to different list.
        });

        // when
        var futureThread = LBinaryOperator.asyncApply(asyncCtx, ctx1, a1, a2, function);
        var result       = LFunction.tryApply(futureThread, CompletableFuture::get);// making sure is actually ended

        // then

        attest(l()).mustAEx(P::containExactlyEx, i1Log, f1Log);
        attest(otherL.value()).mustAEx(P::containExactlyEx, i1Log, "F", f1Log);
        attest(result).mustEx(Be::equalEx, a1 + a2);

    }

    @DataProvider(name = "simpleTest_statesData")
    public static Object[][] simpleTest_statesData() {
        return CallContextShared.simpleTest_statesData();
    }

    @Test(dataProvider = "simpleTest_statesData")
    public void combine_state(String state1Value, String state2Value) {

        // given
        l().clear();
        var ctx1 = CallContexts.ctx(() -> {
            l().add(i1Log);
            return state1Value;
        }, (state, e) -> {
            l().add(f1Log);
            attest(state).mustBeSame(state1Value);
        });
        var ctx2= CallContexts.ctx(() -> {
            l().add(i2Log);
            return state2Value;
        },  (state, e) -> {
            l().add(f2Log);
            attest(state).mustBeSame(state2Value);
        });

        // when
        var result = LBiFunction.nestingApply(ctx1, ctx2, "1", "2", FUNC);

        // then
        attest(l()).mustAEx(P::containExactlyEx, i1Log, i2Log, "1", "2", f2Log, f1Log);
    }

}
