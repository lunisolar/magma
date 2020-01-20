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
public interface IsFltTrait<SELF extends IsFltTrait<SELF>> extends Fluent<SELF> {

	// <editor-fold desc="filtering">

	public boolean is(@Nonnull LFltPredicate predicate);

	/** Variant 'obj.is(..., (...) -> { ..long multiline definition.. })' */
	default boolean is(float a2, @Nonnull LBiFltPredicate predicate) {
		Null.nonNullArg(predicate, "predicate");
		return is(a -> predicate.test(a, a2));
	}

	/** Variant 'obj.is(Is::equal, ...)' or 'opt.filter(Does::contain, ...)', etc.  */
	default boolean is(@Nonnull LBiFltPredicate predicate, float a2) {
		return is(a2, predicate);
	}

	/** Variant 'obj.is(..., (...) -> { ..long multiline definition.. })' */
	default boolean is(float a2, float a3, @Nonnull LTriFltPredicate predicate) {
		Null.nonNullArg(predicate, "predicate");
		return is(a -> predicate.test(a, a2, a3));
	}

	/** Variant 'obj.is(Is::equal, ...)' or 'opt.filter(Does::contain, ...)', etc.  */
	default boolean is(@Nonnull LTriFltPredicate predicate, float a2, float a3) {
		return is(a2, a3, predicate);
	}

	/** Variant 'obj.is(..., (...) -> { ..long multiline definition.. })' */
	default boolean is2(int v, @Nonnull LFltIntPredicate predicate) {
		Null.nonNullArg(predicate, "predicate");
		return is(a -> predicate.test(a, v));
	}

	/** Variant 'obj.is(Is::equal, ...)' or 'opt.filter(Does::contain, ...)', etc.  */
	default boolean is2(@Nonnull LFltIntPredicate predicate, int v) {
		return is2(v, predicate);
	}

	/** Variant 'obj.is(..., (...) -> { ..long multiline definition.. })' */
	default <V> boolean is2_(V v, @Nonnull LObjFltPredicate.LFltObjPred<? super V> predicate) {
		Null.nonNullArg(predicate, "predicate");
		return is(a -> predicate.testFltObj(a, v));
	}

	/** Variant 'obj.is(Is::equal, ...)' or 'opt.filter(Does::contain, ...)', etc.  */
	default <V> boolean is2_(@Nonnull LObjFltPredicate.LFltObjPred<? super V> predicate, V v) {
		return is2_(v, predicate);
	}

	// </editor-fold>
}
