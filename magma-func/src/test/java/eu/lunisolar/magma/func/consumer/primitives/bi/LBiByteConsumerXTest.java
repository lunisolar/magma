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
public class LBiByteConsumerXTest<X extends ParseException> {
    private static final String ORIGINAL_MESSAGE = "Original message";
    private static final String EXCEPTION_WAS_WRAPPED = "Exception was wrapped.";
    private static final String NO_EXCEPTION_WERE_THROWN = "No exception were thrown.";



    private LBiByteConsumerX<X> sut = new LBiByteConsumerX<X>(){
        public  void doAccept(byte a1,byte a2)  throws X {
            Function4U.doNothing();
        }
    };

    private LBiByteConsumer opposite = new LBiByteConsumer(){
        public  void doAccept(byte a1,byte a2)  {
            Function4U.doNothing();
        }
    };



    private LBiByteConsumerX<ParseException> sutAlwaysThrowing = LBiByteConsumerX.lX((a1,a2) -> {
            throw new ParseException(ORIGINAL_MESSAGE, 0);
    });

    private LBiByteConsumerX<RuntimeException> sutAlwaysThrowingUnchecked = LBiByteConsumerX.lX((a1,a2) -> {
            throw new IndexOutOfBoundsException(ORIGINAL_MESSAGE);
    });



    @Test
    public void testTupleCall() throws X {

        LBytePair domainObject = Tuple4U.bytePair((byte)100,(byte)100);

        Object result = sut.tupleAccept(domainObject);

        assertThat(result)
            .isSameAs(LTuple.Void.INSTANCE);
    }

    @Test
    public void testNestingDoAcceptChecked() throws X {

        // then
        try {
            sutAlwaysThrowing.nestingDoAccept((byte)100,(byte)100);
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
            sutAlwaysThrowingUnchecked.nestingDoAccept((byte)100,(byte)100);
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
            sutAlwaysThrowing.shovingDoAccept((byte)100,(byte)100);
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
            sutAlwaysThrowingUnchecked.shovingDoAccept((byte)100,(byte)100);
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
            .isEqualTo("LBiByteConsumerX: void doAccept(byte a1,byte a2) throws X");
    }

    @Test
    public void testLXMethod() throws X {
        assertThat(LBiByteConsumerX.lX(Function4U::doNothing))
            .isInstanceOf(LBiByteConsumerX.class);
    }

    @Test
    public void testWrapXMethod() throws X {
        assertThat(LBiByteConsumerX.wrapX(opposite))
            .isInstanceOf(LBiByteConsumerX.class);
    }


    @Test
    public void testHandlingDoAcceptMethodWrapsTheException() throws X {

        // given
        LBiByteConsumerX<X> sutThrowing = LBiByteConsumerX.lX((a1,a2) -> {
            throw new UnsupportedOperationException();
        });

        // when
        LBiByteConsumerX<RuntimeException> wrapped = sutThrowing.handleBiByteConsX(handler -> handler
            .wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED));

        // then
        try {
            wrapped.doAccept((byte)100,(byte)100);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IllegalArgumentException.class)
                    .hasCauseExactlyInstanceOf(UnsupportedOperationException.class)
                    .hasMessage(EXCEPTION_WAS_WRAPPED);
        }
    }

    @Test
    public void testHandleBiByteConsXMethodDoNotWrapsOtherExceptionIf() throws X {

        // given
        LBiByteConsumerX<X> sutThrowing = LBiByteConsumerX.lX((a1,a2) -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LBiByteConsumerX<X> wrapped = sutThrowing.handleBiByteConsX(handler -> handler
                .wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED)
                .throwIf(IndexOutOfBoundsException.class));

        // then
        try {
            wrapped.doAccept((byte)100,(byte)100);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                    .hasNoCause();
        }
    }

@Test
    public void testHandleBiByteConsXMethodDoNotWrapsOtherExceptionWhen() throws X {

        // given
        LBiByteConsumerX<X> sutThrowing = LBiByteConsumerX.lX((a1,a2) -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LBiByteConsumerX<X> wrapped = sutThrowing.handleBiByteConsX(handler -> handler
                .wrapWhen(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED)
                .throwIf(IndexOutOfBoundsException.class));

        // then
        try {
            wrapped.doAccept((byte)100,(byte)100);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                    .hasNoCause();
        }
    }


    @Test
    public void testHandleBiByteConsXMishandlingExceptionIsAllowed() throws X {

        // given
        LBiByteConsumerX<X> sutThrowing = LBiByteConsumerX.lX((a1,a2) -> {
            throw (X) new ParseException(ORIGINAL_MESSAGE, 0);
        });

        // when
        LBiByteConsumerX<X> wrapped = sutThrowing.handleBiByteConsX(h -> Function4U.doNothing());

        // then
        try {
            wrapped.doAccept((byte)100,(byte)100);
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
    public void testBiByteConsComposeByte() throws X {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LBiByteConsumerX<X> sutO = (a1,a2) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo((byte)90);
                assertThat(a2).isEqualTo((byte)91);
        };

        LByteUnaryOperatorX<X> before1 = p0 -> {
            assertThat(p0).isEqualTo((byte)80);
            beforeCalls.incrementAndGet();
            return (byte)90;
        };
        LByteUnaryOperatorX<X> before2 = p1 -> {
            assertThat(p1).isEqualTo((byte)81);
            beforeCalls.incrementAndGet();
            return (byte)91;
        };

        //when
        LBiByteConsumerX<X> function = sutO.biByteConsComposeByte(before1,before2);
        function.doAccept((byte)80,(byte)81);

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(beforeCalls.get()).isEqualTo(2);
    }


    @Test
    public void testBiByteConsCompose() throws X {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LBiByteConsumerX<X> sutO = (a1,a2) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo((byte)90);
                assertThat(a2).isEqualTo((byte)91);
        };

        LToByteFunctionX<Integer,X> before1 = p0 -> {
            assertThat(p0).isEqualTo(80);
            beforeCalls.incrementAndGet();
            return (byte)90;
        };
        LToByteFunctionX<Integer,X> before2 = p1 -> {
            assertThat(p1).isEqualTo(81);
            beforeCalls.incrementAndGet();
            return (byte)91;
        };

        //when
        LBiConsumerX<Integer,Integer,X> function = sutO.biByteConsCompose(before1,before2);
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
        LBiByteConsumerX<X> sutO = (a1,a2) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo((byte)80);
                assertThat(a2).isEqualTo((byte)81);
        };

        LBiByteConsumerX<X> thenFunction = (a1,a2) -> {
                thenFunctionCalled.set(true);
                assertThat(a1).isEqualTo((byte)80);
                assertThat(a2).isEqualTo((byte)81);
        };

        //when
        LBiByteConsumerX<X> function = sutO.andThen(thenFunction);
        function.doAccept((byte)80,(byte)81);

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);
    }


    @Test
    public void testNesting() {
        assertThat(sut.nestingBiByteCons())
            .isInstanceOf(LBiByteConsumer.class);
    }

    @Test
    public void testShoving() {
        assertThat(sut.shovingBiByteCons())
            .isInstanceOf(LBiByteConsumer.class);
    }

    @Test
    public void testNestingX() {
        assertThat(sut.nestingBiByteConsX())
            .isInstanceOf(LBiByteConsumerX.class);
    }

    @Test
    public void testShovingX() {
        assertThat(sut.shovingBiByteConsX())
            .isInstanceOf(LBiByteConsumerX.class);
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void testShove() {

        // given
        LBiByteConsumerX<X> sutThrowing = LBiByteConsumerX.lX((a1,a2) -> {
            throw new UnsupportedOperationException();
        });

        // when
        sutThrowing.shovingBiByteCons().doAccept((byte)100,(byte)100);
    }

    @Test
    public void testHandleBiByteCons() throws X {

        // given
        LBiByteConsumerX<X> sutThrowing = LBiByteConsumerX.lX((a1,a2) -> {
            throw new UnsupportedOperationException();
        });

        // when
        LBiByteConsumerX<X> wrapped = sutThrowing.handleBiByteConsX(h -> {
            h.wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED);
        });

        // then
        try {
            wrapped.doAccept((byte)100,(byte)100);
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
                .contains("LBiByteConsumerX: void doAccept(byte a1,byte a2) throws X");
    }


    @Test
    public void isThrowing() {
        assertThat(sut.isThrowing())
            .isTrue();
    }

    //<editor-fold desc="Variants">

    private void variantV1(byte a2,byte a1) {
    }

    @Test
    public void compilerSubstituteVariantV1() {
        LBiByteConsumerX lambda = LBiByteConsumerX./*<X>*/lX1(this::variantV1);

        assertThat(lambda).isInstanceOf(LBiByteConsumerX.V1.class);
    }

    //</editor-fold>


    @Test void safeCompiles() {
        LBiByteConsumerX r1 = LBiByteConsumerX.safe(sut); //NOSONAR
    }

    @Test void safePropagates() {
        Object result = LBiByteConsumerX.safe(sut);
        assertThat(result).isSameAs(sut);
    }

    @Test void safeProtectsAgainstNpe() {
        Object result = LBiByteConsumerX.safe(null);
        assertThat(result).isSameAs(LBiByteConsumerX.lX(LBiByteConsumerX.safe()));
    }

    @Test <Y extends Throwable> void safeSupplierPropagates() {
        LSupplierX<LBiByteConsumerX<X>,Y> supplier = ()->sut;
        Object result = LBiByteConsumerX.safeSupplier(supplier);
        assertThat(result).isSameAs(supplier);
    }

    @Test <Y extends Throwable> void safeSupplierProtectsAgainstNpe() {
        Object result = LBiByteConsumerX.safeSupplier(null);
        assertThat(result).isSameAs(LBiByteConsumerX.safeSupplier());
    }

    @Test <Y extends Throwable> void safeSupplierCompiles() {
        LSupplierX<LBiByteConsumerX<X>,Y> r1 = LBiByteConsumerX.safeSupplier(()->sut);  //NOSONAR
    }

}
