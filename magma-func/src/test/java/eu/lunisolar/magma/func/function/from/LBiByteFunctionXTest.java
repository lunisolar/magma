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
public class LBiByteFunctionXTest<R,X extends ParseException> {
    private static final String ORIGINAL_MESSAGE = "Original message";
    private static final String EXCEPTION_WAS_WRAPPED = "Exception was wrapped.";
    private static final String NO_EXCEPTION_WERE_THROWN = "No exception were thrown.";

    private Object  testValue = (R)Integer.valueOf(100);



    private LBiByteFunctionX<R,X> sut = new LBiByteFunctionX(){
        public @Nullable Object  doApply(byte a1,byte a2) throws ParseException {
            return testValue;
        }
    };

    private LBiByteFunction<R> opposite = new LBiByteFunction(){
        public @Nullable Object  doApply(byte a1,byte a2)  {
            return testValue;
        }
    };

    private LBiByteFunctionX<R,X> sutNull = new LBiByteFunctionX(){
        public @Nullable Object  doApply(byte a1,byte a2) throws ParseException {
            return null;
        }
    };



    private LBiByteFunctionX<R,ParseException> sutAlwaysThrowing = LBiByteFunctionX.lX((byte a1,byte a2) -> {
            throw new ParseException(ORIGINAL_MESSAGE, 0);
    });

    private LBiByteFunctionX<R,RuntimeException> sutAlwaysThrowingUnckeck = LBiByteFunctionX.lX((byte a1,byte a2) -> {
            throw new IndexOutOfBoundsException(ORIGINAL_MESSAGE);
    });


    @Test
    public void testTheResult() throws X {
        assertThat(sut.doApply((byte)100,(byte)100))
            .isSameAs(testValue);
    }

    @Test
    public void testTupleCall() throws X {

        //FunctionalCall<LBytePair,R,X> theCall = sut;

        LBytePair domainObject = Tuple4U.tuple((byte)100,(byte)100);

        Object result = sut.tupleApply(domainObject);

        assertThat(result)
            .isSameAs(testValue);
    }

    @Test
    public void testNonNullDoApply() throws X {
        assertThat(sut.nonNullDoApply((byte)100,(byte)100))
            .isSameAs(testValue);
    }

    @Test
    public void testNestingDoApplyChecked() throws X {

        // then
        try {
            sutAlwaysThrowing.nestingDoApply((byte)100,(byte)100);
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
            sutAlwaysThrowingUnckeck.nestingDoApply((byte)100,(byte)100);
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
            sutAlwaysThrowing.shovingDoApply((byte)100,(byte)100);
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
            sutAlwaysThrowingUnckeck.shovingDoApply((byte)100,(byte)100);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                    .hasNoCause()
                    .hasMessage(ORIGINAL_MESSAGE);
        }
    }

    @Test(expectedExceptions=NullPointerException.class, expectedExceptionsMessageRegExp="\\QEvaluated value by nonNullDoApply() method cannot be null (LBiByteFunctionX: R doApply(byte a1,byte a2) throws X).\\E")
    public void testNonNullCapturesNull() throws X {
        sutNull.nonNullDoApply((byte)100,(byte)100);
    }


    @Test
    public void testFunctionalInterfaceDescription() throws X {
        assertThat(sut.functionalInterfaceDescription())
            .isEqualTo("LBiByteFunctionX: R doApply(byte a1,byte a2) throws X");
    }

    @Test
    public void testLXMethod() throws X {
        assertThat(LBiByteFunctionX.lX((byte a1,byte a2) -> testValue ))
            .isInstanceOf(LBiByteFunctionX.class);
    }

    @Test
    public void testWrapXMethod() throws X {
        assertThat(LBiByteFunctionX.wrapX(opposite))
            .isInstanceOf(LBiByteFunctionX.class);
    }


    @Test
    public void testWrapExceptionMethodWrapsTheException() throws X {

        // given
        LBiByteFunctionX<R,X> sutThrowing = LBiByteFunctionX.lX((byte a1,byte a2) -> {
            throw new UnsupportedOperationException();
        });

        // when
        LBiByteFunctionX<R,X> wrapped = sutThrowing.handleBiByteFuncX(handler -> handler
            .wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED));

        // then
        try {
            wrapped.doApply((byte)100,(byte)100);
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
        LBiByteFunctionX<R,X> sutThrowing = LBiByteFunctionX.lX((byte a1,byte a2) -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LBiByteFunctionX<R,X> wrapped = sutThrowing.handleBiByteFuncX(handler -> handler
                .wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED)
                .throwIf(IndexOutOfBoundsException.class));

        // then
        try {
            wrapped.doApply((byte)100,(byte)100);
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
        LBiByteFunctionX<R,X> sutThrowing = LBiByteFunctionX.lX((byte a1,byte a2) -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LBiByteFunctionX<R,X> wrapped = sutThrowing.handleBiByteFuncX(handler -> handler
                .wrapWhen(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED)
                .throwIf(IndexOutOfBoundsException.class));

        // then
        try {
            wrapped.doApply((byte)100,(byte)100);
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
        LBiByteFunctionX<R,X> sutThrowing = LBiByteFunctionX.lX((byte a1,byte a2) -> {
            throw (X) new ParseException(ORIGINAL_MESSAGE, 0);
        });

        // when
        LBiByteFunctionX<R,X> wrapped = sutThrowing.handleBiByteFuncX(h -> Function4U.doNothing());

        // then
        try {
            wrapped.doApply((byte)100,(byte)100);
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
    public void testbiByteFuncComposeByte() throws X {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LBiByteFunctionX<Integer ,X> sutO = (byte a1,byte a2) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo((byte)90);
                assertThat(a2).isEqualTo((byte)91);
                return 9;
        };

        LByteUnaryOperatorX<X> before1 = p0 -> {
            assertThat(p0).isEqualTo((byte)80);
            beforeCalls.incrementAndGet();
            return (byte)90;
        };
        LByteUnaryOperatorX<X> before2 = p1 -> {
            assertThat(p1).isEqualTo((byte)81);
            beforeCalls.incrementAndGet();
            return (byte)91;
        };

        //when
        LBiByteFunctionX<Integer ,X> function = sutO.biByteFuncComposeByte(before1,before2);
        function.doApply((byte)80,(byte)81);

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(beforeCalls.get()).isEqualTo(2);
    }


    @Test
    public void testbiByteFuncCompose() throws X {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LBiByteFunctionX<Integer ,X> sutO = (byte a1,byte a2) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo((byte)90);
                assertThat(a2).isEqualTo((byte)91);
                return 9;
        };

        LToByteFunctionX<Integer ,X> before1 = p0 -> {
            assertThat(p0).isEqualTo(Integer.valueOf(80));
            beforeCalls.incrementAndGet();
            return (byte)90;
        };
        LToByteFunctionX<Integer ,X> before2 = p1 -> {
            assertThat(p1).isEqualTo(Integer.valueOf(81));
            beforeCalls.incrementAndGet();
            return (byte)91;
        };

        //when
        LBiFunctionX<Integer ,Integer ,Integer ,X> function = sutO.biByteFuncCompose(before1,before2);
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
        LBiByteFunctionX<Integer ,X> sutO = (byte a1,byte a2) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo((byte)80);
                assertThat(a2).isEqualTo((byte)81);
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
        LBiByteFunctionX<Integer ,X> function = sutO.then(thenFunction);
        Integer  finalValue = function.doApply((byte)80,(byte)81);

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
        LBiByteFunctionX<Integer ,X> sutO = (byte a1,byte a2) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo((byte)80);
                assertThat(a2).isEqualTo((byte)81);
                return Integer.valueOf(90);
        };

        LConsumerX<Integer ,X> thenFunction = p -> {
                thenFunctionCalled.set(true);
                // R
                assertThat(p).isEqualTo(Integer.valueOf(90));
        };

        //when
        LBiByteConsumerX<X> function = sutO.then(thenFunction);
        function.doAccept((byte)80,(byte)81);

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    // </editor-fold>

    @Test
    public void testNesting() {
        assertThat(sut.nestingBiByteFunc())
            .isInstanceOf(LBiByteFunction.class);
    }

    @Test
    public void testShoving() {
        assertThat(sut.shovingBiByteFunc())
            .isInstanceOf(LBiByteFunction.class);
    }

    @Test
    public void testNestingX() {
        assertThat(sut.nestingBiByteFuncX())
            .isInstanceOf(LBiByteFunctionX.class);
    }

    @Test
    public void testShovingX() {
        assertThat(sut.shovingBiByteFuncX())
            .isInstanceOf(LBiByteFunctionX.class);
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void testShove() {

        // given
        LBiByteFunctionX<R,X> sutThrowing = LBiByteFunctionX.lX((byte a1,byte a2) -> {
            throw new UnsupportedOperationException();
        });

        // when
        sutThrowing.shovingBiByteFunc().doApply((byte)100,(byte)100);
    }

    @Test
    public void testHandleBiByteFunc() throws X {

        // given
        LBiByteFunctionX<R,X> sutThrowing = LBiByteFunctionX.lX((byte a1,byte a2) -> {
            throw new UnsupportedOperationException();
        });

        // when
        LBiByteFunctionX<R,X> wrapped = sutThrowing.handleBiByteFuncX(h -> {
            h.wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED);
        });

        // then
        try {
            wrapped.doApply((byte)100,(byte)100);
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
                .contains("LBiByteFunctionX: R doApply(byte a1,byte a2) throws X");
    }


    @Test
    public void isThrowing() {
        assertThat(sut.isThrowing())
            .isTrue();
    }

    //<editor-fold desc="Variants">

    private R variant1(byte a2,byte a1) {
        return (R)Integer.valueOf(100);
    }

    @Test
    public void compilerSubstituteVariant1() {
        LBiByteFunctionX lambda = LBiByteFunctionX./*<R,X>*/lX1(this::variant1);

        assertThat(lambda).isInstanceOf(LBiByteFunctionX.V1.class);
    }

    //</editor-fold>


    @Test void safeCompiles() {
        LBiByteFunctionX r1 = LBiByteFunctionX.safe(sut);
    }

    @Test void safePropagates() {
        Object result = LBiByteFunctionX.safe(sut);
        assertThat(result).isSameAs(sut);
    }

    @Test void safeProtectsAgainstNpe() {
        Object result = LBiByteFunctionX.safe(null);
        assertThat(result).isSameAs(LBiByteFunctionX.lX(LBiByteFunctionX.safe()));
    }

    @Test <Y extends Throwable> void safeSupplierPropagates() {
        LSupplierX<LBiByteFunctionX<R,X>,Y> supplier = ()->sut;
        Object result = LBiByteFunctionX.safeSupplier(supplier);
        assertThat(result).isSameAs(supplier);
    }

    @Test <Y extends Throwable> void safeSupplierProtectsAgainstNpe() {
        Object result = LBiByteFunctionX.safeSupplier(null);
        assertThat(result).isSameAs(LBiByteFunctionX.safeSupplier());
    }

    @Test <Y extends Throwable> void safeSupplierCompiles() {
        LSupplierX<LBiByteFunctionX<R,X>,Y> r1 = LBiByteFunctionX.safeSupplier(()->sut);
    }

}
