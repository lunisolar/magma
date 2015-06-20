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

package eu.lunisolar.magma.basics.exceptions;

import eu.lunisolar.magma.basics.Null;
import eu.lunisolar.magma.basics.fluent.Fluent;
import eu.lunisolar.magma.basics.probing.ThrowableProbe;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.function.*;

/**
 *
 */
@SuppressWarnings("ThrowableResultOfMethodCallIgnored")
class HandlerBase<SELF extends HandlerBase<SELF, X, Y>, X extends Throwable, Y extends Throwable> implements Handler<SELF, X, Y>, Fluent<SELF>, ThrowableProbe<X> {

    private final X throwable;

    public HandlerBase(@Nonnull X throwable) {
        Null.nonNullArg(throwable, "throwable");
        this.throwable = throwable;
    }

    @Nullable @Override public X getTarget() {
        return throwable;
    }

    // <editor-fold desc="throwInCase">

    public <Y extends Throwable> SELF throwIf(Class<Y> yClass) throws Y {
        if (yClass.isInstance(throwable)) {
            throw yClass.cast(throwable);
        }

        return self();
    }

    public <Y extends Throwable> SELF replaceIf(
            @Nonnull Predicate<X> condition, @Nonnull ExceptionNewFactory<Y> factory,
            @Nonnull String newMessage, @Nullable Object... messageParams) throws Y {

        Handling.throwReplacementIf(condition, throwable, factory, newMessage, messageParams);
        return self();
    }

    public <Y extends Throwable> SELF wrapIf(@Nonnull Predicate<X> condition, @Nonnull ExceptionWrapFactory<Y> factory) throws Y {
        Handling.throwWrapperIf(condition, throwable, factory);
        return self();
    }

    public <Y extends Throwable> SELF wrapIf(
            @Nonnull Predicate<X> condition, @Nonnull ExceptionWrapWithMessageFactory<Y> factory,
            @Nonnull String newMessage, @Nullable Object... messageParams) throws Y {

        Handling.throwWrapperIf(condition, throwable, factory, newMessage, messageParams);
        return self();
    }

    public <Y extends Throwable> SELF replaceWhen(
            @Nonnull Predicate<ThrowableProbe<X>> condition, @Nonnull ExceptionNewFactory<Y> factory,
            @Nonnull String newMessage, @Nullable Object... messageParams) throws Y {

        Handling.throwReplacementIf(condition.test(this), factory, newMessage, messageParams);
        return self();
    }

    public <Y extends Throwable> SELF wrapWhen(@Nonnull Predicate<ThrowableProbe<X>> condition, @Nonnull ExceptionWrapFactory<Y> factory) throws Y {
        Handling.throwWrapperIf(condition.test(this), throwable, factory);
        return self();
    }

    public <Y extends Throwable> SELF wrapWhen(
            @Nonnull Predicate<ThrowableProbe<X>> condition, @Nonnull ExceptionWrapWithMessageFactory<Y> factory,
            @Nonnull String newMessage, @Nullable Object... messageParams) throws Y {

        Handling.throwWrapperIf(condition.test(this), throwable, factory, newMessage, messageParams);
        return self();
    }

    // </editor-fold>

    // <editor-fold desc="simple">

    public SELF nestIfChecked() {
        if (throwable instanceof RuntimeException) {
            return self();
        } else if (throwable instanceof Error) {
            return self();
        } else {
            throw new NestedException(throwable);
        }
    }

    // </editor-fold>

    // <editor-fold desc="finalizers">

    public <Y extends Throwable> void throwReplacement(
            @Nonnull ExceptionNewFactory<Y> factory,
            @Nonnull String newMessage, @Nullable Object... messageParams) throws Y {
        throw Handling.throwReplacement(factory, newMessage, messageParams);
    }

    public <Y extends Throwable> void throwWrapper(@Nonnull ExceptionWrapFactory<Y> factory) throws Y {
        throw Handling.throwWrapper(throwable, factory);
    }

    public <Y extends Throwable> void throwWrapper(
            @Nonnull ExceptionWrapWithMessageFactory<Y> factory,
            @Nonnull String newMessage, @Nullable Object... messageParams) throws Y {
        throw Handling.throwWrapper(throwable, factory, newMessage, messageParams);
    }

    public void throwFailure() {
        throw new ExceptionNotHandled("Exception has not been handled.", throwable);
    }

    public void throwAsIs() throws X {
        throw throwable;
    }

    public void handleRest() {
        if (throwable instanceof RuntimeException) {
            throw (RuntimeException) throwable;
        } else if (throwable instanceof Error) {
            throw (Error) throwable;
        } else {
            throw new NestedException(throwable);
        }
    }

    // </editor-fold>

}