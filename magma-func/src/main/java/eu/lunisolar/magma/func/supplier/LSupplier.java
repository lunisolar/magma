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
 * Non-throwing functional interface (lambda) LSupplier for Java 8.
 *
 * Type: supplier
 *
 * Domain (lvl: 0): none
 *
 * Co-domain: R
 *
 * @see LSupplierX
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LSupplier<R> extends LSupplierX<R, RuntimeException>, MetaSupplier, MetaInterface.NonThrowing {

	public static final String DESCRIPTION = "LSupplier: R doGet()";

	@Override
	@Deprecated
	// calling this method via LSupplier interface should be discouraged.
	default R get() {
		return this.nestingDoGet();
	}

	@Nullable
	public R doGet();

	default R nestingDoGet() {
		return this.doGet();
	}

	default R shovingDoGet() {
		return this.doGet();
	}

	public static final LSupplier<String> NULL_VALUE_MESSAGE_SUPPLIER = () -> "Evaluated value by nonNullDoGet() method cannot be null (" + DESCRIPTION + ").";

	/** Ensures the result is not null */
	@Nonnull
	default R nonNullDoGet() {
		return Objects.requireNonNull(doGet(), NULL_VALUE_MESSAGE_SUPPLIER);
	}

	/** Returns desxription of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LSupplier.DESCRIPTION;
	}

	public static <R> LSupplier<R> of(R r) {
		return () -> r;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	public static <R> LSupplier<R> l(final @Nonnull LSupplier<R> lambda) {
		Objects.requireNonNull(lambda, "Argument [lambda] cannot be null.");
		return lambda;
	}

	// <editor-fold desc="wrap">

	/** Wraps JRE instance. */
	@Nonnull
	public static <R> LSupplier<R> wrap(final java.util.function.Supplier<R> other) {
		return other::get;
	}

	/** Wraps opposite (throwing/non-throwing) instance. */
	@Nonnull
	public static <R, X extends Exception> LSupplier<R> wrap(final @Nonnull LSupplierX<R, X> other) {
		return other::nestingDoGet;
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two suppliers together in a order. */
	@Nonnull
	default <V> LSupplier<V> then(@Nonnull LFunction<? super R, ? extends V> after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return () -> after.doApply(this.doGet());
	}

	/** Combines two suppliers together in a order. */
	@Nonnull
	default LAction then(@Nonnull LConsumer<? super R> after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return () -> after.doAccept(this.doGet());
	}

	/** Combines two suppliers together in a order. */
	@Nonnull
	default LByteSupplier thenToByte(@Nonnull LToByteFunction<? super R> after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return () -> after.doApplyAsByte(this.doGet());
	}

	/** Combines two suppliers together in a order. */
	@Nonnull
	default LShortSupplier thenToShort(@Nonnull LToShortFunction<? super R> after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return () -> after.doApplyAsShort(this.doGet());
	}

	/** Combines two suppliers together in a order. */
	@Nonnull
	default LIntSupplier thenToInt(@Nonnull LToIntFunction<? super R> after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return () -> after.doApplyAsInt(this.doGet());
	}

	/** Combines two suppliers together in a order. */
	@Nonnull
	default LLongSupplier thenToLong(@Nonnull LToLongFunction<? super R> after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return () -> after.doApplyAsLong(this.doGet());
	}

	/** Combines two suppliers together in a order. */
	@Nonnull
	default LFloatSupplier thenToFloat(@Nonnull LToFloatFunction<? super R> after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return () -> after.doApplyAsFloat(this.doGet());
	}

	/** Combines two suppliers together in a order. */
	@Nonnull
	default LDoubleSupplier thenToDouble(@Nonnull LToDoubleFunction<? super R> after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return () -> after.doApplyAsDouble(this.doGet());
	}

	/** Combines two suppliers together in a order. */
	@Nonnull
	default LCharSupplier thenToChar(@Nonnull LToCharFunction<? super R> after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return () -> after.doApplyAsChar(this.doGet());
	}

	/** Combines two suppliers together in a order. */
	@Nonnull
	default LBooleanSupplier thenToBoolean(@Nonnull LPredicate<? super R> after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return () -> after.doTest(this.doGet());
	}

	// </editor-fold>

	// <editor-fold desc="variant conversions">

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default LSupplier<R> nest() {
		return this;
	}

	/** Converts to throwing variant (RuntimeException). */
	@Nonnull
	default LSupplierX<R, RuntimeException> nestX() {
		return this;
	}

	/** Dirty way, checked exception will propagate as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LSupplier<R> shove() {
		return this;
	}

	/** Dirty way, checked exception will propagate as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LSupplierX<R, RuntimeException> shoveX() {
		return this;
	}

	// </editor-fold>

	@Nonnull
	default LSupplier<R> nonNullable() {
		return this::nonNullDoGet;
	}

	// <editor-fold desc="exception handling">

	/** Wraps with additional exception handling. */
	@Nonnull
	public static <R, X extends Exception, E extends Exception, Y extends RuntimeException> LSupplier<R> wrapException(@Nonnull final LSupplier<R> other, Class<E> exception, LSupplier<R> supplier, ExceptionHandler<E, Y> handler) {
		return () -> {
			try {
				return other.doGet();
			} catch (Exception e) {
				try {
					if (supplier != null) {
						return supplier.doGet();
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
	default <E extends Exception, Y extends RuntimeException> LSupplier<R> handle(Class<E> exception, ExceptionHandler<E, Y> handler) {
		Objects.requireNonNull(exception, Function4U.VALIDATION_MESSAGE_EXCEPTION);
		Objects.requireNonNull(handler, Function4U.VALIDATION_MESSAGE_HANDLER);

		return LSupplier.wrapException(this, exception, null, (ExceptionHandler) handler);
	}

	/** Wraps with exception handling that for any exception (including unchecked exception that might be different from X) will call handler function to determine the final exception. */
	@Nonnull
	default <Y extends RuntimeException> LSupplier<R> handle(ExceptionHandler<Exception, Y> handler) {
		Objects.requireNonNull(handler, Function4U.VALIDATION_MESSAGE_HANDLER);

		return LSupplier.wrapException(this, Exception.class, null, (ExceptionHandler) handler);
	}

	/** Wraps with exception handling that for argument exception class will call supplier and return default value instead for propagating exception.  */
	@Nonnull
	default <E extends Exception, Y extends RuntimeException> LSupplier<R> handle(Class<E> exception, LSupplier<R> supplier) {
		Objects.requireNonNull(exception, Function4U.VALIDATION_MESSAGE_EXCEPTION);
		Objects.requireNonNull(supplier, Function4U.VALIDATION_MESSAGE_HANDLER);

		return LSupplier.wrapException(this, exception, supplier, null);
	}

	/** Wraps with exception handling that for any exception will call supplier and return default value instead for propagating exception.  */
	@Nonnull
	default <Y extends RuntimeException> LSupplier<R> handle(LSupplier<R> supplier) {
		Objects.requireNonNull(supplier, Function4U.VALIDATION_MESSAGE_HANDLER);

		return LSupplier.wrapException(this, Exception.class, supplier, null);
	}

	// </editor-fold>

}
