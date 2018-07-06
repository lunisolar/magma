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
public class LObjIntLongPredicateTest<T> {
    private static final String ORIGINAL_MESSAGE = "Original message";
    private static final String EXCEPTION_WAS_WRAPPED = "Exception was wrapped.";
    private static final String NO_EXCEPTION_WERE_THROWN = "No exception were thrown.";

    private boolean testValue = true;



    private LObjIntLongPredicate<Integer> sut = new LObjIntLongPredicate<Integer>(){
        public  boolean doTestX(Integer a1,int a2,long a3)  {
            return testValue;
        }
    };




    private LObjIntLongPredicate<Integer> sutAlwaysThrowing = LObjIntLongPredicate.objIntLongPred((a1,a2,a3) -> {
            throw new ParseException(ORIGINAL_MESSAGE, 0);
    });

    private LObjIntLongPredicate<Integer> sutAlwaysThrowingUnchecked = LObjIntLongPredicate.objIntLongPred((a1,a2,a3) -> {
            throw new IndexOutOfBoundsException(ORIGINAL_MESSAGE);
    });


    @Test
    public void testTheResult() throws Throwable {
        assertThat(sut.doTest(100,100,100L))
            .isEqualTo(testValue);
    }

    @Test
    public void testTupleCall() throws Throwable {

        LObjIntLongTriple<Integer> domainObject = Tuple4U.objIntLongTriple(100,100,100L);

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
            .isEqualTo("LObjIntLongPredicate: boolean doTest(T a1,int a2,long a3)");
    }

    @Test
    public void testObjIntLongPredMethod() throws Throwable {
        assertThat(LObjIntLongPredicate.objIntLongPred((a1,a2,a3) -> testValue ))
            .isInstanceOf(LObjIntLongPredicate.class);
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
        LObjIntLongPredicate<Integer> fun1 = LObjIntLongPredicate.objIntLongPred((a1,a2,a3) -> f1Result);
        LObjIntLongPredicate<Integer> fun2 = LObjIntLongPredicate.objIntLongPred((a1,a2,a3) -> f2Result);

        //when
        LObjIntLongPredicate<Integer> andFunction = fun1.and(fun2);
        LObjIntLongPredicate<Integer> orFunction = fun1.or(fun2);
        LObjIntLongPredicate<Integer> xorFunction = fun1.xor(fun2);

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
        LObjIntLongPredicate<Integer> equals = LObjIntLongPredicate.isEqual(1,1,1L);

        //then
        assertThat(equals.doTest(1,1,1L))
                .isTrue();

        assertThat(equals.doTest(0,0,0L))
                .isFalse();
    }



    // <editor-fold desc="compose (functional)">

    @Test
    public void testObjIntLongPredComposeIntLong() throws Throwable {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LObjIntLongPredicate<Integer> sutO = (a1,a2,a3) -> {
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
        LIntUnaryOperator before2 = p1 -> {
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
        LObjIntLongPredicate<Integer> function = sutO.objIntLongPredComposeIntLong(before1,before2,before3);
        function.doTest(80,81,82L);

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(beforeCalls.get()).isEqualTo(3);
    }


    @Test
    public void testObjIntLongPredCompose() throws Throwable {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LObjIntLongPredicate<Integer> sutO = (a1,a2,a3) -> {
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
        LToIntFunction<Integer> before2 = p1 -> {
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
        LTriPredicate<Integer,Integer,Integer> function = sutO.objIntLongPredCompose(before1,before2,before3);
        function.doTest(80,81,82);

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(beforeCalls.get()).isEqualTo(3);
    }

    // </editor-fold>



    // <editor-fold desc="then (functional)">

    @Test
    public void testBoolToObjIntLongFunc0() throws Throwable  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);

        //given (+ some assertions)
        LObjIntLongPredicate<Integer> sutO = (a1,a2,a3) -> {
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
        LObjIntLongFunction<Integer,Integer> function = sutO.boolToObjIntLongFunc(thenFunction);
        Integer finalValue = function.doApply(80,81,82L);

        //then - finals
        assertThat(finalValue).isEqualTo(100);
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    @Test
    public void testBoolToTieLongFunc1() throws Throwable  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);

        //given (+ some assertions)
        LObjIntLongPredicate<Integer> sutO = (a1,a2,a3) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(80);
                assertThat(a2).isEqualTo(81);
                assertThat(a3).isEqualTo(82L);
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
        LTieLongFunction<Integer> function = sutO.boolToTieLongFunc(thenFunction);
        int finalValue = function.doApplyAsInt(80,81,82L);

        //then - finals
        assertThat(finalValue).isEqualTo(100);
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    @Test
    public void testBoolToObjIntLongPred2() throws Throwable  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);

        //given (+ some assertions)
        LObjIntLongPredicate<Integer> sutO = (a1,a2,a3) -> {
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
        LObjIntLongPredicate<Integer> function = sutO.boolToObjIntLongPred(thenFunction);
        boolean finalValue = function.doTest(80,81,82L);

        //then - finals
        assertThat(finalValue).isEqualTo(true);
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    // </editor-fold>

    @Test
    public void testNesting() {
        assertThat(sut.nestingObjIntLongPred())
            .isSameAs(sut)
            .isInstanceOf(LObjIntLongPredicate.class);
    }

    @Test
    public void testShoving() {
        assertThat(sut.shovingObjIntLongPred())
            .isSameAs(sut)
            .isInstanceOf(LObjIntLongPredicate.class);
    }


    @Test(expectedExceptions = RuntimeException.class)
    public void testShove() {

        // given
        LObjIntLongPredicate<Integer> sutThrowing = LObjIntLongPredicate.objIntLongPred((a1,a2,a3) -> {
            throw new UnsupportedOperationException();
        });

        // when
        sutThrowing.shovingObjIntLongPred().doTest(100,100,100L);
    }


    @Test
    public void testToString() throws Throwable {

        assertThat(sut.toString())
                .isInstanceOf(String.class)
                .startsWith(this.getClass().getName()+"$");

        assertThat(String.format("%s", sut))
                .isInstanceOf(String.class)
                .contains("LObjIntLongPredicate: boolean doTest(T a1,int a2,long a3)");
    }


    @Test
    public void isThrowing() {
        assertThat(sut.isThrowing())
            .isFalse();
    }

    //<editor-fold desc="Variants">

    private boolean variantLObjLongIntPred(Integer a1,long a3,int a2) {
        return true;
    }

    @Test
    public void compilerSubstituteVariantLObjLongIntPred() {
        LObjIntLongPredicate lambda = LObjIntLongPredicate./*<T>*/objLongIntPred(this::variantLObjLongIntPred);

        assertThat(lambda).isInstanceOf(LObjIntLongPredicate.LObjLongIntPred.class);
    }


    private boolean variantLIntObjLongPred(int a2,Integer a1,long a3) {
        return true;
    }

    @Test
    public void compilerSubstituteVariantLIntObjLongPred() {
        LObjIntLongPredicate lambda = LObjIntLongPredicate./*<T>*/intObjLongPred(this::variantLIntObjLongPred);

        assertThat(lambda).isInstanceOf(LObjIntLongPredicate.LIntObjLongPred.class);
    }


    private boolean variantLIntLongObjPred(int a2,long a3,Integer a1) {
        return true;
    }

    @Test
    public void compilerSubstituteVariantLIntLongObjPred() {
        LObjIntLongPredicate lambda = LObjIntLongPredicate./*<T>*/intLongObjPred(this::variantLIntLongObjPred);

        assertThat(lambda).isInstanceOf(LObjIntLongPredicate.LIntLongObjPred.class);
    }


    private boolean variantLLongObjIntPred(long a3,Integer a1,int a2) {
        return true;
    }

    @Test
    public void compilerSubstituteVariantLLongObjIntPred() {
        LObjIntLongPredicate lambda = LObjIntLongPredicate./*<T>*/longObjIntPred(this::variantLLongObjIntPred);

        assertThat(lambda).isInstanceOf(LObjIntLongPredicate.LLongObjIntPred.class);
    }


    private boolean variantLLongIntObjPred(long a3,int a2,Integer a1) {
        return true;
    }

    @Test
    public void compilerSubstituteVariantLLongIntObjPred() {
        LObjIntLongPredicate lambda = LObjIntLongPredicate./*<T>*/longIntObjPred(this::variantLLongIntObjPred);

        assertThat(lambda).isInstanceOf(LObjIntLongPredicate.LLongIntObjPred.class);
    }

    //</editor-fold>


    @Test void safeCompiles() {
        LObjIntLongPredicate r1 = LObjIntLongPredicate.safe(sut); //NOSONAR
    }

    @Test void safePropagates() {
        Object result = LObjIntLongPredicate.safe(sut);
        assertThat(result).isSameAs(sut);
    }

    @Test void safeProtectsAgainstNpe() {
        Object result = LObjIntLongPredicate.safe(null);
        assertThat(result).isSameAs(LObjIntLongPredicate.objIntLongPred(LObjIntLongPredicate.safe()));
    }

    @Test  void safeSupplierPropagates() {
        LSupplier<LObjIntLongPredicate<Integer>> supplier = ()->sut;
        Object result = LObjIntLongPredicate.safeSupplier(supplier);
        assertThat(result).isSameAs(supplier);
    }

    @Test  void safeSupplierProtectsAgainstNpe() {
        Object result = LObjIntLongPredicate.safeSupplier(null);
        assertThat(result).isSameAs(LObjIntLongPredicate.safeSupplier());
    }

    @Test  void safeSupplierCompiles() {
        LSupplier<LObjIntLongPredicate<Integer>> r1 = LObjIntLongPredicate.safeSupplier(()->sut);  //NOSONAR
    }

}
