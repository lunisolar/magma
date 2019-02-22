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
public class LByteToDblFunctionTest {
    private static final String ORIGINAL_MESSAGE = "Original message";
    private static final String EXCEPTION_WAS_WRAPPED = "Exception was wrapped.";
    private static final String NO_EXCEPTION_WERE_THROWN = "No exception were thrown.";

    private double testValue = 100d;



    private LByteToDblFunction sut = new LByteToDblFunction(){
        public  double applyAsDblX(byte a)  {
            return testValue;
        }
    };




    private LByteToDblFunction sutAlwaysThrowing = LByteToDblFunction.byteToDblFunc(a -> {
            throw new ParseException(ORIGINAL_MESSAGE, 0);
    });

    private LByteToDblFunction sutAlwaysThrowingUnchecked = LByteToDblFunction.byteToDblFunc(a -> {
            throw new IndexOutOfBoundsException(ORIGINAL_MESSAGE);
    });


    @Test
    public void testTheResult() throws Throwable {
        assertThat(sut.applyAsDbl((byte)100))
            .isEqualTo(testValue);
    }

    @Test
    public void testTupleCall() throws Throwable {

        LByteSingle domainObject = Tuple4U.byteSingle((byte)100);

        Object result = sut.tupleApplyAsDbl(domainObject);

        assertThat(result)
            .isEqualTo(testValue);
    }

    @Test
    public void testNonNullApplyAsDbl() throws Throwable {
        assertThat(sut.nonNullApplyAsDbl((byte)100))
            .isEqualTo(testValue);
    }

    @Test
    public void testNestingApplyAsDblUnchecked() throws Throwable {

        // then
        try {
            sutAlwaysThrowingUnchecked.nestingApplyAsDbl((byte)100);
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
            sutAlwaysThrowingUnchecked.shovingApplyAsDbl((byte)100);
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
            .isEqualTo("LByteToDblFunction: double applyAsDbl(byte a)");
    }

    @Test
    public void testByteToDblFuncMethod() throws Throwable {
        assertThat(LByteToDblFunction.byteToDblFunc(a -> testValue ))
            .isInstanceOf(LByteToDblFunction.class);
    }






    // <editor-fold desc="compose (functional)">

    @Test
    public void testCompose() throws Throwable {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LByteToDblFunction sutO = a -> {
                mainFunctionCalled.set(true);
                assertThat(a).isEqualTo((byte)90);
                return 100d;
        };

        LByteUnaryOperator before = p0 -> {
            assertThat(p0).isEqualTo((byte)80);
            beforeCalls.incrementAndGet();
            return (byte)90;
        };

        //when
        LByteToDblFunction function = sutO.compose(before);
        function.applyAsDbl((byte)80);

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(beforeCalls.get()).isEqualTo(1);
    }


    @Test
    public void testByteToDblFuncCompose() throws Throwable {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LByteToDblFunction sutO = a -> {
                mainFunctionCalled.set(true);
                assertThat(a).isEqualTo((byte)90);
                return 100d;
        };

        LToByteFunction<Integer> before = p0 -> {
            assertThat(p0).isEqualTo(80);
            beforeCalls.incrementAndGet();
            return (byte)90;
        };

        //when
        LToDblFunction<Integer> function = sutO.byteToDblFuncCompose(before);
        function.applyAsDbl(80);

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
        LByteToDblFunction sutO = a -> {
                mainFunctionCalled.set(true);
                assertThat(a).isEqualTo((byte)80);
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
        LByteFunction<Integer> function = sutO.then(thenFunction);
        Integer finalValue = function.apply((byte)80);

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
        LByteToDblFunction sutO = a -> {
                mainFunctionCalled.set(true);
                assertThat(a).isEqualTo((byte)80);
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
        LByteUnaryOperator function = sutO.thenToByte(thenFunction);
        byte finalValue = function.applyAsByte((byte)80);

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
        LByteToDblFunction sutO = a -> {
                mainFunctionCalled.set(true);
                assertThat(a).isEqualTo((byte)80);
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
        LByteToSrtFunction function = sutO.thenToSrt(thenFunction);
        short finalValue = function.applyAsSrt((byte)80);

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
        LByteToDblFunction sutO = a -> {
                mainFunctionCalled.set(true);
                assertThat(a).isEqualTo((byte)80);
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
        LByteToIntFunction function = sutO.thenToInt(thenFunction);
        int finalValue = function.applyAsInt((byte)80);

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
        LByteToDblFunction sutO = a -> {
                mainFunctionCalled.set(true);
                assertThat(a).isEqualTo((byte)80);
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
        LByteToLongFunction function = sutO.thenToLong(thenFunction);
        long finalValue = function.applyAsLong((byte)80);

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
        LByteToDblFunction sutO = a -> {
                mainFunctionCalled.set(true);
                assertThat(a).isEqualTo((byte)80);
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
        LByteToFltFunction function = sutO.thenToFlt(thenFunction);
        float finalValue = function.applyAsFlt((byte)80);

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
        LByteToDblFunction sutO = a -> {
                mainFunctionCalled.set(true);
                assertThat(a).isEqualTo((byte)80);
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
        LByteToDblFunction function = sutO.thenToDbl(thenFunction);
        double finalValue = function.applyAsDbl((byte)80);

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
        LByteToDblFunction sutO = a -> {
                mainFunctionCalled.set(true);
                assertThat(a).isEqualTo((byte)80);
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
        LByteToCharFunction function = sutO.thenToChar(thenFunction);
        char finalValue = function.applyAsChar((byte)80);

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
        LByteToDblFunction sutO = a -> {
                mainFunctionCalled.set(true);
                assertThat(a).isEqualTo((byte)80);
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
        LBytePredicate function = sutO.thenToBool(thenFunction);
        boolean finalValue = function.test((byte)80);

        //then - finals
        assertThat(finalValue).isEqualTo(true);
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    // </editor-fold>


    @Test(expectedExceptions = RuntimeException.class)
    public void testShove() {

        // given
        LByteToDblFunction sutThrowing = LByteToDblFunction.byteToDblFunc(a -> {
            throw new UnsupportedOperationException();
        });

        // when
        sutThrowing.shovingApplyAsDbl((byte)100);
    }


    @Test
    public void testToString() throws Throwable {

        assertThat(sut.toString())
                .isInstanceOf(String.class)
                .startsWith(this.getClass().getName()+"$");

        assertThat(String.format("%s", sut))
                .isInstanceOf(String.class)
                .contains("LByteToDblFunction: double applyAsDbl(byte a)");
    }


    @Test
    public void isThrowing() {
        assertThat(sut.isThrowing())
            .isFalse();
    }

    @Test void safeCompiles() {
        LByteToDblFunction r1 = LByteToDblFunction.safe(sut); //NOSONAR
    }

    @Test void safePropagates() {
        Object result = LByteToDblFunction.safe(sut);
        assertThat(result).isSameAs(sut);
    }

    @Test void safeProtectsAgainstNpe() {
        Object result = LByteToDblFunction.safe(null);
        assertThat(result).isSameAs(LByteToDblFunction.byteToDblFunc(LByteToDblFunction.safe()));
    }

    @Test  void safeSupplierPropagates() {
        LSupplier<LByteToDblFunction> supplier = ()->sut;
        Object result = LByteToDblFunction.safeSupplier(supplier);
        assertThat(result).isSameAs(supplier);
    }

    @Test  void safeSupplierProtectsAgainstNpe() {
        Object result = LByteToDblFunction.safeSupplier(null);
        assertThat(result).isSameAs(LByteToDblFunction.safeSupplier());
    }

    @Test  void safeSupplierCompiles() {
        LSupplier<LByteToDblFunction> r1 = LByteToDblFunction.safeSupplier(()->sut);  //NOSONAR
    }

}
