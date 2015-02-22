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

package eu.lunisolar.magma.func.consumer.primitives;

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
public class LongConsumerXTest<X extends ParseException> {
    private static final String ORIGINAL_MESSAGE = "Original message";
    private static final String EXCEPTION_WAS_WRAPPED = "Exception was wrapped.";
    private static final String NO_EXCEPTION_WERE_THROWN = "No exception were thrown.";



    private LongConsumerX<X> sut = new LongConsumerX(){
        public  void accept(long l) throws ParseException {
            Function4U.doNothing();
        }
    };

    private LongConsumer opposite = new LongConsumer(){
        public  void accept(long l)  {
            Function4U.doNothing();
        }
    };


    private java.util.function.LongConsumer jre = (long l) -> Function4U.doNothing();




    @Test
    public void testFunctionalInterfaceDescription() throws ParseException {
        assertThat(sut.functionalInterfaceDescription())
            .isEqualTo("LongConsumerX: void accept(long l) throws X");
    }

    @Test
    public void testLXMethod() throws ParseException {
        assertThat(LongConsumerX.lX((long l) -> Function4U.doNothing() ))
            .isInstanceOf(LongConsumerX.class);
    }

    @Test
    public void testWrapXMethod() throws ParseException {
        assertThat(LongConsumerX.wrapX(opposite))
            .isInstanceOf(LongConsumerX.class);
    }

    @Test
    public void testWrapStdMethod() throws ParseException {
        assertThat(LongConsumerX.wrapStd(jre))
            .isInstanceOf(LongConsumerX.class);
    }


    @Test
    public void testWrapExceptionMethodWrapsTheException() throws ParseException {

        // given
        LongConsumerX<X> sutThrowing = LongConsumerX.lX((long l) -> {
            throw new UnsupportedOperationException();
        });

        // when
        LongConsumerX<X> wrapped = LongConsumerX.wrapException(sutThrowing, UnsupportedOperationException.class, t -> {
            throw new IllegalArgumentException(EXCEPTION_WAS_WRAPPED, t);
        });

        // then
        try {
            wrapped.accept((long)100);
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
        LongConsumerX<X> sutThrowing = LongConsumerX.lX((long l) -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LongConsumerX<X> wrapped = LongConsumerX.wrapException(sutThrowing, UnsupportedOperationException.class, t -> {
            throw new IllegalArgumentException(EXCEPTION_WAS_WRAPPED, t);
        });

        // then
        try {
            wrapped.accept((long)100);
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
        LongConsumerX<X> sutThrowing = LongConsumerX.lX((long l) -> {
            throw new UnsupportedOperationException();
        });

        // when
        LongConsumerX<X> wrapped = LongConsumerX.wrapException(sutThrowing, UnsupportedOperationException.class, t -> {
            return null;
        });

        // then
        try {
            wrapped.accept((long)100);
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
    public void testfromLong() throws ParseException {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LongConsumerX<X> sutO = (long l) -> {
                mainFunctionCalled.set(true);
                assertThat(l).isEqualTo((long)90);
        };

        LongUnaryOperatorX<X> before1 = p0 -> {
            assertThat(p0).isEqualTo((long)80);
            beforeCalls.incrementAndGet();
            return (long)90;
        };

        //when
        LongConsumerX<X> function = sutO.fromLong(before1);
        function.accept((long)80);

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(beforeCalls.get()).isEqualTo(1);
    }


    @Test
    public void testfrom() throws ParseException {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LongConsumerX<X> sutO = (long l) -> {
                mainFunctionCalled.set(true);
                assertThat(l).isEqualTo((long)90);
        };

        ToLongFunctionX<Integer ,X> before1 = p0 -> {
            assertThat(p0).isEqualTo(Integer.valueOf(80));
            beforeCalls.incrementAndGet();
            return (long)90;
        };

        //when
        ConsumerX<Integer ,X> function = sutO.from(before1);
        function.accept((Integer )Integer.valueOf(80));

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(beforeCalls.get()).isEqualTo(1);
    }

    // </editor-fold>

    @Test
    public void testAndThen() throws ParseException {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);

         //given (+ some assertions)
        LongConsumerX<X> sutO = (long l) -> {
                mainFunctionCalled.set(true);
                assertThat(l).isEqualTo((long)80);
        };

        LongConsumerX<X> thenFunction = (long l) -> {
                thenFunctionCalled.set(true);
                assertThat(l).isEqualTo((long)80);
        };

        //when
        LongConsumerX<X> function = sutO.andThen(thenFunction);
        function.accept((long)80);

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);
    }

    @Test
    public void testStd() {
        assertThat(sut.std()).isInstanceOf(java.util.function.LongConsumer.class);
    }

    @Test
    public void testNonThrowing() {
        assertThat(sut.nonThrowing()).isInstanceOf(LongConsumer.class);
    }

    @Test
    public void testUncheck() {
        assertThat(sut.uncheck()).isInstanceOf(LongConsumerX.class);
    }


    @Test
    public void testHandle() throws ParseException {

        // given
        LongConsumerX<X> sutThrowing = LongConsumerX.lX((long l) -> {
            throw new UnsupportedOperationException();
        });

        // when
        LongConsumerX<X> wrapped = sutThrowing.handle(UnsupportedOperationException.class, t -> {
            throw new IllegalArgumentException(EXCEPTION_WAS_WRAPPED, t);
        });

        // then
        try {
            wrapped.accept((long)100);
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
                .contains("LongConsumerX: void accept(long l) throws X");
    }

}
