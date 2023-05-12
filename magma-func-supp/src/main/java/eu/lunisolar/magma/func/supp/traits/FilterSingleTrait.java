/*
 * This file is part of "lunisolar-magma".
 *
 * (C) Copyright 2014-2023 Lunisolar (http://lunisolar.eu/).
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
 * Filter trait where there is at max just one value present and it could be voided or zeroed.
 */
public interface FilterSingleTrait<T, SELF extends FilterSingleTrait<T, SELF>> extends FilterTrait<T, SELF>, IsTrait<T, SELF> {

	// <editor-fold desc="is">

	@Override
	default @Nonnull SELF filter(@Nonnull LPredicate<? super T> predicate) {
		Null.nonNullArg(predicate, "predicate");
		return this.is(predicate) ? fluentCtx() : voidValue();
	}

	@Override
	default @Nonnull SELF uniFilter(T a2, @Nonnull LBiPredicate<? super T, ? super T> predicate) {
		Null.nonNullArg(predicate, "predicate");
		return this.uniIs(a2, predicate) ? fluentCtx() : voidValue();
	}

	@Override
	default @Nonnull SELF uniFilter(T a2, T a3, @Nonnull LTriPredicate<? super T, ? super T, ? super T> predicate) {
		Null.nonNullArg(predicate, "predicate");
		return this.uniIs(a2, a3, predicate) ? fluentCtx() : voidValue();
	}

	@Override
	default @Nonnull SELF uniFilter(T a2, T a3, T a4, @Nonnull LQuadPredicate<? super T, ? super T, ? super T, ? super T> predicate) {
		Null.nonNullArg(predicate, "predicate");
		return this.uniIs(a2, a3, a4, predicate) ? fluentCtx() : voidValue();
	}

	@Override
	default @Nonnull <V> SELF filterA(V[] a2, @Nonnull LBiPredicate<T, V[]> predicate) {
		Null.nonNullArg(predicate, "predicate");
		return this.isA(a2, predicate) ? fluentCtx() : voidValue();
	}

	@Override
	default @Nonnull SELF filterBool(boolean v, @Nonnull LObjBoolPredicate<? super T> predicate) {
		Null.nonNullArg(predicate, "predicate");
		return this.isBool(v, predicate) ? fluentCtx() : voidValue();
	}

	@Override
	default @Nonnull SELF filterByte(byte v, @Nonnull LObjBytePredicate<? super T> predicate) {
		Null.nonNullArg(predicate, "predicate");
		return this.isByte(v, predicate) ? fluentCtx() : voidValue();
	}

	@Override
	default @Nonnull SELF filterDbl(double v, @Nonnull LObjDblPredicate<? super T> predicate) {
		Null.nonNullArg(predicate, "predicate");
		return this.isDbl(v, predicate) ? fluentCtx() : voidValue();
	}

	@Override
	default @Nonnull SELF filterChar(char v, @Nonnull LObjCharPredicate<? super T> predicate) {
		Null.nonNullArg(predicate, "predicate");
		return this.isChar(v, predicate) ? fluentCtx() : voidValue();
	}

	@Override
	default @Nonnull SELF filterSrt(short v, @Nonnull LObjSrtPredicate<? super T> predicate) {
		Null.nonNullArg(predicate, "predicate");
		return this.isSrt(v, predicate) ? fluentCtx() : voidValue();
	}

	@Override
	default @Nonnull SELF filterFlt(float v, @Nonnull LObjFltPredicate<? super T> predicate) {
		Null.nonNullArg(predicate, "predicate");
		return this.isFlt(v, predicate) ? fluentCtx() : voidValue();
	}

	@Override
	default @Nonnull SELF filterInt(int v, @Nonnull LObjIntPredicate<? super T> predicate) {
		Null.nonNullArg(predicate, "predicate");
		return this.isInt(v, predicate) ? fluentCtx() : voidValue();
	}

	@Override
	default @Nonnull SELF filterLong(long v, @Nonnull LObjLongPredicate<? super T> predicate) {
		Null.nonNullArg(predicate, "predicate");
		return this.isLong(v, predicate) ? fluentCtx() : voidValue();
	}

	@Override
	default @Nonnull <V> SELF filter(V v, @Nonnull LBiPredicate<? super T, ? super V> predicate) {
		Null.nonNullArg(predicate, "predicate");
		return this.is(v, predicate) ? fluentCtx() : voidValue();
	}

	@Override
	default @Nonnull <V2, V3> SELF filter(V2 a2, V3 a3, @Nonnull LTriPredicate<? super T, ? super V2, ? super V3> predicate) {
		Null.nonNullArg(predicate, "predicate");
		return this.is(a2, a3, predicate) ? fluentCtx() : voidValue();
	}

	@Override
	default @Nonnull <V2, V3, V4> SELF filter(V2 a2, V3 a3, V4 a4, @Nonnull LQuadPredicate<? super T, ? super V2, ? super V3, ? super V4> predicate) {
		Null.nonNullArg(predicate, "predicate");
		return this.is(a2, a3, a4, predicate) ? fluentCtx() : voidValue();
	}

	@Override
	default @Nonnull <V1> SELF filterWith(V1 with1, @Nonnull LBiPredicate<? super V1, ? super T> predicate) {
		Null.nonNullArg(predicate, "predicate");
		return this.isWith(with1, predicate) ? fluentCtx() : voidValue();
	}

	@Override
	default @Nonnull <V1, V2> SELF filterWith(V1 with1, V2 with2, @Nonnull LTriPredicate<? super V1, ? super V2, ? super T> predicate) {
		Null.nonNullArg(predicate, "predicate");
		return this.isWith(with1, with2, predicate) ? fluentCtx() : voidValue();
	}

	@Override
	default @Nonnull SELF uniFilterWith(T with, @Nonnull LBiPredicate<? super T, ? super T> predicate) {
		Null.nonNullArg(predicate, "predicate");
		return this.uniIsWith(with, predicate) ? fluentCtx() : voidValue();
	}

	// </editor-fold>
}
