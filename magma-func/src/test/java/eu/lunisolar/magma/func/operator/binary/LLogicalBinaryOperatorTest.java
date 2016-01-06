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
public class LLogicalBinaryOperatorTest<X extends ParseException> {
    private static final String ORIGINAL_MESSAGE = "Original message";
    private static final String EXCEPTION_WAS_WRAPPED = "Exception was wrapped.";
    private static final String NO_EXCEPTION_WERE_THROWN = "No exception were thrown.";

    private boolean testValue = true;



    private LLogicalBinaryOperator sut = new LLogicalBinaryOperator(){
        public  boolean doApply(boolean a1,boolean a2)  {
            return testValue;
        }
    };

    private LLogicalBinaryOperatorX<X> opposite = new LLogicalBinaryOperatorX(){
        public  boolean doApply(boolean a1,boolean a2) throws ParseException {
            return testValue;
        }
    };




    private LLogicalBinaryOperator sutAlwaysThrowingUnckeck = LLogicalBinaryOperator.l((boolean a1,boolean a2) -> {
            throw new IndexOutOfBoundsException(ORIGINAL_MESSAGE);
    });


    @Test
    public void testTheResult() throws X {
        assertThat(sut.doApply(true,true))
            .isEqualTo(testValue);
    }

    @Test
    public void testTupleCall() throws X {

        LBoolPair domainObject = Tuple4U.tuple(true,true);

        Object result = sut.tupleApply(domainObject);

        assertThat(result)
            .isEqualTo(testValue);
    }

    @Test
    public void testNonNullDoApply() throws X {
        assertThat(sut.nonNullDoApply(true,true))
            .isEqualTo(testValue);
    }

    @Test
    public void testNestingDoApplyUnckeck() throws X {

        // then
        try {
            sutAlwaysThrowingUnckeck.nestingDoApply(true,true);
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
            sutAlwaysThrowingUnckeck.shovingDoApply(true,true);
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
            .isEqualTo("LLogicalBinaryOperator: boolean doApply(boolean a1,boolean a2)");
    }

    @Test
    public void testLMethod() throws X {
        assertThat(LLogicalBinaryOperator.l((boolean a1,boolean a2) -> testValue ))
            .isInstanceOf(LLogicalBinaryOperator.class);
    }

    @Test
    public void testWrapMethod() throws X {
        assertThat(LLogicalBinaryOperator.wrap(opposite))
            .isInstanceOf(LLogicalBinaryOperator.class);
    }

    @Test
    public void testWrapMethodDoNotWrapsRuntimeException() throws X {
        // given
        LLogicalBinaryOperatorX<X> sutThrowing = LLogicalBinaryOperatorX.lX((boolean a1,boolean a2) -> {
            throw new UnsupportedOperationException(ORIGINAL_MESSAGE);
        });

        // when
        LLogicalBinaryOperator wrapped = LLogicalBinaryOperator.wrap(sutThrowing);

        // then
        try {
            wrapped.doApply(true,true);
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
        LLogicalBinaryOperatorX<ParseException> sutThrowing = LLogicalBinaryOperatorX.lX((boolean a1,boolean a2) -> {
            throw new ParseException(ORIGINAL_MESSAGE, 0);
        });

        // when
        LLogicalBinaryOperator wrapped = LLogicalBinaryOperator.wrap(sutThrowing);

        // then
        try {
            wrapped.doApply(true,true);
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
        LLogicalBinaryOperator sutThrowing = LLogicalBinaryOperator.l((boolean a1,boolean a2) -> {
            throw new UnsupportedOperationException();
        });

        // when
        LLogicalBinaryOperator wrapped = sutThrowing.handleLogicalBinaryOp(handler -> handler
            .wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED));

        // then
        try {
            wrapped.doApply(true,true);
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
        LLogicalBinaryOperator sutThrowing = LLogicalBinaryOperator.l((boolean a1,boolean a2) -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LLogicalBinaryOperator wrapped = sutThrowing.handleLogicalBinaryOp(handler -> handler
                .wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED)
                .throwIf(IndexOutOfBoundsException.class));

        // then
        try {
            wrapped.doApply(true,true);
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
        LLogicalBinaryOperator sutThrowing = LLogicalBinaryOperator.l((boolean a1,boolean a2) -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LLogicalBinaryOperator wrapped = sutThrowing.handleLogicalBinaryOp(handler -> handler
                .wrapWhen(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED)
                .throwIf(IndexOutOfBoundsException.class));

        // then
        try {
            wrapped.doApply(true,true);
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
        LLogicalBinaryOperator sutThrowing = LLogicalBinaryOperator.l((boolean a1,boolean a2) -> {
            throw new UnsupportedOperationException(ORIGINAL_MESSAGE);
        });

        // when
        LLogicalBinaryOperator wrapped = sutThrowing.handleLogicalBinaryOp(h -> Function4U.doNothing());

        // then
        try {
            wrapped.doApply(true,true);
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
        assertThat(sut.negate().doApply(true,true))
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
        LLogicalBinaryOperator fun1 = LLogicalBinaryOperator.l((boolean a1,boolean a2) -> f1Result);
        LLogicalBinaryOperator fun2 = LLogicalBinaryOperator.l((boolean a1,boolean a2) -> f2Result);

        //when
        LLogicalBinaryOperator andFunction = fun1.and(fun2);
        LLogicalBinaryOperator orFunction = fun1.or(fun2);
        LLogicalBinaryOperator xorFunction = fun1.xor(fun2);

        //then
        assertThat(andFunction.doApply(true,true))
                .isEqualTo(andResult);

        assertThat(orFunction.doApply(true,true))
                .isEqualTo(orResult);

        assertThat(xorFunction.doApply(true,true))
                .isEqualTo(xorResult);
    }

    @Test
    public void isEqual() throws X  {
        //when
        LLogicalBinaryOperator equals = LLogicalBinaryOperator.isEqual(true,true);

        //then
        assertThat(equals.doApply(true,true))
                .isTrue();

        assertThat(equals.doApply(false,false))
                .isFalse();
    }


    @Test(dataProvider="boolean permutations")
    public void testOperatorAndOrXor(final boolean value1, final boolean value2, final boolean andResult, final boolean orResult, final boolean xorResult) throws X {
        //given
        LLogicalBinaryOperator andFunction = LLogicalBinaryOperator.and();
        LLogicalBinaryOperator orFunction = LLogicalBinaryOperator.or();
        LLogicalBinaryOperator xorFunction = LLogicalBinaryOperator.xor();

        //then
        assertThat(andFunction.doApply(value1, value2))
                .isEqualTo(andResult);

        assertThat(orFunction.doApply(value1, value2))
                .isEqualTo(orResult);

        assertThat(xorFunction.doApply(value1, value2))
                .isEqualTo(xorResult);
    }


    // <editor-fold desc="compose (functional)">

    @Test
    public void testlogicalBinaryOpComposeBoolean() throws X {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LLogicalBinaryOperator sutO = (boolean a1,boolean a2) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(true);
                assertThat(a2).isEqualTo(true);
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

        //when
        LLogicalBinaryOperator function = sutO.logicalBinaryOpComposeBoolean(before1,before2);
        function.doApply(true,true);

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(beforeCalls.get()).isEqualTo(2);
    }


    @Test
    public void testlogicalBinaryOpCompose() throws X {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LLogicalBinaryOperator sutO = (boolean a1,boolean a2) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(true);
                assertThat(a2).isEqualTo(true);
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

        //when
        LBiPredicate<Integer ,Integer > function = sutO.logicalBinaryOpCompose(before1,before2);
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
        LLogicalBinaryOperator sutO = (boolean a1,boolean a2) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(true);
                assertThat(a2).isEqualTo(true);
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
        LBiBoolFunction<Integer > function = sutO.then(thenFunction);
        Integer  finalValue = function.doApply(true,true);

        //then - finals
        assertThat(finalValue).isEqualTo(Integer.valueOf(100));
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    // </editor-fold>

    @Test
    public void testNesting() {
        assertThat(sut.nestingLogicalBinaryOp())
            .isSameAs(sut)
            .isInstanceOf(LLogicalBinaryOperator.class);
    }

    @Test
    public void testShoving() {
        assertThat(sut.shovingLogicalBinaryOp())
            .isSameAs(sut)
            .isInstanceOf(LLogicalBinaryOperator.class);
    }

    @Test
    public void testNestingX() {
        assertThat(sut.nestingLogicalBinaryOpX())
            .isSameAs(sut)
            .isInstanceOf(LLogicalBinaryOperatorX.class);
    }

    @Test
    public void testShovingX() {
        assertThat(sut.shovingLogicalBinaryOpX())
            .isSameAs(sut)
            .isInstanceOf(LLogicalBinaryOperatorX.class);
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void testShove() {

        // given
        LLogicalBinaryOperator sutThrowing = LLogicalBinaryOperator.l((boolean a1,boolean a2) -> {
            throw new UnsupportedOperationException();
        });

        // when
        sutThrowing.shovingLogicalBinaryOp().doApply(true,true);
    }

    @Test
    public void testHandleLogicalBinaryOp() throws X {

        // given
        LLogicalBinaryOperator sutThrowing = LLogicalBinaryOperator.l((boolean a1,boolean a2) -> {
            throw new UnsupportedOperationException();
        });

        // when
        LLogicalBinaryOperator wrapped = sutThrowing.handleLogicalBinaryOp(h -> {
            h.wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED);
        });

        // then
        try {
            wrapped.doApply(true,true);
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
                .contains("LLogicalBinaryOperator: boolean doApply(boolean a1,boolean a2)");
    }


    @Test
    public void isThrowing() {
        assertThat(sut.isThrowing())
            .isFalse();
    }

    @Test void safeCompiles() {
        LLogicalBinaryOperator r1 = LLogicalBinaryOperator.safe(sut); //NOSONAR
        LLogicalBinaryOperatorX r2 = LLogicalBinaryOperator.safe(sut); //NOSONAR
    }

    @Test void safePropagates() {
        Object result = LLogicalBinaryOperator.safe(sut);
        assertThat(result).isSameAs(sut);
    }

    @Test void safeProtectsAgainstNpe() {
        Object result = LLogicalBinaryOperator.safe(null);
        assertThat(result).isSameAs(LLogicalBinaryOperator.l(LLogicalBinaryOperator.safe()));
    }

    @Test  void safeSupplierPropagates() {
        LSupplier<LLogicalBinaryOperator> supplier = ()->sut;
        Object result = LLogicalBinaryOperator.safeSupplier(supplier);
        assertThat(result).isSameAs(supplier);
    }

    @Test  void safeSupplierProtectsAgainstNpe() {
        Object result = LLogicalBinaryOperator.safeSupplier(null);
        assertThat(result).isSameAs(LLogicalBinaryOperator.safeSupplier());
    }

    @Test  void safeSupplierCompiles() {
        LSupplier<LLogicalBinaryOperator> r1 = LLogicalBinaryOperator.safeSupplier(()->sut);  //NOSONAR
    }

}
