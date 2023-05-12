/*
 * This file is part of "lunisolar-magma".
 *
 * (C) Copyright 2014-2023 Lunisolar (http://lunisolar.eu/).
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
import eu.lunisolar.magma.examples.support.DifferentRuntimeException;
import eu.lunisolar.magma.examples.support.DifferentSpecializedRuntimeException;
import eu.lunisolar.magma.examples.support.SomeRuntimeExcepton;
import eu.lunisolar.magma.func.consumer.LConsumer;
import eu.lunisolar.magma.func.consumer.primitives.LLongConsumer;
import eu.lunisolar.magma.func.function.LFunction;
import eu.lunisolar.magma.func.supp.Be;
import eu.lunisolar.magma.func.supp.Has;
import eu.lunisolar.magma.func.supp.Is;
import eu.lunisolar.magma.func.supp.check.Checks;
import org.testng.annotations.Test;

import java.io.*;
import java.text.*;
import java.util.function.*;

import static eu.lunisolar.magma.func.predicate.LPredicate.pred;
import static eu.lunisolar.magma.func.supp.check.Checks.attest;

//>transform-to-MD<

/**
 * Basic introduction (by example) to exception handling with functional interfaces from this library.
 */
//>inject<:readmore

//>inject<:generated

/**
 * Exception Handling
 * ==============================
 *
 * ### Abstract
 *
 * Basic introduction (by example) to exception handling with functional interfaces from this library.
 */
public class Example_ExceptionHandling_Test {

///### Handling
///Each functional interface has the ability to customise exception handling. There are more than few methods to do that. It might be best to present them
///on one single example:
///<a href="https://github.com/lunisolar/magma/blob/master/magma-func/src/main/java/eu/lunisolar/magma/func/function/LFunction.java" target="_blank">LFunction</a>.
///
///#### Default behaviour
///
///First, the actual method that need to be implemented is throwing Throwable:
///
///`R applyX(T a) throws Throwable;`
///
///This method is not intended to be used directly, because there is less exotic method to be called:
///
///`default R apply(T a) { ... }`
///
///This is what this method does:
///1. Intercepts Throwable
///2. Immediately rethrows errors.
///3. Rethrows runtime exceptions.
///4. Nests checked exceptions in <a href="https://github.com/lunisolar/magma/blob/master/magma-basics/src/main/java/eu/lunisolar/magma/basics/exceptions/NestedException.java" target="_blank">NestedException</a>.
///
///#### Handling customization
///
///Following possibilities can only be used for runtime exceptions and checked exceptions:
///
///1. There is -handlingApply- method that additionally takes argument HandlingInstructions. See <a href="https://github.com/lunisolar/magma/blob/master/magma-basics/src/main/java/eu/lunisolar/magma/basics/exceptions/HandlingInstructions.java" target="_blank">HandlingInstructions</a>
///   and <a href="https://github.com/lunisolar/magma/blob/master/magma-basics/src/main/java/eu/lunisolar/magma/basics/exceptions/Handler.java" target="_blank">Handler</a>
///1. There two -apply- methods that additionally takes reference to exception constructor with option to pass message arguments.
///1. There is -applyThen- that takes function that is supposed to transform exception into the originally expected value type.
///
///So, in general there are 3 possibilities to handle exceptions:
///
///1. Instance methods that is just 'apply' with additional handling. To handle exceptions on per call basis.
///1. Instance methods that returns function that always does the exact handling every time. To create function that handles exceptions for all calls.
///1. Static method that takes function arguments, function instance, and handling argument.
///

    /**
     * ### Examples
     *
     * Probably the most quick use of exception handling, provided that situation does not require actual handling of exception (and propagation of nested
     * exception is enough) would be:
     */
    //>example<
    @Test
    public void oneLinePropagation() {

        LLongConsumer.tryAccept(300, Thread::sleep);

    }
    //>example<

    /**
     * For further examples lets confider a method and/or function that throw checked exceptions:
     */

    //>example<

    public static final LFunction<Integer, Integer> throwingAlways = LFunction.func(Example_ExceptionHandling_Test::throwingAlways);

    public static Integer throwingAlways(Integer i) throws CheckedException {
        throw new CheckedException("Something went wrong");
    }

    //>example<

    /**
     * And here is an example with handling instructions:
     */
    //>example<
    @Test(expectedExceptions = DifferentRuntimeException.class)
    public void exampleHandling() {

        LFunction<Integer, Integer> function1 = throwingAlways.handling(h -> h
                .wrapIf(e -> e instanceof RuntimeException, RuntimeException::new)
                .wrapIf(pred(Has::cause).and(Is::notRuntime), DifferentSpecializedRuntimeException::new)
                .wrap(DifferentRuntimeException::new)
        );

        function1.apply(0);
    }
    //>example<

    /**
     * Handling block can be empty. If so, then Nested exception will be produced for any exception except unchecked exceptions that can be freely propagated.
     */
    //>example<
    @Test(expectedExceptions = NestedException.class)
    public void exampleNoHandlingAtAll() {

        LFunction<Integer, Integer> function = throwingAlways.handling(h -> {
        });

        function.apply(0);
    }
    //>example<

    /**
     * A more simpler examples:
     */
    //>example<
    @Test(expectedExceptions = DifferentRuntimeException.class)
    public void simpleExample() throws DifferentRuntimeException {

        throwingAlways.apply(0, DifferentRuntimeException::new);

    }

    @Test(expectedExceptions = DifferentRuntimeException.class, expectedExceptionsMessageRegExp = "message text")
    public void simpleExampleWithMessage() throws DifferentRuntimeException {

        throwingAlways.apply(0, DifferentRuntimeException::new, "message text");

    }
    //>example<

    /**
     * Following examples shows the purpose of the static handling methods - one-line handling for any method call (assuming that there is a library function
     * that covers the specific argument case).
     */
    //>example<
    @Test(expectedExceptions = NestedException.class)
    public void staticExamples() {
        LFunction.tryApply(0, Example_ExceptionHandling_Test::throwingAlways);
    }

    @Test(expectedExceptions = DifferentRuntimeException.class, expectedExceptionsMessageRegExp = "text message and param")
    public void staticExamples2() {
        LFunction.tryApply(0, Example_ExceptionHandling_Test::throwingAlways, DifferentRuntimeException::new, "text message and %s", "param");
    }
    //>example<

    // <editor-fold desc="simple">

    @Test(expectedExceptions = NestedException.class)
    public java.util.function.Function<Integer, Integer> example1() {

        attest(throwingAlways).mustEx(Be::instanceOfEx, Function.class);

        return throwingAlways;
    }

    @Test(expectedExceptions = NestedException.class)
    public LFunction<Integer, Integer> example_nest() {
        throwingAlways.nestingApply(0);   // TODO this seems not needed since it is default behaviour!

        return throwingAlways;    // TODO better documentation description
    }

    @Test(expectedExceptions = CheckedException.class)
    public LFunction<Integer, Integer> example_shove() {
        throwingAlways.shovingApply(0);

        //return throwingAlways.shovingFunc();  // TODO no longer exists
        return null;
    }

    @Test(expectedExceptions = SomeRuntimeExcepton.class)
    public void example4() throws Throwable {
        throwingAlways
                .handling(h -> h.wrap(SomeRuntimeExcepton::new))
                .apply(0);  // <- exception type was generalized to Exception
    }

    @Test(expectedExceptions = SomeRuntimeExcepton.class)
    public void example5() {
        throwingAlways
                .handling(h -> h.wrap(SomeRuntimeExcepton::new))
                .apply(0);
    }

    @Test(expectedExceptions = NestedException.class)
    public void example6() throws ParseException {
        LFunction<Integer, Integer> functionX = throwingAlways.handling((h) -> h
                .wrapIf(RuntimeException.class::isInstance, SomeRuntimeExcepton::new)
        );

        functionX.apply(0);
    }

    @Test(expectedExceptions = NestedException.class)
    public void example7_1() {
        throwingAlways.handlingApply(0, h -> h
                .wrapIf(RuntimeException.class::isInstance, SomeRuntimeExcepton::new)

        );
    }

    @Test(expectedExceptions = SomeRuntimeExcepton.class)
    public void example7_2() {
        throwingAlways.handlingApply(0, h -> h
                .wrapIf(Is::notRuntime, SomeRuntimeExcepton::new)
        );
    }

    // </editor-fold>

    //>inject<:generated

}
