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

package eu.lunisolar.magma.examples;

import eu.lunisolar.magma.func.function.from.LIntFunction;
import eu.lunisolar.magma.func.function.to.LToIntFunction;
import eu.lunisolar.magma.func.operator.unary.LIntUnaryOperator;
import eu.lunisolar.magma.test.JMH;
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
        LIntUnaryOperator theoretically_throwing = i -> i + 1;

        LIntUnaryOperator throwing_for_sure = i -> {
            throw new Exception();
        };

        LIntUnaryOperator throwing_for_sure_runtime = i -> {
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
        return safeValue(f.nonThrowing, intUnaryOpThrowing(UnsupportedOperationException::new)).applyAsInt(32);
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
            return f.runtime_for_sure.applyAsInt(32);   // doApplyAsInt do not have catch {}
        } catch (Exception e) {
            return 3;
        }
    }

    @Benchmark
    @Threads(1)
    public int throwing_for_sure(Functions f) {
        try {
            return f.throwing_for_sure.applyAsInt(32);  // doApplyAsInt has one internal catch {}
        } catch (Exception e) {
            return 3;
        }
    }

    @Benchmark
    @Threads(1)
    public int throwing_for_sure_runtime(Functions f) {
        try {
            return f.throwing_for_sure_runtime.applyAsInt(32);  // doApplyAsInt has one internal catch {}  but it just rethrows runtime
        } catch (Exception e) {
            return 3;
        }
    }

    @Benchmark
    @Threads(1)
    public int nonThrowing(Functions f) {
        return f.nonThrowing.applyAsInt(32);
    }

     @Benchmark
    @Threads(1)
    public int nonThrowing_byINheritedMethod(Functions f) {
        return f.nonThrowing.applyAsInt(32);
    }

    @Benchmark
    @Threads(1)
    public int theoretically_throwing(Functions f) {
        return f.theoretically_throwing.applyAsInt(32);
    }

    @Benchmark
    @Threads(1)
    public Object optional(Functions f) {
        return f.optional.apply(32).get();
    }

    @Benchmark
    @Threads(1)
    public Object optional_instead_of_exception(Functions f) {
        return f.optional_instead_of_exception.apply(32).get();
    }

    public static void main(String[] args) throws Exception {

        JMH.jmh()
           .iterations(3, seconds(30), 3, seconds(30))
           .classes(GeneralSanityCheckPerf.class)
           .mode(Mode.Throughput)
           .opt(o -> o.timeUnit(TimeUnit.MILLISECONDS))
           .gc()
//           .mem()
           .run();
    }


}
