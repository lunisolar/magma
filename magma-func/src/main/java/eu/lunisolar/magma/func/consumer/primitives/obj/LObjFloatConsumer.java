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

package eu.lunisolar.magma.func.consumer.primitives.obj;

import javax.annotation.Nonnull; // NOSONAR
import javax.annotation.Nullable; // NOSONAR
import java.util.Objects; // NOSONAR
import eu.lunisolar.magma.basics.*; //NOSONAR
import eu.lunisolar.magma.func.*; // NOSONAR
import eu.lunisolar.magma.basics.meta.*; // NOSONAR
import eu.lunisolar.magma.basics.meta.functional.*; // NOSONAR
import eu.lunisolar.magma.basics.meta.functional.type.*; // NOSONAR
import eu.lunisolar.magma.basics.meta.functional.domain.*; // NOSONAR
import eu.lunisolar.magma.func.consumer.*; // NOSONAR
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
 * Non-throwing functional interface (lambda) LObjFloatConsumer for Java 8.
 *
 * Type: consumer
 *
 * Domain (lvl: 2): T t, float f
 *
 * Co-domain: none
 *
 * @see LObjFloatConsumerX
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LObjFloatConsumer<T> extends LObjFloatConsumerX<T, RuntimeException>, MetaConsumer, MetaInterface.NonThrowing {

	public static final String DESCRIPTION = "LObjFloatConsumer: void doAccept(T t, float f)";

	public void doAccept(T t, float f);

	default void nestingDoAccept(T t, float f) {
		this.doAccept(t, f);
	}

	default void shovingDoAccept(T t, float f) {
		this.doAccept(t, f);
	}

	/** Returns desxription of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LObjFloatConsumer.DESCRIPTION;
	}

	/** Captures arguments but delays the evaluation. */
	default LAction capture(T t, float f) {
		return () -> this.doAccept(t, f);
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	public static <T> LObjFloatConsumer<T> l(final @Nonnull LObjFloatConsumer<T> lambda) {
		Objects.requireNonNull(lambda, "Argument [lambda] cannot be null.");
		return lambda;
	}

	// <editor-fold desc="wrap">

	/** Wraps opposite (throwing/non-throwing) instance. */
	@Nonnull
	public static <T, X extends Exception> LObjFloatConsumer<T> wrap(final @Nonnull LObjFloatConsumerX<T, X> other) {
		return other::nestingDoAccept;
	}

	// </editor-fold>

	// <editor-fold desc="compose (functional)">

	/**
	 * Allows to manipulate the domain of the functyion.
	 */
	@Nonnull
	default <V1> LObjFloatConsumer<V1> fromFloat(@Nonnull final LFunction<? super V1, ? extends T> before1, @Nonnull final LFloatUnaryOperator before2) {
		Objects.requireNonNull(before1, Function4U.VALIDATION_MESSAGE_BEFORE1);
		Objects.requireNonNull(before2, Function4U.VALIDATION_MESSAGE_BEFORE2);
		return (final V1 v1, final float v2) -> this.doAccept(before1.doApply(v1), before2.doApplyAsFloat(v2));
	}

	/**
	 * Allows to manipulate the domain of the functyion.
	 */
	@Nonnull
	default <V1, V2> LBiConsumer<V1, V2> from(@Nonnull final LFunction<? super V1, ? extends T> before1, @Nonnull final LToFloatFunction<? super V2> before2) {
		Objects.requireNonNull(before1, Function4U.VALIDATION_MESSAGE_BEFORE1);
		Objects.requireNonNull(before2, Function4U.VALIDATION_MESSAGE_BEFORE2);
		return (V1 v1, V2 v2) -> this.doAccept(before1.doApply(v1), before2.doApplyAsFloat(v2));
	}

	// </editor-fold>

	// <editor-fold desc="andThen (consumer/action)">

	/** Combines two consumers together in a order. */
	@Nonnull
	default LObjFloatConsumer<T> andThen(@Nonnull LObjFloatConsumer<? super T> after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (T t, float f) -> {
			this.doAccept(t, f);
			after.doAccept(t, f);
		};
	}

	// </editor-fold>
	// <editor-fold desc="variant conversions">

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default LObjFloatConsumer<T> nest() {
		return this;
	}

	/** Converts to throwing variant (RuntimeException). */
	@Nonnull
	default LObjFloatConsumerX<T, RuntimeException> nestX() {
		return this;
	}

	/** Dirty way, checked exception will propagate as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LObjFloatConsumer<T> shove() {
		return this;
	}

	/** Dirty way, checked exception will propagate as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LObjFloatConsumerX<T, RuntimeException> shoveX() {
		return this;
	}

	// </editor-fold>

	// <editor-fold desc="exception handling">

	/** Wraps with additional exception handling. */
	@Nonnull
	public static <T, X extends Exception, E extends Exception, Y extends RuntimeException> LObjFloatConsumer<T> wrapException(@Nonnull final LObjFloatConsumer<T> other, Class<E> exception, ExceptionHandler<E, Y> handler) {
		return (T t, float f) -> {
			try {
				other.doAccept(t, f);
			} catch (Exception e) {
				throw ExceptionHandler.handle(exception, Objects.requireNonNull(handler), (E) e);
			}
		};
	}

	/** Wraps with exception handling that for argument exception class will call function to determine the final exception. */
	@Nonnull
	default <E extends Exception, Y extends RuntimeException> LObjFloatConsumer<T> handle(Class<E> exception, ExceptionHandler<E, Y> handler) {
		Objects.requireNonNull(exception, Function4U.VALIDATION_MESSAGE_EXCEPTION);
		Objects.requireNonNull(handler, Function4U.VALIDATION_MESSAGE_HANDLER);

		return LObjFloatConsumer.wrapException(this, exception, (ExceptionHandler) handler);
	}

	/** Wraps with exception handling that for any exception (including unchecked exception that might be different from X) will call handler function to determine the final exception. */
	@Nonnull
	default <Y extends RuntimeException> LObjFloatConsumer<T> handle(ExceptionHandler<Exception, Y> handler) {
		Objects.requireNonNull(handler, Function4U.VALIDATION_MESSAGE_HANDLER);

		return LObjFloatConsumer.wrapException(this, Exception.class, (ExceptionHandler) handler);
	}

	// </editor-fold>

}
