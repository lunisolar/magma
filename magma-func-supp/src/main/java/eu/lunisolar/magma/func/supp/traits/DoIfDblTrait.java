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
public interface DoIfDblTrait<SELF extends DoIfDblTrait<SELF>> extends FluentTrait<SELF> {

	// <editor-fold desc="doIf">

	public @Nonnull SELF doIf(@Nonnull LDblPredicate predicate, @Nonnull LDblConsumer action);

	public @Nonnull SELF doIfNot(@Nonnull LDblPredicate predicate, @Nonnull LDblConsumer action);

	default @Nonnull SELF doIf(double a2, @Nonnull LBiDblPredicate predicate, @Nonnull LDblConsumer action) {
		return doIf(a -> predicate.test(a, a2), action);
	}

	/** Variant with reverse predicate-vs-arg order. */
	default @Nonnull SELF doIf(@Nonnull LBiDblPredicate predicate, double a2, @Nonnull LDblConsumer action) {
		return doIf(a2, predicate, action);
	}

	default @Nonnull SELF doIfNot(double a2, @Nonnull LBiDblPredicate predicate, @Nonnull LDblConsumer action) {
		return doIfNot(a -> predicate.test(a, a2), action);
	}

	/** Variant with reverse predicate-vs-arg order. */
	default @Nonnull SELF doIfNot(@Nonnull LBiDblPredicate predicate, double a2, @Nonnull LDblConsumer action) {
		return doIfNot(a2, predicate, action);
	}

	default @Nonnull SELF doIf(double a2, double a3, @Nonnull LTriDblPredicate predicate, @Nonnull LDblConsumer action) {
		return doIf(a -> predicate.test(a, a2, a3), action);
	}

	/** Variant with reverse predicate-vs-arg order. */
	default @Nonnull SELF doIf(@Nonnull LTriDblPredicate predicate, double a2, double a3, @Nonnull LDblConsumer action) {
		return doIf(a2, a3, predicate, action);
	}

	default @Nonnull SELF doIfNot(double a2, double a3, @Nonnull LTriDblPredicate predicate, @Nonnull LDblConsumer action) {
		return doIfNot(a -> predicate.test(a, a2, a3), action);
	}

	/** Variant with reverse predicate-vs-arg order. */
	default @Nonnull SELF doIfNot(@Nonnull LTriDblPredicate predicate, double a2, double a3, @Nonnull LDblConsumer action) {
		return doIfNot(a2, a3, predicate, action);
	}

	default @Nonnull SELF doIfInt(int v, @Nonnull LDblIntPredicate predicate, @Nonnull LDblConsumer action) {
		return doIf(a -> predicate.test(a, v), action);
	}

	/** Variant with reverse predicate-vs-arg order. */
	default @Nonnull SELF doIfInt(@Nonnull LDblIntPredicate predicate, int v, @Nonnull LDblConsumer action) {
		return doIfInt(v, predicate, action);
	}

	default @Nonnull SELF doIfNotInt(int v, @Nonnull LDblIntPredicate predicate, @Nonnull LDblConsumer action) {
		return doIfNot(a -> predicate.test(a, v), action);
	}

	/** Variant with reverse predicate-vs-arg order. */
	default @Nonnull SELF doIfNotInt(@Nonnull LDblIntPredicate predicate, int v, @Nonnull LDblConsumer action) {
		return doIfNotInt(v, predicate, action);
	}

	default @Nonnull <V> SELF doIf_(V v, @Nonnull LObjDblPredicate.LDblObjPred<? super V> predicate, @Nonnull LDblConsumer action) {
		return doIf(a -> predicate.testDblObj(a, v), action);
	}

	/** Variant with reverse predicate-vs-arg order. */
	default @Nonnull <V> SELF doIf_(@Nonnull LObjDblPredicate.LDblObjPred<? super V> predicate, V v, @Nonnull LDblConsumer action) {
		return doIf_(v, predicate, action);
	}

	default @Nonnull <V> SELF doIfNot_(V v, @Nonnull LObjDblPredicate.LDblObjPred<? super V> predicate, @Nonnull LDblConsumer action) {
		return doIfNot(a -> predicate.testDblObj(a, v), action);
	}

	/** Variant with reverse predicate-vs-arg order. */
	default @Nonnull <V> SELF doIfNot_(@Nonnull LObjDblPredicate.LDblObjPred<? super V> predicate, V v, @Nonnull LDblConsumer action) {
		return doIfNot_(v, predicate, action);
	}

	default @Nonnull <V1> SELF doIfWithDbl(V1 with, @Nonnull LObjDblPredicate<? super V1> predicate, @Nonnull LDblConsumer action) {
		return doIf(a -> predicate.test(with, a), action);
	}

	/** Variant with reverse predicate-vs-arg order. */
	default @Nonnull <V1> SELF doIfWithDbl(@Nonnull LObjDblPredicate<? super V1> predicate, V1 with, @Nonnull LDblConsumer action) {
		return doIfWithDbl(with, predicate, action);
	}

	default @Nonnull <V1> SELF doIfNotWithDbl(V1 with, @Nonnull LObjDblPredicate<? super V1> predicate, @Nonnull LDblConsumer action) {
		return doIfNot(a -> predicate.test(with, a), action);
	}

	/** Variant with reverse predicate-vs-arg order. */
	default @Nonnull <V1> SELF doIfNotWithDbl(@Nonnull LObjDblPredicate<? super V1> predicate, V1 with, @Nonnull LDblConsumer action) {
		return doIfNotWithDbl(with, predicate, action);
	}

	// </editor-fold>
}
