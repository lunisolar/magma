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
public interface FilterIntSingleTrait<SELF extends FilterIntSingleTrait<SELF>> extends FilterIntTrait<SELF>, IsIntTrait<SELF> {

	// <editor-fold desc="is">

	@Override
	default @Nonnull SELF filter(@Nonnull LIntPredicate predicate) {
		Null.nonNullArg(predicate, "predicate");
		return this.is(predicate) ? self() : voidValue();
	}

	@Override
	default @Nonnull SELF filter(int a2, @Nonnull LBiIntPredicate predicate) {
		Null.nonNullArg(predicate, "predicate");
		return this.is(a2, predicate) ? self() : voidValue();
	}

	@Override
	default @Nonnull SELF filter(int a2, int a3, @Nonnull LTriIntPredicate predicate) {
		Null.nonNullArg(predicate, "predicate");
		return this.is(a2, a3, predicate) ? self() : voidValue();
	}

	@Override
	default @Nonnull SELF filterBool_(boolean v, @Nonnull LBoolIntPredicate.LIntBoolPred predicate) {
		Null.nonNullArg(predicate, "predicate");
		return this.isBool_(v, predicate) ? self() : voidValue();
	}

	@Override
	default @Nonnull SELF filterByte_(byte v, @Nonnull LByteIntPredicate.LIntBytePred predicate) {
		Null.nonNullArg(predicate, "predicate");
		return this.isByte_(v, predicate) ? self() : voidValue();
	}

	@Override
	default @Nonnull SELF filterDbl_(double v, @Nonnull LDblIntPredicate.LIntDblPred predicate) {
		Null.nonNullArg(predicate, "predicate");
		return this.isDbl_(v, predicate) ? self() : voidValue();
	}

	@Override
	default @Nonnull SELF filterChar_(char v, @Nonnull LCharIntPredicate.LIntCharPred predicate) {
		Null.nonNullArg(predicate, "predicate");
		return this.isChar_(v, predicate) ? self() : voidValue();
	}

	@Override
	default @Nonnull SELF filterSrt_(short v, @Nonnull LSrtIntPredicate.LIntSrtPred predicate) {
		Null.nonNullArg(predicate, "predicate");
		return this.isSrt_(v, predicate) ? self() : voidValue();
	}

	@Override
	default @Nonnull SELF filterFlt_(float v, @Nonnull LFltIntPredicate.LIntFltPred predicate) {
		Null.nonNullArg(predicate, "predicate");
		return this.isFlt_(v, predicate) ? self() : voidValue();
	}

	@Override
	default @Nonnull SELF filterLong_(long v, @Nonnull LLongIntPredicate.LIntLongPred predicate) {
		Null.nonNullArg(predicate, "predicate");
		return this.isLong_(v, predicate) ? self() : voidValue();
	}

	@Override
	default @Nonnull <V> SELF filter_(V v, @Nonnull LObjIntPredicate.LIntObjPred<? super V> predicate) {
		Null.nonNullArg(predicate, "predicate");
		return this.is_(v, predicate) ? self() : voidValue();
	}

	@Override
	default @Nonnull <V1> SELF filterWithInt(V1 with1, @Nonnull LObjIntPredicate<? super V1> predicate) {
		Null.nonNullArg(predicate, "predicate");
		return this.isWithInt(with1, predicate) ? self() : voidValue();
	}

	@Override
	default @Nonnull <V1, V2> SELF filterWith(V1 with1, V2 with2, @Nonnull LBiObjIntPredicate<? super V1, ? super V2> predicate) {
		Null.nonNullArg(predicate, "predicate");
		return this.isWith(with1, with2, predicate) ? self() : voidValue();
	}

	// </editor-fold>
}
