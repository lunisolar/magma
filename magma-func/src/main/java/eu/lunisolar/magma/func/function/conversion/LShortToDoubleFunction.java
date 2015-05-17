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
 * Non-throwing functional interface (lambda) LShortToDoubleFunction for Java 8.
 *
 * Type: function
 *
 * Domain (lvl: 1): short s
 *
 * Co-domain: none
 *
 * @see LShortToDoubleFunctionX
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LShortToDoubleFunction extends LShortToDoubleFunctionX<RuntimeException>, MetaFunction, PrimitiveCodomain<Object>, MetaInterface.NonThrowing { // NOSONAR

	public static final String DESCRIPTION = "LShortToDoubleFunction: double doApplyAsDouble(short s)";

	public double doApplyAsDouble(short s);

	default double nestingDoApplyAsDouble(short s) {
		return this.doApplyAsDouble(s);
	}

	default double shovingDoApplyAsDouble(short s) {
		return this.doApplyAsDouble(s);
	}

	/** Just to mirror the method: Ensures the result is not null */
	default double nonNullDoApplyAsDouble(short s) {
		return doApplyAsDouble(s);
	}

	/** Returns desxription of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LShortToDoubleFunction.DESCRIPTION;
	}

	/** Captures arguments but delays the evaluation. */
	default LDoubleSupplier capture(short s) {
		return () -> this.doApplyAsDouble(s);
	}

	public static LShortToDoubleFunction constant(double r) {
		return (s) -> r;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	public static LShortToDoubleFunction l(final @Nonnull LShortToDoubleFunction lambda) {
		Objects.requireNonNull(lambda, "Argument [lambda] cannot be null.");
		return lambda;
	}

	// <editor-fold desc="wrap">

	/** Wraps opposite (throwing/non-throwing) instance. */
	@Nonnull
	public static <X extends Exception> LShortToDoubleFunction wrap(final @Nonnull LShortToDoubleFunctionX<X> other) {
		return other::nestingDoApplyAsDouble;
	}

	// </editor-fold>

	// <editor-fold desc="compose (functional)">

	/**
	 * Allows to manipulate the domain of the functyion.
	 */
	@Nonnull
	default LShortToDoubleFunction fromShort(@Nonnull final LShortUnaryOperator before1) {
		Objects.requireNonNull(before1, Function4U.VALIDATION_MESSAGE_BEFORE1);
		return (final short v1) -> this.doApplyAsDouble(before1.doApplyAsShort(v1));
	}

	/**
	 * Allows to manipulate the domain of the functyion.
	 */
	@Nonnull
	default <V1> LToDoubleFunction<V1> from(@Nonnull final LToShortFunction<? super V1> before1) {
		Objects.requireNonNull(before1, Function4U.VALIDATION_MESSAGE_BEFORE1);
		return (V1 v1) -> this.doApplyAsDouble(before1.doApplyAsShort(v1));
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two functions together in a order. */
	@Nonnull
	default <V> LShortFunction<V> then(@Nonnull LDoubleFunction<? extends V> after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (short s) -> after.doApply(this.doApplyAsDouble(s));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LShortToByteFunction thenToByte(@Nonnull LDoubleToByteFunction after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (short s) -> after.doApplyAsByte(this.doApplyAsDouble(s));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LShortUnaryOperator thenToShort(@Nonnull LDoubleToShortFunction after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (short s) -> after.doApplyAsShort(this.doApplyAsDouble(s));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LShortToIntFunction thenToInt(@Nonnull LDoubleToIntFunction after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (short s) -> after.doApplyAsInt(this.doApplyAsDouble(s));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LShortToLongFunction thenToLong(@Nonnull LDoubleToLongFunction after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (short s) -> after.doApplyAsLong(this.doApplyAsDouble(s));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LShortToFloatFunction thenToFloat(@Nonnull LDoubleToFloatFunction after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (short s) -> after.doApplyAsFloat(this.doApplyAsDouble(s));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LShortToDoubleFunction thenToDouble(@Nonnull LDoubleUnaryOperator after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (short s) -> after.doApplyAsDouble(this.doApplyAsDouble(s));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LShortToCharFunction thenToChar(@Nonnull LDoubleToCharFunction after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (short s) -> after.doApplyAsChar(this.doApplyAsDouble(s));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LShortPredicate thenToBoolean(@Nonnull LDoublePredicate after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (short s) -> after.doTest(this.doApplyAsDouble(s));
	}

	// </editor-fold>

	// <editor-fold desc="variant conversions">

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default LShortToDoubleFunction nest() {
		return this;
	}

	/** Converts to throwing variant (RuntimeException). */
	@Nonnull
	default LShortToDoubleFunctionX<RuntimeException> nestX() {
		return this;
	}

	/** Dirty way, checked exception will propagate as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LShortToDoubleFunction shove() {
		return this;
	}

	/** Dirty way, checked exception will propagate as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LShortToDoubleFunctionX<RuntimeException> shoveX() {
		return this;
	}

	// </editor-fold>

	// <editor-fold desc="exception handling">

	/** Wraps with additional exception handling. */
	@Nonnull
	public static <X extends Exception, E extends Exception, Y extends RuntimeException> LShortToDoubleFunction wrapException(@Nonnull final LShortToDoubleFunction other, Class<E> exception, LDoubleSupplier supplier, ExceptionHandler<E, Y> handler) {
		return (short s) -> {
			try {
				return other.doApplyAsDouble(s);
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
	default <E extends Exception, Y extends RuntimeException> LShortToDoubleFunction handle(Class<E> exception, ExceptionHandler<E, Y> handler) {
		Objects.requireNonNull(exception, Function4U.VALIDATION_MESSAGE_EXCEPTION);
		Objects.requireNonNull(handler, Function4U.VALIDATION_MESSAGE_HANDLER);

		return LShortToDoubleFunction.wrapException(this, exception, null, (ExceptionHandler) handler);
	}

	/** Wraps with exception handling that for any exception (including unchecked exception that might be different from X) will call handler function to determine the final exception. */
	@Nonnull
	default <Y extends RuntimeException> LShortToDoubleFunction handle(ExceptionHandler<Exception, Y> handler) {
		Objects.requireNonNull(handler, Function4U.VALIDATION_MESSAGE_HANDLER);

		return LShortToDoubleFunction.wrapException(this, Exception.class, null, (ExceptionHandler) handler);
	}

	/** Wraps with exception handling that for argument exception class will call supplier and return default value instead for propagating exception.  */
	@Nonnull
	default <E extends Exception, Y extends RuntimeException> LShortToDoubleFunction handle(Class<E> exception, LDoubleSupplier supplier) {
		Objects.requireNonNull(exception, Function4U.VALIDATION_MESSAGE_EXCEPTION);
		Objects.requireNonNull(supplier, Function4U.VALIDATION_MESSAGE_HANDLER);

		return LShortToDoubleFunction.wrapException(this, exception, supplier, null);
	}

	/** Wraps with exception handling that for any exception will call supplier and return default value instead for propagating exception.  */
	@Nonnull
	default <Y extends RuntimeException> LShortToDoubleFunction handle(LDoubleSupplier supplier) {
		Objects.requireNonNull(supplier, Function4U.VALIDATION_MESSAGE_HANDLER);

		return LShortToDoubleFunction.wrapException(this, Exception.class, supplier, null);
	}

	// </editor-fold>

}
