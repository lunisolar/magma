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
public class LLongToShortFunctionXTest<X extends ParseException> {
    private static final String ORIGINAL_MESSAGE = "Original message";
    private static final String EXCEPTION_WAS_WRAPPED = "Exception was wrapped.";
    private static final String NO_EXCEPTION_WERE_THROWN = "No exception were thrown.";

    private short testValue = (short)100;



    private LLongToShortFunctionX<X> sut = new LLongToShortFunctionX<X>(){
        public  short doApplyAsShort(long a1)  throws X {
            return testValue;
        }
    };

    private LLongToShortFunction opposite = new LLongToShortFunction(){
        public  short doApplyAsShort(long a1)  {
            return testValue;
        }
    };



    private LLongToShortFunctionX<ParseException> sutAlwaysThrowing = LLongToShortFunctionX.lX(a1 -> {
            throw new ParseException(ORIGINAL_MESSAGE, 0);
    });

    private LLongToShortFunctionX<RuntimeException> sutAlwaysThrowingUnchecked = LLongToShortFunctionX.lX(a1 -> {
            throw new IndexOutOfBoundsException(ORIGINAL_MESSAGE);
    });


    @Test
    public void testTheResult() throws X {
        assertThat(sut.doApplyAsShort(100L))
            .isEqualTo(testValue);
    }

    @Test
    public void testTupleCall() throws X {

        LLongSingle domainObject = Tuple4U.longSingle(100L);

        Object result = sut.tupleApplyAsShort(domainObject);

        assertThat(result)
            .isEqualTo(testValue);
    }

    @Test
    public void testNonNullDoApplyAsShort() throws X {
        assertThat(sut.nonNullDoApplyAsShort(100L))
            .isEqualTo(testValue);
    }

    @Test
    public void testNestingDoApplyAsShortChecked() throws X {

        // then
        try {
            sutAlwaysThrowing.nestingDoApplyAsShort(100L);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(NestedException.class)
                    .hasCauseExactlyInstanceOf(ParseException.class)
                    .hasMessage(ORIGINAL_MESSAGE);
        }
    }

    @Test
    public void testNestingDoApplyAsShortUnchecked() throws X {

        // then
        try {
            sutAlwaysThrowingUnchecked.nestingDoApplyAsShort(100L);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                    .hasNoCause()
                    .hasMessage(ORIGINAL_MESSAGE);
        }
    }

    @Test
    public void testShovingDoApplyAsShortChecked() throws X {

        // then
        try {
            sutAlwaysThrowing.shovingDoApplyAsShort(100L);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(ParseException.class)
                    .hasNoCause()
                    .hasMessage(ORIGINAL_MESSAGE);
        }
    }

    @Test
    public void testShovingDoApplyAsShortUnchecked() throws X {

        // then
        try {
            sutAlwaysThrowingUnchecked.shovingDoApplyAsShort(100L);
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
            .isEqualTo("LLongToShortFunctionX: short doApplyAsShort(long a1) throws X");
    }

    @Test
    public void testLXMethod() throws X {
        assertThat(LLongToShortFunctionX.lX(a1 -> testValue ))
            .isInstanceOf(LLongToShortFunctionX.class);
    }

    @Test
    public void testWrapXMethod() throws X {
        assertThat(LLongToShortFunctionX.wrapX(opposite))
            .isInstanceOf(LLongToShortFunctionX.class);
    }


    @Test
    public void testHandlingDoApplyAsShortMethodWrapsTheException() throws X {

        // given
        LLongToShortFunctionX<X> sutThrowing = LLongToShortFunctionX.lX(a1 -> {
            throw new UnsupportedOperationException();
        });

        // when
        LLongToShortFunctionX<RuntimeException> wrapped = sutThrowing.handleLongToShortFuncX(handler -> handler
            .wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED));

        // then
        try {
            wrapped.doApplyAsShort(100L);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IllegalArgumentException.class)
                    .hasCauseExactlyInstanceOf(UnsupportedOperationException.class)
                    .hasMessage(EXCEPTION_WAS_WRAPPED);
        }
    }

    @Test
    public void testHandleLongToShortFuncXMethodDoNotWrapsOtherExceptionIf() throws X {

        // given
        LLongToShortFunctionX<X> sutThrowing = LLongToShortFunctionX.lX(a1 -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LLongToShortFunctionX<X> wrapped = sutThrowing.handleLongToShortFuncX(handler -> handler
                .wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED)
                .throwIf(IndexOutOfBoundsException.class));

        // then
        try {
            wrapped.doApplyAsShort(100L);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                    .hasNoCause();
        }
    }

@Test
    public void testHandleLongToShortFuncXMethodDoNotWrapsOtherExceptionWhen() throws X {

        // given
        LLongToShortFunctionX<X> sutThrowing = LLongToShortFunctionX.lX(a1 -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LLongToShortFunctionX<X> wrapped = sutThrowing.handleLongToShortFuncX(handler -> handler
                .wrapWhen(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED)
                .throwIf(IndexOutOfBoundsException.class));

        // then
        try {
            wrapped.doApplyAsShort(100L);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                    .hasNoCause();
        }
    }


    @Test
    public void testHandleLongToShortFuncXMishandlingExceptionIsAllowed() throws X {

        // given
        LLongToShortFunctionX<X> sutThrowing = LLongToShortFunctionX.lX(a1 -> {
            throw (X) new ParseException(ORIGINAL_MESSAGE, 0);
        });

        // when
        LLongToShortFunctionX<X> wrapped = sutThrowing.handleLongToShortFuncX(h -> Function4U.doNothing());

        // then
        try {
            wrapped.doApplyAsShort(100L);
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
    public void testLongToShortFuncComposeLong() throws X {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LLongToShortFunctionX<X> sutO = a1 -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(90L);
                return (short)100;
        };

        LLongUnaryOperatorX<X> before1 = p0 -> {
            assertThat(p0).isEqualTo(80L);
            beforeCalls.incrementAndGet();
            return 90L;
        };

        //when
        LLongToShortFunctionX<X> function = sutO.longToShortFuncComposeLong(before1);
        function.doApplyAsShort(80L);

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(beforeCalls.get()).isEqualTo(1);
    }


    @Test
    public void testLongToShortFuncCompose() throws X {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LLongToShortFunctionX<X> sutO = a1 -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(90L);
                return (short)100;
        };

        LToLongFunctionX<Integer,X> before1 = p0 -> {
            assertThat(p0).isEqualTo(80);
            beforeCalls.incrementAndGet();
            return 90L;
        };

        //when
        LToShortFunctionX<Integer,X> function = sutO.longToShortFuncCompose(before1);
        function.doApplyAsShort(80);

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
        LLongToShortFunctionX<X> sutO = a1 -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(80L);
                return (short)90;
        };

        LShortFunctionX<Integer,X> thenFunction = p -> {
                thenFunctionCalled.set(true);
                // short
                assertThat(p).isEqualTo((short)90);
                // Integer
                return 100;
        };

        //when
        LLongFunctionX<Integer,X> function = sutO.then(thenFunction);
        Integer finalValue = function.doApply(80L);

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
        LLongToShortFunctionX<X> sutO = a1 -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(80L);
                return (short)90;
        };

        LShortToByteFunctionX<X> thenFunction = p -> {
                thenFunctionCalled.set(true);
                // short
                assertThat(p).isEqualTo((short)90);
                // byte
                return (byte)100;
        };

        //when
        LLongToByteFunctionX<X> function = sutO.thenToByte(thenFunction);
        byte finalValue = function.doApplyAsByte(80L);

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
        LLongToShortFunctionX<X> sutO = a1 -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(80L);
                return (short)90;
        };

        LShortUnaryOperatorX<X> thenFunction = p -> {
                thenFunctionCalled.set(true);
                // short
                assertThat(p).isEqualTo((short)90);
                // short
                return (short)100;
        };

        //when
        LLongToShortFunctionX<X> function = sutO.thenToShort(thenFunction);
        short finalValue = function.doApplyAsShort(80L);

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
        LLongToShortFunctionX<X> sutO = a1 -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(80L);
                return (short)90;
        };

        LShortToIntFunctionX<X> thenFunction = p -> {
                thenFunctionCalled.set(true);
                // short
                assertThat(p).isEqualTo((short)90);
                // int
                return 100;
        };

        //when
        LLongToIntFunctionX<X> function = sutO.thenToInt(thenFunction);
        int finalValue = function.doApplyAsInt(80L);

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
        LLongToShortFunctionX<X> sutO = a1 -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(80L);
                return (short)90;
        };

        LShortToLongFunctionX<X> thenFunction = p -> {
                thenFunctionCalled.set(true);
                // short
                assertThat(p).isEqualTo((short)90);
                // long
                return 100L;
        };

        //when
        LLongUnaryOperatorX<X> function = sutO.thenToLong(thenFunction);
        long finalValue = function.doApplyAsLong(80L);

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
        LLongToShortFunctionX<X> sutO = a1 -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(80L);
                return (short)90;
        };

        LShortToFloatFunctionX<X> thenFunction = p -> {
                thenFunctionCalled.set(true);
                // short
                assertThat(p).isEqualTo((short)90);
                // float
                return 100f;
        };

        //when
        LLongToFloatFunctionX<X> function = sutO.thenToFloat(thenFunction);
        float finalValue = function.doApplyAsFloat(80L);

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
        LLongToShortFunctionX<X> sutO = a1 -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(80L);
                return (short)90;
        };

        LShortToDoubleFunctionX<X> thenFunction = p -> {
                thenFunctionCalled.set(true);
                // short
                assertThat(p).isEqualTo((short)90);
                // double
                return 100d;
        };

        //when
        LLongToDoubleFunctionX<X> function = sutO.thenToDouble(thenFunction);
        double finalValue = function.doApplyAsDouble(80L);

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
        LLongToShortFunctionX<X> sutO = a1 -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(80L);
                return (short)90;
        };

        LShortToCharFunctionX<X> thenFunction = p -> {
                thenFunctionCalled.set(true);
                // short
                assertThat(p).isEqualTo((short)90);
                // char
                return '\u0100';
        };

        //when
        LLongToCharFunctionX<X> function = sutO.thenToChar(thenFunction);
        char finalValue = function.doApplyAsChar(80L);

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
        LLongToShortFunctionX<X> sutO = a1 -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(80L);
                return (short)90;
        };

        LShortPredicateX<X> thenFunction = p -> {
                thenFunctionCalled.set(true);
                // short
                assertThat(p).isEqualTo((short)90);
                // boolean
                return true;
        };

        //when
        LLongPredicateX<X> function = sutO.thenToBool(thenFunction);
        boolean finalValue = function.doTest(80L);

        //then - finals
        assertThat(finalValue).isEqualTo(true);
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    // </editor-fold>

    @Test
    public void testNesting() {
        assertThat(sut.nestingLongToShortFunc())
            .isInstanceOf(LLongToShortFunction.class);
    }

    @Test
    public void testShoving() {
        assertThat(sut.shovingLongToShortFunc())
            .isInstanceOf(LLongToShortFunction.class);
    }

    @Test
    public void testNestingX() {
        assertThat(sut.nestingLongToShortFuncX())
            .isInstanceOf(LLongToShortFunctionX.class);
    }

    @Test
    public void testShovingX() {
        assertThat(sut.shovingLongToShortFuncX())
            .isInstanceOf(LLongToShortFunctionX.class);
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void testShove() {

        // given
        LLongToShortFunctionX<X> sutThrowing = LLongToShortFunctionX.lX(a1 -> {
            throw new UnsupportedOperationException();
        });

        // when
        sutThrowing.shovingLongToShortFunc().doApplyAsShort(100L);
    }

    @Test
    public void testHandleLongToShortFunc() throws X {

        // given
        LLongToShortFunctionX<X> sutThrowing = LLongToShortFunctionX.lX(a1 -> {
            throw new UnsupportedOperationException();
        });

        // when
        LLongToShortFunctionX<X> wrapped = sutThrowing.handleLongToShortFuncX(h -> {
            h.wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED);
        });

        // then
        try {
            wrapped.doApplyAsShort(100L);
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
                .contains("LLongToShortFunctionX: short doApplyAsShort(long a1) throws X");
    }


    @Test
    public void isThrowing() {
        assertThat(sut.isThrowing())
            .isTrue();
    }

    @Test void safeCompiles() {
        LLongToShortFunctionX r1 = LLongToShortFunctionX.safe(sut); //NOSONAR
    }

    @Test void safePropagates() {
        Object result = LLongToShortFunctionX.safe(sut);
        assertThat(result).isSameAs(sut);
    }

    @Test void safeProtectsAgainstNpe() {
        Object result = LLongToShortFunctionX.safe(null);
        assertThat(result).isSameAs(LLongToShortFunctionX.lX(LLongToShortFunctionX.safe()));
    }

    @Test <Y extends Throwable> void safeSupplierPropagates() {
        LSupplierX<LLongToShortFunctionX<X>,Y> supplier = ()->sut;
        Object result = LLongToShortFunctionX.safeSupplier(supplier);
        assertThat(result).isSameAs(supplier);
    }

    @Test <Y extends Throwable> void safeSupplierProtectsAgainstNpe() {
        Object result = LLongToShortFunctionX.safeSupplier(null);
        assertThat(result).isSameAs(LLongToShortFunctionX.safeSupplier());
    }

    @Test <Y extends Throwable> void safeSupplierCompiles() {
        LSupplierX<LLongToShortFunctionX<X>,Y> r1 = LLongToShortFunctionX.safeSupplier(()->sut);  //NOSONAR
    }

}
