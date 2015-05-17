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
 * Non-throwing functional interface (lambda) LDoubleToLongFunction for Java 8.
 *
 * Type: function
 *
 * Domain (lvl: 1): double d
 *
 * Co-domain: none
 *
 * @see LDoubleToLongFunctionX
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LDoubleToLongFunction extends LDoubleToLongFunctionX<RuntimeException>, MetaFunction, PrimitiveCodomain<Object>, MetaInterface.NonThrowing { // NOSONAR

	public static final String DESCRIPTION = "LDoubleToLongFunction: long doApplyAsLong(double d)";

	@Override
	@Deprecated
	// calling this method via LDoubleToLongFunction interface should be discouraged.
	default long applyAsLong(double d) {
		return this.nestingDoApplyAsLong(d);
	}

	public long doApplyAsLong(double d);

	default long nestingDoApplyAsLong(double d) {
		return this.doApplyAsLong(d);
	}

	default long shovingDoApplyAsLong(double d) {
		return this.doApplyAsLong(d);
	}

	/** Just to mirror the method: Ensures the result is not null */
	default long nonNullDoApplyAsLong(double d) {
		return doApplyAsLong(d);
	}

	/** Returns desxription of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LDoubleToLongFunction.DESCRIPTION;
	}

	/** Captures arguments but delays the evaluation. */
	default LLongSupplier capture(double d) {
		return () -> this.doApplyAsLong(d);
	}

	public static LDoubleToLongFunction constant(long r) {
		return (d) -> r;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	public static LDoubleToLongFunction l(final @Nonnull LDoubleToLongFunction lambda) {
		Objects.requireNonNull(lambda, "Argument [lambda] cannot be null.");
		return lambda;
	}

	// <editor-fold desc="wrap">

	/** Wraps JRE instance. */
	@Nonnull
	public static LDoubleToLongFunction wrap(final java.util.function.DoubleToLongFunction other) {
		return other::applyAsLong;
	}

	/** Wraps opposite (throwing/non-throwing) instance. */
	@Nonnull
	public static <X extends Exception> LDoubleToLongFunction wrap(final @Nonnull LDoubleToLongFunctionX<X> other) {
		return other::nestingDoApplyAsLong;
	}

	// </editor-fold>

	// <editor-fold desc="compose (functional)">

	/**
	 * Allows to manipulate the domain of the functyion.
	 */
	@Nonnull
	default LDoubleToLongFunction fromDouble(@Nonnull final LDoubleUnaryOperator before1) {
		Objects.requireNonNull(before1, Function4U.VALIDATION_MESSAGE_BEFORE1);
		return (final double v1) -> this.doApplyAsLong(before1.doApplyAsDouble(v1));
	}

	/**
	 * Allows to manipulate the domain of the functyion.
	 */
	@Nonnull
	default <V1> LToLongFunction<V1> from(@Nonnull final LToDoubleFunction<? super V1> before1) {
		Objects.requireNonNull(before1, Function4U.VALIDATION_MESSAGE_BEFORE1);
		return (V1 v1) -> this.doApplyAsLong(before1.doApplyAsDouble(v1));
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two functions together in a order. */
	@Nonnull
	default <V> LDoubleFunction<V> then(@Nonnull LLongFunction<? extends V> after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (double d) -> after.doApply(this.doApplyAsLong(d));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LDoubleToByteFunction thenToByte(@Nonnull LLongToByteFunction after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (double d) -> after.doApplyAsByte(this.doApplyAsLong(d));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LDoubleToShortFunction thenToShort(@Nonnull LLongToShortFunction after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (double d) -> after.doApplyAsShort(this.doApplyAsLong(d));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LDoubleToIntFunction thenToInt(@Nonnull LLongToIntFunction after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (double d) -> after.doApplyAsInt(this.doApplyAsLong(d));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LDoubleToLongFunction thenToLong(@Nonnull LLongUnaryOperator after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (double d) -> after.doApplyAsLong(this.doApplyAsLong(d));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LDoubleToFloatFunction thenToFloat(@Nonnull LLongToFloatFunction after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (double d) -> after.doApplyAsFloat(this.doApplyAsLong(d));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LDoubleUnaryOperator thenToDouble(@Nonnull LLongToDoubleFunction after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (double d) -> after.doApplyAsDouble(this.doApplyAsLong(d));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LDoubleToCharFunction thenToChar(@Nonnull LLongToCharFunction after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (double d) -> after.doApplyAsChar(this.doApplyAsLong(d));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LDoublePredicate thenToBoolean(@Nonnull LLongPredicate after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (double d) -> after.doTest(this.doApplyAsLong(d));
	}

	// </editor-fold>

	// <editor-fold desc="variant conversions">

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default LDoubleToLongFunction nest() {
		return this;
	}

	/** Converts to throwing variant (RuntimeException). */
	@Nonnull
	default LDoubleToLongFunctionX<RuntimeException> nestX() {
		return this;
	}

	/** Dirty way, checked exception will propagate as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LDoubleToLongFunction shove() {
		return this;
	}

	/** Dirty way, checked exception will propagate as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LDoubleToLongFunctionX<RuntimeException> shoveX() {
		return this;
	}

	// </editor-fold>

	// <editor-fold desc="exception handling">

	/** Wraps with additional exception handling. */
	@Nonnull
	public static <X extends Exception, E extends Exception, Y extends RuntimeException> LDoubleToLongFunction wrapException(@Nonnull final LDoubleToLongFunction other, Class<E> exception, LLongSupplier supplier, ExceptionHandler<E, Y> handler) {
		return (double d) -> {
			try {
				return other.doApplyAsLong(d);
			} catch (Exception e) {
				try {
					if (supplier != null) {
						return supplier.doGetAsLong();
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
	default <E extends Exception, Y extends RuntimeException> LDoubleToLongFunction handle(Class<E> exception, ExceptionHandler<E, Y> handler) {
		Objects.requireNonNull(exception, Function4U.VALIDATION_MESSAGE_EXCEPTION);
		Objects.requireNonNull(handler, Function4U.VALIDATION_MESSAGE_HANDLER);

		return LDoubleToLongFunction.wrapException(this, exception, null, (ExceptionHandler) handler);
	}

	/** Wraps with exception handling that for any exception (including unchecked exception that might be different from X) will call handler function to determine the final exception. */
	@Nonnull
	default <Y extends RuntimeException> LDoubleToLongFunction handle(ExceptionHandler<Exception, Y> handler) {
		Objects.requireNonNull(handler, Function4U.VALIDATION_MESSAGE_HANDLER);

		return LDoubleToLongFunction.wrapException(this, Exception.class, null, (ExceptionHandler) handler);
	}

	/** Wraps with exception handling that for argument exception class will call supplier and return default value instead for propagating exception.  */
	@Nonnull
	default <E extends Exception, Y extends RuntimeException> LDoubleToLongFunction handle(Class<E> exception, LLongSupplier supplier) {
		Objects.requireNonNull(exception, Function4U.VALIDATION_MESSAGE_EXCEPTION);
		Objects.requireNonNull(supplier, Function4U.VALIDATION_MESSAGE_HANDLER);

		return LDoubleToLongFunction.wrapException(this, exception, supplier, null);
	}

	/** Wraps with exception handling that for any exception will call supplier and return default value instead for propagating exception.  */
	@Nonnull
	default <Y extends RuntimeException> LDoubleToLongFunction handle(LLongSupplier supplier) {
		Objects.requireNonNull(supplier, Function4U.VALIDATION_MESSAGE_HANDLER);

		return LDoubleToLongFunction.wrapException(this, Exception.class, supplier, null);
	}

	// </editor-fold>

}
