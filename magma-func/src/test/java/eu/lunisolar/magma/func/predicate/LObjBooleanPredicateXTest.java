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
public class LObjBooleanPredicateXTest<T,X extends ParseException> {
    private static final String ORIGINAL_MESSAGE = "Original message";
    private static final String EXCEPTION_WAS_WRAPPED = "Exception was wrapped.";
    private static final String NO_EXCEPTION_WERE_THROWN = "No exception were thrown.";

    private boolean testValue = true;



    private LObjBooleanPredicateX<T,X> sut = new LObjBooleanPredicateX(){
        public  boolean doTest(Object t, boolean b) throws ParseException {
            return testValue;
        }
    };

    private LObjBooleanPredicate<T> opposite = new LObjBooleanPredicate(){
        public  boolean doTest(Object t, boolean b)  {
            return testValue;
        }
    };



    private LObjBooleanPredicateX<T,ParseException> sutAlwaysThrowing = LObjBooleanPredicateX.lX((T t, boolean b) -> {
            throw new ParseException(ORIGINAL_MESSAGE, 0);
    });

    private LObjBooleanPredicateX<T,RuntimeException> sutAlwaysThrowingUnckeck = LObjBooleanPredicateX.lX((T t, boolean b) -> {
            throw new IndexOutOfBoundsException(ORIGINAL_MESSAGE);
    });


    @Test
    public void testTheResult() throws ParseException {
        assertThat(sut.doTest((T)Integer.valueOf(100),true))
            .isEqualTo(testValue);
    }

    @Test
    public void testNonNullDoTest() throws ParseException {
        assertThat(sut.nonNullDoTest((T)Integer.valueOf(100),true))
            .isEqualTo(testValue);
    }

    @Test
    public void testNestingDoTest_checked() throws ParseException {

        // then
        try {
            sutAlwaysThrowing.nestingDoTest((T)Integer.valueOf(100),true);
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
            sutAlwaysThrowingUnckeck.nestingDoTest((T)Integer.valueOf(100),true);
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
            sutAlwaysThrowing.shovingDoTest((T)Integer.valueOf(100),true);
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
            sutAlwaysThrowingUnckeck.shovingDoTest((T)Integer.valueOf(100),true);
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
        assertThat(sut.doApplyAsBoolean((T)Integer.valueOf(100),true))
            .isEqualTo(testValue);

    }


    @Test
    public void testFunctionalInterfaceDescription() throws ParseException {
        assertThat(sut.functionalInterfaceDescription())
            .isEqualTo("LObjBooleanPredicateX: boolean doTest(T t, boolean b) throws X");
    }

    @Test
    public void testLXMethod() throws ParseException {
        assertThat(LObjBooleanPredicateX.lX((Object t, boolean b) -> testValue ))
            .isInstanceOf(LObjBooleanPredicateX.class);
    }

    @Test
    public void testWrapXMethod() throws ParseException {
        assertThat(LObjBooleanPredicateX.wrapX(opposite))
            .isInstanceOf(LObjBooleanPredicateX.class);
    }


    @Test
    public void testWrapExceptionMethodWrapsTheException() throws ParseException {

        // given
        LObjBooleanPredicateX<T,X> sutThrowing = LObjBooleanPredicateX.lX((T t, boolean b) -> {
            throw new UnsupportedOperationException();
        });

        // when
        LObjBooleanPredicateX<T,X> wrapped = LObjBooleanPredicateX.wrapException(sutThrowing, UnsupportedOperationException.class, null, t -> {
            throw new IllegalArgumentException(EXCEPTION_WAS_WRAPPED, t);
        });

        // then
        try {
            wrapped.doTest((T)Integer.valueOf(100),true);
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
        LObjBooleanPredicateX<T,X> sutThrowing = LObjBooleanPredicateX.lX((T t, boolean b) -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LObjBooleanPredicateX<T,X> wrapped = LObjBooleanPredicateX.wrapException(sutThrowing, UnsupportedOperationException.class, null, t -> {
            throw new IllegalArgumentException(EXCEPTION_WAS_WRAPPED, t);
        });

        // then
        try {
            wrapped.doTest((T)Integer.valueOf(100),true);
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
        LObjBooleanPredicateX<T,X> sutThrowing = LObjBooleanPredicateX.lX((T t, boolean b) -> {
            throw new UnsupportedOperationException();
        });

        // when
        LObjBooleanPredicateX<T,X> wrapped = LObjBooleanPredicateX.wrapException(sutThrowing, UnsupportedOperationException.class, null, t -> {
            return null;
        });

        // then
        try {
            wrapped.doTest((T)Integer.valueOf(100),true);
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
        assertThat(sut.negate().doTest((T)Integer.valueOf(100),true))
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
        LObjBooleanPredicateX<T,X> fun1 = LObjBooleanPredicateX.lX((T t, boolean b) -> f1Result);
        LObjBooleanPredicateX<T,X> fun2 = LObjBooleanPredicateX.lX((T t, boolean b) -> f2Result);

        //when
        LObjBooleanPredicateX<T,X> andFunction = fun1.and(fun2);
        LObjBooleanPredicateX<T,X> orFunction = fun1.or(fun2);
        LObjBooleanPredicateX<T,X> xorFunction = fun1.xor(fun2);

        //then
        assertThat(andFunction.doTest((T)Integer.valueOf(100),true))
                .isEqualTo(andResult);

        assertThat(orFunction.doTest((T)Integer.valueOf(100),true))
                .isEqualTo(orResult);

        assertThat(xorFunction.doTest((T)Integer.valueOf(100),true))
                .isEqualTo(xorResult);
    }

    @Test
    public void isEqual() throws ParseException  {
        //when
        LObjBooleanPredicateX<T,X> equals = LObjBooleanPredicateX.isEqual((T)Integer.valueOf(100),true);

        //then
        assertThat(equals.doTest((T)Integer.valueOf(100),true))
                .isTrue();

        assertThat(equals.doTest((T)Integer.valueOf(0),false))
                .isFalse();
    }



    // <editor-fold desc="compose (functional)">

    @Test
    public void testfromBoolean() throws ParseException {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LObjBooleanPredicateX<Integer ,X> sutO = (Integer t, boolean b) -> {
                mainFunctionCalled.set(true);
                assertThat(t).isEqualTo((T)Integer.valueOf(90));
                assertThat( b).isEqualTo(true);
                return true;
        };

        LFunctionX<Integer ,Integer ,X> before1 = p0 -> {
            assertThat(p0).isEqualTo((T)Integer.valueOf(80));
            beforeCalls.incrementAndGet();
            return 90;
        };
        LBooleanUnaryOperatorX<X> before2 = p1 -> {
            assertThat(p1).isEqualTo(true);
            beforeCalls.incrementAndGet();
            return true;
        };

        //when
        LObjBooleanPredicateX<Integer ,X> function = sutO.fromBoolean(before1,before2);
        function.doTest((Integer )Integer.valueOf(80),true);

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(beforeCalls.get()).isEqualTo(2);
    }


    @Test
    public void testfrom() throws ParseException {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LObjBooleanPredicateX<Integer ,X> sutO = (Integer t, boolean b) -> {
                mainFunctionCalled.set(true);
                assertThat(t).isEqualTo((T)Integer.valueOf(90));
                assertThat( b).isEqualTo(true);
                return true;
        };

        LFunctionX<Integer ,Integer ,X> before1 = p0 -> {
            assertThat(p0).isEqualTo(Integer.valueOf(80));
            beforeCalls.incrementAndGet();
            return 90;
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
    public void testThen0() throws ParseException  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);


        //given (+ some assertions)
        LObjBooleanPredicateX<Integer ,X> sutO = (Integer t, boolean b) -> {
                mainFunctionCalled.set(true);
                assertThat(t).isEqualTo((T)Integer.valueOf(80));
                assertThat( b).isEqualTo(true);
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
        LObjBooleanFunctionX<Integer ,Integer ,X> function = sutO.then(thenFunction);
        Integer  finalValue = function.doApply((Integer )Integer.valueOf(80),true);

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
            .isInstanceOf(LObjBooleanPredicate.class);
    }

    @Test
    public void testShoving() {
        assertThat(sut.shove())
            .isInstanceOf(LObjBooleanPredicate.class);
    }

    @Test
    public void testNestingX() {
        assertThat(sut.nestX())
            .isInstanceOf(LObjBooleanPredicateX.class);
    }

    @Test
    public void testShovingX() {
        assertThat(sut.shoveX())
            .isInstanceOf(LObjBooleanPredicateX.class);
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void testShove() {

        // given
        LObjBooleanPredicateX<T,X> sutThrowing = LObjBooleanPredicateX.lX((T t, boolean b) -> {
            throw new UnsupportedOperationException();
        });

        // when
        sutThrowing.shove().doTest((T)Integer.valueOf(100),true);
    }

    @Test
    public void testHandleX() throws ParseException {

        // given
        LObjBooleanPredicateX<T,X> sutThrowing = LObjBooleanPredicateX.lX((T t, boolean b) -> {
            throw new UnsupportedOperationException();
        });

        // when
        LObjBooleanPredicateX<T,X> wrapped = sutThrowing.handleX(UnsupportedOperationException.class, t -> {
            throw new IllegalArgumentException(EXCEPTION_WAS_WRAPPED, t);
        });

        // then
        try {
            wrapped.doTest((T)Integer.valueOf(100),true);
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
                .contains("LObjBooleanPredicateX: boolean doTest(T t, boolean b) throws X");
    }


    @Test
    public void isThrowing() {
        assertThat(sut.isThrowing())
            .isTrue();
    }


}
