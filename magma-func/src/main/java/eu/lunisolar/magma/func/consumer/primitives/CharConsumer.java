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

package eu.lunisolar.magma.func.consumer.primitives;

import javax.annotation.Nonnull; // NOSONAR
import javax.annotation.Nullable; // NOSONAR
import java.util.Objects; // NOSONAR
import eu.lunisolar.magma.basics.*; //NOSONAR
import eu.lunisolar.magma.func.*; // NOSONAR
import eu.lunisolar.magma.basics.meta.*; // NOSONAR
import eu.lunisolar.magma.basics.meta.domains.*; // NOSONAR
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
 * A consumer.
 * @see {@link eu.lunisolar.magma.func.consumer.primitives.CharConsumerX}
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface CharConsumer extends MetaConsumer {

	public static final String DESCRIPTION = "CharConsumer: void accept(char c)";

	public void accept(char c);

	/** Returns desxription of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return CharConsumer.DESCRIPTION;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	public static CharConsumer l(final @Nonnull CharConsumer lambda) {
		Objects.requireNonNull(lambda, "Argument [lambda] cannot be null.");
		return lambda;
	}

	// <editor-fold desc="wrap">

	/** Wraps opposite (throwing/non-throwing) instance. */
	@Nonnull
	public static <X extends Exception> CharConsumer wrap(final @Nonnull CharConsumerX<X> other) {
		return (char c) -> {
			try {
				other.accept(c);
			} catch (Exception e) {
				throw ExceptionHandler.handleWrapping(e);
			}
		};
	}

	/** Wraps with additional exception handling. */
	@Nonnull
	public static <X extends Exception, Y extends RuntimeException> CharConsumer wrapException(@Nonnull final CharConsumer other, Class<? extends Exception> exception, ExceptionHandler<Exception, Y> rethrower) {
		return (char c) -> {
			try {
				other.accept(c);
			} catch (Exception e) {
				throw ExceptionHandler.handle(exception, rethrower, e);
			}
		};
	}

	// </editor-fold>

	// <editor-fold desc="compose (functional)">

	/**
	 * Allows to manipulate the domain of the functyion.
	 */
	@Nonnull
	default CharConsumer fromChar(@Nonnull final CharUnaryOperator before1) {
		Objects.requireNonNull(before1, Function4U.VALIDATION_MESSAGE_BEFORE1);
		return (final char v1) -> this.accept(before1.applyAsChar(v1));
	}

	/**
	 * Allows to manipulate the domain of the functyion.
	 */
	@Nonnull
	default <V1> Consumer<V1> from(@Nonnull final ToCharFunction<? super V1> before1) {
		Objects.requireNonNull(before1, Function4U.VALIDATION_MESSAGE_BEFORE1);
		return (V1 v1) -> this.accept(before1.applyAsChar(v1));
	}

	// </editor-fold>

	// <editor-fold desc="andThen (consumer/action)">

	/** Combines two consumers together in a order. */
	@Nonnull
	default CharConsumer andThen(@Nonnull CharConsumer after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (char c) -> {
			this.accept(c);
			after.accept(c);
		};
	}

	// </editor-fold>
	// <editor-fold desc="variant conversions">

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default CharConsumer nonThrowing() {
		return this;
	}

	/** Converts to throwing variant (RuntimeException). */
	@Nonnull
	default CharConsumerX<RuntimeException> uncheck() {
		return this::accept;
	}

	// </editor-fold>

	// <editor-fold desc="exception handling">

	/** Wraps with exception handling that for argument exception class will call function to determine the final exception. */
	@Nonnull
	default <Y extends RuntimeException> CharConsumer handle(Class<? extends Exception> exception, ExceptionHandler<? super RuntimeException, Y> handler) {
		Objects.requireNonNull(exception, Function4U.VALIDATION_MESSAGE_EXCEPTION);
		Objects.requireNonNull(handler, Function4U.VALIDATION_MESSAGE_HANDLER);

		return CharConsumer.wrapException(this, exception, (ExceptionHandler) handler);
	}

	/** Wraps with exception handling that for argument exception class will call function to determine the final exception. */
	@Nonnull
	default <Y extends RuntimeException> CharConsumer handle(ExceptionHandler<? super RuntimeException, Y> handler) {
		Objects.requireNonNull(handler, Function4U.VALIDATION_MESSAGE_HANDLER);

		return CharConsumer.wrapException(this, Exception.class, (ExceptionHandler) handler);
	}

	// </editor-fold>

}
