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
public class LIntToByteFunctionXTest<X extends ParseException> {
    private static final String ORIGINAL_MESSAGE = "Original message";
    private static final String EXCEPTION_WAS_WRAPPED = "Exception was wrapped.";
    private static final String NO_EXCEPTION_WERE_THROWN = "No exception were thrown.";

    private byte testValue = (byte)100;



    private LIntToByteFunctionX<X> sut = new LIntToByteFunctionX<X>(){
        public  byte doApplyAsByte(int a)  throws X {
            return testValue;
        }
    };

    private LIntToByteFunction opposite = new LIntToByteFunction(){
        public  byte doApplyAsByte(int a)  {
            return testValue;
        }
    };



    private LIntToByteFunctionX<ParseException> sutAlwaysThrowing = LIntToByteFunctionX.lX(a -> {
            throw new ParseException(ORIGINAL_MESSAGE, 0);
    });

    private LIntToByteFunctionX<RuntimeException> sutAlwaysThrowingUnchecked = LIntToByteFunctionX.lX(a -> {
            throw new IndexOutOfBoundsException(ORIGINAL_MESSAGE);
    });


    @Test
    public void testTheResult() throws X {
        assertThat(sut.doApplyAsByte(100))
            .isEqualTo(testValue);
    }

    @Test
    public void testTupleCall() throws X {

        LIntSingle domainObject = Tuple4U.intSingle(100);

        Object result = sut.tupleApplyAsByte(domainObject);

        assertThat(result)
            .isEqualTo(testValue);
    }

    @Test
    public void testNonNullDoApplyAsByte() throws X {
        assertThat(sut.nonNullDoApplyAsByte(100))
            .isEqualTo(testValue);
    }

    @Test
    public void testNestingDoApplyAsByteChecked() throws X {

        // then
        try {
            sutAlwaysThrowing.nestingDoApplyAsByte(100);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(NestedException.class)
                    .hasCauseExactlyInstanceOf(ParseException.class)
                    .hasMessage(ORIGINAL_MESSAGE);
        }
    }

    @Test
    public void testNestingDoApplyAsByteUnchecked() throws X {

        // then
        try {
            sutAlwaysThrowingUnchecked.nestingDoApplyAsByte(100);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                    .hasNoCause()
                    .hasMessage(ORIGINAL_MESSAGE);
        }
    }

    @Test
    public void testShovingDoApplyAsByteChecked() throws X {

        // then
        try {
            sutAlwaysThrowing.shovingDoApplyAsByte(100);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(ParseException.class)
                    .hasNoCause()
                    .hasMessage(ORIGINAL_MESSAGE);
        }
    }

    @Test
    public void testShovingDoApplyAsByteUnchecked() throws X {

        // then
        try {
            sutAlwaysThrowingUnchecked.shovingDoApplyAsByte(100);
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
            .isEqualTo("LIntToByteFunctionX: byte doApplyAsByte(int a) throws X");
    }

    @Test
    public void testLXMethod() throws X {
        assertThat(LIntToByteFunctionX.lX(a -> testValue ))
            .isInstanceOf(LIntToByteFunctionX.class);
    }

    @Test
    public void testWrapXMethod() throws X {
        assertThat(LIntToByteFunctionX.wrapX(opposite))
            .isInstanceOf(LIntToByteFunctionX.class);
    }


    @Test
    public void testHandlingDoApplyAsByteMethodWrapsTheException() throws X {

        // given
        LIntToByteFunctionX<X> sutThrowing = LIntToByteFunctionX.lX(a -> {
            throw new UnsupportedOperationException();
        });

        // when
        LIntToByteFunctionX<RuntimeException> wrapped = sutThrowing.handleIntToByteFuncX(handler -> handler
            .wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED));

        // then
        try {
            wrapped.doApplyAsByte(100);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IllegalArgumentException.class)
                    .hasCauseExactlyInstanceOf(UnsupportedOperationException.class)
                    .hasMessage(EXCEPTION_WAS_WRAPPED);
        }
    }

    @Test
    public void testHandleIntToByteFuncXMethodDoNotWrapsOtherExceptionIf() throws X {

        // given
        LIntToByteFunctionX<X> sutThrowing = LIntToByteFunctionX.lX(a -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LIntToByteFunctionX<X> wrapped = sutThrowing.handleIntToByteFuncX(handler -> handler
                .wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED)
                .throwIf(IndexOutOfBoundsException.class));

        // then
        try {
            wrapped.doApplyAsByte(100);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                    .hasNoCause();
        }
    }

@Test
    public void testHandleIntToByteFuncXMethodDoNotWrapsOtherExceptionWhen() throws X {

        // given
        LIntToByteFunctionX<X> sutThrowing = LIntToByteFunctionX.lX(a -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LIntToByteFunctionX<X> wrapped = sutThrowing.handleIntToByteFuncX(handler -> handler
                .wrapWhen(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED)
                .throwIf(IndexOutOfBoundsException.class));

        // then
        try {
            wrapped.doApplyAsByte(100);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                    .hasNoCause();
        }
    }


    @Test
    public void testHandleIntToByteFuncXMishandlingExceptionIsAllowed() throws X {

        // given
        LIntToByteFunctionX<X> sutThrowing = LIntToByteFunctionX.lX(a -> {
            throw (X) new ParseException(ORIGINAL_MESSAGE, 0);
        });

        // when
        LIntToByteFunctionX<X> wrapped = sutThrowing.handleIntToByteFuncX(h -> Function4U.doNothing());

        // then
        try {
            wrapped.doApplyAsByte(100);
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
    public void testIntToByteFuncComposeInt() throws X {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LIntToByteFunctionX<X> sutO = a -> {
                mainFunctionCalled.set(true);
                assertThat(a).isEqualTo(90);
                return (byte)100;
        };

        LIntUnaryOperatorX<X> before = p0 -> {
            assertThat(p0).isEqualTo(80);
            beforeCalls.incrementAndGet();
            return 90;
        };

        //when
        LIntToByteFunctionX<X> function = sutO.intToByteFuncComposeInt(before);
        function.doApplyAsByte(80);

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(beforeCalls.get()).isEqualTo(1);
    }


    @Test
    public void testIntToByteFuncCompose() throws X {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LIntToByteFunctionX<X> sutO = a -> {
                mainFunctionCalled.set(true);
                assertThat(a).isEqualTo(90);
                return (byte)100;
        };

        LToIntFunctionX<Integer,X> before = p0 -> {
            assertThat(p0).isEqualTo(80);
            beforeCalls.incrementAndGet();
            return 90;
        };

        //when
        LToByteFunctionX<Integer,X> function = sutO.intToByteFuncCompose(before);
        function.doApplyAsByte(80);

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
        LIntToByteFunctionX<X> sutO = a -> {
                mainFunctionCalled.set(true);
                assertThat(a).isEqualTo(80);
                return (byte)90;
        };

        LByteFunctionX<Integer,X> thenFunction = p -> {
                thenFunctionCalled.set(true);
                // byte
                assertThat(p).isEqualTo((byte)90);
                // Integer
                return 100;
        };

        //when
        LIntFunctionX<Integer,X> function = sutO.then(thenFunction);
        Integer finalValue = function.doApply(80);

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
        LIntToByteFunctionX<X> sutO = a -> {
                mainFunctionCalled.set(true);
                assertThat(a).isEqualTo(80);
                return (byte)90;
        };

        LByteUnaryOperatorX<X> thenFunction = p -> {
                thenFunctionCalled.set(true);
                // byte
                assertThat(p).isEqualTo((byte)90);
                // byte
                return (byte)100;
        };

        //when
        LIntToByteFunctionX<X> function = sutO.thenToByte(thenFunction);
        byte finalValue = function.doApplyAsByte(80);

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
        LIntToByteFunctionX<X> sutO = a -> {
                mainFunctionCalled.set(true);
                assertThat(a).isEqualTo(80);
                return (byte)90;
        };

        LByteToShortFunctionX<X> thenFunction = p -> {
                thenFunctionCalled.set(true);
                // byte
                assertThat(p).isEqualTo((byte)90);
                // short
                return (short)100;
        };

        //when
        LIntToShortFunctionX<X> function = sutO.thenToShort(thenFunction);
        short finalValue = function.doApplyAsShort(80);

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
        LIntToByteFunctionX<X> sutO = a -> {
                mainFunctionCalled.set(true);
                assertThat(a).isEqualTo(80);
                return (byte)90;
        };

        LByteToIntFunctionX<X> thenFunction = p -> {
                thenFunctionCalled.set(true);
                // byte
                assertThat(p).isEqualTo((byte)90);
                // int
                return 100;
        };

        //when
        LIntUnaryOperatorX<X> function = sutO.thenToInt(thenFunction);
        int finalValue = function.doApplyAsInt(80);

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
        LIntToByteFunctionX<X> sutO = a -> {
                mainFunctionCalled.set(true);
                assertThat(a).isEqualTo(80);
                return (byte)90;
        };

        LByteToLongFunctionX<X> thenFunction = p -> {
                thenFunctionCalled.set(true);
                // byte
                assertThat(p).isEqualTo((byte)90);
                // long
                return 100L;
        };

        //when
        LIntToLongFunctionX<X> function = sutO.thenToLong(thenFunction);
        long finalValue = function.doApplyAsLong(80);

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
        LIntToByteFunctionX<X> sutO = a -> {
                mainFunctionCalled.set(true);
                assertThat(a).isEqualTo(80);
                return (byte)90;
        };

        LByteToFloatFunctionX<X> thenFunction = p -> {
                thenFunctionCalled.set(true);
                // byte
                assertThat(p).isEqualTo((byte)90);
                // float
                return 100f;
        };

        //when
        LIntToFloatFunctionX<X> function = sutO.thenToFloat(thenFunction);
        float finalValue = function.doApplyAsFloat(80);

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
        LIntToByteFunctionX<X> sutO = a -> {
                mainFunctionCalled.set(true);
                assertThat(a).isEqualTo(80);
                return (byte)90;
        };

        LByteToDoubleFunctionX<X> thenFunction = p -> {
                thenFunctionCalled.set(true);
                // byte
                assertThat(p).isEqualTo((byte)90);
                // double
                return 100d;
        };

        //when
        LIntToDoubleFunctionX<X> function = sutO.thenToDouble(thenFunction);
        double finalValue = function.doApplyAsDouble(80);

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
        LIntToByteFunctionX<X> sutO = a -> {
                mainFunctionCalled.set(true);
                assertThat(a).isEqualTo(80);
                return (byte)90;
        };

        LByteToCharFunctionX<X> thenFunction = p -> {
                thenFunctionCalled.set(true);
                // byte
                assertThat(p).isEqualTo((byte)90);
                // char
                return '\u0100';
        };

        //when
        LIntToCharFunctionX<X> function = sutO.thenToChar(thenFunction);
        char finalValue = function.doApplyAsChar(80);

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
        LIntToByteFunctionX<X> sutO = a -> {
                mainFunctionCalled.set(true);
                assertThat(a).isEqualTo(80);
                return (byte)90;
        };

        LBytePredicateX<X> thenFunction = p -> {
                thenFunctionCalled.set(true);
                // byte
                assertThat(p).isEqualTo((byte)90);
                // boolean
                return true;
        };

        //when
        LIntPredicateX<X> function = sutO.thenToBool(thenFunction);
        boolean finalValue = function.doTest(80);

        //then - finals
        assertThat(finalValue).isEqualTo(true);
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    // </editor-fold>

    @Test
    public void testNesting() {
        assertThat(sut.nestingIntToByteFunc())
            .isInstanceOf(LIntToByteFunction.class);
    }

    @Test
    public void testShoving() {
        assertThat(sut.shovingIntToByteFunc())
            .isInstanceOf(LIntToByteFunction.class);
    }

    @Test
    public void testNestingX() {
        assertThat(sut.nestingIntToByteFuncX())
            .isInstanceOf(LIntToByteFunctionX.class);
    }

    @Test
    public void testShovingX() {
        assertThat(sut.shovingIntToByteFuncX())
            .isInstanceOf(LIntToByteFunctionX.class);
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void testShove() {

        // given
        LIntToByteFunctionX<X> sutThrowing = LIntToByteFunctionX.lX(a -> {
            throw new UnsupportedOperationException();
        });

        // when
        sutThrowing.shovingIntToByteFunc().doApplyAsByte(100);
    }

    @Test
    public void testHandleIntToByteFunc() throws X {

        // given
        LIntToByteFunctionX<X> sutThrowing = LIntToByteFunctionX.lX(a -> {
            throw new UnsupportedOperationException();
        });

        // when
        LIntToByteFunctionX<X> wrapped = sutThrowing.handleIntToByteFuncX(h -> {
            h.wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED);
        });

        // then
        try {
            wrapped.doApplyAsByte(100);
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
                .contains("LIntToByteFunctionX: byte doApplyAsByte(int a) throws X");
    }


    @Test
    public void isThrowing() {
        assertThat(sut.isThrowing())
            .isTrue();
    }

    @Test void safeCompiles() {
        LIntToByteFunctionX r1 = LIntToByteFunctionX.safe(sut); //NOSONAR
    }

    @Test void safePropagates() {
        Object result = LIntToByteFunctionX.safe(sut);
        assertThat(result).isSameAs(sut);
    }

    @Test void safeProtectsAgainstNpe() {
        Object result = LIntToByteFunctionX.safe(null);
        assertThat(result).isSameAs(LIntToByteFunctionX.lX(LIntToByteFunctionX.safe()));
    }

    @Test <Y extends Throwable> void safeSupplierPropagates() {
        LSupplierX<LIntToByteFunctionX<X>,Y> supplier = ()->sut;
        Object result = LIntToByteFunctionX.safeSupplier(supplier);
        assertThat(result).isSameAs(supplier);
    }

    @Test <Y extends Throwable> void safeSupplierProtectsAgainstNpe() {
        Object result = LIntToByteFunctionX.safeSupplier(null);
        assertThat(result).isSameAs(LIntToByteFunctionX.safeSupplier());
    }

    @Test <Y extends Throwable> void safeSupplierCompiles() {
        LSupplierX<LIntToByteFunctionX<X>,Y> r1 = LIntToByteFunctionX.safeSupplier(()->sut);  //NOSONAR
    }

}
