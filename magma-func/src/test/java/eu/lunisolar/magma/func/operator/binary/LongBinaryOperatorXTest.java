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
 * Foobar is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with Foobar.  If not, see <http://www.gnu.org/licenses/>.
 */

package eu.lunisolar.magma.func.operator.binary;

import eu.lunisolar.magma.func.*; // NOSONAR
import javax.annotation.Nonnull; // NOSONAR
import javax.annotation.Nullable; // NOSONAR
import java.util.Objects;// NOSONAR
import eu.lunisolar.magma.basics.meta.*; // NOSONAR
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
public class LongBinaryOperatorXTest<X extends ParseException> {
    private static final String ORIGINAL_MESSAGE = "Original message";
    private static final String EXCEPTION_WAS_WRAPPED = "Exception was wrapped.";
    private static final String NO_EXCEPTION_WERE_THROWN = "No exception were thrown.";

    private long testValue = (long)100;



    private LongBinaryOperatorX<X> sut = new LongBinaryOperatorX(){
        public  long applyAsLong(long l1,long l2) throws ParseException {
            return testValue;
        }
    };

    private LongBinaryOperator opposite = new LongBinaryOperator(){
        public  long applyAsLong(long l1,long l2)  {
            return testValue;
        }
    };


    private java.util.function.LongBinaryOperator jre = (long l1,long l2) -> testValue;


    @Test
    public void testTheResult() throws ParseException {
        assertThat(sut.applyAsLong((long)100,(long)100))
            .isEqualTo(testValue);
    }

    @Test
    public void testNonNullShouldNotModifyValue() throws ParseException {
        assertThat(sut.nonNull((long)100,(long)100))
            .isEqualTo(testValue);
    }


    @Test
    public void testFunctionalInterfaceDescription() throws ParseException {
        assertThat(sut.functionalInterfaceDescription())
            .isEqualTo("LongBinaryOperatorX: long applyAsLong(long l1,long l2) throws X");
    }

    @Test
    public void testLXMethod() throws ParseException {
        assertThat(LongBinaryOperatorX.lX((long l1,long l2) -> testValue ))
            .isInstanceOf(LongBinaryOperatorX.class);
    }

    @Test
    public void testWrapXMethod() throws ParseException {
        assertThat(LongBinaryOperatorX.wrapX(opposite))
            .isInstanceOf(LongBinaryOperatorX.class);
    }

    @Test
    public void testWrapStdMethod() throws ParseException {
        assertThat(LongBinaryOperatorX.wrapStd(jre))
            .isInstanceOf(LongBinaryOperatorX.class);
    }


    @Test
    public void testWrapExceptionMethodWrapsTheException() throws ParseException {

        // given
        LongBinaryOperatorX<X> sutThrowing = LongBinaryOperatorX.lX((long l1,long l2) -> {
            throw new UnsupportedOperationException();
        });

        // when
        LongBinaryOperatorX<X> wrapped = LongBinaryOperatorX.wrapException(sutThrowing, UnsupportedOperationException.class, t -> {
            throw new IllegalArgumentException(EXCEPTION_WAS_WRAPPED, t);
        });

        // then
        try {
            wrapped.applyAsLong((long)100,(long)100);
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
        LongBinaryOperatorX<X> sutThrowing = LongBinaryOperatorX.lX((long l1,long l2) -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LongBinaryOperatorX<X> wrapped = LongBinaryOperatorX.wrapException(sutThrowing, UnsupportedOperationException.class, t -> {
            throw new IllegalArgumentException(EXCEPTION_WAS_WRAPPED, t);
        });

        // then
        try {
            wrapped.applyAsLong((long)100,(long)100);
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
        LongBinaryOperatorX<X> sutThrowing = LongBinaryOperatorX.lX((long l1,long l2) -> {
            throw new UnsupportedOperationException();
        });

        // when
        LongBinaryOperatorX<X> wrapped = LongBinaryOperatorX.wrapException(sutThrowing, UnsupportedOperationException.class, t -> {
            return null;
        });

        // then
        try {
            wrapped.applyAsLong((long)100,(long)100);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(ExceptionNotHandled.class)
                    .hasCauseExactlyInstanceOf(UnsupportedOperationException.class)
                    .hasMessage("Handler has not processed the exception.");
        }
    }


    @Test
    public void testMin() throws ParseException {
        //given
        long valueSmall = (long)100;
        long valueBig = (long)(valueSmall+10);

        //when
        LongBinaryOperatorX<X> min = LongBinaryOperatorX.min();

        //then
        assertThat(min.applyAsLong(valueSmall, valueBig))
                .isEqualTo(valueSmall);

        assertThat(min.applyAsLong(valueBig, valueSmall))
                .isEqualTo(valueSmall);
    }

    @Test
    public void testMax() throws ParseException {
        //given
        long valueSmall = (long)100;
        long valueBig = (long)(valueSmall+10);

        //when
        LongBinaryOperatorX<X> max = LongBinaryOperatorX.max();

        //then
        assertThat(max.applyAsLong(valueSmall, valueBig))
                .isEqualTo(valueBig);

        assertThat(max.applyAsLong(valueBig, valueSmall))
                .isEqualTo(valueBig);
    }


    // <editor-fold desc="compose (functional)">

    @Test
    public void testfromLong() throws ParseException {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LongBinaryOperatorX<X> sutO = (long l1,long l2) -> {
                mainFunctionCalled.set(true);
                assertThat(l1).isEqualTo((long)90);
                assertThat(l2).isEqualTo((long)91);
                return (long)100;
        };

        LongUnaryOperatorX<X> before1 = p0 -> {
            assertThat(p0).isEqualTo((long)80);
            beforeCalls.incrementAndGet();
            return (long)90;
        };
        LongUnaryOperatorX<X> before2 = p1 -> {
            assertThat(p1).isEqualTo((long)81);
            beforeCalls.incrementAndGet();
            return (long)91;
        };

        //when
        LongBinaryOperatorX<X> function = sutO.fromLong(before1,before2);
        function.applyAsLong((long)80,(long)81);

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(beforeCalls.get()).isEqualTo(2);
    }


    @Test
    public void testfrom() throws ParseException {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LongBinaryOperatorX<X> sutO = (long l1,long l2) -> {
                mainFunctionCalled.set(true);
                assertThat(l1).isEqualTo((long)90);
                assertThat(l2).isEqualTo((long)91);
                return (long)100;
        };

        ToLongFunctionX<Integer ,X> before1 = p0 -> {
            assertThat(p0).isEqualTo(Integer.valueOf(80));
            beforeCalls.incrementAndGet();
            return (long)90;
        };
        ToLongFunctionX<Integer ,X> before2 = p1 -> {
            assertThat(p1).isEqualTo(Integer.valueOf(81));
            beforeCalls.incrementAndGet();
            return (long)91;
        };

        //when
        ToLongBiFunctionX<Integer ,Integer ,X> function = sutO.from(before1,before2);
        function.applyAsLong((Integer )Integer.valueOf(80),(Integer )Integer.valueOf(81));

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(beforeCalls.get()).isEqualTo(2);
    }

    // </editor-fold>


    // <editor-fold desc="then (functional)">

    @Test
    public void testThen0() throws ParseException  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);


        //given (+ some assertions)
        LongBinaryOperatorX<X> sutO = (long l1,long l2) -> {
                mainFunctionCalled.set(true);
                assertThat(l1).isEqualTo((long)80);
                assertThat(l2).isEqualTo((long)81);
                return (long)90;
        };

        LongFunctionX<Integer ,X> thenFunction = p -> {
                thenFunctionCalled.set(true);
                // 
                assertThat(p).isEqualTo((long)90);
                // V
                return Integer.valueOf(100);
        };

        //when
        LongBiFunctionX<Integer ,X> function = sutO.then(thenFunction);
        Integer  finalValue = function.apply((long)80,(long)81);

        //then - finals
        assertThat(finalValue).isEqualTo(Integer.valueOf(100));
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    // </editor-fold>
    @Test
    public void testStd() {
        assertThat(sut.std()).isInstanceOf(java.util.function.LongBinaryOperator.class);
    }

    @Test
    public void testNonThrowing() {
        assertThat(sut.nonThrowing()).isInstanceOf(LongBinaryOperator.class);
    }

    @Test
    public void testUncheck() {
        assertThat(sut.uncheck()).isInstanceOf(LongBinaryOperatorX.class);
    }


    @Test
    public void testHandle() throws ParseException {

        // given
        LongBinaryOperatorX<X> sutThrowing = LongBinaryOperatorX.lX((long l1,long l2) -> {
            throw new UnsupportedOperationException();
        });

        // when
        LongBinaryOperatorX<X> wrapped = sutThrowing.handle(UnsupportedOperationException.class, t -> {
            throw new IllegalArgumentException(EXCEPTION_WAS_WRAPPED, t);
        });

        // then
        try {
            wrapped.applyAsLong((long)100,(long)100);
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
                .contains("LongBinaryOperatorX: long applyAsLong(long l1,long l2) throws X");
    }

}
