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
 * Foobar is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with Foobar.  If not, see <http://www.gnu.org/licenses/>.
 */

package eu.lunisolar.magma.func.predicate;

import eu.lunisolar.magma.func.*; // NOSONAR
import javax.annotation.Nonnull; // NOSONAR
import javax.annotation.Nullable; // NOSONAR
import java.util.Objects;// NOSONAR
import eu.lunisolar.magma.basics.meta.*; // NOSONAR
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
import java.util.concurrent.atomic.AtomicInteger; //NOSONAR
import static org.assertj.core.api.Assertions.*; //NOSONAR

/** The test obviously concentrate on the interface methods the function it self is very simple.  */
public class LongPredicateTest<X extends ParseException> {
    private static final String ORIGINAL_MESSAGE = "Original message";
    private static final String EXCEPTION_WAS_WRAPPED = "Exception was wrapped.";
    private static final String NO_EXCEPTION_WERE_THROWN = "No exception were thrown.";

    private boolean testValue = true;



    private LongPredicate sut = new LongPredicate(){
        public  boolean test(long l)  {
            return testValue;
        }
    };

    private LongPredicateX<X> opposite = new LongPredicateX(){
        public  boolean test(long l) throws ParseException {
            return testValue;
        }
    };


    private java.util.function.LongPredicate jre = (long l) -> testValue;


    @Test
    public void testTheResult() throws ParseException {
        assertThat(sut.test((long)100))
            .isEqualTo(testValue);
    }

    @Test
    public void testNonNullShouldNotModifyValue() throws ParseException {
        assertThat(sut.nonNull((long)100))
            .isEqualTo(testValue);
    }

    @Test
    public void testApplyAsBooleanShouldNotModifyValue() throws ParseException {
        assertThat(sut.applyAsBoolean((long)100))
            .isEqualTo(testValue);

    }


    @Test
    public void testFunctionalInterfaceDescription() throws ParseException {
        assertThat(sut.functionalInterfaceDescription())
            .isEqualTo("LongPredicate: boolean test(long l)");
    }

    @Test
    public void testLMethod() throws ParseException {
        assertThat(LongPredicate.l((long l) -> testValue ))
            .isInstanceOf(LongPredicate.class);
    }

    @Test
    public void testWrapMethod() throws ParseException {
        assertThat(LongPredicate.wrap(opposite))
            .isInstanceOf(LongPredicate.class);
    }

    @Test
    public void testWrapStdMethod() throws ParseException {
        assertThat(LongPredicate.wrapStd(jre))
            .isInstanceOf(LongPredicate.class);
    }

    @Test
    public void testWrapMethodDoNotWrapsRuntimeException() throws ParseException {
        // given
        LongPredicateX<X> sutThrowing = LongPredicateX.lX((long l) -> {
            throw new UnsupportedOperationException(ORIGINAL_MESSAGE);
        });

        // when
        LongPredicate wrapped = LongPredicate.wrap(sutThrowing);

        // then
        try {
            wrapped.test((long)100);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(UnsupportedOperationException.class)
                    .hasNoCause()
                    .hasMessage(ORIGINAL_MESSAGE);
        }
    }

    @Test
    public void testWrapMethodWrapsCheckedException() throws ParseException {
        // given
        LongPredicateX<ParseException> sutThrowing = LongPredicateX.lX((long l) -> {
            throw new ParseException(ORIGINAL_MESSAGE, 0);
        });

        // when
        LongPredicate wrapped = LongPredicate.wrap(sutThrowing);

        // then
        try {
            wrapped.test((long)100);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(NestedException.class)
                    .hasCauseExactlyInstanceOf(ParseException.class)
                    .hasMessage(ORIGINAL_MESSAGE);
        }
    }


    @Test
    public void testWrapExceptionMethodWrapsTheException() throws ParseException {

        // given
        LongPredicate sutThrowing = LongPredicate.l((long l) -> {
            throw new UnsupportedOperationException();
        });

        // when
        LongPredicate wrapped = LongPredicate.wrapException(sutThrowing, UnsupportedOperationException.class, t -> {
            throw new IllegalArgumentException(EXCEPTION_WAS_WRAPPED, t);
        });

        // then
        try {
            wrapped.test((long)100);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IllegalArgumentException.class)
                    .hasCauseExactlyInstanceOf(UnsupportedOperationException.class)
                    .hasMessage(EXCEPTION_WAS_WRAPPED);
        }
    }

    @Test
    public void testWrapExceptionMethodDoNotWrapsOtherException() throws ParseException {

        // given
        LongPredicate sutThrowing = LongPredicate.l((long l) -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LongPredicate wrapped = LongPredicate.wrapException(sutThrowing, UnsupportedOperationException.class, t -> {
            throw new IllegalArgumentException(EXCEPTION_WAS_WRAPPED, t);
        });

        // then
        try {
            wrapped.test((long)100);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                    .hasNoCause();
        }
    }

    @Test
    public void testWrapExceptionMisshandlingExceptionIsDetected() throws ParseException {

        // given
        LongPredicate sutThrowing = LongPredicate.l((long l) -> {
            throw new UnsupportedOperationException();
        });

        // when
        LongPredicate wrapped = LongPredicate.wrapException(sutThrowing, UnsupportedOperationException.class, t -> {
            return null;
        });

        // then
        try {
            wrapped.test((long)100);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(ExceptionNotHandled.class)
                    .hasCauseExactlyInstanceOf(UnsupportedOperationException.class)
                    .hasMessage("Handler has not processed the exception.");
        }
    }

    @Test
    public void testNegate() throws ParseException {
        assertThat(sut.negate().test((long)100))
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
    public void testAndOrXor(final boolean f1Result, final boolean f2Result, final boolean andResult, final boolean orResult, final boolean xorResult) throws ParseException {

        //given
        LongPredicate fun1 = LongPredicate.l((long l) -> f1Result);
        LongPredicate fun2 = LongPredicate.l((long l) -> f2Result);

        //when
        LongPredicate andFunction = fun1.and(fun2);
        LongPredicate orFunction = fun1.or(fun2);
        LongPredicate xorFunction = fun1.xor(fun2);

        //then
        assertThat(andFunction.test((long)100))
                .isEqualTo(andResult);

        assertThat(orFunction.test((long)100))
                .isEqualTo(orResult);

        assertThat(xorFunction.test((long)100))
                .isEqualTo(xorResult);
    }

    @Test
    public void isEqual() throws ParseException  {
        //when
        LongPredicate equals = LongPredicate.isEqual((long)100);

        //then
        assertThat(equals.test((long)100))
                .isTrue();

        assertThat(equals.test((long)0))
                .isFalse();
    }



    // <editor-fold desc="compose (functional)">

    @Test
    public void testfromLong() throws ParseException {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LongPredicate sutO = (long l) -> {
                mainFunctionCalled.set(true);
                assertThat(l).isEqualTo((long)90);
                return true;
        };

        LongUnaryOperator before1 = p0 -> {
            assertThat(p0).isEqualTo((long)80);
            beforeCalls.incrementAndGet();
            return (long)90;
        };

        //when
        LongPredicate function = sutO.fromLong(before1);
        function.test((long)80);

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(beforeCalls.get()).isEqualTo(1);
    }


    @Test
    public void testfrom() throws ParseException {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LongPredicate sutO = (long l) -> {
                mainFunctionCalled.set(true);
                assertThat(l).isEqualTo((long)90);
                return true;
        };

        ToLongFunction<Integer > before1 = p0 -> {
            assertThat(p0).isEqualTo(Integer.valueOf(80));
            beforeCalls.incrementAndGet();
            return (long)90;
        };

        //when
        Predicate<Integer > function = sutO.from(before1);
        function.test((Integer )Integer.valueOf(80));

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(beforeCalls.get()).isEqualTo(1);
    }

    // </editor-fold>


    // <editor-fold desc="then (functional)">

    @Test
    public void testThen0() throws ParseException  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);


        //given (+ some assertions)
        LongPredicate sutO = (long l) -> {
                mainFunctionCalled.set(true);
                assertThat(l).isEqualTo((long)80);
                return true;
        };

        BooleanFunction<Integer > thenFunction = p -> {
                thenFunctionCalled.set(true);
                // 
                assertThat(p).isEqualTo(true);
                // V
                return Integer.valueOf(100);
        };

        //when
        LongFunction<Integer > function = sutO.then(thenFunction);
        Integer  finalValue = function.apply((long)80);

        //then - finals
        assertThat(finalValue).isEqualTo(Integer.valueOf(100));
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    @Test
    public void testThen1ToByte() throws ParseException  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);


        //given (+ some assertions)
        LongPredicate sutO = (long l) -> {
                mainFunctionCalled.set(true);
                assertThat(l).isEqualTo((long)80);
                return true;
        };

        BooleanToByteFunction thenFunction = p -> {
                thenFunctionCalled.set(true);
                // 
                assertThat(p).isEqualTo(true);
                // byte
                return (byte)100;
        };

        //when
        LongToByteFunction function = sutO.thenToByte(thenFunction);
        byte finalValue = function.applyAsByte((long)80);

        //then - finals
        assertThat(finalValue).isEqualTo((byte)100);
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    @Test
    public void testThen2ToShort() throws ParseException  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);


        //given (+ some assertions)
        LongPredicate sutO = (long l) -> {
                mainFunctionCalled.set(true);
                assertThat(l).isEqualTo((long)80);
                return true;
        };

        BooleanToShortFunction thenFunction = p -> {
                thenFunctionCalled.set(true);
                // 
                assertThat(p).isEqualTo(true);
                // short
                return (short)100;
        };

        //when
        LongToShortFunction function = sutO.thenToShort(thenFunction);
        short finalValue = function.applyAsShort((long)80);

        //then - finals
        assertThat(finalValue).isEqualTo((short)100);
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    @Test
    public void testThen3ToInt() throws ParseException  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);


        //given (+ some assertions)
        LongPredicate sutO = (long l) -> {
                mainFunctionCalled.set(true);
                assertThat(l).isEqualTo((long)80);
                return true;
        };

        BooleanToIntFunction thenFunction = p -> {
                thenFunctionCalled.set(true);
                // 
                assertThat(p).isEqualTo(true);
                // int
                return (int)100;
        };

        //when
        LongToIntFunction function = sutO.thenToInt(thenFunction);
        int finalValue = function.applyAsInt((long)80);

        //then - finals
        assertThat(finalValue).isEqualTo((int)100);
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    @Test
    public void testThen4ToLong() throws ParseException  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);


        //given (+ some assertions)
        LongPredicate sutO = (long l) -> {
                mainFunctionCalled.set(true);
                assertThat(l).isEqualTo((long)80);
                return true;
        };

        BooleanToLongFunction thenFunction = p -> {
                thenFunctionCalled.set(true);
                // 
                assertThat(p).isEqualTo(true);
                // long
                return (long)100;
        };

        //when
        LongUnaryOperator function = sutO.thenToLong(thenFunction);
        long finalValue = function.applyAsLong((long)80);

        //then - finals
        assertThat(finalValue).isEqualTo((long)100);
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    @Test
    public void testThen5ToFloat() throws ParseException  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);


        //given (+ some assertions)
        LongPredicate sutO = (long l) -> {
                mainFunctionCalled.set(true);
                assertThat(l).isEqualTo((long)80);
                return true;
        };

        BooleanToFloatFunction thenFunction = p -> {
                thenFunctionCalled.set(true);
                // 
                assertThat(p).isEqualTo(true);
                // float
                return (float)100;
        };

        //when
        LongToFloatFunction function = sutO.thenToFloat(thenFunction);
        float finalValue = function.applyAsFloat((long)80);

        //then - finals
        assertThat(finalValue).isEqualTo((float)100);
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    @Test
    public void testThen6ToDouble() throws ParseException  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);


        //given (+ some assertions)
        LongPredicate sutO = (long l) -> {
                mainFunctionCalled.set(true);
                assertThat(l).isEqualTo((long)80);
                return true;
        };

        BooleanToDoubleFunction thenFunction = p -> {
                thenFunctionCalled.set(true);
                // 
                assertThat(p).isEqualTo(true);
                // double
                return (double)100;
        };

        //when
        LongToDoubleFunction function = sutO.thenToDouble(thenFunction);
        double finalValue = function.applyAsDouble((long)80);

        //then - finals
        assertThat(finalValue).isEqualTo((double)100);
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    @Test
    public void testThen7ToChar() throws ParseException  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);


        //given (+ some assertions)
        LongPredicate sutO = (long l) -> {
                mainFunctionCalled.set(true);
                assertThat(l).isEqualTo((long)80);
                return true;
        };

        BooleanToCharFunction thenFunction = p -> {
                thenFunctionCalled.set(true);
                // 
                assertThat(p).isEqualTo(true);
                // char
                return (char)100;
        };

        //when
        LongToCharFunction function = sutO.thenToChar(thenFunction);
        char finalValue = function.applyAsChar((long)80);

        //then - finals
        assertThat(finalValue).isEqualTo((char)100);
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    @Test
    public void testThen8ToBoolean() throws ParseException  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);


        //given (+ some assertions)
        LongPredicate sutO = (long l) -> {
                mainFunctionCalled.set(true);
                assertThat(l).isEqualTo((long)80);
                return true;
        };

        BooleanUnaryOperator thenFunction = p -> {
                thenFunctionCalled.set(true);
                // 
                assertThat(p).isEqualTo(true);
                // boolean
                return true;
        };

        //when
        LongPredicate function = sutO.thenToBoolean(thenFunction);
        boolean finalValue = function.test((long)80);

        //then - finals
        assertThat(finalValue).isEqualTo(true);
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    // </editor-fold>
    @Test
    public void testStd() {
        assertThat(sut.std()).isInstanceOf(java.util.function.LongPredicate.class);
    }

    @Test
    public void testNonThrowing() {
        assertThat(sut.nonThrowing()).isInstanceOf(LongPredicate.class);
    }

    @Test
    public void testUncheck() {
        assertThat(sut.uncheck()).isInstanceOf(LongPredicateX.class);
    }


    @Test
    public void testHandle() throws ParseException {

        // given
        LongPredicate sutThrowing = LongPredicate.l((long l) -> {
            throw new UnsupportedOperationException();
        });

        // when
        LongPredicate wrapped = sutThrowing.handle(UnsupportedOperationException.class, t -> {
            throw new IllegalArgumentException(EXCEPTION_WAS_WRAPPED, t);
        });

        // then
        try {
            wrapped.test((long)100);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IllegalArgumentException.class)
                    .hasCauseExactlyInstanceOf(UnsupportedOperationException.class)
                    .hasMessage(EXCEPTION_WAS_WRAPPED);
        }
    }

    @Test
    public void testToString() throws ParseException {

        assertThat(sut.toString())
                .isInstanceOf(String.class)
                .startsWith(this.getClass().getName()+"$");

        assertThat(String.format("%s", sut))
                .isInstanceOf(String.class)
                .contains("LongPredicate: boolean test(long l)");
    }

}
