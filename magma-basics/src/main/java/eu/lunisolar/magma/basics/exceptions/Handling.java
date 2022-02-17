/*
 * This file is part of "lunisolar-magma".
 *
 * (C) Copyright 2014-2022 Lunisolar (http://lunisolar.eu/).
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
import java.lang.reflect.*;
import java.util.function.*;

import static eu.lunisolar.magma.basics.Null.nonNullArg;

@SuppressWarnings({"unchecked", "unused"})
public final class Handling implements Serializable {

    public static final String UNKNOWN_ISSUE = "Unknown issue!";

    private static final Thrower<Throwable> THROWER = Handling::throwThe;

    public static final Predicate GENERIC_WRAP_CONDITION = (x) -> x instanceof RuntimeException;

    /**
     * You might call it when execution path never should gone the way it went. The reasons might be like missing assertion of arguments or state of the
     * implementation and this always is some mistake in programming code in one place or another.
     */
    public static RuntimeException shouldNeverBeenHere() {
        throw shouldNeverBeenHere("Should never happen.");
    }

    public static RuntimeException shouldNeverBeenHere(String explanation) {
        throw new Error(explanation);
    }

    public static void handleErrors(Throwable throwable) {
        if (!(throwable instanceof Exception)) {
            throw shoveIt(throwable);
        }
    }

    static <X extends Throwable, Y extends Throwable> Handler<X> handleInstructions(
            @Nonnull X throwable, @Nullable HandlingInstructions<X, Y> instructions) throws Y {
        nonNullArg(throwable, "throwable");
        nonNullArg(instructions, "instructions");
        Handler<X> handler = Handler.handler(throwable);
        instructions.processWith(handler);
        return handler;
    }

    // <editor-fold desc="Message">

    @Nonnull
    public static String constructMessage(@Nullable Throwable e, @Nullable String newMessage, @Nullable Object... messageParams) {
        return (newMessage == null) ? private_message(e) : String.format(newMessage, messageParams);
    }

    @Nonnull
    public static String constructMessage(@Nullable Throwable e, @Nullable String newMessage) {
        return (newMessage == null) ? private_message(e) : newMessage;
    }

    public static String constructMessage(boolean combine, @Nullable Throwable e, @Nullable String newMessage, @Nullable Object... messageParams) {
        if (combine && newMessage != null) {
            String message = constructMessage(e, newMessage, messageParams);
            String causeMessage = e != null ? e.getMessage() : null;
            String finalMessage = causeMessage == null || causeMessage.isBlank() ?
                    message
                    :
                    constructMessage(null, "%s %s", message, causeMessage);

            return finalMessage;
        } else {
            return constructMessage(e, newMessage, messageParams);
        }
    }

    @Nonnull
    public static String constructMessage(boolean combine, @Nullable Throwable e, @Nullable String newMessage) {
        if (combine && newMessage != null) {
            String message = constructMessage(e, newMessage);
            String causeMessage = e != null ? e.getMessage() : null;
            String finalMessage = causeMessage == null || causeMessage.isBlank() ?
                    message
                    :
                    constructMessage(null, "%s %s", message, causeMessage);

            return finalMessage;
        } else {
            return constructMessage(e, newMessage);
        }
    }

    private static String private_message(@Nullable Throwable e) {
        String message;
        if (e == null) {
            message = UNKNOWN_ISSUE;
        } else {
            message = e.getMessage();
        }
        return message;
    }

    // </editor-fold>

    // <editor-fold desc="ExF">

    public static <X extends Throwable> X create(@Nonnull ExF<X> fx) {
        return nonNullArg(fx, "Exception factory cannot be null.").produce();
    }

    //</editor-fold>

    // <editor-fold desc="ExMF">

    public static <X extends Throwable> X create(@Nonnull ExMF<X> fx, @Nullable String msg) {
        return nonNullArg(fx, "Exception factory cannot be null.").produce(constructMessage(null, msg));
    }

    public static <X extends Throwable> X create(@Nonnull ExMF<X> fx, @Nullable String msg, @Nullable Object... args) {
        return nonNullArg(fx, "Exception factory cannot be null.").produce(constructMessage(null, msg, args));
    }

    public static <X extends Throwable> X create(
            @Nonnull ExMF<X> fx, @Nullable String msg1, @Nullable Object[] args1, @Nullable String msg2, @Nullable Object... args2) {
        return nonNullArg(fx, "Exception factory cannot be null.").produce(constructMessage(null, msg1, args1) + ' ' + constructMessage(null, msg2, args2));
    }

    //</editor-fold>

    //<editor-fold desc="ExWF">

    public static <X extends Throwable> X wrapIfNot(@Nullable Class<X> c, @Nullable Throwable e, @Nonnull ExWF<X> fx) {
        handleErrors(e);
        if (c != null && c.isInstance(e)) {
            return (X) e;
        }
        return nonNullArg(fx, "Exception factory cannot be null.").produce(e);
    }

    public static <X extends Throwable> X wrap(@Nullable Throwable e, @Nonnull ExWF<X> fx) {
        return wrapIfNot(null, e, fx);
    }

    //</editor-fold>

    // <editor-fold desc="ExMF">

    public static <X extends Throwable> X wrapIfNot(
            @Nullable Class<X> c, @Nullable Throwable e, @Nonnull ExWMF<X> fx, boolean combine, @Nullable String msg
    ) {
        handleErrors(e);
        if (c != null && c.isInstance(e)) {
            return (X) e;
        }
        return nonNullArg(fx, "Exception factory cannot be null.").produce(constructMessage(combine, e, msg), e);
    }

    public static <X extends Throwable> X wrapIfNot(
            @Nullable Class<X> c, @Nullable Throwable e, @Nonnull ExWMF<X> fx, boolean combine, @Nullable String msg, @Nullable Object... args
    ) {
        handleErrors(e);
        if (c != null && c.isInstance(e)) {
            return (X) e;
        }
        return nonNullArg(fx, "Exception factory cannot be null.").produce(constructMessage(combine, e, msg, args), e);
    }

    public static <X extends Throwable> X wrap(@Nullable Throwable e, @Nonnull ExWMF<X> fx, @Nullable String msg) {
        return wrapIfNot(null, e, fx, false, msg);
    }

    public static <X extends Throwable> X wrap(@Nullable Throwable e, @Nonnull ExWMF<X> fx, @Nullable String msg, @Nullable Object... args) {
        return wrapIfNot(null, e, fx, false, msg, args);
    }

    public static <X extends Throwable> X wrapIfNot(@Nonnull Class<X> clazz, @Nullable Throwable e, @Nonnull ExWMF<X> fx, @Nullable String msg) {
        return wrapIfNot(clazz, e, fx, false, msg);
    }

    public static <X extends Throwable> X wrapIfNot(
            @Nonnull Class<X> clazz, @Nullable Throwable e, @Nonnull ExWMF<X> fx, @Nullable String msg, @Nullable Object... args) {
        return wrapIfNot(clazz, e, fx, false, msg, args);
    }

    public static <X extends Throwable> X combineIfNot(@Nonnull Class<X> clazz, @Nullable Throwable e, @Nonnull ExWMF<X> fx, @Nullable String msg) {
        return wrapIfNot(clazz, e, fx, true, msg);
    }

    public static <X extends Throwable> X combineIfNot(
            @Nonnull Class<X> clazz, @Nullable Throwable e, @Nonnull ExWMF<X> fx, @Nullable String msg, @Nullable Object... args) {
        return wrapIfNot(clazz, e, fx, false, msg, args);
    }

    //</editor-fold>

    // <editor-fold desc="create-ExF4">

    public static <X extends Throwable> X wrapIfNot(
            boolean enableSuppression, boolean writableStackTrace,
            @Nullable Class<X> c, @Nullable Throwable e, @Nonnull ExF4<X> fx, boolean combine, @Nullable String msg
    ) {
        handleErrors(e);
        if (c != null && c.isInstance(e)) {
            return (X) e;
        }
        return nonNullArg(fx, "Exception factory cannot be null.").produce(constructMessage(true, e, msg), e, enableSuppression, writableStackTrace);
    }

    public static <X extends Throwable> X wrapIfNot(
            boolean enableSuppression, boolean writableStackTrace,
            @Nullable Class<X> c, @Nullable Throwable e, @Nonnull ExF4<X> fx, boolean combine, @Nullable String msg, @Nullable Object... args
    ) {
        handleErrors(e);
        if (c != null && c.isInstance(e)) {
            return (X) e;
        }
        return nonNullArg(fx, "Exception factory cannot be null.").produce(constructMessage(true, e, msg, args), e, enableSuppression, writableStackTrace);
    }

    public static <X extends Throwable> X wrap(
            boolean enableSuppression, boolean writableStackTrace, @Nullable Throwable e, @Nonnull ExF4<X> fx, @Nullable String msg
    ) {
        return wrapIfNot(enableSuppression, writableStackTrace, null, e, fx, false, msg);
    }

    public static <X extends Throwable> X wrap(
            boolean enableSuppression, boolean writableStackTrace,
            @Nullable Throwable e, @Nonnull ExF4<X> fx, @Nullable String msg, @Nullable Object... args
    ) {
        return wrapIfNot(enableSuppression, writableStackTrace, null, e, fx, false, msg, args);
    }

    public static <X extends Throwable> X wrapIfNot(
            boolean enableSuppression, boolean writableStackTrace,
            @Nonnull Class<X> clazz, @Nullable Throwable e, @Nonnull ExF4<X> fx, @Nullable String msg
    ) {
        return wrapIfNot(enableSuppression, writableStackTrace, clazz, e, fx, false, msg);
    }

    public static <X extends Throwable> X wrapIfNot(
            boolean enableSuppression, boolean writableStackTrace,
            @Nonnull Class<X> clazz, @Nullable Throwable e, @Nonnull ExF4<X> fx, @Nullable String msg, @Nullable Object... args
    ) {
        return wrapIfNot(enableSuppression, writableStackTrace, clazz, e, fx, false, msg, args);
    }

    //</editor-fold>

    // <editor-fold desc="create or propagate">

    public static <X extends Throwable> X combine(
            @Nullable Throwable e, @Nonnull ExWMF<X> exceptionFactory,
            @Nonnull String newMessage, @Nullable Object... messageParams) {
        handleErrors(e);
        String message = constructMessage(null, newMessage, messageParams);
        String causeMessage = e.getMessage();
        String finalMessage = causeMessage == null || causeMessage.isBlank() ?
                message
                :
                constructMessage(null, "%s %s", message, causeMessage);

        return exceptionFactory.produce(finalMessage, e);
    }

    public static <X extends Throwable> X combine(
            @Nullable Throwable e, @Nonnull ExWMF<X> exceptionFactory,
            @Nonnull String newMessage) {
        handleErrors(e);
        String message = constructMessage(null, newMessage);
        String causeMessage = e.getMessage();
        String finalMessage = causeMessage == null || causeMessage.isBlank() ?
                message
                :
                constructMessage(null, "%s %s", message, causeMessage);

        return exceptionFactory.produce(finalMessage, e);
    }

    // </editor-fold>

    /** Throws exception (convenience method for lambda expressions). Pretends that there might be a product - but it will never be returned. */
    public static <T, X extends Throwable> T throwThe(X e) throws X {
        Null.nonNullArg(e, "e");
        throw e;
    }

    public static <X extends Throwable> RuntimeException shoveIt(Throwable e) {
        return throwIt(e);
    }

    public static <X extends Throwable> RuntimeException throwIt(Throwable e) {
        Thrower<RuntimeException> thrower = (Thrower) THROWER;
        thrower.throwThe(e);
        throw shouldNeverBeenHere();
    }

    @FunctionalInterface
    private interface Thrower<X extends Throwable> {
        void throwThe(Throwable e) throws X;
    }

    //</editor-fold>

    public static RuntimeException nestCheckedAndThrow(Throwable throwable) {
        handleErrors(throwable);
        if (throwable instanceof RuntimeException) {
            throw (RuntimeException) throwable;
        } else if (throwable instanceof Exception) {
            throw new NestedException(throwable);
        }
        throw shouldNeverBeenHere();
    }

    // <editor-fold desc="no-instance constructor">

    private Handling() {
    }

    // </editor-fold>

    // <editor-fold desc="deprecated?">

    protected static <Y extends Throwable, X extends Throwable> void throwReplacementIf(
            boolean conditionMeet, @Nonnull ExMF<Y> fx, @Nonnull String format, @Nullable Object... messageParams
    ) throws Y {
        if (conditionMeet) {
            throw Handling.create(fx, format, messageParams);
        }
    }

    protected static <Y extends Throwable, X extends Throwable> void throwWrapperIf(boolean conditionMeet, @Nonnull X e, @Nonnull ExWF<Y> fx) throws Y {
        if (conditionMeet) {
            throw Handling.wrap(e, fx);
        }
    }

    protected static <Y extends Throwable, X extends Throwable> void throwCombineIf(
            boolean conditionMeet, @Nonnull X e, @Nonnull ExWMF<Y> fx, @Nonnull String format, @Nullable Object... args) throws Y {
        if (conditionMeet) {
            throw Handling.combine(e, fx, format, args);
        }
    }

    protected static <Y extends Throwable, X extends Throwable> void throwWrapperIf(
            boolean conditionMeet, @Nonnull X e, @Nonnull ExWMF<Y> fx, @Nonnull String format, @Nullable Object... args
    ) throws Y {
        if (conditionMeet) {
            throw Handling.wrap(e, fx, format, args);
        }
    }

    protected static <Y extends Throwable, X extends Throwable> void throwReplacementIf(
            @Nonnull Predicate<X> condition, @Nonnull X e, @Nonnull ExMF<Y> fx, @Nonnull String format, @Nullable Object... args
    ) throws Y {
        throwReplacementIf(condition.test(e), fx, format, args);
    }

    protected static <Y extends Throwable, X extends Throwable> void throwWrapperIf(
            @Nonnull Predicate<X> condition, @Nonnull X e, @Nonnull ExWF<Y> fx
    ) throws Y {
        throwWrapperIf(condition.test(e), e, fx);
    }

    protected static <Y extends Throwable, X extends Throwable> void throwWrapperIf(
            @Nonnull Predicate<X> condition, @Nonnull X e, @Nonnull ExWMF<Y> fx, @Nonnull String format, @Nullable Object... args
    ) throws Y {
        throwWrapperIf(condition.test(e), e, fx, format, args);
    }

    protected static <Y extends Throwable, X extends Throwable> void throwCombineIf(
            @Nonnull Predicate<X> condition, @Nonnull X e, @Nonnull ExWMF<Y> fx, @Nonnull String format, @Nullable Object... args
    ) throws Y {
        throwCombineIf(condition.test(e), e, fx, format, args);
    }

    protected static <Y extends Throwable, X extends Throwable> Y throwReplacement(
            @Nonnull ExMF<Y> fx, @Nonnull String format, @Nullable Object... args) throws Y {
        throw Handling.create(fx, format, args);
    }

    protected static <Y extends Throwable, X extends Throwable> Y throwWrapper(@Nonnull X throwable, @Nonnull ExWF<Y> fx) throws Y {
        throw Handling.wrap(throwable, fx);
    }

    protected static <Y extends Throwable, X extends Throwable> Y throwWrapper(
            @Nonnull X throwable, @Nonnull ExWMF<Y> fx, @Nonnull String format, @Nullable Object... args
    ) throws Y {
        throw Handling.wrap(throwable, fx, format, args);
    }

    protected static <Y extends Throwable, X extends Throwable> Y throwCombine(
            @Nonnull X throwable, @Nonnull ExWMF<Y> fx, @Nonnull String format, @Nullable Object... args
    ) throws Y {
        throw Handling.combine(throwable, fx, format, args);
    }

    // </editor-fold>

    public static void ignore(@Nullable Throwable e) {
        // noop
    }

    //<editor-fold desc="handleOr...">

    /**
     * Executes instructions, if none of them wil throw or rethrow exception this method will fail with exception that throwable was not handled.
     * Errors will be rethrow immediately.
     *
     * @return There is nothing ever returned - however return value of method signature can be used in statement: _throw handleOrFail(..)_
     */
    public static <X extends Throwable, Y extends Throwable> RuntimeException handleOrFail(
            @Nonnull X throwable, @Nullable HandlingInstructions<X, Y> instructions) throws Y {

        handleErrors(throwable);

        if (instructions != null) {
            handleInstructions(throwable, instructions);
        }

        throwHandlingFailure(throwable);
        throw shouldNeverBeenHere();
    }

    /**
     * Executes instructions, if none of them wil throw or rethrow exception this method will rethrow RuntimeExceptions and nest checked exceptions.
     * Errors will be rethrow immediately.
     *
     * @return There is nothing ever returned - however return value of method signature can be used in statement: _throw handleOrFail(..)_
     */
    public static <X extends Throwable, Y extends Throwable> RuntimeException handleOrNest(
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
    public static <X extends Throwable, Y extends Throwable> RuntimeException handleOrPropagate(
            @Nonnull X throwable, @Nullable HandlingInstructions<X, Y> instructions) throws Y {

        handleErrors(throwable);

        if (instructions != null) {
            handleInstructions(throwable, instructions);
        }

        throw shoveIt(throwable);
    }

    static Throwable throwHandlingFailure(Throwable throwable) {
        throw new ExceptionNotHandled("Exception has not been handled.", throwable);
    }

    //</editor-fold>

    /** At the cost of dynamic proxy (so not for nano-operations) adds exception handling. */
    public static <C> C proxy(ClassLoader cl, C target, HandlingInstructions<Throwable, RuntimeException> handlingInstructions, Class<?>... classes) {

        nonNullArg(cl, "cl");
        nonNullArg(target, "target");
        nonNullArg(handlingInstructions, "handlingInstructions");
        nonNullArg(classes, "classes");

        if (classes.length == 0) {
            throw new IllegalArgumentException("Proxy must be created for at least one interface.");
        }

        for (Class<?> aClass : classes) {
            if (aClass == null) {
                throw new IllegalArgumentException("Interfaces in array cannot be null.");
            }
        }

        return (C) Proxy.newProxyInstance(cl, classes, (Object proxy, Method method, Object[] args) -> {
            try {
                return method.invoke(target, args);
            } catch (InvocationTargetException e) {
                throw handleOrFail(e.getTargetException(), handlingInstructions);
            }
        });
    }

    public static RuntimeException throwWithSuppression(@Nonnull Throwable main, @Nullable Throwable suppressed) {
        nonNullArg(main, "main");
        if (suppressed != null) {
            main.addSuppressed(suppressed);
        }
        throw throwIt(main);
    }

}