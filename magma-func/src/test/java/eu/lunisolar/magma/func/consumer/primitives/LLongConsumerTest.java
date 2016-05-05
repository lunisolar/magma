/*
 * This file is part of "lunisolar-magma".
 *
 * (C) Copyright 2014-2016 Lunisolar (http://lunisolar.eu/).
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

import eu.lunisolar.magma.func.action.*; // NOSONAR
import eu.lunisolar.magma.func.consumer.*; // NOSONAR
import eu.lunisolar.magma.func.consumer.primitives.*; // NOSONAR
import eu.lunisolar.magma.func.consumer.primitives.bi.*; // NOSONAR
import eu.lunisolar.magma.func.consumer.primitives.obj.*; // NOSONAR
import eu.lunisolar.magma.func.consumer.primitives.tri.*; // NOSONAR
import eu.lunisolar.magma.func.function.*; // NOSONAR
import eu.lunisolar.magma.func.function.conversion.*; // NOSONAR
import eu.lunisolar.magma.func.function.from.*; // NOSONAR
import eu.lunisolar.magma.func.function.to.*; // NOSONAR
import eu.lunisolar.magma.func.operator.binary.*; // NOSONAR
import eu.lunisolar.magma.func.operator.ternary.*; // NOSONAR
import eu.lunisolar.magma.func.operator.unary.*; // NOSONAR
import eu.lunisolar.magma.func.predicate.*; // NOSONAR
import eu.lunisolar.magma.func.supplier.*; // NOSONAR

import org.assertj.core.api.Assertions;  //NOSONAR
import org.testng.annotations.*;      //NOSONAR
import java.util.regex.Pattern;          //NOSONAR
import java.text.ParseException;         //NOSONAR
import eu.lunisolar.magma.basics.*; //NOSONAR
import eu.lunisolar.magma.basics.exceptions.*; //NOSONAR
import java.util.concurrent.atomic.AtomicInteger; //NOSONAR
import eu.lunisolar.magma.struct.tuple.*; // NOSONAR
import static org.assertj.core.api.Assertions.*; //NOSONAR
import java.util.function.*; // NOSONAR

/** The test obviously concentrate on the interface methods the function it self is very simple.  */
public class LLongConsumerTest<X extends ParseException> {
    private static final String ORIGINAL_MESSAGE = "Original message";
    private static final String EXCEPTION_WAS_WRAPPED = "Exception was wrapped.";
    private static final String NO_EXCEPTION_WERE_THROWN = "No exception were thrown.";



    private LLongConsumer sut = new LLongConsumer(){
        public  void doAccept(long a1)  {
            Function4U.doNothing();
        }
    };

    private LLongConsumerX<X> opposite = new LLongConsumerX<X>(){
        public  void doAccept(long a1)  throws X {
            Function4U.doNothing();
        }
    };


    private LongConsumer jre = a1 -> Function4U.doNothing();



    private LLongConsumerX<RuntimeException> sutAlwaysThrowingUnchecked = LLongConsumer.l(a1 -> {
            throw new IndexOutOfBoundsException(ORIGINAL_MESSAGE);
    });



    @Test
    public void testTupleCall() throws X {

        LLongSingle domainObject = Tuple4U.longSingle(100L);

        Object result = sut.tupleAccept(domainObject);

        assertThat(result)
            .isSameAs(LTuple.Void.INSTANCE);
    }

    @Test
    public void testNestingDoAcceptUnchecked() throws X {

        // then
        try {
            sutAlwaysThrowingUnchecked.nestingDoAccept(100L);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                    .hasNoCause()
                    .hasMessage(ORIGINAL_MESSAGE);
        }
    }

    @Test
    public void testShovingDoAcceptUnchecked() throws X {

        // then
        try {
            sutAlwaysThrowingUnchecked.shovingDoAccept(100L);
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
            .isEqualTo("LLongConsumer: void doAccept(long a1)");
    }

    @Test
    public void testLMethod() throws X {
        assertThat(LLongConsumer.l(Function4U::doNothing))
            .isInstanceOf(LLongConsumer.class);
    }

    @Test
    public void testWrapMethod() throws X {
        assertThat(LLongConsumer.wrap(opposite))
            .isInstanceOf(LLongConsumer.class);
    }

    @Test
    public void testWrapStdMethod() throws X {
        assertThat(LLongConsumer.wrap(jre))
            .isInstanceOf(LLongConsumer.class);
    }

    @Test
    public void testWrapMethodDoNotWrapsRuntimeException() throws X {
        // given
        LLongConsumerX<X> sutThrowing = LLongConsumerX.lX(a1 -> {
            throw new UnsupportedOperationException(ORIGINAL_MESSAGE);
        });

        // when
        LLongConsumer wrapped = LLongConsumer.wrap(sutThrowing);

        // then
        try {
            wrapped.doAccept(100L);
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
        LLongConsumerX<ParseException> sutThrowing = LLongConsumerX.lX(a1 -> {
            throw new ParseException(ORIGINAL_MESSAGE, 0);
        });

        // when
        LLongConsumer wrapped = LLongConsumer.wrap(sutThrowing);

        // then
        try {
            wrapped.doAccept(100L);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(NestedException.class)
                    .hasCauseExactlyInstanceOf(ParseException.class)
                    .hasMessage(ORIGINAL_MESSAGE);
        }
    }


    @Test
    public void testHandlingDoAcceptMethodWrapsTheException() throws X {

        // given
        LLongConsumer sutThrowing = LLongConsumer.l(a1 -> {
            throw new UnsupportedOperationException();
        });

        // when
        LLongConsumer wrapped = sutThrowing.handleLongCons(handler -> handler
            .wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED));

        // then
        try {
            wrapped.doAccept(100L);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IllegalArgumentException.class)
                    .hasCauseExactlyInstanceOf(UnsupportedOperationException.class)
                    .hasMessage(EXCEPTION_WAS_WRAPPED);
        }
    }

    @Test
    public void testHandleLongConsMethodDoNotWrapsOtherExceptionIf() throws X {

        // given
        LLongConsumer sutThrowing = LLongConsumer.l(a1 -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LLongConsumer wrapped = sutThrowing.handleLongCons(handler -> handler
                .wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED)
                .throwIf(IndexOutOfBoundsException.class));

        // then
        try {
            wrapped.doAccept(100L);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                    .hasNoCause();
        }
    }

@Test
    public void testHandleLongConsMethodDoNotWrapsOtherExceptionWhen() throws X {

        // given
        LLongConsumer sutThrowing = LLongConsumer.l(a1 -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LLongConsumer wrapped = sutThrowing.handleLongCons(handler -> handler
                .wrapWhen(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED)
                .throwIf(IndexOutOfBoundsException.class));

        // then
        try {
            wrapped.doAccept(100L);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                    .hasNoCause();
        }
    }


    @Test
    public void testHandleLongConsMishandlingExceptionIsAllowed() throws X {

        // given
        LLongConsumer sutThrowing = LLongConsumer.l(a1 -> {
            throw new UnsupportedOperationException(ORIGINAL_MESSAGE);
        });

        // when
        LLongConsumer wrapped = sutThrowing.handleLongCons(h -> Function4U.doNothing());

        // then
        try {
            wrapped.doAccept(100L);
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
    public void testLongConsComposeLong() throws X {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LLongConsumer sutO = a1 -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(90L);
        };

        LLongUnaryOperator before1 = p0 -> {
            assertThat(p0).isEqualTo(80L);
            beforeCalls.incrementAndGet();
            return 90L;
        };

        //when
        LLongConsumer function = sutO.longConsComposeLong(before1);
        function.doAccept(80L);

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(beforeCalls.get()).isEqualTo(1);
    }


    @Test
    public void testLongConsCompose() throws X {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LLongConsumer sutO = a1 -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(90L);
        };

        LToLongFunction<Integer> before1 = p0 -> {
            assertThat(p0).isEqualTo(80);
            beforeCalls.incrementAndGet();
            return 90L;
        };

        //when
        LConsumer<Integer> function = sutO.longConsCompose(before1);
        function.doAccept(80);

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
        LLongConsumer sutO = a1 -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(80L);
        };

        LLongConsumer thenFunction = a1 -> {
                thenFunctionCalled.set(true);
                assertThat(a1).isEqualTo(80L);
        };

        //when
        LLongConsumer function = sutO.andThen(thenFunction);
        function.doAccept(80L);

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);
    }


    @Test
    public void testNesting() {
        assertThat(sut.nestingLongCons())
            .isSameAs(sut)
            .isInstanceOf(LLongConsumer.class);
    }

    @Test
    public void testShoving() {
        assertThat(sut.shovingLongCons())
            .isSameAs(sut)
            .isInstanceOf(LLongConsumer.class);
    }

    @Test
    public void testNestingX() {
        assertThat(sut.nestingLongConsX())
            .isSameAs(sut)
            .isInstanceOf(LLongConsumerX.class);
    }

    @Test
    public void testShovingX() {
        assertThat(sut.shovingLongConsX())
            .isSameAs(sut)
            .isInstanceOf(LLongConsumerX.class);
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void testShove() {

        // given
        LLongConsumer sutThrowing = LLongConsumer.l(a1 -> {
            throw new UnsupportedOperationException();
        });

        // when
        sutThrowing.shovingLongCons().doAccept(100L);
    }

    @Test
    public void testHandleLongCons() throws X {

        // given
        LLongConsumer sutThrowing = LLongConsumer.l(a1 -> {
            throw new UnsupportedOperationException();
        });

        // when
        LLongConsumer wrapped = sutThrowing.handleLongCons(h -> {
            h.wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED);
        });

        // then
        try {
            wrapped.doAccept(100L);
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
                .contains("LLongConsumer: void doAccept(long a1)");
    }


    @Test
    public void isThrowing() {
        assertThat(sut.isThrowing())
            .isFalse();
    }

    @Test void safeCompiles() {
        LLongConsumer r1 = LLongConsumer.safe(sut); //NOSONAR
        LLongConsumerX r2 = LLongConsumer.safe(sut); //NOSONAR
        LongConsumer r3 = LLongConsumer.safe(sut); //NOSONAR
    }

    @Test void safePropagates() {
        Object result = LLongConsumer.safe(sut);
        assertThat(result).isSameAs(sut);
    }

    @Test void safeProtectsAgainstNpe() {
        Object result = LLongConsumer.safe(null);
        assertThat(result).isSameAs(LLongConsumer.l(LLongConsumer.safe()));
    }

    @Test  void safeSupplierPropagates() {
        LSupplier<LLongConsumer> supplier = ()->sut;
        Object result = LLongConsumer.safeSupplier(supplier);
        assertThat(result).isSameAs(supplier);
    }

    @Test  void safeSupplierProtectsAgainstNpe() {
        Object result = LLongConsumer.safeSupplier(null);
        assertThat(result).isSameAs(LLongConsumer.safeSupplier());
    }

    @Test  void safeSupplierCompiles() {
        LSupplier<LLongConsumer> r1 = LLongConsumer.safeSupplier(()->sut);  //NOSONAR
        Supplier<LLongConsumer> r2 = LLongConsumer.safeSupplier(()->sut); //NOSONAR
    }

}
