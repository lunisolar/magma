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

package eu.lunisolar.magma.func.supplier;

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
public class LShortSupplierXTest<X extends ParseException> {
    private static final String ORIGINAL_MESSAGE = "Original message";
    private static final String EXCEPTION_WAS_WRAPPED = "Exception was wrapped.";
    private static final String NO_EXCEPTION_WERE_THROWN = "No exception were thrown.";

    private short testValue = (short)100;



    private LShortSupplierX<X> sut = new LShortSupplierX(){
        public  short doGetAsShort() throws ParseException {
            return testValue;
        }
    };

    private LShortSupplier opposite = new LShortSupplier(){
        public  short doGetAsShort()  {
            return testValue;
        }
    };



    private LShortSupplierX<ParseException> sutAlwaysThrowing = LShortSupplierX.lX(() -> {
            throw new ParseException(ORIGINAL_MESSAGE, 0);
    });

    private LShortSupplierX<RuntimeException> sutAlwaysThrowingUnckeck = LShortSupplierX.lX(() -> {
            throw new IndexOutOfBoundsException(ORIGINAL_MESSAGE);
    });


    @Test
    public void testTheResult() throws ParseException {
        assertThat(sut.doGetAsShort())
            .isEqualTo(testValue);
    }

    @Test
    public void testNonNullDoGetAsShort() throws ParseException {
        assertThat(sut.nonNullDoGetAsShort())
            .isEqualTo(testValue);
    }

    @Test
    public void testNestingDoGetAsShort_checked() throws ParseException {

        // then
        try {
            sutAlwaysThrowing.nestingDoGetAsShort();
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(NestedException.class)
                    .hasCauseExactlyInstanceOf(ParseException.class)
                    .hasMessage(ORIGINAL_MESSAGE);
        }
    }

    @Test
    public void testNestingDoGetAsShort_unckeck() throws ParseException {

        // then
        try {
            sutAlwaysThrowingUnckeck.nestingDoGetAsShort();
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                    .hasNoCause()
                    .hasMessage(ORIGINAL_MESSAGE);
        }
    }

    @Test
    public void testShovingDoGetAsShort_checked() throws ParseException {

        // then
        try {
            sutAlwaysThrowing.shovingDoGetAsShort();
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(ParseException.class)
                    .hasNoCause()
                    .hasMessage(ORIGINAL_MESSAGE);
        }
    }

    @Test
    public void testShovingDoGetAsShort_unckeck() throws ParseException {

        // then
        try {
            sutAlwaysThrowingUnckeck.shovingDoGetAsShort();
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                    .hasNoCause()
                    .hasMessage(ORIGINAL_MESSAGE);
        }
    }



    @Test
    public void testFunctionalInterfaceDescription() throws ParseException {
        assertThat(sut.functionalInterfaceDescription())
            .isEqualTo("LShortSupplierX: short doGetAsShort() throws X");
    }

    @Test
    public void testLXMethod() throws ParseException {
        assertThat(LShortSupplierX.lX(() -> testValue ))
            .isInstanceOf(LShortSupplierX.class);
    }

    @Test
    public void testWrapXMethod() throws ParseException {
        assertThat(LShortSupplierX.wrapX(opposite))
            .isInstanceOf(LShortSupplierX.class);
    }


    @Test
    public void testWrapExceptionMethodWrapsTheException() throws ParseException {

        // given
        LShortSupplierX<X> sutThrowing = LShortSupplierX.lX(() -> {
            throw new UnsupportedOperationException();
        });

        // when
        LShortSupplierX<X> wrapped = LShortSupplierX.wrapException(sutThrowing, UnsupportedOperationException.class, null, t -> {
            throw new IllegalArgumentException(EXCEPTION_WAS_WRAPPED, t);
        });

        // then
        try {
            wrapped.doGetAsShort();
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
        LShortSupplierX<X> sutThrowing = LShortSupplierX.lX(() -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LShortSupplierX<X> wrapped = LShortSupplierX.wrapException(sutThrowing, UnsupportedOperationException.class, null, t -> {
            throw new IllegalArgumentException(EXCEPTION_WAS_WRAPPED, t);
        });

        // then
        try {
            wrapped.doGetAsShort();
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
        LShortSupplierX<X> sutThrowing = LShortSupplierX.lX(() -> {
            throw new UnsupportedOperationException();
        });

        // when
        LShortSupplierX<X> wrapped = LShortSupplierX.wrapException(sutThrowing, UnsupportedOperationException.class, null, t -> {
            return null;
        });

        // then
        try {
            wrapped.doGetAsShort();
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(ExceptionNotHandled.class)
                    .hasCauseExactlyInstanceOf(UnsupportedOperationException.class)
                    .hasMessage("Handler has not processed the exception.");
        }
    }



    // <editor-fold desc="then (functional)">

    @Test
    public void testThen0() throws ParseException  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);


        //given (+ some assertions)
        LShortSupplierX<X> sutO = () -> {
                mainFunctionCalled.set(true);
                return (short)90;
        };

        LShortFunctionX<Integer ,X> thenFunction = p -> {
                thenFunctionCalled.set(true);
                // 
                assertThat(p).isEqualTo((short)90);
                // V
                return Integer.valueOf(100);
        };

        //when
        LSupplierX<Integer ,X> function = sutO.then(thenFunction);
        Integer  finalValue = function.doGet();

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
        LShortSupplierX<X> sutO = () -> {
                mainFunctionCalled.set(true);
                return (short)90;
        };

        LShortToByteFunctionX<X> thenFunction = p -> {
                thenFunctionCalled.set(true);
                // 
                assertThat(p).isEqualTo((short)90);
                // byte
                return (byte)100;
        };

        //when
        LByteSupplierX<X> function = sutO.thenToByte(thenFunction);
        byte finalValue = function.doGetAsByte();

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
        LShortSupplierX<X> sutO = () -> {
                mainFunctionCalled.set(true);
                return (short)90;
        };

        LShortUnaryOperatorX<X> thenFunction = p -> {
                thenFunctionCalled.set(true);
                // 
                assertThat(p).isEqualTo((short)90);
                // short
                return (short)100;
        };

        //when
        LShortSupplierX<X> function = sutO.thenToShort(thenFunction);
        short finalValue = function.doGetAsShort();

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
        LShortSupplierX<X> sutO = () -> {
                mainFunctionCalled.set(true);
                return (short)90;
        };

        LShortToIntFunctionX<X> thenFunction = p -> {
                thenFunctionCalled.set(true);
                // 
                assertThat(p).isEqualTo((short)90);
                // int
                return (int)100;
        };

        //when
        LIntSupplierX<X> function = sutO.thenToInt(thenFunction);
        int finalValue = function.doGetAsInt();

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
        LShortSupplierX<X> sutO = () -> {
                mainFunctionCalled.set(true);
                return (short)90;
        };

        LShortToLongFunctionX<X> thenFunction = p -> {
                thenFunctionCalled.set(true);
                // 
                assertThat(p).isEqualTo((short)90);
                // long
                return (long)100;
        };

        //when
        LLongSupplierX<X> function = sutO.thenToLong(thenFunction);
        long finalValue = function.doGetAsLong();

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
        LShortSupplierX<X> sutO = () -> {
                mainFunctionCalled.set(true);
                return (short)90;
        };

        LShortToFloatFunctionX<X> thenFunction = p -> {
                thenFunctionCalled.set(true);
                // 
                assertThat(p).isEqualTo((short)90);
                // float
                return (float)100;
        };

        //when
        LFloatSupplierX<X> function = sutO.thenToFloat(thenFunction);
        float finalValue = function.doGetAsFloat();

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
        LShortSupplierX<X> sutO = () -> {
                mainFunctionCalled.set(true);
                return (short)90;
        };

        LShortToDoubleFunctionX<X> thenFunction = p -> {
                thenFunctionCalled.set(true);
                // 
                assertThat(p).isEqualTo((short)90);
                // double
                return (double)100;
        };

        //when
        LDoubleSupplierX<X> function = sutO.thenToDouble(thenFunction);
        double finalValue = function.doGetAsDouble();

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
        LShortSupplierX<X> sutO = () -> {
                mainFunctionCalled.set(true);
                return (short)90;
        };

        LShortToCharFunctionX<X> thenFunction = p -> {
                thenFunctionCalled.set(true);
                // 
                assertThat(p).isEqualTo((short)90);
                // char
                return (char)100;
        };

        //when
        LCharSupplierX<X> function = sutO.thenToChar(thenFunction);
        char finalValue = function.doGetAsChar();

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
        LShortSupplierX<X> sutO = () -> {
                mainFunctionCalled.set(true);
                return (short)90;
        };

        LShortPredicateX<X> thenFunction = p -> {
                thenFunctionCalled.set(true);
                // 
                assertThat(p).isEqualTo((short)90);
                // boolean
                return true;
        };

        //when
        LBooleanSupplierX<X> function = sutO.thenToBoolean(thenFunction);
        boolean finalValue = function.doGetAsBoolean();

        //then - finals
        assertThat(finalValue).isEqualTo(true);
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    // </editor-fold>
//
    @Test
    public void testNesting() {
        assertThat(sut.nest())
            .isInstanceOf(LShortSupplier.class);
    }

    @Test
    public void testShoving() {
        assertThat(sut.shove())
            .isInstanceOf(LShortSupplier.class);
    }

    @Test
    public void testNestingX() {
        assertThat(sut.nestX())
            .isInstanceOf(LShortSupplierX.class);
    }

    @Test
    public void testShovingX() {
        assertThat(sut.shoveX())
            .isInstanceOf(LShortSupplierX.class);
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void testShove() {

        // given
        LShortSupplierX<X> sutThrowing = LShortSupplierX.lX(() -> {
            throw new UnsupportedOperationException();
        });

        // when
        sutThrowing.shove().doGetAsShort();
    }

    @Test
    public void testHandleX() throws ParseException {

        // given
        LShortSupplierX<X> sutThrowing = LShortSupplierX.lX(() -> {
            throw new UnsupportedOperationException();
        });

        // when
        LShortSupplierX<X> wrapped = sutThrowing.handleX(UnsupportedOperationException.class, t -> {
            throw new IllegalArgumentException(EXCEPTION_WAS_WRAPPED, t);
        });

        // then
        try {
            wrapped.doGetAsShort();
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
                .contains("LShortSupplierX: short doGetAsShort() throws X");
    }


    @Test
    public void isThrowing() {
        assertThat(sut.isThrowing())
            .isTrue();
    }


}
