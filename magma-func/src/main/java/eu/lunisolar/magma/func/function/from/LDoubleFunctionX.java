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
 * Throwing functional interface (lambda) LDoubleFunctionX for Java 8.
 *
 * Type: function
 *
 * Domain (lvl: 1): double d
 *
 * Co-domain: R
 *
 * @see LDoubleFunction
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LDoubleFunctionX<R, X extends Exception> extends MetaFunction, MetaInterface.Throwing<X> { // NOSONAR

	public static final String DESCRIPTION = "LDoubleFunctionX: R apply(double d) throws X";

	@Nullable
	public R apply(double d) throws X;

	/** Returns desxription of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LDoubleFunctionX.DESCRIPTION;
	}

	/** Captures arguments but delays the evaluation. */
	default LSupplierX<R, X> capture(double d) {
		return () -> this.apply(d);
	}

	public static <R, X extends Exception> LDoubleFunctionX<R, X> constant(R r) {
		return (d) -> r;
	}

	public static final LSupplier<String> NULL_VALUE_MESSAGE_SUPPLIER = () -> "Evaluated value by nonNull() method cannot be null (" + DESCRIPTION + ").";

	/** Ensures the result is not null */
	@Nonnull
	default R nonNull(double d) throws X {
		return Objects.requireNonNull(apply(d), NULL_VALUE_MESSAGE_SUPPLIER);
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	public static <R, X extends Exception> LDoubleFunctionX<R, X> lX(final @Nonnull LDoubleFunctionX<R, X> lambda) {
		Objects.requireNonNull(lambda, "Argument [lambda] cannot be null.");
		return lambda;
	}

	// <editor-fold desc="wrap">

	/** Wraps JRE instance. */
	@Nonnull
	public static <R, X extends Exception> LDoubleFunctionX<R, X> wrapStd(final java.util.function.DoubleFunction<R> other) {
		return other::apply;
	}

	/** Wraps opposite (throwing/non-throwing) instance. */
	@Nonnull
	public static <R, X extends Exception> LDoubleFunctionX<R, X> wrapX(final @Nonnull LDoubleFunction<R> other) {
		return other::apply;
	}

	// </editor-fold>

	// <editor-fold desc="compose (functional)">

	/**
	 * Allows to manipulate the domain of the functyion.
	 */
	@Nonnull
	default LDoubleFunctionX<R, X> fromDouble(@Nonnull final LDoubleUnaryOperatorX<X> before1) {
		Objects.requireNonNull(before1, Function4U.VALIDATION_MESSAGE_BEFORE1);
		return (final double v1) -> this.apply(before1.applyAsDouble(v1));
	}

	/**
	 * Allows to manipulate the domain of the functyion.
	 */
	@Nonnull
	default <V1> LFunctionX<V1, R, X> from(@Nonnull final LToDoubleFunctionX<? super V1, X> before1) {
		Objects.requireNonNull(before1, Function4U.VALIDATION_MESSAGE_BEFORE1);
		return (V1 v1) -> this.apply(before1.applyAsDouble(v1));
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two functions together in a order. */
	@Nonnull
	default <V> LDoubleFunctionX<V, X> then(@Nonnull LFunctionX<? super R, ? extends V, X> after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (double d) -> after.apply(this.apply(d));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LDoubleConsumerX<X> then(@Nonnull LConsumerX<? super R, X> after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (double d) -> after.accept(this.apply(d));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LDoubleToByteFunctionX<X> thenToByte(@Nonnull LToByteFunctionX<? super R, X> after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (double d) -> after.applyAsByte(this.apply(d));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LDoubleToShortFunctionX<X> thenToShort(@Nonnull LToShortFunctionX<? super R, X> after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (double d) -> after.applyAsShort(this.apply(d));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LDoubleToIntFunctionX<X> thenToInt(@Nonnull LToIntFunctionX<? super R, X> after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (double d) -> after.applyAsInt(this.apply(d));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LDoubleToLongFunctionX<X> thenToLong(@Nonnull LToLongFunctionX<? super R, X> after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (double d) -> after.applyAsLong(this.apply(d));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LDoubleToFloatFunctionX<X> thenToFloat(@Nonnull LToFloatFunctionX<? super R, X> after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (double d) -> after.applyAsFloat(this.apply(d));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LDoubleUnaryOperatorX<X> thenToDouble(@Nonnull LToDoubleFunctionX<? super R, X> after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (double d) -> after.applyAsDouble(this.apply(d));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LDoubleToCharFunctionX<X> thenToChar(@Nonnull LToCharFunctionX<? super R, X> after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (double d) -> after.applyAsChar(this.apply(d));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LDoublePredicateX<X> thenToBoolean(@Nonnull LPredicateX<? super R, X> after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (double d) -> after.test(this.apply(d));
	}

	// </editor-fold>

	// <editor-fold desc="variant conversions">

	/** Converts to JRE variant. */
	@Nonnull
	default java.util.function.DoubleFunction<R> std() {
		return LDoubleFunction.wrap(this)::apply;
	}

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default LDoubleFunction<R> nonThrowing() {
		return LDoubleFunction.wrap(this);
	}

	/** Converts to throwing variant (RuntimeException). */
	@Nonnull
	default LDoubleFunctionX<R, RuntimeException> uncheck() {
		return (LDoubleFunctionX) this;
	}

	/** Dirty way, checked exception will propagate as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LDoubleFunction<R> shove() {
		LDoubleFunctionX<R, RuntimeException> exceptionCast = (LDoubleFunctionX<R, RuntimeException>) this;
		return exceptionCast::apply;
	}

	// </editor-fold>

	@Nonnull
	default LDoubleFunctionX<R, X> nonNullableX() {
		return (d) -> Objects.requireNonNull(this.apply(d));
	}

	// <editor-fold desc="exception handling">

	/** Wraps with additional exception handling. */
	@Nonnull
	public static <R, X extends Exception, E extends Exception, Y extends Exception> LDoubleFunctionX<R, Y> wrapException(@Nonnull final LDoubleFunctionX<R, X> other, Class<E> exception, LSupplierX<R, X> supplier, ExceptionHandler<E, Y> handler) {
		return (double d) -> {
			try {
				return other.apply(d);
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
	default <E extends Exception, Y extends Exception> LDoubleFunctionX<R, Y> handleX(Class<E> exception, ExceptionHandler<E, Y> handler) {
		Objects.requireNonNull(exception, Function4U.VALIDATION_MESSAGE_EXCEPTION);
		Objects.requireNonNull(handler, Function4U.VALIDATION_MESSAGE_HANDLER);

		return LDoubleFunctionX.wrapException(this, exception, null, (ExceptionHandler) handler);
	}

	/** Wraps with exception handling that for any exception (including unchecked exception that might be different from X) will call handler function to determine the final exception. */
	@Nonnull
	default <Y extends Exception> LDoubleFunctionX<R, Y> handleX(ExceptionHandler<Exception, Y> handler) {
		Objects.requireNonNull(handler, Function4U.VALIDATION_MESSAGE_HANDLER);

		return LDoubleFunctionX.wrapException(this, Exception.class, null, (ExceptionHandler) handler);
	}

	/** Wraps with exception handling that for argument exception class will call supplier and return default value instead for propagating exception.  */
	@Nonnull
	default <E extends Exception, Y extends Exception> LDoubleFunctionX<R, Y> handleX(Class<E> exception, LSupplierX<R, X> supplier) {
		Objects.requireNonNull(exception, Function4U.VALIDATION_MESSAGE_EXCEPTION);
		Objects.requireNonNull(supplier, Function4U.VALIDATION_MESSAGE_HANDLER);

		return LDoubleFunctionX.wrapException(this, exception, supplier, null);
	}

	/** Wraps with exception handling that for any exception will call supplier and return default value instead for propagating exception.  */
	@Nonnull
	default <Y extends Exception> LDoubleFunctionX<R, Y> handleX(LSupplierX<R, X> supplier) {
		Objects.requireNonNull(supplier, Function4U.VALIDATION_MESSAGE_HANDLER);

		return LDoubleFunctionX.wrapException(this, Exception.class, supplier, null);
	}

	// </editor-fold>

}