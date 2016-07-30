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
public class LBoolSupplierTest<X extends ParseException> {
    private static final String ORIGINAL_MESSAGE = "Original message";
    private static final String EXCEPTION_WAS_WRAPPED = "Exception was wrapped.";
    private static final String NO_EXCEPTION_WERE_THROWN = "No exception were thrown.";

    private boolean testValue = true;



    private LBoolSupplier sut = new LBoolSupplier(){
        public  boolean doGetAsBool()  {
            return testValue;
        }
    };

    private LBoolSupplierX<X> opposite = new LBoolSupplierX<X>(){
        public  boolean doGetAsBool()  throws X {
            return testValue;
        }
    };


    private BooleanSupplier jre = () -> testValue;



    private LBoolSupplierX<RuntimeException> sutAlwaysThrowingUnchecked = LBoolSupplier.l(() -> {
            throw new IndexOutOfBoundsException(ORIGINAL_MESSAGE);
    });


    @Test
    public void testTheResult() throws X {
        assertThat(sut.doGetAsBool())
            .isEqualTo(testValue);
    }

    @Test
    public void testTupleCall() throws X {

        LTuple.Void domainObject = Tuple4U.tuple();

        Object result = sut.tupleGetAsBool(domainObject);

        assertThat(result)
            .isEqualTo(testValue);
    }

    @Test
    public void testNonNullDoGetAsBool() throws X {
        assertThat(sut.nonNullDoGetAsBool())
            .isEqualTo(testValue);
    }

    @Test
    public void testNestingDoGetAsBoolUnchecked() throws X {

        // then
        try {
            sutAlwaysThrowingUnchecked.nestingDoGetAsBool();
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                    .hasNoCause()
                    .hasMessage(ORIGINAL_MESSAGE);
        }
    }

    @Test
    public void testShovingDoGetAsBoolUnchecked() throws X {

        // then
        try {
            sutAlwaysThrowingUnchecked.shovingDoGetAsBool();
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
            .isEqualTo("LBoolSupplier: boolean doGetAsBool()");
    }

    @Test
    public void testLMethod() throws X {
        assertThat(LBoolSupplier.l(() -> testValue ))
            .isInstanceOf(LBoolSupplier.class);
    }

    @Test
    public void testWrapMethod() throws X {
        assertThat(LBoolSupplier.wrap(opposite))
            .isInstanceOf(LBoolSupplier.class);
    }

    @Test
    public void testWrapStdMethod() throws X {
        assertThat(LBoolSupplier.wrap(jre))
            .isInstanceOf(LBoolSupplier.class);
    }

    @Test
    public void testWrapMethodDoNotWrapsRuntimeException() throws X {
        // given
        LBoolSupplierX<X> sutThrowing = LBoolSupplierX.lX(() -> {
            throw new UnsupportedOperationException(ORIGINAL_MESSAGE);
        });

        // when
        LBoolSupplier wrapped = LBoolSupplier.wrap(sutThrowing);

        // then
        try {
            wrapped.doGetAsBool();
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
        LBoolSupplierX<ParseException> sutThrowing = LBoolSupplierX.lX(() -> {
            throw new ParseException(ORIGINAL_MESSAGE, 0);
        });

        // when
        LBoolSupplier wrapped = LBoolSupplier.wrap(sutThrowing);

        // then
        try {
            wrapped.doGetAsBool();
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(NestedException.class)
                    .hasCauseExactlyInstanceOf(ParseException.class)
                    .hasMessage(ORIGINAL_MESSAGE);
        }
    }


    @Test
    public void testHandlingDoGetAsBoolMethodWrapsTheException() throws X {

        // given
        LBoolSupplier sutThrowing = LBoolSupplier.l(() -> {
            throw new UnsupportedOperationException();
        });

        // when
        LBoolSupplier wrapped = sutThrowing.handleBoolSup(handler -> handler
            .wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED));

        // then
        try {
            wrapped.doGetAsBool();
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IllegalArgumentException.class)
                    .hasCauseExactlyInstanceOf(UnsupportedOperationException.class)
                    .hasMessage(EXCEPTION_WAS_WRAPPED);
        }
    }

    @Test
    public void testHandleBoolSupMethodDoNotWrapsOtherExceptionIf() throws X {

        // given
        LBoolSupplier sutThrowing = LBoolSupplier.l(() -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LBoolSupplier wrapped = sutThrowing.handleBoolSup(handler -> handler
                .wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED)
                .throwIf(IndexOutOfBoundsException.class));

        // then
        try {
            wrapped.doGetAsBool();
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                    .hasNoCause();
        }
    }

@Test
    public void testHandleBoolSupMethodDoNotWrapsOtherExceptionWhen() throws X {

        // given
        LBoolSupplier sutThrowing = LBoolSupplier.l(() -> {
            throw new IndexOutOfBoundsException();
        });

        // when
        LBoolSupplier wrapped = sutThrowing.handleBoolSup(handler -> handler
                .wrapWhen(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED)
                .throwIf(IndexOutOfBoundsException.class));

        // then
        try {
            wrapped.doGetAsBool();
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                    .hasNoCause();
        }
    }


    @Test
    public void testHandleBoolSupMishandlingExceptionIsAllowed() throws X {

        // given
        LBoolSupplier sutThrowing = LBoolSupplier.l(() -> {
            throw new UnsupportedOperationException(ORIGINAL_MESSAGE);
        });

        // when
        LBoolSupplier wrapped = sutThrowing.handleBoolSup(h -> Function4U.doNothing());

        // then
        try {
            wrapped.doGetAsBool();
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
        LBoolSupplier sutO = () -> {
                mainFunctionCalled.set(true);
                return true;
        };

        LBoolFunction<Integer> thenFunction = p -> {
                thenFunctionCalled.set(true);
                // boolean
                assertThat(p).isEqualTo(true);
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
    public void testToByteSup1() throws X  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);

        //given (+ some assertions)
        LBoolSupplier sutO = () -> {
                mainFunctionCalled.set(true);
                return true;
        };

        LBoolToByteFunction thenFunction = p -> {
                thenFunctionCalled.set(true);
                // boolean
                assertThat(p).isEqualTo(true);
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
    public void testToShortSup2() throws X  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);

        //given (+ some assertions)
        LBoolSupplier sutO = () -> {
                mainFunctionCalled.set(true);
                return true;
        };

        LBoolToShortFunction thenFunction = p -> {
                thenFunctionCalled.set(true);
                // boolean
                assertThat(p).isEqualTo(true);
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
    public void testToIntSup3() throws X  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);

        //given (+ some assertions)
        LBoolSupplier sutO = () -> {
                mainFunctionCalled.set(true);
                return true;
        };

        LBoolToIntFunction thenFunction = p -> {
                thenFunctionCalled.set(true);
                // boolean
                assertThat(p).isEqualTo(true);
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
    public void testToLongSup4() throws X  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);

        //given (+ some assertions)
        LBoolSupplier sutO = () -> {
                mainFunctionCalled.set(true);
                return true;
        };

        LBoolToLongFunction thenFunction = p -> {
                thenFunctionCalled.set(true);
                // boolean
                assertThat(p).isEqualTo(true);
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
    public void testToFloatSup5() throws X  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);

        //given (+ some assertions)
        LBoolSupplier sutO = () -> {
                mainFunctionCalled.set(true);
                return true;
        };

        LBoolToFloatFunction thenFunction = p -> {
                thenFunctionCalled.set(true);
                // boolean
                assertThat(p).isEqualTo(true);
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
    public void testToDoubleSup6() throws X  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);

        //given (+ some assertions)
        LBoolSupplier sutO = () -> {
                mainFunctionCalled.set(true);
                return true;
        };

        LBoolToDoubleFunction thenFunction = p -> {
                thenFunctionCalled.set(true);
                // boolean
                assertThat(p).isEqualTo(true);
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
    public void testToCharSup7() throws X  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);

        //given (+ some assertions)
        LBoolSupplier sutO = () -> {
                mainFunctionCalled.set(true);
                return true;
        };

        LBoolToCharFunction thenFunction = p -> {
                thenFunctionCalled.set(true);
                // boolean
                assertThat(p).isEqualTo(true);
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
    public void testToBoolSup8() throws X  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);

        //given (+ some assertions)
        LBoolSupplier sutO = () -> {
                mainFunctionCalled.set(true);
                return true;
        };

        LLogicalOperator thenFunction = p -> {
                thenFunctionCalled.set(true);
                // boolean
                assertThat(p).isEqualTo(true);
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
        assertThat(sut.nestingBoolSup())
            .isSameAs(sut)
            .isInstanceOf(LBoolSupplier.class);
    }

    @Test
    public void testShoving() {
        assertThat(sut.shovingBoolSup())
            .isSameAs(sut)
            .isInstanceOf(LBoolSupplier.class);
    }

    @Test
    public void testNestingX() {
        assertThat(sut.nestingBoolSupX())
            .isSameAs(sut)
            .isInstanceOf(LBoolSupplierX.class);
    }

    @Test
    public void testShovingX() {
        assertThat(sut.shovingBoolSupX())
            .isSameAs(sut)
            .isInstanceOf(LBoolSupplierX.class);
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void testShove() {

        // given
        LBoolSupplier sutThrowing = LBoolSupplier.l(() -> {
            throw new UnsupportedOperationException();
        });

        // when
        sutThrowing.shovingBoolSup().doGetAsBool();
    }

    @Test
    public void testHandleBoolSup() throws X {

        // given
        LBoolSupplier sutThrowing = LBoolSupplier.l(() -> {
            throw new UnsupportedOperationException();
        });

        // when
        LBoolSupplier wrapped = sutThrowing.handleBoolSup(h -> {
            h.wrapIf(UnsupportedOperationException.class::isInstance,IllegalArgumentException::new,  EXCEPTION_WAS_WRAPPED);
        });

        // then
        try {
            wrapped.doGetAsBool();
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
                .contains("LBoolSupplier: boolean doGetAsBool()");
    }


    @Test
    public void isThrowing() {
        assertThat(sut.isThrowing())
            .isFalse();
    }

    @Test void safeCompiles() {
        LBoolSupplier r1 = LBoolSupplier.safe(sut); //NOSONAR
        LBoolSupplierX r2 = LBoolSupplier.safe(sut); //NOSONAR
        BooleanSupplier r3 = LBoolSupplier.safe(sut); //NOSONAR
    }

    @Test void safePropagates() {
        Object result = LBoolSupplier.safe(sut);
        assertThat(result).isSameAs(sut);
    }

    @Test void safeProtectsAgainstNpe() {
        Object result = LBoolSupplier.safe(null);
        assertThat(result).isSameAs(LBoolSupplier.l(LBoolSupplier.safe()));
    }

    @Test  void safeSupplierPropagates() {
        LSupplier<LBoolSupplier> supplier = ()->sut;
        Object result = LBoolSupplier.safeSupplier(supplier);
        assertThat(result).isSameAs(supplier);
    }

    @Test  void safeSupplierProtectsAgainstNpe() {
        Object result = LBoolSupplier.safeSupplier(null);
        assertThat(result).isSameAs(LBoolSupplier.safeSupplier());
    }

    @Test  void safeSupplierCompiles() {
        LSupplier<LBoolSupplier> r1 = LBoolSupplier.safeSupplier(()->sut);  //NOSONAR
        Supplier<LBoolSupplier> r2 = LBoolSupplier.safeSupplier(()->sut); //NOSONAR
    }

}
