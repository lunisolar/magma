/*
 * This file is part of "lunisolar-magma".
 *
 * (C) Copyright 2015 Lunisolar (http://lunisolar.eu/).
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

package eu.lunisolar.magma.func.operator.binary;

import eu.lunisolar.magma.func.*; // NOSONAR
import javax.annotation.Nonnull; // NOSONAR
import javax.annotation.Nullable; // NOSONAR
import java.util.Objects;// NOSONAR
import eu.lunisolar.magma.basics.meta.*; // NOSONAR
import eu.lunisolar.magma.basics.meta.functional.*; // NOSONAR
import eu.lunisolar.magma.basics.meta.functional.type.*; // NOSONAR
import eu.lunisolar.magma.basics.meta.functional.domain.*; // NOSONAR
import eu.lunisolar.magma.func.operator.unary.*; // NOSONAR
import eu.lunisolar.magma.func.operator.binary.*; // NOSONAR
import eu.lunisolar.magma.func.operator.ternary.*; // NOSONAR
import eu.lunisolar.magma.func.function.*; // NOSONAR
import eu.lunisolar.magma.func.function.from.*; // NOSONAR
import eu.lunisolar.magma.func.function.to.*; // NOSONAR
import eu.lunisolar.magma.func.function.conversion.*; // NOSONAR
import eu.lunisolar.magma.func.predicate.*; // NOSONAR
import eu.lunisolar.magma.func.supplier.*; // NOSONAR
import eu.lunisolar.magma.func.consumer.primitives.obj.*; // NOSONAR
import eu.lunisolar.magma.func.consumer.primitives.bi.*; // NOSONAR
import eu.lunisolar.magma.func.consumer.primitives.tri.*; // NOSONAR
import eu.lunisolar.magma.func.consumer.primitives.*; // NOSONAR
import eu.lunisolar.magma.func.consumer.*; // NOSONAR
import eu.lunisolar.magma.func.action.*; // NOSONAR

import java.util.function.*; // NOSONAR
import org.assertj.core.api.Assertions;  //NOSONAR
import org.testng.annotations.*;      //NOSONAR
import java.util.regex.Pattern;          //NOSONAR
import java.text.ParseException;         //NOSONAR
import eu.lunisolar.magma.basics.*; //NOSONAR
import eu.lunisolar.magma.basics.exceptions.*; //NOSONAR
import java.util.concurrent.atomic.AtomicInteger; //NOSONAR
import eu.lunisolar.magma.struct.tuple.*; // NOSONAR
import static org.assertj.core.api.Assertions.*; //NOSONAR

/** The test obviously concentrate on the interface methods the function it self is very simple.  */
public class LLongBinaryOperatorXTest<X extends ParseException> {
    private static final String ORIGINAL_MESSAGE = "Original message";
    private static final String EXCEPTION_WAS_WRAPPED = "Exception was wrapped.";
    private static final String NO_EXCEPTION_WERE_THROWN = "No exception were thrown.";

    private long testValue = (long)100;



    private LLongBinaryOperatorX<X> sut = new LLongBinaryOperatorX(){
        public  long doApplyAsLong(long a1,long a2) throws ParseException {
            return testValue;
        }
    };

    private LLongBinaryOperator opposite = new LLongBinaryOperator(){
        public  long doApplyAsLong(long a1,long a2)  {
            return testValue;
        }
    };


    private LongBinaryOperator jre = (long a1,long a2) -> testValue;


    private LLongBinaryOperatorX<ParseException> sutAlwaysThrowing = LLongBinaryOperatorX.lX((long a1,long a2) -> {
            throw new ParseException(ORIGINAL_MESSAGE, 0);
    });

    private LLongBinaryOperatorX<RuntimeException> sutAlwaysThrowingUnckeck = LLongBinaryOperatorX.lX((long a1,long a2) -> {
            throw new IndexOutOfBoundsException(ORIGINAL_MESSAGE);
    });


    @Test
    public void testTheResult() throws X {
        assertThat(sut.doApplyAsLong((long)100,(long)100))
            .isEqualTo(testValue);
    }

    @Test
    public void testTupleCall() throws X {

        //FunctionalCall<LLongPair,Long,X> theCall = sut;

        LLongPair domainObject = Tuple4U.tuple((long)100,(long)100);

        Object result = sut.tupleApplyAsLong(domainObject);

        assertThat(result)
            .isEqualTo(testValue);
    }

    @Test
    public void testNonNullDoApplyAsLong() throws X {
        assertThat(sut.nonNullDoApplyAsLong((long)100,(long)100))
            .isEqualTo(testValue);
    }

    @Test
    public void testNestingDoApplyAsLongChecked() throws X {

        // then
        try {
            sutAlwaysThrowing.nestingDoApplyAsLong((long)100,(long)100);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(NestedException.class)
                    .hasCauseExactlyInstanceOf(ParseException.class)
                    .hasMessage(ORIGINAL_MESSAGE);
        }
    }

    @Test
    public void testNestingDoApplyAsLongUnckeck() throws X {

        // then
        try {
            sutAlwaysThrowingUnckeck.nestingDoApplyAsLong((long)100,(long)100);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                    .hasNoCause()
                    .hasMessage(ORIGINAL_MESSAGE);
        }
    }

    @Test
    public void testShovingDoApplyAsLongChecked() throws X {

        // then
        try {
            sutAlwaysThrowing.shovingDoApplyAsLong((long)100,(long)100);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(ParseException.class)
                    .hasNoCause()
                    .hasMessage(ORIGINAL_MESSAGE);
        }
    }

    @Test
    public void testShovingDoApplyAsLongUnckeck() throws X {

        // then
        try {
            sutAlwaysThrowingUnckeck.shovingDoApplyAsLong((long)100,(long)100);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                    .hasNoCause()
                    .hasMessage(ORIGINAL_MESSAGE);
        }
    }


    @Test
    public void testFunctionalInterfaceDescription() throws X {
        assertThat(sut.functionalInterfaceDescription())
            .isEqualTo("LLongBinaryOperatorX: long doApplyAsLong(long a1,long a2) throws X");
    }

    @Test
    public void testLXMethod() throws X {
        assertThat(LLongBinaryOperatorX.lX((long a1,long a2) -> testValue ))
            .isInstanceOf(LLongBinaryOperatorX.class);
    }

    @Test
    public void testWrapXMethod() throws X {
        assertThat(LLongBinaryOperatorX.wrapX(opposite))
            .isInstanceOf(LLongBinaryOperatorX.class);
    }

    @Test
    public void testWrapStdMethod() throws X {
        assertThat(LLongBinaryOperatorX.wrap(jre))
            .isInstanceOf(LLongBinaryOperatorX.class);
    }


    @Test
    public void testWrapExceptionMethodWrapsTheException() throws X {

        // given
        LLongBinaryOperatorX<X> sutThrowing = LLongBinaryOperatorX.lX((long a1,long a2) -> {
            throw new UnsupportedOperationException();
        });

        // when
        LLongBinaryOperatorX<X> wrapped = sutThrowing.handleLongBinaryOpX(handler -> handler
            .wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED));

        // then
        try {
            wrapped.doApplyAsLong((long)100,(long)100);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IllegalArgumentException.class)
                    .hasCauseExactlyInstanceOf(UnsupportedOperationException.class)
                    .hasMessage(EXCEPTION_WAS_WRAPPED);
        }
    }

    @Test
    public void testWrapExceptionMethodDoNotWrapsOtherExceptionIf() throws X {

        // given
        LLongBinaryOperatorX<X> sutThrowing = LLongBinaryOperatorX.lX((long a1,long a2) -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LLongBinaryOperatorX<X> wrapped = sutThrowing.handleLongBinaryOpX(handler -> handler
                .wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED)
                .throwIf(IndexOutOfBoundsException.class));

        // then
        try {
            wrapped.doApplyAsLong((long)100,(long)100);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                    .hasNoCause();
        }
    }

@Test
    public void testWrapExceptionMethodDoNotWrapsOtherExceptionWhen() throws X {

        // given
        LLongBinaryOperatorX<X> sutThrowing = LLongBinaryOperatorX.lX((long a1,long a2) -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LLongBinaryOperatorX<X> wrapped = sutThrowing.handleLongBinaryOpX(handler -> handler
                .wrapWhen(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED)
                .throwIf(IndexOutOfBoundsException.class));

        // then
        try {
            wrapped.doApplyAsLong((long)100,(long)100);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                    .hasNoCause();
        }
    }


    @Test
    public void testWrapExceptionMishandlingExceptionIsAllowed() throws X {

        // given
        LLongBinaryOperatorX<X> sutThrowing = LLongBinaryOperatorX.lX((long a1,long a2) -> {
            throw (X) new ParseException(ORIGINAL_MESSAGE, 0);
        });

        // when
        LLongBinaryOperatorX<X> wrapped = sutThrowing.handleLongBinaryOpX(h -> Function4U.doNothing());

        // then
        try {
            wrapped.doApplyAsLong((long)100,(long)100);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(NestedException.class)
                    .hasCauseExactlyInstanceOf(ParseException.class)
                    .hasMessage(ORIGINAL_MESSAGE);
        }
    }


    @Test
    public void testMin() throws X {
        //given
        long valueSmall = (long)100;
        long valueBig = (long)(valueSmall+10);

        //when
        LLongBinaryOperatorX<X> min = LLongBinaryOperatorX.min();

        //then
        assertThat(min.doApplyAsLong(valueSmall, valueBig))
                .isEqualTo(valueSmall);

        assertThat(min.doApplyAsLong(valueBig, valueSmall))
                .isEqualTo(valueSmall);
    }

    @Test
    public void testMax() throws X {
        //given
        long valueSmall = (long)100;
        long valueBig = (long)(valueSmall+10);

        //when
        LLongBinaryOperatorX<X> max = LLongBinaryOperatorX.max();

        //then
        assertThat(max.doApplyAsLong(valueSmall, valueBig))
                .isEqualTo(valueBig);

        assertThat(max.doApplyAsLong(valueBig, valueSmall))
                .isEqualTo(valueBig);
    }


    // <editor-fold desc="compose (functional)">

    @Test
    public void testlongBinaryOpComposeLong() throws X {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LLongBinaryOperatorX<X> sutO = (long a1,long a2) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo((long)90);
                assertThat(a2).isEqualTo((long)91);
                return (long)100;
        };

        LLongUnaryOperatorX<X> before1 = p0 -> {
            assertThat(p0).isEqualTo((long)80);
            beforeCalls.incrementAndGet();
            return (long)90;
        };
        LLongUnaryOperatorX<X> before2 = p1 -> {
            assertThat(p1).isEqualTo((long)81);
            beforeCalls.incrementAndGet();
            return (long)91;
        };

        //when
        LLongBinaryOperatorX<X> function = sutO.longBinaryOpComposeLong(before1,before2);
        function.doApplyAsLong((long)80,(long)81);

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(beforeCalls.get()).isEqualTo(2);
    }


    @Test
    public void testlongBinaryOpCompose() throws X {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LLongBinaryOperatorX<X> sutO = (long a1,long a2) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo((long)90);
                assertThat(a2).isEqualTo((long)91);
                return (long)100;
        };

        LToLongFunctionX<Integer ,X> before1 = p0 -> {
            assertThat(p0).isEqualTo(Integer.valueOf(80));
            beforeCalls.incrementAndGet();
            return (long)90;
        };
        LToLongFunctionX<Integer ,X> before2 = p1 -> {
            assertThat(p1).isEqualTo(Integer.valueOf(81));
            beforeCalls.incrementAndGet();
            return (long)91;
        };

        //when
        LToLongBiFunctionX<Integer ,Integer ,X> function = sutO.longBinaryOpCompose(before1,before2);
        function.doApplyAsLong((Integer )Integer.valueOf(80),(Integer )Integer.valueOf(81));

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(beforeCalls.get()).isEqualTo(2);
    }

    // </editor-fold>


    // <editor-fold desc="then (functional)">

    @Test
    public void testThen0() throws X  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);


        //given (+ some assertions)
        LLongBinaryOperatorX<X> sutO = (long a1,long a2) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo((long)80);
                assertThat(a2).isEqualTo((long)81);
                return (long)90;
        };

        LLongFunctionX<Integer ,X> thenFunction = p -> {
                thenFunctionCalled.set(true);
                // 
                assertThat(p).isEqualTo((long)90);
                // V
                return Integer.valueOf(100);
        };

        //when
        LBiLongFunctionX<Integer ,X> function = sutO.then(thenFunction);
        Integer  finalValue = function.doApply((long)80,(long)81);

        //then - finals
        assertThat(finalValue).isEqualTo(Integer.valueOf(100));
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    // </editor-fold>

    @Test
    public void testNesting() {
        assertThat(sut.nestingLongBinaryOp())
            .isInstanceOf(LLongBinaryOperator.class);
    }

    @Test
    public void testShoving() {
        assertThat(sut.shovingLongBinaryOp())
            .isInstanceOf(LLongBinaryOperator.class);
    }

    @Test
    public void testNestingX() {
        assertThat(sut.nestingLongBinaryOpX())
            .isInstanceOf(LLongBinaryOperatorX.class);
    }

    @Test
    public void testShovingX() {
        assertThat(sut.shovingLongBinaryOpX())
            .isInstanceOf(LLongBinaryOperatorX.class);
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void testShove() {

        // given
        LLongBinaryOperatorX<X> sutThrowing = LLongBinaryOperatorX.lX((long a1,long a2) -> {
            throw new UnsupportedOperationException();
        });

        // when
        sutThrowing.shovingLongBinaryOp().doApplyAsLong((long)100,(long)100);
    }

    @Test
    public void testHandleLongBinaryOp() throws X {

        // given
        LLongBinaryOperatorX<X> sutThrowing = LLongBinaryOperatorX.lX((long a1,long a2) -> {
            throw new UnsupportedOperationException();
        });

        // when
        LLongBinaryOperatorX<X> wrapped = sutThrowing.handleLongBinaryOpX(h -> {
            h.wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED);
        });

        // then
        try {
            wrapped.doApplyAsLong((long)100,(long)100);
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
                .contains("LLongBinaryOperatorX: long doApplyAsLong(long a1,long a2) throws X");
    }


    @Test
    public void isThrowing() {
        assertThat(sut.isThrowing())
            .isTrue();
    }


}
