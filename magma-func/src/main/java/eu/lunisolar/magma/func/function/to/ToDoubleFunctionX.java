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

package eu.lunisolar.magma.func.function.to;
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
 * @see {@link eu.lunisolar.magma.func.function.to.ToDoubleFunction}
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface ToDoubleFunctionX<T, X extends Exception> extends MetaFunction, PrimitiveCodomain<ToDoubleFunctionX<T, X>>, MetaThrowingInterface<X> { // NOSONAR

	public static final String DESCRIPTION = "ToDoubleFunctionX: double applyAsDouble(T t) throws X";

	public double applyAsDouble(T t) throws X;

	/** Returns desxription of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return ToDoubleFunctionX.DESCRIPTION;
	}

	/** Just to mirror the method: Ensures the result is not null */
	default double nonNull(T t) throws X {
		return applyAsDouble(t);
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	public static <T, X extends Exception> ToDoubleFunctionX<T, X> lX(final @Nonnull ToDoubleFunctionX<T, X> lambda) {
		Objects.requireNonNull(lambda, "Argument [lambda] cannot be null.");
		return lambda;
	}

	// <editor-fold desc="wrap">

	/** Wraps JRE instance. */
	@Nonnull
	public static <T, X extends Exception> ToDoubleFunctionX<T, X> std(final java.util.function.ToDoubleFunction<T> other) {
		return other::applyAsDouble;
	}

	/** Wraps opposite (throwing/non-throwing) instance. */
	@Nonnull
	public static <T, X extends Exception> ToDoubleFunctionX<T, X> wrapX(final @Nonnull ToDoubleFunction<T> other) {
		return other::applyAsDouble;
	}

	/** Wraps with additional exception handling. */
	@Nonnull
	public static <T, X extends Exception, Y extends Exception> ToDoubleFunctionX<T, Y> wrapException(@Nonnull final ToDoubleFunctionX<T, X> other, Class<? extends Exception> exception, ExceptionHandler<Exception, Y> rethrower) {
		return (T t) -> {
			try {
				return other.applyAsDouble(t);
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
	default <V1> ToDoubleFunctionX<V1, X> from(@Nonnull final FunctionX<? super V1, ? extends T, X> before1) {
		Objects.requireNonNull(before1, Function4U.VALIDATION_MESSAGE_BEFORE1);
		return (final V1 v1) -> this.applyAsDouble(before1.apply(v1));
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two functions together in a order. */
	@Nonnull
	default <V> FunctionX<T, V, X> then(@Nonnull DoubleFunctionX<? extends V, X> after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (T t) -> after.apply(this.applyAsDouble(t));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default ToByteFunctionX<T, X> thenToByte(@Nonnull DoubleToByteFunctionX<X> after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (T t) -> after.applyAsByte(this.applyAsDouble(t));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default ToShortFunctionX<T, X> thenToShort(@Nonnull DoubleToShortFunctionX<X> after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (T t) -> after.applyAsShort(this.applyAsDouble(t));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default ToIntFunctionX<T, X> thenToInt(@Nonnull DoubleToIntFunctionX<X> after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (T t) -> after.applyAsInt(this.applyAsDouble(t));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default ToLongFunctionX<T, X> thenToLong(@Nonnull DoubleToLongFunctionX<X> after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (T t) -> after.applyAsLong(this.applyAsDouble(t));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default ToFloatFunctionX<T, X> thenToFloat(@Nonnull DoubleToFloatFunctionX<X> after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (T t) -> after.applyAsFloat(this.applyAsDouble(t));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default ToDoubleFunctionX<T, X> thenToDouble(@Nonnull DoubleUnaryOperatorX<X> after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (T t) -> after.applyAsDouble(this.applyAsDouble(t));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default ToCharFunctionX<T, X> thenToChar(@Nonnull DoubleToCharFunctionX<X> after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (T t) -> after.applyAsChar(this.applyAsDouble(t));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default PredicateX<T, X> thenToBoolean(@Nonnull DoublePredicateX<X> after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (T t) -> after.test(this.applyAsDouble(t));
	}

	// </editor-fold>

	// <editor-fold desc="variant conversions">

	/** Converts to JRE variant. */
	@Nonnull
	default java.util.function.ToDoubleFunction<T> std() {
		return ToDoubleFunction.wrap(this)::applyAsDouble;
	}

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default ToDoubleFunction<T> nonThrowing() {
		return ToDoubleFunction.wrap(this);
	}

	/** Converts to throwing variant (RuntimeException). */
	@Nonnull
	default ToDoubleFunctionX<T, RuntimeException> uncheck() {
		return nonThrowing()::applyAsDouble;
	}

	// </editor-fold>

	// <editor-fold desc="exception handling">

	/** Wraps with exception handling that for argument exception class will call function to determine the final exception. */
	@Nonnull
	default <Y extends Exception> ToDoubleFunctionX<T, Y> handle(Class<? extends Exception> exception, ExceptionHandler<? super X, Y> handler) {
		Objects.requireNonNull(exception, Function4U.VALIDATION_MESSAGE_EXCEPTION);
		Objects.requireNonNull(handler, Function4U.VALIDATION_MESSAGE_HANDLER);

		return ToDoubleFunctionX.wrapException(this, exception, (ExceptionHandler) handler);
	}

	/** Wraps with exception handling that for argument exception class will call function to determine the final exception. */
	@Nonnull
	default <Y extends Exception> ToDoubleFunctionX<T, Y> handle(ExceptionHandler<? super X, Y> handler) {
		Objects.requireNonNull(handler, Function4U.VALIDATION_MESSAGE_HANDLER);

		return ToDoubleFunctionX.wrapException(this, Exception.class, (ExceptionHandler) handler);
	}

	// </editor-fold>

}
