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

package eu.lunisolar.magma.func.operator.binary;

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
public class LIntBinaryOperatorTest {
    private static final String ORIGINAL_MESSAGE = "Original message";
    private static final String EXCEPTION_WAS_WRAPPED = "Exception was wrapped.";
    private static final String NO_EXCEPTION_WERE_THROWN = "No exception were thrown.";

    private int testValue = 100;



    private LIntBinaryOperator sut = new LIntBinaryOperator(){
        public  int applyAsIntX(int a1,int a2)  {
            return testValue;
        }
    };



    private IntBinaryOperator jre = (a1,a2) -> testValue;


    private LIntBinaryOperator sutAlwaysThrowing = LIntBinaryOperator.intBinaryOp((a1,a2) -> {
            throw new ParseException(ORIGINAL_MESSAGE, 0);
    });

    private LIntBinaryOperator sutAlwaysThrowingUnchecked = LIntBinaryOperator.intBinaryOp((a1,a2) -> {
            throw new IndexOutOfBoundsException(ORIGINAL_MESSAGE);
    });


    @Test
    public void testTheResult() throws Throwable {
         Assert.assertEquals(sut.applyAsInt(100,100), testValue);
    }

    @Test
    public void testTupleCall() throws Throwable {

        LIntPair domainObject = Tuple4U.intPair(100,100);

        Object result = sut.tupleApplyAsInt(domainObject);

            Assert.assertEquals(result, testValue);
    }

    @Test
    public void testNonNullApplyAsInt() throws Throwable {
            Assert.assertEquals(sut.nonNullApplyAsInt(100,100), testValue);
    }

    @Test
    public void testNestingApplyAsIntUnchecked() throws Throwable {

        // then
        try {
            sutAlwaysThrowingUnchecked.nestingApplyAsInt(100,100);
            Assert.fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            Assert.assertEquals(e.getClass(), IndexOutOfBoundsException.class);
            Assert.assertNull(e.getCause());
            Assert.assertEquals(e.getMessage(), ORIGINAL_MESSAGE);
        }
    }

    @Test
    public void testShovingApplyAsIntUnchecked() throws Throwable {

        // then
        try {
            sutAlwaysThrowingUnchecked.shovingApplyAsInt(100,100);
            Assert.fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            Assert.assertEquals(e.getClass(), IndexOutOfBoundsException.class);
            Assert.assertNull(e.getCause());
            Assert.assertEquals(e.getMessage(), ORIGINAL_MESSAGE);
        }
    }


    @Test
    public void testFunctionalInterfaceDescription() throws Throwable {
        Assert.assertEquals(sut.functionalInterfaceDescription(), "LIntBinaryOperator: int applyAsInt(int a1,int a2)");
    }

    @Test
    public void testIntBinaryOpMethod() throws Throwable {
        Assert.assertTrue(LIntBinaryOperator.intBinaryOp((a1,a2) -> testValue ) instanceof LIntBinaryOperator);
    
    }


    @Test
    public void testWrapStdMethod() throws Throwable {
        Assert.assertTrue(LIntBinaryOperator.wrap(jre) instanceof LIntBinaryOperator);
    }




    @Test
    public void minBy() throws Throwable  {
        //when
        LIntBinaryOperator min =  LIntBinaryOperator.minBy(Integer::compare);

        //then
        Assert.assertEquals(min.applyAsInt(0, 56), (Object) 0);
        Assert.assertEquals(min.applyAsInt(56, 0), (Object) 0);

    }

    @Test
    public void maxBy() throws Throwable  {
        //when
        LIntBinaryOperator max =  LIntBinaryOperator.maxBy(Integer::compare);

        //then
        Assert.assertEquals(max.applyAsInt(0, 56), (Object) 56);
        Assert.assertEquals(max.applyAsInt(56, 0), (Object) 56);
    }


    @Test
    public void testMin() throws Throwable {
        //given
        int valueSmall = 10;
        int valueBig = 100;

        //when
        LIntBinaryOperator min = LIntBinaryOperator.min();

        //then
        Assert.assertEquals(min.applyAsInt(valueSmall, valueBig), valueSmall);

        Assert.assertEquals(min.applyAsInt(valueBig, valueSmall), valueSmall);
    }

    @Test
    public void testMax() throws Throwable {
        //given
        int valueSmall = 10;
        int valueBig = 100;

        //when
        LIntBinaryOperator max = LIntBinaryOperator.max();

        //then
        Assert.assertEquals(max.applyAsInt(valueSmall, valueBig), valueBig);

        Assert.assertEquals(max.applyAsInt(valueBig, valueSmall), valueBig);
    }


    // <editor-fold desc="compose (functional)">

    @Test
    public void testCompose() throws Throwable {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LIntBinaryOperator sutO = (a1,a2) -> {
                mainFunctionCalled.set(true);
                Assert.assertEquals(a1, (Object) 90);
                Assert.assertEquals(a2, (Object) 91);
                return 100;
        };

        LIntUnaryOperator before1 = p0 -> {
            Assert.assertEquals(p0, (Object) 80);
            beforeCalls.incrementAndGet();
            return 90;
        };
        LIntUnaryOperator before2 = p1 -> {
            Assert.assertEquals(p1, (Object) 81);
            beforeCalls.incrementAndGet();
            return 91;
        };

        //when
        LIntBinaryOperator function = sutO.compose(before1,before2);
        function.applyAsInt(80,81);

        //then - finals
        Assert.assertTrue(mainFunctionCalled.get());
        Assert.assertEquals(beforeCalls.get(), 2);
    }


    @Test
    public void testUnboxingCompose() throws Throwable {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LIntBinaryOperator sutO = (a1,a2) -> {
                mainFunctionCalled.set(true);
                Assert.assertEquals(a1, (Object) 90);
                Assert.assertEquals(a2, (Object) 91);
                return 100;
        };

        LToIntFunction<Integer> before1 = p0 -> {
            Assert.assertEquals(p0, (Object) 80);
            beforeCalls.incrementAndGet();
            return 90;
        };
        LToIntFunction<Integer> before2 = p1 -> {
            Assert.assertEquals(p1, (Object) 81);
            beforeCalls.incrementAndGet();
            return 91;
        };

        //when
        LToIntBiFunction<Integer,Integer> function = sutO.unboxingCompose(before1,before2);
        function.applyAsInt(80,81);

        //then - finals
        Assert.assertTrue(mainFunctionCalled.get());
        Assert.assertEquals(beforeCalls.get(), 2);
    }

    // </editor-fold>



    // <editor-fold desc="then (functional)">

    @Test
    public void testThen0() throws Throwable  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);

        //given (+ some assertions)
        LIntBinaryOperator sutO = (a1,a2) -> {
                mainFunctionCalled.set(true);
                Assert.assertEquals(a1, (Object) 80);
                Assert.assertEquals(a2, (Object) 81);
                return 90;
        };

        LIntFunction<Integer> thenFunction = p -> {
                thenFunctionCalled.set(true);
                // int
                Assert.assertEquals(p, (Object) 90);
                // Integer
                return 100;
        };

        //when
        LBiIntFunction<Integer> function = sutO.then(thenFunction);
        Integer finalValue = function.apply(80,81);

        //then - finals
        Assert.assertEquals(finalValue, (Object) 100);
        Assert.assertTrue(mainFunctionCalled.get());
        Assert.assertTrue(thenFunctionCalled.get());

    }



    @Test
    public void testThenToInt1() throws Throwable  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);

        //given (+ some assertions)
        LIntBinaryOperator sutO = (a1,a2) -> {
                mainFunctionCalled.set(true);
                Assert.assertEquals(a1, (Object) 80);
                Assert.assertEquals(a2, (Object) 81);
                return 90;
        };

        LIntUnaryOperator thenFunction = p -> {
                thenFunctionCalled.set(true);
                // int
                Assert.assertEquals(p, (Object) 90);
                // int
                return 100;
        };

        //when
        LIntBinaryOperator function = sutO.thenToInt(thenFunction);
        int finalValue = function.applyAsInt(80,81);

        //then - finals
        Assert.assertEquals(finalValue, (Object) 100);
        Assert.assertTrue(mainFunctionCalled.get());
        Assert.assertTrue(thenFunctionCalled.get());

    }



    @Test
    public void testThenToBool2() throws Throwable  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);

        //given (+ some assertions)
        LIntBinaryOperator sutO = (a1,a2) -> {
                mainFunctionCalled.set(true);
                Assert.assertEquals(a1, (Object) 80);
                Assert.assertEquals(a2, (Object) 81);
                return 90;
        };

        LIntPredicate thenFunction = p -> {
                thenFunctionCalled.set(true);
                // int
                Assert.assertEquals(p, (Object) 90);
                // boolean
                return true;
        };

        //when
        LBiIntPredicate function = sutO.thenToBool(thenFunction);
        boolean finalValue = function.test(80,81);

        //then - finals
        Assert.assertEquals(finalValue, (Object) true);
        Assert.assertTrue(mainFunctionCalled.get());
        Assert.assertTrue(thenFunctionCalled.get());

    }



    // </editor-fold>


    @Test(expectedExceptions = RuntimeException.class)
    public void testShove() {

        // given
        LIntBinaryOperator sutThrowing = LIntBinaryOperator.intBinaryOp((a1,a2) -> {
            throw new UnsupportedOperationException();
        });

        // when
        sutThrowing.shovingApplyAsInt(100,100);
    }


    @Test
    public void testToString() throws Throwable {

        Assert.assertTrue(sut.toString().startsWith(this.getClass().getName()+"$"));

        Assert.assertTrue(String.format("%s", sut).contains("LIntBinaryOperator: int applyAsInt(int a1,int a2)"));
    
    }


    @Test
    public void isThrowing() {

        Assert.assertFalse(sut.isThrowing());
    }

}
