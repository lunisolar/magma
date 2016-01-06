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
import eu.lunisolar.magma.func.operator.unary.*; // NOSONAR
import eu.lunisolar.magma.func.operator.binary.*; // NOSONAR
import eu.lunisolar.magma.func.operator.ternary.*; // NOSONAR
import eu.lunisolar.magma.func.function.*; // NOSONAR
import eu.lunisolar.magma.func.function.from.*; // NOSONAR
import eu.lunisolar.magma.func.function.to.*; // NOSONAR
import eu.lunisolar.magma.func.function.conversion.*; // NOSONAR
import eu.lunisolar.magma.func.predicate.*; // NOSONAR
import eu.lunisolar.magma.func.supplier.*; // NOSONAR
import eu.lunisolar.magma.func.consumer.primitives.obj.*; // NOSONAR
import eu.lunisolar.magma.func.consumer.primitives.bi.*; // NOSONAR
import eu.lunisolar.magma.func.consumer.primitives.tri.*; // NOSONAR
import eu.lunisolar.magma.func.consumer.primitives.*; // NOSONAR
import eu.lunisolar.magma.func.consumer.*; // NOSONAR
import eu.lunisolar.magma.func.action.*; // NOSONAR

import java.util.function.*; // NOSONAR
import org.assertj.core.api.Assertions;  //NOSONAR
import org.testng.annotations.*;      //NOSONAR
import java.util.regex.Pattern;          //NOSONAR
import java.text.ParseException;         //NOSONAR
import eu.lunisolar.magma.basics.*; //NOSONAR
import eu.lunisolar.magma.basics.exceptions.*; //NOSONAR
import java.util.concurrent.atomic.AtomicInteger; //NOSONAR
import eu.lunisolar.magma.struct.tuple.*; // NOSONAR
import static org.assertj.core.api.Assertions.*; //NOSONAR

/** The test obviously concentrate on the interface methods the function it self is very simple.  */
public class LObjShortFunctionTest<T,R,X extends ParseException> {
    private static final String ORIGINAL_MESSAGE = "Original message";
    private static final String EXCEPTION_WAS_WRAPPED = "Exception was wrapped.";
    private static final String NO_EXCEPTION_WERE_THROWN = "No exception were thrown.";

    private Object  testValue = (R)Integer.valueOf(100);



    private LObjShortFunction<T,R> sut = new LObjShortFunction(){
        public @Nullable Object  doApply(Object a1,short a2)  {
            return testValue;
        }
    };

    private LObjShortFunctionX<T,R,X> opposite = new LObjShortFunctionX(){
        public @Nullable Object  doApply(Object a1,short a2) throws ParseException {
            return testValue;
        }
    };

    private LObjShortFunction<T,R> sutNull = new LObjShortFunction(){
        public @Nullable Object  doApply(Object a1,short a2)  {
            return null;
        }
    };




    private LObjShortFunction<T,R> sutAlwaysThrowingUnckeck = LObjShortFunction.l((T a1,short a2) -> {
            throw new IndexOutOfBoundsException(ORIGINAL_MESSAGE);
    });


    @Test
    public void testTheResult() throws X {
        assertThat(sut.doApply((T)Integer.valueOf(100),(short)100))
            .isSameAs(testValue);
    }

    @Test
    public void testTupleCall() throws X {

        LObjShortPair<T> domainObject = Tuple4U.tuple((T)Integer.valueOf(100),(short)100);

        Object result = sut.tupleApply(domainObject);

        assertThat(result)
            .isSameAs(testValue);
    }

    @Test
    public void testNonNullDoApply() throws X {
        assertThat(sut.nonNullDoApply((T)Integer.valueOf(100),(short)100))
            .isSameAs(testValue);
    }

    @Test
    public void testNestingDoApplyUnckeck() throws X {

        // then
        try {
            sutAlwaysThrowingUnckeck.nestingDoApply((T)Integer.valueOf(100),(short)100);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                    .hasNoCause()
                    .hasMessage(ORIGINAL_MESSAGE);
        }
    }

    @Test
    public void testShovingDoApplyUnckeck() throws X {

        // then
        try {
            sutAlwaysThrowingUnckeck.shovingDoApply((T)Integer.valueOf(100),(short)100);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                    .hasNoCause()
                    .hasMessage(ORIGINAL_MESSAGE);
        }
    }

    @Test(expectedExceptions=NullPointerException.class, expectedExceptionsMessageRegExp="\\QEvaluated value by nonNullDoApply() method cannot be null (LObjShortFunction: R doApply(T a1,short a2)).\\E")
    public void testNonNullCapturesNull() throws X {
        sutNull.nonNullDoApply((T)Integer.valueOf(100),(short)100);
    }


    @Test
    public void testFunctionalInterfaceDescription() throws X {
        assertThat(sut.functionalInterfaceDescription())
            .isEqualTo("LObjShortFunction: R doApply(T a1,short a2)");
    }

    @Test
    public void testLMethod() throws X {
        assertThat(LObjShortFunction.l((Object a1,short a2) -> testValue ))
            .isInstanceOf(LObjShortFunction.class);
    }

    @Test
    public void testWrapMethod() throws X {
        assertThat(LObjShortFunction.wrap(opposite))
            .isInstanceOf(LObjShortFunction.class);
    }

    @Test
    public void testWrapMethodDoNotWrapsRuntimeException() throws X {
        // given
        LObjShortFunctionX<T,R,X> sutThrowing = LObjShortFunctionX.lX((T a1,short a2) -> {
            throw new UnsupportedOperationException(ORIGINAL_MESSAGE);
        });

        // when
        LObjShortFunction<T,R> wrapped = LObjShortFunction.wrap(sutThrowing);

        // then
        try {
            wrapped.doApply((T)Integer.valueOf(100),(short)100);
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
        LObjShortFunctionX<T,R,ParseException> sutThrowing = LObjShortFunctionX.lX((T a1,short a2) -> {
            throw new ParseException(ORIGINAL_MESSAGE, 0);
        });

        // when
        LObjShortFunction<T,R> wrapped = LObjShortFunction.wrap(sutThrowing);

        // then
        try {
            wrapped.doApply((T)Integer.valueOf(100),(short)100);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(NestedException.class)
                    .hasCauseExactlyInstanceOf(ParseException.class)
                    .hasMessage(ORIGINAL_MESSAGE);
        }
    }


    @Test
    public void testWrapExceptionMethodWrapsTheException() throws X {

        // given
        LObjShortFunction<T,R> sutThrowing = LObjShortFunction.l((T a1,short a2) -> {
            throw new UnsupportedOperationException();
        });

        // when
        LObjShortFunction<T,R> wrapped = sutThrowing.handleObjShortFunc(handler -> handler
            .wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED));

        // then
        try {
            wrapped.doApply((T)Integer.valueOf(100),(short)100);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IllegalArgumentException.class)
                    .hasCauseExactlyInstanceOf(UnsupportedOperationException.class)
                    .hasMessage(EXCEPTION_WAS_WRAPPED);
        }
    }

    @Test
    public void testWrapExceptionMethodDoNotWrapsOtherExceptionIf() throws X {

        // given
        LObjShortFunction<T,R> sutThrowing = LObjShortFunction.l((T a1,short a2) -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LObjShortFunction<T,R> wrapped = sutThrowing.handleObjShortFunc(handler -> handler
                .wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED)
                .throwIf(IndexOutOfBoundsException.class));

        // then
        try {
            wrapped.doApply((T)Integer.valueOf(100),(short)100);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                    .hasNoCause();
        }
    }

@Test
    public void testWrapExceptionMethodDoNotWrapsOtherExceptionWhen() throws X {

        // given
        LObjShortFunction<T,R> sutThrowing = LObjShortFunction.l((T a1,short a2) -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LObjShortFunction<T,R> wrapped = sutThrowing.handleObjShortFunc(handler -> handler
                .wrapWhen(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED)
                .throwIf(IndexOutOfBoundsException.class));

        // then
        try {
            wrapped.doApply((T)Integer.valueOf(100),(short)100);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                    .hasNoCause();
        }
    }


    @Test
    public void testWrapExceptionMishandlingExceptionIsAllowed() throws X {

        // given
        LObjShortFunction<T,R> sutThrowing = LObjShortFunction.l((T a1,short a2) -> {
            throw new UnsupportedOperationException(ORIGINAL_MESSAGE);
        });

        // when
        LObjShortFunction<T,R> wrapped = sutThrowing.handleObjShortFunc(h -> Function4U.doNothing());

        // then
        try {
            wrapped.doApply((T)Integer.valueOf(100),(short)100);
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
    public void testobjShortFuncComposeShort() throws X {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LObjShortFunction<Integer ,Integer > sutO = (Integer a1,short a2) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo((T)Integer.valueOf(90));
                assertThat(a2).isEqualTo((short)91);
                return 9;
        };

        LFunction<Integer ,Integer > before1 = p0 -> {
            assertThat(p0).isEqualTo((T)Integer.valueOf(80));
            beforeCalls.incrementAndGet();
            return 90;
        };
        LShortUnaryOperator before2 = p1 -> {
            assertThat(p1).isEqualTo((short)81);
            beforeCalls.incrementAndGet();
            return (short)91;
        };

        //when
        LObjShortFunction<Integer ,Integer > function = sutO.objShortFuncComposeShort(before1,before2);
        function.doApply((Integer )Integer.valueOf(80),(short)81);

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(beforeCalls.get()).isEqualTo(2);
    }


    @Test
    public void testobjShortFuncCompose() throws X {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LObjShortFunction<Integer ,Integer > sutO = (Integer a1,short a2) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo((T)Integer.valueOf(90));
                assertThat(a2).isEqualTo((short)91);
                return 9;
        };

        LFunction<Integer ,Integer > before1 = p0 -> {
            assertThat(p0).isEqualTo(Integer.valueOf(80));
            beforeCalls.incrementAndGet();
            return 90;
        };
        LToShortFunction<Integer > before2 = p1 -> {
            assertThat(p1).isEqualTo(Integer.valueOf(81));
            beforeCalls.incrementAndGet();
            return (short)91;
        };

        //when
        LBiFunction<Integer ,Integer ,Integer > function = sutO.objShortFuncCompose(before1,before2);
        function.doApply((Integer )Integer.valueOf(80),(Integer )Integer.valueOf(81));

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
        LObjShortFunction<Integer ,Integer > sutO = (Integer a1,short a2) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo((T)Integer.valueOf(80));
                assertThat(a2).isEqualTo((short)81);
                return Integer.valueOf(90);
        };

        LFunction<Integer ,Integer > thenFunction = p -> {
                thenFunctionCalled.set(true);
                // R
                assertThat(p).isEqualTo(Integer.valueOf(90));
                // V
                return Integer.valueOf(100);
        };

        //when
        LObjShortFunction<Integer ,Integer > function = sutO.then(thenFunction);
        Integer  finalValue = function.doApply((Integer )Integer.valueOf(80),(short)81);

        //then - finals
        assertThat(finalValue).isEqualTo(Integer.valueOf(100));
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    @Test
    public void testThen1() throws X  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);


        //given (+ some assertions)
        LObjShortFunction<Integer ,Integer > sutO = (Integer a1,short a2) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo((T)Integer.valueOf(80));
                assertThat(a2).isEqualTo((short)81);
                return Integer.valueOf(90);
        };

        LConsumer<Integer > thenFunction = p -> {
                thenFunctionCalled.set(true);
                // R
                assertThat(p).isEqualTo(Integer.valueOf(90));
        };

        //when
        LObjShortConsumer<Integer > function = sutO.then(thenFunction);
        function.doAccept((Integer )Integer.valueOf(80),(short)81);

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    // </editor-fold>

    @Test
    public void testNesting() {
        assertThat(sut.nestingObjShortFunc())
            .isSameAs(sut)
            .isInstanceOf(LObjShortFunction.class);
    }

    @Test
    public void testShoving() {
        assertThat(sut.shovingObjShortFunc())
            .isSameAs(sut)
            .isInstanceOf(LObjShortFunction.class);
    }

    @Test
    public void testNestingX() {
        assertThat(sut.nestingObjShortFuncX())
            .isSameAs(sut)
            .isInstanceOf(LObjShortFunctionX.class);
    }

    @Test
    public void testShovingX() {
        assertThat(sut.shovingObjShortFuncX())
            .isSameAs(sut)
            .isInstanceOf(LObjShortFunctionX.class);
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void testShove() {

        // given
        LObjShortFunction<T,R> sutThrowing = LObjShortFunction.l((T a1,short a2) -> {
            throw new UnsupportedOperationException();
        });

        // when
        sutThrowing.shovingObjShortFunc().doApply((T)Integer.valueOf(100),(short)100);
    }

    @Test
    public void testHandleObjShortFunc() throws X {

        // given
        LObjShortFunction<T,R> sutThrowing = LObjShortFunction.l((T a1,short a2) -> {
            throw new UnsupportedOperationException();
        });

        // when
        LObjShortFunction<T,R> wrapped = sutThrowing.handleObjShortFunc(h -> {
            h.wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED);
        });

        // then
        try {
            wrapped.doApply((T)Integer.valueOf(100),(short)100);
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
                .contains("LObjShortFunction: R doApply(T a1,short a2)");
    }


    @Test
    public void isThrowing() {
        assertThat(sut.isThrowing())
            .isFalse();
    }

    //<editor-fold desc="Variants">

    private R variant1(short a2,T a1) {
        return (R)Integer.valueOf(100);
    }

    @Test
    public void compilerSubstituteVariant1() {
        LObjShortFunction lambda = LObjShortFunction./*<T,R>*/l1(this::variant1);

        assertThat(lambda).isInstanceOf(LObjShortFunction.V1.class);
    }

    //</editor-fold>


    @Test void safeCompiles() {
        LObjShortFunction r1 = LObjShortFunction.safe(sut); //NOSONAR
        LObjShortFunctionX r2 = LObjShortFunction.safe(sut); //NOSONAR
    }

    @Test void safePropagates() {
        Object result = LObjShortFunction.safe(sut);
        assertThat(result).isSameAs(sut);
    }

    @Test void safeProtectsAgainstNpe() {
        Object result = LObjShortFunction.safe(null);
        assertThat(result).isSameAs(LObjShortFunction.l(LObjShortFunction.safe()));
    }

    @Test  void safeSupplierPropagates() {
        LSupplier<LObjShortFunction<T,R>> supplier = ()->sut;
        Object result = LObjShortFunction.safeSupplier(supplier);
        assertThat(result).isSameAs(supplier);
    }

    @Test  void safeSupplierProtectsAgainstNpe() {
        Object result = LObjShortFunction.safeSupplier(null);
        assertThat(result).isSameAs(LObjShortFunction.safeSupplier());
    }

    @Test  void safeSupplierCompiles() {
        LSupplier<LObjShortFunction<T,R>> r1 = LObjShortFunction.safeSupplier(()->sut);  //NOSONAR
    }

}
