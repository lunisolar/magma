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
public class LBiObjCharPredicateXTest<T1,T2,X extends ParseException> {
    private static final String ORIGINAL_MESSAGE = "Original message";
    private static final String EXCEPTION_WAS_WRAPPED = "Exception was wrapped.";
    private static final String NO_EXCEPTION_WERE_THROWN = "No exception were thrown.";

    private boolean testValue = true;



    private LBiObjCharPredicateX<T1,T2,X> sut = new LBiObjCharPredicateX(){
        public  boolean doTest(Object t1,Object t2, char c) throws ParseException {
            return testValue;
        }
    };

    private LBiObjCharPredicate<T1,T2> opposite = new LBiObjCharPredicate(){
        public  boolean doTest(Object t1,Object t2, char c)  {
            return testValue;
        }
    };



    @Test
    public void testTheResult() throws ParseException {
        assertThat(sut.doTest((T1)Integer.valueOf(100),(T2)Integer.valueOf(100),(char)100))
            .isEqualTo(testValue);
    }

    @Test
    public void testNonNullShouldNotModifyValue() throws ParseException {
        assertThat(sut.nonNull((T1)Integer.valueOf(100),(T2)Integer.valueOf(100),(char)100))
            .isEqualTo(testValue);
    }

    @Test
    public void testApplyAsBooleanShouldNotModifyValue() throws ParseException {
        assertThat(sut.doApplyAsBoolean((T1)Integer.valueOf(100),(T2)Integer.valueOf(100),(char)100))
            .isEqualTo(testValue);

    }


    @Test
    public void testFunctionalInterfaceDescription() throws ParseException {
        assertThat(sut.functionalInterfaceDescription())
            .isEqualTo("LBiObjCharPredicateX: boolean doTest(T1 t1,T2 t2, char c) throws X");
    }

    @Test
    public void testLXMethod() throws ParseException {
        assertThat(LBiObjCharPredicateX.lX((Object t1,Object t2, char c) -> testValue ))
            .isInstanceOf(LBiObjCharPredicateX.class);
    }

    @Test
    public void testWrapXMethod() throws ParseException {
        assertThat(LBiObjCharPredicateX.wrapX(opposite))
            .isInstanceOf(LBiObjCharPredicateX.class);
    }


    @Test
    public void testWrapExceptionMethodWrapsTheException() throws ParseException {

        // given
        LBiObjCharPredicateX<T1,T2,X> sutThrowing = LBiObjCharPredicateX.lX((T1 t1,T2 t2, char c) -> {
            throw new UnsupportedOperationException();
        });

        // when
        LBiObjCharPredicateX<T1,T2,X> wrapped = LBiObjCharPredicateX.wrapException(sutThrowing, UnsupportedOperationException.class, null, t -> {
            throw new IllegalArgumentException(EXCEPTION_WAS_WRAPPED, t);
        });

        // then
        try {
            wrapped.doTest((T1)Integer.valueOf(100),(T2)Integer.valueOf(100),(char)100);
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
        LBiObjCharPredicateX<T1,T2,X> sutThrowing = LBiObjCharPredicateX.lX((T1 t1,T2 t2, char c) -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LBiObjCharPredicateX<T1,T2,X> wrapped = LBiObjCharPredicateX.wrapException(sutThrowing, UnsupportedOperationException.class, null, t -> {
            throw new IllegalArgumentException(EXCEPTION_WAS_WRAPPED, t);
        });

        // then
        try {
            wrapped.doTest((T1)Integer.valueOf(100),(T2)Integer.valueOf(100),(char)100);
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
        LBiObjCharPredicateX<T1,T2,X> sutThrowing = LBiObjCharPredicateX.lX((T1 t1,T2 t2, char c) -> {
            throw new UnsupportedOperationException();
        });

        // when
        LBiObjCharPredicateX<T1,T2,X> wrapped = LBiObjCharPredicateX.wrapException(sutThrowing, UnsupportedOperationException.class, null, t -> {
            return null;
        });

        // then
        try {
            wrapped.doTest((T1)Integer.valueOf(100),(T2)Integer.valueOf(100),(char)100);
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
        assertThat(sut.negate().doTest((T1)Integer.valueOf(100),(T2)Integer.valueOf(100),(char)100))
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
        LBiObjCharPredicateX<T1,T2,X> fun1 = LBiObjCharPredicateX.lX((T1 t1,T2 t2, char c) -> f1Result);
        LBiObjCharPredicateX<T1,T2,X> fun2 = LBiObjCharPredicateX.lX((T1 t1,T2 t2, char c) -> f2Result);

        //when
        LBiObjCharPredicateX<T1,T2,X> andFunction = fun1.and(fun2);
        LBiObjCharPredicateX<T1,T2,X> orFunction = fun1.or(fun2);
        LBiObjCharPredicateX<T1,T2,X> xorFunction = fun1.xor(fun2);

        //then
        assertThat(andFunction.doTest((T1)Integer.valueOf(100),(T2)Integer.valueOf(100),(char)100))
                .isEqualTo(andResult);

        assertThat(orFunction.doTest((T1)Integer.valueOf(100),(T2)Integer.valueOf(100),(char)100))
                .isEqualTo(orResult);

        assertThat(xorFunction.doTest((T1)Integer.valueOf(100),(T2)Integer.valueOf(100),(char)100))
                .isEqualTo(xorResult);
    }

    @Test
    public void isEqual() throws ParseException  {
        //when
        LBiObjCharPredicateX<T1,T2,X> equals = LBiObjCharPredicateX.isEqual((T1)Integer.valueOf(100),(T2)Integer.valueOf(100),(char)100);

        //then
        assertThat(equals.doTest((T1)Integer.valueOf(100),(T2)Integer.valueOf(100),(char)100))
                .isTrue();

        assertThat(equals.doTest((T1)Integer.valueOf(0),(T2)Integer.valueOf(0),(char)0))
                .isFalse();
    }



    // <editor-fold desc="compose (functional)">

    @Test
    public void testfromChar() throws ParseException {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LBiObjCharPredicateX<Integer ,Integer ,X> sutO = (Integer t1,Integer t2, char c) -> {
                mainFunctionCalled.set(true);
                assertThat(t1).isEqualTo((T1)Integer.valueOf(90));
                assertThat(t2).isEqualTo((T2)Integer.valueOf(91));
                assertThat( c).isEqualTo((char)92);
                return true;
        };

        LFunctionX<Integer ,Integer ,X> before1 = p0 -> {
            assertThat(p0).isEqualTo((T1)Integer.valueOf(80));
            beforeCalls.incrementAndGet();
            return 90;
        };
        LFunctionX<Integer ,Integer ,X> before2 = p1 -> {
            assertThat(p1).isEqualTo((T2)Integer.valueOf(81));
            beforeCalls.incrementAndGet();
            return 91;
        };
        LCharUnaryOperatorX<X> before3 = p2 -> {
            assertThat(p2).isEqualTo((char)82);
            beforeCalls.incrementAndGet();
            return (char)92;
        };

        //when
        LBiObjCharPredicateX<Integer ,Integer ,X> function = sutO.fromChar(before1,before2,before3);
        function.doTest((Integer )Integer.valueOf(80),(Integer )Integer.valueOf(81),(char)82);

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(beforeCalls.get()).isEqualTo(3);
    }


    @Test
    public void testfrom() throws ParseException {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LBiObjCharPredicateX<Integer ,Integer ,X> sutO = (Integer t1,Integer t2, char c) -> {
                mainFunctionCalled.set(true);
                assertThat(t1).isEqualTo((T1)Integer.valueOf(90));
                assertThat(t2).isEqualTo((T2)Integer.valueOf(91));
                assertThat( c).isEqualTo((char)92);
                return true;
        };

        LFunctionX<Integer ,Integer ,X> before1 = p0 -> {
            assertThat(p0).isEqualTo(Integer.valueOf(80));
            beforeCalls.incrementAndGet();
            return 90;
        };
        LFunctionX<Integer ,Integer ,X> before2 = p1 -> {
            assertThat(p1).isEqualTo(Integer.valueOf(81));
            beforeCalls.incrementAndGet();
            return 91;
        };
        LToCharFunctionX<Integer ,X> before3 = p2 -> {
            assertThat(p2).isEqualTo(Integer.valueOf(82));
            beforeCalls.incrementAndGet();
            return (char)92;
        };

        //when
        LTriPredicateX<Integer ,Integer ,Integer ,X> function = sutO.from(before1,before2,before3);
        function.doTest((Integer )Integer.valueOf(80),(Integer )Integer.valueOf(81),(Integer )Integer.valueOf(82));

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
        LBiObjCharPredicateX<Integer ,Integer ,X> sutO = (Integer t1,Integer t2, char c) -> {
                mainFunctionCalled.set(true);
                assertThat(t1).isEqualTo((T1)Integer.valueOf(80));
                assertThat(t2).isEqualTo((T2)Integer.valueOf(81));
                assertThat( c).isEqualTo((char)82);
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
        LBiObjCharFunctionX<Integer ,Integer ,Integer ,X> function = sutO.then(thenFunction);
        Integer  finalValue = function.doApply((Integer )Integer.valueOf(80),(Integer )Integer.valueOf(81),(char)82);

        //then - finals
        assertThat(finalValue).isEqualTo(Integer.valueOf(100));
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    // </editor-fold>
    @Test
    public void testNonThrowing() {
        assertThat(sut.nonThrowing()).isInstanceOf(LBiObjCharPredicate.class);
    }

    @Test
    public void testUncheck() {
        assertThat(sut.uncheck()).isInstanceOf(LBiObjCharPredicateX.class);
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void testShove() {

        // given
        LBiObjCharPredicateX<T1,T2,X> sutThrowing = LBiObjCharPredicateX.lX((T1 t1,T2 t2, char c) -> {
            throw new UnsupportedOperationException();
        });

        // when
        sutThrowing.shove().doTest((T1)Integer.valueOf(100),(T2)Integer.valueOf(100),(char)100);
    }

    @Test
    public void testHandleX() throws ParseException {

        // given
        LBiObjCharPredicateX<T1,T2,X> sutThrowing = LBiObjCharPredicateX.lX((T1 t1,T2 t2, char c) -> {
            throw new UnsupportedOperationException();
        });

        // when
        LBiObjCharPredicateX<T1,T2,X> wrapped = sutThrowing.handleX(UnsupportedOperationException.class, t -> {
            throw new IllegalArgumentException(EXCEPTION_WAS_WRAPPED, t);
        });

        // then
        try {
            wrapped.doTest((T1)Integer.valueOf(100),(T2)Integer.valueOf(100),(char)100);
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
                .contains("LBiObjCharPredicateX: boolean doTest(T1 t1,T2 t2, char c) throws X");
    }


    @Test
    public void isThrowing() {
        assertThat(sut.isThrowing())
            .isTrue();
    }


}
