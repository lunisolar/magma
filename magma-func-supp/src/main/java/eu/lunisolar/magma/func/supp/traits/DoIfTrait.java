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
public interface DoIfTrait<T, SELF extends DoIfTrait<T, SELF>> extends FluentTrait<SELF>, IsTrait<T, SELF> {

	// <editor-fold desc="doIf">

	default @Nonnull SELF doIf(@Nonnull LPredicate<? super T> predicate, LConsumer<SELF> action) {
		if (is(predicate))
			action.accept(self());
		return self();
	}

	default @Nonnull SELF doIfNot(@Nonnull LPredicate<? super T> predicate, LConsumer<SELF> action) {
		if (isNot(predicate))
			action.accept(self());
		return self();
	}

	default @Nonnull SELF uniDoIf(T a2, @Nonnull LBiPredicate<? super T, ? super T> predicate, LConsumer<SELF> action) {
		if (uniIs(a2, predicate))
			action.accept(self());
		return self();
	}

	/** Variant with reverse predicate arguments order. */
	default @Nonnull SELF uniDoIf(@Nonnull LBiPredicate<? super T, ? super T> predicate, T a2, LConsumer<SELF> action) {
		return uniDoIf(a2, predicate, action);
	}

	default @Nonnull SELF uniDoIfNot(T a2, @Nonnull LBiPredicate<? super T, ? super T> predicate, LConsumer<SELF> action) {
		if (uniIsNot(a2, predicate))
			action.accept(self());
		return self();
	}

	/** Variant with reverse predicate arguments order. */
	default @Nonnull SELF uniDoIfNot(@Nonnull LBiPredicate<? super T, ? super T> predicate, T a2, LConsumer<SELF> action) {
		return uniDoIfNot(a2, predicate, action);
	}

	default @Nonnull SELF uniDoIf(T a2, T a3, @Nonnull LTriPredicate<? super T, ? super T, ? super T> predicate, LConsumer<SELF> action) {
		if (uniIs(a2, a3, predicate))
			action.accept(self());
		return self();
	}

	/** Variant with reverse predicate arguments order. */
	default @Nonnull SELF uniDoIf(@Nonnull LTriPredicate<? super T, ? super T, ? super T> predicate, T a2, T a3, LConsumer<SELF> action) {
		return uniDoIf(a2, a3, predicate, action);
	}

	default @Nonnull SELF uniDoIfNot(T a2, T a3, @Nonnull LTriPredicate<? super T, ? super T, ? super T> predicate, LConsumer<SELF> action) {
		if (uniIsNot(a2, a3, predicate))
			action.accept(self());
		return self();
	}

	/** Variant with reverse predicate arguments order. */
	default @Nonnull SELF uniDoIfNot(@Nonnull LTriPredicate<? super T, ? super T, ? super T> predicate, T a2, T a3, LConsumer<SELF> action) {
		return uniDoIfNot(a2, a3, predicate, action);
	}

	default @Nonnull SELF uniDoIf(T a2, T a3, T a4, @Nonnull LQuadPredicate<? super T, ? super T, ? super T, ? super T> predicate, LConsumer<SELF> action) {
		if (uniIs(a2, a3, a4, predicate))
			action.accept(self());
		return self();
	}

	/** Variant with reverse predicate arguments order. */
	default @Nonnull SELF uniDoIf(@Nonnull LQuadPredicate<? super T, ? super T, ? super T, ? super T> predicate, T a2, T a3, T a4, LConsumer<SELF> action) {
		return uniDoIf(a2, a3, a4, predicate, action);
	}

	default @Nonnull SELF uniDoIfNot(T a2, T a3, T a4, @Nonnull LQuadPredicate<? super T, ? super T, ? super T, ? super T> predicate, LConsumer<SELF> action) {
		if (uniIsNot(a2, a3, a4, predicate))
			action.accept(self());
		return self();
	}

	/** Variant with reverse predicate arguments order. */
	default @Nonnull SELF uniDoIfNot(@Nonnull LQuadPredicate<? super T, ? super T, ? super T, ? super T> predicate, T a2, T a3, T a4, LConsumer<SELF> action) {
		return uniDoIfNot(a2, a3, a4, predicate, action);
	}

	default @Nonnull SELF doIf(boolean v, @Nonnull LObjBoolPredicate<? super T> predicate, LConsumer<SELF> action) {
		if (is(v, predicate))
			action.accept(self());
		return self();
	}

	/** Variant with reverse predicate arguments order. */
	default @Nonnull SELF doIf(@Nonnull LObjBoolPredicate<? super T> predicate, boolean v, LConsumer<SELF> action) {
		return doIf(v, predicate, action);
	}

	default @Nonnull SELF doIfNot(boolean v, @Nonnull LObjBoolPredicate<? super T> predicate, LConsumer<SELF> action) {
		if (isNot(v, predicate))
			action.accept(self());
		return self();
	}

	/** Variant with reverse predicate arguments order. */
	default @Nonnull SELF doIfNot(@Nonnull LObjBoolPredicate<? super T> predicate, boolean v, LConsumer<SELF> action) {
		return doIfNot(v, predicate, action);
	}

	default @Nonnull SELF doIf(byte v, @Nonnull LObjBytePredicate<? super T> predicate, LConsumer<SELF> action) {
		if (is(v, predicate))
			action.accept(self());
		return self();
	}

	/** Variant with reverse predicate arguments order. */
	default @Nonnull SELF doIf(@Nonnull LObjBytePredicate<? super T> predicate, byte v, LConsumer<SELF> action) {
		return doIf(v, predicate, action);
	}

	default @Nonnull SELF doIfNot(byte v, @Nonnull LObjBytePredicate<? super T> predicate, LConsumer<SELF> action) {
		if (isNot(v, predicate))
			action.accept(self());
		return self();
	}

	/** Variant with reverse predicate arguments order. */
	default @Nonnull SELF doIfNot(@Nonnull LObjBytePredicate<? super T> predicate, byte v, LConsumer<SELF> action) {
		return doIfNot(v, predicate, action);
	}

	default @Nonnull SELF doIf(double v, @Nonnull LObjDblPredicate<? super T> predicate, LConsumer<SELF> action) {
		if (is(v, predicate))
			action.accept(self());
		return self();
	}

	/** Variant with reverse predicate arguments order. */
	default @Nonnull SELF doIf(@Nonnull LObjDblPredicate<? super T> predicate, double v, LConsumer<SELF> action) {
		return doIf(v, predicate, action);
	}

	default @Nonnull SELF doIfNot(double v, @Nonnull LObjDblPredicate<? super T> predicate, LConsumer<SELF> action) {
		if (isNot(v, predicate))
			action.accept(self());
		return self();
	}

	/** Variant with reverse predicate arguments order. */
	default @Nonnull SELF doIfNot(@Nonnull LObjDblPredicate<? super T> predicate, double v, LConsumer<SELF> action) {
		return doIfNot(v, predicate, action);
	}

	default @Nonnull SELF doIf(char v, @Nonnull LObjCharPredicate<? super T> predicate, LConsumer<SELF> action) {
		if (is(v, predicate))
			action.accept(self());
		return self();
	}

	/** Variant with reverse predicate arguments order. */
	default @Nonnull SELF doIf(@Nonnull LObjCharPredicate<? super T> predicate, char v, LConsumer<SELF> action) {
		return doIf(v, predicate, action);
	}

	default @Nonnull SELF doIfNot(char v, @Nonnull LObjCharPredicate<? super T> predicate, LConsumer<SELF> action) {
		if (isNot(v, predicate))
			action.accept(self());
		return self();
	}

	/** Variant with reverse predicate arguments order. */
	default @Nonnull SELF doIfNot(@Nonnull LObjCharPredicate<? super T> predicate, char v, LConsumer<SELF> action) {
		return doIfNot(v, predicate, action);
	}

	default @Nonnull SELF doIf(short v, @Nonnull LObjSrtPredicate<? super T> predicate, LConsumer<SELF> action) {
		if (is(v, predicate))
			action.accept(self());
		return self();
	}

	/** Variant with reverse predicate arguments order. */
	default @Nonnull SELF doIf(@Nonnull LObjSrtPredicate<? super T> predicate, short v, LConsumer<SELF> action) {
		return doIf(v, predicate, action);
	}

	default @Nonnull SELF doIfNot(short v, @Nonnull LObjSrtPredicate<? super T> predicate, LConsumer<SELF> action) {
		if (isNot(v, predicate))
			action.accept(self());
		return self();
	}

	/** Variant with reverse predicate arguments order. */
	default @Nonnull SELF doIfNot(@Nonnull LObjSrtPredicate<? super T> predicate, short v, LConsumer<SELF> action) {
		return doIfNot(v, predicate, action);
	}

	default @Nonnull SELF doIf(float v, @Nonnull LObjFltPredicate<? super T> predicate, LConsumer<SELF> action) {
		if (is(v, predicate))
			action.accept(self());
		return self();
	}

	/** Variant with reverse predicate arguments order. */
	default @Nonnull SELF doIf(@Nonnull LObjFltPredicate<? super T> predicate, float v, LConsumer<SELF> action) {
		return doIf(v, predicate, action);
	}

	default @Nonnull SELF doIfNot(float v, @Nonnull LObjFltPredicate<? super T> predicate, LConsumer<SELF> action) {
		if (isNot(v, predicate))
			action.accept(self());
		return self();
	}

	/** Variant with reverse predicate arguments order. */
	default @Nonnull SELF doIfNot(@Nonnull LObjFltPredicate<? super T> predicate, float v, LConsumer<SELF> action) {
		return doIfNot(v, predicate, action);
	}

	default @Nonnull SELF doIf(int v, @Nonnull LObjIntPredicate<? super T> predicate, LConsumer<SELF> action) {
		if (is(v, predicate))
			action.accept(self());
		return self();
	}

	/** Variant with reverse predicate arguments order. */
	default @Nonnull SELF doIf(@Nonnull LObjIntPredicate<? super T> predicate, int v, LConsumer<SELF> action) {
		return doIf(v, predicate, action);
	}

	default @Nonnull SELF doIfNot(int v, @Nonnull LObjIntPredicate<? super T> predicate, LConsumer<SELF> action) {
		if (isNot(v, predicate))
			action.accept(self());
		return self();
	}

	/** Variant with reverse predicate arguments order. */
	default @Nonnull SELF doIfNot(@Nonnull LObjIntPredicate<? super T> predicate, int v, LConsumer<SELF> action) {
		return doIfNot(v, predicate, action);
	}

	default @Nonnull SELF doIf(long v, @Nonnull LObjLongPredicate<? super T> predicate, LConsumer<SELF> action) {
		if (is(v, predicate))
			action.accept(self());
		return self();
	}

	/** Variant with reverse predicate arguments order. */
	default @Nonnull SELF doIf(@Nonnull LObjLongPredicate<? super T> predicate, long v, LConsumer<SELF> action) {
		return doIf(v, predicate, action);
	}

	default @Nonnull SELF doIfNot(long v, @Nonnull LObjLongPredicate<? super T> predicate, LConsumer<SELF> action) {
		if (isNot(v, predicate))
			action.accept(self());
		return self();
	}

	/** Variant with reverse predicate arguments order. */
	default @Nonnull SELF doIfNot(@Nonnull LObjLongPredicate<? super T> predicate, long v, LConsumer<SELF> action) {
		return doIfNot(v, predicate, action);
	}

	default @Nonnull <V> SELF doIf(V v, @Nonnull LBiPredicate<? super T, ? super V> predicate, LConsumer<SELF> action) {
		if (is(v, predicate))
			action.accept(self());
		return self();
	}

	/** Variant with reverse predicate arguments order. */
	default @Nonnull <V> SELF doIf(@Nonnull LBiPredicate<? super T, ? super V> predicate, V v, LConsumer<SELF> action) {
		return doIf(v, predicate, action);
	}

	default @Nonnull <V> SELF doIfNot(V v, @Nonnull LBiPredicate<? super T, ? super V> predicate, LConsumer<SELF> action) {
		if (isNot(v, predicate))
			action.accept(self());
		return self();
	}

	/** Variant with reverse predicate arguments order. */
	default @Nonnull <V> SELF doIfNot(@Nonnull LBiPredicate<? super T, ? super V> predicate, V v, LConsumer<SELF> action) {
		return doIfNot(v, predicate, action);
	}

	default @Nonnull <V2, V3> SELF doIf(V2 a2, V3 a3, @Nonnull LTriPredicate<? super T, ? super V2, ? super V3> predicate, LConsumer<SELF> action) {
		if (is(a2, a3, predicate))
			action.accept(self());
		return self();
	}

	/** Variant with reverse predicate arguments order. */
	default @Nonnull <V2, V3> SELF doIf(@Nonnull LTriPredicate<? super T, ? super V2, ? super V3> predicate, V2 a2, V3 a3, LConsumer<SELF> action) {
		return doIf(a2, a3, predicate, action);
	}

	default @Nonnull <V2, V3> SELF doIfNot(V2 a2, V3 a3, @Nonnull LTriPredicate<? super T, ? super V2, ? super V3> predicate, LConsumer<SELF> action) {
		if (isNot(a2, a3, predicate))
			action.accept(self());
		return self();
	}

	/** Variant with reverse predicate arguments order. */
	default @Nonnull <V2, V3> SELF doIfNot(@Nonnull LTriPredicate<? super T, ? super V2, ? super V3> predicate, V2 a2, V3 a3, LConsumer<SELF> action) {
		return doIfNot(a2, a3, predicate, action);
	}

	// </editor-fold>
}
