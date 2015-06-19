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
import eu.lunisolar.magma.func.predicate.LPredicateX;
import org.assertj.core.util.Lists;
import org.testng.annotations.Test;

import java.util.*;

import static eu.lunisolar.magma.func.predicate.LPredicate.wrap;
import static org.assertj.core.api.Assertions.assertThat;

public class Example1Test {

    private static final List<Integer> integerList = Lists.newArrayList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9);

    public static Integer potentiallyThrowing(Integer i) throws CheckedException {
        return i;
    }

    public static Integer throwingAlways(Integer i) throws CheckedException {
        throw new CheckedException("Something went wrong");
    }

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

    @Test
    public void standardPredicateWithExceptionWrapping_long() {

        eu.lunisolar.magma.func.predicate.LPredicateX<Integer, CheckedException> predicateX = i -> potentiallyThrowing(i) != null;
        eu.lunisolar.magma.func.predicate.LPredicate<Integer> predicate = eu.lunisolar.magma.func.predicate.LPredicate.wrap(predicateX);
        java.util.function.Predicate<Integer> predicateStd = predicate;
        long result = integerList.stream().filter(predicateStd).count();

        assertThat(result).isEqualTo(10);
    }

    @Test
    public void standardPredicateWithExceptionWrapping_short() {

        long result = integerList.stream().filter(wrap(i -> potentiallyThrowing(i) != null)).count();

        assertThat(result).isEqualTo(10);
    }

    @Test
    public void standardPredicateWithExceptionWrapping_short2() {

        LPredicateX<Integer, CheckedException> predicateX = i -> potentiallyThrowing(i) != null;

        long result = integerList.stream().filter(predicateX.nest()).count();

        assertThat(result).isEqualTo(10);
    }

    public static LPredicateX<Integer, CheckedException> example(java.util.function.Predicate<Integer> predicateStd) {

        return LPredicateX.wrap(predicateStd);

    }

    @Test(expectedExceptions = NestedException.class, expectedExceptionsMessageRegExp = "\\QSomething went wrong\\E")
    public void standardPredicateWithExceptionWrapping2() {

        integerList.stream().filter(wrap(i -> throwingAlways(i) != null)).count();

    }
}
