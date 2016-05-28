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
public class LBoolConsumerTest<X extends ParseException> {
    private static final String ORIGINAL_MESSAGE = "Original message";
    private static final String EXCEPTION_WAS_WRAPPED = "Exception was wrapped.";
    private static final String NO_EXCEPTION_WERE_THROWN = "No exception were thrown.";



    private LBoolConsumer sut = new LBoolConsumer(){
        public  void doAccept(boolean a)  {
            Function4U.doNothing();
        }
    };

    private LBoolConsumerX<X> opposite = new LBoolConsumerX<X>(){
        public  void doAccept(boolean a)  throws X {
            Function4U.doNothing();
        }
    };




    private LBoolConsumerX<RuntimeException> sutAlwaysThrowingUnchecked = LBoolConsumer.l(a -> {
            throw new IndexOutOfBoundsException(ORIGINAL_MESSAGE);
    });



    @Test
    public void testTupleCall() throws X {

        LBoolSingle domainObject = Tuple4U.boolSingle(true);

        Object result = sut.tupleAccept(domainObject);

        assertThat(result)
            .isSameAs(LTuple.Void.INSTANCE);
    }

    @Test
    public void testNestingDoAcceptUnchecked() throws X {

        // then
        try {
            sutAlwaysThrowingUnchecked.nestingDoAccept(true);
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
            sutAlwaysThrowingUnchecked.shovingDoAccept(true);
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
            .isEqualTo("LBoolConsumer: void doAccept(boolean a)");
    }

    @Test
    public void testLMethod() throws X {
        assertThat(LBoolConsumer.l(Function4U::doNothing))
            .isInstanceOf(LBoolConsumer.class);
    }

    @Test
    public void testWrapMethod() throws X {
        assertThat(LBoolConsumer.wrap(opposite))
            .isInstanceOf(LBoolConsumer.class);
    }

    @Test
    public void testWrapMethodDoNotWrapsRuntimeException() throws X {
        // given
        LBoolConsumerX<X> sutThrowing = LBoolConsumerX.lX(a -> {
            throw new UnsupportedOperationException(ORIGINAL_MESSAGE);
        });

        // when
        LBoolConsumer wrapped = LBoolConsumer.wrap(sutThrowing);

        // then
        try {
            wrapped.doAccept(true);
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
        LBoolConsumerX<ParseException> sutThrowing = LBoolConsumerX.lX(a -> {
            throw new ParseException(ORIGINAL_MESSAGE, 0);
        });

        // when
        LBoolConsumer wrapped = LBoolConsumer.wrap(sutThrowing);

        // then
        try {
            wrapped.doAccept(true);
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
        LBoolConsumer sutThrowing = LBoolConsumer.l(a -> {
            throw new UnsupportedOperationException();
        });

        // when
        LBoolConsumer wrapped = sutThrowing.handleBoolCons(handler -> handler
            .wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED));

        // then
        try {
            wrapped.doAccept(true);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IllegalArgumentException.class)
                    .hasCauseExactlyInstanceOf(UnsupportedOperationException.class)
                    .hasMessage(EXCEPTION_WAS_WRAPPED);
        }
    }

    @Test
    public void testHandleBoolConsMethodDoNotWrapsOtherExceptionIf() throws X {

        // given
        LBoolConsumer sutThrowing = LBoolConsumer.l(a -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LBoolConsumer wrapped = sutThrowing.handleBoolCons(handler -> handler
                .wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED)
                .throwIf(IndexOutOfBoundsException.class));

        // then
        try {
            wrapped.doAccept(true);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                    .hasNoCause();
        }
    }

@Test
    public void testHandleBoolConsMethodDoNotWrapsOtherExceptionWhen() throws X {

        // given
        LBoolConsumer sutThrowing = LBoolConsumer.l(a -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LBoolConsumer wrapped = sutThrowing.handleBoolCons(handler -> handler
                .wrapWhen(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED)
                .throwIf(IndexOutOfBoundsException.class));

        // then
        try {
            wrapped.doAccept(true);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                    .hasNoCause();
        }
    }


    @Test
    public void testHandleBoolConsMishandlingExceptionIsAllowed() throws X {

        // given
        LBoolConsumer sutThrowing = LBoolConsumer.l(a -> {
            throw new UnsupportedOperationException(ORIGINAL_MESSAGE);
        });

        // when
        LBoolConsumer wrapped = sutThrowing.handleBoolCons(h -> Function4U.doNothing());

        // then
        try {
            wrapped.doAccept(true);
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
    public void testBoolConsComposeBool() throws X {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LBoolConsumer sutO = a -> {
                mainFunctionCalled.set(true);
                assertThat(a).isEqualTo(true);
        };

        LLogicalOperator before = p0 -> {
            assertThat(p0).isEqualTo(true);
            beforeCalls.incrementAndGet();
            return true;
        };

        //when
        LBoolConsumer function = sutO.boolConsComposeBool(before);
        function.doAccept(true);

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(beforeCalls.get()).isEqualTo(1);
    }


    @Test
    public void testBoolConsCompose() throws X {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LBoolConsumer sutO = a -> {
                mainFunctionCalled.set(true);
                assertThat(a).isEqualTo(true);
        };

        LPredicate<Integer> before = p0 -> {
            assertThat(p0).isEqualTo(80);
            beforeCalls.incrementAndGet();
            return true;
        };

        //when
        LConsumer<Integer> function = sutO.boolConsCompose(before);
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
        LBoolConsumer sutO = a -> {
                mainFunctionCalled.set(true);
                assertThat(a).isEqualTo(true);
        };

        LBoolConsumer thenFunction = a -> {
                thenFunctionCalled.set(true);
                assertThat(a).isEqualTo(true);
        };

        //when
        LBoolConsumer function = sutO.andThen(thenFunction);
        function.doAccept(true);

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);
    }


    @Test
    public void testNesting() {
        assertThat(sut.nestingBoolCons())
            .isSameAs(sut)
            .isInstanceOf(LBoolConsumer.class);
    }

    @Test
    public void testShoving() {
        assertThat(sut.shovingBoolCons())
            .isSameAs(sut)
            .isInstanceOf(LBoolConsumer.class);
    }

    @Test
    public void testNestingX() {
        assertThat(sut.nestingBoolConsX())
            .isSameAs(sut)
            .isInstanceOf(LBoolConsumerX.class);
    }

    @Test
    public void testShovingX() {
        assertThat(sut.shovingBoolConsX())
            .isSameAs(sut)
            .isInstanceOf(LBoolConsumerX.class);
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void testShove() {

        // given
        LBoolConsumer sutThrowing = LBoolConsumer.l(a -> {
            throw new UnsupportedOperationException();
        });

        // when
        sutThrowing.shovingBoolCons().doAccept(true);
    }

    @Test
    public void testHandleBoolCons() throws X {

        // given
        LBoolConsumer sutThrowing = LBoolConsumer.l(a -> {
            throw new UnsupportedOperationException();
        });

        // when
        LBoolConsumer wrapped = sutThrowing.handleBoolCons(h -> {
            h.wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED);
        });

        // then
        try {
            wrapped.doAccept(true);
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
                .contains("LBoolConsumer: void doAccept(boolean a)");
    }


    @Test
    public void isThrowing() {
        assertThat(sut.isThrowing())
            .isFalse();
    }

    @Test void safeCompiles() {
        LBoolConsumer r1 = LBoolConsumer.safe(sut); //NOSONAR
        LBoolConsumerX r2 = LBoolConsumer.safe(sut); //NOSONAR
    }

    @Test void safePropagates() {
        Object result = LBoolConsumer.safe(sut);
        assertThat(result).isSameAs(sut);
    }

    @Test void safeProtectsAgainstNpe() {
        Object result = LBoolConsumer.safe(null);
        assertThat(result).isSameAs(LBoolConsumer.l(LBoolConsumer.safe()));
    }

    @Test  void safeSupplierPropagates() {
        LSupplier<LBoolConsumer> supplier = ()->sut;
        Object result = LBoolConsumer.safeSupplier(supplier);
        assertThat(result).isSameAs(supplier);
    }

    @Test  void safeSupplierProtectsAgainstNpe() {
        Object result = LBoolConsumer.safeSupplier(null);
        assertThat(result).isSameAs(LBoolConsumer.safeSupplier());
    }

    @Test  void safeSupplierCompiles() {
        LSupplier<LBoolConsumer> r1 = LBoolConsumer.safeSupplier(()->sut);  //NOSONAR
    }

}
