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

package eu.lunisolar.magma.func.consumer.primitives.obj;

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
public class LObjDoubleConsumerXTest<T,X extends ParseException> {
    private static final String ORIGINAL_MESSAGE = "Original message";
    private static final String EXCEPTION_WAS_WRAPPED = "Exception was wrapped.";
    private static final String NO_EXCEPTION_WERE_THROWN = "No exception were thrown.";



    private LObjDoubleConsumerX<T,X> sut = new LObjDoubleConsumerX(){
        public  void doAccept(Object t, double d) throws ParseException {
            Function4U.doNothing();
        }
    };

    private LObjDoubleConsumer<T> opposite = new LObjDoubleConsumer(){
        public  void doAccept(Object t, double d)  {
            Function4U.doNothing();
        }
    };


    private java.util.function.ObjDoubleConsumer jre = (Object t, double d) -> Function4U.doNothing();


    private LObjDoubleConsumerX<T,ParseException> sutAlwaysThrowing = LObjDoubleConsumerX.lX((T t, double d) -> {
            throw new ParseException(ORIGINAL_MESSAGE, 0);
    });

    private LObjDoubleConsumerX<T,RuntimeException> sutAlwaysThrowingUnckeck = LObjDoubleConsumerX.lX((T t, double d) -> {
            throw new IndexOutOfBoundsException(ORIGINAL_MESSAGE);
    });




    @Test
    public void testFunctionalInterfaceDescription() throws X {
        assertThat(sut.functionalInterfaceDescription())
            .isEqualTo("LObjDoubleConsumerX: void doAccept(T t, double d) throws X");
    }

    @Test
    public void testLXMethod() throws X {
        assertThat(LObjDoubleConsumerX.lX((Object t, double d) -> Function4U.doNothing() ))
            .isInstanceOf(LObjDoubleConsumerX.class);
    }

    @Test
    public void testWrapXMethod() throws X {
        assertThat(LObjDoubleConsumerX.wrapX(opposite))
            .isInstanceOf(LObjDoubleConsumerX.class);
    }

    @Test
    public void testWrapStdMethod() throws X {
        assertThat(LObjDoubleConsumerX.wrap(jre))
            .isInstanceOf(LObjDoubleConsumerX.class);
    }


    @Test
    public void testWrapExceptionMethodWrapsTheException() throws X {

        // given
        LObjDoubleConsumerX<T,X> sutThrowing = LObjDoubleConsumerX.lX((T t, double d) -> {
            throw new UnsupportedOperationException();
        });

        // when
        LObjDoubleConsumerX<T,X> wrapped = sutThrowing.handleX(h -> {
            h.wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED);
        });

        // then
        try {
            wrapped.doAccept((T)Integer.valueOf(100),(double)100);
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
        LObjDoubleConsumerX<T,X> sutThrowing = LObjDoubleConsumerX.lX((T t, double d) -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LObjDoubleConsumerX<T,X> wrapped = sutThrowing.handleX(handler -> handler
                .wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED)
                .throwIf(IndexOutOfBoundsException.class));

        // then
        try {
            wrapped.doAccept((T)Integer.valueOf(100),(double)100);
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
        LObjDoubleConsumerX<T,X> sutThrowing = LObjDoubleConsumerX.lX((T t, double d) -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LObjDoubleConsumerX<T,X> wrapped = sutThrowing.handleX(handler -> handler
                .wrapWhen(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED)
                .throwIf(IndexOutOfBoundsException.class));

        // then
        try {
            wrapped.doAccept((T)Integer.valueOf(100),(double)100);
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
        LObjDoubleConsumerX<T,X> sutThrowing = LObjDoubleConsumerX.lX((T t, double d) -> {
            throw (X) new ParseException(ORIGINAL_MESSAGE, 0);
        });

        // when
        LObjDoubleConsumerX<T,X> wrapped = sutThrowing.handleX(h -> {
        });

        // then
        try {
            wrapped.doAccept((T)Integer.valueOf(100),(double)100);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(NestedException.class)
                    .hasCauseExactlyInstanceOf(ParseException.class)
                    .hasMessage(ORIGINAL_MESSAGE);
        }
    }



    // <editor-fold desc="compose (functional)">

    @Test
    public void testfromDouble() throws X {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LObjDoubleConsumerX<Integer ,X> sutO = (Integer t, double d) -> {
                mainFunctionCalled.set(true);
                assertThat(t).isEqualTo((T)Integer.valueOf(90));
                assertThat( d).isEqualTo((double)91);
        };

        LFunctionX<Integer ,Integer ,X> before1 = p0 -> {
            assertThat(p0).isEqualTo((T)Integer.valueOf(80));
            beforeCalls.incrementAndGet();
            return 90;
        };
        LDoubleUnaryOperatorX<X> before2 = p1 -> {
            assertThat(p1).isEqualTo((double)81);
            beforeCalls.incrementAndGet();
            return (double)91;
        };

        //when
        LObjDoubleConsumerX<Integer ,X> function = sutO.fromDouble(before1,before2);
        function.doAccept((Integer )Integer.valueOf(80),(double)81);

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(beforeCalls.get()).isEqualTo(2);
    }


    @Test
    public void testfrom() throws X {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LObjDoubleConsumerX<Integer ,X> sutO = (Integer t, double d) -> {
                mainFunctionCalled.set(true);
                assertThat(t).isEqualTo((T)Integer.valueOf(90));
                assertThat( d).isEqualTo((double)91);
        };

        LFunctionX<Integer ,Integer ,X> before1 = p0 -> {
            assertThat(p0).isEqualTo(Integer.valueOf(80));
            beforeCalls.incrementAndGet();
            return 90;
        };
        LToDoubleFunctionX<Integer ,X> before2 = p1 -> {
            assertThat(p1).isEqualTo(Integer.valueOf(81));
            beforeCalls.incrementAndGet();
            return (double)91;
        };

        //when
        LBiConsumerX<Integer ,Integer ,X> function = sutO.from(before1,before2);
        function.doAccept((Integer )Integer.valueOf(80),(Integer )Integer.valueOf(81));

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(beforeCalls.get()).isEqualTo(2);
    }

    // </editor-fold>

    @Test
    public void testAndThen() throws X {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);

         //given (+ some assertions)
        LObjDoubleConsumerX<Integer ,X> sutO = (Integer t, double d) -> {
                mainFunctionCalled.set(true);
                assertThat(t).isEqualTo((T)Integer.valueOf(80));
                assertThat( d).isEqualTo((double)81);
        };

        LObjDoubleConsumerX<Integer ,X> thenFunction = (Integer t, double d) -> {
                thenFunctionCalled.set(true);
                assertThat(t).isEqualTo((T)Integer.valueOf(80));
                assertThat( d).isEqualTo((double)81);
        };

        //when
        LObjDoubleConsumerX<Integer ,X> function = sutO.andThen(thenFunction);
        function.doAccept((Integer )Integer.valueOf(80),(double)81);

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);
    }


    @Test
    public void testNesting() {
        assertThat(sut.nest())
            .isInstanceOf(LObjDoubleConsumer.class);
    }

    @Test
    public void testShoving() {
        assertThat(sut.shove())
            .isInstanceOf(LObjDoubleConsumer.class);
    }

    @Test
    public void testNestingX() {
        assertThat(sut.nestX())
            .isInstanceOf(LObjDoubleConsumerX.class);
    }

    @Test
    public void testShovingX() {
        assertThat(sut.shoveX())
            .isInstanceOf(LObjDoubleConsumerX.class);
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void testShove() {

        // given
        LObjDoubleConsumerX<T,X> sutThrowing = LObjDoubleConsumerX.lX((T t, double d) -> {
            throw new UnsupportedOperationException();
        });

        // when
        sutThrowing.shove().doAccept((T)Integer.valueOf(100),(double)100);
    }

    @Test
    public void testHandle() throws X {

        // given
        LObjDoubleConsumerX<T,X> sutThrowing = LObjDoubleConsumerX.lX((T t, double d) -> {
            throw new UnsupportedOperationException();
        });

        // when
        LObjDoubleConsumerX<T,X> wrapped = sutThrowing.handleX(h -> {
            h.wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED);
        });

        // then
        try {
            wrapped.doAccept((T)Integer.valueOf(100),(double)100);
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
                .contains("LObjDoubleConsumerX: void doAccept(T t, double d) throws X");
    }


    @Test
    public void isThrowing() {
        assertThat(sut.isThrowing())
            .isTrue();
    }


}
