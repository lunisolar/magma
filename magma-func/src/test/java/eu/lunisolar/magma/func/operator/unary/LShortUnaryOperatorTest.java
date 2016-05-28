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
public class LShortUnaryOperatorTest<X extends ParseException> {
    private static final String ORIGINAL_MESSAGE = "Original message";
    private static final String EXCEPTION_WAS_WRAPPED = "Exception was wrapped.";
    private static final String NO_EXCEPTION_WERE_THROWN = "No exception were thrown.";

    private short testValue = (short)100;



    private LShortUnaryOperator sut = new LShortUnaryOperator(){
        public  short doApplyAsShort(short a)  {
            return testValue;
        }
    };

    private LShortUnaryOperatorX<X> opposite = new LShortUnaryOperatorX<X>(){
        public  short doApplyAsShort(short a)  throws X {
            return testValue;
        }
    };




    private LShortUnaryOperatorX<RuntimeException> sutAlwaysThrowingUnchecked = LShortUnaryOperator.l(a -> {
            throw new IndexOutOfBoundsException(ORIGINAL_MESSAGE);
    });


    @Test
    public void testTheResult() throws X {
        assertThat(sut.doApplyAsShort((short)100))
            .isEqualTo(testValue);
    }

    @Test
    public void testTupleCall() throws X {

        LShortSingle domainObject = Tuple4U.shortSingle((short)100);

        Object result = sut.tupleApplyAsShort(domainObject);

        assertThat(result)
            .isEqualTo(testValue);
    }

    @Test
    public void testNonNullDoApplyAsShort() throws X {
        assertThat(sut.nonNullDoApplyAsShort((short)100))
            .isEqualTo(testValue);
    }

    @Test
    public void testNestingDoApplyAsShortUnchecked() throws X {

        // then
        try {
            sutAlwaysThrowingUnchecked.nestingDoApplyAsShort((short)100);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                    .hasNoCause()
                    .hasMessage(ORIGINAL_MESSAGE);
        }
    }

    @Test
    public void testShovingDoApplyAsShortUnchecked() throws X {

        // then
        try {
            sutAlwaysThrowingUnchecked.shovingDoApplyAsShort((short)100);
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
            .isEqualTo("LShortUnaryOperator: short doApplyAsShort(short a)");
    }

    @Test
    public void testLMethod() throws X {
        assertThat(LShortUnaryOperator.l(a -> testValue ))
            .isInstanceOf(LShortUnaryOperator.class);
    }

    @Test
    public void testWrapMethod() throws X {
        assertThat(LShortUnaryOperator.wrap(opposite))
            .isInstanceOf(LShortUnaryOperator.class);
    }

    @Test
    public void testWrapMethodDoNotWrapsRuntimeException() throws X {
        // given
        LShortUnaryOperatorX<X> sutThrowing = LShortUnaryOperatorX.lX(a -> {
            throw new UnsupportedOperationException(ORIGINAL_MESSAGE);
        });

        // when
        LShortUnaryOperator wrapped = LShortUnaryOperator.wrap(sutThrowing);

        // then
        try {
            wrapped.doApplyAsShort((short)100);
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
        LShortUnaryOperatorX<ParseException> sutThrowing = LShortUnaryOperatorX.lX(a -> {
            throw new ParseException(ORIGINAL_MESSAGE, 0);
        });

        // when
        LShortUnaryOperator wrapped = LShortUnaryOperator.wrap(sutThrowing);

        // then
        try {
            wrapped.doApplyAsShort((short)100);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(NestedException.class)
                    .hasCauseExactlyInstanceOf(ParseException.class)
                    .hasMessage(ORIGINAL_MESSAGE);
        }
    }


    @Test
    public void testHandlingDoApplyAsShortMethodWrapsTheException() throws X {

        // given
        LShortUnaryOperator sutThrowing = LShortUnaryOperator.l(a -> {
            throw new UnsupportedOperationException();
        });

        // when
        LShortUnaryOperator wrapped = sutThrowing.handleShortUnaryOp(handler -> handler
            .wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED));

        // then
        try {
            wrapped.doApplyAsShort((short)100);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IllegalArgumentException.class)
                    .hasCauseExactlyInstanceOf(UnsupportedOperationException.class)
                    .hasMessage(EXCEPTION_WAS_WRAPPED);
        }
    }

    @Test
    public void testHandleShortUnaryOpMethodDoNotWrapsOtherExceptionIf() throws X {

        // given
        LShortUnaryOperator sutThrowing = LShortUnaryOperator.l(a -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LShortUnaryOperator wrapped = sutThrowing.handleShortUnaryOp(handler -> handler
                .wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED)
                .throwIf(IndexOutOfBoundsException.class));

        // then
        try {
            wrapped.doApplyAsShort((short)100);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                    .hasNoCause();
        }
    }

@Test
    public void testHandleShortUnaryOpMethodDoNotWrapsOtherExceptionWhen() throws X {

        // given
        LShortUnaryOperator sutThrowing = LShortUnaryOperator.l(a -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LShortUnaryOperator wrapped = sutThrowing.handleShortUnaryOp(handler -> handler
                .wrapWhen(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED)
                .throwIf(IndexOutOfBoundsException.class));

        // then
        try {
            wrapped.doApplyAsShort((short)100);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                    .hasNoCause();
        }
    }


    @Test
    public void testHandleShortUnaryOpMishandlingExceptionIsAllowed() throws X {

        // given
        LShortUnaryOperator sutThrowing = LShortUnaryOperator.l(a -> {
            throw new UnsupportedOperationException(ORIGINAL_MESSAGE);
        });

        // when
        LShortUnaryOperator wrapped = sutThrowing.handleShortUnaryOp(h -> Function4U.doNothing());

        // then
        try {
            wrapped.doApplyAsShort((short)100);
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
    public void testShortUnaryOpComposeShort() throws X {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LShortUnaryOperator sutO = a -> {
                mainFunctionCalled.set(true);
                assertThat(a).isEqualTo((short)90);
                return (short)100;
        };

        LShortUnaryOperator before = p0 -> {
            assertThat(p0).isEqualTo((short)80);
            beforeCalls.incrementAndGet();
            return (short)90;
        };

        //when
        LShortUnaryOperator function = sutO.shortUnaryOpComposeShort(before);
        function.doApplyAsShort((short)80);

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(beforeCalls.get()).isEqualTo(1);
    }


    @Test
    public void testShortUnaryOpCompose() throws X {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LShortUnaryOperator sutO = a -> {
                mainFunctionCalled.set(true);
                assertThat(a).isEqualTo((short)90);
                return (short)100;
        };

        LToShortFunction<Integer> before = p0 -> {
            assertThat(p0).isEqualTo(80);
            beforeCalls.incrementAndGet();
            return (short)90;
        };

        //when
        LToShortFunction<Integer> function = sutO.shortUnaryOpCompose(before);
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
        LShortUnaryOperator sutO = a -> {
                mainFunctionCalled.set(true);
                assertThat(a).isEqualTo((short)80);
                return (short)90;
        };

        LShortFunction<Integer> thenFunction = p -> {
                thenFunctionCalled.set(true);
                // short
                assertThat(p).isEqualTo((short)90);
                // Integer
                return 100;
        };

        //when
        LShortFunction<Integer> function = sutO.then(thenFunction);
        Integer finalValue = function.doApply((short)80);

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
        LShortUnaryOperator sutO = a -> {
                mainFunctionCalled.set(true);
                assertThat(a).isEqualTo((short)80);
                return (short)90;
        };

        LShortToByteFunction thenFunction = p -> {
                thenFunctionCalled.set(true);
                // short
                assertThat(p).isEqualTo((short)90);
                // byte
                return (byte)100;
        };

        //when
        LShortToByteFunction function = sutO.thenToByte(thenFunction);
        byte finalValue = function.doApplyAsByte((short)80);

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
        LShortUnaryOperator sutO = a -> {
                mainFunctionCalled.set(true);
                assertThat(a).isEqualTo((short)80);
                return (short)90;
        };

        LShortUnaryOperator thenFunction = p -> {
                thenFunctionCalled.set(true);
                // short
                assertThat(p).isEqualTo((short)90);
                // short
                return (short)100;
        };

        //when
        LShortUnaryOperator function = sutO.thenToShort(thenFunction);
        short finalValue = function.doApplyAsShort((short)80);

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
        LShortUnaryOperator sutO = a -> {
                mainFunctionCalled.set(true);
                assertThat(a).isEqualTo((short)80);
                return (short)90;
        };

        LShortToIntFunction thenFunction = p -> {
                thenFunctionCalled.set(true);
                // short
                assertThat(p).isEqualTo((short)90);
                // int
                return 100;
        };

        //when
        LShortToIntFunction function = sutO.thenToInt(thenFunction);
        int finalValue = function.doApplyAsInt((short)80);

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
        LShortUnaryOperator sutO = a -> {
                mainFunctionCalled.set(true);
                assertThat(a).isEqualTo((short)80);
                return (short)90;
        };

        LShortToLongFunction thenFunction = p -> {
                thenFunctionCalled.set(true);
                // short
                assertThat(p).isEqualTo((short)90);
                // long
                return 100L;
        };

        //when
        LShortToLongFunction function = sutO.thenToLong(thenFunction);
        long finalValue = function.doApplyAsLong((short)80);

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
        LShortUnaryOperator sutO = a -> {
                mainFunctionCalled.set(true);
                assertThat(a).isEqualTo((short)80);
                return (short)90;
        };

        LShortToFloatFunction thenFunction = p -> {
                thenFunctionCalled.set(true);
                // short
                assertThat(p).isEqualTo((short)90);
                // float
                return 100f;
        };

        //when
        LShortToFloatFunction function = sutO.thenToFloat(thenFunction);
        float finalValue = function.doApplyAsFloat((short)80);

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
        LShortUnaryOperator sutO = a -> {
                mainFunctionCalled.set(true);
                assertThat(a).isEqualTo((short)80);
                return (short)90;
        };

        LShortToDoubleFunction thenFunction = p -> {
                thenFunctionCalled.set(true);
                // short
                assertThat(p).isEqualTo((short)90);
                // double
                return 100d;
        };

        //when
        LShortToDoubleFunction function = sutO.thenToDouble(thenFunction);
        double finalValue = function.doApplyAsDouble((short)80);

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
        LShortUnaryOperator sutO = a -> {
                mainFunctionCalled.set(true);
                assertThat(a).isEqualTo((short)80);
                return (short)90;
        };

        LShortToCharFunction thenFunction = p -> {
                thenFunctionCalled.set(true);
                // short
                assertThat(p).isEqualTo((short)90);
                // char
                return '\u0100';
        };

        //when
        LShortToCharFunction function = sutO.thenToChar(thenFunction);
        char finalValue = function.doApplyAsChar((short)80);

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
        LShortUnaryOperator sutO = a -> {
                mainFunctionCalled.set(true);
                assertThat(a).isEqualTo((short)80);
                return (short)90;
        };

        LShortPredicate thenFunction = p -> {
                thenFunctionCalled.set(true);
                // short
                assertThat(p).isEqualTo((short)90);
                // boolean
                return true;
        };

        //when
        LShortPredicate function = sutO.thenToBool(thenFunction);
        boolean finalValue = function.doTest((short)80);

        //then - finals
        assertThat(finalValue).isEqualTo(true);
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    // </editor-fold>
    @Test
    public void identity() throws X {
        LShortUnaryOperator identityFunction = LShortUnaryOperator.identity();

        assertThat(identityFunction.doApplyAsShort((short)8)).isEqualTo((short)8);
    }


    @Test
    public void testNesting() {
        assertThat(sut.nestingShortUnaryOp())
            .isSameAs(sut)
            .isInstanceOf(LShortUnaryOperator.class);
    }

    @Test
    public void testShoving() {
        assertThat(sut.shovingShortUnaryOp())
            .isSameAs(sut)
            .isInstanceOf(LShortUnaryOperator.class);
    }

    @Test
    public void testNestingX() {
        assertThat(sut.nestingShortUnaryOpX())
            .isSameAs(sut)
            .isInstanceOf(LShortUnaryOperatorX.class);
    }

    @Test
    public void testShovingX() {
        assertThat(sut.shovingShortUnaryOpX())
            .isSameAs(sut)
            .isInstanceOf(LShortUnaryOperatorX.class);
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void testShove() {

        // given
        LShortUnaryOperator sutThrowing = LShortUnaryOperator.l(a -> {
            throw new UnsupportedOperationException();
        });

        // when
        sutThrowing.shovingShortUnaryOp().doApplyAsShort((short)100);
    }

    @Test
    public void testHandleShortUnaryOp() throws X {

        // given
        LShortUnaryOperator sutThrowing = LShortUnaryOperator.l(a -> {
            throw new UnsupportedOperationException();
        });

        // when
        LShortUnaryOperator wrapped = sutThrowing.handleShortUnaryOp(h -> {
            h.wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED);
        });

        // then
        try {
            wrapped.doApplyAsShort((short)100);
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
                .contains("LShortUnaryOperator: short doApplyAsShort(short a)");
    }


    @Test
    public void isThrowing() {
        assertThat(sut.isThrowing())
            .isFalse();
    }

    @Test void safeCompiles() {
        LShortUnaryOperator r1 = LShortUnaryOperator.safe(sut); //NOSONAR
        LShortUnaryOperatorX r2 = LShortUnaryOperator.safe(sut); //NOSONAR
    }

    @Test void safePropagates() {
        Object result = LShortUnaryOperator.safe(sut);
        assertThat(result).isSameAs(sut);
    }

    @Test void safeProtectsAgainstNpe() {
        Object result = LShortUnaryOperator.safe(null);
        assertThat(result).isSameAs(LShortUnaryOperator.l(LShortUnaryOperator.safe()));
    }

    @Test  void safeSupplierPropagates() {
        LSupplier<LShortUnaryOperator> supplier = ()->sut;
        Object result = LShortUnaryOperator.safeSupplier(supplier);
        assertThat(result).isSameAs(supplier);
    }

    @Test  void safeSupplierProtectsAgainstNpe() {
        Object result = LShortUnaryOperator.safeSupplier(null);
        assertThat(result).isSameAs(LShortUnaryOperator.safeSupplier());
    }

    @Test  void safeSupplierCompiles() {
        LSupplier<LShortUnaryOperator> r1 = LShortUnaryOperator.safeSupplier(()->sut);  //NOSONAR
    }

}
