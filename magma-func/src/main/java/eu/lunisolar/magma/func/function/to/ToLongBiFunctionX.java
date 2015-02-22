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
 * @see {@link eu.lunisolar.magma.func.function.to.ToLongBiFunction}
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface ToLongBiFunctionX<T1, T2, X extends Exception> extends MetaFunction, PrimitiveCodomain<ToLongBiFunctionX<T1, T2, X>>, MetaThrowingInterface<X> { // NOSONAR

	public static final String DESCRIPTION = "ToLongBiFunctionX: long applyAsLong(T1 t1,T2 t2) throws X";

	public long applyAsLong(T1 t1, T2 t2) throws X;

	/** Returns desxription of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return ToLongBiFunctionX.DESCRIPTION;
	}

	/** Just to mirror the method: Ensures the result is not null */
	default long nonNull(T1 t1, T2 t2) throws X {
		return applyAsLong(t1, t2);
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	public static <T1, T2, X extends Exception> ToLongBiFunctionX<T1, T2, X> lX(final @Nonnull ToLongBiFunctionX<T1, T2, X> lambda) {
		Objects.requireNonNull(lambda, "Argument [lambda] cannot be null.");
		return lambda;
	}

	// <editor-fold desc="wrap">

	/** Wraps JRE instance. */
	@Nonnull
	public static <T1, T2, X extends Exception> ToLongBiFunctionX<T1, T2, X> wrapStd(final java.util.function.ToLongBiFunction<T1, T2> other) {
		return other::applyAsLong;
	}

	/** Wraps opposite (throwing/non-throwing) instance. */
	@Nonnull
	public static <T1, T2, X extends Exception> ToLongBiFunctionX<T1, T2, X> wrapX(final @Nonnull ToLongBiFunction<T1, T2> other) {
		return other::applyAsLong;
	}

	/** Wraps with additional exception handling. */
	@Nonnull
	public static <T1, T2, X extends Exception, Y extends Exception> ToLongBiFunctionX<T1, T2, Y> wrapException(@Nonnull final ToLongBiFunctionX<T1, T2, X> other, Class<? extends Exception> exception, ExceptionHandler<Exception, Y> rethrower) {
		return (T1 t1, T2 t2) -> {
			try {
				return other.applyAsLong(t1, t2);
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
	default <V1, V2> ToLongBiFunctionX<V1, V2, X> from(@Nonnull final FunctionX<? super V1, ? extends T1, X> before1, @Nonnull final FunctionX<? super V2, ? extends T2, X> before2) {
		Objects.requireNonNull(before1, Function4U.VALIDATION_MESSAGE_BEFORE1);
		Objects.requireNonNull(before2, Function4U.VALIDATION_MESSAGE_BEFORE2);
		return (final V1 v1, final V2 v2) -> this.applyAsLong(before1.apply(v1), before2.apply(v2));
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two functions together in a order. */
	@Nonnull
	default <V> BiFunctionX<T1, T2, V, X> then(@Nonnull LongFunctionX<? extends V, X> after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (T1 t1, T2 t2) -> after.apply(this.applyAsLong(t1, t2));
	}

	// </editor-fold>

	// <editor-fold desc="variant conversions">

	/** Converts to JRE variant. */
	@Nonnull
	default java.util.function.ToLongBiFunction<T1, T2> std() {
		return ToLongBiFunction.wrap(this)::applyAsLong;
	}

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default ToLongBiFunction<T1, T2> nonThrowing() {
		return ToLongBiFunction.wrap(this);
	}

	/** Converts to throwing variant (RuntimeException). */
	@Nonnull
	default ToLongBiFunctionX<T1, T2, RuntimeException> uncheck() {
		return nonThrowing()::applyAsLong;
	}

	// </editor-fold>

	// <editor-fold desc="exception handling">

	/** Wraps with exception handling that for argument exception class will call function to determine the final exception. */
	@Nonnull
	default <Y extends Exception> ToLongBiFunctionX<T1, T2, Y> handle(Class<? extends Exception> exception, ExceptionHandler<? super X, Y> handler) {
		Objects.requireNonNull(exception, Function4U.VALIDATION_MESSAGE_EXCEPTION);
		Objects.requireNonNull(handler, Function4U.VALIDATION_MESSAGE_HANDLER);

		return ToLongBiFunctionX.wrapException(this, exception, (ExceptionHandler) handler);
	}

	/** Wraps with exception handling that for argument exception class will call function to determine the final exception. */
	@Nonnull
	default <Y extends Exception> ToLongBiFunctionX<T1, T2, Y> handle(ExceptionHandler<? super X, Y> handler) {
		Objects.requireNonNull(handler, Function4U.VALIDATION_MESSAGE_HANDLER);

		return ToLongBiFunctionX.wrapException(this, Exception.class, (ExceptionHandler) handler);
	}

	// </editor-fold>

}
