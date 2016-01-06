/*
 * This file is part of "lunisolar-magma".
 *
 * (C) Copyright 2015 Lunisolar (http://lunisolar.eu/).
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
public class LObjByteFunctionTest<T,R,X extends ParseException> {
    private static final String ORIGINAL_MESSAGE = "Original message";
    private static final String EXCEPTION_WAS_WRAPPED = "Exception was wrapped.";
    private static final String NO_EXCEPTION_WERE_THROWN = "No exception were thrown.";

    private Object  testValue = (R)Integer.valueOf(100);



    private LObjByteFunction<T,R> sut = new LObjByteFunction(){
        public @Nullable Object  doApply(Object a1,byte a2)  {
            return testValue;
        }
    };

    private LObjByteFunctionX<T,R,X> opposite = new LObjByteFunctionX(){
        public @Nullable Object  doApply(Object a1,byte a2) throws ParseException {
            return testValue;
        }
    };

    private LObjByteFunction<T,R> sutNull = new LObjByteFunction(){
        public @Nullable Object  doApply(Object a1,byte a2)  {
            return null;
        }
    };




    private LObjByteFunction<T,R> sutAlwaysThrowingUnckeck = LObjByteFunction.l((T a1,byte a2) -> {
            throw new IndexOutOfBoundsException(ORIGINAL_MESSAGE);
    });


    @Test
    public void testTheResult() throws X {
        assertThat(sut.doApply((T)Integer.valueOf(100),(byte)100))
            .isSameAs(testValue);
    }

    @Test
    public void testTupleCall() throws X {

        LObjBytePair<T> domainObject = Tuple4U.tuple((T)Integer.valueOf(100),(byte)100);

        Object result = sut.tupleApply(domainObject);

        assertThat(result)
            .isSameAs(testValue);
    }

    @Test
    public void testNonNullDoApply() throws X {
        assertThat(sut.nonNullDoApply((T)Integer.valueOf(100),(byte)100))
            .isSameAs(testValue);
    }

    @Test
    public void testNestingDoApplyUnckeck() throws X {

        // then
        try {
            sutAlwaysThrowingUnckeck.nestingDoApply((T)Integer.valueOf(100),(byte)100);
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
            sutAlwaysThrowingUnckeck.shovingDoApply((T)Integer.valueOf(100),(byte)100);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                    .hasNoCause()
                    .hasMessage(ORIGINAL_MESSAGE);
        }
    }

    @Test(expectedExceptions=NullPointerException.class, expectedExceptionsMessageRegExp="\\QEvaluated value by nonNullDoApply() method cannot be null (LObjByteFunction: R doApply(T a1,byte a2)).\\E")
    public void testNonNullCapturesNull() throws X {
        sutNull.nonNullDoApply((T)Integer.valueOf(100),(byte)100);
    }


    @Test
    public void testFunctionalInterfaceDescription() throws X {
        assertThat(sut.functionalInterfaceDescription())
            .isEqualTo("LObjByteFunction: R doApply(T a1,byte a2)");
    }

    @Test
    public void testLMethod() throws X {
        assertThat(LObjByteFunction.l((Object a1,byte a2) -> testValue ))
            .isInstanceOf(LObjByteFunction.class);
    }

    @Test
    public void testWrapMethod() throws X {
        assertThat(LObjByteFunction.wrap(opposite))
            .isInstanceOf(LObjByteFunction.class);
    }

    @Test
    public void testWrapMethodDoNotWrapsRuntimeException() throws X {
        // given
        LObjByteFunctionX<T,R,X> sutThrowing = LObjByteFunctionX.lX((T a1,byte a2) -> {
            throw new UnsupportedOperationException(ORIGINAL_MESSAGE);
        });

        // when
        LObjByteFunction<T,R> wrapped = LObjByteFunction.wrap(sutThrowing);

        // then
        try {
            wrapped.doApply((T)Integer.valueOf(100),(byte)100);
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
        LObjByteFunctionX<T,R,ParseException> sutThrowing = LObjByteFunctionX.lX((T a1,byte a2) -> {
            throw new ParseException(ORIGINAL_MESSAGE, 0);
        });

        // when
        LObjByteFunction<T,R> wrapped = LObjByteFunction.wrap(sutThrowing);

        // then
        try {
            wrapped.doApply((T)Integer.valueOf(100),(byte)100);
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
        LObjByteFunction<T,R> sutThrowing = LObjByteFunction.l((T a1,byte a2) -> {
            throw new UnsupportedOperationException();
        });

        // when
        LObjByteFunction<T,R> wrapped = sutThrowing.handleObjByteFunc(handler -> handler
            .wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED));

        // then
        try {
            wrapped.doApply((T)Integer.valueOf(100),(byte)100);
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
        LObjByteFunction<T,R> sutThrowing = LObjByteFunction.l((T a1,byte a2) -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LObjByteFunction<T,R> wrapped = sutThrowing.handleObjByteFunc(handler -> handler
                .wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED)
                .throwIf(IndexOutOfBoundsException.class));

        // then
        try {
            wrapped.doApply((T)Integer.valueOf(100),(byte)100);
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
        LObjByteFunction<T,R> sutThrowing = LObjByteFunction.l((T a1,byte a2) -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LObjByteFunction<T,R> wrapped = sutThrowing.handleObjByteFunc(handler -> handler
                .wrapWhen(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED)
                .throwIf(IndexOutOfBoundsException.class));

        // then
        try {
            wrapped.doApply((T)Integer.valueOf(100),(byte)100);
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
        LObjByteFunction<T,R> sutThrowing = LObjByteFunction.l((T a1,byte a2) -> {
            throw new UnsupportedOperationException(ORIGINAL_MESSAGE);
        });

        // when
        LObjByteFunction<T,R> wrapped = sutThrowing.handleObjByteFunc(h -> Function4U.doNothing());

        // then
        try {
            wrapped.doApply((T)Integer.valueOf(100),(byte)100);
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
    public void testobjByteFuncComposeByte() throws X {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LObjByteFunction<Integer ,Integer > sutO = (Integer a1,byte a2) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo((T)Integer.valueOf(90));
                assertThat(a2).isEqualTo((byte)91);
                return 9;
        };

        LFunction<Integer ,Integer > before1 = p0 -> {
            assertThat(p0).isEqualTo((T)Integer.valueOf(80));
            beforeCalls.incrementAndGet();
            return 90;
        };
        LByteUnaryOperator before2 = p1 -> {
            assertThat(p1).isEqualTo((byte)81);
            beforeCalls.incrementAndGet();
            return (byte)91;
        };

        //when
        LObjByteFunction<Integer ,Integer > function = sutO.objByteFuncComposeByte(before1,before2);
        function.doApply((Integer )Integer.valueOf(80),(byte)81);

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(beforeCalls.get()).isEqualTo(2);
    }


    @Test
    public void testobjByteFuncCompose() throws X {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LObjByteFunction<Integer ,Integer > sutO = (Integer a1,byte a2) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo((T)Integer.valueOf(90));
                assertThat(a2).isEqualTo((byte)91);
                return 9;
        };

        LFunction<Integer ,Integer > before1 = p0 -> {
            assertThat(p0).isEqualTo(Integer.valueOf(80));
            beforeCalls.incrementAndGet();
            return 90;
        };
        LToByteFunction<Integer > before2 = p1 -> {
            assertThat(p1).isEqualTo(Integer.valueOf(81));
            beforeCalls.incrementAndGet();
            return (byte)91;
        };

        //when
        LBiFunction<Integer ,Integer ,Integer > function = sutO.objByteFuncCompose(before1,before2);
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
        LObjByteFunction<Integer ,Integer > sutO = (Integer a1,byte a2) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo((T)Integer.valueOf(80));
                assertThat(a2).isEqualTo((byte)81);
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
        LObjByteFunction<Integer ,Integer > function = sutO.then(thenFunction);
        Integer  finalValue = function.doApply((Integer )Integer.valueOf(80),(byte)81);

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
        LObjByteFunction<Integer ,Integer > sutO = (Integer a1,byte a2) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo((T)Integer.valueOf(80));
                assertThat(a2).isEqualTo((byte)81);
                return Integer.valueOf(90);
        };

        LConsumer<Integer > thenFunction = p -> {
                thenFunctionCalled.set(true);
                // R
                assertThat(p).isEqualTo(Integer.valueOf(90));
        };

        //when
        LObjByteConsumer<Integer > function = sutO.then(thenFunction);
        function.doAccept((Integer )Integer.valueOf(80),(byte)81);

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    // </editor-fold>

    @Test
    public void testNesting() {
        assertThat(sut.nestingObjByteFunc())
            .isSameAs(sut)
            .isInstanceOf(LObjByteFunction.class);
    }

    @Test
    public void testShoving() {
        assertThat(sut.shovingObjByteFunc())
            .isSameAs(sut)
            .isInstanceOf(LObjByteFunction.class);
    }

    @Test
    public void testNestingX() {
        assertThat(sut.nestingObjByteFuncX())
            .isSameAs(sut)
            .isInstanceOf(LObjByteFunctionX.class);
    }

    @Test
    public void testShovingX() {
        assertThat(sut.shovingObjByteFuncX())
            .isSameAs(sut)
            .isInstanceOf(LObjByteFunctionX.class);
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void testShove() {

        // given
        LObjByteFunction<T,R> sutThrowing = LObjByteFunction.l((T a1,byte a2) -> {
            throw new UnsupportedOperationException();
        });

        // when
        sutThrowing.shovingObjByteFunc().doApply((T)Integer.valueOf(100),(byte)100);
    }

    @Test
    public void testHandleObjByteFunc() throws X {

        // given
        LObjByteFunction<T,R> sutThrowing = LObjByteFunction.l((T a1,byte a2) -> {
            throw new UnsupportedOperationException();
        });

        // when
        LObjByteFunction<T,R> wrapped = sutThrowing.handleObjByteFunc(h -> {
            h.wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED);
        });

        // then
        try {
            wrapped.doApply((T)Integer.valueOf(100),(byte)100);
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
                .contains("LObjByteFunction: R doApply(T a1,byte a2)");
    }


    @Test
    public void isThrowing() {
        assertThat(sut.isThrowing())
            .isFalse();
    }

    //<editor-fold desc="Variants">

    private R variant1(byte a2,T a1) {
        return (R)Integer.valueOf(100);
    }

    @Test
    public void compilerSubstituteVariant1() {
        LObjByteFunction lambda = LObjByteFunction./*<T,R>*/l1(this::variant1);

        assertThat(lambda).isInstanceOf(LObjByteFunction.V1.class);
    }

    //</editor-fold>


    @Test void safeCompiles() {
        LObjByteFunction r1 = LObjByteFunction.safe(sut); //NOSONAR
        LObjByteFunctionX r2 = LObjByteFunction.safe(sut); //NOSONAR
    }

    @Test void safePropagates() {
        Object result = LObjByteFunction.safe(sut);
        assertThat(result).isSameAs(sut);
    }

    @Test void safeProtectsAgainstNpe() {
        Object result = LObjByteFunction.safe(null);
        assertThat(result).isSameAs(LObjByteFunction.l(LObjByteFunction.safe()));
    }

    @Test  void safeSupplierPropagates() {
        LSupplier<LObjByteFunction<T,R>> supplier = ()->sut;
        Object result = LObjByteFunction.safeSupplier(supplier);
        assertThat(result).isSameAs(supplier);
    }

    @Test  void safeSupplierProtectsAgainstNpe() {
        Object result = LObjByteFunction.safeSupplier(null);
        assertThat(result).isSameAs(LObjByteFunction.safeSupplier());
    }

    @Test  void safeSupplierCompiles() {
        LSupplier<LObjByteFunction<T,R>> r1 = LObjByteFunction.safeSupplier(()->sut);  //NOSONAR
    }

}
