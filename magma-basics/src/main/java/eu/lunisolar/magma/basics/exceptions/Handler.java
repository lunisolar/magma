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

package eu.lunisolar.magma.basics.exceptions;

import eu.lunisolar.magma.basics.Null;
import eu.lunisolar.magma.basics.fluent.Fluent;
import eu.lunisolar.magma.basics.probing.ThrowableProbe;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.NotThreadSafe;
import java.util.function.*;

import static eu.lunisolar.magma.basics.exceptions.Handling.shoveIt;

/**
 * It should never be treated as replacement for TRY-CATCH. It is intended to be a sugar in places where generic exception are being handled.
 */
@SuppressWarnings({"ThrowableResultOfMethodCallIgnored", "unchecked", "unused"})
@NotThreadSafe
public interface Handler<SELF extends Handler<SELF, X, Y>, X extends Throwable, Y extends Throwable> extends Fluent<SELF> {

    static <X extends Throwable, Y extends Throwable> Handler.The<X, Y> handler(@Nonnull X throwable) {
        return new The<>(throwable);
    }

    static <X extends Throwable, Y extends Throwable> Handler.The<X, Y> handleInstructions(
            @Nonnull X throwable, HandlingInstructions<X, Y> instructions) throws Y {
        Null.nonNullArg(throwable, "instructions");
        Null.nonNullArg(instructions, "instructions");
        The<X, Y> handler = Handler.<X, Y>handler(throwable);
        instructions.processWith(handler);
        return handler;
    }

    /**
     * @return There is nothing ever returned - however return value of method signature can be used in statement: _throw handleOrFail(..)_
     */
    static <X extends Throwable, Y extends Throwable> RuntimeException handleOrFail(
            @Nonnull X throwable, HandlingInstructions<X, Y> instructions) throws Y {

        The<X, Y> handler = handleInstructions(throwable, instructions);

        handler.throwFailure();
        throw Handling.shouldNeverBeenHere();
    }

    /**
     * * @return There is nothing ever returned - however return value of method signature can be used in statement: _throw handleOrFail(..)_
     */
    static <X extends Throwable, Y extends Throwable> RuntimeException handleOrNest(
            @Nonnull X throwable, @Nonnull HandlingInstructions<X, Y> instructions) throws Y {

        The<X, Y> handler = handleInstructions(throwable, instructions);

        handler.handleRest();
        throw Handling.shouldNeverBeenHere();
    }

     /**
     * * @return There is nothing ever returned - however return value of method signature can be used in statement: _throw handleOrFail(..)_
     */
    static <X extends Throwable, Y extends Throwable> RuntimeException handleOrPropagate(
            @Nonnull X throwable, @Nullable HandlingInstructions<X, Y> instructions) throws Y {

        if ( instructions!=null) {
            handleInstructions(throwable, instructions);
        }

        shoveIt(throwable);
        throw Handling.shouldNeverBeenHere();
    }

    <Y extends Throwable> SELF throwIf(Class<Y> yClass) throws Y;

    <Y extends Throwable> SELF replaceIf(
            @Nonnull Predicate<X> condition, @Nonnull ExceptionNewFactory<Y> factory,
            @Nonnull String newMessage, @Nullable Object... messageParams) throws Y;

    <Y extends Throwable> SELF wrapIf(@Nonnull Predicate<X> condition, @Nonnull ExceptionWrapFactory<Y> factory) throws Y;

    <Y extends Throwable> SELF wrapIf(
            @Nonnull Predicate<X> condition, @Nonnull ExceptionWrapWithMessageFactory<Y> factory,
            @Nonnull String newMessage, @Nullable Object... messageParams) throws Y;

    <Y extends Throwable> SELF replaceWhen(
            @Nonnull Predicate<ThrowableProbe<X>> condition, @Nonnull ExceptionNewFactory<Y> factory,
            @Nonnull String newMessage, @Nullable Object... messageParams) throws Y;

    <Y extends Throwable> SELF wrapWhen(@Nonnull Predicate<ThrowableProbe<X>> condition, @Nonnull ExceptionWrapFactory<Y> factory) throws Y;

    <Y extends Throwable> SELF wrapWhen(
            @Nonnull Predicate<ThrowableProbe<X>> condition, @Nonnull ExceptionWrapWithMessageFactory<Y> factory,
            @Nonnull String newMessage, @Nullable Object... messageParams) throws Y;

    SELF nestIfChecked();

    <Y extends Throwable> void throwReplacement(
            @Nonnull ExceptionNewFactory<Y> factory,
            @Nonnull String newMessage, @Nullable Object... messageParams) throws Y;

    <Y extends Throwable> void throwWrapper(@Nonnull ExceptionWrapFactory<Y> factory) throws Y;

    <Y extends Throwable> void throwWrapper(
            @Nonnull ExceptionWrapWithMessageFactory<Y> factory,
            @Nonnull String newMessage, @Nullable Object... messageParams) throws Y;

    void throwFailure();

    void throwAsIs() throws X;

    void handleRest();

    class The<X extends Throwable, Y extends Throwable> extends HandlerBase<The<X, Y>, X, Y> {
        public The(@Nonnull X throwable) {
            super(throwable);
        }
    }

}
