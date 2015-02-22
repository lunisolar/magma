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

package eu.lunisolar.magma.func.function.conversion;
import javax.annotation.Nonnull; // NOSONAR
import javax.annotation.Nullable; // NOSONAR
import java.util.Comparator; // NOSONAR
import java.util.Objects; // NOSONAR
import eu.lunisolar.magma.basics.*; //NOSONAR
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
 * @see {@link eu.lunisolar.magma.func.function.conversion.IntToLongFunctionX}
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface IntToLongFunction extends java.util.function.IntToLongFunction, MetaFunction, PrimitiveCodomain<IntToLongFunction> { // NOSONAR

	public static final String DESCRIPTION = "IntToLongFunction: long applyAsLong(int i)";

	// Ovverriding methods can cause problems with inference.

	/** Returns desxription of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return IntToLongFunction.DESCRIPTION;
	}

	/** Just to mirror the method: Ensures the result is not null */
	default long nonNull(int i) {
		return applyAsLong(i);
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	public static IntToLongFunction l(final @Nonnull IntToLongFunction lambda) {
		Objects.requireNonNull(lambda, "Argument [lambda] cannot be null.");
		return lambda;
	}

	// <editor-fold desc="wrap">

	/** Wraps JRE instance. */
	@Nonnull
	public static IntToLongFunction wrapStd(final java.util.function.IntToLongFunction other) {
		return other::applyAsLong;
	}

	/** Wraps opposite (throwing/non-throwing) instance. */
	@Nonnull
	public static <X extends Exception> IntToLongFunction wrap(final @Nonnull IntToLongFunctionX<X> other) {
		return (int i) -> {
			try {
				return other.applyAsLong(i);
			} catch (Exception e) {
				throw ExceptionHandler.handleWrapping(e);
			}
		};
	}

	/** Wraps with additional exception handling. */
	@Nonnull
	public static <X extends Exception, Y extends RuntimeException> IntToLongFunction wrapException(@Nonnull final IntToLongFunction other, Class<? extends Exception> exception, ExceptionHandler<Exception, Y> rethrower) {
		return (int i) -> {
			try {
				return other.applyAsLong(i);
			} catch (Exception e) {
				throw ExceptionHandler.handle(exception, rethrower, e);
			}
		};
	}

	// </editor-fold>

	// <editor-fold desc="compose (functional)">

	/**
	 * Allows to manipulate the domain of the functyion.
	 */
	@Nonnull
	default IntToLongFunction fromInt(@Nonnull final IntUnaryOperator before1) {
		Objects.requireNonNull(before1, Function4U.VALIDATION_MESSAGE_BEFORE1);
		return (final int v1) -> this.applyAsLong(before1.applyAsInt(v1));
	}

	/**
	 * Allows to manipulate the domain of the functyion.
	 */
	@Nonnull
	default <V1> ToLongFunction<V1> from(@Nonnull final ToIntFunction<? super V1> before1) {
		Objects.requireNonNull(before1, Function4U.VALIDATION_MESSAGE_BEFORE1);
		return (V1 v1) -> this.applyAsLong(before1.applyAsInt(v1));
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two functions together in a order. */
	@Nonnull
	default <V> IntFunction<V> then(@Nonnull LongFunction<? extends V> after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (int i) -> after.apply(this.applyAsLong(i));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default IntToByteFunction thenToByte(@Nonnull LongToByteFunction after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (int i) -> after.applyAsByte(this.applyAsLong(i));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default IntToShortFunction thenToShort(@Nonnull LongToShortFunction after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (int i) -> after.applyAsShort(this.applyAsLong(i));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default IntUnaryOperator thenToInt(@Nonnull LongToIntFunction after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (int i) -> after.applyAsInt(this.applyAsLong(i));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default IntToLongFunction thenToLong(@Nonnull LongUnaryOperator after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (int i) -> after.applyAsLong(this.applyAsLong(i));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default IntToFloatFunction thenToFloat(@Nonnull LongToFloatFunction after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (int i) -> after.applyAsFloat(this.applyAsLong(i));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default IntToDoubleFunction thenToDouble(@Nonnull LongToDoubleFunction after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (int i) -> after.applyAsDouble(this.applyAsLong(i));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default IntToCharFunction thenToChar(@Nonnull LongToCharFunction after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (int i) -> after.applyAsChar(this.applyAsLong(i));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default IntPredicate thenToBoolean(@Nonnull LongPredicate after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (int i) -> after.test(this.applyAsLong(i));
	}

	// </editor-fold>

	// <editor-fold desc="variant conversions">

	/** Converts to JRE variant. */
	@Nonnull
	default java.util.function.IntToLongFunction std() {
		return this;
	}

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default IntToLongFunction nonThrowing() {
		return this;
	}

	/** Converts to throwing variant (RuntimeException). */
	@Nonnull
	default IntToLongFunctionX<RuntimeException> uncheck() {
		return this::applyAsLong;
	}

	// </editor-fold>

	// <editor-fold desc="exception handling">

	/** Wraps with exception handling that for argument exception class will call function to determine the final exception. */
	@Nonnull
	default <Y extends RuntimeException> IntToLongFunction handle(Class<? extends Exception> exception, ExceptionHandler<? super RuntimeException, Y> handler) {
		Objects.requireNonNull(exception, Function4U.VALIDATION_MESSAGE_EXCEPTION);
		Objects.requireNonNull(handler, Function4U.VALIDATION_MESSAGE_HANDLER);

		return IntToLongFunction.wrapException(this, exception, (ExceptionHandler) handler);
	}

	/** Wraps with exception handling that for argument exception class will call function to determine the final exception. */
	@Nonnull
	default <Y extends RuntimeException> IntToLongFunction handle(ExceptionHandler<? super RuntimeException, Y> handler) {
		Objects.requireNonNull(handler, Function4U.VALIDATION_MESSAGE_HANDLER);

		return IntToLongFunction.wrapException(this, Exception.class, (ExceptionHandler) handler);
	}

	// </editor-fold>

}
