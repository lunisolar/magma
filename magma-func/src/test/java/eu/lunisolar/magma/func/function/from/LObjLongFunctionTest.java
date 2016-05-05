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
public class LObjLongFunctionTest<T,R,X extends ParseException> {
    private static final String ORIGINAL_MESSAGE = "Original message";
    private static final String EXCEPTION_WAS_WRAPPED = "Exception was wrapped.";
    private static final String NO_EXCEPTION_WERE_THROWN = "No exception were thrown.";

    private Integer testValue = 100;



    private LObjLongFunction<Integer,Integer> sut = new LObjLongFunction<Integer,Integer>(){
        public @Nullable Integer doApply(Integer a1,long a2)  {
            return testValue;
        }
    };

    private LObjLongFunctionX<Integer,Integer,X> opposite = new LObjLongFunctionX<Integer,Integer,X>(){
        public @Nullable Integer doApply(Integer a1,long a2)  throws X {
            return testValue;
        }
    };

    private LObjLongFunction<Integer,Integer> sutNull = new LObjLongFunction<Integer,Integer>(){
        public @Nullable Integer doApply(Integer a1,long a2)  {
            return null;
        }
    };




    private LObjLongFunctionX<Integer,Integer,RuntimeException> sutAlwaysThrowingUnchecked = LObjLongFunction.l((a1,a2) -> {
            throw new IndexOutOfBoundsException(ORIGINAL_MESSAGE);
    });


    @Test
    public void testTheResult() throws X {
        assertThat(sut.doApply(100,100L))
            .isEqualTo(testValue);
    }

    @Test
    public void testTupleCall() throws X {

        LObjLongPair<Integer> domainObject = Tuple4U.objLongPair(100,100L);

        Object result = sut.tupleApply(domainObject);

        assertThat(result)
            .isEqualTo(testValue);
    }

    @Test
    public void testNonNullDoApply() throws X {
        assertThat(sut.nonNullDoApply(100,100L))
            .isSameAs(testValue);
    }

    @Test
    public void testNestingDoApplyUnchecked() throws X {

        // then
        try {
            sutAlwaysThrowingUnchecked.nestingDoApply(100,100L);
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
            sutAlwaysThrowingUnchecked.shovingDoApply(100,100L);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                    .hasNoCause()
                    .hasMessage(ORIGINAL_MESSAGE);
        }
    }

    @Test(expectedExceptions=NullPointerException.class, expectedExceptionsMessageRegExp="\\QEvaluated value by nonNullDoApply() method cannot be null (LObjLongFunction: R doApply(T a1,long a2)).\\E")
    public void testNonNullCapturesNull() throws X {
        sutNull.nonNullDoApply(100,100L);
    }


    @Test
    public void testFunctionalInterfaceDescription() throws X {
        assertThat(sut.functionalInterfaceDescription())
            .isEqualTo("LObjLongFunction: R doApply(T a1,long a2)");
    }

    @Test
    public void testLMethod() throws X {
        assertThat(LObjLongFunction.l((a1,a2) -> testValue ))
            .isInstanceOf(LObjLongFunction.class);
    }

    @Test
    public void testWrapMethod() throws X {
        assertThat(LObjLongFunction.wrap(opposite))
            .isInstanceOf(LObjLongFunction.class);
    }

    @Test
    public void testWrapMethodDoNotWrapsRuntimeException() throws X {
        // given
        LObjLongFunctionX<Integer,Integer,X> sutThrowing = LObjLongFunctionX.lX((a1,a2) -> {
            throw new UnsupportedOperationException(ORIGINAL_MESSAGE);
        });

        // when
        LObjLongFunction<Integer,Integer> wrapped = LObjLongFunction.wrap(sutThrowing);

        // then
        try {
            wrapped.doApply(100,100L);
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
        LObjLongFunctionX<Integer,Integer,ParseException> sutThrowing = LObjLongFunctionX.lX((a1,a2) -> {
            throw new ParseException(ORIGINAL_MESSAGE, 0);
        });

        // when
        LObjLongFunction<Integer,Integer> wrapped = LObjLongFunction.wrap(sutThrowing);

        // then
        try {
            wrapped.doApply(100,100L);
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
        LObjLongFunction<Integer,Integer> sutThrowing = LObjLongFunction.l((a1,a2) -> {
            throw new UnsupportedOperationException();
        });

        // when
        LObjLongFunction<Integer,Integer> wrapped = sutThrowing.handleObjLongFunc(handler -> handler
            .wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED));

        // then
        try {
            wrapped.doApply(100,100L);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IllegalArgumentException.class)
                    .hasCauseExactlyInstanceOf(UnsupportedOperationException.class)
                    .hasMessage(EXCEPTION_WAS_WRAPPED);
        }
    }

    @Test
    public void testHandleObjLongFuncMethodDoNotWrapsOtherExceptionIf() throws X {

        // given
        LObjLongFunction<Integer,Integer> sutThrowing = LObjLongFunction.l((a1,a2) -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LObjLongFunction<Integer,Integer> wrapped = sutThrowing.handleObjLongFunc(handler -> handler
                .wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED)
                .throwIf(IndexOutOfBoundsException.class));

        // then
        try {
            wrapped.doApply(100,100L);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                    .hasNoCause();
        }
    }

@Test
    public void testHandleObjLongFuncMethodDoNotWrapsOtherExceptionWhen() throws X {

        // given
        LObjLongFunction<Integer,Integer> sutThrowing = LObjLongFunction.l((a1,a2) -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LObjLongFunction<Integer,Integer> wrapped = sutThrowing.handleObjLongFunc(handler -> handler
                .wrapWhen(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED)
                .throwIf(IndexOutOfBoundsException.class));

        // then
        try {
            wrapped.doApply(100,100L);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                    .hasNoCause();
        }
    }


    @Test
    public void testHandleObjLongFuncMishandlingExceptionIsAllowed() throws X {

        // given
        LObjLongFunction<Integer,Integer> sutThrowing = LObjLongFunction.l((a1,a2) -> {
            throw new UnsupportedOperationException(ORIGINAL_MESSAGE);
        });

        // when
        LObjLongFunction<Integer,Integer> wrapped = sutThrowing.handleObjLongFunc(h -> Function4U.doNothing());

        // then
        try {
            wrapped.doApply(100,100L);
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
    public void testObjLongFuncComposeLong() throws X {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LObjLongFunction<Integer,Integer> sutO = (Integer a1,long a2) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(90);
                assertThat(a2).isEqualTo(91L);
                return 100;
        };

        LFunction<Integer,Integer> before1 = p0 -> {
            assertThat(p0).isEqualTo(80);
            beforeCalls.incrementAndGet();
            return 90;
        };
        LLongUnaryOperator before2 = p1 -> {
            assertThat(p1).isEqualTo(81L);
            beforeCalls.incrementAndGet();
            return 91L;
        };

        //when
        LObjLongFunction<Integer,Integer> function = sutO.objLongFuncComposeLong(before1,before2);
        function.doApply(80,81L);

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(beforeCalls.get()).isEqualTo(2);
    }


    @Test
    public void testObjLongFuncCompose() throws X {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LObjLongFunction<Integer,Integer> sutO = (Integer a1,long a2) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(90);
                assertThat(a2).isEqualTo(91L);
                return 100;
        };

        LFunction<Integer,Integer> before1 = p0 -> {
            assertThat(p0).isEqualTo(80);
            beforeCalls.incrementAndGet();
            return 90;
        };
        LToLongFunction<Integer> before2 = p1 -> {
            assertThat(p1).isEqualTo(81);
            beforeCalls.incrementAndGet();
            return 91L;
        };

        //when
        LBiFunction<Integer,Integer,Integer> function = sutO.objLongFuncCompose(before1,before2);
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
        LObjLongFunction<Integer,Integer> sutO = (a1,a2) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(80);
                assertThat(a2).isEqualTo(81L);
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
        LObjLongFunction<Integer,Integer> function = sutO.then(thenFunction);
        Integer finalValue = function.doApply(80,81L);

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
        LObjLongFunction<Integer,Integer> sutO = (a1,a2) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(80);
                assertThat(a2).isEqualTo(81L);
                return 90;
        };

        LConsumer<Integer> thenFunction = p -> {
                thenFunctionCalled.set(true);
                // Integer
                assertThat(p).isEqualTo(90);
        };

        //when
        LObjLongConsumer<Integer> function = sutO.then(thenFunction);
        function.doAccept(80,81L);

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    // </editor-fold>

    @Test
    public void testNesting() {
        assertThat(sut.nestingObjLongFunc())
            .isSameAs(sut)
            .isInstanceOf(LObjLongFunction.class);
    }

    @Test
    public void testShoving() {
        assertThat(sut.shovingObjLongFunc())
            .isSameAs(sut)
            .isInstanceOf(LObjLongFunction.class);
    }

    @Test
    public void testNestingX() {
        assertThat(sut.nestingObjLongFuncX())
            .isSameAs(sut)
            .isInstanceOf(LObjLongFunctionX.class);
    }

    @Test
    public void testShovingX() {
        assertThat(sut.shovingObjLongFuncX())
            .isSameAs(sut)
            .isInstanceOf(LObjLongFunctionX.class);
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void testShove() {

        // given
        LObjLongFunction<Integer,Integer> sutThrowing = LObjLongFunction.l((a1,a2) -> {
            throw new UnsupportedOperationException();
        });

        // when
        sutThrowing.shovingObjLongFunc().doApply(100,100L);
    }

    @Test
    public void testHandleObjLongFunc() throws X {

        // given
        LObjLongFunction<Integer,Integer> sutThrowing = LObjLongFunction.l((a1,a2) -> {
            throw new UnsupportedOperationException();
        });

        // when
        LObjLongFunction<Integer,Integer> wrapped = sutThrowing.handleObjLongFunc(h -> {
            h.wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED);
        });

        // then
        try {
            wrapped.doApply(100,100L);
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
                .contains("LObjLongFunction: R doApply(T a1,long a2)");
    }


    @Test
    public void isThrowing() {
        assertThat(sut.isThrowing())
            .isFalse();
    }

    //<editor-fold desc="Variants">

    private Integer variantV1(long a2,Integer a1) {
        return 100;
    }

    @Test
    public void compilerSubstituteVariantV1() {
        LObjLongFunction lambda = LObjLongFunction./*<T,R>*/l1(this::variantV1);

        assertThat(lambda).isInstanceOf(LObjLongFunction.V1.class);
    }

    //</editor-fold>


    @Test void safeCompiles() {
        LObjLongFunction r1 = LObjLongFunction.safe(sut); //NOSONAR
        LObjLongFunctionX r2 = LObjLongFunction.safe(sut); //NOSONAR
    }

    @Test void safePropagates() {
        Object result = LObjLongFunction.safe(sut);
        assertThat(result).isSameAs(sut);
    }

    @Test void safeProtectsAgainstNpe() {
        Object result = LObjLongFunction.safe(null);
        assertThat(result).isSameAs(LObjLongFunction.l(LObjLongFunction.safe()));
    }

    @Test  void safeSupplierPropagates() {
        LSupplier<LObjLongFunction<Integer,Integer>> supplier = ()->sut;
        Object result = LObjLongFunction.safeSupplier(supplier);
        assertThat(result).isSameAs(supplier);
    }

    @Test  void safeSupplierProtectsAgainstNpe() {
        Object result = LObjLongFunction.safeSupplier(null);
        assertThat(result).isSameAs(LObjLongFunction.safeSupplier());
    }

    @Test  void safeSupplierCompiles() {
        LSupplier<LObjLongFunction<Integer,Integer>> r1 = LObjLongFunction.safeSupplier(()->sut);  //NOSONAR
    }

}
