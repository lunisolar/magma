/*
 * This file is part of "lunisolar-magma".
 *
 * (C) Copyright 2014-2016 Lunisolar (http://lunisolar.eu/).
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
public class LBiObjDoublePredicateXTest<T1,T2,X extends ParseException> {
    private static final String ORIGINAL_MESSAGE = "Original message";
    private static final String EXCEPTION_WAS_WRAPPED = "Exception was wrapped.";
    private static final String NO_EXCEPTION_WERE_THROWN = "No exception were thrown.";

    private boolean testValue = true;



    private LBiObjDoublePredicateX<T1,T2,X> sut = new LBiObjDoublePredicateX(){
        public  boolean doTest(Object a1,Object a2,double a3) throws ParseException {
            return testValue;
        }
    };

    private LBiObjDoublePredicate<T1,T2> opposite = new LBiObjDoublePredicate(){
        public  boolean doTest(Object a1,Object a2,double a3)  {
            return testValue;
        }
    };



    private LBiObjDoublePredicateX<T1,T2,ParseException> sutAlwaysThrowing = LBiObjDoublePredicateX.lX((T1 a1,T2 a2,double a3) -> {
            throw new ParseException(ORIGINAL_MESSAGE, 0);
    });

    private LBiObjDoublePredicateX<T1,T2,RuntimeException> sutAlwaysThrowingUnckeck = LBiObjDoublePredicateX.lX((T1 a1,T2 a2,double a3) -> {
            throw new IndexOutOfBoundsException(ORIGINAL_MESSAGE);
    });


    @Test
    public void testTheResult() throws X {
        assertThat(sut.doTest((T1)Integer.valueOf(100),(T2)Integer.valueOf(100),(double)100))
            .isEqualTo(testValue);
    }

    @Test
    public void testTupleCall() throws X {

        LBiObjDoubleTriple<T1,T2> domainObject = Tuple4U.tuple((T1)Integer.valueOf(100),(T2)Integer.valueOf(100),(double)100);

        Object result = sut.tupleTest(domainObject);

        assertThat(result)
            .isEqualTo(testValue);
    }

    @Test
    public void testNonNullDoTest() throws X {
        assertThat(sut.nonNullDoTest((T1)Integer.valueOf(100),(T2)Integer.valueOf(100),(double)100))
            .isEqualTo(testValue);
    }

    @Test
    public void testNestingDoTestChecked() throws X {

        // then
        try {
            sutAlwaysThrowing.nestingDoTest((T1)Integer.valueOf(100),(T2)Integer.valueOf(100),(double)100);
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
            sutAlwaysThrowingUnckeck.nestingDoTest((T1)Integer.valueOf(100),(T2)Integer.valueOf(100),(double)100);
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
            sutAlwaysThrowing.shovingDoTest((T1)Integer.valueOf(100),(T2)Integer.valueOf(100),(double)100);
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
            sutAlwaysThrowingUnckeck.shovingDoTest((T1)Integer.valueOf(100),(T2)Integer.valueOf(100),(double)100);
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
        assertThat(sut.doApplyAsBoolean((T1)Integer.valueOf(100),(T2)Integer.valueOf(100),(double)100))
            .isEqualTo(testValue);

    }


    @Test
    public void testFunctionalInterfaceDescription() throws X {
        assertThat(sut.functionalInterfaceDescription())
            .isEqualTo("LBiObjDoublePredicateX: boolean doTest(T1 a1,T2 a2,double a3) throws X");
    }

    @Test
    public void testLXMethod() throws X {
        assertThat(LBiObjDoublePredicateX.lX((Object a1,Object a2,double a3) -> testValue ))
            .isInstanceOf(LBiObjDoublePredicateX.class);
    }

    @Test
    public void testWrapXMethod() throws X {
        assertThat(LBiObjDoublePredicateX.wrapX(opposite))
            .isInstanceOf(LBiObjDoublePredicateX.class);
    }


    @Test
    public void testWrapExceptionMethodWrapsTheException() throws X {

        // given
        LBiObjDoublePredicateX<T1,T2,X> sutThrowing = LBiObjDoublePredicateX.lX((T1 a1,T2 a2,double a3) -> {
            throw new UnsupportedOperationException();
        });

        // when
        LBiObjDoublePredicateX<T1,T2,X> wrapped = sutThrowing.handleBiObjDoublePredX(handler -> handler
            .wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED));

        // then
        try {
            wrapped.doTest((T1)Integer.valueOf(100),(T2)Integer.valueOf(100),(double)100);
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
        LBiObjDoublePredicateX<T1,T2,X> sutThrowing = LBiObjDoublePredicateX.lX((T1 a1,T2 a2,double a3) -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LBiObjDoublePredicateX<T1,T2,X> wrapped = sutThrowing.handleBiObjDoublePredX(handler -> handler
                .wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED)
                .throwIf(IndexOutOfBoundsException.class));

        // then
        try {
            wrapped.doTest((T1)Integer.valueOf(100),(T2)Integer.valueOf(100),(double)100);
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
        LBiObjDoublePredicateX<T1,T2,X> sutThrowing = LBiObjDoublePredicateX.lX((T1 a1,T2 a2,double a3) -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LBiObjDoublePredicateX<T1,T2,X> wrapped = sutThrowing.handleBiObjDoublePredX(handler -> handler
                .wrapWhen(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED)
                .throwIf(IndexOutOfBoundsException.class));

        // then
        try {
            wrapped.doTest((T1)Integer.valueOf(100),(T2)Integer.valueOf(100),(double)100);
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
        LBiObjDoublePredicateX<T1,T2,X> sutThrowing = LBiObjDoublePredicateX.lX((T1 a1,T2 a2,double a3) -> {
            throw (X) new ParseException(ORIGINAL_MESSAGE, 0);
        });

        // when
        LBiObjDoublePredicateX<T1,T2,X> wrapped = sutThrowing.handleBiObjDoublePredX(h -> Function4U.doNothing());

        // then
        try {
            wrapped.doTest((T1)Integer.valueOf(100),(T2)Integer.valueOf(100),(double)100);
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
        assertThat(sut.negate().doTest((T1)Integer.valueOf(100),(T2)Integer.valueOf(100),(double)100))
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
        LBiObjDoublePredicateX<T1,T2,X> fun1 = LBiObjDoublePredicateX.lX((T1 a1,T2 a2,double a3) -> f1Result);
        LBiObjDoublePredicateX<T1,T2,X> fun2 = LBiObjDoublePredicateX.lX((T1 a1,T2 a2,double a3) -> f2Result);

        //when
        LBiObjDoublePredicateX<T1,T2,X> andFunction = fun1.and(fun2);
        LBiObjDoublePredicateX<T1,T2,X> orFunction = fun1.or(fun2);
        LBiObjDoublePredicateX<T1,T2,X> xorFunction = fun1.xor(fun2);

        //then
        assertThat(andFunction.doTest((T1)Integer.valueOf(100),(T2)Integer.valueOf(100),(double)100))
                .isEqualTo(andResult);

        assertThat(orFunction.doTest((T1)Integer.valueOf(100),(T2)Integer.valueOf(100),(double)100))
                .isEqualTo(orResult);

        assertThat(xorFunction.doTest((T1)Integer.valueOf(100),(T2)Integer.valueOf(100),(double)100))
                .isEqualTo(xorResult);
    }

    @Test
    public void isEqual() throws X  {
        //when
        LBiObjDoublePredicateX<T1,T2,X> equals = LBiObjDoublePredicateX.isEqual((T1)Integer.valueOf(100),(T2)Integer.valueOf(100),(double)100);

        //then
        assertThat(equals.doTest((T1)Integer.valueOf(100),(T2)Integer.valueOf(100),(double)100))
                .isTrue();

        assertThat(equals.doTest((T1)Integer.valueOf(0),(T2)Integer.valueOf(0),(double)0))
                .isFalse();
    }



    // <editor-fold desc="compose (functional)">

    @Test
    public void testbiObjDoublePredComposeDouble() throws X {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LBiObjDoublePredicateX<Integer ,Integer ,X> sutO = (Integer a1,Integer a2,double a3) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo((T1)Integer.valueOf(90));
                assertThat(a2).isEqualTo((T2)Integer.valueOf(91));
                assertThat(a3).isEqualTo((double)92);
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
        LDoubleUnaryOperatorX<X> before3 = p2 -> {
            assertThat(p2).isEqualTo((double)82);
            beforeCalls.incrementAndGet();
            return (double)92;
        };

        //when
        LBiObjDoublePredicateX<Integer ,Integer ,X> function = sutO.biObjDoublePredComposeDouble(before1,before2,before3);
        function.doTest((Integer )Integer.valueOf(80),(Integer )Integer.valueOf(81),(double)82);

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(beforeCalls.get()).isEqualTo(3);
    }


    @Test
    public void testbiObjDoublePredCompose() throws X {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LBiObjDoublePredicateX<Integer ,Integer ,X> sutO = (Integer a1,Integer a2,double a3) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo((T1)Integer.valueOf(90));
                assertThat(a2).isEqualTo((T2)Integer.valueOf(91));
                assertThat(a3).isEqualTo((double)92);
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
        LToDoubleFunctionX<Integer ,X> before3 = p2 -> {
            assertThat(p2).isEqualTo(Integer.valueOf(82));
            beforeCalls.incrementAndGet();
            return (double)92;
        };

        //when
        LTriPredicateX<Integer ,Integer ,Integer ,X> function = sutO.biObjDoublePredCompose(before1,before2,before3);
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
        LBiObjDoublePredicateX<Integer ,Integer ,X> sutO = (Integer a1,Integer a2,double a3) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo((T1)Integer.valueOf(80));
                assertThat(a2).isEqualTo((T2)Integer.valueOf(81));
                assertThat(a3).isEqualTo((double)82);
                return true;
        };

        LBoolFunctionX<Integer ,X> thenFunction = p -> {
                thenFunctionCalled.set(true);
                // 
                assertThat(p).isEqualTo(true);
                // V
                return Integer.valueOf(100);
        };

        //when
        LBiObjDoubleFunctionX<Integer ,Integer ,Integer ,X> function = sutO.boolToBiObjDoubleFunction(thenFunction);
        Integer  finalValue = function.doApply((Integer )Integer.valueOf(80),(Integer )Integer.valueOf(81),(double)82);

        //then - finals
        assertThat(finalValue).isEqualTo(Integer.valueOf(100));
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    // </editor-fold>

    @Test
    public void testNesting() {
        assertThat(sut.nestingBiObjDoublePred())
            .isInstanceOf(LBiObjDoublePredicate.class);
    }

    @Test
    public void testShoving() {
        assertThat(sut.shovingBiObjDoublePred())
            .isInstanceOf(LBiObjDoublePredicate.class);
    }

    @Test
    public void testNestingX() {
        assertThat(sut.nestingBiObjDoublePredX())
            .isInstanceOf(LBiObjDoublePredicateX.class);
    }

    @Test
    public void testShovingX() {
        assertThat(sut.shovingBiObjDoublePredX())
            .isInstanceOf(LBiObjDoublePredicateX.class);
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void testShove() {

        // given
        LBiObjDoublePredicateX<T1,T2,X> sutThrowing = LBiObjDoublePredicateX.lX((T1 a1,T2 a2,double a3) -> {
            throw new UnsupportedOperationException();
        });

        // when
        sutThrowing.shovingBiObjDoublePred().doTest((T1)Integer.valueOf(100),(T2)Integer.valueOf(100),(double)100);
    }

    @Test
    public void testHandleBiObjDoublePred() throws X {

        // given
        LBiObjDoublePredicateX<T1,T2,X> sutThrowing = LBiObjDoublePredicateX.lX((T1 a1,T2 a2,double a3) -> {
            throw new UnsupportedOperationException();
        });

        // when
        LBiObjDoublePredicateX<T1,T2,X> wrapped = sutThrowing.handleBiObjDoublePredX(h -> {
            h.wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED);
        });

        // then
        try {
            wrapped.doTest((T1)Integer.valueOf(100),(T2)Integer.valueOf(100),(double)100);
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
                .contains("LBiObjDoublePredicateX: boolean doTest(T1 a1,T2 a2,double a3) throws X");
    }


    @Test
    public void isThrowing() {
        assertThat(sut.isThrowing())
            .isTrue();
    }

    //<editor-fold desc="Variants">

    private boolean variant1(T1 a1,double a3,T2 a2) {
        return true;
    }

    @Test
    public void compilerSubstituteVariant1() {
        LBiObjDoublePredicateX lambda = LBiObjDoublePredicateX./*<T1,T2,X>*/lX1(this::variant1);

        assertThat(lambda).isInstanceOf(LBiObjDoublePredicateX.V1.class);
    }


    private boolean variant2(T2 a2,T1 a1,double a3) {
        return true;
    }

    @Test
    public void compilerSubstituteVariant2() {
        LBiObjDoublePredicateX lambda = LBiObjDoublePredicateX./*<T1,T2,X>*/lX2(this::variant2);

        assertThat(lambda).isInstanceOf(LBiObjDoublePredicateX.V2.class);
    }


    private boolean variant3(T2 a2,double a3,T1 a1) {
        return true;
    }

    @Test
    public void compilerSubstituteVariant3() {
        LBiObjDoublePredicateX lambda = LBiObjDoublePredicateX./*<T1,T2,X>*/lX3(this::variant3);

        assertThat(lambda).isInstanceOf(LBiObjDoublePredicateX.V3.class);
    }


    private boolean variant4(double a3,T1 a1,T2 a2) {
        return true;
    }

    @Test
    public void compilerSubstituteVariant4() {
        LBiObjDoublePredicateX lambda = LBiObjDoublePredicateX./*<T1,T2,X>*/lX4(this::variant4);

        assertThat(lambda).isInstanceOf(LBiObjDoublePredicateX.V4.class);
    }


    private boolean variant5(double a3,T2 a2,T1 a1) {
        return true;
    }

    @Test
    public void compilerSubstituteVariant5() {
        LBiObjDoublePredicateX lambda = LBiObjDoublePredicateX./*<T1,T2,X>*/lX5(this::variant5);

        assertThat(lambda).isInstanceOf(LBiObjDoublePredicateX.V5.class);
    }

    //</editor-fold>


    @Test void safeCompiles() {
        LBiObjDoublePredicateX r1 = LBiObjDoublePredicateX.safe(sut); //NOSONAR
    }

    @Test void safePropagates() {
        Object result = LBiObjDoublePredicateX.safe(sut);
        assertThat(result).isSameAs(sut);
    }

    @Test void safeProtectsAgainstNpe() {
        Object result = LBiObjDoublePredicateX.safe(null);
        assertThat(result).isSameAs(LBiObjDoublePredicateX.lX(LBiObjDoublePredicateX.safe()));
    }

    @Test <Y extends Throwable> void safeSupplierPropagates() {
        LSupplierX<LBiObjDoublePredicateX<T1,T2,X>,Y> supplier = ()->sut;
        Object result = LBiObjDoublePredicateX.safeSupplier(supplier);
        assertThat(result).isSameAs(supplier);
    }

    @Test <Y extends Throwable> void safeSupplierProtectsAgainstNpe() {
        Object result = LBiObjDoublePredicateX.safeSupplier(null);
        assertThat(result).isSameAs(LBiObjDoublePredicateX.safeSupplier());
    }

    @Test <Y extends Throwable> void safeSupplierCompiles() {
        LSupplierX<LBiObjDoublePredicateX<T1,T2,X>,Y> r1 = LBiObjDoublePredicateX.safeSupplier(()->sut);  //NOSONAR
    }

}
