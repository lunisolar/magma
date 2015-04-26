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

package eu.lunisolar.magma.func.operator.ternary;
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
 * Function category: operator
 * Throwing interface/lambda variant: TernaryOperatorX
 *
 * @see TernaryOperatorX
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface TernaryOperator<T> extends MetaOperator { // NOSONAR

	public static final String DESCRIPTION = "TernaryOperator: T apply(T t1,T t2,T t3)";

	@Nullable
	public T apply(T t1, T t2, T t3);

	/** Returns desxription of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return TernaryOperator.DESCRIPTION;
	}

	/** Captures arguments but delays the evaluation. */
	default Supplier<T> capture(T t1, T t2, T t3) {
		return () -> this.apply(t1, t2, t3);
	}

	public static final Supplier<String> NULL_VALUE_MESSAGE_SUPPLIER = () -> "Evaluated value by nonNull() method cannot be null (" + DESCRIPTION + ").";

	/** Ensures the result is not null */
	@Nonnull
	default T nonNull(T t1, T t2, T t3) {
		return Objects.requireNonNull(apply(t1, t2, t3), NULL_VALUE_MESSAGE_SUPPLIER);
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	public static <T> TernaryOperator<T> l(final @Nonnull TernaryOperator<T> lambda) {
		Objects.requireNonNull(lambda, "Argument [lambda] cannot be null.");
		return lambda;
	}

	// <editor-fold desc="wrap">

	/** Wraps opposite (throwing/non-throwing) instance. */
	@Nonnull
	public static <T, X extends Exception> TernaryOperator<T> wrap(final @Nonnull TernaryOperatorX<T, X> other) {
		return (T t1, T t2, T t3) -> {
			try {
				return other.apply(t1, t2, t3);
			} catch (Exception e) {
				throw ExceptionHandler.handleWrapping(e);
			}
		};
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two operators together in a order. */
	@Nonnull
	default <V> TriFunction<T, T, T, V> then(@Nonnull Function<? super T, ? extends V> after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (T t1, T t2, T t3) -> after.apply(this.apply(t1, t2, t3));
	}

	// </editor-fold>

	// <editor-fold desc="variant conversions">

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default TernaryOperator<T> nonThrowing() {
		return this;
	}

	/** Converts to throwing variant (RuntimeException). */
	@Nonnull
	default TernaryOperatorX<T, RuntimeException> uncheck() {
		return this::apply;
	}

	/** Dirty way, checked exception will propagate as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default TernaryOperator<T> shove() {
		return this;
	}

	// </editor-fold>

	@Nonnull
	default TernaryOperator<T> nonNullable() {
		return new NonNullTernaryOperator(this);
	}

	// <editor-fold desc="exception handling">

	/** Wraps with additional exception handling. */
	@Nonnull
	public static <T, X extends Exception, E extends Exception, Y extends RuntimeException> TernaryOperator<T> wrapException(@Nonnull final TernaryOperator<T> other, Class<E> exception, Supplier<T> supplier, ExceptionHandler<E, Y> handler) {
		return (T t1, T t2, T t3) -> {
			try {
				return other.apply(t1, t2, t3);
			} catch (Exception e) {
				try {
					if (supplier != null) {
						return supplier.get();
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
	default <E extends Exception, Y extends RuntimeException> TernaryOperator<T> handle(Class<E> exception, ExceptionHandler<E, Y> handler) {
		Objects.requireNonNull(exception, Function4U.VALIDATION_MESSAGE_EXCEPTION);
		Objects.requireNonNull(handler, Function4U.VALIDATION_MESSAGE_HANDLER);

		return TernaryOperator.wrapException(this, exception, null, (ExceptionHandler) handler);
	}

	/** Wraps with exception handling that for any exception (including unchecked exception that might be different from X) will call handler function to determine the final exception. */
	@Nonnull
	default <Y extends RuntimeException> TernaryOperator<T> handle(ExceptionHandler<Exception, Y> handler) {
		Objects.requireNonNull(handler, Function4U.VALIDATION_MESSAGE_HANDLER);

		return TernaryOperator.wrapException(this, Exception.class, null, (ExceptionHandler) handler);
	}

	/** Wraps with exception handling that for argument exception class will call supplier and return default value instead for propagating exception.  */
	@Nonnull
	default <E extends Exception, Y extends RuntimeException> TernaryOperator<T> handle(Class<E> exception, Supplier<T> supplier) {
		Objects.requireNonNull(exception, Function4U.VALIDATION_MESSAGE_EXCEPTION);
		Objects.requireNonNull(supplier, Function4U.VALIDATION_MESSAGE_HANDLER);

		return TernaryOperator.wrapException(this, exception, supplier, null);
	}

	/** Wraps with exception handling that for any exception will call supplier and return default value instead for propagating exception.  */
	@Nonnull
	default <Y extends RuntimeException> TernaryOperator<T> handle(Supplier<T> supplier) {
		Objects.requireNonNull(supplier, Function4U.VALIDATION_MESSAGE_HANDLER);

		return TernaryOperator.wrapException(this, Exception.class, supplier, null);
	}

	// </editor-fold>

}
