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
import eu.lunisolar.magma.func.function.LFunction;
import eu.lunisolar.magma.func.function.LFunctionX;
import org.assertj.core.api.ObjectAssert;
import org.testng.annotations.Test;

import java.util.function.*;

public class Example4Test {

    public static final DefaultFunctionalAssertions<ObjectAssert> then = new DefaultFunctionalAssertions() {
    };

    public static final LFunctionX<Integer, Integer, CheckedException> throwingAlways = LFunctionX.lX(Example4Test::throwingAlways);

    public static Integer potentiallyThrowing(Integer i) throws CheckedException {
        return i;
    }

    public static Integer throwingAlways(Integer i) throws CheckedException {
        throw new CheckedException("Something went wrong");
    }

    public Function<Integer, Integer> cc() {
        return throwingAlways::apply;
    }

    @Test
    public void example1() {

        Function<Integer, Integer> f1 =  throwingAlways.nest();
        Function<Integer, Integer> f2 =  throwingAlways.nest();

//       then.assertThat(f1)
//           .isSameAs(f2);
    }


}
