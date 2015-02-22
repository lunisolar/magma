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

package eu.lunisolar.magma.func.action;

import javax.annotation.Nonnull; // NOSONAR
import java.util.Objects;// NOSONAR
import java.util.function.Predicate; //NOSONAR
import eu.lunisolar.magma.basics.*; //NOSONAR
import eu.lunisolar.magma.func.*; // NOSONAR
import eu.lunisolar.magma.basics.meta.*; // NOSONAR
import eu.lunisolar.magma.basics.meta.domains.*; // NOSONAR
import eu.lunisolar.magma.func.consumer.*; // NOSONAR
import eu.lunisolar.magma.func.function.*; // NOSONAR
import eu.lunisolar.magma.func.supplier.*; // NOSONAR

/**
 * Execution is a replacement for Runnable.
 *
 * - offers default methods
 * - do not rise warnings about Runnable being call directly
 * - two versions (throwing and non-throwing) have conversion methods that mirrors each other)
 *
 * @see {@link eu.lunisolar.magma.func.action.Action}
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface ActionX<X extends Exception> extends MetaAction, MetaThrowingInterface<X> {

	public static final String DESCRIPTION = "ActionX: void execute() throws X";

	public void execute() throws X;

	/** Returns desxription of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return ActionX.DESCRIPTION;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	public static <X extends Exception> ActionX<X> lX(final @Nonnull ActionX<X> lambda) {
		Objects.requireNonNull(lambda, "Argument [lambda] cannot be null.");
		return lambda;
	}

	// <editor-fold desc="wrap">

	/** Wraps JRE instance. */
	@Nonnull
	public static <X extends Exception> ActionX<X> wrapStd(final Runnable other) {
		return other::run;
	}

	/** Wraps opposite (throwing/non-throwing) instance. */
	@Nonnull
	public static <X extends Exception> ActionX<X> wrapX(final @Nonnull Action other) {
		return other::execute;
	}

	/** Wraps with additional exception handling. */
	@Nonnull
	public static <X extends Exception, Y extends Exception> ActionX<Y> wrapException(@Nonnull final ActionX<X> other, Class<? extends Exception> exception, ExceptionHandler<Exception, Y> rethrower) {
		return () -> {
			try {
				other.execute();
			} catch (Exception e) {
				throw ExceptionHandler.handle(exception, rethrower, e);
			}
		};
	}

	// </editor-fold>

	// <editor-fold desc="andThen (consumer/action)">

	/** Combines two actions together in a order. */
	@Nonnull
	default ActionX<X> andThen(@Nonnull ActionX<X> after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return () -> {
			this.execute();
			after.execute();
		};
	}

	// </editor-fold>
	// <editor-fold desc="variant conversions">

	/** Converts to JRE variant. */
	@Nonnull
	default Runnable std() {
		return Action.wrap(this)::execute;
	}

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default Action nonThrowing() {
		return Action.wrap(this);
	}

	/** Converts to throwing variant (RuntimeException). */
	@Nonnull
	default ActionX<RuntimeException> uncheck() {
		return nonThrowing()::execute;
	}

	// </editor-fold>

	// <editor-fold desc="exception handling">

	/** Wraps with exception handling that for argument exception class will call function to determine the final exception. */
	@Nonnull
	default <Y extends Exception> ActionX<Y> handle(Class<? extends Exception> exception, ExceptionHandler<? super X, Y> handler) {
		Objects.requireNonNull(exception, Function4U.VALIDATION_MESSAGE_EXCEPTION);
		Objects.requireNonNull(handler, Function4U.VALIDATION_MESSAGE_HANDLER);

		return ActionX.wrapException(this, exception, (ExceptionHandler) handler);
	}

	/** Wraps with exception handling that for argument exception class will call function to determine the final exception. */
	@Nonnull
	default <Y extends Exception> ActionX<Y> handle(ExceptionHandler<? super X, Y> handler) {
		Objects.requireNonNull(handler, Function4U.VALIDATION_MESSAGE_HANDLER);

		return ActionX.wrapException(this, Exception.class, (ExceptionHandler) handler);
	}

	// </editor-fold>

}
