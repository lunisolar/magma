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
import eu.lunisolar.magma.func.supp.memento.*; // NOSONAR
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
public interface DoIfLongSingleTrait<SELF extends DoIfLongSingleTrait<SELF>> extends LLongSingle, DoIfLongTrait<SELF>, IsLongTrait<SELF>, LongValueTrait<SELF> {

	// <editor-fold desc="doIf">

	default @Nonnull SELF doIf(@Nonnull LLongPredicate predicate, @Nonnull LLongConsumer action) {
		if (is(predicate))
			action.accept(value());
		return self();
	}

	default @Nonnull SELF doIfNot(@Nonnull LLongPredicate predicate, @Nonnull LLongConsumer action) {
		if (isNot(predicate))
			action.accept(value());
		return self();
	}

	default @Nonnull SELF doIf(long a2, @Nonnull LBiLongPredicate predicate, @Nonnull LLongConsumer action) {
		if (is(a2, predicate))
			action.accept(value());
		return self();
	}

	/** Variant with reverse predicate arguments order. */
	default @Nonnull SELF doIf(@Nonnull LBiLongPredicate predicate, long a2, @Nonnull LLongConsumer action) {
		return doIf(a2, predicate, action);
	}

	default @Nonnull SELF doIfNot(long a2, @Nonnull LBiLongPredicate predicate, @Nonnull LLongConsumer action) {
		if (isNot(a2, predicate))
			action.accept(value());
		return self();
	}

	/** Variant with reverse predicate arguments order. */
	default @Nonnull SELF doIfNot(@Nonnull LBiLongPredicate predicate, long a2, @Nonnull LLongConsumer action) {
		return doIfNot(a2, predicate, action);
	}

	default @Nonnull SELF doIf(long a2, long a3, @Nonnull LTriLongPredicate predicate, @Nonnull LLongConsumer action) {
		if (is(a2, a3, predicate))
			action.accept(value());
		return self();
	}

	/** Variant with reverse predicate arguments order. */
	default @Nonnull SELF doIf(@Nonnull LTriLongPredicate predicate, long a2, long a3, @Nonnull LLongConsumer action) {
		return doIf(a2, a3, predicate, action);
	}

	default @Nonnull SELF doIfNot(long a2, long a3, @Nonnull LTriLongPredicate predicate, @Nonnull LLongConsumer action) {
		if (isNot(a2, a3, predicate))
			action.accept(value());
		return self();
	}

	/** Variant with reverse predicate arguments order. */
	default @Nonnull SELF doIfNot(@Nonnull LTriLongPredicate predicate, long a2, long a3, @Nonnull LLongConsumer action) {
		return doIfNot(a2, a3, predicate, action);
	}

	default @Nonnull SELF doIfInt(int v, @Nonnull LLongIntPredicate predicate, @Nonnull LLongConsumer action) {
		if (isInt(v, predicate))
			action.accept(value());
		return self();
	}

	/** Variant with reverse predicate arguments order. */
	default @Nonnull SELF doIfInt(@Nonnull LLongIntPredicate predicate, int v, @Nonnull LLongConsumer action) {
		return doIfInt(v, predicate, action);
	}

	default @Nonnull SELF doIfNotInt(int v, @Nonnull LLongIntPredicate predicate, @Nonnull LLongConsumer action) {
		if (isNotInt(v, predicate))
			action.accept(value());
		return self();
	}

	/** Variant with reverse predicate arguments order. */
	default @Nonnull SELF doIfNotInt(@Nonnull LLongIntPredicate predicate, int v, @Nonnull LLongConsumer action) {
		return doIfNotInt(v, predicate, action);
	}

	default @Nonnull <V> SELF doIf_(V v, @Nonnull LObjLongPredicate.LLongObjPred<? super V> predicate, @Nonnull LLongConsumer action) {
		if (is_(v, predicate))
			action.accept(value());
		return self();
	}

	/** Variant with reverse predicate arguments order. */
	default @Nonnull <V> SELF doIf_(@Nonnull LObjLongPredicate.LLongObjPred<? super V> predicate, V v, @Nonnull LLongConsumer action) {
		return doIf_(v, predicate, action);
	}

	default @Nonnull <V> SELF doIfNot_(V v, @Nonnull LObjLongPredicate.LLongObjPred<? super V> predicate, @Nonnull LLongConsumer action) {
		if (isNot_(v, predicate))
			action.accept(value());
		return self();
	}

	/** Variant with reverse predicate arguments order. */
	default @Nonnull <V> SELF doIfNot_(@Nonnull LObjLongPredicate.LLongObjPred<? super V> predicate, V v, @Nonnull LLongConsumer action) {
		return doIfNot_(v, predicate, action);
	}

	default @Nonnull <V1> SELF doIfWithLong(V1 with, @Nonnull LObjLongPredicate<? super V1> predicate, @Nonnull LLongConsumer action) {
		if (isWithLong(with, predicate))
			action.accept(value());
		return self();
	}

	/** Variant with reverse predicate arguments order. */
	default @Nonnull <V1> SELF doIfWithLong(@Nonnull LObjLongPredicate<? super V1> predicate, V1 with, @Nonnull LLongConsumer action) {
		return doIfWithLong(with, predicate, action);
	}

	default @Nonnull <V1> SELF doIfNotWithLong(V1 with, @Nonnull LObjLongPredicate<? super V1> predicate, @Nonnull LLongConsumer action) {
		if (isNotWithLong(with, predicate))
			action.accept(value());
		return self();
	}

	/** Variant with reverse predicate arguments order. */
	default @Nonnull <V1> SELF doIfNotWithLong(@Nonnull LObjLongPredicate<? super V1> predicate, V1 with, @Nonnull LLongConsumer action) {
		return doIfNotWithLong(with, predicate, action);
	}

	// </editor-fold>
}
