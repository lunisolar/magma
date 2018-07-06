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
public class LBiObjByteConsumerTest<T1,T2> {
    private static final String ORIGINAL_MESSAGE = "Original message";
    private static final String EXCEPTION_WAS_WRAPPED = "Exception was wrapped.";
    private static final String NO_EXCEPTION_WERE_THROWN = "No exception were thrown.";



    private LBiObjByteConsumer<Integer,Integer> sut = new LBiObjByteConsumer<Integer,Integer>(){
        public  void doAcceptX(Integer a1,Integer a2,byte a3)  {
            LBiObjByteConsumer.doNothing(a1,a2,a3);
        }
    };




    private LBiObjByteConsumer<Integer,Integer> sutAlwaysThrowing = LBiObjByteConsumer.biObjByteCons((a1,a2,a3) -> {
            throw new ParseException(ORIGINAL_MESSAGE, 0);
    });

    private LBiObjByteConsumer<Integer,Integer> sutAlwaysThrowingUnchecked = LBiObjByteConsumer.biObjByteCons((a1,a2,a3) -> {
            throw new IndexOutOfBoundsException(ORIGINAL_MESSAGE);
    });



    @Test
    public void testTupleCall() throws Throwable {

        LBiObjByteTriple<Integer,Integer> domainObject = Tuple4U.biObjByteTriple(100,100,(byte)100);

        Object result = sut.tupleAccept(domainObject);

        assertThat(result)
            .isSameAs(LTuple.Void.INSTANCE);
    }

    @Test
    public void testNestingDoAcceptUnchecked() throws Throwable {

        // then
        try {
            sutAlwaysThrowingUnchecked.nestingDoAccept(100,100,(byte)100);
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
            sutAlwaysThrowingUnchecked.shovingDoAccept(100,100,(byte)100);
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
            .isEqualTo("LBiObjByteConsumer: void doAccept(T1 a1,T2 a2,byte a3)");
    }

    @Test
    public void testBiObjByteConsMethod() throws Throwable {
        assertThat(LBiObjByteConsumer.biObjByteCons(LBiObjByteConsumer::doNothing))
            .isInstanceOf(LBiObjByteConsumer.class);
    }






    // <editor-fold desc="compose (functional)">

    @Test
    public void testBiObjByteConsComposeByte() throws Throwable {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LBiObjByteConsumer<Integer,Integer> sutO = (a1,a2,a3) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(90);
                assertThat(a2).isEqualTo(91);
                assertThat(a3).isEqualTo((byte)92);
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
        LByteUnaryOperator before3 = p2 -> {
            assertThat(p2).isEqualTo((byte)82);
            beforeCalls.incrementAndGet();
            return (byte)92;
        };

        //when
        LBiObjByteConsumer<Integer,Integer> function = sutO.biObjByteConsComposeByte(before1,before2,before3);
        function.doAccept(80,81,(byte)82);

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(beforeCalls.get()).isEqualTo(3);
    }


    @Test
    public void testBiObjByteConsCompose() throws Throwable {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LBiObjByteConsumer<Integer,Integer> sutO = (a1,a2,a3) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(90);
                assertThat(a2).isEqualTo(91);
                assertThat(a3).isEqualTo((byte)92);
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
        LToByteFunction<Integer> before3 = p2 -> {
            assertThat(p2).isEqualTo(82);
            beforeCalls.incrementAndGet();
            return (byte)92;
        };

        //when
        LTriConsumer<Integer,Integer,Integer> function = sutO.biObjByteConsCompose(before1,before2,before3);
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
        LBiObjByteConsumer<Integer,Integer> sutO = (a1,a2,a3) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(80);
                assertThat(a2).isEqualTo(81);
                assertThat(a3).isEqualTo((byte)82);
        };

        LBiObjByteConsumer<Integer,Integer> thenFunction = (a1,a2,a3) -> {
                thenFunctionCalled.set(true);
                assertThat(a1).isEqualTo(80);
                assertThat(a2).isEqualTo(81);
                assertThat(a3).isEqualTo((byte)82);
        };

        //when
        LBiObjByteConsumer<Integer,Integer> function = sutO.andThen(thenFunction);
        function.doAccept(80,81,(byte)82);

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);
    }


    @Test
    public void testNesting() {
        assertThat(sut.nestingBiObjByteCons())
            .isSameAs(sut)
            .isInstanceOf(LBiObjByteConsumer.class);
    }

    @Test
    public void testShoving() {
        assertThat(sut.shovingBiObjByteCons())
            .isSameAs(sut)
            .isInstanceOf(LBiObjByteConsumer.class);
    }


    @Test(expectedExceptions = RuntimeException.class)
    public void testShove() {

        // given
        LBiObjByteConsumer<Integer,Integer> sutThrowing = LBiObjByteConsumer.biObjByteCons((a1,a2,a3) -> {
            throw new UnsupportedOperationException();
        });

        // when
        sutThrowing.shovingBiObjByteCons().doAccept(100,100,(byte)100);
    }


    @Test
    public void testToString() throws Throwable {

        assertThat(sut.toString())
                .isInstanceOf(String.class)
                .startsWith(this.getClass().getName()+"$");

        assertThat(String.format("%s", sut))
                .isInstanceOf(String.class)
                .contains("LBiObjByteConsumer: void doAccept(T1 a1,T2 a2,byte a3)");
    }


    @Test
    public void isThrowing() {
        assertThat(sut.isThrowing())
            .isFalse();
    }

    //<editor-fold desc="Variants">

    private void variantLObjByteObj1Cons(Integer a1,byte a3,Integer a2) {
    }

    @Test
    public void compilerSubstituteVariantLObjByteObj1Cons() {
        LBiObjByteConsumer lambda = LBiObjByteConsumer./*<T1,T2>*/objByteObj1Cons(this::variantLObjByteObj1Cons);

        assertThat(lambda).isInstanceOf(LBiObjByteConsumer.LObjByteObj1Cons.class);
    }


    private void variantLObj1Obj0ByteCons(Integer a2,Integer a1,byte a3) {
    }

    @Test
    public void compilerSubstituteVariantLObj1Obj0ByteCons() {
        LBiObjByteConsumer lambda = LBiObjByteConsumer./*<T1,T2>*/obj1Obj0ByteCons(this::variantLObj1Obj0ByteCons);

        assertThat(lambda).isInstanceOf(LBiObjByteConsumer.LObj1Obj0ByteCons.class);
    }


    private void variantLObj1ByteObj0Cons(Integer a2,byte a3,Integer a1) {
    }

    @Test
    public void compilerSubstituteVariantLObj1ByteObj0Cons() {
        LBiObjByteConsumer lambda = LBiObjByteConsumer./*<T1,T2>*/obj1ByteObj0Cons(this::variantLObj1ByteObj0Cons);

        assertThat(lambda).isInstanceOf(LBiObjByteConsumer.LObj1ByteObj0Cons.class);
    }


    private void variantLByteObj0Obj1Cons(byte a3,Integer a1,Integer a2) {
    }

    @Test
    public void compilerSubstituteVariantLByteObj0Obj1Cons() {
        LBiObjByteConsumer lambda = LBiObjByteConsumer./*<T1,T2>*/byteObj0Obj1Cons(this::variantLByteObj0Obj1Cons);

        assertThat(lambda).isInstanceOf(LBiObjByteConsumer.LByteObj0Obj1Cons.class);
    }


    private void variantLByteObjObj0Cons(byte a3,Integer a2,Integer a1) {
    }

    @Test
    public void compilerSubstituteVariantLByteObjObj0Cons() {
        LBiObjByteConsumer lambda = LBiObjByteConsumer./*<T1,T2>*/byteObjObj0Cons(this::variantLByteObjObj0Cons);

        assertThat(lambda).isInstanceOf(LBiObjByteConsumer.LByteObjObj0Cons.class);
    }

    //</editor-fold>


    @Test void safeCompiles() {
        LBiObjByteConsumer r1 = LBiObjByteConsumer.safe(sut); //NOSONAR
    }

    @Test void safePropagates() {
        Object result = LBiObjByteConsumer.safe(sut);
        assertThat(result).isSameAs(sut);
    }

    @Test void safeProtectsAgainstNpe() {
        Object result = LBiObjByteConsumer.safe(null);
        assertThat(result).isSameAs(LBiObjByteConsumer.biObjByteCons(LBiObjByteConsumer.safe()));
    }

    @Test  void safeSupplierPropagates() {
        LSupplier<LBiObjByteConsumer<Integer,Integer>> supplier = ()->sut;
        Object result = LBiObjByteConsumer.safeSupplier(supplier);
        assertThat(result).isSameAs(supplier);
    }

    @Test  void safeSupplierProtectsAgainstNpe() {
        Object result = LBiObjByteConsumer.safeSupplier(null);
        assertThat(result).isSameAs(LBiObjByteConsumer.safeSupplier());
    }

    @Test  void safeSupplierCompiles() {
        LSupplier<LBiObjByteConsumer<Integer,Integer>> r1 = LBiObjByteConsumer.safeSupplier(()->sut);  //NOSONAR
    }

}
