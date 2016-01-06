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
public class LCharBinaryOperatorXTest<X extends ParseException> {
    private static final String ORIGINAL_MESSAGE = "Original message";
    private static final String EXCEPTION_WAS_WRAPPED = "Exception was wrapped.";
    private static final String NO_EXCEPTION_WERE_THROWN = "No exception were thrown.";

    private char testValue = (char)100;



    private LCharBinaryOperatorX<X> sut = new LCharBinaryOperatorX(){
        public  char doApplyAsChar(char a1,char a2) throws ParseException {
            return testValue;
        }
    };

    private LCharBinaryOperator opposite = new LCharBinaryOperator(){
        public  char doApplyAsChar(char a1,char a2)  {
            return testValue;
        }
    };



    private LCharBinaryOperatorX<ParseException> sutAlwaysThrowing = LCharBinaryOperatorX.lX((char a1,char a2) -> {
            throw new ParseException(ORIGINAL_MESSAGE, 0);
    });

    private LCharBinaryOperatorX<RuntimeException> sutAlwaysThrowingUnckeck = LCharBinaryOperatorX.lX((char a1,char a2) -> {
            throw new IndexOutOfBoundsException(ORIGINAL_MESSAGE);
    });


    @Test
    public void testTheResult() throws X {
        assertThat(sut.doApplyAsChar((char)100,(char)100))
            .isEqualTo(testValue);
    }

    @Test
    public void testTupleCall() throws X {

        LCharPair domainObject = Tuple4U.tuple((char)100,(char)100);

        Object result = sut.tupleApplyAsChar(domainObject);

        assertThat(result)
            .isEqualTo(testValue);
    }

    @Test
    public void testNonNullDoApplyAsChar() throws X {
        assertThat(sut.nonNullDoApplyAsChar((char)100,(char)100))
            .isEqualTo(testValue);
    }

    @Test
    public void testNestingDoApplyAsCharChecked() throws X {

        // then
        try {
            sutAlwaysThrowing.nestingDoApplyAsChar((char)100,(char)100);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(NestedException.class)
                    .hasCauseExactlyInstanceOf(ParseException.class)
                    .hasMessage(ORIGINAL_MESSAGE);
        }
    }

    @Test
    public void testNestingDoApplyAsCharUnckeck() throws X {

        // then
        try {
            sutAlwaysThrowingUnckeck.nestingDoApplyAsChar((char)100,(char)100);
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
            sutAlwaysThrowing.shovingDoApplyAsChar((char)100,(char)100);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(ParseException.class)
                    .hasNoCause()
                    .hasMessage(ORIGINAL_MESSAGE);
        }
    }

    @Test
    public void testShovingDoApplyAsCharUnckeck() throws X {

        // then
        try {
            sutAlwaysThrowingUnckeck.shovingDoApplyAsChar((char)100,(char)100);
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
            .isEqualTo("LCharBinaryOperatorX: char doApplyAsChar(char a1,char a2) throws X");
    }

    @Test
    public void testLXMethod() throws X {
        assertThat(LCharBinaryOperatorX.lX((char a1,char a2) -> testValue ))
            .isInstanceOf(LCharBinaryOperatorX.class);
    }

    @Test
    public void testWrapXMethod() throws X {
        assertThat(LCharBinaryOperatorX.wrapX(opposite))
            .isInstanceOf(LCharBinaryOperatorX.class);
    }


    @Test
    public void testWrapExceptionMethodWrapsTheException() throws X {

        // given
        LCharBinaryOperatorX<X> sutThrowing = LCharBinaryOperatorX.lX((char a1,char a2) -> {
            throw new UnsupportedOperationException();
        });

        // when
        LCharBinaryOperatorX<X> wrapped = sutThrowing.handleCharBinaryOpX(handler -> handler
            .wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED));

        // then
        try {
            wrapped.doApplyAsChar((char)100,(char)100);
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
        LCharBinaryOperatorX<X> sutThrowing = LCharBinaryOperatorX.lX((char a1,char a2) -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LCharBinaryOperatorX<X> wrapped = sutThrowing.handleCharBinaryOpX(handler -> handler
                .wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED)
                .throwIf(IndexOutOfBoundsException.class));

        // then
        try {
            wrapped.doApplyAsChar((char)100,(char)100);
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
        LCharBinaryOperatorX<X> sutThrowing = LCharBinaryOperatorX.lX((char a1,char a2) -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LCharBinaryOperatorX<X> wrapped = sutThrowing.handleCharBinaryOpX(handler -> handler
                .wrapWhen(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED)
                .throwIf(IndexOutOfBoundsException.class));

        // then
        try {
            wrapped.doApplyAsChar((char)100,(char)100);
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
        LCharBinaryOperatorX<X> sutThrowing = LCharBinaryOperatorX.lX((char a1,char a2) -> {
            throw (X) new ParseException(ORIGINAL_MESSAGE, 0);
        });

        // when
        LCharBinaryOperatorX<X> wrapped = sutThrowing.handleCharBinaryOpX(h -> Function4U.doNothing());

        // then
        try {
            wrapped.doApplyAsChar((char)100,(char)100);
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
        char valueSmall = (char)100;
        char valueBig = (char)(valueSmall+10);

        //when
        LCharBinaryOperatorX<X> min = LCharBinaryOperatorX.min();

        //then
        assertThat(min.doApplyAsChar(valueSmall, valueBig))
                .isEqualTo(valueSmall);

        assertThat(min.doApplyAsChar(valueBig, valueSmall))
                .isEqualTo(valueSmall);
    }

    @Test
    public void testMax() throws X {
        //given
        char valueSmall = (char)100;
        char valueBig = (char)(valueSmall+10);

        //when
        LCharBinaryOperatorX<X> max = LCharBinaryOperatorX.max();

        //then
        assertThat(max.doApplyAsChar(valueSmall, valueBig))
                .isEqualTo(valueBig);

        assertThat(max.doApplyAsChar(valueBig, valueSmall))
                .isEqualTo(valueBig);
    }


    // <editor-fold desc="compose (functional)">

    @Test
    public void testcharBinaryOpComposeChar() throws X {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LCharBinaryOperatorX<X> sutO = (char a1,char a2) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo((char)90);
                assertThat(a2).isEqualTo((char)91);
                return (char)100;
        };

        LCharUnaryOperatorX<X> before1 = p0 -> {
            assertThat(p0).isEqualTo((char)80);
            beforeCalls.incrementAndGet();
            return (char)90;
        };
        LCharUnaryOperatorX<X> before2 = p1 -> {
            assertThat(p1).isEqualTo((char)81);
            beforeCalls.incrementAndGet();
            return (char)91;
        };

        //when
        LCharBinaryOperatorX<X> function = sutO.charBinaryOpComposeChar(before1,before2);
        function.doApplyAsChar((char)80,(char)81);

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(beforeCalls.get()).isEqualTo(2);
    }


    @Test
    public void testcharBinaryOpCompose() throws X {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LCharBinaryOperatorX<X> sutO = (char a1,char a2) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo((char)90);
                assertThat(a2).isEqualTo((char)91);
                return (char)100;
        };

        LToCharFunctionX<Integer ,X> before1 = p0 -> {
            assertThat(p0).isEqualTo(Integer.valueOf(80));
            beforeCalls.incrementAndGet();
            return (char)90;
        };
        LToCharFunctionX<Integer ,X> before2 = p1 -> {
            assertThat(p1).isEqualTo(Integer.valueOf(81));
            beforeCalls.incrementAndGet();
            return (char)91;
        };

        //when
        LToCharBiFunctionX<Integer ,Integer ,X> function = sutO.charBinaryOpCompose(before1,before2);
        function.doApplyAsChar((Integer )Integer.valueOf(80),(Integer )Integer.valueOf(81));

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
        LCharBinaryOperatorX<X> sutO = (char a1,char a2) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo((char)80);
                assertThat(a2).isEqualTo((char)81);
                return (char)90;
        };

        LCharFunctionX<Integer ,X> thenFunction = p -> {
                thenFunctionCalled.set(true);
                // 
                assertThat(p).isEqualTo((char)90);
                // V
                return Integer.valueOf(100);
        };

        //when
        LBiCharFunctionX<Integer ,X> function = sutO.then(thenFunction);
        Integer  finalValue = function.doApply((char)80,(char)81);

        //then - finals
        assertThat(finalValue).isEqualTo(Integer.valueOf(100));
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    // </editor-fold>

    @Test
    public void testNesting() {
        assertThat(sut.nestingCharBinaryOp())
            .isInstanceOf(LCharBinaryOperator.class);
    }

    @Test
    public void testShoving() {
        assertThat(sut.shovingCharBinaryOp())
            .isInstanceOf(LCharBinaryOperator.class);
    }

    @Test
    public void testNestingX() {
        assertThat(sut.nestingCharBinaryOpX())
            .isInstanceOf(LCharBinaryOperatorX.class);
    }

    @Test
    public void testShovingX() {
        assertThat(sut.shovingCharBinaryOpX())
            .isInstanceOf(LCharBinaryOperatorX.class);
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void testShove() {

        // given
        LCharBinaryOperatorX<X> sutThrowing = LCharBinaryOperatorX.lX((char a1,char a2) -> {
            throw new UnsupportedOperationException();
        });

        // when
        sutThrowing.shovingCharBinaryOp().doApplyAsChar((char)100,(char)100);
    }

    @Test
    public void testHandleCharBinaryOp() throws X {

        // given
        LCharBinaryOperatorX<X> sutThrowing = LCharBinaryOperatorX.lX((char a1,char a2) -> {
            throw new UnsupportedOperationException();
        });

        // when
        LCharBinaryOperatorX<X> wrapped = sutThrowing.handleCharBinaryOpX(h -> {
            h.wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED);
        });

        // then
        try {
            wrapped.doApplyAsChar((char)100,(char)100);
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
                .contains("LCharBinaryOperatorX: char doApplyAsChar(char a1,char a2) throws X");
    }


    @Test
    public void isThrowing() {
        assertThat(sut.isThrowing())
            .isTrue();
    }

    @Test void safeCompiles() {
        LCharBinaryOperatorX r1 = LCharBinaryOperatorX.safe(sut); //NOSONAR
    }

    @Test void safePropagates() {
        Object result = LCharBinaryOperatorX.safe(sut);
        assertThat(result).isSameAs(sut);
    }

    @Test void safeProtectsAgainstNpe() {
        Object result = LCharBinaryOperatorX.safe(null);
        assertThat(result).isSameAs(LCharBinaryOperatorX.lX(LCharBinaryOperatorX.safe()));
    }

    @Test <Y extends Throwable> void safeSupplierPropagates() {
        LSupplierX<LCharBinaryOperatorX<X>,Y> supplier = ()->sut;
        Object result = LCharBinaryOperatorX.safeSupplier(supplier);
        assertThat(result).isSameAs(supplier);
    }

    @Test <Y extends Throwable> void safeSupplierProtectsAgainstNpe() {
        Object result = LCharBinaryOperatorX.safeSupplier(null);
        assertThat(result).isSameAs(LCharBinaryOperatorX.safeSupplier());
    }

    @Test <Y extends Throwable> void safeSupplierCompiles() {
        LSupplierX<LCharBinaryOperatorX<X>,Y> r1 = LCharBinaryOperatorX.safeSupplier(()->sut);  //NOSONAR
    }

}
