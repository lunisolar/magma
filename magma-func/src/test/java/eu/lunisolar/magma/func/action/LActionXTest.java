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
public class LActionXTest<X extends ParseException> {
    private static final String ORIGINAL_MESSAGE = "Original message";
    private static final String EXCEPTION_WAS_WRAPPED = "Exception was wrapped.";
    private static final String NO_EXCEPTION_WERE_THROWN = "No exception were thrown.";



    private LActionX<X> sut = new LActionX(){
        public  void doExecute() throws ParseException {
            Function4U.doNothing();
        }
    };

    private LAction opposite = new LAction(){
        public  void doExecute()  {
            Function4U.doNothing();
        }
    };


    private Runnable jre = () -> Function4U.doNothing();


    private LActionX<ParseException> sutAlwaysThrowing = LActionX.lX(() -> {
            throw new ParseException(ORIGINAL_MESSAGE, 0);
    });

    private LActionX<RuntimeException> sutAlwaysThrowingUnckeck = LActionX.lX(() -> {
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
    public void testNestingDoExecuteChecked() throws X {

        // then
        try {
            sutAlwaysThrowing.nestingDoExecute();
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(NestedException.class)
                    .hasCauseExactlyInstanceOf(ParseException.class)
                    .hasMessage(ORIGINAL_MESSAGE);
        }
    }

    @Test
    public void testNestingDoExecuteUnckeck() throws X {

        // then
        try {
            sutAlwaysThrowingUnckeck.nestingDoExecute();
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                    .hasNoCause()
                    .hasMessage(ORIGINAL_MESSAGE);
        }
    }

    @Test
    public void testShovingDoExecuteChecked() throws X {

        // then
        try {
            sutAlwaysThrowing.shovingDoExecute();
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(ParseException.class)
                    .hasNoCause()
                    .hasMessage(ORIGINAL_MESSAGE);
        }
    }

    @Test
    public void testShovingDoExecuteUnckeck() throws X {

        // then
        try {
            sutAlwaysThrowingUnckeck.shovingDoExecute();
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
            .isEqualTo("LActionX: void doExecute() throws X");
    }

    @Test
    public void testLXMethod() throws X {
        assertThat(LActionX.lX(() -> Function4U.doNothing() ))
            .isInstanceOf(LActionX.class);
    }

    @Test
    public void testWrapXMethod() throws X {
        assertThat(LActionX.wrapX(opposite))
            .isInstanceOf(LActionX.class);
    }

    @Test
    public void testWrapStdMethod() throws X {
        assertThat(LActionX.wrap(jre))
            .isInstanceOf(LActionX.class);
    }


    @Test
    public void testWrapExceptionMethodWrapsTheException() throws X {

        // given
        LActionX<X> sutThrowing = LActionX.lX(() -> {
            throw new UnsupportedOperationException();
        });

        // when
        LActionX<X> wrapped = sutThrowing.handleActX(handler -> handler
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
    public void testWrapExceptionMethodDoNotWrapsOtherExceptionIf() throws X {

        // given
        LActionX<X> sutThrowing = LActionX.lX(() -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LActionX<X> wrapped = sutThrowing.handleActX(handler -> handler
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
    public void testWrapExceptionMethodDoNotWrapsOtherExceptionWhen() throws X {

        // given
        LActionX<X> sutThrowing = LActionX.lX(() -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LActionX<X> wrapped = sutThrowing.handleActX(handler -> handler
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
    public void testWrapExceptionMishandlingExceptionIsAllowed() throws X {

        // given
        LActionX<X> sutThrowing = LActionX.lX(() -> {
            throw (X) new ParseException(ORIGINAL_MESSAGE, 0);
        });

        // when
        LActionX<X> wrapped = sutThrowing.handleActX(h -> Function4U.doNothing());

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
    public void testAndThen() throws X {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);

         //given (+ some assertions)
        LActionX<X> sutO = () -> {
                mainFunctionCalled.set(true);
        };

        LActionX<X> thenFunction = () -> {
                thenFunctionCalled.set(true);
        };

        //when
        LActionX<X> function = sutO.andThen(thenFunction);
        function.doExecute();

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);
    }


    @Test
    public void testNesting() {
        assertThat(sut.nestingAct())
            .isInstanceOf(LAction.class);
    }

    @Test
    public void testShoving() {
        assertThat(sut.shovingAct())
            .isInstanceOf(LAction.class);
    }

    @Test
    public void testNestingX() {
        assertThat(sut.nestingActX())
            .isInstanceOf(LActionX.class);
    }

    @Test
    public void testShovingX() {
        assertThat(sut.shovingActX())
            .isInstanceOf(LActionX.class);
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void testShove() {

        // given
        LActionX<X> sutThrowing = LActionX.lX(() -> {
            throw new UnsupportedOperationException();
        });

        // when
        sutThrowing.shovingAct().doExecute();
    }

    @Test
    public void testHandleAct() throws X {

        // given
        LActionX<X> sutThrowing = LActionX.lX(() -> {
            throw new UnsupportedOperationException();
        });

        // when
        LActionX<X> wrapped = sutThrowing.handleActX(h -> {
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
                .contains("LActionX: void doExecute() throws X");
    }


    @Test
    public void isThrowing() {
        assertThat(sut.isThrowing())
            .isTrue();
    }

    @Test void safeCompiles() {
        LActionX r1 = LActionX.safe(sut); //NOSONAR
        Runnable r3 = LActionX.safe(sut); //NOSONAR
    }

    @Test void safePropagates() {
        Object result = LActionX.safe(sut);
        assertThat(result).isSameAs(sut);
    }

    @Test void safeProtectsAgainstNpe() {
        Object result = LActionX.safe(null);
        assertThat(result).isSameAs(LActionX.lX(LActionX.safe()));
    }

    @Test <Y extends Throwable> void safeSupplierPropagates() {
        LSupplierX<LActionX<X>,Y> supplier = ()->sut;
        Object result = LActionX.safeSupplier(supplier);
        assertThat(result).isSameAs(supplier);
    }

    @Test <Y extends Throwable> void safeSupplierProtectsAgainstNpe() {
        Object result = LActionX.safeSupplier(null);
        assertThat(result).isSameAs(LActionX.safeSupplier());
    }

    @Test <Y extends Throwable> void safeSupplierCompiles() {
        LSupplierX<LActionX<X>,Y> r1 = LActionX.safeSupplier(()->sut);  //NOSONAR
        Supplier<LActionX<X>> r2 = LActionX.safeSupplier(()->sut); //NOSONAR
    }

}
