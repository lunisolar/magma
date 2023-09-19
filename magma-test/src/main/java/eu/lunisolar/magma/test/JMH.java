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

package eu.lunisolar.magma.test;

import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.profile.GCProfiler;
import org.openjdk.jmh.profile.HotspotMemoryProfiler;
import org.openjdk.jmh.results.format.ResultFormatType;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.ChainedOptionsBuilder;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.openjdk.jmh.runner.options.TimeValue;
import org.openjdk.jmh.runner.options.VerboseMode;

import java.util.concurrent.*;
import java.util.function.*;

public class JMH {

    private ChainedOptionsBuilder opt = new OptionsBuilder();

    public JMH() {
        opt
                .verbosity(VerboseMode.EXTRA)
                .syncIterations(true)
                .forks(1)
                .resultFormat(ResultFormatType.TEXT);
    }

    public static JMH jmh() {
        return new JMH();
    }

    public JMH java8ServerArgs() {
        opt.jvmArgs(
                "-server",
                "-XX:+UseTLAB",
                "-XX:+AggressiveOpts",
                "-XX:+UseBiasedLocking",
                "-XX:+UseFastAccessorMethods"
        );
        return this;
    }

    public JMH java10ServerArgs() {
        opt.jvmArgs(
                "-server",
                "-XX:+UseTLAB",
                "-XX:+AggressiveOpts",
                "-XX:+UseBiasedLocking"
        );
        return this;
    }

    public JMH javaServerArgs() {
        opt.jvmArgs(
                "-server",
                "-XX:+UseTLAB",
                "-XX:+AggressiveOpts",
                "-XX:+UseBiasedLocking"
        );
        return this;
    }

    public JMH iterations(int warmupTimes, TimeValue warmupDuration, int measurementsTimes, TimeValue measurementsDurations) {
        opt.warmupIterations(warmupTimes)
           .warmupTime(warmupDuration)
           .measurementIterations(measurementsTimes)
           .measurementTime(measurementsDurations);
        return this;
    }

    public JMH opt(Consumer<ChainedOptionsBuilder> options) {
        options.accept(opt);
        return this;
    }

    public JMH classes(Class... testClass) {
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

    public void run() {
        try {
            new Runner(opt.build()).run();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}