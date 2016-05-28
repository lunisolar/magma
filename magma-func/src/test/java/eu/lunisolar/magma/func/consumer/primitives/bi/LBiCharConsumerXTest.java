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
public class LBiCharConsumerXTest<X extends ParseException> {
    private static final String ORIGINAL_MESSAGE = "Original message";
    private static final String EXCEPTION_WAS_WRAPPED = "Exception was wrapped.";
    private static final String NO_EXCEPTION_WERE_THROWN = "No exception were thrown.";



    private LBiCharConsumerX<X> sut = new LBiCharConsumerX<X>(){
        public  void doAccept(char a1,char a2)  throws X {
            Function4U.doNothing();
        }
    };

    private LBiCharConsumer opposite = new LBiCharConsumer(){
        public  void doAccept(char a1,char a2)  {
            Function4U.doNothing();
        }
    };



    private LBiCharConsumerX<ParseException> sutAlwaysThrowing = LBiCharConsumerX.lX((a1,a2) -> {
            throw new ParseException(ORIGINAL_MESSAGE, 0);
    });

    private LBiCharConsumerX<RuntimeException> sutAlwaysThrowingUnchecked = LBiCharConsumerX.lX((a1,a2) -> {
            throw new IndexOutOfBoundsException(ORIGINAL_MESSAGE);
    });



    @Test
    public void testTupleCall() throws X {

        LCharPair domainObject = Tuple4U.charPair('\u0100','\u0100');

        Object result = sut.tupleAccept(domainObject);

        assertThat(result)
            .isSameAs(LTuple.Void.INSTANCE);
    }

    @Test
    public void testNestingDoAcceptChecked() throws X {

        // then
        try {
            sutAlwaysThrowing.nestingDoAccept('\u0100','\u0100');
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(NestedException.class)
                    .hasCauseExactlyInstanceOf(ParseException.class)
                    .hasMessage(ORIGINAL_MESSAGE);
        }
    }

    @Test
    public void testNestingDoAcceptUnchecked() throws X {

        // then
        try {
            sutAlwaysThrowingUnchecked.nestingDoAccept('\u0100','\u0100');
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
            sutAlwaysThrowing.shovingDoAccept('\u0100','\u0100');
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(ParseException.class)
                    .hasNoCause()
                    .hasMessage(ORIGINAL_MESSAGE);
        }
    }

    @Test
    public void testShovingDoAcceptUnchecked() throws X {

        // then
        try {
            sutAlwaysThrowingUnchecked.shovingDoAccept('\u0100','\u0100');
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
            .isEqualTo("LBiCharConsumerX: void doAccept(char a1,char a2) throws X");
    }

    @Test
    public void testLXMethod() throws X {
        assertThat(LBiCharConsumerX.lX(Function4U::doNothing))
            .isInstanceOf(LBiCharConsumerX.class);
    }

    @Test
    public void testWrapXMethod() throws X {
        assertThat(LBiCharConsumerX.wrapX(opposite))
            .isInstanceOf(LBiCharConsumerX.class);
    }


    @Test
    public void testHandlingDoAcceptMethodWrapsTheException() throws X {

        // given
        LBiCharConsumerX<X> sutThrowing = LBiCharConsumerX.lX((a1,a2) -> {
            throw new UnsupportedOperationException();
        });

        // when
        LBiCharConsumerX<RuntimeException> wrapped = sutThrowing.handleBiCharConsX(handler -> handler
            .wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED));

        // then
        try {
            wrapped.doAccept('\u0100','\u0100');
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IllegalArgumentException.class)
                    .hasCauseExactlyInstanceOf(UnsupportedOperationException.class)
                    .hasMessage(EXCEPTION_WAS_WRAPPED);
        }
    }

    @Test
    public void testHandleBiCharConsXMethodDoNotWrapsOtherExceptionIf() throws X {

        // given
        LBiCharConsumerX<X> sutThrowing = LBiCharConsumerX.lX((a1,a2) -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LBiCharConsumerX<X> wrapped = sutThrowing.handleBiCharConsX(handler -> handler
                .wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED)
                .throwIf(IndexOutOfBoundsException.class));

        // then
        try {
            wrapped.doAccept('\u0100','\u0100');
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                    .hasNoCause();
        }
    }

@Test
    public void testHandleBiCharConsXMethodDoNotWrapsOtherExceptionWhen() throws X {

        // given
        LBiCharConsumerX<X> sutThrowing = LBiCharConsumerX.lX((a1,a2) -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LBiCharConsumerX<X> wrapped = sutThrowing.handleBiCharConsX(handler -> handler
                .wrapWhen(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED)
                .throwIf(IndexOutOfBoundsException.class));

        // then
        try {
            wrapped.doAccept('\u0100','\u0100');
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                    .hasNoCause();
        }
    }


    @Test
    public void testHandleBiCharConsXMishandlingExceptionIsAllowed() throws X {

        // given
        LBiCharConsumerX<X> sutThrowing = LBiCharConsumerX.lX((a1,a2) -> {
            throw (X) new ParseException(ORIGINAL_MESSAGE, 0);
        });

        // when
        LBiCharConsumerX<X> wrapped = sutThrowing.handleBiCharConsX(h -> Function4U.doNothing());

        // then
        try {
            wrapped.doAccept('\u0100','\u0100');
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
    public void testBiCharConsComposeChar() throws X {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LBiCharConsumerX<X> sutO = (a1,a2) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo('\u0090');
                assertThat(a2).isEqualTo('\u0091');
        };

        LCharUnaryOperatorX<X> before1 = p0 -> {
            assertThat(p0).isEqualTo('\u0080');
            beforeCalls.incrementAndGet();
            return '\u0090';
        };
        LCharUnaryOperatorX<X> before2 = p1 -> {
            assertThat(p1).isEqualTo('\u0081');
            beforeCalls.incrementAndGet();
            return '\u0091';
        };

        //when
        LBiCharConsumerX<X> function = sutO.biCharConsComposeChar(before1,before2);
        function.doAccept('\u0080','\u0081');

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(beforeCalls.get()).isEqualTo(2);
    }


    @Test
    public void testBiCharConsCompose() throws X {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LBiCharConsumerX<X> sutO = (a1,a2) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo('\u0090');
                assertThat(a2).isEqualTo('\u0091');
        };

        LToCharFunctionX<Integer,X> before1 = p0 -> {
            assertThat(p0).isEqualTo(80);
            beforeCalls.incrementAndGet();
            return '\u0090';
        };
        LToCharFunctionX<Integer,X> before2 = p1 -> {
            assertThat(p1).isEqualTo(81);
            beforeCalls.incrementAndGet();
            return '\u0091';
        };

        //when
        LBiConsumerX<Integer,Integer,X> function = sutO.biCharConsCompose(before1,before2);
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
        LBiCharConsumerX<X> sutO = (a1,a2) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo('\u0080');
                assertThat(a2).isEqualTo('\u0081');
        };

        LBiCharConsumerX<X> thenFunction = (a1,a2) -> {
                thenFunctionCalled.set(true);
                assertThat(a1).isEqualTo('\u0080');
                assertThat(a2).isEqualTo('\u0081');
        };

        //when
        LBiCharConsumerX<X> function = sutO.andThen(thenFunction);
        function.doAccept('\u0080','\u0081');

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);
    }


    @Test
    public void testNesting() {
        assertThat(sut.nestingBiCharCons())
            .isInstanceOf(LBiCharConsumer.class);
    }

    @Test
    public void testShoving() {
        assertThat(sut.shovingBiCharCons())
            .isInstanceOf(LBiCharConsumer.class);
    }

    @Test
    public void testNestingX() {
        assertThat(sut.nestingBiCharConsX())
            .isInstanceOf(LBiCharConsumerX.class);
    }

    @Test
    public void testShovingX() {
        assertThat(sut.shovingBiCharConsX())
            .isInstanceOf(LBiCharConsumerX.class);
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void testShove() {

        // given
        LBiCharConsumerX<X> sutThrowing = LBiCharConsumerX.lX((a1,a2) -> {
            throw new UnsupportedOperationException();
        });

        // when
        sutThrowing.shovingBiCharCons().doAccept('\u0100','\u0100');
    }

    @Test
    public void testHandleBiCharCons() throws X {

        // given
        LBiCharConsumerX<X> sutThrowing = LBiCharConsumerX.lX((a1,a2) -> {
            throw new UnsupportedOperationException();
        });

        // when
        LBiCharConsumerX<X> wrapped = sutThrowing.handleBiCharConsX(h -> {
            h.wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED);
        });

        // then
        try {
            wrapped.doAccept('\u0100','\u0100');
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
                .contains("LBiCharConsumerX: void doAccept(char a1,char a2) throws X");
    }


    @Test
    public void isThrowing() {
        assertThat(sut.isThrowing())
            .isTrue();
    }

    //<editor-fold desc="Variants">

    private void variantV1(char a2,char a1) {
    }

    @Test
    public void compilerSubstituteVariantV1() {
        LBiCharConsumerX lambda = LBiCharConsumerX./*<X>*/lX1(this::variantV1);

        assertThat(lambda).isInstanceOf(LBiCharConsumerX.V1.class);
    }

    //</editor-fold>


    @Test void safeCompiles() {
        LBiCharConsumerX r1 = LBiCharConsumerX.safe(sut); //NOSONAR
    }

    @Test void safePropagates() {
        Object result = LBiCharConsumerX.safe(sut);
        assertThat(result).isSameAs(sut);
    }

    @Test void safeProtectsAgainstNpe() {
        Object result = LBiCharConsumerX.safe(null);
        assertThat(result).isSameAs(LBiCharConsumerX.lX(LBiCharConsumerX.safe()));
    }

    @Test <Y extends Throwable> void safeSupplierPropagates() {
        LSupplierX<LBiCharConsumerX<X>,Y> supplier = ()->sut;
        Object result = LBiCharConsumerX.safeSupplier(supplier);
        assertThat(result).isSameAs(supplier);
    }

    @Test <Y extends Throwable> void safeSupplierProtectsAgainstNpe() {
        Object result = LBiCharConsumerX.safeSupplier(null);
        assertThat(result).isSameAs(LBiCharConsumerX.safeSupplier());
    }

    @Test <Y extends Throwable> void safeSupplierCompiles() {
        LSupplierX<LBiCharConsumerX<X>,Y> r1 = LBiCharConsumerX.safeSupplier(()->sut);  //NOSONAR
    }

}
