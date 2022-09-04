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

package eu.lunisolar.magma.func.predicate;

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
public class LFltPredicateTest {
    private static final String ORIGINAL_MESSAGE = "Original message";
    private static final String EXCEPTION_WAS_WRAPPED = "Exception was wrapped.";
    private static final String NO_EXCEPTION_WERE_THROWN = "No exception were thrown.";

    private boolean testValue = true;



    private LFltPredicate sut = new LFltPredicate(){
        public  boolean testX(float a)  {
            return testValue;
        }
    };




    private LFltPredicate sutAlwaysThrowing = LFltPredicate.fltPred(a -> {
            throw new ParseException(ORIGINAL_MESSAGE, 0);
    });

    private LFltPredicate sutAlwaysThrowingUnchecked = LFltPredicate.fltPred(a -> {
            throw new IndexOutOfBoundsException(ORIGINAL_MESSAGE);
    });


    @Test
    public void testTheResult() throws Throwable {
         Assert.assertEquals(sut.test(100f), testValue);
    }

    @Test
    public void testTupleCall() throws Throwable {

        LFltSingle domainObject = Tuple4U.fltSingle(100f);

        Object result = sut.tupleTest(domainObject);

            Assert.assertEquals(result, testValue);
    }

    @Test
    public void testNonNullTest() throws Throwable {
            Assert.assertEquals(sut.nonNullTest(100f), testValue);
    }

    @Test
    public void testNestingTestUnchecked() throws Throwable {

        // then
        try {
            sutAlwaysThrowingUnchecked.nestingTest(100f);
            Assert.fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            Assert.assertEquals(e.getClass(), IndexOutOfBoundsException.class);
            Assert.assertNull(e.getCause());
            Assert.assertEquals(e.getMessage(), ORIGINAL_MESSAGE);
        }
    }

    @Test
    public void testShovingTestUnchecked() throws Throwable {

        // then
        try {
            sutAlwaysThrowingUnchecked.shovingTest(100f);
            Assert.fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            Assert.assertEquals(e.getClass(), IndexOutOfBoundsException.class);
            Assert.assertNull(e.getCause());
            Assert.assertEquals(e.getMessage(), ORIGINAL_MESSAGE);
        }
    }

    @Test
    public void testApplyAsBooleanShouldNotModifyValue() throws Throwable {
        Assert.assertEquals(sut.doApplyAsBoolean(100f), testValue);
    }


    @Test
    public void testFunctionalInterfaceDescription() throws Throwable {
        Assert.assertEquals(sut.functionalInterfaceDescription(), "LFltPredicate: boolean test(float a)");
    }

    @Test
    public void testFltPredMethod() throws Throwable {
        Assert.assertTrue(LFltPredicate.fltPred(a -> testValue ) instanceof LFltPredicate);
    
    }




    @Test
    public void testnegate() throws Throwable {
        Assert.assertEquals(sut.negate().test(100f), !testValue);
    }

    @DataProvider(name="boolean permutations")
    public Object[][] permuations() {
       return new Object[][] {
            // b1 , b2   , AND  , OR   , XOR
            {false, false, false, false, false },
            {true , false, false, true , true },
            {false, true , false, true , true },
            {true , true , true , true , false },
       };
    }

    @Test(dataProvider="boolean permutations")
    public void testAndOrXor(final boolean f1Result, final boolean f2Result, final boolean andResult, final boolean orResult, final boolean xorResult) throws Throwable {

        //given
        LFltPredicate fun1 = LFltPredicate.fltPred(a -> f1Result);
        LFltPredicate fun2 = LFltPredicate.fltPred(a -> f2Result);

        //when
        LFltPredicate andFunction = fun1.and(fun2);
        LFltPredicate orFunction = fun1.or(fun2);
        LFltPredicate xorFunction = fun1.xor(fun2);

        //then
        Assert.assertEquals(andFunction.test(100f), andResult);

        Assert.assertEquals(orFunction.test(100f), orResult);

        Assert.assertEquals(xorFunction.test(100f), xorResult);
    }

    @Test
    public void testIsEqual() throws Throwable  {
        //when
        LFltPredicate equals = LFltPredicate.isEqual(1f);

        //then
        Assert.assertTrue(equals.test(1f));

        Assert.assertFalse(equals.test(0f));
    }



    // <editor-fold desc="compose (functional)">

    @Test
    public void testCompose() throws Throwable {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LFltPredicate sutO = a -> {
                mainFunctionCalled.set(true);
                Assert.assertEquals(a, (Object) 90f);
                return true;
        };

        LFltUnaryOperator before = p0 -> {
            Assert.assertEquals(p0, (Object) 80f);
            beforeCalls.incrementAndGet();
            return 90f;
        };

        //when
        LFltPredicate function = sutO.compose(before);
        function.test(80f);

        //then - finals
        Assert.assertTrue(mainFunctionCalled.get());
        Assert.assertEquals(beforeCalls.get(), 1);
    }


    @Test
    public void testUnboxingCompose() throws Throwable {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LFltPredicate sutO = a -> {
                mainFunctionCalled.set(true);
                Assert.assertEquals(a, (Object) 90f);
                return true;
        };

        LToFltFunction<Integer> before = p0 -> {
            Assert.assertEquals(p0, (Object) 80);
            beforeCalls.incrementAndGet();
            return 90f;
        };

        //when
        LPredicate<Integer> function = sutO.unboxingCompose(before);
        function.test(80);

        //then - finals
        Assert.assertTrue(mainFunctionCalled.get());
        Assert.assertEquals(beforeCalls.get(), 1);
    }

    // </editor-fold>



    // <editor-fold desc="then (functional)">

    @Test
    public void testBoolToFltFunc0() throws Throwable  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);

        //given (+ some assertions)
        LFltPredicate sutO = a -> {
                mainFunctionCalled.set(true);
                Assert.assertEquals(a, (Object) 80f);
                return true;
        };

        LBoolFunction<Integer> thenFunction = p -> {
                thenFunctionCalled.set(true);
                // boolean
                Assert.assertEquals(p, (Object) true);
                // Integer
                return 100;
        };

        //when
        LFltFunction<Integer> function = sutO.boolToFltFunc(thenFunction);
        Integer finalValue = function.apply(80f);

        //then - finals
        Assert.assertEquals(finalValue, (Object) 100);
        Assert.assertTrue(mainFunctionCalled.get());
        Assert.assertTrue(thenFunctionCalled.get());

    }



    @Test
    public void testBoolToFltToByteFunc1() throws Throwable  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);

        //given (+ some assertions)
        LFltPredicate sutO = a -> {
                mainFunctionCalled.set(true);
                Assert.assertEquals(a, (Object) 80f);
                return true;
        };

        LBoolToByteFunction thenFunction = p -> {
                thenFunctionCalled.set(true);
                // boolean
                Assert.assertEquals(p, (Object) true);
                // byte
                return (byte)100;
        };

        //when
        LFltToByteFunction function = sutO.boolToFltToByteFunc(thenFunction);
        byte finalValue = function.applyAsByte(80f);

        //then - finals
        Assert.assertEquals(finalValue, (Object) (byte)100);
        Assert.assertTrue(mainFunctionCalled.get());
        Assert.assertTrue(thenFunctionCalled.get());

    }



    @Test
    public void testBoolToFltToSrtFunc2() throws Throwable  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);

        //given (+ some assertions)
        LFltPredicate sutO = a -> {
                mainFunctionCalled.set(true);
                Assert.assertEquals(a, (Object) 80f);
                return true;
        };

        LBoolToSrtFunction thenFunction = p -> {
                thenFunctionCalled.set(true);
                // boolean
                Assert.assertEquals(p, (Object) true);
                // short
                return (short)100;
        };

        //when
        LFltToSrtFunction function = sutO.boolToFltToSrtFunc(thenFunction);
        short finalValue = function.applyAsSrt(80f);

        //then - finals
        Assert.assertEquals(finalValue, (Object) (short)100);
        Assert.assertTrue(mainFunctionCalled.get());
        Assert.assertTrue(thenFunctionCalled.get());

    }



    @Test
    public void testBoolToFltToIntFunc3() throws Throwable  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);

        //given (+ some assertions)
        LFltPredicate sutO = a -> {
                mainFunctionCalled.set(true);
                Assert.assertEquals(a, (Object) 80f);
                return true;
        };

        LBoolToIntFunction thenFunction = p -> {
                thenFunctionCalled.set(true);
                // boolean
                Assert.assertEquals(p, (Object) true);
                // int
                return 100;
        };

        //when
        LFltToIntFunction function = sutO.boolToFltToIntFunc(thenFunction);
        int finalValue = function.applyAsInt(80f);

        //then - finals
        Assert.assertEquals(finalValue, (Object) 100);
        Assert.assertTrue(mainFunctionCalled.get());
        Assert.assertTrue(thenFunctionCalled.get());

    }



    @Test
    public void testBoolToFltToLongFunc4() throws Throwable  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);

        //given (+ some assertions)
        LFltPredicate sutO = a -> {
                mainFunctionCalled.set(true);
                Assert.assertEquals(a, (Object) 80f);
                return true;
        };

        LBoolToLongFunction thenFunction = p -> {
                thenFunctionCalled.set(true);
                // boolean
                Assert.assertEquals(p, (Object) true);
                // long
                return 100L;
        };

        //when
        LFltToLongFunction function = sutO.boolToFltToLongFunc(thenFunction);
        long finalValue = function.applyAsLong(80f);

        //then - finals
        Assert.assertEquals(finalValue, (Object) 100L);
        Assert.assertTrue(mainFunctionCalled.get());
        Assert.assertTrue(thenFunctionCalled.get());

    }



    @Test
    public void testBoolToFltUnaryOp5() throws Throwable  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);

        //given (+ some assertions)
        LFltPredicate sutO = a -> {
                mainFunctionCalled.set(true);
                Assert.assertEquals(a, (Object) 80f);
                return true;
        };

        LBoolToFltFunction thenFunction = p -> {
                thenFunctionCalled.set(true);
                // boolean
                Assert.assertEquals(p, (Object) true);
                // float
                return 100f;
        };

        //when
        LFltUnaryOperator function = sutO.boolToFltUnaryOp(thenFunction);
        float finalValue = function.applyAsFlt(80f);

        //then - finals
        Assert.assertEquals(finalValue, (Object) 100f);
        Assert.assertTrue(mainFunctionCalled.get());
        Assert.assertTrue(thenFunctionCalled.get());

    }



    @Test
    public void testBoolToFltToDblFunc6() throws Throwable  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);

        //given (+ some assertions)
        LFltPredicate sutO = a -> {
                mainFunctionCalled.set(true);
                Assert.assertEquals(a, (Object) 80f);
                return true;
        };

        LBoolToDblFunction thenFunction = p -> {
                thenFunctionCalled.set(true);
                // boolean
                Assert.assertEquals(p, (Object) true);
                // double
                return 100d;
        };

        //when
        LFltToDblFunction function = sutO.boolToFltToDblFunc(thenFunction);
        double finalValue = function.applyAsDbl(80f);

        //then - finals
        Assert.assertEquals(finalValue, (Object) 100d);
        Assert.assertTrue(mainFunctionCalled.get());
        Assert.assertTrue(thenFunctionCalled.get());

    }



    @Test
    public void testBoolToFltToCharFunc7() throws Throwable  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);

        //given (+ some assertions)
        LFltPredicate sutO = a -> {
                mainFunctionCalled.set(true);
                Assert.assertEquals(a, (Object) 80f);
                return true;
        };

        LBoolToCharFunction thenFunction = p -> {
                thenFunctionCalled.set(true);
                // boolean
                Assert.assertEquals(p, (Object) true);
                // char
                return '\u0100';
        };

        //when
        LFltToCharFunction function = sutO.boolToFltToCharFunc(thenFunction);
        char finalValue = function.applyAsChar(80f);

        //then - finals
        Assert.assertEquals(finalValue, (Object) '\u0100');
        Assert.assertTrue(mainFunctionCalled.get());
        Assert.assertTrue(thenFunctionCalled.get());

    }



    @Test
    public void testBoolToFltPred8() throws Throwable  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);

        //given (+ some assertions)
        LFltPredicate sutO = a -> {
                mainFunctionCalled.set(true);
                Assert.assertEquals(a, (Object) 80f);
                return true;
        };

        LLogicalOperator thenFunction = p -> {
                thenFunctionCalled.set(true);
                // boolean
                Assert.assertEquals(p, (Object) true);
                // boolean
                return true;
        };

        //when
        LFltPredicate function = sutO.boolToFltPred(thenFunction);
        boolean finalValue = function.test(80f);

        //then - finals
        Assert.assertEquals(finalValue, (Object) true);
        Assert.assertTrue(mainFunctionCalled.get());
        Assert.assertTrue(thenFunctionCalled.get());

    }



    // </editor-fold>


    @Test(expectedExceptions = RuntimeException.class)
    public void testShove() {

        // given
        LFltPredicate sutThrowing = LFltPredicate.fltPred(a -> {
            throw new UnsupportedOperationException();
        });

        // when
        sutThrowing.shovingTest(100f);
    }


    @Test
    public void testToString() throws Throwable {

        Assert.assertTrue(sut.toString().startsWith(this.getClass().getName()+"$"));

        Assert.assertTrue(String.format("%s", sut).contains("LFltPredicate: boolean test(float a)"));
    
    }


    @Test
    public void isThrowing() {

        Assert.assertFalse(sut.isThrowing());
    }

}
