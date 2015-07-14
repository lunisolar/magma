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
import eu.lunisolar.magma.func.asserts.DefaultFunctionalAssertions;
import eu.lunisolar.magma.func.function.LFunctionX;
import org.assertj.core.api.ObjectAssert;
import org.testng.annotations.Test;

import java.util.function.*;

public class Example6Test {

    public static final DefaultFunctionalAssertions<ObjectAssert> then = new DefaultFunctionalAssertions() {
    };

    public static final LFunctionX<Integer, Integer, CheckedException> throwingAlways = LFunctionX.lX(Example6Test::throwingAlways);

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

        Function<Integer, Integer> f1 = throwingAlways.nestingFunc();
        Function<Integer, Integer> f2 = throwingAlways.nestingFunc();

//       then.assertThat(f1)
//           .isSameAs(f2);
    }

}
