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

import eu.lunisolar.magma.examples.support.CheckedException;
import eu.lunisolar.magma.func.IA;
import eu.lunisolar.magma.func.SA;
import eu.lunisolar.magma.func.consumer.LBiConsumer;
import eu.lunisolar.magma.func.consumer.primitives.obj.LBiObjIntConsumer;
import eu.lunisolar.magma.func.consumer.primitives.obj.LTieConsumer;
import eu.lunisolar.magma.func.consumer.primitives.obj.LTieIntConsumer;
import eu.lunisolar.magma.func.function.from.LOiFunction;
import eu.lunisolar.magma.func.predicate.LPredicate;
import org.testng.annotations.Test;

import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.*;

import static eu.lunisolar.magma.func.IA.ia;
import static eu.lunisolar.magma.func.consumer.primitives.obj.LTieConsumer.tieCons;
import static java.util.Arrays.*;
import static java.util.stream.Collectors.*;

//TODO this a compilation "test'
@SuppressWarnings("SimplifyStreamApiCallChains")
public class Example_ForEach_Test {

    int[]    intArray = new int[0];
    String[] strArray = new String[0];

    List<Integer> intList = new ArrayList<>();
    List<String>  strList = new ArrayList<>();

    List<Integer> intCollection = new ArrayList<>();
    List<String>  strCollection = new ArrayList<>();

    @Test
    public void example_TIE_supplier() throws CheckedException {

        //noinspection Convert2MethodRef
        LOiFunction.<List<String>, String>oiFunc((source, index) -> source.get(index));
        LOiFunction.<List<String>, String>oiFunc(List::get);

        LOiFunction.call(asList(0), 0, List::get);

    }

    @Test
    public void example_noGC_copy() throws CheckedException {

        int[] forTarget = new int[intList.size()];
        // the initial approach is simply loop
        for (int i = 0; i < intList.size(); i++) {
            forTarget[i] = intList.get(i);
        }

        String[] targetStrArray = new String[intList.size()];
        // it is not always possible but here should be no GC.
        LTieConsumer.tieForEach(targetStrArray, IA.list(), strList, tieCons((tg, index, element) -> tg[index] = element));
        LTieConsumer.tieForEach(targetStrArray, IA.list(), strList, (tg, index, element) -> tg[index] = element);

        //noinspection Convert2MethodRef
        LTieConsumer.tieForEach(targetStrArray, IA.list(), strList, tieCons((array, index, value) -> Array.set(array, index, value)));
        LTieConsumer.tieForEach(targetStrArray, IA.list(), strList, tieCons(Array::set));
        LTieConsumer.tieForEach(targetStrArray, IA.list(), strList, Array::set);

        int[] targetIntArray = new int[intList.size()];
        //noinspection Convert2MethodRef
        LTieIntConsumer.tieForEach(targetIntArray, IA.intArray(), intArray, (tg, index, element) -> Array.setInt(tg, index, element));
        LTieIntConsumer.tieForEach(targetIntArray, IA.intArray(), intArray, Array::setInt);

        //noinspection Convert2MethodRef
        LTieConsumer.tieIterate(targetStrArray, SA.collection(), strCollection, (tg, index, element) -> Array.set(tg, index, element));
        LTieConsumer.tieIterate(targetStrArray, SA.collection(), strCollection, Array::set);

        List<String> targetStrList = new ArrayList<>();
        // it is not always possible but here should be no GC.
        LTieConsumer.tieForEach(targetStrList, ia(strList), strList, (tg, index, element) -> tg.add(element));
        //noinspection Convert2MethodRef
        LTieConsumer.tieForEach(targetStrList, ia(strList), strList, (tg, index, element) -> tg.add(index, element));
        LTieConsumer.tieForEach(targetStrList, ia(strList), strList, List::add);
        LTieConsumer.tieForEach(targetStrList, ia(strList), strList, List::set);

        //noinspection Convert2MethodRef
        LBiConsumer.targetedForEach(targetStrList, ia(strList), strList, (tg, element) -> tg.add(element));
        LBiConsumer.targetedForEach(targetStrList, ia(strList), strList, List::add);

    }

    @Test
    public void example_filter() throws CheckedException {

        List<Integer> streamResult = intList.stream()
                                            .filter(i -> i > 0)
                                            .collect(toList());

        List<Integer> target = new ArrayList<>();
        LPredicate.<Integer>pred(i -> i > 0).filterForEach(ia(intList), intList, target::add);
    }

    @Test
    public void example_noGC_filtering() throws CheckedException {

        List<Integer> streamResult = intList.stream()
                                            .filter(i -> i > 0)
                                            .collect(toList());

        //int[] target = new int[source.size()];  //TODO primitives

        List<Integer> target = new ArrayList<>();

        // it is not always possible but here should be no GC.

        LPredicate.<Integer>pred(i -> i > 0).tieForEach(target, ia(intList), intList, (tg, index, element) -> tg.add(element));
        LPredicate.<Integer>pred(i -> i > 0).targetedForEach(target, ia(intList), intList, List::add);
    }

    @Test
    public void example2_makesMoreSense() throws CheckedException {

        Integer[] streamResult = intList.stream()
                                        .filter(i -> i > 0)
                                        .toArray(Integer[]::new);

        int[] target = new int[intList.size()];

        // it is not always possible but here should be no GC.
        LTieConsumer.tieForEach(target, ia(intList), intList, (tg, index, element) -> tg[index] = element);

        LBiObjIntConsumer<List<Integer>, Integer> toList = (list, element, index) -> list.set(index, element);

        //TODO where is TiePredicate?
//        LBiObjIntPredicate<Integer, List<Integer>> filter = (_1, _2, _3) -> true;
//        filter.filterForEach(new ArrayList<>(intList.size()), ia(intList), intList,Array::set );
    }

}
