/*
 * This file is part of "lunisolar-magma".
 *
 * (C) Copyright 2014-2022 Lunisolar (http://lunisolar.eu/).
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
public interface DoIfFltTrait<SELF extends DoIfFltTrait<SELF>> extends FluentTrait<SELF> {

	// <editor-fold desc="doIf">

	public @Nonnull SELF doIf(@Nonnull LFltPredicate predicate, @Nonnull LFltConsumer action);

	public @Nonnull SELF doIfNot(@Nonnull LFltPredicate predicate, @Nonnull LFltConsumer action);

	default @Nonnull SELF doIf(float a2, @Nonnull LBiFltPredicate predicate, @Nonnull LFltConsumer action) {
		return doIf(a -> predicate.test(a, a2), action);
	}

	/** Variant with reverse function-vs-arg order. */
	default @Nonnull SELF doIf(@Nonnull LBiFltPredicate predicate, float a2, @Nonnull LFltConsumer action) {
		return doIf(a2, predicate, action);
	}

	default @Nonnull SELF doIfNot(float a2, @Nonnull LBiFltPredicate predicate, @Nonnull LFltConsumer action) {
		return doIfNot(a -> predicate.test(a, a2), action);
	}

	/** Variant with reverse function-vs-arg order. */
	default @Nonnull SELF doIfNot(@Nonnull LBiFltPredicate predicate, float a2, @Nonnull LFltConsumer action) {
		return doIfNot(a2, predicate, action);
	}

	default @Nonnull SELF doIf(float a2, float a3, @Nonnull LTriFltPredicate predicate, @Nonnull LFltConsumer action) {
		return doIf(a -> predicate.test(a, a2, a3), action);
	}

	/** Variant with reverse function-vs-arg order. */
	default @Nonnull SELF doIf(@Nonnull LTriFltPredicate predicate, float a2, float a3, @Nonnull LFltConsumer action) {
		return doIf(a2, a3, predicate, action);
	}

	default @Nonnull SELF doIfNot(float a2, float a3, @Nonnull LTriFltPredicate predicate, @Nonnull LFltConsumer action) {
		return doIfNot(a -> predicate.test(a, a2, a3), action);
	}

	/** Variant with reverse function-vs-arg order. */
	default @Nonnull SELF doIfNot(@Nonnull LTriFltPredicate predicate, float a2, float a3, @Nonnull LFltConsumer action) {
		return doIfNot(a2, a3, predicate, action);
	}

	default @Nonnull SELF doIfInt(int v, @Nonnull LFltIntPredicate predicate, @Nonnull LFltConsumer action) {
		return doIf(a -> predicate.test(a, v), action);
	}

	/** Variant with reverse function-vs-arg order. */
	default @Nonnull SELF doIfInt(@Nonnull LFltIntPredicate predicate, int v, @Nonnull LFltConsumer action) {
		return doIfInt(v, predicate, action);
	}

	default @Nonnull SELF doIfNotInt(int v, @Nonnull LFltIntPredicate predicate, @Nonnull LFltConsumer action) {
		return doIfNot(a -> predicate.test(a, v), action);
	}

	/** Variant with reverse function-vs-arg order. */
	default @Nonnull SELF doIfNotInt(@Nonnull LFltIntPredicate predicate, int v, @Nonnull LFltConsumer action) {
		return doIfNotInt(v, predicate, action);
	}

	default @Nonnull <V1> SELF doIfWithFlt(V1 with1, @Nonnull LObjFltPredicate<? super V1> predicate, @Nonnull LFltConsumer action) {
		return doIf(a -> predicate.test(with1, a), action);
	}

	/** Variant with reverse function-vs-arg order. */
	default @Nonnull <V1> SELF doIfWithFlt(@Nonnull LObjFltPredicate<? super V1> predicate, V1 with1, @Nonnull LFltConsumer action) {
		return doIfWithFlt(with1, predicate, action);
	}

	default @Nonnull <V1> SELF doIfNotWithFlt(V1 with1, @Nonnull LObjFltPredicate<? super V1> predicate, @Nonnull LFltConsumer action) {
		return doIfNot(a -> predicate.test(with1, a), action);
	}

	/** Variant with reverse function-vs-arg order. */
	default @Nonnull <V1> SELF doIfNotWithFlt(@Nonnull LObjFltPredicate<? super V1> predicate, V1 with1, @Nonnull LFltConsumer action) {
		return doIfNotWithFlt(with1, predicate, action);
	}

	default @Nonnull <V1, V2> SELF doIfWith(V1 with1, V2 with2, @Nonnull LBiObjFltPredicate<? super V1, ? super V2> predicate, @Nonnull LFltConsumer action) {
		return doIf(a -> predicate.test(with1, with2, a), action);
	}

	/** Variant with reverse function-vs-arg order. */
	default @Nonnull <V1, V2> SELF doIfWith(@Nonnull LBiObjFltPredicate<? super V1, ? super V2> predicate, V1 with1, V2 with2, @Nonnull LFltConsumer action) {
		return doIfWith(with1, with2, predicate, action);
	}

	default @Nonnull <V1, V2> SELF doIfNotWith(V1 with1, V2 with2, @Nonnull LBiObjFltPredicate<? super V1, ? super V2> predicate, @Nonnull LFltConsumer action) {
		return doIfNot(a -> predicate.test(with1, with2, a), action);
	}

	/** Variant with reverse function-vs-arg order. */
	default @Nonnull <V1, V2> SELF doIfNotWith(@Nonnull LBiObjFltPredicate<? super V1, ? super V2> predicate, V1 with1, V2 with2, @Nonnull LFltConsumer action) {
		return doIfNotWith(with1, with2, predicate, action);
	}

	// </editor-fold>
}
