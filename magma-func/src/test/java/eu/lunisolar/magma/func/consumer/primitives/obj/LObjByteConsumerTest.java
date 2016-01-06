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
public class LObjByteConsumerTest<T,X extends ParseException> {
    private static final String ORIGINAL_MESSAGE = "Original message";
    private static final String EXCEPTION_WAS_WRAPPED = "Exception was wrapped.";
    private static final String NO_EXCEPTION_WERE_THROWN = "No exception were thrown.";



    private LObjByteConsumer<T> sut = new LObjByteConsumer(){
        public  void doAccept(Object a1,byte a2)  {
            Function4U.doNothing();
        }
    };

    private LObjByteConsumerX<T,X> opposite = new LObjByteConsumerX(){
        public  void doAccept(Object a1,byte a2) throws ParseException {
            Function4U.doNothing();
        }
    };




    private LObjByteConsumer<T> sutAlwaysThrowingUnckeck = LObjByteConsumer.l((T a1,byte a2) -> {
            throw new IndexOutOfBoundsException(ORIGINAL_MESSAGE);
    });



    @Test
    public void testTupleCall() throws X {

        LObjBytePair<T> domainObject = Tuple4U.tuple((T)Integer.valueOf(100),(byte)100);

        Object result = sut.tupleAccept(domainObject);

        assertThat(result)
            .isSameAs(LTuple.Void.INSTANCE);
    }

    @Test
    public void testNestingDoAcceptUnckeck() throws X {

        // then
        try {
            sutAlwaysThrowingUnckeck.nestingDoAccept((T)Integer.valueOf(100),(byte)100);
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
            sutAlwaysThrowingUnckeck.shovingDoAccept((T)Integer.valueOf(100),(byte)100);
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
            .isEqualTo("LObjByteConsumer: void doAccept(T a1,byte a2)");
    }

    @Test
    public void testLMethod() throws X {
        assertThat(LObjByteConsumer.l((Object a1,byte a2) -> Function4U.doNothing() ))
            .isInstanceOf(LObjByteConsumer.class);
    }

    @Test
    public void testWrapMethod() throws X {
        assertThat(LObjByteConsumer.wrap(opposite))
            .isInstanceOf(LObjByteConsumer.class);
    }

    @Test
    public void testWrapMethodDoNotWrapsRuntimeException() throws X {
        // given
        LObjByteConsumerX<T,X> sutThrowing = LObjByteConsumerX.lX((T a1,byte a2) -> {
            throw new UnsupportedOperationException(ORIGINAL_MESSAGE);
        });

        // when
        LObjByteConsumer<T> wrapped = LObjByteConsumer.wrap(sutThrowing);

        // then
        try {
            wrapped.doAccept((T)Integer.valueOf(100),(byte)100);
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
        LObjByteConsumerX<T,ParseException> sutThrowing = LObjByteConsumerX.lX((T a1,byte a2) -> {
            throw new ParseException(ORIGINAL_MESSAGE, 0);
        });

        // when
        LObjByteConsumer<T> wrapped = LObjByteConsumer.wrap(sutThrowing);

        // then
        try {
            wrapped.doAccept((T)Integer.valueOf(100),(byte)100);
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
        LObjByteConsumer<T> sutThrowing = LObjByteConsumer.l((T a1,byte a2) -> {
            throw new UnsupportedOperationException();
        });

        // when
        LObjByteConsumer<T> wrapped = sutThrowing.handleObjByteCons(handler -> handler
            .wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED));

        // then
        try {
            wrapped.doAccept((T)Integer.valueOf(100),(byte)100);
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
        LObjByteConsumer<T> sutThrowing = LObjByteConsumer.l((T a1,byte a2) -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LObjByteConsumer<T> wrapped = sutThrowing.handleObjByteCons(handler -> handler
                .wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED)
                .throwIf(IndexOutOfBoundsException.class));

        // then
        try {
            wrapped.doAccept((T)Integer.valueOf(100),(byte)100);
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
        LObjByteConsumer<T> sutThrowing = LObjByteConsumer.l((T a1,byte a2) -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LObjByteConsumer<T> wrapped = sutThrowing.handleObjByteCons(handler -> handler
                .wrapWhen(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED)
                .throwIf(IndexOutOfBoundsException.class));

        // then
        try {
            wrapped.doAccept((T)Integer.valueOf(100),(byte)100);
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
        LObjByteConsumer<T> sutThrowing = LObjByteConsumer.l((T a1,byte a2) -> {
            throw new UnsupportedOperationException(ORIGINAL_MESSAGE);
        });

        // when
        LObjByteConsumer<T> wrapped = sutThrowing.handleObjByteCons(h -> Function4U.doNothing());

        // then
        try {
            wrapped.doAccept((T)Integer.valueOf(100),(byte)100);
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
    public void testobjByteConsComposeByte() throws X {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LObjByteConsumer<Integer > sutO = (Integer a1,byte a2) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo((T)Integer.valueOf(90));
                assertThat(a2).isEqualTo((byte)91);
        };

        LFunction<Integer ,Integer > before1 = p0 -> {
            assertThat(p0).isEqualTo((T)Integer.valueOf(80));
            beforeCalls.incrementAndGet();
            return 90;
        };
        LByteUnaryOperator before2 = p1 -> {
            assertThat(p1).isEqualTo((byte)81);
            beforeCalls.incrementAndGet();
            return (byte)91;
        };

        //when
        LObjByteConsumer<Integer > function = sutO.objByteConsComposeByte(before1,before2);
        function.doAccept((Integer )Integer.valueOf(80),(byte)81);

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(beforeCalls.get()).isEqualTo(2);
    }


    @Test
    public void testobjByteConsCompose() throws X {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LObjByteConsumer<Integer > sutO = (Integer a1,byte a2) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo((T)Integer.valueOf(90));
                assertThat(a2).isEqualTo((byte)91);
        };

        LFunction<Integer ,Integer > before1 = p0 -> {
            assertThat(p0).isEqualTo(Integer.valueOf(80));
            beforeCalls.incrementAndGet();
            return 90;
        };
        LToByteFunction<Integer > before2 = p1 -> {
            assertThat(p1).isEqualTo(Integer.valueOf(81));
            beforeCalls.incrementAndGet();
            return (byte)91;
        };

        //when
        LBiConsumer<Integer ,Integer > function = sutO.objByteConsCompose(before1,before2);
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
        LObjByteConsumer<Integer > sutO = (Integer a1,byte a2) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo((T)Integer.valueOf(80));
                assertThat(a2).isEqualTo((byte)81);
        };

        LObjByteConsumer<Integer > thenFunction = (Integer a1,byte a2) -> {
                thenFunctionCalled.set(true);
                assertThat(a1).isEqualTo((T)Integer.valueOf(80));
                assertThat(a2).isEqualTo((byte)81);
        };

        //when
        LObjByteConsumer<Integer > function = sutO.andThen(thenFunction);
        function.doAccept((Integer )Integer.valueOf(80),(byte)81);

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);
    }


    @Test
    public void testNesting() {
        assertThat(sut.nestingObjByteCons())
            .isSameAs(sut)
            .isInstanceOf(LObjByteConsumer.class);
    }

    @Test
    public void testShoving() {
        assertThat(sut.shovingObjByteCons())
            .isSameAs(sut)
            .isInstanceOf(LObjByteConsumer.class);
    }

    @Test
    public void testNestingX() {
        assertThat(sut.nestingObjByteConsX())
            .isSameAs(sut)
            .isInstanceOf(LObjByteConsumerX.class);
    }

    @Test
    public void testShovingX() {
        assertThat(sut.shovingObjByteConsX())
            .isSameAs(sut)
            .isInstanceOf(LObjByteConsumerX.class);
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void testShove() {

        // given
        LObjByteConsumer<T> sutThrowing = LObjByteConsumer.l((T a1,byte a2) -> {
            throw new UnsupportedOperationException();
        });

        // when
        sutThrowing.shovingObjByteCons().doAccept((T)Integer.valueOf(100),(byte)100);
    }

    @Test
    public void testHandleObjByteCons() throws X {

        // given
        LObjByteConsumer<T> sutThrowing = LObjByteConsumer.l((T a1,byte a2) -> {
            throw new UnsupportedOperationException();
        });

        // when
        LObjByteConsumer<T> wrapped = sutThrowing.handleObjByteCons(h -> {
            h.wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED);
        });

        // then
        try {
            wrapped.doAccept((T)Integer.valueOf(100),(byte)100);
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
                .contains("LObjByteConsumer: void doAccept(T a1,byte a2)");
    }


    @Test
    public void isThrowing() {
        assertThat(sut.isThrowing())
            .isFalse();
    }

    //<editor-fold desc="Variants">

    private void variant1(byte a2,T a1) {
    }

    @Test
    public void compilerSubstituteVariant1() {
        LObjByteConsumer lambda = LObjByteConsumer./*<T>*/l1(this::variant1);

        assertThat(lambda).isInstanceOf(LObjByteConsumer.V1.class);
    }

    //</editor-fold>


    @Test void safeCompiles() {
        LObjByteConsumer r1 = LObjByteConsumer.safe(sut); //NOSONAR
        LObjByteConsumerX r2 = LObjByteConsumer.safe(sut); //NOSONAR
    }

    @Test void safePropagates() {
        Object result = LObjByteConsumer.safe(sut);
        assertThat(result).isSameAs(sut);
    }

    @Test void safeProtectsAgainstNpe() {
        Object result = LObjByteConsumer.safe(null);
        assertThat(result).isSameAs(LObjByteConsumer.l(LObjByteConsumer.safe()));
    }

    @Test  void safeSupplierPropagates() {
        LSupplier<LObjByteConsumer<T>> supplier = ()->sut;
        Object result = LObjByteConsumer.safeSupplier(supplier);
        assertThat(result).isSameAs(supplier);
    }

    @Test  void safeSupplierProtectsAgainstNpe() {
        Object result = LObjByteConsumer.safeSupplier(null);
        assertThat(result).isSameAs(LObjByteConsumer.safeSupplier());
    }

    @Test  void safeSupplierCompiles() {
        LSupplier<LObjByteConsumer<T>> r1 = LObjByteConsumer.safeSupplier(()->sut);  //NOSONAR
    }

}
