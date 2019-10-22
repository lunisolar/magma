///*
// * This file is part of "lunisolar-magma".
// *
// * (C) Copyright 2014-2019 Lunisolar (http://lunisolar.eu/).
// *
// * Licensed under the Apache License, Version 2.0 (the "License");
// * you may not use this file except in compliance with the License.
// * You may obtain a copy of the License at
// *
// *     http://www.apache.org/licenses/LICENSE-2.0
// *
// * Unless required by applicable law or agreed to in writing, software
// * distributed under the License is distributed on an "AS IS" BASIS,
// * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// * See the License for the specific language governing permissions and
// * limitations under the License.
// */
//
//package eu.lunisolar.magma.examples;
//
//import eu.lunisolar.magma.basics.meta.aType.a;
//import eu.lunisolar.magma.basics.meta.aType.aInt;
//import eu.lunisolar.magma.basics.perf.JMH;
//import eu.lunisolar.magma.func.IA;
//import eu.lunisolar.magma.func.SA;
//import eu.lunisolar.magma.func.consumer.LBiConsumer;
//import eu.lunisolar.magma.func.consumer.LTriConsumer;
//import eu.lunisolar.magma.func.consumer.primitives.obj.LObjIntConsumer;
//import eu.lunisolar.magma.func.consumer.primitives.obj.LTieConsumer;
//import eu.lunisolar.magma.func.function.from.LOiFunction;
//import eu.lunisolar.magma.func.function.to.LTieFunction;
//import eu.lunisolar.magma.func.tuple.LIntSingle.MutIntSingle;
//import eu.lunisolar.magma.func.tuple.Tuple4U;
//import org.openjdk.jmh.annotations.*;
//
//import javax.annotation.Nonnull;
//import java.lang.reflect.*;
//import java.util.*;
//import java.util.concurrent.*;
//
//import static eu.lunisolar.magma.func.IA.iA;
//import static eu.lunisolar.magma.func.consumer.primitives.obj.LTieConsumer.tieCons;
//import static eu.lunisolar.magma.func.consumer.primitives.obj.LTieIntConsumer.tieIntCons;
//import static eu.lunisolar.magma.func.function.to.LOiToIntFunction.oiToIntFunc;
//import static org.openjdk.jmh.runner.options.TimeValue.seconds;
//
//@SuppressWarnings("SimplifyStreamApiCallChains")
//public class LoopsPerf {
//
//    private static final IA<Object[], a<Object>> ARRAY_O   = iA(a -> a.length, LOiFunction.oiFunc((a, i) -> a[i]), tieCons((a, i, e) -> a[i] = e));
//    private static final IA<int[], aInt>         INT_ARRAY = iA(a -> a.length, oiToIntFunc((a, i) -> a[i]), tieIntCons((a, i, e) -> a[i] = e));
//
//    private static final IA<?, a<?>> ARRAY_T = (IA) iA_();
//
//    @Nonnull private static <T> IA<T[], a<T>> iA_() {
//        return iA(a -> a.length, LOiFunction.oiFunc((a, i) -> a[i]), tieCons((a, i, e) -> a[i] = e));
//    }
//
//    @Nonnull private static <T> IA<T[], a<T>> arrayT() {
//        return (IA) ARRAY_T;
//    }
//
//    static final List<String> data = Arrays.asList("1", "22", "333", "4444", "55555", "666666");
//
//    static final List<String> input = new ArrayList<>();
//
//    static {
//        for (int i = 0; i < 1000; i++) {
//            input.addAll(data);
//        }
//    }
//
//    @State(Scope.Thread)
//    public static class MyState {
//
//        final int[]              target     = new int[input.size()];
//        final Integer[]          target2    = new Integer[input.size()];
//        final String[]           target3    = new String[input.size()];
////        final ArrayList<Integer> target4    = new ArrayList<>(input.size());
//        final ArrayList<String>  strTarget  = new ArrayList<>(input.size());
//        final ArrayList<String>  listTarget = new ArrayList<>(input);
//
//        final MutIntSingle i = Tuple4U.intSingle(0);
//
//        @TearDown(Level.Invocation)
//        public void doTearDown() {
//
//        }
//
//    }
//
//    @Benchmark
//    @Threads(1)
//    public void copyOfPairsReferencePerf(MyState state) {
//        LTriConsumer.pairForEach(state.target3, (IA) IA.list(), input, (String[] t, String k, String v) -> {
//            int index = 0;
//            t[index++] = k;
//            t[index++] = v;
//        });
//    }
//
//    @Benchmark
//    @Threads(1)
//    public void copyOfPairsReferencePerf2(MyState state) {
//        state.i.setValue(0);
//        LTriConsumer.pairForEach(state.target3, (IA) IA.list(), input, (String[] t, String k, String v) -> {
//            MutIntSingle i = state.i;
//            int index = i.value();
//            t[index++] = k;
//            t[index++] = v;
//            i.setValue(index);
//        });
//    }
//
//    @Benchmark
//    @Threads(1)
//    public void copyOfPairs(MyState state) {
//        final MutIntSingle i = Tuple4U.intSingle(0);
//        LTriConsumer.pairForEach(state.target3, (IA) IA.list(), input, (String[] t, String k, String v) -> {
//            int index = i.value();
//            t[index++] = k;
//            t[index++] = v;
//            i.setValue(index);
//        });
//    }
//
//    @Benchmark
//    @Threads(1)
//    public int simple_for(MyState state) {
//        int iTarget = 0;
//        for (int i = 0; i < input.size(); i++) {
//            String s = input.get(i);
//            int length = s.length();
//            if (length > 3) {
//                state.target[iTarget] = length;
//                iTarget++;
//            }
//        }
//
//        return iTarget;
//    }
//
//    @Benchmark
//    @Threads(1)
//    public int simple_for___is______Collection_add___really_that_bad_Q(MyState state) {
//        state.strTarget.clear();
//        int iTarget = 0;
//        for (int i = 0; i < input.size(); i++) {
//            String s = input.get(i);
//            state.strTarget.add(s);
//        }
//
//        return iTarget;
//    }
//
//    @Benchmark
//    @Threads(1)
//    public int funcIteration_array(MyState state) {
//        return LTieFunction.tieForEach(0, input.size(), 0, state.target, input, List::get, (int[] t, int i, String e) -> {
//            int length = e.length();
//            if (length > 3) {
//                t[i] = length;
//                return 1;
//            } else {
//                return 0;
//            }
//        });
//    }
//
//    @Benchmark
//    @Threads(1)
//    public int funcIteration_collection(MyState state) {
//
//        state.strTarget.clear();
//        return LTieFunction.tieForEach(0, input.size(), 0, state.strTarget, input, List::get, (Collection t, int i, String e) -> {
//            int length = e.length();
//            if (length > 3) {
//                t.add(length);
//                return 1;
//            } else {
//                return 0;
//            }
//        });
//    }
//
//    @Benchmark
//    @Threads(1)
//    public int funcIteration_list(MyState state) {
//        return LTieFunction.tieForEach(0, input.size(), 0, state.listTarget, input, List::get, (List t, int i, String e) -> {
//            int length = e.length();
//            if (length > 3) {
//                t.set(i, length);
//                return 1;
//            } else {
//                return 0;
//            }
//        });
//    }
//
//    @Benchmark
//    @Threads(1)
//    public int funcIteration_list_no_filtering(MyState state) {
//        return LTieConsumer.tieForEach(0, input.size(), 0, state.listTarget, input, List::get, List::set);
//    }
//
//    @Benchmark
//    @Threads(1)
//    public int funcIteration_array_no_filtering(MyState state) {
//        return LTieConsumer.tieForEach(0, input.size(), 0, state.target3, input, List::get, Array::set);
//    }
//
//    @Benchmark
//    @Threads(1)
//    public int funcIteration_array_no_filtering2(MyState state) {
//        return LTieConsumer.tieForEach(0, input.size(), 0, state.target3, input, List::get, (t, i, e) -> t[i] = e);
//    }
//
//    @Benchmark
//    @Threads(1)
//    public int funcIteration_array_no_filtering3(MyState state) {
//        return LTieConsumer.tieForEach(0, input.size(), 0, state.target3, input, List::get, (t, i, e) -> Array.set(t, i, e));
//    }
//
//    @Benchmark
//    @Threads(1)
//    public List<String> for_iterator(MyState state) {
//        state.strTarget.clear();
//        for (String s : input) {
//            state.strTarget.add(s);
//        }
//        return state.strTarget;
//    }
//
//    @Benchmark
//    @Threads(1)
//    public List<String> for_collection(MyState state) {
//        state.strTarget.clear();
//        for (int i = 0; i < input.size(); i++) {
//            String s = input.get(i);
//            state.strTarget.add(s);
//        }
//        return state.strTarget;
//    }
//
//    @Benchmark
//    @Threads(1)
//    public List<String> collectionAddAll(MyState state) {
//        state.strTarget.clear();
//        state.strTarget.addAll(input);    // two range copy operations of array (ArrayList.toArray, Arrays.copy
//        return state.strTarget;
//    }
//
//    @Benchmark
//    @Threads(1)
//    public List<String> funcIteration2(MyState state) {
//        state.strTarget.clear();
//        return LBiConsumer.targetedIterate(state.strTarget, SA.collection(), input, Collection::add);
//    }
//
//    @Benchmark
//    @Threads(1)
//    public List<String> funcIteration3(MyState state) {
//        state.strTarget.clear();
//        return LBiConsumer.targetedForEach(state.strTarget, IA.list(), input, Collection::add);
//    }
//
//    @Benchmark
//    @Threads(1)
//    public List<String> funcIteration4(MyState state) {
//        state.strTarget.clear();
//        return LBiConsumer.targetedForEach(state.strTarget, IA.list(), input, ArrayList::add);
//    }
//
//    @Benchmark
//    @Threads(1)
//    public int funcIteration_forEach_list_list(MyState state) {
//        int i = LBiConsumer.forEach(IA.list(), state.listTarget, IA.list(), input, LBiConsumer::doNothing);
//
//        if (i != input.size()) {
//            throw new RuntimeException();
//        }
//        return i;
//    }
//
//    @Benchmark
//    @Threads(1)
//    public int funcIteration_forEach_array_list(MyState state) {
//        int i = LBiConsumer.forEach(IA.array(), state.target2, IA.list(), input, LBiConsumer::doNothing);
//
//        if (i != input.size()) {
//            throw new RuntimeException();
//        }
//
//        return i;
//    }
//
//    @Benchmark
//    @Threads(1)
//    public int funcIteration_forEach_array2_list(MyState state) {
//        int i = LBiConsumer.forEach(ARRAY_O, state.target2, IA.list(), input, LBiConsumer::doNothing);
//
//        if (i != input.size()) {
//            throw new RuntimeException();
//        }
//
//        return i;
//    }
//
//    @Benchmark
//    @Threads(1)
//    public int funcIteration_forEach_array3_list(MyState state) {
//        int i = LBiConsumer.forEach(arrayT(), state.target2, IA.list(), input, LBiConsumer::doNothing);
//
//        if (i != input.size()) {
//            throw new RuntimeException();
//        }
//
//        return i;
//    }
//
//    @Benchmark
//    @Threads(1)
//    public List<String> funcIteration_forEach_primitivveAarray_list(MyState state) {
//        int i = LObjIntConsumer.forEach(IA.list(), input, IA.intArray(), state.target, LObjIntConsumer::doNothing);
//
//        if (i != input.size()) {
//            throw new RuntimeException();
//        }
//
//        return null;
//    }
//
//    @Benchmark
//    @Threads(1)
//    public List<String> funcIteration_forEach_primitivveAarray2_list(MyState state) {
//        int i = LObjIntConsumer.forEach(IA.list(), input, INT_ARRAY, state.target, LObjIntConsumer::doNothing);
//
//        if (i != input.size()) {
//            throw new RuntimeException();
//        }
//
//        return null;
//    }
//
////    public static void main(String[] args) throws Exception {
////
////        JMH.jmh()
////           .iterations(3, seconds(3), 3, seconds(3))
////           .classes(LoopsPerf.class)
////           .mode(Mode.Throughput)
////           .opt(o -> o.timeUnit(TimeUnit.MILLISECONDS))
////           .gc()
//////           .mem()
////           .run();
////    }
//
//}
