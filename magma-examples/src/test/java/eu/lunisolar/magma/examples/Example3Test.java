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
import eu.lunisolar.magma.func.asserts.DefaultFunctionalAssertions;
import eu.lunisolar.magma.func.function.LFunction;
import eu.lunisolar.magma.func.function.LFunctionX;
import org.assertj.core.api.ObjectAssert;
import org.testng.annotations.Test;

import java.text.*;
import java.util.function.*;

import static org.assertj.core.api.Assertions.assertThat;

public class Example3Test {

    public static final DefaultFunctionalAssertions<ObjectAssert> then = new DefaultFunctionalAssertions() {
    };

    public static final LFunctionX<Integer, Integer, CheckedException> throwingAlways = LFunctionX.lX(Example3Test::throwingAlways);

    public static Integer potentiallyThrowing(Integer i) throws CheckedException {
        return i;
    }

    public static Integer throwingAlways(Integer i) throws CheckedException {
        throw new CheckedException("Something went wrong");
    }

    @Test(expectedExceptions = NestedException.class)
    public java.util.function.Function<Integer, Integer> example1() {

        assertThat(throwingAlways).isInstanceOf(Function.class);

        return throwingAlways;
    }

    @Test(expectedExceptions = NestedException.class)
    public LFunction<Integer, Integer> example_nest() {
        throwingAlways.nest().apply(0);

        return throwingAlways.nest();
    }

    @Test(expectedExceptions = NestedException.class)
    public LFunction<Integer, Integer> example_nestX() {
        throwingAlways.nestX().apply(0);

        return throwingAlways.nest();
    }

    @Test(expectedExceptions = CheckedException.class)
    public LFunction<Integer, Integer> example_shove() {
        throwingAlways.shove().doApply(0);

        return throwingAlways.shove();
    }

    @Test(expectedExceptions = CheckedException.class)
    public LFunctionX<Integer, Integer, RuntimeException> example_shoveX() {
        throwingAlways.shoveX().apply(0);

        return throwingAlways.shoveX();
    }

    @Test(expectedExceptions = UnsupportedOperationException.class)
    public void example4() throws Throwable {
        throwingAlways
                .handleX((h) -> h.throwWrapper(UnsupportedOperationException::new))
                .doApply(0);  // <- exception type was generalized to Exception
    }

    @Test(expectedExceptions = UnsupportedOperationException.class)
    public void example5() {
        throwingAlways
                .handle((h) -> h.throwWrapper(UnsupportedOperationException::new))
                .doApply(0);
    }

    @Test(expectedExceptions = NestedException.class)
    public void example6() throws ParseException {
        LFunctionX<Integer, Integer, ParseException> functionX = throwingAlways.handleX((h) -> h
                        .wrapIf(RuntimeException.class::isInstance, UnsupportedOperationException::new)
        );

        functionX.doApply(0);
    }

    @Test(expectedExceptions = NestedException.class)
    public void example7_1() {
        // Only RuntimeException will be handled.
        throwingAlways.handlingDoApply(0, (h) -> h
                        .wrapIf(RuntimeException.class::isInstance, UnsupportedOperationException::new)

        );
    }

    @Test(expectedExceptions = UnsupportedOperationException.class)
    public void example7_2() {
        // Only RuntimeException will be handled.
        throwingAlways.handlingDoApply(0, (h) -> h
                        .wrapWhen(p -> !p.isRuntime(), UnsupportedOperationException::new)
        );
    }

//    @Test(expectedExceptions = NestedException.class)
//    public void example7() {
//
//        //TODO
//
//
//
//        // Only RuntimeException will be handled.
//        throwingAlways
//                .handleX_(()->-9)
//                .doApply(0);
//    }

}
