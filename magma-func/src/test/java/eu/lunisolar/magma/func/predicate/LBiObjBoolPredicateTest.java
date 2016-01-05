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
public class LBiObjBoolPredicateTest<T1,T2,X extends ParseException> {
    private static final String ORIGINAL_MESSAGE = "Original message";
    private static final String EXCEPTION_WAS_WRAPPED = "Exception was wrapped.";
    private static final String NO_EXCEPTION_WERE_THROWN = "No exception were thrown.";

    private boolean testValue = true;



    private LBiObjBoolPredicate<T1,T2> sut = new LBiObjBoolPredicate(){
        public  boolean doTest(Object a1,Object a2,boolean a3)  {
            return testValue;
        }
    };

    private LBiObjBoolPredicateX<T1,T2,X> opposite = new LBiObjBoolPredicateX(){
        public  boolean doTest(Object a1,Object a2,boolean a3) throws ParseException {
            return testValue;
        }
    };




    private LBiObjBoolPredicate<T1,T2> sutAlwaysThrowingUnckeck = LBiObjBoolPredicate.l((T1 a1,T2 a2,boolean a3) -> {
            throw new IndexOutOfBoundsException(ORIGINAL_MESSAGE);
    });


    @Test
    public void testTheResult() throws X {
        assertThat(sut.doTest((T1)Integer.valueOf(100),(T2)Integer.valueOf(100),true))
            .isEqualTo(testValue);
    }

    @Test
    public void testTupleCall() throws X {

        //FunctionalCall<LBiObjBoolTriple<T1,T2>,Boolean,RuntimeException> theCall = sut;

        LBiObjBoolTriple<T1,T2> domainObject = Tuple4U.tuple((T1)Integer.valueOf(100),(T2)Integer.valueOf(100),true);

        Object result = sut.tupleTest(domainObject);

        assertThat(result)
            .isEqualTo(testValue);
    }

    @Test
    public void testNonNullDoTest() throws X {
        assertThat(sut.nonNullDoTest((T1)Integer.valueOf(100),(T2)Integer.valueOf(100),true))
            .isEqualTo(testValue);
    }

    @Test
    public void testNestingDoTestUnckeck() throws X {

        // then
        try {
            sutAlwaysThrowingUnckeck.nestingDoTest((T1)Integer.valueOf(100),(T2)Integer.valueOf(100),true);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                    .hasNoCause()
                    .hasMessage(ORIGINAL_MESSAGE);
        }
    }

    @Test
    public void testShovingDoTestUnckeck() throws X {

        // then
        try {
            sutAlwaysThrowingUnckeck.shovingDoTest((T1)Integer.valueOf(100),(T2)Integer.valueOf(100),true);
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
        assertThat(sut.doApplyAsBoolean((T1)Integer.valueOf(100),(T2)Integer.valueOf(100),true))
            .isEqualTo(testValue);

    }


    @Test
    public void testFunctionalInterfaceDescription() throws X {
        assertThat(sut.functionalInterfaceDescription())
            .isEqualTo("LBiObjBoolPredicate: boolean doTest(T1 a1,T2 a2,boolean a3)");
    }

    @Test
    public void testLMethod() throws X {
        assertThat(LBiObjBoolPredicate.l((Object a1,Object a2,boolean a3) -> testValue ))
            .isInstanceOf(LBiObjBoolPredicate.class);
    }

    @Test
    public void testWrapMethod() throws X {
        assertThat(LBiObjBoolPredicate.wrap(opposite))
            .isInstanceOf(LBiObjBoolPredicate.class);
    }

    @Test
    public void testWrapMethodDoNotWrapsRuntimeException() throws X {
        // given
        LBiObjBoolPredicateX<T1,T2,X> sutThrowing = LBiObjBoolPredicateX.lX((T1 a1,T2 a2,boolean a3) -> {
            throw new UnsupportedOperationException(ORIGINAL_MESSAGE);
        });

        // when
        LBiObjBoolPredicate<T1,T2> wrapped = LBiObjBoolPredicate.wrap(sutThrowing);

        // then
        try {
            wrapped.doTest((T1)Integer.valueOf(100),(T2)Integer.valueOf(100),true);
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
        LBiObjBoolPredicateX<T1,T2,ParseException> sutThrowing = LBiObjBoolPredicateX.lX((T1 a1,T2 a2,boolean a3) -> {
            throw new ParseException(ORIGINAL_MESSAGE, 0);
        });

        // when
        LBiObjBoolPredicate<T1,T2> wrapped = LBiObjBoolPredicate.wrap(sutThrowing);

        // then
        try {
            wrapped.doTest((T1)Integer.valueOf(100),(T2)Integer.valueOf(100),true);
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
        LBiObjBoolPredicate<T1,T2> sutThrowing = LBiObjBoolPredicate.l((T1 a1,T2 a2,boolean a3) -> {
            throw new UnsupportedOperationException();
        });

        // when
        LBiObjBoolPredicate<T1,T2> wrapped = sutThrowing.handleBiObjBoolPred(handler -> handler
            .wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED));

        // then
        try {
            wrapped.doTest((T1)Integer.valueOf(100),(T2)Integer.valueOf(100),true);
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
        LBiObjBoolPredicate<T1,T2> sutThrowing = LBiObjBoolPredicate.l((T1 a1,T2 a2,boolean a3) -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LBiObjBoolPredicate<T1,T2> wrapped = sutThrowing.handleBiObjBoolPred(handler -> handler
                .wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED)
                .throwIf(IndexOutOfBoundsException.class));

        // then
        try {
            wrapped.doTest((T1)Integer.valueOf(100),(T2)Integer.valueOf(100),true);
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
        LBiObjBoolPredicate<T1,T2> sutThrowing = LBiObjBoolPredicate.l((T1 a1,T2 a2,boolean a3) -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LBiObjBoolPredicate<T1,T2> wrapped = sutThrowing.handleBiObjBoolPred(handler -> handler
                .wrapWhen(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED)
                .throwIf(IndexOutOfBoundsException.class));

        // then
        try {
            wrapped.doTest((T1)Integer.valueOf(100),(T2)Integer.valueOf(100),true);
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
        LBiObjBoolPredicate<T1,T2> sutThrowing = LBiObjBoolPredicate.l((T1 a1,T2 a2,boolean a3) -> {
            throw new UnsupportedOperationException(ORIGINAL_MESSAGE);
        });

        // when
        LBiObjBoolPredicate<T1,T2> wrapped = sutThrowing.handleBiObjBoolPred(h -> Function4U.doNothing());

        // then
        try {
            wrapped.doTest((T1)Integer.valueOf(100),(T2)Integer.valueOf(100),true);
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
        assertThat(sut.negate().doTest((T1)Integer.valueOf(100),(T2)Integer.valueOf(100),true))
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
        LBiObjBoolPredicate<T1,T2> fun1 = LBiObjBoolPredicate.l((T1 a1,T2 a2,boolean a3) -> f1Result);
        LBiObjBoolPredicate<T1,T2> fun2 = LBiObjBoolPredicate.l((T1 a1,T2 a2,boolean a3) -> f2Result);

        //when
        LBiObjBoolPredicate<T1,T2> andFunction = fun1.and(fun2);
        LBiObjBoolPredicate<T1,T2> orFunction = fun1.or(fun2);
        LBiObjBoolPredicate<T1,T2> xorFunction = fun1.xor(fun2);

        //then
        assertThat(andFunction.doTest((T1)Integer.valueOf(100),(T2)Integer.valueOf(100),true))
                .isEqualTo(andResult);

        assertThat(orFunction.doTest((T1)Integer.valueOf(100),(T2)Integer.valueOf(100),true))
                .isEqualTo(orResult);

        assertThat(xorFunction.doTest((T1)Integer.valueOf(100),(T2)Integer.valueOf(100),true))
                .isEqualTo(xorResult);
    }

    @Test
    public void isEqual() throws X  {
        //when
        LBiObjBoolPredicate<T1,T2> equals = LBiObjBoolPredicate.isEqual((T1)Integer.valueOf(100),(T2)Integer.valueOf(100),true);

        //then
        assertThat(equals.doTest((T1)Integer.valueOf(100),(T2)Integer.valueOf(100),true))
                .isTrue();

        assertThat(equals.doTest((T1)Integer.valueOf(0),(T2)Integer.valueOf(0),false))
                .isFalse();
    }



    // <editor-fold desc="compose (functional)">

    @Test
    public void testbiObjBoolPredComposeBoolean() throws X {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LBiObjBoolPredicate<Integer ,Integer > sutO = (Integer a1,Integer a2,boolean a3) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo((T1)Integer.valueOf(90));
                assertThat(a2).isEqualTo((T2)Integer.valueOf(91));
                assertThat(a3).isEqualTo(true);
                return true;
        };

        LFunction<Integer ,Integer > before1 = p0 -> {
            assertThat(p0).isEqualTo((T1)Integer.valueOf(80));
            beforeCalls.incrementAndGet();
            return 90;
        };
        LFunction<Integer ,Integer > before2 = p1 -> {
            assertThat(p1).isEqualTo((T2)Integer.valueOf(81));
            beforeCalls.incrementAndGet();
            return 91;
        };
        LLogicalOperator before3 = p2 -> {
            assertThat(p2).isEqualTo(true);
            beforeCalls.incrementAndGet();
            return true;
        };

        //when
        LBiObjBoolPredicate<Integer ,Integer > function = sutO.biObjBoolPredComposeBoolean(before1,before2,before3);
        function.doTest((Integer )Integer.valueOf(80),(Integer )Integer.valueOf(81),true);

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(beforeCalls.get()).isEqualTo(3);
    }


    @Test
    public void testbiObjBoolPredCompose() throws X {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LBiObjBoolPredicate<Integer ,Integer > sutO = (Integer a1,Integer a2,boolean a3) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo((T1)Integer.valueOf(90));
                assertThat(a2).isEqualTo((T2)Integer.valueOf(91));
                assertThat(a3).isEqualTo(true);
                return true;
        };

        LFunction<Integer ,Integer > before1 = p0 -> {
            assertThat(p0).isEqualTo(Integer.valueOf(80));
            beforeCalls.incrementAndGet();
            return 90;
        };
        LFunction<Integer ,Integer > before2 = p1 -> {
            assertThat(p1).isEqualTo(Integer.valueOf(81));
            beforeCalls.incrementAndGet();
            return 91;
        };
        LPredicate<Integer > before3 = p2 -> {
            assertThat(p2).isEqualTo(Integer.valueOf(82));
            beforeCalls.incrementAndGet();
            return true;
        };

        //when
        LTriPredicate<Integer ,Integer ,Integer > function = sutO.biObjBoolPredCompose(before1,before2,before3);
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
        LBiObjBoolPredicate<Integer ,Integer > sutO = (Integer a1,Integer a2,boolean a3) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo((T1)Integer.valueOf(80));
                assertThat(a2).isEqualTo((T2)Integer.valueOf(81));
                assertThat(a3).isEqualTo(true);
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
        LBiObjBoolFunction<Integer ,Integer ,Integer > function = sutO.boolToBiObjBoolFunction(thenFunction);
        Integer  finalValue = function.doApply((Integer )Integer.valueOf(80),(Integer )Integer.valueOf(81),true);

        //then - finals
        assertThat(finalValue).isEqualTo(Integer.valueOf(100));
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    // </editor-fold>

    @Test
    public void testNesting() {
        assertThat(sut.nestingBiObjBoolPred())
            .isSameAs(sut)
            .isInstanceOf(LBiObjBoolPredicate.class);
    }

    @Test
    public void testShoving() {
        assertThat(sut.shovingBiObjBoolPred())
            .isSameAs(sut)
            .isInstanceOf(LBiObjBoolPredicate.class);
    }

    @Test
    public void testNestingX() {
        assertThat(sut.nestingBiObjBoolPredX())
            .isSameAs(sut)
            .isInstanceOf(LBiObjBoolPredicateX.class);
    }

    @Test
    public void testShovingX() {
        assertThat(sut.shovingBiObjBoolPredX())
            .isSameAs(sut)
            .isInstanceOf(LBiObjBoolPredicateX.class);
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void testShove() {

        // given
        LBiObjBoolPredicate<T1,T2> sutThrowing = LBiObjBoolPredicate.l((T1 a1,T2 a2,boolean a3) -> {
            throw new UnsupportedOperationException();
        });

        // when
        sutThrowing.shovingBiObjBoolPred().doTest((T1)Integer.valueOf(100),(T2)Integer.valueOf(100),true);
    }

    @Test
    public void testHandleBiObjBoolPred() throws X {

        // given
        LBiObjBoolPredicate<T1,T2> sutThrowing = LBiObjBoolPredicate.l((T1 a1,T2 a2,boolean a3) -> {
            throw new UnsupportedOperationException();
        });

        // when
        LBiObjBoolPredicate<T1,T2> wrapped = sutThrowing.handleBiObjBoolPred(h -> {
            h.wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED);
        });

        // then
        try {
            wrapped.doTest((T1)Integer.valueOf(100),(T2)Integer.valueOf(100),true);
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
                .contains("LBiObjBoolPredicate: boolean doTest(T1 a1,T2 a2,boolean a3)");
    }


    @Test
    public void isThrowing() {
        assertThat(sut.isThrowing())
            .isFalse();
    }

    //<editor-fold desc="Variants">

    private boolean variant1(T1 a1,boolean a3,T2 a2) {
        return true;
    }

    @Test
    public void compilerSubstituteVariant1() {
        LBiObjBoolPredicate lambda = LBiObjBoolPredicate./*<T1,T2>*/l1(this::variant1);

        assertThat(lambda).isInstanceOf(LBiObjBoolPredicate.V1.class);
    }


    private boolean variant2(T2 a2,T1 a1,boolean a3) {
        return true;
    }

    @Test
    public void compilerSubstituteVariant2() {
        LBiObjBoolPredicate lambda = LBiObjBoolPredicate./*<T1,T2>*/l2(this::variant2);

        assertThat(lambda).isInstanceOf(LBiObjBoolPredicate.V2.class);
    }


    private boolean variant3(T2 a2,boolean a3,T1 a1) {
        return true;
    }

    @Test
    public void compilerSubstituteVariant3() {
        LBiObjBoolPredicate lambda = LBiObjBoolPredicate./*<T1,T2>*/l3(this::variant3);

        assertThat(lambda).isInstanceOf(LBiObjBoolPredicate.V3.class);
    }


    private boolean variant4(boolean a3,T1 a1,T2 a2) {
        return true;
    }

    @Test
    public void compilerSubstituteVariant4() {
        LBiObjBoolPredicate lambda = LBiObjBoolPredicate./*<T1,T2>*/l4(this::variant4);

        assertThat(lambda).isInstanceOf(LBiObjBoolPredicate.V4.class);
    }


    private boolean variant5(boolean a3,T2 a2,T1 a1) {
        return true;
    }

    @Test
    public void compilerSubstituteVariant5() {
        LBiObjBoolPredicate lambda = LBiObjBoolPredicate./*<T1,T2>*/l5(this::variant5);

        assertThat(lambda).isInstanceOf(LBiObjBoolPredicate.V5.class);
    }

    //</editor-fold>


    @Test void safeCompiles() {
        LBiObjBoolPredicate r1 = LBiObjBoolPredicate.safe(sut);
        LBiObjBoolPredicateX r2 = LBiObjBoolPredicate.safe(sut);
    }

    @Test void safePropagates() {
        Object result = LBiObjBoolPredicate.safe(sut);
        assertThat(result).isSameAs(sut);
    }

    @Test void safeProtectsAgainstNpe() {
        Object result = LBiObjBoolPredicate.safe(null);
        assertThat(result).isSameAs(LBiObjBoolPredicate.l(LBiObjBoolPredicate.safe()));
    }

    @Test  void safeSupplierPropagates() {
        LSupplier<LBiObjBoolPredicate<T1,T2>> supplier = ()->sut;
        Object result = LBiObjBoolPredicate.safeSupplier(supplier);
        assertThat(result).isSameAs(supplier);
    }

    @Test  void safeSupplierProtectsAgainstNpe() {
        Object result = LBiObjBoolPredicate.safeSupplier(null);
        assertThat(result).isSameAs(LBiObjBoolPredicate.safeSupplier());
    }

    @Test  void safeSupplierCompiles() {
        LSupplier<LBiObjBoolPredicate<T1,T2>> r1 = LBiObjBoolPredicate.safeSupplier(()->sut);
    }

}
