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
public class LBiObjDblPredicateTest<T1,T2> {
    private static final String ORIGINAL_MESSAGE = "Original message";
    private static final String EXCEPTION_WAS_WRAPPED = "Exception was wrapped.";
    private static final String NO_EXCEPTION_WERE_THROWN = "No exception were thrown.";

    private boolean testValue = true;



    private LBiObjDblPredicate<Integer,Integer> sut = new LBiObjDblPredicate<Integer,Integer>(){
        public  boolean doTestX(Integer a1,Integer a2,double a3)  {
            return testValue;
        }
    };




    private LBiObjDblPredicate<Integer,Integer> sutAlwaysThrowing = LBiObjDblPredicate.biObjDblPred((a1,a2,a3) -> {
            throw new ParseException(ORIGINAL_MESSAGE, 0);
    });

    private LBiObjDblPredicate<Integer,Integer> sutAlwaysThrowingUnchecked = LBiObjDblPredicate.biObjDblPred((a1,a2,a3) -> {
            throw new IndexOutOfBoundsException(ORIGINAL_MESSAGE);
    });


    @Test
    public void testTheResult() throws Throwable {
        assertThat(sut.doTest(100,100,100d))
            .isEqualTo(testValue);
    }

    @Test
    public void testTupleCall() throws Throwable {

        LBiObjDblTriple<Integer,Integer> domainObject = Tuple4U.biObjDblTriple(100,100,100d);

        Object result = sut.tupleTest(domainObject);

        assertThat(result)
            .isEqualTo(testValue);
    }

    @Test
    public void testNonNullDoTest() throws Throwable {
        assertThat(sut.nonNullDoTest(100,100,100d))
            .isEqualTo(testValue);
    }

    @Test
    public void testNestingDoTestUnchecked() throws Throwable {

        // then
        try {
            sutAlwaysThrowingUnchecked.nestingDoTest(100,100,100d);
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
            sutAlwaysThrowingUnchecked.shovingDoTest(100,100,100d);
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
        assertThat(sut.doApplyAsBoolean(100,100,100d))
            .isEqualTo(testValue);

    }


    @Test
    public void testFunctionalInterfaceDescription() throws Throwable {
        assertThat(sut.functionalInterfaceDescription())
            .isEqualTo("LBiObjDblPredicate: boolean doTest(T1 a1,T2 a2,double a3)");
    }

    @Test
    public void testBiObjDblPredMethod() throws Throwable {
        assertThat(LBiObjDblPredicate.biObjDblPred((a1,a2,a3) -> testValue ))
            .isInstanceOf(LBiObjDblPredicate.class);
    }




    @Test
    public void testnegate() throws Throwable {
        assertThat(sut.negate().doTest(100,100,100d))
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
        LBiObjDblPredicate<Integer,Integer> fun1 = LBiObjDblPredicate.biObjDblPred((a1,a2,a3) -> f1Result);
        LBiObjDblPredicate<Integer,Integer> fun2 = LBiObjDblPredicate.biObjDblPred((a1,a2,a3) -> f2Result);

        //when
        LBiObjDblPredicate<Integer,Integer> andFunction = fun1.and(fun2);
        LBiObjDblPredicate<Integer,Integer> orFunction = fun1.or(fun2);
        LBiObjDblPredicate<Integer,Integer> xorFunction = fun1.xor(fun2);

        //then
        assertThat(andFunction.doTest(100,100,100d))
                .isEqualTo(andResult);

        assertThat(orFunction.doTest(100,100,100d))
                .isEqualTo(orResult);

        assertThat(xorFunction.doTest(100,100,100d))
                .isEqualTo(xorResult);
    }

    @Test
    public void testIsEqual() throws Throwable  {
        //when
        LBiObjDblPredicate<Integer,Integer> equals = LBiObjDblPredicate.isEqual(1,1,1d);

        //then
        assertThat(equals.doTest(1,1,1d))
                .isTrue();

        assertThat(equals.doTest(0,0,0d))
                .isFalse();
    }



    // <editor-fold desc="compose (functional)">

    @Test
    public void testBiObjDblPredComposeDbl() throws Throwable {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LBiObjDblPredicate<Integer,Integer> sutO = (a1,a2,a3) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(90);
                assertThat(a2).isEqualTo(91);
                assertThat(a3).isEqualTo(92d);
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
        LDblUnaryOperator before3 = p2 -> {
            assertThat(p2).isEqualTo(82d);
            beforeCalls.incrementAndGet();
            return 92d;
        };

        //when
        LBiObjDblPredicate<Integer,Integer> function = sutO.biObjDblPredComposeDbl(before1,before2,before3);
        function.doTest(80,81,82d);

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(beforeCalls.get()).isEqualTo(3);
    }


    @Test
    public void testBiObjDblPredCompose() throws Throwable {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LBiObjDblPredicate<Integer,Integer> sutO = (a1,a2,a3) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(90);
                assertThat(a2).isEqualTo(91);
                assertThat(a3).isEqualTo(92d);
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
        LToDblFunction<Integer> before3 = p2 -> {
            assertThat(p2).isEqualTo(82);
            beforeCalls.incrementAndGet();
            return 92d;
        };

        //when
        LTriPredicate<Integer,Integer,Integer> function = sutO.biObjDblPredCompose(before1,before2,before3);
        function.doTest(80,81,82);

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(beforeCalls.get()).isEqualTo(3);
    }

    // </editor-fold>



    // <editor-fold desc="then (functional)">

    @Test
    public void testBoolToBiObjDblFunc0() throws Throwable  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);

        //given (+ some assertions)
        LBiObjDblPredicate<Integer,Integer> sutO = (a1,a2,a3) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(80);
                assertThat(a2).isEqualTo(81);
                assertThat(a3).isEqualTo(82d);
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
        LBiObjDblFunction<Integer,Integer,Integer> function = sutO.boolToBiObjDblFunc(thenFunction);
        Integer finalValue = function.doApply(80,81,82d);

        //then - finals
        assertThat(finalValue).isEqualTo(100);
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    @Test
    public void testBoolToBiObjDblPred1() throws Throwable  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);

        //given (+ some assertions)
        LBiObjDblPredicate<Integer,Integer> sutO = (a1,a2,a3) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(80);
                assertThat(a2).isEqualTo(81);
                assertThat(a3).isEqualTo(82d);
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
        LBiObjDblPredicate<Integer,Integer> function = sutO.boolToBiObjDblPred(thenFunction);
        boolean finalValue = function.doTest(80,81,82d);

        //then - finals
        assertThat(finalValue).isEqualTo(true);
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    // </editor-fold>

    @Test
    public void testNesting() {
        assertThat(sut.nestingBiObjDblPred())
            .isSameAs(sut)
            .isInstanceOf(LBiObjDblPredicate.class);
    }

    @Test
    public void testShoving() {
        assertThat(sut.shovingBiObjDblPred())
            .isSameAs(sut)
            .isInstanceOf(LBiObjDblPredicate.class);
    }


    @Test(expectedExceptions = RuntimeException.class)
    public void testShove() {

        // given
        LBiObjDblPredicate<Integer,Integer> sutThrowing = LBiObjDblPredicate.biObjDblPred((a1,a2,a3) -> {
            throw new UnsupportedOperationException();
        });

        // when
        sutThrowing.shovingBiObjDblPred().doTest(100,100,100d);
    }


    @Test
    public void testToString() throws Throwable {

        assertThat(sut.toString())
                .isInstanceOf(String.class)
                .startsWith(this.getClass().getName()+"$");

        assertThat(String.format("%s", sut))
                .isInstanceOf(String.class)
                .contains("LBiObjDblPredicate: boolean doTest(T1 a1,T2 a2,double a3)");
    }


    @Test
    public void isThrowing() {
        assertThat(sut.isThrowing())
            .isFalse();
    }

    //<editor-fold desc="Variants">

    private boolean variantLObjDblObj1Pred(Integer a1,double a3,Integer a2) {
        return true;
    }

    @Test
    public void compilerSubstituteVariantLObjDblObj1Pred() {
        LBiObjDblPredicate lambda = LBiObjDblPredicate./*<T1,T2>*/objDblObj1Pred(this::variantLObjDblObj1Pred);

        assertThat(lambda).isInstanceOf(LBiObjDblPredicate.LObjDblObj1Pred.class);
    }


    private boolean variantLObj1Obj0DblPred(Integer a2,Integer a1,double a3) {
        return true;
    }

    @Test
    public void compilerSubstituteVariantLObj1Obj0DblPred() {
        LBiObjDblPredicate lambda = LBiObjDblPredicate./*<T1,T2>*/obj1Obj0DblPred(this::variantLObj1Obj0DblPred);

        assertThat(lambda).isInstanceOf(LBiObjDblPredicate.LObj1Obj0DblPred.class);
    }


    private boolean variantLObj1DblObj0Pred(Integer a2,double a3,Integer a1) {
        return true;
    }

    @Test
    public void compilerSubstituteVariantLObj1DblObj0Pred() {
        LBiObjDblPredicate lambda = LBiObjDblPredicate./*<T1,T2>*/obj1DblObj0Pred(this::variantLObj1DblObj0Pred);

        assertThat(lambda).isInstanceOf(LBiObjDblPredicate.LObj1DblObj0Pred.class);
    }


    private boolean variantLDblObj0Obj1Pred(double a3,Integer a1,Integer a2) {
        return true;
    }

    @Test
    public void compilerSubstituteVariantLDblObj0Obj1Pred() {
        LBiObjDblPredicate lambda = LBiObjDblPredicate./*<T1,T2>*/dblObj0Obj1Pred(this::variantLDblObj0Obj1Pred);

        assertThat(lambda).isInstanceOf(LBiObjDblPredicate.LDblObj0Obj1Pred.class);
    }


    private boolean variantLDblObjObj0Pred(double a3,Integer a2,Integer a1) {
        return true;
    }

    @Test
    public void compilerSubstituteVariantLDblObjObj0Pred() {
        LBiObjDblPredicate lambda = LBiObjDblPredicate./*<T1,T2>*/dblObjObj0Pred(this::variantLDblObjObj0Pred);

        assertThat(lambda).isInstanceOf(LBiObjDblPredicate.LDblObjObj0Pred.class);
    }

    //</editor-fold>


    @Test void safeCompiles() {
        LBiObjDblPredicate r1 = LBiObjDblPredicate.safe(sut); //NOSONAR
    }

    @Test void safePropagates() {
        Object result = LBiObjDblPredicate.safe(sut);
        assertThat(result).isSameAs(sut);
    }

    @Test void safeProtectsAgainstNpe() {
        Object result = LBiObjDblPredicate.safe(null);
        assertThat(result).isSameAs(LBiObjDblPredicate.biObjDblPred(LBiObjDblPredicate.safe()));
    }

    @Test  void safeSupplierPropagates() {
        LSupplier<LBiObjDblPredicate<Integer,Integer>> supplier = ()->sut;
        Object result = LBiObjDblPredicate.safeSupplier(supplier);
        assertThat(result).isSameAs(supplier);
    }

    @Test  void safeSupplierProtectsAgainstNpe() {
        Object result = LBiObjDblPredicate.safeSupplier(null);
        assertThat(result).isSameAs(LBiObjDblPredicate.safeSupplier());
    }

    @Test  void safeSupplierCompiles() {
        LSupplier<LBiObjDblPredicate<Integer,Integer>> r1 = LBiObjDblPredicate.safeSupplier(()->sut);  //NOSONAR
    }

}
