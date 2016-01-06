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
public class LObjIntPredicateXTest<T,X extends ParseException> {
    private static final String ORIGINAL_MESSAGE = "Original message";
    private static final String EXCEPTION_WAS_WRAPPED = "Exception was wrapped.";
    private static final String NO_EXCEPTION_WERE_THROWN = "No exception were thrown.";

    private boolean testValue = true;



    private LObjIntPredicateX<T,X> sut = new LObjIntPredicateX(){
        public  boolean doTest(Object a1,int a2) throws ParseException {
            return testValue;
        }
    };

    private LObjIntPredicate<T> opposite = new LObjIntPredicate(){
        public  boolean doTest(Object a1,int a2)  {
            return testValue;
        }
    };



    private LObjIntPredicateX<T,ParseException> sutAlwaysThrowing = LObjIntPredicateX.lX((T a1,int a2) -> {
            throw new ParseException(ORIGINAL_MESSAGE, 0);
    });

    private LObjIntPredicateX<T,RuntimeException> sutAlwaysThrowingUnckeck = LObjIntPredicateX.lX((T a1,int a2) -> {
            throw new IndexOutOfBoundsException(ORIGINAL_MESSAGE);
    });


    @Test
    public void testTheResult() throws X {
        assertThat(sut.doTest((T)Integer.valueOf(100),(int)100))
            .isEqualTo(testValue);
    }

    @Test
    public void testTupleCall() throws X {

        LObjIntPair<T> domainObject = Tuple4U.tuple((T)Integer.valueOf(100),(int)100);

        Object result = sut.tupleTest(domainObject);

        assertThat(result)
            .isEqualTo(testValue);
    }

    @Test
    public void testNonNullDoTest() throws X {
        assertThat(sut.nonNullDoTest((T)Integer.valueOf(100),(int)100))
            .isEqualTo(testValue);
    }

    @Test
    public void testNestingDoTestChecked() throws X {

        // then
        try {
            sutAlwaysThrowing.nestingDoTest((T)Integer.valueOf(100),(int)100);
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
            sutAlwaysThrowingUnckeck.nestingDoTest((T)Integer.valueOf(100),(int)100);
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
            sutAlwaysThrowing.shovingDoTest((T)Integer.valueOf(100),(int)100);
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
            sutAlwaysThrowingUnckeck.shovingDoTest((T)Integer.valueOf(100),(int)100);
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
        assertThat(sut.doApplyAsBoolean((T)Integer.valueOf(100),(int)100))
            .isEqualTo(testValue);

    }


    @Test
    public void testFunctionalInterfaceDescription() throws X {
        assertThat(sut.functionalInterfaceDescription())
            .isEqualTo("LObjIntPredicateX: boolean doTest(T a1,int a2) throws X");
    }

    @Test
    public void testLXMethod() throws X {
        assertThat(LObjIntPredicateX.lX((Object a1,int a2) -> testValue ))
            .isInstanceOf(LObjIntPredicateX.class);
    }

    @Test
    public void testWrapXMethod() throws X {
        assertThat(LObjIntPredicateX.wrapX(opposite))
            .isInstanceOf(LObjIntPredicateX.class);
    }


    @Test
    public void testWrapExceptionMethodWrapsTheException() throws X {

        // given
        LObjIntPredicateX<T,X> sutThrowing = LObjIntPredicateX.lX((T a1,int a2) -> {
            throw new UnsupportedOperationException();
        });

        // when
        LObjIntPredicateX<T,X> wrapped = sutThrowing.handleObjIntPredX(handler -> handler
            .wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED));

        // then
        try {
            wrapped.doTest((T)Integer.valueOf(100),(int)100);
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
        LObjIntPredicateX<T,X> sutThrowing = LObjIntPredicateX.lX((T a1,int a2) -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LObjIntPredicateX<T,X> wrapped = sutThrowing.handleObjIntPredX(handler -> handler
                .wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED)
                .throwIf(IndexOutOfBoundsException.class));

        // then
        try {
            wrapped.doTest((T)Integer.valueOf(100),(int)100);
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
        LObjIntPredicateX<T,X> sutThrowing = LObjIntPredicateX.lX((T a1,int a2) -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LObjIntPredicateX<T,X> wrapped = sutThrowing.handleObjIntPredX(handler -> handler
                .wrapWhen(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED)
                .throwIf(IndexOutOfBoundsException.class));

        // then
        try {
            wrapped.doTest((T)Integer.valueOf(100),(int)100);
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
        LObjIntPredicateX<T,X> sutThrowing = LObjIntPredicateX.lX((T a1,int a2) -> {
            throw (X) new ParseException(ORIGINAL_MESSAGE, 0);
        });

        // when
        LObjIntPredicateX<T,X> wrapped = sutThrowing.handleObjIntPredX(h -> Function4U.doNothing());

        // then
        try {
            wrapped.doTest((T)Integer.valueOf(100),(int)100);
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
        assertThat(sut.negate().doTest((T)Integer.valueOf(100),(int)100))
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
        LObjIntPredicateX<T,X> fun1 = LObjIntPredicateX.lX((T a1,int a2) -> f1Result);
        LObjIntPredicateX<T,X> fun2 = LObjIntPredicateX.lX((T a1,int a2) -> f2Result);

        //when
        LObjIntPredicateX<T,X> andFunction = fun1.and(fun2);
        LObjIntPredicateX<T,X> orFunction = fun1.or(fun2);
        LObjIntPredicateX<T,X> xorFunction = fun1.xor(fun2);

        //then
        assertThat(andFunction.doTest((T)Integer.valueOf(100),(int)100))
                .isEqualTo(andResult);

        assertThat(orFunction.doTest((T)Integer.valueOf(100),(int)100))
                .isEqualTo(orResult);

        assertThat(xorFunction.doTest((T)Integer.valueOf(100),(int)100))
                .isEqualTo(xorResult);
    }

    @Test
    public void isEqual() throws X  {
        //when
        LObjIntPredicateX<T,X> equals = LObjIntPredicateX.isEqual((T)Integer.valueOf(100),(int)100);

        //then
        assertThat(equals.doTest((T)Integer.valueOf(100),(int)100))
                .isTrue();

        assertThat(equals.doTest((T)Integer.valueOf(0),(int)0))
                .isFalse();
    }



    // <editor-fold desc="compose (functional)">

    @Test
    public void testobjIntPredComposeInt() throws X {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LObjIntPredicateX<Integer ,X> sutO = (Integer a1,int a2) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo((T)Integer.valueOf(90));
                assertThat(a2).isEqualTo((int)91);
                return true;
        };

        LFunctionX<Integer ,Integer ,X> before1 = p0 -> {
            assertThat(p0).isEqualTo((T)Integer.valueOf(80));
            beforeCalls.incrementAndGet();
            return 90;
        };
        LIntUnaryOperatorX<X> before2 = p1 -> {
            assertThat(p1).isEqualTo((int)81);
            beforeCalls.incrementAndGet();
            return (int)91;
        };

        //when
        LObjIntPredicateX<Integer ,X> function = sutO.objIntPredComposeInt(before1,before2);
        function.doTest((Integer )Integer.valueOf(80),(int)81);

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(beforeCalls.get()).isEqualTo(2);
    }


    @Test
    public void testobjIntPredCompose() throws X {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LObjIntPredicateX<Integer ,X> sutO = (Integer a1,int a2) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo((T)Integer.valueOf(90));
                assertThat(a2).isEqualTo((int)91);
                return true;
        };

        LFunctionX<Integer ,Integer ,X> before1 = p0 -> {
            assertThat(p0).isEqualTo(Integer.valueOf(80));
            beforeCalls.incrementAndGet();
            return 90;
        };
        LToIntFunctionX<Integer ,X> before2 = p1 -> {
            assertThat(p1).isEqualTo(Integer.valueOf(81));
            beforeCalls.incrementAndGet();
            return (int)91;
        };

        //when
        LBiPredicateX<Integer ,Integer ,X> function = sutO.objIntPredCompose(before1,before2);
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
        LObjIntPredicateX<Integer ,X> sutO = (Integer a1,int a2) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo((T)Integer.valueOf(80));
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
        LObjIntFunctionX<Integer ,Integer ,X> function = sutO.boolToObjIntFunction(thenFunction);
        Integer  finalValue = function.doApply((Integer )Integer.valueOf(80),(int)81);

        //then - finals
        assertThat(finalValue).isEqualTo(Integer.valueOf(100));
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    // </editor-fold>

    @Test
    public void testNesting() {
        assertThat(sut.nestingObjIntPred())
            .isInstanceOf(LObjIntPredicate.class);
    }

    @Test
    public void testShoving() {
        assertThat(sut.shovingObjIntPred())
            .isInstanceOf(LObjIntPredicate.class);
    }

    @Test
    public void testNestingX() {
        assertThat(sut.nestingObjIntPredX())
            .isInstanceOf(LObjIntPredicateX.class);
    }

    @Test
    public void testShovingX() {
        assertThat(sut.shovingObjIntPredX())
            .isInstanceOf(LObjIntPredicateX.class);
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void testShove() {

        // given
        LObjIntPredicateX<T,X> sutThrowing = LObjIntPredicateX.lX((T a1,int a2) -> {
            throw new UnsupportedOperationException();
        });

        // when
        sutThrowing.shovingObjIntPred().doTest((T)Integer.valueOf(100),(int)100);
    }

    @Test
    public void testHandleObjIntPred() throws X {

        // given
        LObjIntPredicateX<T,X> sutThrowing = LObjIntPredicateX.lX((T a1,int a2) -> {
            throw new UnsupportedOperationException();
        });

        // when
        LObjIntPredicateX<T,X> wrapped = sutThrowing.handleObjIntPredX(h -> {
            h.wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED);
        });

        // then
        try {
            wrapped.doTest((T)Integer.valueOf(100),(int)100);
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
                .contains("LObjIntPredicateX: boolean doTest(T a1,int a2) throws X");
    }


    @Test
    public void isThrowing() {
        assertThat(sut.isThrowing())
            .isTrue();
    }

    //<editor-fold desc="Variants">

    private boolean variant1(int a2,T a1) {
        return true;
    }

    @Test
    public void compilerSubstituteVariant1() {
        LObjIntPredicateX lambda = LObjIntPredicateX./*<T,X>*/lX1(this::variant1);

        assertThat(lambda).isInstanceOf(LObjIntPredicateX.V1.class);
    }

    //</editor-fold>


    @Test void safeCompiles() {
        LObjIntPredicateX r1 = LObjIntPredicateX.safe(sut); //NOSONAR
    }

    @Test void safePropagates() {
        Object result = LObjIntPredicateX.safe(sut);
        assertThat(result).isSameAs(sut);
    }

    @Test void safeProtectsAgainstNpe() {
        Object result = LObjIntPredicateX.safe(null);
        assertThat(result).isSameAs(LObjIntPredicateX.lX(LObjIntPredicateX.safe()));
    }

    @Test <Y extends Throwable> void safeSupplierPropagates() {
        LSupplierX<LObjIntPredicateX<T,X>,Y> supplier = ()->sut;
        Object result = LObjIntPredicateX.safeSupplier(supplier);
        assertThat(result).isSameAs(supplier);
    }

    @Test <Y extends Throwable> void safeSupplierProtectsAgainstNpe() {
        Object result = LObjIntPredicateX.safeSupplier(null);
        assertThat(result).isSameAs(LObjIntPredicateX.safeSupplier());
    }

    @Test <Y extends Throwable> void safeSupplierCompiles() {
        LSupplierX<LObjIntPredicateX<T,X>,Y> r1 = LObjIntPredicateX.safeSupplier(()->sut);  //NOSONAR
    }

}
