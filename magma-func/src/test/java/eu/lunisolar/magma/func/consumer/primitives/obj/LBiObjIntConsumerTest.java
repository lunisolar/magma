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
public class LBiObjIntConsumerTest<T1,T2,X extends ParseException> {
    private static final String ORIGINAL_MESSAGE = "Original message";
    private static final String EXCEPTION_WAS_WRAPPED = "Exception was wrapped.";
    private static final String NO_EXCEPTION_WERE_THROWN = "No exception were thrown.";



    private LBiObjIntConsumer<Integer,Integer> sut = new LBiObjIntConsumer<Integer,Integer>(){
        public  void doAccept(Integer a1,Integer a2,int a3)  {
            Function4U.doNothing();
        }
    };

    private LBiObjIntConsumerX<Integer,Integer,X> opposite = new LBiObjIntConsumerX<Integer,Integer,X>(){
        public  void doAccept(Integer a1,Integer a2,int a3)  throws X {
            Function4U.doNothing();
        }
    };




    private LBiObjIntConsumerX<Integer,Integer,RuntimeException> sutAlwaysThrowingUnchecked = LBiObjIntConsumer.l((a1,a2,a3) -> {
            throw new IndexOutOfBoundsException(ORIGINAL_MESSAGE);
    });



    @Test
    public void testTupleCall() throws X {

        LBiObjIntTriple<Integer,Integer> domainObject = Tuple4U.biObjIntTriple(100,100,100);

        Object result = sut.tupleAccept(domainObject);

        assertThat(result)
            .isSameAs(LTuple.Void.INSTANCE);
    }

    @Test
    public void testNestingDoAcceptUnchecked() throws X {

        // then
        try {
            sutAlwaysThrowingUnchecked.nestingDoAccept(100,100,100);
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
            sutAlwaysThrowingUnchecked.shovingDoAccept(100,100,100);
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
            .isEqualTo("LBiObjIntConsumer: void doAccept(T1 a1,T2 a2,int a3)");
    }

    @Test
    public void testLMethod() throws X {
        assertThat(LBiObjIntConsumer.l(Function4U::doNothing))
            .isInstanceOf(LBiObjIntConsumer.class);
    }

    @Test
    public void testWrapMethod() throws X {
        assertThat(LBiObjIntConsumer.wrap(opposite))
            .isInstanceOf(LBiObjIntConsumer.class);
    }

    @Test
    public void testWrapMethodDoNotWrapsRuntimeException() throws X {
        // given
        LBiObjIntConsumerX<Integer,Integer,X> sutThrowing = LBiObjIntConsumerX.lX((a1,a2,a3) -> {
            throw new UnsupportedOperationException(ORIGINAL_MESSAGE);
        });

        // when
        LBiObjIntConsumer<Integer,Integer> wrapped = LBiObjIntConsumer.wrap(sutThrowing);

        // then
        try {
            wrapped.doAccept(100,100,100);
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
        LBiObjIntConsumerX<Integer,Integer,ParseException> sutThrowing = LBiObjIntConsumerX.lX((a1,a2,a3) -> {
            throw new ParseException(ORIGINAL_MESSAGE, 0);
        });

        // when
        LBiObjIntConsumer<Integer,Integer> wrapped = LBiObjIntConsumer.wrap(sutThrowing);

        // then
        try {
            wrapped.doAccept(100,100,100);
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
        LBiObjIntConsumer<Integer,Integer> sutThrowing = LBiObjIntConsumer.l((a1,a2,a3) -> {
            throw new UnsupportedOperationException();
        });

        // when
        LBiObjIntConsumer<Integer,Integer> wrapped = sutThrowing.handleBiObjIntCons(handler -> handler
            .wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED));

        // then
        try {
            wrapped.doAccept(100,100,100);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IllegalArgumentException.class)
                    .hasCauseExactlyInstanceOf(UnsupportedOperationException.class)
                    .hasMessage(EXCEPTION_WAS_WRAPPED);
        }
    }

    @Test
    public void testHandleBiObjIntConsMethodDoNotWrapsOtherExceptionIf() throws X {

        // given
        LBiObjIntConsumer<Integer,Integer> sutThrowing = LBiObjIntConsumer.l((a1,a2,a3) -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LBiObjIntConsumer<Integer,Integer> wrapped = sutThrowing.handleBiObjIntCons(handler -> handler
                .wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED)
                .throwIf(IndexOutOfBoundsException.class));

        // then
        try {
            wrapped.doAccept(100,100,100);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                    .hasNoCause();
        }
    }

@Test
    public void testHandleBiObjIntConsMethodDoNotWrapsOtherExceptionWhen() throws X {

        // given
        LBiObjIntConsumer<Integer,Integer> sutThrowing = LBiObjIntConsumer.l((a1,a2,a3) -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LBiObjIntConsumer<Integer,Integer> wrapped = sutThrowing.handleBiObjIntCons(handler -> handler
                .wrapWhen(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED)
                .throwIf(IndexOutOfBoundsException.class));

        // then
        try {
            wrapped.doAccept(100,100,100);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                    .hasNoCause();
        }
    }


    @Test
    public void testHandleBiObjIntConsMishandlingExceptionIsAllowed() throws X {

        // given
        LBiObjIntConsumer<Integer,Integer> sutThrowing = LBiObjIntConsumer.l((a1,a2,a3) -> {
            throw new UnsupportedOperationException(ORIGINAL_MESSAGE);
        });

        // when
        LBiObjIntConsumer<Integer,Integer> wrapped = sutThrowing.handleBiObjIntCons(h -> Function4U.doNothing());

        // then
        try {
            wrapped.doAccept(100,100,100);
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
    public void testBiObjIntConsComposeInt() throws X {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LBiObjIntConsumer<Integer,Integer> sutO = (Integer a1,Integer a2,int a3) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(90);
                assertThat(a2).isEqualTo(91);
                assertThat(a3).isEqualTo(92);
        };

        LFunction<Integer,Integer> before1 = p0 -> {
            assertThat(p0).isEqualTo(80);
            beforeCalls.incrementAndGet();
            return 90;
        };
        LFunction<Integer,Integer> before2 = p1 -> {
            assertThat(p1).isEqualTo(81);
            beforeCalls.incrementAndGet();
            return 91;
        };
        LIntUnaryOperator before3 = p2 -> {
            assertThat(p2).isEqualTo(82);
            beforeCalls.incrementAndGet();
            return 92;
        };

        //when
        LBiObjIntConsumer<Integer,Integer> function = sutO.biObjIntConsComposeInt(before1,before2,before3);
        function.doAccept(80,81,82);

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(beforeCalls.get()).isEqualTo(3);
    }


    @Test
    public void testBiObjIntConsCompose() throws X {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LBiObjIntConsumer<Integer,Integer> sutO = (Integer a1,Integer a2,int a3) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(90);
                assertThat(a2).isEqualTo(91);
                assertThat(a3).isEqualTo(92);
        };

        LFunction<Integer,Integer> before1 = p0 -> {
            assertThat(p0).isEqualTo(80);
            beforeCalls.incrementAndGet();
            return 90;
        };
        LFunction<Integer,Integer> before2 = p1 -> {
            assertThat(p1).isEqualTo(81);
            beforeCalls.incrementAndGet();
            return 91;
        };
        LToIntFunction<Integer> before3 = p2 -> {
            assertThat(p2).isEqualTo(82);
            beforeCalls.incrementAndGet();
            return 92;
        };

        //when
        LTriConsumer<Integer,Integer,Integer> function = sutO.biObjIntConsCompose(before1,before2,before3);
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
        LBiObjIntConsumer<Integer,Integer> sutO = (a1,a2,a3) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(80);
                assertThat(a2).isEqualTo(81);
                assertThat(a3).isEqualTo(82);
        };

        LBiObjIntConsumer<Integer,Integer> thenFunction = (a1,a2,a3) -> {
                thenFunctionCalled.set(true);
                assertThat(a1).isEqualTo(80);
                assertThat(a2).isEqualTo(81);
                assertThat(a3).isEqualTo(82);
        };

        //when
        LBiObjIntConsumer<Integer,Integer> function = sutO.andThen(thenFunction);
        function.doAccept(80,81,82);

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);
    }


    @Test
    public void testNesting() {
        assertThat(sut.nestingBiObjIntCons())
            .isSameAs(sut)
            .isInstanceOf(LBiObjIntConsumer.class);
    }

    @Test
    public void testShoving() {
        assertThat(sut.shovingBiObjIntCons())
            .isSameAs(sut)
            .isInstanceOf(LBiObjIntConsumer.class);
    }

    @Test
    public void testNestingX() {
        assertThat(sut.nestingBiObjIntConsX())
            .isSameAs(sut)
            .isInstanceOf(LBiObjIntConsumerX.class);
    }

    @Test
    public void testShovingX() {
        assertThat(sut.shovingBiObjIntConsX())
            .isSameAs(sut)
            .isInstanceOf(LBiObjIntConsumerX.class);
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void testShove() {

        // given
        LBiObjIntConsumer<Integer,Integer> sutThrowing = LBiObjIntConsumer.l((a1,a2,a3) -> {
            throw new UnsupportedOperationException();
        });

        // when
        sutThrowing.shovingBiObjIntCons().doAccept(100,100,100);
    }

    @Test
    public void testHandleBiObjIntCons() throws X {

        // given
        LBiObjIntConsumer<Integer,Integer> sutThrowing = LBiObjIntConsumer.l((a1,a2,a3) -> {
            throw new UnsupportedOperationException();
        });

        // when
        LBiObjIntConsumer<Integer,Integer> wrapped = sutThrowing.handleBiObjIntCons(h -> {
            h.wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED);
        });

        // then
        try {
            wrapped.doAccept(100,100,100);
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
                .contains("LBiObjIntConsumer: void doAccept(T1 a1,T2 a2,int a3)");
    }


    @Test
    public void isThrowing() {
        assertThat(sut.isThrowing())
            .isFalse();
    }

    //<editor-fold desc="Variants">

    private void variantV1(Integer a1,int a3,Integer a2) {
    }

    @Test
    public void compilerSubstituteVariantV1() {
        LBiObjIntConsumer lambda = LBiObjIntConsumer./*<T1,T2>*/l1(this::variantV1);

        assertThat(lambda).isInstanceOf(LBiObjIntConsumer.V1.class);
    }


    private void variantV2(Integer a2,Integer a1,int a3) {
    }

    @Test
    public void compilerSubstituteVariantV2() {
        LBiObjIntConsumer lambda = LBiObjIntConsumer./*<T1,T2>*/l2(this::variantV2);

        assertThat(lambda).isInstanceOf(LBiObjIntConsumer.V2.class);
    }


    private void variantV3(Integer a2,int a3,Integer a1) {
    }

    @Test
    public void compilerSubstituteVariantV3() {
        LBiObjIntConsumer lambda = LBiObjIntConsumer./*<T1,T2>*/l3(this::variantV3);

        assertThat(lambda).isInstanceOf(LBiObjIntConsumer.V3.class);
    }


    private void variantV4(int a3,Integer a1,Integer a2) {
    }

    @Test
    public void compilerSubstituteVariantV4() {
        LBiObjIntConsumer lambda = LBiObjIntConsumer./*<T1,T2>*/l4(this::variantV4);

        assertThat(lambda).isInstanceOf(LBiObjIntConsumer.V4.class);
    }


    private void variantV5(int a3,Integer a2,Integer a1) {
    }

    @Test
    public void compilerSubstituteVariantV5() {
        LBiObjIntConsumer lambda = LBiObjIntConsumer./*<T1,T2>*/l5(this::variantV5);

        assertThat(lambda).isInstanceOf(LBiObjIntConsumer.V5.class);
    }

    //</editor-fold>


    @Test void safeCompiles() {
        LBiObjIntConsumer r1 = LBiObjIntConsumer.safe(sut); //NOSONAR
        LBiObjIntConsumerX r2 = LBiObjIntConsumer.safe(sut); //NOSONAR
    }

    @Test void safePropagates() {
        Object result = LBiObjIntConsumer.safe(sut);
        assertThat(result).isSameAs(sut);
    }

    @Test void safeProtectsAgainstNpe() {
        Object result = LBiObjIntConsumer.safe(null);
        assertThat(result).isSameAs(LBiObjIntConsumer.l(LBiObjIntConsumer.safe()));
    }

    @Test  void safeSupplierPropagates() {
        LSupplier<LBiObjIntConsumer<Integer,Integer>> supplier = ()->sut;
        Object result = LBiObjIntConsumer.safeSupplier(supplier);
        assertThat(result).isSameAs(supplier);
    }

    @Test  void safeSupplierProtectsAgainstNpe() {
        Object result = LBiObjIntConsumer.safeSupplier(null);
        assertThat(result).isSameAs(LBiObjIntConsumer.safeSupplier());
    }

    @Test  void safeSupplierCompiles() {
        LSupplier<LBiObjIntConsumer<Integer,Integer>> r1 = LBiObjIntConsumer.safeSupplier(()->sut);  //NOSONAR
    }

}
