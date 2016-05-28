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
public class LCharToIntFunctionXTest<X extends ParseException> {
    private static final String ORIGINAL_MESSAGE = "Original message";
    private static final String EXCEPTION_WAS_WRAPPED = "Exception was wrapped.";
    private static final String NO_EXCEPTION_WERE_THROWN = "No exception were thrown.";

    private int testValue = 100;



    private LCharToIntFunctionX<X> sut = new LCharToIntFunctionX<X>(){
        public  int doApplyAsInt(char a)  throws X {
            return testValue;
        }
    };

    private LCharToIntFunction opposite = new LCharToIntFunction(){
        public  int doApplyAsInt(char a)  {
            return testValue;
        }
    };



    private LCharToIntFunctionX<ParseException> sutAlwaysThrowing = LCharToIntFunctionX.lX(a -> {
            throw new ParseException(ORIGINAL_MESSAGE, 0);
    });

    private LCharToIntFunctionX<RuntimeException> sutAlwaysThrowingUnchecked = LCharToIntFunctionX.lX(a -> {
            throw new IndexOutOfBoundsException(ORIGINAL_MESSAGE);
    });


    @Test
    public void testTheResult() throws X {
        assertThat(sut.doApplyAsInt('\u0100'))
            .isEqualTo(testValue);
    }

    @Test
    public void testTupleCall() throws X {

        LCharSingle domainObject = Tuple4U.charSingle('\u0100');

        Object result = sut.tupleApplyAsInt(domainObject);

        assertThat(result)
            .isEqualTo(testValue);
    }

    @Test
    public void testNonNullDoApplyAsInt() throws X {
        assertThat(sut.nonNullDoApplyAsInt('\u0100'))
            .isEqualTo(testValue);
    }

    @Test
    public void testNestingDoApplyAsIntChecked() throws X {

        // then
        try {
            sutAlwaysThrowing.nestingDoApplyAsInt('\u0100');
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(NestedException.class)
                    .hasCauseExactlyInstanceOf(ParseException.class)
                    .hasMessage(ORIGINAL_MESSAGE);
        }
    }

    @Test
    public void testNestingDoApplyAsIntUnchecked() throws X {

        // then
        try {
            sutAlwaysThrowingUnchecked.nestingDoApplyAsInt('\u0100');
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                    .hasNoCause()
                    .hasMessage(ORIGINAL_MESSAGE);
        }
    }

    @Test
    public void testShovingDoApplyAsIntChecked() throws X {

        // then
        try {
            sutAlwaysThrowing.shovingDoApplyAsInt('\u0100');
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(ParseException.class)
                    .hasNoCause()
                    .hasMessage(ORIGINAL_MESSAGE);
        }
    }

    @Test
    public void testShovingDoApplyAsIntUnchecked() throws X {

        // then
        try {
            sutAlwaysThrowingUnchecked.shovingDoApplyAsInt('\u0100');
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
            .isEqualTo("LCharToIntFunctionX: int doApplyAsInt(char a) throws X");
    }

    @Test
    public void testLXMethod() throws X {
        assertThat(LCharToIntFunctionX.lX(a -> testValue ))
            .isInstanceOf(LCharToIntFunctionX.class);
    }

    @Test
    public void testWrapXMethod() throws X {
        assertThat(LCharToIntFunctionX.wrapX(opposite))
            .isInstanceOf(LCharToIntFunctionX.class);
    }


    @Test
    public void testHandlingDoApplyAsIntMethodWrapsTheException() throws X {

        // given
        LCharToIntFunctionX<X> sutThrowing = LCharToIntFunctionX.lX(a -> {
            throw new UnsupportedOperationException();
        });

        // when
        LCharToIntFunctionX<RuntimeException> wrapped = sutThrowing.handleCharToIntFuncX(handler -> handler
            .wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED));

        // then
        try {
            wrapped.doApplyAsInt('\u0100');
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IllegalArgumentException.class)
                    .hasCauseExactlyInstanceOf(UnsupportedOperationException.class)
                    .hasMessage(EXCEPTION_WAS_WRAPPED);
        }
    }

    @Test
    public void testHandleCharToIntFuncXMethodDoNotWrapsOtherExceptionIf() throws X {

        // given
        LCharToIntFunctionX<X> sutThrowing = LCharToIntFunctionX.lX(a -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LCharToIntFunctionX<X> wrapped = sutThrowing.handleCharToIntFuncX(handler -> handler
                .wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED)
                .throwIf(IndexOutOfBoundsException.class));

        // then
        try {
            wrapped.doApplyAsInt('\u0100');
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                    .hasNoCause();
        }
    }

@Test
    public void testHandleCharToIntFuncXMethodDoNotWrapsOtherExceptionWhen() throws X {

        // given
        LCharToIntFunctionX<X> sutThrowing = LCharToIntFunctionX.lX(a -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LCharToIntFunctionX<X> wrapped = sutThrowing.handleCharToIntFuncX(handler -> handler
                .wrapWhen(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED)
                .throwIf(IndexOutOfBoundsException.class));

        // then
        try {
            wrapped.doApplyAsInt('\u0100');
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                    .hasNoCause();
        }
    }


    @Test
    public void testHandleCharToIntFuncXMishandlingExceptionIsAllowed() throws X {

        // given
        LCharToIntFunctionX<X> sutThrowing = LCharToIntFunctionX.lX(a -> {
            throw (X) new ParseException(ORIGINAL_MESSAGE, 0);
        });

        // when
        LCharToIntFunctionX<X> wrapped = sutThrowing.handleCharToIntFuncX(h -> Function4U.doNothing());

        // then
        try {
            wrapped.doApplyAsInt('\u0100');
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
    public void testCharToIntFuncComposeChar() throws X {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LCharToIntFunctionX<X> sutO = a -> {
                mainFunctionCalled.set(true);
                assertThat(a).isEqualTo('\u0090');
                return 100;
        };

        LCharUnaryOperatorX<X> before = p0 -> {
            assertThat(p0).isEqualTo('\u0080');
            beforeCalls.incrementAndGet();
            return '\u0090';
        };

        //when
        LCharToIntFunctionX<X> function = sutO.charToIntFuncComposeChar(before);
        function.doApplyAsInt('\u0080');

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(beforeCalls.get()).isEqualTo(1);
    }


    @Test
    public void testCharToIntFuncCompose() throws X {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LCharToIntFunctionX<X> sutO = a -> {
                mainFunctionCalled.set(true);
                assertThat(a).isEqualTo('\u0090');
                return 100;
        };

        LToCharFunctionX<Integer,X> before = p0 -> {
            assertThat(p0).isEqualTo(80);
            beforeCalls.incrementAndGet();
            return '\u0090';
        };

        //when
        LToIntFunctionX<Integer,X> function = sutO.charToIntFuncCompose(before);
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
        LCharToIntFunctionX<X> sutO = a -> {
                mainFunctionCalled.set(true);
                assertThat(a).isEqualTo('\u0080');
                return 90;
        };

        LIntFunctionX<Integer,X> thenFunction = p -> {
                thenFunctionCalled.set(true);
                // int
                assertThat(p).isEqualTo(90);
                // Integer
                return 100;
        };

        //when
        LCharFunctionX<Integer,X> function = sutO.then(thenFunction);
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
        LCharToIntFunctionX<X> sutO = a -> {
                mainFunctionCalled.set(true);
                assertThat(a).isEqualTo('\u0080');
                return 90;
        };

        LIntToByteFunctionX<X> thenFunction = p -> {
                thenFunctionCalled.set(true);
                // int
                assertThat(p).isEqualTo(90);
                // byte
                return (byte)100;
        };

        //when
        LCharToByteFunctionX<X> function = sutO.thenToByte(thenFunction);
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
        LCharToIntFunctionX<X> sutO = a -> {
                mainFunctionCalled.set(true);
                assertThat(a).isEqualTo('\u0080');
                return 90;
        };

        LIntToShortFunctionX<X> thenFunction = p -> {
                thenFunctionCalled.set(true);
                // int
                assertThat(p).isEqualTo(90);
                // short
                return (short)100;
        };

        //when
        LCharToShortFunctionX<X> function = sutO.thenToShort(thenFunction);
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
        LCharToIntFunctionX<X> sutO = a -> {
                mainFunctionCalled.set(true);
                assertThat(a).isEqualTo('\u0080');
                return 90;
        };

        LIntUnaryOperatorX<X> thenFunction = p -> {
                thenFunctionCalled.set(true);
                // int
                assertThat(p).isEqualTo(90);
                // int
                return 100;
        };

        //when
        LCharToIntFunctionX<X> function = sutO.thenToInt(thenFunction);
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
        LCharToIntFunctionX<X> sutO = a -> {
                mainFunctionCalled.set(true);
                assertThat(a).isEqualTo('\u0080');
                return 90;
        };

        LIntToLongFunctionX<X> thenFunction = p -> {
                thenFunctionCalled.set(true);
                // int
                assertThat(p).isEqualTo(90);
                // long
                return 100L;
        };

        //when
        LCharToLongFunctionX<X> function = sutO.thenToLong(thenFunction);
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
        LCharToIntFunctionX<X> sutO = a -> {
                mainFunctionCalled.set(true);
                assertThat(a).isEqualTo('\u0080');
                return 90;
        };

        LIntToFloatFunctionX<X> thenFunction = p -> {
                thenFunctionCalled.set(true);
                // int
                assertThat(p).isEqualTo(90);
                // float
                return 100f;
        };

        //when
        LCharToFloatFunctionX<X> function = sutO.thenToFloat(thenFunction);
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
        LCharToIntFunctionX<X> sutO = a -> {
                mainFunctionCalled.set(true);
                assertThat(a).isEqualTo('\u0080');
                return 90;
        };

        LIntToDoubleFunctionX<X> thenFunction = p -> {
                thenFunctionCalled.set(true);
                // int
                assertThat(p).isEqualTo(90);
                // double
                return 100d;
        };

        //when
        LCharToDoubleFunctionX<X> function = sutO.thenToDouble(thenFunction);
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
        LCharToIntFunctionX<X> sutO = a -> {
                mainFunctionCalled.set(true);
                assertThat(a).isEqualTo('\u0080');
                return 90;
        };

        LIntToCharFunctionX<X> thenFunction = p -> {
                thenFunctionCalled.set(true);
                // int
                assertThat(p).isEqualTo(90);
                // char
                return '\u0100';
        };

        //when
        LCharUnaryOperatorX<X> function = sutO.thenToChar(thenFunction);
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
        LCharToIntFunctionX<X> sutO = a -> {
                mainFunctionCalled.set(true);
                assertThat(a).isEqualTo('\u0080');
                return 90;
        };

        LIntPredicateX<X> thenFunction = p -> {
                thenFunctionCalled.set(true);
                // int
                assertThat(p).isEqualTo(90);
                // boolean
                return true;
        };

        //when
        LCharPredicateX<X> function = sutO.thenToBool(thenFunction);
        boolean finalValue = function.doTest('\u0080');

        //then - finals
        assertThat(finalValue).isEqualTo(true);
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    // </editor-fold>

    @Test
    public void testNesting() {
        assertThat(sut.nestingCharToIntFunc())
            .isInstanceOf(LCharToIntFunction.class);
    }

    @Test
    public void testShoving() {
        assertThat(sut.shovingCharToIntFunc())
            .isInstanceOf(LCharToIntFunction.class);
    }

    @Test
    public void testNestingX() {
        assertThat(sut.nestingCharToIntFuncX())
            .isInstanceOf(LCharToIntFunctionX.class);
    }

    @Test
    public void testShovingX() {
        assertThat(sut.shovingCharToIntFuncX())
            .isInstanceOf(LCharToIntFunctionX.class);
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void testShove() {

        // given
        LCharToIntFunctionX<X> sutThrowing = LCharToIntFunctionX.lX(a -> {
            throw new UnsupportedOperationException();
        });

        // when
        sutThrowing.shovingCharToIntFunc().doApplyAsInt('\u0100');
    }

    @Test
    public void testHandleCharToIntFunc() throws X {

        // given
        LCharToIntFunctionX<X> sutThrowing = LCharToIntFunctionX.lX(a -> {
            throw new UnsupportedOperationException();
        });

        // when
        LCharToIntFunctionX<X> wrapped = sutThrowing.handleCharToIntFuncX(h -> {
            h.wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED);
        });

        // then
        try {
            wrapped.doApplyAsInt('\u0100');
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
                .contains("LCharToIntFunctionX: int doApplyAsInt(char a) throws X");
    }


    @Test
    public void isThrowing() {
        assertThat(sut.isThrowing())
            .isTrue();
    }

    @Test void safeCompiles() {
        LCharToIntFunctionX r1 = LCharToIntFunctionX.safe(sut); //NOSONAR
    }

    @Test void safePropagates() {
        Object result = LCharToIntFunctionX.safe(sut);
        assertThat(result).isSameAs(sut);
    }

    @Test void safeProtectsAgainstNpe() {
        Object result = LCharToIntFunctionX.safe(null);
        assertThat(result).isSameAs(LCharToIntFunctionX.lX(LCharToIntFunctionX.safe()));
    }

    @Test <Y extends Throwable> void safeSupplierPropagates() {
        LSupplierX<LCharToIntFunctionX<X>,Y> supplier = ()->sut;
        Object result = LCharToIntFunctionX.safeSupplier(supplier);
        assertThat(result).isSameAs(supplier);
    }

    @Test <Y extends Throwable> void safeSupplierProtectsAgainstNpe() {
        Object result = LCharToIntFunctionX.safeSupplier(null);
        assertThat(result).isSameAs(LCharToIntFunctionX.safeSupplier());
    }

    @Test <Y extends Throwable> void safeSupplierCompiles() {
        LSupplierX<LCharToIntFunctionX<X>,Y> r1 = LCharToIntFunctionX.safeSupplier(()->sut);  //NOSONAR
    }

}
