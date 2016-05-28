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

package eu.lunisolar.magma.func.function.conversion;

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
public class LBoolToDoubleFunctionXTest<X extends ParseException> {
    private static final String ORIGINAL_MESSAGE = "Original message";
    private static final String EXCEPTION_WAS_WRAPPED = "Exception was wrapped.";
    private static final String NO_EXCEPTION_WERE_THROWN = "No exception were thrown.";

    private double testValue = 100d;



    private LBoolToDoubleFunctionX<X> sut = new LBoolToDoubleFunctionX<X>(){
        public  double doApplyAsDouble(boolean a)  throws X {
            return testValue;
        }
    };

    private LBoolToDoubleFunction opposite = new LBoolToDoubleFunction(){
        public  double doApplyAsDouble(boolean a)  {
            return testValue;
        }
    };



    private LBoolToDoubleFunctionX<ParseException> sutAlwaysThrowing = LBoolToDoubleFunctionX.lX(a -> {
            throw new ParseException(ORIGINAL_MESSAGE, 0);
    });

    private LBoolToDoubleFunctionX<RuntimeException> sutAlwaysThrowingUnchecked = LBoolToDoubleFunctionX.lX(a -> {
            throw new IndexOutOfBoundsException(ORIGINAL_MESSAGE);
    });


    @Test
    public void testTheResult() throws X {
        assertThat(sut.doApplyAsDouble(true))
            .isEqualTo(testValue);
    }

    @Test
    public void testTupleCall() throws X {

        LBoolSingle domainObject = Tuple4U.boolSingle(true);

        Object result = sut.tupleApplyAsDouble(domainObject);

        assertThat(result)
            .isEqualTo(testValue);
    }

    @Test
    public void testNonNullDoApplyAsDouble() throws X {
        assertThat(sut.nonNullDoApplyAsDouble(true))
            .isEqualTo(testValue);
    }

    @Test
    public void testNestingDoApplyAsDoubleChecked() throws X {

        // then
        try {
            sutAlwaysThrowing.nestingDoApplyAsDouble(true);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(NestedException.class)
                    .hasCauseExactlyInstanceOf(ParseException.class)
                    .hasMessage(ORIGINAL_MESSAGE);
        }
    }

    @Test
    public void testNestingDoApplyAsDoubleUnchecked() throws X {

        // then
        try {
            sutAlwaysThrowingUnchecked.nestingDoApplyAsDouble(true);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                    .hasNoCause()
                    .hasMessage(ORIGINAL_MESSAGE);
        }
    }

    @Test
    public void testShovingDoApplyAsDoubleChecked() throws X {

        // then
        try {
            sutAlwaysThrowing.shovingDoApplyAsDouble(true);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(ParseException.class)
                    .hasNoCause()
                    .hasMessage(ORIGINAL_MESSAGE);
        }
    }

    @Test
    public void testShovingDoApplyAsDoubleUnchecked() throws X {

        // then
        try {
            sutAlwaysThrowingUnchecked.shovingDoApplyAsDouble(true);
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
            .isEqualTo("LBoolToDoubleFunctionX: double doApplyAsDouble(boolean a) throws X");
    }

    @Test
    public void testLXMethod() throws X {
        assertThat(LBoolToDoubleFunctionX.lX(a -> testValue ))
            .isInstanceOf(LBoolToDoubleFunctionX.class);
    }

    @Test
    public void testWrapXMethod() throws X {
        assertThat(LBoolToDoubleFunctionX.wrapX(opposite))
            .isInstanceOf(LBoolToDoubleFunctionX.class);
    }


    @Test
    public void testHandlingDoApplyAsDoubleMethodWrapsTheException() throws X {

        // given
        LBoolToDoubleFunctionX<X> sutThrowing = LBoolToDoubleFunctionX.lX(a -> {
            throw new UnsupportedOperationException();
        });

        // when
        LBoolToDoubleFunctionX<RuntimeException> wrapped = sutThrowing.handleBoolToDoubleFuncX(handler -> handler
            .wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED));

        // then
        try {
            wrapped.doApplyAsDouble(true);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IllegalArgumentException.class)
                    .hasCauseExactlyInstanceOf(UnsupportedOperationException.class)
                    .hasMessage(EXCEPTION_WAS_WRAPPED);
        }
    }

    @Test
    public void testHandleBoolToDoubleFuncXMethodDoNotWrapsOtherExceptionIf() throws X {

        // given
        LBoolToDoubleFunctionX<X> sutThrowing = LBoolToDoubleFunctionX.lX(a -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LBoolToDoubleFunctionX<X> wrapped = sutThrowing.handleBoolToDoubleFuncX(handler -> handler
                .wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED)
                .throwIf(IndexOutOfBoundsException.class));

        // then
        try {
            wrapped.doApplyAsDouble(true);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                    .hasNoCause();
        }
    }

@Test
    public void testHandleBoolToDoubleFuncXMethodDoNotWrapsOtherExceptionWhen() throws X {

        // given
        LBoolToDoubleFunctionX<X> sutThrowing = LBoolToDoubleFunctionX.lX(a -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LBoolToDoubleFunctionX<X> wrapped = sutThrowing.handleBoolToDoubleFuncX(handler -> handler
                .wrapWhen(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED)
                .throwIf(IndexOutOfBoundsException.class));

        // then
        try {
            wrapped.doApplyAsDouble(true);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                    .hasNoCause();
        }
    }


    @Test
    public void testHandleBoolToDoubleFuncXMishandlingExceptionIsAllowed() throws X {

        // given
        LBoolToDoubleFunctionX<X> sutThrowing = LBoolToDoubleFunctionX.lX(a -> {
            throw (X) new ParseException(ORIGINAL_MESSAGE, 0);
        });

        // when
        LBoolToDoubleFunctionX<X> wrapped = sutThrowing.handleBoolToDoubleFuncX(h -> Function4U.doNothing());

        // then
        try {
            wrapped.doApplyAsDouble(true);
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
    public void testBoolToDoubleFuncComposeBool() throws X {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LBoolToDoubleFunctionX<X> sutO = a -> {
                mainFunctionCalled.set(true);
                assertThat(a).isEqualTo(true);
                return 100d;
        };

        LLogicalOperatorX<X> before = p0 -> {
            assertThat(p0).isEqualTo(true);
            beforeCalls.incrementAndGet();
            return true;
        };

        //when
        LBoolToDoubleFunctionX<X> function = sutO.boolToDoubleFuncComposeBool(before);
        function.doApplyAsDouble(true);

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(beforeCalls.get()).isEqualTo(1);
    }


    @Test
    public void testBoolToDoubleFuncCompose() throws X {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LBoolToDoubleFunctionX<X> sutO = a -> {
                mainFunctionCalled.set(true);
                assertThat(a).isEqualTo(true);
                return 100d;
        };

        LPredicateX<Integer,X> before = p0 -> {
            assertThat(p0).isEqualTo(80);
            beforeCalls.incrementAndGet();
            return true;
        };

        //when
        LToDoubleFunctionX<Integer,X> function = sutO.boolToDoubleFuncCompose(before);
        function.doApplyAsDouble(80);

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(beforeCalls.get()).isEqualTo(1);
    }

    // </editor-fold>



    // <editor-fold desc="then (functional)">

    @Test
    public void testThen0() throws X  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);

        //given (+ some assertions)
        LBoolToDoubleFunctionX<X> sutO = a -> {
                mainFunctionCalled.set(true);
                assertThat(a).isEqualTo(true);
                return 90d;
        };

        LDoubleFunctionX<Integer,X> thenFunction = p -> {
                thenFunctionCalled.set(true);
                // double
                assertThat(p).isEqualTo(90d);
                // Integer
                return 100;
        };

        //when
        LBoolFunctionX<Integer,X> function = sutO.then(thenFunction);
        Integer finalValue = function.doApply(true);

        //then - finals
        assertThat(finalValue).isEqualTo(100);
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    @Test
    public void testThenToByte1() throws X  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);

        //given (+ some assertions)
        LBoolToDoubleFunctionX<X> sutO = a -> {
                mainFunctionCalled.set(true);
                assertThat(a).isEqualTo(true);
                return 90d;
        };

        LDoubleToByteFunctionX<X> thenFunction = p -> {
                thenFunctionCalled.set(true);
                // double
                assertThat(p).isEqualTo(90d);
                // byte
                return (byte)100;
        };

        //when
        LBoolToByteFunctionX<X> function = sutO.thenToByte(thenFunction);
        byte finalValue = function.doApplyAsByte(true);

        //then - finals
        assertThat(finalValue).isEqualTo((byte)100);
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    @Test
    public void testThenToShort2() throws X  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);

        //given (+ some assertions)
        LBoolToDoubleFunctionX<X> sutO = a -> {
                mainFunctionCalled.set(true);
                assertThat(a).isEqualTo(true);
                return 90d;
        };

        LDoubleToShortFunctionX<X> thenFunction = p -> {
                thenFunctionCalled.set(true);
                // double
                assertThat(p).isEqualTo(90d);
                // short
                return (short)100;
        };

        //when
        LBoolToShortFunctionX<X> function = sutO.thenToShort(thenFunction);
        short finalValue = function.doApplyAsShort(true);

        //then - finals
        assertThat(finalValue).isEqualTo((short)100);
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    @Test
    public void testThenToInt3() throws X  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);

        //given (+ some assertions)
        LBoolToDoubleFunctionX<X> sutO = a -> {
                mainFunctionCalled.set(true);
                assertThat(a).isEqualTo(true);
                return 90d;
        };

        LDoubleToIntFunctionX<X> thenFunction = p -> {
                thenFunctionCalled.set(true);
                // double
                assertThat(p).isEqualTo(90d);
                // int
                return 100;
        };

        //when
        LBoolToIntFunctionX<X> function = sutO.thenToInt(thenFunction);
        int finalValue = function.doApplyAsInt(true);

        //then - finals
        assertThat(finalValue).isEqualTo(100);
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    @Test
    public void testThenToLong4() throws X  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);

        //given (+ some assertions)
        LBoolToDoubleFunctionX<X> sutO = a -> {
                mainFunctionCalled.set(true);
                assertThat(a).isEqualTo(true);
                return 90d;
        };

        LDoubleToLongFunctionX<X> thenFunction = p -> {
                thenFunctionCalled.set(true);
                // double
                assertThat(p).isEqualTo(90d);
                // long
                return 100L;
        };

        //when
        LBoolToLongFunctionX<X> function = sutO.thenToLong(thenFunction);
        long finalValue = function.doApplyAsLong(true);

        //then - finals
        assertThat(finalValue).isEqualTo(100L);
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    @Test
    public void testThenToFloat5() throws X  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);

        //given (+ some assertions)
        LBoolToDoubleFunctionX<X> sutO = a -> {
                mainFunctionCalled.set(true);
                assertThat(a).isEqualTo(true);
                return 90d;
        };

        LDoubleToFloatFunctionX<X> thenFunction = p -> {
                thenFunctionCalled.set(true);
                // double
                assertThat(p).isEqualTo(90d);
                // float
                return 100f;
        };

        //when
        LBoolToFloatFunctionX<X> function = sutO.thenToFloat(thenFunction);
        float finalValue = function.doApplyAsFloat(true);

        //then - finals
        assertThat(finalValue).isEqualTo(100f);
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    @Test
    public void testThenToDouble6() throws X  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);

        //given (+ some assertions)
        LBoolToDoubleFunctionX<X> sutO = a -> {
                mainFunctionCalled.set(true);
                assertThat(a).isEqualTo(true);
                return 90d;
        };

        LDoubleUnaryOperatorX<X> thenFunction = p -> {
                thenFunctionCalled.set(true);
                // double
                assertThat(p).isEqualTo(90d);
                // double
                return 100d;
        };

        //when
        LBoolToDoubleFunctionX<X> function = sutO.thenToDouble(thenFunction);
        double finalValue = function.doApplyAsDouble(true);

        //then - finals
        assertThat(finalValue).isEqualTo(100d);
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    @Test
    public void testThenToChar7() throws X  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);

        //given (+ some assertions)
        LBoolToDoubleFunctionX<X> sutO = a -> {
                mainFunctionCalled.set(true);
                assertThat(a).isEqualTo(true);
                return 90d;
        };

        LDoubleToCharFunctionX<X> thenFunction = p -> {
                thenFunctionCalled.set(true);
                // double
                assertThat(p).isEqualTo(90d);
                // char
                return '\u0100';
        };

        //when
        LBoolToCharFunctionX<X> function = sutO.thenToChar(thenFunction);
        char finalValue = function.doApplyAsChar(true);

        //then - finals
        assertThat(finalValue).isEqualTo('\u0100');
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    @Test
    public void testThenToBool8() throws X  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);

        //given (+ some assertions)
        LBoolToDoubleFunctionX<X> sutO = a -> {
                mainFunctionCalled.set(true);
                assertThat(a).isEqualTo(true);
                return 90d;
        };

        LDoublePredicateX<X> thenFunction = p -> {
                thenFunctionCalled.set(true);
                // double
                assertThat(p).isEqualTo(90d);
                // boolean
                return true;
        };

        //when
        LLogicalOperatorX<X> function = sutO.thenToBool(thenFunction);
        boolean finalValue = function.doApply(true);

        //then - finals
        assertThat(finalValue).isEqualTo(true);
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    // </editor-fold>

    @Test
    public void testNesting() {
        assertThat(sut.nestingBoolToDoubleFunc())
            .isInstanceOf(LBoolToDoubleFunction.class);
    }

    @Test
    public void testShoving() {
        assertThat(sut.shovingBoolToDoubleFunc())
            .isInstanceOf(LBoolToDoubleFunction.class);
    }

    @Test
    public void testNestingX() {
        assertThat(sut.nestingBoolToDoubleFuncX())
            .isInstanceOf(LBoolToDoubleFunctionX.class);
    }

    @Test
    public void testShovingX() {
        assertThat(sut.shovingBoolToDoubleFuncX())
            .isInstanceOf(LBoolToDoubleFunctionX.class);
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void testShove() {

        // given
        LBoolToDoubleFunctionX<X> sutThrowing = LBoolToDoubleFunctionX.lX(a -> {
            throw new UnsupportedOperationException();
        });

        // when
        sutThrowing.shovingBoolToDoubleFunc().doApplyAsDouble(true);
    }

    @Test
    public void testHandleBoolToDoubleFunc() throws X {

        // given
        LBoolToDoubleFunctionX<X> sutThrowing = LBoolToDoubleFunctionX.lX(a -> {
            throw new UnsupportedOperationException();
        });

        // when
        LBoolToDoubleFunctionX<X> wrapped = sutThrowing.handleBoolToDoubleFuncX(h -> {
            h.wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED);
        });

        // then
        try {
            wrapped.doApplyAsDouble(true);
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
                .contains("LBoolToDoubleFunctionX: double doApplyAsDouble(boolean a) throws X");
    }


    @Test
    public void isThrowing() {
        assertThat(sut.isThrowing())
            .isTrue();
    }

    @Test void safeCompiles() {
        LBoolToDoubleFunctionX r1 = LBoolToDoubleFunctionX.safe(sut); //NOSONAR
    }

    @Test void safePropagates() {
        Object result = LBoolToDoubleFunctionX.safe(sut);
        assertThat(result).isSameAs(sut);
    }

    @Test void safeProtectsAgainstNpe() {
        Object result = LBoolToDoubleFunctionX.safe(null);
        assertThat(result).isSameAs(LBoolToDoubleFunctionX.lX(LBoolToDoubleFunctionX.safe()));
    }

    @Test <Y extends Throwable> void safeSupplierPropagates() {
        LSupplierX<LBoolToDoubleFunctionX<X>,Y> supplier = ()->sut;
        Object result = LBoolToDoubleFunctionX.safeSupplier(supplier);
        assertThat(result).isSameAs(supplier);
    }

    @Test <Y extends Throwable> void safeSupplierProtectsAgainstNpe() {
        Object result = LBoolToDoubleFunctionX.safeSupplier(null);
        assertThat(result).isSameAs(LBoolToDoubleFunctionX.safeSupplier());
    }

    @Test <Y extends Throwable> void safeSupplierCompiles() {
        LSupplierX<LBoolToDoubleFunctionX<X>,Y> r1 = LBoolToDoubleFunctionX.safeSupplier(()->sut);  //NOSONAR
    }

}
