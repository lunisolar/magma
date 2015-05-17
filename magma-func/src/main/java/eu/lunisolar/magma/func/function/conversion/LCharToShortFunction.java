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
 * Non-throwing functional interface (lambda) LCharToShortFunction for Java 8.
 *
 * Type: function
 *
 * Domain (lvl: 1): char c
 *
 * Co-domain: none
 *
 * @see LCharToShortFunctionX
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LCharToShortFunction extends LCharToShortFunctionX<RuntimeException>, MetaFunction, PrimitiveCodomain<Object>, MetaInterface.NonThrowing { // NOSONAR

	public static final String DESCRIPTION = "LCharToShortFunction: short doApplyAsShort(char c)";

	public short doApplyAsShort(char c);

	default short nestingDoApplyAsShort(char c) {
		return this.doApplyAsShort(c);
	}

	default short shovingDoApplyAsShort(char c) {
		return this.doApplyAsShort(c);
	}

	/** Just to mirror the method: Ensures the result is not null */
	default short nonNullDoApplyAsShort(char c) {
		return doApplyAsShort(c);
	}

	/** Returns desxription of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LCharToShortFunction.DESCRIPTION;
	}

	/** Captures arguments but delays the evaluation. */
	default LShortSupplier capture(char c) {
		return () -> this.doApplyAsShort(c);
	}

	public static LCharToShortFunction constant(short r) {
		return (c) -> r;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	public static LCharToShortFunction l(final @Nonnull LCharToShortFunction lambda) {
		Objects.requireNonNull(lambda, "Argument [lambda] cannot be null.");
		return lambda;
	}

	// <editor-fold desc="wrap">

	/** Wraps opposite (throwing/non-throwing) instance. */
	@Nonnull
	public static <X extends Exception> LCharToShortFunction wrap(final @Nonnull LCharToShortFunctionX<X> other) {
		return other::nestingDoApplyAsShort;
	}

	// </editor-fold>

	// <editor-fold desc="compose (functional)">

	/**
	 * Allows to manipulate the domain of the functyion.
	 */
	@Nonnull
	default LCharToShortFunction fromChar(@Nonnull final LCharUnaryOperator before1) {
		Objects.requireNonNull(before1, Function4U.VALIDATION_MESSAGE_BEFORE1);
		return (final char v1) -> this.doApplyAsShort(before1.doApplyAsChar(v1));
	}

	/**
	 * Allows to manipulate the domain of the functyion.
	 */
	@Nonnull
	default <V1> LToShortFunction<V1> from(@Nonnull final LToCharFunction<? super V1> before1) {
		Objects.requireNonNull(before1, Function4U.VALIDATION_MESSAGE_BEFORE1);
		return (V1 v1) -> this.doApplyAsShort(before1.doApplyAsChar(v1));
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two functions together in a order. */
	@Nonnull
	default <V> LCharFunction<V> then(@Nonnull LShortFunction<? extends V> after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (char c) -> after.doApply(this.doApplyAsShort(c));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LCharToByteFunction thenToByte(@Nonnull LShortToByteFunction after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (char c) -> after.doApplyAsByte(this.doApplyAsShort(c));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LCharToShortFunction thenToShort(@Nonnull LShortUnaryOperator after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (char c) -> after.doApplyAsShort(this.doApplyAsShort(c));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LCharToIntFunction thenToInt(@Nonnull LShortToIntFunction after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (char c) -> after.doApplyAsInt(this.doApplyAsShort(c));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LCharToLongFunction thenToLong(@Nonnull LShortToLongFunction after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (char c) -> after.doApplyAsLong(this.doApplyAsShort(c));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LCharToFloatFunction thenToFloat(@Nonnull LShortToFloatFunction after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (char c) -> after.doApplyAsFloat(this.doApplyAsShort(c));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LCharToDoubleFunction thenToDouble(@Nonnull LShortToDoubleFunction after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (char c) -> after.doApplyAsDouble(this.doApplyAsShort(c));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LCharUnaryOperator thenToChar(@Nonnull LShortToCharFunction after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (char c) -> after.doApplyAsChar(this.doApplyAsShort(c));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LCharPredicate thenToBoolean(@Nonnull LShortPredicate after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (char c) -> after.doTest(this.doApplyAsShort(c));
	}

	// </editor-fold>

	// <editor-fold desc="variant conversions">

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default LCharToShortFunction nest() {
		return this;
	}

	/** Converts to throwing variant (RuntimeException). */
	@Nonnull
	default LCharToShortFunctionX<RuntimeException> nestX() {
		return this;
	}

	/** Dirty way, checked exception will propagate as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LCharToShortFunction shove() {
		return this;
	}

	/** Dirty way, checked exception will propagate as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LCharToShortFunctionX<RuntimeException> shoveX() {
		return this;
	}

	// </editor-fold>

	// <editor-fold desc="exception handling">

	/** Wraps with additional exception handling. */
	@Nonnull
	public static <X extends Exception, E extends Exception, Y extends RuntimeException> LCharToShortFunction wrapException(@Nonnull final LCharToShortFunction other, Class<E> exception, LShortSupplier supplier, ExceptionHandler<E, Y> handler) {
		return (char c) -> {
			try {
				return other.doApplyAsShort(c);
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
	default <E extends Exception, Y extends RuntimeException> LCharToShortFunction handle(Class<E> exception, ExceptionHandler<E, Y> handler) {
		Objects.requireNonNull(exception, Function4U.VALIDATION_MESSAGE_EXCEPTION);
		Objects.requireNonNull(handler, Function4U.VALIDATION_MESSAGE_HANDLER);

		return LCharToShortFunction.wrapException(this, exception, null, (ExceptionHandler) handler);
	}

	/** Wraps with exception handling that for any exception (including unchecked exception that might be different from X) will call handler function to determine the final exception. */
	@Nonnull
	default <Y extends RuntimeException> LCharToShortFunction handle(ExceptionHandler<Exception, Y> handler) {
		Objects.requireNonNull(handler, Function4U.VALIDATION_MESSAGE_HANDLER);

		return LCharToShortFunction.wrapException(this, Exception.class, null, (ExceptionHandler) handler);
	}

	/** Wraps with exception handling that for argument exception class will call supplier and return default value instead for propagating exception.  */
	@Nonnull
	default <E extends Exception, Y extends RuntimeException> LCharToShortFunction handle(Class<E> exception, LShortSupplier supplier) {
		Objects.requireNonNull(exception, Function4U.VALIDATION_MESSAGE_EXCEPTION);
		Objects.requireNonNull(supplier, Function4U.VALIDATION_MESSAGE_HANDLER);

		return LCharToShortFunction.wrapException(this, exception, supplier, null);
	}

	/** Wraps with exception handling that for any exception will call supplier and return default value instead for propagating exception.  */
	@Nonnull
	default <Y extends RuntimeException> LCharToShortFunction handle(LShortSupplier supplier) {
		Objects.requireNonNull(supplier, Function4U.VALIDATION_MESSAGE_HANDLER);

		return LCharToShortFunction.wrapException(this, Exception.class, supplier, null);
	}

	// </editor-fold>

}
