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
public class LBiIntPredicateXTest<X extends ParseException> {
    private static final String ORIGINAL_MESSAGE = "Original message";
    private static final String EXCEPTION_WAS_WRAPPED = "Exception was wrapped.";
    private static final String NO_EXCEPTION_WERE_THROWN = "No exception were thrown.";

    private boolean testValue = true;



    private LBiIntPredicateX<X> sut = new LBiIntPredicateX(){
        public  boolean doTest(int a1,int a2) throws ParseException {
            return testValue;
        }
    };

    private LBiIntPredicate opposite = new LBiIntPredicate(){
        public  boolean doTest(int a1,int a2)  {
            return testValue;
        }
    };



    private LBiIntPredicateX<ParseException> sutAlwaysThrowing = LBiIntPredicateX.lX((int a1,int a2) -> {
            throw new ParseException(ORIGINAL_MESSAGE, 0);
    });

    private LBiIntPredicateX<RuntimeException> sutAlwaysThrowingUnckeck = LBiIntPredicateX.lX((int a1,int a2) -> {
            throw new IndexOutOfBoundsException(ORIGINAL_MESSAGE);
    });


    @Test
    public void testTheResult() throws X {
        assertThat(sut.doTest((int)100,(int)100))
            .isEqualTo(testValue);
    }

    @Test
    public void testTupleCall() throws X {

        LIntPair domainObject = Tuple4U.tuple((int)100,(int)100);

        Object result = sut.tupleTest(domainObject);

        assertThat(result)
            .isEqualTo(testValue);
    }

    @Test
    public void testNonNullDoTest() throws X {
        assertThat(sut.nonNullDoTest((int)100,(int)100))
            .isEqualTo(testValue);
    }

    @Test
    public void testNestingDoTestChecked() throws X {

        // then
        try {
            sutAlwaysThrowing.nestingDoTest((int)100,(int)100);
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
            sutAlwaysThrowingUnckeck.nestingDoTest((int)100,(int)100);
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
            sutAlwaysThrowing.shovingDoTest((int)100,(int)100);
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
            sutAlwaysThrowingUnckeck.shovingDoTest((int)100,(int)100);
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
        assertThat(sut.doApplyAsBoolean((int)100,(int)100))
            .isEqualTo(testValue);

    }


    @Test
    public void testFunctionalInterfaceDescription() throws X {
        assertThat(sut.functionalInterfaceDescription())
            .isEqualTo("LBiIntPredicateX: boolean doTest(int a1,int a2) throws X");
    }

    @Test
    public void testLXMethod() throws X {
        assertThat(LBiIntPredicateX.lX((int a1,int a2) -> testValue ))
            .isInstanceOf(LBiIntPredicateX.class);
    }

    @Test
    public void testWrapXMethod() throws X {
        assertThat(LBiIntPredicateX.wrapX(opposite))
            .isInstanceOf(LBiIntPredicateX.class);
    }


    @Test
    public void testWrapExceptionMethodWrapsTheException() throws X {

        // given
        LBiIntPredicateX<X> sutThrowing = LBiIntPredicateX.lX((int a1,int a2) -> {
            throw new UnsupportedOperationException();
        });

        // when
        LBiIntPredicateX<X> wrapped = sutThrowing.handleBiIntPredX(handler -> handler
            .wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED));

        // then
        try {
            wrapped.doTest((int)100,(int)100);
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
        LBiIntPredicateX<X> sutThrowing = LBiIntPredicateX.lX((int a1,int a2) -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LBiIntPredicateX<X> wrapped = sutThrowing.handleBiIntPredX(handler -> handler
                .wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED)
                .throwIf(IndexOutOfBoundsException.class));

        // then
        try {
            wrapped.doTest((int)100,(int)100);
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
        LBiIntPredicateX<X> sutThrowing = LBiIntPredicateX.lX((int a1,int a2) -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LBiIntPredicateX<X> wrapped = sutThrowing.handleBiIntPredX(handler -> handler
                .wrapWhen(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED)
                .throwIf(IndexOutOfBoundsException.class));

        // then
        try {
            wrapped.doTest((int)100,(int)100);
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
        LBiIntPredicateX<X> sutThrowing = LBiIntPredicateX.lX((int a1,int a2) -> {
            throw (X) new ParseException(ORIGINAL_MESSAGE, 0);
        });

        // when
        LBiIntPredicateX<X> wrapped = sutThrowing.handleBiIntPredX(h -> Function4U.doNothing());

        // then
        try {
            wrapped.doTest((int)100,(int)100);
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
        assertThat(sut.negate().doTest((int)100,(int)100))
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
        LBiIntPredicateX<X> fun1 = LBiIntPredicateX.lX((int a1,int a2) -> f1Result);
        LBiIntPredicateX<X> fun2 = LBiIntPredicateX.lX((int a1,int a2) -> f2Result);

        //when
        LBiIntPredicateX<X> andFunction = fun1.and(fun2);
        LBiIntPredicateX<X> orFunction = fun1.or(fun2);
        LBiIntPredicateX<X> xorFunction = fun1.xor(fun2);

        //then
        assertThat(andFunction.doTest((int)100,(int)100))
                .isEqualTo(andResult);

        assertThat(orFunction.doTest((int)100,(int)100))
                .isEqualTo(orResult);

        assertThat(xorFunction.doTest((int)100,(int)100))
                .isEqualTo(xorResult);
    }

    @Test
    public void isEqual() throws X  {
        //when
        LBiIntPredicateX<X> equals = LBiIntPredicateX.isEqual((int)100,(int)100);

        //then
        assertThat(equals.doTest((int)100,(int)100))
                .isTrue();

        assertThat(equals.doTest((int)0,(int)0))
                .isFalse();
    }



    // <editor-fold desc="compose (functional)">

    @Test
    public void testbiIntPredComposeInt() throws X {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LBiIntPredicateX<X> sutO = (int a1,int a2) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo((int)90);
                assertThat(a2).isEqualTo((int)91);
                return true;
        };

        LIntUnaryOperatorX<X> before1 = p0 -> {
            assertThat(p0).isEqualTo((int)80);
            beforeCalls.incrementAndGet();
            return (int)90;
        };
        LIntUnaryOperatorX<X> before2 = p1 -> {
            assertThat(p1).isEqualTo((int)81);
            beforeCalls.incrementAndGet();
            return (int)91;
        };

        //when
        LBiIntPredicateX<X> function = sutO.biIntPredComposeInt(before1,before2);
        function.doTest((int)80,(int)81);

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(beforeCalls.get()).isEqualTo(2);
    }


    @Test
    public void testbiIntPredCompose() throws X {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LBiIntPredicateX<X> sutO = (int a1,int a2) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo((int)90);
                assertThat(a2).isEqualTo((int)91);
                return true;
        };

        LToIntFunctionX<Integer ,X> before1 = p0 -> {
            assertThat(p0).isEqualTo(Integer.valueOf(80));
            beforeCalls.incrementAndGet();
            return (int)90;
        };
        LToIntFunctionX<Integer ,X> before2 = p1 -> {
            assertThat(p1).isEqualTo(Integer.valueOf(81));
            beforeCalls.incrementAndGet();
            return (int)91;
        };

        //when
        LBiPredicateX<Integer ,Integer ,X> function = sutO.biIntPredCompose(before1,before2);
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
        LBiIntPredicateX<X> sutO = (int a1,int a2) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo((int)80);
                assertThat(a2).isEqualTo((int)81);
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
        LBiIntFunctionX<Integer ,X> function = sutO.boolToBiIntFunction(thenFunction);
        Integer  finalValue = function.doApply((int)80,(int)81);

        //then - finals
        assertThat(finalValue).isEqualTo(Integer.valueOf(100));
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    // </editor-fold>

    @Test
    public void testNesting() {
        assertThat(sut.nestingBiIntPred())
            .isInstanceOf(LBiIntPredicate.class);
    }

    @Test
    public void testShoving() {
        assertThat(sut.shovingBiIntPred())
            .isInstanceOf(LBiIntPredicate.class);
    }

    @Test
    public void testNestingX() {
        assertThat(sut.nestingBiIntPredX())
            .isInstanceOf(LBiIntPredicateX.class);
    }

    @Test
    public void testShovingX() {
        assertThat(sut.shovingBiIntPredX())
            .isInstanceOf(LBiIntPredicateX.class);
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void testShove() {

        // given
        LBiIntPredicateX<X> sutThrowing = LBiIntPredicateX.lX((int a1,int a2) -> {
            throw new UnsupportedOperationException();
        });

        // when
        sutThrowing.shovingBiIntPred().doTest((int)100,(int)100);
    }

    @Test
    public void testHandleBiIntPred() throws X {

        // given
        LBiIntPredicateX<X> sutThrowing = LBiIntPredicateX.lX((int a1,int a2) -> {
            throw new UnsupportedOperationException();
        });

        // when
        LBiIntPredicateX<X> wrapped = sutThrowing.handleBiIntPredX(h -> {
            h.wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED);
        });

        // then
        try {
            wrapped.doTest((int)100,(int)100);
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
                .contains("LBiIntPredicateX: boolean doTest(int a1,int a2) throws X");
    }


    @Test
    public void isThrowing() {
        assertThat(sut.isThrowing())
            .isTrue();
    }

    //<editor-fold desc="Variants">

    private boolean variant1(int a2,int a1) {
        return true;
    }

    @Test
    public void compilerSubstituteVariant1() {
        LBiIntPredicateX lambda = LBiIntPredicateX./*<X>*/lX1(this::variant1);

        assertThat(lambda).isInstanceOf(LBiIntPredicateX.V1.class);
    }

    //</editor-fold>


    @Test void safeCompiles() {
        LBiIntPredicateX r1 = LBiIntPredicateX.safe(sut); //NOSONAR
    }

    @Test void safePropagates() {
        Object result = LBiIntPredicateX.safe(sut);
        assertThat(result).isSameAs(sut);
    }

    @Test void safeProtectsAgainstNpe() {
        Object result = LBiIntPredicateX.safe(null);
        assertThat(result).isSameAs(LBiIntPredicateX.lX(LBiIntPredicateX.safe()));
    }

    @Test <Y extends Throwable> void safeSupplierPropagates() {
        LSupplierX<LBiIntPredicateX<X>,Y> supplier = ()->sut;
        Object result = LBiIntPredicateX.safeSupplier(supplier);
        assertThat(result).isSameAs(supplier);
    }

    @Test <Y extends Throwable> void safeSupplierProtectsAgainstNpe() {
        Object result = LBiIntPredicateX.safeSupplier(null);
        assertThat(result).isSameAs(LBiIntPredicateX.safeSupplier());
    }

    @Test <Y extends Throwable> void safeSupplierCompiles() {
        LSupplierX<LBiIntPredicateX<X>,Y> r1 = LBiIntPredicateX.safeSupplier(()->sut);  //NOSONAR
    }

}
