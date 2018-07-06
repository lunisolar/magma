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
public class LBiObjSrtConsumerTest<T1,T2> {
    private static final String ORIGINAL_MESSAGE = "Original message";
    private static final String EXCEPTION_WAS_WRAPPED = "Exception was wrapped.";
    private static final String NO_EXCEPTION_WERE_THROWN = "No exception were thrown.";



    private LBiObjSrtConsumer<Integer,Integer> sut = new LBiObjSrtConsumer<Integer,Integer>(){
        public  void doAcceptX(Integer a1,Integer a2,short a3)  {
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

        assertThat(result)
            .isSameAs(LTuple.Void.INSTANCE);
    }

    @Test
    public void testNestingDoAcceptUnchecked() throws Throwable {

        // then
        try {
            sutAlwaysThrowingUnchecked.nestingDoAccept(100,100,(short)100);
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
            sutAlwaysThrowingUnchecked.shovingDoAccept(100,100,(short)100);
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
            .isEqualTo("LBiObjSrtConsumer: void doAccept(T1 a1,T2 a2,short a3)");
    }

    @Test
    public void testBiObjSrtConsMethod() throws Throwable {
        assertThat(LBiObjSrtConsumer.biObjSrtCons(LBiObjSrtConsumer::doNothing))
            .isInstanceOf(LBiObjSrtConsumer.class);
    }






    // <editor-fold desc="compose (functional)">

    @Test
    public void testBiObjSrtConsComposeSrt() throws Throwable {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LBiObjSrtConsumer<Integer,Integer> sutO = (a1,a2,a3) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(90);
                assertThat(a2).isEqualTo(91);
                assertThat(a3).isEqualTo((short)92);
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
        LSrtUnaryOperator before3 = p2 -> {
            assertThat(p2).isEqualTo((short)82);
            beforeCalls.incrementAndGet();
            return (short)92;
        };

        //when
        LBiObjSrtConsumer<Integer,Integer> function = sutO.biObjSrtConsComposeSrt(before1,before2,before3);
        function.doAccept(80,81,(short)82);

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(beforeCalls.get()).isEqualTo(3);
    }


    @Test
    public void testBiObjSrtConsCompose() throws Throwable {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LBiObjSrtConsumer<Integer,Integer> sutO = (a1,a2,a3) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(90);
                assertThat(a2).isEqualTo(91);
                assertThat(a3).isEqualTo((short)92);
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
        LToSrtFunction<Integer> before3 = p2 -> {
            assertThat(p2).isEqualTo(82);
            beforeCalls.incrementAndGet();
            return (short)92;
        };

        //when
        LTriConsumer<Integer,Integer,Integer> function = sutO.biObjSrtConsCompose(before1,before2,before3);
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
        LBiObjSrtConsumer<Integer,Integer> sutO = (a1,a2,a3) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(80);
                assertThat(a2).isEqualTo(81);
                assertThat(a3).isEqualTo((short)82);
        };

        LBiObjSrtConsumer<Integer,Integer> thenFunction = (a1,a2,a3) -> {
                thenFunctionCalled.set(true);
                assertThat(a1).isEqualTo(80);
                assertThat(a2).isEqualTo(81);
                assertThat(a3).isEqualTo((short)82);
        };

        //when
        LBiObjSrtConsumer<Integer,Integer> function = sutO.andThen(thenFunction);
        function.doAccept(80,81,(short)82);

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);
    }


    @Test
    public void testNesting() {
        assertThat(sut.nestingBiObjSrtCons())
            .isSameAs(sut)
            .isInstanceOf(LBiObjSrtConsumer.class);
    }

    @Test
    public void testShoving() {
        assertThat(sut.shovingBiObjSrtCons())
            .isSameAs(sut)
            .isInstanceOf(LBiObjSrtConsumer.class);
    }


    @Test(expectedExceptions = RuntimeException.class)
    public void testShove() {

        // given
        LBiObjSrtConsumer<Integer,Integer> sutThrowing = LBiObjSrtConsumer.biObjSrtCons((a1,a2,a3) -> {
            throw new UnsupportedOperationException();
        });

        // when
        sutThrowing.shovingBiObjSrtCons().doAccept(100,100,(short)100);
    }


    @Test
    public void testToString() throws Throwable {

        assertThat(sut.toString())
                .isInstanceOf(String.class)
                .startsWith(this.getClass().getName()+"$");

        assertThat(String.format("%s", sut))
                .isInstanceOf(String.class)
                .contains("LBiObjSrtConsumer: void doAccept(T1 a1,T2 a2,short a3)");
    }


    @Test
    public void isThrowing() {
        assertThat(sut.isThrowing())
            .isFalse();
    }

    //<editor-fold desc="Variants">

    private void variantLObjSrtObj1Cons(Integer a1,short a3,Integer a2) {
    }

    @Test
    public void compilerSubstituteVariantLObjSrtObj1Cons() {
        LBiObjSrtConsumer lambda = LBiObjSrtConsumer./*<T1,T2>*/objSrtObj1Cons(this::variantLObjSrtObj1Cons);

        assertThat(lambda).isInstanceOf(LBiObjSrtConsumer.LObjSrtObj1Cons.class);
    }


    private void variantLObj1Obj0SrtCons(Integer a2,Integer a1,short a3) {
    }

    @Test
    public void compilerSubstituteVariantLObj1Obj0SrtCons() {
        LBiObjSrtConsumer lambda = LBiObjSrtConsumer./*<T1,T2>*/obj1Obj0SrtCons(this::variantLObj1Obj0SrtCons);

        assertThat(lambda).isInstanceOf(LBiObjSrtConsumer.LObj1Obj0SrtCons.class);
    }


    private void variantLObj1SrtObj0Cons(Integer a2,short a3,Integer a1) {
    }

    @Test
    public void compilerSubstituteVariantLObj1SrtObj0Cons() {
        LBiObjSrtConsumer lambda = LBiObjSrtConsumer./*<T1,T2>*/obj1SrtObj0Cons(this::variantLObj1SrtObj0Cons);

        assertThat(lambda).isInstanceOf(LBiObjSrtConsumer.LObj1SrtObj0Cons.class);
    }


    private void variantLSrtObj0Obj1Cons(short a3,Integer a1,Integer a2) {
    }

    @Test
    public void compilerSubstituteVariantLSrtObj0Obj1Cons() {
        LBiObjSrtConsumer lambda = LBiObjSrtConsumer./*<T1,T2>*/srtObj0Obj1Cons(this::variantLSrtObj0Obj1Cons);

        assertThat(lambda).isInstanceOf(LBiObjSrtConsumer.LSrtObj0Obj1Cons.class);
    }


    private void variantLSrtObjObj0Cons(short a3,Integer a2,Integer a1) {
    }

    @Test
    public void compilerSubstituteVariantLSrtObjObj0Cons() {
        LBiObjSrtConsumer lambda = LBiObjSrtConsumer./*<T1,T2>*/srtObjObj0Cons(this::variantLSrtObjObj0Cons);

        assertThat(lambda).isInstanceOf(LBiObjSrtConsumer.LSrtObjObj0Cons.class);
    }

    //</editor-fold>


    @Test void safeCompiles() {
        LBiObjSrtConsumer r1 = LBiObjSrtConsumer.safe(sut); //NOSONAR
    }

    @Test void safePropagates() {
        Object result = LBiObjSrtConsumer.safe(sut);
        assertThat(result).isSameAs(sut);
    }

    @Test void safeProtectsAgainstNpe() {
        Object result = LBiObjSrtConsumer.safe(null);
        assertThat(result).isSameAs(LBiObjSrtConsumer.biObjSrtCons(LBiObjSrtConsumer.safe()));
    }

    @Test  void safeSupplierPropagates() {
        LSupplier<LBiObjSrtConsumer<Integer,Integer>> supplier = ()->sut;
        Object result = LBiObjSrtConsumer.safeSupplier(supplier);
        assertThat(result).isSameAs(supplier);
    }

    @Test  void safeSupplierProtectsAgainstNpe() {
        Object result = LBiObjSrtConsumer.safeSupplier(null);
        assertThat(result).isSameAs(LBiObjSrtConsumer.safeSupplier());
    }

    @Test  void safeSupplierCompiles() {
        LSupplier<LBiObjSrtConsumer<Integer,Integer>> r1 = LBiObjSrtConsumer.safeSupplier(()->sut);  //NOSONAR
    }

}
