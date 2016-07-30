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
public class LBytePredicateTest<X extends ParseException> {
    private static final String ORIGINAL_MESSAGE = "Original message";
    private static final String EXCEPTION_WAS_WRAPPED = "Exception was wrapped.";
    private static final String NO_EXCEPTION_WERE_THROWN = "No exception were thrown.";

    private boolean testValue = true;



    private LBytePredicate sut = new LBytePredicate(){
        public  boolean doTest(byte a)  {
            return testValue;
        }
    };

    private LBytePredicateX<X> opposite = new LBytePredicateX<X>(){
        public  boolean doTest(byte a)  throws X {
            return testValue;
        }
    };




    private LBytePredicateX<RuntimeException> sutAlwaysThrowingUnchecked = LBytePredicate.l(a -> {
            throw new IndexOutOfBoundsException(ORIGINAL_MESSAGE);
    });


    @Test
    public void testTheResult() throws X {
        assertThat(sut.doTest((byte)100))
            .isEqualTo(testValue);
    }

    @Test
    public void testTupleCall() throws X {

        LByteSingle domainObject = Tuple4U.byteSingle((byte)100);

        Object result = sut.tupleTest(domainObject);

        assertThat(result)
            .isEqualTo(testValue);
    }

    @Test
    public void testNonNullDoTest() throws X {
        assertThat(sut.nonNullDoTest((byte)100))
            .isEqualTo(testValue);
    }

    @Test
    public void testNestingDoTestUnchecked() throws X {

        // then
        try {
            sutAlwaysThrowingUnchecked.nestingDoTest((byte)100);
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
            sutAlwaysThrowingUnchecked.shovingDoTest((byte)100);
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
        assertThat(sut.doApplyAsBoolean((byte)100))
            .isEqualTo(testValue);

    }


    @Test
    public void testFunctionalInterfaceDescription() throws X {
        assertThat(sut.functionalInterfaceDescription())
            .isEqualTo("LBytePredicate: boolean doTest(byte a)");
    }

    @Test
    public void testLMethod() throws X {
        assertThat(LBytePredicate.l(a -> testValue ))
            .isInstanceOf(LBytePredicate.class);
    }

    @Test
    public void testWrapMethod() throws X {
        assertThat(LBytePredicate.wrap(opposite))
            .isInstanceOf(LBytePredicate.class);
    }

    @Test
    public void testWrapMethodDoNotWrapsRuntimeException() throws X {
        // given
        LBytePredicateX<X> sutThrowing = LBytePredicateX.lX(a -> {
            throw new UnsupportedOperationException(ORIGINAL_MESSAGE);
        });

        // when
        LBytePredicate wrapped = LBytePredicate.wrap(sutThrowing);

        // then
        try {
            wrapped.doTest((byte)100);
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
        LBytePredicateX<ParseException> sutThrowing = LBytePredicateX.lX(a -> {
            throw new ParseException(ORIGINAL_MESSAGE, 0);
        });

        // when
        LBytePredicate wrapped = LBytePredicate.wrap(sutThrowing);

        // then
        try {
            wrapped.doTest((byte)100);
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
        LBytePredicate sutThrowing = LBytePredicate.l(a -> {
            throw new UnsupportedOperationException();
        });

        // when
        LBytePredicate wrapped = sutThrowing.handleBytePred(handler -> handler
            .wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED));

        // then
        try {
            wrapped.doTest((byte)100);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IllegalArgumentException.class)
                    .hasCauseExactlyInstanceOf(UnsupportedOperationException.class)
                    .hasMessage(EXCEPTION_WAS_WRAPPED);
        }
    }

    @Test
    public void testHandleBytePredMethodDoNotWrapsOtherExceptionIf() throws X {

        // given
        LBytePredicate sutThrowing = LBytePredicate.l(a -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LBytePredicate wrapped = sutThrowing.handleBytePred(handler -> handler
                .wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED)
                .throwIf(IndexOutOfBoundsException.class));

        // then
        try {
            wrapped.doTest((byte)100);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                    .hasNoCause();
        }
    }

@Test
    public void testHandleBytePredMethodDoNotWrapsOtherExceptionWhen() throws X {

        // given
        LBytePredicate sutThrowing = LBytePredicate.l(a -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LBytePredicate wrapped = sutThrowing.handleBytePred(handler -> handler
                .wrapWhen(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED)
                .throwIf(IndexOutOfBoundsException.class));

        // then
        try {
            wrapped.doTest((byte)100);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                    .hasNoCause();
        }
    }


    @Test
    public void testHandleBytePredMishandlingExceptionIsAllowed() throws X {

        // given
        LBytePredicate sutThrowing = LBytePredicate.l(a -> {
            throw new UnsupportedOperationException(ORIGINAL_MESSAGE);
        });

        // when
        LBytePredicate wrapped = sutThrowing.handleBytePred(h -> Function4U.doNothing());

        // then
        try {
            wrapped.doTest((byte)100);
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
        assertThat(sut.negate().doTest((byte)100))
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
        LBytePredicate fun1 = LBytePredicate.l(a -> f1Result);
        LBytePredicate fun2 = LBytePredicate.l(a -> f2Result);

        //when
        LBytePredicate andFunction = fun1.and(fun2);
        LBytePredicate orFunction = fun1.or(fun2);
        LBytePredicate xorFunction = fun1.xor(fun2);

        //then
        assertThat(andFunction.doTest((byte)100))
                .isEqualTo(andResult);

        assertThat(orFunction.doTest((byte)100))
                .isEqualTo(orResult);

        assertThat(xorFunction.doTest((byte)100))
                .isEqualTo(xorResult);
    }

    @Test
    public void testIsEqual() throws X  {
        //when
        LBytePredicate equals = LBytePredicate.isEqual((byte)1);

        //then
        assertThat(equals.doTest((byte)1))
                .isTrue();

        assertThat(equals.doTest((byte)0))
                .isFalse();
    }



    // <editor-fold desc="compose (functional)">

    @Test
    public void testBytePredComposeByte() throws X {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LBytePredicate sutO = a -> {
                mainFunctionCalled.set(true);
                assertThat(a).isEqualTo((byte)90);
                return true;
        };

        LByteUnaryOperator before = p0 -> {
            assertThat(p0).isEqualTo((byte)80);
            beforeCalls.incrementAndGet();
            return (byte)90;
        };

        //when
        LBytePredicate function = sutO.bytePredComposeByte(before);
        function.doTest((byte)80);

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(beforeCalls.get()).isEqualTo(1);
    }


    @Test
    public void testBytePredCompose() throws X {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LBytePredicate sutO = a -> {
                mainFunctionCalled.set(true);
                assertThat(a).isEqualTo((byte)90);
                return true;
        };

        LToByteFunction<Integer> before = p0 -> {
            assertThat(p0).isEqualTo(80);
            beforeCalls.incrementAndGet();
            return (byte)90;
        };

        //when
        LPredicate<Integer> function = sutO.bytePredCompose(before);
        function.doTest(80);

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(beforeCalls.get()).isEqualTo(1);
    }

    // </editor-fold>



    // <editor-fold desc="then (functional)">

    @Test
    public void testBoolToByteFunc0() throws X  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);

        //given (+ some assertions)
        LBytePredicate sutO = a -> {
                mainFunctionCalled.set(true);
                assertThat(a).isEqualTo((byte)80);
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
        LByteFunction<Integer> function = sutO.boolToByteFunc(thenFunction);
        Integer finalValue = function.doApply((byte)80);

        //then - finals
        assertThat(finalValue).isEqualTo(100);
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    @Test
    public void testBoolToByteUnaryOp1() throws X  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);

        //given (+ some assertions)
        LBytePredicate sutO = a -> {
                mainFunctionCalled.set(true);
                assertThat(a).isEqualTo((byte)80);
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
        LByteUnaryOperator function = sutO.boolToByteUnaryOp(thenFunction);
        byte finalValue = function.doApplyAsByte((byte)80);

        //then - finals
        assertThat(finalValue).isEqualTo((byte)100);
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    @Test
    public void testBoolToByteToShortFunc2() throws X  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);

        //given (+ some assertions)
        LBytePredicate sutO = a -> {
                mainFunctionCalled.set(true);
                assertThat(a).isEqualTo((byte)80);
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
        LByteToShortFunction function = sutO.boolToByteToShortFunc(thenFunction);
        short finalValue = function.doApplyAsShort((byte)80);

        //then - finals
        assertThat(finalValue).isEqualTo((short)100);
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    @Test
    public void testBoolToByteToIntFunc3() throws X  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);

        //given (+ some assertions)
        LBytePredicate sutO = a -> {
                mainFunctionCalled.set(true);
                assertThat(a).isEqualTo((byte)80);
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
        LByteToIntFunction function = sutO.boolToByteToIntFunc(thenFunction);
        int finalValue = function.doApplyAsInt((byte)80);

        //then - finals
        assertThat(finalValue).isEqualTo(100);
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    @Test
    public void testBoolToByteToLongFunc4() throws X  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);

        //given (+ some assertions)
        LBytePredicate sutO = a -> {
                mainFunctionCalled.set(true);
                assertThat(a).isEqualTo((byte)80);
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
        LByteToLongFunction function = sutO.boolToByteToLongFunc(thenFunction);
        long finalValue = function.doApplyAsLong((byte)80);

        //then - finals
        assertThat(finalValue).isEqualTo(100L);
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    @Test
    public void testBoolToByteToFloatFunc5() throws X  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);

        //given (+ some assertions)
        LBytePredicate sutO = a -> {
                mainFunctionCalled.set(true);
                assertThat(a).isEqualTo((byte)80);
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
        LByteToFloatFunction function = sutO.boolToByteToFloatFunc(thenFunction);
        float finalValue = function.doApplyAsFloat((byte)80);

        //then - finals
        assertThat(finalValue).isEqualTo(100f);
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    @Test
    public void testBoolToByteToDoubleFunc6() throws X  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);

        //given (+ some assertions)
        LBytePredicate sutO = a -> {
                mainFunctionCalled.set(true);
                assertThat(a).isEqualTo((byte)80);
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
        LByteToDoubleFunction function = sutO.boolToByteToDoubleFunc(thenFunction);
        double finalValue = function.doApplyAsDouble((byte)80);

        //then - finals
        assertThat(finalValue).isEqualTo(100d);
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    @Test
    public void testBoolToByteToCharFunc7() throws X  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);

        //given (+ some assertions)
        LBytePredicate sutO = a -> {
                mainFunctionCalled.set(true);
                assertThat(a).isEqualTo((byte)80);
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
        LByteToCharFunction function = sutO.boolToByteToCharFunc(thenFunction);
        char finalValue = function.doApplyAsChar((byte)80);

        //then - finals
        assertThat(finalValue).isEqualTo('\u0100');
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    @Test
    public void testBoolToBytePred8() throws X  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);

        //given (+ some assertions)
        LBytePredicate sutO = a -> {
                mainFunctionCalled.set(true);
                assertThat(a).isEqualTo((byte)80);
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
        LBytePredicate function = sutO.boolToBytePred(thenFunction);
        boolean finalValue = function.doTest((byte)80);

        //then - finals
        assertThat(finalValue).isEqualTo(true);
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    // </editor-fold>

    @Test
    public void testNesting() {
        assertThat(sut.nestingBytePred())
            .isSameAs(sut)
            .isInstanceOf(LBytePredicate.class);
    }

    @Test
    public void testShoving() {
        assertThat(sut.shovingBytePred())
            .isSameAs(sut)
            .isInstanceOf(LBytePredicate.class);
    }

    @Test
    public void testNestingX() {
        assertThat(sut.nestingBytePredX())
            .isSameAs(sut)
            .isInstanceOf(LBytePredicateX.class);
    }

    @Test
    public void testShovingX() {
        assertThat(sut.shovingBytePredX())
            .isSameAs(sut)
            .isInstanceOf(LBytePredicateX.class);
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void testShove() {

        // given
        LBytePredicate sutThrowing = LBytePredicate.l(a -> {
            throw new UnsupportedOperationException();
        });

        // when
        sutThrowing.shovingBytePred().doTest((byte)100);
    }

    @Test
    public void testHandleBytePred() throws X {

        // given
        LBytePredicate sutThrowing = LBytePredicate.l(a -> {
            throw new UnsupportedOperationException();
        });

        // when
        LBytePredicate wrapped = sutThrowing.handleBytePred(h -> {
            h.wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED);
        });

        // then
        try {
            wrapped.doTest((byte)100);
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
                .contains("LBytePredicate: boolean doTest(byte a)");
    }


    @Test
    public void isThrowing() {
        assertThat(sut.isThrowing())
            .isFalse();
    }

    @Test void safeCompiles() {
        LBytePredicate r1 = LBytePredicate.safe(sut); //NOSONAR
        LBytePredicateX r2 = LBytePredicate.safe(sut); //NOSONAR
    }

    @Test void safePropagates() {
        Object result = LBytePredicate.safe(sut);
        assertThat(result).isSameAs(sut);
    }

    @Test void safeProtectsAgainstNpe() {
        Object result = LBytePredicate.safe(null);
        assertThat(result).isSameAs(LBytePredicate.l(LBytePredicate.safe()));
    }

    @Test  void safeSupplierPropagates() {
        LSupplier<LBytePredicate> supplier = ()->sut;
        Object result = LBytePredicate.safeSupplier(supplier);
        assertThat(result).isSameAs(supplier);
    }

    @Test  void safeSupplierProtectsAgainstNpe() {
        Object result = LBytePredicate.safeSupplier(null);
        assertThat(result).isSameAs(LBytePredicate.safeSupplier());
    }

    @Test  void safeSupplierCompiles() {
        LSupplier<LBytePredicate> r1 = LBytePredicate.safeSupplier(()->sut);  //NOSONAR
    }

}
