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

package eu.lunisolar.magma.func.function.conversion;

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
public class LByteToLongFunctionTest {
    private static final String ORIGINAL_MESSAGE = "Original message";
    private static final String EXCEPTION_WAS_WRAPPED = "Exception was wrapped.";
    private static final String NO_EXCEPTION_WERE_THROWN = "No exception were thrown.";

    private long testValue = 100L;



    private LByteToLongFunction sut = new LByteToLongFunction(){
        public  long applyAsLongX(byte a)  {
            return testValue;
        }
    };




    private LByteToLongFunction sutAlwaysThrowing = LByteToLongFunction.byteToLongFunc(a -> {
            throw new ParseException(ORIGINAL_MESSAGE, 0);
    });

    private LByteToLongFunction sutAlwaysThrowingUnchecked = LByteToLongFunction.byteToLongFunc(a -> {
            throw new IndexOutOfBoundsException(ORIGINAL_MESSAGE);
    });


    @Test
    public void testTheResult() throws Throwable {
         Assert.assertEquals(sut.applyAsLong((byte)100), testValue);
    }

    @Test
    public void testTupleCall() throws Throwable {

        LByteSingle domainObject = Tuple4U.byteSingle((byte)100);

        Object result = sut.tupleApplyAsLong(domainObject);

            Assert.assertEquals(result, testValue);
    }

    @Test
    public void testNonNullApplyAsLong() throws Throwable {
            Assert.assertEquals(sut.nonNullApplyAsLong((byte)100), testValue);
    }

    @Test
    public void testNestingApplyAsLongUnchecked() throws Throwable {

        // then
        try {
            sutAlwaysThrowingUnchecked.nestingApplyAsLong((byte)100);
            Assert.fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            Assert.assertEquals(e.getClass(), IndexOutOfBoundsException.class);
            Assert.assertNull(e.getCause());
            Assert.assertEquals(e.getMessage(), ORIGINAL_MESSAGE);
        }
    }

    @Test
    public void testShovingApplyAsLongUnchecked() throws Throwable {

        // then
        try {
            sutAlwaysThrowingUnchecked.shovingApplyAsLong((byte)100);
            Assert.fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            Assert.assertEquals(e.getClass(), IndexOutOfBoundsException.class);
            Assert.assertNull(e.getCause());
            Assert.assertEquals(e.getMessage(), ORIGINAL_MESSAGE);
        }
    }


    @Test
    public void testFunctionalInterfaceDescription() throws Throwable {
        Assert.assertEquals(sut.functionalInterfaceDescription(), "LByteToLongFunction: long applyAsLong(byte a)");
    }

    @Test
    public void testByteToLongFuncMethod() throws Throwable {
        Assert.assertTrue(LByteToLongFunction.byteToLongFunc(a -> testValue ) instanceof LByteToLongFunction);
    
    }






    // <editor-fold desc="compose (functional)">

    @Test
    public void testCompose() throws Throwable {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LByteToLongFunction sutO = a -> {
                mainFunctionCalled.set(true);
                Assert.assertEquals(a, (Object) (byte)90);
                return 100L;
        };

        LByteUnaryOperator before = p0 -> {
            Assert.assertEquals(p0, (Object) (byte)80);
            beforeCalls.incrementAndGet();
            return (byte)90;
        };

        //when
        LByteToLongFunction function = sutO.compose(before);
        function.applyAsLong((byte)80);

        //then - finals
        Assert.assertTrue(mainFunctionCalled.get());
        Assert.assertEquals(beforeCalls.get(), 1);
    }


    @Test
    public void testUnboxingCompose() throws Throwable {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LByteToLongFunction sutO = a -> {
                mainFunctionCalled.set(true);
                Assert.assertEquals(a, (Object) (byte)90);
                return 100L;
        };

        LToByteFunction<Integer> before = p0 -> {
            Assert.assertEquals(p0, (Object) 80);
            beforeCalls.incrementAndGet();
            return (byte)90;
        };

        //when
        LToLongFunction<Integer> function = sutO.unboxingCompose(before);
        function.applyAsLong(80);

        //then - finals
        Assert.assertTrue(mainFunctionCalled.get());
        Assert.assertEquals(beforeCalls.get(), 1);
    }

    // </editor-fold>



    // <editor-fold desc="then (functional)">

    @Test
    public void testThen0() throws Throwable  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);

        //given (+ some assertions)
        LByteToLongFunction sutO = a -> {
                mainFunctionCalled.set(true);
                Assert.assertEquals(a, (Object) (byte)80);
                return 90L;
        };

        LLongFunction<Integer> thenFunction = p -> {
                thenFunctionCalled.set(true);
                // long
                Assert.assertEquals(p, (Object) 90L);
                // Integer
                return 100;
        };

        //when
        LByteFunction<Integer> function = sutO.then(thenFunction);
        Integer finalValue = function.apply((byte)80);

        //then - finals
        Assert.assertEquals(finalValue, (Object) 100);
        Assert.assertTrue(mainFunctionCalled.get());
        Assert.assertTrue(thenFunctionCalled.get());

    }



    @Test
    public void testThenToByte1() throws Throwable  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);

        //given (+ some assertions)
        LByteToLongFunction sutO = a -> {
                mainFunctionCalled.set(true);
                Assert.assertEquals(a, (Object) (byte)80);
                return 90L;
        };

        LLongToByteFunction thenFunction = p -> {
                thenFunctionCalled.set(true);
                // long
                Assert.assertEquals(p, (Object) 90L);
                // byte
                return (byte)100;
        };

        //when
        LByteUnaryOperator function = sutO.thenToByte(thenFunction);
        byte finalValue = function.applyAsByte((byte)80);

        //then - finals
        Assert.assertEquals(finalValue, (Object) (byte)100);
        Assert.assertTrue(mainFunctionCalled.get());
        Assert.assertTrue(thenFunctionCalled.get());

    }



    @Test
    public void testThenToSrt2() throws Throwable  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);

        //given (+ some assertions)
        LByteToLongFunction sutO = a -> {
                mainFunctionCalled.set(true);
                Assert.assertEquals(a, (Object) (byte)80);
                return 90L;
        };

        LLongToSrtFunction thenFunction = p -> {
                thenFunctionCalled.set(true);
                // long
                Assert.assertEquals(p, (Object) 90L);
                // short
                return (short)100;
        };

        //when
        LByteToSrtFunction function = sutO.thenToSrt(thenFunction);
        short finalValue = function.applyAsSrt((byte)80);

        //then - finals
        Assert.assertEquals(finalValue, (Object) (short)100);
        Assert.assertTrue(mainFunctionCalled.get());
        Assert.assertTrue(thenFunctionCalled.get());

    }



    @Test
    public void testThenToInt3() throws Throwable  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);

        //given (+ some assertions)
        LByteToLongFunction sutO = a -> {
                mainFunctionCalled.set(true);
                Assert.assertEquals(a, (Object) (byte)80);
                return 90L;
        };

        LLongToIntFunction thenFunction = p -> {
                thenFunctionCalled.set(true);
                // long
                Assert.assertEquals(p, (Object) 90L);
                // int
                return 100;
        };

        //when
        LByteToIntFunction function = sutO.thenToInt(thenFunction);
        int finalValue = function.applyAsInt((byte)80);

        //then - finals
        Assert.assertEquals(finalValue, (Object) 100);
        Assert.assertTrue(mainFunctionCalled.get());
        Assert.assertTrue(thenFunctionCalled.get());

    }



    @Test
    public void testThenToLong4() throws Throwable  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);

        //given (+ some assertions)
        LByteToLongFunction sutO = a -> {
                mainFunctionCalled.set(true);
                Assert.assertEquals(a, (Object) (byte)80);
                return 90L;
        };

        LLongUnaryOperator thenFunction = p -> {
                thenFunctionCalled.set(true);
                // long
                Assert.assertEquals(p, (Object) 90L);
                // long
                return 100L;
        };

        //when
        LByteToLongFunction function = sutO.thenToLong(thenFunction);
        long finalValue = function.applyAsLong((byte)80);

        //then - finals
        Assert.assertEquals(finalValue, (Object) 100L);
        Assert.assertTrue(mainFunctionCalled.get());
        Assert.assertTrue(thenFunctionCalled.get());

    }



    @Test
    public void testThenToFlt5() throws Throwable  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);

        //given (+ some assertions)
        LByteToLongFunction sutO = a -> {
                mainFunctionCalled.set(true);
                Assert.assertEquals(a, (Object) (byte)80);
                return 90L;
        };

        LLongToFltFunction thenFunction = p -> {
                thenFunctionCalled.set(true);
                // long
                Assert.assertEquals(p, (Object) 90L);
                // float
                return 100f;
        };

        //when
        LByteToFltFunction function = sutO.thenToFlt(thenFunction);
        float finalValue = function.applyAsFlt((byte)80);

        //then - finals
        Assert.assertEquals(finalValue, (Object) 100f);
        Assert.assertTrue(mainFunctionCalled.get());
        Assert.assertTrue(thenFunctionCalled.get());

    }



    @Test
    public void testThenToDbl6() throws Throwable  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);

        //given (+ some assertions)
        LByteToLongFunction sutO = a -> {
                mainFunctionCalled.set(true);
                Assert.assertEquals(a, (Object) (byte)80);
                return 90L;
        };

        LLongToDblFunction thenFunction = p -> {
                thenFunctionCalled.set(true);
                // long
                Assert.assertEquals(p, (Object) 90L);
                // double
                return 100d;
        };

        //when
        LByteToDblFunction function = sutO.thenToDbl(thenFunction);
        double finalValue = function.applyAsDbl((byte)80);

        //then - finals
        Assert.assertEquals(finalValue, (Object) 100d);
        Assert.assertTrue(mainFunctionCalled.get());
        Assert.assertTrue(thenFunctionCalled.get());

    }



    @Test
    public void testThenToChar7() throws Throwable  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);

        //given (+ some assertions)
        LByteToLongFunction sutO = a -> {
                mainFunctionCalled.set(true);
                Assert.assertEquals(a, (Object) (byte)80);
                return 90L;
        };

        LLongToCharFunction thenFunction = p -> {
                thenFunctionCalled.set(true);
                // long
                Assert.assertEquals(p, (Object) 90L);
                // char
                return '\u0100';
        };

        //when
        LByteToCharFunction function = sutO.thenToChar(thenFunction);
        char finalValue = function.applyAsChar((byte)80);

        //then - finals
        Assert.assertEquals(finalValue, (Object) '\u0100');
        Assert.assertTrue(mainFunctionCalled.get());
        Assert.assertTrue(thenFunctionCalled.get());

    }



    @Test
    public void testThenToBool8() throws Throwable  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);

        //given (+ some assertions)
        LByteToLongFunction sutO = a -> {
                mainFunctionCalled.set(true);
                Assert.assertEquals(a, (Object) (byte)80);
                return 90L;
        };

        LLongPredicate thenFunction = p -> {
                thenFunctionCalled.set(true);
                // long
                Assert.assertEquals(p, (Object) 90L);
                // boolean
                return true;
        };

        //when
        LBytePredicate function = sutO.thenToBool(thenFunction);
        boolean finalValue = function.test((byte)80);

        //then - finals
        Assert.assertEquals(finalValue, (Object) true);
        Assert.assertTrue(mainFunctionCalled.get());
        Assert.assertTrue(thenFunctionCalled.get());

    }



    // </editor-fold>


    @Test(expectedExceptions = RuntimeException.class)
    public void testShove() {

        // given
        LByteToLongFunction sutThrowing = LByteToLongFunction.byteToLongFunc(a -> {
            throw new UnsupportedOperationException();
        });

        // when
        sutThrowing.shovingApplyAsLong((byte)100);
    }


    @Test
    public void testToString() throws Throwable {

        Assert.assertTrue(sut.toString().startsWith(this.getClass().getName()+"$"));

        Assert.assertTrue(String.format("%s", sut).contains("LByteToLongFunction: long applyAsLong(byte a)"));
    
    }


    @Test
    public void isThrowing() {

        Assert.assertFalse(sut.isThrowing());
    }

}
