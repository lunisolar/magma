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

import eu.lunisolar.magma.func.supp.lazy.Lazy;
import eu.lunisolar.magma.func.supp.lazy.LazyInt;
import eu.lunisolar.magma.func.supp.value.LInteger;
import eu.lunisolar.magma.test.JMH;
import org.openjdk.jmh.annotations.*;

import java.util.concurrent.*;
import java.util.concurrent.atomic.*;

import static eu.lunisolar.magma.test.random.Series.series;
import static eu.lunisolar.magma.test.random.SimpleRandoms.anInt;
import static org.openjdk.jmh.runner.options.TimeValue.seconds;

/**
 * @author Jakub Wach
 */
@SuppressWarnings({"unused", "unchecked"})
public class Lazy_Perf {

    private static final int THREADS = 4;

    public static final int COUNT_ITERATIONS = 10000;

    @State(Scope.Benchmark)
    public static class TheState {

        static final int     intStatic       = 45;
        static final Integer integerStatic   = 45;
        final        int     intInstance     = 45;
        final        Integer integerInstance = 45;
        int     intVariable     = 45;
        Integer integerVariable = 45;
        static final LInteger      lInteger  = LInteger.intValue(45);
        static final LazyInt       lazyInt   = LazyInt.lazyValue(() -> 45);
        static final Lazy<Integer> lazy      = Lazy.lazyValue(() -> 45);
        static final AtomicInteger atomicInt = new AtomicInteger(45);

        @Setup
        public void setup() {
        }

        @TearDown
        public void after() {
        }

    }

    public static class MyException extends RuntimeException {
        public MyException() {}
    }

    public static final MyException EXCEPTION = new MyException();

    @Benchmark @Threads(THREADS) public int lazy(TheState state) {
        int a = 0;
        for (int c = 0; c < COUNT_ITERATIONS; c++) {
            a += state.lazy.get();
        }
        return a;
    }

    @Benchmark @Threads(THREADS) public int lazyInt(TheState state) {
        int a = 0;
        for (int c = 0; c < COUNT_ITERATIONS; c++) {
            a += state.lazyInt.getAsInt();
        }
        return a;
    }

    @Benchmark @Threads(THREADS) public int lInteger(TheState state) {
        int a = 0;
        for (int c = 0; c < COUNT_ITERATIONS; c++) {
            a += state.lInteger.get();
        }
        return a;
    }

    @Benchmark @Threads(THREADS) public int atomicInt(TheState state) {
        int a = 0;
        for (int c = 0; c < COUNT_ITERATIONS; c++) {
            a += state.atomicInt.get();
        }
        return a;
    }

    @Benchmark @Threads(THREADS) public int _integerStatic(TheState state) {
        int a = 0;
        for (int c = 0; c < COUNT_ITERATIONS; c++) {
            a += state.integerStatic;
        }
        return a;
    }

    @Benchmark @Threads(THREADS) public int _intStatic(TheState state) {
        int a = 0;
        for (int c = 0; c < COUNT_ITERATIONS; c++) {
            a += state.intStatic;
        }
        return a;
    }

    @Benchmark @Threads(THREADS) public int _integerInstance(TheState state) {
        int a = 0;
        for (int c = 0; c < COUNT_ITERATIONS; c++) {
            a += state.integerInstance;
        }
        return a;
    }

    @Benchmark @Threads(THREADS) public int _intInstance(TheState state) {
        int a = 0;
        for (int c = 0; c < COUNT_ITERATIONS; c++) {
            a += state.intInstance;
        }
        return a;
    }

    @Benchmark @Threads(THREADS) public int _integerVariable(TheState state) {
        int a = 0;
        for (int c = 0; c < COUNT_ITERATIONS; c++) {
            a += state.integerVariable;
        }
        return a;
    }

    @Benchmark @Threads(THREADS) public int _intVariable(TheState state) {
        int a = 0;
        for (int c = 0; c < COUNT_ITERATIONS; c++) {
            a += state.intVariable;
        }
        return a;
    }

    public static void main(String... args) {
        JMH.jmh()
//           .opt(o -> o.forks(0))
           .java10ServerArgs()
//           .iterations(3, seconds(10), 3, seconds(10))
           .iterations(2, seconds(5), 2, seconds(5))
           .classes(Lazy_Perf.class)
           .gc()
           .mode(Mode.Throughput)
           .opt(o -> o.timeUnit(TimeUnit.MILLISECONDS))
           .run();
    }

}
