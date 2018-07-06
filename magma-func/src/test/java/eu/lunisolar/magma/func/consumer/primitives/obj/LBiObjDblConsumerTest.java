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
public class LBiObjDblConsumerTest<T1,T2> {
    private static final String ORIGINAL_MESSAGE = "Original message";
    private static final String EXCEPTION_WAS_WRAPPED = "Exception was wrapped.";
    private static final String NO_EXCEPTION_WERE_THROWN = "No exception were thrown.";



    private LBiObjDblConsumer<Integer,Integer> sut = new LBiObjDblConsumer<Integer,Integer>(){
        public  void doAcceptX(Integer a1,Integer a2,double a3)  {
            LBiObjDblConsumer.doNothing(a1,a2,a3);
        }
    };




    private LBiObjDblConsumer<Integer,Integer> sutAlwaysThrowing = LBiObjDblConsumer.biObjDblCons((a1,a2,a3) -> {
            throw new ParseException(ORIGINAL_MESSAGE, 0);
    });

    private LBiObjDblConsumer<Integer,Integer> sutAlwaysThrowingUnchecked = LBiObjDblConsumer.biObjDblCons((a1,a2,a3) -> {
            throw new IndexOutOfBoundsException(ORIGINAL_MESSAGE);
    });



    @Test
    public void testTupleCall() throws Throwable {

        LBiObjDblTriple<Integer,Integer> domainObject = Tuple4U.biObjDblTriple(100,100,100d);

        Object result = sut.tupleAccept(domainObject);

        assertThat(result)
            .isSameAs(LTuple.Void.INSTANCE);
    }

    @Test
    public void testNestingDoAcceptUnchecked() throws Throwable {

        // then
        try {
            sutAlwaysThrowingUnchecked.nestingDoAccept(100,100,100d);
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
            sutAlwaysThrowingUnchecked.shovingDoAccept(100,100,100d);
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
            .isEqualTo("LBiObjDblConsumer: void doAccept(T1 a1,T2 a2,double a3)");
    }

    @Test
    public void testBiObjDblConsMethod() throws Throwable {
        assertThat(LBiObjDblConsumer.biObjDblCons(LBiObjDblConsumer::doNothing))
            .isInstanceOf(LBiObjDblConsumer.class);
    }






    // <editor-fold desc="compose (functional)">

    @Test
    public void testBiObjDblConsComposeDbl() throws Throwable {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LBiObjDblConsumer<Integer,Integer> sutO = (a1,a2,a3) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(90);
                assertThat(a2).isEqualTo(91);
                assertThat(a3).isEqualTo(92d);
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
        LDblUnaryOperator before3 = p2 -> {
            assertThat(p2).isEqualTo(82d);
            beforeCalls.incrementAndGet();
            return 92d;
        };

        //when
        LBiObjDblConsumer<Integer,Integer> function = sutO.biObjDblConsComposeDbl(before1,before2,before3);
        function.doAccept(80,81,82d);

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(beforeCalls.get()).isEqualTo(3);
    }


    @Test
    public void testBiObjDblConsCompose() throws Throwable {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LBiObjDblConsumer<Integer,Integer> sutO = (a1,a2,a3) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(90);
                assertThat(a2).isEqualTo(91);
                assertThat(a3).isEqualTo(92d);
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
        LToDblFunction<Integer> before3 = p2 -> {
            assertThat(p2).isEqualTo(82);
            beforeCalls.incrementAndGet();
            return 92d;
        };

        //when
        LTriConsumer<Integer,Integer,Integer> function = sutO.biObjDblConsCompose(before1,before2,before3);
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
        LBiObjDblConsumer<Integer,Integer> sutO = (a1,a2,a3) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(80);
                assertThat(a2).isEqualTo(81);
                assertThat(a3).isEqualTo(82d);
        };

        LBiObjDblConsumer<Integer,Integer> thenFunction = (a1,a2,a3) -> {
                thenFunctionCalled.set(true);
                assertThat(a1).isEqualTo(80);
                assertThat(a2).isEqualTo(81);
                assertThat(a3).isEqualTo(82d);
        };

        //when
        LBiObjDblConsumer<Integer,Integer> function = sutO.andThen(thenFunction);
        function.doAccept(80,81,82d);

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);
    }


    @Test
    public void testNesting() {
        assertThat(sut.nestingBiObjDblCons())
            .isSameAs(sut)
            .isInstanceOf(LBiObjDblConsumer.class);
    }

    @Test
    public void testShoving() {
        assertThat(sut.shovingBiObjDblCons())
            .isSameAs(sut)
            .isInstanceOf(LBiObjDblConsumer.class);
    }


    @Test(expectedExceptions = RuntimeException.class)
    public void testShove() {

        // given
        LBiObjDblConsumer<Integer,Integer> sutThrowing = LBiObjDblConsumer.biObjDblCons((a1,a2,a3) -> {
            throw new UnsupportedOperationException();
        });

        // when
        sutThrowing.shovingBiObjDblCons().doAccept(100,100,100d);
    }


    @Test
    public void testToString() throws Throwable {

        assertThat(sut.toString())
                .isInstanceOf(String.class)
                .startsWith(this.getClass().getName()+"$");

        assertThat(String.format("%s", sut))
                .isInstanceOf(String.class)
                .contains("LBiObjDblConsumer: void doAccept(T1 a1,T2 a2,double a3)");
    }


    @Test
    public void isThrowing() {
        assertThat(sut.isThrowing())
            .isFalse();
    }

    //<editor-fold desc="Variants">

    private void variantLObjDblObj1Cons(Integer a1,double a3,Integer a2) {
    }

    @Test
    public void compilerSubstituteVariantLObjDblObj1Cons() {
        LBiObjDblConsumer lambda = LBiObjDblConsumer./*<T1,T2>*/objDblObj1Cons(this::variantLObjDblObj1Cons);

        assertThat(lambda).isInstanceOf(LBiObjDblConsumer.LObjDblObj1Cons.class);
    }


    private void variantLObj1Obj0DblCons(Integer a2,Integer a1,double a3) {
    }

    @Test
    public void compilerSubstituteVariantLObj1Obj0DblCons() {
        LBiObjDblConsumer lambda = LBiObjDblConsumer./*<T1,T2>*/obj1Obj0DblCons(this::variantLObj1Obj0DblCons);

        assertThat(lambda).isInstanceOf(LBiObjDblConsumer.LObj1Obj0DblCons.class);
    }


    private void variantLObj1DblObj0Cons(Integer a2,double a3,Integer a1) {
    }

    @Test
    public void compilerSubstituteVariantLObj1DblObj0Cons() {
        LBiObjDblConsumer lambda = LBiObjDblConsumer./*<T1,T2>*/obj1DblObj0Cons(this::variantLObj1DblObj0Cons);

        assertThat(lambda).isInstanceOf(LBiObjDblConsumer.LObj1DblObj0Cons.class);
    }


    private void variantLDblObj0Obj1Cons(double a3,Integer a1,Integer a2) {
    }

    @Test
    public void compilerSubstituteVariantLDblObj0Obj1Cons() {
        LBiObjDblConsumer lambda = LBiObjDblConsumer./*<T1,T2>*/dblObj0Obj1Cons(this::variantLDblObj0Obj1Cons);

        assertThat(lambda).isInstanceOf(LBiObjDblConsumer.LDblObj0Obj1Cons.class);
    }


    private void variantLDblObjObj0Cons(double a3,Integer a2,Integer a1) {
    }

    @Test
    public void compilerSubstituteVariantLDblObjObj0Cons() {
        LBiObjDblConsumer lambda = LBiObjDblConsumer./*<T1,T2>*/dblObjObj0Cons(this::variantLDblObjObj0Cons);

        assertThat(lambda).isInstanceOf(LBiObjDblConsumer.LDblObjObj0Cons.class);
    }

    //</editor-fold>


    @Test void safeCompiles() {
        LBiObjDblConsumer r1 = LBiObjDblConsumer.safe(sut); //NOSONAR
    }

    @Test void safePropagates() {
        Object result = LBiObjDblConsumer.safe(sut);
        assertThat(result).isSameAs(sut);
    }

    @Test void safeProtectsAgainstNpe() {
        Object result = LBiObjDblConsumer.safe(null);
        assertThat(result).isSameAs(LBiObjDblConsumer.biObjDblCons(LBiObjDblConsumer.safe()));
    }

    @Test  void safeSupplierPropagates() {
        LSupplier<LBiObjDblConsumer<Integer,Integer>> supplier = ()->sut;
        Object result = LBiObjDblConsumer.safeSupplier(supplier);
        assertThat(result).isSameAs(supplier);
    }

    @Test  void safeSupplierProtectsAgainstNpe() {
        Object result = LBiObjDblConsumer.safeSupplier(null);
        assertThat(result).isSameAs(LBiObjDblConsumer.safeSupplier());
    }

    @Test  void safeSupplierCompiles() {
        LSupplier<LBiObjDblConsumer<Integer,Integer>> r1 = LBiObjDblConsumer.safeSupplier(()->sut);  //NOSONAR
    }

}
