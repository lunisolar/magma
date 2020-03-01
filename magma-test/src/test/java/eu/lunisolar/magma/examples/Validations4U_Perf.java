/*
 * This file is part of "lunisolar-magma".
 *
 * (C) Copyright 2014-2019 Lunisolar (http://lunisolar.eu/).
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

import eu.lunisolar.magma.basics.exceptions.X;
import eu.lunisolar.magma.func.function.LFunction;
import eu.lunisolar.magma.func.predicate.LPredicate;
import eu.lunisolar.magma.func.supp.Be;
import eu.lunisolar.magma.func.supp.Have;
import eu.lunisolar.magma.func.supp.Is;
import eu.lunisolar.magma.func.supp.P;
import eu.lunisolar.magma.func.supplier.LSupplier;
import eu.lunisolar.magma.test.JMH;
import eu.lunisolar.magma.test.random.Series;
import eu.lunisolar.magma.test.random.SeriesParams;
import org.openjdk.jmh.annotations.*;

import java.util.concurrent.*;

import static eu.lunisolar.magma.func.supp.check.Checks.arg;
import static eu.lunisolar.magma.test.random.Series.series;
import static eu.lunisolar.magma.test.random.SimpleRandoms.aString;
import static org.openjdk.jmh.runner.options.TimeValue.seconds;

/**
 * @author Jakub Wach
 */
@SuppressWarnings({"unused", "unchecked"})
public class Validations4U_Perf {

    private static final int THREADS = 1;

    private static final Number                     INT              = 1;
    public static final  LFunction<Number, Integer> NUM_TO_INT       = Number::intValue;
    public static final  int                        TIMES            = 1;
    public static final  int                        SERIES_SIZE      = 10000;
    public static final  int                        COUNT_ITERATIONS = 10000;

    private static final LSupplier<String> str     = () -> aString(20);
    private static final LSupplier<String> nullStr = () -> null;

    private static SeriesParams<String> params() {
        return SeriesParams.<String>params()
                .size(SERIES_SIZE)
                .poolASize(5)
                .percentageA(39)
                .poolAProducer(str)
                .poolBSize(20)
                .percentageB(60)
                .poolBProducer(str)
                .poolCProducer(str);        // <-- ALT1
//                .poolCProducer(nullStr);    // <-- ALT2
    }

    @State(Scope.Thread)
    public static class TheState {

        static Series<String> names  = series(params().name("names"));
        static Series<String> values = series(params().name("values"));

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

    @Benchmark @Threads(THREADS) public Object lpredicate_throwIf_noVarargs(TheState state) {
        int a = 0;
        for (int c = 0; c < COUNT_ITERATIONS; c++) {
            int i = state.i();
            try {
                LPredicate.throwIf(state.values.v(i), Is::nullOrEmpty, X::arg, "Cannot be empty.");
                a++;
            } catch (RuntimeException e) {
                a--;
            }
        }
        return a;
    }

    @Benchmark @Threads(THREADS) public Object lpredicate_throwIf_varargs(TheState state) {
        int a = 0;
        for (int c = 0; c < COUNT_ITERATIONS; c++) {
            int i = state.i();
            try {
                LPredicate.throwIf(state.values.v(i), Is::nullOrEmpty, X::arg, "Cannot be empty: %s.", state.values.v(i));
                a++;
            } catch (RuntimeException e) {
                a--;
            }
        }
        return a;
    }

    @Benchmark @Threads(THREADS) public Object Validations4U_argumentIsNot(TheState state) {
        int a = 0;
        for (int c = 0; c < COUNT_ITERATIONS; c++) {
            int i = state.i();
            try {
                arg(state.values.v(i)).mustNot(Be::nullOrEmpty, "Cannot be empty.");
                a++;
            } catch (RuntimeException e) {
                a--;
            }
        }
        return a;
    }

    @Benchmark @Threads(THREADS) public Object Validations4U_argumentIsNot2(TheState state) {
        int a = 0;
        for (int c = 0; c < COUNT_ITERATIONS; c++) {
            int i = state.i();
            try {
                arg(state.values.v(i)).mustNot(Be::nullOrEmpty, "Cannot be empty.");
                a++;
            } catch (RuntimeException e) {
                a--;
            }
        }
        return a;
    }

    @Benchmark @Threads(THREADS) public Object VValidations4U_arg_isNot(TheState state) {
        int a = 0;
        for (int c = 0; c < COUNT_ITERATIONS; c++) {
            int i = state.i();
            try {
                arg(state.values.v(i)).mustNot(Be::nullOrEmpty, "Cannot be empty.");
                a++;
            } catch (RuntimeException e) {
                a--;
            }
        }
        return a;
    }

    @Benchmark @Threads(THREADS) public Object VValidations4U_arg_name_isNot(TheState state) {
        int a = 0;
        for (int c = 0; c < COUNT_ITERATIONS; c++) {
            int i = state.i();
            try {
                arg(state.values.v(i), state.names.v(i)).mustNot(Be::nullOrEmpty, "Cannot be empty.");
                a++;
            } catch (RuntimeException e) {
                a--;
            }
        }
        return a;
    }

    @Benchmark @Threads(THREADS) public Object arg2(TheState state) {
        int a = 0;
        for (int c = 0; c < COUNT_ITERATIONS; c++) {
            int i = state.i();
            try {
                arg(state.values.v(i), state.names.v(i)).mustNot(Be::nullOrEmpty, "Cannot be empty.");
                a++;
            } catch (RuntimeException e) {
                a--;
            }
        }
        return a;
    }

    @Benchmark @Threads(THREADS) public Object derived(TheState state) {
        int a = 0;
        for (int c = 0; c < COUNT_ITERATIONS; c++) {
            int i = state.i();
            try {
                arg(state.values.v(i)).mustNot(Be::nullOrEmpty, "Cannot be empty.")
                                      .checkInt(String::length,"der",  v -> v.must(Be::gtEq, 2, "Must be longer than 2"));
                a++;
            } catch (RuntimeException e) {
                a--;
            }
        }
        return a;
    }

     @Benchmark @Threads(THREADS) public Object mustNot$(TheState state) {
        int a = 0;
        for (int c = 0; c < COUNT_ITERATIONS; c++) {
            int i = state.i();
            try {
                arg(state.values.v(i)).mustNot$(Be::equal, "", "Cannot be empty.");
                a++;
            } catch (RuntimeException e) {
                a--;
            }
        }
        return a;
    }

    @Benchmark @Threads(THREADS) public Object mustNot$$(TheState state) {
        int a = 0;
        for (int c = 0; c < COUNT_ITERATIONS; c++) {
            int i = state.i();
            try {
                arg(state.values.v(i)).mustNot$$(Be::equal, "", "Cannot be empty.");
                a++;
            } catch (RuntimeException e) {
                a--;
            }
        }
        return a;
    }

    @Benchmark @Threads(THREADS) public Object checkIf_ref(TheState state) {
        int a = 0;
        for (int c = 0; c < COUNT_ITERATIONS; c++) {
            int i = state.i();
            try {
                var s = state.values.v(i);
                if (s!= null ) {
                    arg(state.values.v(i)).mustNot$$(Be::equal, "", "Cannot be empty.");
                }

                a++;
            } catch (RuntimeException e) {
                a--;
            }
        }
        return a;
    }

    @Benchmark @Threads(THREADS) public Object checkIf(TheState state) {
        int a = 0;
        for (int c = 0; c < COUNT_ITERATIONS; c++) {
            int i = state.i();
            try {
                arg(state.values.v(i)).checkWhen(Is::notNull, __ -> __.mustNot$$(Be::equal, "", "Cannot be empty."));
                a++;
            } catch (RuntimeException e) {
                a--;
            }
        }
        return a;
    }

    @Benchmark @Threads(THREADS) public Object checkIf2(TheState state) {
        int a = 0;
        for (int c = 0; c < COUNT_ITERATIONS; c++) {
            int i = state.i();
            try {
                arg(state.values.v(i), "data").checkWhen(Is::notNull, __ -> __.mustNot$$(Be::equal, "conditional check", "Cannot be empty."));
                a++;
            } catch (RuntimeException e) {
                a--;
            }
        }
        return a;
    }

    @Benchmark @Threads(THREADS) public Object Have_have(TheState state) {
        int a = 0;
        for (int c = 0; c < COUNT_ITERATIONS; c++) {
            int i = state.i();
            try {
                arg(state.values.v(i)).must(P.have(String::length, P::ltEq, 32*state.names.v(i).length()), "must have specific length");
                a++;
            } catch (RuntimeException e) {
                a--;
            }
        }
        return a;
    }

    public static void main(String... args) {
        JMH.jmh()
           .java10ServerArgs()
//           .opt(opt -> {
//               opt.jvmArgs(
//                       "-server",
//                       "-XX:+UnlockDiagnosticVMOptions",
//                       "-XX:MaxInlineLevel=60",
//                       "-XX:MaxInlineSize=2000",
//                       "-XX:+PrintInlining"
//               );
//           })
//           .iterations(3, seconds(10), 3, seconds(10))
           .iterations(2, seconds(3), 2, seconds(3))
           .classes(Validations4U_Perf.class)
           .gc()
           .mode(Mode.Throughput)
           .opt(o -> o.timeUnit(TimeUnit.MILLISECONDS))
           .run();
    }

}
