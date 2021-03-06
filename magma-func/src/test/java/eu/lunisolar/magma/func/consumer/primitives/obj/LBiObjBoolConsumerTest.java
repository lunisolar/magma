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
public class LBiObjBoolConsumerTest<T1,T2> {
    private static final String ORIGINAL_MESSAGE = "Original message";
    private static final String EXCEPTION_WAS_WRAPPED = "Exception was wrapped.";
    private static final String NO_EXCEPTION_WERE_THROWN = "No exception were thrown.";



    private LBiObjBoolConsumer<Integer,Integer> sut = new LBiObjBoolConsumer<Integer,Integer>(){
        public  void acceptX(Integer a1,Integer a2,boolean a3)  {
            LBiObjBoolConsumer.doNothing(a1,a2,a3);
        }
    };




    private LBiObjBoolConsumer<Integer,Integer> sutAlwaysThrowing = LBiObjBoolConsumer.biObjBoolCons((a1,a2,a3) -> {
            throw new ParseException(ORIGINAL_MESSAGE, 0);
    });

    private LBiObjBoolConsumer<Integer,Integer> sutAlwaysThrowingUnchecked = LBiObjBoolConsumer.biObjBoolCons((a1,a2,a3) -> {
            throw new IndexOutOfBoundsException(ORIGINAL_MESSAGE);
    });



    @Test
    public void testTupleCall() throws Throwable {

        LBiObjBoolTriple<Integer,Integer> domainObject = Tuple4U.biObjBoolTriple(100,100,true);

        Object result = sut.tupleAccept(domainObject);

        assertThat(result)
            .isSameAs(LTuple.Void.INSTANCE);
    }

    @Test
    public void testNestingAcceptUnchecked() throws Throwable {

        // then
        try {
            sutAlwaysThrowingUnchecked.nestingAccept(100,100,true);
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
            sutAlwaysThrowingUnchecked.shovingAccept(100,100,true);
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
            .isEqualTo("LBiObjBoolConsumer: void accept(T1 a1,T2 a2,boolean a3)");
    }

    @Test
    public void testBiObjBoolConsMethod() throws Throwable {
        assertThat(LBiObjBoolConsumer.biObjBoolCons(LBiObjBoolConsumer::doNothing))
            .isInstanceOf(LBiObjBoolConsumer.class);
    }






    // <editor-fold desc="compose (functional)">

    @Test
    public void testCompose() throws Throwable {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LBiObjBoolConsumer<Integer,Integer> sutO = (a1,a2,a3) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(90);
                assertThat(a2).isEqualTo(91);
                assertThat(a3).isEqualTo(true);
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
        LLogicalOperator before3 = p2 -> {
            assertThat(p2).isEqualTo(true);
            beforeCalls.incrementAndGet();
            return true;
        };

        //when
        LBiObjBoolConsumer<Integer,Integer> function = sutO.compose(before1,before2,before3);
        function.accept(80,81,true);

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(beforeCalls.get()).isEqualTo(3);
    }


    @Test
    public void testBiObjBoolConsCompose() throws Throwable {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LBiObjBoolConsumer<Integer,Integer> sutO = (a1,a2,a3) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(90);
                assertThat(a2).isEqualTo(91);
                assertThat(a3).isEqualTo(true);
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
        LPredicate<Integer> before3 = p2 -> {
            assertThat(p2).isEqualTo(82);
            beforeCalls.incrementAndGet();
            return true;
        };

        //when
        LTriConsumer<Integer,Integer,Integer> function = sutO.biObjBoolConsCompose(before1,before2,before3);
        function.accept(80,81,82);

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
        LBiObjBoolConsumer<Integer,Integer> sutO = (a1,a2,a3) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(80);
                assertThat(a2).isEqualTo(81);
                assertThat(a3).isEqualTo(true);
        };

        LBiObjBoolConsumer<Integer,Integer> thenFunction = (a1,a2,a3) -> {
                thenFunctionCalled.set(true);
                assertThat(a1).isEqualTo(80);
                assertThat(a2).isEqualTo(81);
                assertThat(a3).isEqualTo(true);
        };

        //when
        LBiObjBoolConsumer<Integer,Integer> function = sutO.andThen(thenFunction);
        function.accept(80,81,true);

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);
    }



    @Test(expectedExceptions = RuntimeException.class)
    public void testShove() {

        // given
        LBiObjBoolConsumer<Integer,Integer> sutThrowing = LBiObjBoolConsumer.biObjBoolCons((a1,a2,a3) -> {
            throw new UnsupportedOperationException();
        });

        // when
        sutThrowing.shovingAccept(100,100,true);
    }


    @Test
    public void testToString() throws Throwable {

        assertThat(sut.toString())
                .isInstanceOf(String.class)
                .startsWith(this.getClass().getName()+"$");

        assertThat(String.format("%s", sut))
                .isInstanceOf(String.class)
                .contains("LBiObjBoolConsumer: void accept(T1 a1,T2 a2,boolean a3)");
    }


    @Test
    public void isThrowing() {
        assertThat(sut.isThrowing())
            .isFalse();
    }

    //<editor-fold desc="Variants">

    private void variantLObj0Bool2Obj1Cons(Integer a1,boolean a3,Integer a2) {
    }

    @Test
    public void compilerSubstituteVariantLObj0Bool2Obj1Cons() {
        LBiObjBoolConsumer lambda = LBiObjBoolConsumer./*<T1,T2>*/obj0Bool2Obj1Cons(this::variantLObj0Bool2Obj1Cons);

        assertThat(lambda).isInstanceOf(LBiObjBoolConsumer.LObj0Bool2Obj1Cons.class);
    }


    private void variantLObj1Obj0Bool2Cons(Integer a2,Integer a1,boolean a3) {
    }

    @Test
    public void compilerSubstituteVariantLObj1Obj0Bool2Cons() {
        LBiObjBoolConsumer lambda = LBiObjBoolConsumer./*<T1,T2>*/obj1Obj0Bool2Cons(this::variantLObj1Obj0Bool2Cons);

        assertThat(lambda).isInstanceOf(LBiObjBoolConsumer.LObj1Obj0Bool2Cons.class);
    }


    private void variantLObj1Bool2Obj0Cons(Integer a2,boolean a3,Integer a1) {
    }

    @Test
    public void compilerSubstituteVariantLObj1Bool2Obj0Cons() {
        LBiObjBoolConsumer lambda = LBiObjBoolConsumer./*<T1,T2>*/obj1Bool2Obj0Cons(this::variantLObj1Bool2Obj0Cons);

        assertThat(lambda).isInstanceOf(LBiObjBoolConsumer.LObj1Bool2Obj0Cons.class);
    }


    private void variantLBool2Obj0Obj1Cons(boolean a3,Integer a1,Integer a2) {
    }

    @Test
    public void compilerSubstituteVariantLBool2Obj0Obj1Cons() {
        LBiObjBoolConsumer lambda = LBiObjBoolConsumer./*<T1,T2>*/bool2Obj0Obj1Cons(this::variantLBool2Obj0Obj1Cons);

        assertThat(lambda).isInstanceOf(LBiObjBoolConsumer.LBool2Obj0Obj1Cons.class);
    }


    private void variantLBool2Obj1Obj0Cons(boolean a3,Integer a2,Integer a1) {
    }

    @Test
    public void compilerSubstituteVariantLBool2Obj1Obj0Cons() {
        LBiObjBoolConsumer lambda = LBiObjBoolConsumer./*<T1,T2>*/bool2Obj1Obj0Cons(this::variantLBool2Obj1Obj0Cons);

        assertThat(lambda).isInstanceOf(LBiObjBoolConsumer.LBool2Obj1Obj0Cons.class);
    }

    //</editor-fold>


    @Test void safeCompiles() {
        LBiObjBoolConsumer r1 = LBiObjBoolConsumer.safe(sut); //NOSONAR
    }

    @Test void safePropagates() {
        Object result = LBiObjBoolConsumer.safe(sut);
        assertThat(result).isSameAs(sut);
    }

    @Test void safeProtectsAgainstNpe() {
        Object result = LBiObjBoolConsumer.safe(null);
        assertThat(result).isSameAs(LBiObjBoolConsumer.biObjBoolCons(LBiObjBoolConsumer.safe()));
    }

    @Test  void safeSupplierPropagates() {
        LSupplier<LBiObjBoolConsumer<Integer,Integer>> supplier = ()->sut;
        Object result = LBiObjBoolConsumer.safeSupplier(supplier);
        assertThat(result).isSameAs(supplier);
    }

    @Test  void safeSupplierProtectsAgainstNpe() {
        Object result = LBiObjBoolConsumer.safeSupplier(null);
        assertThat(result).isSameAs(LBiObjBoolConsumer.safeSupplier());
    }

    @Test  void safeSupplierCompiles() {
        LSupplier<LBiObjBoolConsumer<Integer,Integer>> r1 = LBiObjBoolConsumer.safeSupplier(()->sut);  //NOSONAR
    }

}
