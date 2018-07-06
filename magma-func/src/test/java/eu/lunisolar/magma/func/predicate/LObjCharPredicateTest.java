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
import eu.lunisolar.magma.func.tuple.*; // NOSONAR
import static org.assertj.core.api.Assertions.*; //NOSONAR
import java.util.function.*; // NOSONAR

/** The test obviously concentrate on the interface methods the function it self is very simple.  */
public class LObjCharPredicateTest<T> {
    private static final String ORIGINAL_MESSAGE = "Original message";
    private static final String EXCEPTION_WAS_WRAPPED = "Exception was wrapped.";
    private static final String NO_EXCEPTION_WERE_THROWN = "No exception were thrown.";

    private boolean testValue = true;



    private LObjCharPredicate<Integer> sut = new LObjCharPredicate<Integer>(){
        public  boolean doTestX(Integer a1,char a2)  {
            return testValue;
        }
    };




    private LObjCharPredicate<Integer> sutAlwaysThrowing = LObjCharPredicate.objCharPred((a1,a2) -> {
            throw new ParseException(ORIGINAL_MESSAGE, 0);
    });

    private LObjCharPredicate<Integer> sutAlwaysThrowingUnchecked = LObjCharPredicate.objCharPred((a1,a2) -> {
            throw new IndexOutOfBoundsException(ORIGINAL_MESSAGE);
    });


    @Test
    public void testTheResult() throws Throwable {
        assertThat(sut.doTest(100,'\u0100'))
            .isEqualTo(testValue);
    }

    @Test
    public void testTupleCall() throws Throwable {

        LObjCharPair<Integer> domainObject = Tuple4U.objCharPair(100,'\u0100');

        Object result = sut.tupleTest(domainObject);

        assertThat(result)
            .isEqualTo(testValue);
    }

    @Test
    public void testNonNullDoTest() throws Throwable {
        assertThat(sut.nonNullDoTest(100,'\u0100'))
            .isEqualTo(testValue);
    }

    @Test
    public void testNestingDoTestUnchecked() throws Throwable {

        // then
        try {
            sutAlwaysThrowingUnchecked.nestingDoTest(100,'\u0100');
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                    .hasNoCause()
                    .hasMessage(ORIGINAL_MESSAGE);
        }
    }

    @Test
    public void testShovingDoTestUnchecked() throws Throwable {

        // then
        try {
            sutAlwaysThrowingUnchecked.shovingDoTest(100,'\u0100');
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                    .hasNoCause()
                    .hasMessage(ORIGINAL_MESSAGE);
        }
    }

    @Test
    public void testApplyAsBooleanShouldNotModifyValue() throws Throwable {
        assertThat(sut.doApplyAsBoolean(100,'\u0100'))
            .isEqualTo(testValue);

    }


    @Test
    public void testFunctionalInterfaceDescription() throws Throwable {
        assertThat(sut.functionalInterfaceDescription())
            .isEqualTo("LObjCharPredicate: boolean doTest(T a1,char a2)");
    }

    @Test
    public void testObjCharPredMethod() throws Throwable {
        assertThat(LObjCharPredicate.objCharPred((a1,a2) -> testValue ))
            .isInstanceOf(LObjCharPredicate.class);
    }




    @Test
    public void testnegate() throws Throwable {
        assertThat(sut.negate().doTest(100,'\u0100'))
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
    public void testAndOrXor(final boolean f1Result, final boolean f2Result, final boolean andResult, final boolean orResult, final boolean xorResult) throws Throwable {

        //given
        LObjCharPredicate<Integer> fun1 = LObjCharPredicate.objCharPred((a1,a2) -> f1Result);
        LObjCharPredicate<Integer> fun2 = LObjCharPredicate.objCharPred((a1,a2) -> f2Result);

        //when
        LObjCharPredicate<Integer> andFunction = fun1.and(fun2);
        LObjCharPredicate<Integer> orFunction = fun1.or(fun2);
        LObjCharPredicate<Integer> xorFunction = fun1.xor(fun2);

        //then
        assertThat(andFunction.doTest(100,'\u0100'))
                .isEqualTo(andResult);

        assertThat(orFunction.doTest(100,'\u0100'))
                .isEqualTo(orResult);

        assertThat(xorFunction.doTest(100,'\u0100'))
                .isEqualTo(xorResult);
    }

    @Test
    public void testIsEqual() throws Throwable  {
        //when
        LObjCharPredicate<Integer> equals = LObjCharPredicate.isEqual(1,'\u0001');

        //then
        assertThat(equals.doTest(1,'\u0001'))
                .isTrue();

        assertThat(equals.doTest(0,'\u0000'))
                .isFalse();
    }



    // <editor-fold desc="compose (functional)">

    @Test
    public void testObjCharPredComposeChar() throws Throwable {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LObjCharPredicate<Integer> sutO = (a1,a2) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(90);
                assertThat(a2).isEqualTo('\u0091');
                return true;
        };

        LFunction<Integer,Integer> before1 = p0 -> {
            assertThat(p0).isEqualTo(80);
            beforeCalls.incrementAndGet();
            return 90;
        };
        LCharUnaryOperator before2 = p1 -> {
            assertThat(p1).isEqualTo('\u0081');
            beforeCalls.incrementAndGet();
            return '\u0091';
        };

        //when
        LObjCharPredicate<Integer> function = sutO.objCharPredComposeChar(before1,before2);
        function.doTest(80,'\u0081');

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(beforeCalls.get()).isEqualTo(2);
    }


    @Test
    public void testObjCharPredCompose() throws Throwable {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LObjCharPredicate<Integer> sutO = (a1,a2) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(90);
                assertThat(a2).isEqualTo('\u0091');
                return true;
        };

        LFunction<Integer,Integer> before1 = p0 -> {
            assertThat(p0).isEqualTo(80);
            beforeCalls.incrementAndGet();
            return 90;
        };
        LToCharFunction<Integer> before2 = p1 -> {
            assertThat(p1).isEqualTo(81);
            beforeCalls.incrementAndGet();
            return '\u0091';
        };

        //when
        LBiPredicate<Integer,Integer> function = sutO.objCharPredCompose(before1,before2);
        function.doTest(80,81);

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(beforeCalls.get()).isEqualTo(2);
    }

    // </editor-fold>



    // <editor-fold desc="then (functional)">

    @Test
    public void testBoolToObjCharFunc0() throws Throwable  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);

        //given (+ some assertions)
        LObjCharPredicate<Integer> sutO = (a1,a2) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(80);
                assertThat(a2).isEqualTo('\u0081');
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
        LObjCharFunction<Integer,Integer> function = sutO.boolToObjCharFunc(thenFunction);
        Integer finalValue = function.doApply(80,'\u0081');

        //then - finals
        assertThat(finalValue).isEqualTo(100);
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    @Test
    public void testBoolToObjCharPred1() throws Throwable  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);

        //given (+ some assertions)
        LObjCharPredicate<Integer> sutO = (a1,a2) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(80);
                assertThat(a2).isEqualTo('\u0081');
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
        LObjCharPredicate<Integer> function = sutO.boolToObjCharPred(thenFunction);
        boolean finalValue = function.doTest(80,'\u0081');

        //then - finals
        assertThat(finalValue).isEqualTo(true);
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    // </editor-fold>

    @Test
    public void testNesting() {
        assertThat(sut.nestingObjCharPred())
            .isSameAs(sut)
            .isInstanceOf(LObjCharPredicate.class);
    }

    @Test
    public void testShoving() {
        assertThat(sut.shovingObjCharPred())
            .isSameAs(sut)
            .isInstanceOf(LObjCharPredicate.class);
    }


    @Test(expectedExceptions = RuntimeException.class)
    public void testShove() {

        // given
        LObjCharPredicate<Integer> sutThrowing = LObjCharPredicate.objCharPred((a1,a2) -> {
            throw new UnsupportedOperationException();
        });

        // when
        sutThrowing.shovingObjCharPred().doTest(100,'\u0100');
    }


    @Test
    public void testToString() throws Throwable {

        assertThat(sut.toString())
                .isInstanceOf(String.class)
                .startsWith(this.getClass().getName()+"$");

        assertThat(String.format("%s", sut))
                .isInstanceOf(String.class)
                .contains("LObjCharPredicate: boolean doTest(T a1,char a2)");
    }


    @Test
    public void isThrowing() {
        assertThat(sut.isThrowing())
            .isFalse();
    }

    //<editor-fold desc="Variants">

    private boolean variantLCharObjPred(char a2,Integer a1) {
        return true;
    }

    @Test
    public void compilerSubstituteVariantLCharObjPred() {
        LObjCharPredicate lambda = LObjCharPredicate./*<T>*/charObjPred(this::variantLCharObjPred);

        assertThat(lambda).isInstanceOf(LObjCharPredicate.LCharObjPred.class);
    }

    //</editor-fold>


    @Test void safeCompiles() {
        LObjCharPredicate r1 = LObjCharPredicate.safe(sut); //NOSONAR
    }

    @Test void safePropagates() {
        Object result = LObjCharPredicate.safe(sut);
        assertThat(result).isSameAs(sut);
    }

    @Test void safeProtectsAgainstNpe() {
        Object result = LObjCharPredicate.safe(null);
        assertThat(result).isSameAs(LObjCharPredicate.objCharPred(LObjCharPredicate.safe()));
    }

    @Test  void safeSupplierPropagates() {
        LSupplier<LObjCharPredicate<Integer>> supplier = ()->sut;
        Object result = LObjCharPredicate.safeSupplier(supplier);
        assertThat(result).isSameAs(supplier);
    }

    @Test  void safeSupplierProtectsAgainstNpe() {
        Object result = LObjCharPredicate.safeSupplier(null);
        assertThat(result).isSameAs(LObjCharPredicate.safeSupplier());
    }

    @Test  void safeSupplierCompiles() {
        LSupplier<LObjCharPredicate<Integer>> r1 = LObjCharPredicate.safeSupplier(()->sut);  //NOSONAR
    }

}
