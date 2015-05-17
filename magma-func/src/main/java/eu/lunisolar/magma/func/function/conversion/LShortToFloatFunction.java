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
 * Non-throwing functional interface (lambda) LShortToFloatFunction for Java 8.
 *
 * Type: function
 *
 * Domain (lvl: 1): short s
 *
 * Co-domain: none
 *
 * @see LShortToFloatFunctionX
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LShortToFloatFunction extends LShortToFloatFunctionX<RuntimeException>, MetaFunction, PrimitiveCodomain<Object>, MetaInterface.NonThrowing { // NOSONAR

	public static final String DESCRIPTION = "LShortToFloatFunction: float doApplyAsFloat(short s)";

	public float doApplyAsFloat(short s);

	default float nestingDoApplyAsFloat(short s) {
		return this.doApplyAsFloat(s);
	}

	default float shovingDoApplyAsFloat(short s) {
		return this.doApplyAsFloat(s);
	}

	/** Just to mirror the method: Ensures the result is not null */
	default float nonNullDoApplyAsFloat(short s) {
		return doApplyAsFloat(s);
	}

	/** Returns desxription of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LShortToFloatFunction.DESCRIPTION;
	}

	/** Captures arguments but delays the evaluation. */
	default LFloatSupplier capture(short s) {
		return () -> this.doApplyAsFloat(s);
	}

	public static LShortToFloatFunction constant(float r) {
		return (s) -> r;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	public static LShortToFloatFunction l(final @Nonnull LShortToFloatFunction lambda) {
		Objects.requireNonNull(lambda, "Argument [lambda] cannot be null.");
		return lambda;
	}

	// <editor-fold desc="wrap">

	/** Wraps opposite (throwing/non-throwing) instance. */
	@Nonnull
	public static <X extends Exception> LShortToFloatFunction wrap(final @Nonnull LShortToFloatFunctionX<X> other) {
		return other::nestingDoApplyAsFloat;
	}

	// </editor-fold>

	// <editor-fold desc="compose (functional)">

	/**
	 * Allows to manipulate the domain of the functyion.
	 */
	@Nonnull
	default LShortToFloatFunction fromShort(@Nonnull final LShortUnaryOperator before1) {
		Objects.requireNonNull(before1, Function4U.VALIDATION_MESSAGE_BEFORE1);
		return (final short v1) -> this.doApplyAsFloat(before1.doApplyAsShort(v1));
	}

	/**
	 * Allows to manipulate the domain of the functyion.
	 */
	@Nonnull
	default <V1> LToFloatFunction<V1> from(@Nonnull final LToShortFunction<? super V1> before1) {
		Objects.requireNonNull(before1, Function4U.VALIDATION_MESSAGE_BEFORE1);
		return (V1 v1) -> this.doApplyAsFloat(before1.doApplyAsShort(v1));
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two functions together in a order. */
	@Nonnull
	default <V> LShortFunction<V> then(@Nonnull LFloatFunction<? extends V> after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (short s) -> after.doApply(this.doApplyAsFloat(s));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LShortToByteFunction thenToByte(@Nonnull LFloatToByteFunction after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (short s) -> after.doApplyAsByte(this.doApplyAsFloat(s));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LShortUnaryOperator thenToShort(@Nonnull LFloatToShortFunction after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (short s) -> after.doApplyAsShort(this.doApplyAsFloat(s));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LShortToIntFunction thenToInt(@Nonnull LFloatToIntFunction after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (short s) -> after.doApplyAsInt(this.doApplyAsFloat(s));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LShortToLongFunction thenToLong(@Nonnull LFloatToLongFunction after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (short s) -> after.doApplyAsLong(this.doApplyAsFloat(s));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LShortToFloatFunction thenToFloat(@Nonnull LFloatUnaryOperator after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (short s) -> after.doApplyAsFloat(this.doApplyAsFloat(s));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LShortToDoubleFunction thenToDouble(@Nonnull LFloatToDoubleFunction after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (short s) -> after.doApplyAsDouble(this.doApplyAsFloat(s));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LShortToCharFunction thenToChar(@Nonnull LFloatToCharFunction after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (short s) -> after.doApplyAsChar(this.doApplyAsFloat(s));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LShortPredicate thenToBoolean(@Nonnull LFloatPredicate after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (short s) -> after.doTest(this.doApplyAsFloat(s));
	}

	// </editor-fold>

	// <editor-fold desc="variant conversions">

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default LShortToFloatFunction nest() {
		return this;
	}

	/** Converts to throwing variant (RuntimeException). */
	@Nonnull
	default LShortToFloatFunctionX<RuntimeException> nestX() {
		return this;
	}

	/** Dirty way, checked exception will propagate as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LShortToFloatFunction shove() {
		return this;
	}

	/** Dirty way, checked exception will propagate as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LShortToFloatFunctionX<RuntimeException> shoveX() {
		return this;
	}

	// </editor-fold>

	// <editor-fold desc="exception handling">

	/** Wraps with additional exception handling. */
	@Nonnull
	public static <X extends Exception, E extends Exception, Y extends RuntimeException> LShortToFloatFunction wrapException(@Nonnull final LShortToFloatFunction other, Class<E> exception, LFloatSupplier supplier, ExceptionHandler<E, Y> handler) {
		return (short s) -> {
			try {
				return other.doApplyAsFloat(s);
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
	default <E extends Exception, Y extends RuntimeException> LShortToFloatFunction handle(Class<E> exception, ExceptionHandler<E, Y> handler) {
		Objects.requireNonNull(exception, Function4U.VALIDATION_MESSAGE_EXCEPTION);
		Objects.requireNonNull(handler, Function4U.VALIDATION_MESSAGE_HANDLER);

		return LShortToFloatFunction.wrapException(this, exception, null, (ExceptionHandler) handler);
	}

	/** Wraps with exception handling that for any exception (including unchecked exception that might be different from X) will call handler function to determine the final exception. */
	@Nonnull
	default <Y extends RuntimeException> LShortToFloatFunction handle(ExceptionHandler<Exception, Y> handler) {
		Objects.requireNonNull(handler, Function4U.VALIDATION_MESSAGE_HANDLER);

		return LShortToFloatFunction.wrapException(this, Exception.class, null, (ExceptionHandler) handler);
	}

	/** Wraps with exception handling that for argument exception class will call supplier and return default value instead for propagating exception.  */
	@Nonnull
	default <E extends Exception, Y extends RuntimeException> LShortToFloatFunction handle(Class<E> exception, LFloatSupplier supplier) {
		Objects.requireNonNull(exception, Function4U.VALIDATION_MESSAGE_EXCEPTION);
		Objects.requireNonNull(supplier, Function4U.VALIDATION_MESSAGE_HANDLER);

		return LShortToFloatFunction.wrapException(this, exception, supplier, null);
	}

	/** Wraps with exception handling that for any exception will call supplier and return default value instead for propagating exception.  */
	@Nonnull
	default <Y extends RuntimeException> LShortToFloatFunction handle(LFloatSupplier supplier) {
		Objects.requireNonNull(supplier, Function4U.VALIDATION_MESSAGE_HANDLER);

		return LShortToFloatFunction.wrapException(this, Exception.class, supplier, null);
	}

	// </editor-fold>

}
