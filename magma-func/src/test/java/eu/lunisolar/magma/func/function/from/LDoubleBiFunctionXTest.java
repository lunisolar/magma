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

package eu.lunisolar.magma.func.function.from;

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
public class LDoubleBiFunctionXTest<R,X extends ParseException> {
    private static final String ORIGINAL_MESSAGE = "Original message";
    private static final String EXCEPTION_WAS_WRAPPED = "Exception was wrapped.";
    private static final String NO_EXCEPTION_WERE_THROWN = "No exception were thrown.";

    private Object  testValue = (R)Integer.valueOf(100);



    private LDoubleBiFunctionX<R,X> sut = new LDoubleBiFunctionX(){
        public @Nullable Object  apply(double d1,double d2) throws ParseException {
            return testValue;
        }
    };

    private LDoubleBiFunction<R> opposite = new LDoubleBiFunction(){
        public @Nullable Object  apply(double d1,double d2)  {
            return testValue;
        }
    };

    private LDoubleBiFunctionX<R,X> sutNull = new LDoubleBiFunctionX(){
        public @Nullable Object  apply(double d1,double d2) throws ParseException {
            return null;
        }
    };



    @Test
    public void testTheResult() throws ParseException {
        assertThat(sut.apply((double)100,(double)100))
            .isSameAs(testValue);
    }

    @Test
    public void testNonNullShouldNotModifyValue() throws ParseException {
        assertThat(sut.nonNull((double)100,(double)100))
            .isSameAs(testValue);
    }

    @Test(expectedExceptions=NullPointerException.class, expectedExceptionsMessageRegExp="\\QEvaluated value by nonNull() method cannot be null (LDoubleBiFunctionX: R apply(double d1,double d2) throws X).\\E")
    public void testNonNullCapturesNull() throws ParseException {
        sutNull.nonNull((double)100,(double)100);
    }


    @Test
    public void testFunctionalInterfaceDescription() throws ParseException {
        assertThat(sut.functionalInterfaceDescription())
            .isEqualTo("LDoubleBiFunctionX: R apply(double d1,double d2) throws X");
    }

    @Test
    public void testLXMethod() throws ParseException {
        assertThat(LDoubleBiFunctionX.lX((double d1,double d2) -> testValue ))
            .isInstanceOf(LDoubleBiFunctionX.class);
    }

    @Test
    public void testWrapXMethod() throws ParseException {
        assertThat(LDoubleBiFunctionX.wrapX(opposite))
            .isInstanceOf(LDoubleBiFunctionX.class);
    }


    @Test
    public void testWrapExceptionMethodWrapsTheException() throws ParseException {

        // given
        LDoubleBiFunctionX<R,X> sutThrowing = LDoubleBiFunctionX.lX((double d1,double d2) -> {
            throw new UnsupportedOperationException();
        });

        // when
        LDoubleBiFunctionX<R,X> wrapped = LDoubleBiFunctionX.wrapException(sutThrowing, UnsupportedOperationException.class, null, t -> {
            throw new IllegalArgumentException(EXCEPTION_WAS_WRAPPED, t);
        });

        // then
        try {
            wrapped.apply((double)100,(double)100);
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
        LDoubleBiFunctionX<R,X> sutThrowing = LDoubleBiFunctionX.lX((double d1,double d2) -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LDoubleBiFunctionX<R,X> wrapped = LDoubleBiFunctionX.wrapException(sutThrowing, UnsupportedOperationException.class, null, t -> {
            throw new IllegalArgumentException(EXCEPTION_WAS_WRAPPED, t);
        });

        // then
        try {
            wrapped.apply((double)100,(double)100);
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
        LDoubleBiFunctionX<R,X> sutThrowing = LDoubleBiFunctionX.lX((double d1,double d2) -> {
            throw new UnsupportedOperationException();
        });

        // when
        LDoubleBiFunctionX<R,X> wrapped = LDoubleBiFunctionX.wrapException(sutThrowing, UnsupportedOperationException.class, null, t -> {
            return null;
        });

        // then
        try {
            wrapped.apply((double)100,(double)100);
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
    public void testfromDouble() throws ParseException {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LDoubleBiFunctionX<Integer ,X> sutO = (double d1,double d2) -> {
                mainFunctionCalled.set(true);
                assertThat(d1).isEqualTo((double)90);
                assertThat(d2).isEqualTo((double)91);
                return 9;
        };

        LDoubleUnaryOperatorX<X> before1 = p0 -> {
            assertThat(p0).isEqualTo((double)80);
            beforeCalls.incrementAndGet();
            return (double)90;
        };
        LDoubleUnaryOperatorX<X> before2 = p1 -> {
            assertThat(p1).isEqualTo((double)81);
            beforeCalls.incrementAndGet();
            return (double)91;
        };

        //when
        LDoubleBiFunctionX<Integer ,X> function = sutO.fromDouble(before1,before2);
        function.apply((double)80,(double)81);

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(beforeCalls.get()).isEqualTo(2);
    }


    @Test
    public void testfrom() throws ParseException {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LDoubleBiFunctionX<Integer ,X> sutO = (double d1,double d2) -> {
                mainFunctionCalled.set(true);
                assertThat(d1).isEqualTo((double)90);
                assertThat(d2).isEqualTo((double)91);
                return 9;
        };

        LToDoubleFunctionX<Integer ,X> before1 = p0 -> {
            assertThat(p0).isEqualTo(Integer.valueOf(80));
            beforeCalls.incrementAndGet();
            return (double)90;
        };
        LToDoubleFunctionX<Integer ,X> before2 = p1 -> {
            assertThat(p1).isEqualTo(Integer.valueOf(81));
            beforeCalls.incrementAndGet();
            return (double)91;
        };

        //when
        LBiFunctionX<Integer ,Integer ,Integer ,X> function = sutO.from(before1,before2);
        function.apply((Integer )Integer.valueOf(80),(Integer )Integer.valueOf(81));

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
        LDoubleBiFunctionX<Integer ,X> sutO = (double d1,double d2) -> {
                mainFunctionCalled.set(true);
                assertThat(d1).isEqualTo((double)80);
                assertThat(d2).isEqualTo((double)81);
                return Integer.valueOf(90);
        };

        LFunctionX<Integer ,Integer ,X> thenFunction = p -> {
                thenFunctionCalled.set(true);
                // R
                assertThat(p).isEqualTo(Integer.valueOf(90));
                // V
                return Integer.valueOf(100);
        };

        //when
        LDoubleBiFunctionX<Integer ,X> function = sutO.then(thenFunction);
        Integer  finalValue = function.apply((double)80,(double)81);

        //then - finals
        assertThat(finalValue).isEqualTo(Integer.valueOf(100));
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    @Test
    public void testThen1() throws ParseException  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);


        //given (+ some assertions)
        LDoubleBiFunctionX<Integer ,X> sutO = (double d1,double d2) -> {
                mainFunctionCalled.set(true);
                assertThat(d1).isEqualTo((double)80);
                assertThat(d2).isEqualTo((double)81);
                return Integer.valueOf(90);
        };

        LConsumerX<Integer ,X> thenFunction = p -> {
                thenFunctionCalled.set(true);
                // R
                assertThat(p).isEqualTo(Integer.valueOf(90));
        };

        //when
        LDoubleBiConsumerX<X> function = sutO.then(thenFunction);
        function.accept((double)80,(double)81);

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    // </editor-fold>
    @Test
    public void testNonThrowing() {
        assertThat(sut.nonThrowing()).isInstanceOf(LDoubleBiFunction.class);
    }

    @Test
    public void testUncheck() {
        assertThat(sut.uncheck()).isInstanceOf(LDoubleBiFunctionX.class);
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void testShove() {

        // given
        LDoubleBiFunctionX<R,X> sutThrowing = LDoubleBiFunctionX.lX((double d1,double d2) -> {
            throw new UnsupportedOperationException();
        });

        // when
        sutThrowing.shove().apply((double)100,(double)100);
    }

    @Test
    public void testHandleX() throws ParseException {

        // given
        LDoubleBiFunctionX<R,X> sutThrowing = LDoubleBiFunctionX.lX((double d1,double d2) -> {
            throw new UnsupportedOperationException();
        });

        // when
        LDoubleBiFunctionX<R,X> wrapped = sutThrowing.handleX(UnsupportedOperationException.class, t -> {
            throw new IllegalArgumentException(EXCEPTION_WAS_WRAPPED, t);
        });

        // then
        try {
            wrapped.apply((double)100,(double)100);
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
                .contains("LDoubleBiFunctionX: R apply(double d1,double d2) throws X");
    }


    @Test
    public void isThrowing() {
        assertThat(sut.isThrowing())
            .isTrue();
    }


}