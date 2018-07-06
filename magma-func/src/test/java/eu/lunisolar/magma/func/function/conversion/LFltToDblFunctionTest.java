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

package eu.lunisolar.magma.func.function.conversion;

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
public class LFltToDblFunctionTest {
    private static final String ORIGINAL_MESSAGE = "Original message";
    private static final String EXCEPTION_WAS_WRAPPED = "Exception was wrapped.";
    private static final String NO_EXCEPTION_WERE_THROWN = "No exception were thrown.";

    private double testValue = 100d;



    private LFltToDblFunction sut = new LFltToDblFunction(){
        public  double doApplyAsDblX(float a)  {
            return testValue;
        }
    };




    private LFltToDblFunction sutAlwaysThrowing = LFltToDblFunction.fltToDblFunc(a -> {
            throw new ParseException(ORIGINAL_MESSAGE, 0);
    });

    private LFltToDblFunction sutAlwaysThrowingUnchecked = LFltToDblFunction.fltToDblFunc(a -> {
            throw new IndexOutOfBoundsException(ORIGINAL_MESSAGE);
    });


    @Test
    public void testTheResult() throws Throwable {
        assertThat(sut.doApplyAsDbl(100f))
            .isEqualTo(testValue);
    }

    @Test
    public void testTupleCall() throws Throwable {

        LFltSingle domainObject = Tuple4U.fltSingle(100f);

        Object result = sut.tupleApplyAsDbl(domainObject);

        assertThat(result)
            .isEqualTo(testValue);
    }

    @Test
    public void testNonNullDoApplyAsDbl() throws Throwable {
        assertThat(sut.nonNullDoApplyAsDbl(100f))
            .isEqualTo(testValue);
    }

    @Test
    public void testNestingDoApplyAsDblUnchecked() throws Throwable {

        // then
        try {
            sutAlwaysThrowingUnchecked.nestingDoApplyAsDbl(100f);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                    .hasNoCause()
                    .hasMessage(ORIGINAL_MESSAGE);
        }
    }

    @Test
    public void testShovingDoApplyAsDblUnchecked() throws Throwable {

        // then
        try {
            sutAlwaysThrowingUnchecked.shovingDoApplyAsDbl(100f);
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
            .isEqualTo("LFltToDblFunction: double doApplyAsDbl(float a)");
    }

    @Test
    public void testFltToDblFuncMethod() throws Throwable {
        assertThat(LFltToDblFunction.fltToDblFunc(a -> testValue ))
            .isInstanceOf(LFltToDblFunction.class);
    }






    // <editor-fold desc="compose (functional)">

    @Test
    public void testFltToDblFuncComposeFlt() throws Throwable {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LFltToDblFunction sutO = a -> {
                mainFunctionCalled.set(true);
                assertThat(a).isEqualTo(90f);
                return 100d;
        };

        LFltUnaryOperator before = p0 -> {
            assertThat(p0).isEqualTo(80f);
            beforeCalls.incrementAndGet();
            return 90f;
        };

        //when
        LFltToDblFunction function = sutO.fltToDblFuncComposeFlt(before);
        function.doApplyAsDbl(80f);

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(beforeCalls.get()).isEqualTo(1);
    }


    @Test
    public void testFltToDblFuncCompose() throws Throwable {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LFltToDblFunction sutO = a -> {
                mainFunctionCalled.set(true);
                assertThat(a).isEqualTo(90f);
                return 100d;
        };

        LToFltFunction<Integer> before = p0 -> {
            assertThat(p0).isEqualTo(80);
            beforeCalls.incrementAndGet();
            return 90f;
        };

        //when
        LToDblFunction<Integer> function = sutO.fltToDblFuncCompose(before);
        function.doApplyAsDbl(80);

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(beforeCalls.get()).isEqualTo(1);
    }

    // </editor-fold>



    // <editor-fold desc="then (functional)">

    @Test
    public void testThen0() throws Throwable  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);

        //given (+ some assertions)
        LFltToDblFunction sutO = a -> {
                mainFunctionCalled.set(true);
                assertThat(a).isEqualTo(80f);
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
        LFltFunction<Integer> function = sutO.then(thenFunction);
        Integer finalValue = function.doApply(80f);

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
        LFltToDblFunction sutO = a -> {
                mainFunctionCalled.set(true);
                assertThat(a).isEqualTo(80f);
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
        LFltToByteFunction function = sutO.thenToByte(thenFunction);
        byte finalValue = function.doApplyAsByte(80f);

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
        LFltToDblFunction sutO = a -> {
                mainFunctionCalled.set(true);
                assertThat(a).isEqualTo(80f);
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
        LFltToSrtFunction function = sutO.thenToSrt(thenFunction);
        short finalValue = function.doApplyAsSrt(80f);

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
        LFltToDblFunction sutO = a -> {
                mainFunctionCalled.set(true);
                assertThat(a).isEqualTo(80f);
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
        LFltToIntFunction function = sutO.thenToInt(thenFunction);
        int finalValue = function.doApplyAsInt(80f);

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
        LFltToDblFunction sutO = a -> {
                mainFunctionCalled.set(true);
                assertThat(a).isEqualTo(80f);
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
        LFltToLongFunction function = sutO.thenToLong(thenFunction);
        long finalValue = function.doApplyAsLong(80f);

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
        LFltToDblFunction sutO = a -> {
                mainFunctionCalled.set(true);
                assertThat(a).isEqualTo(80f);
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
        LFltUnaryOperator function = sutO.thenToFlt(thenFunction);
        float finalValue = function.doApplyAsFlt(80f);

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
        LFltToDblFunction sutO = a -> {
                mainFunctionCalled.set(true);
                assertThat(a).isEqualTo(80f);
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
        LFltToDblFunction function = sutO.thenToDbl(thenFunction);
        double finalValue = function.doApplyAsDbl(80f);

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
        LFltToDblFunction sutO = a -> {
                mainFunctionCalled.set(true);
                assertThat(a).isEqualTo(80f);
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
        LFltToCharFunction function = sutO.thenToChar(thenFunction);
        char finalValue = function.doApplyAsChar(80f);

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
        LFltToDblFunction sutO = a -> {
                mainFunctionCalled.set(true);
                assertThat(a).isEqualTo(80f);
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
        LFltPredicate function = sutO.thenToBool(thenFunction);
        boolean finalValue = function.doTest(80f);

        //then - finals
        assertThat(finalValue).isEqualTo(true);
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    // </editor-fold>

    @Test
    public void testNesting() {
        assertThat(sut.nestingFltToDblFunc())
            .isSameAs(sut)
            .isInstanceOf(LFltToDblFunction.class);
    }

    @Test
    public void testShoving() {
        assertThat(sut.shovingFltToDblFunc())
            .isSameAs(sut)
            .isInstanceOf(LFltToDblFunction.class);
    }


    @Test(expectedExceptions = RuntimeException.class)
    public void testShove() {

        // given
        LFltToDblFunction sutThrowing = LFltToDblFunction.fltToDblFunc(a -> {
            throw new UnsupportedOperationException();
        });

        // when
        sutThrowing.shovingFltToDblFunc().doApplyAsDbl(100f);
    }


    @Test
    public void testToString() throws Throwable {

        assertThat(sut.toString())
                .isInstanceOf(String.class)
                .startsWith(this.getClass().getName()+"$");

        assertThat(String.format("%s", sut))
                .isInstanceOf(String.class)
                .contains("LFltToDblFunction: double doApplyAsDbl(float a)");
    }


    @Test
    public void isThrowing() {
        assertThat(sut.isThrowing())
            .isFalse();
    }

    @Test void safeCompiles() {
        LFltToDblFunction r1 = LFltToDblFunction.safe(sut); //NOSONAR
    }

    @Test void safePropagates() {
        Object result = LFltToDblFunction.safe(sut);
        assertThat(result).isSameAs(sut);
    }

    @Test void safeProtectsAgainstNpe() {
        Object result = LFltToDblFunction.safe(null);
        assertThat(result).isSameAs(LFltToDblFunction.fltToDblFunc(LFltToDblFunction.safe()));
    }

    @Test  void safeSupplierPropagates() {
        LSupplier<LFltToDblFunction> supplier = ()->sut;
        Object result = LFltToDblFunction.safeSupplier(supplier);
        assertThat(result).isSameAs(supplier);
    }

    @Test  void safeSupplierProtectsAgainstNpe() {
        Object result = LFltToDblFunction.safeSupplier(null);
        assertThat(result).isSameAs(LFltToDblFunction.safeSupplier());
    }

    @Test  void safeSupplierCompiles() {
        LSupplier<LFltToDblFunction> r1 = LFltToDblFunction.safeSupplier(()->sut);  //NOSONAR
    }

}
