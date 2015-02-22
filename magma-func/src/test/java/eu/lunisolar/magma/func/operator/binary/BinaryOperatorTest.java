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
public class BinaryOperatorTest<T,X extends ParseException> {
    private static final String ORIGINAL_MESSAGE = "Original message";
    private static final String EXCEPTION_WAS_WRAPPED = "Exception was wrapped.";
    private static final String NO_EXCEPTION_WERE_THROWN = "No exception were thrown.";

    private Object  testValue = (T)Integer.valueOf(100);



    private BinaryOperator<T> sut = new BinaryOperator(){
        public @Nullable Object  apply(Object t1,Object t2)  {
            return testValue;
        }
    };

    private BinaryOperatorX<T,X> opposite = new BinaryOperatorX(){
        public @Nullable Object  apply(Object t1,Object t2) throws ParseException {
            return testValue;
        }
    };

    private BinaryOperator<T> sutNull = new BinaryOperator(){
        public @Nullable Object  apply(Object t1,Object t2)  {
            return null;
        }
    };


    private java.util.function.BinaryOperator jre = (Object t1,Object t2) -> testValue;


    @Test
    public void testTheResult() throws ParseException {
        assertThat(sut.apply((T)Integer.valueOf(100),(T)Integer.valueOf(100)))
            .isSameAs(testValue);
    }

    @Test
    public void testNonNullShouldNotModifyValue() throws ParseException {
        assertThat(sut.nonNull((T)Integer.valueOf(100),(T)Integer.valueOf(100)))
            .isSameAs(testValue);
    }

    @Test(expectedExceptions=NullPointerException.class, expectedExceptionsMessageRegExp="\\QEvaluated value by nonNull() method cannot be null (BinaryOperator: T apply(T t1,T t2)).\\E")
    public void testNonNullCapturesNull() throws ParseException {
        sutNull.nonNull((T)Integer.valueOf(100),(T)Integer.valueOf(100));
    }


    @Test
    public void testFunctionalInterfaceDescription() throws ParseException {
        assertThat(sut.functionalInterfaceDescription())
            .isEqualTo("BinaryOperator: T apply(T t1,T t2)");
    }

    @Test
    public void testLMethod() throws ParseException {
        assertThat(BinaryOperator.l((Object t1,Object t2) -> testValue ))
            .isInstanceOf(BinaryOperator.class);
    }

    @Test
    public void testWrapMethod() throws ParseException {
        assertThat(BinaryOperator.wrap(opposite))
            .isInstanceOf(BinaryOperator.class);
    }

    @Test
    public void testWrapStdMethod() throws ParseException {
        assertThat(BinaryOperator.wrapStd(jre))
            .isInstanceOf(BinaryOperator.class);
    }

    @Test
    public void testWrapMethodDoNotWrapsRuntimeException() throws ParseException {
        // given
        BinaryOperatorX<T,X> sutThrowing = BinaryOperatorX.lX((T t1,T t2) -> {
            throw new UnsupportedOperationException(ORIGINAL_MESSAGE);
        });

        // when
        BinaryOperator<T> wrapped = BinaryOperator.wrap(sutThrowing);

        // then
        try {
            wrapped.apply((T)Integer.valueOf(100),(T)Integer.valueOf(100));
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
        BinaryOperatorX<T,ParseException> sutThrowing = BinaryOperatorX.lX((T t1,T t2) -> {
            throw new ParseException(ORIGINAL_MESSAGE, 0);
        });

        // when
        BinaryOperator<T> wrapped = BinaryOperator.wrap(sutThrowing);

        // then
        try {
            wrapped.apply((T)Integer.valueOf(100),(T)Integer.valueOf(100));
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
        BinaryOperator<T> sutThrowing = BinaryOperator.l((T t1,T t2) -> {
            throw new UnsupportedOperationException();
        });

        // when
        BinaryOperator<T> wrapped = BinaryOperator.wrapException(sutThrowing, UnsupportedOperationException.class, t -> {
            throw new IllegalArgumentException(EXCEPTION_WAS_WRAPPED, t);
        });

        // then
        try {
            wrapped.apply((T)Integer.valueOf(100),(T)Integer.valueOf(100));
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
        BinaryOperator<T> sutThrowing = BinaryOperator.l((T t1,T t2) -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        BinaryOperator<T> wrapped = BinaryOperator.wrapException(sutThrowing, UnsupportedOperationException.class, t -> {
            throw new IllegalArgumentException(EXCEPTION_WAS_WRAPPED, t);
        });

        // then
        try {
            wrapped.apply((T)Integer.valueOf(100),(T)Integer.valueOf(100));
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
        BinaryOperator<T> sutThrowing = BinaryOperator.l((T t1,T t2) -> {
            throw new UnsupportedOperationException();
        });

        // when
        BinaryOperator<T> wrapped = BinaryOperator.wrapException(sutThrowing, UnsupportedOperationException.class, t -> {
            return null;
        });

        // then
        try {
            wrapped.apply((T)Integer.valueOf(100),(T)Integer.valueOf(100));
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(ExceptionNotHandled.class)
                    .hasCauseExactlyInstanceOf(UnsupportedOperationException.class)
                    .hasMessage("Handler has not processed the exception.");
        }
    }

    @Test
    public void minBy() throws ParseException  {
        //when
        BinaryOperator<Integer > min =  BinaryOperator.minBy((Integer i1, Integer i2) ->Integer.compare(i1, i2));

        //then
        assertThat(min.apply(45, 56))
                .isEqualTo(45);
        assertThat(min.apply(56, 45))
                       .isEqualTo(45);

    }

    @Test
    public void maxBy() throws ParseException  {
        //when
        BinaryOperator<Integer > max =  BinaryOperator.maxBy((Integer i1, Integer i2) ->Integer.compare(i1, i2));

        //then
        assertThat(max.apply(45, 56))
                .isEqualTo(56);
        assertThat(max.apply(56, 45))
                        .isEqualTo(56);
    }



    // <editor-fold desc="then (functional)">

    @Test
    public void testThen0() throws ParseException  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);


        //given (+ some assertions)
        BinaryOperator<Integer > sutO = (Integer t1,Integer t2) -> {
                mainFunctionCalled.set(true);
                assertThat(t1).isEqualTo((T)Integer.valueOf(80));
                assertThat(t2).isEqualTo((T)Integer.valueOf(81));
                return Integer.valueOf(90);
        };

        Function<Integer ,Integer > thenFunction = p -> {
                thenFunctionCalled.set(true);
                // T
                assertThat(p).isEqualTo(Integer.valueOf(90));
                // V
                return Integer.valueOf(100);
        };

        //when
        BiFunction<Integer ,Integer ,Integer > function = sutO.then(thenFunction);
        Integer  finalValue = function.apply((Integer )Integer.valueOf(80),(Integer )Integer.valueOf(81));

        //then - finals
        assertThat(finalValue).isEqualTo(Integer.valueOf(100));
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    // </editor-fold>
    @Test
    public void testStd() {
        assertThat(sut.std()).isInstanceOf(java.util.function.BinaryOperator.class);
    }

    @Test
    public void testNonThrowing() {
        assertThat(sut.nonThrowing()).isInstanceOf(BinaryOperator.class);
    }

    @Test
    public void testUncheck() {
        assertThat(sut.uncheck()).isInstanceOf(BinaryOperatorX.class);
    }


    @Test
    public void testHandle() throws ParseException {

        // given
        BinaryOperator<T> sutThrowing = BinaryOperator.l((T t1,T t2) -> {
            throw new UnsupportedOperationException();
        });

        // when
        BinaryOperator<T> wrapped = sutThrowing.handle(UnsupportedOperationException.class, t -> {
            throw new IllegalArgumentException(EXCEPTION_WAS_WRAPPED, t);
        });

        // then
        try {
            wrapped.apply((T)Integer.valueOf(100),(T)Integer.valueOf(100));
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
                .contains("BinaryOperator: T apply(T t1,T t2)");
    }

}
