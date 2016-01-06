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
import eu.lunisolar.magma.func.operator.unary.*; // NOSONAR
import eu.lunisolar.magma.func.operator.binary.*; // NOSONAR
import eu.lunisolar.magma.func.operator.ternary.*; // NOSONAR
import eu.lunisolar.magma.func.function.*; // NOSONAR
import eu.lunisolar.magma.func.function.from.*; // NOSONAR
import eu.lunisolar.magma.func.function.to.*; // NOSONAR
import eu.lunisolar.magma.func.function.conversion.*; // NOSONAR
import eu.lunisolar.magma.func.predicate.*; // NOSONAR
import eu.lunisolar.magma.func.supplier.*; // NOSONAR
import eu.lunisolar.magma.func.consumer.primitives.obj.*; // NOSONAR
import eu.lunisolar.magma.func.consumer.primitives.bi.*; // NOSONAR
import eu.lunisolar.magma.func.consumer.primitives.tri.*; // NOSONAR
import eu.lunisolar.magma.func.consumer.primitives.*; // NOSONAR
import eu.lunisolar.magma.func.consumer.*; // NOSONAR
import eu.lunisolar.magma.func.action.*; // NOSONAR

import java.util.function.*; // NOSONAR
import org.assertj.core.api.Assertions;  //NOSONAR
import org.testng.annotations.*;      //NOSONAR
import java.util.regex.Pattern;          //NOSONAR
import java.text.ParseException;         //NOSONAR
import eu.lunisolar.magma.basics.*; //NOSONAR
import eu.lunisolar.magma.basics.exceptions.*; //NOSONAR
import java.util.concurrent.atomic.AtomicInteger; //NOSONAR
import eu.lunisolar.magma.struct.tuple.*; // NOSONAR
import static org.assertj.core.api.Assertions.*; //NOSONAR

/** The test obviously concentrate on the interface methods the function it self is very simple.  */
public class LFloatConsumerTest<X extends ParseException> {
    private static final String ORIGINAL_MESSAGE = "Original message";
    private static final String EXCEPTION_WAS_WRAPPED = "Exception was wrapped.";
    private static final String NO_EXCEPTION_WERE_THROWN = "No exception were thrown.";



    private LFloatConsumer sut = new LFloatConsumer(){
        public  void doAccept(float a1)  {
            Function4U.doNothing();
        }
    };

    private LFloatConsumerX<X> opposite = new LFloatConsumerX(){
        public  void doAccept(float a1) throws ParseException {
            Function4U.doNothing();
        }
    };




    private LFloatConsumer sutAlwaysThrowingUnckeck = LFloatConsumer.l(a1 -> {
            throw new IndexOutOfBoundsException(ORIGINAL_MESSAGE);
    });



    @Test
    public void testTupleCall() throws X {

        LFloatSingle domainObject = Tuple4U.tuple((float)100);

        Object result = sut.tupleAccept(domainObject);

        assertThat(result)
            .isSameAs(LTuple.Void.INSTANCE);
    }

    @Test
    public void testNestingDoAcceptUnckeck() throws X {

        // then
        try {
            sutAlwaysThrowingUnckeck.nestingDoAccept((float)100);
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
            sutAlwaysThrowingUnckeck.shovingDoAccept((float)100);
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
            .isEqualTo("LFloatConsumer: void doAccept(float a1)");
    }

    @Test
    public void testLMethod() throws X {
        assertThat(LFloatConsumer.l(a1 -> Function4U.doNothing() ))
            .isInstanceOf(LFloatConsumer.class);
    }

    @Test
    public void testWrapMethod() throws X {
        assertThat(LFloatConsumer.wrap(opposite))
            .isInstanceOf(LFloatConsumer.class);
    }

    @Test
    public void testWrapMethodDoNotWrapsRuntimeException() throws X {
        // given
        LFloatConsumerX<X> sutThrowing = LFloatConsumerX.lX(a1 -> {
            throw new UnsupportedOperationException(ORIGINAL_MESSAGE);
        });

        // when
        LFloatConsumer wrapped = LFloatConsumer.wrap(sutThrowing);

        // then
        try {
            wrapped.doAccept((float)100);
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
        LFloatConsumerX<ParseException> sutThrowing = LFloatConsumerX.lX(a1 -> {
            throw new ParseException(ORIGINAL_MESSAGE, 0);
        });

        // when
        LFloatConsumer wrapped = LFloatConsumer.wrap(sutThrowing);

        // then
        try {
            wrapped.doAccept((float)100);
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
        LFloatConsumer sutThrowing = LFloatConsumer.l(a1 -> {
            throw new UnsupportedOperationException();
        });

        // when
        LFloatConsumer wrapped = sutThrowing.handleFloatCons(handler -> handler
            .wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED));

        // then
        try {
            wrapped.doAccept((float)100);
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
        LFloatConsumer sutThrowing = LFloatConsumer.l(a1 -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LFloatConsumer wrapped = sutThrowing.handleFloatCons(handler -> handler
                .wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED)
                .throwIf(IndexOutOfBoundsException.class));

        // then
        try {
            wrapped.doAccept((float)100);
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
        LFloatConsumer sutThrowing = LFloatConsumer.l(a1 -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LFloatConsumer wrapped = sutThrowing.handleFloatCons(handler -> handler
                .wrapWhen(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED)
                .throwIf(IndexOutOfBoundsException.class));

        // then
        try {
            wrapped.doAccept((float)100);
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
        LFloatConsumer sutThrowing = LFloatConsumer.l(a1 -> {
            throw new UnsupportedOperationException(ORIGINAL_MESSAGE);
        });

        // when
        LFloatConsumer wrapped = sutThrowing.handleFloatCons(h -> Function4U.doNothing());

        // then
        try {
            wrapped.doAccept((float)100);
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
    public void testfloatConsComposeFloat() throws X {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LFloatConsumer sutO = a1 -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo((float)90);
        };

        LFloatUnaryOperator before1 = p0 -> {
            assertThat(p0).isEqualTo((float)80);
            beforeCalls.incrementAndGet();
            return (float)90;
        };

        //when
        LFloatConsumer function = sutO.floatConsComposeFloat(before1);
        function.doAccept((float)80);

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(beforeCalls.get()).isEqualTo(1);
    }


    @Test
    public void testfloatConsCompose() throws X {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LFloatConsumer sutO = a1 -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo((float)90);
        };

        LToFloatFunction<Integer > before1 = p0 -> {
            assertThat(p0).isEqualTo(Integer.valueOf(80));
            beforeCalls.incrementAndGet();
            return (float)90;
        };

        //when
        LConsumer<Integer > function = sutO.floatConsCompose(before1);
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
        LFloatConsumer sutO = a1 -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo((float)80);
        };

        LFloatConsumer thenFunction = (float a1) -> {
                thenFunctionCalled.set(true);
                assertThat(a1).isEqualTo((float)80);
        };

        //when
        LFloatConsumer function = sutO.andThen(thenFunction);
        function.doAccept((float)80);

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);
    }


    @Test
    public void testNesting() {
        assertThat(sut.nestingFloatCons())
            .isSameAs(sut)
            .isInstanceOf(LFloatConsumer.class);
    }

    @Test
    public void testShoving() {
        assertThat(sut.shovingFloatCons())
            .isSameAs(sut)
            .isInstanceOf(LFloatConsumer.class);
    }

    @Test
    public void testNestingX() {
        assertThat(sut.nestingFloatConsX())
            .isSameAs(sut)
            .isInstanceOf(LFloatConsumerX.class);
    }

    @Test
    public void testShovingX() {
        assertThat(sut.shovingFloatConsX())
            .isSameAs(sut)
            .isInstanceOf(LFloatConsumerX.class);
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void testShove() {

        // given
        LFloatConsumer sutThrowing = LFloatConsumer.l(a1 -> {
            throw new UnsupportedOperationException();
        });

        // when
        sutThrowing.shovingFloatCons().doAccept((float)100);
    }

    @Test
    public void testHandleFloatCons() throws X {

        // given
        LFloatConsumer sutThrowing = LFloatConsumer.l(a1 -> {
            throw new UnsupportedOperationException();
        });

        // when
        LFloatConsumer wrapped = sutThrowing.handleFloatCons(h -> {
            h.wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED);
        });

        // then
        try {
            wrapped.doAccept((float)100);
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
                .contains("LFloatConsumer: void doAccept(float a1)");
    }


    @Test
    public void isThrowing() {
        assertThat(sut.isThrowing())
            .isFalse();
    }

    @Test void safeCompiles() {
        LFloatConsumer r1 = LFloatConsumer.safe(sut); //NOSONAR
        LFloatConsumerX r2 = LFloatConsumer.safe(sut); //NOSONAR
    }

    @Test void safePropagates() {
        Object result = LFloatConsumer.safe(sut);
        assertThat(result).isSameAs(sut);
    }

    @Test void safeProtectsAgainstNpe() {
        Object result = LFloatConsumer.safe(null);
        assertThat(result).isSameAs(LFloatConsumer.l(LFloatConsumer.safe()));
    }

    @Test  void safeSupplierPropagates() {
        LSupplier<LFloatConsumer> supplier = ()->sut;
        Object result = LFloatConsumer.safeSupplier(supplier);
        assertThat(result).isSameAs(supplier);
    }

    @Test  void safeSupplierProtectsAgainstNpe() {
        Object result = LFloatConsumer.safeSupplier(null);
        assertThat(result).isSameAs(LFloatConsumer.safeSupplier());
    }

    @Test  void safeSupplierCompiles() {
        LSupplier<LFloatConsumer> r1 = LFloatConsumer.safeSupplier(()->sut);  //NOSONAR
    }

}
