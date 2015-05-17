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

package eu.lunisolar.magma.func.consumer.primitives.bi;

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
public class LFloatBiConsumerXTest<X extends ParseException> {
    private static final String ORIGINAL_MESSAGE = "Original message";
    private static final String EXCEPTION_WAS_WRAPPED = "Exception was wrapped.";
    private static final String NO_EXCEPTION_WERE_THROWN = "No exception were thrown.";



    private LFloatBiConsumerX<X> sut = new LFloatBiConsumerX(){
        public  void doAccept(float f1,float f2) throws ParseException {
            Function4U.doNothing();
        }
    };

    private LFloatBiConsumer opposite = new LFloatBiConsumer(){
        public  void doAccept(float f1,float f2)  {
            Function4U.doNothing();
        }
    };



    private LFloatBiConsumerX<ParseException> sutAlwaysThrowing = LFloatBiConsumerX.lX((float f1,float f2) -> {
            throw new ParseException(ORIGINAL_MESSAGE, 0);
    });

    private LFloatBiConsumerX<RuntimeException> sutAlwaysThrowingUnckeck = LFloatBiConsumerX.lX((float f1,float f2) -> {
            throw new IndexOutOfBoundsException(ORIGINAL_MESSAGE);
    });




    @Test
    public void testFunctionalInterfaceDescription() throws ParseException {
        assertThat(sut.functionalInterfaceDescription())
            .isEqualTo("LFloatBiConsumerX: void doAccept(float f1,float f2) throws X");
    }

    @Test
    public void testLXMethod() throws ParseException {
        assertThat(LFloatBiConsumerX.lX((float f1,float f2) -> Function4U.doNothing() ))
            .isInstanceOf(LFloatBiConsumerX.class);
    }

    @Test
    public void testWrapXMethod() throws ParseException {
        assertThat(LFloatBiConsumerX.wrapX(opposite))
            .isInstanceOf(LFloatBiConsumerX.class);
    }


    @Test
    public void testWrapExceptionMethodWrapsTheException() throws ParseException {

        // given
        LFloatBiConsumerX<X> sutThrowing = LFloatBiConsumerX.lX((float f1,float f2) -> {
            throw new UnsupportedOperationException();
        });

        // when
        LFloatBiConsumerX<X> wrapped = LFloatBiConsumerX.wrapException(sutThrowing, UnsupportedOperationException.class, t -> {
            throw new IllegalArgumentException(EXCEPTION_WAS_WRAPPED, t);
        });

        // then
        try {
            wrapped.doAccept((float)100,(float)100);
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
        LFloatBiConsumerX<X> sutThrowing = LFloatBiConsumerX.lX((float f1,float f2) -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LFloatBiConsumerX<X> wrapped = LFloatBiConsumerX.wrapException(sutThrowing, UnsupportedOperationException.class, t -> {
            throw new IllegalArgumentException(EXCEPTION_WAS_WRAPPED, t);
        });

        // then
        try {
            wrapped.doAccept((float)100,(float)100);
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
        LFloatBiConsumerX<X> sutThrowing = LFloatBiConsumerX.lX((float f1,float f2) -> {
            throw new UnsupportedOperationException();
        });

        // when
        LFloatBiConsumerX<X> wrapped = LFloatBiConsumerX.wrapException(sutThrowing, UnsupportedOperationException.class, t -> {
            return null;
        });

        // then
        try {
            wrapped.doAccept((float)100,(float)100);
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
        LFloatBiConsumerX<X> sutO = (float f1,float f2) -> {
                mainFunctionCalled.set(true);
                assertThat(f1).isEqualTo((float)90);
                assertThat(f2).isEqualTo((float)91);
        };

        LFloatUnaryOperatorX<X> before1 = p0 -> {
            assertThat(p0).isEqualTo((float)80);
            beforeCalls.incrementAndGet();
            return (float)90;
        };
        LFloatUnaryOperatorX<X> before2 = p1 -> {
            assertThat(p1).isEqualTo((float)81);
            beforeCalls.incrementAndGet();
            return (float)91;
        };

        //when
        LFloatBiConsumerX<X> function = sutO.fromFloat(before1,before2);
        function.doAccept((float)80,(float)81);

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(beforeCalls.get()).isEqualTo(2);
    }


    @Test
    public void testfrom() throws ParseException {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LFloatBiConsumerX<X> sutO = (float f1,float f2) -> {
                mainFunctionCalled.set(true);
                assertThat(f1).isEqualTo((float)90);
                assertThat(f2).isEqualTo((float)91);
        };

        LToFloatFunctionX<Integer ,X> before1 = p0 -> {
            assertThat(p0).isEqualTo(Integer.valueOf(80));
            beforeCalls.incrementAndGet();
            return (float)90;
        };
        LToFloatFunctionX<Integer ,X> before2 = p1 -> {
            assertThat(p1).isEqualTo(Integer.valueOf(81));
            beforeCalls.incrementAndGet();
            return (float)91;
        };

        //when
        LBiConsumerX<Integer ,Integer ,X> function = sutO.from(before1,before2);
        function.doAccept((Integer )Integer.valueOf(80),(Integer )Integer.valueOf(81));

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(beforeCalls.get()).isEqualTo(2);
    }

    // </editor-fold>

    @Test
    public void testAndThen() throws ParseException {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);

         //given (+ some assertions)
        LFloatBiConsumerX<X> sutO = (float f1,float f2) -> {
                mainFunctionCalled.set(true);
                assertThat(f1).isEqualTo((float)80);
                assertThat(f2).isEqualTo((float)81);
        };

        LFloatBiConsumerX<X> thenFunction = (float f1,float f2) -> {
                thenFunctionCalled.set(true);
                assertThat(f1).isEqualTo((float)80);
                assertThat(f2).isEqualTo((float)81);
        };

        //when
        LFloatBiConsumerX<X> function = sutO.andThen(thenFunction);
        function.doAccept((float)80,(float)81);

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);
    }

//
    @Test
    public void testNesting() {
        assertThat(sut.nest())
            .isInstanceOf(LFloatBiConsumer.class);
    }

    @Test
    public void testShoving() {
        assertThat(sut.shove())
            .isInstanceOf(LFloatBiConsumer.class);
    }

    @Test
    public void testNestingX() {
        assertThat(sut.nestX())
            .isInstanceOf(LFloatBiConsumerX.class);
    }

    @Test
    public void testShovingX() {
        assertThat(sut.shoveX())
            .isInstanceOf(LFloatBiConsumerX.class);
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void testShove() {

        // given
        LFloatBiConsumerX<X> sutThrowing = LFloatBiConsumerX.lX((float f1,float f2) -> {
            throw new UnsupportedOperationException();
        });

        // when
        sutThrowing.shove().doAccept((float)100,(float)100);
    }

    @Test
    public void testHandleX() throws ParseException {

        // given
        LFloatBiConsumerX<X> sutThrowing = LFloatBiConsumerX.lX((float f1,float f2) -> {
            throw new UnsupportedOperationException();
        });

        // when
        LFloatBiConsumerX<X> wrapped = sutThrowing.handleX(UnsupportedOperationException.class, t -> {
            throw new IllegalArgumentException(EXCEPTION_WAS_WRAPPED, t);
        });

        // then
        try {
            wrapped.doAccept((float)100,(float)100);
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
                .contains("LFloatBiConsumerX: void doAccept(float f1,float f2) throws X");
    }


    @Test
    public void isThrowing() {
        assertThat(sut.isThrowing())
            .isTrue();
    }


}
