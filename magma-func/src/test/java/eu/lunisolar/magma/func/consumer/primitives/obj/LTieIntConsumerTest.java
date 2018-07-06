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
public class LTieIntConsumerTest<T> {
    private static final String ORIGINAL_MESSAGE = "Original message";
    private static final String EXCEPTION_WAS_WRAPPED = "Exception was wrapped.";
    private static final String NO_EXCEPTION_WERE_THROWN = "No exception were thrown.";



    private LTieIntConsumer<Integer> sut = new LTieIntConsumer<Integer>(){
        public  void doAcceptX(Integer a1,int a2,int a3)  {
            LTieIntConsumer.doNothing(a1,a2,a3);
        }
    };




    private LTieIntConsumer<Integer> sutAlwaysThrowing = LTieIntConsumer.tieIntCons((a1,a2,a3) -> {
            throw new ParseException(ORIGINAL_MESSAGE, 0);
    });

    private LTieIntConsumer<Integer> sutAlwaysThrowingUnchecked = LTieIntConsumer.tieIntCons((a1,a2,a3) -> {
            throw new IndexOutOfBoundsException(ORIGINAL_MESSAGE);
    });



    @Test
    public void testTupleCall() throws Throwable {

        LObjBiIntTriple<Integer> domainObject = Tuple4U.objBiIntTriple(100,100,100);

        Object result = sut.tupleAccept(domainObject);

        assertThat(result)
            .isSameAs(LTuple.Void.INSTANCE);
    }

    @Test
    public void testNestingDoAcceptUnchecked() throws Throwable {

        // then
        try {
            sutAlwaysThrowingUnchecked.nestingDoAccept(100,100,100);
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
            sutAlwaysThrowingUnchecked.shovingDoAccept(100,100,100);
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
            .isEqualTo("LTieIntConsumer: void doAccept(T a1,int a2,int a3)");
    }

    @Test
    public void testTieIntConsMethod() throws Throwable {
        assertThat(LTieIntConsumer.tieIntCons(LTieIntConsumer::doNothing))
            .isInstanceOf(LTieIntConsumer.class);
    }






    // <editor-fold desc="compose (functional)">

    @Test
    public void testTieIntConsComposeInt() throws Throwable {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LTieIntConsumer<Integer> sutO = (a1,a2,a3) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(90);
                assertThat(a2).isEqualTo(91);
                assertThat(a3).isEqualTo(92);
        };

        LFunction<Integer,Integer> before1 = p0 -> {
            assertThat(p0).isEqualTo(80);
            beforeCalls.incrementAndGet();
            return 90;
        };
        LIntUnaryOperator before2 = p1 -> {
            assertThat(p1).isEqualTo(81);
            beforeCalls.incrementAndGet();
            return 91;
        };
        LIntUnaryOperator before3 = p2 -> {
            assertThat(p2).isEqualTo(82);
            beforeCalls.incrementAndGet();
            return 92;
        };

        //when
        LTieIntConsumer<Integer> function = sutO.tieIntConsComposeInt(before1,before2,before3);
        function.doAccept(80,81,82);

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(beforeCalls.get()).isEqualTo(3);
    }


    @Test
    public void testTieIntConsCompose() throws Throwable {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LTieIntConsumer<Integer> sutO = (a1,a2,a3) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(90);
                assertThat(a2).isEqualTo(91);
                assertThat(a3).isEqualTo(92);
        };

        LFunction<Integer,Integer> before1 = p0 -> {
            assertThat(p0).isEqualTo(80);
            beforeCalls.incrementAndGet();
            return 90;
        };
        LToIntFunction<Integer> before2 = p1 -> {
            assertThat(p1).isEqualTo(81);
            beforeCalls.incrementAndGet();
            return 91;
        };
        LToIntFunction<Integer> before3 = p2 -> {
            assertThat(p2).isEqualTo(82);
            beforeCalls.incrementAndGet();
            return 92;
        };

        //when
        LTriConsumer<Integer,Integer,Integer> function = sutO.tieIntConsCompose(before1,before2,before3);
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
        LTieIntConsumer<Integer> sutO = (a1,a2,a3) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(80);
                assertThat(a2).isEqualTo(81);
                assertThat(a3).isEqualTo(82);
        };

        LTieIntConsumer<Integer> thenFunction = (a1,a2,a3) -> {
                thenFunctionCalled.set(true);
                assertThat(a1).isEqualTo(80);
                assertThat(a2).isEqualTo(81);
                assertThat(a3).isEqualTo(82);
        };

        //when
        LTieIntConsumer<Integer> function = sutO.andThen(thenFunction);
        function.doAccept(80,81,82);

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);
    }


    @Test
    public void testNesting() {
        assertThat(sut.nestingTieIntCons())
            .isSameAs(sut)
            .isInstanceOf(LTieIntConsumer.class);
    }

    @Test
    public void testShoving() {
        assertThat(sut.shovingTieIntCons())
            .isSameAs(sut)
            .isInstanceOf(LTieIntConsumer.class);
    }


    @Test(expectedExceptions = RuntimeException.class)
    public void testShove() {

        // given
        LTieIntConsumer<Integer> sutThrowing = LTieIntConsumer.tieIntCons((a1,a2,a3) -> {
            throw new UnsupportedOperationException();
        });

        // when
        sutThrowing.shovingTieIntCons().doAccept(100,100,100);
    }


    @Test
    public void testToString() throws Throwable {

        assertThat(sut.toString())
                .isInstanceOf(String.class)
                .startsWith(this.getClass().getName()+"$");

        assertThat(String.format("%s", sut))
                .isInstanceOf(String.class)
                .contains("LTieIntConsumer: void doAccept(T a1,int a2,int a3)");
    }


    @Test
    public void isThrowing() {
        assertThat(sut.isThrowing())
            .isFalse();
    }

    //<editor-fold desc="Variants">

    private void variantLObjInt2Int1Cons(Integer a1,int a3,int a2) {
    }

    @Test
    public void compilerSubstituteVariantLObjInt2Int1Cons() {
        LTieIntConsumer lambda = LTieIntConsumer./*<T>*/objInt2Int1Cons(this::variantLObjInt2Int1Cons);

        assertThat(lambda).isInstanceOf(LTieIntConsumer.LObjInt2Int1Cons.class);
    }


    private void variantLInt1ObjIntCons(int a2,Integer a1,int a3) {
    }

    @Test
    public void compilerSubstituteVariantLInt1ObjIntCons() {
        LTieIntConsumer lambda = LTieIntConsumer./*<T>*/int1ObjIntCons(this::variantLInt1ObjIntCons);

        assertThat(lambda).isInstanceOf(LTieIntConsumer.LInt1ObjIntCons.class);
    }


    private void variantLInt1Int2ObjCons(int a2,int a3,Integer a1) {
    }

    @Test
    public void compilerSubstituteVariantLInt1Int2ObjCons() {
        LTieIntConsumer lambda = LTieIntConsumer./*<T>*/int1Int2ObjCons(this::variantLInt1Int2ObjCons);

        assertThat(lambda).isInstanceOf(LTieIntConsumer.LInt1Int2ObjCons.class);
    }


    private void variantLInt2ObjInt1Cons(int a3,Integer a1,int a2) {
    }

    @Test
    public void compilerSubstituteVariantLInt2ObjInt1Cons() {
        LTieIntConsumer lambda = LTieIntConsumer./*<T>*/int2ObjInt1Cons(this::variantLInt2ObjInt1Cons);

        assertThat(lambda).isInstanceOf(LTieIntConsumer.LInt2ObjInt1Cons.class);
    }


    private void variantLBiIntObjCons(int a3,int a2,Integer a1) {
    }

    @Test
    public void compilerSubstituteVariantLBiIntObjCons() {
        LTieIntConsumer lambda = LTieIntConsumer./*<T>*/biIntObjCons(this::variantLBiIntObjCons);

        assertThat(lambda).isInstanceOf(LTieIntConsumer.LBiIntObjCons.class);
    }

    //</editor-fold>


    @Test void safeCompiles() {
        LTieIntConsumer r1 = LTieIntConsumer.safe(sut); //NOSONAR
    }

    @Test void safePropagates() {
        Object result = LTieIntConsumer.safe(sut);
        assertThat(result).isSameAs(sut);
    }

    @Test void safeProtectsAgainstNpe() {
        Object result = LTieIntConsumer.safe(null);
        assertThat(result).isSameAs(LTieIntConsumer.tieIntCons(LTieIntConsumer.safe()));
    }

    @Test  void safeSupplierPropagates() {
        LSupplier<LTieIntConsumer<Integer>> supplier = ()->sut;
        Object result = LTieIntConsumer.safeSupplier(supplier);
        assertThat(result).isSameAs(supplier);
    }

    @Test  void safeSupplierProtectsAgainstNpe() {
        Object result = LTieIntConsumer.safeSupplier(null);
        assertThat(result).isSameAs(LTieIntConsumer.safeSupplier());
    }

    @Test  void safeSupplierCompiles() {
        LSupplier<LTieIntConsumer<Integer>> r1 = LTieIntConsumer.safeSupplier(()->sut);  //NOSONAR
    }

}
