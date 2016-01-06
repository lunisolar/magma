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
public class LCharBinaryOperatorTest<X extends ParseException> {
    private static final String ORIGINAL_MESSAGE = "Original message";
    private static final String EXCEPTION_WAS_WRAPPED = "Exception was wrapped.";
    private static final String NO_EXCEPTION_WERE_THROWN = "No exception were thrown.";

    private char testValue = (char)100;



    private LCharBinaryOperator sut = new LCharBinaryOperator(){
        public  char doApplyAsChar(char a1,char a2)  {
            return testValue;
        }
    };

    private LCharBinaryOperatorX<X> opposite = new LCharBinaryOperatorX(){
        public  char doApplyAsChar(char a1,char a2) throws ParseException {
            return testValue;
        }
    };




    private LCharBinaryOperator sutAlwaysThrowingUnckeck = LCharBinaryOperator.l((char a1,char a2) -> {
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
            .isEqualTo("LCharBinaryOperator: char doApplyAsChar(char a1,char a2)");
    }

    @Test
    public void testLMethod() throws X {
        assertThat(LCharBinaryOperator.l((char a1,char a2) -> testValue ))
            .isInstanceOf(LCharBinaryOperator.class);
    }

    @Test
    public void testWrapMethod() throws X {
        assertThat(LCharBinaryOperator.wrap(opposite))
            .isInstanceOf(LCharBinaryOperator.class);
    }

    @Test
    public void testWrapMethodDoNotWrapsRuntimeException() throws X {
        // given
        LCharBinaryOperatorX<X> sutThrowing = LCharBinaryOperatorX.lX((char a1,char a2) -> {
            throw new UnsupportedOperationException(ORIGINAL_MESSAGE);
        });

        // when
        LCharBinaryOperator wrapped = LCharBinaryOperator.wrap(sutThrowing);

        // then
        try {
            wrapped.doApplyAsChar((char)100,(char)100);
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
        LCharBinaryOperatorX<ParseException> sutThrowing = LCharBinaryOperatorX.lX((char a1,char a2) -> {
            throw new ParseException(ORIGINAL_MESSAGE, 0);
        });

        // when
        LCharBinaryOperator wrapped = LCharBinaryOperator.wrap(sutThrowing);

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
    public void testWrapExceptionMethodWrapsTheException() throws X {

        // given
        LCharBinaryOperator sutThrowing = LCharBinaryOperator.l((char a1,char a2) -> {
            throw new UnsupportedOperationException();
        });

        // when
        LCharBinaryOperator wrapped = sutThrowing.handleCharBinaryOp(handler -> handler
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
        LCharBinaryOperator sutThrowing = LCharBinaryOperator.l((char a1,char a2) -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LCharBinaryOperator wrapped = sutThrowing.handleCharBinaryOp(handler -> handler
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
        LCharBinaryOperator sutThrowing = LCharBinaryOperator.l((char a1,char a2) -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LCharBinaryOperator wrapped = sutThrowing.handleCharBinaryOp(handler -> handler
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
        LCharBinaryOperator sutThrowing = LCharBinaryOperator.l((char a1,char a2) -> {
            throw new UnsupportedOperationException(ORIGINAL_MESSAGE);
        });

        // when
        LCharBinaryOperator wrapped = sutThrowing.handleCharBinaryOp(h -> Function4U.doNothing());

        // then
        try {
            wrapped.doApplyAsChar((char)100,(char)100);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
             .isExactlyInstanceOf(UnsupportedOperationException.class)
             .hasNoCause()
             .hasMessage(ORIGINAL_MESSAGE);
        }
    }


    @Test
    public void testMin() throws X {
        //given
        char valueSmall = (char)100;
        char valueBig = (char)(valueSmall+10);

        //when
        LCharBinaryOperator min = LCharBinaryOperator.min();

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
        LCharBinaryOperator max = LCharBinaryOperator.max();

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
        LCharBinaryOperator sutO = (char a1,char a2) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo((char)90);
                assertThat(a2).isEqualTo((char)91);
                return (char)100;
        };

        LCharUnaryOperator before1 = p0 -> {
            assertThat(p0).isEqualTo((char)80);
            beforeCalls.incrementAndGet();
            return (char)90;
        };
        LCharUnaryOperator before2 = p1 -> {
            assertThat(p1).isEqualTo((char)81);
            beforeCalls.incrementAndGet();
            return (char)91;
        };

        //when
        LCharBinaryOperator function = sutO.charBinaryOpComposeChar(before1,before2);
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
        LCharBinaryOperator sutO = (char a1,char a2) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo((char)90);
                assertThat(a2).isEqualTo((char)91);
                return (char)100;
        };

        LToCharFunction<Integer > before1 = p0 -> {
            assertThat(p0).isEqualTo(Integer.valueOf(80));
            beforeCalls.incrementAndGet();
            return (char)90;
        };
        LToCharFunction<Integer > before2 = p1 -> {
            assertThat(p1).isEqualTo(Integer.valueOf(81));
            beforeCalls.incrementAndGet();
            return (char)91;
        };

        //when
        LToCharBiFunction<Integer ,Integer > function = sutO.charBinaryOpCompose(before1,before2);
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
        LCharBinaryOperator sutO = (char a1,char a2) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo((char)80);
                assertThat(a2).isEqualTo((char)81);
                return (char)90;
        };

        LCharFunction<Integer > thenFunction = p -> {
                thenFunctionCalled.set(true);
                // 
                assertThat(p).isEqualTo((char)90);
                // V
                return Integer.valueOf(100);
        };

        //when
        LBiCharFunction<Integer > function = sutO.then(thenFunction);
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
            .isSameAs(sut)
            .isInstanceOf(LCharBinaryOperator.class);
    }

    @Test
    public void testShoving() {
        assertThat(sut.shovingCharBinaryOp())
            .isSameAs(sut)
            .isInstanceOf(LCharBinaryOperator.class);
    }

    @Test
    public void testNestingX() {
        assertThat(sut.nestingCharBinaryOpX())
            .isSameAs(sut)
            .isInstanceOf(LCharBinaryOperatorX.class);
    }

    @Test
    public void testShovingX() {
        assertThat(sut.shovingCharBinaryOpX())
            .isSameAs(sut)
            .isInstanceOf(LCharBinaryOperatorX.class);
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void testShove() {

        // given
        LCharBinaryOperator sutThrowing = LCharBinaryOperator.l((char a1,char a2) -> {
            throw new UnsupportedOperationException();
        });

        // when
        sutThrowing.shovingCharBinaryOp().doApplyAsChar((char)100,(char)100);
    }

    @Test
    public void testHandleCharBinaryOp() throws X {

        // given
        LCharBinaryOperator sutThrowing = LCharBinaryOperator.l((char a1,char a2) -> {
            throw new UnsupportedOperationException();
        });

        // when
        LCharBinaryOperator wrapped = sutThrowing.handleCharBinaryOp(h -> {
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
                .contains("LCharBinaryOperator: char doApplyAsChar(char a1,char a2)");
    }


    @Test
    public void isThrowing() {
        assertThat(sut.isThrowing())
            .isFalse();
    }

    @Test void safeCompiles() {
        LCharBinaryOperator r1 = LCharBinaryOperator.safe(sut); //NOSONAR
        LCharBinaryOperatorX r2 = LCharBinaryOperator.safe(sut); //NOSONAR
    }

    @Test void safePropagates() {
        Object result = LCharBinaryOperator.safe(sut);
        assertThat(result).isSameAs(sut);
    }

    @Test void safeProtectsAgainstNpe() {
        Object result = LCharBinaryOperator.safe(null);
        assertThat(result).isSameAs(LCharBinaryOperator.l(LCharBinaryOperator.safe()));
    }

    @Test  void safeSupplierPropagates() {
        LSupplier<LCharBinaryOperator> supplier = ()->sut;
        Object result = LCharBinaryOperator.safeSupplier(supplier);
        assertThat(result).isSameAs(supplier);
    }

    @Test  void safeSupplierProtectsAgainstNpe() {
        Object result = LCharBinaryOperator.safeSupplier(null);
        assertThat(result).isSameAs(LCharBinaryOperator.safeSupplier());
    }

    @Test  void safeSupplierCompiles() {
        LSupplier<LCharBinaryOperator> r1 = LCharBinaryOperator.safeSupplier(()->sut);  //NOSONAR
    }

}
