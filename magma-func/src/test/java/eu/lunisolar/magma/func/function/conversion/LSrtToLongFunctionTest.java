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
public class LSrtToLongFunctionTest {
    private static final String ORIGINAL_MESSAGE = "Original message";
    private static final String EXCEPTION_WAS_WRAPPED = "Exception was wrapped.";
    private static final String NO_EXCEPTION_WERE_THROWN = "No exception were thrown.";

    private long testValue = 100L;



    private LSrtToLongFunction sut = new LSrtToLongFunction(){
        public  long doApplyAsLongX(short a)  {
            return testValue;
        }
    };




    private LSrtToLongFunction sutAlwaysThrowing = LSrtToLongFunction.srtToLongFunc(a -> {
            throw new ParseException(ORIGINAL_MESSAGE, 0);
    });

    private LSrtToLongFunction sutAlwaysThrowingUnchecked = LSrtToLongFunction.srtToLongFunc(a -> {
            throw new IndexOutOfBoundsException(ORIGINAL_MESSAGE);
    });


    @Test
    public void testTheResult() throws Throwable {
        assertThat(sut.doApplyAsLong((short)100))
            .isEqualTo(testValue);
    }

    @Test
    public void testTupleCall() throws Throwable {

        LSrtSingle domainObject = Tuple4U.srtSingle((short)100);

        Object result = sut.tupleApplyAsLong(domainObject);

        assertThat(result)
            .isEqualTo(testValue);
    }

    @Test
    public void testNonNullDoApplyAsLong() throws Throwable {
        assertThat(sut.nonNullDoApplyAsLong((short)100))
            .isEqualTo(testValue);
    }

    @Test
    public void testNestingDoApplyAsLongUnchecked() throws Throwable {

        // then
        try {
            sutAlwaysThrowingUnchecked.nestingDoApplyAsLong((short)100);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                    .hasNoCause()
                    .hasMessage(ORIGINAL_MESSAGE);
        }
    }

    @Test
    public void testShovingDoApplyAsLongUnchecked() throws Throwable {

        // then
        try {
            sutAlwaysThrowingUnchecked.shovingDoApplyAsLong((short)100);
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
            .isEqualTo("LSrtToLongFunction: long doApplyAsLong(short a)");
    }

    @Test
    public void testSrtToLongFuncMethod() throws Throwable {
        assertThat(LSrtToLongFunction.srtToLongFunc(a -> testValue ))
            .isInstanceOf(LSrtToLongFunction.class);
    }






    // <editor-fold desc="compose (functional)">

    @Test
    public void testSrtToLongFuncComposeSrt() throws Throwable {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LSrtToLongFunction sutO = a -> {
                mainFunctionCalled.set(true);
                assertThat(a).isEqualTo((short)90);
                return 100L;
        };

        LSrtUnaryOperator before = p0 -> {
            assertThat(p0).isEqualTo((short)80);
            beforeCalls.incrementAndGet();
            return (short)90;
        };

        //when
        LSrtToLongFunction function = sutO.srtToLongFuncComposeSrt(before);
        function.doApplyAsLong((short)80);

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(beforeCalls.get()).isEqualTo(1);
    }


    @Test
    public void testSrtToLongFuncCompose() throws Throwable {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LSrtToLongFunction sutO = a -> {
                mainFunctionCalled.set(true);
                assertThat(a).isEqualTo((short)90);
                return 100L;
        };

        LToSrtFunction<Integer> before = p0 -> {
            assertThat(p0).isEqualTo(80);
            beforeCalls.incrementAndGet();
            return (short)90;
        };

        //when
        LToLongFunction<Integer> function = sutO.srtToLongFuncCompose(before);
        function.doApplyAsLong(80);

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
        LSrtToLongFunction sutO = a -> {
                mainFunctionCalled.set(true);
                assertThat(a).isEqualTo((short)80);
                return 90L;
        };

        LLongFunction<Integer> thenFunction = p -> {
                thenFunctionCalled.set(true);
                // long
                assertThat(p).isEqualTo(90L);
                // Integer
                return 100;
        };

        //when
        LSrtFunction<Integer> function = sutO.then(thenFunction);
        Integer finalValue = function.doApply((short)80);

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
        LSrtToLongFunction sutO = a -> {
                mainFunctionCalled.set(true);
                assertThat(a).isEqualTo((short)80);
                return 90L;
        };

        LLongToByteFunction thenFunction = p -> {
                thenFunctionCalled.set(true);
                // long
                assertThat(p).isEqualTo(90L);
                // byte
                return (byte)100;
        };

        //when
        LSrtToByteFunction function = sutO.thenToByte(thenFunction);
        byte finalValue = function.doApplyAsByte((short)80);

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
        LSrtToLongFunction sutO = a -> {
                mainFunctionCalled.set(true);
                assertThat(a).isEqualTo((short)80);
                return 90L;
        };

        LLongToSrtFunction thenFunction = p -> {
                thenFunctionCalled.set(true);
                // long
                assertThat(p).isEqualTo(90L);
                // short
                return (short)100;
        };

        //when
        LSrtUnaryOperator function = sutO.thenToSrt(thenFunction);
        short finalValue = function.doApplyAsSrt((short)80);

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
        LSrtToLongFunction sutO = a -> {
                mainFunctionCalled.set(true);
                assertThat(a).isEqualTo((short)80);
                return 90L;
        };

        LLongToIntFunction thenFunction = p -> {
                thenFunctionCalled.set(true);
                // long
                assertThat(p).isEqualTo(90L);
                // int
                return 100;
        };

        //when
        LSrtToIntFunction function = sutO.thenToInt(thenFunction);
        int finalValue = function.doApplyAsInt((short)80);

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
        LSrtToLongFunction sutO = a -> {
                mainFunctionCalled.set(true);
                assertThat(a).isEqualTo((short)80);
                return 90L;
        };

        LLongUnaryOperator thenFunction = p -> {
                thenFunctionCalled.set(true);
                // long
                assertThat(p).isEqualTo(90L);
                // long
                return 100L;
        };

        //when
        LSrtToLongFunction function = sutO.thenToLong(thenFunction);
        long finalValue = function.doApplyAsLong((short)80);

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
        LSrtToLongFunction sutO = a -> {
                mainFunctionCalled.set(true);
                assertThat(a).isEqualTo((short)80);
                return 90L;
        };

        LLongToFltFunction thenFunction = p -> {
                thenFunctionCalled.set(true);
                // long
                assertThat(p).isEqualTo(90L);
                // float
                return 100f;
        };

        //when
        LSrtToFltFunction function = sutO.thenToFlt(thenFunction);
        float finalValue = function.doApplyAsFlt((short)80);

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
        LSrtToLongFunction sutO = a -> {
                mainFunctionCalled.set(true);
                assertThat(a).isEqualTo((short)80);
                return 90L;
        };

        LLongToDblFunction thenFunction = p -> {
                thenFunctionCalled.set(true);
                // long
                assertThat(p).isEqualTo(90L);
                // double
                return 100d;
        };

        //when
        LSrtToDblFunction function = sutO.thenToDbl(thenFunction);
        double finalValue = function.doApplyAsDbl((short)80);

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
        LSrtToLongFunction sutO = a -> {
                mainFunctionCalled.set(true);
                assertThat(a).isEqualTo((short)80);
                return 90L;
        };

        LLongToCharFunction thenFunction = p -> {
                thenFunctionCalled.set(true);
                // long
                assertThat(p).isEqualTo(90L);
                // char
                return '\u0100';
        };

        //when
        LSrtToCharFunction function = sutO.thenToChar(thenFunction);
        char finalValue = function.doApplyAsChar((short)80);

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
        LSrtToLongFunction sutO = a -> {
                mainFunctionCalled.set(true);
                assertThat(a).isEqualTo((short)80);
                return 90L;
        };

        LLongPredicate thenFunction = p -> {
                thenFunctionCalled.set(true);
                // long
                assertThat(p).isEqualTo(90L);
                // boolean
                return true;
        };

        //when
        LSrtPredicate function = sutO.thenToBool(thenFunction);
        boolean finalValue = function.doTest((short)80);

        //then - finals
        assertThat(finalValue).isEqualTo(true);
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    // </editor-fold>

    @Test
    public void testNesting() {
        assertThat(sut.nestingSrtToLongFunc())
            .isSameAs(sut)
            .isInstanceOf(LSrtToLongFunction.class);
    }

    @Test
    public void testShoving() {
        assertThat(sut.shovingSrtToLongFunc())
            .isSameAs(sut)
            .isInstanceOf(LSrtToLongFunction.class);
    }


    @Test(expectedExceptions = RuntimeException.class)
    public void testShove() {

        // given
        LSrtToLongFunction sutThrowing = LSrtToLongFunction.srtToLongFunc(a -> {
            throw new UnsupportedOperationException();
        });

        // when
        sutThrowing.shovingSrtToLongFunc().doApplyAsLong((short)100);
    }


    @Test
    public void testToString() throws Throwable {

        assertThat(sut.toString())
                .isInstanceOf(String.class)
                .startsWith(this.getClass().getName()+"$");

        assertThat(String.format("%s", sut))
                .isInstanceOf(String.class)
                .contains("LSrtToLongFunction: long doApplyAsLong(short a)");
    }


    @Test
    public void isThrowing() {
        assertThat(sut.isThrowing())
            .isFalse();
    }

    @Test void safeCompiles() {
        LSrtToLongFunction r1 = LSrtToLongFunction.safe(sut); //NOSONAR
    }

    @Test void safePropagates() {
        Object result = LSrtToLongFunction.safe(sut);
        assertThat(result).isSameAs(sut);
    }

    @Test void safeProtectsAgainstNpe() {
        Object result = LSrtToLongFunction.safe(null);
        assertThat(result).isSameAs(LSrtToLongFunction.srtToLongFunc(LSrtToLongFunction.safe()));
    }

    @Test  void safeSupplierPropagates() {
        LSupplier<LSrtToLongFunction> supplier = ()->sut;
        Object result = LSrtToLongFunction.safeSupplier(supplier);
        assertThat(result).isSameAs(supplier);
    }

    @Test  void safeSupplierProtectsAgainstNpe() {
        Object result = LSrtToLongFunction.safeSupplier(null);
        assertThat(result).isSameAs(LSrtToLongFunction.safeSupplier());
    }

    @Test  void safeSupplierCompiles() {
        LSupplier<LSrtToLongFunction> r1 = LSrtToLongFunction.safeSupplier(()->sut);  //NOSONAR
    }

}
