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

package eu.lunisolar.magma.func.operator.binary;

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
public class LDblBinaryOperatorTest {
    private static final String ORIGINAL_MESSAGE = "Original message";
    private static final String EXCEPTION_WAS_WRAPPED = "Exception was wrapped.";
    private static final String NO_EXCEPTION_WERE_THROWN = "No exception were thrown.";

    private double testValue = 100d;



    private LDblBinaryOperator sut = new LDblBinaryOperator(){
        public  double applyAsDblX(double a1,double a2)  {
            return testValue;
        }
    };



    private DoubleBinaryOperator jre = (a1,a2) -> testValue;


    private LDblBinaryOperator sutAlwaysThrowing = LDblBinaryOperator.dblBinaryOp((a1,a2) -> {
            throw new ParseException(ORIGINAL_MESSAGE, 0);
    });

    private LDblBinaryOperator sutAlwaysThrowingUnchecked = LDblBinaryOperator.dblBinaryOp((a1,a2) -> {
            throw new IndexOutOfBoundsException(ORIGINAL_MESSAGE);
    });


    @Test
    public void testTheResult() throws Throwable {
        assertThat(sut.applyAsDbl(100d,100d))
            .isEqualTo(testValue);
    }

    @Test
    public void testTupleCall() throws Throwable {

        LDblPair domainObject = Tuple4U.dblPair(100d,100d);

        Object result = sut.tupleApplyAsDbl(domainObject);

        assertThat(result)
            .isEqualTo(testValue);
    }

    @Test
    public void testNonNullApplyAsDbl() throws Throwable {
        assertThat(sut.nonNullApplyAsDbl(100d,100d))
            .isEqualTo(testValue);
    }

    @Test
    public void testNestingApplyAsDblUnchecked() throws Throwable {

        // then
        try {
            sutAlwaysThrowingUnchecked.nestingApplyAsDbl(100d,100d);
            fail(NO_EXCEPTION_WERE_THROWN);
        } catch (Exception e) {
            assertThat(e)
                    .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                    .hasNoCause()
                    .hasMessage(ORIGINAL_MESSAGE);
        }
    }

    @Test
    public void testShovingApplyAsDblUnchecked() throws Throwable {

        // then
        try {
            sutAlwaysThrowingUnchecked.shovingApplyAsDbl(100d,100d);
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
            .isEqualTo("LDblBinaryOperator: double applyAsDbl(double a1,double a2)");
    }

    @Test
    public void testDblBinaryOpMethod() throws Throwable {
        assertThat(LDblBinaryOperator.dblBinaryOp((a1,a2) -> testValue ))
            .isInstanceOf(LDblBinaryOperator.class);
    }


    @Test
    public void testWrapStdMethod() throws Throwable {
        assertThat(LDblBinaryOperator.wrap(jre))
            .isInstanceOf(LDblBinaryOperator.class);
    }




    @Test
    public void minBy() throws Throwable  {
        //when
        LDblBinaryOperator min =  LDblBinaryOperator.minBy(Double::compare);

        //then
        assertThat(min.applyAsDbl(0d, 56d))
                .isEqualTo(0d);
        assertThat(min.applyAsDbl(56d, 0d))
                       .isEqualTo(0d);

    }

    @Test
    public void maxBy() throws Throwable  {
        //when
        LDblBinaryOperator max =  LDblBinaryOperator.maxBy(Double::compare);

        //then
        assertThat(max.applyAsDbl(0d, 56d))
                .isEqualTo(56d);
        assertThat(max.applyAsDbl(56d, 0d))
                        .isEqualTo(56d);
    }


    @Test
    public void testMin() throws Throwable {
        //given
        double valueSmall = 10d;
        double valueBig = 100d;

        //when
        LDblBinaryOperator min = LDblBinaryOperator.min();

        //then
        assertThat(min.applyAsDbl(valueSmall, valueBig))
                .isEqualTo(valueSmall);

        assertThat(min.applyAsDbl(valueBig, valueSmall))
                .isEqualTo(valueSmall);
    }

    @Test
    public void testMax() throws Throwable {
        //given
        double valueSmall = 10d;
        double valueBig = 100d;

        //when
        LDblBinaryOperator max = LDblBinaryOperator.max();

        //then
        assertThat(max.applyAsDbl(valueSmall, valueBig))
                .isEqualTo(valueBig);

        assertThat(max.applyAsDbl(valueBig, valueSmall))
                .isEqualTo(valueBig);
    }


    // <editor-fold desc="compose (functional)">

    @Test
    public void testCompose() throws Throwable {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LDblBinaryOperator sutO = (a1,a2) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(90d);
                assertThat(a2).isEqualTo(91d);
                return 100d;
        };

        LDblUnaryOperator before1 = p0 -> {
            assertThat(p0).isEqualTo(80d);
            beforeCalls.incrementAndGet();
            return 90d;
        };
        LDblUnaryOperator before2 = p1 -> {
            assertThat(p1).isEqualTo(81d);
            beforeCalls.incrementAndGet();
            return 91d;
        };

        //when
        LDblBinaryOperator function = sutO.compose(before1,before2);
        function.applyAsDbl(80d,81d);

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(beforeCalls.get()).isEqualTo(2);
    }


    @Test
    public void testDblBinaryOpCompose() throws Throwable {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final AtomicInteger beforeCalls = new AtomicInteger(0);

        //given (+ some assertions)
        LDblBinaryOperator sutO = (a1,a2) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(90d);
                assertThat(a2).isEqualTo(91d);
                return 100d;
        };

        LToDblFunction<Integer> before1 = p0 -> {
            assertThat(p0).isEqualTo(80);
            beforeCalls.incrementAndGet();
            return 90d;
        };
        LToDblFunction<Integer> before2 = p1 -> {
            assertThat(p1).isEqualTo(81);
            beforeCalls.incrementAndGet();
            return 91d;
        };

        //when
        LToDblBiFunction<Integer,Integer> function = sutO.dblBinaryOpCompose(before1,before2);
        function.applyAsDbl(80,81);

        //then - finals
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(beforeCalls.get()).isEqualTo(2);
    }

    // </editor-fold>



    // <editor-fold desc="then (functional)">

    @Test
    public void testThen0() throws Throwable  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);

        //given (+ some assertions)
        LDblBinaryOperator sutO = (a1,a2) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(80d);
                assertThat(a2).isEqualTo(81d);
                return 90d;
        };

        LDblFunction<Integer> thenFunction = p -> {
                thenFunctionCalled.set(true);
                // double
                assertThat(p).isEqualTo(90d);
                // Integer
                return 100;
        };

        //when
        LBiDblFunction<Integer> function = sutO.then(thenFunction);
        Integer finalValue = function.apply(80d,81d);

        //then - finals
        assertThat(finalValue).isEqualTo(100);
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    @Test
    public void testThenToDbl1() throws Throwable  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);

        //given (+ some assertions)
        LDblBinaryOperator sutO = (a1,a2) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(80d);
                assertThat(a2).isEqualTo(81d);
                return 90d;
        };

        LDblUnaryOperator thenFunction = p -> {
                thenFunctionCalled.set(true);
                // double
                assertThat(p).isEqualTo(90d);
                // double
                return 100d;
        };

        //when
        LDblBinaryOperator function = sutO.thenToDbl(thenFunction);
        double finalValue = function.applyAsDbl(80d,81d);

        //then - finals
        assertThat(finalValue).isEqualTo(100d);
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    @Test
    public void testThenToBool2() throws Throwable  {

        final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(()-> false);
        final ThreadLocal<Boolean> thenFunctionCalled = ThreadLocal.withInitial(()-> false);

        //given (+ some assertions)
        LDblBinaryOperator sutO = (a1,a2) -> {
                mainFunctionCalled.set(true);
                assertThat(a1).isEqualTo(80d);
                assertThat(a2).isEqualTo(81d);
                return 90d;
        };

        LDblPredicate thenFunction = p -> {
                thenFunctionCalled.set(true);
                // double
                assertThat(p).isEqualTo(90d);
                // boolean
                return true;
        };

        //when
        LBiDblPredicate function = sutO.thenToBool(thenFunction);
        boolean finalValue = function.test(80d,81d);

        //then - finals
        assertThat(finalValue).isEqualTo(true);
        assertThat(mainFunctionCalled.get()).isEqualTo(true);
        assertThat(thenFunctionCalled.get()).isEqualTo(true);

    }



    // </editor-fold>


    @Test(expectedExceptions = RuntimeException.class)
    public void testShove() {

        // given
        LDblBinaryOperator sutThrowing = LDblBinaryOperator.dblBinaryOp((a1,a2) -> {
            throw new UnsupportedOperationException();
        });

        // when
        sutThrowing.shovingApplyAsDbl(100d,100d);
    }


    @Test
    public void testToString() throws Throwable {

        assertThat(sut.toString())
                .isInstanceOf(String.class)
                .startsWith(this.getClass().getName()+"$");

        assertThat(String.format("%s", sut))
                .isInstanceOf(String.class)
                .contains("LDblBinaryOperator: double applyAsDbl(double a1,double a2)");
    }


    @Test
    public void isThrowing() {
        assertThat(sut.isThrowing())
            .isFalse();
    }

    @Test void safeCompiles() {
        LDblBinaryOperator r1 = LDblBinaryOperator.safe(sut); //NOSONAR
        DoubleBinaryOperator r3 = LDblBinaryOperator.safe(sut); //NOSONAR
    }

    @Test void safePropagates() {
        Object result = LDblBinaryOperator.safe(sut);
        assertThat(result).isSameAs(sut);
    }

    @Test void safeProtectsAgainstNpe() {
        Object result = LDblBinaryOperator.safe(null);
        assertThat(result).isSameAs(LDblBinaryOperator.dblBinaryOp(LDblBinaryOperator.safe()));
    }

    @Test  void safeSupplierPropagates() {
        LSupplier<LDblBinaryOperator> supplier = ()->sut;
        Object result = LDblBinaryOperator.safeSupplier(supplier);
        assertThat(result).isSameAs(supplier);
    }

    @Test  void safeSupplierProtectsAgainstNpe() {
        Object result = LDblBinaryOperator.safeSupplier(null);
        assertThat(result).isSameAs(LDblBinaryOperator.safeSupplier());
    }

    @Test  void safeSupplierCompiles() {
        LSupplier<LDblBinaryOperator> r1 = LDblBinaryOperator.safeSupplier(()->sut);  //NOSONAR
        Supplier<LDblBinaryOperator> r2 = LDblBinaryOperator.safeSupplier(()->sut); //NOSONAR
    }

}
