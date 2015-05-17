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

import eu.lunisolar.magma.func.Function4U;
import eu.lunisolar.magma.func.action.LAction;
import eu.lunisolar.magma.func.asserts.DefaultFunctionalAssertions;
import eu.lunisolar.magma.func.build.action.LActionBuilder;
import eu.lunisolar.magma.func.build.function.LFunctionBuilder;
import eu.lunisolar.magma.func.build.function.from.LShortFunctionBuilder;
import eu.lunisolar.magma.func.build.supplier.LIntSupplierBuilder;
import eu.lunisolar.magma.func.build.supplier.LSupplierBuilder;
import eu.lunisolar.magma.func.function.LFunction;
import eu.lunisolar.magma.func.function.from.LShortFunction;
import eu.lunisolar.magma.func.supplier.LIntSupplier;
import eu.lunisolar.magma.func.supplier.LSupplier;
import org.assertj.core.api.AbstractIntegerAssert;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.ObjectAssert;
import org.testng.annotations.Test;

import java.util.concurrent.atomic.*;

public class Example2Test {

    public static final DefaultFunctionalAssertions<ObjectAssert> then = new DefaultFunctionalAssertions() {
    };

    private LFunction<Integer, Integer> function = LFunctionBuilder.<Integer, Integer>function()  // a compilation test
            .inCase(i -> i <= 0).evaluate(i -> i)
            .inCase(i -> i > 0).evaluate(i -> i)
            .build();

    private LShortFunction<Integer> shortFunction = LShortFunctionBuilder.<Integer>shortFunction()  // a compilation test
            .inCase(i -> i <= 0).evaluate(i -> (int) i)
            .inCase(i -> i > 0).evaluate(i -> (int) i)
            .build();

    public static final AtomicInteger externalInfluence = new AtomicInteger(0);

    private LSupplier<Integer> supplier = LSupplierBuilder.<Integer>supplier()  // a compilation test
            .inCase(() -> externalInfluence.get() > 0).evaluate(() -> externalInfluence.get())
            .inCase(() -> false).produce(22)
            .eventuallyProduce(33)
            .build();

    private LIntSupplier intSupplier = LIntSupplierBuilder.intSupplier()  // a compilation test
            .inCase(() -> externalInfluence.get() > 0).evaluate(() -> externalInfluence.get())
            .inCase(() -> false).produce(22)
            .eventuallyProduce(33)
            .build();

    public static final AtomicInteger externalEffect = new AtomicInteger(0);

    private LAction action = LActionBuilder.action()  // a compilation test
            .inCase(() -> externalInfluence.get() > 0).evaluate(() -> externalInfluence.get())
            .inCase(() -> false).evaluate(Function4U::doNothing)
            .eventually(Function4U::doNothing)
            .build();

    @Test(expectedExceptions = AssertionError.class)
    public void example() {
        then.assertThat(function)
            .inAllFollowingCases(a -> a.isInstanceOf(Integer.class))
            .doesApply(80).to(a -> a.isEqualTo(80))
            .doesApply(81).toEqualTo(81)
            .doesApply(0).withException(a -> a
                .isExactlyInstanceOf(UnsupportedOperationException.class)
                .hasMessage("Some message"));
    }

    @Test(expectedExceptions = AssertionError.class)
    public void example2() {
        then.withinCodomain(then::assertThatInt).assertThat(function)
            .inAllFollowingCases(a -> a.isInstanceOf(Integer.class))
            .doesApply(80).to(a -> a.isGreaterThan(0))
            .doesApply(81).toEqualTo(81)
            .doesApply(0).withException(a -> a
                .isExactlyInstanceOf(UnsupportedOperationException.class)
                .hasMessage("Some message"));
    }

    public void example3() {
        then.withinCodomain((LFunction<Integer, AbstractIntegerAssert>) Assertions::assertThat).assertThat(function)
            .inAllFollowingCases(a -> a.isInstanceOf(Integer.class))
            .doesApply(80).to(a -> a.isGreaterThan(0))
            .doesApply(81).toEqualTo(81)
            .doesApply(0).withException(a -> a
                .isExactlyInstanceOf(UnsupportedOperationException.class)
                .hasMessage("Some message"));
    }

    @Test(expectedExceptions = AssertionError.class)
    public void example4() {
        then.withinCodomain(Assertions::assertThat, Integer.class, AbstractIntegerAssert.class).assertThat(function)
            .inAllFollowingCases(a -> a.isInstanceOf(Integer.class))
            .doesApply(80).to(a -> a.isGreaterThan(0))
            .doesApply(81).toEqualTo(81)
            .doesApply(0).withException(a -> a
                .isExactlyInstanceOf(UnsupportedOperationException.class)
                .hasMessage("Some message"));
    }

    @Test(expectedExceptions = AssertionError.class)
    public void example5() {
        then.withinCodomain(Assertions::assertThat, int.class, AbstractIntegerAssert.class).assertThat(function)
            .inAllFollowingCases(a -> a.isInstanceOf(Integer.class))
            .doesApply(80).to(a -> a.isGreaterThan(0))
            .doesApply(81).toEqualTo(81)
            .doesApply(0).withException(a -> a
                .isExactlyInstanceOf(UnsupportedOperationException.class)
                .hasMessage("Some message"));
    }

    @Test(expectedExceptions = AssertionError.class)
    public void example6() {
        then.withinIntegerCodomain().assertThat(function)
            .inAllFollowingCases(a -> a.isInstanceOf(Integer.class))
            .doesApply(80).to(a -> a.isGreaterThan(0))
            .doesApply(81).toEqualTo(81)
            .doesApply(0).withException(a -> a
                .isExactlyInstanceOf(UnsupportedOperationException.class)
                .hasMessage("Some message"));
    }

    @Test(expectedExceptions = AssertionError.class)
    public void example7() {
        then.withinIntegerCodomain().assertThat(intSupplier)
            .inAllFollowingCases(a -> a.isInstanceOf(Integer.class))
            .doesGetAsInt().as(a -> a.isGreaterThan(0))
            .doesReturn(81)
            .doesGetAsInt().withException(a -> a
                .isExactlyInstanceOf(UnsupportedOperationException.class)
                .hasMessage("Some message"));
    }

    @Test(expectedExceptions = AssertionError.class)
    public void example8() {
        then.withinIntegerCodomain().assertThat(shortFunction)
            .inAllFollowingCases(a -> a.isInstanceOf(Integer.class))
            .doesApply((short) 80).to(a -> a.isGreaterThan(0))
            .doesApply((short) 81).toEqualTo(81)
            .doesApply((short) 0).withException(a -> a
                .isExactlyInstanceOf(UnsupportedOperationException.class)
                .hasMessage("Some message"));
    }

}
