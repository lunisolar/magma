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
 * Foobar is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with Foobar.  If not, see <http://www.gnu.org/licenses/>.
 */

package eu.lunisolar.magma.examples;

import eu.lunisolar.magma.func.asserts.FunctionalAssertions;
import eu.lunisolar.magma.func.function.Function;
import org.assertj.core.api.AbstractIntegerAssert;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.ObjectAssert;
import org.testng.annotations.Test;

public class Example2Test {

    public static final FunctionalAssertions<ObjectAssert> then = new FunctionalAssertions() {
    };

    private Function<Integer, Integer> function = (t) -> t;  // very complicated :)

    @Test(expectedExceptions = AssertionError.class)
    public void example() {
        then.assertThat(function)
            .recurringAsserts(a -> a.isInstanceOf(Integer.class))
            .doesApply(80).to(a -> a.isEqualTo(80))
            .doesApply(81).toEqualTo(81)
            .doesApply(0).withException(a -> a
                .isExactlyInstanceOf(UnsupportedOperationException.class)
                .hasMessage("Some message"));
    }

    @Test(expectedExceptions = AssertionError.class)
    public void example2() {
        then.withCodomainAssert(then::assertThatInt).assertThat(function)
            .recurringAsserts(a -> a.isInstanceOf(Integer.class))
            .doesApply(80).to(a -> a.isGreaterThan(0))
            .doesApply(81).toEqualTo(81)
            .doesApply(0).withException(a -> a
                .isExactlyInstanceOf(UnsupportedOperationException.class)
                .hasMessage("Some message"));
    }

    public void example3() {
        then.withCodomainAssert((Function<Integer, AbstractIntegerAssert>) Assertions::assertThat).assertThat(function)
            .recurringAsserts(a -> a.isInstanceOf(Integer.class))
            .doesApply(80).to(a -> a.isGreaterThan(0))
            .doesApply(81).toEqualTo(81)
            .doesApply(0).withException(a -> a
                .isExactlyInstanceOf(UnsupportedOperationException.class)
                .hasMessage("Some message"));
    }

    @Test(expectedExceptions = AssertionError.class)
    public void example4() {
        then.withCodomainAssert(Assertions::assertThat, Integer.class, AbstractIntegerAssert.class).assertThat(function)
            .recurringAsserts(a -> a.isInstanceOf(Integer.class))
            .doesApply(80).to(a -> a.isGreaterThan(0))
            .doesApply(81).toEqualTo(81)
            .doesApply(0).withException(a -> a
                .isExactlyInstanceOf(UnsupportedOperationException.class)
                .hasMessage("Some message"));
    }

    @Test(expectedExceptions = AssertionError.class)
    public void example5() {
        then.withCodomainAssert(Assertions::assertThat, int.class, AbstractIntegerAssert.class).assertThat(function)
            .recurringAsserts(a -> a.isInstanceOf(Integer.class))
            .doesApply(80).to(a -> a.isGreaterThan(0))
            .doesApply(81).toEqualTo(81)
            .doesApply(0).withException(a -> a
                .isExactlyInstanceOf(UnsupportedOperationException.class)
                .hasMessage("Some message"));
    }
}
