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
public class LLongPredicateXTest<X extends ParseException> {
    private static final String ORIGINAL_MESSAGE = "Original message";
    private static final String EXCEPTION_WAS_WRAPPED = "Exception was wrapped.";
    private static final String NO_EXCEPTION_WERE_THROWN = "No exception were thrown.";

    private boolean testValue = true;



    private LLongPredicateX<X> sut = new LLongPredicateX<X>(){
        public  boolean doTest(long a1)  throws X {
            return testValue;
        }
    };

    private LLongPredicate opposite = new LLongPredicate(){
        public  boolean doTest(long a1)  {
            return testValue;
        }
    };


    private LongPredicate jre = a1 -> testValue;


    private LLongPredicateX<ParseException> sutAlwaysThrowing = LLongPredicateX.lX(a1 -> {
            throw new ParseException(ORIGINAL_MESSAGE, 0);
    });

    private LLongPredicateX<RuntimeException> sutAlwaysThrowingUnchecked = LLongPredicateX.lX(a1 -> {
            throw new IndexOutOfBoundsException(ORIGINAL_MESSAGE);
    });


    @Test
    public void testTheResult() throws X {
        assertThat(sut.doTest(100L))
            .isEqualTo(testValue);
    }

    @Test
    public void testTupleCall() throws X {

        LLongSingle domainObject = Tuple4U.lLongSingle(100L);

        Object result = sut.tupleTest(domainObject);

        assertThat(result)
            .isEqualTo(testValue);
    }

    @Test
    public void testNonNullDoTest() throws X {
        assertThat(sut.nonNullDoTest(100L))
            .isEqualTo(testValue);
    }

    @Test
    public void testNestingDoTestChecked() throws X {

        // then
        try {
            sutAlwaysThrowing.nestingDoTest(100L);
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
            sutAlwaysThrowingUnchecked.nestingDoTest(100L);
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
            sutAlwaysThrowing.shovingDoTest(100L);
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
            sutAlwaysThrowingUnchecked.shovingDoTest(100L);
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
        assertThat(sut.doApplyAsBoolean(100L))
            .isEqualTo(testValue);

    }


    @Test
    public void testFunctionalInterfaceDescription() throws X {
        assertThat(sut.functionalInterfaceDescription())
            .isEqualTo("LLongPredicateX: boolean doTest(long a1) throws X");
    }

    @Test
    public void testLXMethod() throws X {
        assertThat(LLongPredicateX.lX(a1 -> testValue ))
            .isInstanceOf(LLongPredicateX.class);
    }

    @Test
    public void testWrapXMethod() throws X {
        assertThat(LLongPredicateX.wrapX(opposite))
            .isInstanceOf(LLongPredicateX.class);
    }

    @Test
    public void testWrapStdMethod() throws X {
        assertThat(LLongPredicateX.wrap(jre))
            .isInstanceOf(LLongPredicateX.class);
    }


    @Test
    public void testHandlingDoTestMethodWrapsTheException() throws X {

        // given
        LLongPredicateX<X> sutThrowing = LLongPredicateX.lX(a1 -> {
            throw new UnsupportedOperationException();
        });

        // when
        LLongPredicateX<RuntimeException> wrapped = sutThrowing.handleLongPredX(handler -> handler
            .wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED));

        // then
        try {
            wrapped.doTest(100L);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IllegalArgumentException.class)
                    .hasCauseExactlyInstanceOf(UnsupportedOperationException.class)
                    .hasMessage(EXCEPTION_WAS_WRAPPED);
        }
    }

    @Test
    public void testHandleLongPredXMethodDoNotWrapsOtherExceptionIf() throws X {

        // given
        LLongPredicateX<X> sutThrowing = LLongPredicateX.lX(a1 -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LLongPredicateX<X> wrapped = sutThrowing.handleLongPredX(handler -> handler
                .wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED)
                .throwIf(IndexOutOfBoundsException.class));

        // then
        try {
            wrapped.doTest(100L);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                    .hasNoCause();
        }
    }

@Test
    public void testHandleLongPredXMethodDoNotWrapsOtherExceptionWhen() throws X {

        // given
        LLongPredicateX<X> sutThrowing = LLongPredicateX.lX(a1 -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LLongPredicateX<X> wrapped = sutThrowing.handleLongPredX(handler -> handler
                .wrapWhen(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED)
                .throwIf(IndexOutOfBoundsException.class));

        // then
        try {
            wrapped.doTest(100L);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                    .hasNoCause();
        }
    }


    @Test
    public void testHandleLongPredXMishandlingExceptionIsAllowed() throws X {

        // given
        LLongPredicateX<X> sutThrowing = LLongPredicateX.lX(a1 -> {
            throw (X) new ParseException(ORIGINAL_MESSAGE, 0);
        });

        // when
        LLongPredicateX<X> wrapped = sutThrowing.handleLongPredX(h -> Function4U.doNothing());

        // then
        try {
            wrapped.doTest(100L);
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
        assertThat(sut.negate().doTest(100L))
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
        LLongPredicateX<X> fun1 = LLongPredicateX.lX(a1 -> f1Result);
        LLongPredicateX<X> fun2 = LLongPredicateX.lX(a1 -> f2Result);

        //when
        LLongPredicateX<X> andFunction = fun1.and(fun2);
        LLongPredicateX<X> orFunction = fun1.or(fun2);
        LLongPredicateX<X> xorFunction = fun1.xor(fun2);

        //then
        assertThat(andFunction.doTest(100L))
                .isEqualTo(andResult);

        assertThat(orFunction.doTest(100L))
                .isEqualTo(orResult);

        assertThat(xorFunction.doTest(100L))
                .isEqualTo(xorResult);
    }

    @Test
    public void testIsEqual() throws X  {
        //when
        LLongPredicateX<X> equals = LLongPredicateX.isEqual(1L);

        //then
        assertThat(equals.doTest(1L))
                .isTrue();

        assertThat(equals.doTest(0L))
                .isFalse();
    }



    // <editor-fold desc="compose (functional)">

    @Test
    public void testLongPredComposeLong() throws X {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LLongPredicateX<X> sutO = a1 -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(90L);
                return true;
        };

        LLongUnaryOperatorX<X> before1 = p0 -> {
            assertThat(p0).isEqualTo(80L);
            beforeCalls.incrementAndGet();
            return 90L;
        };

        //when
        LLongPredicateX<X> function = sutO.longPredComposeLong(before1);
        function.doTest(80L);

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(beforeCalls.get()).isEqualTo(1);
    }


    @Test
    public void testLongPredCompose() throws X {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LLongPredicateX<X> sutO = a1 -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(90L);
                return true;
        };

        LToLongFunctionX<Integer,X> before1 = p0 -> {
            assertThat(p0).isEqualTo(80);
            beforeCalls.incrementAndGet();
            return 90L;
        };

        //when
        LPredicateX<Integer,X> function = sutO.longPredCompose(before1);
        function.doTest(80);

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(beforeCalls.get()).isEqualTo(1);
    }

    // </editor-fold>



    // <editor-fold desc="then (functional)">

    @Test
    public void testBoolToLongFunction0() throws X  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);

        //given (+ some assertions)
        LLongPredicateX<X> sutO = a1 -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(80L);
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
        LLongFunctionX<Integer,X> function = sutO.boolToLongFunction(thenFunction);
        Integer finalValue = function.doApply(80L);

        //then - finals
        assertThat(finalValue).isEqualTo(100);
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    @Test
    public void testBoolToLongToByteFunction1() throws X  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);

        //given (+ some assertions)
        LLongPredicateX<X> sutO = a1 -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(80L);
                return true;
        };

        LBoolToByteFunctionX<X> thenFunction = p -> {
                thenFunctionCalled.set(true);
                // boolean
                assertThat(p).isEqualTo(true);
                // byte
                return (byte)100;
        };

        //when
        LLongToByteFunctionX<X> function = sutO.boolToLongToByteFunction(thenFunction);
        byte finalValue = function.doApplyAsByte(80L);

        //then - finals
        assertThat(finalValue).isEqualTo((byte)100);
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    @Test
    public void testBoolToLongToShortFunction2() throws X  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);

        //given (+ some assertions)
        LLongPredicateX<X> sutO = a1 -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(80L);
                return true;
        };

        LBoolToShortFunctionX<X> thenFunction = p -> {
                thenFunctionCalled.set(true);
                // boolean
                assertThat(p).isEqualTo(true);
                // short
                return (short)100;
        };

        //when
        LLongToShortFunctionX<X> function = sutO.boolToLongToShortFunction(thenFunction);
        short finalValue = function.doApplyAsShort(80L);

        //then - finals
        assertThat(finalValue).isEqualTo((short)100);
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    @Test
    public void testBoolToLongToIntFunction3() throws X  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);

        //given (+ some assertions)
        LLongPredicateX<X> sutO = a1 -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(80L);
                return true;
        };

        LBoolToIntFunctionX<X> thenFunction = p -> {
                thenFunctionCalled.set(true);
                // boolean
                assertThat(p).isEqualTo(true);
                // int
                return 100;
        };

        //when
        LLongToIntFunctionX<X> function = sutO.boolToLongToIntFunction(thenFunction);
        int finalValue = function.doApplyAsInt(80L);

        //then - finals
        assertThat(finalValue).isEqualTo(100);
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    @Test
    public void testBoolToLongUnaryOperator4() throws X  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);

        //given (+ some assertions)
        LLongPredicateX<X> sutO = a1 -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(80L);
                return true;
        };

        LBoolToLongFunctionX<X> thenFunction = p -> {
                thenFunctionCalled.set(true);
                // boolean
                assertThat(p).isEqualTo(true);
                // long
                return 100L;
        };

        //when
        LLongUnaryOperatorX<X> function = sutO.boolToLongUnaryOperator(thenFunction);
        long finalValue = function.doApplyAsLong(80L);

        //then - finals
        assertThat(finalValue).isEqualTo(100L);
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    @Test
    public void testBoolToLongToFloatFunction5() throws X  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);

        //given (+ some assertions)
        LLongPredicateX<X> sutO = a1 -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(80L);
                return true;
        };

        LBoolToFloatFunctionX<X> thenFunction = p -> {
                thenFunctionCalled.set(true);
                // boolean
                assertThat(p).isEqualTo(true);
                // float
                return 100f;
        };

        //when
        LLongToFloatFunctionX<X> function = sutO.boolToLongToFloatFunction(thenFunction);
        float finalValue = function.doApplyAsFloat(80L);

        //then - finals
        assertThat(finalValue).isEqualTo(100f);
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    @Test
    public void testBoolToLongToDoubleFunction6() throws X  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);

        //given (+ some assertions)
        LLongPredicateX<X> sutO = a1 -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(80L);
                return true;
        };

        LBoolToDoubleFunctionX<X> thenFunction = p -> {
                thenFunctionCalled.set(true);
                // boolean
                assertThat(p).isEqualTo(true);
                // double
                return 100d;
        };

        //when
        LLongToDoubleFunctionX<X> function = sutO.boolToLongToDoubleFunction(thenFunction);
        double finalValue = function.doApplyAsDouble(80L);

        //then - finals
        assertThat(finalValue).isEqualTo(100d);
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    @Test
    public void testBoolToLongToCharFunction7() throws X  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);

        //given (+ some assertions)
        LLongPredicateX<X> sutO = a1 -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(80L);
                return true;
        };

        LBoolToCharFunctionX<X> thenFunction = p -> {
                thenFunctionCalled.set(true);
                // boolean
                assertThat(p).isEqualTo(true);
                // char
                return '\u0100';
        };

        //when
        LLongToCharFunctionX<X> function = sutO.boolToLongToCharFunction(thenFunction);
        char finalValue = function.doApplyAsChar(80L);

        //then - finals
        assertThat(finalValue).isEqualTo('\u0100');
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    @Test
    public void testBoolToLongPredicate8() throws X  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);

        //given (+ some assertions)
        LLongPredicateX<X> sutO = a1 -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(80L);
                return true;
        };

        LLogicalOperatorX<X> thenFunction = p -> {
                thenFunctionCalled.set(true);
                // boolean
                assertThat(p).isEqualTo(true);
                // boolean
                return true;
        };

        //when
        LLongPredicateX<X> function = sutO.boolToLongPredicate(thenFunction);
        boolean finalValue = function.doTest(80L);

        //then - finals
        assertThat(finalValue).isEqualTo(true);
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    // </editor-fold>

    @Test
    public void testNesting() {
        assertThat(sut.nestingLongPred())
            .isInstanceOf(LLongPredicate.class);
    }

    @Test
    public void testShoving() {
        assertThat(sut.shovingLongPred())
            .isInstanceOf(LLongPredicate.class);
    }

    @Test
    public void testNestingX() {
        assertThat(sut.nestingLongPredX())
            .isInstanceOf(LLongPredicateX.class);
    }

    @Test
    public void testShovingX() {
        assertThat(sut.shovingLongPredX())
            .isInstanceOf(LLongPredicateX.class);
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void testShove() {

        // given
        LLongPredicateX<X> sutThrowing = LLongPredicateX.lX(a1 -> {
            throw new UnsupportedOperationException();
        });

        // when
        sutThrowing.shovingLongPred().doTest(100L);
    }

    @Test
    public void testHandleLongPred() throws X {

        // given
        LLongPredicateX<X> sutThrowing = LLongPredicateX.lX(a1 -> {
            throw new UnsupportedOperationException();
        });

        // when
        LLongPredicateX<X> wrapped = sutThrowing.handleLongPredX(h -> {
            h.wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED);
        });

        // then
        try {
            wrapped.doTest(100L);
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
                .contains("LLongPredicateX: boolean doTest(long a1) throws X");
    }


    @Test
    public void isThrowing() {
        assertThat(sut.isThrowing())
            .isTrue();
    }

    @Test void safeCompiles() {
        LLongPredicateX r1 = LLongPredicateX.safe(sut); //NOSONAR
        LongPredicate r3 = LLongPredicateX.safe(sut); //NOSONAR
    }

    @Test void safePropagates() {
        Object result = LLongPredicateX.safe(sut);
        assertThat(result).isSameAs(sut);
    }

    @Test void safeProtectsAgainstNpe() {
        Object result = LLongPredicateX.safe(null);
        assertThat(result).isSameAs(LLongPredicateX.lX(LLongPredicateX.safe()));
    }

    @Test <Y extends Throwable> void safeSupplierPropagates() {
        LSupplierX<LLongPredicateX<X>,Y> supplier = ()->sut;
        Object result = LLongPredicateX.safeSupplier(supplier);
        assertThat(result).isSameAs(supplier);
    }

    @Test <Y extends Throwable> void safeSupplierProtectsAgainstNpe() {
        Object result = LLongPredicateX.safeSupplier(null);
        assertThat(result).isSameAs(LLongPredicateX.safeSupplier());
    }

    @Test <Y extends Throwable> void safeSupplierCompiles() {
        LSupplierX<LLongPredicateX<X>,Y> r1 = LLongPredicateX.safeSupplier(()->sut);  //NOSONAR
        Supplier<LLongPredicateX<X>> r2 = LLongPredicateX.safeSupplier(()->sut); //NOSONAR
    }

}
