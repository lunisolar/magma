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

package eu.lunisolar.magma.func.predicate;

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
public class LTriBytePredicateTest {
    private static final String ORIGINAL_MESSAGE = "Original message";
    private static final String EXCEPTION_WAS_WRAPPED = "Exception was wrapped.";
    private static final String NO_EXCEPTION_WERE_THROWN = "No exception were thrown.";

    private boolean testValue = true;



    private LTriBytePredicate sut = new LTriBytePredicate(){
        public  boolean testX(byte a1,byte a2,byte a3)  {
            return testValue;
        }
    };




    private LTriBytePredicate sutAlwaysThrowing = LTriBytePredicate.triBytePred((a1,a2,a3) -> {
            throw new ParseException(ORIGINAL_MESSAGE, 0);
    });

    private LTriBytePredicate sutAlwaysThrowingUnchecked = LTriBytePredicate.triBytePred((a1,a2,a3) -> {
            throw new IndexOutOfBoundsException(ORIGINAL_MESSAGE);
    });


    @Test
    public void testTheResult() throws Throwable {
         Assert.assertEquals(sut.test((byte)100,(byte)100,(byte)100), testValue);
    }

    @Test
    public void testTupleCall() throws Throwable {

        LByteTriple domainObject = Tuple4U.byteTriple((byte)100,(byte)100,(byte)100);

        Object result = sut.tupleTest(domainObject);

            Assert.assertEquals(result, testValue);
    }

    @Test
    public void testNonNullTest() throws Throwable {
            Assert.assertEquals(sut.nonNullTest((byte)100,(byte)100,(byte)100), testValue);
    }

    @Test
    public void testNestingTestUnchecked() throws Throwable {

        // then
        try {
            sutAlwaysThrowingUnchecked.nestingTest((byte)100,(byte)100,(byte)100);
            Assert.fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            Assert.assertEquals(e.getClass(), IndexOutOfBoundsException.class);
            Assert.assertNull(e.getCause());
            Assert.assertEquals(e.getMessage(), ORIGINAL_MESSAGE);
        }
    }

    @Test
    public void testShovingTestUnchecked() throws Throwable {

        // then
        try {
            sutAlwaysThrowingUnchecked.shovingTest((byte)100,(byte)100,(byte)100);
            Assert.fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            Assert.assertEquals(e.getClass(), IndexOutOfBoundsException.class);
            Assert.assertNull(e.getCause());
            Assert.assertEquals(e.getMessage(), ORIGINAL_MESSAGE);
        }
    }

    @Test
    public void testApplyAsBooleanShouldNotModifyValue() throws Throwable {
        Assert.assertEquals(sut.doApplyAsBoolean((byte)100,(byte)100,(byte)100), testValue);
    }


    @Test
    public void testFunctionalInterfaceDescription() throws Throwable {
        Assert.assertEquals(sut.functionalInterfaceDescription(), "LTriBytePredicate: boolean test(byte a1,byte a2,byte a3)");
    }

    @Test
    public void testTriBytePredMethod() throws Throwable {
        Assert.assertTrue(LTriBytePredicate.triBytePred((a1,a2,a3) -> testValue ) instanceof LTriBytePredicate);
    
    }




    @Test
    public void testnegate() throws Throwable {
        Assert.assertEquals(sut.negate().test((byte)100,(byte)100,(byte)100), !testValue);
    }

    @DataProvider(name="boolean permutations")
    public Object[][] permuations() {
       return new Object[][] {
            // b1 , b2   , AND  , OR   , XOR
            {false, false, false, false, false },
            {true , false, false, true , true },
            {false, true , false, true , true },
            {true , true , true , true , false },
       };
    }

    @Test(dataProvider="boolean permutations")
    public void testAndOrXor(final boolean f1Result, final boolean f2Result, final boolean andResult, final boolean orResult, final boolean xorResult) throws Throwable {

        //given
        LTriBytePredicate fun1 = LTriBytePredicate.triBytePred((a1,a2,a3) -> f1Result);
        LTriBytePredicate fun2 = LTriBytePredicate.triBytePred((a1,a2,a3) -> f2Result);

        //when
        LTriBytePredicate andFunction = fun1.and(fun2);
        LTriBytePredicate orFunction = fun1.or(fun2);
        LTriBytePredicate xorFunction = fun1.xor(fun2);

        //then
        Assert.assertEquals(andFunction.test((byte)100,(byte)100,(byte)100), andResult);

        Assert.assertEquals(orFunction.test((byte)100,(byte)100,(byte)100), orResult);

        Assert.assertEquals(xorFunction.test((byte)100,(byte)100,(byte)100), xorResult);
    }

    @Test
    public void testIsEqual() throws Throwable  {
        //when
        LTriBytePredicate equals = LTriBytePredicate.isEqual((byte)1,(byte)1,(byte)1);

        //then
        Assert.assertTrue(equals.test((byte)1,(byte)1,(byte)1));

        Assert.assertFalse(equals.test((byte)0,(byte)0,(byte)0));
    }



    // <editor-fold desc="compose (functional)">

    @Test
    public void testCompose() throws Throwable {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LTriBytePredicate sutO = (a1,a2,a3) -> {
                mainFunctionCalled.set(true);
                Assert.assertEquals(a1, (Object) (byte)90);
                Assert.assertEquals(a2, (Object) (byte)91);
                Assert.assertEquals(a3, (Object) (byte)92);
                return true;
        };

        LByteUnaryOperator before1 = p0 -> {
            Assert.assertEquals(p0, (Object) (byte)80);
            beforeCalls.incrementAndGet();
            return (byte)90;
        };
        LByteUnaryOperator before2 = p1 -> {
            Assert.assertEquals(p1, (Object) (byte)81);
            beforeCalls.incrementAndGet();
            return (byte)91;
        };
        LByteUnaryOperator before3 = p2 -> {
            Assert.assertEquals(p2, (Object) (byte)82);
            beforeCalls.incrementAndGet();
            return (byte)92;
        };

        //when
        LTriBytePredicate function = sutO.compose(before1,before2,before3);
        function.test((byte)80,(byte)81,(byte)82);

        //then - finals
        Assert.assertTrue(mainFunctionCalled.get());
        Assert.assertEquals(beforeCalls.get(), 3);
    }


    @Test
    public void testUnboxingCompose() throws Throwable {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LTriBytePredicate sutO = (a1,a2,a3) -> {
                mainFunctionCalled.set(true);
                Assert.assertEquals(a1, (Object) (byte)90);
                Assert.assertEquals(a2, (Object) (byte)91);
                Assert.assertEquals(a3, (Object) (byte)92);
                return true;
        };

        LToByteFunction<Integer> before1 = p0 -> {
            Assert.assertEquals(p0, (Object) 80);
            beforeCalls.incrementAndGet();
            return (byte)90;
        };
        LToByteFunction<Integer> before2 = p1 -> {
            Assert.assertEquals(p1, (Object) 81);
            beforeCalls.incrementAndGet();
            return (byte)91;
        };
        LToByteFunction<Integer> before3 = p2 -> {
            Assert.assertEquals(p2, (Object) 82);
            beforeCalls.incrementAndGet();
            return (byte)92;
        };

        //when
        LTriPredicate<Integer,Integer,Integer> function = sutO.unboxingCompose(before1,before2,before3);
        function.test(80,81,82);

        //then - finals
        Assert.assertTrue(mainFunctionCalled.get());
        Assert.assertEquals(beforeCalls.get(), 3);
    }

    // </editor-fold>



    // <editor-fold desc="then (functional)">

    @Test
    public void testBoolToTriByteFunc0() throws Throwable  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);

        //given (+ some assertions)
        LTriBytePredicate sutO = (a1,a2,a3) -> {
                mainFunctionCalled.set(true);
                Assert.assertEquals(a1, (Object) (byte)80);
                Assert.assertEquals(a2, (Object) (byte)81);
                Assert.assertEquals(a3, (Object) (byte)82);
                return true;
        };

        LBoolFunction<Integer> thenFunction = p -> {
                thenFunctionCalled.set(true);
                // boolean
                Assert.assertEquals(p, (Object) true);
                // Integer
                return 100;
        };

        //when
        LTriByteFunction<Integer> function = sutO.boolToTriByteFunc(thenFunction);
        Integer finalValue = function.apply((byte)80,(byte)81,(byte)82);

        //then - finals
        Assert.assertEquals(finalValue, (Object) 100);
        Assert.assertTrue(mainFunctionCalled.get());
        Assert.assertTrue(thenFunctionCalled.get());

    }



    @Test
    public void testBoolToByteTernaryOp1() throws Throwable  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);

        //given (+ some assertions)
        LTriBytePredicate sutO = (a1,a2,a3) -> {
                mainFunctionCalled.set(true);
                Assert.assertEquals(a1, (Object) (byte)80);
                Assert.assertEquals(a2, (Object) (byte)81);
                Assert.assertEquals(a3, (Object) (byte)82);
                return true;
        };

        LBoolToByteFunction thenFunction = p -> {
                thenFunctionCalled.set(true);
                // boolean
                Assert.assertEquals(p, (Object) true);
                // byte
                return (byte)100;
        };

        //when
        LByteTernaryOperator function = sutO.boolToByteTernaryOp(thenFunction);
        byte finalValue = function.applyAsByte((byte)80,(byte)81,(byte)82);

        //then - finals
        Assert.assertEquals(finalValue, (Object) (byte)100);
        Assert.assertTrue(mainFunctionCalled.get());
        Assert.assertTrue(thenFunctionCalled.get());

    }



    @Test
    public void testBoolToTriBytePred2() throws Throwable  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);

        //given (+ some assertions)
        LTriBytePredicate sutO = (a1,a2,a3) -> {
                mainFunctionCalled.set(true);
                Assert.assertEquals(a1, (Object) (byte)80);
                Assert.assertEquals(a2, (Object) (byte)81);
                Assert.assertEquals(a3, (Object) (byte)82);
                return true;
        };

        LLogicalOperator thenFunction = p -> {
                thenFunctionCalled.set(true);
                // boolean
                Assert.assertEquals(p, (Object) true);
                // boolean
                return true;
        };

        //when
        LTriBytePredicate function = sutO.boolToTriBytePred(thenFunction);
        boolean finalValue = function.test((byte)80,(byte)81,(byte)82);

        //then - finals
        Assert.assertEquals(finalValue, (Object) true);
        Assert.assertTrue(mainFunctionCalled.get());
        Assert.assertTrue(thenFunctionCalled.get());

    }



    // </editor-fold>


    @Test(expectedExceptions = RuntimeException.class)
    public void testShove() {

        // given
        LTriBytePredicate sutThrowing = LTriBytePredicate.triBytePred((a1,a2,a3) -> {
            throw new UnsupportedOperationException();
        });

        // when
        sutThrowing.shovingTest((byte)100,(byte)100,(byte)100);
    }


    @Test
    public void testToString() throws Throwable {

        Assert.assertTrue(sut.toString().startsWith(this.getClass().getName()+"$"));

        Assert.assertTrue(String.format("%s", sut).contains("LTriBytePredicate: boolean test(byte a1,byte a2,byte a3)"));
    
    }


    @Test
    public void isThrowing() {

        Assert.assertFalse(sut.isThrowing());
    }

}
