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

package eu.lunisolar.magma.func.function.from;

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
public class LObjIntBoolFunctionTest<T,R> {
    private static final String ORIGINAL_MESSAGE = "Original message";
    private static final String EXCEPTION_WAS_WRAPPED = "Exception was wrapped.";
    private static final String NO_EXCEPTION_WERE_THROWN = "No exception were thrown.";

    private Integer testValue = 100;



    private LObjIntBoolFunction<Integer,Integer> sut = new LObjIntBoolFunction<Integer,Integer>(){
        public @Nullable Integer doApplyX(Integer a1,int a2,boolean a3)  {
            return testValue;
        }
    };


    private LObjIntBoolFunction<Integer,Integer> sutNull = new LObjIntBoolFunction<Integer,Integer>(){
        public @Nullable Integer doApplyX(Integer a1,int a2,boolean a3)  {
            return null;
        }
    };



    private LObjIntBoolFunction<Integer,Integer> sutAlwaysThrowing = LObjIntBoolFunction.objIntBoolFunc((a1,a2,a3) -> {
            throw new ParseException(ORIGINAL_MESSAGE, 0);
    });

    private LObjIntBoolFunction<Integer,Integer> sutAlwaysThrowingUnchecked = LObjIntBoolFunction.objIntBoolFunc((a1,a2,a3) -> {
            throw new IndexOutOfBoundsException(ORIGINAL_MESSAGE);
    });


    @Test
    public void testTheResult() throws Throwable {
        assertThat(sut.doApply(100,100,true))
            .isEqualTo(testValue);
    }

    @Test
    public void testTupleCall() throws Throwable {

        LObjIntBoolTriple<Integer> domainObject = Tuple4U.objIntBoolTriple(100,100,true);

        Object result = sut.tupleApply(domainObject);

        assertThat(result)
            .isEqualTo(testValue);
    }

    @Test
    public void testNonNullDoApply() throws Throwable {
        assertThat(sut.nonNullDoApply(100,100,true))
            .isSameAs(testValue);
    }

    @Test
    public void testNestingDoApplyUnchecked() throws Throwable {

        // then
        try {
            sutAlwaysThrowingUnchecked.nestingDoApply(100,100,true);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                    .hasNoCause()
                    .hasMessage(ORIGINAL_MESSAGE);
        }
    }

    @Test
    public void testShovingDoApplyUnchecked() throws Throwable {

        // then
        try {
            sutAlwaysThrowingUnchecked.shovingDoApply(100,100,true);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                    .hasNoCause()
                    .hasMessage(ORIGINAL_MESSAGE);
        }
    }

    @Test(expectedExceptions=NullPointerException.class, expectedExceptionsMessageRegExp="\\QEvaluated value by nonNullDoApply() method cannot be null (LObjIntBoolFunction: R doApply(T a1,int a2,boolean a3)).\\E")
    public void testNonNullCapturesNull() throws Throwable {
        sutNull.nonNullDoApply(100,100,true);
    }


    @Test
    public void testFunctionalInterfaceDescription() throws Throwable {
        assertThat(sut.functionalInterfaceDescription())
            .isEqualTo("LObjIntBoolFunction: R doApply(T a1,int a2,boolean a3)");
    }

    @Test
    public void testObjIntBoolFuncMethod() throws Throwable {
        assertThat(LObjIntBoolFunction.objIntBoolFunc((a1,a2,a3) -> testValue ))
            .isInstanceOf(LObjIntBoolFunction.class);
    }






    // <editor-fold desc="compose (functional)">

    @Test
    public void testObjIntBoolFuncComposeIntBool() throws Throwable {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LObjIntBoolFunction<Integer,Integer> sutO = (a1,a2,a3) -> {
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
        LObjIntBoolFunction<Integer,Integer> function = sutO.objIntBoolFuncComposeIntBool(before1,before2,before3);
        function.doApply(80,81,true);

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(beforeCalls.get()).isEqualTo(3);
    }


    @Test
    public void testObjIntBoolFuncCompose() throws Throwable {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LObjIntBoolFunction<Integer,Integer> sutO = (a1,a2,a3) -> {
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
        LTriFunction<Integer,Integer,Integer,Integer> function = sutO.objIntBoolFuncCompose(before1,before2,before3);
        function.doApply(80,81,82);

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
        LObjIntBoolFunction<Integer,Integer> sutO = (a1,a2,a3) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(80);
                assertThat(a2).isEqualTo(81);
                assertThat(a3).isEqualTo(true);
                return 90;
        };

        LFunction<Integer,Integer> thenFunction = p -> {
                thenFunctionCalled.set(true);
                // Integer
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
    public void testThenConsume1() throws Throwable  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);

        //given (+ some assertions)
        LObjIntBoolFunction<Integer,Integer> sutO = (a1,a2,a3) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(80);
                assertThat(a2).isEqualTo(81);
                assertThat(a3).isEqualTo(true);
                return 90;
        };

        LConsumer<Integer> thenFunction = p -> {
                thenFunctionCalled.set(true);
                // Integer
                assertThat(p).isEqualTo(90);
        };

        //when
        LTieBoolConsumer<Integer> function = sutO.thenConsume(thenFunction);
        function.doAccept(80,81,true);

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    @Test
    public void testThenToInt2() throws Throwable  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);

        //given (+ some assertions)
        LObjIntBoolFunction<Integer,Integer> sutO = (a1,a2,a3) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(80);
                assertThat(a2).isEqualTo(81);
                assertThat(a3).isEqualTo(true);
                return 90;
        };

        LToIntFunction<Integer> thenFunction = p -> {
                thenFunctionCalled.set(true);
                // Integer
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
    public void testThenToBool3() throws Throwable  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);

        //given (+ some assertions)
        LObjIntBoolFunction<Integer,Integer> sutO = (a1,a2,a3) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(80);
                assertThat(a2).isEqualTo(81);
                assertThat(a3).isEqualTo(true);
                return 90;
        };

        LPredicate<Integer> thenFunction = p -> {
                thenFunctionCalled.set(true);
                // Integer
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
        assertThat(sut.nestingObjIntBoolFunc())
            .isSameAs(sut)
            .isInstanceOf(LObjIntBoolFunction.class);
    }

    @Test
    public void testShoving() {
        assertThat(sut.shovingObjIntBoolFunc())
            .isSameAs(sut)
            .isInstanceOf(LObjIntBoolFunction.class);
    }


    @Test(expectedExceptions = RuntimeException.class)
    public void testShove() {

        // given
        LObjIntBoolFunction<Integer,Integer> sutThrowing = LObjIntBoolFunction.objIntBoolFunc((a1,a2,a3) -> {
            throw new UnsupportedOperationException();
        });

        // when
        sutThrowing.shovingObjIntBoolFunc().doApply(100,100,true);
    }


    @Test
    public void testToString() throws Throwable {

        assertThat(sut.toString())
                .isInstanceOf(String.class)
                .startsWith(this.getClass().getName()+"$");

        assertThat(String.format("%s", sut))
                .isInstanceOf(String.class)
                .contains("LObjIntBoolFunction: R doApply(T a1,int a2,boolean a3)");
    }


    @Test
    public void isThrowing() {
        assertThat(sut.isThrowing())
            .isFalse();
    }

    //<editor-fold desc="Variants">

    private Integer variantLObjBoolIntFunc(Integer a1,boolean a3,int a2) {
        return 100;
    }

    @Test
    public void compilerSubstituteVariantLObjBoolIntFunc() {
        LObjIntBoolFunction lambda = LObjIntBoolFunction./*<T,R>*/objBoolIntFunc(this::variantLObjBoolIntFunc);

        assertThat(lambda).isInstanceOf(LObjIntBoolFunction.LObjBoolIntFunc.class);
    }


    private Integer variantLIntObjBoolFunc(int a2,Integer a1,boolean a3) {
        return 100;
    }

    @Test
    public void compilerSubstituteVariantLIntObjBoolFunc() {
        LObjIntBoolFunction lambda = LObjIntBoolFunction./*<T,R>*/intObjBoolFunc(this::variantLIntObjBoolFunc);

        assertThat(lambda).isInstanceOf(LObjIntBoolFunction.LIntObjBoolFunc.class);
    }


    private Integer variantLIntBoolObjFunc(int a2,boolean a3,Integer a1) {
        return 100;
    }

    @Test
    public void compilerSubstituteVariantLIntBoolObjFunc() {
        LObjIntBoolFunction lambda = LObjIntBoolFunction./*<T,R>*/intBoolObjFunc(this::variantLIntBoolObjFunc);

        assertThat(lambda).isInstanceOf(LObjIntBoolFunction.LIntBoolObjFunc.class);
    }


    private Integer variantLBoolObjIntFunc(boolean a3,Integer a1,int a2) {
        return 100;
    }

    @Test
    public void compilerSubstituteVariantLBoolObjIntFunc() {
        LObjIntBoolFunction lambda = LObjIntBoolFunction./*<T,R>*/boolObjIntFunc(this::variantLBoolObjIntFunc);

        assertThat(lambda).isInstanceOf(LObjIntBoolFunction.LBoolObjIntFunc.class);
    }


    private Integer variantLBoolIntObjFunc(boolean a3,int a2,Integer a1) {
        return 100;
    }

    @Test
    public void compilerSubstituteVariantLBoolIntObjFunc() {
        LObjIntBoolFunction lambda = LObjIntBoolFunction./*<T,R>*/boolIntObjFunc(this::variantLBoolIntObjFunc);

        assertThat(lambda).isInstanceOf(LObjIntBoolFunction.LBoolIntObjFunc.class);
    }

    //</editor-fold>


    @Test void safeCompiles() {
        LObjIntBoolFunction r1 = LObjIntBoolFunction.safe(sut); //NOSONAR
    }

    @Test void safePropagates() {
        Object result = LObjIntBoolFunction.safe(sut);
        assertThat(result).isSameAs(sut);
    }

    @Test void safeProtectsAgainstNpe() {
        Object result = LObjIntBoolFunction.safe(null);
        assertThat(result).isSameAs(LObjIntBoolFunction.objIntBoolFunc(LObjIntBoolFunction.safe()));
    }

    @Test  void safeSupplierPropagates() {
        LSupplier<LObjIntBoolFunction<Integer,Integer>> supplier = ()->sut;
        Object result = LObjIntBoolFunction.safeSupplier(supplier);
        assertThat(result).isSameAs(supplier);
    }

    @Test  void safeSupplierProtectsAgainstNpe() {
        Object result = LObjIntBoolFunction.safeSupplier(null);
        assertThat(result).isSameAs(LObjIntBoolFunction.safeSupplier());
    }

    @Test  void safeSupplierCompiles() {
        LSupplier<LObjIntBoolFunction<Integer,Integer>> r1 = LObjIntBoolFunction.safeSupplier(()->sut);  //NOSONAR
    }

}
