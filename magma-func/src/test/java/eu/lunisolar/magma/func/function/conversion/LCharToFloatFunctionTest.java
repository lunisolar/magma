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
public class LCharToFloatFunctionTest<X extends ParseException> {
    private static final String ORIGINAL_MESSAGE = "Original message";
    private static final String EXCEPTION_WAS_WRAPPED = "Exception was wrapped.";
    private static final String NO_EXCEPTION_WERE_THROWN = "No exception were thrown.";

    private float testValue = 100f;



    private LCharToFloatFunction sut = new LCharToFloatFunction(){
        public  float doApplyAsFloat(char a)  {
            return testValue;
        }
    };

    private LCharToFloatFunctionX<X> opposite = new LCharToFloatFunctionX<X>(){
        public  float doApplyAsFloat(char a)  throws X {
            return testValue;
        }
    };




    private LCharToFloatFunctionX<RuntimeException> sutAlwaysThrowingUnchecked = LCharToFloatFunction.l(a -> {
            throw new IndexOutOfBoundsException(ORIGINAL_MESSAGE);
    });


    @Test
    public void testTheResult() throws X {
        assertThat(sut.doApplyAsFloat('\u0100'))
            .isEqualTo(testValue);
    }

    @Test
    public void testTupleCall() throws X {

        LCharSingle domainObject = Tuple4U.charSingle('\u0100');

        Object result = sut.tupleApplyAsFloat(domainObject);

        assertThat(result)
            .isEqualTo(testValue);
    }

    @Test
    public void testNonNullDoApplyAsFloat() throws X {
        assertThat(sut.nonNullDoApplyAsFloat('\u0100'))
            .isEqualTo(testValue);
    }

    @Test
    public void testNestingDoApplyAsFloatUnchecked() throws X {

        // then
        try {
            sutAlwaysThrowingUnchecked.nestingDoApplyAsFloat('\u0100');
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                    .hasNoCause()
                    .hasMessage(ORIGINAL_MESSAGE);
        }
    }

    @Test
    public void testShovingDoApplyAsFloatUnchecked() throws X {

        // then
        try {
            sutAlwaysThrowingUnchecked.shovingDoApplyAsFloat('\u0100');
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
            .isEqualTo("LCharToFloatFunction: float doApplyAsFloat(char a)");
    }

    @Test
    public void testLMethod() throws X {
        assertThat(LCharToFloatFunction.l(a -> testValue ))
            .isInstanceOf(LCharToFloatFunction.class);
    }

    @Test
    public void testWrapMethod() throws X {
        assertThat(LCharToFloatFunction.wrap(opposite))
            .isInstanceOf(LCharToFloatFunction.class);
    }

    @Test
    public void testWrapMethodDoNotWrapsRuntimeException() throws X {
        // given
        LCharToFloatFunctionX<X> sutThrowing = LCharToFloatFunctionX.lX(a -> {
            throw new UnsupportedOperationException(ORIGINAL_MESSAGE);
        });

        // when
        LCharToFloatFunction wrapped = LCharToFloatFunction.wrap(sutThrowing);

        // then
        try {
            wrapped.doApplyAsFloat('\u0100');
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
        LCharToFloatFunctionX<ParseException> sutThrowing = LCharToFloatFunctionX.lX(a -> {
            throw new ParseException(ORIGINAL_MESSAGE, 0);
        });

        // when
        LCharToFloatFunction wrapped = LCharToFloatFunction.wrap(sutThrowing);

        // then
        try {
            wrapped.doApplyAsFloat('\u0100');
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(NestedException.class)
                    .hasCauseExactlyInstanceOf(ParseException.class)
                    .hasMessage(ORIGINAL_MESSAGE);
        }
    }


    @Test
    public void testHandlingDoApplyAsFloatMethodWrapsTheException() throws X {

        // given
        LCharToFloatFunction sutThrowing = LCharToFloatFunction.l(a -> {
            throw new UnsupportedOperationException();
        });

        // when
        LCharToFloatFunction wrapped = sutThrowing.handleCharToFloatFunc(handler -> handler
            .wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED));

        // then
        try {
            wrapped.doApplyAsFloat('\u0100');
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IllegalArgumentException.class)
                    .hasCauseExactlyInstanceOf(UnsupportedOperationException.class)
                    .hasMessage(EXCEPTION_WAS_WRAPPED);
        }
    }

    @Test
    public void testHandleCharToFloatFuncMethodDoNotWrapsOtherExceptionIf() throws X {

        // given
        LCharToFloatFunction sutThrowing = LCharToFloatFunction.l(a -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LCharToFloatFunction wrapped = sutThrowing.handleCharToFloatFunc(handler -> handler
                .wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED)
                .throwIf(IndexOutOfBoundsException.class));

        // then
        try {
            wrapped.doApplyAsFloat('\u0100');
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                    .hasNoCause();
        }
    }

@Test
    public void testHandleCharToFloatFuncMethodDoNotWrapsOtherExceptionWhen() throws X {

        // given
        LCharToFloatFunction sutThrowing = LCharToFloatFunction.l(a -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LCharToFloatFunction wrapped = sutThrowing.handleCharToFloatFunc(handler -> handler
                .wrapWhen(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED)
                .throwIf(IndexOutOfBoundsException.class));

        // then
        try {
            wrapped.doApplyAsFloat('\u0100');
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                    .hasNoCause();
        }
    }


    @Test
    public void testHandleCharToFloatFuncMishandlingExceptionIsAllowed() throws X {

        // given
        LCharToFloatFunction sutThrowing = LCharToFloatFunction.l(a -> {
            throw new UnsupportedOperationException(ORIGINAL_MESSAGE);
        });

        // when
        LCharToFloatFunction wrapped = sutThrowing.handleCharToFloatFunc(h -> Function4U.doNothing());

        // then
        try {
            wrapped.doApplyAsFloat('\u0100');
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
    public void testCharToFloatFuncComposeChar() throws X {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LCharToFloatFunction sutO = a -> {
                mainFunctionCalled.set(true);
                assertThat(a).isEqualTo('\u0090');
                return 100f;
        };

        LCharUnaryOperator before = p0 -> {
            assertThat(p0).isEqualTo('\u0080');
            beforeCalls.incrementAndGet();
            return '\u0090';
        };

        //when
        LCharToFloatFunction function = sutO.charToFloatFuncComposeChar(before);
        function.doApplyAsFloat('\u0080');

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(beforeCalls.get()).isEqualTo(1);
    }


    @Test
    public void testCharToFloatFuncCompose() throws X {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LCharToFloatFunction sutO = a -> {
                mainFunctionCalled.set(true);
                assertThat(a).isEqualTo('\u0090');
                return 100f;
        };

        LToCharFunction<Integer> before = p0 -> {
            assertThat(p0).isEqualTo(80);
            beforeCalls.incrementAndGet();
            return '\u0090';
        };

        //when
        LToFloatFunction<Integer> function = sutO.charToFloatFuncCompose(before);
        function.doApplyAsFloat(80);

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
        LCharToFloatFunction sutO = a -> {
                mainFunctionCalled.set(true);
                assertThat(a).isEqualTo('\u0080');
                return 90f;
        };

        LFloatFunction<Integer> thenFunction = p -> {
                thenFunctionCalled.set(true);
                // float
                assertThat(p).isEqualTo(90f);
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
        LCharToFloatFunction sutO = a -> {
                mainFunctionCalled.set(true);
                assertThat(a).isEqualTo('\u0080');
                return 90f;
        };

        LFloatToByteFunction thenFunction = p -> {
                thenFunctionCalled.set(true);
                // float
                assertThat(p).isEqualTo(90f);
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
        LCharToFloatFunction sutO = a -> {
                mainFunctionCalled.set(true);
                assertThat(a).isEqualTo('\u0080');
                return 90f;
        };

        LFloatToShortFunction thenFunction = p -> {
                thenFunctionCalled.set(true);
                // float
                assertThat(p).isEqualTo(90f);
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
        LCharToFloatFunction sutO = a -> {
                mainFunctionCalled.set(true);
                assertThat(a).isEqualTo('\u0080');
                return 90f;
        };

        LFloatToIntFunction thenFunction = p -> {
                thenFunctionCalled.set(true);
                // float
                assertThat(p).isEqualTo(90f);
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
        LCharToFloatFunction sutO = a -> {
                mainFunctionCalled.set(true);
                assertThat(a).isEqualTo('\u0080');
                return 90f;
        };

        LFloatToLongFunction thenFunction = p -> {
                thenFunctionCalled.set(true);
                // float
                assertThat(p).isEqualTo(90f);
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
        LCharToFloatFunction sutO = a -> {
                mainFunctionCalled.set(true);
                assertThat(a).isEqualTo('\u0080');
                return 90f;
        };

        LFloatUnaryOperator thenFunction = p -> {
                thenFunctionCalled.set(true);
                // float
                assertThat(p).isEqualTo(90f);
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
        LCharToFloatFunction sutO = a -> {
                mainFunctionCalled.set(true);
                assertThat(a).isEqualTo('\u0080');
                return 90f;
        };

        LFloatToDoubleFunction thenFunction = p -> {
                thenFunctionCalled.set(true);
                // float
                assertThat(p).isEqualTo(90f);
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
        LCharToFloatFunction sutO = a -> {
                mainFunctionCalled.set(true);
                assertThat(a).isEqualTo('\u0080');
                return 90f;
        };

        LFloatToCharFunction thenFunction = p -> {
                thenFunctionCalled.set(true);
                // float
                assertThat(p).isEqualTo(90f);
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
        LCharToFloatFunction sutO = a -> {
                mainFunctionCalled.set(true);
                assertThat(a).isEqualTo('\u0080');
                return 90f;
        };

        LFloatPredicate thenFunction = p -> {
                thenFunctionCalled.set(true);
                // float
                assertThat(p).isEqualTo(90f);
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
        assertThat(sut.nestingCharToFloatFunc())
            .isSameAs(sut)
            .isInstanceOf(LCharToFloatFunction.class);
    }

    @Test
    public void testShoving() {
        assertThat(sut.shovingCharToFloatFunc())
            .isSameAs(sut)
            .isInstanceOf(LCharToFloatFunction.class);
    }

    @Test
    public void testNestingX() {
        assertThat(sut.nestingCharToFloatFuncX())
            .isSameAs(sut)
            .isInstanceOf(LCharToFloatFunctionX.class);
    }

    @Test
    public void testShovingX() {
        assertThat(sut.shovingCharToFloatFuncX())
            .isSameAs(sut)
            .isInstanceOf(LCharToFloatFunctionX.class);
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void testShove() {

        // given
        LCharToFloatFunction sutThrowing = LCharToFloatFunction.l(a -> {
            throw new UnsupportedOperationException();
        });

        // when
        sutThrowing.shovingCharToFloatFunc().doApplyAsFloat('\u0100');
    }

    @Test
    public void testHandleCharToFloatFunc() throws X {

        // given
        LCharToFloatFunction sutThrowing = LCharToFloatFunction.l(a -> {
            throw new UnsupportedOperationException();
        });

        // when
        LCharToFloatFunction wrapped = sutThrowing.handleCharToFloatFunc(h -> {
            h.wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED);
        });

        // then
        try {
            wrapped.doApplyAsFloat('\u0100');
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
                .contains("LCharToFloatFunction: float doApplyAsFloat(char a)");
    }


    @Test
    public void isThrowing() {
        assertThat(sut.isThrowing())
            .isFalse();
    }

    @Test void safeCompiles() {
        LCharToFloatFunction r1 = LCharToFloatFunction.safe(sut); //NOSONAR
        LCharToFloatFunctionX r2 = LCharToFloatFunction.safe(sut); //NOSONAR
    }

    @Test void safePropagates() {
        Object result = LCharToFloatFunction.safe(sut);
        assertThat(result).isSameAs(sut);
    }

    @Test void safeProtectsAgainstNpe() {
        Object result = LCharToFloatFunction.safe(null);
        assertThat(result).isSameAs(LCharToFloatFunction.l(LCharToFloatFunction.safe()));
    }

    @Test  void safeSupplierPropagates() {
        LSupplier<LCharToFloatFunction> supplier = ()->sut;
        Object result = LCharToFloatFunction.safeSupplier(supplier);
        assertThat(result).isSameAs(supplier);
    }

    @Test  void safeSupplierProtectsAgainstNpe() {
        Object result = LCharToFloatFunction.safeSupplier(null);
        assertThat(result).isSameAs(LCharToFloatFunction.safeSupplier());
    }

    @Test  void safeSupplierCompiles() {
        LSupplier<LCharToFloatFunction> r1 = LCharToFloatFunction.safeSupplier(()->sut);  //NOSONAR
    }

}
