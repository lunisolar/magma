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
import java.util.concurrent.atomic.AtomicInteger; //NOSONAR
import static org.assertj.core.api.Assertions.*; //NOSONAR

/** The test obviously concentrate on the interface methods the function it self is very simple.  */
public class LBiLongPredicateXTest<X extends ParseException> {
    private static final String ORIGINAL_MESSAGE = "Original message";
    private static final String EXCEPTION_WAS_WRAPPED = "Exception was wrapped.";
    private static final String NO_EXCEPTION_WERE_THROWN = "No exception were thrown.";

    private boolean testValue = true;



    private LBiLongPredicateX<X> sut = new LBiLongPredicateX(){
        public  boolean doTest(long l1,long l2) throws ParseException {
            return testValue;
        }
    };

    private LBiLongPredicate opposite = new LBiLongPredicate(){
        public  boolean doTest(long l1,long l2)  {
            return testValue;
        }
    };



    private LBiLongPredicateX<ParseException> sutAlwaysThrowing = LBiLongPredicateX.lX((long l1,long l2) -> {
            throw new ParseException(ORIGINAL_MESSAGE, 0);
    });

    private LBiLongPredicateX<RuntimeException> sutAlwaysThrowingUnckeck = LBiLongPredicateX.lX((long l1,long l2) -> {
            throw new IndexOutOfBoundsException(ORIGINAL_MESSAGE);
    });


    @Test
    public void testTheResult() throws ParseException {
        assertThat(sut.doTest((long)100,(long)100))
            .isEqualTo(testValue);
    }

    @Test
    public void testNonNullDoTest() throws ParseException {
        assertThat(sut.nonNullDoTest((long)100,(long)100))
            .isEqualTo(testValue);
    }

    @Test
    public void testNestingDoTest_checked() throws ParseException {

        // then
        try {
            sutAlwaysThrowing.nestingDoTest((long)100,(long)100);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(NestedException.class)
                    .hasCauseExactlyInstanceOf(ParseException.class)
                    .hasMessage(ORIGINAL_MESSAGE);
        }
    }

    @Test
    public void testNestingDoTest_unckeck() throws ParseException {

        // then
        try {
            sutAlwaysThrowingUnckeck.nestingDoTest((long)100,(long)100);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                    .hasNoCause()
                    .hasMessage(ORIGINAL_MESSAGE);
        }
    }

    @Test
    public void testShovingDoTest_checked() throws ParseException {

        // then
        try {
            sutAlwaysThrowing.shovingDoTest((long)100,(long)100);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(ParseException.class)
                    .hasNoCause()
                    .hasMessage(ORIGINAL_MESSAGE);
        }
    }

    @Test
    public void testShovingDoTest_unckeck() throws ParseException {

        // then
        try {
            sutAlwaysThrowingUnckeck.shovingDoTest((long)100,(long)100);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                    .hasNoCause()
                    .hasMessage(ORIGINAL_MESSAGE);
        }
    }


    @Test
    public void testApplyAsBooleanShouldNotModifyValue() throws ParseException {
        assertThat(sut.doApplyAsBoolean((long)100,(long)100))
            .isEqualTo(testValue);

    }


    @Test
    public void testFunctionalInterfaceDescription() throws ParseException {
        assertThat(sut.functionalInterfaceDescription())
            .isEqualTo("LBiLongPredicateX: boolean doTest(long l1,long l2) throws X");
    }

    @Test
    public void testLXMethod() throws ParseException {
        assertThat(LBiLongPredicateX.lX((long l1,long l2) -> testValue ))
            .isInstanceOf(LBiLongPredicateX.class);
    }

    @Test
    public void testWrapXMethod() throws ParseException {
        assertThat(LBiLongPredicateX.wrapX(opposite))
            .isInstanceOf(LBiLongPredicateX.class);
    }


    @Test
    public void testWrapExceptionMethodWrapsTheException() throws ParseException {

        // given
        LBiLongPredicateX<X> sutThrowing = LBiLongPredicateX.lX((long l1,long l2) -> {
            throw new UnsupportedOperationException();
        });

        // when
        LBiLongPredicateX<X> wrapped = LBiLongPredicateX.wrapException(sutThrowing, UnsupportedOperationException.class, null, t -> {
            throw new IllegalArgumentException(EXCEPTION_WAS_WRAPPED, t);
        });

        // then
        try {
            wrapped.doTest((long)100,(long)100);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IllegalArgumentException.class)
                    .hasCauseExactlyInstanceOf(UnsupportedOperationException.class)
                    .hasMessage(EXCEPTION_WAS_WRAPPED);
        }
    }

    @Test
    public void testWrapExceptionMethodDoNotWrapsOtherException() throws ParseException {

        // given
        LBiLongPredicateX<X> sutThrowing = LBiLongPredicateX.lX((long l1,long l2) -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LBiLongPredicateX<X> wrapped = LBiLongPredicateX.wrapException(sutThrowing, UnsupportedOperationException.class, null, t -> {
            throw new IllegalArgumentException(EXCEPTION_WAS_WRAPPED, t);
        });

        // then
        try {
            wrapped.doTest((long)100,(long)100);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                    .hasNoCause();
        }
    }

    @Test
    public void testWrapExceptionMisshandlingExceptionIsDetected() throws ParseException {

        // given
        LBiLongPredicateX<X> sutThrowing = LBiLongPredicateX.lX((long l1,long l2) -> {
            throw new UnsupportedOperationException();
        });

        // when
        LBiLongPredicateX<X> wrapped = LBiLongPredicateX.wrapException(sutThrowing, UnsupportedOperationException.class, null, t -> {
            return null;
        });

        // then
        try {
            wrapped.doTest((long)100,(long)100);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(ExceptionNotHandled.class)
                    .hasCauseExactlyInstanceOf(UnsupportedOperationException.class)
                    .hasMessage("Handler has not processed the exception.");
        }
    }

    @Test
    public void testNegate() throws ParseException {
        assertThat(sut.negate().doTest((long)100,(long)100))
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
    public void testAndOrXor(final boolean f1Result, final boolean f2Result, final boolean andResult, final boolean orResult, final boolean xorResult) throws ParseException {

        //given
        LBiLongPredicateX<X> fun1 = LBiLongPredicateX.lX((long l1,long l2) -> f1Result);
        LBiLongPredicateX<X> fun2 = LBiLongPredicateX.lX((long l1,long l2) -> f2Result);

        //when
        LBiLongPredicateX<X> andFunction = fun1.and(fun2);
        LBiLongPredicateX<X> orFunction = fun1.or(fun2);
        LBiLongPredicateX<X> xorFunction = fun1.xor(fun2);

        //then
        assertThat(andFunction.doTest((long)100,(long)100))
                .isEqualTo(andResult);

        assertThat(orFunction.doTest((long)100,(long)100))
                .isEqualTo(orResult);

        assertThat(xorFunction.doTest((long)100,(long)100))
                .isEqualTo(xorResult);
    }

    @Test
    public void isEqual() throws ParseException  {
        //when
        LBiLongPredicateX<X> equals = LBiLongPredicateX.isEqual((long)100,(long)100);

        //then
        assertThat(equals.doTest((long)100,(long)100))
                .isTrue();

        assertThat(equals.doTest((long)0,(long)0))
                .isFalse();
    }



    // <editor-fold desc="compose (functional)">

    @Test
    public void testfromLong() throws ParseException {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LBiLongPredicateX<X> sutO = (long l1,long l2) -> {
                mainFunctionCalled.set(true);
                assertThat(l1).isEqualTo((long)90);
                assertThat(l2).isEqualTo((long)91);
                return true;
        };

        LLongUnaryOperatorX<X> before1 = p0 -> {
            assertThat(p0).isEqualTo((long)80);
            beforeCalls.incrementAndGet();
            return (long)90;
        };
        LLongUnaryOperatorX<X> before2 = p1 -> {
            assertThat(p1).isEqualTo((long)81);
            beforeCalls.incrementAndGet();
            return (long)91;
        };

        //when
        LBiLongPredicateX<X> function = sutO.fromLong(before1,before2);
        function.doTest((long)80,(long)81);

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(beforeCalls.get()).isEqualTo(2);
    }


    @Test
    public void testfrom() throws ParseException {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LBiLongPredicateX<X> sutO = (long l1,long l2) -> {
                mainFunctionCalled.set(true);
                assertThat(l1).isEqualTo((long)90);
                assertThat(l2).isEqualTo((long)91);
                return true;
        };

        LToLongFunctionX<Integer ,X> before1 = p0 -> {
            assertThat(p0).isEqualTo(Integer.valueOf(80));
            beforeCalls.incrementAndGet();
            return (long)90;
        };
        LToLongFunctionX<Integer ,X> before2 = p1 -> {
            assertThat(p1).isEqualTo(Integer.valueOf(81));
            beforeCalls.incrementAndGet();
            return (long)91;
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
    public void testThen0() throws ParseException  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);


        //given (+ some assertions)
        LBiLongPredicateX<X> sutO = (long l1,long l2) -> {
                mainFunctionCalled.set(true);
                assertThat(l1).isEqualTo((long)80);
                assertThat(l2).isEqualTo((long)81);
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
        LLongBiFunctionX<Integer ,X> function = sutO.then(thenFunction);
        Integer  finalValue = function.doApply((long)80,(long)81);

        //then - finals
        assertThat(finalValue).isEqualTo(Integer.valueOf(100));
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    // </editor-fold>
//
    @Test
    public void testNesting() {
        assertThat(sut.nest())
            .isInstanceOf(LBiLongPredicate.class);
    }

    @Test
    public void testShoving() {
        assertThat(sut.shove())
            .isInstanceOf(LBiLongPredicate.class);
    }

    @Test
    public void testNestingX() {
        assertThat(sut.nestX())
            .isInstanceOf(LBiLongPredicateX.class);
    }

    @Test
    public void testShovingX() {
        assertThat(sut.shoveX())
            .isInstanceOf(LBiLongPredicateX.class);
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void testShove() {

        // given
        LBiLongPredicateX<X> sutThrowing = LBiLongPredicateX.lX((long l1,long l2) -> {
            throw new UnsupportedOperationException();
        });

        // when
        sutThrowing.shove().doTest((long)100,(long)100);
    }

    @Test
    public void testHandleX() throws ParseException {

        // given
        LBiLongPredicateX<X> sutThrowing = LBiLongPredicateX.lX((long l1,long l2) -> {
            throw new UnsupportedOperationException();
        });

        // when
        LBiLongPredicateX<X> wrapped = sutThrowing.handleX(UnsupportedOperationException.class, t -> {
            throw new IllegalArgumentException(EXCEPTION_WAS_WRAPPED, t);
        });

        // then
        try {
            wrapped.doTest((long)100,(long)100);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IllegalArgumentException.class)
                    .hasCauseExactlyInstanceOf(UnsupportedOperationException.class)
                    .hasMessage(EXCEPTION_WAS_WRAPPED);
        }
    }

    @Test
    public void testToString() throws ParseException {

        assertThat(sut.toString())
                .isInstanceOf(String.class)
                .startsWith(this.getClass().getName()+"$");

        assertThat(String.format("%s", sut))
                .isInstanceOf(String.class)
                .contains("LBiLongPredicateX: boolean doTest(long l1,long l2) throws X");
    }


    @Test
    public void isThrowing() {
        assertThat(sut.isThrowing())
            .isTrue();
    }


}
