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

package eu.lunisolar.magma.func.function.from;

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
public class LBiObjCharFunctionTest<T1,T2,R> {
    private static final String ORIGINAL_MESSAGE = "Original message";
    private static final String EXCEPTION_WAS_WRAPPED = "Exception was wrapped.";
    private static final String NO_EXCEPTION_WERE_THROWN = "No exception were thrown.";

    private Integer testValue = 100;



    private LBiObjCharFunction<Integer,Integer,Integer> sut = new LBiObjCharFunction<Integer,Integer,Integer>(){
        public @Nullable Integer doApplyX(Integer a1,Integer a2,char a3)  {
            return testValue;
        }
    };


    private LBiObjCharFunction<Integer,Integer,Integer> sutNull = new LBiObjCharFunction<Integer,Integer,Integer>(){
        public @Nullable Integer doApplyX(Integer a1,Integer a2,char a3)  {
            return null;
        }
    };



    private LBiObjCharFunction<Integer,Integer,Integer> sutAlwaysThrowing = LBiObjCharFunction.biObjCharFunc((a1,a2,a3) -> {
            throw new ParseException(ORIGINAL_MESSAGE, 0);
    });

    private LBiObjCharFunction<Integer,Integer,Integer> sutAlwaysThrowingUnchecked = LBiObjCharFunction.biObjCharFunc((a1,a2,a3) -> {
            throw new IndexOutOfBoundsException(ORIGINAL_MESSAGE);
    });


    @Test
    public void testTheResult() throws Throwable {
        assertThat(sut.doApply(100,100,'\u0100'))
            .isEqualTo(testValue);
    }

    @Test
    public void testTupleCall() throws Throwable {

        LBiObjCharTriple<Integer,Integer> domainObject = Tuple4U.biObjCharTriple(100,100,'\u0100');

        Object result = sut.tupleApply(domainObject);

        assertThat(result)
            .isEqualTo(testValue);
    }

    @Test
    public void testNonNullDoApply() throws Throwable {
        assertThat(sut.nonNullDoApply(100,100,'\u0100'))
            .isSameAs(testValue);
    }

    @Test
    public void testNestingDoApplyUnchecked() throws Throwable {

        // then
        try {
            sutAlwaysThrowingUnchecked.nestingDoApply(100,100,'\u0100');
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                    .hasNoCause()
                    .hasMessage(ORIGINAL_MESSAGE);
        }
    }

    @Test
    public void testShovingDoApplyUnchecked() throws Throwable {

        // then
        try {
            sutAlwaysThrowingUnchecked.shovingDoApply(100,100,'\u0100');
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                    .hasNoCause()
                    .hasMessage(ORIGINAL_MESSAGE);
        }
    }

    @Test(expectedExceptions=NullPointerException.class, expectedExceptionsMessageRegExp="\\QEvaluated value by nonNullDoApply() method cannot be null (LBiObjCharFunction: R doApply(T1 a1,T2 a2,char a3)).\\E")
    public void testNonNullCapturesNull() throws Throwable {
        sutNull.nonNullDoApply(100,100,'\u0100');
    }


    @Test
    public void testFunctionalInterfaceDescription() throws Throwable {
        assertThat(sut.functionalInterfaceDescription())
            .isEqualTo("LBiObjCharFunction: R doApply(T1 a1,T2 a2,char a3)");
    }

    @Test
    public void testBiObjCharFuncMethod() throws Throwable {
        assertThat(LBiObjCharFunction.biObjCharFunc((a1,a2,a3) -> testValue ))
            .isInstanceOf(LBiObjCharFunction.class);
    }






    // <editor-fold desc="compose (functional)">

    @Test
    public void testBiObjCharFuncComposeChar() throws Throwable {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LBiObjCharFunction<Integer,Integer,Integer> sutO = (a1,a2,a3) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(90);
                assertThat(a2).isEqualTo(91);
                assertThat(a3).isEqualTo('\u0092');
                return 100;
        };

        LFunction<Integer,Integer> before1 = p0 -> {
            assertThat(p0).isEqualTo(80);
            beforeCalls.incrementAndGet();
            return 90;
        };
        LFunction<Integer,Integer> before2 = p1 -> {
            assertThat(p1).isEqualTo(81);
            beforeCalls.incrementAndGet();
            return 91;
        };
        LCharUnaryOperator before3 = p2 -> {
            assertThat(p2).isEqualTo('\u0082');
            beforeCalls.incrementAndGet();
            return '\u0092';
        };

        //when
        LBiObjCharFunction<Integer,Integer,Integer> function = sutO.biObjCharFuncComposeChar(before1,before2,before3);
        function.doApply(80,81,'\u0082');

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(beforeCalls.get()).isEqualTo(3);
    }


    @Test
    public void testBiObjCharFuncCompose() throws Throwable {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LBiObjCharFunction<Integer,Integer,Integer> sutO = (a1,a2,a3) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(90);
                assertThat(a2).isEqualTo(91);
                assertThat(a3).isEqualTo('\u0092');
                return 100;
        };

        LFunction<Integer,Integer> before1 = p0 -> {
            assertThat(p0).isEqualTo(80);
            beforeCalls.incrementAndGet();
            return 90;
        };
        LFunction<Integer,Integer> before2 = p1 -> {
            assertThat(p1).isEqualTo(81);
            beforeCalls.incrementAndGet();
            return 91;
        };
        LToCharFunction<Integer> before3 = p2 -> {
            assertThat(p2).isEqualTo(82);
            beforeCalls.incrementAndGet();
            return '\u0092';
        };

        //when
        LTriFunction<Integer,Integer,Integer,Integer> function = sutO.biObjCharFuncCompose(before1,before2,before3);
        function.doApply(80,81,82);

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(beforeCalls.get()).isEqualTo(3);
    }

    // </editor-fold>



    // <editor-fold desc="then (functional)">

    @Test
    public void testThen0() throws Throwable  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);

        //given (+ some assertions)
        LBiObjCharFunction<Integer,Integer,Integer> sutO = (a1,a2,a3) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(80);
                assertThat(a2).isEqualTo(81);
                assertThat(a3).isEqualTo('\u0082');
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
        LBiObjCharFunction<Integer,Integer,Integer> function = sutO.then(thenFunction);
        Integer finalValue = function.doApply(80,81,'\u0082');

        //then - finals
        assertThat(finalValue).isEqualTo(100);
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    @Test
    public void testThenConsume1() throws Throwable  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);

        //given (+ some assertions)
        LBiObjCharFunction<Integer,Integer,Integer> sutO = (a1,a2,a3) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(80);
                assertThat(a2).isEqualTo(81);
                assertThat(a3).isEqualTo('\u0082');
                return 90;
        };

        LConsumer<Integer> thenFunction = p -> {
                thenFunctionCalled.set(true);
                // Integer
                assertThat(p).isEqualTo(90);
        };

        //when
        LBiObjCharConsumer<Integer,Integer> function = sutO.thenConsume(thenFunction);
        function.doAccept(80,81,'\u0082');

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    @Test
    public void testThenToBool2() throws Throwable  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);

        //given (+ some assertions)
        LBiObjCharFunction<Integer,Integer,Integer> sutO = (a1,a2,a3) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(80);
                assertThat(a2).isEqualTo(81);
                assertThat(a3).isEqualTo('\u0082');
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
        LBiObjCharPredicate<Integer,Integer> function = sutO.thenToBool(thenFunction);
        boolean finalValue = function.doTest(80,81,'\u0082');

        //then - finals
        assertThat(finalValue).isEqualTo(true);
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    // </editor-fold>

    @Test
    public void testNesting() {
        assertThat(sut.nestingBiObjCharFunc())
            .isSameAs(sut)
            .isInstanceOf(LBiObjCharFunction.class);
    }

    @Test
    public void testShoving() {
        assertThat(sut.shovingBiObjCharFunc())
            .isSameAs(sut)
            .isInstanceOf(LBiObjCharFunction.class);
    }


    @Test(expectedExceptions = RuntimeException.class)
    public void testShove() {

        // given
        LBiObjCharFunction<Integer,Integer,Integer> sutThrowing = LBiObjCharFunction.biObjCharFunc((a1,a2,a3) -> {
            throw new UnsupportedOperationException();
        });

        // when
        sutThrowing.shovingBiObjCharFunc().doApply(100,100,'\u0100');
    }


    @Test
    public void testToString() throws Throwable {

        assertThat(sut.toString())
                .isInstanceOf(String.class)
                .startsWith(this.getClass().getName()+"$");

        assertThat(String.format("%s", sut))
                .isInstanceOf(String.class)
                .contains("LBiObjCharFunction: R doApply(T1 a1,T2 a2,char a3)");
    }


    @Test
    public void isThrowing() {
        assertThat(sut.isThrowing())
            .isFalse();
    }

    //<editor-fold desc="Variants">

    private Integer variantLObjCharObj1Func(Integer a1,char a3,Integer a2) {
        return 100;
    }

    @Test
    public void compilerSubstituteVariantLObjCharObj1Func() {
        LBiObjCharFunction lambda = LBiObjCharFunction./*<T1,T2,R>*/objCharObj1Func(this::variantLObjCharObj1Func);

        assertThat(lambda).isInstanceOf(LBiObjCharFunction.LObjCharObj1Func.class);
    }


    private Integer variantLObj1Obj0CharFunc(Integer a2,Integer a1,char a3) {
        return 100;
    }

    @Test
    public void compilerSubstituteVariantLObj1Obj0CharFunc() {
        LBiObjCharFunction lambda = LBiObjCharFunction./*<T1,T2,R>*/obj1Obj0CharFunc(this::variantLObj1Obj0CharFunc);

        assertThat(lambda).isInstanceOf(LBiObjCharFunction.LObj1Obj0CharFunc.class);
    }


    private Integer variantLObj1CharObj0Func(Integer a2,char a3,Integer a1) {
        return 100;
    }

    @Test
    public void compilerSubstituteVariantLObj1CharObj0Func() {
        LBiObjCharFunction lambda = LBiObjCharFunction./*<T1,T2,R>*/obj1CharObj0Func(this::variantLObj1CharObj0Func);

        assertThat(lambda).isInstanceOf(LBiObjCharFunction.LObj1CharObj0Func.class);
    }


    private Integer variantLCharObj0Obj1Func(char a3,Integer a1,Integer a2) {
        return 100;
    }

    @Test
    public void compilerSubstituteVariantLCharObj0Obj1Func() {
        LBiObjCharFunction lambda = LBiObjCharFunction./*<T1,T2,R>*/charObj0Obj1Func(this::variantLCharObj0Obj1Func);

        assertThat(lambda).isInstanceOf(LBiObjCharFunction.LCharObj0Obj1Func.class);
    }


    private Integer variantLCharObjObj0Func(char a3,Integer a2,Integer a1) {
        return 100;
    }

    @Test
    public void compilerSubstituteVariantLCharObjObj0Func() {
        LBiObjCharFunction lambda = LBiObjCharFunction./*<T1,T2,R>*/charObjObj0Func(this::variantLCharObjObj0Func);

        assertThat(lambda).isInstanceOf(LBiObjCharFunction.LCharObjObj0Func.class);
    }

    //</editor-fold>


    @Test void safeCompiles() {
        LBiObjCharFunction r1 = LBiObjCharFunction.safe(sut); //NOSONAR
    }

    @Test void safePropagates() {
        Object result = LBiObjCharFunction.safe(sut);
        assertThat(result).isSameAs(sut);
    }

    @Test void safeProtectsAgainstNpe() {
        Object result = LBiObjCharFunction.safe(null);
        assertThat(result).isSameAs(LBiObjCharFunction.biObjCharFunc(LBiObjCharFunction.safe()));
    }

    @Test  void safeSupplierPropagates() {
        LSupplier<LBiObjCharFunction<Integer,Integer,Integer>> supplier = ()->sut;
        Object result = LBiObjCharFunction.safeSupplier(supplier);
        assertThat(result).isSameAs(supplier);
    }

    @Test  void safeSupplierProtectsAgainstNpe() {
        Object result = LBiObjCharFunction.safeSupplier(null);
        assertThat(result).isSameAs(LBiObjCharFunction.safeSupplier());
    }

    @Test  void safeSupplierCompiles() {
        LSupplier<LBiObjCharFunction<Integer,Integer,Integer>> r1 = LBiObjCharFunction.safeSupplier(()->sut);  //NOSONAR
    }

}
