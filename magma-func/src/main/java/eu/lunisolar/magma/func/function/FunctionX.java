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

package eu.lunisolar.magma.func.function;
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
 * @see {@link eu.lunisolar.magma.func.function.Function}
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface FunctionX<T, R, X extends Exception> extends MetaFunction, MetaThrowingInterface<X> { // NOSONAR

	public static final String DESCRIPTION = "FunctionX: R apply(T t) throws X";

	@Nullable
	public R apply(T t) throws X;

	/** Returns desxription of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return FunctionX.DESCRIPTION;
	}

	public static final Supplier<String> NULL_VALUE_MESSAGE_SUPPLIER = () -> "Evaluated value by nonNull() method cannot be null (" + DESCRIPTION + ").";

	/** Ensures the result is not null */
	@Nonnull
	default R nonNull(T t) throws X {
		return Objects.requireNonNull(apply(t), NULL_VALUE_MESSAGE_SUPPLIER);
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	public static <T, R, X extends Exception> FunctionX<T, R, X> lX(final @Nonnull FunctionX<T, R, X> lambda) {
		Objects.requireNonNull(lambda, "Argument [lambda] cannot be null.");
		return lambda;
	}

	// <editor-fold desc="wrap">

	/** Wraps JRE instance. */
	@Nonnull
	public static <T, R, X extends Exception> FunctionX<T, R, X> wrapStd(final java.util.function.Function<T, R> other) {
		return other::apply;
	}

	/** Wraps opposite (throwing/non-throwing) instance. */
	@Nonnull
	public static <T, R, X extends Exception> FunctionX<T, R, X> wrapX(final @Nonnull Function<T, R> other) {
		return other::apply;
	}

	/** Wraps with additional exception handling. */
	@Nonnull
	public static <T, R, X extends Exception, Y extends Exception> FunctionX<T, R, Y> wrapException(@Nonnull final FunctionX<T, R, X> other, Class<? extends Exception> exception, ExceptionHandler<Exception, Y> rethrower) {
		return (T t) -> {
			try {
				return other.apply(t);
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
	default <V1> FunctionX<V1, R, X> from(@Nonnull final FunctionX<? super V1, ? extends T, X> before1) {
		Objects.requireNonNull(before1, Function4U.VALIDATION_MESSAGE_BEFORE1);
		return (final V1 v1) -> this.apply(before1.apply(v1));
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two functions together in a order. */
	@Nonnull
	default <V> FunctionX<T, V, X> then(@Nonnull FunctionX<? super R, ? extends V, X> after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (T t) -> after.apply(this.apply(t));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default ConsumerX<T, X> then(@Nonnull ConsumerX<? super R, X> after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (T t) -> after.accept(this.apply(t));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default ToByteFunctionX<T, X> thenToByte(@Nonnull ToByteFunctionX<? super R, X> after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (T t) -> after.applyAsByte(this.apply(t));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default ToShortFunctionX<T, X> thenToShort(@Nonnull ToShortFunctionX<? super R, X> after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (T t) -> after.applyAsShort(this.apply(t));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default ToIntFunctionX<T, X> thenToInt(@Nonnull ToIntFunctionX<? super R, X> after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (T t) -> after.applyAsInt(this.apply(t));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default ToLongFunctionX<T, X> thenToLong(@Nonnull ToLongFunctionX<? super R, X> after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (T t) -> after.applyAsLong(this.apply(t));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default ToFloatFunctionX<T, X> thenToFloat(@Nonnull ToFloatFunctionX<? super R, X> after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (T t) -> after.applyAsFloat(this.apply(t));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default ToDoubleFunctionX<T, X> thenToDouble(@Nonnull ToDoubleFunctionX<? super R, X> after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (T t) -> after.applyAsDouble(this.apply(t));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default ToCharFunctionX<T, X> thenToChar(@Nonnull ToCharFunctionX<? super R, X> after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (T t) -> after.applyAsChar(this.apply(t));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default PredicateX<T, X> thenToBoolean(@Nonnull PredicateX<? super R, X> after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (T t) -> after.test(this.apply(t));
	}

	// </editor-fold>

	/** Returns a function that always returns its input argument. */
	@Nonnull
	public static <V, X extends Exception> FunctionX<V, V, X> identity() {
		return t -> t;
	}

	// <editor-fold desc="variant conversions">

	/** Converts to JRE variant. */
	@Nonnull
	default java.util.function.Function<T, R> std() {
		return Function.wrap(this)::apply;
	}

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default Function<T, R> nonThrowing() {
		return Function.wrap(this);
	}

	/** Converts to throwing variant (RuntimeException). */
	@Nonnull
	default FunctionX<T, R, RuntimeException> uncheck() {
		return nonThrowing()::apply;
	}

	// </editor-fold>

	@Nonnull
	default FunctionX<T, R, X> nonNullableX() {
		return new NonNullFunctionX(this);
	}

	// <editor-fold desc="exception handling">

	/** Wraps with exception handling that for argument exception class will call function to determine the final exception. */
	@Nonnull
	default <Y extends Exception> FunctionX<T, R, Y> handle(Class<? extends Exception> exception, ExceptionHandler<? super X, Y> handler) {
		Objects.requireNonNull(exception, Function4U.VALIDATION_MESSAGE_EXCEPTION);
		Objects.requireNonNull(handler, Function4U.VALIDATION_MESSAGE_HANDLER);

		return FunctionX.wrapException(this, exception, (ExceptionHandler) handler);
	}

	/** Wraps with exception handling that for argument exception class will call function to determine the final exception. */
	@Nonnull
	default <Y extends Exception> FunctionX<T, R, Y> handle(ExceptionHandler<? super X, Y> handler) {
		Objects.requireNonNull(handler, Function4U.VALIDATION_MESSAGE_HANDLER);

		return FunctionX.wrapException(this, Exception.class, (ExceptionHandler) handler);
	}

	// </editor-fold>

}
