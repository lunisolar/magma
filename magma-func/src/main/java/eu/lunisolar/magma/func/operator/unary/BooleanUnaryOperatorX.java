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

package eu.lunisolar.magma.func.operator.unary;
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
 * Throwing functional interface (lambda) BooleanUnaryOperatorX for Java 8.
 *
 * Type: operator
 *
 * Domain (lvl: 1): boolean b
 *
 * Co-domain: none
 *
 * @see BooleanUnaryOperator
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface BooleanUnaryOperatorX<X extends Exception> extends MetaLogicalOperator, PrimitiveCodomain<BooleanUnaryOperatorX<X>>, MetaThrowingInterface<X> { // NOSONAR

	public static final String DESCRIPTION = "BooleanUnaryOperatorX: boolean applyAsBoolean(boolean b) throws X";

	public boolean applyAsBoolean(boolean b) throws X;

	/** For convinience boolean operator is also special case of predicate. */
	default boolean test(boolean b) throws X {
		return applyAsBoolean(b);
	}

	/** Returns desxription of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return BooleanUnaryOperatorX.DESCRIPTION;
	}

	/** Captures arguments but delays the evaluation. */
	default BooleanSupplierX<X> capture(boolean b) {
		return () -> this.applyAsBoolean(b);
	}

	/** Just to mirror the method: Ensures the result is not null */
	default boolean nonNull(boolean b) throws X {
		return applyAsBoolean(b);
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	public static <X extends Exception> BooleanUnaryOperatorX<X> lX(final @Nonnull BooleanUnaryOperatorX<X> lambda) {
		Objects.requireNonNull(lambda, "Argument [lambda] cannot be null.");
		return lambda;
	}

	// <editor-fold desc="wrap">

	/** Wraps opposite (throwing/non-throwing) instance. */
	@Nonnull
	public static <X extends Exception> BooleanUnaryOperatorX<X> wrapX(final @Nonnull BooleanUnaryOperator other) {
		return other::applyAsBoolean;
	}

	// </editor-fold>
	// <editor-fold desc="predicate">

	/**
	 *  @see {@link java.util.function.Predicate#negate()}
	 */
	@Nonnull
	default BooleanUnaryOperatorX<X> negate() {
		return (boolean b) -> !applyAsBoolean(b);
	}

	/**
	 *  @see {@link java.util.function.Predicate#and()}
	 */
	@Nonnull
	default BooleanUnaryOperatorX<X> and(@Nonnull BooleanUnaryOperatorX<X> other) {
		Objects.requireNonNull(other, Function4U.VALIDATION_MESSAGE_OTHER);
		return (boolean b) -> applyAsBoolean(b) && other.applyAsBoolean(b);
	}

	/**
	 *  @see {@link java.util.function.Predicate#or()}
	 */
	@Nonnull
	default BooleanUnaryOperatorX<X> or(@Nonnull BooleanUnaryOperatorX<X> other) {
		Objects.requireNonNull(other, Function4U.VALIDATION_MESSAGE_OTHER);
		return (boolean b) -> applyAsBoolean(b) || other.applyAsBoolean(b);
	}

	/**
	 *  @see {@link java.util.function.Predicate#or()}
	 */
	@Nonnull
	default BooleanUnaryOperatorX<X> xor(@Nonnull BooleanUnaryOperatorX<X> other) {
		Objects.requireNonNull(other, Function4U.VALIDATION_MESSAGE_OTHER);
		return (boolean b) -> applyAsBoolean(b) ^ other.applyAsBoolean(b);
	}

	@Nonnull
	public static <X extends Exception> BooleanUnaryOperatorX<X> isEqual(boolean target) {
		return b -> b == target;
	}

	// </editor-fold>

	// <editor-fold desc="compose (functional)">

	/**
	 * Allows to manipulate the domain of the functyion.
	 */
	@Nonnull
	default BooleanUnaryOperatorX<X> fromBoolean(@Nonnull final BooleanUnaryOperatorX<X> before1) {
		Objects.requireNonNull(before1, Function4U.VALIDATION_MESSAGE_BEFORE1);
		return (final boolean v1) -> this.applyAsBoolean(before1.applyAsBoolean(v1));
	}

	/**
	 * Allows to manipulate the domain of the functyion.
	 */
	@Nonnull
	default <V1> PredicateX<V1, X> from(@Nonnull final PredicateX<? super V1, X> before1) {
		Objects.requireNonNull(before1, Function4U.VALIDATION_MESSAGE_BEFORE1);
		return (V1 v1) -> this.applyAsBoolean(before1.applyAsBoolean(v1));
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two operators together in a order. */
	@Nonnull
	default <V> BooleanFunctionX<V, X> then(@Nonnull BooleanFunctionX<? extends V, X> after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (boolean b) -> after.apply(this.applyAsBoolean(b));
	}

	/** Combines two operators together in a order. */
	@Nonnull
	default BooleanToByteFunctionX<X> thenToByte(@Nonnull BooleanToByteFunctionX<X> after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (boolean b) -> after.applyAsByte(this.applyAsBoolean(b));
	}

	/** Combines two operators together in a order. */
	@Nonnull
	default BooleanToShortFunctionX<X> thenToShort(@Nonnull BooleanToShortFunctionX<X> after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (boolean b) -> after.applyAsShort(this.applyAsBoolean(b));
	}

	/** Combines two operators together in a order. */
	@Nonnull
	default BooleanToIntFunctionX<X> thenToInt(@Nonnull BooleanToIntFunctionX<X> after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (boolean b) -> after.applyAsInt(this.applyAsBoolean(b));
	}

	/** Combines two operators together in a order. */
	@Nonnull
	default BooleanToLongFunctionX<X> thenToLong(@Nonnull BooleanToLongFunctionX<X> after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (boolean b) -> after.applyAsLong(this.applyAsBoolean(b));
	}

	/** Combines two operators together in a order. */
	@Nonnull
	default BooleanToFloatFunctionX<X> thenToFloat(@Nonnull BooleanToFloatFunctionX<X> after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (boolean b) -> after.applyAsFloat(this.applyAsBoolean(b));
	}

	/** Combines two operators together in a order. */
	@Nonnull
	default BooleanToDoubleFunctionX<X> thenToDouble(@Nonnull BooleanToDoubleFunctionX<X> after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (boolean b) -> after.applyAsDouble(this.applyAsBoolean(b));
	}

	/** Combines two operators together in a order. */
	@Nonnull
	default BooleanToCharFunctionX<X> thenToChar(@Nonnull BooleanToCharFunctionX<X> after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (boolean b) -> after.applyAsChar(this.applyAsBoolean(b));
	}

	/** Combines two operators together in a order. */
	@Nonnull
	default BooleanUnaryOperatorX<X> thenToBoolean(@Nonnull BooleanUnaryOperatorX<X> after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (boolean b) -> after.applyAsBoolean(this.applyAsBoolean(b));
	}

	// </editor-fold>

	/** Returns a function that always returns its input argument. */
	@Nonnull
	public static <X extends Exception> BooleanUnaryOperatorX<X> identity() {
		return t -> t;
	}

	// <editor-fold desc="variant conversions">

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default BooleanUnaryOperator nonThrowing() {
		return BooleanUnaryOperator.wrap(this);
	}

	/** Converts to throwing variant (RuntimeException). */
	@Nonnull
	default BooleanUnaryOperatorX<RuntimeException> uncheck() {
		return nonThrowing()::applyAsBoolean;
	}

	/** Dirty way, checked exception will propagate as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default BooleanUnaryOperator shove() {
		BooleanUnaryOperatorX<RuntimeException> exceptionCast = (BooleanUnaryOperatorX<RuntimeException>) this;
		return exceptionCast::applyAsBoolean;
	}

	// </editor-fold>

	// <editor-fold desc="exception handling">

	/** Wraps with additional exception handling. */
	@Nonnull
	public static <X extends Exception, E extends Exception, Y extends Exception> BooleanUnaryOperatorX<Y> wrapException(@Nonnull final BooleanUnaryOperatorX<X> other, Class<E> exception, BooleanSupplierX<X> supplier, ExceptionHandler<E, Y> handler) {
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
	default <E extends Exception, Y extends Exception> BooleanUnaryOperatorX<Y> handle(Class<E> exception, ExceptionHandler<E, Y> handler) {
		Objects.requireNonNull(exception, Function4U.VALIDATION_MESSAGE_EXCEPTION);
		Objects.requireNonNull(handler, Function4U.VALIDATION_MESSAGE_HANDLER);

		return BooleanUnaryOperatorX.wrapException(this, exception, null, (ExceptionHandler) handler);
	}

	/** Wraps with exception handling that for any exception (including unchecked exception that might be different from X) will call handler function to determine the final exception. */
	@Nonnull
	default <Y extends Exception> BooleanUnaryOperatorX<Y> handle(ExceptionHandler<Exception, Y> handler) {
		Objects.requireNonNull(handler, Function4U.VALIDATION_MESSAGE_HANDLER);

		return BooleanUnaryOperatorX.wrapException(this, Exception.class, null, (ExceptionHandler) handler);
	}

	/** Wraps with exception handling that for argument exception class will call supplier and return default value instead for propagating exception.  */
	@Nonnull
	default <E extends Exception, Y extends Exception> BooleanUnaryOperatorX<Y> handle(Class<E> exception, BooleanSupplierX<X> supplier) {
		Objects.requireNonNull(exception, Function4U.VALIDATION_MESSAGE_EXCEPTION);
		Objects.requireNonNull(supplier, Function4U.VALIDATION_MESSAGE_HANDLER);

		return BooleanUnaryOperatorX.wrapException(this, exception, supplier, null);
	}

	/** Wraps with exception handling that for any exception will call supplier and return default value instead for propagating exception.  */
	@Nonnull
	default <Y extends Exception> BooleanUnaryOperatorX<Y> handle(BooleanSupplierX<X> supplier) {
		Objects.requireNonNull(supplier, Function4U.VALIDATION_MESSAGE_HANDLER);

		return BooleanUnaryOperatorX.wrapException(this, Exception.class, supplier, null);
	}

	// </editor-fold>

}
