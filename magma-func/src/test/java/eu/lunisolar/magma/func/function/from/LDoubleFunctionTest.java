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

package eu.lunisolar.magma.func.function.from;

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
public class LDoubleFunctionTest<R,X extends ParseException> {
    private static final String ORIGINAL_MESSAGE = "Original message";
    private static final String EXCEPTION_WAS_WRAPPED = "Exception was wrapped.";
    private static final String NO_EXCEPTION_WERE_THROWN = "No exception were thrown.";

    private Integer testValue = 100;



    private LDoubleFunction<Integer> sut = new LDoubleFunction<Integer>(){
        public @Nullable Integer doApply(double a)  {
            return testValue;
        }
    };

    private LDoubleFunctionX<Integer,X> opposite = new LDoubleFunctionX<Integer,X>(){
        public @Nullable Integer doApply(double a)  throws X {
            return testValue;
        }
    };

    private LDoubleFunction<Integer> sutNull = new LDoubleFunction<Integer>(){
        public @Nullable Integer doApply(double a)  {
            return null;
        }
    };


    private DoubleFunction<Integer> jre = a -> testValue;



    private LDoubleFunctionX<Integer,RuntimeException> sutAlwaysThrowingUnchecked = LDoubleFunction.l(a -> {
            throw new IndexOutOfBoundsException(ORIGINAL_MESSAGE);
    });


    @Test
    public void testTheResult() throws X {
        assertThat(sut.doApply(100d))
            .isEqualTo(testValue);
    }

    @Test
    public void testTupleCall() throws X {

        LDoubleSingle domainObject = Tuple4U.doubleSingle(100d);

        Object result = sut.tupleApply(domainObject);

        assertThat(result)
            .isEqualTo(testValue);
    }

    @Test
    public void testNonNullDoApply() throws X {
        assertThat(sut.nonNullDoApply(100d))
            .isSameAs(testValue);
    }

    @Test
    public void testNestingDoApplyUnchecked() throws X {

        // then
        try {
            sutAlwaysThrowingUnchecked.nestingDoApply(100d);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                    .hasNoCause()
                    .hasMessage(ORIGINAL_MESSAGE);
        }
    }

    @Test
    public void testShovingDoApplyUnchecked() throws X {

        // then
        try {
            sutAlwaysThrowingUnchecked.shovingDoApply(100d);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                    .hasNoCause()
                    .hasMessage(ORIGINAL_MESSAGE);
        }
    }

    @Test(expectedExceptions=NullPointerException.class, expectedExceptionsMessageRegExp="\\QEvaluated value by nonNullDoApply() method cannot be null (LDoubleFunction: R doApply(double a)).\\E")
    public void testNonNullCapturesNull() throws X {
        sutNull.nonNullDoApply(100d);
    }


    @Test
    public void testFunctionalInterfaceDescription() throws X {
        assertThat(sut.functionalInterfaceDescription())
            .isEqualTo("LDoubleFunction: R doApply(double a)");
    }

    @Test
    public void testLMethod() throws X {
        assertThat(LDoubleFunction.l(a -> testValue ))
            .isInstanceOf(LDoubleFunction.class);
    }

    @Test
    public void testWrapMethod() throws X {
        assertThat(LDoubleFunction.wrap(opposite))
            .isInstanceOf(LDoubleFunction.class);
    }

    @Test
    public void testWrapStdMethod() throws X {
        assertThat(LDoubleFunction.wrap(jre))
            .isInstanceOf(LDoubleFunction.class);
    }

    @Test
    public void testWrapMethodDoNotWrapsRuntimeException() throws X {
        // given
        LDoubleFunctionX<Integer,X> sutThrowing = LDoubleFunctionX.lX(a -> {
            throw new UnsupportedOperationException(ORIGINAL_MESSAGE);
        });

        // when
        LDoubleFunction<Integer> wrapped = LDoubleFunction.wrap(sutThrowing);

        // then
        try {
            wrapped.doApply(100d);
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
        LDoubleFunctionX<Integer,ParseException> sutThrowing = LDoubleFunctionX.lX(a -> {
            throw new ParseException(ORIGINAL_MESSAGE, 0);
        });

        // when
        LDoubleFunction<Integer> wrapped = LDoubleFunction.wrap(sutThrowing);

        // then
        try {
            wrapped.doApply(100d);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(NestedException.class)
                    .hasCauseExactlyInstanceOf(ParseException.class)
                    .hasMessage(ORIGINAL_MESSAGE);
        }
    }


    @Test
    public void testHandlingDoApplyMethodWrapsTheException() throws X {

        // given
        LDoubleFunction<Integer> sutThrowing = LDoubleFunction.l(a -> {
            throw new UnsupportedOperationException();
        });

        // when
        LDoubleFunction<Integer> wrapped = sutThrowing.handleDoubleFunc(handler -> handler
            .wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED));

        // then
        try {
            wrapped.doApply(100d);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IllegalArgumentException.class)
                    .hasCauseExactlyInstanceOf(UnsupportedOperationException.class)
                    .hasMessage(EXCEPTION_WAS_WRAPPED);
        }
    }

    @Test
    public void testHandleDoubleFuncMethodDoNotWrapsOtherExceptionIf() throws X {

        // given
        LDoubleFunction<Integer> sutThrowing = LDoubleFunction.l(a -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LDoubleFunction<Integer> wrapped = sutThrowing.handleDoubleFunc(handler -> handler
                .wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED)
                .throwIf(IndexOutOfBoundsException.class));

        // then
        try {
            wrapped.doApply(100d);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                    .hasNoCause();
        }
    }

@Test
    public void testHandleDoubleFuncMethodDoNotWrapsOtherExceptionWhen() throws X {

        // given
        LDoubleFunction<Integer> sutThrowing = LDoubleFunction.l(a -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LDoubleFunction<Integer> wrapped = sutThrowing.handleDoubleFunc(handler -> handler
                .wrapWhen(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED)
                .throwIf(IndexOutOfBoundsException.class));

        // then
        try {
            wrapped.doApply(100d);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                    .hasNoCause();
        }
    }


    @Test
    public void testHandleDoubleFuncMishandlingExceptionIsAllowed() throws X {

        // given
        LDoubleFunction<Integer> sutThrowing = LDoubleFunction.l(a -> {
            throw new UnsupportedOperationException(ORIGINAL_MESSAGE);
        });

        // when
        LDoubleFunction<Integer> wrapped = sutThrowing.handleDoubleFunc(h -> Function4U.doNothing());

        // then
        try {
            wrapped.doApply(100d);
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
    public void testDoubleFuncComposeDouble() throws X {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LDoubleFunction<Integer> sutO = a -> {
                mainFunctionCalled.set(true);
                assertThat(a).isEqualTo(90d);
                return 100;
        };

        LDoubleUnaryOperator before = p0 -> {
            assertThat(p0).isEqualTo(80d);
            beforeCalls.incrementAndGet();
            return 90d;
        };

        //when
        LDoubleFunction<Integer> function = sutO.doubleFuncComposeDouble(before);
        function.doApply(80d);

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(beforeCalls.get()).isEqualTo(1);
    }


    @Test
    public void testDoubleFuncCompose() throws X {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LDoubleFunction<Integer> sutO = a -> {
                mainFunctionCalled.set(true);
                assertThat(a).isEqualTo(90d);
                return 100;
        };

        LToDoubleFunction<Integer> before = p0 -> {
            assertThat(p0).isEqualTo(80);
            beforeCalls.incrementAndGet();
            return 90d;
        };

        //when
        LFunction<Integer,Integer> function = sutO.doubleFuncCompose(before);
        function.doApply(80);

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
        LDoubleFunction<Integer> sutO = a -> {
                mainFunctionCalled.set(true);
                assertThat(a).isEqualTo(80d);
                return 90;
        };

        LFunction<Integer,Integer> thenFunction = p -> {
                thenFunctionCalled.set(true);
                // Integer
                assertThat(p).isEqualTo(90);
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
    public void testThen1() throws X  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);

        //given (+ some assertions)
        LDoubleFunction<Integer> sutO = a -> {
                mainFunctionCalled.set(true);
                assertThat(a).isEqualTo(80d);
                return 90;
        };

        LConsumer<Integer> thenFunction = p -> {
                thenFunctionCalled.set(true);
                // Integer
                assertThat(p).isEqualTo(90);
        };

        //when
        LDoubleConsumer function = sutO.then(thenFunction);
        function.doAccept(80d);

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    @Test
    public void testThenToByte2() throws X  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);

        //given (+ some assertions)
        LDoubleFunction<Integer> sutO = a -> {
                mainFunctionCalled.set(true);
                assertThat(a).isEqualTo(80d);
                return 90;
        };

        LToByteFunction<Integer> thenFunction = p -> {
                thenFunctionCalled.set(true);
                // Integer
                assertThat(p).isEqualTo(90);
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
    public void testThenToShort3() throws X  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);

        //given (+ some assertions)
        LDoubleFunction<Integer> sutO = a -> {
                mainFunctionCalled.set(true);
                assertThat(a).isEqualTo(80d);
                return 90;
        };

        LToShortFunction<Integer> thenFunction = p -> {
                thenFunctionCalled.set(true);
                // Integer
                assertThat(p).isEqualTo(90);
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
    public void testThenToInt4() throws X  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);

        //given (+ some assertions)
        LDoubleFunction<Integer> sutO = a -> {
                mainFunctionCalled.set(true);
                assertThat(a).isEqualTo(80d);
                return 90;
        };

        LToIntFunction<Integer> thenFunction = p -> {
                thenFunctionCalled.set(true);
                // Integer
                assertThat(p).isEqualTo(90);
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
    public void testThenToLong5() throws X  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);

        //given (+ some assertions)
        LDoubleFunction<Integer> sutO = a -> {
                mainFunctionCalled.set(true);
                assertThat(a).isEqualTo(80d);
                return 90;
        };

        LToLongFunction<Integer> thenFunction = p -> {
                thenFunctionCalled.set(true);
                // Integer
                assertThat(p).isEqualTo(90);
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
    public void testThenToFloat6() throws X  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);

        //given (+ some assertions)
        LDoubleFunction<Integer> sutO = a -> {
                mainFunctionCalled.set(true);
                assertThat(a).isEqualTo(80d);
                return 90;
        };

        LToFloatFunction<Integer> thenFunction = p -> {
                thenFunctionCalled.set(true);
                // Integer
                assertThat(p).isEqualTo(90);
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
    public void testThenToDouble7() throws X  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);

        //given (+ some assertions)
        LDoubleFunction<Integer> sutO = a -> {
                mainFunctionCalled.set(true);
                assertThat(a).isEqualTo(80d);
                return 90;
        };

        LToDoubleFunction<Integer> thenFunction = p -> {
                thenFunctionCalled.set(true);
                // Integer
                assertThat(p).isEqualTo(90);
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
    public void testThenToChar8() throws X  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);

        //given (+ some assertions)
        LDoubleFunction<Integer> sutO = a -> {
                mainFunctionCalled.set(true);
                assertThat(a).isEqualTo(80d);
                return 90;
        };

        LToCharFunction<Integer> thenFunction = p -> {
                thenFunctionCalled.set(true);
                // Integer
                assertThat(p).isEqualTo(90);
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
    public void testThenToBool9() throws X  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);

        //given (+ some assertions)
        LDoubleFunction<Integer> sutO = a -> {
                mainFunctionCalled.set(true);
                assertThat(a).isEqualTo(80d);
                return 90;
        };

        LPredicate<Integer> thenFunction = p -> {
                thenFunctionCalled.set(true);
                // Integer
                assertThat(p).isEqualTo(90);
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
        assertThat(sut.nestingDoubleFunc())
            .isSameAs(sut)
            .isInstanceOf(LDoubleFunction.class);
    }

    @Test
    public void testShoving() {
        assertThat(sut.shovingDoubleFunc())
            .isSameAs(sut)
            .isInstanceOf(LDoubleFunction.class);
    }

    @Test
    public void testNestingX() {
        assertThat(sut.nestingDoubleFuncX())
            .isSameAs(sut)
            .isInstanceOf(LDoubleFunctionX.class);
    }

    @Test
    public void testShovingX() {
        assertThat(sut.shovingDoubleFuncX())
            .isSameAs(sut)
            .isInstanceOf(LDoubleFunctionX.class);
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void testShove() {

        // given
        LDoubleFunction<Integer> sutThrowing = LDoubleFunction.l(a -> {
            throw new UnsupportedOperationException();
        });

        // when
        sutThrowing.shovingDoubleFunc().doApply(100d);
    }

    @Test
    public void testHandleDoubleFunc() throws X {

        // given
        LDoubleFunction<Integer> sutThrowing = LDoubleFunction.l(a -> {
            throw new UnsupportedOperationException();
        });

        // when
        LDoubleFunction<Integer> wrapped = sutThrowing.handleDoubleFunc(h -> {
            h.wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED);
        });

        // then
        try {
            wrapped.doApply(100d);
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
                .contains("LDoubleFunction: R doApply(double a)");
    }


    @Test
    public void isThrowing() {
        assertThat(sut.isThrowing())
            .isFalse();
    }

    @Test void safeCompiles() {
        LDoubleFunction r1 = LDoubleFunction.safe(sut); //NOSONAR
        LDoubleFunctionX r2 = LDoubleFunction.safe(sut); //NOSONAR
        DoubleFunction r3 = LDoubleFunction.safe(sut); //NOSONAR
    }

    @Test void safePropagates() {
        Object result = LDoubleFunction.safe(sut);
        assertThat(result).isSameAs(sut);
    }

    @Test void safeProtectsAgainstNpe() {
        Object result = LDoubleFunction.safe(null);
        assertThat(result).isSameAs(LDoubleFunction.l(LDoubleFunction.safe()));
    }

    @Test  void safeSupplierPropagates() {
        LSupplier<LDoubleFunction<Integer>> supplier = ()->sut;
        Object result = LDoubleFunction.safeSupplier(supplier);
        assertThat(result).isSameAs(supplier);
    }

    @Test  void safeSupplierProtectsAgainstNpe() {
        Object result = LDoubleFunction.safeSupplier(null);
        assertThat(result).isSameAs(LDoubleFunction.safeSupplier());
    }

    @Test  void safeSupplierCompiles() {
        LSupplier<LDoubleFunction<Integer>> r1 = LDoubleFunction.safeSupplier(()->sut);  //NOSONAR
        Supplier<LDoubleFunction<Integer>> r2 = LDoubleFunction.safeSupplier(()->sut); //NOSONAR
    }

}
