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
public class LBiObjDblFunctionTest<T1,T2,R> {
    private static final String ORIGINAL_MESSAGE = "Original message";
    private static final String EXCEPTION_WAS_WRAPPED = "Exception was wrapped.";
    private static final String NO_EXCEPTION_WERE_THROWN = "No exception were thrown.";

    private Integer testValue = 100;



    private LBiObjDblFunction<Integer,Integer,Integer> sut = new LBiObjDblFunction<Integer,Integer,Integer>(){
        public @Nullable Integer applyX(Integer a1,Integer a2,double a3)  {
            return testValue;
        }
    };


    private LBiObjDblFunction<Integer,Integer,Integer> sutNull = new LBiObjDblFunction<Integer,Integer,Integer>(){
        public @Nullable Integer applyX(Integer a1,Integer a2,double a3)  {
            return null;
        }
    };



    private LBiObjDblFunction<Integer,Integer,Integer> sutAlwaysThrowing = LBiObjDblFunction.biObjDblFunc((a1,a2,a3) -> {
            throw new ParseException(ORIGINAL_MESSAGE, 0);
    });

    private LBiObjDblFunction<Integer,Integer,Integer> sutAlwaysThrowingUnchecked = LBiObjDblFunction.biObjDblFunc((a1,a2,a3) -> {
            throw new IndexOutOfBoundsException(ORIGINAL_MESSAGE);
    });


    @Test
    public void testTheResult() throws Throwable {
        assertThat(sut.apply(100,100,100d))
            .isEqualTo(testValue);
    }

    @Test
    public void testTupleCall() throws Throwable {

        LBiObjDblTriple<Integer,Integer> domainObject = Tuple4U.biObjDblTriple(100,100,100d);

        Object result = sut.tupleApply(domainObject);

        assertThat(result)
            .isEqualTo(testValue);
    }

    @Test
    public void testNonNullApply() throws Throwable {
        assertThat(sut.nonNullApply(100,100,100d))
            .isSameAs(testValue);
    }

    @Test
    public void testNestingApplyUnchecked() throws Throwable {

        // then
        try {
            sutAlwaysThrowingUnchecked.nestingApply(100,100,100d);
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
            sutAlwaysThrowingUnchecked.shovingApply(100,100,100d);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                    .hasNoCause()
                    .hasMessage(ORIGINAL_MESSAGE);
        }
    }

    @Test(expectedExceptions=NullPointerException.class, expectedExceptionsMessageRegExp="\\QEvaluated value by nonNullApply() method cannot be null (LBiObjDblFunction: R apply(T1 a1,T2 a2,double a3)).\\E")
    public void testNonNullCapturesNull() throws Throwable {
        sutNull.nonNullApply(100,100,100d);
    }


    @Test
    public void testFunctionalInterfaceDescription() throws Throwable {
        assertThat(sut.functionalInterfaceDescription())
            .isEqualTo("LBiObjDblFunction: R apply(T1 a1,T2 a2,double a3)");
    }

    @Test
    public void testBiObjDblFuncMethod() throws Throwable {
        assertThat(LBiObjDblFunction.biObjDblFunc((a1,a2,a3) -> testValue ))
            .isInstanceOf(LBiObjDblFunction.class);
    }






    // <editor-fold desc="compose (functional)">

    @Test
    public void testCompose() throws Throwable {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LBiObjDblFunction<Integer,Integer,Integer> sutO = (a1,a2,a3) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(90);
                assertThat(a2).isEqualTo(91);
                assertThat(a3).isEqualTo(92d);
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
        LDblUnaryOperator before3 = p2 -> {
            assertThat(p2).isEqualTo(82d);
            beforeCalls.incrementAndGet();
            return 92d;
        };

        //when
        LBiObjDblFunction<Integer,Integer,Integer> function = sutO.compose(before1,before2,before3);
        function.apply(80,81,82d);

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(beforeCalls.get()).isEqualTo(3);
    }


    @Test
    public void testBiObjDblFuncCompose() throws Throwable {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LBiObjDblFunction<Integer,Integer,Integer> sutO = (a1,a2,a3) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(90);
                assertThat(a2).isEqualTo(91);
                assertThat(a3).isEqualTo(92d);
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
        LToDblFunction<Integer> before3 = p2 -> {
            assertThat(p2).isEqualTo(82);
            beforeCalls.incrementAndGet();
            return 92d;
        };

        //when
        LTriFunction<Integer,Integer,Integer,Integer> function = sutO.biObjDblFuncCompose(before1,before2,before3);
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
        LBiObjDblFunction<Integer,Integer,Integer> sutO = (a1,a2,a3) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(80);
                assertThat(a2).isEqualTo(81);
                assertThat(a3).isEqualTo(82d);
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
        LBiObjDblFunction<Integer,Integer,Integer> function = sutO.then(thenFunction);
        Integer finalValue = function.apply(80,81,82d);

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
        LBiObjDblFunction<Integer,Integer,Integer> sutO = (a1,a2,a3) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(80);
                assertThat(a2).isEqualTo(81);
                assertThat(a3).isEqualTo(82d);
                return 90;
        };

        LConsumer<Integer> thenFunction = p -> {
                thenFunctionCalled.set(true);
                // Integer
                assertThat(p).isEqualTo(90);
        };

        //when
        LBiObjDblConsumer<Integer,Integer> function = sutO.thenConsume(thenFunction);
        function.accept(80,81,82d);

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    @Test
    public void testThenToBool2() throws Throwable  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);

        //given (+ some assertions)
        LBiObjDblFunction<Integer,Integer,Integer> sutO = (a1,a2,a3) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(80);
                assertThat(a2).isEqualTo(81);
                assertThat(a3).isEqualTo(82d);
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
        LBiObjDblPredicate<Integer,Integer> function = sutO.thenToBool(thenFunction);
        boolean finalValue = function.test(80,81,82d);

        //then - finals
        assertThat(finalValue).isEqualTo(true);
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    // </editor-fold>


    @Test(expectedExceptions = RuntimeException.class)
    public void testShove() {

        // given
        LBiObjDblFunction<Integer,Integer,Integer> sutThrowing = LBiObjDblFunction.biObjDblFunc((a1,a2,a3) -> {
            throw new UnsupportedOperationException();
        });

        // when
        sutThrowing.shovingApply(100,100,100d);
    }


    @Test
    public void testToString() throws Throwable {

        assertThat(sut.toString())
                .isInstanceOf(String.class)
                .startsWith(this.getClass().getName()+"$");

        assertThat(String.format("%s", sut))
                .isInstanceOf(String.class)
                .contains("LBiObjDblFunction: R apply(T1 a1,T2 a2,double a3)");
    }


    @Test
    public void isThrowing() {
        assertThat(sut.isThrowing())
            .isFalse();
    }

    //<editor-fold desc="Variants">

    private Integer variantLObj0Dbl2Obj1Func(Integer a1,double a3,Integer a2) {
        return 100;
    }

    @Test
    public void compilerSubstituteVariantLObj0Dbl2Obj1Func() {
        LBiObjDblFunction lambda = LBiObjDblFunction./*<T1,T2,R>*/obj0Dbl2Obj1Func(this::variantLObj0Dbl2Obj1Func);

        assertThat(lambda).isInstanceOf(LBiObjDblFunction.LObj0Dbl2Obj1Func.class);
    }


    private Integer variantLObj1Obj0Dbl2Func(Integer a2,Integer a1,double a3) {
        return 100;
    }

    @Test
    public void compilerSubstituteVariantLObj1Obj0Dbl2Func() {
        LBiObjDblFunction lambda = LBiObjDblFunction./*<T1,T2,R>*/obj1Obj0Dbl2Func(this::variantLObj1Obj0Dbl2Func);

        assertThat(lambda).isInstanceOf(LBiObjDblFunction.LObj1Obj0Dbl2Func.class);
    }


    private Integer variantLObj1Dbl2Obj0Func(Integer a2,double a3,Integer a1) {
        return 100;
    }

    @Test
    public void compilerSubstituteVariantLObj1Dbl2Obj0Func() {
        LBiObjDblFunction lambda = LBiObjDblFunction./*<T1,T2,R>*/obj1Dbl2Obj0Func(this::variantLObj1Dbl2Obj0Func);

        assertThat(lambda).isInstanceOf(LBiObjDblFunction.LObj1Dbl2Obj0Func.class);
    }


    private Integer variantLDbl2Obj0Obj1Func(double a3,Integer a1,Integer a2) {
        return 100;
    }

    @Test
    public void compilerSubstituteVariantLDbl2Obj0Obj1Func() {
        LBiObjDblFunction lambda = LBiObjDblFunction./*<T1,T2,R>*/dbl2Obj0Obj1Func(this::variantLDbl2Obj0Obj1Func);

        assertThat(lambda).isInstanceOf(LBiObjDblFunction.LDbl2Obj0Obj1Func.class);
    }


    private Integer variantLDbl2Obj1Obj0Func(double a3,Integer a2,Integer a1) {
        return 100;
    }

    @Test
    public void compilerSubstituteVariantLDbl2Obj1Obj0Func() {
        LBiObjDblFunction lambda = LBiObjDblFunction./*<T1,T2,R>*/dbl2Obj1Obj0Func(this::variantLDbl2Obj1Obj0Func);

        assertThat(lambda).isInstanceOf(LBiObjDblFunction.LDbl2Obj1Obj0Func.class);
    }

    //</editor-fold>


    @Test void safeCompiles() {
        LBiObjDblFunction r1 = LBiObjDblFunction.safe(sut); //NOSONAR
    }

    @Test void safePropagates() {
        Object result = LBiObjDblFunction.safe(sut);
        assertThat(result).isSameAs(sut);
    }

    @Test void safeProtectsAgainstNpe() {
        Object result = LBiObjDblFunction.safe(null);
        assertThat(result).isSameAs(LBiObjDblFunction.biObjDblFunc(LBiObjDblFunction.safe()));
    }

    @Test  void safeSupplierPropagates() {
        LSupplier<LBiObjDblFunction<Integer,Integer,Integer>> supplier = ()->sut;
        Object result = LBiObjDblFunction.safeSupplier(supplier);
        assertThat(result).isSameAs(supplier);
    }

    @Test  void safeSupplierProtectsAgainstNpe() {
        Object result = LBiObjDblFunction.safeSupplier(null);
        assertThat(result).isSameAs(LBiObjDblFunction.safeSupplier());
    }

    @Test  void safeSupplierCompiles() {
        LSupplier<LBiObjDblFunction<Integer,Integer,Integer>> r1 = LBiObjDblFunction.safeSupplier(()->sut);  //NOSONAR
    }

}
