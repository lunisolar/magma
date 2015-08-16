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

package eu.lunisolar.magma.func.consumer.primitives.bi;

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
public class LBiBooleanConsumerTest<X extends ParseException> {
    private static final String ORIGINAL_MESSAGE = "Original message";
    private static final String EXCEPTION_WAS_WRAPPED = "Exception was wrapped.";
    private static final String NO_EXCEPTION_WERE_THROWN = "No exception were thrown.";



    private LBiBooleanConsumer sut = new LBiBooleanConsumer(){
        public  void doAccept(boolean b1,boolean b2)  {
            Function4U.doNothing();
        }
    };

    private LBiBooleanConsumerX<X> opposite = new LBiBooleanConsumerX(){
        public  void doAccept(boolean b1,boolean b2) throws ParseException {
            Function4U.doNothing();
        }
    };




    private LBiBooleanConsumer sutAlwaysThrowingUnckeck = LBiBooleanConsumer.l((boolean b1,boolean b2) -> {
            throw new IndexOutOfBoundsException(ORIGINAL_MESSAGE);
    });



    @Test
    public void testNestingDoAcceptUnckeck() throws X {

        // then
        try {
            sutAlwaysThrowingUnckeck.nestingDoAccept(true,true);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                    .hasNoCause()
                    .hasMessage(ORIGINAL_MESSAGE);
        }
    }

    @Test
    public void testShovingDoAcceptUnckeck() throws X {

        // then
        try {
            sutAlwaysThrowingUnckeck.shovingDoAccept(true,true);
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
            .isEqualTo("LBiBooleanConsumer: void doAccept(boolean b1,boolean b2)");
    }

    @Test
    public void testLMethod() throws X {
        assertThat(LBiBooleanConsumer.l((boolean b1,boolean b2) -> Function4U.doNothing() ))
            .isInstanceOf(LBiBooleanConsumer.class);
    }

    @Test
    public void testWrapMethod() throws X {
        assertThat(LBiBooleanConsumer.wrap(opposite))
            .isInstanceOf(LBiBooleanConsumer.class);
    }

    @Test
    public void testWrapMethodDoNotWrapsRuntimeException() throws X {
        // given
        LBiBooleanConsumerX<X> sutThrowing = LBiBooleanConsumerX.lX((boolean b1,boolean b2) -> {
            throw new UnsupportedOperationException(ORIGINAL_MESSAGE);
        });

        // when
        LBiBooleanConsumer wrapped = LBiBooleanConsumer.wrap(sutThrowing);

        // then
        try {
            wrapped.doAccept(true,true);
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
        LBiBooleanConsumerX<ParseException> sutThrowing = LBiBooleanConsumerX.lX((boolean b1,boolean b2) -> {
            throw new ParseException(ORIGINAL_MESSAGE, 0);
        });

        // when
        LBiBooleanConsumer wrapped = LBiBooleanConsumer.wrap(sutThrowing);

        // then
        try {
            wrapped.doAccept(true,true);
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
        LBiBooleanConsumer sutThrowing = LBiBooleanConsumer.l((boolean b1,boolean b2) -> {
            throw new UnsupportedOperationException();
        });

        // when
        LBiBooleanConsumer wrapped = sutThrowing.handleBiBoolCons(handler -> handler
            .wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED));

        // then
        try {
            wrapped.doAccept(true,true);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IllegalArgumentException.class)
                    .hasCauseExactlyInstanceOf(UnsupportedOperationException.class)
                    .hasMessage(EXCEPTION_WAS_WRAPPED);
        }
    }

    @Test
    public void testWrapExceptionMethodDoNotWrapsOtherExceptionIf() throws X {

        // given
        LBiBooleanConsumer sutThrowing = LBiBooleanConsumer.l((boolean b1,boolean b2) -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LBiBooleanConsumer wrapped = sutThrowing.handleBiBoolCons(handler -> handler
                .wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED)
                .throwIf(IndexOutOfBoundsException.class));

        // then
        try {
            wrapped.doAccept(true,true);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                    .hasNoCause();
        }
    }

@Test
    public void testWrapExceptionMethodDoNotWrapsOtherExceptionWhen() throws X {

        // given
        LBiBooleanConsumer sutThrowing = LBiBooleanConsumer.l((boolean b1,boolean b2) -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LBiBooleanConsumer wrapped = sutThrowing.handleBiBoolCons(handler -> handler
                .wrapWhen(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED)
                .throwIf(IndexOutOfBoundsException.class));

        // then
        try {
            wrapped.doAccept(true,true);
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
        LBiBooleanConsumer sutThrowing = LBiBooleanConsumer.l((boolean b1,boolean b2) -> {
            throw new UnsupportedOperationException(ORIGINAL_MESSAGE);
        });

        // when
        LBiBooleanConsumer wrapped = sutThrowing.handleBiBoolCons(h -> Function4U.doNothing());

        // then
        try {
            wrapped.doAccept(true,true);
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
    public void testbiBoolConsComposeBoolean() throws X {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LBiBooleanConsumer sutO = (boolean b1,boolean b2) -> {
                mainFunctionCalled.set(true);
                assertThat(b1).isEqualTo(true);
                assertThat(b2).isEqualTo(true);
        };

        LLogicalOperator before1 = p0 -> {
            assertThat(p0).isEqualTo(true);
            beforeCalls.incrementAndGet();
            return true;
        };
        LLogicalOperator before2 = p1 -> {
            assertThat(p1).isEqualTo(true);
            beforeCalls.incrementAndGet();
            return true;
        };

        //when
        LBiBooleanConsumer function = sutO.biBoolConsComposeBoolean(before1,before2);
        function.doAccept(true,true);

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(beforeCalls.get()).isEqualTo(2);
    }


    @Test
    public void testbiBoolConsCompose() throws X {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LBiBooleanConsumer sutO = (boolean b1,boolean b2) -> {
                mainFunctionCalled.set(true);
                assertThat(b1).isEqualTo(true);
                assertThat(b2).isEqualTo(true);
        };

        LPredicate<Integer > before1 = p0 -> {
            assertThat(p0).isEqualTo(Integer.valueOf(80));
            beforeCalls.incrementAndGet();
            return true;
        };
        LPredicate<Integer > before2 = p1 -> {
            assertThat(p1).isEqualTo(Integer.valueOf(81));
            beforeCalls.incrementAndGet();
            return true;
        };

        //when
        LBiConsumer<Integer ,Integer > function = sutO.biBoolConsCompose(before1,before2);
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
        LBiBooleanConsumer sutO = (boolean b1,boolean b2) -> {
                mainFunctionCalled.set(true);
                assertThat(b1).isEqualTo(true);
                assertThat(b2).isEqualTo(true);
        };

        LBiBooleanConsumer thenFunction = (boolean b1,boolean b2) -> {
                thenFunctionCalled.set(true);
                assertThat(b1).isEqualTo(true);
                assertThat(b2).isEqualTo(true);
        };

        //when
        LBiBooleanConsumer function = sutO.andThen(thenFunction);
        function.doAccept(true,true);

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);
    }


    @Test
    public void testNesting() {
        assertThat(sut.nestingBiBoolCons())
            .isSameAs(sut)
            .isInstanceOf(LBiBooleanConsumer.class);
    }

    @Test
    public void testShoving() {
        assertThat(sut.shovingBiBoolCons())
            .isSameAs(sut)
            .isInstanceOf(LBiBooleanConsumer.class);
    }

    @Test
    public void testNestingX() {
        assertThat(sut.nestingBiBoolConsX())
            .isSameAs(sut)
            .isInstanceOf(LBiBooleanConsumerX.class);
    }

    @Test
    public void testShovingX() {
        assertThat(sut.shovingBiBoolConsX())
            .isSameAs(sut)
            .isInstanceOf(LBiBooleanConsumerX.class);
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void testShove() {

        // given
        LBiBooleanConsumer sutThrowing = LBiBooleanConsumer.l((boolean b1,boolean b2) -> {
            throw new UnsupportedOperationException();
        });

        // when
        sutThrowing.shovingBiBoolCons().doAccept(true,true);
    }

    @Test
    public void testHandleBiBoolCons() throws X {

        // given
        LBiBooleanConsumer sutThrowing = LBiBooleanConsumer.l((boolean b1,boolean b2) -> {
            throw new UnsupportedOperationException();
        });

        // when
        LBiBooleanConsumer wrapped = sutThrowing.handleBiBoolCons(h -> {
            h.wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED);
        });

        // then
        try {
            wrapped.doAccept(true,true);
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
                .contains("LBiBooleanConsumer: void doAccept(boolean b1,boolean b2)");
    }


    @Test
    public void isThrowing() {
        assertThat(sut.isThrowing())
            .isFalse();
    }


}


