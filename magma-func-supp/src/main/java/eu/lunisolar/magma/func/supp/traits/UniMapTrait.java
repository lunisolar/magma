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
import eu.lunisolar.magma.func.supp.traits.*; // NOSONAR
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
 * Mapping where result is exactly the same type.
 * Method's name part "uni" is actually only needed for object mappings (to resolve compile issues).
 */
public interface UniMapTrait<T, SELF extends UniMapTrait<T, SELF>> extends ValueTrait<T, SELF>, FluentTrait<SELF> {

	//<editor-fold desc="uniMap">

	default @Nonnull SELF uniMap(@Nonnull LUnaryOperator<T> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return value(mapping.apply(value()));
	}

	default @Nonnull <K> SELF uniMap(K a1, @Nonnull LBiFunction<T, K, T> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return value(mapping.apply(value(), a1));
	}

	default @Nonnull <K> SELF uniMapWith(K a1, @Nonnull LBiFunction<K, T, T> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return value(mapping.apply(a1, value()));
	}

	default @Nonnull <K1, K2> SELF uniMap(K1 a1, K2 a2, @Nonnull LTriFunction<T, K1, K2, T> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return value(mapping.apply(value(), a1, a2));
	}

	default @Nonnull <K1, K2> SELF uniMapWith(K1 a1, K2 a2, @Nonnull LTriFunction<K1, K2, T, T> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return value(mapping.apply(a1, a2, value()));
	}

	default @Nonnull <K1, K2, K3> SELF uniMap(K1 a1, K2 a2, K3 a3, @Nonnull LQuadFunction<T, K1, K2, K3, T> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return value(mapping.apply(value(), a1, a2, a3));
	}

	default @Nonnull <K1, K2, K3> SELF uniMapWith(K1 a1, K2 a2, K3 a3, @Nonnull LQuadFunction<K1, K2, K3, T, T> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return value(mapping.apply(a1, a2, a3, value()));
	}

	default @Nonnull <K1, K2, K3, K4> SELF uniMap(K1 a1, K2 a2, K3 a3, K4 a4, @Nonnull LQuintFunction<T, K1, K2, K3, K4, T> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return value(mapping.apply(value(), a1, a2, a3, a4));
	}

	default @Nonnull <K1, K2, K3, K4> SELF uniMapWith(K1 a1, K2 a2, K3 a3, K4 a4, @Nonnull LQuintFunction<K1, K2, K3, K4, T, T> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return value(mapping.apply(a1, a2, a3, a4, value()));
	}

	//</editor-fold>

}
