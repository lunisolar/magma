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
public class LCharBinaryOperatorTest<X extends ParseException> {
    private static final String ORIGINAL_MESSAGE = "Original message";
    private static final String EXCEPTION_WAS_WRAPPED = "Exception was wrapped.";
    private static final String NO_EXCEPTION_WERE_THROWN = "No exception were thrown.";

    private char testValue = (char)100;



    private LCharBinaryOperator sut = new LCharBinaryOperator(){
        public  char doApplyAsChar(char c1,char c2)  {
            return testValue;
        }
    };

    private LCharBinaryOperatorX<X> opposite = new LCharBinaryOperatorX(){
        public  char doApplyAsChar(char c1,char c2) throws ParseException {
            return testValue;
        }
    };




    private LCharBinaryOperator sutAlwaysThrowingUnckeck = LCharBinaryOperator.l((char c1,char c2) -> {
            throw new IndexOutOfBoundsException(ORIGINAL_MESSAGE);
    });


    @Test
    public void testTheResult() throws ParseException {
        assertThat(sut.doApplyAsChar((char)100,(char)100))
            .isEqualTo(testValue);
    }

    @Test
    public void testNonNullDoApplyAsChar() throws ParseException {
        assertThat(sut.nonNullDoApplyAsChar((char)100,(char)100))
            .isEqualTo(testValue);
    }

    @Test
    public void testNestingDoApplyAsChar_unckeck() throws ParseException {

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
    public void testShovingDoApplyAsChar_unckeck() throws ParseException {

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
    public void testFunctionalInterfaceDescription() throws ParseException {
        assertThat(sut.functionalInterfaceDescription())
            .isEqualTo("LCharBinaryOperator: char doApplyAsChar(char c1,char c2)");
    }

    @Test
    public void testLMethod() throws ParseException {
        assertThat(LCharBinaryOperator.l((char c1,char c2) -> testValue ))
            .isInstanceOf(LCharBinaryOperator.class);
    }

    @Test
    public void testWrapMethod() throws ParseException {
        assertThat(LCharBinaryOperator.wrap(opposite))
            .isInstanceOf(LCharBinaryOperator.class);
    }

    @Test
    public void testWrapMethodDoNotWrapsRuntimeException() throws ParseException {
        // given
        LCharBinaryOperatorX<X> sutThrowing = LCharBinaryOperatorX.lX((char c1,char c2) -> {
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
    public void testWrapMethodWrapsCheckedException() throws ParseException {
        // given
        LCharBinaryOperatorX<ParseException> sutThrowing = LCharBinaryOperatorX.lX((char c1,char c2) -> {
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
    public void testWrapExceptionMethodWrapsTheException() throws ParseException {

        // given
        LCharBinaryOperator sutThrowing = LCharBinaryOperator.l((char c1,char c2) -> {
            throw new UnsupportedOperationException();
        });

        // when
        LCharBinaryOperator wrapped = LCharBinaryOperator.wrapException(sutThrowing, UnsupportedOperationException.class, null, t -> {
            throw new IllegalArgumentException(EXCEPTION_WAS_WRAPPED, t);
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
    public void testWrapExceptionMethodDoNotWrapsOtherException() throws ParseException {

        // given
        LCharBinaryOperator sutThrowing = LCharBinaryOperator.l((char c1,char c2) -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LCharBinaryOperator wrapped = LCharBinaryOperator.wrapException(sutThrowing, UnsupportedOperationException.class, null, t -> {
            throw new IllegalArgumentException(EXCEPTION_WAS_WRAPPED, t);
        });

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
    public void testWrapExceptionMisshandlingExceptionIsDetected() throws ParseException {

        // given
        LCharBinaryOperator sutThrowing = LCharBinaryOperator.l((char c1,char c2) -> {
            throw new UnsupportedOperationException();
        });

        // when
        LCharBinaryOperator wrapped = LCharBinaryOperator.wrapException(sutThrowing, UnsupportedOperationException.class, null, t -> {
            return null;
        });

        // then
        try {
            wrapped.doApplyAsChar((char)100,(char)100);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(ExceptionNotHandled.class)
                    .hasCauseExactlyInstanceOf(UnsupportedOperationException.class)
                    .hasMessage("Handler has not processed the exception.");
        }
    }


    @Test
    public void testMin() throws ParseException {
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
    public void testMax() throws ParseException {
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
    public void testfromChar() throws ParseException {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LCharBinaryOperator sutO = (char c1,char c2) -> {
                mainFunctionCalled.set(true);
                assertThat(c1).isEqualTo((char)90);
                assertThat(c2).isEqualTo((char)91);
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
        LCharBinaryOperator function = sutO.fromChar(before1,before2);
        function.doApplyAsChar((char)80,(char)81);

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(beforeCalls.get()).isEqualTo(2);
    }


    @Test
    public void testfrom() throws ParseException {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LCharBinaryOperator sutO = (char c1,char c2) -> {
                mainFunctionCalled.set(true);
                assertThat(c1).isEqualTo((char)90);
                assertThat(c2).isEqualTo((char)91);
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
        LToCharBiFunction<Integer ,Integer > function = sutO.from(before1,before2);
        function.doApplyAsChar((Integer )Integer.valueOf(80),(Integer )Integer.valueOf(81));

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
        LCharBinaryOperator sutO = (char c1,char c2) -> {
                mainFunctionCalled.set(true);
                assertThat(c1).isEqualTo((char)80);
                assertThat(c2).isEqualTo((char)81);
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
        LCharBiFunction<Integer > function = sutO.then(thenFunction);
        Integer  finalValue = function.doApply((char)80,(char)81);

        //then - finals
        assertThat(finalValue).isEqualTo(Integer.valueOf(100));
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    // </editor-fold>
//
    @Test
    public void testNesting() {
        assertThat(sut.nest())
            .isSameAs(sut)
            .isInstanceOf(LCharBinaryOperator.class);
    }

    @Test
    public void testShoving() {
        assertThat(sut.shove())
            .isSameAs(sut)
            .isInstanceOf(LCharBinaryOperator.class);
    }

    @Test
    public void testNestingX() {
        assertThat(sut.nestX())
            .isSameAs(sut)
            .isInstanceOf(LCharBinaryOperatorX.class);
    }

    @Test
    public void testShovingX() {
        assertThat(sut.shoveX())
            .isSameAs(sut)
            .isInstanceOf(LCharBinaryOperatorX.class);
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void testShove() {

        // given
        LCharBinaryOperator sutThrowing = LCharBinaryOperator.l((char c1,char c2) -> {
            throw new UnsupportedOperationException();
        });

        // when
        sutThrowing.shove().doApplyAsChar((char)100,(char)100);
    }

    @Test
    public void testHandle() throws ParseException {

        // given
        LCharBinaryOperator sutThrowing = LCharBinaryOperator.l((char c1,char c2) -> {
            throw new UnsupportedOperationException();
        });

        // when
        LCharBinaryOperator wrapped = sutThrowing.handle(UnsupportedOperationException.class, t -> {
            throw new IllegalArgumentException(EXCEPTION_WAS_WRAPPED, t);
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
    public void testToString() throws ParseException {

        assertThat(sut.toString())
                .isInstanceOf(String.class)
                .startsWith(this.getClass().getName()+"$");

        assertThat(String.format("%s", sut))
                .isInstanceOf(String.class)
                .contains("LCharBinaryOperator: char doApplyAsChar(char c1,char c2)");
    }


    @Test
    public void isThrowing() {
        assertThat(sut.isThrowing())
            .isFalse();
    }


}
