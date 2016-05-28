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
public class LObjIntToIntFunctionTest<T,X extends ParseException> {
    private static final String ORIGINAL_MESSAGE = "Original message";
    private static final String EXCEPTION_WAS_WRAPPED = "Exception was wrapped.";
    private static final String NO_EXCEPTION_WERE_THROWN = "No exception were thrown.";

    private int testValue = 100;



    private LObjIntToIntFunction<Integer> sut = new LObjIntToIntFunction<Integer>(){
        public  int doApplyAsInt(Integer a1,int a2)  {
            return testValue;
        }
    };

    private LObjIntToIntFunctionX<Integer,X> opposite = new LObjIntToIntFunctionX<Integer,X>(){
        public  int doApplyAsInt(Integer a1,int a2)  throws X {
            return testValue;
        }
    };




    private LObjIntToIntFunctionX<Integer,RuntimeException> sutAlwaysThrowingUnchecked = LObjIntToIntFunction.l((a1,a2) -> {
            throw new IndexOutOfBoundsException(ORIGINAL_MESSAGE);
    });


    @Test
    public void testTheResult() throws X {
        assertThat(sut.doApplyAsInt(100,100))
            .isEqualTo(testValue);
    }

    @Test
    public void testTupleCall() throws X {

        LObjIntPair<Integer> domainObject = Tuple4U.objIntPair(100,100);

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
            .isEqualTo("LObjIntToIntFunction: int doApplyAsInt(T a1,int a2)");
    }

    @Test
    public void testLMethod() throws X {
        assertThat(LObjIntToIntFunction.l((a1,a2) -> testValue ))
            .isInstanceOf(LObjIntToIntFunction.class);
    }

    @Test
    public void testWrapMethod() throws X {
        assertThat(LObjIntToIntFunction.wrap(opposite))
            .isInstanceOf(LObjIntToIntFunction.class);
    }

    @Test
    public void testWrapMethodDoNotWrapsRuntimeException() throws X {
        // given
        LObjIntToIntFunctionX<Integer,X> sutThrowing = LObjIntToIntFunctionX.lX((a1,a2) -> {
            throw new UnsupportedOperationException(ORIGINAL_MESSAGE);
        });

        // when
        LObjIntToIntFunction<Integer> wrapped = LObjIntToIntFunction.wrap(sutThrowing);

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
        LObjIntToIntFunctionX<Integer,ParseException> sutThrowing = LObjIntToIntFunctionX.lX((a1,a2) -> {
            throw new ParseException(ORIGINAL_MESSAGE, 0);
        });

        // when
        LObjIntToIntFunction<Integer> wrapped = LObjIntToIntFunction.wrap(sutThrowing);

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
        LObjIntToIntFunction<Integer> sutThrowing = LObjIntToIntFunction.l((a1,a2) -> {
            throw new UnsupportedOperationException();
        });

        // when
        LObjIntToIntFunction<Integer> wrapped = sutThrowing.handleObjIntToIntFunc(handler -> handler
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
    public void testHandleObjIntToIntFuncMethodDoNotWrapsOtherExceptionIf() throws X {

        // given
        LObjIntToIntFunction<Integer> sutThrowing = LObjIntToIntFunction.l((a1,a2) -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LObjIntToIntFunction<Integer> wrapped = sutThrowing.handleObjIntToIntFunc(handler -> handler
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
    public void testHandleObjIntToIntFuncMethodDoNotWrapsOtherExceptionWhen() throws X {

        // given
        LObjIntToIntFunction<Integer> sutThrowing = LObjIntToIntFunction.l((a1,a2) -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LObjIntToIntFunction<Integer> wrapped = sutThrowing.handleObjIntToIntFunc(handler -> handler
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
    public void testHandleObjIntToIntFuncMishandlingExceptionIsAllowed() throws X {

        // given
        LObjIntToIntFunction<Integer> sutThrowing = LObjIntToIntFunction.l((a1,a2) -> {
            throw new UnsupportedOperationException(ORIGINAL_MESSAGE);
        });

        // when
        LObjIntToIntFunction<Integer> wrapped = sutThrowing.handleObjIntToIntFunc(h -> Function4U.doNothing());

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



    // <editor-fold desc="compose (functional)">

    @Test
    public void testObjIntToIntFuncComposeInt() throws X {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LObjIntToIntFunction<Integer> sutO = (a1,a2) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(90);
                assertThat(a2).isEqualTo(91);
                return 100;
        };

        LFunction<Integer,Integer> before1 = p0 -> {
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
        LObjIntToIntFunction<Integer> function = sutO.objIntToIntFuncComposeInt(before1,before2);
        function.doApplyAsInt(80,81);

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(beforeCalls.get()).isEqualTo(2);
    }


    @Test
    public void testObjIntToIntFuncCompose() throws X {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LObjIntToIntFunction<Integer> sutO = (a1,a2) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(90);
                assertThat(a2).isEqualTo(91);
                return 100;
        };

        LFunction<Integer,Integer> before1 = p0 -> {
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
        LToIntBiFunction<Integer,Integer> function = sutO.objIntToIntFuncCompose(before1,before2);
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
        LObjIntToIntFunction<Integer> sutO = (a1,a2) -> {
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
        LObjIntFunction<Integer,Integer> function = sutO.then(thenFunction);
        Integer finalValue = function.doApply(80,81);

        //then - finals
        assertThat(finalValue).isEqualTo(100);
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    // </editor-fold>

    @Test
    public void testNesting() {
        assertThat(sut.nestingObjIntToIntFunc())
            .isSameAs(sut)
            .isInstanceOf(LObjIntToIntFunction.class);
    }

    @Test
    public void testShoving() {
        assertThat(sut.shovingObjIntToIntFunc())
            .isSameAs(sut)
            .isInstanceOf(LObjIntToIntFunction.class);
    }

    @Test
    public void testNestingX() {
        assertThat(sut.nestingObjIntToIntFuncX())
            .isSameAs(sut)
            .isInstanceOf(LObjIntToIntFunctionX.class);
    }

    @Test
    public void testShovingX() {
        assertThat(sut.shovingObjIntToIntFuncX())
            .isSameAs(sut)
            .isInstanceOf(LObjIntToIntFunctionX.class);
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void testShove() {

        // given
        LObjIntToIntFunction<Integer> sutThrowing = LObjIntToIntFunction.l((a1,a2) -> {
            throw new UnsupportedOperationException();
        });

        // when
        sutThrowing.shovingObjIntToIntFunc().doApplyAsInt(100,100);
    }

    @Test
    public void testHandleObjIntToIntFunc() throws X {

        // given
        LObjIntToIntFunction<Integer> sutThrowing = LObjIntToIntFunction.l((a1,a2) -> {
            throw new UnsupportedOperationException();
        });

        // when
        LObjIntToIntFunction<Integer> wrapped = sutThrowing.handleObjIntToIntFunc(h -> {
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
                .contains("LObjIntToIntFunction: int doApplyAsInt(T a1,int a2)");
    }


    @Test
    public void isThrowing() {
        assertThat(sut.isThrowing())
            .isFalse();
    }

    //<editor-fold desc="Variants">

    private int variantV1(int a2,Integer a1) {
        return 100;
    }

    @Test
    public void compilerSubstituteVariantV1() {
        LObjIntToIntFunction lambda = LObjIntToIntFunction./*<T>*/l1(this::variantV1);

        assertThat(lambda).isInstanceOf(LObjIntToIntFunction.V1.class);
    }

    //</editor-fold>


    @Test void safeCompiles() {
        LObjIntToIntFunction r1 = LObjIntToIntFunction.safe(sut); //NOSONAR
        LObjIntToIntFunctionX r2 = LObjIntToIntFunction.safe(sut); //NOSONAR
    }

    @Test void safePropagates() {
        Object result = LObjIntToIntFunction.safe(sut);
        assertThat(result).isSameAs(sut);
    }

    @Test void safeProtectsAgainstNpe() {
        Object result = LObjIntToIntFunction.safe(null);
        assertThat(result).isSameAs(LObjIntToIntFunction.l(LObjIntToIntFunction.safe()));
    }

    @Test  void safeSupplierPropagates() {
        LSupplier<LObjIntToIntFunction<Integer>> supplier = ()->sut;
        Object result = LObjIntToIntFunction.safeSupplier(supplier);
        assertThat(result).isSameAs(supplier);
    }

    @Test  void safeSupplierProtectsAgainstNpe() {
        Object result = LObjIntToIntFunction.safeSupplier(null);
        assertThat(result).isSameAs(LObjIntToIntFunction.safeSupplier());
    }

    @Test  void safeSupplierCompiles() {
        LSupplier<LObjIntToIntFunction<Integer>> r1 = LObjIntToIntFunction.safeSupplier(()->sut);  //NOSONAR
    }

}
