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
public class LBiDoublePredicateXTest<X extends ParseException> {
    private static final String ORIGINAL_MESSAGE = "Original message";
    private static final String EXCEPTION_WAS_WRAPPED = "Exception was wrapped.";
    private static final String NO_EXCEPTION_WERE_THROWN = "No exception were thrown.";

    private boolean testValue = true;



    private LBiDoublePredicateX<X> sut = new LBiDoublePredicateX<X>(){
        public  boolean doTest(double a1,double a2)  throws X {
            return testValue;
        }
    };

    private LBiDoublePredicate opposite = new LBiDoublePredicate(){
        public  boolean doTest(double a1,double a2)  {
            return testValue;
        }
    };



    private LBiDoublePredicateX<ParseException> sutAlwaysThrowing = LBiDoublePredicateX.lX((a1,a2) -> {
            throw new ParseException(ORIGINAL_MESSAGE, 0);
    });

    private LBiDoublePredicateX<RuntimeException> sutAlwaysThrowingUnchecked = LBiDoublePredicateX.lX((a1,a2) -> {
            throw new IndexOutOfBoundsException(ORIGINAL_MESSAGE);
    });


    @Test
    public void testTheResult() throws X {
        assertThat(sut.doTest(100d,100d))
            .isEqualTo(testValue);
    }

    @Test
    public void testTupleCall() throws X {

        LDoublePair domainObject = Tuple4U.doublePair(100d,100d);

        Object result = sut.tupleTest(domainObject);

        assertThat(result)
            .isEqualTo(testValue);
    }

    @Test
    public void testNonNullDoTest() throws X {
        assertThat(sut.nonNullDoTest(100d,100d))
            .isEqualTo(testValue);
    }

    @Test
    public void testNestingDoTestChecked() throws X {

        // then
        try {
            sutAlwaysThrowing.nestingDoTest(100d,100d);
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
            sutAlwaysThrowingUnchecked.nestingDoTest(100d,100d);
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
            sutAlwaysThrowing.shovingDoTest(100d,100d);
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
            sutAlwaysThrowingUnchecked.shovingDoTest(100d,100d);
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
        assertThat(sut.doApplyAsBoolean(100d,100d))
            .isEqualTo(testValue);

    }


    @Test
    public void testFunctionalInterfaceDescription() throws X {
        assertThat(sut.functionalInterfaceDescription())
            .isEqualTo("LBiDoublePredicateX: boolean doTest(double a1,double a2) throws X");
    }

    @Test
    public void testLXMethod() throws X {
        assertThat(LBiDoublePredicateX.lX((a1,a2) -> testValue ))
            .isInstanceOf(LBiDoublePredicateX.class);
    }

    @Test
    public void testWrapXMethod() throws X {
        assertThat(LBiDoublePredicateX.wrapX(opposite))
            .isInstanceOf(LBiDoublePredicateX.class);
    }


    @Test
    public void testHandlingDoTestMethodWrapsTheException() throws X {

        // given
        LBiDoublePredicateX<X> sutThrowing = LBiDoublePredicateX.lX((a1,a2) -> {
            throw new UnsupportedOperationException();
        });

        // when
        LBiDoublePredicateX<RuntimeException> wrapped = sutThrowing.handleBiDoublePredX(handler -> handler
            .wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED));

        // then
        try {
            wrapped.doTest(100d,100d);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IllegalArgumentException.class)
                    .hasCauseExactlyInstanceOf(UnsupportedOperationException.class)
                    .hasMessage(EXCEPTION_WAS_WRAPPED);
        }
    }

    @Test
    public void testHandleBiDoublePredXMethodDoNotWrapsOtherExceptionIf() throws X {

        // given
        LBiDoublePredicateX<X> sutThrowing = LBiDoublePredicateX.lX((a1,a2) -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LBiDoublePredicateX<X> wrapped = sutThrowing.handleBiDoublePredX(handler -> handler
                .wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED)
                .throwIf(IndexOutOfBoundsException.class));

        // then
        try {
            wrapped.doTest(100d,100d);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                    .hasNoCause();
        }
    }

@Test
    public void testHandleBiDoublePredXMethodDoNotWrapsOtherExceptionWhen() throws X {

        // given
        LBiDoublePredicateX<X> sutThrowing = LBiDoublePredicateX.lX((a1,a2) -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LBiDoublePredicateX<X> wrapped = sutThrowing.handleBiDoublePredX(handler -> handler
                .wrapWhen(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED)
                .throwIf(IndexOutOfBoundsException.class));

        // then
        try {
            wrapped.doTest(100d,100d);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                    .hasNoCause();
        }
    }


    @Test
    public void testHandleBiDoublePredXMishandlingExceptionIsAllowed() throws X {

        // given
        LBiDoublePredicateX<X> sutThrowing = LBiDoublePredicateX.lX((a1,a2) -> {
            throw (X) new ParseException(ORIGINAL_MESSAGE, 0);
        });

        // when
        LBiDoublePredicateX<X> wrapped = sutThrowing.handleBiDoublePredX(h -> Function4U.doNothing());

        // then
        try {
            wrapped.doTest(100d,100d);
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
        assertThat(sut.negate().doTest(100d,100d))
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
        LBiDoublePredicateX<X> fun1 = LBiDoublePredicateX.lX((a1,a2) -> f1Result);
        LBiDoublePredicateX<X> fun2 = LBiDoublePredicateX.lX((a1,a2) -> f2Result);

        //when
        LBiDoublePredicateX<X> andFunction = fun1.and(fun2);
        LBiDoublePredicateX<X> orFunction = fun1.or(fun2);
        LBiDoublePredicateX<X> xorFunction = fun1.xor(fun2);

        //then
        assertThat(andFunction.doTest(100d,100d))
                .isEqualTo(andResult);

        assertThat(orFunction.doTest(100d,100d))
                .isEqualTo(orResult);

        assertThat(xorFunction.doTest(100d,100d))
                .isEqualTo(xorResult);
    }

    @Test
    public void testIsEqual() throws X  {
        //when
        LBiDoublePredicateX<X> equals = LBiDoublePredicateX.isEqual(1d,1d);

        //then
        assertThat(equals.doTest(1d,1d))
                .isTrue();

        assertThat(equals.doTest(0d,0d))
                .isFalse();
    }



    // <editor-fold desc="compose (functional)">

    @Test
    public void testBiDoublePredComposeDouble() throws X {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LBiDoublePredicateX<X> sutO = (a1,a2) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(90d);
                assertThat(a2).isEqualTo(91d);
                return true;
        };

        LDoubleUnaryOperatorX<X> before1 = p0 -> {
            assertThat(p0).isEqualTo(80d);
            beforeCalls.incrementAndGet();
            return 90d;
        };
        LDoubleUnaryOperatorX<X> before2 = p1 -> {
            assertThat(p1).isEqualTo(81d);
            beforeCalls.incrementAndGet();
            return 91d;
        };

        //when
        LBiDoublePredicateX<X> function = sutO.biDoublePredComposeDouble(before1,before2);
        function.doTest(80d,81d);

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(beforeCalls.get()).isEqualTo(2);
    }


    @Test
    public void testBiDoublePredCompose() throws X {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LBiDoublePredicateX<X> sutO = (a1,a2) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(90d);
                assertThat(a2).isEqualTo(91d);
                return true;
        };

        LToDoubleFunctionX<Integer,X> before1 = p0 -> {
            assertThat(p0).isEqualTo(80);
            beforeCalls.incrementAndGet();
            return 90d;
        };
        LToDoubleFunctionX<Integer,X> before2 = p1 -> {
            assertThat(p1).isEqualTo(81);
            beforeCalls.incrementAndGet();
            return 91d;
        };

        //when
        LBiPredicateX<Integer,Integer,X> function = sutO.biDoublePredCompose(before1,before2);
        function.doTest(80,81);

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(beforeCalls.get()).isEqualTo(2);
    }

    // </editor-fold>



    // <editor-fold desc="then (functional)">

    @Test
    public void testBoolToBiDoubleFunc0() throws X  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);

        //given (+ some assertions)
        LBiDoublePredicateX<X> sutO = (a1,a2) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(80d);
                assertThat(a2).isEqualTo(81d);
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
        LBiDoubleFunctionX<Integer,X> function = sutO.boolToBiDoubleFunc(thenFunction);
        Integer finalValue = function.doApply(80d,81d);

        //then - finals
        assertThat(finalValue).isEqualTo(100);
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    // </editor-fold>

    @Test
    public void testNesting() {
        assertThat(sut.nestingBiDoublePred())
            .isInstanceOf(LBiDoublePredicate.class);
    }

    @Test
    public void testShoving() {
        assertThat(sut.shovingBiDoublePred())
            .isInstanceOf(LBiDoublePredicate.class);
    }

    @Test
    public void testNestingX() {
        assertThat(sut.nestingBiDoublePredX())
            .isInstanceOf(LBiDoublePredicateX.class);
    }

    @Test
    public void testShovingX() {
        assertThat(sut.shovingBiDoublePredX())
            .isInstanceOf(LBiDoublePredicateX.class);
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void testShove() {

        // given
        LBiDoublePredicateX<X> sutThrowing = LBiDoublePredicateX.lX((a1,a2) -> {
            throw new UnsupportedOperationException();
        });

        // when
        sutThrowing.shovingBiDoublePred().doTest(100d,100d);
    }

    @Test
    public void testHandleBiDoublePred() throws X {

        // given
        LBiDoublePredicateX<X> sutThrowing = LBiDoublePredicateX.lX((a1,a2) -> {
            throw new UnsupportedOperationException();
        });

        // when
        LBiDoublePredicateX<X> wrapped = sutThrowing.handleBiDoublePredX(h -> {
            h.wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED);
        });

        // then
        try {
            wrapped.doTest(100d,100d);
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
                .contains("LBiDoublePredicateX: boolean doTest(double a1,double a2) throws X");
    }


    @Test
    public void isThrowing() {
        assertThat(sut.isThrowing())
            .isTrue();
    }

    //<editor-fold desc="Variants">

    private boolean variantV1(double a2,double a1) {
        return true;
    }

    @Test
    public void compilerSubstituteVariantV1() {
        LBiDoublePredicateX lambda = LBiDoublePredicateX./*<X>*/lX1(this::variantV1);

        assertThat(lambda).isInstanceOf(LBiDoublePredicateX.V1.class);
    }

    //</editor-fold>


    @Test void safeCompiles() {
        LBiDoublePredicateX r1 = LBiDoublePredicateX.safe(sut); //NOSONAR
    }

    @Test void safePropagates() {
        Object result = LBiDoublePredicateX.safe(sut);
        assertThat(result).isSameAs(sut);
    }

    @Test void safeProtectsAgainstNpe() {
        Object result = LBiDoublePredicateX.safe(null);
        assertThat(result).isSameAs(LBiDoublePredicateX.lX(LBiDoublePredicateX.safe()));
    }

    @Test <Y extends Throwable> void safeSupplierPropagates() {
        LSupplierX<LBiDoublePredicateX<X>,Y> supplier = ()->sut;
        Object result = LBiDoublePredicateX.safeSupplier(supplier);
        assertThat(result).isSameAs(supplier);
    }

    @Test <Y extends Throwable> void safeSupplierProtectsAgainstNpe() {
        Object result = LBiDoublePredicateX.safeSupplier(null);
        assertThat(result).isSameAs(LBiDoublePredicateX.safeSupplier());
    }

    @Test <Y extends Throwable> void safeSupplierCompiles() {
        LSupplierX<LBiDoublePredicateX<X>,Y> r1 = LBiDoublePredicateX.safeSupplier(()->sut);  //NOSONAR
    }

}
