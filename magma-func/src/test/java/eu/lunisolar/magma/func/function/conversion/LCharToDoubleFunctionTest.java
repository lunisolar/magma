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
public class LCharToDoubleFunctionTest<X extends ParseException> {
    private static final String ORIGINAL_MESSAGE = "Original message";
    private static final String EXCEPTION_WAS_WRAPPED = "Exception was wrapped.";
    private static final String NO_EXCEPTION_WERE_THROWN = "No exception were thrown.";

    private double testValue = (double)100;



    private LCharToDoubleFunction sut = new LCharToDoubleFunction(){
        public  double doApplyAsDouble(char c)  {
            return testValue;
        }
    };

    private LCharToDoubleFunctionX<X> opposite = new LCharToDoubleFunctionX(){
        public  double doApplyAsDouble(char c) throws ParseException {
            return testValue;
        }
    };




    private LCharToDoubleFunction sutAlwaysThrowingUnckeck = LCharToDoubleFunction.l(c -> {
            throw new IndexOutOfBoundsException(ORIGINAL_MESSAGE);
    });


    @Test
    public void testTheResult() throws X {
        assertThat(sut.doApplyAsDouble((char)100))
            .isEqualTo(testValue);
    }

    @Test
    public void testNonNullDoApplyAsDouble() throws X {
        assertThat(sut.nonNullDoApplyAsDouble((char)100))
            .isEqualTo(testValue);
    }

    @Test
    public void testNestingDoApplyAsDoubleUnckeck() throws X {

        // then
        try {
            sutAlwaysThrowingUnckeck.nestingDoApplyAsDouble((char)100);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                    .hasNoCause()
                    .hasMessage(ORIGINAL_MESSAGE);
        }
    }

    @Test
    public void testShovingDoApplyAsDoubleUnckeck() throws X {

        // then
        try {
            sutAlwaysThrowingUnckeck.shovingDoApplyAsDouble((char)100);
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
            .isEqualTo("LCharToDoubleFunction: double doApplyAsDouble(char c)");
    }

    @Test
    public void testLMethod() throws X {
        assertThat(LCharToDoubleFunction.l(c -> testValue ))
            .isInstanceOf(LCharToDoubleFunction.class);
    }

    @Test
    public void testWrapMethod() throws X {
        assertThat(LCharToDoubleFunction.wrap(opposite))
            .isInstanceOf(LCharToDoubleFunction.class);
    }

    @Test
    public void testWrapMethodDoNotWrapsRuntimeException() throws X {
        // given
        LCharToDoubleFunctionX<X> sutThrowing = LCharToDoubleFunctionX.lX(c -> {
            throw new UnsupportedOperationException(ORIGINAL_MESSAGE);
        });

        // when
        LCharToDoubleFunction wrapped = LCharToDoubleFunction.wrap(sutThrowing);

        // then
        try {
            wrapped.doApplyAsDouble((char)100);
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
        LCharToDoubleFunctionX<ParseException> sutThrowing = LCharToDoubleFunctionX.lX(c -> {
            throw new ParseException(ORIGINAL_MESSAGE, 0);
        });

        // when
        LCharToDoubleFunction wrapped = LCharToDoubleFunction.wrap(sutThrowing);

        // then
        try {
            wrapped.doApplyAsDouble((char)100);
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
        LCharToDoubleFunction sutThrowing = LCharToDoubleFunction.l(c -> {
            throw new UnsupportedOperationException();
        });

        // when
        LCharToDoubleFunction wrapped = sutThrowing.handleCharToDoubleFunc(handler -> handler
            .wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED));

        // then
        try {
            wrapped.doApplyAsDouble((char)100);
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
        LCharToDoubleFunction sutThrowing = LCharToDoubleFunction.l(c -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LCharToDoubleFunction wrapped = sutThrowing.handleCharToDoubleFunc(handler -> handler
                .wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED)
                .throwIf(IndexOutOfBoundsException.class));

        // then
        try {
            wrapped.doApplyAsDouble((char)100);
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
        LCharToDoubleFunction sutThrowing = LCharToDoubleFunction.l(c -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LCharToDoubleFunction wrapped = sutThrowing.handleCharToDoubleFunc(handler -> handler
                .wrapWhen(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED)
                .throwIf(IndexOutOfBoundsException.class));

        // then
        try {
            wrapped.doApplyAsDouble((char)100);
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
        LCharToDoubleFunction sutThrowing = LCharToDoubleFunction.l(c -> {
            throw new UnsupportedOperationException(ORIGINAL_MESSAGE);
        });

        // when
        LCharToDoubleFunction wrapped = sutThrowing.handleCharToDoubleFunc(h -> Function4U.doNothing());

        // then
        try {
            wrapped.doApplyAsDouble((char)100);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
             .isExactlyInstanceOf(UnsupportedOperationException.class)
             .hasNoCause()
             .hasMessage(ORIGINAL_MESSAGE);
        }
    }



    // <editor-fold desc="compose (functional)">

    @Test
    public void testcharToDoubleFuncComposeChar() throws X {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LCharToDoubleFunction sutO = c -> {
                mainFunctionCalled.set(true);
                assertThat(c).isEqualTo((char)90);
                return (double)100;
        };

        LCharUnaryOperator before1 = p0 -> {
            assertThat(p0).isEqualTo((char)80);
            beforeCalls.incrementAndGet();
            return (char)90;
        };

        //when
        LCharToDoubleFunction function = sutO.charToDoubleFuncComposeChar(before1);
        function.doApplyAsDouble((char)80);

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(beforeCalls.get()).isEqualTo(1);
    }


    @Test
    public void testcharToDoubleFuncCompose() throws X {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LCharToDoubleFunction sutO = c -> {
                mainFunctionCalled.set(true);
                assertThat(c).isEqualTo((char)90);
                return (double)100;
        };

        LToCharFunction<Integer > before1 = p0 -> {
            assertThat(p0).isEqualTo(Integer.valueOf(80));
            beforeCalls.incrementAndGet();
            return (char)90;
        };

        //when
        LToDoubleFunction<Integer > function = sutO.charToDoubleFuncCompose(before1);
        function.doApplyAsDouble((Integer )Integer.valueOf(80));

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
        LCharToDoubleFunction sutO = c -> {
                mainFunctionCalled.set(true);
                assertThat(c).isEqualTo((char)80);
                return (double)90;
        };

        LDoubleFunction<Integer > thenFunction = p -> {
                thenFunctionCalled.set(true);
                // 
                assertThat(p).isEqualTo((double)90);
                // V
                return Integer.valueOf(100);
        };

        //when
        LCharFunction<Integer > function = sutO.then(thenFunction);
        Integer  finalValue = function.doApply((char)80);

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
        LCharToDoubleFunction sutO = c -> {
                mainFunctionCalled.set(true);
                assertThat(c).isEqualTo((char)80);
                return (double)90;
        };

        LDoubleToByteFunction thenFunction = p -> {
                thenFunctionCalled.set(true);
                // 
                assertThat(p).isEqualTo((double)90);
                // byte
                return (byte)100;
        };

        //when
        LCharToByteFunction function = sutO.thenToByte(thenFunction);
        byte finalValue = function.doApplyAsByte((char)80);

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
        LCharToDoubleFunction sutO = c -> {
                mainFunctionCalled.set(true);
                assertThat(c).isEqualTo((char)80);
                return (double)90;
        };

        LDoubleToShortFunction thenFunction = p -> {
                thenFunctionCalled.set(true);
                // 
                assertThat(p).isEqualTo((double)90);
                // short
                return (short)100;
        };

        //when
        LCharToShortFunction function = sutO.thenToShort(thenFunction);
        short finalValue = function.doApplyAsShort((char)80);

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
        LCharToDoubleFunction sutO = c -> {
                mainFunctionCalled.set(true);
                assertThat(c).isEqualTo((char)80);
                return (double)90;
        };

        LDoubleToIntFunction thenFunction = p -> {
                thenFunctionCalled.set(true);
                // 
                assertThat(p).isEqualTo((double)90);
                // int
                return (int)100;
        };

        //when
        LCharToIntFunction function = sutO.thenToInt(thenFunction);
        int finalValue = function.doApplyAsInt((char)80);

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
        LCharToDoubleFunction sutO = c -> {
                mainFunctionCalled.set(true);
                assertThat(c).isEqualTo((char)80);
                return (double)90;
        };

        LDoubleToLongFunction thenFunction = p -> {
                thenFunctionCalled.set(true);
                // 
                assertThat(p).isEqualTo((double)90);
                // long
                return (long)100;
        };

        //when
        LCharToLongFunction function = sutO.thenToLong(thenFunction);
        long finalValue = function.doApplyAsLong((char)80);

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
        LCharToDoubleFunction sutO = c -> {
                mainFunctionCalled.set(true);
                assertThat(c).isEqualTo((char)80);
                return (double)90;
        };

        LDoubleToFloatFunction thenFunction = p -> {
                thenFunctionCalled.set(true);
                // 
                assertThat(p).isEqualTo((double)90);
                // float
                return (float)100;
        };

        //when
        LCharToFloatFunction function = sutO.thenToFloat(thenFunction);
        float finalValue = function.doApplyAsFloat((char)80);

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
        LCharToDoubleFunction sutO = c -> {
                mainFunctionCalled.set(true);
                assertThat(c).isEqualTo((char)80);
                return (double)90;
        };

        LDoubleUnaryOperator thenFunction = p -> {
                thenFunctionCalled.set(true);
                // 
                assertThat(p).isEqualTo((double)90);
                // double
                return (double)100;
        };

        //when
        LCharToDoubleFunction function = sutO.thenToDouble(thenFunction);
        double finalValue = function.doApplyAsDouble((char)80);

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
        LCharToDoubleFunction sutO = c -> {
                mainFunctionCalled.set(true);
                assertThat(c).isEqualTo((char)80);
                return (double)90;
        };

        LDoubleToCharFunction thenFunction = p -> {
                thenFunctionCalled.set(true);
                // 
                assertThat(p).isEqualTo((double)90);
                // char
                return (char)100;
        };

        //when
        LCharUnaryOperator function = sutO.thenToChar(thenFunction);
        char finalValue = function.doApplyAsChar((char)80);

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
        LCharToDoubleFunction sutO = c -> {
                mainFunctionCalled.set(true);
                assertThat(c).isEqualTo((char)80);
                return (double)90;
        };

        LDoublePredicate thenFunction = p -> {
                thenFunctionCalled.set(true);
                // 
                assertThat(p).isEqualTo((double)90);
                // boolean
                return true;
        };

        //when
        LCharPredicate function = sutO.thenToBoolean(thenFunction);
        boolean finalValue = function.doTest((char)80);

        //then - finals
        assertThat(finalValue).isEqualTo(true);
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    // </editor-fold>

    @Test
    public void testNesting() {
        assertThat(sut.nestingCharToDoubleFunc())
            .isSameAs(sut)
            .isInstanceOf(LCharToDoubleFunction.class);
    }

    @Test
    public void testShoving() {
        assertThat(sut.shovingCharToDoubleFunc())
            .isSameAs(sut)
            .isInstanceOf(LCharToDoubleFunction.class);
    }

    @Test
    public void testNestingX() {
        assertThat(sut.nestingCharToDoubleFuncX())
            .isSameAs(sut)
            .isInstanceOf(LCharToDoubleFunctionX.class);
    }

    @Test
    public void testShovingX() {
        assertThat(sut.shovingCharToDoubleFuncX())
            .isSameAs(sut)
            .isInstanceOf(LCharToDoubleFunctionX.class);
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void testShove() {

        // given
        LCharToDoubleFunction sutThrowing = LCharToDoubleFunction.l(c -> {
            throw new UnsupportedOperationException();
        });

        // when
        sutThrowing.shovingCharToDoubleFunc().doApplyAsDouble((char)100);
    }

    @Test
    public void testHandleCharToDoubleFunc() throws X {

        // given
        LCharToDoubleFunction sutThrowing = LCharToDoubleFunction.l(c -> {
            throw new UnsupportedOperationException();
        });

        // when
        LCharToDoubleFunction wrapped = sutThrowing.handleCharToDoubleFunc(h -> {
            h.wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED);
        });

        // then
        try {
            wrapped.doApplyAsDouble((char)100);
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
                .contains("LCharToDoubleFunction: double doApplyAsDouble(char c)");
    }


    @Test
    public void isThrowing() {
        assertThat(sut.isThrowing())
            .isFalse();
    }


}


