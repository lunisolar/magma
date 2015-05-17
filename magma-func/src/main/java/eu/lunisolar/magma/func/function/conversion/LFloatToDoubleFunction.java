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
 * Non-throwing functional interface (lambda) LFloatToDoubleFunction for Java 8.
 *
 * Type: function
 *
 * Domain (lvl: 1): float f
 *
 * Co-domain: none
 *
 * @see LFloatToDoubleFunctionX
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LFloatToDoubleFunction extends LFloatToDoubleFunctionX<RuntimeException>, MetaFunction, PrimitiveCodomain<Object>, MetaInterface.NonThrowing { // NOSONAR

	public static final String DESCRIPTION = "LFloatToDoubleFunction: double doApplyAsDouble(float f)";

	public double doApplyAsDouble(float f);

	default double nestingDoApplyAsDouble(float f) {
		return this.doApplyAsDouble(f);
	}

	default double shovingDoApplyAsDouble(float f) {
		return this.doApplyAsDouble(f);
	}

	/** Just to mirror the method: Ensures the result is not null */
	default double nonNullDoApplyAsDouble(float f) {
		return doApplyAsDouble(f);
	}

	/** Returns desxription of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LFloatToDoubleFunction.DESCRIPTION;
	}

	/** Captures arguments but delays the evaluation. */
	default LDoubleSupplier capture(float f) {
		return () -> this.doApplyAsDouble(f);
	}

	public static LFloatToDoubleFunction constant(double r) {
		return (f) -> r;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	public static LFloatToDoubleFunction l(final @Nonnull LFloatToDoubleFunction lambda) {
		Objects.requireNonNull(lambda, "Argument [lambda] cannot be null.");
		return lambda;
	}

	// <editor-fold desc="wrap">

	/** Wraps opposite (throwing/non-throwing) instance. */
	@Nonnull
	public static <X extends Exception> LFloatToDoubleFunction wrap(final @Nonnull LFloatToDoubleFunctionX<X> other) {
		return other::nestingDoApplyAsDouble;
	}

	// </editor-fold>

	// <editor-fold desc="compose (functional)">

	/**
	 * Allows to manipulate the domain of the functyion.
	 */
	@Nonnull
	default LFloatToDoubleFunction fromFloat(@Nonnull final LFloatUnaryOperator before1) {
		Objects.requireNonNull(before1, Function4U.VALIDATION_MESSAGE_BEFORE1);
		return (final float v1) -> this.doApplyAsDouble(before1.doApplyAsFloat(v1));
	}

	/**
	 * Allows to manipulate the domain of the functyion.
	 */
	@Nonnull
	default <V1> LToDoubleFunction<V1> from(@Nonnull final LToFloatFunction<? super V1> before1) {
		Objects.requireNonNull(before1, Function4U.VALIDATION_MESSAGE_BEFORE1);
		return (V1 v1) -> this.doApplyAsDouble(before1.doApplyAsFloat(v1));
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two functions together in a order. */
	@Nonnull
	default <V> LFloatFunction<V> then(@Nonnull LDoubleFunction<? extends V> after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (float f) -> after.doApply(this.doApplyAsDouble(f));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LFloatToByteFunction thenToByte(@Nonnull LDoubleToByteFunction after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (float f) -> after.doApplyAsByte(this.doApplyAsDouble(f));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LFloatToShortFunction thenToShort(@Nonnull LDoubleToShortFunction after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (float f) -> after.doApplyAsShort(this.doApplyAsDouble(f));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LFloatToIntFunction thenToInt(@Nonnull LDoubleToIntFunction after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (float f) -> after.doApplyAsInt(this.doApplyAsDouble(f));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LFloatToLongFunction thenToLong(@Nonnull LDoubleToLongFunction after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (float f) -> after.doApplyAsLong(this.doApplyAsDouble(f));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LFloatUnaryOperator thenToFloat(@Nonnull LDoubleToFloatFunction after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (float f) -> after.doApplyAsFloat(this.doApplyAsDouble(f));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LFloatToDoubleFunction thenToDouble(@Nonnull LDoubleUnaryOperator after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (float f) -> after.doApplyAsDouble(this.doApplyAsDouble(f));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LFloatToCharFunction thenToChar(@Nonnull LDoubleToCharFunction after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (float f) -> after.doApplyAsChar(this.doApplyAsDouble(f));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LFloatPredicate thenToBoolean(@Nonnull LDoublePredicate after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (float f) -> after.doTest(this.doApplyAsDouble(f));
	}

	// </editor-fold>

	// <editor-fold desc="variant conversions">

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default LFloatToDoubleFunction nest() {
		return this;
	}

	/** Converts to throwing variant (RuntimeException). */
	@Nonnull
	default LFloatToDoubleFunctionX<RuntimeException> nestX() {
		return this;
	}

	/** Dirty way, checked exception will propagate as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LFloatToDoubleFunction shove() {
		return this;
	}

	/** Dirty way, checked exception will propagate as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LFloatToDoubleFunctionX<RuntimeException> shoveX() {
		return this;
	}

	// </editor-fold>

	// <editor-fold desc="exception handling">

	/** Wraps with additional exception handling. */
	@Nonnull
	public static <X extends Exception, E extends Exception, Y extends RuntimeException> LFloatToDoubleFunction wrapException(@Nonnull final LFloatToDoubleFunction other, Class<E> exception, LDoubleSupplier supplier, ExceptionHandler<E, Y> handler) {
		return (float f) -> {
			try {
				return other.doApplyAsDouble(f);
			} catch (Exception e) {
				try {
					if (supplier != null) {
						return supplier.doGetAsDouble();
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
	default <E extends Exception, Y extends RuntimeException> LFloatToDoubleFunction handle(Class<E> exception, ExceptionHandler<E, Y> handler) {
		Objects.requireNonNull(exception, Function4U.VALIDATION_MESSAGE_EXCEPTION);
		Objects.requireNonNull(handler, Function4U.VALIDATION_MESSAGE_HANDLER);

		return LFloatToDoubleFunction.wrapException(this, exception, null, (ExceptionHandler) handler);
	}

	/** Wraps with exception handling that for any exception (including unchecked exception that might be different from X) will call handler function to determine the final exception. */
	@Nonnull
	default <Y extends RuntimeException> LFloatToDoubleFunction handle(ExceptionHandler<Exception, Y> handler) {
		Objects.requireNonNull(handler, Function4U.VALIDATION_MESSAGE_HANDLER);

		return LFloatToDoubleFunction.wrapException(this, Exception.class, null, (ExceptionHandler) handler);
	}

	/** Wraps with exception handling that for argument exception class will call supplier and return default value instead for propagating exception.  */
	@Nonnull
	default <E extends Exception, Y extends RuntimeException> LFloatToDoubleFunction handle(Class<E> exception, LDoubleSupplier supplier) {
		Objects.requireNonNull(exception, Function4U.VALIDATION_MESSAGE_EXCEPTION);
		Objects.requireNonNull(supplier, Function4U.VALIDATION_MESSAGE_HANDLER);

		return LFloatToDoubleFunction.wrapException(this, exception, supplier, null);
	}

	/** Wraps with exception handling that for any exception will call supplier and return default value instead for propagating exception.  */
	@Nonnull
	default <Y extends RuntimeException> LFloatToDoubleFunction handle(LDoubleSupplier supplier) {
		Objects.requireNonNull(supplier, Function4U.VALIDATION_MESSAGE_HANDLER);

		return LFloatToDoubleFunction.wrapException(this, Exception.class, supplier, null);
	}

	// </editor-fold>

}
