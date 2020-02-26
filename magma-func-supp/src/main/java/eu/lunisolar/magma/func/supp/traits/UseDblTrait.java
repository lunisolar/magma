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
public interface UseDblTrait<SELF extends UseDblTrait<SELF>> extends FluentTrait<SELF> {

	// <editor-fold desc="doIf">

	public @Nonnull SELF use(@Nonnull LDblConsumer consumer);

	default @Nonnull SELF use(double a2, @Nonnull LBiDblConsumer consumer) {
		return use(a -> consumer.accept(a, a2));
	}

	/** Variant with reverse predicate-vs-arg order. */
	default @Nonnull SELF use(@Nonnull LBiDblConsumer consumer, double a2) {
		return use(a2, consumer);
	}

	default @Nonnull SELF use(double a2, double a3, @Nonnull LTriDblConsumer consumer) {
		return use(a -> consumer.accept(a, a2, a3));
	}

	/** Variant with reverse predicate-vs-arg order. */
	default @Nonnull SELF use(@Nonnull LTriDblConsumer consumer, double a2, double a3) {
		return use(a2, a3, consumer);
	}

	default @Nonnull SELF useInt(int v, @Nonnull LDblIntPredicate consumer) {
		return use(a -> consumer.test(a, v));
	}

	/** Variant with reverse predicate-vs-arg order. */
	default @Nonnull SELF useInt(@Nonnull LDblIntPredicate consumer, int v) {
		return useInt(v, consumer);
	}

	default @Nonnull <V> SELF use_(V v, @Nonnull LObjDblPredicate.LDblObjPred<? super V> consumer) {
		return use(a -> consumer.testDblObj(a, v));
	}

	/** Variant with reverse predicate-vs-arg order. */
	default @Nonnull <V> SELF use_(@Nonnull LObjDblPredicate.LDblObjPred<? super V> consumer, V v) {
		return use_(v, consumer);
	}

	default @Nonnull <V1> SELF useWithDbl(V1 with, @Nonnull LObjDblPredicate<? super V1> consumer) {
		return use(a -> consumer.test(with, a));
	}

	/** Variant with reverse predicate-vs-arg order. */
	default @Nonnull <V1> SELF useWithDbl(@Nonnull LObjDblPredicate<? super V1> consumer, V1 with) {
		return useWithDbl(with, consumer);
	}

	// </editor-fold>
}
