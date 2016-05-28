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
public class LBiObjLongFunctionTest<T1,T2,R,X extends ParseException> {
    private static final String ORIGINAL_MESSAGE = "Original message";
    private static final String EXCEPTION_WAS_WRAPPED = "Exception was wrapped.";
    private static final String NO_EXCEPTION_WERE_THROWN = "No exception were thrown.";

    private Integer testValue = 100;



    private LBiObjLongFunction<Integer,Integer,Integer> sut = new LBiObjLongFunction<Integer,Integer,Integer>(){
        public @Nullable Integer doApply(Integer a1,Integer a2,long a3)  {
            return testValue;
        }
    };

    private LBiObjLongFunctionX<Integer,Integer,Integer,X> opposite = new LBiObjLongFunctionX<Integer,Integer,Integer,X>(){
        public @Nullable Integer doApply(Integer a1,Integer a2,long a3)  throws X {
            return testValue;
        }
    };

    private LBiObjLongFunction<Integer,Integer,Integer> sutNull = new LBiObjLongFunction<Integer,Integer,Integer>(){
        public @Nullable Integer doApply(Integer a1,Integer a2,long a3)  {
            return null;
        }
    };




    private LBiObjLongFunctionX<Integer,Integer,Integer,RuntimeException> sutAlwaysThrowingUnchecked = LBiObjLongFunction.l((a1,a2,a3) -> {
            throw new IndexOutOfBoundsException(ORIGINAL_MESSAGE);
    });


    @Test
    public void testTheResult() throws X {
        assertThat(sut.doApply(100,100,100L))
            .isEqualTo(testValue);
    }

    @Test
    public void testTupleCall() throws X {

        LBiObjLongTriple<Integer,Integer> domainObject = Tuple4U.biObjLongTriple(100,100,100L);

        Object result = sut.tupleApply(domainObject);

        assertThat(result)
            .isEqualTo(testValue);
    }

    @Test
    public void testNonNullDoApply() throws X {
        assertThat(sut.nonNullDoApply(100,100,100L))
            .isSameAs(testValue);
    }

    @Test
    public void testNestingDoApplyUnchecked() throws X {

        // then
        try {
            sutAlwaysThrowingUnchecked.nestingDoApply(100,100,100L);
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
            sutAlwaysThrowingUnchecked.shovingDoApply(100,100,100L);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                    .hasNoCause()
                    .hasMessage(ORIGINAL_MESSAGE);
        }
    }

    @Test(expectedExceptions=NullPointerException.class, expectedExceptionsMessageRegExp="\\QEvaluated value by nonNullDoApply() method cannot be null (LBiObjLongFunction: R doApply(T1 a1,T2 a2,long a3)).\\E")
    public void testNonNullCapturesNull() throws X {
        sutNull.nonNullDoApply(100,100,100L);
    }


    @Test
    public void testFunctionalInterfaceDescription() throws X {
        assertThat(sut.functionalInterfaceDescription())
            .isEqualTo("LBiObjLongFunction: R doApply(T1 a1,T2 a2,long a3)");
    }

    @Test
    public void testLMethod() throws X {
        assertThat(LBiObjLongFunction.l((a1,a2,a3) -> testValue ))
            .isInstanceOf(LBiObjLongFunction.class);
    }

    @Test
    public void testWrapMethod() throws X {
        assertThat(LBiObjLongFunction.wrap(opposite))
            .isInstanceOf(LBiObjLongFunction.class);
    }

    @Test
    public void testWrapMethodDoNotWrapsRuntimeException() throws X {
        // given
        LBiObjLongFunctionX<Integer,Integer,Integer,X> sutThrowing = LBiObjLongFunctionX.lX((a1,a2,a3) -> {
            throw new UnsupportedOperationException(ORIGINAL_MESSAGE);
        });

        // when
        LBiObjLongFunction<Integer,Integer,Integer> wrapped = LBiObjLongFunction.wrap(sutThrowing);

        // then
        try {
            wrapped.doApply(100,100,100L);
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
        LBiObjLongFunctionX<Integer,Integer,Integer,ParseException> sutThrowing = LBiObjLongFunctionX.lX((a1,a2,a3) -> {
            throw new ParseException(ORIGINAL_MESSAGE, 0);
        });

        // when
        LBiObjLongFunction<Integer,Integer,Integer> wrapped = LBiObjLongFunction.wrap(sutThrowing);

        // then
        try {
            wrapped.doApply(100,100,100L);
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
        LBiObjLongFunction<Integer,Integer,Integer> sutThrowing = LBiObjLongFunction.l((a1,a2,a3) -> {
            throw new UnsupportedOperationException();
        });

        // when
        LBiObjLongFunction<Integer,Integer,Integer> wrapped = sutThrowing.handleBiObjLongFunc(handler -> handler
            .wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED));

        // then
        try {
            wrapped.doApply(100,100,100L);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IllegalArgumentException.class)
                    .hasCauseExactlyInstanceOf(UnsupportedOperationException.class)
                    .hasMessage(EXCEPTION_WAS_WRAPPED);
        }
    }

    @Test
    public void testHandleBiObjLongFuncMethodDoNotWrapsOtherExceptionIf() throws X {

        // given
        LBiObjLongFunction<Integer,Integer,Integer> sutThrowing = LBiObjLongFunction.l((a1,a2,a3) -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LBiObjLongFunction<Integer,Integer,Integer> wrapped = sutThrowing.handleBiObjLongFunc(handler -> handler
                .wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED)
                .throwIf(IndexOutOfBoundsException.class));

        // then
        try {
            wrapped.doApply(100,100,100L);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                    .hasNoCause();
        }
    }

@Test
    public void testHandleBiObjLongFuncMethodDoNotWrapsOtherExceptionWhen() throws X {

        // given
        LBiObjLongFunction<Integer,Integer,Integer> sutThrowing = LBiObjLongFunction.l((a1,a2,a3) -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LBiObjLongFunction<Integer,Integer,Integer> wrapped = sutThrowing.handleBiObjLongFunc(handler -> handler
                .wrapWhen(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED)
                .throwIf(IndexOutOfBoundsException.class));

        // then
        try {
            wrapped.doApply(100,100,100L);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                    .hasNoCause();
        }
    }


    @Test
    public void testHandleBiObjLongFuncMishandlingExceptionIsAllowed() throws X {

        // given
        LBiObjLongFunction<Integer,Integer,Integer> sutThrowing = LBiObjLongFunction.l((a1,a2,a3) -> {
            throw new UnsupportedOperationException(ORIGINAL_MESSAGE);
        });

        // when
        LBiObjLongFunction<Integer,Integer,Integer> wrapped = sutThrowing.handleBiObjLongFunc(h -> Function4U.doNothing());

        // then
        try {
            wrapped.doApply(100,100,100L);
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
    public void testBiObjLongFuncComposeLong() throws X {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LBiObjLongFunction<Integer,Integer,Integer> sutO = (a1,a2,a3) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(90);
                assertThat(a2).isEqualTo(91);
                assertThat(a3).isEqualTo(92L);
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
        LLongUnaryOperator before3 = p2 -> {
            assertThat(p2).isEqualTo(82L);
            beforeCalls.incrementAndGet();
            return 92L;
        };

        //when
        LBiObjLongFunction<Integer,Integer,Integer> function = sutO.biObjLongFuncComposeLong(before1,before2,before3);
        function.doApply(80,81,82L);

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(beforeCalls.get()).isEqualTo(3);
    }


    @Test
    public void testBiObjLongFuncCompose() throws X {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LBiObjLongFunction<Integer,Integer,Integer> sutO = (a1,a2,a3) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(90);
                assertThat(a2).isEqualTo(91);
                assertThat(a3).isEqualTo(92L);
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
        LToLongFunction<Integer> before3 = p2 -> {
            assertThat(p2).isEqualTo(82);
            beforeCalls.incrementAndGet();
            return 92L;
        };

        //when
        LTriFunction<Integer,Integer,Integer,Integer> function = sutO.biObjLongFuncCompose(before1,before2,before3);
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
        LBiObjLongFunction<Integer,Integer,Integer> sutO = (a1,a2,a3) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(80);
                assertThat(a2).isEqualTo(81);
                assertThat(a3).isEqualTo(82L);
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
        LBiObjLongFunction<Integer,Integer,Integer> function = sutO.then(thenFunction);
        Integer finalValue = function.doApply(80,81,82L);

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
        LBiObjLongFunction<Integer,Integer,Integer> sutO = (a1,a2,a3) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(80);
                assertThat(a2).isEqualTo(81);
                assertThat(a3).isEqualTo(82L);
                return 90;
        };

        LConsumer<Integer> thenFunction = p -> {
                thenFunctionCalled.set(true);
                // Integer
                assertThat(p).isEqualTo(90);
        };

        //when
        LBiObjLongConsumer<Integer,Integer> function = sutO.then(thenFunction);
        function.doAccept(80,81,82L);

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    // </editor-fold>

    @Test
    public void testNesting() {
        assertThat(sut.nestingBiObjLongFunc())
            .isSameAs(sut)
            .isInstanceOf(LBiObjLongFunction.class);
    }

    @Test
    public void testShoving() {
        assertThat(sut.shovingBiObjLongFunc())
            .isSameAs(sut)
            .isInstanceOf(LBiObjLongFunction.class);
    }

    @Test
    public void testNestingX() {
        assertThat(sut.nestingBiObjLongFuncX())
            .isSameAs(sut)
            .isInstanceOf(LBiObjLongFunctionX.class);
    }

    @Test
    public void testShovingX() {
        assertThat(sut.shovingBiObjLongFuncX())
            .isSameAs(sut)
            .isInstanceOf(LBiObjLongFunctionX.class);
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void testShove() {

        // given
        LBiObjLongFunction<Integer,Integer,Integer> sutThrowing = LBiObjLongFunction.l((a1,a2,a3) -> {
            throw new UnsupportedOperationException();
        });

        // when
        sutThrowing.shovingBiObjLongFunc().doApply(100,100,100L);
    }

    @Test
    public void testHandleBiObjLongFunc() throws X {

        // given
        LBiObjLongFunction<Integer,Integer,Integer> sutThrowing = LBiObjLongFunction.l((a1,a2,a3) -> {
            throw new UnsupportedOperationException();
        });

        // when
        LBiObjLongFunction<Integer,Integer,Integer> wrapped = sutThrowing.handleBiObjLongFunc(h -> {
            h.wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED);
        });

        // then
        try {
            wrapped.doApply(100,100,100L);
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
                .contains("LBiObjLongFunction: R doApply(T1 a1,T2 a2,long a3)");
    }


    @Test
    public void isThrowing() {
        assertThat(sut.isThrowing())
            .isFalse();
    }

    //<editor-fold desc="Variants">

    private Integer variantV1(Integer a1,long a3,Integer a2) {
        return 100;
    }

    @Test
    public void compilerSubstituteVariantV1() {
        LBiObjLongFunction lambda = LBiObjLongFunction./*<T1,T2,R>*/l1(this::variantV1);

        assertThat(lambda).isInstanceOf(LBiObjLongFunction.V1.class);
    }


    private Integer variantV2(Integer a2,Integer a1,long a3) {
        return 100;
    }

    @Test
    public void compilerSubstituteVariantV2() {
        LBiObjLongFunction lambda = LBiObjLongFunction./*<T1,T2,R>*/l2(this::variantV2);

        assertThat(lambda).isInstanceOf(LBiObjLongFunction.V2.class);
    }


    private Integer variantV3(Integer a2,long a3,Integer a1) {
        return 100;
    }

    @Test
    public void compilerSubstituteVariantV3() {
        LBiObjLongFunction lambda = LBiObjLongFunction./*<T1,T2,R>*/l3(this::variantV3);

        assertThat(lambda).isInstanceOf(LBiObjLongFunction.V3.class);
    }


    private Integer variantV4(long a3,Integer a1,Integer a2) {
        return 100;
    }

    @Test
    public void compilerSubstituteVariantV4() {
        LBiObjLongFunction lambda = LBiObjLongFunction./*<T1,T2,R>*/l4(this::variantV4);

        assertThat(lambda).isInstanceOf(LBiObjLongFunction.V4.class);
    }


    private Integer variantV5(long a3,Integer a2,Integer a1) {
        return 100;
    }

    @Test
    public void compilerSubstituteVariantV5() {
        LBiObjLongFunction lambda = LBiObjLongFunction./*<T1,T2,R>*/l5(this::variantV5);

        assertThat(lambda).isInstanceOf(LBiObjLongFunction.V5.class);
    }

    //</editor-fold>


    @Test void safeCompiles() {
        LBiObjLongFunction r1 = LBiObjLongFunction.safe(sut); //NOSONAR
        LBiObjLongFunctionX r2 = LBiObjLongFunction.safe(sut); //NOSONAR
    }

    @Test void safePropagates() {
        Object result = LBiObjLongFunction.safe(sut);
        assertThat(result).isSameAs(sut);
    }

    @Test void safeProtectsAgainstNpe() {
        Object result = LBiObjLongFunction.safe(null);
        assertThat(result).isSameAs(LBiObjLongFunction.l(LBiObjLongFunction.safe()));
    }

    @Test  void safeSupplierPropagates() {
        LSupplier<LBiObjLongFunction<Integer,Integer,Integer>> supplier = ()->sut;
        Object result = LBiObjLongFunction.safeSupplier(supplier);
        assertThat(result).isSameAs(supplier);
    }

    @Test  void safeSupplierProtectsAgainstNpe() {
        Object result = LBiObjLongFunction.safeSupplier(null);
        assertThat(result).isSameAs(LBiObjLongFunction.safeSupplier());
    }

    @Test  void safeSupplierCompiles() {
        LSupplier<LBiObjLongFunction<Integer,Integer,Integer>> r1 = LBiObjLongFunction.safeSupplier(()->sut);  //NOSONAR
    }

}
