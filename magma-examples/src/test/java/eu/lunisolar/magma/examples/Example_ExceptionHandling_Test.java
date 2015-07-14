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
import eu.lunisolar.magma.basics.probing.ThrowableProbe;
import eu.lunisolar.magma.examples.support.CheckedException;
import eu.lunisolar.magma.examples.support.DifferentCheckedException;
import eu.lunisolar.magma.examples.support.DifferentSpecializedCheckedException;
import eu.lunisolar.magma.examples.support.SmeRuntimeExcepton;
import eu.lunisolar.magma.func.Function4U;
import eu.lunisolar.magma.func.function.LFunction;
import eu.lunisolar.magma.func.function.LFunctionX;
import org.testng.annotations.Test;

import java.text.*;
import java.util.function.*;

import static org.assertj.core.api.Assertions.assertThat;

//>transform-to-MD<

/**
 * Example and instruction how to use exception handling functionality for functional interfaces.
 */
//>inject<:readmore

/**
 * Exception Handling
 * ==============================
 */
public class Example_ExceptionHandling_Test {

    public static final LFunctionX<Integer, Integer, CheckedException> throwingAlways = LFunctionX.lX(
            (LFunctionX<Integer, Integer, CheckedException>) Example_ExceptionHandling_Test::throwingAlways);

    public static Integer throwingAlways(Integer i) throws CheckedException {
        throw new CheckedException("Something went wrong");
    }

    /**
     * Each functional interface has the ability to customise exception handling. There are two methods that do exactly that:
     *
     * - *handleX* - the result function is of a _throwing_ type.
     * - *handle* - the result function is of a _non-throwing_ type.
     *
     * Both of them have only one argument (*HandlingInstructions*) that provides instructions how to handle the exception. Mind that exception is already
     * caught at this point so traditional try-catch is out of the question (there is nothing that blocks the user from using try-catch just like for JRE
     * interfaces anyway). *HandlingInstructions* is nothing more than specialized consumer for the *Handler* object. *Handler* contains few conditional
     * and few unconditional methods. In most cases a _factory_ for a new exception must be provided. Behind the _factory_ are couple of functional interfaces
     * that corresponds the new usual Throwable constructor methods - so that following options can be used:
     *
     * - use constructor reference
     * - use lambda expression that instantiates the exception (mostly usable in cases when exception has some custom constructor).
     * - use lambda expression that explicitly throws an exception.
     * - use some factory object that implements the factory interface.
     * - in all cases factory is obligated to either throw exception or return it.
     *
     * Whenever exception is not processed by the handler it will be propagated (unchecked exception) or nested (checked exception). Errors are propagated
     * immediately.
     */
    //>example<
    @Test(expectedExceptions = SmeRuntimeExcepton.class)
    public void exampleHandling() {

        LFunctionX<Integer, Integer, RuntimeException> function1 = throwingAlways.handleFuncX(h -> h
                        .wrapWhen(Function4U::alwaysTrue, (message, e) -> {
                            e.printStackTrace();
                            throw new SmeRuntimeExcepton(message);
                        }, "message")
                        .wrapWhen(ThrowableProbe::isRuntime, (message, e) -> new SmeRuntimeExcepton(message), "message")
                        .wrapWhen(ThrowableProbe::isRuntime, SmeRuntimeExcepton::new, "message")
        );

        LFunction<Integer, Integer> function2 = throwingAlways.handleFunc(h -> h
                        .wrapWhen(ThrowableProbe::isRuntime, SmeRuntimeExcepton::new)
        );

        Function<Integer, Integer> function3 = throwingAlways.handleFunc(h -> h
                        .wrapWhen(ThrowableProbe::isRuntime, SmeRuntimeExcepton::new)
        );

        function1.doApply(0);
    }
    //>example<

    /**
     * Handling block can be empty. If so then Nested exception will be produced for any exception except unchecked exceptions that can be freely propagated.
     */
    //>Example<
    @Test(expectedExceptions = NestedException.class)
    public void exampleNoHandlingAtAll() {

        LFunctionX<Integer, Integer, RuntimeException> function = throwingAlways.handleFuncX(h -> {
        });

        function.doApply(0);
    }
    //>Example<

    /**
     * You can do also simple or conditional wrapping to another checked exception. However you need to help the compiler a little to understand what kind of
     * exceptions the result function is throwing regardless of what is in the handling block. It would not infer the new function X generic type from within
     * the handling block.
     */
    //>example<
    @Test(expectedExceptions = DifferentCheckedException.class)
    public void exampleWrapToDifferentChecked() throws DifferentCheckedException {

        LFunctionX<Integer, Integer, DifferentCheckedException> function = throwingAlways.handleFuncX(h -> h
                        .throwWrapper(DifferentCheckedException::new)
        );

        function.doApply(0);

        throwingAlways.<DifferentCheckedException>handleFuncX(h -> h
                        .throwWrapper(DifferentCheckedException::new)
        ).doApply(0);
    }
    //>example<

    /**
     * You can do handling on the fly this also on the fly.
     */
    //>example<
    @Test(expectedExceptions = DifferentCheckedException.class)
    public void exampleWrapToDifferentCheckedOnTheFly() throws DifferentCheckedException {

        throwingAlways.<DifferentCheckedException>handleFuncX(h -> h
                        .throwWrapper(DifferentCheckedException::new)
        ).doApply(0);
    }
    //>example<

    /**
     * Each method of an handler has its own definition of exception that will/could be thrown. As an effect unchecked exception could be used freely and the
     * checked one
     * will be checked by the compiler.
     *
     * There are also two sets of conditional methods.
     * - one is using simple predicates on exception - Predicate<X>
     * - the other is using predicated on the ThrowableProbe instance that has little more convenient methods - Predicate<ThrowableProbe<X>>
     */
    //>example<
    @Test(expectedExceptions = DifferentCheckedException.class)
    public void exampleWrapToDifferentCheckedDifferent() throws DifferentCheckedException {

        throwingAlways.<DifferentCheckedException>handleFuncX(h -> h
                        .wrapIf(e -> e instanceof RuntimeException, RuntimeException::new)
                        .wrapWhen(p -> p.hasCause() && p.isNotRuntime(), DifferentSpecializedCheckedException::new)
                        .throwWrapper(DifferentCheckedException::new)
        ).doApply(0);
    }
    //>example<

    // <editor-fold desc="simple">

    @Test(expectedExceptions = NestedException.class)
    public java.util.function.Function<Integer, Integer> example1() {

        assertThat(throwingAlways).isInstanceOf(Function.class);

        return throwingAlways;
    }

    @Test(expectedExceptions = NestedException.class)
    public LFunction<Integer, Integer> example_nest() {
        throwingAlways.nestingFunc().apply(0);

        return throwingAlways.nestingFunc();
    }

    @Test(expectedExceptions = NestedException.class)
    public LFunction<Integer, Integer> example_nestX() {
        throwingAlways.nestingFunc().apply(0);

        return throwingAlways.nestingFunc();
    }

    @Test(expectedExceptions = CheckedException.class)
    public LFunction<Integer, Integer> example_shove() {
        throwingAlways.shovingFunc().doApply(0);

        return throwingAlways.shovingFunc();
    }

    @Test(expectedExceptions = CheckedException.class)
    public LFunctionX<Integer, Integer, RuntimeException> example_shoveX() {
        throwingAlways.shovingFuncX().apply(0);

        return throwingAlways.shovingFuncX();
    }

    @Test(expectedExceptions = SmeRuntimeExcepton.class)
    public void example4() throws Throwable {
        throwingAlways
                .handleFuncX(h -> h.throwWrapper(SmeRuntimeExcepton::new))
                .doApply(0);  // <- exception type was generalized to Exception
    }

    @Test(expectedExceptions = SmeRuntimeExcepton.class)
    public void example5() {
        throwingAlways
                .handleFunc(h -> h.throwWrapper(SmeRuntimeExcepton::new))
                .doApply(0);
    }

    @Test(expectedExceptions = NestedException.class)
    public void example6() throws ParseException {
        LFunctionX<Integer, Integer, ParseException> functionX = throwingAlways.handleFuncX((h) -> h
                        .wrapIf(RuntimeException.class::isInstance, SmeRuntimeExcepton::new)
        );

        functionX.doApply(0);
    }

    @Test(expectedExceptions = NestedException.class)
    public void example7_1() {
        throwingAlways.handlingDoApply(0, h -> h
                        .wrapIf(RuntimeException.class::isInstance, SmeRuntimeExcepton::new)

        );
    }

    @Test(expectedExceptions = SmeRuntimeExcepton.class)
    public void example7_2() {
        throwingAlways.handlingDoApply(0, h -> h
                        .wrapWhen(p -> !p.isRuntime(), SmeRuntimeExcepton::new)
        );
    }

    // </editor-fold>

//>inject<:buildId

}
