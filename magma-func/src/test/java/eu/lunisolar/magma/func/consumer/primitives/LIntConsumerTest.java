/*
 * This file is part of "lunisolar-magma".
 *
 * (C) Copyright 2014-2016 Lunisolar (http://lunisolar.eu/).
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

package eu.lunisolar.magma.func.consumer.primitives;

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
public class LIntConsumerTest {
    private static final String ORIGINAL_MESSAGE = "Original message";
    private static final String EXCEPTION_WAS_WRAPPED = "Exception was wrapped.";
    private static final String NO_EXCEPTION_WERE_THROWN = "No exception were thrown.";



    private LIntConsumer sut = new LIntConsumer(){
        public  void doAcceptX(int a)  {
            LIntConsumer.doNothing(a);
        }
    };



    private IntConsumer jre = a -> LIntConsumer.doNothing(a);


    private LIntConsumer sutAlwaysThrowing = LIntConsumer.intCons(a -> {
            throw new ParseException(ORIGINAL_MESSAGE, 0);
    });

    private LIntConsumer sutAlwaysThrowingUnchecked = LIntConsumer.intCons(a -> {
            throw new IndexOutOfBoundsException(ORIGINAL_MESSAGE);
    });



    @Test
    public void testTupleCall() throws Throwable {

        LIntSingle domainObject = Tuple4U.intSingle(100);

        Object result = sut.tupleAccept(domainObject);

        assertThat(result)
            .isSameAs(LTuple.Void.INSTANCE);
    }

    @Test
    public void testNestingDoAcceptUnchecked() throws Throwable {

        // then
        try {
            sutAlwaysThrowingUnchecked.nestingDoAccept(100);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                    .hasNoCause()
                    .hasMessage(ORIGINAL_MESSAGE);
        }
    }

    @Test
    public void testShovingDoAcceptUnchecked() throws Throwable {

        // then
        try {
            sutAlwaysThrowingUnchecked.shovingDoAccept(100);
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
            .isEqualTo("LIntConsumer: void doAccept(int a)");
    }

    @Test
    public void testIntConsMethod() throws Throwable {
        assertThat(LIntConsumer.intCons(LIntConsumer::doNothing))
            .isInstanceOf(LIntConsumer.class);
    }


    @Test
    public void testWrapStdMethod() throws Throwable {
        assertThat(LIntConsumer.wrap(jre))
            .isInstanceOf(LIntConsumer.class);
    }





    // <editor-fold desc="compose (functional)">

    @Test
    public void testIntConsComposeInt() throws Throwable {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LIntConsumer sutO = a -> {
                mainFunctionCalled.set(true);
                assertThat(a).isEqualTo(90);
        };

        LIntUnaryOperator before = p0 -> {
            assertThat(p0).isEqualTo(80);
            beforeCalls.incrementAndGet();
            return 90;
        };

        //when
        LIntConsumer function = sutO.intConsComposeInt(before);
        function.doAccept(80);

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(beforeCalls.get()).isEqualTo(1);
    }


    @Test
    public void testIntConsCompose() throws Throwable {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LIntConsumer sutO = a -> {
                mainFunctionCalled.set(true);
                assertThat(a).isEqualTo(90);
        };

        LToIntFunction<Integer> before = p0 -> {
            assertThat(p0).isEqualTo(80);
            beforeCalls.incrementAndGet();
            return 90;
        };

        //when
        LConsumer<Integer> function = sutO.intConsCompose(before);
        function.doAccept(80);

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(beforeCalls.get()).isEqualTo(1);
    }

    // </editor-fold>

    @Test
    public void testAndThen() throws Throwable {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);

         //given (+ some assertions)
        LIntConsumer sutO = a -> {
                mainFunctionCalled.set(true);
                assertThat(a).isEqualTo(80);
        };

        LIntConsumer thenFunction = a -> {
                thenFunctionCalled.set(true);
                assertThat(a).isEqualTo(80);
        };

        //when
        LIntConsumer function = sutO.andThen(thenFunction);
        function.doAccept(80);

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);
    }


    @Test
    public void testNesting() {
        assertThat(sut.nestingIntCons())
            .isSameAs(sut)
            .isInstanceOf(LIntConsumer.class);
    }

    @Test
    public void testShoving() {
        assertThat(sut.shovingIntCons())
            .isSameAs(sut)
            .isInstanceOf(LIntConsumer.class);
    }


    @Test(expectedExceptions = RuntimeException.class)
    public void testShove() {

        // given
        LIntConsumer sutThrowing = LIntConsumer.intCons(a -> {
            throw new UnsupportedOperationException();
        });

        // when
        sutThrowing.shovingIntCons().doAccept(100);
    }


    @Test
    public void testToString() throws Throwable {

        assertThat(sut.toString())
                .isInstanceOf(String.class)
                .startsWith(this.getClass().getName()+"$");

        assertThat(String.format("%s", sut))
                .isInstanceOf(String.class)
                .contains("LIntConsumer: void doAccept(int a)");
    }


    @Test
    public void isThrowing() {
        assertThat(sut.isThrowing())
            .isFalse();
    }

    @Test void safeCompiles() {
        LIntConsumer r1 = LIntConsumer.safe(sut); //NOSONAR
        IntConsumer r3 = LIntConsumer.safe(sut); //NOSONAR
    }

    @Test void safePropagates() {
        Object result = LIntConsumer.safe(sut);
        assertThat(result).isSameAs(sut);
    }

    @Test void safeProtectsAgainstNpe() {
        Object result = LIntConsumer.safe(null);
        assertThat(result).isSameAs(LIntConsumer.intCons(LIntConsumer.safe()));
    }

    @Test  void safeSupplierPropagates() {
        LSupplier<LIntConsumer> supplier = ()->sut;
        Object result = LIntConsumer.safeSupplier(supplier);
        assertThat(result).isSameAs(supplier);
    }

    @Test  void safeSupplierProtectsAgainstNpe() {
        Object result = LIntConsumer.safeSupplier(null);
        assertThat(result).isSameAs(LIntConsumer.safeSupplier());
    }

    @Test  void safeSupplierCompiles() {
        LSupplier<LIntConsumer> r1 = LIntConsumer.safeSupplier(()->sut);  //NOSONAR
        Supplier<LIntConsumer> r2 = LIntConsumer.safeSupplier(()->sut); //NOSONAR
    }

}
