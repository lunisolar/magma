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
public class LToDoubleBiFunctionTest<T1,T2,X extends ParseException> {
    private static final String ORIGINAL_MESSAGE = "Original message";
    private static final String EXCEPTION_WAS_WRAPPED = "Exception was wrapped.";
    private static final String NO_EXCEPTION_WERE_THROWN = "No exception were thrown.";

    private double testValue = (double)100;



    private LToDoubleBiFunction<T1,T2> sut = new LToDoubleBiFunction(){
        public  double doApplyAsDouble(Object a1,Object a2)  {
            return testValue;
        }
    };

    private LToDoubleBiFunctionX<T1,T2,X> opposite = new LToDoubleBiFunctionX(){
        public  double doApplyAsDouble(Object a1,Object a2) throws ParseException {
            return testValue;
        }
    };


    private ToDoubleBiFunction jre = (Object a1,Object a2) -> testValue;



    private LToDoubleBiFunction<T1,T2> sutAlwaysThrowingUnckeck = LToDoubleBiFunction.l((T1 a1,T2 a2) -> {
            throw new IndexOutOfBoundsException(ORIGINAL_MESSAGE);
    });


    @Test
    public void testTheResult() throws X {
        assertThat(sut.doApplyAsDouble((T1)Integer.valueOf(100),(T2)Integer.valueOf(100)))
            .isEqualTo(testValue);
    }

    @Test
    public void testTupleCall() throws X {

        LPair<T1,T2> domainObject = Tuple4U.tuple((T1)Integer.valueOf(100),(T2)Integer.valueOf(100));

        Object result = sut.tupleApplyAsDouble(domainObject);

        assertThat(result)
            .isEqualTo(testValue);
    }

    @Test
    public void testNonNullDoApplyAsDouble() throws X {
        assertThat(sut.nonNullDoApplyAsDouble((T1)Integer.valueOf(100),(T2)Integer.valueOf(100)))
            .isEqualTo(testValue);
    }

    @Test
    public void testNestingDoApplyAsDoubleUnckeck() throws X {

        // then
        try {
            sutAlwaysThrowingUnckeck.nestingDoApplyAsDouble((T1)Integer.valueOf(100),(T2)Integer.valueOf(100));
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                    .hasNoCause()
                    .hasMessage(ORIGINAL_MESSAGE);
        }
    }

    @Test
    public void testShovingDoApplyAsDoubleUnckeck() throws X {

        // then
        try {
            sutAlwaysThrowingUnckeck.shovingDoApplyAsDouble((T1)Integer.valueOf(100),(T2)Integer.valueOf(100));
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
            .isEqualTo("LToDoubleBiFunction: double doApplyAsDouble(T1 a1,T2 a2)");
    }

    @Test
    public void testLMethod() throws X {
        assertThat(LToDoubleBiFunction.l((Object a1,Object a2) -> testValue ))
            .isInstanceOf(LToDoubleBiFunction.class);
    }

    @Test
    public void testWrapMethod() throws X {
        assertThat(LToDoubleBiFunction.wrap(opposite))
            .isInstanceOf(LToDoubleBiFunction.class);
    }

    @Test
    public void testWrapStdMethod() throws X {
        assertThat(LToDoubleBiFunction.wrap(jre))
            .isInstanceOf(LToDoubleBiFunction.class);
    }

    @Test
    public void testWrapMethodDoNotWrapsRuntimeException() throws X {
        // given
        LToDoubleBiFunctionX<T1,T2,X> sutThrowing = LToDoubleBiFunctionX.lX((T1 a1,T2 a2) -> {
            throw new UnsupportedOperationException(ORIGINAL_MESSAGE);
        });

        // when
        LToDoubleBiFunction<T1,T2> wrapped = LToDoubleBiFunction.wrap(sutThrowing);

        // then
        try {
            wrapped.doApplyAsDouble((T1)Integer.valueOf(100),(T2)Integer.valueOf(100));
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
        LToDoubleBiFunctionX<T1,T2,ParseException> sutThrowing = LToDoubleBiFunctionX.lX((T1 a1,T2 a2) -> {
            throw new ParseException(ORIGINAL_MESSAGE, 0);
        });

        // when
        LToDoubleBiFunction<T1,T2> wrapped = LToDoubleBiFunction.wrap(sutThrowing);

        // then
        try {
            wrapped.doApplyAsDouble((T1)Integer.valueOf(100),(T2)Integer.valueOf(100));
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
        LToDoubleBiFunction<T1,T2> sutThrowing = LToDoubleBiFunction.l((T1 a1,T2 a2) -> {
            throw new UnsupportedOperationException();
        });

        // when
        LToDoubleBiFunction<T1,T2> wrapped = sutThrowing.handleToDoubleBiFunc(handler -> handler
            .wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED));

        // then
        try {
            wrapped.doApplyAsDouble((T1)Integer.valueOf(100),(T2)Integer.valueOf(100));
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
        LToDoubleBiFunction<T1,T2> sutThrowing = LToDoubleBiFunction.l((T1 a1,T2 a2) -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LToDoubleBiFunction<T1,T2> wrapped = sutThrowing.handleToDoubleBiFunc(handler -> handler
                .wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED)
                .throwIf(IndexOutOfBoundsException.class));

        // then
        try {
            wrapped.doApplyAsDouble((T1)Integer.valueOf(100),(T2)Integer.valueOf(100));
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
        LToDoubleBiFunction<T1,T2> sutThrowing = LToDoubleBiFunction.l((T1 a1,T2 a2) -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LToDoubleBiFunction<T1,T2> wrapped = sutThrowing.handleToDoubleBiFunc(handler -> handler
                .wrapWhen(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED)
                .throwIf(IndexOutOfBoundsException.class));

        // then
        try {
            wrapped.doApplyAsDouble((T1)Integer.valueOf(100),(T2)Integer.valueOf(100));
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
        LToDoubleBiFunction<T1,T2> sutThrowing = LToDoubleBiFunction.l((T1 a1,T2 a2) -> {
            throw new UnsupportedOperationException(ORIGINAL_MESSAGE);
        });

        // when
        LToDoubleBiFunction<T1,T2> wrapped = sutThrowing.handleToDoubleBiFunc(h -> Function4U.doNothing());

        // then
        try {
            wrapped.doApplyAsDouble((T1)Integer.valueOf(100),(T2)Integer.valueOf(100));
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
    public void testtoDoubleBiFuncCompose() throws X {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LToDoubleBiFunction<Integer ,Integer > sutO = (Integer a1,Integer a2) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo((T1)Integer.valueOf(90));
                assertThat(a2).isEqualTo((T2)Integer.valueOf(91));
                return (double)100;
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

        //when
        LToDoubleBiFunction<Integer ,Integer > function = sutO.toDoubleBiFuncCompose(before1,before2);
        function.doApplyAsDouble((Integer )Integer.valueOf(80),(Integer )Integer.valueOf(81));

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
        LToDoubleBiFunction<Integer ,Integer > sutO = (Integer a1,Integer a2) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo((T1)Integer.valueOf(80));
                assertThat(a2).isEqualTo((T2)Integer.valueOf(81));
                return (double)90;
        };

        LDoubleFunction<Integer > thenFunction = p -> {
                thenFunctionCalled.set(true);
                // 
                assertThat(p).isEqualTo((double)90);
                // V
                return Integer.valueOf(100);
        };

        //when
        LBiFunction<Integer ,Integer ,Integer > function = sutO.then(thenFunction);
        Integer  finalValue = function.doApply((Integer )Integer.valueOf(80),(Integer )Integer.valueOf(81));

        //then - finals
        assertThat(finalValue).isEqualTo(Integer.valueOf(100));
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    // </editor-fold>

    @Test
    public void testNesting() {
        assertThat(sut.nestingToDoubleBiFunc())
            .isSameAs(sut)
            .isInstanceOf(LToDoubleBiFunction.class);
    }

    @Test
    public void testShoving() {
        assertThat(sut.shovingToDoubleBiFunc())
            .isSameAs(sut)
            .isInstanceOf(LToDoubleBiFunction.class);
    }

    @Test
    public void testNestingX() {
        assertThat(sut.nestingToDoubleBiFuncX())
            .isSameAs(sut)
            .isInstanceOf(LToDoubleBiFunctionX.class);
    }

    @Test
    public void testShovingX() {
        assertThat(sut.shovingToDoubleBiFuncX())
            .isSameAs(sut)
            .isInstanceOf(LToDoubleBiFunctionX.class);
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void testShove() {

        // given
        LToDoubleBiFunction<T1,T2> sutThrowing = LToDoubleBiFunction.l((T1 a1,T2 a2) -> {
            throw new UnsupportedOperationException();
        });

        // when
        sutThrowing.shovingToDoubleBiFunc().doApplyAsDouble((T1)Integer.valueOf(100),(T2)Integer.valueOf(100));
    }

    @Test
    public void testHandleToDoubleBiFunc() throws X {

        // given
        LToDoubleBiFunction<T1,T2> sutThrowing = LToDoubleBiFunction.l((T1 a1,T2 a2) -> {
            throw new UnsupportedOperationException();
        });

        // when
        LToDoubleBiFunction<T1,T2> wrapped = sutThrowing.handleToDoubleBiFunc(h -> {
            h.wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED);
        });

        // then
        try {
            wrapped.doApplyAsDouble((T1)Integer.valueOf(100),(T2)Integer.valueOf(100));
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
                .contains("LToDoubleBiFunction: double doApplyAsDouble(T1 a1,T2 a2)");
    }


    @Test
    public void isThrowing() {
        assertThat(sut.isThrowing())
            .isFalse();
    }

    //<editor-fold desc="Variants">

    private double variant1(T2 a2,T1 a1) {
        return (double)100;
    }

    @Test
    public void compilerSubstituteVariant1() {
        LToDoubleBiFunction lambda = LToDoubleBiFunction./*<T1,T2>*/l1(this::variant1);

        assertThat(lambda).isInstanceOf(LToDoubleBiFunction.V1.class);
    }

    //</editor-fold>


    @Test void safeCompiles() {
        LToDoubleBiFunction r1 = LToDoubleBiFunction.safe(sut); //NOSONAR
        LToDoubleBiFunctionX r2 = LToDoubleBiFunction.safe(sut); //NOSONAR
        ToDoubleBiFunction r3 = LToDoubleBiFunction.safe(sut); //NOSONAR
    }

    @Test void safePropagates() {
        Object result = LToDoubleBiFunction.safe(sut);
        assertThat(result).isSameAs(sut);
    }

    @Test void safeProtectsAgainstNpe() {
        Object result = LToDoubleBiFunction.safe(null);
        assertThat(result).isSameAs(LToDoubleBiFunction.l(LToDoubleBiFunction.safe()));
    }

    @Test  void safeSupplierPropagates() {
        LSupplier<LToDoubleBiFunction<T1,T2>> supplier = ()->sut;
        Object result = LToDoubleBiFunction.safeSupplier(supplier);
        assertThat(result).isSameAs(supplier);
    }

    @Test  void safeSupplierProtectsAgainstNpe() {
        Object result = LToDoubleBiFunction.safeSupplier(null);
        assertThat(result).isSameAs(LToDoubleBiFunction.safeSupplier());
    }

    @Test  void safeSupplierCompiles() {
        LSupplier<LToDoubleBiFunction<T1,T2>> r1 = LToDoubleBiFunction.safeSupplier(()->sut);  //NOSONAR
        Supplier<LToDoubleBiFunction<T1,T2>> r2 = LToDoubleBiFunction.safeSupplier(()->sut); //NOSONAR
    }

}
