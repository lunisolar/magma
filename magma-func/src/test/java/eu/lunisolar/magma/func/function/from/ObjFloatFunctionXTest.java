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

package eu.lunisolar.magma.func.function.from;

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
public class ObjFloatFunctionXTest<T,R,X extends ParseException> {
    private static final String ORIGINAL_MESSAGE = "Original message";
    private static final String EXCEPTION_WAS_WRAPPED = "Exception was wrapped.";
    private static final String NO_EXCEPTION_WERE_THROWN = "No exception were thrown.";

    private Object  testValue = (R)Integer.valueOf(100);



    private ObjFloatFunctionX<T,R,X> sut = new ObjFloatFunctionX(){
        public @Nullable Object  apply(Object t, float f) throws ParseException {
            return testValue;
        }
    };

    private ObjFloatFunction<T,R> opposite = new ObjFloatFunction(){
        public @Nullable Object  apply(Object t, float f)  {
            return testValue;
        }
    };

    private ObjFloatFunctionX<T,R,X> sutNull = new ObjFloatFunctionX(){
        public @Nullable Object  apply(Object t, float f) throws ParseException {
            return null;
        }
    };



    @Test
    public void testTheResult() throws ParseException {
        assertThat(sut.apply((T)Integer.valueOf(100),(float)100))
            .isSameAs(testValue);
    }

    @Test
    public void testNonNullShouldNotModifyValue() throws ParseException {
        assertThat(sut.nonNull((T)Integer.valueOf(100),(float)100))
            .isSameAs(testValue);
    }

    @Test(expectedExceptions=NullPointerException.class, expectedExceptionsMessageRegExp="\\QEvaluated value by nonNull() method cannot be null (ObjFloatFunctionX: R apply(T t, float f) throws X).\\E")
    public void testNonNullCapturesNull() throws ParseException {
        sutNull.nonNull((T)Integer.valueOf(100),(float)100);
    }


    @Test
    public void testFunctionalInterfaceDescription() throws ParseException {
        assertThat(sut.functionalInterfaceDescription())
            .isEqualTo("ObjFloatFunctionX: R apply(T t, float f) throws X");
    }

    @Test
    public void testLXMethod() throws ParseException {
        assertThat(ObjFloatFunctionX.lX((Object t, float f) -> testValue ))
            .isInstanceOf(ObjFloatFunctionX.class);
    }

    @Test
    public void testWrapXMethod() throws ParseException {
        assertThat(ObjFloatFunctionX.wrapX(opposite))
            .isInstanceOf(ObjFloatFunctionX.class);
    }


    @Test
    public void testWrapExceptionMethodWrapsTheException() throws ParseException {

        // given
        ObjFloatFunctionX<T,R,X> sutThrowing = ObjFloatFunctionX.lX((T t, float f) -> {
            throw new UnsupportedOperationException();
        });

        // when
        ObjFloatFunctionX<T,R,X> wrapped = ObjFloatFunctionX.wrapException(sutThrowing, UnsupportedOperationException.class, t -> {
            throw new IllegalArgumentException(EXCEPTION_WAS_WRAPPED, t);
        });

        // then
        try {
            wrapped.apply((T)Integer.valueOf(100),(float)100);
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
        ObjFloatFunctionX<T,R,X> sutThrowing = ObjFloatFunctionX.lX((T t, float f) -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        ObjFloatFunctionX<T,R,X> wrapped = ObjFloatFunctionX.wrapException(sutThrowing, UnsupportedOperationException.class, t -> {
            throw new IllegalArgumentException(EXCEPTION_WAS_WRAPPED, t);
        });

        // then
        try {
            wrapped.apply((T)Integer.valueOf(100),(float)100);
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
        ObjFloatFunctionX<T,R,X> sutThrowing = ObjFloatFunctionX.lX((T t, float f) -> {
            throw new UnsupportedOperationException();
        });

        // when
        ObjFloatFunctionX<T,R,X> wrapped = ObjFloatFunctionX.wrapException(sutThrowing, UnsupportedOperationException.class, t -> {
            return null;
        });

        // then
        try {
            wrapped.apply((T)Integer.valueOf(100),(float)100);
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
    public void testfromFloat() throws ParseException {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        ObjFloatFunctionX<Integer ,Integer ,X> sutO = (Integer t, float f) -> {
                mainFunctionCalled.set(true);
                assertThat(t).isEqualTo((T)Integer.valueOf(90));
                assertThat( f).isEqualTo((float)91);
                return 9;
        };

        FunctionX<Integer ,Integer ,X> before1 = p0 -> {
            assertThat(p0).isEqualTo((T)Integer.valueOf(80));
            beforeCalls.incrementAndGet();
            return 90;
        };
        FloatUnaryOperatorX<X> before2 = p1 -> {
            assertThat(p1).isEqualTo((float)81);
            beforeCalls.incrementAndGet();
            return (float)91;
        };

        //when
        ObjFloatFunctionX<Integer ,Integer ,X> function = sutO.fromFloat(before1,before2);
        function.apply((Integer )Integer.valueOf(80),(float)81);

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(beforeCalls.get()).isEqualTo(2);
    }


    @Test
    public void testfrom() throws ParseException {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        ObjFloatFunctionX<Integer ,Integer ,X> sutO = (Integer t, float f) -> {
                mainFunctionCalled.set(true);
                assertThat(t).isEqualTo((T)Integer.valueOf(90));
                assertThat( f).isEqualTo((float)91);
                return 9;
        };

        FunctionX<Integer ,Integer ,X> before1 = p0 -> {
            assertThat(p0).isEqualTo(Integer.valueOf(80));
            beforeCalls.incrementAndGet();
            return 90;
        };
        ToFloatFunctionX<Integer ,X> before2 = p1 -> {
            assertThat(p1).isEqualTo(Integer.valueOf(81));
            beforeCalls.incrementAndGet();
            return (float)91;
        };

        //when
        BiFunctionX<Integer ,Integer ,Integer ,X> function = sutO.from(before1,before2);
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
        ObjFloatFunctionX<Integer ,Integer ,X> sutO = (Integer t, float f) -> {
                mainFunctionCalled.set(true);
                assertThat(t).isEqualTo((T)Integer.valueOf(80));
                assertThat( f).isEqualTo((float)81);
                return Integer.valueOf(90);
        };

        FunctionX<Integer ,Integer ,X> thenFunction = p -> {
                thenFunctionCalled.set(true);
                // R
                assertThat(p).isEqualTo(Integer.valueOf(90));
                // V
                return Integer.valueOf(100);
        };

        //when
        ObjFloatFunctionX<Integer ,Integer ,X> function = sutO.then(thenFunction);
        Integer  finalValue = function.apply((Integer )Integer.valueOf(80),(float)81);

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
        ObjFloatFunctionX<Integer ,Integer ,X> sutO = (Integer t, float f) -> {
                mainFunctionCalled.set(true);
                assertThat(t).isEqualTo((T)Integer.valueOf(80));
                assertThat( f).isEqualTo((float)81);
                return Integer.valueOf(90);
        };

        ConsumerX<Integer ,X> thenFunction = p -> {
                thenFunctionCalled.set(true);
                // R
                assertThat(p).isEqualTo(Integer.valueOf(90));
        };

        //when
        ObjFloatConsumerX<Integer ,X> function = sutO.then(thenFunction);
        function.accept((Integer )Integer.valueOf(80),(float)81);

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    // </editor-fold>
    @Test
    public void testNonThrowing() {
        assertThat(sut.nonThrowing()).isInstanceOf(ObjFloatFunction.class);
    }

    @Test
    public void testUncheck() {
        assertThat(sut.uncheck()).isInstanceOf(ObjFloatFunctionX.class);
    }


    @Test
    public void testHandle() throws ParseException {

        // given
        ObjFloatFunctionX<T,R,X> sutThrowing = ObjFloatFunctionX.lX((T t, float f) -> {
            throw new UnsupportedOperationException();
        });

        // when
        ObjFloatFunctionX<T,R,X> wrapped = sutThrowing.handle(UnsupportedOperationException.class, t -> {
            throw new IllegalArgumentException(EXCEPTION_WAS_WRAPPED, t);
        });

        // then
        try {
            wrapped.apply((T)Integer.valueOf(100),(float)100);
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
                .contains("ObjFloatFunctionX: R apply(T t, float f) throws X");
    }

}
