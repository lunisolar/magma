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

import eu.lunisolar.magma.basics.meta.aType.a;
import eu.lunisolar.magma.basics.meta.aType.aInt;
import eu.lunisolar.magma.func.IA;
import eu.lunisolar.magma.func.SA;
import eu.lunisolar.magma.func.consumer.LBiConsumer;
import eu.lunisolar.magma.func.consumer.primitives.obj.LTieConsumer;
import eu.lunisolar.magma.func.function.to.LTieFunction;
import eu.lunisolar.magma.func.supp.tunnel.Funnel;
import eu.lunisolar.magma.func.supp.tunnel.IntFunnel;
import eu.lunisolar.magma.func.supp.tunnel.IntTunnel;
import eu.lunisolar.magma.func.supp.tunnel.Tunnel;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.profile.GCProfiler;
import org.openjdk.jmh.profile.HotspotMemoryProfiler;
import org.openjdk.jmh.results.format.ResultFormatType;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.ChainedOptionsBuilder;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.openjdk.jmh.runner.options.VerboseMode;

import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.stream.*;

import static eu.lunisolar.magma.func.consumer.primitives.obj.LObjIntConsumer.objIntCons;
import static org.openjdk.jmh.runner.options.TimeValue.seconds;

@SuppressWarnings("SimplifyStreamApiCallChains")
public class StatelessPerf {

    static final List<String> data = Arrays.asList("1", "22", "333", "4444", "55555", "666666");

    static final List<String> input = new ArrayList<>();

    static {
        for (int i = 0; i < 10; i++) {
            input.addAll(data);
        }
    }

    @State(Scope.Thread)
    public static class MyState {

        final int[]         target  = new int[input.size()];
        final Integer[]     target2 = new Integer[input.size()];
        final List<Integer> target4 = new ArrayList<>(input.size());

        final IntTunnel<a<String>> tunnel = Tunnel.<String>tunnel()
                .mapToInt(String::length)
                //
                .filter(i -> i > 3)
//                .filter(i -> i > 2)
//                .filter(i -> i > 3)
//                .filter(i -> i > 4)
//                .filter(i -> i > 5)
//                .filter(i -> i > 5)
//                .filter(i -> i > 5)
                //
                ;

        final Tunnel<Integer, a<String>> tunnel2 = Tunnel.<String>tunnel()
                .mapToInt(String::length)
                //
                .filter(i -> i > 3)
//                .filter(i -> i > 2)
//                .filter(i -> i > 3)
//                .filter(i -> i > 4)
//                .filter(i -> i > 5)
//                .filter(i -> i > 5)
//                .filter(i -> i > 5)
                //
                .mapToObj(i -> i);

        final ArraySequentialAccess                  sa      = new ArraySequentialAccess();
        final IntFunnel<int[], a<String>>            funnel  = tunnel.funnelTo(sa);
        final Funnel<Collection<Integer>, a<String>> funnel2 = tunnel2.funnelTo(SA.collection());

        //final LIntSingle.MutIntSingle index = new LIntSingle.MutIntSingle(0);

        int index = 0;

        class ArraySequentialAccess extends SA<int[], int[], aInt> {
            public ArraySequentialAccess() {
                super(SA.UNKNOWN_SIZE, i -> i,
                      a -> index >= a.length,
                      null,
                      objIntCons((int[] a, int e) -> {
                          int i = index;
                          a[i] = e;
                          index++;
                      }));
            }
        }

        @Setup(Level.Invocation)
        public void doSetup() {
            target4.clear();
            index = 0;
        }

        @TearDown(Level.Invocation)
        public void doTearDown() {

        }

    }

    @Benchmark
    @Threads(1)
    public int simple_for(MyState state) {
        int iTarget = 0;
        for (int i = 0; i < input.size(); i++) {
            String s = input.get(i);
            int length = s.length();
            if (length >3  ) {
                state.target[iTarget] = length;
                iTarget++;
            }
        }

        return iTarget;
    }

    @Benchmark
    @Threads(1)
    public int funcIteration(MyState state) {
        return LTieFunction.tieForEach(0, input.size(), 0, state.target, input, List::get, (int[] t, int i, String e) ->  {
            int length = e.length();
            if ( length>3) {
                t[i] = length;
                return 1;
            } else {
                return 0;
            }
        });
    }

    @Benchmark
    @Threads(1)
    public Object reusingTunnel(MyState state) {
        return state.funnel.push(state.target, IA.list(), input);
    }

    @Benchmark
    @Threads(1)
    public Object reusingTunnel2(MyState state) {
        return state.funnel2.push(state.target4, IA.list(), input);
    }

    @Benchmark
    @Threads(1)
    public Object reusingTunnel3(MyState state) {
        return state.funnel.push(state.target, SA.collection(), input);
    }

    @Benchmark
    @Threads(1)
    public Object adHocTunnel(MyState state) {

        IntTunnel<a<String>> tunnel = Tunnel.<String>tunnel()
                .mapToInt(String::length)
                //
                .filter(i -> i > 3)
//                .filter(i -> i > 2)
//                .filter(i -> i > 3)
//                .filter(i -> i > 4)
//                .filter(i -> i > 5)
//                .filter(i -> i > 5)
//                .filter(i -> i > 5)
                //
                ;

        final IntFunnel<int[], a<String>> funnel = tunnel.funnelTo(state.sa);
        return funnel.push(state.target, IA.list(), input);
    }

    @Benchmark
    @Threads(1)
    public int objStream(MyState state) {
        input.stream()
             .map(String::length)
             //
             .filter(i -> i > 3)
//             .filter(i -> i > 2)
//             .filter(i -> i > 3)
//             .filter(i -> i > 4)
//             .filter(i -> i > 5)
//             .filter(i -> i > 5)
//             .filter(i -> i > 5)
             //
             .collect(Collectors.toList());
        return state.hashCode();
    }

    @Benchmark
    @Threads(1)
    public int optStream(MyState state) {
        input.stream()
             .mapToInt(String::length)
             //
             .filter(i -> i > 3)
//             .filter(i -> i > 2)
//             .filter(i -> i > 3)
//             .filter(i -> i > 4)
//             .filter(i -> i > 5)
//             .filter(i -> i > 5)
//             .filter(i -> i > 5)
             //
             .toArray();
        return state.hashCode();
    }

    @Benchmark
    @Threads(1)
    public int opt2Stream(MyState state) {
        return input.stream()
                    .map(String::length)
                    //
                    .filter(i -> i > 3)
//                    .filter(i -> i > 2)
//                    .filter(i -> i > 3)
//                    .filter(i -> i > 4)
//                    .filter(i -> i > 5)
//                    .filter(i -> i > 5)
//                    .filter(i -> i > 5)
                    //
                    .toArray(size -> state.target2).length;
    }

    public static void main(String[] args) throws Exception {
//        Options opt = new OptionsBuilder()
//                .include(StatelessPerf.class.getName())
//                .forks(1)
//                .build();
//
//        new org.openjdk.jmh.runner.Runner(opt).run();

        JMH.jmh()
           .classes(StatelessPerf.class)
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
