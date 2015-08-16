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
import eu.lunisolar.magma.func.consumer.*; // NOSONAR
import eu.lunisolar.magma.func.consumer.primitives.*; // NOSONAR
import eu.lunisolar.magma.func.consumer.primitives.bi.*; // NOSONAR
import eu.lunisolar.magma.func.consumer.primitives.tri.*; // NOSONAR
import eu.lunisolar.magma.func.consumer.primitives.obj.*; // NOSONAR
import eu.lunisolar.magma.func.action.*; // NOSONAR
import org.assertj.core.api.Assertions;  //NOSONAR
import org.testng.annotations.*;      //NOSONAR
import java.util.regex.Pattern;          //NOSONAR
import java.text.ParseException;         //NOSONAR
import eu.lunisolar.magma.basics.*; //NOSONAR
import eu.lunisolar.magma.basics.exceptions.*; //NOSONAR
import java.util.concurrent.atomic.AtomicInteger; //NOSONAR
import static org.assertj.core.api.Assertions.*; //NOSONAR

/** The test obviously concentrate on the interface methods the function it self is very simple.  */
public class LShortBinaryOperatorTest<X extends ParseException> {
    private static final String ORIGINAL_MESSAGE = "Original message";
    private static final String EXCEPTION_WAS_WRAPPED = "Exception was wrapped.";
    private static final String NO_EXCEPTION_WERE_THROWN = "No exception were thrown.";

    private short testValue = (short)100;



    private LShortBinaryOperator sut = new LShortBinaryOperator(){
        public  short doApplyAsShort(short s1,short s2)  {
            return testValue;
        }
    };

    private LShortBinaryOperatorX<X> opposite = new LShortBinaryOperatorX(){
        public  short doApplyAsShort(short s1,short s2) throws ParseException {
            return testValue;
        }
    };




    private LShortBinaryOperator sutAlwaysThrowingUnckeck = LShortBinaryOperator.l((short s1,short s2) -> {
            throw new IndexOutOfBoundsException(ORIGINAL_MESSAGE);
    });


    @Test
    public void testTheResult() throws X {
        assertThat(sut.doApplyAsShort((short)100,(short)100))
            .isEqualTo(testValue);
    }

    @Test
    public void testNonNullDoApplyAsShort() throws X {
        assertThat(sut.nonNullDoApplyAsShort((short)100,(short)100))
            .isEqualTo(testValue);
    }

    @Test
    public void testNestingDoApplyAsShortUnckeck() throws X {

        // then
        try {
            sutAlwaysThrowingUnckeck.nestingDoApplyAsShort((short)100,(short)100);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                    .hasNoCause()
                    .hasMessage(ORIGINAL_MESSAGE);
        }
    }

    @Test
    public void testShovingDoApplyAsShortUnckeck() throws X {

        // then
        try {
            sutAlwaysThrowingUnckeck.shovingDoApplyAsShort((short)100,(short)100);
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
            .isEqualTo("LShortBinaryOperator: short doApplyAsShort(short s1,short s2)");
    }

    @Test
    public void testLMethod() throws X {
        assertThat(LShortBinaryOperator.l((short s1,short s2) -> testValue ))
            .isInstanceOf(LShortBinaryOperator.class);
    }

    @Test
    public void testWrapMethod() throws X {
        assertThat(LShortBinaryOperator.wrap(opposite))
            .isInstanceOf(LShortBinaryOperator.class);
    }

    @Test
    public void testWrapMethodDoNotWrapsRuntimeException() throws X {
        // given
        LShortBinaryOperatorX<X> sutThrowing = LShortBinaryOperatorX.lX((short s1,short s2) -> {
            throw new UnsupportedOperationException(ORIGINAL_MESSAGE);
        });

        // when
        LShortBinaryOperator wrapped = LShortBinaryOperator.wrap(sutThrowing);

        // then
        try {
            wrapped.doApplyAsShort((short)100,(short)100);
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
        LShortBinaryOperatorX<ParseException> sutThrowing = LShortBinaryOperatorX.lX((short s1,short s2) -> {
            throw new ParseException(ORIGINAL_MESSAGE, 0);
        });

        // when
        LShortBinaryOperator wrapped = LShortBinaryOperator.wrap(sutThrowing);

        // then
        try {
            wrapped.doApplyAsShort((short)100,(short)100);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(NestedException.class)
                    .hasCauseExactlyInstanceOf(ParseException.class)
                    .hasMessage(ORIGINAL_MESSAGE);
        }
    }


    @Test
    public void testWrapExceptionMethodWrapsTheException() throws X {

        // given
        LShortBinaryOperator sutThrowing = LShortBinaryOperator.l((short s1,short s2) -> {
            throw new UnsupportedOperationException();
        });

        // when
        LShortBinaryOperator wrapped = sutThrowing.handleShortBinaryOp(handler -> handler
            .wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED));

        // then
        try {
            wrapped.doApplyAsShort((short)100,(short)100);
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
        LShortBinaryOperator sutThrowing = LShortBinaryOperator.l((short s1,short s2) -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LShortBinaryOperator wrapped = sutThrowing.handleShortBinaryOp(handler -> handler
                .wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED)
                .throwIf(IndexOutOfBoundsException.class));

        // then
        try {
            wrapped.doApplyAsShort((short)100,(short)100);
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
        LShortBinaryOperator sutThrowing = LShortBinaryOperator.l((short s1,short s2) -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LShortBinaryOperator wrapped = sutThrowing.handleShortBinaryOp(handler -> handler
                .wrapWhen(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED)
                .throwIf(IndexOutOfBoundsException.class));

        // then
        try {
            wrapped.doApplyAsShort((short)100,(short)100);
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
        LShortBinaryOperator sutThrowing = LShortBinaryOperator.l((short s1,short s2) -> {
            throw new UnsupportedOperationException(ORIGINAL_MESSAGE);
        });

        // when
        LShortBinaryOperator wrapped = sutThrowing.handleShortBinaryOp(h -> Function4U.doNothing());

        // then
        try {
            wrapped.doApplyAsShort((short)100,(short)100);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
             .isExactlyInstanceOf(UnsupportedOperationException.class)
             .hasNoCause()
             .hasMessage(ORIGINAL_MESSAGE);
        }
    }


    @Test
    public void testMin() throws X {
        //given
        short valueSmall = (short)100;
        short valueBig = (short)(valueSmall+10);

        //when
        LShortBinaryOperator min = LShortBinaryOperator.min();

        //then
        assertThat(min.doApplyAsShort(valueSmall, valueBig))
                .isEqualTo(valueSmall);

        assertThat(min.doApplyAsShort(valueBig, valueSmall))
                .isEqualTo(valueSmall);
    }

    @Test
    public void testMax() throws X {
        //given
        short valueSmall = (short)100;
        short valueBig = (short)(valueSmall+10);

        //when
        LShortBinaryOperator max = LShortBinaryOperator.max();

        //then
        assertThat(max.doApplyAsShort(valueSmall, valueBig))
                .isEqualTo(valueBig);

        assertThat(max.doApplyAsShort(valueBig, valueSmall))
                .isEqualTo(valueBig);
    }


    // <editor-fold desc="compose (functional)">

    @Test
    public void testshortBinaryOpComposeShort() throws X {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LShortBinaryOperator sutO = (short s1,short s2) -> {
                mainFunctionCalled.set(true);
                assertThat(s1).isEqualTo((short)90);
                assertThat(s2).isEqualTo((short)91);
                return (short)100;
        };

        LShortUnaryOperator before1 = p0 -> {
            assertThat(p0).isEqualTo((short)80);
            beforeCalls.incrementAndGet();
            return (short)90;
        };
        LShortUnaryOperator before2 = p1 -> {
            assertThat(p1).isEqualTo((short)81);
            beforeCalls.incrementAndGet();
            return (short)91;
        };

        //when
        LShortBinaryOperator function = sutO.shortBinaryOpComposeShort(before1,before2);
        function.doApplyAsShort((short)80,(short)81);

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(beforeCalls.get()).isEqualTo(2);
    }


    @Test
    public void testshortBinaryOpCompose() throws X {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LShortBinaryOperator sutO = (short s1,short s2) -> {
                mainFunctionCalled.set(true);
                assertThat(s1).isEqualTo((short)90);
                assertThat(s2).isEqualTo((short)91);
                return (short)100;
        };

        LToShortFunction<Integer > before1 = p0 -> {
            assertThat(p0).isEqualTo(Integer.valueOf(80));
            beforeCalls.incrementAndGet();
            return (short)90;
        };
        LToShortFunction<Integer > before2 = p1 -> {
            assertThat(p1).isEqualTo(Integer.valueOf(81));
            beforeCalls.incrementAndGet();
            return (short)91;
        };

        //when
        LToShortBiFunction<Integer ,Integer > function = sutO.shortBinaryOpCompose(before1,before2);
        function.doApplyAsShort((Integer )Integer.valueOf(80),(Integer )Integer.valueOf(81));

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
        LShortBinaryOperator sutO = (short s1,short s2) -> {
                mainFunctionCalled.set(true);
                assertThat(s1).isEqualTo((short)80);
                assertThat(s2).isEqualTo((short)81);
                return (short)90;
        };

        LShortFunction<Integer > thenFunction = p -> {
                thenFunctionCalled.set(true);
                // 
                assertThat(p).isEqualTo((short)90);
                // V
                return Integer.valueOf(100);
        };

        //when
        LBiShortFunction<Integer > function = sutO.then(thenFunction);
        Integer  finalValue = function.doApply((short)80,(short)81);

        //then - finals
        assertThat(finalValue).isEqualTo(Integer.valueOf(100));
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    // </editor-fold>

    @Test
    public void testNesting() {
        assertThat(sut.nestingShortBinaryOp())
            .isSameAs(sut)
            .isInstanceOf(LShortBinaryOperator.class);
    }

    @Test
    public void testShoving() {
        assertThat(sut.shovingShortBinaryOp())
            .isSameAs(sut)
            .isInstanceOf(LShortBinaryOperator.class);
    }

    @Test
    public void testNestingX() {
        assertThat(sut.nestingShortBinaryOpX())
            .isSameAs(sut)
            .isInstanceOf(LShortBinaryOperatorX.class);
    }

    @Test
    public void testShovingX() {
        assertThat(sut.shovingShortBinaryOpX())
            .isSameAs(sut)
            .isInstanceOf(LShortBinaryOperatorX.class);
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void testShove() {

        // given
        LShortBinaryOperator sutThrowing = LShortBinaryOperator.l((short s1,short s2) -> {
            throw new UnsupportedOperationException();
        });

        // when
        sutThrowing.shovingShortBinaryOp().doApplyAsShort((short)100,(short)100);
    }

    @Test
    public void testHandleShortBinaryOp() throws X {

        // given
        LShortBinaryOperator sutThrowing = LShortBinaryOperator.l((short s1,short s2) -> {
            throw new UnsupportedOperationException();
        });

        // when
        LShortBinaryOperator wrapped = sutThrowing.handleShortBinaryOp(h -> {
            h.wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED);
        });

        // then
        try {
            wrapped.doApplyAsShort((short)100,(short)100);
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
                .contains("LShortBinaryOperator: short doApplyAsShort(short s1,short s2)");
    }


    @Test
    public void isThrowing() {
        assertThat(sut.isThrowing())
            .isFalse();
    }


}
