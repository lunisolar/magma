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

package eu.lunisolar.magma.func.consumer.primitives;

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
 * Non-throwing functional interface (lambda) LCharConsumer for Java 8.
 *
 * Type: consumer
 *
 * Domain (lvl: 1): char c
 *
 * Co-domain: none
 *
 * @see LCharConsumerX
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LCharConsumer extends LCharConsumerX<RuntimeException>, MetaConsumer, MetaInterface.NonThrowing {

	static final String DESCRIPTION = "LCharConsumer: void doAccept(char c)";

	void doAccept(char c);

	/** Function call that handles exceptions by always nesting checked exceptions and propagating the otheres as is. */
	default void nestingDoAccept(char c) {
		this.doAccept(c);
	}

	/** Function call that handles exceptions by always propagating them as is even when they are undeclared checked ones. */
	default void shovingDoAccept(char c) {
		this.doAccept(c);
	}

	/** Returns description of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LCharConsumer.DESCRIPTION;
	}

	/** Captures arguments but delays the evaluation. */
	default LAction captureCharCons(char c) {
		return () -> this.doAccept(c);
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static LCharConsumer l(final @Nonnull LCharConsumer lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	// <editor-fold desc="wrap">

	/** Wraps opposite (throwing vs non-throwing) instance. */
	@Nonnull
	static <X extends Throwable> LCharConsumer wrap(final @Nonnull LCharConsumerX<X> other) {
		return other::nestingDoAccept;
	}

	// </editor-fold>

	// <editor-fold desc="compose (functional)">

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default LCharConsumer charConsComposeChar(@Nonnull final LCharUnaryOperator before1) {
		Null.nonNullArg(before1, "before1");
		return v1 -> this.doAccept(before1.doApplyAsChar(v1));
	}

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default <V1> LConsumer<V1> charConsCompose(@Nonnull final LToCharFunction<? super V1> before1) {
		Null.nonNullArg(before1, "before1");
		return v1 -> this.doAccept(before1.doApplyAsChar(v1));
	}

	// </editor-fold>

	// <editor-fold desc="andThen (consumer/action)">

	/** Combines two consumers together in a order. */
	@Nonnull
	default LCharConsumer andThen(@Nonnull LCharConsumer after) {
		Null.nonNullArg(after, "after");
		return c -> {
			this.doAccept(c);
			after.doAccept(c);
		};
	}

	// </editor-fold> // <editor-fold desc="variant conversions">

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default LCharConsumer nestingCharCons() {
		return this;
	}

	/** Converts to throwing variant (RuntimeException). */
	@Nonnull
	default LCharConsumerX<RuntimeException> nestingCharConsX() {
		return this;
	}

	/** Converts to non-throwing variant that will propagate checked exception as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LCharConsumer shovingCharCons() {
		return this;
	}

	/** Converts to throwing variant (RuntimeException) that will propagate checked exception as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LCharConsumerX<RuntimeException> shovingCharConsX() {
		return this;
	}

	// </editor-fold>

}
