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

package eu.lunisolar.magma.func.function.to;

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
public class LTieFunctionTest<T1,T2> {
    private static final String ORIGINAL_MESSAGE = "Original message";
    private static final String EXCEPTION_WAS_WRAPPED = "Exception was wrapped.";
    private static final String NO_EXCEPTION_WERE_THROWN = "No exception were thrown.";

    private int testValue = 100;



    private LTieFunction<Integer,Integer> sut = new LTieFunction<Integer,Integer>(){
        public  int applyAsIntX(Integer a1,int a2,Integer a3)  {
            return testValue;
        }
    };




    private LTieFunction<Integer,Integer> sutAlwaysThrowing = LTieFunction.tieFunc((a1,a2,a3) -> {
            throw new ParseException(ORIGINAL_MESSAGE, 0);
    });

    private LTieFunction<Integer,Integer> sutAlwaysThrowingUnchecked = LTieFunction.tieFunc((a1,a2,a3) -> {
            throw new IndexOutOfBoundsException(ORIGINAL_MESSAGE);
    });


    @Test
    public void testTheResult() throws Throwable {
         Assert.assertEquals(sut.applyAsInt(100,100,100), testValue);
    }

    @Test
    public void testTupleCall() throws Throwable {

        LObjIntObjTriple<Integer,Integer> domainObject = Tuple4U.objIntObjTriple(100,100,100);

        Object result = sut.tupleApplyAsInt(domainObject);

            Assert.assertEquals(result, testValue);
    }

    @Test
    public void testNonNullApplyAsInt() throws Throwable {
            Assert.assertEquals(sut.nonNullApplyAsInt(100,100,100), testValue);
    }

    @Test
    public void testNestingApplyAsIntUnchecked() throws Throwable {

        // then
        try {
            sutAlwaysThrowingUnchecked.nestingApplyAsInt(100,100,100);
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
            sutAlwaysThrowingUnchecked.shovingApplyAsInt(100,100,100);
            Assert.fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            Assert.assertEquals(e.getClass(), IndexOutOfBoundsException.class);
            Assert.assertNull(e.getCause());
            Assert.assertEquals(e.getMessage(), ORIGINAL_MESSAGE);
        }
    }


    @Test
    public void testFunctionalInterfaceDescription() throws Throwable {
        Assert.assertEquals(sut.functionalInterfaceDescription(), "LTieFunction: int applyAsInt(T1 a1,int a2,T2 a3)");
    }

    @Test
    public void testTieFuncMethod() throws Throwable {
        Assert.assertTrue(LTieFunction.tieFunc((a1,a2,a3) -> testValue ) instanceof LTieFunction);
    
    }






    // <editor-fold desc="compose (functional)">

    @Test
    public void testCompose() throws Throwable {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LTieFunction<Integer,Integer> sutO = (a1,a2,a3) -> {
                mainFunctionCalled.set(true);
                Assert.assertEquals(a1, (Object) 90);
                Assert.assertEquals(a2, (Object) 91);
                Assert.assertEquals(a3, (Object) 92);
                return 100;
        };

        LFunction<Integer,Integer> before1 = p0 -> {
            Assert.assertEquals(p0, (Object) 80);
            beforeCalls.incrementAndGet();
            return 90;
        };
        LIntUnaryOperator before2 = p1 -> {
            Assert.assertEquals(p1, (Object) 81);
            beforeCalls.incrementAndGet();
            return 91;
        };
        LFunction<Integer,Integer> before3 = p2 -> {
            Assert.assertEquals(p2, (Object) 82);
            beforeCalls.incrementAndGet();
            return 92;
        };

        //when
        LTieFunction<Integer,Integer> function = sutO.compose(before1,before2,before3);
        function.applyAsInt(80,81,82);

        //then - finals
        Assert.assertTrue(mainFunctionCalled.get());
        Assert.assertEquals(beforeCalls.get(), 3);
    }


    @Test
    public void testTieFuncCompose() throws Throwable {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LTieFunction<Integer,Integer> sutO = (a1,a2,a3) -> {
                mainFunctionCalled.set(true);
                Assert.assertEquals(a1, (Object) 90);
                Assert.assertEquals(a2, (Object) 91);
                Assert.assertEquals(a3, (Object) 92);
                return 100;
        };

        LFunction<Integer,Integer> before1 = p0 -> {
            Assert.assertEquals(p0, (Object) 80);
            beforeCalls.incrementAndGet();
            return 90;
        };
        LToIntFunction<Integer> before2 = p1 -> {
            Assert.assertEquals(p1, (Object) 81);
            beforeCalls.incrementAndGet();
            return 91;
        };
        LFunction<Integer,Integer> before3 = p2 -> {
            Assert.assertEquals(p2, (Object) 82);
            beforeCalls.incrementAndGet();
            return 92;
        };

        //when
        LToIntTriFunction<Integer,Integer,Integer> function = sutO.tieFuncCompose(before1,before2,before3);
        function.applyAsInt(80,81,82);

        //then - finals
        Assert.assertTrue(mainFunctionCalled.get());
        Assert.assertEquals(beforeCalls.get(), 3);
    }

    // </editor-fold>



    // <editor-fold desc="then (functional)">

    @Test
    public void testThen0() throws Throwable  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);

        //given (+ some assertions)
        LTieFunction<Integer,Integer> sutO = (a1,a2,a3) -> {
                mainFunctionCalled.set(true);
                Assert.assertEquals(a1, (Object) 80);
                Assert.assertEquals(a2, (Object) 81);
                Assert.assertEquals(a3, (Object) 82);
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
        LObjIntObjFunction<Integer,Integer,Integer> function = sutO.then(thenFunction);
        Integer finalValue = function.apply(80,81,82);

        //then - finals
        Assert.assertEquals(finalValue, (Object) 100);
        Assert.assertTrue(mainFunctionCalled.get());
        Assert.assertTrue(thenFunctionCalled.get());

    }



    @Test
    public void testThenToInt1() throws Throwable  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);

        //given (+ some assertions)
        LTieFunction<Integer,Integer> sutO = (a1,a2,a3) -> {
                mainFunctionCalled.set(true);
                Assert.assertEquals(a1, (Object) 80);
                Assert.assertEquals(a2, (Object) 81);
                Assert.assertEquals(a3, (Object) 82);
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
        LTieFunction<Integer,Integer> function = sutO.thenToInt(thenFunction);
        int finalValue = function.applyAsInt(80,81,82);

        //then - finals
        Assert.assertEquals(finalValue, (Object) 100);
        Assert.assertTrue(mainFunctionCalled.get());
        Assert.assertTrue(thenFunctionCalled.get());

    }



    @Test
    public void testThenToBool2() throws Throwable  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);

        //given (+ some assertions)
        LTieFunction<Integer,Integer> sutO = (a1,a2,a3) -> {
                mainFunctionCalled.set(true);
                Assert.assertEquals(a1, (Object) 80);
                Assert.assertEquals(a2, (Object) 81);
                Assert.assertEquals(a3, (Object) 82);
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
        LObjIntObjPredicate<Integer,Integer> function = sutO.thenToBool(thenFunction);
        boolean finalValue = function.test(80,81,82);

        //then - finals
        Assert.assertEquals(finalValue, (Object) true);
        Assert.assertTrue(mainFunctionCalled.get());
        Assert.assertTrue(thenFunctionCalled.get());

    }



    // </editor-fold>


    @Test(expectedExceptions = RuntimeException.class)
    public void testShove() {

        // given
        LTieFunction<Integer,Integer> sutThrowing = LTieFunction.tieFunc((a1,a2,a3) -> {
            throw new UnsupportedOperationException();
        });

        // when
        sutThrowing.shovingApplyAsInt(100,100,100);
    }


    @Test
    public void testToString() throws Throwable {

        Assert.assertTrue(sut.toString().startsWith(this.getClass().getName()+"$"));

        Assert.assertTrue(String.format("%s", sut).contains("LTieFunction: int applyAsInt(T1 a1,int a2,T2 a3)"));
    
    }


    @Test
    public void isThrowing() {

        Assert.assertFalse(sut.isThrowing());
    }

    //<editor-fold desc="Variants">

    private int variantLObj0Obj2Int1ToIntFunc(Integer a1,Integer a3,int a2) {
        return 100;
    }

    @Test
    public void compilerSubstituteVariantLObj0Obj2Int1ToIntFunc() {
        LTieFunction lambda = LTieFunction./*<T1,T2>*/obj0Obj2Int1ToIntFunc(this::variantLObj0Obj2Int1ToIntFunc);

        Assert.assertTrue(lambda instanceof LTieFunction.LObj0Obj2Int1ToIntFunc);
    }


    private int variantLInt1BiObj2ToIntFunc(int a2,Integer a1,Integer a3) {
        return 100;
    }

    @Test
    public void compilerSubstituteVariantLInt1BiObj2ToIntFunc() {
        LTieFunction lambda = LTieFunction./*<T1,T2>*/int1BiObj2ToIntFunc(this::variantLInt1BiObj2ToIntFunc);

        Assert.assertTrue(lambda instanceof LTieFunction.LInt1BiObj2ToIntFunc);
    }


    private int variantLInt1Obj2Obj0ToIntFunc(int a2,Integer a3,Integer a1) {
        return 100;
    }

    @Test
    public void compilerSubstituteVariantLInt1Obj2Obj0ToIntFunc() {
        LTieFunction lambda = LTieFunction./*<T1,T2>*/int1Obj2Obj0ToIntFunc(this::variantLInt1Obj2Obj0ToIntFunc);

        Assert.assertTrue(lambda instanceof LTieFunction.LInt1Obj2Obj0ToIntFunc);
    }


    private int variantLObj2Obj0Int1ToIntFunc(Integer a3,Integer a1,int a2) {
        return 100;
    }

    @Test
    public void compilerSubstituteVariantLObj2Obj0Int1ToIntFunc() {
        LTieFunction lambda = LTieFunction./*<T1,T2>*/obj2Obj0Int1ToIntFunc(this::variantLObj2Obj0Int1ToIntFunc);

        Assert.assertTrue(lambda instanceof LTieFunction.LObj2Obj0Int1ToIntFunc);
    }


    private int variantLObj2Int1Obj0ToIntFunc(Integer a3,int a2,Integer a1) {
        return 100;
    }

    @Test
    public void compilerSubstituteVariantLObj2Int1Obj0ToIntFunc() {
        LTieFunction lambda = LTieFunction./*<T1,T2>*/obj2Int1Obj0ToIntFunc(this::variantLObj2Int1Obj0ToIntFunc);

        Assert.assertTrue(lambda instanceof LTieFunction.LObj2Int1Obj0ToIntFunc);
    }

    //</editor-fold>


}
