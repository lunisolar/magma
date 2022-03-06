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

package eu.lunisolar.magma.func;

import eu.lunisolar.magma.basics.meta.aType;
import eu.lunisolar.magma.basics.meta.aType.a;
import eu.lunisolar.magma.basics.meta.functional.SequentialRead;
import eu.lunisolar.magma.basics.meta.functional.SequentialWrite;
import eu.lunisolar.magma.basics.meta.functional.type.OFunction;
import eu.lunisolar.magma.basics.meta.functional.type.TeConsumer;
import eu.lunisolar.magma.func.consumer.LBiConsumer;
import eu.lunisolar.magma.func.function.LFunction;
import eu.lunisolar.magma.func.function.to.LToIntFunction;
import eu.lunisolar.magma.func.predicate.LPredicate;
import org.testng.annotations.Test;

import javax.annotation.Nonnull;
import java.lang.reflect.*;
import java.util.*;
import java.util.stream.*;

import static eu.lunisolar.magma.func.consumer.LBiConsumer.biCons;
import static eu.lunisolar.magma.func.consumer.LBiConsumer.biConsThrowing;
import static eu.lunisolar.magma.func.function.LFunction.func;
import static eu.lunisolar.magma.func.function.LFunction.identity;
import org.testng.Assert;
import static java.util.Arrays.*;

/**
 *    
 */
@SuppressWarnings("unchecked")
public class SATest {


    @Test public void testCollection() {
        Object container = new ArrayList();

        SA<?, ?, a<?>> sa = (SA) SA.collection();
        LToIntFunction size = sa.sizeFunc();
        LFunction iteratorFunc = sa.adapter();
        LPredicate hasNextPredicate = sa.tester();
        LFunction nextFunc = sa.supplier();
        LBiConsumer consumer = sa.consumer();

        Assert.assertEquals(size.applyAsInt(container), 0);

        Object iterator = iteratorFunc.apply(container);

        Assert.assertEquals(hasNextPredicate.test(iterator), false);
        consumer.accept(container, 22);
        iterator = iteratorFunc.apply(container);
        Assert.assertTrue(hasNextPredicate.test(iterator));

        Assert.assertEquals(nextFunc.apply(iterator), 22);

        Assert.assertFalse(hasNextPredicate.test(iterator));
    }

    @Test public void testIterable() {
        Object container = asList(22);

        SA<?, ?, a<?>> sa = (SA) SA.iterable();
        LToIntFunction size = sa.sizeFunc();
        LFunction iteratorFunc = sa.adapter();
        LPredicate hasNextPredicate = sa.tester();
        LFunction nextFunc = sa.supplier();
        LBiConsumer consumer = sa.consumer();

        Assert.assertEquals(size.applyAsInt(container), -1);

        Object iterator = iteratorFunc.apply(container);

        try {
            consumer.accept(container, 22);
            Assert.fail("Expecting exception");
        } catch (Throwable e) {
            Assert.assertEquals(e.getClass(), UnsupportedOperationException.class);
        }
        Assert.assertTrue(hasNextPredicate.test(iterator));

        Assert.assertEquals(nextFunc.apply(iterator), 22);

        Assert.assertFalse(hasNextPredicate.test(iterator));
    }

    @Test public void testStream() {
        Object container = asList(22).stream();

        SA<?, ?, a<?>> sa = (SA) SA.stream();
        LToIntFunction size = sa.sizeFunc();
        LFunction iteratorFunc = sa.adapter();
        LPredicate hasNextPredicate = sa.tester();
        LFunction nextFunc = sa.supplier();
        LBiConsumer consumer = sa.consumer();

        Assert.assertEquals(size.applyAsInt(container), -1);

        Object iterator = iteratorFunc.apply(container);

        try {
            consumer.accept(container, 22);
            Assert.fail("Expecting exception");
        } catch (Throwable e) {
            Assert.assertEquals(e.getClass(), UnsupportedOperationException.class);
        }
        Assert.assertTrue(hasNextPredicate.test(iterator));

        Assert.assertEquals(nextFunc.apply(iterator), 22);

        Assert.assertFalse(hasNextPredicate.test(iterator));
    }

    @Test public void testIterator() {
        Object container = asList(22).iterator();

        SA<?, ?, a<?>> sa = (SA) SA.iterator();
        LToIntFunction size = sa.sizeFunc();
        LFunction iteratorFunc = sa.adapter();
        LPredicate hasNextPredicate = sa.tester();
        LFunction nextFunc = sa.supplier();
        LBiConsumer consumer = sa.consumer();

        Assert.assertEquals(size.applyAsInt(container), -1);

        Object iterator = iteratorFunc.apply(container);

        try {
            consumer.accept(container, 22);
            Assert.fail("Expecting exception");
        } catch (Throwable e) {
            Assert.assertEquals(e.getClass(), UnsupportedOperationException.class);
        }
        Assert.assertTrue(hasNextPredicate.test(iterator));

        Assert.assertEquals(nextFunc.apply(iterator), 22);

        Assert.assertFalse(hasNextPredicate.test(iterator));
    }

    @Test public void testEnumeration() {
        Object container = Collections.enumeration(asList(22));

        SA<?, ?, a<?>> sa = (SA) SA.enumeration();
        LToIntFunction size = sa.sizeFunc();
        LFunction iteratorFunc = sa.adapter();
        LPredicate hasNextPredicate = sa.tester();
        LFunction nextFunc = sa.supplier();
        LBiConsumer consumer = sa.consumer();

        Assert.assertEquals(size.applyAsInt(container), -1);

        Object iterator = iteratorFunc.apply(container);

        try {
            consumer.accept(container, 22);
            Assert.fail("Expecting exception");
        } catch (Throwable e) {
            Assert.assertEquals(e.getClass(), UnsupportedOperationException.class);
        }
        Assert.assertTrue(hasNextPredicate.test(iterator));

        Assert.assertEquals(nextFunc.apply(iterator), 22);

        Assert.assertFalse(hasNextPredicate.test(iterator));
    }

    @Test public void testListIterator() {
        Object container = asList(22).listIterator();

        SA<?, ?, a<?>> sa = (SA) SA.listIterator();
        LToIntFunction size = sa.sizeFunc();
        LFunction iteratorFunc = sa.adapter();
        LPredicate hasNextPredicate = sa.tester();
        LFunction nextFunc = sa.supplier();
        LBiConsumer consumer = sa.consumer();

        Assert.assertEquals(size.applyAsInt(container), -1);

        Object iterator = iteratorFunc.apply(container);

        try {
            consumer.accept(container, 22);
            Assert.fail("Expecting exception");
        } catch (Throwable e) {
            Assert.assertEquals(e.getClass(), UnsupportedOperationException.class);
        }
        Assert.assertTrue(hasNextPredicate.test(iterator));

        Assert.assertEquals(nextFunc.apply(iterator), 22);

        Assert.assertFalse(hasNextPredicate.test(iterator));
    }

    @Test public void testListIterator_ArrayList() {
        Object container = new ArrayList(asList(22)).listIterator();

        SA<?, ?, a<?>> sa = (SA) SA.listIterator();
        LToIntFunction size = sa.sizeFunc();
        LFunction iteratorFunc = sa.adapter();
        LPredicate hasNextPredicate = sa.tester();
        LFunction nextFunc = sa.supplier();
        LBiConsumer consumer = sa.consumer();

        Assert.assertEquals(size.applyAsInt(container), -1);

        Object iterator = iteratorFunc.apply(container);

        Assert.assertEquals(hasNextPredicate.test(iterator), true);
        consumer.accept(container, 22);
        iterator = iteratorFunc.apply(container);
        Assert.assertTrue(hasNextPredicate.test(iterator));

        Assert.assertEquals(nextFunc.apply(iterator), 22);

        Assert.assertFalse(hasNextPredicate.test(iterator));
    }

    @Test public void testArray() {
        Object container = new Object[]{22};

        SA<?, ?, a<?>> sa = (SA) SA.array();
        LToIntFunction size = sa.sizeFunc();
        LFunction iteratorFunc = sa.adapter();
        LPredicate hasNextPredicate = sa.tester();
        LFunction nextFunc = sa.supplier();
        LBiConsumer consumer = sa.consumer();

        Assert.assertEquals(size.applyAsInt(container), 1);

        Object iterator = iteratorFunc.apply(container);

        try {
            consumer.accept(container, 22);
            Assert.fail("Expecting exception");
        } catch (Throwable e) {
            Assert.assertEquals(e.getClass(), UnsupportedOperationException.class);
        }
        Assert.assertTrue(hasNextPredicate.test(iterator));

        Assert.assertEquals(nextFunc.apply(iterator), 22);

        Assert.assertFalse(hasNextPredicate.test(iterator));
    }

    @Test public void testIntegerArray() {
        Object container = new Integer[]{22};

        SA<?, ?, a<?>> sa = (SA) SA.array();
        LToIntFunction size = sa.sizeFunc();
        LFunction iteratorFunc = sa.adapter();
        LPredicate hasNextPredicate = sa.tester();
        LFunction nextFunc = sa.supplier();
        LBiConsumer consumer = sa.consumer();

        Assert.assertEquals(size.applyAsInt(container), 1);

        Object iterator = iteratorFunc.apply(container);

        try {
            consumer.accept(container, 22);
            Assert.fail("Expecting exception");
        } catch (Throwable e) {
            Assert.assertEquals(e.getClass(), UnsupportedOperationException.class);
        }
        Assert.assertTrue(hasNextPredicate.test(iterator));

        Assert.assertEquals(nextFunc.apply(iterator), 22);

        Assert.assertFalse(hasNextPredicate.test(iterator));
    }

    @Test public void testIntArray() {
        Object container = new int[]{22};

        SA<?, ?, a<?>> sa = (SA) SA.intArrayObj();
        LToIntFunction size = sa.sizeFunc();
        LFunction iteratorFunc = sa.adapter();
        LPredicate hasNextPredicate = sa.tester();
        LFunction nextFunc = sa.supplier();
        LBiConsumer consumer = sa.consumer();

        Assert.assertEquals(size.applyAsInt(container), 1);

        Object iterator = iteratorFunc.apply(container);

        try {
            consumer.accept(container, 22);
            Assert.fail("Expecting exception");
        } catch (Throwable e) {
            Assert.assertEquals(e.getClass(), UnsupportedOperationException.class);
        }
        Assert.assertTrue(hasNextPredicate.test(iterator));

        Assert.assertEquals(nextFunc.apply(iterator), 22);

        Assert.assertFalse(hasNextPredicate.test(iterator));
    }

    @Test public void testLongArray() {
        Object container = new long[]{22};

        SA<?, ?, a<?>> sa = (SA) SA.longArrayObj();
        LToIntFunction size = sa.sizeFunc();
        LFunction iteratorFunc = sa.adapter();
        LPredicate hasNextPredicate = sa.tester();
        LFunction nextFunc = sa.supplier();
        LBiConsumer consumer = sa.consumer();

        Assert.assertEquals(size.applyAsInt(container), 1);

        Object iterator = iteratorFunc.apply(container);

        try {
            consumer.accept(container, 22L);
            Assert.fail("Expecting exception");
        } catch (Throwable e) {
            Assert.assertEquals(e.getClass(), UnsupportedOperationException.class);
        }
        Assert.assertTrue(hasNextPredicate.test(iterator));

        Assert.assertEquals(nextFunc.apply(iterator), 22L);

        Assert.assertFalse(hasNextPredicate.test(iterator));
    }

    @Test public void testByteArray() {
        Object container = new byte[]{22};

        SA<?, ?, a<?>> sa = (SA) SA.byteArrayObj();
        LToIntFunction size = sa.sizeFunc();
        LFunction iteratorFunc = sa.adapter();
        LPredicate hasNextPredicate = sa.tester();
        LFunction nextFunc = sa.supplier();
        LBiConsumer consumer = sa.consumer();

        Assert.assertEquals(size.applyAsInt(container), 1);

        Object iterator = iteratorFunc.apply(container);

        try {
            consumer.accept(container, 22);
            Assert.fail("Expecting exception");
        } catch (Throwable e) {
            Assert.assertEquals(e.getClass(), UnsupportedOperationException.class);
        }
        Assert.assertTrue(hasNextPredicate.test(iterator));

        Assert.assertEquals(nextFunc.apply(iterator), 22);

        Assert.assertFalse(hasNextPredicate.test(iterator));
    }


}