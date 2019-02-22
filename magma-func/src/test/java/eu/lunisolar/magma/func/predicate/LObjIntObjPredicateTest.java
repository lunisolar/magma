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
public class LObjIntObjPredicateTest<T1,T2> {
    private static final String ORIGINAL_MESSAGE = "Original message";
    private static final String EXCEPTION_WAS_WRAPPED = "Exception was wrapped.";
    private static final String NO_EXCEPTION_WERE_THROWN = "No exception were thrown.";

    private boolean testValue = true;



    private LObjIntObjPredicate<Integer,Integer> sut = new LObjIntObjPredicate<Integer,Integer>(){
        public  boolean testX(Integer a1,int a2,Integer a3)  {
            return testValue;
        }
    };




    private LObjIntObjPredicate<Integer,Integer> sutAlwaysThrowing = LObjIntObjPredicate.objIntObjPred((a1,a2,a3) -> {
            throw new ParseException(ORIGINAL_MESSAGE, 0);
    });

    private LObjIntObjPredicate<Integer,Integer> sutAlwaysThrowingUnchecked = LObjIntObjPredicate.objIntObjPred((a1,a2,a3) -> {
            throw new IndexOutOfBoundsException(ORIGINAL_MESSAGE);
    });


    @Test
    public void testTheResult() throws Throwable {
        assertThat(sut.test(100,100,100))
            .isEqualTo(testValue);
    }

    @Test
    public void testTupleCall() throws Throwable {

        LObjIntObjTriple<Integer,Integer> domainObject = Tuple4U.objIntObjTriple(100,100,100);

        Object result = sut.tupleTest(domainObject);

        assertThat(result)
            .isEqualTo(testValue);
    }

    @Test
    public void testNonNullTest() throws Throwable {
        assertThat(sut.nonNullTest(100,100,100))
            .isEqualTo(testValue);
    }

    @Test
    public void testNestingTestUnchecked() throws Throwable {

        // then
        try {
            sutAlwaysThrowingUnchecked.nestingTest(100,100,100);
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
            sutAlwaysThrowingUnchecked.shovingTest(100,100,100);
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
        assertThat(sut.doApplyAsBoolean(100,100,100))
            .isEqualTo(testValue);

    }


    @Test
    public void testFunctionalInterfaceDescription() throws Throwable {
        assertThat(sut.functionalInterfaceDescription())
            .isEqualTo("LObjIntObjPredicate: boolean test(T1 a1,int a2,T2 a3)");
    }

    @Test
    public void testObjIntObjPredMethod() throws Throwable {
        assertThat(LObjIntObjPredicate.objIntObjPred((a1,a2,a3) -> testValue ))
            .isInstanceOf(LObjIntObjPredicate.class);
    }




    @Test
    public void testnegate() throws Throwable {
        assertThat(sut.negate().test(100,100,100))
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
        LObjIntObjPredicate<Integer,Integer> fun1 = LObjIntObjPredicate.objIntObjPred((a1,a2,a3) -> f1Result);
        LObjIntObjPredicate<Integer,Integer> fun2 = LObjIntObjPredicate.objIntObjPred((a1,a2,a3) -> f2Result);

        //when
        LObjIntObjPredicate<Integer,Integer> andFunction = fun1.and(fun2);
        LObjIntObjPredicate<Integer,Integer> orFunction = fun1.or(fun2);
        LObjIntObjPredicate<Integer,Integer> xorFunction = fun1.xor(fun2);

        //then
        assertThat(andFunction.test(100,100,100))
                .isEqualTo(andResult);

        assertThat(orFunction.test(100,100,100))
                .isEqualTo(orResult);

        assertThat(xorFunction.test(100,100,100))
                .isEqualTo(xorResult);
    }

    @Test
    public void testIsEqual() throws Throwable  {
        //when
        LObjIntObjPredicate<Integer,Integer> equals = LObjIntObjPredicate.isEqual(1,1,1);

        //then
        assertThat(equals.test(1,1,1))
                .isTrue();

        assertThat(equals.test(0,0,0))
                .isFalse();
    }



    // <editor-fold desc="compose (functional)">

    @Test
    public void testCompose() throws Throwable {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LObjIntObjPredicate<Integer,Integer> sutO = (a1,a2,a3) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(90);
                assertThat(a2).isEqualTo(91);
                assertThat(a3).isEqualTo(92);
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
        LFunction<Integer,Integer> before3 = p2 -> {
            assertThat(p2).isEqualTo(82);
            beforeCalls.incrementAndGet();
            return 92;
        };

        //when
        LObjIntObjPredicate<Integer,Integer> function = sutO.compose(before1,before2,before3);
        function.test(80,81,82);

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(beforeCalls.get()).isEqualTo(3);
    }


    @Test
    public void testObjIntObjPredCompose() throws Throwable {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LObjIntObjPredicate<Integer,Integer> sutO = (a1,a2,a3) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(90);
                assertThat(a2).isEqualTo(91);
                assertThat(a3).isEqualTo(92);
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
        LFunction<Integer,Integer> before3 = p2 -> {
            assertThat(p2).isEqualTo(82);
            beforeCalls.incrementAndGet();
            return 92;
        };

        //when
        LTriPredicate<Integer,Integer,Integer> function = sutO.objIntObjPredCompose(before1,before2,before3);
        function.test(80,81,82);

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(beforeCalls.get()).isEqualTo(3);
    }

    // </editor-fold>



    // <editor-fold desc="then (functional)">

    @Test
    public void testBoolToObjIntObjFunc0() throws Throwable  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);

        //given (+ some assertions)
        LObjIntObjPredicate<Integer,Integer> sutO = (a1,a2,a3) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(80);
                assertThat(a2).isEqualTo(81);
                assertThat(a3).isEqualTo(82);
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
        LObjIntObjFunction<Integer,Integer,Integer> function = sutO.boolToObjIntObjFunc(thenFunction);
        Integer finalValue = function.apply(80,81,82);

        //then - finals
        assertThat(finalValue).isEqualTo(100);
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    @Test
    public void testBoolToTieFunc1() throws Throwable  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);

        //given (+ some assertions)
        LObjIntObjPredicate<Integer,Integer> sutO = (a1,a2,a3) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(80);
                assertThat(a2).isEqualTo(81);
                assertThat(a3).isEqualTo(82);
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
        LTieFunction<Integer,Integer> function = sutO.boolToTieFunc(thenFunction);
        int finalValue = function.applyAsInt(80,81,82);

        //then - finals
        assertThat(finalValue).isEqualTo(100);
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    @Test
    public void testBoolToObjIntObjPred2() throws Throwable  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);

        //given (+ some assertions)
        LObjIntObjPredicate<Integer,Integer> sutO = (a1,a2,a3) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(80);
                assertThat(a2).isEqualTo(81);
                assertThat(a3).isEqualTo(82);
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
        LObjIntObjPredicate<Integer,Integer> function = sutO.boolToObjIntObjPred(thenFunction);
        boolean finalValue = function.test(80,81,82);

        //then - finals
        assertThat(finalValue).isEqualTo(true);
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    // </editor-fold>


    @Test(expectedExceptions = RuntimeException.class)
    public void testShove() {

        // given
        LObjIntObjPredicate<Integer,Integer> sutThrowing = LObjIntObjPredicate.objIntObjPred((a1,a2,a3) -> {
            throw new UnsupportedOperationException();
        });

        // when
        sutThrowing.shovingTest(100,100,100);
    }


    @Test
    public void testToString() throws Throwable {

        assertThat(sut.toString())
                .isInstanceOf(String.class)
                .startsWith(this.getClass().getName()+"$");

        assertThat(String.format("%s", sut))
                .isInstanceOf(String.class)
                .contains("LObjIntObjPredicate: boolean test(T1 a1,int a2,T2 a3)");
    }


    @Test
    public void isThrowing() {
        assertThat(sut.isThrowing())
            .isFalse();
    }

    //<editor-fold desc="Variants">

    private boolean variantLObj0Obj2Int1Pred(Integer a1,Integer a3,int a2) {
        return true;
    }

    @Test
    public void compilerSubstituteVariantLObj0Obj2Int1Pred() {
        LObjIntObjPredicate lambda = LObjIntObjPredicate./*<T1,T2>*/obj0Obj2Int1Pred(this::variantLObj0Obj2Int1Pred);

        assertThat(lambda).isInstanceOf(LObjIntObjPredicate.LObj0Obj2Int1Pred.class);
    }


    private boolean variantLInt1BiObj2Pred(int a2,Integer a1,Integer a3) {
        return true;
    }

    @Test
    public void compilerSubstituteVariantLInt1BiObj2Pred() {
        LObjIntObjPredicate lambda = LObjIntObjPredicate./*<T1,T2>*/int1BiObj2Pred(this::variantLInt1BiObj2Pred);

        assertThat(lambda).isInstanceOf(LObjIntObjPredicate.LInt1BiObj2Pred.class);
    }


    private boolean variantLInt1Obj2Obj0Pred(int a2,Integer a3,Integer a1) {
        return true;
    }

    @Test
    public void compilerSubstituteVariantLInt1Obj2Obj0Pred() {
        LObjIntObjPredicate lambda = LObjIntObjPredicate./*<T1,T2>*/int1Obj2Obj0Pred(this::variantLInt1Obj2Obj0Pred);

        assertThat(lambda).isInstanceOf(LObjIntObjPredicate.LInt1Obj2Obj0Pred.class);
    }


    private boolean variantLObj2Obj0Int1Pred(Integer a3,Integer a1,int a2) {
        return true;
    }

    @Test
    public void compilerSubstituteVariantLObj2Obj0Int1Pred() {
        LObjIntObjPredicate lambda = LObjIntObjPredicate./*<T1,T2>*/obj2Obj0Int1Pred(this::variantLObj2Obj0Int1Pred);

        assertThat(lambda).isInstanceOf(LObjIntObjPredicate.LObj2Obj0Int1Pred.class);
    }


    private boolean variantLObj2Int1Obj0Pred(Integer a3,int a2,Integer a1) {
        return true;
    }

    @Test
    public void compilerSubstituteVariantLObj2Int1Obj0Pred() {
        LObjIntObjPredicate lambda = LObjIntObjPredicate./*<T1,T2>*/obj2Int1Obj0Pred(this::variantLObj2Int1Obj0Pred);

        assertThat(lambda).isInstanceOf(LObjIntObjPredicate.LObj2Int1Obj0Pred.class);
    }

    //</editor-fold>


    @Test void safeCompiles() {
        LObjIntObjPredicate r1 = LObjIntObjPredicate.safe(sut); //NOSONAR
    }

    @Test void safePropagates() {
        Object result = LObjIntObjPredicate.safe(sut);
        assertThat(result).isSameAs(sut);
    }

    @Test void safeProtectsAgainstNpe() {
        Object result = LObjIntObjPredicate.safe(null);
        assertThat(result).isSameAs(LObjIntObjPredicate.objIntObjPred(LObjIntObjPredicate.safe()));
    }

    @Test  void safeSupplierPropagates() {
        LSupplier<LObjIntObjPredicate<Integer,Integer>> supplier = ()->sut;
        Object result = LObjIntObjPredicate.safeSupplier(supplier);
        assertThat(result).isSameAs(supplier);
    }

    @Test  void safeSupplierProtectsAgainstNpe() {
        Object result = LObjIntObjPredicate.safeSupplier(null);
        assertThat(result).isSameAs(LObjIntObjPredicate.safeSupplier());
    }

    @Test  void safeSupplierCompiles() {
        LSupplier<LObjIntObjPredicate<Integer,Integer>> r1 = LObjIntObjPredicate.safeSupplier(()->sut);  //NOSONAR
    }

}
