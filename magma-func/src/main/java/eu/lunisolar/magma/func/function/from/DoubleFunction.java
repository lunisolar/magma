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

package eu.lunisolar.magma.func.function.from;
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
 * @see {@link eu.lunisolar.magma.func.function.from.DoubleFunctionX}
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface DoubleFunction<R> extends java.util.function.DoubleFunction<R>, MetaFunction { // NOSONAR

	public static final String DESCRIPTION = "DoubleFunction: R apply(double d)";

	// Ovverriding methods can cause problems with inference.

	/** Returns desxription of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return DoubleFunction.DESCRIPTION;
	}

	public static final Supplier<String> NULL_VALUE_MESSAGE_SUPPLIER = () -> "Evaluated value by nonNull() method cannot be null (" + DESCRIPTION + ").";

	/** Ensures the result is not null */
	@Nonnull
	default R nonNull(double d) {
		return Objects.requireNonNull(apply(d), NULL_VALUE_MESSAGE_SUPPLIER);
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	public static <R> DoubleFunction<R> l(final @Nonnull DoubleFunction<R> lambda) {
		Objects.requireNonNull(lambda, "Argument [lambda] cannot be null.");
		return lambda;
	}

	// <editor-fold desc="wrap">

	/** Wraps JRE instance. */
	@Nonnull
	public static <R> DoubleFunction<R> wrapStd(final java.util.function.DoubleFunction<R> other) {
		return other::apply;
	}

	/** Wraps opposite (throwing/non-throwing) instance. */
	@Nonnull
	public static <R, X extends Exception> DoubleFunction<R> wrap(final @Nonnull DoubleFunctionX<R, X> other) {
		return (double d) -> {
			try {
				return other.apply(d);
			} catch (Exception e) {
				throw ExceptionHandler.handleWrapping(e);
			}
		};
	}

	/** Wraps with additional exception handling. */
	@Nonnull
	public static <R, X extends Exception, Y extends RuntimeException> DoubleFunction<R> wrapException(@Nonnull final DoubleFunction<R> other, Class<? extends Exception> exception, ExceptionHandler<Exception, Y> rethrower) {
		return (double d) -> {
			try {
				return other.apply(d);
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
	default DoubleFunction<R> fromDouble(@Nonnull final DoubleUnaryOperator before1) {
		Objects.requireNonNull(before1, Function4U.VALIDATION_MESSAGE_BEFORE1);
		return (final double v1) -> this.apply(before1.applyAsDouble(v1));
	}

	/**
	 * Allows to manipulate the domain of the functyion.
	 */
	@Nonnull
	default <V1> Function<V1, R> from(@Nonnull final ToDoubleFunction<? super V1> before1) {
		Objects.requireNonNull(before1, Function4U.VALIDATION_MESSAGE_BEFORE1);
		return (V1 v1) -> this.apply(before1.applyAsDouble(v1));
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two functions together in a order. */
	@Nonnull
	default <V> DoubleFunction<V> then(@Nonnull Function<? super R, ? extends V> after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (double d) -> after.apply(this.apply(d));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default DoubleConsumer then(@Nonnull Consumer<? super R> after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (double d) -> after.accept(this.apply(d));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default DoubleToByteFunction thenToByte(@Nonnull ToByteFunction<? super R> after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (double d) -> after.applyAsByte(this.apply(d));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default DoubleToShortFunction thenToShort(@Nonnull ToShortFunction<? super R> after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (double d) -> after.applyAsShort(this.apply(d));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default DoubleToIntFunction thenToInt(@Nonnull ToIntFunction<? super R> after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (double d) -> after.applyAsInt(this.apply(d));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default DoubleToLongFunction thenToLong(@Nonnull ToLongFunction<? super R> after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (double d) -> after.applyAsLong(this.apply(d));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default DoubleToFloatFunction thenToFloat(@Nonnull ToFloatFunction<? super R> after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (double d) -> after.applyAsFloat(this.apply(d));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default DoubleUnaryOperator thenToDouble(@Nonnull ToDoubleFunction<? super R> after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (double d) -> after.applyAsDouble(this.apply(d));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default DoubleToCharFunction thenToChar(@Nonnull ToCharFunction<? super R> after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (double d) -> after.applyAsChar(this.apply(d));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default DoublePredicate thenToBoolean(@Nonnull Predicate<? super R> after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (double d) -> after.test(this.apply(d));
	}

	// </editor-fold>

	// <editor-fold desc="variant conversions">

	/** Converts to JRE variant. */
	@Nonnull
	default java.util.function.DoubleFunction<R> std() {
		return this;
	}

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default DoubleFunction<R> nonThrowing() {
		return this;
	}

	/** Converts to throwing variant (RuntimeException). */
	@Nonnull
	default DoubleFunctionX<R, RuntimeException> uncheck() {
		return this::apply;
	}

	// </editor-fold>

	@Nonnull
	default DoubleFunction<R> nonNullable() {
		return new NonNullDoubleFunction(this);
	}

	// <editor-fold desc="exception handling">

	/** Wraps with exception handling that for argument exception class will call function to determine the final exception. */
	@Nonnull
	default <Y extends RuntimeException> DoubleFunction<R> handle(Class<? extends Exception> exception, ExceptionHandler<? super RuntimeException, Y> handler) {
		Objects.requireNonNull(exception, Function4U.VALIDATION_MESSAGE_EXCEPTION);
		Objects.requireNonNull(handler, Function4U.VALIDATION_MESSAGE_HANDLER);

		return DoubleFunction.wrapException(this, exception, (ExceptionHandler) handler);
	}

	/** Wraps with exception handling that for argument exception class will call function to determine the final exception. */
	@Nonnull
	default <Y extends RuntimeException> DoubleFunction<R> handle(ExceptionHandler<? super RuntimeException, Y> handler) {
		Objects.requireNonNull(handler, Function4U.VALIDATION_MESSAGE_HANDLER);

		return DoubleFunction.wrapException(this, Exception.class, (ExceptionHandler) handler);
	}

	// </editor-fold>

}
