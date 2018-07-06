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
public class LBiBoolFunctionTest<R> {
    private static final String ORIGINAL_MESSAGE = "Original message";
    private static final String EXCEPTION_WAS_WRAPPED = "Exception was wrapped.";
    private static final String NO_EXCEPTION_WERE_THROWN = "No exception were thrown.";

    private Integer testValue = 100;



    private LBiBoolFunction<Integer> sut = new LBiBoolFunction<Integer>(){
        public @Nullable Integer doApplyX(boolean a1,boolean a2)  {
            return testValue;
        }
    };


    private LBiBoolFunction<Integer> sutNull = new LBiBoolFunction<Integer>(){
        public @Nullable Integer doApplyX(boolean a1,boolean a2)  {
            return null;
        }
    };



    private LBiBoolFunction<Integer> sutAlwaysThrowing = LBiBoolFunction.biBoolFunc((a1,a2) -> {
            throw new ParseException(ORIGINAL_MESSAGE, 0);
    });

    private LBiBoolFunction<Integer> sutAlwaysThrowingUnchecked = LBiBoolFunction.biBoolFunc((a1,a2) -> {
            throw new IndexOutOfBoundsException(ORIGINAL_MESSAGE);
    });


    @Test
    public void testTheResult() throws Throwable {
        assertThat(sut.doApply(true,true))
            .isEqualTo(testValue);
    }

    @Test
    public void testTupleCall() throws Throwable {

        LBoolPair domainObject = Tuple4U.boolPair(true,true);

        Object result = sut.tupleApply(domainObject);

        assertThat(result)
            .isEqualTo(testValue);
    }

    @Test
    public void testNonNullDoApply() throws Throwable {
        assertThat(sut.nonNullDoApply(true,true))
            .isSameAs(testValue);
    }

    @Test
    public void testNestingDoApplyUnchecked() throws Throwable {

        // then
        try {
            sutAlwaysThrowingUnchecked.nestingDoApply(true,true);
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
            sutAlwaysThrowingUnchecked.shovingDoApply(true,true);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                    .hasNoCause()
                    .hasMessage(ORIGINAL_MESSAGE);
        }
    }

    @Test(expectedExceptions=NullPointerException.class, expectedExceptionsMessageRegExp="\\QEvaluated value by nonNullDoApply() method cannot be null (LBiBoolFunction: R doApply(boolean a1,boolean a2)).\\E")
    public void testNonNullCapturesNull() throws Throwable {
        sutNull.nonNullDoApply(true,true);
    }


    @Test
    public void testFunctionalInterfaceDescription() throws Throwable {
        assertThat(sut.functionalInterfaceDescription())
            .isEqualTo("LBiBoolFunction: R doApply(boolean a1,boolean a2)");
    }

    @Test
    public void testBiBoolFuncMethod() throws Throwable {
        assertThat(LBiBoolFunction.biBoolFunc((a1,a2) -> testValue ))
            .isInstanceOf(LBiBoolFunction.class);
    }






    // <editor-fold desc="compose (functional)">

    @Test
    public void testBiBoolFuncComposeBool() throws Throwable {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LBiBoolFunction<Integer> sutO = (a1,a2) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(true);
                assertThat(a2).isEqualTo(true);
                return 100;
        };

        LLogicalOperator before1 = p0 -> {
            assertThat(p0).isEqualTo(true);
            beforeCalls.incrementAndGet();
            return true;
        };
        LLogicalOperator before2 = p1 -> {
            assertThat(p1).isEqualTo(true);
            beforeCalls.incrementAndGet();
            return true;
        };

        //when
        LBiBoolFunction<Integer> function = sutO.biBoolFuncComposeBool(before1,before2);
        function.doApply(true,true);

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(beforeCalls.get()).isEqualTo(2);
    }


    @Test
    public void testBiBoolFuncCompose() throws Throwable {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LBiBoolFunction<Integer> sutO = (a1,a2) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(true);
                assertThat(a2).isEqualTo(true);
                return 100;
        };

        LPredicate<Integer> before1 = p0 -> {
            assertThat(p0).isEqualTo(80);
            beforeCalls.incrementAndGet();
            return true;
        };
        LPredicate<Integer> before2 = p1 -> {
            assertThat(p1).isEqualTo(81);
            beforeCalls.incrementAndGet();
            return true;
        };

        //when
        LBiFunction<Integer,Integer,Integer> function = sutO.biBoolFuncCompose(before1,before2);
        function.doApply(80,81);

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(beforeCalls.get()).isEqualTo(2);
    }

    // </editor-fold>



    // <editor-fold desc="then (functional)">

    @Test
    public void testThen0() throws Throwable  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);

        //given (+ some assertions)
        LBiBoolFunction<Integer> sutO = (a1,a2) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(true);
                assertThat(a2).isEqualTo(true);
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
        LBiBoolFunction<Integer> function = sutO.then(thenFunction);
        Integer finalValue = function.doApply(true,true);

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
        LBiBoolFunction<Integer> sutO = (a1,a2) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(true);
                assertThat(a2).isEqualTo(true);
                return 90;
        };

        LConsumer<Integer> thenFunction = p -> {
                thenFunctionCalled.set(true);
                // Integer
                assertThat(p).isEqualTo(90);
        };

        //when
        LBiBoolConsumer function = sutO.thenConsume(thenFunction);
        function.doAccept(true,true);

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    @Test
    public void testThenToBool2() throws Throwable  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);

        //given (+ some assertions)
        LBiBoolFunction<Integer> sutO = (a1,a2) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(true);
                assertThat(a2).isEqualTo(true);
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
        LLogicalBinaryOperator function = sutO.thenToBool(thenFunction);
        boolean finalValue = function.doApply(true,true);

        //then - finals
        assertThat(finalValue).isEqualTo(true);
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    // </editor-fold>

    @Test
    public void testNesting() {
        assertThat(sut.nestingBiBoolFunc())
            .isSameAs(sut)
            .isInstanceOf(LBiBoolFunction.class);
    }

    @Test
    public void testShoving() {
        assertThat(sut.shovingBiBoolFunc())
            .isSameAs(sut)
            .isInstanceOf(LBiBoolFunction.class);
    }


    @Test(expectedExceptions = RuntimeException.class)
    public void testShove() {

        // given
        LBiBoolFunction<Integer> sutThrowing = LBiBoolFunction.biBoolFunc((a1,a2) -> {
            throw new UnsupportedOperationException();
        });

        // when
        sutThrowing.shovingBiBoolFunc().doApply(true,true);
    }


    @Test
    public void testToString() throws Throwable {

        assertThat(sut.toString())
                .isInstanceOf(String.class)
                .startsWith(this.getClass().getName()+"$");

        assertThat(String.format("%s", sut))
                .isInstanceOf(String.class)
                .contains("LBiBoolFunction: R doApply(boolean a1,boolean a2)");
    }


    @Test
    public void isThrowing() {
        assertThat(sut.isThrowing())
            .isFalse();
    }

    //<editor-fold desc="Variants">

    private Integer variantLBool1Bool0Func(boolean a2,boolean a1) {
        return 100;
    }

    @Test
    public void compilerSubstituteVariantLBool1Bool0Func() {
        LBiBoolFunction lambda = LBiBoolFunction./*<R>*/bool1Bool0Func(this::variantLBool1Bool0Func);

        assertThat(lambda).isInstanceOf(LBiBoolFunction.LBool1Bool0Func.class);
    }

    //</editor-fold>


    @Test void safeCompiles() {
        LBiBoolFunction r1 = LBiBoolFunction.safe(sut); //NOSONAR
    }

    @Test void safePropagates() {
        Object result = LBiBoolFunction.safe(sut);
        assertThat(result).isSameAs(sut);
    }

    @Test void safeProtectsAgainstNpe() {
        Object result = LBiBoolFunction.safe(null);
        assertThat(result).isSameAs(LBiBoolFunction.biBoolFunc(LBiBoolFunction.safe()));
    }

    @Test  void safeSupplierPropagates() {
        LSupplier<LBiBoolFunction<Integer>> supplier = ()->sut;
        Object result = LBiBoolFunction.safeSupplier(supplier);
        assertThat(result).isSameAs(supplier);
    }

    @Test  void safeSupplierProtectsAgainstNpe() {
        Object result = LBiBoolFunction.safeSupplier(null);
        assertThat(result).isSameAs(LBiBoolFunction.safeSupplier());
    }

    @Test  void safeSupplierCompiles() {
        LSupplier<LBiBoolFunction<Integer>> r1 = LBiBoolFunction.safeSupplier(()->sut);  //NOSONAR
    }

}
