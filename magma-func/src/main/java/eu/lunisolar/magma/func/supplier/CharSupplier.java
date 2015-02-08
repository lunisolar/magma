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
 * @see {@link eu.lunisolar.magma.func.supplier.CharSupplierX}
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface CharSupplier extends MetaSupplier, PrimitiveCodomain<CharSupplier> {

	public static final String DESCRIPTION = "CharSupplier: char getAsChar()";

	public char getAsChar();

	/** Returns desxription of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return CharSupplier.DESCRIPTION;
	}

	/** Just to mirror the method: Ensures the result is not null */
	default char nonNull() {
		return getAsChar();
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	public static CharSupplier l(final @Nonnull CharSupplier lambda) {
		Objects.requireNonNull(lambda, "Argument [lambda] cannot be null.");
		return lambda;
	}

	// <editor-fold desc="wrap">

	/** Wraps opposite (throwing/non-throwing) instance. */
	@Nonnull
	public static <X extends Exception> CharSupplier wrap(final @Nonnull CharSupplierX<X> other) {
		return () -> {
			try {
				return other.getAsChar();
			} catch (Exception e) {
				throw ExceptionHandler.handleWrapping(e);
			}
		};
	}

	/** Wraps with additional exception handling. */
	@Nonnull
	public static <X extends Exception, Y extends RuntimeException> CharSupplier wrapException(@Nonnull final CharSupplier other, Class<? extends Exception> exception, ExceptionHandler<Exception, Y> rethrower) {
		return () -> {
			try {
				return other.getAsChar();
			} catch (Exception e) {
				throw ExceptionHandler.handle(exception, rethrower, e);
			}
		};
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two suppliers together in a order. */
	@Nonnull
	default <V> Supplier<V> then(@Nonnull CharFunction<? extends V> after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return () -> after.apply(this.getAsChar());
	}

	/** Combines two suppliers together in a order. */
	@Nonnull
	default ByteSupplier thenToByte(@Nonnull CharToByteFunction after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return () -> after.applyAsByte(this.getAsChar());
	}

	/** Combines two suppliers together in a order. */
	@Nonnull
	default ShortSupplier thenToShort(@Nonnull CharToShortFunction after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return () -> after.applyAsShort(this.getAsChar());
	}

	/** Combines two suppliers together in a order. */
	@Nonnull
	default IntSupplier thenToInt(@Nonnull CharToIntFunction after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return () -> after.applyAsInt(this.getAsChar());
	}

	/** Combines two suppliers together in a order. */
	@Nonnull
	default LongSupplier thenToLong(@Nonnull CharToLongFunction after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return () -> after.applyAsLong(this.getAsChar());
	}

	/** Combines two suppliers together in a order. */
	@Nonnull
	default FloatSupplier thenToFloat(@Nonnull CharToFloatFunction after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return () -> after.applyAsFloat(this.getAsChar());
	}

	/** Combines two suppliers together in a order. */
	@Nonnull
	default DoubleSupplier thenToDouble(@Nonnull CharToDoubleFunction after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return () -> after.applyAsDouble(this.getAsChar());
	}

	/** Combines two suppliers together in a order. */
	@Nonnull
	default CharSupplier thenToChar(@Nonnull CharUnaryOperator after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return () -> after.applyAsChar(this.getAsChar());
	}

	/** Combines two suppliers together in a order. */
	@Nonnull
	default BooleanSupplier thenToBoolean(@Nonnull CharPredicate after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return () -> after.test(this.getAsChar());
	}

	// </editor-fold>

	// <editor-fold desc="variant conversions">

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default CharSupplier nonThrowing() {
		return this;
	}

	/** Converts to throwing variant (RuntimeException). */
	@Nonnull
	default CharSupplierX<RuntimeException> uncheck() {
		return this::getAsChar;
	}

	// </editor-fold>

	// <editor-fold desc="exception handling">

	/** Wraps with exception handling that for argument exception class will call function to determine the final exception. */
	@Nonnull
	default <Y extends RuntimeException> CharSupplier handle(Class<? extends Exception> exception, ExceptionHandler<? super RuntimeException, Y> handler) {
		Objects.requireNonNull(exception, Function4U.VALIDATION_MESSAGE_EXCEPTION);
		Objects.requireNonNull(handler, Function4U.VALIDATION_MESSAGE_HANDLER);

		return CharSupplier.wrapException(this, exception, (ExceptionHandler) handler);
	}

	/** Wraps with exception handling that for argument exception class will call function to determine the final exception. */
	@Nonnull
	default <Y extends RuntimeException> CharSupplier handle(ExceptionHandler<? super RuntimeException, Y> handler) {
		Objects.requireNonNull(handler, Function4U.VALIDATION_MESSAGE_HANDLER);

		return CharSupplier.wrapException(this, Exception.class, (ExceptionHandler) handler);
	}

	// </editor-fold>

}
