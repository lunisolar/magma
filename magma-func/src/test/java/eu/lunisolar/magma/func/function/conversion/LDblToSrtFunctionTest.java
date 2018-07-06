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
public class LDblToSrtFunctionTest {
    private static final String ORIGINAL_MESSAGE = "Original message";
    private static final String EXCEPTION_WAS_WRAPPED = "Exception was wrapped.";
    private static final String NO_EXCEPTION_WERE_THROWN = "No exception were thrown.";

    private short testValue = (short)100;



    private LDblToSrtFunction sut = new LDblToSrtFunction(){
        public  short doApplyAsSrtX(double a)  {
            return testValue;
        }
    };




    private LDblToSrtFunction sutAlwaysThrowing = LDblToSrtFunction.dblToSrtFunc(a -> {
            throw new ParseException(ORIGINAL_MESSAGE, 0);
    });

    private LDblToSrtFunction sutAlwaysThrowingUnchecked = LDblToSrtFunction.dblToSrtFunc(a -> {
            throw new IndexOutOfBoundsException(ORIGINAL_MESSAGE);
    });


    @Test
    public void testTheResult() throws Throwable {
        assertThat(sut.doApplyAsSrt(100d))
            .isEqualTo(testValue);
    }

    @Test
    public void testTupleCall() throws Throwable {

        LDblSingle domainObject = Tuple4U.dblSingle(100d);

        Object result = sut.tupleApplyAsSrt(domainObject);

        assertThat(result)
            .isEqualTo(testValue);
    }

    @Test
    public void testNonNullDoApplyAsSrt() throws Throwable {
        assertThat(sut.nonNullDoApplyAsSrt(100d))
            .isEqualTo(testValue);
    }

    @Test
    public void testNestingDoApplyAsSrtUnchecked() throws Throwable {

        // then
        try {
            sutAlwaysThrowingUnchecked.nestingDoApplyAsSrt(100d);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                    .hasNoCause()
                    .hasMessage(ORIGINAL_MESSAGE);
        }
    }

    @Test
    public void testShovingDoApplyAsSrtUnchecked() throws Throwable {

        // then
        try {
            sutAlwaysThrowingUnchecked.shovingDoApplyAsSrt(100d);
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
            .isEqualTo("LDblToSrtFunction: short doApplyAsSrt(double a)");
    }

    @Test
    public void testDblToSrtFuncMethod() throws Throwable {
        assertThat(LDblToSrtFunction.dblToSrtFunc(a -> testValue ))
            .isInstanceOf(LDblToSrtFunction.class);
    }






    // <editor-fold desc="compose (functional)">

    @Test
    public void testDblToSrtFuncComposeDbl() throws Throwable {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LDblToSrtFunction sutO = a -> {
                mainFunctionCalled.set(true);
                assertThat(a).isEqualTo(90d);
                return (short)100;
        };

        LDblUnaryOperator before = p0 -> {
            assertThat(p0).isEqualTo(80d);
            beforeCalls.incrementAndGet();
            return 90d;
        };

        //when
        LDblToSrtFunction function = sutO.dblToSrtFuncComposeDbl(before);
        function.doApplyAsSrt(80d);

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(beforeCalls.get()).isEqualTo(1);
    }


    @Test
    public void testDblToSrtFuncCompose() throws Throwable {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LDblToSrtFunction sutO = a -> {
                mainFunctionCalled.set(true);
                assertThat(a).isEqualTo(90d);
                return (short)100;
        };

        LToDblFunction<Integer> before = p0 -> {
            assertThat(p0).isEqualTo(80);
            beforeCalls.incrementAndGet();
            return 90d;
        };

        //when
        LToSrtFunction<Integer> function = sutO.dblToSrtFuncCompose(before);
        function.doApplyAsSrt(80);

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
        LDblToSrtFunction sutO = a -> {
                mainFunctionCalled.set(true);
                assertThat(a).isEqualTo(80d);
                return (short)90;
        };

        LSrtFunction<Integer> thenFunction = p -> {
                thenFunctionCalled.set(true);
                // short
                assertThat(p).isEqualTo((short)90);
                // Integer
                return 100;
        };

        //when
        LDblFunction<Integer> function = sutO.then(thenFunction);
        Integer finalValue = function.doApply(80d);

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
        LDblToSrtFunction sutO = a -> {
                mainFunctionCalled.set(true);
                assertThat(a).isEqualTo(80d);
                return (short)90;
        };

        LSrtToByteFunction thenFunction = p -> {
                thenFunctionCalled.set(true);
                // short
                assertThat(p).isEqualTo((short)90);
                // byte
                return (byte)100;
        };

        //when
        LDblToByteFunction function = sutO.thenToByte(thenFunction);
        byte finalValue = function.doApplyAsByte(80d);

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
        LDblToSrtFunction sutO = a -> {
                mainFunctionCalled.set(true);
                assertThat(a).isEqualTo(80d);
                return (short)90;
        };

        LSrtUnaryOperator thenFunction = p -> {
                thenFunctionCalled.set(true);
                // short
                assertThat(p).isEqualTo((short)90);
                // short
                return (short)100;
        };

        //when
        LDblToSrtFunction function = sutO.thenToSrt(thenFunction);
        short finalValue = function.doApplyAsSrt(80d);

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
        LDblToSrtFunction sutO = a -> {
                mainFunctionCalled.set(true);
                assertThat(a).isEqualTo(80d);
                return (short)90;
        };

        LSrtToIntFunction thenFunction = p -> {
                thenFunctionCalled.set(true);
                // short
                assertThat(p).isEqualTo((short)90);
                // int
                return 100;
        };

        //when
        LDblToIntFunction function = sutO.thenToInt(thenFunction);
        int finalValue = function.doApplyAsInt(80d);

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
        LDblToSrtFunction sutO = a -> {
                mainFunctionCalled.set(true);
                assertThat(a).isEqualTo(80d);
                return (short)90;
        };

        LSrtToLongFunction thenFunction = p -> {
                thenFunctionCalled.set(true);
                // short
                assertThat(p).isEqualTo((short)90);
                // long
                return 100L;
        };

        //when
        LDblToLongFunction function = sutO.thenToLong(thenFunction);
        long finalValue = function.doApplyAsLong(80d);

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
        LDblToSrtFunction sutO = a -> {
                mainFunctionCalled.set(true);
                assertThat(a).isEqualTo(80d);
                return (short)90;
        };

        LSrtToFltFunction thenFunction = p -> {
                thenFunctionCalled.set(true);
                // short
                assertThat(p).isEqualTo((short)90);
                // float
                return 100f;
        };

        //when
        LDblToFltFunction function = sutO.thenToFlt(thenFunction);
        float finalValue = function.doApplyAsFlt(80d);

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
        LDblToSrtFunction sutO = a -> {
                mainFunctionCalled.set(true);
                assertThat(a).isEqualTo(80d);
                return (short)90;
        };

        LSrtToDblFunction thenFunction = p -> {
                thenFunctionCalled.set(true);
                // short
                assertThat(p).isEqualTo((short)90);
                // double
                return 100d;
        };

        //when
        LDblUnaryOperator function = sutO.thenToDbl(thenFunction);
        double finalValue = function.doApplyAsDbl(80d);

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
        LDblToSrtFunction sutO = a -> {
                mainFunctionCalled.set(true);
                assertThat(a).isEqualTo(80d);
                return (short)90;
        };

        LSrtToCharFunction thenFunction = p -> {
                thenFunctionCalled.set(true);
                // short
                assertThat(p).isEqualTo((short)90);
                // char
                return '\u0100';
        };

        //when
        LDblToCharFunction function = sutO.thenToChar(thenFunction);
        char finalValue = function.doApplyAsChar(80d);

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
        LDblToSrtFunction sutO = a -> {
                mainFunctionCalled.set(true);
                assertThat(a).isEqualTo(80d);
                return (short)90;
        };

        LSrtPredicate thenFunction = p -> {
                thenFunctionCalled.set(true);
                // short
                assertThat(p).isEqualTo((short)90);
                // boolean
                return true;
        };

        //when
        LDblPredicate function = sutO.thenToBool(thenFunction);
        boolean finalValue = function.doTest(80d);

        //then - finals
        assertThat(finalValue).isEqualTo(true);
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    // </editor-fold>

    @Test
    public void testNesting() {
        assertThat(sut.nestingDblToSrtFunc())
            .isSameAs(sut)
            .isInstanceOf(LDblToSrtFunction.class);
    }

    @Test
    public void testShoving() {
        assertThat(sut.shovingDblToSrtFunc())
            .isSameAs(sut)
            .isInstanceOf(LDblToSrtFunction.class);
    }


    @Test(expectedExceptions = RuntimeException.class)
    public void testShove() {

        // given
        LDblToSrtFunction sutThrowing = LDblToSrtFunction.dblToSrtFunc(a -> {
            throw new UnsupportedOperationException();
        });

        // when
        sutThrowing.shovingDblToSrtFunc().doApplyAsSrt(100d);
    }


    @Test
    public void testToString() throws Throwable {

        assertThat(sut.toString())
                .isInstanceOf(String.class)
                .startsWith(this.getClass().getName()+"$");

        assertThat(String.format("%s", sut))
                .isInstanceOf(String.class)
                .contains("LDblToSrtFunction: short doApplyAsSrt(double a)");
    }


    @Test
    public void isThrowing() {
        assertThat(sut.isThrowing())
            .isFalse();
    }

    @Test void safeCompiles() {
        LDblToSrtFunction r1 = LDblToSrtFunction.safe(sut); //NOSONAR
    }

    @Test void safePropagates() {
        Object result = LDblToSrtFunction.safe(sut);
        assertThat(result).isSameAs(sut);
    }

    @Test void safeProtectsAgainstNpe() {
        Object result = LDblToSrtFunction.safe(null);
        assertThat(result).isSameAs(LDblToSrtFunction.dblToSrtFunc(LDblToSrtFunction.safe()));
    }

    @Test  void safeSupplierPropagates() {
        LSupplier<LDblToSrtFunction> supplier = ()->sut;
        Object result = LDblToSrtFunction.safeSupplier(supplier);
        assertThat(result).isSameAs(supplier);
    }

    @Test  void safeSupplierProtectsAgainstNpe() {
        Object result = LDblToSrtFunction.safeSupplier(null);
        assertThat(result).isSameAs(LDblToSrtFunction.safeSupplier());
    }

    @Test  void safeSupplierCompiles() {
        LSupplier<LDblToSrtFunction> r1 = LDblToSrtFunction.safeSupplier(()->sut);  //NOSONAR
    }

}
