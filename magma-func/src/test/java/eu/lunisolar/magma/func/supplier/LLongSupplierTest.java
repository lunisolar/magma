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

package eu.lunisolar.magma.func.supplier;

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
public class LLongSupplierTest<X extends ParseException> {
    private static final String ORIGINAL_MESSAGE = "Original message";
    private static final String EXCEPTION_WAS_WRAPPED = "Exception was wrapped.";
    private static final String NO_EXCEPTION_WERE_THROWN = "No exception were thrown.";

    private long testValue = (long)100;



    private LLongSupplier sut = new LLongSupplier(){
        public  long doGetAsLong()  {
            return testValue;
        }
    };

    private LLongSupplierX<X> opposite = new LLongSupplierX(){
        public  long doGetAsLong() throws ParseException {
            return testValue;
        }
    };


    private LongSupplier jre = () -> testValue;



    private LLongSupplier sutAlwaysThrowingUnckeck = LLongSupplier.l(() -> {
            throw new IndexOutOfBoundsException(ORIGINAL_MESSAGE);
    });


    @Test
    public void testTheResult() throws X {
        assertThat(sut.doGetAsLong())
            .isEqualTo(testValue);
    }

    @Test
    public void testTupleCall() throws X {

        //FunctionalCall<LTuple.Void,Long,RuntimeException> theCall = sut;

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
    public void testNestingDoGetAsLongUnckeck() throws X {

        // then
        try {
            sutAlwaysThrowingUnckeck.nestingDoGetAsLong();
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                    .hasNoCause()
                    .hasMessage(ORIGINAL_MESSAGE);
        }
    }

    @Test
    public void testShovingDoGetAsLongUnckeck() throws X {

        // then
        try {
            sutAlwaysThrowingUnckeck.shovingDoGetAsLong();
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
            .isEqualTo("LLongSupplier: long doGetAsLong()");
    }

    @Test
    public void testLMethod() throws X {
        assertThat(LLongSupplier.l(() -> testValue ))
            .isInstanceOf(LLongSupplier.class);
    }

    @Test
    public void testWrapMethod() throws X {
        assertThat(LLongSupplier.wrap(opposite))
            .isInstanceOf(LLongSupplier.class);
    }

    @Test
    public void testWrapStdMethod() throws X {
        assertThat(LLongSupplier.wrap(jre))
            .isInstanceOf(LLongSupplier.class);
    }

    @Test
    public void testWrapMethodDoNotWrapsRuntimeException() throws X {
        // given
        LLongSupplierX<X> sutThrowing = LLongSupplierX.lX(() -> {
            throw new UnsupportedOperationException(ORIGINAL_MESSAGE);
        });

        // when
        LLongSupplier wrapped = LLongSupplier.wrap(sutThrowing);

        // then
        try {
            wrapped.doGetAsLong();
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
        LLongSupplierX<ParseException> sutThrowing = LLongSupplierX.lX(() -> {
            throw new ParseException(ORIGINAL_MESSAGE, 0);
        });

        // when
        LLongSupplier wrapped = LLongSupplier.wrap(sutThrowing);

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


    @Test
    public void testWrapExceptionMethodWrapsTheException() throws X {

        // given
        LLongSupplier sutThrowing = LLongSupplier.l(() -> {
            throw new UnsupportedOperationException();
        });

        // when
        LLongSupplier wrapped = sutThrowing.handleLongSup(handler -> handler
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
    public void testWrapExceptionMethodDoNotWrapsOtherExceptionIf() throws X {

        // given
        LLongSupplier sutThrowing = LLongSupplier.l(() -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LLongSupplier wrapped = sutThrowing.handleLongSup(handler -> handler
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
    public void testWrapExceptionMethodDoNotWrapsOtherExceptionWhen() throws X {

        // given
        LLongSupplier sutThrowing = LLongSupplier.l(() -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LLongSupplier wrapped = sutThrowing.handleLongSup(handler -> handler
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
    public void testWrapExceptionMishandlingExceptionIsAllowed() throws X {

        // given
        LLongSupplier sutThrowing = LLongSupplier.l(() -> {
            throw new UnsupportedOperationException(ORIGINAL_MESSAGE);
        });

        // when
        LLongSupplier wrapped = sutThrowing.handleLongSup(h -> Function4U.doNothing());

        // then
        try {
            wrapped.doGetAsLong();
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
    public void testThen0() throws X  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);


        //given (+ some assertions)
        LLongSupplier sutO = () -> {
                mainFunctionCalled.set(true);
                return (long)90;
        };

        LLongFunction<Integer > thenFunction = p -> {
                thenFunctionCalled.set(true);
                // 
                assertThat(p).isEqualTo((long)90);
                // V
                return Integer.valueOf(100);
        };

        //when
        LSupplier<Integer > function = sutO.toSupplier(thenFunction);
        Integer  finalValue = function.doGet();

        //then - finals
        assertThat(finalValue).isEqualTo(Integer.valueOf(100));
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    @Test
    public void testThen1ToByte() throws X  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);


        //given (+ some assertions)
        LLongSupplier sutO = () -> {
                mainFunctionCalled.set(true);
                return (long)90;
        };

        LLongToByteFunction thenFunction = p -> {
                thenFunctionCalled.set(true);
                // 
                assertThat(p).isEqualTo((long)90);
                // byte
                return (byte)100;
        };

        //when
        LByteSupplier function = sutO.toByteSupplier(thenFunction);
        byte finalValue = function.doGetAsByte();

        //then - finals
        assertThat(finalValue).isEqualTo((byte)100);
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    @Test
    public void testThen2ToShort() throws X  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);


        //given (+ some assertions)
        LLongSupplier sutO = () -> {
                mainFunctionCalled.set(true);
                return (long)90;
        };

        LLongToShortFunction thenFunction = p -> {
                thenFunctionCalled.set(true);
                // 
                assertThat(p).isEqualTo((long)90);
                // short
                return (short)100;
        };

        //when
        LShortSupplier function = sutO.toShortSupplier(thenFunction);
        short finalValue = function.doGetAsShort();

        //then - finals
        assertThat(finalValue).isEqualTo((short)100);
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    @Test
    public void testThen3ToInt() throws X  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);


        //given (+ some assertions)
        LLongSupplier sutO = () -> {
                mainFunctionCalled.set(true);
                return (long)90;
        };

        LLongToIntFunction thenFunction = p -> {
                thenFunctionCalled.set(true);
                // 
                assertThat(p).isEqualTo((long)90);
                // int
                return (int)100;
        };

        //when
        LIntSupplier function = sutO.toIntSupplier(thenFunction);
        int finalValue = function.doGetAsInt();

        //then - finals
        assertThat(finalValue).isEqualTo((int)100);
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    @Test
    public void testThen4ToLong() throws X  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);


        //given (+ some assertions)
        LLongSupplier sutO = () -> {
                mainFunctionCalled.set(true);
                return (long)90;
        };

        LLongUnaryOperator thenFunction = p -> {
                thenFunctionCalled.set(true);
                // 
                assertThat(p).isEqualTo((long)90);
                // long
                return (long)100;
        };

        //when
        LLongSupplier function = sutO.toLongSupplier(thenFunction);
        long finalValue = function.doGetAsLong();

        //then - finals
        assertThat(finalValue).isEqualTo((long)100);
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    @Test
    public void testThen5ToFloat() throws X  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);


        //given (+ some assertions)
        LLongSupplier sutO = () -> {
                mainFunctionCalled.set(true);
                return (long)90;
        };

        LLongToFloatFunction thenFunction = p -> {
                thenFunctionCalled.set(true);
                // 
                assertThat(p).isEqualTo((long)90);
                // float
                return (float)100;
        };

        //when
        LFloatSupplier function = sutO.toFloatSupplier(thenFunction);
        float finalValue = function.doGetAsFloat();

        //then - finals
        assertThat(finalValue).isEqualTo((float)100);
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    @Test
    public void testThen6ToDouble() throws X  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);


        //given (+ some assertions)
        LLongSupplier sutO = () -> {
                mainFunctionCalled.set(true);
                return (long)90;
        };

        LLongToDoubleFunction thenFunction = p -> {
                thenFunctionCalled.set(true);
                // 
                assertThat(p).isEqualTo((long)90);
                // double
                return (double)100;
        };

        //when
        LDoubleSupplier function = sutO.toDoubleSupplier(thenFunction);
        double finalValue = function.doGetAsDouble();

        //then - finals
        assertThat(finalValue).isEqualTo((double)100);
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    @Test
    public void testThen7ToChar() throws X  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);


        //given (+ some assertions)
        LLongSupplier sutO = () -> {
                mainFunctionCalled.set(true);
                return (long)90;
        };

        LLongToCharFunction thenFunction = p -> {
                thenFunctionCalled.set(true);
                // 
                assertThat(p).isEqualTo((long)90);
                // char
                return (char)100;
        };

        //when
        LCharSupplier function = sutO.toCharSupplier(thenFunction);
        char finalValue = function.doGetAsChar();

        //then - finals
        assertThat(finalValue).isEqualTo((char)100);
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    @Test
    public void testThen8ToBool() throws X  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);


        //given (+ some assertions)
        LLongSupplier sutO = () -> {
                mainFunctionCalled.set(true);
                return (long)90;
        };

        LLongPredicate thenFunction = p -> {
                thenFunctionCalled.set(true);
                // 
                assertThat(p).isEqualTo((long)90);
                // boolean
                return true;
        };

        //when
        LBoolSupplier function = sutO.toBoolSupplier(thenFunction);
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
            .isSameAs(sut)
            .isInstanceOf(LLongSupplier.class);
    }

    @Test
    public void testShoving() {
        assertThat(sut.shovingLongSup())
            .isSameAs(sut)
            .isInstanceOf(LLongSupplier.class);
    }

    @Test
    public void testNestingX() {
        assertThat(sut.nestingLongSupX())
            .isSameAs(sut)
            .isInstanceOf(LLongSupplierX.class);
    }

    @Test
    public void testShovingX() {
        assertThat(sut.shovingLongSupX())
            .isSameAs(sut)
            .isInstanceOf(LLongSupplierX.class);
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void testShove() {

        // given
        LLongSupplier sutThrowing = LLongSupplier.l(() -> {
            throw new UnsupportedOperationException();
        });

        // when
        sutThrowing.shovingLongSup().doGetAsLong();
    }

    @Test
    public void testHandleLongSup() throws X {

        // given
        LLongSupplier sutThrowing = LLongSupplier.l(() -> {
            throw new UnsupportedOperationException();
        });

        // when
        LLongSupplier wrapped = sutThrowing.handleLongSup(h -> {
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
                .contains("LLongSupplier: long doGetAsLong()");
    }


    @Test
    public void isThrowing() {
        assertThat(sut.isThrowing())
            .isFalse();
    }

    @Test void safeCompiles() {
        LLongSupplier r1 = LLongSupplier.safe(sut);
        LLongSupplierX r2 = LLongSupplier.safe(sut);
        LongSupplier r3 = LLongSupplier.safe(sut);
    }

    @Test void safePropagates() {
        Object result = LLongSupplier.safe(sut);
        assertThat(result).isSameAs(sut);
    }

    @Test void safeProtectsAgainstNpe() {
        Object result = LLongSupplier.safe(null);
        assertThat(result).isSameAs(LLongSupplier.l(LLongSupplier.safe()));
    }

    @Test  void safeSupplierPropagates() {
        LSupplier<LLongSupplier> supplier = ()->sut;
        Object result = LLongSupplier.safeSupplier(supplier);
        assertThat(result).isSameAs(supplier);
    }

    @Test  void safeSupplierProtectsAgainstNpe() {
        Object result = LLongSupplier.safeSupplier(null);
        assertThat(result).isSameAs(LLongSupplier.safeSupplier());
    }

    @Test  void safeSupplierCompiles() {
        LSupplier<LLongSupplier> r1 = LLongSupplier.safeSupplier(()->sut);
        Supplier<LLongSupplier> r2 = LLongSupplier.safeSupplier(()->sut);
    }

}
