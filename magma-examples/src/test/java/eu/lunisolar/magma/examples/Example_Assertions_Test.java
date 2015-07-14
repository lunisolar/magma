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

package eu.lunisolar.magma.examples;

import eu.lunisolar.magma.examples.support.CheckedException;
import eu.lunisolar.magma.func.Function4U;
import eu.lunisolar.magma.func.action.LAction;
import eu.lunisolar.magma.func.asserts.DefaultFunctionalAssertions;
import eu.lunisolar.magma.func.build.action.LActionBuilder;
import eu.lunisolar.magma.func.build.function.LFunctionBuilder;
import eu.lunisolar.magma.func.build.function.from.LShortFunctionBuilder;
import eu.lunisolar.magma.func.build.supplier.LIntSupplierBuilder;
import eu.lunisolar.magma.func.build.supplier.LSupplierBuilder;
import eu.lunisolar.magma.func.consumer.LConsumer;
import eu.lunisolar.magma.func.consumer.primitives.LIntConsumer;
import eu.lunisolar.magma.func.function.LFunction;
import eu.lunisolar.magma.func.function.LFunctionX;
import eu.lunisolar.magma.func.function.from.LIntFunction;
import eu.lunisolar.magma.func.function.from.LShortFunction;
import eu.lunisolar.magma.func.operator.unary.LIntUnaryOperator;
import eu.lunisolar.magma.func.operator.unary.LUnaryOperator;
import eu.lunisolar.magma.func.predicate.LIntPredicate;
import eu.lunisolar.magma.func.predicate.LPredicate;
import eu.lunisolar.magma.func.supplier.LIntSupplier;
import eu.lunisolar.magma.func.supplier.LSupplier;
import eu.lunisolar.magma.func.supplier.LSupplierX;
import org.assertj.core.api.AbstractIntegerAssert;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.ObjectAssert;
import org.testng.annotations.Test;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.swing.*;
import java.text.*;
import java.util.concurrent.atomic.*;
import java.util.function.*;

import static org.assertj.core.api.Assertions.assertThat;

//>transform-to-MD<
/**
 * Basic explanations (by example) how Assertions work with functional interfaces.
 */
//>inject<:readmore

/**
 * Assertions
 * ==========================
 *
 * ### Abstract
 *
 * Basic explanations (by example) how Assertions work with functional interfaces.
 *
 * In case there is any value in testing specific function implementations, a fluent assertions might come handy.
 */
public class Example_Assertions_Test {

    /**
     * ### Before we use assertions
     *
     * *FunctionalAssertions** is the interface that contains all factory methods for AssertJ assertion classes. All those factory methods are _default_
     * methods.
     *
     * When you are using this interface you must provide base assertion implementation for Object assert - **this is required** (if it is not provided the
     * compiler will go crazy in every case you would like to use it).
     *
     * > Please mind that used here example assertions are just for demonstration purposes, so you need to excuse the simplicity and repetition. Also beware
     * > that those example tests do test and present assertions not the function implementation.
     *
     * The minimalistic way to use it is:
     */
    //>example<
    public static final DefaultFunctionalAssertions<ObjectAssert> then = new DefaultFunctionalAssertions() {
    };
    //>example<

    /**
     * There is no need to implement factory method for Object assert as it is already provided in BasicAssertions:
     */

///```Java
///default OS assertThat(Object actual) {
///    return (OS) Assertions.assertThat(actual);
///}
///```

///
///> Please note that AssertJ do not directly have assert class implementation for primitive types. So case of primitive values all assertions are automatically
///> boxing the values. And since AssertJ is expected to be used in non-production code it was left as is.
///
    /**
     * ### Examples
     *
     * Lets consider following functions.
     */
    //>example<
    private LFunctionX<Integer, Integer, CheckedException> function      = i -> i;
    private LShortFunction<Integer>                        shortFunction = i -> (int) i;
    //>example<

    public static final AtomicInteger externalInfluence = new AtomicInteger(0);
//
//    private LIntSupplier intSupplier = LIntSupplierBuilder.intSupplier() //
//            .inCase(() -> externalInfluence.get() > 0).evaluate(() -> externalInfluence.get())
//            .inCase(() -> false).produce(22)
//            .eventuallyProduce(33)
//            .build();

    public static final AtomicInteger externalEffect = new AtomicInteger(0);

//    private LSupplier<Integer> supplier = LSupplierBuilder.<Integer>supplier()  // a compilation test
//            .inCase(() -> externalInfluence.get() > 0).evaluate(() -> externalInfluence.get())
//            .inCase(() -> false).produce(22)
//            .eventuallyProduce(33)
//            .build();

    private LAction action = LActionBuilder.action()  // a compilation test
            .inCase(() -> externalInfluence.get() > 0).evaluate(() -> externalEffect.set(externalInfluence.get()))
            .eventually(() -> externalEffect.set(-1))
            .build();

    /**
     * Now we can test the function with assertions.
     */
    //>example<
    @Test
    public void example() {
        Assertions.assertThatThrownBy(() -> {

            then.assertThat(function)
                .inAllFollowingCases(a -> a.isInstanceOf(Integer.class))
                .doesApply(80).to(a -> a.isEqualTo(80))
                .doesApply(81).toEqualTo(81)
                .doesApply(0).withException(e -> e
                    .isExactlyInstanceOf(UnsupportedOperationException.class)
                    .hasMessage("Some message"));

        }).hasMessageContaining("Should evaluate with exception");   //TODO fix message, it should include arguments and be more friendly.
    }
    //>example<

///
/// What happens in above code is:
///
/// + we check each time if the result is indeed an Integer (provided there were no exceptions),
/// + we do two test calls to the function to check the result,
///   + first of them operates on full ObjectAssert (and possibly checks more things).
///   + second of them only checks if the return value is equal to specific value (we do not need to use lambda expression for that).
/// + we also assume that for the argument value 0 there will be an exception
///

    /**
     * One unfortunate fact is that since this was a generic FunctionX<T,R,X> the assert we have each time is the ObjectAssert instance.
     *
     * #### Specialised assertions.
     *
     * We can always provide different base Assert for an generic type. By using **withinCodomain()** method. And you actually can always use your own base
     * assertion. Just do main that the Assertions::assertThat is heavily overloaded and comparator would require some generic types to be specified
     * explicitly.
     */
    //>example<
    @Test(expectedExceptions = AssertionError.class)
    public void assertThatInt() {
        then.withinCodomain(then::assertThatInt).assertThat(function)
            .inAllFollowingCases(a -> a.isInstanceOf(Integer.class))
            .doesApply(80).to(a -> a.isGreaterThan(0))    // <- NOW WE CAN DO THIS
                .doesApply(81).toEqualTo(81)
                .doesApply(0).withException(e -> e
                .isExactlyInstanceOf(UnsupportedOperationException.class)
                .hasMessage("Some message"));
    }
    //>example<

    /**
     * Here are examples how you can specify specific assertions for the generic codomain.
     */
    //>example<
    @Test
    public void assertThatIntAlternatives() {
        then.withinCodomain((LFunction<Integer, AbstractIntegerAssert>) Assertions::assertThat).assertThat(function)
            .doesApply(80).to(a -> a.isGreaterThan(0));

        then.withinCodomain(Assertions::assertThat, Integer.class, AbstractIntegerAssert.class).assertThat(function)
            .doesApply(80).to(a -> a.isGreaterThan(0));

        then.withinIntegerCodomain().assertThat(function)
            .doesApply(80).to(a -> a.isGreaterThan(0));
    }
    //>example<

    @Test(expectedExceptions = AssertionError.class)
    public void compilationCheck() {
        then.withinIntegerCodomain().assertThat(shortFunction)
            .inAllFollowingCases(a -> a.isInstanceOf(Integer.class))
            .doesApply((short) 80).to(a -> a.isGreaterThan(0))
            .doesApply((short) 81).toEqualTo(81)
            .doesApply((short) 0).withException(e -> e
                .isExactlyInstanceOf(UnsupportedOperationException.class)
                .hasMessage("Some message"));
    }

    //TODO
//    /**
//     * ### What if function do not have an input or output?
//     *
//     * In most cases it means that the real _effect_ or _influence_ is external. If you can access that external effect and cause then following example
//     * might be usefull. Lets consider extreme case - Action.
//     */
//    //>example<
//    @Test(expectedExceptions = AssertionError.class, expectedExceptionsMessageRegExp = "Expecting:\n <3>\nto be equal to:\n <4000>\nbut was not")
//    public void testRecurringAssertsNegative() throws ParseException {
//
//        then.assertThat(action)
//            .doesExecute().when(() -> externalInfluence.set(-99)).soThat(() -> assertThat(externalEffect.get()).isEqualTo(-1))
//            .doesExecute().when(() -> externalInfluence.set(0)).soThat(() -> assertThat(externalEffect.get()).isEqualTo(-1))
//            .doesExecute().when(() -> externalInfluence.set(1)).soThat(() -> assertThat(externalEffect.get()).isEqualTo(1))
//            .doesExecute().when(() -> externalInfluence.set(3)).soThat(() -> assertThat(externalEffect.get()).isEqualTo(4000))
//        ;
//
//    }
//    //>example<

}
