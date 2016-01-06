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
public class LByteToCharFunctionXTest<X extends ParseException> {
    private static final String ORIGINAL_MESSAGE = "Original message";
    private static final String EXCEPTION_WAS_WRAPPED = "Exception was wrapped.";
    private static final String NO_EXCEPTION_WERE_THROWN = "No exception were thrown.";

    private char testValue = (char)100;



    private LByteToCharFunctionX<X> sut = new LByteToCharFunctionX(){
        public  char doApplyAsChar(byte a1) throws ParseException {
            return testValue;
        }
    };

    private LByteToCharFunction opposite = new LByteToCharFunction(){
        public  char doApplyAsChar(byte a1)  {
            return testValue;
        }
    };



    private LByteToCharFunctionX<ParseException> sutAlwaysThrowing = LByteToCharFunctionX.lX(a1 -> {
            throw new ParseException(ORIGINAL_MESSAGE, 0);
    });

    private LByteToCharFunctionX<RuntimeException> sutAlwaysThrowingUnckeck = LByteToCharFunctionX.lX(a1 -> {
            throw new IndexOutOfBoundsException(ORIGINAL_MESSAGE);
    });


    @Test
    public void testTheResult() throws X {
        assertThat(sut.doApplyAsChar((byte)100))
            .isEqualTo(testValue);
    }

    @Test
    public void testTupleCall() throws X {

        LByteSingle domainObject = Tuple4U.tuple((byte)100);

        Object result = sut.tupleApplyAsChar(domainObject);

        assertThat(result)
            .isEqualTo(testValue);
    }

    @Test
    public void testNonNullDoApplyAsChar() throws X {
        assertThat(sut.nonNullDoApplyAsChar((byte)100))
            .isEqualTo(testValue);
    }

    @Test
    public void testNestingDoApplyAsCharChecked() throws X {

        // then
        try {
            sutAlwaysThrowing.nestingDoApplyAsChar((byte)100);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(NestedException.class)
                    .hasCauseExactlyInstanceOf(ParseException.class)
                    .hasMessage(ORIGINAL_MESSAGE);
        }
    }

    @Test
    public void testNestingDoApplyAsCharUnckeck() throws X {

        // then
        try {
            sutAlwaysThrowingUnckeck.nestingDoApplyAsChar((byte)100);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                    .hasNoCause()
                    .hasMessage(ORIGINAL_MESSAGE);
        }
    }

    @Test
    public void testShovingDoApplyAsCharChecked() throws X {

        // then
        try {
            sutAlwaysThrowing.shovingDoApplyAsChar((byte)100);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(ParseException.class)
                    .hasNoCause()
                    .hasMessage(ORIGINAL_MESSAGE);
        }
    }

    @Test
    public void testShovingDoApplyAsCharUnckeck() throws X {

        // then
        try {
            sutAlwaysThrowingUnckeck.shovingDoApplyAsChar((byte)100);
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
            .isEqualTo("LByteToCharFunctionX: char doApplyAsChar(byte a1) throws X");
    }

    @Test
    public void testLXMethod() throws X {
        assertThat(LByteToCharFunctionX.lX(a1 -> testValue ))
            .isInstanceOf(LByteToCharFunctionX.class);
    }

    @Test
    public void testWrapXMethod() throws X {
        assertThat(LByteToCharFunctionX.wrapX(opposite))
            .isInstanceOf(LByteToCharFunctionX.class);
    }


    @Test
    public void testWrapExceptionMethodWrapsTheException() throws X {

        // given
        LByteToCharFunctionX<X> sutThrowing = LByteToCharFunctionX.lX(a1 -> {
            throw new UnsupportedOperationException();
        });

        // when
        LByteToCharFunctionX<X> wrapped = sutThrowing.handleByteToCharFuncX(handler -> handler
            .wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED));

        // then
        try {
            wrapped.doApplyAsChar((byte)100);
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
        LByteToCharFunctionX<X> sutThrowing = LByteToCharFunctionX.lX(a1 -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LByteToCharFunctionX<X> wrapped = sutThrowing.handleByteToCharFuncX(handler -> handler
                .wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED)
                .throwIf(IndexOutOfBoundsException.class));

        // then
        try {
            wrapped.doApplyAsChar((byte)100);
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
        LByteToCharFunctionX<X> sutThrowing = LByteToCharFunctionX.lX(a1 -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LByteToCharFunctionX<X> wrapped = sutThrowing.handleByteToCharFuncX(handler -> handler
                .wrapWhen(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED)
                .throwIf(IndexOutOfBoundsException.class));

        // then
        try {
            wrapped.doApplyAsChar((byte)100);
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
        LByteToCharFunctionX<X> sutThrowing = LByteToCharFunctionX.lX(a1 -> {
            throw (X) new ParseException(ORIGINAL_MESSAGE, 0);
        });

        // when
        LByteToCharFunctionX<X> wrapped = sutThrowing.handleByteToCharFuncX(h -> Function4U.doNothing());

        // then
        try {
            wrapped.doApplyAsChar((byte)100);
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
    public void testbyteToCharFuncComposeByte() throws X {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LByteToCharFunctionX<X> sutO = a1 -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo((byte)90);
                return (char)100;
        };

        LByteUnaryOperatorX<X> before1 = p0 -> {
            assertThat(p0).isEqualTo((byte)80);
            beforeCalls.incrementAndGet();
            return (byte)90;
        };

        //when
        LByteToCharFunctionX<X> function = sutO.byteToCharFuncComposeByte(before1);
        function.doApplyAsChar((byte)80);

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(beforeCalls.get()).isEqualTo(1);
    }


    @Test
    public void testbyteToCharFuncCompose() throws X {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LByteToCharFunctionX<X> sutO = a1 -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo((byte)90);
                return (char)100;
        };

        LToByteFunctionX<Integer ,X> before1 = p0 -> {
            assertThat(p0).isEqualTo(Integer.valueOf(80));
            beforeCalls.incrementAndGet();
            return (byte)90;
        };

        //when
        LToCharFunctionX<Integer ,X> function = sutO.byteToCharFuncCompose(before1);
        function.doApplyAsChar((Integer )Integer.valueOf(80));

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
        LByteToCharFunctionX<X> sutO = a1 -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo((byte)80);
                return (char)90;
        };

        LCharFunctionX<Integer ,X> thenFunction = p -> {
                thenFunctionCalled.set(true);
                // 
                assertThat(p).isEqualTo((char)90);
                // V
                return Integer.valueOf(100);
        };

        //when
        LByteFunctionX<Integer ,X> function = sutO.then(thenFunction);
        Integer  finalValue = function.doApply((byte)80);

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
        LByteToCharFunctionX<X> sutO = a1 -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo((byte)80);
                return (char)90;
        };

        LCharToByteFunctionX<X> thenFunction = p -> {
                thenFunctionCalled.set(true);
                // 
                assertThat(p).isEqualTo((char)90);
                // byte
                return (byte)100;
        };

        //when
        LByteUnaryOperatorX<X> function = sutO.thenToByte(thenFunction);
        byte finalValue = function.doApplyAsByte((byte)80);

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
        LByteToCharFunctionX<X> sutO = a1 -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo((byte)80);
                return (char)90;
        };

        LCharToShortFunctionX<X> thenFunction = p -> {
                thenFunctionCalled.set(true);
                // 
                assertThat(p).isEqualTo((char)90);
                // short
                return (short)100;
        };

        //when
        LByteToShortFunctionX<X> function = sutO.thenToShort(thenFunction);
        short finalValue = function.doApplyAsShort((byte)80);

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
        LByteToCharFunctionX<X> sutO = a1 -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo((byte)80);
                return (char)90;
        };

        LCharToIntFunctionX<X> thenFunction = p -> {
                thenFunctionCalled.set(true);
                // 
                assertThat(p).isEqualTo((char)90);
                // int
                return (int)100;
        };

        //when
        LByteToIntFunctionX<X> function = sutO.thenToInt(thenFunction);
        int finalValue = function.doApplyAsInt((byte)80);

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
        LByteToCharFunctionX<X> sutO = a1 -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo((byte)80);
                return (char)90;
        };

        LCharToLongFunctionX<X> thenFunction = p -> {
                thenFunctionCalled.set(true);
                // 
                assertThat(p).isEqualTo((char)90);
                // long
                return (long)100;
        };

        //when
        LByteToLongFunctionX<X> function = sutO.thenToLong(thenFunction);
        long finalValue = function.doApplyAsLong((byte)80);

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
        LByteToCharFunctionX<X> sutO = a1 -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo((byte)80);
                return (char)90;
        };

        LCharToFloatFunctionX<X> thenFunction = p -> {
                thenFunctionCalled.set(true);
                // 
                assertThat(p).isEqualTo((char)90);
                // float
                return (float)100;
        };

        //when
        LByteToFloatFunctionX<X> function = sutO.thenToFloat(thenFunction);
        float finalValue = function.doApplyAsFloat((byte)80);

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
        LByteToCharFunctionX<X> sutO = a1 -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo((byte)80);
                return (char)90;
        };

        LCharToDoubleFunctionX<X> thenFunction = p -> {
                thenFunctionCalled.set(true);
                // 
                assertThat(p).isEqualTo((char)90);
                // double
                return (double)100;
        };

        //when
        LByteToDoubleFunctionX<X> function = sutO.thenToDouble(thenFunction);
        double finalValue = function.doApplyAsDouble((byte)80);

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
        LByteToCharFunctionX<X> sutO = a1 -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo((byte)80);
                return (char)90;
        };

        LCharUnaryOperatorX<X> thenFunction = p -> {
                thenFunctionCalled.set(true);
                // 
                assertThat(p).isEqualTo((char)90);
                // char
                return (char)100;
        };

        //when
        LByteToCharFunctionX<X> function = sutO.thenToChar(thenFunction);
        char finalValue = function.doApplyAsChar((byte)80);

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
        LByteToCharFunctionX<X> sutO = a1 -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo((byte)80);
                return (char)90;
        };

        LCharPredicateX<X> thenFunction = p -> {
                thenFunctionCalled.set(true);
                // 
                assertThat(p).isEqualTo((char)90);
                // boolean
                return true;
        };

        //when
        LBytePredicateX<X> function = sutO.thenToBool(thenFunction);
        boolean finalValue = function.doTest((byte)80);

        //then - finals
        assertThat(finalValue).isEqualTo(true);
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    // </editor-fold>

    @Test
    public void testNesting() {
        assertThat(sut.nestingByteToCharFunc())
            .isInstanceOf(LByteToCharFunction.class);
    }

    @Test
    public void testShoving() {
        assertThat(sut.shovingByteToCharFunc())
            .isInstanceOf(LByteToCharFunction.class);
    }

    @Test
    public void testNestingX() {
        assertThat(sut.nestingByteToCharFuncX())
            .isInstanceOf(LByteToCharFunctionX.class);
    }

    @Test
    public void testShovingX() {
        assertThat(sut.shovingByteToCharFuncX())
            .isInstanceOf(LByteToCharFunctionX.class);
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void testShove() {

        // given
        LByteToCharFunctionX<X> sutThrowing = LByteToCharFunctionX.lX(a1 -> {
            throw new UnsupportedOperationException();
        });

        // when
        sutThrowing.shovingByteToCharFunc().doApplyAsChar((byte)100);
    }

    @Test
    public void testHandleByteToCharFunc() throws X {

        // given
        LByteToCharFunctionX<X> sutThrowing = LByteToCharFunctionX.lX(a1 -> {
            throw new UnsupportedOperationException();
        });

        // when
        LByteToCharFunctionX<X> wrapped = sutThrowing.handleByteToCharFuncX(h -> {
            h.wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED);
        });

        // then
        try {
            wrapped.doApplyAsChar((byte)100);
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
                .contains("LByteToCharFunctionX: char doApplyAsChar(byte a1) throws X");
    }


    @Test
    public void isThrowing() {
        assertThat(sut.isThrowing())
            .isTrue();
    }

    @Test void safeCompiles() {
        LByteToCharFunctionX r1 = LByteToCharFunctionX.safe(sut); //NOSONAR
    }

    @Test void safePropagates() {
        Object result = LByteToCharFunctionX.safe(sut);
        assertThat(result).isSameAs(sut);
    }

    @Test void safeProtectsAgainstNpe() {
        Object result = LByteToCharFunctionX.safe(null);
        assertThat(result).isSameAs(LByteToCharFunctionX.lX(LByteToCharFunctionX.safe()));
    }

    @Test <Y extends Throwable> void safeSupplierPropagates() {
        LSupplierX<LByteToCharFunctionX<X>,Y> supplier = ()->sut;
        Object result = LByteToCharFunctionX.safeSupplier(supplier);
        assertThat(result).isSameAs(supplier);
    }

    @Test <Y extends Throwable> void safeSupplierProtectsAgainstNpe() {
        Object result = LByteToCharFunctionX.safeSupplier(null);
        assertThat(result).isSameAs(LByteToCharFunctionX.safeSupplier());
    }

    @Test <Y extends Throwable> void safeSupplierCompiles() {
        LSupplierX<LByteToCharFunctionX<X>,Y> r1 = LByteToCharFunctionX.safeSupplier(()->sut);  //NOSONAR
    }

}
