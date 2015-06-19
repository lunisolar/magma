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

package eu.lunisolar.magma.func.operator.unary;

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
public class LBooleanUnaryOperatorTest<X extends ParseException> {
    private static final String ORIGINAL_MESSAGE = "Original message";
    private static final String EXCEPTION_WAS_WRAPPED = "Exception was wrapped.";
    private static final String NO_EXCEPTION_WERE_THROWN = "No exception were thrown.";

    private boolean testValue = true;



    private LBooleanUnaryOperator sut = new LBooleanUnaryOperator(){
        public  boolean doApplyAsBoolean(boolean b)  {
            return testValue;
        }
    };

    private LBooleanUnaryOperatorX<X> opposite = new LBooleanUnaryOperatorX(){
        public  boolean doApplyAsBoolean(boolean b) throws ParseException {
            return testValue;
        }
    };




    private LBooleanUnaryOperator sutAlwaysThrowingUnckeck = LBooleanUnaryOperator.l((boolean b) -> {
            throw new IndexOutOfBoundsException(ORIGINAL_MESSAGE);
    });


    @Test
    public void testTheResult() throws X {
        assertThat(sut.doApplyAsBoolean(true))
            .isEqualTo(testValue);
    }

    @Test
    public void testNonNullDoApplyAsBoolean() throws X {
        assertThat(sut.nonNullDoApplyAsBoolean(true))
            .isEqualTo(testValue);
    }

    @Test
    public void testNestingDoApplyAsBoolean_unckeck() throws X {

        // then
        try {
            sutAlwaysThrowingUnckeck.nestingDoApplyAsBoolean(true);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                    .hasNoCause()
                    .hasMessage(ORIGINAL_MESSAGE);
        }
    }

    @Test
    public void testShovingDoApplyAsBoolean_unckeck() throws X {

        // then
        try {
            sutAlwaysThrowingUnckeck.shovingDoApplyAsBoolean(true);
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
            .isEqualTo("LBooleanUnaryOperator: boolean doApplyAsBoolean(boolean b)");
    }

    @Test
    public void testLMethod() throws X {
        assertThat(LBooleanUnaryOperator.l((boolean b) -> testValue ))
            .isInstanceOf(LBooleanUnaryOperator.class);
    }

    @Test
    public void testWrapMethod() throws X {
        assertThat(LBooleanUnaryOperator.wrap(opposite))
            .isInstanceOf(LBooleanUnaryOperator.class);
    }

    @Test
    public void testWrapMethodDoNotWrapsRuntimeException() throws X {
        // given
        LBooleanUnaryOperatorX<X> sutThrowing = LBooleanUnaryOperatorX.lX((boolean b) -> {
            throw new UnsupportedOperationException(ORIGINAL_MESSAGE);
        });

        // when
        LBooleanUnaryOperator wrapped = LBooleanUnaryOperator.wrap(sutThrowing);

        // then
        try {
            wrapped.doApplyAsBoolean(true);
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
        LBooleanUnaryOperatorX<ParseException> sutThrowing = LBooleanUnaryOperatorX.lX((boolean b) -> {
            throw new ParseException(ORIGINAL_MESSAGE, 0);
        });

        // when
        LBooleanUnaryOperator wrapped = LBooleanUnaryOperator.wrap(sutThrowing);

        // then
        try {
            wrapped.doApplyAsBoolean(true);
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
        LBooleanUnaryOperator sutThrowing = LBooleanUnaryOperator.l((boolean b) -> {
            throw new UnsupportedOperationException();
        });

        // when
        LBooleanUnaryOperator wrapped = sutThrowing.handle(h -> {
            h.wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED);
        });

        // then
        try {
            wrapped.doApplyAsBoolean(true);
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
        LBooleanUnaryOperator sutThrowing = LBooleanUnaryOperator.l((boolean b) -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LBooleanUnaryOperator wrapped = sutThrowing.handle(handler -> handler
                .wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED)
                .throwIf(IndexOutOfBoundsException.class));

        // then
        try {
            wrapped.doApplyAsBoolean(true);
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
        LBooleanUnaryOperator sutThrowing = LBooleanUnaryOperator.l((boolean b) -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LBooleanUnaryOperator wrapped = sutThrowing.handle(handler -> handler
                .wrapWhen(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED)
                .throwIf(IndexOutOfBoundsException.class));

        // then
        try {
            wrapped.doApplyAsBoolean(true);
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
        LBooleanUnaryOperator sutThrowing = LBooleanUnaryOperator.l((boolean b) -> {
            throw new UnsupportedOperationException(ORIGINAL_MESSAGE);
        });

        // when
        LBooleanUnaryOperator wrapped = sutThrowing.handle(h -> {
        });

        // then
        try {
            wrapped.doApplyAsBoolean(true);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
             .isExactlyInstanceOf(UnsupportedOperationException.class)
             .hasNoCause()
             .hasMessage(ORIGINAL_MESSAGE);
        }
    }

    @Test
    public void testNegate() throws X {
        assertThat(sut.negate().doApplyAsBoolean(true))
            .isEqualTo(!testValue);
    }

    @DataProvider(name="boolean permutations")
    public Object[][] permuations() {
       return new Object[][] {
            // b1 , b2   , AND  , OR   , XOR
            {false, false, false, false, false },
            {true , false, false, true , true },
            {false, true , false, true , true },
            {true , true , true , true , false },
       };
    }

    @Test(dataProvider="boolean permutations")
    public void testAndOrXor(final boolean f1Result, final boolean f2Result, final boolean andResult, final boolean orResult, final boolean xorResult) throws X {

        //given
        LBooleanUnaryOperator fun1 = LBooleanUnaryOperator.l((boolean b) -> f1Result);
        LBooleanUnaryOperator fun2 = LBooleanUnaryOperator.l((boolean b) -> f2Result);

        //when
        LBooleanUnaryOperator andFunction = fun1.and(fun2);
        LBooleanUnaryOperator orFunction = fun1.or(fun2);
        LBooleanUnaryOperator xorFunction = fun1.xor(fun2);

        //then
        assertThat(andFunction.doApplyAsBoolean(true))
                .isEqualTo(andResult);

        assertThat(orFunction.doApplyAsBoolean(true))
                .isEqualTo(orResult);

        assertThat(xorFunction.doApplyAsBoolean(true))
                .isEqualTo(xorResult);
    }

    @Test
    public void isEqual() throws X  {
        //when
        LBooleanUnaryOperator equals = LBooleanUnaryOperator.isEqual(true);

        //then
        assertThat(equals.doApplyAsBoolean(true))
                .isTrue();

        assertThat(equals.doApplyAsBoolean(false))
                .isFalse();
    }



    // <editor-fold desc="compose (functional)">

    @Test
    public void testfromBoolean() throws X {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LBooleanUnaryOperator sutO = (boolean b) -> {
                mainFunctionCalled.set(true);
                assertThat(b).isEqualTo(true);
                return true;
        };

        LBooleanUnaryOperator before1 = p0 -> {
            assertThat(p0).isEqualTo(true);
            beforeCalls.incrementAndGet();
            return true;
        };

        //when
        LBooleanUnaryOperator function = sutO.fromBoolean(before1);
        function.doApplyAsBoolean(true);

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(beforeCalls.get()).isEqualTo(1);
    }


    @Test
    public void testfrom() throws X {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LBooleanUnaryOperator sutO = (boolean b) -> {
                mainFunctionCalled.set(true);
                assertThat(b).isEqualTo(true);
                return true;
        };

        LPredicate<Integer > before1 = p0 -> {
            assertThat(p0).isEqualTo(Integer.valueOf(80));
            beforeCalls.incrementAndGet();
            return true;
        };

        //when
        LPredicate<Integer > function = sutO.from(before1);
        function.doTest((Integer )Integer.valueOf(80));

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
        LBooleanUnaryOperator sutO = (boolean b) -> {
                mainFunctionCalled.set(true);
                assertThat(b).isEqualTo(true);
                return true;
        };

        LBooleanFunction<Integer > thenFunction = p -> {
                thenFunctionCalled.set(true);
                // 
                assertThat(p).isEqualTo(true);
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
        LBooleanUnaryOperator sutO = (boolean b) -> {
                mainFunctionCalled.set(true);
                assertThat(b).isEqualTo(true);
                return true;
        };

        LBooleanToByteFunction thenFunction = p -> {
                thenFunctionCalled.set(true);
                // 
                assertThat(p).isEqualTo(true);
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
        LBooleanUnaryOperator sutO = (boolean b) -> {
                mainFunctionCalled.set(true);
                assertThat(b).isEqualTo(true);
                return true;
        };

        LBooleanToShortFunction thenFunction = p -> {
                thenFunctionCalled.set(true);
                // 
                assertThat(p).isEqualTo(true);
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
        LBooleanUnaryOperator sutO = (boolean b) -> {
                mainFunctionCalled.set(true);
                assertThat(b).isEqualTo(true);
                return true;
        };

        LBooleanToIntFunction thenFunction = p -> {
                thenFunctionCalled.set(true);
                // 
                assertThat(p).isEqualTo(true);
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
        LBooleanUnaryOperator sutO = (boolean b) -> {
                mainFunctionCalled.set(true);
                assertThat(b).isEqualTo(true);
                return true;
        };

        LBooleanToLongFunction thenFunction = p -> {
                thenFunctionCalled.set(true);
                // 
                assertThat(p).isEqualTo(true);
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
        LBooleanUnaryOperator sutO = (boolean b) -> {
                mainFunctionCalled.set(true);
                assertThat(b).isEqualTo(true);
                return true;
        };

        LBooleanToFloatFunction thenFunction = p -> {
                thenFunctionCalled.set(true);
                // 
                assertThat(p).isEqualTo(true);
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
        LBooleanUnaryOperator sutO = (boolean b) -> {
                mainFunctionCalled.set(true);
                assertThat(b).isEqualTo(true);
                return true;
        };

        LBooleanToDoubleFunction thenFunction = p -> {
                thenFunctionCalled.set(true);
                // 
                assertThat(p).isEqualTo(true);
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
        LBooleanUnaryOperator sutO = (boolean b) -> {
                mainFunctionCalled.set(true);
                assertThat(b).isEqualTo(true);
                return true;
        };

        LBooleanToCharFunction thenFunction = p -> {
                thenFunctionCalled.set(true);
                // 
                assertThat(p).isEqualTo(true);
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
        LBooleanUnaryOperator sutO = (boolean b) -> {
                mainFunctionCalled.set(true);
                assertThat(b).isEqualTo(true);
                return true;
        };

        LBooleanUnaryOperator thenFunction = p -> {
                thenFunctionCalled.set(true);
                // 
                assertThat(p).isEqualTo(true);
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
    public void identity() throws X {
        LBooleanUnaryOperator identityFunction = LBooleanUnaryOperator.identity();

        assertThat(identityFunction.doApplyAsBoolean(true)).isEqualTo(true);
    }


    @Test
    public void testNesting() {
        assertThat(sut.nest())
            .isSameAs(sut)
            .isInstanceOf(LBooleanUnaryOperator.class);
    }

    @Test
    public void testShoving() {
        assertThat(sut.shove())
            .isSameAs(sut)
            .isInstanceOf(LBooleanUnaryOperator.class);
    }

    @Test
    public void testNestingX() {
        assertThat(sut.nestX())
            .isSameAs(sut)
            .isInstanceOf(LBooleanUnaryOperatorX.class);
    }

    @Test
    public void testShovingX() {
        assertThat(sut.shoveX())
            .isSameAs(sut)
            .isInstanceOf(LBooleanUnaryOperatorX.class);
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void testShove() {

        // given
        LBooleanUnaryOperator sutThrowing = LBooleanUnaryOperator.l((boolean b) -> {
            throw new UnsupportedOperationException();
        });

        // when
        sutThrowing.shove().doApplyAsBoolean(true);
    }

    @Test
    public void testHandle() throws X {

        // given
        LBooleanUnaryOperator sutThrowing = LBooleanUnaryOperator.l((boolean b) -> {
            throw new UnsupportedOperationException();
        });

        // when
        LBooleanUnaryOperator wrapped = sutThrowing.handle(h -> {
            h.wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED);
        });

        // then
        try {
            wrapped.doApplyAsBoolean(true);
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
                .contains("LBooleanUnaryOperator: boolean doApplyAsBoolean(boolean b)");
    }


    @Test
    public void isThrowing() {
        assertThat(sut.isThrowing())
            .isFalse();
    }


}
