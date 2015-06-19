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

package eu.lunisolar.magma.func.operator.unary;

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
public class LUnaryOperatorXTest<T,X extends ParseException> {
    private static final String ORIGINAL_MESSAGE = "Original message";
    private static final String EXCEPTION_WAS_WRAPPED = "Exception was wrapped.";
    private static final String NO_EXCEPTION_WERE_THROWN = "No exception were thrown.";

    private Object  testValue = (T)Integer.valueOf(100);



    private LUnaryOperatorX<T,X> sut = new LUnaryOperatorX(){
        public @Nullable Object  doApply(Object t) throws ParseException {
            return testValue;
        }
    };

    private LUnaryOperator<T> opposite = new LUnaryOperator(){
        public @Nullable Object  doApply(Object t)  {
            return testValue;
        }
    };

    private LUnaryOperatorX<T,X> sutNull = new LUnaryOperatorX(){
        public @Nullable Object  doApply(Object t) throws ParseException {
            return null;
        }
    };


    private java.util.function.UnaryOperator jre = (Object t) -> testValue;


    private LUnaryOperatorX<T,ParseException> sutAlwaysThrowing = LUnaryOperatorX.lX((T t) -> {
            throw new ParseException(ORIGINAL_MESSAGE, 0);
    });

    private LUnaryOperatorX<T,RuntimeException> sutAlwaysThrowingUnckeck = LUnaryOperatorX.lX((T t) -> {
            throw new IndexOutOfBoundsException(ORIGINAL_MESSAGE);
    });


    @Test
    public void testTheResult() throws X {
        assertThat(sut.doApply((T)Integer.valueOf(100)))
            .isSameAs(testValue);
    }

    @Test
    public void testNonNullDoApply() throws X {
        assertThat(sut.nonNullDoApply((T)Integer.valueOf(100)))
            .isSameAs(testValue);
    }

    @Test
    public void testNestingDoApply_checked() throws X {

        // then
        try {
            sutAlwaysThrowing.nestingDoApply((T)Integer.valueOf(100));
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(NestedException.class)
                    .hasCauseExactlyInstanceOf(ParseException.class)
                    .hasMessage(ORIGINAL_MESSAGE);
        }
    }

    @Test
    public void testNestingDoApply_unckeck() throws X {

        // then
        try {
            sutAlwaysThrowingUnckeck.nestingDoApply((T)Integer.valueOf(100));
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                    .hasNoCause()
                    .hasMessage(ORIGINAL_MESSAGE);
        }
    }

    @Test
    public void testShovingDoApply_checked() throws X {

        // then
        try {
            sutAlwaysThrowing.shovingDoApply((T)Integer.valueOf(100));
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(ParseException.class)
                    .hasNoCause()
                    .hasMessage(ORIGINAL_MESSAGE);
        }
    }

    @Test
    public void testShovingDoApply_unckeck() throws X {

        // then
        try {
            sutAlwaysThrowingUnckeck.shovingDoApply((T)Integer.valueOf(100));
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                    .hasNoCause()
                    .hasMessage(ORIGINAL_MESSAGE);
        }
    }


    @Test(expectedExceptions=NullPointerException.class, expectedExceptionsMessageRegExp="\\QEvaluated value by nonNullDoApply() method cannot be null (LUnaryOperatorX: T doApply(T t) throws X).\\E")
    public void testNonNullCapturesNull() throws X {
        sutNull.nonNullDoApply((T)Integer.valueOf(100));
    }


    @Test
    public void testFunctionalInterfaceDescription() throws X {
        assertThat(sut.functionalInterfaceDescription())
            .isEqualTo("LUnaryOperatorX: T doApply(T t) throws X");
    }

    @Test
    public void testLXMethod() throws X {
        assertThat(LUnaryOperatorX.lX((Object t) -> testValue ))
            .isInstanceOf(LUnaryOperatorX.class);
    }

    @Test
    public void testWrapXMethod() throws X {
        assertThat(LUnaryOperatorX.wrapX(opposite))
            .isInstanceOf(LUnaryOperatorX.class);
    }

    @Test
    public void testWrapStdMethod() throws X {
        assertThat(LUnaryOperatorX.wrap(jre))
            .isInstanceOf(LUnaryOperatorX.class);
    }


    @Test
    public void testWrapExceptionMethodWrapsTheException() throws X {

        // given
        LUnaryOperatorX<T,X> sutThrowing = LUnaryOperatorX.lX((T t) -> {
            throw new UnsupportedOperationException();
        });

        // when
        LUnaryOperatorX<T,X> wrapped = sutThrowing.handleX(h -> {
            h.wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED);
        });

        // then
        try {
            wrapped.doApply((T)Integer.valueOf(100));
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
        LUnaryOperatorX<T,X> sutThrowing = LUnaryOperatorX.lX((T t) -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LUnaryOperatorX<T,X> wrapped = sutThrowing.handleX(handler -> handler
                .wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED)
                .throwIf(IndexOutOfBoundsException.class));

        // then
        try {
            wrapped.doApply((T)Integer.valueOf(100));
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
        LUnaryOperatorX<T,X> sutThrowing = LUnaryOperatorX.lX((T t) -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LUnaryOperatorX<T,X> wrapped = sutThrowing.handleX(handler -> handler
                .wrapWhen(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED)
                .throwIf(IndexOutOfBoundsException.class));

        // then
        try {
            wrapped.doApply((T)Integer.valueOf(100));
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
        LUnaryOperatorX<T,X> sutThrowing = LUnaryOperatorX.lX((T t) -> {
            throw (X) new ParseException(ORIGINAL_MESSAGE, 0);
        });

        // when
        LUnaryOperatorX<T,X> wrapped = sutThrowing.handleX(h -> {
        });

        // then
        try {
            wrapped.doApply((T)Integer.valueOf(100));
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(NestedException.class)
                    .hasCauseExactlyInstanceOf(ParseException.class)
                    .hasMessage(ORIGINAL_MESSAGE);
        }
    }



    // <editor-fold desc="then (functional)">

    @Test
    public void testThen0() throws X  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);


        //given (+ some assertions)
        LUnaryOperatorX<Integer ,X> sutO = (Integer t) -> {
                mainFunctionCalled.set(true);
                assertThat(t).isEqualTo((T)Integer.valueOf(80));
                return Integer.valueOf(90);
        };

        LFunctionX<Integer ,Integer ,X> thenFunction = p -> {
                thenFunctionCalled.set(true);
                // T
                assertThat(p).isEqualTo(Integer.valueOf(90));
                // V
                return Integer.valueOf(100);
        };

        //when
        LFunctionX<Integer ,Integer ,X> function = sutO.then(thenFunction);
        Integer  finalValue = function.doApply((Integer )Integer.valueOf(80));

        //then - finals
        assertThat(finalValue).isEqualTo(Integer.valueOf(100));
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    @Test
    public void testThen1ToByte() throws X  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);


        //given (+ some assertions)
        LUnaryOperatorX<Integer ,X> sutO = (Integer t) -> {
                mainFunctionCalled.set(true);
                assertThat(t).isEqualTo((T)Integer.valueOf(80));
                return Integer.valueOf(90);
        };

        LToByteFunctionX<Integer ,X> thenFunction = p -> {
                thenFunctionCalled.set(true);
                // T
                assertThat(p).isEqualTo(Integer.valueOf(90));
                // byte
                return (byte)100;
        };

        //when
        LToByteFunctionX<Integer ,X> function = sutO.thenToByte(thenFunction);
        byte finalValue = function.doApplyAsByte((Integer )Integer.valueOf(80));

        //then - finals
        assertThat(finalValue).isEqualTo((byte)100);
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    @Test
    public void testThen2ToShort() throws X  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);


        //given (+ some assertions)
        LUnaryOperatorX<Integer ,X> sutO = (Integer t) -> {
                mainFunctionCalled.set(true);
                assertThat(t).isEqualTo((T)Integer.valueOf(80));
                return Integer.valueOf(90);
        };

        LToShortFunctionX<Integer ,X> thenFunction = p -> {
                thenFunctionCalled.set(true);
                // T
                assertThat(p).isEqualTo(Integer.valueOf(90));
                // short
                return (short)100;
        };

        //when
        LToShortFunctionX<Integer ,X> function = sutO.thenToShort(thenFunction);
        short finalValue = function.doApplyAsShort((Integer )Integer.valueOf(80));

        //then - finals
        assertThat(finalValue).isEqualTo((short)100);
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    @Test
    public void testThen3ToInt() throws X  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);


        //given (+ some assertions)
        LUnaryOperatorX<Integer ,X> sutO = (Integer t) -> {
                mainFunctionCalled.set(true);
                assertThat(t).isEqualTo((T)Integer.valueOf(80));
                return Integer.valueOf(90);
        };

        LToIntFunctionX<Integer ,X> thenFunction = p -> {
                thenFunctionCalled.set(true);
                // T
                assertThat(p).isEqualTo(Integer.valueOf(90));
                // int
                return (int)100;
        };

        //when
        LToIntFunctionX<Integer ,X> function = sutO.thenToInt(thenFunction);
        int finalValue = function.doApplyAsInt((Integer )Integer.valueOf(80));

        //then - finals
        assertThat(finalValue).isEqualTo((int)100);
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    @Test
    public void testThen4ToLong() throws X  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);


        //given (+ some assertions)
        LUnaryOperatorX<Integer ,X> sutO = (Integer t) -> {
                mainFunctionCalled.set(true);
                assertThat(t).isEqualTo((T)Integer.valueOf(80));
                return Integer.valueOf(90);
        };

        LToLongFunctionX<Integer ,X> thenFunction = p -> {
                thenFunctionCalled.set(true);
                // T
                assertThat(p).isEqualTo(Integer.valueOf(90));
                // long
                return (long)100;
        };

        //when
        LToLongFunctionX<Integer ,X> function = sutO.thenToLong(thenFunction);
        long finalValue = function.doApplyAsLong((Integer )Integer.valueOf(80));

        //then - finals
        assertThat(finalValue).isEqualTo((long)100);
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    @Test
    public void testThen5ToFloat() throws X  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);


        //given (+ some assertions)
        LUnaryOperatorX<Integer ,X> sutO = (Integer t) -> {
                mainFunctionCalled.set(true);
                assertThat(t).isEqualTo((T)Integer.valueOf(80));
                return Integer.valueOf(90);
        };

        LToFloatFunctionX<Integer ,X> thenFunction = p -> {
                thenFunctionCalled.set(true);
                // T
                assertThat(p).isEqualTo(Integer.valueOf(90));
                // float
                return (float)100;
        };

        //when
        LToFloatFunctionX<Integer ,X> function = sutO.thenToFloat(thenFunction);
        float finalValue = function.doApplyAsFloat((Integer )Integer.valueOf(80));

        //then - finals
        assertThat(finalValue).isEqualTo((float)100);
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    @Test
    public void testThen6ToDouble() throws X  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);


        //given (+ some assertions)
        LUnaryOperatorX<Integer ,X> sutO = (Integer t) -> {
                mainFunctionCalled.set(true);
                assertThat(t).isEqualTo((T)Integer.valueOf(80));
                return Integer.valueOf(90);
        };

        LToDoubleFunctionX<Integer ,X> thenFunction = p -> {
                thenFunctionCalled.set(true);
                // T
                assertThat(p).isEqualTo(Integer.valueOf(90));
                // double
                return (double)100;
        };

        //when
        LToDoubleFunctionX<Integer ,X> function = sutO.thenToDouble(thenFunction);
        double finalValue = function.doApplyAsDouble((Integer )Integer.valueOf(80));

        //then - finals
        assertThat(finalValue).isEqualTo((double)100);
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    @Test
    public void testThen7ToChar() throws X  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);


        //given (+ some assertions)
        LUnaryOperatorX<Integer ,X> sutO = (Integer t) -> {
                mainFunctionCalled.set(true);
                assertThat(t).isEqualTo((T)Integer.valueOf(80));
                return Integer.valueOf(90);
        };

        LToCharFunctionX<Integer ,X> thenFunction = p -> {
                thenFunctionCalled.set(true);
                // T
                assertThat(p).isEqualTo(Integer.valueOf(90));
                // char
                return (char)100;
        };

        //when
        LToCharFunctionX<Integer ,X> function = sutO.thenToChar(thenFunction);
        char finalValue = function.doApplyAsChar((Integer )Integer.valueOf(80));

        //then - finals
        assertThat(finalValue).isEqualTo((char)100);
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    @Test
    public void testThen8ToBoolean() throws X  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);


        //given (+ some assertions)
        LUnaryOperatorX<Integer ,X> sutO = (Integer t) -> {
                mainFunctionCalled.set(true);
                assertThat(t).isEqualTo((T)Integer.valueOf(80));
                return Integer.valueOf(90);
        };

        LPredicateX<Integer ,X> thenFunction = p -> {
                thenFunctionCalled.set(true);
                // T
                assertThat(p).isEqualTo(Integer.valueOf(90));
                // boolean
                return true;
        };

        //when
        LPredicateX<Integer ,X> function = sutO.thenToBoolean(thenFunction);
        boolean finalValue = function.doTest((Integer )Integer.valueOf(80));

        //then - finals
        assertThat(finalValue).isEqualTo(true);
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    // </editor-fold>
    @Test
    public void identity() throws X {
        LUnaryOperatorX<Integer ,X> identityFunction = LUnaryOperatorX.identity();

        assertThat(identityFunction.doApply((Integer )Integer.valueOf(80))).isEqualTo((Integer )Integer.valueOf(80));
    }


    @Test
    public void testNesting() {
        assertThat(sut.nest())
            .isInstanceOf(LUnaryOperator.class);
    }

    @Test
    public void testShoving() {
        assertThat(sut.shove())
            .isInstanceOf(LUnaryOperator.class);
    }

    @Test
    public void testNestingX() {
        assertThat(sut.nestX())
            .isInstanceOf(LUnaryOperatorX.class);
    }

    @Test
    public void testShovingX() {
        assertThat(sut.shoveX())
            .isInstanceOf(LUnaryOperatorX.class);
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void testShove() {

        // given
        LUnaryOperatorX<T,X> sutThrowing = LUnaryOperatorX.lX((T t) -> {
            throw new UnsupportedOperationException();
        });

        // when
        sutThrowing.shove().doApply((T)Integer.valueOf(100));
    }

    @Test
    public void testHandle() throws X {

        // given
        LUnaryOperatorX<T,X> sutThrowing = LUnaryOperatorX.lX((T t) -> {
            throw new UnsupportedOperationException();
        });

        // when
        LUnaryOperatorX<T,X> wrapped = sutThrowing.handleX(h -> {
            h.wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED);
        });

        // then
        try {
            wrapped.doApply((T)Integer.valueOf(100));
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
                .contains("LUnaryOperatorX: T doApply(T t) throws X");
    }


    @Test
    public void isThrowing() {
        assertThat(sut.isThrowing())
            .isTrue();
    }


}
