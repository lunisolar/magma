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

package eu.lunisolar.magma.func.function.from;

import eu.lunisolar.magma.func.*; // NOSONAR
import javax.annotation.Nonnull; // NOSONAR
import javax.annotation.Nullable; // NOSONAR
import java.util.Objects;// NOSONAR
import eu.lunisolar.magma.basics.meta.*; // NOSONAR
import eu.lunisolar.magma.basics.meta.functional.*; // NOSONAR
import eu.lunisolar.magma.basics.meta.functional.type.*; // NOSONAR
import eu.lunisolar.magma.basics.meta.functional.domain.*; // NOSONAR

import eu.lunisolar.magma.func.action.*; // NOSONAR
import eu.lunisolar.magma.func.consumer.*; // NOSONAR
import eu.lunisolar.magma.func.consumer.primitives.*; // NOSONAR
import eu.lunisolar.magma.func.consumer.primitives.bi.*; // NOSONAR
import eu.lunisolar.magma.func.consumer.primitives.obj.*; // NOSONAR
import eu.lunisolar.magma.func.consumer.primitives.tri.*; // NOSONAR
import eu.lunisolar.magma.func.function.*; // NOSONAR
import eu.lunisolar.magma.func.function.conversion.*; // NOSONAR
import eu.lunisolar.magma.func.function.from.*; // NOSONAR
import eu.lunisolar.magma.func.function.to.*; // NOSONAR
import eu.lunisolar.magma.func.operator.binary.*; // NOSONAR
import eu.lunisolar.magma.func.operator.ternary.*; // NOSONAR
import eu.lunisolar.magma.func.operator.unary.*; // NOSONAR
import eu.lunisolar.magma.func.predicate.*; // NOSONAR
import eu.lunisolar.magma.func.supplier.*; // NOSONAR

import org.testng.Assert;
import org.testng.annotations.*;      //NOSONAR
import java.util.regex.Pattern;          //NOSONAR
import java.text.ParseException;         //NOSONAR
import eu.lunisolar.magma.basics.*; //NOSONAR
import eu.lunisolar.magma.basics.exceptions.*; //NOSONAR
import java.util.concurrent.atomic.AtomicInteger; //NOSONAR
import eu.lunisolar.magma.func.tuple.*; // NOSONAR
import java.util.function.*; // NOSONAR

/** The test obviously concentrate on the interface methods the function it self is very simple.  */
public class LBiByteFunctionTest<R> {
    private static final String ORIGINAL_MESSAGE = "Original message";
    private static final String EXCEPTION_WAS_WRAPPED = "Exception was wrapped.";
    private static final String NO_EXCEPTION_WERE_THROWN = "No exception were thrown.";

    private Integer testValue = 100;



    private LBiByteFunction<Integer> sut = new LBiByteFunction<Integer>(){
        public @Nullable Integer applyX(byte a1,byte a2)  {
            return testValue;
        }
    };


    private LBiByteFunction<Integer> sutNull = new LBiByteFunction<Integer>(){
        public @Nullable Integer applyX(byte a1,byte a2)  {
            return null;
        }
    };



    private LBiByteFunction<Integer> sutAlwaysThrowing = LBiByteFunction.biByteFunc((a1,a2) -> {
            throw new ParseException(ORIGINAL_MESSAGE, 0);
    });

    private LBiByteFunction<Integer> sutAlwaysThrowingUnchecked = LBiByteFunction.biByteFunc((a1,a2) -> {
            throw new IndexOutOfBoundsException(ORIGINAL_MESSAGE);
    });


    @Test
    public void testTheResult() throws Throwable {
         Assert.assertEquals(sut.apply((byte)100,(byte)100), testValue);
    }

    @Test
    public void testTupleCall() throws Throwable {

        LBytePair domainObject = Tuple4U.bytePair((byte)100,(byte)100);

        Object result = sut.tupleApply(domainObject);

            Assert.assertEquals(result, testValue);
    }

    @Test
    public void testNonNullApply() throws Throwable {
            Assert.assertSame(sut.nonNullApply((byte)100,(byte)100), testValue);
    }

    @Test
    public void testNestingApplyUnchecked() throws Throwable {

        // then
        try {
            sutAlwaysThrowingUnchecked.nestingApply((byte)100,(byte)100);
            Assert.fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            Assert.assertEquals(e.getClass(), IndexOutOfBoundsException.class);
            Assert.assertNull(e.getCause());
            Assert.assertEquals(e.getMessage(), ORIGINAL_MESSAGE);
        }
    }

    @Test
    public void testShovingApplyUnchecked() throws Throwable {

        // then
        try {
            sutAlwaysThrowingUnchecked.shovingApply((byte)100,(byte)100);
            Assert.fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            Assert.assertEquals(e.getClass(), IndexOutOfBoundsException.class);
            Assert.assertNull(e.getCause());
            Assert.assertEquals(e.getMessage(), ORIGINAL_MESSAGE);
        }
    }

    @Test(expectedExceptions=NullPointerException.class, expectedExceptionsMessageRegExp="\\QEvaluated value by nonNullApply() method cannot be null (LBiByteFunction: R apply(byte a1,byte a2)).\\E")
    public void testNonNullCapturesNull() throws Throwable {
        sutNull.nonNullApply((byte)100,(byte)100);
    }


    @Test
    public void testFunctionalInterfaceDescription() throws Throwable {
        Assert.assertEquals(sut.functionalInterfaceDescription(), "LBiByteFunction: R apply(byte a1,byte a2)");
    }

    @Test
    public void testBiByteFuncMethod() throws Throwable {
        Assert.assertTrue(LBiByteFunction.biByteFunc((a1,a2) -> testValue ) instanceof LBiByteFunction);
    
    }






    // <editor-fold desc="compose (functional)">

    @Test
    public void testCompose() throws Throwable {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LBiByteFunction<Integer> sutO = (a1,a2) -> {
                mainFunctionCalled.set(true);
                Assert.assertEquals(a1, (Object) (byte)90);
                Assert.assertEquals(a2, (Object) (byte)91);
                return 100;
        };

        LByteUnaryOperator before1 = p0 -> {
            Assert.assertEquals(p0, (Object) (byte)80);
            beforeCalls.incrementAndGet();
            return (byte)90;
        };
        LByteUnaryOperator before2 = p1 -> {
            Assert.assertEquals(p1, (Object) (byte)81);
            beforeCalls.incrementAndGet();
            return (byte)91;
        };

        //when
        LBiByteFunction<Integer> function = sutO.compose(before1,before2);
        function.apply((byte)80,(byte)81);

        //then - finals
        Assert.assertTrue(mainFunctionCalled.get());
        Assert.assertEquals(beforeCalls.get(), 2);
    }


    @Test
    public void testUnboxingCompose() throws Throwable {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LBiByteFunction<Integer> sutO = (a1,a2) -> {
                mainFunctionCalled.set(true);
                Assert.assertEquals(a1, (Object) (byte)90);
                Assert.assertEquals(a2, (Object) (byte)91);
                return 100;
        };

        LToByteFunction<Integer> before1 = p0 -> {
            Assert.assertEquals(p0, (Object) 80);
            beforeCalls.incrementAndGet();
            return (byte)90;
        };
        LToByteFunction<Integer> before2 = p1 -> {
            Assert.assertEquals(p1, (Object) 81);
            beforeCalls.incrementAndGet();
            return (byte)91;
        };

        //when
        LBiFunction<Integer,Integer,Integer> function = sutO.unboxingCompose(before1,before2);
        function.apply(80,81);

        //then - finals
        Assert.assertTrue(mainFunctionCalled.get());
        Assert.assertEquals(beforeCalls.get(), 2);
    }

    // </editor-fold>



    // <editor-fold desc="then (functional)">

    @Test
    public void testThen0() throws Throwable  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);

        //given (+ some assertions)
        LBiByteFunction<Integer> sutO = (a1,a2) -> {
                mainFunctionCalled.set(true);
                Assert.assertEquals(a1, (Object) (byte)80);
                Assert.assertEquals(a2, (Object) (byte)81);
                return 90;
        };

        LFunction<Integer,Integer> thenFunction = p -> {
                thenFunctionCalled.set(true);
                // Integer
                Assert.assertEquals(p, (Object) 90);
                // Integer
                return 100;
        };

        //when
        LBiByteFunction<Integer> function = sutO.then(thenFunction);
        Integer finalValue = function.apply((byte)80,(byte)81);

        //then - finals
        Assert.assertEquals(finalValue, (Object) 100);
        Assert.assertTrue(mainFunctionCalled.get());
        Assert.assertTrue(thenFunctionCalled.get());

    }



    @Test
    public void testThenConsume1() throws Throwable  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);

        //given (+ some assertions)
        LBiByteFunction<Integer> sutO = (a1,a2) -> {
                mainFunctionCalled.set(true);
                Assert.assertEquals(a1, (Object) (byte)80);
                Assert.assertEquals(a2, (Object) (byte)81);
                return 90;
        };

        LConsumer<Integer> thenFunction = p -> {
                thenFunctionCalled.set(true);
                // Integer
                Assert.assertEquals(p, (Object) 90);
        };

        //when
        LBiByteConsumer function = sutO.thenConsume(thenFunction);
        function.accept((byte)80,(byte)81);

        //then - finals
        Assert.assertTrue(mainFunctionCalled.get());
        Assert.assertTrue(thenFunctionCalled.get());

    }



    @Test
    public void testThenToByte2() throws Throwable  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);

        //given (+ some assertions)
        LBiByteFunction<Integer> sutO = (a1,a2) -> {
                mainFunctionCalled.set(true);
                Assert.assertEquals(a1, (Object) (byte)80);
                Assert.assertEquals(a2, (Object) (byte)81);
                return 90;
        };

        LToByteFunction<Integer> thenFunction = p -> {
                thenFunctionCalled.set(true);
                // Integer
                Assert.assertEquals(p, (Object) 90);
                // byte
                return (byte)100;
        };

        //when
        LByteBinaryOperator function = sutO.thenToByte(thenFunction);
        byte finalValue = function.applyAsByte((byte)80,(byte)81);

        //then - finals
        Assert.assertEquals(finalValue, (Object) (byte)100);
        Assert.assertTrue(mainFunctionCalled.get());
        Assert.assertTrue(thenFunctionCalled.get());

    }



    @Test
    public void testThenToBool3() throws Throwable  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);

        //given (+ some assertions)
        LBiByteFunction<Integer> sutO = (a1,a2) -> {
                mainFunctionCalled.set(true);
                Assert.assertEquals(a1, (Object) (byte)80);
                Assert.assertEquals(a2, (Object) (byte)81);
                return 90;
        };

        LPredicate<Integer> thenFunction = p -> {
                thenFunctionCalled.set(true);
                // Integer
                Assert.assertEquals(p, (Object) 90);
                // boolean
                return true;
        };

        //when
        LBiBytePredicate function = sutO.thenToBool(thenFunction);
        boolean finalValue = function.test((byte)80,(byte)81);

        //then - finals
        Assert.assertEquals(finalValue, (Object) true);
        Assert.assertTrue(mainFunctionCalled.get());
        Assert.assertTrue(thenFunctionCalled.get());

    }



    // </editor-fold>


    @Test(expectedExceptions = RuntimeException.class)
    public void testShove() {

        // given
        LBiByteFunction<Integer> sutThrowing = LBiByteFunction.biByteFunc((a1,a2) -> {
            throw new UnsupportedOperationException();
        });

        // when
        sutThrowing.shovingApply((byte)100,(byte)100);
    }


    @Test
    public void testToString() throws Throwable {

        Assert.assertTrue(sut.toString().startsWith(this.getClass().getName()+"$"));

        Assert.assertTrue(String.format("%s", sut).contains("LBiByteFunction: R apply(byte a1,byte a2)"));
    
    }


    @Test
    public void isThrowing() {

        Assert.assertFalse(sut.isThrowing());
    }

}
