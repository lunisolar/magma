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
public class LToShortFunctionTest<T,X extends ParseException> {
    private static final String ORIGINAL_MESSAGE = "Original message";
    private static final String EXCEPTION_WAS_WRAPPED = "Exception was wrapped.";
    private static final String NO_EXCEPTION_WERE_THROWN = "No exception were thrown.";

    private short testValue = (short)100;



    private LToShortFunction<T> sut = new LToShortFunction(){
        public  short doApplyAsShort(Object a1)  {
            return testValue;
        }
    };

    private LToShortFunctionX<T,X> opposite = new LToShortFunctionX(){
        public  short doApplyAsShort(Object a1) throws ParseException {
            return testValue;
        }
    };




    private LToShortFunction<T> sutAlwaysThrowingUnckeck = LToShortFunction.l(a1 -> {
            throw new IndexOutOfBoundsException(ORIGINAL_MESSAGE);
    });


    @Test
    public void testTheResult() throws X {
        assertThat(sut.doApplyAsShort((T)Integer.valueOf(100)))
            .isEqualTo(testValue);
    }

    @Test
    public void testTupleCall() throws X {

        LSingle<T> domainObject = Tuple4U.tuple((T)Integer.valueOf(100));

        Object result = sut.tupleApplyAsShort(domainObject);

        assertThat(result)
            .isEqualTo(testValue);
    }

    @Test
    public void testNonNullDoApplyAsShort() throws X {
        assertThat(sut.nonNullDoApplyAsShort((T)Integer.valueOf(100)))
            .isEqualTo(testValue);
    }

    @Test
    public void testNestingDoApplyAsShortUnckeck() throws X {

        // then
        try {
            sutAlwaysThrowingUnckeck.nestingDoApplyAsShort((T)Integer.valueOf(100));
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                    .hasNoCause()
                    .hasMessage(ORIGINAL_MESSAGE);
        }
    }

    @Test
    public void testShovingDoApplyAsShortUnckeck() throws X {

        // then
        try {
            sutAlwaysThrowingUnckeck.shovingDoApplyAsShort((T)Integer.valueOf(100));
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
            .isEqualTo("LToShortFunction: short doApplyAsShort(T a1)");
    }

    @Test
    public void testLMethod() throws X {
        assertThat(LToShortFunction.l(a1 -> testValue ))
            .isInstanceOf(LToShortFunction.class);
    }

    @Test
    public void testWrapMethod() throws X {
        assertThat(LToShortFunction.wrap(opposite))
            .isInstanceOf(LToShortFunction.class);
    }

    @Test
    public void testWrapMethodDoNotWrapsRuntimeException() throws X {
        // given
        LToShortFunctionX<T,X> sutThrowing = LToShortFunctionX.lX(a1 -> {
            throw new UnsupportedOperationException(ORIGINAL_MESSAGE);
        });

        // when
        LToShortFunction<T> wrapped = LToShortFunction.wrap(sutThrowing);

        // then
        try {
            wrapped.doApplyAsShort((T)Integer.valueOf(100));
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
        LToShortFunctionX<T,ParseException> sutThrowing = LToShortFunctionX.lX(a1 -> {
            throw new ParseException(ORIGINAL_MESSAGE, 0);
        });

        // when
        LToShortFunction<T> wrapped = LToShortFunction.wrap(sutThrowing);

        // then
        try {
            wrapped.doApplyAsShort((T)Integer.valueOf(100));
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(NestedException.class)
                    .hasCauseExactlyInstanceOf(ParseException.class)
                    .hasMessage(ORIGINAL_MESSAGE);
        }
    }


    @Test
    public void testWrapExceptionMethodWrapsTheException() throws X {

        // given
        LToShortFunction<T> sutThrowing = LToShortFunction.l(a1 -> {
            throw new UnsupportedOperationException();
        });

        // when
        LToShortFunction<T> wrapped = sutThrowing.handleToShortFunc(handler -> handler
            .wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED));

        // then
        try {
            wrapped.doApplyAsShort((T)Integer.valueOf(100));
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
        LToShortFunction<T> sutThrowing = LToShortFunction.l(a1 -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LToShortFunction<T> wrapped = sutThrowing.handleToShortFunc(handler -> handler
                .wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED)
                .throwIf(IndexOutOfBoundsException.class));

        // then
        try {
            wrapped.doApplyAsShort((T)Integer.valueOf(100));
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
        LToShortFunction<T> sutThrowing = LToShortFunction.l(a1 -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LToShortFunction<T> wrapped = sutThrowing.handleToShortFunc(handler -> handler
                .wrapWhen(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED)
                .throwIf(IndexOutOfBoundsException.class));

        // then
        try {
            wrapped.doApplyAsShort((T)Integer.valueOf(100));
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
        LToShortFunction<T> sutThrowing = LToShortFunction.l(a1 -> {
            throw new UnsupportedOperationException(ORIGINAL_MESSAGE);
        });

        // when
        LToShortFunction<T> wrapped = sutThrowing.handleToShortFunc(h -> Function4U.doNothing());

        // then
        try {
            wrapped.doApplyAsShort((T)Integer.valueOf(100));
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
    public void testtoShortFuncCompose() throws X {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LToShortFunction<Integer > sutO = a1 -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo((T)Integer.valueOf(90));
                return (short)100;
        };

        LFunction<Integer ,Integer > before1 = p0 -> {
            assertThat(p0).isEqualTo((T)Integer.valueOf(80));
            beforeCalls.incrementAndGet();
            return 90;
        };

        //when
        LToShortFunction<Integer > function = sutO.toShortFuncCompose(before1);
        function.doApplyAsShort((Integer )Integer.valueOf(80));

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
        LToShortFunction<Integer > sutO = a1 -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo((T)Integer.valueOf(80));
                return (short)90;
        };

        LShortFunction<Integer > thenFunction = p -> {
                thenFunctionCalled.set(true);
                // 
                assertThat(p).isEqualTo((short)90);
                // V
                return Integer.valueOf(100);
        };

        //when
        LFunction<Integer ,Integer > function = sutO.then(thenFunction);
        Integer  finalValue = function.doApply((Integer )Integer.valueOf(80));

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
        LToShortFunction<Integer > sutO = a1 -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo((T)Integer.valueOf(80));
                return (short)90;
        };

        LShortToByteFunction thenFunction = p -> {
                thenFunctionCalled.set(true);
                // 
                assertThat(p).isEqualTo((short)90);
                // byte
                return (byte)100;
        };

        //when
        LToByteFunction<Integer > function = sutO.thenToByte(thenFunction);
        byte finalValue = function.doApplyAsByte((Integer )Integer.valueOf(80));

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
        LToShortFunction<Integer > sutO = a1 -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo((T)Integer.valueOf(80));
                return (short)90;
        };

        LShortUnaryOperator thenFunction = p -> {
                thenFunctionCalled.set(true);
                // 
                assertThat(p).isEqualTo((short)90);
                // short
                return (short)100;
        };

        //when
        LToShortFunction<Integer > function = sutO.thenToShort(thenFunction);
        short finalValue = function.doApplyAsShort((Integer )Integer.valueOf(80));

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
        LToShortFunction<Integer > sutO = a1 -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo((T)Integer.valueOf(80));
                return (short)90;
        };

        LShortToIntFunction thenFunction = p -> {
                thenFunctionCalled.set(true);
                // 
                assertThat(p).isEqualTo((short)90);
                // int
                return (int)100;
        };

        //when
        LToIntFunction<Integer > function = sutO.thenToInt(thenFunction);
        int finalValue = function.doApplyAsInt((Integer )Integer.valueOf(80));

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
        LToShortFunction<Integer > sutO = a1 -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo((T)Integer.valueOf(80));
                return (short)90;
        };

        LShortToLongFunction thenFunction = p -> {
                thenFunctionCalled.set(true);
                // 
                assertThat(p).isEqualTo((short)90);
                // long
                return (long)100;
        };

        //when
        LToLongFunction<Integer > function = sutO.thenToLong(thenFunction);
        long finalValue = function.doApplyAsLong((Integer )Integer.valueOf(80));

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
        LToShortFunction<Integer > sutO = a1 -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo((T)Integer.valueOf(80));
                return (short)90;
        };

        LShortToFloatFunction thenFunction = p -> {
                thenFunctionCalled.set(true);
                // 
                assertThat(p).isEqualTo((short)90);
                // float
                return (float)100;
        };

        //when
        LToFloatFunction<Integer > function = sutO.thenToFloat(thenFunction);
        float finalValue = function.doApplyAsFloat((Integer )Integer.valueOf(80));

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
        LToShortFunction<Integer > sutO = a1 -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo((T)Integer.valueOf(80));
                return (short)90;
        };

        LShortToDoubleFunction thenFunction = p -> {
                thenFunctionCalled.set(true);
                // 
                assertThat(p).isEqualTo((short)90);
                // double
                return (double)100;
        };

        //when
        LToDoubleFunction<Integer > function = sutO.thenToDouble(thenFunction);
        double finalValue = function.doApplyAsDouble((Integer )Integer.valueOf(80));

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
        LToShortFunction<Integer > sutO = a1 -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo((T)Integer.valueOf(80));
                return (short)90;
        };

        LShortToCharFunction thenFunction = p -> {
                thenFunctionCalled.set(true);
                // 
                assertThat(p).isEqualTo((short)90);
                // char
                return (char)100;
        };

        //when
        LToCharFunction<Integer > function = sutO.thenToChar(thenFunction);
        char finalValue = function.doApplyAsChar((Integer )Integer.valueOf(80));

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
        LToShortFunction<Integer > sutO = a1 -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo((T)Integer.valueOf(80));
                return (short)90;
        };

        LShortPredicate thenFunction = p -> {
                thenFunctionCalled.set(true);
                // 
                assertThat(p).isEqualTo((short)90);
                // boolean
                return true;
        };

        //when
        LPredicate<Integer > function = sutO.thenToBool(thenFunction);
        boolean finalValue = function.doTest((Integer )Integer.valueOf(80));

        //then - finals
        assertThat(finalValue).isEqualTo(true);
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    // </editor-fold>

    @Test
    public void testNesting() {
        assertThat(sut.nestingToShortFunc())
            .isSameAs(sut)
            .isInstanceOf(LToShortFunction.class);
    }

    @Test
    public void testShoving() {
        assertThat(sut.shovingToShortFunc())
            .isSameAs(sut)
            .isInstanceOf(LToShortFunction.class);
    }

    @Test
    public void testNestingX() {
        assertThat(sut.nestingToShortFuncX())
            .isSameAs(sut)
            .isInstanceOf(LToShortFunctionX.class);
    }

    @Test
    public void testShovingX() {
        assertThat(sut.shovingToShortFuncX())
            .isSameAs(sut)
            .isInstanceOf(LToShortFunctionX.class);
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void testShove() {

        // given
        LToShortFunction<T> sutThrowing = LToShortFunction.l(a1 -> {
            throw new UnsupportedOperationException();
        });

        // when
        sutThrowing.shovingToShortFunc().doApplyAsShort((T)Integer.valueOf(100));
    }

    @Test
    public void testHandleToShortFunc() throws X {

        // given
        LToShortFunction<T> sutThrowing = LToShortFunction.l(a1 -> {
            throw new UnsupportedOperationException();
        });

        // when
        LToShortFunction<T> wrapped = sutThrowing.handleToShortFunc(h -> {
            h.wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED);
        });

        // then
        try {
            wrapped.doApplyAsShort((T)Integer.valueOf(100));
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
                .contains("LToShortFunction: short doApplyAsShort(T a1)");
    }


    @Test
    public void isThrowing() {
        assertThat(sut.isThrowing())
            .isFalse();
    }

    @Test void safeCompiles() {
        LToShortFunction r1 = LToShortFunction.safe(sut); //NOSONAR
        LToShortFunctionX r2 = LToShortFunction.safe(sut); //NOSONAR
    }

    @Test void safePropagates() {
        Object result = LToShortFunction.safe(sut);
        assertThat(result).isSameAs(sut);
    }

    @Test void safeProtectsAgainstNpe() {
        Object result = LToShortFunction.safe(null);
        assertThat(result).isSameAs(LToShortFunction.l(LToShortFunction.safe()));
    }

    @Test  void safeSupplierPropagates() {
        LSupplier<LToShortFunction<T>> supplier = ()->sut;
        Object result = LToShortFunction.safeSupplier(supplier);
        assertThat(result).isSameAs(supplier);
    }

    @Test  void safeSupplierProtectsAgainstNpe() {
        Object result = LToShortFunction.safeSupplier(null);
        assertThat(result).isSameAs(LToShortFunction.safeSupplier());
    }

    @Test  void safeSupplierCompiles() {
        LSupplier<LToShortFunction<T>> r1 = LToShortFunction.safeSupplier(()->sut);  //NOSONAR
    }

}
