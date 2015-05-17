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

package eu.lunisolar.magma.func.supplier;

import javax.annotation.Nonnull; // NOSONAR
import javax.annotation.Nullable; // NOSONAR
import java.util.Objects; // NOSONAR
import eu.lunisolar.magma.basics.*; // NOSONAR
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
 * Throwing functional interface (lambda) LIntSupplierX for Java 8.
 *
 * Type: supplier
 *
 * Domain (lvl: 0): none
 *
 * Co-domain: none
 *
 * @see LIntSupplier
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LIntSupplierX<X extends Exception> extends MetaSupplier, PrimitiveCodomain<Object>, MetaInterface.Throwing<X> {

	public static final String DESCRIPTION = "LIntSupplierX: int getAsInt() throws X";

	public int getAsInt() throws X;

	/** Returns desxription of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LIntSupplierX.DESCRIPTION;
	}

	public static <X extends Exception> LIntSupplierX<X> of(int r) {
		return () -> r;
	}

	/** Just to mirror the method: Ensures the result is not null */
	default int nonNull() throws X {
		return getAsInt();
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	public static <X extends Exception> LIntSupplierX<X> lX(final @Nonnull LIntSupplierX<X> lambda) {
		Objects.requireNonNull(lambda, "Argument [lambda] cannot be null.");
		return lambda;
	}

	// <editor-fold desc="wrap">

	/** Wraps JRE instance. */
	@Nonnull
	public static <X extends Exception> LIntSupplierX<X> wrapStd(final java.util.function.IntSupplier other) {
		return other::getAsInt;
	}

	/** Wraps opposite (throwing/non-throwing) instance. */
	@Nonnull
	public static <X extends Exception> LIntSupplierX<X> wrapX(final @Nonnull LIntSupplier other) {
		return other::getAsInt;
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two suppliers together in a order. */
	@Nonnull
	default <V> LSupplierX<V, X> then(@Nonnull LIntFunctionX<? extends V, X> after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return () -> after.apply(this.getAsInt());
	}

	/** Combines two suppliers together in a order. */
	@Nonnull
	default LByteSupplierX<X> thenToByte(@Nonnull LIntToByteFunctionX<X> after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return () -> after.applyAsByte(this.getAsInt());
	}

	/** Combines two suppliers together in a order. */
	@Nonnull
	default LShortSupplierX<X> thenToShort(@Nonnull LIntToShortFunctionX<X> after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return () -> after.applyAsShort(this.getAsInt());
	}

	/** Combines two suppliers together in a order. */
	@Nonnull
	default LIntSupplierX<X> thenToInt(@Nonnull LIntUnaryOperatorX<X> after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return () -> after.applyAsInt(this.getAsInt());
	}

	/** Combines two suppliers together in a order. */
	@Nonnull
	default LLongSupplierX<X> thenToLong(@Nonnull LIntToLongFunctionX<X> after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return () -> after.applyAsLong(this.getAsInt());
	}

	/** Combines two suppliers together in a order. */
	@Nonnull
	default LFloatSupplierX<X> thenToFloat(@Nonnull LIntToFloatFunctionX<X> after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return () -> after.applyAsFloat(this.getAsInt());
	}

	/** Combines two suppliers together in a order. */
	@Nonnull
	default LDoubleSupplierX<X> thenToDouble(@Nonnull LIntToDoubleFunctionX<X> after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return () -> after.applyAsDouble(this.getAsInt());
	}

	/** Combines two suppliers together in a order. */
	@Nonnull
	default LCharSupplierX<X> thenToChar(@Nonnull LIntToCharFunctionX<X> after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return () -> after.applyAsChar(this.getAsInt());
	}

	/** Combines two suppliers together in a order. */
	@Nonnull
	default LBooleanSupplierX<X> thenToBoolean(@Nonnull LIntPredicateX<X> after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return () -> after.test(this.getAsInt());
	}

	// </editor-fold>

	// <editor-fold desc="variant conversions">

	/** Converts to JRE variant. */
	@Nonnull
	default java.util.function.IntSupplier std() {
		return LIntSupplier.wrap(this)::getAsInt;
	}

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default LIntSupplier nonThrowing() {
		return LIntSupplier.wrap(this);
	}

	/** Converts to throwing variant (RuntimeException). */
	@Nonnull
	default LIntSupplierX<RuntimeException> uncheck() {
		return (LIntSupplierX) this;
	}

	/** Dirty way, checked exception will propagate as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LIntSupplier shove() {
		LIntSupplierX<RuntimeException> exceptionCast = (LIntSupplierX<RuntimeException>) this;
		return exceptionCast::getAsInt;
	}

	// </editor-fold>

	// <editor-fold desc="exception handling">

	/** Wraps with additional exception handling. */
	@Nonnull
	public static <X extends Exception, E extends Exception, Y extends Exception> LIntSupplierX<Y> wrapException(@Nonnull final LIntSupplierX<X> other, Class<E> exception, LIntSupplierX<X> supplier, ExceptionHandler<E, Y> handler) {
		return () -> {
			try {
				return other.getAsInt();
			} catch (Exception e) {
				try {
					if (supplier != null) {
						return supplier.getAsInt();
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
	default <E extends Exception, Y extends Exception> LIntSupplierX<Y> handleX(Class<E> exception, ExceptionHandler<E, Y> handler) {
		Objects.requireNonNull(exception, Function4U.VALIDATION_MESSAGE_EXCEPTION);
		Objects.requireNonNull(handler, Function4U.VALIDATION_MESSAGE_HANDLER);

		return LIntSupplierX.wrapException(this, exception, null, (ExceptionHandler) handler);
	}

	/** Wraps with exception handling that for any exception (including unchecked exception that might be different from X) will call handler function to determine the final exception. */
	@Nonnull
	default <Y extends Exception> LIntSupplierX<Y> handleX(ExceptionHandler<Exception, Y> handler) {
		Objects.requireNonNull(handler, Function4U.VALIDATION_MESSAGE_HANDLER);

		return LIntSupplierX.wrapException(this, Exception.class, null, (ExceptionHandler) handler);
	}

	/** Wraps with exception handling that for argument exception class will call supplier and return default value instead for propagating exception.  */
	@Nonnull
	default <E extends Exception, Y extends Exception> LIntSupplierX<Y> handleX(Class<E> exception, LIntSupplierX<X> supplier) {
		Objects.requireNonNull(exception, Function4U.VALIDATION_MESSAGE_EXCEPTION);
		Objects.requireNonNull(supplier, Function4U.VALIDATION_MESSAGE_HANDLER);

		return LIntSupplierX.wrapException(this, exception, supplier, null);
	}

	/** Wraps with exception handling that for any exception will call supplier and return default value instead for propagating exception.  */
	@Nonnull
	default <Y extends Exception> LIntSupplierX<Y> handleX(LIntSupplierX<X> supplier) {
		Objects.requireNonNull(supplier, Function4U.VALIDATION_MESSAGE_HANDLER);

		return LIntSupplierX.wrapException(this, Exception.class, supplier, null);
	}

	// </editor-fold>

}