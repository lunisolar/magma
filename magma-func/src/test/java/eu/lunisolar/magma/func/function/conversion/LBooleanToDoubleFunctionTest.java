/*
 * (C) Copyright 2015 Lunisolar (http://lunisolar.eu/).
 *
 * This file is part of "lunisolar-magma".
 *
 * "lunisolar-magma" is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * "lunisolar-magma" is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with "lunisolar-magma".  If not, see <http://www.gnu.org/licenses/>.
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
import java.util.concurrent.atomic.AtomicInteger; //NOSONAR
import static org.assertj.core.api.Assertions.*; //NOSONAR

/** The test obviously concentrate on the interface methods the function it self is very simple.  */
public class LBooleanToDoubleFunctionTest<X extends ParseException> {
    private static final String ORIGINAL_MESSAGE = "Original message";
    private static final String EXCEPTION_WAS_WRAPPED = "Exception was wrapped.";
    private static final String NO_EXCEPTION_WERE_THROWN = "No exception were thrown.";

    private double testValue = (double)100;



    private LBooleanToDoubleFunction sut = new LBooleanToDoubleFunction(){
        public  double applyAsDouble(boolean b)  {
            return testValue;
        }
    };

    private LBooleanToDoubleFunctionX<X> opposite = new LBooleanToDoubleFunctionX(){
        public  double applyAsDouble(boolean b) throws ParseException {
            return testValue;
        }
    };



    @Test
    public void testTheResult() throws ParseException {
        assertThat(sut.applyAsDouble(true))
            .isEqualTo(testValue);
    }

    @Test
    public void testNonNullShouldNotModifyValue() throws ParseException {
        assertThat(sut.nonNull(true))
            .isEqualTo(testValue);
    }


    @Test
    public void testFunctionalInterfaceDescription() throws ParseException {
        assertThat(sut.functionalInterfaceDescription())
            .isEqualTo("LBooleanToDoubleFunction: double applyAsDouble(boolean b)");
    }

    @Test
    public void testLMethod() throws ParseException {
        assertThat(LBooleanToDoubleFunction.l((boolean b) -> testValue ))
            .isInstanceOf(LBooleanToDoubleFunction.class);
    }

    @Test
    public void testWrapMethod() throws ParseException {
        assertThat(LBooleanToDoubleFunction.wrap(opposite))
            .isInstanceOf(LBooleanToDoubleFunction.class);
    }

    @Test
    public void testWrapMethodDoNotWrapsRuntimeException() throws ParseException {
        // given
        LBooleanToDoubleFunctionX<X> sutThrowing = LBooleanToDoubleFunctionX.lX((boolean b) -> {
            throw new UnsupportedOperationException(ORIGINAL_MESSAGE);
        });

        // when
        LBooleanToDoubleFunction wrapped = LBooleanToDoubleFunction.wrap(sutThrowing);

        // then
        try {
            wrapped.applyAsDouble(true);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(UnsupportedOperationException.class)
                    .hasNoCause()
                    .hasMessage(ORIGINAL_MESSAGE);
        }
    }

    @Test
    public void testWrapMethodWrapsCheckedException() throws ParseException {
        // given
        LBooleanToDoubleFunctionX<ParseException> sutThrowing = LBooleanToDoubleFunctionX.lX((boolean b) -> {
            throw new ParseException(ORIGINAL_MESSAGE, 0);
        });

        // when
        LBooleanToDoubleFunction wrapped = LBooleanToDoubleFunction.wrap(sutThrowing);

        // then
        try {
            wrapped.applyAsDouble(true);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(NestedException.class)
                    .hasCauseExactlyInstanceOf(ParseException.class)
                    .hasMessage(ORIGINAL_MESSAGE);
        }
    }


    @Test
    public void testWrapExceptionMethodWrapsTheException() throws ParseException {

        // given
        LBooleanToDoubleFunction sutThrowing = LBooleanToDoubleFunction.l((boolean b) -> {
            throw new UnsupportedOperationException();
        });

        // when
        LBooleanToDoubleFunction wrapped = LBooleanToDoubleFunction.wrapException(sutThrowing, UnsupportedOperationException.class, null, t -> {
            throw new IllegalArgumentException(EXCEPTION_WAS_WRAPPED, t);
        });

        // then
        try {
            wrapped.applyAsDouble(true);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IllegalArgumentException.class)
                    .hasCauseExactlyInstanceOf(UnsupportedOperationException.class)
                    .hasMessage(EXCEPTION_WAS_WRAPPED);
        }
    }

    @Test
    public void testWrapExceptionMethodDoNotWrapsOtherException() throws ParseException {

        // given
        LBooleanToDoubleFunction sutThrowing = LBooleanToDoubleFunction.l((boolean b) -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LBooleanToDoubleFunction wrapped = LBooleanToDoubleFunction.wrapException(sutThrowing, UnsupportedOperationException.class, null, t -> {
            throw new IllegalArgumentException(EXCEPTION_WAS_WRAPPED, t);
        });

        // then
        try {
            wrapped.applyAsDouble(true);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                    .hasNoCause();
        }
    }

    @Test
    public void testWrapExceptionMisshandlingExceptionIsDetected() throws ParseException {

        // given
        LBooleanToDoubleFunction sutThrowing = LBooleanToDoubleFunction.l((boolean b) -> {
            throw new UnsupportedOperationException();
        });

        // when
        LBooleanToDoubleFunction wrapped = LBooleanToDoubleFunction.wrapException(sutThrowing, UnsupportedOperationException.class, null, t -> {
            return null;
        });

        // then
        try {
            wrapped.applyAsDouble(true);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(ExceptionNotHandled.class)
                    .hasCauseExactlyInstanceOf(UnsupportedOperationException.class)
                    .hasMessage("Handler has not processed the exception.");
        }
    }



    // <editor-fold desc="compose (functional)">

    @Test
    public void testfromBoolean() throws ParseException {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LBooleanToDoubleFunction sutO = (boolean b) -> {
                mainFunctionCalled.set(true);
                assertThat(b).isEqualTo(true);
                return (double)100;
        };

        LBooleanUnaryOperator before1 = p0 -> {
            assertThat(p0).isEqualTo(true);
            beforeCalls.incrementAndGet();
            return true;
        };

        //when
        LBooleanToDoubleFunction function = sutO.fromBoolean(before1);
        function.applyAsDouble(true);

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(beforeCalls.get()).isEqualTo(1);
    }


    @Test
    public void testfrom() throws ParseException {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LBooleanToDoubleFunction sutO = (boolean b) -> {
                mainFunctionCalled.set(true);
                assertThat(b).isEqualTo(true);
                return (double)100;
        };

        LPredicate<Integer > before1 = p0 -> {
            assertThat(p0).isEqualTo(Integer.valueOf(80));
            beforeCalls.incrementAndGet();
            return true;
        };

        //when
        LToDoubleFunction<Integer > function = sutO.from(before1);
        function.applyAsDouble((Integer )Integer.valueOf(80));

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(beforeCalls.get()).isEqualTo(1);
    }

    // </editor-fold>


    // <editor-fold desc="then (functional)">

    @Test
    public void testThen0() throws ParseException  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);


        //given (+ some assertions)
        LBooleanToDoubleFunction sutO = (boolean b) -> {
                mainFunctionCalled.set(true);
                assertThat(b).isEqualTo(true);
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
        LBooleanFunction<Integer > function = sutO.then(thenFunction);
        Integer  finalValue = function.apply(true);

        //then - finals
        assertThat(finalValue).isEqualTo(Integer.valueOf(100));
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    @Test
    public void testThen1ToByte() throws ParseException  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);


        //given (+ some assertions)
        LBooleanToDoubleFunction sutO = (boolean b) -> {
                mainFunctionCalled.set(true);
                assertThat(b).isEqualTo(true);
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
        LBooleanToByteFunction function = sutO.thenToByte(thenFunction);
        byte finalValue = function.applyAsByte(true);

        //then - finals
        assertThat(finalValue).isEqualTo((byte)100);
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    @Test
    public void testThen2ToShort() throws ParseException  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);


        //given (+ some assertions)
        LBooleanToDoubleFunction sutO = (boolean b) -> {
                mainFunctionCalled.set(true);
                assertThat(b).isEqualTo(true);
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
        LBooleanToShortFunction function = sutO.thenToShort(thenFunction);
        short finalValue = function.applyAsShort(true);

        //then - finals
        assertThat(finalValue).isEqualTo((short)100);
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    @Test
    public void testThen3ToInt() throws ParseException  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);


        //given (+ some assertions)
        LBooleanToDoubleFunction sutO = (boolean b) -> {
                mainFunctionCalled.set(true);
                assertThat(b).isEqualTo(true);
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
        LBooleanToIntFunction function = sutO.thenToInt(thenFunction);
        int finalValue = function.applyAsInt(true);

        //then - finals
        assertThat(finalValue).isEqualTo((int)100);
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    @Test
    public void testThen4ToLong() throws ParseException  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);


        //given (+ some assertions)
        LBooleanToDoubleFunction sutO = (boolean b) -> {
                mainFunctionCalled.set(true);
                assertThat(b).isEqualTo(true);
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
        LBooleanToLongFunction function = sutO.thenToLong(thenFunction);
        long finalValue = function.applyAsLong(true);

        //then - finals
        assertThat(finalValue).isEqualTo((long)100);
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    @Test
    public void testThen5ToFloat() throws ParseException  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);


        //given (+ some assertions)
        LBooleanToDoubleFunction sutO = (boolean b) -> {
                mainFunctionCalled.set(true);
                assertThat(b).isEqualTo(true);
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
        LBooleanToFloatFunction function = sutO.thenToFloat(thenFunction);
        float finalValue = function.applyAsFloat(true);

        //then - finals
        assertThat(finalValue).isEqualTo((float)100);
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    @Test
    public void testThen6ToDouble() throws ParseException  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);


        //given (+ some assertions)
        LBooleanToDoubleFunction sutO = (boolean b) -> {
                mainFunctionCalled.set(true);
                assertThat(b).isEqualTo(true);
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
        LBooleanToDoubleFunction function = sutO.thenToDouble(thenFunction);
        double finalValue = function.applyAsDouble(true);

        //then - finals
        assertThat(finalValue).isEqualTo((double)100);
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    @Test
    public void testThen7ToChar() throws ParseException  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);


        //given (+ some assertions)
        LBooleanToDoubleFunction sutO = (boolean b) -> {
                mainFunctionCalled.set(true);
                assertThat(b).isEqualTo(true);
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
        LBooleanToCharFunction function = sutO.thenToChar(thenFunction);
        char finalValue = function.applyAsChar(true);

        //then - finals
        assertThat(finalValue).isEqualTo((char)100);
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    @Test
    public void testThen8ToBoolean() throws ParseException  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);


        //given (+ some assertions)
        LBooleanToDoubleFunction sutO = (boolean b) -> {
                mainFunctionCalled.set(true);
                assertThat(b).isEqualTo(true);
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
        LBooleanUnaryOperator function = sutO.thenToBoolean(thenFunction);
        boolean finalValue = function.applyAsBoolean(true);

        //then - finals
        assertThat(finalValue).isEqualTo(true);
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    // </editor-fold>
    @Test
    public void testNonThrowing() {
        assertThat(sut.nonThrowing()).isInstanceOf(LBooleanToDoubleFunction.class);
    }

    @Test
    public void testUncheck() {
        assertThat(sut.uncheck()).isInstanceOf(LBooleanToDoubleFunctionX.class);
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void testShove() {

        // given
        LBooleanToDoubleFunction sutThrowing = LBooleanToDoubleFunction.l((boolean b) -> {
            throw new UnsupportedOperationException();
        });

        // when
        sutThrowing.shove().applyAsDouble(true);
    }

    @Test
    public void testHandle() throws ParseException {

        // given
        LBooleanToDoubleFunction sutThrowing = LBooleanToDoubleFunction.l((boolean b) -> {
            throw new UnsupportedOperationException();
        });

        // when
        LBooleanToDoubleFunction wrapped = sutThrowing.handle(UnsupportedOperationException.class, t -> {
            throw new IllegalArgumentException(EXCEPTION_WAS_WRAPPED, t);
        });

        // then
        try {
            wrapped.applyAsDouble(true);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IllegalArgumentException.class)
                    .hasCauseExactlyInstanceOf(UnsupportedOperationException.class)
                    .hasMessage(EXCEPTION_WAS_WRAPPED);
        }
    }

    @Test
    public void testToString() throws ParseException {

        assertThat(sut.toString())
                .isInstanceOf(String.class)
                .startsWith(this.getClass().getName()+"$");

        assertThat(String.format("%s", sut))
                .isInstanceOf(String.class)
                .contains("LBooleanToDoubleFunction: double applyAsDouble(boolean b)");
    }


    @Test
    public void isThrowing() {
        assertThat(sut.isThrowing())
            .isFalse();
    }


}