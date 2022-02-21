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

package eu.lunisolar.magma.examples;

import eu.lunisolar.magma.asserts.func.operator.unary.LIntUnaryOperatorAssert;
import eu.lunisolar.magma.func.action.LAction;
import eu.lunisolar.magma.func.build.action.LActionBuilder;
import eu.lunisolar.magma.func.function.LFunction;
import eu.lunisolar.magma.func.function.from.LSrtFunction;
import eu.lunisolar.magma.func.operator.binary.LBinaryOperator;
import eu.lunisolar.magma.func.supp.Be;
import org.assertj.core.api.AbstractIntegerAssert;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;

import java.util.concurrent.atomic.*;

import static eu.lunisolar.magma.asserts.Attests.THEN;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

//>transform-to-MD<
/**
 * Basic introduction (by example) to asserting the function behaviour.
 */
//>inject<:readmore

//>inject<:generated

/**
 * Assertions
 * ==========================
 *
 * ### Abstract
 *
 * Basic introduction (by example) to asserting the function behaviour.
 *
 * While testing single lambda expression (usually very small peace of code), on its own, might be overkill. It might be beneficial in some cases
 * to enclose tested solution into a functional interface and test its response (codomain) for the given input (domain).
 *
 * For that purpose every functional interface from library has the assertion class (plus assertion classes for JRE function in any case they would be needed).
 */
public class Example_Assertions_Test {

///
///### Before we use assertions
///
///<a href="https://github.com/lunisolar/magma/blob/master/magma-asserts/src/main/java/eu/lunisolar/magma/asserts/Attests.java" target="_blank">Attests</a>
///and
///<a href="https://github.com/lunisolar/magma/blob/master/magma-asserts/src/main/java/eu/lunisolar/magma/asserts/DefaultAttests.java" target="_blank">DefaultAttests</a>
///are the classes that are aggregating all the factory methods you might need.
///

    /**
     * ### Examples
     */
    private LFunction<Integer, Integer> function      = i -> i;
    private LSrtFunction<Integer>       shortFunction = i -> (int) i;

    public static final AtomicInteger extInfluence = new AtomicInteger(0);
    public static final AtomicInteger extEffect    = new AtomicInteger(0);

    private LAction action = LActionBuilder.action()  // a compilation test
                                           .inCase(() -> extInfluence.get() > 0).evaluate(() -> extEffect.set(extInfluence.get()))
                                           .otherwise(() -> extEffect.set(-1))
                                           .build();

    @Test
    public void inAllFollowingCases_cumulates() {

        LIntUnaryOperatorAssert.The<?, ?> sut = THEN.attestIntUnaryOp(i -> i)
                                                    .inAllFollowingCases(a -> a.isGreaterThan(20))
                                                    .inAllFollowingCases(a -> a.isLessThan(10));

        assertThatThrownBy(() -> sut.doesApplyAsInt(15).asEqualTo(15))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("to be greater than");

        assertThatThrownBy(() -> sut.doesApplyAsInt(25).asEqualTo(15))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("to be less than");

    }

    /**
     * This is how some simple tests can be carried on based on functional assertions:
     */
    //>example<
    @Test
    public void example0() {

        String sut = "1234567890";
        LBinaryOperator<String> replaceOperation = sut::replace;

        THEN.withinStringCodomain()                                         // #1
            .attestBinaryOp(replaceOperation)
            .inAllFollowingCases(result -> result.contains("_x_"))          // #2
            .doesApply("1", "_x_").asEqualTo("_x_234567890")       // #3
            .doesApply("2", "_x_").asEqualTo("1_x_34567890")
            .doesApply("3", "_x_").to$(check -> check.must$(Be::equal$, "12_x_4567890"))  // #4
            .doesApply("3", null).withException(ex -> ex.isInstanceOf(NullPointerException.class));
    }
    //>example<
///
/// > At #1) We can manipulate AssertJ assertion for the codomain.
/// >
/// > At #2) We can add recurring assertions (multiple if needed) to check generic assertions.
/// >
/// > At #3) We can add as many corner cases as needed.
/// >
/// > At #4) There is possibility to use [fluent validations](http://lunisolar.eu/magma/validations-fluent). The "ex" stands for "ex" variants of the methods
/// that actually produce message.
/// 

    /**
     * Another example with some explanation below:
     */
    //>example<
    @Test(expectedExceptions = AssertionError.class, expectedExceptionsMessageRegExp = "Case \\(0\\) should evaluate with exception.")
    public void example() {

        THEN.attestFunc(function)
            .inAllFollowingCases(a -> a.isInstanceOf(Integer.class))
            .doesApply(80).to(a -> a.isEqualTo(80))
            .doesApply(81).toEqualTo(81)
            .doesApply(0).withException(e -> e.isExactlyInstanceOf(UnsupportedOperationException.class)
                                              .hasMessage("Some message"));
    }
    //>example<

///
/// What happens in above code is:
///
/// + we check each time if the result is indeed an Integer (provided there were no exceptions),
/// + we do two test calls to the function to check the result,
///   + first of them operates on full ObjectAssert (and possibly checks more things).
///   + second of them only checks if the return value is equal to specific value (we do not need to use lambda expression for that).
/// + we also assert that for the argument value 0 there will be an exception
///

    /**
     * One unfortunate fact is that since this was a generic FunctionX<T,R,X> the assert we have each time is the ObjectAssert instance.
     *
     * #### Specialised result assertions
     *
     * We can always provide different base Assert for an generic type that is the result of the functions. By using **withinCodomain()** method:
     */
    //>example<
    @Test(expectedExceptions = AssertionError.class)
    public void assertThatInt() {
        THEN.withinCodomain(THEN::attestThatInt).attestFunc(function)
            .doesApply(80).to(a -> a.isGreaterThan(0))    // <- NOW WE CAN DO THIS
            .doesApply(81).toEqualTo(81)
            .doesApply(0).withException(e -> e.isExactlyInstanceOf(UnsupportedOperationException.class)
                                              .hasMessage("Some message"));
    }
    //>example<

    /**
     * Here are examples how you can specify specific AssertJ assertions for the generic codomain.
     */
    //>example<
    @Test
    public void assertThatIntAlternatives() {
        THEN.withinCodomain((LFunction<Integer, AbstractIntegerAssert>) Assertions::assertThat).attestFunc(function)
            .doesApply(80).to(a -> a.isGreaterThan(0));

        THEN.withinCodomain(Assertions::assertThat, Integer.class, AbstractIntegerAssert.class).attestFunc(function)
            .doesApply(80).to(a -> a.isGreaterThan(0));

        THEN.withinIntegerCodomain().attestFunc(function)
            .doesApply(80).to(a -> a.isGreaterThan(0));
    }
    //>example<

    @Test(expectedExceptions = AssertionError.class)
    public void compilationCheck() {

        THEN.withinIntegerCodomain().attestSrtFunc(shortFunction)
            .inAllFollowingCases(a -> a.isInstanceOf(Integer.class))
            .doesApply((short) 80).to(a -> a.isGreaterThan(0))
            .doesApply((short) 81).toEqualTo(81)
            .doesApply((short) 0).withException(e -> e
                .isExactlyInstanceOf(UnsupportedOperationException.class)
                .hasMessage("Some message"));
    }

///
/// ### What if function do not have an input or output?
///
/// Every functional interface has its assertion class, that includes even LAction -
/// <a href="https://github.com/lunisolar/magma/blob/master/magma-asserts/src/main/java/eu/lunisolar/magma/asserts/func/action/LActionAssert.java" target="_blank">LActionAssert</a>
/// In such cases where there is no domain and/or codomain, the assumption of the assertion class is that there might be some external _influence_ or
/// _effect_.
/// Here is an example how to 'access' that external _effect_/_influence_:
///
    //>example<
    @Test(expectedExceptions = AssertionError.class)
    public void testActionAssert() {

        THEN.attestAct(action)
            .doesExecute().when(() -> extInfluence.set(-99)).soThat(() -> assertThat(extEffect.get()).isEqualTo(-1))
            .doesExecute().when(() -> extInfluence.set(0)).soThat(() -> assertThat(extEffect.get()).isEqualTo(-1))
            .doesExecute().when(() -> extInfluence.set(1)).soThat(() -> assertThat(extEffect.get()).isEqualTo(1))
            .doesExecute().when(() -> extInfluence.set(3)).soThat(() -> assertThat(extEffect.get()).isEqualTo(4000));
    }
    //>example<

    @Test(expectedExceptions = AssertionError.class)
    public void testRecurringAssertsNegative() {

        THEN.attestAct((Runnable) action)
            .doesExecute().when(() -> extInfluence.set(-99)).soThat(() -> assertThat(extEffect.get()).isEqualTo(-1))
            .doesExecute().when(() -> extInfluence.set(0)).soThat(() -> assertThat(extEffect.get()).isEqualTo(-1))
            .doesExecute().when(() -> extInfluence.set(1)).soThat(() -> assertThat(extEffect.get()).isEqualTo(1))
            .doesExecute().when(() -> extInfluence.set(3)).soThat(() -> assertThat(extEffect.get()).isEqualTo(4000));
    }

    //>inject<:generated
}
