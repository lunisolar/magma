/*
 * This file is part of "lunisolar-magma".
 *
 * (C) Copyright 2015 Lunisolar (http://lunisolar.eu/).
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
public class LBiObjLongConsumerXTest<T1,T2,X extends ParseException> {
    private static final String ORIGINAL_MESSAGE = "Original message";
    private static final String EXCEPTION_WAS_WRAPPED = "Exception was wrapped.";
    private static final String NO_EXCEPTION_WERE_THROWN = "No exception were thrown.";



    private LBiObjLongConsumerX<T1,T2,X> sut = new LBiObjLongConsumerX(){
        public  void doAccept(Object a1,Object a2,long a3) throws ParseException {
            Function4U.doNothing();
        }
    };

    private LBiObjLongConsumer<T1,T2> opposite = new LBiObjLongConsumer(){
        public  void doAccept(Object a1,Object a2,long a3)  {
            Function4U.doNothing();
        }
    };



    private LBiObjLongConsumerX<T1,T2,ParseException> sutAlwaysThrowing = LBiObjLongConsumerX.lX((T1 a1,T2 a2,long a3) -> {
            throw new ParseException(ORIGINAL_MESSAGE, 0);
    });

    private LBiObjLongConsumerX<T1,T2,RuntimeException> sutAlwaysThrowingUnckeck = LBiObjLongConsumerX.lX((T1 a1,T2 a2,long a3) -> {
            throw new IndexOutOfBoundsException(ORIGINAL_MESSAGE);
    });



    @Test
    public void testTupleCall() throws X {

        //FunctionalCall<LBiObjLongTriple<T1,T2>,LTuple.Void,X> theCall = sut;

        LBiObjLongTriple<T1,T2> domainObject = Tuple4U.tuple((T1)Integer.valueOf(100),(T2)Integer.valueOf(100),(long)100);

        Object result = sut.tupleAccept(domainObject);

        assertThat(result)
            .isSameAs(LTuple.Void.INSTANCE);
    }

    @Test
    public void testNestingDoAcceptChecked() throws X {

        // then
        try {
            sutAlwaysThrowing.nestingDoAccept((T1)Integer.valueOf(100),(T2)Integer.valueOf(100),(long)100);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(NestedException.class)
                    .hasCauseExactlyInstanceOf(ParseException.class)
                    .hasMessage(ORIGINAL_MESSAGE);
        }
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
    public void testShovingDoAcceptChecked() throws X {

        // then
        try {
            sutAlwaysThrowing.shovingDoAccept((T1)Integer.valueOf(100),(T2)Integer.valueOf(100),(long)100);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(ParseException.class)
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
            .isEqualTo("LBiObjLongConsumerX: void doAccept(T1 a1,T2 a2,long a3) throws X");
    }

    @Test
    public void testLXMethod() throws X {
        assertThat(LBiObjLongConsumerX.lX((Object a1,Object a2,long a3) -> Function4U.doNothing() ))
            .isInstanceOf(LBiObjLongConsumerX.class);
    }

    @Test
    public void testWrapXMethod() throws X {
        assertThat(LBiObjLongConsumerX.wrapX(opposite))
            .isInstanceOf(LBiObjLongConsumerX.class);
    }


    @Test
    public void testWrapExceptionMethodWrapsTheException() throws X {

        // given
        LBiObjLongConsumerX<T1,T2,X> sutThrowing = LBiObjLongConsumerX.lX((T1 a1,T2 a2,long a3) -> {
            throw new UnsupportedOperationException();
        });

        // when
        LBiObjLongConsumerX<T1,T2,X> wrapped = sutThrowing.handleBiObjLongConsX(handler -> handler
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
        LBiObjLongConsumerX<T1,T2,X> sutThrowing = LBiObjLongConsumerX.lX((T1 a1,T2 a2,long a3) -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LBiObjLongConsumerX<T1,T2,X> wrapped = sutThrowing.handleBiObjLongConsX(handler -> handler
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
        LBiObjLongConsumerX<T1,T2,X> sutThrowing = LBiObjLongConsumerX.lX((T1 a1,T2 a2,long a3) -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LBiObjLongConsumerX<T1,T2,X> wrapped = sutThrowing.handleBiObjLongConsX(handler -> handler
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
        LBiObjLongConsumerX<T1,T2,X> sutThrowing = LBiObjLongConsumerX.lX((T1 a1,T2 a2,long a3) -> {
            throw (X) new ParseException(ORIGINAL_MESSAGE, 0);
        });

        // when
        LBiObjLongConsumerX<T1,T2,X> wrapped = sutThrowing.handleBiObjLongConsX(h -> Function4U.doNothing());

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



    // <editor-fold desc="compose (functional)">

    @Test
    public void testbiObjLongConsComposeLong() throws X {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LBiObjLongConsumerX<Integer ,Integer ,X> sutO = (Integer a1,Integer a2,long a3) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo((T1)Integer.valueOf(90));
                assertThat(a2).isEqualTo((T2)Integer.valueOf(91));
                assertThat(a3).isEqualTo((long)92);
        };

        LFunctionX<Integer ,Integer ,X> before1 = p0 -> {
            assertThat(p0).isEqualTo((T1)Integer.valueOf(80));
            beforeCalls.incrementAndGet();
            return 90;
        };
        LFunctionX<Integer ,Integer ,X> before2 = p1 -> {
            assertThat(p1).isEqualTo((T2)Integer.valueOf(81));
            beforeCalls.incrementAndGet();
            return 91;
        };
        LLongUnaryOperatorX<X> before3 = p2 -> {
            assertThat(p2).isEqualTo((long)82);
            beforeCalls.incrementAndGet();
            return (long)92;
        };

        //when
        LBiObjLongConsumerX<Integer ,Integer ,X> function = sutO.biObjLongConsComposeLong(before1,before2,before3);
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
        LBiObjLongConsumerX<Integer ,Integer ,X> sutO = (Integer a1,Integer a2,long a3) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo((T1)Integer.valueOf(90));
                assertThat(a2).isEqualTo((T2)Integer.valueOf(91));
                assertThat(a3).isEqualTo((long)92);
        };

        LFunctionX<Integer ,Integer ,X> before1 = p0 -> {
            assertThat(p0).isEqualTo(Integer.valueOf(80));
            beforeCalls.incrementAndGet();
            return 90;
        };
        LFunctionX<Integer ,Integer ,X> before2 = p1 -> {
            assertThat(p1).isEqualTo(Integer.valueOf(81));
            beforeCalls.incrementAndGet();
            return 91;
        };
        LToLongFunctionX<Integer ,X> before3 = p2 -> {
            assertThat(p2).isEqualTo(Integer.valueOf(82));
            beforeCalls.incrementAndGet();
            return (long)92;
        };

        //when
        LTriConsumerX<Integer ,Integer ,Integer ,X> function = sutO.biObjLongConsCompose(before1,before2,before3);
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
        LBiObjLongConsumerX<Integer ,Integer ,X> sutO = (Integer a1,Integer a2,long a3) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo((T1)Integer.valueOf(80));
                assertThat(a2).isEqualTo((T2)Integer.valueOf(81));
                assertThat(a3).isEqualTo((long)82);
        };

        LBiObjLongConsumerX<Integer ,Integer ,X> thenFunction = (Integer a1,Integer a2,long a3) -> {
                thenFunctionCalled.set(true);
                assertThat(a1).isEqualTo((T1)Integer.valueOf(80));
                assertThat(a2).isEqualTo((T2)Integer.valueOf(81));
                assertThat(a3).isEqualTo((long)82);
        };

        //when
        LBiObjLongConsumerX<Integer ,Integer ,X> function = sutO.andThen(thenFunction);
        function.doAccept((Integer )Integer.valueOf(80),(Integer )Integer.valueOf(81),(long)82);

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
        LBiObjLongConsumerX<T1,T2,X> sutThrowing = LBiObjLongConsumerX.lX((T1 a1,T2 a2,long a3) -> {
            throw new UnsupportedOperationException();
        });

        // when
        sutThrowing.shovingBiObjLongCons().doAccept((T1)Integer.valueOf(100),(T2)Integer.valueOf(100),(long)100);
    }

    @Test
    public void testHandleBiObjLongCons() throws X {

        // given
        LBiObjLongConsumerX<T1,T2,X> sutThrowing = LBiObjLongConsumerX.lX((T1 a1,T2 a2,long a3) -> {
            throw new UnsupportedOperationException();
        });

        // when
        LBiObjLongConsumerX<T1,T2,X> wrapped = sutThrowing.handleBiObjLongConsX(h -> {
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
                .contains("LBiObjLongConsumerX: void doAccept(T1 a1,T2 a2,long a3) throws X");
    }


    @Test
    public void isThrowing() {
        assertThat(sut.isThrowing())
            .isTrue();
    }

    //<editor-fold desc="Variants">

    private void variant1(T1 a1,long a3,T2 a2) {
    }

    @Test
    public void compilerSubstituteVariant1() {
        LBiObjLongConsumerX lambda = LBiObjLongConsumerX./*<T1,T2,X>*/lX1(this::variant1);

        assertThat(lambda).isInstanceOf(LBiObjLongConsumerX.V1.class);
    }


    private void variant2(T2 a2,T1 a1,long a3) {
    }

    @Test
    public void compilerSubstituteVariant2() {
        LBiObjLongConsumerX lambda = LBiObjLongConsumerX./*<T1,T2,X>*/lX2(this::variant2);

        assertThat(lambda).isInstanceOf(LBiObjLongConsumerX.V2.class);
    }


    private void variant3(T2 a2,long a3,T1 a1) {
    }

    @Test
    public void compilerSubstituteVariant3() {
        LBiObjLongConsumerX lambda = LBiObjLongConsumerX./*<T1,T2,X>*/lX3(this::variant3);

        assertThat(lambda).isInstanceOf(LBiObjLongConsumerX.V3.class);
    }


    private void variant4(long a3,T1 a1,T2 a2) {
    }

    @Test
    public void compilerSubstituteVariant4() {
        LBiObjLongConsumerX lambda = LBiObjLongConsumerX./*<T1,T2,X>*/lX4(this::variant4);

        assertThat(lambda).isInstanceOf(LBiObjLongConsumerX.V4.class);
    }


    private void variant5(long a3,T2 a2,T1 a1) {
    }

    @Test
    public void compilerSubstituteVariant5() {
        LBiObjLongConsumerX lambda = LBiObjLongConsumerX./*<T1,T2,X>*/lX5(this::variant5);

        assertThat(lambda).isInstanceOf(LBiObjLongConsumerX.V5.class);
    }

    //</editor-fold>


    @Test void safeCompiles() {
        LBiObjLongConsumerX r1 = LBiObjLongConsumerX.safe(sut);
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
        LSupplierX<LBiObjLongConsumerX<T1,T2,X>,Y> supplier = ()->sut;
        Object result = LBiObjLongConsumerX.safeSupplier(supplier);
        assertThat(result).isSameAs(supplier);
    }

    @Test <Y extends Throwable> void safeSupplierProtectsAgainstNpe() {
        Object result = LBiObjLongConsumerX.safeSupplier(null);
        assertThat(result).isSameAs(LBiObjLongConsumerX.safeSupplier());
    }

    @Test <Y extends Throwable> void safeSupplierCompiles() {
        LSupplierX<LBiObjLongConsumerX<T1,T2,X>,Y> r1 = LBiObjLongConsumerX.safeSupplier(()->sut);
    }

}
