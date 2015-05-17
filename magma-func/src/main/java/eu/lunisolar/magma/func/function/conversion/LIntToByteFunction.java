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

package eu.lunisolar.magma.func.function.conversion;
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
 * Non-throwing functional interface (lambda) LIntToByteFunction for Java 8.
 *
 * Type: function
 *
 * Domain (lvl: 1): int i
 *
 * Co-domain: none
 *
 * @see LIntToByteFunctionX
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LIntToByteFunction extends LIntToByteFunctionX<RuntimeException>, MetaFunction, PrimitiveCodomain<Object>, MetaInterface.NonThrowing { // NOSONAR

	public static final String DESCRIPTION = "LIntToByteFunction: byte applyAsByte(int i)";

	// Ovverriding methods can cause problems with inference.

	/** Returns desxription of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LIntToByteFunction.DESCRIPTION;
	}

	/** Captures arguments but delays the evaluation. */
	default LByteSupplier capture(int i) {
		return () -> this.applyAsByte(i);
	}

	public static LIntToByteFunction constant(byte r) {
		return (i) -> r;
	}

	/** Just to mirror the method: Ensures the result is not null */
	default byte nonNull(int i) {
		return applyAsByte(i);
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	public static LIntToByteFunction l(final @Nonnull LIntToByteFunction lambda) {
		Objects.requireNonNull(lambda, "Argument [lambda] cannot be null.");
		return lambda;
	}

	// <editor-fold desc="wrap">

	/** Wraps opposite (throwing/non-throwing) instance. */
	@Nonnull
	public static <X extends Exception> LIntToByteFunction wrap(final @Nonnull LIntToByteFunctionX<X> other) {
		return (int i) -> {
			try {
				return other.applyAsByte(i);
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
	default LIntToByteFunction fromInt(@Nonnull final LIntUnaryOperator before1) {
		Objects.requireNonNull(before1, Function4U.VALIDATION_MESSAGE_BEFORE1);
		return (final int v1) -> this.applyAsByte(before1.applyAsInt(v1));
	}

	/**
	 * Allows to manipulate the domain of the functyion.
	 */
	@Nonnull
	default <V1> LToByteFunction<V1> from(@Nonnull final LToIntFunction<? super V1> before1) {
		Objects.requireNonNull(before1, Function4U.VALIDATION_MESSAGE_BEFORE1);
		return (V1 v1) -> this.applyAsByte(before1.applyAsInt(v1));
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two functions together in a order. */
	@Nonnull
	default <V> LIntFunction<V> then(@Nonnull LByteFunction<? extends V> after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (int i) -> after.apply(this.applyAsByte(i));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LIntToByteFunction thenToByte(@Nonnull LByteUnaryOperator after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (int i) -> after.applyAsByte(this.applyAsByte(i));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LIntToShortFunction thenToShort(@Nonnull LByteToShortFunction after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (int i) -> after.applyAsShort(this.applyAsByte(i));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LIntUnaryOperator thenToInt(@Nonnull LByteToIntFunction after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (int i) -> after.applyAsInt(this.applyAsByte(i));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LIntToLongFunction thenToLong(@Nonnull LByteToLongFunction after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (int i) -> after.applyAsLong(this.applyAsByte(i));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LIntToFloatFunction thenToFloat(@Nonnull LByteToFloatFunction after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (int i) -> after.applyAsFloat(this.applyAsByte(i));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LIntToDoubleFunction thenToDouble(@Nonnull LByteToDoubleFunction after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (int i) -> after.applyAsDouble(this.applyAsByte(i));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LIntToCharFunction thenToChar(@Nonnull LByteToCharFunction after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (int i) -> after.applyAsChar(this.applyAsByte(i));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LIntPredicate thenToBoolean(@Nonnull LBytePredicate after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (int i) -> after.test(this.applyAsByte(i));
	}

	// </editor-fold>

	// <editor-fold desc="variant conversions">

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default LIntToByteFunction nonThrowing() {
		return this;
	}

	/** Converts to throwing variant (RuntimeException). */
	@Nonnull
	default LIntToByteFunctionX<RuntimeException> uncheck() {
		return (LIntToByteFunctionX) this;
	}

	/** Dirty way, checked exception will propagate as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LIntToByteFunction shove() {
		return this;
	}

	// </editor-fold>

	// <editor-fold desc="exception handling">

	/** Wraps with additional exception handling. */
	@Nonnull
	public static <X extends Exception, E extends Exception, Y extends RuntimeException> LIntToByteFunction wrapException(@Nonnull final LIntToByteFunction other, Class<E> exception, LByteSupplier supplier, ExceptionHandler<E, Y> handler) {
		return (int i) -> {
			try {
				return other.applyAsByte(i);
			} catch (Exception e) {
				try {
					if (supplier != null) {
						return supplier.getAsByte();
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
	default <E extends Exception, Y extends RuntimeException> LIntToByteFunction handle(Class<E> exception, ExceptionHandler<E, Y> handler) {
		Objects.requireNonNull(exception, Function4U.VALIDATION_MESSAGE_EXCEPTION);
		Objects.requireNonNull(handler, Function4U.VALIDATION_MESSAGE_HANDLER);

		return LIntToByteFunction.wrapException(this, exception, null, (ExceptionHandler) handler);
	}

	/** Wraps with exception handling that for any exception (including unchecked exception that might be different from X) will call handler function to determine the final exception. */
	@Nonnull
	default <Y extends RuntimeException> LIntToByteFunction handle(ExceptionHandler<Exception, Y> handler) {
		Objects.requireNonNull(handler, Function4U.VALIDATION_MESSAGE_HANDLER);

		return LIntToByteFunction.wrapException(this, Exception.class, null, (ExceptionHandler) handler);
	}

	/** Wraps with exception handling that for argument exception class will call supplier and return default value instead for propagating exception.  */
	@Nonnull
	default <E extends Exception, Y extends RuntimeException> LIntToByteFunction handle(Class<E> exception, LByteSupplier supplier) {
		Objects.requireNonNull(exception, Function4U.VALIDATION_MESSAGE_EXCEPTION);
		Objects.requireNonNull(supplier, Function4U.VALIDATION_MESSAGE_HANDLER);

		return LIntToByteFunction.wrapException(this, exception, supplier, null);
	}

	/** Wraps with exception handling that for any exception will call supplier and return default value instead for propagating exception.  */
	@Nonnull
	default <Y extends RuntimeException> LIntToByteFunction handle(LByteSupplier supplier) {
		Objects.requireNonNull(supplier, Function4U.VALIDATION_MESSAGE_HANDLER);

		return LIntToByteFunction.wrapException(this, Exception.class, supplier, null);
	}

	// </editor-fold>

}