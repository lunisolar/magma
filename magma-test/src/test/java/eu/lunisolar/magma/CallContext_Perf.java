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

import eu.lunisolar.magma.CallContext_Perf.SomeNamedContextUtility.SomeSimpleContextUtilitySupplier;
import eu.lunisolar.magma.basics.exceptions.Handling;
import eu.lunisolar.magma.func.AsyncCallContext;
import eu.lunisolar.magma.func.CallContext;
import eu.lunisolar.magma.func.action.LAction;
import eu.lunisolar.magma.func.operator.binary.LBinaryOperator;
import eu.lunisolar.magma.func.operator.binary.LIntBinaryOperator;
import eu.lunisolar.magma.func.supplier.LSupplier;
import eu.lunisolar.magma.test.JMH;
import eu.lunisolar.magma.test.random.Series;
import eu.lunisolar.magma.test.random.SeriesParams;
import org.openjdk.jmh.annotations.*;

import javax.annotation.concurrent.NotThreadSafe;
import java.util.concurrent.*;

import static eu.lunisolar.magma.basics.Null.nonNullArg;
import static eu.lunisolar.magma.test.random.Series.series;
import static eu.lunisolar.magma.test.random.SimpleRandoms.anInt;
import static org.openjdk.jmh.runner.options.TimeValue.seconds;

/**
 * Conclusions, given the JIT/JVM optimizations, given heavy accent on making CallContext instances reusable and well known (e.g. static) in place of use:
 * <ul>
 *      <li>Functionality not applicable for primitive domains/codomains (except maybe for TwoStage) - capturing of arguments + codomain - causes excessive boxing? (LIntBinaryOperator) [implementation adapted: removed from primitives]</li>
 *      <li>TwoStep can (given optimizations), performance wise and GC wise, outperform CallContext ONLY when no object is passed from start() to end() [implementation adapted]</li>
 *      <li>Aggregated CallContext (including 'downgraded' TwoStage instances) is faster than dedicated TwoStage aggregation.</li>
 *      <li>SomeUtil.execute(()-> return .......) is getting heavily optimized [implementation adapted: removed TwoStage altogether]</li>
 * </ul>
 *
 * @author Jakub Wach
 */
@SuppressWarnings({"unused", "unchecked"})
public class CallContext_Perf {

    private static final int THREADS     = 1;
    private static final int SERIES_SIZE = 1000;

    private static final LSupplier<Integer>       int1               = () -> anInt(40000);
    private static final int                      COUNT_ITERATIONS   = 10000;
    public static final  LBinaryOperator<Integer> FUNCTION           = (a1, a2) -> a1 >= a2 ? a1 : a2;
    public static final  LIntBinaryOperator       FUNCTION_PRIMITIVE = (a1, a2) -> a1 >= a2 ? a1 : a2;
    public static final  String                   NAME_STR           = "AAAA";

    private static SeriesParams<Integer> params() {
        return SeriesParams.<Integer>params().size(SERIES_SIZE).poolASize(1).percentageA(1).poolAProducer(int1)   // forcing some measure of exception handling
//                           .poolAProducer(() -> null)   // forcing some measure of exception handling
                           .poolBSize(20).percentageB(60).poolBProducer(int1).poolCProducer(int1);
    }

    public static interface SomeSimpleContextUtilitySupplier<V> {
        V calculate();
    }

    @NotThreadSafe
    public static class SomeSimpleContextUtility {
        public long entries = 0; // \
        public long exits   = 0; // / non atomic - performance will be measured just for one thread.

        public <V> V executeInContext(SomeSimpleContextUtilitySupplier<V> supplier) {
            enterContext();
            var v = supplier.calculate();
            exitContext();
            return v;
        }

        public void exitContext()  {exits++;}
        public void enterContext() {entries++;}

        // Bad because causes GC due to the interface incompatibility
        public static <V> V someBadUtilityMethod(SomeSimpleContextUtility u, LSupplier<V> calculation) {
            return u.executeInContext(calculation::shovingGet);
        }

        public static <V> V someGoodUtilityMethod(SomeSimpleContextUtility u, SomeSimpleContextUtilitySupplier<V> calculation) {
            return u.executeInContext(calculation);
        }

        long sum() {
            return entries + exits;
        }
    }

    public static interface ConvenientSupplier1<V> extends SomeSimpleContextUtilitySupplier<V> {

        default @Override V calculate() {
            try {
                return doCalculate();
            } catch (Throwable e) {
                throw Handling.throwIt(e);
            }
        }

        V doCalculate() throws Throwable;
    }

    public static interface ConvenientSupplier2<V> extends SomeSimpleContextUtilitySupplier<V>, LSupplier<V> {
        default @Override V calculate() {
            return shovingGet();
        }
    }

    @NotThreadSafe
    public static class SomeCustomizedContextUtility {

        public static <V> V someGoodUtilityMethod1(SomeSimpleContextUtility u, ConvenientSupplier1<V> calculation) {
            return u.executeInContext(calculation);
        }

        public static <V> V someGoodUtilityMethod2(SomeSimpleContextUtility u, ConvenientSupplier2<V> calculation) {
            return u.executeInContext(calculation);
        }

    }

    @NotThreadSafe
    public static class SomeNamedContextUtility {
        public long value = 0;

        public static interface SomeSimpleContextUtilitySupplier<V> {
            V calculate();
        }

        public <V> V executeInContext(String name, SomeSimpleContextUtilitySupplier<V> supplier) {
            long increment = name.length();
            var v = supplier.calculate();
            value += increment;
            return v;
        }

        public void enterContext(long increment) {value += increment;}

        public CallContext namedCtx(String name) {return CallContext.ctx(() -> name.length() /* something to hold on to */, l -> this.value += l);}

    }

    public static final SomeNamedContextUtility NAMED          = new SomeNamedContextUtility();
    static final        CallContext             named_intended = NAMED.namedCtx(NAME_STR);

    @State(Scope.Thread)
    public static class TheState {

        final SomeSimpleContextUtility C1 = new SomeSimpleContextUtility();
        final SomeSimpleContextUtility C2 = new SomeSimpleContextUtility();
        final SomeSimpleContextUtility C3 = new SomeSimpleContextUtility();
        final SomeSimpleContextUtility C4 = new SomeSimpleContextUtility();

        final SomeNamedContextUtility NAME = NAMED;

        final Series<Integer> a1 = series(params().name("a1"));
        final Series<Integer> a2 = series(params().name("a2"));

        CallContext CTX1 = CallContext.ctx(C1::enterContext, C1::exitContext);
        CallContext CTX2 = CallContext.ctx(C2::enterContext, C2::exitContext);
        CallContext CTX3 = CallContext.ctx(C3::enterContext, C3::exitContext);
        CallContext CTX4 = CallContext.ctx(C4::enterContext, C4::exitContext);

        AsyncCallContext FAKE_ASYNC = LAction::execute;

        CallContext[] ARRAY = new CallContext[]{CTX1, CTX2, CTX3, CTX4};

        CallContext decoupledCtx() {return ARRAY[iteration % ARRAY.length];}

        @Setup public void setup() {
        }

        @TearDown public void after() {
        }

        private int iteration = 0;

        public int i() {
            iteration++;
            iteration = iteration >= SERIES_SIZE ? 0 : iteration;
            return iteration;
        }

    }

    //<editor-fold desc="ref">

    @Benchmark @Threads(THREADS) public Object function_ref_performance_roof(TheState state) {
        int a = 0;
        var runningSum = 0;
        for (int c = 0; c < COUNT_ITERATIONS; c++) {
            var i = state.i();
            var a1 = state.a1.v(i);
            var a2 = state.a2.v(i);
            try {
                runningSum += a1 >= a2 ? a1 : a2;
            } catch (Exception e) {
                runningSum += 1;
            }
        }
        return runningSum + state.C1.entries + state.C2.exits;
    }

    @Benchmark @Threads(THREADS) public Object function_ref_case_to_replace_ideal(TheState state) {
        int a = 0;
        var runningSum = 0;
        for (int c = 0; c < COUNT_ITERATIONS; c++) {
            var i = state.i();
            var a1 = state.a1.v(i);
            var a2 = state.a2.v(i);
            try {
                runningSum += state.C1.executeInContext(() -> state.C2.executeInContext(() -> FUNCTION.apply(a1, a2)));
            } catch (Exception e) {
                runningSum += 1;
            }
        }
        return runningSum + state.C1.entries + state.C2.exits;
    }

    @Benchmark @Threads(THREADS) public Object function_ref_case_to_replace_ideal2(TheState state) {
        int a = 0;
        var runningSum = 0;
        for (int c = 0; c < COUNT_ITERATIONS; c++) {
            var i = state.i();
            var a1 = state.a1.v(i);
            var a2 = state.a2.v(i);
            try {
                runningSum += state.C1.executeInContext(() -> {
                    state.C2.enterContext();
                    Integer v = ((LSupplier<Integer>) () -> FUNCTION.apply(a1, a2)).shovingGet();
                    state.C2.exitContext();
                    return v;
                });
            } catch (Exception e) {
                runningSum += 1;
            }
        }
        return runningSum + state.C1.entries + state.C2.exits;
    }

    @Benchmark @Threads(THREADS) public Object function_ref_case_to_replace_NOT_ideal(TheState state) {
        int a = 0;
        var runningSum = 0;
        for (int c = 0; c < COUNT_ITERATIONS; c++) {
            var i = state.i();
            var a1 = state.a1.v(i);
            var a2 = state.a2.v(i);
            try {
                runningSum += state.C1.executeInContext(() -> state.C2.executeInContext(() -> state.C1.executeInContext(() -> FUNCTION.apply(a1, a2))));
            } catch (Exception e) {
                runningSum += 1;
            }
        }
        return runningSum + state.C1.entries + state.C2.exits;
    }

    /* Because of this case it is not beneficial to introduce CallContext as "unified" functional interface. */
    @Benchmark @Threads(THREADS) public Object function_ref_BAD_utilityMethod(TheState state) {
        int a = 0;
        var runningSum = 0;
        for (int c = 0; c < COUNT_ITERATIONS; c++) {
            var i = state.i();
            var a1 = state.a1.v(i);
            var a2 = state.a2.v(i);
            try {
                runningSum += SomeSimpleContextUtility.someBadUtilityMethod(state.C1, () -> FUNCTION.apply(a1, a2));
            } catch (Exception e) {
                runningSum += 1;
            }
        }
        return runningSum + state.C1.entries + state.C2.exits;
    }

    @Benchmark @Threads(THREADS) public Object function_ref_COMPROMISE_utilityMethod1(TheState state) {
        int a = 0;
        var runningSum = 0;
        for (int c = 0; c < COUNT_ITERATIONS; c++) {
            var i = state.i();
            var a1 = state.a1.v(i);
            var a2 = state.a2.v(i);
            try {
                runningSum += SomeCustomizedContextUtility.someGoodUtilityMethod1(state.C1, () -> FUNCTION.apply(a1, a2));
            } catch (Exception e) {
                runningSum += 1;
            }
        }
        return runningSum + state.C1.entries + state.C2.exits;
    }

    @Benchmark @Threads(THREADS) public Object function_ref_COMPROMISE_utilityMethod2(TheState state) {
        int a = 0;
        var runningSum = 0;
        for (int c = 0; c < COUNT_ITERATIONS; c++) {
            var i = state.i();
            var a1 = state.a1.v(i);
            var a2 = state.a2.v(i);
            try {
                runningSum += SomeCustomizedContextUtility.someGoodUtilityMethod2(state.C1, () -> FUNCTION.apply(a1, a2));
            } catch (Exception e) {
                runningSum += 1;
            }
        }
        return runningSum + state.C1.entries + state.C2.exits;
    }

    @Benchmark @Threads(THREADS) public Object function_ref_GOOD_utilityMethod(TheState state) {
        int a = 0;
        var runningSum = 0;
        for (int c = 0; c < COUNT_ITERATIONS; c++) {
            var i = state.i();
            var a1 = state.a1.v(i);
            var a2 = state.a2.v(i);
            try {
                runningSum += SomeSimpleContextUtility.someGoodUtilityMethod(state.C1, () -> FUNCTION.apply(a1, a2));
            } catch (Exception e) {
                runningSum += 1;
            }
        }
        return runningSum + state.C1.entries + state.C2.exits;
    }
    //</editor-fold>

    @Benchmark @Threads(THREADS) public Object function_ref_biOp_named_intended_CTX2(TheState state) {
        int a = 0;
        var runningSum = 0;
        for (int c = 0; c < COUNT_ITERATIONS; c++) {
            var i = state.i();
            var a1 = state.a1.v(i);
            var a2 = state.a2.v(i);
            try {
                runningSum += NAMED.executeInContext(NAME_STR, () -> {
                    return SomeSimpleContextUtility.someGoodUtilityMethod(state.C1, () -> FUNCTION.apply(a1, a2));
                });
            } catch (Exception e) {
                runningSum += 1;
            }
        }
        return runningSum + state.C1.entries + state.C2.exits;
    }

    //

    @Benchmark @Threads(THREADS) public Object call_biOp_CTX1(TheState state) {
        int a = 0;
        var runningSum = 0;
        for (int c = 0; c < COUNT_ITERATIONS; c++) {
            var i = state.i();
            var a1 = state.a1.v(i);
            var a2 = state.a2.v(i);
            try {
                runningSum += LBinaryOperator.shovingApply(state.CTX1, a1, a2, FUNCTION);
            } catch (Exception e) {
                runningSum += 1;
            }
        }
        return runningSum + state.C1.sum() + state.C2.sum() + state.C3.sum() + state.C4.sum();
    }

    @Benchmark @Threads(THREADS) public Object call_biOp_CTX1_CTX2(TheState state) {
        int a = 0;
        var runningSum = 0;
        for (int c = 0; c < COUNT_ITERATIONS; c++) {
            var i = state.i();
            var a1 = state.a1.v(i);
            var a2 = state.a2.v(i);
            try {
                runningSum += LBinaryOperator.shovingApply(state.CTX1, state.CTX2, a1, a2, FUNCTION);
            } catch (Exception e) {
                runningSum += 1;
            }
        }
        return runningSum + state.C1.sum() + state.C2.sum() + state.C3.sum() + state.C4.sum();
    }

    @Benchmark @Threads(THREADS) public Object call_biOp_CTX1_CTX2_with_1null(TheState state) {
        int a = 0;
        var runningSum = 0;
        for (int c = 0; c < COUNT_ITERATIONS; c++) {
            var i = state.i();
            var a1 = state.a1.v(i);
            var a2 = state.a2.v(i);
            try {
                runningSum += LBinaryOperator.shovingApply(null, state.CTX1, state.CTX2, a1, a2, FUNCTION);
            } catch (Exception e) {
                runningSum += 1;
            }
        }
        return runningSum + state.C1.sum() + state.C2.sum() + state.C3.sum() + state.C4.sum();
    }

    @Benchmark @Threads(THREADS) public Object call_biOp_CTX1_CTX2_with_2null(TheState state) {
        int a = 0;
        var runningSum = 0;
        for (int c = 0; c < COUNT_ITERATIONS; c++) {
            var i = state.i();
            var a1 = state.a1.v(i);
            var a2 = state.a2.v(i);
            try {
                runningSum += LBinaryOperator.shovingApply(null, state.CTX1, null, state.CTX2, a1, a2, FUNCTION);
            } catch (Exception e) {
                runningSum += 1;
            }
        }
        return runningSum + state.C1.sum() + state.C2.sum() + state.C3.sum() + state.C4.sum();
    }

    @Benchmark @Threads(THREADS) public Object call_biOp_CTX1_CTX2_CTX3(TheState state) {
        int a = 0;
        var runningSum = 0;
        for (int c = 0; c < COUNT_ITERATIONS; c++) {
            var i = state.i();
            var a1 = state.a1.v(i);
            var a2 = state.a2.v(i);
            try {
                runningSum += LBinaryOperator.shovingApply(state.CTX1, state.CTX2, state.CTX3, a1, a2, FUNCTION);
            } catch (Exception e) {
                runningSum += 1;
            }
        }
        return runningSum + state.C1.sum() + state.C2.sum() + state.C3.sum() + state.C4.sum();
    }

    @Benchmark @Threads(THREADS) public Object call_biOp_CTX1_CTX2_CTX3_CTX4(TheState state) {
        int a = 0;
        var runningSum = 0;
        for (int c = 0; c < COUNT_ITERATIONS; c++) {
            var i = state.i();
            var a1 = state.a1.v(i);
            var a2 = state.a2.v(i);
            try {
                runningSum += LBinaryOperator.shovingApply(state.CTX1, state.CTX2, state.CTX3, state.CTX4, a1, a2, FUNCTION);
            } catch (Exception e) {
                runningSum += 1;
            }
        }
        return runningSum + state.C1.sum() + state.C2.sum() + state.C3.sum() + state.C4.sum();
    }

    @Benchmark @Threads(THREADS) public Object call_biOp_EXTRA_CTX1_CTX2_CTX3_CTX4(TheState state) {
        int a = 0;
        var runningSum = 0;
        for (int c = 0; c < COUNT_ITERATIONS; c++) {
            var i = state.i();
            var a1 = state.a1.v(i);
            var a2 = state.a2.v(i);
            try {
                runningSum += state.C1.executeInContext(() -> { // <- some context that cannot be two-phase
                    return LBinaryOperator.shovingApply(state.CTX1, state.CTX2, state.CTX3, state.CTX4, a1, a2, FUNCTION);
                });
            } catch (Exception e) {
                runningSum += 1;
            }
        }
        return runningSum + state.C1.sum() + state.C2.sum() + state.C3.sum() + state.C4.sum();
    }

    @Benchmark @Threads(THREADS) public Object call_biOp_EXTRA_CTX1_CTX2_CTX3_CTX42(TheState state) {
        int a = 0;
        var runningSum = 0;
        for (int c = 0; c < COUNT_ITERATIONS; c++) {
            var i = state.i();
            var a1 = state.a1.v(i);
            var a2 = state.a2.v(i);
            try {
                runningSum += state.C1.executeInContext(() -> { // <- some context that cannot be two-phase
                    return LBinaryOperator.shovingApply(state.CTX1, state.CTX2, state.CTX3, state.CTX4, a1, a2, (a11, a21) -> a11 >= a21 ? a11 : a21);
                });
            } catch (Exception e) {
                runningSum += 1;
            }
        }
        return runningSum + state.C1.sum() + state.C2.sum() + state.C3.sum() + state.C4.sum();
    }
    @Benchmark @Threads(THREADS) public Object call_action_CTX1_CTX2_CTX3_CTX4(TheState state) {
        int a = 0;
        var runningSum = 0;
        for (int c = 0; c < COUNT_ITERATIONS; c++) {
            var i = state.i();
            var a1 = state.a1.v(i);
            var a2 = state.a2.v(i);
            try {
                LAction.shovingExecute(state.CTX1, state.CTX2, state.CTX3, state.CTX4, () -> FUNCTION.applyX(a1, a2));
            } catch (Exception e) {
                runningSum += 1;
            }
        }
        return runningSum + state.C1.sum() + state.C2.sum() + state.C3.sum() + state.C4.sum();
    }

//    @Benchmark @Threads(THREADS) public Object call_biOp_EXTRA_CTX1_CTX2_CTX3_CTX4_primitive(TheState state) {
//        int a = 0;
//        var runningSum = 0;
//        for (int c = 0; c < COUNT_ITERATIONS; c++) {
//            var i = state.i();
//            var a1 = state.a1.v(i);
//            var a2 = state.a2.v(i);
//            try {
//                runningSum += state.C1.executeInContext(() -> { // <- some context that cannot be two-phase
//                    return LIntBinaryOperator.shovingApplyAsInt(state.CTX1, state.CTX2, state.CTX3, state.CTX4, a1, a2, FUNCTION_PRIMITIVE);
//                });
//            } catch (Exception e) {
//                runningSum += 1;
//            }
//        }
//        return runningSum + state.C1.sum() + state.C2.sum() + state.C3.sum() + state.C4.sum();
//    }

    @Benchmark @Threads(THREADS) public Object call_biOp_named_adHoc_CTX2(TheState state) {
        int a = 0;
        var runningSum = 0;
        for (int c = 0; c < COUNT_ITERATIONS; c++) {
            var i = state.i();
            var a1 = state.a1.v(i);
            var a2 = state.a2.v(i);
            try {
                runningSum += LBinaryOperator.shovingApply(state.NAME.namedCtx(NAME_STR), state.CTX2, a1, a2, FUNCTION);
            } catch (Exception e) {
                runningSum += 1;
            }
        }
        return runningSum + state.C1.sum() + state.C2.sum() + state.C3.sum() + state.C4.sum();
    }

    @Benchmark @Threads(THREADS) public Object call_biOp_named_adHoc_better_CTX2(TheState state) {
        int a = 0;
        var runningSum = 0;
        final CallContext aaaaa = state.NAME.namedCtx(NAME_STR);
        for (int c = 0; c < COUNT_ITERATIONS; c++) {
            var i = state.i();
            var a1 = state.a1.v(i);
            var a2 = state.a2.v(i);
            try {
                runningSum += LBinaryOperator.shovingApply(aaaaa, state.CTX2, a1, a2, FUNCTION);
            } catch (Exception e) {
                runningSum += 1;
            }
        }
        return runningSum + state.C1.sum() + state.C2.sum() + state.C3.sum() + state.C4.sum();
    }

    @Benchmark @Threads(THREADS) public Object call_biOp_named_intended_CTX2(TheState state) {
        int a = 0;
        var runningSum = 0;

        for (int c = 0; c < COUNT_ITERATIONS; c++) {
            var i = state.i();
            var a1 = state.a1.v(i);
            var a2 = state.a2.v(i);
            try {
                runningSum += LBinaryOperator.shovingApply(named_intended, state.CTX2, a1, a2, FUNCTION);
            } catch (Exception e) {
                runningSum += 1;
            }
        }
        return runningSum + state.C1.sum() + state.C2.sum() + state.C3.sum() + state.C4.sum();
    }

    @Benchmark @Threads(THREADS) public Object call_biOp_named_intended_CTX2____FAKE_ASYNC(TheState state) {
        int a = 0;
        var runningSum = 0;

        for (int c = 0; c < COUNT_ITERATIONS; c++) {
            var i = state.i();
            var a1 = state.a1.v(i);
            var a2 = state.a2.v(i);
            try {
                runningSum += LBinaryOperator.asyncApply(state.FAKE_ASYNC, named_intended, state.CTX2, a1, a2, FUNCTION).join();
            } catch (Exception e) {
                runningSum += 1;
            }
        }
        return runningSum + state.C1.sum() + state.C2.sum() + state.C3.sum() + state.C4.sum();
    }

    @Benchmark @Threads(THREADS) public Object call_biOp_semiRandom_EXTRA_CTX1_CTX2(TheState state) {
        int a = 0;
        var runningSum = 0;
        for (int c = 0; c < COUNT_ITERATIONS; c++) {
            var i = state.i();
            var a1 = state.a1.v(i);
            var a2 = state.a2.v(i);
            try {
                runningSum += state.C1.executeInContext(() -> { // <- some context that cannot be two-phase
                    return LBinaryOperator.shovingApply(state.decoupledCtx(),
                                                        state.decoupledCtx(),
                                                        a1,
                                                        a2,
                                                        (a11, a21) -> a11 >= a21 ? a11 : a21);
                });
            } catch (Exception e) {
                runningSum += 1;
            }
        }
        return runningSum + state.C1.sum() + state.C2.sum() + state.C3.sum() + state.C4.sum();
    }

    @Benchmark @Threads(THREADS) public Object call_biOp_semiRandom_EXTRA_CTX1_CTX2_CTX3(TheState state) {
        int a = 0;
        var runningSum = 0;
        for (int c = 0; c < COUNT_ITERATIONS; c++) {
            var i = state.i();
            var a1 = state.a1.v(i);
            var a2 = state.a2.v(i);
            try {
                runningSum += state.C1.executeInContext(() -> { // <- some context that cannot be two-phase
                    return LBinaryOperator.shovingApply(state.decoupledCtx(),
                                                        state.decoupledCtx(),
                                                        state.decoupledCtx(),
                                                        a1,
                                                        a2,
                                                        (a11, a21) -> a11 >= a21 ? a11 : a21);
                });
            } catch (Exception e) {
                runningSum += 1;
            }
        }
        return runningSum + state.C1.sum() + state.C2.sum() + state.C3.sum() + state.C4.sum();
    }

    @Benchmark @Threads(THREADS) public Object call_biOp_semiRandom_EXTRA_CTX1_CTX2_CTX3_CTX42(TheState state) {
        int a = 0;
        var runningSum = 0;
        for (int c = 0; c < COUNT_ITERATIONS; c++) {
            var i = state.i();
            var a1 = state.a1.v(i);
            var a2 = state.a2.v(i);
            try {
                runningSum += state.C1.executeInContext(() -> { // <- some context that cannot be two-phase
                    return LBinaryOperator.shovingApply(state.decoupledCtx(),
                                                        state.decoupledCtx(),
                                                        state.decoupledCtx(),
                                                        state.decoupledCtx(),
                                                        a1,
                                                        a2,
                                                        (a11, a21) -> a11 >= a21 ? a11 : a21);
                });
            } catch (Exception e) {
                runningSum += 1;
            }
        }
        return runningSum + state.C1.sum() + state.C2.sum() + state.C3.sum() + state.C4.sum();
    }

    @Benchmark @Threads(THREADS) public Object call_biOp_semiRandom_CTX1_CTX2_CTX3_CTX42(TheState state) {
        int a = 0;
        var runningSum = 0;
        for (int c = 0; c < COUNT_ITERATIONS; c++) {
            var i = state.i();
            var a1 = state.a1.v(i);
            var a2 = state.a2.v(i);
            try {
                runningSum += LBinaryOperator.shovingApply(state.decoupledCtx(),
                                                           state.decoupledCtx(),
                                                           state.decoupledCtx(),
                                                           state.decoupledCtx(),
                                                           a1,
                                                           a2,
                                                           (a11, a21) -> a11 >= a21 ? a11 : a21);
            } catch (Exception e) {
                runningSum += 1;
            }
        }
        return runningSum + state.C1.sum() + state.C2.sum() + state.C3.sum() + state.C4.sum();
    }

    public static void main(String... args) {
        JMH.jmh()
           .java10ServerArgs()
//           .iterations(3, seconds(10), 3, seconds(10))
           .iterations(2, seconds(2), 2, seconds(2))
           .classes(CallContext_Perf.class)
           .gc()
           .mode(Mode.Throughput)
           .opt(o -> o.timeUnit(TimeUnit.MILLISECONDS))
           .run();
    }

}
