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
public class LTieIntFunctionTest<T> {
    private static final String ORIGINAL_MESSAGE = "Original message";
    private static final String EXCEPTION_WAS_WRAPPED = "Exception was wrapped.";
    private static final String NO_EXCEPTION_WERE_THROWN = "No exception were thrown.";

    private int testValue = 100;



    private LTieIntFunction<Integer> sut = new LTieIntFunction<Integer>(){
        public  int doApplyAsIntX(Integer a1,int a2,int a3)  {
            return testValue;
        }
    };




    private LTieIntFunction<Integer> sutAlwaysThrowing = LTieIntFunction.tieIntFunc((a1,a2,a3) -> {
            throw new ParseException(ORIGINAL_MESSAGE, 0);
    });

    private LTieIntFunction<Integer> sutAlwaysThrowingUnchecked = LTieIntFunction.tieIntFunc((a1,a2,a3) -> {
            throw new IndexOutOfBoundsException(ORIGINAL_MESSAGE);
    });


    @Test
    public void testTheResult() throws Throwable {
        assertThat(sut.doApplyAsInt(100,100,100))
            .isEqualTo(testValue);
    }

    @Test
    public void testTupleCall() throws Throwable {

        LObjBiIntTriple<Integer> domainObject = Tuple4U.objBiIntTriple(100,100,100);

        Object result = sut.tupleApplyAsInt(domainObject);

        assertThat(result)
            .isEqualTo(testValue);
    }

    @Test
    public void testNonNullDoApplyAsInt() throws Throwable {
        assertThat(sut.nonNullDoApplyAsInt(100,100,100))
            .isEqualTo(testValue);
    }

    @Test
    public void testNestingDoApplyAsIntUnchecked() throws Throwable {

        // then
        try {
            sutAlwaysThrowingUnchecked.nestingDoApplyAsInt(100,100,100);
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
            sutAlwaysThrowingUnchecked.shovingDoApplyAsInt(100,100,100);
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
            .isEqualTo("LTieIntFunction: int doApplyAsInt(T a1,int a2,int a3)");
    }

    @Test
    public void testTieIntFuncMethod() throws Throwable {
        assertThat(LTieIntFunction.tieIntFunc((a1,a2,a3) -> testValue ))
            .isInstanceOf(LTieIntFunction.class);
    }






    // <editor-fold desc="compose (functional)">

    @Test
    public void testTieIntFuncComposeInt() throws Throwable {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LTieIntFunction<Integer> sutO = (a1,a2,a3) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(90);
                assertThat(a2).isEqualTo(91);
                assertThat(a3).isEqualTo(92);
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
        LIntUnaryOperator before3 = p2 -> {
            assertThat(p2).isEqualTo(82);
            beforeCalls.incrementAndGet();
            return 92;
        };

        //when
        LTieIntFunction<Integer> function = sutO.tieIntFuncComposeInt(before1,before2,before3);
        function.doApplyAsInt(80,81,82);

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(beforeCalls.get()).isEqualTo(3);
    }


    @Test
    public void testTieIntFuncCompose() throws Throwable {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LTieIntFunction<Integer> sutO = (a1,a2,a3) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(90);
                assertThat(a2).isEqualTo(91);
                assertThat(a3).isEqualTo(92);
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
        LToIntFunction<Integer> before3 = p2 -> {
            assertThat(p2).isEqualTo(82);
            beforeCalls.incrementAndGet();
            return 92;
        };

        //when
        LToIntTriFunction<Integer,Integer,Integer> function = sutO.tieIntFuncCompose(before1,before2,before3);
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
        LTieIntFunction<Integer> sutO = (a1,a2,a3) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(80);
                assertThat(a2).isEqualTo(81);
                assertThat(a3).isEqualTo(82);
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
        LObjBiIntFunction<Integer,Integer> function = sutO.then(thenFunction);
        Integer finalValue = function.doApply(80,81,82);

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
        LTieIntFunction<Integer> sutO = (a1,a2,a3) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(80);
                assertThat(a2).isEqualTo(81);
                assertThat(a3).isEqualTo(82);
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
        LTieIntFunction<Integer> function = sutO.thenToInt(thenFunction);
        int finalValue = function.doApplyAsInt(80,81,82);

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
        LTieIntFunction<Integer> sutO = (a1,a2,a3) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(80);
                assertThat(a2).isEqualTo(81);
                assertThat(a3).isEqualTo(82);
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
        LObjBiIntPredicate<Integer> function = sutO.thenToBool(thenFunction);
        boolean finalValue = function.doTest(80,81,82);

        //then - finals
        assertThat(finalValue).isEqualTo(true);
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    // </editor-fold>

    @Test
    public void testNesting() {
        assertThat(sut.nestingTieIntFunc())
            .isSameAs(sut)
            .isInstanceOf(LTieIntFunction.class);
    }

    @Test
    public void testShoving() {
        assertThat(sut.shovingTieIntFunc())
            .isSameAs(sut)
            .isInstanceOf(LTieIntFunction.class);
    }


    @Test(expectedExceptions = RuntimeException.class)
    public void testShove() {

        // given
        LTieIntFunction<Integer> sutThrowing = LTieIntFunction.tieIntFunc((a1,a2,a3) -> {
            throw new UnsupportedOperationException();
        });

        // when
        sutThrowing.shovingTieIntFunc().doApplyAsInt(100,100,100);
    }


    @Test
    public void testToString() throws Throwable {

        assertThat(sut.toString())
                .isInstanceOf(String.class)
                .startsWith(this.getClass().getName()+"$");

        assertThat(String.format("%s", sut))
                .isInstanceOf(String.class)
                .contains("LTieIntFunction: int doApplyAsInt(T a1,int a2,int a3)");
    }


    @Test
    public void isThrowing() {
        assertThat(sut.isThrowing())
            .isFalse();
    }

    //<editor-fold desc="Variants">

    private int variantLObjInt2Int1ToIntFunc(Integer a1,int a3,int a2) {
        return 100;
    }

    @Test
    public void compilerSubstituteVariantLObjInt2Int1ToIntFunc() {
        LTieIntFunction lambda = LTieIntFunction./*<T>*/objInt2Int1ToIntFunc(this::variantLObjInt2Int1ToIntFunc);

        assertThat(lambda).isInstanceOf(LTieIntFunction.LObjInt2Int1ToIntFunc.class);
    }


    private int variantLInt1ObjIntToIntFunc(int a2,Integer a1,int a3) {
        return 100;
    }

    @Test
    public void compilerSubstituteVariantLInt1ObjIntToIntFunc() {
        LTieIntFunction lambda = LTieIntFunction./*<T>*/int1ObjIntToIntFunc(this::variantLInt1ObjIntToIntFunc);

        assertThat(lambda).isInstanceOf(LTieIntFunction.LInt1ObjIntToIntFunc.class);
    }


    private int variantLInt1Int2ObjToIntFunc(int a2,int a3,Integer a1) {
        return 100;
    }

    @Test
    public void compilerSubstituteVariantLInt1Int2ObjToIntFunc() {
        LTieIntFunction lambda = LTieIntFunction./*<T>*/int1Int2ObjToIntFunc(this::variantLInt1Int2ObjToIntFunc);

        assertThat(lambda).isInstanceOf(LTieIntFunction.LInt1Int2ObjToIntFunc.class);
    }


    private int variantLInt2ObjInt1ToIntFunc(int a3,Integer a1,int a2) {
        return 100;
    }

    @Test
    public void compilerSubstituteVariantLInt2ObjInt1ToIntFunc() {
        LTieIntFunction lambda = LTieIntFunction./*<T>*/int2ObjInt1ToIntFunc(this::variantLInt2ObjInt1ToIntFunc);

        assertThat(lambda).isInstanceOf(LTieIntFunction.LInt2ObjInt1ToIntFunc.class);
    }


    private int variantLBiIntObjToIntFunc(int a3,int a2,Integer a1) {
        return 100;
    }

    @Test
    public void compilerSubstituteVariantLBiIntObjToIntFunc() {
        LTieIntFunction lambda = LTieIntFunction./*<T>*/biIntObjToIntFunc(this::variantLBiIntObjToIntFunc);

        assertThat(lambda).isInstanceOf(LTieIntFunction.LBiIntObjToIntFunc.class);
    }

    //</editor-fold>


    @Test void safeCompiles() {
        LTieIntFunction r1 = LTieIntFunction.safe(sut); //NOSONAR
    }

    @Test void safePropagates() {
        Object result = LTieIntFunction.safe(sut);
        assertThat(result).isSameAs(sut);
    }

    @Test void safeProtectsAgainstNpe() {
        Object result = LTieIntFunction.safe(null);
        assertThat(result).isSameAs(LTieIntFunction.tieIntFunc(LTieIntFunction.safe()));
    }

    @Test  void safeSupplierPropagates() {
        LSupplier<LTieIntFunction<Integer>> supplier = ()->sut;
        Object result = LTieIntFunction.safeSupplier(supplier);
        assertThat(result).isSameAs(supplier);
    }

    @Test  void safeSupplierProtectsAgainstNpe() {
        Object result = LTieIntFunction.safeSupplier(null);
        assertThat(result).isSameAs(LTieIntFunction.safeSupplier());
    }

    @Test  void safeSupplierCompiles() {
        LSupplier<LTieIntFunction<Integer>> r1 = LTieIntFunction.safeSupplier(()->sut);  //NOSONAR
    }

}
