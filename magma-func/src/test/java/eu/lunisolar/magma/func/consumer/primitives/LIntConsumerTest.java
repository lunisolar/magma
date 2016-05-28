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
public class LIntConsumerTest<X extends ParseException> {
    private static final String ORIGINAL_MESSAGE = "Original message";
    private static final String EXCEPTION_WAS_WRAPPED = "Exception was wrapped.";
    private static final String NO_EXCEPTION_WERE_THROWN = "No exception were thrown.";



    private LIntConsumer sut = new LIntConsumer(){
        public  void doAccept(int a)  {
            Function4U.doNothing();
        }
    };

    private LIntConsumerX<X> opposite = new LIntConsumerX<X>(){
        public  void doAccept(int a)  throws X {
            Function4U.doNothing();
        }
    };


    private IntConsumer jre = a -> Function4U.doNothing();



    private LIntConsumerX<RuntimeException> sutAlwaysThrowingUnchecked = LIntConsumer.l(a -> {
            throw new IndexOutOfBoundsException(ORIGINAL_MESSAGE);
    });



    @Test
    public void testTupleCall() throws X {

        LIntSingle domainObject = Tuple4U.intSingle(100);

        Object result = sut.tupleAccept(domainObject);

        assertThat(result)
            .isSameAs(LTuple.Void.INSTANCE);
    }

    @Test
    public void testNestingDoAcceptUnchecked() throws X {

        // then
        try {
            sutAlwaysThrowingUnchecked.nestingDoAccept(100);
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
            sutAlwaysThrowingUnchecked.shovingDoAccept(100);
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
            .isEqualTo("LIntConsumer: void doAccept(int a)");
    }

    @Test
    public void testLMethod() throws X {
        assertThat(LIntConsumer.l(Function4U::doNothing))
            .isInstanceOf(LIntConsumer.class);
    }

    @Test
    public void testWrapMethod() throws X {
        assertThat(LIntConsumer.wrap(opposite))
            .isInstanceOf(LIntConsumer.class);
    }

    @Test
    public void testWrapStdMethod() throws X {
        assertThat(LIntConsumer.wrap(jre))
            .isInstanceOf(LIntConsumer.class);
    }

    @Test
    public void testWrapMethodDoNotWrapsRuntimeException() throws X {
        // given
        LIntConsumerX<X> sutThrowing = LIntConsumerX.lX(a -> {
            throw new UnsupportedOperationException(ORIGINAL_MESSAGE);
        });

        // when
        LIntConsumer wrapped = LIntConsumer.wrap(sutThrowing);

        // then
        try {
            wrapped.doAccept(100);
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
        LIntConsumerX<ParseException> sutThrowing = LIntConsumerX.lX(a -> {
            throw new ParseException(ORIGINAL_MESSAGE, 0);
        });

        // when
        LIntConsumer wrapped = LIntConsumer.wrap(sutThrowing);

        // then
        try {
            wrapped.doAccept(100);
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
        LIntConsumer sutThrowing = LIntConsumer.l(a -> {
            throw new UnsupportedOperationException();
        });

        // when
        LIntConsumer wrapped = sutThrowing.handleIntCons(handler -> handler
            .wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED));

        // then
        try {
            wrapped.doAccept(100);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IllegalArgumentException.class)
                    .hasCauseExactlyInstanceOf(UnsupportedOperationException.class)
                    .hasMessage(EXCEPTION_WAS_WRAPPED);
        }
    }

    @Test
    public void testHandleIntConsMethodDoNotWrapsOtherExceptionIf() throws X {

        // given
        LIntConsumer sutThrowing = LIntConsumer.l(a -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LIntConsumer wrapped = sutThrowing.handleIntCons(handler -> handler
                .wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED)
                .throwIf(IndexOutOfBoundsException.class));

        // then
        try {
            wrapped.doAccept(100);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                    .hasNoCause();
        }
    }

@Test
    public void testHandleIntConsMethodDoNotWrapsOtherExceptionWhen() throws X {

        // given
        LIntConsumer sutThrowing = LIntConsumer.l(a -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LIntConsumer wrapped = sutThrowing.handleIntCons(handler -> handler
                .wrapWhen(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED)
                .throwIf(IndexOutOfBoundsException.class));

        // then
        try {
            wrapped.doAccept(100);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                    .hasNoCause();
        }
    }


    @Test
    public void testHandleIntConsMishandlingExceptionIsAllowed() throws X {

        // given
        LIntConsumer sutThrowing = LIntConsumer.l(a -> {
            throw new UnsupportedOperationException(ORIGINAL_MESSAGE);
        });

        // when
        LIntConsumer wrapped = sutThrowing.handleIntCons(h -> Function4U.doNothing());

        // then
        try {
            wrapped.doAccept(100);
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
    public void testIntConsComposeInt() throws X {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LIntConsumer sutO = a -> {
                mainFunctionCalled.set(true);
                assertThat(a).isEqualTo(90);
        };

        LIntUnaryOperator before = p0 -> {
            assertThat(p0).isEqualTo(80);
            beforeCalls.incrementAndGet();
            return 90;
        };

        //when
        LIntConsumer function = sutO.intConsComposeInt(before);
        function.doAccept(80);

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(beforeCalls.get()).isEqualTo(1);
    }


    @Test
    public void testIntConsCompose() throws X {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LIntConsumer sutO = a -> {
                mainFunctionCalled.set(true);
                assertThat(a).isEqualTo(90);
        };

        LToIntFunction<Integer> before = p0 -> {
            assertThat(p0).isEqualTo(80);
            beforeCalls.incrementAndGet();
            return 90;
        };

        //when
        LConsumer<Integer> function = sutO.intConsCompose(before);
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
        LIntConsumer sutO = a -> {
                mainFunctionCalled.set(true);
                assertThat(a).isEqualTo(80);
        };

        LIntConsumer thenFunction = a -> {
                thenFunctionCalled.set(true);
                assertThat(a).isEqualTo(80);
        };

        //when
        LIntConsumer function = sutO.andThen(thenFunction);
        function.doAccept(80);

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);
    }


    @Test
    public void testNesting() {
        assertThat(sut.nestingIntCons())
            .isSameAs(sut)
            .isInstanceOf(LIntConsumer.class);
    }

    @Test
    public void testShoving() {
        assertThat(sut.shovingIntCons())
            .isSameAs(sut)
            .isInstanceOf(LIntConsumer.class);
    }

    @Test
    public void testNestingX() {
        assertThat(sut.nestingIntConsX())
            .isSameAs(sut)
            .isInstanceOf(LIntConsumerX.class);
    }

    @Test
    public void testShovingX() {
        assertThat(sut.shovingIntConsX())
            .isSameAs(sut)
            .isInstanceOf(LIntConsumerX.class);
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void testShove() {

        // given
        LIntConsumer sutThrowing = LIntConsumer.l(a -> {
            throw new UnsupportedOperationException();
        });

        // when
        sutThrowing.shovingIntCons().doAccept(100);
    }

    @Test
    public void testHandleIntCons() throws X {

        // given
        LIntConsumer sutThrowing = LIntConsumer.l(a -> {
            throw new UnsupportedOperationException();
        });

        // when
        LIntConsumer wrapped = sutThrowing.handleIntCons(h -> {
            h.wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED);
        });

        // then
        try {
            wrapped.doAccept(100);
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
                .contains("LIntConsumer: void doAccept(int a)");
    }


    @Test
    public void isThrowing() {
        assertThat(sut.isThrowing())
            .isFalse();
    }

    @Test void safeCompiles() {
        LIntConsumer r1 = LIntConsumer.safe(sut); //NOSONAR
        LIntConsumerX r2 = LIntConsumer.safe(sut); //NOSONAR
        IntConsumer r3 = LIntConsumer.safe(sut); //NOSONAR
    }

    @Test void safePropagates() {
        Object result = LIntConsumer.safe(sut);
        assertThat(result).isSameAs(sut);
    }

    @Test void safeProtectsAgainstNpe() {
        Object result = LIntConsumer.safe(null);
        assertThat(result).isSameAs(LIntConsumer.l(LIntConsumer.safe()));
    }

    @Test  void safeSupplierPropagates() {
        LSupplier<LIntConsumer> supplier = ()->sut;
        Object result = LIntConsumer.safeSupplier(supplier);
        assertThat(result).isSameAs(supplier);
    }

    @Test  void safeSupplierProtectsAgainstNpe() {
        Object result = LIntConsumer.safeSupplier(null);
        assertThat(result).isSameAs(LIntConsumer.safeSupplier());
    }

    @Test  void safeSupplierCompiles() {
        LSupplier<LIntConsumer> r1 = LIntConsumer.safeSupplier(()->sut);  //NOSONAR
        Supplier<LIntConsumer> r2 = LIntConsumer.safeSupplier(()->sut); //NOSONAR
    }

}
