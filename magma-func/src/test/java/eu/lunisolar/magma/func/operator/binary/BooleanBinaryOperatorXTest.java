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
 * Foobar is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with Foobar.  If not, see <http://www.gnu.org/licenses/>.
 */

package eu.lunisolar.magma.func.operator.binary;

import eu.lunisolar.magma.func.*; // NOSONAR
import javax.annotation.Nonnull; // NOSONAR
import javax.annotation.Nullable; // NOSONAR
import java.util.Objects;// NOSONAR
import eu.lunisolar.magma.basics.meta.*; // NOSONAR
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
public class BooleanBinaryOperatorXTest<X extends ParseException> {
    private static final String ORIGINAL_MESSAGE = "Original message";
    private static final String EXCEPTION_WAS_WRAPPED = "Exception was wrapped.";
    private static final String NO_EXCEPTION_WERE_THROWN = "No exception were thrown.";

    private boolean testValue = true;



    private BooleanBinaryOperatorX<X> sut = new BooleanBinaryOperatorX(){
        public  boolean applyAsBoolean(boolean b1,boolean b2) throws ParseException {
            return testValue;
        }
    };

    private BooleanBinaryOperator opposite = new BooleanBinaryOperator(){
        public  boolean applyAsBoolean(boolean b1,boolean b2)  {
            return testValue;
        }
    };



    @Test
    public void testTheResult() throws ParseException {
        assertThat(sut.applyAsBoolean(true,true))
            .isEqualTo(testValue);
    }

    @Test
    public void testNonNullShouldNotModifyValue() throws ParseException {
        assertThat(sut.nonNull(true,true))
            .isEqualTo(testValue);
    }


    @Test
    public void testFunctionalInterfaceDescription() throws ParseException {
        assertThat(sut.functionalInterfaceDescription())
            .isEqualTo("BooleanBinaryOperatorX: boolean applyAsBoolean(boolean b1,boolean b2) throws X");
    }

    @Test
    public void testLXMethod() throws ParseException {
        assertThat(BooleanBinaryOperatorX.lX((boolean b1,boolean b2) -> testValue ))
            .isInstanceOf(BooleanBinaryOperatorX.class);
    }

    @Test
    public void testWrapXMethod() throws ParseException {
        assertThat(BooleanBinaryOperatorX.wrapX(opposite))
            .isInstanceOf(BooleanBinaryOperatorX.class);
    }


    @Test
    public void testWrapExceptionMethodWrapsTheException() throws ParseException {

        // given
        BooleanBinaryOperatorX<X> sutThrowing = BooleanBinaryOperatorX.lX((boolean b1,boolean b2) -> {
            throw new UnsupportedOperationException();
        });

        // when
        BooleanBinaryOperatorX<X> wrapped = BooleanBinaryOperatorX.wrapException(sutThrowing, UnsupportedOperationException.class, null, t -> {
            throw new IllegalArgumentException(EXCEPTION_WAS_WRAPPED, t);
        });

        // then
        try {
            wrapped.applyAsBoolean(true,true);
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
        BooleanBinaryOperatorX<X> sutThrowing = BooleanBinaryOperatorX.lX((boolean b1,boolean b2) -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        BooleanBinaryOperatorX<X> wrapped = BooleanBinaryOperatorX.wrapException(sutThrowing, UnsupportedOperationException.class, null, t -> {
            throw new IllegalArgumentException(EXCEPTION_WAS_WRAPPED, t);
        });

        // then
        try {
            wrapped.applyAsBoolean(true,true);
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
        BooleanBinaryOperatorX<X> sutThrowing = BooleanBinaryOperatorX.lX((boolean b1,boolean b2) -> {
            throw new UnsupportedOperationException();
        });

        // when
        BooleanBinaryOperatorX<X> wrapped = BooleanBinaryOperatorX.wrapException(sutThrowing, UnsupportedOperationException.class, null, t -> {
            return null;
        });

        // then
        try {
            wrapped.applyAsBoolean(true,true);
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
        assertThat(sut.negate().applyAsBoolean(true,true))
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
        BooleanBinaryOperatorX<X> fun1 = BooleanBinaryOperatorX.lX((boolean b1,boolean b2) -> f1Result);
        BooleanBinaryOperatorX<X> fun2 = BooleanBinaryOperatorX.lX((boolean b1,boolean b2) -> f2Result);

        //when
        BooleanBinaryOperatorX<X> andFunction = fun1.and(fun2);
        BooleanBinaryOperatorX<X> orFunction = fun1.or(fun2);
        BooleanBinaryOperatorX<X> xorFunction = fun1.xor(fun2);

        //then
        assertThat(andFunction.applyAsBoolean(true,true))
                .isEqualTo(andResult);

        assertThat(orFunction.applyAsBoolean(true,true))
                .isEqualTo(orResult);

        assertThat(xorFunction.applyAsBoolean(true,true))
                .isEqualTo(xorResult);
    }

    @Test
    public void isEqual() throws ParseException  {
        //when
        BooleanBinaryOperatorX<X> equals = BooleanBinaryOperatorX.isEqual(true,true);

        //then
        assertThat(equals.test(true,true))
                .isTrue();

        assertThat(equals.test(false,false))
                .isFalse();
    }


    @Test(dataProvider="boolean permutations")
    public void testOperatorAndOrXor(final boolean value1, final boolean value2, final boolean andResult, final boolean orResult, final boolean xorResult) throws ParseException {
        //given
        BooleanBinaryOperatorX<X> andFunction = BooleanBinaryOperatorX.and();
        BooleanBinaryOperatorX<X> orFunction = BooleanBinaryOperatorX.or();
        BooleanBinaryOperatorX<X> xorFunction = BooleanBinaryOperatorX.xor();

        //then
        assertThat(andFunction.applyAsBoolean(value1, value2))
                .isEqualTo(andResult);

        assertThat(orFunction.applyAsBoolean(value1, value2))
                .isEqualTo(orResult);

        assertThat(xorFunction.applyAsBoolean(value1, value2))
                .isEqualTo(xorResult);
    }


    // <editor-fold desc="compose (functional)">

    @Test
    public void testfromBoolean() throws ParseException {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        BooleanBinaryOperatorX<X> sutO = (boolean b1,boolean b2) -> {
                mainFunctionCalled.set(true);
                assertThat(b1).isEqualTo(true);
                assertThat(b2).isEqualTo(true);
                return true;
        };

        BooleanUnaryOperatorX<X> before1 = p0 -> {
            assertThat(p0).isEqualTo(true);
            beforeCalls.incrementAndGet();
            return true;
        };
        BooleanUnaryOperatorX<X> before2 = p1 -> {
            assertThat(p1).isEqualTo(true);
            beforeCalls.incrementAndGet();
            return true;
        };

        //when
        BooleanBinaryOperatorX<X> function = sutO.fromBoolean(before1,before2);
        function.applyAsBoolean(true,true);

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(beforeCalls.get()).isEqualTo(2);
    }


    @Test
    public void testfrom() throws ParseException {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        BooleanBinaryOperatorX<X> sutO = (boolean b1,boolean b2) -> {
                mainFunctionCalled.set(true);
                assertThat(b1).isEqualTo(true);
                assertThat(b2).isEqualTo(true);
                return true;
        };

        PredicateX<Integer ,X> before1 = p0 -> {
            assertThat(p0).isEqualTo(Integer.valueOf(80));
            beforeCalls.incrementAndGet();
            return true;
        };
        PredicateX<Integer ,X> before2 = p1 -> {
            assertThat(p1).isEqualTo(Integer.valueOf(81));
            beforeCalls.incrementAndGet();
            return true;
        };

        //when
        BiPredicateX<Integer ,Integer ,X> function = sutO.from(before1,before2);
        function.test((Integer )Integer.valueOf(80),(Integer )Integer.valueOf(81));

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
        BooleanBinaryOperatorX<X> sutO = (boolean b1,boolean b2) -> {
                mainFunctionCalled.set(true);
                assertThat(b1).isEqualTo(true);
                assertThat(b2).isEqualTo(true);
                return true;
        };

        BooleanFunctionX<Integer ,X> thenFunction = p -> {
                thenFunctionCalled.set(true);
                // 
                assertThat(p).isEqualTo(true);
                // V
                return Integer.valueOf(100);
        };

        //when
        BooleanBiFunctionX<Integer ,X> function = sutO.then(thenFunction);
        Integer  finalValue = function.apply(true,true);

        //then - finals
        assertThat(finalValue).isEqualTo(Integer.valueOf(100));
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    // </editor-fold>
    @Test
    public void testNonThrowing() {
        assertThat(sut.nonThrowing()).isInstanceOf(BooleanBinaryOperator.class);
    }

    @Test
    public void testUncheck() {
        assertThat(sut.uncheck()).isInstanceOf(BooleanBinaryOperatorX.class);
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void testShove() {

        // given
        BooleanBinaryOperatorX<X> sutThrowing = BooleanBinaryOperatorX.lX((boolean b1,boolean b2) -> {
            throw new UnsupportedOperationException();
        });

        // when
        sutThrowing.shove().applyAsBoolean(true,true);
    }

    @Test
    public void testHandle() throws ParseException {

        // given
        BooleanBinaryOperatorX<X> sutThrowing = BooleanBinaryOperatorX.lX((boolean b1,boolean b2) -> {
            throw new UnsupportedOperationException();
        });

        // when
        BooleanBinaryOperatorX<X> wrapped = sutThrowing.handle(UnsupportedOperationException.class, t -> {
            throw new IllegalArgumentException(EXCEPTION_WAS_WRAPPED, t);
        });

        // then
        try {
            wrapped.applyAsBoolean(true,true);
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
                .contains("BooleanBinaryOperatorX: boolean applyAsBoolean(boolean b1,boolean b2) throws X");
    }

}
