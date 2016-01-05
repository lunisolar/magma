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

package eu.lunisolar.magma.func.operator.ternary;

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
public class LTernaryOperatorTest<T,X extends ParseException> {
    private static final String ORIGINAL_MESSAGE = "Original message";
    private static final String EXCEPTION_WAS_WRAPPED = "Exception was wrapped.";
    private static final String NO_EXCEPTION_WERE_THROWN = "No exception were thrown.";

    private Object  testValue = (T)Integer.valueOf(100);



    private LTernaryOperator<T> sut = new LTernaryOperator(){
        public @Nullable Object  doApply(Object a1,Object a2,Object a3)  {
            return testValue;
        }
    };

    private LTernaryOperatorX<T,X> opposite = new LTernaryOperatorX(){
        public @Nullable Object  doApply(Object a1,Object a2,Object a3) throws ParseException {
            return testValue;
        }
    };

    private LTernaryOperator<T> sutNull = new LTernaryOperator(){
        public @Nullable Object  doApply(Object a1,Object a2,Object a3)  {
            return null;
        }
    };




    private LTernaryOperator<T> sutAlwaysThrowingUnckeck = LTernaryOperator.l((T a1,T a2,T a3) -> {
            throw new IndexOutOfBoundsException(ORIGINAL_MESSAGE);
    });


    @Test
    public void testTheResult() throws X {
        assertThat(sut.doApply((T)Integer.valueOf(100),(T)Integer.valueOf(100),(T)Integer.valueOf(100)))
            .isSameAs(testValue);
    }

    @Test
    public void testTupleCall() throws X {

        //FunctionalCall<LTriple<T,T,T>,T,RuntimeException> theCall = sut;

        LTriple<T,T,T> domainObject = Tuple4U.tuple((T)Integer.valueOf(100),(T)Integer.valueOf(100),(T)Integer.valueOf(100));

        Object result = sut.tupleApply(domainObject);

        assertThat(result)
            .isSameAs(testValue);
    }

    @Test
    public void testNonNullDoApply() throws X {
        assertThat(sut.nonNullDoApply((T)Integer.valueOf(100),(T)Integer.valueOf(100),(T)Integer.valueOf(100)))
            .isSameAs(testValue);
    }

    @Test
    public void testNestingDoApplyUnckeck() throws X {

        // then
        try {
            sutAlwaysThrowingUnckeck.nestingDoApply((T)Integer.valueOf(100),(T)Integer.valueOf(100),(T)Integer.valueOf(100));
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                    .hasNoCause()
                    .hasMessage(ORIGINAL_MESSAGE);
        }
    }

    @Test
    public void testShovingDoApplyUnckeck() throws X {

        // then
        try {
            sutAlwaysThrowingUnckeck.shovingDoApply((T)Integer.valueOf(100),(T)Integer.valueOf(100),(T)Integer.valueOf(100));
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                    .hasNoCause()
                    .hasMessage(ORIGINAL_MESSAGE);
        }
    }

    @Test(expectedExceptions=NullPointerException.class, expectedExceptionsMessageRegExp="\\QEvaluated value by nonNullDoApply() method cannot be null (LTernaryOperator: T doApply(T a1,T a2,T a3)).\\E")
    public void testNonNullCapturesNull() throws X {
        sutNull.nonNullDoApply((T)Integer.valueOf(100),(T)Integer.valueOf(100),(T)Integer.valueOf(100));
    }


    @Test
    public void testFunctionalInterfaceDescription() throws X {
        assertThat(sut.functionalInterfaceDescription())
            .isEqualTo("LTernaryOperator: T doApply(T a1,T a2,T a3)");
    }

    @Test
    public void testLMethod() throws X {
        assertThat(LTernaryOperator.l((Object a1,Object a2,Object a3) -> testValue ))
            .isInstanceOf(LTernaryOperator.class);
    }

    @Test
    public void testWrapMethod() throws X {
        assertThat(LTernaryOperator.wrap(opposite))
            .isInstanceOf(LTernaryOperator.class);
    }

    @Test
    public void testWrapMethodDoNotWrapsRuntimeException() throws X {
        // given
        LTernaryOperatorX<T,X> sutThrowing = LTernaryOperatorX.lX((T a1,T a2,T a3) -> {
            throw new UnsupportedOperationException(ORIGINAL_MESSAGE);
        });

        // when
        LTernaryOperator<T> wrapped = LTernaryOperator.wrap(sutThrowing);

        // then
        try {
            wrapped.doApply((T)Integer.valueOf(100),(T)Integer.valueOf(100),(T)Integer.valueOf(100));
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
        LTernaryOperatorX<T,ParseException> sutThrowing = LTernaryOperatorX.lX((T a1,T a2,T a3) -> {
            throw new ParseException(ORIGINAL_MESSAGE, 0);
        });

        // when
        LTernaryOperator<T> wrapped = LTernaryOperator.wrap(sutThrowing);

        // then
        try {
            wrapped.doApply((T)Integer.valueOf(100),(T)Integer.valueOf(100),(T)Integer.valueOf(100));
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
        LTernaryOperator<T> sutThrowing = LTernaryOperator.l((T a1,T a2,T a3) -> {
            throw new UnsupportedOperationException();
        });

        // when
        LTernaryOperator<T> wrapped = sutThrowing.handleTernaryOp(handler -> handler
            .wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED));

        // then
        try {
            wrapped.doApply((T)Integer.valueOf(100),(T)Integer.valueOf(100),(T)Integer.valueOf(100));
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
        LTernaryOperator<T> sutThrowing = LTernaryOperator.l((T a1,T a2,T a3) -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LTernaryOperator<T> wrapped = sutThrowing.handleTernaryOp(handler -> handler
                .wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED)
                .throwIf(IndexOutOfBoundsException.class));

        // then
        try {
            wrapped.doApply((T)Integer.valueOf(100),(T)Integer.valueOf(100),(T)Integer.valueOf(100));
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
        LTernaryOperator<T> sutThrowing = LTernaryOperator.l((T a1,T a2,T a3) -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LTernaryOperator<T> wrapped = sutThrowing.handleTernaryOp(handler -> handler
                .wrapWhen(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED)
                .throwIf(IndexOutOfBoundsException.class));

        // then
        try {
            wrapped.doApply((T)Integer.valueOf(100),(T)Integer.valueOf(100),(T)Integer.valueOf(100));
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
        LTernaryOperator<T> sutThrowing = LTernaryOperator.l((T a1,T a2,T a3) -> {
            throw new UnsupportedOperationException(ORIGINAL_MESSAGE);
        });

        // when
        LTernaryOperator<T> wrapped = sutThrowing.handleTernaryOp(h -> Function4U.doNothing());

        // then
        try {
            wrapped.doApply((T)Integer.valueOf(100),(T)Integer.valueOf(100),(T)Integer.valueOf(100));
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
             .isExactlyInstanceOf(UnsupportedOperationException.class)
             .hasNoCause()
             .hasMessage(ORIGINAL_MESSAGE);
        }
    }



    // <editor-fold desc="then (functional)">

    @Test
    public void testThen0() throws X  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);


        //given (+ some assertions)
        LTernaryOperator<Integer > sutO = (Integer a1,Integer a2,Integer a3) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo((T)Integer.valueOf(80));
                assertThat(a2).isEqualTo((T)Integer.valueOf(81));
                assertThat(a3).isEqualTo((T)Integer.valueOf(82));
                return Integer.valueOf(90);
        };

        LFunction<Integer ,Integer > thenFunction = p -> {
                thenFunctionCalled.set(true);
                // T
                assertThat(p).isEqualTo(Integer.valueOf(90));
                // V
                return Integer.valueOf(100);
        };

        //when
        LTriFunction<Integer ,Integer ,Integer ,Integer > function = sutO.then(thenFunction);
        Integer  finalValue = function.doApply((Integer )Integer.valueOf(80),(Integer )Integer.valueOf(81),(Integer )Integer.valueOf(82));

        //then - finals
        assertThat(finalValue).isEqualTo(Integer.valueOf(100));
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    // </editor-fold>

    @Test
    public void testNesting() {
        assertThat(sut.nestingTernaryOp())
            .isSameAs(sut)
            .isInstanceOf(LTernaryOperator.class);
    }

    @Test
    public void testShoving() {
        assertThat(sut.shovingTernaryOp())
            .isSameAs(sut)
            .isInstanceOf(LTernaryOperator.class);
    }

    @Test
    public void testNestingX() {
        assertThat(sut.nestingTernaryOpX())
            .isSameAs(sut)
            .isInstanceOf(LTernaryOperatorX.class);
    }

    @Test
    public void testShovingX() {
        assertThat(sut.shovingTernaryOpX())
            .isSameAs(sut)
            .isInstanceOf(LTernaryOperatorX.class);
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void testShove() {

        // given
        LTernaryOperator<T> sutThrowing = LTernaryOperator.l((T a1,T a2,T a3) -> {
            throw new UnsupportedOperationException();
        });

        // when
        sutThrowing.shovingTernaryOp().doApply((T)Integer.valueOf(100),(T)Integer.valueOf(100),(T)Integer.valueOf(100));
    }

    @Test
    public void testHandleTernaryOp() throws X {

        // given
        LTernaryOperator<T> sutThrowing = LTernaryOperator.l((T a1,T a2,T a3) -> {
            throw new UnsupportedOperationException();
        });

        // when
        LTernaryOperator<T> wrapped = sutThrowing.handleTernaryOp(h -> {
            h.wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED);
        });

        // then
        try {
            wrapped.doApply((T)Integer.valueOf(100),(T)Integer.valueOf(100),(T)Integer.valueOf(100));
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
                .contains("LTernaryOperator: T doApply(T a1,T a2,T a3)");
    }


    @Test
    public void isThrowing() {
        assertThat(sut.isThrowing())
            .isFalse();
    }

    @Test void safeCompiles() {
        LTernaryOperator r1 = LTernaryOperator.safe(sut);
        LTernaryOperatorX r2 = LTernaryOperator.safe(sut);
    }

    @Test void safePropagates() {
        Object result = LTernaryOperator.safe(sut);
        assertThat(result).isSameAs(sut);
    }

    @Test void safeProtectsAgainstNpe() {
        Object result = LTernaryOperator.safe(null);
        assertThat(result).isSameAs(LTernaryOperator.l(LTernaryOperator.safe()));
    }

    @Test  void safeSupplierPropagates() {
        LSupplier<LTernaryOperator<T>> supplier = ()->sut;
        Object result = LTernaryOperator.safeSupplier(supplier);
        assertThat(result).isSameAs(supplier);
    }

    @Test  void safeSupplierProtectsAgainstNpe() {
        Object result = LTernaryOperator.safeSupplier(null);
        assertThat(result).isSameAs(LTernaryOperator.safeSupplier());
    }

    @Test  void safeSupplierCompiles() {
        LSupplier<LTernaryOperator<T>> r1 = LTernaryOperator.safeSupplier(()->sut);
    }

}
