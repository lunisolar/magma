/*
 * This file is part of "lunisolar-magma".
 *
 * (C) Copyright 2014-2022 Lunisolar (http://lunisolar.eu/).
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

import eu.lunisolar.magma.func.CallContext;
import eu.lunisolar.magma.func.operator.binary.LBinaryOperator;
import eu.lunisolar.magma.func.operator.binary.LIntBinaryOperator;
import eu.lunisolar.magma.func.supplier.LSupplier;
import eu.lunisolar.magma.test.JMH;
import eu.lunisolar.magma.test.random.Series;
import eu.lunisolar.magma.test.random.SeriesParams;
import org.openjdk.jmh.annotations.*;

import javax.annotation.Nullable;
import java.util.concurrent.*;

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

    private static SeriesParams<Integer> params() {
        return SeriesParams.<Integer>params()
                           .size(SERIES_SIZE)
                           .poolASize(5)
                           .percentageA(39)
                           .poolAProducer(int1)
                           .poolBSize(20)
                           .percentageB(60)
                           .poolBProducer(int1)
                           .poolCProducer(int1);
    }

    @State(Scope.Thread)
    public static class TheState {

        static Series<Integer> a1 = series(params().name("a1"));
        static Series<Integer> a2 = series(params().name("a2"));

        long entries = 0;
        long exits   = 0;

        // Thread unsafe - CallContext internal impact for measurement must be minimal.
        CallContext CTX_TS_1 = CallContext.ctx(() -> {if (exits % 2 == 0) {entries++;}}, () -> exits++);
//        CallContext CTX_TS_1 = CallContext.ctx(() -> entries++, () -> exits++);

        // Thread unsafe - CallContext internal impact for measurement must be minimal.
        CallContext CTX_TS_2 = CallContext.ctx(() -> entries++, () -> exits++);

        // Thread unsafe - CallContext internal impact for measurement must be minimal.
        CallContext CTX_1 = functionCall -> {
            entries++;
            var v = functionCall.get();
            exits++;
            return v;
        };

        // Thread unsafe - CallContext internal impact for measurement must be minimal.
        CallContext CTX_2 = functionCall -> {
            entries++;
            var v = functionCall.get();
            exits++;
            return v;
        };

        CallContext CTX_TS_DOUBLE = CallContext.combine(CTX_TS_1, CTX_TS_2);
        CallContext CTX_DOUBLE    = CallContext.combine(CTX_1, CTX_2);
        CallContext CTX3_DOUBLE   = CallContext.combine((CallContext) CTX_TS_1, CTX_TS_2);
        CallContext CTX4_4        = CallContext.combine(CTX_1, CTX_2, CTX_1, CTX_2);

        CallContext[] ARRAY = new CallContext[]{CTX_1, CTX_TS_1, CTX_2, CTX_TS_2, CTX_DOUBLE};
//        TwoStage[]    ARRAY2 = new TwoStage[]{CTX_TS_1, CTX_TS_2};
//        TwoStage[]    ARRAY3 = new TwoStage[]{CTX_TS_1, CTX_TS_DOUBLE};

        CallContext decoupledCtx() {return ARRAY[iteration % ARRAY.length];}
//        TwoStage decoupledTwoStage() {return ARRAY2[iteration % ARRAY2.length];}
//        TwoStage decoupledTwoStage2() {return ARRAY3[iteration % ARRAY2.length];}

        @Setup
        public void setup() {
        }

        @TearDown
        public void after() {
        }

        private int iteration = 0;

        public int i() {
            iteration++;
            iteration = iteration >= SERIES_SIZE ? 0 : iteration;
            return iteration;
        }

    }

    @Benchmark @Threads(THREADS) public Object function_reference(TheState state) {
        int a = 0;
        var runningSum = 0;
        for (int c = 0; c < COUNT_ITERATIONS; c++) {
            var i = state.i();
            var a1 = state.a1.v(i);
            var a2 = state.a2.v(i);

            runningSum += LBinaryOperator.call(a1, a2, FUNCTION);
        }
        return runningSum + state.entries + state.exits;
    }

    @Benchmark @Threads(THREADS) public Object call_CTX_TS_1(TheState state) {
        int a = 0;
        var runningSum = 0;
        for (int c = 0; c < COUNT_ITERATIONS; c++) {
            var i = state.i();
            var a1 = state.a1.v(i);
            var a2 = state.a2.v(i);

            runningSum += LBinaryOperator.shovingApply(state.CTX_TS_1, a1, a2, FUNCTION);
        }
        return runningSum + state.entries + state.exits;
    }

//    @Benchmark @Threads(THREADS) public Object call_CTX_TS_1_primitive(TheState state) {
//        int a = 0;
//        var runningSum = 0;
//        for (int c = 0; c < COUNT_ITERATIONS; c++) {
//            var i = state.i();
//            var a1 = state.a1.v(i);
//            var a2 = state.a2.v(i);
//
//            runningSum += LIntBinaryOperator.applyAsInt(state.CTX_TS_1, a1, a2, FUNCTION_PRIMITIVE);
//        }
//        return runningSum + state.entries + state.exits;
//    }

//    @Benchmark @Threads(THREADS) public Object call_CTX_TS_1_primitive_decoupled(TheState state) {
//        int a = 0;
//        var runningSum = 0;
//        for (int c = 0; c < COUNT_ITERATIONS; c++) {
//            var i = state.i();
//            var a1 = state.a1.v(i);
//            var a2 = state.a2.v(i);
//
//            runningSum += LIntBinaryOperator.applyAsInt(state.decoupledTwoStage(), a1, a2, FUNCTION_PRIMITIVE);
//        }
//        return runningSum + state.entries + state.exits;
//    }

    @Benchmark @Threads(THREADS) public Object call_CTX_TS_1_as_CC(TheState state) {
        int a = 0;
        var runningSum = 0;
        for (int c = 0; c < COUNT_ITERATIONS; c++) {
            var i = state.i();
            var a1 = state.a1.v(i);
            var a2 = state.a2.v(i);

            runningSum += LBinaryOperator.shovingApply((CallContext) state.CTX_TS_1, a1, a2, FUNCTION);
        }
        return runningSum + state.entries + state.exits;
    }

    @Benchmark @Threads(THREADS) public Object call_CTX_TS_DOUBLE(TheState state) {
        int a = 0;
        var runningSum = 0;
        for (int c = 0; c < COUNT_ITERATIONS; c++) {
            var i = state.i();
            var a1 = state.a1.v(i);
            var a2 = state.a2.v(i);

            runningSum += LBinaryOperator.shovingApply(state.CTX_TS_DOUBLE, a1, a2, FUNCTION);
        }
        return runningSum + state.entries + state.exits;
    }

//    @Benchmark @Threads(THREADS) public Object call_CTX_TS_DOUBLE_primitive(TheState state) {
//        int a = 0;
//        var runningSum = 0;
//        for (int c = 0; c < COUNT_ITERATIONS; c++) {
//            var i = state.i();
//            var a1 = state.a1.v(i);
//            var a2 = state.a2.v(i);
//
//            runningSum += LIntBinaryOperator.applyAsInt(state.CTX_TS_DOUBLE, a1, a2, FUNCTION_PRIMITIVE);
//        }
//        return runningSum + state.entries + state.exits;
//    }

    @Benchmark @Threads(THREADS) public Object call_CTX3_DOUBLE(TheState state) {
        int a = 0;
        var runningSum = 0;
        for (int c = 0; c < COUNT_ITERATIONS; c++) {
            var i = state.i();
            var a1 = state.a1.v(i);
            var a2 = state.a2.v(i);

            runningSum += LBinaryOperator.shovingApply(state.CTX3_DOUBLE, a1, a2, FUNCTION);
        }
        return runningSum + state.entries + state.exits;
    }

    @Benchmark @Threads(THREADS) public Object call_CTX_1(TheState state) {
        int a = 0;
        var runningSum = 0;
        for (int c = 0; c < COUNT_ITERATIONS; c++) {
            var i = state.i();
            var a1 = state.a1.v(i);
            var a2 = state.a2.v(i);
            runningSum += LBinaryOperator.shovingApply(state.CTX_1, a1, a2, FUNCTION);
        }
        return runningSum + state.entries + state.exits;
    }

    @Benchmark @Threads(THREADS) public Object call_CTX_DOUBLE(TheState state) {
        int a = 0;
        var runningSum = 0;
        for (int c = 0; c < COUNT_ITERATIONS; c++) {
            var i = state.i();
            var a1 = state.a1.v(i);
            var a2 = state.a2.v(i);
            runningSum += LBinaryOperator.shovingApply(state.CTX_DOUBLE, a1, a2, FUNCTION);
        }
        return runningSum + state.entries + state.exits;
    }

    @Benchmark @Threads(THREADS) public Object call_CTX4_4(TheState state) {
        int a = 0;
        var runningSum = 0;
        for (int c = 0; c < COUNT_ITERATIONS; c++) {
            var i = state.i();
            var a1 = state.a1.v(i);
            var a2 = state.a2.v(i);
            runningSum += LBinaryOperator.shovingApply(state.CTX4_4, a1, a2, FUNCTION);
        }
        return runningSum + state.entries + state.exits;
    }

    @Benchmark @Threads(THREADS) public Object call_semiRandomCtx(TheState state) { // no optimizations
        int a = 0;
        var runningSum = 0;
        for (int c = 0; c < COUNT_ITERATIONS; c++) {
            var i = state.i();
            var a1 = state.a1.v(i);
            var a2 = state.a2.v(i);
            runningSum += LBinaryOperator.shovingApply(state.decoupledCtx(), a1, a2, FUNCTION);
        }
        return runningSum + state.entries + state.exits;
    }

    @Benchmark @Threads(THREADS) public Object call_adHOcCombine_worst(TheState state) {
        int a = 0;
        var runningSum = 0;

        for (int c = 0; c < COUNT_ITERATIONS; c++) {
            var i = state.i();
            var a1 = state.a1.v(i);
            var a2 = state.a2.v(i);
            runningSum += LBinaryOperator.shovingApply(CallContext.combine(state.CTX_1, state.CTX_2), a1, a2, FUNCTION);
        }
        return runningSum + state.entries + state.exits;
    }

    @Benchmark @Threads(THREADS) public Object call_adHOcCombine_better(TheState state) {
        int a = 0;
        var runningSum = 0;

        CallContext combine = CallContext.combine(state.CTX_1, state.CTX_2);
        for (int c = 0; c < COUNT_ITERATIONS; c++) {
            var i = state.i();
            var a1 = state.a1.v(i);
            var a2 = state.a2.v(i);
            runningSum += LBinaryOperator.shovingApply(combine, a1, a2, FUNCTION);
        }
        return runningSum + state.entries + state.exits;
    }

    public static void main(String... args) {
        JMH.jmh()
           .java10ServerArgs()
//           .iterations(3, seconds(10), 3, seconds(10))
           .iterations(2, seconds(3), 2, seconds(3))
           .classes(CallContext_Perf.class)
           .gc()
           .mode(Mode.Throughput)
           .opt(o -> o.timeUnit(TimeUnit.MILLISECONDS))
           .run();
    }

}
