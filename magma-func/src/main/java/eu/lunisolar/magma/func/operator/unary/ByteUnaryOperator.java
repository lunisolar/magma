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

package eu.lunisolar.magma.func.operator.unary;
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
 * @see {@link eu.lunisolar.magma.func.operator.unary.ByteUnaryOperatorX}
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface ByteUnaryOperator extends MetaOperator, PrimitiveCodomain<ByteUnaryOperator> { // NOSONAR

	public static final String DESCRIPTION = "ByteUnaryOperator: byte applyAsByte(byte b)";

	public byte applyAsByte(byte b);

	/** Returns desxription of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return ByteUnaryOperator.DESCRIPTION;
	}

	/** Just to mirror the method: Ensures the result is not null */
	default byte nonNull(byte b) {
		return applyAsByte(b);
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	public static ByteUnaryOperator l(final @Nonnull ByteUnaryOperator lambda) {
		Objects.requireNonNull(lambda, "Argument [lambda] cannot be null.");
		return lambda;
	}

	// <editor-fold desc="wrap">

	/** Wraps opposite (throwing/non-throwing) instance. */
	@Nonnull
	public static <X extends Exception> ByteUnaryOperator wrap(final @Nonnull ByteUnaryOperatorX<X> other) {
		return (byte b) -> {
			try {
				return other.applyAsByte(b);
			} catch (Exception e) {
				throw ExceptionHandler.handleWrapping(e);
			}
		};
	}

	/** Wraps with additional exception handling. */
	@Nonnull
	public static <X extends Exception, Y extends RuntimeException> ByteUnaryOperator wrapException(@Nonnull final ByteUnaryOperator other, Class<? extends Exception> exception, ExceptionHandler<Exception, Y> rethrower) {
		return (byte b) -> {
			try {
				return other.applyAsByte(b);
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
	default ByteUnaryOperator fromByte(@Nonnull final ByteUnaryOperator before1) {
		Objects.requireNonNull(before1, Function4U.VALIDATION_MESSAGE_BEFORE1);
		return (final byte v1) -> this.applyAsByte(before1.applyAsByte(v1));
	}

	/**
	 * Allows to manipulate the domain of the functyion.
	 */
	@Nonnull
	default <V1> ToByteFunction<V1> from(@Nonnull final ToByteFunction<? super V1> before1) {
		Objects.requireNonNull(before1, Function4U.VALIDATION_MESSAGE_BEFORE1);
		return (V1 v1) -> this.applyAsByte(before1.applyAsByte(v1));
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two operators together in a order. */
	@Nonnull
	default <V> ByteFunction<V> then(@Nonnull ByteFunction<? extends V> after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (byte b) -> after.apply(this.applyAsByte(b));
	}

	/** Combines two operators together in a order. */
	@Nonnull
	default ByteUnaryOperator thenToByte(@Nonnull ByteUnaryOperator after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (byte b) -> after.applyAsByte(this.applyAsByte(b));
	}

	/** Combines two operators together in a order. */
	@Nonnull
	default ByteToShortFunction thenToShort(@Nonnull ByteToShortFunction after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (byte b) -> after.applyAsShort(this.applyAsByte(b));
	}

	/** Combines two operators together in a order. */
	@Nonnull
	default ByteToIntFunction thenToInt(@Nonnull ByteToIntFunction after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (byte b) -> after.applyAsInt(this.applyAsByte(b));
	}

	/** Combines two operators together in a order. */
	@Nonnull
	default ByteToLongFunction thenToLong(@Nonnull ByteToLongFunction after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (byte b) -> after.applyAsLong(this.applyAsByte(b));
	}

	/** Combines two operators together in a order. */
	@Nonnull
	default ByteToFloatFunction thenToFloat(@Nonnull ByteToFloatFunction after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (byte b) -> after.applyAsFloat(this.applyAsByte(b));
	}

	/** Combines two operators together in a order. */
	@Nonnull
	default ByteToDoubleFunction thenToDouble(@Nonnull ByteToDoubleFunction after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (byte b) -> after.applyAsDouble(this.applyAsByte(b));
	}

	/** Combines two operators together in a order. */
	@Nonnull
	default ByteToCharFunction thenToChar(@Nonnull ByteToCharFunction after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (byte b) -> after.applyAsChar(this.applyAsByte(b));
	}

	/** Combines two operators together in a order. */
	@Nonnull
	default BytePredicate thenToBoolean(@Nonnull BytePredicate after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (byte b) -> after.test(this.applyAsByte(b));
	}

	// </editor-fold>

	/** Returns a function that always returns its input argument. */
	@Nonnull
	public static ByteUnaryOperator identity() {
		return t -> t;
	}

	// <editor-fold desc="variant conversions">

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default ByteUnaryOperator nonThrowing() {
		return this;
	}

	/** Converts to throwing variant (RuntimeException). */
	@Nonnull
	default ByteUnaryOperatorX<RuntimeException> uncheck() {
		return this::applyAsByte;
	}

	// </editor-fold>

	// <editor-fold desc="exception handling">

	/** Wraps with exception handling that for argument exception class will call function to determine the final exception. */
	@Nonnull
	default <Y extends RuntimeException> ByteUnaryOperator handle(Class<? extends Exception> exception, ExceptionHandler<? super RuntimeException, Y> handler) {
		Objects.requireNonNull(exception, Function4U.VALIDATION_MESSAGE_EXCEPTION);
		Objects.requireNonNull(handler, Function4U.VALIDATION_MESSAGE_HANDLER);

		return ByteUnaryOperator.wrapException(this, exception, (ExceptionHandler) handler);
	}

	/** Wraps with exception handling that for argument exception class will call function to determine the final exception. */
	@Nonnull
	default <Y extends RuntimeException> ByteUnaryOperator handle(ExceptionHandler<? super RuntimeException, Y> handler) {
		Objects.requireNonNull(handler, Function4U.VALIDATION_MESSAGE_HANDLER);

		return ByteUnaryOperator.wrapException(this, Exception.class, (ExceptionHandler) handler);
	}

	// </editor-fold>

}
