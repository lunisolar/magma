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
public interface FilterIntTrait<SELF extends FilterIntTrait<SELF>> extends FluentTrait<SELF> {

	// <editor-fold desc="filtering">

	public SELF filter(@Nonnull LIntPredicate predicate);

	/** Variant 'obj.filter(..., (...) -> { ..long multiline definition.. })' */
	default SELF filter(int a2, @Nonnull LBiIntPredicate predicate) {
		return filter(a -> predicate.test(a, a2));
	}

	/** Variant 'obj.filter(Is::equal, ...)' or 'opt.filter(Does::contain, ...)', etc.  */
	default SELF filter(@Nonnull LBiIntPredicate predicate, int a2) {
		return filter(a2, predicate);
	}

	/** Variant 'obj.filter(..., (...) -> { ..long multiline definition.. })' */
	default SELF filter(int a2, int a3, @Nonnull LTriIntPredicate predicate) {
		return filter(a -> predicate.test(a, a2, a3));
	}

	/** Variant 'obj.filter(Is::equal, ...)' or 'opt.filter(Does::contain, ...)', etc.  */
	default SELF filter(@Nonnull LTriIntPredicate predicate, int a2, int a3) {
		return filter(a2, a3, predicate);
	}

	/** Variant 'obj.filter(..., (...) -> { ..long multiline definition.. })' */
	default SELF filter2Variant(boolean v, @Nonnull LBoolIntPredicate.LIntBoolPred predicate) {
		return filter(a -> predicate.testIntBool(a, v));
	}

	/** Variant 'obj.filter(Is::equal, ...)' or 'opt.filter(Does::contain, ...)', etc.  */
	default SELF filter2Variant(@Nonnull LBoolIntPredicate.LIntBoolPred predicate, boolean v) {
		return filter2Variant(v, predicate);
	}

	/** Variant 'obj.filter(..., (...) -> { ..long multiline definition.. })' */
	default SELF filter2Variant(byte v, @Nonnull LByteIntPredicate.LIntBytePred predicate) {
		return filter(a -> predicate.testIntByte(a, v));
	}

	/** Variant 'obj.filter(Is::equal, ...)' or 'opt.filter(Does::contain, ...)', etc.  */
	default SELF filter2Variant(@Nonnull LByteIntPredicate.LIntBytePred predicate, byte v) {
		return filter2Variant(v, predicate);
	}

	/** Variant 'obj.filter(..., (...) -> { ..long multiline definition.. })' */
	default SELF filter2Variant(double v, @Nonnull LDblIntPredicate.LIntDblPred predicate) {
		return filter(a -> predicate.testIntDbl(a, v));
	}

	/** Variant 'obj.filter(Is::equal, ...)' or 'opt.filter(Does::contain, ...)', etc.  */
	default SELF filter2Variant(@Nonnull LDblIntPredicate.LIntDblPred predicate, double v) {
		return filter2Variant(v, predicate);
	}

	/** Variant 'obj.filter(..., (...) -> { ..long multiline definition.. })' */
	default SELF filter2Variant(char v, @Nonnull LCharIntPredicate.LIntCharPred predicate) {
		return filter(a -> predicate.testIntChar(a, v));
	}

	/** Variant 'obj.filter(Is::equal, ...)' or 'opt.filter(Does::contain, ...)', etc.  */
	default SELF filter2Variant(@Nonnull LCharIntPredicate.LIntCharPred predicate, char v) {
		return filter2Variant(v, predicate);
	}

	/** Variant 'obj.filter(..., (...) -> { ..long multiline definition.. })' */
	default SELF filter2Variant(short v, @Nonnull LSrtIntPredicate.LIntSrtPred predicate) {
		return filter(a -> predicate.testIntSrt(a, v));
	}

	/** Variant 'obj.filter(Is::equal, ...)' or 'opt.filter(Does::contain, ...)', etc.  */
	default SELF filter2Variant(@Nonnull LSrtIntPredicate.LIntSrtPred predicate, short v) {
		return filter2Variant(v, predicate);
	}

	/** Variant 'obj.filter(..., (...) -> { ..long multiline definition.. })' */
	default SELF filter2Variant(float v, @Nonnull LFltIntPredicate.LIntFltPred predicate) {
		return filter(a -> predicate.testIntFlt(a, v));
	}

	/** Variant 'obj.filter(Is::equal, ...)' or 'opt.filter(Does::contain, ...)', etc.  */
	default SELF filter2Variant(@Nonnull LFltIntPredicate.LIntFltPred predicate, float v) {
		return filter2Variant(v, predicate);
	}

	/** Variant 'obj.filter(..., (...) -> { ..long multiline definition.. })' */
	default SELF filter2Variant(long v, @Nonnull LLongIntPredicate.LIntLongPred predicate) {
		return filter(a -> predicate.testIntLong(a, v));
	}

	/** Variant 'obj.filter(Is::equal, ...)' or 'opt.filter(Does::contain, ...)', etc.  */
	default SELF filter2Variant(@Nonnull LLongIntPredicate.LIntLongPred predicate, long v) {
		return filter2Variant(v, predicate);
	}

	/** Variant 'obj.filter(..., (...) -> { ..long multiline definition.. })' */
	default <V> SELF filter2Variant(V v, @Nonnull LObjIntPredicate.LIntObjPred<? super V> predicate) {
		return filter(a -> predicate.testIntObj(a, v));
	}

	/** Variant 'obj.filter(Is::equal, ...)' or 'opt.filter(Does::contain, ...)', etc.  */
	default <V> SELF filter2Variant(@Nonnull LObjIntPredicate.LIntObjPred<? super V> predicate, V v) {
		return filter2Variant(v, predicate);
	}

	// </editor-fold>
}
