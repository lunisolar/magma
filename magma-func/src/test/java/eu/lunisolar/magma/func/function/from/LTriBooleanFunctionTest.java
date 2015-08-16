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

package eu.lunisolar.magma.func.function.from;

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
import eu.lunisolar.magma.func.consumer.*; // NOSONAR
import eu.lunisolar.magma.func.consumer.primitives.*; // NOSONAR
import eu.lunisolar.magma.func.consumer.primitives.bi.*; // NOSONAR
import eu.lunisolar.magma.func.consumer.primitives.tri.*; // NOSONAR
import eu.lunisolar.magma.func.consumer.primitives.obj.*; // NOSONAR
import eu.lunisolar.magma.func.action.*; // NOSONAR
import org.assertj.core.api.Assertions;  //NOSONAR
import org.testng.annotations.*;      //NOSONAR
import java.util.regex.Pattern;          //NOSONAR
import java.text.ParseException;         //NOSONAR
import eu.lunisolar.magma.basics.*; //NOSONAR
import eu.lunisolar.magma.basics.exceptions.*; //NOSONAR
import java.util.concurrent.atomic.AtomicInteger; //NOSONAR
import static org.assertj.core.api.Assertions.*; //NOSONAR

/** The test obviously concentrate on the interface methods the function it self is very simple.  */
public class LTriBooleanFunctionTest<R,X extends ParseException> {
    private static final String ORIGINAL_MESSAGE = "Original message";
    private static final String EXCEPTION_WAS_WRAPPED = "Exception was wrapped.";
    private static final String NO_EXCEPTION_WERE_THROWN = "No exception were thrown.";

    private Object  testValue = (R)Integer.valueOf(100);



    private LTriBooleanFunction<R> sut = new LTriBooleanFunction(){
        public @Nullable Object  doApply(boolean b1,boolean b2,boolean b3)  {
            return testValue;
        }
    };

    private LTriBooleanFunctionX<R,X> opposite = new LTriBooleanFunctionX(){
        public @Nullable Object  doApply(boolean b1,boolean b2,boolean b3) throws ParseException {
            return testValue;
        }
    };

    private LTriBooleanFunction<R> sutNull = new LTriBooleanFunction(){
        public @Nullable Object  doApply(boolean b1,boolean b2,boolean b3)  {
            return null;
        }
    };




    private LTriBooleanFunction<R> sutAlwaysThrowingUnckeck = LTriBooleanFunction.l((boolean b1,boolean b2,boolean b3) -> {
            throw new IndexOutOfBoundsException(ORIGINAL_MESSAGE);
    });


    @Test
    public void testTheResult() throws X {
        assertThat(sut.doApply(true,true,true))
            .isSameAs(testValue);
    }

    @Test
    public void testNonNullDoApply() throws X {
        assertThat(sut.nonNullDoApply(true,true,true))
            .isSameAs(testValue);
    }

    @Test
    public void testNestingDoApplyUnckeck() throws X {

        // then
        try {
            sutAlwaysThrowingUnckeck.nestingDoApply(true,true,true);
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
            sutAlwaysThrowingUnckeck.shovingDoApply(true,true,true);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                    .hasNoCause()
                    .hasMessage(ORIGINAL_MESSAGE);
        }
    }

    @Test(expectedExceptions=NullPointerException.class, expectedExceptionsMessageRegExp="\\QEvaluated value by nonNullDoApply() method cannot be null (LTriBooleanFunction: R doApply(boolean b1,boolean b2,boolean b3)).\\E")
    public void testNonNullCapturesNull() throws X {
        sutNull.nonNullDoApply(true,true,true);
    }


    @Test
    public void testFunctionalInterfaceDescription() throws X {
        assertThat(sut.functionalInterfaceDescription())
            .isEqualTo("LTriBooleanFunction: R doApply(boolean b1,boolean b2,boolean b3)");
    }

    @Test
    public void testLMethod() throws X {
        assertThat(LTriBooleanFunction.l((boolean b1,boolean b2,boolean b3) -> testValue ))
            .isInstanceOf(LTriBooleanFunction.class);
    }

    @Test
    public void testWrapMethod() throws X {
        assertThat(LTriBooleanFunction.wrap(opposite))
            .isInstanceOf(LTriBooleanFunction.class);
    }

    @Test
    public void testWrapMethodDoNotWrapsRuntimeException() throws X {
        // given
        LTriBooleanFunctionX<R,X> sutThrowing = LTriBooleanFunctionX.lX((boolean b1,boolean b2,boolean b3) -> {
            throw new UnsupportedOperationException(ORIGINAL_MESSAGE);
        });

        // when
        LTriBooleanFunction<R> wrapped = LTriBooleanFunction.wrap(sutThrowing);

        // then
        try {
            wrapped.doApply(true,true,true);
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
        LTriBooleanFunctionX<R,ParseException> sutThrowing = LTriBooleanFunctionX.lX((boolean b1,boolean b2,boolean b3) -> {
            throw new ParseException(ORIGINAL_MESSAGE, 0);
        });

        // when
        LTriBooleanFunction<R> wrapped = LTriBooleanFunction.wrap(sutThrowing);

        // then
        try {
            wrapped.doApply(true,true,true);
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
        LTriBooleanFunction<R> sutThrowing = LTriBooleanFunction.l((boolean b1,boolean b2,boolean b3) -> {
            throw new UnsupportedOperationException();
        });

        // when
        LTriBooleanFunction<R> wrapped = sutThrowing.handleTriBoolFunc(handler -> handler
            .wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED));

        // then
        try {
            wrapped.doApply(true,true,true);
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
        LTriBooleanFunction<R> sutThrowing = LTriBooleanFunction.l((boolean b1,boolean b2,boolean b3) -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LTriBooleanFunction<R> wrapped = sutThrowing.handleTriBoolFunc(handler -> handler
                .wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED)
                .throwIf(IndexOutOfBoundsException.class));

        // then
        try {
            wrapped.doApply(true,true,true);
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
        LTriBooleanFunction<R> sutThrowing = LTriBooleanFunction.l((boolean b1,boolean b2,boolean b3) -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LTriBooleanFunction<R> wrapped = sutThrowing.handleTriBoolFunc(handler -> handler
                .wrapWhen(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED)
                .throwIf(IndexOutOfBoundsException.class));

        // then
        try {
            wrapped.doApply(true,true,true);
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
        LTriBooleanFunction<R> sutThrowing = LTriBooleanFunction.l((boolean b1,boolean b2,boolean b3) -> {
            throw new UnsupportedOperationException(ORIGINAL_MESSAGE);
        });

        // when
        LTriBooleanFunction<R> wrapped = sutThrowing.handleTriBoolFunc(h -> Function4U.doNothing());

        // then
        try {
            wrapped.doApply(true,true,true);
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
    public void testtriBoolFuncComposeBoolean() throws X {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LTriBooleanFunction<Integer > sutO = (boolean b1,boolean b2,boolean b3) -> {
                mainFunctionCalled.set(true);
                assertThat(b1).isEqualTo(true);
                assertThat(b2).isEqualTo(true);
                assertThat(b3).isEqualTo(true);
                return 9;
        };

        LLogicalOperator before1 = p0 -> {
            assertThat(p0).isEqualTo(true);
            beforeCalls.incrementAndGet();
            return true;
        };
        LLogicalOperator before2 = p1 -> {
            assertThat(p1).isEqualTo(true);
            beforeCalls.incrementAndGet();
            return true;
        };
        LLogicalOperator before3 = p2 -> {
            assertThat(p2).isEqualTo(true);
            beforeCalls.incrementAndGet();
            return true;
        };

        //when
        LTriBooleanFunction<Integer > function = sutO.triBoolFuncComposeBoolean(before1,before2,before3);
        function.doApply(true,true,true);

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(beforeCalls.get()).isEqualTo(3);
    }


    @Test
    public void testtriBoolFuncCompose() throws X {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LTriBooleanFunction<Integer > sutO = (boolean b1,boolean b2,boolean b3) -> {
                mainFunctionCalled.set(true);
                assertThat(b1).isEqualTo(true);
                assertThat(b2).isEqualTo(true);
                assertThat(b3).isEqualTo(true);
                return 9;
        };

        LPredicate<Integer > before1 = p0 -> {
            assertThat(p0).isEqualTo(Integer.valueOf(80));
            beforeCalls.incrementAndGet();
            return true;
        };
        LPredicate<Integer > before2 = p1 -> {
            assertThat(p1).isEqualTo(Integer.valueOf(81));
            beforeCalls.incrementAndGet();
            return true;
        };
        LPredicate<Integer > before3 = p2 -> {
            assertThat(p2).isEqualTo(Integer.valueOf(82));
            beforeCalls.incrementAndGet();
            return true;
        };

        //when
        LTriFunction<Integer ,Integer ,Integer ,Integer > function = sutO.triBoolFuncCompose(before1,before2,before3);
        function.doApply((Integer )Integer.valueOf(80),(Integer )Integer.valueOf(81),(Integer )Integer.valueOf(82));

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(beforeCalls.get()).isEqualTo(3);
    }

    // </editor-fold>


    // <editor-fold desc="then (functional)">

    @Test
    public void testThen0() throws X  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);


        //given (+ some assertions)
        LTriBooleanFunction<Integer > sutO = (boolean b1,boolean b2,boolean b3) -> {
                mainFunctionCalled.set(true);
                assertThat(b1).isEqualTo(true);
                assertThat(b2).isEqualTo(true);
                assertThat(b3).isEqualTo(true);
                return Integer.valueOf(90);
        };

        LFunction<Integer ,Integer > thenFunction = p -> {
                thenFunctionCalled.set(true);
                // R
                assertThat(p).isEqualTo(Integer.valueOf(90));
                // V
                return Integer.valueOf(100);
        };

        //when
        LTriBooleanFunction<Integer > function = sutO.then(thenFunction);
        Integer  finalValue = function.doApply(true,true,true);

        //then - finals
        assertThat(finalValue).isEqualTo(Integer.valueOf(100));
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    @Test
    public void testThen1() throws X  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);


        //given (+ some assertions)
        LTriBooleanFunction<Integer > sutO = (boolean b1,boolean b2,boolean b3) -> {
                mainFunctionCalled.set(true);
                assertThat(b1).isEqualTo(true);
                assertThat(b2).isEqualTo(true);
                assertThat(b3).isEqualTo(true);
                return Integer.valueOf(90);
        };

        LConsumer<Integer > thenFunction = p -> {
                thenFunctionCalled.set(true);
                // R
                assertThat(p).isEqualTo(Integer.valueOf(90));
        };

        //when
        LTriBooleanConsumer function = sutO.then(thenFunction);
        function.doAccept(true,true,true);

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    // </editor-fold>

    @Test
    public void testNesting() {
        assertThat(sut.nestingTriBoolFunc())
            .isSameAs(sut)
            .isInstanceOf(LTriBooleanFunction.class);
    }

    @Test
    public void testShoving() {
        assertThat(sut.shovingTriBoolFunc())
            .isSameAs(sut)
            .isInstanceOf(LTriBooleanFunction.class);
    }

    @Test
    public void testNestingX() {
        assertThat(sut.nestingTriBoolFuncX())
            .isSameAs(sut)
            .isInstanceOf(LTriBooleanFunctionX.class);
    }

    @Test
    public void testShovingX() {
        assertThat(sut.shovingTriBoolFuncX())
            .isSameAs(sut)
            .isInstanceOf(LTriBooleanFunctionX.class);
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void testShove() {

        // given
        LTriBooleanFunction<R> sutThrowing = LTriBooleanFunction.l((boolean b1,boolean b2,boolean b3) -> {
            throw new UnsupportedOperationException();
        });

        // when
        sutThrowing.shovingTriBoolFunc().doApply(true,true,true);
    }

    @Test
    public void testHandleTriBoolFunc() throws X {

        // given
        LTriBooleanFunction<R> sutThrowing = LTriBooleanFunction.l((boolean b1,boolean b2,boolean b3) -> {
            throw new UnsupportedOperationException();
        });

        // when
        LTriBooleanFunction<R> wrapped = sutThrowing.handleTriBoolFunc(h -> {
            h.wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED);
        });

        // then
        try {
            wrapped.doApply(true,true,true);
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
                .contains("LTriBooleanFunction: R doApply(boolean b1,boolean b2,boolean b3)");
    }


    @Test
    public void isThrowing() {
        assertThat(sut.isThrowing())
            .isFalse();
    }


}


