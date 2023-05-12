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
 * Trait for any class that has fluent filter method.
 */
public interface UseSingleTrait<T, SELF extends UseSingleTrait<T, SELF>> extends UseTrait<T, SELF>, ValueTrait<T, SELF> {

	// <editor-fold desc="doIf">

	default @Nonnull SELF use(@Nonnull LConsumer<? super T> consumer) {
		consumer.accept(value());
		return fluentCtx();
	}

	default @Nonnull SELF uniUse(T a2, @Nonnull LBiConsumer<? super T, ? super T> consumer) {
		consumer.accept(value(), a2);
		return fluentCtx();
	}

	/** Variant with reverse function-vs-arg order. */
	default @Nonnull SELF uniUse(@Nonnull LBiConsumer<? super T, ? super T> consumer, T a2) {
		return uniUse(a2, consumer);
	}

	default @Nonnull SELF uniUse(T a2, T a3, @Nonnull LTriConsumer<? super T, ? super T, ? super T> consumer) {
		consumer.accept(value(), a2, a3);
		return fluentCtx();
	}

	/** Variant with reverse function-vs-arg order. */
	default @Nonnull SELF uniUse(@Nonnull LTriConsumer<? super T, ? super T, ? super T> consumer, T a2, T a3) {
		return uniUse(a2, a3, consumer);
	}

	default @Nonnull SELF uniUse(T a2, T a3, T a4, @Nonnull LQuadConsumer<? super T, ? super T, ? super T, ? super T> consumer) {
		consumer.accept(value(), a2, a3, a4);
		return fluentCtx();
	}

	/** Variant with reverse function-vs-arg order. */
	default @Nonnull SELF uniUse(@Nonnull LQuadConsumer<? super T, ? super T, ? super T, ? super T> consumer, T a2, T a3, T a4) {
		return uniUse(a2, a3, a4, consumer);
	}

	default @Nonnull <V> SELF useA(V[] a2, @Nonnull LBiConsumer<T, V[]> consumer) {
		consumer.accept(value(), a2);
		return fluentCtx();
	}

	/** Variant with reverse function-vs-arg order. */
	default @Nonnull <V> SELF useA(@Nonnull LBiConsumer<T, V[]> consumer, V... a2) {
		return useA(a2, consumer);
	}

	default @Nonnull SELF useBool(boolean v, @Nonnull LObjBoolConsumer<? super T> consumer) {
		consumer.accept(value(), v);
		return fluentCtx();
	}

	/** Variant with reverse function-vs-arg order. */
	default @Nonnull SELF useBool(@Nonnull LObjBoolConsumer<? super T> consumer, boolean v) {
		return useBool(v, consumer);
	}

	default @Nonnull SELF useByte(byte v, @Nonnull LObjByteConsumer<? super T> consumer) {
		consumer.accept(value(), v);
		return fluentCtx();
	}

	/** Variant with reverse function-vs-arg order. */
	default @Nonnull SELF useByte(@Nonnull LObjByteConsumer<? super T> consumer, byte v) {
		return useByte(v, consumer);
	}

	default @Nonnull SELF useDbl(double v, @Nonnull LObjDblConsumer<? super T> consumer) {
		consumer.accept(value(), v);
		return fluentCtx();
	}

	/** Variant with reverse function-vs-arg order. */
	default @Nonnull SELF useDbl(@Nonnull LObjDblConsumer<? super T> consumer, double v) {
		return useDbl(v, consumer);
	}

	default @Nonnull SELF useChar(char v, @Nonnull LObjCharConsumer<? super T> consumer) {
		consumer.accept(value(), v);
		return fluentCtx();
	}

	/** Variant with reverse function-vs-arg order. */
	default @Nonnull SELF useChar(@Nonnull LObjCharConsumer<? super T> consumer, char v) {
		return useChar(v, consumer);
	}

	default @Nonnull SELF useSrt(short v, @Nonnull LObjSrtConsumer<? super T> consumer) {
		consumer.accept(value(), v);
		return fluentCtx();
	}

	/** Variant with reverse function-vs-arg order. */
	default @Nonnull SELF useSrt(@Nonnull LObjSrtConsumer<? super T> consumer, short v) {
		return useSrt(v, consumer);
	}

	default @Nonnull SELF useFlt(float v, @Nonnull LObjFltConsumer<? super T> consumer) {
		consumer.accept(value(), v);
		return fluentCtx();
	}

	/** Variant with reverse function-vs-arg order. */
	default @Nonnull SELF useFlt(@Nonnull LObjFltConsumer<? super T> consumer, float v) {
		return useFlt(v, consumer);
	}

	default @Nonnull SELF useInt(int v, @Nonnull LObjIntConsumer<? super T> consumer) {
		consumer.accept(value(), v);
		return fluentCtx();
	}

	/** Variant with reverse function-vs-arg order. */
	default @Nonnull SELF useInt(@Nonnull LObjIntConsumer<? super T> consumer, int v) {
		return useInt(v, consumer);
	}

	default @Nonnull SELF useLong(long v, @Nonnull LObjLongConsumer<? super T> consumer) {
		consumer.accept(value(), v);
		return fluentCtx();
	}

	/** Variant with reverse function-vs-arg order. */
	default @Nonnull SELF useLong(@Nonnull LObjLongConsumer<? super T> consumer, long v) {
		return useLong(v, consumer);
	}

	default @Nonnull <V> SELF use(V v, @Nonnull LBiConsumer<? super T, ? super V> consumer) {
		consumer.accept(value(), v);
		return fluentCtx();
	}

	/** Variant with reverse function-vs-arg order. */
	default @Nonnull <V> SELF use(@Nonnull LBiConsumer<? super T, ? super V> consumer, V v) {
		return use(v, consumer);
	}

	default @Nonnull <V2, V3> SELF use(V2 a2, V3 a3, @Nonnull LTriConsumer<? super T, ? super V2, ? super V3> consumer) {
		consumer.accept(value(), a2, a3);
		return fluentCtx();
	}

	/** Variant with reverse function-vs-arg order. */
	default @Nonnull <V2, V3> SELF use(@Nonnull LTriConsumer<? super T, ? super V2, ? super V3> consumer, V2 a2, V3 a3) {
		return use(a2, a3, consumer);
	}

	default @Nonnull <V2, V3, V4> SELF use(V2 a2, V3 a3, V4 a4, @Nonnull LQuadConsumer<? super T, ? super V2, ? super V3, ? super V4> consumer) {
		consumer.accept(value(), a2, a3, a4);
		return fluentCtx();
	}

	/** Variant with reverse function-vs-arg order. */
	default @Nonnull <V2, V3, V4> SELF use(@Nonnull LQuadConsumer<? super T, ? super V2, ? super V3, ? super V4> consumer, V2 a2, V3 a3, V4 a4) {
		return use(a2, a3, a4, consumer);
	}

	default @Nonnull <V1> SELF useWith(V1 with1, @Nonnull LBiConsumer<? super V1, ? super T> consumer) {
		consumer.accept(with1, value());
		return fluentCtx();
	}

	/** Variant with reverse function-vs-arg order. */
	default @Nonnull <V1> SELF useWith(@Nonnull LBiConsumer<? super V1, ? super T> consumer, V1 with1) {
		return useWith(with1, consumer);
	}

	default @Nonnull <V1, V2> SELF useWith(V1 with1, V2 with2, @Nonnull LTriConsumer<? super V1, ? super V2, ? super T> consumer) {
		consumer.accept(with1, with2, value());
		return fluentCtx();
	}

	/** Variant with reverse function-vs-arg order. */
	default @Nonnull <V1, V2> SELF useWith(@Nonnull LTriConsumer<? super V1, ? super V2, ? super T> consumer, V1 with1, V2 with2) {
		return useWith(with1, with2, consumer);
	}

	default @Nonnull SELF uniUseWith(T with, @Nonnull LBiConsumer<? super T, ? super T> consumer) {
		consumer.accept(with, value());
		return fluentCtx();
	}

	/** Variant with reverse function-vs-arg order. */
	default @Nonnull SELF uniUseWith(@Nonnull LBiConsumer<? super T, ? super T> consumer, T with) {
		return uniUseWith(with, consumer);
	}

	// </editor-fold>
}
