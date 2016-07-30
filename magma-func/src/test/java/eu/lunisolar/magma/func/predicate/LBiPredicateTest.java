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

import eu.lunisolar.magma.func.action.*; // NOSONAR
import eu.lunisolar.magma.func.consumer.*; // NOSONAR
import eu.lunisolar.magma.func.consumer.primitives.*; // NOSONAR
import eu.lunisolar.magma.func.consumer.primitives.bi.*; // NOSONAR
import eu.lunisolar.magma.func.consumer.primitives.obj.*; // NOSONAR
import eu.lunisolar.magma.func.consumer.primitives.tri.*; // NOSONAR
import eu.lunisolar.magma.func.function.*; // NOSONAR
import eu.lunisolar.magma.func.function.conversion.*; // NOSONAR
import eu.lunisolar.magma.func.function.from.*; // NOSONAR
import eu.lunisolar.magma.func.function.to.*; // NOSONAR
import eu.lunisolar.magma.func.operator.binary.*; // NOSONAR
import eu.lunisolar.magma.func.operator.ternary.*; // NOSONAR
import eu.lunisolar.magma.func.operator.unary.*; // NOSONAR
import eu.lunisolar.magma.func.predicate.*; // NOSONAR
import eu.lunisolar.magma.func.supplier.*; // NOSONAR

import org.assertj.core.api.Assertions;  //NOSONAR
import org.testng.annotations.*;      //NOSONAR
import java.util.regex.Pattern;          //NOSONAR
import java.text.ParseException;         //NOSONAR
import eu.lunisolar.magma.basics.*; //NOSONAR
import eu.lunisolar.magma.basics.exceptions.*; //NOSONAR
import java.util.concurrent.atomic.AtomicInteger; //NOSONAR
import eu.lunisolar.magma.struct.tuple.*; // NOSONAR
import static org.assertj.core.api.Assertions.*; //NOSONAR
import java.util.function.*; // NOSONAR

/** The test obviously concentrate on the interface methods the function it self is very simple.  */
public class LBiPredicateTest<T1,T2,X extends ParseException> {
    private static final String ORIGINAL_MESSAGE = "Original message";
    private static final String EXCEPTION_WAS_WRAPPED = "Exception was wrapped.";
    private static final String NO_EXCEPTION_WERE_THROWN = "No exception were thrown.";

    private boolean testValue = true;



    private LBiPredicate<Integer,Integer> sut = new LBiPredicate<Integer,Integer>(){
        public  boolean doTest(Integer a1,Integer a2)  {
            return testValue;
        }
    };

    private LBiPredicateX<Integer,Integer,X> opposite = new LBiPredicateX<Integer,Integer,X>(){
        public  boolean doTest(Integer a1,Integer a2)  throws X {
            return testValue;
        }
    };


    private BiPredicate<Integer,Integer> jre = (a1,a2) -> testValue;



    private LBiPredicateX<Integer,Integer,RuntimeException> sutAlwaysThrowingUnchecked = LBiPredicate.l((a1,a2) -> {
            throw new IndexOutOfBoundsException(ORIGINAL_MESSAGE);
    });


    @Test
    public void testTheResult() throws X {
        assertThat(sut.doTest(100,100))
            .isEqualTo(testValue);
    }

    @Test
    public void testTupleCall() throws X {

        LPair<Integer,Integer> domainObject = Tuple4U.pair(100,100);

        Object result = sut.tupleTest(domainObject);

        assertThat(result)
            .isEqualTo(testValue);
    }

    @Test
    public void testNonNullDoTest() throws X {
        assertThat(sut.nonNullDoTest(100,100))
            .isEqualTo(testValue);
    }

    @Test
    public void testNestingDoTestUnchecked() throws X {

        // then
        try {
            sutAlwaysThrowingUnchecked.nestingDoTest(100,100);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                    .hasNoCause()
                    .hasMessage(ORIGINAL_MESSAGE);
        }
    }

    @Test
    public void testShovingDoTestUnchecked() throws X {

        // then
        try {
            sutAlwaysThrowingUnchecked.shovingDoTest(100,100);
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
        assertThat(sut.doApplyAsBoolean(100,100))
            .isEqualTo(testValue);

    }


    @Test
    public void testFunctionalInterfaceDescription() throws X {
        assertThat(sut.functionalInterfaceDescription())
            .isEqualTo("LBiPredicate: boolean doTest(T1 a1,T2 a2)");
    }

    @Test
    public void testLMethod() throws X {
        assertThat(LBiPredicate.l((a1,a2) -> testValue ))
            .isInstanceOf(LBiPredicate.class);
    }

    @Test
    public void testWrapMethod() throws X {
        assertThat(LBiPredicate.wrap(opposite))
            .isInstanceOf(LBiPredicate.class);
    }

    @Test
    public void testWrapStdMethod() throws X {
        assertThat(LBiPredicate.wrap(jre))
            .isInstanceOf(LBiPredicate.class);
    }

    @Test
    public void testWrapMethodDoNotWrapsRuntimeException() throws X {
        // given
        LBiPredicateX<Integer,Integer,X> sutThrowing = LBiPredicateX.lX((a1,a2) -> {
            throw new UnsupportedOperationException(ORIGINAL_MESSAGE);
        });

        // when
        LBiPredicate<Integer,Integer> wrapped = LBiPredicate.wrap(sutThrowing);

        // then
        try {
            wrapped.doTest(100,100);
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
        LBiPredicateX<Integer,Integer,ParseException> sutThrowing = LBiPredicateX.lX((a1,a2) -> {
            throw new ParseException(ORIGINAL_MESSAGE, 0);
        });

        // when
        LBiPredicate<Integer,Integer> wrapped = LBiPredicate.wrap(sutThrowing);

        // then
        try {
            wrapped.doTest(100,100);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(NestedException.class)
                    .hasCauseExactlyInstanceOf(ParseException.class)
                    .hasMessage(ORIGINAL_MESSAGE);
        }
    }


    @Test
    public void testHandlingDoTestMethodWrapsTheException() throws X {

        // given
        LBiPredicate<Integer,Integer> sutThrowing = LBiPredicate.l((a1,a2) -> {
            throw new UnsupportedOperationException();
        });

        // when
        LBiPredicate<Integer,Integer> wrapped = sutThrowing.handleBiPred(handler -> handler
            .wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED));

        // then
        try {
            wrapped.doTest(100,100);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IllegalArgumentException.class)
                    .hasCauseExactlyInstanceOf(UnsupportedOperationException.class)
                    .hasMessage(EXCEPTION_WAS_WRAPPED);
        }
    }

    @Test
    public void testHandleBiPredMethodDoNotWrapsOtherExceptionIf() throws X {

        // given
        LBiPredicate<Integer,Integer> sutThrowing = LBiPredicate.l((a1,a2) -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LBiPredicate<Integer,Integer> wrapped = sutThrowing.handleBiPred(handler -> handler
                .wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED)
                .throwIf(IndexOutOfBoundsException.class));

        // then
        try {
            wrapped.doTest(100,100);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                    .hasNoCause();
        }
    }

@Test
    public void testHandleBiPredMethodDoNotWrapsOtherExceptionWhen() throws X {

        // given
        LBiPredicate<Integer,Integer> sutThrowing = LBiPredicate.l((a1,a2) -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LBiPredicate<Integer,Integer> wrapped = sutThrowing.handleBiPred(handler -> handler
                .wrapWhen(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED)
                .throwIf(IndexOutOfBoundsException.class));

        // then
        try {
            wrapped.doTest(100,100);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                    .hasNoCause();
        }
    }


    @Test
    public void testHandleBiPredMishandlingExceptionIsAllowed() throws X {

        // given
        LBiPredicate<Integer,Integer> sutThrowing = LBiPredicate.l((a1,a2) -> {
            throw new UnsupportedOperationException(ORIGINAL_MESSAGE);
        });

        // when
        LBiPredicate<Integer,Integer> wrapped = sutThrowing.handleBiPred(h -> Function4U.doNothing());

        // then
        try {
            wrapped.doTest(100,100);
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
        assertThat(sut.negate().doTest(100,100))
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
        LBiPredicate<Integer,Integer> fun1 = LBiPredicate.l((a1,a2) -> f1Result);
        LBiPredicate<Integer,Integer> fun2 = LBiPredicate.l((a1,a2) -> f2Result);

        //when
        LBiPredicate<Integer,Integer> andFunction = fun1.and(fun2);
        LBiPredicate<Integer,Integer> orFunction = fun1.or(fun2);
        LBiPredicate<Integer,Integer> xorFunction = fun1.xor(fun2);

        //then
        assertThat(andFunction.doTest(100,100))
                .isEqualTo(andResult);

        assertThat(orFunction.doTest(100,100))
                .isEqualTo(orResult);

        assertThat(xorFunction.doTest(100,100))
                .isEqualTo(xorResult);
    }

    @Test
    public void testIsEqual() throws X  {
        //when
        LBiPredicate<Integer,Integer> equals = LBiPredicate.isEqual(1,1);

        //then
        assertThat(equals.doTest(1,1))
                .isTrue();

        assertThat(equals.doTest(0,0))
                .isFalse();
    }



    // <editor-fold desc="compose (functional)">

    @Test
    public void testBiPredCompose() throws X {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LBiPredicate<Integer,Integer> sutO = (a1,a2) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(90);
                assertThat(a2).isEqualTo(91);
                return true;
        };

        LFunction<Integer,Integer> before1 = p0 -> {
            assertThat(p0).isEqualTo(80);
            beforeCalls.incrementAndGet();
            return 90;
        };
        LFunction<Integer,Integer> before2 = p1 -> {
            assertThat(p1).isEqualTo(81);
            beforeCalls.incrementAndGet();
            return 91;
        };

        //when
        LBiPredicate<Integer,Integer> function = sutO.biPredCompose(before1,before2);
        function.doTest(80,81);

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(beforeCalls.get()).isEqualTo(2);
    }

    // </editor-fold>



    // <editor-fold desc="then (functional)">

    @Test
    public void testBoolToBiFunc0() throws X  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);

        //given (+ some assertions)
        LBiPredicate<Integer,Integer> sutO = (a1,a2) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(80);
                assertThat(a2).isEqualTo(81);
                return true;
        };

        LBoolFunction<Integer> thenFunction = p -> {
                thenFunctionCalled.set(true);
                // boolean
                assertThat(p).isEqualTo(true);
                // Integer
                return 100;
        };

        //when
        LBiFunction<Integer,Integer,Integer> function = sutO.boolToBiFunc(thenFunction);
        Integer finalValue = function.doApply(80,81);

        //then - finals
        assertThat(finalValue).isEqualTo(100);
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    // </editor-fold>

    @Test
    public void testNesting() {
        assertThat(sut.nestingBiPred())
            .isSameAs(sut)
            .isInstanceOf(LBiPredicate.class);
    }

    @Test
    public void testShoving() {
        assertThat(sut.shovingBiPred())
            .isSameAs(sut)
            .isInstanceOf(LBiPredicate.class);
    }

    @Test
    public void testNestingX() {
        assertThat(sut.nestingBiPredX())
            .isSameAs(sut)
            .isInstanceOf(LBiPredicateX.class);
    }

    @Test
    public void testShovingX() {
        assertThat(sut.shovingBiPredX())
            .isSameAs(sut)
            .isInstanceOf(LBiPredicateX.class);
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void testShove() {

        // given
        LBiPredicate<Integer,Integer> sutThrowing = LBiPredicate.l((a1,a2) -> {
            throw new UnsupportedOperationException();
        });

        // when
        sutThrowing.shovingBiPred().doTest(100,100);
    }

    @Test
    public void testHandleBiPred() throws X {

        // given
        LBiPredicate<Integer,Integer> sutThrowing = LBiPredicate.l((a1,a2) -> {
            throw new UnsupportedOperationException();
        });

        // when
        LBiPredicate<Integer,Integer> wrapped = sutThrowing.handleBiPred(h -> {
            h.wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED);
        });

        // then
        try {
            wrapped.doTest(100,100);
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
                .contains("LBiPredicate: boolean doTest(T1 a1,T2 a2)");
    }


    @Test
    public void isThrowing() {
        assertThat(sut.isThrowing())
            .isFalse();
    }

    //<editor-fold desc="Variants">

    private boolean variantV1(Integer a2,Integer a1) {
        return true;
    }

    @Test
    public void compilerSubstituteVariantV1() {
        LBiPredicate lambda = LBiPredicate./*<T1,T2>*/l1(this::variantV1);

        assertThat(lambda).isInstanceOf(LBiPredicate.V1.class);
    }

    //</editor-fold>


    @Test void safeCompiles() {
        LBiPredicate r1 = LBiPredicate.safe(sut); //NOSONAR
        LBiPredicateX r2 = LBiPredicate.safe(sut); //NOSONAR
        BiPredicate r3 = LBiPredicate.safe(sut); //NOSONAR
    }

    @Test void safePropagates() {
        Object result = LBiPredicate.safe(sut);
        assertThat(result).isSameAs(sut);
    }

    @Test void safeProtectsAgainstNpe() {
        Object result = LBiPredicate.safe(null);
        assertThat(result).isSameAs(LBiPredicate.l(LBiPredicate.safe()));
    }

    @Test  void safeSupplierPropagates() {
        LSupplier<LBiPredicate<Integer,Integer>> supplier = ()->sut;
        Object result = LBiPredicate.safeSupplier(supplier);
        assertThat(result).isSameAs(supplier);
    }

    @Test  void safeSupplierProtectsAgainstNpe() {
        Object result = LBiPredicate.safeSupplier(null);
        assertThat(result).isSameAs(LBiPredicate.safeSupplier());
    }

    @Test  void safeSupplierCompiles() {
        LSupplier<LBiPredicate<Integer,Integer>> r1 = LBiPredicate.safeSupplier(()->sut);  //NOSONAR
        Supplier<LBiPredicate<Integer,Integer>> r2 = LBiPredicate.safeSupplier(()->sut); //NOSONAR
    }

}
