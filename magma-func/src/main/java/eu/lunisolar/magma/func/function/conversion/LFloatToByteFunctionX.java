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
 * Throwing functional interface (lambda) LFloatToByteFunctionX for Java 8.
 *
 * Type: function
 *
 * Domain (lvl: 1): float f
 *
 * Co-domain: none
 *
 * @see LFloatToByteFunction
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LFloatToByteFunctionX<X extends Exception> extends MetaFunction, PrimitiveCodomain<Object>, MetaInterface.Throwing<X> { // NOSONAR

	public static final String DESCRIPTION = "LFloatToByteFunctionX: byte doApplyAsByte(float f) throws X";

	public byte doApplyAsByte(float f) throws X;

	default byte nestingDoApplyAsByte(float f) {
		try {
			return this.doApplyAsByte(f);
		} catch (RuntimeException e) {
			throw e;
		} catch (Exception e) {
			throw new NestedException(e);
		}
	}

	default byte shovingDoApplyAsByte(float f) {
		return ((LFloatToByteFunctionX<RuntimeException>) this).doApplyAsByte(f);
	}

	/** Just to mirror the method: Ensures the result is not null */
	default byte nonNullDoApplyAsByte(float f) throws X {
		return doApplyAsByte(f);
	}

	/** Returns desxription of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LFloatToByteFunctionX.DESCRIPTION;
	}

	/** Captures arguments but delays the evaluation. */
	default LByteSupplierX<X> capture(float f) {
		return () -> this.doApplyAsByte(f);
	}

	public static <X extends Exception> LFloatToByteFunctionX<X> constant(byte r) {
		return (f) -> r;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	public static <X extends Exception> LFloatToByteFunctionX<X> lX(final @Nonnull LFloatToByteFunctionX<X> lambda) {
		Objects.requireNonNull(lambda, "Argument [lambda] cannot be null.");
		return lambda;
	}

	// <editor-fold desc="wrap">

	/** Wraps opposite (throwing/non-throwing) instance. */
	@Nonnull
	public static <X extends Exception> LFloatToByteFunctionX<X> wrapX(final @Nonnull LFloatToByteFunction other) {
		return (LFloatToByteFunctionX) other;
	}

	// </editor-fold>

	// <editor-fold desc="compose (functional)">

	/**
	 * Allows to manipulate the domain of the functyion.
	 */
	@Nonnull
	default LFloatToByteFunctionX<X> fromFloat(@Nonnull final LFloatUnaryOperatorX<X> before1) {
		Objects.requireNonNull(before1, Function4U.VALIDATION_MESSAGE_BEFORE1);
		return (final float v1) -> this.doApplyAsByte(before1.doApplyAsFloat(v1));
	}

	/**
	 * Allows to manipulate the domain of the functyion.
	 */
	@Nonnull
	default <V1> LToByteFunctionX<V1, X> from(@Nonnull final LToFloatFunctionX<? super V1, X> before1) {
		Objects.requireNonNull(before1, Function4U.VALIDATION_MESSAGE_BEFORE1);
		return (V1 v1) -> this.doApplyAsByte(before1.doApplyAsFloat(v1));
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two functions together in a order. */
	@Nonnull
	default <V> LFloatFunctionX<V, X> then(@Nonnull LByteFunctionX<? extends V, X> after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (float f) -> after.doApply(this.doApplyAsByte(f));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LFloatToByteFunctionX<X> thenToByte(@Nonnull LByteUnaryOperatorX<X> after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (float f) -> after.doApplyAsByte(this.doApplyAsByte(f));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LFloatToShortFunctionX<X> thenToShort(@Nonnull LByteToShortFunctionX<X> after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (float f) -> after.doApplyAsShort(this.doApplyAsByte(f));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LFloatToIntFunctionX<X> thenToInt(@Nonnull LByteToIntFunctionX<X> after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (float f) -> after.doApplyAsInt(this.doApplyAsByte(f));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LFloatToLongFunctionX<X> thenToLong(@Nonnull LByteToLongFunctionX<X> after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (float f) -> after.doApplyAsLong(this.doApplyAsByte(f));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LFloatUnaryOperatorX<X> thenToFloat(@Nonnull LByteToFloatFunctionX<X> after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (float f) -> after.doApplyAsFloat(this.doApplyAsByte(f));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LFloatToDoubleFunctionX<X> thenToDouble(@Nonnull LByteToDoubleFunctionX<X> after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (float f) -> after.doApplyAsDouble(this.doApplyAsByte(f));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LFloatToCharFunctionX<X> thenToChar(@Nonnull LByteToCharFunctionX<X> after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (float f) -> after.doApplyAsChar(this.doApplyAsByte(f));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LFloatPredicateX<X> thenToBoolean(@Nonnull LBytePredicateX<X> after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (float f) -> after.doTest(this.doApplyAsByte(f));
	}

	// </editor-fold>

	// <editor-fold desc="variant conversions">

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default LFloatToByteFunction nest() {
		return this::nestingDoApplyAsByte;
	}

	/** Converts to throwing variant (RuntimeException). */
	@Nonnull
	default LFloatToByteFunctionX<RuntimeException> nestX() {
		return this::nestingDoApplyAsByte;
	}

	/** Dirty way, checked exception will propagate as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LFloatToByteFunction shove() {
		return this::shovingDoApplyAsByte;
	}

	/** Dirty way, checked exception will propagate as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LFloatToByteFunctionX<RuntimeException> shoveX() {
		return this::shovingDoApplyAsByte;
	}

	// </editor-fold>

	// <editor-fold desc="exception handling">

	/** Wraps with additional exception handling. */
	@Nonnull
	public static <X extends Exception, E extends Exception, Y extends Exception> LFloatToByteFunctionX<Y> wrapException(@Nonnull final LFloatToByteFunctionX<X> other, Class<E> exception, LByteSupplierX<X> supplier, ExceptionHandler<E, Y> handler) {
		return (float f) -> {
			try {
				return other.doApplyAsByte(f);
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
	default <E extends Exception, Y extends Exception> LFloatToByteFunctionX<Y> handleX(Class<E> exception, ExceptionHandler<E, Y> handler) {
		Objects.requireNonNull(exception, Function4U.VALIDATION_MESSAGE_EXCEPTION);
		Objects.requireNonNull(handler, Function4U.VALIDATION_MESSAGE_HANDLER);

		return LFloatToByteFunctionX.wrapException(this, exception, null, (ExceptionHandler) handler);
	}

	/** Wraps with exception handling that for any exception (including unchecked exception that might be different from X) will call handler function to determine the final exception. */
	@Nonnull
	default <Y extends Exception> LFloatToByteFunctionX<Y> handleX(ExceptionHandler<Exception, Y> handler) {
		Objects.requireNonNull(handler, Function4U.VALIDATION_MESSAGE_HANDLER);

		return LFloatToByteFunctionX.wrapException(this, Exception.class, null, (ExceptionHandler) handler);
	}

	/** Wraps with exception handling that for argument exception class will call supplier and return default value instead for propagating exception.  */
	@Nonnull
	default <E extends Exception, Y extends Exception> LFloatToByteFunctionX<Y> handleX(Class<E> exception, LByteSupplierX<X> supplier) {
		Objects.requireNonNull(exception, Function4U.VALIDATION_MESSAGE_EXCEPTION);
		Objects.requireNonNull(supplier, Function4U.VALIDATION_MESSAGE_HANDLER);

		return LFloatToByteFunctionX.wrapException(this, exception, supplier, null);
	}

	/** Wraps with exception handling that for any exception will call supplier and return default value instead for propagating exception.  */
	@Nonnull
	default <Y extends Exception> LFloatToByteFunctionX<Y> handleX(LByteSupplierX<X> supplier) {
		Objects.requireNonNull(supplier, Function4U.VALIDATION_MESSAGE_HANDLER);

		return LFloatToByteFunctionX.wrapException(this, Exception.class, supplier, null);
	}

	// </editor-fold>

}
