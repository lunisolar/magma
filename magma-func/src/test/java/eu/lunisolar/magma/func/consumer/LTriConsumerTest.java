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
public class LTriConsumerTest<T1,T2,T3> {
    private static final String ORIGINAL_MESSAGE = "Original message";
    private static final String EXCEPTION_WAS_WRAPPED = "Exception was wrapped.";
    private static final String NO_EXCEPTION_WERE_THROWN = "No exception were thrown.";



    private LTriConsumer<Integer,Integer,Integer> sut = new LTriConsumer<Integer,Integer,Integer>(){
        public  void doAcceptX(Integer a1,Integer a2,Integer a3)  {
            LTriConsumer.doNothing(a1,a2,a3);
        }
    };




    private LTriConsumer<Integer,Integer,Integer> sutAlwaysThrowing = LTriConsumer.triCons((a1,a2,a3) -> {
            throw new ParseException(ORIGINAL_MESSAGE, 0);
    });

    private LTriConsumer<Integer,Integer,Integer> sutAlwaysThrowingUnchecked = LTriConsumer.triCons((a1,a2,a3) -> {
            throw new IndexOutOfBoundsException(ORIGINAL_MESSAGE);
    });



    @Test
    public void testTupleCall() throws Throwable {

        LTriple<Integer,Integer,Integer> domainObject = Tuple4U.triple(100,100,100);

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
            .isEqualTo("LTriConsumer: void doAccept(T1 a1,T2 a2,T3 a3)");
    }

    @Test
    public void testTriConsMethod() throws Throwable {
        assertThat(LTriConsumer.triCons(LTriConsumer::doNothing))
            .isInstanceOf(LTriConsumer.class);
    }






    // <editor-fold desc="compose (functional)">

    @Test
    public void testTriConsCompose() throws Throwable {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LTriConsumer<Integer,Integer,Integer> sutO = (a1,a2,a3) -> {
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

        //when
        LTriConsumer<Integer,Integer,Integer> function = sutO.triConsCompose(before1,before2,before3);
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
        LTriConsumer<Integer,Integer,Integer> sutO = (a1,a2,a3) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(80);
                assertThat(a2).isEqualTo(81);
                assertThat(a3).isEqualTo(82);
        };

        LTriConsumer<Integer,Integer,Integer> thenFunction = (a1,a2,a3) -> {
                thenFunctionCalled.set(true);
                assertThat(a1).isEqualTo(80);
                assertThat(a2).isEqualTo(81);
                assertThat(a3).isEqualTo(82);
        };

        //when
        LTriConsumer<Integer,Integer,Integer> function = sutO.andThen(thenFunction);
        function.doAccept(80,81,82);

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);
    }


    @Test
    public void testNesting() {
        assertThat(sut.nestingTriCons())
            .isSameAs(sut)
            .isInstanceOf(LTriConsumer.class);
    }

    @Test
    public void testShoving() {
        assertThat(sut.shovingTriCons())
            .isSameAs(sut)
            .isInstanceOf(LTriConsumer.class);
    }


    @Test(expectedExceptions = RuntimeException.class)
    public void testShove() {

        // given
        LTriConsumer<Integer,Integer,Integer> sutThrowing = LTriConsumer.triCons((a1,a2,a3) -> {
            throw new UnsupportedOperationException();
        });

        // when
        sutThrowing.shovingTriCons().doAccept(100,100,100);
    }


    @Test
    public void testToString() throws Throwable {

        assertThat(sut.toString())
                .isInstanceOf(String.class)
                .startsWith(this.getClass().getName()+"$");

        assertThat(String.format("%s", sut))
                .isInstanceOf(String.class)
                .contains("LTriConsumer: void doAccept(T1 a1,T2 a2,T3 a3)");
    }


    @Test
    public void isThrowing() {
        assertThat(sut.isThrowing())
            .isFalse();
    }

    //<editor-fold desc="Variants">

    private void variantLObjObj2Obj1Cons(Integer a1,Integer a3,Integer a2) {
    }

    @Test
    public void compilerSubstituteVariantLObjObj2Obj1Cons() {
        LTriConsumer lambda = LTriConsumer./*<T1,T2,T3>*/objObj2Obj1Cons(this::variantLObjObj2Obj1Cons);

        assertThat(lambda).isInstanceOf(LTriConsumer.LObjObj2Obj1Cons.class);
    }


    private void variantLObj1BiObjCons(Integer a2,Integer a1,Integer a3) {
    }

    @Test
    public void compilerSubstituteVariantLObj1BiObjCons() {
        LTriConsumer lambda = LTriConsumer./*<T1,T2,T3>*/obj1BiObjCons(this::variantLObj1BiObjCons);

        assertThat(lambda).isInstanceOf(LTriConsumer.LObj1BiObjCons.class);
    }


    private void variantLObj1Obj2Obj0Cons(Integer a2,Integer a3,Integer a1) {
    }

    @Test
    public void compilerSubstituteVariantLObj1Obj2Obj0Cons() {
        LTriConsumer lambda = LTriConsumer./*<T1,T2,T3>*/obj1Obj2Obj0Cons(this::variantLObj1Obj2Obj0Cons);

        assertThat(lambda).isInstanceOf(LTriConsumer.LObj1Obj2Obj0Cons.class);
    }


    private void variantLObj2Obj0Obj1Cons(Integer a3,Integer a1,Integer a2) {
    }

    @Test
    public void compilerSubstituteVariantLObj2Obj0Obj1Cons() {
        LTriConsumer lambda = LTriConsumer./*<T1,T2,T3>*/obj2Obj0Obj1Cons(this::variantLObj2Obj0Obj1Cons);

        assertThat(lambda).isInstanceOf(LTriConsumer.LObj2Obj0Obj1Cons.class);
    }


    private void variantLBiObjObj0Cons(Integer a3,Integer a2,Integer a1) {
    }

    @Test
    public void compilerSubstituteVariantLBiObjObj0Cons() {
        LTriConsumer lambda = LTriConsumer./*<T1,T2,T3>*/biObjObj0Cons(this::variantLBiObjObj0Cons);

        assertThat(lambda).isInstanceOf(LTriConsumer.LBiObjObj0Cons.class);
    }

    //</editor-fold>


    @Test void safeCompiles() {
        LTriConsumer r1 = LTriConsumer.safe(sut); //NOSONAR
    }

    @Test void safePropagates() {
        Object result = LTriConsumer.safe(sut);
        assertThat(result).isSameAs(sut);
    }

    @Test void safeProtectsAgainstNpe() {
        Object result = LTriConsumer.safe(null);
        assertThat(result).isSameAs(LTriConsumer.triCons(LTriConsumer.safe()));
    }

    @Test  void safeSupplierPropagates() {
        LSupplier<LTriConsumer<Integer,Integer,Integer>> supplier = ()->sut;
        Object result = LTriConsumer.safeSupplier(supplier);
        assertThat(result).isSameAs(supplier);
    }

    @Test  void safeSupplierProtectsAgainstNpe() {
        Object result = LTriConsumer.safeSupplier(null);
        assertThat(result).isSameAs(LTriConsumer.safeSupplier());
    }

    @Test  void safeSupplierCompiles() {
        LSupplier<LTriConsumer<Integer,Integer,Integer>> r1 = LTriConsumer.safeSupplier(()->sut);  //NOSONAR
    }

}
