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
public class LFloatPredicateTest<X extends ParseException> {
    private static final String ORIGINAL_MESSAGE = "Original message";
    private static final String EXCEPTION_WAS_WRAPPED = "Exception was wrapped.";
    private static final String NO_EXCEPTION_WERE_THROWN = "No exception were thrown.";

    private boolean testValue = true;



    private LFloatPredicate sut = new LFloatPredicate(){
        public  boolean doTest(float a)  {
            return testValue;
        }
    };

    private LFloatPredicateX<X> opposite = new LFloatPredicateX<X>(){
        public  boolean doTest(float a)  throws X {
            return testValue;
        }
    };




    private LFloatPredicateX<RuntimeException> sutAlwaysThrowingUnchecked = LFloatPredicate.l(a -> {
            throw new IndexOutOfBoundsException(ORIGINAL_MESSAGE);
    });


    @Test
    public void testTheResult() throws X {
        assertThat(sut.doTest(100f))
            .isEqualTo(testValue);
    }

    @Test
    public void testTupleCall() throws X {

        LFloatSingle domainObject = Tuple4U.floatSingle(100f);

        Object result = sut.tupleTest(domainObject);

        assertThat(result)
            .isEqualTo(testValue);
    }

    @Test
    public void testNonNullDoTest() throws X {
        assertThat(sut.nonNullDoTest(100f))
            .isEqualTo(testValue);
    }

    @Test
    public void testNestingDoTestUnchecked() throws X {

        // then
        try {
            sutAlwaysThrowingUnchecked.nestingDoTest(100f);
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
            sutAlwaysThrowingUnchecked.shovingDoTest(100f);
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
        assertThat(sut.doApplyAsBoolean(100f))
            .isEqualTo(testValue);

    }


    @Test
    public void testFunctionalInterfaceDescription() throws X {
        assertThat(sut.functionalInterfaceDescription())
            .isEqualTo("LFloatPredicate: boolean doTest(float a)");
    }

    @Test
    public void testLMethod() throws X {
        assertThat(LFloatPredicate.l(a -> testValue ))
            .isInstanceOf(LFloatPredicate.class);
    }

    @Test
    public void testWrapMethod() throws X {
        assertThat(LFloatPredicate.wrap(opposite))
            .isInstanceOf(LFloatPredicate.class);
    }

    @Test
    public void testWrapMethodDoNotWrapsRuntimeException() throws X {
        // given
        LFloatPredicateX<X> sutThrowing = LFloatPredicateX.lX(a -> {
            throw new UnsupportedOperationException(ORIGINAL_MESSAGE);
        });

        // when
        LFloatPredicate wrapped = LFloatPredicate.wrap(sutThrowing);

        // then
        try {
            wrapped.doTest(100f);
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
        LFloatPredicateX<ParseException> sutThrowing = LFloatPredicateX.lX(a -> {
            throw new ParseException(ORIGINAL_MESSAGE, 0);
        });

        // when
        LFloatPredicate wrapped = LFloatPredicate.wrap(sutThrowing);

        // then
        try {
            wrapped.doTest(100f);
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
        LFloatPredicate sutThrowing = LFloatPredicate.l(a -> {
            throw new UnsupportedOperationException();
        });

        // when
        LFloatPredicate wrapped = sutThrowing.handleFloatPred(handler -> handler
            .wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED));

        // then
        try {
            wrapped.doTest(100f);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IllegalArgumentException.class)
                    .hasCauseExactlyInstanceOf(UnsupportedOperationException.class)
                    .hasMessage(EXCEPTION_WAS_WRAPPED);
        }
    }

    @Test
    public void testHandleFloatPredMethodDoNotWrapsOtherExceptionIf() throws X {

        // given
        LFloatPredicate sutThrowing = LFloatPredicate.l(a -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LFloatPredicate wrapped = sutThrowing.handleFloatPred(handler -> handler
                .wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED)
                .throwIf(IndexOutOfBoundsException.class));

        // then
        try {
            wrapped.doTest(100f);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                    .hasNoCause();
        }
    }

@Test
    public void testHandleFloatPredMethodDoNotWrapsOtherExceptionWhen() throws X {

        // given
        LFloatPredicate sutThrowing = LFloatPredicate.l(a -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LFloatPredicate wrapped = sutThrowing.handleFloatPred(handler -> handler
                .wrapWhen(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED)
                .throwIf(IndexOutOfBoundsException.class));

        // then
        try {
            wrapped.doTest(100f);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                    .hasNoCause();
        }
    }


    @Test
    public void testHandleFloatPredMishandlingExceptionIsAllowed() throws X {

        // given
        LFloatPredicate sutThrowing = LFloatPredicate.l(a -> {
            throw new UnsupportedOperationException(ORIGINAL_MESSAGE);
        });

        // when
        LFloatPredicate wrapped = sutThrowing.handleFloatPred(h -> Function4U.doNothing());

        // then
        try {
            wrapped.doTest(100f);
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
        assertThat(sut.negate().doTest(100f))
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
        LFloatPredicate fun1 = LFloatPredicate.l(a -> f1Result);
        LFloatPredicate fun2 = LFloatPredicate.l(a -> f2Result);

        //when
        LFloatPredicate andFunction = fun1.and(fun2);
        LFloatPredicate orFunction = fun1.or(fun2);
        LFloatPredicate xorFunction = fun1.xor(fun2);

        //then
        assertThat(andFunction.doTest(100f))
                .isEqualTo(andResult);

        assertThat(orFunction.doTest(100f))
                .isEqualTo(orResult);

        assertThat(xorFunction.doTest(100f))
                .isEqualTo(xorResult);
    }

    @Test
    public void testIsEqual() throws X  {
        //when
        LFloatPredicate equals = LFloatPredicate.isEqual(1f);

        //then
        assertThat(equals.doTest(1f))
                .isTrue();

        assertThat(equals.doTest(0f))
                .isFalse();
    }



    // <editor-fold desc="compose (functional)">

    @Test
    public void testFloatPredComposeFloat() throws X {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LFloatPredicate sutO = a -> {
                mainFunctionCalled.set(true);
                assertThat(a).isEqualTo(90f);
                return true;
        };

        LFloatUnaryOperator before = p0 -> {
            assertThat(p0).isEqualTo(80f);
            beforeCalls.incrementAndGet();
            return 90f;
        };

        //when
        LFloatPredicate function = sutO.floatPredComposeFloat(before);
        function.doTest(80f);

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(beforeCalls.get()).isEqualTo(1);
    }


    @Test
    public void testFloatPredCompose() throws X {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LFloatPredicate sutO = a -> {
                mainFunctionCalled.set(true);
                assertThat(a).isEqualTo(90f);
                return true;
        };

        LToFloatFunction<Integer> before = p0 -> {
            assertThat(p0).isEqualTo(80);
            beforeCalls.incrementAndGet();
            return 90f;
        };

        //when
        LPredicate<Integer> function = sutO.floatPredCompose(before);
        function.doTest(80);

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(beforeCalls.get()).isEqualTo(1);
    }

    // </editor-fold>



    // <editor-fold desc="then (functional)">

    @Test
    public void testBoolToFloatFunc0() throws X  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);

        //given (+ some assertions)
        LFloatPredicate sutO = a -> {
                mainFunctionCalled.set(true);
                assertThat(a).isEqualTo(80f);
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
        LFloatFunction<Integer> function = sutO.boolToFloatFunc(thenFunction);
        Integer finalValue = function.doApply(80f);

        //then - finals
        assertThat(finalValue).isEqualTo(100);
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    @Test
    public void testBoolToFloatToByteFunc1() throws X  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);

        //given (+ some assertions)
        LFloatPredicate sutO = a -> {
                mainFunctionCalled.set(true);
                assertThat(a).isEqualTo(80f);
                return true;
        };

        LBoolToByteFunction thenFunction = p -> {
                thenFunctionCalled.set(true);
                // boolean
                assertThat(p).isEqualTo(true);
                // byte
                return (byte)100;
        };

        //when
        LFloatToByteFunction function = sutO.boolToFloatToByteFunc(thenFunction);
        byte finalValue = function.doApplyAsByte(80f);

        //then - finals
        assertThat(finalValue).isEqualTo((byte)100);
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    @Test
    public void testBoolToFloatToShortFunc2() throws X  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);

        //given (+ some assertions)
        LFloatPredicate sutO = a -> {
                mainFunctionCalled.set(true);
                assertThat(a).isEqualTo(80f);
                return true;
        };

        LBoolToShortFunction thenFunction = p -> {
                thenFunctionCalled.set(true);
                // boolean
                assertThat(p).isEqualTo(true);
                // short
                return (short)100;
        };

        //when
        LFloatToShortFunction function = sutO.boolToFloatToShortFunc(thenFunction);
        short finalValue = function.doApplyAsShort(80f);

        //then - finals
        assertThat(finalValue).isEqualTo((short)100);
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    @Test
    public void testBoolToFloatToIntFunc3() throws X  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);

        //given (+ some assertions)
        LFloatPredicate sutO = a -> {
                mainFunctionCalled.set(true);
                assertThat(a).isEqualTo(80f);
                return true;
        };

        LBoolToIntFunction thenFunction = p -> {
                thenFunctionCalled.set(true);
                // boolean
                assertThat(p).isEqualTo(true);
                // int
                return 100;
        };

        //when
        LFloatToIntFunction function = sutO.boolToFloatToIntFunc(thenFunction);
        int finalValue = function.doApplyAsInt(80f);

        //then - finals
        assertThat(finalValue).isEqualTo(100);
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    @Test
    public void testBoolToFloatToLongFunc4() throws X  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);

        //given (+ some assertions)
        LFloatPredicate sutO = a -> {
                mainFunctionCalled.set(true);
                assertThat(a).isEqualTo(80f);
                return true;
        };

        LBoolToLongFunction thenFunction = p -> {
                thenFunctionCalled.set(true);
                // boolean
                assertThat(p).isEqualTo(true);
                // long
                return 100L;
        };

        //when
        LFloatToLongFunction function = sutO.boolToFloatToLongFunc(thenFunction);
        long finalValue = function.doApplyAsLong(80f);

        //then - finals
        assertThat(finalValue).isEqualTo(100L);
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    @Test
    public void testBoolToFloatUnaryOp5() throws X  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);

        //given (+ some assertions)
        LFloatPredicate sutO = a -> {
                mainFunctionCalled.set(true);
                assertThat(a).isEqualTo(80f);
                return true;
        };

        LBoolToFloatFunction thenFunction = p -> {
                thenFunctionCalled.set(true);
                // boolean
                assertThat(p).isEqualTo(true);
                // float
                return 100f;
        };

        //when
        LFloatUnaryOperator function = sutO.boolToFloatUnaryOp(thenFunction);
        float finalValue = function.doApplyAsFloat(80f);

        //then - finals
        assertThat(finalValue).isEqualTo(100f);
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    @Test
    public void testBoolToFloatToDoubleFunc6() throws X  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);

        //given (+ some assertions)
        LFloatPredicate sutO = a -> {
                mainFunctionCalled.set(true);
                assertThat(a).isEqualTo(80f);
                return true;
        };

        LBoolToDoubleFunction thenFunction = p -> {
                thenFunctionCalled.set(true);
                // boolean
                assertThat(p).isEqualTo(true);
                // double
                return 100d;
        };

        //when
        LFloatToDoubleFunction function = sutO.boolToFloatToDoubleFunc(thenFunction);
        double finalValue = function.doApplyAsDouble(80f);

        //then - finals
        assertThat(finalValue).isEqualTo(100d);
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    @Test
    public void testBoolToFloatToCharFunc7() throws X  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);

        //given (+ some assertions)
        LFloatPredicate sutO = a -> {
                mainFunctionCalled.set(true);
                assertThat(a).isEqualTo(80f);
                return true;
        };

        LBoolToCharFunction thenFunction = p -> {
                thenFunctionCalled.set(true);
                // boolean
                assertThat(p).isEqualTo(true);
                // char
                return '\u0100';
        };

        //when
        LFloatToCharFunction function = sutO.boolToFloatToCharFunc(thenFunction);
        char finalValue = function.doApplyAsChar(80f);

        //then - finals
        assertThat(finalValue).isEqualTo('\u0100');
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    @Test
    public void testBoolToFloatPred8() throws X  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);

        //given (+ some assertions)
        LFloatPredicate sutO = a -> {
                mainFunctionCalled.set(true);
                assertThat(a).isEqualTo(80f);
                return true;
        };

        LLogicalOperator thenFunction = p -> {
                thenFunctionCalled.set(true);
                // boolean
                assertThat(p).isEqualTo(true);
                // boolean
                return true;
        };

        //when
        LFloatPredicate function = sutO.boolToFloatPred(thenFunction);
        boolean finalValue = function.doTest(80f);

        //then - finals
        assertThat(finalValue).isEqualTo(true);
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    // </editor-fold>

    @Test
    public void testNesting() {
        assertThat(sut.nestingFloatPred())
            .isSameAs(sut)
            .isInstanceOf(LFloatPredicate.class);
    }

    @Test
    public void testShoving() {
        assertThat(sut.shovingFloatPred())
            .isSameAs(sut)
            .isInstanceOf(LFloatPredicate.class);
    }

    @Test
    public void testNestingX() {
        assertThat(sut.nestingFloatPredX())
            .isSameAs(sut)
            .isInstanceOf(LFloatPredicateX.class);
    }

    @Test
    public void testShovingX() {
        assertThat(sut.shovingFloatPredX())
            .isSameAs(sut)
            .isInstanceOf(LFloatPredicateX.class);
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void testShove() {

        // given
        LFloatPredicate sutThrowing = LFloatPredicate.l(a -> {
            throw new UnsupportedOperationException();
        });

        // when
        sutThrowing.shovingFloatPred().doTest(100f);
    }

    @Test
    public void testHandleFloatPred() throws X {

        // given
        LFloatPredicate sutThrowing = LFloatPredicate.l(a -> {
            throw new UnsupportedOperationException();
        });

        // when
        LFloatPredicate wrapped = sutThrowing.handleFloatPred(h -> {
            h.wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED);
        });

        // then
        try {
            wrapped.doTest(100f);
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
                .contains("LFloatPredicate: boolean doTest(float a)");
    }


    @Test
    public void isThrowing() {
        assertThat(sut.isThrowing())
            .isFalse();
    }

    @Test void safeCompiles() {
        LFloatPredicate r1 = LFloatPredicate.safe(sut); //NOSONAR
        LFloatPredicateX r2 = LFloatPredicate.safe(sut); //NOSONAR
    }

    @Test void safePropagates() {
        Object result = LFloatPredicate.safe(sut);
        assertThat(result).isSameAs(sut);
    }

    @Test void safeProtectsAgainstNpe() {
        Object result = LFloatPredicate.safe(null);
        assertThat(result).isSameAs(LFloatPredicate.l(LFloatPredicate.safe()));
    }

    @Test  void safeSupplierPropagates() {
        LSupplier<LFloatPredicate> supplier = ()->sut;
        Object result = LFloatPredicate.safeSupplier(supplier);
        assertThat(result).isSameAs(supplier);
    }

    @Test  void safeSupplierProtectsAgainstNpe() {
        Object result = LFloatPredicate.safeSupplier(null);
        assertThat(result).isSameAs(LFloatPredicate.safeSupplier());
    }

    @Test  void safeSupplierCompiles() {
        LSupplier<LFloatPredicate> r1 = LFloatPredicate.safeSupplier(()->sut);  //NOSONAR
    }

}
