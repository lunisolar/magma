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

package eu.lunisolar.magma.func.consumer.primitives.obj;

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
public class LBiObjSrtConsumerTest<T1,T2> {
    private static final String ORIGINAL_MESSAGE = "Original message";
    private static final String EXCEPTION_WAS_WRAPPED = "Exception was wrapped.";
    private static final String NO_EXCEPTION_WERE_THROWN = "No exception were thrown.";



    private LBiObjSrtConsumer<Integer,Integer> sut = new LBiObjSrtConsumer<Integer,Integer>(){
        public  void acceptX(Integer a1,Integer a2,short a3)  {
            LBiObjSrtConsumer.doNothing(a1,a2,a3);
        }
    };




    private LBiObjSrtConsumer<Integer,Integer> sutAlwaysThrowing = LBiObjSrtConsumer.biObjSrtCons((a1,a2,a3) -> {
            throw new ParseException(ORIGINAL_MESSAGE, 0);
    });

    private LBiObjSrtConsumer<Integer,Integer> sutAlwaysThrowingUnchecked = LBiObjSrtConsumer.biObjSrtCons((a1,a2,a3) -> {
            throw new IndexOutOfBoundsException(ORIGINAL_MESSAGE);
    });



    @Test
    public void testTupleCall() throws Throwable {

        LBiObjSrtTriple<Integer,Integer> domainObject = Tuple4U.biObjSrtTriple(100,100,(short)100);

        Object result = sut.tupleAccept(domainObject);

            Assert.assertSame(result, LTuple.Void.INSTANCE);
    }

    @Test
    public void testNestingAcceptUnchecked() throws Throwable {

        // then
        try {
            sutAlwaysThrowingUnchecked.nestingAccept(100,100,(short)100);
            Assert.fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            Assert.assertEquals(e.getClass(), IndexOutOfBoundsException.class);
            Assert.assertNull(e.getCause());
            Assert.assertEquals(e.getMessage(), ORIGINAL_MESSAGE);
        }
    }

    @Test
    public void testShovingAcceptUnchecked() throws Throwable {

        // then
        try {
            sutAlwaysThrowingUnchecked.shovingAccept(100,100,(short)100);
            Assert.fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            Assert.assertEquals(e.getClass(), IndexOutOfBoundsException.class);
            Assert.assertNull(e.getCause());
            Assert.assertEquals(e.getMessage(), ORIGINAL_MESSAGE);
        }
    }


    @Test
    public void testFunctionalInterfaceDescription() throws Throwable {
        Assert.assertEquals(sut.functionalInterfaceDescription(), "LBiObjSrtConsumer: void accept(T1 a1,T2 a2,short a3)");
    }

    @Test
    public void testBiObjSrtConsMethod() throws Throwable {
        Assert.assertTrue(LBiObjSrtConsumer.biObjSrtCons(LBiObjSrtConsumer::doNothing) instanceof LBiObjSrtConsumer);
    
    }






    // <editor-fold desc="compose (functional)">

    @Test
    public void testCompose() throws Throwable {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LBiObjSrtConsumer<Integer,Integer> sutO = (a1,a2,a3) -> {
                mainFunctionCalled.set(true);
                Assert.assertEquals(a1, (Object) 90);
                Assert.assertEquals(a2, (Object) 91);
                Assert.assertEquals(a3, (Object) (short)92);
        };

        LFunction<Integer,Integer> before1 = p0 -> {
            Assert.assertEquals(p0, (Object) 80);
            beforeCalls.incrementAndGet();
            return 90;
        };
        LFunction<Integer,Integer> before2 = p1 -> {
            Assert.assertEquals(p1, (Object) 81);
            beforeCalls.incrementAndGet();
            return 91;
        };
        LSrtUnaryOperator before3 = p2 -> {
            Assert.assertEquals(p2, (Object) (short)82);
            beforeCalls.incrementAndGet();
            return (short)92;
        };

        //when
        LBiObjSrtConsumer<Integer,Integer> function = sutO.compose(before1,before2,before3);
        function.accept(80,81,(short)82);

        //then - finals
        Assert.assertTrue(mainFunctionCalled.get());
        Assert.assertEquals(beforeCalls.get(), 3);
    }


    @Test
    public void testBiObjSrtConsCompose() throws Throwable {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LBiObjSrtConsumer<Integer,Integer> sutO = (a1,a2,a3) -> {
                mainFunctionCalled.set(true);
                Assert.assertEquals(a1, (Object) 90);
                Assert.assertEquals(a2, (Object) 91);
                Assert.assertEquals(a3, (Object) (short)92);
        };

        LFunction<Integer,Integer> before1 = p0 -> {
            Assert.assertEquals(p0, (Object) 80);
            beforeCalls.incrementAndGet();
            return 90;
        };
        LFunction<Integer,Integer> before2 = p1 -> {
            Assert.assertEquals(p1, (Object) 81);
            beforeCalls.incrementAndGet();
            return 91;
        };
        LToSrtFunction<Integer> before3 = p2 -> {
            Assert.assertEquals(p2, (Object) 82);
            beforeCalls.incrementAndGet();
            return (short)92;
        };

        //when
        LTriConsumer<Integer,Integer,Integer> function = sutO.biObjSrtConsCompose(before1,before2,before3);
        function.accept(80,81,82);

        //then - finals
        Assert.assertTrue(mainFunctionCalled.get());
        Assert.assertEquals(beforeCalls.get(), 3);
    }

    // </editor-fold>

    @Test
    public void testAndThen() throws Throwable {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);

         //given (+ some assertions)
        LBiObjSrtConsumer<Integer,Integer> sutO = (a1,a2,a3) -> {
                mainFunctionCalled.set(true);
                Assert.assertEquals((Object)a1, (Object) 80);
                Assert.assertEquals((Object)a2, (Object) 81);
                Assert.assertEquals((Object)a3, (Object) (short)82);
        };

        LBiObjSrtConsumer<Integer,Integer> thenFunction = (a1,a2,a3) -> {
                thenFunctionCalled.set(true);
                Assert.assertEquals((Object)a1, (Object) 80);
                Assert.assertEquals((Object)a2, (Object) 81);
                Assert.assertEquals((Object)a3, (Object) (short)82);
        };

        //when
        LBiObjSrtConsumer<Integer,Integer> function = sutO.andThen(thenFunction);
        function.accept(80,81,(short)82);

        //then - finals
        Assert.assertTrue(mainFunctionCalled.get());
        Assert.assertTrue(thenFunctionCalled.get());
    }



    @Test(expectedExceptions = RuntimeException.class)
    public void testShove() {

        // given
        LBiObjSrtConsumer<Integer,Integer> sutThrowing = LBiObjSrtConsumer.biObjSrtCons((a1,a2,a3) -> {
            throw new UnsupportedOperationException();
        });

        // when
        sutThrowing.shovingAccept(100,100,(short)100);
    }


    @Test
    public void testToString() throws Throwable {

        Assert.assertTrue(sut.toString().startsWith(this.getClass().getName()+"$"));

        Assert.assertTrue(String.format("%s", sut).contains("LBiObjSrtConsumer: void accept(T1 a1,T2 a2,short a3)"));
    
    }


    @Test
    public void isThrowing() {

        Assert.assertFalse(sut.isThrowing());
    }

    //<editor-fold desc="Variants">

    private void variantLObj0Srt2Obj1Cons(Integer a1,short a3,Integer a2) {
    }

    @Test
    public void compilerSubstituteVariantLObj0Srt2Obj1Cons() {
        LBiObjSrtConsumer lambda = LBiObjSrtConsumer./*<T1,T2>*/obj0Srt2Obj1Cons(this::variantLObj0Srt2Obj1Cons);

        Assert.assertTrue(lambda instanceof LBiObjSrtConsumer.LObj0Srt2Obj1Cons);
    }


    private void variantLObj1Obj0Srt2Cons(Integer a2,Integer a1,short a3) {
    }

    @Test
    public void compilerSubstituteVariantLObj1Obj0Srt2Cons() {
        LBiObjSrtConsumer lambda = LBiObjSrtConsumer./*<T1,T2>*/obj1Obj0Srt2Cons(this::variantLObj1Obj0Srt2Cons);

        Assert.assertTrue(lambda instanceof LBiObjSrtConsumer.LObj1Obj0Srt2Cons);
    }


    private void variantLObj1Srt2Obj0Cons(Integer a2,short a3,Integer a1) {
    }

    @Test
    public void compilerSubstituteVariantLObj1Srt2Obj0Cons() {
        LBiObjSrtConsumer lambda = LBiObjSrtConsumer./*<T1,T2>*/obj1Srt2Obj0Cons(this::variantLObj1Srt2Obj0Cons);

        Assert.assertTrue(lambda instanceof LBiObjSrtConsumer.LObj1Srt2Obj0Cons);
    }


    private void variantLSrt2Obj0Obj1Cons(short a3,Integer a1,Integer a2) {
    }

    @Test
    public void compilerSubstituteVariantLSrt2Obj0Obj1Cons() {
        LBiObjSrtConsumer lambda = LBiObjSrtConsumer./*<T1,T2>*/srt2Obj0Obj1Cons(this::variantLSrt2Obj0Obj1Cons);

        Assert.assertTrue(lambda instanceof LBiObjSrtConsumer.LSrt2Obj0Obj1Cons);
    }


    private void variantLSrt2Obj1Obj0Cons(short a3,Integer a2,Integer a1) {
    }

    @Test
    public void compilerSubstituteVariantLSrt2Obj1Obj0Cons() {
        LBiObjSrtConsumer lambda = LBiObjSrtConsumer./*<T1,T2>*/srt2Obj1Obj0Cons(this::variantLSrt2Obj1Obj0Cons);

        Assert.assertTrue(lambda instanceof LBiObjSrtConsumer.LSrt2Obj1Obj0Cons);
    }

    //</editor-fold>


}
