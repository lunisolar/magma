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
public interface DoIfTrait<T, SELF extends DoIfTrait<T, SELF>> extends FluentTrait<SELF> {

	default <R> SELF doIf(@Nonnull Class<R> clazz, @Nonnull LConsumer<R> action) {
		Null.nonNullArg(clazz, "clazz");
		Null.nonNullArg(action, "action");
		return (SELF) doIf(clazz::isInstance, (LConsumer) action);
	}

	//<editor-fold desc="doIf">

	public @Nonnull SELF doIf(@Nonnull LPredicate<? super T> predicate, @Nonnull LConsumer<? super T> action);

	public @Nonnull SELF doIfNot(@Nonnull LPredicate<? super T> predicate, @Nonnull LConsumer<? super T> action);

	default @Nonnull SELF uniDoIf(T a2, @Nonnull LBiPredicate<? super T, ? super T> predicate, @Nonnull LConsumer<? super T> action) {
		return doIf(a -> predicate.test(a, a2), action);
	}

	/** Variant with reverse function-vs-arg order. */
	default @Nonnull SELF uniDoIf(@Nonnull LBiPredicate<? super T, ? super T> predicate, T a2, @Nonnull LConsumer<? super T> action) {
		return uniDoIf(a2, predicate, action);
	}

	default @Nonnull SELF uniDoIfNot(T a2, @Nonnull LBiPredicate<? super T, ? super T> predicate, @Nonnull LConsumer<? super T> action) {
		return doIfNot(a -> predicate.test(a, a2), action);
	}

	/** Variant with reverse function-vs-arg order. */
	default @Nonnull SELF uniDoIfNot(@Nonnull LBiPredicate<? super T, ? super T> predicate, T a2, @Nonnull LConsumer<? super T> action) {
		return uniDoIfNot(a2, predicate, action);
	}

	default @Nonnull SELF uniDoIf(T a2, T a3, @Nonnull LTriPredicate<? super T, ? super T, ? super T> predicate, @Nonnull LConsumer<? super T> action) {
		return doIf(a -> predicate.test(a, a2, a3), action);
	}

	/** Variant with reverse function-vs-arg order. */
	default @Nonnull SELF uniDoIf(@Nonnull LTriPredicate<? super T, ? super T, ? super T> predicate, T a2, T a3, @Nonnull LConsumer<? super T> action) {
		return uniDoIf(a2, a3, predicate, action);
	}

	default @Nonnull SELF uniDoIfNot(T a2, T a3, @Nonnull LTriPredicate<? super T, ? super T, ? super T> predicate, @Nonnull LConsumer<? super T> action) {
		return doIfNot(a -> predicate.test(a, a2, a3), action);
	}

	/** Variant with reverse function-vs-arg order. */
	default @Nonnull SELF uniDoIfNot(@Nonnull LTriPredicate<? super T, ? super T, ? super T> predicate, T a2, T a3, @Nonnull LConsumer<? super T> action) {
		return uniDoIfNot(a2, a3, predicate, action);
	}

	default @Nonnull SELF uniDoIf(T a2, T a3, T a4, @Nonnull LQuadPredicate<? super T, ? super T, ? super T, ? super T> predicate, @Nonnull LConsumer<? super T> action) {
		return doIf(a -> predicate.test(a, a2, a3, a4), action);
	}

	/** Variant with reverse function-vs-arg order. */
	default @Nonnull SELF uniDoIf(@Nonnull LQuadPredicate<? super T, ? super T, ? super T, ? super T> predicate, T a2, T a3, T a4, @Nonnull LConsumer<? super T> action) {
		return uniDoIf(a2, a3, a4, predicate, action);
	}

	default @Nonnull SELF uniDoIfNot(T a2, T a3, T a4, @Nonnull LQuadPredicate<? super T, ? super T, ? super T, ? super T> predicate, @Nonnull LConsumer<? super T> action) {
		return doIfNot(a -> predicate.test(a, a2, a3, a4), action);
	}

	/** Variant with reverse function-vs-arg order. */
	default @Nonnull SELF uniDoIfNot(@Nonnull LQuadPredicate<? super T, ? super T, ? super T, ? super T> predicate, T a2, T a3, T a4, @Nonnull LConsumer<? super T> action) {
		return uniDoIfNot(a2, a3, a4, predicate, action);
	}

	default @Nonnull <V> SELF doIfA(V[] a2, @Nonnull LBiPredicate<T, V[]> predicate, @Nonnull LConsumer<? super T> action) {
		return doIf(a -> predicate.test(a, a2), action);
	}

	/** Variant with reverse function-vs-arg order. */
	default @Nonnull <V> SELF doIfA(@Nonnull LBiPredicate<T, V[]> predicate, V[] a2, @Nonnull LConsumer<? super T> action) {
		return doIfA(a2, predicate, action);
	}

	default @Nonnull <V> SELF doIfNotA(V[] a2, @Nonnull LBiPredicate<T, V[]> predicate, @Nonnull LConsumer<? super T> action) {
		return doIfNot(a -> predicate.test(a, a2), action);
	}

	/** Variant with reverse function-vs-arg order. */
	default @Nonnull <V> SELF doIfNotA(@Nonnull LBiPredicate<T, V[]> predicate, V[] a2, @Nonnull LConsumer<? super T> action) {
		return doIfNotA(a2, predicate, action);
	}

	default @Nonnull SELF doIfBool(boolean v, @Nonnull LObjBoolPredicate<? super T> predicate, @Nonnull LConsumer<? super T> action) {
		return doIf(a -> predicate.test(a, v), action);
	}

	/** Variant with reverse function-vs-arg order. */
	default @Nonnull SELF doIfBool(@Nonnull LObjBoolPredicate<? super T> predicate, boolean v, @Nonnull LConsumer<? super T> action) {
		return doIfBool(v, predicate, action);
	}

	default @Nonnull SELF doIfNotBool(boolean v, @Nonnull LObjBoolPredicate<? super T> predicate, @Nonnull LConsumer<? super T> action) {
		return doIfNot(a -> predicate.test(a, v), action);
	}

	/** Variant with reverse function-vs-arg order. */
	default @Nonnull SELF doIfNotBool(@Nonnull LObjBoolPredicate<? super T> predicate, boolean v, @Nonnull LConsumer<? super T> action) {
		return doIfNotBool(v, predicate, action);
	}

	default @Nonnull SELF doIfByte(byte v, @Nonnull LObjBytePredicate<? super T> predicate, @Nonnull LConsumer<? super T> action) {
		return doIf(a -> predicate.test(a, v), action);
	}

	/** Variant with reverse function-vs-arg order. */
	default @Nonnull SELF doIfByte(@Nonnull LObjBytePredicate<? super T> predicate, byte v, @Nonnull LConsumer<? super T> action) {
		return doIfByte(v, predicate, action);
	}

	default @Nonnull SELF doIfNotByte(byte v, @Nonnull LObjBytePredicate<? super T> predicate, @Nonnull LConsumer<? super T> action) {
		return doIfNot(a -> predicate.test(a, v), action);
	}

	/** Variant with reverse function-vs-arg order. */
	default @Nonnull SELF doIfNotByte(@Nonnull LObjBytePredicate<? super T> predicate, byte v, @Nonnull LConsumer<? super T> action) {
		return doIfNotByte(v, predicate, action);
	}

	default @Nonnull SELF doIfDbl(double v, @Nonnull LObjDblPredicate<? super T> predicate, @Nonnull LConsumer<? super T> action) {
		return doIf(a -> predicate.test(a, v), action);
	}

	/** Variant with reverse function-vs-arg order. */
	default @Nonnull SELF doIfDbl(@Nonnull LObjDblPredicate<? super T> predicate, double v, @Nonnull LConsumer<? super T> action) {
		return doIfDbl(v, predicate, action);
	}

	default @Nonnull SELF doIfNotDbl(double v, @Nonnull LObjDblPredicate<? super T> predicate, @Nonnull LConsumer<? super T> action) {
		return doIfNot(a -> predicate.test(a, v), action);
	}

	/** Variant with reverse function-vs-arg order. */
	default @Nonnull SELF doIfNotDbl(@Nonnull LObjDblPredicate<? super T> predicate, double v, @Nonnull LConsumer<? super T> action) {
		return doIfNotDbl(v, predicate, action);
	}

	default @Nonnull SELF doIfChar(char v, @Nonnull LObjCharPredicate<? super T> predicate, @Nonnull LConsumer<? super T> action) {
		return doIf(a -> predicate.test(a, v), action);
	}

	/** Variant with reverse function-vs-arg order. */
	default @Nonnull SELF doIfChar(@Nonnull LObjCharPredicate<? super T> predicate, char v, @Nonnull LConsumer<? super T> action) {
		return doIfChar(v, predicate, action);
	}

	default @Nonnull SELF doIfNotChar(char v, @Nonnull LObjCharPredicate<? super T> predicate, @Nonnull LConsumer<? super T> action) {
		return doIfNot(a -> predicate.test(a, v), action);
	}

	/** Variant with reverse function-vs-arg order. */
	default @Nonnull SELF doIfNotChar(@Nonnull LObjCharPredicate<? super T> predicate, char v, @Nonnull LConsumer<? super T> action) {
		return doIfNotChar(v, predicate, action);
	}

	default @Nonnull SELF doIfSrt(short v, @Nonnull LObjSrtPredicate<? super T> predicate, @Nonnull LConsumer<? super T> action) {
		return doIf(a -> predicate.test(a, v), action);
	}

	/** Variant with reverse function-vs-arg order. */
	default @Nonnull SELF doIfSrt(@Nonnull LObjSrtPredicate<? super T> predicate, short v, @Nonnull LConsumer<? super T> action) {
		return doIfSrt(v, predicate, action);
	}

	default @Nonnull SELF doIfNotSrt(short v, @Nonnull LObjSrtPredicate<? super T> predicate, @Nonnull LConsumer<? super T> action) {
		return doIfNot(a -> predicate.test(a, v), action);
	}

	/** Variant with reverse function-vs-arg order. */
	default @Nonnull SELF doIfNotSrt(@Nonnull LObjSrtPredicate<? super T> predicate, short v, @Nonnull LConsumer<? super T> action) {
		return doIfNotSrt(v, predicate, action);
	}

	default @Nonnull SELF doIfFlt(float v, @Nonnull LObjFltPredicate<? super T> predicate, @Nonnull LConsumer<? super T> action) {
		return doIf(a -> predicate.test(a, v), action);
	}

	/** Variant with reverse function-vs-arg order. */
	default @Nonnull SELF doIfFlt(@Nonnull LObjFltPredicate<? super T> predicate, float v, @Nonnull LConsumer<? super T> action) {
		return doIfFlt(v, predicate, action);
	}

	default @Nonnull SELF doIfNotFlt(float v, @Nonnull LObjFltPredicate<? super T> predicate, @Nonnull LConsumer<? super T> action) {
		return doIfNot(a -> predicate.test(a, v), action);
	}

	/** Variant with reverse function-vs-arg order. */
	default @Nonnull SELF doIfNotFlt(@Nonnull LObjFltPredicate<? super T> predicate, float v, @Nonnull LConsumer<? super T> action) {
		return doIfNotFlt(v, predicate, action);
	}

	default @Nonnull SELF doIfInt(int v, @Nonnull LObjIntPredicate<? super T> predicate, @Nonnull LConsumer<? super T> action) {
		return doIf(a -> predicate.test(a, v), action);
	}

	/** Variant with reverse function-vs-arg order. */
	default @Nonnull SELF doIfInt(@Nonnull LObjIntPredicate<? super T> predicate, int v, @Nonnull LConsumer<? super T> action) {
		return doIfInt(v, predicate, action);
	}

	default @Nonnull SELF doIfNotInt(int v, @Nonnull LObjIntPredicate<? super T> predicate, @Nonnull LConsumer<? super T> action) {
		return doIfNot(a -> predicate.test(a, v), action);
	}

	/** Variant with reverse function-vs-arg order. */
	default @Nonnull SELF doIfNotInt(@Nonnull LObjIntPredicate<? super T> predicate, int v, @Nonnull LConsumer<? super T> action) {
		return doIfNotInt(v, predicate, action);
	}

	default @Nonnull SELF doIfLong(long v, @Nonnull LObjLongPredicate<? super T> predicate, @Nonnull LConsumer<? super T> action) {
		return doIf(a -> predicate.test(a, v), action);
	}

	/** Variant with reverse function-vs-arg order. */
	default @Nonnull SELF doIfLong(@Nonnull LObjLongPredicate<? super T> predicate, long v, @Nonnull LConsumer<? super T> action) {
		return doIfLong(v, predicate, action);
	}

	default @Nonnull SELF doIfNotLong(long v, @Nonnull LObjLongPredicate<? super T> predicate, @Nonnull LConsumer<? super T> action) {
		return doIfNot(a -> predicate.test(a, v), action);
	}

	/** Variant with reverse function-vs-arg order. */
	default @Nonnull SELF doIfNotLong(@Nonnull LObjLongPredicate<? super T> predicate, long v, @Nonnull LConsumer<? super T> action) {
		return doIfNotLong(v, predicate, action);
	}

	default @Nonnull <V> SELF doIf(V v, @Nonnull LBiPredicate<? super T, ? super V> predicate, @Nonnull LConsumer<? super T> action) {
		return doIf(a -> predicate.test(a, v), action);
	}

	/** Variant with reverse function-vs-arg order. */
	default @Nonnull <V> SELF doIf(@Nonnull LBiPredicate<? super T, ? super V> predicate, V v, @Nonnull LConsumer<? super T> action) {
		return doIf(v, predicate, action);
	}

	default @Nonnull <V> SELF doIfNot(V v, @Nonnull LBiPredicate<? super T, ? super V> predicate, @Nonnull LConsumer<? super T> action) {
		return doIfNot(a -> predicate.test(a, v), action);
	}

	/** Variant with reverse function-vs-arg order. */
	default @Nonnull <V> SELF doIfNot(@Nonnull LBiPredicate<? super T, ? super V> predicate, V v, @Nonnull LConsumer<? super T> action) {
		return doIfNot(v, predicate, action);
	}

	default @Nonnull <V2, V3> SELF doIf(V2 a2, V3 a3, @Nonnull LTriPredicate<? super T, ? super V2, ? super V3> predicate, @Nonnull LConsumer<? super T> action) {
		return doIf(a -> predicate.test(a, a2, a3), action);
	}

	/** Variant with reverse function-vs-arg order. */
	default @Nonnull <V2, V3> SELF doIf(@Nonnull LTriPredicate<? super T, ? super V2, ? super V3> predicate, V2 a2, V3 a3, @Nonnull LConsumer<? super T> action) {
		return doIf(a2, a3, predicate, action);
	}

	default @Nonnull <V2, V3> SELF doIfNot(V2 a2, V3 a3, @Nonnull LTriPredicate<? super T, ? super V2, ? super V3> predicate, @Nonnull LConsumer<? super T> action) {
		return doIfNot(a -> predicate.test(a, a2, a3), action);
	}

	/** Variant with reverse function-vs-arg order. */
	default @Nonnull <V2, V3> SELF doIfNot(@Nonnull LTriPredicate<? super T, ? super V2, ? super V3> predicate, V2 a2, V3 a3, @Nonnull LConsumer<? super T> action) {
		return doIfNot(a2, a3, predicate, action);
	}

	default @Nonnull <V2, V3, V4> SELF doIf(V2 a2, V3 a3, V4 a4, @Nonnull LQuadPredicate<? super T, ? super V2, ? super V3, ? super V4> predicate, @Nonnull LConsumer<? super T> action) {
		return doIf(a -> predicate.test(a, a2, a3, a4), action);
	}

	/** Variant with reverse function-vs-arg order. */
	default @Nonnull <V2, V3, V4> SELF doIf(@Nonnull LQuadPredicate<? super T, ? super V2, ? super V3, ? super V4> predicate, V2 a2, V3 a3, V4 a4, @Nonnull LConsumer<? super T> action) {
		return doIf(a2, a3, a4, predicate, action);
	}

	default @Nonnull <V2, V3, V4> SELF doIfNot(V2 a2, V3 a3, V4 a4, @Nonnull LQuadPredicate<? super T, ? super V2, ? super V3, ? super V4> predicate, @Nonnull LConsumer<? super T> action) {
		return doIfNot(a -> predicate.test(a, a2, a3, a4), action);
	}

	/** Variant with reverse function-vs-arg order. */
	default @Nonnull <V2, V3, V4> SELF doIfNot(@Nonnull LQuadPredicate<? super T, ? super V2, ? super V3, ? super V4> predicate, V2 a2, V3 a3, V4 a4, @Nonnull LConsumer<? super T> action) {
		return doIfNot(a2, a3, a4, predicate, action);
	}

	default @Nonnull <V1> SELF doIfWith(V1 with1, @Nonnull LBiPredicate<? super V1, ? super T> predicate, @Nonnull LConsumer<? super T> action) {
		return doIf(a -> predicate.test(with1, a), action);
	}

	/** Variant with reverse function-vs-arg order. */
	default @Nonnull <V1> SELF doIfWith(@Nonnull LBiPredicate<? super V1, ? super T> predicate, V1 with1, @Nonnull LConsumer<? super T> action) {
		return doIfWith(with1, predicate, action);
	}

	default @Nonnull <V1> SELF doIfNotWith(V1 with1, @Nonnull LBiPredicate<? super V1, ? super T> predicate, @Nonnull LConsumer<? super T> action) {
		return doIfNot(a -> predicate.test(with1, a), action);
	}

	/** Variant with reverse function-vs-arg order. */
	default @Nonnull <V1> SELF doIfNotWith(@Nonnull LBiPredicate<? super V1, ? super T> predicate, V1 with1, @Nonnull LConsumer<? super T> action) {
		return doIfNotWith(with1, predicate, action);
	}

	default @Nonnull <V1, V2> SELF doIfWith(V1 with1, V2 with2, @Nonnull LTriPredicate<? super V1, ? super V2, ? super T> predicate, @Nonnull LConsumer<? super T> action) {
		return doIf(a -> predicate.test(with1, with2, a), action);
	}

	/** Variant with reverse function-vs-arg order. */
	default @Nonnull <V1, V2> SELF doIfWith(@Nonnull LTriPredicate<? super V1, ? super V2, ? super T> predicate, V1 with1, V2 with2, @Nonnull LConsumer<? super T> action) {
		return doIfWith(with1, with2, predicate, action);
	}

	default @Nonnull <V1, V2> SELF doIfNotWith(V1 with1, V2 with2, @Nonnull LTriPredicate<? super V1, ? super V2, ? super T> predicate, @Nonnull LConsumer<? super T> action) {
		return doIfNot(a -> predicate.test(with1, with2, a), action);
	}

	/** Variant with reverse function-vs-arg order. */
	default @Nonnull <V1, V2> SELF doIfNotWith(@Nonnull LTriPredicate<? super V1, ? super V2, ? super T> predicate, V1 with1, V2 with2, @Nonnull LConsumer<? super T> action) {
		return doIfNotWith(with1, with2, predicate, action);
	}

	default @Nonnull SELF uniDoIfWith(T with, @Nonnull LBiPredicate<? super T, ? super T> predicate, @Nonnull LConsumer<? super T> action) {
		return doIf(a -> predicate.test(with, a), action);
	}

	/** Variant with reverse function-vs-arg order. */
	default @Nonnull SELF uniDoIfWith(@Nonnull LBiPredicate<? super T, ? super T> predicate, T with, @Nonnull LConsumer<? super T> action) {
		return uniDoIfWith(with, predicate, action);
	}

	default @Nonnull SELF uniDoIfNotWith(T with, @Nonnull LBiPredicate<? super T, ? super T> predicate, @Nonnull LConsumer<? super T> action) {
		return doIfNot(a -> predicate.test(with, a), action);
	}

	/** Variant with reverse function-vs-arg order. */
	default @Nonnull SELF uniDoIfNotWith(@Nonnull LBiPredicate<? super T, ? super T> predicate, T with, @Nonnull LConsumer<? super T> action) {
		return uniDoIfNotWith(with, predicate, action);
	}

	//</editor-fold>
}
