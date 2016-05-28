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
public class LCharToByteFunctionTest<X extends ParseException> {
    private static final String ORIGINAL_MESSAGE = "Original message";
    private static final String EXCEPTION_WAS_WRAPPED = "Exception was wrapped.";
    private static final String NO_EXCEPTION_WERE_THROWN = "No exception were thrown.";

    private byte testValue = (byte)100;



    private LCharToByteFunction sut = new LCharToByteFunction(){
        public  byte doApplyAsByte(char a)  {
            return testValue;
        }
    };

    private LCharToByteFunctionX<X> opposite = new LCharToByteFunctionX<X>(){
        public  byte doApplyAsByte(char a)  throws X {
            return testValue;
        }
    };




    private LCharToByteFunctionX<RuntimeException> sutAlwaysThrowingUnchecked = LCharToByteFunction.l(a -> {
            throw new IndexOutOfBoundsException(ORIGINAL_MESSAGE);
    });


    @Test
    public void testTheResult() throws X {
        assertThat(sut.doApplyAsByte('\u0100'))
            .isEqualTo(testValue);
    }

    @Test
    public void testTupleCall() throws X {

        LCharSingle domainObject = Tuple4U.charSingle('\u0100');

        Object result = sut.tupleApplyAsByte(domainObject);

        assertThat(result)
            .isEqualTo(testValue);
    }

    @Test
    public void testNonNullDoApplyAsByte() throws X {
        assertThat(sut.nonNullDoApplyAsByte('\u0100'))
            .isEqualTo(testValue);
    }

    @Test
    public void testNestingDoApplyAsByteUnchecked() throws X {

        // then
        try {
            sutAlwaysThrowingUnchecked.nestingDoApplyAsByte('\u0100');
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                    .hasNoCause()
                    .hasMessage(ORIGINAL_MESSAGE);
        }
    }

    @Test
    public void testShovingDoApplyAsByteUnchecked() throws X {

        // then
        try {
            sutAlwaysThrowingUnchecked.shovingDoApplyAsByte('\u0100');
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
            .isEqualTo("LCharToByteFunction: byte doApplyAsByte(char a)");
    }

    @Test
    public void testLMethod() throws X {
        assertThat(LCharToByteFunction.l(a -> testValue ))
            .isInstanceOf(LCharToByteFunction.class);
    }

    @Test
    public void testWrapMethod() throws X {
        assertThat(LCharToByteFunction.wrap(opposite))
            .isInstanceOf(LCharToByteFunction.class);
    }

    @Test
    public void testWrapMethodDoNotWrapsRuntimeException() throws X {
        // given
        LCharToByteFunctionX<X> sutThrowing = LCharToByteFunctionX.lX(a -> {
            throw new UnsupportedOperationException(ORIGINAL_MESSAGE);
        });

        // when
        LCharToByteFunction wrapped = LCharToByteFunction.wrap(sutThrowing);

        // then
        try {
            wrapped.doApplyAsByte('\u0100');
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
        LCharToByteFunctionX<ParseException> sutThrowing = LCharToByteFunctionX.lX(a -> {
            throw new ParseException(ORIGINAL_MESSAGE, 0);
        });

        // when
        LCharToByteFunction wrapped = LCharToByteFunction.wrap(sutThrowing);

        // then
        try {
            wrapped.doApplyAsByte('\u0100');
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(NestedException.class)
                    .hasCauseExactlyInstanceOf(ParseException.class)
                    .hasMessage(ORIGINAL_MESSAGE);
        }
    }


    @Test
    public void testHandlingDoApplyAsByteMethodWrapsTheException() throws X {

        // given
        LCharToByteFunction sutThrowing = LCharToByteFunction.l(a -> {
            throw new UnsupportedOperationException();
        });

        // when
        LCharToByteFunction wrapped = sutThrowing.handleCharToByteFunc(handler -> handler
            .wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED));

        // then
        try {
            wrapped.doApplyAsByte('\u0100');
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IllegalArgumentException.class)
                    .hasCauseExactlyInstanceOf(UnsupportedOperationException.class)
                    .hasMessage(EXCEPTION_WAS_WRAPPED);
        }
    }

    @Test
    public void testHandleCharToByteFuncMethodDoNotWrapsOtherExceptionIf() throws X {

        // given
        LCharToByteFunction sutThrowing = LCharToByteFunction.l(a -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LCharToByteFunction wrapped = sutThrowing.handleCharToByteFunc(handler -> handler
                .wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED)
                .throwIf(IndexOutOfBoundsException.class));

        // then
        try {
            wrapped.doApplyAsByte('\u0100');
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                    .hasNoCause();
        }
    }

@Test
    public void testHandleCharToByteFuncMethodDoNotWrapsOtherExceptionWhen() throws X {

        // given
        LCharToByteFunction sutThrowing = LCharToByteFunction.l(a -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LCharToByteFunction wrapped = sutThrowing.handleCharToByteFunc(handler -> handler
                .wrapWhen(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED)
                .throwIf(IndexOutOfBoundsException.class));

        // then
        try {
            wrapped.doApplyAsByte('\u0100');
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                    .hasNoCause();
        }
    }


    @Test
    public void testHandleCharToByteFuncMishandlingExceptionIsAllowed() throws X {

        // given
        LCharToByteFunction sutThrowing = LCharToByteFunction.l(a -> {
            throw new UnsupportedOperationException(ORIGINAL_MESSAGE);
        });

        // when
        LCharToByteFunction wrapped = sutThrowing.handleCharToByteFunc(h -> Function4U.doNothing());

        // then
        try {
            wrapped.doApplyAsByte('\u0100');
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
    public void testCharToByteFuncComposeChar() throws X {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LCharToByteFunction sutO = a -> {
                mainFunctionCalled.set(true);
                assertThat(a).isEqualTo('\u0090');
                return (byte)100;
        };

        LCharUnaryOperator before = p0 -> {
            assertThat(p0).isEqualTo('\u0080');
            beforeCalls.incrementAndGet();
            return '\u0090';
        };

        //when
        LCharToByteFunction function = sutO.charToByteFuncComposeChar(before);
        function.doApplyAsByte('\u0080');

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(beforeCalls.get()).isEqualTo(1);
    }


    @Test
    public void testCharToByteFuncCompose() throws X {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LCharToByteFunction sutO = a -> {
                mainFunctionCalled.set(true);
                assertThat(a).isEqualTo('\u0090');
                return (byte)100;
        };

        LToCharFunction<Integer> before = p0 -> {
            assertThat(p0).isEqualTo(80);
            beforeCalls.incrementAndGet();
            return '\u0090';
        };

        //when
        LToByteFunction<Integer> function = sutO.charToByteFuncCompose(before);
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
        LCharToByteFunction sutO = a -> {
                mainFunctionCalled.set(true);
                assertThat(a).isEqualTo('\u0080');
                return (byte)90;
        };

        LByteFunction<Integer> thenFunction = p -> {
                thenFunctionCalled.set(true);
                // byte
                assertThat(p).isEqualTo((byte)90);
                // Integer
                return 100;
        };

        //when
        LCharFunction<Integer> function = sutO.then(thenFunction);
        Integer finalValue = function.doApply('\u0080');

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
        LCharToByteFunction sutO = a -> {
                mainFunctionCalled.set(true);
                assertThat(a).isEqualTo('\u0080');
                return (byte)90;
        };

        LByteUnaryOperator thenFunction = p -> {
                thenFunctionCalled.set(true);
                // byte
                assertThat(p).isEqualTo((byte)90);
                // byte
                return (byte)100;
        };

        //when
        LCharToByteFunction function = sutO.thenToByte(thenFunction);
        byte finalValue = function.doApplyAsByte('\u0080');

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
        LCharToByteFunction sutO = a -> {
                mainFunctionCalled.set(true);
                assertThat(a).isEqualTo('\u0080');
                return (byte)90;
        };

        LByteToShortFunction thenFunction = p -> {
                thenFunctionCalled.set(true);
                // byte
                assertThat(p).isEqualTo((byte)90);
                // short
                return (short)100;
        };

        //when
        LCharToShortFunction function = sutO.thenToShort(thenFunction);
        short finalValue = function.doApplyAsShort('\u0080');

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
        LCharToByteFunction sutO = a -> {
                mainFunctionCalled.set(true);
                assertThat(a).isEqualTo('\u0080');
                return (byte)90;
        };

        LByteToIntFunction thenFunction = p -> {
                thenFunctionCalled.set(true);
                // byte
                assertThat(p).isEqualTo((byte)90);
                // int
                return 100;
        };

        //when
        LCharToIntFunction function = sutO.thenToInt(thenFunction);
        int finalValue = function.doApplyAsInt('\u0080');

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
        LCharToByteFunction sutO = a -> {
                mainFunctionCalled.set(true);
                assertThat(a).isEqualTo('\u0080');
                return (byte)90;
        };

        LByteToLongFunction thenFunction = p -> {
                thenFunctionCalled.set(true);
                // byte
                assertThat(p).isEqualTo((byte)90);
                // long
                return 100L;
        };

        //when
        LCharToLongFunction function = sutO.thenToLong(thenFunction);
        long finalValue = function.doApplyAsLong('\u0080');

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
        LCharToByteFunction sutO = a -> {
                mainFunctionCalled.set(true);
                assertThat(a).isEqualTo('\u0080');
                return (byte)90;
        };

        LByteToFloatFunction thenFunction = p -> {
                thenFunctionCalled.set(true);
                // byte
                assertThat(p).isEqualTo((byte)90);
                // float
                return 100f;
        };

        //when
        LCharToFloatFunction function = sutO.thenToFloat(thenFunction);
        float finalValue = function.doApplyAsFloat('\u0080');

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
        LCharToByteFunction sutO = a -> {
                mainFunctionCalled.set(true);
                assertThat(a).isEqualTo('\u0080');
                return (byte)90;
        };

        LByteToDoubleFunction thenFunction = p -> {
                thenFunctionCalled.set(true);
                // byte
                assertThat(p).isEqualTo((byte)90);
                // double
                return 100d;
        };

        //when
        LCharToDoubleFunction function = sutO.thenToDouble(thenFunction);
        double finalValue = function.doApplyAsDouble('\u0080');

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
        LCharToByteFunction sutO = a -> {
                mainFunctionCalled.set(true);
                assertThat(a).isEqualTo('\u0080');
                return (byte)90;
        };

        LByteToCharFunction thenFunction = p -> {
                thenFunctionCalled.set(true);
                // byte
                assertThat(p).isEqualTo((byte)90);
                // char
                return '\u0100';
        };

        //when
        LCharUnaryOperator function = sutO.thenToChar(thenFunction);
        char finalValue = function.doApplyAsChar('\u0080');

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
        LCharToByteFunction sutO = a -> {
                mainFunctionCalled.set(true);
                assertThat(a).isEqualTo('\u0080');
                return (byte)90;
        };

        LBytePredicate thenFunction = p -> {
                thenFunctionCalled.set(true);
                // byte
                assertThat(p).isEqualTo((byte)90);
                // boolean
                return true;
        };

        //when
        LCharPredicate function = sutO.thenToBool(thenFunction);
        boolean finalValue = function.doTest('\u0080');

        //then - finals
        assertThat(finalValue).isEqualTo(true);
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    // </editor-fold>

    @Test
    public void testNesting() {
        assertThat(sut.nestingCharToByteFunc())
            .isSameAs(sut)
            .isInstanceOf(LCharToByteFunction.class);
    }

    @Test
    public void testShoving() {
        assertThat(sut.shovingCharToByteFunc())
            .isSameAs(sut)
            .isInstanceOf(LCharToByteFunction.class);
    }

    @Test
    public void testNestingX() {
        assertThat(sut.nestingCharToByteFuncX())
            .isSameAs(sut)
            .isInstanceOf(LCharToByteFunctionX.class);
    }

    @Test
    public void testShovingX() {
        assertThat(sut.shovingCharToByteFuncX())
            .isSameAs(sut)
            .isInstanceOf(LCharToByteFunctionX.class);
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void testShove() {

        // given
        LCharToByteFunction sutThrowing = LCharToByteFunction.l(a -> {
            throw new UnsupportedOperationException();
        });

        // when
        sutThrowing.shovingCharToByteFunc().doApplyAsByte('\u0100');
    }

    @Test
    public void testHandleCharToByteFunc() throws X {

        // given
        LCharToByteFunction sutThrowing = LCharToByteFunction.l(a -> {
            throw new UnsupportedOperationException();
        });

        // when
        LCharToByteFunction wrapped = sutThrowing.handleCharToByteFunc(h -> {
            h.wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED);
        });

        // then
        try {
            wrapped.doApplyAsByte('\u0100');
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
                .contains("LCharToByteFunction: byte doApplyAsByte(char a)");
    }


    @Test
    public void isThrowing() {
        assertThat(sut.isThrowing())
            .isFalse();
    }

    @Test void safeCompiles() {
        LCharToByteFunction r1 = LCharToByteFunction.safe(sut); //NOSONAR
        LCharToByteFunctionX r2 = LCharToByteFunction.safe(sut); //NOSONAR
    }

    @Test void safePropagates() {
        Object result = LCharToByteFunction.safe(sut);
        assertThat(result).isSameAs(sut);
    }

    @Test void safeProtectsAgainstNpe() {
        Object result = LCharToByteFunction.safe(null);
        assertThat(result).isSameAs(LCharToByteFunction.l(LCharToByteFunction.safe()));
    }

    @Test  void safeSupplierPropagates() {
        LSupplier<LCharToByteFunction> supplier = ()->sut;
        Object result = LCharToByteFunction.safeSupplier(supplier);
        assertThat(result).isSameAs(supplier);
    }

    @Test  void safeSupplierProtectsAgainstNpe() {
        Object result = LCharToByteFunction.safeSupplier(null);
        assertThat(result).isSameAs(LCharToByteFunction.safeSupplier());
    }

    @Test  void safeSupplierCompiles() {
        LSupplier<LCharToByteFunction> r1 = LCharToByteFunction.safeSupplier(()->sut);  //NOSONAR
    }

}
