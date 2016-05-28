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
public class LFloatBinaryOperatorXTest<X extends ParseException> {
    private static final String ORIGINAL_MESSAGE = "Original message";
    private static final String EXCEPTION_WAS_WRAPPED = "Exception was wrapped.";
    private static final String NO_EXCEPTION_WERE_THROWN = "No exception were thrown.";

    private float testValue = 100f;



    private LFloatBinaryOperatorX<X> sut = new LFloatBinaryOperatorX<X>(){
        public  float doApplyAsFloat(float a1,float a2)  throws X {
            return testValue;
        }
    };

    private LFloatBinaryOperator opposite = new LFloatBinaryOperator(){
        public  float doApplyAsFloat(float a1,float a2)  {
            return testValue;
        }
    };



    private LFloatBinaryOperatorX<ParseException> sutAlwaysThrowing = LFloatBinaryOperatorX.lX((a1,a2) -> {
            throw new ParseException(ORIGINAL_MESSAGE, 0);
    });

    private LFloatBinaryOperatorX<RuntimeException> sutAlwaysThrowingUnchecked = LFloatBinaryOperatorX.lX((a1,a2) -> {
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
    public void testNestingDoApplyAsFloatChecked() throws X {

        // then
        try {
            sutAlwaysThrowing.nestingDoApplyAsFloat(100f,100f);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(NestedException.class)
                    .hasCauseExactlyInstanceOf(ParseException.class)
                    .hasMessage(ORIGINAL_MESSAGE);
        }
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
    public void testShovingDoApplyAsFloatChecked() throws X {

        // then
        try {
            sutAlwaysThrowing.shovingDoApplyAsFloat(100f,100f);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(ParseException.class)
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
            .isEqualTo("LFloatBinaryOperatorX: float doApplyAsFloat(float a1,float a2) throws X");
    }

    @Test
    public void testLXMethod() throws X {
        assertThat(LFloatBinaryOperatorX.lX((a1,a2) -> testValue ))
            .isInstanceOf(LFloatBinaryOperatorX.class);
    }

    @Test
    public void testWrapXMethod() throws X {
        assertThat(LFloatBinaryOperatorX.wrapX(opposite))
            .isInstanceOf(LFloatBinaryOperatorX.class);
    }


    @Test
    public void testHandlingDoApplyAsFloatMethodWrapsTheException() throws X {

        // given
        LFloatBinaryOperatorX<X> sutThrowing = LFloatBinaryOperatorX.lX((a1,a2) -> {
            throw new UnsupportedOperationException();
        });

        // when
        LFloatBinaryOperatorX<RuntimeException> wrapped = sutThrowing.handleFloatBinaryOpX(handler -> handler
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
    public void testHandleFloatBinaryOpXMethodDoNotWrapsOtherExceptionIf() throws X {

        // given
        LFloatBinaryOperatorX<X> sutThrowing = LFloatBinaryOperatorX.lX((a1,a2) -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LFloatBinaryOperatorX<X> wrapped = sutThrowing.handleFloatBinaryOpX(handler -> handler
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
    public void testHandleFloatBinaryOpXMethodDoNotWrapsOtherExceptionWhen() throws X {

        // given
        LFloatBinaryOperatorX<X> sutThrowing = LFloatBinaryOperatorX.lX((a1,a2) -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LFloatBinaryOperatorX<X> wrapped = sutThrowing.handleFloatBinaryOpX(handler -> handler
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
    public void testHandleFloatBinaryOpXMishandlingExceptionIsAllowed() throws X {

        // given
        LFloatBinaryOperatorX<X> sutThrowing = LFloatBinaryOperatorX.lX((a1,a2) -> {
            throw (X) new ParseException(ORIGINAL_MESSAGE, 0);
        });

        // when
        LFloatBinaryOperatorX<X> wrapped = sutThrowing.handleFloatBinaryOpX(h -> Function4U.doNothing());

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
    public void minBy() throws X  {
        //when
        LFloatBinaryOperatorX<X> min =  LFloatBinaryOperatorX.minBy(Float::compare);

        //then
        assertThat(min.doApplyAsFloat(0f, 56f))
                .isEqualTo(0f);
        assertThat(min.doApplyAsFloat(56f, 0f))
                       .isEqualTo(0f);

    }

    @Test
    public void maxBy() throws X  {
        //when
        LFloatBinaryOperatorX<X> max =  LFloatBinaryOperatorX.maxBy(Float::compare);

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
        LFloatBinaryOperatorX<X> min = LFloatBinaryOperatorX.min();

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
        LFloatBinaryOperatorX<X> max = LFloatBinaryOperatorX.max();

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
        LFloatBinaryOperatorX<X> sutO = (a1,a2) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(90f);
                assertThat(a2).isEqualTo(91f);
                return 100f;
        };

        LFloatUnaryOperatorX<X> before1 = p0 -> {
            assertThat(p0).isEqualTo(80f);
            beforeCalls.incrementAndGet();
            return 90f;
        };
        LFloatUnaryOperatorX<X> before2 = p1 -> {
            assertThat(p1).isEqualTo(81f);
            beforeCalls.incrementAndGet();
            return 91f;
        };

        //when
        LFloatBinaryOperatorX<X> function = sutO.floatBinaryOpComposeFloat(before1,before2);
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
        LFloatBinaryOperatorX<X> sutO = (a1,a2) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(90f);
                assertThat(a2).isEqualTo(91f);
                return 100f;
        };

        LToFloatFunctionX<Integer,X> before1 = p0 -> {
            assertThat(p0).isEqualTo(80);
            beforeCalls.incrementAndGet();
            return 90f;
        };
        LToFloatFunctionX<Integer,X> before2 = p1 -> {
            assertThat(p1).isEqualTo(81);
            beforeCalls.incrementAndGet();
            return 91f;
        };

        //when
        LToFloatBiFunctionX<Integer,Integer,X> function = sutO.floatBinaryOpCompose(before1,before2);
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
        LFloatBinaryOperatorX<X> sutO = (a1,a2) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(80f);
                assertThat(a2).isEqualTo(81f);
                return 90f;
        };

        LFloatFunctionX<Integer,X> thenFunction = p -> {
                thenFunctionCalled.set(true);
                // float
                assertThat(p).isEqualTo(90f);
                // Integer
                return 100;
        };

        //when
        LBiFloatFunctionX<Integer,X> function = sutO.then(thenFunction);
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
            .isInstanceOf(LFloatBinaryOperator.class);
    }

    @Test
    public void testShoving() {
        assertThat(sut.shovingFloatBinaryOp())
            .isInstanceOf(LFloatBinaryOperator.class);
    }

    @Test
    public void testNestingX() {
        assertThat(sut.nestingFloatBinaryOpX())
            .isInstanceOf(LFloatBinaryOperatorX.class);
    }

    @Test
    public void testShovingX() {
        assertThat(sut.shovingFloatBinaryOpX())
            .isInstanceOf(LFloatBinaryOperatorX.class);
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void testShove() {

        // given
        LFloatBinaryOperatorX<X> sutThrowing = LFloatBinaryOperatorX.lX((a1,a2) -> {
            throw new UnsupportedOperationException();
        });

        // when
        sutThrowing.shovingFloatBinaryOp().doApplyAsFloat(100f,100f);
    }

    @Test
    public void testHandleFloatBinaryOp() throws X {

        // given
        LFloatBinaryOperatorX<X> sutThrowing = LFloatBinaryOperatorX.lX((a1,a2) -> {
            throw new UnsupportedOperationException();
        });

        // when
        LFloatBinaryOperatorX<X> wrapped = sutThrowing.handleFloatBinaryOpX(h -> {
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
                .contains("LFloatBinaryOperatorX: float doApplyAsFloat(float a1,float a2) throws X");
    }


    @Test
    public void isThrowing() {
        assertThat(sut.isThrowing())
            .isTrue();
    }

    @Test void safeCompiles() {
        LFloatBinaryOperatorX r1 = LFloatBinaryOperatorX.safe(sut); //NOSONAR
    }

    @Test void safePropagates() {
        Object result = LFloatBinaryOperatorX.safe(sut);
        assertThat(result).isSameAs(sut);
    }

    @Test void safeProtectsAgainstNpe() {
        Object result = LFloatBinaryOperatorX.safe(null);
        assertThat(result).isSameAs(LFloatBinaryOperatorX.lX(LFloatBinaryOperatorX.safe()));
    }

    @Test <Y extends Throwable> void safeSupplierPropagates() {
        LSupplierX<LFloatBinaryOperatorX<X>,Y> supplier = ()->sut;
        Object result = LFloatBinaryOperatorX.safeSupplier(supplier);
        assertThat(result).isSameAs(supplier);
    }

    @Test <Y extends Throwable> void safeSupplierProtectsAgainstNpe() {
        Object result = LFloatBinaryOperatorX.safeSupplier(null);
        assertThat(result).isSameAs(LFloatBinaryOperatorX.safeSupplier());
    }

    @Test <Y extends Throwable> void safeSupplierCompiles() {
        LSupplierX<LFloatBinaryOperatorX<X>,Y> r1 = LFloatBinaryOperatorX.safeSupplier(()->sut);  //NOSONAR
    }

}
