/*
 * This file is part of "lunisolar-magma".
 *
 * (C) Copyright 2015 Lunisolar (http://lunisolar.eu/).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
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
import eu.lunisolar.magma.func.consumer.primitives.obj.*; // NOSONAR
import eu.lunisolar.magma.func.consumer.primitives.bi.*; // NOSONAR
import eu.lunisolar.magma.func.consumer.primitives.tri.*; // NOSONAR
import eu.lunisolar.magma.func.consumer.primitives.*; // NOSONAR
import eu.lunisolar.magma.func.consumer.*; // NOSONAR
import eu.lunisolar.magma.func.action.*; // NOSONAR

import java.util.function.*; // NOSONAR
import org.assertj.core.api.Assertions;  //NOSONAR
import org.testng.annotations.*;      //NOSONAR
import java.util.regex.Pattern;          //NOSONAR
import java.text.ParseException;         //NOSONAR
import eu.lunisolar.magma.basics.*; //NOSONAR
import eu.lunisolar.magma.basics.exceptions.*; //NOSONAR
import java.util.concurrent.atomic.AtomicInteger; //NOSONAR
import eu.lunisolar.magma.struct.tuple.*; // NOSONAR
import static org.assertj.core.api.Assertions.*; //NOSONAR

/** The test obviously concentrate on the interface methods the function it self is very simple.  */
public class LObjDoubleConsumerXTest<T,X extends ParseException> {
    private static final String ORIGINAL_MESSAGE = "Original message";
    private static final String EXCEPTION_WAS_WRAPPED = "Exception was wrapped.";
    private static final String NO_EXCEPTION_WERE_THROWN = "No exception were thrown.";



    private LObjDoubleConsumerX<T,X> sut = new LObjDoubleConsumerX(){
        public  void doAccept(Object a1,double a2) throws ParseException {
            Function4U.doNothing();
        }
    };

    private LObjDoubleConsumer<T> opposite = new LObjDoubleConsumer(){
        public  void doAccept(Object a1,double a2)  {
            Function4U.doNothing();
        }
    };


    private ObjDoubleConsumer jre = (Object a1,double a2) -> Function4U.doNothing();


    private LObjDoubleConsumerX<T,ParseException> sutAlwaysThrowing = LObjDoubleConsumerX.lX((T a1,double a2) -> {
            throw new ParseException(ORIGINAL_MESSAGE, 0);
    });

    private LObjDoubleConsumerX<T,RuntimeException> sutAlwaysThrowingUnckeck = LObjDoubleConsumerX.lX((T a1,double a2) -> {
            throw new IndexOutOfBoundsException(ORIGINAL_MESSAGE);
    });



    @Test
    public void testTupleCall() throws X {

        LObjDoublePair<T> domainObject = Tuple4U.tuple((T)Integer.valueOf(100),(double)100);

        Object result = sut.tupleAccept(domainObject);

        assertThat(result)
            .isSameAs(LTuple.Void.INSTANCE);
    }

    @Test
    public void testNestingDoAcceptChecked() throws X {

        // then
        try {
            sutAlwaysThrowing.nestingDoAccept((T)Integer.valueOf(100),(double)100);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(NestedException.class)
                    .hasCauseExactlyInstanceOf(ParseException.class)
                    .hasMessage(ORIGINAL_MESSAGE);
        }
    }

    @Test
    public void testNestingDoAcceptUnckeck() throws X {

        // then
        try {
            sutAlwaysThrowingUnckeck.nestingDoAccept((T)Integer.valueOf(100),(double)100);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                    .hasNoCause()
                    .hasMessage(ORIGINAL_MESSAGE);
        }
    }

    @Test
    public void testShovingDoAcceptChecked() throws X {

        // then
        try {
            sutAlwaysThrowing.shovingDoAccept((T)Integer.valueOf(100),(double)100);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(ParseException.class)
                    .hasNoCause()
                    .hasMessage(ORIGINAL_MESSAGE);
        }
    }

    @Test
    public void testShovingDoAcceptUnckeck() throws X {

        // then
        try {
            sutAlwaysThrowingUnckeck.shovingDoAccept((T)Integer.valueOf(100),(double)100);
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
            .isEqualTo("LObjDoubleConsumerX: void doAccept(T a1,double a2) throws X");
    }

    @Test
    public void testLXMethod() throws X {
        assertThat(LObjDoubleConsumerX.lX((Object a1,double a2) -> Function4U.doNothing() ))
            .isInstanceOf(LObjDoubleConsumerX.class);
    }

    @Test
    public void testWrapXMethod() throws X {
        assertThat(LObjDoubleConsumerX.wrapX(opposite))
            .isInstanceOf(LObjDoubleConsumerX.class);
    }

    @Test
    public void testWrapStdMethod() throws X {
        assertThat(LObjDoubleConsumerX.wrap(jre))
            .isInstanceOf(LObjDoubleConsumerX.class);
    }


    @Test
    public void testWrapExceptionMethodWrapsTheException() throws X {

        // given
        LObjDoubleConsumerX<T,X> sutThrowing = LObjDoubleConsumerX.lX((T a1,double a2) -> {
            throw new UnsupportedOperationException();
        });

        // when
        LObjDoubleConsumerX<T,X> wrapped = sutThrowing.handleObjDoubleConsX(handler -> handler
            .wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED));

        // then
        try {
            wrapped.doAccept((T)Integer.valueOf(100),(double)100);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IllegalArgumentException.class)
                    .hasCauseExactlyInstanceOf(UnsupportedOperationException.class)
                    .hasMessage(EXCEPTION_WAS_WRAPPED);
        }
    }

    @Test
    public void testWrapExceptionMethodDoNotWrapsOtherExceptionIf() throws X {

        // given
        LObjDoubleConsumerX<T,X> sutThrowing = LObjDoubleConsumerX.lX((T a1,double a2) -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LObjDoubleConsumerX<T,X> wrapped = sutThrowing.handleObjDoubleConsX(handler -> handler
                .wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED)
                .throwIf(IndexOutOfBoundsException.class));

        // then
        try {
            wrapped.doAccept((T)Integer.valueOf(100),(double)100);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                    .hasNoCause();
        }
    }

@Test
    public void testWrapExceptionMethodDoNotWrapsOtherExceptionWhen() throws X {

        // given
        LObjDoubleConsumerX<T,X> sutThrowing = LObjDoubleConsumerX.lX((T a1,double a2) -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LObjDoubleConsumerX<T,X> wrapped = sutThrowing.handleObjDoubleConsX(handler -> handler
                .wrapWhen(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED)
                .throwIf(IndexOutOfBoundsException.class));

        // then
        try {
            wrapped.doAccept((T)Integer.valueOf(100),(double)100);
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
        LObjDoubleConsumerX<T,X> sutThrowing = LObjDoubleConsumerX.lX((T a1,double a2) -> {
            throw (X) new ParseException(ORIGINAL_MESSAGE, 0);
        });

        // when
        LObjDoubleConsumerX<T,X> wrapped = sutThrowing.handleObjDoubleConsX(h -> Function4U.doNothing());

        // then
        try {
            wrapped.doAccept((T)Integer.valueOf(100),(double)100);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(NestedException.class)
                    .hasCauseExactlyInstanceOf(ParseException.class)
                    .hasMessage(ORIGINAL_MESSAGE);
        }
    }



    // <editor-fold desc="compose (functional)">

    @Test
    public void testobjDoubleConsComposeDouble() throws X {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LObjDoubleConsumerX<Integer ,X> sutO = (Integer a1,double a2) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo((T)Integer.valueOf(90));
                assertThat(a2).isEqualTo((double)91);
        };

        LFunctionX<Integer ,Integer ,X> before1 = p0 -> {
            assertThat(p0).isEqualTo((T)Integer.valueOf(80));
            beforeCalls.incrementAndGet();
            return 90;
        };
        LDoubleUnaryOperatorX<X> before2 = p1 -> {
            assertThat(p1).isEqualTo((double)81);
            beforeCalls.incrementAndGet();
            return (double)91;
        };

        //when
        LObjDoubleConsumerX<Integer ,X> function = sutO.objDoubleConsComposeDouble(before1,before2);
        function.doAccept((Integer )Integer.valueOf(80),(double)81);

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(beforeCalls.get()).isEqualTo(2);
    }


    @Test
    public void testobjDoubleConsCompose() throws X {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LObjDoubleConsumerX<Integer ,X> sutO = (Integer a1,double a2) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo((T)Integer.valueOf(90));
                assertThat(a2).isEqualTo((double)91);
        };

        LFunctionX<Integer ,Integer ,X> before1 = p0 -> {
            assertThat(p0).isEqualTo(Integer.valueOf(80));
            beforeCalls.incrementAndGet();
            return 90;
        };
        LToDoubleFunctionX<Integer ,X> before2 = p1 -> {
            assertThat(p1).isEqualTo(Integer.valueOf(81));
            beforeCalls.incrementAndGet();
            return (double)91;
        };

        //when
        LBiConsumerX<Integer ,Integer ,X> function = sutO.objDoubleConsCompose(before1,before2);
        function.doAccept((Integer )Integer.valueOf(80),(Integer )Integer.valueOf(81));

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(beforeCalls.get()).isEqualTo(2);
    }

    // </editor-fold>

    @Test
    public void testAndThen() throws X {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);

         //given (+ some assertions)
        LObjDoubleConsumerX<Integer ,X> sutO = (Integer a1,double a2) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo((T)Integer.valueOf(80));
                assertThat(a2).isEqualTo((double)81);
        };

        LObjDoubleConsumerX<Integer ,X> thenFunction = (Integer a1,double a2) -> {
                thenFunctionCalled.set(true);
                assertThat(a1).isEqualTo((T)Integer.valueOf(80));
                assertThat(a2).isEqualTo((double)81);
        };

        //when
        LObjDoubleConsumerX<Integer ,X> function = sutO.andThen(thenFunction);
        function.doAccept((Integer )Integer.valueOf(80),(double)81);

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);
    }


    @Test
    public void testNesting() {
        assertThat(sut.nestingObjDoubleCons())
            .isInstanceOf(LObjDoubleConsumer.class);
    }

    @Test
    public void testShoving() {
        assertThat(sut.shovingObjDoubleCons())
            .isInstanceOf(LObjDoubleConsumer.class);
    }

    @Test
    public void testNestingX() {
        assertThat(sut.nestingObjDoubleConsX())
            .isInstanceOf(LObjDoubleConsumerX.class);
    }

    @Test
    public void testShovingX() {
        assertThat(sut.shovingObjDoubleConsX())
            .isInstanceOf(LObjDoubleConsumerX.class);
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void testShove() {

        // given
        LObjDoubleConsumerX<T,X> sutThrowing = LObjDoubleConsumerX.lX((T a1,double a2) -> {
            throw new UnsupportedOperationException();
        });

        // when
        sutThrowing.shovingObjDoubleCons().doAccept((T)Integer.valueOf(100),(double)100);
    }

    @Test
    public void testHandleObjDoubleCons() throws X {

        // given
        LObjDoubleConsumerX<T,X> sutThrowing = LObjDoubleConsumerX.lX((T a1,double a2) -> {
            throw new UnsupportedOperationException();
        });

        // when
        LObjDoubleConsumerX<T,X> wrapped = sutThrowing.handleObjDoubleConsX(h -> {
            h.wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED);
        });

        // then
        try {
            wrapped.doAccept((T)Integer.valueOf(100),(double)100);
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
                .contains("LObjDoubleConsumerX: void doAccept(T a1,double a2) throws X");
    }


    @Test
    public void isThrowing() {
        assertThat(sut.isThrowing())
            .isTrue();
    }

    //<editor-fold desc="Variants">

    private void variant1(double a2,T a1) {
    }

    @Test
    public void compilerSubstituteVariant1() {
        LObjDoubleConsumerX lambda = LObjDoubleConsumerX./*<T,X>*/lX1(this::variant1);

        assertThat(lambda).isInstanceOf(LObjDoubleConsumerX.V1.class);
    }

    //</editor-fold>


    @Test void safeCompiles() {
        LObjDoubleConsumerX r1 = LObjDoubleConsumerX.safe(sut); //NOSONAR
        ObjDoubleConsumer r3 = LObjDoubleConsumerX.safe(sut); //NOSONAR
    }

    @Test void safePropagates() {
        Object result = LObjDoubleConsumerX.safe(sut);
        assertThat(result).isSameAs(sut);
    }

    @Test void safeProtectsAgainstNpe() {
        Object result = LObjDoubleConsumerX.safe(null);
        assertThat(result).isSameAs(LObjDoubleConsumerX.lX(LObjDoubleConsumerX.safe()));
    }

    @Test <Y extends Throwable> void safeSupplierPropagates() {
        LSupplierX<LObjDoubleConsumerX<T,X>,Y> supplier = ()->sut;
        Object result = LObjDoubleConsumerX.safeSupplier(supplier);
        assertThat(result).isSameAs(supplier);
    }

    @Test <Y extends Throwable> void safeSupplierProtectsAgainstNpe() {
        Object result = LObjDoubleConsumerX.safeSupplier(null);
        assertThat(result).isSameAs(LObjDoubleConsumerX.safeSupplier());
    }

    @Test <Y extends Throwable> void safeSupplierCompiles() {
        LSupplierX<LObjDoubleConsumerX<T,X>,Y> r1 = LObjDoubleConsumerX.safeSupplier(()->sut);  //NOSONAR
        Supplier<LObjDoubleConsumerX<T,X>> r2 = LObjDoubleConsumerX.safeSupplier(()->sut); //NOSONAR
    }

}
