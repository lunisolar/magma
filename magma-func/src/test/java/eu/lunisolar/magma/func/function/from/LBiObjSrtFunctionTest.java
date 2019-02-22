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
public class LBiObjSrtFunctionTest<T1,T2,R> {
    private static final String ORIGINAL_MESSAGE = "Original message";
    private static final String EXCEPTION_WAS_WRAPPED = "Exception was wrapped.";
    private static final String NO_EXCEPTION_WERE_THROWN = "No exception were thrown.";

    private Integer testValue = 100;



    private LBiObjSrtFunction<Integer,Integer,Integer> sut = new LBiObjSrtFunction<Integer,Integer,Integer>(){
        public @Nullable Integer applyX(Integer a1,Integer a2,short a3)  {
            return testValue;
        }
    };


    private LBiObjSrtFunction<Integer,Integer,Integer> sutNull = new LBiObjSrtFunction<Integer,Integer,Integer>(){
        public @Nullable Integer applyX(Integer a1,Integer a2,short a3)  {
            return null;
        }
    };



    private LBiObjSrtFunction<Integer,Integer,Integer> sutAlwaysThrowing = LBiObjSrtFunction.biObjSrtFunc((a1,a2,a3) -> {
            throw new ParseException(ORIGINAL_MESSAGE, 0);
    });

    private LBiObjSrtFunction<Integer,Integer,Integer> sutAlwaysThrowingUnchecked = LBiObjSrtFunction.biObjSrtFunc((a1,a2,a3) -> {
            throw new IndexOutOfBoundsException(ORIGINAL_MESSAGE);
    });


    @Test
    public void testTheResult() throws Throwable {
        assertThat(sut.apply(100,100,(short)100))
            .isEqualTo(testValue);
    }

    @Test
    public void testTupleCall() throws Throwable {

        LBiObjSrtTriple<Integer,Integer> domainObject = Tuple4U.biObjSrtTriple(100,100,(short)100);

        Object result = sut.tupleApply(domainObject);

        assertThat(result)
            .isEqualTo(testValue);
    }

    @Test
    public void testNonNullApply() throws Throwable {
        assertThat(sut.nonNullApply(100,100,(short)100))
            .isSameAs(testValue);
    }

    @Test
    public void testNestingApplyUnchecked() throws Throwable {

        // then
        try {
            sutAlwaysThrowingUnchecked.nestingApply(100,100,(short)100);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                    .hasNoCause()
                    .hasMessage(ORIGINAL_MESSAGE);
        }
    }

    @Test
    public void testShovingApplyUnchecked() throws Throwable {

        // then
        try {
            sutAlwaysThrowingUnchecked.shovingApply(100,100,(short)100);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                    .hasNoCause()
                    .hasMessage(ORIGINAL_MESSAGE);
        }
    }

    @Test(expectedExceptions=NullPointerException.class, expectedExceptionsMessageRegExp="\\QEvaluated value by nonNullApply() method cannot be null (LBiObjSrtFunction: R apply(T1 a1,T2 a2,short a3)).\\E")
    public void testNonNullCapturesNull() throws Throwable {
        sutNull.nonNullApply(100,100,(short)100);
    }


    @Test
    public void testFunctionalInterfaceDescription() throws Throwable {
        assertThat(sut.functionalInterfaceDescription())
            .isEqualTo("LBiObjSrtFunction: R apply(T1 a1,T2 a2,short a3)");
    }

    @Test
    public void testBiObjSrtFuncMethod() throws Throwable {
        assertThat(LBiObjSrtFunction.biObjSrtFunc((a1,a2,a3) -> testValue ))
            .isInstanceOf(LBiObjSrtFunction.class);
    }






    // <editor-fold desc="compose (functional)">

    @Test
    public void testCompose() throws Throwable {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LBiObjSrtFunction<Integer,Integer,Integer> sutO = (a1,a2,a3) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(90);
                assertThat(a2).isEqualTo(91);
                assertThat(a3).isEqualTo((short)92);
                return 100;
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
        LSrtUnaryOperator before3 = p2 -> {
            assertThat(p2).isEqualTo((short)82);
            beforeCalls.incrementAndGet();
            return (short)92;
        };

        //when
        LBiObjSrtFunction<Integer,Integer,Integer> function = sutO.compose(before1,before2,before3);
        function.apply(80,81,(short)82);

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(beforeCalls.get()).isEqualTo(3);
    }


    @Test
    public void testBiObjSrtFuncCompose() throws Throwable {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LBiObjSrtFunction<Integer,Integer,Integer> sutO = (a1,a2,a3) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(90);
                assertThat(a2).isEqualTo(91);
                assertThat(a3).isEqualTo((short)92);
                return 100;
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
        LToSrtFunction<Integer> before3 = p2 -> {
            assertThat(p2).isEqualTo(82);
            beforeCalls.incrementAndGet();
            return (short)92;
        };

        //when
        LTriFunction<Integer,Integer,Integer,Integer> function = sutO.biObjSrtFuncCompose(before1,before2,before3);
        function.apply(80,81,82);

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
        LBiObjSrtFunction<Integer,Integer,Integer> sutO = (a1,a2,a3) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(80);
                assertThat(a2).isEqualTo(81);
                assertThat(a3).isEqualTo((short)82);
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
        LBiObjSrtFunction<Integer,Integer,Integer> function = sutO.then(thenFunction);
        Integer finalValue = function.apply(80,81,(short)82);

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
        LBiObjSrtFunction<Integer,Integer,Integer> sutO = (a1,a2,a3) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(80);
                assertThat(a2).isEqualTo(81);
                assertThat(a3).isEqualTo((short)82);
                return 90;
        };

        LConsumer<Integer> thenFunction = p -> {
                thenFunctionCalled.set(true);
                // Integer
                assertThat(p).isEqualTo(90);
        };

        //when
        LBiObjSrtConsumer<Integer,Integer> function = sutO.thenConsume(thenFunction);
        function.accept(80,81,(short)82);

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    @Test
    public void testThenToBool2() throws Throwable  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);

        //given (+ some assertions)
        LBiObjSrtFunction<Integer,Integer,Integer> sutO = (a1,a2,a3) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(80);
                assertThat(a2).isEqualTo(81);
                assertThat(a3).isEqualTo((short)82);
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
        LBiObjSrtPredicate<Integer,Integer> function = sutO.thenToBool(thenFunction);
        boolean finalValue = function.test(80,81,(short)82);

        //then - finals
        assertThat(finalValue).isEqualTo(true);
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    // </editor-fold>


    @Test(expectedExceptions = RuntimeException.class)
    public void testShove() {

        // given
        LBiObjSrtFunction<Integer,Integer,Integer> sutThrowing = LBiObjSrtFunction.biObjSrtFunc((a1,a2,a3) -> {
            throw new UnsupportedOperationException();
        });

        // when
        sutThrowing.shovingApply(100,100,(short)100);
    }


    @Test
    public void testToString() throws Throwable {

        assertThat(sut.toString())
                .isInstanceOf(String.class)
                .startsWith(this.getClass().getName()+"$");

        assertThat(String.format("%s", sut))
                .isInstanceOf(String.class)
                .contains("LBiObjSrtFunction: R apply(T1 a1,T2 a2,short a3)");
    }


    @Test
    public void isThrowing() {
        assertThat(sut.isThrowing())
            .isFalse();
    }

    //<editor-fold desc="Variants">

    private Integer variantLObj0Srt2Obj1Func(Integer a1,short a3,Integer a2) {
        return 100;
    }

    @Test
    public void compilerSubstituteVariantLObj0Srt2Obj1Func() {
        LBiObjSrtFunction lambda = LBiObjSrtFunction./*<T1,T2,R>*/obj0Srt2Obj1Func(this::variantLObj0Srt2Obj1Func);

        assertThat(lambda).isInstanceOf(LBiObjSrtFunction.LObj0Srt2Obj1Func.class);
    }


    private Integer variantLObj1Obj0Srt2Func(Integer a2,Integer a1,short a3) {
        return 100;
    }

    @Test
    public void compilerSubstituteVariantLObj1Obj0Srt2Func() {
        LBiObjSrtFunction lambda = LBiObjSrtFunction./*<T1,T2,R>*/obj1Obj0Srt2Func(this::variantLObj1Obj0Srt2Func);

        assertThat(lambda).isInstanceOf(LBiObjSrtFunction.LObj1Obj0Srt2Func.class);
    }


    private Integer variantLObj1Srt2Obj0Func(Integer a2,short a3,Integer a1) {
        return 100;
    }

    @Test
    public void compilerSubstituteVariantLObj1Srt2Obj0Func() {
        LBiObjSrtFunction lambda = LBiObjSrtFunction./*<T1,T2,R>*/obj1Srt2Obj0Func(this::variantLObj1Srt2Obj0Func);

        assertThat(lambda).isInstanceOf(LBiObjSrtFunction.LObj1Srt2Obj0Func.class);
    }


    private Integer variantLSrt2Obj0Obj1Func(short a3,Integer a1,Integer a2) {
        return 100;
    }

    @Test
    public void compilerSubstituteVariantLSrt2Obj0Obj1Func() {
        LBiObjSrtFunction lambda = LBiObjSrtFunction./*<T1,T2,R>*/srt2Obj0Obj1Func(this::variantLSrt2Obj0Obj1Func);

        assertThat(lambda).isInstanceOf(LBiObjSrtFunction.LSrt2Obj0Obj1Func.class);
    }


    private Integer variantLSrt2Obj1Obj0Func(short a3,Integer a2,Integer a1) {
        return 100;
    }

    @Test
    public void compilerSubstituteVariantLSrt2Obj1Obj0Func() {
        LBiObjSrtFunction lambda = LBiObjSrtFunction./*<T1,T2,R>*/srt2Obj1Obj0Func(this::variantLSrt2Obj1Obj0Func);

        assertThat(lambda).isInstanceOf(LBiObjSrtFunction.LSrt2Obj1Obj0Func.class);
    }

    //</editor-fold>


    @Test void safeCompiles() {
        LBiObjSrtFunction r1 = LBiObjSrtFunction.safe(sut); //NOSONAR
    }

    @Test void safePropagates() {
        Object result = LBiObjSrtFunction.safe(sut);
        assertThat(result).isSameAs(sut);
    }

    @Test void safeProtectsAgainstNpe() {
        Object result = LBiObjSrtFunction.safe(null);
        assertThat(result).isSameAs(LBiObjSrtFunction.biObjSrtFunc(LBiObjSrtFunction.safe()));
    }

    @Test  void safeSupplierPropagates() {
        LSupplier<LBiObjSrtFunction<Integer,Integer,Integer>> supplier = ()->sut;
        Object result = LBiObjSrtFunction.safeSupplier(supplier);
        assertThat(result).isSameAs(supplier);
    }

    @Test  void safeSupplierProtectsAgainstNpe() {
        Object result = LBiObjSrtFunction.safeSupplier(null);
        assertThat(result).isSameAs(LBiObjSrtFunction.safeSupplier());
    }

    @Test  void safeSupplierCompiles() {
        LSupplier<LBiObjSrtFunction<Integer,Integer,Integer>> r1 = LBiObjSrtFunction.safeSupplier(()->sut);  //NOSONAR
    }

}
