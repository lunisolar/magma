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
public class LObjShortPredicateTest<T,X extends ParseException> {
    private static final String ORIGINAL_MESSAGE = "Original message";
    private static final String EXCEPTION_WAS_WRAPPED = "Exception was wrapped.";
    private static final String NO_EXCEPTION_WERE_THROWN = "No exception were thrown.";

    private boolean testValue = true;



    private LObjShortPredicate<T> sut = new LObjShortPredicate(){
        public  boolean doTest(Object t, short s)  {
            return testValue;
        }
    };

    private LObjShortPredicateX<T,X> opposite = new LObjShortPredicateX(){
        public  boolean doTest(Object t, short s) throws ParseException {
            return testValue;
        }
    };




    private LObjShortPredicate<T> sutAlwaysThrowingUnckeck = LObjShortPredicate.l((T t, short s) -> {
            throw new IndexOutOfBoundsException(ORIGINAL_MESSAGE);
    });


    @Test
    public void testTheResult() throws X {
        assertThat(sut.doTest((T)Integer.valueOf(100),(short)100))
            .isEqualTo(testValue);
    }

    @Test
    public void testNonNullDoTest() throws X {
        assertThat(sut.nonNullDoTest((T)Integer.valueOf(100),(short)100))
            .isEqualTo(testValue);
    }

    @Test
    public void testNestingDoTest_unckeck() throws X {

        // then
        try {
            sutAlwaysThrowingUnckeck.nestingDoTest((T)Integer.valueOf(100),(short)100);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                    .hasNoCause()
                    .hasMessage(ORIGINAL_MESSAGE);
        }
    }

    @Test
    public void testShovingDoTest_unckeck() throws X {

        // then
        try {
            sutAlwaysThrowingUnckeck.shovingDoTest((T)Integer.valueOf(100),(short)100);
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
        assertThat(sut.doApplyAsBoolean((T)Integer.valueOf(100),(short)100))
            .isEqualTo(testValue);

    }


    @Test
    public void testFunctionalInterfaceDescription() throws X {
        assertThat(sut.functionalInterfaceDescription())
            .isEqualTo("LObjShortPredicate: boolean doTest(T t, short s)");
    }

    @Test
    public void testLMethod() throws X {
        assertThat(LObjShortPredicate.l((Object t, short s) -> testValue ))
            .isInstanceOf(LObjShortPredicate.class);
    }

    @Test
    public void testWrapMethod() throws X {
        assertThat(LObjShortPredicate.wrap(opposite))
            .isInstanceOf(LObjShortPredicate.class);
    }

    @Test
    public void testWrapMethodDoNotWrapsRuntimeException() throws X {
        // given
        LObjShortPredicateX<T,X> sutThrowing = LObjShortPredicateX.lX((T t, short s) -> {
            throw new UnsupportedOperationException(ORIGINAL_MESSAGE);
        });

        // when
        LObjShortPredicate<T> wrapped = LObjShortPredicate.wrap(sutThrowing);

        // then
        try {
            wrapped.doTest((T)Integer.valueOf(100),(short)100);
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
        LObjShortPredicateX<T,ParseException> sutThrowing = LObjShortPredicateX.lX((T t, short s) -> {
            throw new ParseException(ORIGINAL_MESSAGE, 0);
        });

        // when
        LObjShortPredicate<T> wrapped = LObjShortPredicate.wrap(sutThrowing);

        // then
        try {
            wrapped.doTest((T)Integer.valueOf(100),(short)100);
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
        LObjShortPredicate<T> sutThrowing = LObjShortPredicate.l((T t, short s) -> {
            throw new UnsupportedOperationException();
        });

        // when
        LObjShortPredicate<T> wrapped = sutThrowing.handle(h -> {
            h.wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED);
        });

        // then
        try {
            wrapped.doTest((T)Integer.valueOf(100),(short)100);
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
        LObjShortPredicate<T> sutThrowing = LObjShortPredicate.l((T t, short s) -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LObjShortPredicate<T> wrapped = sutThrowing.handle(handler -> handler
                .wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED)
                .throwIf(IndexOutOfBoundsException.class));

        // then
        try {
            wrapped.doTest((T)Integer.valueOf(100),(short)100);
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
        LObjShortPredicate<T> sutThrowing = LObjShortPredicate.l((T t, short s) -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LObjShortPredicate<T> wrapped = sutThrowing.handle(handler -> handler
                .wrapWhen(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED)
                .throwIf(IndexOutOfBoundsException.class));

        // then
        try {
            wrapped.doTest((T)Integer.valueOf(100),(short)100);
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
        LObjShortPredicate<T> sutThrowing = LObjShortPredicate.l((T t, short s) -> {
            throw new UnsupportedOperationException(ORIGINAL_MESSAGE);
        });

        // when
        LObjShortPredicate<T> wrapped = sutThrowing.handle(h -> {
        });

        // then
        try {
            wrapped.doTest((T)Integer.valueOf(100),(short)100);
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
        assertThat(sut.negate().doTest((T)Integer.valueOf(100),(short)100))
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
        LObjShortPredicate<T> fun1 = LObjShortPredicate.l((T t, short s) -> f1Result);
        LObjShortPredicate<T> fun2 = LObjShortPredicate.l((T t, short s) -> f2Result);

        //when
        LObjShortPredicate<T> andFunction = fun1.and(fun2);
        LObjShortPredicate<T> orFunction = fun1.or(fun2);
        LObjShortPredicate<T> xorFunction = fun1.xor(fun2);

        //then
        assertThat(andFunction.doTest((T)Integer.valueOf(100),(short)100))
                .isEqualTo(andResult);

        assertThat(orFunction.doTest((T)Integer.valueOf(100),(short)100))
                .isEqualTo(orResult);

        assertThat(xorFunction.doTest((T)Integer.valueOf(100),(short)100))
                .isEqualTo(xorResult);
    }

    @Test
    public void isEqual() throws X  {
        //when
        LObjShortPredicate<T> equals = LObjShortPredicate.isEqual((T)Integer.valueOf(100),(short)100);

        //then
        assertThat(equals.doTest((T)Integer.valueOf(100),(short)100))
                .isTrue();

        assertThat(equals.doTest((T)Integer.valueOf(0),(short)0))
                .isFalse();
    }



    // <editor-fold desc="compose (functional)">

    @Test
    public void testfromShort() throws X {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LObjShortPredicate<Integer > sutO = (Integer t, short s) -> {
                mainFunctionCalled.set(true);
                assertThat(t).isEqualTo((T)Integer.valueOf(90));
                assertThat( s).isEqualTo((short)91);
                return true;
        };

        LFunction<Integer ,Integer > before1 = p0 -> {
            assertThat(p0).isEqualTo((T)Integer.valueOf(80));
            beforeCalls.incrementAndGet();
            return 90;
        };
        LShortUnaryOperator before2 = p1 -> {
            assertThat(p1).isEqualTo((short)81);
            beforeCalls.incrementAndGet();
            return (short)91;
        };

        //when
        LObjShortPredicate<Integer > function = sutO.fromShort(before1,before2);
        function.doTest((Integer )Integer.valueOf(80),(short)81);

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(beforeCalls.get()).isEqualTo(2);
    }


    @Test
    public void testfrom() throws X {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LObjShortPredicate<Integer > sutO = (Integer t, short s) -> {
                mainFunctionCalled.set(true);
                assertThat(t).isEqualTo((T)Integer.valueOf(90));
                assertThat( s).isEqualTo((short)91);
                return true;
        };

        LFunction<Integer ,Integer > before1 = p0 -> {
            assertThat(p0).isEqualTo(Integer.valueOf(80));
            beforeCalls.incrementAndGet();
            return 90;
        };
        LToShortFunction<Integer > before2 = p1 -> {
            assertThat(p1).isEqualTo(Integer.valueOf(81));
            beforeCalls.incrementAndGet();
            return (short)91;
        };

        //when
        LBiPredicate<Integer ,Integer > function = sutO.from(before1,before2);
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
        LObjShortPredicate<Integer > sutO = (Integer t, short s) -> {
                mainFunctionCalled.set(true);
                assertThat(t).isEqualTo((T)Integer.valueOf(80));
                assertThat( s).isEqualTo((short)81);
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
        LObjShortFunction<Integer ,Integer > function = sutO.then(thenFunction);
        Integer  finalValue = function.doApply((Integer )Integer.valueOf(80),(short)81);

        //then - finals
        assertThat(finalValue).isEqualTo(Integer.valueOf(100));
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    // </editor-fold>

    @Test
    public void testNesting() {
        assertThat(sut.nest())
            .isSameAs(sut)
            .isInstanceOf(LObjShortPredicate.class);
    }

    @Test
    public void testShoving() {
        assertThat(sut.shove())
            .isSameAs(sut)
            .isInstanceOf(LObjShortPredicate.class);
    }

    @Test
    public void testNestingX() {
        assertThat(sut.nestX())
            .isSameAs(sut)
            .isInstanceOf(LObjShortPredicateX.class);
    }

    @Test
    public void testShovingX() {
        assertThat(sut.shoveX())
            .isSameAs(sut)
            .isInstanceOf(LObjShortPredicateX.class);
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void testShove() {

        // given
        LObjShortPredicate<T> sutThrowing = LObjShortPredicate.l((T t, short s) -> {
            throw new UnsupportedOperationException();
        });

        // when
        sutThrowing.shove().doTest((T)Integer.valueOf(100),(short)100);
    }

    @Test
    public void testHandle() throws X {

        // given
        LObjShortPredicate<T> sutThrowing = LObjShortPredicate.l((T t, short s) -> {
            throw new UnsupportedOperationException();
        });

        // when
        LObjShortPredicate<T> wrapped = sutThrowing.handle(h -> {
            h.wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED);
        });

        // then
        try {
            wrapped.doTest((T)Integer.valueOf(100),(short)100);
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
                .contains("LObjShortPredicate: boolean doTest(T t, short s)");
    }


    @Test
    public void isThrowing() {
        assertThat(sut.isThrowing())
            .isFalse();
    }


}
