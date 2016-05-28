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
public class LObjShortConsumerTest<T,X extends ParseException> {
    private static final String ORIGINAL_MESSAGE = "Original message";
    private static final String EXCEPTION_WAS_WRAPPED = "Exception was wrapped.";
    private static final String NO_EXCEPTION_WERE_THROWN = "No exception were thrown.";



    private LObjShortConsumer<Integer> sut = new LObjShortConsumer<Integer>(){
        public  void doAccept(Integer a1,short a2)  {
            Function4U.doNothing();
        }
    };

    private LObjShortConsumerX<Integer,X> opposite = new LObjShortConsumerX<Integer,X>(){
        public  void doAccept(Integer a1,short a2)  throws X {
            Function4U.doNothing();
        }
    };




    private LObjShortConsumerX<Integer,RuntimeException> sutAlwaysThrowingUnchecked = LObjShortConsumer.l((a1,a2) -> {
            throw new IndexOutOfBoundsException(ORIGINAL_MESSAGE);
    });



    @Test
    public void testTupleCall() throws X {

        LObjShortPair<Integer> domainObject = Tuple4U.objShortPair(100,(short)100);

        Object result = sut.tupleAccept(domainObject);

        assertThat(result)
            .isSameAs(LTuple.Void.INSTANCE);
    }

    @Test
    public void testNestingDoAcceptUnchecked() throws X {

        // then
        try {
            sutAlwaysThrowingUnchecked.nestingDoAccept(100,(short)100);
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
            sutAlwaysThrowingUnchecked.shovingDoAccept(100,(short)100);
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
            .isEqualTo("LObjShortConsumer: void doAccept(T a1,short a2)");
    }

    @Test
    public void testLMethod() throws X {
        assertThat(LObjShortConsumer.l(Function4U::doNothing))
            .isInstanceOf(LObjShortConsumer.class);
    }

    @Test
    public void testWrapMethod() throws X {
        assertThat(LObjShortConsumer.wrap(opposite))
            .isInstanceOf(LObjShortConsumer.class);
    }

    @Test
    public void testWrapMethodDoNotWrapsRuntimeException() throws X {
        // given
        LObjShortConsumerX<Integer,X> sutThrowing = LObjShortConsumerX.lX((a1,a2) -> {
            throw new UnsupportedOperationException(ORIGINAL_MESSAGE);
        });

        // when
        LObjShortConsumer<Integer> wrapped = LObjShortConsumer.wrap(sutThrowing);

        // then
        try {
            wrapped.doAccept(100,(short)100);
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
        LObjShortConsumerX<Integer,ParseException> sutThrowing = LObjShortConsumerX.lX((a1,a2) -> {
            throw new ParseException(ORIGINAL_MESSAGE, 0);
        });

        // when
        LObjShortConsumer<Integer> wrapped = LObjShortConsumer.wrap(sutThrowing);

        // then
        try {
            wrapped.doAccept(100,(short)100);
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
        LObjShortConsumer<Integer> sutThrowing = LObjShortConsumer.l((a1,a2) -> {
            throw new UnsupportedOperationException();
        });

        // when
        LObjShortConsumer<Integer> wrapped = sutThrowing.handleObjShortCons(handler -> handler
            .wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED));

        // then
        try {
            wrapped.doAccept(100,(short)100);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IllegalArgumentException.class)
                    .hasCauseExactlyInstanceOf(UnsupportedOperationException.class)
                    .hasMessage(EXCEPTION_WAS_WRAPPED);
        }
    }

    @Test
    public void testHandleObjShortConsMethodDoNotWrapsOtherExceptionIf() throws X {

        // given
        LObjShortConsumer<Integer> sutThrowing = LObjShortConsumer.l((a1,a2) -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LObjShortConsumer<Integer> wrapped = sutThrowing.handleObjShortCons(handler -> handler
                .wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED)
                .throwIf(IndexOutOfBoundsException.class));

        // then
        try {
            wrapped.doAccept(100,(short)100);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                    .hasNoCause();
        }
    }

@Test
    public void testHandleObjShortConsMethodDoNotWrapsOtherExceptionWhen() throws X {

        // given
        LObjShortConsumer<Integer> sutThrowing = LObjShortConsumer.l((a1,a2) -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LObjShortConsumer<Integer> wrapped = sutThrowing.handleObjShortCons(handler -> handler
                .wrapWhen(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED)
                .throwIf(IndexOutOfBoundsException.class));

        // then
        try {
            wrapped.doAccept(100,(short)100);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                    .hasNoCause();
        }
    }


    @Test
    public void testHandleObjShortConsMishandlingExceptionIsAllowed() throws X {

        // given
        LObjShortConsumer<Integer> sutThrowing = LObjShortConsumer.l((a1,a2) -> {
            throw new UnsupportedOperationException(ORIGINAL_MESSAGE);
        });

        // when
        LObjShortConsumer<Integer> wrapped = sutThrowing.handleObjShortCons(h -> Function4U.doNothing());

        // then
        try {
            wrapped.doAccept(100,(short)100);
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
    public void testObjShortConsComposeShort() throws X {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LObjShortConsumer<Integer> sutO = (a1,a2) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(90);
                assertThat(a2).isEqualTo((short)91);
        };

        LFunction<Integer,Integer> before1 = p0 -> {
            assertThat(p0).isEqualTo(80);
            beforeCalls.incrementAndGet();
            return 90;
        };
        LShortUnaryOperator before2 = p1 -> {
            assertThat(p1).isEqualTo((short)81);
            beforeCalls.incrementAndGet();
            return (short)91;
        };

        //when
        LObjShortConsumer<Integer> function = sutO.objShortConsComposeShort(before1,before2);
        function.doAccept(80,(short)81);

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(beforeCalls.get()).isEqualTo(2);
    }


    @Test
    public void testObjShortConsCompose() throws X {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LObjShortConsumer<Integer> sutO = (a1,a2) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(90);
                assertThat(a2).isEqualTo((short)91);
        };

        LFunction<Integer,Integer> before1 = p0 -> {
            assertThat(p0).isEqualTo(80);
            beforeCalls.incrementAndGet();
            return 90;
        };
        LToShortFunction<Integer> before2 = p1 -> {
            assertThat(p1).isEqualTo(81);
            beforeCalls.incrementAndGet();
            return (short)91;
        };

        //when
        LBiConsumer<Integer,Integer> function = sutO.objShortConsCompose(before1,before2);
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
        LObjShortConsumer<Integer> sutO = (a1,a2) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(80);
                assertThat(a2).isEqualTo((short)81);
        };

        LObjShortConsumer<Integer> thenFunction = (a1,a2) -> {
                thenFunctionCalled.set(true);
                assertThat(a1).isEqualTo(80);
                assertThat(a2).isEqualTo((short)81);
        };

        //when
        LObjShortConsumer<Integer> function = sutO.andThen(thenFunction);
        function.doAccept(80,(short)81);

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);
    }


    @Test
    public void testNesting() {
        assertThat(sut.nestingObjShortCons())
            .isSameAs(sut)
            .isInstanceOf(LObjShortConsumer.class);
    }

    @Test
    public void testShoving() {
        assertThat(sut.shovingObjShortCons())
            .isSameAs(sut)
            .isInstanceOf(LObjShortConsumer.class);
    }

    @Test
    public void testNestingX() {
        assertThat(sut.nestingObjShortConsX())
            .isSameAs(sut)
            .isInstanceOf(LObjShortConsumerX.class);
    }

    @Test
    public void testShovingX() {
        assertThat(sut.shovingObjShortConsX())
            .isSameAs(sut)
            .isInstanceOf(LObjShortConsumerX.class);
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void testShove() {

        // given
        LObjShortConsumer<Integer> sutThrowing = LObjShortConsumer.l((a1,a2) -> {
            throw new UnsupportedOperationException();
        });

        // when
        sutThrowing.shovingObjShortCons().doAccept(100,(short)100);
    }

    @Test
    public void testHandleObjShortCons() throws X {

        // given
        LObjShortConsumer<Integer> sutThrowing = LObjShortConsumer.l((a1,a2) -> {
            throw new UnsupportedOperationException();
        });

        // when
        LObjShortConsumer<Integer> wrapped = sutThrowing.handleObjShortCons(h -> {
            h.wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED);
        });

        // then
        try {
            wrapped.doAccept(100,(short)100);
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
                .contains("LObjShortConsumer: void doAccept(T a1,short a2)");
    }


    @Test
    public void isThrowing() {
        assertThat(sut.isThrowing())
            .isFalse();
    }

    //<editor-fold desc="Variants">

    private void variantV1(short a2,Integer a1) {
    }

    @Test
    public void compilerSubstituteVariantV1() {
        LObjShortConsumer lambda = LObjShortConsumer./*<T>*/l1(this::variantV1);

        assertThat(lambda).isInstanceOf(LObjShortConsumer.V1.class);
    }

    //</editor-fold>


    @Test void safeCompiles() {
        LObjShortConsumer r1 = LObjShortConsumer.safe(sut); //NOSONAR
        LObjShortConsumerX r2 = LObjShortConsumer.safe(sut); //NOSONAR
    }

    @Test void safePropagates() {
        Object result = LObjShortConsumer.safe(sut);
        assertThat(result).isSameAs(sut);
    }

    @Test void safeProtectsAgainstNpe() {
        Object result = LObjShortConsumer.safe(null);
        assertThat(result).isSameAs(LObjShortConsumer.l(LObjShortConsumer.safe()));
    }

    @Test  void safeSupplierPropagates() {
        LSupplier<LObjShortConsumer<Integer>> supplier = ()->sut;
        Object result = LObjShortConsumer.safeSupplier(supplier);
        assertThat(result).isSameAs(supplier);
    }

    @Test  void safeSupplierProtectsAgainstNpe() {
        Object result = LObjShortConsumer.safeSupplier(null);
        assertThat(result).isSameAs(LObjShortConsumer.safeSupplier());
    }

    @Test  void safeSupplierCompiles() {
        LSupplier<LObjShortConsumer<Integer>> r1 = LObjShortConsumer.safeSupplier(()->sut);  //NOSONAR
    }

}
