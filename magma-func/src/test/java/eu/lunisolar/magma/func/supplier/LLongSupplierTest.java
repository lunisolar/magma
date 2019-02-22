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
import eu.lunisolar.magma.func.tuple.*; // NOSONAR
import static org.assertj.core.api.Assertions.*; //NOSONAR
import java.util.function.*; // NOSONAR

/** The test obviously concentrate on the interface methods the function it self is very simple.  */
public class LLongSupplierTest {
    private static final String ORIGINAL_MESSAGE = "Original message";
    private static final String EXCEPTION_WAS_WRAPPED = "Exception was wrapped.";
    private static final String NO_EXCEPTION_WERE_THROWN = "No exception were thrown.";

    private long testValue = 100L;



    private LLongSupplier sut = new LLongSupplier(){
        public  long getAsLongX()  {
            return testValue;
        }
    };



    private LongSupplier jre = () -> testValue;


    private LLongSupplier sutAlwaysThrowing = LLongSupplier.longSup(() -> {
            throw new ParseException(ORIGINAL_MESSAGE, 0);
    });

    private LLongSupplier sutAlwaysThrowingUnchecked = LLongSupplier.longSup(() -> {
            throw new IndexOutOfBoundsException(ORIGINAL_MESSAGE);
    });


    @Test
    public void testTheResult() throws Throwable {
        assertThat(sut.getAsLong())
            .isEqualTo(testValue);
    }

    @Test
    public void testTupleCall() throws Throwable {

        LTuple.Void domainObject = Tuple4U.tuple();

        Object result = sut.tupleGetAsLong(domainObject);

        assertThat(result)
            .isEqualTo(testValue);
    }

    @Test
    public void testNonNullGetAsLong() throws Throwable {
        assertThat(sut.nonNullGetAsLong())
            .isEqualTo(testValue);
    }

    @Test
    public void testNestingGetAsLongUnchecked() throws Throwable {

        // then
        try {
            sutAlwaysThrowingUnchecked.nestingGetAsLong();
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                    .hasNoCause()
                    .hasMessage(ORIGINAL_MESSAGE);
        }
    }

    @Test
    public void testShovingGetAsLongUnchecked() throws Throwable {

        // then
        try {
            sutAlwaysThrowingUnchecked.shovingGetAsLong();
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                    .hasNoCause()
                    .hasMessage(ORIGINAL_MESSAGE);
        }
    }


    @Test
    public void testFunctionalInterfaceDescription() throws Throwable {
        assertThat(sut.functionalInterfaceDescription())
            .isEqualTo("LLongSupplier: long getAsLong()");
    }

    @Test
    public void testLongSupMethod() throws Throwable {
        assertThat(LLongSupplier.longSup(() -> testValue ))
            .isInstanceOf(LLongSupplier.class);
    }


    @Test
    public void testWrapStdMethod() throws Throwable {
        assertThat(LLongSupplier.wrap(jre))
            .isInstanceOf(LLongSupplier.class);
    }






    // <editor-fold desc="then (functional)">

    @Test
    public void testToSup0() throws Throwable  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);

        //given (+ some assertions)
        LLongSupplier sutO = () -> {
                mainFunctionCalled.set(true);
                return 90L;
        };

        LLongFunction<Integer> thenFunction = p -> {
                thenFunctionCalled.set(true);
                // long
                assertThat(p).isEqualTo(90L);
                // Integer
                return 100;
        };

        //when
        LSupplier<Integer> function = sutO.toSup(thenFunction);
        Integer finalValue = function.get();

        //then - finals
        assertThat(finalValue).isEqualTo(100);
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    @Test
    public void testToByteSup1() throws Throwable  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);

        //given (+ some assertions)
        LLongSupplier sutO = () -> {
                mainFunctionCalled.set(true);
                return 90L;
        };

        LLongToByteFunction thenFunction = p -> {
                thenFunctionCalled.set(true);
                // long
                assertThat(p).isEqualTo(90L);
                // byte
                return (byte)100;
        };

        //when
        LByteSupplier function = sutO.toByteSup(thenFunction);
        byte finalValue = function.getAsByte();

        //then - finals
        assertThat(finalValue).isEqualTo((byte)100);
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    @Test
    public void testToSrtSup2() throws Throwable  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);

        //given (+ some assertions)
        LLongSupplier sutO = () -> {
                mainFunctionCalled.set(true);
                return 90L;
        };

        LLongToSrtFunction thenFunction = p -> {
                thenFunctionCalled.set(true);
                // long
                assertThat(p).isEqualTo(90L);
                // short
                return (short)100;
        };

        //when
        LSrtSupplier function = sutO.toSrtSup(thenFunction);
        short finalValue = function.getAsSrt();

        //then - finals
        assertThat(finalValue).isEqualTo((short)100);
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    @Test
    public void testToIntSup3() throws Throwable  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);

        //given (+ some assertions)
        LLongSupplier sutO = () -> {
                mainFunctionCalled.set(true);
                return 90L;
        };

        LLongToIntFunction thenFunction = p -> {
                thenFunctionCalled.set(true);
                // long
                assertThat(p).isEqualTo(90L);
                // int
                return 100;
        };

        //when
        LIntSupplier function = sutO.toIntSup(thenFunction);
        int finalValue = function.getAsInt();

        //then - finals
        assertThat(finalValue).isEqualTo(100);
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    @Test
    public void testToLongSup4() throws Throwable  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);

        //given (+ some assertions)
        LLongSupplier sutO = () -> {
                mainFunctionCalled.set(true);
                return 90L;
        };

        LLongUnaryOperator thenFunction = p -> {
                thenFunctionCalled.set(true);
                // long
                assertThat(p).isEqualTo(90L);
                // long
                return 100L;
        };

        //when
        LLongSupplier function = sutO.toLongSup(thenFunction);
        long finalValue = function.getAsLong();

        //then - finals
        assertThat(finalValue).isEqualTo(100L);
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    @Test
    public void testToFltSup5() throws Throwable  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);

        //given (+ some assertions)
        LLongSupplier sutO = () -> {
                mainFunctionCalled.set(true);
                return 90L;
        };

        LLongToFltFunction thenFunction = p -> {
                thenFunctionCalled.set(true);
                // long
                assertThat(p).isEqualTo(90L);
                // float
                return 100f;
        };

        //when
        LFltSupplier function = sutO.toFltSup(thenFunction);
        float finalValue = function.getAsFlt();

        //then - finals
        assertThat(finalValue).isEqualTo(100f);
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    @Test
    public void testToDblSup6() throws Throwable  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);

        //given (+ some assertions)
        LLongSupplier sutO = () -> {
                mainFunctionCalled.set(true);
                return 90L;
        };

        LLongToDblFunction thenFunction = p -> {
                thenFunctionCalled.set(true);
                // long
                assertThat(p).isEqualTo(90L);
                // double
                return 100d;
        };

        //when
        LDblSupplier function = sutO.toDblSup(thenFunction);
        double finalValue = function.getAsDbl();

        //then - finals
        assertThat(finalValue).isEqualTo(100d);
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    @Test
    public void testToCharSup7() throws Throwable  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);

        //given (+ some assertions)
        LLongSupplier sutO = () -> {
                mainFunctionCalled.set(true);
                return 90L;
        };

        LLongToCharFunction thenFunction = p -> {
                thenFunctionCalled.set(true);
                // long
                assertThat(p).isEqualTo(90L);
                // char
                return '\u0100';
        };

        //when
        LCharSupplier function = sutO.toCharSup(thenFunction);
        char finalValue = function.getAsChar();

        //then - finals
        assertThat(finalValue).isEqualTo('\u0100');
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    @Test
    public void testToBoolSup8() throws Throwable  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);

        //given (+ some assertions)
        LLongSupplier sutO = () -> {
                mainFunctionCalled.set(true);
                return 90L;
        };

        LLongPredicate thenFunction = p -> {
                thenFunctionCalled.set(true);
                // long
                assertThat(p).isEqualTo(90L);
                // boolean
                return true;
        };

        //when
        LBoolSupplier function = sutO.toBoolSup(thenFunction);
        boolean finalValue = function.getAsBool();

        //then - finals
        assertThat(finalValue).isEqualTo(true);
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    // </editor-fold>


    @Test(expectedExceptions = RuntimeException.class)
    public void testShove() {

        // given
        LLongSupplier sutThrowing = LLongSupplier.longSup(() -> {
            throw new UnsupportedOperationException();
        });

        // when
        sutThrowing.shovingGetAsLong();
    }


    @Test
    public void testToString() throws Throwable {

        assertThat(sut.toString())
                .isInstanceOf(String.class)
                .startsWith(this.getClass().getName()+"$");

        assertThat(String.format("%s", sut))
                .isInstanceOf(String.class)
                .contains("LLongSupplier: long getAsLong()");
    }


    @Test
    public void isThrowing() {
        assertThat(sut.isThrowing())
            .isFalse();
    }

    @Test void safeCompiles() {
        LLongSupplier r1 = LLongSupplier.safe(sut); //NOSONAR
        LongSupplier r3 = LLongSupplier.safe(sut); //NOSONAR
    }

    @Test void safePropagates() {
        Object result = LLongSupplier.safe(sut);
        assertThat(result).isSameAs(sut);
    }

    @Test void safeProtectsAgainstNpe() {
        Object result = LLongSupplier.safe(null);
        assertThat(result).isSameAs(LLongSupplier.longSup(LLongSupplier.safe()));
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
        LSupplier<LLongSupplier> r1 = LLongSupplier.safeSupplier(()->sut);  //NOSONAR
        Supplier<LLongSupplier> r2 = LLongSupplier.safeSupplier(()->sut); //NOSONAR
    }

}
