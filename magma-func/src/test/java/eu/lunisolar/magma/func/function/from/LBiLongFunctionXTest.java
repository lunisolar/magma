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

package eu.lunisolar.magma.func.function.from;

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
public class LBiLongFunctionXTest<R,X extends ParseException> {
    private static final String ORIGINAL_MESSAGE = "Original message";
    private static final String EXCEPTION_WAS_WRAPPED = "Exception was wrapped.";
    private static final String NO_EXCEPTION_WERE_THROWN = "No exception were thrown.";

    private Object  testValue = (R)Integer.valueOf(100);



    private LBiLongFunctionX<R,X> sut = new LBiLongFunctionX(){
        public @Nullable Object  doApply(long a1,long a2) throws ParseException {
            return testValue;
        }
    };

    private LBiLongFunction<R> opposite = new LBiLongFunction(){
        public @Nullable Object  doApply(long a1,long a2)  {
            return testValue;
        }
    };

    private LBiLongFunctionX<R,X> sutNull = new LBiLongFunctionX(){
        public @Nullable Object  doApply(long a1,long a2) throws ParseException {
            return null;
        }
    };



    private LBiLongFunctionX<R,ParseException> sutAlwaysThrowing = LBiLongFunctionX.lX((long a1,long a2) -> {
            throw new ParseException(ORIGINAL_MESSAGE, 0);
    });

    private LBiLongFunctionX<R,RuntimeException> sutAlwaysThrowingUnckeck = LBiLongFunctionX.lX((long a1,long a2) -> {
            throw new IndexOutOfBoundsException(ORIGINAL_MESSAGE);
    });


    @Test
    public void testTheResult() throws X {
        assertThat(sut.doApply((long)100,(long)100))
            .isSameAs(testValue);
    }

    @Test
    public void testTupleCall() throws X {

        LLongPair domainObject = Tuple4U.tuple((long)100,(long)100);

        Object result = sut.tupleApply(domainObject);

        assertThat(result)
            .isSameAs(testValue);
    }

    @Test
    public void testNonNullDoApply() throws X {
        assertThat(sut.nonNullDoApply((long)100,(long)100))
            .isSameAs(testValue);
    }

    @Test
    public void testNestingDoApplyChecked() throws X {

        // then
        try {
            sutAlwaysThrowing.nestingDoApply((long)100,(long)100);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(NestedException.class)
                    .hasCauseExactlyInstanceOf(ParseException.class)
                    .hasMessage(ORIGINAL_MESSAGE);
        }
    }

    @Test
    public void testNestingDoApplyUnckeck() throws X {

        // then
        try {
            sutAlwaysThrowingUnckeck.nestingDoApply((long)100,(long)100);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                    .hasNoCause()
                    .hasMessage(ORIGINAL_MESSAGE);
        }
    }

    @Test
    public void testShovingDoApplyChecked() throws X {

        // then
        try {
            sutAlwaysThrowing.shovingDoApply((long)100,(long)100);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(ParseException.class)
                    .hasNoCause()
                    .hasMessage(ORIGINAL_MESSAGE);
        }
    }

    @Test
    public void testShovingDoApplyUnckeck() throws X {

        // then
        try {
            sutAlwaysThrowingUnckeck.shovingDoApply((long)100,(long)100);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                    .hasNoCause()
                    .hasMessage(ORIGINAL_MESSAGE);
        }
    }

    @Test(expectedExceptions=NullPointerException.class, expectedExceptionsMessageRegExp="\\QEvaluated value by nonNullDoApply() method cannot be null (LBiLongFunctionX: R doApply(long a1,long a2) throws X).\\E")
    public void testNonNullCapturesNull() throws X {
        sutNull.nonNullDoApply((long)100,(long)100);
    }


    @Test
    public void testFunctionalInterfaceDescription() throws X {
        assertThat(sut.functionalInterfaceDescription())
            .isEqualTo("LBiLongFunctionX: R doApply(long a1,long a2) throws X");
    }

    @Test
    public void testLXMethod() throws X {
        assertThat(LBiLongFunctionX.lX((long a1,long a2) -> testValue ))
            .isInstanceOf(LBiLongFunctionX.class);
    }

    @Test
    public void testWrapXMethod() throws X {
        assertThat(LBiLongFunctionX.wrapX(opposite))
            .isInstanceOf(LBiLongFunctionX.class);
    }


    @Test
    public void testWrapExceptionMethodWrapsTheException() throws X {

        // given
        LBiLongFunctionX<R,X> sutThrowing = LBiLongFunctionX.lX((long a1,long a2) -> {
            throw new UnsupportedOperationException();
        });

        // when
        LBiLongFunctionX<R,X> wrapped = sutThrowing.handleBiLongFuncX(handler -> handler
            .wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED));

        // then
        try {
            wrapped.doApply((long)100,(long)100);
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
        LBiLongFunctionX<R,X> sutThrowing = LBiLongFunctionX.lX((long a1,long a2) -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LBiLongFunctionX<R,X> wrapped = sutThrowing.handleBiLongFuncX(handler -> handler
                .wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED)
                .throwIf(IndexOutOfBoundsException.class));

        // then
        try {
            wrapped.doApply((long)100,(long)100);
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
        LBiLongFunctionX<R,X> sutThrowing = LBiLongFunctionX.lX((long a1,long a2) -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LBiLongFunctionX<R,X> wrapped = sutThrowing.handleBiLongFuncX(handler -> handler
                .wrapWhen(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED)
                .throwIf(IndexOutOfBoundsException.class));

        // then
        try {
            wrapped.doApply((long)100,(long)100);
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
        LBiLongFunctionX<R,X> sutThrowing = LBiLongFunctionX.lX((long a1,long a2) -> {
            throw (X) new ParseException(ORIGINAL_MESSAGE, 0);
        });

        // when
        LBiLongFunctionX<R,X> wrapped = sutThrowing.handleBiLongFuncX(h -> Function4U.doNothing());

        // then
        try {
            wrapped.doApply((long)100,(long)100);
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
    public void testbiLongFuncComposeLong() throws X {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LBiLongFunctionX<Integer ,X> sutO = (long a1,long a2) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo((long)90);
                assertThat(a2).isEqualTo((long)91);
                return 9;
        };

        LLongUnaryOperatorX<X> before1 = p0 -> {
            assertThat(p0).isEqualTo((long)80);
            beforeCalls.incrementAndGet();
            return (long)90;
        };
        LLongUnaryOperatorX<X> before2 = p1 -> {
            assertThat(p1).isEqualTo((long)81);
            beforeCalls.incrementAndGet();
            return (long)91;
        };

        //when
        LBiLongFunctionX<Integer ,X> function = sutO.biLongFuncComposeLong(before1,before2);
        function.doApply((long)80,(long)81);

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(beforeCalls.get()).isEqualTo(2);
    }


    @Test
    public void testbiLongFuncCompose() throws X {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LBiLongFunctionX<Integer ,X> sutO = (long a1,long a2) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo((long)90);
                assertThat(a2).isEqualTo((long)91);
                return 9;
        };

        LToLongFunctionX<Integer ,X> before1 = p0 -> {
            assertThat(p0).isEqualTo(Integer.valueOf(80));
            beforeCalls.incrementAndGet();
            return (long)90;
        };
        LToLongFunctionX<Integer ,X> before2 = p1 -> {
            assertThat(p1).isEqualTo(Integer.valueOf(81));
            beforeCalls.incrementAndGet();
            return (long)91;
        };

        //when
        LBiFunctionX<Integer ,Integer ,Integer ,X> function = sutO.biLongFuncCompose(before1,before2);
        function.doApply((Integer )Integer.valueOf(80),(Integer )Integer.valueOf(81));

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(beforeCalls.get()).isEqualTo(2);
    }

    // </editor-fold>


    // <editor-fold desc="then (functional)">

    @Test
    public void testThen0() throws X  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);


        //given (+ some assertions)
        LBiLongFunctionX<Integer ,X> sutO = (long a1,long a2) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo((long)80);
                assertThat(a2).isEqualTo((long)81);
                return Integer.valueOf(90);
        };

        LFunctionX<Integer ,Integer ,X> thenFunction = p -> {
                thenFunctionCalled.set(true);
                // R
                assertThat(p).isEqualTo(Integer.valueOf(90));
                // V
                return Integer.valueOf(100);
        };

        //when
        LBiLongFunctionX<Integer ,X> function = sutO.then(thenFunction);
        Integer  finalValue = function.doApply((long)80,(long)81);

        //then - finals
        assertThat(finalValue).isEqualTo(Integer.valueOf(100));
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    @Test
    public void testThen1() throws X  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);


        //given (+ some assertions)
        LBiLongFunctionX<Integer ,X> sutO = (long a1,long a2) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo((long)80);
                assertThat(a2).isEqualTo((long)81);
                return Integer.valueOf(90);
        };

        LConsumerX<Integer ,X> thenFunction = p -> {
                thenFunctionCalled.set(true);
                // R
                assertThat(p).isEqualTo(Integer.valueOf(90));
        };

        //when
        LBiLongConsumerX<X> function = sutO.then(thenFunction);
        function.doAccept((long)80,(long)81);

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    // </editor-fold>

    @Test
    public void testNesting() {
        assertThat(sut.nestingBiLongFunc())
            .isInstanceOf(LBiLongFunction.class);
    }

    @Test
    public void testShoving() {
        assertThat(sut.shovingBiLongFunc())
            .isInstanceOf(LBiLongFunction.class);
    }

    @Test
    public void testNestingX() {
        assertThat(sut.nestingBiLongFuncX())
            .isInstanceOf(LBiLongFunctionX.class);
    }

    @Test
    public void testShovingX() {
        assertThat(sut.shovingBiLongFuncX())
            .isInstanceOf(LBiLongFunctionX.class);
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void testShove() {

        // given
        LBiLongFunctionX<R,X> sutThrowing = LBiLongFunctionX.lX((long a1,long a2) -> {
            throw new UnsupportedOperationException();
        });

        // when
        sutThrowing.shovingBiLongFunc().doApply((long)100,(long)100);
    }

    @Test
    public void testHandleBiLongFunc() throws X {

        // given
        LBiLongFunctionX<R,X> sutThrowing = LBiLongFunctionX.lX((long a1,long a2) -> {
            throw new UnsupportedOperationException();
        });

        // when
        LBiLongFunctionX<R,X> wrapped = sutThrowing.handleBiLongFuncX(h -> {
            h.wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED);
        });

        // then
        try {
            wrapped.doApply((long)100,(long)100);
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
                .contains("LBiLongFunctionX: R doApply(long a1,long a2) throws X");
    }


    @Test
    public void isThrowing() {
        assertThat(sut.isThrowing())
            .isTrue();
    }

    //<editor-fold desc="Variants">

    private R variant1(long a2,long a1) {
        return (R)Integer.valueOf(100);
    }

    @Test
    public void compilerSubstituteVariant1() {
        LBiLongFunctionX lambda = LBiLongFunctionX./*<R,X>*/lX1(this::variant1);

        assertThat(lambda).isInstanceOf(LBiLongFunctionX.V1.class);
    }

    //</editor-fold>


    @Test void safeCompiles() {
        LBiLongFunctionX r1 = LBiLongFunctionX.safe(sut); //NOSONAR
    }

    @Test void safePropagates() {
        Object result = LBiLongFunctionX.safe(sut);
        assertThat(result).isSameAs(sut);
    }

    @Test void safeProtectsAgainstNpe() {
        Object result = LBiLongFunctionX.safe(null);
        assertThat(result).isSameAs(LBiLongFunctionX.lX(LBiLongFunctionX.safe()));
    }

    @Test <Y extends Throwable> void safeSupplierPropagates() {
        LSupplierX<LBiLongFunctionX<R,X>,Y> supplier = ()->sut;
        Object result = LBiLongFunctionX.safeSupplier(supplier);
        assertThat(result).isSameAs(supplier);
    }

    @Test <Y extends Throwable> void safeSupplierProtectsAgainstNpe() {
        Object result = LBiLongFunctionX.safeSupplier(null);
        assertThat(result).isSameAs(LBiLongFunctionX.safeSupplier());
    }

    @Test <Y extends Throwable> void safeSupplierCompiles() {
        LSupplierX<LBiLongFunctionX<R,X>,Y> r1 = LBiLongFunctionX.safeSupplier(()->sut);  //NOSONAR
    }

}
