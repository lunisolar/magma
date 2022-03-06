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

import eu.lunisolar.magma.asserts.func.FuncAttests;
import eu.lunisolar.magma.asserts.func.operator.unary.LIntUnaryOperatorAttest;
import eu.lunisolar.magma.func.action.LAction;
import eu.lunisolar.magma.func.build.action.LActionBuilder;
import eu.lunisolar.magma.func.function.LFunction;
import eu.lunisolar.magma.func.function.from.LSrtFunction;
import eu.lunisolar.magma.func.operator.binary.LBinaryOperator;
import eu.lunisolar.magma.func.supp.Be;
import eu.lunisolar.magma.func.supp.Have;
import eu.lunisolar.magma.func.supp.P;
import org.testng.annotations.Test;

import java.util.concurrent.atomic.*;

import static eu.lunisolar.magma.func.supp.check.Checks.attest;
import static eu.lunisolar.magma.func.supp.check.Checks.attestThrownBy;

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

        LIntUnaryOperatorAttest sut = FuncAttests.attestIntUnaryOp(i -> i)
                                                 .inAllFollowingCases(a -> a.must$(Be::gt$, 20))
                                                 .inAllFollowingCases(a -> a.must$(Be::lt$, 10));

        attestThrownBy(() -> sut.doesApplyAsInt(15).asEqualTo(15))
                .must$(Be::instanceOf$, AssertionError.class)
                .must$(Have::msgContain$, "must be > 20");

        attestThrownBy(() -> sut.doesApplyAsInt(25).asEqualTo(15))
                .must$(Be::instanceOf$, AssertionError.class)
                .must$(Have::msgContain$, "25 must be < 10");

    }

    /**
     * This is how some simple tests can be carried on, based on functional assertions:
     */
    //>example<
    @Test
    public void example0() {

        String sut = "1234567890";
        LBinaryOperator<String> replaceOperation = sut::replace;

        FuncAttests.attestBinaryOp(replaceOperation)
                   .inAllFollowingCases(result -> result.must$(P::contain$, "_x_")) // #1
                   .doesApply("1", "_x_").asEqualTo("_x_234567890")            // #2
                   .doesApply("2", "_x_").asEqualTo("1_x_34567890")
                   .doesApply("3", "_x_").to$(check -> check.must$(Be::equal$, "12_x_4567890"))  // #3
                   .doesApply("3", null).withException(ex -> ex.must$(Be::instanceOf$, NullPointerException.class));
    }
    //>example<
///
/// > At #1) We can add recurring assertions (multiple if needed) to check base assertions.
/// >
/// > At #2) We can add as many corner cases as needed.
/// >
/// > At #3) There is possibility to use [fluent validations](http://lunisolar.eu/magma/validations-fluent). The "ex" stands for "ex" variants of the methods
/// that actually produce message.
///

    /**
     * Another example with some explanation below:
     */
    //>example<
    @Test(expectedExceptions = AssertionError.class, expectedExceptionsMessageRegExp = "Case \\(0\\) should evaluate with exception.")
    public void example() {

        FuncAttests.attestFunc(function)
                   .inAllFollowingCases(a -> a.must$(Be::instanceOf$, Integer.class))
                   .doesApply(80).to(a -> a.must$(Be::equal$, 80))
                   .doesApply(81).toEqualTo(81)
                   .doesApply(0).withException(e -> e.must$(Be::instanceOf$, UnsupportedOperationException.class)
                                                     .must$(Have::msgEqual$, "Some message"));
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
     * TODO. TODO, TODO: this is probably to be removed!
     *
     * #### Specialised result assertions
     *
     * We can always provide different base Assert for an generic type that is the result of the functions. By using **withinCodomain()** method:
     */
    //>example<
    @Test(expectedExceptions = AssertionError.class)
    public void assertThatInt() {
        FuncAttests.attestFunc(function)
                   .doesApply(80).to(a -> a.must$(Be::gt$, 0))
                   .doesApply(81).toEqualTo(81)
                   .doesApply(0).withException(e -> e.must$(Be::instanceOf$, UnsupportedOperationException.class)
                                                     .must$(Have::msgEqual$, "Some message"));
    }
    //>example<

    @Test(expectedExceptions = AssertionError.class)
    public void compilationCheck() {

        FuncAttests.attestSrtFunc(shortFunction)
                   .inAllFollowingCases(a -> a.must$(Be::instanceOf$, Integer.class))
                   .doesApply((short) 80).to(a -> a.must$(Be::gt$, 0))
                   .doesApply((short) 81).toEqualTo(81)
                   .doesApply((short) 0).withException(e -> e
                           .must$(Be::instanceOf$, UnsupportedOperationException.class)
                           .must$(Have::msgEqual$, "Some message"));
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

        FuncAttests.attestAct(action)
                   .doesExecute().when(() -> extInfluence.set(-99)).soThat(() -> attest(extEffect.get()).must$(Be::equal$, -1))
                   .doesExecute().when(() -> extInfluence.set(0)).soThat(() -> attest(extEffect.get()).must$(Be::equal$, -1))
                   .doesExecute().when(() -> extInfluence.set(1)).soThat(() -> attest(extEffect.get()).must$(Be::equal$, 1))
                   .doesExecute().when(() -> extInfluence.set(3)).soThat(() -> attest(extEffect.get()).must$(Be::equal$, 4000));
    }
    //>example<

    @Test(expectedExceptions = AssertionError.class)
    public void testRecurringAssertsNegative() {

        FuncAttests.attestAct((Runnable) action)
                   .doesExecute().when(() -> extInfluence.set(-99)).soThat(() -> attest(extEffect.get()).must$(Be::equal$, -1))
                   .doesExecute().when(() -> extInfluence.set(0)).soThat(() -> attest(extEffect.get()).must$(Be::equal$, -1))
                   .doesExecute().when(() -> extInfluence.set(1)).soThat(() -> attest(extEffect.get()).must$(Be::equal$, 1))
                   .doesExecute().when(() -> extInfluence.set(3)).soThat(() -> attest(extEffect.get()).must$(Be::equal$, 4000));
    }

    //>inject<:generated
}
