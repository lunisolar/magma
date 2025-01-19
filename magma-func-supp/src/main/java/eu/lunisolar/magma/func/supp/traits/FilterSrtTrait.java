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
public interface FilterSrtTrait<SELF extends FilterSrtTrait<SELF>> extends FluentTrait<SELF> {

	//<editor-fold desc="filtering">

	public SELF filter(@Nonnull LSrtPredicate predicate);

	/** Variant 'obj.filter(..., (...) -> { ..long multiline definition.. })' */
	default @Nonnull SELF filter(short a2, @Nonnull LBiSrtPredicate predicate) {
		return filter(a -> predicate.test(a, a2));
	}

	/** Variant 'obj.filter(Is::equal, ...)' or 'opt.filter(Does::contain, ...)', etc.  */
	default @Nonnull SELF filter(@Nonnull LBiSrtPredicate predicate, short a2) {
		return filter(a2, predicate);
	}

	/** Variant 'obj.filter(..., (...) -> { ..long multiline definition.. })' */
	default @Nonnull SELF filter(short a2, short a3, @Nonnull LTriSrtPredicate predicate) {
		return filter(a -> predicate.test(a, a2, a3));
	}

	/** Variant 'obj.filter(Is::equal, ...)' or 'opt.filter(Does::contain, ...)', etc.  */
	default @Nonnull SELF filter(@Nonnull LTriSrtPredicate predicate, short a2, short a3) {
		return filter(a2, a3, predicate);
	}

	/** Variant 'obj.filter(..., (...) -> { ..long multiline definition.. })' */
	default @Nonnull SELF filterInt(int v, @Nonnull LSrtIntPredicate predicate) {
		return filter(a -> predicate.test(a, v));
	}

	/** Variant 'obj.filter(Is::equal, ...)' or 'opt.filter(Does::contain, ...)', etc.  */
	default @Nonnull SELF filterInt(@Nonnull LSrtIntPredicate predicate, int v) {
		return filterInt(v, predicate);
	}

	/** Variant 'obj.filter(..., (...) -> { ..long multiline definition.. })' */
	default @Nonnull <V1> SELF filterWithSrt(V1 with1, @Nonnull LObjSrtPredicate<? super V1> predicate) {
		return filter(a -> predicate.test(with1, a));
	}

	/** Variant 'obj.filter(Is::equal, ...)' or 'opt.filter(Does::contain, ...)', etc.  */
	default @Nonnull <V1> SELF filterWithSrt(@Nonnull LObjSrtPredicate<? super V1> predicate, V1 with1) {
		return filterWithSrt(with1, predicate);
	}

	/** Variant 'obj.filter(..., (...) -> { ..long multiline definition.. })' */
	default @Nonnull <V1, V2> SELF filterWith(V1 with1, V2 with2, @Nonnull LBiObjSrtPredicate<? super V1, ? super V2> predicate) {
		return filter(a -> predicate.test(with1, with2, a));
	}

	/** Variant 'obj.filter(Is::equal, ...)' or 'opt.filter(Does::contain, ...)', etc.  */
	default @Nonnull <V1, V2> SELF filterWith(@Nonnull LBiObjSrtPredicate<? super V1, ? super V2> predicate, V1 with1, V2 with2) {
		return filterWith(with1, with2, predicate);
	}

	//</editor-fold>
}
