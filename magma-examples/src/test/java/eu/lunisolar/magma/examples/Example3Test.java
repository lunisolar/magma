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
import eu.lunisolar.magma.func.asserts.DefaultFunctionalAssertions;
import eu.lunisolar.magma.func.asserts.FunctionalAssertions;
import eu.lunisolar.magma.func.function.Function;
import eu.lunisolar.magma.func.function.FunctionX;
import org.assertj.core.api.ObjectAssert;
import org.testng.annotations.Test;

import java.text.*;

public class Example3Test {

    public static final DefaultFunctionalAssertions<ObjectAssert> then = new DefaultFunctionalAssertions() {
    };

    public static final FunctionX<Integer, Integer, CheckedException> throwingAlways = FunctionX.lX(Example3Test::throwingAlways);

    public static Integer potentiallyThrowing(Integer i) throws CheckedException {
        return i;
    }

    public static Integer throwingAlways(Integer i) throws CheckedException {
        throw new CheckedException("Something went wrong");
    }

    @Test(expectedExceptions = NestedException.class)
    public java.util.function.Function<Integer, Integer> example1() {
        throwingAlways.std().apply(0);

        return throwingAlways.std();
    }

    @Test(expectedExceptions = NestedException.class)
    public Function<Integer, Integer> example2() {
        throwingAlways.nonThrowing().apply(0);

        return throwingAlways.nonThrowing();
    }

    @Test(expectedExceptions = NestedException.class)
    public FunctionX<Integer, Integer, RuntimeException> example3() {
        throwingAlways.uncheck().apply(0);

        return throwingAlways.uncheck();
    }

    @Test(expectedExceptions = CheckedException.class)
    public Function<Integer, Integer> example_dirty() {
        throwingAlways.shove().apply(0);

        return throwingAlways.shove();
    }

    @Test(expectedExceptions = UnsupportedOperationException.class)
    public void example4() throws Exception {
        throwingAlways
                .handleX((e) -> {
                    throw new UnsupportedOperationException(e);  // <- cannot infer exception for handle()
                })
                .apply(0);  // <- exception type was generalized to Exception
    }

    @Test(expectedExceptions = UnsupportedOperationException.class)
    public void example5() {
        throwingAlways
                .handleX((e) -> new UnsupportedOperationException(e))
                .apply(0);
    }

    @Test(expectedExceptions = NestedException.class)
    public void example6() {
        // Only RuntimeException will be handled.
        throwingAlways
                .handleX(RuntimeException.class, (e) -> new UnsupportedOperationException(e))
                .apply(0);
    }

}
