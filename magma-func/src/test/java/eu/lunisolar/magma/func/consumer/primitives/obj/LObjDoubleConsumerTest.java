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
public class LObjDoubleConsumerTest<T,X extends ParseException> {
    private static final String ORIGINAL_MESSAGE = "Original message";
    private static final String EXCEPTION_WAS_WRAPPED = "Exception was wrapped.";
    private static final String NO_EXCEPTION_WERE_THROWN = "No exception were thrown.";



    private LObjDoubleConsumer<Integer> sut = new LObjDoubleConsumer<Integer>(){
        public  void doAccept(Integer a1,double a2)  {
            Function4U.doNothing();
        }
    };

    private LObjDoubleConsumerX<Integer,X> opposite = new LObjDoubleConsumerX<Integer,X>(){
        public  void doAccept(Integer a1,double a2)  throws X {
            Function4U.doNothing();
        }
    };


    private ObjDoubleConsumer<Integer> jre = (a1,a2) -> Function4U.doNothing();



    private LObjDoubleConsumerX<Integer,RuntimeException> sutAlwaysThrowingUnchecked = LObjDoubleConsumer.l((a1,a2) -> {
            throw new IndexOutOfBoundsException(ORIGINAL_MESSAGE);
    });



    @Test
    public void testTupleCall() throws X {

        LObjDoublePair<Integer> domainObject = Tuple4U.objDoublePair(100,100d);

        Object result = sut.tupleAccept(domainObject);

        assertThat(result)
            .isSameAs(LTuple.Void.INSTANCE);
    }

    @Test
    public void testNestingDoAcceptUnchecked() throws X {

        // then
        try {
            sutAlwaysThrowingUnchecked.nestingDoAccept(100,100d);
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
            sutAlwaysThrowingUnchecked.shovingDoAccept(100,100d);
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
            .isEqualTo("LObjDoubleConsumer: void doAccept(T a1,double a2)");
    }

    @Test
    public void testLMethod() throws X {
        assertThat(LObjDoubleConsumer.l(Function4U::doNothing))
            .isInstanceOf(LObjDoubleConsumer.class);
    }

    @Test
    public void testWrapMethod() throws X {
        assertThat(LObjDoubleConsumer.wrap(opposite))
            .isInstanceOf(LObjDoubleConsumer.class);
    }

    @Test
    public void testWrapStdMethod() throws X {
        assertThat(LObjDoubleConsumer.wrap(jre))
            .isInstanceOf(LObjDoubleConsumer.class);
    }

    @Test
    public void testWrapMethodDoNotWrapsRuntimeException() throws X {
        // given
        LObjDoubleConsumerX<Integer,X> sutThrowing = LObjDoubleConsumerX.lX((a1,a2) -> {
            throw new UnsupportedOperationException(ORIGINAL_MESSAGE);
        });

        // when
        LObjDoubleConsumer<Integer> wrapped = LObjDoubleConsumer.wrap(sutThrowing);

        // then
        try {
            wrapped.doAccept(100,100d);
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
        LObjDoubleConsumerX<Integer,ParseException> sutThrowing = LObjDoubleConsumerX.lX((a1,a2) -> {
            throw new ParseException(ORIGINAL_MESSAGE, 0);
        });

        // when
        LObjDoubleConsumer<Integer> wrapped = LObjDoubleConsumer.wrap(sutThrowing);

        // then
        try {
            wrapped.doAccept(100,100d);
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
        LObjDoubleConsumer<Integer> sutThrowing = LObjDoubleConsumer.l((a1,a2) -> {
            throw new UnsupportedOperationException();
        });

        // when
        LObjDoubleConsumer<Integer> wrapped = sutThrowing.handleObjDoubleCons(handler -> handler
            .wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED));

        // then
        try {
            wrapped.doAccept(100,100d);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IllegalArgumentException.class)
                    .hasCauseExactlyInstanceOf(UnsupportedOperationException.class)
                    .hasMessage(EXCEPTION_WAS_WRAPPED);
        }
    }

    @Test
    public void testHandleObjDoubleConsMethodDoNotWrapsOtherExceptionIf() throws X {

        // given
        LObjDoubleConsumer<Integer> sutThrowing = LObjDoubleConsumer.l((a1,a2) -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LObjDoubleConsumer<Integer> wrapped = sutThrowing.handleObjDoubleCons(handler -> handler
                .wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED)
                .throwIf(IndexOutOfBoundsException.class));

        // then
        try {
            wrapped.doAccept(100,100d);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                    .hasNoCause();
        }
    }

@Test
    public void testHandleObjDoubleConsMethodDoNotWrapsOtherExceptionWhen() throws X {

        // given
        LObjDoubleConsumer<Integer> sutThrowing = LObjDoubleConsumer.l((a1,a2) -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LObjDoubleConsumer<Integer> wrapped = sutThrowing.handleObjDoubleCons(handler -> handler
                .wrapWhen(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED)
                .throwIf(IndexOutOfBoundsException.class));

        // then
        try {
            wrapped.doAccept(100,100d);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                    .hasNoCause();
        }
    }


    @Test
    public void testHandleObjDoubleConsMishandlingExceptionIsAllowed() throws X {

        // given
        LObjDoubleConsumer<Integer> sutThrowing = LObjDoubleConsumer.l((a1,a2) -> {
            throw new UnsupportedOperationException(ORIGINAL_MESSAGE);
        });

        // when
        LObjDoubleConsumer<Integer> wrapped = sutThrowing.handleObjDoubleCons(h -> Function4U.doNothing());

        // then
        try {
            wrapped.doAccept(100,100d);
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
    public void testObjDoubleConsComposeDouble() throws X {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LObjDoubleConsumer<Integer> sutO = (Integer a1,double a2) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(90);
                assertThat(a2).isEqualTo(91d);
        };

        LFunction<Integer,Integer> before1 = p0 -> {
            assertThat(p0).isEqualTo(80);
            beforeCalls.incrementAndGet();
            return 90;
        };
        LDoubleUnaryOperator before2 = p1 -> {
            assertThat(p1).isEqualTo(81d);
            beforeCalls.incrementAndGet();
            return 91d;
        };

        //when
        LObjDoubleConsumer<Integer> function = sutO.objDoubleConsComposeDouble(before1,before2);
        function.doAccept(80,81d);

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(beforeCalls.get()).isEqualTo(2);
    }


    @Test
    public void testObjDoubleConsCompose() throws X {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LObjDoubleConsumer<Integer> sutO = (Integer a1,double a2) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(90);
                assertThat(a2).isEqualTo(91d);
        };

        LFunction<Integer,Integer> before1 = p0 -> {
            assertThat(p0).isEqualTo(80);
            beforeCalls.incrementAndGet();
            return 90;
        };
        LToDoubleFunction<Integer> before2 = p1 -> {
            assertThat(p1).isEqualTo(81);
            beforeCalls.incrementAndGet();
            return 91d;
        };

        //when
        LBiConsumer<Integer,Integer> function = sutO.objDoubleConsCompose(before1,before2);
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
        LObjDoubleConsumer<Integer> sutO = (a1,a2) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(80);
                assertThat(a2).isEqualTo(81d);
        };

        LObjDoubleConsumer<Integer> thenFunction = (a1,a2) -> {
                thenFunctionCalled.set(true);
                assertThat(a1).isEqualTo(80);
                assertThat(a2).isEqualTo(81d);
        };

        //when
        LObjDoubleConsumer<Integer> function = sutO.andThen(thenFunction);
        function.doAccept(80,81d);

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);
    }


    @Test
    public void testNesting() {
        assertThat(sut.nestingObjDoubleCons())
            .isSameAs(sut)
            .isInstanceOf(LObjDoubleConsumer.class);
    }

    @Test
    public void testShoving() {
        assertThat(sut.shovingObjDoubleCons())
            .isSameAs(sut)
            .isInstanceOf(LObjDoubleConsumer.class);
    }

    @Test
    public void testNestingX() {
        assertThat(sut.nestingObjDoubleConsX())
            .isSameAs(sut)
            .isInstanceOf(LObjDoubleConsumerX.class);
    }

    @Test
    public void testShovingX() {
        assertThat(sut.shovingObjDoubleConsX())
            .isSameAs(sut)
            .isInstanceOf(LObjDoubleConsumerX.class);
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void testShove() {

        // given
        LObjDoubleConsumer<Integer> sutThrowing = LObjDoubleConsumer.l((a1,a2) -> {
            throw new UnsupportedOperationException();
        });

        // when
        sutThrowing.shovingObjDoubleCons().doAccept(100,100d);
    }

    @Test
    public void testHandleObjDoubleCons() throws X {

        // given
        LObjDoubleConsumer<Integer> sutThrowing = LObjDoubleConsumer.l((a1,a2) -> {
            throw new UnsupportedOperationException();
        });

        // when
        LObjDoubleConsumer<Integer> wrapped = sutThrowing.handleObjDoubleCons(h -> {
            h.wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED);
        });

        // then
        try {
            wrapped.doAccept(100,100d);
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
                .contains("LObjDoubleConsumer: void doAccept(T a1,double a2)");
    }


    @Test
    public void isThrowing() {
        assertThat(sut.isThrowing())
            .isFalse();
    }

    //<editor-fold desc="Variants">

    private void variantV1(double a2,Integer a1) {
    }

    @Test
    public void compilerSubstituteVariantV1() {
        LObjDoubleConsumer lambda = LObjDoubleConsumer./*<T>*/l1(this::variantV1);

        assertThat(lambda).isInstanceOf(LObjDoubleConsumer.V1.class);
    }

    //</editor-fold>


    @Test void safeCompiles() {
        LObjDoubleConsumer r1 = LObjDoubleConsumer.safe(sut); //NOSONAR
        LObjDoubleConsumerX r2 = LObjDoubleConsumer.safe(sut); //NOSONAR
        ObjDoubleConsumer r3 = LObjDoubleConsumer.safe(sut); //NOSONAR
    }

    @Test void safePropagates() {
        Object result = LObjDoubleConsumer.safe(sut);
        assertThat(result).isSameAs(sut);
    }

    @Test void safeProtectsAgainstNpe() {
        Object result = LObjDoubleConsumer.safe(null);
        assertThat(result).isSameAs(LObjDoubleConsumer.l(LObjDoubleConsumer.safe()));
    }

    @Test  void safeSupplierPropagates() {
        LSupplier<LObjDoubleConsumer<Integer>> supplier = ()->sut;
        Object result = LObjDoubleConsumer.safeSupplier(supplier);
        assertThat(result).isSameAs(supplier);
    }

    @Test  void safeSupplierProtectsAgainstNpe() {
        Object result = LObjDoubleConsumer.safeSupplier(null);
        assertThat(result).isSameAs(LObjDoubleConsumer.safeSupplier());
    }

    @Test  void safeSupplierCompiles() {
        LSupplier<LObjDoubleConsumer<Integer>> r1 = LObjDoubleConsumer.safeSupplier(()->sut);  //NOSONAR
        Supplier<LObjDoubleConsumer<Integer>> r2 = LObjDoubleConsumer.safeSupplier(()->sut); //NOSONAR
    }

}
