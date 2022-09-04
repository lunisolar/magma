/*
 * This file is part of "lunisolar-magma".
 *
 * (C) Copyright 2014-2022 Lunisolar (http://lunisolar.eu/).
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

package eu.lunisolar.magma.func.operator.unary;

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

import org.testng.Assert;
import org.testng.annotations.*;      //NOSONAR
import java.util.regex.Pattern;          //NOSONAR
import java.text.ParseException;         //NOSONAR
import eu.lunisolar.magma.basics.*; //NOSONAR
import eu.lunisolar.magma.basics.exceptions.*; //NOSONAR
import java.util.concurrent.atomic.AtomicInteger; //NOSONAR
import eu.lunisolar.magma.func.tuple.*; // NOSONAR
import java.util.function.*; // NOSONAR

/** The test obviously concentrate on the interface methods the function it self is very simple.  */
public class LCharUnaryOperatorTest {
    private static final String ORIGINAL_MESSAGE = "Original message";
    private static final String EXCEPTION_WAS_WRAPPED = "Exception was wrapped.";
    private static final String NO_EXCEPTION_WERE_THROWN = "No exception were thrown.";

    private char testValue = '\u0100';



    private LCharUnaryOperator sut = new LCharUnaryOperator(){
        public  char applyAsCharX(char a)  {
            return testValue;
        }
    };




    private LCharUnaryOperator sutAlwaysThrowing = LCharUnaryOperator.charUnaryOp(a -> {
            throw new ParseException(ORIGINAL_MESSAGE, 0);
    });

    private LCharUnaryOperator sutAlwaysThrowingUnchecked = LCharUnaryOperator.charUnaryOp(a -> {
            throw new IndexOutOfBoundsException(ORIGINAL_MESSAGE);
    });


    @Test
    public void testTheResult() throws Throwable {
         Assert.assertEquals(sut.applyAsChar('\u0100'), testValue);
    }

    @Test
    public void testTupleCall() throws Throwable {

        LCharSingle domainObject = Tuple4U.charSingle('\u0100');

        Object result = sut.tupleApplyAsChar(domainObject);

            Assert.assertEquals(result, testValue);
    }

    @Test
    public void testNonNullApplyAsChar() throws Throwable {
            Assert.assertEquals(sut.nonNullApplyAsChar('\u0100'), testValue);
    }

    @Test
    public void testNestingApplyAsCharUnchecked() throws Throwable {

        // then
        try {
            sutAlwaysThrowingUnchecked.nestingApplyAsChar('\u0100');
            Assert.fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            Assert.assertEquals(e.getClass(), IndexOutOfBoundsException.class);
            Assert.assertNull(e.getCause());
            Assert.assertEquals(e.getMessage(), ORIGINAL_MESSAGE);
        }
    }

    @Test
    public void testShovingApplyAsCharUnchecked() throws Throwable {

        // then
        try {
            sutAlwaysThrowingUnchecked.shovingApplyAsChar('\u0100');
            Assert.fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            Assert.assertEquals(e.getClass(), IndexOutOfBoundsException.class);
            Assert.assertNull(e.getCause());
            Assert.assertEquals(e.getMessage(), ORIGINAL_MESSAGE);
        }
    }


    @Test
    public void testFunctionalInterfaceDescription() throws Throwable {
        Assert.assertEquals(sut.functionalInterfaceDescription(), "LCharUnaryOperator: char applyAsChar(char a)");
    }

    @Test
    public void testCharUnaryOpMethod() throws Throwable {
        Assert.assertTrue(LCharUnaryOperator.charUnaryOp(a -> testValue ) instanceof LCharUnaryOperator);
    
    }






    // <editor-fold desc="compose (functional)">

    @Test
    public void testCompose() throws Throwable {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LCharUnaryOperator sutO = a -> {
                mainFunctionCalled.set(true);
                Assert.assertEquals(a, (Object) '\u0090');
                return '\u0100';
        };

        LCharUnaryOperator before = p0 -> {
            Assert.assertEquals(p0, (Object) '\u0080');
            beforeCalls.incrementAndGet();
            return '\u0090';
        };

        //when
        LCharUnaryOperator function = sutO.compose(before);
        function.applyAsChar('\u0080');

        //then - finals
        Assert.assertTrue(mainFunctionCalled.get());
        Assert.assertEquals(beforeCalls.get(), 1);
    }


    @Test
    public void testUnboxingCompose() throws Throwable {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LCharUnaryOperator sutO = a -> {
                mainFunctionCalled.set(true);
                Assert.assertEquals(a, (Object) '\u0090');
                return '\u0100';
        };

        LToCharFunction<Integer> before = p0 -> {
            Assert.assertEquals(p0, (Object) 80);
            beforeCalls.incrementAndGet();
            return '\u0090';
        };

        //when
        LToCharFunction<Integer> function = sutO.unboxingCompose(before);
        function.applyAsChar(80);

        //then - finals
        Assert.assertTrue(mainFunctionCalled.get());
        Assert.assertEquals(beforeCalls.get(), 1);
    }

    // </editor-fold>



    // <editor-fold desc="then (functional)">

    @Test
    public void testThen0() throws Throwable  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);

        //given (+ some assertions)
        LCharUnaryOperator sutO = a -> {
                mainFunctionCalled.set(true);
                Assert.assertEquals(a, (Object) '\u0080');
                return '\u0090';
        };

        LCharFunction<Integer> thenFunction = p -> {
                thenFunctionCalled.set(true);
                // char
                Assert.assertEquals(p, (Object) '\u0090');
                // Integer
                return 100;
        };

        //when
        LCharFunction<Integer> function = sutO.then(thenFunction);
        Integer finalValue = function.apply('\u0080');

        //then - finals
        Assert.assertEquals(finalValue, (Object) 100);
        Assert.assertTrue(mainFunctionCalled.get());
        Assert.assertTrue(thenFunctionCalled.get());

    }



    @Test
    public void testThenToByte1() throws Throwable  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);

        //given (+ some assertions)
        LCharUnaryOperator sutO = a -> {
                mainFunctionCalled.set(true);
                Assert.assertEquals(a, (Object) '\u0080');
                return '\u0090';
        };

        LCharToByteFunction thenFunction = p -> {
                thenFunctionCalled.set(true);
                // char
                Assert.assertEquals(p, (Object) '\u0090');
                // byte
                return (byte)100;
        };

        //when
        LCharToByteFunction function = sutO.thenToByte(thenFunction);
        byte finalValue = function.applyAsByte('\u0080');

        //then - finals
        Assert.assertEquals(finalValue, (Object) (byte)100);
        Assert.assertTrue(mainFunctionCalled.get());
        Assert.assertTrue(thenFunctionCalled.get());

    }



    @Test
    public void testThenToSrt2() throws Throwable  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);

        //given (+ some assertions)
        LCharUnaryOperator sutO = a -> {
                mainFunctionCalled.set(true);
                Assert.assertEquals(a, (Object) '\u0080');
                return '\u0090';
        };

        LCharToSrtFunction thenFunction = p -> {
                thenFunctionCalled.set(true);
                // char
                Assert.assertEquals(p, (Object) '\u0090');
                // short
                return (short)100;
        };

        //when
        LCharToSrtFunction function = sutO.thenToSrt(thenFunction);
        short finalValue = function.applyAsSrt('\u0080');

        //then - finals
        Assert.assertEquals(finalValue, (Object) (short)100);
        Assert.assertTrue(mainFunctionCalled.get());
        Assert.assertTrue(thenFunctionCalled.get());

    }



    @Test
    public void testThenToInt3() throws Throwable  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);

        //given (+ some assertions)
        LCharUnaryOperator sutO = a -> {
                mainFunctionCalled.set(true);
                Assert.assertEquals(a, (Object) '\u0080');
                return '\u0090';
        };

        LCharToIntFunction thenFunction = p -> {
                thenFunctionCalled.set(true);
                // char
                Assert.assertEquals(p, (Object) '\u0090');
                // int
                return 100;
        };

        //when
        LCharToIntFunction function = sutO.thenToInt(thenFunction);
        int finalValue = function.applyAsInt('\u0080');

        //then - finals
        Assert.assertEquals(finalValue, (Object) 100);
        Assert.assertTrue(mainFunctionCalled.get());
        Assert.assertTrue(thenFunctionCalled.get());

    }



    @Test
    public void testThenToLong4() throws Throwable  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);

        //given (+ some assertions)
        LCharUnaryOperator sutO = a -> {
                mainFunctionCalled.set(true);
                Assert.assertEquals(a, (Object) '\u0080');
                return '\u0090';
        };

        LCharToLongFunction thenFunction = p -> {
                thenFunctionCalled.set(true);
                // char
                Assert.assertEquals(p, (Object) '\u0090');
                // long
                return 100L;
        };

        //when
        LCharToLongFunction function = sutO.thenToLong(thenFunction);
        long finalValue = function.applyAsLong('\u0080');

        //then - finals
        Assert.assertEquals(finalValue, (Object) 100L);
        Assert.assertTrue(mainFunctionCalled.get());
        Assert.assertTrue(thenFunctionCalled.get());

    }



    @Test
    public void testThenToFlt5() throws Throwable  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);

        //given (+ some assertions)
        LCharUnaryOperator sutO = a -> {
                mainFunctionCalled.set(true);
                Assert.assertEquals(a, (Object) '\u0080');
                return '\u0090';
        };

        LCharToFltFunction thenFunction = p -> {
                thenFunctionCalled.set(true);
                // char
                Assert.assertEquals(p, (Object) '\u0090');
                // float
                return 100f;
        };

        //when
        LCharToFltFunction function = sutO.thenToFlt(thenFunction);
        float finalValue = function.applyAsFlt('\u0080');

        //then - finals
        Assert.assertEquals(finalValue, (Object) 100f);
        Assert.assertTrue(mainFunctionCalled.get());
        Assert.assertTrue(thenFunctionCalled.get());

    }



    @Test
    public void testThenToDbl6() throws Throwable  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);

        //given (+ some assertions)
        LCharUnaryOperator sutO = a -> {
                mainFunctionCalled.set(true);
                Assert.assertEquals(a, (Object) '\u0080');
                return '\u0090';
        };

        LCharToDblFunction thenFunction = p -> {
                thenFunctionCalled.set(true);
                // char
                Assert.assertEquals(p, (Object) '\u0090');
                // double
                return 100d;
        };

        //when
        LCharToDblFunction function = sutO.thenToDbl(thenFunction);
        double finalValue = function.applyAsDbl('\u0080');

        //then - finals
        Assert.assertEquals(finalValue, (Object) 100d);
        Assert.assertTrue(mainFunctionCalled.get());
        Assert.assertTrue(thenFunctionCalled.get());

    }



    @Test
    public void testThenToChar7() throws Throwable  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);

        //given (+ some assertions)
        LCharUnaryOperator sutO = a -> {
                mainFunctionCalled.set(true);
                Assert.assertEquals(a, (Object) '\u0080');
                return '\u0090';
        };

        LCharUnaryOperator thenFunction = p -> {
                thenFunctionCalled.set(true);
                // char
                Assert.assertEquals(p, (Object) '\u0090');
                // char
                return '\u0100';
        };

        //when
        LCharUnaryOperator function = sutO.thenToChar(thenFunction);
        char finalValue = function.applyAsChar('\u0080');

        //then - finals
        Assert.assertEquals(finalValue, (Object) '\u0100');
        Assert.assertTrue(mainFunctionCalled.get());
        Assert.assertTrue(thenFunctionCalled.get());

    }



    @Test
    public void testThenToBool8() throws Throwable  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);

        //given (+ some assertions)
        LCharUnaryOperator sutO = a -> {
                mainFunctionCalled.set(true);
                Assert.assertEquals(a, (Object) '\u0080');
                return '\u0090';
        };

        LCharPredicate thenFunction = p -> {
                thenFunctionCalled.set(true);
                // char
                Assert.assertEquals(p, (Object) '\u0090');
                // boolean
                return true;
        };

        //when
        LCharPredicate function = sutO.thenToBool(thenFunction);
        boolean finalValue = function.test('\u0080');

        //then - finals
        Assert.assertEquals(finalValue, (Object) true);
        Assert.assertTrue(mainFunctionCalled.get());
        Assert.assertTrue(thenFunctionCalled.get());

    }



    // </editor-fold>
    @Test
    public void identity() throws Throwable {
        LCharUnaryOperator identityFunction = LCharUnaryOperator.identity();
        Assert.assertEquals(identityFunction.applyAsChar('\u0008'), (Object) '\u0008');
    }



    @Test(expectedExceptions = RuntimeException.class)
    public void testShove() {

        // given
        LCharUnaryOperator sutThrowing = LCharUnaryOperator.charUnaryOp(a -> {
            throw new UnsupportedOperationException();
        });

        // when
        sutThrowing.shovingApplyAsChar('\u0100');
    }


    @Test
    public void testToString() throws Throwable {

        Assert.assertTrue(sut.toString().startsWith(this.getClass().getName()+"$"));

        Assert.assertTrue(String.format("%s", sut).contains("LCharUnaryOperator: char applyAsChar(char a)"));
    
    }


    @Test
    public void isThrowing() {

        Assert.assertFalse(sut.isThrowing());
    }

}
