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
 * Throwing functional interface (lambda) LDoubleBinaryOperatorX for Java 8.
 *
 * Type: operator
 *
 * Domain (lvl: 2): double d1,double d2
 *
 * Co-domain: none
 *
 * @see LDoubleBinaryOperator
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LDoubleBinaryOperatorX<X extends Exception> extends java.util.function.DoubleBinaryOperator, MetaOperator, PrimitiveCodomain<Object>, MetaInterface.Throwing<X> { // NOSONAR

	public static final String DESCRIPTION = "LDoubleBinaryOperatorX: double doApplyAsDouble(double d1,double d2) throws X";

	@Override
	@Deprecated
	// calling this method via LDoubleBinaryOperatorX interface should be discouraged.
	default double applyAsDouble(double d1, double d2) {
		return this.nestingDoApplyAsDouble(d1, d2);
	}

	public double doApplyAsDouble(double d1, double d2) throws X;

	default double nestingDoApplyAsDouble(double d1, double d2) {
		try {
			return this.doApplyAsDouble(d1, d2);
		} catch (RuntimeException e) {
			throw e;
		} catch (Exception e) {
			throw new NestedException(e);
		}
	}

	default double shovingDoApplyAsDouble(double d1, double d2) {
		return ((LDoubleBinaryOperatorX<RuntimeException>) this).doApplyAsDouble(d1, d2);
	}

	/** Just to mirror the method: Ensures the result is not null */
	default double nonNullDoApplyAsDouble(double d1, double d2) throws X {
		return doApplyAsDouble(d1, d2);
	}

	/** Returns desxription of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LDoubleBinaryOperatorX.DESCRIPTION;
	}

	/** Captures arguments but delays the evaluation. */
	default LDoubleSupplierX<X> capture(double d1, double d2) {
		return () -> this.doApplyAsDouble(d1, d2);
	}

	public static <X extends Exception> LDoubleBinaryOperatorX<X> constant(double r) {
		return (d1, d2) -> r;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	public static <X extends Exception> LDoubleBinaryOperatorX<X> lX(final @Nonnull LDoubleBinaryOperatorX<X> lambda) {
		Objects.requireNonNull(lambda, "Argument [lambda] cannot be null.");
		return lambda;
	}

	// <editor-fold desc="wrap">

	/** Wraps JRE instance. */
	@Nonnull
	public static <X extends Exception> LDoubleBinaryOperatorX<X> wrap(final java.util.function.DoubleBinaryOperator other) {
		return other::applyAsDouble;
	}

	/** Wraps opposite (throwing/non-throwing) instance. */
	@Nonnull
	public static <X extends Exception> LDoubleBinaryOperatorX<X> wrapX(final @Nonnull LDoubleBinaryOperator other) {
		return (LDoubleBinaryOperatorX) other;
	}

	// </editor-fold>
	// <editor-fold desc="minmax/logical">

	/**
	 * @see {@link java.util.function.BinaryOperator#minBy()}
	 */
	@Nonnull
	public static <X extends Exception> LDoubleBinaryOperatorX<X> min() {
		return Double::min;
	}

	/**
	 * @see {@link java.util.function.BinaryOperator#maxBy()}
	 */
	@Nonnull
	public static <X extends Exception> LDoubleBinaryOperatorX<X> max() {
		return Double::max;
	}

	// </editor-fold>

	// <editor-fold desc="compose (functional)">

	/**
	 * Allows to manipulate the domain of the functyion.
	 */
	@Nonnull
	default LDoubleBinaryOperatorX<X> fromDouble(@Nonnull final LDoubleUnaryOperatorX<X> before1, @Nonnull final LDoubleUnaryOperatorX<X> before2) {
		Objects.requireNonNull(before1, Function4U.VALIDATION_MESSAGE_BEFORE1);
		Objects.requireNonNull(before2, Function4U.VALIDATION_MESSAGE_BEFORE2);
		return (final double v1, final double v2) -> this.doApplyAsDouble(before1.doApplyAsDouble(v1), before2.doApplyAsDouble(v2));
	}

	/**
	 * Allows to manipulate the domain of the functyion.
	 */
	@Nonnull
	default <V1, V2> LToDoubleBiFunctionX<V1, V2, X> from(@Nonnull final LToDoubleFunctionX<? super V1, X> before1, @Nonnull final LToDoubleFunctionX<? super V2, X> before2) {
		Objects.requireNonNull(before1, Function4U.VALIDATION_MESSAGE_BEFORE1);
		Objects.requireNonNull(before2, Function4U.VALIDATION_MESSAGE_BEFORE2);
		return (V1 v1, V2 v2) -> this.doApplyAsDouble(before1.doApplyAsDouble(v1), before2.doApplyAsDouble(v2));
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two operators together in a order. */
	@Nonnull
	default <V> LDoubleBiFunctionX<V, X> then(@Nonnull LDoubleFunctionX<? extends V, X> after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (double d1, double d2) -> after.doApply(this.doApplyAsDouble(d1, d2));
	}

	// </editor-fold>

	// <editor-fold desc="variant conversions">

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default LDoubleBinaryOperator nest() {
		return this::nestingDoApplyAsDouble;
	}

	/** Converts to throwing variant (RuntimeException). */
	@Nonnull
	default LDoubleBinaryOperatorX<RuntimeException> nestX() {
		return this::nestingDoApplyAsDouble;
	}

	/** Dirty way, checked exception will propagate as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LDoubleBinaryOperator shove() {
		return this::shovingDoApplyAsDouble;
	}

	/** Dirty way, checked exception will propagate as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LDoubleBinaryOperatorX<RuntimeException> shoveX() {
		return this::shovingDoApplyAsDouble;
	}

	// </editor-fold>

	// <editor-fold desc="exception handling">

	/** Wraps with additional exception handling. */
	@Nonnull
	public static <X extends Exception, E extends Exception, Y extends Exception> LDoubleBinaryOperatorX<Y> wrapException(@Nonnull final LDoubleBinaryOperatorX<X> other, Class<E> exception, LDoubleSupplierX<X> supplier, ExceptionHandler<E, Y> handler) {
		return (double d1, double d2) -> {
			try {
				return other.doApplyAsDouble(d1, d2);
			} catch (Exception e) {
				try {
					if (supplier != null) {
						return supplier.doGetAsDouble();
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
	default <E extends Exception, Y extends Exception> LDoubleBinaryOperatorX<Y> handleX(Class<E> exception, ExceptionHandler<E, Y> handler) {
		Objects.requireNonNull(exception, Function4U.VALIDATION_MESSAGE_EXCEPTION);
		Objects.requireNonNull(handler, Function4U.VALIDATION_MESSAGE_HANDLER);

		return LDoubleBinaryOperatorX.wrapException(this, exception, null, (ExceptionHandler) handler);
	}

	/** Wraps with exception handling that for any exception (including unchecked exception that might be different from X) will call handler function to determine the final exception. */
	@Nonnull
	default <Y extends Exception> LDoubleBinaryOperatorX<Y> handleX(ExceptionHandler<Exception, Y> handler) {
		Objects.requireNonNull(handler, Function4U.VALIDATION_MESSAGE_HANDLER);

		return LDoubleBinaryOperatorX.wrapException(this, Exception.class, null, (ExceptionHandler) handler);
	}

	/** Wraps with exception handling that for argument exception class will call supplier and return default value instead for propagating exception.  */
	@Nonnull
	default <E extends Exception, Y extends Exception> LDoubleBinaryOperatorX<Y> handleX(Class<E> exception, LDoubleSupplierX<X> supplier) {
		Objects.requireNonNull(exception, Function4U.VALIDATION_MESSAGE_EXCEPTION);
		Objects.requireNonNull(supplier, Function4U.VALIDATION_MESSAGE_HANDLER);

		return LDoubleBinaryOperatorX.wrapException(this, exception, supplier, null);
	}

	/** Wraps with exception handling that for any exception will call supplier and return default value instead for propagating exception.  */
	@Nonnull
	default <Y extends Exception> LDoubleBinaryOperatorX<Y> handleX(LDoubleSupplierX<X> supplier) {
		Objects.requireNonNull(supplier, Function4U.VALIDATION_MESSAGE_HANDLER);

		return LDoubleBinaryOperatorX.wrapException(this, Exception.class, supplier, null);
	}

	// </editor-fold>

}
