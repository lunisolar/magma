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
public class LObjByteFunctionXTest<T,R,X extends ParseException> {
    private static final String ORIGINAL_MESSAGE = "Original message";
    private static final String EXCEPTION_WAS_WRAPPED = "Exception was wrapped.";
    private static final String NO_EXCEPTION_WERE_THROWN = "No exception were thrown.";

    private Integer testValue = 100;



    private LObjByteFunctionX<Integer,Integer,X> sut = new LObjByteFunctionX<Integer,Integer,X>(){
        public @Nullable Integer doApply(Integer a1,byte a2)  throws X {
            return testValue;
        }
    };

    private LObjByteFunction<Integer,Integer> opposite = new LObjByteFunction<Integer,Integer>(){
        public @Nullable Integer doApply(Integer a1,byte a2)  {
            return testValue;
        }
    };

    private LObjByteFunctionX<Integer,Integer,X> sutNull = new LObjByteFunctionX<Integer,Integer,X>(){
        public @Nullable Integer doApply(Integer a1,byte a2)  throws X {
            return null;
        }
    };



    private LObjByteFunctionX<Integer,Integer,ParseException> sutAlwaysThrowing = LObjByteFunctionX.lX((a1,a2) -> {
            throw new ParseException(ORIGINAL_MESSAGE, 0);
    });

    private LObjByteFunctionX<Integer,Integer,RuntimeException> sutAlwaysThrowingUnchecked = LObjByteFunctionX.lX((a1,a2) -> {
            throw new IndexOutOfBoundsException(ORIGINAL_MESSAGE);
    });


    @Test
    public void testTheResult() throws X {
        assertThat(sut.doApply(100,(byte)100))
            .isEqualTo(testValue);
    }

    @Test
    public void testTupleCall() throws X {

        LObjBytePair<Integer> domainObject = Tuple4U.objBytePair(100,(byte)100);

        Object result = sut.tupleApply(domainObject);

        assertThat(result)
            .isEqualTo(testValue);
    }

    @Test
    public void testNonNullDoApply() throws X {
        assertThat(sut.nonNullDoApply(100,(byte)100))
            .isSameAs(testValue);
    }

    @Test
    public void testNestingDoApplyChecked() throws X {

        // then
        try {
            sutAlwaysThrowing.nestingDoApply(100,(byte)100);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(NestedException.class)
                    .hasCauseExactlyInstanceOf(ParseException.class)
                    .hasMessage(ORIGINAL_MESSAGE);
        }
    }

    @Test
    public void testNestingDoApplyUnchecked() throws X {

        // then
        try {
            sutAlwaysThrowingUnchecked.nestingDoApply(100,(byte)100);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                    .hasNoCause()
                    .hasMessage(ORIGINAL_MESSAGE);
        }
    }

    @Test
    public void testShovingDoApplyChecked() throws X {

        // then
        try {
            sutAlwaysThrowing.shovingDoApply(100,(byte)100);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(ParseException.class)
                    .hasNoCause()
                    .hasMessage(ORIGINAL_MESSAGE);
        }
    }

    @Test
    public void testShovingDoApplyUnchecked() throws X {

        // then
        try {
            sutAlwaysThrowingUnchecked.shovingDoApply(100,(byte)100);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                    .hasNoCause()
                    .hasMessage(ORIGINAL_MESSAGE);
        }
    }

    @Test(expectedExceptions=NullPointerException.class, expectedExceptionsMessageRegExp="\\QEvaluated value by nonNullDoApply() method cannot be null (LObjByteFunctionX: R doApply(T a1,byte a2) throws X).\\E")
    public void testNonNullCapturesNull() throws X {
        sutNull.nonNullDoApply(100,(byte)100);
    }


    @Test
    public void testFunctionalInterfaceDescription() throws X {
        assertThat(sut.functionalInterfaceDescription())
            .isEqualTo("LObjByteFunctionX: R doApply(T a1,byte a2) throws X");
    }

    @Test
    public void testLXMethod() throws X {
        assertThat(LObjByteFunctionX.lX((a1,a2) -> testValue ))
            .isInstanceOf(LObjByteFunctionX.class);
    }

    @Test
    public void testWrapXMethod() throws X {
        assertThat(LObjByteFunctionX.wrapX(opposite))
            .isInstanceOf(LObjByteFunctionX.class);
    }


    @Test
    public void testHandlingDoApplyMethodWrapsTheException() throws X {

        // given
        LObjByteFunctionX<Integer,Integer,X> sutThrowing = LObjByteFunctionX.lX((a1,a2) -> {
            throw new UnsupportedOperationException();
        });

        // when
        LObjByteFunctionX<Integer,Integer,RuntimeException> wrapped = sutThrowing.handleObjByteFuncX(handler -> handler
            .wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED));

        // then
        try {
            wrapped.doApply(100,(byte)100);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IllegalArgumentException.class)
                    .hasCauseExactlyInstanceOf(UnsupportedOperationException.class)
                    .hasMessage(EXCEPTION_WAS_WRAPPED);
        }
    }

    @Test
    public void testHandleObjByteFuncXMethodDoNotWrapsOtherExceptionIf() throws X {

        // given
        LObjByteFunctionX<Integer,Integer,X> sutThrowing = LObjByteFunctionX.lX((a1,a2) -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LObjByteFunctionX<Integer,Integer,X> wrapped = sutThrowing.handleObjByteFuncX(handler -> handler
                .wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED)
                .throwIf(IndexOutOfBoundsException.class));

        // then
        try {
            wrapped.doApply(100,(byte)100);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                    .hasNoCause();
        }
    }

@Test
    public void testHandleObjByteFuncXMethodDoNotWrapsOtherExceptionWhen() throws X {

        // given
        LObjByteFunctionX<Integer,Integer,X> sutThrowing = LObjByteFunctionX.lX((a1,a2) -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LObjByteFunctionX<Integer,Integer,X> wrapped = sutThrowing.handleObjByteFuncX(handler -> handler
                .wrapWhen(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED)
                .throwIf(IndexOutOfBoundsException.class));

        // then
        try {
            wrapped.doApply(100,(byte)100);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                    .hasNoCause();
        }
    }


    @Test
    public void testHandleObjByteFuncXMishandlingExceptionIsAllowed() throws X {

        // given
        LObjByteFunctionX<Integer,Integer,X> sutThrowing = LObjByteFunctionX.lX((a1,a2) -> {
            throw (X) new ParseException(ORIGINAL_MESSAGE, 0);
        });

        // when
        LObjByteFunctionX<Integer,Integer,X> wrapped = sutThrowing.handleObjByteFuncX(h -> Function4U.doNothing());

        // then
        try {
            wrapped.doApply(100,(byte)100);
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
    public void testObjByteFuncComposeByte() throws X {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LObjByteFunctionX<Integer,Integer,X> sutO = (Integer a1,byte a2) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(90);
                assertThat(a2).isEqualTo((byte)91);
                return 100;
        };

        LFunctionX<Integer,Integer,X> before1 = p0 -> {
            assertThat(p0).isEqualTo(80);
            beforeCalls.incrementAndGet();
            return 90;
        };
        LByteUnaryOperatorX<X> before2 = p1 -> {
            assertThat(p1).isEqualTo((byte)81);
            beforeCalls.incrementAndGet();
            return (byte)91;
        };

        //when
        LObjByteFunctionX<Integer,Integer,X> function = sutO.objByteFuncComposeByte(before1,before2);
        function.doApply(80,(byte)81);

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(beforeCalls.get()).isEqualTo(2);
    }


    @Test
    public void testObjByteFuncCompose() throws X {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LObjByteFunctionX<Integer,Integer,X> sutO = (Integer a1,byte a2) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(90);
                assertThat(a2).isEqualTo((byte)91);
                return 100;
        };

        LFunctionX<Integer,Integer,X> before1 = p0 -> {
            assertThat(p0).isEqualTo(80);
            beforeCalls.incrementAndGet();
            return 90;
        };
        LToByteFunctionX<Integer,X> before2 = p1 -> {
            assertThat(p1).isEqualTo(81);
            beforeCalls.incrementAndGet();
            return (byte)91;
        };

        //when
        LBiFunctionX<Integer,Integer,Integer,X> function = sutO.objByteFuncCompose(before1,before2);
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
        LObjByteFunctionX<Integer,Integer,X> sutO = (a1,a2) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(80);
                assertThat(a2).isEqualTo((byte)81);
                return 90;
        };

        LFunctionX<Integer,Integer,X> thenFunction = p -> {
                thenFunctionCalled.set(true);
                // Integer
                assertThat(p).isEqualTo(90);
                // Integer
                return 100;
        };

        //when
        LObjByteFunctionX<Integer,Integer,X> function = sutO.then(thenFunction);
        Integer finalValue = function.doApply(80,(byte)81);

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
        LObjByteFunctionX<Integer,Integer,X> sutO = (a1,a2) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(80);
                assertThat(a2).isEqualTo((byte)81);
                return 90;
        };

        LConsumerX<Integer,X> thenFunction = p -> {
                thenFunctionCalled.set(true);
                // Integer
                assertThat(p).isEqualTo(90);
        };

        //when
        LObjByteConsumerX<Integer,X> function = sutO.then(thenFunction);
        function.doAccept(80,(byte)81);

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    // </editor-fold>

    @Test
    public void testNesting() {
        assertThat(sut.nestingObjByteFunc())
            .isInstanceOf(LObjByteFunction.class);
    }

    @Test
    public void testShoving() {
        assertThat(sut.shovingObjByteFunc())
            .isInstanceOf(LObjByteFunction.class);
    }

    @Test
    public void testNestingX() {
        assertThat(sut.nestingObjByteFuncX())
            .isInstanceOf(LObjByteFunctionX.class);
    }

    @Test
    public void testShovingX() {
        assertThat(sut.shovingObjByteFuncX())
            .isInstanceOf(LObjByteFunctionX.class);
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void testShove() {

        // given
        LObjByteFunctionX<Integer,Integer,X> sutThrowing = LObjByteFunctionX.lX((a1,a2) -> {
            throw new UnsupportedOperationException();
        });

        // when
        sutThrowing.shovingObjByteFunc().doApply(100,(byte)100);
    }

    @Test
    public void testHandleObjByteFunc() throws X {

        // given
        LObjByteFunctionX<Integer,Integer,X> sutThrowing = LObjByteFunctionX.lX((a1,a2) -> {
            throw new UnsupportedOperationException();
        });

        // when
        LObjByteFunctionX<Integer,Integer,X> wrapped = sutThrowing.handleObjByteFuncX(h -> {
            h.wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED);
        });

        // then
        try {
            wrapped.doApply(100,(byte)100);
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
                .contains("LObjByteFunctionX: R doApply(T a1,byte a2) throws X");
    }


    @Test
    public void isThrowing() {
        assertThat(sut.isThrowing())
            .isTrue();
    }

    //<editor-fold desc="Variants">

    private Integer variantV1(byte a2,Integer a1) {
        return 100;
    }

    @Test
    public void compilerSubstituteVariantV1() {
        LObjByteFunctionX lambda = LObjByteFunctionX./*<T,R,X>*/lX1(this::variantV1);

        assertThat(lambda).isInstanceOf(LObjByteFunctionX.V1.class);
    }

    //</editor-fold>


    @Test void safeCompiles() {
        LObjByteFunctionX r1 = LObjByteFunctionX.safe(sut); //NOSONAR
    }

    @Test void safePropagates() {
        Object result = LObjByteFunctionX.safe(sut);
        assertThat(result).isSameAs(sut);
    }

    @Test void safeProtectsAgainstNpe() {
        Object result = LObjByteFunctionX.safe(null);
        assertThat(result).isSameAs(LObjByteFunctionX.lX(LObjByteFunctionX.safe()));
    }

    @Test <Y extends Throwable> void safeSupplierPropagates() {
        LSupplierX<LObjByteFunctionX<Integer,Integer,X>,Y> supplier = ()->sut;
        Object result = LObjByteFunctionX.safeSupplier(supplier);
        assertThat(result).isSameAs(supplier);
    }

    @Test <Y extends Throwable> void safeSupplierProtectsAgainstNpe() {
        Object result = LObjByteFunctionX.safeSupplier(null);
        assertThat(result).isSameAs(LObjByteFunctionX.safeSupplier());
    }

    @Test <Y extends Throwable> void safeSupplierCompiles() {
        LSupplierX<LObjByteFunctionX<Integer,Integer,X>,Y> r1 = LObjByteFunctionX.safeSupplier(()->sut);  //NOSONAR
    }

}
