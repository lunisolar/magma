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
public class LLongToFltFunctionTest {
    private static final String ORIGINAL_MESSAGE = "Original message";
    private static final String EXCEPTION_WAS_WRAPPED = "Exception was wrapped.";
    private static final String NO_EXCEPTION_WERE_THROWN = "No exception were thrown.";

    private float testValue = 100f;



    private LLongToFltFunction sut = new LLongToFltFunction(){
        public  float applyAsFltX(long a)  {
            return testValue;
        }
    };




    private LLongToFltFunction sutAlwaysThrowing = LLongToFltFunction.longToFltFunc(a -> {
            throw new ParseException(ORIGINAL_MESSAGE, 0);
    });

    private LLongToFltFunction sutAlwaysThrowingUnchecked = LLongToFltFunction.longToFltFunc(a -> {
            throw new IndexOutOfBoundsException(ORIGINAL_MESSAGE);
    });


    @Test
    public void testTheResult() throws Throwable {
        assertThat(sut.applyAsFlt(100L))
            .isEqualTo(testValue);
    }

    @Test
    public void testTupleCall() throws Throwable {

        LLongSingle domainObject = Tuple4U.longSingle(100L);

        Object result = sut.tupleApplyAsFlt(domainObject);

        assertThat(result)
            .isEqualTo(testValue);
    }

    @Test
    public void testNonNullApplyAsFlt() throws Throwable {
        assertThat(sut.nonNullApplyAsFlt(100L))
            .isEqualTo(testValue);
    }

    @Test
    public void testNestingApplyAsFltUnchecked() throws Throwable {

        // then
        try {
            sutAlwaysThrowingUnchecked.nestingApplyAsFlt(100L);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                    .hasNoCause()
                    .hasMessage(ORIGINAL_MESSAGE);
        }
    }

    @Test
    public void testShovingApplyAsFltUnchecked() throws Throwable {

        // then
        try {
            sutAlwaysThrowingUnchecked.shovingApplyAsFlt(100L);
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
            .isEqualTo("LLongToFltFunction: float applyAsFlt(long a)");
    }

    @Test
    public void testLongToFltFuncMethod() throws Throwable {
        assertThat(LLongToFltFunction.longToFltFunc(a -> testValue ))
            .isInstanceOf(LLongToFltFunction.class);
    }






    // <editor-fold desc="compose (functional)">

    @Test
    public void testCompose() throws Throwable {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LLongToFltFunction sutO = a -> {
                mainFunctionCalled.set(true);
                assertThat(a).isEqualTo(90L);
                return 100f;
        };

        LLongUnaryOperator before = p0 -> {
            assertThat(p0).isEqualTo(80L);
            beforeCalls.incrementAndGet();
            return 90L;
        };

        //when
        LLongToFltFunction function = sutO.compose(before);
        function.applyAsFlt(80L);

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(beforeCalls.get()).isEqualTo(1);
    }


    @Test
    public void testLongToFltFuncCompose() throws Throwable {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LLongToFltFunction sutO = a -> {
                mainFunctionCalled.set(true);
                assertThat(a).isEqualTo(90L);
                return 100f;
        };

        LToLongFunction<Integer> before = p0 -> {
            assertThat(p0).isEqualTo(80);
            beforeCalls.incrementAndGet();
            return 90L;
        };

        //when
        LToFltFunction<Integer> function = sutO.longToFltFuncCompose(before);
        function.applyAsFlt(80);

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
        LLongToFltFunction sutO = a -> {
                mainFunctionCalled.set(true);
                assertThat(a).isEqualTo(80L);
                return 90f;
        };

        LFltFunction<Integer> thenFunction = p -> {
                thenFunctionCalled.set(true);
                // float
                assertThat(p).isEqualTo(90f);
                // Integer
                return 100;
        };

        //when
        LLongFunction<Integer> function = sutO.then(thenFunction);
        Integer finalValue = function.apply(80L);

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
        LLongToFltFunction sutO = a -> {
                mainFunctionCalled.set(true);
                assertThat(a).isEqualTo(80L);
                return 90f;
        };

        LFltToByteFunction thenFunction = p -> {
                thenFunctionCalled.set(true);
                // float
                assertThat(p).isEqualTo(90f);
                // byte
                return (byte)100;
        };

        //when
        LLongToByteFunction function = sutO.thenToByte(thenFunction);
        byte finalValue = function.applyAsByte(80L);

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
        LLongToFltFunction sutO = a -> {
                mainFunctionCalled.set(true);
                assertThat(a).isEqualTo(80L);
                return 90f;
        };

        LFltToSrtFunction thenFunction = p -> {
                thenFunctionCalled.set(true);
                // float
                assertThat(p).isEqualTo(90f);
                // short
                return (short)100;
        };

        //when
        LLongToSrtFunction function = sutO.thenToSrt(thenFunction);
        short finalValue = function.applyAsSrt(80L);

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
        LLongToFltFunction sutO = a -> {
                mainFunctionCalled.set(true);
                assertThat(a).isEqualTo(80L);
                return 90f;
        };

        LFltToIntFunction thenFunction = p -> {
                thenFunctionCalled.set(true);
                // float
                assertThat(p).isEqualTo(90f);
                // int
                return 100;
        };

        //when
        LLongToIntFunction function = sutO.thenToInt(thenFunction);
        int finalValue = function.applyAsInt(80L);

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
        LLongToFltFunction sutO = a -> {
                mainFunctionCalled.set(true);
                assertThat(a).isEqualTo(80L);
                return 90f;
        };

        LFltToLongFunction thenFunction = p -> {
                thenFunctionCalled.set(true);
                // float
                assertThat(p).isEqualTo(90f);
                // long
                return 100L;
        };

        //when
        LLongUnaryOperator function = sutO.thenToLong(thenFunction);
        long finalValue = function.applyAsLong(80L);

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
        LLongToFltFunction sutO = a -> {
                mainFunctionCalled.set(true);
                assertThat(a).isEqualTo(80L);
                return 90f;
        };

        LFltUnaryOperator thenFunction = p -> {
                thenFunctionCalled.set(true);
                // float
                assertThat(p).isEqualTo(90f);
                // float
                return 100f;
        };

        //when
        LLongToFltFunction function = sutO.thenToFlt(thenFunction);
        float finalValue = function.applyAsFlt(80L);

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
        LLongToFltFunction sutO = a -> {
                mainFunctionCalled.set(true);
                assertThat(a).isEqualTo(80L);
                return 90f;
        };

        LFltToDblFunction thenFunction = p -> {
                thenFunctionCalled.set(true);
                // float
                assertThat(p).isEqualTo(90f);
                // double
                return 100d;
        };

        //when
        LLongToDblFunction function = sutO.thenToDbl(thenFunction);
        double finalValue = function.applyAsDbl(80L);

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
        LLongToFltFunction sutO = a -> {
                mainFunctionCalled.set(true);
                assertThat(a).isEqualTo(80L);
                return 90f;
        };

        LFltToCharFunction thenFunction = p -> {
                thenFunctionCalled.set(true);
                // float
                assertThat(p).isEqualTo(90f);
                // char
                return '\u0100';
        };

        //when
        LLongToCharFunction function = sutO.thenToChar(thenFunction);
        char finalValue = function.applyAsChar(80L);

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
        LLongToFltFunction sutO = a -> {
                mainFunctionCalled.set(true);
                assertThat(a).isEqualTo(80L);
                return 90f;
        };

        LFltPredicate thenFunction = p -> {
                thenFunctionCalled.set(true);
                // float
                assertThat(p).isEqualTo(90f);
                // boolean
                return true;
        };

        //when
        LLongPredicate function = sutO.thenToBool(thenFunction);
        boolean finalValue = function.test(80L);

        //then - finals
        assertThat(finalValue).isEqualTo(true);
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    // </editor-fold>


    @Test(expectedExceptions = RuntimeException.class)
    public void testShove() {

        // given
        LLongToFltFunction sutThrowing = LLongToFltFunction.longToFltFunc(a -> {
            throw new UnsupportedOperationException();
        });

        // when
        sutThrowing.shovingApplyAsFlt(100L);
    }


    @Test
    public void testToString() throws Throwable {

        assertThat(sut.toString())
                .isInstanceOf(String.class)
                .startsWith(this.getClass().getName()+"$");

        assertThat(String.format("%s", sut))
                .isInstanceOf(String.class)
                .contains("LLongToFltFunction: float applyAsFlt(long a)");
    }


    @Test
    public void isThrowing() {
        assertThat(sut.isThrowing())
            .isFalse();
    }

    @Test void safeCompiles() {
        LLongToFltFunction r1 = LLongToFltFunction.safe(sut); //NOSONAR
    }

    @Test void safePropagates() {
        Object result = LLongToFltFunction.safe(sut);
        assertThat(result).isSameAs(sut);
    }

    @Test void safeProtectsAgainstNpe() {
        Object result = LLongToFltFunction.safe(null);
        assertThat(result).isSameAs(LLongToFltFunction.longToFltFunc(LLongToFltFunction.safe()));
    }

    @Test  void safeSupplierPropagates() {
        LSupplier<LLongToFltFunction> supplier = ()->sut;
        Object result = LLongToFltFunction.safeSupplier(supplier);
        assertThat(result).isSameAs(supplier);
    }

    @Test  void safeSupplierProtectsAgainstNpe() {
        Object result = LLongToFltFunction.safeSupplier(null);
        assertThat(result).isSameAs(LLongToFltFunction.safeSupplier());
    }

    @Test  void safeSupplierCompiles() {
        LSupplier<LLongToFltFunction> r1 = LLongToFltFunction.safeSupplier(()->sut);  //NOSONAR
    }

}
