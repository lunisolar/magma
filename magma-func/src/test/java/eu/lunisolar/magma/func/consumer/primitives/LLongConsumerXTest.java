/*
 * This file is part of "lunisolar-magma".
 *
 * (C) Copyright 2015 Lunisolar (http://lunisolar.eu/).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
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
public class LLongConsumerXTest<X extends ParseException> {
    private static final String ORIGINAL_MESSAGE = "Original message";
    private static final String EXCEPTION_WAS_WRAPPED = "Exception was wrapped.";
    private static final String NO_EXCEPTION_WERE_THROWN = "No exception were thrown.";



    private LLongConsumerX<X> sut = new LLongConsumerX(){
        public  void doAccept(long l) throws ParseException {
            Function4U.doNothing();
        }
    };

    private LLongConsumer opposite = new LLongConsumer(){
        public  void doAccept(long l)  {
            Function4U.doNothing();
        }
    };


    private java.util.function.LongConsumer jre = (long l) -> Function4U.doNothing();


    private LLongConsumerX<ParseException> sutAlwaysThrowing = LLongConsumerX.lX((long l) -> {
            throw new ParseException(ORIGINAL_MESSAGE, 0);
    });

    private LLongConsumerX<RuntimeException> sutAlwaysThrowingUnckeck = LLongConsumerX.lX((long l) -> {
            throw new IndexOutOfBoundsException(ORIGINAL_MESSAGE);
    });




    @Test
    public void testFunctionalInterfaceDescription() throws X {
        assertThat(sut.functionalInterfaceDescription())
            .isEqualTo("LLongConsumerX: void doAccept(long l) throws X");
    }

    @Test
    public void testLXMethod() throws X {
        assertThat(LLongConsumerX.lX((long l) -> Function4U.doNothing() ))
            .isInstanceOf(LLongConsumerX.class);
    }

    @Test
    public void testWrapXMethod() throws X {
        assertThat(LLongConsumerX.wrapX(opposite))
            .isInstanceOf(LLongConsumerX.class);
    }

    @Test
    public void testWrapStdMethod() throws X {
        assertThat(LLongConsumerX.wrap(jre))
            .isInstanceOf(LLongConsumerX.class);
    }


    @Test
    public void testWrapExceptionMethodWrapsTheException() throws X {

        // given
        LLongConsumerX<X> sutThrowing = LLongConsumerX.lX((long l) -> {
            throw new UnsupportedOperationException();
        });

        // when
        LLongConsumerX<X> wrapped = sutThrowing.handleX(h -> {
            h.wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED);
        });

        // then
        try {
            wrapped.doAccept((long)100);
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
        LLongConsumerX<X> sutThrowing = LLongConsumerX.lX((long l) -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LLongConsumerX<X> wrapped = sutThrowing.handleX(handler -> handler
                .wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED)
                .throwIf(IndexOutOfBoundsException.class));

        // then
        try {
            wrapped.doAccept((long)100);
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
        LLongConsumerX<X> sutThrowing = LLongConsumerX.lX((long l) -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LLongConsumerX<X> wrapped = sutThrowing.handleX(handler -> handler
                .wrapWhen(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED)
                .throwIf(IndexOutOfBoundsException.class));

        // then
        try {
            wrapped.doAccept((long)100);
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
        LLongConsumerX<X> sutThrowing = LLongConsumerX.lX((long l) -> {
            throw (X) new ParseException(ORIGINAL_MESSAGE, 0);
        });

        // when
        LLongConsumerX<X> wrapped = sutThrowing.handleX(h -> {
        });

        // then
        try {
            wrapped.doAccept((long)100);
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
    public void testfromLong() throws X {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LLongConsumerX<X> sutO = (long l) -> {
                mainFunctionCalled.set(true);
                assertThat(l).isEqualTo((long)90);
        };

        LLongUnaryOperatorX<X> before1 = p0 -> {
            assertThat(p0).isEqualTo((long)80);
            beforeCalls.incrementAndGet();
            return (long)90;
        };

        //when
        LLongConsumerX<X> function = sutO.fromLong(before1);
        function.doAccept((long)80);

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(beforeCalls.get()).isEqualTo(1);
    }


    @Test
    public void testfrom() throws X {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LLongConsumerX<X> sutO = (long l) -> {
                mainFunctionCalled.set(true);
                assertThat(l).isEqualTo((long)90);
        };

        LToLongFunctionX<Integer ,X> before1 = p0 -> {
            assertThat(p0).isEqualTo(Integer.valueOf(80));
            beforeCalls.incrementAndGet();
            return (long)90;
        };

        //when
        LConsumerX<Integer ,X> function = sutO.from(before1);
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
        LLongConsumerX<X> sutO = (long l) -> {
                mainFunctionCalled.set(true);
                assertThat(l).isEqualTo((long)80);
        };

        LLongConsumerX<X> thenFunction = (long l) -> {
                thenFunctionCalled.set(true);
                assertThat(l).isEqualTo((long)80);
        };

        //when
        LLongConsumerX<X> function = sutO.andThen(thenFunction);
        function.doAccept((long)80);

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);
    }


    @Test
    public void testNesting() {
        assertThat(sut.nest())
            .isInstanceOf(LLongConsumer.class);
    }

    @Test
    public void testShoving() {
        assertThat(sut.shove())
            .isInstanceOf(LLongConsumer.class);
    }

    @Test
    public void testNestingX() {
        assertThat(sut.nestX())
            .isInstanceOf(LLongConsumerX.class);
    }

    @Test
    public void testShovingX() {
        assertThat(sut.shoveX())
            .isInstanceOf(LLongConsumerX.class);
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void testShove() {

        // given
        LLongConsumerX<X> sutThrowing = LLongConsumerX.lX((long l) -> {
            throw new UnsupportedOperationException();
        });

        // when
        sutThrowing.shove().doAccept((long)100);
    }

    @Test
    public void testHandle() throws X {

        // given
        LLongConsumerX<X> sutThrowing = LLongConsumerX.lX((long l) -> {
            throw new UnsupportedOperationException();
        });

        // when
        LLongConsumerX<X> wrapped = sutThrowing.handleX(h -> {
            h.wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED);
        });

        // then
        try {
            wrapped.doAccept((long)100);
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
                .contains("LLongConsumerX: void doAccept(long l) throws X");
    }


    @Test
    public void isThrowing() {
        assertThat(sut.isThrowing())
            .isTrue();
    }


}
