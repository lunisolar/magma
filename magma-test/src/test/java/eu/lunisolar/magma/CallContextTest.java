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
import eu.lunisolar.magma.func.CallContext;
import eu.lunisolar.magma.func.action.LAction;
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

import javax.annotation.Nonnull;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;

import static eu.lunisolar.magma.func.supp.check.Checks.attest;
import static eu.lunisolar.magma.func.supp.check.Checks.attestThrownBy;

public class CallContextTest {

    @DataProvider(name = "simpleTest")
    public static Object[][] test1() {return new Object[][]{{"3", "4"}, {"2", "3"}};}

    @Test(dataProvider = "simpleTest")
    public void simpleTestTwoStage(String a1, String a2) {

        // given
        l().clear();
        var ctx = CallContext.ctx(() -> {
            i1.execute();
            return null;
        }, (c, x) -> f1.execute());

        // when
        var result = LBiFunction.nestingApply(ctx, a1, a2, FUNC);

        // then
        attest(result).mustEx(Be::equalEx, a1 + a2);
        attest(l()).mustAEx(P::containExactlyEx, i1Ex, a1, a2, f1Ex);
    }

    @Test(dataProvider = "simpleTest")
    public void simpleTestTwoStage2(String a1, String a2) {

        // given
        l().clear();
        var ctx = CallContext.ctx(i1, f1);

        // when
        var result = LBiFunction.nestingApply(ctx, a1, a2, FUNC);

        // then
        attest(result).mustEx(Be::equalEx, a1 + a2);
        attest(l()).mustAEx(P::containExactlyEx, i1Ex, a1, a2, f1Ex);
    }

    @Test(dataProvider = "simpleTest")
    public void simpleTest_consumer(String a1, String a2) {

        // given
        l().clear();
        var ctx = CallContext.ctx(i1, f1);

        // when
        LConsumer.nestingAccept(ctx, a1, str -> l().add(str));

        // then
        attest(l()).mustAEx(P::containExactlyEx, i1Ex, a1, f1Ex);
    }

    @Test(dataProvider = "simpleTest")
    public void combineTwoStage_executesStartsAndEndsInOrder(String a1, String a2) {

        // given
        l().clear();
        var ctx1 = CallContext.ctx(i1, f1);
        var ctx2 = CallContext.ctx(i2, f2);

        // when
        var result = LBiFunction.nestingApply(ctx1, ctx2, a1, a2, FUNC);

        // then
        attest(l()).mustAEx(P::containExactlyEx, i1Ex, i2Ex, a1, a2, f2Ex, f1Ex);
    }

    //<editor-fold desc="setup">

    /** To enhance coverage for CallContext. */
    @Nonnull private CallContext ctxCreate_finisherWithThrowableArg(
            LAction init1, LAction finalize1) {
        return CallContext.ctx(() -> {
            init1.executeX();
            return null;
        }, (ignored1, ignored2) -> finalize1.executeX());
    }

    private static final ThreadLocal<List<String>> L = ThreadLocal.withInitial(ArrayList::new);
    private static List<String> l() {return L.get();}

    private static Object[] p(
            LAction init1, LAction finish1,
            LAction init2, LAction finish2,
            LBiFunction<String, String, String> function,
            Consumer<Checks.Check<Throwable>> exChecker,
            String... expectedLog
    ) {
        return new Object[]{init1, finish1, init2, finish2, function, exChecker, expectedLog};
    }

    public static final LBiFunction<String, String, String> FUNC    = (a1, a2) -> {
        l().add("" + a1);
        l().add("" + a2);
        return a1 + a2;
    };
    public static final String                              ARG1    = "a1";
    public static final String                              ARG2    = "a2";
    public static final String                              ExEX_F   = "E1";
    public static final LBiFunction<String, String, String> EX_FUNC = (a1, a2) -> {throw new Exception(ExEX_F);};
    public static final String                              i1Ex     = "C1.init";
    public static final String                              i2Ex     = "C2.init";
    public static final String                              f1Ex     = "C1.finalize";
    public static final String                              f2Ex     = "C2.finalize";
    public static final LAction                             i1      = () -> l().add(i1Ex);
    public static final LAction                             i2      = () -> l().add(i2Ex);
    public static final LAction                             f1      = () -> l().add(f1Ex);
    public static final LAction                             f2      = () -> l().add(f2Ex);
    public static final LAction                             EX_I1   = () -> {throw new Exception("I1");};
    public static final LAction                             EX_F1   = () -> {throw new Exception("F1");};
    public static final LAction                             EX_I2   = () -> {throw new Exception("I2");};
    public static final LAction                             EX_F2   = () -> {throw new Exception("F2");};

    //</editor-fold>

    @DataProvider(name = "exceptionHandling")
    public static Object[][] exceptionHandling() {
        return new Object[][]{
                p(i1, f1, i2, f2, EX_FUNC,
                  attest -> attest.mustEx(Be::exactlyInstanceOfEx, Exception.class)
                                  .mustEx(Have::msgEqualEx, ExEX_F)
                                  .mustEx(Have::noCauseEx)
                                  .mustEx(Have::noSuspendedEx),
                  i1Ex, i2Ex, f2Ex, f1Ex
                ),

                //<editor-fold desc="single exception in context">
                p(EX_I1, f1, i2, f2, FUNC,
                  attest -> attest.mustEx(Be::exactlyInstanceOfEx, Exception.class)
                                  .mustEx(Have::msgEqualEx, "I1")
                                  .mustEx(Have::noCauseEx)
                                  .mustEx(Have::noSuspendedEx)
                ),
                p(i1, EX_F1, i2, f2, FUNC,
                  attest -> attest.mustEx(Be::exactlyInstanceOfEx, Exception.class)
                                  .mustEx(Have::msgEqualEx, "F1")
                                  .mustEx(Have::noCauseEx)
                                  .mustEx(Have::noSuspendedEx),
                  i1Ex, i2Ex, ARG1, ARG2, f2Ex
                ),
                p(i1, f1, EX_I2, f2, FUNC,
                  attest -> attest.mustEx(Be::exactlyInstanceOfEx, Exception.class)
                                  .mustEx(Have::msgEqualEx, "I2")
                                  .mustEx(Have::noCauseEx)
                                  .mustEx(Have::noSuspendedEx),
                  i1Ex, f1Ex
                ),
                p(i1, f1, i2, EX_F2, FUNC,
                  attest -> attest.mustEx(Be::exactlyInstanceOfEx, Exception.class)
                                  .mustEx(Have::msgEqualEx, "F2")
                                  .mustEx(Have::noCauseEx)
                                  .mustEx(Have::noSuspendedEx),
                  i1Ex, i2Ex, ARG1, ARG2, f1Ex
                ),
                //</editor-fold>

                //<editor-fold desc="both main function and finisher fails">
                p(i1, EX_F1, i2, f2, EX_FUNC,
                  attest -> attest.mustEx(Be::exactlyInstanceOfEx, Exception.class)
                                  .mustEx(Have::msgEqualEx, "E1")
                                  .mustEx(Have::noCauseEx)
                                  .mustEx(Have::suspendedEx)
                                  .check(e -> e.getSuppressed()[0], ch -> {
                                      ch.mustEx(Be::exactlyInstanceOfEx, Exception.class)
                                        .mustEx(Have::msgEqualEx, "F1")
                                        .mustEx(Have::noCauseEx)
                                        .mustEx(Have::noSuspendedEx);
                                  }),
                  i1Ex, i2Ex, f2Ex
                ),
                p(i1, f1, i2, EX_F2, EX_FUNC,
                  attest -> attest.mustEx(Be::exactlyInstanceOfEx, Exception.class)
                                  .mustEx(Have::msgEqualEx, "E1")
                                  .mustEx(Have::noCauseEx)
                                  .check(e -> e.getSuppressed()[0], ch -> {
                                      ch.mustEx(Be::exactlyInstanceOfEx, Exception.class)
                                        .mustEx(Have::msgEqualEx, "F2")
                                        .mustEx(Have::noCauseEx)
                                        .mustEx(Have::noSuspendedEx);
                                  }),
                  i1Ex, i2Ex, f1Ex
                ),
                //</editor-fold>

                //<editor-fold desc="main function and both finisher fails">
                p(i1, EX_F1, i2, EX_F2, EX_FUNC,
                  attest -> attest.mustEx(Be::exactlyInstanceOfEx, Exception.class)
                                  .mustEx(Have::msgEqualEx, "E1")
                                  .mustEx(Have::noCauseEx)
                                  .check(e -> e.getSuppressed()[0], ch -> {
                                      ch.mustEx(Be::exactlyInstanceOfEx, Exception.class)
                                        .mustEx(Have::msgEqualEx, "F2")
                                        .mustEx(Have::noCauseEx)
                                        .mustEx(Have::noSuspendedEx);
                                  })
                                  .check(e -> e.getSuppressed()[1], ch2 -> {
                                      ch2.mustEx(Be::exactlyInstanceOfEx, Exception.class)
                                         .mustEx(Have::msgEqualEx, "F1")
                                         .mustEx(Have::noCauseEx)
                                         .mustEx(Have::noSuspendedEx);
                                  }),
                  i1Ex, i2Ex
                ),
                //</editor-fold>

                //<editor-fold desc="last initializer + first finisher fail">
                p(i1, EX_F1, EX_I2, f2, FUNC,
                  attest -> attest.mustEx(Be::exactlyInstanceOfEx, Exception.class)
                                  .mustEx(Have::msgEqualEx, "I2")
                                  .mustEx(Have::noCauseEx)
                                  .check(e -> e.getSuppressed()[0], ch -> {
                                      ch.mustEx(Be::exactlyInstanceOfEx, Exception.class)
                                        .mustEx(Have::msgEqualEx, "F1")
                                        .mustEx(Have::noCauseEx)
                                        .mustEx(Have::noSuspendedEx);
                                  }),
                  i1Ex
                ),
                //</editor-fold>

        };
    }

    @Test(dataProvider = "exceptionHandling")
    public void combine_exceptionHandling_shoving(
            LAction init1, LAction finish1,
            LAction init2, LAction finish2,
            LBiFunction<String, String, String> function,
            Consumer<Checks.Check<Throwable>> exChecker,
            String... expectedLog
    ) {
        // given
        l().clear();
        var ctx1 = CallContext.ctx(init1, finish1);
        var ctx2 = CallContext.ctx(init2, finish2);

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
            LAction init1, LAction finish1,
            LAction init2, LAction finish2,
            LBiFunction<String, String, String> function,
            Consumer<Checks.Check<Throwable>> exChecker,
            String... expectedLog
    ) {
        // given
        l().clear();
        var ctx1 = CallContext.ctx(init1, finish1);
        var ctx2 = CallContext.ctx(init2, finish2);

        // when
        var check = attestThrownBy(() -> {
            LBiFunction.nestingApply(ctx1, ctx2, ARG1, ARG2, function);
        });

        // then
        check.mustEx(Be::exactlyInstanceOfEx, NestedException.class)
             .mustEx(Have::noSuspendedEx)
             .mustEx(Have::causeEx)
             .check(Throwable::getCause, exChecker::accept);
        attest(l()).mustAEx(P::containExactlyEx, expectedLog);
    }

    @Test(dataProvider = "exceptionHandling")
    public void combine_exceptionHandling_finisherWithThrowableArg(
            LAction init1, LAction finish1,
            LAction init2, LAction finish2,
            LBiFunction<String, String, String> function,
            Consumer<Checks.Check<Throwable>> exChecker,
            String... expectedLog
    ) {
        // given
        l().clear();
        var ctx1 = ctxCreate_finisherWithThrowableArg(init1, finish1);
        var ctx2 = ctxCreate_finisherWithThrowableArg(init2, finish2);

        // when
        var check = attestThrownBy(() -> {
            LBiFunction.shovingApply(ctx1, ctx2, ARG1, ARG2, function);
        });

        // then
        exChecker.accept(check);
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
        var futureThread = LSupplier.asyncGet(asyncCtx, function);

        // then
        attest(futureThread.join()).mustEx(Be::notSameEx, unitTestThread);
    }

    @Test
    public void asyncSupplier_exception() {
        // given
        LSupplier<Thread> function = () -> {throw X.unsupported("unsupported");};
        var capturedL = l();
        capturedL.clear();
        var asyncCtx = AsyncCallContext.ctx(call -> CompletableFuture.runAsync(call::execute));
        var ctx1 = CallContext.ctx(() -> capturedL.add(i1Ex), () -> capturedL.add(f1Ex));
        var future = LSupplier.asyncGet(asyncCtx, function);

        // when
        var check = attestThrownBy(future::get);

        // then
        check.mustEx(Be::instanceOfEx, ExecutionException.class)
             .mustEx(Have::msgEqualEx, "java.lang.UnsupportedOperationException: unsupported")
             .mustEx(Have::noSuspendedEx)
             .mustEx(Have::causeEx)
             .check(Throwable::getCause, __ -> __
                     .mustEx(Be::instanceOfEx, UnsupportedOperationException.class)
                     .mustEx(Have::msgEqualEx, "unsupported")
                     .mustEx(Have::noCauseEx)
                     .mustEx(Have::noSuspendedEx));
    }

    @Test
    public void supplier_init_exception_returned_not_thrown() {
        // given
        var capturedL = l();
        capturedL.clear();
        var asyncCtx = AsyncCallContext.ctx(call -> CompletableFuture.runAsync(call::execute));
        var ctx1 = CallContext.ctx(()-> new UnsupportedOperationException("unsupported"), obj-> {/*NOOP*/});

        // when
        var check = attestThrownBy(()-> LSupplier.shovingGet(ctx1, Object::new));

        // then
        check.mustEx(Be::instanceOfEx, UnsupportedOperationException.class)
             .mustEx(Have::msgEqualEx, "unsupported")
             .mustEx(Have::noCauseEx)
             .mustEx(Have::noSuspendedEx);
    }

    @Test
    public void asyncConsumer() {
        // given
        LConsumer<List<String>> function = list -> {
            list.add("F");
        };
        l().clear();
        var capturedL = l();
        var otherL = LValue.<List<String>>objValue(null);
        var asyncCtx = AsyncCallContext.ctx(call -> CompletableFuture.runAsync(call::execute));
        var ctx1 = CallContext.ctx(() -> {
            capturedL.add(i1Ex);
            l().clear();
            l().add(i1Ex); // ThreadLocal means it will go to different list.
            otherL.value(l());
        }, () -> {
            capturedL.add(f1Ex);
            l().add(f1Ex); // ThreadLocal means it will go to different list.
        });

        // when
        var futureThread = LConsumer.asyncAccept(asyncCtx, ctx1, l(), function);
        futureThread.join();// making sure is actually ended

        // then

        attest(l()).mustAEx(P::containExactlyEx, i1Ex, "F", f1Ex);
        attest(otherL.value()).mustAEx(P::containExactlyEx, i1Ex, f1Ex);
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
        var otherL = LValue.<List<String>>objValue(null);
        var asyncCtx = AsyncCallContext.ctx(call -> CompletableFuture.runAsync(call::execute));
        var ctx1 = CallContext.ctx(() -> {
            capturedL.add(i1Ex);
            l().clear();
            l().add(i1Ex); // ThreadLocal means it will go to different list.
            otherL.value(l());
        }, () -> {
            capturedL.add(f1Ex);
            l().add(f1Ex); // ThreadLocal means it will go to different list.
        });

        // when
        var futureThread = LBinaryOperator.asyncApply(asyncCtx, ctx1, a1, a2, function);
        var result = LFunction.tryApply(futureThread, CompletableFuture::get);// making sure is actually ended

        // then

        attest(l()).mustAEx(P::containExactlyEx, i1Ex, f1Ex);
        attest(otherL.value()).mustAEx(P::containExactlyEx, i1Ex, "F", f1Ex);
        attest(result).mustEx(Be::equalEx, a1 + a2);

    }

}
