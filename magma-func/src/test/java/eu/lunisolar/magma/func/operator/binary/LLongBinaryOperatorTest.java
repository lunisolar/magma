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
public class LLongBinaryOperatorTest<X extends ParseException> {
    private static final String ORIGINAL_MESSAGE = "Original message";
    private static final String EXCEPTION_WAS_WRAPPED = "Exception was wrapped.";
    private static final String NO_EXCEPTION_WERE_THROWN = "No exception were thrown.";

    private long testValue = 100L;



    private LLongBinaryOperator sut = new LLongBinaryOperator(){
        public  long doApplyAsLong(long a1,long a2)  {
            return testValue;
        }
    };

    private LLongBinaryOperatorX<X> opposite = new LLongBinaryOperatorX<X>(){
        public  long doApplyAsLong(long a1,long a2)  throws X {
            return testValue;
        }
    };


    private LongBinaryOperator jre = (a1,a2) -> testValue;



    private LLongBinaryOperatorX<RuntimeException> sutAlwaysThrowingUnchecked = LLongBinaryOperator.l((a1,a2) -> {
            throw new IndexOutOfBoundsException(ORIGINAL_MESSAGE);
    });


    @Test
    public void testTheResult() throws X {
        assertThat(sut.doApplyAsLong(100L,100L))
            .isEqualTo(testValue);
    }

    @Test
    public void testTupleCall() throws X {

        LLongPair domainObject = Tuple4U.longPair(100L,100L);

        Object result = sut.tupleApplyAsLong(domainObject);

        assertThat(result)
            .isEqualTo(testValue);
    }

    @Test
    public void testNonNullDoApplyAsLong() throws X {
        assertThat(sut.nonNullDoApplyAsLong(100L,100L))
            .isEqualTo(testValue);
    }

    @Test
    public void testNestingDoApplyAsLongUnchecked() throws X {

        // then
        try {
            sutAlwaysThrowingUnchecked.nestingDoApplyAsLong(100L,100L);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                    .hasNoCause()
                    .hasMessage(ORIGINAL_MESSAGE);
        }
    }

    @Test
    public void testShovingDoApplyAsLongUnchecked() throws X {

        // then
        try {
            sutAlwaysThrowingUnchecked.shovingDoApplyAsLong(100L,100L);
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
            .isEqualTo("LLongBinaryOperator: long doApplyAsLong(long a1,long a2)");
    }

    @Test
    public void testLMethod() throws X {
        assertThat(LLongBinaryOperator.l((a1,a2) -> testValue ))
            .isInstanceOf(LLongBinaryOperator.class);
    }

    @Test
    public void testWrapMethod() throws X {
        assertThat(LLongBinaryOperator.wrap(opposite))
            .isInstanceOf(LLongBinaryOperator.class);
    }

    @Test
    public void testWrapStdMethod() throws X {
        assertThat(LLongBinaryOperator.wrap(jre))
            .isInstanceOf(LLongBinaryOperator.class);
    }

    @Test
    public void testWrapMethodDoNotWrapsRuntimeException() throws X {
        // given
        LLongBinaryOperatorX<X> sutThrowing = LLongBinaryOperatorX.lX((a1,a2) -> {
            throw new UnsupportedOperationException(ORIGINAL_MESSAGE);
        });

        // when
        LLongBinaryOperator wrapped = LLongBinaryOperator.wrap(sutThrowing);

        // then
        try {
            wrapped.doApplyAsLong(100L,100L);
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
        LLongBinaryOperatorX<ParseException> sutThrowing = LLongBinaryOperatorX.lX((a1,a2) -> {
            throw new ParseException(ORIGINAL_MESSAGE, 0);
        });

        // when
        LLongBinaryOperator wrapped = LLongBinaryOperator.wrap(sutThrowing);

        // then
        try {
            wrapped.doApplyAsLong(100L,100L);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(NestedException.class)
                    .hasCauseExactlyInstanceOf(ParseException.class)
                    .hasMessage(ORIGINAL_MESSAGE);
        }
    }


    @Test
    public void testHandlingDoApplyAsLongMethodWrapsTheException() throws X {

        // given
        LLongBinaryOperator sutThrowing = LLongBinaryOperator.l((a1,a2) -> {
            throw new UnsupportedOperationException();
        });

        // when
        LLongBinaryOperator wrapped = sutThrowing.handleLongBinaryOp(handler -> handler
            .wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED));

        // then
        try {
            wrapped.doApplyAsLong(100L,100L);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IllegalArgumentException.class)
                    .hasCauseExactlyInstanceOf(UnsupportedOperationException.class)
                    .hasMessage(EXCEPTION_WAS_WRAPPED);
        }
    }

    @Test
    public void testHandleLongBinaryOpMethodDoNotWrapsOtherExceptionIf() throws X {

        // given
        LLongBinaryOperator sutThrowing = LLongBinaryOperator.l((a1,a2) -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LLongBinaryOperator wrapped = sutThrowing.handleLongBinaryOp(handler -> handler
                .wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED)
                .throwIf(IndexOutOfBoundsException.class));

        // then
        try {
            wrapped.doApplyAsLong(100L,100L);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                    .hasNoCause();
        }
    }

@Test
    public void testHandleLongBinaryOpMethodDoNotWrapsOtherExceptionWhen() throws X {

        // given
        LLongBinaryOperator sutThrowing = LLongBinaryOperator.l((a1,a2) -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LLongBinaryOperator wrapped = sutThrowing.handleLongBinaryOp(handler -> handler
                .wrapWhen(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED)
                .throwIf(IndexOutOfBoundsException.class));

        // then
        try {
            wrapped.doApplyAsLong(100L,100L);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                    .hasNoCause();
        }
    }


    @Test
    public void testHandleLongBinaryOpMishandlingExceptionIsAllowed() throws X {

        // given
        LLongBinaryOperator sutThrowing = LLongBinaryOperator.l((a1,a2) -> {
            throw new UnsupportedOperationException(ORIGINAL_MESSAGE);
        });

        // when
        LLongBinaryOperator wrapped = sutThrowing.handleLongBinaryOp(h -> Function4U.doNothing());

        // then
        try {
            wrapped.doApplyAsLong(100L,100L);
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
        LLongBinaryOperator min =  LLongBinaryOperator.minBy(Long::compare);

        //then
        assertThat(min.doApplyAsLong(0L, 56L))
                .isEqualTo(0L);
        assertThat(min.doApplyAsLong(56L, 0L))
                       .isEqualTo(0L);

    }

    @Test
    public void maxBy() throws X  {
        //when
        LLongBinaryOperator max =  LLongBinaryOperator.maxBy(Long::compare);

        //then
        assertThat(max.doApplyAsLong(0L, 56L))
                .isEqualTo(56L);
        assertThat(max.doApplyAsLong(56L, 0L))
                        .isEqualTo(56L);
    }


    @Test
    public void testMin() throws X {
        //given
        long valueSmall = 10L;
        long valueBig = 100L;

        //when
        LLongBinaryOperator min = LLongBinaryOperator.min();

        //then
        assertThat(min.doApplyAsLong(valueSmall, valueBig))
                .isEqualTo(valueSmall);

        assertThat(min.doApplyAsLong(valueBig, valueSmall))
                .isEqualTo(valueSmall);
    }

    @Test
    public void testMax() throws X {
        //given
        long valueSmall = 10L;
        long valueBig = 100L;

        //when
        LLongBinaryOperator max = LLongBinaryOperator.max();

        //then
        assertThat(max.doApplyAsLong(valueSmall, valueBig))
                .isEqualTo(valueBig);

        assertThat(max.doApplyAsLong(valueBig, valueSmall))
                .isEqualTo(valueBig);
    }


    // <editor-fold desc="compose (functional)">

    @Test
    public void testLongBinaryOpComposeLong() throws X {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LLongBinaryOperator sutO = (long a1,long a2) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(90L);
                assertThat(a2).isEqualTo(91L);
                return 100L;
        };

        LLongUnaryOperator before1 = p0 -> {
            assertThat(p0).isEqualTo(80L);
            beforeCalls.incrementAndGet();
            return 90L;
        };
        LLongUnaryOperator before2 = p1 -> {
            assertThat(p1).isEqualTo(81L);
            beforeCalls.incrementAndGet();
            return 91L;
        };

        //when
        LLongBinaryOperator function = sutO.longBinaryOpComposeLong(before1,before2);
        function.doApplyAsLong(80L,81L);

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(beforeCalls.get()).isEqualTo(2);
    }


    @Test
    public void testLongBinaryOpCompose() throws X {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LLongBinaryOperator sutO = (long a1,long a2) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(90L);
                assertThat(a2).isEqualTo(91L);
                return 100L;
        };

        LToLongFunction<Integer> before1 = p0 -> {
            assertThat(p0).isEqualTo(80);
            beforeCalls.incrementAndGet();
            return 90L;
        };
        LToLongFunction<Integer> before2 = p1 -> {
            assertThat(p1).isEqualTo(81);
            beforeCalls.incrementAndGet();
            return 91L;
        };

        //when
        LToLongBiFunction<Integer,Integer> function = sutO.longBinaryOpCompose(before1,before2);
        function.doApplyAsLong(80,81);

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
        LLongBinaryOperator sutO = (a1,a2) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(80L);
                assertThat(a2).isEqualTo(81L);
                return 90L;
        };

        LLongFunction<Integer> thenFunction = p -> {
                thenFunctionCalled.set(true);
                // long
                assertThat(p).isEqualTo(90L);
                // Integer
                return 100;
        };

        //when
        LBiLongFunction<Integer> function = sutO.then(thenFunction);
        Integer finalValue = function.doApply(80L,81L);

        //then - finals
        assertThat(finalValue).isEqualTo(100);
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    // </editor-fold>

    @Test
    public void testNesting() {
        assertThat(sut.nestingLongBinaryOp())
            .isSameAs(sut)
            .isInstanceOf(LLongBinaryOperator.class);
    }

    @Test
    public void testShoving() {
        assertThat(sut.shovingLongBinaryOp())
            .isSameAs(sut)
            .isInstanceOf(LLongBinaryOperator.class);
    }

    @Test
    public void testNestingX() {
        assertThat(sut.nestingLongBinaryOpX())
            .isSameAs(sut)
            .isInstanceOf(LLongBinaryOperatorX.class);
    }

    @Test
    public void testShovingX() {
        assertThat(sut.shovingLongBinaryOpX())
            .isSameAs(sut)
            .isInstanceOf(LLongBinaryOperatorX.class);
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void testShove() {

        // given
        LLongBinaryOperator sutThrowing = LLongBinaryOperator.l((a1,a2) -> {
            throw new UnsupportedOperationException();
        });

        // when
        sutThrowing.shovingLongBinaryOp().doApplyAsLong(100L,100L);
    }

    @Test
    public void testHandleLongBinaryOp() throws X {

        // given
        LLongBinaryOperator sutThrowing = LLongBinaryOperator.l((a1,a2) -> {
            throw new UnsupportedOperationException();
        });

        // when
        LLongBinaryOperator wrapped = sutThrowing.handleLongBinaryOp(h -> {
            h.wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED);
        });

        // then
        try {
            wrapped.doApplyAsLong(100L,100L);
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
                .contains("LLongBinaryOperator: long doApplyAsLong(long a1,long a2)");
    }


    @Test
    public void isThrowing() {
        assertThat(sut.isThrowing())
            .isFalse();
    }

    @Test void safeCompiles() {
        LLongBinaryOperator r1 = LLongBinaryOperator.safe(sut); //NOSONAR
        LLongBinaryOperatorX r2 = LLongBinaryOperator.safe(sut); //NOSONAR
        LongBinaryOperator r3 = LLongBinaryOperator.safe(sut); //NOSONAR
    }

    @Test void safePropagates() {
        Object result = LLongBinaryOperator.safe(sut);
        assertThat(result).isSameAs(sut);
    }

    @Test void safeProtectsAgainstNpe() {
        Object result = LLongBinaryOperator.safe(null);
        assertThat(result).isSameAs(LLongBinaryOperator.l(LLongBinaryOperator.safe()));
    }

    @Test  void safeSupplierPropagates() {
        LSupplier<LLongBinaryOperator> supplier = ()->sut;
        Object result = LLongBinaryOperator.safeSupplier(supplier);
        assertThat(result).isSameAs(supplier);
    }

    @Test  void safeSupplierProtectsAgainstNpe() {
        Object result = LLongBinaryOperator.safeSupplier(null);
        assertThat(result).isSameAs(LLongBinaryOperator.safeSupplier());
    }

    @Test  void safeSupplierCompiles() {
        LSupplier<LLongBinaryOperator> r1 = LLongBinaryOperator.safeSupplier(()->sut);  //NOSONAR
        Supplier<LLongBinaryOperator> r2 = LLongBinaryOperator.safeSupplier(()->sut); //NOSONAR
    }

}
