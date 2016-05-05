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
public class LDoubleConsumerTest<X extends ParseException> {
    private static final String ORIGINAL_MESSAGE = "Original message";
    private static final String EXCEPTION_WAS_WRAPPED = "Exception was wrapped.";
    private static final String NO_EXCEPTION_WERE_THROWN = "No exception were thrown.";



    private LDoubleConsumer sut = new LDoubleConsumer(){
        public  void doAccept(double a1)  {
            Function4U.doNothing();
        }
    };

    private LDoubleConsumerX<X> opposite = new LDoubleConsumerX<X>(){
        public  void doAccept(double a1)  throws X {
            Function4U.doNothing();
        }
    };


    private DoubleConsumer jre = a1 -> Function4U.doNothing();



    private LDoubleConsumerX<RuntimeException> sutAlwaysThrowingUnchecked = LDoubleConsumer.l(a1 -> {
            throw new IndexOutOfBoundsException(ORIGINAL_MESSAGE);
    });



    @Test
    public void testTupleCall() throws X {

        LDoubleSingle domainObject = Tuple4U.doubleSingle(100d);

        Object result = sut.tupleAccept(domainObject);

        assertThat(result)
            .isSameAs(LTuple.Void.INSTANCE);
    }

    @Test
    public void testNestingDoAcceptUnchecked() throws X {

        // then
        try {
            sutAlwaysThrowingUnchecked.nestingDoAccept(100d);
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
            sutAlwaysThrowingUnchecked.shovingDoAccept(100d);
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
            .isEqualTo("LDoubleConsumer: void doAccept(double a1)");
    }

    @Test
    public void testLMethod() throws X {
        assertThat(LDoubleConsumer.l(Function4U::doNothing))
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
        LDoubleConsumerX<X> sutThrowing = LDoubleConsumerX.lX(a1 -> {
            throw new UnsupportedOperationException(ORIGINAL_MESSAGE);
        });

        // when
        LDoubleConsumer wrapped = LDoubleConsumer.wrap(sutThrowing);

        // then
        try {
            wrapped.doAccept(100d);
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
        LDoubleConsumerX<ParseException> sutThrowing = LDoubleConsumerX.lX(a1 -> {
            throw new ParseException(ORIGINAL_MESSAGE, 0);
        });

        // when
        LDoubleConsumer wrapped = LDoubleConsumer.wrap(sutThrowing);

        // then
        try {
            wrapped.doAccept(100d);
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
        LDoubleConsumer sutThrowing = LDoubleConsumer.l(a1 -> {
            throw new UnsupportedOperationException();
        });

        // when
        LDoubleConsumer wrapped = sutThrowing.handleDoubleCons(handler -> handler
            .wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED));

        // then
        try {
            wrapped.doAccept(100d);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IllegalArgumentException.class)
                    .hasCauseExactlyInstanceOf(UnsupportedOperationException.class)
                    .hasMessage(EXCEPTION_WAS_WRAPPED);
        }
    }

    @Test
    public void testHandleDoubleConsMethodDoNotWrapsOtherExceptionIf() throws X {

        // given
        LDoubleConsumer sutThrowing = LDoubleConsumer.l(a1 -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LDoubleConsumer wrapped = sutThrowing.handleDoubleCons(handler -> handler
                .wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED)
                .throwIf(IndexOutOfBoundsException.class));

        // then
        try {
            wrapped.doAccept(100d);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                    .hasNoCause();
        }
    }

@Test
    public void testHandleDoubleConsMethodDoNotWrapsOtherExceptionWhen() throws X {

        // given
        LDoubleConsumer sutThrowing = LDoubleConsumer.l(a1 -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LDoubleConsumer wrapped = sutThrowing.handleDoubleCons(handler -> handler
                .wrapWhen(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED)
                .throwIf(IndexOutOfBoundsException.class));

        // then
        try {
            wrapped.doAccept(100d);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                    .hasNoCause();
        }
    }


    @Test
    public void testHandleDoubleConsMishandlingExceptionIsAllowed() throws X {

        // given
        LDoubleConsumer sutThrowing = LDoubleConsumer.l(a1 -> {
            throw new UnsupportedOperationException(ORIGINAL_MESSAGE);
        });

        // when
        LDoubleConsumer wrapped = sutThrowing.handleDoubleCons(h -> Function4U.doNothing());

        // then
        try {
            wrapped.doAccept(100d);
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
    public void testDoubleConsComposeDouble() throws X {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LDoubleConsumer sutO = a1 -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(90d);
        };

        LDoubleUnaryOperator before1 = p0 -> {
            assertThat(p0).isEqualTo(80d);
            beforeCalls.incrementAndGet();
            return 90d;
        };

        //when
        LDoubleConsumer function = sutO.doubleConsComposeDouble(before1);
        function.doAccept(80d);

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(beforeCalls.get()).isEqualTo(1);
    }


    @Test
    public void testDoubleConsCompose() throws X {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LDoubleConsumer sutO = a1 -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(90d);
        };

        LToDoubleFunction<Integer> before1 = p0 -> {
            assertThat(p0).isEqualTo(80);
            beforeCalls.incrementAndGet();
            return 90d;
        };

        //when
        LConsumer<Integer> function = sutO.doubleConsCompose(before1);
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
        LDoubleConsumer sutO = a1 -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(80d);
        };

        LDoubleConsumer thenFunction = a1 -> {
                thenFunctionCalled.set(true);
                assertThat(a1).isEqualTo(80d);
        };

        //when
        LDoubleConsumer function = sutO.andThen(thenFunction);
        function.doAccept(80d);

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);
    }


    @Test
    public void testNesting() {
        assertThat(sut.nestingDoubleCons())
            .isSameAs(sut)
            .isInstanceOf(LDoubleConsumer.class);
    }

    @Test
    public void testShoving() {
        assertThat(sut.shovingDoubleCons())
            .isSameAs(sut)
            .isInstanceOf(LDoubleConsumer.class);
    }

    @Test
    public void testNestingX() {
        assertThat(sut.nestingDoubleConsX())
            .isSameAs(sut)
            .isInstanceOf(LDoubleConsumerX.class);
    }

    @Test
    public void testShovingX() {
        assertThat(sut.shovingDoubleConsX())
            .isSameAs(sut)
            .isInstanceOf(LDoubleConsumerX.class);
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void testShove() {

        // given
        LDoubleConsumer sutThrowing = LDoubleConsumer.l(a1 -> {
            throw new UnsupportedOperationException();
        });

        // when
        sutThrowing.shovingDoubleCons().doAccept(100d);
    }

    @Test
    public void testHandleDoubleCons() throws X {

        // given
        LDoubleConsumer sutThrowing = LDoubleConsumer.l(a1 -> {
            throw new UnsupportedOperationException();
        });

        // when
        LDoubleConsumer wrapped = sutThrowing.handleDoubleCons(h -> {
            h.wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED);
        });

        // then
        try {
            wrapped.doAccept(100d);
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
                .contains("LDoubleConsumer: void doAccept(double a1)");
    }


    @Test
    public void isThrowing() {
        assertThat(sut.isThrowing())
            .isFalse();
    }

    @Test void safeCompiles() {
        LDoubleConsumer r1 = LDoubleConsumer.safe(sut); //NOSONAR
        LDoubleConsumerX r2 = LDoubleConsumer.safe(sut); //NOSONAR
        DoubleConsumer r3 = LDoubleConsumer.safe(sut); //NOSONAR
    }

    @Test void safePropagates() {
        Object result = LDoubleConsumer.safe(sut);
        assertThat(result).isSameAs(sut);
    }

    @Test void safeProtectsAgainstNpe() {
        Object result = LDoubleConsumer.safe(null);
        assertThat(result).isSameAs(LDoubleConsumer.l(LDoubleConsumer.safe()));
    }

    @Test  void safeSupplierPropagates() {
        LSupplier<LDoubleConsumer> supplier = ()->sut;
        Object result = LDoubleConsumer.safeSupplier(supplier);
        assertThat(result).isSameAs(supplier);
    }

    @Test  void safeSupplierProtectsAgainstNpe() {
        Object result = LDoubleConsumer.safeSupplier(null);
        assertThat(result).isSameAs(LDoubleConsumer.safeSupplier());
    }

    @Test  void safeSupplierCompiles() {
        LSupplier<LDoubleConsumer> r1 = LDoubleConsumer.safeSupplier(()->sut);  //NOSONAR
        Supplier<LDoubleConsumer> r2 = LDoubleConsumer.safeSupplier(()->sut); //NOSONAR
    }

}
