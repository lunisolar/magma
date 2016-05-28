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

package eu.lunisolar.magma.func.consumer.primitives.obj;

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
public class LBiObjByteConsumerXTest<T1,T2,X extends ParseException> {
    private static final String ORIGINAL_MESSAGE = "Original message";
    private static final String EXCEPTION_WAS_WRAPPED = "Exception was wrapped.";
    private static final String NO_EXCEPTION_WERE_THROWN = "No exception were thrown.";



    private LBiObjByteConsumerX<Integer,Integer,X> sut = new LBiObjByteConsumerX<Integer,Integer,X>(){
        public  void doAccept(Integer a1,Integer a2,byte a3)  throws X {
            Function4U.doNothing();
        }
    };

    private LBiObjByteConsumer<Integer,Integer> opposite = new LBiObjByteConsumer<Integer,Integer>(){
        public  void doAccept(Integer a1,Integer a2,byte a3)  {
            Function4U.doNothing();
        }
    };



    private LBiObjByteConsumerX<Integer,Integer,ParseException> sutAlwaysThrowing = LBiObjByteConsumerX.lX((a1,a2,a3) -> {
            throw new ParseException(ORIGINAL_MESSAGE, 0);
    });

    private LBiObjByteConsumerX<Integer,Integer,RuntimeException> sutAlwaysThrowingUnchecked = LBiObjByteConsumerX.lX((a1,a2,a3) -> {
            throw new IndexOutOfBoundsException(ORIGINAL_MESSAGE);
    });



    @Test
    public void testTupleCall() throws X {

        LBiObjByteTriple<Integer,Integer> domainObject = Tuple4U.biObjByteTriple(100,100,(byte)100);

        Object result = sut.tupleAccept(domainObject);

        assertThat(result)
            .isSameAs(LTuple.Void.INSTANCE);
    }

    @Test
    public void testNestingDoAcceptChecked() throws X {

        // then
        try {
            sutAlwaysThrowing.nestingDoAccept(100,100,(byte)100);
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
            sutAlwaysThrowingUnchecked.nestingDoAccept(100,100,(byte)100);
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
            sutAlwaysThrowing.shovingDoAccept(100,100,(byte)100);
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
            sutAlwaysThrowingUnchecked.shovingDoAccept(100,100,(byte)100);
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
            .isEqualTo("LBiObjByteConsumerX: void doAccept(T1 a1,T2 a2,byte a3) throws X");
    }

    @Test
    public void testLXMethod() throws X {
        assertThat(LBiObjByteConsumerX.lX(Function4U::doNothing))
            .isInstanceOf(LBiObjByteConsumerX.class);
    }

    @Test
    public void testWrapXMethod() throws X {
        assertThat(LBiObjByteConsumerX.wrapX(opposite))
            .isInstanceOf(LBiObjByteConsumerX.class);
    }


    @Test
    public void testHandlingDoAcceptMethodWrapsTheException() throws X {

        // given
        LBiObjByteConsumerX<Integer,Integer,X> sutThrowing = LBiObjByteConsumerX.lX((a1,a2,a3) -> {
            throw new UnsupportedOperationException();
        });

        // when
        LBiObjByteConsumerX<Integer,Integer,RuntimeException> wrapped = sutThrowing.handleBiObjByteConsX(handler -> handler
            .wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED));

        // then
        try {
            wrapped.doAccept(100,100,(byte)100);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IllegalArgumentException.class)
                    .hasCauseExactlyInstanceOf(UnsupportedOperationException.class)
                    .hasMessage(EXCEPTION_WAS_WRAPPED);
        }
    }

    @Test
    public void testHandleBiObjByteConsXMethodDoNotWrapsOtherExceptionIf() throws X {

        // given
        LBiObjByteConsumerX<Integer,Integer,X> sutThrowing = LBiObjByteConsumerX.lX((a1,a2,a3) -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LBiObjByteConsumerX<Integer,Integer,X> wrapped = sutThrowing.handleBiObjByteConsX(handler -> handler
                .wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED)
                .throwIf(IndexOutOfBoundsException.class));

        // then
        try {
            wrapped.doAccept(100,100,(byte)100);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                    .hasNoCause();
        }
    }

@Test
    public void testHandleBiObjByteConsXMethodDoNotWrapsOtherExceptionWhen() throws X {

        // given
        LBiObjByteConsumerX<Integer,Integer,X> sutThrowing = LBiObjByteConsumerX.lX((a1,a2,a3) -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LBiObjByteConsumerX<Integer,Integer,X> wrapped = sutThrowing.handleBiObjByteConsX(handler -> handler
                .wrapWhen(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED)
                .throwIf(IndexOutOfBoundsException.class));

        // then
        try {
            wrapped.doAccept(100,100,(byte)100);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                    .hasNoCause();
        }
    }


    @Test
    public void testHandleBiObjByteConsXMishandlingExceptionIsAllowed() throws X {

        // given
        LBiObjByteConsumerX<Integer,Integer,X> sutThrowing = LBiObjByteConsumerX.lX((a1,a2,a3) -> {
            throw (X) new ParseException(ORIGINAL_MESSAGE, 0);
        });

        // when
        LBiObjByteConsumerX<Integer,Integer,X> wrapped = sutThrowing.handleBiObjByteConsX(h -> Function4U.doNothing());

        // then
        try {
            wrapped.doAccept(100,100,(byte)100);
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
    public void testBiObjByteConsComposeByte() throws X {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LBiObjByteConsumerX<Integer,Integer,X> sutO = (a1,a2,a3) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(90);
                assertThat(a2).isEqualTo(91);
                assertThat(a3).isEqualTo((byte)92);
        };

        LFunctionX<Integer,Integer,X> before1 = p0 -> {
            assertThat(p0).isEqualTo(80);
            beforeCalls.incrementAndGet();
            return 90;
        };
        LFunctionX<Integer,Integer,X> before2 = p1 -> {
            assertThat(p1).isEqualTo(81);
            beforeCalls.incrementAndGet();
            return 91;
        };
        LByteUnaryOperatorX<X> before3 = p2 -> {
            assertThat(p2).isEqualTo((byte)82);
            beforeCalls.incrementAndGet();
            return (byte)92;
        };

        //when
        LBiObjByteConsumerX<Integer,Integer,X> function = sutO.biObjByteConsComposeByte(before1,before2,before3);
        function.doAccept(80,81,(byte)82);

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(beforeCalls.get()).isEqualTo(3);
    }


    @Test
    public void testBiObjByteConsCompose() throws X {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LBiObjByteConsumerX<Integer,Integer,X> sutO = (a1,a2,a3) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(90);
                assertThat(a2).isEqualTo(91);
                assertThat(a3).isEqualTo((byte)92);
        };

        LFunctionX<Integer,Integer,X> before1 = p0 -> {
            assertThat(p0).isEqualTo(80);
            beforeCalls.incrementAndGet();
            return 90;
        };
        LFunctionX<Integer,Integer,X> before2 = p1 -> {
            assertThat(p1).isEqualTo(81);
            beforeCalls.incrementAndGet();
            return 91;
        };
        LToByteFunctionX<Integer,X> before3 = p2 -> {
            assertThat(p2).isEqualTo(82);
            beforeCalls.incrementAndGet();
            return (byte)92;
        };

        //when
        LTriConsumerX<Integer,Integer,Integer,X> function = sutO.biObjByteConsCompose(before1,before2,before3);
        function.doAccept(80,81,82);

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(beforeCalls.get()).isEqualTo(3);
    }

    // </editor-fold>

    @Test
    public void testAndThen() throws X {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);

         //given (+ some assertions)
        LBiObjByteConsumerX<Integer,Integer,X> sutO = (a1,a2,a3) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(80);
                assertThat(a2).isEqualTo(81);
                assertThat(a3).isEqualTo((byte)82);
        };

        LBiObjByteConsumerX<Integer,Integer,X> thenFunction = (a1,a2,a3) -> {
                thenFunctionCalled.set(true);
                assertThat(a1).isEqualTo(80);
                assertThat(a2).isEqualTo(81);
                assertThat(a3).isEqualTo((byte)82);
        };

        //when
        LBiObjByteConsumerX<Integer,Integer,X> function = sutO.andThen(thenFunction);
        function.doAccept(80,81,(byte)82);

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);
    }


    @Test
    public void testNesting() {
        assertThat(sut.nestingBiObjByteCons())
            .isInstanceOf(LBiObjByteConsumer.class);
    }

    @Test
    public void testShoving() {
        assertThat(sut.shovingBiObjByteCons())
            .isInstanceOf(LBiObjByteConsumer.class);
    }

    @Test
    public void testNestingX() {
        assertThat(sut.nestingBiObjByteConsX())
            .isInstanceOf(LBiObjByteConsumerX.class);
    }

    @Test
    public void testShovingX() {
        assertThat(sut.shovingBiObjByteConsX())
            .isInstanceOf(LBiObjByteConsumerX.class);
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void testShove() {

        // given
        LBiObjByteConsumerX<Integer,Integer,X> sutThrowing = LBiObjByteConsumerX.lX((a1,a2,a3) -> {
            throw new UnsupportedOperationException();
        });

        // when
        sutThrowing.shovingBiObjByteCons().doAccept(100,100,(byte)100);
    }

    @Test
    public void testHandleBiObjByteCons() throws X {

        // given
        LBiObjByteConsumerX<Integer,Integer,X> sutThrowing = LBiObjByteConsumerX.lX((a1,a2,a3) -> {
            throw new UnsupportedOperationException();
        });

        // when
        LBiObjByteConsumerX<Integer,Integer,X> wrapped = sutThrowing.handleBiObjByteConsX(h -> {
            h.wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED);
        });

        // then
        try {
            wrapped.doAccept(100,100,(byte)100);
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
                .contains("LBiObjByteConsumerX: void doAccept(T1 a1,T2 a2,byte a3) throws X");
    }


    @Test
    public void isThrowing() {
        assertThat(sut.isThrowing())
            .isTrue();
    }

    //<editor-fold desc="Variants">

    private void variantV1(Integer a1,byte a3,Integer a2) {
    }

    @Test
    public void compilerSubstituteVariantV1() {
        LBiObjByteConsumerX lambda = LBiObjByteConsumerX./*<T1,T2,X>*/lX1(this::variantV1);

        assertThat(lambda).isInstanceOf(LBiObjByteConsumerX.V1.class);
    }


    private void variantV2(Integer a2,Integer a1,byte a3) {
    }

    @Test
    public void compilerSubstituteVariantV2() {
        LBiObjByteConsumerX lambda = LBiObjByteConsumerX./*<T1,T2,X>*/lX2(this::variantV2);

        assertThat(lambda).isInstanceOf(LBiObjByteConsumerX.V2.class);
    }


    private void variantV3(Integer a2,byte a3,Integer a1) {
    }

    @Test
    public void compilerSubstituteVariantV3() {
        LBiObjByteConsumerX lambda = LBiObjByteConsumerX./*<T1,T2,X>*/lX3(this::variantV3);

        assertThat(lambda).isInstanceOf(LBiObjByteConsumerX.V3.class);
    }


    private void variantV4(byte a3,Integer a1,Integer a2) {
    }

    @Test
    public void compilerSubstituteVariantV4() {
        LBiObjByteConsumerX lambda = LBiObjByteConsumerX./*<T1,T2,X>*/lX4(this::variantV4);

        assertThat(lambda).isInstanceOf(LBiObjByteConsumerX.V4.class);
    }


    private void variantV5(byte a3,Integer a2,Integer a1) {
    }

    @Test
    public void compilerSubstituteVariantV5() {
        LBiObjByteConsumerX lambda = LBiObjByteConsumerX./*<T1,T2,X>*/lX5(this::variantV5);

        assertThat(lambda).isInstanceOf(LBiObjByteConsumerX.V5.class);
    }

    //</editor-fold>


    @Test void safeCompiles() {
        LBiObjByteConsumerX r1 = LBiObjByteConsumerX.safe(sut); //NOSONAR
    }

    @Test void safePropagates() {
        Object result = LBiObjByteConsumerX.safe(sut);
        assertThat(result).isSameAs(sut);
    }

    @Test void safeProtectsAgainstNpe() {
        Object result = LBiObjByteConsumerX.safe(null);
        assertThat(result).isSameAs(LBiObjByteConsumerX.lX(LBiObjByteConsumerX.safe()));
    }

    @Test <Y extends Throwable> void safeSupplierPropagates() {
        LSupplierX<LBiObjByteConsumerX<Integer,Integer,X>,Y> supplier = ()->sut;
        Object result = LBiObjByteConsumerX.safeSupplier(supplier);
        assertThat(result).isSameAs(supplier);
    }

    @Test <Y extends Throwable> void safeSupplierProtectsAgainstNpe() {
        Object result = LBiObjByteConsumerX.safeSupplier(null);
        assertThat(result).isSameAs(LBiObjByteConsumerX.safeSupplier());
    }

    @Test <Y extends Throwable> void safeSupplierCompiles() {
        LSupplierX<LBiObjByteConsumerX<Integer,Integer,X>,Y> r1 = LBiObjByteConsumerX.safeSupplier(()->sut);  //NOSONAR
    }

}
