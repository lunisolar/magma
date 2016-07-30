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
public class LObjShortPredicateTest<T,X extends ParseException> {
    private static final String ORIGINAL_MESSAGE = "Original message";
    private static final String EXCEPTION_WAS_WRAPPED = "Exception was wrapped.";
    private static final String NO_EXCEPTION_WERE_THROWN = "No exception were thrown.";

    private boolean testValue = true;



    private LObjShortPredicate<Integer> sut = new LObjShortPredicate<Integer>(){
        public  boolean doTest(Integer a1,short a2)  {
            return testValue;
        }
    };

    private LObjShortPredicateX<Integer,X> opposite = new LObjShortPredicateX<Integer,X>(){
        public  boolean doTest(Integer a1,short a2)  throws X {
            return testValue;
        }
    };




    private LObjShortPredicateX<Integer,RuntimeException> sutAlwaysThrowingUnchecked = LObjShortPredicate.l((a1,a2) -> {
            throw new IndexOutOfBoundsException(ORIGINAL_MESSAGE);
    });


    @Test
    public void testTheResult() throws X {
        assertThat(sut.doTest(100,(short)100))
            .isEqualTo(testValue);
    }

    @Test
    public void testTupleCall() throws X {

        LObjShortPair<Integer> domainObject = Tuple4U.objShortPair(100,(short)100);

        Object result = sut.tupleTest(domainObject);

        assertThat(result)
            .isEqualTo(testValue);
    }

    @Test
    public void testNonNullDoTest() throws X {
        assertThat(sut.nonNullDoTest(100,(short)100))
            .isEqualTo(testValue);
    }

    @Test
    public void testNestingDoTestUnchecked() throws X {

        // then
        try {
            sutAlwaysThrowingUnchecked.nestingDoTest(100,(short)100);
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
            sutAlwaysThrowingUnchecked.shovingDoTest(100,(short)100);
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
        assertThat(sut.doApplyAsBoolean(100,(short)100))
            .isEqualTo(testValue);

    }


    @Test
    public void testFunctionalInterfaceDescription() throws X {
        assertThat(sut.functionalInterfaceDescription())
            .isEqualTo("LObjShortPredicate: boolean doTest(T a1,short a2)");
    }

    @Test
    public void testLMethod() throws X {
        assertThat(LObjShortPredicate.l((a1,a2) -> testValue ))
            .isInstanceOf(LObjShortPredicate.class);
    }

    @Test
    public void testWrapMethod() throws X {
        assertThat(LObjShortPredicate.wrap(opposite))
            .isInstanceOf(LObjShortPredicate.class);
    }

    @Test
    public void testWrapMethodDoNotWrapsRuntimeException() throws X {
        // given
        LObjShortPredicateX<Integer,X> sutThrowing = LObjShortPredicateX.lX((a1,a2) -> {
            throw new UnsupportedOperationException(ORIGINAL_MESSAGE);
        });

        // when
        LObjShortPredicate<Integer> wrapped = LObjShortPredicate.wrap(sutThrowing);

        // then
        try {
            wrapped.doTest(100,(short)100);
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
        LObjShortPredicateX<Integer,ParseException> sutThrowing = LObjShortPredicateX.lX((a1,a2) -> {
            throw new ParseException(ORIGINAL_MESSAGE, 0);
        });

        // when
        LObjShortPredicate<Integer> wrapped = LObjShortPredicate.wrap(sutThrowing);

        // then
        try {
            wrapped.doTest(100,(short)100);
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
        LObjShortPredicate<Integer> sutThrowing = LObjShortPredicate.l((a1,a2) -> {
            throw new UnsupportedOperationException();
        });

        // when
        LObjShortPredicate<Integer> wrapped = sutThrowing.handleObjShortPred(handler -> handler
            .wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED));

        // then
        try {
            wrapped.doTest(100,(short)100);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IllegalArgumentException.class)
                    .hasCauseExactlyInstanceOf(UnsupportedOperationException.class)
                    .hasMessage(EXCEPTION_WAS_WRAPPED);
        }
    }

    @Test
    public void testHandleObjShortPredMethodDoNotWrapsOtherExceptionIf() throws X {

        // given
        LObjShortPredicate<Integer> sutThrowing = LObjShortPredicate.l((a1,a2) -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LObjShortPredicate<Integer> wrapped = sutThrowing.handleObjShortPred(handler -> handler
                .wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED)
                .throwIf(IndexOutOfBoundsException.class));

        // then
        try {
            wrapped.doTest(100,(short)100);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                    .hasNoCause();
        }
    }

@Test
    public void testHandleObjShortPredMethodDoNotWrapsOtherExceptionWhen() throws X {

        // given
        LObjShortPredicate<Integer> sutThrowing = LObjShortPredicate.l((a1,a2) -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LObjShortPredicate<Integer> wrapped = sutThrowing.handleObjShortPred(handler -> handler
                .wrapWhen(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED)
                .throwIf(IndexOutOfBoundsException.class));

        // then
        try {
            wrapped.doTest(100,(short)100);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                    .hasNoCause();
        }
    }


    @Test
    public void testHandleObjShortPredMishandlingExceptionIsAllowed() throws X {

        // given
        LObjShortPredicate<Integer> sutThrowing = LObjShortPredicate.l((a1,a2) -> {
            throw new UnsupportedOperationException(ORIGINAL_MESSAGE);
        });

        // when
        LObjShortPredicate<Integer> wrapped = sutThrowing.handleObjShortPred(h -> Function4U.doNothing());

        // then
        try {
            wrapped.doTest(100,(short)100);
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
        assertThat(sut.negate().doTest(100,(short)100))
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
        LObjShortPredicate<Integer> fun1 = LObjShortPredicate.l((a1,a2) -> f1Result);
        LObjShortPredicate<Integer> fun2 = LObjShortPredicate.l((a1,a2) -> f2Result);

        //when
        LObjShortPredicate<Integer> andFunction = fun1.and(fun2);
        LObjShortPredicate<Integer> orFunction = fun1.or(fun2);
        LObjShortPredicate<Integer> xorFunction = fun1.xor(fun2);

        //then
        assertThat(andFunction.doTest(100,(short)100))
                .isEqualTo(andResult);

        assertThat(orFunction.doTest(100,(short)100))
                .isEqualTo(orResult);

        assertThat(xorFunction.doTest(100,(short)100))
                .isEqualTo(xorResult);
    }

    @Test
    public void testIsEqual() throws X  {
        //when
        LObjShortPredicate<Integer> equals = LObjShortPredicate.isEqual(1,(short)1);

        //then
        assertThat(equals.doTest(1,(short)1))
                .isTrue();

        assertThat(equals.doTest(0,(short)0))
                .isFalse();
    }



    // <editor-fold desc="compose (functional)">

    @Test
    public void testObjShortPredComposeShort() throws X {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LObjShortPredicate<Integer> sutO = (a1,a2) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(90);
                assertThat(a2).isEqualTo((short)91);
                return true;
        };

        LFunction<Integer,Integer> before1 = p0 -> {
            assertThat(p0).isEqualTo(80);
            beforeCalls.incrementAndGet();
            return 90;
        };
        LShortUnaryOperator before2 = p1 -> {
            assertThat(p1).isEqualTo((short)81);
            beforeCalls.incrementAndGet();
            return (short)91;
        };

        //when
        LObjShortPredicate<Integer> function = sutO.objShortPredComposeShort(before1,before2);
        function.doTest(80,(short)81);

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(beforeCalls.get()).isEqualTo(2);
    }


    @Test
    public void testObjShortPredCompose() throws X {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LObjShortPredicate<Integer> sutO = (a1,a2) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(90);
                assertThat(a2).isEqualTo((short)91);
                return true;
        };

        LFunction<Integer,Integer> before1 = p0 -> {
            assertThat(p0).isEqualTo(80);
            beforeCalls.incrementAndGet();
            return 90;
        };
        LToShortFunction<Integer> before2 = p1 -> {
            assertThat(p1).isEqualTo(81);
            beforeCalls.incrementAndGet();
            return (short)91;
        };

        //when
        LBiPredicate<Integer,Integer> function = sutO.objShortPredCompose(before1,before2);
        function.doTest(80,81);

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(beforeCalls.get()).isEqualTo(2);
    }

    // </editor-fold>



    // <editor-fold desc="then (functional)">

    @Test
    public void testBoolToObjShortFunc0() throws X  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);

        //given (+ some assertions)
        LObjShortPredicate<Integer> sutO = (a1,a2) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(80);
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
        LObjShortFunction<Integer,Integer> function = sutO.boolToObjShortFunc(thenFunction);
        Integer finalValue = function.doApply(80,(short)81);

        //then - finals
        assertThat(finalValue).isEqualTo(100);
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    // </editor-fold>

    @Test
    public void testNesting() {
        assertThat(sut.nestingObjShortPred())
            .isSameAs(sut)
            .isInstanceOf(LObjShortPredicate.class);
    }

    @Test
    public void testShoving() {
        assertThat(sut.shovingObjShortPred())
            .isSameAs(sut)
            .isInstanceOf(LObjShortPredicate.class);
    }

    @Test
    public void testNestingX() {
        assertThat(sut.nestingObjShortPredX())
            .isSameAs(sut)
            .isInstanceOf(LObjShortPredicateX.class);
    }

    @Test
    public void testShovingX() {
        assertThat(sut.shovingObjShortPredX())
            .isSameAs(sut)
            .isInstanceOf(LObjShortPredicateX.class);
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void testShove() {

        // given
        LObjShortPredicate<Integer> sutThrowing = LObjShortPredicate.l((a1,a2) -> {
            throw new UnsupportedOperationException();
        });

        // when
        sutThrowing.shovingObjShortPred().doTest(100,(short)100);
    }

    @Test
    public void testHandleObjShortPred() throws X {

        // given
        LObjShortPredicate<Integer> sutThrowing = LObjShortPredicate.l((a1,a2) -> {
            throw new UnsupportedOperationException();
        });

        // when
        LObjShortPredicate<Integer> wrapped = sutThrowing.handleObjShortPred(h -> {
            h.wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED);
        });

        // then
        try {
            wrapped.doTest(100,(short)100);
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
                .contains("LObjShortPredicate: boolean doTest(T a1,short a2)");
    }


    @Test
    public void isThrowing() {
        assertThat(sut.isThrowing())
            .isFalse();
    }

    //<editor-fold desc="Variants">

    private boolean variantV1(short a2,Integer a1) {
        return true;
    }

    @Test
    public void compilerSubstituteVariantV1() {
        LObjShortPredicate lambda = LObjShortPredicate./*<T>*/l1(this::variantV1);

        assertThat(lambda).isInstanceOf(LObjShortPredicate.V1.class);
    }

    //</editor-fold>


    @Test void safeCompiles() {
        LObjShortPredicate r1 = LObjShortPredicate.safe(sut); //NOSONAR
        LObjShortPredicateX r2 = LObjShortPredicate.safe(sut); //NOSONAR
    }

    @Test void safePropagates() {
        Object result = LObjShortPredicate.safe(sut);
        assertThat(result).isSameAs(sut);
    }

    @Test void safeProtectsAgainstNpe() {
        Object result = LObjShortPredicate.safe(null);
        assertThat(result).isSameAs(LObjShortPredicate.l(LObjShortPredicate.safe()));
    }

    @Test  void safeSupplierPropagates() {
        LSupplier<LObjShortPredicate<Integer>> supplier = ()->sut;
        Object result = LObjShortPredicate.safeSupplier(supplier);
        assertThat(result).isSameAs(supplier);
    }

    @Test  void safeSupplierProtectsAgainstNpe() {
        Object result = LObjShortPredicate.safeSupplier(null);
        assertThat(result).isSameAs(LObjShortPredicate.safeSupplier());
    }

    @Test  void safeSupplierCompiles() {
        LSupplier<LObjShortPredicate<Integer>> r1 = LObjShortPredicate.safeSupplier(()->sut);  //NOSONAR
    }

}
