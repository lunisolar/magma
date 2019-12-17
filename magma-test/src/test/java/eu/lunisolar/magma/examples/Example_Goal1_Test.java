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

import eu.lunisolar.magma.basics.exceptions.NestedException;
import eu.lunisolar.magma.examples.support.CheckedException;
import eu.lunisolar.magma.func.consumer.LConsumer;
import eu.lunisolar.magma.func.predicate.LPredicate;
import org.assertj.core.util.Lists;
import org.testng.annotations.Test;

import java.io.*;
import java.util.*;
import java.util.function.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

//>transform-to-MD<
/**
 * Basic introduction (by example) to details of first of main goals -
 * **Throwing lambda expressions and functional interfaces that declare and throw
 * checked exceptions (since 2.0 throwing non-throwing lambdas are merged)**.
 */
//>inject<:readmore

//>inject<:generated

/**
 * Throwing lambda expressions and interfaces
 * ==========================
 *
 * ### Abstract
 *
 * Basic introduction (by example) to details of first of main goals -
 * **Throwing lambda expressions and functional interfaces that declare and throw
 * checked exceptions (since 2.0 throwing non-throwing lambdas are merged)**.
 */
public class Example_Goal1_Test {

    private static final List<Integer> integerList = Lists.newArrayList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9);

    /**
     * ### Introduction to the issue
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
     * But doing that in every place, sometimes if not always, asks for an better solution. The immediate effect of having less boilerplate code by replacing
     * anonymous classes by lambda expressions is drastically limited when checked expressions comes into the equation.
     * Of course you can always go with only using unchecked exceptions, but in most cases it is not up to you (e.g. Java JRE, or 3rd party libraries have
     * them anyway).
     *
     * ### Solution
     *
     * What this library introduces is actual functional interfaces that intercepts checked exception and wrap them to NestedException.
     *
     * So now you can have something like this:
     */
    //>example<
    @Test
    public void checkedExceptionPropagated() throws CheckedException {
        assertThatThrownBy(() -> {

            LPredicate<Integer> predicateX = i -> throwingAlways(i) != null;
            predicateX.test(10);

        })
                .isInstanceOf(NestedException.class)
                .hasCauseInstanceOf(CheckedException.class);
    }
    //>example<

    /**
     * Each such interface has some default and static methods that can help to use those interfaces. Here an example what you can do when you need JRE
     * Predicate that handles (wraps) the exceptions.
     */
    //>example<
    @Test
    public void standardPredicateWithExceptionWrapping_short() {

        long result = integerList.stream().filter(LPredicate.pred(i -> potentiallyThrowing(i) != null)).count();

        assertThat(result).isEqualTo(10);
    }
    //>example<

    /**
     * What happens is very simple. The **LPredicate.pred** method is actually only forcing compiler to use more specialized functional interface than the
     * JRE one. Each JRE functional interface (from _java.util.function_ package) is extended by appropriate functional interface from this library.
     * The library provides default implementation for the methods of the JRE interfaces with methods that handles the checked exception.
     *
     * If an checked exception will rise in place where this expression is used as normal Predicate then this wil happen:
     */
    @Test(expectedExceptions = NestedException.class, expectedExceptionsMessageRegExp = "\\QSomething went wrong\\E")
    public void standardPredicateWithExceptionWrapping2() {
        long result = integerList.stream().filter(LPredicate.pred(i -> throwingAlways(i) != null)).count();
    }

    /**
     * In case you need, you could also wrap existing JRE Predicate.
     */
    //>example<
    public static LPredicate<Integer> example(Predicate<Integer> predicateStd) {
        return LPredicate.wrap(predicateStd);
    }
    //>example<

    /**
     * ### Addition effects
     *
     * Here are additional examples of what you can do with additional methods:
     */

    //>example<
    @Test
    public void rethrowLikeRuntimeException() {
        byte[] input = new byte[0];

        LConsumer.cons((byte[] in) -> new ByteArrayInputStream(in).close()).shovingAccept(input);
    }

    @Test
    public void quickNestException() {
        byte[] input = new byte[0];

        LConsumer.cons((byte[] in) -> new ByteArrayInputStream(in).close()).nestingAccept(input);

        // or simply: 
        LConsumer.tryAccept(input, in -> new ByteArrayInputStream(in).close());
    }
    //>example<

    /**
     * When you write some utility that constantly do for example IO operations and is already defined by contract that it cannot declare checked exceptions.
     */
    //>example<
    @Test
    public void quickHandlingException() {
        byte[] input = new byte[0];

        LConsumer.handlingAccept(input, in -> new ByteArrayInputStream(in).close(), e -> e
                .wrapIf(IOException.class::isInstance, RuntimeException::new)
                .handleRest());
    }
    //>example<

    //>inject<:generated
}
