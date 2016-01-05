/*
 * This file is part of "lunisolar-magma".
 *
 * (C) Copyright 2015 Lunisolar (http://lunisolar.eu/).
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
import eu.lunisolar.magma.func.operator.unary.*; // NOSONAR
import eu.lunisolar.magma.func.operator.binary.*; // NOSONAR
import eu.lunisolar.magma.func.operator.ternary.*; // NOSONAR
import eu.lunisolar.magma.func.function.*; // NOSONAR
import eu.lunisolar.magma.func.function.from.*; // NOSONAR
import eu.lunisolar.magma.func.function.to.*; // NOSONAR
import eu.lunisolar.magma.func.function.conversion.*; // NOSONAR
import eu.lunisolar.magma.func.predicate.*; // NOSONAR
import eu.lunisolar.magma.func.supplier.*; // NOSONAR
import eu.lunisolar.magma.func.consumer.primitives.obj.*; // NOSONAR
import eu.lunisolar.magma.func.consumer.primitives.bi.*; // NOSONAR
import eu.lunisolar.magma.func.consumer.primitives.tri.*; // NOSONAR
import eu.lunisolar.magma.func.consumer.primitives.*; // NOSONAR
import eu.lunisolar.magma.func.consumer.*; // NOSONAR
import eu.lunisolar.magma.func.action.*; // NOSONAR

import java.util.function.*; // NOSONAR
import org.assertj.core.api.Assertions;  //NOSONAR
import org.testng.annotations.*;      //NOSONAR
import java.util.regex.Pattern;          //NOSONAR
import java.text.ParseException;         //NOSONAR
import eu.lunisolar.magma.basics.*; //NOSONAR
import eu.lunisolar.magma.basics.exceptions.*; //NOSONAR
import java.util.concurrent.atomic.AtomicInteger; //NOSONAR
import eu.lunisolar.magma.struct.tuple.*; // NOSONAR
import static org.assertj.core.api.Assertions.*; //NOSONAR

/** The test obviously concentrate on the interface methods the function it self is very simple.  */
public class LBiCharPredicateXTest<X extends ParseException> {
    private static final String ORIGINAL_MESSAGE = "Original message";
    private static final String EXCEPTION_WAS_WRAPPED = "Exception was wrapped.";
    private static final String NO_EXCEPTION_WERE_THROWN = "No exception were thrown.";

    private boolean testValue = true;



    private LBiCharPredicateX<X> sut = new LBiCharPredicateX(){
        public  boolean doTest(char a1,char a2) throws ParseException {
            return testValue;
        }
    };

    private LBiCharPredicate opposite = new LBiCharPredicate(){
        public  boolean doTest(char a1,char a2)  {
            return testValue;
        }
    };



    private LBiCharPredicateX<ParseException> sutAlwaysThrowing = LBiCharPredicateX.lX((char a1,char a2) -> {
            throw new ParseException(ORIGINAL_MESSAGE, 0);
    });

    private LBiCharPredicateX<RuntimeException> sutAlwaysThrowingUnckeck = LBiCharPredicateX.lX((char a1,char a2) -> {
            throw new IndexOutOfBoundsException(ORIGINAL_MESSAGE);
    });


    @Test
    public void testTheResult() throws X {
        assertThat(sut.doTest((char)100,(char)100))
            .isEqualTo(testValue);
    }

    @Test
    public void testTupleCall() throws X {

        //FunctionalCall<LCharPair,Boolean,X> theCall = sut;

        LCharPair domainObject = Tuple4U.tuple((char)100,(char)100);

        Object result = sut.tupleTest(domainObject);

        assertThat(result)
            .isEqualTo(testValue);
    }

    @Test
    public void testNonNullDoTest() throws X {
        assertThat(sut.nonNullDoTest((char)100,(char)100))
            .isEqualTo(testValue);
    }

    @Test
    public void testNestingDoTestChecked() throws X {

        // then
        try {
            sutAlwaysThrowing.nestingDoTest((char)100,(char)100);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(NestedException.class)
                    .hasCauseExactlyInstanceOf(ParseException.class)
                    .hasMessage(ORIGINAL_MESSAGE);
        }
    }

    @Test
    public void testNestingDoTestUnckeck() throws X {

        // then
        try {
            sutAlwaysThrowingUnckeck.nestingDoTest((char)100,(char)100);
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
            sutAlwaysThrowing.shovingDoTest((char)100,(char)100);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(ParseException.class)
                    .hasNoCause()
                    .hasMessage(ORIGINAL_MESSAGE);
        }
    }

    @Test
    public void testShovingDoTestUnckeck() throws X {

        // then
        try {
            sutAlwaysThrowingUnckeck.shovingDoTest((char)100,(char)100);
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
        assertThat(sut.doApplyAsBoolean((char)100,(char)100))
            .isEqualTo(testValue);

    }


    @Test
    public void testFunctionalInterfaceDescription() throws X {
        assertThat(sut.functionalInterfaceDescription())
            .isEqualTo("LBiCharPredicateX: boolean doTest(char a1,char a2) throws X");
    }

    @Test
    public void testLXMethod() throws X {
        assertThat(LBiCharPredicateX.lX((char a1,char a2) -> testValue ))
            .isInstanceOf(LBiCharPredicateX.class);
    }

    @Test
    public void testWrapXMethod() throws X {
        assertThat(LBiCharPredicateX.wrapX(opposite))
            .isInstanceOf(LBiCharPredicateX.class);
    }


    @Test
    public void testWrapExceptionMethodWrapsTheException() throws X {

        // given
        LBiCharPredicateX<X> sutThrowing = LBiCharPredicateX.lX((char a1,char a2) -> {
            throw new UnsupportedOperationException();
        });

        // when
        LBiCharPredicateX<X> wrapped = sutThrowing.handleBiCharPredX(handler -> handler
            .wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED));

        // then
        try {
            wrapped.doTest((char)100,(char)100);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IllegalArgumentException.class)
                    .hasCauseExactlyInstanceOf(UnsupportedOperationException.class)
                    .hasMessage(EXCEPTION_WAS_WRAPPED);
        }
    }

    @Test
    public void testWrapExceptionMethodDoNotWrapsOtherExceptionIf() throws X {

        // given
        LBiCharPredicateX<X> sutThrowing = LBiCharPredicateX.lX((char a1,char a2) -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LBiCharPredicateX<X> wrapped = sutThrowing.handleBiCharPredX(handler -> handler
                .wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED)
                .throwIf(IndexOutOfBoundsException.class));

        // then
        try {
            wrapped.doTest((char)100,(char)100);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                    .hasNoCause();
        }
    }

@Test
    public void testWrapExceptionMethodDoNotWrapsOtherExceptionWhen() throws X {

        // given
        LBiCharPredicateX<X> sutThrowing = LBiCharPredicateX.lX((char a1,char a2) -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LBiCharPredicateX<X> wrapped = sutThrowing.handleBiCharPredX(handler -> handler
                .wrapWhen(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED)
                .throwIf(IndexOutOfBoundsException.class));

        // then
        try {
            wrapped.doTest((char)100,(char)100);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                    .hasNoCause();
        }
    }


    @Test
    public void testWrapExceptionMishandlingExceptionIsAllowed() throws X {

        // given
        LBiCharPredicateX<X> sutThrowing = LBiCharPredicateX.lX((char a1,char a2) -> {
            throw (X) new ParseException(ORIGINAL_MESSAGE, 0);
        });

        // when
        LBiCharPredicateX<X> wrapped = sutThrowing.handleBiCharPredX(h -> Function4U.doNothing());

        // then
        try {
            wrapped.doTest((char)100,(char)100);
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
        assertThat(sut.negate().doTest((char)100,(char)100))
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
        LBiCharPredicateX<X> fun1 = LBiCharPredicateX.lX((char a1,char a2) -> f1Result);
        LBiCharPredicateX<X> fun2 = LBiCharPredicateX.lX((char a1,char a2) -> f2Result);

        //when
        LBiCharPredicateX<X> andFunction = fun1.and(fun2);
        LBiCharPredicateX<X> orFunction = fun1.or(fun2);
        LBiCharPredicateX<X> xorFunction = fun1.xor(fun2);

        //then
        assertThat(andFunction.doTest((char)100,(char)100))
                .isEqualTo(andResult);

        assertThat(orFunction.doTest((char)100,(char)100))
                .isEqualTo(orResult);

        assertThat(xorFunction.doTest((char)100,(char)100))
                .isEqualTo(xorResult);
    }

    @Test
    public void isEqual() throws X  {
        //when
        LBiCharPredicateX<X> equals = LBiCharPredicateX.isEqual((char)100,(char)100);

        //then
        assertThat(equals.doTest((char)100,(char)100))
                .isTrue();

        assertThat(equals.doTest((char)0,(char)0))
                .isFalse();
    }



    // <editor-fold desc="compose (functional)">

    @Test
    public void testbiCharPredComposeChar() throws X {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LBiCharPredicateX<X> sutO = (char a1,char a2) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo((char)90);
                assertThat(a2).isEqualTo((char)91);
                return true;
        };

        LCharUnaryOperatorX<X> before1 = p0 -> {
            assertThat(p0).isEqualTo((char)80);
            beforeCalls.incrementAndGet();
            return (char)90;
        };
        LCharUnaryOperatorX<X> before2 = p1 -> {
            assertThat(p1).isEqualTo((char)81);
            beforeCalls.incrementAndGet();
            return (char)91;
        };

        //when
        LBiCharPredicateX<X> function = sutO.biCharPredComposeChar(before1,before2);
        function.doTest((char)80,(char)81);

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(beforeCalls.get()).isEqualTo(2);
    }


    @Test
    public void testbiCharPredCompose() throws X {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LBiCharPredicateX<X> sutO = (char a1,char a2) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo((char)90);
                assertThat(a2).isEqualTo((char)91);
                return true;
        };

        LToCharFunctionX<Integer ,X> before1 = p0 -> {
            assertThat(p0).isEqualTo(Integer.valueOf(80));
            beforeCalls.incrementAndGet();
            return (char)90;
        };
        LToCharFunctionX<Integer ,X> before2 = p1 -> {
            assertThat(p1).isEqualTo(Integer.valueOf(81));
            beforeCalls.incrementAndGet();
            return (char)91;
        };

        //when
        LBiPredicateX<Integer ,Integer ,X> function = sutO.biCharPredCompose(before1,before2);
        function.doTest((Integer )Integer.valueOf(80),(Integer )Integer.valueOf(81));

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(beforeCalls.get()).isEqualTo(2);
    }

    // </editor-fold>


    // <editor-fold desc="then (functional)">

    @Test
    public void testThen0() throws X  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);


        //given (+ some assertions)
        LBiCharPredicateX<X> sutO = (char a1,char a2) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo((char)80);
                assertThat(a2).isEqualTo((char)81);
                return true;
        };

        LBoolFunctionX<Integer ,X> thenFunction = p -> {
                thenFunctionCalled.set(true);
                // 
                assertThat(p).isEqualTo(true);
                // V
                return Integer.valueOf(100);
        };

        //when
        LBiCharFunctionX<Integer ,X> function = sutO.boolToBiCharFunction(thenFunction);
        Integer  finalValue = function.doApply((char)80,(char)81);

        //then - finals
        assertThat(finalValue).isEqualTo(Integer.valueOf(100));
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    // </editor-fold>

    @Test
    public void testNesting() {
        assertThat(sut.nestingBiCharPred())
            .isInstanceOf(LBiCharPredicate.class);
    }

    @Test
    public void testShoving() {
        assertThat(sut.shovingBiCharPred())
            .isInstanceOf(LBiCharPredicate.class);
    }

    @Test
    public void testNestingX() {
        assertThat(sut.nestingBiCharPredX())
            .isInstanceOf(LBiCharPredicateX.class);
    }

    @Test
    public void testShovingX() {
        assertThat(sut.shovingBiCharPredX())
            .isInstanceOf(LBiCharPredicateX.class);
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void testShove() {

        // given
        LBiCharPredicateX<X> sutThrowing = LBiCharPredicateX.lX((char a1,char a2) -> {
            throw new UnsupportedOperationException();
        });

        // when
        sutThrowing.shovingBiCharPred().doTest((char)100,(char)100);
    }

    @Test
    public void testHandleBiCharPred() throws X {

        // given
        LBiCharPredicateX<X> sutThrowing = LBiCharPredicateX.lX((char a1,char a2) -> {
            throw new UnsupportedOperationException();
        });

        // when
        LBiCharPredicateX<X> wrapped = sutThrowing.handleBiCharPredX(h -> {
            h.wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED);
        });

        // then
        try {
            wrapped.doTest((char)100,(char)100);
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
                .contains("LBiCharPredicateX: boolean doTest(char a1,char a2) throws X");
    }


    @Test
    public void isThrowing() {
        assertThat(sut.isThrowing())
            .isTrue();
    }

    //<editor-fold desc="Variants">

    private boolean variant1(char a2,char a1) {
        return true;
    }

    @Test
    public void compilerSubstituteVariant1() {
        LBiCharPredicateX lambda = LBiCharPredicateX./*<X>*/lX1(this::variant1);

        assertThat(lambda).isInstanceOf(LBiCharPredicateX.V1.class);
    }

    //</editor-fold>


    @Test void safeCompiles() {
        LBiCharPredicateX r1 = LBiCharPredicateX.safe(sut);
    }

    @Test void safePropagates() {
        Object result = LBiCharPredicateX.safe(sut);
        assertThat(result).isSameAs(sut);
    }

    @Test void safeProtectsAgainstNpe() {
        Object result = LBiCharPredicateX.safe(null);
        assertThat(result).isSameAs(LBiCharPredicateX.lX(LBiCharPredicateX.safe()));
    }

    @Test <Y extends Throwable> void safeSupplierPropagates() {
        LSupplierX<LBiCharPredicateX<X>,Y> supplier = ()->sut;
        Object result = LBiCharPredicateX.safeSupplier(supplier);
        assertThat(result).isSameAs(supplier);
    }

    @Test <Y extends Throwable> void safeSupplierProtectsAgainstNpe() {
        Object result = LBiCharPredicateX.safeSupplier(null);
        assertThat(result).isSameAs(LBiCharPredicateX.safeSupplier());
    }

    @Test <Y extends Throwable> void safeSupplierCompiles() {
        LSupplierX<LBiCharPredicateX<X>,Y> r1 = LBiCharPredicateX.safeSupplier(()->sut);
    }

}
