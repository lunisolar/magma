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
public interface DoIfIntSingleTrait<SELF extends DoIfIntSingleTrait<SELF>> extends LIntSingle, DoIfIntTrait<SELF>, IsIntTrait<SELF>, IntValueTrait<SELF> {

	// <editor-fold desc="doIf">

	default @Nonnull SELF doIf(@Nonnull LIntPredicate predicate, @Nonnull LIntConsumer action) {
		if (is(predicate))
			action.accept(value());
		return self();
	}

	default @Nonnull SELF doIfNot(@Nonnull LIntPredicate predicate, @Nonnull LIntConsumer action) {
		if (isNot(predicate))
			action.accept(value());
		return self();
	}

	default @Nonnull SELF doIf(int a2, @Nonnull LBiIntPredicate predicate, @Nonnull LIntConsumer action) {
		if (is(a2, predicate))
			action.accept(value());
		return self();
	}

	/** Variant with reverse predicate-vs-arg order. */
	default @Nonnull SELF doIf(@Nonnull LBiIntPredicate predicate, int a2, @Nonnull LIntConsumer action) {
		return doIf(a2, predicate, action);
	}

	default @Nonnull SELF doIfNot(int a2, @Nonnull LBiIntPredicate predicate, @Nonnull LIntConsumer action) {
		if (isNot(a2, predicate))
			action.accept(value());
		return self();
	}

	/** Variant with reverse predicate-vs-arg order. */
	default @Nonnull SELF doIfNot(@Nonnull LBiIntPredicate predicate, int a2, @Nonnull LIntConsumer action) {
		return doIfNot(a2, predicate, action);
	}

	default @Nonnull SELF doIf(int a2, int a3, @Nonnull LTriIntPredicate predicate, @Nonnull LIntConsumer action) {
		if (is(a2, a3, predicate))
			action.accept(value());
		return self();
	}

	/** Variant with reverse predicate-vs-arg order. */
	default @Nonnull SELF doIf(@Nonnull LTriIntPredicate predicate, int a2, int a3, @Nonnull LIntConsumer action) {
		return doIf(a2, a3, predicate, action);
	}

	default @Nonnull SELF doIfNot(int a2, int a3, @Nonnull LTriIntPredicate predicate, @Nonnull LIntConsumer action) {
		if (isNot(a2, a3, predicate))
			action.accept(value());
		return self();
	}

	/** Variant with reverse predicate-vs-arg order. */
	default @Nonnull SELF doIfNot(@Nonnull LTriIntPredicate predicate, int a2, int a3, @Nonnull LIntConsumer action) {
		return doIfNot(a2, a3, predicate, action);
	}

	default @Nonnull SELF doIf_Bool(boolean v, @Nonnull LBoolIntPredicate.LIntBoolPred predicate, @Nonnull LIntConsumer action) {
		if (is_Bool(v, predicate))
			action.accept(value());
		return self();
	}

	/** Variant with reverse predicate-vs-arg order. */
	default @Nonnull SELF doIf_Bool(@Nonnull LBoolIntPredicate.LIntBoolPred predicate, boolean v, @Nonnull LIntConsumer action) {
		return doIf_Bool(v, predicate, action);
	}

	default @Nonnull SELF doIfNot_Bool(boolean v, @Nonnull LBoolIntPredicate.LIntBoolPred predicate, @Nonnull LIntConsumer action) {
		if (isNot_Bool(v, predicate))
			action.accept(value());
		return self();
	}

	/** Variant with reverse predicate-vs-arg order. */
	default @Nonnull SELF doIfNot_Bool(@Nonnull LBoolIntPredicate.LIntBoolPred predicate, boolean v, @Nonnull LIntConsumer action) {
		return doIfNot_Bool(v, predicate, action);
	}

	default @Nonnull SELF doIf_Byte(byte v, @Nonnull LByteIntPredicate.LIntBytePred predicate, @Nonnull LIntConsumer action) {
		if (is_Byte(v, predicate))
			action.accept(value());
		return self();
	}

	/** Variant with reverse predicate-vs-arg order. */
	default @Nonnull SELF doIf_Byte(@Nonnull LByteIntPredicate.LIntBytePred predicate, byte v, @Nonnull LIntConsumer action) {
		return doIf_Byte(v, predicate, action);
	}

	default @Nonnull SELF doIfNot_Byte(byte v, @Nonnull LByteIntPredicate.LIntBytePred predicate, @Nonnull LIntConsumer action) {
		if (isNot_Byte(v, predicate))
			action.accept(value());
		return self();
	}

	/** Variant with reverse predicate-vs-arg order. */
	default @Nonnull SELF doIfNot_Byte(@Nonnull LByteIntPredicate.LIntBytePred predicate, byte v, @Nonnull LIntConsumer action) {
		return doIfNot_Byte(v, predicate, action);
	}

	default @Nonnull SELF doIf_Dbl(double v, @Nonnull LDblIntPredicate.LIntDblPred predicate, @Nonnull LIntConsumer action) {
		if (is_Dbl(v, predicate))
			action.accept(value());
		return self();
	}

	/** Variant with reverse predicate-vs-arg order. */
	default @Nonnull SELF doIf_Dbl(@Nonnull LDblIntPredicate.LIntDblPred predicate, double v, @Nonnull LIntConsumer action) {
		return doIf_Dbl(v, predicate, action);
	}

	default @Nonnull SELF doIfNot_Dbl(double v, @Nonnull LDblIntPredicate.LIntDblPred predicate, @Nonnull LIntConsumer action) {
		if (isNot_Dbl(v, predicate))
			action.accept(value());
		return self();
	}

	/** Variant with reverse predicate-vs-arg order. */
	default @Nonnull SELF doIfNot_Dbl(@Nonnull LDblIntPredicate.LIntDblPred predicate, double v, @Nonnull LIntConsumer action) {
		return doIfNot_Dbl(v, predicate, action);
	}

	default @Nonnull SELF doIf_Char(char v, @Nonnull LCharIntPredicate.LIntCharPred predicate, @Nonnull LIntConsumer action) {
		if (is_Char(v, predicate))
			action.accept(value());
		return self();
	}

	/** Variant with reverse predicate-vs-arg order. */
	default @Nonnull SELF doIf_Char(@Nonnull LCharIntPredicate.LIntCharPred predicate, char v, @Nonnull LIntConsumer action) {
		return doIf_Char(v, predicate, action);
	}

	default @Nonnull SELF doIfNot_Char(char v, @Nonnull LCharIntPredicate.LIntCharPred predicate, @Nonnull LIntConsumer action) {
		if (isNot_Char(v, predicate))
			action.accept(value());
		return self();
	}

	/** Variant with reverse predicate-vs-arg order. */
	default @Nonnull SELF doIfNot_Char(@Nonnull LCharIntPredicate.LIntCharPred predicate, char v, @Nonnull LIntConsumer action) {
		return doIfNot_Char(v, predicate, action);
	}

	default @Nonnull SELF doIf_Srt(short v, @Nonnull LSrtIntPredicate.LIntSrtPred predicate, @Nonnull LIntConsumer action) {
		if (is_Srt(v, predicate))
			action.accept(value());
		return self();
	}

	/** Variant with reverse predicate-vs-arg order. */
	default @Nonnull SELF doIf_Srt(@Nonnull LSrtIntPredicate.LIntSrtPred predicate, short v, @Nonnull LIntConsumer action) {
		return doIf_Srt(v, predicate, action);
	}

	default @Nonnull SELF doIfNot_Srt(short v, @Nonnull LSrtIntPredicate.LIntSrtPred predicate, @Nonnull LIntConsumer action) {
		if (isNot_Srt(v, predicate))
			action.accept(value());
		return self();
	}

	/** Variant with reverse predicate-vs-arg order. */
	default @Nonnull SELF doIfNot_Srt(@Nonnull LSrtIntPredicate.LIntSrtPred predicate, short v, @Nonnull LIntConsumer action) {
		return doIfNot_Srt(v, predicate, action);
	}

	default @Nonnull SELF doIf_Flt(float v, @Nonnull LFltIntPredicate.LIntFltPred predicate, @Nonnull LIntConsumer action) {
		if (is_Flt(v, predicate))
			action.accept(value());
		return self();
	}

	/** Variant with reverse predicate-vs-arg order. */
	default @Nonnull SELF doIf_Flt(@Nonnull LFltIntPredicate.LIntFltPred predicate, float v, @Nonnull LIntConsumer action) {
		return doIf_Flt(v, predicate, action);
	}

	default @Nonnull SELF doIfNot_Flt(float v, @Nonnull LFltIntPredicate.LIntFltPred predicate, @Nonnull LIntConsumer action) {
		if (isNot_Flt(v, predicate))
			action.accept(value());
		return self();
	}

	/** Variant with reverse predicate-vs-arg order. */
	default @Nonnull SELF doIfNot_Flt(@Nonnull LFltIntPredicate.LIntFltPred predicate, float v, @Nonnull LIntConsumer action) {
		return doIfNot_Flt(v, predicate, action);
	}

	default @Nonnull SELF doIf_Long(long v, @Nonnull LLongIntPredicate.LIntLongPred predicate, @Nonnull LIntConsumer action) {
		if (is_Long(v, predicate))
			action.accept(value());
		return self();
	}

	/** Variant with reverse predicate-vs-arg order. */
	default @Nonnull SELF doIf_Long(@Nonnull LLongIntPredicate.LIntLongPred predicate, long v, @Nonnull LIntConsumer action) {
		return doIf_Long(v, predicate, action);
	}

	default @Nonnull SELF doIfNot_Long(long v, @Nonnull LLongIntPredicate.LIntLongPred predicate, @Nonnull LIntConsumer action) {
		if (isNot_Long(v, predicate))
			action.accept(value());
		return self();
	}

	/** Variant with reverse predicate-vs-arg order. */
	default @Nonnull SELF doIfNot_Long(@Nonnull LLongIntPredicate.LIntLongPred predicate, long v, @Nonnull LIntConsumer action) {
		return doIfNot_Long(v, predicate, action);
	}

	default @Nonnull <V> SELF doIf_(V v, @Nonnull LObjIntPredicate.LIntObjPred<? super V> predicate, @Nonnull LIntConsumer action) {
		if (is_(v, predicate))
			action.accept(value());
		return self();
	}

	/** Variant with reverse predicate-vs-arg order. */
	default @Nonnull <V> SELF doIf_(@Nonnull LObjIntPredicate.LIntObjPred<? super V> predicate, V v, @Nonnull LIntConsumer action) {
		return doIf_(v, predicate, action);
	}

	default @Nonnull <V> SELF doIfNot_(V v, @Nonnull LObjIntPredicate.LIntObjPred<? super V> predicate, @Nonnull LIntConsumer action) {
		if (isNot_(v, predicate))
			action.accept(value());
		return self();
	}

	/** Variant with reverse predicate-vs-arg order. */
	default @Nonnull <V> SELF doIfNot_(@Nonnull LObjIntPredicate.LIntObjPred<? super V> predicate, V v, @Nonnull LIntConsumer action) {
		return doIfNot_(v, predicate, action);
	}

	default @Nonnull <V1> SELF doIfWithInt(V1 with, @Nonnull LObjIntPredicate<? super V1> predicate, @Nonnull LIntConsumer action) {
		if (isWithInt(with, predicate))
			action.accept(value());
		return self();
	}

	/** Variant with reverse predicate-vs-arg order. */
	default @Nonnull <V1> SELF doIfWithInt(@Nonnull LObjIntPredicate<? super V1> predicate, V1 with, @Nonnull LIntConsumer action) {
		return doIfWithInt(with, predicate, action);
	}

	default @Nonnull <V1> SELF doIfNotWithInt(V1 with, @Nonnull LObjIntPredicate<? super V1> predicate, @Nonnull LIntConsumer action) {
		if (isNotWithInt(with, predicate))
			action.accept(value());
		return self();
	}

	/** Variant with reverse predicate-vs-arg order. */
	default @Nonnull <V1> SELF doIfNotWithInt(@Nonnull LObjIntPredicate<? super V1> predicate, V1 with, @Nonnull LIntConsumer action) {
		return doIfNotWithInt(with, predicate, action);
	}

	// </editor-fold>
}
