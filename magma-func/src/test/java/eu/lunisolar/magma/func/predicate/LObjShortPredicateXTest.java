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
public class LObjShortPredicateXTest<T,X extends ParseException> {
    private static final String ORIGINAL_MESSAGE = "Original message";
    private static final String EXCEPTION_WAS_WRAPPED = "Exception was wrapped.";
    private static final String NO_EXCEPTION_WERE_THROWN = "No exception were thrown.";

    private boolean testValue = true;



    private LObjShortPredicateX<T,X> sut = new LObjShortPredicateX(){
        public  boolean doTest(Object t, short s) throws ParseException {
            return testValue;
        }
    };

    private LObjShortPredicate<T> opposite = new LObjShortPredicate(){
        public  boolean doTest(Object t, short s)  {
            return testValue;
        }
    };



    private LObjShortPredicateX<T,ParseException> sutAlwaysThrowing = LObjShortPredicateX.lX((T t, short s) -> {
            throw new ParseException(ORIGINAL_MESSAGE, 0);
    });

    private LObjShortPredicateX<T,RuntimeException> sutAlwaysThrowingUnckeck = LObjShortPredicateX.lX((T t, short s) -> {
            throw new IndexOutOfBoundsException(ORIGINAL_MESSAGE);
    });


    @Test
    public void testTheResult() throws ParseException {
        assertThat(sut.doTest((T)Integer.valueOf(100),(short)100))
            .isEqualTo(testValue);
    }

    @Test
    public void testNonNullDoTest() throws ParseException {
        assertThat(sut.nonNullDoTest((T)Integer.valueOf(100),(short)100))
            .isEqualTo(testValue);
    }

    @Test
    public void testNestingDoTest_checked() throws ParseException {

        // then
        try {
            sutAlwaysThrowing.nestingDoTest((T)Integer.valueOf(100),(short)100);
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
            sutAlwaysThrowingUnckeck.nestingDoTest((T)Integer.valueOf(100),(short)100);
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
            sutAlwaysThrowing.shovingDoTest((T)Integer.valueOf(100),(short)100);
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
            sutAlwaysThrowingUnckeck.shovingDoTest((T)Integer.valueOf(100),(short)100);
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
        assertThat(sut.doApplyAsBoolean((T)Integer.valueOf(100),(short)100))
            .isEqualTo(testValue);

    }


    @Test
    public void testFunctionalInterfaceDescription() throws ParseException {
        assertThat(sut.functionalInterfaceDescription())
            .isEqualTo("LObjShortPredicateX: boolean doTest(T t, short s) throws X");
    }

    @Test
    public void testLXMethod() throws ParseException {
        assertThat(LObjShortPredicateX.lX((Object t, short s) -> testValue ))
            .isInstanceOf(LObjShortPredicateX.class);
    }

    @Test
    public void testWrapXMethod() throws ParseException {
        assertThat(LObjShortPredicateX.wrapX(opposite))
            .isInstanceOf(LObjShortPredicateX.class);
    }


    @Test
    public void testWrapExceptionMethodWrapsTheException() throws ParseException {

        // given
        LObjShortPredicateX<T,X> sutThrowing = LObjShortPredicateX.lX((T t, short s) -> {
            throw new UnsupportedOperationException();
        });

        // when
        LObjShortPredicateX<T,X> wrapped = LObjShortPredicateX.wrapException(sutThrowing, UnsupportedOperationException.class, null, t -> {
            throw new IllegalArgumentException(EXCEPTION_WAS_WRAPPED, t);
        });

        // then
        try {
            wrapped.doTest((T)Integer.valueOf(100),(short)100);
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
        LObjShortPredicateX<T,X> sutThrowing = LObjShortPredicateX.lX((T t, short s) -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LObjShortPredicateX<T,X> wrapped = LObjShortPredicateX.wrapException(sutThrowing, UnsupportedOperationException.class, null, t -> {
            throw new IllegalArgumentException(EXCEPTION_WAS_WRAPPED, t);
        });

        // then
        try {
            wrapped.doTest((T)Integer.valueOf(100),(short)100);
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
        LObjShortPredicateX<T,X> sutThrowing = LObjShortPredicateX.lX((T t, short s) -> {
            throw new UnsupportedOperationException();
        });

        // when
        LObjShortPredicateX<T,X> wrapped = LObjShortPredicateX.wrapException(sutThrowing, UnsupportedOperationException.class, null, t -> {
            return null;
        });

        // then
        try {
            wrapped.doTest((T)Integer.valueOf(100),(short)100);
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
        assertThat(sut.negate().doTest((T)Integer.valueOf(100),(short)100))
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
        LObjShortPredicateX<T,X> fun1 = LObjShortPredicateX.lX((T t, short s) -> f1Result);
        LObjShortPredicateX<T,X> fun2 = LObjShortPredicateX.lX((T t, short s) -> f2Result);

        //when
        LObjShortPredicateX<T,X> andFunction = fun1.and(fun2);
        LObjShortPredicateX<T,X> orFunction = fun1.or(fun2);
        LObjShortPredicateX<T,X> xorFunction = fun1.xor(fun2);

        //then
        assertThat(andFunction.doTest((T)Integer.valueOf(100),(short)100))
                .isEqualTo(andResult);

        assertThat(orFunction.doTest((T)Integer.valueOf(100),(short)100))
                .isEqualTo(orResult);

        assertThat(xorFunction.doTest((T)Integer.valueOf(100),(short)100))
                .isEqualTo(xorResult);
    }

    @Test
    public void isEqual() throws ParseException  {
        //when
        LObjShortPredicateX<T,X> equals = LObjShortPredicateX.isEqual((T)Integer.valueOf(100),(short)100);

        //then
        assertThat(equals.doTest((T)Integer.valueOf(100),(short)100))
                .isTrue();

        assertThat(equals.doTest((T)Integer.valueOf(0),(short)0))
                .isFalse();
    }



    // <editor-fold desc="compose (functional)">

    @Test
    public void testfromShort() throws ParseException {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LObjShortPredicateX<Integer ,X> sutO = (Integer t, short s) -> {
                mainFunctionCalled.set(true);
                assertThat(t).isEqualTo((T)Integer.valueOf(90));
                assertThat( s).isEqualTo((short)91);
                return true;
        };

        LFunctionX<Integer ,Integer ,X> before1 = p0 -> {
            assertThat(p0).isEqualTo((T)Integer.valueOf(80));
            beforeCalls.incrementAndGet();
            return 90;
        };
        LShortUnaryOperatorX<X> before2 = p1 -> {
            assertThat(p1).isEqualTo((short)81);
            beforeCalls.incrementAndGet();
            return (short)91;
        };

        //when
        LObjShortPredicateX<Integer ,X> function = sutO.fromShort(before1,before2);
        function.doTest((Integer )Integer.valueOf(80),(short)81);

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(beforeCalls.get()).isEqualTo(2);
    }


    @Test
    public void testfrom() throws ParseException {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LObjShortPredicateX<Integer ,X> sutO = (Integer t, short s) -> {
                mainFunctionCalled.set(true);
                assertThat(t).isEqualTo((T)Integer.valueOf(90));
                assertThat( s).isEqualTo((short)91);
                return true;
        };

        LFunctionX<Integer ,Integer ,X> before1 = p0 -> {
            assertThat(p0).isEqualTo(Integer.valueOf(80));
            beforeCalls.incrementAndGet();
            return 90;
        };
        LToShortFunctionX<Integer ,X> before2 = p1 -> {
            assertThat(p1).isEqualTo(Integer.valueOf(81));
            beforeCalls.incrementAndGet();
            return (short)91;
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
        LObjShortPredicateX<Integer ,X> sutO = (Integer t, short s) -> {
                mainFunctionCalled.set(true);
                assertThat(t).isEqualTo((T)Integer.valueOf(80));
                assertThat( s).isEqualTo((short)81);
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
        LObjShortFunctionX<Integer ,Integer ,X> function = sutO.then(thenFunction);
        Integer  finalValue = function.doApply((Integer )Integer.valueOf(80),(short)81);

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
            .isInstanceOf(LObjShortPredicate.class);
    }

    @Test
    public void testShoving() {
        assertThat(sut.shove())
            .isInstanceOf(LObjShortPredicate.class);
    }

    @Test
    public void testNestingX() {
        assertThat(sut.nestX())
            .isInstanceOf(LObjShortPredicateX.class);
    }

    @Test
    public void testShovingX() {
        assertThat(sut.shoveX())
            .isInstanceOf(LObjShortPredicateX.class);
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void testShove() {

        // given
        LObjShortPredicateX<T,X> sutThrowing = LObjShortPredicateX.lX((T t, short s) -> {
            throw new UnsupportedOperationException();
        });

        // when
        sutThrowing.shove().doTest((T)Integer.valueOf(100),(short)100);
    }

    @Test
    public void testHandleX() throws ParseException {

        // given
        LObjShortPredicateX<T,X> sutThrowing = LObjShortPredicateX.lX((T t, short s) -> {
            throw new UnsupportedOperationException();
        });

        // when
        LObjShortPredicateX<T,X> wrapped = sutThrowing.handleX(UnsupportedOperationException.class, t -> {
            throw new IllegalArgumentException(EXCEPTION_WAS_WRAPPED, t);
        });

        // then
        try {
            wrapped.doTest((T)Integer.valueOf(100),(short)100);
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
                .contains("LObjShortPredicateX: boolean doTest(T t, short s) throws X");
    }


    @Test
    public void isThrowing() {
        assertThat(sut.isThrowing())
            .isTrue();
    }


}
