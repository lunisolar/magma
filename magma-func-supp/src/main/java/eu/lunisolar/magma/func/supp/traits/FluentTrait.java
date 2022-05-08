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
 * Compared to {@link Fluent} forces to use exception handling from Magma functions.
 */
public interface FluentTrait<FLUENT> extends Fluent<FLUENT> {

	public default @Nonnull FLUENT fluentUse(@Nonnull LConsumer<FLUENT> interjection) {
		Null.nonNullArg(interjection, "interjection");
		return LConsumer.inlineAccept(fluentCtx(), interjection);
	}

	public default @Nonnull <T2> FLUENT fluentUse(T2 a2, @Nonnull LBiConsumer<FLUENT, T2> interjection) {
		Null.nonNullArg(interjection, "interjection");
		return LBiConsumer.inlineAccept(fluentCtx(), a2, interjection);
	}

	public default @Nonnull <T2, T3> FLUENT fluentUse(T2 a2, T3 a3, @Nonnull LTriConsumer<FLUENT, T2, T3> interjection) {
		Null.nonNullArg(interjection, "interjection");
		return LTriConsumer.inlineAccept(fluentCtx(), a2, a3, interjection);
	}

	public default @Nonnull <T2, T4, T5> FLUENT fluentUse(T2 a2, T4 a3, T5 a4, @Nonnull LQuadConsumer<FLUENT, T2, T4, T5> interjection) {
		Null.nonNullArg(interjection, "interjection");
		return LQuadConsumer.inlineAccept(fluentCtx(), a2, a3, a4, interjection);
	}

	public default @Nonnull <T1> FLUENT fluentUseWith(T1 a1, @Nonnull LBiConsumer<T1, FLUENT> interjection) {
		Null.nonNullArg(interjection, "interjection");
		return LBiConsumer.inlineAcceptR(fluentCtx(), a1, fluentCtx(), interjection);
	}

	public default @Nonnull <T1, T2> FLUENT fluentUseWith(T1 a1, T2 a2, @Nonnull LTriConsumer<T1, T2, FLUENT> interjection) {
		Null.nonNullArg(interjection, "interjection");
		return LTriConsumer.inlineAcceptR(fluentCtx(), a1, a2, fluentCtx(), interjection);
	}

	public default @Nonnull <T1, T2, T4> FLUENT fluentUseWith(T1 a1, T2 a2, T4 a3, @Nonnull LQuadConsumer<T1, T2, T4, FLUENT> interjection) {
		Null.nonNullArg(interjection, "interjection");
		return LQuadConsumer.inlineAcceptR(fluentCtx(), a1, a2, a3, fluentCtx(), interjection);
	}

	public default @Nonnull <R> R fluentNullableMap(@Nonnull LFunction<FLUENT, R> interjection) {
		Null.nonNullArg(interjection, "interjection");
		return interjection.apply(fluentCtx());
	}

	public default @Nonnull <R> R fluentMap(@Nonnull LFunction<FLUENT, R> interjection) {
		Null.nonNullArg(interjection, "interjection");
		return Null.nonNull(fluentNullableMap(interjection));
	}

	public default @Nonnull <H2, R> R fluentNullableMap(H2 a2, @Nonnull LBiFunction<FLUENT, H2, R> interjection) {
		Null.nonNullArg(interjection, "interjection");
		return interjection.apply(fluentCtx(), a2);
	}

	public default @Nonnull <H2, R> R fluentMap(H2 a2, @Nonnull LBiFunction<FLUENT, H2, R> interjection) {
		Null.nonNullArg(interjection, "interjection");
		return Null.nonNull(fluentNullableMap(a2, interjection));
	}

	public default @Nonnull <H2, H3, R> R fluentNullableMap(H2 a2, H3 a3, @Nonnull LTriFunction<FLUENT, H2, H3, R> interjection) {
		Null.nonNullArg(interjection, "interjection");
		return interjection.apply(fluentCtx(), a2, a3);
	}

	public default @Nonnull <H2, H3, R> R fluentMap(H2 a2, H3 a3, @Nonnull LTriFunction<FLUENT, H2, H3, R> interjection) {
		Null.nonNullArg(interjection, "interjection");
		return Null.nonNull(fluentNullableMap(a2, a3, interjection));
	}

	public default @Nonnull <H2, H4, H5, R> R fluentNullableMap(H2 a2, H4 a3, H5 a4, @Nonnull LQuadFunction<FLUENT, H2, H4, H5, R> interjection) {
		Null.nonNullArg(interjection, "interjection");
		return interjection.apply(fluentCtx(), a2, a3, a4);
	}

	public default @Nonnull <H2, H4, H5, R> R fluentMap(H2 a2, H4 a3, H5 a4, @Nonnull LQuadFunction<FLUENT, H2, H4, H5, R> interjection) {
		Null.nonNullArg(interjection, "interjection");
		return Null.nonNull(fluentNullableMap(a2, a3, a4, interjection));
	}
}