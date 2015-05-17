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
public class LLongToIntFunctionXTest<X extends ParseException> {
    private static final String ORIGINAL_MESSAGE = "Original message";
    private static final String EXCEPTION_WAS_WRAPPED = "Exception was wrapped.";
    private static final String NO_EXCEPTION_WERE_THROWN = "No exception were thrown.";

    private int testValue = (int)100;



    private LLongToIntFunctionX<X> sut = new LLongToIntFunctionX(){
        public  int doApplyAsInt(long l) throws ParseException {
            return testValue;
        }
    };

    private LLongToIntFunction opposite = new LLongToIntFunction(){
        public  int doApplyAsInt(long l)  {
            return testValue;
        }
    };


    private java.util.function.LongToIntFunction jre = (long l) -> testValue;


    private LLongToIntFunctionX<ParseException> sutAlwaysThrowing = LLongToIntFunctionX.lX((long l) -> {
            throw new ParseException(ORIGINAL_MESSAGE, 0);
    });

    private LLongToIntFunctionX<RuntimeException> sutAlwaysThrowingUnckeck = LLongToIntFunctionX.lX((long l) -> {
            throw new IndexOutOfBoundsException(ORIGINAL_MESSAGE);
    });


    @Test
    public void testTheResult() throws ParseException {
        assertThat(sut.doApplyAsInt((long)100))
            .isEqualTo(testValue);
    }

    @Test
    public void testNonNullDoApplyAsInt() throws ParseException {
        assertThat(sut.nonNullDoApplyAsInt((long)100))
            .isEqualTo(testValue);
    }

    @Test
    public void testNestingDoApplyAsInt_checked() throws ParseException {

        // then
        try {
            sutAlwaysThrowing.nestingDoApplyAsInt((long)100);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(NestedException.class)
                    .hasCauseExactlyInstanceOf(ParseException.class)
                    .hasMessage(ORIGINAL_MESSAGE);
        }
    }

    @Test
    public void testNestingDoApplyAsInt_unckeck() throws ParseException {

        // then
        try {
            sutAlwaysThrowingUnckeck.nestingDoApplyAsInt((long)100);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                    .hasNoCause()
                    .hasMessage(ORIGINAL_MESSAGE);
        }
    }

    @Test
    public void testShovingDoApplyAsInt_checked() throws ParseException {

        // then
        try {
            sutAlwaysThrowing.shovingDoApplyAsInt((long)100);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(ParseException.class)
                    .hasNoCause()
                    .hasMessage(ORIGINAL_MESSAGE);
        }
    }

    @Test
    public void testShovingDoApplyAsInt_unckeck() throws ParseException {

        // then
        try {
            sutAlwaysThrowingUnckeck.shovingDoApplyAsInt((long)100);
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
            .isEqualTo("LLongToIntFunctionX: int doApplyAsInt(long l) throws X");
    }

    @Test
    public void testLXMethod() throws ParseException {
        assertThat(LLongToIntFunctionX.lX((long l) -> testValue ))
            .isInstanceOf(LLongToIntFunctionX.class);
    }

    @Test
    public void testWrapXMethod() throws ParseException {
        assertThat(LLongToIntFunctionX.wrapX(opposite))
            .isInstanceOf(LLongToIntFunctionX.class);
    }

    @Test
    public void testWrapStdMethod() throws ParseException {
        assertThat(LLongToIntFunctionX.wrap(jre))
            .isInstanceOf(LLongToIntFunctionX.class);
    }


    @Test
    public void testWrapExceptionMethodWrapsTheException() throws ParseException {

        // given
        LLongToIntFunctionX<X> sutThrowing = LLongToIntFunctionX.lX((long l) -> {
            throw new UnsupportedOperationException();
        });

        // when
        LLongToIntFunctionX<X> wrapped = LLongToIntFunctionX.wrapException(sutThrowing, UnsupportedOperationException.class, null, t -> {
            throw new IllegalArgumentException(EXCEPTION_WAS_WRAPPED, t);
        });

        // then
        try {
            wrapped.doApplyAsInt((long)100);
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
        LLongToIntFunctionX<X> sutThrowing = LLongToIntFunctionX.lX((long l) -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LLongToIntFunctionX<X> wrapped = LLongToIntFunctionX.wrapException(sutThrowing, UnsupportedOperationException.class, null, t -> {
            throw new IllegalArgumentException(EXCEPTION_WAS_WRAPPED, t);
        });

        // then
        try {
            wrapped.doApplyAsInt((long)100);
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
        LLongToIntFunctionX<X> sutThrowing = LLongToIntFunctionX.lX((long l) -> {
            throw new UnsupportedOperationException();
        });

        // when
        LLongToIntFunctionX<X> wrapped = LLongToIntFunctionX.wrapException(sutThrowing, UnsupportedOperationException.class, null, t -> {
            return null;
        });

        // then
        try {
            wrapped.doApplyAsInt((long)100);
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
    public void testfromLong() throws ParseException {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LLongToIntFunctionX<X> sutO = (long l) -> {
                mainFunctionCalled.set(true);
                assertThat(l).isEqualTo((long)90);
                return (int)100;
        };

        LLongUnaryOperatorX<X> before1 = p0 -> {
            assertThat(p0).isEqualTo((long)80);
            beforeCalls.incrementAndGet();
            return (long)90;
        };

        //when
        LLongToIntFunctionX<X> function = sutO.fromLong(before1);
        function.doApplyAsInt((long)80);

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(beforeCalls.get()).isEqualTo(1);
    }


    @Test
    public void testfrom() throws ParseException {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LLongToIntFunctionX<X> sutO = (long l) -> {
                mainFunctionCalled.set(true);
                assertThat(l).isEqualTo((long)90);
                return (int)100;
        };

        LToLongFunctionX<Integer ,X> before1 = p0 -> {
            assertThat(p0).isEqualTo(Integer.valueOf(80));
            beforeCalls.incrementAndGet();
            return (long)90;
        };

        //when
        LToIntFunctionX<Integer ,X> function = sutO.from(before1);
        function.doApplyAsInt((Integer )Integer.valueOf(80));

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
        LLongToIntFunctionX<X> sutO = (long l) -> {
                mainFunctionCalled.set(true);
                assertThat(l).isEqualTo((long)80);
                return (int)90;
        };

        LIntFunctionX<Integer ,X> thenFunction = p -> {
                thenFunctionCalled.set(true);
                // 
                assertThat(p).isEqualTo((int)90);
                // V
                return Integer.valueOf(100);
        };

        //when
        LLongFunctionX<Integer ,X> function = sutO.then(thenFunction);
        Integer  finalValue = function.doApply((long)80);

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
        LLongToIntFunctionX<X> sutO = (long l) -> {
                mainFunctionCalled.set(true);
                assertThat(l).isEqualTo((long)80);
                return (int)90;
        };

        LIntToByteFunctionX<X> thenFunction = p -> {
                thenFunctionCalled.set(true);
                // 
                assertThat(p).isEqualTo((int)90);
                // byte
                return (byte)100;
        };

        //when
        LLongToByteFunctionX<X> function = sutO.thenToByte(thenFunction);
        byte finalValue = function.doApplyAsByte((long)80);

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
        LLongToIntFunctionX<X> sutO = (long l) -> {
                mainFunctionCalled.set(true);
                assertThat(l).isEqualTo((long)80);
                return (int)90;
        };

        LIntToShortFunctionX<X> thenFunction = p -> {
                thenFunctionCalled.set(true);
                // 
                assertThat(p).isEqualTo((int)90);
                // short
                return (short)100;
        };

        //when
        LLongToShortFunctionX<X> function = sutO.thenToShort(thenFunction);
        short finalValue = function.doApplyAsShort((long)80);

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
        LLongToIntFunctionX<X> sutO = (long l) -> {
                mainFunctionCalled.set(true);
                assertThat(l).isEqualTo((long)80);
                return (int)90;
        };

        LIntUnaryOperatorX<X> thenFunction = p -> {
                thenFunctionCalled.set(true);
                // 
                assertThat(p).isEqualTo((int)90);
                // int
                return (int)100;
        };

        //when
        LLongToIntFunctionX<X> function = sutO.thenToInt(thenFunction);
        int finalValue = function.doApplyAsInt((long)80);

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
        LLongToIntFunctionX<X> sutO = (long l) -> {
                mainFunctionCalled.set(true);
                assertThat(l).isEqualTo((long)80);
                return (int)90;
        };

        LIntToLongFunctionX<X> thenFunction = p -> {
                thenFunctionCalled.set(true);
                // 
                assertThat(p).isEqualTo((int)90);
                // long
                return (long)100;
        };

        //when
        LLongUnaryOperatorX<X> function = sutO.thenToLong(thenFunction);
        long finalValue = function.doApplyAsLong((long)80);

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
        LLongToIntFunctionX<X> sutO = (long l) -> {
                mainFunctionCalled.set(true);
                assertThat(l).isEqualTo((long)80);
                return (int)90;
        };

        LIntToFloatFunctionX<X> thenFunction = p -> {
                thenFunctionCalled.set(true);
                // 
                assertThat(p).isEqualTo((int)90);
                // float
                return (float)100;
        };

        //when
        LLongToFloatFunctionX<X> function = sutO.thenToFloat(thenFunction);
        float finalValue = function.doApplyAsFloat((long)80);

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
        LLongToIntFunctionX<X> sutO = (long l) -> {
                mainFunctionCalled.set(true);
                assertThat(l).isEqualTo((long)80);
                return (int)90;
        };

        LIntToDoubleFunctionX<X> thenFunction = p -> {
                thenFunctionCalled.set(true);
                // 
                assertThat(p).isEqualTo((int)90);
                // double
                return (double)100;
        };

        //when
        LLongToDoubleFunctionX<X> function = sutO.thenToDouble(thenFunction);
        double finalValue = function.doApplyAsDouble((long)80);

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
        LLongToIntFunctionX<X> sutO = (long l) -> {
                mainFunctionCalled.set(true);
                assertThat(l).isEqualTo((long)80);
                return (int)90;
        };

        LIntToCharFunctionX<X> thenFunction = p -> {
                thenFunctionCalled.set(true);
                // 
                assertThat(p).isEqualTo((int)90);
                // char
                return (char)100;
        };

        //when
        LLongToCharFunctionX<X> function = sutO.thenToChar(thenFunction);
        char finalValue = function.doApplyAsChar((long)80);

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
        LLongToIntFunctionX<X> sutO = (long l) -> {
                mainFunctionCalled.set(true);
                assertThat(l).isEqualTo((long)80);
                return (int)90;
        };

        LIntPredicateX<X> thenFunction = p -> {
                thenFunctionCalled.set(true);
                // 
                assertThat(p).isEqualTo((int)90);
                // boolean
                return true;
        };

        //when
        LLongPredicateX<X> function = sutO.thenToBoolean(thenFunction);
        boolean finalValue = function.doTest((long)80);

        //then - finals
        assertThat(finalValue).isEqualTo(true);
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    // </editor-fold>
//
//    @Test
//    public void testStd() {
//        assertThat(sut.std()).isInstanceOf(java.util.function.LongToIntFunction.class);
//    }
//
//
    @Test
    public void testNesting() {
        assertThat(sut.nest())
            .isInstanceOf(LLongToIntFunction.class);
    }

    @Test
    public void testShoving() {
        assertThat(sut.shove())
            .isInstanceOf(LLongToIntFunction.class);
    }

    @Test
    public void testNestingX() {
        assertThat(sut.nestX())
            .isInstanceOf(LLongToIntFunctionX.class);
    }

    @Test
    public void testShovingX() {
        assertThat(sut.shoveX())
            .isInstanceOf(LLongToIntFunctionX.class);
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void testShove() {

        // given
        LLongToIntFunctionX<X> sutThrowing = LLongToIntFunctionX.lX((long l) -> {
            throw new UnsupportedOperationException();
        });

        // when
        sutThrowing.shove().doApplyAsInt((long)100);
    }

    @Test
    public void testHandleX() throws ParseException {

        // given
        LLongToIntFunctionX<X> sutThrowing = LLongToIntFunctionX.lX((long l) -> {
            throw new UnsupportedOperationException();
        });

        // when
        LLongToIntFunctionX<X> wrapped = sutThrowing.handleX(UnsupportedOperationException.class, t -> {
            throw new IllegalArgumentException(EXCEPTION_WAS_WRAPPED, t);
        });

        // then
        try {
            wrapped.doApplyAsInt((long)100);
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
                .contains("LLongToIntFunctionX: int doApplyAsInt(long l) throws X");
    }


    @Test
    public void isThrowing() {
        assertThat(sut.isThrowing())
            .isTrue();
    }


}
