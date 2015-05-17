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

package eu.lunisolar.magma.func.operator.ternary;

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
public class LTernaryOperatorTest<T,X extends ParseException> {
    private static final String ORIGINAL_MESSAGE = "Original message";
    private static final String EXCEPTION_WAS_WRAPPED = "Exception was wrapped.";
    private static final String NO_EXCEPTION_WERE_THROWN = "No exception were thrown.";

    private Object  testValue = (T)Integer.valueOf(100);



    private LTernaryOperator<T> sut = new LTernaryOperator(){
        public @Nullable Object  doApply(Object t1,Object t2,Object t3)  {
            return testValue;
        }
    };

    private LTernaryOperatorX<T,X> opposite = new LTernaryOperatorX(){
        public @Nullable Object  doApply(Object t1,Object t2,Object t3) throws ParseException {
            return testValue;
        }
    };

    private LTernaryOperator<T> sutNull = new LTernaryOperator(){
        public @Nullable Object  doApply(Object t1,Object t2,Object t3)  {
            return null;
        }
    };




    private LTernaryOperator<T> sutAlwaysThrowingUnckeck = LTernaryOperator.l((T t1,T t2,T t3) -> {
            throw new IndexOutOfBoundsException(ORIGINAL_MESSAGE);
    });


    @Test
    public void testTheResult() throws ParseException {
        assertThat(sut.doApply((T)Integer.valueOf(100),(T)Integer.valueOf(100),(T)Integer.valueOf(100)))
            .isSameAs(testValue);
    }

    @Test
    public void testNonNullDoApply() throws ParseException {
        assertThat(sut.nonNullDoApply((T)Integer.valueOf(100),(T)Integer.valueOf(100),(T)Integer.valueOf(100)))
            .isSameAs(testValue);
    }

    @Test
    public void testNestingDoApply_unckeck() throws ParseException {

        // then
        try {
            sutAlwaysThrowingUnckeck.nestingDoApply((T)Integer.valueOf(100),(T)Integer.valueOf(100),(T)Integer.valueOf(100));
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                    .hasNoCause()
                    .hasMessage(ORIGINAL_MESSAGE);
        }
    }

    @Test
    public void testShovingDoApply_unckeck() throws ParseException {

        // then
        try {
            sutAlwaysThrowingUnckeck.shovingDoApply((T)Integer.valueOf(100),(T)Integer.valueOf(100),(T)Integer.valueOf(100));
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                    .hasNoCause()
                    .hasMessage(ORIGINAL_MESSAGE);
        }
    }


    @Test(expectedExceptions=NullPointerException.class, expectedExceptionsMessageRegExp="\\QEvaluated value by nonNullDoApply() method cannot be null (LTernaryOperator: T doApply(T t1,T t2,T t3)).\\E")
    public void testNonNullCapturesNull() throws ParseException {
        sutNull.nonNullDoApply((T)Integer.valueOf(100),(T)Integer.valueOf(100),(T)Integer.valueOf(100));
    }


    @Test
    public void testFunctionalInterfaceDescription() throws ParseException {
        assertThat(sut.functionalInterfaceDescription())
            .isEqualTo("LTernaryOperator: T doApply(T t1,T t2,T t3)");
    }

    @Test
    public void testLMethod() throws ParseException {
        assertThat(LTernaryOperator.l((Object t1,Object t2,Object t3) -> testValue ))
            .isInstanceOf(LTernaryOperator.class);
    }

    @Test
    public void testWrapMethod() throws ParseException {
        assertThat(LTernaryOperator.wrap(opposite))
            .isInstanceOf(LTernaryOperator.class);
    }

    @Test
    public void testWrapMethodDoNotWrapsRuntimeException() throws ParseException {
        // given
        LTernaryOperatorX<T,X> sutThrowing = LTernaryOperatorX.lX((T t1,T t2,T t3) -> {
            throw new UnsupportedOperationException(ORIGINAL_MESSAGE);
        });

        // when
        LTernaryOperator<T> wrapped = LTernaryOperator.wrap(sutThrowing);

        // then
        try {
            wrapped.doApply((T)Integer.valueOf(100),(T)Integer.valueOf(100),(T)Integer.valueOf(100));
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
        LTernaryOperatorX<T,ParseException> sutThrowing = LTernaryOperatorX.lX((T t1,T t2,T t3) -> {
            throw new ParseException(ORIGINAL_MESSAGE, 0);
        });

        // when
        LTernaryOperator<T> wrapped = LTernaryOperator.wrap(sutThrowing);

        // then
        try {
            wrapped.doApply((T)Integer.valueOf(100),(T)Integer.valueOf(100),(T)Integer.valueOf(100));
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
        LTernaryOperator<T> sutThrowing = LTernaryOperator.l((T t1,T t2,T t3) -> {
            throw new UnsupportedOperationException();
        });

        // when
        LTernaryOperator<T> wrapped = LTernaryOperator.wrapException(sutThrowing, UnsupportedOperationException.class, null, t -> {
            throw new IllegalArgumentException(EXCEPTION_WAS_WRAPPED, t);
        });

        // then
        try {
            wrapped.doApply((T)Integer.valueOf(100),(T)Integer.valueOf(100),(T)Integer.valueOf(100));
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
        LTernaryOperator<T> sutThrowing = LTernaryOperator.l((T t1,T t2,T t3) -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LTernaryOperator<T> wrapped = LTernaryOperator.wrapException(sutThrowing, UnsupportedOperationException.class, null, t -> {
            throw new IllegalArgumentException(EXCEPTION_WAS_WRAPPED, t);
        });

        // then
        try {
            wrapped.doApply((T)Integer.valueOf(100),(T)Integer.valueOf(100),(T)Integer.valueOf(100));
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
        LTernaryOperator<T> sutThrowing = LTernaryOperator.l((T t1,T t2,T t3) -> {
            throw new UnsupportedOperationException();
        });

        // when
        LTernaryOperator<T> wrapped = LTernaryOperator.wrapException(sutThrowing, UnsupportedOperationException.class, null, t -> {
            return null;
        });

        // then
        try {
            wrapped.doApply((T)Integer.valueOf(100),(T)Integer.valueOf(100),(T)Integer.valueOf(100));
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(ExceptionNotHandled.class)
                    .hasCauseExactlyInstanceOf(UnsupportedOperationException.class)
                    .hasMessage("Handler has not processed the exception.");
        }
    }



    // <editor-fold desc="then (functional)">

    @Test
    public void testThen0() throws ParseException  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);


        //given (+ some assertions)
        LTernaryOperator<Integer > sutO = (Integer t1,Integer t2,Integer t3) -> {
                mainFunctionCalled.set(true);
                assertThat(t1).isEqualTo((T)Integer.valueOf(80));
                assertThat(t2).isEqualTo((T)Integer.valueOf(81));
                assertThat(t3).isEqualTo((T)Integer.valueOf(82));
                return Integer.valueOf(90);
        };

        LFunction<Integer ,Integer > thenFunction = p -> {
                thenFunctionCalled.set(true);
                // T
                assertThat(p).isEqualTo(Integer.valueOf(90));
                // V
                return Integer.valueOf(100);
        };

        //when
        LTriFunction<Integer ,Integer ,Integer ,Integer > function = sutO.then(thenFunction);
        Integer  finalValue = function.doApply((Integer )Integer.valueOf(80),(Integer )Integer.valueOf(81),(Integer )Integer.valueOf(82));

        //then - finals
        assertThat(finalValue).isEqualTo(Integer.valueOf(100));
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    // </editor-fold>
//
    @Test
    public void testNesting() {
        assertThat(sut.nest())
            .isSameAs(sut)
            .isInstanceOf(LTernaryOperator.class);
    }

    @Test
    public void testShoving() {
        assertThat(sut.shove())
            .isSameAs(sut)
            .isInstanceOf(LTernaryOperator.class);
    }

    @Test
    public void testNestingX() {
        assertThat(sut.nestX())
            .isSameAs(sut)
            .isInstanceOf(LTernaryOperatorX.class);
    }

    @Test
    public void testShovingX() {
        assertThat(sut.shoveX())
            .isSameAs(sut)
            .isInstanceOf(LTernaryOperatorX.class);
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void testShove() {

        // given
        LTernaryOperator<T> sutThrowing = LTernaryOperator.l((T t1,T t2,T t3) -> {
            throw new UnsupportedOperationException();
        });

        // when
        sutThrowing.shove().doApply((T)Integer.valueOf(100),(T)Integer.valueOf(100),(T)Integer.valueOf(100));
    }

    @Test
    public void testHandle() throws ParseException {

        // given
        LTernaryOperator<T> sutThrowing = LTernaryOperator.l((T t1,T t2,T t3) -> {
            throw new UnsupportedOperationException();
        });

        // when
        LTernaryOperator<T> wrapped = sutThrowing.handle(UnsupportedOperationException.class, t -> {
            throw new IllegalArgumentException(EXCEPTION_WAS_WRAPPED, t);
        });

        // then
        try {
            wrapped.doApply((T)Integer.valueOf(100),(T)Integer.valueOf(100),(T)Integer.valueOf(100));
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
                .contains("LTernaryOperator: T doApply(T t1,T t2,T t3)");
    }


    @Test
    public void isThrowing() {
        assertThat(sut.isThrowing())
            .isFalse();
    }


}
