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

import eu.lunisolar.magma.func.function.LFunction;
import eu.lunisolar.magma.func.supp.P;
import eu.lunisolar.magma.func.supp.opt.Opt;
import eu.lunisolar.magma.func.supp.opt.OptInt;
import eu.lunisolar.magma.func.supp.opt.OptTrait;
import eu.lunisolar.magma.func.supplier.LSupplier;
import eu.lunisolar.magma.test.JMH;
import eu.lunisolar.magma.test.random.Series;
import eu.lunisolar.magma.test.random.SeriesParams;
import org.openjdk.jmh.annotations.*;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.*;

import static eu.lunisolar.magma.test.random.Series.series;
import static eu.lunisolar.magma.test.random.SimpleRandoms.anInt;
import static org.openjdk.jmh.runner.options.TimeValue.seconds;

/**
 * @author Jakub Wach
 */
@SuppressWarnings({"unused", "unchecked"})
public class Optionals_Perf {

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

    private static SeriesParams<Integer> params2() {
        return SeriesParams.<Integer>params()
                       .size(SERIES_SIZE)
                       .poolASize(5)
                       .percentageA(39)
                       .poolAProducer(int1.toSup(Opt::of))
                       .poolBSize(20)
                       .percentageB(60)
                       .poolBProducer(int1.toSup(Opt::of))
                       .poolCProducer(int1.toSup(Opt::of));        // <-- ALT1
//                .poolCProducer(nullStr);    // <-- ALT2
    }

    @State(Scope.Thread)
    public static class TheState  {

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

        public Opt<Integer> value(int i) {
            return Opt.of(values.v(i));
        }

    }

    @State(Scope.Thread)
    public static class TheState2 {

        static Series<Integer> names  = series(params().name("names"));
        static Series<Opt<Integer>> values = series(params2().name("values"));

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


    @Benchmark @Threads(THREADS) public Object obj_equals_Opt(TheState state) {
        int a = 0;
        for (int c = 0; c < COUNT_ITERATIONS; c++) {
            int i = state.i();
            a += Opt.of(state.values.v(i))
                    .is(P::equal, state.names.v(i)) ? 1 : 0;
        }
        return a;
    }

    @Benchmark @Threads(THREADS) public Object obj_equals_Opt_2_(TheState2 state) {
        int a = 0;
        for (int c = 0; c < COUNT_ITERATIONS; c++) {
            int i = state.i();
            a += Opt.of(state.values.v(i))
                    .is(P::equal, state.names.v(i)) ? 1 : 0;
        }
        return a;
    }

    @Benchmark @Threads(THREADS) public Object obj_equals_Opt_2_B(TheState2 state) {
        int a = 0;
        AtomicReference<Opt<Integer>> AA = new AtomicReference(null);
        for (int c = 0; c < COUNT_ITERATIONS; c++) {
            var FF = c;
            int i = state.i();

            a += state.values.v(i)
                    .is(__ -> P.equal(__, state.names.v(i))) ? 1 : 0;
        }
        return a;
    }

     @Benchmark @Threads(THREADS) public Object obj_equals_Opt_2_B2(TheState2 state) {
        int a = 0;

        for (int c = 0; c < COUNT_ITERATIONS; c++) {
            var FF = c;
            int i = state.i();
            a += state.values.v(i)
                    .is(__ -> aBoolean(state, FF, i, __)) ? 1 : 0;
        }
        return a;
    }

    public static boolean aBoolean(TheState2 state, int FF, int i, Integer __) {
        return (FF%2>0) ||  P.equal(__, state.names.v(i));
    }

//    @Benchmark @Threads(THREADS)
    public Object obj_equals_Opt2(TheState state) {
        int a = 0;
        for (int c = 0; c < COUNT_ITERATIONS; c++) {
            int i = state.i();
            a += Opt.of(state.values.v(i))
                    .is(s -> s.equals(state.names.v(i))) ? 1 : 0;
        }
        return a;
    }

//    @Benchmark @Threads(THREADS)
    public Object obj_equals_Opt3(TheState state) {
        int a = 0;
        for (int c = 0; c < COUNT_ITERATIONS; c++) {
            int i = state.i();
            a += Opt.of(state.values.v(i))
                    .is(Integer::equals, state.names.v(i)) ? 1 : 0;
        }
        return a;
    }

//    @Benchmark @Threads(THREADS)
    public Object obj_equals_OptInt(TheState state) {
        int a = 0;
        for (int c = 0; c < COUNT_ITERATIONS; c++) {
            int i = state.i();
            a += OptInt.of(state.values.v(i))
                       .is(P::equal, state.names.v(i)) ? 1 : 0;
        }
        return a;
    }

//    @Benchmark @Threads(THREADS)
    public Object obj_equals_OptInt2(TheState state) {
        int a = 0;
        for (int c = 0; c < COUNT_ITERATIONS; c++) {
            int i = state.i();
            a += OptInt.of(state.values.v(i))
                       .is((i1, i2) -> i1 == i2, state.names.v(i)) ? 1 : 0;
        }
        return a;
    }

//    @Benchmark @Threads(THREADS)
    public Object obj_equals_Optional(TheState state) {
        int a = 0;
        for (int c = 0; c < COUNT_ITERATIONS; c++) {
            int i = state.i();
            a += Optional.ofNullable(state.values.v(i))
                         .filter(s -> Objects.equals(s, state.names.v(i)))
                         .isPresent() ? 1 : 0;
        }
        return a;
    }

//    @Benchmark @Threads(THREADS)
    public Object obj_equals_ref(TheState state) {
        int a = 0;
        for (int c = 0; c < COUNT_ITERATIONS; c++) {
            int i = state.i();
            a += Objects.equals(state.values.v(i), state.names.v(i)) ? 1 : 0;
        }
        return a;
    }

//    @Benchmark @Threads(THREADS)
    public Object obj_equals_ref2(TheState state) {
        int a = 0;
        for (int c = 0; c < COUNT_ITERATIONS; c++) {
            int i = state.i();
            a += state.values.v(i).equals(state.names.v(i)) ? 1 : 0;
        }
        return a;
    }

//    @Benchmark @Threads(THREADS)
    public Object obj_equals_ref3(TheState state) {
        int a = 0;
        for (int c = 0; c < COUNT_ITERATIONS; c++) {
            int i = state.i();

            int v1 = state.values.v(i);
            int v2 = state.names.v(i);
            a += v1 == v2 ? 1 : 0;
        }
        return a;
    }

    public static class OtherOpt<T> implements OptTrait<T, OtherOpt<T>> {

        private final T value;

        public OtherOpt(T value) {
            this.value = value;
        }

        @Nullable @Override public T value() {
            return value;
        }

        @Nonnull @Override public OtherOpt<T> value(@Nullable T t) {
            return new OtherOpt<>(value);
        }
        @Nonnull @Override public OtherOpt<T> voidValue() {
            return new OtherOpt<>(null);
        }
        @Nonnull @Override public T nullable() {
            return value;
        }
    }

//    @Benchmark @Threads(THREADS)
    public Object obj_fromOpt_BCS(TheState state) {
        int a = 0;
        for (int c = 0; c < COUNT_ITERATIONS; c++) {
            int i = state.i();
            a += Opt.of(state.values.v(i)).valueFrom(Opt.of(state.values.v(i)))
                    .is(P::equal, state.names.v(i)) ? 1 : 0;
        }
        return a;
    }

//    @Benchmark @Threads(THREADS)
    public Object obj_fromOpt_WCS(TheState state) {
        int a = 0;
        for (int c = 0; c < COUNT_ITERATIONS; c++) {
            int i = state.i();
            a += Opt.of(state.values.v(i)).valueFrom(new OtherOpt<>(state.values.v(i)))
                    .is(P::equal, state.names.v(i)) ? 1 : 0;
        }
        return a;
    }

    public static void main(String... args) {
        JMH.jmh()
           .java10ServerArgs()
//           .iterations(3, seconds(10), 3, seconds(10))
           .iterations(2, seconds(3), 2, seconds(3))
           .classes(Optionals_Perf.class)
           .gc()
           .mode(Mode.Throughput)
           .opt(o -> o.timeUnit(TimeUnit.MILLISECONDS))
           .run();
    }

}
