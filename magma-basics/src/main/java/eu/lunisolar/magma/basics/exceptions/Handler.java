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

package eu.lunisolar.magma.basics.exceptions;

import eu.lunisolar.magma.basics.Null;
import eu.lunisolar.magma.basics.meta.SelfReferencing;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.NotThreadSafe;
import java.util.function.*;

import static eu.lunisolar.magma.basics.exceptions.Handling.*;

/**
 * It should never be treated as replacement for TRY-CATCH. It is intended to be a sugar in places where generic exception are being handled.
 */
@SuppressWarnings({"ThrowableResultOfMethodCallIgnored", "unchecked", "unused"})
@NotThreadSafe
public final class Handler<X extends Throwable> implements SelfReferencing<Handler<X>> {

    private final X e;

    public Handler(@Nonnull X e) {
        Null.nonNullArg(e, "e");
        this.e = e;
    }

    public static <X extends Throwable, Y extends Throwable> Handler<X> handler(@Nonnull X throwable) {
        return new Handler<>(throwable);
    }

    public X target() {
        return e;
    }

    public <Z extends Throwable> Handler<X> rethrowIf(Class<Z> yClass) throws Z {
        X throwable = target();
        if (yClass.isInstance(throwable)) {
            throw yClass.cast(throwable);
        }

        return self();
    }

    public <Z extends Throwable> Handler<X> replaceIf(
            @Nonnull Predicate<X> condition, @Nonnull ExMF<Z> factory,
            @Nonnull String newMessage, @Nullable Object... messageParams) throws Z {

        Handling.throwReplacementIf(condition, target(), factory, newMessage, messageParams);
        return self();
    }

    public <Z extends Throwable> Handler<X> wrapIf(@Nonnull Predicate<X> condition, @Nonnull ExWF<Z> factory) throws Z {
        Handling.throwWrapperIf(condition, target(), factory);
        return self();
    }

    public <Z extends Throwable> Handler<X> wrapIf(
            @Nonnull Predicate<X> condition, @Nonnull ExWMF<Z> factory,
            @Nonnull String newMessage, @Nullable Object... messageParams) throws Z {
        Handling.throwWrapperIf(condition, target(), factory, newMessage, messageParams);
        return self();
    }

    public <Z extends Throwable> Handler<X> combineIf(
            @Nonnull Predicate<X> condition, @Nonnull ExWMF<Z> factory,
            @Nonnull String newMessage, @Nullable Object... messageParams) throws Z {
        Handling.throwCombineIf(condition, target(), factory, newMessage, messageParams);
        return self();
    }

    public Handler<X> nestIfChecked() {
        X throwable = target();
        if (throwable instanceof RuntimeException) {
            return self();
        } else if (throwable instanceof Error) {
            return self();
        } else {
            throw new NestedException(throwable);
        }
    }

    public <Z extends Throwable> void replace(
            @Nonnull ExMF<Z> factory,
            @Nonnull String newMessage, @Nullable Object... messageParams) throws Z {
        throw Handling.throwReplacement(factory, newMessage, messageParams);
    }

    public <Z extends Throwable> void wrap(@Nonnull ExWF<Z> factory) throws Z {
        throw Handling.throwWrapper(target(), factory);
    }

    public <Z extends Throwable> void wrap(
            @Nonnull ExWMF<Z> factory,
            @Nonnull String newMessage, @Nullable Object... messageParams) throws Z {
        throw Handling.throwWrapper(target(), factory, newMessage, messageParams);
    }

    public <Z extends Throwable> void combine(
            @Nonnull ExWMF<Z> factory,
            @Nonnull String newMessage, @Nullable Object... messageParams) throws Z {
        throw Handling.throwCombine(target(), factory, newMessage, messageParams);
    }

    public void throwFailure() {
        throwFailure(target());
    }

    public static <X extends Throwable, Y extends Throwable> RuntimeException handleOrFail(
            @Nonnull X throwable, @Nullable HandlingInstructions<X, Y> instructions) throws Y {
        return Handling.handleOrFail(throwable, instructions);
    }

    public static <X extends Throwable, Y extends Throwable> RuntimeException handleOrNest(
            @Nonnull X throwable, @Nullable HandlingInstructions<X, Y> instructions) throws Y {
        return Handling.handleOrNest(throwable, instructions);
    }

    public static <X extends Throwable, Y extends Throwable> RuntimeException handleOrPropagate(
            @Nonnull X throwable, @Nullable HandlingInstructions<X, Y> instructions) throws Y {
        return Handling.handleOrPropagate(throwable, instructions);
    }

    public static Throwable throwFailure(Throwable throwable) {
        return Handling.throwHandlingFailure(throwable);
    }

    public void throwAsIs() {
        throw Handling.shoveIt(target());
    }

    public void handleRest() {
        X throwable = target();
        nestCheckedAndThrow(throwable);
    }

}
