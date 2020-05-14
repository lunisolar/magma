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
public interface FilterBoolSingleTrait<SELF extends FilterBoolSingleTrait<SELF>> extends FilterBoolTrait<SELF>, IsBoolTrait<SELF> {

	// <editor-fold desc="is">

	@Override
	default @Nonnull SELF filter(@Nonnull LLogicalOperator operator) {
		Null.nonNullArg(operator, "operator");
		return this.is(operator) ? self() : voidValue();
	}

	@Override
	default @Nonnull SELF filter(boolean a2, @Nonnull LLogicalBinaryOperator operator) {
		Null.nonNullArg(operator, "operator");
		return this.is(a2, operator) ? self() : voidValue();
	}

	@Override
	default @Nonnull SELF filter(boolean a2, boolean a3, @Nonnull LLogicalTernaryOperator operator) {
		Null.nonNullArg(operator, "operator");
		return this.is(a2, a3, operator) ? self() : voidValue();
	}

	@Override
	default @Nonnull SELF filterInt(int v, @Nonnull LBoolIntPredicate operator) {
		Null.nonNullArg(operator, "operator");
		return this.isInt(v, operator) ? self() : voidValue();
	}

	@Override
	default @Nonnull <V> SELF filter_(V v, @Nonnull LObjBoolPredicate.LBoolObjPred<? super V> operator) {
		Null.nonNullArg(operator, "operator");
		return this.is_(v, operator) ? self() : voidValue();
	}

	@Override
	default @Nonnull <V1> SELF filterWithBool(V1 with1, @Nonnull LObjBoolPredicate<? super V1> operator) {
		Null.nonNullArg(operator, "operator");
		return this.isWithBool(with1, operator) ? self() : voidValue();
	}

	@Override
	default @Nonnull <V1, V2> SELF filterWith(V1 with1, V2 with2, @Nonnull LBiObjBoolPredicate<? super V1, ? super V2> operator) {
		Null.nonNullArg(operator, "operator");
		return this.isWith(with1, with2, operator) ? self() : voidValue();
	}

	// </editor-fold>
}
