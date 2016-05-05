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

package eu.lunisolar.magma.func.function.to;

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
public class LToLongFunctionXTest<T,X extends ParseException> {
    private static final String ORIGINAL_MESSAGE = "Original message";
    private static final String EXCEPTION_WAS_WRAPPED = "Exception was wrapped.";
    private static final String NO_EXCEPTION_WERE_THROWN = "No exception were thrown.";

    private long testValue = 100L;



    private LToLongFunctionX<Integer,X> sut = new LToLongFunctionX<Integer,X>(){
        public  long doApplyAsLong(Integer a1)  throws X {
            return testValue;
        }
    };

    private LToLongFunction<Integer> opposite = new LToLongFunction<Integer>(){
        public  long doApplyAsLong(Integer a1)  {
            return testValue;
        }
    };


    private ToLongFunction<Integer> jre = a1 -> testValue;


    private LToLongFunctionX<Integer,ParseException> sutAlwaysThrowing = LToLongFunctionX.lX(a1 -> {
            throw new ParseException(ORIGINAL_MESSAGE, 0);
    });

    private LToLongFunctionX<Integer,RuntimeException> sutAlwaysThrowingUnchecked = LToLongFunctionX.lX(a1 -> {
            throw new IndexOutOfBoundsException(ORIGINAL_MESSAGE);
    });


    @Test
    public void testTheResult() throws X {
        assertThat(sut.doApplyAsLong(100))
            .isEqualTo(testValue);
    }

    @Test
    public void testTupleCall() throws X {

        LSingle<Integer> domainObject = Tuple4U.single(100);

        Object result = sut.tupleApplyAsLong(domainObject);

        assertThat(result)
            .isEqualTo(testValue);
    }

    @Test
    public void testNonNullDoApplyAsLong() throws X {
        assertThat(sut.nonNullDoApplyAsLong(100))
            .isEqualTo(testValue);
    }

    @Test
    public void testNestingDoApplyAsLongChecked() throws X {

        // then
        try {
            sutAlwaysThrowing.nestingDoApplyAsLong(100);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(NestedException.class)
                    .hasCauseExactlyInstanceOf(ParseException.class)
                    .hasMessage(ORIGINAL_MESSAGE);
        }
    }

    @Test
    public void testNestingDoApplyAsLongUnchecked() throws X {

        // then
        try {
            sutAlwaysThrowingUnchecked.nestingDoApplyAsLong(100);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                    .hasNoCause()
                    .hasMessage(ORIGINAL_MESSAGE);
        }
    }

    @Test
    public void testShovingDoApplyAsLongChecked() throws X {

        // then
        try {
            sutAlwaysThrowing.shovingDoApplyAsLong(100);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(ParseException.class)
                    .hasNoCause()
                    .hasMessage(ORIGINAL_MESSAGE);
        }
    }

    @Test
    public void testShovingDoApplyAsLongUnchecked() throws X {

        // then
        try {
            sutAlwaysThrowingUnchecked.shovingDoApplyAsLong(100);
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
            .isEqualTo("LToLongFunctionX: long doApplyAsLong(T a1) throws X");
    }

    @Test
    public void testLXMethod() throws X {
        assertThat(LToLongFunctionX.lX(a1 -> testValue ))
            .isInstanceOf(LToLongFunctionX.class);
    }

    @Test
    public void testWrapXMethod() throws X {
        assertThat(LToLongFunctionX.wrapX(opposite))
            .isInstanceOf(LToLongFunctionX.class);
    }

    @Test
    public void testWrapStdMethod() throws X {
        assertThat(LToLongFunctionX.wrap(jre))
            .isInstanceOf(LToLongFunctionX.class);
    }


    @Test
    public void testHandlingDoApplyAsLongMethodWrapsTheException() throws X {

        // given
        LToLongFunctionX<Integer,X> sutThrowing = LToLongFunctionX.lX(a1 -> {
            throw new UnsupportedOperationException();
        });

        // when
        LToLongFunctionX<Integer,RuntimeException> wrapped = sutThrowing.handleToLongFuncX(handler -> handler
            .wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED));

        // then
        try {
            wrapped.doApplyAsLong(100);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IllegalArgumentException.class)
                    .hasCauseExactlyInstanceOf(UnsupportedOperationException.class)
                    .hasMessage(EXCEPTION_WAS_WRAPPED);
        }
    }

    @Test
    public void testHandleToLongFuncXMethodDoNotWrapsOtherExceptionIf() throws X {

        // given
        LToLongFunctionX<Integer,X> sutThrowing = LToLongFunctionX.lX(a1 -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LToLongFunctionX<Integer,X> wrapped = sutThrowing.handleToLongFuncX(handler -> handler
                .wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED)
                .throwIf(IndexOutOfBoundsException.class));

        // then
        try {
            wrapped.doApplyAsLong(100);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                    .hasNoCause();
        }
    }

@Test
    public void testHandleToLongFuncXMethodDoNotWrapsOtherExceptionWhen() throws X {

        // given
        LToLongFunctionX<Integer,X> sutThrowing = LToLongFunctionX.lX(a1 -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LToLongFunctionX<Integer,X> wrapped = sutThrowing.handleToLongFuncX(handler -> handler
                .wrapWhen(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED)
                .throwIf(IndexOutOfBoundsException.class));

        // then
        try {
            wrapped.doApplyAsLong(100);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                    .hasNoCause();
        }
    }


    @Test
    public void testHandleToLongFuncXMishandlingExceptionIsAllowed() throws X {

        // given
        LToLongFunctionX<Integer,X> sutThrowing = LToLongFunctionX.lX(a1 -> {
            throw (X) new ParseException(ORIGINAL_MESSAGE, 0);
        });

        // when
        LToLongFunctionX<Integer,X> wrapped = sutThrowing.handleToLongFuncX(h -> Function4U.doNothing());

        // then
        try {
            wrapped.doApplyAsLong(100);
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
    public void testToLongFuncCompose() throws X {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LToLongFunctionX<Integer,X> sutO = a1 -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(90);
                return 100L;
        };

        LFunctionX<Integer,Integer,X> before1 = p0 -> {
            assertThat(p0).isEqualTo(80);
            beforeCalls.incrementAndGet();
            return 90;
        };

        //when
        LToLongFunctionX<Integer,X> function = sutO.toLongFuncCompose(before1);
        function.doApplyAsLong(80);

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
        LToLongFunctionX<Integer,X> sutO = a1 -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(80);
                return 90L;
        };

        LLongFunctionX<Integer,X> thenFunction = p -> {
                thenFunctionCalled.set(true);
                // long
                assertThat(p).isEqualTo(90L);
                // Integer
                return 100;
        };

        //when
        LFunctionX<Integer,Integer,X> function = sutO.then(thenFunction);
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
        LToLongFunctionX<Integer,X> sutO = a1 -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(80);
                return 90L;
        };

        LLongToByteFunctionX<X> thenFunction = p -> {
                thenFunctionCalled.set(true);
                // long
                assertThat(p).isEqualTo(90L);
                // byte
                return (byte)100;
        };

        //when
        LToByteFunctionX<Integer,X> function = sutO.thenToByte(thenFunction);
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
        LToLongFunctionX<Integer,X> sutO = a1 -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(80);
                return 90L;
        };

        LLongToShortFunctionX<X> thenFunction = p -> {
                thenFunctionCalled.set(true);
                // long
                assertThat(p).isEqualTo(90L);
                // short
                return (short)100;
        };

        //when
        LToShortFunctionX<Integer,X> function = sutO.thenToShort(thenFunction);
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
        LToLongFunctionX<Integer,X> sutO = a1 -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(80);
                return 90L;
        };

        LLongToIntFunctionX<X> thenFunction = p -> {
                thenFunctionCalled.set(true);
                // long
                assertThat(p).isEqualTo(90L);
                // int
                return 100;
        };

        //when
        LToIntFunctionX<Integer,X> function = sutO.thenToInt(thenFunction);
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
        LToLongFunctionX<Integer,X> sutO = a1 -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(80);
                return 90L;
        };

        LLongUnaryOperatorX<X> thenFunction = p -> {
                thenFunctionCalled.set(true);
                // long
                assertThat(p).isEqualTo(90L);
                // long
                return 100L;
        };

        //when
        LToLongFunctionX<Integer,X> function = sutO.thenToLong(thenFunction);
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
        LToLongFunctionX<Integer,X> sutO = a1 -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(80);
                return 90L;
        };

        LLongToFloatFunctionX<X> thenFunction = p -> {
                thenFunctionCalled.set(true);
                // long
                assertThat(p).isEqualTo(90L);
                // float
                return 100f;
        };

        //when
        LToFloatFunctionX<Integer,X> function = sutO.thenToFloat(thenFunction);
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
        LToLongFunctionX<Integer,X> sutO = a1 -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(80);
                return 90L;
        };

        LLongToDoubleFunctionX<X> thenFunction = p -> {
                thenFunctionCalled.set(true);
                // long
                assertThat(p).isEqualTo(90L);
                // double
                return 100d;
        };

        //when
        LToDoubleFunctionX<Integer,X> function = sutO.thenToDouble(thenFunction);
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
        LToLongFunctionX<Integer,X> sutO = a1 -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(80);
                return 90L;
        };

        LLongToCharFunctionX<X> thenFunction = p -> {
                thenFunctionCalled.set(true);
                // long
                assertThat(p).isEqualTo(90L);
                // char
                return '\u0100';
        };

        //when
        LToCharFunctionX<Integer,X> function = sutO.thenToChar(thenFunction);
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
        LToLongFunctionX<Integer,X> sutO = a1 -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(80);
                return 90L;
        };

        LLongPredicateX<X> thenFunction = p -> {
                thenFunctionCalled.set(true);
                // long
                assertThat(p).isEqualTo(90L);
                // boolean
                return true;
        };

        //when
        LPredicateX<Integer,X> function = sutO.thenToBool(thenFunction);
        boolean finalValue = function.doTest(80);

        //then - finals
        assertThat(finalValue).isEqualTo(true);
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    // </editor-fold>

    @Test
    public void testNesting() {
        assertThat(sut.nestingToLongFunc())
            .isInstanceOf(LToLongFunction.class);
    }

    @Test
    public void testShoving() {
        assertThat(sut.shovingToLongFunc())
            .isInstanceOf(LToLongFunction.class);
    }

    @Test
    public void testNestingX() {
        assertThat(sut.nestingToLongFuncX())
            .isInstanceOf(LToLongFunctionX.class);
    }

    @Test
    public void testShovingX() {
        assertThat(sut.shovingToLongFuncX())
            .isInstanceOf(LToLongFunctionX.class);
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void testShove() {

        // given
        LToLongFunctionX<Integer,X> sutThrowing = LToLongFunctionX.lX(a1 -> {
            throw new UnsupportedOperationException();
        });

        // when
        sutThrowing.shovingToLongFunc().doApplyAsLong(100);
    }

    @Test
    public void testHandleToLongFunc() throws X {

        // given
        LToLongFunctionX<Integer,X> sutThrowing = LToLongFunctionX.lX(a1 -> {
            throw new UnsupportedOperationException();
        });

        // when
        LToLongFunctionX<Integer,X> wrapped = sutThrowing.handleToLongFuncX(h -> {
            h.wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED);
        });

        // then
        try {
            wrapped.doApplyAsLong(100);
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
                .contains("LToLongFunctionX: long doApplyAsLong(T a1) throws X");
    }


    @Test
    public void isThrowing() {
        assertThat(sut.isThrowing())
            .isTrue();
    }

    @Test void safeCompiles() {
        LToLongFunctionX r1 = LToLongFunctionX.safe(sut); //NOSONAR
        ToLongFunction r3 = LToLongFunctionX.safe(sut); //NOSONAR
    }

    @Test void safePropagates() {
        Object result = LToLongFunctionX.safe(sut);
        assertThat(result).isSameAs(sut);
    }

    @Test void safeProtectsAgainstNpe() {
        Object result = LToLongFunctionX.safe(null);
        assertThat(result).isSameAs(LToLongFunctionX.lX(LToLongFunctionX.safe()));
    }

    @Test <Y extends Throwable> void safeSupplierPropagates() {
        LSupplierX<LToLongFunctionX<Integer,X>,Y> supplier = ()->sut;
        Object result = LToLongFunctionX.safeSupplier(supplier);
        assertThat(result).isSameAs(supplier);
    }

    @Test <Y extends Throwable> void safeSupplierProtectsAgainstNpe() {
        Object result = LToLongFunctionX.safeSupplier(null);
        assertThat(result).isSameAs(LToLongFunctionX.safeSupplier());
    }

    @Test <Y extends Throwable> void safeSupplierCompiles() {
        LSupplierX<LToLongFunctionX<Integer,X>,Y> r1 = LToLongFunctionX.safeSupplier(()->sut);  //NOSONAR
        Supplier<LToLongFunctionX<Integer,X>> r2 = LToLongFunctionX.safeSupplier(()->sut); //NOSONAR
    }

}
