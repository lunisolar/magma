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
 * @see {@link eu.lunisolar.magma.func.supplier.SupplierX}
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface Supplier<R> extends java.util.function.Supplier<R>, MetaSupplier {

	public static final String DESCRIPTION = "Supplier: R get()";

	// Ovverriding methods can cause problems with inference.

	/** Returns desxription of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return Supplier.DESCRIPTION;
	}

	public static final Supplier<String> NULL_VALUE_MESSAGE_SUPPLIER = () -> "Evaluated value by nonNull() method cannot be null (" + DESCRIPTION + ").";

	/** Ensures the result is not null */
	@Nonnull
	default R nonNull() {
		return Objects.requireNonNull(get(), NULL_VALUE_MESSAGE_SUPPLIER);
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	public static <R> Supplier<R> l(final @Nonnull Supplier<R> lambda) {
		Objects.requireNonNull(lambda, "Argument [lambda] cannot be null.");
		return lambda;
	}

	// <editor-fold desc="wrap">

	/** Wraps JRE instance. */
	@Nonnull
	public static <R> Supplier<R> wrapStd(final java.util.function.Supplier<R> other) {
		return other::get;
	}

	/** Wraps opposite (throwing/non-throwing) instance. */
	@Nonnull
	public static <R, X extends Exception> Supplier<R> wrap(final @Nonnull SupplierX<R, X> other) {
		return () -> {
			try {
				return other.get();
			} catch (Exception e) {
				throw ExceptionHandler.handleWrapping(e);
			}
		};
	}

	/** Wraps with additional exception handling. */
	@Nonnull
	public static <R, X extends Exception, Y extends RuntimeException> Supplier<R> wrapException(@Nonnull final Supplier<R> other, Class<? extends Exception> exception, ExceptionHandler<Exception, Y> rethrower) {
		return () -> {
			try {
				return other.get();
			} catch (Exception e) {
				throw ExceptionHandler.handle(exception, rethrower, e);
			}
		};
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two suppliers together in a order. */
	@Nonnull
	default <V> Supplier<V> then(@Nonnull Function<? super R, ? extends V> after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return () -> after.apply(this.get());
	}

	/** Combines two suppliers together in a order. */
	@Nonnull
	default Action then(@Nonnull Consumer<? super R> after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return () -> after.accept(this.get());
	}

	/** Combines two suppliers together in a order. */
	@Nonnull
	default ByteSupplier thenToByte(@Nonnull ToByteFunction<? super R> after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return () -> after.applyAsByte(this.get());
	}

	/** Combines two suppliers together in a order. */
	@Nonnull
	default ShortSupplier thenToShort(@Nonnull ToShortFunction<? super R> after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return () -> after.applyAsShort(this.get());
	}

	/** Combines two suppliers together in a order. */
	@Nonnull
	default IntSupplier thenToInt(@Nonnull ToIntFunction<? super R> after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return () -> after.applyAsInt(this.get());
	}

	/** Combines two suppliers together in a order. */
	@Nonnull
	default LongSupplier thenToLong(@Nonnull ToLongFunction<? super R> after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return () -> after.applyAsLong(this.get());
	}

	/** Combines two suppliers together in a order. */
	@Nonnull
	default FloatSupplier thenToFloat(@Nonnull ToFloatFunction<? super R> after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return () -> after.applyAsFloat(this.get());
	}

	/** Combines two suppliers together in a order. */
	@Nonnull
	default DoubleSupplier thenToDouble(@Nonnull ToDoubleFunction<? super R> after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return () -> after.applyAsDouble(this.get());
	}

	/** Combines two suppliers together in a order. */
	@Nonnull
	default CharSupplier thenToChar(@Nonnull ToCharFunction<? super R> after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return () -> after.applyAsChar(this.get());
	}

	/** Combines two suppliers together in a order. */
	@Nonnull
	default BooleanSupplier thenToBoolean(@Nonnull Predicate<? super R> after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return () -> after.test(this.get());
	}

	// </editor-fold>

	// <editor-fold desc="variant conversions">

	/** Converts to JRE variant. */
	@Nonnull
	default java.util.function.Supplier<R> std() {
		return this;
	}

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default Supplier<R> nonThrowing() {
		return this;
	}

	/** Converts to throwing variant (RuntimeException). */
	@Nonnull
	default SupplierX<R, RuntimeException> uncheck() {
		return this::get;
	}

	// </editor-fold>

	@Nonnull
	default Supplier<R> nonNullable() {
		return new NonNullSupplier(this);
	}

	// <editor-fold desc="exception handling">

	/** Wraps with exception handling that for argument exception class will call function to determine the final exception. */
	@Nonnull
	default <Y extends RuntimeException> Supplier<R> handle(Class<? extends Exception> exception, ExceptionHandler<? super RuntimeException, Y> handler) {
		Objects.requireNonNull(exception, Function4U.VALIDATION_MESSAGE_EXCEPTION);
		Objects.requireNonNull(handler, Function4U.VALIDATION_MESSAGE_HANDLER);

		return Supplier.wrapException(this, exception, (ExceptionHandler) handler);
	}

	/** Wraps with exception handling that for argument exception class will call function to determine the final exception. */
	@Nonnull
	default <Y extends RuntimeException> Supplier<R> handle(ExceptionHandler<? super RuntimeException, Y> handler) {
		Objects.requireNonNull(handler, Function4U.VALIDATION_MESSAGE_HANDLER);

		return Supplier.wrapException(this, Exception.class, (ExceptionHandler) handler);
	}

	// </editor-fold>

}
