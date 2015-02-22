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
 *
 * @see {@link eu.lunisolar.magma.func.function.to.ToIntFunctionX}
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface ToIntFunction<T> extends java.util.function.ToIntFunction<T>, MetaFunction, PrimitiveCodomain<ToIntFunction<T>> { // NOSONAR

	public static final String DESCRIPTION = "ToIntFunction: int applyAsInt(T t)";

	// Ovverriding methods can cause problems with inference.

	/** Returns desxription of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return ToIntFunction.DESCRIPTION;
	}

	/** Just to mirror the method: Ensures the result is not null */
	default int nonNull(T t) {
		return applyAsInt(t);
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	public static <T> ToIntFunction<T> l(final @Nonnull ToIntFunction<T> lambda) {
		Objects.requireNonNull(lambda, "Argument [lambda] cannot be null.");
		return lambda;
	}

	// <editor-fold desc="wrap">

	/** Wraps JRE instance. */
	@Nonnull
	public static <T> ToIntFunction<T> wrapStd(final java.util.function.ToIntFunction<T> other) {
		return other::applyAsInt;
	}

	/** Wraps opposite (throwing/non-throwing) instance. */
	@Nonnull
	public static <T, X extends Exception> ToIntFunction<T> wrap(final @Nonnull ToIntFunctionX<T, X> other) {
		return (T t) -> {
			try {
				return other.applyAsInt(t);
			} catch (Exception e) {
				throw ExceptionHandler.handleWrapping(e);
			}
		};
	}

	/** Wraps with additional exception handling. */
	@Nonnull
	public static <T, X extends Exception, Y extends RuntimeException> ToIntFunction<T> wrapException(@Nonnull final ToIntFunction<T> other, Class<? extends Exception> exception, ExceptionHandler<Exception, Y> rethrower) {
		return (T t) -> {
			try {
				return other.applyAsInt(t);
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
	default <V1> ToIntFunction<V1> from(@Nonnull final Function<? super V1, ? extends T> before1) {
		Objects.requireNonNull(before1, Function4U.VALIDATION_MESSAGE_BEFORE1);
		return (final V1 v1) -> this.applyAsInt(before1.apply(v1));
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two functions together in a order. */
	@Nonnull
	default <V> Function<T, V> then(@Nonnull IntFunction<? extends V> after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (T t) -> after.apply(this.applyAsInt(t));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default ToByteFunction<T> thenToByte(@Nonnull IntToByteFunction after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (T t) -> after.applyAsByte(this.applyAsInt(t));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default ToShortFunction<T> thenToShort(@Nonnull IntToShortFunction after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (T t) -> after.applyAsShort(this.applyAsInt(t));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default ToIntFunction<T> thenToInt(@Nonnull IntUnaryOperator after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (T t) -> after.applyAsInt(this.applyAsInt(t));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default ToLongFunction<T> thenToLong(@Nonnull IntToLongFunction after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (T t) -> after.applyAsLong(this.applyAsInt(t));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default ToFloatFunction<T> thenToFloat(@Nonnull IntToFloatFunction after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (T t) -> after.applyAsFloat(this.applyAsInt(t));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default ToDoubleFunction<T> thenToDouble(@Nonnull IntToDoubleFunction after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (T t) -> after.applyAsDouble(this.applyAsInt(t));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default ToCharFunction<T> thenToChar(@Nonnull IntToCharFunction after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (T t) -> after.applyAsChar(this.applyAsInt(t));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default Predicate<T> thenToBoolean(@Nonnull IntPredicate after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (T t) -> after.test(this.applyAsInt(t));
	}

	// </editor-fold>

	// <editor-fold desc="variant conversions">

	/** Converts to JRE variant. */
	@Nonnull
	default java.util.function.ToIntFunction<T> std() {
		return this;
	}

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default ToIntFunction<T> nonThrowing() {
		return this;
	}

	/** Converts to throwing variant (RuntimeException). */
	@Nonnull
	default ToIntFunctionX<T, RuntimeException> uncheck() {
		return this::applyAsInt;
	}

	// </editor-fold>

	// <editor-fold desc="exception handling">

	/** Wraps with exception handling that for argument exception class will call function to determine the final exception. */
	@Nonnull
	default <Y extends RuntimeException> ToIntFunction<T> handle(Class<? extends Exception> exception, ExceptionHandler<? super RuntimeException, Y> handler) {
		Objects.requireNonNull(exception, Function4U.VALIDATION_MESSAGE_EXCEPTION);
		Objects.requireNonNull(handler, Function4U.VALIDATION_MESSAGE_HANDLER);

		return ToIntFunction.wrapException(this, exception, (ExceptionHandler) handler);
	}

	/** Wraps with exception handling that for argument exception class will call function to determine the final exception. */
	@Nonnull
	default <Y extends RuntimeException> ToIntFunction<T> handle(ExceptionHandler<? super RuntimeException, Y> handler) {
		Objects.requireNonNull(handler, Function4U.VALIDATION_MESSAGE_HANDLER);

		return ToIntFunction.wrapException(this, Exception.class, (ExceptionHandler) handler);
	}

	// </editor-fold>

}
