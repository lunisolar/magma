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
public class LPredicateTest<T,X extends ParseException> {
    private static final String ORIGINAL_MESSAGE = "Original message";
    private static final String EXCEPTION_WAS_WRAPPED = "Exception was wrapped.";
    private static final String NO_EXCEPTION_WERE_THROWN = "No exception were thrown.";

    private boolean testValue = true;



    private LPredicate<Integer> sut = new LPredicate<Integer>(){
        public  boolean doTest(Integer a)  {
            return testValue;
        }
    };

    private LPredicateX<Integer,X> opposite = new LPredicateX<Integer,X>(){
        public  boolean doTest(Integer a)  throws X {
            return testValue;
        }
    };


    private Predicate<Integer> jre = a -> testValue;



    private LPredicateX<Integer,RuntimeException> sutAlwaysThrowingUnchecked = LPredicate.l(a -> {
            throw new IndexOutOfBoundsException(ORIGINAL_MESSAGE);
    });


    @Test
    public void testTheResult() throws X {
        assertThat(sut.doTest(100))
            .isEqualTo(testValue);
    }

    @Test
    public void testTupleCall() throws X {

        LSingle<Integer> domainObject = Tuple4U.single(100);

        Object result = sut.tupleTest(domainObject);

        assertThat(result)
            .isEqualTo(testValue);
    }

    @Test
    public void testNonNullDoTest() throws X {
        assertThat(sut.nonNullDoTest(100))
            .isEqualTo(testValue);
    }

    @Test
    public void testNestingDoTestUnchecked() throws X {

        // then
        try {
            sutAlwaysThrowingUnchecked.nestingDoTest(100);
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
            sutAlwaysThrowingUnchecked.shovingDoTest(100);
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
        assertThat(sut.doApplyAsBoolean(100))
            .isEqualTo(testValue);

    }


    @Test
    public void testFunctionalInterfaceDescription() throws X {
        assertThat(sut.functionalInterfaceDescription())
            .isEqualTo("LPredicate: boolean doTest(T a)");
    }

    @Test
    public void testLMethod() throws X {
        assertThat(LPredicate.l(a -> testValue ))
            .isInstanceOf(LPredicate.class);
    }

    @Test
    public void testWrapMethod() throws X {
        assertThat(LPredicate.wrap(opposite))
            .isInstanceOf(LPredicate.class);
    }

    @Test
    public void testWrapStdMethod() throws X {
        assertThat(LPredicate.wrap(jre))
            .isInstanceOf(LPredicate.class);
    }

    @Test
    public void testWrapMethodDoNotWrapsRuntimeException() throws X {
        // given
        LPredicateX<Integer,X> sutThrowing = LPredicateX.lX(a -> {
            throw new UnsupportedOperationException(ORIGINAL_MESSAGE);
        });

        // when
        LPredicate<Integer> wrapped = LPredicate.wrap(sutThrowing);

        // then
        try {
            wrapped.doTest(100);
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
        LPredicateX<Integer,ParseException> sutThrowing = LPredicateX.lX(a -> {
            throw new ParseException(ORIGINAL_MESSAGE, 0);
        });

        // when
        LPredicate<Integer> wrapped = LPredicate.wrap(sutThrowing);

        // then
        try {
            wrapped.doTest(100);
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
        LPredicate<Integer> sutThrowing = LPredicate.l(a -> {
            throw new UnsupportedOperationException();
        });

        // when
        LPredicate<Integer> wrapped = sutThrowing.handlePred(handler -> handler
            .wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED));

        // then
        try {
            wrapped.doTest(100);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IllegalArgumentException.class)
                    .hasCauseExactlyInstanceOf(UnsupportedOperationException.class)
                    .hasMessage(EXCEPTION_WAS_WRAPPED);
        }
    }

    @Test
    public void testHandlePredMethodDoNotWrapsOtherExceptionIf() throws X {

        // given
        LPredicate<Integer> sutThrowing = LPredicate.l(a -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LPredicate<Integer> wrapped = sutThrowing.handlePred(handler -> handler
                .wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED)
                .throwIf(IndexOutOfBoundsException.class));

        // then
        try {
            wrapped.doTest(100);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                    .hasNoCause();
        }
    }

@Test
    public void testHandlePredMethodDoNotWrapsOtherExceptionWhen() throws X {

        // given
        LPredicate<Integer> sutThrowing = LPredicate.l(a -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LPredicate<Integer> wrapped = sutThrowing.handlePred(handler -> handler
                .wrapWhen(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED)
                .throwIf(IndexOutOfBoundsException.class));

        // then
        try {
            wrapped.doTest(100);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                    .hasNoCause();
        }
    }


    @Test
    public void testHandlePredMishandlingExceptionIsAllowed() throws X {

        // given
        LPredicate<Integer> sutThrowing = LPredicate.l(a -> {
            throw new UnsupportedOperationException(ORIGINAL_MESSAGE);
        });

        // when
        LPredicate<Integer> wrapped = sutThrowing.handlePred(h -> Function4U.doNothing());

        // then
        try {
            wrapped.doTest(100);
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
        assertThat(sut.negate().doTest(100))
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
        LPredicate<Integer> fun1 = LPredicate.l(a -> f1Result);
        LPredicate<Integer> fun2 = LPredicate.l(a -> f2Result);

        //when
        LPredicate<Integer> andFunction = fun1.and(fun2);
        LPredicate<Integer> orFunction = fun1.or(fun2);
        LPredicate<Integer> xorFunction = fun1.xor(fun2);

        //then
        assertThat(andFunction.doTest(100))
                .isEqualTo(andResult);

        assertThat(orFunction.doTest(100))
                .isEqualTo(orResult);

        assertThat(xorFunction.doTest(100))
                .isEqualTo(xorResult);
    }

    @Test
    public void testIsEqual() throws X  {
        //when
        LPredicate<Integer> equals = LPredicate.isEqual(1);

        //then
        assertThat(equals.doTest(1))
                .isTrue();

        assertThat(equals.doTest(0))
                .isFalse();
    }



    // <editor-fold desc="compose (functional)">

    @Test
    public void testPredCompose() throws X {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LPredicate<Integer> sutO = a -> {
                mainFunctionCalled.set(true);
                assertThat(a).isEqualTo(90);
                return true;
        };

        LFunction<Integer,Integer> before = p0 -> {
            assertThat(p0).isEqualTo(80);
            beforeCalls.incrementAndGet();
            return 90;
        };

        //when
        LPredicate<Integer> function = sutO.predCompose(before);
        function.doTest(80);

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(beforeCalls.get()).isEqualTo(1);
    }

    // </editor-fold>



    // <editor-fold desc="then (functional)">

    @Test
    public void testBoolToFunc0() throws X  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);

        //given (+ some assertions)
        LPredicate<Integer> sutO = a -> {
                mainFunctionCalled.set(true);
                assertThat(a).isEqualTo(80);
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
        LFunction<Integer,Integer> function = sutO.boolToFunc(thenFunction);
        Integer finalValue = function.doApply(80);

        //then - finals
        assertThat(finalValue).isEqualTo(100);
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    @Test
    public void testBoolToToByteFunc1() throws X  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);

        //given (+ some assertions)
        LPredicate<Integer> sutO = a -> {
                mainFunctionCalled.set(true);
                assertThat(a).isEqualTo(80);
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
        LToByteFunction<Integer> function = sutO.boolToToByteFunc(thenFunction);
        byte finalValue = function.doApplyAsByte(80);

        //then - finals
        assertThat(finalValue).isEqualTo((byte)100);
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    @Test
    public void testBoolToToShortFunc2() throws X  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);

        //given (+ some assertions)
        LPredicate<Integer> sutO = a -> {
                mainFunctionCalled.set(true);
                assertThat(a).isEqualTo(80);
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
        LToShortFunction<Integer> function = sutO.boolToToShortFunc(thenFunction);
        short finalValue = function.doApplyAsShort(80);

        //then - finals
        assertThat(finalValue).isEqualTo((short)100);
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    @Test
    public void testBoolToToIntFunc3() throws X  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);

        //given (+ some assertions)
        LPredicate<Integer> sutO = a -> {
                mainFunctionCalled.set(true);
                assertThat(a).isEqualTo(80);
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
        LToIntFunction<Integer> function = sutO.boolToToIntFunc(thenFunction);
        int finalValue = function.doApplyAsInt(80);

        //then - finals
        assertThat(finalValue).isEqualTo(100);
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    @Test
    public void testBoolToToLongFunc4() throws X  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);

        //given (+ some assertions)
        LPredicate<Integer> sutO = a -> {
                mainFunctionCalled.set(true);
                assertThat(a).isEqualTo(80);
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
        LToLongFunction<Integer> function = sutO.boolToToLongFunc(thenFunction);
        long finalValue = function.doApplyAsLong(80);

        //then - finals
        assertThat(finalValue).isEqualTo(100L);
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    @Test
    public void testBoolToToFloatFunc5() throws X  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);

        //given (+ some assertions)
        LPredicate<Integer> sutO = a -> {
                mainFunctionCalled.set(true);
                assertThat(a).isEqualTo(80);
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
        LToFloatFunction<Integer> function = sutO.boolToToFloatFunc(thenFunction);
        float finalValue = function.doApplyAsFloat(80);

        //then - finals
        assertThat(finalValue).isEqualTo(100f);
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    @Test
    public void testBoolToToDoubleFunc6() throws X  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);

        //given (+ some assertions)
        LPredicate<Integer> sutO = a -> {
                mainFunctionCalled.set(true);
                assertThat(a).isEqualTo(80);
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
        LToDoubleFunction<Integer> function = sutO.boolToToDoubleFunc(thenFunction);
        double finalValue = function.doApplyAsDouble(80);

        //then - finals
        assertThat(finalValue).isEqualTo(100d);
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    @Test
    public void testBoolToToCharFunc7() throws X  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);

        //given (+ some assertions)
        LPredicate<Integer> sutO = a -> {
                mainFunctionCalled.set(true);
                assertThat(a).isEqualTo(80);
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
        LToCharFunction<Integer> function = sutO.boolToToCharFunc(thenFunction);
        char finalValue = function.doApplyAsChar(80);

        //then - finals
        assertThat(finalValue).isEqualTo('\u0100');
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    @Test
    public void testBoolToPred8() throws X  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);

        //given (+ some assertions)
        LPredicate<Integer> sutO = a -> {
                mainFunctionCalled.set(true);
                assertThat(a).isEqualTo(80);
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
        LPredicate<Integer> function = sutO.boolToPred(thenFunction);
        boolean finalValue = function.doTest(80);

        //then - finals
        assertThat(finalValue).isEqualTo(true);
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    // </editor-fold>

    @Test
    public void testNesting() {
        assertThat(sut.nestingPred())
            .isSameAs(sut)
            .isInstanceOf(LPredicate.class);
    }

    @Test
    public void testShoving() {
        assertThat(sut.shovingPred())
            .isSameAs(sut)
            .isInstanceOf(LPredicate.class);
    }

    @Test
    public void testNestingX() {
        assertThat(sut.nestingPredX())
            .isSameAs(sut)
            .isInstanceOf(LPredicateX.class);
    }

    @Test
    public void testShovingX() {
        assertThat(sut.shovingPredX())
            .isSameAs(sut)
            .isInstanceOf(LPredicateX.class);
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void testShove() {

        // given
        LPredicate<Integer> sutThrowing = LPredicate.l(a -> {
            throw new UnsupportedOperationException();
        });

        // when
        sutThrowing.shovingPred().doTest(100);
    }

    @Test
    public void testHandlePred() throws X {

        // given
        LPredicate<Integer> sutThrowing = LPredicate.l(a -> {
            throw new UnsupportedOperationException();
        });

        // when
        LPredicate<Integer> wrapped = sutThrowing.handlePred(h -> {
            h.wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED);
        });

        // then
        try {
            wrapped.doTest(100);
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
                .contains("LPredicate: boolean doTest(T a)");
    }


    @Test
    public void isThrowing() {
        assertThat(sut.isThrowing())
            .isFalse();
    }

    @Test void safeCompiles() {
        LPredicate r1 = LPredicate.safe(sut); //NOSONAR
        LPredicateX r2 = LPredicate.safe(sut); //NOSONAR
        Predicate r3 = LPredicate.safe(sut); //NOSONAR
    }

    @Test void safePropagates() {
        Object result = LPredicate.safe(sut);
        assertThat(result).isSameAs(sut);
    }

    @Test void safeProtectsAgainstNpe() {
        Object result = LPredicate.safe(null);
        assertThat(result).isSameAs(LPredicate.l(LPredicate.safe()));
    }

    @Test  void safeSupplierPropagates() {
        LSupplier<LPredicate<Integer>> supplier = ()->sut;
        Object result = LPredicate.safeSupplier(supplier);
        assertThat(result).isSameAs(supplier);
    }

    @Test  void safeSupplierProtectsAgainstNpe() {
        Object result = LPredicate.safeSupplier(null);
        assertThat(result).isSameAs(LPredicate.safeSupplier());
    }

    @Test  void safeSupplierCompiles() {
        LSupplier<LPredicate<Integer>> r1 = LPredicate.safeSupplier(()->sut);  //NOSONAR
        Supplier<LPredicate<Integer>> r2 = LPredicate.safeSupplier(()->sut); //NOSONAR
    }

}
