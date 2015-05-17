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

package eu.lunisolar.magma.func.function.to;

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
import java.util.concurrent.atomic.AtomicInteger; //NOSONAR
import static org.assertj.core.api.Assertions.*; //NOSONAR

/** The test obviously concentrate on the interface methods the function it self is very simple.  */
public class LToDoubleBiFunctionTest<T1,T2,X extends ParseException> {
    private static final String ORIGINAL_MESSAGE = "Original message";
    private static final String EXCEPTION_WAS_WRAPPED = "Exception was wrapped.";
    private static final String NO_EXCEPTION_WERE_THROWN = "No exception were thrown.";

    private double testValue = (double)100;



    private LToDoubleBiFunction<T1,T2> sut = new LToDoubleBiFunction(){
        public  double doApplyAsDouble(Object t1,Object t2)  {
            return testValue;
        }
    };

    private LToDoubleBiFunctionX<T1,T2,X> opposite = new LToDoubleBiFunctionX(){
        public  double doApplyAsDouble(Object t1,Object t2) throws ParseException {
            return testValue;
        }
    };


    private java.util.function.ToDoubleBiFunction jre = (Object t1,Object t2) -> testValue;



    private LToDoubleBiFunction<T1,T2> sutAlwaysThrowingUnckeck = LToDoubleBiFunction.l((T1 t1,T2 t2) -> {
            throw new IndexOutOfBoundsException(ORIGINAL_MESSAGE);
    });


    @Test
    public void testTheResult() throws ParseException {
        assertThat(sut.doApplyAsDouble((T1)Integer.valueOf(100),(T2)Integer.valueOf(100)))
            .isEqualTo(testValue);
    }

    @Test
    public void testNonNullDoApplyAsDouble() throws ParseException {
        assertThat(sut.nonNullDoApplyAsDouble((T1)Integer.valueOf(100),(T2)Integer.valueOf(100)))
            .isEqualTo(testValue);
    }

    @Test
    public void testNestingDoApplyAsDouble_unckeck() throws ParseException {

        // then
        try {
            sutAlwaysThrowingUnckeck.nestingDoApplyAsDouble((T1)Integer.valueOf(100),(T2)Integer.valueOf(100));
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                    .hasNoCause()
                    .hasMessage(ORIGINAL_MESSAGE);
        }
    }

    @Test
    public void testShovingDoApplyAsDouble_unckeck() throws ParseException {

        // then
        try {
            sutAlwaysThrowingUnckeck.shovingDoApplyAsDouble((T1)Integer.valueOf(100),(T2)Integer.valueOf(100));
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                    .hasNoCause()
                    .hasMessage(ORIGINAL_MESSAGE);
        }
    }



    @Test
    public void testFunctionalInterfaceDescription() throws ParseException {
        assertThat(sut.functionalInterfaceDescription())
            .isEqualTo("LToDoubleBiFunction: double doApplyAsDouble(T1 t1,T2 t2)");
    }

    @Test
    public void testLMethod() throws ParseException {
        assertThat(LToDoubleBiFunction.l((Object t1,Object t2) -> testValue ))
            .isInstanceOf(LToDoubleBiFunction.class);
    }

    @Test
    public void testWrapMethod() throws ParseException {
        assertThat(LToDoubleBiFunction.wrap(opposite))
            .isInstanceOf(LToDoubleBiFunction.class);
    }

    @Test
    public void testWrapStdMethod() throws ParseException {
        assertThat(LToDoubleBiFunction.wrap(jre))
            .isInstanceOf(LToDoubleBiFunction.class);
    }

    @Test
    public void testWrapMethodDoNotWrapsRuntimeException() throws ParseException {
        // given
        LToDoubleBiFunctionX<T1,T2,X> sutThrowing = LToDoubleBiFunctionX.lX((T1 t1,T2 t2) -> {
            throw new UnsupportedOperationException(ORIGINAL_MESSAGE);
        });

        // when
        LToDoubleBiFunction<T1,T2> wrapped = LToDoubleBiFunction.wrap(sutThrowing);

        // then
        try {
            wrapped.doApplyAsDouble((T1)Integer.valueOf(100),(T2)Integer.valueOf(100));
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
        LToDoubleBiFunctionX<T1,T2,ParseException> sutThrowing = LToDoubleBiFunctionX.lX((T1 t1,T2 t2) -> {
            throw new ParseException(ORIGINAL_MESSAGE, 0);
        });

        // when
        LToDoubleBiFunction<T1,T2> wrapped = LToDoubleBiFunction.wrap(sutThrowing);

        // then
        try {
            wrapped.doApplyAsDouble((T1)Integer.valueOf(100),(T2)Integer.valueOf(100));
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
        LToDoubleBiFunction<T1,T2> sutThrowing = LToDoubleBiFunction.l((T1 t1,T2 t2) -> {
            throw new UnsupportedOperationException();
        });

        // when
        LToDoubleBiFunction<T1,T2> wrapped = LToDoubleBiFunction.wrapException(sutThrowing, UnsupportedOperationException.class, null, t -> {
            throw new IllegalArgumentException(EXCEPTION_WAS_WRAPPED, t);
        });

        // then
        try {
            wrapped.doApplyAsDouble((T1)Integer.valueOf(100),(T2)Integer.valueOf(100));
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
        LToDoubleBiFunction<T1,T2> sutThrowing = LToDoubleBiFunction.l((T1 t1,T2 t2) -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LToDoubleBiFunction<T1,T2> wrapped = LToDoubleBiFunction.wrapException(sutThrowing, UnsupportedOperationException.class, null, t -> {
            throw new IllegalArgumentException(EXCEPTION_WAS_WRAPPED, t);
        });

        // then
        try {
            wrapped.doApplyAsDouble((T1)Integer.valueOf(100),(T2)Integer.valueOf(100));
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
        LToDoubleBiFunction<T1,T2> sutThrowing = LToDoubleBiFunction.l((T1 t1,T2 t2) -> {
            throw new UnsupportedOperationException();
        });

        // when
        LToDoubleBiFunction<T1,T2> wrapped = LToDoubleBiFunction.wrapException(sutThrowing, UnsupportedOperationException.class, null, t -> {
            return null;
        });

        // then
        try {
            wrapped.doApplyAsDouble((T1)Integer.valueOf(100),(T2)Integer.valueOf(100));
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(ExceptionNotHandled.class)
                    .hasCauseExactlyInstanceOf(UnsupportedOperationException.class)
                    .hasMessage("Handler has not processed the exception.");
        }
    }



    // <editor-fold desc="compose (functional)">

    @Test
    public void testfrom() throws ParseException {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LToDoubleBiFunction<Integer ,Integer > sutO = (Integer t1,Integer t2) -> {
                mainFunctionCalled.set(true);
                assertThat(t1).isEqualTo((T1)Integer.valueOf(90));
                assertThat(t2).isEqualTo((T2)Integer.valueOf(91));
                return (double)100;
        };

        LFunction<Integer ,Integer > before1 = p0 -> {
            assertThat(p0).isEqualTo((T1)Integer.valueOf(80));
            beforeCalls.incrementAndGet();
            return 90;
        };
        LFunction<Integer ,Integer > before2 = p1 -> {
            assertThat(p1).isEqualTo((T2)Integer.valueOf(81));
            beforeCalls.incrementAndGet();
            return 91;
        };

        //when
        LToDoubleBiFunction<Integer ,Integer > function = sutO.from(before1,before2);
        function.doApplyAsDouble((Integer )Integer.valueOf(80),(Integer )Integer.valueOf(81));

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(beforeCalls.get()).isEqualTo(2);
    }

    // </editor-fold>


    // <editor-fold desc="then (functional)">

    @Test
    public void testThen0() throws ParseException  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);


        //given (+ some assertions)
        LToDoubleBiFunction<Integer ,Integer > sutO = (Integer t1,Integer t2) -> {
                mainFunctionCalled.set(true);
                assertThat(t1).isEqualTo((T1)Integer.valueOf(80));
                assertThat(t2).isEqualTo((T2)Integer.valueOf(81));
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
        LBiFunction<Integer ,Integer ,Integer > function = sutO.then(thenFunction);
        Integer  finalValue = function.doApply((Integer )Integer.valueOf(80),(Integer )Integer.valueOf(81));

        //then - finals
        assertThat(finalValue).isEqualTo(Integer.valueOf(100));
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    // </editor-fold>
//
//    @Test
//    public void testStd() {
//        assertThat(sut.std()).isInstanceOf(java.util.function.ToDoubleBiFunction.class);
//    }
//
//
    @Test
    public void testNesting() {
        assertThat(sut.nest())
            .isSameAs(sut)
            .isInstanceOf(LToDoubleBiFunction.class);
    }

    @Test
    public void testShoving() {
        assertThat(sut.shove())
            .isSameAs(sut)
            .isInstanceOf(LToDoubleBiFunction.class);
    }

    @Test
    public void testNestingX() {
        assertThat(sut.nestX())
            .isSameAs(sut)
            .isInstanceOf(LToDoubleBiFunctionX.class);
    }

    @Test
    public void testShovingX() {
        assertThat(sut.shoveX())
            .isSameAs(sut)
            .isInstanceOf(LToDoubleBiFunctionX.class);
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void testShove() {

        // given
        LToDoubleBiFunction<T1,T2> sutThrowing = LToDoubleBiFunction.l((T1 t1,T2 t2) -> {
            throw new UnsupportedOperationException();
        });

        // when
        sutThrowing.shove().doApplyAsDouble((T1)Integer.valueOf(100),(T2)Integer.valueOf(100));
    }

    @Test
    public void testHandle() throws ParseException {

        // given
        LToDoubleBiFunction<T1,T2> sutThrowing = LToDoubleBiFunction.l((T1 t1,T2 t2) -> {
            throw new UnsupportedOperationException();
        });

        // when
        LToDoubleBiFunction<T1,T2> wrapped = sutThrowing.handle(UnsupportedOperationException.class, t -> {
            throw new IllegalArgumentException(EXCEPTION_WAS_WRAPPED, t);
        });

        // then
        try {
            wrapped.doApplyAsDouble((T1)Integer.valueOf(100),(T2)Integer.valueOf(100));
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
                .contains("LToDoubleBiFunction: double doApplyAsDouble(T1 t1,T2 t2)");
    }


    @Test
    public void isThrowing() {
        assertThat(sut.isThrowing())
            .isFalse();
    }


}
