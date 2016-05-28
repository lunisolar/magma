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

package eu.lunisolar.magma.func.function.from;

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
import eu.lunisolar.magma.struct.tuple.*; // NOSONAR
import static org.assertj.core.api.Assertions.*; //NOSONAR
import java.util.function.*; // NOSONAR

/** The test obviously concentrate on the interface methods the function it self is very simple.  */
public class LBiObjFloatFunctionTest<T1,T2,R,X extends ParseException> {
    private static final String ORIGINAL_MESSAGE = "Original message";
    private static final String EXCEPTION_WAS_WRAPPED = "Exception was wrapped.";
    private static final String NO_EXCEPTION_WERE_THROWN = "No exception were thrown.";

    private Integer testValue = 100;



    private LBiObjFloatFunction<Integer,Integer,Integer> sut = new LBiObjFloatFunction<Integer,Integer,Integer>(){
        public @Nullable Integer doApply(Integer a1,Integer a2,float a3)  {
            return testValue;
        }
    };

    private LBiObjFloatFunctionX<Integer,Integer,Integer,X> opposite = new LBiObjFloatFunctionX<Integer,Integer,Integer,X>(){
        public @Nullable Integer doApply(Integer a1,Integer a2,float a3)  throws X {
            return testValue;
        }
    };

    private LBiObjFloatFunction<Integer,Integer,Integer> sutNull = new LBiObjFloatFunction<Integer,Integer,Integer>(){
        public @Nullable Integer doApply(Integer a1,Integer a2,float a3)  {
            return null;
        }
    };




    private LBiObjFloatFunctionX<Integer,Integer,Integer,RuntimeException> sutAlwaysThrowingUnchecked = LBiObjFloatFunction.l((a1,a2,a3) -> {
            throw new IndexOutOfBoundsException(ORIGINAL_MESSAGE);
    });


    @Test
    public void testTheResult() throws X {
        assertThat(sut.doApply(100,100,100f))
            .isEqualTo(testValue);
    }

    @Test
    public void testTupleCall() throws X {

        LBiObjFloatTriple<Integer,Integer> domainObject = Tuple4U.biObjFloatTriple(100,100,100f);

        Object result = sut.tupleApply(domainObject);

        assertThat(result)
            .isEqualTo(testValue);
    }

    @Test
    public void testNonNullDoApply() throws X {
        assertThat(sut.nonNullDoApply(100,100,100f))
            .isSameAs(testValue);
    }

    @Test
    public void testNestingDoApplyUnchecked() throws X {

        // then
        try {
            sutAlwaysThrowingUnchecked.nestingDoApply(100,100,100f);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                    .hasNoCause()
                    .hasMessage(ORIGINAL_MESSAGE);
        }
    }

    @Test
    public void testShovingDoApplyUnchecked() throws X {

        // then
        try {
            sutAlwaysThrowingUnchecked.shovingDoApply(100,100,100f);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                    .hasNoCause()
                    .hasMessage(ORIGINAL_MESSAGE);
        }
    }

    @Test(expectedExceptions=NullPointerException.class, expectedExceptionsMessageRegExp="\\QEvaluated value by nonNullDoApply() method cannot be null (LBiObjFloatFunction: R doApply(T1 a1,T2 a2,float a3)).\\E")
    public void testNonNullCapturesNull() throws X {
        sutNull.nonNullDoApply(100,100,100f);
    }


    @Test
    public void testFunctionalInterfaceDescription() throws X {
        assertThat(sut.functionalInterfaceDescription())
            .isEqualTo("LBiObjFloatFunction: R doApply(T1 a1,T2 a2,float a3)");
    }

    @Test
    public void testLMethod() throws X {
        assertThat(LBiObjFloatFunction.l((a1,a2,a3) -> testValue ))
            .isInstanceOf(LBiObjFloatFunction.class);
    }

    @Test
    public void testWrapMethod() throws X {
        assertThat(LBiObjFloatFunction.wrap(opposite))
            .isInstanceOf(LBiObjFloatFunction.class);
    }

    @Test
    public void testWrapMethodDoNotWrapsRuntimeException() throws X {
        // given
        LBiObjFloatFunctionX<Integer,Integer,Integer,X> sutThrowing = LBiObjFloatFunctionX.lX((a1,a2,a3) -> {
            throw new UnsupportedOperationException(ORIGINAL_MESSAGE);
        });

        // when
        LBiObjFloatFunction<Integer,Integer,Integer> wrapped = LBiObjFloatFunction.wrap(sutThrowing);

        // then
        try {
            wrapped.doApply(100,100,100f);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(UnsupportedOperationException.class)
                    .hasNoCause()
                    .hasMessage(ORIGINAL_MESSAGE);
        }
    }

    @Test
    public void testWrapMethodWrapsCheckedException() throws X {
        // given
        LBiObjFloatFunctionX<Integer,Integer,Integer,ParseException> sutThrowing = LBiObjFloatFunctionX.lX((a1,a2,a3) -> {
            throw new ParseException(ORIGINAL_MESSAGE, 0);
        });

        // when
        LBiObjFloatFunction<Integer,Integer,Integer> wrapped = LBiObjFloatFunction.wrap(sutThrowing);

        // then
        try {
            wrapped.doApply(100,100,100f);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(NestedException.class)
                    .hasCauseExactlyInstanceOf(ParseException.class)
                    .hasMessage(ORIGINAL_MESSAGE);
        }
    }


    @Test
    public void testHandlingDoApplyMethodWrapsTheException() throws X {

        // given
        LBiObjFloatFunction<Integer,Integer,Integer> sutThrowing = LBiObjFloatFunction.l((a1,a2,a3) -> {
            throw new UnsupportedOperationException();
        });

        // when
        LBiObjFloatFunction<Integer,Integer,Integer> wrapped = sutThrowing.handleBiObjFloatFunc(handler -> handler
            .wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED));

        // then
        try {
            wrapped.doApply(100,100,100f);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IllegalArgumentException.class)
                    .hasCauseExactlyInstanceOf(UnsupportedOperationException.class)
                    .hasMessage(EXCEPTION_WAS_WRAPPED);
        }
    }

    @Test
    public void testHandleBiObjFloatFuncMethodDoNotWrapsOtherExceptionIf() throws X {

        // given
        LBiObjFloatFunction<Integer,Integer,Integer> sutThrowing = LBiObjFloatFunction.l((a1,a2,a3) -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LBiObjFloatFunction<Integer,Integer,Integer> wrapped = sutThrowing.handleBiObjFloatFunc(handler -> handler
                .wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED)
                .throwIf(IndexOutOfBoundsException.class));

        // then
        try {
            wrapped.doApply(100,100,100f);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                    .hasNoCause();
        }
    }

@Test
    public void testHandleBiObjFloatFuncMethodDoNotWrapsOtherExceptionWhen() throws X {

        // given
        LBiObjFloatFunction<Integer,Integer,Integer> sutThrowing = LBiObjFloatFunction.l((a1,a2,a3) -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LBiObjFloatFunction<Integer,Integer,Integer> wrapped = sutThrowing.handleBiObjFloatFunc(handler -> handler
                .wrapWhen(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED)
                .throwIf(IndexOutOfBoundsException.class));

        // then
        try {
            wrapped.doApply(100,100,100f);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                    .hasNoCause();
        }
    }


    @Test
    public void testHandleBiObjFloatFuncMishandlingExceptionIsAllowed() throws X {

        // given
        LBiObjFloatFunction<Integer,Integer,Integer> sutThrowing = LBiObjFloatFunction.l((a1,a2,a3) -> {
            throw new UnsupportedOperationException(ORIGINAL_MESSAGE);
        });

        // when
        LBiObjFloatFunction<Integer,Integer,Integer> wrapped = sutThrowing.handleBiObjFloatFunc(h -> Function4U.doNothing());

        // then
        try {
            wrapped.doApply(100,100,100f);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
             .isExactlyInstanceOf(UnsupportedOperationException.class)
             .hasNoCause()
             .hasMessage(ORIGINAL_MESSAGE);
        }
    }



    // <editor-fold desc="compose (functional)">

    @Test
    public void testBiObjFloatFuncComposeFloat() throws X {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LBiObjFloatFunction<Integer,Integer,Integer> sutO = (a1,a2,a3) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(90);
                assertThat(a2).isEqualTo(91);
                assertThat(a3).isEqualTo(92f);
                return 100;
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
        LFloatUnaryOperator before3 = p2 -> {
            assertThat(p2).isEqualTo(82f);
            beforeCalls.incrementAndGet();
            return 92f;
        };

        //when
        LBiObjFloatFunction<Integer,Integer,Integer> function = sutO.biObjFloatFuncComposeFloat(before1,before2,before3);
        function.doApply(80,81,82f);

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(beforeCalls.get()).isEqualTo(3);
    }


    @Test
    public void testBiObjFloatFuncCompose() throws X {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LBiObjFloatFunction<Integer,Integer,Integer> sutO = (a1,a2,a3) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(90);
                assertThat(a2).isEqualTo(91);
                assertThat(a3).isEqualTo(92f);
                return 100;
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
        LToFloatFunction<Integer> before3 = p2 -> {
            assertThat(p2).isEqualTo(82);
            beforeCalls.incrementAndGet();
            return 92f;
        };

        //when
        LTriFunction<Integer,Integer,Integer,Integer> function = sutO.biObjFloatFuncCompose(before1,before2,before3);
        function.doApply(80,81,82);

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(beforeCalls.get()).isEqualTo(3);
    }

    // </editor-fold>



    // <editor-fold desc="then (functional)">

    @Test
    public void testThen0() throws X  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);

        //given (+ some assertions)
        LBiObjFloatFunction<Integer,Integer,Integer> sutO = (a1,a2,a3) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(80);
                assertThat(a2).isEqualTo(81);
                assertThat(a3).isEqualTo(82f);
                return 90;
        };

        LFunction<Integer,Integer> thenFunction = p -> {
                thenFunctionCalled.set(true);
                // Integer
                assertThat(p).isEqualTo(90);
                // Integer
                return 100;
        };

        //when
        LBiObjFloatFunction<Integer,Integer,Integer> function = sutO.then(thenFunction);
        Integer finalValue = function.doApply(80,81,82f);

        //then - finals
        assertThat(finalValue).isEqualTo(100);
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    @Test
    public void testThen1() throws X  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);

        //given (+ some assertions)
        LBiObjFloatFunction<Integer,Integer,Integer> sutO = (a1,a2,a3) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(80);
                assertThat(a2).isEqualTo(81);
                assertThat(a3).isEqualTo(82f);
                return 90;
        };

        LConsumer<Integer> thenFunction = p -> {
                thenFunctionCalled.set(true);
                // Integer
                assertThat(p).isEqualTo(90);
        };

        //when
        LBiObjFloatConsumer<Integer,Integer> function = sutO.then(thenFunction);
        function.doAccept(80,81,82f);

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    // </editor-fold>

    @Test
    public void testNesting() {
        assertThat(sut.nestingBiObjFloatFunc())
            .isSameAs(sut)
            .isInstanceOf(LBiObjFloatFunction.class);
    }

    @Test
    public void testShoving() {
        assertThat(sut.shovingBiObjFloatFunc())
            .isSameAs(sut)
            .isInstanceOf(LBiObjFloatFunction.class);
    }

    @Test
    public void testNestingX() {
        assertThat(sut.nestingBiObjFloatFuncX())
            .isSameAs(sut)
            .isInstanceOf(LBiObjFloatFunctionX.class);
    }

    @Test
    public void testShovingX() {
        assertThat(sut.shovingBiObjFloatFuncX())
            .isSameAs(sut)
            .isInstanceOf(LBiObjFloatFunctionX.class);
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void testShove() {

        // given
        LBiObjFloatFunction<Integer,Integer,Integer> sutThrowing = LBiObjFloatFunction.l((a1,a2,a3) -> {
            throw new UnsupportedOperationException();
        });

        // when
        sutThrowing.shovingBiObjFloatFunc().doApply(100,100,100f);
    }

    @Test
    public void testHandleBiObjFloatFunc() throws X {

        // given
        LBiObjFloatFunction<Integer,Integer,Integer> sutThrowing = LBiObjFloatFunction.l((a1,a2,a3) -> {
            throw new UnsupportedOperationException();
        });

        // when
        LBiObjFloatFunction<Integer,Integer,Integer> wrapped = sutThrowing.handleBiObjFloatFunc(h -> {
            h.wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED);
        });

        // then
        try {
            wrapped.doApply(100,100,100f);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IllegalArgumentException.class)
                    .hasCauseExactlyInstanceOf(UnsupportedOperationException.class)
                    .hasMessage(EXCEPTION_WAS_WRAPPED);
        }
    }

    @Test
    public void testToString() throws X {

        assertThat(sut.toString())
                .isInstanceOf(String.class)
                .startsWith(this.getClass().getName()+"$");

        assertThat(String.format("%s", sut))
                .isInstanceOf(String.class)
                .contains("LBiObjFloatFunction: R doApply(T1 a1,T2 a2,float a3)");
    }


    @Test
    public void isThrowing() {
        assertThat(sut.isThrowing())
            .isFalse();
    }

    //<editor-fold desc="Variants">

    private Integer variantV1(Integer a1,float a3,Integer a2) {
        return 100;
    }

    @Test
    public void compilerSubstituteVariantV1() {
        LBiObjFloatFunction lambda = LBiObjFloatFunction./*<T1,T2,R>*/l1(this::variantV1);

        assertThat(lambda).isInstanceOf(LBiObjFloatFunction.V1.class);
    }


    private Integer variantV2(Integer a2,Integer a1,float a3) {
        return 100;
    }

    @Test
    public void compilerSubstituteVariantV2() {
        LBiObjFloatFunction lambda = LBiObjFloatFunction./*<T1,T2,R>*/l2(this::variantV2);

        assertThat(lambda).isInstanceOf(LBiObjFloatFunction.V2.class);
    }


    private Integer variantV3(Integer a2,float a3,Integer a1) {
        return 100;
    }

    @Test
    public void compilerSubstituteVariantV3() {
        LBiObjFloatFunction lambda = LBiObjFloatFunction./*<T1,T2,R>*/l3(this::variantV3);

        assertThat(lambda).isInstanceOf(LBiObjFloatFunction.V3.class);
    }


    private Integer variantV4(float a3,Integer a1,Integer a2) {
        return 100;
    }

    @Test
    public void compilerSubstituteVariantV4() {
        LBiObjFloatFunction lambda = LBiObjFloatFunction./*<T1,T2,R>*/l4(this::variantV4);

        assertThat(lambda).isInstanceOf(LBiObjFloatFunction.V4.class);
    }


    private Integer variantV5(float a3,Integer a2,Integer a1) {
        return 100;
    }

    @Test
    public void compilerSubstituteVariantV5() {
        LBiObjFloatFunction lambda = LBiObjFloatFunction./*<T1,T2,R>*/l5(this::variantV5);

        assertThat(lambda).isInstanceOf(LBiObjFloatFunction.V5.class);
    }

    //</editor-fold>


    @Test void safeCompiles() {
        LBiObjFloatFunction r1 = LBiObjFloatFunction.safe(sut); //NOSONAR
        LBiObjFloatFunctionX r2 = LBiObjFloatFunction.safe(sut); //NOSONAR
    }

    @Test void safePropagates() {
        Object result = LBiObjFloatFunction.safe(sut);
        assertThat(result).isSameAs(sut);
    }

    @Test void safeProtectsAgainstNpe() {
        Object result = LBiObjFloatFunction.safe(null);
        assertThat(result).isSameAs(LBiObjFloatFunction.l(LBiObjFloatFunction.safe()));
    }

    @Test  void safeSupplierPropagates() {
        LSupplier<LBiObjFloatFunction<Integer,Integer,Integer>> supplier = ()->sut;
        Object result = LBiObjFloatFunction.safeSupplier(supplier);
        assertThat(result).isSameAs(supplier);
    }

    @Test  void safeSupplierProtectsAgainstNpe() {
        Object result = LBiObjFloatFunction.safeSupplier(null);
        assertThat(result).isSameAs(LBiObjFloatFunction.safeSupplier());
    }

    @Test  void safeSupplierCompiles() {
        LSupplier<LBiObjFloatFunction<Integer,Integer,Integer>> r1 = LBiObjFloatFunction.safeSupplier(()->sut);  //NOSONAR
    }

}
