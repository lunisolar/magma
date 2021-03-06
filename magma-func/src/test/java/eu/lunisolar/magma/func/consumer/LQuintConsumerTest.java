/*
 * This file is part of "lunisolar-magma".
 *
 * (C) Copyright 2014-2019 Lunisolar (http://lunisolar.eu/).
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

package eu.lunisolar.magma.func.consumer;

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

import org.assertj.core.api.Assertions;  //NOSONAR
import org.testng.annotations.*;      //NOSONAR
import java.util.regex.Pattern;          //NOSONAR
import java.text.ParseException;         //NOSONAR
import eu.lunisolar.magma.basics.*; //NOSONAR
import eu.lunisolar.magma.basics.exceptions.*; //NOSONAR
import java.util.concurrent.atomic.AtomicInteger; //NOSONAR
import eu.lunisolar.magma.func.tuple.*; // NOSONAR
import static org.assertj.core.api.Assertions.*; //NOSONAR
import java.util.function.*; // NOSONAR

/** The test obviously concentrate on the interface methods the function it self is very simple.  */
public class LQuintConsumerTest<T1,T2,T3,T4,T5> {
    private static final String ORIGINAL_MESSAGE = "Original message";
    private static final String EXCEPTION_WAS_WRAPPED = "Exception was wrapped.";
    private static final String NO_EXCEPTION_WERE_THROWN = "No exception were thrown.";



    private LQuintConsumer<Integer,Integer,Integer,Integer,Integer> sut = new LQuintConsumer<Integer,Integer,Integer,Integer,Integer>(){
        public  void acceptX(Integer a1,Integer a2,Integer a3,Integer a4,Integer a5)  {
            LQuintConsumer.doNothing(a1,a2,a3,a4,a5);
        }
    };




    private LQuintConsumer<Integer,Integer,Integer,Integer,Integer> sutAlwaysThrowing = LQuintConsumer.quintCons((a1,a2,a3,a4,a5) -> {
            throw new ParseException(ORIGINAL_MESSAGE, 0);
    });

    private LQuintConsumer<Integer,Integer,Integer,Integer,Integer> sutAlwaysThrowingUnchecked = LQuintConsumer.quintCons((a1,a2,a3,a4,a5) -> {
            throw new IndexOutOfBoundsException(ORIGINAL_MESSAGE);
    });



    @Test
    public void testTupleCall() throws Throwable {

        LQuint<Integer,Integer,Integer,Integer,Integer> domainObject = Tuple4U.quint(100,100,100,100,100);

        Object result = sut.tupleAccept(domainObject);

        assertThat(result)
            .isSameAs(LTuple.Void.INSTANCE);
    }

    @Test
    public void testNestingAcceptUnchecked() throws Throwable {

        // then
        try {
            sutAlwaysThrowingUnchecked.nestingAccept(100,100,100,100,100);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                    .hasNoCause()
                    .hasMessage(ORIGINAL_MESSAGE);
        }
    }

    @Test
    public void testShovingAcceptUnchecked() throws Throwable {

        // then
        try {
            sutAlwaysThrowingUnchecked.shovingAccept(100,100,100,100,100);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                    .hasNoCause()
                    .hasMessage(ORIGINAL_MESSAGE);
        }
    }


    @Test
    public void testFunctionalInterfaceDescription() throws Throwable {
        assertThat(sut.functionalInterfaceDescription())
            .isEqualTo("LQuintConsumer: void accept(T1 a1,T2 a2,T3 a3,T4 a4,T5 a5)");
    }

    @Test
    public void testQuintConsMethod() throws Throwable {
        assertThat(LQuintConsumer.quintCons(LQuintConsumer::doNothing))
            .isInstanceOf(LQuintConsumer.class);
    }






    // <editor-fold desc="compose (functional)">

    @Test
    public void testCompose() throws Throwable {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LQuintConsumer<Integer,Integer,Integer,Integer,Integer> sutO = (a1,a2,a3,a4,a5) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(90);
                assertThat(a2).isEqualTo(91);
                assertThat(a3).isEqualTo(92);
                assertThat(a4).isEqualTo(93);
                assertThat(a5).isEqualTo(94);
        };

        LFunction<Integer,Integer> before1 = p0 -> {
            assertThat(p0).isEqualTo(80);
            beforeCalls.incrementAndGet();
            return 90;
        };
        LFunction<Integer,Integer> before2 = p1 -> {
            assertThat(p1).isEqualTo(81);
            beforeCalls.incrementAndGet();
            return 91;
        };
        LFunction<Integer,Integer> before3 = p2 -> {
            assertThat(p2).isEqualTo(82);
            beforeCalls.incrementAndGet();
            return 92;
        };
        LFunction<Integer,Integer> before4 = p3 -> {
            assertThat(p3).isEqualTo(83);
            beforeCalls.incrementAndGet();
            return 93;
        };
        LFunction<Integer,Integer> before5 = p4 -> {
            assertThat(p4).isEqualTo(84);
            beforeCalls.incrementAndGet();
            return 94;
        };

        //when
        LQuintConsumer<Integer,Integer,Integer,Integer,Integer> function = sutO.compose(before1,before2,before3,before4,before5);
        function.accept(80,81,82,83,84);

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(beforeCalls.get()).isEqualTo(5);
    }

    // </editor-fold>

    @Test
    public void testAndThen() throws Throwable {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);

         //given (+ some assertions)
        LQuintConsumer<Integer,Integer,Integer,Integer,Integer> sutO = (a1,a2,a3,a4,a5) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(80);
                assertThat(a2).isEqualTo(81);
                assertThat(a3).isEqualTo(82);
                assertThat(a4).isEqualTo(83);
                assertThat(a5).isEqualTo(84);
        };

        LQuintConsumer<Integer,Integer,Integer,Integer,Integer> thenFunction = (a1,a2,a3,a4,a5) -> {
                thenFunctionCalled.set(true);
                assertThat(a1).isEqualTo(80);
                assertThat(a2).isEqualTo(81);
                assertThat(a3).isEqualTo(82);
                assertThat(a4).isEqualTo(83);
                assertThat(a5).isEqualTo(84);
        };

        //when
        LQuintConsumer<Integer,Integer,Integer,Integer,Integer> function = sutO.andThen(thenFunction);
        function.accept(80,81,82,83,84);

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);
    }



    @Test(expectedExceptions = RuntimeException.class)
    public void testShove() {

        // given
        LQuintConsumer<Integer,Integer,Integer,Integer,Integer> sutThrowing = LQuintConsumer.quintCons((a1,a2,a3,a4,a5) -> {
            throw new UnsupportedOperationException();
        });

        // when
        sutThrowing.shovingAccept(100,100,100,100,100);
    }


    @Test
    public void testToString() throws Throwable {

        assertThat(sut.toString())
                .isInstanceOf(String.class)
                .startsWith(this.getClass().getName()+"$");

        assertThat(String.format("%s", sut))
                .isInstanceOf(String.class)
                .contains("LQuintConsumer: void accept(T1 a1,T2 a2,T3 a3,T4 a4,T5 a5)");
    }


    @Test
    public void isThrowing() {
        assertThat(sut.isThrowing())
            .isFalse();
    }

    @Test void safeCompiles() {
        LQuintConsumer r1 = LQuintConsumer.safe(sut); //NOSONAR
    }

    @Test void safePropagates() {
        Object result = LQuintConsumer.safe(sut);
        assertThat(result).isSameAs(sut);
    }

    @Test void safeProtectsAgainstNpe() {
        Object result = LQuintConsumer.safe(null);
        assertThat(result).isSameAs(LQuintConsumer.quintCons(LQuintConsumer.safe()));
    }

    @Test  void safeSupplierPropagates() {
        LSupplier<LQuintConsumer<Integer,Integer,Integer,Integer,Integer>> supplier = ()->sut;
        Object result = LQuintConsumer.safeSupplier(supplier);
        assertThat(result).isSameAs(supplier);
    }

    @Test  void safeSupplierProtectsAgainstNpe() {
        Object result = LQuintConsumer.safeSupplier(null);
        assertThat(result).isSameAs(LQuintConsumer.safeSupplier());
    }

    @Test  void safeSupplierCompiles() {
        LSupplier<LQuintConsumer<Integer,Integer,Integer,Integer,Integer>> r1 = LQuintConsumer.safeSupplier(()->sut);  //NOSONAR
    }

}
