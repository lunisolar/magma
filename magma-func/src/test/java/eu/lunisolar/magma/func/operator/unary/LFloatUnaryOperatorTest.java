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

package eu.lunisolar.magma.func.operator.unary;

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
public class LFloatUnaryOperatorTest<X extends ParseException> {
    private static final String ORIGINAL_MESSAGE = "Original message";
    private static final String EXCEPTION_WAS_WRAPPED = "Exception was wrapped.";
    private static final String NO_EXCEPTION_WERE_THROWN = "No exception were thrown.";

    private float testValue = 100f;



    private LFloatUnaryOperator sut = new LFloatUnaryOperator(){
        public  float doApplyAsFloat(float a)  {
            return testValue;
        }
    };

    private LFloatUnaryOperatorX<X> opposite = new LFloatUnaryOperatorX<X>(){
        public  float doApplyAsFloat(float a)  throws X {
            return testValue;
        }
    };




    private LFloatUnaryOperatorX<RuntimeException> sutAlwaysThrowingUnchecked = LFloatUnaryOperator.l(a -> {
            throw new IndexOutOfBoundsException(ORIGINAL_MESSAGE);
    });


    @Test
    public void testTheResult() throws X {
        assertThat(sut.doApplyAsFloat(100f))
            .isEqualTo(testValue);
    }

    @Test
    public void testTupleCall() throws X {

        LFloatSingle domainObject = Tuple4U.floatSingle(100f);

        Object result = sut.tupleApplyAsFloat(domainObject);

        assertThat(result)
            .isEqualTo(testValue);
    }

    @Test
    public void testNonNullDoApplyAsFloat() throws X {
        assertThat(sut.nonNullDoApplyAsFloat(100f))
            .isEqualTo(testValue);
    }

    @Test
    public void testNestingDoApplyAsFloatUnchecked() throws X {

        // then
        try {
            sutAlwaysThrowingUnchecked.nestingDoApplyAsFloat(100f);
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
            sutAlwaysThrowingUnchecked.shovingDoApplyAsFloat(100f);
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
            .isEqualTo("LFloatUnaryOperator: float doApplyAsFloat(float a)");
    }

    @Test
    public void testLMethod() throws X {
        assertThat(LFloatUnaryOperator.l(a -> testValue ))
            .isInstanceOf(LFloatUnaryOperator.class);
    }

    @Test
    public void testWrapMethod() throws X {
        assertThat(LFloatUnaryOperator.wrap(opposite))
            .isInstanceOf(LFloatUnaryOperator.class);
    }

    @Test
    public void testWrapMethodDoNotWrapsRuntimeException() throws X {
        // given
        LFloatUnaryOperatorX<X> sutThrowing = LFloatUnaryOperatorX.lX(a -> {
            throw new UnsupportedOperationException(ORIGINAL_MESSAGE);
        });

        // when
        LFloatUnaryOperator wrapped = LFloatUnaryOperator.wrap(sutThrowing);

        // then
        try {
            wrapped.doApplyAsFloat(100f);
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
        LFloatUnaryOperatorX<ParseException> sutThrowing = LFloatUnaryOperatorX.lX(a -> {
            throw new ParseException(ORIGINAL_MESSAGE, 0);
        });

        // when
        LFloatUnaryOperator wrapped = LFloatUnaryOperator.wrap(sutThrowing);

        // then
        try {
            wrapped.doApplyAsFloat(100f);
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
        LFloatUnaryOperator sutThrowing = LFloatUnaryOperator.l(a -> {
            throw new UnsupportedOperationException();
        });

        // when
        LFloatUnaryOperator wrapped = sutThrowing.handleFloatUnaryOp(handler -> handler
            .wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED));

        // then
        try {
            wrapped.doApplyAsFloat(100f);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IllegalArgumentException.class)
                    .hasCauseExactlyInstanceOf(UnsupportedOperationException.class)
                    .hasMessage(EXCEPTION_WAS_WRAPPED);
        }
    }

    @Test
    public void testHandleFloatUnaryOpMethodDoNotWrapsOtherExceptionIf() throws X {

        // given
        LFloatUnaryOperator sutThrowing = LFloatUnaryOperator.l(a -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LFloatUnaryOperator wrapped = sutThrowing.handleFloatUnaryOp(handler -> handler
                .wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED)
                .throwIf(IndexOutOfBoundsException.class));

        // then
        try {
            wrapped.doApplyAsFloat(100f);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                    .hasNoCause();
        }
    }

@Test
    public void testHandleFloatUnaryOpMethodDoNotWrapsOtherExceptionWhen() throws X {

        // given
        LFloatUnaryOperator sutThrowing = LFloatUnaryOperator.l(a -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LFloatUnaryOperator wrapped = sutThrowing.handleFloatUnaryOp(handler -> handler
                .wrapWhen(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED)
                .throwIf(IndexOutOfBoundsException.class));

        // then
        try {
            wrapped.doApplyAsFloat(100f);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                    .hasNoCause();
        }
    }


    @Test
    public void testHandleFloatUnaryOpMishandlingExceptionIsAllowed() throws X {

        // given
        LFloatUnaryOperator sutThrowing = LFloatUnaryOperator.l(a -> {
            throw new UnsupportedOperationException(ORIGINAL_MESSAGE);
        });

        // when
        LFloatUnaryOperator wrapped = sutThrowing.handleFloatUnaryOp(h -> Function4U.doNothing());

        // then
        try {
            wrapped.doApplyAsFloat(100f);
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
    public void testFloatUnaryOpComposeFloat() throws X {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LFloatUnaryOperator sutO = a -> {
                mainFunctionCalled.set(true);
                assertThat(a).isEqualTo(90f);
                return 100f;
        };

        LFloatUnaryOperator before = p0 -> {
            assertThat(p0).isEqualTo(80f);
            beforeCalls.incrementAndGet();
            return 90f;
        };

        //when
        LFloatUnaryOperator function = sutO.floatUnaryOpComposeFloat(before);
        function.doApplyAsFloat(80f);

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(beforeCalls.get()).isEqualTo(1);
    }


    @Test
    public void testFloatUnaryOpCompose() throws X {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LFloatUnaryOperator sutO = a -> {
                mainFunctionCalled.set(true);
                assertThat(a).isEqualTo(90f);
                return 100f;
        };

        LToFloatFunction<Integer> before = p0 -> {
            assertThat(p0).isEqualTo(80);
            beforeCalls.incrementAndGet();
            return 90f;
        };

        //when
        LToFloatFunction<Integer> function = sutO.floatUnaryOpCompose(before);
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
        LFloatUnaryOperator sutO = a -> {
                mainFunctionCalled.set(true);
                assertThat(a).isEqualTo(80f);
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
        LFloatFunction<Integer> function = sutO.then(thenFunction);
        Integer finalValue = function.doApply(80f);

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
        LFloatUnaryOperator sutO = a -> {
                mainFunctionCalled.set(true);
                assertThat(a).isEqualTo(80f);
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
        LFloatToByteFunction function = sutO.thenToByte(thenFunction);
        byte finalValue = function.doApplyAsByte(80f);

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
        LFloatUnaryOperator sutO = a -> {
                mainFunctionCalled.set(true);
                assertThat(a).isEqualTo(80f);
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
        LFloatToShortFunction function = sutO.thenToShort(thenFunction);
        short finalValue = function.doApplyAsShort(80f);

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
        LFloatUnaryOperator sutO = a -> {
                mainFunctionCalled.set(true);
                assertThat(a).isEqualTo(80f);
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
        LFloatToIntFunction function = sutO.thenToInt(thenFunction);
        int finalValue = function.doApplyAsInt(80f);

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
        LFloatUnaryOperator sutO = a -> {
                mainFunctionCalled.set(true);
                assertThat(a).isEqualTo(80f);
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
        LFloatToLongFunction function = sutO.thenToLong(thenFunction);
        long finalValue = function.doApplyAsLong(80f);

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
        LFloatUnaryOperator sutO = a -> {
                mainFunctionCalled.set(true);
                assertThat(a).isEqualTo(80f);
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
        LFloatUnaryOperator function = sutO.thenToFloat(thenFunction);
        float finalValue = function.doApplyAsFloat(80f);

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
        LFloatUnaryOperator sutO = a -> {
                mainFunctionCalled.set(true);
                assertThat(a).isEqualTo(80f);
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
        LFloatToDoubleFunction function = sutO.thenToDouble(thenFunction);
        double finalValue = function.doApplyAsDouble(80f);

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
        LFloatUnaryOperator sutO = a -> {
                mainFunctionCalled.set(true);
                assertThat(a).isEqualTo(80f);
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
        LFloatToCharFunction function = sutO.thenToChar(thenFunction);
        char finalValue = function.doApplyAsChar(80f);

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
        LFloatUnaryOperator sutO = a -> {
                mainFunctionCalled.set(true);
                assertThat(a).isEqualTo(80f);
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
        LFloatPredicate function = sutO.thenToBool(thenFunction);
        boolean finalValue = function.doTest(80f);

        //then - finals
        assertThat(finalValue).isEqualTo(true);
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    // </editor-fold>
    @Test
    public void identity() throws X {
        LFloatUnaryOperator identityFunction = LFloatUnaryOperator.identity();

        assertThat(identityFunction.doApplyAsFloat(8f)).isEqualTo(8f);
    }


    @Test
    public void testNesting() {
        assertThat(sut.nestingFloatUnaryOp())
            .isSameAs(sut)
            .isInstanceOf(LFloatUnaryOperator.class);
    }

    @Test
    public void testShoving() {
        assertThat(sut.shovingFloatUnaryOp())
            .isSameAs(sut)
            .isInstanceOf(LFloatUnaryOperator.class);
    }

    @Test
    public void testNestingX() {
        assertThat(sut.nestingFloatUnaryOpX())
            .isSameAs(sut)
            .isInstanceOf(LFloatUnaryOperatorX.class);
    }

    @Test
    public void testShovingX() {
        assertThat(sut.shovingFloatUnaryOpX())
            .isSameAs(sut)
            .isInstanceOf(LFloatUnaryOperatorX.class);
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void testShove() {

        // given
        LFloatUnaryOperator sutThrowing = LFloatUnaryOperator.l(a -> {
            throw new UnsupportedOperationException();
        });

        // when
        sutThrowing.shovingFloatUnaryOp().doApplyAsFloat(100f);
    }

    @Test
    public void testHandleFloatUnaryOp() throws X {

        // given
        LFloatUnaryOperator sutThrowing = LFloatUnaryOperator.l(a -> {
            throw new UnsupportedOperationException();
        });

        // when
        LFloatUnaryOperator wrapped = sutThrowing.handleFloatUnaryOp(h -> {
            h.wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED);
        });

        // then
        try {
            wrapped.doApplyAsFloat(100f);
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
                .contains("LFloatUnaryOperator: float doApplyAsFloat(float a)");
    }


    @Test
    public void isThrowing() {
        assertThat(sut.isThrowing())
            .isFalse();
    }

    @Test void safeCompiles() {
        LFloatUnaryOperator r1 = LFloatUnaryOperator.safe(sut); //NOSONAR
        LFloatUnaryOperatorX r2 = LFloatUnaryOperator.safe(sut); //NOSONAR
    }

    @Test void safePropagates() {
        Object result = LFloatUnaryOperator.safe(sut);
        assertThat(result).isSameAs(sut);
    }

    @Test void safeProtectsAgainstNpe() {
        Object result = LFloatUnaryOperator.safe(null);
        assertThat(result).isSameAs(LFloatUnaryOperator.l(LFloatUnaryOperator.safe()));
    }

    @Test  void safeSupplierPropagates() {
        LSupplier<LFloatUnaryOperator> supplier = ()->sut;
        Object result = LFloatUnaryOperator.safeSupplier(supplier);
        assertThat(result).isSameAs(supplier);
    }

    @Test  void safeSupplierProtectsAgainstNpe() {
        Object result = LFloatUnaryOperator.safeSupplier(null);
        assertThat(result).isSameAs(LFloatUnaryOperator.safeSupplier());
    }

    @Test  void safeSupplierCompiles() {
        LSupplier<LFloatUnaryOperator> r1 = LFloatUnaryOperator.safeSupplier(()->sut);  //NOSONAR
    }

}
