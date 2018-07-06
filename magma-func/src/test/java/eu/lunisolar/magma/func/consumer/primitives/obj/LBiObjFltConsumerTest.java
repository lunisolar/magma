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
public class LBiObjFltConsumerTest<T1,T2> {
    private static final String ORIGINAL_MESSAGE = "Original message";
    private static final String EXCEPTION_WAS_WRAPPED = "Exception was wrapped.";
    private static final String NO_EXCEPTION_WERE_THROWN = "No exception were thrown.";



    private LBiObjFltConsumer<Integer,Integer> sut = new LBiObjFltConsumer<Integer,Integer>(){
        public  void doAcceptX(Integer a1,Integer a2,float a3)  {
            LBiObjFltConsumer.doNothing(a1,a2,a3);
        }
    };




    private LBiObjFltConsumer<Integer,Integer> sutAlwaysThrowing = LBiObjFltConsumer.biObjFltCons((a1,a2,a3) -> {
            throw new ParseException(ORIGINAL_MESSAGE, 0);
    });

    private LBiObjFltConsumer<Integer,Integer> sutAlwaysThrowingUnchecked = LBiObjFltConsumer.biObjFltCons((a1,a2,a3) -> {
            throw new IndexOutOfBoundsException(ORIGINAL_MESSAGE);
    });



    @Test
    public void testTupleCall() throws Throwable {

        LBiObjFltTriple<Integer,Integer> domainObject = Tuple4U.biObjFltTriple(100,100,100f);

        Object result = sut.tupleAccept(domainObject);

        assertThat(result)
            .isSameAs(LTuple.Void.INSTANCE);
    }

    @Test
    public void testNestingDoAcceptUnchecked() throws Throwable {

        // then
        try {
            sutAlwaysThrowingUnchecked.nestingDoAccept(100,100,100f);
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
            sutAlwaysThrowingUnchecked.shovingDoAccept(100,100,100f);
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
            .isEqualTo("LBiObjFltConsumer: void doAccept(T1 a1,T2 a2,float a3)");
    }

    @Test
    public void testBiObjFltConsMethod() throws Throwable {
        assertThat(LBiObjFltConsumer.biObjFltCons(LBiObjFltConsumer::doNothing))
            .isInstanceOf(LBiObjFltConsumer.class);
    }






    // <editor-fold desc="compose (functional)">

    @Test
    public void testBiObjFltConsComposeFlt() throws Throwable {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LBiObjFltConsumer<Integer,Integer> sutO = (a1,a2,a3) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(90);
                assertThat(a2).isEqualTo(91);
                assertThat(a3).isEqualTo(92f);
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
        LFltUnaryOperator before3 = p2 -> {
            assertThat(p2).isEqualTo(82f);
            beforeCalls.incrementAndGet();
            return 92f;
        };

        //when
        LBiObjFltConsumer<Integer,Integer> function = sutO.biObjFltConsComposeFlt(before1,before2,before3);
        function.doAccept(80,81,82f);

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(beforeCalls.get()).isEqualTo(3);
    }


    @Test
    public void testBiObjFltConsCompose() throws Throwable {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LBiObjFltConsumer<Integer,Integer> sutO = (a1,a2,a3) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(90);
                assertThat(a2).isEqualTo(91);
                assertThat(a3).isEqualTo(92f);
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
        LToFltFunction<Integer> before3 = p2 -> {
            assertThat(p2).isEqualTo(82);
            beforeCalls.incrementAndGet();
            return 92f;
        };

        //when
        LTriConsumer<Integer,Integer,Integer> function = sutO.biObjFltConsCompose(before1,before2,before3);
        function.doAccept(80,81,82);

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(beforeCalls.get()).isEqualTo(3);
    }

    // </editor-fold>

    @Test
    public void testAndThen() throws Throwable {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);

         //given (+ some assertions)
        LBiObjFltConsumer<Integer,Integer> sutO = (a1,a2,a3) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(80);
                assertThat(a2).isEqualTo(81);
                assertThat(a3).isEqualTo(82f);
        };

        LBiObjFltConsumer<Integer,Integer> thenFunction = (a1,a2,a3) -> {
                thenFunctionCalled.set(true);
                assertThat(a1).isEqualTo(80);
                assertThat(a2).isEqualTo(81);
                assertThat(a3).isEqualTo(82f);
        };

        //when
        LBiObjFltConsumer<Integer,Integer> function = sutO.andThen(thenFunction);
        function.doAccept(80,81,82f);

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);
    }


    @Test
    public void testNesting() {
        assertThat(sut.nestingBiObjFltCons())
            .isSameAs(sut)
            .isInstanceOf(LBiObjFltConsumer.class);
    }

    @Test
    public void testShoving() {
        assertThat(sut.shovingBiObjFltCons())
            .isSameAs(sut)
            .isInstanceOf(LBiObjFltConsumer.class);
    }


    @Test(expectedExceptions = RuntimeException.class)
    public void testShove() {

        // given
        LBiObjFltConsumer<Integer,Integer> sutThrowing = LBiObjFltConsumer.biObjFltCons((a1,a2,a3) -> {
            throw new UnsupportedOperationException();
        });

        // when
        sutThrowing.shovingBiObjFltCons().doAccept(100,100,100f);
    }


    @Test
    public void testToString() throws Throwable {

        assertThat(sut.toString())
                .isInstanceOf(String.class)
                .startsWith(this.getClass().getName()+"$");

        assertThat(String.format("%s", sut))
                .isInstanceOf(String.class)
                .contains("LBiObjFltConsumer: void doAccept(T1 a1,T2 a2,float a3)");
    }


    @Test
    public void isThrowing() {
        assertThat(sut.isThrowing())
            .isFalse();
    }

    //<editor-fold desc="Variants">

    private void variantLObjFltObj1Cons(Integer a1,float a3,Integer a2) {
    }

    @Test
    public void compilerSubstituteVariantLObjFltObj1Cons() {
        LBiObjFltConsumer lambda = LBiObjFltConsumer./*<T1,T2>*/objFltObj1Cons(this::variantLObjFltObj1Cons);

        assertThat(lambda).isInstanceOf(LBiObjFltConsumer.LObjFltObj1Cons.class);
    }


    private void variantLObj1Obj0FltCons(Integer a2,Integer a1,float a3) {
    }

    @Test
    public void compilerSubstituteVariantLObj1Obj0FltCons() {
        LBiObjFltConsumer lambda = LBiObjFltConsumer./*<T1,T2>*/obj1Obj0FltCons(this::variantLObj1Obj0FltCons);

        assertThat(lambda).isInstanceOf(LBiObjFltConsumer.LObj1Obj0FltCons.class);
    }


    private void variantLObj1FltObj0Cons(Integer a2,float a3,Integer a1) {
    }

    @Test
    public void compilerSubstituteVariantLObj1FltObj0Cons() {
        LBiObjFltConsumer lambda = LBiObjFltConsumer./*<T1,T2>*/obj1FltObj0Cons(this::variantLObj1FltObj0Cons);

        assertThat(lambda).isInstanceOf(LBiObjFltConsumer.LObj1FltObj0Cons.class);
    }


    private void variantLFltObj0Obj1Cons(float a3,Integer a1,Integer a2) {
    }

    @Test
    public void compilerSubstituteVariantLFltObj0Obj1Cons() {
        LBiObjFltConsumer lambda = LBiObjFltConsumer./*<T1,T2>*/fltObj0Obj1Cons(this::variantLFltObj0Obj1Cons);

        assertThat(lambda).isInstanceOf(LBiObjFltConsumer.LFltObj0Obj1Cons.class);
    }


    private void variantLFltObjObj0Cons(float a3,Integer a2,Integer a1) {
    }

    @Test
    public void compilerSubstituteVariantLFltObjObj0Cons() {
        LBiObjFltConsumer lambda = LBiObjFltConsumer./*<T1,T2>*/fltObjObj0Cons(this::variantLFltObjObj0Cons);

        assertThat(lambda).isInstanceOf(LBiObjFltConsumer.LFltObjObj0Cons.class);
    }

    //</editor-fold>


    @Test void safeCompiles() {
        LBiObjFltConsumer r1 = LBiObjFltConsumer.safe(sut); //NOSONAR
    }

    @Test void safePropagates() {
        Object result = LBiObjFltConsumer.safe(sut);
        assertThat(result).isSameAs(sut);
    }

    @Test void safeProtectsAgainstNpe() {
        Object result = LBiObjFltConsumer.safe(null);
        assertThat(result).isSameAs(LBiObjFltConsumer.biObjFltCons(LBiObjFltConsumer.safe()));
    }

    @Test  void safeSupplierPropagates() {
        LSupplier<LBiObjFltConsumer<Integer,Integer>> supplier = ()->sut;
        Object result = LBiObjFltConsumer.safeSupplier(supplier);
        assertThat(result).isSameAs(supplier);
    }

    @Test  void safeSupplierProtectsAgainstNpe() {
        Object result = LBiObjFltConsumer.safeSupplier(null);
        assertThat(result).isSameAs(LBiObjFltConsumer.safeSupplier());
    }

    @Test  void safeSupplierCompiles() {
        LSupplier<LBiObjFltConsumer<Integer,Integer>> r1 = LBiObjFltConsumer.safeSupplier(()->sut);  //NOSONAR
    }

}
