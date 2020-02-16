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
public interface IsBoolTrait<SELF extends IsBoolTrait<SELF>> extends BoolValueTrait<SELF> {

	// <editor-fold desc="is">

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	default boolean is(@Nonnull LLogicalOperator predicate) {
		Null.nonNullArg(predicate, "predicate");
		return predicate.apply(value());
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	default boolean isNot(@Nonnull LLogicalOperator predicate) {
		Null.nonNullArg(predicate, "predicate");
		return !predicate.apply(value());
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	default boolean is(boolean a2, @Nonnull LLogicalBinaryOperator predicate) {
		Null.nonNullArg(predicate, "predicate");
		return predicate.apply(value(), a2);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	default boolean is(@Nonnull LLogicalBinaryOperator predicate, boolean a2) {
		return is(a2, predicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	default boolean isNot(boolean a2, @Nonnull LLogicalBinaryOperator predicate) {
		Null.nonNullArg(predicate, "predicate");
		return !predicate.apply(value(), a2);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	default boolean isNot(@Nonnull LLogicalBinaryOperator predicate, boolean a2) {
		return isNot(a2, predicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	default boolean is(boolean a2, boolean a3, @Nonnull LLogicalTernaryOperator predicate) {
		Null.nonNullArg(predicate, "predicate");
		return predicate.apply(value(), a2, a3);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	default boolean is(@Nonnull LLogicalTernaryOperator predicate, boolean a2, boolean a3) {
		return is(a2, a3, predicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	default boolean isNot(boolean a2, boolean a3, @Nonnull LLogicalTernaryOperator predicate) {
		Null.nonNullArg(predicate, "predicate");
		return !predicate.apply(value(), a2, a3);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	default boolean isNot(@Nonnull LLogicalTernaryOperator predicate, boolean a2, boolean a3) {
		return isNot(a2, a3, predicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	default boolean isInt(int v, @Nonnull LBoolIntPredicate predicate) {
		Null.nonNullArg(predicate, "predicate");
		return predicate.test(value(), v);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	default boolean isInt(@Nonnull LBoolIntPredicate predicate, int v) {
		return isInt(v, predicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	default boolean isNotInt(int v, @Nonnull LBoolIntPredicate predicate) {
		Null.nonNullArg(predicate, "predicate");
		return !predicate.test(value(), v);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	default boolean isNotInt(@Nonnull LBoolIntPredicate predicate, int v) {
		return isNotInt(v, predicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	default <V> boolean is_(V v, @Nonnull LObjBoolPredicate.LBoolObjPred<? super V> predicate) {
		Null.nonNullArg(predicate, "predicate");
		return predicate.testBoolObj(value(), v);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	default <V> boolean is_(@Nonnull LObjBoolPredicate.LBoolObjPred<? super V> predicate, V v) {
		return is_(v, predicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	default <V> boolean isNot_(V v, @Nonnull LObjBoolPredicate.LBoolObjPred<? super V> predicate) {
		Null.nonNullArg(predicate, "predicate");
		return !predicate.testBoolObj(value(), v);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	default <V> boolean isNot_(@Nonnull LObjBoolPredicate.LBoolObjPred<? super V> predicate, V v) {
		return isNot_(v, predicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	default <V1> boolean isWithBool(V1 with, @Nonnull LObjBoolPredicate<? super V1> predicate) {
		Null.nonNullArg(predicate, "predicate");
		return predicate.test(with, value());
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	default <V1> boolean isWithBool(@Nonnull LObjBoolPredicate<? super V1> predicate, V1 with) {
		return isWithBool(with, predicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	default <V1> boolean isNotWithBool(V1 with, @Nonnull LObjBoolPredicate<? super V1> predicate) {
		Null.nonNullArg(predicate, "predicate");
		return !predicate.test(with, value());
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	default <V1> boolean isNotWithBool(@Nonnull LObjBoolPredicate<? super V1> predicate, V1 with) {
		return isNotWithBool(with, predicate);
	}

	// </editor-fold>
}
