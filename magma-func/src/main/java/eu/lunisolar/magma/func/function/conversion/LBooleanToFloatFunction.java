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
 * Non-throwing functional interface (lambda) LBooleanToFloatFunction for Java 8.
 *
 * Type: function
 *
 * Domain (lvl: 1): boolean b
 *
 * Co-domain: none
 *
 * @see LBooleanToFloatFunctionX
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LBooleanToFloatFunction extends LBooleanToFloatFunctionX<RuntimeException>, MetaFunction, PrimitiveCodomain<Object>, MetaInterface.NonThrowing { // NOSONAR

	public static final String DESCRIPTION = "LBooleanToFloatFunction: float doApplyAsFloat(boolean b)";

	public float doApplyAsFloat(boolean b);

	default float nestingDoApplyAsFloat(boolean b) {
		return this.doApplyAsFloat(b);
	}

	default float shovingDoApplyAsFloat(boolean b) {
		return this.doApplyAsFloat(b);
	}

	/** Just to mirror the method: Ensures the result is not null */
	default float nonNullDoApplyAsFloat(boolean b) {
		return doApplyAsFloat(b);
	}

	/** Returns desxription of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LBooleanToFloatFunction.DESCRIPTION;
	}

	/** Captures arguments but delays the evaluation. */
	default LFloatSupplier capture(boolean b) {
		return () -> this.doApplyAsFloat(b);
	}

	public static LBooleanToFloatFunction constant(float r) {
		return (b) -> r;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	public static LBooleanToFloatFunction l(final @Nonnull LBooleanToFloatFunction lambda) {
		Objects.requireNonNull(lambda, "Argument [lambda] cannot be null.");
		return lambda;
	}

	// <editor-fold desc="wrap">

	/** Wraps opposite (throwing/non-throwing) instance. */
	@Nonnull
	public static <X extends Exception> LBooleanToFloatFunction wrap(final @Nonnull LBooleanToFloatFunctionX<X> other) {
		return other::nestingDoApplyAsFloat;
	}

	// </editor-fold>

	// <editor-fold desc="compose (functional)">

	/**
	 * Allows to manipulate the domain of the functyion.
	 */
	@Nonnull
	default LBooleanToFloatFunction fromBoolean(@Nonnull final LBooleanUnaryOperator before1) {
		Objects.requireNonNull(before1, Function4U.VALIDATION_MESSAGE_BEFORE1);
		return (final boolean v1) -> this.doApplyAsFloat(before1.doApplyAsBoolean(v1));
	}

	/**
	 * Allows to manipulate the domain of the functyion.
	 */
	@Nonnull
	default <V1> LToFloatFunction<V1> from(@Nonnull final LPredicate<? super V1> before1) {
		Objects.requireNonNull(before1, Function4U.VALIDATION_MESSAGE_BEFORE1);
		return (V1 v1) -> this.doApplyAsFloat(before1.doApplyAsBoolean(v1));
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two functions together in a order. */
	@Nonnull
	default <V> LBooleanFunction<V> then(@Nonnull LFloatFunction<? extends V> after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (boolean b) -> after.doApply(this.doApplyAsFloat(b));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LBooleanToByteFunction thenToByte(@Nonnull LFloatToByteFunction after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (boolean b) -> after.doApplyAsByte(this.doApplyAsFloat(b));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LBooleanToShortFunction thenToShort(@Nonnull LFloatToShortFunction after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (boolean b) -> after.doApplyAsShort(this.doApplyAsFloat(b));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LBooleanToIntFunction thenToInt(@Nonnull LFloatToIntFunction after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (boolean b) -> after.doApplyAsInt(this.doApplyAsFloat(b));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LBooleanToLongFunction thenToLong(@Nonnull LFloatToLongFunction after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (boolean b) -> after.doApplyAsLong(this.doApplyAsFloat(b));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LBooleanToFloatFunction thenToFloat(@Nonnull LFloatUnaryOperator after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (boolean b) -> after.doApplyAsFloat(this.doApplyAsFloat(b));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LBooleanToDoubleFunction thenToDouble(@Nonnull LFloatToDoubleFunction after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (boolean b) -> after.doApplyAsDouble(this.doApplyAsFloat(b));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LBooleanToCharFunction thenToChar(@Nonnull LFloatToCharFunction after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (boolean b) -> after.doApplyAsChar(this.doApplyAsFloat(b));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LBooleanUnaryOperator thenToBoolean(@Nonnull LFloatPredicate after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (boolean b) -> after.doTest(this.doApplyAsFloat(b));
	}

	// </editor-fold>

	// <editor-fold desc="variant conversions">

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default LBooleanToFloatFunction nest() {
		return this;
	}

	/** Converts to throwing variant (RuntimeException). */
	@Nonnull
	default LBooleanToFloatFunctionX<RuntimeException> nestX() {
		return this;
	}

	/** Dirty way, checked exception will propagate as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LBooleanToFloatFunction shove() {
		return this;
	}

	/** Dirty way, checked exception will propagate as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LBooleanToFloatFunctionX<RuntimeException> shoveX() {
		return this;
	}

	// </editor-fold>

	// <editor-fold desc="exception handling">

	/** Wraps with additional exception handling. */
	@Nonnull
	public static <X extends Exception, E extends Exception, Y extends RuntimeException> LBooleanToFloatFunction wrapException(@Nonnull final LBooleanToFloatFunction other, Class<E> exception, LFloatSupplier supplier, ExceptionHandler<E, Y> handler) {
		return (boolean b) -> {
			try {
				return other.doApplyAsFloat(b);
			} catch (Exception e) {
				try {
					if (supplier != null) {
						return supplier.doGetAsFloat();
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
	default <E extends Exception, Y extends RuntimeException> LBooleanToFloatFunction handle(Class<E> exception, ExceptionHandler<E, Y> handler) {
		Objects.requireNonNull(exception, Function4U.VALIDATION_MESSAGE_EXCEPTION);
		Objects.requireNonNull(handler, Function4U.VALIDATION_MESSAGE_HANDLER);

		return LBooleanToFloatFunction.wrapException(this, exception, null, (ExceptionHandler) handler);
	}

	/** Wraps with exception handling that for any exception (including unchecked exception that might be different from X) will call handler function to determine the final exception. */
	@Nonnull
	default <Y extends RuntimeException> LBooleanToFloatFunction handle(ExceptionHandler<Exception, Y> handler) {
		Objects.requireNonNull(handler, Function4U.VALIDATION_MESSAGE_HANDLER);

		return LBooleanToFloatFunction.wrapException(this, Exception.class, null, (ExceptionHandler) handler);
	}

	/** Wraps with exception handling that for argument exception class will call supplier and return default value instead for propagating exception.  */
	@Nonnull
	default <E extends Exception, Y extends RuntimeException> LBooleanToFloatFunction handle(Class<E> exception, LFloatSupplier supplier) {
		Objects.requireNonNull(exception, Function4U.VALIDATION_MESSAGE_EXCEPTION);
		Objects.requireNonNull(supplier, Function4U.VALIDATION_MESSAGE_HANDLER);

		return LBooleanToFloatFunction.wrapException(this, exception, supplier, null);
	}

	/** Wraps with exception handling that for any exception will call supplier and return default value instead for propagating exception.  */
	@Nonnull
	default <Y extends RuntimeException> LBooleanToFloatFunction handle(LFloatSupplier supplier) {
		Objects.requireNonNull(supplier, Function4U.VALIDATION_MESSAGE_HANDLER);

		return LBooleanToFloatFunction.wrapException(this, Exception.class, supplier, null);
	}

	// </editor-fold>

}
