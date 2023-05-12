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

package eu.lunisolar.magma.examples;

import eu.lunisolar.magma.basics.exceptions.Handler;
import eu.lunisolar.magma.func.function.LFunction;
import eu.lunisolar.magma.func.operator.unary.LIntUnaryOperator;
import eu.lunisolar.magma.func.supplier.LSupplier;
import eu.lunisolar.magma.test.JMH;
import eu.lunisolar.magma.test.random.Series;
import eu.lunisolar.magma.test.random.SeriesParams;
import org.openjdk.jmh.annotations.*;

import java.util.concurrent.*;
import java.util.function.*;

import static eu.lunisolar.magma.test.random.Series.series;
import static eu.lunisolar.magma.test.random.SimpleRandoms.anInt;
import static org.openjdk.jmh.runner.options.TimeValue.seconds;

/**
 * @author Jakub Wach
 */
@SuppressWarnings({"unused", "unchecked"})
public class LFunctionsHandling_Perf {

    private static final int THREADS = 1;

    private static final Number                     INT         = 1;
    public static final  LFunction<Number, Integer> NUM_TO_INT  = Number::intValue;
    public static final  int                        TIMES       = 1;
    public static final  int                        SERIES_SIZE = 1000;

    private static final LSupplier<Integer> int1             = () -> anInt(4000);
    private static final LSupplier<Integer> int2             = () -> anInt(100000);
    public static final  int                COUNT_ITERATIONS = 10000;

    private static SeriesParams<Integer> params() {
        return SeriesParams.<Integer>params()
                .size(SERIES_SIZE)
                .poolASize(5)
                .percentageA(39)
                .poolAProducer(int1)
                .poolBSize(20)
                .percentageB(60)
                .poolBProducer(int1)
                .poolCProducer(int1);        // <-- ALT1
//                .poolCProducer(nullStr);    // <-- ALT2
    }

    @State(Scope.Thread)
    public static class TheState {

        static Series<Integer> names  = series(params().name("names"));
        static Series<Integer> values = series(params().name("values"));

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

    public static class MyException extends RuntimeException {
        public MyException() { }
    }

    public static final MyException EXCEPTION = new MyException();

    public static LIntUnaryOperator FUNC = i -> i++;
    public static IntUnaryOperator  REF  = i -> i++;

    public static LIntUnaryOperator THROWING = i -> {throw EXCEPTION;};

    @Benchmark @Threads(THREADS) public Object pureFunc(TheState state) {
        int a = 0;
        for (int c = 0; c < COUNT_ITERATIONS; c++) {
            int i = state.i();
            a += FUNC.applyAsInt(i);
        }
        return a;
    }

    @Benchmark @Threads(THREADS) public Object throwing(TheState state) {
        int a = 0;
        for (int c = 0; c < COUNT_ITERATIONS; c++) {
            int i = state.i();
            try {
                a += THROWING.applyAsInt(i);
            } catch (MyException e) {
                a += 1;
            }
        }
        return a;
    }

    @Benchmark @Threads(THREADS) public Object handling(TheState state) {
        int a = 0;
        for (int c = 0; c < COUNT_ITERATIONS; c++) {
            int i = state.i();
            try {
                a += THROWING.handlingApplyAsInt(i, Handler::handleRest);
            } catch (MyException e) {
                a += 1;
            }
        }
        return a;
    }

    @Benchmark @Threads(THREADS) public Object ref_pureFunc(TheState state) {
        int a = 0;
        for (int c = 0; c < COUNT_ITERATIONS; c++) {
            int i = state.i();
            a += REF.applyAsInt(i);
        }
        return a;
    }

    public static void main(String... args) {
        JMH.jmh()
           .java10ServerArgs()
//           .iterations(3, seconds(10), 3, seconds(10))
           .iterations(2, seconds(3), 2, seconds(3))
           .classes(LFunctionsHandling_Perf.class)
           .gc()
           .mode(Mode.Throughput)
           .opt(o -> o.timeUnit(TimeUnit.MILLISECONDS))
           .run();
    }

}
