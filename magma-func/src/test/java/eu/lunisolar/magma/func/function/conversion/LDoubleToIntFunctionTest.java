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

package eu.lunisolar.magma.func.function.conversion;

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
public class LDoubleToIntFunctionTest<X extends ParseException> {
    private static final String ORIGINAL_MESSAGE = "Original message";
    private static final String EXCEPTION_WAS_WRAPPED = "Exception was wrapped.";
    private static final String NO_EXCEPTION_WERE_THROWN = "No exception were thrown.";

    private int testValue = (int)100;



    private LDoubleToIntFunction sut = new LDoubleToIntFunction(){
        public  int doApplyAsInt(double d)  {
            return testValue;
        }
    };

    private LDoubleToIntFunctionX<X> opposite = new LDoubleToIntFunctionX(){
        public  int doApplyAsInt(double d) throws ParseException {
            return testValue;
        }
    };


    private java.util.function.DoubleToIntFunction jre = d -> testValue;



    private LDoubleToIntFunction sutAlwaysThrowingUnckeck = LDoubleToIntFunction.l(d -> {
            throw new IndexOutOfBoundsException(ORIGINAL_MESSAGE);
    });


    @Test
    public void testTheResult() throws X {
        assertThat(sut.doApplyAsInt((double)100))
            .isEqualTo(testValue);
    }

    @Test
    public void testNonNullDoApplyAsInt() throws X {
        assertThat(sut.nonNullDoApplyAsInt((double)100))
            .isEqualTo(testValue);
    }

    @Test
    public void testNestingDoApplyAsIntUnckeck() throws X {

        // then
        try {
            sutAlwaysThrowingUnckeck.nestingDoApplyAsInt((double)100);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                    .hasNoCause()
                    .hasMessage(ORIGINAL_MESSAGE);
        }
    }

    @Test
    public void testShovingDoApplyAsIntUnckeck() throws X {

        // then
        try {
            sutAlwaysThrowingUnckeck.shovingDoApplyAsInt((double)100);
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
            .isEqualTo("LDoubleToIntFunction: int doApplyAsInt(double d)");
    }

    @Test
    public void testLMethod() throws X {
        assertThat(LDoubleToIntFunction.l(d -> testValue ))
            .isInstanceOf(LDoubleToIntFunction.class);
    }

    @Test
    public void testWrapMethod() throws X {
        assertThat(LDoubleToIntFunction.wrap(opposite))
            .isInstanceOf(LDoubleToIntFunction.class);
    }

    @Test
    public void testWrapStdMethod() throws X {
        assertThat(LDoubleToIntFunction.wrap(jre))
            .isInstanceOf(LDoubleToIntFunction.class);
    }

    @Test
    public void testWrapMethodDoNotWrapsRuntimeException() throws X {
        // given
        LDoubleToIntFunctionX<X> sutThrowing = LDoubleToIntFunctionX.lX(d -> {
            throw new UnsupportedOperationException(ORIGINAL_MESSAGE);
        });

        // when
        LDoubleToIntFunction wrapped = LDoubleToIntFunction.wrap(sutThrowing);

        // then
        try {
            wrapped.doApplyAsInt((double)100);
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
        LDoubleToIntFunctionX<ParseException> sutThrowing = LDoubleToIntFunctionX.lX(d -> {
            throw new ParseException(ORIGINAL_MESSAGE, 0);
        });

        // when
        LDoubleToIntFunction wrapped = LDoubleToIntFunction.wrap(sutThrowing);

        // then
        try {
            wrapped.doApplyAsInt((double)100);
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
        LDoubleToIntFunction sutThrowing = LDoubleToIntFunction.l(d -> {
            throw new UnsupportedOperationException();
        });

        // when
        LDoubleToIntFunction wrapped = sutThrowing.handle(handler -> handler
            .wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED));

        // then
        try {
            wrapped.doApplyAsInt((double)100);
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
        LDoubleToIntFunction sutThrowing = LDoubleToIntFunction.l(d -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LDoubleToIntFunction wrapped = sutThrowing.handle(handler -> handler
                .wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED)
                .throwIf(IndexOutOfBoundsException.class));

        // then
        try {
            wrapped.doApplyAsInt((double)100);
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
        LDoubleToIntFunction sutThrowing = LDoubleToIntFunction.l(d -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LDoubleToIntFunction wrapped = sutThrowing.handle(handler -> handler
                .wrapWhen(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED)
                .throwIf(IndexOutOfBoundsException.class));

        // then
        try {
            wrapped.doApplyAsInt((double)100);
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
        LDoubleToIntFunction sutThrowing = LDoubleToIntFunction.l(d -> {
            throw new UnsupportedOperationException(ORIGINAL_MESSAGE);
        });

        // when
        LDoubleToIntFunction wrapped = sutThrowing.handle(h -> Function4U.doNothing());

        // then
        try {
            wrapped.doApplyAsInt((double)100);
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
    public void testfromDouble() throws X {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LDoubleToIntFunction sutO = d -> {
                mainFunctionCalled.set(true);
                assertThat(d).isEqualTo((double)90);
                return (int)100;
        };

        LDoubleUnaryOperator before1 = p0 -> {
            assertThat(p0).isEqualTo((double)80);
            beforeCalls.incrementAndGet();
            return (double)90;
        };

        //when
        LDoubleToIntFunction function = sutO.fromDouble(before1);
        function.doApplyAsInt((double)80);

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(beforeCalls.get()).isEqualTo(1);
    }


    @Test
    public void testfrom() throws X {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LDoubleToIntFunction sutO = d -> {
                mainFunctionCalled.set(true);
                assertThat(d).isEqualTo((double)90);
                return (int)100;
        };

        LToDoubleFunction<Integer > before1 = p0 -> {
            assertThat(p0).isEqualTo(Integer.valueOf(80));
            beforeCalls.incrementAndGet();
            return (double)90;
        };

        //when
        LToIntFunction<Integer > function = sutO.from(before1);
        function.doApplyAsInt((Integer )Integer.valueOf(80));

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(beforeCalls.get()).isEqualTo(1);
    }

    // </editor-fold>


    // <editor-fold desc="then (functional)">

    @Test
    public void testThen0() throws X  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);


        //given (+ some assertions)
        LDoubleToIntFunction sutO = d -> {
                mainFunctionCalled.set(true);
                assertThat(d).isEqualTo((double)80);
                return (int)90;
        };

        LIntFunction<Integer > thenFunction = p -> {
                thenFunctionCalled.set(true);
                // 
                assertThat(p).isEqualTo((int)90);
                // V
                return Integer.valueOf(100);
        };

        //when
        LDoubleFunction<Integer > function = sutO.then(thenFunction);
        Integer  finalValue = function.doApply((double)80);

        //then - finals
        assertThat(finalValue).isEqualTo(Integer.valueOf(100));
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    @Test
    public void testThen1ToByte() throws X  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);


        //given (+ some assertions)
        LDoubleToIntFunction sutO = d -> {
                mainFunctionCalled.set(true);
                assertThat(d).isEqualTo((double)80);
                return (int)90;
        };

        LIntToByteFunction thenFunction = p -> {
                thenFunctionCalled.set(true);
                // 
                assertThat(p).isEqualTo((int)90);
                // byte
                return (byte)100;
        };

        //when
        LDoubleToByteFunction function = sutO.thenToByte(thenFunction);
        byte finalValue = function.doApplyAsByte((double)80);

        //then - finals
        assertThat(finalValue).isEqualTo((byte)100);
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    @Test
    public void testThen2ToShort() throws X  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);


        //given (+ some assertions)
        LDoubleToIntFunction sutO = d -> {
                mainFunctionCalled.set(true);
                assertThat(d).isEqualTo((double)80);
                return (int)90;
        };

        LIntToShortFunction thenFunction = p -> {
                thenFunctionCalled.set(true);
                // 
                assertThat(p).isEqualTo((int)90);
                // short
                return (short)100;
        };

        //when
        LDoubleToShortFunction function = sutO.thenToShort(thenFunction);
        short finalValue = function.doApplyAsShort((double)80);

        //then - finals
        assertThat(finalValue).isEqualTo((short)100);
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    @Test
    public void testThen3ToInt() throws X  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);


        //given (+ some assertions)
        LDoubleToIntFunction sutO = d -> {
                mainFunctionCalled.set(true);
                assertThat(d).isEqualTo((double)80);
                return (int)90;
        };

        LIntUnaryOperator thenFunction = p -> {
                thenFunctionCalled.set(true);
                // 
                assertThat(p).isEqualTo((int)90);
                // int
                return (int)100;
        };

        //when
        LDoubleToIntFunction function = sutO.thenToInt(thenFunction);
        int finalValue = function.doApplyAsInt((double)80);

        //then - finals
        assertThat(finalValue).isEqualTo((int)100);
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    @Test
    public void testThen4ToLong() throws X  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);


        //given (+ some assertions)
        LDoubleToIntFunction sutO = d -> {
                mainFunctionCalled.set(true);
                assertThat(d).isEqualTo((double)80);
                return (int)90;
        };

        LIntToLongFunction thenFunction = p -> {
                thenFunctionCalled.set(true);
                // 
                assertThat(p).isEqualTo((int)90);
                // long
                return (long)100;
        };

        //when
        LDoubleToLongFunction function = sutO.thenToLong(thenFunction);
        long finalValue = function.doApplyAsLong((double)80);

        //then - finals
        assertThat(finalValue).isEqualTo((long)100);
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    @Test
    public void testThen5ToFloat() throws X  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);


        //given (+ some assertions)
        LDoubleToIntFunction sutO = d -> {
                mainFunctionCalled.set(true);
                assertThat(d).isEqualTo((double)80);
                return (int)90;
        };

        LIntToFloatFunction thenFunction = p -> {
                thenFunctionCalled.set(true);
                // 
                assertThat(p).isEqualTo((int)90);
                // float
                return (float)100;
        };

        //when
        LDoubleToFloatFunction function = sutO.thenToFloat(thenFunction);
        float finalValue = function.doApplyAsFloat((double)80);

        //then - finals
        assertThat(finalValue).isEqualTo((float)100);
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    @Test
    public void testThen6ToDouble() throws X  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);


        //given (+ some assertions)
        LDoubleToIntFunction sutO = d -> {
                mainFunctionCalled.set(true);
                assertThat(d).isEqualTo((double)80);
                return (int)90;
        };

        LIntToDoubleFunction thenFunction = p -> {
                thenFunctionCalled.set(true);
                // 
                assertThat(p).isEqualTo((int)90);
                // double
                return (double)100;
        };

        //when
        LDoubleUnaryOperator function = sutO.thenToDouble(thenFunction);
        double finalValue = function.doApplyAsDouble((double)80);

        //then - finals
        assertThat(finalValue).isEqualTo((double)100);
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    @Test
    public void testThen7ToChar() throws X  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);


        //given (+ some assertions)
        LDoubleToIntFunction sutO = d -> {
                mainFunctionCalled.set(true);
                assertThat(d).isEqualTo((double)80);
                return (int)90;
        };

        LIntToCharFunction thenFunction = p -> {
                thenFunctionCalled.set(true);
                // 
                assertThat(p).isEqualTo((int)90);
                // char
                return (char)100;
        };

        //when
        LDoubleToCharFunction function = sutO.thenToChar(thenFunction);
        char finalValue = function.doApplyAsChar((double)80);

        //then - finals
        assertThat(finalValue).isEqualTo((char)100);
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    @Test
    public void testThen8ToBoolean() throws X  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);


        //given (+ some assertions)
        LDoubleToIntFunction sutO = d -> {
                mainFunctionCalled.set(true);
                assertThat(d).isEqualTo((double)80);
                return (int)90;
        };

        LIntPredicate thenFunction = p -> {
                thenFunctionCalled.set(true);
                // 
                assertThat(p).isEqualTo((int)90);
                // boolean
                return true;
        };

        //when
        LDoublePredicate function = sutO.thenToBoolean(thenFunction);
        boolean finalValue = function.doTest((double)80);

        //then - finals
        assertThat(finalValue).isEqualTo(true);
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    // </editor-fold>

    @Test
    public void testNesting() {
        assertThat(sut.nest())
            .isSameAs(sut)
            .isInstanceOf(LDoubleToIntFunction.class);
    }

    @Test
    public void testShoving() {
        assertThat(sut.shove())
            .isSameAs(sut)
            .isInstanceOf(LDoubleToIntFunction.class);
    }

    @Test
    public void testNestingX() {
        assertThat(sut.nestX())
            .isSameAs(sut)
            .isInstanceOf(LDoubleToIntFunctionX.class);
    }

    @Test
    public void testShovingX() {
        assertThat(sut.shoveX())
            .isSameAs(sut)
            .isInstanceOf(LDoubleToIntFunctionX.class);
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void testShove() {

        // given
        LDoubleToIntFunction sutThrowing = LDoubleToIntFunction.l(d -> {
            throw new UnsupportedOperationException();
        });

        // when
        sutThrowing.shove().doApplyAsInt((double)100);
    }

    @Test
    public void testHandle() throws X {

        // given
        LDoubleToIntFunction sutThrowing = LDoubleToIntFunction.l(d -> {
            throw new UnsupportedOperationException();
        });

        // when
        LDoubleToIntFunction wrapped = sutThrowing.handle(h -> {
            h.wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED);
        });

        // then
        try {
            wrapped.doApplyAsInt((double)100);
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
                .contains("LDoubleToIntFunction: int doApplyAsInt(double d)");
    }


    @Test
    public void isThrowing() {
        assertThat(sut.isThrowing())
            .isFalse();
    }


}
