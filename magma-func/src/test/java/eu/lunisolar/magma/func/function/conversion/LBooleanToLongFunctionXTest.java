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
public class LBooleanToLongFunctionXTest<X extends ParseException> {
    private static final String ORIGINAL_MESSAGE = "Original message";
    private static final String EXCEPTION_WAS_WRAPPED = "Exception was wrapped.";
    private static final String NO_EXCEPTION_WERE_THROWN = "No exception were thrown.";

    private long testValue = (long)100;



    private LBooleanToLongFunctionX<X> sut = new LBooleanToLongFunctionX(){
        public  long doApplyAsLong(boolean b) throws ParseException {
            return testValue;
        }
    };

    private LBooleanToLongFunction opposite = new LBooleanToLongFunction(){
        public  long doApplyAsLong(boolean b)  {
            return testValue;
        }
    };



    private LBooleanToLongFunctionX<ParseException> sutAlwaysThrowing = LBooleanToLongFunctionX.lX((boolean b) -> {
            throw new ParseException(ORIGINAL_MESSAGE, 0);
    });

    private LBooleanToLongFunctionX<RuntimeException> sutAlwaysThrowingUnckeck = LBooleanToLongFunctionX.lX((boolean b) -> {
            throw new IndexOutOfBoundsException(ORIGINAL_MESSAGE);
    });


    @Test
    public void testTheResult() throws ParseException {
        assertThat(sut.doApplyAsLong(true))
            .isEqualTo(testValue);
    }

    @Test
    public void testNonNullDoApplyAsLong() throws ParseException {
        assertThat(sut.nonNullDoApplyAsLong(true))
            .isEqualTo(testValue);
    }

    @Test
    public void testNestingDoApplyAsLong_checked() throws ParseException {

        // then
        try {
            sutAlwaysThrowing.nestingDoApplyAsLong(true);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(NestedException.class)
                    .hasCauseExactlyInstanceOf(ParseException.class)
                    .hasMessage(ORIGINAL_MESSAGE);
        }
    }

    @Test
    public void testNestingDoApplyAsLong_unckeck() throws ParseException {

        // then
        try {
            sutAlwaysThrowingUnckeck.nestingDoApplyAsLong(true);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                    .hasNoCause()
                    .hasMessage(ORIGINAL_MESSAGE);
        }
    }

    @Test
    public void testShovingDoApplyAsLong_checked() throws ParseException {

        // then
        try {
            sutAlwaysThrowing.shovingDoApplyAsLong(true);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(ParseException.class)
                    .hasNoCause()
                    .hasMessage(ORIGINAL_MESSAGE);
        }
    }

    @Test
    public void testShovingDoApplyAsLong_unckeck() throws ParseException {

        // then
        try {
            sutAlwaysThrowingUnckeck.shovingDoApplyAsLong(true);
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
            .isEqualTo("LBooleanToLongFunctionX: long doApplyAsLong(boolean b) throws X");
    }

    @Test
    public void testLXMethod() throws ParseException {
        assertThat(LBooleanToLongFunctionX.lX((boolean b) -> testValue ))
            .isInstanceOf(LBooleanToLongFunctionX.class);
    }

    @Test
    public void testWrapXMethod() throws ParseException {
        assertThat(LBooleanToLongFunctionX.wrapX(opposite))
            .isInstanceOf(LBooleanToLongFunctionX.class);
    }


    @Test
    public void testWrapExceptionMethodWrapsTheException() throws ParseException {

        // given
        LBooleanToLongFunctionX<X> sutThrowing = LBooleanToLongFunctionX.lX((boolean b) -> {
            throw new UnsupportedOperationException();
        });

        // when
        LBooleanToLongFunctionX<X> wrapped = LBooleanToLongFunctionX.wrapException(sutThrowing, UnsupportedOperationException.class, null, t -> {
            throw new IllegalArgumentException(EXCEPTION_WAS_WRAPPED, t);
        });

        // then
        try {
            wrapped.doApplyAsLong(true);
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
        LBooleanToLongFunctionX<X> sutThrowing = LBooleanToLongFunctionX.lX((boolean b) -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LBooleanToLongFunctionX<X> wrapped = LBooleanToLongFunctionX.wrapException(sutThrowing, UnsupportedOperationException.class, null, t -> {
            throw new IllegalArgumentException(EXCEPTION_WAS_WRAPPED, t);
        });

        // then
        try {
            wrapped.doApplyAsLong(true);
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
        LBooleanToLongFunctionX<X> sutThrowing = LBooleanToLongFunctionX.lX((boolean b) -> {
            throw new UnsupportedOperationException();
        });

        // when
        LBooleanToLongFunctionX<X> wrapped = LBooleanToLongFunctionX.wrapException(sutThrowing, UnsupportedOperationException.class, null, t -> {
            return null;
        });

        // then
        try {
            wrapped.doApplyAsLong(true);
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
        LBooleanToLongFunctionX<X> sutO = (boolean b) -> {
                mainFunctionCalled.set(true);
                assertThat(b).isEqualTo(true);
                return (long)100;
        };

        LBooleanUnaryOperatorX<X> before1 = p0 -> {
            assertThat(p0).isEqualTo(true);
            beforeCalls.incrementAndGet();
            return true;
        };

        //when
        LBooleanToLongFunctionX<X> function = sutO.fromBoolean(before1);
        function.doApplyAsLong(true);

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(beforeCalls.get()).isEqualTo(1);
    }


    @Test
    public void testfrom() throws ParseException {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LBooleanToLongFunctionX<X> sutO = (boolean b) -> {
                mainFunctionCalled.set(true);
                assertThat(b).isEqualTo(true);
                return (long)100;
        };

        LPredicateX<Integer ,X> before1 = p0 -> {
            assertThat(p0).isEqualTo(Integer.valueOf(80));
            beforeCalls.incrementAndGet();
            return true;
        };

        //when
        LToLongFunctionX<Integer ,X> function = sutO.from(before1);
        function.doApplyAsLong((Integer )Integer.valueOf(80));

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
        LBooleanToLongFunctionX<X> sutO = (boolean b) -> {
                mainFunctionCalled.set(true);
                assertThat(b).isEqualTo(true);
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
        LBooleanFunctionX<Integer ,X> function = sutO.then(thenFunction);
        Integer  finalValue = function.doApply(true);

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
        LBooleanToLongFunctionX<X> sutO = (boolean b) -> {
                mainFunctionCalled.set(true);
                assertThat(b).isEqualTo(true);
                return (long)90;
        };

        LLongToByteFunctionX<X> thenFunction = p -> {
                thenFunctionCalled.set(true);
                // 
                assertThat(p).isEqualTo((long)90);
                // byte
                return (byte)100;
        };

        //when
        LBooleanToByteFunctionX<X> function = sutO.thenToByte(thenFunction);
        byte finalValue = function.doApplyAsByte(true);

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
        LBooleanToLongFunctionX<X> sutO = (boolean b) -> {
                mainFunctionCalled.set(true);
                assertThat(b).isEqualTo(true);
                return (long)90;
        };

        LLongToShortFunctionX<X> thenFunction = p -> {
                thenFunctionCalled.set(true);
                // 
                assertThat(p).isEqualTo((long)90);
                // short
                return (short)100;
        };

        //when
        LBooleanToShortFunctionX<X> function = sutO.thenToShort(thenFunction);
        short finalValue = function.doApplyAsShort(true);

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
        LBooleanToLongFunctionX<X> sutO = (boolean b) -> {
                mainFunctionCalled.set(true);
                assertThat(b).isEqualTo(true);
                return (long)90;
        };

        LLongToIntFunctionX<X> thenFunction = p -> {
                thenFunctionCalled.set(true);
                // 
                assertThat(p).isEqualTo((long)90);
                // int
                return (int)100;
        };

        //when
        LBooleanToIntFunctionX<X> function = sutO.thenToInt(thenFunction);
        int finalValue = function.doApplyAsInt(true);

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
        LBooleanToLongFunctionX<X> sutO = (boolean b) -> {
                mainFunctionCalled.set(true);
                assertThat(b).isEqualTo(true);
                return (long)90;
        };

        LLongUnaryOperatorX<X> thenFunction = p -> {
                thenFunctionCalled.set(true);
                // 
                assertThat(p).isEqualTo((long)90);
                // long
                return (long)100;
        };

        //when
        LBooleanToLongFunctionX<X> function = sutO.thenToLong(thenFunction);
        long finalValue = function.doApplyAsLong(true);

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
        LBooleanToLongFunctionX<X> sutO = (boolean b) -> {
                mainFunctionCalled.set(true);
                assertThat(b).isEqualTo(true);
                return (long)90;
        };

        LLongToFloatFunctionX<X> thenFunction = p -> {
                thenFunctionCalled.set(true);
                // 
                assertThat(p).isEqualTo((long)90);
                // float
                return (float)100;
        };

        //when
        LBooleanToFloatFunctionX<X> function = sutO.thenToFloat(thenFunction);
        float finalValue = function.doApplyAsFloat(true);

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
        LBooleanToLongFunctionX<X> sutO = (boolean b) -> {
                mainFunctionCalled.set(true);
                assertThat(b).isEqualTo(true);
                return (long)90;
        };

        LLongToDoubleFunctionX<X> thenFunction = p -> {
                thenFunctionCalled.set(true);
                // 
                assertThat(p).isEqualTo((long)90);
                // double
                return (double)100;
        };

        //when
        LBooleanToDoubleFunctionX<X> function = sutO.thenToDouble(thenFunction);
        double finalValue = function.doApplyAsDouble(true);

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
        LBooleanToLongFunctionX<X> sutO = (boolean b) -> {
                mainFunctionCalled.set(true);
                assertThat(b).isEqualTo(true);
                return (long)90;
        };

        LLongToCharFunctionX<X> thenFunction = p -> {
                thenFunctionCalled.set(true);
                // 
                assertThat(p).isEqualTo((long)90);
                // char
                return (char)100;
        };

        //when
        LBooleanToCharFunctionX<X> function = sutO.thenToChar(thenFunction);
        char finalValue = function.doApplyAsChar(true);

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
        LBooleanToLongFunctionX<X> sutO = (boolean b) -> {
                mainFunctionCalled.set(true);
                assertThat(b).isEqualTo(true);
                return (long)90;
        };

        LLongPredicateX<X> thenFunction = p -> {
                thenFunctionCalled.set(true);
                // 
                assertThat(p).isEqualTo((long)90);
                // boolean
                return true;
        };

        //when
        LBooleanUnaryOperatorX<X> function = sutO.thenToBoolean(thenFunction);
        boolean finalValue = function.doApplyAsBoolean(true);

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
            .isInstanceOf(LBooleanToLongFunction.class);
    }

    @Test
    public void testShoving() {
        assertThat(sut.shove())
            .isInstanceOf(LBooleanToLongFunction.class);
    }

    @Test
    public void testNestingX() {
        assertThat(sut.nestX())
            .isInstanceOf(LBooleanToLongFunctionX.class);
    }

    @Test
    public void testShovingX() {
        assertThat(sut.shoveX())
            .isInstanceOf(LBooleanToLongFunctionX.class);
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void testShove() {

        // given
        LBooleanToLongFunctionX<X> sutThrowing = LBooleanToLongFunctionX.lX((boolean b) -> {
            throw new UnsupportedOperationException();
        });

        // when
        sutThrowing.shove().doApplyAsLong(true);
    }

    @Test
    public void testHandleX() throws ParseException {

        // given
        LBooleanToLongFunctionX<X> sutThrowing = LBooleanToLongFunctionX.lX((boolean b) -> {
            throw new UnsupportedOperationException();
        });

        // when
        LBooleanToLongFunctionX<X> wrapped = sutThrowing.handleX(UnsupportedOperationException.class, t -> {
            throw new IllegalArgumentException(EXCEPTION_WAS_WRAPPED, t);
        });

        // then
        try {
            wrapped.doApplyAsLong(true);
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
                .contains("LBooleanToLongFunctionX: long doApplyAsLong(boolean b) throws X");
    }


    @Test
    public void isThrowing() {
        assertThat(sut.isThrowing())
            .isTrue();
    }


}
