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
public interface UniMapBoolTrait<SELF extends UniMapBoolTrait<SELF>> extends BoolValueTrait<SELF>, FluentTrait<SELF> {

	// <editor-fold desc="uniMap">

	default @Nonnull SELF map(@Nonnull LLogicalOperator mapping) {
		Null.nonNullArg(mapping, "mapping");
		return value(mapping.apply(value()));
	}

	default @Nonnull <K> SELF map_(K a1, @Nonnull LObjBoolPredicate.LBoolObjPred<? super K> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return value(mapping.testBoolObj(value(), a1));
	}

	default @Nonnull <K> SELF mapWith(K a1, @Nonnull LObjBoolPredicate<? super K> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return value(mapping.test(a1, value()));
	}

	default @Nonnull <K1, K2> SELF map_(K1 a1, K2 a2, @Nonnull LBiObjBoolPredicate.LBool2Obj0Obj1Pred<? super K1, ? super K2> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return value(mapping.testBool2Obj0Obj1(value(), a1, a2));
	}

	default @Nonnull <K1, K2> SELF mapWith(K1 a1, K2 a2, @Nonnull LBiObjBoolPredicate<? super K1, ? super K2> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return value(mapping.test(a1, a2, value()));
	}

	default @Nonnull SELF map(boolean a1, @Nonnull LLogicalBinaryOperator mapping) {
		Null.nonNullArg(mapping, "mapping");
		return value(mapping.apply(value(), a1));
	}

	default @Nonnull SELF map(boolean a1, boolean a2, @Nonnull LLogicalTernaryOperator mapping) {
		Null.nonNullArg(mapping, "mapping");
		return value(mapping.apply(value(), a1, a2));
	}

	// </editor-fold>

}
