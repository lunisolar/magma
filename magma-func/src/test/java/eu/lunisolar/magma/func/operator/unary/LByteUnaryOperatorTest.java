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

package eu.lunisolar.magma.func.operator.unary;

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
public class LByteUnaryOperatorTest {
    private static final String ORIGINAL_MESSAGE = "Original message";
    private static final String EXCEPTION_WAS_WRAPPED = "Exception was wrapped.";
    private static final String NO_EXCEPTION_WERE_THROWN = "No exception were thrown.";

    private byte testValue = (byte)100;



    private LByteUnaryOperator sut = new LByteUnaryOperator(){
        public  byte applyAsByteX(byte a)  {
            return testValue;
        }
    };




    private LByteUnaryOperator sutAlwaysThrowing = LByteUnaryOperator.byteUnaryOp(a -> {
            throw new ParseException(ORIGINAL_MESSAGE, 0);
    });

    private LByteUnaryOperator sutAlwaysThrowingUnchecked = LByteUnaryOperator.byteUnaryOp(a -> {
            throw new IndexOutOfBoundsException(ORIGINAL_MESSAGE);
    });


    @Test
    public void testTheResult() throws Throwable {
         Assert.assertEquals(sut.applyAsByte((byte)100), testValue);
    }

    @Test
    public void testTupleCall() throws Throwable {

        LByteSingle domainObject = Tuple4U.byteSingle((byte)100);

        Object result = sut.tupleApplyAsByte(domainObject);

            Assert.assertEquals(result, testValue);
    }

    @Test
    public void testNonNullApplyAsByte() throws Throwable {
            Assert.assertEquals(sut.nonNullApplyAsByte((byte)100), testValue);
    }

    @Test
    public void testNestingApplyAsByteUnchecked() throws Throwable {

        // then
        try {
            sutAlwaysThrowingUnchecked.nestingApplyAsByte((byte)100);
            Assert.fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            Assert.assertEquals(e.getClass(), IndexOutOfBoundsException.class);
            Assert.assertNull(e.getCause());
            Assert.assertEquals(e.getMessage(), ORIGINAL_MESSAGE);
        }
    }

    @Test
    public void testShovingApplyAsByteUnchecked() throws Throwable {

        // then
        try {
            sutAlwaysThrowingUnchecked.shovingApplyAsByte((byte)100);
            Assert.fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            Assert.assertEquals(e.getClass(), IndexOutOfBoundsException.class);
            Assert.assertNull(e.getCause());
            Assert.assertEquals(e.getMessage(), ORIGINAL_MESSAGE);
        }
    }


    @Test
    public void testFunctionalInterfaceDescription() throws Throwable {
        Assert.assertEquals(sut.functionalInterfaceDescription(), "LByteUnaryOperator: byte applyAsByte(byte a)");
    }

    @Test
    public void testByteUnaryOpMethod() throws Throwable {
        Assert.assertTrue(LByteUnaryOperator.byteUnaryOp(a -> testValue ) instanceof LByteUnaryOperator);
    
    }






    // <editor-fold desc="compose (functional)">

    @Test
    public void testCompose() throws Throwable {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LByteUnaryOperator sutO = a -> {
                mainFunctionCalled.set(true);
                Assert.assertEquals(a, (Object) (byte)90);
                return (byte)100;
        };

        LByteUnaryOperator before = p0 -> {
            Assert.assertEquals(p0, (Object) (byte)80);
            beforeCalls.incrementAndGet();
            return (byte)90;
        };

        //when
        LByteUnaryOperator function = sutO.compose(before);
        function.applyAsByte((byte)80);

        //then - finals
        Assert.assertTrue(mainFunctionCalled.get());
        Assert.assertEquals(beforeCalls.get(), 1);
    }


    @Test
    public void testUnboxingCompose() throws Throwable {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LByteUnaryOperator sutO = a -> {
                mainFunctionCalled.set(true);
                Assert.assertEquals(a, (Object) (byte)90);
                return (byte)100;
        };

        LToByteFunction<Integer> before = p0 -> {
            Assert.assertEquals(p0, (Object) 80);
            beforeCalls.incrementAndGet();
            return (byte)90;
        };

        //when
        LToByteFunction<Integer> function = sutO.unboxingCompose(before);
        function.applyAsByte(80);

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
        LByteUnaryOperator sutO = a -> {
                mainFunctionCalled.set(true);
                Assert.assertEquals(a, (Object) (byte)80);
                return (byte)90;
        };

        LByteFunction<Integer> thenFunction = p -> {
                thenFunctionCalled.set(true);
                // byte
                Assert.assertEquals(p, (Object) (byte)90);
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
        LByteUnaryOperator sutO = a -> {
                mainFunctionCalled.set(true);
                Assert.assertEquals(a, (Object) (byte)80);
                return (byte)90;
        };

        LByteUnaryOperator thenFunction = p -> {
                thenFunctionCalled.set(true);
                // byte
                Assert.assertEquals(p, (Object) (byte)90);
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
        LByteUnaryOperator sutO = a -> {
                mainFunctionCalled.set(true);
                Assert.assertEquals(a, (Object) (byte)80);
                return (byte)90;
        };

        LByteToSrtFunction thenFunction = p -> {
                thenFunctionCalled.set(true);
                // byte
                Assert.assertEquals(p, (Object) (byte)90);
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
        LByteUnaryOperator sutO = a -> {
                mainFunctionCalled.set(true);
                Assert.assertEquals(a, (Object) (byte)80);
                return (byte)90;
        };

        LByteToIntFunction thenFunction = p -> {
                thenFunctionCalled.set(true);
                // byte
                Assert.assertEquals(p, (Object) (byte)90);
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
        LByteUnaryOperator sutO = a -> {
                mainFunctionCalled.set(true);
                Assert.assertEquals(a, (Object) (byte)80);
                return (byte)90;
        };

        LByteToLongFunction thenFunction = p -> {
                thenFunctionCalled.set(true);
                // byte
                Assert.assertEquals(p, (Object) (byte)90);
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
        LByteUnaryOperator sutO = a -> {
                mainFunctionCalled.set(true);
                Assert.assertEquals(a, (Object) (byte)80);
                return (byte)90;
        };

        LByteToFltFunction thenFunction = p -> {
                thenFunctionCalled.set(true);
                // byte
                Assert.assertEquals(p, (Object) (byte)90);
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
        LByteUnaryOperator sutO = a -> {
                mainFunctionCalled.set(true);
                Assert.assertEquals(a, (Object) (byte)80);
                return (byte)90;
        };

        LByteToDblFunction thenFunction = p -> {
                thenFunctionCalled.set(true);
                // byte
                Assert.assertEquals(p, (Object) (byte)90);
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
        LByteUnaryOperator sutO = a -> {
                mainFunctionCalled.set(true);
                Assert.assertEquals(a, (Object) (byte)80);
                return (byte)90;
        };

        LByteToCharFunction thenFunction = p -> {
                thenFunctionCalled.set(true);
                // byte
                Assert.assertEquals(p, (Object) (byte)90);
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
        LByteUnaryOperator sutO = a -> {
                mainFunctionCalled.set(true);
                Assert.assertEquals(a, (Object) (byte)80);
                return (byte)90;
        };

        LBytePredicate thenFunction = p -> {
                thenFunctionCalled.set(true);
                // byte
                Assert.assertEquals(p, (Object) (byte)90);
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
    @Test
    public void identity() throws Throwable {
        LByteUnaryOperator identityFunction = LByteUnaryOperator.identity();
        Assert.assertEquals(identityFunction.applyAsByte((byte)8), (Object) (byte)8);
    }



    @Test(expectedExceptions = RuntimeException.class)
    public void testShove() {

        // given
        LByteUnaryOperator sutThrowing = LByteUnaryOperator.byteUnaryOp(a -> {
            throw new UnsupportedOperationException();
        });

        // when
        sutThrowing.shovingApplyAsByte((byte)100);
    }


    @Test
    public void testToString() throws Throwable {

        Assert.assertTrue(sut.toString().startsWith(this.getClass().getName()+"$"));

        Assert.assertTrue(String.format("%s", sut).contains("LByteUnaryOperator: byte applyAsByte(byte a)"));
    
    }


    @Test
    public void isThrowing() {

        Assert.assertFalse(sut.isThrowing());
    }

}
