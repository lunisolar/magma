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

package eu.lunisolar.magma.func.operator.binary;

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
import eu.lunisolar.magma.func.consumer.*; // NOSONAR
import eu.lunisolar.magma.func.consumer.primitives.*; // NOSONAR
import eu.lunisolar.magma.func.consumer.primitives.bi.*; // NOSONAR
import eu.lunisolar.magma.func.consumer.primitives.tri.*; // NOSONAR
import eu.lunisolar.magma.func.consumer.primitives.obj.*; // NOSONAR
import eu.lunisolar.magma.func.action.*; // NOSONAR
import org.assertj.core.api.Assertions;  //NOSONAR
import org.testng.annotations.*;      //NOSONAR
import java.util.regex.Pattern;          //NOSONAR
import java.text.ParseException;         //NOSONAR
import eu.lunisolar.magma.basics.*; //NOSONAR
import eu.lunisolar.magma.basics.exceptions.*; //NOSONAR
import java.util.concurrent.atomic.AtomicInteger; //NOSONAR
import static org.assertj.core.api.Assertions.*; //NOSONAR

/** The test obviously concentrate on the interface methods the function it self is very simple.  */
public class LIntBinaryOperatorXTest<X extends ParseException> {
    private static final String ORIGINAL_MESSAGE = "Original message";
    private static final String EXCEPTION_WAS_WRAPPED = "Exception was wrapped.";
    private static final String NO_EXCEPTION_WERE_THROWN = "No exception were thrown.";

    private int testValue = (int)100;



    private LIntBinaryOperatorX<X> sut = new LIntBinaryOperatorX(){
        public  int doApplyAsInt(int i1,int i2) throws ParseException {
            return testValue;
        }
    };

    private LIntBinaryOperator opposite = new LIntBinaryOperator(){
        public  int doApplyAsInt(int i1,int i2)  {
            return testValue;
        }
    };


    private java.util.function.IntBinaryOperator jre = (int i1,int i2) -> testValue;


    private LIntBinaryOperatorX<ParseException> sutAlwaysThrowing = LIntBinaryOperatorX.lX((int i1,int i2) -> {
            throw new ParseException(ORIGINAL_MESSAGE, 0);
    });

    private LIntBinaryOperatorX<RuntimeException> sutAlwaysThrowingUnckeck = LIntBinaryOperatorX.lX((int i1,int i2) -> {
            throw new IndexOutOfBoundsException(ORIGINAL_MESSAGE);
    });


    @Test
    public void testTheResult() throws X {
        assertThat(sut.doApplyAsInt((int)100,(int)100))
            .isEqualTo(testValue);
    }

    @Test
    public void testNonNullDoApplyAsInt() throws X {
        assertThat(sut.nonNullDoApplyAsInt((int)100,(int)100))
            .isEqualTo(testValue);
    }

    @Test
    public void testNestingDoApplyAsIntChecked() throws X {

        // then
        try {
            sutAlwaysThrowing.nestingDoApplyAsInt((int)100,(int)100);
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
            sutAlwaysThrowingUnckeck.nestingDoApplyAsInt((int)100,(int)100);
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
            sutAlwaysThrowing.shovingDoApplyAsInt((int)100,(int)100);
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
            sutAlwaysThrowingUnckeck.shovingDoApplyAsInt((int)100,(int)100);
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
            .isEqualTo("LIntBinaryOperatorX: int doApplyAsInt(int i1,int i2) throws X");
    }

    @Test
    public void testLXMethod() throws X {
        assertThat(LIntBinaryOperatorX.lX((int i1,int i2) -> testValue ))
            .isInstanceOf(LIntBinaryOperatorX.class);
    }

    @Test
    public void testWrapXMethod() throws X {
        assertThat(LIntBinaryOperatorX.wrapX(opposite))
            .isInstanceOf(LIntBinaryOperatorX.class);
    }

    @Test
    public void testWrapStdMethod() throws X {
        assertThat(LIntBinaryOperatorX.wrap(jre))
            .isInstanceOf(LIntBinaryOperatorX.class);
    }


    @Test
    public void testWrapExceptionMethodWrapsTheException() throws X {

        // given
        LIntBinaryOperatorX<X> sutThrowing = LIntBinaryOperatorX.lX((int i1,int i2) -> {
            throw new UnsupportedOperationException();
        });

        // when
        LIntBinaryOperatorX<X> wrapped = sutThrowing.handleIntBinaryOpX(handler -> handler
            .wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED));

        // then
        try {
            wrapped.doApplyAsInt((int)100,(int)100);
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
        LIntBinaryOperatorX<X> sutThrowing = LIntBinaryOperatorX.lX((int i1,int i2) -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LIntBinaryOperatorX<X> wrapped = sutThrowing.handleIntBinaryOpX(handler -> handler
                .wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED)
                .throwIf(IndexOutOfBoundsException.class));

        // then
        try {
            wrapped.doApplyAsInt((int)100,(int)100);
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
        LIntBinaryOperatorX<X> sutThrowing = LIntBinaryOperatorX.lX((int i1,int i2) -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LIntBinaryOperatorX<X> wrapped = sutThrowing.handleIntBinaryOpX(handler -> handler
                .wrapWhen(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED)
                .throwIf(IndexOutOfBoundsException.class));

        // then
        try {
            wrapped.doApplyAsInt((int)100,(int)100);
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
        LIntBinaryOperatorX<X> sutThrowing = LIntBinaryOperatorX.lX((int i1,int i2) -> {
            throw (X) new ParseException(ORIGINAL_MESSAGE, 0);
        });

        // when
        LIntBinaryOperatorX<X> wrapped = sutThrowing.handleIntBinaryOpX(h -> Function4U.doNothing());

        // then
        try {
            wrapped.doApplyAsInt((int)100,(int)100);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(NestedException.class)
                    .hasCauseExactlyInstanceOf(ParseException.class)
                    .hasMessage(ORIGINAL_MESSAGE);
        }
    }


    @Test
    public void testMin() throws X {
        //given
        int valueSmall = (int)100;
        int valueBig = (int)(valueSmall+10);

        //when
        LIntBinaryOperatorX<X> min = LIntBinaryOperatorX.min();

        //then
        assertThat(min.doApplyAsInt(valueSmall, valueBig))
                .isEqualTo(valueSmall);

        assertThat(min.doApplyAsInt(valueBig, valueSmall))
                .isEqualTo(valueSmall);
    }

    @Test
    public void testMax() throws X {
        //given
        int valueSmall = (int)100;
        int valueBig = (int)(valueSmall+10);

        //when
        LIntBinaryOperatorX<X> max = LIntBinaryOperatorX.max();

        //then
        assertThat(max.doApplyAsInt(valueSmall, valueBig))
                .isEqualTo(valueBig);

        assertThat(max.doApplyAsInt(valueBig, valueSmall))
                .isEqualTo(valueBig);
    }


    // <editor-fold desc="compose (functional)">

    @Test
    public void testintBinaryOpComposeInt() throws X {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LIntBinaryOperatorX<X> sutO = (int i1,int i2) -> {
                mainFunctionCalled.set(true);
                assertThat(i1).isEqualTo((int)90);
                assertThat(i2).isEqualTo((int)91);
                return (int)100;
        };

        LIntUnaryOperatorX<X> before1 = p0 -> {
            assertThat(p0).isEqualTo((int)80);
            beforeCalls.incrementAndGet();
            return (int)90;
        };
        LIntUnaryOperatorX<X> before2 = p1 -> {
            assertThat(p1).isEqualTo((int)81);
            beforeCalls.incrementAndGet();
            return (int)91;
        };

        //when
        LIntBinaryOperatorX<X> function = sutO.intBinaryOpComposeInt(before1,before2);
        function.doApplyAsInt((int)80,(int)81);

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(beforeCalls.get()).isEqualTo(2);
    }


    @Test
    public void testintBinaryOpCompose() throws X {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LIntBinaryOperatorX<X> sutO = (int i1,int i2) -> {
                mainFunctionCalled.set(true);
                assertThat(i1).isEqualTo((int)90);
                assertThat(i2).isEqualTo((int)91);
                return (int)100;
        };

        LToIntFunctionX<Integer ,X> before1 = p0 -> {
            assertThat(p0).isEqualTo(Integer.valueOf(80));
            beforeCalls.incrementAndGet();
            return (int)90;
        };
        LToIntFunctionX<Integer ,X> before2 = p1 -> {
            assertThat(p1).isEqualTo(Integer.valueOf(81));
            beforeCalls.incrementAndGet();
            return (int)91;
        };

        //when
        LToIntBiFunctionX<Integer ,Integer ,X> function = sutO.intBinaryOpCompose(before1,before2);
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
        LIntBinaryOperatorX<X> sutO = (int i1,int i2) -> {
                mainFunctionCalled.set(true);
                assertThat(i1).isEqualTo((int)80);
                assertThat(i2).isEqualTo((int)81);
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
        LBiIntFunctionX<Integer ,X> function = sutO.then(thenFunction);
        Integer  finalValue = function.doApply((int)80,(int)81);

        //then - finals
        assertThat(finalValue).isEqualTo(Integer.valueOf(100));
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    // </editor-fold>

    @Test
    public void testNesting() {
        assertThat(sut.nestingIntBinaryOp())
            .isInstanceOf(LIntBinaryOperator.class);
    }

    @Test
    public void testShoving() {
        assertThat(sut.shovingIntBinaryOp())
            .isInstanceOf(LIntBinaryOperator.class);
    }

    @Test
    public void testNestingX() {
        assertThat(sut.nestingIntBinaryOpX())
            .isInstanceOf(LIntBinaryOperatorX.class);
    }

    @Test
    public void testShovingX() {
        assertThat(sut.shovingIntBinaryOpX())
            .isInstanceOf(LIntBinaryOperatorX.class);
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void testShove() {

        // given
        LIntBinaryOperatorX<X> sutThrowing = LIntBinaryOperatorX.lX((int i1,int i2) -> {
            throw new UnsupportedOperationException();
        });

        // when
        sutThrowing.shovingIntBinaryOp().doApplyAsInt((int)100,(int)100);
    }

    @Test
    public void testHandleIntBinaryOp() throws X {

        // given
        LIntBinaryOperatorX<X> sutThrowing = LIntBinaryOperatorX.lX((int i1,int i2) -> {
            throw new UnsupportedOperationException();
        });

        // when
        LIntBinaryOperatorX<X> wrapped = sutThrowing.handleIntBinaryOpX(h -> {
            h.wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED);
        });

        // then
        try {
            wrapped.doApplyAsInt((int)100,(int)100);
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
                .contains("LIntBinaryOperatorX: int doApplyAsInt(int i1,int i2) throws X");
    }


    @Test
    public void isThrowing() {
        assertThat(sut.isThrowing())
            .isTrue();
    }


}
