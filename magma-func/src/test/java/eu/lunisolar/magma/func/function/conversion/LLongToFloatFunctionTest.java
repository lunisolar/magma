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
public class LLongToFloatFunctionTest<X extends ParseException> {
    private static final String ORIGINAL_MESSAGE = "Original message";
    private static final String EXCEPTION_WAS_WRAPPED = "Exception was wrapped.";
    private static final String NO_EXCEPTION_WERE_THROWN = "No exception were thrown.";

    private float testValue = 100f;



    private LLongToFloatFunction sut = new LLongToFloatFunction(){
        public  float doApplyAsFloat(long a)  {
            return testValue;
        }
    };

    private LLongToFloatFunctionX<X> opposite = new LLongToFloatFunctionX<X>(){
        public  float doApplyAsFloat(long a)  throws X {
            return testValue;
        }
    };




    private LLongToFloatFunctionX<RuntimeException> sutAlwaysThrowingUnchecked = LLongToFloatFunction.l(a -> {
            throw new IndexOutOfBoundsException(ORIGINAL_MESSAGE);
    });


    @Test
    public void testTheResult() throws X {
        assertThat(sut.doApplyAsFloat(100L))
            .isEqualTo(testValue);
    }

    @Test
    public void testTupleCall() throws X {

        LLongSingle domainObject = Tuple4U.longSingle(100L);

        Object result = sut.tupleApplyAsFloat(domainObject);

        assertThat(result)
            .isEqualTo(testValue);
    }

    @Test
    public void testNonNullDoApplyAsFloat() throws X {
        assertThat(sut.nonNullDoApplyAsFloat(100L))
            .isEqualTo(testValue);
    }

    @Test
    public void testNestingDoApplyAsFloatUnchecked() throws X {

        // then
        try {
            sutAlwaysThrowingUnchecked.nestingDoApplyAsFloat(100L);
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
            sutAlwaysThrowingUnchecked.shovingDoApplyAsFloat(100L);
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
            .isEqualTo("LLongToFloatFunction: float doApplyAsFloat(long a)");
    }

    @Test
    public void testLMethod() throws X {
        assertThat(LLongToFloatFunction.l(a -> testValue ))
            .isInstanceOf(LLongToFloatFunction.class);
    }

    @Test
    public void testWrapMethod() throws X {
        assertThat(LLongToFloatFunction.wrap(opposite))
            .isInstanceOf(LLongToFloatFunction.class);
    }

    @Test
    public void testWrapMethodDoNotWrapsRuntimeException() throws X {
        // given
        LLongToFloatFunctionX<X> sutThrowing = LLongToFloatFunctionX.lX(a -> {
            throw new UnsupportedOperationException(ORIGINAL_MESSAGE);
        });

        // when
        LLongToFloatFunction wrapped = LLongToFloatFunction.wrap(sutThrowing);

        // then
        try {
            wrapped.doApplyAsFloat(100L);
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
        LLongToFloatFunctionX<ParseException> sutThrowing = LLongToFloatFunctionX.lX(a -> {
            throw new ParseException(ORIGINAL_MESSAGE, 0);
        });

        // when
        LLongToFloatFunction wrapped = LLongToFloatFunction.wrap(sutThrowing);

        // then
        try {
            wrapped.doApplyAsFloat(100L);
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
        LLongToFloatFunction sutThrowing = LLongToFloatFunction.l(a -> {
            throw new UnsupportedOperationException();
        });

        // when
        LLongToFloatFunction wrapped = sutThrowing.handleLongToFloatFunc(handler -> handler
            .wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED));

        // then
        try {
            wrapped.doApplyAsFloat(100L);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IllegalArgumentException.class)
                    .hasCauseExactlyInstanceOf(UnsupportedOperationException.class)
                    .hasMessage(EXCEPTION_WAS_WRAPPED);
        }
    }

    @Test
    public void testHandleLongToFloatFuncMethodDoNotWrapsOtherExceptionIf() throws X {

        // given
        LLongToFloatFunction sutThrowing = LLongToFloatFunction.l(a -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LLongToFloatFunction wrapped = sutThrowing.handleLongToFloatFunc(handler -> handler
                .wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED)
                .throwIf(IndexOutOfBoundsException.class));

        // then
        try {
            wrapped.doApplyAsFloat(100L);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                    .hasNoCause();
        }
    }

@Test
    public void testHandleLongToFloatFuncMethodDoNotWrapsOtherExceptionWhen() throws X {

        // given
        LLongToFloatFunction sutThrowing = LLongToFloatFunction.l(a -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LLongToFloatFunction wrapped = sutThrowing.handleLongToFloatFunc(handler -> handler
                .wrapWhen(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED)
                .throwIf(IndexOutOfBoundsException.class));

        // then
        try {
            wrapped.doApplyAsFloat(100L);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                    .hasNoCause();
        }
    }


    @Test
    public void testHandleLongToFloatFuncMishandlingExceptionIsAllowed() throws X {

        // given
        LLongToFloatFunction sutThrowing = LLongToFloatFunction.l(a -> {
            throw new UnsupportedOperationException(ORIGINAL_MESSAGE);
        });

        // when
        LLongToFloatFunction wrapped = sutThrowing.handleLongToFloatFunc(h -> Function4U.doNothing());

        // then
        try {
            wrapped.doApplyAsFloat(100L);
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
    public void testLongToFloatFuncComposeLong() throws X {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LLongToFloatFunction sutO = a -> {
                mainFunctionCalled.set(true);
                assertThat(a).isEqualTo(90L);
                return 100f;
        };

        LLongUnaryOperator before = p0 -> {
            assertThat(p0).isEqualTo(80L);
            beforeCalls.incrementAndGet();
            return 90L;
        };

        //when
        LLongToFloatFunction function = sutO.longToFloatFuncComposeLong(before);
        function.doApplyAsFloat(80L);

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(beforeCalls.get()).isEqualTo(1);
    }


    @Test
    public void testLongToFloatFuncCompose() throws X {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LLongToFloatFunction sutO = a -> {
                mainFunctionCalled.set(true);
                assertThat(a).isEqualTo(90L);
                return 100f;
        };

        LToLongFunction<Integer> before = p0 -> {
            assertThat(p0).isEqualTo(80);
            beforeCalls.incrementAndGet();
            return 90L;
        };

        //when
        LToFloatFunction<Integer> function = sutO.longToFloatFuncCompose(before);
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
        LLongToFloatFunction sutO = a -> {
                mainFunctionCalled.set(true);
                assertThat(a).isEqualTo(80L);
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
        LLongFunction<Integer> function = sutO.then(thenFunction);
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
        LLongToFloatFunction sutO = a -> {
                mainFunctionCalled.set(true);
                assertThat(a).isEqualTo(80L);
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
        LLongToByteFunction function = sutO.thenToByte(thenFunction);
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
        LLongToFloatFunction sutO = a -> {
                mainFunctionCalled.set(true);
                assertThat(a).isEqualTo(80L);
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
        LLongToShortFunction function = sutO.thenToShort(thenFunction);
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
        LLongToFloatFunction sutO = a -> {
                mainFunctionCalled.set(true);
                assertThat(a).isEqualTo(80L);
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
        LLongToIntFunction function = sutO.thenToInt(thenFunction);
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
        LLongToFloatFunction sutO = a -> {
                mainFunctionCalled.set(true);
                assertThat(a).isEqualTo(80L);
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
        LLongUnaryOperator function = sutO.thenToLong(thenFunction);
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
        LLongToFloatFunction sutO = a -> {
                mainFunctionCalled.set(true);
                assertThat(a).isEqualTo(80L);
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
        LLongToFloatFunction function = sutO.thenToFloat(thenFunction);
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
        LLongToFloatFunction sutO = a -> {
                mainFunctionCalled.set(true);
                assertThat(a).isEqualTo(80L);
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
        LLongToDoubleFunction function = sutO.thenToDouble(thenFunction);
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
        LLongToFloatFunction sutO = a -> {
                mainFunctionCalled.set(true);
                assertThat(a).isEqualTo(80L);
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
        LLongToCharFunction function = sutO.thenToChar(thenFunction);
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
        LLongToFloatFunction sutO = a -> {
                mainFunctionCalled.set(true);
                assertThat(a).isEqualTo(80L);
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
        LLongPredicate function = sutO.thenToBool(thenFunction);
        boolean finalValue = function.doTest(80L);

        //then - finals
        assertThat(finalValue).isEqualTo(true);
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    // </editor-fold>

    @Test
    public void testNesting() {
        assertThat(sut.nestingLongToFloatFunc())
            .isSameAs(sut)
            .isInstanceOf(LLongToFloatFunction.class);
    }

    @Test
    public void testShoving() {
        assertThat(sut.shovingLongToFloatFunc())
            .isSameAs(sut)
            .isInstanceOf(LLongToFloatFunction.class);
    }

    @Test
    public void testNestingX() {
        assertThat(sut.nestingLongToFloatFuncX())
            .isSameAs(sut)
            .isInstanceOf(LLongToFloatFunctionX.class);
    }

    @Test
    public void testShovingX() {
        assertThat(sut.shovingLongToFloatFuncX())
            .isSameAs(sut)
            .isInstanceOf(LLongToFloatFunctionX.class);
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void testShove() {

        // given
        LLongToFloatFunction sutThrowing = LLongToFloatFunction.l(a -> {
            throw new UnsupportedOperationException();
        });

        // when
        sutThrowing.shovingLongToFloatFunc().doApplyAsFloat(100L);
    }

    @Test
    public void testHandleLongToFloatFunc() throws X {

        // given
        LLongToFloatFunction sutThrowing = LLongToFloatFunction.l(a -> {
            throw new UnsupportedOperationException();
        });

        // when
        LLongToFloatFunction wrapped = sutThrowing.handleLongToFloatFunc(h -> {
            h.wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED);
        });

        // then
        try {
            wrapped.doApplyAsFloat(100L);
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
                .contains("LLongToFloatFunction: float doApplyAsFloat(long a)");
    }


    @Test
    public void isThrowing() {
        assertThat(sut.isThrowing())
            .isFalse();
    }

    @Test void safeCompiles() {
        LLongToFloatFunction r1 = LLongToFloatFunction.safe(sut); //NOSONAR
        LLongToFloatFunctionX r2 = LLongToFloatFunction.safe(sut); //NOSONAR
    }

    @Test void safePropagates() {
        Object result = LLongToFloatFunction.safe(sut);
        assertThat(result).isSameAs(sut);
    }

    @Test void safeProtectsAgainstNpe() {
        Object result = LLongToFloatFunction.safe(null);
        assertThat(result).isSameAs(LLongToFloatFunction.l(LLongToFloatFunction.safe()));
    }

    @Test  void safeSupplierPropagates() {
        LSupplier<LLongToFloatFunction> supplier = ()->sut;
        Object result = LLongToFloatFunction.safeSupplier(supplier);
        assertThat(result).isSameAs(supplier);
    }

    @Test  void safeSupplierProtectsAgainstNpe() {
        Object result = LLongToFloatFunction.safeSupplier(null);
        assertThat(result).isSameAs(LLongToFloatFunction.safeSupplier());
    }

    @Test  void safeSupplierCompiles() {
        LSupplier<LLongToFloatFunction> r1 = LLongToFloatFunction.safeSupplier(()->sut);  //NOSONAR
    }

}
