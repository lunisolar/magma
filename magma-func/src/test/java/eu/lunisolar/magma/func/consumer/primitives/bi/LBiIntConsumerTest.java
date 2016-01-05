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
public class LBiIntConsumerTest<X extends ParseException> {
    private static final String ORIGINAL_MESSAGE = "Original message";
    private static final String EXCEPTION_WAS_WRAPPED = "Exception was wrapped.";
    private static final String NO_EXCEPTION_WERE_THROWN = "No exception were thrown.";



    private LBiIntConsumer sut = new LBiIntConsumer(){
        public  void doAccept(int a1,int a2)  {
            Function4U.doNothing();
        }
    };

    private LBiIntConsumerX<X> opposite = new LBiIntConsumerX(){
        public  void doAccept(int a1,int a2) throws ParseException {
            Function4U.doNothing();
        }
    };




    private LBiIntConsumer sutAlwaysThrowingUnckeck = LBiIntConsumer.l((int a1,int a2) -> {
            throw new IndexOutOfBoundsException(ORIGINAL_MESSAGE);
    });



    @Test
    public void testTupleCall() throws X {

        //FunctionalCall<LIntPair,LTuple.Void,RuntimeException> theCall = sut;

        LIntPair domainObject = Tuple4U.tuple((int)100,(int)100);

        Object result = sut.tupleAccept(domainObject);

        assertThat(result)
            .isSameAs(LTuple.Void.INSTANCE);
    }

    @Test
    public void testNestingDoAcceptUnckeck() throws X {

        // then
        try {
            sutAlwaysThrowingUnckeck.nestingDoAccept((int)100,(int)100);
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
            sutAlwaysThrowingUnckeck.shovingDoAccept((int)100,(int)100);
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
            .isEqualTo("LBiIntConsumer: void doAccept(int a1,int a2)");
    }

    @Test
    public void testLMethod() throws X {
        assertThat(LBiIntConsumer.l((int a1,int a2) -> Function4U.doNothing() ))
            .isInstanceOf(LBiIntConsumer.class);
    }

    @Test
    public void testWrapMethod() throws X {
        assertThat(LBiIntConsumer.wrap(opposite))
            .isInstanceOf(LBiIntConsumer.class);
    }

    @Test
    public void testWrapMethodDoNotWrapsRuntimeException() throws X {
        // given
        LBiIntConsumerX<X> sutThrowing = LBiIntConsumerX.lX((int a1,int a2) -> {
            throw new UnsupportedOperationException(ORIGINAL_MESSAGE);
        });

        // when
        LBiIntConsumer wrapped = LBiIntConsumer.wrap(sutThrowing);

        // then
        try {
            wrapped.doAccept((int)100,(int)100);
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
        LBiIntConsumerX<ParseException> sutThrowing = LBiIntConsumerX.lX((int a1,int a2) -> {
            throw new ParseException(ORIGINAL_MESSAGE, 0);
        });

        // when
        LBiIntConsumer wrapped = LBiIntConsumer.wrap(sutThrowing);

        // then
        try {
            wrapped.doAccept((int)100,(int)100);
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
        LBiIntConsumer sutThrowing = LBiIntConsumer.l((int a1,int a2) -> {
            throw new UnsupportedOperationException();
        });

        // when
        LBiIntConsumer wrapped = sutThrowing.handleBiIntCons(handler -> handler
            .wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED));

        // then
        try {
            wrapped.doAccept((int)100,(int)100);
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
        LBiIntConsumer sutThrowing = LBiIntConsumer.l((int a1,int a2) -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LBiIntConsumer wrapped = sutThrowing.handleBiIntCons(handler -> handler
                .wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED)
                .throwIf(IndexOutOfBoundsException.class));

        // then
        try {
            wrapped.doAccept((int)100,(int)100);
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
        LBiIntConsumer sutThrowing = LBiIntConsumer.l((int a1,int a2) -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LBiIntConsumer wrapped = sutThrowing.handleBiIntCons(handler -> handler
                .wrapWhen(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED)
                .throwIf(IndexOutOfBoundsException.class));

        // then
        try {
            wrapped.doAccept((int)100,(int)100);
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
        LBiIntConsumer sutThrowing = LBiIntConsumer.l((int a1,int a2) -> {
            throw new UnsupportedOperationException(ORIGINAL_MESSAGE);
        });

        // when
        LBiIntConsumer wrapped = sutThrowing.handleBiIntCons(h -> Function4U.doNothing());

        // then
        try {
            wrapped.doAccept((int)100,(int)100);
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
    public void testbiIntConsComposeInt() throws X {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LBiIntConsumer sutO = (int a1,int a2) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo((int)90);
                assertThat(a2).isEqualTo((int)91);
        };

        LIntUnaryOperator before1 = p0 -> {
            assertThat(p0).isEqualTo((int)80);
            beforeCalls.incrementAndGet();
            return (int)90;
        };
        LIntUnaryOperator before2 = p1 -> {
            assertThat(p1).isEqualTo((int)81);
            beforeCalls.incrementAndGet();
            return (int)91;
        };

        //when
        LBiIntConsumer function = sutO.biIntConsComposeInt(before1,before2);
        function.doAccept((int)80,(int)81);

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(beforeCalls.get()).isEqualTo(2);
    }


    @Test
    public void testbiIntConsCompose() throws X {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LBiIntConsumer sutO = (int a1,int a2) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo((int)90);
                assertThat(a2).isEqualTo((int)91);
        };

        LToIntFunction<Integer > before1 = p0 -> {
            assertThat(p0).isEqualTo(Integer.valueOf(80));
            beforeCalls.incrementAndGet();
            return (int)90;
        };
        LToIntFunction<Integer > before2 = p1 -> {
            assertThat(p1).isEqualTo(Integer.valueOf(81));
            beforeCalls.incrementAndGet();
            return (int)91;
        };

        //when
        LBiConsumer<Integer ,Integer > function = sutO.biIntConsCompose(before1,before2);
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
        LBiIntConsumer sutO = (int a1,int a2) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo((int)80);
                assertThat(a2).isEqualTo((int)81);
        };

        LBiIntConsumer thenFunction = (int a1,int a2) -> {
                thenFunctionCalled.set(true);
                assertThat(a1).isEqualTo((int)80);
                assertThat(a2).isEqualTo((int)81);
        };

        //when
        LBiIntConsumer function = sutO.andThen(thenFunction);
        function.doAccept((int)80,(int)81);

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);
    }


    @Test
    public void testNesting() {
        assertThat(sut.nestingBiIntCons())
            .isSameAs(sut)
            .isInstanceOf(LBiIntConsumer.class);
    }

    @Test
    public void testShoving() {
        assertThat(sut.shovingBiIntCons())
            .isSameAs(sut)
            .isInstanceOf(LBiIntConsumer.class);
    }

    @Test
    public void testNestingX() {
        assertThat(sut.nestingBiIntConsX())
            .isSameAs(sut)
            .isInstanceOf(LBiIntConsumerX.class);
    }

    @Test
    public void testShovingX() {
        assertThat(sut.shovingBiIntConsX())
            .isSameAs(sut)
            .isInstanceOf(LBiIntConsumerX.class);
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void testShove() {

        // given
        LBiIntConsumer sutThrowing = LBiIntConsumer.l((int a1,int a2) -> {
            throw new UnsupportedOperationException();
        });

        // when
        sutThrowing.shovingBiIntCons().doAccept((int)100,(int)100);
    }

    @Test
    public void testHandleBiIntCons() throws X {

        // given
        LBiIntConsumer sutThrowing = LBiIntConsumer.l((int a1,int a2) -> {
            throw new UnsupportedOperationException();
        });

        // when
        LBiIntConsumer wrapped = sutThrowing.handleBiIntCons(h -> {
            h.wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED);
        });

        // then
        try {
            wrapped.doAccept((int)100,(int)100);
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
                .contains("LBiIntConsumer: void doAccept(int a1,int a2)");
    }


    @Test
    public void isThrowing() {
        assertThat(sut.isThrowing())
            .isFalse();
    }

    //<editor-fold desc="Variants">

    private void variant1(int a2,int a1) {
    }

    @Test
    public void compilerSubstituteVariant1() {
        LBiIntConsumer lambda = LBiIntConsumer./**/l1(this::variant1);

        assertThat(lambda).isInstanceOf(LBiIntConsumer.V1.class);
    }

    //</editor-fold>


    @Test void safeCompiles() {
        LBiIntConsumer r1 = LBiIntConsumer.safe(sut);
        LBiIntConsumerX r2 = LBiIntConsumer.safe(sut);
    }

    @Test void safePropagates() {
        Object result = LBiIntConsumer.safe(sut);
        assertThat(result).isSameAs(sut);
    }

    @Test void safeProtectsAgainstNpe() {
        Object result = LBiIntConsumer.safe(null);
        assertThat(result).isSameAs(LBiIntConsumer.l(LBiIntConsumer.safe()));
    }

    @Test  void safeSupplierPropagates() {
        LSupplier<LBiIntConsumer> supplier = ()->sut;
        Object result = LBiIntConsumer.safeSupplier(supplier);
        assertThat(result).isSameAs(supplier);
    }

    @Test  void safeSupplierProtectsAgainstNpe() {
        Object result = LBiIntConsumer.safeSupplier(null);
        assertThat(result).isSameAs(LBiIntConsumer.safeSupplier());
    }

    @Test  void safeSupplierCompiles() {
        LSupplier<LBiIntConsumer> r1 = LBiIntConsumer.safeSupplier(()->sut);
    }

}
