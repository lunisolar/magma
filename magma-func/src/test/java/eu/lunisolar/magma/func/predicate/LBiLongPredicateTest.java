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
public class LBiLongPredicateTest<X extends ParseException> {
    private static final String ORIGINAL_MESSAGE = "Original message";
    private static final String EXCEPTION_WAS_WRAPPED = "Exception was wrapped.";
    private static final String NO_EXCEPTION_WERE_THROWN = "No exception were thrown.";

    private boolean testValue = true;



    private LBiLongPredicate sut = new LBiLongPredicate(){
        public  boolean doTest(long a1,long a2)  {
            return testValue;
        }
    };

    private LBiLongPredicateX<X> opposite = new LBiLongPredicateX<X>(){
        public  boolean doTest(long a1,long a2)  throws X {
            return testValue;
        }
    };




    private LBiLongPredicateX<RuntimeException> sutAlwaysThrowingUnchecked = LBiLongPredicate.l((a1,a2) -> {
            throw new IndexOutOfBoundsException(ORIGINAL_MESSAGE);
    });


    @Test
    public void testTheResult() throws X {
        assertThat(sut.doTest(100L,100L))
            .isEqualTo(testValue);
    }

    @Test
    public void testTupleCall() throws X {

        LLongPair domainObject = Tuple4U.longPair(100L,100L);

        Object result = sut.tupleTest(domainObject);

        assertThat(result)
            .isEqualTo(testValue);
    }

    @Test
    public void testNonNullDoTest() throws X {
        assertThat(sut.nonNullDoTest(100L,100L))
            .isEqualTo(testValue);
    }

    @Test
    public void testNestingDoTestUnchecked() throws X {

        // then
        try {
            sutAlwaysThrowingUnchecked.nestingDoTest(100L,100L);
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
            sutAlwaysThrowingUnchecked.shovingDoTest(100L,100L);
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
        assertThat(sut.doApplyAsBoolean(100L,100L))
            .isEqualTo(testValue);

    }


    @Test
    public void testFunctionalInterfaceDescription() throws X {
        assertThat(sut.functionalInterfaceDescription())
            .isEqualTo("LBiLongPredicate: boolean doTest(long a1,long a2)");
    }

    @Test
    public void testLMethod() throws X {
        assertThat(LBiLongPredicate.l((a1,a2) -> testValue ))
            .isInstanceOf(LBiLongPredicate.class);
    }

    @Test
    public void testWrapMethod() throws X {
        assertThat(LBiLongPredicate.wrap(opposite))
            .isInstanceOf(LBiLongPredicate.class);
    }

    @Test
    public void testWrapMethodDoNotWrapsRuntimeException() throws X {
        // given
        LBiLongPredicateX<X> sutThrowing = LBiLongPredicateX.lX((a1,a2) -> {
            throw new UnsupportedOperationException(ORIGINAL_MESSAGE);
        });

        // when
        LBiLongPredicate wrapped = LBiLongPredicate.wrap(sutThrowing);

        // then
        try {
            wrapped.doTest(100L,100L);
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
        LBiLongPredicateX<ParseException> sutThrowing = LBiLongPredicateX.lX((a1,a2) -> {
            throw new ParseException(ORIGINAL_MESSAGE, 0);
        });

        // when
        LBiLongPredicate wrapped = LBiLongPredicate.wrap(sutThrowing);

        // then
        try {
            wrapped.doTest(100L,100L);
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
        LBiLongPredicate sutThrowing = LBiLongPredicate.l((a1,a2) -> {
            throw new UnsupportedOperationException();
        });

        // when
        LBiLongPredicate wrapped = sutThrowing.handleBiLongPred(handler -> handler
            .wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED));

        // then
        try {
            wrapped.doTest(100L,100L);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IllegalArgumentException.class)
                    .hasCauseExactlyInstanceOf(UnsupportedOperationException.class)
                    .hasMessage(EXCEPTION_WAS_WRAPPED);
        }
    }

    @Test
    public void testHandleBiLongPredMethodDoNotWrapsOtherExceptionIf() throws X {

        // given
        LBiLongPredicate sutThrowing = LBiLongPredicate.l((a1,a2) -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LBiLongPredicate wrapped = sutThrowing.handleBiLongPred(handler -> handler
                .wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED)
                .throwIf(IndexOutOfBoundsException.class));

        // then
        try {
            wrapped.doTest(100L,100L);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                    .hasNoCause();
        }
    }

@Test
    public void testHandleBiLongPredMethodDoNotWrapsOtherExceptionWhen() throws X {

        // given
        LBiLongPredicate sutThrowing = LBiLongPredicate.l((a1,a2) -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LBiLongPredicate wrapped = sutThrowing.handleBiLongPred(handler -> handler
                .wrapWhen(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED)
                .throwIf(IndexOutOfBoundsException.class));

        // then
        try {
            wrapped.doTest(100L,100L);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                    .hasNoCause();
        }
    }


    @Test
    public void testHandleBiLongPredMishandlingExceptionIsAllowed() throws X {

        // given
        LBiLongPredicate sutThrowing = LBiLongPredicate.l((a1,a2) -> {
            throw new UnsupportedOperationException(ORIGINAL_MESSAGE);
        });

        // when
        LBiLongPredicate wrapped = sutThrowing.handleBiLongPred(h -> Function4U.doNothing());

        // then
        try {
            wrapped.doTest(100L,100L);
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
        assertThat(sut.negate().doTest(100L,100L))
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
        LBiLongPredicate fun1 = LBiLongPredicate.l((a1,a2) -> f1Result);
        LBiLongPredicate fun2 = LBiLongPredicate.l((a1,a2) -> f2Result);

        //when
        LBiLongPredicate andFunction = fun1.and(fun2);
        LBiLongPredicate orFunction = fun1.or(fun2);
        LBiLongPredicate xorFunction = fun1.xor(fun2);

        //then
        assertThat(andFunction.doTest(100L,100L))
                .isEqualTo(andResult);

        assertThat(orFunction.doTest(100L,100L))
                .isEqualTo(orResult);

        assertThat(xorFunction.doTest(100L,100L))
                .isEqualTo(xorResult);
    }

    @Test
    public void testIsEqual() throws X  {
        //when
        LBiLongPredicate equals = LBiLongPredicate.isEqual(1L,1L);

        //then
        assertThat(equals.doTest(1L,1L))
                .isTrue();

        assertThat(equals.doTest(0L,0L))
                .isFalse();
    }



    // <editor-fold desc="compose (functional)">

    @Test
    public void testBiLongPredComposeLong() throws X {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LBiLongPredicate sutO = (a1,a2) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(90L);
                assertThat(a2).isEqualTo(91L);
                return true;
        };

        LLongUnaryOperator before1 = p0 -> {
            assertThat(p0).isEqualTo(80L);
            beforeCalls.incrementAndGet();
            return 90L;
        };
        LLongUnaryOperator before2 = p1 -> {
            assertThat(p1).isEqualTo(81L);
            beforeCalls.incrementAndGet();
            return 91L;
        };

        //when
        LBiLongPredicate function = sutO.biLongPredComposeLong(before1,before2);
        function.doTest(80L,81L);

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(beforeCalls.get()).isEqualTo(2);
    }


    @Test
    public void testBiLongPredCompose() throws X {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LBiLongPredicate sutO = (a1,a2) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(90L);
                assertThat(a2).isEqualTo(91L);
                return true;
        };

        LToLongFunction<Integer> before1 = p0 -> {
            assertThat(p0).isEqualTo(80);
            beforeCalls.incrementAndGet();
            return 90L;
        };
        LToLongFunction<Integer> before2 = p1 -> {
            assertThat(p1).isEqualTo(81);
            beforeCalls.incrementAndGet();
            return 91L;
        };

        //when
        LBiPredicate<Integer,Integer> function = sutO.biLongPredCompose(before1,before2);
        function.doTest(80,81);

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(beforeCalls.get()).isEqualTo(2);
    }

    // </editor-fold>



    // <editor-fold desc="then (functional)">

    @Test
    public void testBoolToBiLongFunc0() throws X  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);

        //given (+ some assertions)
        LBiLongPredicate sutO = (a1,a2) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(80L);
                assertThat(a2).isEqualTo(81L);
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
        LBiLongFunction<Integer> function = sutO.boolToBiLongFunc(thenFunction);
        Integer finalValue = function.doApply(80L,81L);

        //then - finals
        assertThat(finalValue).isEqualTo(100);
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    // </editor-fold>

    @Test
    public void testNesting() {
        assertThat(sut.nestingBiLongPred())
            .isSameAs(sut)
            .isInstanceOf(LBiLongPredicate.class);
    }

    @Test
    public void testShoving() {
        assertThat(sut.shovingBiLongPred())
            .isSameAs(sut)
            .isInstanceOf(LBiLongPredicate.class);
    }

    @Test
    public void testNestingX() {
        assertThat(sut.nestingBiLongPredX())
            .isSameAs(sut)
            .isInstanceOf(LBiLongPredicateX.class);
    }

    @Test
    public void testShovingX() {
        assertThat(sut.shovingBiLongPredX())
            .isSameAs(sut)
            .isInstanceOf(LBiLongPredicateX.class);
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void testShove() {

        // given
        LBiLongPredicate sutThrowing = LBiLongPredicate.l((a1,a2) -> {
            throw new UnsupportedOperationException();
        });

        // when
        sutThrowing.shovingBiLongPred().doTest(100L,100L);
    }

    @Test
    public void testHandleBiLongPred() throws X {

        // given
        LBiLongPredicate sutThrowing = LBiLongPredicate.l((a1,a2) -> {
            throw new UnsupportedOperationException();
        });

        // when
        LBiLongPredicate wrapped = sutThrowing.handleBiLongPred(h -> {
            h.wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED);
        });

        // then
        try {
            wrapped.doTest(100L,100L);
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
                .contains("LBiLongPredicate: boolean doTest(long a1,long a2)");
    }


    @Test
    public void isThrowing() {
        assertThat(sut.isThrowing())
            .isFalse();
    }

    //<editor-fold desc="Variants">

    private boolean variantV1(long a2,long a1) {
        return true;
    }

    @Test
    public void compilerSubstituteVariantV1() {
        LBiLongPredicate lambda = LBiLongPredicate./**/l1(this::variantV1);

        assertThat(lambda).isInstanceOf(LBiLongPredicate.V1.class);
    }

    //</editor-fold>


    @Test void safeCompiles() {
        LBiLongPredicate r1 = LBiLongPredicate.safe(sut); //NOSONAR
        LBiLongPredicateX r2 = LBiLongPredicate.safe(sut); //NOSONAR
    }

    @Test void safePropagates() {
        Object result = LBiLongPredicate.safe(sut);
        assertThat(result).isSameAs(sut);
    }

    @Test void safeProtectsAgainstNpe() {
        Object result = LBiLongPredicate.safe(null);
        assertThat(result).isSameAs(LBiLongPredicate.l(LBiLongPredicate.safe()));
    }

    @Test  void safeSupplierPropagates() {
        LSupplier<LBiLongPredicate> supplier = ()->sut;
        Object result = LBiLongPredicate.safeSupplier(supplier);
        assertThat(result).isSameAs(supplier);
    }

    @Test  void safeSupplierProtectsAgainstNpe() {
        Object result = LBiLongPredicate.safeSupplier(null);
        assertThat(result).isSameAs(LBiLongPredicate.safeSupplier());
    }

    @Test  void safeSupplierCompiles() {
        LSupplier<LBiLongPredicate> r1 = LBiLongPredicate.safeSupplier(()->sut);  //NOSONAR
    }

}
