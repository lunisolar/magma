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

package eu.lunisolar.magma.func.function.conversion;

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
public class LDoubleToByteFunctionXTest<X extends ParseException> {
    private static final String ORIGINAL_MESSAGE = "Original message";
    private static final String EXCEPTION_WAS_WRAPPED = "Exception was wrapped.";
    private static final String NO_EXCEPTION_WERE_THROWN = "No exception were thrown.";

    private byte testValue = (byte)100;



    private LDoubleToByteFunctionX<X> sut = new LDoubleToByteFunctionX(){
        public  byte doApplyAsByte(double d) throws ParseException {
            return testValue;
        }
    };

    private LDoubleToByteFunction opposite = new LDoubleToByteFunction(){
        public  byte doApplyAsByte(double d)  {
            return testValue;
        }
    };



    private LDoubleToByteFunctionX<ParseException> sutAlwaysThrowing = LDoubleToByteFunctionX.lX(d -> {
            throw new ParseException(ORIGINAL_MESSAGE, 0);
    });

    private LDoubleToByteFunctionX<RuntimeException> sutAlwaysThrowingUnckeck = LDoubleToByteFunctionX.lX(d -> {
            throw new IndexOutOfBoundsException(ORIGINAL_MESSAGE);
    });


    @Test
    public void testTheResult() throws X {
        assertThat(sut.doApplyAsByte((double)100))
            .isEqualTo(testValue);
    }

    @Test
    public void testNonNullDoApplyAsByte() throws X {
        assertThat(sut.nonNullDoApplyAsByte((double)100))
            .isEqualTo(testValue);
    }

    @Test
    public void testNestingDoApplyAsByteChecked() throws X {

        // then
        try {
            sutAlwaysThrowing.nestingDoApplyAsByte((double)100);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(NestedException.class)
                    .hasCauseExactlyInstanceOf(ParseException.class)
                    .hasMessage(ORIGINAL_MESSAGE);
        }
    }

    @Test
    public void testNestingDoApplyAsByteUnckeck() throws X {

        // then
        try {
            sutAlwaysThrowingUnckeck.nestingDoApplyAsByte((double)100);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                    .hasNoCause()
                    .hasMessage(ORIGINAL_MESSAGE);
        }
    }

    @Test
    public void testShovingDoApplyAsByteChecked() throws X {

        // then
        try {
            sutAlwaysThrowing.shovingDoApplyAsByte((double)100);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(ParseException.class)
                    .hasNoCause()
                    .hasMessage(ORIGINAL_MESSAGE);
        }
    }

    @Test
    public void testShovingDoApplyAsByteUnckeck() throws X {

        // then
        try {
            sutAlwaysThrowingUnckeck.shovingDoApplyAsByte((double)100);
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
            .isEqualTo("LDoubleToByteFunctionX: byte doApplyAsByte(double d) throws X");
    }

    @Test
    public void testLXMethod() throws X {
        assertThat(LDoubleToByteFunctionX.lX(d -> testValue ))
            .isInstanceOf(LDoubleToByteFunctionX.class);
    }

    @Test
    public void testWrapXMethod() throws X {
        assertThat(LDoubleToByteFunctionX.wrapX(opposite))
            .isInstanceOf(LDoubleToByteFunctionX.class);
    }


    @Test
    public void testWrapExceptionMethodWrapsTheException() throws X {

        // given
        LDoubleToByteFunctionX<X> sutThrowing = LDoubleToByteFunctionX.lX(d -> {
            throw new UnsupportedOperationException();
        });

        // when
        LDoubleToByteFunctionX<X> wrapped = sutThrowing.handleDoubleToByteFuncX(handler -> handler
            .wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED));

        // then
        try {
            wrapped.doApplyAsByte((double)100);
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
        LDoubleToByteFunctionX<X> sutThrowing = LDoubleToByteFunctionX.lX(d -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LDoubleToByteFunctionX<X> wrapped = sutThrowing.handleDoubleToByteFuncX(handler -> handler
                .wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED)
                .throwIf(IndexOutOfBoundsException.class));

        // then
        try {
            wrapped.doApplyAsByte((double)100);
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
        LDoubleToByteFunctionX<X> sutThrowing = LDoubleToByteFunctionX.lX(d -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LDoubleToByteFunctionX<X> wrapped = sutThrowing.handleDoubleToByteFuncX(handler -> handler
                .wrapWhen(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED)
                .throwIf(IndexOutOfBoundsException.class));

        // then
        try {
            wrapped.doApplyAsByte((double)100);
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
        LDoubleToByteFunctionX<X> sutThrowing = LDoubleToByteFunctionX.lX(d -> {
            throw (X) new ParseException(ORIGINAL_MESSAGE, 0);
        });

        // when
        LDoubleToByteFunctionX<X> wrapped = sutThrowing.handleDoubleToByteFuncX(h -> Function4U.doNothing());

        // then
        try {
            wrapped.doApplyAsByte((double)100);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(NestedException.class)
                    .hasCauseExactlyInstanceOf(ParseException.class)
                    .hasMessage(ORIGINAL_MESSAGE);
        }
    }



    // <editor-fold desc="compose (functional)">

    @Test
    public void testdoubleToByteFuncComposeDouble() throws X {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LDoubleToByteFunctionX<X> sutO = d -> {
                mainFunctionCalled.set(true);
                assertThat(d).isEqualTo((double)90);
                return (byte)100;
        };

        LDoubleUnaryOperatorX<X> before1 = p0 -> {
            assertThat(p0).isEqualTo((double)80);
            beforeCalls.incrementAndGet();
            return (double)90;
        };

        //when
        LDoubleToByteFunctionX<X> function = sutO.doubleToByteFuncComposeDouble(before1);
        function.doApplyAsByte((double)80);

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(beforeCalls.get()).isEqualTo(1);
    }


    @Test
    public void testdoubleToByteFuncCompose() throws X {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LDoubleToByteFunctionX<X> sutO = d -> {
                mainFunctionCalled.set(true);
                assertThat(d).isEqualTo((double)90);
                return (byte)100;
        };

        LToDoubleFunctionX<Integer ,X> before1 = p0 -> {
            assertThat(p0).isEqualTo(Integer.valueOf(80));
            beforeCalls.incrementAndGet();
            return (double)90;
        };

        //when
        LToByteFunctionX<Integer ,X> function = sutO.doubleToByteFuncCompose(before1);
        function.doApplyAsByte((Integer )Integer.valueOf(80));

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(beforeCalls.get()).isEqualTo(1);
    }

    // </editor-fold>


    // <editor-fold desc="then (functional)">

    @Test
    public void testThen0() throws X  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);


        //given (+ some assertions)
        LDoubleToByteFunctionX<X> sutO = d -> {
                mainFunctionCalled.set(true);
                assertThat(d).isEqualTo((double)80);
                return (byte)90;
        };

        LByteFunctionX<Integer ,X> thenFunction = p -> {
                thenFunctionCalled.set(true);
                // 
                assertThat(p).isEqualTo((byte)90);
                // V
                return Integer.valueOf(100);
        };

        //when
        LDoubleFunctionX<Integer ,X> function = sutO.then(thenFunction);
        Integer  finalValue = function.doApply((double)80);

        //then - finals
        assertThat(finalValue).isEqualTo(Integer.valueOf(100));
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    @Test
    public void testThen1ToByte() throws X  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);


        //given (+ some assertions)
        LDoubleToByteFunctionX<X> sutO = d -> {
                mainFunctionCalled.set(true);
                assertThat(d).isEqualTo((double)80);
                return (byte)90;
        };

        LByteUnaryOperatorX<X> thenFunction = p -> {
                thenFunctionCalled.set(true);
                // 
                assertThat(p).isEqualTo((byte)90);
                // byte
                return (byte)100;
        };

        //when
        LDoubleToByteFunctionX<X> function = sutO.thenToByte(thenFunction);
        byte finalValue = function.doApplyAsByte((double)80);

        //then - finals
        assertThat(finalValue).isEqualTo((byte)100);
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    @Test
    public void testThen2ToShort() throws X  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);


        //given (+ some assertions)
        LDoubleToByteFunctionX<X> sutO = d -> {
                mainFunctionCalled.set(true);
                assertThat(d).isEqualTo((double)80);
                return (byte)90;
        };

        LByteToShortFunctionX<X> thenFunction = p -> {
                thenFunctionCalled.set(true);
                // 
                assertThat(p).isEqualTo((byte)90);
                // short
                return (short)100;
        };

        //when
        LDoubleToShortFunctionX<X> function = sutO.thenToShort(thenFunction);
        short finalValue = function.doApplyAsShort((double)80);

        //then - finals
        assertThat(finalValue).isEqualTo((short)100);
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    @Test
    public void testThen3ToInt() throws X  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);


        //given (+ some assertions)
        LDoubleToByteFunctionX<X> sutO = d -> {
                mainFunctionCalled.set(true);
                assertThat(d).isEqualTo((double)80);
                return (byte)90;
        };

        LByteToIntFunctionX<X> thenFunction = p -> {
                thenFunctionCalled.set(true);
                // 
                assertThat(p).isEqualTo((byte)90);
                // int
                return (int)100;
        };

        //when
        LDoubleToIntFunctionX<X> function = sutO.thenToInt(thenFunction);
        int finalValue = function.doApplyAsInt((double)80);

        //then - finals
        assertThat(finalValue).isEqualTo((int)100);
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    @Test
    public void testThen4ToLong() throws X  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);


        //given (+ some assertions)
        LDoubleToByteFunctionX<X> sutO = d -> {
                mainFunctionCalled.set(true);
                assertThat(d).isEqualTo((double)80);
                return (byte)90;
        };

        LByteToLongFunctionX<X> thenFunction = p -> {
                thenFunctionCalled.set(true);
                // 
                assertThat(p).isEqualTo((byte)90);
                // long
                return (long)100;
        };

        //when
        LDoubleToLongFunctionX<X> function = sutO.thenToLong(thenFunction);
        long finalValue = function.doApplyAsLong((double)80);

        //then - finals
        assertThat(finalValue).isEqualTo((long)100);
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    @Test
    public void testThen5ToFloat() throws X  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);


        //given (+ some assertions)
        LDoubleToByteFunctionX<X> sutO = d -> {
                mainFunctionCalled.set(true);
                assertThat(d).isEqualTo((double)80);
                return (byte)90;
        };

        LByteToFloatFunctionX<X> thenFunction = p -> {
                thenFunctionCalled.set(true);
                // 
                assertThat(p).isEqualTo((byte)90);
                // float
                return (float)100;
        };

        //when
        LDoubleToFloatFunctionX<X> function = sutO.thenToFloat(thenFunction);
        float finalValue = function.doApplyAsFloat((double)80);

        //then - finals
        assertThat(finalValue).isEqualTo((float)100);
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    @Test
    public void testThen6ToDouble() throws X  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);


        //given (+ some assertions)
        LDoubleToByteFunctionX<X> sutO = d -> {
                mainFunctionCalled.set(true);
                assertThat(d).isEqualTo((double)80);
                return (byte)90;
        };

        LByteToDoubleFunctionX<X> thenFunction = p -> {
                thenFunctionCalled.set(true);
                // 
                assertThat(p).isEqualTo((byte)90);
                // double
                return (double)100;
        };

        //when
        LDoubleUnaryOperatorX<X> function = sutO.thenToDouble(thenFunction);
        double finalValue = function.doApplyAsDouble((double)80);

        //then - finals
        assertThat(finalValue).isEqualTo((double)100);
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    @Test
    public void testThen7ToChar() throws X  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);


        //given (+ some assertions)
        LDoubleToByteFunctionX<X> sutO = d -> {
                mainFunctionCalled.set(true);
                assertThat(d).isEqualTo((double)80);
                return (byte)90;
        };

        LByteToCharFunctionX<X> thenFunction = p -> {
                thenFunctionCalled.set(true);
                // 
                assertThat(p).isEqualTo((byte)90);
                // char
                return (char)100;
        };

        //when
        LDoubleToCharFunctionX<X> function = sutO.thenToChar(thenFunction);
        char finalValue = function.doApplyAsChar((double)80);

        //then - finals
        assertThat(finalValue).isEqualTo((char)100);
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    @Test
    public void testThen8ToBoolean() throws X  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);


        //given (+ some assertions)
        LDoubleToByteFunctionX<X> sutO = d -> {
                mainFunctionCalled.set(true);
                assertThat(d).isEqualTo((double)80);
                return (byte)90;
        };

        LBytePredicateX<X> thenFunction = p -> {
                thenFunctionCalled.set(true);
                // 
                assertThat(p).isEqualTo((byte)90);
                // boolean
                return true;
        };

        //when
        LDoublePredicateX<X> function = sutO.thenToBoolean(thenFunction);
        boolean finalValue = function.doTest((double)80);

        //then - finals
        assertThat(finalValue).isEqualTo(true);
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    // </editor-fold>

    @Test
    public void testNesting() {
        assertThat(sut.nestingDoubleToByteFunc())
            .isInstanceOf(LDoubleToByteFunction.class);
    }

    @Test
    public void testShoving() {
        assertThat(sut.shovingDoubleToByteFunc())
            .isInstanceOf(LDoubleToByteFunction.class);
    }

    @Test
    public void testNestingX() {
        assertThat(sut.nestingDoubleToByteFuncX())
            .isInstanceOf(LDoubleToByteFunctionX.class);
    }

    @Test
    public void testShovingX() {
        assertThat(sut.shovingDoubleToByteFuncX())
            .isInstanceOf(LDoubleToByteFunctionX.class);
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void testShove() {

        // given
        LDoubleToByteFunctionX<X> sutThrowing = LDoubleToByteFunctionX.lX(d -> {
            throw new UnsupportedOperationException();
        });

        // when
        sutThrowing.shovingDoubleToByteFunc().doApplyAsByte((double)100);
    }

    @Test
    public void testHandleDoubleToByteFunc() throws X {

        // given
        LDoubleToByteFunctionX<X> sutThrowing = LDoubleToByteFunctionX.lX(d -> {
            throw new UnsupportedOperationException();
        });

        // when
        LDoubleToByteFunctionX<X> wrapped = sutThrowing.handleDoubleToByteFuncX(h -> {
            h.wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED);
        });

        // then
        try {
            wrapped.doApplyAsByte((double)100);
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
                .contains("LDoubleToByteFunctionX: byte doApplyAsByte(double d) throws X");
    }


    @Test
    public void isThrowing() {
        assertThat(sut.isThrowing())
            .isTrue();
    }


}


