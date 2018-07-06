/*
 * This file is part of "lunisolar-magma".
 *
 * (C) Copyright 2014-2016 Lunisolar (http://lunisolar.eu/).
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

import eu.lunisolar.magma.func.function.from.LIntFunction;
import eu.lunisolar.magma.func.function.to.LToIntFunction;
import eu.lunisolar.magma.func.operator.unary.LIntUnaryOperator;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.profile.GCProfiler;
import org.openjdk.jmh.profile.HotspotMemoryProfiler;
import org.openjdk.jmh.results.format.ResultFormatType;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.ChainedOptionsBuilder;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.openjdk.jmh.runner.options.VerboseMode;

import javax.annotation.Nonnull;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;

import static eu.lunisolar.magma.func.function.from.LIntFunction.intFuncThrowing;
import static eu.lunisolar.magma.func.function.to.LToIntFunction.toIntFuncThrowing;
import static eu.lunisolar.magma.func.operator.unary.LIntUnaryOperator.intUnaryOpThrowing;
import static org.openjdk.jmh.runner.options.TimeValue.seconds;

@SuppressWarnings("SimplifyStreamApiCallChains")
public class GeneralSanityCheckPerf {

    static final int[] TABLE = new int[45000];

    static final List<String> input = new ArrayList<>();

    @State(Scope.Thread)
    public static class Functions {

        IntUnaryOperator reference = i -> i + 1;

        LIntUnaryOperator                    nonThrowing            = i -> i + 1;
        LIntUnaryOperator/*.LIntUnaryOperatorX*/ theoretically_throwing = i -> i + 1;

        LIntUnaryOperator/*.LIntUnaryOperatorX*/ throwing_for_sure = i -> {
            throw new Exception();
        };

        LIntUnaryOperator/*.LIntUnaryOperatorX*/ throwing_for_sure_runtime = i -> {
            throw new RuntimeException();
        };

        LIntUnaryOperator runtime_for_sure = i -> {
            throw new RuntimeException();
        };

        LIntFunction<Optional> optional = Optional::of;

        LIntFunction<Optional> optional_instead_of_exception = i -> Optional.of("failed message");
    }

    public static <T> T requireNonNullElse(T obj, T defaultObj) {
        return (obj != null) ? obj : Objects.requireNonNull(defaultObj, "defaultObj");
    }

    public static @Nonnull <T> T safeValue(T value, @Nonnull T safe) {
        return requireNonNullElse(value, safe);
    }

    @Benchmark
    @Threads(1)
    public int referenceToThrowing(Functions f) {
        return safeValue(f.nonThrowing, intUnaryOpThrowing(UnsupportedOperationException::new)).doApplyAsInt(32);
    }

    @Benchmark
    @Threads(1)
    public Object reference(Functions f) {
        return f.reference.applyAsInt(32);
    }


    @Benchmark
    @Threads(1)
    public int runtime_for_sure(Functions f) {
        try {
            return f.runtime_for_sure.doApplyAsInt(32);   // doApplyAsInt do not have catch {}
        } catch (Exception e) {
            return 3;
        }
    }

    @Benchmark
    @Threads(1)
    public int throwing_for_sure(Functions f) {
        try {
            return f.throwing_for_sure.doApplyAsInt(32);  // doApplyAsInt has one internal catch {}
        } catch (Exception e) {
            return 3;
        }
    }

    @Benchmark
    @Threads(1)
    public int throwing_for_sure_runtime(Functions f) {
        try {
            return f.throwing_for_sure_runtime.doApplyAsInt(32);  // doApplyAsInt has one internal catch {}  but it just rethrows runtime
        } catch (Exception e) {
            return 3;
        }
    }

    @Benchmark
    @Threads(1)
    public int nonThrowing(Functions f) {
        return f.nonThrowing.doApplyAsInt(32);
    }

     @Benchmark
    @Threads(1)
    public int nonThrowing_byINheritedMethod(Functions f) {
        return f.nonThrowing.applyAsInt(32);
    }

    @Benchmark
    @Threads(1)
    public int theoretically_throwing(Functions f) {
        return f.theoretically_throwing.doApplyAsInt(32);
    }

    @Benchmark
    @Threads(1)
    public Object optional(Functions f) {
        return f.optional.doApply(32).get();
    }

    @Benchmark
    @Threads(1)
    public Object optional_instead_of_exception(Functions f) {
        return f.optional_instead_of_exception.doApply(32).get();
    }

    public static void main(String[] args) throws Exception {
//        Options opt = new OptionsBuilder()
//                .include(StatelessPerf.class.getName())
//                .forks(1)
//                .build();
//
//        new org.openjdk.jmh.runner.Runner(opt).run();

        JMH.jmh()
           .classes(GeneralSanityCheckPerf.class)
           .mode(Mode.Throughput)
           .opt(o -> o.timeUnit(TimeUnit.MILLISECONDS))
           .gc()
//           .mem()
           .run();
    }

    public static class JMH {

        private ChainedOptionsBuilder opt = new OptionsBuilder();

        public JMH() {
            opt.jvmArgs(
                    "-server",
                    "-XX:+UseTLAB",
                    "-XX:+AggressiveOpts",
                    "-XX:+UseBiasedLocking",
                    "-XX:+UseFastAccessorMethods"
            )
               .verbosity(VerboseMode.EXTRA)
               .warmupIterations(3)
               .warmupTime(seconds(1))
               .measurementIterations(3)
               .measurementTime(seconds(1))
               .syncIterations(true)
               .forks(1)
               .resultFormat(ResultFormatType.TEXT);
        }

        public static JMH jmh() {
            return new JMH();
        }

        public JMH opt(Consumer<ChainedOptionsBuilder> options) {
            options.accept(opt);
            return this;
        }

        public JMH classes(Class... testClass) throws RunnerException {
            for (Class c : testClass) {
                opt.include(c.getSimpleName());
            }
            return this;
        }

        public JMH param(String name, String... values) {
            opt.param(name, values);
            return this;
        }

        public JMH mode(Mode mode) {
            opt.mode(mode);
            return this;
        }

        public JMH timeUnit(TimeUnit unit) {
            opt.timeUnit(unit);
            return this;
        }

        public JMH gc() {
            opt.addProfiler(GCProfiler.class);
            return this;
        }

        public JMH mem() {
            opt.addProfiler(HotspotMemoryProfiler.class);
            return this;
        }

        public void run() throws RunnerException {
            new Runner(opt.build()).run();
        }

    }
}
