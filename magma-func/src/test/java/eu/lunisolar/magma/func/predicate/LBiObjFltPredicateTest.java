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
public class LBiObjFltPredicateTest<T1,T2> {
    private static final String ORIGINAL_MESSAGE = "Original message";
    private static final String EXCEPTION_WAS_WRAPPED = "Exception was wrapped.";
    private static final String NO_EXCEPTION_WERE_THROWN = "No exception were thrown.";

    private boolean testValue = true;



    private LBiObjFltPredicate<Integer,Integer> sut = new LBiObjFltPredicate<Integer,Integer>(){
        public  boolean testX(Integer a1,Integer a2,float a3)  {
            return testValue;
        }
    };




    private LBiObjFltPredicate<Integer,Integer> sutAlwaysThrowing = LBiObjFltPredicate.biObjFltPred((a1,a2,a3) -> {
            throw new ParseException(ORIGINAL_MESSAGE, 0);
    });

    private LBiObjFltPredicate<Integer,Integer> sutAlwaysThrowingUnchecked = LBiObjFltPredicate.biObjFltPred((a1,a2,a3) -> {
            throw new IndexOutOfBoundsException(ORIGINAL_MESSAGE);
    });


    @Test
    public void testTheResult() throws Throwable {
         Assert.assertEquals(sut.test(100,100,100f), testValue);
    }

    @Test
    public void testTupleCall() throws Throwable {

        LBiObjFltTriple<Integer,Integer> domainObject = Tuple4U.biObjFltTriple(100,100,100f);

        Object result = sut.tupleTest(domainObject);

            Assert.assertEquals(result, testValue);
    }

    @Test
    public void testNonNullTest() throws Throwable {
            Assert.assertEquals(sut.nonNullTest(100,100,100f), testValue);
    }

    @Test
    public void testNestingTestUnchecked() throws Throwable {

        // then
        try {
            sutAlwaysThrowingUnchecked.nestingTest(100,100,100f);
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
            sutAlwaysThrowingUnchecked.shovingTest(100,100,100f);
            Assert.fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            Assert.assertEquals(e.getClass(), IndexOutOfBoundsException.class);
            Assert.assertNull(e.getCause());
            Assert.assertEquals(e.getMessage(), ORIGINAL_MESSAGE);
        }
    }

    @Test
    public void testApplyAsBooleanShouldNotModifyValue() throws Throwable {
        Assert.assertEquals(sut.doApplyAsBoolean(100,100,100f), testValue);
    }


    @Test
    public void testFunctionalInterfaceDescription() throws Throwable {
        Assert.assertEquals(sut.functionalInterfaceDescription(), "LBiObjFltPredicate: boolean test(T1 a1,T2 a2,float a3)");
    }

    @Test
    public void testBiObjFltPredMethod() throws Throwable {
        Assert.assertTrue(LBiObjFltPredicate.biObjFltPred((a1,a2,a3) -> testValue ) instanceof LBiObjFltPredicate);
    
    }




    @Test
    public void testnegate() throws Throwable {
        Assert.assertEquals(sut.negate().test(100,100,100f), !testValue);
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
        LBiObjFltPredicate<Integer,Integer> fun1 = LBiObjFltPredicate.biObjFltPred((a1,a2,a3) -> f1Result);
        LBiObjFltPredicate<Integer,Integer> fun2 = LBiObjFltPredicate.biObjFltPred((a1,a2,a3) -> f2Result);

        //when
        LBiObjFltPredicate<Integer,Integer> andFunction = fun1.and(fun2);
        LBiObjFltPredicate<Integer,Integer> orFunction = fun1.or(fun2);
        LBiObjFltPredicate<Integer,Integer> xorFunction = fun1.xor(fun2);

        //then
        Assert.assertEquals(andFunction.test(100,100,100f), andResult);

        Assert.assertEquals(orFunction.test(100,100,100f), orResult);

        Assert.assertEquals(xorFunction.test(100,100,100f), xorResult);
    }

    @Test
    public void testIsEqual() throws Throwable  {
        //when
        LBiObjFltPredicate<Integer,Integer> equals = LBiObjFltPredicate.isEqual(1,1,1f);

        //then
        Assert.assertTrue(equals.test(1,1,1f));

        Assert.assertFalse(equals.test(0,0,0f));
    }



    // <editor-fold desc="compose (functional)">

    @Test
    public void testCompose() throws Throwable {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LBiObjFltPredicate<Integer,Integer> sutO = (a1,a2,a3) -> {
                mainFunctionCalled.set(true);
                Assert.assertEquals(a1, (Object) 90);
                Assert.assertEquals(a2, (Object) 91);
                Assert.assertEquals(a3, (Object) 92f);
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
        LFltUnaryOperator before3 = p2 -> {
            Assert.assertEquals(p2, (Object) 82f);
            beforeCalls.incrementAndGet();
            return 92f;
        };

        //when
        LBiObjFltPredicate<Integer,Integer> function = sutO.compose(before1,before2,before3);
        function.test(80,81,82f);

        //then - finals
        Assert.assertTrue(mainFunctionCalled.get());
        Assert.assertEquals(beforeCalls.get(), 3);
    }


    @Test
    public void testBiObjFltPredCompose() throws Throwable {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LBiObjFltPredicate<Integer,Integer> sutO = (a1,a2,a3) -> {
                mainFunctionCalled.set(true);
                Assert.assertEquals(a1, (Object) 90);
                Assert.assertEquals(a2, (Object) 91);
                Assert.assertEquals(a3, (Object) 92f);
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
        LToFltFunction<Integer> before3 = p2 -> {
            Assert.assertEquals(p2, (Object) 82);
            beforeCalls.incrementAndGet();
            return 92f;
        };

        //when
        LTriPredicate<Integer,Integer,Integer> function = sutO.biObjFltPredCompose(before1,before2,before3);
        function.test(80,81,82);

        //then - finals
        Assert.assertTrue(mainFunctionCalled.get());
        Assert.assertEquals(beforeCalls.get(), 3);
    }

    // </editor-fold>



    // <editor-fold desc="then (functional)">

    @Test
    public void testBoolToBiObjFltFunc0() throws Throwable  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);

        //given (+ some assertions)
        LBiObjFltPredicate<Integer,Integer> sutO = (a1,a2,a3) -> {
                mainFunctionCalled.set(true);
                Assert.assertEquals(a1, (Object) 80);
                Assert.assertEquals(a2, (Object) 81);
                Assert.assertEquals(a3, (Object) 82f);
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
        LBiObjFltFunction<Integer,Integer,Integer> function = sutO.boolToBiObjFltFunc(thenFunction);
        Integer finalValue = function.apply(80,81,82f);

        //then - finals
        Assert.assertEquals(finalValue, (Object) 100);
        Assert.assertTrue(mainFunctionCalled.get());
        Assert.assertTrue(thenFunctionCalled.get());

    }



    @Test
    public void testBoolToBiObjFltPred1() throws Throwable  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);

        //given (+ some assertions)
        LBiObjFltPredicate<Integer,Integer> sutO = (a1,a2,a3) -> {
                mainFunctionCalled.set(true);
                Assert.assertEquals(a1, (Object) 80);
                Assert.assertEquals(a2, (Object) 81);
                Assert.assertEquals(a3, (Object) 82f);
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
        LBiObjFltPredicate<Integer,Integer> function = sutO.boolToBiObjFltPred(thenFunction);
        boolean finalValue = function.test(80,81,82f);

        //then - finals
        Assert.assertEquals(finalValue, (Object) true);
        Assert.assertTrue(mainFunctionCalled.get());
        Assert.assertTrue(thenFunctionCalled.get());

    }



    // </editor-fold>


    @Test(expectedExceptions = RuntimeException.class)
    public void testShove() {

        // given
        LBiObjFltPredicate<Integer,Integer> sutThrowing = LBiObjFltPredicate.biObjFltPred((a1,a2,a3) -> {
            throw new UnsupportedOperationException();
        });

        // when
        sutThrowing.shovingTest(100,100,100f);
    }


    @Test
    public void testToString() throws Throwable {

        Assert.assertTrue(sut.toString().startsWith(this.getClass().getName()+"$"));

        Assert.assertTrue(String.format("%s", sut).contains("LBiObjFltPredicate: boolean test(T1 a1,T2 a2,float a3)"));
    
    }


    @Test
    public void isThrowing() {

        Assert.assertFalse(sut.isThrowing());
    }

    //<editor-fold desc="Variants">

    private boolean variantLObj0Flt2Obj1Pred(Integer a1,float a3,Integer a2) {
        return true;
    }

    @Test
    public void compilerSubstituteVariantLObj0Flt2Obj1Pred() {
        LBiObjFltPredicate lambda = LBiObjFltPredicate./*<T1,T2>*/obj0Flt2Obj1Pred(this::variantLObj0Flt2Obj1Pred);

        Assert.assertTrue(lambda instanceof LBiObjFltPredicate.LObj0Flt2Obj1Pred);
    }


    private boolean variantLObj1Obj0Flt2Pred(Integer a2,Integer a1,float a3) {
        return true;
    }

    @Test
    public void compilerSubstituteVariantLObj1Obj0Flt2Pred() {
        LBiObjFltPredicate lambda = LBiObjFltPredicate./*<T1,T2>*/obj1Obj0Flt2Pred(this::variantLObj1Obj0Flt2Pred);

        Assert.assertTrue(lambda instanceof LBiObjFltPredicate.LObj1Obj0Flt2Pred);
    }


    private boolean variantLObj1Flt2Obj0Pred(Integer a2,float a3,Integer a1) {
        return true;
    }

    @Test
    public void compilerSubstituteVariantLObj1Flt2Obj0Pred() {
        LBiObjFltPredicate lambda = LBiObjFltPredicate./*<T1,T2>*/obj1Flt2Obj0Pred(this::variantLObj1Flt2Obj0Pred);

        Assert.assertTrue(lambda instanceof LBiObjFltPredicate.LObj1Flt2Obj0Pred);
    }


    private boolean variantLFlt2Obj0Obj1Pred(float a3,Integer a1,Integer a2) {
        return true;
    }

    @Test
    public void compilerSubstituteVariantLFlt2Obj0Obj1Pred() {
        LBiObjFltPredicate lambda = LBiObjFltPredicate./*<T1,T2>*/flt2Obj0Obj1Pred(this::variantLFlt2Obj0Obj1Pred);

        Assert.assertTrue(lambda instanceof LBiObjFltPredicate.LFlt2Obj0Obj1Pred);
    }


    private boolean variantLFlt2Obj1Obj0Pred(float a3,Integer a2,Integer a1) {
        return true;
    }

    @Test
    public void compilerSubstituteVariantLFlt2Obj1Obj0Pred() {
        LBiObjFltPredicate lambda = LBiObjFltPredicate./*<T1,T2>*/flt2Obj1Obj0Pred(this::variantLFlt2Obj1Obj0Pred);

        Assert.assertTrue(lambda instanceof LBiObjFltPredicate.LFlt2Obj1Obj0Pred);
    }

    //</editor-fold>


}
