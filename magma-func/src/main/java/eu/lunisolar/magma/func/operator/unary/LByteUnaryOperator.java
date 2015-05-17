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
 * Non-throwing functional interface (lambda) LByteUnaryOperator for Java 8.
 *
 * Type: operator
 *
 * Domain (lvl: 1): byte b
 *
 * Co-domain: none
 *
 * @see LByteUnaryOperatorX
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LByteUnaryOperator extends LByteUnaryOperatorX<RuntimeException>, MetaOperator, PrimitiveCodomain<Object>, MetaInterface.NonThrowing { // NOSONAR

	public static final String DESCRIPTION = "LByteUnaryOperator: byte doApplyAsByte(byte b)";

	public byte doApplyAsByte(byte b);

	default byte nestingDoApplyAsByte(byte b) {
		return this.doApplyAsByte(b);
	}

	default byte shovingDoApplyAsByte(byte b) {
		return this.doApplyAsByte(b);
	}

	/** Just to mirror the method: Ensures the result is not null */
	default byte nonNullDoApplyAsByte(byte b) {
		return doApplyAsByte(b);
	}

	/** Returns desxription of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LByteUnaryOperator.DESCRIPTION;
	}

	/** Captures arguments but delays the evaluation. */
	default LByteSupplier capture(byte b) {
		return () -> this.doApplyAsByte(b);
	}

	public static LByteUnaryOperator constant(byte r) {
		return (b) -> r;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	public static LByteUnaryOperator l(final @Nonnull LByteUnaryOperator lambda) {
		Objects.requireNonNull(lambda, "Argument [lambda] cannot be null.");
		return lambda;
	}

	// <editor-fold desc="wrap">

	/** Wraps opposite (throwing/non-throwing) instance. */
	@Nonnull
	public static <X extends Exception> LByteUnaryOperator wrap(final @Nonnull LByteUnaryOperatorX<X> other) {
		return other::nestingDoApplyAsByte;
	}

	// </editor-fold>

	// <editor-fold desc="compose (functional)">

	/**
	 * Allows to manipulate the domain of the functyion.
	 */
	@Nonnull
	default LByteUnaryOperator fromByte(@Nonnull final LByteUnaryOperator before1) {
		Objects.requireNonNull(before1, Function4U.VALIDATION_MESSAGE_BEFORE1);
		return (final byte v1) -> this.doApplyAsByte(before1.doApplyAsByte(v1));
	}

	/**
	 * Allows to manipulate the domain of the functyion.
	 */
	@Nonnull
	default <V1> LToByteFunction<V1> from(@Nonnull final LToByteFunction<? super V1> before1) {
		Objects.requireNonNull(before1, Function4U.VALIDATION_MESSAGE_BEFORE1);
		return (V1 v1) -> this.doApplyAsByte(before1.doApplyAsByte(v1));
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two operators together in a order. */
	@Nonnull
	default <V> LByteFunction<V> then(@Nonnull LByteFunction<? extends V> after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (byte b) -> after.doApply(this.doApplyAsByte(b));
	}

	/** Combines two operators together in a order. */
	@Nonnull
	default LByteUnaryOperator thenToByte(@Nonnull LByteUnaryOperator after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (byte b) -> after.doApplyAsByte(this.doApplyAsByte(b));
	}

	/** Combines two operators together in a order. */
	@Nonnull
	default LByteToShortFunction thenToShort(@Nonnull LByteToShortFunction after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (byte b) -> after.doApplyAsShort(this.doApplyAsByte(b));
	}

	/** Combines two operators together in a order. */
	@Nonnull
	default LByteToIntFunction thenToInt(@Nonnull LByteToIntFunction after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (byte b) -> after.doApplyAsInt(this.doApplyAsByte(b));
	}

	/** Combines two operators together in a order. */
	@Nonnull
	default LByteToLongFunction thenToLong(@Nonnull LByteToLongFunction after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (byte b) -> after.doApplyAsLong(this.doApplyAsByte(b));
	}

	/** Combines two operators together in a order. */
	@Nonnull
	default LByteToFloatFunction thenToFloat(@Nonnull LByteToFloatFunction after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (byte b) -> after.doApplyAsFloat(this.doApplyAsByte(b));
	}

	/** Combines two operators together in a order. */
	@Nonnull
	default LByteToDoubleFunction thenToDouble(@Nonnull LByteToDoubleFunction after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (byte b) -> after.doApplyAsDouble(this.doApplyAsByte(b));
	}

	/** Combines two operators together in a order. */
	@Nonnull
	default LByteToCharFunction thenToChar(@Nonnull LByteToCharFunction after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (byte b) -> after.doApplyAsChar(this.doApplyAsByte(b));
	}

	/** Combines two operators together in a order. */
	@Nonnull
	default LBytePredicate thenToBoolean(@Nonnull LBytePredicate after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (byte b) -> after.doTest(this.doApplyAsByte(b));
	}

	// </editor-fold>

	/** Returns a function that always returns its input argument. */
	@Nonnull
	public static LByteUnaryOperator identity() {
		return t -> t;
	}

	// <editor-fold desc="variant conversions">

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default LByteUnaryOperator nest() {
		return this;
	}

	/** Converts to throwing variant (RuntimeException). */
	@Nonnull
	default LByteUnaryOperatorX<RuntimeException> nestX() {
		return this;
	}

	/** Dirty way, checked exception will propagate as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LByteUnaryOperator shove() {
		return this;
	}

	/** Dirty way, checked exception will propagate as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LByteUnaryOperatorX<RuntimeException> shoveX() {
		return this;
	}

	// </editor-fold>

	// <editor-fold desc="exception handling">

	/** Wraps with additional exception handling. */
	@Nonnull
	public static <X extends Exception, E extends Exception, Y extends RuntimeException> LByteUnaryOperator wrapException(@Nonnull final LByteUnaryOperator other, Class<E> exception, LByteSupplier supplier, ExceptionHandler<E, Y> handler) {
		return (byte b) -> {
			try {
				return other.doApplyAsByte(b);
			} catch (Exception e) {
				try {
					if (supplier != null) {
						return supplier.doGetAsByte();
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
	default <E extends Exception, Y extends RuntimeException> LByteUnaryOperator handle(Class<E> exception, ExceptionHandler<E, Y> handler) {
		Objects.requireNonNull(exception, Function4U.VALIDATION_MESSAGE_EXCEPTION);
		Objects.requireNonNull(handler, Function4U.VALIDATION_MESSAGE_HANDLER);

		return LByteUnaryOperator.wrapException(this, exception, null, (ExceptionHandler) handler);
	}

	/** Wraps with exception handling that for any exception (including unchecked exception that might be different from X) will call handler function to determine the final exception. */
	@Nonnull
	default <Y extends RuntimeException> LByteUnaryOperator handle(ExceptionHandler<Exception, Y> handler) {
		Objects.requireNonNull(handler, Function4U.VALIDATION_MESSAGE_HANDLER);

		return LByteUnaryOperator.wrapException(this, Exception.class, null, (ExceptionHandler) handler);
	}

	/** Wraps with exception handling that for argument exception class will call supplier and return default value instead for propagating exception.  */
	@Nonnull
	default <E extends Exception, Y extends RuntimeException> LByteUnaryOperator handle(Class<E> exception, LByteSupplier supplier) {
		Objects.requireNonNull(exception, Function4U.VALIDATION_MESSAGE_EXCEPTION);
		Objects.requireNonNull(supplier, Function4U.VALIDATION_MESSAGE_HANDLER);

		return LByteUnaryOperator.wrapException(this, exception, supplier, null);
	}

	/** Wraps with exception handling that for any exception will call supplier and return default value instead for propagating exception.  */
	@Nonnull
	default <Y extends RuntimeException> LByteUnaryOperator handle(LByteSupplier supplier) {
		Objects.requireNonNull(supplier, Function4U.VALIDATION_MESSAGE_HANDLER);

		return LByteUnaryOperator.wrapException(this, Exception.class, supplier, null);
	}

	// </editor-fold>

}
