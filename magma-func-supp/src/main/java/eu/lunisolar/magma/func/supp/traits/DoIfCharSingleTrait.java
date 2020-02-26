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
public interface DoIfCharSingleTrait<SELF extends DoIfCharSingleTrait<SELF>> extends LCharSingle, DoIfCharTrait<SELF>, IsCharTrait<SELF>, CharValueTrait<SELF> {

	// <editor-fold desc="doIf">

	default @Nonnull SELF doIf(@Nonnull LCharPredicate predicate, @Nonnull LCharConsumer action) {
		if (is(predicate))
			action.accept(value());
		return self();
	}

	default @Nonnull SELF doIfNot(@Nonnull LCharPredicate predicate, @Nonnull LCharConsumer action) {
		if (isNot(predicate))
			action.accept(value());
		return self();
	}

	default @Nonnull SELF doIf(char a2, @Nonnull LBiCharPredicate predicate, @Nonnull LCharConsumer action) {
		if (is(a2, predicate))
			action.accept(value());
		return self();
	}

	/** Variant with reverse predicate-vs-arg order. */
	default @Nonnull SELF doIf(@Nonnull LBiCharPredicate predicate, char a2, @Nonnull LCharConsumer action) {
		return doIf(a2, predicate, action);
	}

	default @Nonnull SELF doIfNot(char a2, @Nonnull LBiCharPredicate predicate, @Nonnull LCharConsumer action) {
		if (isNot(a2, predicate))
			action.accept(value());
		return self();
	}

	/** Variant with reverse predicate-vs-arg order. */
	default @Nonnull SELF doIfNot(@Nonnull LBiCharPredicate predicate, char a2, @Nonnull LCharConsumer action) {
		return doIfNot(a2, predicate, action);
	}

	default @Nonnull SELF doIf(char a2, char a3, @Nonnull LTriCharPredicate predicate, @Nonnull LCharConsumer action) {
		if (is(a2, a3, predicate))
			action.accept(value());
		return self();
	}

	/** Variant with reverse predicate-vs-arg order. */
	default @Nonnull SELF doIf(@Nonnull LTriCharPredicate predicate, char a2, char a3, @Nonnull LCharConsumer action) {
		return doIf(a2, a3, predicate, action);
	}

	default @Nonnull SELF doIfNot(char a2, char a3, @Nonnull LTriCharPredicate predicate, @Nonnull LCharConsumer action) {
		if (isNot(a2, a3, predicate))
			action.accept(value());
		return self();
	}

	/** Variant with reverse predicate-vs-arg order. */
	default @Nonnull SELF doIfNot(@Nonnull LTriCharPredicate predicate, char a2, char a3, @Nonnull LCharConsumer action) {
		return doIfNot(a2, a3, predicate, action);
	}

	default @Nonnull SELF doIfInt(int v, @Nonnull LCharIntPredicate predicate, @Nonnull LCharConsumer action) {
		if (isInt(v, predicate))
			action.accept(value());
		return self();
	}

	/** Variant with reverse predicate-vs-arg order. */
	default @Nonnull SELF doIfInt(@Nonnull LCharIntPredicate predicate, int v, @Nonnull LCharConsumer action) {
		return doIfInt(v, predicate, action);
	}

	default @Nonnull SELF doIfNotInt(int v, @Nonnull LCharIntPredicate predicate, @Nonnull LCharConsumer action) {
		if (isNotInt(v, predicate))
			action.accept(value());
		return self();
	}

	/** Variant with reverse predicate-vs-arg order. */
	default @Nonnull SELF doIfNotInt(@Nonnull LCharIntPredicate predicate, int v, @Nonnull LCharConsumer action) {
		return doIfNotInt(v, predicate, action);
	}

	default @Nonnull <V> SELF doIf_(V v, @Nonnull LObjCharPredicate.LCharObjPred<? super V> predicate, @Nonnull LCharConsumer action) {
		if (is_(v, predicate))
			action.accept(value());
		return self();
	}

	/** Variant with reverse predicate-vs-arg order. */
	default @Nonnull <V> SELF doIf_(@Nonnull LObjCharPredicate.LCharObjPred<? super V> predicate, V v, @Nonnull LCharConsumer action) {
		return doIf_(v, predicate, action);
	}

	default @Nonnull <V> SELF doIfNot_(V v, @Nonnull LObjCharPredicate.LCharObjPred<? super V> predicate, @Nonnull LCharConsumer action) {
		if (isNot_(v, predicate))
			action.accept(value());
		return self();
	}

	/** Variant with reverse predicate-vs-arg order. */
	default @Nonnull <V> SELF doIfNot_(@Nonnull LObjCharPredicate.LCharObjPred<? super V> predicate, V v, @Nonnull LCharConsumer action) {
		return doIfNot_(v, predicate, action);
	}

	default @Nonnull <V1> SELF doIfWithChar(V1 with, @Nonnull LObjCharPredicate<? super V1> predicate, @Nonnull LCharConsumer action) {
		if (isWithChar(with, predicate))
			action.accept(value());
		return self();
	}

	/** Variant with reverse predicate-vs-arg order. */
	default @Nonnull <V1> SELF doIfWithChar(@Nonnull LObjCharPredicate<? super V1> predicate, V1 with, @Nonnull LCharConsumer action) {
		return doIfWithChar(with, predicate, action);
	}

	default @Nonnull <V1> SELF doIfNotWithChar(V1 with, @Nonnull LObjCharPredicate<? super V1> predicate, @Nonnull LCharConsumer action) {
		if (isNotWithChar(with, predicate))
			action.accept(value());
		return self();
	}

	/** Variant with reverse predicate-vs-arg order. */
	default @Nonnull <V1> SELF doIfNotWithChar(@Nonnull LObjCharPredicate<? super V1> predicate, V1 with, @Nonnull LCharConsumer action) {
		return doIfNotWithChar(with, predicate, action);
	}

	// </editor-fold>
}
