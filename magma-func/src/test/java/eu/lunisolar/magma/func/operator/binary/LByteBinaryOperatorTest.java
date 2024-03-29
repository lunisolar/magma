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

package eu.lunisolar.magma.func.operator.binary;

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
public class LByteBinaryOperatorTest {
    private static final String ORIGINAL_MESSAGE = "Original message";
    private static final String EXCEPTION_WAS_WRAPPED = "Exception was wrapped.";
    private static final String NO_EXCEPTION_WERE_THROWN = "No exception were thrown.";

    private byte testValue = (byte)100;



    private LByteBinaryOperator sut = new LByteBinaryOperator(){
        public  byte applyAsByteX(byte a1,byte a2)  {
            return testValue;
        }
    };




    private LByteBinaryOperator sutAlwaysThrowing = LByteBinaryOperator.byteBinaryOp((a1,a2) -> {
            throw new ParseException(ORIGINAL_MESSAGE, 0);
    });

    private LByteBinaryOperator sutAlwaysThrowingUnchecked = LByteBinaryOperator.byteBinaryOp((a1,a2) -> {
            throw new IndexOutOfBoundsException(ORIGINAL_MESSAGE);
    });


    @Test
    public void testTheResult() throws Throwable {
         Assert.assertEquals(sut.applyAsByte((byte)100,(byte)100), testValue);
    }

    @Test
    public void testTupleCall() throws Throwable {

        LBytePair domainObject = Tuple4U.bytePair((byte)100,(byte)100);

        Object result = sut.tupleApplyAsByte(domainObject);

            Assert.assertEquals(result, testValue);
    }

    @Test
    public void testNonNullApplyAsByte() throws Throwable {
            Assert.assertEquals(sut.nonNullApplyAsByte((byte)100,(byte)100), testValue);
    }

    @Test
    public void testNestingApplyAsByteUnchecked() throws Throwable {

        // then
        try {
            sutAlwaysThrowingUnchecked.nestingApplyAsByte((byte)100,(byte)100);
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
            sutAlwaysThrowingUnchecked.shovingApplyAsByte((byte)100,(byte)100);
            Assert.fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            Assert.assertEquals(e.getClass(), IndexOutOfBoundsException.class);
            Assert.assertNull(e.getCause());
            Assert.assertEquals(e.getMessage(), ORIGINAL_MESSAGE);
        }
    }


    @Test
    public void testFunctionalInterfaceDescription() throws Throwable {
        Assert.assertEquals(sut.functionalInterfaceDescription(), "LByteBinaryOperator: byte applyAsByte(byte a1,byte a2)");
    }

    @Test
    public void testByteBinaryOpMethod() throws Throwable {
        Assert.assertTrue(LByteBinaryOperator.byteBinaryOp((a1,a2) -> testValue ) instanceof LByteBinaryOperator);
    
    }





    @Test
    public void minBy() throws Throwable  {
        //when
        LByteBinaryOperator min =  LByteBinaryOperator.minBy(Byte::compare);

        //then
        Assert.assertEquals(min.applyAsByte((byte)0, (byte)56), (Object) (byte)0);
        Assert.assertEquals(min.applyAsByte((byte)56, (byte)0), (Object) (byte)0);

    }

    @Test
    public void maxBy() throws Throwable  {
        //when
        LByteBinaryOperator max =  LByteBinaryOperator.maxBy(Byte::compare);

        //then
        Assert.assertEquals(max.applyAsByte((byte)0, (byte)56), (Object) (byte)56);
        Assert.assertEquals(max.applyAsByte((byte)56, (byte)0), (Object) (byte)56);
    }


    @Test
    public void testMin() throws Throwable {
        //given
        byte valueSmall = (byte)10;
        byte valueBig = (byte)100;

        //when
        LByteBinaryOperator min = LByteBinaryOperator.min();

        //then
        Assert.assertEquals(min.applyAsByte(valueSmall, valueBig), valueSmall);

        Assert.assertEquals(min.applyAsByte(valueBig, valueSmall), valueSmall);
    }

    @Test
    public void testMax() throws Throwable {
        //given
        byte valueSmall = (byte)10;
        byte valueBig = (byte)100;

        //when
        LByteBinaryOperator max = LByteBinaryOperator.max();

        //then
        Assert.assertEquals(max.applyAsByte(valueSmall, valueBig), valueBig);

        Assert.assertEquals(max.applyAsByte(valueBig, valueSmall), valueBig);
    }


    // <editor-fold desc="compose (functional)">

    @Test
    public void testCompose() throws Throwable {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LByteBinaryOperator sutO = (a1,a2) -> {
                mainFunctionCalled.set(true);
                Assert.assertEquals(a1, (Object) (byte)90);
                Assert.assertEquals(a2, (Object) (byte)91);
                return (byte)100;
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
        LByteBinaryOperator function = sutO.compose(before1,before2);
        function.applyAsByte((byte)80,(byte)81);

        //then - finals
        Assert.assertTrue(mainFunctionCalled.get());
        Assert.assertEquals(beforeCalls.get(), 2);
    }


    @Test
    public void testUnboxingCompose() throws Throwable {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LByteBinaryOperator sutO = (a1,a2) -> {
                mainFunctionCalled.set(true);
                Assert.assertEquals(a1, (Object) (byte)90);
                Assert.assertEquals(a2, (Object) (byte)91);
                return (byte)100;
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
        LToByteBiFunction<Integer,Integer> function = sutO.unboxingCompose(before1,before2);
        function.applyAsByte(80,81);

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
        LByteBinaryOperator sutO = (a1,a2) -> {
                mainFunctionCalled.set(true);
                Assert.assertEquals(a1, (Object) (byte)80);
                Assert.assertEquals(a2, (Object) (byte)81);
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
        LBiByteFunction<Integer> function = sutO.then(thenFunction);
        Integer finalValue = function.apply((byte)80,(byte)81);

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
        LByteBinaryOperator sutO = (a1,a2) -> {
                mainFunctionCalled.set(true);
                Assert.assertEquals(a1, (Object) (byte)80);
                Assert.assertEquals(a2, (Object) (byte)81);
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
        LByteBinaryOperator function = sutO.thenToByte(thenFunction);
        byte finalValue = function.applyAsByte((byte)80,(byte)81);

        //then - finals
        Assert.assertEquals(finalValue, (Object) (byte)100);
        Assert.assertTrue(mainFunctionCalled.get());
        Assert.assertTrue(thenFunctionCalled.get());

    }



    @Test
    public void testThenToBool2() throws Throwable  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);

        //given (+ some assertions)
        LByteBinaryOperator sutO = (a1,a2) -> {
                mainFunctionCalled.set(true);
                Assert.assertEquals(a1, (Object) (byte)80);
                Assert.assertEquals(a2, (Object) (byte)81);
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
        LByteBinaryOperator sutThrowing = LByteBinaryOperator.byteBinaryOp((a1,a2) -> {
            throw new UnsupportedOperationException();
        });

        // when
        sutThrowing.shovingApplyAsByte((byte)100,(byte)100);
    }


    @Test
    public void testToString() throws Throwable {

        Assert.assertTrue(sut.toString().startsWith(this.getClass().getName()+"$"));

        Assert.assertTrue(String.format("%s", sut).contains("LByteBinaryOperator: byte applyAsByte(byte a1,byte a2)"));
    
    }


    @Test
    public void isThrowing() {

        Assert.assertFalse(sut.isThrowing());
    }

}
