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
public class LBiBytePredicateTest<X extends ParseException> {
    private static final String ORIGINAL_MESSAGE = "Original message";
    private static final String EXCEPTION_WAS_WRAPPED = "Exception was wrapped.";
    private static final String NO_EXCEPTION_WERE_THROWN = "No exception were thrown.";

    private boolean testValue = true;



    private LBiBytePredicate sut = new LBiBytePredicate(){
        public  boolean doTest(byte a1,byte a2)  {
            return testValue;
        }
    };

    private LBiBytePredicateX<X> opposite = new LBiBytePredicateX<X>(){
        public  boolean doTest(byte a1,byte a2)  throws X {
            return testValue;
        }
    };




    private LBiBytePredicateX<RuntimeException> sutAlwaysThrowingUnchecked = LBiBytePredicate.l((a1,a2) -> {
            throw new IndexOutOfBoundsException(ORIGINAL_MESSAGE);
    });


    @Test
    public void testTheResult() throws X {
        assertThat(sut.doTest((byte)100,(byte)100))
            .isEqualTo(testValue);
    }

    @Test
    public void testTupleCall() throws X {

        LBytePair domainObject = Tuple4U.lBytePair((byte)100,(byte)100);

        Object result = sut.tupleTest(domainObject);

        assertThat(result)
            .isEqualTo(testValue);
    }

    @Test
    public void testNonNullDoTest() throws X {
        assertThat(sut.nonNullDoTest((byte)100,(byte)100))
            .isEqualTo(testValue);
    }

    @Test
    public void testNestingDoTestUnchecked() throws X {

        // then
        try {
            sutAlwaysThrowingUnchecked.nestingDoTest((byte)100,(byte)100);
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
            sutAlwaysThrowingUnchecked.shovingDoTest((byte)100,(byte)100);
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
        assertThat(sut.doApplyAsBoolean((byte)100,(byte)100))
            .isEqualTo(testValue);

    }


    @Test
    public void testFunctionalInterfaceDescription() throws X {
        assertThat(sut.functionalInterfaceDescription())
            .isEqualTo("LBiBytePredicate: boolean doTest(byte a1,byte a2)");
    }

    @Test
    public void testLMethod() throws X {
        assertThat(LBiBytePredicate.l((a1,a2) -> testValue ))
            .isInstanceOf(LBiBytePredicate.class);
    }

    @Test
    public void testWrapMethod() throws X {
        assertThat(LBiBytePredicate.wrap(opposite))
            .isInstanceOf(LBiBytePredicate.class);
    }

    @Test
    public void testWrapMethodDoNotWrapsRuntimeException() throws X {
        // given
        LBiBytePredicateX<X> sutThrowing = LBiBytePredicateX.lX((a1,a2) -> {
            throw new UnsupportedOperationException(ORIGINAL_MESSAGE);
        });

        // when
        LBiBytePredicate wrapped = LBiBytePredicate.wrap(sutThrowing);

        // then
        try {
            wrapped.doTest((byte)100,(byte)100);
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
        LBiBytePredicateX<ParseException> sutThrowing = LBiBytePredicateX.lX((a1,a2) -> {
            throw new ParseException(ORIGINAL_MESSAGE, 0);
        });

        // when
        LBiBytePredicate wrapped = LBiBytePredicate.wrap(sutThrowing);

        // then
        try {
            wrapped.doTest((byte)100,(byte)100);
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
        LBiBytePredicate sutThrowing = LBiBytePredicate.l((a1,a2) -> {
            throw new UnsupportedOperationException();
        });

        // when
        LBiBytePredicate wrapped = sutThrowing.handleBiBytePred(handler -> handler
            .wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED));

        // then
        try {
            wrapped.doTest((byte)100,(byte)100);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IllegalArgumentException.class)
                    .hasCauseExactlyInstanceOf(UnsupportedOperationException.class)
                    .hasMessage(EXCEPTION_WAS_WRAPPED);
        }
    }

    @Test
    public void testHandleBiBytePredMethodDoNotWrapsOtherExceptionIf() throws X {

        // given
        LBiBytePredicate sutThrowing = LBiBytePredicate.l((a1,a2) -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LBiBytePredicate wrapped = sutThrowing.handleBiBytePred(handler -> handler
                .wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED)
                .throwIf(IndexOutOfBoundsException.class));

        // then
        try {
            wrapped.doTest((byte)100,(byte)100);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                    .hasNoCause();
        }
    }

@Test
    public void testHandleBiBytePredMethodDoNotWrapsOtherExceptionWhen() throws X {

        // given
        LBiBytePredicate sutThrowing = LBiBytePredicate.l((a1,a2) -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LBiBytePredicate wrapped = sutThrowing.handleBiBytePred(handler -> handler
                .wrapWhen(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED)
                .throwIf(IndexOutOfBoundsException.class));

        // then
        try {
            wrapped.doTest((byte)100,(byte)100);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                    .hasNoCause();
        }
    }


    @Test
    public void testHandleBiBytePredMishandlingExceptionIsAllowed() throws X {

        // given
        LBiBytePredicate sutThrowing = LBiBytePredicate.l((a1,a2) -> {
            throw new UnsupportedOperationException(ORIGINAL_MESSAGE);
        });

        // when
        LBiBytePredicate wrapped = sutThrowing.handleBiBytePred(h -> Function4U.doNothing());

        // then
        try {
            wrapped.doTest((byte)100,(byte)100);
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
        assertThat(sut.negate().doTest((byte)100,(byte)100))
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
        LBiBytePredicate fun1 = LBiBytePredicate.l((a1,a2) -> f1Result);
        LBiBytePredicate fun2 = LBiBytePredicate.l((a1,a2) -> f2Result);

        //when
        LBiBytePredicate andFunction = fun1.and(fun2);
        LBiBytePredicate orFunction = fun1.or(fun2);
        LBiBytePredicate xorFunction = fun1.xor(fun2);

        //then
        assertThat(andFunction.doTest((byte)100,(byte)100))
                .isEqualTo(andResult);

        assertThat(orFunction.doTest((byte)100,(byte)100))
                .isEqualTo(orResult);

        assertThat(xorFunction.doTest((byte)100,(byte)100))
                .isEqualTo(xorResult);
    }

    @Test
    public void testIsEqual() throws X  {
        //when
        LBiBytePredicate equals = LBiBytePredicate.isEqual((byte)1,(byte)1);

        //then
        assertThat(equals.doTest((byte)1,(byte)1))
                .isTrue();

        assertThat(equals.doTest((byte)0,(byte)0))
                .isFalse();
    }



    // <editor-fold desc="compose (functional)">

    @Test
    public void testBiBytePredComposeByte() throws X {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LBiBytePredicate sutO = (byte a1,byte a2) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo((byte)90);
                assertThat(a2).isEqualTo((byte)91);
                return true;
        };

        LByteUnaryOperator before1 = p0 -> {
            assertThat(p0).isEqualTo((byte)80);
            beforeCalls.incrementAndGet();
            return (byte)90;
        };
        LByteUnaryOperator before2 = p1 -> {
            assertThat(p1).isEqualTo((byte)81);
            beforeCalls.incrementAndGet();
            return (byte)91;
        };

        //when
        LBiBytePredicate function = sutO.biBytePredComposeByte(before1,before2);
        function.doTest((byte)80,(byte)81);

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(beforeCalls.get()).isEqualTo(2);
    }


    @Test
    public void testBiBytePredCompose() throws X {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LBiBytePredicate sutO = (byte a1,byte a2) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo((byte)90);
                assertThat(a2).isEqualTo((byte)91);
                return true;
        };

        LToByteFunction<Integer> before1 = p0 -> {
            assertThat(p0).isEqualTo(80);
            beforeCalls.incrementAndGet();
            return (byte)90;
        };
        LToByteFunction<Integer> before2 = p1 -> {
            assertThat(p1).isEqualTo(81);
            beforeCalls.incrementAndGet();
            return (byte)91;
        };

        //when
        LBiPredicate<Integer,Integer> function = sutO.biBytePredCompose(before1,before2);
        function.doTest(80,81);

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(beforeCalls.get()).isEqualTo(2);
    }

    // </editor-fold>



    // <editor-fold desc="then (functional)">

    @Test
    public void testBoolToBiByteFunction0() throws X  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);

        //given (+ some assertions)
        LBiBytePredicate sutO = (a1,a2) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo((byte)80);
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
        LBiByteFunction<Integer> function = sutO.boolToBiByteFunction(thenFunction);
        Integer finalValue = function.doApply((byte)80,(byte)81);

        //then - finals
        assertThat(finalValue).isEqualTo(100);
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    // </editor-fold>

    @Test
    public void testNesting() {
        assertThat(sut.nestingBiBytePred())
            .isSameAs(sut)
            .isInstanceOf(LBiBytePredicate.class);
    }

    @Test
    public void testShoving() {
        assertThat(sut.shovingBiBytePred())
            .isSameAs(sut)
            .isInstanceOf(LBiBytePredicate.class);
    }

    @Test
    public void testNestingX() {
        assertThat(sut.nestingBiBytePredX())
            .isSameAs(sut)
            .isInstanceOf(LBiBytePredicateX.class);
    }

    @Test
    public void testShovingX() {
        assertThat(sut.shovingBiBytePredX())
            .isSameAs(sut)
            .isInstanceOf(LBiBytePredicateX.class);
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void testShove() {

        // given
        LBiBytePredicate sutThrowing = LBiBytePredicate.l((a1,a2) -> {
            throw new UnsupportedOperationException();
        });

        // when
        sutThrowing.shovingBiBytePred().doTest((byte)100,(byte)100);
    }

    @Test
    public void testHandleBiBytePred() throws X {

        // given
        LBiBytePredicate sutThrowing = LBiBytePredicate.l((a1,a2) -> {
            throw new UnsupportedOperationException();
        });

        // when
        LBiBytePredicate wrapped = sutThrowing.handleBiBytePred(h -> {
            h.wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED);
        });

        // then
        try {
            wrapped.doTest((byte)100,(byte)100);
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
                .contains("LBiBytePredicate: boolean doTest(byte a1,byte a2)");
    }


    @Test
    public void isThrowing() {
        assertThat(sut.isThrowing())
            .isFalse();
    }

    //<editor-fold desc="Variants">

    private boolean variantV1(byte a2,byte a1) {
        return true;
    }

    @Test
    public void compilerSubstituteVariantV1() {
        LBiBytePredicate lambda = LBiBytePredicate./**/l1(this::variantV1);

        assertThat(lambda).isInstanceOf(LBiBytePredicate.V1.class);
    }

    //</editor-fold>


    @Test void safeCompiles() {
        LBiBytePredicate r1 = LBiBytePredicate.safe(sut); //NOSONAR
        LBiBytePredicateX r2 = LBiBytePredicate.safe(sut); //NOSONAR
    }

    @Test void safePropagates() {
        Object result = LBiBytePredicate.safe(sut);
        assertThat(result).isSameAs(sut);
    }

    @Test void safeProtectsAgainstNpe() {
        Object result = LBiBytePredicate.safe(null);
        assertThat(result).isSameAs(LBiBytePredicate.l(LBiBytePredicate.safe()));
    }

    @Test  void safeSupplierPropagates() {
        LSupplier<LBiBytePredicate> supplier = ()->sut;
        Object result = LBiBytePredicate.safeSupplier(supplier);
        assertThat(result).isSameAs(supplier);
    }

    @Test  void safeSupplierProtectsAgainstNpe() {
        Object result = LBiBytePredicate.safeSupplier(null);
        assertThat(result).isSameAs(LBiBytePredicate.safeSupplier());
    }

    @Test  void safeSupplierCompiles() {
        LSupplier<LBiBytePredicate> r1 = LBiBytePredicate.safeSupplier(()->sut);  //NOSONAR
    }

}
