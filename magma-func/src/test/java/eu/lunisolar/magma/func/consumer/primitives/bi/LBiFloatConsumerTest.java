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
public class LBiFloatConsumerTest<X extends ParseException> {
    private static final String ORIGINAL_MESSAGE = "Original message";
    private static final String EXCEPTION_WAS_WRAPPED = "Exception was wrapped.";
    private static final String NO_EXCEPTION_WERE_THROWN = "No exception were thrown.";



    private LBiFloatConsumer sut = new LBiFloatConsumer(){
        public  void doAccept(float a1,float a2)  {
            Function4U.doNothing();
        }
    };

    private LBiFloatConsumerX<X> opposite = new LBiFloatConsumerX<X>(){
        public  void doAccept(float a1,float a2)  throws X {
            Function4U.doNothing();
        }
    };




    private LBiFloatConsumerX<RuntimeException> sutAlwaysThrowingUnchecked = LBiFloatConsumer.l((a1,a2) -> {
            throw new IndexOutOfBoundsException(ORIGINAL_MESSAGE);
    });



    @Test
    public void testTupleCall() throws X {

        LFloatPair domainObject = Tuple4U.floatPair(100f,100f);

        Object result = sut.tupleAccept(domainObject);

        assertThat(result)
            .isSameAs(LTuple.Void.INSTANCE);
    }

    @Test
    public void testNestingDoAcceptUnchecked() throws X {

        // then
        try {
            sutAlwaysThrowingUnchecked.nestingDoAccept(100f,100f);
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
            sutAlwaysThrowingUnchecked.shovingDoAccept(100f,100f);
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
            .isEqualTo("LBiFloatConsumer: void doAccept(float a1,float a2)");
    }

    @Test
    public void testLMethod() throws X {
        assertThat(LBiFloatConsumer.l(Function4U::doNothing))
            .isInstanceOf(LBiFloatConsumer.class);
    }

    @Test
    public void testWrapMethod() throws X {
        assertThat(LBiFloatConsumer.wrap(opposite))
            .isInstanceOf(LBiFloatConsumer.class);
    }

    @Test
    public void testWrapMethodDoNotWrapsRuntimeException() throws X {
        // given
        LBiFloatConsumerX<X> sutThrowing = LBiFloatConsumerX.lX((a1,a2) -> {
            throw new UnsupportedOperationException(ORIGINAL_MESSAGE);
        });

        // when
        LBiFloatConsumer wrapped = LBiFloatConsumer.wrap(sutThrowing);

        // then
        try {
            wrapped.doAccept(100f,100f);
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
        LBiFloatConsumerX<ParseException> sutThrowing = LBiFloatConsumerX.lX((a1,a2) -> {
            throw new ParseException(ORIGINAL_MESSAGE, 0);
        });

        // when
        LBiFloatConsumer wrapped = LBiFloatConsumer.wrap(sutThrowing);

        // then
        try {
            wrapped.doAccept(100f,100f);
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
        LBiFloatConsumer sutThrowing = LBiFloatConsumer.l((a1,a2) -> {
            throw new UnsupportedOperationException();
        });

        // when
        LBiFloatConsumer wrapped = sutThrowing.handleBiFloatCons(handler -> handler
            .wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED));

        // then
        try {
            wrapped.doAccept(100f,100f);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IllegalArgumentException.class)
                    .hasCauseExactlyInstanceOf(UnsupportedOperationException.class)
                    .hasMessage(EXCEPTION_WAS_WRAPPED);
        }
    }

    @Test
    public void testHandleBiFloatConsMethodDoNotWrapsOtherExceptionIf() throws X {

        // given
        LBiFloatConsumer sutThrowing = LBiFloatConsumer.l((a1,a2) -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LBiFloatConsumer wrapped = sutThrowing.handleBiFloatCons(handler -> handler
                .wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED)
                .throwIf(IndexOutOfBoundsException.class));

        // then
        try {
            wrapped.doAccept(100f,100f);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                    .hasNoCause();
        }
    }

@Test
    public void testHandleBiFloatConsMethodDoNotWrapsOtherExceptionWhen() throws X {

        // given
        LBiFloatConsumer sutThrowing = LBiFloatConsumer.l((a1,a2) -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LBiFloatConsumer wrapped = sutThrowing.handleBiFloatCons(handler -> handler
                .wrapWhen(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED)
                .throwIf(IndexOutOfBoundsException.class));

        // then
        try {
            wrapped.doAccept(100f,100f);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                    .hasNoCause();
        }
    }


    @Test
    public void testHandleBiFloatConsMishandlingExceptionIsAllowed() throws X {

        // given
        LBiFloatConsumer sutThrowing = LBiFloatConsumer.l((a1,a2) -> {
            throw new UnsupportedOperationException(ORIGINAL_MESSAGE);
        });

        // when
        LBiFloatConsumer wrapped = sutThrowing.handleBiFloatCons(h -> Function4U.doNothing());

        // then
        try {
            wrapped.doAccept(100f,100f);
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
    public void testBiFloatConsComposeFloat() throws X {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LBiFloatConsumer sutO = (a1,a2) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(90f);
                assertThat(a2).isEqualTo(91f);
        };

        LFloatUnaryOperator before1 = p0 -> {
            assertThat(p0).isEqualTo(80f);
            beforeCalls.incrementAndGet();
            return 90f;
        };
        LFloatUnaryOperator before2 = p1 -> {
            assertThat(p1).isEqualTo(81f);
            beforeCalls.incrementAndGet();
            return 91f;
        };

        //when
        LBiFloatConsumer function = sutO.biFloatConsComposeFloat(before1,before2);
        function.doAccept(80f,81f);

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(beforeCalls.get()).isEqualTo(2);
    }


    @Test
    public void testBiFloatConsCompose() throws X {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LBiFloatConsumer sutO = (a1,a2) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(90f);
                assertThat(a2).isEqualTo(91f);
        };

        LToFloatFunction<Integer> before1 = p0 -> {
            assertThat(p0).isEqualTo(80);
            beforeCalls.incrementAndGet();
            return 90f;
        };
        LToFloatFunction<Integer> before2 = p1 -> {
            assertThat(p1).isEqualTo(81);
            beforeCalls.incrementAndGet();
            return 91f;
        };

        //when
        LBiConsumer<Integer,Integer> function = sutO.biFloatConsCompose(before1,before2);
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
        LBiFloatConsumer sutO = (a1,a2) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(80f);
                assertThat(a2).isEqualTo(81f);
        };

        LBiFloatConsumer thenFunction = (a1,a2) -> {
                thenFunctionCalled.set(true);
                assertThat(a1).isEqualTo(80f);
                assertThat(a2).isEqualTo(81f);
        };

        //when
        LBiFloatConsumer function = sutO.andThen(thenFunction);
        function.doAccept(80f,81f);

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);
    }


    @Test
    public void testNesting() {
        assertThat(sut.nestingBiFloatCons())
            .isSameAs(sut)
            .isInstanceOf(LBiFloatConsumer.class);
    }

    @Test
    public void testShoving() {
        assertThat(sut.shovingBiFloatCons())
            .isSameAs(sut)
            .isInstanceOf(LBiFloatConsumer.class);
    }

    @Test
    public void testNestingX() {
        assertThat(sut.nestingBiFloatConsX())
            .isSameAs(sut)
            .isInstanceOf(LBiFloatConsumerX.class);
    }

    @Test
    public void testShovingX() {
        assertThat(sut.shovingBiFloatConsX())
            .isSameAs(sut)
            .isInstanceOf(LBiFloatConsumerX.class);
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void testShove() {

        // given
        LBiFloatConsumer sutThrowing = LBiFloatConsumer.l((a1,a2) -> {
            throw new UnsupportedOperationException();
        });

        // when
        sutThrowing.shovingBiFloatCons().doAccept(100f,100f);
    }

    @Test
    public void testHandleBiFloatCons() throws X {

        // given
        LBiFloatConsumer sutThrowing = LBiFloatConsumer.l((a1,a2) -> {
            throw new UnsupportedOperationException();
        });

        // when
        LBiFloatConsumer wrapped = sutThrowing.handleBiFloatCons(h -> {
            h.wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED);
        });

        // then
        try {
            wrapped.doAccept(100f,100f);
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
                .contains("LBiFloatConsumer: void doAccept(float a1,float a2)");
    }


    @Test
    public void isThrowing() {
        assertThat(sut.isThrowing())
            .isFalse();
    }

    //<editor-fold desc="Variants">

    private void variantV1(float a2,float a1) {
    }

    @Test
    public void compilerSubstituteVariantV1() {
        LBiFloatConsumer lambda = LBiFloatConsumer./**/l1(this::variantV1);

        assertThat(lambda).isInstanceOf(LBiFloatConsumer.V1.class);
    }

    //</editor-fold>


    @Test void safeCompiles() {
        LBiFloatConsumer r1 = LBiFloatConsumer.safe(sut); //NOSONAR
        LBiFloatConsumerX r2 = LBiFloatConsumer.safe(sut); //NOSONAR
    }

    @Test void safePropagates() {
        Object result = LBiFloatConsumer.safe(sut);
        assertThat(result).isSameAs(sut);
    }

    @Test void safeProtectsAgainstNpe() {
        Object result = LBiFloatConsumer.safe(null);
        assertThat(result).isSameAs(LBiFloatConsumer.l(LBiFloatConsumer.safe()));
    }

    @Test  void safeSupplierPropagates() {
        LSupplier<LBiFloatConsumer> supplier = ()->sut;
        Object result = LBiFloatConsumer.safeSupplier(supplier);
        assertThat(result).isSameAs(supplier);
    }

    @Test  void safeSupplierProtectsAgainstNpe() {
        Object result = LBiFloatConsumer.safeSupplier(null);
        assertThat(result).isSameAs(LBiFloatConsumer.safeSupplier());
    }

    @Test  void safeSupplierCompiles() {
        LSupplier<LBiFloatConsumer> r1 = LBiFloatConsumer.safeSupplier(()->sut);  //NOSONAR
    }

}
