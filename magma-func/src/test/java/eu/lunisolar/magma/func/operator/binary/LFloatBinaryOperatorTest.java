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

package eu.lunisolar.magma.func.operator.binary;

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
public class LFloatBinaryOperatorTest<X extends ParseException> {
    private static final String ORIGINAL_MESSAGE = "Original message";
    private static final String EXCEPTION_WAS_WRAPPED = "Exception was wrapped.";
    private static final String NO_EXCEPTION_WERE_THROWN = "No exception were thrown.";

    private float testValue = 100f;



    private LFloatBinaryOperator sut = new LFloatBinaryOperator(){
        public  float doApplyAsFloat(float a1,float a2)  {
            return testValue;
        }
    };

    private LFloatBinaryOperatorX<X> opposite = new LFloatBinaryOperatorX<X>(){
        public  float doApplyAsFloat(float a1,float a2)  throws X {
            return testValue;
        }
    };




    private LFloatBinaryOperatorX<RuntimeException> sutAlwaysThrowingUnchecked = LFloatBinaryOperator.l((a1,a2) -> {
            throw new IndexOutOfBoundsException(ORIGINAL_MESSAGE);
    });


    @Test
    public void testTheResult() throws X {
        assertThat(sut.doApplyAsFloat(100f,100f))
            .isEqualTo(testValue);
    }

    @Test
    public void testTupleCall() throws X {

        LFloatPair domainObject = Tuple4U.floatPair(100f,100f);

        Object result = sut.tupleApplyAsFloat(domainObject);

        assertThat(result)
            .isEqualTo(testValue);
    }

    @Test
    public void testNonNullDoApplyAsFloat() throws X {
        assertThat(sut.nonNullDoApplyAsFloat(100f,100f))
            .isEqualTo(testValue);
    }

    @Test
    public void testNestingDoApplyAsFloatUnchecked() throws X {

        // then
        try {
            sutAlwaysThrowingUnchecked.nestingDoApplyAsFloat(100f,100f);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                    .hasNoCause()
                    .hasMessage(ORIGINAL_MESSAGE);
        }
    }

    @Test
    public void testShovingDoApplyAsFloatUnchecked() throws X {

        // then
        try {
            sutAlwaysThrowingUnchecked.shovingDoApplyAsFloat(100f,100f);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                    .hasNoCause()
                    .hasMessage(ORIGINAL_MESSAGE);
        }
    }


    @Test
    public void testFunctionalInterfaceDescription() throws X {
        assertThat(sut.functionalInterfaceDescription())
            .isEqualTo("LFloatBinaryOperator: float doApplyAsFloat(float a1,float a2)");
    }

    @Test
    public void testLMethod() throws X {
        assertThat(LFloatBinaryOperator.l((a1,a2) -> testValue ))
            .isInstanceOf(LFloatBinaryOperator.class);
    }

    @Test
    public void testWrapMethod() throws X {
        assertThat(LFloatBinaryOperator.wrap(opposite))
            .isInstanceOf(LFloatBinaryOperator.class);
    }

    @Test
    public void testWrapMethodDoNotWrapsRuntimeException() throws X {
        // given
        LFloatBinaryOperatorX<X> sutThrowing = LFloatBinaryOperatorX.lX((a1,a2) -> {
            throw new UnsupportedOperationException(ORIGINAL_MESSAGE);
        });

        // when
        LFloatBinaryOperator wrapped = LFloatBinaryOperator.wrap(sutThrowing);

        // then
        try {
            wrapped.doApplyAsFloat(100f,100f);
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
        LFloatBinaryOperatorX<ParseException> sutThrowing = LFloatBinaryOperatorX.lX((a1,a2) -> {
            throw new ParseException(ORIGINAL_MESSAGE, 0);
        });

        // when
        LFloatBinaryOperator wrapped = LFloatBinaryOperator.wrap(sutThrowing);

        // then
        try {
            wrapped.doApplyAsFloat(100f,100f);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(NestedException.class)
                    .hasCauseExactlyInstanceOf(ParseException.class)
                    .hasMessage(ORIGINAL_MESSAGE);
        }
    }


    @Test
    public void testHandlingDoApplyAsFloatMethodWrapsTheException() throws X {

        // given
        LFloatBinaryOperator sutThrowing = LFloatBinaryOperator.l((a1,a2) -> {
            throw new UnsupportedOperationException();
        });

        // when
        LFloatBinaryOperator wrapped = sutThrowing.handleFloatBinaryOp(handler -> handler
            .wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED));

        // then
        try {
            wrapped.doApplyAsFloat(100f,100f);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IllegalArgumentException.class)
                    .hasCauseExactlyInstanceOf(UnsupportedOperationException.class)
                    .hasMessage(EXCEPTION_WAS_WRAPPED);
        }
    }

    @Test
    public void testHandleFloatBinaryOpMethodDoNotWrapsOtherExceptionIf() throws X {

        // given
        LFloatBinaryOperator sutThrowing = LFloatBinaryOperator.l((a1,a2) -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LFloatBinaryOperator wrapped = sutThrowing.handleFloatBinaryOp(handler -> handler
                .wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED)
                .throwIf(IndexOutOfBoundsException.class));

        // then
        try {
            wrapped.doApplyAsFloat(100f,100f);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                    .hasNoCause();
        }
    }

@Test
    public void testHandleFloatBinaryOpMethodDoNotWrapsOtherExceptionWhen() throws X {

        // given
        LFloatBinaryOperator sutThrowing = LFloatBinaryOperator.l((a1,a2) -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LFloatBinaryOperator wrapped = sutThrowing.handleFloatBinaryOp(handler -> handler
                .wrapWhen(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED)
                .throwIf(IndexOutOfBoundsException.class));

        // then
        try {
            wrapped.doApplyAsFloat(100f,100f);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                    .hasNoCause();
        }
    }


    @Test
    public void testHandleFloatBinaryOpMishandlingExceptionIsAllowed() throws X {

        // given
        LFloatBinaryOperator sutThrowing = LFloatBinaryOperator.l((a1,a2) -> {
            throw new UnsupportedOperationException(ORIGINAL_MESSAGE);
        });

        // when
        LFloatBinaryOperator wrapped = sutThrowing.handleFloatBinaryOp(h -> Function4U.doNothing());

        // then
        try {
            wrapped.doApplyAsFloat(100f,100f);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
             .isExactlyInstanceOf(UnsupportedOperationException.class)
             .hasNoCause()
             .hasMessage(ORIGINAL_MESSAGE);
        }
    }


    @Test
    public void minBy() throws X  {
        //when
        LFloatBinaryOperator min =  LFloatBinaryOperator.minBy(Float::compare);

        //then
        assertThat(min.doApplyAsFloat(0f, 56f))
                .isEqualTo(0f);
        assertThat(min.doApplyAsFloat(56f, 0f))
                       .isEqualTo(0f);

    }

    @Test
    public void maxBy() throws X  {
        //when
        LFloatBinaryOperator max =  LFloatBinaryOperator.maxBy(Float::compare);

        //then
        assertThat(max.doApplyAsFloat(0f, 56f))
                .isEqualTo(56f);
        assertThat(max.doApplyAsFloat(56f, 0f))
                        .isEqualTo(56f);
    }


    @Test
    public void testMin() throws X {
        //given
        float valueSmall = 10f;
        float valueBig = 100f;

        //when
        LFloatBinaryOperator min = LFloatBinaryOperator.min();

        //then
        assertThat(min.doApplyAsFloat(valueSmall, valueBig))
                .isEqualTo(valueSmall);

        assertThat(min.doApplyAsFloat(valueBig, valueSmall))
                .isEqualTo(valueSmall);
    }

    @Test
    public void testMax() throws X {
        //given
        float valueSmall = 10f;
        float valueBig = 100f;

        //when
        LFloatBinaryOperator max = LFloatBinaryOperator.max();

        //then
        assertThat(max.doApplyAsFloat(valueSmall, valueBig))
                .isEqualTo(valueBig);

        assertThat(max.doApplyAsFloat(valueBig, valueSmall))
                .isEqualTo(valueBig);
    }


    // <editor-fold desc="compose (functional)">

    @Test
    public void testFloatBinaryOpComposeFloat() throws X {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LFloatBinaryOperator sutO = (float a1,float a2) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(90f);
                assertThat(a2).isEqualTo(91f);
                return 100f;
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
        LFloatBinaryOperator function = sutO.floatBinaryOpComposeFloat(before1,before2);
        function.doApplyAsFloat(80f,81f);

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(beforeCalls.get()).isEqualTo(2);
    }


    @Test
    public void testFloatBinaryOpCompose() throws X {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LFloatBinaryOperator sutO = (float a1,float a2) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(90f);
                assertThat(a2).isEqualTo(91f);
                return 100f;
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
        LToFloatBiFunction<Integer,Integer> function = sutO.floatBinaryOpCompose(before1,before2);
        function.doApplyAsFloat(80,81);

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
        LFloatBinaryOperator sutO = (a1,a2) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(80f);
                assertThat(a2).isEqualTo(81f);
                return 90f;
        };

        LFloatFunction<Integer> thenFunction = p -> {
                thenFunctionCalled.set(true);
                // float
                assertThat(p).isEqualTo(90f);
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



    // </editor-fold>

    @Test
    public void testNesting() {
        assertThat(sut.nestingFloatBinaryOp())
            .isSameAs(sut)
            .isInstanceOf(LFloatBinaryOperator.class);
    }

    @Test
    public void testShoving() {
        assertThat(sut.shovingFloatBinaryOp())
            .isSameAs(sut)
            .isInstanceOf(LFloatBinaryOperator.class);
    }

    @Test
    public void testNestingX() {
        assertThat(sut.nestingFloatBinaryOpX())
            .isSameAs(sut)
            .isInstanceOf(LFloatBinaryOperatorX.class);
    }

    @Test
    public void testShovingX() {
        assertThat(sut.shovingFloatBinaryOpX())
            .isSameAs(sut)
            .isInstanceOf(LFloatBinaryOperatorX.class);
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void testShove() {

        // given
        LFloatBinaryOperator sutThrowing = LFloatBinaryOperator.l((a1,a2) -> {
            throw new UnsupportedOperationException();
        });

        // when
        sutThrowing.shovingFloatBinaryOp().doApplyAsFloat(100f,100f);
    }

    @Test
    public void testHandleFloatBinaryOp() throws X {

        // given
        LFloatBinaryOperator sutThrowing = LFloatBinaryOperator.l((a1,a2) -> {
            throw new UnsupportedOperationException();
        });

        // when
        LFloatBinaryOperator wrapped = sutThrowing.handleFloatBinaryOp(h -> {
            h.wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED);
        });

        // then
        try {
            wrapped.doApplyAsFloat(100f,100f);
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
                .contains("LFloatBinaryOperator: float doApplyAsFloat(float a1,float a2)");
    }


    @Test
    public void isThrowing() {
        assertThat(sut.isThrowing())
            .isFalse();
    }

    @Test void safeCompiles() {
        LFloatBinaryOperator r1 = LFloatBinaryOperator.safe(sut); //NOSONAR
        LFloatBinaryOperatorX r2 = LFloatBinaryOperator.safe(sut); //NOSONAR
    }

    @Test void safePropagates() {
        Object result = LFloatBinaryOperator.safe(sut);
        assertThat(result).isSameAs(sut);
    }

    @Test void safeProtectsAgainstNpe() {
        Object result = LFloatBinaryOperator.safe(null);
        assertThat(result).isSameAs(LFloatBinaryOperator.l(LFloatBinaryOperator.safe()));
    }

    @Test  void safeSupplierPropagates() {
        LSupplier<LFloatBinaryOperator> supplier = ()->sut;
        Object result = LFloatBinaryOperator.safeSupplier(supplier);
        assertThat(result).isSameAs(supplier);
    }

    @Test  void safeSupplierProtectsAgainstNpe() {
        Object result = LFloatBinaryOperator.safeSupplier(null);
        assertThat(result).isSameAs(LFloatBinaryOperator.safeSupplier());
    }

    @Test  void safeSupplierCompiles() {
        LSupplier<LFloatBinaryOperator> r1 = LFloatBinaryOperator.safeSupplier(()->sut);  //NOSONAR
    }

}
