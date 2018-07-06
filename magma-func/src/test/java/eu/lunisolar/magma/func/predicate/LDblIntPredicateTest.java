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
public class LDblIntPredicateTest {
    private static final String ORIGINAL_MESSAGE = "Original message";
    private static final String EXCEPTION_WAS_WRAPPED = "Exception was wrapped.";
    private static final String NO_EXCEPTION_WERE_THROWN = "No exception were thrown.";

    private boolean testValue = true;



    private LDblIntPredicate sut = new LDblIntPredicate(){
        public  boolean doTestX(double a1,int a2)  {
            return testValue;
        }
    };




    private LDblIntPredicate sutAlwaysThrowing = LDblIntPredicate.dblIntPred((a1,a2) -> {
            throw new ParseException(ORIGINAL_MESSAGE, 0);
    });

    private LDblIntPredicate sutAlwaysThrowingUnchecked = LDblIntPredicate.dblIntPred((a1,a2) -> {
            throw new IndexOutOfBoundsException(ORIGINAL_MESSAGE);
    });


    @Test
    public void testTheResult() throws Throwable {
        assertThat(sut.doTest(100d,100))
            .isEqualTo(testValue);
    }

    @Test
    public void testTupleCall() throws Throwable {

        LDblIntPair domainObject = Tuple4U.dblIntPair(100d,100);

        Object result = sut.tupleTest(domainObject);

        assertThat(result)
            .isEqualTo(testValue);
    }

    @Test
    public void testNonNullDoTest() throws Throwable {
        assertThat(sut.nonNullDoTest(100d,100))
            .isEqualTo(testValue);
    }

    @Test
    public void testNestingDoTestUnchecked() throws Throwable {

        // then
        try {
            sutAlwaysThrowingUnchecked.nestingDoTest(100d,100);
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
            sutAlwaysThrowingUnchecked.shovingDoTest(100d,100);
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
        assertThat(sut.doApplyAsBoolean(100d,100))
            .isEqualTo(testValue);

    }


    @Test
    public void testFunctionalInterfaceDescription() throws Throwable {
        assertThat(sut.functionalInterfaceDescription())
            .isEqualTo("LDblIntPredicate: boolean doTest(double a1,int a2)");
    }

    @Test
    public void testDblIntPredMethod() throws Throwable {
        assertThat(LDblIntPredicate.dblIntPred((a1,a2) -> testValue ))
            .isInstanceOf(LDblIntPredicate.class);
    }




    @Test
    public void testnegate() throws Throwable {
        assertThat(sut.negate().doTest(100d,100))
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
        LDblIntPredicate fun1 = LDblIntPredicate.dblIntPred((a1,a2) -> f1Result);
        LDblIntPredicate fun2 = LDblIntPredicate.dblIntPred((a1,a2) -> f2Result);

        //when
        LDblIntPredicate andFunction = fun1.and(fun2);
        LDblIntPredicate orFunction = fun1.or(fun2);
        LDblIntPredicate xorFunction = fun1.xor(fun2);

        //then
        assertThat(andFunction.doTest(100d,100))
                .isEqualTo(andResult);

        assertThat(orFunction.doTest(100d,100))
                .isEqualTo(orResult);

        assertThat(xorFunction.doTest(100d,100))
                .isEqualTo(xorResult);
    }

    @Test
    public void testIsEqual() throws Throwable  {
        //when
        LDblIntPredicate equals = LDblIntPredicate.isEqual(1d,1);

        //then
        assertThat(equals.doTest(1d,1))
                .isTrue();

        assertThat(equals.doTest(0d,0))
                .isFalse();
    }



    // <editor-fold desc="compose (functional)">

    @Test
    public void testDblIntPredComposeDblInt() throws Throwable {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LDblIntPredicate sutO = (a1,a2) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(90d);
                assertThat(a2).isEqualTo(91);
                return true;
        };

        LDblUnaryOperator before1 = p0 -> {
            assertThat(p0).isEqualTo(80d);
            beforeCalls.incrementAndGet();
            return 90d;
        };
        LIntUnaryOperator before2 = p1 -> {
            assertThat(p1).isEqualTo(81);
            beforeCalls.incrementAndGet();
            return 91;
        };

        //when
        LDblIntPredicate function = sutO.dblIntPredComposeDblInt(before1,before2);
        function.doTest(80d,81);

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(beforeCalls.get()).isEqualTo(2);
    }


    @Test
    public void testDblIntPredCompose() throws Throwable {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LDblIntPredicate sutO = (a1,a2) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(90d);
                assertThat(a2).isEqualTo(91);
                return true;
        };

        LToDblFunction<Integer> before1 = p0 -> {
            assertThat(p0).isEqualTo(80);
            beforeCalls.incrementAndGet();
            return 90d;
        };
        LToIntFunction<Integer> before2 = p1 -> {
            assertThat(p1).isEqualTo(81);
            beforeCalls.incrementAndGet();
            return 91;
        };

        //when
        LBiPredicate<Integer,Integer> function = sutO.dblIntPredCompose(before1,before2);
        function.doTest(80,81);

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(beforeCalls.get()).isEqualTo(2);
    }

    // </editor-fold>



    // <editor-fold desc="then (functional)">

    @Test
    public void testBoolToDblIntPred0() throws Throwable  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);

        //given (+ some assertions)
        LDblIntPredicate sutO = (a1,a2) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(80d);
                assertThat(a2).isEqualTo(81);
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
        LDblIntPredicate function = sutO.boolToDblIntPred(thenFunction);
        boolean finalValue = function.doTest(80d,81);

        //then - finals
        assertThat(finalValue).isEqualTo(true);
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    // </editor-fold>

    @Test
    public void testNesting() {
        assertThat(sut.nestingDblIntPred())
            .isSameAs(sut)
            .isInstanceOf(LDblIntPredicate.class);
    }

    @Test
    public void testShoving() {
        assertThat(sut.shovingDblIntPred())
            .isSameAs(sut)
            .isInstanceOf(LDblIntPredicate.class);
    }


    @Test(expectedExceptions = RuntimeException.class)
    public void testShove() {

        // given
        LDblIntPredicate sutThrowing = LDblIntPredicate.dblIntPred((a1,a2) -> {
            throw new UnsupportedOperationException();
        });

        // when
        sutThrowing.shovingDblIntPred().doTest(100d,100);
    }


    @Test
    public void testToString() throws Throwable {

        assertThat(sut.toString())
                .isInstanceOf(String.class)
                .startsWith(this.getClass().getName()+"$");

        assertThat(String.format("%s", sut))
                .isInstanceOf(String.class)
                .contains("LDblIntPredicate: boolean doTest(double a1,int a2)");
    }


    @Test
    public void isThrowing() {
        assertThat(sut.isThrowing())
            .isFalse();
    }

    //<editor-fold desc="Variants">

    private boolean variantLIntDblPred(int a2,double a1) {
        return true;
    }

    @Test
    public void compilerSubstituteVariantLIntDblPred() {
        LDblIntPredicate lambda = LDblIntPredicate./**/intDblPred(this::variantLIntDblPred);

        assertThat(lambda).isInstanceOf(LDblIntPredicate.LIntDblPred.class);
    }

    //</editor-fold>


    @Test void safeCompiles() {
        LDblIntPredicate r1 = LDblIntPredicate.safe(sut); //NOSONAR
    }

    @Test void safePropagates() {
        Object result = LDblIntPredicate.safe(sut);
        assertThat(result).isSameAs(sut);
    }

    @Test void safeProtectsAgainstNpe() {
        Object result = LDblIntPredicate.safe(null);
        assertThat(result).isSameAs(LDblIntPredicate.dblIntPred(LDblIntPredicate.safe()));
    }

    @Test  void safeSupplierPropagates() {
        LSupplier<LDblIntPredicate> supplier = ()->sut;
        Object result = LDblIntPredicate.safeSupplier(supplier);
        assertThat(result).isSameAs(supplier);
    }

    @Test  void safeSupplierProtectsAgainstNpe() {
        Object result = LDblIntPredicate.safeSupplier(null);
        assertThat(result).isSameAs(LDblIntPredicate.safeSupplier());
    }

    @Test  void safeSupplierCompiles() {
        LSupplier<LDblIntPredicate> r1 = LDblIntPredicate.safeSupplier(()->sut);  //NOSONAR
    }

}
