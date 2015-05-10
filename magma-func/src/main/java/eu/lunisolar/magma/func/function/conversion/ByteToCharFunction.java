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
 * Non-throwing functional interface (lambda) ByteToCharFunction for Java 8.
 *
 * Type: function
 *
 * Domain (lvl: 1): byte b
 *
 * Co-domain: none
 *
 * @see ByteToCharFunctionX
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface ByteToCharFunction extends ByteToCharFunctionX<RuntimeException>, MetaFunction, PrimitiveCodomain<Object>, MetaInterface.NonThrowing { // NOSONAR

	public static final String DESCRIPTION = "ByteToCharFunction: char applyAsChar(byte b)";

	// Ovverriding methods can cause problems with inference.

	/** Returns desxription of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return ByteToCharFunction.DESCRIPTION;
	}

	/** Captures arguments but delays the evaluation. */
	default CharSupplier capture(byte b) {
		return () -> this.applyAsChar(b);
	}

	public static ByteToCharFunction constant(char r) {
		return (b) -> r;
	}

	/** Just to mirror the method: Ensures the result is not null */
	default char nonNull(byte b) {
		return applyAsChar(b);
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	public static ByteToCharFunction l(final @Nonnull ByteToCharFunction lambda) {
		Objects.requireNonNull(lambda, "Argument [lambda] cannot be null.");
		return lambda;
	}

	// <editor-fold desc="wrap">

	/** Wraps opposite (throwing/non-throwing) instance. */
	@Nonnull
	public static <X extends Exception> ByteToCharFunction wrap(final @Nonnull ByteToCharFunctionX<X> other) {
		return (byte b) -> {
			try {
				return other.applyAsChar(b);
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
	default ByteToCharFunction fromByte(@Nonnull final ByteUnaryOperator before1) {
		Objects.requireNonNull(before1, Function4U.VALIDATION_MESSAGE_BEFORE1);
		return (final byte v1) -> this.applyAsChar(before1.applyAsByte(v1));
	}

	/**
	 * Allows to manipulate the domain of the functyion.
	 */
	@Nonnull
	default <V1> ToCharFunction<V1> from(@Nonnull final ToByteFunction<? super V1> before1) {
		Objects.requireNonNull(before1, Function4U.VALIDATION_MESSAGE_BEFORE1);
		return (V1 v1) -> this.applyAsChar(before1.applyAsByte(v1));
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two functions together in a order. */
	@Nonnull
	default <V> ByteFunction<V> then(@Nonnull CharFunction<? extends V> after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (byte b) -> after.apply(this.applyAsChar(b));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default ByteUnaryOperator thenToByte(@Nonnull CharToByteFunction after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (byte b) -> after.applyAsByte(this.applyAsChar(b));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default ByteToShortFunction thenToShort(@Nonnull CharToShortFunction after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (byte b) -> after.applyAsShort(this.applyAsChar(b));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default ByteToIntFunction thenToInt(@Nonnull CharToIntFunction after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (byte b) -> after.applyAsInt(this.applyAsChar(b));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default ByteToLongFunction thenToLong(@Nonnull CharToLongFunction after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (byte b) -> after.applyAsLong(this.applyAsChar(b));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default ByteToFloatFunction thenToFloat(@Nonnull CharToFloatFunction after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (byte b) -> after.applyAsFloat(this.applyAsChar(b));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default ByteToDoubleFunction thenToDouble(@Nonnull CharToDoubleFunction after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (byte b) -> after.applyAsDouble(this.applyAsChar(b));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default ByteToCharFunction thenToChar(@Nonnull CharUnaryOperator after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (byte b) -> after.applyAsChar(this.applyAsChar(b));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default BytePredicate thenToBoolean(@Nonnull CharPredicate after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (byte b) -> after.test(this.applyAsChar(b));
	}

	// </editor-fold>

	// <editor-fold desc="variant conversions">

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default ByteToCharFunction nonThrowing() {
		return this;
	}

	/** Converts to throwing variant (RuntimeException). */
	@Nonnull
	default ByteToCharFunctionX<RuntimeException> uncheck() {
		return (ByteToCharFunctionX) this;
	}

	/** Dirty way, checked exception will propagate as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default ByteToCharFunction shove() {
		return this;
	}

	// </editor-fold>

	// <editor-fold desc="exception handling">

	/** Wraps with additional exception handling. */
	@Nonnull
	public static <X extends Exception, E extends Exception, Y extends RuntimeException> ByteToCharFunction wrapException(@Nonnull final ByteToCharFunction other, Class<E> exception, CharSupplier supplier, ExceptionHandler<E, Y> handler) {
		return (byte b) -> {
			try {
				return other.applyAsChar(b);
			} catch (Exception e) {
				try {
					if (supplier != null) {
						return supplier.getAsChar();
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
	default <E extends Exception, Y extends RuntimeException> ByteToCharFunction handle(Class<E> exception, ExceptionHandler<E, Y> handler) {
		Objects.requireNonNull(exception, Function4U.VALIDATION_MESSAGE_EXCEPTION);
		Objects.requireNonNull(handler, Function4U.VALIDATION_MESSAGE_HANDLER);

		return ByteToCharFunction.wrapException(this, exception, null, (ExceptionHandler) handler);
	}

	/** Wraps with exception handling that for any exception (including unchecked exception that might be different from X) will call handler function to determine the final exception. */
	@Nonnull
	default <Y extends RuntimeException> ByteToCharFunction handle(ExceptionHandler<Exception, Y> handler) {
		Objects.requireNonNull(handler, Function4U.VALIDATION_MESSAGE_HANDLER);

		return ByteToCharFunction.wrapException(this, Exception.class, null, (ExceptionHandler) handler);
	}

	/** Wraps with exception handling that for argument exception class will call supplier and return default value instead for propagating exception.  */
	@Nonnull
	default <E extends Exception, Y extends RuntimeException> ByteToCharFunction handle(Class<E> exception, CharSupplier supplier) {
		Objects.requireNonNull(exception, Function4U.VALIDATION_MESSAGE_EXCEPTION);
		Objects.requireNonNull(supplier, Function4U.VALIDATION_MESSAGE_HANDLER);

		return ByteToCharFunction.wrapException(this, exception, supplier, null);
	}

	/** Wraps with exception handling that for any exception will call supplier and return default value instead for propagating exception.  */
	@Nonnull
	default <Y extends RuntimeException> ByteToCharFunction handle(CharSupplier supplier) {
		Objects.requireNonNull(supplier, Function4U.VALIDATION_MESSAGE_HANDLER);

		return ByteToCharFunction.wrapException(this, Exception.class, supplier, null);
	}

	// </editor-fold>

}
