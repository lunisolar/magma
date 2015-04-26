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

package eu.lunisolar.magma.func.function.conversion;
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
 * Throwing functional interface (lambda) BooleanToFloatFunctionX for Java 8.
 *
 * Type: function
 *
 * Domain (lvl: 1): boolean b
 *
 * Co-domain: none
 *
 * @see BooleanToFloatFunction
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface BooleanToFloatFunctionX<X extends Exception> extends MetaFunction, PrimitiveCodomain<BooleanToFloatFunctionX<X>>, MetaThrowingInterface<X> { // NOSONAR

	public static final String DESCRIPTION = "BooleanToFloatFunctionX: float applyAsFloat(boolean b) throws X";

	public float applyAsFloat(boolean b) throws X;

	/** Returns desxription of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return BooleanToFloatFunctionX.DESCRIPTION;
	}

	/** Captures arguments but delays the evaluation. */
	default FloatSupplierX<X> capture(boolean b) {
		return () -> this.applyAsFloat(b);
	}

	/** Just to mirror the method: Ensures the result is not null */
	default float nonNull(boolean b) throws X {
		return applyAsFloat(b);
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	public static <X extends Exception> BooleanToFloatFunctionX<X> lX(final @Nonnull BooleanToFloatFunctionX<X> lambda) {
		Objects.requireNonNull(lambda, "Argument [lambda] cannot be null.");
		return lambda;
	}

	// <editor-fold desc="wrap">

	/** Wraps opposite (throwing/non-throwing) instance. */
	@Nonnull
	public static <X extends Exception> BooleanToFloatFunctionX<X> wrapX(final @Nonnull BooleanToFloatFunction other) {
		return other::applyAsFloat;
	}

	// </editor-fold>

	// <editor-fold desc="compose (functional)">

	/**
	 * Allows to manipulate the domain of the functyion.
	 */
	@Nonnull
	default BooleanToFloatFunctionX<X> fromBoolean(@Nonnull final BooleanUnaryOperatorX<X> before1) {
		Objects.requireNonNull(before1, Function4U.VALIDATION_MESSAGE_BEFORE1);
		return (final boolean v1) -> this.applyAsFloat(before1.applyAsBoolean(v1));
	}

	/**
	 * Allows to manipulate the domain of the functyion.
	 */
	@Nonnull
	default <V1> ToFloatFunctionX<V1, X> from(@Nonnull final PredicateX<? super V1, X> before1) {
		Objects.requireNonNull(before1, Function4U.VALIDATION_MESSAGE_BEFORE1);
		return (V1 v1) -> this.applyAsFloat(before1.applyAsBoolean(v1));
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two functions together in a order. */
	@Nonnull
	default <V> BooleanFunctionX<V, X> then(@Nonnull FloatFunctionX<? extends V, X> after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (boolean b) -> after.apply(this.applyAsFloat(b));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default BooleanToByteFunctionX<X> thenToByte(@Nonnull FloatToByteFunctionX<X> after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (boolean b) -> after.applyAsByte(this.applyAsFloat(b));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default BooleanToShortFunctionX<X> thenToShort(@Nonnull FloatToShortFunctionX<X> after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (boolean b) -> after.applyAsShort(this.applyAsFloat(b));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default BooleanToIntFunctionX<X> thenToInt(@Nonnull FloatToIntFunctionX<X> after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (boolean b) -> after.applyAsInt(this.applyAsFloat(b));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default BooleanToLongFunctionX<X> thenToLong(@Nonnull FloatToLongFunctionX<X> after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (boolean b) -> after.applyAsLong(this.applyAsFloat(b));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default BooleanToFloatFunctionX<X> thenToFloat(@Nonnull FloatUnaryOperatorX<X> after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (boolean b) -> after.applyAsFloat(this.applyAsFloat(b));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default BooleanToDoubleFunctionX<X> thenToDouble(@Nonnull FloatToDoubleFunctionX<X> after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (boolean b) -> after.applyAsDouble(this.applyAsFloat(b));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default BooleanToCharFunctionX<X> thenToChar(@Nonnull FloatToCharFunctionX<X> after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (boolean b) -> after.applyAsChar(this.applyAsFloat(b));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default BooleanUnaryOperatorX<X> thenToBoolean(@Nonnull FloatPredicateX<X> after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (boolean b) -> after.test(this.applyAsFloat(b));
	}

	// </editor-fold>

	// <editor-fold desc="variant conversions">

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default BooleanToFloatFunction nonThrowing() {
		return BooleanToFloatFunction.wrap(this);
	}

	/** Converts to throwing variant (RuntimeException). */
	@Nonnull
	default BooleanToFloatFunctionX<RuntimeException> uncheck() {
		return nonThrowing()::applyAsFloat;
	}

	/** Dirty way, checked exception will propagate as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default BooleanToFloatFunction shove() {
		BooleanToFloatFunctionX<RuntimeException> exceptionCast = (BooleanToFloatFunctionX<RuntimeException>) this;
		return exceptionCast::applyAsFloat;
	}

	// </editor-fold>

	// <editor-fold desc="exception handling">

	/** Wraps with additional exception handling. */
	@Nonnull
	public static <X extends Exception, E extends Exception, Y extends Exception> BooleanToFloatFunctionX<Y> wrapException(@Nonnull final BooleanToFloatFunctionX<X> other, Class<E> exception, FloatSupplierX<X> supplier, ExceptionHandler<E, Y> handler) {
		return (boolean b) -> {
			try {
				return other.applyAsFloat(b);
			} catch (Exception e) {
				try {
					if (supplier != null) {
						return supplier.getAsFloat();
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
	default <E extends Exception, Y extends Exception> BooleanToFloatFunctionX<Y> handle(Class<E> exception, ExceptionHandler<E, Y> handler) {
		Objects.requireNonNull(exception, Function4U.VALIDATION_MESSAGE_EXCEPTION);
		Objects.requireNonNull(handler, Function4U.VALIDATION_MESSAGE_HANDLER);

		return BooleanToFloatFunctionX.wrapException(this, exception, null, (ExceptionHandler) handler);
	}

	/** Wraps with exception handling that for any exception (including unchecked exception that might be different from X) will call handler function to determine the final exception. */
	@Nonnull
	default <Y extends Exception> BooleanToFloatFunctionX<Y> handle(ExceptionHandler<Exception, Y> handler) {
		Objects.requireNonNull(handler, Function4U.VALIDATION_MESSAGE_HANDLER);

		return BooleanToFloatFunctionX.wrapException(this, Exception.class, null, (ExceptionHandler) handler);
	}

	/** Wraps with exception handling that for argument exception class will call supplier and return default value instead for propagating exception.  */
	@Nonnull
	default <E extends Exception, Y extends Exception> BooleanToFloatFunctionX<Y> handle(Class<E> exception, FloatSupplierX<X> supplier) {
		Objects.requireNonNull(exception, Function4U.VALIDATION_MESSAGE_EXCEPTION);
		Objects.requireNonNull(supplier, Function4U.VALIDATION_MESSAGE_HANDLER);

		return BooleanToFloatFunctionX.wrapException(this, exception, supplier, null);
	}

	/** Wraps with exception handling that for any exception will call supplier and return default value instead for propagating exception.  */
	@Nonnull
	default <Y extends Exception> BooleanToFloatFunctionX<Y> handle(FloatSupplierX<X> supplier) {
		Objects.requireNonNull(supplier, Function4U.VALIDATION_MESSAGE_HANDLER);

		return BooleanToFloatFunctionX.wrapException(this, Exception.class, supplier, null);
	}

	// </editor-fold>

}
