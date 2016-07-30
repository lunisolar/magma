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
public class LObjBytePredicateTest<T,X extends ParseException> {
    private static final String ORIGINAL_MESSAGE = "Original message";
    private static final String EXCEPTION_WAS_WRAPPED = "Exception was wrapped.";
    private static final String NO_EXCEPTION_WERE_THROWN = "No exception were thrown.";

    private boolean testValue = true;



    private LObjBytePredicate<Integer> sut = new LObjBytePredicate<Integer>(){
        public  boolean doTest(Integer a1,byte a2)  {
            return testValue;
        }
    };

    private LObjBytePredicateX<Integer,X> opposite = new LObjBytePredicateX<Integer,X>(){
        public  boolean doTest(Integer a1,byte a2)  throws X {
            return testValue;
        }
    };




    private LObjBytePredicateX<Integer,RuntimeException> sutAlwaysThrowingUnchecked = LObjBytePredicate.l((a1,a2) -> {
            throw new IndexOutOfBoundsException(ORIGINAL_MESSAGE);
    });


    @Test
    public void testTheResult() throws X {
        assertThat(sut.doTest(100,(byte)100))
            .isEqualTo(testValue);
    }

    @Test
    public void testTupleCall() throws X {

        LObjBytePair<Integer> domainObject = Tuple4U.objBytePair(100,(byte)100);

        Object result = sut.tupleTest(domainObject);

        assertThat(result)
            .isEqualTo(testValue);
    }

    @Test
    public void testNonNullDoTest() throws X {
        assertThat(sut.nonNullDoTest(100,(byte)100))
            .isEqualTo(testValue);
    }

    @Test
    public void testNestingDoTestUnchecked() throws X {

        // then
        try {
            sutAlwaysThrowingUnchecked.nestingDoTest(100,(byte)100);
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
            sutAlwaysThrowingUnchecked.shovingDoTest(100,(byte)100);
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
        assertThat(sut.doApplyAsBoolean(100,(byte)100))
            .isEqualTo(testValue);

    }


    @Test
    public void testFunctionalInterfaceDescription() throws X {
        assertThat(sut.functionalInterfaceDescription())
            .isEqualTo("LObjBytePredicate: boolean doTest(T a1,byte a2)");
    }

    @Test
    public void testLMethod() throws X {
        assertThat(LObjBytePredicate.l((a1,a2) -> testValue ))
            .isInstanceOf(LObjBytePredicate.class);
    }

    @Test
    public void testWrapMethod() throws X {
        assertThat(LObjBytePredicate.wrap(opposite))
            .isInstanceOf(LObjBytePredicate.class);
    }

    @Test
    public void testWrapMethodDoNotWrapsRuntimeException() throws X {
        // given
        LObjBytePredicateX<Integer,X> sutThrowing = LObjBytePredicateX.lX((a1,a2) -> {
            throw new UnsupportedOperationException(ORIGINAL_MESSAGE);
        });

        // when
        LObjBytePredicate<Integer> wrapped = LObjBytePredicate.wrap(sutThrowing);

        // then
        try {
            wrapped.doTest(100,(byte)100);
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
        LObjBytePredicateX<Integer,ParseException> sutThrowing = LObjBytePredicateX.lX((a1,a2) -> {
            throw new ParseException(ORIGINAL_MESSAGE, 0);
        });

        // when
        LObjBytePredicate<Integer> wrapped = LObjBytePredicate.wrap(sutThrowing);

        // then
        try {
            wrapped.doTest(100,(byte)100);
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
        LObjBytePredicate<Integer> sutThrowing = LObjBytePredicate.l((a1,a2) -> {
            throw new UnsupportedOperationException();
        });

        // when
        LObjBytePredicate<Integer> wrapped = sutThrowing.handleObjBytePred(handler -> handler
            .wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED));

        // then
        try {
            wrapped.doTest(100,(byte)100);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IllegalArgumentException.class)
                    .hasCauseExactlyInstanceOf(UnsupportedOperationException.class)
                    .hasMessage(EXCEPTION_WAS_WRAPPED);
        }
    }

    @Test
    public void testHandleObjBytePredMethodDoNotWrapsOtherExceptionIf() throws X {

        // given
        LObjBytePredicate<Integer> sutThrowing = LObjBytePredicate.l((a1,a2) -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LObjBytePredicate<Integer> wrapped = sutThrowing.handleObjBytePred(handler -> handler
                .wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED)
                .throwIf(IndexOutOfBoundsException.class));

        // then
        try {
            wrapped.doTest(100,(byte)100);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                    .hasNoCause();
        }
    }

@Test
    public void testHandleObjBytePredMethodDoNotWrapsOtherExceptionWhen() throws X {

        // given
        LObjBytePredicate<Integer> sutThrowing = LObjBytePredicate.l((a1,a2) -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LObjBytePredicate<Integer> wrapped = sutThrowing.handleObjBytePred(handler -> handler
                .wrapWhen(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED)
                .throwIf(IndexOutOfBoundsException.class));

        // then
        try {
            wrapped.doTest(100,(byte)100);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                    .hasNoCause();
        }
    }


    @Test
    public void testHandleObjBytePredMishandlingExceptionIsAllowed() throws X {

        // given
        LObjBytePredicate<Integer> sutThrowing = LObjBytePredicate.l((a1,a2) -> {
            throw new UnsupportedOperationException(ORIGINAL_MESSAGE);
        });

        // when
        LObjBytePredicate<Integer> wrapped = sutThrowing.handleObjBytePred(h -> Function4U.doNothing());

        // then
        try {
            wrapped.doTest(100,(byte)100);
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
        assertThat(sut.negate().doTest(100,(byte)100))
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
        LObjBytePredicate<Integer> fun1 = LObjBytePredicate.l((a1,a2) -> f1Result);
        LObjBytePredicate<Integer> fun2 = LObjBytePredicate.l((a1,a2) -> f2Result);

        //when
        LObjBytePredicate<Integer> andFunction = fun1.and(fun2);
        LObjBytePredicate<Integer> orFunction = fun1.or(fun2);
        LObjBytePredicate<Integer> xorFunction = fun1.xor(fun2);

        //then
        assertThat(andFunction.doTest(100,(byte)100))
                .isEqualTo(andResult);

        assertThat(orFunction.doTest(100,(byte)100))
                .isEqualTo(orResult);

        assertThat(xorFunction.doTest(100,(byte)100))
                .isEqualTo(xorResult);
    }

    @Test
    public void testIsEqual() throws X  {
        //when
        LObjBytePredicate<Integer> equals = LObjBytePredicate.isEqual(1,(byte)1);

        //then
        assertThat(equals.doTest(1,(byte)1))
                .isTrue();

        assertThat(equals.doTest(0,(byte)0))
                .isFalse();
    }



    // <editor-fold desc="compose (functional)">

    @Test
    public void testObjBytePredComposeByte() throws X {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LObjBytePredicate<Integer> sutO = (a1,a2) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(90);
                assertThat(a2).isEqualTo((byte)91);
                return true;
        };

        LFunction<Integer,Integer> before1 = p0 -> {
            assertThat(p0).isEqualTo(80);
            beforeCalls.incrementAndGet();
            return 90;
        };
        LByteUnaryOperator before2 = p1 -> {
            assertThat(p1).isEqualTo((byte)81);
            beforeCalls.incrementAndGet();
            return (byte)91;
        };

        //when
        LObjBytePredicate<Integer> function = sutO.objBytePredComposeByte(before1,before2);
        function.doTest(80,(byte)81);

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(beforeCalls.get()).isEqualTo(2);
    }


    @Test
    public void testObjBytePredCompose() throws X {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LObjBytePredicate<Integer> sutO = (a1,a2) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(90);
                assertThat(a2).isEqualTo((byte)91);
                return true;
        };

        LFunction<Integer,Integer> before1 = p0 -> {
            assertThat(p0).isEqualTo(80);
            beforeCalls.incrementAndGet();
            return 90;
        };
        LToByteFunction<Integer> before2 = p1 -> {
            assertThat(p1).isEqualTo(81);
            beforeCalls.incrementAndGet();
            return (byte)91;
        };

        //when
        LBiPredicate<Integer,Integer> function = sutO.objBytePredCompose(before1,before2);
        function.doTest(80,81);

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(beforeCalls.get()).isEqualTo(2);
    }

    // </editor-fold>



    // <editor-fold desc="then (functional)">

    @Test
    public void testBoolToObjByteFunc0() throws X  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);

        //given (+ some assertions)
        LObjBytePredicate<Integer> sutO = (a1,a2) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(80);
                assertThat(a2).isEqualTo((byte)81);
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
        LObjByteFunction<Integer,Integer> function = sutO.boolToObjByteFunc(thenFunction);
        Integer finalValue = function.doApply(80,(byte)81);

        //then - finals
        assertThat(finalValue).isEqualTo(100);
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    // </editor-fold>

    @Test
    public void testNesting() {
        assertThat(sut.nestingObjBytePred())
            .isSameAs(sut)
            .isInstanceOf(LObjBytePredicate.class);
    }

    @Test
    public void testShoving() {
        assertThat(sut.shovingObjBytePred())
            .isSameAs(sut)
            .isInstanceOf(LObjBytePredicate.class);
    }

    @Test
    public void testNestingX() {
        assertThat(sut.nestingObjBytePredX())
            .isSameAs(sut)
            .isInstanceOf(LObjBytePredicateX.class);
    }

    @Test
    public void testShovingX() {
        assertThat(sut.shovingObjBytePredX())
            .isSameAs(sut)
            .isInstanceOf(LObjBytePredicateX.class);
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void testShove() {

        // given
        LObjBytePredicate<Integer> sutThrowing = LObjBytePredicate.l((a1,a2) -> {
            throw new UnsupportedOperationException();
        });

        // when
        sutThrowing.shovingObjBytePred().doTest(100,(byte)100);
    }

    @Test
    public void testHandleObjBytePred() throws X {

        // given
        LObjBytePredicate<Integer> sutThrowing = LObjBytePredicate.l((a1,a2) -> {
            throw new UnsupportedOperationException();
        });

        // when
        LObjBytePredicate<Integer> wrapped = sutThrowing.handleObjBytePred(h -> {
            h.wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED);
        });

        // then
        try {
            wrapped.doTest(100,(byte)100);
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
                .contains("LObjBytePredicate: boolean doTest(T a1,byte a2)");
    }


    @Test
    public void isThrowing() {
        assertThat(sut.isThrowing())
            .isFalse();
    }

    //<editor-fold desc="Variants">

    private boolean variantV1(byte a2,Integer a1) {
        return true;
    }

    @Test
    public void compilerSubstituteVariantV1() {
        LObjBytePredicate lambda = LObjBytePredicate./*<T>*/l1(this::variantV1);

        assertThat(lambda).isInstanceOf(LObjBytePredicate.V1.class);
    }

    //</editor-fold>


    @Test void safeCompiles() {
        LObjBytePredicate r1 = LObjBytePredicate.safe(sut); //NOSONAR
        LObjBytePredicateX r2 = LObjBytePredicate.safe(sut); //NOSONAR
    }

    @Test void safePropagates() {
        Object result = LObjBytePredicate.safe(sut);
        assertThat(result).isSameAs(sut);
    }

    @Test void safeProtectsAgainstNpe() {
        Object result = LObjBytePredicate.safe(null);
        assertThat(result).isSameAs(LObjBytePredicate.l(LObjBytePredicate.safe()));
    }

    @Test  void safeSupplierPropagates() {
        LSupplier<LObjBytePredicate<Integer>> supplier = ()->sut;
        Object result = LObjBytePredicate.safeSupplier(supplier);
        assertThat(result).isSameAs(supplier);
    }

    @Test  void safeSupplierProtectsAgainstNpe() {
        Object result = LObjBytePredicate.safeSupplier(null);
        assertThat(result).isSameAs(LObjBytePredicate.safeSupplier());
    }

    @Test  void safeSupplierCompiles() {
        LSupplier<LObjBytePredicate<Integer>> r1 = LObjBytePredicate.safeSupplier(()->sut);  //NOSONAR
    }

}
