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
public class LBiObjShortFunctionTest<T1,T2,R,X extends ParseException> {
    private static final String ORIGINAL_MESSAGE = "Original message";
    private static final String EXCEPTION_WAS_WRAPPED = "Exception was wrapped.";
    private static final String NO_EXCEPTION_WERE_THROWN = "No exception were thrown.";

    private Integer testValue = 100;



    private LBiObjShortFunction<Integer,Integer,Integer> sut = new LBiObjShortFunction<Integer,Integer,Integer>(){
        public @Nullable Integer doApply(Integer a1,Integer a2,short a3)  {
            return testValue;
        }
    };

    private LBiObjShortFunctionX<Integer,Integer,Integer,X> opposite = new LBiObjShortFunctionX<Integer,Integer,Integer,X>(){
        public @Nullable Integer doApply(Integer a1,Integer a2,short a3)  throws X {
            return testValue;
        }
    };

    private LBiObjShortFunction<Integer,Integer,Integer> sutNull = new LBiObjShortFunction<Integer,Integer,Integer>(){
        public @Nullable Integer doApply(Integer a1,Integer a2,short a3)  {
            return null;
        }
    };




    private LBiObjShortFunctionX<Integer,Integer,Integer,RuntimeException> sutAlwaysThrowingUnchecked = LBiObjShortFunction.l((a1,a2,a3) -> {
            throw new IndexOutOfBoundsException(ORIGINAL_MESSAGE);
    });


    @Test
    public void testTheResult() throws X {
        assertThat(sut.doApply(100,100,(short)100))
            .isEqualTo(testValue);
    }

    @Test
    public void testTupleCall() throws X {

        LBiObjShortTriple<Integer,Integer> domainObject = Tuple4U.biObjShortTriple(100,100,(short)100);

        Object result = sut.tupleApply(domainObject);

        assertThat(result)
            .isEqualTo(testValue);
    }

    @Test
    public void testNonNullDoApply() throws X {
        assertThat(sut.nonNullDoApply(100,100,(short)100))
            .isSameAs(testValue);
    }

    @Test
    public void testNestingDoApplyUnchecked() throws X {

        // then
        try {
            sutAlwaysThrowingUnchecked.nestingDoApply(100,100,(short)100);
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
            sutAlwaysThrowingUnchecked.shovingDoApply(100,100,(short)100);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                    .hasNoCause()
                    .hasMessage(ORIGINAL_MESSAGE);
        }
    }

    @Test(expectedExceptions=NullPointerException.class, expectedExceptionsMessageRegExp="\\QEvaluated value by nonNullDoApply() method cannot be null (LBiObjShortFunction: R doApply(T1 a1,T2 a2,short a3)).\\E")
    public void testNonNullCapturesNull() throws X {
        sutNull.nonNullDoApply(100,100,(short)100);
    }


    @Test
    public void testFunctionalInterfaceDescription() throws X {
        assertThat(sut.functionalInterfaceDescription())
            .isEqualTo("LBiObjShortFunction: R doApply(T1 a1,T2 a2,short a3)");
    }

    @Test
    public void testLMethod() throws X {
        assertThat(LBiObjShortFunction.l((a1,a2,a3) -> testValue ))
            .isInstanceOf(LBiObjShortFunction.class);
    }

    @Test
    public void testWrapMethod() throws X {
        assertThat(LBiObjShortFunction.wrap(opposite))
            .isInstanceOf(LBiObjShortFunction.class);
    }

    @Test
    public void testWrapMethodDoNotWrapsRuntimeException() throws X {
        // given
        LBiObjShortFunctionX<Integer,Integer,Integer,X> sutThrowing = LBiObjShortFunctionX.lX((a1,a2,a3) -> {
            throw new UnsupportedOperationException(ORIGINAL_MESSAGE);
        });

        // when
        LBiObjShortFunction<Integer,Integer,Integer> wrapped = LBiObjShortFunction.wrap(sutThrowing);

        // then
        try {
            wrapped.doApply(100,100,(short)100);
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
        LBiObjShortFunctionX<Integer,Integer,Integer,ParseException> sutThrowing = LBiObjShortFunctionX.lX((a1,a2,a3) -> {
            throw new ParseException(ORIGINAL_MESSAGE, 0);
        });

        // when
        LBiObjShortFunction<Integer,Integer,Integer> wrapped = LBiObjShortFunction.wrap(sutThrowing);

        // then
        try {
            wrapped.doApply(100,100,(short)100);
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
        LBiObjShortFunction<Integer,Integer,Integer> sutThrowing = LBiObjShortFunction.l((a1,a2,a3) -> {
            throw new UnsupportedOperationException();
        });

        // when
        LBiObjShortFunction<Integer,Integer,Integer> wrapped = sutThrowing.handleBiObjShortFunc(handler -> handler
            .wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED));

        // then
        try {
            wrapped.doApply(100,100,(short)100);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IllegalArgumentException.class)
                    .hasCauseExactlyInstanceOf(UnsupportedOperationException.class)
                    .hasMessage(EXCEPTION_WAS_WRAPPED);
        }
    }

    @Test
    public void testHandleBiObjShortFuncMethodDoNotWrapsOtherExceptionIf() throws X {

        // given
        LBiObjShortFunction<Integer,Integer,Integer> sutThrowing = LBiObjShortFunction.l((a1,a2,a3) -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LBiObjShortFunction<Integer,Integer,Integer> wrapped = sutThrowing.handleBiObjShortFunc(handler -> handler
                .wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED)
                .throwIf(IndexOutOfBoundsException.class));

        // then
        try {
            wrapped.doApply(100,100,(short)100);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                    .hasNoCause();
        }
    }

@Test
    public void testHandleBiObjShortFuncMethodDoNotWrapsOtherExceptionWhen() throws X {

        // given
        LBiObjShortFunction<Integer,Integer,Integer> sutThrowing = LBiObjShortFunction.l((a1,a2,a3) -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LBiObjShortFunction<Integer,Integer,Integer> wrapped = sutThrowing.handleBiObjShortFunc(handler -> handler
                .wrapWhen(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED)
                .throwIf(IndexOutOfBoundsException.class));

        // then
        try {
            wrapped.doApply(100,100,(short)100);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                    .hasNoCause();
        }
    }


    @Test
    public void testHandleBiObjShortFuncMishandlingExceptionIsAllowed() throws X {

        // given
        LBiObjShortFunction<Integer,Integer,Integer> sutThrowing = LBiObjShortFunction.l((a1,a2,a3) -> {
            throw new UnsupportedOperationException(ORIGINAL_MESSAGE);
        });

        // when
        LBiObjShortFunction<Integer,Integer,Integer> wrapped = sutThrowing.handleBiObjShortFunc(h -> Function4U.doNothing());

        // then
        try {
            wrapped.doApply(100,100,(short)100);
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
    public void testBiObjShortFuncComposeShort() throws X {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LBiObjShortFunction<Integer,Integer,Integer> sutO = (a1,a2,a3) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(90);
                assertThat(a2).isEqualTo(91);
                assertThat(a3).isEqualTo((short)92);
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
        LShortUnaryOperator before3 = p2 -> {
            assertThat(p2).isEqualTo((short)82);
            beforeCalls.incrementAndGet();
            return (short)92;
        };

        //when
        LBiObjShortFunction<Integer,Integer,Integer> function = sutO.biObjShortFuncComposeShort(before1,before2,before3);
        function.doApply(80,81,(short)82);

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(beforeCalls.get()).isEqualTo(3);
    }


    @Test
    public void testBiObjShortFuncCompose() throws X {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LBiObjShortFunction<Integer,Integer,Integer> sutO = (a1,a2,a3) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(90);
                assertThat(a2).isEqualTo(91);
                assertThat(a3).isEqualTo((short)92);
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
        LToShortFunction<Integer> before3 = p2 -> {
            assertThat(p2).isEqualTo(82);
            beforeCalls.incrementAndGet();
            return (short)92;
        };

        //when
        LTriFunction<Integer,Integer,Integer,Integer> function = sutO.biObjShortFuncCompose(before1,before2,before3);
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
        LBiObjShortFunction<Integer,Integer,Integer> sutO = (a1,a2,a3) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(80);
                assertThat(a2).isEqualTo(81);
                assertThat(a3).isEqualTo((short)82);
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
        LBiObjShortFunction<Integer,Integer,Integer> function = sutO.then(thenFunction);
        Integer finalValue = function.doApply(80,81,(short)82);

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
        LBiObjShortFunction<Integer,Integer,Integer> sutO = (a1,a2,a3) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(80);
                assertThat(a2).isEqualTo(81);
                assertThat(a3).isEqualTo((short)82);
                return 90;
        };

        LConsumer<Integer> thenFunction = p -> {
                thenFunctionCalled.set(true);
                // Integer
                assertThat(p).isEqualTo(90);
        };

        //when
        LBiObjShortConsumer<Integer,Integer> function = sutO.then(thenFunction);
        function.doAccept(80,81,(short)82);

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    // </editor-fold>

    @Test
    public void testNesting() {
        assertThat(sut.nestingBiObjShortFunc())
            .isSameAs(sut)
            .isInstanceOf(LBiObjShortFunction.class);
    }

    @Test
    public void testShoving() {
        assertThat(sut.shovingBiObjShortFunc())
            .isSameAs(sut)
            .isInstanceOf(LBiObjShortFunction.class);
    }

    @Test
    public void testNestingX() {
        assertThat(sut.nestingBiObjShortFuncX())
            .isSameAs(sut)
            .isInstanceOf(LBiObjShortFunctionX.class);
    }

    @Test
    public void testShovingX() {
        assertThat(sut.shovingBiObjShortFuncX())
            .isSameAs(sut)
            .isInstanceOf(LBiObjShortFunctionX.class);
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void testShove() {

        // given
        LBiObjShortFunction<Integer,Integer,Integer> sutThrowing = LBiObjShortFunction.l((a1,a2,a3) -> {
            throw new UnsupportedOperationException();
        });

        // when
        sutThrowing.shovingBiObjShortFunc().doApply(100,100,(short)100);
    }

    @Test
    public void testHandleBiObjShortFunc() throws X {

        // given
        LBiObjShortFunction<Integer,Integer,Integer> sutThrowing = LBiObjShortFunction.l((a1,a2,a3) -> {
            throw new UnsupportedOperationException();
        });

        // when
        LBiObjShortFunction<Integer,Integer,Integer> wrapped = sutThrowing.handleBiObjShortFunc(h -> {
            h.wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED);
        });

        // then
        try {
            wrapped.doApply(100,100,(short)100);
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
                .contains("LBiObjShortFunction: R doApply(T1 a1,T2 a2,short a3)");
    }


    @Test
    public void isThrowing() {
        assertThat(sut.isThrowing())
            .isFalse();
    }

    //<editor-fold desc="Variants">

    private Integer variantV1(Integer a1,short a3,Integer a2) {
        return 100;
    }

    @Test
    public void compilerSubstituteVariantV1() {
        LBiObjShortFunction lambda = LBiObjShortFunction./*<T1,T2,R>*/l1(this::variantV1);

        assertThat(lambda).isInstanceOf(LBiObjShortFunction.V1.class);
    }


    private Integer variantV2(Integer a2,Integer a1,short a3) {
        return 100;
    }

    @Test
    public void compilerSubstituteVariantV2() {
        LBiObjShortFunction lambda = LBiObjShortFunction./*<T1,T2,R>*/l2(this::variantV2);

        assertThat(lambda).isInstanceOf(LBiObjShortFunction.V2.class);
    }


    private Integer variantV3(Integer a2,short a3,Integer a1) {
        return 100;
    }

    @Test
    public void compilerSubstituteVariantV3() {
        LBiObjShortFunction lambda = LBiObjShortFunction./*<T1,T2,R>*/l3(this::variantV3);

        assertThat(lambda).isInstanceOf(LBiObjShortFunction.V3.class);
    }


    private Integer variantV4(short a3,Integer a1,Integer a2) {
        return 100;
    }

    @Test
    public void compilerSubstituteVariantV4() {
        LBiObjShortFunction lambda = LBiObjShortFunction./*<T1,T2,R>*/l4(this::variantV4);

        assertThat(lambda).isInstanceOf(LBiObjShortFunction.V4.class);
    }


    private Integer variantV5(short a3,Integer a2,Integer a1) {
        return 100;
    }

    @Test
    public void compilerSubstituteVariantV5() {
        LBiObjShortFunction lambda = LBiObjShortFunction./*<T1,T2,R>*/l5(this::variantV5);

        assertThat(lambda).isInstanceOf(LBiObjShortFunction.V5.class);
    }

    //</editor-fold>


    @Test void safeCompiles() {
        LBiObjShortFunction r1 = LBiObjShortFunction.safe(sut); //NOSONAR
        LBiObjShortFunctionX r2 = LBiObjShortFunction.safe(sut); //NOSONAR
    }

    @Test void safePropagates() {
        Object result = LBiObjShortFunction.safe(sut);
        assertThat(result).isSameAs(sut);
    }

    @Test void safeProtectsAgainstNpe() {
        Object result = LBiObjShortFunction.safe(null);
        assertThat(result).isSameAs(LBiObjShortFunction.l(LBiObjShortFunction.safe()));
    }

    @Test  void safeSupplierPropagates() {
        LSupplier<LBiObjShortFunction<Integer,Integer,Integer>> supplier = ()->sut;
        Object result = LBiObjShortFunction.safeSupplier(supplier);
        assertThat(result).isSameAs(supplier);
    }

    @Test  void safeSupplierProtectsAgainstNpe() {
        Object result = LBiObjShortFunction.safeSupplier(null);
        assertThat(result).isSameAs(LBiObjShortFunction.safeSupplier());
    }

    @Test  void safeSupplierCompiles() {
        LSupplier<LBiObjShortFunction<Integer,Integer,Integer>> r1 = LBiObjShortFunction.safeSupplier(()->sut);  //NOSONAR
    }

}
