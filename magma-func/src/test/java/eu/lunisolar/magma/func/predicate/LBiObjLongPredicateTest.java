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
public class LBiObjLongPredicateTest<T1,T2> {
    private static final String ORIGINAL_MESSAGE = "Original message";
    private static final String EXCEPTION_WAS_WRAPPED = "Exception was wrapped.";
    private static final String NO_EXCEPTION_WERE_THROWN = "No exception were thrown.";

    private boolean testValue = true;



    private LBiObjLongPredicate<Integer,Integer> sut = new LBiObjLongPredicate<Integer,Integer>(){
        public  boolean doTestX(Integer a1,Integer a2,long a3)  {
            return testValue;
        }
    };




    private LBiObjLongPredicate<Integer,Integer> sutAlwaysThrowing = LBiObjLongPredicate.biObjLongPred((a1,a2,a3) -> {
            throw new ParseException(ORIGINAL_MESSAGE, 0);
    });

    private LBiObjLongPredicate<Integer,Integer> sutAlwaysThrowingUnchecked = LBiObjLongPredicate.biObjLongPred((a1,a2,a3) -> {
            throw new IndexOutOfBoundsException(ORIGINAL_MESSAGE);
    });


    @Test
    public void testTheResult() throws Throwable {
        assertThat(sut.doTest(100,100,100L))
            .isEqualTo(testValue);
    }

    @Test
    public void testTupleCall() throws Throwable {

        LBiObjLongTriple<Integer,Integer> domainObject = Tuple4U.biObjLongTriple(100,100,100L);

        Object result = sut.tupleTest(domainObject);

        assertThat(result)
            .isEqualTo(testValue);
    }

    @Test
    public void testNonNullDoTest() throws Throwable {
        assertThat(sut.nonNullDoTest(100,100,100L))
            .isEqualTo(testValue);
    }

    @Test
    public void testNestingDoTestUnchecked() throws Throwable {

        // then
        try {
            sutAlwaysThrowingUnchecked.nestingDoTest(100,100,100L);
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
            sutAlwaysThrowingUnchecked.shovingDoTest(100,100,100L);
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
        assertThat(sut.doApplyAsBoolean(100,100,100L))
            .isEqualTo(testValue);

    }


    @Test
    public void testFunctionalInterfaceDescription() throws Throwable {
        assertThat(sut.functionalInterfaceDescription())
            .isEqualTo("LBiObjLongPredicate: boolean doTest(T1 a1,T2 a2,long a3)");
    }

    @Test
    public void testBiObjLongPredMethod() throws Throwable {
        assertThat(LBiObjLongPredicate.biObjLongPred((a1,a2,a3) -> testValue ))
            .isInstanceOf(LBiObjLongPredicate.class);
    }




    @Test
    public void testnegate() throws Throwable {
        assertThat(sut.negate().doTest(100,100,100L))
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
        LBiObjLongPredicate<Integer,Integer> fun1 = LBiObjLongPredicate.biObjLongPred((a1,a2,a3) -> f1Result);
        LBiObjLongPredicate<Integer,Integer> fun2 = LBiObjLongPredicate.biObjLongPred((a1,a2,a3) -> f2Result);

        //when
        LBiObjLongPredicate<Integer,Integer> andFunction = fun1.and(fun2);
        LBiObjLongPredicate<Integer,Integer> orFunction = fun1.or(fun2);
        LBiObjLongPredicate<Integer,Integer> xorFunction = fun1.xor(fun2);

        //then
        assertThat(andFunction.doTest(100,100,100L))
                .isEqualTo(andResult);

        assertThat(orFunction.doTest(100,100,100L))
                .isEqualTo(orResult);

        assertThat(xorFunction.doTest(100,100,100L))
                .isEqualTo(xorResult);
    }

    @Test
    public void testIsEqual() throws Throwable  {
        //when
        LBiObjLongPredicate<Integer,Integer> equals = LBiObjLongPredicate.isEqual(1,1,1L);

        //then
        assertThat(equals.doTest(1,1,1L))
                .isTrue();

        assertThat(equals.doTest(0,0,0L))
                .isFalse();
    }



    // <editor-fold desc="compose (functional)">

    @Test
    public void testBiObjLongPredComposeLong() throws Throwable {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LBiObjLongPredicate<Integer,Integer> sutO = (a1,a2,a3) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(90);
                assertThat(a2).isEqualTo(91);
                assertThat(a3).isEqualTo(92L);
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
        LLongUnaryOperator before3 = p2 -> {
            assertThat(p2).isEqualTo(82L);
            beforeCalls.incrementAndGet();
            return 92L;
        };

        //when
        LBiObjLongPredicate<Integer,Integer> function = sutO.biObjLongPredComposeLong(before1,before2,before3);
        function.doTest(80,81,82L);

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(beforeCalls.get()).isEqualTo(3);
    }


    @Test
    public void testBiObjLongPredCompose() throws Throwable {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LBiObjLongPredicate<Integer,Integer> sutO = (a1,a2,a3) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(90);
                assertThat(a2).isEqualTo(91);
                assertThat(a3).isEqualTo(92L);
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
        LToLongFunction<Integer> before3 = p2 -> {
            assertThat(p2).isEqualTo(82);
            beforeCalls.incrementAndGet();
            return 92L;
        };

        //when
        LTriPredicate<Integer,Integer,Integer> function = sutO.biObjLongPredCompose(before1,before2,before3);
        function.doTest(80,81,82);

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(beforeCalls.get()).isEqualTo(3);
    }

    // </editor-fold>



    // <editor-fold desc="then (functional)">

    @Test
    public void testBoolToBiObjLongFunc0() throws Throwable  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);

        //given (+ some assertions)
        LBiObjLongPredicate<Integer,Integer> sutO = (a1,a2,a3) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(80);
                assertThat(a2).isEqualTo(81);
                assertThat(a3).isEqualTo(82L);
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
        LBiObjLongFunction<Integer,Integer,Integer> function = sutO.boolToBiObjLongFunc(thenFunction);
        Integer finalValue = function.doApply(80,81,82L);

        //then - finals
        assertThat(finalValue).isEqualTo(100);
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    @Test
    public void testBoolToBiObjLongPred1() throws Throwable  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);

        //given (+ some assertions)
        LBiObjLongPredicate<Integer,Integer> sutO = (a1,a2,a3) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(80);
                assertThat(a2).isEqualTo(81);
                assertThat(a3).isEqualTo(82L);
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
        LBiObjLongPredicate<Integer,Integer> function = sutO.boolToBiObjLongPred(thenFunction);
        boolean finalValue = function.doTest(80,81,82L);

        //then - finals
        assertThat(finalValue).isEqualTo(true);
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    // </editor-fold>

    @Test
    public void testNesting() {
        assertThat(sut.nestingBiObjLongPred())
            .isSameAs(sut)
            .isInstanceOf(LBiObjLongPredicate.class);
    }

    @Test
    public void testShoving() {
        assertThat(sut.shovingBiObjLongPred())
            .isSameAs(sut)
            .isInstanceOf(LBiObjLongPredicate.class);
    }


    @Test(expectedExceptions = RuntimeException.class)
    public void testShove() {

        // given
        LBiObjLongPredicate<Integer,Integer> sutThrowing = LBiObjLongPredicate.biObjLongPred((a1,a2,a3) -> {
            throw new UnsupportedOperationException();
        });

        // when
        sutThrowing.shovingBiObjLongPred().doTest(100,100,100L);
    }


    @Test
    public void testToString() throws Throwable {

        assertThat(sut.toString())
                .isInstanceOf(String.class)
                .startsWith(this.getClass().getName()+"$");

        assertThat(String.format("%s", sut))
                .isInstanceOf(String.class)
                .contains("LBiObjLongPredicate: boolean doTest(T1 a1,T2 a2,long a3)");
    }


    @Test
    public void isThrowing() {
        assertThat(sut.isThrowing())
            .isFalse();
    }

    //<editor-fold desc="Variants">

    private boolean variantLObjLongObj1Pred(Integer a1,long a3,Integer a2) {
        return true;
    }

    @Test
    public void compilerSubstituteVariantLObjLongObj1Pred() {
        LBiObjLongPredicate lambda = LBiObjLongPredicate./*<T1,T2>*/objLongObj1Pred(this::variantLObjLongObj1Pred);

        assertThat(lambda).isInstanceOf(LBiObjLongPredicate.LObjLongObj1Pred.class);
    }


    private boolean variantLObj1Obj0LongPred(Integer a2,Integer a1,long a3) {
        return true;
    }

    @Test
    public void compilerSubstituteVariantLObj1Obj0LongPred() {
        LBiObjLongPredicate lambda = LBiObjLongPredicate./*<T1,T2>*/obj1Obj0LongPred(this::variantLObj1Obj0LongPred);

        assertThat(lambda).isInstanceOf(LBiObjLongPredicate.LObj1Obj0LongPred.class);
    }


    private boolean variantLObj1LongObj0Pred(Integer a2,long a3,Integer a1) {
        return true;
    }

    @Test
    public void compilerSubstituteVariantLObj1LongObj0Pred() {
        LBiObjLongPredicate lambda = LBiObjLongPredicate./*<T1,T2>*/obj1LongObj0Pred(this::variantLObj1LongObj0Pred);

        assertThat(lambda).isInstanceOf(LBiObjLongPredicate.LObj1LongObj0Pred.class);
    }


    private boolean variantLLongObj0Obj1Pred(long a3,Integer a1,Integer a2) {
        return true;
    }

    @Test
    public void compilerSubstituteVariantLLongObj0Obj1Pred() {
        LBiObjLongPredicate lambda = LBiObjLongPredicate./*<T1,T2>*/longObj0Obj1Pred(this::variantLLongObj0Obj1Pred);

        assertThat(lambda).isInstanceOf(LBiObjLongPredicate.LLongObj0Obj1Pred.class);
    }


    private boolean variantLLongObjObj0Pred(long a3,Integer a2,Integer a1) {
        return true;
    }

    @Test
    public void compilerSubstituteVariantLLongObjObj0Pred() {
        LBiObjLongPredicate lambda = LBiObjLongPredicate./*<T1,T2>*/longObjObj0Pred(this::variantLLongObjObj0Pred);

        assertThat(lambda).isInstanceOf(LBiObjLongPredicate.LLongObjObj0Pred.class);
    }

    //</editor-fold>


    @Test void safeCompiles() {
        LBiObjLongPredicate r1 = LBiObjLongPredicate.safe(sut); //NOSONAR
    }

    @Test void safePropagates() {
        Object result = LBiObjLongPredicate.safe(sut);
        assertThat(result).isSameAs(sut);
    }

    @Test void safeProtectsAgainstNpe() {
        Object result = LBiObjLongPredicate.safe(null);
        assertThat(result).isSameAs(LBiObjLongPredicate.biObjLongPred(LBiObjLongPredicate.safe()));
    }

    @Test  void safeSupplierPropagates() {
        LSupplier<LBiObjLongPredicate<Integer,Integer>> supplier = ()->sut;
        Object result = LBiObjLongPredicate.safeSupplier(supplier);
        assertThat(result).isSameAs(supplier);
    }

    @Test  void safeSupplierProtectsAgainstNpe() {
        Object result = LBiObjLongPredicate.safeSupplier(null);
        assertThat(result).isSameAs(LBiObjLongPredicate.safeSupplier());
    }

    @Test  void safeSupplierCompiles() {
        LSupplier<LBiObjLongPredicate<Integer,Integer>> r1 = LBiObjLongPredicate.safeSupplier(()->sut);  //NOSONAR
    }

}
