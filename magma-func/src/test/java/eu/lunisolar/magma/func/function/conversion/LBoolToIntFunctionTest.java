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
public class LBoolToIntFunctionTest<X extends ParseException> {
    private static final String ORIGINAL_MESSAGE = "Original message";
    private static final String EXCEPTION_WAS_WRAPPED = "Exception was wrapped.";
    private static final String NO_EXCEPTION_WERE_THROWN = "No exception were thrown.";

    private int testValue = 100;



    private LBoolToIntFunction sut = new LBoolToIntFunction(){
        public  int doApplyAsInt(boolean a1)  {
            return testValue;
        }
    };

    private LBoolToIntFunctionX<X> opposite = new LBoolToIntFunctionX<X>(){
        public  int doApplyAsInt(boolean a1)  throws X {
            return testValue;
        }
    };




    private LBoolToIntFunctionX<RuntimeException> sutAlwaysThrowingUnchecked = LBoolToIntFunction.l(a1 -> {
            throw new IndexOutOfBoundsException(ORIGINAL_MESSAGE);
    });


    @Test
    public void testTheResult() throws X {
        assertThat(sut.doApplyAsInt(true))
            .isEqualTo(testValue);
    }

    @Test
    public void testTupleCall() throws X {

        LBoolSingle domainObject = Tuple4U.boolSingle(true);

        Object result = sut.tupleApplyAsInt(domainObject);

        assertThat(result)
            .isEqualTo(testValue);
    }

    @Test
    public void testNonNullDoApplyAsInt() throws X {
        assertThat(sut.nonNullDoApplyAsInt(true))
            .isEqualTo(testValue);
    }

    @Test
    public void testNestingDoApplyAsIntUnchecked() throws X {

        // then
        try {
            sutAlwaysThrowingUnchecked.nestingDoApplyAsInt(true);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                    .hasNoCause()
                    .hasMessage(ORIGINAL_MESSAGE);
        }
    }

    @Test
    public void testShovingDoApplyAsIntUnchecked() throws X {

        // then
        try {
            sutAlwaysThrowingUnchecked.shovingDoApplyAsInt(true);
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
            .isEqualTo("LBoolToIntFunction: int doApplyAsInt(boolean a1)");
    }

    @Test
    public void testLMethod() throws X {
        assertThat(LBoolToIntFunction.l(a1 -> testValue ))
            .isInstanceOf(LBoolToIntFunction.class);
    }

    @Test
    public void testWrapMethod() throws X {
        assertThat(LBoolToIntFunction.wrap(opposite))
            .isInstanceOf(LBoolToIntFunction.class);
    }

    @Test
    public void testWrapMethodDoNotWrapsRuntimeException() throws X {
        // given
        LBoolToIntFunctionX<X> sutThrowing = LBoolToIntFunctionX.lX(a1 -> {
            throw new UnsupportedOperationException(ORIGINAL_MESSAGE);
        });

        // when
        LBoolToIntFunction wrapped = LBoolToIntFunction.wrap(sutThrowing);

        // then
        try {
            wrapped.doApplyAsInt(true);
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
        LBoolToIntFunctionX<ParseException> sutThrowing = LBoolToIntFunctionX.lX(a1 -> {
            throw new ParseException(ORIGINAL_MESSAGE, 0);
        });

        // when
        LBoolToIntFunction wrapped = LBoolToIntFunction.wrap(sutThrowing);

        // then
        try {
            wrapped.doApplyAsInt(true);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(NestedException.class)
                    .hasCauseExactlyInstanceOf(ParseException.class)
                    .hasMessage(ORIGINAL_MESSAGE);
        }
    }


    @Test
    public void testHandlingDoApplyAsIntMethodWrapsTheException() throws X {

        // given
        LBoolToIntFunction sutThrowing = LBoolToIntFunction.l(a1 -> {
            throw new UnsupportedOperationException();
        });

        // when
        LBoolToIntFunction wrapped = sutThrowing.handleBoolToIntFunc(handler -> handler
            .wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED));

        // then
        try {
            wrapped.doApplyAsInt(true);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IllegalArgumentException.class)
                    .hasCauseExactlyInstanceOf(UnsupportedOperationException.class)
                    .hasMessage(EXCEPTION_WAS_WRAPPED);
        }
    }

    @Test
    public void testHandleBoolToIntFuncMethodDoNotWrapsOtherExceptionIf() throws X {

        // given
        LBoolToIntFunction sutThrowing = LBoolToIntFunction.l(a1 -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LBoolToIntFunction wrapped = sutThrowing.handleBoolToIntFunc(handler -> handler
                .wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED)
                .throwIf(IndexOutOfBoundsException.class));

        // then
        try {
            wrapped.doApplyAsInt(true);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                    .hasNoCause();
        }
    }

@Test
    public void testHandleBoolToIntFuncMethodDoNotWrapsOtherExceptionWhen() throws X {

        // given
        LBoolToIntFunction sutThrowing = LBoolToIntFunction.l(a1 -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LBoolToIntFunction wrapped = sutThrowing.handleBoolToIntFunc(handler -> handler
                .wrapWhen(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED)
                .throwIf(IndexOutOfBoundsException.class));

        // then
        try {
            wrapped.doApplyAsInt(true);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                    .hasNoCause();
        }
    }


    @Test
    public void testHandleBoolToIntFuncMishandlingExceptionIsAllowed() throws X {

        // given
        LBoolToIntFunction sutThrowing = LBoolToIntFunction.l(a1 -> {
            throw new UnsupportedOperationException(ORIGINAL_MESSAGE);
        });

        // when
        LBoolToIntFunction wrapped = sutThrowing.handleBoolToIntFunc(h -> Function4U.doNothing());

        // then
        try {
            wrapped.doApplyAsInt(true);
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
    public void testBoolToIntFuncComposeBool() throws X {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LBoolToIntFunction sutO = a1 -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(true);
                return 100;
        };

        LLogicalOperator before1 = p0 -> {
            assertThat(p0).isEqualTo(true);
            beforeCalls.incrementAndGet();
            return true;
        };

        //when
        LBoolToIntFunction function = sutO.boolToIntFuncComposeBool(before1);
        function.doApplyAsInt(true);

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(beforeCalls.get()).isEqualTo(1);
    }


    @Test
    public void testBoolToIntFuncCompose() throws X {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LBoolToIntFunction sutO = a1 -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(true);
                return 100;
        };

        LPredicate<Integer> before1 = p0 -> {
            assertThat(p0).isEqualTo(80);
            beforeCalls.incrementAndGet();
            return true;
        };

        //when
        LToIntFunction<Integer> function = sutO.boolToIntFuncCompose(before1);
        function.doApplyAsInt(80);

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
        LBoolToIntFunction sutO = a1 -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(true);
                return 90;
        };

        LIntFunction<Integer> thenFunction = p -> {
                thenFunctionCalled.set(true);
                // int
                assertThat(p).isEqualTo(90);
                // Integer
                return 100;
        };

        //when
        LBoolFunction<Integer> function = sutO.then(thenFunction);
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
        LBoolToIntFunction sutO = a1 -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(true);
                return 90;
        };

        LIntToByteFunction thenFunction = p -> {
                thenFunctionCalled.set(true);
                // int
                assertThat(p).isEqualTo(90);
                // byte
                return (byte)100;
        };

        //when
        LBoolToByteFunction function = sutO.thenToByte(thenFunction);
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
        LBoolToIntFunction sutO = a1 -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(true);
                return 90;
        };

        LIntToShortFunction thenFunction = p -> {
                thenFunctionCalled.set(true);
                // int
                assertThat(p).isEqualTo(90);
                // short
                return (short)100;
        };

        //when
        LBoolToShortFunction function = sutO.thenToShort(thenFunction);
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
        LBoolToIntFunction sutO = a1 -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(true);
                return 90;
        };

        LIntUnaryOperator thenFunction = p -> {
                thenFunctionCalled.set(true);
                // int
                assertThat(p).isEqualTo(90);
                // int
                return 100;
        };

        //when
        LBoolToIntFunction function = sutO.thenToInt(thenFunction);
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
        LBoolToIntFunction sutO = a1 -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(true);
                return 90;
        };

        LIntToLongFunction thenFunction = p -> {
                thenFunctionCalled.set(true);
                // int
                assertThat(p).isEqualTo(90);
                // long
                return 100L;
        };

        //when
        LBoolToLongFunction function = sutO.thenToLong(thenFunction);
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
        LBoolToIntFunction sutO = a1 -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(true);
                return 90;
        };

        LIntToFloatFunction thenFunction = p -> {
                thenFunctionCalled.set(true);
                // int
                assertThat(p).isEqualTo(90);
                // float
                return 100f;
        };

        //when
        LBoolToFloatFunction function = sutO.thenToFloat(thenFunction);
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
        LBoolToIntFunction sutO = a1 -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(true);
                return 90;
        };

        LIntToDoubleFunction thenFunction = p -> {
                thenFunctionCalled.set(true);
                // int
                assertThat(p).isEqualTo(90);
                // double
                return 100d;
        };

        //when
        LBoolToDoubleFunction function = sutO.thenToDouble(thenFunction);
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
        LBoolToIntFunction sutO = a1 -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(true);
                return 90;
        };

        LIntToCharFunction thenFunction = p -> {
                thenFunctionCalled.set(true);
                // int
                assertThat(p).isEqualTo(90);
                // char
                return '\u0100';
        };

        //when
        LBoolToCharFunction function = sutO.thenToChar(thenFunction);
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
        LBoolToIntFunction sutO = a1 -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(true);
                return 90;
        };

        LIntPredicate thenFunction = p -> {
                thenFunctionCalled.set(true);
                // int
                assertThat(p).isEqualTo(90);
                // boolean
                return true;
        };

        //when
        LLogicalOperator function = sutO.thenToBool(thenFunction);
        boolean finalValue = function.doApply(true);

        //then - finals
        assertThat(finalValue).isEqualTo(true);
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    // </editor-fold>

    @Test
    public void testNesting() {
        assertThat(sut.nestingBoolToIntFunc())
            .isSameAs(sut)
            .isInstanceOf(LBoolToIntFunction.class);
    }

    @Test
    public void testShoving() {
        assertThat(sut.shovingBoolToIntFunc())
            .isSameAs(sut)
            .isInstanceOf(LBoolToIntFunction.class);
    }

    @Test
    public void testNestingX() {
        assertThat(sut.nestingBoolToIntFuncX())
            .isSameAs(sut)
            .isInstanceOf(LBoolToIntFunctionX.class);
    }

    @Test
    public void testShovingX() {
        assertThat(sut.shovingBoolToIntFuncX())
            .isSameAs(sut)
            .isInstanceOf(LBoolToIntFunctionX.class);
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void testShove() {

        // given
        LBoolToIntFunction sutThrowing = LBoolToIntFunction.l(a1 -> {
            throw new UnsupportedOperationException();
        });

        // when
        sutThrowing.shovingBoolToIntFunc().doApplyAsInt(true);
    }

    @Test
    public void testHandleBoolToIntFunc() throws X {

        // given
        LBoolToIntFunction sutThrowing = LBoolToIntFunction.l(a1 -> {
            throw new UnsupportedOperationException();
        });

        // when
        LBoolToIntFunction wrapped = sutThrowing.handleBoolToIntFunc(h -> {
            h.wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED);
        });

        // then
        try {
            wrapped.doApplyAsInt(true);
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
                .contains("LBoolToIntFunction: int doApplyAsInt(boolean a1)");
    }


    @Test
    public void isThrowing() {
        assertThat(sut.isThrowing())
            .isFalse();
    }

    @Test void safeCompiles() {
        LBoolToIntFunction r1 = LBoolToIntFunction.safe(sut); //NOSONAR
        LBoolToIntFunctionX r2 = LBoolToIntFunction.safe(sut); //NOSONAR
    }

    @Test void safePropagates() {
        Object result = LBoolToIntFunction.safe(sut);
        assertThat(result).isSameAs(sut);
    }

    @Test void safeProtectsAgainstNpe() {
        Object result = LBoolToIntFunction.safe(null);
        assertThat(result).isSameAs(LBoolToIntFunction.l(LBoolToIntFunction.safe()));
    }

    @Test  void safeSupplierPropagates() {
        LSupplier<LBoolToIntFunction> supplier = ()->sut;
        Object result = LBoolToIntFunction.safeSupplier(supplier);
        assertThat(result).isSameAs(supplier);
    }

    @Test  void safeSupplierProtectsAgainstNpe() {
        Object result = LBoolToIntFunction.safeSupplier(null);
        assertThat(result).isSameAs(LBoolToIntFunction.safeSupplier());
    }

    @Test  void safeSupplierCompiles() {
        LSupplier<LBoolToIntFunction> r1 = LBoolToIntFunction.safeSupplier(()->sut);  //NOSONAR
    }

}
