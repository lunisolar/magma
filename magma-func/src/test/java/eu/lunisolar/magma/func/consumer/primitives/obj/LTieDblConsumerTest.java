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
public class LTieDblConsumerTest<T> {
    private static final String ORIGINAL_MESSAGE = "Original message";
    private static final String EXCEPTION_WAS_WRAPPED = "Exception was wrapped.";
    private static final String NO_EXCEPTION_WERE_THROWN = "No exception were thrown.";



    private LTieDblConsumer<Integer> sut = new LTieDblConsumer<Integer>(){
        public  void acceptX(Integer a1,int a2,double a3)  {
            LTieDblConsumer.doNothing(a1,a2,a3);
        }
    };




    private LTieDblConsumer<Integer> sutAlwaysThrowing = LTieDblConsumer.tieDblCons((a1,a2,a3) -> {
            throw new ParseException(ORIGINAL_MESSAGE, 0);
    });

    private LTieDblConsumer<Integer> sutAlwaysThrowingUnchecked = LTieDblConsumer.tieDblCons((a1,a2,a3) -> {
            throw new IndexOutOfBoundsException(ORIGINAL_MESSAGE);
    });



    @Test
    public void testTupleCall() throws Throwable {

        LObjIntDblTriple<Integer> domainObject = Tuple4U.objIntDblTriple(100,100,100d);

        Object result = sut.tupleAccept(domainObject);

        assertThat(result)
            .isSameAs(LTuple.Void.INSTANCE);
    }

    @Test
    public void testNestingAcceptUnchecked() throws Throwable {

        // then
        try {
            sutAlwaysThrowingUnchecked.nestingAccept(100,100,100d);
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
            sutAlwaysThrowingUnchecked.shovingAccept(100,100,100d);
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
            .isEqualTo("LTieDblConsumer: void accept(T a1,int a2,double a3)");
    }

    @Test
    public void testTieDblConsMethod() throws Throwable {
        assertThat(LTieDblConsumer.tieDblCons(LTieDblConsumer::doNothing))
            .isInstanceOf(LTieDblConsumer.class);
    }






    // <editor-fold desc="compose (functional)">

    @Test
    public void testCompose() throws Throwable {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LTieDblConsumer<Integer> sutO = (a1,a2,a3) -> {
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
        LIntUnaryOperator before2 = p1 -> {
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
        LTieDblConsumer<Integer> function = sutO.compose(before1,before2,before3);
        function.accept(80,81,82d);

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(beforeCalls.get()).isEqualTo(3);
    }


    @Test
    public void testTieDblConsCompose() throws Throwable {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LTieDblConsumer<Integer> sutO = (a1,a2,a3) -> {
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
        LToIntFunction<Integer> before2 = p1 -> {
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
        LTriConsumer<Integer,Integer,Integer> function = sutO.tieDblConsCompose(before1,before2,before3);
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
        LTieDblConsumer<Integer> sutO = (a1,a2,a3) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(80);
                assertThat(a2).isEqualTo(81);
                assertThat(a3).isEqualTo(82d);
        };

        LTieDblConsumer<Integer> thenFunction = (a1,a2,a3) -> {
                thenFunctionCalled.set(true);
                assertThat(a1).isEqualTo(80);
                assertThat(a2).isEqualTo(81);
                assertThat(a3).isEqualTo(82d);
        };

        //when
        LTieDblConsumer<Integer> function = sutO.andThen(thenFunction);
        function.accept(80,81,82d);

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);
    }



    @Test(expectedExceptions = RuntimeException.class)
    public void testShove() {

        // given
        LTieDblConsumer<Integer> sutThrowing = LTieDblConsumer.tieDblCons((a1,a2,a3) -> {
            throw new UnsupportedOperationException();
        });

        // when
        sutThrowing.shovingAccept(100,100,100d);
    }


    @Test
    public void testToString() throws Throwable {

        assertThat(sut.toString())
                .isInstanceOf(String.class)
                .startsWith(this.getClass().getName()+"$");

        assertThat(String.format("%s", sut))
                .isInstanceOf(String.class)
                .contains("LTieDblConsumer: void accept(T a1,int a2,double a3)");
    }


    @Test
    public void isThrowing() {
        assertThat(sut.isThrowing())
            .isFalse();
    }

    //<editor-fold desc="Variants">

    private void variantLObjDblIntCons(Integer a1,double a3,int a2) {
    }

    @Test
    public void compilerSubstituteVariantLObjDblIntCons() {
        LTieDblConsumer lambda = LTieDblConsumer./*<T>*/objDblIntCons(this::variantLObjDblIntCons);

        assertThat(lambda).isInstanceOf(LTieDblConsumer.LObjDblIntCons.class);
    }


    private void variantLIntObjDblCons(int a2,Integer a1,double a3) {
    }

    @Test
    public void compilerSubstituteVariantLIntObjDblCons() {
        LTieDblConsumer lambda = LTieDblConsumer./*<T>*/intObjDblCons(this::variantLIntObjDblCons);

        assertThat(lambda).isInstanceOf(LTieDblConsumer.LIntObjDblCons.class);
    }


    private void variantLIntDblObjCons(int a2,double a3,Integer a1) {
    }

    @Test
    public void compilerSubstituteVariantLIntDblObjCons() {
        LTieDblConsumer lambda = LTieDblConsumer./*<T>*/intDblObjCons(this::variantLIntDblObjCons);

        assertThat(lambda).isInstanceOf(LTieDblConsumer.LIntDblObjCons.class);
    }


    private void variantLDblObjIntCons(double a3,Integer a1,int a2) {
    }

    @Test
    public void compilerSubstituteVariantLDblObjIntCons() {
        LTieDblConsumer lambda = LTieDblConsumer./*<T>*/dblObjIntCons(this::variantLDblObjIntCons);

        assertThat(lambda).isInstanceOf(LTieDblConsumer.LDblObjIntCons.class);
    }


    private void variantLDblIntObjCons(double a3,int a2,Integer a1) {
    }

    @Test
    public void compilerSubstituteVariantLDblIntObjCons() {
        LTieDblConsumer lambda = LTieDblConsumer./*<T>*/dblIntObjCons(this::variantLDblIntObjCons);

        assertThat(lambda).isInstanceOf(LTieDblConsumer.LDblIntObjCons.class);
    }

    //</editor-fold>


    @Test void safeCompiles() {
        LTieDblConsumer r1 = LTieDblConsumer.safe(sut); //NOSONAR
    }

    @Test void safePropagates() {
        Object result = LTieDblConsumer.safe(sut);
        assertThat(result).isSameAs(sut);
    }

    @Test void safeProtectsAgainstNpe() {
        Object result = LTieDblConsumer.safe(null);
        assertThat(result).isSameAs(LTieDblConsumer.tieDblCons(LTieDblConsumer.safe()));
    }

    @Test  void safeSupplierPropagates() {
        LSupplier<LTieDblConsumer<Integer>> supplier = ()->sut;
        Object result = LTieDblConsumer.safeSupplier(supplier);
        assertThat(result).isSameAs(supplier);
    }

    @Test  void safeSupplierProtectsAgainstNpe() {
        Object result = LTieDblConsumer.safeSupplier(null);
        assertThat(result).isSameAs(LTieDblConsumer.safeSupplier());
    }

    @Test  void safeSupplierCompiles() {
        LSupplier<LTieDblConsumer<Integer>> r1 = LTieDblConsumer.safeSupplier(()->sut);  //NOSONAR
    }

}
