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
import eu.lunisolar.magma.basics.exceptions.*; // NOSONAR
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
 * Throwing functional interface (lambda) LBiObjCharConsumerX for Java 8.
 *
 * Type: consumer
 *
 * Domain (lvl: 3): T1 t1,T2 t2, char c
 *
 * Co-domain: none
 *
 * @see LBiObjCharConsumer
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LBiObjCharConsumerX<T1, T2, X extends Throwable> extends MetaConsumer, MetaInterface.Throwing<X> {

	public static final String DESCRIPTION = "LBiObjCharConsumerX: void doAccept(T1 t1,T2 t2, char c) throws X";

	public void doAccept(T1 t1, T2 t2, char c) throws X;

	default void nestingDoAccept(T1 t1, T2 t2, char c) {
		try {
			this.doAccept(t1, t2, c);
		} catch (RuntimeException | Error e) {
			throw e;
		} catch (Throwable e) {
			throw new NestedException(e);
		}
	}

	default void shovingDoAccept(T1 t1, T2 t2, char c) {
		((LBiObjCharConsumerX<T1, T2, RuntimeException>) this).doAccept(t1, t2, c);
	}

	default <Y extends Throwable> void handlingDoAccept(T1 t1, T2 t2, char c, HandlingInstructions<Throwable, Y> handling) throws Y {

		try {
			this.doAccept(t1, t2, c);
		} catch (Throwable e) {
			throw Handler.handleOrNest(e, handling);
		}
	}

	/** Returns desxription of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LBiObjCharConsumerX.DESCRIPTION;
	}

	/** Captures arguments but delays the evaluation. */
	default LActionX<X> capture(T1 t1, T2 t2, char c) {
		return () -> this.doAccept(t1, t2, c);
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	public static <T1, T2, X extends Throwable> LBiObjCharConsumerX<T1, T2, X> lX(final @Nonnull LBiObjCharConsumerX<T1, T2, X> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	public static <T1, T2, X extends Throwable> LBiObjCharConsumerX<T1, T2, X> lX(@Nonnull Class<X> xClass, final @Nonnull LBiObjCharConsumerX<T1, T2, X> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	// <editor-fold desc="wrap">

	/** Wraps opposite (throwing/non-throwing) instance. */
	@Nonnull
	public static <T1, T2, X extends Throwable> LBiObjCharConsumerX<T1, T2, X> wrapX(final @Nonnull LBiObjCharConsumer<T1, T2> other) {
		return (LBiObjCharConsumerX) other;
	}

	// </editor-fold>

	// <editor-fold desc="compose (functional)">

	/**
	 * Allows to manipulate the domain of the functyion.
	 */
	@Nonnull
	default <V1, V2> LBiObjCharConsumerX<V1, V2, X> fromChar(@Nonnull final LFunctionX<? super V1, ? extends T1, X> before1, @Nonnull final LFunctionX<? super V2, ? extends T2, X> before2, @Nonnull final LCharUnaryOperatorX<X> before3) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		Null.nonNullArg(before3, "before3");
		return (final V1 v1, final V2 v2, final char v3) -> this.doAccept(before1.doApply(v1), before2.doApply(v2), before3.doApplyAsChar(v3));
	}

	/**
	 * Allows to manipulate the domain of the functyion.
	 */
	@Nonnull
	default <V1, V2, V3> LTriConsumerX<V1, V2, V3, X> from(@Nonnull final LFunctionX<? super V1, ? extends T1, X> before1, @Nonnull final LFunctionX<? super V2, ? extends T2, X> before2, @Nonnull final LToCharFunctionX<? super V3, X> before3) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		Null.nonNullArg(before3, "before3");
		return (V1 v1, V2 v2, V3 v3) -> this.doAccept(before1.doApply(v1), before2.doApply(v2), before3.doApplyAsChar(v3));
	}

	// </editor-fold>

	// <editor-fold desc="andThen (consumer/action)">

	/** Combines two consumers together in a order. */
	@Nonnull
	default LBiObjCharConsumerX<T1, T2, X> andThen(@Nonnull LBiObjCharConsumerX<? super T1, ? super T2, X> after) {
		Null.nonNullArg(after, "after");
		return (T1 t1, T2 t2, char c) -> {
			this.doAccept(t1, t2, c);
			after.doAccept(t1, t2, c);
		};
	}

	// </editor-fold> // <editor-fold desc="variant conversions">

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default LBiObjCharConsumer<T1, T2> nest() {
		return this::nestingDoAccept;
	}

	/** Converts to throwing variant (RuntimeException). */
	@Nonnull
	default LBiObjCharConsumerX<T1, T2, RuntimeException> nestX() {
		return this::nestingDoAccept;
	}

	/** Dirty way, checked exception will propagate as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LBiObjCharConsumer<T1, T2> shove() {
		return this::shovingDoAccept;
	}

	/** Dirty way, checked exception will propagate as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LBiObjCharConsumerX<T1, T2, RuntimeException> shoveX() {
		return this::shovingDoAccept;
	}

	// </editor-fold>

	// <editor-fold desc="exception handling">

	@Nonnull
	default LBiObjCharConsumer<T1, T2> handle(@Nonnull HandlingInstructions<Throwable, RuntimeException> handling) {
		return (T1 t1, T2 t2, char c) -> this.handlingDoAccept(t1, t2, c, handling);
	}

	@Nonnull
	default <Y extends Throwable> LBiObjCharConsumerX<T1, T2, Y> handleX(@Nonnull HandlingInstructions<Throwable, Y> handling) {
		return (T1 t1, T2 t2, char c) -> this.handlingDoAccept(t1, t2, c, handling);
	}

	// </editor-fold>

}
