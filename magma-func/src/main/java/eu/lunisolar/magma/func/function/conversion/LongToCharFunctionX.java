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
 * Throwing functional interface (lambda) LongToCharFunctionX for Java 8.
 *
 * Type: function
 *
 * Domain (lvl: 1): long l
 *
 * Co-domain: none
 *
 * @see LongToCharFunction
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LongToCharFunctionX<X extends Exception> extends MetaFunction, PrimitiveCodomain<LongToCharFunctionX<X>>, MetaThrowingInterface<X> { // NOSONAR

	public static final String DESCRIPTION = "LongToCharFunctionX: char applyAsChar(long l) throws X";

	public char applyAsChar(long l) throws X;

	/** Returns desxription of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LongToCharFunctionX.DESCRIPTION;
	}

	/** Captures arguments but delays the evaluation. */
	default CharSupplierX<X> capture(long l) {
		return () -> this.applyAsChar(l);
	}

	public static <X extends Exception> LongToCharFunctionX<X> constant(char r) {
		return (l) -> r;
	}

	/** Just to mirror the method: Ensures the result is not null */
	default char nonNull(long l) throws X {
		return applyAsChar(l);
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	public static <X extends Exception> LongToCharFunctionX<X> lX(final @Nonnull LongToCharFunctionX<X> lambda) {
		Objects.requireNonNull(lambda, "Argument [lambda] cannot be null.");
		return lambda;
	}

	// <editor-fold desc="wrap">

	/** Wraps opposite (throwing/non-throwing) instance. */
	@Nonnull
	public static <X extends Exception> LongToCharFunctionX<X> wrapX(final @Nonnull LongToCharFunction other) {
		return other::applyAsChar;
	}

	// </editor-fold>

	// <editor-fold desc="compose (functional)">

	/**
	 * Allows to manipulate the domain of the functyion.
	 */
	@Nonnull
	default LongToCharFunctionX<X> fromLong(@Nonnull final LongUnaryOperatorX<X> before1) {
		Objects.requireNonNull(before1, Function4U.VALIDATION_MESSAGE_BEFORE1);
		return (final long v1) -> this.applyAsChar(before1.applyAsLong(v1));
	}

	/**
	 * Allows to manipulate the domain of the functyion.
	 */
	@Nonnull
	default <V1> ToCharFunctionX<V1, X> from(@Nonnull final ToLongFunctionX<? super V1, X> before1) {
		Objects.requireNonNull(before1, Function4U.VALIDATION_MESSAGE_BEFORE1);
		return (V1 v1) -> this.applyAsChar(before1.applyAsLong(v1));
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two functions together in a order. */
	@Nonnull
	default <V> LongFunctionX<V, X> then(@Nonnull CharFunctionX<? extends V, X> after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (long l) -> after.apply(this.applyAsChar(l));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LongToByteFunctionX<X> thenToByte(@Nonnull CharToByteFunctionX<X> after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (long l) -> after.applyAsByte(this.applyAsChar(l));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LongToShortFunctionX<X> thenToShort(@Nonnull CharToShortFunctionX<X> after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (long l) -> after.applyAsShort(this.applyAsChar(l));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LongToIntFunctionX<X> thenToInt(@Nonnull CharToIntFunctionX<X> after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (long l) -> after.applyAsInt(this.applyAsChar(l));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LongUnaryOperatorX<X> thenToLong(@Nonnull CharToLongFunctionX<X> after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (long l) -> after.applyAsLong(this.applyAsChar(l));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LongToFloatFunctionX<X> thenToFloat(@Nonnull CharToFloatFunctionX<X> after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (long l) -> after.applyAsFloat(this.applyAsChar(l));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LongToDoubleFunctionX<X> thenToDouble(@Nonnull CharToDoubleFunctionX<X> after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (long l) -> after.applyAsDouble(this.applyAsChar(l));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LongToCharFunctionX<X> thenToChar(@Nonnull CharUnaryOperatorX<X> after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (long l) -> after.applyAsChar(this.applyAsChar(l));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LongPredicateX<X> thenToBoolean(@Nonnull CharPredicateX<X> after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (long l) -> after.test(this.applyAsChar(l));
	}

	// </editor-fold>

	// <editor-fold desc="variant conversions">

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default LongToCharFunction nonThrowing() {
		return LongToCharFunction.wrap(this);
	}

	/** Converts to throwing variant (RuntimeException). */
	@Nonnull
	default LongToCharFunctionX<RuntimeException> uncheck() {
		return nonThrowing()::applyAsChar;
	}

	/** Dirty way, checked exception will propagate as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LongToCharFunction shove() {
		LongToCharFunctionX<RuntimeException> exceptionCast = (LongToCharFunctionX<RuntimeException>) this;
		return exceptionCast::applyAsChar;
	}

	// </editor-fold>

	// <editor-fold desc="exception handling">

	/** Wraps with additional exception handling. */
	@Nonnull
	public static <X extends Exception, E extends Exception, Y extends Exception> LongToCharFunctionX<Y> wrapException(@Nonnull final LongToCharFunctionX<X> other, Class<E> exception, CharSupplierX<X> supplier, ExceptionHandler<E, Y> handler) {
		return (long l) -> {
			try {
				return other.applyAsChar(l);
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
	default <E extends Exception, Y extends Exception> LongToCharFunctionX<Y> handle(Class<E> exception, ExceptionHandler<E, Y> handler) {
		Objects.requireNonNull(exception, Function4U.VALIDATION_MESSAGE_EXCEPTION);
		Objects.requireNonNull(handler, Function4U.VALIDATION_MESSAGE_HANDLER);

		return LongToCharFunctionX.wrapException(this, exception, null, (ExceptionHandler) handler);
	}

	/** Wraps with exception handling that for any exception (including unchecked exception that might be different from X) will call handler function to determine the final exception. */
	@Nonnull
	default <Y extends Exception> LongToCharFunctionX<Y> handle(ExceptionHandler<Exception, Y> handler) {
		Objects.requireNonNull(handler, Function4U.VALIDATION_MESSAGE_HANDLER);

		return LongToCharFunctionX.wrapException(this, Exception.class, null, (ExceptionHandler) handler);
	}

	/** Wraps with exception handling that for argument exception class will call supplier and return default value instead for propagating exception.  */
	@Nonnull
	default <E extends Exception, Y extends Exception> LongToCharFunctionX<Y> handle(Class<E> exception, CharSupplierX<X> supplier) {
		Objects.requireNonNull(exception, Function4U.VALIDATION_MESSAGE_EXCEPTION);
		Objects.requireNonNull(supplier, Function4U.VALIDATION_MESSAGE_HANDLER);

		return LongToCharFunctionX.wrapException(this, exception, supplier, null);
	}

	/** Wraps with exception handling that for any exception will call supplier and return default value instead for propagating exception.  */
	@Nonnull
	default <Y extends Exception> LongToCharFunctionX<Y> handle(CharSupplierX<X> supplier) {
		Objects.requireNonNull(supplier, Function4U.VALIDATION_MESSAGE_HANDLER);

		return LongToCharFunctionX.wrapException(this, Exception.class, supplier, null);
	}

	// </editor-fold>

}
