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
public class LObjIntDblPredicateTest<T> {
    private static final String ORIGINAL_MESSAGE = "Original message";
    private static final String EXCEPTION_WAS_WRAPPED = "Exception was wrapped.";
    private static final String NO_EXCEPTION_WERE_THROWN = "No exception were thrown.";

    private boolean testValue = true;



    private LObjIntDblPredicate<Integer> sut = new LObjIntDblPredicate<Integer>(){
        public  boolean testX(Integer a1,int a2,double a3)  {
            return testValue;
        }
    };




    private LObjIntDblPredicate<Integer> sutAlwaysThrowing = LObjIntDblPredicate.objIntDblPred((a1,a2,a3) -> {
            throw new ParseException(ORIGINAL_MESSAGE, 0);
    });

    private LObjIntDblPredicate<Integer> sutAlwaysThrowingUnchecked = LObjIntDblPredicate.objIntDblPred((a1,a2,a3) -> {
            throw new IndexOutOfBoundsException(ORIGINAL_MESSAGE);
    });


    @Test
    public void testTheResult() throws Throwable {
        assertThat(sut.test(100,100,100d))
            .isEqualTo(testValue);
    }

    @Test
    public void testTupleCall() throws Throwable {

        LObjIntDblTriple<Integer> domainObject = Tuple4U.objIntDblTriple(100,100,100d);

        Object result = sut.tupleTest(domainObject);

        assertThat(result)
            .isEqualTo(testValue);
    }

    @Test
    public void testNonNullTest() throws Throwable {
        assertThat(sut.nonNullTest(100,100,100d))
            .isEqualTo(testValue);
    }

    @Test
    public void testNestingTestUnchecked() throws Throwable {

        // then
        try {
            sutAlwaysThrowingUnchecked.nestingTest(100,100,100d);
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
            sutAlwaysThrowingUnchecked.shovingTest(100,100,100d);
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
            .isEqualTo("LObjIntDblPredicate: boolean test(T a1,int a2,double a3)");
    }

    @Test
    public void testObjIntDblPredMethod() throws Throwable {
        assertThat(LObjIntDblPredicate.objIntDblPred((a1,a2,a3) -> testValue ))
            .isInstanceOf(LObjIntDblPredicate.class);
    }




    @Test
    public void testnegate() throws Throwable {
        assertThat(sut.negate().test(100,100,100d))
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
        LObjIntDblPredicate<Integer> fun1 = LObjIntDblPredicate.objIntDblPred((a1,a2,a3) -> f1Result);
        LObjIntDblPredicate<Integer> fun2 = LObjIntDblPredicate.objIntDblPred((a1,a2,a3) -> f2Result);

        //when
        LObjIntDblPredicate<Integer> andFunction = fun1.and(fun2);
        LObjIntDblPredicate<Integer> orFunction = fun1.or(fun2);
        LObjIntDblPredicate<Integer> xorFunction = fun1.xor(fun2);

        //then
        assertThat(andFunction.test(100,100,100d))
                .isEqualTo(andResult);

        assertThat(orFunction.test(100,100,100d))
                .isEqualTo(orResult);

        assertThat(xorFunction.test(100,100,100d))
                .isEqualTo(xorResult);
    }

    @Test
    public void testIsEqual() throws Throwable  {
        //when
        LObjIntDblPredicate<Integer> equals = LObjIntDblPredicate.isEqual(1,1,1d);

        //then
        assertThat(equals.test(1,1,1d))
                .isTrue();

        assertThat(equals.test(0,0,0d))
                .isFalse();
    }



    // <editor-fold desc="compose (functional)">

    @Test
    public void testCompose() throws Throwable {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LObjIntDblPredicate<Integer> sutO = (a1,a2,a3) -> {
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
        LIntUnaryOperator before2 = p1 -> {
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
        LObjIntDblPredicate<Integer> function = sutO.compose(before1,before2,before3);
        function.test(80,81,82d);

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(beforeCalls.get()).isEqualTo(3);
    }


    @Test
    public void testObjIntDblPredCompose() throws Throwable {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LObjIntDblPredicate<Integer> sutO = (a1,a2,a3) -> {
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
        LToIntFunction<Integer> before2 = p1 -> {
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
        LTriPredicate<Integer,Integer,Integer> function = sutO.objIntDblPredCompose(before1,before2,before3);
        function.test(80,81,82);

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(beforeCalls.get()).isEqualTo(3);
    }

    // </editor-fold>



    // <editor-fold desc="then (functional)">

    @Test
    public void testBoolToObjIntDblFunc0() throws Throwable  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);

        //given (+ some assertions)
        LObjIntDblPredicate<Integer> sutO = (a1,a2,a3) -> {
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
        LObjIntDblFunction<Integer,Integer> function = sutO.boolToObjIntDblFunc(thenFunction);
        Integer finalValue = function.apply(80,81,82d);

        //then - finals
        assertThat(finalValue).isEqualTo(100);
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    @Test
    public void testBoolToTieDblFunc1() throws Throwable  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);

        //given (+ some assertions)
        LObjIntDblPredicate<Integer> sutO = (a1,a2,a3) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(80);
                assertThat(a2).isEqualTo(81);
                assertThat(a3).isEqualTo(82d);
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
        LTieDblFunction<Integer> function = sutO.boolToTieDblFunc(thenFunction);
        int finalValue = function.applyAsInt(80,81,82d);

        //then - finals
        assertThat(finalValue).isEqualTo(100);
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    @Test
    public void testBoolToObjIntDblPred2() throws Throwable  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);

        //given (+ some assertions)
        LObjIntDblPredicate<Integer> sutO = (a1,a2,a3) -> {
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
        LObjIntDblPredicate<Integer> function = sutO.boolToObjIntDblPred(thenFunction);
        boolean finalValue = function.test(80,81,82d);

        //then - finals
        assertThat(finalValue).isEqualTo(true);
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    // </editor-fold>


    @Test(expectedExceptions = RuntimeException.class)
    public void testShove() {

        // given
        LObjIntDblPredicate<Integer> sutThrowing = LObjIntDblPredicate.objIntDblPred((a1,a2,a3) -> {
            throw new UnsupportedOperationException();
        });

        // when
        sutThrowing.shovingTest(100,100,100d);
    }


    @Test
    public void testToString() throws Throwable {

        assertThat(sut.toString())
                .isInstanceOf(String.class)
                .startsWith(this.getClass().getName()+"$");

        assertThat(String.format("%s", sut))
                .isInstanceOf(String.class)
                .contains("LObjIntDblPredicate: boolean test(T a1,int a2,double a3)");
    }


    @Test
    public void isThrowing() {
        assertThat(sut.isThrowing())
            .isFalse();
    }

    //<editor-fold desc="Variants">

    private boolean variantLObjDblIntPred(Integer a1,double a3,int a2) {
        return true;
    }

    @Test
    public void compilerSubstituteVariantLObjDblIntPred() {
        LObjIntDblPredicate lambda = LObjIntDblPredicate./*<T>*/objDblIntPred(this::variantLObjDblIntPred);

        assertThat(lambda).isInstanceOf(LObjIntDblPredicate.LObjDblIntPred.class);
    }


    private boolean variantLIntObjDblPred(int a2,Integer a1,double a3) {
        return true;
    }

    @Test
    public void compilerSubstituteVariantLIntObjDblPred() {
        LObjIntDblPredicate lambda = LObjIntDblPredicate./*<T>*/intObjDblPred(this::variantLIntObjDblPred);

        assertThat(lambda).isInstanceOf(LObjIntDblPredicate.LIntObjDblPred.class);
    }


    private boolean variantLIntDblObjPred(int a2,double a3,Integer a1) {
        return true;
    }

    @Test
    public void compilerSubstituteVariantLIntDblObjPred() {
        LObjIntDblPredicate lambda = LObjIntDblPredicate./*<T>*/intDblObjPred(this::variantLIntDblObjPred);

        assertThat(lambda).isInstanceOf(LObjIntDblPredicate.LIntDblObjPred.class);
    }


    private boolean variantLDblObjIntPred(double a3,Integer a1,int a2) {
        return true;
    }

    @Test
    public void compilerSubstituteVariantLDblObjIntPred() {
        LObjIntDblPredicate lambda = LObjIntDblPredicate./*<T>*/dblObjIntPred(this::variantLDblObjIntPred);

        assertThat(lambda).isInstanceOf(LObjIntDblPredicate.LDblObjIntPred.class);
    }


    private boolean variantLDblIntObjPred(double a3,int a2,Integer a1) {
        return true;
    }

    @Test
    public void compilerSubstituteVariantLDblIntObjPred() {
        LObjIntDblPredicate lambda = LObjIntDblPredicate./*<T>*/dblIntObjPred(this::variantLDblIntObjPred);

        assertThat(lambda).isInstanceOf(LObjIntDblPredicate.LDblIntObjPred.class);
    }

    //</editor-fold>


    @Test void safeCompiles() {
        LObjIntDblPredicate r1 = LObjIntDblPredicate.safe(sut); //NOSONAR
    }

    @Test void safePropagates() {
        Object result = LObjIntDblPredicate.safe(sut);
        assertThat(result).isSameAs(sut);
    }

    @Test void safeProtectsAgainstNpe() {
        Object result = LObjIntDblPredicate.safe(null);
        assertThat(result).isSameAs(LObjIntDblPredicate.objIntDblPred(LObjIntDblPredicate.safe()));
    }

    @Test  void safeSupplierPropagates() {
        LSupplier<LObjIntDblPredicate<Integer>> supplier = ()->sut;
        Object result = LObjIntDblPredicate.safeSupplier(supplier);
        assertThat(result).isSameAs(supplier);
    }

    @Test  void safeSupplierProtectsAgainstNpe() {
        Object result = LObjIntDblPredicate.safeSupplier(null);
        assertThat(result).isSameAs(LObjIntDblPredicate.safeSupplier());
    }

    @Test  void safeSupplierCompiles() {
        LSupplier<LObjIntDblPredicate<Integer>> r1 = LObjIntDblPredicate.safeSupplier(()->sut);  //NOSONAR
    }

}
