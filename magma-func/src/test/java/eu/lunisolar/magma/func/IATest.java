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

package eu.lunisolar.magma.func;

import eu.lunisolar.magma.func.consumer.primitives.obj.*;
import eu.lunisolar.magma.func.function.from.LOiFunction;
import eu.lunisolar.magma.func.function.to.*;
import eu.lunisolar.magma.func.predicate.LObjIntPredicate;
import org.testng.annotations.Test;

import static java.util.Arrays.*;
import static org.assertj.core.api.Assertions.assertThat;

/**
 *    
 */
@SuppressWarnings("unchecked")
public class IATest {


     @Test public void testList() {
        Object container = asList(1, 2, 3);

        LToIntFunction size = IA.list().sizeFunc();
        LTieConsumer setter = IA.list().setter();
        LOiFunction getter = IA.list().getter();

        assertThat(size.applyAsInt(container)).isEqualTo(3);
        setter.accept(container, 0, 22);
        assertThat(getter.apply(container, 0)).isEqualTo(22);
    }

     @Test public void testObjectArray() {
        Object container = new Object[]{1,2,3};

        LToIntFunction size = IA.array().sizeFunc();
        LTieConsumer setter = IA.array().setter();
        LOiFunction getter = IA.array().getter();

        assertThat(size.applyAsInt(container)).isEqualTo(3);
        setter.accept(container, 0, 22);
        assertThat(getter.apply(container, 0)).isEqualTo(22);
    }

     @Test public void testIntegerArray() {
        Object container = new Integer[]{1,2,3};

        LToIntFunction size = IA.array().sizeFunc();
        LTieConsumer setter = IA.array().setter();
        LOiFunction getter = IA.array().getter();

        assertThat(size.applyAsInt(container)).isEqualTo(3);
        setter.accept(container, 0, 22);
        assertThat(getter.apply(container, 0)).isEqualTo(22);
    }

     @Test public void testIntArray() {
        Object container = new int[]{1,2,3};

        LToIntFunction size = IA.intArray().sizeFunc();
        LTieIntConsumer setter = IA.intArray().setter();
        LOiToIntFunction getter = IA.intArray().getter();

        assertThat(size.applyAsInt(container)).isEqualTo(3);
        setter.accept(container, 0, 22);
        assertThat(getter.applyAsInt(container, 0)).isEqualTo(22);
    }

     @Test public void testLongArray() {
        Object container = new long[]{1,2,3};

        LToIntFunction size = IA.longArray().sizeFunc();
        LTieLongConsumer setter = IA.longArray().setter();
        LOiToLongFunction getter = IA.longArray().getter();

        assertThat(size.applyAsInt(container)).isEqualTo(3);
        setter.accept(container, 0, 22);
        assertThat(getter.applyAsLong(container, 0)).isEqualTo(22);
    }

     @Test public void testDoubleArray() {
        Object container = new double[]{1,2,3};

        LToIntFunction size = IA.doubleArray().sizeFunc();
        LTieDblConsumer setter = IA.doubleArray().setter();
        LOiToDblFunction getter = IA.doubleArray().getter();

        assertThat(size.applyAsInt(container)).isEqualTo(3);
        setter.accept(container, 0, 22);
        assertThat(getter.applyAsDbl(container, 0)).isEqualTo(22);
    }

     @Test public void testByteArray() {
        Object container = new byte[]{1,2,3};

        LToIntFunction size = IA.byteArray().sizeFunc();
        LTieByteConsumer setter = IA.byteArray().setter();
        LOiToByteFunction getter = IA.byteArray().getter();

        assertThat(size.applyAsInt(container)).isEqualTo(3);
        setter.accept(container, 0, (byte)22);
        assertThat(getter.applyAsByte(container, 0)).isEqualTo((byte)22);
    }

     @Test public void testShortArray() {
        Object container = new short[]{1,2,3};

        LToIntFunction size = IA.shortArray().sizeFunc();
        LTieSrtConsumer setter = IA.shortArray().setter();
        LOiToSrtFunction getter = IA.shortArray().getter();

        assertThat(size.applyAsInt(container)).isEqualTo(3);
        setter.accept(container, 0, (short)22);
        assertThat(getter.applyAsSrt(container, 0)).isEqualTo((short)22);
    }

     @Test public void testCharArray() {
        Object container = new char[]{1,2,3};

        LToIntFunction size = IA.charArray().sizeFunc();
        LTieCharConsumer setter = IA.charArray().setter();
        LOiToCharFunction getter = IA.charArray().getter();

        assertThat(size.applyAsInt(container)).isEqualTo(3);
        setter.accept(container, 0, (char)22);
        assertThat(getter.applyAsChar(container, 0)).isEqualTo((char)22);
    }

     @Test public void testFloatArray() {
        Object container = new float[]{1,2,3};

        LToIntFunction size = IA.floatArray().sizeFunc();
        LTieFltConsumer setter = IA.floatArray().setter();
        LOiToFltFunction getter = IA.floatArray().getter();

        assertThat(size.applyAsInt(container)).isEqualTo(3);
        setter.accept(container, 0, (float)22);
        assertThat(getter.applyAsFlt(container, 0)).isEqualTo((float)22);
    }

     @Test public void testBoolArray() {
        Object container = new boolean[]{true,false,true};

        LToIntFunction size = IA.boolArray().sizeFunc();
        LTieBoolConsumer setter = IA.boolArray().setter();
        LObjIntPredicate getter = IA.boolArray().getter();

        assertThat(size.applyAsInt(container)).isEqualTo(3);
        setter.accept(container, 0, false);
        assertThat(getter.test(container, 0)).isEqualTo(false);
    }


}