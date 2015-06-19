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

package eu.lunisolar.magma.func.predicate;

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
public class LObjBytePredicateXTest<T,X extends ParseException> {
    private static final String ORIGINAL_MESSAGE = "Original message";
    private static final String EXCEPTION_WAS_WRAPPED = "Exception was wrapped.";
    private static final String NO_EXCEPTION_WERE_THROWN = "No exception were thrown.";

    private boolean testValue = true;



    private LObjBytePredicateX<T,X> sut = new LObjBytePredicateX(){
        public  boolean doTest(Object t, byte b) throws ParseException {
            return testValue;
        }
    };

    private LObjBytePredicate<T> opposite = new LObjBytePredicate(){
        public  boolean doTest(Object t, byte b)  {
            return testValue;
        }
    };



    private LObjBytePredicateX<T,ParseException> sutAlwaysThrowing = LObjBytePredicateX.lX((T t, byte b) -> {
            throw new ParseException(ORIGINAL_MESSAGE, 0);
    });

    private LObjBytePredicateX<T,RuntimeException> sutAlwaysThrowingUnckeck = LObjBytePredicateX.lX((T t, byte b) -> {
            throw new IndexOutOfBoundsException(ORIGINAL_MESSAGE);
    });


    @Test
    public void testTheResult() throws X {
        assertThat(sut.doTest((T)Integer.valueOf(100),(byte)100))
            .isEqualTo(testValue);
    }

    @Test
    public void testNonNullDoTest() throws X {
        assertThat(sut.nonNullDoTest((T)Integer.valueOf(100),(byte)100))
            .isEqualTo(testValue);
    }

    @Test
    public void testNestingDoTest_checked() throws X {

        // then
        try {
            sutAlwaysThrowing.nestingDoTest((T)Integer.valueOf(100),(byte)100);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(NestedException.class)
                    .hasCauseExactlyInstanceOf(ParseException.class)
                    .hasMessage(ORIGINAL_MESSAGE);
        }
    }

    @Test
    public void testNestingDoTest_unckeck() throws X {

        // then
        try {
            sutAlwaysThrowingUnckeck.nestingDoTest((T)Integer.valueOf(100),(byte)100);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                    .hasNoCause()
                    .hasMessage(ORIGINAL_MESSAGE);
        }
    }

    @Test
    public void testShovingDoTest_checked() throws X {

        // then
        try {
            sutAlwaysThrowing.shovingDoTest((T)Integer.valueOf(100),(byte)100);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(ParseException.class)
                    .hasNoCause()
                    .hasMessage(ORIGINAL_MESSAGE);
        }
    }

    @Test
    public void testShovingDoTest_unckeck() throws X {

        // then
        try {
            sutAlwaysThrowingUnckeck.shovingDoTest((T)Integer.valueOf(100),(byte)100);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                    .hasNoCause()
                    .hasMessage(ORIGINAL_MESSAGE);
        }
    }


    @Test
    public void testApplyAsBooleanShouldNotModifyValue() throws X {
        assertThat(sut.doApplyAsBoolean((T)Integer.valueOf(100),(byte)100))
            .isEqualTo(testValue);

    }


    @Test
    public void testFunctionalInterfaceDescription() throws X {
        assertThat(sut.functionalInterfaceDescription())
            .isEqualTo("LObjBytePredicateX: boolean doTest(T t, byte b) throws X");
    }

    @Test
    public void testLXMethod() throws X {
        assertThat(LObjBytePredicateX.lX((Object t, byte b) -> testValue ))
            .isInstanceOf(LObjBytePredicateX.class);
    }

    @Test
    public void testWrapXMethod() throws X {
        assertThat(LObjBytePredicateX.wrapX(opposite))
            .isInstanceOf(LObjBytePredicateX.class);
    }


    @Test
    public void testWrapExceptionMethodWrapsTheException() throws X {

        // given
        LObjBytePredicateX<T,X> sutThrowing = LObjBytePredicateX.lX((T t, byte b) -> {
            throw new UnsupportedOperationException();
        });

        // when
        LObjBytePredicateX<T,X> wrapped = sutThrowing.handleX(h -> {
            h.wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED);
        });

        // then
        try {
            wrapped.doTest((T)Integer.valueOf(100),(byte)100);
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
        LObjBytePredicateX<T,X> sutThrowing = LObjBytePredicateX.lX((T t, byte b) -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LObjBytePredicateX<T,X> wrapped = sutThrowing.handleX(handler -> handler
                .wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED)
                .throwIf(IndexOutOfBoundsException.class));

        // then
        try {
            wrapped.doTest((T)Integer.valueOf(100),(byte)100);
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
        LObjBytePredicateX<T,X> sutThrowing = LObjBytePredicateX.lX((T t, byte b) -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LObjBytePredicateX<T,X> wrapped = sutThrowing.handleX(handler -> handler
                .wrapWhen(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED)
                .throwIf(IndexOutOfBoundsException.class));

        // then
        try {
            wrapped.doTest((T)Integer.valueOf(100),(byte)100);
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
        LObjBytePredicateX<T,X> sutThrowing = LObjBytePredicateX.lX((T t, byte b) -> {
            throw (X) new ParseException(ORIGINAL_MESSAGE, 0);
        });

        // when
        LObjBytePredicateX<T,X> wrapped = sutThrowing.handleX(h -> {
        });

        // then
        try {
            wrapped.doTest((T)Integer.valueOf(100),(byte)100);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(NestedException.class)
                    .hasCauseExactlyInstanceOf(ParseException.class)
                    .hasMessage(ORIGINAL_MESSAGE);
        }
    }

    @Test
    public void testNegate() throws X {
        assertThat(sut.negate().doTest((T)Integer.valueOf(100),(byte)100))
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
        LObjBytePredicateX<T,X> fun1 = LObjBytePredicateX.lX((T t, byte b) -> f1Result);
        LObjBytePredicateX<T,X> fun2 = LObjBytePredicateX.lX((T t, byte b) -> f2Result);

        //when
        LObjBytePredicateX<T,X> andFunction = fun1.and(fun2);
        LObjBytePredicateX<T,X> orFunction = fun1.or(fun2);
        LObjBytePredicateX<T,X> xorFunction = fun1.xor(fun2);

        //then
        assertThat(andFunction.doTest((T)Integer.valueOf(100),(byte)100))
                .isEqualTo(andResult);

        assertThat(orFunction.doTest((T)Integer.valueOf(100),(byte)100))
                .isEqualTo(orResult);

        assertThat(xorFunction.doTest((T)Integer.valueOf(100),(byte)100))
                .isEqualTo(xorResult);
    }

    @Test
    public void isEqual() throws X  {
        //when
        LObjBytePredicateX<T,X> equals = LObjBytePredicateX.isEqual((T)Integer.valueOf(100),(byte)100);

        //then
        assertThat(equals.doTest((T)Integer.valueOf(100),(byte)100))
                .isTrue();

        assertThat(equals.doTest((T)Integer.valueOf(0),(byte)0))
                .isFalse();
    }



    // <editor-fold desc="compose (functional)">

    @Test
    public void testfromByte() throws X {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LObjBytePredicateX<Integer ,X> sutO = (Integer t, byte b) -> {
                mainFunctionCalled.set(true);
                assertThat(t).isEqualTo((T)Integer.valueOf(90));
                assertThat( b).isEqualTo((byte)91);
                return true;
        };

        LFunctionX<Integer ,Integer ,X> before1 = p0 -> {
            assertThat(p0).isEqualTo((T)Integer.valueOf(80));
            beforeCalls.incrementAndGet();
            return 90;
        };
        LByteUnaryOperatorX<X> before2 = p1 -> {
            assertThat(p1).isEqualTo((byte)81);
            beforeCalls.incrementAndGet();
            return (byte)91;
        };

        //when
        LObjBytePredicateX<Integer ,X> function = sutO.fromByte(before1,before2);
        function.doTest((Integer )Integer.valueOf(80),(byte)81);

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(beforeCalls.get()).isEqualTo(2);
    }


    @Test
    public void testfrom() throws X {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LObjBytePredicateX<Integer ,X> sutO = (Integer t, byte b) -> {
                mainFunctionCalled.set(true);
                assertThat(t).isEqualTo((T)Integer.valueOf(90));
                assertThat( b).isEqualTo((byte)91);
                return true;
        };

        LFunctionX<Integer ,Integer ,X> before1 = p0 -> {
            assertThat(p0).isEqualTo(Integer.valueOf(80));
            beforeCalls.incrementAndGet();
            return 90;
        };
        LToByteFunctionX<Integer ,X> before2 = p1 -> {
            assertThat(p1).isEqualTo(Integer.valueOf(81));
            beforeCalls.incrementAndGet();
            return (byte)91;
        };

        //when
        LBiPredicateX<Integer ,Integer ,X> function = sutO.from(before1,before2);
        function.doTest((Integer )Integer.valueOf(80),(Integer )Integer.valueOf(81));

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
        LObjBytePredicateX<Integer ,X> sutO = (Integer t, byte b) -> {
                mainFunctionCalled.set(true);
                assertThat(t).isEqualTo((T)Integer.valueOf(80));
                assertThat( b).isEqualTo((byte)81);
                return true;
        };

        LBooleanFunctionX<Integer ,X> thenFunction = p -> {
                thenFunctionCalled.set(true);
                // 
                assertThat(p).isEqualTo(true);
                // V
                return Integer.valueOf(100);
        };

        //when
        LObjByteFunctionX<Integer ,Integer ,X> function = sutO.then(thenFunction);
        Integer  finalValue = function.doApply((Integer )Integer.valueOf(80),(byte)81);

        //then - finals
        assertThat(finalValue).isEqualTo(Integer.valueOf(100));
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    // </editor-fold>

    @Test
    public void testNesting() {
        assertThat(sut.nest())
            .isInstanceOf(LObjBytePredicate.class);
    }

    @Test
    public void testShoving() {
        assertThat(sut.shove())
            .isInstanceOf(LObjBytePredicate.class);
    }

    @Test
    public void testNestingX() {
        assertThat(sut.nestX())
            .isInstanceOf(LObjBytePredicateX.class);
    }

    @Test
    public void testShovingX() {
        assertThat(sut.shoveX())
            .isInstanceOf(LObjBytePredicateX.class);
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void testShove() {

        // given
        LObjBytePredicateX<T,X> sutThrowing = LObjBytePredicateX.lX((T t, byte b) -> {
            throw new UnsupportedOperationException();
        });

        // when
        sutThrowing.shove().doTest((T)Integer.valueOf(100),(byte)100);
    }

    @Test
    public void testHandle() throws X {

        // given
        LObjBytePredicateX<T,X> sutThrowing = LObjBytePredicateX.lX((T t, byte b) -> {
            throw new UnsupportedOperationException();
        });

        // when
        LObjBytePredicateX<T,X> wrapped = sutThrowing.handleX(h -> {
            h.wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED);
        });

        // then
        try {
            wrapped.doTest((T)Integer.valueOf(100),(byte)100);
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
                .contains("LObjBytePredicateX: boolean doTest(T t, byte b) throws X");
    }


    @Test
    public void isThrowing() {
        assertThat(sut.isThrowing())
            .isTrue();
    }


}
