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
public class LBiLongConsumerXTest<X extends ParseException> {
    private static final String ORIGINAL_MESSAGE = "Original message";
    private static final String EXCEPTION_WAS_WRAPPED = "Exception was wrapped.";
    private static final String NO_EXCEPTION_WERE_THROWN = "No exception were thrown.";



    private LBiLongConsumerX<X> sut = new LBiLongConsumerX<X>(){
        public  void doAccept(long a1,long a2)  throws X {
            Function4U.doNothing();
        }
    };

    private LBiLongConsumer opposite = new LBiLongConsumer(){
        public  void doAccept(long a1,long a2)  {
            Function4U.doNothing();
        }
    };



    private LBiLongConsumerX<ParseException> sutAlwaysThrowing = LBiLongConsumerX.lX((a1,a2) -> {
            throw new ParseException(ORIGINAL_MESSAGE, 0);
    });

    private LBiLongConsumerX<RuntimeException> sutAlwaysThrowingUnchecked = LBiLongConsumerX.lX((a1,a2) -> {
            throw new IndexOutOfBoundsException(ORIGINAL_MESSAGE);
    });



    @Test
    public void testTupleCall() throws X {

        LLongPair domainObject = Tuple4U.longPair(100L,100L);

        Object result = sut.tupleAccept(domainObject);

        assertThat(result)
            .isSameAs(LTuple.Void.INSTANCE);
    }

    @Test
    public void testNestingDoAcceptChecked() throws X {

        // then
        try {
            sutAlwaysThrowing.nestingDoAccept(100L,100L);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(NestedException.class)
                    .hasCauseExactlyInstanceOf(ParseException.class)
                    .hasMessage(ORIGINAL_MESSAGE);
        }
    }

    @Test
    public void testNestingDoAcceptUnchecked() throws X {

        // then
        try {
            sutAlwaysThrowingUnchecked.nestingDoAccept(100L,100L);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                    .hasNoCause()
                    .hasMessage(ORIGINAL_MESSAGE);
        }
    }

    @Test
    public void testShovingDoAcceptChecked() throws X {

        // then
        try {
            sutAlwaysThrowing.shovingDoAccept(100L,100L);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(ParseException.class)
                    .hasNoCause()
                    .hasMessage(ORIGINAL_MESSAGE);
        }
    }

    @Test
    public void testShovingDoAcceptUnchecked() throws X {

        // then
        try {
            sutAlwaysThrowingUnchecked.shovingDoAccept(100L,100L);
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
            .isEqualTo("LBiLongConsumerX: void doAccept(long a1,long a2) throws X");
    }

    @Test
    public void testLXMethod() throws X {
        assertThat(LBiLongConsumerX.lX(Function4U::doNothing))
            .isInstanceOf(LBiLongConsumerX.class);
    }

    @Test
    public void testWrapXMethod() throws X {
        assertThat(LBiLongConsumerX.wrapX(opposite))
            .isInstanceOf(LBiLongConsumerX.class);
    }


    @Test
    public void testHandlingDoAcceptMethodWrapsTheException() throws X {

        // given
        LBiLongConsumerX<X> sutThrowing = LBiLongConsumerX.lX((a1,a2) -> {
            throw new UnsupportedOperationException();
        });

        // when
        LBiLongConsumerX<RuntimeException> wrapped = sutThrowing.handleBiLongConsX(handler -> handler
            .wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED));

        // then
        try {
            wrapped.doAccept(100L,100L);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IllegalArgumentException.class)
                    .hasCauseExactlyInstanceOf(UnsupportedOperationException.class)
                    .hasMessage(EXCEPTION_WAS_WRAPPED);
        }
    }

    @Test
    public void testHandleBiLongConsXMethodDoNotWrapsOtherExceptionIf() throws X {

        // given
        LBiLongConsumerX<X> sutThrowing = LBiLongConsumerX.lX((a1,a2) -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LBiLongConsumerX<X> wrapped = sutThrowing.handleBiLongConsX(handler -> handler
                .wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED)
                .throwIf(IndexOutOfBoundsException.class));

        // then
        try {
            wrapped.doAccept(100L,100L);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                    .hasNoCause();
        }
    }

@Test
    public void testHandleBiLongConsXMethodDoNotWrapsOtherExceptionWhen() throws X {

        // given
        LBiLongConsumerX<X> sutThrowing = LBiLongConsumerX.lX((a1,a2) -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LBiLongConsumerX<X> wrapped = sutThrowing.handleBiLongConsX(handler -> handler
                .wrapWhen(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED)
                .throwIf(IndexOutOfBoundsException.class));

        // then
        try {
            wrapped.doAccept(100L,100L);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                    .hasNoCause();
        }
    }


    @Test
    public void testHandleBiLongConsXMishandlingExceptionIsAllowed() throws X {

        // given
        LBiLongConsumerX<X> sutThrowing = LBiLongConsumerX.lX((a1,a2) -> {
            throw (X) new ParseException(ORIGINAL_MESSAGE, 0);
        });

        // when
        LBiLongConsumerX<X> wrapped = sutThrowing.handleBiLongConsX(h -> Function4U.doNothing());

        // then
        try {
            wrapped.doAccept(100L,100L);
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
    public void testBiLongConsComposeLong() throws X {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LBiLongConsumerX<X> sutO = (long a1,long a2) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(90L);
                assertThat(a2).isEqualTo(91L);
        };

        LLongUnaryOperatorX<X> before1 = p0 -> {
            assertThat(p0).isEqualTo(80L);
            beforeCalls.incrementAndGet();
            return 90L;
        };
        LLongUnaryOperatorX<X> before2 = p1 -> {
            assertThat(p1).isEqualTo(81L);
            beforeCalls.incrementAndGet();
            return 91L;
        };

        //when
        LBiLongConsumerX<X> function = sutO.biLongConsComposeLong(before1,before2);
        function.doAccept(80L,81L);

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(beforeCalls.get()).isEqualTo(2);
    }


    @Test
    public void testBiLongConsCompose() throws X {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LBiLongConsumerX<X> sutO = (long a1,long a2) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(90L);
                assertThat(a2).isEqualTo(91L);
        };

        LToLongFunctionX<Integer,X> before1 = p0 -> {
            assertThat(p0).isEqualTo(80);
            beforeCalls.incrementAndGet();
            return 90L;
        };
        LToLongFunctionX<Integer,X> before2 = p1 -> {
            assertThat(p1).isEqualTo(81);
            beforeCalls.incrementAndGet();
            return 91L;
        };

        //when
        LBiConsumerX<Integer,Integer,X> function = sutO.biLongConsCompose(before1,before2);
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
        LBiLongConsumerX<X> sutO = (a1,a2) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(80L);
                assertThat(a2).isEqualTo(81L);
        };

        LBiLongConsumerX<X> thenFunction = (a1,a2) -> {
                thenFunctionCalled.set(true);
                assertThat(a1).isEqualTo(80L);
                assertThat(a2).isEqualTo(81L);
        };

        //when
        LBiLongConsumerX<X> function = sutO.andThen(thenFunction);
        function.doAccept(80L,81L);

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);
    }


    @Test
    public void testNesting() {
        assertThat(sut.nestingBiLongCons())
            .isInstanceOf(LBiLongConsumer.class);
    }

    @Test
    public void testShoving() {
        assertThat(sut.shovingBiLongCons())
            .isInstanceOf(LBiLongConsumer.class);
    }

    @Test
    public void testNestingX() {
        assertThat(sut.nestingBiLongConsX())
            .isInstanceOf(LBiLongConsumerX.class);
    }

    @Test
    public void testShovingX() {
        assertThat(sut.shovingBiLongConsX())
            .isInstanceOf(LBiLongConsumerX.class);
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void testShove() {

        // given
        LBiLongConsumerX<X> sutThrowing = LBiLongConsumerX.lX((a1,a2) -> {
            throw new UnsupportedOperationException();
        });

        // when
        sutThrowing.shovingBiLongCons().doAccept(100L,100L);
    }

    @Test
    public void testHandleBiLongCons() throws X {

        // given
        LBiLongConsumerX<X> sutThrowing = LBiLongConsumerX.lX((a1,a2) -> {
            throw new UnsupportedOperationException();
        });

        // when
        LBiLongConsumerX<X> wrapped = sutThrowing.handleBiLongConsX(h -> {
            h.wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED);
        });

        // then
        try {
            wrapped.doAccept(100L,100L);
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
                .contains("LBiLongConsumerX: void doAccept(long a1,long a2) throws X");
    }


    @Test
    public void isThrowing() {
        assertThat(sut.isThrowing())
            .isTrue();
    }

    //<editor-fold desc="Variants">

    private void variantV1(long a2,long a1) {
    }

    @Test
    public void compilerSubstituteVariantV1() {
        LBiLongConsumerX lambda = LBiLongConsumerX./*<X>*/lX1(this::variantV1);

        assertThat(lambda).isInstanceOf(LBiLongConsumerX.V1.class);
    }

    //</editor-fold>


    @Test void safeCompiles() {
        LBiLongConsumerX r1 = LBiLongConsumerX.safe(sut); //NOSONAR
    }

    @Test void safePropagates() {
        Object result = LBiLongConsumerX.safe(sut);
        assertThat(result).isSameAs(sut);
    }

    @Test void safeProtectsAgainstNpe() {
        Object result = LBiLongConsumerX.safe(null);
        assertThat(result).isSameAs(LBiLongConsumerX.lX(LBiLongConsumerX.safe()));
    }

    @Test <Y extends Throwable> void safeSupplierPropagates() {
        LSupplierX<LBiLongConsumerX<X>,Y> supplier = ()->sut;
        Object result = LBiLongConsumerX.safeSupplier(supplier);
        assertThat(result).isSameAs(supplier);
    }

    @Test <Y extends Throwable> void safeSupplierProtectsAgainstNpe() {
        Object result = LBiLongConsumerX.safeSupplier(null);
        assertThat(result).isSameAs(LBiLongConsumerX.safeSupplier());
    }

    @Test <Y extends Throwable> void safeSupplierCompiles() {
        LSupplierX<LBiLongConsumerX<X>,Y> r1 = LBiLongConsumerX.safeSupplier(()->sut);  //NOSONAR
    }

}
