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

package eu.lunisolar.magma.func.function.to;
import javax.annotation.Nonnull; // NOSONAR
import javax.annotation.Nullable; // NOSONAR
import java.util.Comparator; // NOSONAR
import java.util.Objects; // NOSONAR
import eu.lunisolar.magma.basics.*; //NOSONAR
import eu.lunisolar.magma.basics.builder.*; // NOSONAR
import eu.lunisolar.magma.basics.meta.*; // NOSONAR
import eu.lunisolar.magma.basics.meta.domains.*; // NOSONAR
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
 * Non-throwing functional interface (lambda) ToLongBiFunction for Java 8.
 *
 * Type: function
 *
 * Domain (lvl: 2): T1 t1,T2 t2
 *
 * Co-domain: none
 *
 * @see ToLongBiFunctionX
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface ToLongBiFunction<T1, T2> extends java.util.function.ToLongBiFunction<T1, T2>, MetaFunction, PrimitiveCodomain<ToLongBiFunction<T1, T2>> { // NOSONAR

	public static final String DESCRIPTION = "ToLongBiFunction: long applyAsLong(T1 t1,T2 t2)";

	// Ovverriding methods can cause problems with inference.

	/** Returns desxription of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return ToLongBiFunction.DESCRIPTION;
	}

	/** Captures arguments but delays the evaluation. */
	default LongSupplier capture(T1 t1, T2 t2) {
		return () -> this.applyAsLong(t1, t2);
	}

	public static <T1, T2> ToLongBiFunction<T1, T2> constant(long r) {
		return (t1, t2) -> r;
	}

	/** Just to mirror the method: Ensures the result is not null */
	default long nonNull(T1 t1, T2 t2) {
		return applyAsLong(t1, t2);
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	public static <T1, T2> ToLongBiFunction<T1, T2> l(final @Nonnull ToLongBiFunction<T1, T2> lambda) {
		Objects.requireNonNull(lambda, "Argument [lambda] cannot be null.");
		return lambda;
	}

	// <editor-fold desc="wrap">

	/** Wraps JRE instance. */
	@Nonnull
	public static <T1, T2> ToLongBiFunction<T1, T2> wrapStd(final java.util.function.ToLongBiFunction<T1, T2> other) {
		return other::applyAsLong;
	}

	/** Wraps opposite (throwing/non-throwing) instance. */
	@Nonnull
	public static <T1, T2, X extends Exception> ToLongBiFunction<T1, T2> wrap(final @Nonnull ToLongBiFunctionX<T1, T2, X> other) {
		return (T1 t1, T2 t2) -> {
			try {
				return other.applyAsLong(t1, t2);
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
	default <V1, V2> ToLongBiFunction<V1, V2> from(@Nonnull final Function<? super V1, ? extends T1> before1, @Nonnull final Function<? super V2, ? extends T2> before2) {
		Objects.requireNonNull(before1, Function4U.VALIDATION_MESSAGE_BEFORE1);
		Objects.requireNonNull(before2, Function4U.VALIDATION_MESSAGE_BEFORE2);
		return (final V1 v1, final V2 v2) -> this.applyAsLong(before1.apply(v1), before2.apply(v2));
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two functions together in a order. */
	@Nonnull
	default <V> BiFunction<T1, T2, V> then(@Nonnull LongFunction<? extends V> after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (T1 t1, T2 t2) -> after.apply(this.applyAsLong(t1, t2));
	}

	// </editor-fold>

	// <editor-fold desc="variant conversions">

	/** Converts to JRE variant. */
	@Nonnull
	default java.util.function.ToLongBiFunction<T1, T2> std() {
		return this;
	}

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default ToLongBiFunction<T1, T2> nonThrowing() {
		return this;
	}

	/** Converts to throwing variant (RuntimeException). */
	@Nonnull
	default ToLongBiFunctionX<T1, T2, RuntimeException> uncheck() {
		return this::applyAsLong;
	}

	/** Dirty way, checked exception will propagate as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default ToLongBiFunction<T1, T2> shove() {
		return this;
	}

	// </editor-fold>

	// <editor-fold desc="exception handling">

	/** Wraps with additional exception handling. */
	@Nonnull
	public static <T1, T2, X extends Exception, E extends Exception, Y extends RuntimeException> ToLongBiFunction<T1, T2> wrapException(@Nonnull final ToLongBiFunction<T1, T2> other, Class<E> exception, LongSupplier supplier,
			ExceptionHandler<E, Y> handler) {
		return (T1 t1, T2 t2) -> {
			try {
				return other.applyAsLong(t1, t2);
			} catch (Exception e) {
				try {
					if (supplier != null) {
						return supplier.getAsLong();
					}
				} catch (Exception supplierException) {
					throw new ExceptionNotHandled("Provided supplier (as a default value supplier/exception handler) failed on its own.", supplierException);
				}
				throw ExceptionHandler.handle(exception, Objects.requireNonNull(handler), (E) e);
			}
		};
	}

	/** Wraps with exception handling that for argument exception class will call function to determine the final exception. */
	@Nonnull
	default <E extends Exception, Y extends RuntimeException> ToLongBiFunction<T1, T2> handle(Class<E> exception, ExceptionHandler<E, Y> handler) {
		Objects.requireNonNull(exception, Function4U.VALIDATION_MESSAGE_EXCEPTION);
		Objects.requireNonNull(handler, Function4U.VALIDATION_MESSAGE_HANDLER);

		return ToLongBiFunction.wrapException(this, exception, null, (ExceptionHandler) handler);
	}

	/** Wraps with exception handling that for any exception (including unchecked exception that might be different from X) will call handler function to determine the final exception. */
	@Nonnull
	default <Y extends RuntimeException> ToLongBiFunction<T1, T2> handle(ExceptionHandler<Exception, Y> handler) {
		Objects.requireNonNull(handler, Function4U.VALIDATION_MESSAGE_HANDLER);

		return ToLongBiFunction.wrapException(this, Exception.class, null, (ExceptionHandler) handler);
	}

	/** Wraps with exception handling that for argument exception class will call supplier and return default value instead for propagating exception.  */
	@Nonnull
	default <E extends Exception, Y extends RuntimeException> ToLongBiFunction<T1, T2> handle(Class<E> exception, LongSupplier supplier) {
		Objects.requireNonNull(exception, Function4U.VALIDATION_MESSAGE_EXCEPTION);
		Objects.requireNonNull(supplier, Function4U.VALIDATION_MESSAGE_HANDLER);

		return ToLongBiFunction.wrapException(this, exception, supplier, null);
	}

	/** Wraps with exception handling that for any exception will call supplier and return default value instead for propagating exception.  */
	@Nonnull
	default <Y extends RuntimeException> ToLongBiFunction<T1, T2> handle(LongSupplier supplier) {
		Objects.requireNonNull(supplier, Function4U.VALIDATION_MESSAGE_HANDLER);

		return ToLongBiFunction.wrapException(this, Exception.class, supplier, null);
	}

	// </editor-fold>

}
