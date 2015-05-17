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
 * Throwing functional interface (lambda) LByteToDoubleFunctionX for Java 8.
 *
 * Type: function
 *
 * Domain (lvl: 1): byte b
 *
 * Co-domain: none
 *
 * @see LByteToDoubleFunction
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LByteToDoubleFunctionX<X extends Exception> extends MetaFunction, PrimitiveCodomain<Object>, MetaInterface.Throwing<X> { // NOSONAR

	public static final String DESCRIPTION = "LByteToDoubleFunctionX: double doApplyAsDouble(byte b) throws X";

	public double doApplyAsDouble(byte b) throws X;

	default double nestingDoApplyAsDouble(byte b) {
		try {
			return this.doApplyAsDouble(b);
		} catch (RuntimeException e) {
			throw e;
		} catch (Exception e) {
			throw new NestedException(e);
		}
	}

	default double shovingDoApplyAsDouble(byte b) {
		return ((LByteToDoubleFunctionX<RuntimeException>) this).doApplyAsDouble(b);
	}

	/** Just to mirror the method: Ensures the result is not null */
	default double nonNullDoApplyAsDouble(byte b) throws X {
		return doApplyAsDouble(b);
	}

	/** Returns desxription of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LByteToDoubleFunctionX.DESCRIPTION;
	}

	/** Captures arguments but delays the evaluation. */
	default LDoubleSupplierX<X> capture(byte b) {
		return () -> this.doApplyAsDouble(b);
	}

	public static <X extends Exception> LByteToDoubleFunctionX<X> constant(double r) {
		return (b) -> r;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	public static <X extends Exception> LByteToDoubleFunctionX<X> lX(final @Nonnull LByteToDoubleFunctionX<X> lambda) {
		Objects.requireNonNull(lambda, "Argument [lambda] cannot be null.");
		return lambda;
	}

	// <editor-fold desc="wrap">

	/** Wraps opposite (throwing/non-throwing) instance. */
	@Nonnull
	public static <X extends Exception> LByteToDoubleFunctionX<X> wrapX(final @Nonnull LByteToDoubleFunction other) {
		return (LByteToDoubleFunctionX) other;
	}

	// </editor-fold>

	// <editor-fold desc="compose (functional)">

	/**
	 * Allows to manipulate the domain of the functyion.
	 */
	@Nonnull
	default LByteToDoubleFunctionX<X> fromByte(@Nonnull final LByteUnaryOperatorX<X> before1) {
		Objects.requireNonNull(before1, Function4U.VALIDATION_MESSAGE_BEFORE1);
		return (final byte v1) -> this.doApplyAsDouble(before1.doApplyAsByte(v1));
	}

	/**
	 * Allows to manipulate the domain of the functyion.
	 */
	@Nonnull
	default <V1> LToDoubleFunctionX<V1, X> from(@Nonnull final LToByteFunctionX<? super V1, X> before1) {
		Objects.requireNonNull(before1, Function4U.VALIDATION_MESSAGE_BEFORE1);
		return (V1 v1) -> this.doApplyAsDouble(before1.doApplyAsByte(v1));
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two functions together in a order. */
	@Nonnull
	default <V> LByteFunctionX<V, X> then(@Nonnull LDoubleFunctionX<? extends V, X> after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (byte b) -> after.doApply(this.doApplyAsDouble(b));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LByteUnaryOperatorX<X> thenToByte(@Nonnull LDoubleToByteFunctionX<X> after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (byte b) -> after.doApplyAsByte(this.doApplyAsDouble(b));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LByteToShortFunctionX<X> thenToShort(@Nonnull LDoubleToShortFunctionX<X> after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (byte b) -> after.doApplyAsShort(this.doApplyAsDouble(b));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LByteToIntFunctionX<X> thenToInt(@Nonnull LDoubleToIntFunctionX<X> after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (byte b) -> after.doApplyAsInt(this.doApplyAsDouble(b));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LByteToLongFunctionX<X> thenToLong(@Nonnull LDoubleToLongFunctionX<X> after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (byte b) -> after.doApplyAsLong(this.doApplyAsDouble(b));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LByteToFloatFunctionX<X> thenToFloat(@Nonnull LDoubleToFloatFunctionX<X> after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (byte b) -> after.doApplyAsFloat(this.doApplyAsDouble(b));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LByteToDoubleFunctionX<X> thenToDouble(@Nonnull LDoubleUnaryOperatorX<X> after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (byte b) -> after.doApplyAsDouble(this.doApplyAsDouble(b));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LByteToCharFunctionX<X> thenToChar(@Nonnull LDoubleToCharFunctionX<X> after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (byte b) -> after.doApplyAsChar(this.doApplyAsDouble(b));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LBytePredicateX<X> thenToBoolean(@Nonnull LDoublePredicateX<X> after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (byte b) -> after.doTest(this.doApplyAsDouble(b));
	}

	// </editor-fold>

	// <editor-fold desc="variant conversions">

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default LByteToDoubleFunction nest() {
		return this::nestingDoApplyAsDouble;
	}

	/** Converts to throwing variant (RuntimeException). */
	@Nonnull
	default LByteToDoubleFunctionX<RuntimeException> nestX() {
		return this::nestingDoApplyAsDouble;
	}

	/** Dirty way, checked exception will propagate as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LByteToDoubleFunction shove() {
		return this::shovingDoApplyAsDouble;
	}

	/** Dirty way, checked exception will propagate as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LByteToDoubleFunctionX<RuntimeException> shoveX() {
		return this::shovingDoApplyAsDouble;
	}

	// </editor-fold>

	// <editor-fold desc="exception handling">

	/** Wraps with additional exception handling. */
	@Nonnull
	public static <X extends Exception, E extends Exception, Y extends Exception> LByteToDoubleFunctionX<Y> wrapException(@Nonnull final LByteToDoubleFunctionX<X> other, Class<E> exception, LDoubleSupplierX<X> supplier, ExceptionHandler<E, Y> handler) {
		return (byte b) -> {
			try {
				return other.doApplyAsDouble(b);
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
	default <E extends Exception, Y extends Exception> LByteToDoubleFunctionX<Y> handleX(Class<E> exception, ExceptionHandler<E, Y> handler) {
		Objects.requireNonNull(exception, Function4U.VALIDATION_MESSAGE_EXCEPTION);
		Objects.requireNonNull(handler, Function4U.VALIDATION_MESSAGE_HANDLER);

		return LByteToDoubleFunctionX.wrapException(this, exception, null, (ExceptionHandler) handler);
	}

	/** Wraps with exception handling that for any exception (including unchecked exception that might be different from X) will call handler function to determine the final exception. */
	@Nonnull
	default <Y extends Exception> LByteToDoubleFunctionX<Y> handleX(ExceptionHandler<Exception, Y> handler) {
		Objects.requireNonNull(handler, Function4U.VALIDATION_MESSAGE_HANDLER);

		return LByteToDoubleFunctionX.wrapException(this, Exception.class, null, (ExceptionHandler) handler);
	}

	/** Wraps with exception handling that for argument exception class will call supplier and return default value instead for propagating exception.  */
	@Nonnull
	default <E extends Exception, Y extends Exception> LByteToDoubleFunctionX<Y> handleX(Class<E> exception, LDoubleSupplierX<X> supplier) {
		Objects.requireNonNull(exception, Function4U.VALIDATION_MESSAGE_EXCEPTION);
		Objects.requireNonNull(supplier, Function4U.VALIDATION_MESSAGE_HANDLER);

		return LByteToDoubleFunctionX.wrapException(this, exception, supplier, null);
	}

	/** Wraps with exception handling that for any exception will call supplier and return default value instead for propagating exception.  */
	@Nonnull
	default <Y extends Exception> LByteToDoubleFunctionX<Y> handleX(LDoubleSupplierX<X> supplier) {
		Objects.requireNonNull(supplier, Function4U.VALIDATION_MESSAGE_HANDLER);

		return LByteToDoubleFunctionX.wrapException(this, Exception.class, supplier, null);
	}

	// </editor-fold>

}
