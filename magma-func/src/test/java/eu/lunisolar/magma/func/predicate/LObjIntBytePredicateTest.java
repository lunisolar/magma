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
public class LObjIntBytePredicateTest<T> {
    private static final String ORIGINAL_MESSAGE = "Original message";
    private static final String EXCEPTION_WAS_WRAPPED = "Exception was wrapped.";
    private static final String NO_EXCEPTION_WERE_THROWN = "No exception were thrown.";

    private boolean testValue = true;



    private LObjIntBytePredicate<Integer> sut = new LObjIntBytePredicate<Integer>(){
        public  boolean doTestX(Integer a1,int a2,byte a3)  {
            return testValue;
        }
    };




    private LObjIntBytePredicate<Integer> sutAlwaysThrowing = LObjIntBytePredicate.objIntBytePred((a1,a2,a3) -> {
            throw new ParseException(ORIGINAL_MESSAGE, 0);
    });

    private LObjIntBytePredicate<Integer> sutAlwaysThrowingUnchecked = LObjIntBytePredicate.objIntBytePred((a1,a2,a3) -> {
            throw new IndexOutOfBoundsException(ORIGINAL_MESSAGE);
    });


    @Test
    public void testTheResult() throws Throwable {
        assertThat(sut.doTest(100,100,(byte)100))
            .isEqualTo(testValue);
    }

    @Test
    public void testTupleCall() throws Throwable {

        LObjIntByteTriple<Integer> domainObject = Tuple4U.objIntByteTriple(100,100,(byte)100);

        Object result = sut.tupleTest(domainObject);

        assertThat(result)
            .isEqualTo(testValue);
    }

    @Test
    public void testNonNullDoTest() throws Throwable {
        assertThat(sut.nonNullDoTest(100,100,(byte)100))
            .isEqualTo(testValue);
    }

    @Test
    public void testNestingDoTestUnchecked() throws Throwable {

        // then
        try {
            sutAlwaysThrowingUnchecked.nestingDoTest(100,100,(byte)100);
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
            sutAlwaysThrowingUnchecked.shovingDoTest(100,100,(byte)100);
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
        assertThat(sut.doApplyAsBoolean(100,100,(byte)100))
            .isEqualTo(testValue);

    }


    @Test
    public void testFunctionalInterfaceDescription() throws Throwable {
        assertThat(sut.functionalInterfaceDescription())
            .isEqualTo("LObjIntBytePredicate: boolean doTest(T a1,int a2,byte a3)");
    }

    @Test
    public void testObjIntBytePredMethod() throws Throwable {
        assertThat(LObjIntBytePredicate.objIntBytePred((a1,a2,a3) -> testValue ))
            .isInstanceOf(LObjIntBytePredicate.class);
    }




    @Test
    public void testnegate() throws Throwable {
        assertThat(sut.negate().doTest(100,100,(byte)100))
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
        LObjIntBytePredicate<Integer> fun1 = LObjIntBytePredicate.objIntBytePred((a1,a2,a3) -> f1Result);
        LObjIntBytePredicate<Integer> fun2 = LObjIntBytePredicate.objIntBytePred((a1,a2,a3) -> f2Result);

        //when
        LObjIntBytePredicate<Integer> andFunction = fun1.and(fun2);
        LObjIntBytePredicate<Integer> orFunction = fun1.or(fun2);
        LObjIntBytePredicate<Integer> xorFunction = fun1.xor(fun2);

        //then
        assertThat(andFunction.doTest(100,100,(byte)100))
                .isEqualTo(andResult);

        assertThat(orFunction.doTest(100,100,(byte)100))
                .isEqualTo(orResult);

        assertThat(xorFunction.doTest(100,100,(byte)100))
                .isEqualTo(xorResult);
    }

    @Test
    public void testIsEqual() throws Throwable  {
        //when
        LObjIntBytePredicate<Integer> equals = LObjIntBytePredicate.isEqual(1,1,(byte)1);

        //then
        assertThat(equals.doTest(1,1,(byte)1))
                .isTrue();

        assertThat(equals.doTest(0,0,(byte)0))
                .isFalse();
    }



    // <editor-fold desc="compose (functional)">

    @Test
    public void testObjIntBytePredComposeIntByte() throws Throwable {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LObjIntBytePredicate<Integer> sutO = (a1,a2,a3) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(90);
                assertThat(a2).isEqualTo(91);
                assertThat(a3).isEqualTo((byte)92);
                return true;
        };

        LFunction<Integer,Integer> before1 = p0 -> {
            assertThat(p0).isEqualTo(80);
            beforeCalls.incrementAndGet();
            return 90;
        };
        LIntUnaryOperator before2 = p1 -> {
            assertThat(p1).isEqualTo(81);
            beforeCalls.incrementAndGet();
            return 91;
        };
        LByteUnaryOperator before3 = p2 -> {
            assertThat(p2).isEqualTo((byte)82);
            beforeCalls.incrementAndGet();
            return (byte)92;
        };

        //when
        LObjIntBytePredicate<Integer> function = sutO.objIntBytePredComposeIntByte(before1,before2,before3);
        function.doTest(80,81,(byte)82);

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(beforeCalls.get()).isEqualTo(3);
    }


    @Test
    public void testObjIntBytePredCompose() throws Throwable {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LObjIntBytePredicate<Integer> sutO = (a1,a2,a3) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(90);
                assertThat(a2).isEqualTo(91);
                assertThat(a3).isEqualTo((byte)92);
                return true;
        };

        LFunction<Integer,Integer> before1 = p0 -> {
            assertThat(p0).isEqualTo(80);
            beforeCalls.incrementAndGet();
            return 90;
        };
        LToIntFunction<Integer> before2 = p1 -> {
            assertThat(p1).isEqualTo(81);
            beforeCalls.incrementAndGet();
            return 91;
        };
        LToByteFunction<Integer> before3 = p2 -> {
            assertThat(p2).isEqualTo(82);
            beforeCalls.incrementAndGet();
            return (byte)92;
        };

        //when
        LTriPredicate<Integer,Integer,Integer> function = sutO.objIntBytePredCompose(before1,before2,before3);
        function.doTest(80,81,82);

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(beforeCalls.get()).isEqualTo(3);
    }

    // </editor-fold>



    // <editor-fold desc="then (functional)">

    @Test
    public void testBoolToObjIntByteFunc0() throws Throwable  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);

        //given (+ some assertions)
        LObjIntBytePredicate<Integer> sutO = (a1,a2,a3) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(80);
                assertThat(a2).isEqualTo(81);
                assertThat(a3).isEqualTo((byte)82);
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
        LObjIntByteFunction<Integer,Integer> function = sutO.boolToObjIntByteFunc(thenFunction);
        Integer finalValue = function.doApply(80,81,(byte)82);

        //then - finals
        assertThat(finalValue).isEqualTo(100);
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    @Test
    public void testBoolToTieByteFunc1() throws Throwable  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);

        //given (+ some assertions)
        LObjIntBytePredicate<Integer> sutO = (a1,a2,a3) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(80);
                assertThat(a2).isEqualTo(81);
                assertThat(a3).isEqualTo((byte)82);
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
        LTieByteFunction<Integer> function = sutO.boolToTieByteFunc(thenFunction);
        int finalValue = function.doApplyAsInt(80,81,(byte)82);

        //then - finals
        assertThat(finalValue).isEqualTo(100);
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    @Test
    public void testBoolToObjIntBytePred2() throws Throwable  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);

        //given (+ some assertions)
        LObjIntBytePredicate<Integer> sutO = (a1,a2,a3) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(80);
                assertThat(a2).isEqualTo(81);
                assertThat(a3).isEqualTo((byte)82);
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
        LObjIntBytePredicate<Integer> function = sutO.boolToObjIntBytePred(thenFunction);
        boolean finalValue = function.doTest(80,81,(byte)82);

        //then - finals
        assertThat(finalValue).isEqualTo(true);
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    // </editor-fold>

    @Test
    public void testNesting() {
        assertThat(sut.nestingObjIntBytePred())
            .isSameAs(sut)
            .isInstanceOf(LObjIntBytePredicate.class);
    }

    @Test
    public void testShoving() {
        assertThat(sut.shovingObjIntBytePred())
            .isSameAs(sut)
            .isInstanceOf(LObjIntBytePredicate.class);
    }


    @Test(expectedExceptions = RuntimeException.class)
    public void testShove() {

        // given
        LObjIntBytePredicate<Integer> sutThrowing = LObjIntBytePredicate.objIntBytePred((a1,a2,a3) -> {
            throw new UnsupportedOperationException();
        });

        // when
        sutThrowing.shovingObjIntBytePred().doTest(100,100,(byte)100);
    }


    @Test
    public void testToString() throws Throwable {

        assertThat(sut.toString())
                .isInstanceOf(String.class)
                .startsWith(this.getClass().getName()+"$");

        assertThat(String.format("%s", sut))
                .isInstanceOf(String.class)
                .contains("LObjIntBytePredicate: boolean doTest(T a1,int a2,byte a3)");
    }


    @Test
    public void isThrowing() {
        assertThat(sut.isThrowing())
            .isFalse();
    }

    //<editor-fold desc="Variants">

    private boolean variantLObjByteIntPred(Integer a1,byte a3,int a2) {
        return true;
    }

    @Test
    public void compilerSubstituteVariantLObjByteIntPred() {
        LObjIntBytePredicate lambda = LObjIntBytePredicate./*<T>*/objByteIntPred(this::variantLObjByteIntPred);

        assertThat(lambda).isInstanceOf(LObjIntBytePredicate.LObjByteIntPred.class);
    }


    private boolean variantLIntObjBytePred(int a2,Integer a1,byte a3) {
        return true;
    }

    @Test
    public void compilerSubstituteVariantLIntObjBytePred() {
        LObjIntBytePredicate lambda = LObjIntBytePredicate./*<T>*/intObjBytePred(this::variantLIntObjBytePred);

        assertThat(lambda).isInstanceOf(LObjIntBytePredicate.LIntObjBytePred.class);
    }


    private boolean variantLIntByteObjPred(int a2,byte a3,Integer a1) {
        return true;
    }

    @Test
    public void compilerSubstituteVariantLIntByteObjPred() {
        LObjIntBytePredicate lambda = LObjIntBytePredicate./*<T>*/intByteObjPred(this::variantLIntByteObjPred);

        assertThat(lambda).isInstanceOf(LObjIntBytePredicate.LIntByteObjPred.class);
    }


    private boolean variantLByteObjIntPred(byte a3,Integer a1,int a2) {
        return true;
    }

    @Test
    public void compilerSubstituteVariantLByteObjIntPred() {
        LObjIntBytePredicate lambda = LObjIntBytePredicate./*<T>*/byteObjIntPred(this::variantLByteObjIntPred);

        assertThat(lambda).isInstanceOf(LObjIntBytePredicate.LByteObjIntPred.class);
    }


    private boolean variantLByteIntObjPred(byte a3,int a2,Integer a1) {
        return true;
    }

    @Test
    public void compilerSubstituteVariantLByteIntObjPred() {
        LObjIntBytePredicate lambda = LObjIntBytePredicate./*<T>*/byteIntObjPred(this::variantLByteIntObjPred);

        assertThat(lambda).isInstanceOf(LObjIntBytePredicate.LByteIntObjPred.class);
    }

    //</editor-fold>


    @Test void safeCompiles() {
        LObjIntBytePredicate r1 = LObjIntBytePredicate.safe(sut); //NOSONAR
    }

    @Test void safePropagates() {
        Object result = LObjIntBytePredicate.safe(sut);
        assertThat(result).isSameAs(sut);
    }

    @Test void safeProtectsAgainstNpe() {
        Object result = LObjIntBytePredicate.safe(null);
        assertThat(result).isSameAs(LObjIntBytePredicate.objIntBytePred(LObjIntBytePredicate.safe()));
    }

    @Test  void safeSupplierPropagates() {
        LSupplier<LObjIntBytePredicate<Integer>> supplier = ()->sut;
        Object result = LObjIntBytePredicate.safeSupplier(supplier);
        assertThat(result).isSameAs(supplier);
    }

    @Test  void safeSupplierProtectsAgainstNpe() {
        Object result = LObjIntBytePredicate.safeSupplier(null);
        assertThat(result).isSameAs(LObjIntBytePredicate.safeSupplier());
    }

    @Test  void safeSupplierCompiles() {
        LSupplier<LObjIntBytePredicate<Integer>> r1 = LObjIntBytePredicate.safeSupplier(()->sut);  //NOSONAR
    }

}
