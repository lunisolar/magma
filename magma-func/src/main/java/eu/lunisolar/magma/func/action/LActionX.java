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

package eu.lunisolar.magma.func.action;

import javax.annotation.Nonnull; // NOSONAR
import java.util.Objects;// NOSONAR
import java.util.function.Predicate; //NOSONAR
import eu.lunisolar.magma.basics.*; //NOSONAR
import eu.lunisolar.magma.func.*; // NOSONAR
import eu.lunisolar.magma.basics.meta.*; // NOSONAR
import eu.lunisolar.magma.basics.meta.functional.*; // NOSONAR
import eu.lunisolar.magma.basics.meta.functional.type.*; // NOSONAR
import eu.lunisolar.magma.basics.meta.functional.domain.*; // NOSONAR
import eu.lunisolar.magma.func.consumer.*; // NOSONAR
import eu.lunisolar.magma.func.function.*; // NOSONAR
import eu.lunisolar.magma.func.supplier.*; // NOSONAR

/**
 * LActionX is a replacement for Runnable.
 *
 * - offers default methods
 * - do not rise warnings about Runnable being call directly
 * - two versions (throwing and non-throwing) have conversion methods that mirrors each other)
 *
 * Throwing functional interface (lambda) LActionX for Java 8.
 *
 * Type: action
 *
 * Domain (lvl: 0): none
 *
 * Co-domain: none
 *
 * @see LAction
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LActionX<X extends Exception> extends Runnable, MetaAction, MetaInterface.Throwing<X> {

	public static final String DESCRIPTION = "LActionX: void doExecute() throws X";

	@Override
	@Deprecated
	// calling this method via LActionX interface should be discouraged.
	default void run() {
		this.nestingDoExecute();
	}

	public void doExecute() throws X;

	default void nestingDoExecute() {
		try {
			this.doExecute();
		} catch (RuntimeException e) {
			throw e;
		} catch (Exception e) {
			throw new NestedException(e);
		}
	}

	default void shovingDoExecute() {
		((LActionX<RuntimeException>) this).doExecute();
	}

	/** Returns desxription of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LActionX.DESCRIPTION;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	public static <X extends Exception> LActionX<X> lX(final @Nonnull LActionX<X> lambda) {
		Objects.requireNonNull(lambda, "Argument [lambda] cannot be null.");
		return lambda;
	}

	// <editor-fold desc="wrap">

	/** Wraps JRE instance. */
	@Nonnull
	public static <X extends Exception> LActionX<X> wrap(final Runnable other) {
		return other::run;
	}

	/** Wraps opposite (throwing/non-throwing) instance. */
	@Nonnull
	public static <X extends Exception> LActionX<X> wrapX(final @Nonnull LAction other) {
		return (LActionX) other;
	}

	// </editor-fold>

	// <editor-fold desc="andThen (consumer/action)">

	/** Combines two actions together in a order. */
	@Nonnull
	default LActionX<X> andThen(@Nonnull LActionX<X> after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return () -> {
			this.doExecute();
			after.doExecute();
		};
	}

	// </editor-fold>
	// <editor-fold desc="variant conversions">

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default LAction nest() {
		return this::nestingDoExecute;
	}

	/** Converts to throwing variant (RuntimeException). */
	@Nonnull
	default LActionX<RuntimeException> nestX() {
		return this::nestingDoExecute;
	}

	/** Dirty way, checked exception will propagate as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LAction shove() {
		return this::shovingDoExecute;
	}

	/** Dirty way, checked exception will propagate as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LActionX<RuntimeException> shoveX() {
		return this::shovingDoExecute;
	}

	// </editor-fold>

	// <editor-fold desc="exception handling">

	/** Wraps with additional exception handling. */
	@Nonnull
	public static <X extends Exception, E extends Exception, Y extends Exception> LActionX<Y> wrapException(@Nonnull final LActionX<X> other, Class<E> exception, ExceptionHandler<E, Y> handler) {
		return () -> {
			try {
				other.doExecute();
			} catch (Exception e) {
				throw ExceptionHandler.handle(exception, Objects.requireNonNull(handler), (E) e);
			}
		};
	}

	/** Wraps with exception handling that for argument exception class will call function to determine the final exception. */
	@Nonnull
	default <E extends Exception, Y extends Exception> LActionX<Y> handleX(Class<E> exception, ExceptionHandler<E, Y> handler) {
		Objects.requireNonNull(exception, Function4U.VALIDATION_MESSAGE_EXCEPTION);
		Objects.requireNonNull(handler, Function4U.VALIDATION_MESSAGE_HANDLER);

		return LActionX.wrapException(this, exception, (ExceptionHandler) handler);
	}

	/** Wraps with exception handling that for any exception (including unchecked exception that might be different from X) will call handler function to determine the final exception. */
	@Nonnull
	default <Y extends Exception> LActionX<Y> handleX(ExceptionHandler<Exception, Y> handler) {
		Objects.requireNonNull(handler, Function4U.VALIDATION_MESSAGE_HANDLER);

		return LActionX.wrapException(this, Exception.class, (ExceptionHandler) handler);
	}

	// </editor-fold>

}
