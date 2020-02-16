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
public interface FilterTrait<T, SELF extends FilterTrait<T, SELF>> extends FluentTrait<SELF> {

	// <editor-fold desc="filtering">

	public SELF filter(@Nonnull LPredicate<? super T> predicate);

	/** Variant 'obj.filter(..., (...) -> { ..long multiline definition.. })' */
	default @Nonnull SELF uniFilter(T a2, @Nonnull LBiPredicate<? super T, ? super T> predicate) {
		return filter(a -> predicate.test(a, a2));
	}

	/** Variant 'obj.filter(Is::equal, ...)' or 'opt.filter(Does::contain, ...)', etc.  */
	default @Nonnull SELF uniFilter(@Nonnull LBiPredicate<? super T, ? super T> predicate, T a2) {
		return uniFilter(a2, predicate);
	}

	/** Variant 'obj.filter(..., (...) -> { ..long multiline definition.. })' */
	default @Nonnull SELF uniFilter(T a2, T a3, @Nonnull LTriPredicate<? super T, ? super T, ? super T> predicate) {
		return filter(a -> predicate.test(a, a2, a3));
	}

	/** Variant 'obj.filter(Is::equal, ...)' or 'opt.filter(Does::contain, ...)', etc.  */
	default @Nonnull SELF uniFilter(@Nonnull LTriPredicate<? super T, ? super T, ? super T> predicate, T a2, T a3) {
		return uniFilter(a2, a3, predicate);
	}

	/** Variant 'obj.filter(..., (...) -> { ..long multiline definition.. })' */
	default @Nonnull SELF uniFilter(T a2, T a3, T a4, @Nonnull LQuadPredicate<? super T, ? super T, ? super T, ? super T> predicate) {
		return filter(a -> predicate.test(a, a2, a3, a4));
	}

	/** Variant 'obj.filter(Is::equal, ...)' or 'opt.filter(Does::contain, ...)', etc.  */
	default @Nonnull SELF uniFilter(@Nonnull LQuadPredicate<? super T, ? super T, ? super T, ? super T> predicate, T a2, T a3, T a4) {
		return uniFilter(a2, a3, a4, predicate);
	}

	/** Variant 'obj.filter(..., (...) -> { ..long multiline definition.. })' */
	default @Nonnull SELF filterBool(boolean v, @Nonnull LObjBoolPredicate<? super T> predicate) {
		return filter(a -> predicate.test(a, v));
	}

	/** Variant 'obj.filter(Is::equal, ...)' or 'opt.filter(Does::contain, ...)', etc.  */
	default @Nonnull SELF filterBool(@Nonnull LObjBoolPredicate<? super T> predicate, boolean v) {
		return filterBool(v, predicate);
	}

	/** Variant 'obj.filter(..., (...) -> { ..long multiline definition.. })' */
	default @Nonnull SELF filterByte(byte v, @Nonnull LObjBytePredicate<? super T> predicate) {
		return filter(a -> predicate.test(a, v));
	}

	/** Variant 'obj.filter(Is::equal, ...)' or 'opt.filter(Does::contain, ...)', etc.  */
	default @Nonnull SELF filterByte(@Nonnull LObjBytePredicate<? super T> predicate, byte v) {
		return filterByte(v, predicate);
	}

	/** Variant 'obj.filter(..., (...) -> { ..long multiline definition.. })' */
	default @Nonnull SELF filterDbl(double v, @Nonnull LObjDblPredicate<? super T> predicate) {
		return filter(a -> predicate.test(a, v));
	}

	/** Variant 'obj.filter(Is::equal, ...)' or 'opt.filter(Does::contain, ...)', etc.  */
	default @Nonnull SELF filterDbl(@Nonnull LObjDblPredicate<? super T> predicate, double v) {
		return filterDbl(v, predicate);
	}

	/** Variant 'obj.filter(..., (...) -> { ..long multiline definition.. })' */
	default @Nonnull SELF filterChar(char v, @Nonnull LObjCharPredicate<? super T> predicate) {
		return filter(a -> predicate.test(a, v));
	}

	/** Variant 'obj.filter(Is::equal, ...)' or 'opt.filter(Does::contain, ...)', etc.  */
	default @Nonnull SELF filterChar(@Nonnull LObjCharPredicate<? super T> predicate, char v) {
		return filterChar(v, predicate);
	}

	/** Variant 'obj.filter(..., (...) -> { ..long multiline definition.. })' */
	default @Nonnull SELF filterSrt(short v, @Nonnull LObjSrtPredicate<? super T> predicate) {
		return filter(a -> predicate.test(a, v));
	}

	/** Variant 'obj.filter(Is::equal, ...)' or 'opt.filter(Does::contain, ...)', etc.  */
	default @Nonnull SELF filterSrt(@Nonnull LObjSrtPredicate<? super T> predicate, short v) {
		return filterSrt(v, predicate);
	}

	/** Variant 'obj.filter(..., (...) -> { ..long multiline definition.. })' */
	default @Nonnull SELF filterFlt(float v, @Nonnull LObjFltPredicate<? super T> predicate) {
		return filter(a -> predicate.test(a, v));
	}

	/** Variant 'obj.filter(Is::equal, ...)' or 'opt.filter(Does::contain, ...)', etc.  */
	default @Nonnull SELF filterFlt(@Nonnull LObjFltPredicate<? super T> predicate, float v) {
		return filterFlt(v, predicate);
	}

	/** Variant 'obj.filter(..., (...) -> { ..long multiline definition.. })' */
	default @Nonnull SELF filterInt(int v, @Nonnull LObjIntPredicate<? super T> predicate) {
		return filter(a -> predicate.test(a, v));
	}

	/** Variant 'obj.filter(Is::equal, ...)' or 'opt.filter(Does::contain, ...)', etc.  */
	default @Nonnull SELF filterInt(@Nonnull LObjIntPredicate<? super T> predicate, int v) {
		return filterInt(v, predicate);
	}

	/** Variant 'obj.filter(..., (...) -> { ..long multiline definition.. })' */
	default @Nonnull SELF filterLong(long v, @Nonnull LObjLongPredicate<? super T> predicate) {
		return filter(a -> predicate.test(a, v));
	}

	/** Variant 'obj.filter(Is::equal, ...)' or 'opt.filter(Does::contain, ...)', etc.  */
	default @Nonnull SELF filterLong(@Nonnull LObjLongPredicate<? super T> predicate, long v) {
		return filterLong(v, predicate);
	}

	/** Variant 'obj.filter(..., (...) -> { ..long multiline definition.. })' */
	default @Nonnull <V> SELF filter(V v, @Nonnull LBiPredicate<? super T, ? super V> predicate) {
		return filter(a -> predicate.test(a, v));
	}

	/** Variant 'obj.filter(Is::equal, ...)' or 'opt.filter(Does::contain, ...)', etc.  */
	default @Nonnull <V> SELF filter(@Nonnull LBiPredicate<? super T, ? super V> predicate, V v) {
		return filter(v, predicate);
	}

	/** Variant 'obj.filter(..., (...) -> { ..long multiline definition.. })' */
	default @Nonnull <V2, V3> SELF filter(V2 a2, V3 a3, @Nonnull LTriPredicate<? super T, ? super V2, ? super V3> predicate) {
		return filter(a -> predicate.test(a, a2, a3));
	}

	/** Variant 'obj.filter(Is::equal, ...)' or 'opt.filter(Does::contain, ...)', etc.  */
	default @Nonnull <V2, V3> SELF filter(@Nonnull LTriPredicate<? super T, ? super V2, ? super V3> predicate, V2 a2, V3 a3) {
		return filter(a2, a3, predicate);
	}

	/** Variant 'obj.filter(..., (...) -> { ..long multiline definition.. })' */
	default @Nonnull <V1> SELF filterWith(V1 with, @Nonnull LBiPredicate<? super V1, ? super T> predicate) {
		return filter(a -> predicate.test(with, a));
	}

	/** Variant 'obj.filter(Is::equal, ...)' or 'opt.filter(Does::contain, ...)', etc.  */
	default @Nonnull <V1> SELF filterWith(@Nonnull LBiPredicate<? super V1, ? super T> predicate, V1 with) {
		return filterWith(with, predicate);
	}

	/** Variant 'obj.filter(..., (...) -> { ..long multiline definition.. })' */
	default @Nonnull SELF uniFilterWith(T with, @Nonnull LBiPredicate<? super T, ? super T> predicate) {
		return filter(a -> predicate.test(with, a));
	}

	/** Variant 'obj.filter(Is::equal, ...)' or 'opt.filter(Does::contain, ...)', etc.  */
	default @Nonnull SELF uniFilterWith(@Nonnull LBiPredicate<? super T, ? super T> predicate, T with) {
		return uniFilterWith(with, predicate);
	}

	// </editor-fold>
}
