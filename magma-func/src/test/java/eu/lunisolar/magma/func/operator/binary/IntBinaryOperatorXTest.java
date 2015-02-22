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
public class IntBinaryOperatorXTest<X extends ParseException> {
    private static final String ORIGINAL_MESSAGE = "Original message";
    private static final String EXCEPTION_WAS_WRAPPED = "Exception was wrapped.";
    private static final String NO_EXCEPTION_WERE_THROWN = "No exception were thrown.";

    private int testValue = (int)100;



    private IntBinaryOperatorX<X> sut = new IntBinaryOperatorX(){
        public  int applyAsInt(int i1,int i2) throws ParseException {
            return testValue;
        }
    };

    private IntBinaryOperator opposite = new IntBinaryOperator(){
        public  int applyAsInt(int i1,int i2)  {
            return testValue;
        }
    };


    private java.util.function.IntBinaryOperator jre = (int i1,int i2) -> testValue;


    @Test
    public void testTheResult() throws ParseException {
        assertThat(sut.applyAsInt((int)100,(int)100))
            .isEqualTo(testValue);
    }

    @Test
    public void testNonNullShouldNotModifyValue() throws ParseException {
        assertThat(sut.nonNull((int)100,(int)100))
            .isEqualTo(testValue);
    }


    @Test
    public void testFunctionalInterfaceDescription() throws ParseException {
        assertThat(sut.functionalInterfaceDescription())
            .isEqualTo("IntBinaryOperatorX: int applyAsInt(int i1,int i2) throws X");
    }

    @Test
    public void testLXMethod() throws ParseException {
        assertThat(IntBinaryOperatorX.lX((int i1,int i2) -> testValue ))
            .isInstanceOf(IntBinaryOperatorX.class);
    }

    @Test
    public void testWrapXMethod() throws ParseException {
        assertThat(IntBinaryOperatorX.wrapX(opposite))
            .isInstanceOf(IntBinaryOperatorX.class);
    }

    @Test
    public void testWrapStdMethod() throws ParseException {
        assertThat(IntBinaryOperatorX.wrapStd(jre))
            .isInstanceOf(IntBinaryOperatorX.class);
    }


    @Test
    public void testWrapExceptionMethodWrapsTheException() throws ParseException {

        // given
        IntBinaryOperatorX<X> sutThrowing = IntBinaryOperatorX.lX((int i1,int i2) -> {
            throw new UnsupportedOperationException();
        });

        // when
        IntBinaryOperatorX<X> wrapped = IntBinaryOperatorX.wrapException(sutThrowing, UnsupportedOperationException.class, t -> {
            throw new IllegalArgumentException(EXCEPTION_WAS_WRAPPED, t);
        });

        // then
        try {
            wrapped.applyAsInt((int)100,(int)100);
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
        IntBinaryOperatorX<X> sutThrowing = IntBinaryOperatorX.lX((int i1,int i2) -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        IntBinaryOperatorX<X> wrapped = IntBinaryOperatorX.wrapException(sutThrowing, UnsupportedOperationException.class, t -> {
            throw new IllegalArgumentException(EXCEPTION_WAS_WRAPPED, t);
        });

        // then
        try {
            wrapped.applyAsInt((int)100,(int)100);
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
        IntBinaryOperatorX<X> sutThrowing = IntBinaryOperatorX.lX((int i1,int i2) -> {
            throw new UnsupportedOperationException();
        });

        // when
        IntBinaryOperatorX<X> wrapped = IntBinaryOperatorX.wrapException(sutThrowing, UnsupportedOperationException.class, t -> {
            return null;
        });

        // then
        try {
            wrapped.applyAsInt((int)100,(int)100);
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
        int valueSmall = (int)100;
        int valueBig = (int)(valueSmall+10);

        //when
        IntBinaryOperatorX<X> min = IntBinaryOperatorX.min();

        //then
        assertThat(min.applyAsInt(valueSmall, valueBig))
                .isEqualTo(valueSmall);

        assertThat(min.applyAsInt(valueBig, valueSmall))
                .isEqualTo(valueSmall);
    }

    @Test
    public void testMax() throws ParseException {
        //given
        int valueSmall = (int)100;
        int valueBig = (int)(valueSmall+10);

        //when
        IntBinaryOperatorX<X> max = IntBinaryOperatorX.max();

        //then
        assertThat(max.applyAsInt(valueSmall, valueBig))
                .isEqualTo(valueBig);

        assertThat(max.applyAsInt(valueBig, valueSmall))
                .isEqualTo(valueBig);
    }


    // <editor-fold desc="compose (functional)">

    @Test
    public void testfromInt() throws ParseException {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        IntBinaryOperatorX<X> sutO = (int i1,int i2) -> {
                mainFunctionCalled.set(true);
                assertThat(i1).isEqualTo((int)90);
                assertThat(i2).isEqualTo((int)91);
                return (int)100;
        };

        IntUnaryOperatorX<X> before1 = p0 -> {
            assertThat(p0).isEqualTo((int)80);
            beforeCalls.incrementAndGet();
            return (int)90;
        };
        IntUnaryOperatorX<X> before2 = p1 -> {
            assertThat(p1).isEqualTo((int)81);
            beforeCalls.incrementAndGet();
            return (int)91;
        };

        //when
        IntBinaryOperatorX<X> function = sutO.fromInt(before1,before2);
        function.applyAsInt((int)80,(int)81);

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(beforeCalls.get()).isEqualTo(2);
    }


    @Test
    public void testfrom() throws ParseException {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        IntBinaryOperatorX<X> sutO = (int i1,int i2) -> {
                mainFunctionCalled.set(true);
                assertThat(i1).isEqualTo((int)90);
                assertThat(i2).isEqualTo((int)91);
                return (int)100;
        };

        ToIntFunctionX<Integer ,X> before1 = p0 -> {
            assertThat(p0).isEqualTo(Integer.valueOf(80));
            beforeCalls.incrementAndGet();
            return (int)90;
        };
        ToIntFunctionX<Integer ,X> before2 = p1 -> {
            assertThat(p1).isEqualTo(Integer.valueOf(81));
            beforeCalls.incrementAndGet();
            return (int)91;
        };

        //when
        ToIntBiFunctionX<Integer ,Integer ,X> function = sutO.from(before1,before2);
        function.applyAsInt((Integer )Integer.valueOf(80),(Integer )Integer.valueOf(81));

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
        IntBinaryOperatorX<X> sutO = (int i1,int i2) -> {
                mainFunctionCalled.set(true);
                assertThat(i1).isEqualTo((int)80);
                assertThat(i2).isEqualTo((int)81);
                return (int)90;
        };

        IntFunctionX<Integer ,X> thenFunction = p -> {
                thenFunctionCalled.set(true);
                // 
                assertThat(p).isEqualTo((int)90);
                // V
                return Integer.valueOf(100);
        };

        //when
        IntBiFunctionX<Integer ,X> function = sutO.then(thenFunction);
        Integer  finalValue = function.apply((int)80,(int)81);

        //then - finals
        assertThat(finalValue).isEqualTo(Integer.valueOf(100));
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    // </editor-fold>
    @Test
    public void testStd() {
        assertThat(sut.std()).isInstanceOf(java.util.function.IntBinaryOperator.class);
    }

    @Test
    public void testNonThrowing() {
        assertThat(sut.nonThrowing()).isInstanceOf(IntBinaryOperator.class);
    }

    @Test
    public void testUncheck() {
        assertThat(sut.uncheck()).isInstanceOf(IntBinaryOperatorX.class);
    }


    @Test
    public void testHandle() throws ParseException {

        // given
        IntBinaryOperatorX<X> sutThrowing = IntBinaryOperatorX.lX((int i1,int i2) -> {
            throw new UnsupportedOperationException();
        });

        // when
        IntBinaryOperatorX<X> wrapped = sutThrowing.handle(UnsupportedOperationException.class, t -> {
            throw new IllegalArgumentException(EXCEPTION_WAS_WRAPPED, t);
        });

        // then
        try {
            wrapped.applyAsInt((int)100,(int)100);
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
                .contains("IntBinaryOperatorX: int applyAsInt(int i1,int i2) throws X");
    }

}
