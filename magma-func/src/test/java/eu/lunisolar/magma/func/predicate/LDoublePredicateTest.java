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
public class LDoublePredicateTest<X extends ParseException> {
    private static final String ORIGINAL_MESSAGE = "Original message";
    private static final String EXCEPTION_WAS_WRAPPED = "Exception was wrapped.";
    private static final String NO_EXCEPTION_WERE_THROWN = "No exception were thrown.";

    private boolean testValue = true;



    private LDoublePredicate sut = new LDoublePredicate(){
        public  boolean doTest(double a1)  {
            return testValue;
        }
    };

    private LDoublePredicateX<X> opposite = new LDoublePredicateX<X>(){
        public  boolean doTest(double a1)  throws X {
            return testValue;
        }
    };


    private DoublePredicate jre = a1 -> testValue;



    private LDoublePredicateX<RuntimeException> sutAlwaysThrowingUnchecked = LDoublePredicate.l(a1 -> {
            throw new IndexOutOfBoundsException(ORIGINAL_MESSAGE);
    });


    @Test
    public void testTheResult() throws X {
        assertThat(sut.doTest(100d))
            .isEqualTo(testValue);
    }

    @Test
    public void testTupleCall() throws X {

        LDoubleSingle domainObject = Tuple4U.lDoubleSingle(100d);

        Object result = sut.tupleTest(domainObject);

        assertThat(result)
            .isEqualTo(testValue);
    }

    @Test
    public void testNonNullDoTest() throws X {
        assertThat(sut.nonNullDoTest(100d))
            .isEqualTo(testValue);
    }

    @Test
    public void testNestingDoTestUnchecked() throws X {

        // then
        try {
            sutAlwaysThrowingUnchecked.nestingDoTest(100d);
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
            sutAlwaysThrowingUnchecked.shovingDoTest(100d);
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
        assertThat(sut.doApplyAsBoolean(100d))
            .isEqualTo(testValue);

    }


    @Test
    public void testFunctionalInterfaceDescription() throws X {
        assertThat(sut.functionalInterfaceDescription())
            .isEqualTo("LDoublePredicate: boolean doTest(double a1)");
    }

    @Test
    public void testLMethod() throws X {
        assertThat(LDoublePredicate.l(a1 -> testValue ))
            .isInstanceOf(LDoublePredicate.class);
    }

    @Test
    public void testWrapMethod() throws X {
        assertThat(LDoublePredicate.wrap(opposite))
            .isInstanceOf(LDoublePredicate.class);
    }

    @Test
    public void testWrapStdMethod() throws X {
        assertThat(LDoublePredicate.wrap(jre))
            .isInstanceOf(LDoublePredicate.class);
    }

    @Test
    public void testWrapMethodDoNotWrapsRuntimeException() throws X {
        // given
        LDoublePredicateX<X> sutThrowing = LDoublePredicateX.lX(a1 -> {
            throw new UnsupportedOperationException(ORIGINAL_MESSAGE);
        });

        // when
        LDoublePredicate wrapped = LDoublePredicate.wrap(sutThrowing);

        // then
        try {
            wrapped.doTest(100d);
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
        LDoublePredicateX<ParseException> sutThrowing = LDoublePredicateX.lX(a1 -> {
            throw new ParseException(ORIGINAL_MESSAGE, 0);
        });

        // when
        LDoublePredicate wrapped = LDoublePredicate.wrap(sutThrowing);

        // then
        try {
            wrapped.doTest(100d);
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
        LDoublePredicate sutThrowing = LDoublePredicate.l(a1 -> {
            throw new UnsupportedOperationException();
        });

        // when
        LDoublePredicate wrapped = sutThrowing.handleDoublePred(handler -> handler
            .wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED));

        // then
        try {
            wrapped.doTest(100d);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IllegalArgumentException.class)
                    .hasCauseExactlyInstanceOf(UnsupportedOperationException.class)
                    .hasMessage(EXCEPTION_WAS_WRAPPED);
        }
    }

    @Test
    public void testHandleDoublePredMethodDoNotWrapsOtherExceptionIf() throws X {

        // given
        LDoublePredicate sutThrowing = LDoublePredicate.l(a1 -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LDoublePredicate wrapped = sutThrowing.handleDoublePred(handler -> handler
                .wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED)
                .throwIf(IndexOutOfBoundsException.class));

        // then
        try {
            wrapped.doTest(100d);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                    .hasNoCause();
        }
    }

@Test
    public void testHandleDoublePredMethodDoNotWrapsOtherExceptionWhen() throws X {

        // given
        LDoublePredicate sutThrowing = LDoublePredicate.l(a1 -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LDoublePredicate wrapped = sutThrowing.handleDoublePred(handler -> handler
                .wrapWhen(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED)
                .throwIf(IndexOutOfBoundsException.class));

        // then
        try {
            wrapped.doTest(100d);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                    .hasNoCause();
        }
    }


    @Test
    public void testHandleDoublePredMishandlingExceptionIsAllowed() throws X {

        // given
        LDoublePredicate sutThrowing = LDoublePredicate.l(a1 -> {
            throw new UnsupportedOperationException(ORIGINAL_MESSAGE);
        });

        // when
        LDoublePredicate wrapped = sutThrowing.handleDoublePred(h -> Function4U.doNothing());

        // then
        try {
            wrapped.doTest(100d);
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
        assertThat(sut.negate().doTest(100d))
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
        LDoublePredicate fun1 = LDoublePredicate.l(a1 -> f1Result);
        LDoublePredicate fun2 = LDoublePredicate.l(a1 -> f2Result);

        //when
        LDoublePredicate andFunction = fun1.and(fun2);
        LDoublePredicate orFunction = fun1.or(fun2);
        LDoublePredicate xorFunction = fun1.xor(fun2);

        //then
        assertThat(andFunction.doTest(100d))
                .isEqualTo(andResult);

        assertThat(orFunction.doTest(100d))
                .isEqualTo(orResult);

        assertThat(xorFunction.doTest(100d))
                .isEqualTo(xorResult);
    }

    @Test
    public void testIsEqual() throws X  {
        //when
        LDoublePredicate equals = LDoublePredicate.isEqual(1d);

        //then
        assertThat(equals.doTest(1d))
                .isTrue();

        assertThat(equals.doTest(0d))
                .isFalse();
    }



    // <editor-fold desc="compose (functional)">

    @Test
    public void testDoublePredComposeDouble() throws X {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LDoublePredicate sutO = a1 -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(90d);
                return true;
        };

        LDoubleUnaryOperator before1 = p0 -> {
            assertThat(p0).isEqualTo(80d);
            beforeCalls.incrementAndGet();
            return 90d;
        };

        //when
        LDoublePredicate function = sutO.doublePredComposeDouble(before1);
        function.doTest(80d);

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(beforeCalls.get()).isEqualTo(1);
    }


    @Test
    public void testDoublePredCompose() throws X {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LDoublePredicate sutO = a1 -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(90d);
                return true;
        };

        LToDoubleFunction<Integer> before1 = p0 -> {
            assertThat(p0).isEqualTo(80);
            beforeCalls.incrementAndGet();
            return 90d;
        };

        //when
        LPredicate<Integer> function = sutO.doublePredCompose(before1);
        function.doTest(80);

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(beforeCalls.get()).isEqualTo(1);
    }

    // </editor-fold>



    // <editor-fold desc="then (functional)">

    @Test
    public void testBoolToDoubleFunction0() throws X  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);

        //given (+ some assertions)
        LDoublePredicate sutO = a1 -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(80d);
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
        LDoubleFunction<Integer> function = sutO.boolToDoubleFunction(thenFunction);
        Integer finalValue = function.doApply(80d);

        //then - finals
        assertThat(finalValue).isEqualTo(100);
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    @Test
    public void testBoolToDoubleToByteFunction1() throws X  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);

        //given (+ some assertions)
        LDoublePredicate sutO = a1 -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(80d);
                return true;
        };

        LBoolToByteFunction thenFunction = p -> {
                thenFunctionCalled.set(true);
                // boolean
                assertThat(p).isEqualTo(true);
                // byte
                return (byte)100;
        };

        //when
        LDoubleToByteFunction function = sutO.boolToDoubleToByteFunction(thenFunction);
        byte finalValue = function.doApplyAsByte(80d);

        //then - finals
        assertThat(finalValue).isEqualTo((byte)100);
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    @Test
    public void testBoolToDoubleToShortFunction2() throws X  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);

        //given (+ some assertions)
        LDoublePredicate sutO = a1 -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(80d);
                return true;
        };

        LBoolToShortFunction thenFunction = p -> {
                thenFunctionCalled.set(true);
                // boolean
                assertThat(p).isEqualTo(true);
                // short
                return (short)100;
        };

        //when
        LDoubleToShortFunction function = sutO.boolToDoubleToShortFunction(thenFunction);
        short finalValue = function.doApplyAsShort(80d);

        //then - finals
        assertThat(finalValue).isEqualTo((short)100);
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    @Test
    public void testBoolToDoubleToIntFunction3() throws X  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);

        //given (+ some assertions)
        LDoublePredicate sutO = a1 -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(80d);
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
        LDoubleToIntFunction function = sutO.boolToDoubleToIntFunction(thenFunction);
        int finalValue = function.doApplyAsInt(80d);

        //then - finals
        assertThat(finalValue).isEqualTo(100);
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    @Test
    public void testBoolToDoubleToLongFunction4() throws X  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);

        //given (+ some assertions)
        LDoublePredicate sutO = a1 -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(80d);
                return true;
        };

        LBoolToLongFunction thenFunction = p -> {
                thenFunctionCalled.set(true);
                // boolean
                assertThat(p).isEqualTo(true);
                // long
                return 100L;
        };

        //when
        LDoubleToLongFunction function = sutO.boolToDoubleToLongFunction(thenFunction);
        long finalValue = function.doApplyAsLong(80d);

        //then - finals
        assertThat(finalValue).isEqualTo(100L);
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    @Test
    public void testBoolToDoubleToFloatFunction5() throws X  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);

        //given (+ some assertions)
        LDoublePredicate sutO = a1 -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(80d);
                return true;
        };

        LBoolToFloatFunction thenFunction = p -> {
                thenFunctionCalled.set(true);
                // boolean
                assertThat(p).isEqualTo(true);
                // float
                return 100f;
        };

        //when
        LDoubleToFloatFunction function = sutO.boolToDoubleToFloatFunction(thenFunction);
        float finalValue = function.doApplyAsFloat(80d);

        //then - finals
        assertThat(finalValue).isEqualTo(100f);
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    @Test
    public void testBoolToDoubleUnaryOperator6() throws X  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);

        //given (+ some assertions)
        LDoublePredicate sutO = a1 -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(80d);
                return true;
        };

        LBoolToDoubleFunction thenFunction = p -> {
                thenFunctionCalled.set(true);
                // boolean
                assertThat(p).isEqualTo(true);
                // double
                return 100d;
        };

        //when
        LDoubleUnaryOperator function = sutO.boolToDoubleUnaryOperator(thenFunction);
        double finalValue = function.doApplyAsDouble(80d);

        //then - finals
        assertThat(finalValue).isEqualTo(100d);
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    @Test
    public void testBoolToDoubleToCharFunction7() throws X  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);

        //given (+ some assertions)
        LDoublePredicate sutO = a1 -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(80d);
                return true;
        };

        LBoolToCharFunction thenFunction = p -> {
                thenFunctionCalled.set(true);
                // boolean
                assertThat(p).isEqualTo(true);
                // char
                return '\u0100';
        };

        //when
        LDoubleToCharFunction function = sutO.boolToDoubleToCharFunction(thenFunction);
        char finalValue = function.doApplyAsChar(80d);

        //then - finals
        assertThat(finalValue).isEqualTo('\u0100');
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    @Test
    public void testBoolToDoublePredicate8() throws X  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);

        //given (+ some assertions)
        LDoublePredicate sutO = a1 -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(80d);
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
        LDoublePredicate function = sutO.boolToDoublePredicate(thenFunction);
        boolean finalValue = function.doTest(80d);

        //then - finals
        assertThat(finalValue).isEqualTo(true);
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    // </editor-fold>

    @Test
    public void testNesting() {
        assertThat(sut.nestingDoublePred())
            .isSameAs(sut)
            .isInstanceOf(LDoublePredicate.class);
    }

    @Test
    public void testShoving() {
        assertThat(sut.shovingDoublePred())
            .isSameAs(sut)
            .isInstanceOf(LDoublePredicate.class);
    }

    @Test
    public void testNestingX() {
        assertThat(sut.nestingDoublePredX())
            .isSameAs(sut)
            .isInstanceOf(LDoublePredicateX.class);
    }

    @Test
    public void testShovingX() {
        assertThat(sut.shovingDoublePredX())
            .isSameAs(sut)
            .isInstanceOf(LDoublePredicateX.class);
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void testShove() {

        // given
        LDoublePredicate sutThrowing = LDoublePredicate.l(a1 -> {
            throw new UnsupportedOperationException();
        });

        // when
        sutThrowing.shovingDoublePred().doTest(100d);
    }

    @Test
    public void testHandleDoublePred() throws X {

        // given
        LDoublePredicate sutThrowing = LDoublePredicate.l(a1 -> {
            throw new UnsupportedOperationException();
        });

        // when
        LDoublePredicate wrapped = sutThrowing.handleDoublePred(h -> {
            h.wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED);
        });

        // then
        try {
            wrapped.doTest(100d);
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
                .contains("LDoublePredicate: boolean doTest(double a1)");
    }


    @Test
    public void isThrowing() {
        assertThat(sut.isThrowing())
            .isFalse();
    }

    @Test void safeCompiles() {
        LDoublePredicate r1 = LDoublePredicate.safe(sut); //NOSONAR
        LDoublePredicateX r2 = LDoublePredicate.safe(sut); //NOSONAR
        DoublePredicate r3 = LDoublePredicate.safe(sut); //NOSONAR
    }

    @Test void safePropagates() {
        Object result = LDoublePredicate.safe(sut);
        assertThat(result).isSameAs(sut);
    }

    @Test void safeProtectsAgainstNpe() {
        Object result = LDoublePredicate.safe(null);
        assertThat(result).isSameAs(LDoublePredicate.l(LDoublePredicate.safe()));
    }

    @Test  void safeSupplierPropagates() {
        LSupplier<LDoublePredicate> supplier = ()->sut;
        Object result = LDoublePredicate.safeSupplier(supplier);
        assertThat(result).isSameAs(supplier);
    }

    @Test  void safeSupplierProtectsAgainstNpe() {
        Object result = LDoublePredicate.safeSupplier(null);
        assertThat(result).isSameAs(LDoublePredicate.safeSupplier());
    }

    @Test  void safeSupplierCompiles() {
        LSupplier<LDoublePredicate> r1 = LDoublePredicate.safeSupplier(()->sut);  //NOSONAR
        Supplier<LDoublePredicate> r2 = LDoublePredicate.safeSupplier(()->sut); //NOSONAR
    }

}
