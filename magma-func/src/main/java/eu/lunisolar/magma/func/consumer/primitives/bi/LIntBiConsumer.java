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

package eu.lunisolar.magma.func.consumer.primitives.bi;

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
 * Non-throwing functional interface (lambda) LIntBiConsumer for Java 8.
 *
 * Type: consumer
 *
 * Domain (lvl: 2): int i1,int i2
 *
 * Co-domain: none
 *
 * @see LIntBiConsumerX
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LIntBiConsumer extends LIntBiConsumerX<RuntimeException>, MetaConsumer, MetaInterface.NonThrowing {

	static final String DESCRIPTION = "LIntBiConsumer: void doAccept(int i1,int i2)";

	void doAccept(int i1, int i2);

	default void nestingDoAccept(int i1, int i2) {
		this.doAccept(i1, i2);
	}

	default void shovingDoAccept(int i1, int i2) {
		this.doAccept(i1, i2);
	}

	/** Returns desxription of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LIntBiConsumer.DESCRIPTION;
	}

	/** Captures arguments but delays the evaluation. */
	default LAction captureIBiCons(int i1, int i2) {
		return () -> this.doAccept(i1, i2);
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static LIntBiConsumer l(final @Nonnull LIntBiConsumer lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	// <editor-fold desc="wrap">

	/** Wraps opposite (throwing/non-throwing) instance. */
	@Nonnull
	static <X extends Throwable> LIntBiConsumer wrap(final @Nonnull LIntBiConsumerX<X> other) {
		return other::nestingDoAccept;
	}

	// </editor-fold>

	// <editor-fold desc="compose (functional)">

	/**
	 * Allows to manipulate the domain of the function.
	 */
	@Nonnull
	default LIntBiConsumer iBiConsFromInt(@Nonnull final LIntUnaryOperator before1, @Nonnull final LIntUnaryOperator before2) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		return (final int v1, final int v2) -> this.doAccept(before1.doApplyAsInt(v1), before2.doApplyAsInt(v2));
	}

	/**
	 * Allows to manipulate the domain of the function.
	 */
	@Nonnull
	default <V1, V2> LBiConsumer<V1, V2> iBiConsFrom(@Nonnull final LToIntFunction<? super V1> before1, @Nonnull final LToIntFunction<? super V2> before2) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		return (V1 v1, V2 v2) -> this.doAccept(before1.doApplyAsInt(v1), before2.doApplyAsInt(v2));
	}

	// </editor-fold>

	// <editor-fold desc="andThen (consumer/action)">

	/** Combines two consumers together in a order. */
	@Nonnull
	default LIntBiConsumer andThen(@Nonnull LIntBiConsumer after) {
		Null.nonNullArg(after, "after");
		return (int i1, int i2) -> {
			this.doAccept(i1, i2);
			after.doAccept(i1, i2);
		};
	}

	// </editor-fold> // <editor-fold desc="variant conversions">

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default LIntBiConsumer nestingIBiCons() {
		return this;
	}

	/** Converts to throwing variant (RuntimeException). */
	@Nonnull
	default LIntBiConsumerX<RuntimeException> nestingIBiConsX() {
		return this;
	}

	/** Dirty way, checked exception will propagate as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LIntBiConsumer shovingIBiCons() {
		return this;
	}

	/** Dirty way, checked exception will propagate as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LIntBiConsumerX<RuntimeException> shovingIBiConsX() {
		return this;
	}

	// </editor-fold>

}
