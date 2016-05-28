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
public class LBiObjLongConsumerXTest<T1,T2,X extends ParseException> {
    private static final String ORIGINAL_MESSAGE = "Original message";
    private static final String EXCEPTION_WAS_WRAPPED = "Exception was wrapped.";
    private static final String NO_EXCEPTION_WERE_THROWN = "No exception were thrown.";



    private LBiObjLongConsumerX<Integer,Integer,X> sut = new LBiObjLongConsumerX<Integer,Integer,X>(){
        public  void doAccept(Integer a1,Integer a2,long a3)  throws X {
            Function4U.doNothing();
        }
    };

    private LBiObjLongConsumer<Integer,Integer> opposite = new LBiObjLongConsumer<Integer,Integer>(){
        public  void doAccept(Integer a1,Integer a2,long a3)  {
            Function4U.doNothing();
        }
    };



    private LBiObjLongConsumerX<Integer,Integer,ParseException> sutAlwaysThrowing = LBiObjLongConsumerX.lX((a1,a2,a3) -> {
            throw new ParseException(ORIGINAL_MESSAGE, 0);
    });

    private LBiObjLongConsumerX<Integer,Integer,RuntimeException> sutAlwaysThrowingUnchecked = LBiObjLongConsumerX.lX((a1,a2,a3) -> {
            throw new IndexOutOfBoundsException(ORIGINAL_MESSAGE);
    });



    @Test
    public void testTupleCall() throws X {

        LBiObjLongTriple<Integer,Integer> domainObject = Tuple4U.biObjLongTriple(100,100,100L);

        Object result = sut.tupleAccept(domainObject);

        assertThat(result)
            .isSameAs(LTuple.Void.INSTANCE);
    }

    @Test
    public void testNestingDoAcceptChecked() throws X {

        // then
        try {
            sutAlwaysThrowing.nestingDoAccept(100,100,100L);
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
            sutAlwaysThrowingUnchecked.nestingDoAccept(100,100,100L);
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
            sutAlwaysThrowing.shovingDoAccept(100,100,100L);
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
            sutAlwaysThrowingUnchecked.shovingDoAccept(100,100,100L);
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
            .isEqualTo("LBiObjLongConsumerX: void doAccept(T1 a1,T2 a2,long a3) throws X");
    }

    @Test
    public void testLXMethod() throws X {
        assertThat(LBiObjLongConsumerX.lX(Function4U::doNothing))
            .isInstanceOf(LBiObjLongConsumerX.class);
    }

    @Test
    public void testWrapXMethod() throws X {
        assertThat(LBiObjLongConsumerX.wrapX(opposite))
            .isInstanceOf(LBiObjLongConsumerX.class);
    }


    @Test
    public void testHandlingDoAcceptMethodWrapsTheException() throws X {

        // given
        LBiObjLongConsumerX<Integer,Integer,X> sutThrowing = LBiObjLongConsumerX.lX((a1,a2,a3) -> {
            throw new UnsupportedOperationException();
        });

        // when
        LBiObjLongConsumerX<Integer,Integer,RuntimeException> wrapped = sutThrowing.handleBiObjLongConsX(handler -> handler
            .wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED));

        // then
        try {
            wrapped.doAccept(100,100,100L);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IllegalArgumentException.class)
                    .hasCauseExactlyInstanceOf(UnsupportedOperationException.class)
                    .hasMessage(EXCEPTION_WAS_WRAPPED);
        }
    }

    @Test
    public void testHandleBiObjLongConsXMethodDoNotWrapsOtherExceptionIf() throws X {

        // given
        LBiObjLongConsumerX<Integer,Integer,X> sutThrowing = LBiObjLongConsumerX.lX((a1,a2,a3) -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LBiObjLongConsumerX<Integer,Integer,X> wrapped = sutThrowing.handleBiObjLongConsX(handler -> handler
                .wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED)
                .throwIf(IndexOutOfBoundsException.class));

        // then
        try {
            wrapped.doAccept(100,100,100L);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                    .hasNoCause();
        }
    }

@Test
    public void testHandleBiObjLongConsXMethodDoNotWrapsOtherExceptionWhen() throws X {

        // given
        LBiObjLongConsumerX<Integer,Integer,X> sutThrowing = LBiObjLongConsumerX.lX((a1,a2,a3) -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LBiObjLongConsumerX<Integer,Integer,X> wrapped = sutThrowing.handleBiObjLongConsX(handler -> handler
                .wrapWhen(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED)
                .throwIf(IndexOutOfBoundsException.class));

        // then
        try {
            wrapped.doAccept(100,100,100L);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                    .hasNoCause();
        }
    }


    @Test
    public void testHandleBiObjLongConsXMishandlingExceptionIsAllowed() throws X {

        // given
        LBiObjLongConsumerX<Integer,Integer,X> sutThrowing = LBiObjLongConsumerX.lX((a1,a2,a3) -> {
            throw (X) new ParseException(ORIGINAL_MESSAGE, 0);
        });

        // when
        LBiObjLongConsumerX<Integer,Integer,X> wrapped = sutThrowing.handleBiObjLongConsX(h -> Function4U.doNothing());

        // then
        try {
            wrapped.doAccept(100,100,100L);
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
    public void testBiObjLongConsComposeLong() throws X {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LBiObjLongConsumerX<Integer,Integer,X> sutO = (a1,a2,a3) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(90);
                assertThat(a2).isEqualTo(91);
                assertThat(a3).isEqualTo(92L);
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
        LLongUnaryOperatorX<X> before3 = p2 -> {
            assertThat(p2).isEqualTo(82L);
            beforeCalls.incrementAndGet();
            return 92L;
        };

        //when
        LBiObjLongConsumerX<Integer,Integer,X> function = sutO.biObjLongConsComposeLong(before1,before2,before3);
        function.doAccept(80,81,82L);

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(beforeCalls.get()).isEqualTo(3);
    }


    @Test
    public void testBiObjLongConsCompose() throws X {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LBiObjLongConsumerX<Integer,Integer,X> sutO = (a1,a2,a3) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(90);
                assertThat(a2).isEqualTo(91);
                assertThat(a3).isEqualTo(92L);
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
        LToLongFunctionX<Integer,X> before3 = p2 -> {
            assertThat(p2).isEqualTo(82);
            beforeCalls.incrementAndGet();
            return 92L;
        };

        //when
        LTriConsumerX<Integer,Integer,Integer,X> function = sutO.biObjLongConsCompose(before1,before2,before3);
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
        LBiObjLongConsumerX<Integer,Integer,X> sutO = (a1,a2,a3) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(80);
                assertThat(a2).isEqualTo(81);
                assertThat(a3).isEqualTo(82L);
        };

        LBiObjLongConsumerX<Integer,Integer,X> thenFunction = (a1,a2,a3) -> {
                thenFunctionCalled.set(true);
                assertThat(a1).isEqualTo(80);
                assertThat(a2).isEqualTo(81);
                assertThat(a3).isEqualTo(82L);
        };

        //when
        LBiObjLongConsumerX<Integer,Integer,X> function = sutO.andThen(thenFunction);
        function.doAccept(80,81,82L);

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);
    }


    @Test
    public void testNesting() {
        assertThat(sut.nestingBiObjLongCons())
            .isInstanceOf(LBiObjLongConsumer.class);
    }

    @Test
    public void testShoving() {
        assertThat(sut.shovingBiObjLongCons())
            .isInstanceOf(LBiObjLongConsumer.class);
    }

    @Test
    public void testNestingX() {
        assertThat(sut.nestingBiObjLongConsX())
            .isInstanceOf(LBiObjLongConsumerX.class);
    }

    @Test
    public void testShovingX() {
        assertThat(sut.shovingBiObjLongConsX())
            .isInstanceOf(LBiObjLongConsumerX.class);
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void testShove() {

        // given
        LBiObjLongConsumerX<Integer,Integer,X> sutThrowing = LBiObjLongConsumerX.lX((a1,a2,a3) -> {
            throw new UnsupportedOperationException();
        });

        // when
        sutThrowing.shovingBiObjLongCons().doAccept(100,100,100L);
    }

    @Test
    public void testHandleBiObjLongCons() throws X {

        // given
        LBiObjLongConsumerX<Integer,Integer,X> sutThrowing = LBiObjLongConsumerX.lX((a1,a2,a3) -> {
            throw new UnsupportedOperationException();
        });

        // when
        LBiObjLongConsumerX<Integer,Integer,X> wrapped = sutThrowing.handleBiObjLongConsX(h -> {
            h.wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED);
        });

        // then
        try {
            wrapped.doAccept(100,100,100L);
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
                .contains("LBiObjLongConsumerX: void doAccept(T1 a1,T2 a2,long a3) throws X");
    }


    @Test
    public void isThrowing() {
        assertThat(sut.isThrowing())
            .isTrue();
    }

    //<editor-fold desc="Variants">

    private void variantV1(Integer a1,long a3,Integer a2) {
    }

    @Test
    public void compilerSubstituteVariantV1() {
        LBiObjLongConsumerX lambda = LBiObjLongConsumerX./*<T1,T2,X>*/lX1(this::variantV1);

        assertThat(lambda).isInstanceOf(LBiObjLongConsumerX.V1.class);
    }


    private void variantV2(Integer a2,Integer a1,long a3) {
    }

    @Test
    public void compilerSubstituteVariantV2() {
        LBiObjLongConsumerX lambda = LBiObjLongConsumerX./*<T1,T2,X>*/lX2(this::variantV2);

        assertThat(lambda).isInstanceOf(LBiObjLongConsumerX.V2.class);
    }


    private void variantV3(Integer a2,long a3,Integer a1) {
    }

    @Test
    public void compilerSubstituteVariantV3() {
        LBiObjLongConsumerX lambda = LBiObjLongConsumerX./*<T1,T2,X>*/lX3(this::variantV3);

        assertThat(lambda).isInstanceOf(LBiObjLongConsumerX.V3.class);
    }


    private void variantV4(long a3,Integer a1,Integer a2) {
    }

    @Test
    public void compilerSubstituteVariantV4() {
        LBiObjLongConsumerX lambda = LBiObjLongConsumerX./*<T1,T2,X>*/lX4(this::variantV4);

        assertThat(lambda).isInstanceOf(LBiObjLongConsumerX.V4.class);
    }


    private void variantV5(long a3,Integer a2,Integer a1) {
    }

    @Test
    public void compilerSubstituteVariantV5() {
        LBiObjLongConsumerX lambda = LBiObjLongConsumerX./*<T1,T2,X>*/lX5(this::variantV5);

        assertThat(lambda).isInstanceOf(LBiObjLongConsumerX.V5.class);
    }

    //</editor-fold>


    @Test void safeCompiles() {
        LBiObjLongConsumerX r1 = LBiObjLongConsumerX.safe(sut); //NOSONAR
    }

    @Test void safePropagates() {
        Object result = LBiObjLongConsumerX.safe(sut);
        assertThat(result).isSameAs(sut);
    }

    @Test void safeProtectsAgainstNpe() {
        Object result = LBiObjLongConsumerX.safe(null);
        assertThat(result).isSameAs(LBiObjLongConsumerX.lX(LBiObjLongConsumerX.safe()));
    }

    @Test <Y extends Throwable> void safeSupplierPropagates() {
        LSupplierX<LBiObjLongConsumerX<Integer,Integer,X>,Y> supplier = ()->sut;
        Object result = LBiObjLongConsumerX.safeSupplier(supplier);
        assertThat(result).isSameAs(supplier);
    }

    @Test <Y extends Throwable> void safeSupplierProtectsAgainstNpe() {
        Object result = LBiObjLongConsumerX.safeSupplier(null);
        assertThat(result).isSameAs(LBiObjLongConsumerX.safeSupplier());
    }

    @Test <Y extends Throwable> void safeSupplierCompiles() {
        LSupplierX<LBiObjLongConsumerX<Integer,Integer,X>,Y> r1 = LBiObjLongConsumerX.safeSupplier(()->sut);  //NOSONAR
    }

}
