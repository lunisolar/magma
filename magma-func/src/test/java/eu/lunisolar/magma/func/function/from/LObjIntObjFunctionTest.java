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
public class LObjIntObjFunctionTest<T1,T2,R> {
    private static final String ORIGINAL_MESSAGE = "Original message";
    private static final String EXCEPTION_WAS_WRAPPED = "Exception was wrapped.";
    private static final String NO_EXCEPTION_WERE_THROWN = "No exception were thrown.";

    private Integer testValue = 100;



    private LObjIntObjFunction<Integer,Integer,Integer> sut = new LObjIntObjFunction<Integer,Integer,Integer>(){
        public @Nullable Integer doApplyX(Integer a1,int a2,Integer a3)  {
            return testValue;
        }
    };


    private LObjIntObjFunction<Integer,Integer,Integer> sutNull = new LObjIntObjFunction<Integer,Integer,Integer>(){
        public @Nullable Integer doApplyX(Integer a1,int a2,Integer a3)  {
            return null;
        }
    };



    private LObjIntObjFunction<Integer,Integer,Integer> sutAlwaysThrowing = LObjIntObjFunction.objIntObjFunc((a1,a2,a3) -> {
            throw new ParseException(ORIGINAL_MESSAGE, 0);
    });

    private LObjIntObjFunction<Integer,Integer,Integer> sutAlwaysThrowingUnchecked = LObjIntObjFunction.objIntObjFunc((a1,a2,a3) -> {
            throw new IndexOutOfBoundsException(ORIGINAL_MESSAGE);
    });


    @Test
    public void testTheResult() throws Throwable {
        assertThat(sut.doApply(100,100,100))
            .isEqualTo(testValue);
    }

    @Test
    public void testTupleCall() throws Throwable {

        LObjIntObjTriple<Integer,Integer> domainObject = Tuple4U.objIntObjTriple(100,100,100);

        Object result = sut.tupleApply(domainObject);

        assertThat(result)
            .isEqualTo(testValue);
    }

    @Test
    public void testNonNullDoApply() throws Throwable {
        assertThat(sut.nonNullDoApply(100,100,100))
            .isSameAs(testValue);
    }

    @Test
    public void testNestingDoApplyUnchecked() throws Throwable {

        // then
        try {
            sutAlwaysThrowingUnchecked.nestingDoApply(100,100,100);
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
            sutAlwaysThrowingUnchecked.shovingDoApply(100,100,100);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                    .hasNoCause()
                    .hasMessage(ORIGINAL_MESSAGE);
        }
    }

    @Test(expectedExceptions=NullPointerException.class, expectedExceptionsMessageRegExp="\\QEvaluated value by nonNullDoApply() method cannot be null (LObjIntObjFunction: R doApply(T1 a1,int a2,T2 a3)).\\E")
    public void testNonNullCapturesNull() throws Throwable {
        sutNull.nonNullDoApply(100,100,100);
    }


    @Test
    public void testFunctionalInterfaceDescription() throws Throwable {
        assertThat(sut.functionalInterfaceDescription())
            .isEqualTo("LObjIntObjFunction: R doApply(T1 a1,int a2,T2 a3)");
    }

    @Test
    public void testObjIntObjFuncMethod() throws Throwable {
        assertThat(LObjIntObjFunction.objIntObjFunc((a1,a2,a3) -> testValue ))
            .isInstanceOf(LObjIntObjFunction.class);
    }






    // <editor-fold desc="compose (functional)">

    @Test
    public void testObjIntObjFuncComposeInt() throws Throwable {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LObjIntObjFunction<Integer,Integer,Integer> sutO = (a1,a2,a3) -> {
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
        LFunction<Integer,Integer> before3 = p2 -> {
            assertThat(p2).isEqualTo(82);
            beforeCalls.incrementAndGet();
            return 92;
        };

        //when
        LObjIntObjFunction<Integer,Integer,Integer> function = sutO.objIntObjFuncComposeInt(before1,before2,before3);
        function.doApply(80,81,82);

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(beforeCalls.get()).isEqualTo(3);
    }


    @Test
    public void testObjIntObjFuncCompose() throws Throwable {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LObjIntObjFunction<Integer,Integer,Integer> sutO = (a1,a2,a3) -> {
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
        LFunction<Integer,Integer> before3 = p2 -> {
            assertThat(p2).isEqualTo(82);
            beforeCalls.incrementAndGet();
            return 92;
        };

        //when
        LTriFunction<Integer,Integer,Integer,Integer> function = sutO.objIntObjFuncCompose(before1,before2,before3);
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
        LObjIntObjFunction<Integer,Integer,Integer> sutO = (a1,a2,a3) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(80);
                assertThat(a2).isEqualTo(81);
                assertThat(a3).isEqualTo(82);
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
        LObjIntObjFunction<Integer,Integer,Integer> function = sutO.then(thenFunction);
        Integer finalValue = function.doApply(80,81,82);

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
        LObjIntObjFunction<Integer,Integer,Integer> sutO = (a1,a2,a3) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(80);
                assertThat(a2).isEqualTo(81);
                assertThat(a3).isEqualTo(82);
                return 90;
        };

        LConsumer<Integer> thenFunction = p -> {
                thenFunctionCalled.set(true);
                // Integer
                assertThat(p).isEqualTo(90);
        };

        //when
        LTieConsumer<Integer,Integer> function = sutO.thenConsume(thenFunction);
        function.doAccept(80,81,82);

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    @Test
    public void testThenToInt2() throws Throwable  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);

        //given (+ some assertions)
        LObjIntObjFunction<Integer,Integer,Integer> sutO = (a1,a2,a3) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(80);
                assertThat(a2).isEqualTo(81);
                assertThat(a3).isEqualTo(82);
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
        LTieFunction<Integer,Integer> function = sutO.thenToInt(thenFunction);
        int finalValue = function.doApplyAsInt(80,81,82);

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
        LObjIntObjFunction<Integer,Integer,Integer> sutO = (a1,a2,a3) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(80);
                assertThat(a2).isEqualTo(81);
                assertThat(a3).isEqualTo(82);
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
        LObjIntObjPredicate<Integer,Integer> function = sutO.thenToBool(thenFunction);
        boolean finalValue = function.doTest(80,81,82);

        //then - finals
        assertThat(finalValue).isEqualTo(true);
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    // </editor-fold>

    @Test
    public void testNesting() {
        assertThat(sut.nestingObjIntObjFunc())
            .isSameAs(sut)
            .isInstanceOf(LObjIntObjFunction.class);
    }

    @Test
    public void testShoving() {
        assertThat(sut.shovingObjIntObjFunc())
            .isSameAs(sut)
            .isInstanceOf(LObjIntObjFunction.class);
    }


    @Test(expectedExceptions = RuntimeException.class)
    public void testShove() {

        // given
        LObjIntObjFunction<Integer,Integer,Integer> sutThrowing = LObjIntObjFunction.objIntObjFunc((a1,a2,a3) -> {
            throw new UnsupportedOperationException();
        });

        // when
        sutThrowing.shovingObjIntObjFunc().doApply(100,100,100);
    }


    @Test
    public void testToString() throws Throwable {

        assertThat(sut.toString())
                .isInstanceOf(String.class)
                .startsWith(this.getClass().getName()+"$");

        assertThat(String.format("%s", sut))
                .isInstanceOf(String.class)
                .contains("LObjIntObjFunction: R doApply(T1 a1,int a2,T2 a3)");
    }


    @Test
    public void isThrowing() {
        assertThat(sut.isThrowing())
            .isFalse();
    }

    //<editor-fold desc="Variants">

    private Integer variantLObjObj2IntFunc(Integer a1,Integer a3,int a2) {
        return 100;
    }

    @Test
    public void compilerSubstituteVariantLObjObj2IntFunc() {
        LObjIntObjFunction lambda = LObjIntObjFunction./*<T1,T2,R>*/objObj2IntFunc(this::variantLObjObj2IntFunc);

        assertThat(lambda).isInstanceOf(LObjIntObjFunction.LObjObj2IntFunc.class);
    }


    private Integer variantLIntBiObjFunc(int a2,Integer a1,Integer a3) {
        return 100;
    }

    @Test
    public void compilerSubstituteVariantLIntBiObjFunc() {
        LObjIntObjFunction lambda = LObjIntObjFunction./*<T1,T2,R>*/intBiObjFunc(this::variantLIntBiObjFunc);

        assertThat(lambda).isInstanceOf(LObjIntObjFunction.LIntBiObjFunc.class);
    }


    private Integer variantLIntObj2Obj0Func(int a2,Integer a3,Integer a1) {
        return 100;
    }

    @Test
    public void compilerSubstituteVariantLIntObj2Obj0Func() {
        LObjIntObjFunction lambda = LObjIntObjFunction./*<T1,T2,R>*/intObj2Obj0Func(this::variantLIntObj2Obj0Func);

        assertThat(lambda).isInstanceOf(LObjIntObjFunction.LIntObj2Obj0Func.class);
    }


    private Integer variantLObj2Obj0IntFunc(Integer a3,Integer a1,int a2) {
        return 100;
    }

    @Test
    public void compilerSubstituteVariantLObj2Obj0IntFunc() {
        LObjIntObjFunction lambda = LObjIntObjFunction./*<T1,T2,R>*/obj2Obj0IntFunc(this::variantLObj2Obj0IntFunc);

        assertThat(lambda).isInstanceOf(LObjIntObjFunction.LObj2Obj0IntFunc.class);
    }


    private Integer variantLObj2IntObj0Func(Integer a3,int a2,Integer a1) {
        return 100;
    }

    @Test
    public void compilerSubstituteVariantLObj2IntObj0Func() {
        LObjIntObjFunction lambda = LObjIntObjFunction./*<T1,T2,R>*/obj2IntObj0Func(this::variantLObj2IntObj0Func);

        assertThat(lambda).isInstanceOf(LObjIntObjFunction.LObj2IntObj0Func.class);
    }

    //</editor-fold>


    @Test void safeCompiles() {
        LObjIntObjFunction r1 = LObjIntObjFunction.safe(sut); //NOSONAR
    }

    @Test void safePropagates() {
        Object result = LObjIntObjFunction.safe(sut);
        assertThat(result).isSameAs(sut);
    }

    @Test void safeProtectsAgainstNpe() {
        Object result = LObjIntObjFunction.safe(null);
        assertThat(result).isSameAs(LObjIntObjFunction.objIntObjFunc(LObjIntObjFunction.safe()));
    }

    @Test  void safeSupplierPropagates() {
        LSupplier<LObjIntObjFunction<Integer,Integer,Integer>> supplier = ()->sut;
        Object result = LObjIntObjFunction.safeSupplier(supplier);
        assertThat(result).isSameAs(supplier);
    }

    @Test  void safeSupplierProtectsAgainstNpe() {
        Object result = LObjIntObjFunction.safeSupplier(null);
        assertThat(result).isSameAs(LObjIntObjFunction.safeSupplier());
    }

    @Test  void safeSupplierCompiles() {
        LSupplier<LObjIntObjFunction<Integer,Integer,Integer>> r1 = LObjIntObjFunction.safeSupplier(()->sut);  //NOSONAR
    }

}
