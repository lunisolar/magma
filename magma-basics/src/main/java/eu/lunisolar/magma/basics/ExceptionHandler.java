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
 * Foobar is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with Foobar.  If not, see <http://www.gnu.org/licenses/>.
 */
package eu.lunisolar.magma.basics;

import java.util.Objects;

/**
 * Functional interface to handle exception around other functional interface implementations.
 */
@FunctionalInterface
public interface ExceptionHandler<T extends Exception, R extends Exception> {

    String ARGUMENT_EXCEPTION_PREDICATE_CANNOT_BE_NULL = "Argument [exceptionPredicate] cannot be null.";
    String ARGUMENT_EXCEPTION_CANNOT_BE_NULL           = "Argument [exception] cannot be null.";
    String ARGUMENT_HANDLER_CANNOT_BE_NULL             = "Argument [handler] cannot be null.";

    ExceptionHandler<Exception, ExceptionNotHandled> UNLIKELY_HANDLER = ex -> {
        throw new ExceptionNotHandled("Handler has not processed the exception.", ex);
    };

    /** Handler should either produce or throw the exception. Preferably it is a new exception, that wraps the consumed one. */
    R handle(Exception exception) throws R;

    /** Exception handling routine for simple case - typical handling exception in non-throwing implementation */
    static RuntimeException handleWrapping(Exception exception) {
        if (exception instanceof RuntimeException) {
            throw (RuntimeException) exception;
        } else {
            // ignore this place, look up the next/previous line in stacktrace.
            throw new NestedException(exception);
        }
    }

    /**
     * Exception handling routine for any case. Re-thrower is used only for exceptions that are instance of the argument exception class.
     *
     * Potentially there is a possibility to use predicate instead of a class, but this can cause complication or runtime CCE caused in general by compiler
     * doing a bad or no matching between two functional interfaces (this involves more than just calling this method).
     */
    static <Y extends Exception> RuntimeException handle(
            Class<? extends Exception> exceptionClass, ExceptionHandler<Exception, Y> handler, Exception exception) throws Y {

        Objects.requireNonNull(exceptionClass, ARGUMENT_EXCEPTION_PREDICATE_CANNOT_BE_NULL);
        Objects.requireNonNull(exception, ARGUMENT_EXCEPTION_CANNOT_BE_NULL);

        if (exceptionClass.isInstance(exception)) {

            Objects.requireNonNull(handler, ARGUMENT_HANDLER_CANNOT_BE_NULL);
            Y effectiveException = handler.handle(exception);
            if (effectiveException != null) {
                throw effectiveException;
            }
            throw new ExceptionNotHandled("Handler has not processed the exception.", exception);

        } else {
            throw handleWrapping(exception);
        }
    }

}
