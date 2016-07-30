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
public class LIntPredicateXTest<X extends ParseException> {
    private static final String ORIGINAL_MESSAGE = "Original message";
    private static final String EXCEPTION_WAS_WRAPPED = "Exception was wrapped.";
    private static final String NO_EXCEPTION_WERE_THROWN = "No exception were thrown.";

    private boolean testValue = true;



    private LIntPredicateX<X> sut = new LIntPredicateX<X>(){
        public  boolean doTest(int a)  throws X {
            return testValue;
        }
    };

    private LIntPredicate opposite = new LIntPredicate(){
        public  boolean doTest(int a)  {
            return testValue;
        }
    };


    private IntPredicate jre = a -> testValue;


    private LIntPredicateX<ParseException> sutAlwaysThrowing = LIntPredicateX.lX(a -> {
            throw new ParseException(ORIGINAL_MESSAGE, 0);
    });

    private LIntPredicateX<RuntimeException> sutAlwaysThrowingUnchecked = LIntPredicateX.lX(a -> {
            throw new IndexOutOfBoundsException(ORIGINAL_MESSAGE);
    });


    @Test
    public void testTheResult() throws X {
        assertThat(sut.doTest(100))
            .isEqualTo(testValue);
    }

    @Test
    public void testTupleCall() throws X {

        LIntSingle domainObject = Tuple4U.intSingle(100);

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
    public void testNestingDoTestChecked() throws X {

        // then
        try {
            sutAlwaysThrowing.nestingDoTest(100);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(NestedException.class)
                    .hasCauseExactlyInstanceOf(ParseException.class)
                    .hasMessage(ORIGINAL_MESSAGE);
        }
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
    public void testShovingDoTestChecked() throws X {

        // then
        try {
            sutAlwaysThrowing.shovingDoTest(100);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(ParseException.class)
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
            .isEqualTo("LIntPredicateX: boolean doTest(int a) throws X");
    }

    @Test
    public void testLXMethod() throws X {
        assertThat(LIntPredicateX.lX(a -> testValue ))
            .isInstanceOf(LIntPredicateX.class);
    }

    @Test
    public void testWrapXMethod() throws X {
        assertThat(LIntPredicateX.wrapX(opposite))
            .isInstanceOf(LIntPredicateX.class);
    }

    @Test
    public void testWrapStdMethod() throws X {
        assertThat(LIntPredicateX.wrap(jre))
            .isInstanceOf(LIntPredicateX.class);
    }


    @Test
    public void testHandlingDoTestMethodWrapsTheException() throws X {

        // given
        LIntPredicateX<X> sutThrowing = LIntPredicateX.lX(a -> {
            throw new UnsupportedOperationException();
        });

        // when
        LIntPredicateX<RuntimeException> wrapped = sutThrowing.handleIntPredX(handler -> handler
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
    public void testHandleIntPredXMethodDoNotWrapsOtherExceptionIf() throws X {

        // given
        LIntPredicateX<X> sutThrowing = LIntPredicateX.lX(a -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LIntPredicateX<X> wrapped = sutThrowing.handleIntPredX(handler -> handler
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
    public void testHandleIntPredXMethodDoNotWrapsOtherExceptionWhen() throws X {

        // given
        LIntPredicateX<X> sutThrowing = LIntPredicateX.lX(a -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LIntPredicateX<X> wrapped = sutThrowing.handleIntPredX(handler -> handler
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
    public void testHandleIntPredXMishandlingExceptionIsAllowed() throws X {

        // given
        LIntPredicateX<X> sutThrowing = LIntPredicateX.lX(a -> {
            throw (X) new ParseException(ORIGINAL_MESSAGE, 0);
        });

        // when
        LIntPredicateX<X> wrapped = sutThrowing.handleIntPredX(h -> Function4U.doNothing());

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
        LIntPredicateX<X> fun1 = LIntPredicateX.lX(a -> f1Result);
        LIntPredicateX<X> fun2 = LIntPredicateX.lX(a -> f2Result);

        //when
        LIntPredicateX<X> andFunction = fun1.and(fun2);
        LIntPredicateX<X> orFunction = fun1.or(fun2);
        LIntPredicateX<X> xorFunction = fun1.xor(fun2);

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
        LIntPredicateX<X> equals = LIntPredicateX.isEqual(1);

        //then
        assertThat(equals.doTest(1))
                .isTrue();

        assertThat(equals.doTest(0))
                .isFalse();
    }



    // <editor-fold desc="compose (functional)">

    @Test
    public void testIntPredComposeInt() throws X {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LIntPredicateX<X> sutO = a -> {
                mainFunctionCalled.set(true);
                assertThat(a).isEqualTo(90);
                return true;
        };

        LIntUnaryOperatorX<X> before = p0 -> {
            assertThat(p0).isEqualTo(80);
            beforeCalls.incrementAndGet();
            return 90;
        };

        //when
        LIntPredicateX<X> function = sutO.intPredComposeInt(before);
        function.doTest(80);

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(beforeCalls.get()).isEqualTo(1);
    }


    @Test
    public void testIntPredCompose() throws X {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LIntPredicateX<X> sutO = a -> {
                mainFunctionCalled.set(true);
                assertThat(a).isEqualTo(90);
                return true;
        };

        LToIntFunctionX<Integer,X> before = p0 -> {
            assertThat(p0).isEqualTo(80);
            beforeCalls.incrementAndGet();
            return 90;
        };

        //when
        LPredicateX<Integer,X> function = sutO.intPredCompose(before);
        function.doTest(80);

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(beforeCalls.get()).isEqualTo(1);
    }

    // </editor-fold>



    // <editor-fold desc="then (functional)">

    @Test
    public void testBoolToIntFunc0() throws X  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);

        //given (+ some assertions)
        LIntPredicateX<X> sutO = a -> {
                mainFunctionCalled.set(true);
                assertThat(a).isEqualTo(80);
                return true;
        };

        LBoolFunctionX<Integer,X> thenFunction = p -> {
                thenFunctionCalled.set(true);
                // boolean
                assertThat(p).isEqualTo(true);
                // Integer
                return 100;
        };

        //when
        LIntFunctionX<Integer,X> function = sutO.boolToIntFunc(thenFunction);
        Integer finalValue = function.doApply(80);

        //then - finals
        assertThat(finalValue).isEqualTo(100);
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    @Test
    public void testBoolToIntToByteFunc1() throws X  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);

        //given (+ some assertions)
        LIntPredicateX<X> sutO = a -> {
                mainFunctionCalled.set(true);
                assertThat(a).isEqualTo(80);
                return true;
        };

        LBoolToByteFunctionX<X> thenFunction = p -> {
                thenFunctionCalled.set(true);
                // boolean
                assertThat(p).isEqualTo(true);
                // byte
                return (byte)100;
        };

        //when
        LIntToByteFunctionX<X> function = sutO.boolToIntToByteFunc(thenFunction);
        byte finalValue = function.doApplyAsByte(80);

        //then - finals
        assertThat(finalValue).isEqualTo((byte)100);
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    @Test
    public void testBoolToIntToShortFunc2() throws X  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);

        //given (+ some assertions)
        LIntPredicateX<X> sutO = a -> {
                mainFunctionCalled.set(true);
                assertThat(a).isEqualTo(80);
                return true;
        };

        LBoolToShortFunctionX<X> thenFunction = p -> {
                thenFunctionCalled.set(true);
                // boolean
                assertThat(p).isEqualTo(true);
                // short
                return (short)100;
        };

        //when
        LIntToShortFunctionX<X> function = sutO.boolToIntToShortFunc(thenFunction);
        short finalValue = function.doApplyAsShort(80);

        //then - finals
        assertThat(finalValue).isEqualTo((short)100);
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    @Test
    public void testBoolToIntUnaryOp3() throws X  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);

        //given (+ some assertions)
        LIntPredicateX<X> sutO = a -> {
                mainFunctionCalled.set(true);
                assertThat(a).isEqualTo(80);
                return true;
        };

        LBoolToIntFunctionX<X> thenFunction = p -> {
                thenFunctionCalled.set(true);
                // boolean
                assertThat(p).isEqualTo(true);
                // int
                return 100;
        };

        //when
        LIntUnaryOperatorX<X> function = sutO.boolToIntUnaryOp(thenFunction);
        int finalValue = function.doApplyAsInt(80);

        //then - finals
        assertThat(finalValue).isEqualTo(100);
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    @Test
    public void testBoolToIntToLongFunc4() throws X  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);

        //given (+ some assertions)
        LIntPredicateX<X> sutO = a -> {
                mainFunctionCalled.set(true);
                assertThat(a).isEqualTo(80);
                return true;
        };

        LBoolToLongFunctionX<X> thenFunction = p -> {
                thenFunctionCalled.set(true);
                // boolean
                assertThat(p).isEqualTo(true);
                // long
                return 100L;
        };

        //when
        LIntToLongFunctionX<X> function = sutO.boolToIntToLongFunc(thenFunction);
        long finalValue = function.doApplyAsLong(80);

        //then - finals
        assertThat(finalValue).isEqualTo(100L);
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    @Test
    public void testBoolToIntToFloatFunc5() throws X  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);

        //given (+ some assertions)
        LIntPredicateX<X> sutO = a -> {
                mainFunctionCalled.set(true);
                assertThat(a).isEqualTo(80);
                return true;
        };

        LBoolToFloatFunctionX<X> thenFunction = p -> {
                thenFunctionCalled.set(true);
                // boolean
                assertThat(p).isEqualTo(true);
                // float
                return 100f;
        };

        //when
        LIntToFloatFunctionX<X> function = sutO.boolToIntToFloatFunc(thenFunction);
        float finalValue = function.doApplyAsFloat(80);

        //then - finals
        assertThat(finalValue).isEqualTo(100f);
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    @Test
    public void testBoolToIntToDoubleFunc6() throws X  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);

        //given (+ some assertions)
        LIntPredicateX<X> sutO = a -> {
                mainFunctionCalled.set(true);
                assertThat(a).isEqualTo(80);
                return true;
        };

        LBoolToDoubleFunctionX<X> thenFunction = p -> {
                thenFunctionCalled.set(true);
                // boolean
                assertThat(p).isEqualTo(true);
                // double
                return 100d;
        };

        //when
        LIntToDoubleFunctionX<X> function = sutO.boolToIntToDoubleFunc(thenFunction);
        double finalValue = function.doApplyAsDouble(80);

        //then - finals
        assertThat(finalValue).isEqualTo(100d);
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    @Test
    public void testBoolToIntToCharFunc7() throws X  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);

        //given (+ some assertions)
        LIntPredicateX<X> sutO = a -> {
                mainFunctionCalled.set(true);
                assertThat(a).isEqualTo(80);
                return true;
        };

        LBoolToCharFunctionX<X> thenFunction = p -> {
                thenFunctionCalled.set(true);
                // boolean
                assertThat(p).isEqualTo(true);
                // char
                return '\u0100';
        };

        //when
        LIntToCharFunctionX<X> function = sutO.boolToIntToCharFunc(thenFunction);
        char finalValue = function.doApplyAsChar(80);

        //then - finals
        assertThat(finalValue).isEqualTo('\u0100');
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    @Test
    public void testBoolToIntPred8() throws X  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);

        //given (+ some assertions)
        LIntPredicateX<X> sutO = a -> {
                mainFunctionCalled.set(true);
                assertThat(a).isEqualTo(80);
                return true;
        };

        LLogicalOperatorX<X> thenFunction = p -> {
                thenFunctionCalled.set(true);
                // boolean
                assertThat(p).isEqualTo(true);
                // boolean
                return true;
        };

        //when
        LIntPredicateX<X> function = sutO.boolToIntPred(thenFunction);
        boolean finalValue = function.doTest(80);

        //then - finals
        assertThat(finalValue).isEqualTo(true);
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    // </editor-fold>

    @Test
    public void testNesting() {
        assertThat(sut.nestingIntPred())
            .isInstanceOf(LIntPredicate.class);
    }

    @Test
    public void testShoving() {
        assertThat(sut.shovingIntPred())
            .isInstanceOf(LIntPredicate.class);
    }

    @Test
    public void testNestingX() {
        assertThat(sut.nestingIntPredX())
            .isInstanceOf(LIntPredicateX.class);
    }

    @Test
    public void testShovingX() {
        assertThat(sut.shovingIntPredX())
            .isInstanceOf(LIntPredicateX.class);
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void testShove() {

        // given
        LIntPredicateX<X> sutThrowing = LIntPredicateX.lX(a -> {
            throw new UnsupportedOperationException();
        });

        // when
        sutThrowing.shovingIntPred().doTest(100);
    }

    @Test
    public void testHandleIntPred() throws X {

        // given
        LIntPredicateX<X> sutThrowing = LIntPredicateX.lX(a -> {
            throw new UnsupportedOperationException();
        });

        // when
        LIntPredicateX<X> wrapped = sutThrowing.handleIntPredX(h -> {
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
                .contains("LIntPredicateX: boolean doTest(int a) throws X");
    }


    @Test
    public void isThrowing() {
        assertThat(sut.isThrowing())
            .isTrue();
    }

    @Test void safeCompiles() {
        LIntPredicateX r1 = LIntPredicateX.safe(sut); //NOSONAR
        IntPredicate r3 = LIntPredicateX.safe(sut); //NOSONAR
    }

    @Test void safePropagates() {
        Object result = LIntPredicateX.safe(sut);
        assertThat(result).isSameAs(sut);
    }

    @Test void safeProtectsAgainstNpe() {
        Object result = LIntPredicateX.safe(null);
        assertThat(result).isSameAs(LIntPredicateX.lX(LIntPredicateX.safe()));
    }

    @Test <Y extends Throwable> void safeSupplierPropagates() {
        LSupplierX<LIntPredicateX<X>,Y> supplier = ()->sut;
        Object result = LIntPredicateX.safeSupplier(supplier);
        assertThat(result).isSameAs(supplier);
    }

    @Test <Y extends Throwable> void safeSupplierProtectsAgainstNpe() {
        Object result = LIntPredicateX.safeSupplier(null);
        assertThat(result).isSameAs(LIntPredicateX.safeSupplier());
    }

    @Test <Y extends Throwable> void safeSupplierCompiles() {
        LSupplierX<LIntPredicateX<X>,Y> r1 = LIntPredicateX.safeSupplier(()->sut);  //NOSONAR
        Supplier<LIntPredicateX<X>> r2 = LIntPredicateX.safeSupplier(()->sut); //NOSONAR
    }

}
