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
public class LObjBoolPredicateTest<T,X extends ParseException> {
    private static final String ORIGINAL_MESSAGE = "Original message";
    private static final String EXCEPTION_WAS_WRAPPED = "Exception was wrapped.";
    private static final String NO_EXCEPTION_WERE_THROWN = "No exception were thrown.";

    private boolean testValue = true;



    private LObjBoolPredicate<T> sut = new LObjBoolPredicate(){
        public  boolean doTest(Object a1,boolean a2)  {
            return testValue;
        }
    };

    private LObjBoolPredicateX<T,X> opposite = new LObjBoolPredicateX(){
        public  boolean doTest(Object a1,boolean a2) throws ParseException {
            return testValue;
        }
    };




    private LObjBoolPredicate<T> sutAlwaysThrowingUnckeck = LObjBoolPredicate.l((T a1,boolean a2) -> {
            throw new IndexOutOfBoundsException(ORIGINAL_MESSAGE);
    });


    @Test
    public void testTheResult() throws X {
        assertThat(sut.doTest((T)Integer.valueOf(100),true))
            .isEqualTo(testValue);
    }

    @Test
    public void testTupleCall() throws X {

        LObjBoolPair<T> domainObject = Tuple4U.tuple((T)Integer.valueOf(100),true);

        Object result = sut.tupleTest(domainObject);

        assertThat(result)
            .isEqualTo(testValue);
    }

    @Test
    public void testNonNullDoTest() throws X {
        assertThat(sut.nonNullDoTest((T)Integer.valueOf(100),true))
            .isEqualTo(testValue);
    }

    @Test
    public void testNestingDoTestUnckeck() throws X {

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
    public void testShovingDoTestUnckeck() throws X {

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
    public void testApplyAsBooleanShouldNotModifyValue() throws X {
        assertThat(sut.doApplyAsBoolean((T)Integer.valueOf(100),true))
            .isEqualTo(testValue);

    }


    @Test
    public void testFunctionalInterfaceDescription() throws X {
        assertThat(sut.functionalInterfaceDescription())
            .isEqualTo("LObjBoolPredicate: boolean doTest(T a1,boolean a2)");
    }

    @Test
    public void testLMethod() throws X {
        assertThat(LObjBoolPredicate.l((Object a1,boolean a2) -> testValue ))
            .isInstanceOf(LObjBoolPredicate.class);
    }

    @Test
    public void testWrapMethod() throws X {
        assertThat(LObjBoolPredicate.wrap(opposite))
            .isInstanceOf(LObjBoolPredicate.class);
    }

    @Test
    public void testWrapMethodDoNotWrapsRuntimeException() throws X {
        // given
        LObjBoolPredicateX<T,X> sutThrowing = LObjBoolPredicateX.lX((T a1,boolean a2) -> {
            throw new UnsupportedOperationException(ORIGINAL_MESSAGE);
        });

        // when
        LObjBoolPredicate<T> wrapped = LObjBoolPredicate.wrap(sutThrowing);

        // then
        try {
            wrapped.doTest((T)Integer.valueOf(100),true);
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
        LObjBoolPredicateX<T,ParseException> sutThrowing = LObjBoolPredicateX.lX((T a1,boolean a2) -> {
            throw new ParseException(ORIGINAL_MESSAGE, 0);
        });

        // when
        LObjBoolPredicate<T> wrapped = LObjBoolPredicate.wrap(sutThrowing);

        // then
        try {
            wrapped.doTest((T)Integer.valueOf(100),true);
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
        LObjBoolPredicate<T> sutThrowing = LObjBoolPredicate.l((T a1,boolean a2) -> {
            throw new UnsupportedOperationException();
        });

        // when
        LObjBoolPredicate<T> wrapped = sutThrowing.handleObjBoolPred(handler -> handler
            .wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED));

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
    public void testWrapExceptionMethodDoNotWrapsOtherExceptionIf() throws X {

        // given
        LObjBoolPredicate<T> sutThrowing = LObjBoolPredicate.l((T a1,boolean a2) -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LObjBoolPredicate<T> wrapped = sutThrowing.handleObjBoolPred(handler -> handler
                .wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED)
                .throwIf(IndexOutOfBoundsException.class));

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
    public void testWrapExceptionMethodDoNotWrapsOtherExceptionWhen() throws X {

        // given
        LObjBoolPredicate<T> sutThrowing = LObjBoolPredicate.l((T a1,boolean a2) -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LObjBoolPredicate<T> wrapped = sutThrowing.handleObjBoolPred(handler -> handler
                .wrapWhen(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED)
                .throwIf(IndexOutOfBoundsException.class));

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
    public void testWrapExceptionMishandlingExceptionIsAllowed() throws X {

        // given
        LObjBoolPredicate<T> sutThrowing = LObjBoolPredicate.l((T a1,boolean a2) -> {
            throw new UnsupportedOperationException(ORIGINAL_MESSAGE);
        });

        // when
        LObjBoolPredicate<T> wrapped = sutThrowing.handleObjBoolPred(h -> Function4U.doNothing());

        // then
        try {
            wrapped.doTest((T)Integer.valueOf(100),true);
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
    public void testAndOrXor(final boolean f1Result, final boolean f2Result, final boolean andResult, final boolean orResult, final boolean xorResult) throws X {

        //given
        LObjBoolPredicate<T> fun1 = LObjBoolPredicate.l((T a1,boolean a2) -> f1Result);
        LObjBoolPredicate<T> fun2 = LObjBoolPredicate.l((T a1,boolean a2) -> f2Result);

        //when
        LObjBoolPredicate<T> andFunction = fun1.and(fun2);
        LObjBoolPredicate<T> orFunction = fun1.or(fun2);
        LObjBoolPredicate<T> xorFunction = fun1.xor(fun2);

        //then
        assertThat(andFunction.doTest((T)Integer.valueOf(100),true))
                .isEqualTo(andResult);

        assertThat(orFunction.doTest((T)Integer.valueOf(100),true))
                .isEqualTo(orResult);

        assertThat(xorFunction.doTest((T)Integer.valueOf(100),true))
                .isEqualTo(xorResult);
    }

    @Test
    public void isEqual() throws X  {
        //when
        LObjBoolPredicate<T> equals = LObjBoolPredicate.isEqual((T)Integer.valueOf(100),true);

        //then
        assertThat(equals.doTest((T)Integer.valueOf(100),true))
                .isTrue();

        assertThat(equals.doTest((T)Integer.valueOf(0),false))
                .isFalse();
    }



    // <editor-fold desc="compose (functional)">

    @Test
    public void testobjBoolPredComposeBoolean() throws X {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LObjBoolPredicate<Integer > sutO = (Integer a1,boolean a2) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo((T)Integer.valueOf(90));
                assertThat(a2).isEqualTo(true);
                return true;
        };

        LFunction<Integer ,Integer > before1 = p0 -> {
            assertThat(p0).isEqualTo((T)Integer.valueOf(80));
            beforeCalls.incrementAndGet();
            return 90;
        };
        LLogicalOperator before2 = p1 -> {
            assertThat(p1).isEqualTo(true);
            beforeCalls.incrementAndGet();
            return true;
        };

        //when
        LObjBoolPredicate<Integer > function = sutO.objBoolPredComposeBoolean(before1,before2);
        function.doTest((Integer )Integer.valueOf(80),true);

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(beforeCalls.get()).isEqualTo(2);
    }


    @Test
    public void testobjBoolPredCompose() throws X {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LObjBoolPredicate<Integer > sutO = (Integer a1,boolean a2) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo((T)Integer.valueOf(90));
                assertThat(a2).isEqualTo(true);
                return true;
        };

        LFunction<Integer ,Integer > before1 = p0 -> {
            assertThat(p0).isEqualTo(Integer.valueOf(80));
            beforeCalls.incrementAndGet();
            return 90;
        };
        LPredicate<Integer > before2 = p1 -> {
            assertThat(p1).isEqualTo(Integer.valueOf(81));
            beforeCalls.incrementAndGet();
            return true;
        };

        //when
        LBiPredicate<Integer ,Integer > function = sutO.objBoolPredCompose(before1,before2);
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
        LObjBoolPredicate<Integer > sutO = (Integer a1,boolean a2) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo((T)Integer.valueOf(80));
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
        LObjBoolFunction<Integer ,Integer > function = sutO.boolToObjBoolFunction(thenFunction);
        Integer  finalValue = function.doApply((Integer )Integer.valueOf(80),true);

        //then - finals
        assertThat(finalValue).isEqualTo(Integer.valueOf(100));
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    // </editor-fold>

    @Test
    public void testNesting() {
        assertThat(sut.nestingObjBoolPred())
            .isSameAs(sut)
            .isInstanceOf(LObjBoolPredicate.class);
    }

    @Test
    public void testShoving() {
        assertThat(sut.shovingObjBoolPred())
            .isSameAs(sut)
            .isInstanceOf(LObjBoolPredicate.class);
    }

    @Test
    public void testNestingX() {
        assertThat(sut.nestingObjBoolPredX())
            .isSameAs(sut)
            .isInstanceOf(LObjBoolPredicateX.class);
    }

    @Test
    public void testShovingX() {
        assertThat(sut.shovingObjBoolPredX())
            .isSameAs(sut)
            .isInstanceOf(LObjBoolPredicateX.class);
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void testShove() {

        // given
        LObjBoolPredicate<T> sutThrowing = LObjBoolPredicate.l((T a1,boolean a2) -> {
            throw new UnsupportedOperationException();
        });

        // when
        sutThrowing.shovingObjBoolPred().doTest((T)Integer.valueOf(100),true);
    }

    @Test
    public void testHandleObjBoolPred() throws X {

        // given
        LObjBoolPredicate<T> sutThrowing = LObjBoolPredicate.l((T a1,boolean a2) -> {
            throw new UnsupportedOperationException();
        });

        // when
        LObjBoolPredicate<T> wrapped = sutThrowing.handleObjBoolPred(h -> {
            h.wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED);
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
    public void testToString() throws X {

        assertThat(sut.toString())
                .isInstanceOf(String.class)
                .startsWith(this.getClass().getName()+"$");

        assertThat(String.format("%s", sut))
                .isInstanceOf(String.class)
                .contains("LObjBoolPredicate: boolean doTest(T a1,boolean a2)");
    }


    @Test
    public void isThrowing() {
        assertThat(sut.isThrowing())
            .isFalse();
    }

    //<editor-fold desc="Variants">

    private boolean variant1(boolean a2,T a1) {
        return true;
    }

    @Test
    public void compilerSubstituteVariant1() {
        LObjBoolPredicate lambda = LObjBoolPredicate./*<T>*/l1(this::variant1);

        assertThat(lambda).isInstanceOf(LObjBoolPredicate.V1.class);
    }

    //</editor-fold>


    @Test void safeCompiles() {
        LObjBoolPredicate r1 = LObjBoolPredicate.safe(sut); //NOSONAR
        LObjBoolPredicateX r2 = LObjBoolPredicate.safe(sut); //NOSONAR
    }

    @Test void safePropagates() {
        Object result = LObjBoolPredicate.safe(sut);
        assertThat(result).isSameAs(sut);
    }

    @Test void safeProtectsAgainstNpe() {
        Object result = LObjBoolPredicate.safe(null);
        assertThat(result).isSameAs(LObjBoolPredicate.l(LObjBoolPredicate.safe()));
    }

    @Test  void safeSupplierPropagates() {
        LSupplier<LObjBoolPredicate<T>> supplier = ()->sut;
        Object result = LObjBoolPredicate.safeSupplier(supplier);
        assertThat(result).isSameAs(supplier);
    }

    @Test  void safeSupplierProtectsAgainstNpe() {
        Object result = LObjBoolPredicate.safeSupplier(null);
        assertThat(result).isSameAs(LObjBoolPredicate.safeSupplier());
    }

    @Test  void safeSupplierCompiles() {
        LSupplier<LObjBoolPredicate<T>> r1 = LObjBoolPredicate.safeSupplier(()->sut);  //NOSONAR
    }

}
