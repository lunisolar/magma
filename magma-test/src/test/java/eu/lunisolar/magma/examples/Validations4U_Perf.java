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
import eu.lunisolar.magma.func.predicate.LBiPredicate;
import eu.lunisolar.magma.func.predicate.LPredicate;
import eu.lunisolar.magma.func.supp.Be;
import eu.lunisolar.magma.func.supp.Is;
import eu.lunisolar.magma.func.supp.P;
import eu.lunisolar.magma.func.supp.opt.Opt;
import eu.lunisolar.magma.func.supplier.LSupplier;
import eu.lunisolar.magma.test.JMH;
import eu.lunisolar.magma.test.random.Series;
import eu.lunisolar.magma.test.random.SeriesParams;
import org.openjdk.jmh.annotations.*;

import javax.annotation.Nullable;
import java.util.concurrent.*;

import static eu.lunisolar.magma.func.supp.MsgVerbosity.*;
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

    public static String SAME = "";

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

        static Series<String> names  = series(params().name("names"), SAME);
        static Series<String> values = series(params().name("values"), SAME);

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

    @Benchmark @Threads(THREADS) public Object lpredicate_throwIf_msg_from_params(TheState state) {
        int a = 0;
        for (int c = 0; c < COUNT_ITERATIONS; c++) {
            int i = state.i();
            try {
                LPredicate.throwIf(state.values.v(i), Is::nullOrEmpty, X::arg, "Cannot be empty: %s.");
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

    @Benchmark @Threads(THREADS) public Object throwIf_customMsg_bad(TheState state) {
        int a = 0;
        for (int c = 0; c < COUNT_ITERATIONS; c++) {
            int i = state.i();
            try {
                LPredicate.throwIf(state.values.v(i),  Be::nullOrEmpty, X::arg, (o1)-> { return ""+ state.values.v(i).getClass();});
                a++;
            } catch (RuntimeException e) {
                a--;
            }
        }
        return a;
    }

    @Benchmark @Threads(THREADS) public Object arg2_customMsg(TheState state) {
        int a = 0;
        for (int c = 0; c < COUNT_ITERATIONS; c++) {
            int i = state.i();
            try {
                arg(state.values.v(i), state.names.v(i)).mustNot(Be::nullOrEmpty, "Cannot be empty index: %s", i);
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
                                      .checkInt(String::length,"der",  v -> v.must2(Be::gtEq, 2, "Must be longer than 2"));
                a++;
            } catch (RuntimeException e) {
                a--;
            }
        }
        return a;
    }

    @Benchmark @Threads(THREADS) public Object mustNotEx_REF0(TheState state) {
        int a = 0;
        for (int c = 0; c < COUNT_ITERATIONS; c++) {
            int i = state.i();
            try {
                if (state.values.v(i).equals("")) {
                    throw new RuntimeException("Cannot be empty.");
                }
                a++;
            } catch (RuntimeException e) {
                a--;
            }
        }
        return a;
    }

    @Benchmark @Threads(THREADS) public Object mustNotEx_REF_______0(TheState state) {
        int a = 0;
        for (int c = 0; c < COUNT_ITERATIONS; c++) {
            int i = state.i();
            try {
                arg(state.values.v(i)).mustNot2(P::equal, "", "No no!");
                a++;
            } catch (RuntimeException e) {
                a--;
            }
        }
        return a;
    }

    @Benchmark @Threads(THREADS) public Object mustNotEx_REF_______01(TheState state) {
        int a = 0;
        for (int c = 0; c < COUNT_ITERATIONS; c++) {
            int i = state.i();
            try {
                arg(state.values.v(i)).fluentUse(__ -> __.mustNot2(P::equal, "", "No no!"));
                a++;
            } catch (RuntimeException e) {
                a--;
            }
        }
        return a;
    }

    @Benchmark @Threads(THREADS) public Object mustNotEx_REF_______1(TheState state) {
        int a = 0;
        for (int c = 0; c < COUNT_ITERATIONS; c++) {
            int i = state.i();
            try {
                arg(state.values.v(i)).verbosity(ALL).mustNot2(P::equal, "","No no!");
                a++;
            } catch (RuntimeException e) {
                a--;
            }
        }
        return a;
    }

    @Benchmark @Threads(THREADS) public Object mustNotEx_REF1(TheState state) {
        int a = 0;
        for (int c = 0; c < COUNT_ITERATIONS; c++) {
            int i = state.i();
            try {
                String v = state.values.v(i);
                if (v.equals("")) {
                    throw new RuntimeException("Cannot be empty: '" + v + "'");
                }
                a++;
            } catch (RuntimeException e) {
                a--;
            }
        }
        return a;
    }

    @Benchmark @Threads(THREADS) public Object mustNotEx_throwIf_REF(TheState state) {
        int a = 0;
        for (int c = 0; c < COUNT_ITERATIONS; c++) {
            int i = state.i();
            try {
                String v = state.values.v(i);
                LBiPredicate.throwIf(v, P::equal, "", X::arg, "Cannot be empty");
                a++;
            } catch (RuntimeException e) {
                a--;
            }
        }
        return a;
    }

    @Benchmark @Threads(THREADS) public Object mustNotEx_throwIf_REF1(TheState state) {
        int a = 0;
        for (int c = 0; c < COUNT_ITERATIONS; c++) {
            int i = state.i();
            try {
                String v = state.values.v(i);
                LBiPredicate.throwIf(v, P::equal, "", X::arg,"Cannot be empty: %s");
                a++;
            } catch (RuntimeException e) {
                a--;
            }
        }
        return a;
    }

//    @Benchmark @Threads(THREADS) public Object mustNotEx_throwIf_REF_MsgFunc(TheState state) {
//        int a = 0;
//        for (int c = 0; c < COUNT_ITERATIONS; c++) {
//            int i = state.i();
//            try {
//                String v = state.values.v(i);
//                LBiPredicate.throwIf(v, P::equal, "", X::arg,(__, p)-> String.format("Cannot be empty: %s", __));
//                a++;
//            } catch (RuntimeException e) {
//                a--;
//            }
//        }
//        return a;
//    }

//    @Benchmark @Threads(THREADS) public Object mustNotEx_throwIf_REF_MsgFunc_Better(TheState state) {
//        int a = 0;
//        for (int c = 0; c < COUNT_ITERATIONS; c++) {
//            int i = state.i();
//            try {
//                String v = state.values.v(i);
//                LBiPredicate.throwIf(v, P::equal, "", X::arg,(__, p)-> new StringBuilder().append("Cannot be empty: ").append(__).toString());
//                a++;
//            } catch (RuntimeException e) {
//                a--;
//            }
//        }
//        return a;
//    }

//    @Benchmark @Threads(THREADS) public Object mustNotEx_throwIf_REF_MsgFunc_BAD(TheState state) {
//        int a = 0;
//        for (int c = 0; c < COUNT_ITERATIONS; c++) {
//            int i = state.i();
//            try {
//                String v = state.values.v(i);
//                LBiPredicate.throwIf(v, P::equal, "", X::arg,(__, p)-> String.format("Cannot be empty: %s", v));
//                a++;
//            } catch (RuntimeException e) {
//                a--;
//            }
//        }
//        return a;
//    }

    //
    @Benchmark @Threads(THREADS) public Object mustNot(TheState state) {
        int a = 0;
        for (int c = 0; c < COUNT_ITERATIONS; c++) {
            int i = state.i();
            try {
                arg(state.values.v(i)).mustNot2(Be::equal, "", "Cannot be empty.");
                a++;
            } catch (RuntimeException e) {
                a--;
            }
        }
        return a;
    }

    @Benchmark @Threads(THREADS) public Object mustNotExEx(TheState state) {
        int a = 0;
        for (int c = 0; c < COUNT_ITERATIONS; c++) {
            int i = state.i();
            try {
                arg(state.values.v(i)).verbosity(ALL).mustNot2(Be::equal, "", "Cannot be empty.");
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
                    arg(state.values.v(i)).verbosity(ALL).mustNot2(Be::equal, "", "Cannot be empty.");
                }

                a++;
            } catch (RuntimeException e) {
                a--;
            }
        }
        return a;
    }

    @Benchmark @Threads(THREADS) public Object notEqualEx_arg(TheState state) {
        int a = 0;
        for (int c = 0; c < COUNT_ITERATIONS; c++) {
            int i = state.i();
            try {
//                var s = state.values.v(i);
//                if (s!= null ) {
                    arg(state.values.v(i)).must2Ex(Be::notEqualEx, "");
//                }

                a++;
            } catch (RuntimeException e) {
                a--;
            }
        }
        return a;
    }

//     @Benchmark @Threads(THREADS) public Object notEqualEx_arg_customMsg(TheState state) {
//        int a = 0;
//        for (int c = 0; c < COUNT_ITERATIONS; c++) {
//            int i = state.i();
//            try {
////                var s = state.values.v(i);
////                if (s!= null ) {
//                String v = state.values.v(i);
//                arg(v).must_(Be::notEqualEx, "", "Special comment: %s", v);
////                }
//
//                a++;
//            } catch (RuntimeException e) {
//                a--;
//            }
//        }
//        return a;
//    }

    @Benchmark @Threads(THREADS) public Object notEqualEx_arg_customMsg_verbosity2(TheState state) {
        int a = 0;
        for (int c = 0; c < COUNT_ITERATIONS; c++) {
            int i = state.i();
            try {
//                var s = state.values.v(i);
//                if (s!= null ) {
                String v = state.values.v(i);
                arg(v).must2Ex(Be::notEqualEx, "", "Special comment: %s");
//                }

                a++;
            } catch (RuntimeException e) {
                a--;
            }
        }
        return a;
    }

     @Benchmark @Threads(THREADS) public Object notEqualEx_throwIfNot(TheState state) {
        int a = 0;
        for (int c = 0; c < COUNT_ITERATIONS; c++) {
            int i = state.i();
            try {
//                var s = state.values.v(i);
//                if (s!= null ) {
                    LBiPredicate.throwIfNotEx(state.values.v(i), "", Be::notEqualEx, X::arg);
//                }throwIfNot

                a++;
            } catch (RuntimeException e) {
                a--;
            }
        }
        return a;
    }

    @Benchmark @Threads(THREADS) public Object notEqualEx_throwIfNotExEx(TheState state) {
        int a = 0;
        for (int c = 0; c < COUNT_ITERATIONS; c++) {
            int i = state.i();
            try {
//                var s = state.values.v(i);
//                if (s!= null ) {
                    LBiPredicate.throwIfNotEx(state.values.v(i), "", Be::notEqualEx, X::arg, "Message: %s");
//                }throwIfNot

                a++;
            } catch (RuntimeException e) {
                a--;
            }
        }
        return a;
    }

    @Benchmark @Threads(THREADS) public Object notEqualEx_mustExEx(TheState state) {
        int a = 0;
        for (int c = 0; c < COUNT_ITERATIONS; c++) {
            int i = state.i();
            try {
//                var s = state.values.v(i);
//                if (s!= null ) {
                arg(state.values.v(i)).must2Ex(Be::notEqualEx, ALL,"");
//                }throwIfNot

                a++;
            } catch (RuntimeException e) {
                a--;
            }
        }
        return a;
    }

     @Benchmark @Threads(THREADS) public Object equal(TheState state) {
        int a = 0;
        for (int c = 0; c < COUNT_ITERATIONS; c++) {
            int i = state.i();
            try {
//                var s = state.values.v(i);
//                if (s!= null ) {
                    arg(state.values.v(i)).must2(Be::notEqual, "", "MUST");
//                }

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
                arg(state.values.v(i)).checkWhen(Is::notNull, __ -> __.mustNot2(Be::equal, "", "Cannot be empty."));
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
                arg(state.values.v(i), "data").checkWhen(Is::notNull, __ -> __.mustNot2(Be::equal, "conditional check", "Cannot be empty."));
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
                arg(state.values.v(i)).mustNot(P.have(Object::toString, P::same, SAME), "must have specific length");
                a++;
            } catch (RuntimeException e) {
                a--;
            }
        }
        return a;
    }


    @Benchmark @Threads(THREADS) public Object verbosity_0(TheState state) {
        int a = 0;
        for (int c = 0; c < COUNT_ITERATIONS; c++) {
            int i = state.i();
            try {
                arg(state.values.v(i), state.names.v(i)).must4(PP::notSame4, SAME, "a", "b", "Cannot be empty index");
                a++;
            } catch (RuntimeException e) {
                a--;
            }
        }
        return a;
    }

    @Benchmark @Threads(THREADS) public Object verbosity_1(TheState state) {
        int a = 0;
        for (int c = 0; c < COUNT_ITERATIONS; c++) {
            int i = state.i();
            try {
                arg(state.values.v(i), state.names.v(i)).must4(PP::notSame4, SAME, "a", "b", "Cannot be empty index");
                a++;
            } catch (RuntimeException e) {
                a--;
            }
        }
        return a;
    }

    @Benchmark @Threads(THREADS) public Object verbosity_2(TheState state) {
        int a = 0;
        for (int c = 0; c < COUNT_ITERATIONS; c++) {
            int i = state.i();
            try {
                arg(state.values.v(i), state.names.v(i)).must4(PP::notSame4, SAME, "a", "b", "Cannot be empty index");
                a++;
            } catch (RuntimeException e) {
                a--;
            }
        }
        return a;
    }

    @Benchmark @Threads(THREADS) public Object verbosity_d0(TheState state) {
        int a = 0;
        for (int c = 0; c < COUNT_ITERATIONS; c++) {
            int i = state.i();
            try {
                arg(state.values.v(i), state.names.v(i)).verbosity(MIN).must4(PP::notSame4, SAME, "a", "b", "Cannot be empty index");
                a++;
            } catch (RuntimeException e) {
                a--;
            }
        }
        return a;
    }

    @Benchmark @Threads(THREADS) public Object verbosity_d1(TheState state) {
        int a = 0;
        for (int c = 0; c < COUNT_ITERATIONS; c++) {
            int i = state.i();
            try {
                arg(state.values.v(i), state.names.v(i)).verbosity(VAL).must4(PP::notSame4, SAME, "a", "b", "Cannot be empty index");
                a++;
            } catch (RuntimeException e) {
                a--;
            }
        }
        return a;
    }

    @Benchmark @Threads(THREADS) public Object verbosity_d2(TheState state) {
        int a = 0;
        for (int c = 0; c < COUNT_ITERATIONS; c++) {
            int i = state.i();
            try {
                arg(state.values.v(i), state.names.v(i)).verbosity(ALL).must4(PP::notSame4, SAME, "a", "b", "Cannot be empty index");
                a++;
            } catch (RuntimeException e) {
                a--;
            }
        }
        return a;
    }

    @Benchmark @Threads(THREADS) public Object verbosity_MAX_MAX(TheState state) {
        int a = 0;
        for (int c = 0; c < COUNT_ITERATIONS; c++) {
            int i = state.i();
            try {
                arg(state.values.v(i), state.names.v(i), X::state).verbosity(ALL).must4Ex(PP::notSame4Ex, SAME, "a", "b", "Cannot be empty index %s, %s, %s", SAME, "a", "b");
                a++;
            } catch (RuntimeException e) {
                a--;
            }
        }
        return a;
    }

     @Benchmark @Threads(THREADS) public Object verbosity_d2_customMsg(TheState state) {
        int a = 0;
        for (int c = 0; c < COUNT_ITERATIONS; c++) {
            int i = state.i();
            try {
                arg(state.values.v(i), state.names.v(i)).verbose().must4(PP::notSame4, SAME, "a", "b", "Cannot be empty index %s", "3465");
                a++;
            } catch (RuntimeException e) {
                a--;
            }
        }
        return a;
    }

//    @Benchmark @Threads(THREADS) public Object ToStr_toStr(TheState state) {
//        int a = 0;
//        for (int c = 0; c < COUNT_ITERATIONS; c++) {
//            int i = state.i();
//            try {
//                a += ToStr.toStr(state.values.v(i)).length();
//            } catch (RuntimeException e) {
//                a--;
//            }
//        }
//        return a;
//    }
//
//    @Benchmark @Threads(THREADS) public Object ToStr_toStr_ref0(TheState state) {
//        int a = 0;
//        for (int c = 0; c < COUNT_ITERATIONS; c++) {
//            int i = state.i();
//            try {
//                String result;
//                Object o = state.values.v(i);
//                if (o == null) {
//                    result = ToStr.NULL_REPRESENTATION;
//                } else {
//                    StringBuilder result1;
//                    StringBuilder sb = new StringBuilder();
//                    if (o == null) {
//                        result1 = sb.append(ToStr.NULL_REPRESENTATION);
//                    } else {result1 = sb.append('\'').append(o).append('\'').append("^^").append(o.getClass().getSimpleName());}
//                    result = result1.toString();
//                }
//
//                a += result.length();
//            } catch (RuntimeException e) {
//                a--;
//            }
//        }
//        return a;
//    }
//
//    @Benchmark @Threads(THREADS) public Object ToStr_toStr_ref1(TheState state) {
//        int a = 0;
//        for (int c = 0; c < COUNT_ITERATIONS; c++) {
//            int i = state.i();
//            try {
//                Object o = state.values.v(i);
//                String result = String.format("'%s'^^", o, o==null? "?" : o.getClass());
//                a += result.length();
//            } catch (RuntimeException e) {
//                a--;
//            }
//        }
//        return a;
//    }

     @Benchmark @Threads(THREADS) public Object ToStr_toStr_ref1(TheState state) {
        int a = 0;
        for (int c = 0; c < COUNT_ITERATIONS; c++) {
            int i = state.i();
            try {
                String v = state.values.v(i);
                a += Opt.of(v).orThrow(X::arg, "%s", v.length()).get().length();
            } catch (RuntimeException e) {
                a--;
            }
        }
        return a;
    }

    public static class PP {

        public static boolean notSame4(@Nullable Object n, @Nullable Object other1, @Nullable Object other2, @Nullable Object other3) {
            return !(n == other1) && !(n == other2) && !(n == other3);
        }

        public static String notSame4Ex(@Nullable Object n, @Nullable Object other1, @Nullable Object other2, @Nullable Object other3) {
            if (!notSame4(n, other1, other2, other3 )) {
                return String.format("<%s> must be different from <%s>, <%s> and <%s>", other1, other2, other3);
            };
            return null;
        }

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
