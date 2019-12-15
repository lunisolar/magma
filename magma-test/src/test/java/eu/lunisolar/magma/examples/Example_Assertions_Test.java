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

import eu.lunisolar.magma.func.action.LAction;
import eu.lunisolar.magma.func.build.action.LActionBuilder;
import eu.lunisolar.magma.func.function.LFunction;
import eu.lunisolar.magma.func.function.from.LSrtFunction;
import org.assertj.core.api.AbstractIntegerAssert;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;

import java.util.concurrent.atomic.*;

import static eu.lunisolar.magma.func.asserts.FunctionalAssertions.THEN;
import static org.assertj.core.api.Assertions.assertThat;

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
 * In case there is any value in testing specific function implementations, a fluent assertions might come handy. Obviously this is not intended for every
 * function.
 * And actually you could always use it for testing any method that happens to have corresponding interface in this library.
 */
public class Example_Assertions_Test {

///
///### Before we use assertions
///
///<a href="https://github.com/lunisolar/magma/blob/master/magma-func-asserts/src/main/java/eu/lunisolar/magma/func/asserts/FunctionalAssertions.java" target="_blank">FunctionalAssertions</a>
///and
///<a href="https://github.com/lunisolar/magma/blob/master/magma-func-asserts/src/main/java/eu/lunisolar/magma/func/asserts/DefaultFunctionalAssertions.java" target="_blank">DefaultFunctionalAssertions</a>
///are the classes that are aggregating all the methods you need. 
///

    /**
     * ### Examples
     *
     * Lets consider following functions.
     */
    //>example<
    private LFunction<Integer, Integer> function      = i -> i;
    private LSrtFunction<Integer>       shortFunction = i -> (int) i;
    //>example<

    public static final AtomicInteger extInfluence = new AtomicInteger(0);
    public static final AtomicInteger extEffect    = new AtomicInteger(0);

    private LAction action = LActionBuilder.action()  // a compilation test
                                           .inCase(() -> extInfluence.get() > 0).evaluate(() -> extEffect.set(extInfluence.get()))
                                           .eventually(() -> extEffect.set(-1))
                                           .build();

    /**
     * Now we can test the function with assertions.
     */
    //>example<
    @Test(expectedExceptions = AssertionError.class, expectedExceptionsMessageRegExp = "Case \\(0\\) should evaluate with exception.")
    public void example() {

        THEN.assertFunc(function)
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
        THEN.withinCodomain(THEN::assertThatInt).assertFunc(function)
            .doesApply(80).to(a -> a.isGreaterThan(0))    // <- NOW WE CAN DO THIS
            .doesApply(81).toEqualTo(81)
            .doesApply(0).withException(e -> e.isExactlyInstanceOf(UnsupportedOperationException.class)
                                              .hasMessage("Some message"));
    }
    //>example<

    /**
     * Here are examples how you can specify specific assertions for the generic codomain.
     */
    //>example<
    @Test
    public void assertThatIntAlternatives() {
        THEN.withinCodomain((LFunction<Integer, AbstractIntegerAssert>) Assertions::assertThat).assertFunc(function)
            .doesApply(80).to(a -> a.isGreaterThan(0));

        THEN.withinCodomain(Assertions::assertThat, Integer.class, AbstractIntegerAssert.class).assertFunc(function)
            .doesApply(80).to(a -> a.isGreaterThan(0));

        THEN.withinIntegerCodomain().assertFunc(function)
            .doesApply(80).to(a -> a.isGreaterThan(0));
    }
    //>example<

    @Test(expectedExceptions = AssertionError.class)
    public void compilationCheck() {

        THEN.withinIntegerCodomain().assertSrtFunc(shortFunction)
            .inAllFollowingCases(a -> a.isInstanceOf(Integer.class))
            .doesApply((short) 80).to(a -> a.isGreaterThan(0))
            .doesApply((short) 81).toEqualTo(81)
            .doesApply((short) 0).withException(e -> e
                .isExactlyInstanceOf(UnsupportedOperationException.class)
                .hasMessage("Some message"));
    }

    /**
     * ### What if function do not have an input or output?
     *
     * In most cases it means that the real _effect_ or _influence_ is external. If you can access that external effect and cause then following example
     * might be useful. Lets consider extreme case - Action.
     */
    //>example<
    @Test(expectedExceptions = AssertionError.class)
    public void testActionAssert() {

        THEN.assertAct(action)
            .doesExecute().when(() -> extInfluence.set(-99)).soThat(() -> assertThat(extEffect.get()).isEqualTo(-1))
            .doesExecute().when(() -> extInfluence.set(0)).soThat(() -> assertThat(extEffect.get()).isEqualTo(-1))
            .doesExecute().when(() -> extInfluence.set(1)).soThat(() -> assertThat(extEffect.get()).isEqualTo(1))
            .doesExecute().when(() -> extInfluence.set(3)).soThat(() -> assertThat(extEffect.get()).isEqualTo(4000));
    }
    //>example<


    /**
     * ### Assertions for JRE interfaces
     *
     * Provided that JRE interface exists, the library interface extends it. But that will not work for asserting the result of the instance of the only JRE interface.
     * So for each JRE interface there is also a corresponding factory method (however naming convention is not from JRE): 
     */
    //>example<
    @Test(expectedExceptions = AssertionError.class)
    public void testRecurringAssertsNegative() {

        THEN.assertAct((Runnable)action)
            .doesExecute().when(() -> extInfluence.set(-99)).soThat(() -> assertThat(extEffect.get()).isEqualTo(-1))
            .doesExecute().when(() -> extInfluence.set(0)).soThat(() -> assertThat(extEffect.get()).isEqualTo(-1))
            .doesExecute().when(() -> extInfluence.set(1)).soThat(() -> assertThat(extEffect.get()).isEqualTo(1))
            .doesExecute().when(() -> extInfluence.set(3)).soThat(() -> assertThat(extEffect.get()).isEqualTo(4000));
    }
    //>example<

    //>inject<:generated
}
