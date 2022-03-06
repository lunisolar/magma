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

package eu.lunisolar.magma.func.consumer.primitives.bi;

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
public class LBiSrtConsumerTest {
    private static final String ORIGINAL_MESSAGE = "Original message";
    private static final String EXCEPTION_WAS_WRAPPED = "Exception was wrapped.";
    private static final String NO_EXCEPTION_WERE_THROWN = "No exception were thrown.";



    private LBiSrtConsumer sut = new LBiSrtConsumer(){
        public  void acceptX(short a1,short a2)  {
            LBiSrtConsumer.doNothing(a1,a2);
        }
    };




    private LBiSrtConsumer sutAlwaysThrowing = LBiSrtConsumer.biSrtCons((a1,a2) -> {
            throw new ParseException(ORIGINAL_MESSAGE, 0);
    });

    private LBiSrtConsumer sutAlwaysThrowingUnchecked = LBiSrtConsumer.biSrtCons((a1,a2) -> {
            throw new IndexOutOfBoundsException(ORIGINAL_MESSAGE);
    });



    @Test
    public void testTupleCall() throws Throwable {

        LSrtPair domainObject = Tuple4U.srtPair((short)100,(short)100);

        Object result = sut.tupleAccept(domainObject);

            Assert.assertSame(result, LTuple.Void.INSTANCE);
    }

    @Test
    public void testNestingAcceptUnchecked() throws Throwable {

        // then
        try {
            sutAlwaysThrowingUnchecked.nestingAccept((short)100,(short)100);
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
            sutAlwaysThrowingUnchecked.shovingAccept((short)100,(short)100);
            Assert.fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            Assert.assertEquals(e.getClass(), IndexOutOfBoundsException.class);
            Assert.assertNull(e.getCause());
            Assert.assertEquals(e.getMessage(), ORIGINAL_MESSAGE);
        }
    }


    @Test
    public void testFunctionalInterfaceDescription() throws Throwable {
        Assert.assertEquals(sut.functionalInterfaceDescription(), "LBiSrtConsumer: void accept(short a1,short a2)");
    }

    @Test
    public void testBiSrtConsMethod() throws Throwable {
        Assert.assertTrue(LBiSrtConsumer.biSrtCons(LBiSrtConsumer::doNothing) instanceof LBiSrtConsumer);
    
    }






    // <editor-fold desc="compose (functional)">

    @Test
    public void testCompose() throws Throwable {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LBiSrtConsumer sutO = (a1,a2) -> {
                mainFunctionCalled.set(true);
                Assert.assertEquals(a1, (Object) (short)90);
                Assert.assertEquals(a2, (Object) (short)91);
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

        //when
        LBiSrtConsumer function = sutO.compose(before1,before2);
        function.accept((short)80,(short)81);

        //then - finals
        Assert.assertTrue(mainFunctionCalled.get());
        Assert.assertEquals(beforeCalls.get(), 2);
    }


    @Test
    public void testBiSrtConsCompose() throws Throwable {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LBiSrtConsumer sutO = (a1,a2) -> {
                mainFunctionCalled.set(true);
                Assert.assertEquals(a1, (Object) (short)90);
                Assert.assertEquals(a2, (Object) (short)91);
        };

        LToSrtFunction<Integer> before1 = p0 -> {
            Assert.assertEquals(p0, (Object) 80);
            beforeCalls.incrementAndGet();
            return (short)90;
        };
        LToSrtFunction<Integer> before2 = p1 -> {
            Assert.assertEquals(p1, (Object) 81);
            beforeCalls.incrementAndGet();
            return (short)91;
        };

        //when
        LBiConsumer<Integer,Integer> function = sutO.biSrtConsCompose(before1,before2);
        function.accept(80,81);

        //then - finals
        Assert.assertTrue(mainFunctionCalled.get());
        Assert.assertEquals(beforeCalls.get(), 2);
    }

    // </editor-fold>

    @Test
    public void testAndThen() throws Throwable {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);

         //given (+ some assertions)
        LBiSrtConsumer sutO = (a1,a2) -> {
                mainFunctionCalled.set(true);
                Assert.assertEquals((Object)a1, (Object) (short)80);
                Assert.assertEquals((Object)a2, (Object) (short)81);
        };

        LBiSrtConsumer thenFunction = (a1,a2) -> {
                thenFunctionCalled.set(true);
                Assert.assertEquals((Object)a1, (Object) (short)80);
                Assert.assertEquals((Object)a2, (Object) (short)81);
        };

        //when
        LBiSrtConsumer function = sutO.andThen(thenFunction);
        function.accept((short)80,(short)81);

        //then - finals
        Assert.assertTrue(mainFunctionCalled.get());
        Assert.assertTrue(thenFunctionCalled.get());
    }



    @Test(expectedExceptions = RuntimeException.class)
    public void testShove() {

        // given
        LBiSrtConsumer sutThrowing = LBiSrtConsumer.biSrtCons((a1,a2) -> {
            throw new UnsupportedOperationException();
        });

        // when
        sutThrowing.shovingAccept((short)100,(short)100);
    }


    @Test
    public void testToString() throws Throwable {

        Assert.assertTrue(sut.toString().startsWith(this.getClass().getName()+"$"));

        Assert.assertTrue(String.format("%s", sut).contains("LBiSrtConsumer: void accept(short a1,short a2)"));
    
    }


    @Test
    public void isThrowing() {

        Assert.assertFalse(sut.isThrowing());
    }

    //<editor-fold desc="Variants">

    private void variantLSrt1Srt0Cons(short a2,short a1) {
    }

    @Test
    public void compilerSubstituteVariantLSrt1Srt0Cons() {
        LBiSrtConsumer lambda = LBiSrtConsumer./**/srt1Srt0Cons(this::variantLSrt1Srt0Cons);

        Assert.assertTrue(lambda instanceof LBiSrtConsumer.LSrt1Srt0Cons);
    }

    //</editor-fold>


}
