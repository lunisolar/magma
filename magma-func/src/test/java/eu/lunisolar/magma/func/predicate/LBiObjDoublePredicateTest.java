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
import eu.lunisolar.magma.struct.tuple.*; // NOSONAR
import static org.assertj.core.api.Assertions.*; //NOSONAR
import java.util.function.*; // NOSONAR

/** The test obviously concentrate on the interface methods the function it self is very simple.  */
public class LBiObjDoublePredicateTest<T1,T2,X extends ParseException> {
    private static final String ORIGINAL_MESSAGE = "Original message";
    private static final String EXCEPTION_WAS_WRAPPED = "Exception was wrapped.";
    private static final String NO_EXCEPTION_WERE_THROWN = "No exception were thrown.";

    private boolean testValue = true;



    private LBiObjDoublePredicate<Integer,Integer> sut = new LBiObjDoublePredicate<Integer,Integer>(){
        public  boolean doTest(Integer a1,Integer a2,double a3)  {
            return testValue;
        }
    };

    private LBiObjDoublePredicateX<Integer,Integer,X> opposite = new LBiObjDoublePredicateX<Integer,Integer,X>(){
        public  boolean doTest(Integer a1,Integer a2,double a3)  throws X {
            return testValue;
        }
    };




    private LBiObjDoublePredicateX<Integer,Integer,RuntimeException> sutAlwaysThrowingUnchecked = LBiObjDoublePredicate.l((a1,a2,a3) -> {
            throw new IndexOutOfBoundsException(ORIGINAL_MESSAGE);
    });


    @Test
    public void testTheResult() throws X {
        assertThat(sut.doTest(100,100,100d))
            .isEqualTo(testValue);
    }

    @Test
    public void testTupleCall() throws X {

        LBiObjDoubleTriple<Integer,Integer> domainObject = Tuple4U.biObjDoubleTriple(100,100,100d);

        Object result = sut.tupleTest(domainObject);

        assertThat(result)
            .isEqualTo(testValue);
    }

    @Test
    public void testNonNullDoTest() throws X {
        assertThat(sut.nonNullDoTest(100,100,100d))
            .isEqualTo(testValue);
    }

    @Test
    public void testNestingDoTestUnchecked() throws X {

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
    public void testShovingDoTestUnchecked() throws X {

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
    public void testApplyAsBooleanShouldNotModifyValue() throws X {
        assertThat(sut.doApplyAsBoolean(100,100,100d))
            .isEqualTo(testValue);

    }


    @Test
    public void testFunctionalInterfaceDescription() throws X {
        assertThat(sut.functionalInterfaceDescription())
            .isEqualTo("LBiObjDoublePredicate: boolean doTest(T1 a1,T2 a2,double a3)");
    }

    @Test
    public void testLMethod() throws X {
        assertThat(LBiObjDoublePredicate.l((a1,a2,a3) -> testValue ))
            .isInstanceOf(LBiObjDoublePredicate.class);
    }

    @Test
    public void testWrapMethod() throws X {
        assertThat(LBiObjDoublePredicate.wrap(opposite))
            .isInstanceOf(LBiObjDoublePredicate.class);
    }

    @Test
    public void testWrapMethodDoNotWrapsRuntimeException() throws X {
        // given
        LBiObjDoublePredicateX<Integer,Integer,X> sutThrowing = LBiObjDoublePredicateX.lX((a1,a2,a3) -> {
            throw new UnsupportedOperationException(ORIGINAL_MESSAGE);
        });

        // when
        LBiObjDoublePredicate<Integer,Integer> wrapped = LBiObjDoublePredicate.wrap(sutThrowing);

        // then
        try {
            wrapped.doTest(100,100,100d);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(UnsupportedOperationException.class)
                    .hasNoCause()
                    .hasMessage(ORIGINAL_MESSAGE);
        }
    }

    @Test
    public void testWrapMethodWrapsCheckedException() throws X {
        // given
        LBiObjDoublePredicateX<Integer,Integer,ParseException> sutThrowing = LBiObjDoublePredicateX.lX((a1,a2,a3) -> {
            throw new ParseException(ORIGINAL_MESSAGE, 0);
        });

        // when
        LBiObjDoublePredicate<Integer,Integer> wrapped = LBiObjDoublePredicate.wrap(sutThrowing);

        // then
        try {
            wrapped.doTest(100,100,100d);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(NestedException.class)
                    .hasCauseExactlyInstanceOf(ParseException.class)
                    .hasMessage(ORIGINAL_MESSAGE);
        }
    }


    @Test
    public void testHandlingDoTestMethodWrapsTheException() throws X {

        // given
        LBiObjDoublePredicate<Integer,Integer> sutThrowing = LBiObjDoublePredicate.l((a1,a2,a3) -> {
            throw new UnsupportedOperationException();
        });

        // when
        LBiObjDoublePredicate<Integer,Integer> wrapped = sutThrowing.handleBiObjDoublePred(handler -> handler
            .wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED));

        // then
        try {
            wrapped.doTest(100,100,100d);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IllegalArgumentException.class)
                    .hasCauseExactlyInstanceOf(UnsupportedOperationException.class)
                    .hasMessage(EXCEPTION_WAS_WRAPPED);
        }
    }

    @Test
    public void testHandleBiObjDoublePredMethodDoNotWrapsOtherExceptionIf() throws X {

        // given
        LBiObjDoublePredicate<Integer,Integer> sutThrowing = LBiObjDoublePredicate.l((a1,a2,a3) -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LBiObjDoublePredicate<Integer,Integer> wrapped = sutThrowing.handleBiObjDoublePred(handler -> handler
                .wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED)
                .throwIf(IndexOutOfBoundsException.class));

        // then
        try {
            wrapped.doTest(100,100,100d);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                    .hasNoCause();
        }
    }

@Test
    public void testHandleBiObjDoublePredMethodDoNotWrapsOtherExceptionWhen() throws X {

        // given
        LBiObjDoublePredicate<Integer,Integer> sutThrowing = LBiObjDoublePredicate.l((a1,a2,a3) -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LBiObjDoublePredicate<Integer,Integer> wrapped = sutThrowing.handleBiObjDoublePred(handler -> handler
                .wrapWhen(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED)
                .throwIf(IndexOutOfBoundsException.class));

        // then
        try {
            wrapped.doTest(100,100,100d);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                    .hasNoCause();
        }
    }


    @Test
    public void testHandleBiObjDoublePredMishandlingExceptionIsAllowed() throws X {

        // given
        LBiObjDoublePredicate<Integer,Integer> sutThrowing = LBiObjDoublePredicate.l((a1,a2,a3) -> {
            throw new UnsupportedOperationException(ORIGINAL_MESSAGE);
        });

        // when
        LBiObjDoublePredicate<Integer,Integer> wrapped = sutThrowing.handleBiObjDoublePred(h -> Function4U.doNothing());

        // then
        try {
            wrapped.doTest(100,100,100d);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
             .isExactlyInstanceOf(UnsupportedOperationException.class)
             .hasNoCause()
             .hasMessage(ORIGINAL_MESSAGE);
        }
    }

    @Test
    public void testnegate() throws X {
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
    public void testAndOrXor(final boolean f1Result, final boolean f2Result, final boolean andResult, final boolean orResult, final boolean xorResult) throws X {

        //given
        LBiObjDoublePredicate<Integer,Integer> fun1 = LBiObjDoublePredicate.l((a1,a2,a3) -> f1Result);
        LBiObjDoublePredicate<Integer,Integer> fun2 = LBiObjDoublePredicate.l((a1,a2,a3) -> f2Result);

        //when
        LBiObjDoublePredicate<Integer,Integer> andFunction = fun1.and(fun2);
        LBiObjDoublePredicate<Integer,Integer> orFunction = fun1.or(fun2);
        LBiObjDoublePredicate<Integer,Integer> xorFunction = fun1.xor(fun2);

        //then
        assertThat(andFunction.doTest(100,100,100d))
                .isEqualTo(andResult);

        assertThat(orFunction.doTest(100,100,100d))
                .isEqualTo(orResult);

        assertThat(xorFunction.doTest(100,100,100d))
                .isEqualTo(xorResult);
    }

    @Test
    public void testIsEqual() throws X  {
        //when
        LBiObjDoublePredicate<Integer,Integer> equals = LBiObjDoublePredicate.isEqual(1,1,1d);

        //then
        assertThat(equals.doTest(1,1,1d))
                .isTrue();

        assertThat(equals.doTest(0,0,0d))
                .isFalse();
    }



    // <editor-fold desc="compose (functional)">

    @Test
    public void testBiObjDoublePredComposeDouble() throws X {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LBiObjDoublePredicate<Integer,Integer> sutO = (a1,a2,a3) -> {
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
        LDoubleUnaryOperator before3 = p2 -> {
            assertThat(p2).isEqualTo(82d);
            beforeCalls.incrementAndGet();
            return 92d;
        };

        //when
        LBiObjDoublePredicate<Integer,Integer> function = sutO.biObjDoublePredComposeDouble(before1,before2,before3);
        function.doTest(80,81,82d);

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(beforeCalls.get()).isEqualTo(3);
    }


    @Test
    public void testBiObjDoublePredCompose() throws X {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LBiObjDoublePredicate<Integer,Integer> sutO = (a1,a2,a3) -> {
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
        LToDoubleFunction<Integer> before3 = p2 -> {
            assertThat(p2).isEqualTo(82);
            beforeCalls.incrementAndGet();
            return 92d;
        };

        //when
        LTriPredicate<Integer,Integer,Integer> function = sutO.biObjDoublePredCompose(before1,before2,before3);
        function.doTest(80,81,82);

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(beforeCalls.get()).isEqualTo(3);
    }

    // </editor-fold>



    // <editor-fold desc="then (functional)">

    @Test
    public void testBoolToBiObjDoubleFunc0() throws X  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);

        //given (+ some assertions)
        LBiObjDoublePredicate<Integer,Integer> sutO = (a1,a2,a3) -> {
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
        LBiObjDoubleFunction<Integer,Integer,Integer> function = sutO.boolToBiObjDoubleFunc(thenFunction);
        Integer finalValue = function.doApply(80,81,82d);

        //then - finals
        assertThat(finalValue).isEqualTo(100);
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    // </editor-fold>

    @Test
    public void testNesting() {
        assertThat(sut.nestingBiObjDoublePred())
            .isSameAs(sut)
            .isInstanceOf(LBiObjDoublePredicate.class);
    }

    @Test
    public void testShoving() {
        assertThat(sut.shovingBiObjDoublePred())
            .isSameAs(sut)
            .isInstanceOf(LBiObjDoublePredicate.class);
    }

    @Test
    public void testNestingX() {
        assertThat(sut.nestingBiObjDoublePredX())
            .isSameAs(sut)
            .isInstanceOf(LBiObjDoublePredicateX.class);
    }

    @Test
    public void testShovingX() {
        assertThat(sut.shovingBiObjDoublePredX())
            .isSameAs(sut)
            .isInstanceOf(LBiObjDoublePredicateX.class);
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void testShove() {

        // given
        LBiObjDoublePredicate<Integer,Integer> sutThrowing = LBiObjDoublePredicate.l((a1,a2,a3) -> {
            throw new UnsupportedOperationException();
        });

        // when
        sutThrowing.shovingBiObjDoublePred().doTest(100,100,100d);
    }

    @Test
    public void testHandleBiObjDoublePred() throws X {

        // given
        LBiObjDoublePredicate<Integer,Integer> sutThrowing = LBiObjDoublePredicate.l((a1,a2,a3) -> {
            throw new UnsupportedOperationException();
        });

        // when
        LBiObjDoublePredicate<Integer,Integer> wrapped = sutThrowing.handleBiObjDoublePred(h -> {
            h.wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED);
        });

        // then
        try {
            wrapped.doTest(100,100,100d);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IllegalArgumentException.class)
                    .hasCauseExactlyInstanceOf(UnsupportedOperationException.class)
                    .hasMessage(EXCEPTION_WAS_WRAPPED);
        }
    }

    @Test
    public void testToString() throws X {

        assertThat(sut.toString())
                .isInstanceOf(String.class)
                .startsWith(this.getClass().getName()+"$");

        assertThat(String.format("%s", sut))
                .isInstanceOf(String.class)
                .contains("LBiObjDoublePredicate: boolean doTest(T1 a1,T2 a2,double a3)");
    }


    @Test
    public void isThrowing() {
        assertThat(sut.isThrowing())
            .isFalse();
    }

    //<editor-fold desc="Variants">

    private boolean variantV1(Integer a1,double a3,Integer a2) {
        return true;
    }

    @Test
    public void compilerSubstituteVariantV1() {
        LBiObjDoublePredicate lambda = LBiObjDoublePredicate./*<T1,T2>*/l1(this::variantV1);

        assertThat(lambda).isInstanceOf(LBiObjDoublePredicate.V1.class);
    }


    private boolean variantV2(Integer a2,Integer a1,double a3) {
        return true;
    }

    @Test
    public void compilerSubstituteVariantV2() {
        LBiObjDoublePredicate lambda = LBiObjDoublePredicate./*<T1,T2>*/l2(this::variantV2);

        assertThat(lambda).isInstanceOf(LBiObjDoublePredicate.V2.class);
    }


    private boolean variantV3(Integer a2,double a3,Integer a1) {
        return true;
    }

    @Test
    public void compilerSubstituteVariantV3() {
        LBiObjDoublePredicate lambda = LBiObjDoublePredicate./*<T1,T2>*/l3(this::variantV3);

        assertThat(lambda).isInstanceOf(LBiObjDoublePredicate.V3.class);
    }


    private boolean variantV4(double a3,Integer a1,Integer a2) {
        return true;
    }

    @Test
    public void compilerSubstituteVariantV4() {
        LBiObjDoublePredicate lambda = LBiObjDoublePredicate./*<T1,T2>*/l4(this::variantV4);

        assertThat(lambda).isInstanceOf(LBiObjDoublePredicate.V4.class);
    }


    private boolean variantV5(double a3,Integer a2,Integer a1) {
        return true;
    }

    @Test
    public void compilerSubstituteVariantV5() {
        LBiObjDoublePredicate lambda = LBiObjDoublePredicate./*<T1,T2>*/l5(this::variantV5);

        assertThat(lambda).isInstanceOf(LBiObjDoublePredicate.V5.class);
    }

    //</editor-fold>


    @Test void safeCompiles() {
        LBiObjDoublePredicate r1 = LBiObjDoublePredicate.safe(sut); //NOSONAR
        LBiObjDoublePredicateX r2 = LBiObjDoublePredicate.safe(sut); //NOSONAR
    }

    @Test void safePropagates() {
        Object result = LBiObjDoublePredicate.safe(sut);
        assertThat(result).isSameAs(sut);
    }

    @Test void safeProtectsAgainstNpe() {
        Object result = LBiObjDoublePredicate.safe(null);
        assertThat(result).isSameAs(LBiObjDoublePredicate.l(LBiObjDoublePredicate.safe()));
    }

    @Test  void safeSupplierPropagates() {
        LSupplier<LBiObjDoublePredicate<Integer,Integer>> supplier = ()->sut;
        Object result = LBiObjDoublePredicate.safeSupplier(supplier);
        assertThat(result).isSameAs(supplier);
    }

    @Test  void safeSupplierProtectsAgainstNpe() {
        Object result = LBiObjDoublePredicate.safeSupplier(null);
        assertThat(result).isSameAs(LBiObjDoublePredicate.safeSupplier());
    }

    @Test  void safeSupplierCompiles() {
        LSupplier<LBiObjDoublePredicate<Integer,Integer>> r1 = LBiObjDoublePredicate.safeSupplier(()->sut);  //NOSONAR
    }

}
