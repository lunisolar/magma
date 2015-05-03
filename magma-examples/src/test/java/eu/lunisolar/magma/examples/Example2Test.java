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

import eu.lunisolar.magma.func.asserts.DefaultFunctionalAssertions;
import eu.lunisolar.magma.func.build.function.FunctionBuilder;
import eu.lunisolar.magma.func.build.supplier.IntSupplierBuilder;
import eu.lunisolar.magma.func.build.supplier.SupplierBuilder;
import eu.lunisolar.magma.func.function.Function;
import eu.lunisolar.magma.func.supplier.IntSupplier;
import eu.lunisolar.magma.func.supplier.Supplier;
import org.assertj.core.api.AbstractIntegerAssert;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.ObjectAssert;
import org.testng.annotations.Test;

import java.util.concurrent.atomic.*;

public class Example2Test {

    public static final DefaultFunctionalAssertions<ObjectAssert> then = new DefaultFunctionalAssertions() {
    };

    private Function<Integer, Integer> function = FunctionBuilder.<Integer, Integer>function()  // a compilation test
            .inCase(i -> i <= 0).evaluate(i -> i)
            .inCase(i -> i > 0).evaluate(i -> i)
                    //TODO: .inCase(34)   ??????
            .build();

    public static final AtomicInteger externalInfluence = new AtomicInteger(0);

    private Supplier<Integer> supplier = SupplierBuilder.<Integer>supplier()  // a compilation test
            .inCase(() -> externalInfluence.get() > 0).evaluate(() -> externalInfluence.get())
            .inCase(() -> false).produce(22)
            .eventuallyProduce(33)
            .build();

    private IntSupplier intSupplier = IntSupplierBuilder.intSupplier()  // a compilation test
            .inCase(() -> externalInfluence.get() > 0).evaluate(() -> externalInfluence.get())
            .inCase(() -> false).produce(22)
            .eventuallyProduce(33)
            .build();

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
        then.withinCodomain(then::assertThatInt).assertThat(function)
            .recurringAsserts(a -> a.isInstanceOf(Integer.class))
            .doesApply(80).to(a -> a.isGreaterThan(0))
            .doesApply(81).toEqualTo(81)
            .doesApply(0).withException(a -> a
                .isExactlyInstanceOf(UnsupportedOperationException.class)
                .hasMessage("Some message"));
    }

    public void example3() {
        then.withinCodomain((Function<Integer, AbstractIntegerAssert>) Assertions::assertThat).assertThat(function)
            .recurringAsserts(a -> a.isInstanceOf(Integer.class))
            .doesApply(80).to(a -> a.isGreaterThan(0))
            .doesApply(81).toEqualTo(81)
            .doesApply(0).withException(a -> a
                .isExactlyInstanceOf(UnsupportedOperationException.class)
                .hasMessage("Some message"));
    }

    @Test(expectedExceptions = AssertionError.class)
    public void example4() {
        then.withinCodomain(Assertions::assertThat, Integer.class, AbstractIntegerAssert.class).assertThat(function)
            .recurringAsserts(a -> a.isInstanceOf(Integer.class))
            .doesApply(80).to(a -> a.isGreaterThan(0))
            .doesApply(81).toEqualTo(81)
            .doesApply(0).withException(a -> a
                .isExactlyInstanceOf(UnsupportedOperationException.class)
                .hasMessage("Some message"));
    }

    @Test(expectedExceptions = AssertionError.class)
    public void example5() {
        then.withinCodomain(Assertions::assertThat, int.class, AbstractIntegerAssert.class).assertThat(function)
            .recurringAsserts(a -> a.isInstanceOf(Integer.class))
            .doesApply(80).to(a -> a.isGreaterThan(0))
            .doesApply(81).toEqualTo(81)
            .doesApply(0).withException(a -> a
                .isExactlyInstanceOf(UnsupportedOperationException.class)
                .hasMessage("Some message"));
    }

    @Test(expectedExceptions = AssertionError.class)
    public void example6() {
        then.withinIntegerCodomain().assertThat(function)
            .recurringAsserts(a -> a.isInstanceOf(Integer.class))
            .doesApply(80).to(a -> a.isGreaterThan(0))
            .doesApply(81).toEqualTo(81)
            .doesApply(0).withException(a -> a
                .isExactlyInstanceOf(UnsupportedOperationException.class)
                .hasMessage("Some message"));
    }
}
