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
 * Non-throwing functional interface (lambda) LLongSupplier for Java 8.
 *
 * Type: supplier
 *
 * Domain (lvl: 0): none
 *
 * Co-domain: none
 *
 * @see LLongSupplierX
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LLongSupplier extends java.util.function.LongSupplier, LLongSupplierX<RuntimeException>, MetaSupplier, PrimitiveCodomain<Object>, MetaInterface.NonThrowing {

	public static final String DESCRIPTION = "LLongSupplier: long getAsLong()";

	// Ovverriding methods can cause problems with inference.

	/** Returns desxription of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LLongSupplier.DESCRIPTION;
	}

	public static LLongSupplier of(long r) {
		return () -> r;
	}

	/** Just to mirror the method: Ensures the result is not null */
	default long nonNull() {
		return getAsLong();
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	public static LLongSupplier l(final @Nonnull LLongSupplier lambda) {
		Objects.requireNonNull(lambda, "Argument [lambda] cannot be null.");
		return lambda;
	}

	// <editor-fold desc="wrap">

	/** Wraps JRE instance. */
	@Nonnull
	public static LLongSupplier wrapStd(final java.util.function.LongSupplier other) {
		return other::getAsLong;
	}

	/** Wraps opposite (throwing/non-throwing) instance. */
	@Nonnull
	public static <X extends Exception> LLongSupplier wrap(final @Nonnull LLongSupplierX<X> other) {
		return () -> {
			try {
				return other.getAsLong();
			} catch (Exception e) {
				throw ExceptionHandler.handleWrapping(e);
			}
		};
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two suppliers together in a order. */
	@Nonnull
	default <V> LSupplier<V> then(@Nonnull LLongFunction<? extends V> after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return () -> after.apply(this.getAsLong());
	}

	/** Combines two suppliers together in a order. */
	@Nonnull
	default LByteSupplier thenToByte(@Nonnull LLongToByteFunction after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return () -> after.applyAsByte(this.getAsLong());
	}

	/** Combines two suppliers together in a order. */
	@Nonnull
	default LShortSupplier thenToShort(@Nonnull LLongToShortFunction after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return () -> after.applyAsShort(this.getAsLong());
	}

	/** Combines two suppliers together in a order. */
	@Nonnull
	default LIntSupplier thenToInt(@Nonnull LLongToIntFunction after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return () -> after.applyAsInt(this.getAsLong());
	}

	/** Combines two suppliers together in a order. */
	@Nonnull
	default LLongSupplier thenToLong(@Nonnull LLongUnaryOperator after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return () -> after.applyAsLong(this.getAsLong());
	}

	/** Combines two suppliers together in a order. */
	@Nonnull
	default LFloatSupplier thenToFloat(@Nonnull LLongToFloatFunction after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return () -> after.applyAsFloat(this.getAsLong());
	}

	/** Combines two suppliers together in a order. */
	@Nonnull
	default LDoubleSupplier thenToDouble(@Nonnull LLongToDoubleFunction after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return () -> after.applyAsDouble(this.getAsLong());
	}

	/** Combines two suppliers together in a order. */
	@Nonnull
	default LCharSupplier thenToChar(@Nonnull LLongToCharFunction after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return () -> after.applyAsChar(this.getAsLong());
	}

	/** Combines two suppliers together in a order. */
	@Nonnull
	default LBooleanSupplier thenToBoolean(@Nonnull LLongPredicate after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return () -> after.test(this.getAsLong());
	}

	// </editor-fold>

	// <editor-fold desc="variant conversions">

	/** Converts to JRE variant. */
	@Nonnull
	default java.util.function.LongSupplier std() {
		return this;
	}

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default LLongSupplier nonThrowing() {
		return this;
	}

	/** Converts to throwing variant (RuntimeException). */
	@Nonnull
	default LLongSupplierX<RuntimeException> uncheck() {
		return (LLongSupplierX) this;
	}

	/** Dirty way, checked exception will propagate as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LLongSupplier shove() {
		return this;
	}

	// </editor-fold>

	// <editor-fold desc="exception handling">

	/** Wraps with additional exception handling. */
	@Nonnull
	public static <X extends Exception, E extends Exception, Y extends RuntimeException> LLongSupplier wrapException(@Nonnull final LLongSupplier other, Class<E> exception, LLongSupplier supplier, ExceptionHandler<E, Y> handler) {
		return () -> {
			try {
				return other.getAsLong();
			} catch (Exception e) {
				try {
					if (supplier != null) {
						return supplier.getAsLong();
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
	default <E extends Exception, Y extends RuntimeException> LLongSupplier handle(Class<E> exception, ExceptionHandler<E, Y> handler) {
		Objects.requireNonNull(exception, Function4U.VALIDATION_MESSAGE_EXCEPTION);
		Objects.requireNonNull(handler, Function4U.VALIDATION_MESSAGE_HANDLER);

		return LLongSupplier.wrapException(this, exception, null, (ExceptionHandler) handler);
	}

	/** Wraps with exception handling that for any exception (including unchecked exception that might be different from X) will call handler function to determine the final exception. */
	@Nonnull
	default <Y extends RuntimeException> LLongSupplier handle(ExceptionHandler<Exception, Y> handler) {
		Objects.requireNonNull(handler, Function4U.VALIDATION_MESSAGE_HANDLER);

		return LLongSupplier.wrapException(this, Exception.class, null, (ExceptionHandler) handler);
	}

	/** Wraps with exception handling that for argument exception class will call supplier and return default value instead for propagating exception.  */
	@Nonnull
	default <E extends Exception, Y extends RuntimeException> LLongSupplier handle(Class<E> exception, LLongSupplier supplier) {
		Objects.requireNonNull(exception, Function4U.VALIDATION_MESSAGE_EXCEPTION);
		Objects.requireNonNull(supplier, Function4U.VALIDATION_MESSAGE_HANDLER);

		return LLongSupplier.wrapException(this, exception, supplier, null);
	}

	/** Wraps with exception handling that for any exception will call supplier and return default value instead for propagating exception.  */
	@Nonnull
	default <Y extends RuntimeException> LLongSupplier handle(LLongSupplier supplier) {
		Objects.requireNonNull(supplier, Function4U.VALIDATION_MESSAGE_HANDLER);

		return LLongSupplier.wrapException(this, Exception.class, supplier, null);
	}

	// </editor-fold>

}
