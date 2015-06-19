/*
 * (C) Copyright 2015 Lunisolar (http://lunisolar.eu/).
 *
 * This file is part of "lunisolar-magma".
 *
 * "lunisolar-magma" is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * "lunisolar-magma" is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with "lunisolar-magma".  If not, see <http://www.gnu.org/licenses/>.
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
public class LBooleanBinaryOperatorXTest<X extends ParseException> {
    private static final String ORIGINAL_MESSAGE = "Original message";
    private static final String EXCEPTION_WAS_WRAPPED = "Exception was wrapped.";
    private static final String NO_EXCEPTION_WERE_THROWN = "No exception were thrown.";

    private boolean testValue = true;



    private LBooleanBinaryOperatorX<X> sut = new LBooleanBinaryOperatorX(){
        public  boolean doApplyAsBoolean(boolean b1,boolean b2) throws ParseException {
            return testValue;
        }
    };

    private LBooleanBinaryOperator opposite = new LBooleanBinaryOperator(){
        public  boolean doApplyAsBoolean(boolean b1,boolean b2)  {
            return testValue;
        }
    };



    private LBooleanBinaryOperatorX<ParseException> sutAlwaysThrowing = LBooleanBinaryOperatorX.lX((boolean b1,boolean b2) -> {
            throw new ParseException(ORIGINAL_MESSAGE, 0);
    });

    private LBooleanBinaryOperatorX<RuntimeException> sutAlwaysThrowingUnckeck = LBooleanBinaryOperatorX.lX((boolean b1,boolean b2) -> {
            throw new IndexOutOfBoundsException(ORIGINAL_MESSAGE);
    });


    @Test
    public void testTheResult() throws X {
        assertThat(sut.doApplyAsBoolean(true,true))
            .isEqualTo(testValue);
    }

    @Test
    public void testNonNullDoApplyAsBoolean() throws X {
        assertThat(sut.nonNullDoApplyAsBoolean(true,true))
            .isEqualTo(testValue);
    }

    @Test
    public void testNestingDoApplyAsBoolean_checked() throws X {

        // then
        try {
            sutAlwaysThrowing.nestingDoApplyAsBoolean(true,true);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(NestedException.class)
                    .hasCauseExactlyInstanceOf(ParseException.class)
                    .hasMessage(ORIGINAL_MESSAGE);
        }
    }

    @Test
    public void testNestingDoApplyAsBoolean_unckeck() throws X {

        // then
        try {
            sutAlwaysThrowingUnckeck.nestingDoApplyAsBoolean(true,true);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                    .hasNoCause()
                    .hasMessage(ORIGINAL_MESSAGE);
        }
    }

    @Test
    public void testShovingDoApplyAsBoolean_checked() throws X {

        // then
        try {
            sutAlwaysThrowing.shovingDoApplyAsBoolean(true,true);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(ParseException.class)
                    .hasNoCause()
                    .hasMessage(ORIGINAL_MESSAGE);
        }
    }

    @Test
    public void testShovingDoApplyAsBoolean_unckeck() throws X {

        // then
        try {
            sutAlwaysThrowingUnckeck.shovingDoApplyAsBoolean(true,true);
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
            .isEqualTo("LBooleanBinaryOperatorX: boolean doApplyAsBoolean(boolean b1,boolean b2) throws X");
    }

    @Test
    public void testLXMethod() throws X {
        assertThat(LBooleanBinaryOperatorX.lX((boolean b1,boolean b2) -> testValue ))
            .isInstanceOf(LBooleanBinaryOperatorX.class);
    }

    @Test
    public void testWrapXMethod() throws X {
        assertThat(LBooleanBinaryOperatorX.wrapX(opposite))
            .isInstanceOf(LBooleanBinaryOperatorX.class);
    }


    @Test
    public void testWrapExceptionMethodWrapsTheException() throws X {

        // given
        LBooleanBinaryOperatorX<X> sutThrowing = LBooleanBinaryOperatorX.lX((boolean b1,boolean b2) -> {
            throw new UnsupportedOperationException();
        });

        // when
        LBooleanBinaryOperatorX<X> wrapped = sutThrowing.handleX(h -> {
            h.wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED);
        });

        // then
        try {
            wrapped.doApplyAsBoolean(true,true);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IllegalArgumentException.class)
                    .hasCauseExactlyInstanceOf(UnsupportedOperationException.class)
                    .hasMessage(EXCEPTION_WAS_WRAPPED);
        }
    }

    @Test
    public void testWrapExceptionMethodDoNotWrapsOtherException_if() throws X {

        // given
        LBooleanBinaryOperatorX<X> sutThrowing = LBooleanBinaryOperatorX.lX((boolean b1,boolean b2) -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LBooleanBinaryOperatorX<X> wrapped = sutThrowing.handleX(handler -> handler
                .wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED)
                .throwIf(IndexOutOfBoundsException.class));

        // then
        try {
            wrapped.doApplyAsBoolean(true,true);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                    .hasNoCause();
        }
    }

@Test
    public void testWrapExceptionMethodDoNotWrapsOtherException_when() throws X {

        // given
        LBooleanBinaryOperatorX<X> sutThrowing = LBooleanBinaryOperatorX.lX((boolean b1,boolean b2) -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LBooleanBinaryOperatorX<X> wrapped = sutThrowing.handleX(handler -> handler
                .wrapWhen(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED)
                .throwIf(IndexOutOfBoundsException.class));

        // then
        try {
            wrapped.doApplyAsBoolean(true,true);
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
        LBooleanBinaryOperatorX<X> sutThrowing = LBooleanBinaryOperatorX.lX((boolean b1,boolean b2) -> {
            throw (X) new ParseException(ORIGINAL_MESSAGE, 0);
        });

        // when
        LBooleanBinaryOperatorX<X> wrapped = sutThrowing.handleX(h -> {
        });

        // then
        try {
            wrapped.doApplyAsBoolean(true,true);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(NestedException.class)
                    .hasCauseExactlyInstanceOf(ParseException.class)
                    .hasMessage(ORIGINAL_MESSAGE);
        }
    }

    @Test
    public void testNegate() throws X {
        assertThat(sut.negate().doApplyAsBoolean(true,true))
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
        LBooleanBinaryOperatorX<X> fun1 = LBooleanBinaryOperatorX.lX((boolean b1,boolean b2) -> f1Result);
        LBooleanBinaryOperatorX<X> fun2 = LBooleanBinaryOperatorX.lX((boolean b1,boolean b2) -> f2Result);

        //when
        LBooleanBinaryOperatorX<X> andFunction = fun1.and(fun2);
        LBooleanBinaryOperatorX<X> orFunction = fun1.or(fun2);
        LBooleanBinaryOperatorX<X> xorFunction = fun1.xor(fun2);

        //then
        assertThat(andFunction.doApplyAsBoolean(true,true))
                .isEqualTo(andResult);

        assertThat(orFunction.doApplyAsBoolean(true,true))
                .isEqualTo(orResult);

        assertThat(xorFunction.doApplyAsBoolean(true,true))
                .isEqualTo(xorResult);
    }

    @Test
    public void isEqual() throws X  {
        //when
        LBooleanBinaryOperatorX<X> equals = LBooleanBinaryOperatorX.isEqual(true,true);

        //then
        assertThat(equals.doApplyAsBoolean(true,true))
                .isTrue();

        assertThat(equals.doApplyAsBoolean(false,false))
                .isFalse();
    }


    @Test(dataProvider="boolean permutations")
    public void testOperatorAndOrXor(final boolean value1, final boolean value2, final boolean andResult, final boolean orResult, final boolean xorResult) throws X {
        //given
        LBooleanBinaryOperatorX<X> andFunction = LBooleanBinaryOperatorX.and();
        LBooleanBinaryOperatorX<X> orFunction = LBooleanBinaryOperatorX.or();
        LBooleanBinaryOperatorX<X> xorFunction = LBooleanBinaryOperatorX.xor();

        //then
        assertThat(andFunction.doApplyAsBoolean(value1, value2))
                .isEqualTo(andResult);

        assertThat(orFunction.doApplyAsBoolean(value1, value2))
                .isEqualTo(orResult);

        assertThat(xorFunction.doApplyAsBoolean(value1, value2))
                .isEqualTo(xorResult);
    }


    // <editor-fold desc="compose (functional)">

    @Test
    public void testfromBoolean() throws X {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LBooleanBinaryOperatorX<X> sutO = (boolean b1,boolean b2) -> {
                mainFunctionCalled.set(true);
                assertThat(b1).isEqualTo(true);
                assertThat(b2).isEqualTo(true);
                return true;
        };

        LBooleanUnaryOperatorX<X> before1 = p0 -> {
            assertThat(p0).isEqualTo(true);
            beforeCalls.incrementAndGet();
            return true;
        };
        LBooleanUnaryOperatorX<X> before2 = p1 -> {
            assertThat(p1).isEqualTo(true);
            beforeCalls.incrementAndGet();
            return true;
        };

        //when
        LBooleanBinaryOperatorX<X> function = sutO.fromBoolean(before1,before2);
        function.doApplyAsBoolean(true,true);

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(beforeCalls.get()).isEqualTo(2);
    }


    @Test
    public void testfrom() throws X {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LBooleanBinaryOperatorX<X> sutO = (boolean b1,boolean b2) -> {
                mainFunctionCalled.set(true);
                assertThat(b1).isEqualTo(true);
                assertThat(b2).isEqualTo(true);
                return true;
        };

        LPredicateX<Integer ,X> before1 = p0 -> {
            assertThat(p0).isEqualTo(Integer.valueOf(80));
            beforeCalls.incrementAndGet();
            return true;
        };
        LPredicateX<Integer ,X> before2 = p1 -> {
            assertThat(p1).isEqualTo(Integer.valueOf(81));
            beforeCalls.incrementAndGet();
            return true;
        };

        //when
        LBiPredicateX<Integer ,Integer ,X> function = sutO.from(before1,before2);
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
        LBooleanBinaryOperatorX<X> sutO = (boolean b1,boolean b2) -> {
                mainFunctionCalled.set(true);
                assertThat(b1).isEqualTo(true);
                assertThat(b2).isEqualTo(true);
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
        LBooleanBiFunctionX<Integer ,X> function = sutO.then(thenFunction);
        Integer  finalValue = function.doApply(true,true);

        //then - finals
        assertThat(finalValue).isEqualTo(Integer.valueOf(100));
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    // </editor-fold>

    @Test
    public void testNesting() {
        assertThat(sut.nest())
            .isInstanceOf(LBooleanBinaryOperator.class);
    }

    @Test
    public void testShoving() {
        assertThat(sut.shove())
            .isInstanceOf(LBooleanBinaryOperator.class);
    }

    @Test
    public void testNestingX() {
        assertThat(sut.nestX())
            .isInstanceOf(LBooleanBinaryOperatorX.class);
    }

    @Test
    public void testShovingX() {
        assertThat(sut.shoveX())
            .isInstanceOf(LBooleanBinaryOperatorX.class);
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void testShove() {

        // given
        LBooleanBinaryOperatorX<X> sutThrowing = LBooleanBinaryOperatorX.lX((boolean b1,boolean b2) -> {
            throw new UnsupportedOperationException();
        });

        // when
        sutThrowing.shove().doApplyAsBoolean(true,true);
    }

    @Test
    public void testHandle() throws X {

        // given
        LBooleanBinaryOperatorX<X> sutThrowing = LBooleanBinaryOperatorX.lX((boolean b1,boolean b2) -> {
            throw new UnsupportedOperationException();
        });

        // when
        LBooleanBinaryOperatorX<X> wrapped = sutThrowing.handleX(h -> {
            h.wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED);
        });

        // then
        try {
            wrapped.doApplyAsBoolean(true,true);
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
                .contains("LBooleanBinaryOperatorX: boolean doApplyAsBoolean(boolean b1,boolean b2) throws X");
    }


    @Test
    public void isThrowing() {
        assertThat(sut.isThrowing())
            .isTrue();
    }


}
