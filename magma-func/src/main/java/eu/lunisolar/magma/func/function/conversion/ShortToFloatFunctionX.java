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
 * @see {@link eu.lunisolar.magma.func.function.conversion.ShortToFloatFunction}
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface ShortToFloatFunctionX<X extends Exception> extends MetaFunction, PrimitiveCodomain<ShortToFloatFunctionX<X>>, MetaThrowingInterface<X> { // NOSONAR

	public static final String DESCRIPTION = "ShortToFloatFunctionX: float applyAsFloat(short s) throws X";

	public float applyAsFloat(short s) throws X;

	/** Returns desxription of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return ShortToFloatFunctionX.DESCRIPTION;
	}

	/** Just to mirror the method: Ensures the result is not null */
	default float nonNull(short s) throws X {
		return applyAsFloat(s);
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	public static <X extends Exception> ShortToFloatFunctionX<X> lX(final @Nonnull ShortToFloatFunctionX<X> lambda) {
		Objects.requireNonNull(lambda, "Argument [lambda] cannot be null.");
		return lambda;
	}

	// <editor-fold desc="wrap">

	/** Wraps opposite (throwing/non-throwing) instance. */
	@Nonnull
	public static <X extends Exception> ShortToFloatFunctionX<X> wrapX(final @Nonnull ShortToFloatFunction other) {
		return other::applyAsFloat;
	}

	/** Wraps with additional exception handling. */
	@Nonnull
	public static <X extends Exception, Y extends Exception> ShortToFloatFunctionX<Y> wrapException(@Nonnull final ShortToFloatFunctionX<X> other, Class<? extends Exception> exception, ExceptionHandler<Exception, Y> rethrower) {
		return (short s) -> {
			try {
				return other.applyAsFloat(s);
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
	default ShortToFloatFunctionX<X> fromShort(@Nonnull final ShortUnaryOperatorX<X> before1) {
		Objects.requireNonNull(before1, Function4U.VALIDATION_MESSAGE_BEFORE1);
		return (final short v1) -> this.applyAsFloat(before1.applyAsShort(v1));
	}

	/**
	 * Allows to manipulate the domain of the functyion.
	 */
	@Nonnull
	default <V1> ToFloatFunctionX<V1, X> from(@Nonnull final ToShortFunctionX<? super V1, X> before1) {
		Objects.requireNonNull(before1, Function4U.VALIDATION_MESSAGE_BEFORE1);
		return (V1 v1) -> this.applyAsFloat(before1.applyAsShort(v1));
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two functions together in a order. */
	@Nonnull
	default <V> ShortFunctionX<V, X> then(@Nonnull FloatFunctionX<? extends V, X> after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (short s) -> after.apply(this.applyAsFloat(s));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default ShortToByteFunctionX<X> thenToByte(@Nonnull FloatToByteFunctionX<X> after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (short s) -> after.applyAsByte(this.applyAsFloat(s));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default ShortUnaryOperatorX<X> thenToShort(@Nonnull FloatToShortFunctionX<X> after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (short s) -> after.applyAsShort(this.applyAsFloat(s));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default ShortToIntFunctionX<X> thenToInt(@Nonnull FloatToIntFunctionX<X> after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (short s) -> after.applyAsInt(this.applyAsFloat(s));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default ShortToLongFunctionX<X> thenToLong(@Nonnull FloatToLongFunctionX<X> after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (short s) -> after.applyAsLong(this.applyAsFloat(s));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default ShortToFloatFunctionX<X> thenToFloat(@Nonnull FloatUnaryOperatorX<X> after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (short s) -> after.applyAsFloat(this.applyAsFloat(s));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default ShortToDoubleFunctionX<X> thenToDouble(@Nonnull FloatToDoubleFunctionX<X> after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (short s) -> after.applyAsDouble(this.applyAsFloat(s));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default ShortToCharFunctionX<X> thenToChar(@Nonnull FloatToCharFunctionX<X> after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (short s) -> after.applyAsChar(this.applyAsFloat(s));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default ShortPredicateX<X> thenToBoolean(@Nonnull FloatPredicateX<X> after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (short s) -> after.test(this.applyAsFloat(s));
	}

	// </editor-fold>

	// <editor-fold desc="variant conversions">

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default ShortToFloatFunction nonThrowing() {
		return ShortToFloatFunction.wrap(this);
	}

	/** Converts to throwing variant (RuntimeException). */
	@Nonnull
	default ShortToFloatFunctionX<RuntimeException> uncheck() {
		return nonThrowing()::applyAsFloat;
	}

	// </editor-fold>

	// <editor-fold desc="exception handling">

	/** Wraps with exception handling that for argument exception class will call function to determine the final exception. */
	@Nonnull
	default <Y extends Exception> ShortToFloatFunctionX<Y> handle(Class<? extends Exception> exception, ExceptionHandler<? super X, Y> handler) {
		Objects.requireNonNull(exception, Function4U.VALIDATION_MESSAGE_EXCEPTION);
		Objects.requireNonNull(handler, Function4U.VALIDATION_MESSAGE_HANDLER);

		return ShortToFloatFunctionX.wrapException(this, exception, (ExceptionHandler) handler);
	}

	/** Wraps with exception handling that for argument exception class will call function to determine the final exception. */
	@Nonnull
	default <Y extends Exception> ShortToFloatFunctionX<Y> handle(ExceptionHandler<? super X, Y> handler) {
		Objects.requireNonNull(handler, Function4U.VALIDATION_MESSAGE_HANDLER);

		return ShortToFloatFunctionX.wrapException(this, Exception.class, (ExceptionHandler) handler);
	}

	// </editor-fold>

}
