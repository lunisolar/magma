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
public class LQuintPredicateTest<T1,T2,T3,T4,T5> {
    private static final String ORIGINAL_MESSAGE = "Original message";
    private static final String EXCEPTION_WAS_WRAPPED = "Exception was wrapped.";
    private static final String NO_EXCEPTION_WERE_THROWN = "No exception were thrown.";

    private boolean testValue = true;



    private LQuintPredicate<Integer,Integer,Integer,Integer,Integer> sut = new LQuintPredicate<Integer,Integer,Integer,Integer,Integer>(){
        public  boolean testX(Integer a1,Integer a2,Integer a3,Integer a4,Integer a5)  {
            return testValue;
        }
    };




    private LQuintPredicate<Integer,Integer,Integer,Integer,Integer> sutAlwaysThrowing = LQuintPredicate.quintPred((a1,a2,a3,a4,a5) -> {
            throw new ParseException(ORIGINAL_MESSAGE, 0);
    });

    private LQuintPredicate<Integer,Integer,Integer,Integer,Integer> sutAlwaysThrowingUnchecked = LQuintPredicate.quintPred((a1,a2,a3,a4,a5) -> {
            throw new IndexOutOfBoundsException(ORIGINAL_MESSAGE);
    });


    @Test
    public void testTheResult() throws Throwable {
         Assert.assertEquals(sut.test(100,100,100,100,100), testValue);
    }

    @Test
    public void testTupleCall() throws Throwable {

        LQuint<Integer,Integer,Integer,Integer,Integer> domainObject = Tuple4U.quint(100,100,100,100,100);

        Object result = sut.tupleTest(domainObject);

            Assert.assertEquals(result, testValue);
    }

    @Test
    public void testNonNullTest() throws Throwable {
            Assert.assertEquals(sut.nonNullTest(100,100,100,100,100), testValue);
    }

    @Test
    public void testNestingTestUnchecked() throws Throwable {

        // then
        try {
            sutAlwaysThrowingUnchecked.nestingTest(100,100,100,100,100);
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
            sutAlwaysThrowingUnchecked.shovingTest(100,100,100,100,100);
            Assert.fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            Assert.assertEquals(e.getClass(), IndexOutOfBoundsException.class);
            Assert.assertNull(e.getCause());
            Assert.assertEquals(e.getMessage(), ORIGINAL_MESSAGE);
        }
    }

    @Test
    public void testApplyAsBooleanShouldNotModifyValue() throws Throwable {
        Assert.assertEquals(sut.doApplyAsBoolean(100,100,100,100,100), testValue);
    }


    @Test
    public void testFunctionalInterfaceDescription() throws Throwable {
        Assert.assertEquals(sut.functionalInterfaceDescription(), "LQuintPredicate: boolean test(T1 a1,T2 a2,T3 a3,T4 a4,T5 a5)");
    }

    @Test
    public void testQuintPredMethod() throws Throwable {
        Assert.assertTrue(LQuintPredicate.quintPred((a1,a2,a3,a4,a5) -> testValue ) instanceof LQuintPredicate);
    
    }




    @Test
    public void testnegate() throws Throwable {
        Assert.assertEquals(sut.negate().test(100,100,100,100,100), !testValue);
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
        LQuintPredicate<Integer,Integer,Integer,Integer,Integer> fun1 = LQuintPredicate.quintPred((a1,a2,a3,a4,a5) -> f1Result);
        LQuintPredicate<Integer,Integer,Integer,Integer,Integer> fun2 = LQuintPredicate.quintPred((a1,a2,a3,a4,a5) -> f2Result);

        //when
        LQuintPredicate<Integer,Integer,Integer,Integer,Integer> andFunction = fun1.and(fun2);
        LQuintPredicate<Integer,Integer,Integer,Integer,Integer> orFunction = fun1.or(fun2);
        LQuintPredicate<Integer,Integer,Integer,Integer,Integer> xorFunction = fun1.xor(fun2);

        //then
        Assert.assertEquals(andFunction.test(100,100,100,100,100), andResult);

        Assert.assertEquals(orFunction.test(100,100,100,100,100), orResult);

        Assert.assertEquals(xorFunction.test(100,100,100,100,100), xorResult);
    }

    @Test
    public void testIsEqual() throws Throwable  {
        //when
        LQuintPredicate<Integer,Integer,Integer,Integer,Integer> equals = LQuintPredicate.isEqual(1,1,1,1,1);

        //then
        Assert.assertTrue(equals.test(1,1,1,1,1));

        Assert.assertFalse(equals.test(0,0,0,0,0));
    }



    // <editor-fold desc="compose (functional)">

    @Test
    public void testCompose() throws Throwable {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LQuintPredicate<Integer,Integer,Integer,Integer,Integer> sutO = (a1,a2,a3,a4,a5) -> {
                mainFunctionCalled.set(true);
                Assert.assertEquals(a1, (Object) 90);
                Assert.assertEquals(a2, (Object) 91);
                Assert.assertEquals(a3, (Object) 92);
                Assert.assertEquals(a4, (Object) 93);
                Assert.assertEquals(a5, (Object) 94);
                return true;
        };

        LFunction<Integer,Integer> before1 = p0 -> {
            Assert.assertEquals(p0, (Object) 80);
            beforeCalls.incrementAndGet();
            return 90;
        };
        LFunction<Integer,Integer> before2 = p1 -> {
            Assert.assertEquals(p1, (Object) 81);
            beforeCalls.incrementAndGet();
            return 91;
        };
        LFunction<Integer,Integer> before3 = p2 -> {
            Assert.assertEquals(p2, (Object) 82);
            beforeCalls.incrementAndGet();
            return 92;
        };
        LFunction<Integer,Integer> before4 = p3 -> {
            Assert.assertEquals(p3, (Object) 83);
            beforeCalls.incrementAndGet();
            return 93;
        };
        LFunction<Integer,Integer> before5 = p4 -> {
            Assert.assertEquals(p4, (Object) 84);
            beforeCalls.incrementAndGet();
            return 94;
        };

        //when
        LQuintPredicate<Integer,Integer,Integer,Integer,Integer> function = sutO.compose(before1,before2,before3,before4,before5);
        function.test(80,81,82,83,84);

        //then - finals
        Assert.assertTrue(mainFunctionCalled.get());
        Assert.assertEquals(beforeCalls.get(), 5);
    }

    // </editor-fold>



    // <editor-fold desc="then (functional)">

    @Test
    public void testBoolToQuintFunc0() throws Throwable  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);

        //given (+ some assertions)
        LQuintPredicate<Integer,Integer,Integer,Integer,Integer> sutO = (a1,a2,a3,a4,a5) -> {
                mainFunctionCalled.set(true);
                Assert.assertEquals(a1, (Object) 80);
                Assert.assertEquals(a2, (Object) 81);
                Assert.assertEquals(a3, (Object) 82);
                Assert.assertEquals(a4, (Object) 83);
                Assert.assertEquals(a5, (Object) 84);
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
        LQuintFunction<Integer,Integer,Integer,Integer,Integer,Integer> function = sutO.boolToQuintFunc(thenFunction);
        Integer finalValue = function.apply(80,81,82,83,84);

        //then - finals
        Assert.assertEquals(finalValue, (Object) 100);
        Assert.assertTrue(mainFunctionCalled.get());
        Assert.assertTrue(thenFunctionCalled.get());

    }



    // </editor-fold>


    @Test(expectedExceptions = RuntimeException.class)
    public void testShove() {

        // given
        LQuintPredicate<Integer,Integer,Integer,Integer,Integer> sutThrowing = LQuintPredicate.quintPred((a1,a2,a3,a4,a5) -> {
            throw new UnsupportedOperationException();
        });

        // when
        sutThrowing.shovingTest(100,100,100,100,100);
    }


    @Test
    public void testToString() throws Throwable {

        Assert.assertTrue(sut.toString().startsWith(this.getClass().getName()+"$"));

        Assert.assertTrue(String.format("%s", sut).contains("LQuintPredicate: boolean test(T1 a1,T2 a2,T3 a3,T4 a4,T5 a5)"));
    
    }


    @Test
    public void isThrowing() {

        Assert.assertFalse(sut.isThrowing());
    }

}
