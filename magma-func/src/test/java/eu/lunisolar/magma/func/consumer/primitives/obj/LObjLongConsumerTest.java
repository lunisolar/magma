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
public class LObjLongConsumerTest<T,X extends ParseException> {
    private static final String ORIGINAL_MESSAGE = "Original message";
    private static final String EXCEPTION_WAS_WRAPPED = "Exception was wrapped.";
    private static final String NO_EXCEPTION_WERE_THROWN = "No exception were thrown.";



    private LObjLongConsumer<T> sut = new LObjLongConsumer(){
        public  void doAccept(Object a1,long a2)  {
            Function4U.doNothing();
        }
    };

    private LObjLongConsumerX<T,X> opposite = new LObjLongConsumerX(){
        public  void doAccept(Object a1,long a2) throws ParseException {
            Function4U.doNothing();
        }
    };


    private ObjLongConsumer jre = (Object a1,long a2) -> Function4U.doNothing();



    private LObjLongConsumer<T> sutAlwaysThrowingUnckeck = LObjLongConsumer.l((T a1,long a2) -> {
            throw new IndexOutOfBoundsException(ORIGINAL_MESSAGE);
    });



    @Test
    public void testTupleCall() throws X {

        LObjLongPair<T> domainObject = Tuple4U.tuple((T)Integer.valueOf(100),(long)100);

        Object result = sut.tupleAccept(domainObject);

        assertThat(result)
            .isSameAs(LTuple.Void.INSTANCE);
    }

    @Test
    public void testNestingDoAcceptUnckeck() throws X {

        // then
        try {
            sutAlwaysThrowingUnckeck.nestingDoAccept((T)Integer.valueOf(100),(long)100);
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
            sutAlwaysThrowingUnckeck.shovingDoAccept((T)Integer.valueOf(100),(long)100);
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
            .isEqualTo("LObjLongConsumer: void doAccept(T a1,long a2)");
    }

    @Test
    public void testLMethod() throws X {
        assertThat(LObjLongConsumer.l((Object a1,long a2) -> Function4U.doNothing() ))
            .isInstanceOf(LObjLongConsumer.class);
    }

    @Test
    public void testWrapMethod() throws X {
        assertThat(LObjLongConsumer.wrap(opposite))
            .isInstanceOf(LObjLongConsumer.class);
    }

    @Test
    public void testWrapStdMethod() throws X {
        assertThat(LObjLongConsumer.wrap(jre))
            .isInstanceOf(LObjLongConsumer.class);
    }

    @Test
    public void testWrapMethodDoNotWrapsRuntimeException() throws X {
        // given
        LObjLongConsumerX<T,X> sutThrowing = LObjLongConsumerX.lX((T a1,long a2) -> {
            throw new UnsupportedOperationException(ORIGINAL_MESSAGE);
        });

        // when
        LObjLongConsumer<T> wrapped = LObjLongConsumer.wrap(sutThrowing);

        // then
        try {
            wrapped.doAccept((T)Integer.valueOf(100),(long)100);
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
        LObjLongConsumerX<T,ParseException> sutThrowing = LObjLongConsumerX.lX((T a1,long a2) -> {
            throw new ParseException(ORIGINAL_MESSAGE, 0);
        });

        // when
        LObjLongConsumer<T> wrapped = LObjLongConsumer.wrap(sutThrowing);

        // then
        try {
            wrapped.doAccept((T)Integer.valueOf(100),(long)100);
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
        LObjLongConsumer<T> sutThrowing = LObjLongConsumer.l((T a1,long a2) -> {
            throw new UnsupportedOperationException();
        });

        // when
        LObjLongConsumer<T> wrapped = sutThrowing.handleObjLongCons(handler -> handler
            .wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED));

        // then
        try {
            wrapped.doAccept((T)Integer.valueOf(100),(long)100);
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
        LObjLongConsumer<T> sutThrowing = LObjLongConsumer.l((T a1,long a2) -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LObjLongConsumer<T> wrapped = sutThrowing.handleObjLongCons(handler -> handler
                .wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED)
                .throwIf(IndexOutOfBoundsException.class));

        // then
        try {
            wrapped.doAccept((T)Integer.valueOf(100),(long)100);
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
        LObjLongConsumer<T> sutThrowing = LObjLongConsumer.l((T a1,long a2) -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LObjLongConsumer<T> wrapped = sutThrowing.handleObjLongCons(handler -> handler
                .wrapWhen(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED)
                .throwIf(IndexOutOfBoundsException.class));

        // then
        try {
            wrapped.doAccept((T)Integer.valueOf(100),(long)100);
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
        LObjLongConsumer<T> sutThrowing = LObjLongConsumer.l((T a1,long a2) -> {
            throw new UnsupportedOperationException(ORIGINAL_MESSAGE);
        });

        // when
        LObjLongConsumer<T> wrapped = sutThrowing.handleObjLongCons(h -> Function4U.doNothing());

        // then
        try {
            wrapped.doAccept((T)Integer.valueOf(100),(long)100);
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
    public void testobjLongConsComposeLong() throws X {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LObjLongConsumer<Integer > sutO = (Integer a1,long a2) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo((T)Integer.valueOf(90));
                assertThat(a2).isEqualTo((long)91);
        };

        LFunction<Integer ,Integer > before1 = p0 -> {
            assertThat(p0).isEqualTo((T)Integer.valueOf(80));
            beforeCalls.incrementAndGet();
            return 90;
        };
        LLongUnaryOperator before2 = p1 -> {
            assertThat(p1).isEqualTo((long)81);
            beforeCalls.incrementAndGet();
            return (long)91;
        };

        //when
        LObjLongConsumer<Integer > function = sutO.objLongConsComposeLong(before1,before2);
        function.doAccept((Integer )Integer.valueOf(80),(long)81);

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(beforeCalls.get()).isEqualTo(2);
    }


    @Test
    public void testobjLongConsCompose() throws X {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LObjLongConsumer<Integer > sutO = (Integer a1,long a2) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo((T)Integer.valueOf(90));
                assertThat(a2).isEqualTo((long)91);
        };

        LFunction<Integer ,Integer > before1 = p0 -> {
            assertThat(p0).isEqualTo(Integer.valueOf(80));
            beforeCalls.incrementAndGet();
            return 90;
        };
        LToLongFunction<Integer > before2 = p1 -> {
            assertThat(p1).isEqualTo(Integer.valueOf(81));
            beforeCalls.incrementAndGet();
            return (long)91;
        };

        //when
        LBiConsumer<Integer ,Integer > function = sutO.objLongConsCompose(before1,before2);
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
        LObjLongConsumer<Integer > sutO = (Integer a1,long a2) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo((T)Integer.valueOf(80));
                assertThat(a2).isEqualTo((long)81);
        };

        LObjLongConsumer<Integer > thenFunction = (Integer a1,long a2) -> {
                thenFunctionCalled.set(true);
                assertThat(a1).isEqualTo((T)Integer.valueOf(80));
                assertThat(a2).isEqualTo((long)81);
        };

        //when
        LObjLongConsumer<Integer > function = sutO.andThen(thenFunction);
        function.doAccept((Integer )Integer.valueOf(80),(long)81);

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);
    }


    @Test
    public void testNesting() {
        assertThat(sut.nestingObjLongCons())
            .isSameAs(sut)
            .isInstanceOf(LObjLongConsumer.class);
    }

    @Test
    public void testShoving() {
        assertThat(sut.shovingObjLongCons())
            .isSameAs(sut)
            .isInstanceOf(LObjLongConsumer.class);
    }

    @Test
    public void testNestingX() {
        assertThat(sut.nestingObjLongConsX())
            .isSameAs(sut)
            .isInstanceOf(LObjLongConsumerX.class);
    }

    @Test
    public void testShovingX() {
        assertThat(sut.shovingObjLongConsX())
            .isSameAs(sut)
            .isInstanceOf(LObjLongConsumerX.class);
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void testShove() {

        // given
        LObjLongConsumer<T> sutThrowing = LObjLongConsumer.l((T a1,long a2) -> {
            throw new UnsupportedOperationException();
        });

        // when
        sutThrowing.shovingObjLongCons().doAccept((T)Integer.valueOf(100),(long)100);
    }

    @Test
    public void testHandleObjLongCons() throws X {

        // given
        LObjLongConsumer<T> sutThrowing = LObjLongConsumer.l((T a1,long a2) -> {
            throw new UnsupportedOperationException();
        });

        // when
        LObjLongConsumer<T> wrapped = sutThrowing.handleObjLongCons(h -> {
            h.wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED);
        });

        // then
        try {
            wrapped.doAccept((T)Integer.valueOf(100),(long)100);
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
                .contains("LObjLongConsumer: void doAccept(T a1,long a2)");
    }


    @Test
    public void isThrowing() {
        assertThat(sut.isThrowing())
            .isFalse();
    }

    //<editor-fold desc="Variants">

    private void variant1(long a2,T a1) {
    }

    @Test
    public void compilerSubstituteVariant1() {
        LObjLongConsumer lambda = LObjLongConsumer./*<T>*/l1(this::variant1);

        assertThat(lambda).isInstanceOf(LObjLongConsumer.V1.class);
    }

    //</editor-fold>


    @Test void safeCompiles() {
        LObjLongConsumer r1 = LObjLongConsumer.safe(sut); //NOSONAR
        LObjLongConsumerX r2 = LObjLongConsumer.safe(sut); //NOSONAR
        ObjLongConsumer r3 = LObjLongConsumer.safe(sut); //NOSONAR
    }

    @Test void safePropagates() {
        Object result = LObjLongConsumer.safe(sut);
        assertThat(result).isSameAs(sut);
    }

    @Test void safeProtectsAgainstNpe() {
        Object result = LObjLongConsumer.safe(null);
        assertThat(result).isSameAs(LObjLongConsumer.l(LObjLongConsumer.safe()));
    }

    @Test  void safeSupplierPropagates() {
        LSupplier<LObjLongConsumer<T>> supplier = ()->sut;
        Object result = LObjLongConsumer.safeSupplier(supplier);
        assertThat(result).isSameAs(supplier);
    }

    @Test  void safeSupplierProtectsAgainstNpe() {
        Object result = LObjLongConsumer.safeSupplier(null);
        assertThat(result).isSameAs(LObjLongConsumer.safeSupplier());
    }

    @Test  void safeSupplierCompiles() {
        LSupplier<LObjLongConsumer<T>> r1 = LObjLongConsumer.safeSupplier(()->sut);  //NOSONAR
        Supplier<LObjLongConsumer<T>> r2 = LObjLongConsumer.safeSupplier(()->sut); //NOSONAR
    }

}
