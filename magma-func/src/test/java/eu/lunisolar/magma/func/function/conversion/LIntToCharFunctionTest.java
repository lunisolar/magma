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
public class LIntToCharFunctionTest {
    private static final String ORIGINAL_MESSAGE = "Original message";
    private static final String EXCEPTION_WAS_WRAPPED = "Exception was wrapped.";
    private static final String NO_EXCEPTION_WERE_THROWN = "No exception were thrown.";

    private char testValue = '\u0100';



    private LIntToCharFunction sut = new LIntToCharFunction(){
        public  char applyAsCharX(int a)  {
            return testValue;
        }
    };




    private LIntToCharFunction sutAlwaysThrowing = LIntToCharFunction.intToCharFunc(a -> {
            throw new ParseException(ORIGINAL_MESSAGE, 0);
    });

    private LIntToCharFunction sutAlwaysThrowingUnchecked = LIntToCharFunction.intToCharFunc(a -> {
            throw new IndexOutOfBoundsException(ORIGINAL_MESSAGE);
    });


    @Test
    public void testTheResult() throws Throwable {
        assertThat(sut.applyAsChar(100))
            .isEqualTo(testValue);
    }

    @Test
    public void testTupleCall() throws Throwable {

        LIntSingle domainObject = Tuple4U.intSingle(100);

        Object result = sut.tupleApplyAsChar(domainObject);

        assertThat(result)
            .isEqualTo(testValue);
    }

    @Test
    public void testNonNullApplyAsChar() throws Throwable {
        assertThat(sut.nonNullApplyAsChar(100))
            .isEqualTo(testValue);
    }

    @Test
    public void testNestingApplyAsCharUnchecked() throws Throwable {

        // then
        try {
            sutAlwaysThrowingUnchecked.nestingApplyAsChar(100);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                    .hasNoCause()
                    .hasMessage(ORIGINAL_MESSAGE);
        }
    }

    @Test
    public void testShovingApplyAsCharUnchecked() throws Throwable {

        // then
        try {
            sutAlwaysThrowingUnchecked.shovingApplyAsChar(100);
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
            .isEqualTo("LIntToCharFunction: char applyAsChar(int a)");
    }

    @Test
    public void testIntToCharFuncMethod() throws Throwable {
        assertThat(LIntToCharFunction.intToCharFunc(a -> testValue ))
            .isInstanceOf(LIntToCharFunction.class);
    }






    // <editor-fold desc="compose (functional)">

    @Test
    public void testCompose() throws Throwable {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LIntToCharFunction sutO = a -> {
                mainFunctionCalled.set(true);
                assertThat(a).isEqualTo(90);
                return '\u0100';
        };

        LIntUnaryOperator before = p0 -> {
            assertThat(p0).isEqualTo(80);
            beforeCalls.incrementAndGet();
            return 90;
        };

        //when
        LIntToCharFunction function = sutO.compose(before);
        function.applyAsChar(80);

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(beforeCalls.get()).isEqualTo(1);
    }


    @Test
    public void testIntToCharFuncCompose() throws Throwable {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LIntToCharFunction sutO = a -> {
                mainFunctionCalled.set(true);
                assertThat(a).isEqualTo(90);
                return '\u0100';
        };

        LToIntFunction<Integer> before = p0 -> {
            assertThat(p0).isEqualTo(80);
            beforeCalls.incrementAndGet();
            return 90;
        };

        //when
        LToCharFunction<Integer> function = sutO.intToCharFuncCompose(before);
        function.applyAsChar(80);

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
        LIntToCharFunction sutO = a -> {
                mainFunctionCalled.set(true);
                assertThat(a).isEqualTo(80);
                return '\u0090';
        };

        LCharFunction<Integer> thenFunction = p -> {
                thenFunctionCalled.set(true);
                // char
                assertThat(p).isEqualTo('\u0090');
                // Integer
                return 100;
        };

        //when
        LIntFunction<Integer> function = sutO.then(thenFunction);
        Integer finalValue = function.apply(80);

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
        LIntToCharFunction sutO = a -> {
                mainFunctionCalled.set(true);
                assertThat(a).isEqualTo(80);
                return '\u0090';
        };

        LCharToByteFunction thenFunction = p -> {
                thenFunctionCalled.set(true);
                // char
                assertThat(p).isEqualTo('\u0090');
                // byte
                return (byte)100;
        };

        //when
        LIntToByteFunction function = sutO.thenToByte(thenFunction);
        byte finalValue = function.applyAsByte(80);

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
        LIntToCharFunction sutO = a -> {
                mainFunctionCalled.set(true);
                assertThat(a).isEqualTo(80);
                return '\u0090';
        };

        LCharToSrtFunction thenFunction = p -> {
                thenFunctionCalled.set(true);
                // char
                assertThat(p).isEqualTo('\u0090');
                // short
                return (short)100;
        };

        //when
        LIntToSrtFunction function = sutO.thenToSrt(thenFunction);
        short finalValue = function.applyAsSrt(80);

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
        LIntToCharFunction sutO = a -> {
                mainFunctionCalled.set(true);
                assertThat(a).isEqualTo(80);
                return '\u0090';
        };

        LCharToIntFunction thenFunction = p -> {
                thenFunctionCalled.set(true);
                // char
                assertThat(p).isEqualTo('\u0090');
                // int
                return 100;
        };

        //when
        LIntUnaryOperator function = sutO.thenToInt(thenFunction);
        int finalValue = function.applyAsInt(80);

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
        LIntToCharFunction sutO = a -> {
                mainFunctionCalled.set(true);
                assertThat(a).isEqualTo(80);
                return '\u0090';
        };

        LCharToLongFunction thenFunction = p -> {
                thenFunctionCalled.set(true);
                // char
                assertThat(p).isEqualTo('\u0090');
                // long
                return 100L;
        };

        //when
        LIntToLongFunction function = sutO.thenToLong(thenFunction);
        long finalValue = function.applyAsLong(80);

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
        LIntToCharFunction sutO = a -> {
                mainFunctionCalled.set(true);
                assertThat(a).isEqualTo(80);
                return '\u0090';
        };

        LCharToFltFunction thenFunction = p -> {
                thenFunctionCalled.set(true);
                // char
                assertThat(p).isEqualTo('\u0090');
                // float
                return 100f;
        };

        //when
        LIntToFltFunction function = sutO.thenToFlt(thenFunction);
        float finalValue = function.applyAsFlt(80);

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
        LIntToCharFunction sutO = a -> {
                mainFunctionCalled.set(true);
                assertThat(a).isEqualTo(80);
                return '\u0090';
        };

        LCharToDblFunction thenFunction = p -> {
                thenFunctionCalled.set(true);
                // char
                assertThat(p).isEqualTo('\u0090');
                // double
                return 100d;
        };

        //when
        LIntToDblFunction function = sutO.thenToDbl(thenFunction);
        double finalValue = function.applyAsDbl(80);

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
        LIntToCharFunction sutO = a -> {
                mainFunctionCalled.set(true);
                assertThat(a).isEqualTo(80);
                return '\u0090';
        };

        LCharUnaryOperator thenFunction = p -> {
                thenFunctionCalled.set(true);
                // char
                assertThat(p).isEqualTo('\u0090');
                // char
                return '\u0100';
        };

        //when
        LIntToCharFunction function = sutO.thenToChar(thenFunction);
        char finalValue = function.applyAsChar(80);

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
        LIntToCharFunction sutO = a -> {
                mainFunctionCalled.set(true);
                assertThat(a).isEqualTo(80);
                return '\u0090';
        };

        LCharPredicate thenFunction = p -> {
                thenFunctionCalled.set(true);
                // char
                assertThat(p).isEqualTo('\u0090');
                // boolean
                return true;
        };

        //when
        LIntPredicate function = sutO.thenToBool(thenFunction);
        boolean finalValue = function.test(80);

        //then - finals
        assertThat(finalValue).isEqualTo(true);
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    // </editor-fold>


    @Test(expectedExceptions = RuntimeException.class)
    public void testShove() {

        // given
        LIntToCharFunction sutThrowing = LIntToCharFunction.intToCharFunc(a -> {
            throw new UnsupportedOperationException();
        });

        // when
        sutThrowing.shovingApplyAsChar(100);
    }


    @Test
    public void testToString() throws Throwable {

        assertThat(sut.toString())
                .isInstanceOf(String.class)
                .startsWith(this.getClass().getName()+"$");

        assertThat(String.format("%s", sut))
                .isInstanceOf(String.class)
                .contains("LIntToCharFunction: char applyAsChar(int a)");
    }


    @Test
    public void isThrowing() {
        assertThat(sut.isThrowing())
            .isFalse();
    }

    @Test void safeCompiles() {
        LIntToCharFunction r1 = LIntToCharFunction.safe(sut); //NOSONAR
    }

    @Test void safePropagates() {
        Object result = LIntToCharFunction.safe(sut);
        assertThat(result).isSameAs(sut);
    }

    @Test void safeProtectsAgainstNpe() {
        Object result = LIntToCharFunction.safe(null);
        assertThat(result).isSameAs(LIntToCharFunction.intToCharFunc(LIntToCharFunction.safe()));
    }

    @Test  void safeSupplierPropagates() {
        LSupplier<LIntToCharFunction> supplier = ()->sut;
        Object result = LIntToCharFunction.safeSupplier(supplier);
        assertThat(result).isSameAs(supplier);
    }

    @Test  void safeSupplierProtectsAgainstNpe() {
        Object result = LIntToCharFunction.safeSupplier(null);
        assertThat(result).isSameAs(LIntToCharFunction.safeSupplier());
    }

    @Test  void safeSupplierCompiles() {
        LSupplier<LIntToCharFunction> r1 = LIntToCharFunction.safeSupplier(()->sut);  //NOSONAR
    }

}
