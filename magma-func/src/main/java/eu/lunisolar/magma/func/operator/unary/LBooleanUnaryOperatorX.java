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
 * Throwing functional interface (lambda) LBooleanUnaryOperatorX for Java 8.
 *
 * Type: operator
 *
 * Domain (lvl: 1): boolean b
 *
 * Co-domain: none
 *
 * @see LBooleanUnaryOperator
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LBooleanUnaryOperatorX<X extends Exception> extends MetaLogicalOperator, PrimitiveCodomain<Object>, MetaInterface.Throwing<X> { // NOSONAR

	public static final String DESCRIPTION = "LBooleanUnaryOperatorX: boolean applyAsBoolean(boolean b) throws X";

	public boolean applyAsBoolean(boolean b) throws X;

	/** For convinience boolean operator is also special case of predicate. */
	default boolean test(boolean b) throws X {
		return applyAsBoolean(b);
	}

	/** Returns desxription of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LBooleanUnaryOperatorX.DESCRIPTION;
	}

	/** Captures arguments but delays the evaluation. */
	default LBooleanSupplierX<X> capture(boolean b) {
		return () -> this.applyAsBoolean(b);
	}

	public static <X extends Exception> LBooleanUnaryOperatorX<X> constant(boolean r) {
		return (b) -> r;
	}

	/** Just to mirror the method: Ensures the result is not null */
	default boolean nonNull(boolean b) throws X {
		return applyAsBoolean(b);
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	public static <X extends Exception> LBooleanUnaryOperatorX<X> lX(final @Nonnull LBooleanUnaryOperatorX<X> lambda) {
		Objects.requireNonNull(lambda, "Argument [lambda] cannot be null.");
		return lambda;
	}

	// <editor-fold desc="wrap">

	/** Wraps opposite (throwing/non-throwing) instance. */
	@Nonnull
	public static <X extends Exception> LBooleanUnaryOperatorX<X> wrapX(final @Nonnull LBooleanUnaryOperator other) {
		return other::applyAsBoolean;
	}

	// </editor-fold>
	// <editor-fold desc="predicate">

	/**
	 *  @see {@link java.util.function.Predicate#negate()}
	 */
	@Nonnull
	default LBooleanUnaryOperatorX<X> negate() {
		return (boolean b) -> !applyAsBoolean(b);
	}

	/**
	 *  @see {@link java.util.function.Predicate#and()}
	 */
	@Nonnull
	default LBooleanUnaryOperatorX<X> and(@Nonnull LBooleanUnaryOperatorX<X> other) {
		Objects.requireNonNull(other, Function4U.VALIDATION_MESSAGE_OTHER);
		return (boolean b) -> applyAsBoolean(b) && other.applyAsBoolean(b);
	}

	/**
	 *  @see {@link java.util.function.Predicate#or()}
	 */
	@Nonnull
	default LBooleanUnaryOperatorX<X> or(@Nonnull LBooleanUnaryOperatorX<X> other) {
		Objects.requireNonNull(other, Function4U.VALIDATION_MESSAGE_OTHER);
		return (boolean b) -> applyAsBoolean(b) || other.applyAsBoolean(b);
	}

	/**
	 *  @see {@link java.util.function.Predicate#or()}
	 */
	@Nonnull
	default LBooleanUnaryOperatorX<X> xor(@Nonnull LBooleanUnaryOperatorX<X> other) {
		Objects.requireNonNull(other, Function4U.VALIDATION_MESSAGE_OTHER);
		return (boolean b) -> applyAsBoolean(b) ^ other.applyAsBoolean(b);
	}

	@Nonnull
	public static <X extends Exception> LBooleanUnaryOperatorX<X> isEqual(boolean target) {
		return b -> b == target;
	}

	// </editor-fold>

	// <editor-fold desc="compose (functional)">

	/**
	 * Allows to manipulate the domain of the functyion.
	 */
	@Nonnull
	default LBooleanUnaryOperatorX<X> fromBoolean(@Nonnull final LBooleanUnaryOperatorX<X> before1) {
		Objects.requireNonNull(before1, Function4U.VALIDATION_MESSAGE_BEFORE1);
		return (final boolean v1) -> this.applyAsBoolean(before1.applyAsBoolean(v1));
	}

	/**
	 * Allows to manipulate the domain of the functyion.
	 */
	@Nonnull
	default <V1> LPredicateX<V1, X> from(@Nonnull final LPredicateX<? super V1, X> before1) {
		Objects.requireNonNull(before1, Function4U.VALIDATION_MESSAGE_BEFORE1);
		return (V1 v1) -> this.applyAsBoolean(before1.applyAsBoolean(v1));
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two operators together in a order. */
	@Nonnull
	default <V> LBooleanFunctionX<V, X> then(@Nonnull LBooleanFunctionX<? extends V, X> after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (boolean b) -> after.apply(this.applyAsBoolean(b));
	}

	/** Combines two operators together in a order. */
	@Nonnull
	default LBooleanToByteFunctionX<X> thenToByte(@Nonnull LBooleanToByteFunctionX<X> after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (boolean b) -> after.applyAsByte(this.applyAsBoolean(b));
	}

	/** Combines two operators together in a order. */
	@Nonnull
	default LBooleanToShortFunctionX<X> thenToShort(@Nonnull LBooleanToShortFunctionX<X> after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (boolean b) -> after.applyAsShort(this.applyAsBoolean(b));
	}

	/** Combines two operators together in a order. */
	@Nonnull
	default LBooleanToIntFunctionX<X> thenToInt(@Nonnull LBooleanToIntFunctionX<X> after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (boolean b) -> after.applyAsInt(this.applyAsBoolean(b));
	}

	/** Combines two operators together in a order. */
	@Nonnull
	default LBooleanToLongFunctionX<X> thenToLong(@Nonnull LBooleanToLongFunctionX<X> after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (boolean b) -> after.applyAsLong(this.applyAsBoolean(b));
	}

	/** Combines two operators together in a order. */
	@Nonnull
	default LBooleanToFloatFunctionX<X> thenToFloat(@Nonnull LBooleanToFloatFunctionX<X> after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (boolean b) -> after.applyAsFloat(this.applyAsBoolean(b));
	}

	/** Combines two operators together in a order. */
	@Nonnull
	default LBooleanToDoubleFunctionX<X> thenToDouble(@Nonnull LBooleanToDoubleFunctionX<X> after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (boolean b) -> after.applyAsDouble(this.applyAsBoolean(b));
	}

	/** Combines two operators together in a order. */
	@Nonnull
	default LBooleanToCharFunctionX<X> thenToChar(@Nonnull LBooleanToCharFunctionX<X> after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (boolean b) -> after.applyAsChar(this.applyAsBoolean(b));
	}

	/** Combines two operators together in a order. */
	@Nonnull
	default LBooleanUnaryOperatorX<X> thenToBoolean(@Nonnull LBooleanUnaryOperatorX<X> after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (boolean b) -> after.applyAsBoolean(this.applyAsBoolean(b));
	}

	// </editor-fold>

	/** Returns a function that always returns its input argument. */
	@Nonnull
	public static <X extends Exception> LBooleanUnaryOperatorX<X> identity() {
		return t -> t;
	}

	// <editor-fold desc="variant conversions">

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default LBooleanUnaryOperator nonThrowing() {
		return LBooleanUnaryOperator.wrap(this);
	}

	/** Converts to throwing variant (RuntimeException). */
	@Nonnull
	default LBooleanUnaryOperatorX<RuntimeException> uncheck() {
		return (LBooleanUnaryOperatorX) this;
	}

	/** Dirty way, checked exception will propagate as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LBooleanUnaryOperator shove() {
		LBooleanUnaryOperatorX<RuntimeException> exceptionCast = (LBooleanUnaryOperatorX<RuntimeException>) this;
		return exceptionCast::applyAsBoolean;
	}

	// </editor-fold>

	// <editor-fold desc="exception handling">

	/** Wraps with additional exception handling. */
	@Nonnull
	public static <X extends Exception, E extends Exception, Y extends Exception> LBooleanUnaryOperatorX<Y> wrapException(@Nonnull final LBooleanUnaryOperatorX<X> other, Class<E> exception, LBooleanSupplierX<X> supplier, ExceptionHandler<E, Y> handler) {
		return (boolean b) -> {
			try {
				return other.applyAsBoolean(b);
			} catch (Exception e) {
				try {
					if (supplier != null) {
						return supplier.getAsBoolean();
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
	default <E extends Exception, Y extends Exception> LBooleanUnaryOperatorX<Y> handleX(Class<E> exception, ExceptionHandler<E, Y> handler) {
		Objects.requireNonNull(exception, Function4U.VALIDATION_MESSAGE_EXCEPTION);
		Objects.requireNonNull(handler, Function4U.VALIDATION_MESSAGE_HANDLER);

		return LBooleanUnaryOperatorX.wrapException(this, exception, null, (ExceptionHandler) handler);
	}

	/** Wraps with exception handling that for any exception (including unchecked exception that might be different from X) will call handler function to determine the final exception. */
	@Nonnull
	default <Y extends Exception> LBooleanUnaryOperatorX<Y> handleX(ExceptionHandler<Exception, Y> handler) {
		Objects.requireNonNull(handler, Function4U.VALIDATION_MESSAGE_HANDLER);

		return LBooleanUnaryOperatorX.wrapException(this, Exception.class, null, (ExceptionHandler) handler);
	}

	/** Wraps with exception handling that for argument exception class will call supplier and return default value instead for propagating exception.  */
	@Nonnull
	default <E extends Exception, Y extends Exception> LBooleanUnaryOperatorX<Y> handleX(Class<E> exception, LBooleanSupplierX<X> supplier) {
		Objects.requireNonNull(exception, Function4U.VALIDATION_MESSAGE_EXCEPTION);
		Objects.requireNonNull(supplier, Function4U.VALIDATION_MESSAGE_HANDLER);

		return LBooleanUnaryOperatorX.wrapException(this, exception, supplier, null);
	}

	/** Wraps with exception handling that for any exception will call supplier and return default value instead for propagating exception.  */
	@Nonnull
	default <Y extends Exception> LBooleanUnaryOperatorX<Y> handleX(LBooleanSupplierX<X> supplier) {
		Objects.requireNonNull(supplier, Function4U.VALIDATION_MESSAGE_HANDLER);

		return LBooleanUnaryOperatorX.wrapException(this, Exception.class, supplier, null);
	}

	// </editor-fold>

}
