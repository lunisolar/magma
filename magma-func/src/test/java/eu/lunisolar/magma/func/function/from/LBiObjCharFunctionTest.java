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
public class LBiObjCharFunctionTest<T1,T2,R,X extends ParseException> {
    private static final String ORIGINAL_MESSAGE = "Original message";
    private static final String EXCEPTION_WAS_WRAPPED = "Exception was wrapped.";
    private static final String NO_EXCEPTION_WERE_THROWN = "No exception were thrown.";

    private Object  testValue = (R)Integer.valueOf(100);



    private LBiObjCharFunction<T1,T2,R> sut = new LBiObjCharFunction(){
        public @Nullable Object  doApply(Object a1,Object a2,char a3)  {
            return testValue;
        }
    };

    private LBiObjCharFunctionX<T1,T2,R,X> opposite = new LBiObjCharFunctionX(){
        public @Nullable Object  doApply(Object a1,Object a2,char a3) throws ParseException {
            return testValue;
        }
    };

    private LBiObjCharFunction<T1,T2,R> sutNull = new LBiObjCharFunction(){
        public @Nullable Object  doApply(Object a1,Object a2,char a3)  {
            return null;
        }
    };




    private LBiObjCharFunction<T1,T2,R> sutAlwaysThrowingUnckeck = LBiObjCharFunction.l((T1 a1,T2 a2,char a3) -> {
            throw new IndexOutOfBoundsException(ORIGINAL_MESSAGE);
    });


    @Test
    public void testTheResult() throws X {
        assertThat(sut.doApply((T1)Integer.valueOf(100),(T2)Integer.valueOf(100),(char)100))
            .isSameAs(testValue);
    }

    @Test
    public void testTupleCall() throws X {

        LBiObjCharTriple<T1,T2> domainObject = Tuple4U.tuple((T1)Integer.valueOf(100),(T2)Integer.valueOf(100),(char)100);

        Object result = sut.tupleApply(domainObject);

        assertThat(result)
            .isSameAs(testValue);
    }

    @Test
    public void testNonNullDoApply() throws X {
        assertThat(sut.nonNullDoApply((T1)Integer.valueOf(100),(T2)Integer.valueOf(100),(char)100))
            .isSameAs(testValue);
    }

    @Test
    public void testNestingDoApplyUnckeck() throws X {

        // then
        try {
            sutAlwaysThrowingUnckeck.nestingDoApply((T1)Integer.valueOf(100),(T2)Integer.valueOf(100),(char)100);
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
            sutAlwaysThrowingUnckeck.shovingDoApply((T1)Integer.valueOf(100),(T2)Integer.valueOf(100),(char)100);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                    .hasNoCause()
                    .hasMessage(ORIGINAL_MESSAGE);
        }
    }

    @Test(expectedExceptions=NullPointerException.class, expectedExceptionsMessageRegExp="\\QEvaluated value by nonNullDoApply() method cannot be null (LBiObjCharFunction: R doApply(T1 a1,T2 a2,char a3)).\\E")
    public void testNonNullCapturesNull() throws X {
        sutNull.nonNullDoApply((T1)Integer.valueOf(100),(T2)Integer.valueOf(100),(char)100);
    }


    @Test
    public void testFunctionalInterfaceDescription() throws X {
        assertThat(sut.functionalInterfaceDescription())
            .isEqualTo("LBiObjCharFunction: R doApply(T1 a1,T2 a2,char a3)");
    }

    @Test
    public void testLMethod() throws X {
        assertThat(LBiObjCharFunction.l((Object a1,Object a2,char a3) -> testValue ))
            .isInstanceOf(LBiObjCharFunction.class);
    }

    @Test
    public void testWrapMethod() throws X {
        assertThat(LBiObjCharFunction.wrap(opposite))
            .isInstanceOf(LBiObjCharFunction.class);
    }

    @Test
    public void testWrapMethodDoNotWrapsRuntimeException() throws X {
        // given
        LBiObjCharFunctionX<T1,T2,R,X> sutThrowing = LBiObjCharFunctionX.lX((T1 a1,T2 a2,char a3) -> {
            throw new UnsupportedOperationException(ORIGINAL_MESSAGE);
        });

        // when
        LBiObjCharFunction<T1,T2,R> wrapped = LBiObjCharFunction.wrap(sutThrowing);

        // then
        try {
            wrapped.doApply((T1)Integer.valueOf(100),(T2)Integer.valueOf(100),(char)100);
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
        LBiObjCharFunctionX<T1,T2,R,ParseException> sutThrowing = LBiObjCharFunctionX.lX((T1 a1,T2 a2,char a3) -> {
            throw new ParseException(ORIGINAL_MESSAGE, 0);
        });

        // when
        LBiObjCharFunction<T1,T2,R> wrapped = LBiObjCharFunction.wrap(sutThrowing);

        // then
        try {
            wrapped.doApply((T1)Integer.valueOf(100),(T2)Integer.valueOf(100),(char)100);
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
        LBiObjCharFunction<T1,T2,R> sutThrowing = LBiObjCharFunction.l((T1 a1,T2 a2,char a3) -> {
            throw new UnsupportedOperationException();
        });

        // when
        LBiObjCharFunction<T1,T2,R> wrapped = sutThrowing.handleBiObjCharFunc(handler -> handler
            .wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED));

        // then
        try {
            wrapped.doApply((T1)Integer.valueOf(100),(T2)Integer.valueOf(100),(char)100);
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
        LBiObjCharFunction<T1,T2,R> sutThrowing = LBiObjCharFunction.l((T1 a1,T2 a2,char a3) -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LBiObjCharFunction<T1,T2,R> wrapped = sutThrowing.handleBiObjCharFunc(handler -> handler
                .wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED)
                .throwIf(IndexOutOfBoundsException.class));

        // then
        try {
            wrapped.doApply((T1)Integer.valueOf(100),(T2)Integer.valueOf(100),(char)100);
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
        LBiObjCharFunction<T1,T2,R> sutThrowing = LBiObjCharFunction.l((T1 a1,T2 a2,char a3) -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LBiObjCharFunction<T1,T2,R> wrapped = sutThrowing.handleBiObjCharFunc(handler -> handler
                .wrapWhen(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED)
                .throwIf(IndexOutOfBoundsException.class));

        // then
        try {
            wrapped.doApply((T1)Integer.valueOf(100),(T2)Integer.valueOf(100),(char)100);
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
        LBiObjCharFunction<T1,T2,R> sutThrowing = LBiObjCharFunction.l((T1 a1,T2 a2,char a3) -> {
            throw new UnsupportedOperationException(ORIGINAL_MESSAGE);
        });

        // when
        LBiObjCharFunction<T1,T2,R> wrapped = sutThrowing.handleBiObjCharFunc(h -> Function4U.doNothing());

        // then
        try {
            wrapped.doApply((T1)Integer.valueOf(100),(T2)Integer.valueOf(100),(char)100);
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
    public void testbiObjCharFuncComposeChar() throws X {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LBiObjCharFunction<Integer ,Integer ,Integer > sutO = (Integer a1,Integer a2,char a3) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo((T1)Integer.valueOf(90));
                assertThat(a2).isEqualTo((T2)Integer.valueOf(91));
                assertThat(a3).isEqualTo((char)92);
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
        LCharUnaryOperator before3 = p2 -> {
            assertThat(p2).isEqualTo((char)82);
            beforeCalls.incrementAndGet();
            return (char)92;
        };

        //when
        LBiObjCharFunction<Integer ,Integer ,Integer > function = sutO.biObjCharFuncComposeChar(before1,before2,before3);
        function.doApply((Integer )Integer.valueOf(80),(Integer )Integer.valueOf(81),(char)82);

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(beforeCalls.get()).isEqualTo(3);
    }


    @Test
    public void testbiObjCharFuncCompose() throws X {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LBiObjCharFunction<Integer ,Integer ,Integer > sutO = (Integer a1,Integer a2,char a3) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo((T1)Integer.valueOf(90));
                assertThat(a2).isEqualTo((T2)Integer.valueOf(91));
                assertThat(a3).isEqualTo((char)92);
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
        LToCharFunction<Integer > before3 = p2 -> {
            assertThat(p2).isEqualTo(Integer.valueOf(82));
            beforeCalls.incrementAndGet();
            return (char)92;
        };

        //when
        LTriFunction<Integer ,Integer ,Integer ,Integer > function = sutO.biObjCharFuncCompose(before1,before2,before3);
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
        LBiObjCharFunction<Integer ,Integer ,Integer > sutO = (Integer a1,Integer a2,char a3) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo((T1)Integer.valueOf(80));
                assertThat(a2).isEqualTo((T2)Integer.valueOf(81));
                assertThat(a3).isEqualTo((char)82);
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
        LBiObjCharFunction<Integer ,Integer ,Integer > function = sutO.then(thenFunction);
        Integer  finalValue = function.doApply((Integer )Integer.valueOf(80),(Integer )Integer.valueOf(81),(char)82);

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
        LBiObjCharFunction<Integer ,Integer ,Integer > sutO = (Integer a1,Integer a2,char a3) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo((T1)Integer.valueOf(80));
                assertThat(a2).isEqualTo((T2)Integer.valueOf(81));
                assertThat(a3).isEqualTo((char)82);
                return Integer.valueOf(90);
        };

        LConsumer<Integer > thenFunction = p -> {
                thenFunctionCalled.set(true);
                // R
                assertThat(p).isEqualTo(Integer.valueOf(90));
        };

        //when
        LBiObjCharConsumer<Integer ,Integer > function = sutO.then(thenFunction);
        function.doAccept((Integer )Integer.valueOf(80),(Integer )Integer.valueOf(81),(char)82);

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    // </editor-fold>

    @Test
    public void testNesting() {
        assertThat(sut.nestingBiObjCharFunc())
            .isSameAs(sut)
            .isInstanceOf(LBiObjCharFunction.class);
    }

    @Test
    public void testShoving() {
        assertThat(sut.shovingBiObjCharFunc())
            .isSameAs(sut)
            .isInstanceOf(LBiObjCharFunction.class);
    }

    @Test
    public void testNestingX() {
        assertThat(sut.nestingBiObjCharFuncX())
            .isSameAs(sut)
            .isInstanceOf(LBiObjCharFunctionX.class);
    }

    @Test
    public void testShovingX() {
        assertThat(sut.shovingBiObjCharFuncX())
            .isSameAs(sut)
            .isInstanceOf(LBiObjCharFunctionX.class);
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void testShove() {

        // given
        LBiObjCharFunction<T1,T2,R> sutThrowing = LBiObjCharFunction.l((T1 a1,T2 a2,char a3) -> {
            throw new UnsupportedOperationException();
        });

        // when
        sutThrowing.shovingBiObjCharFunc().doApply((T1)Integer.valueOf(100),(T2)Integer.valueOf(100),(char)100);
    }

    @Test
    public void testHandleBiObjCharFunc() throws X {

        // given
        LBiObjCharFunction<T1,T2,R> sutThrowing = LBiObjCharFunction.l((T1 a1,T2 a2,char a3) -> {
            throw new UnsupportedOperationException();
        });

        // when
        LBiObjCharFunction<T1,T2,R> wrapped = sutThrowing.handleBiObjCharFunc(h -> {
            h.wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED);
        });

        // then
        try {
            wrapped.doApply((T1)Integer.valueOf(100),(T2)Integer.valueOf(100),(char)100);
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
                .contains("LBiObjCharFunction: R doApply(T1 a1,T2 a2,char a3)");
    }


    @Test
    public void isThrowing() {
        assertThat(sut.isThrowing())
            .isFalse();
    }

    //<editor-fold desc="Variants">

    private R variant1(T1 a1,char a3,T2 a2) {
        return (R)Integer.valueOf(100);
    }

    @Test
    public void compilerSubstituteVariant1() {
        LBiObjCharFunction lambda = LBiObjCharFunction./*<T1,T2,R>*/l1(this::variant1);

        assertThat(lambda).isInstanceOf(LBiObjCharFunction.V1.class);
    }


    private R variant2(T2 a2,T1 a1,char a3) {
        return (R)Integer.valueOf(100);
    }

    @Test
    public void compilerSubstituteVariant2() {
        LBiObjCharFunction lambda = LBiObjCharFunction./*<T1,T2,R>*/l2(this::variant2);

        assertThat(lambda).isInstanceOf(LBiObjCharFunction.V2.class);
    }


    private R variant3(T2 a2,char a3,T1 a1) {
        return (R)Integer.valueOf(100);
    }

    @Test
    public void compilerSubstituteVariant3() {
        LBiObjCharFunction lambda = LBiObjCharFunction./*<T1,T2,R>*/l3(this::variant3);

        assertThat(lambda).isInstanceOf(LBiObjCharFunction.V3.class);
    }


    private R variant4(char a3,T1 a1,T2 a2) {
        return (R)Integer.valueOf(100);
    }

    @Test
    public void compilerSubstituteVariant4() {
        LBiObjCharFunction lambda = LBiObjCharFunction./*<T1,T2,R>*/l4(this::variant4);

        assertThat(lambda).isInstanceOf(LBiObjCharFunction.V4.class);
    }


    private R variant5(char a3,T2 a2,T1 a1) {
        return (R)Integer.valueOf(100);
    }

    @Test
    public void compilerSubstituteVariant5() {
        LBiObjCharFunction lambda = LBiObjCharFunction./*<T1,T2,R>*/l5(this::variant5);

        assertThat(lambda).isInstanceOf(LBiObjCharFunction.V5.class);
    }

    //</editor-fold>


    @Test void safeCompiles() {
        LBiObjCharFunction r1 = LBiObjCharFunction.safe(sut); //NOSONAR
        LBiObjCharFunctionX r2 = LBiObjCharFunction.safe(sut); //NOSONAR
    }

    @Test void safePropagates() {
        Object result = LBiObjCharFunction.safe(sut);
        assertThat(result).isSameAs(sut);
    }

    @Test void safeProtectsAgainstNpe() {
        Object result = LBiObjCharFunction.safe(null);
        assertThat(result).isSameAs(LBiObjCharFunction.l(LBiObjCharFunction.safe()));
    }

    @Test  void safeSupplierPropagates() {
        LSupplier<LBiObjCharFunction<T1,T2,R>> supplier = ()->sut;
        Object result = LBiObjCharFunction.safeSupplier(supplier);
        assertThat(result).isSameAs(supplier);
    }

    @Test  void safeSupplierProtectsAgainstNpe() {
        Object result = LBiObjCharFunction.safeSupplier(null);
        assertThat(result).isSameAs(LBiObjCharFunction.safeSupplier());
    }

    @Test  void safeSupplierCompiles() {
        LSupplier<LBiObjCharFunction<T1,T2,R>> r1 = LBiObjCharFunction.safeSupplier(()->sut);  //NOSONAR
    }

}
