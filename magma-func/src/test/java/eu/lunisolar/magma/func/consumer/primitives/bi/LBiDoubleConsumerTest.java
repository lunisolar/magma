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

package eu.lunisolar.magma.func.consumer.primitives.bi;

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
public class LBiDoubleConsumerTest<X extends ParseException> {
    private static final String ORIGINAL_MESSAGE = "Original message";
    private static final String EXCEPTION_WAS_WRAPPED = "Exception was wrapped.";
    private static final String NO_EXCEPTION_WERE_THROWN = "No exception were thrown.";



    private LBiDoubleConsumer sut = new LBiDoubleConsumer(){
        public  void doAccept(double a1,double a2)  {
            Function4U.doNothing();
        }
    };

    private LBiDoubleConsumerX<X> opposite = new LBiDoubleConsumerX<X>(){
        public  void doAccept(double a1,double a2)  throws X {
            Function4U.doNothing();
        }
    };




    private LBiDoubleConsumerX<RuntimeException> sutAlwaysThrowingUnchecked = LBiDoubleConsumer.l((a1,a2) -> {
            throw new IndexOutOfBoundsException(ORIGINAL_MESSAGE);
    });



    @Test
    public void testTupleCall() throws X {

        LDoublePair domainObject = Tuple4U.doublePair(100d,100d);

        Object result = sut.tupleAccept(domainObject);

        assertThat(result)
            .isSameAs(LTuple.Void.INSTANCE);
    }

    @Test
    public void testNestingDoAcceptUnchecked() throws X {

        // then
        try {
            sutAlwaysThrowingUnchecked.nestingDoAccept(100d,100d);
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
            sutAlwaysThrowingUnchecked.shovingDoAccept(100d,100d);
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
            .isEqualTo("LBiDoubleConsumer: void doAccept(double a1,double a2)");
    }

    @Test
    public void testLMethod() throws X {
        assertThat(LBiDoubleConsumer.l(Function4U::doNothing))
            .isInstanceOf(LBiDoubleConsumer.class);
    }

    @Test
    public void testWrapMethod() throws X {
        assertThat(LBiDoubleConsumer.wrap(opposite))
            .isInstanceOf(LBiDoubleConsumer.class);
    }

    @Test
    public void testWrapMethodDoNotWrapsRuntimeException() throws X {
        // given
        LBiDoubleConsumerX<X> sutThrowing = LBiDoubleConsumerX.lX((a1,a2) -> {
            throw new UnsupportedOperationException(ORIGINAL_MESSAGE);
        });

        // when
        LBiDoubleConsumer wrapped = LBiDoubleConsumer.wrap(sutThrowing);

        // then
        try {
            wrapped.doAccept(100d,100d);
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
        LBiDoubleConsumerX<ParseException> sutThrowing = LBiDoubleConsumerX.lX((a1,a2) -> {
            throw new ParseException(ORIGINAL_MESSAGE, 0);
        });

        // when
        LBiDoubleConsumer wrapped = LBiDoubleConsumer.wrap(sutThrowing);

        // then
        try {
            wrapped.doAccept(100d,100d);
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
        LBiDoubleConsumer sutThrowing = LBiDoubleConsumer.l((a1,a2) -> {
            throw new UnsupportedOperationException();
        });

        // when
        LBiDoubleConsumer wrapped = sutThrowing.handleBiDoubleCons(handler -> handler
            .wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED));

        // then
        try {
            wrapped.doAccept(100d,100d);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IllegalArgumentException.class)
                    .hasCauseExactlyInstanceOf(UnsupportedOperationException.class)
                    .hasMessage(EXCEPTION_WAS_WRAPPED);
        }
    }

    @Test
    public void testHandleBiDoubleConsMethodDoNotWrapsOtherExceptionIf() throws X {

        // given
        LBiDoubleConsumer sutThrowing = LBiDoubleConsumer.l((a1,a2) -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LBiDoubleConsumer wrapped = sutThrowing.handleBiDoubleCons(handler -> handler
                .wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED)
                .throwIf(IndexOutOfBoundsException.class));

        // then
        try {
            wrapped.doAccept(100d,100d);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                    .hasNoCause();
        }
    }

@Test
    public void testHandleBiDoubleConsMethodDoNotWrapsOtherExceptionWhen() throws X {

        // given
        LBiDoubleConsumer sutThrowing = LBiDoubleConsumer.l((a1,a2) -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LBiDoubleConsumer wrapped = sutThrowing.handleBiDoubleCons(handler -> handler
                .wrapWhen(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED)
                .throwIf(IndexOutOfBoundsException.class));

        // then
        try {
            wrapped.doAccept(100d,100d);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                    .hasNoCause();
        }
    }


    @Test
    public void testHandleBiDoubleConsMishandlingExceptionIsAllowed() throws X {

        // given
        LBiDoubleConsumer sutThrowing = LBiDoubleConsumer.l((a1,a2) -> {
            throw new UnsupportedOperationException(ORIGINAL_MESSAGE);
        });

        // when
        LBiDoubleConsumer wrapped = sutThrowing.handleBiDoubleCons(h -> Function4U.doNothing());

        // then
        try {
            wrapped.doAccept(100d,100d);
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
    public void testBiDoubleConsComposeDouble() throws X {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LBiDoubleConsumer sutO = (a1,a2) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(90d);
                assertThat(a2).isEqualTo(91d);
        };

        LDoubleUnaryOperator before1 = p0 -> {
            assertThat(p0).isEqualTo(80d);
            beforeCalls.incrementAndGet();
            return 90d;
        };
        LDoubleUnaryOperator before2 = p1 -> {
            assertThat(p1).isEqualTo(81d);
            beforeCalls.incrementAndGet();
            return 91d;
        };

        //when
        LBiDoubleConsumer function = sutO.biDoubleConsComposeDouble(before1,before2);
        function.doAccept(80d,81d);

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(beforeCalls.get()).isEqualTo(2);
    }


    @Test
    public void testBiDoubleConsCompose() throws X {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LBiDoubleConsumer sutO = (a1,a2) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(90d);
                assertThat(a2).isEqualTo(91d);
        };

        LToDoubleFunction<Integer> before1 = p0 -> {
            assertThat(p0).isEqualTo(80);
            beforeCalls.incrementAndGet();
            return 90d;
        };
        LToDoubleFunction<Integer> before2 = p1 -> {
            assertThat(p1).isEqualTo(81);
            beforeCalls.incrementAndGet();
            return 91d;
        };

        //when
        LBiConsumer<Integer,Integer> function = sutO.biDoubleConsCompose(before1,before2);
        function.doAccept(80,81);

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
        LBiDoubleConsumer sutO = (a1,a2) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(80d);
                assertThat(a2).isEqualTo(81d);
        };

        LBiDoubleConsumer thenFunction = (a1,a2) -> {
                thenFunctionCalled.set(true);
                assertThat(a1).isEqualTo(80d);
                assertThat(a2).isEqualTo(81d);
        };

        //when
        LBiDoubleConsumer function = sutO.andThen(thenFunction);
        function.doAccept(80d,81d);

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);
    }


    @Test
    public void testNesting() {
        assertThat(sut.nestingBiDoubleCons())
            .isSameAs(sut)
            .isInstanceOf(LBiDoubleConsumer.class);
    }

    @Test
    public void testShoving() {
        assertThat(sut.shovingBiDoubleCons())
            .isSameAs(sut)
            .isInstanceOf(LBiDoubleConsumer.class);
    }

    @Test
    public void testNestingX() {
        assertThat(sut.nestingBiDoubleConsX())
            .isSameAs(sut)
            .isInstanceOf(LBiDoubleConsumerX.class);
    }

    @Test
    public void testShovingX() {
        assertThat(sut.shovingBiDoubleConsX())
            .isSameAs(sut)
            .isInstanceOf(LBiDoubleConsumerX.class);
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void testShove() {

        // given
        LBiDoubleConsumer sutThrowing = LBiDoubleConsumer.l((a1,a2) -> {
            throw new UnsupportedOperationException();
        });

        // when
        sutThrowing.shovingBiDoubleCons().doAccept(100d,100d);
    }

    @Test
    public void testHandleBiDoubleCons() throws X {

        // given
        LBiDoubleConsumer sutThrowing = LBiDoubleConsumer.l((a1,a2) -> {
            throw new UnsupportedOperationException();
        });

        // when
        LBiDoubleConsumer wrapped = sutThrowing.handleBiDoubleCons(h -> {
            h.wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED);
        });

        // then
        try {
            wrapped.doAccept(100d,100d);
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
                .contains("LBiDoubleConsumer: void doAccept(double a1,double a2)");
    }


    @Test
    public void isThrowing() {
        assertThat(sut.isThrowing())
            .isFalse();
    }

    //<editor-fold desc="Variants">

    private void variantV1(double a2,double a1) {
    }

    @Test
    public void compilerSubstituteVariantV1() {
        LBiDoubleConsumer lambda = LBiDoubleConsumer./**/l1(this::variantV1);

        assertThat(lambda).isInstanceOf(LBiDoubleConsumer.V1.class);
    }

    //</editor-fold>


    @Test void safeCompiles() {
        LBiDoubleConsumer r1 = LBiDoubleConsumer.safe(sut); //NOSONAR
        LBiDoubleConsumerX r2 = LBiDoubleConsumer.safe(sut); //NOSONAR
    }

    @Test void safePropagates() {
        Object result = LBiDoubleConsumer.safe(sut);
        assertThat(result).isSameAs(sut);
    }

    @Test void safeProtectsAgainstNpe() {
        Object result = LBiDoubleConsumer.safe(null);
        assertThat(result).isSameAs(LBiDoubleConsumer.l(LBiDoubleConsumer.safe()));
    }

    @Test  void safeSupplierPropagates() {
        LSupplier<LBiDoubleConsumer> supplier = ()->sut;
        Object result = LBiDoubleConsumer.safeSupplier(supplier);
        assertThat(result).isSameAs(supplier);
    }

    @Test  void safeSupplierProtectsAgainstNpe() {
        Object result = LBiDoubleConsumer.safeSupplier(null);
        assertThat(result).isSameAs(LBiDoubleConsumer.safeSupplier());
    }

    @Test  void safeSupplierCompiles() {
        LSupplier<LBiDoubleConsumer> r1 = LBiDoubleConsumer.safeSupplier(()->sut);  //NOSONAR
    }

}
