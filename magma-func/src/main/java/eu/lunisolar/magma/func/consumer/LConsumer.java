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
 * Non-throwing functional interface (lambda) LConsumer for Java 8.
 *
 * Type: consumer
 *
 * Domain (lvl: 1): T t
 *
 * Co-domain: none
 *
 * @see LConsumerX
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LConsumer<T> extends LConsumerX<T, RuntimeException>, MetaConsumer, MetaInterface.NonThrowing {

	static final String DESCRIPTION = "LConsumer: void doAccept(T t)";

	/**
	 * Default implementation for JRE method that calls exception nesting method.
	 * @deprecated Calling this method via LConsumer interface should be discouraged.
	 */
	@Override
	@Deprecated
	default void accept(T t) {
		this.nestingDoAccept(t);
	}

	void doAccept(T t);

	default void nestingDoAccept(T t) {
		this.doAccept(t);
	}

	default void shovingDoAccept(T t) {
		this.doAccept(t);
	}

	/** Returns desxription of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LConsumer.DESCRIPTION;
	}

	/** Captures arguments but delays the evaluation. */
	default LAction captureCons(T t) {
		return () -> this.doAccept(t);
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T> LConsumer<T> l(final @Nonnull LConsumer<T> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	// <editor-fold desc="wrap">

	/** Wraps JRE instance. */
	@Nonnull
	static <T> LConsumer<T> wrap(final java.util.function.Consumer<T> other) {
		return other::accept;
	}

	/** Wraps opposite (throwing/non-throwing) instance. */
	@Nonnull
	static <T, X extends Throwable> LConsumer<T> wrap(final @Nonnull LConsumerX<T, X> other) {
		return other::nestingDoAccept;
	}

	// </editor-fold>

	// <editor-fold desc="compose (functional)">

	/**
	 * Allows to manipulate the domain of the function.
	 */
	@Nonnull
	default <V1> LConsumer<V1> consFrom(@Nonnull final LFunction<? super V1, ? extends T> before1) {
		Null.nonNullArg(before1, "before1");
		return v1 -> this.doAccept(before1.doApply(v1));
	}

	// </editor-fold>

	// <editor-fold desc="andThen (consumer/action)">

	/** Combines two consumers together in a order. */
	@Nonnull
	default LConsumer<T> andThen(@Nonnull LConsumer<? super T> after) {
		Null.nonNullArg(after, "after");
		return t -> {
			this.doAccept(t);
			after.doAccept(t);
		};
	}

	// </editor-fold> // <editor-fold desc="variant conversions">

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default LConsumer<T> nestingCons() {
		return this;
	}

	/** Converts to throwing variant (RuntimeException). */
	@Nonnull
	default LConsumerX<T, RuntimeException> nestingConsX() {
		return this;
	}

	/** Dirty way, checked exception will propagate as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LConsumer<T> shovingCons() {
		return this;
	}

	/** Dirty way, checked exception will propagate as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LConsumerX<T, RuntimeException> shovingConsX() {
		return this;
	}

	// </editor-fold>

}
