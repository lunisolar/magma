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

package eu.lunisolar.magma.func.consumer;

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
public class LConsumerXTest<T,X extends ParseException> {
    private static final String ORIGINAL_MESSAGE = "Original message";
    private static final String EXCEPTION_WAS_WRAPPED = "Exception was wrapped.";
    private static final String NO_EXCEPTION_WERE_THROWN = "No exception were thrown.";



    private LConsumerX<Integer,X> sut = new LConsumerX<Integer,X>(){
        public  void doAccept(Integer a)  throws X {
            Function4U.doNothing();
        }
    };

    private LConsumer<Integer> opposite = new LConsumer<Integer>(){
        public  void doAccept(Integer a)  {
            Function4U.doNothing();
        }
    };


    private Consumer<Integer> jre = a -> Function4U.doNothing();


    private LConsumerX<Integer,ParseException> sutAlwaysThrowing = LConsumerX.lX(a -> {
            throw new ParseException(ORIGINAL_MESSAGE, 0);
    });

    private LConsumerX<Integer,RuntimeException> sutAlwaysThrowingUnchecked = LConsumerX.lX(a -> {
            throw new IndexOutOfBoundsException(ORIGINAL_MESSAGE);
    });



    @Test
    public void testTupleCall() throws X {

        LSingle<Integer> domainObject = Tuple4U.single(100);

        Object result = sut.tupleAccept(domainObject);

        assertThat(result)
            .isSameAs(LTuple.Void.INSTANCE);
    }

    @Test
    public void testNestingDoAcceptChecked() throws X {

        // then
        try {
            sutAlwaysThrowing.nestingDoAccept(100);
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
            sutAlwaysThrowingUnchecked.nestingDoAccept(100);
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
            sutAlwaysThrowing.shovingDoAccept(100);
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
            sutAlwaysThrowingUnchecked.shovingDoAccept(100);
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
            .isEqualTo("LConsumerX: void doAccept(T a) throws X");
    }

    @Test
    public void testLXMethod() throws X {
        assertThat(LConsumerX.lX(Function4U::doNothing))
            .isInstanceOf(LConsumerX.class);
    }

    @Test
    public void testWrapXMethod() throws X {
        assertThat(LConsumerX.wrapX(opposite))
            .isInstanceOf(LConsumerX.class);
    }

    @Test
    public void testWrapStdMethod() throws X {
        assertThat(LConsumerX.wrap(jre))
            .isInstanceOf(LConsumerX.class);
    }


    @Test
    public void testHandlingDoAcceptMethodWrapsTheException() throws X {

        // given
        LConsumerX<Integer,X> sutThrowing = LConsumerX.lX(a -> {
            throw new UnsupportedOperationException();
        });

        // when
        LConsumerX<Integer,RuntimeException> wrapped = sutThrowing.handleConsX(handler -> handler
            .wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED));

        // then
        try {
            wrapped.doAccept(100);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IllegalArgumentException.class)
                    .hasCauseExactlyInstanceOf(UnsupportedOperationException.class)
                    .hasMessage(EXCEPTION_WAS_WRAPPED);
        }
    }

    @Test
    public void testHandleConsXMethodDoNotWrapsOtherExceptionIf() throws X {

        // given
        LConsumerX<Integer,X> sutThrowing = LConsumerX.lX(a -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LConsumerX<Integer,X> wrapped = sutThrowing.handleConsX(handler -> handler
                .wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED)
                .throwIf(IndexOutOfBoundsException.class));

        // then
        try {
            wrapped.doAccept(100);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                    .hasNoCause();
        }
    }

@Test
    public void testHandleConsXMethodDoNotWrapsOtherExceptionWhen() throws X {

        // given
        LConsumerX<Integer,X> sutThrowing = LConsumerX.lX(a -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LConsumerX<Integer,X> wrapped = sutThrowing.handleConsX(handler -> handler
                .wrapWhen(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED)
                .throwIf(IndexOutOfBoundsException.class));

        // then
        try {
            wrapped.doAccept(100);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                    .hasNoCause();
        }
    }


    @Test
    public void testHandleConsXMishandlingExceptionIsAllowed() throws X {

        // given
        LConsumerX<Integer,X> sutThrowing = LConsumerX.lX(a -> {
            throw (X) new ParseException(ORIGINAL_MESSAGE, 0);
        });

        // when
        LConsumerX<Integer,X> wrapped = sutThrowing.handleConsX(h -> Function4U.doNothing());

        // then
        try {
            wrapped.doAccept(100);
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
    public void testConsCompose() throws X {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LConsumerX<Integer,X> sutO = a -> {
                mainFunctionCalled.set(true);
                assertThat(a).isEqualTo(90);
        };

        LFunctionX<Integer,Integer,X> before = p0 -> {
            assertThat(p0).isEqualTo(80);
            beforeCalls.incrementAndGet();
            return 90;
        };

        //when
        LConsumerX<Integer,X> function = sutO.consCompose(before);
        function.doAccept(80);

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(beforeCalls.get()).isEqualTo(1);
    }

    // </editor-fold>

    @Test
    public void testAndThen() throws X {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);

         //given (+ some assertions)
        LConsumerX<Integer,X> sutO = a -> {
                mainFunctionCalled.set(true);
                assertThat(a).isEqualTo(80);
        };

        LConsumerX<Integer,X> thenFunction = a -> {
                thenFunctionCalled.set(true);
                assertThat(a).isEqualTo(80);
        };

        //when
        LConsumerX<Integer,X> function = sutO.andThen(thenFunction);
        function.doAccept(80);

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);
    }


    @Test
    public void testNesting() {
        assertThat(sut.nestingCons())
            .isInstanceOf(LConsumer.class);
    }

    @Test
    public void testShoving() {
        assertThat(sut.shovingCons())
            .isInstanceOf(LConsumer.class);
    }

    @Test
    public void testNestingX() {
        assertThat(sut.nestingConsX())
            .isInstanceOf(LConsumerX.class);
    }

    @Test
    public void testShovingX() {
        assertThat(sut.shovingConsX())
            .isInstanceOf(LConsumerX.class);
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void testShove() {

        // given
        LConsumerX<Integer,X> sutThrowing = LConsumerX.lX(a -> {
            throw new UnsupportedOperationException();
        });

        // when
        sutThrowing.shovingCons().doAccept(100);
    }

    @Test
    public void testHandleCons() throws X {

        // given
        LConsumerX<Integer,X> sutThrowing = LConsumerX.lX(a -> {
            throw new UnsupportedOperationException();
        });

        // when
        LConsumerX<Integer,X> wrapped = sutThrowing.handleConsX(h -> {
            h.wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED);
        });

        // then
        try {
            wrapped.doAccept(100);
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
                .contains("LConsumerX: void doAccept(T a) throws X");
    }


    @Test
    public void isThrowing() {
        assertThat(sut.isThrowing())
            .isTrue();
    }

    @Test void safeCompiles() {
        LConsumerX r1 = LConsumerX.safe(sut); //NOSONAR
        Consumer r3 = LConsumerX.safe(sut); //NOSONAR
    }

    @Test void safePropagates() {
        Object result = LConsumerX.safe(sut);
        assertThat(result).isSameAs(sut);
    }

    @Test void safeProtectsAgainstNpe() {
        Object result = LConsumerX.safe(null);
        assertThat(result).isSameAs(LConsumerX.lX(LConsumerX.safe()));
    }

    @Test <Y extends Throwable> void safeSupplierPropagates() {
        LSupplierX<LConsumerX<Integer,X>,Y> supplier = ()->sut;
        Object result = LConsumerX.safeSupplier(supplier);
        assertThat(result).isSameAs(supplier);
    }

    @Test <Y extends Throwable> void safeSupplierProtectsAgainstNpe() {
        Object result = LConsumerX.safeSupplier(null);
        assertThat(result).isSameAs(LConsumerX.safeSupplier());
    }

    @Test <Y extends Throwable> void safeSupplierCompiles() {
        LSupplierX<LConsumerX<Integer,X>,Y> r1 = LConsumerX.safeSupplier(()->sut);  //NOSONAR
        Supplier<LConsumerX<Integer,X>> r2 = LConsumerX.safeSupplier(()->sut); //NOSONAR
    }

}
