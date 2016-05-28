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
public class LToCharBiFunctionXTest<T1,T2,X extends ParseException> {
    private static final String ORIGINAL_MESSAGE = "Original message";
    private static final String EXCEPTION_WAS_WRAPPED = "Exception was wrapped.";
    private static final String NO_EXCEPTION_WERE_THROWN = "No exception were thrown.";

    private char testValue = '\u0100';



    private LToCharBiFunctionX<Integer,Integer,X> sut = new LToCharBiFunctionX<Integer,Integer,X>(){
        public  char doApplyAsChar(Integer a1,Integer a2)  throws X {
            return testValue;
        }
    };

    private LToCharBiFunction<Integer,Integer> opposite = new LToCharBiFunction<Integer,Integer>(){
        public  char doApplyAsChar(Integer a1,Integer a2)  {
            return testValue;
        }
    };



    private LToCharBiFunctionX<Integer,Integer,ParseException> sutAlwaysThrowing = LToCharBiFunctionX.lX((a1,a2) -> {
            throw new ParseException(ORIGINAL_MESSAGE, 0);
    });

    private LToCharBiFunctionX<Integer,Integer,RuntimeException> sutAlwaysThrowingUnchecked = LToCharBiFunctionX.lX((a1,a2) -> {
            throw new IndexOutOfBoundsException(ORIGINAL_MESSAGE);
    });


    @Test
    public void testTheResult() throws X {
        assertThat(sut.doApplyAsChar(100,100))
            .isEqualTo(testValue);
    }

    @Test
    public void testTupleCall() throws X {

        LPair<Integer,Integer> domainObject = Tuple4U.pair(100,100);

        Object result = sut.tupleApplyAsChar(domainObject);

        assertThat(result)
            .isEqualTo(testValue);
    }

    @Test
    public void testNonNullDoApplyAsChar() throws X {
        assertThat(sut.nonNullDoApplyAsChar(100,100))
            .isEqualTo(testValue);
    }

    @Test
    public void testNestingDoApplyAsCharChecked() throws X {

        // then
        try {
            sutAlwaysThrowing.nestingDoApplyAsChar(100,100);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(NestedException.class)
                    .hasCauseExactlyInstanceOf(ParseException.class)
                    .hasMessage(ORIGINAL_MESSAGE);
        }
    }

    @Test
    public void testNestingDoApplyAsCharUnchecked() throws X {

        // then
        try {
            sutAlwaysThrowingUnchecked.nestingDoApplyAsChar(100,100);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                    .hasNoCause()
                    .hasMessage(ORIGINAL_MESSAGE);
        }
    }

    @Test
    public void testShovingDoApplyAsCharChecked() throws X {

        // then
        try {
            sutAlwaysThrowing.shovingDoApplyAsChar(100,100);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(ParseException.class)
                    .hasNoCause()
                    .hasMessage(ORIGINAL_MESSAGE);
        }
    }

    @Test
    public void testShovingDoApplyAsCharUnchecked() throws X {

        // then
        try {
            sutAlwaysThrowingUnchecked.shovingDoApplyAsChar(100,100);
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
            .isEqualTo("LToCharBiFunctionX: char doApplyAsChar(T1 a1,T2 a2) throws X");
    }

    @Test
    public void testLXMethod() throws X {
        assertThat(LToCharBiFunctionX.lX((a1,a2) -> testValue ))
            .isInstanceOf(LToCharBiFunctionX.class);
    }

    @Test
    public void testWrapXMethod() throws X {
        assertThat(LToCharBiFunctionX.wrapX(opposite))
            .isInstanceOf(LToCharBiFunctionX.class);
    }


    @Test
    public void testHandlingDoApplyAsCharMethodWrapsTheException() throws X {

        // given
        LToCharBiFunctionX<Integer,Integer,X> sutThrowing = LToCharBiFunctionX.lX((a1,a2) -> {
            throw new UnsupportedOperationException();
        });

        // when
        LToCharBiFunctionX<Integer,Integer,RuntimeException> wrapped = sutThrowing.handleToCharBiFuncX(handler -> handler
            .wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED));

        // then
        try {
            wrapped.doApplyAsChar(100,100);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IllegalArgumentException.class)
                    .hasCauseExactlyInstanceOf(UnsupportedOperationException.class)
                    .hasMessage(EXCEPTION_WAS_WRAPPED);
        }
    }

    @Test
    public void testHandleToCharBiFuncXMethodDoNotWrapsOtherExceptionIf() throws X {

        // given
        LToCharBiFunctionX<Integer,Integer,X> sutThrowing = LToCharBiFunctionX.lX((a1,a2) -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LToCharBiFunctionX<Integer,Integer,X> wrapped = sutThrowing.handleToCharBiFuncX(handler -> handler
                .wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED)
                .throwIf(IndexOutOfBoundsException.class));

        // then
        try {
            wrapped.doApplyAsChar(100,100);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                    .hasNoCause();
        }
    }

@Test
    public void testHandleToCharBiFuncXMethodDoNotWrapsOtherExceptionWhen() throws X {

        // given
        LToCharBiFunctionX<Integer,Integer,X> sutThrowing = LToCharBiFunctionX.lX((a1,a2) -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LToCharBiFunctionX<Integer,Integer,X> wrapped = sutThrowing.handleToCharBiFuncX(handler -> handler
                .wrapWhen(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED)
                .throwIf(IndexOutOfBoundsException.class));

        // then
        try {
            wrapped.doApplyAsChar(100,100);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                    .hasNoCause();
        }
    }


    @Test
    public void testHandleToCharBiFuncXMishandlingExceptionIsAllowed() throws X {

        // given
        LToCharBiFunctionX<Integer,Integer,X> sutThrowing = LToCharBiFunctionX.lX((a1,a2) -> {
            throw (X) new ParseException(ORIGINAL_MESSAGE, 0);
        });

        // when
        LToCharBiFunctionX<Integer,Integer,X> wrapped = sutThrowing.handleToCharBiFuncX(h -> Function4U.doNothing());

        // then
        try {
            wrapped.doApplyAsChar(100,100);
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
    public void testToCharBiFuncCompose() throws X {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LToCharBiFunctionX<Integer,Integer,X> sutO = (a1,a2) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(90);
                assertThat(a2).isEqualTo(91);
                return '\u0100';
        };

        LFunctionX<Integer,Integer,X> before1 = p0 -> {
            assertThat(p0).isEqualTo(80);
            beforeCalls.incrementAndGet();
            return 90;
        };
        LFunctionX<Integer,Integer,X> before2 = p1 -> {
            assertThat(p1).isEqualTo(81);
            beforeCalls.incrementAndGet();
            return 91;
        };

        //when
        LToCharBiFunctionX<Integer,Integer,X> function = sutO.toCharBiFuncCompose(before1,before2);
        function.doApplyAsChar(80,81);

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
        LToCharBiFunctionX<Integer,Integer,X> sutO = (a1,a2) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(80);
                assertThat(a2).isEqualTo(81);
                return '\u0090';
        };

        LCharFunctionX<Integer,X> thenFunction = p -> {
                thenFunctionCalled.set(true);
                // char
                assertThat(p).isEqualTo('\u0090');
                // Integer
                return 100;
        };

        //when
        LBiFunctionX<Integer,Integer,Integer,X> function = sutO.then(thenFunction);
        Integer finalValue = function.doApply(80,81);

        //then - finals
        assertThat(finalValue).isEqualTo(100);
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    // </editor-fold>

    @Test
    public void testNesting() {
        assertThat(sut.nestingToCharBiFunc())
            .isInstanceOf(LToCharBiFunction.class);
    }

    @Test
    public void testShoving() {
        assertThat(sut.shovingToCharBiFunc())
            .isInstanceOf(LToCharBiFunction.class);
    }

    @Test
    public void testNestingX() {
        assertThat(sut.nestingToCharBiFuncX())
            .isInstanceOf(LToCharBiFunctionX.class);
    }

    @Test
    public void testShovingX() {
        assertThat(sut.shovingToCharBiFuncX())
            .isInstanceOf(LToCharBiFunctionX.class);
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void testShove() {

        // given
        LToCharBiFunctionX<Integer,Integer,X> sutThrowing = LToCharBiFunctionX.lX((a1,a2) -> {
            throw new UnsupportedOperationException();
        });

        // when
        sutThrowing.shovingToCharBiFunc().doApplyAsChar(100,100);
    }

    @Test
    public void testHandleToCharBiFunc() throws X {

        // given
        LToCharBiFunctionX<Integer,Integer,X> sutThrowing = LToCharBiFunctionX.lX((a1,a2) -> {
            throw new UnsupportedOperationException();
        });

        // when
        LToCharBiFunctionX<Integer,Integer,X> wrapped = sutThrowing.handleToCharBiFuncX(h -> {
            h.wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED);
        });

        // then
        try {
            wrapped.doApplyAsChar(100,100);
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
                .contains("LToCharBiFunctionX: char doApplyAsChar(T1 a1,T2 a2) throws X");
    }


    @Test
    public void isThrowing() {
        assertThat(sut.isThrowing())
            .isTrue();
    }

    //<editor-fold desc="Variants">

    private char variantV1(Integer a2,Integer a1) {
        return '\u0100';
    }

    @Test
    public void compilerSubstituteVariantV1() {
        LToCharBiFunctionX lambda = LToCharBiFunctionX./*<T1,T2,X>*/lX1(this::variantV1);

        assertThat(lambda).isInstanceOf(LToCharBiFunctionX.V1.class);
    }

    //</editor-fold>


    @Test void safeCompiles() {
        LToCharBiFunctionX r1 = LToCharBiFunctionX.safe(sut); //NOSONAR
    }

    @Test void safePropagates() {
        Object result = LToCharBiFunctionX.safe(sut);
        assertThat(result).isSameAs(sut);
    }

    @Test void safeProtectsAgainstNpe() {
        Object result = LToCharBiFunctionX.safe(null);
        assertThat(result).isSameAs(LToCharBiFunctionX.lX(LToCharBiFunctionX.safe()));
    }

    @Test <Y extends Throwable> void safeSupplierPropagates() {
        LSupplierX<LToCharBiFunctionX<Integer,Integer,X>,Y> supplier = ()->sut;
        Object result = LToCharBiFunctionX.safeSupplier(supplier);
        assertThat(result).isSameAs(supplier);
    }

    @Test <Y extends Throwable> void safeSupplierProtectsAgainstNpe() {
        Object result = LToCharBiFunctionX.safeSupplier(null);
        assertThat(result).isSameAs(LToCharBiFunctionX.safeSupplier());
    }

    @Test <Y extends Throwable> void safeSupplierCompiles() {
        LSupplierX<LToCharBiFunctionX<Integer,Integer,X>,Y> r1 = LToCharBiFunctionX.safeSupplier(()->sut);  //NOSONAR
    }

}
