/*
 * This file is part of "lunisolar-magma".
 *
 * (C) Copyright 2015 Lunisolar (http://lunisolar.eu/).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package eu.lunisolar.magma.func.consumer;

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
 * Throwing functional interface (lambda) LTriConsumerX for Java 8.
 *
 * Type: consumer
 *
 * Domain (lvl: 3): T1 t1,T2 t2,T3 t3
 *
 * Co-domain: none
 *
 * @see LTriConsumer
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LTriConsumerX<T1, T2, T3, X extends Throwable> extends MetaConsumer, MetaInterface.Throwing<X> {

	static final String DESCRIPTION = "LTriConsumerX: void doAccept(T1 t1,T2 t2,T3 t3) throws X";

	void doAccept(T1 t1, T2 t2, T3 t3) throws X;

	default void nestingDoAccept(T1 t1, T2 t2, T3 t3) {
		try {
			this.doAccept(t1, t2, t3);
		} catch (RuntimeException | Error e) { // NOSONAR
			throw e;
		} catch (Throwable e) { // NOSONAR
			throw new NestedException(e);
		}
	}

	default void shovingDoAccept(T1 t1, T2 t2, T3 t3) {
		((LTriConsumerX<T1, T2, T3, RuntimeException>) this).doAccept(t1, t2, t3);
	}

	default <Y extends Throwable> void handlingDoAccept(T1 t1, T2 t2, T3 t3, HandlingInstructions<Throwable, Y> handling) throws Y {

		try {
			this.doAccept(t1, t2, t3);
		} catch (Throwable e) { // NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	/** Returns desxription of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LTriConsumerX.DESCRIPTION;
	}

	/** Captures arguments but delays the evaluation. */
	default LActionX<X> capture(T1 t1, T2 t2, T3 t3) {
		return () -> this.doAccept(t1, t2, t3);
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T1, T2, T3, X extends Throwable> LTriConsumerX<T1, T2, T3, X> lX(final @Nonnull LTriConsumerX<T1, T2, T3, X> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T1, T2, T3, X extends Throwable> LTriConsumerX<T1, T2, T3, X> lX(@Nonnull Class<X> xClass, final @Nonnull LTriConsumerX<T1, T2, T3, X> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	// <editor-fold desc="wrap">

	/** Wraps opposite (throwing/non-throwing) instance. */
	@Nonnull
	static <T1, T2, T3, X extends Throwable> LTriConsumerX<T1, T2, T3, X> wrapX(final @Nonnull LTriConsumer<T1, T2, T3> other) {
		return (LTriConsumerX) other;
	}

	// </editor-fold>

	// <editor-fold desc="compose (functional)">

	/**
	 * Allows to manipulate the domain of the functyion.
	 */
	@Nonnull
	default <V1, V2, V3> LTriConsumerX<V1, V2, V3, X> from(@Nonnull final LFunctionX<? super V1, ? extends T1, X> before1, @Nonnull final LFunctionX<? super V2, ? extends T2, X> before2, @Nonnull final LFunctionX<? super V3, ? extends T3, X> before3) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		Null.nonNullArg(before3, "before3");
		return (final V1 v1, final V2 v2, final V3 v3) -> this.doAccept(before1.doApply(v1), before2.doApply(v2), before3.doApply(v3));
	}

	// </editor-fold>

	// <editor-fold desc="andThen (consumer/action)">

	/** Combines two consumers together in a order. */
	@Nonnull
	default LTriConsumerX<T1, T2, T3, X> andThen(@Nonnull LTriConsumerX<? super T1, ? super T2, ? super T3, X> after) {
		Null.nonNullArg(after, "after");
		return (T1 t1, T2 t2, T3 t3) -> {
			this.doAccept(t1, t2, t3);
			after.doAccept(t1, t2, t3);
		};
	}

	// </editor-fold> // <editor-fold desc="variant conversions">

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default LTriConsumer<T1, T2, T3> nest() {
		return this::nestingDoAccept;
	}

	/** Converts to throwing variant (RuntimeException). */
	@Nonnull
	default LTriConsumerX<T1, T2, T3, RuntimeException> nestX() {
		return this::nestingDoAccept;
	}

	/** Dirty way, checked exception will propagate as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LTriConsumer<T1, T2, T3> shove() {
		return this::shovingDoAccept;
	}

	/** Dirty way, checked exception will propagate as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LTriConsumerX<T1, T2, T3, RuntimeException> shoveX() {
		return this::shovingDoAccept;
	}

	// </editor-fold>

	// <editor-fold desc="exception handling">

	@Nonnull
	default LTriConsumer<T1, T2, T3> handle(@Nonnull HandlingInstructions<Throwable, RuntimeException> handling) {
		return (T1 t1, T2 t2, T3 t3) -> this.handlingDoAccept(t1, t2, t3, handling);
	}

	@Nonnull
	default <Y extends Throwable> LTriConsumerX<T1, T2, T3, Y> handleX(@Nonnull HandlingInstructions<Throwable, Y> handling) {
		return (T1 t1, T2 t2, T3 t3) -> this.handlingDoAccept(t1, t2, t3, handling);
	}

	// </editor-fold>

}
