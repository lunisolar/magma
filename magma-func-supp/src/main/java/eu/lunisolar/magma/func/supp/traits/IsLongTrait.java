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
public interface IsLongTrait<SELF extends IsLongTrait<SELF>> extends LongValueTrait<SELF> {

	// <editor-fold desc="is">

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	default @Nonnull boolean is(@Nonnull LLongPredicate predicate) {
		Null.nonNullArg(predicate, "predicate");
		return predicate.test(value());
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	default @Nonnull boolean isNot(@Nonnull LLongPredicate predicate) {
		Null.nonNullArg(predicate, "predicate");
		return !predicate.test(value());
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	default @Nonnull boolean is(long a2, @Nonnull LBiLongPredicate predicate) {
		Null.nonNullArg(predicate, "predicate");
		return predicate.test(value(), a2);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	default @Nonnull boolean is(@Nonnull LBiLongPredicate predicate, long a2) {
		return is(a2, predicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	default @Nonnull boolean isNot(long a2, @Nonnull LBiLongPredicate predicate) {
		Null.nonNullArg(predicate, "predicate");
		return !predicate.test(value(), a2);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	default @Nonnull boolean isNot(@Nonnull LBiLongPredicate predicate, long a2) {
		return isNot(a2, predicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	default @Nonnull boolean is(long a2, long a3, @Nonnull LTriLongPredicate predicate) {
		Null.nonNullArg(predicate, "predicate");
		return predicate.test(value(), a2, a3);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	default @Nonnull boolean is(@Nonnull LTriLongPredicate predicate, long a2, long a3) {
		return is(a2, a3, predicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	default @Nonnull boolean isNot(long a2, long a3, @Nonnull LTriLongPredicate predicate) {
		Null.nonNullArg(predicate, "predicate");
		return !predicate.test(value(), a2, a3);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	default @Nonnull boolean isNot(@Nonnull LTriLongPredicate predicate, long a2, long a3) {
		return isNot(a2, a3, predicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	default @Nonnull boolean is2(int v, @Nonnull LLongIntPredicate predicate) {
		Null.nonNullArg(predicate, "predicate");
		return predicate.test(value(), v);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	default @Nonnull boolean is2(@Nonnull LLongIntPredicate predicate, int v) {
		return is2(v, predicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	default @Nonnull boolean isNot2(int v, @Nonnull LLongIntPredicate predicate) {
		Null.nonNullArg(predicate, "predicate");
		return !predicate.test(value(), v);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	default @Nonnull boolean isNot2(@Nonnull LLongIntPredicate predicate, int v) {
		return isNot2(v, predicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	default @Nonnull <V> boolean is2_(V v, @Nonnull LObjLongPredicate.LLongObjPred<? super V> predicate) {
		Null.nonNullArg(predicate, "predicate");
		return predicate.testLongObj(value(), v);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	default @Nonnull <V> boolean is2_(@Nonnull LObjLongPredicate.LLongObjPred<? super V> predicate, V v) {
		return is2_(v, predicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	default @Nonnull <V> boolean isNot2_(V v, @Nonnull LObjLongPredicate.LLongObjPred<? super V> predicate) {
		Null.nonNullArg(predicate, "predicate");
		return !predicate.testLongObj(value(), v);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	default @Nonnull <V> boolean isNot2_(@Nonnull LObjLongPredicate.LLongObjPred<? super V> predicate, V v) {
		return isNot2_(v, predicate);
	}

	// </editor-fold>
}
