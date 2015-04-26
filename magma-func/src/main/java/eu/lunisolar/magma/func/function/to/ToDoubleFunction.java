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
 * Non-throwing functional interface (lambda) ToDoubleFunction for Java 8.
 *
 * Type: function
 *
 * Domain (lvl: 1): T t
 *
 * Co-domain: none
 *
 * @see ToDoubleFunctionX
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface ToDoubleFunction<T> extends java.util.function.ToDoubleFunction<T>, MetaFunction, PrimitiveCodomain<ToDoubleFunction<T>> { // NOSONAR

	public static final String DESCRIPTION = "ToDoubleFunction: double applyAsDouble(T t)";

	// Ovverriding methods can cause problems with inference.

	/** Returns desxription of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return ToDoubleFunction.DESCRIPTION;
	}

	/** Captures arguments but delays the evaluation. */
	default DoubleSupplier capture(T t) {
		return () -> this.applyAsDouble(t);
	}

	/** Just to mirror the method: Ensures the result is not null */
	default double nonNull(T t) {
		return applyAsDouble(t);
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	public static <T> ToDoubleFunction<T> l(final @Nonnull ToDoubleFunction<T> lambda) {
		Objects.requireNonNull(lambda, "Argument [lambda] cannot be null.");
		return lambda;
	}

	// <editor-fold desc="wrap">

	/** Wraps JRE instance. */
	@Nonnull
	public static <T> ToDoubleFunction<T> wrapStd(final java.util.function.ToDoubleFunction<T> other) {
		return other::applyAsDouble;
	}

	/** Wraps opposite (throwing/non-throwing) instance. */
	@Nonnull
	public static <T, X extends Exception> ToDoubleFunction<T> wrap(final @Nonnull ToDoubleFunctionX<T, X> other) {
		return (T t) -> {
			try {
				return other.applyAsDouble(t);
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
	default <V1> ToDoubleFunction<V1> from(@Nonnull final Function<? super V1, ? extends T> before1) {
		Objects.requireNonNull(before1, Function4U.VALIDATION_MESSAGE_BEFORE1);
		return (final V1 v1) -> this.applyAsDouble(before1.apply(v1));
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two functions together in a order. */
	@Nonnull
	default <V> Function<T, V> then(@Nonnull DoubleFunction<? extends V> after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (T t) -> after.apply(this.applyAsDouble(t));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default ToByteFunction<T> thenToByte(@Nonnull DoubleToByteFunction after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (T t) -> after.applyAsByte(this.applyAsDouble(t));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default ToShortFunction<T> thenToShort(@Nonnull DoubleToShortFunction after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (T t) -> after.applyAsShort(this.applyAsDouble(t));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default ToIntFunction<T> thenToInt(@Nonnull DoubleToIntFunction after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (T t) -> after.applyAsInt(this.applyAsDouble(t));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default ToLongFunction<T> thenToLong(@Nonnull DoubleToLongFunction after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (T t) -> after.applyAsLong(this.applyAsDouble(t));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default ToFloatFunction<T> thenToFloat(@Nonnull DoubleToFloatFunction after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (T t) -> after.applyAsFloat(this.applyAsDouble(t));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default ToDoubleFunction<T> thenToDouble(@Nonnull DoubleUnaryOperator after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (T t) -> after.applyAsDouble(this.applyAsDouble(t));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default ToCharFunction<T> thenToChar(@Nonnull DoubleToCharFunction after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (T t) -> after.applyAsChar(this.applyAsDouble(t));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default Predicate<T> thenToBoolean(@Nonnull DoublePredicate after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (T t) -> after.test(this.applyAsDouble(t));
	}

	// </editor-fold>

	// <editor-fold desc="variant conversions">

	/** Converts to JRE variant. */
	@Nonnull
	default java.util.function.ToDoubleFunction<T> std() {
		return this;
	}

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default ToDoubleFunction<T> nonThrowing() {
		return this;
	}

	/** Converts to throwing variant (RuntimeException). */
	@Nonnull
	default ToDoubleFunctionX<T, RuntimeException> uncheck() {
		return this::applyAsDouble;
	}

	/** Dirty way, checked exception will propagate as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default ToDoubleFunction<T> shove() {
		return this;
	}

	// </editor-fold>

	// <editor-fold desc="exception handling">

	/** Wraps with additional exception handling. */
	@Nonnull
	public static <T, X extends Exception, E extends Exception, Y extends RuntimeException> ToDoubleFunction<T> wrapException(@Nonnull final ToDoubleFunction<T> other, Class<E> exception, DoubleSupplier supplier, ExceptionHandler<E, Y> handler) {
		return (T t) -> {
			try {
				return other.applyAsDouble(t);
			} catch (Exception e) {
				try {
					if (supplier != null) {
						return supplier.getAsDouble();
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
	default <E extends Exception, Y extends RuntimeException> ToDoubleFunction<T> handle(Class<E> exception, ExceptionHandler<E, Y> handler) {
		Objects.requireNonNull(exception, Function4U.VALIDATION_MESSAGE_EXCEPTION);
		Objects.requireNonNull(handler, Function4U.VALIDATION_MESSAGE_HANDLER);

		return ToDoubleFunction.wrapException(this, exception, null, (ExceptionHandler) handler);
	}

	/** Wraps with exception handling that for any exception (including unchecked exception that might be different from X) will call handler function to determine the final exception. */
	@Nonnull
	default <Y extends RuntimeException> ToDoubleFunction<T> handle(ExceptionHandler<Exception, Y> handler) {
		Objects.requireNonNull(handler, Function4U.VALIDATION_MESSAGE_HANDLER);

		return ToDoubleFunction.wrapException(this, Exception.class, null, (ExceptionHandler) handler);
	}

	/** Wraps with exception handling that for argument exception class will call supplier and return default value instead for propagating exception.  */
	@Nonnull
	default <E extends Exception, Y extends RuntimeException> ToDoubleFunction<T> handle(Class<E> exception, DoubleSupplier supplier) {
		Objects.requireNonNull(exception, Function4U.VALIDATION_MESSAGE_EXCEPTION);
		Objects.requireNonNull(supplier, Function4U.VALIDATION_MESSAGE_HANDLER);

		return ToDoubleFunction.wrapException(this, exception, supplier, null);
	}

	/** Wraps with exception handling that for any exception will call supplier and return default value instead for propagating exception.  */
	@Nonnull
	default <Y extends RuntimeException> ToDoubleFunction<T> handle(DoubleSupplier supplier) {
		Objects.requireNonNull(supplier, Function4U.VALIDATION_MESSAGE_HANDLER);

		return ToDoubleFunction.wrapException(this, Exception.class, supplier, null);
	}

	// </editor-fold>

}
