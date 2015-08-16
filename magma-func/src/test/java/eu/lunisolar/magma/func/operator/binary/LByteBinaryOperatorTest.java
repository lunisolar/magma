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
public class LByteBinaryOperatorTest<X extends ParseException> {
    private static final String ORIGINAL_MESSAGE = "Original message";
    private static final String EXCEPTION_WAS_WRAPPED = "Exception was wrapped.";
    private static final String NO_EXCEPTION_WERE_THROWN = "No exception were thrown.";

    private byte testValue = (byte)100;



    private LByteBinaryOperator sut = new LByteBinaryOperator(){
        public  byte doApplyAsByte(byte b1,byte b2)  {
            return testValue;
        }
    };

    private LByteBinaryOperatorX<X> opposite = new LByteBinaryOperatorX(){
        public  byte doApplyAsByte(byte b1,byte b2) throws ParseException {
            return testValue;
        }
    };




    private LByteBinaryOperator sutAlwaysThrowingUnckeck = LByteBinaryOperator.l((byte b1,byte b2) -> {
            throw new IndexOutOfBoundsException(ORIGINAL_MESSAGE);
    });


    @Test
    public void testTheResult() throws X {
        assertThat(sut.doApplyAsByte((byte)100,(byte)100))
            .isEqualTo(testValue);
    }

    @Test
    public void testNonNullDoApplyAsByte() throws X {
        assertThat(sut.nonNullDoApplyAsByte((byte)100,(byte)100))
            .isEqualTo(testValue);
    }

    @Test
    public void testNestingDoApplyAsByteUnckeck() throws X {

        // then
        try {
            sutAlwaysThrowingUnckeck.nestingDoApplyAsByte((byte)100,(byte)100);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                    .hasNoCause()
                    .hasMessage(ORIGINAL_MESSAGE);
        }
    }

    @Test
    public void testShovingDoApplyAsByteUnckeck() throws X {

        // then
        try {
            sutAlwaysThrowingUnckeck.shovingDoApplyAsByte((byte)100,(byte)100);
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
            .isEqualTo("LByteBinaryOperator: byte doApplyAsByte(byte b1,byte b2)");
    }

    @Test
    public void testLMethod() throws X {
        assertThat(LByteBinaryOperator.l((byte b1,byte b2) -> testValue ))
            .isInstanceOf(LByteBinaryOperator.class);
    }

    @Test
    public void testWrapMethod() throws X {
        assertThat(LByteBinaryOperator.wrap(opposite))
            .isInstanceOf(LByteBinaryOperator.class);
    }

    @Test
    public void testWrapMethodDoNotWrapsRuntimeException() throws X {
        // given
        LByteBinaryOperatorX<X> sutThrowing = LByteBinaryOperatorX.lX((byte b1,byte b2) -> {
            throw new UnsupportedOperationException(ORIGINAL_MESSAGE);
        });

        // when
        LByteBinaryOperator wrapped = LByteBinaryOperator.wrap(sutThrowing);

        // then
        try {
            wrapped.doApplyAsByte((byte)100,(byte)100);
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
        LByteBinaryOperatorX<ParseException> sutThrowing = LByteBinaryOperatorX.lX((byte b1,byte b2) -> {
            throw new ParseException(ORIGINAL_MESSAGE, 0);
        });

        // when
        LByteBinaryOperator wrapped = LByteBinaryOperator.wrap(sutThrowing);

        // then
        try {
            wrapped.doApplyAsByte((byte)100,(byte)100);
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
        LByteBinaryOperator sutThrowing = LByteBinaryOperator.l((byte b1,byte b2) -> {
            throw new UnsupportedOperationException();
        });

        // when
        LByteBinaryOperator wrapped = sutThrowing.handleByteBinaryOp(handler -> handler
            .wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED));

        // then
        try {
            wrapped.doApplyAsByte((byte)100,(byte)100);
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
        LByteBinaryOperator sutThrowing = LByteBinaryOperator.l((byte b1,byte b2) -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LByteBinaryOperator wrapped = sutThrowing.handleByteBinaryOp(handler -> handler
                .wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED)
                .throwIf(IndexOutOfBoundsException.class));

        // then
        try {
            wrapped.doApplyAsByte((byte)100,(byte)100);
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
        LByteBinaryOperator sutThrowing = LByteBinaryOperator.l((byte b1,byte b2) -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LByteBinaryOperator wrapped = sutThrowing.handleByteBinaryOp(handler -> handler
                .wrapWhen(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED)
                .throwIf(IndexOutOfBoundsException.class));

        // then
        try {
            wrapped.doApplyAsByte((byte)100,(byte)100);
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
        LByteBinaryOperator sutThrowing = LByteBinaryOperator.l((byte b1,byte b2) -> {
            throw new UnsupportedOperationException(ORIGINAL_MESSAGE);
        });

        // when
        LByteBinaryOperator wrapped = sutThrowing.handleByteBinaryOp(h -> Function4U.doNothing());

        // then
        try {
            wrapped.doApplyAsByte((byte)100,(byte)100);
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
        byte valueSmall = (byte)100;
        byte valueBig = (byte)(valueSmall+10);

        //when
        LByteBinaryOperator min = LByteBinaryOperator.min();

        //then
        assertThat(min.doApplyAsByte(valueSmall, valueBig))
                .isEqualTo(valueSmall);

        assertThat(min.doApplyAsByte(valueBig, valueSmall))
                .isEqualTo(valueSmall);
    }

    @Test
    public void testMax() throws X {
        //given
        byte valueSmall = (byte)100;
        byte valueBig = (byte)(valueSmall+10);

        //when
        LByteBinaryOperator max = LByteBinaryOperator.max();

        //then
        assertThat(max.doApplyAsByte(valueSmall, valueBig))
                .isEqualTo(valueBig);

        assertThat(max.doApplyAsByte(valueBig, valueSmall))
                .isEqualTo(valueBig);
    }


    // <editor-fold desc="compose (functional)">

    @Test
    public void testbyteBinaryOpComposeByte() throws X {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LByteBinaryOperator sutO = (byte b1,byte b2) -> {
                mainFunctionCalled.set(true);
                assertThat(b1).isEqualTo((byte)90);
                assertThat(b2).isEqualTo((byte)91);
                return (byte)100;
        };

        LByteUnaryOperator before1 = p0 -> {
            assertThat(p0).isEqualTo((byte)80);
            beforeCalls.incrementAndGet();
            return (byte)90;
        };
        LByteUnaryOperator before2 = p1 -> {
            assertThat(p1).isEqualTo((byte)81);
            beforeCalls.incrementAndGet();
            return (byte)91;
        };

        //when
        LByteBinaryOperator function = sutO.byteBinaryOpComposeByte(before1,before2);
        function.doApplyAsByte((byte)80,(byte)81);

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(beforeCalls.get()).isEqualTo(2);
    }


    @Test
    public void testbyteBinaryOpCompose() throws X {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LByteBinaryOperator sutO = (byte b1,byte b2) -> {
                mainFunctionCalled.set(true);
                assertThat(b1).isEqualTo((byte)90);
                assertThat(b2).isEqualTo((byte)91);
                return (byte)100;
        };

        LToByteFunction<Integer > before1 = p0 -> {
            assertThat(p0).isEqualTo(Integer.valueOf(80));
            beforeCalls.incrementAndGet();
            return (byte)90;
        };
        LToByteFunction<Integer > before2 = p1 -> {
            assertThat(p1).isEqualTo(Integer.valueOf(81));
            beforeCalls.incrementAndGet();
            return (byte)91;
        };

        //when
        LToByteBiFunction<Integer ,Integer > function = sutO.byteBinaryOpCompose(before1,before2);
        function.doApplyAsByte((Integer )Integer.valueOf(80),(Integer )Integer.valueOf(81));

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
        LByteBinaryOperator sutO = (byte b1,byte b2) -> {
                mainFunctionCalled.set(true);
                assertThat(b1).isEqualTo((byte)80);
                assertThat(b2).isEqualTo((byte)81);
                return (byte)90;
        };

        LByteFunction<Integer > thenFunction = p -> {
                thenFunctionCalled.set(true);
                // 
                assertThat(p).isEqualTo((byte)90);
                // V
                return Integer.valueOf(100);
        };

        //when
        LBiByteFunction<Integer > function = sutO.then(thenFunction);
        Integer  finalValue = function.doApply((byte)80,(byte)81);

        //then - finals
        assertThat(finalValue).isEqualTo(Integer.valueOf(100));
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    // </editor-fold>

    @Test
    public void testNesting() {
        assertThat(sut.nestingByteBinaryOp())
            .isSameAs(sut)
            .isInstanceOf(LByteBinaryOperator.class);
    }

    @Test
    public void testShoving() {
        assertThat(sut.shovingByteBinaryOp())
            .isSameAs(sut)
            .isInstanceOf(LByteBinaryOperator.class);
    }

    @Test
    public void testNestingX() {
        assertThat(sut.nestingByteBinaryOpX())
            .isSameAs(sut)
            .isInstanceOf(LByteBinaryOperatorX.class);
    }

    @Test
    public void testShovingX() {
        assertThat(sut.shovingByteBinaryOpX())
            .isSameAs(sut)
            .isInstanceOf(LByteBinaryOperatorX.class);
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void testShove() {

        // given
        LByteBinaryOperator sutThrowing = LByteBinaryOperator.l((byte b1,byte b2) -> {
            throw new UnsupportedOperationException();
        });

        // when
        sutThrowing.shovingByteBinaryOp().doApplyAsByte((byte)100,(byte)100);
    }

    @Test
    public void testHandleByteBinaryOp() throws X {

        // given
        LByteBinaryOperator sutThrowing = LByteBinaryOperator.l((byte b1,byte b2) -> {
            throw new UnsupportedOperationException();
        });

        // when
        LByteBinaryOperator wrapped = sutThrowing.handleByteBinaryOp(h -> {
            h.wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED);
        });

        // then
        try {
            wrapped.doApplyAsByte((byte)100,(byte)100);
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
                .contains("LByteBinaryOperator: byte doApplyAsByte(byte b1,byte b2)");
    }


    @Test
    public void isThrowing() {
        assertThat(sut.isThrowing())
            .isFalse();
    }


}


