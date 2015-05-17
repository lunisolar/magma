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
 * Non-throwing functional interface (lambda) LFloatToShortFunction for Java 8.
 *
 * Type: function
 *
 * Domain (lvl: 1): float f
 *
 * Co-domain: none
 *
 * @see LFloatToShortFunctionX
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LFloatToShortFunction extends LFloatToShortFunctionX<RuntimeException>, MetaFunction, PrimitiveCodomain<Object>, MetaInterface.NonThrowing { // NOSONAR

	public static final String DESCRIPTION = "LFloatToShortFunction: short doApplyAsShort(float f)";

	public short doApplyAsShort(float f);

	default short nestingDoApplyAsShort(float f) {
		return this.doApplyAsShort(f);
	}

	default short shovingDoApplyAsShort(float f) {
		return this.doApplyAsShort(f);
	}

	/** Just to mirror the method: Ensures the result is not null */
	default short nonNullDoApplyAsShort(float f) {
		return doApplyAsShort(f);
	}

	/** Returns desxription of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LFloatToShortFunction.DESCRIPTION;
	}

	/** Captures arguments but delays the evaluation. */
	default LShortSupplier capture(float f) {
		return () -> this.doApplyAsShort(f);
	}

	public static LFloatToShortFunction constant(short r) {
		return (f) -> r;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	public static LFloatToShortFunction l(final @Nonnull LFloatToShortFunction lambda) {
		Objects.requireNonNull(lambda, "Argument [lambda] cannot be null.");
		return lambda;
	}

	// <editor-fold desc="wrap">

	/** Wraps opposite (throwing/non-throwing) instance. */
	@Nonnull
	public static <X extends Exception> LFloatToShortFunction wrap(final @Nonnull LFloatToShortFunctionX<X> other) {
		return other::nestingDoApplyAsShort;
	}

	// </editor-fold>

	// <editor-fold desc="compose (functional)">

	/**
	 * Allows to manipulate the domain of the functyion.
	 */
	@Nonnull
	default LFloatToShortFunction fromFloat(@Nonnull final LFloatUnaryOperator before1) {
		Objects.requireNonNull(before1, Function4U.VALIDATION_MESSAGE_BEFORE1);
		return (final float v1) -> this.doApplyAsShort(before1.doApplyAsFloat(v1));
	}

	/**
	 * Allows to manipulate the domain of the functyion.
	 */
	@Nonnull
	default <V1> LToShortFunction<V1> from(@Nonnull final LToFloatFunction<? super V1> before1) {
		Objects.requireNonNull(before1, Function4U.VALIDATION_MESSAGE_BEFORE1);
		return (V1 v1) -> this.doApplyAsShort(before1.doApplyAsFloat(v1));
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two functions together in a order. */
	@Nonnull
	default <V> LFloatFunction<V> then(@Nonnull LShortFunction<? extends V> after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (float f) -> after.doApply(this.doApplyAsShort(f));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LFloatToByteFunction thenToByte(@Nonnull LShortToByteFunction after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (float f) -> after.doApplyAsByte(this.doApplyAsShort(f));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LFloatToShortFunction thenToShort(@Nonnull LShortUnaryOperator after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (float f) -> after.doApplyAsShort(this.doApplyAsShort(f));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LFloatToIntFunction thenToInt(@Nonnull LShortToIntFunction after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (float f) -> after.doApplyAsInt(this.doApplyAsShort(f));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LFloatToLongFunction thenToLong(@Nonnull LShortToLongFunction after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (float f) -> after.doApplyAsLong(this.doApplyAsShort(f));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LFloatUnaryOperator thenToFloat(@Nonnull LShortToFloatFunction after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (float f) -> after.doApplyAsFloat(this.doApplyAsShort(f));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LFloatToDoubleFunction thenToDouble(@Nonnull LShortToDoubleFunction after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (float f) -> after.doApplyAsDouble(this.doApplyAsShort(f));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LFloatToCharFunction thenToChar(@Nonnull LShortToCharFunction after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (float f) -> after.doApplyAsChar(this.doApplyAsShort(f));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LFloatPredicate thenToBoolean(@Nonnull LShortPredicate after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (float f) -> after.doTest(this.doApplyAsShort(f));
	}

	// </editor-fold>

	// <editor-fold desc="variant conversions">

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default LFloatToShortFunction nest() {
		return this;
	}

	/** Converts to throwing variant (RuntimeException). */
	@Nonnull
	default LFloatToShortFunctionX<RuntimeException> nestX() {
		return this;
	}

	/** Dirty way, checked exception will propagate as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LFloatToShortFunction shove() {
		return this;
	}

	/** Dirty way, checked exception will propagate as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LFloatToShortFunctionX<RuntimeException> shoveX() {
		return this;
	}

	// </editor-fold>

	// <editor-fold desc="exception handling">

	/** Wraps with additional exception handling. */
	@Nonnull
	public static <X extends Exception, E extends Exception, Y extends RuntimeException> LFloatToShortFunction wrapException(@Nonnull final LFloatToShortFunction other, Class<E> exception, LShortSupplier supplier, ExceptionHandler<E, Y> handler) {
		return (float f) -> {
			try {
				return other.doApplyAsShort(f);
			} catch (Exception e) {
				try {
					if (supplier != null) {
						return supplier.doGetAsShort();
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
	default <E extends Exception, Y extends RuntimeException> LFloatToShortFunction handle(Class<E> exception, ExceptionHandler<E, Y> handler) {
		Objects.requireNonNull(exception, Function4U.VALIDATION_MESSAGE_EXCEPTION);
		Objects.requireNonNull(handler, Function4U.VALIDATION_MESSAGE_HANDLER);

		return LFloatToShortFunction.wrapException(this, exception, null, (ExceptionHandler) handler);
	}

	/** Wraps with exception handling that for any exception (including unchecked exception that might be different from X) will call handler function to determine the final exception. */
	@Nonnull
	default <Y extends RuntimeException> LFloatToShortFunction handle(ExceptionHandler<Exception, Y> handler) {
		Objects.requireNonNull(handler, Function4U.VALIDATION_MESSAGE_HANDLER);

		return LFloatToShortFunction.wrapException(this, Exception.class, null, (ExceptionHandler) handler);
	}

	/** Wraps with exception handling that for argument exception class will call supplier and return default value instead for propagating exception.  */
	@Nonnull
	default <E extends Exception, Y extends RuntimeException> LFloatToShortFunction handle(Class<E> exception, LShortSupplier supplier) {
		Objects.requireNonNull(exception, Function4U.VALIDATION_MESSAGE_EXCEPTION);
		Objects.requireNonNull(supplier, Function4U.VALIDATION_MESSAGE_HANDLER);

		return LFloatToShortFunction.wrapException(this, exception, supplier, null);
	}

	/** Wraps with exception handling that for any exception will call supplier and return default value instead for propagating exception.  */
	@Nonnull
	default <Y extends RuntimeException> LFloatToShortFunction handle(LShortSupplier supplier) {
		Objects.requireNonNull(supplier, Function4U.VALIDATION_MESSAGE_HANDLER);

		return LFloatToShortFunction.wrapException(this, Exception.class, supplier, null);
	}

	// </editor-fold>

}
