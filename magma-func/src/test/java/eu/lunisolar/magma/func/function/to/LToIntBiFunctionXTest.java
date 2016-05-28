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

package eu.lunisolar.magma.func.function.to;

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
public class LToIntBiFunctionXTest<T1,T2,X extends ParseException> {
    private static final String ORIGINAL_MESSAGE = "Original message";
    private static final String EXCEPTION_WAS_WRAPPED = "Exception was wrapped.";
    private static final String NO_EXCEPTION_WERE_THROWN = "No exception were thrown.";

    private int testValue = 100;



    private LToIntBiFunctionX<Integer,Integer,X> sut = new LToIntBiFunctionX<Integer,Integer,X>(){
        public  int doApplyAsInt(Integer a1,Integer a2)  throws X {
            return testValue;
        }
    };

    private LToIntBiFunction<Integer,Integer> opposite = new LToIntBiFunction<Integer,Integer>(){
        public  int doApplyAsInt(Integer a1,Integer a2)  {
            return testValue;
        }
    };


    private ToIntBiFunction<Integer,Integer> jre = (a1,a2) -> testValue;


    private LToIntBiFunctionX<Integer,Integer,ParseException> sutAlwaysThrowing = LToIntBiFunctionX.lX((a1,a2) -> {
            throw new ParseException(ORIGINAL_MESSAGE, 0);
    });

    private LToIntBiFunctionX<Integer,Integer,RuntimeException> sutAlwaysThrowingUnchecked = LToIntBiFunctionX.lX((a1,a2) -> {
            throw new IndexOutOfBoundsException(ORIGINAL_MESSAGE);
    });


    @Test
    public void testTheResult() throws X {
        assertThat(sut.doApplyAsInt(100,100))
            .isEqualTo(testValue);
    }

    @Test
    public void testTupleCall() throws X {

        LPair<Integer,Integer> domainObject = Tuple4U.pair(100,100);

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
    public void testNestingDoApplyAsIntChecked() throws X {

        // then
        try {
            sutAlwaysThrowing.nestingDoApplyAsInt(100,100);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(NestedException.class)
                    .hasCauseExactlyInstanceOf(ParseException.class)
                    .hasMessage(ORIGINAL_MESSAGE);
        }
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
    public void testShovingDoApplyAsIntChecked() throws X {

        // then
        try {
            sutAlwaysThrowing.shovingDoApplyAsInt(100,100);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(ParseException.class)
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
            .isEqualTo("LToIntBiFunctionX: int doApplyAsInt(T1 a1,T2 a2) throws X");
    }

    @Test
    public void testLXMethod() throws X {
        assertThat(LToIntBiFunctionX.lX((a1,a2) -> testValue ))
            .isInstanceOf(LToIntBiFunctionX.class);
    }

    @Test
    public void testWrapXMethod() throws X {
        assertThat(LToIntBiFunctionX.wrapX(opposite))
            .isInstanceOf(LToIntBiFunctionX.class);
    }

    @Test
    public void testWrapStdMethod() throws X {
        assertThat(LToIntBiFunctionX.wrap(jre))
            .isInstanceOf(LToIntBiFunctionX.class);
    }


    @Test
    public void testHandlingDoApplyAsIntMethodWrapsTheException() throws X {

        // given
        LToIntBiFunctionX<Integer,Integer,X> sutThrowing = LToIntBiFunctionX.lX((a1,a2) -> {
            throw new UnsupportedOperationException();
        });

        // when
        LToIntBiFunctionX<Integer,Integer,RuntimeException> wrapped = sutThrowing.handleToIntBiFuncX(handler -> handler
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
    public void testHandleToIntBiFuncXMethodDoNotWrapsOtherExceptionIf() throws X {

        // given
        LToIntBiFunctionX<Integer,Integer,X> sutThrowing = LToIntBiFunctionX.lX((a1,a2) -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LToIntBiFunctionX<Integer,Integer,X> wrapped = sutThrowing.handleToIntBiFuncX(handler -> handler
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
    public void testHandleToIntBiFuncXMethodDoNotWrapsOtherExceptionWhen() throws X {

        // given
        LToIntBiFunctionX<Integer,Integer,X> sutThrowing = LToIntBiFunctionX.lX((a1,a2) -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LToIntBiFunctionX<Integer,Integer,X> wrapped = sutThrowing.handleToIntBiFuncX(handler -> handler
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
    public void testHandleToIntBiFuncXMishandlingExceptionIsAllowed() throws X {

        // given
        LToIntBiFunctionX<Integer,Integer,X> sutThrowing = LToIntBiFunctionX.lX((a1,a2) -> {
            throw (X) new ParseException(ORIGINAL_MESSAGE, 0);
        });

        // when
        LToIntBiFunctionX<Integer,Integer,X> wrapped = sutThrowing.handleToIntBiFuncX(h -> Function4U.doNothing());

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



    // <editor-fold desc="compose (functional)">

    @Test
    public void testToIntBiFuncCompose() throws X {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LToIntBiFunctionX<Integer,Integer,X> sutO = (a1,a2) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(90);
                assertThat(a2).isEqualTo(91);
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

        //when
        LToIntBiFunctionX<Integer,Integer,X> function = sutO.toIntBiFuncCompose(before1,before2);
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
        LToIntBiFunctionX<Integer,Integer,X> sutO = (a1,a2) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(80);
                assertThat(a2).isEqualTo(81);
                return 90;
        };

        LIntFunctionX<Integer,X> thenFunction = p -> {
                thenFunctionCalled.set(true);
                // int
                assertThat(p).isEqualTo(90);
                // Integer
                return 100;
        };

        //when
        LBiFunctionX<Integer,Integer,Integer,X> function = sutO.then(thenFunction);
        Integer finalValue = function.doApply(80,81);

        //then - finals
        assertThat(finalValue).isEqualTo(100);
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    // </editor-fold>

    @Test
    public void testNesting() {
        assertThat(sut.nestingToIntBiFunc())
            .isInstanceOf(LToIntBiFunction.class);
    }

    @Test
    public void testShoving() {
        assertThat(sut.shovingToIntBiFunc())
            .isInstanceOf(LToIntBiFunction.class);
    }

    @Test
    public void testNestingX() {
        assertThat(sut.nestingToIntBiFuncX())
            .isInstanceOf(LToIntBiFunctionX.class);
    }

    @Test
    public void testShovingX() {
        assertThat(sut.shovingToIntBiFuncX())
            .isInstanceOf(LToIntBiFunctionX.class);
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void testShove() {

        // given
        LToIntBiFunctionX<Integer,Integer,X> sutThrowing = LToIntBiFunctionX.lX((a1,a2) -> {
            throw new UnsupportedOperationException();
        });

        // when
        sutThrowing.shovingToIntBiFunc().doApplyAsInt(100,100);
    }

    @Test
    public void testHandleToIntBiFunc() throws X {

        // given
        LToIntBiFunctionX<Integer,Integer,X> sutThrowing = LToIntBiFunctionX.lX((a1,a2) -> {
            throw new UnsupportedOperationException();
        });

        // when
        LToIntBiFunctionX<Integer,Integer,X> wrapped = sutThrowing.handleToIntBiFuncX(h -> {
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
                .contains("LToIntBiFunctionX: int doApplyAsInt(T1 a1,T2 a2) throws X");
    }


    @Test
    public void isThrowing() {
        assertThat(sut.isThrowing())
            .isTrue();
    }

    //<editor-fold desc="Variants">

    private int variantV1(Integer a2,Integer a1) {
        return 100;
    }

    @Test
    public void compilerSubstituteVariantV1() {
        LToIntBiFunctionX lambda = LToIntBiFunctionX./*<T1,T2,X>*/lX1(this::variantV1);

        assertThat(lambda).isInstanceOf(LToIntBiFunctionX.V1.class);
    }

    //</editor-fold>


    @Test void safeCompiles() {
        LToIntBiFunctionX r1 = LToIntBiFunctionX.safe(sut); //NOSONAR
        ToIntBiFunction r3 = LToIntBiFunctionX.safe(sut); //NOSONAR
    }

    @Test void safePropagates() {
        Object result = LToIntBiFunctionX.safe(sut);
        assertThat(result).isSameAs(sut);
    }

    @Test void safeProtectsAgainstNpe() {
        Object result = LToIntBiFunctionX.safe(null);
        assertThat(result).isSameAs(LToIntBiFunctionX.lX(LToIntBiFunctionX.safe()));
    }

    @Test <Y extends Throwable> void safeSupplierPropagates() {
        LSupplierX<LToIntBiFunctionX<Integer,Integer,X>,Y> supplier = ()->sut;
        Object result = LToIntBiFunctionX.safeSupplier(supplier);
        assertThat(result).isSameAs(supplier);
    }

    @Test <Y extends Throwable> void safeSupplierProtectsAgainstNpe() {
        Object result = LToIntBiFunctionX.safeSupplier(null);
        assertThat(result).isSameAs(LToIntBiFunctionX.safeSupplier());
    }

    @Test <Y extends Throwable> void safeSupplierCompiles() {
        LSupplierX<LToIntBiFunctionX<Integer,Integer,X>,Y> r1 = LToIntBiFunctionX.safeSupplier(()->sut);  //NOSONAR
        Supplier<LToIntBiFunctionX<Integer,Integer,X>> r2 = LToIntBiFunctionX.safeSupplier(()->sut); //NOSONAR
    }

}
