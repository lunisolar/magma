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

package eu.lunisolar.magma.func.function.from;

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
public class LFloatBiFunctionTest<R,X extends ParseException> {
    private static final String ORIGINAL_MESSAGE = "Original message";
    private static final String EXCEPTION_WAS_WRAPPED = "Exception was wrapped.";
    private static final String NO_EXCEPTION_WERE_THROWN = "No exception were thrown.";

    private Object  testValue = (R)Integer.valueOf(100);



    private LFloatBiFunction<R> sut = new LFloatBiFunction(){
        public @Nullable Object  doApply(float f1,float f2)  {
            return testValue;
        }
    };

    private LFloatBiFunctionX<R,X> opposite = new LFloatBiFunctionX(){
        public @Nullable Object  doApply(float f1,float f2) throws ParseException {
            return testValue;
        }
    };

    private LFloatBiFunction<R> sutNull = new LFloatBiFunction(){
        public @Nullable Object  doApply(float f1,float f2)  {
            return null;
        }
    };




    private LFloatBiFunction<R> sutAlwaysThrowingUnckeck = LFloatBiFunction.l((float f1,float f2) -> {
            throw new IndexOutOfBoundsException(ORIGINAL_MESSAGE);
    });


    @Test
    public void testTheResult() throws ParseException {
        assertThat(sut.doApply((float)100,(float)100))
            .isSameAs(testValue);
    }

    @Test
    public void testNonNullDoApply() throws ParseException {
        assertThat(sut.nonNullDoApply((float)100,(float)100))
            .isSameAs(testValue);
    }

    @Test
    public void testNestingDoApply_unckeck() throws ParseException {

        // then
        try {
            sutAlwaysThrowingUnckeck.nestingDoApply((float)100,(float)100);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                    .hasNoCause()
                    .hasMessage(ORIGINAL_MESSAGE);
        }
    }

    @Test
    public void testShovingDoApply_unckeck() throws ParseException {

        // then
        try {
            sutAlwaysThrowingUnckeck.shovingDoApply((float)100,(float)100);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                    .hasNoCause()
                    .hasMessage(ORIGINAL_MESSAGE);
        }
    }


    @Test(expectedExceptions=NullPointerException.class, expectedExceptionsMessageRegExp="\\QEvaluated value by nonNullDoApply() method cannot be null (LFloatBiFunction: R doApply(float f1,float f2)).\\E")
    public void testNonNullCapturesNull() throws ParseException {
        sutNull.nonNullDoApply((float)100,(float)100);
    }


    @Test
    public void testFunctionalInterfaceDescription() throws ParseException {
        assertThat(sut.functionalInterfaceDescription())
            .isEqualTo("LFloatBiFunction: R doApply(float f1,float f2)");
    }

    @Test
    public void testLMethod() throws ParseException {
        assertThat(LFloatBiFunction.l((float f1,float f2) -> testValue ))
            .isInstanceOf(LFloatBiFunction.class);
    }

    @Test
    public void testWrapMethod() throws ParseException {
        assertThat(LFloatBiFunction.wrap(opposite))
            .isInstanceOf(LFloatBiFunction.class);
    }

    @Test
    public void testWrapMethodDoNotWrapsRuntimeException() throws ParseException {
        // given
        LFloatBiFunctionX<R,X> sutThrowing = LFloatBiFunctionX.lX((float f1,float f2) -> {
            throw new UnsupportedOperationException(ORIGINAL_MESSAGE);
        });

        // when
        LFloatBiFunction<R> wrapped = LFloatBiFunction.wrap(sutThrowing);

        // then
        try {
            wrapped.doApply((float)100,(float)100);
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
        LFloatBiFunctionX<R,ParseException> sutThrowing = LFloatBiFunctionX.lX((float f1,float f2) -> {
            throw new ParseException(ORIGINAL_MESSAGE, 0);
        });

        // when
        LFloatBiFunction<R> wrapped = LFloatBiFunction.wrap(sutThrowing);

        // then
        try {
            wrapped.doApply((float)100,(float)100);
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
        LFloatBiFunction<R> sutThrowing = LFloatBiFunction.l((float f1,float f2) -> {
            throw new UnsupportedOperationException();
        });

        // when
        LFloatBiFunction<R> wrapped = LFloatBiFunction.wrapException(sutThrowing, UnsupportedOperationException.class, null, t -> {
            throw new IllegalArgumentException(EXCEPTION_WAS_WRAPPED, t);
        });

        // then
        try {
            wrapped.doApply((float)100,(float)100);
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
        LFloatBiFunction<R> sutThrowing = LFloatBiFunction.l((float f1,float f2) -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LFloatBiFunction<R> wrapped = LFloatBiFunction.wrapException(sutThrowing, UnsupportedOperationException.class, null, t -> {
            throw new IllegalArgumentException(EXCEPTION_WAS_WRAPPED, t);
        });

        // then
        try {
            wrapped.doApply((float)100,(float)100);
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
        LFloatBiFunction<R> sutThrowing = LFloatBiFunction.l((float f1,float f2) -> {
            throw new UnsupportedOperationException();
        });

        // when
        LFloatBiFunction<R> wrapped = LFloatBiFunction.wrapException(sutThrowing, UnsupportedOperationException.class, null, t -> {
            return null;
        });

        // then
        try {
            wrapped.doApply((float)100,(float)100);
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
    public void testfromFloat() throws ParseException {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LFloatBiFunction<Integer > sutO = (float f1,float f2) -> {
                mainFunctionCalled.set(true);
                assertThat(f1).isEqualTo((float)90);
                assertThat(f2).isEqualTo((float)91);
                return 9;
        };

        LFloatUnaryOperator before1 = p0 -> {
            assertThat(p0).isEqualTo((float)80);
            beforeCalls.incrementAndGet();
            return (float)90;
        };
        LFloatUnaryOperator before2 = p1 -> {
            assertThat(p1).isEqualTo((float)81);
            beforeCalls.incrementAndGet();
            return (float)91;
        };

        //when
        LFloatBiFunction<Integer > function = sutO.fromFloat(before1,before2);
        function.doApply((float)80,(float)81);

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(beforeCalls.get()).isEqualTo(2);
    }


    @Test
    public void testfrom() throws ParseException {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LFloatBiFunction<Integer > sutO = (float f1,float f2) -> {
                mainFunctionCalled.set(true);
                assertThat(f1).isEqualTo((float)90);
                assertThat(f2).isEqualTo((float)91);
                return 9;
        };

        LToFloatFunction<Integer > before1 = p0 -> {
            assertThat(p0).isEqualTo(Integer.valueOf(80));
            beforeCalls.incrementAndGet();
            return (float)90;
        };
        LToFloatFunction<Integer > before2 = p1 -> {
            assertThat(p1).isEqualTo(Integer.valueOf(81));
            beforeCalls.incrementAndGet();
            return (float)91;
        };

        //when
        LBiFunction<Integer ,Integer ,Integer > function = sutO.from(before1,before2);
        function.doApply((Integer )Integer.valueOf(80),(Integer )Integer.valueOf(81));

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
        LFloatBiFunction<Integer > sutO = (float f1,float f2) -> {
                mainFunctionCalled.set(true);
                assertThat(f1).isEqualTo((float)80);
                assertThat(f2).isEqualTo((float)81);
                return Integer.valueOf(90);
        };

        LFunction<Integer ,Integer > thenFunction = p -> {
                thenFunctionCalled.set(true);
                // R
                assertThat(p).isEqualTo(Integer.valueOf(90));
                // V
                return Integer.valueOf(100);
        };

        //when
        LFloatBiFunction<Integer > function = sutO.then(thenFunction);
        Integer  finalValue = function.doApply((float)80,(float)81);

        //then - finals
        assertThat(finalValue).isEqualTo(Integer.valueOf(100));
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    @Test
    public void testThen1() throws ParseException  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);


        //given (+ some assertions)
        LFloatBiFunction<Integer > sutO = (float f1,float f2) -> {
                mainFunctionCalled.set(true);
                assertThat(f1).isEqualTo((float)80);
                assertThat(f2).isEqualTo((float)81);
                return Integer.valueOf(90);
        };

        LConsumer<Integer > thenFunction = p -> {
                thenFunctionCalled.set(true);
                // R
                assertThat(p).isEqualTo(Integer.valueOf(90));
        };

        //when
        LFloatBiConsumer function = sutO.then(thenFunction);
        function.doAccept((float)80,(float)81);

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    // </editor-fold>
//
    @Test
    public void testNesting() {
        assertThat(sut.nest())
            .isSameAs(sut)
            .isInstanceOf(LFloatBiFunction.class);
    }

    @Test
    public void testShoving() {
        assertThat(sut.shove())
            .isSameAs(sut)
            .isInstanceOf(LFloatBiFunction.class);
    }

    @Test
    public void testNestingX() {
        assertThat(sut.nestX())
            .isSameAs(sut)
            .isInstanceOf(LFloatBiFunctionX.class);
    }

    @Test
    public void testShovingX() {
        assertThat(sut.shoveX())
            .isSameAs(sut)
            .isInstanceOf(LFloatBiFunctionX.class);
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void testShove() {

        // given
        LFloatBiFunction<R> sutThrowing = LFloatBiFunction.l((float f1,float f2) -> {
            throw new UnsupportedOperationException();
        });

        // when
        sutThrowing.shove().doApply((float)100,(float)100);
    }

    @Test
    public void testHandle() throws ParseException {

        // given
        LFloatBiFunction<R> sutThrowing = LFloatBiFunction.l((float f1,float f2) -> {
            throw new UnsupportedOperationException();
        });

        // when
        LFloatBiFunction<R> wrapped = sutThrowing.handle(UnsupportedOperationException.class, t -> {
            throw new IllegalArgumentException(EXCEPTION_WAS_WRAPPED, t);
        });

        // then
        try {
            wrapped.doApply((float)100,(float)100);
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
                .contains("LFloatBiFunction: R doApply(float f1,float f2)");
    }


    @Test
    public void isThrowing() {
        assertThat(sut.isThrowing())
            .isFalse();
    }


}
