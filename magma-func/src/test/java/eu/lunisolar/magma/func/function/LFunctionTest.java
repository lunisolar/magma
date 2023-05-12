/*
 * This file is part of "lunisolar-magma".
 *
 * (C) Copyright 2014-2023 Lunisolar (http://lunisolar.eu/).
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

package eu.lunisolar.magma.func.function;

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
public class LFunctionTest<T,R> {
    private static final String ORIGINAL_MESSAGE = "Original message";
    private static final String EXCEPTION_WAS_WRAPPED = "Exception was wrapped.";
    private static final String NO_EXCEPTION_WERE_THROWN = "No exception were thrown.";

    private Integer testValue = 100;



    private LFunction<Integer,Integer> sut = new LFunction<Integer,Integer>(){
        public @Nullable Integer applyX(Integer a)  {
            return testValue;
        }
    };


    private LFunction<Integer,Integer> sutNull = new LFunction<Integer,Integer>(){
        public @Nullable Integer applyX(Integer a)  {
            return null;
        }
    };


    private Function<Integer,Integer> jre = a -> testValue;


    private LFunction<Integer,Integer> sutAlwaysThrowing = LFunction.func(a -> {
            throw new ParseException(ORIGINAL_MESSAGE, 0);
    });

    private LFunction<Integer,Integer> sutAlwaysThrowingUnchecked = LFunction.func(a -> {
            throw new IndexOutOfBoundsException(ORIGINAL_MESSAGE);
    });


    @Test
    public void testTheResult() throws Throwable {
         Assert.assertEquals(sut.apply(100), testValue);
    }

    @Test
    public void testTupleCall() throws Throwable {

        LSingle<Integer> domainObject = Tuple4U.single(100);

        Object result = sut.tupleApply(domainObject);

            Assert.assertEquals(result, testValue);
    }

    @Test
    public void testNonNullApply() throws Throwable {
            Assert.assertSame(sut.nonNullApply(100), testValue);
    }

    @Test
    public void testNestingApplyUnchecked() throws Throwable {

        // then
        try {
            sutAlwaysThrowingUnchecked.nestingApply(100);
            Assert.fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            Assert.assertEquals(e.getClass(), IndexOutOfBoundsException.class);
            Assert.assertNull(e.getCause());
            Assert.assertEquals(e.getMessage(), ORIGINAL_MESSAGE);
        }
    }

    @Test
    public void testShovingApplyUnchecked() throws Throwable {

        // then
        try {
            sutAlwaysThrowingUnchecked.shovingApply(100);
            Assert.fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            Assert.assertEquals(e.getClass(), IndexOutOfBoundsException.class);
            Assert.assertNull(e.getCause());
            Assert.assertEquals(e.getMessage(), ORIGINAL_MESSAGE);
        }
    }

    @Test(expectedExceptions=NullPointerException.class, expectedExceptionsMessageRegExp="\\QEvaluated value by nonNullApply() method cannot be null (LFunction: R apply(T a)).\\E")
    public void testNonNullCapturesNull() throws Throwable {
        sutNull.nonNullApply(100);
    }


    @Test
    public void testFunctionalInterfaceDescription() throws Throwable {
        Assert.assertEquals(sut.functionalInterfaceDescription(), "LFunction: R apply(T a)");
    }

    @Test
    public void testFuncMethod() throws Throwable {
        Assert.assertTrue(LFunction.func(a -> testValue ) instanceof LFunction);
    
    }


    @Test
    public void testWrapStdMethod() throws Throwable {
        Assert.assertTrue(LFunction.wrap(jre) instanceof LFunction);
    }





    // <editor-fold desc="compose (functional)">

    @Test
    public void testCompose() throws Throwable {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LFunction<Integer,Integer> sutO = a -> {
                mainFunctionCalled.set(true);
                Assert.assertEquals(a, (Object) 90);
                return 100;
        };

        LFunction<Integer,Integer> before = p0 -> {
            Assert.assertEquals(p0, (Object) 80);
            beforeCalls.incrementAndGet();
            return 90;
        };

        //when
        LFunction<Integer,Integer> function = sutO.compose(before);
        function.apply(80);

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
        LFunction<Integer,Integer> sutO = a -> {
                mainFunctionCalled.set(true);
                Assert.assertEquals(a, (Object) 80);
                return 90;
        };

        LFunction<Integer,Integer> thenFunction = p -> {
                thenFunctionCalled.set(true);
                // Integer
                Assert.assertEquals(p, (Object) 90);
                // Integer
                return 100;
        };

        //when
        LFunction<Integer,Integer> function = sutO.then(thenFunction);
        Integer finalValue = function.apply(80);

        //then - finals
        Assert.assertEquals(finalValue, (Object) 100);
        Assert.assertTrue(mainFunctionCalled.get());
        Assert.assertTrue(thenFunctionCalled.get());

    }



    @Test
    public void testThenConsume1() throws Throwable  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);

        //given (+ some assertions)
        LFunction<Integer,Integer> sutO = a -> {
                mainFunctionCalled.set(true);
                Assert.assertEquals(a, (Object) 80);
                return 90;
        };

        LConsumer<Integer> thenFunction = p -> {
                thenFunctionCalled.set(true);
                // Integer
                Assert.assertEquals(p, (Object) 90);
        };

        //when
        LConsumer<Integer> function = sutO.thenConsume(thenFunction);
        function.accept(80);

        //then - finals
        Assert.assertTrue(mainFunctionCalled.get());
        Assert.assertTrue(thenFunctionCalled.get());

    }



    @Test
    public void testThenToByte2() throws Throwable  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);

        //given (+ some assertions)
        LFunction<Integer,Integer> sutO = a -> {
                mainFunctionCalled.set(true);
                Assert.assertEquals(a, (Object) 80);
                return 90;
        };

        LToByteFunction<Integer> thenFunction = p -> {
                thenFunctionCalled.set(true);
                // Integer
                Assert.assertEquals(p, (Object) 90);
                // byte
                return (byte)100;
        };

        //when
        LToByteFunction<Integer> function = sutO.thenToByte(thenFunction);
        byte finalValue = function.applyAsByte(80);

        //then - finals
        Assert.assertEquals(finalValue, (Object) (byte)100);
        Assert.assertTrue(mainFunctionCalled.get());
        Assert.assertTrue(thenFunctionCalled.get());

    }



    @Test
    public void testThenToSrt3() throws Throwable  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);

        //given (+ some assertions)
        LFunction<Integer,Integer> sutO = a -> {
                mainFunctionCalled.set(true);
                Assert.assertEquals(a, (Object) 80);
                return 90;
        };

        LToSrtFunction<Integer> thenFunction = p -> {
                thenFunctionCalled.set(true);
                // Integer
                Assert.assertEquals(p, (Object) 90);
                // short
                return (short)100;
        };

        //when
        LToSrtFunction<Integer> function = sutO.thenToSrt(thenFunction);
        short finalValue = function.applyAsSrt(80);

        //then - finals
        Assert.assertEquals(finalValue, (Object) (short)100);
        Assert.assertTrue(mainFunctionCalled.get());
        Assert.assertTrue(thenFunctionCalled.get());

    }



    @Test
    public void testThenToInt4() throws Throwable  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);

        //given (+ some assertions)
        LFunction<Integer,Integer> sutO = a -> {
                mainFunctionCalled.set(true);
                Assert.assertEquals(a, (Object) 80);
                return 90;
        };

        LToIntFunction<Integer> thenFunction = p -> {
                thenFunctionCalled.set(true);
                // Integer
                Assert.assertEquals(p, (Object) 90);
                // int
                return 100;
        };

        //when
        LToIntFunction<Integer> function = sutO.thenToInt(thenFunction);
        int finalValue = function.applyAsInt(80);

        //then - finals
        Assert.assertEquals(finalValue, (Object) 100);
        Assert.assertTrue(mainFunctionCalled.get());
        Assert.assertTrue(thenFunctionCalled.get());

    }



    @Test
    public void testThenToLong5() throws Throwable  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);

        //given (+ some assertions)
        LFunction<Integer,Integer> sutO = a -> {
                mainFunctionCalled.set(true);
                Assert.assertEquals(a, (Object) 80);
                return 90;
        };

        LToLongFunction<Integer> thenFunction = p -> {
                thenFunctionCalled.set(true);
                // Integer
                Assert.assertEquals(p, (Object) 90);
                // long
                return 100L;
        };

        //when
        LToLongFunction<Integer> function = sutO.thenToLong(thenFunction);
        long finalValue = function.applyAsLong(80);

        //then - finals
        Assert.assertEquals(finalValue, (Object) 100L);
        Assert.assertTrue(mainFunctionCalled.get());
        Assert.assertTrue(thenFunctionCalled.get());

    }



    @Test
    public void testThenToFlt6() throws Throwable  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);

        //given (+ some assertions)
        LFunction<Integer,Integer> sutO = a -> {
                mainFunctionCalled.set(true);
                Assert.assertEquals(a, (Object) 80);
                return 90;
        };

        LToFltFunction<Integer> thenFunction = p -> {
                thenFunctionCalled.set(true);
                // Integer
                Assert.assertEquals(p, (Object) 90);
                // float
                return 100f;
        };

        //when
        LToFltFunction<Integer> function = sutO.thenToFlt(thenFunction);
        float finalValue = function.applyAsFlt(80);

        //then - finals
        Assert.assertEquals(finalValue, (Object) 100f);
        Assert.assertTrue(mainFunctionCalled.get());
        Assert.assertTrue(thenFunctionCalled.get());

    }



    @Test
    public void testThenToDbl7() throws Throwable  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);

        //given (+ some assertions)
        LFunction<Integer,Integer> sutO = a -> {
                mainFunctionCalled.set(true);
                Assert.assertEquals(a, (Object) 80);
                return 90;
        };

        LToDblFunction<Integer> thenFunction = p -> {
                thenFunctionCalled.set(true);
                // Integer
                Assert.assertEquals(p, (Object) 90);
                // double
                return 100d;
        };

        //when
        LToDblFunction<Integer> function = sutO.thenToDbl(thenFunction);
        double finalValue = function.applyAsDbl(80);

        //then - finals
        Assert.assertEquals(finalValue, (Object) 100d);
        Assert.assertTrue(mainFunctionCalled.get());
        Assert.assertTrue(thenFunctionCalled.get());

    }



    @Test
    public void testThenToChar8() throws Throwable  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);

        //given (+ some assertions)
        LFunction<Integer,Integer> sutO = a -> {
                mainFunctionCalled.set(true);
                Assert.assertEquals(a, (Object) 80);
                return 90;
        };

        LToCharFunction<Integer> thenFunction = p -> {
                thenFunctionCalled.set(true);
                // Integer
                Assert.assertEquals(p, (Object) 90);
                // char
                return '\u0100';
        };

        //when
        LToCharFunction<Integer> function = sutO.thenToChar(thenFunction);
        char finalValue = function.applyAsChar(80);

        //then - finals
        Assert.assertEquals(finalValue, (Object) '\u0100');
        Assert.assertTrue(mainFunctionCalled.get());
        Assert.assertTrue(thenFunctionCalled.get());

    }



    @Test
    public void testThenToBool9() throws Throwable  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);

        //given (+ some assertions)
        LFunction<Integer,Integer> sutO = a -> {
                mainFunctionCalled.set(true);
                Assert.assertEquals(a, (Object) 80);
                return 90;
        };

        LPredicate<Integer> thenFunction = p -> {
                thenFunctionCalled.set(true);
                // Integer
                Assert.assertEquals(p, (Object) 90);
                // boolean
                return true;
        };

        //when
        LPredicate<Integer> function = sutO.thenToBool(thenFunction);
        boolean finalValue = function.test(80);

        //then - finals
        Assert.assertEquals(finalValue, (Object) true);
        Assert.assertTrue(mainFunctionCalled.get());
        Assert.assertTrue(thenFunctionCalled.get());

    }



    // </editor-fold>
    @Test
    public void identity() throws Throwable {
        LFunction<Integer,Integer> identityFunction = LFunction.identity();
        Assert.assertEquals(identityFunction.apply(8), (Object) 8);
    }



    @Test(expectedExceptions = RuntimeException.class)
    public void testShove() {

        // given
        LFunction<Integer,Integer> sutThrowing = LFunction.func(a -> {
            throw new UnsupportedOperationException();
        });

        // when
        sutThrowing.shovingApply(100);
    }


    @Test
    public void testToString() throws Throwable {

        Assert.assertTrue(sut.toString().startsWith(this.getClass().getName()+"$"));

        Assert.assertTrue(String.format("%s", sut).contains("LFunction: R apply(T a)"));
    
    }


    @Test
    public void isThrowing() {

        Assert.assertFalse(sut.isThrowing());
    }

}
