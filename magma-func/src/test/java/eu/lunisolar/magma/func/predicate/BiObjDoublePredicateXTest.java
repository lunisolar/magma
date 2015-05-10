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
public class BiObjDoublePredicateXTest<T1,T2,X extends ParseException> {
    private static final String ORIGINAL_MESSAGE = "Original message";
    private static final String EXCEPTION_WAS_WRAPPED = "Exception was wrapped.";
    private static final String NO_EXCEPTION_WERE_THROWN = "No exception were thrown.";

    private boolean testValue = true;



    private BiObjDoublePredicateX<T1,T2,X> sut = new BiObjDoublePredicateX(){
        public  boolean test(Object t1,Object t2, double d) throws ParseException {
            return testValue;
        }
    };

    private BiObjDoublePredicate<T1,T2> opposite = new BiObjDoublePredicate(){
        public  boolean test(Object t1,Object t2, double d)  {
            return testValue;
        }
    };



    @Test
    public void testTheResult() throws ParseException {
        assertThat(sut.test((T1)Integer.valueOf(100),(T2)Integer.valueOf(100),(double)100))
            .isEqualTo(testValue);
    }

    @Test
    public void testNonNullShouldNotModifyValue() throws ParseException {
        assertThat(sut.nonNull((T1)Integer.valueOf(100),(T2)Integer.valueOf(100),(double)100))
            .isEqualTo(testValue);
    }

    @Test
    public void testApplyAsBooleanShouldNotModifyValue() throws ParseException {
        assertThat(sut.applyAsBoolean((T1)Integer.valueOf(100),(T2)Integer.valueOf(100),(double)100))
            .isEqualTo(testValue);

    }


    @Test
    public void testFunctionalInterfaceDescription() throws ParseException {
        assertThat(sut.functionalInterfaceDescription())
            .isEqualTo("BiObjDoublePredicateX: boolean test(T1 t1,T2 t2, double d) throws X");
    }

    @Test
    public void testLXMethod() throws ParseException {
        assertThat(BiObjDoublePredicateX.lX((Object t1,Object t2, double d) -> testValue ))
            .isInstanceOf(BiObjDoublePredicateX.class);
    }

    @Test
    public void testWrapXMethod() throws ParseException {
        assertThat(BiObjDoublePredicateX.wrapX(opposite))
            .isInstanceOf(BiObjDoublePredicateX.class);
    }


    @Test
    public void testWrapExceptionMethodWrapsTheException() throws ParseException {

        // given
        BiObjDoublePredicateX<T1,T2,X> sutThrowing = BiObjDoublePredicateX.lX((T1 t1,T2 t2, double d) -> {
            throw new UnsupportedOperationException();
        });

        // when
        BiObjDoublePredicateX<T1,T2,X> wrapped = BiObjDoublePredicateX.wrapException(sutThrowing, UnsupportedOperationException.class, null, t -> {
            throw new IllegalArgumentException(EXCEPTION_WAS_WRAPPED, t);
        });

        // then
        try {
            wrapped.test((T1)Integer.valueOf(100),(T2)Integer.valueOf(100),(double)100);
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
        BiObjDoublePredicateX<T1,T2,X> sutThrowing = BiObjDoublePredicateX.lX((T1 t1,T2 t2, double d) -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        BiObjDoublePredicateX<T1,T2,X> wrapped = BiObjDoublePredicateX.wrapException(sutThrowing, UnsupportedOperationException.class, null, t -> {
            throw new IllegalArgumentException(EXCEPTION_WAS_WRAPPED, t);
        });

        // then
        try {
            wrapped.test((T1)Integer.valueOf(100),(T2)Integer.valueOf(100),(double)100);
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
        BiObjDoublePredicateX<T1,T2,X> sutThrowing = BiObjDoublePredicateX.lX((T1 t1,T2 t2, double d) -> {
            throw new UnsupportedOperationException();
        });

        // when
        BiObjDoublePredicateX<T1,T2,X> wrapped = BiObjDoublePredicateX.wrapException(sutThrowing, UnsupportedOperationException.class, null, t -> {
            return null;
        });

        // then
        try {
            wrapped.test((T1)Integer.valueOf(100),(T2)Integer.valueOf(100),(double)100);
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
        assertThat(sut.negate().test((T1)Integer.valueOf(100),(T2)Integer.valueOf(100),(double)100))
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
        BiObjDoublePredicateX<T1,T2,X> fun1 = BiObjDoublePredicateX.lX((T1 t1,T2 t2, double d) -> f1Result);
        BiObjDoublePredicateX<T1,T2,X> fun2 = BiObjDoublePredicateX.lX((T1 t1,T2 t2, double d) -> f2Result);

        //when
        BiObjDoublePredicateX<T1,T2,X> andFunction = fun1.and(fun2);
        BiObjDoublePredicateX<T1,T2,X> orFunction = fun1.or(fun2);
        BiObjDoublePredicateX<T1,T2,X> xorFunction = fun1.xor(fun2);

        //then
        assertThat(andFunction.test((T1)Integer.valueOf(100),(T2)Integer.valueOf(100),(double)100))
                .isEqualTo(andResult);

        assertThat(orFunction.test((T1)Integer.valueOf(100),(T2)Integer.valueOf(100),(double)100))
                .isEqualTo(orResult);

        assertThat(xorFunction.test((T1)Integer.valueOf(100),(T2)Integer.valueOf(100),(double)100))
                .isEqualTo(xorResult);
    }

    @Test
    public void isEqual() throws ParseException  {
        //when
        BiObjDoublePredicateX<T1,T2,X> equals = BiObjDoublePredicateX.isEqual((T1)Integer.valueOf(100),(T2)Integer.valueOf(100),(double)100);

        //then
        assertThat(equals.test((T1)Integer.valueOf(100),(T2)Integer.valueOf(100),(double)100))
                .isTrue();

        assertThat(equals.test((T1)Integer.valueOf(0),(T2)Integer.valueOf(0),(double)0))
                .isFalse();
    }



    // <editor-fold desc="compose (functional)">

    @Test
    public void testfromDouble() throws ParseException {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        BiObjDoublePredicateX<Integer ,Integer ,X> sutO = (Integer t1,Integer t2, double d) -> {
                mainFunctionCalled.set(true);
                assertThat(t1).isEqualTo((T1)Integer.valueOf(90));
                assertThat(t2).isEqualTo((T2)Integer.valueOf(91));
                assertThat( d).isEqualTo((double)92);
                return true;
        };

        FunctionX<Integer ,Integer ,X> before1 = p0 -> {
            assertThat(p0).isEqualTo((T1)Integer.valueOf(80));
            beforeCalls.incrementAndGet();
            return 90;
        };
        FunctionX<Integer ,Integer ,X> before2 = p1 -> {
            assertThat(p1).isEqualTo((T2)Integer.valueOf(81));
            beforeCalls.incrementAndGet();
            return 91;
        };
        DoubleUnaryOperatorX<X> before3 = p2 -> {
            assertThat(p2).isEqualTo((double)82);
            beforeCalls.incrementAndGet();
            return (double)92;
        };

        //when
        BiObjDoublePredicateX<Integer ,Integer ,X> function = sutO.fromDouble(before1,before2,before3);
        function.test((Integer )Integer.valueOf(80),(Integer )Integer.valueOf(81),(double)82);

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(beforeCalls.get()).isEqualTo(3);
    }


    @Test
    public void testfrom() throws ParseException {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        BiObjDoublePredicateX<Integer ,Integer ,X> sutO = (Integer t1,Integer t2, double d) -> {
                mainFunctionCalled.set(true);
                assertThat(t1).isEqualTo((T1)Integer.valueOf(90));
                assertThat(t2).isEqualTo((T2)Integer.valueOf(91));
                assertThat( d).isEqualTo((double)92);
                return true;
        };

        FunctionX<Integer ,Integer ,X> before1 = p0 -> {
            assertThat(p0).isEqualTo(Integer.valueOf(80));
            beforeCalls.incrementAndGet();
            return 90;
        };
        FunctionX<Integer ,Integer ,X> before2 = p1 -> {
            assertThat(p1).isEqualTo(Integer.valueOf(81));
            beforeCalls.incrementAndGet();
            return 91;
        };
        ToDoubleFunctionX<Integer ,X> before3 = p2 -> {
            assertThat(p2).isEqualTo(Integer.valueOf(82));
            beforeCalls.incrementAndGet();
            return (double)92;
        };

        //when
        TriPredicateX<Integer ,Integer ,Integer ,X> function = sutO.from(before1,before2,before3);
        function.test((Integer )Integer.valueOf(80),(Integer )Integer.valueOf(81),(Integer )Integer.valueOf(82));

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(beforeCalls.get()).isEqualTo(3);
    }

    // </editor-fold>


    // <editor-fold desc="then (functional)">

    @Test
    public void testThen0() throws ParseException  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);


        //given (+ some assertions)
        BiObjDoublePredicateX<Integer ,Integer ,X> sutO = (Integer t1,Integer t2, double d) -> {
                mainFunctionCalled.set(true);
                assertThat(t1).isEqualTo((T1)Integer.valueOf(80));
                assertThat(t2).isEqualTo((T2)Integer.valueOf(81));
                assertThat( d).isEqualTo((double)82);
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
        BiObjDoubleFunctionX<Integer ,Integer ,Integer ,X> function = sutO.then(thenFunction);
        Integer  finalValue = function.apply((Integer )Integer.valueOf(80),(Integer )Integer.valueOf(81),(double)82);

        //then - finals
        assertThat(finalValue).isEqualTo(Integer.valueOf(100));
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    // </editor-fold>
    @Test
    public void testNonThrowing() {
        assertThat(sut.nonThrowing()).isInstanceOf(BiObjDoublePredicate.class);
    }

    @Test
    public void testUncheck() {
        assertThat(sut.uncheck()).isInstanceOf(BiObjDoublePredicateX.class);
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void testShove() {

        // given
        BiObjDoublePredicateX<T1,T2,X> sutThrowing = BiObjDoublePredicateX.lX((T1 t1,T2 t2, double d) -> {
            throw new UnsupportedOperationException();
        });

        // when
        sutThrowing.shove().test((T1)Integer.valueOf(100),(T2)Integer.valueOf(100),(double)100);
    }

    @Test
    public void testHandleX() throws ParseException {

        // given
        BiObjDoublePredicateX<T1,T2,X> sutThrowing = BiObjDoublePredicateX.lX((T1 t1,T2 t2, double d) -> {
            throw new UnsupportedOperationException();
        });

        // when
        BiObjDoublePredicateX<T1,T2,X> wrapped = sutThrowing.handleX(UnsupportedOperationException.class, t -> {
            throw new IllegalArgumentException(EXCEPTION_WAS_WRAPPED, t);
        });

        // then
        try {
            wrapped.test((T1)Integer.valueOf(100),(T2)Integer.valueOf(100),(double)100);
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
                .contains("BiObjDoublePredicateX: boolean test(T1 t1,T2 t2, double d) throws X");
    }


    @Test
    public void isThrowing() {
        assertThat(sut.isThrowing())
            .isTrue();
    }


}
