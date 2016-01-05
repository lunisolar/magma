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

package eu.lunisolar.magma.func.operator.binary;

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
public class LBinaryOperatorXTest<T,X extends ParseException> {
    private static final String ORIGINAL_MESSAGE = "Original message";
    private static final String EXCEPTION_WAS_WRAPPED = "Exception was wrapped.";
    private static final String NO_EXCEPTION_WERE_THROWN = "No exception were thrown.";

    private Object  testValue = (T)Integer.valueOf(100);



    private LBinaryOperatorX<T,X> sut = new LBinaryOperatorX(){
        public @Nullable Object  doApply(Object a1,Object a2) throws ParseException {
            return testValue;
        }
    };

    private LBinaryOperator<T> opposite = new LBinaryOperator(){
        public @Nullable Object  doApply(Object a1,Object a2)  {
            return testValue;
        }
    };

    private LBinaryOperatorX<T,X> sutNull = new LBinaryOperatorX(){
        public @Nullable Object  doApply(Object a1,Object a2) throws ParseException {
            return null;
        }
    };


    private BinaryOperator jre = (Object a1,Object a2) -> testValue;


    private LBinaryOperatorX<T,ParseException> sutAlwaysThrowing = LBinaryOperatorX.lX((T a1,T a2) -> {
            throw new ParseException(ORIGINAL_MESSAGE, 0);
    });

    private LBinaryOperatorX<T,RuntimeException> sutAlwaysThrowingUnckeck = LBinaryOperatorX.lX((T a1,T a2) -> {
            throw new IndexOutOfBoundsException(ORIGINAL_MESSAGE);
    });


    @Test
    public void testTheResult() throws X {
        assertThat(sut.doApply((T)Integer.valueOf(100),(T)Integer.valueOf(100)))
            .isSameAs(testValue);
    }

    @Test
    public void testTupleCall() throws X {

        //FunctionalCall<LPair<T,T>,T,X> theCall = sut;

        LPair<T,T> domainObject = Tuple4U.tuple((T)Integer.valueOf(100),(T)Integer.valueOf(100));

        Object result = sut.tupleApply(domainObject);

        assertThat(result)
            .isSameAs(testValue);
    }

    @Test
    public void testNonNullDoApply() throws X {
        assertThat(sut.nonNullDoApply((T)Integer.valueOf(100),(T)Integer.valueOf(100)))
            .isSameAs(testValue);
    }

    @Test
    public void testNestingDoApplyChecked() throws X {

        // then
        try {
            sutAlwaysThrowing.nestingDoApply((T)Integer.valueOf(100),(T)Integer.valueOf(100));
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
            sutAlwaysThrowingUnckeck.nestingDoApply((T)Integer.valueOf(100),(T)Integer.valueOf(100));
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
            sutAlwaysThrowing.shovingDoApply((T)Integer.valueOf(100),(T)Integer.valueOf(100));
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
            sutAlwaysThrowingUnckeck.shovingDoApply((T)Integer.valueOf(100),(T)Integer.valueOf(100));
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                    .hasNoCause()
                    .hasMessage(ORIGINAL_MESSAGE);
        }
    }

    @Test(expectedExceptions=NullPointerException.class, expectedExceptionsMessageRegExp="\\QEvaluated value by nonNullDoApply() method cannot be null (LBinaryOperatorX: T doApply(T a1,T a2) throws X).\\E")
    public void testNonNullCapturesNull() throws X {
        sutNull.nonNullDoApply((T)Integer.valueOf(100),(T)Integer.valueOf(100));
    }


    @Test
    public void testFunctionalInterfaceDescription() throws X {
        assertThat(sut.functionalInterfaceDescription())
            .isEqualTo("LBinaryOperatorX: T doApply(T a1,T a2) throws X");
    }

    @Test
    public void testLXMethod() throws X {
        assertThat(LBinaryOperatorX.lX((Object a1,Object a2) -> testValue ))
            .isInstanceOf(LBinaryOperatorX.class);
    }

    @Test
    public void testWrapXMethod() throws X {
        assertThat(LBinaryOperatorX.wrapX(opposite))
            .isInstanceOf(LBinaryOperatorX.class);
    }

    @Test
    public void testWrapStdMethod() throws X {
        assertThat(LBinaryOperatorX.wrap(jre))
            .isInstanceOf(LBinaryOperatorX.class);
    }


    @Test
    public void testWrapExceptionMethodWrapsTheException() throws X {

        // given
        LBinaryOperatorX<T,X> sutThrowing = LBinaryOperatorX.lX((T a1,T a2) -> {
            throw new UnsupportedOperationException();
        });

        // when
        LBinaryOperatorX<T,X> wrapped = sutThrowing.handleBinaryOpX(handler -> handler
            .wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED));

        // then
        try {
            wrapped.doApply((T)Integer.valueOf(100),(T)Integer.valueOf(100));
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
        LBinaryOperatorX<T,X> sutThrowing = LBinaryOperatorX.lX((T a1,T a2) -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LBinaryOperatorX<T,X> wrapped = sutThrowing.handleBinaryOpX(handler -> handler
                .wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED)
                .throwIf(IndexOutOfBoundsException.class));

        // then
        try {
            wrapped.doApply((T)Integer.valueOf(100),(T)Integer.valueOf(100));
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
        LBinaryOperatorX<T,X> sutThrowing = LBinaryOperatorX.lX((T a1,T a2) -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LBinaryOperatorX<T,X> wrapped = sutThrowing.handleBinaryOpX(handler -> handler
                .wrapWhen(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED)
                .throwIf(IndexOutOfBoundsException.class));

        // then
        try {
            wrapped.doApply((T)Integer.valueOf(100),(T)Integer.valueOf(100));
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
        LBinaryOperatorX<T,X> sutThrowing = LBinaryOperatorX.lX((T a1,T a2) -> {
            throw (X) new ParseException(ORIGINAL_MESSAGE, 0);
        });

        // when
        LBinaryOperatorX<T,X> wrapped = sutThrowing.handleBinaryOpX(h -> Function4U.doNothing());

        // then
        try {
            wrapped.doApply((T)Integer.valueOf(100),(T)Integer.valueOf(100));
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
        LBinaryOperatorX<Integer ,X> sutO = (Integer a1,Integer a2) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo((T)Integer.valueOf(80));
                assertThat(a2).isEqualTo((T)Integer.valueOf(81));
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
        LBiFunctionX<Integer ,Integer ,Integer ,X> function = sutO.then(thenFunction);
        Integer  finalValue = function.doApply((Integer )Integer.valueOf(80),(Integer )Integer.valueOf(81));

        //then - finals
        assertThat(finalValue).isEqualTo(Integer.valueOf(100));
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    // </editor-fold>

    @Test
    public void testNesting() {
        assertThat(sut.nestingBinaryOp())
            .isInstanceOf(LBinaryOperator.class);
    }

    @Test
    public void testShoving() {
        assertThat(sut.shovingBinaryOp())
            .isInstanceOf(LBinaryOperator.class);
    }

    @Test
    public void testNestingX() {
        assertThat(sut.nestingBinaryOpX())
            .isInstanceOf(LBinaryOperatorX.class);
    }

    @Test
    public void testShovingX() {
        assertThat(sut.shovingBinaryOpX())
            .isInstanceOf(LBinaryOperatorX.class);
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void testShove() {

        // given
        LBinaryOperatorX<T,X> sutThrowing = LBinaryOperatorX.lX((T a1,T a2) -> {
            throw new UnsupportedOperationException();
        });

        // when
        sutThrowing.shovingBinaryOp().doApply((T)Integer.valueOf(100),(T)Integer.valueOf(100));
    }

    @Test
    public void testHandleBinaryOp() throws X {

        // given
        LBinaryOperatorX<T,X> sutThrowing = LBinaryOperatorX.lX((T a1,T a2) -> {
            throw new UnsupportedOperationException();
        });

        // when
        LBinaryOperatorX<T,X> wrapped = sutThrowing.handleBinaryOpX(h -> {
            h.wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED);
        });

        // then
        try {
            wrapped.doApply((T)Integer.valueOf(100),(T)Integer.valueOf(100));
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
                .contains("LBinaryOperatorX: T doApply(T a1,T a2) throws X");
    }


    @Test
    public void isThrowing() {
        assertThat(sut.isThrowing())
            .isTrue();
    }

    @Test void safeCompiles() {
        LBinaryOperatorX r1 = LBinaryOperatorX.safe(sut);
        BinaryOperator r3 = LBinaryOperatorX.safe(sut);
    }

    @Test void safePropagates() {
        Object result = LBinaryOperatorX.safe(sut);
        assertThat(result).isSameAs(sut);
    }

    @Test void safeProtectsAgainstNpe() {
        Object result = LBinaryOperatorX.safe(null);
        assertThat(result).isSameAs(LBinaryOperatorX.lX(LBinaryOperatorX.safe()));
    }

    @Test <Y extends Throwable> void safeSupplierPropagates() {
        LSupplierX<LBinaryOperatorX<T,X>,Y> supplier = ()->sut;
        Object result = LBinaryOperatorX.safeSupplier(supplier);
        assertThat(result).isSameAs(supplier);
    }

    @Test <Y extends Throwable> void safeSupplierProtectsAgainstNpe() {
        Object result = LBinaryOperatorX.safeSupplier(null);
        assertThat(result).isSameAs(LBinaryOperatorX.safeSupplier());
    }

    @Test <Y extends Throwable> void safeSupplierCompiles() {
        LSupplierX<LBinaryOperatorX<T,X>,Y> r1 = LBinaryOperatorX.safeSupplier(()->sut);
        Supplier<LBinaryOperatorX<T,X>> r2 = LBinaryOperatorX.safeSupplier(()->sut);
    }

}
