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
public class LFltTernaryOperatorTest {
    private static final String ORIGINAL_MESSAGE = "Original message";
    private static final String EXCEPTION_WAS_WRAPPED = "Exception was wrapped.";
    private static final String NO_EXCEPTION_WERE_THROWN = "No exception were thrown.";

    private float testValue = 100f;



    private LFltTernaryOperator sut = new LFltTernaryOperator(){
        public  float applyAsFltX(float a1,float a2,float a3)  {
            return testValue;
        }
    };




    private LFltTernaryOperator sutAlwaysThrowing = LFltTernaryOperator.fltTernaryOp((a1,a2,a3) -> {
            throw new ParseException(ORIGINAL_MESSAGE, 0);
    });

    private LFltTernaryOperator sutAlwaysThrowingUnchecked = LFltTernaryOperator.fltTernaryOp((a1,a2,a3) -> {
            throw new IndexOutOfBoundsException(ORIGINAL_MESSAGE);
    });


    @Test
    public void testTheResult() throws Throwable {
         Assert.assertEquals(sut.applyAsFlt(100f,100f,100f), testValue);
    }

    @Test
    public void testTupleCall() throws Throwable {

        LFltTriple domainObject = Tuple4U.fltTriple(100f,100f,100f);

        Object result = sut.tupleApplyAsFlt(domainObject);

            Assert.assertEquals(result, testValue);
    }

    @Test
    public void testNonNullApplyAsFlt() throws Throwable {
            Assert.assertEquals(sut.nonNullApplyAsFlt(100f,100f,100f), testValue);
    }

    @Test
    public void testNestingApplyAsFltUnchecked() throws Throwable {

        // then
        try {
            sutAlwaysThrowingUnchecked.nestingApplyAsFlt(100f,100f,100f);
            Assert.fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            Assert.assertEquals(e.getClass(), IndexOutOfBoundsException.class);
            Assert.assertNull(e.getCause());
            Assert.assertEquals(e.getMessage(), ORIGINAL_MESSAGE);
        }
    }

    @Test
    public void testShovingApplyAsFltUnchecked() throws Throwable {

        // then
        try {
            sutAlwaysThrowingUnchecked.shovingApplyAsFlt(100f,100f,100f);
            Assert.fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            Assert.assertEquals(e.getClass(), IndexOutOfBoundsException.class);
            Assert.assertNull(e.getCause());
            Assert.assertEquals(e.getMessage(), ORIGINAL_MESSAGE);
        }
    }


    @Test
    public void testFunctionalInterfaceDescription() throws Throwable {
        Assert.assertEquals(sut.functionalInterfaceDescription(), "LFltTernaryOperator: float applyAsFlt(float a1,float a2,float a3)");
    }

    @Test
    public void testFltTernaryOpMethod() throws Throwable {
        Assert.assertTrue(LFltTernaryOperator.fltTernaryOp((a1,a2,a3) -> testValue ) instanceof LFltTernaryOperator);
    
    }






    // <editor-fold desc="compose (functional)">

    @Test
    public void testCompose() throws Throwable {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LFltTernaryOperator sutO = (a1,a2,a3) -> {
                mainFunctionCalled.set(true);
                Assert.assertEquals(a1, (Object) 90f);
                Assert.assertEquals(a2, (Object) 91f);
                Assert.assertEquals(a3, (Object) 92f);
                return 100f;
        };

        LFltUnaryOperator before1 = p0 -> {
            Assert.assertEquals(p0, (Object) 80f);
            beforeCalls.incrementAndGet();
            return 90f;
        };
        LFltUnaryOperator before2 = p1 -> {
            Assert.assertEquals(p1, (Object) 81f);
            beforeCalls.incrementAndGet();
            return 91f;
        };
        LFltUnaryOperator before3 = p2 -> {
            Assert.assertEquals(p2, (Object) 82f);
            beforeCalls.incrementAndGet();
            return 92f;
        };

        //when
        LFltTernaryOperator function = sutO.compose(before1,before2,before3);
        function.applyAsFlt(80f,81f,82f);

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
        LFltTernaryOperator sutO = (a1,a2,a3) -> {
                mainFunctionCalled.set(true);
                Assert.assertEquals(a1, (Object) 80f);
                Assert.assertEquals(a2, (Object) 81f);
                Assert.assertEquals(a3, (Object) 82f);
                return 90f;
        };

        LFltFunction<Integer> thenFunction = p -> {
                thenFunctionCalled.set(true);
                // float
                Assert.assertEquals(p, (Object) 90f);
                // Integer
                return 100;
        };

        //when
        LTriFltFunction<Integer> function = sutO.then(thenFunction);
        Integer finalValue = function.apply(80f,81f,82f);

        //then - finals
        Assert.assertEquals(finalValue, (Object) 100);
        Assert.assertTrue(mainFunctionCalled.get());
        Assert.assertTrue(thenFunctionCalled.get());

    }



    @Test
    public void testThenToFlt1() throws Throwable  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);

        //given (+ some assertions)
        LFltTernaryOperator sutO = (a1,a2,a3) -> {
                mainFunctionCalled.set(true);
                Assert.assertEquals(a1, (Object) 80f);
                Assert.assertEquals(a2, (Object) 81f);
                Assert.assertEquals(a3, (Object) 82f);
                return 90f;
        };

        LFltUnaryOperator thenFunction = p -> {
                thenFunctionCalled.set(true);
                // float
                Assert.assertEquals(p, (Object) 90f);
                // float
                return 100f;
        };

        //when
        LFltTernaryOperator function = sutO.thenToFlt(thenFunction);
        float finalValue = function.applyAsFlt(80f,81f,82f);

        //then - finals
        Assert.assertEquals(finalValue, (Object) 100f);
        Assert.assertTrue(mainFunctionCalled.get());
        Assert.assertTrue(thenFunctionCalled.get());

    }



    @Test
    public void testThenToBool2() throws Throwable  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);

        //given (+ some assertions)
        LFltTernaryOperator sutO = (a1,a2,a3) -> {
                mainFunctionCalled.set(true);
                Assert.assertEquals(a1, (Object) 80f);
                Assert.assertEquals(a2, (Object) 81f);
                Assert.assertEquals(a3, (Object) 82f);
                return 90f;
        };

        LFltPredicate thenFunction = p -> {
                thenFunctionCalled.set(true);
                // float
                Assert.assertEquals(p, (Object) 90f);
                // boolean
                return true;
        };

        //when
        LTriFltPredicate function = sutO.thenToBool(thenFunction);
        boolean finalValue = function.test(80f,81f,82f);

        //then - finals
        Assert.assertEquals(finalValue, (Object) true);
        Assert.assertTrue(mainFunctionCalled.get());
        Assert.assertTrue(thenFunctionCalled.get());

    }



    // </editor-fold>


    @Test(expectedExceptions = RuntimeException.class)
    public void testShove() {

        // given
        LFltTernaryOperator sutThrowing = LFltTernaryOperator.fltTernaryOp((a1,a2,a3) -> {
            throw new UnsupportedOperationException();
        });

        // when
        sutThrowing.shovingApplyAsFlt(100f,100f,100f);
    }


    @Test
    public void testToString() throws Throwable {

        Assert.assertTrue(sut.toString().startsWith(this.getClass().getName()+"$"));

        Assert.assertTrue(String.format("%s", sut).contains("LFltTernaryOperator: float applyAsFlt(float a1,float a2,float a3)"));
    
    }


    @Test
    public void isThrowing() {

        Assert.assertFalse(sut.isThrowing());
    }

}
