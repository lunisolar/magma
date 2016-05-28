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
public class LBiShortPredicateTest<X extends ParseException> {
    private static final String ORIGINAL_MESSAGE = "Original message";
    private static final String EXCEPTION_WAS_WRAPPED = "Exception was wrapped.";
    private static final String NO_EXCEPTION_WERE_THROWN = "No exception were thrown.";

    private boolean testValue = true;



    private LBiShortPredicate sut = new LBiShortPredicate(){
        public  boolean doTest(short a1,short a2)  {
            return testValue;
        }
    };

    private LBiShortPredicateX<X> opposite = new LBiShortPredicateX<X>(){
        public  boolean doTest(short a1,short a2)  throws X {
            return testValue;
        }
    };




    private LBiShortPredicateX<RuntimeException> sutAlwaysThrowingUnchecked = LBiShortPredicate.l((a1,a2) -> {
            throw new IndexOutOfBoundsException(ORIGINAL_MESSAGE);
    });


    @Test
    public void testTheResult() throws X {
        assertThat(sut.doTest((short)100,(short)100))
            .isEqualTo(testValue);
    }

    @Test
    public void testTupleCall() throws X {

        LShortPair domainObject = Tuple4U.shortPair((short)100,(short)100);

        Object result = sut.tupleTest(domainObject);

        assertThat(result)
            .isEqualTo(testValue);
    }

    @Test
    public void testNonNullDoTest() throws X {
        assertThat(sut.nonNullDoTest((short)100,(short)100))
            .isEqualTo(testValue);
    }

    @Test
    public void testNestingDoTestUnchecked() throws X {

        // then
        try {
            sutAlwaysThrowingUnchecked.nestingDoTest((short)100,(short)100);
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
            sutAlwaysThrowingUnchecked.shovingDoTest((short)100,(short)100);
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
        assertThat(sut.doApplyAsBoolean((short)100,(short)100))
            .isEqualTo(testValue);

    }


    @Test
    public void testFunctionalInterfaceDescription() throws X {
        assertThat(sut.functionalInterfaceDescription())
            .isEqualTo("LBiShortPredicate: boolean doTest(short a1,short a2)");
    }

    @Test
    public void testLMethod() throws X {
        assertThat(LBiShortPredicate.l((a1,a2) -> testValue ))
            .isInstanceOf(LBiShortPredicate.class);
    }

    @Test
    public void testWrapMethod() throws X {
        assertThat(LBiShortPredicate.wrap(opposite))
            .isInstanceOf(LBiShortPredicate.class);
    }

    @Test
    public void testWrapMethodDoNotWrapsRuntimeException() throws X {
        // given
        LBiShortPredicateX<X> sutThrowing = LBiShortPredicateX.lX((a1,a2) -> {
            throw new UnsupportedOperationException(ORIGINAL_MESSAGE);
        });

        // when
        LBiShortPredicate wrapped = LBiShortPredicate.wrap(sutThrowing);

        // then
        try {
            wrapped.doTest((short)100,(short)100);
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
        LBiShortPredicateX<ParseException> sutThrowing = LBiShortPredicateX.lX((a1,a2) -> {
            throw new ParseException(ORIGINAL_MESSAGE, 0);
        });

        // when
        LBiShortPredicate wrapped = LBiShortPredicate.wrap(sutThrowing);

        // then
        try {
            wrapped.doTest((short)100,(short)100);
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
        LBiShortPredicate sutThrowing = LBiShortPredicate.l((a1,a2) -> {
            throw new UnsupportedOperationException();
        });

        // when
        LBiShortPredicate wrapped = sutThrowing.handleBiShortPred(handler -> handler
            .wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED));

        // then
        try {
            wrapped.doTest((short)100,(short)100);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IllegalArgumentException.class)
                    .hasCauseExactlyInstanceOf(UnsupportedOperationException.class)
                    .hasMessage(EXCEPTION_WAS_WRAPPED);
        }
    }

    @Test
    public void testHandleBiShortPredMethodDoNotWrapsOtherExceptionIf() throws X {

        // given
        LBiShortPredicate sutThrowing = LBiShortPredicate.l((a1,a2) -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LBiShortPredicate wrapped = sutThrowing.handleBiShortPred(handler -> handler
                .wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED)
                .throwIf(IndexOutOfBoundsException.class));

        // then
        try {
            wrapped.doTest((short)100,(short)100);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                    .hasNoCause();
        }
    }

@Test
    public void testHandleBiShortPredMethodDoNotWrapsOtherExceptionWhen() throws X {

        // given
        LBiShortPredicate sutThrowing = LBiShortPredicate.l((a1,a2) -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LBiShortPredicate wrapped = sutThrowing.handleBiShortPred(handler -> handler
                .wrapWhen(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED)
                .throwIf(IndexOutOfBoundsException.class));

        // then
        try {
            wrapped.doTest((short)100,(short)100);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                    .hasNoCause();
        }
    }


    @Test
    public void testHandleBiShortPredMishandlingExceptionIsAllowed() throws X {

        // given
        LBiShortPredicate sutThrowing = LBiShortPredicate.l((a1,a2) -> {
            throw new UnsupportedOperationException(ORIGINAL_MESSAGE);
        });

        // when
        LBiShortPredicate wrapped = sutThrowing.handleBiShortPred(h -> Function4U.doNothing());

        // then
        try {
            wrapped.doTest((short)100,(short)100);
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
        assertThat(sut.negate().doTest((short)100,(short)100))
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
        LBiShortPredicate fun1 = LBiShortPredicate.l((a1,a2) -> f1Result);
        LBiShortPredicate fun2 = LBiShortPredicate.l((a1,a2) -> f2Result);

        //when
        LBiShortPredicate andFunction = fun1.and(fun2);
        LBiShortPredicate orFunction = fun1.or(fun2);
        LBiShortPredicate xorFunction = fun1.xor(fun2);

        //then
        assertThat(andFunction.doTest((short)100,(short)100))
                .isEqualTo(andResult);

        assertThat(orFunction.doTest((short)100,(short)100))
                .isEqualTo(orResult);

        assertThat(xorFunction.doTest((short)100,(short)100))
                .isEqualTo(xorResult);
    }

    @Test
    public void testIsEqual() throws X  {
        //when
        LBiShortPredicate equals = LBiShortPredicate.isEqual((short)1,(short)1);

        //then
        assertThat(equals.doTest((short)1,(short)1))
                .isTrue();

        assertThat(equals.doTest((short)0,(short)0))
                .isFalse();
    }



    // <editor-fold desc="compose (functional)">

    @Test
    public void testBiShortPredComposeShort() throws X {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LBiShortPredicate sutO = (a1,a2) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo((short)90);
                assertThat(a2).isEqualTo((short)91);
                return true;
        };

        LShortUnaryOperator before1 = p0 -> {
            assertThat(p0).isEqualTo((short)80);
            beforeCalls.incrementAndGet();
            return (short)90;
        };
        LShortUnaryOperator before2 = p1 -> {
            assertThat(p1).isEqualTo((short)81);
            beforeCalls.incrementAndGet();
            return (short)91;
        };

        //when
        LBiShortPredicate function = sutO.biShortPredComposeShort(before1,before2);
        function.doTest((short)80,(short)81);

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(beforeCalls.get()).isEqualTo(2);
    }


    @Test
    public void testBiShortPredCompose() throws X {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LBiShortPredicate sutO = (a1,a2) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo((short)90);
                assertThat(a2).isEqualTo((short)91);
                return true;
        };

        LToShortFunction<Integer> before1 = p0 -> {
            assertThat(p0).isEqualTo(80);
            beforeCalls.incrementAndGet();
            return (short)90;
        };
        LToShortFunction<Integer> before2 = p1 -> {
            assertThat(p1).isEqualTo(81);
            beforeCalls.incrementAndGet();
            return (short)91;
        };

        //when
        LBiPredicate<Integer,Integer> function = sutO.biShortPredCompose(before1,before2);
        function.doTest(80,81);

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(beforeCalls.get()).isEqualTo(2);
    }

    // </editor-fold>



    // <editor-fold desc="then (functional)">

    @Test
    public void testBoolToBiShortFunction0() throws X  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);

        //given (+ some assertions)
        LBiShortPredicate sutO = (a1,a2) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo((short)80);
                assertThat(a2).isEqualTo((short)81);
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
        LBiShortFunction<Integer> function = sutO.boolToBiShortFunction(thenFunction);
        Integer finalValue = function.doApply((short)80,(short)81);

        //then - finals
        assertThat(finalValue).isEqualTo(100);
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    // </editor-fold>

    @Test
    public void testNesting() {
        assertThat(sut.nestingBiShortPred())
            .isSameAs(sut)
            .isInstanceOf(LBiShortPredicate.class);
    }

    @Test
    public void testShoving() {
        assertThat(sut.shovingBiShortPred())
            .isSameAs(sut)
            .isInstanceOf(LBiShortPredicate.class);
    }

    @Test
    public void testNestingX() {
        assertThat(sut.nestingBiShortPredX())
            .isSameAs(sut)
            .isInstanceOf(LBiShortPredicateX.class);
    }

    @Test
    public void testShovingX() {
        assertThat(sut.shovingBiShortPredX())
            .isSameAs(sut)
            .isInstanceOf(LBiShortPredicateX.class);
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void testShove() {

        // given
        LBiShortPredicate sutThrowing = LBiShortPredicate.l((a1,a2) -> {
            throw new UnsupportedOperationException();
        });

        // when
        sutThrowing.shovingBiShortPred().doTest((short)100,(short)100);
    }

    @Test
    public void testHandleBiShortPred() throws X {

        // given
        LBiShortPredicate sutThrowing = LBiShortPredicate.l((a1,a2) -> {
            throw new UnsupportedOperationException();
        });

        // when
        LBiShortPredicate wrapped = sutThrowing.handleBiShortPred(h -> {
            h.wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED);
        });

        // then
        try {
            wrapped.doTest((short)100,(short)100);
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
                .contains("LBiShortPredicate: boolean doTest(short a1,short a2)");
    }


    @Test
    public void isThrowing() {
        assertThat(sut.isThrowing())
            .isFalse();
    }

    //<editor-fold desc="Variants">

    private boolean variantV1(short a2,short a1) {
        return true;
    }

    @Test
    public void compilerSubstituteVariantV1() {
        LBiShortPredicate lambda = LBiShortPredicate./**/l1(this::variantV1);

        assertThat(lambda).isInstanceOf(LBiShortPredicate.V1.class);
    }

    //</editor-fold>


    @Test void safeCompiles() {
        LBiShortPredicate r1 = LBiShortPredicate.safe(sut); //NOSONAR
        LBiShortPredicateX r2 = LBiShortPredicate.safe(sut); //NOSONAR
    }

    @Test void safePropagates() {
        Object result = LBiShortPredicate.safe(sut);
        assertThat(result).isSameAs(sut);
    }

    @Test void safeProtectsAgainstNpe() {
        Object result = LBiShortPredicate.safe(null);
        assertThat(result).isSameAs(LBiShortPredicate.l(LBiShortPredicate.safe()));
    }

    @Test  void safeSupplierPropagates() {
        LSupplier<LBiShortPredicate> supplier = ()->sut;
        Object result = LBiShortPredicate.safeSupplier(supplier);
        assertThat(result).isSameAs(supplier);
    }

    @Test  void safeSupplierProtectsAgainstNpe() {
        Object result = LBiShortPredicate.safeSupplier(null);
        assertThat(result).isSameAs(LBiShortPredicate.safeSupplier());
    }

    @Test  void safeSupplierCompiles() {
        LSupplier<LBiShortPredicate> r1 = LBiShortPredicate.safeSupplier(()->sut);  //NOSONAR
    }

}
