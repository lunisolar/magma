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
public class LLongToIntFunctionTest {
    private static final String ORIGINAL_MESSAGE = "Original message";
    private static final String EXCEPTION_WAS_WRAPPED = "Exception was wrapped.";
    private static final String NO_EXCEPTION_WERE_THROWN = "No exception were thrown.";

    private int testValue = 100;



    private LLongToIntFunction sut = new LLongToIntFunction(){
        public  int applyAsIntX(long a)  {
            return testValue;
        }
    };



    private LongToIntFunction jre = a -> testValue;


    private LLongToIntFunction sutAlwaysThrowing = LLongToIntFunction.longToIntFunc(a -> {
            throw new ParseException(ORIGINAL_MESSAGE, 0);
    });

    private LLongToIntFunction sutAlwaysThrowingUnchecked = LLongToIntFunction.longToIntFunc(a -> {
            throw new IndexOutOfBoundsException(ORIGINAL_MESSAGE);
    });


    @Test
    public void testTheResult() throws Throwable {
         Assert.assertEquals(sut.applyAsInt(100L), testValue);
    }

    @Test
    public void testTupleCall() throws Throwable {

        LLongSingle domainObject = Tuple4U.longSingle(100L);

        Object result = sut.tupleApplyAsInt(domainObject);

            Assert.assertEquals(result, testValue);
    }

    @Test
    public void testNonNullApplyAsInt() throws Throwable {
            Assert.assertEquals(sut.nonNullApplyAsInt(100L), testValue);
    }

    @Test
    public void testNestingApplyAsIntUnchecked() throws Throwable {

        // then
        try {
            sutAlwaysThrowingUnchecked.nestingApplyAsInt(100L);
            Assert.fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            Assert.assertEquals(e.getClass(), IndexOutOfBoundsException.class);
            Assert.assertNull(e.getCause());
            Assert.assertEquals(e.getMessage(), ORIGINAL_MESSAGE);
        }
    }

    @Test
    public void testShovingApplyAsIntUnchecked() throws Throwable {

        // then
        try {
            sutAlwaysThrowingUnchecked.shovingApplyAsInt(100L);
            Assert.fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            Assert.assertEquals(e.getClass(), IndexOutOfBoundsException.class);
            Assert.assertNull(e.getCause());
            Assert.assertEquals(e.getMessage(), ORIGINAL_MESSAGE);
        }
    }


    @Test
    public void testFunctionalInterfaceDescription() throws Throwable {
        Assert.assertEquals(sut.functionalInterfaceDescription(), "LLongToIntFunction: int applyAsInt(long a)");
    }

    @Test
    public void testLongToIntFuncMethod() throws Throwable {
        Assert.assertTrue(LLongToIntFunction.longToIntFunc(a -> testValue ) instanceof LLongToIntFunction);
    
    }


    @Test
    public void testWrapStdMethod() throws Throwable {
        Assert.assertTrue(LLongToIntFunction.wrap(jre) instanceof LLongToIntFunction);
    }





    // <editor-fold desc="compose (functional)">

    @Test
    public void testCompose() throws Throwable {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LLongToIntFunction sutO = a -> {
                mainFunctionCalled.set(true);
                Assert.assertEquals(a, (Object) 90L);
                return 100;
        };

        LLongUnaryOperator before = p0 -> {
            Assert.assertEquals(p0, (Object) 80L);
            beforeCalls.incrementAndGet();
            return 90L;
        };

        //when
        LLongToIntFunction function = sutO.compose(before);
        function.applyAsInt(80L);

        //then - finals
        Assert.assertTrue(mainFunctionCalled.get());
        Assert.assertEquals(beforeCalls.get(), 1);
    }


    @Test
    public void testLongToIntFuncCompose() throws Throwable {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LLongToIntFunction sutO = a -> {
                mainFunctionCalled.set(true);
                Assert.assertEquals(a, (Object) 90L);
                return 100;
        };

        LToLongFunction<Integer> before = p0 -> {
            Assert.assertEquals(p0, (Object) 80);
            beforeCalls.incrementAndGet();
            return 90L;
        };

        //when
        LToIntFunction<Integer> function = sutO.longToIntFuncCompose(before);
        function.applyAsInt(80);

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
        LLongToIntFunction sutO = a -> {
                mainFunctionCalled.set(true);
                Assert.assertEquals(a, (Object) 80L);
                return 90;
        };

        LIntFunction<Integer> thenFunction = p -> {
                thenFunctionCalled.set(true);
                // int
                Assert.assertEquals(p, (Object) 90);
                // Integer
                return 100;
        };

        //when
        LLongFunction<Integer> function = sutO.then(thenFunction);
        Integer finalValue = function.apply(80L);

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
        LLongToIntFunction sutO = a -> {
                mainFunctionCalled.set(true);
                Assert.assertEquals(a, (Object) 80L);
                return 90;
        };

        LIntToByteFunction thenFunction = p -> {
                thenFunctionCalled.set(true);
                // int
                Assert.assertEquals(p, (Object) 90);
                // byte
                return (byte)100;
        };

        //when
        LLongToByteFunction function = sutO.thenToByte(thenFunction);
        byte finalValue = function.applyAsByte(80L);

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
        LLongToIntFunction sutO = a -> {
                mainFunctionCalled.set(true);
                Assert.assertEquals(a, (Object) 80L);
                return 90;
        };

        LIntToSrtFunction thenFunction = p -> {
                thenFunctionCalled.set(true);
                // int
                Assert.assertEquals(p, (Object) 90);
                // short
                return (short)100;
        };

        //when
        LLongToSrtFunction function = sutO.thenToSrt(thenFunction);
        short finalValue = function.applyAsSrt(80L);

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
        LLongToIntFunction sutO = a -> {
                mainFunctionCalled.set(true);
                Assert.assertEquals(a, (Object) 80L);
                return 90;
        };

        LIntUnaryOperator thenFunction = p -> {
                thenFunctionCalled.set(true);
                // int
                Assert.assertEquals(p, (Object) 90);
                // int
                return 100;
        };

        //when
        LLongToIntFunction function = sutO.thenToInt(thenFunction);
        int finalValue = function.applyAsInt(80L);

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
        LLongToIntFunction sutO = a -> {
                mainFunctionCalled.set(true);
                Assert.assertEquals(a, (Object) 80L);
                return 90;
        };

        LIntToLongFunction thenFunction = p -> {
                thenFunctionCalled.set(true);
                // int
                Assert.assertEquals(p, (Object) 90);
                // long
                return 100L;
        };

        //when
        LLongUnaryOperator function = sutO.thenToLong(thenFunction);
        long finalValue = function.applyAsLong(80L);

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
        LLongToIntFunction sutO = a -> {
                mainFunctionCalled.set(true);
                Assert.assertEquals(a, (Object) 80L);
                return 90;
        };

        LIntToFltFunction thenFunction = p -> {
                thenFunctionCalled.set(true);
                // int
                Assert.assertEquals(p, (Object) 90);
                // float
                return 100f;
        };

        //when
        LLongToFltFunction function = sutO.thenToFlt(thenFunction);
        float finalValue = function.applyAsFlt(80L);

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
        LLongToIntFunction sutO = a -> {
                mainFunctionCalled.set(true);
                Assert.assertEquals(a, (Object) 80L);
                return 90;
        };

        LIntToDblFunction thenFunction = p -> {
                thenFunctionCalled.set(true);
                // int
                Assert.assertEquals(p, (Object) 90);
                // double
                return 100d;
        };

        //when
        LLongToDblFunction function = sutO.thenToDbl(thenFunction);
        double finalValue = function.applyAsDbl(80L);

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
        LLongToIntFunction sutO = a -> {
                mainFunctionCalled.set(true);
                Assert.assertEquals(a, (Object) 80L);
                return 90;
        };

        LIntToCharFunction thenFunction = p -> {
                thenFunctionCalled.set(true);
                // int
                Assert.assertEquals(p, (Object) 90);
                // char
                return '\u0100';
        };

        //when
        LLongToCharFunction function = sutO.thenToChar(thenFunction);
        char finalValue = function.applyAsChar(80L);

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
        LLongToIntFunction sutO = a -> {
                mainFunctionCalled.set(true);
                Assert.assertEquals(a, (Object) 80L);
                return 90;
        };

        LIntPredicate thenFunction = p -> {
                thenFunctionCalled.set(true);
                // int
                Assert.assertEquals(p, (Object) 90);
                // boolean
                return true;
        };

        //when
        LLongPredicate function = sutO.thenToBool(thenFunction);
        boolean finalValue = function.test(80L);

        //then - finals
        Assert.assertEquals(finalValue, (Object) true);
        Assert.assertTrue(mainFunctionCalled.get());
        Assert.assertTrue(thenFunctionCalled.get());

    }



    // </editor-fold>


    @Test(expectedExceptions = RuntimeException.class)
    public void testShove() {

        // given
        LLongToIntFunction sutThrowing = LLongToIntFunction.longToIntFunc(a -> {
            throw new UnsupportedOperationException();
        });

        // when
        sutThrowing.shovingApplyAsInt(100L);
    }


    @Test
    public void testToString() throws Throwable {

        Assert.assertTrue(sut.toString().startsWith(this.getClass().getName()+"$"));

        Assert.assertTrue(String.format("%s", sut).contains("LLongToIntFunction: int applyAsInt(long a)"));
    
    }


    @Test
    public void isThrowing() {

        Assert.assertFalse(sut.isThrowing());
    }

}
