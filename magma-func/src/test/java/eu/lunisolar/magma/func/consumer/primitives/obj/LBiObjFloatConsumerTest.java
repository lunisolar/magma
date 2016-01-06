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
public class LBiObjFloatConsumerTest<T1,T2,X extends ParseException> {
    private static final String ORIGINAL_MESSAGE = "Original message";
    private static final String EXCEPTION_WAS_WRAPPED = "Exception was wrapped.";
    private static final String NO_EXCEPTION_WERE_THROWN = "No exception were thrown.";



    private LBiObjFloatConsumer<T1,T2> sut = new LBiObjFloatConsumer(){
        public  void doAccept(Object a1,Object a2,float a3)  {
            Function4U.doNothing();
        }
    };

    private LBiObjFloatConsumerX<T1,T2,X> opposite = new LBiObjFloatConsumerX(){
        public  void doAccept(Object a1,Object a2,float a3) throws ParseException {
            Function4U.doNothing();
        }
    };




    private LBiObjFloatConsumer<T1,T2> sutAlwaysThrowingUnckeck = LBiObjFloatConsumer.l((T1 a1,T2 a2,float a3) -> {
            throw new IndexOutOfBoundsException(ORIGINAL_MESSAGE);
    });



    @Test
    public void testTupleCall() throws X {

        LBiObjFloatTriple<T1,T2> domainObject = Tuple4U.tuple((T1)Integer.valueOf(100),(T2)Integer.valueOf(100),(float)100);

        Object result = sut.tupleAccept(domainObject);

        assertThat(result)
            .isSameAs(LTuple.Void.INSTANCE);
    }

    @Test
    public void testNestingDoAcceptUnckeck() throws X {

        // then
        try {
            sutAlwaysThrowingUnckeck.nestingDoAccept((T1)Integer.valueOf(100),(T2)Integer.valueOf(100),(float)100);
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
            sutAlwaysThrowingUnckeck.shovingDoAccept((T1)Integer.valueOf(100),(T2)Integer.valueOf(100),(float)100);
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
            .isEqualTo("LBiObjFloatConsumer: void doAccept(T1 a1,T2 a2,float a3)");
    }

    @Test
    public void testLMethod() throws X {
        assertThat(LBiObjFloatConsumer.l((Object a1,Object a2,float a3) -> Function4U.doNothing() ))
            .isInstanceOf(LBiObjFloatConsumer.class);
    }

    @Test
    public void testWrapMethod() throws X {
        assertThat(LBiObjFloatConsumer.wrap(opposite))
            .isInstanceOf(LBiObjFloatConsumer.class);
    }

    @Test
    public void testWrapMethodDoNotWrapsRuntimeException() throws X {
        // given
        LBiObjFloatConsumerX<T1,T2,X> sutThrowing = LBiObjFloatConsumerX.lX((T1 a1,T2 a2,float a3) -> {
            throw new UnsupportedOperationException(ORIGINAL_MESSAGE);
        });

        // when
        LBiObjFloatConsumer<T1,T2> wrapped = LBiObjFloatConsumer.wrap(sutThrowing);

        // then
        try {
            wrapped.doAccept((T1)Integer.valueOf(100),(T2)Integer.valueOf(100),(float)100);
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
        LBiObjFloatConsumerX<T1,T2,ParseException> sutThrowing = LBiObjFloatConsumerX.lX((T1 a1,T2 a2,float a3) -> {
            throw new ParseException(ORIGINAL_MESSAGE, 0);
        });

        // when
        LBiObjFloatConsumer<T1,T2> wrapped = LBiObjFloatConsumer.wrap(sutThrowing);

        // then
        try {
            wrapped.doAccept((T1)Integer.valueOf(100),(T2)Integer.valueOf(100),(float)100);
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
        LBiObjFloatConsumer<T1,T2> sutThrowing = LBiObjFloatConsumer.l((T1 a1,T2 a2,float a3) -> {
            throw new UnsupportedOperationException();
        });

        // when
        LBiObjFloatConsumer<T1,T2> wrapped = sutThrowing.handleBiObjFloatCons(handler -> handler
            .wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED));

        // then
        try {
            wrapped.doAccept((T1)Integer.valueOf(100),(T2)Integer.valueOf(100),(float)100);
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
        LBiObjFloatConsumer<T1,T2> sutThrowing = LBiObjFloatConsumer.l((T1 a1,T2 a2,float a3) -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LBiObjFloatConsumer<T1,T2> wrapped = sutThrowing.handleBiObjFloatCons(handler -> handler
                .wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED)
                .throwIf(IndexOutOfBoundsException.class));

        // then
        try {
            wrapped.doAccept((T1)Integer.valueOf(100),(T2)Integer.valueOf(100),(float)100);
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
        LBiObjFloatConsumer<T1,T2> sutThrowing = LBiObjFloatConsumer.l((T1 a1,T2 a2,float a3) -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LBiObjFloatConsumer<T1,T2> wrapped = sutThrowing.handleBiObjFloatCons(handler -> handler
                .wrapWhen(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED)
                .throwIf(IndexOutOfBoundsException.class));

        // then
        try {
            wrapped.doAccept((T1)Integer.valueOf(100),(T2)Integer.valueOf(100),(float)100);
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
        LBiObjFloatConsumer<T1,T2> sutThrowing = LBiObjFloatConsumer.l((T1 a1,T2 a2,float a3) -> {
            throw new UnsupportedOperationException(ORIGINAL_MESSAGE);
        });

        // when
        LBiObjFloatConsumer<T1,T2> wrapped = sutThrowing.handleBiObjFloatCons(h -> Function4U.doNothing());

        // then
        try {
            wrapped.doAccept((T1)Integer.valueOf(100),(T2)Integer.valueOf(100),(float)100);
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
    public void testbiObjFloatConsComposeFloat() throws X {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LBiObjFloatConsumer<Integer ,Integer > sutO = (Integer a1,Integer a2,float a3) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo((T1)Integer.valueOf(90));
                assertThat(a2).isEqualTo((T2)Integer.valueOf(91));
                assertThat(a3).isEqualTo((float)92);
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
        LFloatUnaryOperator before3 = p2 -> {
            assertThat(p2).isEqualTo((float)82);
            beforeCalls.incrementAndGet();
            return (float)92;
        };

        //when
        LBiObjFloatConsumer<Integer ,Integer > function = sutO.biObjFloatConsComposeFloat(before1,before2,before3);
        function.doAccept((Integer )Integer.valueOf(80),(Integer )Integer.valueOf(81),(float)82);

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(beforeCalls.get()).isEqualTo(3);
    }


    @Test
    public void testbiObjFloatConsCompose() throws X {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LBiObjFloatConsumer<Integer ,Integer > sutO = (Integer a1,Integer a2,float a3) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo((T1)Integer.valueOf(90));
                assertThat(a2).isEqualTo((T2)Integer.valueOf(91));
                assertThat(a3).isEqualTo((float)92);
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
        LToFloatFunction<Integer > before3 = p2 -> {
            assertThat(p2).isEqualTo(Integer.valueOf(82));
            beforeCalls.incrementAndGet();
            return (float)92;
        };

        //when
        LTriConsumer<Integer ,Integer ,Integer > function = sutO.biObjFloatConsCompose(before1,before2,before3);
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
        LBiObjFloatConsumer<Integer ,Integer > sutO = (Integer a1,Integer a2,float a3) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo((T1)Integer.valueOf(80));
                assertThat(a2).isEqualTo((T2)Integer.valueOf(81));
                assertThat(a3).isEqualTo((float)82);
        };

        LBiObjFloatConsumer<Integer ,Integer > thenFunction = (Integer a1,Integer a2,float a3) -> {
                thenFunctionCalled.set(true);
                assertThat(a1).isEqualTo((T1)Integer.valueOf(80));
                assertThat(a2).isEqualTo((T2)Integer.valueOf(81));
                assertThat(a3).isEqualTo((float)82);
        };

        //when
        LBiObjFloatConsumer<Integer ,Integer > function = sutO.andThen(thenFunction);
        function.doAccept((Integer )Integer.valueOf(80),(Integer )Integer.valueOf(81),(float)82);

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);
    }


    @Test
    public void testNesting() {
        assertThat(sut.nestingBiObjFloatCons())
            .isSameAs(sut)
            .isInstanceOf(LBiObjFloatConsumer.class);
    }

    @Test
    public void testShoving() {
        assertThat(sut.shovingBiObjFloatCons())
            .isSameAs(sut)
            .isInstanceOf(LBiObjFloatConsumer.class);
    }

    @Test
    public void testNestingX() {
        assertThat(sut.nestingBiObjFloatConsX())
            .isSameAs(sut)
            .isInstanceOf(LBiObjFloatConsumerX.class);
    }

    @Test
    public void testShovingX() {
        assertThat(sut.shovingBiObjFloatConsX())
            .isSameAs(sut)
            .isInstanceOf(LBiObjFloatConsumerX.class);
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void testShove() {

        // given
        LBiObjFloatConsumer<T1,T2> sutThrowing = LBiObjFloatConsumer.l((T1 a1,T2 a2,float a3) -> {
            throw new UnsupportedOperationException();
        });

        // when
        sutThrowing.shovingBiObjFloatCons().doAccept((T1)Integer.valueOf(100),(T2)Integer.valueOf(100),(float)100);
    }

    @Test
    public void testHandleBiObjFloatCons() throws X {

        // given
        LBiObjFloatConsumer<T1,T2> sutThrowing = LBiObjFloatConsumer.l((T1 a1,T2 a2,float a3) -> {
            throw new UnsupportedOperationException();
        });

        // when
        LBiObjFloatConsumer<T1,T2> wrapped = sutThrowing.handleBiObjFloatCons(h -> {
            h.wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED);
        });

        // then
        try {
            wrapped.doAccept((T1)Integer.valueOf(100),(T2)Integer.valueOf(100),(float)100);
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
                .contains("LBiObjFloatConsumer: void doAccept(T1 a1,T2 a2,float a3)");
    }


    @Test
    public void isThrowing() {
        assertThat(sut.isThrowing())
            .isFalse();
    }

    //<editor-fold desc="Variants">

    private void variant1(T1 a1,float a3,T2 a2) {
    }

    @Test
    public void compilerSubstituteVariant1() {
        LBiObjFloatConsumer lambda = LBiObjFloatConsumer./*<T1,T2>*/l1(this::variant1);

        assertThat(lambda).isInstanceOf(LBiObjFloatConsumer.V1.class);
    }


    private void variant2(T2 a2,T1 a1,float a3) {
    }

    @Test
    public void compilerSubstituteVariant2() {
        LBiObjFloatConsumer lambda = LBiObjFloatConsumer./*<T1,T2>*/l2(this::variant2);

        assertThat(lambda).isInstanceOf(LBiObjFloatConsumer.V2.class);
    }


    private void variant3(T2 a2,float a3,T1 a1) {
    }

    @Test
    public void compilerSubstituteVariant3() {
        LBiObjFloatConsumer lambda = LBiObjFloatConsumer./*<T1,T2>*/l3(this::variant3);

        assertThat(lambda).isInstanceOf(LBiObjFloatConsumer.V3.class);
    }


    private void variant4(float a3,T1 a1,T2 a2) {
    }

    @Test
    public void compilerSubstituteVariant4() {
        LBiObjFloatConsumer lambda = LBiObjFloatConsumer./*<T1,T2>*/l4(this::variant4);

        assertThat(lambda).isInstanceOf(LBiObjFloatConsumer.V4.class);
    }


    private void variant5(float a3,T2 a2,T1 a1) {
    }

    @Test
    public void compilerSubstituteVariant5() {
        LBiObjFloatConsumer lambda = LBiObjFloatConsumer./*<T1,T2>*/l5(this::variant5);

        assertThat(lambda).isInstanceOf(LBiObjFloatConsumer.V5.class);
    }

    //</editor-fold>


    @Test void safeCompiles() {
        LBiObjFloatConsumer r1 = LBiObjFloatConsumer.safe(sut); //NOSONAR
        LBiObjFloatConsumerX r2 = LBiObjFloatConsumer.safe(sut); //NOSONAR
    }

    @Test void safePropagates() {
        Object result = LBiObjFloatConsumer.safe(sut);
        assertThat(result).isSameAs(sut);
    }

    @Test void safeProtectsAgainstNpe() {
        Object result = LBiObjFloatConsumer.safe(null);
        assertThat(result).isSameAs(LBiObjFloatConsumer.l(LBiObjFloatConsumer.safe()));
    }

    @Test  void safeSupplierPropagates() {
        LSupplier<LBiObjFloatConsumer<T1,T2>> supplier = ()->sut;
        Object result = LBiObjFloatConsumer.safeSupplier(supplier);
        assertThat(result).isSameAs(supplier);
    }

    @Test  void safeSupplierProtectsAgainstNpe() {
        Object result = LBiObjFloatConsumer.safeSupplier(null);
        assertThat(result).isSameAs(LBiObjFloatConsumer.safeSupplier());
    }

    @Test  void safeSupplierCompiles() {
        LSupplier<LBiObjFloatConsumer<T1,T2>> r1 = LBiObjFloatConsumer.safeSupplier(()->sut);  //NOSONAR
    }

}
