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
public class LLongSupplierXTest<X extends ParseException> {
    private static final String ORIGINAL_MESSAGE = "Original message";
    private static final String EXCEPTION_WAS_WRAPPED = "Exception was wrapped.";
    private static final String NO_EXCEPTION_WERE_THROWN = "No exception were thrown.";

    private long testValue = 100L;



    private LLongSupplierX<X> sut = new LLongSupplierX<X>(){
        public  long doGetAsLong()  throws X {
            return testValue;
        }
    };

    private LLongSupplier opposite = new LLongSupplier(){
        public  long doGetAsLong()  {
            return testValue;
        }
    };


    private LongSupplier jre = () -> testValue;


    private LLongSupplierX<ParseException> sutAlwaysThrowing = LLongSupplierX.lX(() -> {
            throw new ParseException(ORIGINAL_MESSAGE, 0);
    });

    private LLongSupplierX<RuntimeException> sutAlwaysThrowingUnchecked = LLongSupplierX.lX(() -> {
            throw new IndexOutOfBoundsException(ORIGINAL_MESSAGE);
    });


    @Test
    public void testTheResult() throws X {
        assertThat(sut.doGetAsLong())
            .isEqualTo(testValue);
    }

    @Test
    public void testTupleCall() throws X {

        LTuple.Void domainObject = Tuple4U.tuple();

        Object result = sut.tupleGetAsLong(domainObject);

        assertThat(result)
            .isEqualTo(testValue);
    }

    @Test
    public void testNonNullDoGetAsLong() throws X {
        assertThat(sut.nonNullDoGetAsLong())
            .isEqualTo(testValue);
    }

    @Test
    public void testNestingDoGetAsLongChecked() throws X {

        // then
        try {
            sutAlwaysThrowing.nestingDoGetAsLong();
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(NestedException.class)
                    .hasCauseExactlyInstanceOf(ParseException.class)
                    .hasMessage(ORIGINAL_MESSAGE);
        }
    }

    @Test
    public void testNestingDoGetAsLongUnchecked() throws X {

        // then
        try {
            sutAlwaysThrowingUnchecked.nestingDoGetAsLong();
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                    .hasNoCause()
                    .hasMessage(ORIGINAL_MESSAGE);
        }
    }

    @Test
    public void testShovingDoGetAsLongChecked() throws X {

        // then
        try {
            sutAlwaysThrowing.shovingDoGetAsLong();
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(ParseException.class)
                    .hasNoCause()
                    .hasMessage(ORIGINAL_MESSAGE);
        }
    }

    @Test
    public void testShovingDoGetAsLongUnchecked() throws X {

        // then
        try {
            sutAlwaysThrowingUnchecked.shovingDoGetAsLong();
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
            .isEqualTo("LLongSupplierX: long doGetAsLong() throws X");
    }

    @Test
    public void testLXMethod() throws X {
        assertThat(LLongSupplierX.lX(() -> testValue ))
            .isInstanceOf(LLongSupplierX.class);
    }

    @Test
    public void testWrapXMethod() throws X {
        assertThat(LLongSupplierX.wrapX(opposite))
            .isInstanceOf(LLongSupplierX.class);
    }

    @Test
    public void testWrapStdMethod() throws X {
        assertThat(LLongSupplierX.wrap(jre))
            .isInstanceOf(LLongSupplierX.class);
    }


    @Test
    public void testHandlingDoGetAsLongMethodWrapsTheException() throws X {

        // given
        LLongSupplierX<X> sutThrowing = LLongSupplierX.lX(() -> {
            throw new UnsupportedOperationException();
        });

        // when
        LLongSupplierX<RuntimeException> wrapped = sutThrowing.handleLongSupX(handler -> handler
            .wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED));

        // then
        try {
            wrapped.doGetAsLong();
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IllegalArgumentException.class)
                    .hasCauseExactlyInstanceOf(UnsupportedOperationException.class)
                    .hasMessage(EXCEPTION_WAS_WRAPPED);
        }
    }

    @Test
    public void testHandleLongSupXMethodDoNotWrapsOtherExceptionIf() throws X {

        // given
        LLongSupplierX<X> sutThrowing = LLongSupplierX.lX(() -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LLongSupplierX<X> wrapped = sutThrowing.handleLongSupX(handler -> handler
                .wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED)
                .throwIf(IndexOutOfBoundsException.class));

        // then
        try {
            wrapped.doGetAsLong();
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                    .hasNoCause();
        }
    }

@Test
    public void testHandleLongSupXMethodDoNotWrapsOtherExceptionWhen() throws X {

        // given
        LLongSupplierX<X> sutThrowing = LLongSupplierX.lX(() -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LLongSupplierX<X> wrapped = sutThrowing.handleLongSupX(handler -> handler
                .wrapWhen(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED)
                .throwIf(IndexOutOfBoundsException.class));

        // then
        try {
            wrapped.doGetAsLong();
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                    .hasNoCause();
        }
    }


    @Test
    public void testHandleLongSupXMishandlingExceptionIsAllowed() throws X {

        // given
        LLongSupplierX<X> sutThrowing = LLongSupplierX.lX(() -> {
            throw (X) new ParseException(ORIGINAL_MESSAGE, 0);
        });

        // when
        LLongSupplierX<X> wrapped = sutThrowing.handleLongSupX(h -> Function4U.doNothing());

        // then
        try {
            wrapped.doGetAsLong();
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(NestedException.class)
                    .hasCauseExactlyInstanceOf(ParseException.class)
                    .hasMessage(ORIGINAL_MESSAGE);
        }
    }




    // <editor-fold desc="then (functional)">

    @Test
    public void testToSup0() throws X  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);

        //given (+ some assertions)
        LLongSupplierX<X> sutO = () -> {
                mainFunctionCalled.set(true);
                return 90L;
        };

        LLongFunctionX<Integer,X> thenFunction = p -> {
                thenFunctionCalled.set(true);
                // long
                assertThat(p).isEqualTo(90L);
                // Integer
                return 100;
        };

        //when
        LSupplierX<Integer,X> function = sutO.toSup(thenFunction);
        Integer finalValue = function.doGet();

        //then - finals
        assertThat(finalValue).isEqualTo(100);
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    @Test
    public void testToByteSup1() throws X  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);

        //given (+ some assertions)
        LLongSupplierX<X> sutO = () -> {
                mainFunctionCalled.set(true);
                return 90L;
        };

        LLongToByteFunctionX<X> thenFunction = p -> {
                thenFunctionCalled.set(true);
                // long
                assertThat(p).isEqualTo(90L);
                // byte
                return (byte)100;
        };

        //when
        LByteSupplierX<X> function = sutO.toByteSup(thenFunction);
        byte finalValue = function.doGetAsByte();

        //then - finals
        assertThat(finalValue).isEqualTo((byte)100);
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    @Test
    public void testToShortSup2() throws X  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);

        //given (+ some assertions)
        LLongSupplierX<X> sutO = () -> {
                mainFunctionCalled.set(true);
                return 90L;
        };

        LLongToShortFunctionX<X> thenFunction = p -> {
                thenFunctionCalled.set(true);
                // long
                assertThat(p).isEqualTo(90L);
                // short
                return (short)100;
        };

        //when
        LShortSupplierX<X> function = sutO.toShortSup(thenFunction);
        short finalValue = function.doGetAsShort();

        //then - finals
        assertThat(finalValue).isEqualTo((short)100);
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    @Test
    public void testToIntSup3() throws X  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);

        //given (+ some assertions)
        LLongSupplierX<X> sutO = () -> {
                mainFunctionCalled.set(true);
                return 90L;
        };

        LLongToIntFunctionX<X> thenFunction = p -> {
                thenFunctionCalled.set(true);
                // long
                assertThat(p).isEqualTo(90L);
                // int
                return 100;
        };

        //when
        LIntSupplierX<X> function = sutO.toIntSup(thenFunction);
        int finalValue = function.doGetAsInt();

        //then - finals
        assertThat(finalValue).isEqualTo(100);
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    @Test
    public void testToLongSup4() throws X  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);

        //given (+ some assertions)
        LLongSupplierX<X> sutO = () -> {
                mainFunctionCalled.set(true);
                return 90L;
        };

        LLongUnaryOperatorX<X> thenFunction = p -> {
                thenFunctionCalled.set(true);
                // long
                assertThat(p).isEqualTo(90L);
                // long
                return 100L;
        };

        //when
        LLongSupplierX<X> function = sutO.toLongSup(thenFunction);
        long finalValue = function.doGetAsLong();

        //then - finals
        assertThat(finalValue).isEqualTo(100L);
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    @Test
    public void testToFloatSup5() throws X  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);

        //given (+ some assertions)
        LLongSupplierX<X> sutO = () -> {
                mainFunctionCalled.set(true);
                return 90L;
        };

        LLongToFloatFunctionX<X> thenFunction = p -> {
                thenFunctionCalled.set(true);
                // long
                assertThat(p).isEqualTo(90L);
                // float
                return 100f;
        };

        //when
        LFloatSupplierX<X> function = sutO.toFloatSup(thenFunction);
        float finalValue = function.doGetAsFloat();

        //then - finals
        assertThat(finalValue).isEqualTo(100f);
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    @Test
    public void testToDoubleSup6() throws X  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);

        //given (+ some assertions)
        LLongSupplierX<X> sutO = () -> {
                mainFunctionCalled.set(true);
                return 90L;
        };

        LLongToDoubleFunctionX<X> thenFunction = p -> {
                thenFunctionCalled.set(true);
                // long
                assertThat(p).isEqualTo(90L);
                // double
                return 100d;
        };

        //when
        LDoubleSupplierX<X> function = sutO.toDoubleSup(thenFunction);
        double finalValue = function.doGetAsDouble();

        //then - finals
        assertThat(finalValue).isEqualTo(100d);
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    @Test
    public void testToCharSup7() throws X  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);

        //given (+ some assertions)
        LLongSupplierX<X> sutO = () -> {
                mainFunctionCalled.set(true);
                return 90L;
        };

        LLongToCharFunctionX<X> thenFunction = p -> {
                thenFunctionCalled.set(true);
                // long
                assertThat(p).isEqualTo(90L);
                // char
                return '\u0100';
        };

        //when
        LCharSupplierX<X> function = sutO.toCharSup(thenFunction);
        char finalValue = function.doGetAsChar();

        //then - finals
        assertThat(finalValue).isEqualTo('\u0100');
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    @Test
    public void testToBoolSup8() throws X  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);

        //given (+ some assertions)
        LLongSupplierX<X> sutO = () -> {
                mainFunctionCalled.set(true);
                return 90L;
        };

        LLongPredicateX<X> thenFunction = p -> {
                thenFunctionCalled.set(true);
                // long
                assertThat(p).isEqualTo(90L);
                // boolean
                return true;
        };

        //when
        LBoolSupplierX<X> function = sutO.toBoolSup(thenFunction);
        boolean finalValue = function.doGetAsBool();

        //then - finals
        assertThat(finalValue).isEqualTo(true);
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    // </editor-fold>

    @Test
    public void testNesting() {
        assertThat(sut.nestingLongSup())
            .isInstanceOf(LLongSupplier.class);
    }

    @Test
    public void testShoving() {
        assertThat(sut.shovingLongSup())
            .isInstanceOf(LLongSupplier.class);
    }

    @Test
    public void testNestingX() {
        assertThat(sut.nestingLongSupX())
            .isInstanceOf(LLongSupplierX.class);
    }

    @Test
    public void testShovingX() {
        assertThat(sut.shovingLongSupX())
            .isInstanceOf(LLongSupplierX.class);
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void testShove() {

        // given
        LLongSupplierX<X> sutThrowing = LLongSupplierX.lX(() -> {
            throw new UnsupportedOperationException();
        });

        // when
        sutThrowing.shovingLongSup().doGetAsLong();
    }

    @Test
    public void testHandleLongSup() throws X {

        // given
        LLongSupplierX<X> sutThrowing = LLongSupplierX.lX(() -> {
            throw new UnsupportedOperationException();
        });

        // when
        LLongSupplierX<X> wrapped = sutThrowing.handleLongSupX(h -> {
            h.wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED);
        });

        // then
        try {
            wrapped.doGetAsLong();
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
                .contains("LLongSupplierX: long doGetAsLong() throws X");
    }


    @Test
    public void isThrowing() {
        assertThat(sut.isThrowing())
            .isTrue();
    }

    @Test void safeCompiles() {
        LLongSupplierX r1 = LLongSupplierX.safe(sut); //NOSONAR
        LongSupplier r3 = LLongSupplierX.safe(sut); //NOSONAR
    }

    @Test void safePropagates() {
        Object result = LLongSupplierX.safe(sut);
        assertThat(result).isSameAs(sut);
    }

    @Test void safeProtectsAgainstNpe() {
        Object result = LLongSupplierX.safe(null);
        assertThat(result).isSameAs(LLongSupplierX.lX(LLongSupplierX.safe()));
    }

    @Test <Y extends Throwable> void safeSupplierPropagates() {
        LSupplierX<LLongSupplierX<X>,Y> supplier = ()->sut;
        Object result = LLongSupplierX.safeSupplier(supplier);
        assertThat(result).isSameAs(supplier);
    }

    @Test <Y extends Throwable> void safeSupplierProtectsAgainstNpe() {
        Object result = LLongSupplierX.safeSupplier(null);
        assertThat(result).isSameAs(LLongSupplierX.safeSupplier());
    }

    @Test <Y extends Throwable> void safeSupplierCompiles() {
        LSupplierX<LLongSupplierX<X>,Y> r1 = LLongSupplierX.safeSupplier(()->sut);  //NOSONAR
        Supplier<LLongSupplierX<X>> r2 = LLongSupplierX.safeSupplier(()->sut); //NOSONAR
    }

}
