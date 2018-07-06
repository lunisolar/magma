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
public class LBiObjBoolPredicateTest<T1,T2> {
    private static final String ORIGINAL_MESSAGE = "Original message";
    private static final String EXCEPTION_WAS_WRAPPED = "Exception was wrapped.";
    private static final String NO_EXCEPTION_WERE_THROWN = "No exception were thrown.";

    private boolean testValue = true;



    private LBiObjBoolPredicate<Integer,Integer> sut = new LBiObjBoolPredicate<Integer,Integer>(){
        public  boolean doTestX(Integer a1,Integer a2,boolean a3)  {
            return testValue;
        }
    };




    private LBiObjBoolPredicate<Integer,Integer> sutAlwaysThrowing = LBiObjBoolPredicate.biObjBoolPred((a1,a2,a3) -> {
            throw new ParseException(ORIGINAL_MESSAGE, 0);
    });

    private LBiObjBoolPredicate<Integer,Integer> sutAlwaysThrowingUnchecked = LBiObjBoolPredicate.biObjBoolPred((a1,a2,a3) -> {
            throw new IndexOutOfBoundsException(ORIGINAL_MESSAGE);
    });


    @Test
    public void testTheResult() throws Throwable {
        assertThat(sut.doTest(100,100,true))
            .isEqualTo(testValue);
    }

    @Test
    public void testTupleCall() throws Throwable {

        LBiObjBoolTriple<Integer,Integer> domainObject = Tuple4U.biObjBoolTriple(100,100,true);

        Object result = sut.tupleTest(domainObject);

        assertThat(result)
            .isEqualTo(testValue);
    }

    @Test
    public void testNonNullDoTest() throws Throwable {
        assertThat(sut.nonNullDoTest(100,100,true))
            .isEqualTo(testValue);
    }

    @Test
    public void testNestingDoTestUnchecked() throws Throwable {

        // then
        try {
            sutAlwaysThrowingUnchecked.nestingDoTest(100,100,true);
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
            sutAlwaysThrowingUnchecked.shovingDoTest(100,100,true);
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
        assertThat(sut.doApplyAsBoolean(100,100,true))
            .isEqualTo(testValue);

    }


    @Test
    public void testFunctionalInterfaceDescription() throws Throwable {
        assertThat(sut.functionalInterfaceDescription())
            .isEqualTo("LBiObjBoolPredicate: boolean doTest(T1 a1,T2 a2,boolean a3)");
    }

    @Test
    public void testBiObjBoolPredMethod() throws Throwable {
        assertThat(LBiObjBoolPredicate.biObjBoolPred((a1,a2,a3) -> testValue ))
            .isInstanceOf(LBiObjBoolPredicate.class);
    }




    @Test
    public void testnegate() throws Throwable {
        assertThat(sut.negate().doTest(100,100,true))
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
        LBiObjBoolPredicate<Integer,Integer> fun1 = LBiObjBoolPredicate.biObjBoolPred((a1,a2,a3) -> f1Result);
        LBiObjBoolPredicate<Integer,Integer> fun2 = LBiObjBoolPredicate.biObjBoolPred((a1,a2,a3) -> f2Result);

        //when
        LBiObjBoolPredicate<Integer,Integer> andFunction = fun1.and(fun2);
        LBiObjBoolPredicate<Integer,Integer> orFunction = fun1.or(fun2);
        LBiObjBoolPredicate<Integer,Integer> xorFunction = fun1.xor(fun2);

        //then
        assertThat(andFunction.doTest(100,100,true))
                .isEqualTo(andResult);

        assertThat(orFunction.doTest(100,100,true))
                .isEqualTo(orResult);

        assertThat(xorFunction.doTest(100,100,true))
                .isEqualTo(xorResult);
    }

    @Test
    public void testIsEqual() throws Throwable  {
        //when
        LBiObjBoolPredicate<Integer,Integer> equals = LBiObjBoolPredicate.isEqual(1,1,true);

        //then
        assertThat(equals.doTest(1,1,true))
                .isTrue();

        assertThat(equals.doTest(0,0,false))
                .isFalse();
    }



    // <editor-fold desc="compose (functional)">

    @Test
    public void testBiObjBoolPredComposeBool() throws Throwable {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LBiObjBoolPredicate<Integer,Integer> sutO = (a1,a2,a3) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(90);
                assertThat(a2).isEqualTo(91);
                assertThat(a3).isEqualTo(true);
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
        LLogicalOperator before3 = p2 -> {
            assertThat(p2).isEqualTo(true);
            beforeCalls.incrementAndGet();
            return true;
        };

        //when
        LBiObjBoolPredicate<Integer,Integer> function = sutO.biObjBoolPredComposeBool(before1,before2,before3);
        function.doTest(80,81,true);

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(beforeCalls.get()).isEqualTo(3);
    }


    @Test
    public void testBiObjBoolPredCompose() throws Throwable {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LBiObjBoolPredicate<Integer,Integer> sutO = (a1,a2,a3) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(90);
                assertThat(a2).isEqualTo(91);
                assertThat(a3).isEqualTo(true);
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
        LPredicate<Integer> before3 = p2 -> {
            assertThat(p2).isEqualTo(82);
            beforeCalls.incrementAndGet();
            return true;
        };

        //when
        LTriPredicate<Integer,Integer,Integer> function = sutO.biObjBoolPredCompose(before1,before2,before3);
        function.doTest(80,81,82);

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(beforeCalls.get()).isEqualTo(3);
    }

    // </editor-fold>



    // <editor-fold desc="then (functional)">

    @Test
    public void testBoolToBiObjBoolFunc0() throws Throwable  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);

        //given (+ some assertions)
        LBiObjBoolPredicate<Integer,Integer> sutO = (a1,a2,a3) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(80);
                assertThat(a2).isEqualTo(81);
                assertThat(a3).isEqualTo(true);
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
        LBiObjBoolFunction<Integer,Integer,Integer> function = sutO.boolToBiObjBoolFunc(thenFunction);
        Integer finalValue = function.doApply(80,81,true);

        //then - finals
        assertThat(finalValue).isEqualTo(100);
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    @Test
    public void testBoolToBiObjBoolPred1() throws Throwable  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);

        //given (+ some assertions)
        LBiObjBoolPredicate<Integer,Integer> sutO = (a1,a2,a3) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(80);
                assertThat(a2).isEqualTo(81);
                assertThat(a3).isEqualTo(true);
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
        LBiObjBoolPredicate<Integer,Integer> function = sutO.boolToBiObjBoolPred(thenFunction);
        boolean finalValue = function.doTest(80,81,true);

        //then - finals
        assertThat(finalValue).isEqualTo(true);
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    // </editor-fold>

    @Test
    public void testNesting() {
        assertThat(sut.nestingBiObjBoolPred())
            .isSameAs(sut)
            .isInstanceOf(LBiObjBoolPredicate.class);
    }

    @Test
    public void testShoving() {
        assertThat(sut.shovingBiObjBoolPred())
            .isSameAs(sut)
            .isInstanceOf(LBiObjBoolPredicate.class);
    }


    @Test(expectedExceptions = RuntimeException.class)
    public void testShove() {

        // given
        LBiObjBoolPredicate<Integer,Integer> sutThrowing = LBiObjBoolPredicate.biObjBoolPred((a1,a2,a3) -> {
            throw new UnsupportedOperationException();
        });

        // when
        sutThrowing.shovingBiObjBoolPred().doTest(100,100,true);
    }


    @Test
    public void testToString() throws Throwable {

        assertThat(sut.toString())
                .isInstanceOf(String.class)
                .startsWith(this.getClass().getName()+"$");

        assertThat(String.format("%s", sut))
                .isInstanceOf(String.class)
                .contains("LBiObjBoolPredicate: boolean doTest(T1 a1,T2 a2,boolean a3)");
    }


    @Test
    public void isThrowing() {
        assertThat(sut.isThrowing())
            .isFalse();
    }

    //<editor-fold desc="Variants">

    private boolean variantLObjBoolObj1Pred(Integer a1,boolean a3,Integer a2) {
        return true;
    }

    @Test
    public void compilerSubstituteVariantLObjBoolObj1Pred() {
        LBiObjBoolPredicate lambda = LBiObjBoolPredicate./*<T1,T2>*/objBoolObj1Pred(this::variantLObjBoolObj1Pred);

        assertThat(lambda).isInstanceOf(LBiObjBoolPredicate.LObjBoolObj1Pred.class);
    }


    private boolean variantLObj1Obj0BoolPred(Integer a2,Integer a1,boolean a3) {
        return true;
    }

    @Test
    public void compilerSubstituteVariantLObj1Obj0BoolPred() {
        LBiObjBoolPredicate lambda = LBiObjBoolPredicate./*<T1,T2>*/obj1Obj0BoolPred(this::variantLObj1Obj0BoolPred);

        assertThat(lambda).isInstanceOf(LBiObjBoolPredicate.LObj1Obj0BoolPred.class);
    }


    private boolean variantLObj1BoolObj0Pred(Integer a2,boolean a3,Integer a1) {
        return true;
    }

    @Test
    public void compilerSubstituteVariantLObj1BoolObj0Pred() {
        LBiObjBoolPredicate lambda = LBiObjBoolPredicate./*<T1,T2>*/obj1BoolObj0Pred(this::variantLObj1BoolObj0Pred);

        assertThat(lambda).isInstanceOf(LBiObjBoolPredicate.LObj1BoolObj0Pred.class);
    }


    private boolean variantLBoolObj0Obj1Pred(boolean a3,Integer a1,Integer a2) {
        return true;
    }

    @Test
    public void compilerSubstituteVariantLBoolObj0Obj1Pred() {
        LBiObjBoolPredicate lambda = LBiObjBoolPredicate./*<T1,T2>*/boolObj0Obj1Pred(this::variantLBoolObj0Obj1Pred);

        assertThat(lambda).isInstanceOf(LBiObjBoolPredicate.LBoolObj0Obj1Pred.class);
    }


    private boolean variantLBoolObjObj0Pred(boolean a3,Integer a2,Integer a1) {
        return true;
    }

    @Test
    public void compilerSubstituteVariantLBoolObjObj0Pred() {
        LBiObjBoolPredicate lambda = LBiObjBoolPredicate./*<T1,T2>*/boolObjObj0Pred(this::variantLBoolObjObj0Pred);

        assertThat(lambda).isInstanceOf(LBiObjBoolPredicate.LBoolObjObj0Pred.class);
    }

    //</editor-fold>


    @Test void safeCompiles() {
        LBiObjBoolPredicate r1 = LBiObjBoolPredicate.safe(sut); //NOSONAR
    }

    @Test void safePropagates() {
        Object result = LBiObjBoolPredicate.safe(sut);
        assertThat(result).isSameAs(sut);
    }

    @Test void safeProtectsAgainstNpe() {
        Object result = LBiObjBoolPredicate.safe(null);
        assertThat(result).isSameAs(LBiObjBoolPredicate.biObjBoolPred(LBiObjBoolPredicate.safe()));
    }

    @Test  void safeSupplierPropagates() {
        LSupplier<LBiObjBoolPredicate<Integer,Integer>> supplier = ()->sut;
        Object result = LBiObjBoolPredicate.safeSupplier(supplier);
        assertThat(result).isSameAs(supplier);
    }

    @Test  void safeSupplierProtectsAgainstNpe() {
        Object result = LBiObjBoolPredicate.safeSupplier(null);
        assertThat(result).isSameAs(LBiObjBoolPredicate.safeSupplier());
    }

    @Test  void safeSupplierCompiles() {
        LSupplier<LBiObjBoolPredicate<Integer,Integer>> r1 = LBiObjBoolPredicate.safeSupplier(()->sut);  //NOSONAR
    }

}
