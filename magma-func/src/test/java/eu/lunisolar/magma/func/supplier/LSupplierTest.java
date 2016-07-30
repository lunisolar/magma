/*
 * This file is part of "lunisolar-magma".
 *
 * (C) Copyright 2014-2016 Lunisolar (http://lunisolar.eu/).
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

package eu.lunisolar.magma.func.supplier;

import eu.lunisolar.magma.func.*; // NOSONAR
import javax.annotation.Nonnull; // NOSONAR
import javax.annotation.Nullable; // NOSONAR
import java.util.Objects;// NOSONAR
import eu.lunisolar.magma.basics.meta.*; // NOSONAR
import eu.lunisolar.magma.basics.meta.functional.*; // NOSONAR
import eu.lunisolar.magma.basics.meta.functional.type.*; // NOSONAR
import eu.lunisolar.magma.basics.meta.functional.domain.*; // NOSONAR

import eu.lunisolar.magma.func.action.*; // NOSONAR
import eu.lunisolar.magma.func.consumer.*; // NOSONAR
import eu.lunisolar.magma.func.consumer.primitives.*; // NOSONAR
import eu.lunisolar.magma.func.consumer.primitives.bi.*; // NOSONAR
import eu.lunisolar.magma.func.consumer.primitives.obj.*; // NOSONAR
import eu.lunisolar.magma.func.consumer.primitives.tri.*; // NOSONAR
import eu.lunisolar.magma.func.function.*; // NOSONAR
import eu.lunisolar.magma.func.function.conversion.*; // NOSONAR
import eu.lunisolar.magma.func.function.from.*; // NOSONAR
import eu.lunisolar.magma.func.function.to.*; // NOSONAR
import eu.lunisolar.magma.func.operator.binary.*; // NOSONAR
import eu.lunisolar.magma.func.operator.ternary.*; // NOSONAR
import eu.lunisolar.magma.func.operator.unary.*; // NOSONAR
import eu.lunisolar.magma.func.predicate.*; // NOSONAR
import eu.lunisolar.magma.func.supplier.*; // NOSONAR

import org.assertj.core.api.Assertions;  //NOSONAR
import org.testng.annotations.*;      //NOSONAR
import java.util.regex.Pattern;          //NOSONAR
import java.text.ParseException;         //NOSONAR
import eu.lunisolar.magma.basics.*; //NOSONAR
import eu.lunisolar.magma.basics.exceptions.*; //NOSONAR
import java.util.concurrent.atomic.AtomicInteger; //NOSONAR
import eu.lunisolar.magma.struct.tuple.*; // NOSONAR
import static org.assertj.core.api.Assertions.*; //NOSONAR
import java.util.function.*; // NOSONAR

/** The test obviously concentrate on the interface methods the function it self is very simple.  */
public class LSupplierTest<T,X extends ParseException> {
    private static final String ORIGINAL_MESSAGE = "Original message";
    private static final String EXCEPTION_WAS_WRAPPED = "Exception was wrapped.";
    private static final String NO_EXCEPTION_WERE_THROWN = "No exception were thrown.";

    private Integer testValue = 100;



    private LSupplier<Integer> sut = new LSupplier<Integer>(){
        public @Nullable Integer doGet()  {
            return testValue;
        }
    };

    private LSupplierX<Integer,X> opposite = new LSupplierX<Integer,X>(){
        public @Nullable Integer doGet()  throws X {
            return testValue;
        }
    };

    private LSupplier<Integer> sutNull = new LSupplier<Integer>(){
        public @Nullable Integer doGet()  {
            return null;
        }
    };


    private Supplier<Integer> jre = () -> testValue;



    private LSupplierX<Integer,RuntimeException> sutAlwaysThrowingUnchecked = LSupplier.l(() -> {
            throw new IndexOutOfBoundsException(ORIGINAL_MESSAGE);
    });


    @Test
    public void testTheResult() throws X {
        assertThat(sut.doGet())
            .isEqualTo(testValue);
    }

    @Test
    public void testTupleCall() throws X {

        LTuple.Void domainObject = Tuple4U.tuple();

        Object result = sut.tupleGet(domainObject);

        assertThat(result)
            .isEqualTo(testValue);
    }

    @Test
    public void testNonNullDoGet() throws X {
        assertThat(sut.nonNullDoGet())
            .isSameAs(testValue);
    }

    @Test
    public void testNestingDoGetUnchecked() throws X {

        // then
        try {
            sutAlwaysThrowingUnchecked.nestingDoGet();
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                    .hasNoCause()
                    .hasMessage(ORIGINAL_MESSAGE);
        }
    }

    @Test
    public void testShovingDoGetUnchecked() throws X {

        // then
        try {
            sutAlwaysThrowingUnchecked.shovingDoGet();
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                    .hasNoCause()
                    .hasMessage(ORIGINAL_MESSAGE);
        }
    }

    @Test(expectedExceptions=NullPointerException.class, expectedExceptionsMessageRegExp="\\QEvaluated value by nonNullDoGet() method cannot be null (LSupplier: T doGet()).\\E")
    public void testNonNullCapturesNull() throws X {
        sutNull.nonNullDoGet();
    }


    @Test
    public void testFunctionalInterfaceDescription() throws X {
        assertThat(sut.functionalInterfaceDescription())
            .isEqualTo("LSupplier: T doGet()");
    }

    @Test
    public void testLMethod() throws X {
        assertThat(LSupplier.l(() -> testValue ))
            .isInstanceOf(LSupplier.class);
    }

    @Test
    public void testWrapMethod() throws X {
        assertThat(LSupplier.wrap(opposite))
            .isInstanceOf(LSupplier.class);
    }

    @Test
    public void testWrapStdMethod() throws X {
        assertThat(LSupplier.wrap(jre))
            .isInstanceOf(LSupplier.class);
    }

    @Test
    public void testWrapMethodDoNotWrapsRuntimeException() throws X {
        // given
        LSupplierX<Integer,X> sutThrowing = LSupplierX.lX(() -> {
            throw new UnsupportedOperationException(ORIGINAL_MESSAGE);
        });

        // when
        LSupplier<Integer> wrapped = LSupplier.wrap(sutThrowing);

        // then
        try {
            wrapped.doGet();
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
        LSupplierX<Integer,ParseException> sutThrowing = LSupplierX.lX(() -> {
            throw new ParseException(ORIGINAL_MESSAGE, 0);
        });

        // when
        LSupplier<Integer> wrapped = LSupplier.wrap(sutThrowing);

        // then
        try {
            wrapped.doGet();
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(NestedException.class)
                    .hasCauseExactlyInstanceOf(ParseException.class)
                    .hasMessage(ORIGINAL_MESSAGE);
        }
    }


    @Test
    public void testHandlingDoGetMethodWrapsTheException() throws X {

        // given
        LSupplier<Integer> sutThrowing = LSupplier.l(() -> {
            throw new UnsupportedOperationException();
        });

        // when
        LSupplier<Integer> wrapped = sutThrowing.handleSup(handler -> handler
            .wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED));

        // then
        try {
            wrapped.doGet();
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IllegalArgumentException.class)
                    .hasCauseExactlyInstanceOf(UnsupportedOperationException.class)
                    .hasMessage(EXCEPTION_WAS_WRAPPED);
        }
    }

    @Test
    public void testHandleSupMethodDoNotWrapsOtherExceptionIf() throws X {

        // given
        LSupplier<Integer> sutThrowing = LSupplier.l(() -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LSupplier<Integer> wrapped = sutThrowing.handleSup(handler -> handler
                .wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED)
                .throwIf(IndexOutOfBoundsException.class));

        // then
        try {
            wrapped.doGet();
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                    .hasNoCause();
        }
    }

@Test
    public void testHandleSupMethodDoNotWrapsOtherExceptionWhen() throws X {

        // given
        LSupplier<Integer> sutThrowing = LSupplier.l(() -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LSupplier<Integer> wrapped = sutThrowing.handleSup(handler -> handler
                .wrapWhen(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED)
                .throwIf(IndexOutOfBoundsException.class));

        // then
        try {
            wrapped.doGet();
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                    .hasNoCause();
        }
    }


    @Test
    public void testHandleSupMishandlingExceptionIsAllowed() throws X {

        // given
        LSupplier<Integer> sutThrowing = LSupplier.l(() -> {
            throw new UnsupportedOperationException(ORIGINAL_MESSAGE);
        });

        // when
        LSupplier<Integer> wrapped = sutThrowing.handleSup(h -> Function4U.doNothing());

        // then
        try {
            wrapped.doGet();
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
             .isExactlyInstanceOf(UnsupportedOperationException.class)
             .hasNoCause()
             .hasMessage(ORIGINAL_MESSAGE);
        }
    }




    // <editor-fold desc="then (functional)">

    @Test
    public void testToSup0() throws X  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);

        //given (+ some assertions)
        LSupplier<Integer> sutO = () -> {
                mainFunctionCalled.set(true);
                return 90;
        };

        LFunction<Integer,Integer> thenFunction = p -> {
                thenFunctionCalled.set(true);
                // Integer
                assertThat(p).isEqualTo(90);
                // Integer
                return 100;
        };

        //when
        LSupplier<Integer> function = sutO.toSup(thenFunction);
        Integer finalValue = function.doGet();

        //then - finals
        assertThat(finalValue).isEqualTo(100);
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    @Test
    public void testToAct1() throws X  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);

        //given (+ some assertions)
        LSupplier<Integer> sutO = () -> {
                mainFunctionCalled.set(true);
                return 90;
        };

        LConsumer<Integer> thenFunction = p -> {
                thenFunctionCalled.set(true);
                // Integer
                assertThat(p).isEqualTo(90);
        };

        //when
        LAction function = sutO.toAct(thenFunction);
        function.doExecute();

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    @Test
    public void testToByteSup2() throws X  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);

        //given (+ some assertions)
        LSupplier<Integer> sutO = () -> {
                mainFunctionCalled.set(true);
                return 90;
        };

        LToByteFunction<Integer> thenFunction = p -> {
                thenFunctionCalled.set(true);
                // Integer
                assertThat(p).isEqualTo(90);
                // byte
                return (byte)100;
        };

        //when
        LByteSupplier function = sutO.toByteSup(thenFunction);
        byte finalValue = function.doGetAsByte();

        //then - finals
        assertThat(finalValue).isEqualTo((byte)100);
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    @Test
    public void testToShortSup3() throws X  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);

        //given (+ some assertions)
        LSupplier<Integer> sutO = () -> {
                mainFunctionCalled.set(true);
                return 90;
        };

        LToShortFunction<Integer> thenFunction = p -> {
                thenFunctionCalled.set(true);
                // Integer
                assertThat(p).isEqualTo(90);
                // short
                return (short)100;
        };

        //when
        LShortSupplier function = sutO.toShortSup(thenFunction);
        short finalValue = function.doGetAsShort();

        //then - finals
        assertThat(finalValue).isEqualTo((short)100);
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    @Test
    public void testToIntSup4() throws X  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);

        //given (+ some assertions)
        LSupplier<Integer> sutO = () -> {
                mainFunctionCalled.set(true);
                return 90;
        };

        LToIntFunction<Integer> thenFunction = p -> {
                thenFunctionCalled.set(true);
                // Integer
                assertThat(p).isEqualTo(90);
                // int
                return 100;
        };

        //when
        LIntSupplier function = sutO.toIntSup(thenFunction);
        int finalValue = function.doGetAsInt();

        //then - finals
        assertThat(finalValue).isEqualTo(100);
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    @Test
    public void testToLongSup5() throws X  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);

        //given (+ some assertions)
        LSupplier<Integer> sutO = () -> {
                mainFunctionCalled.set(true);
                return 90;
        };

        LToLongFunction<Integer> thenFunction = p -> {
                thenFunctionCalled.set(true);
                // Integer
                assertThat(p).isEqualTo(90);
                // long
                return 100L;
        };

        //when
        LLongSupplier function = sutO.toLongSup(thenFunction);
        long finalValue = function.doGetAsLong();

        //then - finals
        assertThat(finalValue).isEqualTo(100L);
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    @Test
    public void testToFloatSup6() throws X  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);

        //given (+ some assertions)
        LSupplier<Integer> sutO = () -> {
                mainFunctionCalled.set(true);
                return 90;
        };

        LToFloatFunction<Integer> thenFunction = p -> {
                thenFunctionCalled.set(true);
                // Integer
                assertThat(p).isEqualTo(90);
                // float
                return 100f;
        };

        //when
        LFloatSupplier function = sutO.toFloatSup(thenFunction);
        float finalValue = function.doGetAsFloat();

        //then - finals
        assertThat(finalValue).isEqualTo(100f);
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    @Test
    public void testToDoubleSup7() throws X  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);

        //given (+ some assertions)
        LSupplier<Integer> sutO = () -> {
                mainFunctionCalled.set(true);
                return 90;
        };

        LToDoubleFunction<Integer> thenFunction = p -> {
                thenFunctionCalled.set(true);
                // Integer
                assertThat(p).isEqualTo(90);
                // double
                return 100d;
        };

        //when
        LDoubleSupplier function = sutO.toDoubleSup(thenFunction);
        double finalValue = function.doGetAsDouble();

        //then - finals
        assertThat(finalValue).isEqualTo(100d);
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    @Test
    public void testToCharSup8() throws X  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);

        //given (+ some assertions)
        LSupplier<Integer> sutO = () -> {
                mainFunctionCalled.set(true);
                return 90;
        };

        LToCharFunction<Integer> thenFunction = p -> {
                thenFunctionCalled.set(true);
                // Integer
                assertThat(p).isEqualTo(90);
                // char
                return '\u0100';
        };

        //when
        LCharSupplier function = sutO.toCharSup(thenFunction);
        char finalValue = function.doGetAsChar();

        //then - finals
        assertThat(finalValue).isEqualTo('\u0100');
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    @Test
    public void testToBoolSup9() throws X  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);

        //given (+ some assertions)
        LSupplier<Integer> sutO = () -> {
                mainFunctionCalled.set(true);
                return 90;
        };

        LPredicate<Integer> thenFunction = p -> {
                thenFunctionCalled.set(true);
                // Integer
                assertThat(p).isEqualTo(90);
                // boolean
                return true;
        };

        //when
        LBoolSupplier function = sutO.toBoolSup(thenFunction);
        boolean finalValue = function.doGetAsBool();

        //then - finals
        assertThat(finalValue).isEqualTo(true);
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    // </editor-fold>

    @Test
    public void testNesting() {
        assertThat(sut.nestingSup())
            .isSameAs(sut)
            .isInstanceOf(LSupplier.class);
    }

    @Test
    public void testShoving() {
        assertThat(sut.shovingSup())
            .isSameAs(sut)
            .isInstanceOf(LSupplier.class);
    }

    @Test
    public void testNestingX() {
        assertThat(sut.nestingSupX())
            .isSameAs(sut)
            .isInstanceOf(LSupplierX.class);
    }

    @Test
    public void testShovingX() {
        assertThat(sut.shovingSupX())
            .isSameAs(sut)
            .isInstanceOf(LSupplierX.class);
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void testShove() {

        // given
        LSupplier<Integer> sutThrowing = LSupplier.l(() -> {
            throw new UnsupportedOperationException();
        });

        // when
        sutThrowing.shovingSup().doGet();
    }

    @Test
    public void testHandleSup() throws X {

        // given
        LSupplier<Integer> sutThrowing = LSupplier.l(() -> {
            throw new UnsupportedOperationException();
        });

        // when
        LSupplier<Integer> wrapped = sutThrowing.handleSup(h -> {
            h.wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED);
        });

        // then
        try {
            wrapped.doGet();
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
                .contains("LSupplier: T doGet()");
    }


    @Test
    public void isThrowing() {
        assertThat(sut.isThrowing())
            .isFalse();
    }

    @Test void safeCompiles() {
        LSupplier r1 = LSupplier.safe(sut); //NOSONAR
        LSupplierX r2 = LSupplier.safe(sut); //NOSONAR
        Supplier r3 = LSupplier.safe(sut); //NOSONAR
    }

    @Test void safePropagates() {
        Object result = LSupplier.safe(sut);
        assertThat(result).isSameAs(sut);
    }

    @Test void safeProtectsAgainstNpe() {
        Object result = LSupplier.safe(null);
        assertThat(result).isSameAs(LSupplier.l(LSupplier.safe()));
    }

    @Test  void safeSupplierPropagates() {
        LSupplier<LSupplier<Integer>> supplier = ()->sut;
        Object result = LSupplier.safeSupplier(supplier);
        assertThat(result).isSameAs(supplier);
    }

    @Test  void safeSupplierProtectsAgainstNpe() {
        Object result = LSupplier.safeSupplier(null);
        assertThat(result).isSameAs(LSupplier.safeSupplier());
    }

    @Test  void safeSupplierCompiles() {
        LSupplier<LSupplier<Integer>> r1 = LSupplier.safeSupplier(()->sut);  //NOSONAR
        Supplier<LSupplier<Integer>> r2 = LSupplier.safeSupplier(()->sut); //NOSONAR
    }

}
