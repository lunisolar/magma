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
public class LDoubleToCharFunctionTest<X extends ParseException> {
    private static final String ORIGINAL_MESSAGE = "Original message";
    private static final String EXCEPTION_WAS_WRAPPED = "Exception was wrapped.";
    private static final String NO_EXCEPTION_WERE_THROWN = "No exception were thrown.";

    private char testValue = '\u0100';



    private LDoubleToCharFunction sut = new LDoubleToCharFunction(){
        public  char doApplyAsChar(double a)  {
            return testValue;
        }
    };

    private LDoubleToCharFunctionX<X> opposite = new LDoubleToCharFunctionX<X>(){
        public  char doApplyAsChar(double a)  throws X {
            return testValue;
        }
    };




    private LDoubleToCharFunctionX<RuntimeException> sutAlwaysThrowingUnchecked = LDoubleToCharFunction.l(a -> {
            throw new IndexOutOfBoundsException(ORIGINAL_MESSAGE);
    });


    @Test
    public void testTheResult() throws X {
        assertThat(sut.doApplyAsChar(100d))
            .isEqualTo(testValue);
    }

    @Test
    public void testTupleCall() throws X {

        LDoubleSingle domainObject = Tuple4U.doubleSingle(100d);

        Object result = sut.tupleApplyAsChar(domainObject);

        assertThat(result)
            .isEqualTo(testValue);
    }

    @Test
    public void testNonNullDoApplyAsChar() throws X {
        assertThat(sut.nonNullDoApplyAsChar(100d))
            .isEqualTo(testValue);
    }

    @Test
    public void testNestingDoApplyAsCharUnchecked() throws X {

        // then
        try {
            sutAlwaysThrowingUnchecked.nestingDoApplyAsChar(100d);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                    .hasNoCause()
                    .hasMessage(ORIGINAL_MESSAGE);
        }
    }

    @Test
    public void testShovingDoApplyAsCharUnchecked() throws X {

        // then
        try {
            sutAlwaysThrowingUnchecked.shovingDoApplyAsChar(100d);
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
            .isEqualTo("LDoubleToCharFunction: char doApplyAsChar(double a)");
    }

    @Test
    public void testLMethod() throws X {
        assertThat(LDoubleToCharFunction.l(a -> testValue ))
            .isInstanceOf(LDoubleToCharFunction.class);
    }

    @Test
    public void testWrapMethod() throws X {
        assertThat(LDoubleToCharFunction.wrap(opposite))
            .isInstanceOf(LDoubleToCharFunction.class);
    }

    @Test
    public void testWrapMethodDoNotWrapsRuntimeException() throws X {
        // given
        LDoubleToCharFunctionX<X> sutThrowing = LDoubleToCharFunctionX.lX(a -> {
            throw new UnsupportedOperationException(ORIGINAL_MESSAGE);
        });

        // when
        LDoubleToCharFunction wrapped = LDoubleToCharFunction.wrap(sutThrowing);

        // then
        try {
            wrapped.doApplyAsChar(100d);
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
        LDoubleToCharFunctionX<ParseException> sutThrowing = LDoubleToCharFunctionX.lX(a -> {
            throw new ParseException(ORIGINAL_MESSAGE, 0);
        });

        // when
        LDoubleToCharFunction wrapped = LDoubleToCharFunction.wrap(sutThrowing);

        // then
        try {
            wrapped.doApplyAsChar(100d);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(NestedException.class)
                    .hasCauseExactlyInstanceOf(ParseException.class)
                    .hasMessage(ORIGINAL_MESSAGE);
        }
    }


    @Test
    public void testHandlingDoApplyAsCharMethodWrapsTheException() throws X {

        // given
        LDoubleToCharFunction sutThrowing = LDoubleToCharFunction.l(a -> {
            throw new UnsupportedOperationException();
        });

        // when
        LDoubleToCharFunction wrapped = sutThrowing.handleDoubleToCharFunc(handler -> handler
            .wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED));

        // then
        try {
            wrapped.doApplyAsChar(100d);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IllegalArgumentException.class)
                    .hasCauseExactlyInstanceOf(UnsupportedOperationException.class)
                    .hasMessage(EXCEPTION_WAS_WRAPPED);
        }
    }

    @Test
    public void testHandleDoubleToCharFuncMethodDoNotWrapsOtherExceptionIf() throws X {

        // given
        LDoubleToCharFunction sutThrowing = LDoubleToCharFunction.l(a -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LDoubleToCharFunction wrapped = sutThrowing.handleDoubleToCharFunc(handler -> handler
                .wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED)
                .throwIf(IndexOutOfBoundsException.class));

        // then
        try {
            wrapped.doApplyAsChar(100d);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                    .hasNoCause();
        }
    }

@Test
    public void testHandleDoubleToCharFuncMethodDoNotWrapsOtherExceptionWhen() throws X {

        // given
        LDoubleToCharFunction sutThrowing = LDoubleToCharFunction.l(a -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LDoubleToCharFunction wrapped = sutThrowing.handleDoubleToCharFunc(handler -> handler
                .wrapWhen(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED)
                .throwIf(IndexOutOfBoundsException.class));

        // then
        try {
            wrapped.doApplyAsChar(100d);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                    .hasNoCause();
        }
    }


    @Test
    public void testHandleDoubleToCharFuncMishandlingExceptionIsAllowed() throws X {

        // given
        LDoubleToCharFunction sutThrowing = LDoubleToCharFunction.l(a -> {
            throw new UnsupportedOperationException(ORIGINAL_MESSAGE);
        });

        // when
        LDoubleToCharFunction wrapped = sutThrowing.handleDoubleToCharFunc(h -> Function4U.doNothing());

        // then
        try {
            wrapped.doApplyAsChar(100d);
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
    public void testDoubleToCharFuncComposeDouble() throws X {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LDoubleToCharFunction sutO = a -> {
                mainFunctionCalled.set(true);
                assertThat(a).isEqualTo(90d);
                return '\u0100';
        };

        LDoubleUnaryOperator before = p0 -> {
            assertThat(p0).isEqualTo(80d);
            beforeCalls.incrementAndGet();
            return 90d;
        };

        //when
        LDoubleToCharFunction function = sutO.doubleToCharFuncComposeDouble(before);
        function.doApplyAsChar(80d);

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(beforeCalls.get()).isEqualTo(1);
    }


    @Test
    public void testDoubleToCharFuncCompose() throws X {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LDoubleToCharFunction sutO = a -> {
                mainFunctionCalled.set(true);
                assertThat(a).isEqualTo(90d);
                return '\u0100';
        };

        LToDoubleFunction<Integer> before = p0 -> {
            assertThat(p0).isEqualTo(80);
            beforeCalls.incrementAndGet();
            return 90d;
        };

        //when
        LToCharFunction<Integer> function = sutO.doubleToCharFuncCompose(before);
        function.doApplyAsChar(80);

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
        LDoubleToCharFunction sutO = a -> {
                mainFunctionCalled.set(true);
                assertThat(a).isEqualTo(80d);
                return '\u0090';
        };

        LCharFunction<Integer> thenFunction = p -> {
                thenFunctionCalled.set(true);
                // char
                assertThat(p).isEqualTo('\u0090');
                // Integer
                return 100;
        };

        //when
        LDoubleFunction<Integer> function = sutO.then(thenFunction);
        Integer finalValue = function.doApply(80d);

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
        LDoubleToCharFunction sutO = a -> {
                mainFunctionCalled.set(true);
                assertThat(a).isEqualTo(80d);
                return '\u0090';
        };

        LCharToByteFunction thenFunction = p -> {
                thenFunctionCalled.set(true);
                // char
                assertThat(p).isEqualTo('\u0090');
                // byte
                return (byte)100;
        };

        //when
        LDoubleToByteFunction function = sutO.thenToByte(thenFunction);
        byte finalValue = function.doApplyAsByte(80d);

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
        LDoubleToCharFunction sutO = a -> {
                mainFunctionCalled.set(true);
                assertThat(a).isEqualTo(80d);
                return '\u0090';
        };

        LCharToShortFunction thenFunction = p -> {
                thenFunctionCalled.set(true);
                // char
                assertThat(p).isEqualTo('\u0090');
                // short
                return (short)100;
        };

        //when
        LDoubleToShortFunction function = sutO.thenToShort(thenFunction);
        short finalValue = function.doApplyAsShort(80d);

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
        LDoubleToCharFunction sutO = a -> {
                mainFunctionCalled.set(true);
                assertThat(a).isEqualTo(80d);
                return '\u0090';
        };

        LCharToIntFunction thenFunction = p -> {
                thenFunctionCalled.set(true);
                // char
                assertThat(p).isEqualTo('\u0090');
                // int
                return 100;
        };

        //when
        LDoubleToIntFunction function = sutO.thenToInt(thenFunction);
        int finalValue = function.doApplyAsInt(80d);

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
        LDoubleToCharFunction sutO = a -> {
                mainFunctionCalled.set(true);
                assertThat(a).isEqualTo(80d);
                return '\u0090';
        };

        LCharToLongFunction thenFunction = p -> {
                thenFunctionCalled.set(true);
                // char
                assertThat(p).isEqualTo('\u0090');
                // long
                return 100L;
        };

        //when
        LDoubleToLongFunction function = sutO.thenToLong(thenFunction);
        long finalValue = function.doApplyAsLong(80d);

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
        LDoubleToCharFunction sutO = a -> {
                mainFunctionCalled.set(true);
                assertThat(a).isEqualTo(80d);
                return '\u0090';
        };

        LCharToFloatFunction thenFunction = p -> {
                thenFunctionCalled.set(true);
                // char
                assertThat(p).isEqualTo('\u0090');
                // float
                return 100f;
        };

        //when
        LDoubleToFloatFunction function = sutO.thenToFloat(thenFunction);
        float finalValue = function.doApplyAsFloat(80d);

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
        LDoubleToCharFunction sutO = a -> {
                mainFunctionCalled.set(true);
                assertThat(a).isEqualTo(80d);
                return '\u0090';
        };

        LCharToDoubleFunction thenFunction = p -> {
                thenFunctionCalled.set(true);
                // char
                assertThat(p).isEqualTo('\u0090');
                // double
                return 100d;
        };

        //when
        LDoubleUnaryOperator function = sutO.thenToDouble(thenFunction);
        double finalValue = function.doApplyAsDouble(80d);

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
        LDoubleToCharFunction sutO = a -> {
                mainFunctionCalled.set(true);
                assertThat(a).isEqualTo(80d);
                return '\u0090';
        };

        LCharUnaryOperator thenFunction = p -> {
                thenFunctionCalled.set(true);
                // char
                assertThat(p).isEqualTo('\u0090');
                // char
                return '\u0100';
        };

        //when
        LDoubleToCharFunction function = sutO.thenToChar(thenFunction);
        char finalValue = function.doApplyAsChar(80d);

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
        LDoubleToCharFunction sutO = a -> {
                mainFunctionCalled.set(true);
                assertThat(a).isEqualTo(80d);
                return '\u0090';
        };

        LCharPredicate thenFunction = p -> {
                thenFunctionCalled.set(true);
                // char
                assertThat(p).isEqualTo('\u0090');
                // boolean
                return true;
        };

        //when
        LDoublePredicate function = sutO.thenToBool(thenFunction);
        boolean finalValue = function.doTest(80d);

        //then - finals
        assertThat(finalValue).isEqualTo(true);
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    // </editor-fold>

    @Test
    public void testNesting() {
        assertThat(sut.nestingDoubleToCharFunc())
            .isSameAs(sut)
            .isInstanceOf(LDoubleToCharFunction.class);
    }

    @Test
    public void testShoving() {
        assertThat(sut.shovingDoubleToCharFunc())
            .isSameAs(sut)
            .isInstanceOf(LDoubleToCharFunction.class);
    }

    @Test
    public void testNestingX() {
        assertThat(sut.nestingDoubleToCharFuncX())
            .isSameAs(sut)
            .isInstanceOf(LDoubleToCharFunctionX.class);
    }

    @Test
    public void testShovingX() {
        assertThat(sut.shovingDoubleToCharFuncX())
            .isSameAs(sut)
            .isInstanceOf(LDoubleToCharFunctionX.class);
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void testShove() {

        // given
        LDoubleToCharFunction sutThrowing = LDoubleToCharFunction.l(a -> {
            throw new UnsupportedOperationException();
        });

        // when
        sutThrowing.shovingDoubleToCharFunc().doApplyAsChar(100d);
    }

    @Test
    public void testHandleDoubleToCharFunc() throws X {

        // given
        LDoubleToCharFunction sutThrowing = LDoubleToCharFunction.l(a -> {
            throw new UnsupportedOperationException();
        });

        // when
        LDoubleToCharFunction wrapped = sutThrowing.handleDoubleToCharFunc(h -> {
            h.wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED);
        });

        // then
        try {
            wrapped.doApplyAsChar(100d);
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
                .contains("LDoubleToCharFunction: char doApplyAsChar(double a)");
    }


    @Test
    public void isThrowing() {
        assertThat(sut.isThrowing())
            .isFalse();
    }

    @Test void safeCompiles() {
        LDoubleToCharFunction r1 = LDoubleToCharFunction.safe(sut); //NOSONAR
        LDoubleToCharFunctionX r2 = LDoubleToCharFunction.safe(sut); //NOSONAR
    }

    @Test void safePropagates() {
        Object result = LDoubleToCharFunction.safe(sut);
        assertThat(result).isSameAs(sut);
    }

    @Test void safeProtectsAgainstNpe() {
        Object result = LDoubleToCharFunction.safe(null);
        assertThat(result).isSameAs(LDoubleToCharFunction.l(LDoubleToCharFunction.safe()));
    }

    @Test  void safeSupplierPropagates() {
        LSupplier<LDoubleToCharFunction> supplier = ()->sut;
        Object result = LDoubleToCharFunction.safeSupplier(supplier);
        assertThat(result).isSameAs(supplier);
    }

    @Test  void safeSupplierProtectsAgainstNpe() {
        Object result = LDoubleToCharFunction.safeSupplier(null);
        assertThat(result).isSameAs(LDoubleToCharFunction.safeSupplier());
    }

    @Test  void safeSupplierCompiles() {
        LSupplier<LDoubleToCharFunction> r1 = LDoubleToCharFunction.safeSupplier(()->sut);  //NOSONAR
    }

}
