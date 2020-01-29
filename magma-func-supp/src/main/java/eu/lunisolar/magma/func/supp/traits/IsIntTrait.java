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
public interface IsIntTrait<SELF extends IsIntTrait<SELF>> extends IntValueTrait<SELF> {

	// <editor-fold desc="is">

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	default boolean is(@Nonnull LIntPredicate predicate) {
		Null.nonNullArg(predicate, "predicate");
		return predicate.test(value());
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	default boolean isNot(@Nonnull LIntPredicate predicate) {
		Null.nonNullArg(predicate, "predicate");
		return !predicate.test(value());
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	default boolean is(int a2, @Nonnull LBiIntPredicate predicate) {
		Null.nonNullArg(predicate, "predicate");
		return predicate.test(value(), a2);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	default boolean is(@Nonnull LBiIntPredicate predicate, int a2) {
		return is(a2, predicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	default boolean isNot(int a2, @Nonnull LBiIntPredicate predicate) {
		Null.nonNullArg(predicate, "predicate");
		return !predicate.test(value(), a2);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	default boolean isNot(@Nonnull LBiIntPredicate predicate, int a2) {
		return isNot(a2, predicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	default boolean is(int a2, int a3, @Nonnull LTriIntPredicate predicate) {
		Null.nonNullArg(predicate, "predicate");
		return predicate.test(value(), a2, a3);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	default boolean is(@Nonnull LTriIntPredicate predicate, int a2, int a3) {
		return is(a2, a3, predicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	default boolean isNot(int a2, int a3, @Nonnull LTriIntPredicate predicate) {
		Null.nonNullArg(predicate, "predicate");
		return !predicate.test(value(), a2, a3);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	default boolean isNot(@Nonnull LTriIntPredicate predicate, int a2, int a3) {
		return isNot(a2, a3, predicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	default boolean is_(boolean v, @Nonnull LBoolIntPredicate.LIntBoolPred predicate) {
		Null.nonNullArg(predicate, "predicate");
		return predicate.testIntBool(value(), v);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	default boolean is_(@Nonnull LBoolIntPredicate.LIntBoolPred predicate, boolean v) {
		return is_(v, predicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	default boolean isNot_(boolean v, @Nonnull LBoolIntPredicate.LIntBoolPred predicate) {
		Null.nonNullArg(predicate, "predicate");
		return !predicate.testIntBool(value(), v);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	default boolean isNot_(@Nonnull LBoolIntPredicate.LIntBoolPred predicate, boolean v) {
		return isNot_(v, predicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	default boolean is_(byte v, @Nonnull LByteIntPredicate.LIntBytePred predicate) {
		Null.nonNullArg(predicate, "predicate");
		return predicate.testIntByte(value(), v);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	default boolean is_(@Nonnull LByteIntPredicate.LIntBytePred predicate, byte v) {
		return is_(v, predicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	default boolean isNot_(byte v, @Nonnull LByteIntPredicate.LIntBytePred predicate) {
		Null.nonNullArg(predicate, "predicate");
		return !predicate.testIntByte(value(), v);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	default boolean isNot_(@Nonnull LByteIntPredicate.LIntBytePred predicate, byte v) {
		return isNot_(v, predicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	default boolean is_(double v, @Nonnull LDblIntPredicate.LIntDblPred predicate) {
		Null.nonNullArg(predicate, "predicate");
		return predicate.testIntDbl(value(), v);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	default boolean is_(@Nonnull LDblIntPredicate.LIntDblPred predicate, double v) {
		return is_(v, predicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	default boolean isNot_(double v, @Nonnull LDblIntPredicate.LIntDblPred predicate) {
		Null.nonNullArg(predicate, "predicate");
		return !predicate.testIntDbl(value(), v);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	default boolean isNot_(@Nonnull LDblIntPredicate.LIntDblPred predicate, double v) {
		return isNot_(v, predicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	default boolean is_(char v, @Nonnull LCharIntPredicate.LIntCharPred predicate) {
		Null.nonNullArg(predicate, "predicate");
		return predicate.testIntChar(value(), v);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	default boolean is_(@Nonnull LCharIntPredicate.LIntCharPred predicate, char v) {
		return is_(v, predicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	default boolean isNot_(char v, @Nonnull LCharIntPredicate.LIntCharPred predicate) {
		Null.nonNullArg(predicate, "predicate");
		return !predicate.testIntChar(value(), v);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	default boolean isNot_(@Nonnull LCharIntPredicate.LIntCharPred predicate, char v) {
		return isNot_(v, predicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	default boolean is_(short v, @Nonnull LSrtIntPredicate.LIntSrtPred predicate) {
		Null.nonNullArg(predicate, "predicate");
		return predicate.testIntSrt(value(), v);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	default boolean is_(@Nonnull LSrtIntPredicate.LIntSrtPred predicate, short v) {
		return is_(v, predicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	default boolean isNot_(short v, @Nonnull LSrtIntPredicate.LIntSrtPred predicate) {
		Null.nonNullArg(predicate, "predicate");
		return !predicate.testIntSrt(value(), v);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	default boolean isNot_(@Nonnull LSrtIntPredicate.LIntSrtPred predicate, short v) {
		return isNot_(v, predicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	default boolean is_(float v, @Nonnull LFltIntPredicate.LIntFltPred predicate) {
		Null.nonNullArg(predicate, "predicate");
		return predicate.testIntFlt(value(), v);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	default boolean is_(@Nonnull LFltIntPredicate.LIntFltPred predicate, float v) {
		return is_(v, predicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	default boolean isNot_(float v, @Nonnull LFltIntPredicate.LIntFltPred predicate) {
		Null.nonNullArg(predicate, "predicate");
		return !predicate.testIntFlt(value(), v);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	default boolean isNot_(@Nonnull LFltIntPredicate.LIntFltPred predicate, float v) {
		return isNot_(v, predicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	default boolean is_(long v, @Nonnull LLongIntPredicate.LIntLongPred predicate) {
		Null.nonNullArg(predicate, "predicate");
		return predicate.testIntLong(value(), v);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	default boolean is_(@Nonnull LLongIntPredicate.LIntLongPred predicate, long v) {
		return is_(v, predicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	default boolean isNot_(long v, @Nonnull LLongIntPredicate.LIntLongPred predicate) {
		Null.nonNullArg(predicate, "predicate");
		return !predicate.testIntLong(value(), v);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	default boolean isNot_(@Nonnull LLongIntPredicate.LIntLongPred predicate, long v) {
		return isNot_(v, predicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	default <V> boolean is_(V v, @Nonnull LObjIntPredicate.LIntObjPred<? super V> predicate) {
		Null.nonNullArg(predicate, "predicate");
		return predicate.testIntObj(value(), v);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	default <V> boolean is_(@Nonnull LObjIntPredicate.LIntObjPred<? super V> predicate, V v) {
		return is_(v, predicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	default <V> boolean isNot_(V v, @Nonnull LObjIntPredicate.LIntObjPred<? super V> predicate) {
		Null.nonNullArg(predicate, "predicate");
		return !predicate.testIntObj(value(), v);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	default <V> boolean isNot_(@Nonnull LObjIntPredicate.LIntObjPred<? super V> predicate, V v) {
		return isNot_(v, predicate);
	}

	// </editor-fold>
}
