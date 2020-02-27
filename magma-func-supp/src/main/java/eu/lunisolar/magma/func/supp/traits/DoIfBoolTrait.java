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
public interface DoIfBoolTrait<SELF extends DoIfBoolTrait<SELF>> extends FluentTrait<SELF> {

	// <editor-fold desc="doIf">

	public @Nonnull SELF doIf(@Nonnull LLogicalOperator operator, @Nonnull LBoolConsumer action);

	public @Nonnull SELF doIfNot(@Nonnull LLogicalOperator operator, @Nonnull LBoolConsumer action);

	default @Nonnull SELF doIf(boolean a2, @Nonnull LLogicalBinaryOperator operator, @Nonnull LBoolConsumer action) {
		return doIf(a -> operator.apply(a, a2), action);
	}

	/** Variant with reverse function-vs-arg order. */
	default @Nonnull SELF doIf(@Nonnull LLogicalBinaryOperator operator, boolean a2, @Nonnull LBoolConsumer action) {
		return doIf(a2, operator, action);
	}

	default @Nonnull SELF doIfNot(boolean a2, @Nonnull LLogicalBinaryOperator operator, @Nonnull LBoolConsumer action) {
		return doIfNot(a -> operator.apply(a, a2), action);
	}

	/** Variant with reverse function-vs-arg order. */
	default @Nonnull SELF doIfNot(@Nonnull LLogicalBinaryOperator operator, boolean a2, @Nonnull LBoolConsumer action) {
		return doIfNot(a2, operator, action);
	}

	default @Nonnull SELF doIf(boolean a2, boolean a3, @Nonnull LLogicalTernaryOperator operator, @Nonnull LBoolConsumer action) {
		return doIf(a -> operator.apply(a, a2, a3), action);
	}

	/** Variant with reverse function-vs-arg order. */
	default @Nonnull SELF doIf(@Nonnull LLogicalTernaryOperator operator, boolean a2, boolean a3, @Nonnull LBoolConsumer action) {
		return doIf(a2, a3, operator, action);
	}

	default @Nonnull SELF doIfNot(boolean a2, boolean a3, @Nonnull LLogicalTernaryOperator operator, @Nonnull LBoolConsumer action) {
		return doIfNot(a -> operator.apply(a, a2, a3), action);
	}

	/** Variant with reverse function-vs-arg order. */
	default @Nonnull SELF doIfNot(@Nonnull LLogicalTernaryOperator operator, boolean a2, boolean a3, @Nonnull LBoolConsumer action) {
		return doIfNot(a2, a3, operator, action);
	}

	default @Nonnull SELF doIfInt(int v, @Nonnull LBoolIntPredicate operator, @Nonnull LBoolConsumer action) {
		return doIf(a -> operator.test(a, v), action);
	}

	/** Variant with reverse function-vs-arg order. */
	default @Nonnull SELF doIfInt(@Nonnull LBoolIntPredicate operator, int v, @Nonnull LBoolConsumer action) {
		return doIfInt(v, operator, action);
	}

	default @Nonnull SELF doIfNotInt(int v, @Nonnull LBoolIntPredicate operator, @Nonnull LBoolConsumer action) {
		return doIfNot(a -> operator.test(a, v), action);
	}

	/** Variant with reverse function-vs-arg order. */
	default @Nonnull SELF doIfNotInt(@Nonnull LBoolIntPredicate operator, int v, @Nonnull LBoolConsumer action) {
		return doIfNotInt(v, operator, action);
	}

	default @Nonnull <V> SELF doIf_(V v, @Nonnull LObjBoolPredicate.LBoolObjPred<? super V> operator, @Nonnull LBoolConsumer action) {
		return doIf(a -> operator.testBoolObj(a, v), action);
	}

	/** Variant with reverse function-vs-arg order. */
	default @Nonnull <V> SELF doIf_(@Nonnull LObjBoolPredicate.LBoolObjPred<? super V> operator, V v, @Nonnull LBoolConsumer action) {
		return doIf_(v, operator, action);
	}

	default @Nonnull <V> SELF doIfNot_(V v, @Nonnull LObjBoolPredicate.LBoolObjPred<? super V> operator, @Nonnull LBoolConsumer action) {
		return doIfNot(a -> operator.testBoolObj(a, v), action);
	}

	/** Variant with reverse function-vs-arg order. */
	default @Nonnull <V> SELF doIfNot_(@Nonnull LObjBoolPredicate.LBoolObjPred<? super V> operator, V v, @Nonnull LBoolConsumer action) {
		return doIfNot_(v, operator, action);
	}

	default @Nonnull <V1> SELF doIfWithBool(V1 with1, @Nonnull LObjBoolPredicate<? super V1> operator, @Nonnull LBoolConsumer action) {
		return doIf(a -> operator.test(with1, a), action);
	}

	/** Variant with reverse function-vs-arg order. */
	default @Nonnull <V1> SELF doIfWithBool(@Nonnull LObjBoolPredicate<? super V1> operator, V1 with1, @Nonnull LBoolConsumer action) {
		return doIfWithBool(with1, operator, action);
	}

	default @Nonnull <V1> SELF doIfNotWithBool(V1 with1, @Nonnull LObjBoolPredicate<? super V1> operator, @Nonnull LBoolConsumer action) {
		return doIfNot(a -> operator.test(with1, a), action);
	}

	/** Variant with reverse function-vs-arg order. */
	default @Nonnull <V1> SELF doIfNotWithBool(@Nonnull LObjBoolPredicate<? super V1> operator, V1 with1, @Nonnull LBoolConsumer action) {
		return doIfNotWithBool(with1, operator, action);
	}

	default @Nonnull <V1, V2> SELF doIfWith(V1 with1, V2 with2, @Nonnull LBiObjBoolPredicate<? super V1, ? super V2> operator, @Nonnull LBoolConsumer action) {
		return doIf(a -> operator.test(with1, with2, a), action);
	}

	/** Variant with reverse function-vs-arg order. */
	default @Nonnull <V1, V2> SELF doIfWith(@Nonnull LBiObjBoolPredicate<? super V1, ? super V2> operator, V1 with1, V2 with2, @Nonnull LBoolConsumer action) {
		return doIfWith(with1, with2, operator, action);
	}

	default @Nonnull <V1, V2> SELF doIfNotWith(V1 with1, V2 with2, @Nonnull LBiObjBoolPredicate<? super V1, ? super V2> operator, @Nonnull LBoolConsumer action) {
		return doIfNot(a -> operator.test(with1, with2, a), action);
	}

	/** Variant with reverse function-vs-arg order. */
	default @Nonnull <V1, V2> SELF doIfNotWith(@Nonnull LBiObjBoolPredicate<? super V1, ? super V2> operator, V1 with1, V2 with2, @Nonnull LBoolConsumer action) {
		return doIfNotWith(with1, with2, operator, action);
	}

	// </editor-fold>
}
