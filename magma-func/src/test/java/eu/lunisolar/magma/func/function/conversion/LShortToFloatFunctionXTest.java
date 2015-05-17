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
public class LShortToFloatFunctionXTest<X extends ParseException> {
    private static final String ORIGINAL_MESSAGE = "Original message";
    private static final String EXCEPTION_WAS_WRAPPED = "Exception was wrapped.";
    private static final String NO_EXCEPTION_WERE_THROWN = "No exception were thrown.";

    private float testValue = (float)100;



    private LShortToFloatFunctionX<X> sut = new LShortToFloatFunctionX(){
        public  float doApplyAsFloat(short s) throws ParseException {
            return testValue;
        }
    };

    private LShortToFloatFunction opposite = new LShortToFloatFunction(){
        public  float doApplyAsFloat(short s)  {
            return testValue;
        }
    };



    private LShortToFloatFunctionX<ParseException> sutAlwaysThrowing = LShortToFloatFunctionX.lX((short s) -> {
            throw new ParseException(ORIGINAL_MESSAGE, 0);
    });

    private LShortToFloatFunctionX<RuntimeException> sutAlwaysThrowingUnckeck = LShortToFloatFunctionX.lX((short s) -> {
            throw new IndexOutOfBoundsException(ORIGINAL_MESSAGE);
    });


    @Test
    public void testTheResult() throws ParseException {
        assertThat(sut.doApplyAsFloat((short)100))
            .isEqualTo(testValue);
    }

    @Test
    public void testNonNullDoApplyAsFloat() throws ParseException {
        assertThat(sut.nonNullDoApplyAsFloat((short)100))
            .isEqualTo(testValue);
    }

    @Test
    public void testNestingDoApplyAsFloat_checked() throws ParseException {

        // then
        try {
            sutAlwaysThrowing.nestingDoApplyAsFloat((short)100);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(NestedException.class)
                    .hasCauseExactlyInstanceOf(ParseException.class)
                    .hasMessage(ORIGINAL_MESSAGE);
        }
    }

    @Test
    public void testNestingDoApplyAsFloat_unckeck() throws ParseException {

        // then
        try {
            sutAlwaysThrowingUnckeck.nestingDoApplyAsFloat((short)100);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                    .hasNoCause()
                    .hasMessage(ORIGINAL_MESSAGE);
        }
    }

    @Test
    public void testShovingDoApplyAsFloat_checked() throws ParseException {

        // then
        try {
            sutAlwaysThrowing.shovingDoApplyAsFloat((short)100);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(ParseException.class)
                    .hasNoCause()
                    .hasMessage(ORIGINAL_MESSAGE);
        }
    }

    @Test
    public void testShovingDoApplyAsFloat_unckeck() throws ParseException {

        // then
        try {
            sutAlwaysThrowingUnckeck.shovingDoApplyAsFloat((short)100);
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
            .isEqualTo("LShortToFloatFunctionX: float doApplyAsFloat(short s) throws X");
    }

    @Test
    public void testLXMethod() throws ParseException {
        assertThat(LShortToFloatFunctionX.lX((short s) -> testValue ))
            .isInstanceOf(LShortToFloatFunctionX.class);
    }

    @Test
    public void testWrapXMethod() throws ParseException {
        assertThat(LShortToFloatFunctionX.wrapX(opposite))
            .isInstanceOf(LShortToFloatFunctionX.class);
    }


    @Test
    public void testWrapExceptionMethodWrapsTheException() throws ParseException {

        // given
        LShortToFloatFunctionX<X> sutThrowing = LShortToFloatFunctionX.lX((short s) -> {
            throw new UnsupportedOperationException();
        });

        // when
        LShortToFloatFunctionX<X> wrapped = LShortToFloatFunctionX.wrapException(sutThrowing, UnsupportedOperationException.class, null, t -> {
            throw new IllegalArgumentException(EXCEPTION_WAS_WRAPPED, t);
        });

        // then
        try {
            wrapped.doApplyAsFloat((short)100);
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
        LShortToFloatFunctionX<X> sutThrowing = LShortToFloatFunctionX.lX((short s) -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LShortToFloatFunctionX<X> wrapped = LShortToFloatFunctionX.wrapException(sutThrowing, UnsupportedOperationException.class, null, t -> {
            throw new IllegalArgumentException(EXCEPTION_WAS_WRAPPED, t);
        });

        // then
        try {
            wrapped.doApplyAsFloat((short)100);
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
        LShortToFloatFunctionX<X> sutThrowing = LShortToFloatFunctionX.lX((short s) -> {
            throw new UnsupportedOperationException();
        });

        // when
        LShortToFloatFunctionX<X> wrapped = LShortToFloatFunctionX.wrapException(sutThrowing, UnsupportedOperationException.class, null, t -> {
            return null;
        });

        // then
        try {
            wrapped.doApplyAsFloat((short)100);
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
    public void testfromShort() throws ParseException {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LShortToFloatFunctionX<X> sutO = (short s) -> {
                mainFunctionCalled.set(true);
                assertThat(s).isEqualTo((short)90);
                return (float)100;
        };

        LShortUnaryOperatorX<X> before1 = p0 -> {
            assertThat(p0).isEqualTo((short)80);
            beforeCalls.incrementAndGet();
            return (short)90;
        };

        //when
        LShortToFloatFunctionX<X> function = sutO.fromShort(before1);
        function.doApplyAsFloat((short)80);

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(beforeCalls.get()).isEqualTo(1);
    }


    @Test
    public void testfrom() throws ParseException {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LShortToFloatFunctionX<X> sutO = (short s) -> {
                mainFunctionCalled.set(true);
                assertThat(s).isEqualTo((short)90);
                return (float)100;
        };

        LToShortFunctionX<Integer ,X> before1 = p0 -> {
            assertThat(p0).isEqualTo(Integer.valueOf(80));
            beforeCalls.incrementAndGet();
            return (short)90;
        };

        //when
        LToFloatFunctionX<Integer ,X> function = sutO.from(before1);
        function.doApplyAsFloat((Integer )Integer.valueOf(80));

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
        LShortToFloatFunctionX<X> sutO = (short s) -> {
                mainFunctionCalled.set(true);
                assertThat(s).isEqualTo((short)80);
                return (float)90;
        };

        LFloatFunctionX<Integer ,X> thenFunction = p -> {
                thenFunctionCalled.set(true);
                // 
                assertThat(p).isEqualTo((float)90);
                // V
                return Integer.valueOf(100);
        };

        //when
        LShortFunctionX<Integer ,X> function = sutO.then(thenFunction);
        Integer  finalValue = function.doApply((short)80);

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
        LShortToFloatFunctionX<X> sutO = (short s) -> {
                mainFunctionCalled.set(true);
                assertThat(s).isEqualTo((short)80);
                return (float)90;
        };

        LFloatToByteFunctionX<X> thenFunction = p -> {
                thenFunctionCalled.set(true);
                // 
                assertThat(p).isEqualTo((float)90);
                // byte
                return (byte)100;
        };

        //when
        LShortToByteFunctionX<X> function = sutO.thenToByte(thenFunction);
        byte finalValue = function.doApplyAsByte((short)80);

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
        LShortToFloatFunctionX<X> sutO = (short s) -> {
                mainFunctionCalled.set(true);
                assertThat(s).isEqualTo((short)80);
                return (float)90;
        };

        LFloatToShortFunctionX<X> thenFunction = p -> {
                thenFunctionCalled.set(true);
                // 
                assertThat(p).isEqualTo((float)90);
                // short
                return (short)100;
        };

        //when
        LShortUnaryOperatorX<X> function = sutO.thenToShort(thenFunction);
        short finalValue = function.doApplyAsShort((short)80);

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
        LShortToFloatFunctionX<X> sutO = (short s) -> {
                mainFunctionCalled.set(true);
                assertThat(s).isEqualTo((short)80);
                return (float)90;
        };

        LFloatToIntFunctionX<X> thenFunction = p -> {
                thenFunctionCalled.set(true);
                // 
                assertThat(p).isEqualTo((float)90);
                // int
                return (int)100;
        };

        //when
        LShortToIntFunctionX<X> function = sutO.thenToInt(thenFunction);
        int finalValue = function.doApplyAsInt((short)80);

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
        LShortToFloatFunctionX<X> sutO = (short s) -> {
                mainFunctionCalled.set(true);
                assertThat(s).isEqualTo((short)80);
                return (float)90;
        };

        LFloatToLongFunctionX<X> thenFunction = p -> {
                thenFunctionCalled.set(true);
                // 
                assertThat(p).isEqualTo((float)90);
                // long
                return (long)100;
        };

        //when
        LShortToLongFunctionX<X> function = sutO.thenToLong(thenFunction);
        long finalValue = function.doApplyAsLong((short)80);

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
        LShortToFloatFunctionX<X> sutO = (short s) -> {
                mainFunctionCalled.set(true);
                assertThat(s).isEqualTo((short)80);
                return (float)90;
        };

        LFloatUnaryOperatorX<X> thenFunction = p -> {
                thenFunctionCalled.set(true);
                // 
                assertThat(p).isEqualTo((float)90);
                // float
                return (float)100;
        };

        //when
        LShortToFloatFunctionX<X> function = sutO.thenToFloat(thenFunction);
        float finalValue = function.doApplyAsFloat((short)80);

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
        LShortToFloatFunctionX<X> sutO = (short s) -> {
                mainFunctionCalled.set(true);
                assertThat(s).isEqualTo((short)80);
                return (float)90;
        };

        LFloatToDoubleFunctionX<X> thenFunction = p -> {
                thenFunctionCalled.set(true);
                // 
                assertThat(p).isEqualTo((float)90);
                // double
                return (double)100;
        };

        //when
        LShortToDoubleFunctionX<X> function = sutO.thenToDouble(thenFunction);
        double finalValue = function.doApplyAsDouble((short)80);

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
        LShortToFloatFunctionX<X> sutO = (short s) -> {
                mainFunctionCalled.set(true);
                assertThat(s).isEqualTo((short)80);
                return (float)90;
        };

        LFloatToCharFunctionX<X> thenFunction = p -> {
                thenFunctionCalled.set(true);
                // 
                assertThat(p).isEqualTo((float)90);
                // char
                return (char)100;
        };

        //when
        LShortToCharFunctionX<X> function = sutO.thenToChar(thenFunction);
        char finalValue = function.doApplyAsChar((short)80);

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
        LShortToFloatFunctionX<X> sutO = (short s) -> {
                mainFunctionCalled.set(true);
                assertThat(s).isEqualTo((short)80);
                return (float)90;
        };

        LFloatPredicateX<X> thenFunction = p -> {
                thenFunctionCalled.set(true);
                // 
                assertThat(p).isEqualTo((float)90);
                // boolean
                return true;
        };

        //when
        LShortPredicateX<X> function = sutO.thenToBoolean(thenFunction);
        boolean finalValue = function.doTest((short)80);

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
            .isInstanceOf(LShortToFloatFunction.class);
    }

    @Test
    public void testShoving() {
        assertThat(sut.shove())
            .isInstanceOf(LShortToFloatFunction.class);
    }

    @Test
    public void testNestingX() {
        assertThat(sut.nestX())
            .isInstanceOf(LShortToFloatFunctionX.class);
    }

    @Test
    public void testShovingX() {
        assertThat(sut.shoveX())
            .isInstanceOf(LShortToFloatFunctionX.class);
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void testShove() {

        // given
        LShortToFloatFunctionX<X> sutThrowing = LShortToFloatFunctionX.lX((short s) -> {
            throw new UnsupportedOperationException();
        });

        // when
        sutThrowing.shove().doApplyAsFloat((short)100);
    }

    @Test
    public void testHandleX() throws ParseException {

        // given
        LShortToFloatFunctionX<X> sutThrowing = LShortToFloatFunctionX.lX((short s) -> {
            throw new UnsupportedOperationException();
        });

        // when
        LShortToFloatFunctionX<X> wrapped = sutThrowing.handleX(UnsupportedOperationException.class, t -> {
            throw new IllegalArgumentException(EXCEPTION_WAS_WRAPPED, t);
        });

        // then
        try {
            wrapped.doApplyAsFloat((short)100);
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
                .contains("LShortToFloatFunctionX: float doApplyAsFloat(short s) throws X");
    }


    @Test
    public void isThrowing() {
        assertThat(sut.isThrowing())
            .isTrue();
    }


}
