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
public interface DoIfIntTrait<SELF extends DoIfIntTrait<SELF>> extends FluentTrait<SELF>, IsIntTrait<SELF> {

	// <editor-fold desc="doIf">

	default @Nonnull SELF doIf(@Nonnull LIntPredicate predicate, LConsumer<SELF> action) {
		if (is(predicate))
			action.accept(self());
		return self();
	}

	default @Nonnull SELF doIfNot(@Nonnull LIntPredicate predicate, LConsumer<SELF> action) {
		if (isNot(predicate))
			action.accept(self());
		return self();
	}

	default @Nonnull SELF doIf(int a2, @Nonnull LBiIntPredicate predicate, LConsumer<SELF> action) {
		if (is(a2, predicate))
			action.accept(self());
		return self();
	}

	/** Variant with reverse predicate arguments order. */
	default @Nonnull SELF doIf(@Nonnull LBiIntPredicate predicate, int a2, LConsumer<SELF> action) {
		return doIf(a2, predicate, action);
	}

	default @Nonnull SELF doIfNot(int a2, @Nonnull LBiIntPredicate predicate, LConsumer<SELF> action) {
		if (isNot(a2, predicate))
			action.accept(self());
		return self();
	}

	/** Variant with reverse predicate arguments order. */
	default @Nonnull SELF doIfNot(@Nonnull LBiIntPredicate predicate, int a2, LConsumer<SELF> action) {
		return doIfNot(a2, predicate, action);
	}

	default @Nonnull SELF doIf(int a2, int a3, @Nonnull LTriIntPredicate predicate, LConsumer<SELF> action) {
		if (is(a2, a3, predicate))
			action.accept(self());
		return self();
	}

	/** Variant with reverse predicate arguments order. */
	default @Nonnull SELF doIf(@Nonnull LTriIntPredicate predicate, int a2, int a3, LConsumer<SELF> action) {
		return doIf(a2, a3, predicate, action);
	}

	default @Nonnull SELF doIfNot(int a2, int a3, @Nonnull LTriIntPredicate predicate, LConsumer<SELF> action) {
		if (isNot(a2, a3, predicate))
			action.accept(self());
		return self();
	}

	/** Variant with reverse predicate arguments order. */
	default @Nonnull SELF doIfNot(@Nonnull LTriIntPredicate predicate, int a2, int a3, LConsumer<SELF> action) {
		return doIfNot(a2, a3, predicate, action);
	}

	default @Nonnull SELF doIf_(boolean v, @Nonnull LBoolIntPredicate.LIntBoolPred predicate, LConsumer<SELF> action) {
		if (is_(v, predicate))
			action.accept(self());
		return self();
	}

	/** Variant with reverse predicate arguments order. */
	default @Nonnull SELF doIf_(@Nonnull LBoolIntPredicate.LIntBoolPred predicate, boolean v, LConsumer<SELF> action) {
		return doIf_(v, predicate, action);
	}

	default @Nonnull SELF doIfNot_(boolean v, @Nonnull LBoolIntPredicate.LIntBoolPred predicate, LConsumer<SELF> action) {
		if (isNot_(v, predicate))
			action.accept(self());
		return self();
	}

	/** Variant with reverse predicate arguments order. */
	default @Nonnull SELF doIfNot_(@Nonnull LBoolIntPredicate.LIntBoolPred predicate, boolean v, LConsumer<SELF> action) {
		return doIfNot_(v, predicate, action);
	}

	default @Nonnull SELF doIf_(byte v, @Nonnull LByteIntPredicate.LIntBytePred predicate, LConsumer<SELF> action) {
		if (is_(v, predicate))
			action.accept(self());
		return self();
	}

	/** Variant with reverse predicate arguments order. */
	default @Nonnull SELF doIf_(@Nonnull LByteIntPredicate.LIntBytePred predicate, byte v, LConsumer<SELF> action) {
		return doIf_(v, predicate, action);
	}

	default @Nonnull SELF doIfNot_(byte v, @Nonnull LByteIntPredicate.LIntBytePred predicate, LConsumer<SELF> action) {
		if (isNot_(v, predicate))
			action.accept(self());
		return self();
	}

	/** Variant with reverse predicate arguments order. */
	default @Nonnull SELF doIfNot_(@Nonnull LByteIntPredicate.LIntBytePred predicate, byte v, LConsumer<SELF> action) {
		return doIfNot_(v, predicate, action);
	}

	default @Nonnull SELF doIf_(double v, @Nonnull LDblIntPredicate.LIntDblPred predicate, LConsumer<SELF> action) {
		if (is_(v, predicate))
			action.accept(self());
		return self();
	}

	/** Variant with reverse predicate arguments order. */
	default @Nonnull SELF doIf_(@Nonnull LDblIntPredicate.LIntDblPred predicate, double v, LConsumer<SELF> action) {
		return doIf_(v, predicate, action);
	}

	default @Nonnull SELF doIfNot_(double v, @Nonnull LDblIntPredicate.LIntDblPred predicate, LConsumer<SELF> action) {
		if (isNot_(v, predicate))
			action.accept(self());
		return self();
	}

	/** Variant with reverse predicate arguments order. */
	default @Nonnull SELF doIfNot_(@Nonnull LDblIntPredicate.LIntDblPred predicate, double v, LConsumer<SELF> action) {
		return doIfNot_(v, predicate, action);
	}

	default @Nonnull SELF doIf_(char v, @Nonnull LCharIntPredicate.LIntCharPred predicate, LConsumer<SELF> action) {
		if (is_(v, predicate))
			action.accept(self());
		return self();
	}

	/** Variant with reverse predicate arguments order. */
	default @Nonnull SELF doIf_(@Nonnull LCharIntPredicate.LIntCharPred predicate, char v, LConsumer<SELF> action) {
		return doIf_(v, predicate, action);
	}

	default @Nonnull SELF doIfNot_(char v, @Nonnull LCharIntPredicate.LIntCharPred predicate, LConsumer<SELF> action) {
		if (isNot_(v, predicate))
			action.accept(self());
		return self();
	}

	/** Variant with reverse predicate arguments order. */
	default @Nonnull SELF doIfNot_(@Nonnull LCharIntPredicate.LIntCharPred predicate, char v, LConsumer<SELF> action) {
		return doIfNot_(v, predicate, action);
	}

	default @Nonnull SELF doIf_(short v, @Nonnull LSrtIntPredicate.LIntSrtPred predicate, LConsumer<SELF> action) {
		if (is_(v, predicate))
			action.accept(self());
		return self();
	}

	/** Variant with reverse predicate arguments order. */
	default @Nonnull SELF doIf_(@Nonnull LSrtIntPredicate.LIntSrtPred predicate, short v, LConsumer<SELF> action) {
		return doIf_(v, predicate, action);
	}

	default @Nonnull SELF doIfNot_(short v, @Nonnull LSrtIntPredicate.LIntSrtPred predicate, LConsumer<SELF> action) {
		if (isNot_(v, predicate))
			action.accept(self());
		return self();
	}

	/** Variant with reverse predicate arguments order. */
	default @Nonnull SELF doIfNot_(@Nonnull LSrtIntPredicate.LIntSrtPred predicate, short v, LConsumer<SELF> action) {
		return doIfNot_(v, predicate, action);
	}

	default @Nonnull SELF doIf_(float v, @Nonnull LFltIntPredicate.LIntFltPred predicate, LConsumer<SELF> action) {
		if (is_(v, predicate))
			action.accept(self());
		return self();
	}

	/** Variant with reverse predicate arguments order. */
	default @Nonnull SELF doIf_(@Nonnull LFltIntPredicate.LIntFltPred predicate, float v, LConsumer<SELF> action) {
		return doIf_(v, predicate, action);
	}

	default @Nonnull SELF doIfNot_(float v, @Nonnull LFltIntPredicate.LIntFltPred predicate, LConsumer<SELF> action) {
		if (isNot_(v, predicate))
			action.accept(self());
		return self();
	}

	/** Variant with reverse predicate arguments order. */
	default @Nonnull SELF doIfNot_(@Nonnull LFltIntPredicate.LIntFltPred predicate, float v, LConsumer<SELF> action) {
		return doIfNot_(v, predicate, action);
	}

	default @Nonnull SELF doIf_(long v, @Nonnull LLongIntPredicate.LIntLongPred predicate, LConsumer<SELF> action) {
		if (is_(v, predicate))
			action.accept(self());
		return self();
	}

	/** Variant with reverse predicate arguments order. */
	default @Nonnull SELF doIf_(@Nonnull LLongIntPredicate.LIntLongPred predicate, long v, LConsumer<SELF> action) {
		return doIf_(v, predicate, action);
	}

	default @Nonnull SELF doIfNot_(long v, @Nonnull LLongIntPredicate.LIntLongPred predicate, LConsumer<SELF> action) {
		if (isNot_(v, predicate))
			action.accept(self());
		return self();
	}

	/** Variant with reverse predicate arguments order. */
	default @Nonnull SELF doIfNot_(@Nonnull LLongIntPredicate.LIntLongPred predicate, long v, LConsumer<SELF> action) {
		return doIfNot_(v, predicate, action);
	}

	default @Nonnull <V> SELF doIf_(V v, @Nonnull LObjIntPredicate.LIntObjPred<? super V> predicate, LConsumer<SELF> action) {
		if (is_(v, predicate))
			action.accept(self());
		return self();
	}

	/** Variant with reverse predicate arguments order. */
	default @Nonnull <V> SELF doIf_(@Nonnull LObjIntPredicate.LIntObjPred<? super V> predicate, V v, LConsumer<SELF> action) {
		return doIf_(v, predicate, action);
	}

	default @Nonnull <V> SELF doIfNot_(V v, @Nonnull LObjIntPredicate.LIntObjPred<? super V> predicate, LConsumer<SELF> action) {
		if (isNot_(v, predicate))
			action.accept(self());
		return self();
	}

	/** Variant with reverse predicate arguments order. */
	default @Nonnull <V> SELF doIfNot_(@Nonnull LObjIntPredicate.LIntObjPred<? super V> predicate, V v, LConsumer<SELF> action) {
		return doIfNot_(v, predicate, action);
	}

	// </editor-fold>
}
