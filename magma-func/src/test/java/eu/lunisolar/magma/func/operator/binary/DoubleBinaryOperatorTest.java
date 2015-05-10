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

package eu.lunisolar.magma.func.operator.binary;

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
public class DoubleBinaryOperatorTest<X extends ParseException> {
    private static final String ORIGINAL_MESSAGE = "Original message";
    private static final String EXCEPTION_WAS_WRAPPED = "Exception was wrapped.";
    private static final String NO_EXCEPTION_WERE_THROWN = "No exception were thrown.";

    private double testValue = (double)100;



    private DoubleBinaryOperator sut = new DoubleBinaryOperator(){
        public  double applyAsDouble(double d1,double d2)  {
            return testValue;
        }
    };

    private DoubleBinaryOperatorX<X> opposite = new DoubleBinaryOperatorX(){
        public  double applyAsDouble(double d1,double d2) throws ParseException {
            return testValue;
        }
    };


    private java.util.function.DoubleBinaryOperator jre = (double d1,double d2) -> testValue;


    @Test
    public void testTheResult() throws ParseException {
        assertThat(sut.applyAsDouble((double)100,(double)100))
            .isEqualTo(testValue);
    }

    @Test
    public void testNonNullShouldNotModifyValue() throws ParseException {
        assertThat(sut.nonNull((double)100,(double)100))
            .isEqualTo(testValue);
    }


    @Test
    public void testFunctionalInterfaceDescription() throws ParseException {
        assertThat(sut.functionalInterfaceDescription())
            .isEqualTo("DoubleBinaryOperator: double applyAsDouble(double d1,double d2)");
    }

    @Test
    public void testLMethod() throws ParseException {
        assertThat(DoubleBinaryOperator.l((double d1,double d2) -> testValue ))
            .isInstanceOf(DoubleBinaryOperator.class);
    }

    @Test
    public void testWrapMethod() throws ParseException {
        assertThat(DoubleBinaryOperator.wrap(opposite))
            .isInstanceOf(DoubleBinaryOperator.class);
    }

    @Test
    public void testWrapStdMethod() throws ParseException {
        assertThat(DoubleBinaryOperator.wrapStd(jre))
            .isInstanceOf(DoubleBinaryOperator.class);
    }

    @Test
    public void testWrapMethodDoNotWrapsRuntimeException() throws ParseException {
        // given
        DoubleBinaryOperatorX<X> sutThrowing = DoubleBinaryOperatorX.lX((double d1,double d2) -> {
            throw new UnsupportedOperationException(ORIGINAL_MESSAGE);
        });

        // when
        DoubleBinaryOperator wrapped = DoubleBinaryOperator.wrap(sutThrowing);

        // then
        try {
            wrapped.applyAsDouble((double)100,(double)100);
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
        DoubleBinaryOperatorX<ParseException> sutThrowing = DoubleBinaryOperatorX.lX((double d1,double d2) -> {
            throw new ParseException(ORIGINAL_MESSAGE, 0);
        });

        // when
        DoubleBinaryOperator wrapped = DoubleBinaryOperator.wrap(sutThrowing);

        // then
        try {
            wrapped.applyAsDouble((double)100,(double)100);
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
        DoubleBinaryOperator sutThrowing = DoubleBinaryOperator.l((double d1,double d2) -> {
            throw new UnsupportedOperationException();
        });

        // when
        DoubleBinaryOperator wrapped = DoubleBinaryOperator.wrapException(sutThrowing, UnsupportedOperationException.class, null, t -> {
            throw new IllegalArgumentException(EXCEPTION_WAS_WRAPPED, t);
        });

        // then
        try {
            wrapped.applyAsDouble((double)100,(double)100);
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
        DoubleBinaryOperator sutThrowing = DoubleBinaryOperator.l((double d1,double d2) -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        DoubleBinaryOperator wrapped = DoubleBinaryOperator.wrapException(sutThrowing, UnsupportedOperationException.class, null, t -> {
            throw new IllegalArgumentException(EXCEPTION_WAS_WRAPPED, t);
        });

        // then
        try {
            wrapped.applyAsDouble((double)100,(double)100);
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
        DoubleBinaryOperator sutThrowing = DoubleBinaryOperator.l((double d1,double d2) -> {
            throw new UnsupportedOperationException();
        });

        // when
        DoubleBinaryOperator wrapped = DoubleBinaryOperator.wrapException(sutThrowing, UnsupportedOperationException.class, null, t -> {
            return null;
        });

        // then
        try {
            wrapped.applyAsDouble((double)100,(double)100);
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
        double valueSmall = (double)100;
        double valueBig = (double)(valueSmall+10);

        //when
        DoubleBinaryOperator min = DoubleBinaryOperator.min();

        //then
        assertThat(min.applyAsDouble(valueSmall, valueBig))
                .isEqualTo(valueSmall);

        assertThat(min.applyAsDouble(valueBig, valueSmall))
                .isEqualTo(valueSmall);
    }

    @Test
    public void testMax() throws ParseException {
        //given
        double valueSmall = (double)100;
        double valueBig = (double)(valueSmall+10);

        //when
        DoubleBinaryOperator max = DoubleBinaryOperator.max();

        //then
        assertThat(max.applyAsDouble(valueSmall, valueBig))
                .isEqualTo(valueBig);

        assertThat(max.applyAsDouble(valueBig, valueSmall))
                .isEqualTo(valueBig);
    }


    // <editor-fold desc="compose (functional)">

    @Test
    public void testfromDouble() throws ParseException {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        DoubleBinaryOperator sutO = (double d1,double d2) -> {
                mainFunctionCalled.set(true);
                assertThat(d1).isEqualTo((double)90);
                assertThat(d2).isEqualTo((double)91);
                return (double)100;
        };

        DoubleUnaryOperator before1 = p0 -> {
            assertThat(p0).isEqualTo((double)80);
            beforeCalls.incrementAndGet();
            return (double)90;
        };
        DoubleUnaryOperator before2 = p1 -> {
            assertThat(p1).isEqualTo((double)81);
            beforeCalls.incrementAndGet();
            return (double)91;
        };

        //when
        DoubleBinaryOperator function = sutO.fromDouble(before1,before2);
        function.applyAsDouble((double)80,(double)81);

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(beforeCalls.get()).isEqualTo(2);
    }


    @Test
    public void testfrom() throws ParseException {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        DoubleBinaryOperator sutO = (double d1,double d2) -> {
                mainFunctionCalled.set(true);
                assertThat(d1).isEqualTo((double)90);
                assertThat(d2).isEqualTo((double)91);
                return (double)100;
        };

        ToDoubleFunction<Integer > before1 = p0 -> {
            assertThat(p0).isEqualTo(Integer.valueOf(80));
            beforeCalls.incrementAndGet();
            return (double)90;
        };
        ToDoubleFunction<Integer > before2 = p1 -> {
            assertThat(p1).isEqualTo(Integer.valueOf(81));
            beforeCalls.incrementAndGet();
            return (double)91;
        };

        //when
        ToDoubleBiFunction<Integer ,Integer > function = sutO.from(before1,before2);
        function.applyAsDouble((Integer )Integer.valueOf(80),(Integer )Integer.valueOf(81));

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
        DoubleBinaryOperator sutO = (double d1,double d2) -> {
                mainFunctionCalled.set(true);
                assertThat(d1).isEqualTo((double)80);
                assertThat(d2).isEqualTo((double)81);
                return (double)90;
        };

        DoubleFunction<Integer > thenFunction = p -> {
                thenFunctionCalled.set(true);
                // 
                assertThat(p).isEqualTo((double)90);
                // V
                return Integer.valueOf(100);
        };

        //when
        DoubleBiFunction<Integer > function = sutO.then(thenFunction);
        Integer  finalValue = function.apply((double)80,(double)81);

        //then - finals
        assertThat(finalValue).isEqualTo(Integer.valueOf(100));
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    // </editor-fold>
    @Test
    public void testStd() {
        assertThat(sut.std()).isInstanceOf(java.util.function.DoubleBinaryOperator.class);
    }

    @Test
    public void testNonThrowing() {
        assertThat(sut.nonThrowing()).isInstanceOf(DoubleBinaryOperator.class);
    }

    @Test
    public void testUncheck() {
        assertThat(sut.uncheck()).isInstanceOf(DoubleBinaryOperatorX.class);
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void testShove() {

        // given
        DoubleBinaryOperator sutThrowing = DoubleBinaryOperator.l((double d1,double d2) -> {
            throw new UnsupportedOperationException();
        });

        // when
        sutThrowing.shove().applyAsDouble((double)100,(double)100);
    }

    @Test
    public void testHandle() throws ParseException {

        // given
        DoubleBinaryOperator sutThrowing = DoubleBinaryOperator.l((double d1,double d2) -> {
            throw new UnsupportedOperationException();
        });

        // when
        DoubleBinaryOperator wrapped = sutThrowing.handle(UnsupportedOperationException.class, t -> {
            throw new IllegalArgumentException(EXCEPTION_WAS_WRAPPED, t);
        });

        // then
        try {
            wrapped.applyAsDouble((double)100,(double)100);
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
                .contains("DoubleBinaryOperator: double applyAsDouble(double d1,double d2)");
    }


    @Test
    public void isThrowing() {
        assertThat(sut.isThrowing())
            .isFalse();
    }


}
