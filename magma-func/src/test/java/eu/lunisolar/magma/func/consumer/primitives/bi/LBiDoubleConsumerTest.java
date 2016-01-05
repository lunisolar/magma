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
public class LBiDoubleConsumerTest<X extends ParseException> {
    private static final String ORIGINAL_MESSAGE = "Original message";
    private static final String EXCEPTION_WAS_WRAPPED = "Exception was wrapped.";
    private static final String NO_EXCEPTION_WERE_THROWN = "No exception were thrown.";



    private LBiDoubleConsumer sut = new LBiDoubleConsumer(){
        public  void doAccept(double a1,double a2)  {
            Function4U.doNothing();
        }
    };

    private LBiDoubleConsumerX<X> opposite = new LBiDoubleConsumerX(){
        public  void doAccept(double a1,double a2) throws ParseException {
            Function4U.doNothing();
        }
    };




    private LBiDoubleConsumer sutAlwaysThrowingUnckeck = LBiDoubleConsumer.l((double a1,double a2) -> {
            throw new IndexOutOfBoundsException(ORIGINAL_MESSAGE);
    });



    @Test
    public void testTupleCall() throws X {

        //FunctionalCall<LDoublePair,LTuple.Void,RuntimeException> theCall = sut;

        LDoublePair domainObject = Tuple4U.tuple((double)100,(double)100);

        Object result = sut.tupleAccept(domainObject);

        assertThat(result)
            .isSameAs(LTuple.Void.INSTANCE);
    }

    @Test
    public void testNestingDoAcceptUnckeck() throws X {

        // then
        try {
            sutAlwaysThrowingUnckeck.nestingDoAccept((double)100,(double)100);
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
            sutAlwaysThrowingUnckeck.shovingDoAccept((double)100,(double)100);
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
        assertThat(LBiDoubleConsumer.l((double a1,double a2) -> Function4U.doNothing() ))
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
        LBiDoubleConsumerX<X> sutThrowing = LBiDoubleConsumerX.lX((double a1,double a2) -> {
            throw new UnsupportedOperationException(ORIGINAL_MESSAGE);
        });

        // when
        LBiDoubleConsumer wrapped = LBiDoubleConsumer.wrap(sutThrowing);

        // then
        try {
            wrapped.doAccept((double)100,(double)100);
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
        LBiDoubleConsumerX<ParseException> sutThrowing = LBiDoubleConsumerX.lX((double a1,double a2) -> {
            throw new ParseException(ORIGINAL_MESSAGE, 0);
        });

        // when
        LBiDoubleConsumer wrapped = LBiDoubleConsumer.wrap(sutThrowing);

        // then
        try {
            wrapped.doAccept((double)100,(double)100);
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
        LBiDoubleConsumer sutThrowing = LBiDoubleConsumer.l((double a1,double a2) -> {
            throw new UnsupportedOperationException();
        });

        // when
        LBiDoubleConsumer wrapped = sutThrowing.handleBiDoubleCons(handler -> handler
            .wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED));

        // then
        try {
            wrapped.doAccept((double)100,(double)100);
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
        LBiDoubleConsumer sutThrowing = LBiDoubleConsumer.l((double a1,double a2) -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LBiDoubleConsumer wrapped = sutThrowing.handleBiDoubleCons(handler -> handler
                .wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED)
                .throwIf(IndexOutOfBoundsException.class));

        // then
        try {
            wrapped.doAccept((double)100,(double)100);
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
        LBiDoubleConsumer sutThrowing = LBiDoubleConsumer.l((double a1,double a2) -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LBiDoubleConsumer wrapped = sutThrowing.handleBiDoubleCons(handler -> handler
                .wrapWhen(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED)
                .throwIf(IndexOutOfBoundsException.class));

        // then
        try {
            wrapped.doAccept((double)100,(double)100);
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
        LBiDoubleConsumer sutThrowing = LBiDoubleConsumer.l((double a1,double a2) -> {
            throw new UnsupportedOperationException(ORIGINAL_MESSAGE);
        });

        // when
        LBiDoubleConsumer wrapped = sutThrowing.handleBiDoubleCons(h -> Function4U.doNothing());

        // then
        try {
            wrapped.doAccept((double)100,(double)100);
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
    public void testbiDoubleConsComposeDouble() throws X {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LBiDoubleConsumer sutO = (double a1,double a2) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo((double)90);
                assertThat(a2).isEqualTo((double)91);
        };

        LDoubleUnaryOperator before1 = p0 -> {
            assertThat(p0).isEqualTo((double)80);
            beforeCalls.incrementAndGet();
            return (double)90;
        };
        LDoubleUnaryOperator before2 = p1 -> {
            assertThat(p1).isEqualTo((double)81);
            beforeCalls.incrementAndGet();
            return (double)91;
        };

        //when
        LBiDoubleConsumer function = sutO.biDoubleConsComposeDouble(before1,before2);
        function.doAccept((double)80,(double)81);

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(beforeCalls.get()).isEqualTo(2);
    }


    @Test
    public void testbiDoubleConsCompose() throws X {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LBiDoubleConsumer sutO = (double a1,double a2) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo((double)90);
                assertThat(a2).isEqualTo((double)91);
        };

        LToDoubleFunction<Integer > before1 = p0 -> {
            assertThat(p0).isEqualTo(Integer.valueOf(80));
            beforeCalls.incrementAndGet();
            return (double)90;
        };
        LToDoubleFunction<Integer > before2 = p1 -> {
            assertThat(p1).isEqualTo(Integer.valueOf(81));
            beforeCalls.incrementAndGet();
            return (double)91;
        };

        //when
        LBiConsumer<Integer ,Integer > function = sutO.biDoubleConsCompose(before1,before2);
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
        LBiDoubleConsumer sutO = (double a1,double a2) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo((double)80);
                assertThat(a2).isEqualTo((double)81);
        };

        LBiDoubleConsumer thenFunction = (double a1,double a2) -> {
                thenFunctionCalled.set(true);
                assertThat(a1).isEqualTo((double)80);
                assertThat(a2).isEqualTo((double)81);
        };

        //when
        LBiDoubleConsumer function = sutO.andThen(thenFunction);
        function.doAccept((double)80,(double)81);

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
        LBiDoubleConsumer sutThrowing = LBiDoubleConsumer.l((double a1,double a2) -> {
            throw new UnsupportedOperationException();
        });

        // when
        sutThrowing.shovingBiDoubleCons().doAccept((double)100,(double)100);
    }

    @Test
    public void testHandleBiDoubleCons() throws X {

        // given
        LBiDoubleConsumer sutThrowing = LBiDoubleConsumer.l((double a1,double a2) -> {
            throw new UnsupportedOperationException();
        });

        // when
        LBiDoubleConsumer wrapped = sutThrowing.handleBiDoubleCons(h -> {
            h.wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED);
        });

        // then
        try {
            wrapped.doAccept((double)100,(double)100);
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

    private void variant1(double a2,double a1) {
    }

    @Test
    public void compilerSubstituteVariant1() {
        LBiDoubleConsumer lambda = LBiDoubleConsumer./**/l1(this::variant1);

        assertThat(lambda).isInstanceOf(LBiDoubleConsumer.V1.class);
    }

    //</editor-fold>


    @Test void safeCompiles() {
        LBiDoubleConsumer r1 = LBiDoubleConsumer.safe(sut);
        LBiDoubleConsumerX r2 = LBiDoubleConsumer.safe(sut);
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
        LSupplier<LBiDoubleConsumer> r1 = LBiDoubleConsumer.safeSupplier(()->sut);
    }

}
