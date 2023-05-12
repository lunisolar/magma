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
public class LSrtTernaryOperatorTest {
    private static final String ORIGINAL_MESSAGE = "Original message";
    private static final String EXCEPTION_WAS_WRAPPED = "Exception was wrapped.";
    private static final String NO_EXCEPTION_WERE_THROWN = "No exception were thrown.";

    private short testValue = (short)100;



    private LSrtTernaryOperator sut = new LSrtTernaryOperator(){
        public  short applyAsSrtX(short a1,short a2,short a3)  {
            return testValue;
        }
    };




    private LSrtTernaryOperator sutAlwaysThrowing = LSrtTernaryOperator.srtTernaryOp((a1,a2,a3) -> {
            throw new ParseException(ORIGINAL_MESSAGE, 0);
    });

    private LSrtTernaryOperator sutAlwaysThrowingUnchecked = LSrtTernaryOperator.srtTernaryOp((a1,a2,a3) -> {
            throw new IndexOutOfBoundsException(ORIGINAL_MESSAGE);
    });


    @Test
    public void testTheResult() throws Throwable {
         Assert.assertEquals(sut.applyAsSrt((short)100,(short)100,(short)100), testValue);
    }

    @Test
    public void testTupleCall() throws Throwable {

        LSrtTriple domainObject = Tuple4U.srtTriple((short)100,(short)100,(short)100);

        Object result = sut.tupleApplyAsSrt(domainObject);

            Assert.assertEquals(result, testValue);
    }

    @Test
    public void testNonNullApplyAsSrt() throws Throwable {
            Assert.assertEquals(sut.nonNullApplyAsSrt((short)100,(short)100,(short)100), testValue);
    }

    @Test
    public void testNestingApplyAsSrtUnchecked() throws Throwable {

        // then
        try {
            sutAlwaysThrowingUnchecked.nestingApplyAsSrt((short)100,(short)100,(short)100);
            Assert.fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            Assert.assertEquals(e.getClass(), IndexOutOfBoundsException.class);
            Assert.assertNull(e.getCause());
            Assert.assertEquals(e.getMessage(), ORIGINAL_MESSAGE);
        }
    }

    @Test
    public void testShovingApplyAsSrtUnchecked() throws Throwable {

        // then
        try {
            sutAlwaysThrowingUnchecked.shovingApplyAsSrt((short)100,(short)100,(short)100);
            Assert.fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            Assert.assertEquals(e.getClass(), IndexOutOfBoundsException.class);
            Assert.assertNull(e.getCause());
            Assert.assertEquals(e.getMessage(), ORIGINAL_MESSAGE);
        }
    }


    @Test
    public void testFunctionalInterfaceDescription() throws Throwable {
        Assert.assertEquals(sut.functionalInterfaceDescription(), "LSrtTernaryOperator: short applyAsSrt(short a1,short a2,short a3)");
    }

    @Test
    public void testSrtTernaryOpMethod() throws Throwable {
        Assert.assertTrue(LSrtTernaryOperator.srtTernaryOp((a1,a2,a3) -> testValue ) instanceof LSrtTernaryOperator);
    
    }






    // <editor-fold desc="compose (functional)">

    @Test
    public void testCompose() throws Throwable {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LSrtTernaryOperator sutO = (a1,a2,a3) -> {
                mainFunctionCalled.set(true);
                Assert.assertEquals(a1, (Object) (short)90);
                Assert.assertEquals(a2, (Object) (short)91);
                Assert.assertEquals(a3, (Object) (short)92);
                return (short)100;
        };

        LSrtUnaryOperator before1 = p0 -> {
            Assert.assertEquals(p0, (Object) (short)80);
            beforeCalls.incrementAndGet();
            return (short)90;
        };
        LSrtUnaryOperator before2 = p1 -> {
            Assert.assertEquals(p1, (Object) (short)81);
            beforeCalls.incrementAndGet();
            return (short)91;
        };
        LSrtUnaryOperator before3 = p2 -> {
            Assert.assertEquals(p2, (Object) (short)82);
            beforeCalls.incrementAndGet();
            return (short)92;
        };

        //when
        LSrtTernaryOperator function = sutO.compose(before1,before2,before3);
        function.applyAsSrt((short)80,(short)81,(short)82);

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
        LSrtTernaryOperator sutO = (a1,a2,a3) -> {
                mainFunctionCalled.set(true);
                Assert.assertEquals(a1, (Object) (short)80);
                Assert.assertEquals(a2, (Object) (short)81);
                Assert.assertEquals(a3, (Object) (short)82);
                return (short)90;
        };

        LSrtFunction<Integer> thenFunction = p -> {
                thenFunctionCalled.set(true);
                // short
                Assert.assertEquals(p, (Object) (short)90);
                // Integer
                return 100;
        };

        //when
        LTriSrtFunction<Integer> function = sutO.then(thenFunction);
        Integer finalValue = function.apply((short)80,(short)81,(short)82);

        //then - finals
        Assert.assertEquals(finalValue, (Object) 100);
        Assert.assertTrue(mainFunctionCalled.get());
        Assert.assertTrue(thenFunctionCalled.get());

    }



    @Test
    public void testThenToSrt1() throws Throwable  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);

        //given (+ some assertions)
        LSrtTernaryOperator sutO = (a1,a2,a3) -> {
                mainFunctionCalled.set(true);
                Assert.assertEquals(a1, (Object) (short)80);
                Assert.assertEquals(a2, (Object) (short)81);
                Assert.assertEquals(a3, (Object) (short)82);
                return (short)90;
        };

        LSrtUnaryOperator thenFunction = p -> {
                thenFunctionCalled.set(true);
                // short
                Assert.assertEquals(p, (Object) (short)90);
                // short
                return (short)100;
        };

        //when
        LSrtTernaryOperator function = sutO.thenToSrt(thenFunction);
        short finalValue = function.applyAsSrt((short)80,(short)81,(short)82);

        //then - finals
        Assert.assertEquals(finalValue, (Object) (short)100);
        Assert.assertTrue(mainFunctionCalled.get());
        Assert.assertTrue(thenFunctionCalled.get());

    }



    @Test
    public void testThenToBool2() throws Throwable  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);

        //given (+ some assertions)
        LSrtTernaryOperator sutO = (a1,a2,a3) -> {
                mainFunctionCalled.set(true);
                Assert.assertEquals(a1, (Object) (short)80);
                Assert.assertEquals(a2, (Object) (short)81);
                Assert.assertEquals(a3, (Object) (short)82);
                return (short)90;
        };

        LSrtPredicate thenFunction = p -> {
                thenFunctionCalled.set(true);
                // short
                Assert.assertEquals(p, (Object) (short)90);
                // boolean
                return true;
        };

        //when
        LTriSrtPredicate function = sutO.thenToBool(thenFunction);
        boolean finalValue = function.test((short)80,(short)81,(short)82);

        //then - finals
        Assert.assertEquals(finalValue, (Object) true);
        Assert.assertTrue(mainFunctionCalled.get());
        Assert.assertTrue(thenFunctionCalled.get());

    }



    // </editor-fold>


    @Test(expectedExceptions = RuntimeException.class)
    public void testShove() {

        // given
        LSrtTernaryOperator sutThrowing = LSrtTernaryOperator.srtTernaryOp((a1,a2,a3) -> {
            throw new UnsupportedOperationException();
        });

        // when
        sutThrowing.shovingApplyAsSrt((short)100,(short)100,(short)100);
    }


    @Test
    public void testToString() throws Throwable {

        Assert.assertTrue(sut.toString().startsWith(this.getClass().getName()+"$"));

        Assert.assertTrue(String.format("%s", sut).contains("LSrtTernaryOperator: short applyAsSrt(short a1,short a2,short a3)"));
    
    }


    @Test
    public void isThrowing() {

        Assert.assertFalse(sut.isThrowing());
    }

}
