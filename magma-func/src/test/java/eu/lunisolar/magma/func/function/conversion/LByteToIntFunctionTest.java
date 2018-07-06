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
public class LByteToIntFunctionTest {
    private static final String ORIGINAL_MESSAGE = "Original message";
    private static final String EXCEPTION_WAS_WRAPPED = "Exception was wrapped.";
    private static final String NO_EXCEPTION_WERE_THROWN = "No exception were thrown.";

    private int testValue = 100;



    private LByteToIntFunction sut = new LByteToIntFunction(){
        public  int doApplyAsIntX(byte a)  {
            return testValue;
        }
    };




    private LByteToIntFunction sutAlwaysThrowing = LByteToIntFunction.byteToIntFunc(a -> {
            throw new ParseException(ORIGINAL_MESSAGE, 0);
    });

    private LByteToIntFunction sutAlwaysThrowingUnchecked = LByteToIntFunction.byteToIntFunc(a -> {
            throw new IndexOutOfBoundsException(ORIGINAL_MESSAGE);
    });


    @Test
    public void testTheResult() throws Throwable {
        assertThat(sut.doApplyAsInt((byte)100))
            .isEqualTo(testValue);
    }

    @Test
    public void testTupleCall() throws Throwable {

        LByteSingle domainObject = Tuple4U.byteSingle((byte)100);

        Object result = sut.tupleApplyAsInt(domainObject);

        assertThat(result)
            .isEqualTo(testValue);
    }

    @Test
    public void testNonNullDoApplyAsInt() throws Throwable {
        assertThat(sut.nonNullDoApplyAsInt((byte)100))
            .isEqualTo(testValue);
    }

    @Test
    public void testNestingDoApplyAsIntUnchecked() throws Throwable {

        // then
        try {
            sutAlwaysThrowingUnchecked.nestingDoApplyAsInt((byte)100);
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
            sutAlwaysThrowingUnchecked.shovingDoApplyAsInt((byte)100);
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
            .isEqualTo("LByteToIntFunction: int doApplyAsInt(byte a)");
    }

    @Test
    public void testByteToIntFuncMethod() throws Throwable {
        assertThat(LByteToIntFunction.byteToIntFunc(a -> testValue ))
            .isInstanceOf(LByteToIntFunction.class);
    }






    // <editor-fold desc="compose (functional)">

    @Test
    public void testByteToIntFuncComposeByte() throws Throwable {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LByteToIntFunction sutO = a -> {
                mainFunctionCalled.set(true);
                assertThat(a).isEqualTo((byte)90);
                return 100;
        };

        LByteUnaryOperator before = p0 -> {
            assertThat(p0).isEqualTo((byte)80);
            beforeCalls.incrementAndGet();
            return (byte)90;
        };

        //when
        LByteToIntFunction function = sutO.byteToIntFuncComposeByte(before);
        function.doApplyAsInt((byte)80);

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(beforeCalls.get()).isEqualTo(1);
    }


    @Test
    public void testByteToIntFuncCompose() throws Throwable {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LByteToIntFunction sutO = a -> {
                mainFunctionCalled.set(true);
                assertThat(a).isEqualTo((byte)90);
                return 100;
        };

        LToByteFunction<Integer> before = p0 -> {
            assertThat(p0).isEqualTo(80);
            beforeCalls.incrementAndGet();
            return (byte)90;
        };

        //when
        LToIntFunction<Integer> function = sutO.byteToIntFuncCompose(before);
        function.doApplyAsInt(80);

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
        LByteToIntFunction sutO = a -> {
                mainFunctionCalled.set(true);
                assertThat(a).isEqualTo((byte)80);
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
        LByteFunction<Integer> function = sutO.then(thenFunction);
        Integer finalValue = function.doApply((byte)80);

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
        LByteToIntFunction sutO = a -> {
                mainFunctionCalled.set(true);
                assertThat(a).isEqualTo((byte)80);
                return 90;
        };

        LIntToByteFunction thenFunction = p -> {
                thenFunctionCalled.set(true);
                // int
                assertThat(p).isEqualTo(90);
                // byte
                return (byte)100;
        };

        //when
        LByteUnaryOperator function = sutO.thenToByte(thenFunction);
        byte finalValue = function.doApplyAsByte((byte)80);

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
        LByteToIntFunction sutO = a -> {
                mainFunctionCalled.set(true);
                assertThat(a).isEqualTo((byte)80);
                return 90;
        };

        LIntToSrtFunction thenFunction = p -> {
                thenFunctionCalled.set(true);
                // int
                assertThat(p).isEqualTo(90);
                // short
                return (short)100;
        };

        //when
        LByteToSrtFunction function = sutO.thenToSrt(thenFunction);
        short finalValue = function.doApplyAsSrt((byte)80);

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
        LByteToIntFunction sutO = a -> {
                mainFunctionCalled.set(true);
                assertThat(a).isEqualTo((byte)80);
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
        LByteToIntFunction function = sutO.thenToInt(thenFunction);
        int finalValue = function.doApplyAsInt((byte)80);

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
        LByteToIntFunction sutO = a -> {
                mainFunctionCalled.set(true);
                assertThat(a).isEqualTo((byte)80);
                return 90;
        };

        LIntToLongFunction thenFunction = p -> {
                thenFunctionCalled.set(true);
                // int
                assertThat(p).isEqualTo(90);
                // long
                return 100L;
        };

        //when
        LByteToLongFunction function = sutO.thenToLong(thenFunction);
        long finalValue = function.doApplyAsLong((byte)80);

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
        LByteToIntFunction sutO = a -> {
                mainFunctionCalled.set(true);
                assertThat(a).isEqualTo((byte)80);
                return 90;
        };

        LIntToFltFunction thenFunction = p -> {
                thenFunctionCalled.set(true);
                // int
                assertThat(p).isEqualTo(90);
                // float
                return 100f;
        };

        //when
        LByteToFltFunction function = sutO.thenToFlt(thenFunction);
        float finalValue = function.doApplyAsFlt((byte)80);

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
        LByteToIntFunction sutO = a -> {
                mainFunctionCalled.set(true);
                assertThat(a).isEqualTo((byte)80);
                return 90;
        };

        LIntToDblFunction thenFunction = p -> {
                thenFunctionCalled.set(true);
                // int
                assertThat(p).isEqualTo(90);
                // double
                return 100d;
        };

        //when
        LByteToDblFunction function = sutO.thenToDbl(thenFunction);
        double finalValue = function.doApplyAsDbl((byte)80);

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
        LByteToIntFunction sutO = a -> {
                mainFunctionCalled.set(true);
                assertThat(a).isEqualTo((byte)80);
                return 90;
        };

        LIntToCharFunction thenFunction = p -> {
                thenFunctionCalled.set(true);
                // int
                assertThat(p).isEqualTo(90);
                // char
                return '\u0100';
        };

        //when
        LByteToCharFunction function = sutO.thenToChar(thenFunction);
        char finalValue = function.doApplyAsChar((byte)80);

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
        LByteToIntFunction sutO = a -> {
                mainFunctionCalled.set(true);
                assertThat(a).isEqualTo((byte)80);
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
        LBytePredicate function = sutO.thenToBool(thenFunction);
        boolean finalValue = function.doTest((byte)80);

        //then - finals
        assertThat(finalValue).isEqualTo(true);
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    // </editor-fold>

    @Test
    public void testNesting() {
        assertThat(sut.nestingByteToIntFunc())
            .isSameAs(sut)
            .isInstanceOf(LByteToIntFunction.class);
    }

    @Test
    public void testShoving() {
        assertThat(sut.shovingByteToIntFunc())
            .isSameAs(sut)
            .isInstanceOf(LByteToIntFunction.class);
    }


    @Test(expectedExceptions = RuntimeException.class)
    public void testShove() {

        // given
        LByteToIntFunction sutThrowing = LByteToIntFunction.byteToIntFunc(a -> {
            throw new UnsupportedOperationException();
        });

        // when
        sutThrowing.shovingByteToIntFunc().doApplyAsInt((byte)100);
    }


    @Test
    public void testToString() throws Throwable {

        assertThat(sut.toString())
                .isInstanceOf(String.class)
                .startsWith(this.getClass().getName()+"$");

        assertThat(String.format("%s", sut))
                .isInstanceOf(String.class)
                .contains("LByteToIntFunction: int doApplyAsInt(byte a)");
    }


    @Test
    public void isThrowing() {
        assertThat(sut.isThrowing())
            .isFalse();
    }

    @Test void safeCompiles() {
        LByteToIntFunction r1 = LByteToIntFunction.safe(sut); //NOSONAR
    }

    @Test void safePropagates() {
        Object result = LByteToIntFunction.safe(sut);
        assertThat(result).isSameAs(sut);
    }

    @Test void safeProtectsAgainstNpe() {
        Object result = LByteToIntFunction.safe(null);
        assertThat(result).isSameAs(LByteToIntFunction.byteToIntFunc(LByteToIntFunction.safe()));
    }

    @Test  void safeSupplierPropagates() {
        LSupplier<LByteToIntFunction> supplier = ()->sut;
        Object result = LByteToIntFunction.safeSupplier(supplier);
        assertThat(result).isSameAs(supplier);
    }

    @Test  void safeSupplierProtectsAgainstNpe() {
        Object result = LByteToIntFunction.safeSupplier(null);
        assertThat(result).isSameAs(LByteToIntFunction.safeSupplier());
    }

    @Test  void safeSupplierCompiles() {
        LSupplier<LByteToIntFunction> r1 = LByteToIntFunction.safeSupplier(()->sut);  //NOSONAR
    }

}
