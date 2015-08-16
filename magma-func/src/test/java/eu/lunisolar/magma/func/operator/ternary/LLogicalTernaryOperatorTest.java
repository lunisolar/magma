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
public class LLogicalTernaryOperatorTest<X extends ParseException> {
    private static final String ORIGINAL_MESSAGE = "Original message";
    private static final String EXCEPTION_WAS_WRAPPED = "Exception was wrapped.";
    private static final String NO_EXCEPTION_WERE_THROWN = "No exception were thrown.";

    private boolean testValue = true;



    private LLogicalTernaryOperator sut = new LLogicalTernaryOperator(){
        public  boolean doApply(boolean b1,boolean b2,boolean b3)  {
            return testValue;
        }
    };

    private LLogicalTernaryOperatorX<X> opposite = new LLogicalTernaryOperatorX(){
        public  boolean doApply(boolean b1,boolean b2,boolean b3) throws ParseException {
            return testValue;
        }
    };




    private LLogicalTernaryOperator sutAlwaysThrowingUnckeck = LLogicalTernaryOperator.l((boolean b1,boolean b2,boolean b3) -> {
            throw new IndexOutOfBoundsException(ORIGINAL_MESSAGE);
    });


    @Test
    public void testTheResult() throws X {
        assertThat(sut.doApply(true,true,true))
            .isEqualTo(testValue);
    }

    @Test
    public void testNonNullDoApply() throws X {
        assertThat(sut.nonNullDoApply(true,true,true))
            .isEqualTo(testValue);
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


    @Test
    public void testFunctionalInterfaceDescription() throws X {
        assertThat(sut.functionalInterfaceDescription())
            .isEqualTo("LLogicalTernaryOperator: boolean doApply(boolean b1,boolean b2,boolean b3)");
    }

    @Test
    public void testLMethod() throws X {
        assertThat(LLogicalTernaryOperator.l((boolean b1,boolean b2,boolean b3) -> testValue ))
            .isInstanceOf(LLogicalTernaryOperator.class);
    }

    @Test
    public void testWrapMethod() throws X {
        assertThat(LLogicalTernaryOperator.wrap(opposite))
            .isInstanceOf(LLogicalTernaryOperator.class);
    }

    @Test
    public void testWrapMethodDoNotWrapsRuntimeException() throws X {
        // given
        LLogicalTernaryOperatorX<X> sutThrowing = LLogicalTernaryOperatorX.lX((boolean b1,boolean b2,boolean b3) -> {
            throw new UnsupportedOperationException(ORIGINAL_MESSAGE);
        });

        // when
        LLogicalTernaryOperator wrapped = LLogicalTernaryOperator.wrap(sutThrowing);

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
        LLogicalTernaryOperatorX<ParseException> sutThrowing = LLogicalTernaryOperatorX.lX((boolean b1,boolean b2,boolean b3) -> {
            throw new ParseException(ORIGINAL_MESSAGE, 0);
        });

        // when
        LLogicalTernaryOperator wrapped = LLogicalTernaryOperator.wrap(sutThrowing);

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
        LLogicalTernaryOperator sutThrowing = LLogicalTernaryOperator.l((boolean b1,boolean b2,boolean b3) -> {
            throw new UnsupportedOperationException();
        });

        // when
        LLogicalTernaryOperator wrapped = sutThrowing.handleLogicalTernaryOp(handler -> handler
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
        LLogicalTernaryOperator sutThrowing = LLogicalTernaryOperator.l((boolean b1,boolean b2,boolean b3) -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LLogicalTernaryOperator wrapped = sutThrowing.handleLogicalTernaryOp(handler -> handler
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
        LLogicalTernaryOperator sutThrowing = LLogicalTernaryOperator.l((boolean b1,boolean b2,boolean b3) -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LLogicalTernaryOperator wrapped = sutThrowing.handleLogicalTernaryOp(handler -> handler
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
        LLogicalTernaryOperator sutThrowing = LLogicalTernaryOperator.l((boolean b1,boolean b2,boolean b3) -> {
            throw new UnsupportedOperationException(ORIGINAL_MESSAGE);
        });

        // when
        LLogicalTernaryOperator wrapped = sutThrowing.handleLogicalTernaryOp(h -> Function4U.doNothing());

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
    public void testnegate() throws X {
        assertThat(sut.negate().doApply(true,true,true))
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
        LLogicalTernaryOperator fun1 = LLogicalTernaryOperator.l((boolean b1,boolean b2,boolean b3) -> f1Result);
        LLogicalTernaryOperator fun2 = LLogicalTernaryOperator.l((boolean b1,boolean b2,boolean b3) -> f2Result);

        //when
        LLogicalTernaryOperator andFunction = fun1.and(fun2);
        LLogicalTernaryOperator orFunction = fun1.or(fun2);
        LLogicalTernaryOperator xorFunction = fun1.xor(fun2);

        //then
        assertThat(andFunction.doApply(true,true,true))
                .isEqualTo(andResult);

        assertThat(orFunction.doApply(true,true,true))
                .isEqualTo(orResult);

        assertThat(xorFunction.doApply(true,true,true))
                .isEqualTo(xorResult);
    }

    @Test
    public void isEqual() throws X  {
        //when
        LLogicalTernaryOperator equals = LLogicalTernaryOperator.isEqual(true,true,true);

        //then
        assertThat(equals.doApply(true,true,true))
                .isTrue();

        assertThat(equals.doApply(false,false,false))
                .isFalse();
    }



    // <editor-fold desc="compose (functional)">

    @Test
    public void testlogicalTernaryOpComposeBoolean() throws X {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LLogicalTernaryOperator sutO = (boolean b1,boolean b2,boolean b3) -> {
                mainFunctionCalled.set(true);
                assertThat(b1).isEqualTo(true);
                assertThat(b2).isEqualTo(true);
                assertThat(b3).isEqualTo(true);
                return true;
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
        LLogicalTernaryOperator function = sutO.logicalTernaryOpComposeBoolean(before1,before2,before3);
        function.doApply(true,true,true);

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(beforeCalls.get()).isEqualTo(3);
    }


    @Test
    public void testlogicalTernaryOpCompose() throws X {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LLogicalTernaryOperator sutO = (boolean b1,boolean b2,boolean b3) -> {
                mainFunctionCalled.set(true);
                assertThat(b1).isEqualTo(true);
                assertThat(b2).isEqualTo(true);
                assertThat(b3).isEqualTo(true);
                return true;
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
        LTriPredicate<Integer ,Integer ,Integer > function = sutO.logicalTernaryOpCompose(before1,before2,before3);
        function.doTest((Integer )Integer.valueOf(80),(Integer )Integer.valueOf(81),(Integer )Integer.valueOf(82));

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
        LLogicalTernaryOperator sutO = (boolean b1,boolean b2,boolean b3) -> {
                mainFunctionCalled.set(true);
                assertThat(b1).isEqualTo(true);
                assertThat(b2).isEqualTo(true);
                assertThat(b3).isEqualTo(true);
                return true;
        };

        LBoolFunction<Integer > thenFunction = p -> {
                thenFunctionCalled.set(true);
                // 
                assertThat(p).isEqualTo(true);
                // V
                return Integer.valueOf(100);
        };

        //when
        LTriBoolFunction<Integer > function = sutO.then(thenFunction);
        Integer  finalValue = function.doApply(true,true,true);

        //then - finals
        assertThat(finalValue).isEqualTo(Integer.valueOf(100));
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    // </editor-fold>

    @Test
    public void testNesting() {
        assertThat(sut.nestingLogicalTernaryOp())
            .isSameAs(sut)
            .isInstanceOf(LLogicalTernaryOperator.class);
    }

    @Test
    public void testShoving() {
        assertThat(sut.shovingLogicalTernaryOp())
            .isSameAs(sut)
            .isInstanceOf(LLogicalTernaryOperator.class);
    }

    @Test
    public void testNestingX() {
        assertThat(sut.nestingLogicalTernaryOpX())
            .isSameAs(sut)
            .isInstanceOf(LLogicalTernaryOperatorX.class);
    }

    @Test
    public void testShovingX() {
        assertThat(sut.shovingLogicalTernaryOpX())
            .isSameAs(sut)
            .isInstanceOf(LLogicalTernaryOperatorX.class);
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void testShove() {

        // given
        LLogicalTernaryOperator sutThrowing = LLogicalTernaryOperator.l((boolean b1,boolean b2,boolean b3) -> {
            throw new UnsupportedOperationException();
        });

        // when
        sutThrowing.shovingLogicalTernaryOp().doApply(true,true,true);
    }

    @Test
    public void testHandleLogicalTernaryOp() throws X {

        // given
        LLogicalTernaryOperator sutThrowing = LLogicalTernaryOperator.l((boolean b1,boolean b2,boolean b3) -> {
            throw new UnsupportedOperationException();
        });

        // when
        LLogicalTernaryOperator wrapped = sutThrowing.handleLogicalTernaryOp(h -> {
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
                .contains("LLogicalTernaryOperator: boolean doApply(boolean b1,boolean b2,boolean b3)");
    }


    @Test
    public void isThrowing() {
        assertThat(sut.isThrowing())
            .isFalse();
    }


}

