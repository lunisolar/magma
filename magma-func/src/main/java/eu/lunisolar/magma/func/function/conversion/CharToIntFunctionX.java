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
 * Throwing functional interface (lambda) CharToIntFunctionX for Java 8.
 *
 * Type: function
 *
 * Domain (lvl: 1): char c
 *
 * Co-domain: none
 *
 * @see CharToIntFunction
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface CharToIntFunctionX<X extends Exception> extends MetaFunction, PrimitiveCodomain<CharToIntFunctionX<X>>, MetaThrowingInterface<X> { // NOSONAR

	public static final String DESCRIPTION = "CharToIntFunctionX: int applyAsInt(char c) throws X";

	public int applyAsInt(char c) throws X;

	/** Returns desxription of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return CharToIntFunctionX.DESCRIPTION;
	}

	/** Captures arguments but delays the evaluation. */
	default IntSupplierX<X> capture(char c) {
		return () -> this.applyAsInt(c);
	}

	/** Just to mirror the method: Ensures the result is not null */
	default int nonNull(char c) throws X {
		return applyAsInt(c);
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	public static <X extends Exception> CharToIntFunctionX<X> lX(final @Nonnull CharToIntFunctionX<X> lambda) {
		Objects.requireNonNull(lambda, "Argument [lambda] cannot be null.");
		return lambda;
	}

	// <editor-fold desc="wrap">

	/** Wraps opposite (throwing/non-throwing) instance. */
	@Nonnull
	public static <X extends Exception> CharToIntFunctionX<X> wrapX(final @Nonnull CharToIntFunction other) {
		return other::applyAsInt;
	}

	// </editor-fold>

	// <editor-fold desc="compose (functional)">

	/**
	 * Allows to manipulate the domain of the functyion.
	 */
	@Nonnull
	default CharToIntFunctionX<X> fromChar(@Nonnull final CharUnaryOperatorX<X> before1) {
		Objects.requireNonNull(before1, Function4U.VALIDATION_MESSAGE_BEFORE1);
		return (final char v1) -> this.applyAsInt(before1.applyAsChar(v1));
	}

	/**
	 * Allows to manipulate the domain of the functyion.
	 */
	@Nonnull
	default <V1> ToIntFunctionX<V1, X> from(@Nonnull final ToCharFunctionX<? super V1, X> before1) {
		Objects.requireNonNull(before1, Function4U.VALIDATION_MESSAGE_BEFORE1);
		return (V1 v1) -> this.applyAsInt(before1.applyAsChar(v1));
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two functions together in a order. */
	@Nonnull
	default <V> CharFunctionX<V, X> then(@Nonnull IntFunctionX<? extends V, X> after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (char c) -> after.apply(this.applyAsInt(c));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default CharToByteFunctionX<X> thenToByte(@Nonnull IntToByteFunctionX<X> after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (char c) -> after.applyAsByte(this.applyAsInt(c));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default CharToShortFunctionX<X> thenToShort(@Nonnull IntToShortFunctionX<X> after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (char c) -> after.applyAsShort(this.applyAsInt(c));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default CharToIntFunctionX<X> thenToInt(@Nonnull IntUnaryOperatorX<X> after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (char c) -> after.applyAsInt(this.applyAsInt(c));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default CharToLongFunctionX<X> thenToLong(@Nonnull IntToLongFunctionX<X> after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (char c) -> after.applyAsLong(this.applyAsInt(c));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default CharToFloatFunctionX<X> thenToFloat(@Nonnull IntToFloatFunctionX<X> after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (char c) -> after.applyAsFloat(this.applyAsInt(c));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default CharToDoubleFunctionX<X> thenToDouble(@Nonnull IntToDoubleFunctionX<X> after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (char c) -> after.applyAsDouble(this.applyAsInt(c));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default CharUnaryOperatorX<X> thenToChar(@Nonnull IntToCharFunctionX<X> after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (char c) -> after.applyAsChar(this.applyAsInt(c));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default CharPredicateX<X> thenToBoolean(@Nonnull IntPredicateX<X> after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (char c) -> after.test(this.applyAsInt(c));
	}

	// </editor-fold>

	// <editor-fold desc="variant conversions">

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default CharToIntFunction nonThrowing() {
		return CharToIntFunction.wrap(this);
	}

	/** Converts to throwing variant (RuntimeException). */
	@Nonnull
	default CharToIntFunctionX<RuntimeException> uncheck() {
		return nonThrowing()::applyAsInt;
	}

	/** Dirty way, checked exception will propagate as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default CharToIntFunction shove() {
		CharToIntFunctionX<RuntimeException> exceptionCast = (CharToIntFunctionX<RuntimeException>) this;
		return exceptionCast::applyAsInt;
	}

	// </editor-fold>

	// <editor-fold desc="exception handling">

	/** Wraps with additional exception handling. */
	@Nonnull
	public static <X extends Exception, E extends Exception, Y extends Exception> CharToIntFunctionX<Y> wrapException(@Nonnull final CharToIntFunctionX<X> other, Class<E> exception, IntSupplierX<X> supplier, ExceptionHandler<E, Y> handler) {
		return (char c) -> {
			try {
				return other.applyAsInt(c);
			} catch (Exception e) {
				try {
					if (supplier != null) {
						return supplier.getAsInt();
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
	default <E extends Exception, Y extends Exception> CharToIntFunctionX<Y> handle(Class<E> exception, ExceptionHandler<E, Y> handler) {
		Objects.requireNonNull(exception, Function4U.VALIDATION_MESSAGE_EXCEPTION);
		Objects.requireNonNull(handler, Function4U.VALIDATION_MESSAGE_HANDLER);

		return CharToIntFunctionX.wrapException(this, exception, null, (ExceptionHandler) handler);
	}

	/** Wraps with exception handling that for any exception (including unchecked exception that might be different from X) will call handler function to determine the final exception. */
	@Nonnull
	default <Y extends Exception> CharToIntFunctionX<Y> handle(ExceptionHandler<Exception, Y> handler) {
		Objects.requireNonNull(handler, Function4U.VALIDATION_MESSAGE_HANDLER);

		return CharToIntFunctionX.wrapException(this, Exception.class, null, (ExceptionHandler) handler);
	}

	/** Wraps with exception handling that for argument exception class will call supplier and return default value instead for propagating exception.  */
	@Nonnull
	default <E extends Exception, Y extends Exception> CharToIntFunctionX<Y> handle(Class<E> exception, IntSupplierX<X> supplier) {
		Objects.requireNonNull(exception, Function4U.VALIDATION_MESSAGE_EXCEPTION);
		Objects.requireNonNull(supplier, Function4U.VALIDATION_MESSAGE_HANDLER);

		return CharToIntFunctionX.wrapException(this, exception, supplier, null);
	}

	/** Wraps with exception handling that for any exception will call supplier and return default value instead for propagating exception.  */
	@Nonnull
	default <Y extends Exception> CharToIntFunctionX<Y> handle(IntSupplierX<X> supplier) {
		Objects.requireNonNull(supplier, Function4U.VALIDATION_MESSAGE_HANDLER);

		return CharToIntFunctionX.wrapException(this, Exception.class, supplier, null);
	}

	// </editor-fold>

}
