/*
 * This file is part of "lunisolar-magma".
 *
 * (C) Copyright 2014-2016 Lunisolar (http://lunisolar.eu/).
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

package eu.lunisolar.magma.basics.exceptions;

import eu.lunisolar.magma.basics.fluent.Fluent;
import eu.lunisolar.magma.basics.probing.Probe;
import eu.lunisolar.magma.basics.probing.ThrowableProbe;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.NotThreadSafe;
import java.util.function.*;

import static eu.lunisolar.magma.basics.exceptions.Handling.*;
import static java.util.Objects.*;

/**
 * It should never be treated as replacement for TRY-CATCH. It is intended to be a sugar in places where generic exception are being handled.
 */
@SuppressWarnings({"ThrowableResultOfMethodCallIgnored", "unchecked", "unused"})
@NotThreadSafe
public interface Handler<SELF extends Handler<SELF, X, Y>, X extends Throwable, Y extends Throwable> extends Fluent<SELF>, ThrowableProbe<X> {

    static <X extends Throwable, Y extends Throwable> Handler.The<X, Y> handler(@Nonnull X throwable) {
        return new The(throwable);
    }

    /**
     * Executes instructions, if none of them wil throw or rethrow exception this method will fail with exception that throwable was not handled.
     * Errors will be rethrow immediately.
     *
     * @return There is nothing ever returned - however return value of method signature can be used in statement: _throw handleOrFail(..)_
     */
    static <X extends Throwable, Y extends Throwable> RuntimeException handleOrFail(
            @Nonnull X throwable, @Nullable HandlingInstructions<X, Y> instructions) throws Y {

        handleErrors(throwable);

        if (instructions != null) {
            handleInstructions(throwable, instructions);
        }

        throwFailure(throwable);
        throw shouldNeverBeenHere();
    }

    /**
     * Executes instructions, if none of them wil throw or rethrow exception this method will rethrow RuntimeExceptions and nest checked exceptions.
     * Errors will be rethrow immediately.
     *
     * @return There is nothing ever returned - however return value of method signature can be used in statement: _throw handleOrFail(..)_
     */
    static <X extends Throwable, Y extends Throwable> RuntimeException handleOrNest(
            @Nonnull X throwable, @Nullable HandlingInstructions<X, Y> instructions) throws Y {

        handleErrors(throwable);

        if (instructions != null) {
            handleInstructions(throwable, instructions);
        }

        nestCheckedAndThrow(throwable);
        throw shouldNeverBeenHere();
    }

    /**
     * Executes instructions, if none of them wil throw or rethrow exception this method will rethrow original exception regardless it is checked or not.
     * Errors will be rethrow immediately.
     *
     * @return There is nothing ever returned - however return value of method signature can be used in statement: _throw handleOrFail(..)_
     */
    static <X extends Throwable, Y extends Throwable> RuntimeException handleOrPropagate(
            @Nonnull X throwable, @Nullable HandlingInstructions<X, Y> instructions) throws Y {

        handleErrors(throwable);

        if (instructions != null) {
            handleInstructions(throwable, instructions);
        }

        throw shoveIt(throwable);
    }

    default <Z extends Throwable> SELF throwIf(Class<Z> yClass) throws Z {
        X throwable = target();
        if (yClass.isInstance(throwable)) {
            throw yClass.cast(throwable);
        }

        return self();
    }

    default <Z extends Throwable> SELF replaceIf(
            @Nonnull Predicate<X> condition, @Nonnull ExceptionWithMessageFactory<Z> factory,
            @Nonnull String newMessage, @Nullable Object... messageParams) throws Z {
        Handling.throwReplacementIf(condition, target(), factory, newMessage, messageParams);
        return self();
    }

    default <Z extends Throwable> SELF wrapIf(@Nonnull Predicate<X> condition, @Nonnull ExceptionWrapFactory<Z> factory) throws Z {
        Handling.throwWrapperIf(condition, target(), factory);
        return self();
    }

    default <Z extends Throwable> SELF wrapIf(
            @Nonnull Predicate<X> condition, @Nonnull ExceptionWrapWithMessageFactory<Z> factory,
            @Nonnull String newMessage, @Nullable Object... messageParams) throws Z {
        Handling.throwWrapperIf(condition, nonNullTarget(), factory, newMessage, messageParams);
        return self();
    }

    default <Z extends Throwable> SELF replaceWhen(
            @Nonnull Predicate<ThrowableProbe<X>> condition, @Nonnull ExceptionWithMessageFactory<Z> factory,
            @Nonnull String newMessage, @Nullable Object... messageParams) throws Z {

        Handling.throwReplacementIf(condition.test(this), factory, newMessage, messageParams);
        return self();
    }

    default <Z extends Throwable> SELF wrapWhen(@Nonnull Predicate<ThrowableProbe<X>> condition, @Nonnull ExceptionWrapFactory<Z> factory) throws Z {
        Handling.throwWrapperIf(condition.test(this), nonNullTarget(), factory);
        return self();
    }

    default <Z extends Throwable> SELF wrapWhen(
            @Nonnull Predicate<ThrowableProbe<X>> condition, @Nonnull ExceptionWrapWithMessageFactory<Z> factory,
            @Nonnull String newMessage, @Nullable Object... messageParams) throws Z {

        Handling.throwWrapperIf(condition.test(this), nonNullTarget(), factory, newMessage, messageParams);
        return self();
    }

    default SELF nestIfChecked() {
        X throwable = target();
        if (throwable instanceof RuntimeException) {
            return self();
        } else if (throwable instanceof Error) {
            return self();
        } else {
            throw new NestedException(throwable);
        }
    }

    default <Z extends Throwable> void throwReplacement(
            @Nonnull ExceptionWithMessageFactory<Z> factory,
            @Nonnull String newMessage, @Nullable Object... messageParams) throws Z {
        throw Handling.throwReplacement(factory, newMessage, messageParams);
    }

    default <Z extends Throwable> void throwWrapper(@Nonnull ExceptionWrapFactory<Z> factory) throws Z {
        throw Handling.throwWrapper(nonNullTarget(), factory);
    }

    default <Z extends Throwable> void throwWrapper(
            @Nonnull ExceptionWrapWithMessageFactory<Z> factory,
            @Nonnull String newMessage, @Nullable Object... messageParams) throws Z {
        throw Handling.throwWrapper(nonNullTarget(), factory, newMessage, messageParams);
    }

    default void throwFailure() {
        throwFailure(target());
    }

    static void throwFailure(Throwable throwable) {
        throw new ExceptionNotHandled("Exception has not been handled.", throwable);
    }

    default void throwAsIs() throws X {
        throw nonNullTarget();
    }

    default void handleRest() {
        X throwable = target();
        nestCheckedAndThrow(throwable);
    }

    final class The<X extends Throwable, Y extends Throwable> extends Probe.Base<X> implements Handler<The<X, Y>, X, Y> {
        public The(@Nonnull X throwable) {
            super(requireNonNull(throwable));
        }
    }

}
