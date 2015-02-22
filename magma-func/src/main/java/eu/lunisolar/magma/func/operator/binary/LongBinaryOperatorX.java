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
 * @see {@link eu.lunisolar.magma.func.operator.binary.LongBinaryOperator}
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LongBinaryOperatorX<X extends Exception> extends MetaOperator, PrimitiveCodomain<LongBinaryOperatorX<X>>, MetaThrowingInterface<X> { // NOSONAR

	public static final String DESCRIPTION = "LongBinaryOperatorX: long applyAsLong(long l1,long l2) throws X";

	public long applyAsLong(long l1, long l2) throws X;

	/** Returns desxription of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LongBinaryOperatorX.DESCRIPTION;
	}

	/** Just to mirror the method: Ensures the result is not null */
	default long nonNull(long l1, long l2) throws X {
		return applyAsLong(l1, l2);
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	public static <X extends Exception> LongBinaryOperatorX<X> lX(final @Nonnull LongBinaryOperatorX<X> lambda) {
		Objects.requireNonNull(lambda, "Argument [lambda] cannot be null.");
		return lambda;
	}

	// <editor-fold desc="wrap">

	/** Wraps JRE instance. */
	@Nonnull
	public static <X extends Exception> LongBinaryOperatorX<X> wrapStd(final java.util.function.LongBinaryOperator other) {
		return other::applyAsLong;
	}

	/** Wraps opposite (throwing/non-throwing) instance. */
	@Nonnull
	public static <X extends Exception> LongBinaryOperatorX<X> wrapX(final @Nonnull LongBinaryOperator other) {
		return other::applyAsLong;
	}

	/** Wraps with additional exception handling. */
	@Nonnull
	public static <X extends Exception, Y extends Exception> LongBinaryOperatorX<Y> wrapException(@Nonnull final LongBinaryOperatorX<X> other, Class<? extends Exception> exception, ExceptionHandler<Exception, Y> rethrower) {
		return (long l1, long l2) -> {
			try {
				return other.applyAsLong(l1, l2);
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
	public static <X extends Exception> LongBinaryOperatorX<X> min() {
		return Long::min;
	}

	/**
	 * @see {@link java.util.function.BinaryOperator#maxBy()}
	 */
	@Nonnull
	public static <X extends Exception> LongBinaryOperatorX<X> max() {
		return Long::max;
	}

	// </editor-fold>

	// <editor-fold desc="compose (functional)">

	/**
	 * Allows to manipulate the domain of the functyion.
	 */
	@Nonnull
	default LongBinaryOperatorX<X> fromLong(@Nonnull final LongUnaryOperatorX<X> before1, @Nonnull final LongUnaryOperatorX<X> before2) {
		Objects.requireNonNull(before1, Function4U.VALIDATION_MESSAGE_BEFORE1);
		Objects.requireNonNull(before2, Function4U.VALIDATION_MESSAGE_BEFORE2);
		return (final long v1, final long v2) -> this.applyAsLong(before1.applyAsLong(v1), before2.applyAsLong(v2));
	}

	/**
	 * Allows to manipulate the domain of the functyion.
	 */
	@Nonnull
	default <V1, V2> ToLongBiFunctionX<V1, V2, X> from(@Nonnull final ToLongFunctionX<? super V1, X> before1, @Nonnull final ToLongFunctionX<? super V2, X> before2) {
		Objects.requireNonNull(before1, Function4U.VALIDATION_MESSAGE_BEFORE1);
		Objects.requireNonNull(before2, Function4U.VALIDATION_MESSAGE_BEFORE2);
		return (V1 v1, V2 v2) -> this.applyAsLong(before1.applyAsLong(v1), before2.applyAsLong(v2));
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two operators together in a order. */
	@Nonnull
	default <V> LongBiFunctionX<V, X> then(@Nonnull LongFunctionX<? extends V, X> after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (long l1, long l2) -> after.apply(this.applyAsLong(l1, l2));
	}

	// </editor-fold>

	// <editor-fold desc="variant conversions">

	/** Converts to JRE variant. */
	@Nonnull
	default java.util.function.LongBinaryOperator std() {
		return LongBinaryOperator.wrap(this)::applyAsLong;
	}

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default LongBinaryOperator nonThrowing() {
		return LongBinaryOperator.wrap(this);
	}

	/** Converts to throwing variant (RuntimeException). */
	@Nonnull
	default LongBinaryOperatorX<RuntimeException> uncheck() {
		return nonThrowing()::applyAsLong;
	}

	// </editor-fold>

	// <editor-fold desc="exception handling">

	/** Wraps with exception handling that for argument exception class will call function to determine the final exception. */
	@Nonnull
	default <Y extends Exception> LongBinaryOperatorX<Y> handle(Class<? extends Exception> exception, ExceptionHandler<? super X, Y> handler) {
		Objects.requireNonNull(exception, Function4U.VALIDATION_MESSAGE_EXCEPTION);
		Objects.requireNonNull(handler, Function4U.VALIDATION_MESSAGE_HANDLER);

		return LongBinaryOperatorX.wrapException(this, exception, (ExceptionHandler) handler);
	}

	/** Wraps with exception handling that for argument exception class will call function to determine the final exception. */
	@Nonnull
	default <Y extends Exception> LongBinaryOperatorX<Y> handle(ExceptionHandler<? super X, Y> handler) {
		Objects.requireNonNull(handler, Function4U.VALIDATION_MESSAGE_HANDLER);

		return LongBinaryOperatorX.wrapException(this, Exception.class, (ExceptionHandler) handler);
	}

	// </editor-fold>

}
