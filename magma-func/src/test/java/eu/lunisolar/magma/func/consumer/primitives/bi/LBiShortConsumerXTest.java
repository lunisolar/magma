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

package eu.lunisolar.magma.func.consumer.primitives.bi;

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
public class LBiShortConsumerXTest<X extends ParseException> {
    private static final String ORIGINAL_MESSAGE = "Original message";
    private static final String EXCEPTION_WAS_WRAPPED = "Exception was wrapped.";
    private static final String NO_EXCEPTION_WERE_THROWN = "No exception were thrown.";



    private LBiShortConsumerX<X> sut = new LBiShortConsumerX(){
        public  void doAccept(short a1,short a2) throws ParseException {
            Function4U.doNothing();
        }
    };

    private LBiShortConsumer opposite = new LBiShortConsumer(){
        public  void doAccept(short a1,short a2)  {
            Function4U.doNothing();
        }
    };



    private LBiShortConsumerX<ParseException> sutAlwaysThrowing = LBiShortConsumerX.lX((short a1,short a2) -> {
            throw new ParseException(ORIGINAL_MESSAGE, 0);
    });

    private LBiShortConsumerX<RuntimeException> sutAlwaysThrowingUnckeck = LBiShortConsumerX.lX((short a1,short a2) -> {
            throw new IndexOutOfBoundsException(ORIGINAL_MESSAGE);
    });



    @Test
    public void testTupleCall() throws X {

        LShortPair domainObject = Tuple4U.tuple((short)100,(short)100);

        Object result = sut.tupleAccept(domainObject);

        assertThat(result)
            .isSameAs(LTuple.Void.INSTANCE);
    }

    @Test
    public void testNestingDoAcceptChecked() throws X {

        // then
        try {
            sutAlwaysThrowing.nestingDoAccept((short)100,(short)100);
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
            sutAlwaysThrowingUnckeck.nestingDoAccept((short)100,(short)100);
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
            sutAlwaysThrowing.shovingDoAccept((short)100,(short)100);
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
            sutAlwaysThrowingUnckeck.shovingDoAccept((short)100,(short)100);
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
            .isEqualTo("LBiShortConsumerX: void doAccept(short a1,short a2) throws X");
    }

    @Test
    public void testLXMethod() throws X {
        assertThat(LBiShortConsumerX.lX((short a1,short a2) -> Function4U.doNothing() ))
            .isInstanceOf(LBiShortConsumerX.class);
    }

    @Test
    public void testWrapXMethod() throws X {
        assertThat(LBiShortConsumerX.wrapX(opposite))
            .isInstanceOf(LBiShortConsumerX.class);
    }


    @Test
    public void testWrapExceptionMethodWrapsTheException() throws X {

        // given
        LBiShortConsumerX<X> sutThrowing = LBiShortConsumerX.lX((short a1,short a2) -> {
            throw new UnsupportedOperationException();
        });

        // when
        LBiShortConsumerX<X> wrapped = sutThrowing.handleBiShortConsX(handler -> handler
            .wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED));

        // then
        try {
            wrapped.doAccept((short)100,(short)100);
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
        LBiShortConsumerX<X> sutThrowing = LBiShortConsumerX.lX((short a1,short a2) -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LBiShortConsumerX<X> wrapped = sutThrowing.handleBiShortConsX(handler -> handler
                .wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED)
                .throwIf(IndexOutOfBoundsException.class));

        // then
        try {
            wrapped.doAccept((short)100,(short)100);
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
        LBiShortConsumerX<X> sutThrowing = LBiShortConsumerX.lX((short a1,short a2) -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LBiShortConsumerX<X> wrapped = sutThrowing.handleBiShortConsX(handler -> handler
                .wrapWhen(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED)
                .throwIf(IndexOutOfBoundsException.class));

        // then
        try {
            wrapped.doAccept((short)100,(short)100);
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
        LBiShortConsumerX<X> sutThrowing = LBiShortConsumerX.lX((short a1,short a2) -> {
            throw (X) new ParseException(ORIGINAL_MESSAGE, 0);
        });

        // when
        LBiShortConsumerX<X> wrapped = sutThrowing.handleBiShortConsX(h -> Function4U.doNothing());

        // then
        try {
            wrapped.doAccept((short)100,(short)100);
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
    public void testbiShortConsComposeShort() throws X {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LBiShortConsumerX<X> sutO = (short a1,short a2) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo((short)90);
                assertThat(a2).isEqualTo((short)91);
        };

        LShortUnaryOperatorX<X> before1 = p0 -> {
            assertThat(p0).isEqualTo((short)80);
            beforeCalls.incrementAndGet();
            return (short)90;
        };
        LShortUnaryOperatorX<X> before2 = p1 -> {
            assertThat(p1).isEqualTo((short)81);
            beforeCalls.incrementAndGet();
            return (short)91;
        };

        //when
        LBiShortConsumerX<X> function = sutO.biShortConsComposeShort(before1,before2);
        function.doAccept((short)80,(short)81);

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(beforeCalls.get()).isEqualTo(2);
    }


    @Test
    public void testbiShortConsCompose() throws X {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LBiShortConsumerX<X> sutO = (short a1,short a2) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo((short)90);
                assertThat(a2).isEqualTo((short)91);
        };

        LToShortFunctionX<Integer ,X> before1 = p0 -> {
            assertThat(p0).isEqualTo(Integer.valueOf(80));
            beforeCalls.incrementAndGet();
            return (short)90;
        };
        LToShortFunctionX<Integer ,X> before2 = p1 -> {
            assertThat(p1).isEqualTo(Integer.valueOf(81));
            beforeCalls.incrementAndGet();
            return (short)91;
        };

        //when
        LBiConsumerX<Integer ,Integer ,X> function = sutO.biShortConsCompose(before1,before2);
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
        LBiShortConsumerX<X> sutO = (short a1,short a2) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo((short)80);
                assertThat(a2).isEqualTo((short)81);
        };

        LBiShortConsumerX<X> thenFunction = (short a1,short a2) -> {
                thenFunctionCalled.set(true);
                assertThat(a1).isEqualTo((short)80);
                assertThat(a2).isEqualTo((short)81);
        };

        //when
        LBiShortConsumerX<X> function = sutO.andThen(thenFunction);
        function.doAccept((short)80,(short)81);

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);
    }


    @Test
    public void testNesting() {
        assertThat(sut.nestingBiShortCons())
            .isInstanceOf(LBiShortConsumer.class);
    }

    @Test
    public void testShoving() {
        assertThat(sut.shovingBiShortCons())
            .isInstanceOf(LBiShortConsumer.class);
    }

    @Test
    public void testNestingX() {
        assertThat(sut.nestingBiShortConsX())
            .isInstanceOf(LBiShortConsumerX.class);
    }

    @Test
    public void testShovingX() {
        assertThat(sut.shovingBiShortConsX())
            .isInstanceOf(LBiShortConsumerX.class);
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void testShove() {

        // given
        LBiShortConsumerX<X> sutThrowing = LBiShortConsumerX.lX((short a1,short a2) -> {
            throw new UnsupportedOperationException();
        });

        // when
        sutThrowing.shovingBiShortCons().doAccept((short)100,(short)100);
    }

    @Test
    public void testHandleBiShortCons() throws X {

        // given
        LBiShortConsumerX<X> sutThrowing = LBiShortConsumerX.lX((short a1,short a2) -> {
            throw new UnsupportedOperationException();
        });

        // when
        LBiShortConsumerX<X> wrapped = sutThrowing.handleBiShortConsX(h -> {
            h.wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED);
        });

        // then
        try {
            wrapped.doAccept((short)100,(short)100);
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
                .contains("LBiShortConsumerX: void doAccept(short a1,short a2) throws X");
    }


    @Test
    public void isThrowing() {
        assertThat(sut.isThrowing())
            .isTrue();
    }

    //<editor-fold desc="Variants">

    private void variant1(short a2,short a1) {
    }

    @Test
    public void compilerSubstituteVariant1() {
        LBiShortConsumerX lambda = LBiShortConsumerX./*<X>*/lX1(this::variant1);

        assertThat(lambda).isInstanceOf(LBiShortConsumerX.V1.class);
    }

    //</editor-fold>


    @Test void safeCompiles() {
        LBiShortConsumerX r1 = LBiShortConsumerX.safe(sut); //NOSONAR
    }

    @Test void safePropagates() {
        Object result = LBiShortConsumerX.safe(sut);
        assertThat(result).isSameAs(sut);
    }

    @Test void safeProtectsAgainstNpe() {
        Object result = LBiShortConsumerX.safe(null);
        assertThat(result).isSameAs(LBiShortConsumerX.lX(LBiShortConsumerX.safe()));
    }

    @Test <Y extends Throwable> void safeSupplierPropagates() {
        LSupplierX<LBiShortConsumerX<X>,Y> supplier = ()->sut;
        Object result = LBiShortConsumerX.safeSupplier(supplier);
        assertThat(result).isSameAs(supplier);
    }

    @Test <Y extends Throwable> void safeSupplierProtectsAgainstNpe() {
        Object result = LBiShortConsumerX.safeSupplier(null);
        assertThat(result).isSameAs(LBiShortConsumerX.safeSupplier());
    }

    @Test <Y extends Throwable> void safeSupplierCompiles() {
        LSupplierX<LBiShortConsumerX<X>,Y> r1 = LBiShortConsumerX.safeSupplier(()->sut);  //NOSONAR
    }

}
