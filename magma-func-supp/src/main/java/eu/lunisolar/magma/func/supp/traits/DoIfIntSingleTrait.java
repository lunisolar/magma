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
public interface DoIfIntSingleTrait<SELF extends DoIfIntSingleTrait<SELF>> extends DoIfIntTrait<SELF>, IsIntTrait<SELF>, IntValueTrait<SELF> {

	// <editor-fold desc="doIf">

	default @Nonnull SELF doIf(@Nonnull LIntPredicate predicate, @Nonnull LIntConsumer action) {
		if (is(predicate))
			action.accept(value());
		return fluentCtx();
	}

	default @Nonnull SELF doIfNot(@Nonnull LIntPredicate predicate, @Nonnull LIntConsumer action) {
		if (isNot(predicate))
			action.accept(value());
		return fluentCtx();
	}

	default @Nonnull SELF doIf(int a2, @Nonnull LBiIntPredicate predicate, @Nonnull LIntConsumer action) {
		if (is(a2, predicate))
			action.accept(value());
		return fluentCtx();
	}

	/** Variant with reverse function-vs-arg order. */
	default @Nonnull SELF doIf(@Nonnull LBiIntPredicate predicate, int a2, @Nonnull LIntConsumer action) {
		return doIf(a2, predicate, action);
	}

	default @Nonnull SELF doIfNot(int a2, @Nonnull LBiIntPredicate predicate, @Nonnull LIntConsumer action) {
		if (isNot(a2, predicate))
			action.accept(value());
		return fluentCtx();
	}

	/** Variant with reverse function-vs-arg order. */
	default @Nonnull SELF doIfNot(@Nonnull LBiIntPredicate predicate, int a2, @Nonnull LIntConsumer action) {
		return doIfNot(a2, predicate, action);
	}

	default @Nonnull SELF doIf(int a2, int a3, @Nonnull LTriIntPredicate predicate, @Nonnull LIntConsumer action) {
		if (is(a2, a3, predicate))
			action.accept(value());
		return fluentCtx();
	}

	/** Variant with reverse function-vs-arg order. */
	default @Nonnull SELF doIf(@Nonnull LTriIntPredicate predicate, int a2, int a3, @Nonnull LIntConsumer action) {
		return doIf(a2, a3, predicate, action);
	}

	default @Nonnull SELF doIfNot(int a2, int a3, @Nonnull LTriIntPredicate predicate, @Nonnull LIntConsumer action) {
		if (isNot(a2, a3, predicate))
			action.accept(value());
		return fluentCtx();
	}

	/** Variant with reverse function-vs-arg order. */
	default @Nonnull SELF doIfNot(@Nonnull LTriIntPredicate predicate, int a2, int a3, @Nonnull LIntConsumer action) {
		return doIfNot(a2, a3, predicate, action);
	}

	default @Nonnull SELF doIfBool_(boolean v, @Nonnull LBoolIntPredicate.LIntBoolPred predicate, @Nonnull LIntConsumer action) {
		if (isBool_(v, predicate))
			action.accept(value());
		return fluentCtx();
	}

	/** Variant with reverse function-vs-arg order. */
	default @Nonnull SELF doIfBool_(@Nonnull LBoolIntPredicate.LIntBoolPred predicate, boolean v, @Nonnull LIntConsumer action) {
		return doIfBool_(v, predicate, action);
	}

	default @Nonnull SELF doIfNotBool_(boolean v, @Nonnull LBoolIntPredicate.LIntBoolPred predicate, @Nonnull LIntConsumer action) {
		if (isNotBool_(v, predicate))
			action.accept(value());
		return fluentCtx();
	}

	/** Variant with reverse function-vs-arg order. */
	default @Nonnull SELF doIfNotBool_(@Nonnull LBoolIntPredicate.LIntBoolPred predicate, boolean v, @Nonnull LIntConsumer action) {
		return doIfNotBool_(v, predicate, action);
	}

	default @Nonnull SELF doIfByte_(byte v, @Nonnull LByteIntPredicate.LIntBytePred predicate, @Nonnull LIntConsumer action) {
		if (isByte_(v, predicate))
			action.accept(value());
		return fluentCtx();
	}

	/** Variant with reverse function-vs-arg order. */
	default @Nonnull SELF doIfByte_(@Nonnull LByteIntPredicate.LIntBytePred predicate, byte v, @Nonnull LIntConsumer action) {
		return doIfByte_(v, predicate, action);
	}

	default @Nonnull SELF doIfNotByte_(byte v, @Nonnull LByteIntPredicate.LIntBytePred predicate, @Nonnull LIntConsumer action) {
		if (isNotByte_(v, predicate))
			action.accept(value());
		return fluentCtx();
	}

	/** Variant with reverse function-vs-arg order. */
	default @Nonnull SELF doIfNotByte_(@Nonnull LByteIntPredicate.LIntBytePred predicate, byte v, @Nonnull LIntConsumer action) {
		return doIfNotByte_(v, predicate, action);
	}

	default @Nonnull SELF doIfDbl_(double v, @Nonnull LDblIntPredicate.LIntDblPred predicate, @Nonnull LIntConsumer action) {
		if (isDbl_(v, predicate))
			action.accept(value());
		return fluentCtx();
	}

	/** Variant with reverse function-vs-arg order. */
	default @Nonnull SELF doIfDbl_(@Nonnull LDblIntPredicate.LIntDblPred predicate, double v, @Nonnull LIntConsumer action) {
		return doIfDbl_(v, predicate, action);
	}

	default @Nonnull SELF doIfNotDbl_(double v, @Nonnull LDblIntPredicate.LIntDblPred predicate, @Nonnull LIntConsumer action) {
		if (isNotDbl_(v, predicate))
			action.accept(value());
		return fluentCtx();
	}

	/** Variant with reverse function-vs-arg order. */
	default @Nonnull SELF doIfNotDbl_(@Nonnull LDblIntPredicate.LIntDblPred predicate, double v, @Nonnull LIntConsumer action) {
		return doIfNotDbl_(v, predicate, action);
	}

	default @Nonnull SELF doIfChar_(char v, @Nonnull LCharIntPredicate.LIntCharPred predicate, @Nonnull LIntConsumer action) {
		if (isChar_(v, predicate))
			action.accept(value());
		return fluentCtx();
	}

	/** Variant with reverse function-vs-arg order. */
	default @Nonnull SELF doIfChar_(@Nonnull LCharIntPredicate.LIntCharPred predicate, char v, @Nonnull LIntConsumer action) {
		return doIfChar_(v, predicate, action);
	}

	default @Nonnull SELF doIfNotChar_(char v, @Nonnull LCharIntPredicate.LIntCharPred predicate, @Nonnull LIntConsumer action) {
		if (isNotChar_(v, predicate))
			action.accept(value());
		return fluentCtx();
	}

	/** Variant with reverse function-vs-arg order. */
	default @Nonnull SELF doIfNotChar_(@Nonnull LCharIntPredicate.LIntCharPred predicate, char v, @Nonnull LIntConsumer action) {
		return doIfNotChar_(v, predicate, action);
	}

	default @Nonnull SELF doIfSrt_(short v, @Nonnull LSrtIntPredicate.LIntSrtPred predicate, @Nonnull LIntConsumer action) {
		if (isSrt_(v, predicate))
			action.accept(value());
		return fluentCtx();
	}

	/** Variant with reverse function-vs-arg order. */
	default @Nonnull SELF doIfSrt_(@Nonnull LSrtIntPredicate.LIntSrtPred predicate, short v, @Nonnull LIntConsumer action) {
		return doIfSrt_(v, predicate, action);
	}

	default @Nonnull SELF doIfNotSrt_(short v, @Nonnull LSrtIntPredicate.LIntSrtPred predicate, @Nonnull LIntConsumer action) {
		if (isNotSrt_(v, predicate))
			action.accept(value());
		return fluentCtx();
	}

	/** Variant with reverse function-vs-arg order. */
	default @Nonnull SELF doIfNotSrt_(@Nonnull LSrtIntPredicate.LIntSrtPred predicate, short v, @Nonnull LIntConsumer action) {
		return doIfNotSrt_(v, predicate, action);
	}

	default @Nonnull SELF doIfFlt_(float v, @Nonnull LFltIntPredicate.LIntFltPred predicate, @Nonnull LIntConsumer action) {
		if (isFlt_(v, predicate))
			action.accept(value());
		return fluentCtx();
	}

	/** Variant with reverse function-vs-arg order. */
	default @Nonnull SELF doIfFlt_(@Nonnull LFltIntPredicate.LIntFltPred predicate, float v, @Nonnull LIntConsumer action) {
		return doIfFlt_(v, predicate, action);
	}

	default @Nonnull SELF doIfNotFlt_(float v, @Nonnull LFltIntPredicate.LIntFltPred predicate, @Nonnull LIntConsumer action) {
		if (isNotFlt_(v, predicate))
			action.accept(value());
		return fluentCtx();
	}

	/** Variant with reverse function-vs-arg order. */
	default @Nonnull SELF doIfNotFlt_(@Nonnull LFltIntPredicate.LIntFltPred predicate, float v, @Nonnull LIntConsumer action) {
		return doIfNotFlt_(v, predicate, action);
	}

	default @Nonnull SELF doIfLong_(long v, @Nonnull LLongIntPredicate.LIntLongPred predicate, @Nonnull LIntConsumer action) {
		if (isLong_(v, predicate))
			action.accept(value());
		return fluentCtx();
	}

	/** Variant with reverse function-vs-arg order. */
	default @Nonnull SELF doIfLong_(@Nonnull LLongIntPredicate.LIntLongPred predicate, long v, @Nonnull LIntConsumer action) {
		return doIfLong_(v, predicate, action);
	}

	default @Nonnull SELF doIfNotLong_(long v, @Nonnull LLongIntPredicate.LIntLongPred predicate, @Nonnull LIntConsumer action) {
		if (isNotLong_(v, predicate))
			action.accept(value());
		return fluentCtx();
	}

	/** Variant with reverse function-vs-arg order. */
	default @Nonnull SELF doIfNotLong_(@Nonnull LLongIntPredicate.LIntLongPred predicate, long v, @Nonnull LIntConsumer action) {
		return doIfNotLong_(v, predicate, action);
	}

	default @Nonnull <V> SELF doIf_(V v, @Nonnull LObjIntPredicate.LIntObjPred<? super V> predicate, @Nonnull LIntConsumer action) {
		if (is_(v, predicate))
			action.accept(value());
		return fluentCtx();
	}

	/** Variant with reverse function-vs-arg order. */
	default @Nonnull <V> SELF doIf_(@Nonnull LObjIntPredicate.LIntObjPred<? super V> predicate, V v, @Nonnull LIntConsumer action) {
		return doIf_(v, predicate, action);
	}

	default @Nonnull <V> SELF doIfNot_(V v, @Nonnull LObjIntPredicate.LIntObjPred<? super V> predicate, @Nonnull LIntConsumer action) {
		if (isNot_(v, predicate))
			action.accept(value());
		return fluentCtx();
	}

	/** Variant with reverse function-vs-arg order. */
	default @Nonnull <V> SELF doIfNot_(@Nonnull LObjIntPredicate.LIntObjPred<? super V> predicate, V v, @Nonnull LIntConsumer action) {
		return doIfNot_(v, predicate, action);
	}

	default @Nonnull <V1> SELF doIfWithInt(V1 with1, @Nonnull LObjIntPredicate<? super V1> predicate, @Nonnull LIntConsumer action) {
		if (isWithInt(with1, predicate))
			action.accept(value());
		return fluentCtx();
	}

	/** Variant with reverse function-vs-arg order. */
	default @Nonnull <V1> SELF doIfWithInt(@Nonnull LObjIntPredicate<? super V1> predicate, V1 with1, @Nonnull LIntConsumer action) {
		return doIfWithInt(with1, predicate, action);
	}

	default @Nonnull <V1> SELF doIfNotWithInt(V1 with1, @Nonnull LObjIntPredicate<? super V1> predicate, @Nonnull LIntConsumer action) {
		if (isNotWithInt(with1, predicate))
			action.accept(value());
		return fluentCtx();
	}

	/** Variant with reverse function-vs-arg order. */
	default @Nonnull <V1> SELF doIfNotWithInt(@Nonnull LObjIntPredicate<? super V1> predicate, V1 with1, @Nonnull LIntConsumer action) {
		return doIfNotWithInt(with1, predicate, action);
	}

	default @Nonnull <V1, V2> SELF doIfWith(V1 with1, V2 with2, @Nonnull LBiObjIntPredicate<? super V1, ? super V2> predicate, @Nonnull LIntConsumer action) {
		if (isWith(with1, with2, predicate))
			action.accept(value());
		return fluentCtx();
	}

	/** Variant with reverse function-vs-arg order. */
	default @Nonnull <V1, V2> SELF doIfWith(@Nonnull LBiObjIntPredicate<? super V1, ? super V2> predicate, V1 with1, V2 with2, @Nonnull LIntConsumer action) {
		return doIfWith(with1, with2, predicate, action);
	}

	default @Nonnull <V1, V2> SELF doIfNotWith(V1 with1, V2 with2, @Nonnull LBiObjIntPredicate<? super V1, ? super V2> predicate, @Nonnull LIntConsumer action) {
		if (isNotWith(with1, with2, predicate))
			action.accept(value());
		return fluentCtx();
	}

	/** Variant with reverse function-vs-arg order. */
	default @Nonnull <V1, V2> SELF doIfNotWith(@Nonnull LBiObjIntPredicate<? super V1, ? super V2> predicate, V1 with1, V2 with2, @Nonnull LIntConsumer action) {
		return doIfNotWith(with1, with2, predicate, action);
	}

	// </editor-fold>
}
