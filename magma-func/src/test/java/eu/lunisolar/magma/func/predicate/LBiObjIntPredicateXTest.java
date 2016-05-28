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
public class LBiObjIntPredicateXTest<T1,T2,X extends ParseException> {
    private static final String ORIGINAL_MESSAGE = "Original message";
    private static final String EXCEPTION_WAS_WRAPPED = "Exception was wrapped.";
    private static final String NO_EXCEPTION_WERE_THROWN = "No exception were thrown.";

    private boolean testValue = true;



    private LBiObjIntPredicateX<Integer,Integer,X> sut = new LBiObjIntPredicateX<Integer,Integer,X>(){
        public  boolean doTest(Integer a1,Integer a2,int a3)  throws X {
            return testValue;
        }
    };

    private LBiObjIntPredicate<Integer,Integer> opposite = new LBiObjIntPredicate<Integer,Integer>(){
        public  boolean doTest(Integer a1,Integer a2,int a3)  {
            return testValue;
        }
    };



    private LBiObjIntPredicateX<Integer,Integer,ParseException> sutAlwaysThrowing = LBiObjIntPredicateX.lX((a1,a2,a3) -> {
            throw new ParseException(ORIGINAL_MESSAGE, 0);
    });

    private LBiObjIntPredicateX<Integer,Integer,RuntimeException> sutAlwaysThrowingUnchecked = LBiObjIntPredicateX.lX((a1,a2,a3) -> {
            throw new IndexOutOfBoundsException(ORIGINAL_MESSAGE);
    });


    @Test
    public void testTheResult() throws X {
        assertThat(sut.doTest(100,100,100))
            .isEqualTo(testValue);
    }

    @Test
    public void testTupleCall() throws X {

        LBiObjIntTriple<Integer,Integer> domainObject = Tuple4U.biObjIntTriple(100,100,100);

        Object result = sut.tupleTest(domainObject);

        assertThat(result)
            .isEqualTo(testValue);
    }

    @Test
    public void testNonNullDoTest() throws X {
        assertThat(sut.nonNullDoTest(100,100,100))
            .isEqualTo(testValue);
    }

    @Test
    public void testNestingDoTestChecked() throws X {

        // then
        try {
            sutAlwaysThrowing.nestingDoTest(100,100,100);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(NestedException.class)
                    .hasCauseExactlyInstanceOf(ParseException.class)
                    .hasMessage(ORIGINAL_MESSAGE);
        }
    }

    @Test
    public void testNestingDoTestUnchecked() throws X {

        // then
        try {
            sutAlwaysThrowingUnchecked.nestingDoTest(100,100,100);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                    .hasNoCause()
                    .hasMessage(ORIGINAL_MESSAGE);
        }
    }

    @Test
    public void testShovingDoTestChecked() throws X {

        // then
        try {
            sutAlwaysThrowing.shovingDoTest(100,100,100);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(ParseException.class)
                    .hasNoCause()
                    .hasMessage(ORIGINAL_MESSAGE);
        }
    }

    @Test
    public void testShovingDoTestUnchecked() throws X {

        // then
        try {
            sutAlwaysThrowingUnchecked.shovingDoTest(100,100,100);
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
        assertThat(sut.doApplyAsBoolean(100,100,100))
            .isEqualTo(testValue);

    }


    @Test
    public void testFunctionalInterfaceDescription() throws X {
        assertThat(sut.functionalInterfaceDescription())
            .isEqualTo("LBiObjIntPredicateX: boolean doTest(T1 a1,T2 a2,int a3) throws X");
    }

    @Test
    public void testLXMethod() throws X {
        assertThat(LBiObjIntPredicateX.lX((a1,a2,a3) -> testValue ))
            .isInstanceOf(LBiObjIntPredicateX.class);
    }

    @Test
    public void testWrapXMethod() throws X {
        assertThat(LBiObjIntPredicateX.wrapX(opposite))
            .isInstanceOf(LBiObjIntPredicateX.class);
    }


    @Test
    public void testHandlingDoTestMethodWrapsTheException() throws X {

        // given
        LBiObjIntPredicateX<Integer,Integer,X> sutThrowing = LBiObjIntPredicateX.lX((a1,a2,a3) -> {
            throw new UnsupportedOperationException();
        });

        // when
        LBiObjIntPredicateX<Integer,Integer,RuntimeException> wrapped = sutThrowing.handleBiObjIntPredX(handler -> handler
            .wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED));

        // then
        try {
            wrapped.doTest(100,100,100);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IllegalArgumentException.class)
                    .hasCauseExactlyInstanceOf(UnsupportedOperationException.class)
                    .hasMessage(EXCEPTION_WAS_WRAPPED);
        }
    }

    @Test
    public void testHandleBiObjIntPredXMethodDoNotWrapsOtherExceptionIf() throws X {

        // given
        LBiObjIntPredicateX<Integer,Integer,X> sutThrowing = LBiObjIntPredicateX.lX((a1,a2,a3) -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LBiObjIntPredicateX<Integer,Integer,X> wrapped = sutThrowing.handleBiObjIntPredX(handler -> handler
                .wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED)
                .throwIf(IndexOutOfBoundsException.class));

        // then
        try {
            wrapped.doTest(100,100,100);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                    .hasNoCause();
        }
    }

@Test
    public void testHandleBiObjIntPredXMethodDoNotWrapsOtherExceptionWhen() throws X {

        // given
        LBiObjIntPredicateX<Integer,Integer,X> sutThrowing = LBiObjIntPredicateX.lX((a1,a2,a3) -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LBiObjIntPredicateX<Integer,Integer,X> wrapped = sutThrowing.handleBiObjIntPredX(handler -> handler
                .wrapWhen(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED)
                .throwIf(IndexOutOfBoundsException.class));

        // then
        try {
            wrapped.doTest(100,100,100);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                    .hasNoCause();
        }
    }


    @Test
    public void testHandleBiObjIntPredXMishandlingExceptionIsAllowed() throws X {

        // given
        LBiObjIntPredicateX<Integer,Integer,X> sutThrowing = LBiObjIntPredicateX.lX((a1,a2,a3) -> {
            throw (X) new ParseException(ORIGINAL_MESSAGE, 0);
        });

        // when
        LBiObjIntPredicateX<Integer,Integer,X> wrapped = sutThrowing.handleBiObjIntPredX(h -> Function4U.doNothing());

        // then
        try {
            wrapped.doTest(100,100,100);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(NestedException.class)
                    .hasCauseExactlyInstanceOf(ParseException.class)
                    .hasMessage(ORIGINAL_MESSAGE);
        }
    }

    @Test
    public void testnegate() throws X {
        assertThat(sut.negate().doTest(100,100,100))
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
        LBiObjIntPredicateX<Integer,Integer,X> fun1 = LBiObjIntPredicateX.lX((a1,a2,a3) -> f1Result);
        LBiObjIntPredicateX<Integer,Integer,X> fun2 = LBiObjIntPredicateX.lX((a1,a2,a3) -> f2Result);

        //when
        LBiObjIntPredicateX<Integer,Integer,X> andFunction = fun1.and(fun2);
        LBiObjIntPredicateX<Integer,Integer,X> orFunction = fun1.or(fun2);
        LBiObjIntPredicateX<Integer,Integer,X> xorFunction = fun1.xor(fun2);

        //then
        assertThat(andFunction.doTest(100,100,100))
                .isEqualTo(andResult);

        assertThat(orFunction.doTest(100,100,100))
                .isEqualTo(orResult);

        assertThat(xorFunction.doTest(100,100,100))
                .isEqualTo(xorResult);
    }

    @Test
    public void testIsEqual() throws X  {
        //when
        LBiObjIntPredicateX<Integer,Integer,X> equals = LBiObjIntPredicateX.isEqual(1,1,1);

        //then
        assertThat(equals.doTest(1,1,1))
                .isTrue();

        assertThat(equals.doTest(0,0,0))
                .isFalse();
    }



    // <editor-fold desc="compose (functional)">

    @Test
    public void testBiObjIntPredComposeInt() throws X {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LBiObjIntPredicateX<Integer,Integer,X> sutO = (a1,a2,a3) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(90);
                assertThat(a2).isEqualTo(91);
                assertThat(a3).isEqualTo(92);
                return true;
        };

        LFunctionX<Integer,Integer,X> before1 = p0 -> {
            assertThat(p0).isEqualTo(80);
            beforeCalls.incrementAndGet();
            return 90;
        };
        LFunctionX<Integer,Integer,X> before2 = p1 -> {
            assertThat(p1).isEqualTo(81);
            beforeCalls.incrementAndGet();
            return 91;
        };
        LIntUnaryOperatorX<X> before3 = p2 -> {
            assertThat(p2).isEqualTo(82);
            beforeCalls.incrementAndGet();
            return 92;
        };

        //when
        LBiObjIntPredicateX<Integer,Integer,X> function = sutO.biObjIntPredComposeInt(before1,before2,before3);
        function.doTest(80,81,82);

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(beforeCalls.get()).isEqualTo(3);
    }


    @Test
    public void testBiObjIntPredCompose() throws X {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LBiObjIntPredicateX<Integer,Integer,X> sutO = (a1,a2,a3) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(90);
                assertThat(a2).isEqualTo(91);
                assertThat(a3).isEqualTo(92);
                return true;
        };

        LFunctionX<Integer,Integer,X> before1 = p0 -> {
            assertThat(p0).isEqualTo(80);
            beforeCalls.incrementAndGet();
            return 90;
        };
        LFunctionX<Integer,Integer,X> before2 = p1 -> {
            assertThat(p1).isEqualTo(81);
            beforeCalls.incrementAndGet();
            return 91;
        };
        LToIntFunctionX<Integer,X> before3 = p2 -> {
            assertThat(p2).isEqualTo(82);
            beforeCalls.incrementAndGet();
            return 92;
        };

        //when
        LTriPredicateX<Integer,Integer,Integer,X> function = sutO.biObjIntPredCompose(before1,before2,before3);
        function.doTest(80,81,82);

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(beforeCalls.get()).isEqualTo(3);
    }

    // </editor-fold>



    // <editor-fold desc="then (functional)">

    @Test
    public void testBoolToBiObjIntFunction0() throws X  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);

        //given (+ some assertions)
        LBiObjIntPredicateX<Integer,Integer,X> sutO = (a1,a2,a3) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(80);
                assertThat(a2).isEqualTo(81);
                assertThat(a3).isEqualTo(82);
                return true;
        };

        LBoolFunctionX<Integer,X> thenFunction = p -> {
                thenFunctionCalled.set(true);
                // boolean
                assertThat(p).isEqualTo(true);
                // Integer
                return 100;
        };

        //when
        LBiObjIntFunctionX<Integer,Integer,Integer,X> function = sutO.boolToBiObjIntFunction(thenFunction);
        Integer finalValue = function.doApply(80,81,82);

        //then - finals
        assertThat(finalValue).isEqualTo(100);
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    // </editor-fold>

    @Test
    public void testNesting() {
        assertThat(sut.nestingBiObjIntPred())
            .isInstanceOf(LBiObjIntPredicate.class);
    }

    @Test
    public void testShoving() {
        assertThat(sut.shovingBiObjIntPred())
            .isInstanceOf(LBiObjIntPredicate.class);
    }

    @Test
    public void testNestingX() {
        assertThat(sut.nestingBiObjIntPredX())
            .isInstanceOf(LBiObjIntPredicateX.class);
    }

    @Test
    public void testShovingX() {
        assertThat(sut.shovingBiObjIntPredX())
            .isInstanceOf(LBiObjIntPredicateX.class);
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void testShove() {

        // given
        LBiObjIntPredicateX<Integer,Integer,X> sutThrowing = LBiObjIntPredicateX.lX((a1,a2,a3) -> {
            throw new UnsupportedOperationException();
        });

        // when
        sutThrowing.shovingBiObjIntPred().doTest(100,100,100);
    }

    @Test
    public void testHandleBiObjIntPred() throws X {

        // given
        LBiObjIntPredicateX<Integer,Integer,X> sutThrowing = LBiObjIntPredicateX.lX((a1,a2,a3) -> {
            throw new UnsupportedOperationException();
        });

        // when
        LBiObjIntPredicateX<Integer,Integer,X> wrapped = sutThrowing.handleBiObjIntPredX(h -> {
            h.wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED);
        });

        // then
        try {
            wrapped.doTest(100,100,100);
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
                .contains("LBiObjIntPredicateX: boolean doTest(T1 a1,T2 a2,int a3) throws X");
    }


    @Test
    public void isThrowing() {
        assertThat(sut.isThrowing())
            .isTrue();
    }

    //<editor-fold desc="Variants">

    private boolean variantV1(Integer a1,int a3,Integer a2) {
        return true;
    }

    @Test
    public void compilerSubstituteVariantV1() {
        LBiObjIntPredicateX lambda = LBiObjIntPredicateX./*<T1,T2,X>*/lX1(this::variantV1);

        assertThat(lambda).isInstanceOf(LBiObjIntPredicateX.V1.class);
    }


    private boolean variantV2(Integer a2,Integer a1,int a3) {
        return true;
    }

    @Test
    public void compilerSubstituteVariantV2() {
        LBiObjIntPredicateX lambda = LBiObjIntPredicateX./*<T1,T2,X>*/lX2(this::variantV2);

        assertThat(lambda).isInstanceOf(LBiObjIntPredicateX.V2.class);
    }


    private boolean variantV3(Integer a2,int a3,Integer a1) {
        return true;
    }

    @Test
    public void compilerSubstituteVariantV3() {
        LBiObjIntPredicateX lambda = LBiObjIntPredicateX./*<T1,T2,X>*/lX3(this::variantV3);

        assertThat(lambda).isInstanceOf(LBiObjIntPredicateX.V3.class);
    }


    private boolean variantV4(int a3,Integer a1,Integer a2) {
        return true;
    }

    @Test
    public void compilerSubstituteVariantV4() {
        LBiObjIntPredicateX lambda = LBiObjIntPredicateX./*<T1,T2,X>*/lX4(this::variantV4);

        assertThat(lambda).isInstanceOf(LBiObjIntPredicateX.V4.class);
    }


    private boolean variantV5(int a3,Integer a2,Integer a1) {
        return true;
    }

    @Test
    public void compilerSubstituteVariantV5() {
        LBiObjIntPredicateX lambda = LBiObjIntPredicateX./*<T1,T2,X>*/lX5(this::variantV5);

        assertThat(lambda).isInstanceOf(LBiObjIntPredicateX.V5.class);
    }

    //</editor-fold>


    @Test void safeCompiles() {
        LBiObjIntPredicateX r1 = LBiObjIntPredicateX.safe(sut); //NOSONAR
    }

    @Test void safePropagates() {
        Object result = LBiObjIntPredicateX.safe(sut);
        assertThat(result).isSameAs(sut);
    }

    @Test void safeProtectsAgainstNpe() {
        Object result = LBiObjIntPredicateX.safe(null);
        assertThat(result).isSameAs(LBiObjIntPredicateX.lX(LBiObjIntPredicateX.safe()));
    }

    @Test <Y extends Throwable> void safeSupplierPropagates() {
        LSupplierX<LBiObjIntPredicateX<Integer,Integer,X>,Y> supplier = ()->sut;
        Object result = LBiObjIntPredicateX.safeSupplier(supplier);
        assertThat(result).isSameAs(supplier);
    }

    @Test <Y extends Throwable> void safeSupplierProtectsAgainstNpe() {
        Object result = LBiObjIntPredicateX.safeSupplier(null);
        assertThat(result).isSameAs(LBiObjIntPredicateX.safeSupplier());
    }

    @Test <Y extends Throwable> void safeSupplierCompiles() {
        LSupplierX<LBiObjIntPredicateX<Integer,Integer,X>,Y> r1 = LBiObjIntPredicateX.safeSupplier(()->sut);  //NOSONAR
    }

}
