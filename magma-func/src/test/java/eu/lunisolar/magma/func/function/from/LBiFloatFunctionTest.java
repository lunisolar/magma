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
public class LBiFloatFunctionTest<R,X extends ParseException> {
    private static final String ORIGINAL_MESSAGE = "Original message";
    private static final String EXCEPTION_WAS_WRAPPED = "Exception was wrapped.";
    private static final String NO_EXCEPTION_WERE_THROWN = "No exception were thrown.";

    private Integer testValue = 100;



    private LBiFloatFunction<Integer> sut = new LBiFloatFunction<Integer>(){
        public @Nullable Integer doApply(float a1,float a2)  {
            return testValue;
        }
    };

    private LBiFloatFunctionX<Integer,X> opposite = new LBiFloatFunctionX<Integer,X>(){
        public @Nullable Integer doApply(float a1,float a2)  throws X {
            return testValue;
        }
    };

    private LBiFloatFunction<Integer> sutNull = new LBiFloatFunction<Integer>(){
        public @Nullable Integer doApply(float a1,float a2)  {
            return null;
        }
    };




    private LBiFloatFunctionX<Integer,RuntimeException> sutAlwaysThrowingUnchecked = LBiFloatFunction.l((a1,a2) -> {
            throw new IndexOutOfBoundsException(ORIGINAL_MESSAGE);
    });


    @Test
    public void testTheResult() throws X {
        assertThat(sut.doApply(100f,100f))
            .isEqualTo(testValue);
    }

    @Test
    public void testTupleCall() throws X {

        LFloatPair domainObject = Tuple4U.floatPair(100f,100f);

        Object result = sut.tupleApply(domainObject);

        assertThat(result)
            .isEqualTo(testValue);
    }

    @Test
    public void testNonNullDoApply() throws X {
        assertThat(sut.nonNullDoApply(100f,100f))
            .isSameAs(testValue);
    }

    @Test
    public void testNestingDoApplyUnchecked() throws X {

        // then
        try {
            sutAlwaysThrowingUnchecked.nestingDoApply(100f,100f);
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
            sutAlwaysThrowingUnchecked.shovingDoApply(100f,100f);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                    .hasNoCause()
                    .hasMessage(ORIGINAL_MESSAGE);
        }
    }

    @Test(expectedExceptions=NullPointerException.class, expectedExceptionsMessageRegExp="\\QEvaluated value by nonNullDoApply() method cannot be null (LBiFloatFunction: R doApply(float a1,float a2)).\\E")
    public void testNonNullCapturesNull() throws X {
        sutNull.nonNullDoApply(100f,100f);
    }


    @Test
    public void testFunctionalInterfaceDescription() throws X {
        assertThat(sut.functionalInterfaceDescription())
            .isEqualTo("LBiFloatFunction: R doApply(float a1,float a2)");
    }

    @Test
    public void testLMethod() throws X {
        assertThat(LBiFloatFunction.l((a1,a2) -> testValue ))
            .isInstanceOf(LBiFloatFunction.class);
    }

    @Test
    public void testWrapMethod() throws X {
        assertThat(LBiFloatFunction.wrap(opposite))
            .isInstanceOf(LBiFloatFunction.class);
    }

    @Test
    public void testWrapMethodDoNotWrapsRuntimeException() throws X {
        // given
        LBiFloatFunctionX<Integer,X> sutThrowing = LBiFloatFunctionX.lX((a1,a2) -> {
            throw new UnsupportedOperationException(ORIGINAL_MESSAGE);
        });

        // when
        LBiFloatFunction<Integer> wrapped = LBiFloatFunction.wrap(sutThrowing);

        // then
        try {
            wrapped.doApply(100f,100f);
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
        LBiFloatFunctionX<Integer,ParseException> sutThrowing = LBiFloatFunctionX.lX((a1,a2) -> {
            throw new ParseException(ORIGINAL_MESSAGE, 0);
        });

        // when
        LBiFloatFunction<Integer> wrapped = LBiFloatFunction.wrap(sutThrowing);

        // then
        try {
            wrapped.doApply(100f,100f);
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
        LBiFloatFunction<Integer> sutThrowing = LBiFloatFunction.l((a1,a2) -> {
            throw new UnsupportedOperationException();
        });

        // when
        LBiFloatFunction<Integer> wrapped = sutThrowing.handleBiFloatFunc(handler -> handler
            .wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED));

        // then
        try {
            wrapped.doApply(100f,100f);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IllegalArgumentException.class)
                    .hasCauseExactlyInstanceOf(UnsupportedOperationException.class)
                    .hasMessage(EXCEPTION_WAS_WRAPPED);
        }
    }

    @Test
    public void testHandleBiFloatFuncMethodDoNotWrapsOtherExceptionIf() throws X {

        // given
        LBiFloatFunction<Integer> sutThrowing = LBiFloatFunction.l((a1,a2) -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LBiFloatFunction<Integer> wrapped = sutThrowing.handleBiFloatFunc(handler -> handler
                .wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED)
                .throwIf(IndexOutOfBoundsException.class));

        // then
        try {
            wrapped.doApply(100f,100f);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                    .hasNoCause();
        }
    }

@Test
    public void testHandleBiFloatFuncMethodDoNotWrapsOtherExceptionWhen() throws X {

        // given
        LBiFloatFunction<Integer> sutThrowing = LBiFloatFunction.l((a1,a2) -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LBiFloatFunction<Integer> wrapped = sutThrowing.handleBiFloatFunc(handler -> handler
                .wrapWhen(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED)
                .throwIf(IndexOutOfBoundsException.class));

        // then
        try {
            wrapped.doApply(100f,100f);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                    .hasNoCause();
        }
    }


    @Test
    public void testHandleBiFloatFuncMishandlingExceptionIsAllowed() throws X {

        // given
        LBiFloatFunction<Integer> sutThrowing = LBiFloatFunction.l((a1,a2) -> {
            throw new UnsupportedOperationException(ORIGINAL_MESSAGE);
        });

        // when
        LBiFloatFunction<Integer> wrapped = sutThrowing.handleBiFloatFunc(h -> Function4U.doNothing());

        // then
        try {
            wrapped.doApply(100f,100f);
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
    public void testBiFloatFuncComposeFloat() throws X {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LBiFloatFunction<Integer> sutO = (a1,a2) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(90f);
                assertThat(a2).isEqualTo(91f);
                return 100;
        };

        LFloatUnaryOperator before1 = p0 -> {
            assertThat(p0).isEqualTo(80f);
            beforeCalls.incrementAndGet();
            return 90f;
        };
        LFloatUnaryOperator before2 = p1 -> {
            assertThat(p1).isEqualTo(81f);
            beforeCalls.incrementAndGet();
            return 91f;
        };

        //when
        LBiFloatFunction<Integer> function = sutO.biFloatFuncComposeFloat(before1,before2);
        function.doApply(80f,81f);

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(beforeCalls.get()).isEqualTo(2);
    }


    @Test
    public void testBiFloatFuncCompose() throws X {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LBiFloatFunction<Integer> sutO = (a1,a2) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(90f);
                assertThat(a2).isEqualTo(91f);
                return 100;
        };

        LToFloatFunction<Integer> before1 = p0 -> {
            assertThat(p0).isEqualTo(80);
            beforeCalls.incrementAndGet();
            return 90f;
        };
        LToFloatFunction<Integer> before2 = p1 -> {
            assertThat(p1).isEqualTo(81);
            beforeCalls.incrementAndGet();
            return 91f;
        };

        //when
        LBiFunction<Integer,Integer,Integer> function = sutO.biFloatFuncCompose(before1,before2);
        function.doApply(80,81);

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(beforeCalls.get()).isEqualTo(2);
    }

    // </editor-fold>



    // <editor-fold desc="then (functional)">

    @Test
    public void testThen0() throws X  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);

        //given (+ some assertions)
        LBiFloatFunction<Integer> sutO = (a1,a2) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(80f);
                assertThat(a2).isEqualTo(81f);
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
        LBiFloatFunction<Integer> function = sutO.then(thenFunction);
        Integer finalValue = function.doApply(80f,81f);

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
        LBiFloatFunction<Integer> sutO = (a1,a2) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(80f);
                assertThat(a2).isEqualTo(81f);
                return 90;
        };

        LConsumer<Integer> thenFunction = p -> {
                thenFunctionCalled.set(true);
                // Integer
                assertThat(p).isEqualTo(90);
        };

        //when
        LBiFloatConsumer function = sutO.then(thenFunction);
        function.doAccept(80f,81f);

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    // </editor-fold>

    @Test
    public void testNesting() {
        assertThat(sut.nestingBiFloatFunc())
            .isSameAs(sut)
            .isInstanceOf(LBiFloatFunction.class);
    }

    @Test
    public void testShoving() {
        assertThat(sut.shovingBiFloatFunc())
            .isSameAs(sut)
            .isInstanceOf(LBiFloatFunction.class);
    }

    @Test
    public void testNestingX() {
        assertThat(sut.nestingBiFloatFuncX())
            .isSameAs(sut)
            .isInstanceOf(LBiFloatFunctionX.class);
    }

    @Test
    public void testShovingX() {
        assertThat(sut.shovingBiFloatFuncX())
            .isSameAs(sut)
            .isInstanceOf(LBiFloatFunctionX.class);
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void testShove() {

        // given
        LBiFloatFunction<Integer> sutThrowing = LBiFloatFunction.l((a1,a2) -> {
            throw new UnsupportedOperationException();
        });

        // when
        sutThrowing.shovingBiFloatFunc().doApply(100f,100f);
    }

    @Test
    public void testHandleBiFloatFunc() throws X {

        // given
        LBiFloatFunction<Integer> sutThrowing = LBiFloatFunction.l((a1,a2) -> {
            throw new UnsupportedOperationException();
        });

        // when
        LBiFloatFunction<Integer> wrapped = sutThrowing.handleBiFloatFunc(h -> {
            h.wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED);
        });

        // then
        try {
            wrapped.doApply(100f,100f);
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
                .contains("LBiFloatFunction: R doApply(float a1,float a2)");
    }


    @Test
    public void isThrowing() {
        assertThat(sut.isThrowing())
            .isFalse();
    }

    //<editor-fold desc="Variants">

    private Integer variantV1(float a2,float a1) {
        return 100;
    }

    @Test
    public void compilerSubstituteVariantV1() {
        LBiFloatFunction lambda = LBiFloatFunction./*<R>*/l1(this::variantV1);

        assertThat(lambda).isInstanceOf(LBiFloatFunction.V1.class);
    }

    //</editor-fold>


    @Test void safeCompiles() {
        LBiFloatFunction r1 = LBiFloatFunction.safe(sut); //NOSONAR
        LBiFloatFunctionX r2 = LBiFloatFunction.safe(sut); //NOSONAR
    }

    @Test void safePropagates() {
        Object result = LBiFloatFunction.safe(sut);
        assertThat(result).isSameAs(sut);
    }

    @Test void safeProtectsAgainstNpe() {
        Object result = LBiFloatFunction.safe(null);
        assertThat(result).isSameAs(LBiFloatFunction.l(LBiFloatFunction.safe()));
    }

    @Test  void safeSupplierPropagates() {
        LSupplier<LBiFloatFunction<Integer>> supplier = ()->sut;
        Object result = LBiFloatFunction.safeSupplier(supplier);
        assertThat(result).isSameAs(supplier);
    }

    @Test  void safeSupplierProtectsAgainstNpe() {
        Object result = LBiFloatFunction.safeSupplier(null);
        assertThat(result).isSameAs(LBiFloatFunction.safeSupplier());
    }

    @Test  void safeSupplierCompiles() {
        LSupplier<LBiFloatFunction<Integer>> r1 = LBiFloatFunction.safeSupplier(()->sut);  //NOSONAR
    }

}
