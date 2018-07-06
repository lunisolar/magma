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
public class LTieBoolConsumerTest<T> {
    private static final String ORIGINAL_MESSAGE = "Original message";
    private static final String EXCEPTION_WAS_WRAPPED = "Exception was wrapped.";
    private static final String NO_EXCEPTION_WERE_THROWN = "No exception were thrown.";



    private LTieBoolConsumer<Integer> sut = new LTieBoolConsumer<Integer>(){
        public  void doAcceptX(Integer a1,int a2,boolean a3)  {
            LTieBoolConsumer.doNothing(a1,a2,a3);
        }
    };




    private LTieBoolConsumer<Integer> sutAlwaysThrowing = LTieBoolConsumer.tieBoolCons((a1,a2,a3) -> {
            throw new ParseException(ORIGINAL_MESSAGE, 0);
    });

    private LTieBoolConsumer<Integer> sutAlwaysThrowingUnchecked = LTieBoolConsumer.tieBoolCons((a1,a2,a3) -> {
            throw new IndexOutOfBoundsException(ORIGINAL_MESSAGE);
    });



    @Test
    public void testTupleCall() throws Throwable {

        LObjIntBoolTriple<Integer> domainObject = Tuple4U.objIntBoolTriple(100,100,true);

        Object result = sut.tupleAccept(domainObject);

        assertThat(result)
            .isSameAs(LTuple.Void.INSTANCE);
    }

    @Test
    public void testNestingDoAcceptUnchecked() throws Throwable {

        // then
        try {
            sutAlwaysThrowingUnchecked.nestingDoAccept(100,100,true);
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
            sutAlwaysThrowingUnchecked.shovingDoAccept(100,100,true);
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
            .isEqualTo("LTieBoolConsumer: void doAccept(T a1,int a2,boolean a3)");
    }

    @Test
    public void testTieBoolConsMethod() throws Throwable {
        assertThat(LTieBoolConsumer.tieBoolCons(LTieBoolConsumer::doNothing))
            .isInstanceOf(LTieBoolConsumer.class);
    }






    // <editor-fold desc="compose (functional)">

    @Test
    public void testTieBoolConsComposeIntBool() throws Throwable {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LTieBoolConsumer<Integer> sutO = (a1,a2,a3) -> {
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
        LIntUnaryOperator before2 = p1 -> {
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
        LTieBoolConsumer<Integer> function = sutO.tieBoolConsComposeIntBool(before1,before2,before3);
        function.doAccept(80,81,true);

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(beforeCalls.get()).isEqualTo(3);
    }


    @Test
    public void testTieBoolConsCompose() throws Throwable {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LTieBoolConsumer<Integer> sutO = (a1,a2,a3) -> {
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
        LToIntFunction<Integer> before2 = p1 -> {
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
        LTriConsumer<Integer,Integer,Integer> function = sutO.tieBoolConsCompose(before1,before2,before3);
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
        LTieBoolConsumer<Integer> sutO = (a1,a2,a3) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(80);
                assertThat(a2).isEqualTo(81);
                assertThat(a3).isEqualTo(true);
        };

        LTieBoolConsumer<Integer> thenFunction = (a1,a2,a3) -> {
                thenFunctionCalled.set(true);
                assertThat(a1).isEqualTo(80);
                assertThat(a2).isEqualTo(81);
                assertThat(a3).isEqualTo(true);
        };

        //when
        LTieBoolConsumer<Integer> function = sutO.andThen(thenFunction);
        function.doAccept(80,81,true);

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);
    }


    @Test
    public void testNesting() {
        assertThat(sut.nestingTieBoolCons())
            .isSameAs(sut)
            .isInstanceOf(LTieBoolConsumer.class);
    }

    @Test
    public void testShoving() {
        assertThat(sut.shovingTieBoolCons())
            .isSameAs(sut)
            .isInstanceOf(LTieBoolConsumer.class);
    }


    @Test(expectedExceptions = RuntimeException.class)
    public void testShove() {

        // given
        LTieBoolConsumer<Integer> sutThrowing = LTieBoolConsumer.tieBoolCons((a1,a2,a3) -> {
            throw new UnsupportedOperationException();
        });

        // when
        sutThrowing.shovingTieBoolCons().doAccept(100,100,true);
    }


    @Test
    public void testToString() throws Throwable {

        assertThat(sut.toString())
                .isInstanceOf(String.class)
                .startsWith(this.getClass().getName()+"$");

        assertThat(String.format("%s", sut))
                .isInstanceOf(String.class)
                .contains("LTieBoolConsumer: void doAccept(T a1,int a2,boolean a3)");
    }


    @Test
    public void isThrowing() {
        assertThat(sut.isThrowing())
            .isFalse();
    }

    //<editor-fold desc="Variants">

    private void variantLObjBoolIntCons(Integer a1,boolean a3,int a2) {
    }

    @Test
    public void compilerSubstituteVariantLObjBoolIntCons() {
        LTieBoolConsumer lambda = LTieBoolConsumer./*<T>*/objBoolIntCons(this::variantLObjBoolIntCons);

        assertThat(lambda).isInstanceOf(LTieBoolConsumer.LObjBoolIntCons.class);
    }


    private void variantLIntObjBoolCons(int a2,Integer a1,boolean a3) {
    }

    @Test
    public void compilerSubstituteVariantLIntObjBoolCons() {
        LTieBoolConsumer lambda = LTieBoolConsumer./*<T>*/intObjBoolCons(this::variantLIntObjBoolCons);

        assertThat(lambda).isInstanceOf(LTieBoolConsumer.LIntObjBoolCons.class);
    }


    private void variantLIntBoolObjCons(int a2,boolean a3,Integer a1) {
    }

    @Test
    public void compilerSubstituteVariantLIntBoolObjCons() {
        LTieBoolConsumer lambda = LTieBoolConsumer./*<T>*/intBoolObjCons(this::variantLIntBoolObjCons);

        assertThat(lambda).isInstanceOf(LTieBoolConsumer.LIntBoolObjCons.class);
    }


    private void variantLBoolObjIntCons(boolean a3,Integer a1,int a2) {
    }

    @Test
    public void compilerSubstituteVariantLBoolObjIntCons() {
        LTieBoolConsumer lambda = LTieBoolConsumer./*<T>*/boolObjIntCons(this::variantLBoolObjIntCons);

        assertThat(lambda).isInstanceOf(LTieBoolConsumer.LBoolObjIntCons.class);
    }


    private void variantLBoolIntObjCons(boolean a3,int a2,Integer a1) {
    }

    @Test
    public void compilerSubstituteVariantLBoolIntObjCons() {
        LTieBoolConsumer lambda = LTieBoolConsumer./*<T>*/boolIntObjCons(this::variantLBoolIntObjCons);

        assertThat(lambda).isInstanceOf(LTieBoolConsumer.LBoolIntObjCons.class);
    }

    //</editor-fold>


    @Test void safeCompiles() {
        LTieBoolConsumer r1 = LTieBoolConsumer.safe(sut); //NOSONAR
    }

    @Test void safePropagates() {
        Object result = LTieBoolConsumer.safe(sut);
        assertThat(result).isSameAs(sut);
    }

    @Test void safeProtectsAgainstNpe() {
        Object result = LTieBoolConsumer.safe(null);
        assertThat(result).isSameAs(LTieBoolConsumer.tieBoolCons(LTieBoolConsumer.safe()));
    }

    @Test  void safeSupplierPropagates() {
        LSupplier<LTieBoolConsumer<Integer>> supplier = ()->sut;
        Object result = LTieBoolConsumer.safeSupplier(supplier);
        assertThat(result).isSameAs(supplier);
    }

    @Test  void safeSupplierProtectsAgainstNpe() {
        Object result = LTieBoolConsumer.safeSupplier(null);
        assertThat(result).isSameAs(LTieBoolConsumer.safeSupplier());
    }

    @Test  void safeSupplierCompiles() {
        LSupplier<LTieBoolConsumer<Integer>> r1 = LTieBoolConsumer.safeSupplier(()->sut);  //NOSONAR
    }

}
