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

package eu.lunisolar.magma.func.operator.binary;
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
 * Throwing functional interface (lambda) LIntBinaryOperatorX for Java 8.
 *
 * Type: operator
 *
 * Domain (lvl: 2): int i1,int i2
 *
 * Co-domain: none
 *
 * @see LIntBinaryOperator
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LIntBinaryOperatorX<X extends Exception> extends java.util.function.IntBinaryOperator, MetaOperator, PrimitiveCodomain<Object>, MetaInterface.Throwing<X> { // NOSONAR

	public static final String DESCRIPTION = "LIntBinaryOperatorX: int doApplyAsInt(int i1,int i2) throws X";

	@Override
	@Deprecated
	// calling this method via LIntBinaryOperatorX interface should be discouraged.
	default int applyAsInt(int i1, int i2) {
		return this.nestingDoApplyAsInt(i1, i2);
	}

	public int doApplyAsInt(int i1, int i2) throws X;

	default int nestingDoApplyAsInt(int i1, int i2) {
		try {
			return this.doApplyAsInt(i1, i2);
		} catch (RuntimeException e) {
			throw e;
		} catch (Exception e) {
			throw new NestedException(e);
		}
	}

	default int shovingDoApplyAsInt(int i1, int i2) {
		return ((LIntBinaryOperatorX<RuntimeException>) this).doApplyAsInt(i1, i2);
	}

	/** Just to mirror the method: Ensures the result is not null */
	default int nonNullDoApplyAsInt(int i1, int i2) throws X {
		return doApplyAsInt(i1, i2);
	}

	/** Returns desxription of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LIntBinaryOperatorX.DESCRIPTION;
	}

	/** Captures arguments but delays the evaluation. */
	default LIntSupplierX<X> capture(int i1, int i2) {
		return () -> this.doApplyAsInt(i1, i2);
	}

	public static <X extends Exception> LIntBinaryOperatorX<X> constant(int r) {
		return (i1, i2) -> r;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	public static <X extends Exception> LIntBinaryOperatorX<X> lX(final @Nonnull LIntBinaryOperatorX<X> lambda) {
		Objects.requireNonNull(lambda, "Argument [lambda] cannot be null.");
		return lambda;
	}

	// <editor-fold desc="wrap">

	/** Wraps JRE instance. */
	@Nonnull
	public static <X extends Exception> LIntBinaryOperatorX<X> wrap(final java.util.function.IntBinaryOperator other) {
		return other::applyAsInt;
	}

	/** Wraps opposite (throwing/non-throwing) instance. */
	@Nonnull
	public static <X extends Exception> LIntBinaryOperatorX<X> wrapX(final @Nonnull LIntBinaryOperator other) {
		return (LIntBinaryOperatorX) other;
	}

	// </editor-fold>
	// <editor-fold desc="minmax/logical">

	/**
	 * @see {@link java.util.function.BinaryOperator#minBy()}
	 */
	@Nonnull
	public static <X extends Exception> LIntBinaryOperatorX<X> min() {
		return Integer::min;
	}

	/**
	 * @see {@link java.util.function.BinaryOperator#maxBy()}
	 */
	@Nonnull
	public static <X extends Exception> LIntBinaryOperatorX<X> max() {
		return Integer::max;
	}

	// </editor-fold>

	// <editor-fold desc="compose (functional)">

	/**
	 * Allows to manipulate the domain of the functyion.
	 */
	@Nonnull
	default LIntBinaryOperatorX<X> fromInt(@Nonnull final LIntUnaryOperatorX<X> before1, @Nonnull final LIntUnaryOperatorX<X> before2) {
		Objects.requireNonNull(before1, Function4U.VALIDATION_MESSAGE_BEFORE1);
		Objects.requireNonNull(before2, Function4U.VALIDATION_MESSAGE_BEFORE2);
		return (final int v1, final int v2) -> this.doApplyAsInt(before1.doApplyAsInt(v1), before2.doApplyAsInt(v2));
	}

	/**
	 * Allows to manipulate the domain of the functyion.
	 */
	@Nonnull
	default <V1, V2> LToIntBiFunctionX<V1, V2, X> from(@Nonnull final LToIntFunctionX<? super V1, X> before1, @Nonnull final LToIntFunctionX<? super V2, X> before2) {
		Objects.requireNonNull(before1, Function4U.VALIDATION_MESSAGE_BEFORE1);
		Objects.requireNonNull(before2, Function4U.VALIDATION_MESSAGE_BEFORE2);
		return (V1 v1, V2 v2) -> this.doApplyAsInt(before1.doApplyAsInt(v1), before2.doApplyAsInt(v2));
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two operators together in a order. */
	@Nonnull
	default <V> LIntBiFunctionX<V, X> then(@Nonnull LIntFunctionX<? extends V, X> after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (int i1, int i2) -> after.doApply(this.doApplyAsInt(i1, i2));
	}

	// </editor-fold>

	// <editor-fold desc="variant conversions">

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default LIntBinaryOperator nest() {
		return this::nestingDoApplyAsInt;
	}

	/** Converts to throwing variant (RuntimeException). */
	@Nonnull
	default LIntBinaryOperatorX<RuntimeException> nestX() {
		return this::nestingDoApplyAsInt;
	}

	/** Dirty way, checked exception will propagate as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LIntBinaryOperator shove() {
		return this::shovingDoApplyAsInt;
	}

	/** Dirty way, checked exception will propagate as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LIntBinaryOperatorX<RuntimeException> shoveX() {
		return this::shovingDoApplyAsInt;
	}

	// </editor-fold>

	// <editor-fold desc="exception handling">

	/** Wraps with additional exception handling. */
	@Nonnull
	public static <X extends Exception, E extends Exception, Y extends Exception> LIntBinaryOperatorX<Y> wrapException(@Nonnull final LIntBinaryOperatorX<X> other, Class<E> exception, LIntSupplierX<X> supplier, ExceptionHandler<E, Y> handler) {
		return (int i1, int i2) -> {
			try {
				return other.doApplyAsInt(i1, i2);
			} catch (Exception e) {
				try {
					if (supplier != null) {
						return supplier.doGetAsInt();
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
	default <E extends Exception, Y extends Exception> LIntBinaryOperatorX<Y> handleX(Class<E> exception, ExceptionHandler<E, Y> handler) {
		Objects.requireNonNull(exception, Function4U.VALIDATION_MESSAGE_EXCEPTION);
		Objects.requireNonNull(handler, Function4U.VALIDATION_MESSAGE_HANDLER);

		return LIntBinaryOperatorX.wrapException(this, exception, null, (ExceptionHandler) handler);
	}

	/** Wraps with exception handling that for any exception (including unchecked exception that might be different from X) will call handler function to determine the final exception. */
	@Nonnull
	default <Y extends Exception> LIntBinaryOperatorX<Y> handleX(ExceptionHandler<Exception, Y> handler) {
		Objects.requireNonNull(handler, Function4U.VALIDATION_MESSAGE_HANDLER);

		return LIntBinaryOperatorX.wrapException(this, Exception.class, null, (ExceptionHandler) handler);
	}

	/** Wraps with exception handling that for argument exception class will call supplier and return default value instead for propagating exception.  */
	@Nonnull
	default <E extends Exception, Y extends Exception> LIntBinaryOperatorX<Y> handleX(Class<E> exception, LIntSupplierX<X> supplier) {
		Objects.requireNonNull(exception, Function4U.VALIDATION_MESSAGE_EXCEPTION);
		Objects.requireNonNull(supplier, Function4U.VALIDATION_MESSAGE_HANDLER);

		return LIntBinaryOperatorX.wrapException(this, exception, supplier, null);
	}

	/** Wraps with exception handling that for any exception will call supplier and return default value instead for propagating exception.  */
	@Nonnull
	default <Y extends Exception> LIntBinaryOperatorX<Y> handleX(LIntSupplierX<X> supplier) {
		Objects.requireNonNull(supplier, Function4U.VALIDATION_MESSAGE_HANDLER);

		return LIntBinaryOperatorX.wrapException(this, Exception.class, supplier, null);
	}

	// </editor-fold>

}
