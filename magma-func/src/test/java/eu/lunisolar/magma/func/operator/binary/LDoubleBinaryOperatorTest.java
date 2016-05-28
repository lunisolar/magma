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
public class LDoubleBinaryOperatorTest<X extends ParseException> {
    private static final String ORIGINAL_MESSAGE = "Original message";
    private static final String EXCEPTION_WAS_WRAPPED = "Exception was wrapped.";
    private static final String NO_EXCEPTION_WERE_THROWN = "No exception were thrown.";

    private double testValue = 100d;



    private LDoubleBinaryOperator sut = new LDoubleBinaryOperator(){
        public  double doApplyAsDouble(double a1,double a2)  {
            return testValue;
        }
    };

    private LDoubleBinaryOperatorX<X> opposite = new LDoubleBinaryOperatorX<X>(){
        public  double doApplyAsDouble(double a1,double a2)  throws X {
            return testValue;
        }
    };


    private DoubleBinaryOperator jre = (a1,a2) -> testValue;



    private LDoubleBinaryOperatorX<RuntimeException> sutAlwaysThrowingUnchecked = LDoubleBinaryOperator.l((a1,a2) -> {
            throw new IndexOutOfBoundsException(ORIGINAL_MESSAGE);
    });


    @Test
    public void testTheResult() throws X {
        assertThat(sut.doApplyAsDouble(100d,100d))
            .isEqualTo(testValue);
    }

    @Test
    public void testTupleCall() throws X {

        LDoublePair domainObject = Tuple4U.doublePair(100d,100d);

        Object result = sut.tupleApplyAsDouble(domainObject);

        assertThat(result)
            .isEqualTo(testValue);
    }

    @Test
    public void testNonNullDoApplyAsDouble() throws X {
        assertThat(sut.nonNullDoApplyAsDouble(100d,100d))
            .isEqualTo(testValue);
    }

    @Test
    public void testNestingDoApplyAsDoubleUnchecked() throws X {

        // then
        try {
            sutAlwaysThrowingUnchecked.nestingDoApplyAsDouble(100d,100d);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                    .hasNoCause()
                    .hasMessage(ORIGINAL_MESSAGE);
        }
    }

    @Test
    public void testShovingDoApplyAsDoubleUnchecked() throws X {

        // then
        try {
            sutAlwaysThrowingUnchecked.shovingDoApplyAsDouble(100d,100d);
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
            .isEqualTo("LDoubleBinaryOperator: double doApplyAsDouble(double a1,double a2)");
    }

    @Test
    public void testLMethod() throws X {
        assertThat(LDoubleBinaryOperator.l((a1,a2) -> testValue ))
            .isInstanceOf(LDoubleBinaryOperator.class);
    }

    @Test
    public void testWrapMethod() throws X {
        assertThat(LDoubleBinaryOperator.wrap(opposite))
            .isInstanceOf(LDoubleBinaryOperator.class);
    }

    @Test
    public void testWrapStdMethod() throws X {
        assertThat(LDoubleBinaryOperator.wrap(jre))
            .isInstanceOf(LDoubleBinaryOperator.class);
    }

    @Test
    public void testWrapMethodDoNotWrapsRuntimeException() throws X {
        // given
        LDoubleBinaryOperatorX<X> sutThrowing = LDoubleBinaryOperatorX.lX((a1,a2) -> {
            throw new UnsupportedOperationException(ORIGINAL_MESSAGE);
        });

        // when
        LDoubleBinaryOperator wrapped = LDoubleBinaryOperator.wrap(sutThrowing);

        // then
        try {
            wrapped.doApplyAsDouble(100d,100d);
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
        LDoubleBinaryOperatorX<ParseException> sutThrowing = LDoubleBinaryOperatorX.lX((a1,a2) -> {
            throw new ParseException(ORIGINAL_MESSAGE, 0);
        });

        // when
        LDoubleBinaryOperator wrapped = LDoubleBinaryOperator.wrap(sutThrowing);

        // then
        try {
            wrapped.doApplyAsDouble(100d,100d);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(NestedException.class)
                    .hasCauseExactlyInstanceOf(ParseException.class)
                    .hasMessage(ORIGINAL_MESSAGE);
        }
    }


    @Test
    public void testHandlingDoApplyAsDoubleMethodWrapsTheException() throws X {

        // given
        LDoubleBinaryOperator sutThrowing = LDoubleBinaryOperator.l((a1,a2) -> {
            throw new UnsupportedOperationException();
        });

        // when
        LDoubleBinaryOperator wrapped = sutThrowing.handleDoubleBinaryOp(handler -> handler
            .wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED));

        // then
        try {
            wrapped.doApplyAsDouble(100d,100d);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IllegalArgumentException.class)
                    .hasCauseExactlyInstanceOf(UnsupportedOperationException.class)
                    .hasMessage(EXCEPTION_WAS_WRAPPED);
        }
    }

    @Test
    public void testHandleDoubleBinaryOpMethodDoNotWrapsOtherExceptionIf() throws X {

        // given
        LDoubleBinaryOperator sutThrowing = LDoubleBinaryOperator.l((a1,a2) -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LDoubleBinaryOperator wrapped = sutThrowing.handleDoubleBinaryOp(handler -> handler
                .wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED)
                .throwIf(IndexOutOfBoundsException.class));

        // then
        try {
            wrapped.doApplyAsDouble(100d,100d);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                    .hasNoCause();
        }
    }

@Test
    public void testHandleDoubleBinaryOpMethodDoNotWrapsOtherExceptionWhen() throws X {

        // given
        LDoubleBinaryOperator sutThrowing = LDoubleBinaryOperator.l((a1,a2) -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LDoubleBinaryOperator wrapped = sutThrowing.handleDoubleBinaryOp(handler -> handler
                .wrapWhen(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED)
                .throwIf(IndexOutOfBoundsException.class));

        // then
        try {
            wrapped.doApplyAsDouble(100d,100d);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                    .hasNoCause();
        }
    }


    @Test
    public void testHandleDoubleBinaryOpMishandlingExceptionIsAllowed() throws X {

        // given
        LDoubleBinaryOperator sutThrowing = LDoubleBinaryOperator.l((a1,a2) -> {
            throw new UnsupportedOperationException(ORIGINAL_MESSAGE);
        });

        // when
        LDoubleBinaryOperator wrapped = sutThrowing.handleDoubleBinaryOp(h -> Function4U.doNothing());

        // then
        try {
            wrapped.doApplyAsDouble(100d,100d);
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
        LDoubleBinaryOperator min =  LDoubleBinaryOperator.minBy(Double::compare);

        //then
        assertThat(min.doApplyAsDouble(0d, 56d))
                .isEqualTo(0d);
        assertThat(min.doApplyAsDouble(56d, 0d))
                       .isEqualTo(0d);

    }

    @Test
    public void maxBy() throws X  {
        //when
        LDoubleBinaryOperator max =  LDoubleBinaryOperator.maxBy(Double::compare);

        //then
        assertThat(max.doApplyAsDouble(0d, 56d))
                .isEqualTo(56d);
        assertThat(max.doApplyAsDouble(56d, 0d))
                        .isEqualTo(56d);
    }


    @Test
    public void testMin() throws X {
        //given
        double valueSmall = 10d;
        double valueBig = 100d;

        //when
        LDoubleBinaryOperator min = LDoubleBinaryOperator.min();

        //then
        assertThat(min.doApplyAsDouble(valueSmall, valueBig))
                .isEqualTo(valueSmall);

        assertThat(min.doApplyAsDouble(valueBig, valueSmall))
                .isEqualTo(valueSmall);
    }

    @Test
    public void testMax() throws X {
        //given
        double valueSmall = 10d;
        double valueBig = 100d;

        //when
        LDoubleBinaryOperator max = LDoubleBinaryOperator.max();

        //then
        assertThat(max.doApplyAsDouble(valueSmall, valueBig))
                .isEqualTo(valueBig);

        assertThat(max.doApplyAsDouble(valueBig, valueSmall))
                .isEqualTo(valueBig);
    }


    // <editor-fold desc="compose (functional)">

    @Test
    public void testDoubleBinaryOpComposeDouble() throws X {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LDoubleBinaryOperator sutO = (a1,a2) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(90d);
                assertThat(a2).isEqualTo(91d);
                return 100d;
        };

        LDoubleUnaryOperator before1 = p0 -> {
            assertThat(p0).isEqualTo(80d);
            beforeCalls.incrementAndGet();
            return 90d;
        };
        LDoubleUnaryOperator before2 = p1 -> {
            assertThat(p1).isEqualTo(81d);
            beforeCalls.incrementAndGet();
            return 91d;
        };

        //when
        LDoubleBinaryOperator function = sutO.doubleBinaryOpComposeDouble(before1,before2);
        function.doApplyAsDouble(80d,81d);

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(beforeCalls.get()).isEqualTo(2);
    }


    @Test
    public void testDoubleBinaryOpCompose() throws X {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LDoubleBinaryOperator sutO = (a1,a2) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(90d);
                assertThat(a2).isEqualTo(91d);
                return 100d;
        };

        LToDoubleFunction<Integer> before1 = p0 -> {
            assertThat(p0).isEqualTo(80);
            beforeCalls.incrementAndGet();
            return 90d;
        };
        LToDoubleFunction<Integer> before2 = p1 -> {
            assertThat(p1).isEqualTo(81);
            beforeCalls.incrementAndGet();
            return 91d;
        };

        //when
        LToDoubleBiFunction<Integer,Integer> function = sutO.doubleBinaryOpCompose(before1,before2);
        function.doApplyAsDouble(80,81);

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
        LDoubleBinaryOperator sutO = (a1,a2) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(80d);
                assertThat(a2).isEqualTo(81d);
                return 90d;
        };

        LDoubleFunction<Integer> thenFunction = p -> {
                thenFunctionCalled.set(true);
                // double
                assertThat(p).isEqualTo(90d);
                // Integer
                return 100;
        };

        //when
        LBiDoubleFunction<Integer> function = sutO.then(thenFunction);
        Integer finalValue = function.doApply(80d,81d);

        //then - finals
        assertThat(finalValue).isEqualTo(100);
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    // </editor-fold>

    @Test
    public void testNesting() {
        assertThat(sut.nestingDoubleBinaryOp())
            .isSameAs(sut)
            .isInstanceOf(LDoubleBinaryOperator.class);
    }

    @Test
    public void testShoving() {
        assertThat(sut.shovingDoubleBinaryOp())
            .isSameAs(sut)
            .isInstanceOf(LDoubleBinaryOperator.class);
    }

    @Test
    public void testNestingX() {
        assertThat(sut.nestingDoubleBinaryOpX())
            .isSameAs(sut)
            .isInstanceOf(LDoubleBinaryOperatorX.class);
    }

    @Test
    public void testShovingX() {
        assertThat(sut.shovingDoubleBinaryOpX())
            .isSameAs(sut)
            .isInstanceOf(LDoubleBinaryOperatorX.class);
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void testShove() {

        // given
        LDoubleBinaryOperator sutThrowing = LDoubleBinaryOperator.l((a1,a2) -> {
            throw new UnsupportedOperationException();
        });

        // when
        sutThrowing.shovingDoubleBinaryOp().doApplyAsDouble(100d,100d);
    }

    @Test
    public void testHandleDoubleBinaryOp() throws X {

        // given
        LDoubleBinaryOperator sutThrowing = LDoubleBinaryOperator.l((a1,a2) -> {
            throw new UnsupportedOperationException();
        });

        // when
        LDoubleBinaryOperator wrapped = sutThrowing.handleDoubleBinaryOp(h -> {
            h.wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED);
        });

        // then
        try {
            wrapped.doApplyAsDouble(100d,100d);
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
                .contains("LDoubleBinaryOperator: double doApplyAsDouble(double a1,double a2)");
    }


    @Test
    public void isThrowing() {
        assertThat(sut.isThrowing())
            .isFalse();
    }

    @Test void safeCompiles() {
        LDoubleBinaryOperator r1 = LDoubleBinaryOperator.safe(sut); //NOSONAR
        LDoubleBinaryOperatorX r2 = LDoubleBinaryOperator.safe(sut); //NOSONAR
        DoubleBinaryOperator r3 = LDoubleBinaryOperator.safe(sut); //NOSONAR
    }

    @Test void safePropagates() {
        Object result = LDoubleBinaryOperator.safe(sut);
        assertThat(result).isSameAs(sut);
    }

    @Test void safeProtectsAgainstNpe() {
        Object result = LDoubleBinaryOperator.safe(null);
        assertThat(result).isSameAs(LDoubleBinaryOperator.l(LDoubleBinaryOperator.safe()));
    }

    @Test  void safeSupplierPropagates() {
        LSupplier<LDoubleBinaryOperator> supplier = ()->sut;
        Object result = LDoubleBinaryOperator.safeSupplier(supplier);
        assertThat(result).isSameAs(supplier);
    }

    @Test  void safeSupplierProtectsAgainstNpe() {
        Object result = LDoubleBinaryOperator.safeSupplier(null);
        assertThat(result).isSameAs(LDoubleBinaryOperator.safeSupplier());
    }

    @Test  void safeSupplierCompiles() {
        LSupplier<LDoubleBinaryOperator> r1 = LDoubleBinaryOperator.safeSupplier(()->sut);  //NOSONAR
        Supplier<LDoubleBinaryOperator> r2 = LDoubleBinaryOperator.safeSupplier(()->sut); //NOSONAR
    }

}
