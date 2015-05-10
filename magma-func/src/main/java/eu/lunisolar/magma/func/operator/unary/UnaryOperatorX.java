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

package eu.lunisolar.magma.func.operator.unary;
import javax.annotation.Nonnull; // NOSONAR
import javax.annotation.Nullable; // NOSONAR
import java.util.Comparator; // NOSONAR
import java.util.Objects; // NOSONAR
import eu.lunisolar.magma.basics.*; //NOSONAR
import eu.lunisolar.magma.basics.builder.*; // NOSONAR
import eu.lunisolar.magma.basics.meta.*; // NOSONAR
import eu.lunisolar.magma.basics.meta.functional.*; // NOSONAR
import eu.lunisolar.magma.basics.meta.functional.type.*; // NOSONAR
import eu.lunisolar.magma.basics.meta.functional.domain.*; // NOSONAR
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
 * Throwing functional interface (lambda) UnaryOperatorX for Java 8.
 *
 * Type: operator
 *
 * Domain (lvl: 1): T t
 *
 * Co-domain: T
 *
 * @see UnaryOperator
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface UnaryOperatorX<T, X extends Exception> extends FunctionX<T, T, X>, MetaOperator, MetaInterface.Throwing<X> { // NOSONAR

	public static final String DESCRIPTION = "UnaryOperatorX: T apply(T t) throws X";

	// Ovverriding methods can cause problems with inference.

	/** Returns desxription of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return UnaryOperatorX.DESCRIPTION;
	}

	/** Captures arguments but delays the evaluation. */
	default SupplierX<T, X> capture(T t) {
		return () -> this.apply(t);
	}

	public static <T, X extends Exception> UnaryOperatorX<T, X> constant(T r) {
		return (t) -> r;
	}

	public static final Supplier<String> NULL_VALUE_MESSAGE_SUPPLIER = () -> "Evaluated value by nonNull() method cannot be null (" + DESCRIPTION + ").";

	/** Ensures the result is not null */
	@Nonnull
	default T nonNull(T t) throws X {
		return Objects.requireNonNull(apply(t), NULL_VALUE_MESSAGE_SUPPLIER);
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	public static <T, X extends Exception> UnaryOperatorX<T, X> lX(final @Nonnull UnaryOperatorX<T, X> lambda) {
		Objects.requireNonNull(lambda, "Argument [lambda] cannot be null.");
		return lambda;
	}

	// <editor-fold desc="wrap">

	/** Wraps JRE instance. */
	@Nonnull
	public static <T, X extends Exception> UnaryOperatorX<T, X> wrapStd(final java.util.function.UnaryOperator<T> other) {
		return other::apply;
	}

	/** Wraps opposite (throwing/non-throwing) instance. */
	@Nonnull
	public static <T, X extends Exception> UnaryOperatorX<T, X> wrapX(final @Nonnull UnaryOperator<T> other) {
		return other::apply;
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two operators together in a order. */
	@Nonnull
	default <V> FunctionX<T, V, X> then(@Nonnull FunctionX<? super T, ? extends V, X> after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (T t) -> after.apply(this.apply(t));
	}

	/** Combines two operators together in a order. */
	@Nonnull
	default ToByteFunctionX<T, X> thenToByte(@Nonnull ToByteFunctionX<? super T, X> after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (T t) -> after.applyAsByte(this.apply(t));
	}

	/** Combines two operators together in a order. */
	@Nonnull
	default ToShortFunctionX<T, X> thenToShort(@Nonnull ToShortFunctionX<? super T, X> after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (T t) -> after.applyAsShort(this.apply(t));
	}

	/** Combines two operators together in a order. */
	@Nonnull
	default ToIntFunctionX<T, X> thenToInt(@Nonnull ToIntFunctionX<? super T, X> after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (T t) -> after.applyAsInt(this.apply(t));
	}

	/** Combines two operators together in a order. */
	@Nonnull
	default ToLongFunctionX<T, X> thenToLong(@Nonnull ToLongFunctionX<? super T, X> after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (T t) -> after.applyAsLong(this.apply(t));
	}

	/** Combines two operators together in a order. */
	@Nonnull
	default ToFloatFunctionX<T, X> thenToFloat(@Nonnull ToFloatFunctionX<? super T, X> after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (T t) -> after.applyAsFloat(this.apply(t));
	}

	/** Combines two operators together in a order. */
	@Nonnull
	default ToDoubleFunctionX<T, X> thenToDouble(@Nonnull ToDoubleFunctionX<? super T, X> after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (T t) -> after.applyAsDouble(this.apply(t));
	}

	/** Combines two operators together in a order. */
	@Nonnull
	default ToCharFunctionX<T, X> thenToChar(@Nonnull ToCharFunctionX<? super T, X> after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (T t) -> after.applyAsChar(this.apply(t));
	}

	/** Combines two operators together in a order. */
	@Nonnull
	default PredicateX<T, X> thenToBoolean(@Nonnull PredicateX<? super T, X> after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (T t) -> after.test(this.apply(t));
	}

	// </editor-fold>

	/** Returns a function that always returns its input argument. */
	@Nonnull
	public static <V, X extends Exception> UnaryOperatorX<V, X> identity() {
		return t -> t;
	}

	// <editor-fold desc="variant conversions">

	/** Converts to JRE variant. */
	@Nonnull
	default java.util.function.UnaryOperator<T> std() {
		return UnaryOperator.wrap(this)::apply;
	}

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default UnaryOperator<T> nonThrowing() {
		return UnaryOperator.wrap(this);
	}

	/** Converts to throwing variant (RuntimeException). */
	@Nonnull
	default UnaryOperatorX<T, RuntimeException> uncheck() {
		return (UnaryOperatorX) this;
	}

	/** Dirty way, checked exception will propagate as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default UnaryOperator<T> shove() {
		UnaryOperatorX<T, RuntimeException> exceptionCast = (UnaryOperatorX<T, RuntimeException>) this;
		return exceptionCast::apply;
	}

	// </editor-fold>

	@Nonnull
	default UnaryOperatorX<T, X> nonNullableX() {
		return (t) -> Objects.requireNonNull(this.apply(t));
	}

	// <editor-fold desc="exception handling">

	/** Wraps with additional exception handling. */
	@Nonnull
	public static <T, X extends Exception, E extends Exception, Y extends Exception> UnaryOperatorX<T, Y> wrapException(@Nonnull final UnaryOperatorX<T, X> other, Class<E> exception, SupplierX<T, X> supplier, ExceptionHandler<E, Y> handler) {
		return (T t) -> {
			try {
				return other.apply(t);
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
	default <E extends Exception, Y extends Exception> UnaryOperatorX<T, Y> handleX(Class<E> exception, ExceptionHandler<E, Y> handler) {
		Objects.requireNonNull(exception, Function4U.VALIDATION_MESSAGE_EXCEPTION);
		Objects.requireNonNull(handler, Function4U.VALIDATION_MESSAGE_HANDLER);

		return UnaryOperatorX.wrapException(this, exception, null, (ExceptionHandler) handler);
	}

	/** Wraps with exception handling that for any exception (including unchecked exception that might be different from X) will call handler function to determine the final exception. */
	@Nonnull
	default <Y extends Exception> UnaryOperatorX<T, Y> handleX(ExceptionHandler<Exception, Y> handler) {
		Objects.requireNonNull(handler, Function4U.VALIDATION_MESSAGE_HANDLER);

		return UnaryOperatorX.wrapException(this, Exception.class, null, (ExceptionHandler) handler);
	}

	/** Wraps with exception handling that for argument exception class will call supplier and return default value instead for propagating exception.  */
	@Nonnull
	default <E extends Exception, Y extends Exception> UnaryOperatorX<T, Y> handleX(Class<E> exception, SupplierX<T, X> supplier) {
		Objects.requireNonNull(exception, Function4U.VALIDATION_MESSAGE_EXCEPTION);
		Objects.requireNonNull(supplier, Function4U.VALIDATION_MESSAGE_HANDLER);

		return UnaryOperatorX.wrapException(this, exception, supplier, null);
	}

	/** Wraps with exception handling that for any exception will call supplier and return default value instead for propagating exception.  */
	@Nonnull
	default <Y extends Exception> UnaryOperatorX<T, Y> handleX(SupplierX<T, X> supplier) {
		Objects.requireNonNull(supplier, Function4U.VALIDATION_MESSAGE_HANDLER);

		return UnaryOperatorX.wrapException(this, Exception.class, supplier, null);
	}

	// </editor-fold>

}
