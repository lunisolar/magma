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
public class LBiObjCharPredicateTest<T1,T2,X extends ParseException> {
    private static final String ORIGINAL_MESSAGE = "Original message";
    private static final String EXCEPTION_WAS_WRAPPED = "Exception was wrapped.";
    private static final String NO_EXCEPTION_WERE_THROWN = "No exception were thrown.";

    private boolean testValue = true;



    private LBiObjCharPredicate<T1,T2> sut = new LBiObjCharPredicate(){
        public  boolean doTest(Object a1,Object a2,char a3)  {
            return testValue;
        }
    };

    private LBiObjCharPredicateX<T1,T2,X> opposite = new LBiObjCharPredicateX(){
        public  boolean doTest(Object a1,Object a2,char a3) throws ParseException {
            return testValue;
        }
    };




    private LBiObjCharPredicate<T1,T2> sutAlwaysThrowingUnckeck = LBiObjCharPredicate.l((T1 a1,T2 a2,char a3) -> {
            throw new IndexOutOfBoundsException(ORIGINAL_MESSAGE);
    });


    @Test
    public void testTheResult() throws X {
        assertThat(sut.doTest((T1)Integer.valueOf(100),(T2)Integer.valueOf(100),(char)100))
            .isEqualTo(testValue);
    }

    @Test
    public void testTupleCall() throws X {

        LBiObjCharTriple<T1,T2> domainObject = Tuple4U.tuple((T1)Integer.valueOf(100),(T2)Integer.valueOf(100),(char)100);

        Object result = sut.tupleTest(domainObject);

        assertThat(result)
            .isEqualTo(testValue);
    }

    @Test
    public void testNonNullDoTest() throws X {
        assertThat(sut.nonNullDoTest((T1)Integer.valueOf(100),(T2)Integer.valueOf(100),(char)100))
            .isEqualTo(testValue);
    }

    @Test
    public void testNestingDoTestUnckeck() throws X {

        // then
        try {
            sutAlwaysThrowingUnckeck.nestingDoTest((T1)Integer.valueOf(100),(T2)Integer.valueOf(100),(char)100);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                    .hasNoCause()
                    .hasMessage(ORIGINAL_MESSAGE);
        }
    }

    @Test
    public void testShovingDoTestUnckeck() throws X {

        // then
        try {
            sutAlwaysThrowingUnckeck.shovingDoTest((T1)Integer.valueOf(100),(T2)Integer.valueOf(100),(char)100);
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
        assertThat(sut.doApplyAsBoolean((T1)Integer.valueOf(100),(T2)Integer.valueOf(100),(char)100))
            .isEqualTo(testValue);

    }


    @Test
    public void testFunctionalInterfaceDescription() throws X {
        assertThat(sut.functionalInterfaceDescription())
            .isEqualTo("LBiObjCharPredicate: boolean doTest(T1 a1,T2 a2,char a3)");
    }

    @Test
    public void testLMethod() throws X {
        assertThat(LBiObjCharPredicate.l((Object a1,Object a2,char a3) -> testValue ))
            .isInstanceOf(LBiObjCharPredicate.class);
    }

    @Test
    public void testWrapMethod() throws X {
        assertThat(LBiObjCharPredicate.wrap(opposite))
            .isInstanceOf(LBiObjCharPredicate.class);
    }

    @Test
    public void testWrapMethodDoNotWrapsRuntimeException() throws X {
        // given
        LBiObjCharPredicateX<T1,T2,X> sutThrowing = LBiObjCharPredicateX.lX((T1 a1,T2 a2,char a3) -> {
            throw new UnsupportedOperationException(ORIGINAL_MESSAGE);
        });

        // when
        LBiObjCharPredicate<T1,T2> wrapped = LBiObjCharPredicate.wrap(sutThrowing);

        // then
        try {
            wrapped.doTest((T1)Integer.valueOf(100),(T2)Integer.valueOf(100),(char)100);
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
        LBiObjCharPredicateX<T1,T2,ParseException> sutThrowing = LBiObjCharPredicateX.lX((T1 a1,T2 a2,char a3) -> {
            throw new ParseException(ORIGINAL_MESSAGE, 0);
        });

        // when
        LBiObjCharPredicate<T1,T2> wrapped = LBiObjCharPredicate.wrap(sutThrowing);

        // then
        try {
            wrapped.doTest((T1)Integer.valueOf(100),(T2)Integer.valueOf(100),(char)100);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(NestedException.class)
                    .hasCauseExactlyInstanceOf(ParseException.class)
                    .hasMessage(ORIGINAL_MESSAGE);
        }
    }


    @Test
    public void testWrapExceptionMethodWrapsTheException() throws X {

        // given
        LBiObjCharPredicate<T1,T2> sutThrowing = LBiObjCharPredicate.l((T1 a1,T2 a2,char a3) -> {
            throw new UnsupportedOperationException();
        });

        // when
        LBiObjCharPredicate<T1,T2> wrapped = sutThrowing.handleBiObjCharPred(handler -> handler
            .wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED));

        // then
        try {
            wrapped.doTest((T1)Integer.valueOf(100),(T2)Integer.valueOf(100),(char)100);
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
        LBiObjCharPredicate<T1,T2> sutThrowing = LBiObjCharPredicate.l((T1 a1,T2 a2,char a3) -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LBiObjCharPredicate<T1,T2> wrapped = sutThrowing.handleBiObjCharPred(handler -> handler
                .wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED)
                .throwIf(IndexOutOfBoundsException.class));

        // then
        try {
            wrapped.doTest((T1)Integer.valueOf(100),(T2)Integer.valueOf(100),(char)100);
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
        LBiObjCharPredicate<T1,T2> sutThrowing = LBiObjCharPredicate.l((T1 a1,T2 a2,char a3) -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LBiObjCharPredicate<T1,T2> wrapped = sutThrowing.handleBiObjCharPred(handler -> handler
                .wrapWhen(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED)
                .throwIf(IndexOutOfBoundsException.class));

        // then
        try {
            wrapped.doTest((T1)Integer.valueOf(100),(T2)Integer.valueOf(100),(char)100);
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
        LBiObjCharPredicate<T1,T2> sutThrowing = LBiObjCharPredicate.l((T1 a1,T2 a2,char a3) -> {
            throw new UnsupportedOperationException(ORIGINAL_MESSAGE);
        });

        // when
        LBiObjCharPredicate<T1,T2> wrapped = sutThrowing.handleBiObjCharPred(h -> Function4U.doNothing());

        // then
        try {
            wrapped.doTest((T1)Integer.valueOf(100),(T2)Integer.valueOf(100),(char)100);
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
        assertThat(sut.negate().doTest((T1)Integer.valueOf(100),(T2)Integer.valueOf(100),(char)100))
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
        LBiObjCharPredicate<T1,T2> fun1 = LBiObjCharPredicate.l((T1 a1,T2 a2,char a3) -> f1Result);
        LBiObjCharPredicate<T1,T2> fun2 = LBiObjCharPredicate.l((T1 a1,T2 a2,char a3) -> f2Result);

        //when
        LBiObjCharPredicate<T1,T2> andFunction = fun1.and(fun2);
        LBiObjCharPredicate<T1,T2> orFunction = fun1.or(fun2);
        LBiObjCharPredicate<T1,T2> xorFunction = fun1.xor(fun2);

        //then
        assertThat(andFunction.doTest((T1)Integer.valueOf(100),(T2)Integer.valueOf(100),(char)100))
                .isEqualTo(andResult);

        assertThat(orFunction.doTest((T1)Integer.valueOf(100),(T2)Integer.valueOf(100),(char)100))
                .isEqualTo(orResult);

        assertThat(xorFunction.doTest((T1)Integer.valueOf(100),(T2)Integer.valueOf(100),(char)100))
                .isEqualTo(xorResult);
    }

    @Test
    public void isEqual() throws X  {
        //when
        LBiObjCharPredicate<T1,T2> equals = LBiObjCharPredicate.isEqual((T1)Integer.valueOf(100),(T2)Integer.valueOf(100),(char)100);

        //then
        assertThat(equals.doTest((T1)Integer.valueOf(100),(T2)Integer.valueOf(100),(char)100))
                .isTrue();

        assertThat(equals.doTest((T1)Integer.valueOf(0),(T2)Integer.valueOf(0),(char)0))
                .isFalse();
    }



    // <editor-fold desc="compose (functional)">

    @Test
    public void testbiObjCharPredComposeChar() throws X {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LBiObjCharPredicate<Integer ,Integer > sutO = (Integer a1,Integer a2,char a3) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo((T1)Integer.valueOf(90));
                assertThat(a2).isEqualTo((T2)Integer.valueOf(91));
                assertThat(a3).isEqualTo((char)92);
                return true;
        };

        LFunction<Integer ,Integer > before1 = p0 -> {
            assertThat(p0).isEqualTo((T1)Integer.valueOf(80));
            beforeCalls.incrementAndGet();
            return 90;
        };
        LFunction<Integer ,Integer > before2 = p1 -> {
            assertThat(p1).isEqualTo((T2)Integer.valueOf(81));
            beforeCalls.incrementAndGet();
            return 91;
        };
        LCharUnaryOperator before3 = p2 -> {
            assertThat(p2).isEqualTo((char)82);
            beforeCalls.incrementAndGet();
            return (char)92;
        };

        //when
        LBiObjCharPredicate<Integer ,Integer > function = sutO.biObjCharPredComposeChar(before1,before2,before3);
        function.doTest((Integer )Integer.valueOf(80),(Integer )Integer.valueOf(81),(char)82);

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(beforeCalls.get()).isEqualTo(3);
    }


    @Test
    public void testbiObjCharPredCompose() throws X {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LBiObjCharPredicate<Integer ,Integer > sutO = (Integer a1,Integer a2,char a3) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo((T1)Integer.valueOf(90));
                assertThat(a2).isEqualTo((T2)Integer.valueOf(91));
                assertThat(a3).isEqualTo((char)92);
                return true;
        };

        LFunction<Integer ,Integer > before1 = p0 -> {
            assertThat(p0).isEqualTo(Integer.valueOf(80));
            beforeCalls.incrementAndGet();
            return 90;
        };
        LFunction<Integer ,Integer > before2 = p1 -> {
            assertThat(p1).isEqualTo(Integer.valueOf(81));
            beforeCalls.incrementAndGet();
            return 91;
        };
        LToCharFunction<Integer > before3 = p2 -> {
            assertThat(p2).isEqualTo(Integer.valueOf(82));
            beforeCalls.incrementAndGet();
            return (char)92;
        };

        //when
        LTriPredicate<Integer ,Integer ,Integer > function = sutO.biObjCharPredCompose(before1,before2,before3);
        function.doTest((Integer )Integer.valueOf(80),(Integer )Integer.valueOf(81),(Integer )Integer.valueOf(82));

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(beforeCalls.get()).isEqualTo(3);
    }

    // </editor-fold>


    // <editor-fold desc="then (functional)">

    @Test
    public void testThen0() throws X  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);


        //given (+ some assertions)
        LBiObjCharPredicate<Integer ,Integer > sutO = (Integer a1,Integer a2,char a3) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo((T1)Integer.valueOf(80));
                assertThat(a2).isEqualTo((T2)Integer.valueOf(81));
                assertThat(a3).isEqualTo((char)82);
                return true;
        };

        LBoolFunction<Integer > thenFunction = p -> {
                thenFunctionCalled.set(true);
                // 
                assertThat(p).isEqualTo(true);
                // V
                return Integer.valueOf(100);
        };

        //when
        LBiObjCharFunction<Integer ,Integer ,Integer > function = sutO.boolToBiObjCharFunction(thenFunction);
        Integer  finalValue = function.doApply((Integer )Integer.valueOf(80),(Integer )Integer.valueOf(81),(char)82);

        //then - finals
        assertThat(finalValue).isEqualTo(Integer.valueOf(100));
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    // </editor-fold>

    @Test
    public void testNesting() {
        assertThat(sut.nestingBiObjCharPred())
            .isSameAs(sut)
            .isInstanceOf(LBiObjCharPredicate.class);
    }

    @Test
    public void testShoving() {
        assertThat(sut.shovingBiObjCharPred())
            .isSameAs(sut)
            .isInstanceOf(LBiObjCharPredicate.class);
    }

    @Test
    public void testNestingX() {
        assertThat(sut.nestingBiObjCharPredX())
            .isSameAs(sut)
            .isInstanceOf(LBiObjCharPredicateX.class);
    }

    @Test
    public void testShovingX() {
        assertThat(sut.shovingBiObjCharPredX())
            .isSameAs(sut)
            .isInstanceOf(LBiObjCharPredicateX.class);
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void testShove() {

        // given
        LBiObjCharPredicate<T1,T2> sutThrowing = LBiObjCharPredicate.l((T1 a1,T2 a2,char a3) -> {
            throw new UnsupportedOperationException();
        });

        // when
        sutThrowing.shovingBiObjCharPred().doTest((T1)Integer.valueOf(100),(T2)Integer.valueOf(100),(char)100);
    }

    @Test
    public void testHandleBiObjCharPred() throws X {

        // given
        LBiObjCharPredicate<T1,T2> sutThrowing = LBiObjCharPredicate.l((T1 a1,T2 a2,char a3) -> {
            throw new UnsupportedOperationException();
        });

        // when
        LBiObjCharPredicate<T1,T2> wrapped = sutThrowing.handleBiObjCharPred(h -> {
            h.wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED);
        });

        // then
        try {
            wrapped.doTest((T1)Integer.valueOf(100),(T2)Integer.valueOf(100),(char)100);
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
                .contains("LBiObjCharPredicate: boolean doTest(T1 a1,T2 a2,char a3)");
    }


    @Test
    public void isThrowing() {
        assertThat(sut.isThrowing())
            .isFalse();
    }

    //<editor-fold desc="Variants">

    private boolean variant1(T1 a1,char a3,T2 a2) {
        return true;
    }

    @Test
    public void compilerSubstituteVariant1() {
        LBiObjCharPredicate lambda = LBiObjCharPredicate./*<T1,T2>*/l1(this::variant1);

        assertThat(lambda).isInstanceOf(LBiObjCharPredicate.V1.class);
    }


    private boolean variant2(T2 a2,T1 a1,char a3) {
        return true;
    }

    @Test
    public void compilerSubstituteVariant2() {
        LBiObjCharPredicate lambda = LBiObjCharPredicate./*<T1,T2>*/l2(this::variant2);

        assertThat(lambda).isInstanceOf(LBiObjCharPredicate.V2.class);
    }


    private boolean variant3(T2 a2,char a3,T1 a1) {
        return true;
    }

    @Test
    public void compilerSubstituteVariant3() {
        LBiObjCharPredicate lambda = LBiObjCharPredicate./*<T1,T2>*/l3(this::variant3);

        assertThat(lambda).isInstanceOf(LBiObjCharPredicate.V3.class);
    }


    private boolean variant4(char a3,T1 a1,T2 a2) {
        return true;
    }

    @Test
    public void compilerSubstituteVariant4() {
        LBiObjCharPredicate lambda = LBiObjCharPredicate./*<T1,T2>*/l4(this::variant4);

        assertThat(lambda).isInstanceOf(LBiObjCharPredicate.V4.class);
    }


    private boolean variant5(char a3,T2 a2,T1 a1) {
        return true;
    }

    @Test
    public void compilerSubstituteVariant5() {
        LBiObjCharPredicate lambda = LBiObjCharPredicate./*<T1,T2>*/l5(this::variant5);

        assertThat(lambda).isInstanceOf(LBiObjCharPredicate.V5.class);
    }

    //</editor-fold>


    @Test void safeCompiles() {
        LBiObjCharPredicate r1 = LBiObjCharPredicate.safe(sut); //NOSONAR
        LBiObjCharPredicateX r2 = LBiObjCharPredicate.safe(sut); //NOSONAR
    }

    @Test void safePropagates() {
        Object result = LBiObjCharPredicate.safe(sut);
        assertThat(result).isSameAs(sut);
    }

    @Test void safeProtectsAgainstNpe() {
        Object result = LBiObjCharPredicate.safe(null);
        assertThat(result).isSameAs(LBiObjCharPredicate.l(LBiObjCharPredicate.safe()));
    }

    @Test  void safeSupplierPropagates() {
        LSupplier<LBiObjCharPredicate<T1,T2>> supplier = ()->sut;
        Object result = LBiObjCharPredicate.safeSupplier(supplier);
        assertThat(result).isSameAs(supplier);
    }

    @Test  void safeSupplierProtectsAgainstNpe() {
        Object result = LBiObjCharPredicate.safeSupplier(null);
        assertThat(result).isSameAs(LBiObjCharPredicate.safeSupplier());
    }

    @Test  void safeSupplierCompiles() {
        LSupplier<LBiObjCharPredicate<T1,T2>> r1 = LBiObjCharPredicate.safeSupplier(()->sut);  //NOSONAR
    }

}
