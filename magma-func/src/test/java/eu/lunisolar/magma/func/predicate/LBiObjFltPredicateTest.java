/*
 * This file is part of "lunisolar-magma".
 *
 * (C) Copyright 2014-2019 Lunisolar (http://lunisolar.eu/).
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
        assertThat(sut.test(100,100,100f))
            .isEqualTo(testValue);
    }

    @Test
    public void testTupleCall() throws Throwable {

        LBiObjFltTriple<Integer,Integer> domainObject = Tuple4U.biObjFltTriple(100,100,100f);

        Object result = sut.tupleTest(domainObject);

        assertThat(result)
            .isEqualTo(testValue);
    }

    @Test
    public void testNonNullTest() throws Throwable {
        assertThat(sut.nonNullTest(100,100,100f))
            .isEqualTo(testValue);
    }

    @Test
    public void testNestingTestUnchecked() throws Throwable {

        // then
        try {
            sutAlwaysThrowingUnchecked.nestingTest(100,100,100f);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                    .hasNoCause()
                    .hasMessage(ORIGINAL_MESSAGE);
        }
    }

    @Test
    public void testShovingTestUnchecked() throws Throwable {

        // then
        try {
            sutAlwaysThrowingUnchecked.shovingTest(100,100,100f);
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
        assertThat(sut.doApplyAsBoolean(100,100,100f))
            .isEqualTo(testValue);

    }


    @Test
    public void testFunctionalInterfaceDescription() throws Throwable {
        assertThat(sut.functionalInterfaceDescription())
            .isEqualTo("LBiObjFltPredicate: boolean test(T1 a1,T2 a2,float a3)");
    }

    @Test
    public void testBiObjFltPredMethod() throws Throwable {
        assertThat(LBiObjFltPredicate.biObjFltPred((a1,a2,a3) -> testValue ))
            .isInstanceOf(LBiObjFltPredicate.class);
    }




    @Test
    public void testnegate() throws Throwable {
        assertThat(sut.negate().test(100,100,100f))
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
        LBiObjFltPredicate<Integer,Integer> fun1 = LBiObjFltPredicate.biObjFltPred((a1,a2,a3) -> f1Result);
        LBiObjFltPredicate<Integer,Integer> fun2 = LBiObjFltPredicate.biObjFltPred((a1,a2,a3) -> f2Result);

        //when
        LBiObjFltPredicate<Integer,Integer> andFunction = fun1.and(fun2);
        LBiObjFltPredicate<Integer,Integer> orFunction = fun1.or(fun2);
        LBiObjFltPredicate<Integer,Integer> xorFunction = fun1.xor(fun2);

        //then
        assertThat(andFunction.test(100,100,100f))
                .isEqualTo(andResult);

        assertThat(orFunction.test(100,100,100f))
                .isEqualTo(orResult);

        assertThat(xorFunction.test(100,100,100f))
                .isEqualTo(xorResult);
    }

    @Test
    public void testIsEqual() throws Throwable  {
        //when
        LBiObjFltPredicate<Integer,Integer> equals = LBiObjFltPredicate.isEqual(1,1,1f);

        //then
        assertThat(equals.test(1,1,1f))
                .isTrue();

        assertThat(equals.test(0,0,0f))
                .isFalse();
    }



    // <editor-fold desc="compose (functional)">

    @Test
    public void testCompose() throws Throwable {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LBiObjFltPredicate<Integer,Integer> sutO = (a1,a2,a3) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(90);
                assertThat(a2).isEqualTo(91);
                assertThat(a3).isEqualTo(92f);
                return true;
        };

        LFunction<Integer,Integer> before1 = p0 -> {
            assertThat(p0).isEqualTo(80);
            beforeCalls.incrementAndGet();
            return 90;
        };
        LFunction<Integer,Integer> before2 = p1 -> {
            assertThat(p1).isEqualTo(81);
            beforeCalls.incrementAndGet();
            return 91;
        };
        LFltUnaryOperator before3 = p2 -> {
            assertThat(p2).isEqualTo(82f);
            beforeCalls.incrementAndGet();
            return 92f;
        };

        //when
        LBiObjFltPredicate<Integer,Integer> function = sutO.compose(before1,before2,before3);
        function.test(80,81,82f);

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(beforeCalls.get()).isEqualTo(3);
    }


    @Test
    public void testBiObjFltPredCompose() throws Throwable {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LBiObjFltPredicate<Integer,Integer> sutO = (a1,a2,a3) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(90);
                assertThat(a2).isEqualTo(91);
                assertThat(a3).isEqualTo(92f);
                return true;
        };

        LFunction<Integer,Integer> before1 = p0 -> {
            assertThat(p0).isEqualTo(80);
            beforeCalls.incrementAndGet();
            return 90;
        };
        LFunction<Integer,Integer> before2 = p1 -> {
            assertThat(p1).isEqualTo(81);
            beforeCalls.incrementAndGet();
            return 91;
        };
        LToFltFunction<Integer> before3 = p2 -> {
            assertThat(p2).isEqualTo(82);
            beforeCalls.incrementAndGet();
            return 92f;
        };

        //when
        LTriPredicate<Integer,Integer,Integer> function = sutO.biObjFltPredCompose(before1,before2,before3);
        function.test(80,81,82);

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(beforeCalls.get()).isEqualTo(3);
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
                assertThat(a1).isEqualTo(80);
                assertThat(a2).isEqualTo(81);
                assertThat(a3).isEqualTo(82f);
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
        LBiObjFltFunction<Integer,Integer,Integer> function = sutO.boolToBiObjFltFunc(thenFunction);
        Integer finalValue = function.apply(80,81,82f);

        //then - finals
        assertThat(finalValue).isEqualTo(100);
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    @Test
    public void testBoolToBiObjFltPred1() throws Throwable  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);

        //given (+ some assertions)
        LBiObjFltPredicate<Integer,Integer> sutO = (a1,a2,a3) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(80);
                assertThat(a2).isEqualTo(81);
                assertThat(a3).isEqualTo(82f);
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
        LBiObjFltPredicate<Integer,Integer> function = sutO.boolToBiObjFltPred(thenFunction);
        boolean finalValue = function.test(80,81,82f);

        //then - finals
        assertThat(finalValue).isEqualTo(true);
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

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

        assertThat(sut.toString())
                .isInstanceOf(String.class)
                .startsWith(this.getClass().getName()+"$");

        assertThat(String.format("%s", sut))
                .isInstanceOf(String.class)
                .contains("LBiObjFltPredicate: boolean test(T1 a1,T2 a2,float a3)");
    }


    @Test
    public void isThrowing() {
        assertThat(sut.isThrowing())
            .isFalse();
    }

    //<editor-fold desc="Variants">

    private boolean variantLObj0Flt2Obj1Pred(Integer a1,float a3,Integer a2) {
        return true;
    }

    @Test
    public void compilerSubstituteVariantLObj0Flt2Obj1Pred() {
        LBiObjFltPredicate lambda = LBiObjFltPredicate./*<T1,T2>*/obj0Flt2Obj1Pred(this::variantLObj0Flt2Obj1Pred);

        assertThat(lambda).isInstanceOf(LBiObjFltPredicate.LObj0Flt2Obj1Pred.class);
    }


    private boolean variantLObj1Obj0Flt2Pred(Integer a2,Integer a1,float a3) {
        return true;
    }

    @Test
    public void compilerSubstituteVariantLObj1Obj0Flt2Pred() {
        LBiObjFltPredicate lambda = LBiObjFltPredicate./*<T1,T2>*/obj1Obj0Flt2Pred(this::variantLObj1Obj0Flt2Pred);

        assertThat(lambda).isInstanceOf(LBiObjFltPredicate.LObj1Obj0Flt2Pred.class);
    }


    private boolean variantLObj1Flt2Obj0Pred(Integer a2,float a3,Integer a1) {
        return true;
    }

    @Test
    public void compilerSubstituteVariantLObj1Flt2Obj0Pred() {
        LBiObjFltPredicate lambda = LBiObjFltPredicate./*<T1,T2>*/obj1Flt2Obj0Pred(this::variantLObj1Flt2Obj0Pred);

        assertThat(lambda).isInstanceOf(LBiObjFltPredicate.LObj1Flt2Obj0Pred.class);
    }


    private boolean variantLFlt2Obj0Obj1Pred(float a3,Integer a1,Integer a2) {
        return true;
    }

    @Test
    public void compilerSubstituteVariantLFlt2Obj0Obj1Pred() {
        LBiObjFltPredicate lambda = LBiObjFltPredicate./*<T1,T2>*/flt2Obj0Obj1Pred(this::variantLFlt2Obj0Obj1Pred);

        assertThat(lambda).isInstanceOf(LBiObjFltPredicate.LFlt2Obj0Obj1Pred.class);
    }


    private boolean variantLFlt2Obj1Obj0Pred(float a3,Integer a2,Integer a1) {
        return true;
    }

    @Test
    public void compilerSubstituteVariantLFlt2Obj1Obj0Pred() {
        LBiObjFltPredicate lambda = LBiObjFltPredicate./*<T1,T2>*/flt2Obj1Obj0Pred(this::variantLFlt2Obj1Obj0Pred);

        assertThat(lambda).isInstanceOf(LBiObjFltPredicate.LFlt2Obj1Obj0Pred.class);
    }

    //</editor-fold>


    @Test void safeCompiles() {
        LBiObjFltPredicate r1 = LBiObjFltPredicate.safe(sut); //NOSONAR
    }

    @Test void safePropagates() {
        Object result = LBiObjFltPredicate.safe(sut);
        assertThat(result).isSameAs(sut);
    }

    @Test void safeProtectsAgainstNpe() {
        Object result = LBiObjFltPredicate.safe(null);
        assertThat(result).isSameAs(LBiObjFltPredicate.biObjFltPred(LBiObjFltPredicate.safe()));
    }

    @Test  void safeSupplierPropagates() {
        LSupplier<LBiObjFltPredicate<Integer,Integer>> supplier = ()->sut;
        Object result = LBiObjFltPredicate.safeSupplier(supplier);
        assertThat(result).isSameAs(supplier);
    }

    @Test  void safeSupplierProtectsAgainstNpe() {
        Object result = LBiObjFltPredicate.safeSupplier(null);
        assertThat(result).isSameAs(LBiObjFltPredicate.safeSupplier());
    }

    @Test  void safeSupplierCompiles() {
        LSupplier<LBiObjFltPredicate<Integer,Integer>> r1 = LBiObjFltPredicate.safeSupplier(()->sut);  //NOSONAR
    }

}
