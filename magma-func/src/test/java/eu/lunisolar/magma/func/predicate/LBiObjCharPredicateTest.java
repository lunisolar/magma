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
public class LBiObjCharPredicateTest<T1,T2> {
    private static final String ORIGINAL_MESSAGE = "Original message";
    private static final String EXCEPTION_WAS_WRAPPED = "Exception was wrapped.";
    private static final String NO_EXCEPTION_WERE_THROWN = "No exception were thrown.";

    private boolean testValue = true;



    private LBiObjCharPredicate<Integer,Integer> sut = new LBiObjCharPredicate<Integer,Integer>(){
        public  boolean doTestX(Integer a1,Integer a2,char a3)  {
            return testValue;
        }
    };




    private LBiObjCharPredicate<Integer,Integer> sutAlwaysThrowing = LBiObjCharPredicate.biObjCharPred((a1,a2,a3) -> {
            throw new ParseException(ORIGINAL_MESSAGE, 0);
    });

    private LBiObjCharPredicate<Integer,Integer> sutAlwaysThrowingUnchecked = LBiObjCharPredicate.biObjCharPred((a1,a2,a3) -> {
            throw new IndexOutOfBoundsException(ORIGINAL_MESSAGE);
    });


    @Test
    public void testTheResult() throws Throwable {
        assertThat(sut.doTest(100,100,'\u0100'))
            .isEqualTo(testValue);
    }

    @Test
    public void testTupleCall() throws Throwable {

        LBiObjCharTriple<Integer,Integer> domainObject = Tuple4U.biObjCharTriple(100,100,'\u0100');

        Object result = sut.tupleTest(domainObject);

        assertThat(result)
            .isEqualTo(testValue);
    }

    @Test
    public void testNonNullDoTest() throws Throwable {
        assertThat(sut.nonNullDoTest(100,100,'\u0100'))
            .isEqualTo(testValue);
    }

    @Test
    public void testNestingDoTestUnchecked() throws Throwable {

        // then
        try {
            sutAlwaysThrowingUnchecked.nestingDoTest(100,100,'\u0100');
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
            sutAlwaysThrowingUnchecked.shovingDoTest(100,100,'\u0100');
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
        assertThat(sut.doApplyAsBoolean(100,100,'\u0100'))
            .isEqualTo(testValue);

    }


    @Test
    public void testFunctionalInterfaceDescription() throws Throwable {
        assertThat(sut.functionalInterfaceDescription())
            .isEqualTo("LBiObjCharPredicate: boolean doTest(T1 a1,T2 a2,char a3)");
    }

    @Test
    public void testBiObjCharPredMethod() throws Throwable {
        assertThat(LBiObjCharPredicate.biObjCharPred((a1,a2,a3) -> testValue ))
            .isInstanceOf(LBiObjCharPredicate.class);
    }




    @Test
    public void testnegate() throws Throwable {
        assertThat(sut.negate().doTest(100,100,'\u0100'))
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
        LBiObjCharPredicate<Integer,Integer> fun1 = LBiObjCharPredicate.biObjCharPred((a1,a2,a3) -> f1Result);
        LBiObjCharPredicate<Integer,Integer> fun2 = LBiObjCharPredicate.biObjCharPred((a1,a2,a3) -> f2Result);

        //when
        LBiObjCharPredicate<Integer,Integer> andFunction = fun1.and(fun2);
        LBiObjCharPredicate<Integer,Integer> orFunction = fun1.or(fun2);
        LBiObjCharPredicate<Integer,Integer> xorFunction = fun1.xor(fun2);

        //then
        assertThat(andFunction.doTest(100,100,'\u0100'))
                .isEqualTo(andResult);

        assertThat(orFunction.doTest(100,100,'\u0100'))
                .isEqualTo(orResult);

        assertThat(xorFunction.doTest(100,100,'\u0100'))
                .isEqualTo(xorResult);
    }

    @Test
    public void testIsEqual() throws Throwable  {
        //when
        LBiObjCharPredicate<Integer,Integer> equals = LBiObjCharPredicate.isEqual(1,1,'\u0001');

        //then
        assertThat(equals.doTest(1,1,'\u0001'))
                .isTrue();

        assertThat(equals.doTest(0,0,'\u0000'))
                .isFalse();
    }



    // <editor-fold desc="compose (functional)">

    @Test
    public void testBiObjCharPredComposeChar() throws Throwable {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LBiObjCharPredicate<Integer,Integer> sutO = (a1,a2,a3) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(90);
                assertThat(a2).isEqualTo(91);
                assertThat(a3).isEqualTo('\u0092');
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
        LCharUnaryOperator before3 = p2 -> {
            assertThat(p2).isEqualTo('\u0082');
            beforeCalls.incrementAndGet();
            return '\u0092';
        };

        //when
        LBiObjCharPredicate<Integer,Integer> function = sutO.biObjCharPredComposeChar(before1,before2,before3);
        function.doTest(80,81,'\u0082');

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(beforeCalls.get()).isEqualTo(3);
    }


    @Test
    public void testBiObjCharPredCompose() throws Throwable {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LBiObjCharPredicate<Integer,Integer> sutO = (a1,a2,a3) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(90);
                assertThat(a2).isEqualTo(91);
                assertThat(a3).isEqualTo('\u0092');
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
        LToCharFunction<Integer> before3 = p2 -> {
            assertThat(p2).isEqualTo(82);
            beforeCalls.incrementAndGet();
            return '\u0092';
        };

        //when
        LTriPredicate<Integer,Integer,Integer> function = sutO.biObjCharPredCompose(before1,before2,before3);
        function.doTest(80,81,82);

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(beforeCalls.get()).isEqualTo(3);
    }

    // </editor-fold>



    // <editor-fold desc="then (functional)">

    @Test
    public void testBoolToBiObjCharFunc0() throws Throwable  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);

        //given (+ some assertions)
        LBiObjCharPredicate<Integer,Integer> sutO = (a1,a2,a3) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(80);
                assertThat(a2).isEqualTo(81);
                assertThat(a3).isEqualTo('\u0082');
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
        LBiObjCharFunction<Integer,Integer,Integer> function = sutO.boolToBiObjCharFunc(thenFunction);
        Integer finalValue = function.doApply(80,81,'\u0082');

        //then - finals
        assertThat(finalValue).isEqualTo(100);
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    @Test
    public void testBoolToBiObjCharPred1() throws Throwable  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);

        //given (+ some assertions)
        LBiObjCharPredicate<Integer,Integer> sutO = (a1,a2,a3) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(80);
                assertThat(a2).isEqualTo(81);
                assertThat(a3).isEqualTo('\u0082');
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
        LBiObjCharPredicate<Integer,Integer> function = sutO.boolToBiObjCharPred(thenFunction);
        boolean finalValue = function.doTest(80,81,'\u0082');

        //then - finals
        assertThat(finalValue).isEqualTo(true);
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


    @Test(expectedExceptions = RuntimeException.class)
    public void testShove() {

        // given
        LBiObjCharPredicate<Integer,Integer> sutThrowing = LBiObjCharPredicate.biObjCharPred((a1,a2,a3) -> {
            throw new UnsupportedOperationException();
        });

        // when
        sutThrowing.shovingBiObjCharPred().doTest(100,100,'\u0100');
    }


    @Test
    public void testToString() throws Throwable {

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

    private boolean variantLObjCharObj1Pred(Integer a1,char a3,Integer a2) {
        return true;
    }

    @Test
    public void compilerSubstituteVariantLObjCharObj1Pred() {
        LBiObjCharPredicate lambda = LBiObjCharPredicate./*<T1,T2>*/objCharObj1Pred(this::variantLObjCharObj1Pred);

        assertThat(lambda).isInstanceOf(LBiObjCharPredicate.LObjCharObj1Pred.class);
    }


    private boolean variantLObj1Obj0CharPred(Integer a2,Integer a1,char a3) {
        return true;
    }

    @Test
    public void compilerSubstituteVariantLObj1Obj0CharPred() {
        LBiObjCharPredicate lambda = LBiObjCharPredicate./*<T1,T2>*/obj1Obj0CharPred(this::variantLObj1Obj0CharPred);

        assertThat(lambda).isInstanceOf(LBiObjCharPredicate.LObj1Obj0CharPred.class);
    }


    private boolean variantLObj1CharObj0Pred(Integer a2,char a3,Integer a1) {
        return true;
    }

    @Test
    public void compilerSubstituteVariantLObj1CharObj0Pred() {
        LBiObjCharPredicate lambda = LBiObjCharPredicate./*<T1,T2>*/obj1CharObj0Pred(this::variantLObj1CharObj0Pred);

        assertThat(lambda).isInstanceOf(LBiObjCharPredicate.LObj1CharObj0Pred.class);
    }


    private boolean variantLCharObj0Obj1Pred(char a3,Integer a1,Integer a2) {
        return true;
    }

    @Test
    public void compilerSubstituteVariantLCharObj0Obj1Pred() {
        LBiObjCharPredicate lambda = LBiObjCharPredicate./*<T1,T2>*/charObj0Obj1Pred(this::variantLCharObj0Obj1Pred);

        assertThat(lambda).isInstanceOf(LBiObjCharPredicate.LCharObj0Obj1Pred.class);
    }


    private boolean variantLCharObjObj0Pred(char a3,Integer a2,Integer a1) {
        return true;
    }

    @Test
    public void compilerSubstituteVariantLCharObjObj0Pred() {
        LBiObjCharPredicate lambda = LBiObjCharPredicate./*<T1,T2>*/charObjObj0Pred(this::variantLCharObjObj0Pred);

        assertThat(lambda).isInstanceOf(LBiObjCharPredicate.LCharObjObj0Pred.class);
    }

    //</editor-fold>


    @Test void safeCompiles() {
        LBiObjCharPredicate r1 = LBiObjCharPredicate.safe(sut); //NOSONAR
    }

    @Test void safePropagates() {
        Object result = LBiObjCharPredicate.safe(sut);
        assertThat(result).isSameAs(sut);
    }

    @Test void safeProtectsAgainstNpe() {
        Object result = LBiObjCharPredicate.safe(null);
        assertThat(result).isSameAs(LBiObjCharPredicate.biObjCharPred(LBiObjCharPredicate.safe()));
    }

    @Test  void safeSupplierPropagates() {
        LSupplier<LBiObjCharPredicate<Integer,Integer>> supplier = ()->sut;
        Object result = LBiObjCharPredicate.safeSupplier(supplier);
        assertThat(result).isSameAs(supplier);
    }

    @Test  void safeSupplierProtectsAgainstNpe() {
        Object result = LBiObjCharPredicate.safeSupplier(null);
        assertThat(result).isSameAs(LBiObjCharPredicate.safeSupplier());
    }

    @Test  void safeSupplierCompiles() {
        LSupplier<LBiObjCharPredicate<Integer,Integer>> r1 = LBiObjCharPredicate.safeSupplier(()->sut);  //NOSONAR
    }

}
