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
public class LIntToLongFunctionTest<X extends ParseException> {
    private static final String ORIGINAL_MESSAGE = "Original message";
    private static final String EXCEPTION_WAS_WRAPPED = "Exception was wrapped.";
    private static final String NO_EXCEPTION_WERE_THROWN = "No exception were thrown.";

    private long testValue = (long)100;



    private LIntToLongFunction sut = new LIntToLongFunction(){
        public  long applyAsLong(int i)  {
            return testValue;
        }
    };

    private LIntToLongFunctionX<X> opposite = new LIntToLongFunctionX(){
        public  long applyAsLong(int i) throws ParseException {
            return testValue;
        }
    };


    private java.util.function.IntToLongFunction jre = (int i) -> testValue;


    @Test
    public void testTheResult() throws ParseException {
        assertThat(sut.applyAsLong((int)100))
            .isEqualTo(testValue);
    }

    @Test
    public void testNonNullShouldNotModifyValue() throws ParseException {
        assertThat(sut.nonNull((int)100))
            .isEqualTo(testValue);
    }


    @Test
    public void testFunctionalInterfaceDescription() throws ParseException {
        assertThat(sut.functionalInterfaceDescription())
            .isEqualTo("LIntToLongFunction: long applyAsLong(int i)");
    }

    @Test
    public void testLMethod() throws ParseException {
        assertThat(LIntToLongFunction.l((int i) -> testValue ))
            .isInstanceOf(LIntToLongFunction.class);
    }

    @Test
    public void testWrapMethod() throws ParseException {
        assertThat(LIntToLongFunction.wrap(opposite))
            .isInstanceOf(LIntToLongFunction.class);
    }

    @Test
    public void testWrapStdMethod() throws ParseException {
        assertThat(LIntToLongFunction.wrapStd(jre))
            .isInstanceOf(LIntToLongFunction.class);
    }

    @Test
    public void testWrapMethodDoNotWrapsRuntimeException() throws ParseException {
        // given
        LIntToLongFunctionX<X> sutThrowing = LIntToLongFunctionX.lX((int i) -> {
            throw new UnsupportedOperationException(ORIGINAL_MESSAGE);
        });

        // when
        LIntToLongFunction wrapped = LIntToLongFunction.wrap(sutThrowing);

        // then
        try {
            wrapped.applyAsLong((int)100);
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
        LIntToLongFunctionX<ParseException> sutThrowing = LIntToLongFunctionX.lX((int i) -> {
            throw new ParseException(ORIGINAL_MESSAGE, 0);
        });

        // when
        LIntToLongFunction wrapped = LIntToLongFunction.wrap(sutThrowing);

        // then
        try {
            wrapped.applyAsLong((int)100);
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
        LIntToLongFunction sutThrowing = LIntToLongFunction.l((int i) -> {
            throw new UnsupportedOperationException();
        });

        // when
        LIntToLongFunction wrapped = LIntToLongFunction.wrapException(sutThrowing, UnsupportedOperationException.class, null, t -> {
            throw new IllegalArgumentException(EXCEPTION_WAS_WRAPPED, t);
        });

        // then
        try {
            wrapped.applyAsLong((int)100);
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
        LIntToLongFunction sutThrowing = LIntToLongFunction.l((int i) -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LIntToLongFunction wrapped = LIntToLongFunction.wrapException(sutThrowing, UnsupportedOperationException.class, null, t -> {
            throw new IllegalArgumentException(EXCEPTION_WAS_WRAPPED, t);
        });

        // then
        try {
            wrapped.applyAsLong((int)100);
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
        LIntToLongFunction sutThrowing = LIntToLongFunction.l((int i) -> {
            throw new UnsupportedOperationException();
        });

        // when
        LIntToLongFunction wrapped = LIntToLongFunction.wrapException(sutThrowing, UnsupportedOperationException.class, null, t -> {
            return null;
        });

        // then
        try {
            wrapped.applyAsLong((int)100);
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
    public void testfromInt() throws ParseException {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LIntToLongFunction sutO = (int i) -> {
                mainFunctionCalled.set(true);
                assertThat(i).isEqualTo((int)90);
                return (long)100;
        };

        LIntUnaryOperator before1 = p0 -> {
            assertThat(p0).isEqualTo((int)80);
            beforeCalls.incrementAndGet();
            return (int)90;
        };

        //when
        LIntToLongFunction function = sutO.fromInt(before1);
        function.applyAsLong((int)80);

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(beforeCalls.get()).isEqualTo(1);
    }


    @Test
    public void testfrom() throws ParseException {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LIntToLongFunction sutO = (int i) -> {
                mainFunctionCalled.set(true);
                assertThat(i).isEqualTo((int)90);
                return (long)100;
        };

        LToIntFunction<Integer > before1 = p0 -> {
            assertThat(p0).isEqualTo(Integer.valueOf(80));
            beforeCalls.incrementAndGet();
            return (int)90;
        };

        //when
        LToLongFunction<Integer > function = sutO.from(before1);
        function.applyAsLong((Integer )Integer.valueOf(80));

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
        LIntToLongFunction sutO = (int i) -> {
                mainFunctionCalled.set(true);
                assertThat(i).isEqualTo((int)80);
                return (long)90;
        };

        LLongFunction<Integer > thenFunction = p -> {
                thenFunctionCalled.set(true);
                // 
                assertThat(p).isEqualTo((long)90);
                // V
                return Integer.valueOf(100);
        };

        //when
        LIntFunction<Integer > function = sutO.then(thenFunction);
        Integer  finalValue = function.apply((int)80);

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
        LIntToLongFunction sutO = (int i) -> {
                mainFunctionCalled.set(true);
                assertThat(i).isEqualTo((int)80);
                return (long)90;
        };

        LLongToByteFunction thenFunction = p -> {
                thenFunctionCalled.set(true);
                // 
                assertThat(p).isEqualTo((long)90);
                // byte
                return (byte)100;
        };

        //when
        LIntToByteFunction function = sutO.thenToByte(thenFunction);
        byte finalValue = function.applyAsByte((int)80);

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
        LIntToLongFunction sutO = (int i) -> {
                mainFunctionCalled.set(true);
                assertThat(i).isEqualTo((int)80);
                return (long)90;
        };

        LLongToShortFunction thenFunction = p -> {
                thenFunctionCalled.set(true);
                // 
                assertThat(p).isEqualTo((long)90);
                // short
                return (short)100;
        };

        //when
        LIntToShortFunction function = sutO.thenToShort(thenFunction);
        short finalValue = function.applyAsShort((int)80);

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
        LIntToLongFunction sutO = (int i) -> {
                mainFunctionCalled.set(true);
                assertThat(i).isEqualTo((int)80);
                return (long)90;
        };

        LLongToIntFunction thenFunction = p -> {
                thenFunctionCalled.set(true);
                // 
                assertThat(p).isEqualTo((long)90);
                // int
                return (int)100;
        };

        //when
        LIntUnaryOperator function = sutO.thenToInt(thenFunction);
        int finalValue = function.applyAsInt((int)80);

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
        LIntToLongFunction sutO = (int i) -> {
                mainFunctionCalled.set(true);
                assertThat(i).isEqualTo((int)80);
                return (long)90;
        };

        LLongUnaryOperator thenFunction = p -> {
                thenFunctionCalled.set(true);
                // 
                assertThat(p).isEqualTo((long)90);
                // long
                return (long)100;
        };

        //when
        LIntToLongFunction function = sutO.thenToLong(thenFunction);
        long finalValue = function.applyAsLong((int)80);

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
        LIntToLongFunction sutO = (int i) -> {
                mainFunctionCalled.set(true);
                assertThat(i).isEqualTo((int)80);
                return (long)90;
        };

        LLongToFloatFunction thenFunction = p -> {
                thenFunctionCalled.set(true);
                // 
                assertThat(p).isEqualTo((long)90);
                // float
                return (float)100;
        };

        //when
        LIntToFloatFunction function = sutO.thenToFloat(thenFunction);
        float finalValue = function.applyAsFloat((int)80);

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
        LIntToLongFunction sutO = (int i) -> {
                mainFunctionCalled.set(true);
                assertThat(i).isEqualTo((int)80);
                return (long)90;
        };

        LLongToDoubleFunction thenFunction = p -> {
                thenFunctionCalled.set(true);
                // 
                assertThat(p).isEqualTo((long)90);
                // double
                return (double)100;
        };

        //when
        LIntToDoubleFunction function = sutO.thenToDouble(thenFunction);
        double finalValue = function.applyAsDouble((int)80);

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
        LIntToLongFunction sutO = (int i) -> {
                mainFunctionCalled.set(true);
                assertThat(i).isEqualTo((int)80);
                return (long)90;
        };

        LLongToCharFunction thenFunction = p -> {
                thenFunctionCalled.set(true);
                // 
                assertThat(p).isEqualTo((long)90);
                // char
                return (char)100;
        };

        //when
        LIntToCharFunction function = sutO.thenToChar(thenFunction);
        char finalValue = function.applyAsChar((int)80);

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
        LIntToLongFunction sutO = (int i) -> {
                mainFunctionCalled.set(true);
                assertThat(i).isEqualTo((int)80);
                return (long)90;
        };

        LLongPredicate thenFunction = p -> {
                thenFunctionCalled.set(true);
                // 
                assertThat(p).isEqualTo((long)90);
                // boolean
                return true;
        };

        //when
        LIntPredicate function = sutO.thenToBoolean(thenFunction);
        boolean finalValue = function.test((int)80);

        //then - finals
        assertThat(finalValue).isEqualTo(true);
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    // </editor-fold>
    @Test
    public void testStd() {
        assertThat(sut.std()).isInstanceOf(java.util.function.IntToLongFunction.class);
    }

    @Test
    public void testNonThrowing() {
        assertThat(sut.nonThrowing()).isInstanceOf(LIntToLongFunction.class);
    }

    @Test
    public void testUncheck() {
        assertThat(sut.uncheck()).isInstanceOf(LIntToLongFunctionX.class);
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void testShove() {

        // given
        LIntToLongFunction sutThrowing = LIntToLongFunction.l((int i) -> {
            throw new UnsupportedOperationException();
        });

        // when
        sutThrowing.shove().applyAsLong((int)100);
    }

    @Test
    public void testHandle() throws ParseException {

        // given
        LIntToLongFunction sutThrowing = LIntToLongFunction.l((int i) -> {
            throw new UnsupportedOperationException();
        });

        // when
        LIntToLongFunction wrapped = sutThrowing.handle(UnsupportedOperationException.class, t -> {
            throw new IllegalArgumentException(EXCEPTION_WAS_WRAPPED, t);
        });

        // then
        try {
            wrapped.applyAsLong((int)100);
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
                .contains("LIntToLongFunction: long applyAsLong(int i)");
    }


    @Test
    public void isThrowing() {
        assertThat(sut.isThrowing())
            .isFalse();
    }


}
