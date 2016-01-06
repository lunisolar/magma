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
public class LBiObjLongConsumerTest<T1,T2,X extends ParseException> {
    private static final String ORIGINAL_MESSAGE = "Original message";
    private static final String EXCEPTION_WAS_WRAPPED = "Exception was wrapped.";
    private static final String NO_EXCEPTION_WERE_THROWN = "No exception were thrown.";



    private LBiObjLongConsumer<T1,T2> sut = new LBiObjLongConsumer(){
        public  void doAccept(Object a1,Object a2,long a3)  {
            Function4U.doNothing();
        }
    };

    private LBiObjLongConsumerX<T1,T2,X> opposite = new LBiObjLongConsumerX(){
        public  void doAccept(Object a1,Object a2,long a3) throws ParseException {
            Function4U.doNothing();
        }
    };




    private LBiObjLongConsumer<T1,T2> sutAlwaysThrowingUnckeck = LBiObjLongConsumer.l((T1 a1,T2 a2,long a3) -> {
            throw new IndexOutOfBoundsException(ORIGINAL_MESSAGE);
    });



    @Test
    public void testTupleCall() throws X {

        LBiObjLongTriple<T1,T2> domainObject = Tuple4U.tuple((T1)Integer.valueOf(100),(T2)Integer.valueOf(100),(long)100);

        Object result = sut.tupleAccept(domainObject);

        assertThat(result)
            .isSameAs(LTuple.Void.INSTANCE);
    }

    @Test
    public void testNestingDoAcceptUnckeck() throws X {

        // then
        try {
            sutAlwaysThrowingUnckeck.nestingDoAccept((T1)Integer.valueOf(100),(T2)Integer.valueOf(100),(long)100);
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
            sutAlwaysThrowingUnckeck.shovingDoAccept((T1)Integer.valueOf(100),(T2)Integer.valueOf(100),(long)100);
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
            .isEqualTo("LBiObjLongConsumer: void doAccept(T1 a1,T2 a2,long a3)");
    }

    @Test
    public void testLMethod() throws X {
        assertThat(LBiObjLongConsumer.l((Object a1,Object a2,long a3) -> Function4U.doNothing() ))
            .isInstanceOf(LBiObjLongConsumer.class);
    }

    @Test
    public void testWrapMethod() throws X {
        assertThat(LBiObjLongConsumer.wrap(opposite))
            .isInstanceOf(LBiObjLongConsumer.class);
    }

    @Test
    public void testWrapMethodDoNotWrapsRuntimeException() throws X {
        // given
        LBiObjLongConsumerX<T1,T2,X> sutThrowing = LBiObjLongConsumerX.lX((T1 a1,T2 a2,long a3) -> {
            throw new UnsupportedOperationException(ORIGINAL_MESSAGE);
        });

        // when
        LBiObjLongConsumer<T1,T2> wrapped = LBiObjLongConsumer.wrap(sutThrowing);

        // then
        try {
            wrapped.doAccept((T1)Integer.valueOf(100),(T2)Integer.valueOf(100),(long)100);
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
        LBiObjLongConsumerX<T1,T2,ParseException> sutThrowing = LBiObjLongConsumerX.lX((T1 a1,T2 a2,long a3) -> {
            throw new ParseException(ORIGINAL_MESSAGE, 0);
        });

        // when
        LBiObjLongConsumer<T1,T2> wrapped = LBiObjLongConsumer.wrap(sutThrowing);

        // then
        try {
            wrapped.doAccept((T1)Integer.valueOf(100),(T2)Integer.valueOf(100),(long)100);
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
        LBiObjLongConsumer<T1,T2> sutThrowing = LBiObjLongConsumer.l((T1 a1,T2 a2,long a3) -> {
            throw new UnsupportedOperationException();
        });

        // when
        LBiObjLongConsumer<T1,T2> wrapped = sutThrowing.handleBiObjLongCons(handler -> handler
            .wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED));

        // then
        try {
            wrapped.doAccept((T1)Integer.valueOf(100),(T2)Integer.valueOf(100),(long)100);
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
        LBiObjLongConsumer<T1,T2> sutThrowing = LBiObjLongConsumer.l((T1 a1,T2 a2,long a3) -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LBiObjLongConsumer<T1,T2> wrapped = sutThrowing.handleBiObjLongCons(handler -> handler
                .wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED)
                .throwIf(IndexOutOfBoundsException.class));

        // then
        try {
            wrapped.doAccept((T1)Integer.valueOf(100),(T2)Integer.valueOf(100),(long)100);
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
        LBiObjLongConsumer<T1,T2> sutThrowing = LBiObjLongConsumer.l((T1 a1,T2 a2,long a3) -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LBiObjLongConsumer<T1,T2> wrapped = sutThrowing.handleBiObjLongCons(handler -> handler
                .wrapWhen(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED)
                .throwIf(IndexOutOfBoundsException.class));

        // then
        try {
            wrapped.doAccept((T1)Integer.valueOf(100),(T2)Integer.valueOf(100),(long)100);
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
        LBiObjLongConsumer<T1,T2> sutThrowing = LBiObjLongConsumer.l((T1 a1,T2 a2,long a3) -> {
            throw new UnsupportedOperationException(ORIGINAL_MESSAGE);
        });

        // when
        LBiObjLongConsumer<T1,T2> wrapped = sutThrowing.handleBiObjLongCons(h -> Function4U.doNothing());

        // then
        try {
            wrapped.doAccept((T1)Integer.valueOf(100),(T2)Integer.valueOf(100),(long)100);
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
    public void testbiObjLongConsComposeLong() throws X {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LBiObjLongConsumer<Integer ,Integer > sutO = (Integer a1,Integer a2,long a3) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo((T1)Integer.valueOf(90));
                assertThat(a2).isEqualTo((T2)Integer.valueOf(91));
                assertThat(a3).isEqualTo((long)92);
        };

        LFunction<Integer ,Integer > before1 = p0 -> {
            assertThat(p0).isEqualTo((T1)Integer.valueOf(80));
            beforeCalls.incrementAndGet();
            return 90;
        };
        LFunction<Integer ,Integer > before2 = p1 -> {
            assertThat(p1).isEqualTo((T2)Integer.valueOf(81));
            beforeCalls.incrementAndGet();
            return 91;
        };
        LLongUnaryOperator before3 = p2 -> {
            assertThat(p2).isEqualTo((long)82);
            beforeCalls.incrementAndGet();
            return (long)92;
        };

        //when
        LBiObjLongConsumer<Integer ,Integer > function = sutO.biObjLongConsComposeLong(before1,before2,before3);
        function.doAccept((Integer )Integer.valueOf(80),(Integer )Integer.valueOf(81),(long)82);

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(beforeCalls.get()).isEqualTo(3);
    }


    @Test
    public void testbiObjLongConsCompose() throws X {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LBiObjLongConsumer<Integer ,Integer > sutO = (Integer a1,Integer a2,long a3) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo((T1)Integer.valueOf(90));
                assertThat(a2).isEqualTo((T2)Integer.valueOf(91));
                assertThat(a3).isEqualTo((long)92);
        };

        LFunction<Integer ,Integer > before1 = p0 -> {
            assertThat(p0).isEqualTo(Integer.valueOf(80));
            beforeCalls.incrementAndGet();
            return 90;
        };
        LFunction<Integer ,Integer > before2 = p1 -> {
            assertThat(p1).isEqualTo(Integer.valueOf(81));
            beforeCalls.incrementAndGet();
            return 91;
        };
        LToLongFunction<Integer > before3 = p2 -> {
            assertThat(p2).isEqualTo(Integer.valueOf(82));
            beforeCalls.incrementAndGet();
            return (long)92;
        };

        //when
        LTriConsumer<Integer ,Integer ,Integer > function = sutO.biObjLongConsCompose(before1,before2,before3);
        function.doAccept((Integer )Integer.valueOf(80),(Integer )Integer.valueOf(81),(Integer )Integer.valueOf(82));

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
        LBiObjLongConsumer<Integer ,Integer > sutO = (Integer a1,Integer a2,long a3) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo((T1)Integer.valueOf(80));
                assertThat(a2).isEqualTo((T2)Integer.valueOf(81));
                assertThat(a3).isEqualTo((long)82);
        };

        LBiObjLongConsumer<Integer ,Integer > thenFunction = (Integer a1,Integer a2,long a3) -> {
                thenFunctionCalled.set(true);
                assertThat(a1).isEqualTo((T1)Integer.valueOf(80));
                assertThat(a2).isEqualTo((T2)Integer.valueOf(81));
                assertThat(a3).isEqualTo((long)82);
        };

        //when
        LBiObjLongConsumer<Integer ,Integer > function = sutO.andThen(thenFunction);
        function.doAccept((Integer )Integer.valueOf(80),(Integer )Integer.valueOf(81),(long)82);

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);
    }


    @Test
    public void testNesting() {
        assertThat(sut.nestingBiObjLongCons())
            .isSameAs(sut)
            .isInstanceOf(LBiObjLongConsumer.class);
    }

    @Test
    public void testShoving() {
        assertThat(sut.shovingBiObjLongCons())
            .isSameAs(sut)
            .isInstanceOf(LBiObjLongConsumer.class);
    }

    @Test
    public void testNestingX() {
        assertThat(sut.nestingBiObjLongConsX())
            .isSameAs(sut)
            .isInstanceOf(LBiObjLongConsumerX.class);
    }

    @Test
    public void testShovingX() {
        assertThat(sut.shovingBiObjLongConsX())
            .isSameAs(sut)
            .isInstanceOf(LBiObjLongConsumerX.class);
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void testShove() {

        // given
        LBiObjLongConsumer<T1,T2> sutThrowing = LBiObjLongConsumer.l((T1 a1,T2 a2,long a3) -> {
            throw new UnsupportedOperationException();
        });

        // when
        sutThrowing.shovingBiObjLongCons().doAccept((T1)Integer.valueOf(100),(T2)Integer.valueOf(100),(long)100);
    }

    @Test
    public void testHandleBiObjLongCons() throws X {

        // given
        LBiObjLongConsumer<T1,T2> sutThrowing = LBiObjLongConsumer.l((T1 a1,T2 a2,long a3) -> {
            throw new UnsupportedOperationException();
        });

        // when
        LBiObjLongConsumer<T1,T2> wrapped = sutThrowing.handleBiObjLongCons(h -> {
            h.wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED);
        });

        // then
        try {
            wrapped.doAccept((T1)Integer.valueOf(100),(T2)Integer.valueOf(100),(long)100);
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
                .contains("LBiObjLongConsumer: void doAccept(T1 a1,T2 a2,long a3)");
    }


    @Test
    public void isThrowing() {
        assertThat(sut.isThrowing())
            .isFalse();
    }

    //<editor-fold desc="Variants">

    private void variant1(T1 a1,long a3,T2 a2) {
    }

    @Test
    public void compilerSubstituteVariant1() {
        LBiObjLongConsumer lambda = LBiObjLongConsumer./*<T1,T2>*/l1(this::variant1);

        assertThat(lambda).isInstanceOf(LBiObjLongConsumer.V1.class);
    }


    private void variant2(T2 a2,T1 a1,long a3) {
    }

    @Test
    public void compilerSubstituteVariant2() {
        LBiObjLongConsumer lambda = LBiObjLongConsumer./*<T1,T2>*/l2(this::variant2);

        assertThat(lambda).isInstanceOf(LBiObjLongConsumer.V2.class);
    }


    private void variant3(T2 a2,long a3,T1 a1) {
    }

    @Test
    public void compilerSubstituteVariant3() {
        LBiObjLongConsumer lambda = LBiObjLongConsumer./*<T1,T2>*/l3(this::variant3);

        assertThat(lambda).isInstanceOf(LBiObjLongConsumer.V3.class);
    }


    private void variant4(long a3,T1 a1,T2 a2) {
    }

    @Test
    public void compilerSubstituteVariant4() {
        LBiObjLongConsumer lambda = LBiObjLongConsumer./*<T1,T2>*/l4(this::variant4);

        assertThat(lambda).isInstanceOf(LBiObjLongConsumer.V4.class);
    }


    private void variant5(long a3,T2 a2,T1 a1) {
    }

    @Test
    public void compilerSubstituteVariant5() {
        LBiObjLongConsumer lambda = LBiObjLongConsumer./*<T1,T2>*/l5(this::variant5);

        assertThat(lambda).isInstanceOf(LBiObjLongConsumer.V5.class);
    }

    //</editor-fold>


    @Test void safeCompiles() {
        LBiObjLongConsumer r1 = LBiObjLongConsumer.safe(sut); //NOSONAR
        LBiObjLongConsumerX r2 = LBiObjLongConsumer.safe(sut); //NOSONAR
    }

    @Test void safePropagates() {
        Object result = LBiObjLongConsumer.safe(sut);
        assertThat(result).isSameAs(sut);
    }

    @Test void safeProtectsAgainstNpe() {
        Object result = LBiObjLongConsumer.safe(null);
        assertThat(result).isSameAs(LBiObjLongConsumer.l(LBiObjLongConsumer.safe()));
    }

    @Test  void safeSupplierPropagates() {
        LSupplier<LBiObjLongConsumer<T1,T2>> supplier = ()->sut;
        Object result = LBiObjLongConsumer.safeSupplier(supplier);
        assertThat(result).isSameAs(supplier);
    }

    @Test  void safeSupplierProtectsAgainstNpe() {
        Object result = LBiObjLongConsumer.safeSupplier(null);
        assertThat(result).isSameAs(LBiObjLongConsumer.safeSupplier());
    }

    @Test  void safeSupplierCompiles() {
        LSupplier<LBiObjLongConsumer<T1,T2>> r1 = LBiObjLongConsumer.safeSupplier(()->sut);  //NOSONAR
    }

}
