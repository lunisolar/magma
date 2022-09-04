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
public class LBiCharConsumerTest {
    private static final String ORIGINAL_MESSAGE = "Original message";
    private static final String EXCEPTION_WAS_WRAPPED = "Exception was wrapped.";
    private static final String NO_EXCEPTION_WERE_THROWN = "No exception were thrown.";



    private LBiCharConsumer sut = new LBiCharConsumer(){
        public  void acceptX(char a1,char a2)  {
            LBiCharConsumer.doNothing(a1,a2);
        }
    };




    private LBiCharConsumer sutAlwaysThrowing = LBiCharConsumer.biCharCons((a1,a2) -> {
            throw new ParseException(ORIGINAL_MESSAGE, 0);
    });

    private LBiCharConsumer sutAlwaysThrowingUnchecked = LBiCharConsumer.biCharCons((a1,a2) -> {
            throw new IndexOutOfBoundsException(ORIGINAL_MESSAGE);
    });



    @Test
    public void testTupleCall() throws Throwable {

        LCharPair domainObject = Tuple4U.charPair('\u0100','\u0100');

        Object result = sut.tupleAccept(domainObject);

            Assert.assertSame(result, LTuple.Void.INSTANCE);
    }

    @Test
    public void testNestingAcceptUnchecked() throws Throwable {

        // then
        try {
            sutAlwaysThrowingUnchecked.nestingAccept('\u0100','\u0100');
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
            sutAlwaysThrowingUnchecked.shovingAccept('\u0100','\u0100');
            Assert.fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            Assert.assertEquals(e.getClass(), IndexOutOfBoundsException.class);
            Assert.assertNull(e.getCause());
            Assert.assertEquals(e.getMessage(), ORIGINAL_MESSAGE);
        }
    }


    @Test
    public void testFunctionalInterfaceDescription() throws Throwable {
        Assert.assertEquals(sut.functionalInterfaceDescription(), "LBiCharConsumer: void accept(char a1,char a2)");
    }

    @Test
    public void testBiCharConsMethod() throws Throwable {
        Assert.assertTrue(LBiCharConsumer.biCharCons(LBiCharConsumer::doNothing) instanceof LBiCharConsumer);
    
    }






    // <editor-fold desc="compose (functional)">

    @Test
    public void testCompose() throws Throwable {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LBiCharConsumer sutO = (a1,a2) -> {
                mainFunctionCalled.set(true);
                Assert.assertEquals(a1, (Object) '\u0090');
                Assert.assertEquals(a2, (Object) '\u0091');
        };

        LCharUnaryOperator before1 = p0 -> {
            Assert.assertEquals(p0, (Object) '\u0080');
            beforeCalls.incrementAndGet();
            return '\u0090';
        };
        LCharUnaryOperator before2 = p1 -> {
            Assert.assertEquals(p1, (Object) '\u0081');
            beforeCalls.incrementAndGet();
            return '\u0091';
        };

        //when
        LBiCharConsumer function = sutO.compose(before1,before2);
        function.accept('\u0080','\u0081');

        //then - finals
        Assert.assertTrue(mainFunctionCalled.get());
        Assert.assertEquals(beforeCalls.get(), 2);
    }


    @Test
    public void testUnboxingCompose() throws Throwable {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LBiCharConsumer sutO = (a1,a2) -> {
                mainFunctionCalled.set(true);
                Assert.assertEquals(a1, (Object) '\u0090');
                Assert.assertEquals(a2, (Object) '\u0091');
        };

        LToCharFunction<Integer> before1 = p0 -> {
            Assert.assertEquals(p0, (Object) 80);
            beforeCalls.incrementAndGet();
            return '\u0090';
        };
        LToCharFunction<Integer> before2 = p1 -> {
            Assert.assertEquals(p1, (Object) 81);
            beforeCalls.incrementAndGet();
            return '\u0091';
        };

        //when
        LBiConsumer<Integer,Integer> function = sutO.unboxingCompose(before1,before2);
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
        LBiCharConsumer sutO = (a1,a2) -> {
                mainFunctionCalled.set(true);
                Assert.assertEquals((Object)a1, (Object) '\u0080');
                Assert.assertEquals((Object)a2, (Object) '\u0081');
        };

        LBiCharConsumer thenFunction = (a1,a2) -> {
                thenFunctionCalled.set(true);
                Assert.assertEquals((Object)a1, (Object) '\u0080');
                Assert.assertEquals((Object)a2, (Object) '\u0081');
        };

        //when
        LBiCharConsumer function = sutO.andThen(thenFunction);
        function.accept('\u0080','\u0081');

        //then - finals
        Assert.assertTrue(mainFunctionCalled.get());
        Assert.assertTrue(thenFunctionCalled.get());
    }



    @Test(expectedExceptions = RuntimeException.class)
    public void testShove() {

        // given
        LBiCharConsumer sutThrowing = LBiCharConsumer.biCharCons((a1,a2) -> {
            throw new UnsupportedOperationException();
        });

        // when
        sutThrowing.shovingAccept('\u0100','\u0100');
    }


    @Test
    public void testToString() throws Throwable {

        Assert.assertTrue(sut.toString().startsWith(this.getClass().getName()+"$"));

        Assert.assertTrue(String.format("%s", sut).contains("LBiCharConsumer: void accept(char a1,char a2)"));
    
    }


    @Test
    public void isThrowing() {

        Assert.assertFalse(sut.isThrowing());
    }

}
