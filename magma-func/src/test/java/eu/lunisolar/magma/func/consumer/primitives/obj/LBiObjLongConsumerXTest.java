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

package eu.lunisolar.magma.func.consumer.primitives.obj;

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
public class LBiObjLongConsumerXTest<T1,T2,X extends ParseException> {
    private static final String ORIGINAL_MESSAGE = "Original message";
    private static final String EXCEPTION_WAS_WRAPPED = "Exception was wrapped.";
    private static final String NO_EXCEPTION_WERE_THROWN = "No exception were thrown.";



    private LBiObjLongConsumerX<T1,T2,X> sut = new LBiObjLongConsumerX(){
        public  void doAccept(Object t1,Object t2, long l) throws ParseException {
            Function4U.doNothing();
        }
    };

    private LBiObjLongConsumer<T1,T2> opposite = new LBiObjLongConsumer(){
        public  void doAccept(Object t1,Object t2, long l)  {
            Function4U.doNothing();
        }
    };



    private LBiObjLongConsumerX<T1,T2,ParseException> sutAlwaysThrowing = LBiObjLongConsumerX.lX((T1 t1,T2 t2, long l) -> {
            throw new ParseException(ORIGINAL_MESSAGE, 0);
    });

    private LBiObjLongConsumerX<T1,T2,RuntimeException> sutAlwaysThrowingUnckeck = LBiObjLongConsumerX.lX((T1 t1,T2 t2, long l) -> {
            throw new IndexOutOfBoundsException(ORIGINAL_MESSAGE);
    });




    @Test
    public void testFunctionalInterfaceDescription() throws ParseException {
        assertThat(sut.functionalInterfaceDescription())
            .isEqualTo("LBiObjLongConsumerX: void doAccept(T1 t1,T2 t2, long l) throws X");
    }

    @Test
    public void testLXMethod() throws ParseException {
        assertThat(LBiObjLongConsumerX.lX((Object t1,Object t2, long l) -> Function4U.doNothing() ))
            .isInstanceOf(LBiObjLongConsumerX.class);
    }

    @Test
    public void testWrapXMethod() throws ParseException {
        assertThat(LBiObjLongConsumerX.wrapX(opposite))
            .isInstanceOf(LBiObjLongConsumerX.class);
    }


    @Test
    public void testWrapExceptionMethodWrapsTheException() throws ParseException {

        // given
        LBiObjLongConsumerX<T1,T2,X> sutThrowing = LBiObjLongConsumerX.lX((T1 t1,T2 t2, long l) -> {
            throw new UnsupportedOperationException();
        });

        // when
        LBiObjLongConsumerX<T1,T2,X> wrapped = LBiObjLongConsumerX.wrapException(sutThrowing, UnsupportedOperationException.class, t -> {
            throw new IllegalArgumentException(EXCEPTION_WAS_WRAPPED, t);
        });

        // then
        try {
            wrapped.doAccept((T1)Integer.valueOf(100),(T2)Integer.valueOf(100),(long)100);
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
        LBiObjLongConsumerX<T1,T2,X> sutThrowing = LBiObjLongConsumerX.lX((T1 t1,T2 t2, long l) -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LBiObjLongConsumerX<T1,T2,X> wrapped = LBiObjLongConsumerX.wrapException(sutThrowing, UnsupportedOperationException.class, t -> {
            throw new IllegalArgumentException(EXCEPTION_WAS_WRAPPED, t);
        });

        // then
        try {
            wrapped.doAccept((T1)Integer.valueOf(100),(T2)Integer.valueOf(100),(long)100);
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
        LBiObjLongConsumerX<T1,T2,X> sutThrowing = LBiObjLongConsumerX.lX((T1 t1,T2 t2, long l) -> {
            throw new UnsupportedOperationException();
        });

        // when
        LBiObjLongConsumerX<T1,T2,X> wrapped = LBiObjLongConsumerX.wrapException(sutThrowing, UnsupportedOperationException.class, t -> {
            return null;
        });

        // then
        try {
            wrapped.doAccept((T1)Integer.valueOf(100),(T2)Integer.valueOf(100),(long)100);
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
    public void testfromLong() throws ParseException {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LBiObjLongConsumerX<Integer ,Integer ,X> sutO = (Integer t1,Integer t2, long l) -> {
                mainFunctionCalled.set(true);
                assertThat(t1).isEqualTo((T1)Integer.valueOf(90));
                assertThat(t2).isEqualTo((T2)Integer.valueOf(91));
                assertThat( l).isEqualTo((long)92);
        };

        LFunctionX<Integer ,Integer ,X> before1 = p0 -> {
            assertThat(p0).isEqualTo((T1)Integer.valueOf(80));
            beforeCalls.incrementAndGet();
            return 90;
        };
        LFunctionX<Integer ,Integer ,X> before2 = p1 -> {
            assertThat(p1).isEqualTo((T2)Integer.valueOf(81));
            beforeCalls.incrementAndGet();
            return 91;
        };
        LLongUnaryOperatorX<X> before3 = p2 -> {
            assertThat(p2).isEqualTo((long)82);
            beforeCalls.incrementAndGet();
            return (long)92;
        };

        //when
        LBiObjLongConsumerX<Integer ,Integer ,X> function = sutO.fromLong(before1,before2,before3);
        function.doAccept((Integer )Integer.valueOf(80),(Integer )Integer.valueOf(81),(long)82);

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(beforeCalls.get()).isEqualTo(3);
    }


    @Test
    public void testfrom() throws ParseException {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LBiObjLongConsumerX<Integer ,Integer ,X> sutO = (Integer t1,Integer t2, long l) -> {
                mainFunctionCalled.set(true);
                assertThat(t1).isEqualTo((T1)Integer.valueOf(90));
                assertThat(t2).isEqualTo((T2)Integer.valueOf(91));
                assertThat( l).isEqualTo((long)92);
        };

        LFunctionX<Integer ,Integer ,X> before1 = p0 -> {
            assertThat(p0).isEqualTo(Integer.valueOf(80));
            beforeCalls.incrementAndGet();
            return 90;
        };
        LFunctionX<Integer ,Integer ,X> before2 = p1 -> {
            assertThat(p1).isEqualTo(Integer.valueOf(81));
            beforeCalls.incrementAndGet();
            return 91;
        };
        LToLongFunctionX<Integer ,X> before3 = p2 -> {
            assertThat(p2).isEqualTo(Integer.valueOf(82));
            beforeCalls.incrementAndGet();
            return (long)92;
        };

        //when
        LTriConsumerX<Integer ,Integer ,Integer ,X> function = sutO.from(before1,before2,before3);
        function.doAccept((Integer )Integer.valueOf(80),(Integer )Integer.valueOf(81),(Integer )Integer.valueOf(82));

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(beforeCalls.get()).isEqualTo(3);
    }

    // </editor-fold>

    @Test
    public void testAndThen() throws ParseException {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);

         //given (+ some assertions)
        LBiObjLongConsumerX<Integer ,Integer ,X> sutO = (Integer t1,Integer t2, long l) -> {
                mainFunctionCalled.set(true);
                assertThat(t1).isEqualTo((T1)Integer.valueOf(80));
                assertThat(t2).isEqualTo((T2)Integer.valueOf(81));
                assertThat( l).isEqualTo((long)82);
        };

        LBiObjLongConsumerX<Integer ,Integer ,X> thenFunction = (Integer t1,Integer t2, long l) -> {
                thenFunctionCalled.set(true);
                assertThat(t1).isEqualTo((T1)Integer.valueOf(80));
                assertThat(t2).isEqualTo((T2)Integer.valueOf(81));
                assertThat( l).isEqualTo((long)82);
        };

        //when
        LBiObjLongConsumerX<Integer ,Integer ,X> function = sutO.andThen(thenFunction);
        function.doAccept((Integer )Integer.valueOf(80),(Integer )Integer.valueOf(81),(long)82);

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);
    }

//
    @Test
    public void testNesting() {
        assertThat(sut.nest())
            .isInstanceOf(LBiObjLongConsumer.class);
    }

    @Test
    public void testShoving() {
        assertThat(sut.shove())
            .isInstanceOf(LBiObjLongConsumer.class);
    }

    @Test
    public void testNestingX() {
        assertThat(sut.nestX())
            .isInstanceOf(LBiObjLongConsumerX.class);
    }

    @Test
    public void testShovingX() {
        assertThat(sut.shoveX())
            .isInstanceOf(LBiObjLongConsumerX.class);
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void testShove() {

        // given
        LBiObjLongConsumerX<T1,T2,X> sutThrowing = LBiObjLongConsumerX.lX((T1 t1,T2 t2, long l) -> {
            throw new UnsupportedOperationException();
        });

        // when
        sutThrowing.shove().doAccept((T1)Integer.valueOf(100),(T2)Integer.valueOf(100),(long)100);
    }

    @Test
    public void testHandleX() throws ParseException {

        // given
        LBiObjLongConsumerX<T1,T2,X> sutThrowing = LBiObjLongConsumerX.lX((T1 t1,T2 t2, long l) -> {
            throw new UnsupportedOperationException();
        });

        // when
        LBiObjLongConsumerX<T1,T2,X> wrapped = sutThrowing.handleX(UnsupportedOperationException.class, t -> {
            throw new IllegalArgumentException(EXCEPTION_WAS_WRAPPED, t);
        });

        // then
        try {
            wrapped.doAccept((T1)Integer.valueOf(100),(T2)Integer.valueOf(100),(long)100);
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
                .contains("LBiObjLongConsumerX: void doAccept(T1 t1,T2 t2, long l) throws X");
    }


    @Test
    public void isThrowing() {
        assertThat(sut.isThrowing())
            .isTrue();
    }


}
