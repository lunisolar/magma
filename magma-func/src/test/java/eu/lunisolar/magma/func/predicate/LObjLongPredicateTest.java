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
public class LObjLongPredicateTest<T,X extends ParseException> {
    private static final String ORIGINAL_MESSAGE = "Original message";
    private static final String EXCEPTION_WAS_WRAPPED = "Exception was wrapped.";
    private static final String NO_EXCEPTION_WERE_THROWN = "No exception were thrown.";

    private boolean testValue = true;



    private LObjLongPredicate<Integer> sut = new LObjLongPredicate<Integer>(){
        public  boolean doTest(Integer a1,long a2)  {
            return testValue;
        }
    };

    private LObjLongPredicateX<Integer,X> opposite = new LObjLongPredicateX<Integer,X>(){
        public  boolean doTest(Integer a1,long a2)  throws X {
            return testValue;
        }
    };




    private LObjLongPredicateX<Integer,RuntimeException> sutAlwaysThrowingUnchecked = LObjLongPredicate.l((a1,a2) -> {
            throw new IndexOutOfBoundsException(ORIGINAL_MESSAGE);
    });


    @Test
    public void testTheResult() throws X {
        assertThat(sut.doTest(100,100L))
            .isEqualTo(testValue);
    }

    @Test
    public void testTupleCall() throws X {

        LObjLongPair<Integer> domainObject = Tuple4U.objLongPair(100,100L);

        Object result = sut.tupleTest(domainObject);

        assertThat(result)
            .isEqualTo(testValue);
    }

    @Test
    public void testNonNullDoTest() throws X {
        assertThat(sut.nonNullDoTest(100,100L))
            .isEqualTo(testValue);
    }

    @Test
    public void testNestingDoTestUnchecked() throws X {

        // then
        try {
            sutAlwaysThrowingUnchecked.nestingDoTest(100,100L);
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
            sutAlwaysThrowingUnchecked.shovingDoTest(100,100L);
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
        assertThat(sut.doApplyAsBoolean(100,100L))
            .isEqualTo(testValue);

    }


    @Test
    public void testFunctionalInterfaceDescription() throws X {
        assertThat(sut.functionalInterfaceDescription())
            .isEqualTo("LObjLongPredicate: boolean doTest(T a1,long a2)");
    }

    @Test
    public void testLMethod() throws X {
        assertThat(LObjLongPredicate.l((a1,a2) -> testValue ))
            .isInstanceOf(LObjLongPredicate.class);
    }

    @Test
    public void testWrapMethod() throws X {
        assertThat(LObjLongPredicate.wrap(opposite))
            .isInstanceOf(LObjLongPredicate.class);
    }

    @Test
    public void testWrapMethodDoNotWrapsRuntimeException() throws X {
        // given
        LObjLongPredicateX<Integer,X> sutThrowing = LObjLongPredicateX.lX((a1,a2) -> {
            throw new UnsupportedOperationException(ORIGINAL_MESSAGE);
        });

        // when
        LObjLongPredicate<Integer> wrapped = LObjLongPredicate.wrap(sutThrowing);

        // then
        try {
            wrapped.doTest(100,100L);
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
        LObjLongPredicateX<Integer,ParseException> sutThrowing = LObjLongPredicateX.lX((a1,a2) -> {
            throw new ParseException(ORIGINAL_MESSAGE, 0);
        });

        // when
        LObjLongPredicate<Integer> wrapped = LObjLongPredicate.wrap(sutThrowing);

        // then
        try {
            wrapped.doTest(100,100L);
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
        LObjLongPredicate<Integer> sutThrowing = LObjLongPredicate.l((a1,a2) -> {
            throw new UnsupportedOperationException();
        });

        // when
        LObjLongPredicate<Integer> wrapped = sutThrowing.handleObjLongPred(handler -> handler
            .wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED));

        // then
        try {
            wrapped.doTest(100,100L);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IllegalArgumentException.class)
                    .hasCauseExactlyInstanceOf(UnsupportedOperationException.class)
                    .hasMessage(EXCEPTION_WAS_WRAPPED);
        }
    }

    @Test
    public void testHandleObjLongPredMethodDoNotWrapsOtherExceptionIf() throws X {

        // given
        LObjLongPredicate<Integer> sutThrowing = LObjLongPredicate.l((a1,a2) -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LObjLongPredicate<Integer> wrapped = sutThrowing.handleObjLongPred(handler -> handler
                .wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED)
                .throwIf(IndexOutOfBoundsException.class));

        // then
        try {
            wrapped.doTest(100,100L);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                    .hasNoCause();
        }
    }

@Test
    public void testHandleObjLongPredMethodDoNotWrapsOtherExceptionWhen() throws X {

        // given
        LObjLongPredicate<Integer> sutThrowing = LObjLongPredicate.l((a1,a2) -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LObjLongPredicate<Integer> wrapped = sutThrowing.handleObjLongPred(handler -> handler
                .wrapWhen(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED)
                .throwIf(IndexOutOfBoundsException.class));

        // then
        try {
            wrapped.doTest(100,100L);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                    .hasNoCause();
        }
    }


    @Test
    public void testHandleObjLongPredMishandlingExceptionIsAllowed() throws X {

        // given
        LObjLongPredicate<Integer> sutThrowing = LObjLongPredicate.l((a1,a2) -> {
            throw new UnsupportedOperationException(ORIGINAL_MESSAGE);
        });

        // when
        LObjLongPredicate<Integer> wrapped = sutThrowing.handleObjLongPred(h -> Function4U.doNothing());

        // then
        try {
            wrapped.doTest(100,100L);
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
        assertThat(sut.negate().doTest(100,100L))
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
        LObjLongPredicate<Integer> fun1 = LObjLongPredicate.l((a1,a2) -> f1Result);
        LObjLongPredicate<Integer> fun2 = LObjLongPredicate.l((a1,a2) -> f2Result);

        //when
        LObjLongPredicate<Integer> andFunction = fun1.and(fun2);
        LObjLongPredicate<Integer> orFunction = fun1.or(fun2);
        LObjLongPredicate<Integer> xorFunction = fun1.xor(fun2);

        //then
        assertThat(andFunction.doTest(100,100L))
                .isEqualTo(andResult);

        assertThat(orFunction.doTest(100,100L))
                .isEqualTo(orResult);

        assertThat(xorFunction.doTest(100,100L))
                .isEqualTo(xorResult);
    }

    @Test
    public void testIsEqual() throws X  {
        //when
        LObjLongPredicate<Integer> equals = LObjLongPredicate.isEqual(1,1L);

        //then
        assertThat(equals.doTest(1,1L))
                .isTrue();

        assertThat(equals.doTest(0,0L))
                .isFalse();
    }



    // <editor-fold desc="compose (functional)">

    @Test
    public void testObjLongPredComposeLong() throws X {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LObjLongPredicate<Integer> sutO = (a1,a2) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(90);
                assertThat(a2).isEqualTo(91L);
                return true;
        };

        LFunction<Integer,Integer> before1 = p0 -> {
            assertThat(p0).isEqualTo(80);
            beforeCalls.incrementAndGet();
            return 90;
        };
        LLongUnaryOperator before2 = p1 -> {
            assertThat(p1).isEqualTo(81L);
            beforeCalls.incrementAndGet();
            return 91L;
        };

        //when
        LObjLongPredicate<Integer> function = sutO.objLongPredComposeLong(before1,before2);
        function.doTest(80,81L);

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(beforeCalls.get()).isEqualTo(2);
    }


    @Test
    public void testObjLongPredCompose() throws X {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LObjLongPredicate<Integer> sutO = (a1,a2) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(90);
                assertThat(a2).isEqualTo(91L);
                return true;
        };

        LFunction<Integer,Integer> before1 = p0 -> {
            assertThat(p0).isEqualTo(80);
            beforeCalls.incrementAndGet();
            return 90;
        };
        LToLongFunction<Integer> before2 = p1 -> {
            assertThat(p1).isEqualTo(81);
            beforeCalls.incrementAndGet();
            return 91L;
        };

        //when
        LBiPredicate<Integer,Integer> function = sutO.objLongPredCompose(before1,before2);
        function.doTest(80,81);

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(beforeCalls.get()).isEqualTo(2);
    }

    // </editor-fold>



    // <editor-fold desc="then (functional)">

    @Test
    public void testBoolToObjLongFunc0() throws X  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);

        //given (+ some assertions)
        LObjLongPredicate<Integer> sutO = (a1,a2) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(80);
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
        LObjLongFunction<Integer,Integer> function = sutO.boolToObjLongFunc(thenFunction);
        Integer finalValue = function.doApply(80,81L);

        //then - finals
        assertThat(finalValue).isEqualTo(100);
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    // </editor-fold>

    @Test
    public void testNesting() {
        assertThat(sut.nestingObjLongPred())
            .isSameAs(sut)
            .isInstanceOf(LObjLongPredicate.class);
    }

    @Test
    public void testShoving() {
        assertThat(sut.shovingObjLongPred())
            .isSameAs(sut)
            .isInstanceOf(LObjLongPredicate.class);
    }

    @Test
    public void testNestingX() {
        assertThat(sut.nestingObjLongPredX())
            .isSameAs(sut)
            .isInstanceOf(LObjLongPredicateX.class);
    }

    @Test
    public void testShovingX() {
        assertThat(sut.shovingObjLongPredX())
            .isSameAs(sut)
            .isInstanceOf(LObjLongPredicateX.class);
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void testShove() {

        // given
        LObjLongPredicate<Integer> sutThrowing = LObjLongPredicate.l((a1,a2) -> {
            throw new UnsupportedOperationException();
        });

        // when
        sutThrowing.shovingObjLongPred().doTest(100,100L);
    }

    @Test
    public void testHandleObjLongPred() throws X {

        // given
        LObjLongPredicate<Integer> sutThrowing = LObjLongPredicate.l((a1,a2) -> {
            throw new UnsupportedOperationException();
        });

        // when
        LObjLongPredicate<Integer> wrapped = sutThrowing.handleObjLongPred(h -> {
            h.wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED);
        });

        // then
        try {
            wrapped.doTest(100,100L);
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
                .contains("LObjLongPredicate: boolean doTest(T a1,long a2)");
    }


    @Test
    public void isThrowing() {
        assertThat(sut.isThrowing())
            .isFalse();
    }

    //<editor-fold desc="Variants">

    private boolean variantV1(long a2,Integer a1) {
        return true;
    }

    @Test
    public void compilerSubstituteVariantV1() {
        LObjLongPredicate lambda = LObjLongPredicate./*<T>*/l1(this::variantV1);

        assertThat(lambda).isInstanceOf(LObjLongPredicate.V1.class);
    }

    //</editor-fold>


    @Test void safeCompiles() {
        LObjLongPredicate r1 = LObjLongPredicate.safe(sut); //NOSONAR
        LObjLongPredicateX r2 = LObjLongPredicate.safe(sut); //NOSONAR
    }

    @Test void safePropagates() {
        Object result = LObjLongPredicate.safe(sut);
        assertThat(result).isSameAs(sut);
    }

    @Test void safeProtectsAgainstNpe() {
        Object result = LObjLongPredicate.safe(null);
        assertThat(result).isSameAs(LObjLongPredicate.l(LObjLongPredicate.safe()));
    }

    @Test  void safeSupplierPropagates() {
        LSupplier<LObjLongPredicate<Integer>> supplier = ()->sut;
        Object result = LObjLongPredicate.safeSupplier(supplier);
        assertThat(result).isSameAs(supplier);
    }

    @Test  void safeSupplierProtectsAgainstNpe() {
        Object result = LObjLongPredicate.safeSupplier(null);
        assertThat(result).isSameAs(LObjLongPredicate.safeSupplier());
    }

    @Test  void safeSupplierCompiles() {
        LSupplier<LObjLongPredicate<Integer>> r1 = LObjLongPredicate.safeSupplier(()->sut);  //NOSONAR
    }

}
