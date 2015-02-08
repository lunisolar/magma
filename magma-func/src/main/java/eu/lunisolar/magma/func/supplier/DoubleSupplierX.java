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
 * Foobar is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with Foobar.  If not, see <http://www.gnu.org/licenses/>.
 */

package eu.lunisolar.magma.func.supplier;

import javax.annotation.Nonnull; // NOSONAR
import javax.annotation.Nullable; // NOSONAR
import java.util.Objects; // NOSONAR
import eu.lunisolar.magma.basics.*; // NOSONAR
import eu.lunisolar.magma.basics.builder.*; // NOSONAR
import eu.lunisolar.magma.basics.meta.*; // NOSONAR
import eu.lunisolar.magma.basics.meta.domains.*; // NOSONAR
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
 *
 * @see {@link eu.lunisolar.magma.func.supplier.DoubleSupplier}
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface DoubleSupplierX<X extends Exception> extends MetaSupplier, PrimitiveCodomain<DoubleSupplierX<X>>, MetaThrowingInterface<X> {

	public static final String DESCRIPTION = "DoubleSupplierX: double getAsDouble() throws X";

	public double getAsDouble() throws X;

	/** Returns desxription of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return DoubleSupplierX.DESCRIPTION;
	}

	/** Just to mirror the method: Ensures the result is not null */
	default double nonNull() throws X {
		return getAsDouble();
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	public static <X extends Exception> DoubleSupplierX<X> lX(final @Nonnull DoubleSupplierX<X> lambda) {
		Objects.requireNonNull(lambda, "Argument [lambda] cannot be null.");
		return lambda;
	}

	// <editor-fold desc="wrap">

	/** Wraps JRE instance. */
	@Nonnull
	public static <X extends Exception> DoubleSupplierX<X> std(final java.util.function.DoubleSupplier other) {
		return other::getAsDouble;
	}

	/** Wraps opposite (throwing/non-throwing) instance. */
	@Nonnull
	public static <X extends Exception> DoubleSupplierX<X> wrapX(final @Nonnull DoubleSupplier other) {
		return other::getAsDouble;
	}

	/** Wraps with additional exception handling. */
	@Nonnull
	public static <X extends Exception, Y extends Exception> DoubleSupplierX<Y> wrapException(@Nonnull final DoubleSupplierX<X> other, Class<? extends Exception> exception, ExceptionHandler<Exception, Y> rethrower) {
		return () -> {
			try {
				return other.getAsDouble();
			} catch (Exception e) {
				throw ExceptionHandler.handle(exception, rethrower, e);
			}
		};
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two suppliers together in a order. */
	@Nonnull
	default <V> SupplierX<V, X> then(@Nonnull DoubleFunctionX<? extends V, X> after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return () -> after.apply(this.getAsDouble());
	}

	/** Combines two suppliers together in a order. */
	@Nonnull
	default ByteSupplierX<X> thenToByte(@Nonnull DoubleToByteFunctionX<X> after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return () -> after.applyAsByte(this.getAsDouble());
	}

	/** Combines two suppliers together in a order. */
	@Nonnull
	default ShortSupplierX<X> thenToShort(@Nonnull DoubleToShortFunctionX<X> after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return () -> after.applyAsShort(this.getAsDouble());
	}

	/** Combines two suppliers together in a order. */
	@Nonnull
	default IntSupplierX<X> thenToInt(@Nonnull DoubleToIntFunctionX<X> after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return () -> after.applyAsInt(this.getAsDouble());
	}

	/** Combines two suppliers together in a order. */
	@Nonnull
	default LongSupplierX<X> thenToLong(@Nonnull DoubleToLongFunctionX<X> after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return () -> after.applyAsLong(this.getAsDouble());
	}

	/** Combines two suppliers together in a order. */
	@Nonnull
	default FloatSupplierX<X> thenToFloat(@Nonnull DoubleToFloatFunctionX<X> after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return () -> after.applyAsFloat(this.getAsDouble());
	}

	/** Combines two suppliers together in a order. */
	@Nonnull
	default DoubleSupplierX<X> thenToDouble(@Nonnull DoubleUnaryOperatorX<X> after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return () -> after.applyAsDouble(this.getAsDouble());
	}

	/** Combines two suppliers together in a order. */
	@Nonnull
	default CharSupplierX<X> thenToChar(@Nonnull DoubleToCharFunctionX<X> after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return () -> after.applyAsChar(this.getAsDouble());
	}

	/** Combines two suppliers together in a order. */
	@Nonnull
	default BooleanSupplierX<X> thenToBoolean(@Nonnull DoublePredicateX<X> after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return () -> after.test(this.getAsDouble());
	}

	// </editor-fold>

	// <editor-fold desc="variant conversions">

	/** Converts to JRE variant. */
	@Nonnull
	default java.util.function.DoubleSupplier std() {
		return DoubleSupplier.wrap(this)::getAsDouble;
	}

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default DoubleSupplier nonThrowing() {
		return DoubleSupplier.wrap(this);
	}

	/** Converts to throwing variant (RuntimeException). */
	@Nonnull
	default DoubleSupplierX<RuntimeException> uncheck() {
		return nonThrowing()::getAsDouble;
	}

	// </editor-fold>

	// <editor-fold desc="exception handling">

	/** Wraps with exception handling that for argument exception class will call function to determine the final exception. */
	@Nonnull
	default <Y extends Exception> DoubleSupplierX<Y> handle(Class<? extends Exception> exception, ExceptionHandler<? super X, Y> handler) {
		Objects.requireNonNull(exception, Function4U.VALIDATION_MESSAGE_EXCEPTION);
		Objects.requireNonNull(handler, Function4U.VALIDATION_MESSAGE_HANDLER);

		return DoubleSupplierX.wrapException(this, exception, (ExceptionHandler) handler);
	}

	/** Wraps with exception handling that for argument exception class will call function to determine the final exception. */
	@Nonnull
	default <Y extends Exception> DoubleSupplierX<Y> handle(ExceptionHandler<? super X, Y> handler) {
		Objects.requireNonNull(handler, Function4U.VALIDATION_MESSAGE_HANDLER);

		return DoubleSupplierX.wrapException(this, Exception.class, (ExceptionHandler) handler);
	}

	// </editor-fold>

}
