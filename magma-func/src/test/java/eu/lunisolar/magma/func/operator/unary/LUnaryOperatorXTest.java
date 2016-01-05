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

package eu.lunisolar.magma.func.operator.unary;

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
public class LUnaryOperatorXTest<T,X extends ParseException> {
    private static final String ORIGINAL_MESSAGE = "Original message";
    private static final String EXCEPTION_WAS_WRAPPED = "Exception was wrapped.";
    private static final String NO_EXCEPTION_WERE_THROWN = "No exception were thrown.";

    private Object  testValue = (T)Integer.valueOf(100);



    private LUnaryOperatorX<T,X> sut = new LUnaryOperatorX(){
        public @Nullable Object  doApply(Object a1) throws ParseException {
            return testValue;
        }
    };

    private LUnaryOperator<T> opposite = new LUnaryOperator(){
        public @Nullable Object  doApply(Object a1)  {
            return testValue;
        }
    };

    private LUnaryOperatorX<T,X> sutNull = new LUnaryOperatorX(){
        public @Nullable Object  doApply(Object a1) throws ParseException {
            return null;
        }
    };


    private UnaryOperator jre = a1 -> testValue;


    private LUnaryOperatorX<T,ParseException> sutAlwaysThrowing = LUnaryOperatorX.lX(a1 -> {
            throw new ParseException(ORIGINAL_MESSAGE, 0);
    });

    private LUnaryOperatorX<T,RuntimeException> sutAlwaysThrowingUnckeck = LUnaryOperatorX.lX(a1 -> {
            throw new IndexOutOfBoundsException(ORIGINAL_MESSAGE);
    });


    @Test
    public void testTheResult() throws X {
        assertThat(sut.doApply((T)Integer.valueOf(100)))
            .isSameAs(testValue);
    }

    @Test
    public void testTupleCall() throws X {

        //FunctionalCall<LSingle<T>,T,X> theCall = sut;

        LSingle<T> domainObject = Tuple4U.tuple((T)Integer.valueOf(100));

        Object result = sut.tupleApply(domainObject);

        assertThat(result)
            .isSameAs(testValue);
    }

    @Test
    public void testNonNullDoApply() throws X {
        assertThat(sut.nonNullDoApply((T)Integer.valueOf(100)))
            .isSameAs(testValue);
    }

    @Test
    public void testNestingDoApplyChecked() throws X {

        // then
        try {
            sutAlwaysThrowing.nestingDoApply((T)Integer.valueOf(100));
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(NestedException.class)
                    .hasCauseExactlyInstanceOf(ParseException.class)
                    .hasMessage(ORIGINAL_MESSAGE);
        }
    }

    @Test
    public void testNestingDoApplyUnckeck() throws X {

        // then
        try {
            sutAlwaysThrowingUnckeck.nestingDoApply((T)Integer.valueOf(100));
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                    .hasNoCause()
                    .hasMessage(ORIGINAL_MESSAGE);
        }
    }

    @Test
    public void testShovingDoApplyChecked() throws X {

        // then
        try {
            sutAlwaysThrowing.shovingDoApply((T)Integer.valueOf(100));
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(ParseException.class)
                    .hasNoCause()
                    .hasMessage(ORIGINAL_MESSAGE);
        }
    }

    @Test
    public void testShovingDoApplyUnckeck() throws X {

        // then
        try {
            sutAlwaysThrowingUnckeck.shovingDoApply((T)Integer.valueOf(100));
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                    .hasNoCause()
                    .hasMessage(ORIGINAL_MESSAGE);
        }
    }

    @Test(expectedExceptions=NullPointerException.class, expectedExceptionsMessageRegExp="\\QEvaluated value by nonNullDoApply() method cannot be null (LUnaryOperatorX: T doApply(T a1) throws X).\\E")
    public void testNonNullCapturesNull() throws X {
        sutNull.nonNullDoApply((T)Integer.valueOf(100));
    }


    @Test
    public void testFunctionalInterfaceDescription() throws X {
        assertThat(sut.functionalInterfaceDescription())
            .isEqualTo("LUnaryOperatorX: T doApply(T a1) throws X");
    }

    @Test
    public void testLXMethod() throws X {
        assertThat(LUnaryOperatorX.lX(a1 -> testValue ))
            .isInstanceOf(LUnaryOperatorX.class);
    }

    @Test
    public void testWrapXMethod() throws X {
        assertThat(LUnaryOperatorX.wrapX(opposite))
            .isInstanceOf(LUnaryOperatorX.class);
    }

    @Test
    public void testWrapStdMethod() throws X {
        assertThat(LUnaryOperatorX.wrap(jre))
            .isInstanceOf(LUnaryOperatorX.class);
    }


    @Test
    public void testWrapExceptionMethodWrapsTheException() throws X {

        // given
        LUnaryOperatorX<T,X> sutThrowing = LUnaryOperatorX.lX(a1 -> {
            throw new UnsupportedOperationException();
        });

        // when
        LUnaryOperatorX<T,X> wrapped = sutThrowing.handleUnaryOpX(handler -> handler
            .wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED));

        // then
        try {
            wrapped.doApply((T)Integer.valueOf(100));
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
        LUnaryOperatorX<T,X> sutThrowing = LUnaryOperatorX.lX(a1 -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LUnaryOperatorX<T,X> wrapped = sutThrowing.handleUnaryOpX(handler -> handler
                .wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED)
                .throwIf(IndexOutOfBoundsException.class));

        // then
        try {
            wrapped.doApply((T)Integer.valueOf(100));
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
        LUnaryOperatorX<T,X> sutThrowing = LUnaryOperatorX.lX(a1 -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LUnaryOperatorX<T,X> wrapped = sutThrowing.handleUnaryOpX(handler -> handler
                .wrapWhen(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED)
                .throwIf(IndexOutOfBoundsException.class));

        // then
        try {
            wrapped.doApply((T)Integer.valueOf(100));
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
        LUnaryOperatorX<T,X> sutThrowing = LUnaryOperatorX.lX(a1 -> {
            throw (X) new ParseException(ORIGINAL_MESSAGE, 0);
        });

        // when
        LUnaryOperatorX<T,X> wrapped = sutThrowing.handleUnaryOpX(h -> Function4U.doNothing());

        // then
        try {
            wrapped.doApply((T)Integer.valueOf(100));
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(NestedException.class)
                    .hasCauseExactlyInstanceOf(ParseException.class)
                    .hasMessage(ORIGINAL_MESSAGE);
        }
    }



    // <editor-fold desc="then (functional)">

    @Test
    public void testThen0() throws X  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);


        //given (+ some assertions)
        LUnaryOperatorX<Integer ,X> sutO = a1 -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo((T)Integer.valueOf(80));
                return Integer.valueOf(90);
        };

        LFunctionX<Integer ,Integer ,X> thenFunction = p -> {
                thenFunctionCalled.set(true);
                // T
                assertThat(p).isEqualTo(Integer.valueOf(90));
                // V
                return Integer.valueOf(100);
        };

        //when
        LFunctionX<Integer ,Integer ,X> function = sutO.then(thenFunction);
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
        LUnaryOperatorX<Integer ,X> sutO = a1 -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo((T)Integer.valueOf(80));
                return Integer.valueOf(90);
        };

        LToByteFunctionX<Integer ,X> thenFunction = p -> {
                thenFunctionCalled.set(true);
                // T
                assertThat(p).isEqualTo(Integer.valueOf(90));
                // byte
                return (byte)100;
        };

        //when
        LToByteFunctionX<Integer ,X> function = sutO.thenToByte(thenFunction);
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
        LUnaryOperatorX<Integer ,X> sutO = a1 -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo((T)Integer.valueOf(80));
                return Integer.valueOf(90);
        };

        LToShortFunctionX<Integer ,X> thenFunction = p -> {
                thenFunctionCalled.set(true);
                // T
                assertThat(p).isEqualTo(Integer.valueOf(90));
                // short
                return (short)100;
        };

        //when
        LToShortFunctionX<Integer ,X> function = sutO.thenToShort(thenFunction);
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
        LUnaryOperatorX<Integer ,X> sutO = a1 -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo((T)Integer.valueOf(80));
                return Integer.valueOf(90);
        };

        LToIntFunctionX<Integer ,X> thenFunction = p -> {
                thenFunctionCalled.set(true);
                // T
                assertThat(p).isEqualTo(Integer.valueOf(90));
                // int
                return (int)100;
        };

        //when
        LToIntFunctionX<Integer ,X> function = sutO.thenToInt(thenFunction);
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
        LUnaryOperatorX<Integer ,X> sutO = a1 -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo((T)Integer.valueOf(80));
                return Integer.valueOf(90);
        };

        LToLongFunctionX<Integer ,X> thenFunction = p -> {
                thenFunctionCalled.set(true);
                // T
                assertThat(p).isEqualTo(Integer.valueOf(90));
                // long
                return (long)100;
        };

        //when
        LToLongFunctionX<Integer ,X> function = sutO.thenToLong(thenFunction);
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
        LUnaryOperatorX<Integer ,X> sutO = a1 -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo((T)Integer.valueOf(80));
                return Integer.valueOf(90);
        };

        LToFloatFunctionX<Integer ,X> thenFunction = p -> {
                thenFunctionCalled.set(true);
                // T
                assertThat(p).isEqualTo(Integer.valueOf(90));
                // float
                return (float)100;
        };

        //when
        LToFloatFunctionX<Integer ,X> function = sutO.thenToFloat(thenFunction);
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
        LUnaryOperatorX<Integer ,X> sutO = a1 -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo((T)Integer.valueOf(80));
                return Integer.valueOf(90);
        };

        LToDoubleFunctionX<Integer ,X> thenFunction = p -> {
                thenFunctionCalled.set(true);
                // T
                assertThat(p).isEqualTo(Integer.valueOf(90));
                // double
                return (double)100;
        };

        //when
        LToDoubleFunctionX<Integer ,X> function = sutO.thenToDouble(thenFunction);
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
        LUnaryOperatorX<Integer ,X> sutO = a1 -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo((T)Integer.valueOf(80));
                return Integer.valueOf(90);
        };

        LToCharFunctionX<Integer ,X> thenFunction = p -> {
                thenFunctionCalled.set(true);
                // T
                assertThat(p).isEqualTo(Integer.valueOf(90));
                // char
                return (char)100;
        };

        //when
        LToCharFunctionX<Integer ,X> function = sutO.thenToChar(thenFunction);
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
        LUnaryOperatorX<Integer ,X> sutO = a1 -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo((T)Integer.valueOf(80));
                return Integer.valueOf(90);
        };

        LPredicateX<Integer ,X> thenFunction = p -> {
                thenFunctionCalled.set(true);
                // T
                assertThat(p).isEqualTo(Integer.valueOf(90));
                // boolean
                return true;
        };

        //when
        LPredicateX<Integer ,X> function = sutO.thenToBool(thenFunction);
        boolean finalValue = function.doTest((Integer )Integer.valueOf(80));

        //then - finals
        assertThat(finalValue).isEqualTo(true);
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    // </editor-fold>
    @Test
    public void identity() throws X {
        LUnaryOperatorX<Integer ,X> identityFunction = LUnaryOperatorX.identity();

        assertThat(identityFunction.doApply((Integer )Integer.valueOf(80))).isEqualTo((Integer )Integer.valueOf(80));
    }


    @Test
    public void testNesting() {
        assertThat(sut.nestingUnaryOp())
            .isInstanceOf(LUnaryOperator.class);
    }

    @Test
    public void testShoving() {
        assertThat(sut.shovingUnaryOp())
            .isInstanceOf(LUnaryOperator.class);
    }

    @Test
    public void testNestingX() {
        assertThat(sut.nestingUnaryOpX())
            .isInstanceOf(LUnaryOperatorX.class);
    }

    @Test
    public void testShovingX() {
        assertThat(sut.shovingUnaryOpX())
            .isInstanceOf(LUnaryOperatorX.class);
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void testShove() {

        // given
        LUnaryOperatorX<T,X> sutThrowing = LUnaryOperatorX.lX(a1 -> {
            throw new UnsupportedOperationException();
        });

        // when
        sutThrowing.shovingUnaryOp().doApply((T)Integer.valueOf(100));
    }

    @Test
    public void testHandleUnaryOp() throws X {

        // given
        LUnaryOperatorX<T,X> sutThrowing = LUnaryOperatorX.lX(a1 -> {
            throw new UnsupportedOperationException();
        });

        // when
        LUnaryOperatorX<T,X> wrapped = sutThrowing.handleUnaryOpX(h -> {
            h.wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED);
        });

        // then
        try {
            wrapped.doApply((T)Integer.valueOf(100));
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
                .contains("LUnaryOperatorX: T doApply(T a1) throws X");
    }


    @Test
    public void isThrowing() {
        assertThat(sut.isThrowing())
            .isTrue();
    }

    @Test void safeCompiles() {
        LUnaryOperatorX r1 = LUnaryOperatorX.safe(sut);
        UnaryOperator r3 = LUnaryOperatorX.safe(sut);
    }

    @Test void safePropagates() {
        Object result = LUnaryOperatorX.safe(sut);
        assertThat(result).isSameAs(sut);
    }

    @Test void safeProtectsAgainstNpe() {
        Object result = LUnaryOperatorX.safe(null);
        assertThat(result).isSameAs(LUnaryOperatorX.lX(LUnaryOperatorX.safe()));
    }

    @Test <Y extends Throwable> void safeSupplierPropagates() {
        LSupplierX<LUnaryOperatorX<T,X>,Y> supplier = ()->sut;
        Object result = LUnaryOperatorX.safeSupplier(supplier);
        assertThat(result).isSameAs(supplier);
    }

    @Test <Y extends Throwable> void safeSupplierProtectsAgainstNpe() {
        Object result = LUnaryOperatorX.safeSupplier(null);
        assertThat(result).isSameAs(LUnaryOperatorX.safeSupplier());
    }

    @Test <Y extends Throwable> void safeSupplierCompiles() {
        LSupplierX<LUnaryOperatorX<T,X>,Y> r1 = LUnaryOperatorX.safeSupplier(()->sut);
        Supplier<LUnaryOperatorX<T,X>> r2 = LUnaryOperatorX.safeSupplier(()->sut);
    }

}
