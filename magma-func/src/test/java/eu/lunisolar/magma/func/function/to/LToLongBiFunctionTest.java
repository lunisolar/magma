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
public class LToLongBiFunctionTest<T1,T2,X extends ParseException> {
    private static final String ORIGINAL_MESSAGE = "Original message";
    private static final String EXCEPTION_WAS_WRAPPED = "Exception was wrapped.";
    private static final String NO_EXCEPTION_WERE_THROWN = "No exception were thrown.";

    private long testValue = (long)100;



    private LToLongBiFunction<T1,T2> sut = new LToLongBiFunction(){
        public  long doApplyAsLong(Object a1,Object a2)  {
            return testValue;
        }
    };

    private LToLongBiFunctionX<T1,T2,X> opposite = new LToLongBiFunctionX(){
        public  long doApplyAsLong(Object a1,Object a2) throws ParseException {
            return testValue;
        }
    };


    private ToLongBiFunction jre = (Object a1,Object a2) -> testValue;



    private LToLongBiFunction<T1,T2> sutAlwaysThrowingUnckeck = LToLongBiFunction.l((T1 a1,T2 a2) -> {
            throw new IndexOutOfBoundsException(ORIGINAL_MESSAGE);
    });


    @Test
    public void testTheResult() throws X {
        assertThat(sut.doApplyAsLong((T1)Integer.valueOf(100),(T2)Integer.valueOf(100)))
            .isEqualTo(testValue);
    }

    @Test
    public void testTupleCall() throws X {

        LPair<T1,T2> domainObject = Tuple4U.tuple((T1)Integer.valueOf(100),(T2)Integer.valueOf(100));

        Object result = sut.tupleApplyAsLong(domainObject);

        assertThat(result)
            .isEqualTo(testValue);
    }

    @Test
    public void testNonNullDoApplyAsLong() throws X {
        assertThat(sut.nonNullDoApplyAsLong((T1)Integer.valueOf(100),(T2)Integer.valueOf(100)))
            .isEqualTo(testValue);
    }

    @Test
    public void testNestingDoApplyAsLongUnckeck() throws X {

        // then
        try {
            sutAlwaysThrowingUnckeck.nestingDoApplyAsLong((T1)Integer.valueOf(100),(T2)Integer.valueOf(100));
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                    .hasNoCause()
                    .hasMessage(ORIGINAL_MESSAGE);
        }
    }

    @Test
    public void testShovingDoApplyAsLongUnckeck() throws X {

        // then
        try {
            sutAlwaysThrowingUnckeck.shovingDoApplyAsLong((T1)Integer.valueOf(100),(T2)Integer.valueOf(100));
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
            .isEqualTo("LToLongBiFunction: long doApplyAsLong(T1 a1,T2 a2)");
    }

    @Test
    public void testLMethod() throws X {
        assertThat(LToLongBiFunction.l((Object a1,Object a2) -> testValue ))
            .isInstanceOf(LToLongBiFunction.class);
    }

    @Test
    public void testWrapMethod() throws X {
        assertThat(LToLongBiFunction.wrap(opposite))
            .isInstanceOf(LToLongBiFunction.class);
    }

    @Test
    public void testWrapStdMethod() throws X {
        assertThat(LToLongBiFunction.wrap(jre))
            .isInstanceOf(LToLongBiFunction.class);
    }

    @Test
    public void testWrapMethodDoNotWrapsRuntimeException() throws X {
        // given
        LToLongBiFunctionX<T1,T2,X> sutThrowing = LToLongBiFunctionX.lX((T1 a1,T2 a2) -> {
            throw new UnsupportedOperationException(ORIGINAL_MESSAGE);
        });

        // when
        LToLongBiFunction<T1,T2> wrapped = LToLongBiFunction.wrap(sutThrowing);

        // then
        try {
            wrapped.doApplyAsLong((T1)Integer.valueOf(100),(T2)Integer.valueOf(100));
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
        LToLongBiFunctionX<T1,T2,ParseException> sutThrowing = LToLongBiFunctionX.lX((T1 a1,T2 a2) -> {
            throw new ParseException(ORIGINAL_MESSAGE, 0);
        });

        // when
        LToLongBiFunction<T1,T2> wrapped = LToLongBiFunction.wrap(sutThrowing);

        // then
        try {
            wrapped.doApplyAsLong((T1)Integer.valueOf(100),(T2)Integer.valueOf(100));
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
        LToLongBiFunction<T1,T2> sutThrowing = LToLongBiFunction.l((T1 a1,T2 a2) -> {
            throw new UnsupportedOperationException();
        });

        // when
        LToLongBiFunction<T1,T2> wrapped = sutThrowing.handleToLongBiFunc(handler -> handler
            .wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED));

        // then
        try {
            wrapped.doApplyAsLong((T1)Integer.valueOf(100),(T2)Integer.valueOf(100));
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
        LToLongBiFunction<T1,T2> sutThrowing = LToLongBiFunction.l((T1 a1,T2 a2) -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LToLongBiFunction<T1,T2> wrapped = sutThrowing.handleToLongBiFunc(handler -> handler
                .wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED)
                .throwIf(IndexOutOfBoundsException.class));

        // then
        try {
            wrapped.doApplyAsLong((T1)Integer.valueOf(100),(T2)Integer.valueOf(100));
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
        LToLongBiFunction<T1,T2> sutThrowing = LToLongBiFunction.l((T1 a1,T2 a2) -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LToLongBiFunction<T1,T2> wrapped = sutThrowing.handleToLongBiFunc(handler -> handler
                .wrapWhen(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED)
                .throwIf(IndexOutOfBoundsException.class));

        // then
        try {
            wrapped.doApplyAsLong((T1)Integer.valueOf(100),(T2)Integer.valueOf(100));
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
        LToLongBiFunction<T1,T2> sutThrowing = LToLongBiFunction.l((T1 a1,T2 a2) -> {
            throw new UnsupportedOperationException(ORIGINAL_MESSAGE);
        });

        // when
        LToLongBiFunction<T1,T2> wrapped = sutThrowing.handleToLongBiFunc(h -> Function4U.doNothing());

        // then
        try {
            wrapped.doApplyAsLong((T1)Integer.valueOf(100),(T2)Integer.valueOf(100));
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
    public void testtoLongBiFuncCompose() throws X {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LToLongBiFunction<Integer ,Integer > sutO = (Integer a1,Integer a2) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo((T1)Integer.valueOf(90));
                assertThat(a2).isEqualTo((T2)Integer.valueOf(91));
                return (long)100;
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
        LToLongBiFunction<Integer ,Integer > function = sutO.toLongBiFuncCompose(before1,before2);
        function.doApplyAsLong((Integer )Integer.valueOf(80),(Integer )Integer.valueOf(81));

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
        LToLongBiFunction<Integer ,Integer > sutO = (Integer a1,Integer a2) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo((T1)Integer.valueOf(80));
                assertThat(a2).isEqualTo((T2)Integer.valueOf(81));
                return (long)90;
        };

        LLongFunction<Integer > thenFunction = p -> {
                thenFunctionCalled.set(true);
                // 
                assertThat(p).isEqualTo((long)90);
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
        assertThat(sut.nestingToLongBiFunc())
            .isSameAs(sut)
            .isInstanceOf(LToLongBiFunction.class);
    }

    @Test
    public void testShoving() {
        assertThat(sut.shovingToLongBiFunc())
            .isSameAs(sut)
            .isInstanceOf(LToLongBiFunction.class);
    }

    @Test
    public void testNestingX() {
        assertThat(sut.nestingToLongBiFuncX())
            .isSameAs(sut)
            .isInstanceOf(LToLongBiFunctionX.class);
    }

    @Test
    public void testShovingX() {
        assertThat(sut.shovingToLongBiFuncX())
            .isSameAs(sut)
            .isInstanceOf(LToLongBiFunctionX.class);
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void testShove() {

        // given
        LToLongBiFunction<T1,T2> sutThrowing = LToLongBiFunction.l((T1 a1,T2 a2) -> {
            throw new UnsupportedOperationException();
        });

        // when
        sutThrowing.shovingToLongBiFunc().doApplyAsLong((T1)Integer.valueOf(100),(T2)Integer.valueOf(100));
    }

    @Test
    public void testHandleToLongBiFunc() throws X {

        // given
        LToLongBiFunction<T1,T2> sutThrowing = LToLongBiFunction.l((T1 a1,T2 a2) -> {
            throw new UnsupportedOperationException();
        });

        // when
        LToLongBiFunction<T1,T2> wrapped = sutThrowing.handleToLongBiFunc(h -> {
            h.wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED);
        });

        // then
        try {
            wrapped.doApplyAsLong((T1)Integer.valueOf(100),(T2)Integer.valueOf(100));
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
                .contains("LToLongBiFunction: long doApplyAsLong(T1 a1,T2 a2)");
    }


    @Test
    public void isThrowing() {
        assertThat(sut.isThrowing())
            .isFalse();
    }

    //<editor-fold desc="Variants">

    private long variant1(T2 a2,T1 a1) {
        return (long)100;
    }

    @Test
    public void compilerSubstituteVariant1() {
        LToLongBiFunction lambda = LToLongBiFunction./*<T1,T2>*/l1(this::variant1);

        assertThat(lambda).isInstanceOf(LToLongBiFunction.V1.class);
    }

    //</editor-fold>


    @Test void safeCompiles() {
        LToLongBiFunction r1 = LToLongBiFunction.safe(sut); //NOSONAR
        LToLongBiFunctionX r2 = LToLongBiFunction.safe(sut); //NOSONAR
        ToLongBiFunction r3 = LToLongBiFunction.safe(sut); //NOSONAR
    }

    @Test void safePropagates() {
        Object result = LToLongBiFunction.safe(sut);
        assertThat(result).isSameAs(sut);
    }

    @Test void safeProtectsAgainstNpe() {
        Object result = LToLongBiFunction.safe(null);
        assertThat(result).isSameAs(LToLongBiFunction.l(LToLongBiFunction.safe()));
    }

    @Test  void safeSupplierPropagates() {
        LSupplier<LToLongBiFunction<T1,T2>> supplier = ()->sut;
        Object result = LToLongBiFunction.safeSupplier(supplier);
        assertThat(result).isSameAs(supplier);
    }

    @Test  void safeSupplierProtectsAgainstNpe() {
        Object result = LToLongBiFunction.safeSupplier(null);
        assertThat(result).isSameAs(LToLongBiFunction.safeSupplier());
    }

    @Test  void safeSupplierCompiles() {
        LSupplier<LToLongBiFunction<T1,T2>> r1 = LToLongBiFunction.safeSupplier(()->sut);  //NOSONAR
        Supplier<LToLongBiFunction<T1,T2>> r2 = LToLongBiFunction.safeSupplier(()->sut); //NOSONAR
    }

}
