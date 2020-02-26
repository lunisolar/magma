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
public interface DoIfSrtTrait<SELF extends DoIfSrtTrait<SELF>> extends FluentTrait<SELF> {

	// <editor-fold desc="doIf">

	public @Nonnull SELF doIf(@Nonnull LSrtPredicate predicate, @Nonnull LSrtConsumer action);

	public @Nonnull SELF doIfNot(@Nonnull LSrtPredicate predicate, @Nonnull LSrtConsumer action);

	default @Nonnull SELF doIf(short a2, @Nonnull LBiSrtPredicate predicate, @Nonnull LSrtConsumer action) {
		return doIf(a -> predicate.test(a, a2), action);
	}

	/** Variant with reverse predicate-vs-arg order. */
	default @Nonnull SELF doIf(@Nonnull LBiSrtPredicate predicate, short a2, @Nonnull LSrtConsumer action) {
		return doIf(a2, predicate, action);
	}

	default @Nonnull SELF doIfNot(short a2, @Nonnull LBiSrtPredicate predicate, @Nonnull LSrtConsumer action) {
		return doIfNot(a -> predicate.test(a, a2), action);
	}

	/** Variant with reverse predicate-vs-arg order. */
	default @Nonnull SELF doIfNot(@Nonnull LBiSrtPredicate predicate, short a2, @Nonnull LSrtConsumer action) {
		return doIfNot(a2, predicate, action);
	}

	default @Nonnull SELF doIf(short a2, short a3, @Nonnull LTriSrtPredicate predicate, @Nonnull LSrtConsumer action) {
		return doIf(a -> predicate.test(a, a2, a3), action);
	}

	/** Variant with reverse predicate-vs-arg order. */
	default @Nonnull SELF doIf(@Nonnull LTriSrtPredicate predicate, short a2, short a3, @Nonnull LSrtConsumer action) {
		return doIf(a2, a3, predicate, action);
	}

	default @Nonnull SELF doIfNot(short a2, short a3, @Nonnull LTriSrtPredicate predicate, @Nonnull LSrtConsumer action) {
		return doIfNot(a -> predicate.test(a, a2, a3), action);
	}

	/** Variant with reverse predicate-vs-arg order. */
	default @Nonnull SELF doIfNot(@Nonnull LTriSrtPredicate predicate, short a2, short a3, @Nonnull LSrtConsumer action) {
		return doIfNot(a2, a3, predicate, action);
	}

	default @Nonnull SELF doIfInt(int v, @Nonnull LSrtIntPredicate predicate, @Nonnull LSrtConsumer action) {
		return doIf(a -> predicate.test(a, v), action);
	}

	/** Variant with reverse predicate-vs-arg order. */
	default @Nonnull SELF doIfInt(@Nonnull LSrtIntPredicate predicate, int v, @Nonnull LSrtConsumer action) {
		return doIfInt(v, predicate, action);
	}

	default @Nonnull SELF doIfNotInt(int v, @Nonnull LSrtIntPredicate predicate, @Nonnull LSrtConsumer action) {
		return doIfNot(a -> predicate.test(a, v), action);
	}

	/** Variant with reverse predicate-vs-arg order. */
	default @Nonnull SELF doIfNotInt(@Nonnull LSrtIntPredicate predicate, int v, @Nonnull LSrtConsumer action) {
		return doIfNotInt(v, predicate, action);
	}

	default @Nonnull <V> SELF doIf_(V v, @Nonnull LObjSrtPredicate.LSrtObjPred<? super V> predicate, @Nonnull LSrtConsumer action) {
		return doIf(a -> predicate.testSrtObj(a, v), action);
	}

	/** Variant with reverse predicate-vs-arg order. */
	default @Nonnull <V> SELF doIf_(@Nonnull LObjSrtPredicate.LSrtObjPred<? super V> predicate, V v, @Nonnull LSrtConsumer action) {
		return doIf_(v, predicate, action);
	}

	default @Nonnull <V> SELF doIfNot_(V v, @Nonnull LObjSrtPredicate.LSrtObjPred<? super V> predicate, @Nonnull LSrtConsumer action) {
		return doIfNot(a -> predicate.testSrtObj(a, v), action);
	}

	/** Variant with reverse predicate-vs-arg order. */
	default @Nonnull <V> SELF doIfNot_(@Nonnull LObjSrtPredicate.LSrtObjPred<? super V> predicate, V v, @Nonnull LSrtConsumer action) {
		return doIfNot_(v, predicate, action);
	}

	default @Nonnull <V1> SELF doIfWithSrt(V1 with, @Nonnull LObjSrtPredicate<? super V1> predicate, @Nonnull LSrtConsumer action) {
		return doIf(a -> predicate.test(with, a), action);
	}

	/** Variant with reverse predicate-vs-arg order. */
	default @Nonnull <V1> SELF doIfWithSrt(@Nonnull LObjSrtPredicate<? super V1> predicate, V1 with, @Nonnull LSrtConsumer action) {
		return doIfWithSrt(with, predicate, action);
	}

	default @Nonnull <V1> SELF doIfNotWithSrt(V1 with, @Nonnull LObjSrtPredicate<? super V1> predicate, @Nonnull LSrtConsumer action) {
		return doIfNot(a -> predicate.test(with, a), action);
	}

	/** Variant with reverse predicate-vs-arg order. */
	default @Nonnull <V1> SELF doIfNotWithSrt(@Nonnull LObjSrtPredicate<? super V1> predicate, V1 with, @Nonnull LSrtConsumer action) {
		return doIfNotWithSrt(with, predicate, action);
	}

	// </editor-fold>
}
