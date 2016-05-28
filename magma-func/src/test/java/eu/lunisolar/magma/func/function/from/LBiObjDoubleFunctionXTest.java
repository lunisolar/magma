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
public class LBiObjDoubleFunctionXTest<T1,T2,R,X extends ParseException> {
    private static final String ORIGINAL_MESSAGE = "Original message";
    private static final String EXCEPTION_WAS_WRAPPED = "Exception was wrapped.";
    private static final String NO_EXCEPTION_WERE_THROWN = "No exception were thrown.";

    private Integer testValue = 100;



    private LBiObjDoubleFunctionX<Integer,Integer,Integer,X> sut = new LBiObjDoubleFunctionX<Integer,Integer,Integer,X>(){
        public @Nullable Integer doApply(Integer a1,Integer a2,double a3)  throws X {
            return testValue;
        }
    };

    private LBiObjDoubleFunction<Integer,Integer,Integer> opposite = new LBiObjDoubleFunction<Integer,Integer,Integer>(){
        public @Nullable Integer doApply(Integer a1,Integer a2,double a3)  {
            return testValue;
        }
    };

    private LBiObjDoubleFunctionX<Integer,Integer,Integer,X> sutNull = new LBiObjDoubleFunctionX<Integer,Integer,Integer,X>(){
        public @Nullable Integer doApply(Integer a1,Integer a2,double a3)  throws X {
            return null;
        }
    };



    private LBiObjDoubleFunctionX<Integer,Integer,Integer,ParseException> sutAlwaysThrowing = LBiObjDoubleFunctionX.lX((a1,a2,a3) -> {
            throw new ParseException(ORIGINAL_MESSAGE, 0);
    });

    private LBiObjDoubleFunctionX<Integer,Integer,Integer,RuntimeException> sutAlwaysThrowingUnchecked = LBiObjDoubleFunctionX.lX((a1,a2,a3) -> {
            throw new IndexOutOfBoundsException(ORIGINAL_MESSAGE);
    });


    @Test
    public void testTheResult() throws X {
        assertThat(sut.doApply(100,100,100d))
            .isEqualTo(testValue);
    }

    @Test
    public void testTupleCall() throws X {

        LBiObjDoubleTriple<Integer,Integer> domainObject = Tuple4U.biObjDoubleTriple(100,100,100d);

        Object result = sut.tupleApply(domainObject);

        assertThat(result)
            .isEqualTo(testValue);
    }

    @Test
    public void testNonNullDoApply() throws X {
        assertThat(sut.nonNullDoApply(100,100,100d))
            .isSameAs(testValue);
    }

    @Test
    public void testNestingDoApplyChecked() throws X {

        // then
        try {
            sutAlwaysThrowing.nestingDoApply(100,100,100d);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(NestedException.class)
                    .hasCauseExactlyInstanceOf(ParseException.class)
                    .hasMessage(ORIGINAL_MESSAGE);
        }
    }

    @Test
    public void testNestingDoApplyUnchecked() throws X {

        // then
        try {
            sutAlwaysThrowingUnchecked.nestingDoApply(100,100,100d);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                    .hasNoCause()
                    .hasMessage(ORIGINAL_MESSAGE);
        }
    }

    @Test
    public void testShovingDoApplyChecked() throws X {

        // then
        try {
            sutAlwaysThrowing.shovingDoApply(100,100,100d);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(ParseException.class)
                    .hasNoCause()
                    .hasMessage(ORIGINAL_MESSAGE);
        }
    }

    @Test
    public void testShovingDoApplyUnchecked() throws X {

        // then
        try {
            sutAlwaysThrowingUnchecked.shovingDoApply(100,100,100d);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                    .hasNoCause()
                    .hasMessage(ORIGINAL_MESSAGE);
        }
    }

    @Test(expectedExceptions=NullPointerException.class, expectedExceptionsMessageRegExp="\\QEvaluated value by nonNullDoApply() method cannot be null (LBiObjDoubleFunctionX: R doApply(T1 a1,T2 a2,double a3) throws X).\\E")
    public void testNonNullCapturesNull() throws X {
        sutNull.nonNullDoApply(100,100,100d);
    }


    @Test
    public void testFunctionalInterfaceDescription() throws X {
        assertThat(sut.functionalInterfaceDescription())
            .isEqualTo("LBiObjDoubleFunctionX: R doApply(T1 a1,T2 a2,double a3) throws X");
    }

    @Test
    public void testLXMethod() throws X {
        assertThat(LBiObjDoubleFunctionX.lX((a1,a2,a3) -> testValue ))
            .isInstanceOf(LBiObjDoubleFunctionX.class);
    }

    @Test
    public void testWrapXMethod() throws X {
        assertThat(LBiObjDoubleFunctionX.wrapX(opposite))
            .isInstanceOf(LBiObjDoubleFunctionX.class);
    }


    @Test
    public void testHandlingDoApplyMethodWrapsTheException() throws X {

        // given
        LBiObjDoubleFunctionX<Integer,Integer,Integer,X> sutThrowing = LBiObjDoubleFunctionX.lX((a1,a2,a3) -> {
            throw new UnsupportedOperationException();
        });

        // when
        LBiObjDoubleFunctionX<Integer,Integer,Integer,RuntimeException> wrapped = sutThrowing.handleBiObjDoubleFuncX(handler -> handler
            .wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED));

        // then
        try {
            wrapped.doApply(100,100,100d);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IllegalArgumentException.class)
                    .hasCauseExactlyInstanceOf(UnsupportedOperationException.class)
                    .hasMessage(EXCEPTION_WAS_WRAPPED);
        }
    }

    @Test
    public void testHandleBiObjDoubleFuncXMethodDoNotWrapsOtherExceptionIf() throws X {

        // given
        LBiObjDoubleFunctionX<Integer,Integer,Integer,X> sutThrowing = LBiObjDoubleFunctionX.lX((a1,a2,a3) -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LBiObjDoubleFunctionX<Integer,Integer,Integer,X> wrapped = sutThrowing.handleBiObjDoubleFuncX(handler -> handler
                .wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED)
                .throwIf(IndexOutOfBoundsException.class));

        // then
        try {
            wrapped.doApply(100,100,100d);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                    .hasNoCause();
        }
    }

@Test
    public void testHandleBiObjDoubleFuncXMethodDoNotWrapsOtherExceptionWhen() throws X {

        // given
        LBiObjDoubleFunctionX<Integer,Integer,Integer,X> sutThrowing = LBiObjDoubleFunctionX.lX((a1,a2,a3) -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LBiObjDoubleFunctionX<Integer,Integer,Integer,X> wrapped = sutThrowing.handleBiObjDoubleFuncX(handler -> handler
                .wrapWhen(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED)
                .throwIf(IndexOutOfBoundsException.class));

        // then
        try {
            wrapped.doApply(100,100,100d);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                    .hasNoCause();
        }
    }


    @Test
    public void testHandleBiObjDoubleFuncXMishandlingExceptionIsAllowed() throws X {

        // given
        LBiObjDoubleFunctionX<Integer,Integer,Integer,X> sutThrowing = LBiObjDoubleFunctionX.lX((a1,a2,a3) -> {
            throw (X) new ParseException(ORIGINAL_MESSAGE, 0);
        });

        // when
        LBiObjDoubleFunctionX<Integer,Integer,Integer,X> wrapped = sutThrowing.handleBiObjDoubleFuncX(h -> Function4U.doNothing());

        // then
        try {
            wrapped.doApply(100,100,100d);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(NestedException.class)
                    .hasCauseExactlyInstanceOf(ParseException.class)
                    .hasMessage(ORIGINAL_MESSAGE);
        }
    }



    // <editor-fold desc="compose (functional)">

    @Test
    public void testBiObjDoubleFuncComposeDouble() throws X {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LBiObjDoubleFunctionX<Integer,Integer,Integer,X> sutO = (a1,a2,a3) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(90);
                assertThat(a2).isEqualTo(91);
                assertThat(a3).isEqualTo(92d);
                return 100;
        };

        LFunctionX<Integer,Integer,X> before1 = p0 -> {
            assertThat(p0).isEqualTo(80);
            beforeCalls.incrementAndGet();
            return 90;
        };
        LFunctionX<Integer,Integer,X> before2 = p1 -> {
            assertThat(p1).isEqualTo(81);
            beforeCalls.incrementAndGet();
            return 91;
        };
        LDoubleUnaryOperatorX<X> before3 = p2 -> {
            assertThat(p2).isEqualTo(82d);
            beforeCalls.incrementAndGet();
            return 92d;
        };

        //when
        LBiObjDoubleFunctionX<Integer,Integer,Integer,X> function = sutO.biObjDoubleFuncComposeDouble(before1,before2,before3);
        function.doApply(80,81,82d);

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(beforeCalls.get()).isEqualTo(3);
    }


    @Test
    public void testBiObjDoubleFuncCompose() throws X {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LBiObjDoubleFunctionX<Integer,Integer,Integer,X> sutO = (a1,a2,a3) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(90);
                assertThat(a2).isEqualTo(91);
                assertThat(a3).isEqualTo(92d);
                return 100;
        };

        LFunctionX<Integer,Integer,X> before1 = p0 -> {
            assertThat(p0).isEqualTo(80);
            beforeCalls.incrementAndGet();
            return 90;
        };
        LFunctionX<Integer,Integer,X> before2 = p1 -> {
            assertThat(p1).isEqualTo(81);
            beforeCalls.incrementAndGet();
            return 91;
        };
        LToDoubleFunctionX<Integer,X> before3 = p2 -> {
            assertThat(p2).isEqualTo(82);
            beforeCalls.incrementAndGet();
            return 92d;
        };

        //when
        LTriFunctionX<Integer,Integer,Integer,Integer,X> function = sutO.biObjDoubleFuncCompose(before1,before2,before3);
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
        LBiObjDoubleFunctionX<Integer,Integer,Integer,X> sutO = (a1,a2,a3) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(80);
                assertThat(a2).isEqualTo(81);
                assertThat(a3).isEqualTo(82d);
                return 90;
        };

        LFunctionX<Integer,Integer,X> thenFunction = p -> {
                thenFunctionCalled.set(true);
                // Integer
                assertThat(p).isEqualTo(90);
                // Integer
                return 100;
        };

        //when
        LBiObjDoubleFunctionX<Integer,Integer,Integer,X> function = sutO.then(thenFunction);
        Integer finalValue = function.doApply(80,81,82d);

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
        LBiObjDoubleFunctionX<Integer,Integer,Integer,X> sutO = (a1,a2,a3) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(80);
                assertThat(a2).isEqualTo(81);
                assertThat(a3).isEqualTo(82d);
                return 90;
        };

        LConsumerX<Integer,X> thenFunction = p -> {
                thenFunctionCalled.set(true);
                // Integer
                assertThat(p).isEqualTo(90);
        };

        //when
        LBiObjDoubleConsumerX<Integer,Integer,X> function = sutO.then(thenFunction);
        function.doAccept(80,81,82d);

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    // </editor-fold>

    @Test
    public void testNesting() {
        assertThat(sut.nestingBiObjDoubleFunc())
            .isInstanceOf(LBiObjDoubleFunction.class);
    }

    @Test
    public void testShoving() {
        assertThat(sut.shovingBiObjDoubleFunc())
            .isInstanceOf(LBiObjDoubleFunction.class);
    }

    @Test
    public void testNestingX() {
        assertThat(sut.nestingBiObjDoubleFuncX())
            .isInstanceOf(LBiObjDoubleFunctionX.class);
    }

    @Test
    public void testShovingX() {
        assertThat(sut.shovingBiObjDoubleFuncX())
            .isInstanceOf(LBiObjDoubleFunctionX.class);
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void testShove() {

        // given
        LBiObjDoubleFunctionX<Integer,Integer,Integer,X> sutThrowing = LBiObjDoubleFunctionX.lX((a1,a2,a3) -> {
            throw new UnsupportedOperationException();
        });

        // when
        sutThrowing.shovingBiObjDoubleFunc().doApply(100,100,100d);
    }

    @Test
    public void testHandleBiObjDoubleFunc() throws X {

        // given
        LBiObjDoubleFunctionX<Integer,Integer,Integer,X> sutThrowing = LBiObjDoubleFunctionX.lX((a1,a2,a3) -> {
            throw new UnsupportedOperationException();
        });

        // when
        LBiObjDoubleFunctionX<Integer,Integer,Integer,X> wrapped = sutThrowing.handleBiObjDoubleFuncX(h -> {
            h.wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED);
        });

        // then
        try {
            wrapped.doApply(100,100,100d);
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
                .contains("LBiObjDoubleFunctionX: R doApply(T1 a1,T2 a2,double a3) throws X");
    }


    @Test
    public void isThrowing() {
        assertThat(sut.isThrowing())
            .isTrue();
    }

    //<editor-fold desc="Variants">

    private Integer variantV1(Integer a1,double a3,Integer a2) {
        return 100;
    }

    @Test
    public void compilerSubstituteVariantV1() {
        LBiObjDoubleFunctionX lambda = LBiObjDoubleFunctionX./*<T1,T2,R,X>*/lX1(this::variantV1);

        assertThat(lambda).isInstanceOf(LBiObjDoubleFunctionX.V1.class);
    }


    private Integer variantV2(Integer a2,Integer a1,double a3) {
        return 100;
    }

    @Test
    public void compilerSubstituteVariantV2() {
        LBiObjDoubleFunctionX lambda = LBiObjDoubleFunctionX./*<T1,T2,R,X>*/lX2(this::variantV2);

        assertThat(lambda).isInstanceOf(LBiObjDoubleFunctionX.V2.class);
    }


    private Integer variantV3(Integer a2,double a3,Integer a1) {
        return 100;
    }

    @Test
    public void compilerSubstituteVariantV3() {
        LBiObjDoubleFunctionX lambda = LBiObjDoubleFunctionX./*<T1,T2,R,X>*/lX3(this::variantV3);

        assertThat(lambda).isInstanceOf(LBiObjDoubleFunctionX.V3.class);
    }


    private Integer variantV4(double a3,Integer a1,Integer a2) {
        return 100;
    }

    @Test
    public void compilerSubstituteVariantV4() {
        LBiObjDoubleFunctionX lambda = LBiObjDoubleFunctionX./*<T1,T2,R,X>*/lX4(this::variantV4);

        assertThat(lambda).isInstanceOf(LBiObjDoubleFunctionX.V4.class);
    }


    private Integer variantV5(double a3,Integer a2,Integer a1) {
        return 100;
    }

    @Test
    public void compilerSubstituteVariantV5() {
        LBiObjDoubleFunctionX lambda = LBiObjDoubleFunctionX./*<T1,T2,R,X>*/lX5(this::variantV5);

        assertThat(lambda).isInstanceOf(LBiObjDoubleFunctionX.V5.class);
    }

    //</editor-fold>


    @Test void safeCompiles() {
        LBiObjDoubleFunctionX r1 = LBiObjDoubleFunctionX.safe(sut); //NOSONAR
    }

    @Test void safePropagates() {
        Object result = LBiObjDoubleFunctionX.safe(sut);
        assertThat(result).isSameAs(sut);
    }

    @Test void safeProtectsAgainstNpe() {
        Object result = LBiObjDoubleFunctionX.safe(null);
        assertThat(result).isSameAs(LBiObjDoubleFunctionX.lX(LBiObjDoubleFunctionX.safe()));
    }

    @Test <Y extends Throwable> void safeSupplierPropagates() {
        LSupplierX<LBiObjDoubleFunctionX<Integer,Integer,Integer,X>,Y> supplier = ()->sut;
        Object result = LBiObjDoubleFunctionX.safeSupplier(supplier);
        assertThat(result).isSameAs(supplier);
    }

    @Test <Y extends Throwable> void safeSupplierProtectsAgainstNpe() {
        Object result = LBiObjDoubleFunctionX.safeSupplier(null);
        assertThat(result).isSameAs(LBiObjDoubleFunctionX.safeSupplier());
    }

    @Test <Y extends Throwable> void safeSupplierCompiles() {
        LSupplierX<LBiObjDoubleFunctionX<Integer,Integer,Integer,X>,Y> r1 = LBiObjDoubleFunctionX.safeSupplier(()->sut);  //NOSONAR
    }

}
