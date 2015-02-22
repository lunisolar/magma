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
 * @see {@link eu.lunisolar.magma.func.function.BiFunctionX}
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface BiFunction<T1, T2, R> extends java.util.function.BiFunction<T1, T2, R>, MetaFunction { // NOSONAR

	public static final String DESCRIPTION = "BiFunction: R apply(T1 t1,T2 t2)";

	// Ovverriding methods can cause problems with inference.

	/** Returns desxription of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return BiFunction.DESCRIPTION;
	}

	public static final Supplier<String> NULL_VALUE_MESSAGE_SUPPLIER = () -> "Evaluated value by nonNull() method cannot be null (" + DESCRIPTION + ").";

	/** Ensures the result is not null */
	@Nonnull
	default R nonNull(T1 t1, T2 t2) {
		return Objects.requireNonNull(apply(t1, t2), NULL_VALUE_MESSAGE_SUPPLIER);
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	public static <T1, T2, R> BiFunction<T1, T2, R> l(final @Nonnull BiFunction<T1, T2, R> lambda) {
		Objects.requireNonNull(lambda, "Argument [lambda] cannot be null.");
		return lambda;
	}

	// <editor-fold desc="wrap">

	/** Wraps JRE instance. */
	@Nonnull
	public static <T1, T2, R> BiFunction<T1, T2, R> wrapStd(final java.util.function.BiFunction<T1, T2, R> other) {
		return other::apply;
	}

	/** Wraps opposite (throwing/non-throwing) instance. */
	@Nonnull
	public static <T1, T2, R, X extends Exception> BiFunction<T1, T2, R> wrap(final @Nonnull BiFunctionX<T1, T2, R, X> other) {
		return (T1 t1, T2 t2) -> {
			try {
				return other.apply(t1, t2);
			} catch (Exception e) {
				throw ExceptionHandler.handleWrapping(e);
			}
		};
	}

	/** Wraps with additional exception handling. */
	@Nonnull
	public static <T1, T2, R, X extends Exception, Y extends RuntimeException> BiFunction<T1, T2, R> wrapException(@Nonnull final BiFunction<T1, T2, R> other, Class<? extends Exception> exception, ExceptionHandler<Exception, Y> rethrower) {
		return (T1 t1, T2 t2) -> {
			try {
				return other.apply(t1, t2);
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
	default <V1, V2> BiFunction<V1, V2, R> from(@Nonnull final Function<? super V1, ? extends T1> before1, @Nonnull final Function<? super V2, ? extends T2> before2) {
		Objects.requireNonNull(before1, Function4U.VALIDATION_MESSAGE_BEFORE1);
		Objects.requireNonNull(before2, Function4U.VALIDATION_MESSAGE_BEFORE2);
		return (final V1 v1, final V2 v2) -> this.apply(before1.apply(v1), before2.apply(v2));
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two functions together in a order. */
	@Nonnull
	default <V> BiFunction<T1, T2, V> then(@Nonnull Function<? super R, ? extends V> after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (T1 t1, T2 t2) -> after.apply(this.apply(t1, t2));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default BiConsumer<T1, T2> then(@Nonnull Consumer<? super R> after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (T1 t1, T2 t2) -> after.accept(this.apply(t1, t2));
	}

	// </editor-fold>

	// <editor-fold desc="variant conversions">

	/** Converts to JRE variant. */
	@Nonnull
	default java.util.function.BiFunction<T1, T2, R> std() {
		return this;
	}

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default BiFunction<T1, T2, R> nonThrowing() {
		return this;
	}

	/** Converts to throwing variant (RuntimeException). */
	@Nonnull
	default BiFunctionX<T1, T2, R, RuntimeException> uncheck() {
		return this::apply;
	}

	// </editor-fold>

	@Nonnull
	default BiFunction<T1, T2, R> nonNullable() {
		return new NonNullBiFunction(this);
	}

	// <editor-fold desc="exception handling">

	/** Wraps with exception handling that for argument exception class will call function to determine the final exception. */
	@Nonnull
	default <Y extends RuntimeException> BiFunction<T1, T2, R> handle(Class<? extends Exception> exception, ExceptionHandler<? super RuntimeException, Y> handler) {
		Objects.requireNonNull(exception, Function4U.VALIDATION_MESSAGE_EXCEPTION);
		Objects.requireNonNull(handler, Function4U.VALIDATION_MESSAGE_HANDLER);

		return BiFunction.wrapException(this, exception, (ExceptionHandler) handler);
	}

	/** Wraps with exception handling that for argument exception class will call function to determine the final exception. */
	@Nonnull
	default <Y extends RuntimeException> BiFunction<T1, T2, R> handle(ExceptionHandler<? super RuntimeException, Y> handler) {
		Objects.requireNonNull(handler, Function4U.VALIDATION_MESSAGE_HANDLER);

		return BiFunction.wrapException(this, Exception.class, (ExceptionHandler) handler);
	}

	// </editor-fold>

}
