/*
 * This file is part of "lunisolar-magma".
 *
 * (C) Copyright 2015 Lunisolar (http://lunisolar.eu/).
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
import eu.lunisolar.magma.func.operator.unary.*; // NOSONAR
import eu.lunisolar.magma.func.operator.binary.*; // NOSONAR
import eu.lunisolar.magma.func.operator.ternary.*; // NOSONAR
import eu.lunisolar.magma.func.function.*; // NOSONAR
import eu.lunisolar.magma.func.function.from.*; // NOSONAR
import eu.lunisolar.magma.func.function.to.*; // NOSONAR
import eu.lunisolar.magma.func.function.conversion.*; // NOSONAR
import eu.lunisolar.magma.func.predicate.*; // NOSONAR
import eu.lunisolar.magma.func.supplier.*; // NOSONAR
import eu.lunisolar.magma.func.consumer.primitives.obj.*; // NOSONAR
import eu.lunisolar.magma.func.consumer.primitives.bi.*; // NOSONAR
import eu.lunisolar.magma.func.consumer.primitives.tri.*; // NOSONAR
import eu.lunisolar.magma.func.consumer.primitives.*; // NOSONAR
import eu.lunisolar.magma.func.consumer.*; // NOSONAR
import eu.lunisolar.magma.func.action.*; // NOSONAR

import java.util.function.*; // NOSONAR
import org.assertj.core.api.Assertions;  //NOSONAR
import org.testng.annotations.*;      //NOSONAR
import java.util.regex.Pattern;          //NOSONAR
import java.text.ParseException;         //NOSONAR
import eu.lunisolar.magma.basics.*; //NOSONAR
import eu.lunisolar.magma.basics.exceptions.*; //NOSONAR
import java.util.concurrent.atomic.AtomicInteger; //NOSONAR
import eu.lunisolar.magma.struct.tuple.*; // NOSONAR
import static org.assertj.core.api.Assertions.*; //NOSONAR

/** The test obviously concentrate on the interface methods the function it self is very simple.  */
public class LIntToDoubleFunctionXTest<X extends ParseException> {
    private static final String ORIGINAL_MESSAGE = "Original message";
    private static final String EXCEPTION_WAS_WRAPPED = "Exception was wrapped.";
    private static final String NO_EXCEPTION_WERE_THROWN = "No exception were thrown.";

    private double testValue = (double)100;



    private LIntToDoubleFunctionX<X> sut = new LIntToDoubleFunctionX(){
        public  double doApplyAsDouble(int a1) throws ParseException {
            return testValue;
        }
    };

    private LIntToDoubleFunction opposite = new LIntToDoubleFunction(){
        public  double doApplyAsDouble(int a1)  {
            return testValue;
        }
    };


    private IntToDoubleFunction jre = a1 -> testValue;


    private LIntToDoubleFunctionX<ParseException> sutAlwaysThrowing = LIntToDoubleFunctionX.lX(a1 -> {
            throw new ParseException(ORIGINAL_MESSAGE, 0);
    });

    private LIntToDoubleFunctionX<RuntimeException> sutAlwaysThrowingUnckeck = LIntToDoubleFunctionX.lX(a1 -> {
            throw new IndexOutOfBoundsException(ORIGINAL_MESSAGE);
    });


    @Test
    public void testTheResult() throws X {
        assertThat(sut.doApplyAsDouble((int)100))
            .isEqualTo(testValue);
    }

    @Test
    public void testTupleCall() throws X {

        //FunctionalCall<LIntSingle,Double,X> theCall = sut;

        LIntSingle domainObject = Tuple4U.tuple((int)100);

        Object result = sut.tupleApplyAsDouble(domainObject);

        assertThat(result)
            .isEqualTo(testValue);
    }

    @Test
    public void testNonNullDoApplyAsDouble() throws X {
        assertThat(sut.nonNullDoApplyAsDouble((int)100))
            .isEqualTo(testValue);
    }

    @Test
    public void testNestingDoApplyAsDoubleChecked() throws X {

        // then
        try {
            sutAlwaysThrowing.nestingDoApplyAsDouble((int)100);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(NestedException.class)
                    .hasCauseExactlyInstanceOf(ParseException.class)
                    .hasMessage(ORIGINAL_MESSAGE);
        }
    }

    @Test
    public void testNestingDoApplyAsDoubleUnckeck() throws X {

        // then
        try {
            sutAlwaysThrowingUnckeck.nestingDoApplyAsDouble((int)100);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                    .hasNoCause()
                    .hasMessage(ORIGINAL_MESSAGE);
        }
    }

    @Test
    public void testShovingDoApplyAsDoubleChecked() throws X {

        // then
        try {
            sutAlwaysThrowing.shovingDoApplyAsDouble((int)100);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(ParseException.class)
                    .hasNoCause()
                    .hasMessage(ORIGINAL_MESSAGE);
        }
    }

    @Test
    public void testShovingDoApplyAsDoubleUnckeck() throws X {

        // then
        try {
            sutAlwaysThrowingUnckeck.shovingDoApplyAsDouble((int)100);
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
            .isEqualTo("LIntToDoubleFunctionX: double doApplyAsDouble(int a1) throws X");
    }

    @Test
    public void testLXMethod() throws X {
        assertThat(LIntToDoubleFunctionX.lX(a1 -> testValue ))
            .isInstanceOf(LIntToDoubleFunctionX.class);
    }

    @Test
    public void testWrapXMethod() throws X {
        assertThat(LIntToDoubleFunctionX.wrapX(opposite))
            .isInstanceOf(LIntToDoubleFunctionX.class);
    }

    @Test
    public void testWrapStdMethod() throws X {
        assertThat(LIntToDoubleFunctionX.wrap(jre))
            .isInstanceOf(LIntToDoubleFunctionX.class);
    }


    @Test
    public void testWrapExceptionMethodWrapsTheException() throws X {

        // given
        LIntToDoubleFunctionX<X> sutThrowing = LIntToDoubleFunctionX.lX(a1 -> {
            throw new UnsupportedOperationException();
        });

        // when
        LIntToDoubleFunctionX<X> wrapped = sutThrowing.handleIntToDoubleFuncX(handler -> handler
            .wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED));

        // then
        try {
            wrapped.doApplyAsDouble((int)100);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IllegalArgumentException.class)
                    .hasCauseExactlyInstanceOf(UnsupportedOperationException.class)
                    .hasMessage(EXCEPTION_WAS_WRAPPED);
        }
    }

    @Test
    public void testWrapExceptionMethodDoNotWrapsOtherExceptionIf() throws X {

        // given
        LIntToDoubleFunctionX<X> sutThrowing = LIntToDoubleFunctionX.lX(a1 -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LIntToDoubleFunctionX<X> wrapped = sutThrowing.handleIntToDoubleFuncX(handler -> handler
                .wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED)
                .throwIf(IndexOutOfBoundsException.class));

        // then
        try {
            wrapped.doApplyAsDouble((int)100);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                    .hasNoCause();
        }
    }

@Test
    public void testWrapExceptionMethodDoNotWrapsOtherExceptionWhen() throws X {

        // given
        LIntToDoubleFunctionX<X> sutThrowing = LIntToDoubleFunctionX.lX(a1 -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LIntToDoubleFunctionX<X> wrapped = sutThrowing.handleIntToDoubleFuncX(handler -> handler
                .wrapWhen(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED)
                .throwIf(IndexOutOfBoundsException.class));

        // then
        try {
            wrapped.doApplyAsDouble((int)100);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                    .hasNoCause();
        }
    }


    @Test
    public void testWrapExceptionMishandlingExceptionIsAllowed() throws X {

        // given
        LIntToDoubleFunctionX<X> sutThrowing = LIntToDoubleFunctionX.lX(a1 -> {
            throw (X) new ParseException(ORIGINAL_MESSAGE, 0);
        });

        // when
        LIntToDoubleFunctionX<X> wrapped = sutThrowing.handleIntToDoubleFuncX(h -> Function4U.doNothing());

        // then
        try {
            wrapped.doApplyAsDouble((int)100);
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
    public void testintToDoubleFuncComposeInt() throws X {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LIntToDoubleFunctionX<X> sutO = a1 -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo((int)90);
                return (double)100;
        };

        LIntUnaryOperatorX<X> before1 = p0 -> {
            assertThat(p0).isEqualTo((int)80);
            beforeCalls.incrementAndGet();
            return (int)90;
        };

        //when
        LIntToDoubleFunctionX<X> function = sutO.intToDoubleFuncComposeInt(before1);
        function.doApplyAsDouble((int)80);

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(beforeCalls.get()).isEqualTo(1);
    }


    @Test
    public void testintToDoubleFuncCompose() throws X {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LIntToDoubleFunctionX<X> sutO = a1 -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo((int)90);
                return (double)100;
        };

        LToIntFunctionX<Integer ,X> before1 = p0 -> {
            assertThat(p0).isEqualTo(Integer.valueOf(80));
            beforeCalls.incrementAndGet();
            return (int)90;
        };

        //when
        LToDoubleFunctionX<Integer ,X> function = sutO.intToDoubleFuncCompose(before1);
        function.doApplyAsDouble((Integer )Integer.valueOf(80));

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
        LIntToDoubleFunctionX<X> sutO = a1 -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo((int)80);
                return (double)90;
        };

        LDoubleFunctionX<Integer ,X> thenFunction = p -> {
                thenFunctionCalled.set(true);
                // 
                assertThat(p).isEqualTo((double)90);
                // V
                return Integer.valueOf(100);
        };

        //when
        LIntFunctionX<Integer ,X> function = sutO.then(thenFunction);
        Integer  finalValue = function.doApply((int)80);

        //then - finals
        assertThat(finalValue).isEqualTo(Integer.valueOf(100));
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    @Test
    public void testThen1ToByte() throws X  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);


        //given (+ some assertions)
        LIntToDoubleFunctionX<X> sutO = a1 -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo((int)80);
                return (double)90;
        };

        LDoubleToByteFunctionX<X> thenFunction = p -> {
                thenFunctionCalled.set(true);
                // 
                assertThat(p).isEqualTo((double)90);
                // byte
                return (byte)100;
        };

        //when
        LIntToByteFunctionX<X> function = sutO.thenToByte(thenFunction);
        byte finalValue = function.doApplyAsByte((int)80);

        //then - finals
        assertThat(finalValue).isEqualTo((byte)100);
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    @Test
    public void testThen2ToShort() throws X  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);


        //given (+ some assertions)
        LIntToDoubleFunctionX<X> sutO = a1 -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo((int)80);
                return (double)90;
        };

        LDoubleToShortFunctionX<X> thenFunction = p -> {
                thenFunctionCalled.set(true);
                // 
                assertThat(p).isEqualTo((double)90);
                // short
                return (short)100;
        };

        //when
        LIntToShortFunctionX<X> function = sutO.thenToShort(thenFunction);
        short finalValue = function.doApplyAsShort((int)80);

        //then - finals
        assertThat(finalValue).isEqualTo((short)100);
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    @Test
    public void testThen3ToInt() throws X  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);


        //given (+ some assertions)
        LIntToDoubleFunctionX<X> sutO = a1 -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo((int)80);
                return (double)90;
        };

        LDoubleToIntFunctionX<X> thenFunction = p -> {
                thenFunctionCalled.set(true);
                // 
                assertThat(p).isEqualTo((double)90);
                // int
                return (int)100;
        };

        //when
        LIntUnaryOperatorX<X> function = sutO.thenToInt(thenFunction);
        int finalValue = function.doApplyAsInt((int)80);

        //then - finals
        assertThat(finalValue).isEqualTo((int)100);
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    @Test
    public void testThen4ToLong() throws X  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);


        //given (+ some assertions)
        LIntToDoubleFunctionX<X> sutO = a1 -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo((int)80);
                return (double)90;
        };

        LDoubleToLongFunctionX<X> thenFunction = p -> {
                thenFunctionCalled.set(true);
                // 
                assertThat(p).isEqualTo((double)90);
                // long
                return (long)100;
        };

        //when
        LIntToLongFunctionX<X> function = sutO.thenToLong(thenFunction);
        long finalValue = function.doApplyAsLong((int)80);

        //then - finals
        assertThat(finalValue).isEqualTo((long)100);
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    @Test
    public void testThen5ToFloat() throws X  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);


        //given (+ some assertions)
        LIntToDoubleFunctionX<X> sutO = a1 -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo((int)80);
                return (double)90;
        };

        LDoubleToFloatFunctionX<X> thenFunction = p -> {
                thenFunctionCalled.set(true);
                // 
                assertThat(p).isEqualTo((double)90);
                // float
                return (float)100;
        };

        //when
        LIntToFloatFunctionX<X> function = sutO.thenToFloat(thenFunction);
        float finalValue = function.doApplyAsFloat((int)80);

        //then - finals
        assertThat(finalValue).isEqualTo((float)100);
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    @Test
    public void testThen6ToDouble() throws X  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);


        //given (+ some assertions)
        LIntToDoubleFunctionX<X> sutO = a1 -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo((int)80);
                return (double)90;
        };

        LDoubleUnaryOperatorX<X> thenFunction = p -> {
                thenFunctionCalled.set(true);
                // 
                assertThat(p).isEqualTo((double)90);
                // double
                return (double)100;
        };

        //when
        LIntToDoubleFunctionX<X> function = sutO.thenToDouble(thenFunction);
        double finalValue = function.doApplyAsDouble((int)80);

        //then - finals
        assertThat(finalValue).isEqualTo((double)100);
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    @Test
    public void testThen7ToChar() throws X  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);


        //given (+ some assertions)
        LIntToDoubleFunctionX<X> sutO = a1 -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo((int)80);
                return (double)90;
        };

        LDoubleToCharFunctionX<X> thenFunction = p -> {
                thenFunctionCalled.set(true);
                // 
                assertThat(p).isEqualTo((double)90);
                // char
                return (char)100;
        };

        //when
        LIntToCharFunctionX<X> function = sutO.thenToChar(thenFunction);
        char finalValue = function.doApplyAsChar((int)80);

        //then - finals
        assertThat(finalValue).isEqualTo((char)100);
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    @Test
    public void testThen8ToBool() throws X  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);


        //given (+ some assertions)
        LIntToDoubleFunctionX<X> sutO = a1 -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo((int)80);
                return (double)90;
        };

        LDoublePredicateX<X> thenFunction = p -> {
                thenFunctionCalled.set(true);
                // 
                assertThat(p).isEqualTo((double)90);
                // boolean
                return true;
        };

        //when
        LIntPredicateX<X> function = sutO.thenToBool(thenFunction);
        boolean finalValue = function.doTest((int)80);

        //then - finals
        assertThat(finalValue).isEqualTo(true);
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    // </editor-fold>

    @Test
    public void testNesting() {
        assertThat(sut.nestingIntToDoubleFunc())
            .isInstanceOf(LIntToDoubleFunction.class);
    }

    @Test
    public void testShoving() {
        assertThat(sut.shovingIntToDoubleFunc())
            .isInstanceOf(LIntToDoubleFunction.class);
    }

    @Test
    public void testNestingX() {
        assertThat(sut.nestingIntToDoubleFuncX())
            .isInstanceOf(LIntToDoubleFunctionX.class);
    }

    @Test
    public void testShovingX() {
        assertThat(sut.shovingIntToDoubleFuncX())
            .isInstanceOf(LIntToDoubleFunctionX.class);
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void testShove() {

        // given
        LIntToDoubleFunctionX<X> sutThrowing = LIntToDoubleFunctionX.lX(a1 -> {
            throw new UnsupportedOperationException();
        });

        // when
        sutThrowing.shovingIntToDoubleFunc().doApplyAsDouble((int)100);
    }

    @Test
    public void testHandleIntToDoubleFunc() throws X {

        // given
        LIntToDoubleFunctionX<X> sutThrowing = LIntToDoubleFunctionX.lX(a1 -> {
            throw new UnsupportedOperationException();
        });

        // when
        LIntToDoubleFunctionX<X> wrapped = sutThrowing.handleIntToDoubleFuncX(h -> {
            h.wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED);
        });

        // then
        try {
            wrapped.doApplyAsDouble((int)100);
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
                .contains("LIntToDoubleFunctionX: double doApplyAsDouble(int a1) throws X");
    }


    @Test
    public void isThrowing() {
        assertThat(sut.isThrowing())
            .isTrue();
    }

    @Test void safeCompiles() {
        LIntToDoubleFunctionX r1 = LIntToDoubleFunctionX.safe(sut);
        IntToDoubleFunction r3 = LIntToDoubleFunctionX.safe(sut);
    }

    @Test void safePropagates() {
        Object result = LIntToDoubleFunctionX.safe(sut);
        assertThat(result).isSameAs(sut);
    }

    @Test void safeProtectsAgainstNpe() {
        Object result = LIntToDoubleFunctionX.safe(null);
        assertThat(result).isSameAs(LIntToDoubleFunctionX.lX(LIntToDoubleFunctionX.safe()));
    }

    @Test <Y extends Throwable> void safeSupplierPropagates() {
        LSupplierX<LIntToDoubleFunctionX<X>,Y> supplier = ()->sut;
        Object result = LIntToDoubleFunctionX.safeSupplier(supplier);
        assertThat(result).isSameAs(supplier);
    }

    @Test <Y extends Throwable> void safeSupplierProtectsAgainstNpe() {
        Object result = LIntToDoubleFunctionX.safeSupplier(null);
        assertThat(result).isSameAs(LIntToDoubleFunctionX.safeSupplier());
    }

    @Test <Y extends Throwable> void safeSupplierCompiles() {
        LSupplierX<LIntToDoubleFunctionX<X>,Y> r1 = LIntToDoubleFunctionX.safeSupplier(()->sut);
        Supplier<LIntToDoubleFunctionX<X>> r2 = LIntToDoubleFunctionX.safeSupplier(()->sut);
    }

}
