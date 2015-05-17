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

package eu.lunisolar.magma.func.function.from;
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
 * Throwing functional interface (lambda) LObjLongFunctionX for Java 8.
 *
 * Type: function
 *
 * Domain (lvl: 2): T t, long l
 *
 * Co-domain: R
 *
 * @see LObjLongFunction
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LObjLongFunctionX<T, R, X extends Exception> extends MetaFunction, MetaInterface.Throwing<X> { // NOSONAR

	public static final String DESCRIPTION = "LObjLongFunctionX: R apply(T t, long l) throws X";

	@Nullable
	public R apply(T t, long l) throws X;

	/** Returns desxription of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LObjLongFunctionX.DESCRIPTION;
	}

	/** Captures arguments but delays the evaluation. */
	default LSupplierX<R, X> capture(T t, long l) {
		return () -> this.apply(t, l);
	}

	public static <T, R, X extends Exception> LObjLongFunctionX<T, R, X> constant(R r) {
		return (t, l) -> r;
	}

	public static final LSupplier<String> NULL_VALUE_MESSAGE_SUPPLIER = () -> "Evaluated value by nonNull() method cannot be null (" + DESCRIPTION + ").";

	/** Ensures the result is not null */
	@Nonnull
	default R nonNull(T t, long l) throws X {
		return Objects.requireNonNull(apply(t, l), NULL_VALUE_MESSAGE_SUPPLIER);
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	public static <T, R, X extends Exception> LObjLongFunctionX<T, R, X> lX(final @Nonnull LObjLongFunctionX<T, R, X> lambda) {
		Objects.requireNonNull(lambda, "Argument [lambda] cannot be null.");
		return lambda;
	}

	// <editor-fold desc="wrap">

	/** Wraps opposite (throwing/non-throwing) instance. */
	@Nonnull
	public static <T, R, X extends Exception> LObjLongFunctionX<T, R, X> wrapX(final @Nonnull LObjLongFunction<T, R> other) {
		return other::apply;
	}

	// </editor-fold>

	// <editor-fold desc="compose (functional)">

	/**
	 * Allows to manipulate the domain of the functyion.
	 */
	@Nonnull
	default <V1> LObjLongFunctionX<V1, R, X> fromLong(@Nonnull final LFunctionX<? super V1, ? extends T, X> before1, @Nonnull final LLongUnaryOperatorX<X> before2) {
		Objects.requireNonNull(before1, Function4U.VALIDATION_MESSAGE_BEFORE1);
		Objects.requireNonNull(before2, Function4U.VALIDATION_MESSAGE_BEFORE2);
		return (final V1 v1, final long v2) -> this.apply(before1.apply(v1), before2.applyAsLong(v2));
	}

	/**
	 * Allows to manipulate the domain of the functyion.
	 */
	@Nonnull
	default <V1, V2> LBiFunctionX<V1, V2, R, X> from(@Nonnull final LFunctionX<? super V1, ? extends T, X> before1, @Nonnull final LToLongFunctionX<? super V2, X> before2) {
		Objects.requireNonNull(before1, Function4U.VALIDATION_MESSAGE_BEFORE1);
		Objects.requireNonNull(before2, Function4U.VALIDATION_MESSAGE_BEFORE2);
		return (V1 v1, V2 v2) -> this.apply(before1.apply(v1), before2.applyAsLong(v2));
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two functions together in a order. */
	@Nonnull
	default <V> LObjLongFunctionX<T, V, X> then(@Nonnull LFunctionX<? super R, ? extends V, X> after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (T t, long l) -> after.apply(this.apply(t, l));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LObjLongConsumerX<T, X> then(@Nonnull LConsumerX<? super R, X> after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (T t, long l) -> after.accept(this.apply(t, l));
	}

	// </editor-fold>

	// <editor-fold desc="variant conversions">

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default LObjLongFunction<T, R> nonThrowing() {
		return LObjLongFunction.wrap(this);
	}

	/** Converts to throwing variant (RuntimeException). */
	@Nonnull
	default LObjLongFunctionX<T, R, RuntimeException> uncheck() {
		return (LObjLongFunctionX) this;
	}

	/** Dirty way, checked exception will propagate as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LObjLongFunction<T, R> shove() {
		LObjLongFunctionX<T, R, RuntimeException> exceptionCast = (LObjLongFunctionX<T, R, RuntimeException>) this;
		return exceptionCast::apply;
	}

	// </editor-fold>

	@Nonnull
	default LObjLongFunctionX<T, R, X> nonNullableX() {
		return (t, l) -> Objects.requireNonNull(this.apply(t, l));
	}

	// <editor-fold desc="exception handling">

	/** Wraps with additional exception handling. */
	@Nonnull
	public static <T, R, X extends Exception, E extends Exception, Y extends Exception> LObjLongFunctionX<T, R, Y> wrapException(@Nonnull final LObjLongFunctionX<T, R, X> other, Class<E> exception, LSupplierX<R, X> supplier, ExceptionHandler<E, Y> handler) {
		return (T t, long l) -> {
			try {
				return other.apply(t, l);
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
	default <E extends Exception, Y extends Exception> LObjLongFunctionX<T, R, Y> handleX(Class<E> exception, ExceptionHandler<E, Y> handler) {
		Objects.requireNonNull(exception, Function4U.VALIDATION_MESSAGE_EXCEPTION);
		Objects.requireNonNull(handler, Function4U.VALIDATION_MESSAGE_HANDLER);

		return LObjLongFunctionX.wrapException(this, exception, null, (ExceptionHandler) handler);
	}

	/** Wraps with exception handling that for any exception (including unchecked exception that might be different from X) will call handler function to determine the final exception. */
	@Nonnull
	default <Y extends Exception> LObjLongFunctionX<T, R, Y> handleX(ExceptionHandler<Exception, Y> handler) {
		Objects.requireNonNull(handler, Function4U.VALIDATION_MESSAGE_HANDLER);

		return LObjLongFunctionX.wrapException(this, Exception.class, null, (ExceptionHandler) handler);
	}

	/** Wraps with exception handling that for argument exception class will call supplier and return default value instead for propagating exception.  */
	@Nonnull
	default <E extends Exception, Y extends Exception> LObjLongFunctionX<T, R, Y> handleX(Class<E> exception, LSupplierX<R, X> supplier) {
		Objects.requireNonNull(exception, Function4U.VALIDATION_MESSAGE_EXCEPTION);
		Objects.requireNonNull(supplier, Function4U.VALIDATION_MESSAGE_HANDLER);

		return LObjLongFunctionX.wrapException(this, exception, supplier, null);
	}

	/** Wraps with exception handling that for any exception will call supplier and return default value instead for propagating exception.  */
	@Nonnull
	default <Y extends Exception> LObjLongFunctionX<T, R, Y> handleX(LSupplierX<R, X> supplier) {
		Objects.requireNonNull(supplier, Function4U.VALIDATION_MESSAGE_HANDLER);

		return LObjLongFunctionX.wrapException(this, Exception.class, supplier, null);
	}

	// </editor-fold>

}