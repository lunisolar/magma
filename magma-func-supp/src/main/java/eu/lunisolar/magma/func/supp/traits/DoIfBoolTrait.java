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
public interface DoIfBoolTrait<SELF extends DoIfBoolTrait<SELF>> extends FluentTrait<SELF>, IsBoolTrait<SELF> {

	// <editor-fold desc="doIf">

	default SELF doIf(@Nonnull LLogicalOperator predicate, LConsumer<SELF> action) {
		if (is(predicate))
			action.accept(self());
		return self();
	}

	default SELF doIfNot(@Nonnull LLogicalOperator predicate, LConsumer<SELF> action) {
		if (isNot(predicate))
			action.accept(self());
		return self();
	}

	default SELF doIf(boolean a2, @Nonnull LLogicalBinaryOperator predicate, LConsumer<SELF> action) {
		if (is(a2, predicate))
			action.accept(self());
		return self();
	}

	/** Variant with reverse predicate arguments order. */
	default SELF doIf(@Nonnull LLogicalBinaryOperator predicate, boolean a2, LConsumer<SELF> action) {
		return doIf(a2, predicate, action);
	}

	default SELF doIfNot(boolean a2, @Nonnull LLogicalBinaryOperator predicate, LConsumer<SELF> action) {
		if (isNot(a2, predicate))
			action.accept(self());
		return self();
	}

	/** Variant with reverse predicate arguments order. */
	default SELF doIfNot(@Nonnull LLogicalBinaryOperator predicate, boolean a2, LConsumer<SELF> action) {
		return doIfNot(a2, predicate, action);
	}

	default SELF doIf(boolean a2, boolean a3, @Nonnull LLogicalTernaryOperator predicate, LConsumer<SELF> action) {
		if (is(a2, a3, predicate))
			action.accept(self());
		return self();
	}

	/** Variant with reverse predicate arguments order. */
	default SELF doIf(@Nonnull LLogicalTernaryOperator predicate, boolean a2, boolean a3, LConsumer<SELF> action) {
		return doIf(a2, a3, predicate, action);
	}

	default SELF doIfNot(boolean a2, boolean a3, @Nonnull LLogicalTernaryOperator predicate, LConsumer<SELF> action) {
		if (isNot(a2, a3, predicate))
			action.accept(self());
		return self();
	}

	/** Variant with reverse predicate arguments order. */
	default SELF doIfNot(@Nonnull LLogicalTernaryOperator predicate, boolean a2, boolean a3, LConsumer<SELF> action) {
		return doIfNot(a2, a3, predicate, action);
	}

	default SELF doIf2(int v, @Nonnull LBoolIntPredicate predicate, LConsumer<SELF> action) {
		if (is2(v, predicate))
			action.accept(self());
		return self();
	}

	/** Variant with reverse predicate arguments order. */
	default SELF doIf2(@Nonnull LBoolIntPredicate predicate, int v, LConsumer<SELF> action) {
		return doIf2(v, predicate, action);
	}

	default SELF doIfNot2(int v, @Nonnull LBoolIntPredicate predicate, LConsumer<SELF> action) {
		if (isNot2(v, predicate))
			action.accept(self());
		return self();
	}

	/** Variant with reverse predicate arguments order. */
	default SELF doIfNot2(@Nonnull LBoolIntPredicate predicate, int v, LConsumer<SELF> action) {
		return doIfNot2(v, predicate, action);
	}

	default <V> SELF doIf2Variant(V v, @Nonnull LObjBoolPredicate.LBoolObjPred<? super V> predicate, LConsumer<SELF> action) {
		if (is2Variant(v, predicate))
			action.accept(self());
		return self();
	}

	/** Variant with reverse predicate arguments order. */
	default <V> SELF doIf2Variant(@Nonnull LObjBoolPredicate.LBoolObjPred<? super V> predicate, V v, LConsumer<SELF> action) {
		return doIf2Variant(v, predicate, action);
	}

	default <V> SELF doIfNot2Variant(V v, @Nonnull LObjBoolPredicate.LBoolObjPred<? super V> predicate, LConsumer<SELF> action) {
		if (isNot2Variant(v, predicate))
			action.accept(self());
		return self();
	}

	/** Variant with reverse predicate arguments order. */
	default <V> SELF doIfNot2Variant(@Nonnull LObjBoolPredicate.LBoolObjPred<? super V> predicate, V v, LConsumer<SELF> action) {
		return doIfNot2Variant(v, predicate, action);
	}

	// </editor-fold>
}
