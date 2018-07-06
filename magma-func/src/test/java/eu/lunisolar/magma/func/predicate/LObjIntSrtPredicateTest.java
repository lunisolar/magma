/*
 * This file is part of "lunisolar-magma".
 *
 * (C) Copyright 2014-2016 Lunisolar (http://lunisolar.eu/).
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

import org.assertj.core.api.Assertions;  //NOSONAR
import org.testng.annotations.*;      //NOSONAR
import java.util.regex.Pattern;          //NOSONAR
import java.text.ParseException;         //NOSONAR
import eu.lunisolar.magma.basics.*; //NOSONAR
import eu.lunisolar.magma.basics.exceptions.*; //NOSONAR
import java.util.concurrent.atomic.AtomicInteger; //NOSONAR
import eu.lunisolar.magma.func.tuple.*; // NOSONAR
import static org.assertj.core.api.Assertions.*; //NOSONAR
import java.util.function.*; // NOSONAR

/** The test obviously concentrate on the interface methods the function it self is very simple.  */
public class LObjIntSrtPredicateTest<T> {
    private static final String ORIGINAL_MESSAGE = "Original message";
    private static final String EXCEPTION_WAS_WRAPPED = "Exception was wrapped.";
    private static final String NO_EXCEPTION_WERE_THROWN = "No exception were thrown.";

    private boolean testValue = true;



    private LObjIntSrtPredicate<Integer> sut = new LObjIntSrtPredicate<Integer>(){
        public  boolean doTestX(Integer a1,int a2,short a3)  {
            return testValue;
        }
    };




    private LObjIntSrtPredicate<Integer> sutAlwaysThrowing = LObjIntSrtPredicate.objIntSrtPred((a1,a2,a3) -> {
            throw new ParseException(ORIGINAL_MESSAGE, 0);
    });

    private LObjIntSrtPredicate<Integer> sutAlwaysThrowingUnchecked = LObjIntSrtPredicate.objIntSrtPred((a1,a2,a3) -> {
            throw new IndexOutOfBoundsException(ORIGINAL_MESSAGE);
    });


    @Test
    public void testTheResult() throws Throwable {
        assertThat(sut.doTest(100,100,(short)100))
            .isEqualTo(testValue);
    }

    @Test
    public void testTupleCall() throws Throwable {

        LObjIntSrtTriple<Integer> domainObject = Tuple4U.objIntSrtTriple(100,100,(short)100);

        Object result = sut.tupleTest(domainObject);

        assertThat(result)
            .isEqualTo(testValue);
    }

    @Test
    public void testNonNullDoTest() throws Throwable {
        assertThat(sut.nonNullDoTest(100,100,(short)100))
            .isEqualTo(testValue);
    }

    @Test
    public void testNestingDoTestUnchecked() throws Throwable {

        // then
        try {
            sutAlwaysThrowingUnchecked.nestingDoTest(100,100,(short)100);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                    .hasNoCause()
                    .hasMessage(ORIGINAL_MESSAGE);
        }
    }

    @Test
    public void testShovingDoTestUnchecked() throws Throwable {

        // then
        try {
            sutAlwaysThrowingUnchecked.shovingDoTest(100,100,(short)100);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                    .hasNoCause()
                    .hasMessage(ORIGINAL_MESSAGE);
        }
    }

    @Test
    public void testApplyAsBooleanShouldNotModifyValue() throws Throwable {
        assertThat(sut.doApplyAsBoolean(100,100,(short)100))
            .isEqualTo(testValue);

    }


    @Test
    public void testFunctionalInterfaceDescription() throws Throwable {
        assertThat(sut.functionalInterfaceDescription())
            .isEqualTo("LObjIntSrtPredicate: boolean doTest(T a1,int a2,short a3)");
    }

    @Test
    public void testObjIntSrtPredMethod() throws Throwable {
        assertThat(LObjIntSrtPredicate.objIntSrtPred((a1,a2,a3) -> testValue ))
            .isInstanceOf(LObjIntSrtPredicate.class);
    }




    @Test
    public void testnegate() throws Throwable {
        assertThat(sut.negate().doTest(100,100,(short)100))
            .isEqualTo(!testValue);
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
        LObjIntSrtPredicate<Integer> fun1 = LObjIntSrtPredicate.objIntSrtPred((a1,a2,a3) -> f1Result);
        LObjIntSrtPredicate<Integer> fun2 = LObjIntSrtPredicate.objIntSrtPred((a1,a2,a3) -> f2Result);

        //when
        LObjIntSrtPredicate<Integer> andFunction = fun1.and(fun2);
        LObjIntSrtPredicate<Integer> orFunction = fun1.or(fun2);
        LObjIntSrtPredicate<Integer> xorFunction = fun1.xor(fun2);

        //then
        assertThat(andFunction.doTest(100,100,(short)100))
                .isEqualTo(andResult);

        assertThat(orFunction.doTest(100,100,(short)100))
                .isEqualTo(orResult);

        assertThat(xorFunction.doTest(100,100,(short)100))
                .isEqualTo(xorResult);
    }

    @Test
    public void testIsEqual() throws Throwable  {
        //when
        LObjIntSrtPredicate<Integer> equals = LObjIntSrtPredicate.isEqual(1,1,(short)1);

        //then
        assertThat(equals.doTest(1,1,(short)1))
                .isTrue();

        assertThat(equals.doTest(0,0,(short)0))
                .isFalse();
    }



    // <editor-fold desc="compose (functional)">

    @Test
    public void testObjIntSrtPredComposeIntSrt() throws Throwable {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LObjIntSrtPredicate<Integer> sutO = (a1,a2,a3) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(90);
                assertThat(a2).isEqualTo(91);
                assertThat(a3).isEqualTo((short)92);
                return true;
        };

        LFunction<Integer,Integer> before1 = p0 -> {
            assertThat(p0).isEqualTo(80);
            beforeCalls.incrementAndGet();
            return 90;
        };
        LIntUnaryOperator before2 = p1 -> {
            assertThat(p1).isEqualTo(81);
            beforeCalls.incrementAndGet();
            return 91;
        };
        LSrtUnaryOperator before3 = p2 -> {
            assertThat(p2).isEqualTo((short)82);
            beforeCalls.incrementAndGet();
            return (short)92;
        };

        //when
        LObjIntSrtPredicate<Integer> function = sutO.objIntSrtPredComposeIntSrt(before1,before2,before3);
        function.doTest(80,81,(short)82);

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(beforeCalls.get()).isEqualTo(3);
    }


    @Test
    public void testObjIntSrtPredCompose() throws Throwable {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LObjIntSrtPredicate<Integer> sutO = (a1,a2,a3) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(90);
                assertThat(a2).isEqualTo(91);
                assertThat(a3).isEqualTo((short)92);
                return true;
        };

        LFunction<Integer,Integer> before1 = p0 -> {
            assertThat(p0).isEqualTo(80);
            beforeCalls.incrementAndGet();
            return 90;
        };
        LToIntFunction<Integer> before2 = p1 -> {
            assertThat(p1).isEqualTo(81);
            beforeCalls.incrementAndGet();
            return 91;
        };
        LToSrtFunction<Integer> before3 = p2 -> {
            assertThat(p2).isEqualTo(82);
            beforeCalls.incrementAndGet();
            return (short)92;
        };

        //when
        LTriPredicate<Integer,Integer,Integer> function = sutO.objIntSrtPredCompose(before1,before2,before3);
        function.doTest(80,81,82);

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(beforeCalls.get()).isEqualTo(3);
    }

    // </editor-fold>



    // <editor-fold desc="then (functional)">

    @Test
    public void testBoolToObjIntSrtFunc0() throws Throwable  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);

        //given (+ some assertions)
        LObjIntSrtPredicate<Integer> sutO = (a1,a2,a3) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(80);
                assertThat(a2).isEqualTo(81);
                assertThat(a3).isEqualTo((short)82);
                return true;
        };

        LBoolFunction<Integer> thenFunction = p -> {
                thenFunctionCalled.set(true);
                // boolean
                assertThat(p).isEqualTo(true);
                // Integer
                return 100;
        };

        //when
        LObjIntSrtFunction<Integer,Integer> function = sutO.boolToObjIntSrtFunc(thenFunction);
        Integer finalValue = function.doApply(80,81,(short)82);

        //then - finals
        assertThat(finalValue).isEqualTo(100);
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    @Test
    public void testBoolToTieSrtFunc1() throws Throwable  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);

        //given (+ some assertions)
        LObjIntSrtPredicate<Integer> sutO = (a1,a2,a3) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(80);
                assertThat(a2).isEqualTo(81);
                assertThat(a3).isEqualTo((short)82);
                return true;
        };

        LBoolToIntFunction thenFunction = p -> {
                thenFunctionCalled.set(true);
                // boolean
                assertThat(p).isEqualTo(true);
                // int
                return 100;
        };

        //when
        LTieSrtFunction<Integer> function = sutO.boolToTieSrtFunc(thenFunction);
        int finalValue = function.doApplyAsInt(80,81,(short)82);

        //then - finals
        assertThat(finalValue).isEqualTo(100);
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    @Test
    public void testBoolToObjIntSrtPred2() throws Throwable  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);

        //given (+ some assertions)
        LObjIntSrtPredicate<Integer> sutO = (a1,a2,a3) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(80);
                assertThat(a2).isEqualTo(81);
                assertThat(a3).isEqualTo((short)82);
                return true;
        };

        LLogicalOperator thenFunction = p -> {
                thenFunctionCalled.set(true);
                // boolean
                assertThat(p).isEqualTo(true);
                // boolean
                return true;
        };

        //when
        LObjIntSrtPredicate<Integer> function = sutO.boolToObjIntSrtPred(thenFunction);
        boolean finalValue = function.doTest(80,81,(short)82);

        //then - finals
        assertThat(finalValue).isEqualTo(true);
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    // </editor-fold>

    @Test
    public void testNesting() {
        assertThat(sut.nestingObjIntSrtPred())
            .isSameAs(sut)
            .isInstanceOf(LObjIntSrtPredicate.class);
    }

    @Test
    public void testShoving() {
        assertThat(sut.shovingObjIntSrtPred())
            .isSameAs(sut)
            .isInstanceOf(LObjIntSrtPredicate.class);
    }


    @Test(expectedExceptions = RuntimeException.class)
    public void testShove() {

        // given
        LObjIntSrtPredicate<Integer> sutThrowing = LObjIntSrtPredicate.objIntSrtPred((a1,a2,a3) -> {
            throw new UnsupportedOperationException();
        });

        // when
        sutThrowing.shovingObjIntSrtPred().doTest(100,100,(short)100);
    }


    @Test
    public void testToString() throws Throwable {

        assertThat(sut.toString())
                .isInstanceOf(String.class)
                .startsWith(this.getClass().getName()+"$");

        assertThat(String.format("%s", sut))
                .isInstanceOf(String.class)
                .contains("LObjIntSrtPredicate: boolean doTest(T a1,int a2,short a3)");
    }


    @Test
    public void isThrowing() {
        assertThat(sut.isThrowing())
            .isFalse();
    }

    //<editor-fold desc="Variants">

    private boolean variantLObjSrtIntPred(Integer a1,short a3,int a2) {
        return true;
    }

    @Test
    public void compilerSubstituteVariantLObjSrtIntPred() {
        LObjIntSrtPredicate lambda = LObjIntSrtPredicate./*<T>*/objSrtIntPred(this::variantLObjSrtIntPred);

        assertThat(lambda).isInstanceOf(LObjIntSrtPredicate.LObjSrtIntPred.class);
    }


    private boolean variantLIntObjSrtPred(int a2,Integer a1,short a3) {
        return true;
    }

    @Test
    public void compilerSubstituteVariantLIntObjSrtPred() {
        LObjIntSrtPredicate lambda = LObjIntSrtPredicate./*<T>*/intObjSrtPred(this::variantLIntObjSrtPred);

        assertThat(lambda).isInstanceOf(LObjIntSrtPredicate.LIntObjSrtPred.class);
    }


    private boolean variantLIntSrtObjPred(int a2,short a3,Integer a1) {
        return true;
    }

    @Test
    public void compilerSubstituteVariantLIntSrtObjPred() {
        LObjIntSrtPredicate lambda = LObjIntSrtPredicate./*<T>*/intSrtObjPred(this::variantLIntSrtObjPred);

        assertThat(lambda).isInstanceOf(LObjIntSrtPredicate.LIntSrtObjPred.class);
    }


    private boolean variantLSrtObjIntPred(short a3,Integer a1,int a2) {
        return true;
    }

    @Test
    public void compilerSubstituteVariantLSrtObjIntPred() {
        LObjIntSrtPredicate lambda = LObjIntSrtPredicate./*<T>*/srtObjIntPred(this::variantLSrtObjIntPred);

        assertThat(lambda).isInstanceOf(LObjIntSrtPredicate.LSrtObjIntPred.class);
    }


    private boolean variantLSrtIntObjPred(short a3,int a2,Integer a1) {
        return true;
    }

    @Test
    public void compilerSubstituteVariantLSrtIntObjPred() {
        LObjIntSrtPredicate lambda = LObjIntSrtPredicate./*<T>*/srtIntObjPred(this::variantLSrtIntObjPred);

        assertThat(lambda).isInstanceOf(LObjIntSrtPredicate.LSrtIntObjPred.class);
    }

    //</editor-fold>


    @Test void safeCompiles() {
        LObjIntSrtPredicate r1 = LObjIntSrtPredicate.safe(sut); //NOSONAR
    }

    @Test void safePropagates() {
        Object result = LObjIntSrtPredicate.safe(sut);
        assertThat(result).isSameAs(sut);
    }

    @Test void safeProtectsAgainstNpe() {
        Object result = LObjIntSrtPredicate.safe(null);
        assertThat(result).isSameAs(LObjIntSrtPredicate.objIntSrtPred(LObjIntSrtPredicate.safe()));
    }

    @Test  void safeSupplierPropagates() {
        LSupplier<LObjIntSrtPredicate<Integer>> supplier = ()->sut;
        Object result = LObjIntSrtPredicate.safeSupplier(supplier);
        assertThat(result).isSameAs(supplier);
    }

    @Test  void safeSupplierProtectsAgainstNpe() {
        Object result = LObjIntSrtPredicate.safeSupplier(null);
        assertThat(result).isSameAs(LObjIntSrtPredicate.safeSupplier());
    }

    @Test  void safeSupplierCompiles() {
        LSupplier<LObjIntSrtPredicate<Integer>> r1 = LObjIntSrtPredicate.safeSupplier(()->sut);  //NOSONAR
    }

}
