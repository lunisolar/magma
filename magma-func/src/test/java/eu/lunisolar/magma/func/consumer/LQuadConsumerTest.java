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
public class LQuadConsumerTest<T1,T2,T3,T4> {
    private static final String ORIGINAL_MESSAGE = "Original message";
    private static final String EXCEPTION_WAS_WRAPPED = "Exception was wrapped.";
    private static final String NO_EXCEPTION_WERE_THROWN = "No exception were thrown.";



    private LQuadConsumer<Integer,Integer,Integer,Integer> sut = new LQuadConsumer<Integer,Integer,Integer,Integer>(){
        public  void acceptX(Integer a1,Integer a2,Integer a3,Integer a4)  {
            LQuadConsumer.doNothing(a1,a2,a3,a4);
        }
    };




    private LQuadConsumer<Integer,Integer,Integer,Integer> sutAlwaysThrowing = LQuadConsumer.quadCons((a1,a2,a3,a4) -> {
            throw new ParseException(ORIGINAL_MESSAGE, 0);
    });

    private LQuadConsumer<Integer,Integer,Integer,Integer> sutAlwaysThrowingUnchecked = LQuadConsumer.quadCons((a1,a2,a3,a4) -> {
            throw new IndexOutOfBoundsException(ORIGINAL_MESSAGE);
    });



    @Test
    public void testTupleCall() throws Throwable {

        LQuad<Integer,Integer,Integer,Integer> domainObject = Tuple4U.quad(100,100,100,100);

        Object result = sut.tupleAccept(domainObject);

        assertThat(result)
            .isSameAs(LTuple.Void.INSTANCE);
    }

    @Test
    public void testNestingAcceptUnchecked() throws Throwable {

        // then
        try {
            sutAlwaysThrowingUnchecked.nestingAccept(100,100,100,100);
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
            sutAlwaysThrowingUnchecked.shovingAccept(100,100,100,100);
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
            .isEqualTo("LQuadConsumer: void accept(T1 a1,T2 a2,T3 a3,T4 a4)");
    }

    @Test
    public void testQuadConsMethod() throws Throwable {
        assertThat(LQuadConsumer.quadCons(LQuadConsumer::doNothing))
            .isInstanceOf(LQuadConsumer.class);
    }






    // <editor-fold desc="compose (functional)">

    @Test
    public void testCompose() throws Throwable {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LQuadConsumer<Integer,Integer,Integer,Integer> sutO = (a1,a2,a3,a4) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(90);
                assertThat(a2).isEqualTo(91);
                assertThat(a3).isEqualTo(92);
                assertThat(a4).isEqualTo(93);
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

        //when
        LQuadConsumer<Integer,Integer,Integer,Integer> function = sutO.compose(before1,before2,before3,before4);
        function.accept(80,81,82,83);

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(beforeCalls.get()).isEqualTo(4);
    }

    // </editor-fold>

    @Test
    public void testAndThen() throws Throwable {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);

         //given (+ some assertions)
        LQuadConsumer<Integer,Integer,Integer,Integer> sutO = (a1,a2,a3,a4) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(80);
                assertThat(a2).isEqualTo(81);
                assertThat(a3).isEqualTo(82);
                assertThat(a4).isEqualTo(83);
        };

        LQuadConsumer<Integer,Integer,Integer,Integer> thenFunction = (a1,a2,a3,a4) -> {
                thenFunctionCalled.set(true);
                assertThat(a1).isEqualTo(80);
                assertThat(a2).isEqualTo(81);
                assertThat(a3).isEqualTo(82);
                assertThat(a4).isEqualTo(83);
        };

        //when
        LQuadConsumer<Integer,Integer,Integer,Integer> function = sutO.andThen(thenFunction);
        function.accept(80,81,82,83);

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);
    }



    @Test(expectedExceptions = RuntimeException.class)
    public void testShove() {

        // given
        LQuadConsumer<Integer,Integer,Integer,Integer> sutThrowing = LQuadConsumer.quadCons((a1,a2,a3,a4) -> {
            throw new UnsupportedOperationException();
        });

        // when
        sutThrowing.shovingAccept(100,100,100,100);
    }


    @Test
    public void testToString() throws Throwable {

        assertThat(sut.toString())
                .isInstanceOf(String.class)
                .startsWith(this.getClass().getName()+"$");

        assertThat(String.format("%s", sut))
                .isInstanceOf(String.class)
                .contains("LQuadConsumer: void accept(T1 a1,T2 a2,T3 a3,T4 a4)");
    }


    @Test
    public void isThrowing() {
        assertThat(sut.isThrowing())
            .isFalse();
    }

    //<editor-fold desc="Variants">

    private void variantLBiObj1Obj3Obj2Cons(Integer a1,Integer a2,Integer a4,Integer a3) {
    }

    @Test
    public void compilerSubstituteVariantLBiObj1Obj3Obj2Cons() {
        LQuadConsumer lambda = LQuadConsumer./*<T1,T2,T3,T4>*/biObj1Obj3Obj2Cons(this::variantLBiObj1Obj3Obj2Cons);

        assertThat(lambda).isInstanceOf(LQuadConsumer.LBiObj1Obj3Obj2Cons.class);
    }


    private void variantLObj0Obj2BiObj3Cons(Integer a1,Integer a3,Integer a2,Integer a4) {
    }

    @Test
    public void compilerSubstituteVariantLObj0Obj2BiObj3Cons() {
        LQuadConsumer lambda = LQuadConsumer./*<T1,T2,T3,T4>*/obj0Obj2BiObj3Cons(this::variantLObj0Obj2BiObj3Cons);

        assertThat(lambda).isInstanceOf(LQuadConsumer.LObj0Obj2BiObj3Cons.class);
    }


    private void variantLObj0Obj2Obj3Obj1Cons(Integer a1,Integer a3,Integer a4,Integer a2) {
    }

    @Test
    public void compilerSubstituteVariantLObj0Obj2Obj3Obj1Cons() {
        LQuadConsumer lambda = LQuadConsumer./*<T1,T2,T3,T4>*/obj0Obj2Obj3Obj1Cons(this::variantLObj0Obj2Obj3Obj1Cons);

        assertThat(lambda).isInstanceOf(LQuadConsumer.LObj0Obj2Obj3Obj1Cons.class);
    }


    private void variantLObj0Obj3Obj1Obj2Cons(Integer a1,Integer a4,Integer a2,Integer a3) {
    }

    @Test
    public void compilerSubstituteVariantLObj0Obj3Obj1Obj2Cons() {
        LQuadConsumer lambda = LQuadConsumer./*<T1,T2,T3,T4>*/obj0Obj3Obj1Obj2Cons(this::variantLObj0Obj3Obj1Obj2Cons);

        assertThat(lambda).isInstanceOf(LQuadConsumer.LObj0Obj3Obj1Obj2Cons.class);
    }


    private void variantLObj0BiObj2Obj1Cons(Integer a1,Integer a4,Integer a3,Integer a2) {
    }

    @Test
    public void compilerSubstituteVariantLObj0BiObj2Obj1Cons() {
        LQuadConsumer lambda = LQuadConsumer./*<T1,T2,T3,T4>*/obj0BiObj2Obj1Cons(this::variantLObj0BiObj2Obj1Cons);

        assertThat(lambda).isInstanceOf(LQuadConsumer.LObj0BiObj2Obj1Cons.class);
    }


    private void variantLObj1TriObj3Cons(Integer a2,Integer a1,Integer a3,Integer a4) {
    }

    @Test
    public void compilerSubstituteVariantLObj1TriObj3Cons() {
        LQuadConsumer lambda = LQuadConsumer./*<T1,T2,T3,T4>*/obj1TriObj3Cons(this::variantLObj1TriObj3Cons);

        assertThat(lambda).isInstanceOf(LQuadConsumer.LObj1TriObj3Cons.class);
    }


    private void variantLObj1Obj0Obj3Obj2Cons(Integer a2,Integer a1,Integer a4,Integer a3) {
    }

    @Test
    public void compilerSubstituteVariantLObj1Obj0Obj3Obj2Cons() {
        LQuadConsumer lambda = LQuadConsumer./*<T1,T2,T3,T4>*/obj1Obj0Obj3Obj2Cons(this::variantLObj1Obj0Obj3Obj2Cons);

        assertThat(lambda).isInstanceOf(LQuadConsumer.LObj1Obj0Obj3Obj2Cons.class);
    }


    private void variantLObj1Obj2BiObj3Cons(Integer a2,Integer a3,Integer a1,Integer a4) {
    }

    @Test
    public void compilerSubstituteVariantLObj1Obj2BiObj3Cons() {
        LQuadConsumer lambda = LQuadConsumer./*<T1,T2,T3,T4>*/obj1Obj2BiObj3Cons(this::variantLObj1Obj2BiObj3Cons);

        assertThat(lambda).isInstanceOf(LQuadConsumer.LObj1Obj2BiObj3Cons.class);
    }


    private void variantLObj1Obj2Obj3Obj0Cons(Integer a2,Integer a3,Integer a4,Integer a1) {
    }

    @Test
    public void compilerSubstituteVariantLObj1Obj2Obj3Obj0Cons() {
        LQuadConsumer lambda = LQuadConsumer./*<T1,T2,T3,T4>*/obj1Obj2Obj3Obj0Cons(this::variantLObj1Obj2Obj3Obj0Cons);

        assertThat(lambda).isInstanceOf(LQuadConsumer.LObj1Obj2Obj3Obj0Cons.class);
    }


    private void variantLObj1Obj3Obj0Obj2Cons(Integer a2,Integer a4,Integer a1,Integer a3) {
    }

    @Test
    public void compilerSubstituteVariantLObj1Obj3Obj0Obj2Cons() {
        LQuadConsumer lambda = LQuadConsumer./*<T1,T2,T3,T4>*/obj1Obj3Obj0Obj2Cons(this::variantLObj1Obj3Obj0Obj2Cons);

        assertThat(lambda).isInstanceOf(LQuadConsumer.LObj1Obj3Obj0Obj2Cons.class);
    }


    private void variantLObj1BiObj2Obj0Cons(Integer a2,Integer a4,Integer a3,Integer a1) {
    }

    @Test
    public void compilerSubstituteVariantLObj1BiObj2Obj0Cons() {
        LQuadConsumer lambda = LQuadConsumer./*<T1,T2,T3,T4>*/obj1BiObj2Obj0Cons(this::variantLObj1BiObj2Obj0Cons);

        assertThat(lambda).isInstanceOf(LQuadConsumer.LObj1BiObj2Obj0Cons.class);
    }


    private void variantLObj2Obj0BiObj3Cons(Integer a3,Integer a1,Integer a2,Integer a4) {
    }

    @Test
    public void compilerSubstituteVariantLObj2Obj0BiObj3Cons() {
        LQuadConsumer lambda = LQuadConsumer./*<T1,T2,T3,T4>*/obj2Obj0BiObj3Cons(this::variantLObj2Obj0BiObj3Cons);

        assertThat(lambda).isInstanceOf(LQuadConsumer.LObj2Obj0BiObj3Cons.class);
    }


    private void variantLObj2Obj0Obj3Obj1Cons(Integer a3,Integer a1,Integer a4,Integer a2) {
    }

    @Test
    public void compilerSubstituteVariantLObj2Obj0Obj3Obj1Cons() {
        LQuadConsumer lambda = LQuadConsumer./*<T1,T2,T3,T4>*/obj2Obj0Obj3Obj1Cons(this::variantLObj2Obj0Obj3Obj1Cons);

        assertThat(lambda).isInstanceOf(LQuadConsumer.LObj2Obj0Obj3Obj1Cons.class);
    }


    private void variantLBiObj1BiObj3Cons(Integer a3,Integer a2,Integer a1,Integer a4) {
    }

    @Test
    public void compilerSubstituteVariantLBiObj1BiObj3Cons() {
        LQuadConsumer lambda = LQuadConsumer./*<T1,T2,T3,T4>*/biObj1BiObj3Cons(this::variantLBiObj1BiObj3Cons);

        assertThat(lambda).isInstanceOf(LQuadConsumer.LBiObj1BiObj3Cons.class);
    }


    private void variantLBiObj1Obj3Obj0Cons(Integer a3,Integer a2,Integer a4,Integer a1) {
    }

    @Test
    public void compilerSubstituteVariantLBiObj1Obj3Obj0Cons() {
        LQuadConsumer lambda = LQuadConsumer./*<T1,T2,T3,T4>*/biObj1Obj3Obj0Cons(this::variantLBiObj1Obj3Obj0Cons);

        assertThat(lambda).isInstanceOf(LQuadConsumer.LBiObj1Obj3Obj0Cons.class);
    }


    private void variantLObj2Obj3Obj0Obj1Cons(Integer a3,Integer a4,Integer a1,Integer a2) {
    }

    @Test
    public void compilerSubstituteVariantLObj2Obj3Obj0Obj1Cons() {
        LQuadConsumer lambda = LQuadConsumer./*<T1,T2,T3,T4>*/obj2Obj3Obj0Obj1Cons(this::variantLObj2Obj3Obj0Obj1Cons);

        assertThat(lambda).isInstanceOf(LQuadConsumer.LObj2Obj3Obj0Obj1Cons.class);
    }


    private void variantLObj2Obj3Obj1Obj0Cons(Integer a3,Integer a4,Integer a2,Integer a1) {
    }

    @Test
    public void compilerSubstituteVariantLObj2Obj3Obj1Obj0Cons() {
        LQuadConsumer lambda = LQuadConsumer./*<T1,T2,T3,T4>*/obj2Obj3Obj1Obj0Cons(this::variantLObj2Obj3Obj1Obj0Cons);

        assertThat(lambda).isInstanceOf(LQuadConsumer.LObj2Obj3Obj1Obj0Cons.class);
    }


    private void variantLObj3Obj0Obj1Obj2Cons(Integer a4,Integer a1,Integer a2,Integer a3) {
    }

    @Test
    public void compilerSubstituteVariantLObj3Obj0Obj1Obj2Cons() {
        LQuadConsumer lambda = LQuadConsumer./*<T1,T2,T3,T4>*/obj3Obj0Obj1Obj2Cons(this::variantLObj3Obj0Obj1Obj2Cons);

        assertThat(lambda).isInstanceOf(LQuadConsumer.LObj3Obj0Obj1Obj2Cons.class);
    }


    private void variantLObj3BiObj2Obj1Cons(Integer a4,Integer a1,Integer a3,Integer a2) {
    }

    @Test
    public void compilerSubstituteVariantLObj3BiObj2Obj1Cons() {
        LQuadConsumer lambda = LQuadConsumer./*<T1,T2,T3,T4>*/obj3BiObj2Obj1Cons(this::variantLObj3BiObj2Obj1Cons);

        assertThat(lambda).isInstanceOf(LQuadConsumer.LObj3BiObj2Obj1Cons.class);
    }


    private void variantLBiObj1Obj0Obj2Cons(Integer a4,Integer a2,Integer a1,Integer a3) {
    }

    @Test
    public void compilerSubstituteVariantLBiObj1Obj0Obj2Cons() {
        LQuadConsumer lambda = LQuadConsumer./*<T1,T2,T3,T4>*/biObj1Obj0Obj2Cons(this::variantLBiObj1Obj0Obj2Cons);

        assertThat(lambda).isInstanceOf(LQuadConsumer.LBiObj1Obj0Obj2Cons.class);
    }


    private void variantLTriObj2Obj0Cons(Integer a4,Integer a2,Integer a3,Integer a1) {
    }

    @Test
    public void compilerSubstituteVariantLTriObj2Obj0Cons() {
        LQuadConsumer lambda = LQuadConsumer./*<T1,T2,T3,T4>*/triObj2Obj0Cons(this::variantLTriObj2Obj0Cons);

        assertThat(lambda).isInstanceOf(LQuadConsumer.LTriObj2Obj0Cons.class);
    }


    private void variantLObj3Obj2Obj0Obj1Cons(Integer a4,Integer a3,Integer a1,Integer a2) {
    }

    @Test
    public void compilerSubstituteVariantLObj3Obj2Obj0Obj1Cons() {
        LQuadConsumer lambda = LQuadConsumer./*<T1,T2,T3,T4>*/obj3Obj2Obj0Obj1Cons(this::variantLObj3Obj2Obj0Obj1Cons);

        assertThat(lambda).isInstanceOf(LQuadConsumer.LObj3Obj2Obj0Obj1Cons.class);
    }


    private void variantLObj3Obj2Obj1Obj0Cons(Integer a4,Integer a3,Integer a2,Integer a1) {
    }

    @Test
    public void compilerSubstituteVariantLObj3Obj2Obj1Obj0Cons() {
        LQuadConsumer lambda = LQuadConsumer./*<T1,T2,T3,T4>*/obj3Obj2Obj1Obj0Cons(this::variantLObj3Obj2Obj1Obj0Cons);

        assertThat(lambda).isInstanceOf(LQuadConsumer.LObj3Obj2Obj1Obj0Cons.class);
    }

    //</editor-fold>


    @Test void safeCompiles() {
        LQuadConsumer r1 = LQuadConsumer.safe(sut); //NOSONAR
    }

    @Test void safePropagates() {
        Object result = LQuadConsumer.safe(sut);
        assertThat(result).isSameAs(sut);
    }

    @Test void safeProtectsAgainstNpe() {
        Object result = LQuadConsumer.safe(null);
        assertThat(result).isSameAs(LQuadConsumer.quadCons(LQuadConsumer.safe()));
    }

    @Test  void safeSupplierPropagates() {
        LSupplier<LQuadConsumer<Integer,Integer,Integer,Integer>> supplier = ()->sut;
        Object result = LQuadConsumer.safeSupplier(supplier);
        assertThat(result).isSameAs(supplier);
    }

    @Test  void safeSupplierProtectsAgainstNpe() {
        Object result = LQuadConsumer.safeSupplier(null);
        assertThat(result).isSameAs(LQuadConsumer.safeSupplier());
    }

    @Test  void safeSupplierCompiles() {
        LSupplier<LQuadConsumer<Integer,Integer,Integer,Integer>> r1 = LQuadConsumer.safeSupplier(()->sut);  //NOSONAR
    }

}
