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
public class LBiObjDoubleFunctionTest<T1,T2,R,X extends ParseException> {
    private static final String ORIGINAL_MESSAGE = "Original message";
    private static final String EXCEPTION_WAS_WRAPPED = "Exception was wrapped.";
    private static final String NO_EXCEPTION_WERE_THROWN = "No exception were thrown.";

    private Object  testValue = (R)Integer.valueOf(100);



    private LBiObjDoubleFunction<T1,T2,R> sut = new LBiObjDoubleFunction(){
        public @Nullable Object  doApply(Object a1,Object a2,double a3)  {
            return testValue;
        }
    };

    private LBiObjDoubleFunctionX<T1,T2,R,X> opposite = new LBiObjDoubleFunctionX(){
        public @Nullable Object  doApply(Object a1,Object a2,double a3) throws ParseException {
            return testValue;
        }
    };

    private LBiObjDoubleFunction<T1,T2,R> sutNull = new LBiObjDoubleFunction(){
        public @Nullable Object  doApply(Object a1,Object a2,double a3)  {
            return null;
        }
    };




    private LBiObjDoubleFunction<T1,T2,R> sutAlwaysThrowingUnckeck = LBiObjDoubleFunction.l((T1 a1,T2 a2,double a3) -> {
            throw new IndexOutOfBoundsException(ORIGINAL_MESSAGE);
    });


    @Test
    public void testTheResult() throws X {
        assertThat(sut.doApply((T1)Integer.valueOf(100),(T2)Integer.valueOf(100),(double)100))
            .isSameAs(testValue);
    }

    @Test
    public void testTupleCall() throws X {

        LBiObjDoubleTriple<T1,T2> domainObject = Tuple4U.tuple((T1)Integer.valueOf(100),(T2)Integer.valueOf(100),(double)100);

        Object result = sut.tupleApply(domainObject);

        assertThat(result)
            .isSameAs(testValue);
    }

    @Test
    public void testNonNullDoApply() throws X {
        assertThat(sut.nonNullDoApply((T1)Integer.valueOf(100),(T2)Integer.valueOf(100),(double)100))
            .isSameAs(testValue);
    }

    @Test
    public void testNestingDoApplyUnckeck() throws X {

        // then
        try {
            sutAlwaysThrowingUnckeck.nestingDoApply((T1)Integer.valueOf(100),(T2)Integer.valueOf(100),(double)100);
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
            sutAlwaysThrowingUnckeck.shovingDoApply((T1)Integer.valueOf(100),(T2)Integer.valueOf(100),(double)100);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                    .hasNoCause()
                    .hasMessage(ORIGINAL_MESSAGE);
        }
    }

    @Test(expectedExceptions=NullPointerException.class, expectedExceptionsMessageRegExp="\\QEvaluated value by nonNullDoApply() method cannot be null (LBiObjDoubleFunction: R doApply(T1 a1,T2 a2,double a3)).\\E")
    public void testNonNullCapturesNull() throws X {
        sutNull.nonNullDoApply((T1)Integer.valueOf(100),(T2)Integer.valueOf(100),(double)100);
    }


    @Test
    public void testFunctionalInterfaceDescription() throws X {
        assertThat(sut.functionalInterfaceDescription())
            .isEqualTo("LBiObjDoubleFunction: R doApply(T1 a1,T2 a2,double a3)");
    }

    @Test
    public void testLMethod() throws X {
        assertThat(LBiObjDoubleFunction.l((Object a1,Object a2,double a3) -> testValue ))
            .isInstanceOf(LBiObjDoubleFunction.class);
    }

    @Test
    public void testWrapMethod() throws X {
        assertThat(LBiObjDoubleFunction.wrap(opposite))
            .isInstanceOf(LBiObjDoubleFunction.class);
    }

    @Test
    public void testWrapMethodDoNotWrapsRuntimeException() throws X {
        // given
        LBiObjDoubleFunctionX<T1,T2,R,X> sutThrowing = LBiObjDoubleFunctionX.lX((T1 a1,T2 a2,double a3) -> {
            throw new UnsupportedOperationException(ORIGINAL_MESSAGE);
        });

        // when
        LBiObjDoubleFunction<T1,T2,R> wrapped = LBiObjDoubleFunction.wrap(sutThrowing);

        // then
        try {
            wrapped.doApply((T1)Integer.valueOf(100),(T2)Integer.valueOf(100),(double)100);
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
        LBiObjDoubleFunctionX<T1,T2,R,ParseException> sutThrowing = LBiObjDoubleFunctionX.lX((T1 a1,T2 a2,double a3) -> {
            throw new ParseException(ORIGINAL_MESSAGE, 0);
        });

        // when
        LBiObjDoubleFunction<T1,T2,R> wrapped = LBiObjDoubleFunction.wrap(sutThrowing);

        // then
        try {
            wrapped.doApply((T1)Integer.valueOf(100),(T2)Integer.valueOf(100),(double)100);
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
        LBiObjDoubleFunction<T1,T2,R> sutThrowing = LBiObjDoubleFunction.l((T1 a1,T2 a2,double a3) -> {
            throw new UnsupportedOperationException();
        });

        // when
        LBiObjDoubleFunction<T1,T2,R> wrapped = sutThrowing.handleBiObjDoubleFunc(handler -> handler
            .wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED));

        // then
        try {
            wrapped.doApply((T1)Integer.valueOf(100),(T2)Integer.valueOf(100),(double)100);
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
        LBiObjDoubleFunction<T1,T2,R> sutThrowing = LBiObjDoubleFunction.l((T1 a1,T2 a2,double a3) -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LBiObjDoubleFunction<T1,T2,R> wrapped = sutThrowing.handleBiObjDoubleFunc(handler -> handler
                .wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED)
                .throwIf(IndexOutOfBoundsException.class));

        // then
        try {
            wrapped.doApply((T1)Integer.valueOf(100),(T2)Integer.valueOf(100),(double)100);
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
        LBiObjDoubleFunction<T1,T2,R> sutThrowing = LBiObjDoubleFunction.l((T1 a1,T2 a2,double a3) -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LBiObjDoubleFunction<T1,T2,R> wrapped = sutThrowing.handleBiObjDoubleFunc(handler -> handler
                .wrapWhen(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED)
                .throwIf(IndexOutOfBoundsException.class));

        // then
        try {
            wrapped.doApply((T1)Integer.valueOf(100),(T2)Integer.valueOf(100),(double)100);
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
        LBiObjDoubleFunction<T1,T2,R> sutThrowing = LBiObjDoubleFunction.l((T1 a1,T2 a2,double a3) -> {
            throw new UnsupportedOperationException(ORIGINAL_MESSAGE);
        });

        // when
        LBiObjDoubleFunction<T1,T2,R> wrapped = sutThrowing.handleBiObjDoubleFunc(h -> Function4U.doNothing());

        // then
        try {
            wrapped.doApply((T1)Integer.valueOf(100),(T2)Integer.valueOf(100),(double)100);
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
    public void testbiObjDoubleFuncComposeDouble() throws X {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LBiObjDoubleFunction<Integer ,Integer ,Integer > sutO = (Integer a1,Integer a2,double a3) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo((T1)Integer.valueOf(90));
                assertThat(a2).isEqualTo((T2)Integer.valueOf(91));
                assertThat(a3).isEqualTo((double)92);
                return 9;
        };

        LFunction<Integer ,Integer > before1 = p0 -> {
            assertThat(p0).isEqualTo((T1)Integer.valueOf(80));
            beforeCalls.incrementAndGet();
            return 90;
        };
        LFunction<Integer ,Integer > before2 = p1 -> {
            assertThat(p1).isEqualTo((T2)Integer.valueOf(81));
            beforeCalls.incrementAndGet();
            return 91;
        };
        LDoubleUnaryOperator before3 = p2 -> {
            assertThat(p2).isEqualTo((double)82);
            beforeCalls.incrementAndGet();
            return (double)92;
        };

        //when
        LBiObjDoubleFunction<Integer ,Integer ,Integer > function = sutO.biObjDoubleFuncComposeDouble(before1,before2,before3);
        function.doApply((Integer )Integer.valueOf(80),(Integer )Integer.valueOf(81),(double)82);

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(beforeCalls.get()).isEqualTo(3);
    }


    @Test
    public void testbiObjDoubleFuncCompose() throws X {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LBiObjDoubleFunction<Integer ,Integer ,Integer > sutO = (Integer a1,Integer a2,double a3) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo((T1)Integer.valueOf(90));
                assertThat(a2).isEqualTo((T2)Integer.valueOf(91));
                assertThat(a3).isEqualTo((double)92);
                return 9;
        };

        LFunction<Integer ,Integer > before1 = p0 -> {
            assertThat(p0).isEqualTo(Integer.valueOf(80));
            beforeCalls.incrementAndGet();
            return 90;
        };
        LFunction<Integer ,Integer > before2 = p1 -> {
            assertThat(p1).isEqualTo(Integer.valueOf(81));
            beforeCalls.incrementAndGet();
            return 91;
        };
        LToDoubleFunction<Integer > before3 = p2 -> {
            assertThat(p2).isEqualTo(Integer.valueOf(82));
            beforeCalls.incrementAndGet();
            return (double)92;
        };

        //when
        LTriFunction<Integer ,Integer ,Integer ,Integer > function = sutO.biObjDoubleFuncCompose(before1,before2,before3);
        function.doApply((Integer )Integer.valueOf(80),(Integer )Integer.valueOf(81),(Integer )Integer.valueOf(82));

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
        LBiObjDoubleFunction<Integer ,Integer ,Integer > sutO = (Integer a1,Integer a2,double a3) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo((T1)Integer.valueOf(80));
                assertThat(a2).isEqualTo((T2)Integer.valueOf(81));
                assertThat(a3).isEqualTo((double)82);
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
        LBiObjDoubleFunction<Integer ,Integer ,Integer > function = sutO.then(thenFunction);
        Integer  finalValue = function.doApply((Integer )Integer.valueOf(80),(Integer )Integer.valueOf(81),(double)82);

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
        LBiObjDoubleFunction<Integer ,Integer ,Integer > sutO = (Integer a1,Integer a2,double a3) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo((T1)Integer.valueOf(80));
                assertThat(a2).isEqualTo((T2)Integer.valueOf(81));
                assertThat(a3).isEqualTo((double)82);
                return Integer.valueOf(90);
        };

        LConsumer<Integer > thenFunction = p -> {
                thenFunctionCalled.set(true);
                // R
                assertThat(p).isEqualTo(Integer.valueOf(90));
        };

        //when
        LBiObjDoubleConsumer<Integer ,Integer > function = sutO.then(thenFunction);
        function.doAccept((Integer )Integer.valueOf(80),(Integer )Integer.valueOf(81),(double)82);

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    // </editor-fold>

    @Test
    public void testNesting() {
        assertThat(sut.nestingBiObjDoubleFunc())
            .isSameAs(sut)
            .isInstanceOf(LBiObjDoubleFunction.class);
    }

    @Test
    public void testShoving() {
        assertThat(sut.shovingBiObjDoubleFunc())
            .isSameAs(sut)
            .isInstanceOf(LBiObjDoubleFunction.class);
    }

    @Test
    public void testNestingX() {
        assertThat(sut.nestingBiObjDoubleFuncX())
            .isSameAs(sut)
            .isInstanceOf(LBiObjDoubleFunctionX.class);
    }

    @Test
    public void testShovingX() {
        assertThat(sut.shovingBiObjDoubleFuncX())
            .isSameAs(sut)
            .isInstanceOf(LBiObjDoubleFunctionX.class);
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void testShove() {

        // given
        LBiObjDoubleFunction<T1,T2,R> sutThrowing = LBiObjDoubleFunction.l((T1 a1,T2 a2,double a3) -> {
            throw new UnsupportedOperationException();
        });

        // when
        sutThrowing.shovingBiObjDoubleFunc().doApply((T1)Integer.valueOf(100),(T2)Integer.valueOf(100),(double)100);
    }

    @Test
    public void testHandleBiObjDoubleFunc() throws X {

        // given
        LBiObjDoubleFunction<T1,T2,R> sutThrowing = LBiObjDoubleFunction.l((T1 a1,T2 a2,double a3) -> {
            throw new UnsupportedOperationException();
        });

        // when
        LBiObjDoubleFunction<T1,T2,R> wrapped = sutThrowing.handleBiObjDoubleFunc(h -> {
            h.wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED);
        });

        // then
        try {
            wrapped.doApply((T1)Integer.valueOf(100),(T2)Integer.valueOf(100),(double)100);
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
                .contains("LBiObjDoubleFunction: R doApply(T1 a1,T2 a2,double a3)");
    }


    @Test
    public void isThrowing() {
        assertThat(sut.isThrowing())
            .isFalse();
    }

    //<editor-fold desc="Variants">

    private R variant1(T1 a1,double a3,T2 a2) {
        return (R)Integer.valueOf(100);
    }

    @Test
    public void compilerSubstituteVariant1() {
        LBiObjDoubleFunction lambda = LBiObjDoubleFunction./*<T1,T2,R>*/l1(this::variant1);

        assertThat(lambda).isInstanceOf(LBiObjDoubleFunction.V1.class);
    }


    private R variant2(T2 a2,T1 a1,double a3) {
        return (R)Integer.valueOf(100);
    }

    @Test
    public void compilerSubstituteVariant2() {
        LBiObjDoubleFunction lambda = LBiObjDoubleFunction./*<T1,T2,R>*/l2(this::variant2);

        assertThat(lambda).isInstanceOf(LBiObjDoubleFunction.V2.class);
    }


    private R variant3(T2 a2,double a3,T1 a1) {
        return (R)Integer.valueOf(100);
    }

    @Test
    public void compilerSubstituteVariant3() {
        LBiObjDoubleFunction lambda = LBiObjDoubleFunction./*<T1,T2,R>*/l3(this::variant3);

        assertThat(lambda).isInstanceOf(LBiObjDoubleFunction.V3.class);
    }


    private R variant4(double a3,T1 a1,T2 a2) {
        return (R)Integer.valueOf(100);
    }

    @Test
    public void compilerSubstituteVariant4() {
        LBiObjDoubleFunction lambda = LBiObjDoubleFunction./*<T1,T2,R>*/l4(this::variant4);

        assertThat(lambda).isInstanceOf(LBiObjDoubleFunction.V4.class);
    }


    private R variant5(double a3,T2 a2,T1 a1) {
        return (R)Integer.valueOf(100);
    }

    @Test
    public void compilerSubstituteVariant5() {
        LBiObjDoubleFunction lambda = LBiObjDoubleFunction./*<T1,T2,R>*/l5(this::variant5);

        assertThat(lambda).isInstanceOf(LBiObjDoubleFunction.V5.class);
    }

    //</editor-fold>


    @Test void safeCompiles() {
        LBiObjDoubleFunction r1 = LBiObjDoubleFunction.safe(sut); //NOSONAR
        LBiObjDoubleFunctionX r2 = LBiObjDoubleFunction.safe(sut); //NOSONAR
    }

    @Test void safePropagates() {
        Object result = LBiObjDoubleFunction.safe(sut);
        assertThat(result).isSameAs(sut);
    }

    @Test void safeProtectsAgainstNpe() {
        Object result = LBiObjDoubleFunction.safe(null);
        assertThat(result).isSameAs(LBiObjDoubleFunction.l(LBiObjDoubleFunction.safe()));
    }

    @Test  void safeSupplierPropagates() {
        LSupplier<LBiObjDoubleFunction<T1,T2,R>> supplier = ()->sut;
        Object result = LBiObjDoubleFunction.safeSupplier(supplier);
        assertThat(result).isSameAs(supplier);
    }

    @Test  void safeSupplierProtectsAgainstNpe() {
        Object result = LBiObjDoubleFunction.safeSupplier(null);
        assertThat(result).isSameAs(LBiObjDoubleFunction.safeSupplier());
    }

    @Test  void safeSupplierCompiles() {
        LSupplier<LBiObjDoubleFunction<T1,T2,R>> r1 = LBiObjDoubleFunction.safeSupplier(()->sut);  //NOSONAR
    }

}
