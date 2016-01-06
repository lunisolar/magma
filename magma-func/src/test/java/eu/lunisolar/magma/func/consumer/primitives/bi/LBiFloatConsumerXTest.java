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
public class LBiFloatConsumerXTest<X extends ParseException> {
    private static final String ORIGINAL_MESSAGE = "Original message";
    private static final String EXCEPTION_WAS_WRAPPED = "Exception was wrapped.";
    private static final String NO_EXCEPTION_WERE_THROWN = "No exception were thrown.";



    private LBiFloatConsumerX<X> sut = new LBiFloatConsumerX(){
        public  void doAccept(float a1,float a2) throws ParseException {
            Function4U.doNothing();
        }
    };

    private LBiFloatConsumer opposite = new LBiFloatConsumer(){
        public  void doAccept(float a1,float a2)  {
            Function4U.doNothing();
        }
    };



    private LBiFloatConsumerX<ParseException> sutAlwaysThrowing = LBiFloatConsumerX.lX((float a1,float a2) -> {
            throw new ParseException(ORIGINAL_MESSAGE, 0);
    });

    private LBiFloatConsumerX<RuntimeException> sutAlwaysThrowingUnckeck = LBiFloatConsumerX.lX((float a1,float a2) -> {
            throw new IndexOutOfBoundsException(ORIGINAL_MESSAGE);
    });



    @Test
    public void testTupleCall() throws X {

        LFloatPair domainObject = Tuple4U.tuple((float)100,(float)100);

        Object result = sut.tupleAccept(domainObject);

        assertThat(result)
            .isSameAs(LTuple.Void.INSTANCE);
    }

    @Test
    public void testNestingDoAcceptChecked() throws X {

        // then
        try {
            sutAlwaysThrowing.nestingDoAccept((float)100,(float)100);
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
            sutAlwaysThrowingUnckeck.nestingDoAccept((float)100,(float)100);
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
            sutAlwaysThrowing.shovingDoAccept((float)100,(float)100);
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
            sutAlwaysThrowingUnckeck.shovingDoAccept((float)100,(float)100);
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
            .isEqualTo("LBiFloatConsumerX: void doAccept(float a1,float a2) throws X");
    }

    @Test
    public void testLXMethod() throws X {
        assertThat(LBiFloatConsumerX.lX((float a1,float a2) -> Function4U.doNothing() ))
            .isInstanceOf(LBiFloatConsumerX.class);
    }

    @Test
    public void testWrapXMethod() throws X {
        assertThat(LBiFloatConsumerX.wrapX(opposite))
            .isInstanceOf(LBiFloatConsumerX.class);
    }


    @Test
    public void testWrapExceptionMethodWrapsTheException() throws X {

        // given
        LBiFloatConsumerX<X> sutThrowing = LBiFloatConsumerX.lX((float a1,float a2) -> {
            throw new UnsupportedOperationException();
        });

        // when
        LBiFloatConsumerX<X> wrapped = sutThrowing.handleBiFloatConsX(handler -> handler
            .wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED));

        // then
        try {
            wrapped.doAccept((float)100,(float)100);
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
        LBiFloatConsumerX<X> sutThrowing = LBiFloatConsumerX.lX((float a1,float a2) -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LBiFloatConsumerX<X> wrapped = sutThrowing.handleBiFloatConsX(handler -> handler
                .wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED)
                .throwIf(IndexOutOfBoundsException.class));

        // then
        try {
            wrapped.doAccept((float)100,(float)100);
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
        LBiFloatConsumerX<X> sutThrowing = LBiFloatConsumerX.lX((float a1,float a2) -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LBiFloatConsumerX<X> wrapped = sutThrowing.handleBiFloatConsX(handler -> handler
                .wrapWhen(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED)
                .throwIf(IndexOutOfBoundsException.class));

        // then
        try {
            wrapped.doAccept((float)100,(float)100);
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
        LBiFloatConsumerX<X> sutThrowing = LBiFloatConsumerX.lX((float a1,float a2) -> {
            throw (X) new ParseException(ORIGINAL_MESSAGE, 0);
        });

        // when
        LBiFloatConsumerX<X> wrapped = sutThrowing.handleBiFloatConsX(h -> Function4U.doNothing());

        // then
        try {
            wrapped.doAccept((float)100,(float)100);
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
    public void testbiFloatConsComposeFloat() throws X {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LBiFloatConsumerX<X> sutO = (float a1,float a2) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo((float)90);
                assertThat(a2).isEqualTo((float)91);
        };

        LFloatUnaryOperatorX<X> before1 = p0 -> {
            assertThat(p0).isEqualTo((float)80);
            beforeCalls.incrementAndGet();
            return (float)90;
        };
        LFloatUnaryOperatorX<X> before2 = p1 -> {
            assertThat(p1).isEqualTo((float)81);
            beforeCalls.incrementAndGet();
            return (float)91;
        };

        //when
        LBiFloatConsumerX<X> function = sutO.biFloatConsComposeFloat(before1,before2);
        function.doAccept((float)80,(float)81);

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(beforeCalls.get()).isEqualTo(2);
    }


    @Test
    public void testbiFloatConsCompose() throws X {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LBiFloatConsumerX<X> sutO = (float a1,float a2) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo((float)90);
                assertThat(a2).isEqualTo((float)91);
        };

        LToFloatFunctionX<Integer ,X> before1 = p0 -> {
            assertThat(p0).isEqualTo(Integer.valueOf(80));
            beforeCalls.incrementAndGet();
            return (float)90;
        };
        LToFloatFunctionX<Integer ,X> before2 = p1 -> {
            assertThat(p1).isEqualTo(Integer.valueOf(81));
            beforeCalls.incrementAndGet();
            return (float)91;
        };

        //when
        LBiConsumerX<Integer ,Integer ,X> function = sutO.biFloatConsCompose(before1,before2);
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
        LBiFloatConsumerX<X> sutO = (float a1,float a2) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo((float)80);
                assertThat(a2).isEqualTo((float)81);
        };

        LBiFloatConsumerX<X> thenFunction = (float a1,float a2) -> {
                thenFunctionCalled.set(true);
                assertThat(a1).isEqualTo((float)80);
                assertThat(a2).isEqualTo((float)81);
        };

        //when
        LBiFloatConsumerX<X> function = sutO.andThen(thenFunction);
        function.doAccept((float)80,(float)81);

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);
    }


    @Test
    public void testNesting() {
        assertThat(sut.nestingBiFloatCons())
            .isInstanceOf(LBiFloatConsumer.class);
    }

    @Test
    public void testShoving() {
        assertThat(sut.shovingBiFloatCons())
            .isInstanceOf(LBiFloatConsumer.class);
    }

    @Test
    public void testNestingX() {
        assertThat(sut.nestingBiFloatConsX())
            .isInstanceOf(LBiFloatConsumerX.class);
    }

    @Test
    public void testShovingX() {
        assertThat(sut.shovingBiFloatConsX())
            .isInstanceOf(LBiFloatConsumerX.class);
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void testShove() {

        // given
        LBiFloatConsumerX<X> sutThrowing = LBiFloatConsumerX.lX((float a1,float a2) -> {
            throw new UnsupportedOperationException();
        });

        // when
        sutThrowing.shovingBiFloatCons().doAccept((float)100,(float)100);
    }

    @Test
    public void testHandleBiFloatCons() throws X {

        // given
        LBiFloatConsumerX<X> sutThrowing = LBiFloatConsumerX.lX((float a1,float a2) -> {
            throw new UnsupportedOperationException();
        });

        // when
        LBiFloatConsumerX<X> wrapped = sutThrowing.handleBiFloatConsX(h -> {
            h.wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED);
        });

        // then
        try {
            wrapped.doAccept((float)100,(float)100);
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
                .contains("LBiFloatConsumerX: void doAccept(float a1,float a2) throws X");
    }


    @Test
    public void isThrowing() {
        assertThat(sut.isThrowing())
            .isTrue();
    }

    //<editor-fold desc="Variants">

    private void variant1(float a2,float a1) {
    }

    @Test
    public void compilerSubstituteVariant1() {
        LBiFloatConsumerX lambda = LBiFloatConsumerX./*<X>*/lX1(this::variant1);

        assertThat(lambda).isInstanceOf(LBiFloatConsumerX.V1.class);
    }

    //</editor-fold>


    @Test void safeCompiles() {
        LBiFloatConsumerX r1 = LBiFloatConsumerX.safe(sut); //NOSONAR
    }

    @Test void safePropagates() {
        Object result = LBiFloatConsumerX.safe(sut);
        assertThat(result).isSameAs(sut);
    }

    @Test void safeProtectsAgainstNpe() {
        Object result = LBiFloatConsumerX.safe(null);
        assertThat(result).isSameAs(LBiFloatConsumerX.lX(LBiFloatConsumerX.safe()));
    }

    @Test <Y extends Throwable> void safeSupplierPropagates() {
        LSupplierX<LBiFloatConsumerX<X>,Y> supplier = ()->sut;
        Object result = LBiFloatConsumerX.safeSupplier(supplier);
        assertThat(result).isSameAs(supplier);
    }

    @Test <Y extends Throwable> void safeSupplierProtectsAgainstNpe() {
        Object result = LBiFloatConsumerX.safeSupplier(null);
        assertThat(result).isSameAs(LBiFloatConsumerX.safeSupplier());
    }

    @Test <Y extends Throwable> void safeSupplierCompiles() {
        LSupplierX<LBiFloatConsumerX<X>,Y> r1 = LBiFloatConsumerX.safeSupplier(()->sut);  //NOSONAR
    }

}
