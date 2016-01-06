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
public class LObjIntToIntFunctionXTest<T,X extends ParseException> {
    private static final String ORIGINAL_MESSAGE = "Original message";
    private static final String EXCEPTION_WAS_WRAPPED = "Exception was wrapped.";
    private static final String NO_EXCEPTION_WERE_THROWN = "No exception were thrown.";

    private int testValue = (int)100;



    private LObjIntToIntFunctionX<T,X> sut = new LObjIntToIntFunctionX(){
        public  int doApplyAsInt(Object a1,int a2) throws ParseException {
            return testValue;
        }
    };

    private LObjIntToIntFunction<T> opposite = new LObjIntToIntFunction(){
        public  int doApplyAsInt(Object a1,int a2)  {
            return testValue;
        }
    };



    private LObjIntToIntFunctionX<T,ParseException> sutAlwaysThrowing = LObjIntToIntFunctionX.lX((T a1,int a2) -> {
            throw new ParseException(ORIGINAL_MESSAGE, 0);
    });

    private LObjIntToIntFunctionX<T,RuntimeException> sutAlwaysThrowingUnckeck = LObjIntToIntFunctionX.lX((T a1,int a2) -> {
            throw new IndexOutOfBoundsException(ORIGINAL_MESSAGE);
    });


    @Test
    public void testTheResult() throws X {
        assertThat(sut.doApplyAsInt((T)Integer.valueOf(100),(int)100))
            .isEqualTo(testValue);
    }

    @Test
    public void testTupleCall() throws X {

        LObjIntPair<T> domainObject = Tuple4U.tuple((T)Integer.valueOf(100),(int)100);

        Object result = sut.tupleApplyAsInt(domainObject);

        assertThat(result)
            .isEqualTo(testValue);
    }

    @Test
    public void testNonNullDoApplyAsInt() throws X {
        assertThat(sut.nonNullDoApplyAsInt((T)Integer.valueOf(100),(int)100))
            .isEqualTo(testValue);
    }

    @Test
    public void testNestingDoApplyAsIntChecked() throws X {

        // then
        try {
            sutAlwaysThrowing.nestingDoApplyAsInt((T)Integer.valueOf(100),(int)100);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(NestedException.class)
                    .hasCauseExactlyInstanceOf(ParseException.class)
                    .hasMessage(ORIGINAL_MESSAGE);
        }
    }

    @Test
    public void testNestingDoApplyAsIntUnckeck() throws X {

        // then
        try {
            sutAlwaysThrowingUnckeck.nestingDoApplyAsInt((T)Integer.valueOf(100),(int)100);
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
            sutAlwaysThrowing.shovingDoApplyAsInt((T)Integer.valueOf(100),(int)100);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(ParseException.class)
                    .hasNoCause()
                    .hasMessage(ORIGINAL_MESSAGE);
        }
    }

    @Test
    public void testShovingDoApplyAsIntUnckeck() throws X {

        // then
        try {
            sutAlwaysThrowingUnckeck.shovingDoApplyAsInt((T)Integer.valueOf(100),(int)100);
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
            .isEqualTo("LObjIntToIntFunctionX: int doApplyAsInt(T a1,int a2) throws X");
    }

    @Test
    public void testLXMethod() throws X {
        assertThat(LObjIntToIntFunctionX.lX((Object a1,int a2) -> testValue ))
            .isInstanceOf(LObjIntToIntFunctionX.class);
    }

    @Test
    public void testWrapXMethod() throws X {
        assertThat(LObjIntToIntFunctionX.wrapX(opposite))
            .isInstanceOf(LObjIntToIntFunctionX.class);
    }


    @Test
    public void testWrapExceptionMethodWrapsTheException() throws X {

        // given
        LObjIntToIntFunctionX<T,X> sutThrowing = LObjIntToIntFunctionX.lX((T a1,int a2) -> {
            throw new UnsupportedOperationException();
        });

        // when
        LObjIntToIntFunctionX<T,X> wrapped = sutThrowing.handleObjIntToIntFuncX(handler -> handler
            .wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED));

        // then
        try {
            wrapped.doApplyAsInt((T)Integer.valueOf(100),(int)100);
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
        LObjIntToIntFunctionX<T,X> sutThrowing = LObjIntToIntFunctionX.lX((T a1,int a2) -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LObjIntToIntFunctionX<T,X> wrapped = sutThrowing.handleObjIntToIntFuncX(handler -> handler
                .wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED)
                .throwIf(IndexOutOfBoundsException.class));

        // then
        try {
            wrapped.doApplyAsInt((T)Integer.valueOf(100),(int)100);
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
        LObjIntToIntFunctionX<T,X> sutThrowing = LObjIntToIntFunctionX.lX((T a1,int a2) -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LObjIntToIntFunctionX<T,X> wrapped = sutThrowing.handleObjIntToIntFuncX(handler -> handler
                .wrapWhen(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED)
                .throwIf(IndexOutOfBoundsException.class));

        // then
        try {
            wrapped.doApplyAsInt((T)Integer.valueOf(100),(int)100);
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
        LObjIntToIntFunctionX<T,X> sutThrowing = LObjIntToIntFunctionX.lX((T a1,int a2) -> {
            throw (X) new ParseException(ORIGINAL_MESSAGE, 0);
        });

        // when
        LObjIntToIntFunctionX<T,X> wrapped = sutThrowing.handleObjIntToIntFuncX(h -> Function4U.doNothing());

        // then
        try {
            wrapped.doApplyAsInt((T)Integer.valueOf(100),(int)100);
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
    public void testobjIntToIntFuncComposeInt() throws X {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LObjIntToIntFunctionX<Integer ,X> sutO = (Integer a1,int a2) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo((T)Integer.valueOf(90));
                assertThat(a2).isEqualTo((int)91);
                return (int)100;
        };

        LFunctionX<Integer ,Integer ,X> before1 = p0 -> {
            assertThat(p0).isEqualTo((T)Integer.valueOf(80));
            beforeCalls.incrementAndGet();
            return 90;
        };
        LIntUnaryOperatorX<X> before2 = p1 -> {
            assertThat(p1).isEqualTo((int)81);
            beforeCalls.incrementAndGet();
            return (int)91;
        };

        //when
        LObjIntToIntFunctionX<Integer ,X> function = sutO.objIntToIntFuncComposeInt(before1,before2);
        function.doApplyAsInt((Integer )Integer.valueOf(80),(int)81);

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(beforeCalls.get()).isEqualTo(2);
    }


    @Test
    public void testobjIntToIntFuncCompose() throws X {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LObjIntToIntFunctionX<Integer ,X> sutO = (Integer a1,int a2) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo((T)Integer.valueOf(90));
                assertThat(a2).isEqualTo((int)91);
                return (int)100;
        };

        LFunctionX<Integer ,Integer ,X> before1 = p0 -> {
            assertThat(p0).isEqualTo(Integer.valueOf(80));
            beforeCalls.incrementAndGet();
            return 90;
        };
        LToIntFunctionX<Integer ,X> before2 = p1 -> {
            assertThat(p1).isEqualTo(Integer.valueOf(81));
            beforeCalls.incrementAndGet();
            return (int)91;
        };

        //when
        LToIntBiFunctionX<Integer ,Integer ,X> function = sutO.objIntToIntFuncCompose(before1,before2);
        function.doApplyAsInt((Integer )Integer.valueOf(80),(Integer )Integer.valueOf(81));

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
        LObjIntToIntFunctionX<Integer ,X> sutO = (Integer a1,int a2) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo((T)Integer.valueOf(80));
                assertThat(a2).isEqualTo((int)81);
                return (int)90;
        };

        LIntFunctionX<Integer ,X> thenFunction = p -> {
                thenFunctionCalled.set(true);
                // 
                assertThat(p).isEqualTo((int)90);
                // V
                return Integer.valueOf(100);
        };

        //when
        LObjIntFunctionX<Integer ,Integer ,X> function = sutO.then(thenFunction);
        Integer  finalValue = function.doApply((Integer )Integer.valueOf(80),(int)81);

        //then - finals
        assertThat(finalValue).isEqualTo(Integer.valueOf(100));
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    // </editor-fold>

    @Test
    public void testNesting() {
        assertThat(sut.nestingObjIntToIntFunc())
            .isInstanceOf(LObjIntToIntFunction.class);
    }

    @Test
    public void testShoving() {
        assertThat(sut.shovingObjIntToIntFunc())
            .isInstanceOf(LObjIntToIntFunction.class);
    }

    @Test
    public void testNestingX() {
        assertThat(sut.nestingObjIntToIntFuncX())
            .isInstanceOf(LObjIntToIntFunctionX.class);
    }

    @Test
    public void testShovingX() {
        assertThat(sut.shovingObjIntToIntFuncX())
            .isInstanceOf(LObjIntToIntFunctionX.class);
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void testShove() {

        // given
        LObjIntToIntFunctionX<T,X> sutThrowing = LObjIntToIntFunctionX.lX((T a1,int a2) -> {
            throw new UnsupportedOperationException();
        });

        // when
        sutThrowing.shovingObjIntToIntFunc().doApplyAsInt((T)Integer.valueOf(100),(int)100);
    }

    @Test
    public void testHandleObjIntToIntFunc() throws X {

        // given
        LObjIntToIntFunctionX<T,X> sutThrowing = LObjIntToIntFunctionX.lX((T a1,int a2) -> {
            throw new UnsupportedOperationException();
        });

        // when
        LObjIntToIntFunctionX<T,X> wrapped = sutThrowing.handleObjIntToIntFuncX(h -> {
            h.wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED);
        });

        // then
        try {
            wrapped.doApplyAsInt((T)Integer.valueOf(100),(int)100);
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
                .contains("LObjIntToIntFunctionX: int doApplyAsInt(T a1,int a2) throws X");
    }


    @Test
    public void isThrowing() {
        assertThat(sut.isThrowing())
            .isTrue();
    }

    //<editor-fold desc="Variants">

    private int variant1(int a2,T a1) {
        return (int)100;
    }

    @Test
    public void compilerSubstituteVariant1() {
        LObjIntToIntFunctionX lambda = LObjIntToIntFunctionX./*<T,X>*/lX1(this::variant1);

        assertThat(lambda).isInstanceOf(LObjIntToIntFunctionX.V1.class);
    }

    //</editor-fold>


    @Test void safeCompiles() {
        LObjIntToIntFunctionX r1 = LObjIntToIntFunctionX.safe(sut); //NOSONAR
    }

    @Test void safePropagates() {
        Object result = LObjIntToIntFunctionX.safe(sut);
        assertThat(result).isSameAs(sut);
    }

    @Test void safeProtectsAgainstNpe() {
        Object result = LObjIntToIntFunctionX.safe(null);
        assertThat(result).isSameAs(LObjIntToIntFunctionX.lX(LObjIntToIntFunctionX.safe()));
    }

    @Test <Y extends Throwable> void safeSupplierPropagates() {
        LSupplierX<LObjIntToIntFunctionX<T,X>,Y> supplier = ()->sut;
        Object result = LObjIntToIntFunctionX.safeSupplier(supplier);
        assertThat(result).isSameAs(supplier);
    }

    @Test <Y extends Throwable> void safeSupplierProtectsAgainstNpe() {
        Object result = LObjIntToIntFunctionX.safeSupplier(null);
        assertThat(result).isSameAs(LObjIntToIntFunctionX.safeSupplier());
    }

    @Test <Y extends Throwable> void safeSupplierCompiles() {
        LSupplierX<LObjIntToIntFunctionX<T,X>,Y> r1 = LObjIntToIntFunctionX.safeSupplier(()->sut);  //NOSONAR
    }

}
