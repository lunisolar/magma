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
 * Compared to {@link Fluent} forces to use exception handling from Magma functions. 
 */
public interface FluentTrait<SELF extends FluentTrait<SELF>> extends Fluent<SELF> {

	/** Non-capturing (if used properly) interjection in fluent calls. Please mind the boxing. */
	default @Nonnull SELF fluentUse(@Nonnull LConsumer<SELF> interjection) {
		return Fluent.super.fluentUse(interjection);
	}

	/** Non-capturing (if used properly) interjection in fluent calls. Please mind the boxing. */
	default @Nonnull <T> SELF fluentUse(T ctx, @Nonnull LBiConsumer<SELF, T> interjection) {
		return Fluent.super.fluentUse(ctx, interjection);
	}

	default @Nonnull <R> R fluentMap(@Nonnull LFunction<SELF, R> mapping) {
		return Fluent.super.fluentMap(mapping);
	}

	default @Nonnull <T, R> R fluentMap(T ctx, @Nonnull LBiFunction<SELF, T, R> mapping) {
		return Fluent.super.fluentMap(ctx, mapping);
	}

	default @Nullable <R> R fluentNullableMap(@Nonnull LFunction<SELF, R> mapping) {
		return Fluent.super.fluentNullableMap(mapping);
	}

	default @Nullable <T, R> R fluentNullableMap(T ctx, @Nonnull LBiFunction<SELF, T, R> mapping) {
		return Fluent.super.fluentNullableMap(ctx, mapping);
	}
}