/*
 * This file is part of "lunisolar-magma".
 *
 * (C) Copyright 2014-2019 Lunisolar (http://lunisolar.eu/).
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
public class LQuadPredicateTest<T1,T2,T3,T4> {
    private static final String ORIGINAL_MESSAGE = "Original message";
    private static final String EXCEPTION_WAS_WRAPPED = "Exception was wrapped.";
    private static final String NO_EXCEPTION_WERE_THROWN = "No exception were thrown.";

    private boolean testValue = true;



    private LQuadPredicate<Integer,Integer,Integer,Integer> sut = new LQuadPredicate<Integer,Integer,Integer,Integer>(){
        public  boolean testX(Integer a1,Integer a2,Integer a3,Integer a4)  {
            return testValue;
        }
    };




    private LQuadPredicate<Integer,Integer,Integer,Integer> sutAlwaysThrowing = LQuadPredicate.quadPred((a1,a2,a3,a4) -> {
            throw new ParseException(ORIGINAL_MESSAGE, 0);
    });

    private LQuadPredicate<Integer,Integer,Integer,Integer> sutAlwaysThrowingUnchecked = LQuadPredicate.quadPred((a1,a2,a3,a4) -> {
            throw new IndexOutOfBoundsException(ORIGINAL_MESSAGE);
    });


    @Test
    public void testTheResult() throws Throwable {
        assertThat(sut.test(100,100,100,100))
            .isEqualTo(testValue);
    }

    @Test
    public void testTupleCall() throws Throwable {

        LQuad<Integer,Integer,Integer,Integer> domainObject = Tuple4U.quad(100,100,100,100);

        Object result = sut.tupleTest(domainObject);

        assertThat(result)
            .isEqualTo(testValue);
    }

    @Test
    public void testNonNullTest() throws Throwable {
        assertThat(sut.nonNullTest(100,100,100,100))
            .isEqualTo(testValue);
    }

    @Test
    public void testNestingTestUnchecked() throws Throwable {

        // then
        try {
            sutAlwaysThrowingUnchecked.nestingTest(100,100,100,100);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                    .hasNoCause()
                    .hasMessage(ORIGINAL_MESSAGE);
        }
    }

    @Test
    public void testShovingTestUnchecked() throws Throwable {

        // then
        try {
            sutAlwaysThrowingUnchecked.shovingTest(100,100,100,100);
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
        assertThat(sut.doApplyAsBoolean(100,100,100,100))
            .isEqualTo(testValue);

    }


    @Test
    public void testFunctionalInterfaceDescription() throws Throwable {
        assertThat(sut.functionalInterfaceDescription())
            .isEqualTo("LQuadPredicate: boolean test(T1 a1,T2 a2,T3 a3,T4 a4)");
    }

    @Test
    public void testQuadPredMethod() throws Throwable {
        assertThat(LQuadPredicate.quadPred((a1,a2,a3,a4) -> testValue ))
            .isInstanceOf(LQuadPredicate.class);
    }




    @Test
    public void testnegate() throws Throwable {
        assertThat(sut.negate().test(100,100,100,100))
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
        LQuadPredicate<Integer,Integer,Integer,Integer> fun1 = LQuadPredicate.quadPred((a1,a2,a3,a4) -> f1Result);
        LQuadPredicate<Integer,Integer,Integer,Integer> fun2 = LQuadPredicate.quadPred((a1,a2,a3,a4) -> f2Result);

        //when
        LQuadPredicate<Integer,Integer,Integer,Integer> andFunction = fun1.and(fun2);
        LQuadPredicate<Integer,Integer,Integer,Integer> orFunction = fun1.or(fun2);
        LQuadPredicate<Integer,Integer,Integer,Integer> xorFunction = fun1.xor(fun2);

        //then
        assertThat(andFunction.test(100,100,100,100))
                .isEqualTo(andResult);

        assertThat(orFunction.test(100,100,100,100))
                .isEqualTo(orResult);

        assertThat(xorFunction.test(100,100,100,100))
                .isEqualTo(xorResult);
    }

    @Test
    public void testIsEqual() throws Throwable  {
        //when
        LQuadPredicate<Integer,Integer,Integer,Integer> equals = LQuadPredicate.isEqual(1,1,1,1);

        //then
        assertThat(equals.test(1,1,1,1))
                .isTrue();

        assertThat(equals.test(0,0,0,0))
                .isFalse();
    }



    // <editor-fold desc="compose (functional)">

    @Test
    public void testCompose() throws Throwable {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LQuadPredicate<Integer,Integer,Integer,Integer> sutO = (a1,a2,a3,a4) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(90);
                assertThat(a2).isEqualTo(91);
                assertThat(a3).isEqualTo(92);
                assertThat(a4).isEqualTo(93);
                return true;
        };

        LFunction<Integer,Integer> before1 = p0 -> {
            assertThat(p0).isEqualTo(80);
            beforeCalls.incrementAndGet();
            return 90;
        };
        LFunction<Integer,Integer> before2 = p1 -> {
            assertThat(p1).isEqualTo(81);
            beforeCalls.incrementAndGet();
            return 91;
        };
        LFunction<Integer,Integer> before3 = p2 -> {
            assertThat(p2).isEqualTo(82);
            beforeCalls.incrementAndGet();
            return 92;
        };
        LFunction<Integer,Integer> before4 = p3 -> {
            assertThat(p3).isEqualTo(83);
            beforeCalls.incrementAndGet();
            return 93;
        };

        //when
        LQuadPredicate<Integer,Integer,Integer,Integer> function = sutO.compose(before1,before2,before3,before4);
        function.test(80,81,82,83);

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(beforeCalls.get()).isEqualTo(4);
    }

    // </editor-fold>



    // <editor-fold desc="then (functional)">

    @Test
    public void testBoolToQuadFunc0() throws Throwable  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);

        //given (+ some assertions)
        LQuadPredicate<Integer,Integer,Integer,Integer> sutO = (a1,a2,a3,a4) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(80);
                assertThat(a2).isEqualTo(81);
                assertThat(a3).isEqualTo(82);
                assertThat(a4).isEqualTo(83);
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
        LQuadFunction<Integer,Integer,Integer,Integer,Integer> function = sutO.boolToQuadFunc(thenFunction);
        Integer finalValue = function.apply(80,81,82,83);

        //then - finals
        assertThat(finalValue).isEqualTo(100);
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    // </editor-fold>


    @Test(expectedExceptions = RuntimeException.class)
    public void testShove() {

        // given
        LQuadPredicate<Integer,Integer,Integer,Integer> sutThrowing = LQuadPredicate.quadPred((a1,a2,a3,a4) -> {
            throw new UnsupportedOperationException();
        });

        // when
        sutThrowing.shovingTest(100,100,100,100);
    }


    @Test
    public void testToString() throws Throwable {

        assertThat(sut.toString())
                .isInstanceOf(String.class)
                .startsWith(this.getClass().getName()+"$");

        assertThat(String.format("%s", sut))
                .isInstanceOf(String.class)
                .contains("LQuadPredicate: boolean test(T1 a1,T2 a2,T3 a3,T4 a4)");
    }


    @Test
    public void isThrowing() {
        assertThat(sut.isThrowing())
            .isFalse();
    }

    //<editor-fold desc="Variants">

    private boolean variantLBiObj1Obj3Obj2Pred(Integer a1,Integer a2,Integer a4,Integer a3) {
        return true;
    }

    @Test
    public void compilerSubstituteVariantLBiObj1Obj3Obj2Pred() {
        LQuadPredicate lambda = LQuadPredicate./*<T1,T2,T3,T4>*/biObj1Obj3Obj2Pred(this::variantLBiObj1Obj3Obj2Pred);

        assertThat(lambda).isInstanceOf(LQuadPredicate.LBiObj1Obj3Obj2Pred.class);
    }


    private boolean variantLObj0Obj2BiObj3Pred(Integer a1,Integer a3,Integer a2,Integer a4) {
        return true;
    }

    @Test
    public void compilerSubstituteVariantLObj0Obj2BiObj3Pred() {
        LQuadPredicate lambda = LQuadPredicate./*<T1,T2,T3,T4>*/obj0Obj2BiObj3Pred(this::variantLObj0Obj2BiObj3Pred);

        assertThat(lambda).isInstanceOf(LQuadPredicate.LObj0Obj2BiObj3Pred.class);
    }


    private boolean variantLObj0Obj2Obj3Obj1Pred(Integer a1,Integer a3,Integer a4,Integer a2) {
        return true;
    }

    @Test
    public void compilerSubstituteVariantLObj0Obj2Obj3Obj1Pred() {
        LQuadPredicate lambda = LQuadPredicate./*<T1,T2,T3,T4>*/obj0Obj2Obj3Obj1Pred(this::variantLObj0Obj2Obj3Obj1Pred);

        assertThat(lambda).isInstanceOf(LQuadPredicate.LObj0Obj2Obj3Obj1Pred.class);
    }


    private boolean variantLObj0Obj3Obj1Obj2Pred(Integer a1,Integer a4,Integer a2,Integer a3) {
        return true;
    }

    @Test
    public void compilerSubstituteVariantLObj0Obj3Obj1Obj2Pred() {
        LQuadPredicate lambda = LQuadPredicate./*<T1,T2,T3,T4>*/obj0Obj3Obj1Obj2Pred(this::variantLObj0Obj3Obj1Obj2Pred);

        assertThat(lambda).isInstanceOf(LQuadPredicate.LObj0Obj3Obj1Obj2Pred.class);
    }


    private boolean variantLObj0BiObj2Obj1Pred(Integer a1,Integer a4,Integer a3,Integer a2) {
        return true;
    }

    @Test
    public void compilerSubstituteVariantLObj0BiObj2Obj1Pred() {
        LQuadPredicate lambda = LQuadPredicate./*<T1,T2,T3,T4>*/obj0BiObj2Obj1Pred(this::variantLObj0BiObj2Obj1Pred);

        assertThat(lambda).isInstanceOf(LQuadPredicate.LObj0BiObj2Obj1Pred.class);
    }


    private boolean variantLObj1TriObj3Pred(Integer a2,Integer a1,Integer a3,Integer a4) {
        return true;
    }

    @Test
    public void compilerSubstituteVariantLObj1TriObj3Pred() {
        LQuadPredicate lambda = LQuadPredicate./*<T1,T2,T3,T4>*/obj1TriObj3Pred(this::variantLObj1TriObj3Pred);

        assertThat(lambda).isInstanceOf(LQuadPredicate.LObj1TriObj3Pred.class);
    }


    private boolean variantLObj1Obj0Obj3Obj2Pred(Integer a2,Integer a1,Integer a4,Integer a3) {
        return true;
    }

    @Test
    public void compilerSubstituteVariantLObj1Obj0Obj3Obj2Pred() {
        LQuadPredicate lambda = LQuadPredicate./*<T1,T2,T3,T4>*/obj1Obj0Obj3Obj2Pred(this::variantLObj1Obj0Obj3Obj2Pred);

        assertThat(lambda).isInstanceOf(LQuadPredicate.LObj1Obj0Obj3Obj2Pred.class);
    }


    private boolean variantLObj1Obj2BiObj3Pred(Integer a2,Integer a3,Integer a1,Integer a4) {
        return true;
    }

    @Test
    public void compilerSubstituteVariantLObj1Obj2BiObj3Pred() {
        LQuadPredicate lambda = LQuadPredicate./*<T1,T2,T3,T4>*/obj1Obj2BiObj3Pred(this::variantLObj1Obj2BiObj3Pred);

        assertThat(lambda).isInstanceOf(LQuadPredicate.LObj1Obj2BiObj3Pred.class);
    }


    private boolean variantLObj1Obj2Obj3Obj0Pred(Integer a2,Integer a3,Integer a4,Integer a1) {
        return true;
    }

    @Test
    public void compilerSubstituteVariantLObj1Obj2Obj3Obj0Pred() {
        LQuadPredicate lambda = LQuadPredicate./*<T1,T2,T3,T4>*/obj1Obj2Obj3Obj0Pred(this::variantLObj1Obj2Obj3Obj0Pred);

        assertThat(lambda).isInstanceOf(LQuadPredicate.LObj1Obj2Obj3Obj0Pred.class);
    }


    private boolean variantLObj1Obj3Obj0Obj2Pred(Integer a2,Integer a4,Integer a1,Integer a3) {
        return true;
    }

    @Test
    public void compilerSubstituteVariantLObj1Obj3Obj0Obj2Pred() {
        LQuadPredicate lambda = LQuadPredicate./*<T1,T2,T3,T4>*/obj1Obj3Obj0Obj2Pred(this::variantLObj1Obj3Obj0Obj2Pred);

        assertThat(lambda).isInstanceOf(LQuadPredicate.LObj1Obj3Obj0Obj2Pred.class);
    }


    private boolean variantLObj1BiObj2Obj0Pred(Integer a2,Integer a4,Integer a3,Integer a1) {
        return true;
    }

    @Test
    public void compilerSubstituteVariantLObj1BiObj2Obj0Pred() {
        LQuadPredicate lambda = LQuadPredicate./*<T1,T2,T3,T4>*/obj1BiObj2Obj0Pred(this::variantLObj1BiObj2Obj0Pred);

        assertThat(lambda).isInstanceOf(LQuadPredicate.LObj1BiObj2Obj0Pred.class);
    }


    private boolean variantLObj2Obj0BiObj3Pred(Integer a3,Integer a1,Integer a2,Integer a4) {
        return true;
    }

    @Test
    public void compilerSubstituteVariantLObj2Obj0BiObj3Pred() {
        LQuadPredicate lambda = LQuadPredicate./*<T1,T2,T3,T4>*/obj2Obj0BiObj3Pred(this::variantLObj2Obj0BiObj3Pred);

        assertThat(lambda).isInstanceOf(LQuadPredicate.LObj2Obj0BiObj3Pred.class);
    }


    private boolean variantLObj2Obj0Obj3Obj1Pred(Integer a3,Integer a1,Integer a4,Integer a2) {
        return true;
    }

    @Test
    public void compilerSubstituteVariantLObj2Obj0Obj3Obj1Pred() {
        LQuadPredicate lambda = LQuadPredicate./*<T1,T2,T3,T4>*/obj2Obj0Obj3Obj1Pred(this::variantLObj2Obj0Obj3Obj1Pred);

        assertThat(lambda).isInstanceOf(LQuadPredicate.LObj2Obj0Obj3Obj1Pred.class);
    }


    private boolean variantLBiObj1BiObj3Pred(Integer a3,Integer a2,Integer a1,Integer a4) {
        return true;
    }

    @Test
    public void compilerSubstituteVariantLBiObj1BiObj3Pred() {
        LQuadPredicate lambda = LQuadPredicate./*<T1,T2,T3,T4>*/biObj1BiObj3Pred(this::variantLBiObj1BiObj3Pred);

        assertThat(lambda).isInstanceOf(LQuadPredicate.LBiObj1BiObj3Pred.class);
    }


    private boolean variantLBiObj1Obj3Obj0Pred(Integer a3,Integer a2,Integer a4,Integer a1) {
        return true;
    }

    @Test
    public void compilerSubstituteVariantLBiObj1Obj3Obj0Pred() {
        LQuadPredicate lambda = LQuadPredicate./*<T1,T2,T3,T4>*/biObj1Obj3Obj0Pred(this::variantLBiObj1Obj3Obj0Pred);

        assertThat(lambda).isInstanceOf(LQuadPredicate.LBiObj1Obj3Obj0Pred.class);
    }


    private boolean variantLObj2Obj3Obj0Obj1Pred(Integer a3,Integer a4,Integer a1,Integer a2) {
        return true;
    }

    @Test
    public void compilerSubstituteVariantLObj2Obj3Obj0Obj1Pred() {
        LQuadPredicate lambda = LQuadPredicate./*<T1,T2,T3,T4>*/obj2Obj3Obj0Obj1Pred(this::variantLObj2Obj3Obj0Obj1Pred);

        assertThat(lambda).isInstanceOf(LQuadPredicate.LObj2Obj3Obj0Obj1Pred.class);
    }


    private boolean variantLObj2Obj3Obj1Obj0Pred(Integer a3,Integer a4,Integer a2,Integer a1) {
        return true;
    }

    @Test
    public void compilerSubstituteVariantLObj2Obj3Obj1Obj0Pred() {
        LQuadPredicate lambda = LQuadPredicate./*<T1,T2,T3,T4>*/obj2Obj3Obj1Obj0Pred(this::variantLObj2Obj3Obj1Obj0Pred);

        assertThat(lambda).isInstanceOf(LQuadPredicate.LObj2Obj3Obj1Obj0Pred.class);
    }


    private boolean variantLObj3Obj0Obj1Obj2Pred(Integer a4,Integer a1,Integer a2,Integer a3) {
        return true;
    }

    @Test
    public void compilerSubstituteVariantLObj3Obj0Obj1Obj2Pred() {
        LQuadPredicate lambda = LQuadPredicate./*<T1,T2,T3,T4>*/obj3Obj0Obj1Obj2Pred(this::variantLObj3Obj0Obj1Obj2Pred);

        assertThat(lambda).isInstanceOf(LQuadPredicate.LObj3Obj0Obj1Obj2Pred.class);
    }


    private boolean variantLObj3BiObj2Obj1Pred(Integer a4,Integer a1,Integer a3,Integer a2) {
        return true;
    }

    @Test
    public void compilerSubstituteVariantLObj3BiObj2Obj1Pred() {
        LQuadPredicate lambda = LQuadPredicate./*<T1,T2,T3,T4>*/obj3BiObj2Obj1Pred(this::variantLObj3BiObj2Obj1Pred);

        assertThat(lambda).isInstanceOf(LQuadPredicate.LObj3BiObj2Obj1Pred.class);
    }


    private boolean variantLBiObj1Obj0Obj2Pred(Integer a4,Integer a2,Integer a1,Integer a3) {
        return true;
    }

    @Test
    public void compilerSubstituteVariantLBiObj1Obj0Obj2Pred() {
        LQuadPredicate lambda = LQuadPredicate./*<T1,T2,T3,T4>*/biObj1Obj0Obj2Pred(this::variantLBiObj1Obj0Obj2Pred);

        assertThat(lambda).isInstanceOf(LQuadPredicate.LBiObj1Obj0Obj2Pred.class);
    }


    private boolean variantLTriObj2Obj0Pred(Integer a4,Integer a2,Integer a3,Integer a1) {
        return true;
    }

    @Test
    public void compilerSubstituteVariantLTriObj2Obj0Pred() {
        LQuadPredicate lambda = LQuadPredicate./*<T1,T2,T3,T4>*/triObj2Obj0Pred(this::variantLTriObj2Obj0Pred);

        assertThat(lambda).isInstanceOf(LQuadPredicate.LTriObj2Obj0Pred.class);
    }


    private boolean variantLObj3Obj2Obj0Obj1Pred(Integer a4,Integer a3,Integer a1,Integer a2) {
        return true;
    }

    @Test
    public void compilerSubstituteVariantLObj3Obj2Obj0Obj1Pred() {
        LQuadPredicate lambda = LQuadPredicate./*<T1,T2,T3,T4>*/obj3Obj2Obj0Obj1Pred(this::variantLObj3Obj2Obj0Obj1Pred);

        assertThat(lambda).isInstanceOf(LQuadPredicate.LObj3Obj2Obj0Obj1Pred.class);
    }


    private boolean variantLObj3Obj2Obj1Obj0Pred(Integer a4,Integer a3,Integer a2,Integer a1) {
        return true;
    }

    @Test
    public void compilerSubstituteVariantLObj3Obj2Obj1Obj0Pred() {
        LQuadPredicate lambda = LQuadPredicate./*<T1,T2,T3,T4>*/obj3Obj2Obj1Obj0Pred(this::variantLObj3Obj2Obj1Obj0Pred);

        assertThat(lambda).isInstanceOf(LQuadPredicate.LObj3Obj2Obj1Obj0Pred.class);
    }

    //</editor-fold>


    @Test void safeCompiles() {
        LQuadPredicate r1 = LQuadPredicate.safe(sut); //NOSONAR
    }

    @Test void safePropagates() {
        Object result = LQuadPredicate.safe(sut);
        assertThat(result).isSameAs(sut);
    }

    @Test void safeProtectsAgainstNpe() {
        Object result = LQuadPredicate.safe(null);
        assertThat(result).isSameAs(LQuadPredicate.quadPred(LQuadPredicate.safe()));
    }

    @Test  void safeSupplierPropagates() {
        LSupplier<LQuadPredicate<Integer,Integer,Integer,Integer>> supplier = ()->sut;
        Object result = LQuadPredicate.safeSupplier(supplier);
        assertThat(result).isSameAs(supplier);
    }

    @Test  void safeSupplierProtectsAgainstNpe() {
        Object result = LQuadPredicate.safeSupplier(null);
        assertThat(result).isSameAs(LQuadPredicate.safeSupplier());
    }

    @Test  void safeSupplierCompiles() {
        LSupplier<LQuadPredicate<Integer,Integer,Integer,Integer>> r1 = LQuadPredicate.safeSupplier(()->sut);  //NOSONAR
    }

}
