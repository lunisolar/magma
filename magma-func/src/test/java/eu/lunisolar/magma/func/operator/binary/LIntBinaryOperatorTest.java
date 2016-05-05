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
public class LIntBinaryOperatorTest<X extends ParseException> {
    private static final String ORIGINAL_MESSAGE = "Original message";
    private static final String EXCEPTION_WAS_WRAPPED = "Exception was wrapped.";
    private static final String NO_EXCEPTION_WERE_THROWN = "No exception were thrown.";

    private int testValue = 100;



    private LIntBinaryOperator sut = new LIntBinaryOperator(){
        public  int doApplyAsInt(int a1,int a2)  {
            return testValue;
        }
    };

    private LIntBinaryOperatorX<X> opposite = new LIntBinaryOperatorX<X>(){
        public  int doApplyAsInt(int a1,int a2)  throws X {
            return testValue;
        }
    };


    private IntBinaryOperator jre = (a1,a2) -> testValue;



    private LIntBinaryOperatorX<RuntimeException> sutAlwaysThrowingUnchecked = LIntBinaryOperator.l((a1,a2) -> {
            throw new IndexOutOfBoundsException(ORIGINAL_MESSAGE);
    });


    @Test
    public void testTheResult() throws X {
        assertThat(sut.doApplyAsInt(100,100))
            .isEqualTo(testValue);
    }

    @Test
    public void testTupleCall() throws X {

        LIntPair domainObject = Tuple4U.intPair(100,100);

        Object result = sut.tupleApplyAsInt(domainObject);

        assertThat(result)
            .isEqualTo(testValue);
    }

    @Test
    public void testNonNullDoApplyAsInt() throws X {
        assertThat(sut.nonNullDoApplyAsInt(100,100))
            .isEqualTo(testValue);
    }

    @Test
    public void testNestingDoApplyAsIntUnchecked() throws X {

        // then
        try {
            sutAlwaysThrowingUnchecked.nestingDoApplyAsInt(100,100);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                    .hasNoCause()
                    .hasMessage(ORIGINAL_MESSAGE);
        }
    }

    @Test
    public void testShovingDoApplyAsIntUnchecked() throws X {

        // then
        try {
            sutAlwaysThrowingUnchecked.shovingDoApplyAsInt(100,100);
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
            .isEqualTo("LIntBinaryOperator: int doApplyAsInt(int a1,int a2)");
    }

    @Test
    public void testLMethod() throws X {
        assertThat(LIntBinaryOperator.l((a1,a2) -> testValue ))
            .isInstanceOf(LIntBinaryOperator.class);
    }

    @Test
    public void testWrapMethod() throws X {
        assertThat(LIntBinaryOperator.wrap(opposite))
            .isInstanceOf(LIntBinaryOperator.class);
    }

    @Test
    public void testWrapStdMethod() throws X {
        assertThat(LIntBinaryOperator.wrap(jre))
            .isInstanceOf(LIntBinaryOperator.class);
    }

    @Test
    public void testWrapMethodDoNotWrapsRuntimeException() throws X {
        // given
        LIntBinaryOperatorX<X> sutThrowing = LIntBinaryOperatorX.lX((a1,a2) -> {
            throw new UnsupportedOperationException(ORIGINAL_MESSAGE);
        });

        // when
        LIntBinaryOperator wrapped = LIntBinaryOperator.wrap(sutThrowing);

        // then
        try {
            wrapped.doApplyAsInt(100,100);
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
        LIntBinaryOperatorX<ParseException> sutThrowing = LIntBinaryOperatorX.lX((a1,a2) -> {
            throw new ParseException(ORIGINAL_MESSAGE, 0);
        });

        // when
        LIntBinaryOperator wrapped = LIntBinaryOperator.wrap(sutThrowing);

        // then
        try {
            wrapped.doApplyAsInt(100,100);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(NestedException.class)
                    .hasCauseExactlyInstanceOf(ParseException.class)
                    .hasMessage(ORIGINAL_MESSAGE);
        }
    }


    @Test
    public void testHandlingDoApplyAsIntMethodWrapsTheException() throws X {

        // given
        LIntBinaryOperator sutThrowing = LIntBinaryOperator.l((a1,a2) -> {
            throw new UnsupportedOperationException();
        });

        // when
        LIntBinaryOperator wrapped = sutThrowing.handleIntBinaryOp(handler -> handler
            .wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED));

        // then
        try {
            wrapped.doApplyAsInt(100,100);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IllegalArgumentException.class)
                    .hasCauseExactlyInstanceOf(UnsupportedOperationException.class)
                    .hasMessage(EXCEPTION_WAS_WRAPPED);
        }
    }

    @Test
    public void testHandleIntBinaryOpMethodDoNotWrapsOtherExceptionIf() throws X {

        // given
        LIntBinaryOperator sutThrowing = LIntBinaryOperator.l((a1,a2) -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LIntBinaryOperator wrapped = sutThrowing.handleIntBinaryOp(handler -> handler
                .wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED)
                .throwIf(IndexOutOfBoundsException.class));

        // then
        try {
            wrapped.doApplyAsInt(100,100);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                    .hasNoCause();
        }
    }

@Test
    public void testHandleIntBinaryOpMethodDoNotWrapsOtherExceptionWhen() throws X {

        // given
        LIntBinaryOperator sutThrowing = LIntBinaryOperator.l((a1,a2) -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LIntBinaryOperator wrapped = sutThrowing.handleIntBinaryOp(handler -> handler
                .wrapWhen(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED)
                .throwIf(IndexOutOfBoundsException.class));

        // then
        try {
            wrapped.doApplyAsInt(100,100);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                    .hasNoCause();
        }
    }


    @Test
    public void testHandleIntBinaryOpMishandlingExceptionIsAllowed() throws X {

        // given
        LIntBinaryOperator sutThrowing = LIntBinaryOperator.l((a1,a2) -> {
            throw new UnsupportedOperationException(ORIGINAL_MESSAGE);
        });

        // when
        LIntBinaryOperator wrapped = sutThrowing.handleIntBinaryOp(h -> Function4U.doNothing());

        // then
        try {
            wrapped.doApplyAsInt(100,100);
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
        LIntBinaryOperator min =  LIntBinaryOperator.minBy(Integer::compare);

        //then
        assertThat(min.doApplyAsInt(0, 56))
                .isEqualTo(0);
        assertThat(min.doApplyAsInt(56, 0))
                       .isEqualTo(0);

    }

    @Test
    public void maxBy() throws X  {
        //when
        LIntBinaryOperator max =  LIntBinaryOperator.maxBy(Integer::compare);

        //then
        assertThat(max.doApplyAsInt(0, 56))
                .isEqualTo(56);
        assertThat(max.doApplyAsInt(56, 0))
                        .isEqualTo(56);
    }


    @Test
    public void testMin() throws X {
        //given
        int valueSmall = 10;
        int valueBig = 100;

        //when
        LIntBinaryOperator min = LIntBinaryOperator.min();

        //then
        assertThat(min.doApplyAsInt(valueSmall, valueBig))
                .isEqualTo(valueSmall);

        assertThat(min.doApplyAsInt(valueBig, valueSmall))
                .isEqualTo(valueSmall);
    }

    @Test
    public void testMax() throws X {
        //given
        int valueSmall = 10;
        int valueBig = 100;

        //when
        LIntBinaryOperator max = LIntBinaryOperator.max();

        //then
        assertThat(max.doApplyAsInt(valueSmall, valueBig))
                .isEqualTo(valueBig);

        assertThat(max.doApplyAsInt(valueBig, valueSmall))
                .isEqualTo(valueBig);
    }


    // <editor-fold desc="compose (functional)">

    @Test
    public void testIntBinaryOpComposeInt() throws X {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LIntBinaryOperator sutO = (int a1,int a2) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(90);
                assertThat(a2).isEqualTo(91);
                return 100;
        };

        LIntUnaryOperator before1 = p0 -> {
            assertThat(p0).isEqualTo(80);
            beforeCalls.incrementAndGet();
            return 90;
        };
        LIntUnaryOperator before2 = p1 -> {
            assertThat(p1).isEqualTo(81);
            beforeCalls.incrementAndGet();
            return 91;
        };

        //when
        LIntBinaryOperator function = sutO.intBinaryOpComposeInt(before1,before2);
        function.doApplyAsInt(80,81);

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(beforeCalls.get()).isEqualTo(2);
    }


    @Test
    public void testIntBinaryOpCompose() throws X {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LIntBinaryOperator sutO = (int a1,int a2) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(90);
                assertThat(a2).isEqualTo(91);
                return 100;
        };

        LToIntFunction<Integer> before1 = p0 -> {
            assertThat(p0).isEqualTo(80);
            beforeCalls.incrementAndGet();
            return 90;
        };
        LToIntFunction<Integer> before2 = p1 -> {
            assertThat(p1).isEqualTo(81);
            beforeCalls.incrementAndGet();
            return 91;
        };

        //when
        LToIntBiFunction<Integer,Integer> function = sutO.intBinaryOpCompose(before1,before2);
        function.doApplyAsInt(80,81);

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
        LIntBinaryOperator sutO = (a1,a2) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(80);
                assertThat(a2).isEqualTo(81);
                return 90;
        };

        LIntFunction<Integer> thenFunction = p -> {
                thenFunctionCalled.set(true);
                // int
                assertThat(p).isEqualTo(90);
                // Integer
                return 100;
        };

        //when
        LBiIntFunction<Integer> function = sutO.then(thenFunction);
        Integer finalValue = function.doApply(80,81);

        //then - finals
        assertThat(finalValue).isEqualTo(100);
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    // </editor-fold>

    @Test
    public void testNesting() {
        assertThat(sut.nestingIntBinaryOp())
            .isSameAs(sut)
            .isInstanceOf(LIntBinaryOperator.class);
    }

    @Test
    public void testShoving() {
        assertThat(sut.shovingIntBinaryOp())
            .isSameAs(sut)
            .isInstanceOf(LIntBinaryOperator.class);
    }

    @Test
    public void testNestingX() {
        assertThat(sut.nestingIntBinaryOpX())
            .isSameAs(sut)
            .isInstanceOf(LIntBinaryOperatorX.class);
    }

    @Test
    public void testShovingX() {
        assertThat(sut.shovingIntBinaryOpX())
            .isSameAs(sut)
            .isInstanceOf(LIntBinaryOperatorX.class);
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void testShove() {

        // given
        LIntBinaryOperator sutThrowing = LIntBinaryOperator.l((a1,a2) -> {
            throw new UnsupportedOperationException();
        });

        // when
        sutThrowing.shovingIntBinaryOp().doApplyAsInt(100,100);
    }

    @Test
    public void testHandleIntBinaryOp() throws X {

        // given
        LIntBinaryOperator sutThrowing = LIntBinaryOperator.l((a1,a2) -> {
            throw new UnsupportedOperationException();
        });

        // when
        LIntBinaryOperator wrapped = sutThrowing.handleIntBinaryOp(h -> {
            h.wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED);
        });

        // then
        try {
            wrapped.doApplyAsInt(100,100);
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
                .contains("LIntBinaryOperator: int doApplyAsInt(int a1,int a2)");
    }


    @Test
    public void isThrowing() {
        assertThat(sut.isThrowing())
            .isFalse();
    }

    @Test void safeCompiles() {
        LIntBinaryOperator r1 = LIntBinaryOperator.safe(sut); //NOSONAR
        LIntBinaryOperatorX r2 = LIntBinaryOperator.safe(sut); //NOSONAR
        IntBinaryOperator r3 = LIntBinaryOperator.safe(sut); //NOSONAR
    }

    @Test void safePropagates() {
        Object result = LIntBinaryOperator.safe(sut);
        assertThat(result).isSameAs(sut);
    }

    @Test void safeProtectsAgainstNpe() {
        Object result = LIntBinaryOperator.safe(null);
        assertThat(result).isSameAs(LIntBinaryOperator.l(LIntBinaryOperator.safe()));
    }

    @Test  void safeSupplierPropagates() {
        LSupplier<LIntBinaryOperator> supplier = ()->sut;
        Object result = LIntBinaryOperator.safeSupplier(supplier);
        assertThat(result).isSameAs(supplier);
    }

    @Test  void safeSupplierProtectsAgainstNpe() {
        Object result = LIntBinaryOperator.safeSupplier(null);
        assertThat(result).isSameAs(LIntBinaryOperator.safeSupplier());
    }

    @Test  void safeSupplierCompiles() {
        LSupplier<LIntBinaryOperator> r1 = LIntBinaryOperator.safeSupplier(()->sut);  //NOSONAR
        Supplier<LIntBinaryOperator> r2 = LIntBinaryOperator.safeSupplier(()->sut); //NOSONAR
    }

}
