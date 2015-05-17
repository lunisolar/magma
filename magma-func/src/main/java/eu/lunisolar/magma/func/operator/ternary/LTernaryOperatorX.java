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

package eu.lunisolar.magma.func.operator.ternary;
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
 * Throwing functional interface (lambda) LTernaryOperatorX for Java 8.
 *
 * Type: operator
 *
 * Domain (lvl: 3): T t1,T t2,T t3
 *
 * Co-domain: T
 *
 * @see LTernaryOperator
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LTernaryOperatorX<T, X extends Exception> extends MetaOperator, MetaInterface.Throwing<X> { // NOSONAR

	public static final String DESCRIPTION = "LTernaryOperatorX: T doApply(T t1,T t2,T t3) throws X";

	@Nullable
	public T doApply(T t1, T t2, T t3) throws X;

	default T nestingDoApply(T t1, T t2, T t3) {
		try {
			return this.doApply(t1, t2, t3);
		} catch (RuntimeException e) {
			throw e;
		} catch (Exception e) {
			throw new NestedException(e);
		}
	}

	default T shovingDoApply(T t1, T t2, T t3) {
		return ((LTernaryOperatorX<T, RuntimeException>) this).doApply(t1, t2, t3);
	}

	public static final LSupplier<String> NULL_VALUE_MESSAGE_SUPPLIER = () -> "Evaluated value by nonNullDoApply() method cannot be null (" + DESCRIPTION + ").";

	/** Ensures the result is not null */
	@Nonnull
	default T nonNullDoApply(T t1, T t2, T t3) throws X {
		return Objects.requireNonNull(doApply(t1, t2, t3), NULL_VALUE_MESSAGE_SUPPLIER);
	}

	/** Returns desxription of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LTernaryOperatorX.DESCRIPTION;
	}

	/** Captures arguments but delays the evaluation. */
	default LSupplierX<T, X> capture(T t1, T t2, T t3) {
		return () -> this.doApply(t1, t2, t3);
	}

	public static <T, X extends Exception> LTernaryOperatorX<T, X> constant(T r) {
		return (t1, t2, t3) -> r;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	public static <T, X extends Exception> LTernaryOperatorX<T, X> lX(final @Nonnull LTernaryOperatorX<T, X> lambda) {
		Objects.requireNonNull(lambda, "Argument [lambda] cannot be null.");
		return lambda;
	}

	// <editor-fold desc="wrap">

	/** Wraps opposite (throwing/non-throwing) instance. */
	@Nonnull
	public static <T, X extends Exception> LTernaryOperatorX<T, X> wrapX(final @Nonnull LTernaryOperator<T> other) {
		return (LTernaryOperatorX) other;
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two operators together in a order. */
	@Nonnull
	default <V> LTriFunctionX<T, T, T, V, X> then(@Nonnull LFunctionX<? super T, ? extends V, X> after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (T t1, T t2, T t3) -> after.doApply(this.doApply(t1, t2, t3));
	}

	// </editor-fold>

	// <editor-fold desc="variant conversions">

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default LTernaryOperator<T> nest() {
		return this::nestingDoApply;
	}

	/** Converts to throwing variant (RuntimeException). */
	@Nonnull
	default LTernaryOperatorX<T, RuntimeException> nestX() {
		return this::nestingDoApply;
	}

	/** Dirty way, checked exception will propagate as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LTernaryOperator<T> shove() {
		return this::shovingDoApply;
	}

	/** Dirty way, checked exception will propagate as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LTernaryOperatorX<T, RuntimeException> shoveX() {
		return this::shovingDoApply;
	}

	// </editor-fold>

	@Nonnull
	default LTernaryOperatorX<T, X> nonNullableX() {
		return this::nonNullDoApply;
	}

	// <editor-fold desc="exception handling">

	/** Wraps with additional exception handling. */
	@Nonnull
	public static <T, X extends Exception, E extends Exception, Y extends Exception> LTernaryOperatorX<T, Y> wrapException(@Nonnull final LTernaryOperatorX<T, X> other, Class<E> exception, LSupplierX<T, X> supplier, ExceptionHandler<E, Y> handler) {
		return (T t1, T t2, T t3) -> {
			try {
				return other.doApply(t1, t2, t3);
			} catch (Exception e) {
				try {
					if (supplier != null) {
						return supplier.doGet();
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
	default <E extends Exception, Y extends Exception> LTernaryOperatorX<T, Y> handleX(Class<E> exception, ExceptionHandler<E, Y> handler) {
		Objects.requireNonNull(exception, Function4U.VALIDATION_MESSAGE_EXCEPTION);
		Objects.requireNonNull(handler, Function4U.VALIDATION_MESSAGE_HANDLER);

		return LTernaryOperatorX.wrapException(this, exception, null, (ExceptionHandler) handler);
	}

	/** Wraps with exception handling that for any exception (including unchecked exception that might be different from X) will call handler function to determine the final exception. */
	@Nonnull
	default <Y extends Exception> LTernaryOperatorX<T, Y> handleX(ExceptionHandler<Exception, Y> handler) {
		Objects.requireNonNull(handler, Function4U.VALIDATION_MESSAGE_HANDLER);

		return LTernaryOperatorX.wrapException(this, Exception.class, null, (ExceptionHandler) handler);
	}

	/** Wraps with exception handling that for argument exception class will call supplier and return default value instead for propagating exception.  */
	@Nonnull
	default <E extends Exception, Y extends Exception> LTernaryOperatorX<T, Y> handleX(Class<E> exception, LSupplierX<T, X> supplier) {
		Objects.requireNonNull(exception, Function4U.VALIDATION_MESSAGE_EXCEPTION);
		Objects.requireNonNull(supplier, Function4U.VALIDATION_MESSAGE_HANDLER);

		return LTernaryOperatorX.wrapException(this, exception, supplier, null);
	}

	/** Wraps with exception handling that for any exception will call supplier and return default value instead for propagating exception.  */
	@Nonnull
	default <Y extends Exception> LTernaryOperatorX<T, Y> handleX(LSupplierX<T, X> supplier) {
		Objects.requireNonNull(supplier, Function4U.VALIDATION_MESSAGE_HANDLER);

		return LTernaryOperatorX.wrapException(this, Exception.class, supplier, null);
	}

	// </editor-fold>

}
