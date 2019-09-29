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

import eu.lunisolar.magma.func.IA;
import eu.lunisolar.magma.func.consumer.LBiConsumer;
import eu.lunisolar.magma.func.consumer.primitives.obj.LObjIntConsumer;
import eu.lunisolar.magma.func.consumer.primitives.obj.LTieConsumer;
import eu.lunisolar.magma.func.function.to.LTieFunction;
import org.testng.annotations.Test;

import javax.annotation.Nonnull;
import java.lang.reflect.*;
import java.util.*;
import java.util.stream.*;

import static eu.lunisolar.magma.func.consumer.LBiConsumer.biCons;
import static eu.lunisolar.magma.func.consumer.LBiConsumer.targetedForEach;
import static eu.lunisolar.magma.func.consumer.primitives.obj.LTieConsumer.tieCons;
import static org.assertj.core.api.Assertions.assertThat;

@SuppressWarnings("RedundantArrayCreation")
public class IterationsTest {

    @Nonnull private Integer[] sourceArray() {
        return new Integer[]{100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119};
    }

    @Nonnull private Integer[] targetArray() {
        return new Integer[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19};
    }

    @Test
    void consumerForEach0() {
        Integer[] targetArray = targetArray();
        int targetIncrement = LTieConsumer.tieForEach(0, 10, 0, targetArray, sourceArray(), (o, i) -> o[i], (t, i, e) -> t[i] = e);
        assertThat(targetIncrement).isEqualTo(10);
        assertThat(targetArray).containsExactly(new Integer[]{100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19});

        targetIncrement = LTieConsumer.tieForEach(0, 10, targetIncrement, targetArray, sourceArray(), Array::get, Array::set);
        assertThat(targetIncrement).isEqualTo(10);
        assertThat(targetArray).containsExactly(new Integer[]{100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109});
    }

    @Test
    void consumerForEach1() {
        Integer[] targetArray = targetArray();
        int targetIncrement = LTieConsumer.tieForEach(0, 10, 3, targetArray, sourceArray(), Array::get, Array::set);
        assertThat(targetIncrement).isEqualTo(10);
        assertThat(targetArray).containsExactly(new Integer[]{0, 1, 2, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 13, 14, 15, 16, 17, 18, 19});
    }

    @Test
    void consumerForEach2() {
        Integer[] targetArray = targetArray();
        int targetIncrement = LTieConsumer.tieForEach(5, 14, 3, targetArray, sourceArray(), Array::get, Array::set);
        assertThat(targetIncrement).isEqualTo(9);
        assertThat(targetArray).containsExactly(new Integer[]{0, 1, 2, 105, 106, 107, 108, 109, 110, 111, 112, 113, 12, 13, 14, 15, 16, 17, 18, 19});
    }

    @Test(expectedExceptions = IndexOutOfBoundsException.class, expectedExceptionsMessageRegExp = "100")
    void functionForEach_throwing0() {
        int targetIncrement = LTieFunction.tieForEach(0, 10, 0, targetArray(), sourceArray(), (o, i) -> o[i], (t, i, e) -> t[i] = e);
        // (t, i, e) -> t[i] = e
        // It is a function that returns [t[i] = e] that evaluates to [e]. First [e] is 100 and this is increment for the next target index.
    }

    @Test
    void functionForEach0() {
        Integer[] targetArray = targetArray();
        int targetIncrement = LTieFunction.tieForEach(0, 10, 0, targetArray, sourceArray(), Array::get, tieCons(Array::set).toTieFunction());
        assertThat(targetIncrement).isEqualTo(10);
        assertThat(targetArray).containsExactly(new Integer[]{100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19});

        targetIncrement = LTieFunction.tieForEach(0, 10, targetIncrement, targetArray, sourceArray(), Array::get, tieCons(Array::set).toTieFunction());
        assertThat(targetIncrement).isEqualTo(10);
        assertThat(targetArray).containsExactly(new Integer[]{100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109});
    }

    @Test
    void functionForEach1() {

        // A "consumer" that says that each element has "unpacked" into to spaces (indexes), but actually only "uses" one of them.
        LTieFunction<Object, Object> tieFunction = tieCons(Array::set).toTieFunction().thenToInt(i -> 2);

        Integer[] targetArray = Stream.concat(Stream.of(targetArray()), Stream.of(targetArray())).toArray(Integer[]::new);

        int targetIncrement = LTieFunction.tieForEach(0, 10, 0, targetArray, sourceArray(), Array::get, tieFunction);
        assertThat(targetIncrement).isEqualTo(20);
        assertThat(targetArray).containsExactly(new Integer[]{
                100, 1, 101, 3, 102, 5, 103, 7, 104, 9, 105, 11, 106, 13, 107, 15, 108, 17, 109, 19,
                0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19});

        targetIncrement = LTieFunction.tieForEach(0, 10, targetIncrement, targetArray, sourceArray(), Array::get,
                                                  tieFunction);
        assertThat(targetIncrement).isEqualTo(20);
        assertThat(targetArray).containsExactly(new Integer[]{
                100, 1, 101, 3, 102, 5, 103, 7, 104, 9, 105, 11, 106, 13, 107, 15, 108, 17, 109, 19,
                100, 1, 101, 3, 102, 5, 103, 7, 104, 9, 105, 11, 106, 13, 107, 15, 108, 17, 109, 19});
    }

    @Test
    void functionForEach2() {
        List<Integer> result  = targetedForEach(new ArrayList(), IA.array(), sourceArray(), Collection::add);
        assertThat(result).containsExactly(100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119);
    }

}
