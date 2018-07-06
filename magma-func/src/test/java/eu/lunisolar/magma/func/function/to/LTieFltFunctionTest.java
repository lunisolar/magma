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

package eu.lunisolar.magma.func.function.to;

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
public class LTieFltFunctionTest<T> {
    private static final String ORIGINAL_MESSAGE = "Original message";
    private static final String EXCEPTION_WAS_WRAPPED = "Exception was wrapped.";
    private static final String NO_EXCEPTION_WERE_THROWN = "No exception were thrown.";

    private int testValue = 100;



    private LTieFltFunction<Integer> sut = new LTieFltFunction<Integer>(){
        public  int doApplyAsIntX(Integer a1,int a2,float a3)  {
            return testValue;
        }
    };




    private LTieFltFunction<Integer> sutAlwaysThrowing = LTieFltFunction.tieFltFunc((a1,a2,a3) -> {
            throw new ParseException(ORIGINAL_MESSAGE, 0);
    });

    private LTieFltFunction<Integer> sutAlwaysThrowingUnchecked = LTieFltFunction.tieFltFunc((a1,a2,a3) -> {
            throw new IndexOutOfBoundsException(ORIGINAL_MESSAGE);
    });


    @Test
    public void testTheResult() throws Throwable {
        assertThat(sut.doApplyAsInt(100,100,100f))
            .isEqualTo(testValue);
    }

    @Test
    public void testTupleCall() throws Throwable {

        LObjIntFltTriple<Integer> domainObject = Tuple4U.objIntFltTriple(100,100,100f);

        Object result = sut.tupleApplyAsInt(domainObject);

        assertThat(result)
            .isEqualTo(testValue);
    }

    @Test
    public void testNonNullDoApplyAsInt() throws Throwable {
        assertThat(sut.nonNullDoApplyAsInt(100,100,100f))
            .isEqualTo(testValue);
    }

    @Test
    public void testNestingDoApplyAsIntUnchecked() throws Throwable {

        // then
        try {
            sutAlwaysThrowingUnchecked.nestingDoApplyAsInt(100,100,100f);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                    .hasNoCause()
                    .hasMessage(ORIGINAL_MESSAGE);
        }
    }

    @Test
    public void testShovingDoApplyAsIntUnchecked() throws Throwable {

        // then
        try {
            sutAlwaysThrowingUnchecked.shovingDoApplyAsInt(100,100,100f);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                    .hasNoCause()
                    .hasMessage(ORIGINAL_MESSAGE);
        }
    }


    @Test
    public void testFunctionalInterfaceDescription() throws Throwable {
        assertThat(sut.functionalInterfaceDescription())
            .isEqualTo("LTieFltFunction: int doApplyAsInt(T a1,int a2,float a3)");
    }

    @Test
    public void testTieFltFuncMethod() throws Throwable {
        assertThat(LTieFltFunction.tieFltFunc((a1,a2,a3) -> testValue ))
            .isInstanceOf(LTieFltFunction.class);
    }






    // <editor-fold desc="compose (functional)">

    @Test
    public void testTieFltFuncComposeIntFlt() throws Throwable {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LTieFltFunction<Integer> sutO = (a1,a2,a3) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(90);
                assertThat(a2).isEqualTo(91);
                assertThat(a3).isEqualTo(92f);
                return 100;
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
        LFltUnaryOperator before3 = p2 -> {
            assertThat(p2).isEqualTo(82f);
            beforeCalls.incrementAndGet();
            return 92f;
        };

        //when
        LTieFltFunction<Integer> function = sutO.tieFltFuncComposeIntFlt(before1,before2,before3);
        function.doApplyAsInt(80,81,82f);

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(beforeCalls.get()).isEqualTo(3);
    }


    @Test
    public void testTieFltFuncCompose() throws Throwable {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LTieFltFunction<Integer> sutO = (a1,a2,a3) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(90);
                assertThat(a2).isEqualTo(91);
                assertThat(a3).isEqualTo(92f);
                return 100;
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
        LToFltFunction<Integer> before3 = p2 -> {
            assertThat(p2).isEqualTo(82);
            beforeCalls.incrementAndGet();
            return 92f;
        };

        //when
        LToIntTriFunction<Integer,Integer,Integer> function = sutO.tieFltFuncCompose(before1,before2,before3);
        function.doApplyAsInt(80,81,82);

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(beforeCalls.get()).isEqualTo(3);
    }

    // </editor-fold>



    // <editor-fold desc="then (functional)">

    @Test
    public void testThen0() throws Throwable  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);

        //given (+ some assertions)
        LTieFltFunction<Integer> sutO = (a1,a2,a3) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(80);
                assertThat(a2).isEqualTo(81);
                assertThat(a3).isEqualTo(82f);
                return 90;
        };

        LIntFunction<Integer> thenFunction = p -> {
                thenFunctionCalled.set(true);
                // int
                assertThat(p).isEqualTo(90);
                // Integer
                return 100;
        };

        //when
        LObjIntFltFunction<Integer,Integer> function = sutO.then(thenFunction);
        Integer finalValue = function.doApply(80,81,82f);

        //then - finals
        assertThat(finalValue).isEqualTo(100);
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    @Test
    public void testThenToInt1() throws Throwable  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);

        //given (+ some assertions)
        LTieFltFunction<Integer> sutO = (a1,a2,a3) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(80);
                assertThat(a2).isEqualTo(81);
                assertThat(a3).isEqualTo(82f);
                return 90;
        };

        LIntUnaryOperator thenFunction = p -> {
                thenFunctionCalled.set(true);
                // int
                assertThat(p).isEqualTo(90);
                // int
                return 100;
        };

        //when
        LTieFltFunction<Integer> function = sutO.thenToInt(thenFunction);
        int finalValue = function.doApplyAsInt(80,81,82f);

        //then - finals
        assertThat(finalValue).isEqualTo(100);
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    @Test
    public void testThenToBool2() throws Throwable  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);

        //given (+ some assertions)
        LTieFltFunction<Integer> sutO = (a1,a2,a3) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(80);
                assertThat(a2).isEqualTo(81);
                assertThat(a3).isEqualTo(82f);
                return 90;
        };

        LIntPredicate thenFunction = p -> {
                thenFunctionCalled.set(true);
                // int
                assertThat(p).isEqualTo(90);
                // boolean
                return true;
        };

        //when
        LObjIntFltPredicate<Integer> function = sutO.thenToBool(thenFunction);
        boolean finalValue = function.doTest(80,81,82f);

        //then - finals
        assertThat(finalValue).isEqualTo(true);
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    // </editor-fold>

    @Test
    public void testNesting() {
        assertThat(sut.nestingTieFltFunc())
            .isSameAs(sut)
            .isInstanceOf(LTieFltFunction.class);
    }

    @Test
    public void testShoving() {
        assertThat(sut.shovingTieFltFunc())
            .isSameAs(sut)
            .isInstanceOf(LTieFltFunction.class);
    }


    @Test(expectedExceptions = RuntimeException.class)
    public void testShove() {

        // given
        LTieFltFunction<Integer> sutThrowing = LTieFltFunction.tieFltFunc((a1,a2,a3) -> {
            throw new UnsupportedOperationException();
        });

        // when
        sutThrowing.shovingTieFltFunc().doApplyAsInt(100,100,100f);
    }


    @Test
    public void testToString() throws Throwable {

        assertThat(sut.toString())
                .isInstanceOf(String.class)
                .startsWith(this.getClass().getName()+"$");

        assertThat(String.format("%s", sut))
                .isInstanceOf(String.class)
                .contains("LTieFltFunction: int doApplyAsInt(T a1,int a2,float a3)");
    }


    @Test
    public void isThrowing() {
        assertThat(sut.isThrowing())
            .isFalse();
    }

    //<editor-fold desc="Variants">

    private int variantLObjFltIntToIntFunc(Integer a1,float a3,int a2) {
        return 100;
    }

    @Test
    public void compilerSubstituteVariantLObjFltIntToIntFunc() {
        LTieFltFunction lambda = LTieFltFunction./*<T>*/objFltIntToIntFunc(this::variantLObjFltIntToIntFunc);

        assertThat(lambda).isInstanceOf(LTieFltFunction.LObjFltIntToIntFunc.class);
    }


    private int variantLIntObjFltToIntFunc(int a2,Integer a1,float a3) {
        return 100;
    }

    @Test
    public void compilerSubstituteVariantLIntObjFltToIntFunc() {
        LTieFltFunction lambda = LTieFltFunction./*<T>*/intObjFltToIntFunc(this::variantLIntObjFltToIntFunc);

        assertThat(lambda).isInstanceOf(LTieFltFunction.LIntObjFltToIntFunc.class);
    }


    private int variantLIntFltObjToIntFunc(int a2,float a3,Integer a1) {
        return 100;
    }

    @Test
    public void compilerSubstituteVariantLIntFltObjToIntFunc() {
        LTieFltFunction lambda = LTieFltFunction./*<T>*/intFltObjToIntFunc(this::variantLIntFltObjToIntFunc);

        assertThat(lambda).isInstanceOf(LTieFltFunction.LIntFltObjToIntFunc.class);
    }


    private int variantLFltObjIntToIntFunc(float a3,Integer a1,int a2) {
        return 100;
    }

    @Test
    public void compilerSubstituteVariantLFltObjIntToIntFunc() {
        LTieFltFunction lambda = LTieFltFunction./*<T>*/fltObjIntToIntFunc(this::variantLFltObjIntToIntFunc);

        assertThat(lambda).isInstanceOf(LTieFltFunction.LFltObjIntToIntFunc.class);
    }


    private int variantLFltIntObjToIntFunc(float a3,int a2,Integer a1) {
        return 100;
    }

    @Test
    public void compilerSubstituteVariantLFltIntObjToIntFunc() {
        LTieFltFunction lambda = LTieFltFunction./*<T>*/fltIntObjToIntFunc(this::variantLFltIntObjToIntFunc);

        assertThat(lambda).isInstanceOf(LTieFltFunction.LFltIntObjToIntFunc.class);
    }

    //</editor-fold>


    @Test void safeCompiles() {
        LTieFltFunction r1 = LTieFltFunction.safe(sut); //NOSONAR
    }

    @Test void safePropagates() {
        Object result = LTieFltFunction.safe(sut);
        assertThat(result).isSameAs(sut);
    }

    @Test void safeProtectsAgainstNpe() {
        Object result = LTieFltFunction.safe(null);
        assertThat(result).isSameAs(LTieFltFunction.tieFltFunc(LTieFltFunction.safe()));
    }

    @Test  void safeSupplierPropagates() {
        LSupplier<LTieFltFunction<Integer>> supplier = ()->sut;
        Object result = LTieFltFunction.safeSupplier(supplier);
        assertThat(result).isSameAs(supplier);
    }

    @Test  void safeSupplierProtectsAgainstNpe() {
        Object result = LTieFltFunction.safeSupplier(null);
        assertThat(result).isSameAs(LTieFltFunction.safeSupplier());
    }

    @Test  void safeSupplierCompiles() {
        LSupplier<LTieFltFunction<Integer>> r1 = LTieFltFunction.safeSupplier(()->sut);  //NOSONAR
    }

}
