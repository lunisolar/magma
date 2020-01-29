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
public interface IsTrait<T, SELF extends IsTrait<T, SELF>> extends ValueTrait<T, SELF> {

	// <editor-fold desc="is">

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	default boolean is(@Nonnull LPredicate<? super T> predicate) {
		Null.nonNullArg(predicate, "predicate");
		return predicate.test(value());
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	default boolean isNot(@Nonnull LPredicate<? super T> predicate) {
		Null.nonNullArg(predicate, "predicate");
		return !predicate.test(value());
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	default boolean uniIs(T a2, @Nonnull LBiPredicate<? super T, ? super T> predicate) {
		Null.nonNullArg(predicate, "predicate");
		return predicate.test(value(), a2);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	default boolean uniIs(@Nonnull LBiPredicate<? super T, ? super T> predicate, T a2) {
		return uniIs(a2, predicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	default boolean uniIsNot(T a2, @Nonnull LBiPredicate<? super T, ? super T> predicate) {
		Null.nonNullArg(predicate, "predicate");
		return !predicate.test(value(), a2);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	default boolean uniIsNot(@Nonnull LBiPredicate<? super T, ? super T> predicate, T a2) {
		return uniIsNot(a2, predicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	default boolean uniIs(T a2, T a3, @Nonnull LTriPredicate<? super T, ? super T, ? super T> predicate) {
		Null.nonNullArg(predicate, "predicate");
		return predicate.test(value(), a2, a3);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	default boolean uniIs(@Nonnull LTriPredicate<? super T, ? super T, ? super T> predicate, T a2, T a3) {
		return uniIs(a2, a3, predicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	default boolean uniIsNot(T a2, T a3, @Nonnull LTriPredicate<? super T, ? super T, ? super T> predicate) {
		Null.nonNullArg(predicate, "predicate");
		return !predicate.test(value(), a2, a3);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	default boolean uniIsNot(@Nonnull LTriPredicate<? super T, ? super T, ? super T> predicate, T a2, T a3) {
		return uniIsNot(a2, a3, predicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	default boolean uniIs(T a2, T a3, T a4, @Nonnull LQuadPredicate<? super T, ? super T, ? super T, ? super T> predicate) {
		Null.nonNullArg(predicate, "predicate");
		return predicate.test(value(), a2, a3, a4);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	default boolean uniIs(@Nonnull LQuadPredicate<? super T, ? super T, ? super T, ? super T> predicate, T a2, T a3, T a4) {
		return uniIs(a2, a3, a4, predicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	default boolean uniIsNot(T a2, T a3, T a4, @Nonnull LQuadPredicate<? super T, ? super T, ? super T, ? super T> predicate) {
		Null.nonNullArg(predicate, "predicate");
		return !predicate.test(value(), a2, a3, a4);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	default boolean uniIsNot(@Nonnull LQuadPredicate<? super T, ? super T, ? super T, ? super T> predicate, T a2, T a3, T a4) {
		return uniIsNot(a2, a3, a4, predicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	default boolean is(boolean v, @Nonnull LObjBoolPredicate<? super T> predicate) {
		Null.nonNullArg(predicate, "predicate");
		return predicate.test(value(), v);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	default boolean is(@Nonnull LObjBoolPredicate<? super T> predicate, boolean v) {
		return is(v, predicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	default boolean isNot(boolean v, @Nonnull LObjBoolPredicate<? super T> predicate) {
		Null.nonNullArg(predicate, "predicate");
		return !predicate.test(value(), v);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	default boolean isNot(@Nonnull LObjBoolPredicate<? super T> predicate, boolean v) {
		return isNot(v, predicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	default boolean is(byte v, @Nonnull LObjBytePredicate<? super T> predicate) {
		Null.nonNullArg(predicate, "predicate");
		return predicate.test(value(), v);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	default boolean is(@Nonnull LObjBytePredicate<? super T> predicate, byte v) {
		return is(v, predicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	default boolean isNot(byte v, @Nonnull LObjBytePredicate<? super T> predicate) {
		Null.nonNullArg(predicate, "predicate");
		return !predicate.test(value(), v);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	default boolean isNot(@Nonnull LObjBytePredicate<? super T> predicate, byte v) {
		return isNot(v, predicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	default boolean is(double v, @Nonnull LObjDblPredicate<? super T> predicate) {
		Null.nonNullArg(predicate, "predicate");
		return predicate.test(value(), v);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	default boolean is(@Nonnull LObjDblPredicate<? super T> predicate, double v) {
		return is(v, predicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	default boolean isNot(double v, @Nonnull LObjDblPredicate<? super T> predicate) {
		Null.nonNullArg(predicate, "predicate");
		return !predicate.test(value(), v);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	default boolean isNot(@Nonnull LObjDblPredicate<? super T> predicate, double v) {
		return isNot(v, predicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	default boolean is(char v, @Nonnull LObjCharPredicate<? super T> predicate) {
		Null.nonNullArg(predicate, "predicate");
		return predicate.test(value(), v);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	default boolean is(@Nonnull LObjCharPredicate<? super T> predicate, char v) {
		return is(v, predicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	default boolean isNot(char v, @Nonnull LObjCharPredicate<? super T> predicate) {
		Null.nonNullArg(predicate, "predicate");
		return !predicate.test(value(), v);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	default boolean isNot(@Nonnull LObjCharPredicate<? super T> predicate, char v) {
		return isNot(v, predicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	default boolean is(short v, @Nonnull LObjSrtPredicate<? super T> predicate) {
		Null.nonNullArg(predicate, "predicate");
		return predicate.test(value(), v);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	default boolean is(@Nonnull LObjSrtPredicate<? super T> predicate, short v) {
		return is(v, predicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	default boolean isNot(short v, @Nonnull LObjSrtPredicate<? super T> predicate) {
		Null.nonNullArg(predicate, "predicate");
		return !predicate.test(value(), v);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	default boolean isNot(@Nonnull LObjSrtPredicate<? super T> predicate, short v) {
		return isNot(v, predicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	default boolean is(float v, @Nonnull LObjFltPredicate<? super T> predicate) {
		Null.nonNullArg(predicate, "predicate");
		return predicate.test(value(), v);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	default boolean is(@Nonnull LObjFltPredicate<? super T> predicate, float v) {
		return is(v, predicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	default boolean isNot(float v, @Nonnull LObjFltPredicate<? super T> predicate) {
		Null.nonNullArg(predicate, "predicate");
		return !predicate.test(value(), v);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	default boolean isNot(@Nonnull LObjFltPredicate<? super T> predicate, float v) {
		return isNot(v, predicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	default boolean is(int v, @Nonnull LObjIntPredicate<? super T> predicate) {
		Null.nonNullArg(predicate, "predicate");
		return predicate.test(value(), v);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	default boolean is(@Nonnull LObjIntPredicate<? super T> predicate, int v) {
		return is(v, predicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	default boolean isNot(int v, @Nonnull LObjIntPredicate<? super T> predicate) {
		Null.nonNullArg(predicate, "predicate");
		return !predicate.test(value(), v);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	default boolean isNot(@Nonnull LObjIntPredicate<? super T> predicate, int v) {
		return isNot(v, predicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	default boolean is(long v, @Nonnull LObjLongPredicate<? super T> predicate) {
		Null.nonNullArg(predicate, "predicate");
		return predicate.test(value(), v);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	default boolean is(@Nonnull LObjLongPredicate<? super T> predicate, long v) {
		return is(v, predicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	default boolean isNot(long v, @Nonnull LObjLongPredicate<? super T> predicate) {
		Null.nonNullArg(predicate, "predicate");
		return !predicate.test(value(), v);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	default boolean isNot(@Nonnull LObjLongPredicate<? super T> predicate, long v) {
		return isNot(v, predicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	default <V> boolean is(V v, @Nonnull LBiPredicate<? super T, ? super V> predicate) {
		Null.nonNullArg(predicate, "predicate");
		return predicate.test(value(), v);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	default <V> boolean is(@Nonnull LBiPredicate<? super T, ? super V> predicate, V v) {
		return is(v, predicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	default <V> boolean isNot(V v, @Nonnull LBiPredicate<? super T, ? super V> predicate) {
		Null.nonNullArg(predicate, "predicate");
		return !predicate.test(value(), v);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	default <V> boolean isNot(@Nonnull LBiPredicate<? super T, ? super V> predicate, V v) {
		return isNot(v, predicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	default <V2, V3> boolean is(V2 a2, V3 a3, @Nonnull LTriPredicate<? super T, ? super V2, ? super V3> predicate) {
		Null.nonNullArg(predicate, "predicate");
		return predicate.test(value(), a2, a3);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	default <V2, V3> boolean is(@Nonnull LTriPredicate<? super T, ? super V2, ? super V3> predicate, V2 a2, V3 a3) {
		return is(a2, a3, predicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	default <V2, V3> boolean isNot(V2 a2, V3 a3, @Nonnull LTriPredicate<? super T, ? super V2, ? super V3> predicate) {
		Null.nonNullArg(predicate, "predicate");
		return !predicate.test(value(), a2, a3);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	default <V2, V3> boolean isNot(@Nonnull LTriPredicate<? super T, ? super V2, ? super V3> predicate, V2 a2, V3 a3) {
		return isNot(a2, a3, predicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	default <V1> boolean isWith(V1 with, @Nonnull LBiPredicate<? super V1, ? super T> predicate) {
		Null.nonNullArg(predicate, "predicate");
		return predicate.test(with, value());
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	default <V1> boolean isWith(@Nonnull LBiPredicate<? super V1, ? super T> predicate, V1 with) {
		return isWith(with, predicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	default <V1> boolean isNotWith(V1 with, @Nonnull LBiPredicate<? super V1, ? super T> predicate) {
		Null.nonNullArg(predicate, "predicate");
		return !predicate.test(with, value());
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	default <V1> boolean isNotWith(@Nonnull LBiPredicate<? super V1, ? super T> predicate, V1 with) {
		return isNotWith(with, predicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	default boolean uniIsWith(T with, @Nonnull LBiPredicate<? super T, ? super T> predicate) {
		Null.nonNullArg(predicate, "predicate");
		return predicate.test(with, value());
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	default boolean uniIsWith(@Nonnull LBiPredicate<? super T, ? super T> predicate, T with) {
		return uniIsWith(with, predicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	default boolean uniIsNotWith(T with, @Nonnull LBiPredicate<? super T, ? super T> predicate) {
		Null.nonNullArg(predicate, "predicate");
		return !predicate.test(with, value());
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	default boolean uniIsNotWith(@Nonnull LBiPredicate<? super T, ? super T> predicate, T with) {
		return uniIsNotWith(with, predicate);
	}

	// </editor-fold>
}
