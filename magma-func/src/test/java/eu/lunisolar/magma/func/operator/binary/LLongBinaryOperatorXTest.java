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
public class LLongBinaryOperatorXTest<X extends ParseException> {
    private static final String ORIGINAL_MESSAGE = "Original message";
    private static final String EXCEPTION_WAS_WRAPPED = "Exception was wrapped.";
    private static final String NO_EXCEPTION_WERE_THROWN = "No exception were thrown.";

    private long testValue = (long)100;



    private LLongBinaryOperatorX<X> sut = new LLongBinaryOperatorX(){
        public  long doApplyAsLong(long l1,long l2) throws ParseException {
            return testValue;
        }
    };

    private LLongBinaryOperator opposite = new LLongBinaryOperator(){
        public  long doApplyAsLong(long l1,long l2)  {
            return testValue;
        }
    };


    private java.util.function.LongBinaryOperator jre = (long l1,long l2) -> testValue;


    private LLongBinaryOperatorX<ParseException> sutAlwaysThrowing = LLongBinaryOperatorX.lX((long l1,long l2) -> {
            throw new ParseException(ORIGINAL_MESSAGE, 0);
    });

    private LLongBinaryOperatorX<RuntimeException> sutAlwaysThrowingUnckeck = LLongBinaryOperatorX.lX((long l1,long l2) -> {
            throw new IndexOutOfBoundsException(ORIGINAL_MESSAGE);
    });


    @Test
    public void testTheResult() throws X {
        assertThat(sut.doApplyAsLong((long)100,(long)100))
            .isEqualTo(testValue);
    }

    @Test
    public void testNonNullDoApplyAsLong() throws X {
        assertThat(sut.nonNullDoApplyAsLong((long)100,(long)100))
            .isEqualTo(testValue);
    }

    @Test
    public void testNestingDoApplyAsLong_checked() throws X {

        // then
        try {
            sutAlwaysThrowing.nestingDoApplyAsLong((long)100,(long)100);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(NestedException.class)
                    .hasCauseExactlyInstanceOf(ParseException.class)
                    .hasMessage(ORIGINAL_MESSAGE);
        }
    }

    @Test
    public void testNestingDoApplyAsLong_unckeck() throws X {

        // then
        try {
            sutAlwaysThrowingUnckeck.nestingDoApplyAsLong((long)100,(long)100);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                    .hasNoCause()
                    .hasMessage(ORIGINAL_MESSAGE);
        }
    }

    @Test
    public void testShovingDoApplyAsLong_checked() throws X {

        // then
        try {
            sutAlwaysThrowing.shovingDoApplyAsLong((long)100,(long)100);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(ParseException.class)
                    .hasNoCause()
                    .hasMessage(ORIGINAL_MESSAGE);
        }
    }

    @Test
    public void testShovingDoApplyAsLong_unckeck() throws X {

        // then
        try {
            sutAlwaysThrowingUnckeck.shovingDoApplyAsLong((long)100,(long)100);
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
            .isEqualTo("LLongBinaryOperatorX: long doApplyAsLong(long l1,long l2) throws X");
    }

    @Test
    public void testLXMethod() throws X {
        assertThat(LLongBinaryOperatorX.lX((long l1,long l2) -> testValue ))
            .isInstanceOf(LLongBinaryOperatorX.class);
    }

    @Test
    public void testWrapXMethod() throws X {
        assertThat(LLongBinaryOperatorX.wrapX(opposite))
            .isInstanceOf(LLongBinaryOperatorX.class);
    }

    @Test
    public void testWrapStdMethod() throws X {
        assertThat(LLongBinaryOperatorX.wrap(jre))
            .isInstanceOf(LLongBinaryOperatorX.class);
    }


    @Test
    public void testWrapExceptionMethodWrapsTheException() throws X {

        // given
        LLongBinaryOperatorX<X> sutThrowing = LLongBinaryOperatorX.lX((long l1,long l2) -> {
            throw new UnsupportedOperationException();
        });

        // when
        LLongBinaryOperatorX<X> wrapped = sutThrowing.handleX(h -> {
            h.wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED);
        });

        // then
        try {
            wrapped.doApplyAsLong((long)100,(long)100);
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
        LLongBinaryOperatorX<X> sutThrowing = LLongBinaryOperatorX.lX((long l1,long l2) -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LLongBinaryOperatorX<X> wrapped = sutThrowing.handleX(handler -> handler
                .wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED)
                .throwIf(IndexOutOfBoundsException.class));

        // then
        try {
            wrapped.doApplyAsLong((long)100,(long)100);
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
        LLongBinaryOperatorX<X> sutThrowing = LLongBinaryOperatorX.lX((long l1,long l2) -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LLongBinaryOperatorX<X> wrapped = sutThrowing.handleX(handler -> handler
                .wrapWhen(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED)
                .throwIf(IndexOutOfBoundsException.class));

        // then
        try {
            wrapped.doApplyAsLong((long)100,(long)100);
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
        LLongBinaryOperatorX<X> sutThrowing = LLongBinaryOperatorX.lX((long l1,long l2) -> {
            throw (X) new ParseException(ORIGINAL_MESSAGE, 0);
        });

        // when
        LLongBinaryOperatorX<X> wrapped = sutThrowing.handleX(h -> {
        });

        // then
        try {
            wrapped.doApplyAsLong((long)100,(long)100);
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
        long valueSmall = (long)100;
        long valueBig = (long)(valueSmall+10);

        //when
        LLongBinaryOperatorX<X> min = LLongBinaryOperatorX.min();

        //then
        assertThat(min.doApplyAsLong(valueSmall, valueBig))
                .isEqualTo(valueSmall);

        assertThat(min.doApplyAsLong(valueBig, valueSmall))
                .isEqualTo(valueSmall);
    }

    @Test
    public void testMax() throws X {
        //given
        long valueSmall = (long)100;
        long valueBig = (long)(valueSmall+10);

        //when
        LLongBinaryOperatorX<X> max = LLongBinaryOperatorX.max();

        //then
        assertThat(max.doApplyAsLong(valueSmall, valueBig))
                .isEqualTo(valueBig);

        assertThat(max.doApplyAsLong(valueBig, valueSmall))
                .isEqualTo(valueBig);
    }


    // <editor-fold desc="compose (functional)">

    @Test
    public void testfromLong() throws X {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LLongBinaryOperatorX<X> sutO = (long l1,long l2) -> {
                mainFunctionCalled.set(true);
                assertThat(l1).isEqualTo((long)90);
                assertThat(l2).isEqualTo((long)91);
                return (long)100;
        };

        LLongUnaryOperatorX<X> before1 = p0 -> {
            assertThat(p0).isEqualTo((long)80);
            beforeCalls.incrementAndGet();
            return (long)90;
        };
        LLongUnaryOperatorX<X> before2 = p1 -> {
            assertThat(p1).isEqualTo((long)81);
            beforeCalls.incrementAndGet();
            return (long)91;
        };

        //when
        LLongBinaryOperatorX<X> function = sutO.fromLong(before1,before2);
        function.doApplyAsLong((long)80,(long)81);

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(beforeCalls.get()).isEqualTo(2);
    }


    @Test
    public void testfrom() throws X {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LLongBinaryOperatorX<X> sutO = (long l1,long l2) -> {
                mainFunctionCalled.set(true);
                assertThat(l1).isEqualTo((long)90);
                assertThat(l2).isEqualTo((long)91);
                return (long)100;
        };

        LToLongFunctionX<Integer ,X> before1 = p0 -> {
            assertThat(p0).isEqualTo(Integer.valueOf(80));
            beforeCalls.incrementAndGet();
            return (long)90;
        };
        LToLongFunctionX<Integer ,X> before2 = p1 -> {
            assertThat(p1).isEqualTo(Integer.valueOf(81));
            beforeCalls.incrementAndGet();
            return (long)91;
        };

        //when
        LToLongBiFunctionX<Integer ,Integer ,X> function = sutO.from(before1,before2);
        function.doApplyAsLong((Integer )Integer.valueOf(80),(Integer )Integer.valueOf(81));

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
        LLongBinaryOperatorX<X> sutO = (long l1,long l2) -> {
                mainFunctionCalled.set(true);
                assertThat(l1).isEqualTo((long)80);
                assertThat(l2).isEqualTo((long)81);
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
        LLongBiFunctionX<Integer ,X> function = sutO.then(thenFunction);
        Integer  finalValue = function.doApply((long)80,(long)81);

        //then - finals
        assertThat(finalValue).isEqualTo(Integer.valueOf(100));
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    // </editor-fold>

    @Test
    public void testNesting() {
        assertThat(sut.nest())
            .isInstanceOf(LLongBinaryOperator.class);
    }

    @Test
    public void testShoving() {
        assertThat(sut.shove())
            .isInstanceOf(LLongBinaryOperator.class);
    }

    @Test
    public void testNestingX() {
        assertThat(sut.nestX())
            .isInstanceOf(LLongBinaryOperatorX.class);
    }

    @Test
    public void testShovingX() {
        assertThat(sut.shoveX())
            .isInstanceOf(LLongBinaryOperatorX.class);
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void testShove() {

        // given
        LLongBinaryOperatorX<X> sutThrowing = LLongBinaryOperatorX.lX((long l1,long l2) -> {
            throw new UnsupportedOperationException();
        });

        // when
        sutThrowing.shove().doApplyAsLong((long)100,(long)100);
    }

    @Test
    public void testHandle() throws X {

        // given
        LLongBinaryOperatorX<X> sutThrowing = LLongBinaryOperatorX.lX((long l1,long l2) -> {
            throw new UnsupportedOperationException();
        });

        // when
        LLongBinaryOperatorX<X> wrapped = sutThrowing.handleX(h -> {
            h.wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED);
        });

        // then
        try {
            wrapped.doApplyAsLong((long)100,(long)100);
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
                .contains("LLongBinaryOperatorX: long doApplyAsLong(long l1,long l2) throws X");
    }


    @Test
    public void isThrowing() {
        assertThat(sut.isThrowing())
            .isTrue();
    }


}
