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

package eu.lunisolar.magma.func.consumer.primitives;

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
public class LDoubleConsumerTest<X extends ParseException> {
    private static final String ORIGINAL_MESSAGE = "Original message";
    private static final String EXCEPTION_WAS_WRAPPED = "Exception was wrapped.";
    private static final String NO_EXCEPTION_WERE_THROWN = "No exception were thrown.";



    private LDoubleConsumer sut = new LDoubleConsumer(){
        public  void doAccept(double d)  {
            Function4U.doNothing();
        }
    };

    private LDoubleConsumerX<X> opposite = new LDoubleConsumerX(){
        public  void doAccept(double d) throws ParseException {
            Function4U.doNothing();
        }
    };


    private java.util.function.DoubleConsumer jre = (double d) -> Function4U.doNothing();



    private LDoubleConsumer sutAlwaysThrowingUnckeck = LDoubleConsumer.l((double d) -> {
            throw new IndexOutOfBoundsException(ORIGINAL_MESSAGE);
    });




    @Test
    public void testFunctionalInterfaceDescription() throws X {
        assertThat(sut.functionalInterfaceDescription())
            .isEqualTo("LDoubleConsumer: void doAccept(double d)");
    }

    @Test
    public void testLMethod() throws X {
        assertThat(LDoubleConsumer.l((double d) -> Function4U.doNothing() ))
            .isInstanceOf(LDoubleConsumer.class);
    }

    @Test
    public void testWrapMethod() throws X {
        assertThat(LDoubleConsumer.wrap(opposite))
            .isInstanceOf(LDoubleConsumer.class);
    }

    @Test
    public void testWrapStdMethod() throws X {
        assertThat(LDoubleConsumer.wrap(jre))
            .isInstanceOf(LDoubleConsumer.class);
    }

    @Test
    public void testWrapMethodDoNotWrapsRuntimeException() throws X {
        // given
        LDoubleConsumerX<X> sutThrowing = LDoubleConsumerX.lX((double d) -> {
            throw new UnsupportedOperationException(ORIGINAL_MESSAGE);
        });

        // when
        LDoubleConsumer wrapped = LDoubleConsumer.wrap(sutThrowing);

        // then
        try {
            wrapped.doAccept((double)100);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(UnsupportedOperationException.class)
                    .hasNoCause()
                    .hasMessage(ORIGINAL_MESSAGE);
        }
    }

    @Test
    public void testWrapMethodWrapsCheckedException() throws X {
        // given
        LDoubleConsumerX<ParseException> sutThrowing = LDoubleConsumerX.lX((double d) -> {
            throw new ParseException(ORIGINAL_MESSAGE, 0);
        });

        // when
        LDoubleConsumer wrapped = LDoubleConsumer.wrap(sutThrowing);

        // then
        try {
            wrapped.doAccept((double)100);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(NestedException.class)
                    .hasCauseExactlyInstanceOf(ParseException.class)
                    .hasMessage(ORIGINAL_MESSAGE);
        }
    }


    @Test
    public void testWrapExceptionMethodWrapsTheException() throws X {

        // given
        LDoubleConsumer sutThrowing = LDoubleConsumer.l((double d) -> {
            throw new UnsupportedOperationException();
        });

        // when
        LDoubleConsumer wrapped = sutThrowing.handle(h -> {
            h.wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED);
        });

        // then
        try {
            wrapped.doAccept((double)100);
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
        LDoubleConsumer sutThrowing = LDoubleConsumer.l((double d) -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LDoubleConsumer wrapped = sutThrowing.handle(handler -> handler
                .wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED)
                .throwIf(IndexOutOfBoundsException.class));

        // then
        try {
            wrapped.doAccept((double)100);
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
        LDoubleConsumer sutThrowing = LDoubleConsumer.l((double d) -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LDoubleConsumer wrapped = sutThrowing.handle(handler -> handler
                .wrapWhen(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED)
                .throwIf(IndexOutOfBoundsException.class));

        // then
        try {
            wrapped.doAccept((double)100);
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
        LDoubleConsumer sutThrowing = LDoubleConsumer.l((double d) -> {
            throw new UnsupportedOperationException(ORIGINAL_MESSAGE);
        });

        // when
        LDoubleConsumer wrapped = sutThrowing.handle(h -> {
        });

        // then
        try {
            wrapped.doAccept((double)100);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
             .isExactlyInstanceOf(UnsupportedOperationException.class)
             .hasNoCause()
             .hasMessage(ORIGINAL_MESSAGE);
        }
    }



    // <editor-fold desc="compose (functional)">

    @Test
    public void testfromDouble() throws X {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LDoubleConsumer sutO = (double d) -> {
                mainFunctionCalled.set(true);
                assertThat(d).isEqualTo((double)90);
        };

        LDoubleUnaryOperator before1 = p0 -> {
            assertThat(p0).isEqualTo((double)80);
            beforeCalls.incrementAndGet();
            return (double)90;
        };

        //when
        LDoubleConsumer function = sutO.fromDouble(before1);
        function.doAccept((double)80);

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(beforeCalls.get()).isEqualTo(1);
    }


    @Test
    public void testfrom() throws X {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LDoubleConsumer sutO = (double d) -> {
                mainFunctionCalled.set(true);
                assertThat(d).isEqualTo((double)90);
        };

        LToDoubleFunction<Integer > before1 = p0 -> {
            assertThat(p0).isEqualTo(Integer.valueOf(80));
            beforeCalls.incrementAndGet();
            return (double)90;
        };

        //when
        LConsumer<Integer > function = sutO.from(before1);
        function.doAccept((Integer )Integer.valueOf(80));

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(beforeCalls.get()).isEqualTo(1);
    }

    // </editor-fold>

    @Test
    public void testAndThen() throws X {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);

         //given (+ some assertions)
        LDoubleConsumer sutO = (double d) -> {
                mainFunctionCalled.set(true);
                assertThat(d).isEqualTo((double)80);
        };

        LDoubleConsumer thenFunction = (double d) -> {
                thenFunctionCalled.set(true);
                assertThat(d).isEqualTo((double)80);
        };

        //when
        LDoubleConsumer function = sutO.andThen(thenFunction);
        function.doAccept((double)80);

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);
    }


    @Test
    public void testNesting() {
        assertThat(sut.nest())
            .isSameAs(sut)
            .isInstanceOf(LDoubleConsumer.class);
    }

    @Test
    public void testShoving() {
        assertThat(sut.shove())
            .isSameAs(sut)
            .isInstanceOf(LDoubleConsumer.class);
    }

    @Test
    public void testNestingX() {
        assertThat(sut.nestX())
            .isSameAs(sut)
            .isInstanceOf(LDoubleConsumerX.class);
    }

    @Test
    public void testShovingX() {
        assertThat(sut.shoveX())
            .isSameAs(sut)
            .isInstanceOf(LDoubleConsumerX.class);
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void testShove() {

        // given
        LDoubleConsumer sutThrowing = LDoubleConsumer.l((double d) -> {
            throw new UnsupportedOperationException();
        });

        // when
        sutThrowing.shove().doAccept((double)100);
    }

    @Test
    public void testHandle() throws X {

        // given
        LDoubleConsumer sutThrowing = LDoubleConsumer.l((double d) -> {
            throw new UnsupportedOperationException();
        });

        // when
        LDoubleConsumer wrapped = sutThrowing.handle(h -> {
            h.wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED);
        });

        // then
        try {
            wrapped.doAccept((double)100);
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
                .contains("LDoubleConsumer: void doAccept(double d)");
    }


    @Test
    public void isThrowing() {
        assertThat(sut.isThrowing())
            .isFalse();
    }


}
