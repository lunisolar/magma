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

package eu.lunisolar.magma.func.action;

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
public class LActionTest<X extends ParseException> {
    private static final String ORIGINAL_MESSAGE = "Original message";
    private static final String EXCEPTION_WAS_WRAPPED = "Exception was wrapped.";
    private static final String NO_EXCEPTION_WERE_THROWN = "No exception were thrown.";



    private LAction sut = new LAction(){
        public  void doExecute()  {
            Function4U.doNothing();
        }
    };

    private LActionX<X> opposite = new LActionX<X>(){
        public  void doExecute()  throws X {
            Function4U.doNothing();
        }
    };


    private Runnable jre = () -> Function4U.doNothing();



    private LActionX<RuntimeException> sutAlwaysThrowingUnchecked = LAction.l(() -> {
            throw new IndexOutOfBoundsException(ORIGINAL_MESSAGE);
    });



    @Test
    public void testTupleCall() throws X {

        LTuple.Void domainObject = Tuple4U.tuple();

        Object result = sut.tupleExecute(domainObject);

        assertThat(result)
            .isSameAs(LTuple.Void.INSTANCE);
    }

    @Test
    public void testNestingDoExecuteUnchecked() throws X {

        // then
        try {
            sutAlwaysThrowingUnchecked.nestingDoExecute();
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                    .hasNoCause()
                    .hasMessage(ORIGINAL_MESSAGE);
        }
    }

    @Test
    public void testShovingDoExecuteUnchecked() throws X {

        // then
        try {
            sutAlwaysThrowingUnchecked.shovingDoExecute();
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
            .isEqualTo("LAction: void doExecute()");
    }

    @Test
    public void testLMethod() throws X {
        assertThat(LAction.l(Function4U::doNothing))
            .isInstanceOf(LAction.class);
    }

    @Test
    public void testWrapMethod() throws X {
        assertThat(LAction.wrap(opposite))
            .isInstanceOf(LAction.class);
    }

    @Test
    public void testWrapStdMethod() throws X {
        assertThat(LAction.wrap(jre))
            .isInstanceOf(LAction.class);
    }

    @Test
    public void testWrapMethodDoNotWrapsRuntimeException() throws X {
        // given
        LActionX<X> sutThrowing = LActionX.lX(() -> {
            throw new UnsupportedOperationException(ORIGINAL_MESSAGE);
        });

        // when
        LAction wrapped = LAction.wrap(sutThrowing);

        // then
        try {
            wrapped.doExecute();
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
        LActionX<ParseException> sutThrowing = LActionX.lX(() -> {
            throw new ParseException(ORIGINAL_MESSAGE, 0);
        });

        // when
        LAction wrapped = LAction.wrap(sutThrowing);

        // then
        try {
            wrapped.doExecute();
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(NestedException.class)
                    .hasCauseExactlyInstanceOf(ParseException.class)
                    .hasMessage(ORIGINAL_MESSAGE);
        }
    }


    @Test
    public void testHandlingDoExecuteMethodWrapsTheException() throws X {

        // given
        LAction sutThrowing = LAction.l(() -> {
            throw new UnsupportedOperationException();
        });

        // when
        LAction wrapped = sutThrowing.handleAct(handler -> handler
            .wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED));

        // then
        try {
            wrapped.doExecute();
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IllegalArgumentException.class)
                    .hasCauseExactlyInstanceOf(UnsupportedOperationException.class)
                    .hasMessage(EXCEPTION_WAS_WRAPPED);
        }
    }

    @Test
    public void testHandleActMethodDoNotWrapsOtherExceptionIf() throws X {

        // given
        LAction sutThrowing = LAction.l(() -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LAction wrapped = sutThrowing.handleAct(handler -> handler
                .wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED)
                .throwIf(IndexOutOfBoundsException.class));

        // then
        try {
            wrapped.doExecute();
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                    .hasNoCause();
        }
    }

@Test
    public void testHandleActMethodDoNotWrapsOtherExceptionWhen() throws X {

        // given
        LAction sutThrowing = LAction.l(() -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LAction wrapped = sutThrowing.handleAct(handler -> handler
                .wrapWhen(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED)
                .throwIf(IndexOutOfBoundsException.class));

        // then
        try {
            wrapped.doExecute();
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                    .hasNoCause();
        }
    }


    @Test
    public void testHandleActMishandlingExceptionIsAllowed() throws X {

        // given
        LAction sutThrowing = LAction.l(() -> {
            throw new UnsupportedOperationException(ORIGINAL_MESSAGE);
        });

        // when
        LAction wrapped = sutThrowing.handleAct(h -> Function4U.doNothing());

        // then
        try {
            wrapped.doExecute();
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
             .isExactlyInstanceOf(UnsupportedOperationException.class)
             .hasNoCause()
             .hasMessage(ORIGINAL_MESSAGE);
        }
    }


    @Test
    public void testAndThen() throws X {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);

         //given (+ some assertions)
        LAction sutO = () -> {
                mainFunctionCalled.set(true);
        };

        LAction thenFunction = () -> {
                thenFunctionCalled.set(true);
        };

        //when
        LAction function = sutO.andThen(thenFunction);
        function.doExecute();

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);
    }


    @Test
    public void testNesting() {
        assertThat(sut.nestingAct())
            .isSameAs(sut)
            .isInstanceOf(LAction.class);
    }

    @Test
    public void testShoving() {
        assertThat(sut.shovingAct())
            .isSameAs(sut)
            .isInstanceOf(LAction.class);
    }

    @Test
    public void testNestingX() {
        assertThat(sut.nestingActX())
            .isSameAs(sut)
            .isInstanceOf(LActionX.class);
    }

    @Test
    public void testShovingX() {
        assertThat(sut.shovingActX())
            .isSameAs(sut)
            .isInstanceOf(LActionX.class);
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void testShove() {

        // given
        LAction sutThrowing = LAction.l(() -> {
            throw new UnsupportedOperationException();
        });

        // when
        sutThrowing.shovingAct().doExecute();
    }

    @Test
    public void testHandleAct() throws X {

        // given
        LAction sutThrowing = LAction.l(() -> {
            throw new UnsupportedOperationException();
        });

        // when
        LAction wrapped = sutThrowing.handleAct(h -> {
            h.wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED);
        });

        // then
        try {
            wrapped.doExecute();
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
                .contains("LAction: void doExecute()");
    }


    @Test
    public void isThrowing() {
        assertThat(sut.isThrowing())
            .isFalse();
    }

    @Test void safeCompiles() {
        LAction r1 = LAction.safe(sut); //NOSONAR
        LActionX r2 = LAction.safe(sut); //NOSONAR
        Runnable r3 = LAction.safe(sut); //NOSONAR
    }

    @Test void safePropagates() {
        Object result = LAction.safe(sut);
        assertThat(result).isSameAs(sut);
    }

    @Test void safeProtectsAgainstNpe() {
        Object result = LAction.safe(null);
        assertThat(result).isSameAs(LAction.l(LAction.safe()));
    }

    @Test  void safeSupplierPropagates() {
        LSupplier<LAction> supplier = ()->sut;
        Object result = LAction.safeSupplier(supplier);
        assertThat(result).isSameAs(supplier);
    }

    @Test  void safeSupplierProtectsAgainstNpe() {
        Object result = LAction.safeSupplier(null);
        assertThat(result).isSameAs(LAction.safeSupplier());
    }

    @Test  void safeSupplierCompiles() {
        LSupplier<LAction> r1 = LAction.safeSupplier(()->sut);  //NOSONAR
        Supplier<LAction> r2 = LAction.safeSupplier(()->sut); //NOSONAR
    }

}
