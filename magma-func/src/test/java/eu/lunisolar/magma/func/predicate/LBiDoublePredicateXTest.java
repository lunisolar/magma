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

package eu.lunisolar.magma.func.predicate;

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
public class LBiDoublePredicateXTest<X extends ParseException> {
    private static final String ORIGINAL_MESSAGE = "Original message";
    private static final String EXCEPTION_WAS_WRAPPED = "Exception was wrapped.";
    private static final String NO_EXCEPTION_WERE_THROWN = "No exception were thrown.";

    private boolean testValue = true;



    private LBiDoublePredicateX<X> sut = new LBiDoublePredicateX(){
        public  boolean doTest(double d1,double d2) throws ParseException {
            return testValue;
        }
    };

    private LBiDoublePredicate opposite = new LBiDoublePredicate(){
        public  boolean doTest(double d1,double d2)  {
            return testValue;
        }
    };



    private LBiDoublePredicateX<ParseException> sutAlwaysThrowing = LBiDoublePredicateX.lX((double d1,double d2) -> {
            throw new ParseException(ORIGINAL_MESSAGE, 0);
    });

    private LBiDoublePredicateX<RuntimeException> sutAlwaysThrowingUnckeck = LBiDoublePredicateX.lX((double d1,double d2) -> {
            throw new IndexOutOfBoundsException(ORIGINAL_MESSAGE);
    });


    @Test
    public void testTheResult() throws X {
        assertThat(sut.doTest((double)100,(double)100))
            .isEqualTo(testValue);
    }

    @Test
    public void testNonNullDoTest() throws X {
        assertThat(sut.nonNullDoTest((double)100,(double)100))
            .isEqualTo(testValue);
    }

    @Test
    public void testNestingDoTestChecked() throws X {

        // then
        try {
            sutAlwaysThrowing.nestingDoTest((double)100,(double)100);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(NestedException.class)
                    .hasCauseExactlyInstanceOf(ParseException.class)
                    .hasMessage(ORIGINAL_MESSAGE);
        }
    }

    @Test
    public void testNestingDoTestUnckeck() throws X {

        // then
        try {
            sutAlwaysThrowingUnckeck.nestingDoTest((double)100,(double)100);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                    .hasNoCause()
                    .hasMessage(ORIGINAL_MESSAGE);
        }
    }

    @Test
    public void testShovingDoTestChecked() throws X {

        // then
        try {
            sutAlwaysThrowing.shovingDoTest((double)100,(double)100);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(ParseException.class)
                    .hasNoCause()
                    .hasMessage(ORIGINAL_MESSAGE);
        }
    }

    @Test
    public void testShovingDoTestUnckeck() throws X {

        // then
        try {
            sutAlwaysThrowingUnckeck.shovingDoTest((double)100,(double)100);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                    .hasNoCause()
                    .hasMessage(ORIGINAL_MESSAGE);
        }
    }

    @Test
    public void testApplyAsBooleanShouldNotModifyValue() throws X {
        assertThat(sut.doApplyAsBoolean((double)100,(double)100))
            .isEqualTo(testValue);

    }


    @Test
    public void testFunctionalInterfaceDescription() throws X {
        assertThat(sut.functionalInterfaceDescription())
            .isEqualTo("LBiDoublePredicateX: boolean doTest(double d1,double d2) throws X");
    }

    @Test
    public void testLXMethod() throws X {
        assertThat(LBiDoublePredicateX.lX((double d1,double d2) -> testValue ))
            .isInstanceOf(LBiDoublePredicateX.class);
    }

    @Test
    public void testWrapXMethod() throws X {
        assertThat(LBiDoublePredicateX.wrapX(opposite))
            .isInstanceOf(LBiDoublePredicateX.class);
    }


    @Test
    public void testWrapExceptionMethodWrapsTheException() throws X {

        // given
        LBiDoublePredicateX<X> sutThrowing = LBiDoublePredicateX.lX((double d1,double d2) -> {
            throw new UnsupportedOperationException();
        });

        // when
        LBiDoublePredicateX<X> wrapped = sutThrowing.handleBiDoublePredX(handler -> handler
            .wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED));

        // then
        try {
            wrapped.doTest((double)100,(double)100);
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
        LBiDoublePredicateX<X> sutThrowing = LBiDoublePredicateX.lX((double d1,double d2) -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LBiDoublePredicateX<X> wrapped = sutThrowing.handleBiDoublePredX(handler -> handler
                .wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED)
                .throwIf(IndexOutOfBoundsException.class));

        // then
        try {
            wrapped.doTest((double)100,(double)100);
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
        LBiDoublePredicateX<X> sutThrowing = LBiDoublePredicateX.lX((double d1,double d2) -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LBiDoublePredicateX<X> wrapped = sutThrowing.handleBiDoublePredX(handler -> handler
                .wrapWhen(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED)
                .throwIf(IndexOutOfBoundsException.class));

        // then
        try {
            wrapped.doTest((double)100,(double)100);
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
        LBiDoublePredicateX<X> sutThrowing = LBiDoublePredicateX.lX((double d1,double d2) -> {
            throw (X) new ParseException(ORIGINAL_MESSAGE, 0);
        });

        // when
        LBiDoublePredicateX<X> wrapped = sutThrowing.handleBiDoublePredX(h -> Function4U.doNothing());

        // then
        try {
            wrapped.doTest((double)100,(double)100);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(NestedException.class)
                    .hasCauseExactlyInstanceOf(ParseException.class)
                    .hasMessage(ORIGINAL_MESSAGE);
        }
    }

    @Test
    public void testnegate() throws X {
        assertThat(sut.negate().doTest((double)100,(double)100))
            .isEqualTo(!testValue);
    }

    @DataProvider(name="boolean permutations")
    public Object[][] permuations() {
       return new Object[][] {
            // b1 , b2   , AND  , OR   , XOR
            {false, false, false, false, false },
            {true , false, false, true , true },
            {false, true , false, true , true },
            {true , true , true , true , false },
       };
    }

    @Test(dataProvider="boolean permutations")
    public void testAndOrXor(final boolean f1Result, final boolean f2Result, final boolean andResult, final boolean orResult, final boolean xorResult) throws X {

        //given
        LBiDoublePredicateX<X> fun1 = LBiDoublePredicateX.lX((double d1,double d2) -> f1Result);
        LBiDoublePredicateX<X> fun2 = LBiDoublePredicateX.lX((double d1,double d2) -> f2Result);

        //when
        LBiDoublePredicateX<X> andFunction = fun1.and(fun2);
        LBiDoublePredicateX<X> orFunction = fun1.or(fun2);
        LBiDoublePredicateX<X> xorFunction = fun1.xor(fun2);

        //then
        assertThat(andFunction.doTest((double)100,(double)100))
                .isEqualTo(andResult);

        assertThat(orFunction.doTest((double)100,(double)100))
                .isEqualTo(orResult);

        assertThat(xorFunction.doTest((double)100,(double)100))
                .isEqualTo(xorResult);
    }

    @Test
    public void isEqual() throws X  {
        //when
        LBiDoublePredicateX<X> equals = LBiDoublePredicateX.isEqual((double)100,(double)100);

        //then
        assertThat(equals.doTest((double)100,(double)100))
                .isTrue();

        assertThat(equals.doTest((double)0,(double)0))
                .isFalse();
    }



    // <editor-fold desc="compose (functional)">

    @Test
    public void testbiDoublePredComposeDouble() throws X {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LBiDoublePredicateX<X> sutO = (double d1,double d2) -> {
                mainFunctionCalled.set(true);
                assertThat(d1).isEqualTo((double)90);
                assertThat(d2).isEqualTo((double)91);
                return true;
        };

        LDoubleUnaryOperatorX<X> before1 = p0 -> {
            assertThat(p0).isEqualTo((double)80);
            beforeCalls.incrementAndGet();
            return (double)90;
        };
        LDoubleUnaryOperatorX<X> before2 = p1 -> {
            assertThat(p1).isEqualTo((double)81);
            beforeCalls.incrementAndGet();
            return (double)91;
        };

        //when
        LBiDoublePredicateX<X> function = sutO.biDoublePredComposeDouble(before1,before2);
        function.doTest((double)80,(double)81);

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(beforeCalls.get()).isEqualTo(2);
    }


    @Test
    public void testbiDoublePredCompose() throws X {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LBiDoublePredicateX<X> sutO = (double d1,double d2) -> {
                mainFunctionCalled.set(true);
                assertThat(d1).isEqualTo((double)90);
                assertThat(d2).isEqualTo((double)91);
                return true;
        };

        LToDoubleFunctionX<Integer ,X> before1 = p0 -> {
            assertThat(p0).isEqualTo(Integer.valueOf(80));
            beforeCalls.incrementAndGet();
            return (double)90;
        };
        LToDoubleFunctionX<Integer ,X> before2 = p1 -> {
            assertThat(p1).isEqualTo(Integer.valueOf(81));
            beforeCalls.incrementAndGet();
            return (double)91;
        };

        //when
        LBiPredicateX<Integer ,Integer ,X> function = sutO.biDoublePredCompose(before1,before2);
        function.doTest((Integer )Integer.valueOf(80),(Integer )Integer.valueOf(81));

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(beforeCalls.get()).isEqualTo(2);
    }

    // </editor-fold>


    // <editor-fold desc="then (functional)">

    @Test
    public void testThen0() throws X  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);


        //given (+ some assertions)
        LBiDoublePredicateX<X> sutO = (double d1,double d2) -> {
                mainFunctionCalled.set(true);
                assertThat(d1).isEqualTo((double)80);
                assertThat(d2).isEqualTo((double)81);
                return true;
        };

        LBooleanFunctionX<Integer ,X> thenFunction = p -> {
                thenFunctionCalled.set(true);
                // 
                assertThat(p).isEqualTo(true);
                // V
                return Integer.valueOf(100);
        };

        //when
        LBiDoubleFunctionX<Integer ,X> function = sutO.boolToBiDoubleFunction(thenFunction);
        Integer  finalValue = function.doApply((double)80,(double)81);

        //then - finals
        assertThat(finalValue).isEqualTo(Integer.valueOf(100));
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    // </editor-fold>

    @Test
    public void testNesting() {
        assertThat(sut.nestingBiDoublePred())
            .isInstanceOf(LBiDoublePredicate.class);
    }

    @Test
    public void testShoving() {
        assertThat(sut.shovingBiDoublePred())
            .isInstanceOf(LBiDoublePredicate.class);
    }

    @Test
    public void testNestingX() {
        assertThat(sut.nestingBiDoublePredX())
            .isInstanceOf(LBiDoublePredicateX.class);
    }

    @Test
    public void testShovingX() {
        assertThat(sut.shovingBiDoublePredX())
            .isInstanceOf(LBiDoublePredicateX.class);
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void testShove() {

        // given
        LBiDoublePredicateX<X> sutThrowing = LBiDoublePredicateX.lX((double d1,double d2) -> {
            throw new UnsupportedOperationException();
        });

        // when
        sutThrowing.shovingBiDoublePred().doTest((double)100,(double)100);
    }

    @Test
    public void testHandleBiDoublePred() throws X {

        // given
        LBiDoublePredicateX<X> sutThrowing = LBiDoublePredicateX.lX((double d1,double d2) -> {
            throw new UnsupportedOperationException();
        });

        // when
        LBiDoublePredicateX<X> wrapped = sutThrowing.handleBiDoublePredX(h -> {
            h.wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED);
        });

        // then
        try {
            wrapped.doTest((double)100,(double)100);
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
                .contains("LBiDoublePredicateX: boolean doTest(double d1,double d2) throws X");
    }


    @Test
    public void isThrowing() {
        assertThat(sut.isThrowing())
            .isTrue();
    }


}


