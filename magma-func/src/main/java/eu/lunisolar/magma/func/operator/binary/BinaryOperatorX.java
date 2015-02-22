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

package eu.lunisolar.magma.func.operator.binary;
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
 *
 * @see {@link eu.lunisolar.magma.func.operator.binary.BinaryOperator}
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface BinaryOperatorX<T, X extends Exception> extends BiFunctionX<T, T, T, X>, MetaOperator, MetaThrowingInterface<X> { // NOSONAR

	public static final String DESCRIPTION = "BinaryOperatorX: T apply(T t1,T t2) throws X";

	// Ovverriding methods can cause problems with inference.

	/** Returns desxription of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return BinaryOperatorX.DESCRIPTION;
	}

	public static final Supplier<String> NULL_VALUE_MESSAGE_SUPPLIER = () -> "Evaluated value by nonNull() method cannot be null (" + DESCRIPTION + ").";

	/** Ensures the result is not null */
	@Nonnull
	default T nonNull(T t1, T t2) throws X {
		return Objects.requireNonNull(apply(t1, t2), NULL_VALUE_MESSAGE_SUPPLIER);
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	public static <T, X extends Exception> BinaryOperatorX<T, X> lX(final @Nonnull BinaryOperatorX<T, X> lambda) {
		Objects.requireNonNull(lambda, "Argument [lambda] cannot be null.");
		return lambda;
	}

	// <editor-fold desc="wrap">

	/** Wraps JRE instance. */
	@Nonnull
	public static <T, X extends Exception> BinaryOperatorX<T, X> wrapStd(final java.util.function.BinaryOperator<T> other) {
		return other::apply;
	}

	/** Wraps opposite (throwing/non-throwing) instance. */
	@Nonnull
	public static <T, X extends Exception> BinaryOperatorX<T, X> wrapX(final @Nonnull BinaryOperator<T> other) {
		return other::apply;
	}

	/** Wraps with additional exception handling. */
	@Nonnull
	public static <T, X extends Exception, Y extends Exception> BinaryOperatorX<T, Y> wrapException(@Nonnull final BinaryOperatorX<T, X> other, Class<? extends Exception> exception, ExceptionHandler<Exception, Y> rethrower) {
		return (T t1, T t2) -> {
			try {
				return other.apply(t1, t2);
			} catch (Exception e) {
				throw ExceptionHandler.handle(exception, rethrower, e);
			}
		};
	}

	// </editor-fold>
	// <editor-fold desc="minmax/logical">

	/**
	 * @see {@link java.util.function.BinaryOperator#minBy()}
	 */
	@Nonnull
	public static <T, X extends Exception> BinaryOperatorX<T, X> minBy(@Nonnull Comparator<? super T> comparator) {
		Objects.requireNonNull(comparator, Function4U.VALIDATION_MESSAGE_COMPARATOR);
		return (a, b) -> comparator.compare(a, b) <= 0 ? a : b;
	}

	/**
	 * @see {@link java.util.function.BinaryOperator#maxBy()}
	 */
	@Nonnull
	public static <T, X extends Exception> BinaryOperatorX<T, X> maxBy(@Nonnull Comparator<? super T> comparator) {
		Objects.requireNonNull(comparator, Function4U.VALIDATION_MESSAGE_COMPARATOR);
		return (a, b) -> comparator.compare(a, b) >= 0 ? a : b;
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two operators together in a order. */
	@Nonnull
	default <V> BiFunctionX<T, T, V, X> then(@Nonnull FunctionX<? super T, ? extends V, X> after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (T t1, T t2) -> after.apply(this.apply(t1, t2));
	}

	// </editor-fold>

	// <editor-fold desc="variant conversions">

	/** Converts to JRE variant. */
	@Nonnull
	default java.util.function.BinaryOperator<T> std() {
		return BinaryOperator.wrap(this)::apply;
	}

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default BinaryOperator<T> nonThrowing() {
		return BinaryOperator.wrap(this);
	}

	/** Converts to throwing variant (RuntimeException). */
	@Nonnull
	default BinaryOperatorX<T, RuntimeException> uncheck() {
		return nonThrowing()::apply;
	}

	// </editor-fold>

	@Nonnull
	default BinaryOperatorX<T, X> nonNullableX() {
		return new NonNullBinaryOperatorX(this);
	}

	// <editor-fold desc="exception handling">

	/** Wraps with exception handling that for argument exception class will call function to determine the final exception. */
	@Nonnull
	default <Y extends Exception> BinaryOperatorX<T, Y> handle(Class<? extends Exception> exception, ExceptionHandler<? super X, Y> handler) {
		Objects.requireNonNull(exception, Function4U.VALIDATION_MESSAGE_EXCEPTION);
		Objects.requireNonNull(handler, Function4U.VALIDATION_MESSAGE_HANDLER);

		return BinaryOperatorX.wrapException(this, exception, (ExceptionHandler) handler);
	}

	/** Wraps with exception handling that for argument exception class will call function to determine the final exception. */
	@Nonnull
	default <Y extends Exception> BinaryOperatorX<T, Y> handle(ExceptionHandler<? super X, Y> handler) {
		Objects.requireNonNull(handler, Function4U.VALIDATION_MESSAGE_HANDLER);

		return BinaryOperatorX.wrapException(this, Exception.class, (ExceptionHandler) handler);
	}

	// </editor-fold>

}
