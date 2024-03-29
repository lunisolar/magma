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

package eu.lunisolar.magma.func.operator.ternary;

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
public class LLongTernaryOperatorTest {
    private static final String ORIGINAL_MESSAGE = "Original message";
    private static final String EXCEPTION_WAS_WRAPPED = "Exception was wrapped.";
    private static final String NO_EXCEPTION_WERE_THROWN = "No exception were thrown.";

    private long testValue = 100L;



    private LLongTernaryOperator sut = new LLongTernaryOperator(){
        public  long applyAsLongX(long a1,long a2,long a3)  {
            return testValue;
        }
    };




    private LLongTernaryOperator sutAlwaysThrowing = LLongTernaryOperator.longTernaryOp((a1,a2,a3) -> {
            throw new ParseException(ORIGINAL_MESSAGE, 0);
    });

    private LLongTernaryOperator sutAlwaysThrowingUnchecked = LLongTernaryOperator.longTernaryOp((a1,a2,a3) -> {
            throw new IndexOutOfBoundsException(ORIGINAL_MESSAGE);
    });


    @Test
    public void testTheResult() throws Throwable {
         Assert.assertEquals(sut.applyAsLong(100L,100L,100L), testValue);
    }

    @Test
    public void testTupleCall() throws Throwable {

        LLongTriple domainObject = Tuple4U.longTriple(100L,100L,100L);

        Object result = sut.tupleApplyAsLong(domainObject);

            Assert.assertEquals(result, testValue);
    }

    @Test
    public void testNonNullApplyAsLong() throws Throwable {
            Assert.assertEquals(sut.nonNullApplyAsLong(100L,100L,100L), testValue);
    }

    @Test
    public void testNestingApplyAsLongUnchecked() throws Throwable {

        // then
        try {
            sutAlwaysThrowingUnchecked.nestingApplyAsLong(100L,100L,100L);
            Assert.fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            Assert.assertEquals(e.getClass(), IndexOutOfBoundsException.class);
            Assert.assertNull(e.getCause());
            Assert.assertEquals(e.getMessage(), ORIGINAL_MESSAGE);
        }
    }

    @Test
    public void testShovingApplyAsLongUnchecked() throws Throwable {

        // then
        try {
            sutAlwaysThrowingUnchecked.shovingApplyAsLong(100L,100L,100L);
            Assert.fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            Assert.assertEquals(e.getClass(), IndexOutOfBoundsException.class);
            Assert.assertNull(e.getCause());
            Assert.assertEquals(e.getMessage(), ORIGINAL_MESSAGE);
        }
    }


    @Test
    public void testFunctionalInterfaceDescription() throws Throwable {
        Assert.assertEquals(sut.functionalInterfaceDescription(), "LLongTernaryOperator: long applyAsLong(long a1,long a2,long a3)");
    }

    @Test
    public void testLongTernaryOpMethod() throws Throwable {
        Assert.assertTrue(LLongTernaryOperator.longTernaryOp((a1,a2,a3) -> testValue ) instanceof LLongTernaryOperator);
    
    }






    // <editor-fold desc="compose (functional)">

    @Test
    public void testCompose() throws Throwable {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LLongTernaryOperator sutO = (a1,a2,a3) -> {
                mainFunctionCalled.set(true);
                Assert.assertEquals(a1, (Object) 90L);
                Assert.assertEquals(a2, (Object) 91L);
                Assert.assertEquals(a3, (Object) 92L);
                return 100L;
        };

        LLongUnaryOperator before1 = p0 -> {
            Assert.assertEquals(p0, (Object) 80L);
            beforeCalls.incrementAndGet();
            return 90L;
        };
        LLongUnaryOperator before2 = p1 -> {
            Assert.assertEquals(p1, (Object) 81L);
            beforeCalls.incrementAndGet();
            return 91L;
        };
        LLongUnaryOperator before3 = p2 -> {
            Assert.assertEquals(p2, (Object) 82L);
            beforeCalls.incrementAndGet();
            return 92L;
        };

        //when
        LLongTernaryOperator function = sutO.compose(before1,before2,before3);
        function.applyAsLong(80L,81L,82L);

        //then - finals
        Assert.assertTrue(mainFunctionCalled.get());
        Assert.assertEquals(beforeCalls.get(), 3);
    }

    // </editor-fold>



    // <editor-fold desc="then (functional)">

    @Test
    public void testThen0() throws Throwable  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);

        //given (+ some assertions)
        LLongTernaryOperator sutO = (a1,a2,a3) -> {
                mainFunctionCalled.set(true);
                Assert.assertEquals(a1, (Object) 80L);
                Assert.assertEquals(a2, (Object) 81L);
                Assert.assertEquals(a3, (Object) 82L);
                return 90L;
        };

        LLongFunction<Integer> thenFunction = p -> {
                thenFunctionCalled.set(true);
                // long
                Assert.assertEquals(p, (Object) 90L);
                // Integer
                return 100;
        };

        //when
        LTriLongFunction<Integer> function = sutO.then(thenFunction);
        Integer finalValue = function.apply(80L,81L,82L);

        //then - finals
        Assert.assertEquals(finalValue, (Object) 100);
        Assert.assertTrue(mainFunctionCalled.get());
        Assert.assertTrue(thenFunctionCalled.get());

    }



    @Test
    public void testThenToLong1() throws Throwable  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);

        //given (+ some assertions)
        LLongTernaryOperator sutO = (a1,a2,a3) -> {
                mainFunctionCalled.set(true);
                Assert.assertEquals(a1, (Object) 80L);
                Assert.assertEquals(a2, (Object) 81L);
                Assert.assertEquals(a3, (Object) 82L);
                return 90L;
        };

        LLongUnaryOperator thenFunction = p -> {
                thenFunctionCalled.set(true);
                // long
                Assert.assertEquals(p, (Object) 90L);
                // long
                return 100L;
        };

        //when
        LLongTernaryOperator function = sutO.thenToLong(thenFunction);
        long finalValue = function.applyAsLong(80L,81L,82L);

        //then - finals
        Assert.assertEquals(finalValue, (Object) 100L);
        Assert.assertTrue(mainFunctionCalled.get());
        Assert.assertTrue(thenFunctionCalled.get());

    }



    @Test
    public void testThenToBool2() throws Throwable  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);

        //given (+ some assertions)
        LLongTernaryOperator sutO = (a1,a2,a3) -> {
                mainFunctionCalled.set(true);
                Assert.assertEquals(a1, (Object) 80L);
                Assert.assertEquals(a2, (Object) 81L);
                Assert.assertEquals(a3, (Object) 82L);
                return 90L;
        };

        LLongPredicate thenFunction = p -> {
                thenFunctionCalled.set(true);
                // long
                Assert.assertEquals(p, (Object) 90L);
                // boolean
                return true;
        };

        //when
        LTriLongPredicate function = sutO.thenToBool(thenFunction);
        boolean finalValue = function.test(80L,81L,82L);

        //then - finals
        Assert.assertEquals(finalValue, (Object) true);
        Assert.assertTrue(mainFunctionCalled.get());
        Assert.assertTrue(thenFunctionCalled.get());

    }



    // </editor-fold>


    @Test(expectedExceptions = RuntimeException.class)
    public void testShove() {

        // given
        LLongTernaryOperator sutThrowing = LLongTernaryOperator.longTernaryOp((a1,a2,a3) -> {
            throw new UnsupportedOperationException();
        });

        // when
        sutThrowing.shovingApplyAsLong(100L,100L,100L);
    }


    @Test
    public void testToString() throws Throwable {

        Assert.assertTrue(sut.toString().startsWith(this.getClass().getName()+"$"));

        Assert.assertTrue(String.format("%s", sut).contains("LLongTernaryOperator: long applyAsLong(long a1,long a2,long a3)"));
    
    }


    @Test
    public void isThrowing() {

        Assert.assertFalse(sut.isThrowing());
    }

}
