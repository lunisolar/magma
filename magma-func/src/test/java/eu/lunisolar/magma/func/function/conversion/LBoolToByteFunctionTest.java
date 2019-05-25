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
public class LBoolToByteFunctionTest {
    private static final String ORIGINAL_MESSAGE = "Original message";
    private static final String EXCEPTION_WAS_WRAPPED = "Exception was wrapped.";
    private static final String NO_EXCEPTION_WERE_THROWN = "No exception were thrown.";

    private byte testValue = (byte)100;



    private LBoolToByteFunction sut = new LBoolToByteFunction(){
        public  byte applyAsByteX(boolean a)  {
            return testValue;
        }
    };




    private LBoolToByteFunction sutAlwaysThrowing = LBoolToByteFunction.boolToByteFunc(a -> {
            throw new ParseException(ORIGINAL_MESSAGE, 0);
    });

    private LBoolToByteFunction sutAlwaysThrowingUnchecked = LBoolToByteFunction.boolToByteFunc(a -> {
            throw new IndexOutOfBoundsException(ORIGINAL_MESSAGE);
    });


    @Test
    public void testTheResult() throws Throwable {
        assertThat(sut.applyAsByte(true))
            .isEqualTo(testValue);
    }

    @Test
    public void testTupleCall() throws Throwable {

        LBoolSingle domainObject = Tuple4U.boolSingle(true);

        Object result = sut.tupleApplyAsByte(domainObject);

        assertThat(result)
            .isEqualTo(testValue);
    }

    @Test
    public void testNonNullApplyAsByte() throws Throwable {
        assertThat(sut.nonNullApplyAsByte(true))
            .isEqualTo(testValue);
    }

    @Test
    public void testNestingApplyAsByteUnchecked() throws Throwable {

        // then
        try {
            sutAlwaysThrowingUnchecked.nestingApplyAsByte(true);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                    .hasNoCause()
                    .hasMessage(ORIGINAL_MESSAGE);
        }
    }

    @Test
    public void testShovingApplyAsByteUnchecked() throws Throwable {

        // then
        try {
            sutAlwaysThrowingUnchecked.shovingApplyAsByte(true);
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
            .isEqualTo("LBoolToByteFunction: byte applyAsByte(boolean a)");
    }

    @Test
    public void testBoolToByteFuncMethod() throws Throwable {
        assertThat(LBoolToByteFunction.boolToByteFunc(a -> testValue ))
            .isInstanceOf(LBoolToByteFunction.class);
    }






    // <editor-fold desc="compose (functional)">

    @Test
    public void testCompose() throws Throwable {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LBoolToByteFunction sutO = a -> {
                mainFunctionCalled.set(true);
                assertThat(a).isEqualTo(true);
                return (byte)100;
        };

        LLogicalOperator before = p0 -> {
            assertThat(p0).isEqualTo(true);
            beforeCalls.incrementAndGet();
            return true;
        };

        //when
        LBoolToByteFunction function = sutO.compose(before);
        function.applyAsByte(true);

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(beforeCalls.get()).isEqualTo(1);
    }


    @Test
    public void testBoolToByteFuncCompose() throws Throwable {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LBoolToByteFunction sutO = a -> {
                mainFunctionCalled.set(true);
                assertThat(a).isEqualTo(true);
                return (byte)100;
        };

        LPredicate<Integer> before = p0 -> {
            assertThat(p0).isEqualTo(80);
            beforeCalls.incrementAndGet();
            return true;
        };

        //when
        LToByteFunction<Integer> function = sutO.boolToByteFuncCompose(before);
        function.applyAsByte(80);

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
        LBoolToByteFunction sutO = a -> {
                mainFunctionCalled.set(true);
                assertThat(a).isEqualTo(true);
                return (byte)90;
        };

        LByteFunction<Integer> thenFunction = p -> {
                thenFunctionCalled.set(true);
                // byte
                assertThat(p).isEqualTo((byte)90);
                // Integer
                return 100;
        };

        //when
        LBoolFunction<Integer> function = sutO.then(thenFunction);
        Integer finalValue = function.apply(true);

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
        LBoolToByteFunction sutO = a -> {
                mainFunctionCalled.set(true);
                assertThat(a).isEqualTo(true);
                return (byte)90;
        };

        LByteUnaryOperator thenFunction = p -> {
                thenFunctionCalled.set(true);
                // byte
                assertThat(p).isEqualTo((byte)90);
                // byte
                return (byte)100;
        };

        //when
        LBoolToByteFunction function = sutO.thenToByte(thenFunction);
        byte finalValue = function.applyAsByte(true);

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
        LBoolToByteFunction sutO = a -> {
                mainFunctionCalled.set(true);
                assertThat(a).isEqualTo(true);
                return (byte)90;
        };

        LByteToSrtFunction thenFunction = p -> {
                thenFunctionCalled.set(true);
                // byte
                assertThat(p).isEqualTo((byte)90);
                // short
                return (short)100;
        };

        //when
        LBoolToSrtFunction function = sutO.thenToSrt(thenFunction);
        short finalValue = function.applyAsSrt(true);

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
        LBoolToByteFunction sutO = a -> {
                mainFunctionCalled.set(true);
                assertThat(a).isEqualTo(true);
                return (byte)90;
        };

        LByteToIntFunction thenFunction = p -> {
                thenFunctionCalled.set(true);
                // byte
                assertThat(p).isEqualTo((byte)90);
                // int
                return 100;
        };

        //when
        LBoolToIntFunction function = sutO.thenToInt(thenFunction);
        int finalValue = function.applyAsInt(true);

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
        LBoolToByteFunction sutO = a -> {
                mainFunctionCalled.set(true);
                assertThat(a).isEqualTo(true);
                return (byte)90;
        };

        LByteToLongFunction thenFunction = p -> {
                thenFunctionCalled.set(true);
                // byte
                assertThat(p).isEqualTo((byte)90);
                // long
                return 100L;
        };

        //when
        LBoolToLongFunction function = sutO.thenToLong(thenFunction);
        long finalValue = function.applyAsLong(true);

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
        LBoolToByteFunction sutO = a -> {
                mainFunctionCalled.set(true);
                assertThat(a).isEqualTo(true);
                return (byte)90;
        };

        LByteToFltFunction thenFunction = p -> {
                thenFunctionCalled.set(true);
                // byte
                assertThat(p).isEqualTo((byte)90);
                // float
                return 100f;
        };

        //when
        LBoolToFltFunction function = sutO.thenToFlt(thenFunction);
        float finalValue = function.applyAsFlt(true);

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
        LBoolToByteFunction sutO = a -> {
                mainFunctionCalled.set(true);
                assertThat(a).isEqualTo(true);
                return (byte)90;
        };

        LByteToDblFunction thenFunction = p -> {
                thenFunctionCalled.set(true);
                // byte
                assertThat(p).isEqualTo((byte)90);
                // double
                return 100d;
        };

        //when
        LBoolToDblFunction function = sutO.thenToDbl(thenFunction);
        double finalValue = function.applyAsDbl(true);

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
        LBoolToByteFunction sutO = a -> {
                mainFunctionCalled.set(true);
                assertThat(a).isEqualTo(true);
                return (byte)90;
        };

        LByteToCharFunction thenFunction = p -> {
                thenFunctionCalled.set(true);
                // byte
                assertThat(p).isEqualTo((byte)90);
                // char
                return '\u0100';
        };

        //when
        LBoolToCharFunction function = sutO.thenToChar(thenFunction);
        char finalValue = function.applyAsChar(true);

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
        LBoolToByteFunction sutO = a -> {
                mainFunctionCalled.set(true);
                assertThat(a).isEqualTo(true);
                return (byte)90;
        };

        LBytePredicate thenFunction = p -> {
                thenFunctionCalled.set(true);
                // byte
                assertThat(p).isEqualTo((byte)90);
                // boolean
                return true;
        };

        //when
        LLogicalOperator function = sutO.thenToBool(thenFunction);
        boolean finalValue = function.apply(true);

        //then - finals
        assertThat(finalValue).isEqualTo(true);
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    // </editor-fold>


    @Test(expectedExceptions = RuntimeException.class)
    public void testShove() {

        // given
        LBoolToByteFunction sutThrowing = LBoolToByteFunction.boolToByteFunc(a -> {
            throw new UnsupportedOperationException();
        });

        // when
        sutThrowing.shovingApplyAsByte(true);
    }


    @Test
    public void testToString() throws Throwable {

        assertThat(sut.toString())
                .isInstanceOf(String.class)
                .startsWith(this.getClass().getName()+"$");

        assertThat(String.format("%s", sut))
                .isInstanceOf(String.class)
                .contains("LBoolToByteFunction: byte applyAsByte(boolean a)");
    }


    @Test
    public void isThrowing() {
        assertThat(sut.isThrowing())
            .isFalse();
    }

    @Test void safeCompiles() {
        LBoolToByteFunction r1 = LBoolToByteFunction.safe(sut); //NOSONAR
    }

    @Test void safePropagates() {
        Object result = LBoolToByteFunction.safe(sut);
        assertThat(result).isSameAs(sut);
    }

    @Test void safeProtectsAgainstNpe() {
        Object result = LBoolToByteFunction.safe(null);
        assertThat(result).isSameAs(LBoolToByteFunction.boolToByteFunc(LBoolToByteFunction.safe()));
    }

    @Test  void safeSupplierPropagates() {
        LSupplier<LBoolToByteFunction> supplier = ()->sut;
        Object result = LBoolToByteFunction.safeSupplier(supplier);
        assertThat(result).isSameAs(supplier);
    }

    @Test  void safeSupplierProtectsAgainstNpe() {
        Object result = LBoolToByteFunction.safeSupplier(null);
        assertThat(result).isSameAs(LBoolToByteFunction.safeSupplier());
    }

    @Test  void safeSupplierCompiles() {
        LSupplier<LBoolToByteFunction> r1 = LBoolToByteFunction.safeSupplier(()->sut);  //NOSONAR
    }

}
