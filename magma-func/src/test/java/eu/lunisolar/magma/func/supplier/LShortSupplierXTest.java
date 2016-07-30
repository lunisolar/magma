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
public class LShortSupplierXTest<X extends ParseException> {
    private static final String ORIGINAL_MESSAGE = "Original message";
    private static final String EXCEPTION_WAS_WRAPPED = "Exception was wrapped.";
    private static final String NO_EXCEPTION_WERE_THROWN = "No exception were thrown.";

    private short testValue = (short)100;



    private LShortSupplierX<X> sut = new LShortSupplierX<X>(){
        public  short doGetAsShort()  throws X {
            return testValue;
        }
    };

    private LShortSupplier opposite = new LShortSupplier(){
        public  short doGetAsShort()  {
            return testValue;
        }
    };



    private LShortSupplierX<ParseException> sutAlwaysThrowing = LShortSupplierX.lX(() -> {
            throw new ParseException(ORIGINAL_MESSAGE, 0);
    });

    private LShortSupplierX<RuntimeException> sutAlwaysThrowingUnchecked = LShortSupplierX.lX(() -> {
            throw new IndexOutOfBoundsException(ORIGINAL_MESSAGE);
    });


    @Test
    public void testTheResult() throws X {
        assertThat(sut.doGetAsShort())
            .isEqualTo(testValue);
    }

    @Test
    public void testTupleCall() throws X {

        LTuple.Void domainObject = Tuple4U.tuple();

        Object result = sut.tupleGetAsShort(domainObject);

        assertThat(result)
            .isEqualTo(testValue);
    }

    @Test
    public void testNonNullDoGetAsShort() throws X {
        assertThat(sut.nonNullDoGetAsShort())
            .isEqualTo(testValue);
    }

    @Test
    public void testNestingDoGetAsShortChecked() throws X {

        // then
        try {
            sutAlwaysThrowing.nestingDoGetAsShort();
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(NestedException.class)
                    .hasCauseExactlyInstanceOf(ParseException.class)
                    .hasMessage(ORIGINAL_MESSAGE);
        }
    }

    @Test
    public void testNestingDoGetAsShortUnchecked() throws X {

        // then
        try {
            sutAlwaysThrowingUnchecked.nestingDoGetAsShort();
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                    .hasNoCause()
                    .hasMessage(ORIGINAL_MESSAGE);
        }
    }

    @Test
    public void testShovingDoGetAsShortChecked() throws X {

        // then
        try {
            sutAlwaysThrowing.shovingDoGetAsShort();
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(ParseException.class)
                    .hasNoCause()
                    .hasMessage(ORIGINAL_MESSAGE);
        }
    }

    @Test
    public void testShovingDoGetAsShortUnchecked() throws X {

        // then
        try {
            sutAlwaysThrowingUnchecked.shovingDoGetAsShort();
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
            .isEqualTo("LShortSupplierX: short doGetAsShort() throws X");
    }

    @Test
    public void testLXMethod() throws X {
        assertThat(LShortSupplierX.lX(() -> testValue ))
            .isInstanceOf(LShortSupplierX.class);
    }

    @Test
    public void testWrapXMethod() throws X {
        assertThat(LShortSupplierX.wrapX(opposite))
            .isInstanceOf(LShortSupplierX.class);
    }


    @Test
    public void testHandlingDoGetAsShortMethodWrapsTheException() throws X {

        // given
        LShortSupplierX<X> sutThrowing = LShortSupplierX.lX(() -> {
            throw new UnsupportedOperationException();
        });

        // when
        LShortSupplierX<RuntimeException> wrapped = sutThrowing.handleShortSupX(handler -> handler
            .wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED));

        // then
        try {
            wrapped.doGetAsShort();
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IllegalArgumentException.class)
                    .hasCauseExactlyInstanceOf(UnsupportedOperationException.class)
                    .hasMessage(EXCEPTION_WAS_WRAPPED);
        }
    }

    @Test
    public void testHandleShortSupXMethodDoNotWrapsOtherExceptionIf() throws X {

        // given
        LShortSupplierX<X> sutThrowing = LShortSupplierX.lX(() -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LShortSupplierX<X> wrapped = sutThrowing.handleShortSupX(handler -> handler
                .wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED)
                .throwIf(IndexOutOfBoundsException.class));

        // then
        try {
            wrapped.doGetAsShort();
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                    .hasNoCause();
        }
    }

@Test
    public void testHandleShortSupXMethodDoNotWrapsOtherExceptionWhen() throws X {

        // given
        LShortSupplierX<X> sutThrowing = LShortSupplierX.lX(() -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LShortSupplierX<X> wrapped = sutThrowing.handleShortSupX(handler -> handler
                .wrapWhen(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED)
                .throwIf(IndexOutOfBoundsException.class));

        // then
        try {
            wrapped.doGetAsShort();
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                    .hasNoCause();
        }
    }


    @Test
    public void testHandleShortSupXMishandlingExceptionIsAllowed() throws X {

        // given
        LShortSupplierX<X> sutThrowing = LShortSupplierX.lX(() -> {
            throw (X) new ParseException(ORIGINAL_MESSAGE, 0);
        });

        // when
        LShortSupplierX<X> wrapped = sutThrowing.handleShortSupX(h -> Function4U.doNothing());

        // then
        try {
            wrapped.doGetAsShort();
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
        LShortSupplierX<X> sutO = () -> {
                mainFunctionCalled.set(true);
                return (short)90;
        };

        LShortFunctionX<Integer,X> thenFunction = p -> {
                thenFunctionCalled.set(true);
                // short
                assertThat(p).isEqualTo((short)90);
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
        LShortSupplierX<X> sutO = () -> {
                mainFunctionCalled.set(true);
                return (short)90;
        };

        LShortToByteFunctionX<X> thenFunction = p -> {
                thenFunctionCalled.set(true);
                // short
                assertThat(p).isEqualTo((short)90);
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
        LShortSupplierX<X> sutO = () -> {
                mainFunctionCalled.set(true);
                return (short)90;
        };

        LShortUnaryOperatorX<X> thenFunction = p -> {
                thenFunctionCalled.set(true);
                // short
                assertThat(p).isEqualTo((short)90);
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
        LShortSupplierX<X> sutO = () -> {
                mainFunctionCalled.set(true);
                return (short)90;
        };

        LShortToIntFunctionX<X> thenFunction = p -> {
                thenFunctionCalled.set(true);
                // short
                assertThat(p).isEqualTo((short)90);
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
        LShortSupplierX<X> sutO = () -> {
                mainFunctionCalled.set(true);
                return (short)90;
        };

        LShortToLongFunctionX<X> thenFunction = p -> {
                thenFunctionCalled.set(true);
                // short
                assertThat(p).isEqualTo((short)90);
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
        LShortSupplierX<X> sutO = () -> {
                mainFunctionCalled.set(true);
                return (short)90;
        };

        LShortToFloatFunctionX<X> thenFunction = p -> {
                thenFunctionCalled.set(true);
                // short
                assertThat(p).isEqualTo((short)90);
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
        LShortSupplierX<X> sutO = () -> {
                mainFunctionCalled.set(true);
                return (short)90;
        };

        LShortToDoubleFunctionX<X> thenFunction = p -> {
                thenFunctionCalled.set(true);
                // short
                assertThat(p).isEqualTo((short)90);
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
        LShortSupplierX<X> sutO = () -> {
                mainFunctionCalled.set(true);
                return (short)90;
        };

        LShortToCharFunctionX<X> thenFunction = p -> {
                thenFunctionCalled.set(true);
                // short
                assertThat(p).isEqualTo((short)90);
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
        LShortSupplierX<X> sutO = () -> {
                mainFunctionCalled.set(true);
                return (short)90;
        };

        LShortPredicateX<X> thenFunction = p -> {
                thenFunctionCalled.set(true);
                // short
                assertThat(p).isEqualTo((short)90);
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
        assertThat(sut.nestingShortSup())
            .isInstanceOf(LShortSupplier.class);
    }

    @Test
    public void testShoving() {
        assertThat(sut.shovingShortSup())
            .isInstanceOf(LShortSupplier.class);
    }

    @Test
    public void testNestingX() {
        assertThat(sut.nestingShortSupX())
            .isInstanceOf(LShortSupplierX.class);
    }

    @Test
    public void testShovingX() {
        assertThat(sut.shovingShortSupX())
            .isInstanceOf(LShortSupplierX.class);
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void testShove() {

        // given
        LShortSupplierX<X> sutThrowing = LShortSupplierX.lX(() -> {
            throw new UnsupportedOperationException();
        });

        // when
        sutThrowing.shovingShortSup().doGetAsShort();
    }

    @Test
    public void testHandleShortSup() throws X {

        // given
        LShortSupplierX<X> sutThrowing = LShortSupplierX.lX(() -> {
            throw new UnsupportedOperationException();
        });

        // when
        LShortSupplierX<X> wrapped = sutThrowing.handleShortSupX(h -> {
            h.wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED);
        });

        // then
        try {
            wrapped.doGetAsShort();
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
                .contains("LShortSupplierX: short doGetAsShort() throws X");
    }


    @Test
    public void isThrowing() {
        assertThat(sut.isThrowing())
            .isTrue();
    }

    @Test void safeCompiles() {
        LShortSupplierX r1 = LShortSupplierX.safe(sut); //NOSONAR
    }

    @Test void safePropagates() {
        Object result = LShortSupplierX.safe(sut);
        assertThat(result).isSameAs(sut);
    }

    @Test void safeProtectsAgainstNpe() {
        Object result = LShortSupplierX.safe(null);
        assertThat(result).isSameAs(LShortSupplierX.lX(LShortSupplierX.safe()));
    }

    @Test <Y extends Throwable> void safeSupplierPropagates() {
        LSupplierX<LShortSupplierX<X>,Y> supplier = ()->sut;
        Object result = LShortSupplierX.safeSupplier(supplier);
        assertThat(result).isSameAs(supplier);
    }

    @Test <Y extends Throwable> void safeSupplierProtectsAgainstNpe() {
        Object result = LShortSupplierX.safeSupplier(null);
        assertThat(result).isSameAs(LShortSupplierX.safeSupplier());
    }

    @Test <Y extends Throwable> void safeSupplierCompiles() {
        LSupplierX<LShortSupplierX<X>,Y> r1 = LShortSupplierX.safeSupplier(()->sut);  //NOSONAR
    }

}
