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

import eu.lunisolar.magma.basics.exceptions.NestedException;
import eu.lunisolar.magma.examples.support.CheckedException;
import eu.lunisolar.magma.func.predicate.LPredicateX;
import org.assertj.core.util.Lists;
import org.testng.annotations.Test;

import java.util.*;
import java.util.function.*;

import static org.assertj.core.api.Assertions.assertThat;

//>transform-to-MD<
/**
 * Basic explanations (by example) how first of main goals where realised -
 * **Throwing lambda expressions and functional interfaces that declare and throw checked exceptions**.
 */
//>inject<:readmore

/**
 * By Example - Throwing lambda expressions and interfaces.
 * ==========================
 *
 * #### Abstract
 *
 * This article covers basic explanations (by example) how first of main goals where realised -
 * **Throwing lambda expressions and functional interfaces that declare and throw checked exceptions**.
 */
public class Example_Goal1_Test {

    private static final List<Integer> integerList = Lists.newArrayList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9);

    /**
     * #### Introduction to the issue
     *
     * Lets consider a method that _unfortunately_ could throw an checked exception:
     */
    //>example<
    public static Integer potentiallyThrowing(Integer i) throws CheckedException {
        return i; // an example
    }
    //>example<

    public static Integer throwingAlways(Integer i) throws CheckedException {
        throw new CheckedException("Something went wrong");
    }

    /**
     * Following code shows what we would need to do _normally_ to use that method in lambda expression:
     */
    //>example<
    @Test
    public void problemToSolve() {

        long result = integerList.stream().filter(i -> {
            try {
                return potentiallyThrowing(i) != null;
            } catch (CheckedException e) {
                throw new RuntimeException(e);
            }
        }).count();

        assertThat(result).isEqualTo(10);
    }
    //>example<

    /**
     * We can eventually minimize this to some method that encloses the exception handling and then use this method by reference.
     */
    //>example<
    private static boolean myMethodToReference(Integer i) {
        try {
            return potentiallyThrowing(i) != null;
        } catch (CheckedException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void problemToSolve2() {

        long result = integerList.stream().filter(Example_Goal1_Test::myMethodToReference).count();

        assertThat(result).isEqualTo(10);
    }
    //>example<

    /**
     * But doing that in every place sometimes, if not always, asks for an better solution. The immediate effect of having less boilerplate code by replacing
     * anonymous classes by lambda expressions is drastically limited when checked expressions comes into the equation.
     * Of course you can always go with only using unchecked exceptions, but in most cases it is not up to you (e.g. Java JRE, or 3rd party libraries have
     * them anyway).
     *
     * #### Solution
     *
     * What this library introduces is actual functional interfaces that do declare and propagate checked exceptions. Each such interface have additional
     * generic parameter that actually allows to define the exception that can be either checked or unchecked one. This generic parameter is consistently named
     * **X** and defined as **X extends Throwable**. So for example for the
     * LFunctionX interface main method is:
     *
     * ```Java
     * boolean doTest(T t) throws X;
     * ```
     *
     * So now you can have something like this:
     */
    //>example<
    @Test(expectedExceptions = CheckedException.class)
    public void checkedExceptionPropagated() throws CheckedException {
        LPredicateX<Integer, CheckedException> predicateX = i -> throwingAlways(i) != null;
        predicateX.doTest(10);
    }
    //>example<

    /**
     * Each such interface has some default and static methods that can help to use them. Here an example what you can do then you need JRE
     * Predicate that handles the exceptions.
     */
    //>example<
    @Test
    public void standardPredicateWithExceptionWrapping_short() {

        long result = integerList.stream().filter(LPredicateX.lX(i -> potentiallyThrowing(i) != null)).count();

        assertThat(result).isEqualTo(10);
    }
    //>example<

    /**
     * What happens is very simple. The **LPredicateX.lX** method is actually only forcing compiler to use more specialized functional interface than the
     * JRE one. Each JRE functional interface (from _java.util.function_ package) is extended by appropriate functional interface from this library.
     * The latter provides default implementation for the method of the former and that method handles the checked exception.
     *
     * If an checked exception will rise in place where this expression is used as normal Predicate then this wil happen:
     */
    @Test(expectedExceptions = NestedException.class, expectedExceptionsMessageRegExp = "\\QSomething went wrong\\E")
    public void standardPredicateWithExceptionWrapping2() {
        long result = integerList.stream().filter(LPredicateX.lX(i -> throwingAlways(i) != null)).count();
    }

    /**
     * > Please note that the **LPredicateX** was here presented in to example tests that actually show different behaviour. In first case we call method
     * **doTest()** and in second it is actually **test()** method called since _filter()_ implementation know only about Predicate from JRE.
     *
     * In case you need, could also wrap existing JRE Predicate. But mind that this actually is done by instance-capturing lambda unlike the **lX** method.
     */
    //>example<
    public static LPredicateX<Integer, CheckedException> example(Predicate<Integer> predicateStd) {
        return LPredicateX.wrap(predicateStd);
    }
    //>example<

    //>inject<:generated
}

