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
 * Throwing functional interface (lambda) LObjBooleanConsumerX for Java 8.
 *
 * Type: consumer
 *
 * Domain (lvl: 2): T t, boolean b
 *
 * Co-domain: none
 *
 * @see LObjBooleanConsumer
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LObjBooleanConsumerX<T, X extends Exception> extends MetaConsumer, MetaInterface.Throwing<X> {

	public static final String DESCRIPTION = "LObjBooleanConsumerX: void doAccept(T t, boolean b) throws X";

	public void doAccept(T t, boolean b) throws X;

	default void nestingDoAccept(T t, boolean b) {
		try {
			this.doAccept(t, b);
		} catch (RuntimeException e) {
			throw e;
		} catch (Exception e) {
			throw new NestedException(e);
		}
	}

	default void shovingDoAccept(T t, boolean b) {
		((LObjBooleanConsumerX<T, RuntimeException>) this).doAccept(t, b);
	}

	/** Returns desxription of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LObjBooleanConsumerX.DESCRIPTION;
	}

	/** Captures arguments but delays the evaluation. */
	default LActionX<X> capture(T t, boolean b) {
		return () -> this.doAccept(t, b);
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	public static <T, X extends Exception> LObjBooleanConsumerX<T, X> lX(final @Nonnull LObjBooleanConsumerX<T, X> lambda) {
		Objects.requireNonNull(lambda, "Argument [lambda] cannot be null.");
		return lambda;
	}

	// <editor-fold desc="wrap">

	/** Wraps opposite (throwing/non-throwing) instance. */
	@Nonnull
	public static <T, X extends Exception> LObjBooleanConsumerX<T, X> wrapX(final @Nonnull LObjBooleanConsumer<T> other) {
		return (LObjBooleanConsumerX) other;
	}

	// </editor-fold>

	// <editor-fold desc="compose (functional)">

	/**
	 * Allows to manipulate the domain of the functyion.
	 */
	@Nonnull
	default <V1> LObjBooleanConsumerX<V1, X> fromBoolean(@Nonnull final LFunctionX<? super V1, ? extends T, X> before1, @Nonnull final LBooleanUnaryOperatorX<X> before2) {
		Objects.requireNonNull(before1, Function4U.VALIDATION_MESSAGE_BEFORE1);
		Objects.requireNonNull(before2, Function4U.VALIDATION_MESSAGE_BEFORE2);
		return (final V1 v1, final boolean v2) -> this.doAccept(before1.doApply(v1), before2.doApplyAsBoolean(v2));
	}

	/**
	 * Allows to manipulate the domain of the functyion.
	 */
	@Nonnull
	default <V1, V2> LBiConsumerX<V1, V2, X> from(@Nonnull final LFunctionX<? super V1, ? extends T, X> before1, @Nonnull final LPredicateX<? super V2, X> before2) {
		Objects.requireNonNull(before1, Function4U.VALIDATION_MESSAGE_BEFORE1);
		Objects.requireNonNull(before2, Function4U.VALIDATION_MESSAGE_BEFORE2);
		return (V1 v1, V2 v2) -> this.doAccept(before1.doApply(v1), before2.doApplyAsBoolean(v2));
	}

	// </editor-fold>

	// <editor-fold desc="andThen (consumer/action)">

	/** Combines two consumers together in a order. */
	@Nonnull
	default LObjBooleanConsumerX<T, X> andThen(@Nonnull LObjBooleanConsumerX<? super T, X> after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (T t, boolean b) -> {
			this.doAccept(t, b);
			after.doAccept(t, b);
		};
	}

	// </editor-fold>
	// <editor-fold desc="variant conversions">

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default LObjBooleanConsumer<T> nest() {
		return this::nestingDoAccept;
	}

	/** Converts to throwing variant (RuntimeException). */
	@Nonnull
	default LObjBooleanConsumerX<T, RuntimeException> nestX() {
		return this::nestingDoAccept;
	}

	/** Dirty way, checked exception will propagate as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LObjBooleanConsumer<T> shove() {
		return this::shovingDoAccept;
	}

	/** Dirty way, checked exception will propagate as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LObjBooleanConsumerX<T, RuntimeException> shoveX() {
		return this::shovingDoAccept;
	}

	// </editor-fold>

	// <editor-fold desc="exception handling">

	/** Wraps with additional exception handling. */
	@Nonnull
	public static <T, X extends Exception, E extends Exception, Y extends Exception> LObjBooleanConsumerX<T, Y> wrapException(@Nonnull final LObjBooleanConsumerX<T, X> other, Class<E> exception, ExceptionHandler<E, Y> handler) {
		return (T t, boolean b) -> {
			try {
				other.doAccept(t, b);
			} catch (Exception e) {
				throw ExceptionHandler.handle(exception, Objects.requireNonNull(handler), (E) e);
			}
		};
	}

	/** Wraps with exception handling that for argument exception class will call function to determine the final exception. */
	@Nonnull
	default <E extends Exception, Y extends Exception> LObjBooleanConsumerX<T, Y> handleX(Class<E> exception, ExceptionHandler<E, Y> handler) {
		Objects.requireNonNull(exception, Function4U.VALIDATION_MESSAGE_EXCEPTION);
		Objects.requireNonNull(handler, Function4U.VALIDATION_MESSAGE_HANDLER);

		return LObjBooleanConsumerX.wrapException(this, exception, (ExceptionHandler) handler);
	}

	/** Wraps with exception handling that for any exception (including unchecked exception that might be different from X) will call handler function to determine the final exception. */
	@Nonnull
	default <Y extends Exception> LObjBooleanConsumerX<T, Y> handleX(ExceptionHandler<Exception, Y> handler) {
		Objects.requireNonNull(handler, Function4U.VALIDATION_MESSAGE_HANDLER);

		return LObjBooleanConsumerX.wrapException(this, Exception.class, (ExceptionHandler) handler);
	}

	// </editor-fold>

}
