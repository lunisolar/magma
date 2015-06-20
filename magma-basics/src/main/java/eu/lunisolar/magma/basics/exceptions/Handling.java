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

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.*;
import java.util.function.*;

/**
 * Obfuscated version eu.lunisolar.lava.core.exceptions.Routine.
 */
@SuppressWarnings({"unchecked", "unused"})
public class Handling implements Serializable {

    public static final String UNKNOWN_PROBLEM = "UNKNOWN PROBLEM!";

    private static final Thrower<Throwable> THROWER = (Throwable x) -> {
        throw x;
    };

    public static final Predicate GENERIC_WRAP_CONDITION = (x) -> x instanceof RuntimeException;

    /**
     * You might call it when execution path never should gone the way it went. The reasons might be like missing assertion of arguments or state of the
     * implementation and this always is some mistake in programming code in one place or another.
     */
    public static RuntimeException shouldNeverBeenHere() {
        throw new Error("Should never happen.");
    }

    public static void handleErrors(Throwable throwable) {
        if (throwable instanceof Error) {
            throw (Error) throwable;
        }
    }

    static <X extends Throwable, Y extends Throwable> Handler.The<X, Y> handleInstructions(
            @Nonnull X throwable, HandlingInstructions<X, Y> instructions) throws Y {
        Null.nonNullArg(throwable, "instructions");
        Null.nonNullArg(instructions, "instructions");
        Handler.The<X, Y> handler = Handler.<X, Y>handler(throwable);
        instructions.processWith(handler);
        return handler;
    }

    // <editor-fold desc="General">

    @Nonnull
    public static String constructMessage(
            @Nullable Throwable e, @Nullable String newMessage, @Nullable Object... messageParams) {
        String message;
        if (newMessage == null) {
            if (e == null) {
                message = UNKNOWN_PROBLEM;
            } else {
                message = e.getMessage();
            }
        } else {
            message = String.format(newMessage, messageParams);
        }
        return message;
    }

    // </editor-fold>

    // <editor-fold desc="createAndThrow">

    public static <Y extends Throwable, X extends Throwable> Y throwReplacement(
            @Nonnull ExceptionNewFactory<Y> factory,
            @Nonnull String newMessage,
            @Nullable Object... messageParams) throws Y {

        throw Handling.create(factory, newMessage, messageParams);
    }

    public static <Y extends Throwable, X extends Throwable> Y throwWrapper(
            @Nonnull X throwable,
            @Nonnull ExceptionWrapFactory<Y> factory) throws Y {

        throw Handling.wrap(throwable, factory);
    }

    public static <Y extends Throwable, X extends Throwable> Y throwWrapper(
            @Nonnull X throwable,
            @Nonnull ExceptionWrapWithMessageFactory<Y> factory,
            @Nonnull String newMessage, @Nullable Object... messageParams) throws Y {

        throw Handling.wrap(throwable, factory, newMessage, messageParams);
    }

    // </editor-fold>

    // <editor-fold desc="conditional create-and-throw">

    public static <Y extends Throwable, X extends Throwable> void throwReplacementIf(
            boolean conditionMeet,
            @Nonnull ExceptionNewFactory<Y> factory,
            @Nonnull String newMessage,
            @Nullable Object... messageParams) throws Y {

        if (conditionMeet) {
            throw Handling.create(factory, newMessage, messageParams);
        }
    }

    public static <Y extends Throwable, X extends Throwable> void throwWrapperIf(
            boolean conditionMeet,
            @Nonnull X throwable,
            @Nonnull ExceptionWrapFactory<Y> factory) throws Y {

        if (conditionMeet) {
            throw Handling.wrap(throwable, factory);
        }
    }

    public static <Y extends Throwable, X extends Throwable> void throwWrapperIf(
            boolean conditionMeet,
            @Nonnull X throwable,
            @Nonnull ExceptionWrapWithMessageFactory<Y> factory,
            @Nonnull String newMessage, @Nullable Object... messageParams) throws Y {

        if (conditionMeet) {
            throw Handling.wrap(throwable, factory, newMessage, messageParams);
        }
    }

    public static <Y extends Throwable, X extends Throwable> void throwReplacementIf(
            @Nonnull Predicate<X> condition,
            @Nonnull X throwable,
            @Nonnull ExceptionNewFactory<Y> factory,
            @Nonnull String newMessage,
            @Nullable Object... messageParams) throws Y {

        throwReplacementIf(condition.test(throwable), factory, newMessage, messageParams);
    }

    public static <Y extends Throwable, X extends Throwable> void throwWrapperIf(
            @Nonnull Predicate<X> condition,
            @Nonnull X throwable,
            @Nonnull ExceptionWrapFactory<Y> factory) throws Y {

        throwWrapperIf(condition.test(throwable), throwable, factory);
    }

    public static <Y extends Throwable, X extends Throwable> void throwWrapperIf(
            @Nonnull Predicate<X> condition,
            @Nonnull X throwable,
            @Nonnull ExceptionWrapWithMessageFactory<Y> factory,
            @Nonnull String newMessage, @Nullable Object... messageParams) throws Y {

        throwWrapperIf(condition.test(throwable), throwable, factory, newMessage, messageParams);
    }

    // </editor-fold>

    // <editor-fold desc="create">

    @SuppressWarnings({"ThrowableResultOfMethodCallIgnored", "unchecked"})
    public static <X extends Throwable> X create(
            @Nonnull ExceptionNewFactory<X> exceptionFactory,
            @Nonnull String newMessage, @Nullable Object... messageParams) {

        String message = constructMessage(null, newMessage, messageParams);
        return exceptionFactory.produce(message);
    }

    // </editor-fold>

    // <editor-fold desc="create or propagate">

    public static <X extends Throwable> X wrap(@Nullable Throwable e, @Nonnull ExceptionWrapFactory<X> exceptionFactory) {
        return exceptionFactory.produce(e); //NOSONAR
    }

    public static <X extends Throwable> X wrap(
            @Nullable Throwable e, @Nonnull ExceptionWrapWithMessageFactory<X> exceptionFactory,
            @Nonnull String newMessage, @Nullable Object... messageParams) {

        String message = constructMessage(null, newMessage, messageParams);
        return exceptionFactory.produce(message, e);
    }

    // </editor-fold>

    public static <X extends Throwable> void shoveIt(Throwable e) {
        Thrower<RuntimeException> thrower = (Thrower) THROWER;
        thrower.throwIt(e);
    }

    @FunctionalInterface
    private interface Thrower<X extends Throwable> {
        void throwIt(Throwable e) throws X;
    }

    // <editor-fold desc="no-instance constructor">

    private Handling() {
    }

    // </editor-fold>

}