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
import eu.lunisolar.magma.basics.exceptions.*; //NOSONAR
import java.util.concurrent.atomic.AtomicInteger; //NOSONAR
import static org.assertj.core.api.Assertions.*; //NOSONAR

/** The test obviously concentrate on the interface methods the function it self is very simple.  */
public class LShortBinaryOperatorXTest<X extends ParseException> {
    private static final String ORIGINAL_MESSAGE = "Original message";
    private static final String EXCEPTION_WAS_WRAPPED = "Exception was wrapped.";
    private static final String NO_EXCEPTION_WERE_THROWN = "No exception were thrown.";

    private short testValue = (short)100;



    private LShortBinaryOperatorX<X> sut = new LShortBinaryOperatorX(){
        public  short doApplyAsShort(short s1,short s2) throws ParseException {
            return testValue;
        }
    };

    private LShortBinaryOperator opposite = new LShortBinaryOperator(){
        public  short doApplyAsShort(short s1,short s2)  {
            return testValue;
        }
    };



    private LShortBinaryOperatorX<ParseException> sutAlwaysThrowing = LShortBinaryOperatorX.lX((short s1,short s2) -> {
            throw new ParseException(ORIGINAL_MESSAGE, 0);
    });

    private LShortBinaryOperatorX<RuntimeException> sutAlwaysThrowingUnckeck = LShortBinaryOperatorX.lX((short s1,short s2) -> {
            throw new IndexOutOfBoundsException(ORIGINAL_MESSAGE);
    });


    @Test
    public void testTheResult() throws X {
        assertThat(sut.doApplyAsShort((short)100,(short)100))
            .isEqualTo(testValue);
    }

    @Test
    public void testNonNullDoApplyAsShort() throws X {
        assertThat(sut.nonNullDoApplyAsShort((short)100,(short)100))
            .isEqualTo(testValue);
    }

    @Test
    public void testNestingDoApplyAsShort_checked() throws X {

        // then
        try {
            sutAlwaysThrowing.nestingDoApplyAsShort((short)100,(short)100);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(NestedException.class)
                    .hasCauseExactlyInstanceOf(ParseException.class)
                    .hasMessage(ORIGINAL_MESSAGE);
        }
    }

    @Test
    public void testNestingDoApplyAsShort_unckeck() throws X {

        // then
        try {
            sutAlwaysThrowingUnckeck.nestingDoApplyAsShort((short)100,(short)100);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                    .hasNoCause()
                    .hasMessage(ORIGINAL_MESSAGE);
        }
    }

    @Test
    public void testShovingDoApplyAsShort_checked() throws X {

        // then
        try {
            sutAlwaysThrowing.shovingDoApplyAsShort((short)100,(short)100);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(ParseException.class)
                    .hasNoCause()
                    .hasMessage(ORIGINAL_MESSAGE);
        }
    }

    @Test
    public void testShovingDoApplyAsShort_unckeck() throws X {

        // then
        try {
            sutAlwaysThrowingUnckeck.shovingDoApplyAsShort((short)100,(short)100);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                    .hasNoCause()
                    .hasMessage(ORIGINAL_MESSAGE);
        }
    }



    @Test
    public void testFunctionalInterfaceDescription() throws X {
        assertThat(sut.functionalInterfaceDescription())
            .isEqualTo("LShortBinaryOperatorX: short doApplyAsShort(short s1,short s2) throws X");
    }

    @Test
    public void testLXMethod() throws X {
        assertThat(LShortBinaryOperatorX.lX((short s1,short s2) -> testValue ))
            .isInstanceOf(LShortBinaryOperatorX.class);
    }

    @Test
    public void testWrapXMethod() throws X {
        assertThat(LShortBinaryOperatorX.wrapX(opposite))
            .isInstanceOf(LShortBinaryOperatorX.class);
    }


    @Test
    public void testWrapExceptionMethodWrapsTheException() throws X {

        // given
        LShortBinaryOperatorX<X> sutThrowing = LShortBinaryOperatorX.lX((short s1,short s2) -> {
            throw new UnsupportedOperationException();
        });

        // when
        LShortBinaryOperatorX<X> wrapped = sutThrowing.handleX(h -> {
            h.wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED);
        });

        // then
        try {
            wrapped.doApplyAsShort((short)100,(short)100);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IllegalArgumentException.class)
                    .hasCauseExactlyInstanceOf(UnsupportedOperationException.class)
                    .hasMessage(EXCEPTION_WAS_WRAPPED);
        }
    }

    @Test
    public void testWrapExceptionMethodDoNotWrapsOtherException_if() throws X {

        // given
        LShortBinaryOperatorX<X> sutThrowing = LShortBinaryOperatorX.lX((short s1,short s2) -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LShortBinaryOperatorX<X> wrapped = sutThrowing.handleX(handler -> handler
                .wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED)
                .throwIf(IndexOutOfBoundsException.class));

        // then
        try {
            wrapped.doApplyAsShort((short)100,(short)100);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                    .hasNoCause();
        }
    }

@Test
    public void testWrapExceptionMethodDoNotWrapsOtherException_when() throws X {

        // given
        LShortBinaryOperatorX<X> sutThrowing = LShortBinaryOperatorX.lX((short s1,short s2) -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LShortBinaryOperatorX<X> wrapped = sutThrowing.handleX(handler -> handler
                .wrapWhen(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED)
                .throwIf(IndexOutOfBoundsException.class));

        // then
        try {
            wrapped.doApplyAsShort((short)100,(short)100);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                    .hasNoCause();
        }
    }


    @Test
    public void testWrapExceptionMishandlingExceptionIsAllowed() throws X {

        // given
        LShortBinaryOperatorX<X> sutThrowing = LShortBinaryOperatorX.lX((short s1,short s2) -> {
            throw (X) new ParseException(ORIGINAL_MESSAGE, 0);
        });

        // when
        LShortBinaryOperatorX<X> wrapped = sutThrowing.handleX(h -> {
        });

        // then
        try {
            wrapped.doApplyAsShort((short)100,(short)100);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(NestedException.class)
                    .hasCauseExactlyInstanceOf(ParseException.class)
                    .hasMessage(ORIGINAL_MESSAGE);
        }
    }


    @Test
    public void testMin() throws X {
        //given
        short valueSmall = (short)100;
        short valueBig = (short)(valueSmall+10);

        //when
        LShortBinaryOperatorX<X> min = LShortBinaryOperatorX.min();

        //then
        assertThat(min.doApplyAsShort(valueSmall, valueBig))
                .isEqualTo(valueSmall);

        assertThat(min.doApplyAsShort(valueBig, valueSmall))
                .isEqualTo(valueSmall);
    }

    @Test
    public void testMax() throws X {
        //given
        short valueSmall = (short)100;
        short valueBig = (short)(valueSmall+10);

        //when
        LShortBinaryOperatorX<X> max = LShortBinaryOperatorX.max();

        //then
        assertThat(max.doApplyAsShort(valueSmall, valueBig))
                .isEqualTo(valueBig);

        assertThat(max.doApplyAsShort(valueBig, valueSmall))
                .isEqualTo(valueBig);
    }


    // <editor-fold desc="compose (functional)">

    @Test
    public void testfromShort() throws X {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LShortBinaryOperatorX<X> sutO = (short s1,short s2) -> {
                mainFunctionCalled.set(true);
                assertThat(s1).isEqualTo((short)90);
                assertThat(s2).isEqualTo((short)91);
                return (short)100;
        };

        LShortUnaryOperatorX<X> before1 = p0 -> {
            assertThat(p0).isEqualTo((short)80);
            beforeCalls.incrementAndGet();
            return (short)90;
        };
        LShortUnaryOperatorX<X> before2 = p1 -> {
            assertThat(p1).isEqualTo((short)81);
            beforeCalls.incrementAndGet();
            return (short)91;
        };

        //when
        LShortBinaryOperatorX<X> function = sutO.fromShort(before1,before2);
        function.doApplyAsShort((short)80,(short)81);

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(beforeCalls.get()).isEqualTo(2);
    }


    @Test
    public void testfrom() throws X {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LShortBinaryOperatorX<X> sutO = (short s1,short s2) -> {
                mainFunctionCalled.set(true);
                assertThat(s1).isEqualTo((short)90);
                assertThat(s2).isEqualTo((short)91);
                return (short)100;
        };

        LToShortFunctionX<Integer ,X> before1 = p0 -> {
            assertThat(p0).isEqualTo(Integer.valueOf(80));
            beforeCalls.incrementAndGet();
            return (short)90;
        };
        LToShortFunctionX<Integer ,X> before2 = p1 -> {
            assertThat(p1).isEqualTo(Integer.valueOf(81));
            beforeCalls.incrementAndGet();
            return (short)91;
        };

        //when
        LToShortBiFunctionX<Integer ,Integer ,X> function = sutO.from(before1,before2);
        function.doApplyAsShort((Integer )Integer.valueOf(80),(Integer )Integer.valueOf(81));

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(beforeCalls.get()).isEqualTo(2);
    }

    // </editor-fold>


    // <editor-fold desc="then (functional)">

    @Test
    public void testThen0() throws X  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);


        //given (+ some assertions)
        LShortBinaryOperatorX<X> sutO = (short s1,short s2) -> {
                mainFunctionCalled.set(true);
                assertThat(s1).isEqualTo((short)80);
                assertThat(s2).isEqualTo((short)81);
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
        LShortBiFunctionX<Integer ,X> function = sutO.then(thenFunction);
        Integer  finalValue = function.doApply((short)80,(short)81);

        //then - finals
        assertThat(finalValue).isEqualTo(Integer.valueOf(100));
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    // </editor-fold>

    @Test
    public void testNesting() {
        assertThat(sut.nest())
            .isInstanceOf(LShortBinaryOperator.class);
    }

    @Test
    public void testShoving() {
        assertThat(sut.shove())
            .isInstanceOf(LShortBinaryOperator.class);
    }

    @Test
    public void testNestingX() {
        assertThat(sut.nestX())
            .isInstanceOf(LShortBinaryOperatorX.class);
    }

    @Test
    public void testShovingX() {
        assertThat(sut.shoveX())
            .isInstanceOf(LShortBinaryOperatorX.class);
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void testShove() {

        // given
        LShortBinaryOperatorX<X> sutThrowing = LShortBinaryOperatorX.lX((short s1,short s2) -> {
            throw new UnsupportedOperationException();
        });

        // when
        sutThrowing.shove().doApplyAsShort((short)100,(short)100);
    }

    @Test
    public void testHandle() throws X {

        // given
        LShortBinaryOperatorX<X> sutThrowing = LShortBinaryOperatorX.lX((short s1,short s2) -> {
            throw new UnsupportedOperationException();
        });

        // when
        LShortBinaryOperatorX<X> wrapped = sutThrowing.handleX(h -> {
            h.wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED);
        });

        // then
        try {
            wrapped.doApplyAsShort((short)100,(short)100);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IllegalArgumentException.class)
                    .hasCauseExactlyInstanceOf(UnsupportedOperationException.class)
                    .hasMessage(EXCEPTION_WAS_WRAPPED);
        }
    }

    @Test
    public void testToString() throws X {

        assertThat(sut.toString())
                .isInstanceOf(String.class)
                .startsWith(this.getClass().getName()+"$");

        assertThat(String.format("%s", sut))
                .isInstanceOf(String.class)
                .contains("LShortBinaryOperatorX: short doApplyAsShort(short s1,short s2) throws X");
    }


    @Test
    public void isThrowing() {
        assertThat(sut.isThrowing())
            .isTrue();
    }


}
