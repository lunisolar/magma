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
 * Throwing functional interface (lambda) LBiObjByteConsumerX for Java 8.
 *
 * Type: consumer
 *
 * Domain (lvl: 3): T1 t1,T2 t2, byte b
 *
 * Co-domain: none
 *
 * @see LBiObjByteConsumer
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LBiObjByteConsumerX<T1, T2, X extends Exception> extends MetaConsumer, MetaInterface.Throwing<X> {

	public static final String DESCRIPTION = "LBiObjByteConsumerX: void accept(T1 t1,T2 t2, byte b) throws X";

	public void accept(T1 t1, T2 t2, byte b) throws X;

	/** Returns desxription of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LBiObjByteConsumerX.DESCRIPTION;
	}

	/** Captures arguments but delays the evaluation. */
	default LActionX<X> capture(T1 t1, T2 t2, byte b) {
		return () -> this.accept(t1, t2, b);
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	public static <T1, T2, X extends Exception> LBiObjByteConsumerX<T1, T2, X> lX(final @Nonnull LBiObjByteConsumerX<T1, T2, X> lambda) {
		Objects.requireNonNull(lambda, "Argument [lambda] cannot be null.");
		return lambda;
	}

	// <editor-fold desc="wrap">

	/** Wraps opposite (throwing/non-throwing) instance. */
	@Nonnull
	public static <T1, T2, X extends Exception> LBiObjByteConsumerX<T1, T2, X> wrapX(final @Nonnull LBiObjByteConsumer<T1, T2> other) {
		return other::accept;
	}

	// </editor-fold>

	// <editor-fold desc="compose (functional)">

	/**
	 * Allows to manipulate the domain of the functyion.
	 */
	@Nonnull
	default <V1, V2> LBiObjByteConsumerX<V1, V2, X> fromByte(@Nonnull final LFunctionX<? super V1, ? extends T1, X> before1, @Nonnull final LFunctionX<? super V2, ? extends T2, X> before2, @Nonnull final LByteUnaryOperatorX<X> before3) {
		Objects.requireNonNull(before1, Function4U.VALIDATION_MESSAGE_BEFORE1);
		Objects.requireNonNull(before2, Function4U.VALIDATION_MESSAGE_BEFORE2);
		Objects.requireNonNull(before3, Function4U.VALIDATION_MESSAGE_BEFORE3);
		return (final V1 v1, final V2 v2, final byte v3) -> this.accept(before1.apply(v1), before2.apply(v2), before3.applyAsByte(v3));
	}

	/**
	 * Allows to manipulate the domain of the functyion.
	 */
	@Nonnull
	default <V1, V2, V3> LTriConsumerX<V1, V2, V3, X> from(@Nonnull final LFunctionX<? super V1, ? extends T1, X> before1, @Nonnull final LFunctionX<? super V2, ? extends T2, X> before2, @Nonnull final LToByteFunctionX<? super V3, X> before3) {
		Objects.requireNonNull(before1, Function4U.VALIDATION_MESSAGE_BEFORE1);
		Objects.requireNonNull(before2, Function4U.VALIDATION_MESSAGE_BEFORE2);
		Objects.requireNonNull(before3, Function4U.VALIDATION_MESSAGE_BEFORE3);
		return (V1 v1, V2 v2, V3 v3) -> this.accept(before1.apply(v1), before2.apply(v2), before3.applyAsByte(v3));
	}

	// </editor-fold>

	// <editor-fold desc="andThen (consumer/action)">

	/** Combines two consumers together in a order. */
	@Nonnull
	default LBiObjByteConsumerX<T1, T2, X> andThen(@Nonnull LBiObjByteConsumerX<? super T1, ? super T2, X> after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (T1 t1, T2 t2, byte b) -> {
			this.accept(t1, t2, b);
			after.accept(t1, t2, b);
		};
	}

	// </editor-fold>
	// <editor-fold desc="variant conversions">

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default LBiObjByteConsumer<T1, T2> nonThrowing() {
		return LBiObjByteConsumer.wrap(this);
	}

	/** Converts to throwing variant (RuntimeException). */
	@Nonnull
	default LBiObjByteConsumerX<T1, T2, RuntimeException> uncheck() {
		return (LBiObjByteConsumerX) this;
	}

	/** Dirty way, checked exception will propagate as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LBiObjByteConsumer<T1, T2> shove() {
		LBiObjByteConsumerX<T1, T2, RuntimeException> exceptionCast = (LBiObjByteConsumerX<T1, T2, RuntimeException>) this;
		return exceptionCast::accept;
	}

	// </editor-fold>

	// <editor-fold desc="exception handling">

	/** Wraps with additional exception handling. */
	@Nonnull
	public static <T1, T2, X extends Exception, E extends Exception, Y extends Exception> LBiObjByteConsumerX<T1, T2, Y> wrapException(@Nonnull final LBiObjByteConsumerX<T1, T2, X> other, Class<E> exception, ExceptionHandler<E, Y> handler) {
		return (T1 t1, T2 t2, byte b) -> {
			try {
				other.accept(t1, t2, b);
			} catch (Exception e) {
				throw ExceptionHandler.handle(exception, Objects.requireNonNull(handler), (E) e);
			}
		};
	}

	/** Wraps with exception handling that for argument exception class will call function to determine the final exception. */
	@Nonnull
	default <E extends Exception, Y extends Exception> LBiObjByteConsumerX<T1, T2, Y> handleX(Class<E> exception, ExceptionHandler<E, Y> handler) {
		Objects.requireNonNull(exception, Function4U.VALIDATION_MESSAGE_EXCEPTION);
		Objects.requireNonNull(handler, Function4U.VALIDATION_MESSAGE_HANDLER);

		return LBiObjByteConsumerX.wrapException(this, exception, (ExceptionHandler) handler);
	}

	/** Wraps with exception handling that for any exception (including unchecked exception that might be different from X) will call handler function to determine the final exception. */
	@Nonnull
	default <Y extends Exception> LBiObjByteConsumerX<T1, T2, Y> handleX(ExceptionHandler<Exception, Y> handler) {
		Objects.requireNonNull(handler, Function4U.VALIDATION_MESSAGE_HANDLER);

		return LBiObjByteConsumerX.wrapException(this, Exception.class, (ExceptionHandler) handler);
	}

	// </editor-fold>

}