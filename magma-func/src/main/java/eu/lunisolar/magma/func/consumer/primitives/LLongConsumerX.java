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

package eu.lunisolar.magma.func.consumer.primitives;

import javax.annotation.Nonnull; // NOSONAR
import javax.annotation.Nullable; // NOSONAR
import java.util.Objects; // NOSONAR
import eu.lunisolar.magma.basics.*; //NOSONAR
import eu.lunisolar.magma.func.*; // NOSONAR
import eu.lunisolar.magma.basics.meta.*; // NOSONAR
import eu.lunisolar.magma.basics.meta.functional.*; // NOSONAR
import eu.lunisolar.magma.basics.meta.functional.type.*; // NOSONAR
import eu.lunisolar.magma.basics.meta.functional.domain.*; // NOSONAR
import eu.lunisolar.magma.func.consumer.*; // NOSONAR
import eu.lunisolar.magma.func.*; // NOSONAR
import eu.lunisolar.magma.func.operator.unary.*; // NOSONAR
import eu.lunisolar.magma.func.operator.binary.*; // NOSONAR
import eu.lunisolar.magma.func.operator.ternary.*; // NOSONAR
import eu.lunisolar.magma.func.function.*; // NOSONAR
import eu.lunisolar.magma.func.function.from.*; // NOSONAR
import eu.lunisolar.magma.func.function.to.*; // NOSONAR
import eu.lunisolar.magma.func.function.conversion.*; // NOSONAR
import eu.lunisolar.magma.func.predicate.*; // NOSONAR
import eu.lunisolar.magma.func.supplier.*; // NOSONAR
import eu.lunisolar.magma.func.consumer.*; // NOSONAR
import eu.lunisolar.magma.func.consumer.primitives.*; // NOSONAR
import eu.lunisolar.magma.func.consumer.primitives.bi.*; // NOSONAR
import eu.lunisolar.magma.func.consumer.primitives.tri.*; // NOSONAR
import eu.lunisolar.magma.func.consumer.primitives.obj.*; // NOSONAR
import eu.lunisolar.magma.func.action.*; // NOSONAR

/**
 * Throwing functional interface (lambda) LLongConsumerX for Java 8.
 *
 * Type: consumer
 *
 * Domain (lvl: 1): long l
 *
 * Co-domain: none
 *
 * @see LLongConsumer
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LLongConsumerX<X extends Exception> extends MetaConsumer, MetaInterface.Throwing<X> {

	public static final String DESCRIPTION = "LLongConsumerX: void accept(long l) throws X";

	public void accept(long l) throws X;

	/** Returns desxription of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LLongConsumerX.DESCRIPTION;
	}

	/** Captures arguments but delays the evaluation. */
	default LActionX<X> capture(long l) {
		return () -> this.accept(l);
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	public static <X extends Exception> LLongConsumerX<X> lX(final @Nonnull LLongConsumerX<X> lambda) {
		Objects.requireNonNull(lambda, "Argument [lambda] cannot be null.");
		return lambda;
	}

	// <editor-fold desc="wrap">

	/** Wraps JRE instance. */
	@Nonnull
	public static <X extends Exception> LLongConsumerX<X> wrapStd(final java.util.function.LongConsumer other) {
		return other::accept;
	}

	/** Wraps opposite (throwing/non-throwing) instance. */
	@Nonnull
	public static <X extends Exception> LLongConsumerX<X> wrapX(final @Nonnull LLongConsumer other) {
		return other::accept;
	}

	// </editor-fold>

	// <editor-fold desc="compose (functional)">

	/**
	 * Allows to manipulate the domain of the functyion.
	 */
	@Nonnull
	default LLongConsumerX<X> fromLong(@Nonnull final LLongUnaryOperatorX<X> before1) {
		Objects.requireNonNull(before1, Function4U.VALIDATION_MESSAGE_BEFORE1);
		return (final long v1) -> this.accept(before1.applyAsLong(v1));
	}

	/**
	 * Allows to manipulate the domain of the functyion.
	 */
	@Nonnull
	default <V1> LConsumerX<V1, X> from(@Nonnull final LToLongFunctionX<? super V1, X> before1) {
		Objects.requireNonNull(before1, Function4U.VALIDATION_MESSAGE_BEFORE1);
		return (V1 v1) -> this.accept(before1.applyAsLong(v1));
	}

	// </editor-fold>

	// <editor-fold desc="andThen (consumer/action)">

	/** Combines two consumers together in a order. */
	@Nonnull
	default LLongConsumerX<X> andThen(@Nonnull LLongConsumerX<X> after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (long l) -> {
			this.accept(l);
			after.accept(l);
		};
	}

	// </editor-fold>
	// <editor-fold desc="variant conversions">

	/** Converts to JRE variant. */
	@Nonnull
	default java.util.function.LongConsumer std() {
		return LLongConsumer.wrap(this)::accept;
	}

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default LLongConsumer nonThrowing() {
		return LLongConsumer.wrap(this);
	}

	/** Converts to throwing variant (RuntimeException). */
	@Nonnull
	default LLongConsumerX<RuntimeException> uncheck() {
		return (LLongConsumerX) this;
	}

	/** Dirty way, checked exception will propagate as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LLongConsumer shove() {
		LLongConsumerX<RuntimeException> exceptionCast = (LLongConsumerX<RuntimeException>) this;
		return exceptionCast::accept;
	}

	// </editor-fold>

	// <editor-fold desc="exception handling">

	/** Wraps with additional exception handling. */
	@Nonnull
	public static <X extends Exception, E extends Exception, Y extends Exception> LLongConsumerX<Y> wrapException(@Nonnull final LLongConsumerX<X> other, Class<E> exception, ExceptionHandler<E, Y> handler) {
		return (long l) -> {
			try {
				other.accept(l);
			} catch (Exception e) {
				throw ExceptionHandler.handle(exception, Objects.requireNonNull(handler), (E) e);
			}
		};
	}

	/** Wraps with exception handling that for argument exception class will call function to determine the final exception. */
	@Nonnull
	default <E extends Exception, Y extends Exception> LLongConsumerX<Y> handleX(Class<E> exception, ExceptionHandler<E, Y> handler) {
		Objects.requireNonNull(exception, Function4U.VALIDATION_MESSAGE_EXCEPTION);
		Objects.requireNonNull(handler, Function4U.VALIDATION_MESSAGE_HANDLER);

		return LLongConsumerX.wrapException(this, exception, (ExceptionHandler) handler);
	}

	/** Wraps with exception handling that for any exception (including unchecked exception that might be different from X) will call handler function to determine the final exception. */
	@Nonnull
	default <Y extends Exception> LLongConsumerX<Y> handleX(ExceptionHandler<Exception, Y> handler) {
		Objects.requireNonNull(handler, Function4U.VALIDATION_MESSAGE_HANDLER);

		return LLongConsumerX.wrapException(this, Exception.class, (ExceptionHandler) handler);
	}

	// </editor-fold>

}