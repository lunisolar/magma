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
public class LTieBoolFunctionTest<T> {
    private static final String ORIGINAL_MESSAGE = "Original message";
    private static final String EXCEPTION_WAS_WRAPPED = "Exception was wrapped.";
    private static final String NO_EXCEPTION_WERE_THROWN = "No exception were thrown.";

    private int testValue = 100;



    private LTieBoolFunction<Integer> sut = new LTieBoolFunction<Integer>(){
        public  int doApplyAsIntX(Integer a1,int a2,boolean a3)  {
            return testValue;
        }
    };




    private LTieBoolFunction<Integer> sutAlwaysThrowing = LTieBoolFunction.tieBoolFunc((a1,a2,a3) -> {
            throw new ParseException(ORIGINAL_MESSAGE, 0);
    });

    private LTieBoolFunction<Integer> sutAlwaysThrowingUnchecked = LTieBoolFunction.tieBoolFunc((a1,a2,a3) -> {
            throw new IndexOutOfBoundsException(ORIGINAL_MESSAGE);
    });


    @Test
    public void testTheResult() throws Throwable {
        assertThat(sut.doApplyAsInt(100,100,true))
            .isEqualTo(testValue);
    }

    @Test
    public void testTupleCall() throws Throwable {

        LObjIntBoolTriple<Integer> domainObject = Tuple4U.objIntBoolTriple(100,100,true);

        Object result = sut.tupleApplyAsInt(domainObject);

        assertThat(result)
            .isEqualTo(testValue);
    }

    @Test
    public void testNonNullDoApplyAsInt() throws Throwable {
        assertThat(sut.nonNullDoApplyAsInt(100,100,true))
            .isEqualTo(testValue);
    }

    @Test
    public void testNestingDoApplyAsIntUnchecked() throws Throwable {

        // then
        try {
            sutAlwaysThrowingUnchecked.nestingDoApplyAsInt(100,100,true);
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
            sutAlwaysThrowingUnchecked.shovingDoApplyAsInt(100,100,true);
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
            .isEqualTo("LTieBoolFunction: int doApplyAsInt(T a1,int a2,boolean a3)");
    }

    @Test
    public void testTieBoolFuncMethod() throws Throwable {
        assertThat(LTieBoolFunction.tieBoolFunc((a1,a2,a3) -> testValue ))
            .isInstanceOf(LTieBoolFunction.class);
    }






    // <editor-fold desc="compose (functional)">

    @Test
    public void testTieBoolFuncComposeIntBool() throws Throwable {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LTieBoolFunction<Integer> sutO = (a1,a2,a3) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(90);
                assertThat(a2).isEqualTo(91);
                assertThat(a3).isEqualTo(true);
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
        LLogicalOperator before3 = p2 -> {
            assertThat(p2).isEqualTo(true);
            beforeCalls.incrementAndGet();
            return true;
        };

        //when
        LTieBoolFunction<Integer> function = sutO.tieBoolFuncComposeIntBool(before1,before2,before3);
        function.doApplyAsInt(80,81,true);

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(beforeCalls.get()).isEqualTo(3);
    }


    @Test
    public void testTieBoolFuncCompose() throws Throwable {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LTieBoolFunction<Integer> sutO = (a1,a2,a3) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(90);
                assertThat(a2).isEqualTo(91);
                assertThat(a3).isEqualTo(true);
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
        LPredicate<Integer> before3 = p2 -> {
            assertThat(p2).isEqualTo(82);
            beforeCalls.incrementAndGet();
            return true;
        };

        //when
        LToIntTriFunction<Integer,Integer,Integer> function = sutO.tieBoolFuncCompose(before1,before2,before3);
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
        LTieBoolFunction<Integer> sutO = (a1,a2,a3) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(80);
                assertThat(a2).isEqualTo(81);
                assertThat(a3).isEqualTo(true);
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
        LObjIntBoolFunction<Integer,Integer> function = sutO.then(thenFunction);
        Integer finalValue = function.doApply(80,81,true);

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
        LTieBoolFunction<Integer> sutO = (a1,a2,a3) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(80);
                assertThat(a2).isEqualTo(81);
                assertThat(a3).isEqualTo(true);
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
        LTieBoolFunction<Integer> function = sutO.thenToInt(thenFunction);
        int finalValue = function.doApplyAsInt(80,81,true);

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
        LTieBoolFunction<Integer> sutO = (a1,a2,a3) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(80);
                assertThat(a2).isEqualTo(81);
                assertThat(a3).isEqualTo(true);
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
        LObjIntBoolPredicate<Integer> function = sutO.thenToBool(thenFunction);
        boolean finalValue = function.doTest(80,81,true);

        //then - finals
        assertThat(finalValue).isEqualTo(true);
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    // </editor-fold>

    @Test
    public void testNesting() {
        assertThat(sut.nestingTieBoolFunc())
            .isSameAs(sut)
            .isInstanceOf(LTieBoolFunction.class);
    }

    @Test
    public void testShoving() {
        assertThat(sut.shovingTieBoolFunc())
            .isSameAs(sut)
            .isInstanceOf(LTieBoolFunction.class);
    }


    @Test(expectedExceptions = RuntimeException.class)
    public void testShove() {

        // given
        LTieBoolFunction<Integer> sutThrowing = LTieBoolFunction.tieBoolFunc((a1,a2,a3) -> {
            throw new UnsupportedOperationException();
        });

        // when
        sutThrowing.shovingTieBoolFunc().doApplyAsInt(100,100,true);
    }


    @Test
    public void testToString() throws Throwable {

        assertThat(sut.toString())
                .isInstanceOf(String.class)
                .startsWith(this.getClass().getName()+"$");

        assertThat(String.format("%s", sut))
                .isInstanceOf(String.class)
                .contains("LTieBoolFunction: int doApplyAsInt(T a1,int a2,boolean a3)");
    }


    @Test
    public void isThrowing() {
        assertThat(sut.isThrowing())
            .isFalse();
    }

    //<editor-fold desc="Variants">

    private int variantLObjBoolIntToIntFunc(Integer a1,boolean a3,int a2) {
        return 100;
    }

    @Test
    public void compilerSubstituteVariantLObjBoolIntToIntFunc() {
        LTieBoolFunction lambda = LTieBoolFunction./*<T>*/objBoolIntToIntFunc(this::variantLObjBoolIntToIntFunc);

        assertThat(lambda).isInstanceOf(LTieBoolFunction.LObjBoolIntToIntFunc.class);
    }


    private int variantLIntObjBoolToIntFunc(int a2,Integer a1,boolean a3) {
        return 100;
    }

    @Test
    public void compilerSubstituteVariantLIntObjBoolToIntFunc() {
        LTieBoolFunction lambda = LTieBoolFunction./*<T>*/intObjBoolToIntFunc(this::variantLIntObjBoolToIntFunc);

        assertThat(lambda).isInstanceOf(LTieBoolFunction.LIntObjBoolToIntFunc.class);
    }


    private int variantLIntBoolObjToIntFunc(int a2,boolean a3,Integer a1) {
        return 100;
    }

    @Test
    public void compilerSubstituteVariantLIntBoolObjToIntFunc() {
        LTieBoolFunction lambda = LTieBoolFunction./*<T>*/intBoolObjToIntFunc(this::variantLIntBoolObjToIntFunc);

        assertThat(lambda).isInstanceOf(LTieBoolFunction.LIntBoolObjToIntFunc.class);
    }


    private int variantLBoolObjIntToIntFunc(boolean a3,Integer a1,int a2) {
        return 100;
    }

    @Test
    public void compilerSubstituteVariantLBoolObjIntToIntFunc() {
        LTieBoolFunction lambda = LTieBoolFunction./*<T>*/boolObjIntToIntFunc(this::variantLBoolObjIntToIntFunc);

        assertThat(lambda).isInstanceOf(LTieBoolFunction.LBoolObjIntToIntFunc.class);
    }


    private int variantLBoolIntObjToIntFunc(boolean a3,int a2,Integer a1) {
        return 100;
    }

    @Test
    public void compilerSubstituteVariantLBoolIntObjToIntFunc() {
        LTieBoolFunction lambda = LTieBoolFunction./*<T>*/boolIntObjToIntFunc(this::variantLBoolIntObjToIntFunc);

        assertThat(lambda).isInstanceOf(LTieBoolFunction.LBoolIntObjToIntFunc.class);
    }

    //</editor-fold>


    @Test void safeCompiles() {
        LTieBoolFunction r1 = LTieBoolFunction.safe(sut); //NOSONAR
    }

    @Test void safePropagates() {
        Object result = LTieBoolFunction.safe(sut);
        assertThat(result).isSameAs(sut);
    }

    @Test void safeProtectsAgainstNpe() {
        Object result = LTieBoolFunction.safe(null);
        assertThat(result).isSameAs(LTieBoolFunction.tieBoolFunc(LTieBoolFunction.safe()));
    }

    @Test  void safeSupplierPropagates() {
        LSupplier<LTieBoolFunction<Integer>> supplier = ()->sut;
        Object result = LTieBoolFunction.safeSupplier(supplier);
        assertThat(result).isSameAs(supplier);
    }

    @Test  void safeSupplierProtectsAgainstNpe() {
        Object result = LTieBoolFunction.safeSupplier(null);
        assertThat(result).isSameAs(LTieBoolFunction.safeSupplier());
    }

    @Test  void safeSupplierCompiles() {
        LSupplier<LTieBoolFunction<Integer>> r1 = LTieBoolFunction.safeSupplier(()->sut);  //NOSONAR
    }

}
