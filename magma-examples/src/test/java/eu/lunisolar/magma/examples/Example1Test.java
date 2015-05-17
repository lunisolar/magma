/*
 * (C) Copyright 2015 Lunisolar (http://lunisolar.eu/).
 *
 * This file is part of "lunisolar-magma".
 *
 * "lunisolar-magma" is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * "lunisolar-magma" is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with "lunisolar-magma".  If not, see <http://www.gnu.org/licenses/>.
 */

package eu.lunisolar.magma.examples;

import eu.lunisolar.magma.basics.NestedException;
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

        long result = integerList.stream().filter(predicateX.nonThrowing()).count();

        assertThat(result).isEqualTo(10);
    }

    public static LPredicateX<Integer, CheckedException> example(java.util.function.Predicate<Integer> predicateStd) {

        return LPredicateX.wrapStd(predicateStd);

    }

    @Test(expectedExceptions = NestedException.class, expectedExceptionsMessageRegExp = "\\QSomething went wrong\\E")
    public void standardPredicateWithExceptionWrapping2() {

        integerList.stream().filter(wrap(i -> throwingAlways(i) != null)).count();

    }
}
