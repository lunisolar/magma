/*
 * (C) Copyright 2015 Lunisolar (http://lunisolar.eu/).
 *
 * This file is part of "lunisolar-magma".
 *
 * "lunisolar-magma" is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * "lunisolar-magma" is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with "lunisolar-magma".  If not, see <http://www.gnu.org/licenses/>.
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
public class LBooleanToDoubleFunctionTest<X extends ParseException> {
    private static final String ORIGINAL_MESSAGE = "Original message";
    private static final String EXCEPTION_WAS_WRAPPED = "Exception was wrapped.";
    private static final String NO_EXCEPTION_WERE_THROWN = "No exception were thrown.";

    private double testValue = (double)100;



    private LBooleanToDoubleFunction sut = new LBooleanToDoubleFunction(){
        public  double doApplyAsDouble(boolean b)  {
            return testValue;
        }
    };

    private LBooleanToDoubleFunctionX<X> opposite = new LBooleanToDoubleFunctionX(){
        public  double doApplyAsDouble(boolean b) throws ParseException {
            return testValue;
        }
    };




    private LBooleanToDoubleFunction sutAlwaysThrowingUnckeck = LBooleanToDoubleFunction.l((boolean b) -> {
            throw new IndexOutOfBoundsException(ORIGINAL_MESSAGE);
    });


    @Test
    public void testTheResult() throws X {
        assertThat(sut.doApplyAsDouble(true))
            .isEqualTo(testValue);
    }

    @Test
    public void testNonNullDoApplyAsDouble() throws X {
        assertThat(sut.nonNullDoApplyAsDouble(true))
            .isEqualTo(testValue);
    }

    @Test
    public void testNestingDoApplyAsDouble_unckeck() throws X {

        // then
        try {
            sutAlwaysThrowingUnckeck.nestingDoApplyAsDouble(true);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                    .hasNoCause()
                    .hasMessage(ORIGINAL_MESSAGE);
        }
    }

    @Test
    public void testShovingDoApplyAsDouble_unckeck() throws X {

        // then
        try {
            sutAlwaysThrowingUnckeck.shovingDoApplyAsDouble(true);
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
            .isEqualTo("LBooleanToDoubleFunction: double doApplyAsDouble(boolean b)");
    }

    @Test
    public void testLMethod() throws X {
        assertThat(LBooleanToDoubleFunction.l((boolean b) -> testValue ))
            .isInstanceOf(LBooleanToDoubleFunction.class);
    }

    @Test
    public void testWrapMethod() throws X {
        assertThat(LBooleanToDoubleFunction.wrap(opposite))
            .isInstanceOf(LBooleanToDoubleFunction.class);
    }

    @Test
    public void testWrapMethodDoNotWrapsRuntimeException() throws X {
        // given
        LBooleanToDoubleFunctionX<X> sutThrowing = LBooleanToDoubleFunctionX.lX((boolean b) -> {
            throw new UnsupportedOperationException(ORIGINAL_MESSAGE);
        });

        // when
        LBooleanToDoubleFunction wrapped = LBooleanToDoubleFunction.wrap(sutThrowing);

        // then
        try {
            wrapped.doApplyAsDouble(true);
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
        LBooleanToDoubleFunctionX<ParseException> sutThrowing = LBooleanToDoubleFunctionX.lX((boolean b) -> {
            throw new ParseException(ORIGINAL_MESSAGE, 0);
        });

        // when
        LBooleanToDoubleFunction wrapped = LBooleanToDoubleFunction.wrap(sutThrowing);

        // then
        try {
            wrapped.doApplyAsDouble(true);
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
        LBooleanToDoubleFunction sutThrowing = LBooleanToDoubleFunction.l((boolean b) -> {
            throw new UnsupportedOperationException();
        });

        // when
        LBooleanToDoubleFunction wrapped = sutThrowing.handle(h -> {
            h.wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED);
        });

        // then
        try {
            wrapped.doApplyAsDouble(true);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IllegalArgumentException.class)
                    .hasCauseExactlyInstanceOf(UnsupportedOperationException.class)
                    .hasMessage(EXCEPTION_WAS_WRAPPED);
        }
    }

    @Test
    public void testWrapExceptionMethodDoNotWrapsOtherException_if() throws X {

        // given
        LBooleanToDoubleFunction sutThrowing = LBooleanToDoubleFunction.l((boolean b) -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LBooleanToDoubleFunction wrapped = sutThrowing.handle(handler -> handler
                .wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED)
                .throwIf(IndexOutOfBoundsException.class));

        // then
        try {
            wrapped.doApplyAsDouble(true);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                    .hasNoCause();
        }
    }

@Test
    public void testWrapExceptionMethodDoNotWrapsOtherException_when() throws X {

        // given
        LBooleanToDoubleFunction sutThrowing = LBooleanToDoubleFunction.l((boolean b) -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LBooleanToDoubleFunction wrapped = sutThrowing.handle(handler -> handler
                .wrapWhen(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED)
                .throwIf(IndexOutOfBoundsException.class));

        // then
        try {
            wrapped.doApplyAsDouble(true);
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
        LBooleanToDoubleFunction sutThrowing = LBooleanToDoubleFunction.l((boolean b) -> {
            throw new UnsupportedOperationException(ORIGINAL_MESSAGE);
        });

        // when
        LBooleanToDoubleFunction wrapped = sutThrowing.handle(h -> {
        });

        // then
        try {
            wrapped.doApplyAsDouble(true);
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
    public void testfromBoolean() throws X {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LBooleanToDoubleFunction sutO = (boolean b) -> {
                mainFunctionCalled.set(true);
                assertThat(b).isEqualTo(true);
                return (double)100;
        };

        LBooleanUnaryOperator before1 = p0 -> {
            assertThat(p0).isEqualTo(true);
            beforeCalls.incrementAndGet();
            return true;
        };

        //when
        LBooleanToDoubleFunction function = sutO.fromBoolean(before1);
        function.doApplyAsDouble(true);

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(beforeCalls.get()).isEqualTo(1);
    }


    @Test
    public void testfrom() throws X {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LBooleanToDoubleFunction sutO = (boolean b) -> {
                mainFunctionCalled.set(true);
                assertThat(b).isEqualTo(true);
                return (double)100;
        };

        LPredicate<Integer > before1 = p0 -> {
            assertThat(p0).isEqualTo(Integer.valueOf(80));
            beforeCalls.incrementAndGet();
            return true;
        };

        //when
        LToDoubleFunction<Integer > function = sutO.from(before1);
        function.doApplyAsDouble((Integer )Integer.valueOf(80));

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
        LBooleanToDoubleFunction sutO = (boolean b) -> {
                mainFunctionCalled.set(true);
                assertThat(b).isEqualTo(true);
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
        LBooleanFunction<Integer > function = sutO.then(thenFunction);
        Integer  finalValue = function.doApply(true);

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
        LBooleanToDoubleFunction sutO = (boolean b) -> {
                mainFunctionCalled.set(true);
                assertThat(b).isEqualTo(true);
                return (double)90;
        };

        LDoubleToByteFunction thenFunction = p -> {
                thenFunctionCalled.set(true);
                // 
                assertThat(p).isEqualTo((double)90);
                // byte
                return (byte)100;
        };

        //when
        LBooleanToByteFunction function = sutO.thenToByte(thenFunction);
        byte finalValue = function.doApplyAsByte(true);

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
        LBooleanToDoubleFunction sutO = (boolean b) -> {
                mainFunctionCalled.set(true);
                assertThat(b).isEqualTo(true);
                return (double)90;
        };

        LDoubleToShortFunction thenFunction = p -> {
                thenFunctionCalled.set(true);
                // 
                assertThat(p).isEqualTo((double)90);
                // short
                return (short)100;
        };

        //when
        LBooleanToShortFunction function = sutO.thenToShort(thenFunction);
        short finalValue = function.doApplyAsShort(true);

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
        LBooleanToDoubleFunction sutO = (boolean b) -> {
                mainFunctionCalled.set(true);
                assertThat(b).isEqualTo(true);
                return (double)90;
        };

        LDoubleToIntFunction thenFunction = p -> {
                thenFunctionCalled.set(true);
                // 
                assertThat(p).isEqualTo((double)90);
                // int
                return (int)100;
        };

        //when
        LBooleanToIntFunction function = sutO.thenToInt(thenFunction);
        int finalValue = function.doApplyAsInt(true);

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
        LBooleanToDoubleFunction sutO = (boolean b) -> {
                mainFunctionCalled.set(true);
                assertThat(b).isEqualTo(true);
                return (double)90;
        };

        LDoubleToLongFunction thenFunction = p -> {
                thenFunctionCalled.set(true);
                // 
                assertThat(p).isEqualTo((double)90);
                // long
                return (long)100;
        };

        //when
        LBooleanToLongFunction function = sutO.thenToLong(thenFunction);
        long finalValue = function.doApplyAsLong(true);

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
        LBooleanToDoubleFunction sutO = (boolean b) -> {
                mainFunctionCalled.set(true);
                assertThat(b).isEqualTo(true);
                return (double)90;
        };

        LDoubleToFloatFunction thenFunction = p -> {
                thenFunctionCalled.set(true);
                // 
                assertThat(p).isEqualTo((double)90);
                // float
                return (float)100;
        };

        //when
        LBooleanToFloatFunction function = sutO.thenToFloat(thenFunction);
        float finalValue = function.doApplyAsFloat(true);

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
        LBooleanToDoubleFunction sutO = (boolean b) -> {
                mainFunctionCalled.set(true);
                assertThat(b).isEqualTo(true);
                return (double)90;
        };

        LDoubleUnaryOperator thenFunction = p -> {
                thenFunctionCalled.set(true);
                // 
                assertThat(p).isEqualTo((double)90);
                // double
                return (double)100;
        };

        //when
        LBooleanToDoubleFunction function = sutO.thenToDouble(thenFunction);
        double finalValue = function.doApplyAsDouble(true);

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
        LBooleanToDoubleFunction sutO = (boolean b) -> {
                mainFunctionCalled.set(true);
                assertThat(b).isEqualTo(true);
                return (double)90;
        };

        LDoubleToCharFunction thenFunction = p -> {
                thenFunctionCalled.set(true);
                // 
                assertThat(p).isEqualTo((double)90);
                // char
                return (char)100;
        };

        //when
        LBooleanToCharFunction function = sutO.thenToChar(thenFunction);
        char finalValue = function.doApplyAsChar(true);

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
        LBooleanToDoubleFunction sutO = (boolean b) -> {
                mainFunctionCalled.set(true);
                assertThat(b).isEqualTo(true);
                return (double)90;
        };

        LDoublePredicate thenFunction = p -> {
                thenFunctionCalled.set(true);
                // 
                assertThat(p).isEqualTo((double)90);
                // boolean
                return true;
        };

        //when
        LBooleanUnaryOperator function = sutO.thenToBoolean(thenFunction);
        boolean finalValue = function.doApplyAsBoolean(true);

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
            .isInstanceOf(LBooleanToDoubleFunction.class);
    }

    @Test
    public void testShoving() {
        assertThat(sut.shove())
            .isSameAs(sut)
            .isInstanceOf(LBooleanToDoubleFunction.class);
    }

    @Test
    public void testNestingX() {
        assertThat(sut.nestX())
            .isSameAs(sut)
            .isInstanceOf(LBooleanToDoubleFunctionX.class);
    }

    @Test
    public void testShovingX() {
        assertThat(sut.shoveX())
            .isSameAs(sut)
            .isInstanceOf(LBooleanToDoubleFunctionX.class);
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void testShove() {

        // given
        LBooleanToDoubleFunction sutThrowing = LBooleanToDoubleFunction.l((boolean b) -> {
            throw new UnsupportedOperationException();
        });

        // when
        sutThrowing.shove().doApplyAsDouble(true);
    }

    @Test
    public void testHandle() throws X {

        // given
        LBooleanToDoubleFunction sutThrowing = LBooleanToDoubleFunction.l((boolean b) -> {
            throw new UnsupportedOperationException();
        });

        // when
        LBooleanToDoubleFunction wrapped = sutThrowing.handle(h -> {
            h.wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED);
        });

        // then
        try {
            wrapped.doApplyAsDouble(true);
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
                .contains("LBooleanToDoubleFunction: double doApplyAsDouble(boolean b)");
    }


    @Test
    public void isThrowing() {
        assertThat(sut.isThrowing())
            .isFalse();
    }


}
