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

package eu.lunisolar.magma.func.consumer;

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
 * Non-throwing functional interface (lambda) TriConsumer for Java 8.
 *
 * Type: consumer
 *
 * Domain (lvl: 3): T1 t1,T2 t2,T3 t3
 *
 * Co-domain: none
 *
 * @see TriConsumerX
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface TriConsumer<T1, T2, T3> extends TriConsumerX<T1, T2, T3, RuntimeException>, MetaConsumer, MetaInterface.NonThrowing {

	public static final String DESCRIPTION = "TriConsumer: void accept(T1 t1,T2 t2,T3 t3)";

	// Ovverriding methods can cause problems with inference.

	/** Returns desxription of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return TriConsumer.DESCRIPTION;
	}

	/** Captures arguments but delays the evaluation. */
	default Action capture(T1 t1, T2 t2, T3 t3) {
		return () -> this.accept(t1, t2, t3);
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	public static <T1, T2, T3> TriConsumer<T1, T2, T3> l(final @Nonnull TriConsumer<T1, T2, T3> lambda) {
		Objects.requireNonNull(lambda, "Argument [lambda] cannot be null.");
		return lambda;
	}

	// <editor-fold desc="wrap">

	/** Wraps opposite (throwing/non-throwing) instance. */
	@Nonnull
	public static <T1, T2, T3, X extends Exception> TriConsumer<T1, T2, T3> wrap(final @Nonnull TriConsumerX<T1, T2, T3, X> other) {
		return (T1 t1, T2 t2, T3 t3) -> {
			try {
				other.accept(t1, t2, t3);
			} catch (Exception e) {
				throw ExceptionHandler.handleWrapping(e);
			}
		};
	}

	// </editor-fold>

	// <editor-fold desc="compose (functional)">

	/**
	 * Allows to manipulate the domain of the functyion.
	 */
	@Nonnull
	default <V1, V2, V3> TriConsumer<V1, V2, V3> from(@Nonnull final Function<? super V1, ? extends T1> before1, @Nonnull final Function<? super V2, ? extends T2> before2, @Nonnull final Function<? super V3, ? extends T3> before3) {
		Objects.requireNonNull(before1, Function4U.VALIDATION_MESSAGE_BEFORE1);
		Objects.requireNonNull(before2, Function4U.VALIDATION_MESSAGE_BEFORE2);
		Objects.requireNonNull(before3, Function4U.VALIDATION_MESSAGE_BEFORE3);
		return (final V1 v1, final V2 v2, final V3 v3) -> this.accept(before1.apply(v1), before2.apply(v2), before3.apply(v3));
	}

	// </editor-fold>

	// <editor-fold desc="andThen (consumer/action)">

	/** Combines two consumers together in a order. */
	@Nonnull
	default TriConsumer<T1, T2, T3> andThen(@Nonnull TriConsumer<? super T1, ? super T2, ? super T3> after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (T1 t1, T2 t2, T3 t3) -> {
			this.accept(t1, t2, t3);
			after.accept(t1, t2, t3);
		};
	}

	// </editor-fold>
	// <editor-fold desc="variant conversions">

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default TriConsumer<T1, T2, T3> nonThrowing() {
		return this;
	}

	/** Converts to throwing variant (RuntimeException). */
	@Nonnull
	default TriConsumerX<T1, T2, T3, RuntimeException> uncheck() {
		return (TriConsumerX) this;
	}

	/** Dirty way, checked exception will propagate as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default TriConsumer<T1, T2, T3> shove() {
		return this;
	}

	// </editor-fold>

	// <editor-fold desc="exception handling">

	/** Wraps with additional exception handling. */
	@Nonnull
	public static <T1, T2, T3, X extends Exception, E extends Exception, Y extends RuntimeException> TriConsumer<T1, T2, T3> wrapException(@Nonnull final TriConsumer<T1, T2, T3> other, Class<E> exception, ExceptionHandler<E, Y> handler) {
		return (T1 t1, T2 t2, T3 t3) -> {
			try {
				other.accept(t1, t2, t3);
			} catch (Exception e) {
				throw ExceptionHandler.handle(exception, Objects.requireNonNull(handler), (E) e);
			}
		};
	}

	/** Wraps with exception handling that for argument exception class will call function to determine the final exception. */
	@Nonnull
	default <E extends Exception, Y extends RuntimeException> TriConsumer<T1, T2, T3> handle(Class<E> exception, ExceptionHandler<E, Y> handler) {
		Objects.requireNonNull(exception, Function4U.VALIDATION_MESSAGE_EXCEPTION);
		Objects.requireNonNull(handler, Function4U.VALIDATION_MESSAGE_HANDLER);

		return TriConsumer.wrapException(this, exception, (ExceptionHandler) handler);
	}

	/** Wraps with exception handling that for any exception (including unchecked exception that might be different from X) will call handler function to determine the final exception. */
	@Nonnull
	default <Y extends RuntimeException> TriConsumer<T1, T2, T3> handle(ExceptionHandler<Exception, Y> handler) {
		Objects.requireNonNull(handler, Function4U.VALIDATION_MESSAGE_HANDLER);

		return TriConsumer.wrapException(this, Exception.class, (ExceptionHandler) handler);
	}

	// </editor-fold>

}
