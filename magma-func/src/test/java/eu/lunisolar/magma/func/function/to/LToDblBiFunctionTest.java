/*
 * This file is part of "lunisolar-magma".
 *
 * (C) Copyright 2014-2019 Lunisolar (http://lunisolar.eu/).
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
public class LToDblBiFunctionTest<T1,T2> {
    private static final String ORIGINAL_MESSAGE = "Original message";
    private static final String EXCEPTION_WAS_WRAPPED = "Exception was wrapped.";
    private static final String NO_EXCEPTION_WERE_THROWN = "No exception were thrown.";

    private double testValue = 100d;



    private LToDblBiFunction<Integer,Integer> sut = new LToDblBiFunction<Integer,Integer>(){
        public  double applyAsDblX(Integer a1,Integer a2)  {
            return testValue;
        }
    };



    private ToDoubleBiFunction<Integer,Integer> jre = (a1,a2) -> testValue;


    private LToDblBiFunction<Integer,Integer> sutAlwaysThrowing = LToDblBiFunction.toDblBiFunc((a1,a2) -> {
            throw new ParseException(ORIGINAL_MESSAGE, 0);
    });

    private LToDblBiFunction<Integer,Integer> sutAlwaysThrowingUnchecked = LToDblBiFunction.toDblBiFunc((a1,a2) -> {
            throw new IndexOutOfBoundsException(ORIGINAL_MESSAGE);
    });


    @Test
    public void testTheResult() throws Throwable {
        assertThat(sut.applyAsDbl(100,100))
            .isEqualTo(testValue);
    }

    @Test
    public void testTupleCall() throws Throwable {

        LPair<Integer,Integer> domainObject = Tuple4U.pair(100,100);

        Object result = sut.tupleApplyAsDbl(domainObject);

        assertThat(result)
            .isEqualTo(testValue);
    }

    @Test
    public void testNonNullApplyAsDbl() throws Throwable {
        assertThat(sut.nonNullApplyAsDbl(100,100))
            .isEqualTo(testValue);
    }

    @Test
    public void testNestingApplyAsDblUnchecked() throws Throwable {

        // then
        try {
            sutAlwaysThrowingUnchecked.nestingApplyAsDbl(100,100);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                    .hasNoCause()
                    .hasMessage(ORIGINAL_MESSAGE);
        }
    }

    @Test
    public void testShovingApplyAsDblUnchecked() throws Throwable {

        // then
        try {
            sutAlwaysThrowingUnchecked.shovingApplyAsDbl(100,100);
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
            .isEqualTo("LToDblBiFunction: double applyAsDbl(T1 a1,T2 a2)");
    }

    @Test
    public void testToDblBiFuncMethod() throws Throwable {
        assertThat(LToDblBiFunction.toDblBiFunc((a1,a2) -> testValue ))
            .isInstanceOf(LToDblBiFunction.class);
    }


    @Test
    public void testWrapStdMethod() throws Throwable {
        assertThat(LToDblBiFunction.wrap(jre))
            .isInstanceOf(LToDblBiFunction.class);
    }





    // <editor-fold desc="compose (functional)">

    @Test
    public void testCompose() throws Throwable {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LToDblBiFunction<Integer,Integer> sutO = (a1,a2) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(90);
                assertThat(a2).isEqualTo(91);
                return 100d;
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

        //when
        LToDblBiFunction<Integer,Integer> function = sutO.compose(before1,before2);
        function.applyAsDbl(80,81);

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
        LToDblBiFunction<Integer,Integer> sutO = (a1,a2) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(80);
                assertThat(a2).isEqualTo(81);
                return 90d;
        };

        LDblFunction<Integer> thenFunction = p -> {
                thenFunctionCalled.set(true);
                // double
                assertThat(p).isEqualTo(90d);
                // Integer
                return 100;
        };

        //when
        LBiFunction<Integer,Integer,Integer> function = sutO.then(thenFunction);
        Integer finalValue = function.apply(80,81);

        //then - finals
        assertThat(finalValue).isEqualTo(100);
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    @Test
    public void testThenToByte1() throws Throwable  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);

        //given (+ some assertions)
        LToDblBiFunction<Integer,Integer> sutO = (a1,a2) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(80);
                assertThat(a2).isEqualTo(81);
                return 90d;
        };

        LDblToByteFunction thenFunction = p -> {
                thenFunctionCalled.set(true);
                // double
                assertThat(p).isEqualTo(90d);
                // byte
                return (byte)100;
        };

        //when
        LToByteBiFunction<Integer,Integer> function = sutO.thenToByte(thenFunction);
        byte finalValue = function.applyAsByte(80,81);

        //then - finals
        assertThat(finalValue).isEqualTo((byte)100);
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    @Test
    public void testThenToSrt2() throws Throwable  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);

        //given (+ some assertions)
        LToDblBiFunction<Integer,Integer> sutO = (a1,a2) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(80);
                assertThat(a2).isEqualTo(81);
                return 90d;
        };

        LDblToSrtFunction thenFunction = p -> {
                thenFunctionCalled.set(true);
                // double
                assertThat(p).isEqualTo(90d);
                // short
                return (short)100;
        };

        //when
        LToSrtBiFunction<Integer,Integer> function = sutO.thenToSrt(thenFunction);
        short finalValue = function.applyAsSrt(80,81);

        //then - finals
        assertThat(finalValue).isEqualTo((short)100);
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    @Test
    public void testThenToInt3() throws Throwable  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);

        //given (+ some assertions)
        LToDblBiFunction<Integer,Integer> sutO = (a1,a2) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(80);
                assertThat(a2).isEqualTo(81);
                return 90d;
        };

        LDblToIntFunction thenFunction = p -> {
                thenFunctionCalled.set(true);
                // double
                assertThat(p).isEqualTo(90d);
                // int
                return 100;
        };

        //when
        LToIntBiFunction<Integer,Integer> function = sutO.thenToInt(thenFunction);
        int finalValue = function.applyAsInt(80,81);

        //then - finals
        assertThat(finalValue).isEqualTo(100);
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    @Test
    public void testThenToLong4() throws Throwable  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);

        //given (+ some assertions)
        LToDblBiFunction<Integer,Integer> sutO = (a1,a2) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(80);
                assertThat(a2).isEqualTo(81);
                return 90d;
        };

        LDblToLongFunction thenFunction = p -> {
                thenFunctionCalled.set(true);
                // double
                assertThat(p).isEqualTo(90d);
                // long
                return 100L;
        };

        //when
        LToLongBiFunction<Integer,Integer> function = sutO.thenToLong(thenFunction);
        long finalValue = function.applyAsLong(80,81);

        //then - finals
        assertThat(finalValue).isEqualTo(100L);
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    @Test
    public void testThenToFlt5() throws Throwable  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);

        //given (+ some assertions)
        LToDblBiFunction<Integer,Integer> sutO = (a1,a2) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(80);
                assertThat(a2).isEqualTo(81);
                return 90d;
        };

        LDblToFltFunction thenFunction = p -> {
                thenFunctionCalled.set(true);
                // double
                assertThat(p).isEqualTo(90d);
                // float
                return 100f;
        };

        //when
        LToFltBiFunction<Integer,Integer> function = sutO.thenToFlt(thenFunction);
        float finalValue = function.applyAsFlt(80,81);

        //then - finals
        assertThat(finalValue).isEqualTo(100f);
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    @Test
    public void testThenToDbl6() throws Throwable  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);

        //given (+ some assertions)
        LToDblBiFunction<Integer,Integer> sutO = (a1,a2) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(80);
                assertThat(a2).isEqualTo(81);
                return 90d;
        };

        LDblUnaryOperator thenFunction = p -> {
                thenFunctionCalled.set(true);
                // double
                assertThat(p).isEqualTo(90d);
                // double
                return 100d;
        };

        //when
        LToDblBiFunction<Integer,Integer> function = sutO.thenToDbl(thenFunction);
        double finalValue = function.applyAsDbl(80,81);

        //then - finals
        assertThat(finalValue).isEqualTo(100d);
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    @Test
    public void testThenToChar7() throws Throwable  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);

        //given (+ some assertions)
        LToDblBiFunction<Integer,Integer> sutO = (a1,a2) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(80);
                assertThat(a2).isEqualTo(81);
                return 90d;
        };

        LDblToCharFunction thenFunction = p -> {
                thenFunctionCalled.set(true);
                // double
                assertThat(p).isEqualTo(90d);
                // char
                return '\u0100';
        };

        //when
        LToCharBiFunction<Integer,Integer> function = sutO.thenToChar(thenFunction);
        char finalValue = function.applyAsChar(80,81);

        //then - finals
        assertThat(finalValue).isEqualTo('\u0100');
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    @Test
    public void testThenToBool8() throws Throwable  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);

        //given (+ some assertions)
        LToDblBiFunction<Integer,Integer> sutO = (a1,a2) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(80);
                assertThat(a2).isEqualTo(81);
                return 90d;
        };

        LDblPredicate thenFunction = p -> {
                thenFunctionCalled.set(true);
                // double
                assertThat(p).isEqualTo(90d);
                // boolean
                return true;
        };

        //when
        LBiPredicate<Integer,Integer> function = sutO.thenToBool(thenFunction);
        boolean finalValue = function.test(80,81);

        //then - finals
        assertThat(finalValue).isEqualTo(true);
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    // </editor-fold>


    @Test(expectedExceptions = RuntimeException.class)
    public void testShove() {

        // given
        LToDblBiFunction<Integer,Integer> sutThrowing = LToDblBiFunction.toDblBiFunc((a1,a2) -> {
            throw new UnsupportedOperationException();
        });

        // when
        sutThrowing.shovingApplyAsDbl(100,100);
    }


    @Test
    public void testToString() throws Throwable {

        assertThat(sut.toString())
                .isInstanceOf(String.class)
                .startsWith(this.getClass().getName()+"$");

        assertThat(String.format("%s", sut))
                .isInstanceOf(String.class)
                .contains("LToDblBiFunction: double applyAsDbl(T1 a1,T2 a2)");
    }


    @Test
    public void isThrowing() {
        assertThat(sut.isThrowing())
            .isFalse();
    }

    //<editor-fold desc="Variants">

    private double variantLToDblObj1Obj0Func(Integer a2,Integer a1) {
        return 100d;
    }

    @Test
    public void compilerSubstituteVariantLToDblObj1Obj0Func() {
        LToDblBiFunction lambda = LToDblBiFunction./*<T1,T2>*/toDblObj1Obj0Func(this::variantLToDblObj1Obj0Func);

        assertThat(lambda).isInstanceOf(LToDblBiFunction.LToDblObj1Obj0Func.class);
    }

    //</editor-fold>


    @Test void safeCompiles() {
        LToDblBiFunction r1 = LToDblBiFunction.safe(sut); //NOSONAR
        ToDoubleBiFunction r3 = LToDblBiFunction.safe(sut); //NOSONAR
    }

    @Test void safePropagates() {
        Object result = LToDblBiFunction.safe(sut);
        assertThat(result).isSameAs(sut);
    }

    @Test void safeProtectsAgainstNpe() {
        Object result = LToDblBiFunction.safe(null);
        assertThat(result).isSameAs(LToDblBiFunction.toDblBiFunc(LToDblBiFunction.safe()));
    }

    @Test  void safeSupplierPropagates() {
        LSupplier<LToDblBiFunction<Integer,Integer>> supplier = ()->sut;
        Object result = LToDblBiFunction.safeSupplier(supplier);
        assertThat(result).isSameAs(supplier);
    }

    @Test  void safeSupplierProtectsAgainstNpe() {
        Object result = LToDblBiFunction.safeSupplier(null);
        assertThat(result).isSameAs(LToDblBiFunction.safeSupplier());
    }

    @Test  void safeSupplierCompiles() {
        LSupplier<LToDblBiFunction<Integer,Integer>> r1 = LToDblBiFunction.safeSupplier(()->sut);  //NOSONAR
        Supplier<LToDblBiFunction<Integer,Integer>> r2 = LToDblBiFunction.safeSupplier(()->sut); //NOSONAR
    }

}