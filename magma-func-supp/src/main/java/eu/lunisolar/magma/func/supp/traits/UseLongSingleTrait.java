/*
 * This file is part of "lunisolar-magma".
 *
 * (C) Copyright 2014-2019 Lunisolar (http://lunisolar.eu/).
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

package eu.lunisolar.magma.func.supp.traits;

import javax.annotation.Nonnull; // NOSONAR
import javax.annotation.Nullable; // NOSONAR
import java.util.*; // NOSONAR
import eu.lunisolar.magma.basics.*; // NOSONAR
import eu.lunisolar.magma.basics.builder.*; // NOSONAR
import eu.lunisolar.magma.basics.exceptions.*; // NOSONAR
import eu.lunisolar.magma.basics.meta.*; // NOSONAR
import eu.lunisolar.magma.basics.meta.aType.*; // NOSONAR
import eu.lunisolar.magma.basics.meta.functional.*; // NOSONAR
import eu.lunisolar.magma.basics.meta.functional.type.*; // NOSONAR
import eu.lunisolar.magma.basics.meta.functional.domain.*; // NOSONAR
import eu.lunisolar.magma.func.*; // NOSONAR
import eu.lunisolar.magma.func.supp.*; // NOSONAR
import eu.lunisolar.magma.func.supp.check.*; // NOSONAR
import eu.lunisolar.magma.func.supp.value.*; // NOSONAR
import eu.lunisolar.magma.func.tuple.*; // NOSONAR
import eu.lunisolar.magma.basics.fluent.*; // NOSONAR

import eu.lunisolar.magma.func.action.*; // NOSONAR
import eu.lunisolar.magma.func.consumer.*; // NOSONAR
import eu.lunisolar.magma.func.consumer.primitives.*; // NOSONAR
import eu.lunisolar.magma.func.consumer.primitives.bi.*; // NOSONAR
import eu.lunisolar.magma.func.consumer.primitives.obj.*; // NOSONAR
import eu.lunisolar.magma.func.consumer.primitives.tri.*; // NOSONAR
import eu.lunisolar.magma.func.function.*; // NOSONAR
import eu.lunisolar.magma.func.function.conversion.*; // NOSONAR
import eu.lunisolar.magma.func.function.from.*; // NOSONAR
import eu.lunisolar.magma.func.function.to.*; // NOSONAR
import eu.lunisolar.magma.func.operator.binary.*; // NOSONAR
import eu.lunisolar.magma.func.operator.ternary.*; // NOSONAR
import eu.lunisolar.magma.func.operator.unary.*; // NOSONAR
import eu.lunisolar.magma.func.predicate.*; // NOSONAR
import eu.lunisolar.magma.func.supplier.*; // NOSONAR

/**
 * Trait for any class that has fluent filter method.
 */
public interface UseLongSingleTrait<SELF extends UseLongSingleTrait<SELF>> extends UseLongTrait<SELF>, LongValueTrait<SELF> {

	// <editor-fold desc="doIf">

	default @Nonnull SELF use(@Nonnull LLongConsumer consumer) {
		consumer.accept(value());
		return fluentCtx();
	}

	default @Nonnull SELF use(long a2, @Nonnull LBiLongConsumer consumer) {
		consumer.accept(value(), a2);
		return fluentCtx();
	}

	/** Variant with reverse function-vs-arg order. */
	default @Nonnull SELF use(@Nonnull LBiLongConsumer consumer, long a2) {
		return use(a2, consumer);
	}

	default @Nonnull SELF use(long a2, long a3, @Nonnull LTriLongConsumer consumer) {
		consumer.accept(value(), a2, a3);
		return fluentCtx();
	}

	/** Variant with reverse function-vs-arg order. */
	default @Nonnull SELF use(@Nonnull LTriLongConsumer consumer, long a2, long a3) {
		return use(a2, a3, consumer);
	}

	default @Nonnull SELF useInt(int v, @Nonnull LLongIntConsumer consumer) {
		consumer.accept(value(), v);
		return fluentCtx();
	}

	/** Variant with reverse function-vs-arg order. */
	default @Nonnull SELF useInt(@Nonnull LLongIntConsumer consumer, int v) {
		return useInt(v, consumer);
	}

	default @Nonnull <V> SELF use_(V v, @Nonnull LObjLongConsumer.LLongObjCons<? super V> consumer) {
		consumer.acceptLongObj(value(), v);
		return fluentCtx();
	}

	/** Variant with reverse function-vs-arg order. */
	default @Nonnull <V> SELF use_(@Nonnull LObjLongConsumer.LLongObjCons<? super V> consumer, V v) {
		return use_(v, consumer);
	}

	default @Nonnull <V1> SELF useWithLong(V1 with1, @Nonnull LObjLongConsumer<? super V1> consumer) {
		consumer.accept(with1, value());
		return fluentCtx();
	}

	/** Variant with reverse function-vs-arg order. */
	default @Nonnull <V1> SELF useWithLong(@Nonnull LObjLongConsumer<? super V1> consumer, V1 with1) {
		return useWithLong(with1, consumer);
	}

	default @Nonnull <V1, V2> SELF useWith(V1 with1, V2 with2, @Nonnull LBiObjLongConsumer<? super V1, ? super V2> consumer) {
		consumer.accept(with1, with2, value());
		return fluentCtx();
	}

	/** Variant with reverse function-vs-arg order. */
	default @Nonnull <V1, V2> SELF useWith(@Nonnull LBiObjLongConsumer<? super V1, ? super V2> consumer, V1 with1, V2 with2) {
		return useWith(with1, with2, consumer);
	}

	// </editor-fold>
}
