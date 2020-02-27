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
import javax.annotation.concurrent.ThreadSafe; // NOSONAR
import java.util.Objects; // NOSONAR
import eu.lunisolar.magma.basics.*; // NOSONAR
import eu.lunisolar.magma.basics.builder.*; // NOSONAR
import eu.lunisolar.magma.basics.exceptions.*; // NOSONAR
import eu.lunisolar.magma.basics.fluent.Fluent; // NOSONAR
import eu.lunisolar.magma.basics.meta.*; // NOSONAR
import eu.lunisolar.magma.basics.meta.aType.*; // NOSONAR
import eu.lunisolar.magma.basics.meta.functional.*; // NOSONAR
import eu.lunisolar.magma.basics.meta.functional.type.*; // NOSONAR
import eu.lunisolar.magma.basics.meta.functional.domain.*; // NOSONAR
import eu.lunisolar.magma.func.*; // NOSONAR
import eu.lunisolar.magma.func.supp.Be; // NOSONAR
import eu.lunisolar.magma.func.supp.check.*; // NOSONAR
import eu.lunisolar.magma.func.supp.memento.*; // NOSONAR
import eu.lunisolar.magma.func.supp.traits.*; // NOSONAR
import eu.lunisolar.magma.func.tuple.*; // NOSONAR
import eu.lunisolar.magma.basics.fluent.*; //NOSONAR

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

import eu.lunisolar.magma.func.supp.value.*;

import static eu.lunisolar.magma.func.supp.traits.CheckTrait.*;

public interface CheckBoolTrait<SELF extends CheckBoolTrait<SELF>> extends FluentTrait<SELF>, aValue<aBool>, LBoolSingle, BoolValueTrait<SELF> {

	boolean get();

	default boolean value() {
		return get();
	}

	@Nonnull
	default String checkTraitType() {
		return "Value";
	}

	@Nonnull
	default String checkTraitName() {
		return "?";
	}

	@Nonnull
	default ExMF<RuntimeException> checkTraitFactory() {
		return X::value;
	}

	// <editor-fold desc="main methods">

	default @Nonnull SELF must(@Nonnull LLogicalOperator operator, @Nonnull String message) {
		Null.nonNullArg(operator, "operator");
		LLogicalOperator.throwIfNot(get(), operator, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull SELF must$(@Nonnull LLogicalOperator operator, @Nonnull String message) {
		Null.nonNullArg(operator, "operator");
		LLogicalOperator.throwIfNot(get(), operator, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull SELF must(@Nonnull LLogicalOperator operator, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(operator, "operator");
		LLogicalOperator.throwIfNot(get(), operator, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull SELF mustX(@Nonnull LLogicalOperator operator, @Nonnull ExMF<RuntimeException> ex, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(operator, "operator");
		LLogicalOperator.throwIfNot(get(), operator, ex, message, messageParams);
		return self();
	}

	default @Nonnull SELF mustNot(@Nonnull LLogicalOperator operator, @Nonnull String message) {
		Null.nonNullArg(operator, "operator");
		LLogicalOperator.throwIf(get(), operator, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull SELF mustNot$(@Nonnull LLogicalOperator operator, @Nonnull String message) {
		Null.nonNullArg(operator, "operator");
		LLogicalOperator.throwIf(get(), operator, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull SELF mustNot(@Nonnull LLogicalOperator operator, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(operator, "operator");
		LLogicalOperator.throwIf(get(), operator, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull SELF mustXNot(@Nonnull LLogicalOperator operator, @Nonnull ExMF<RuntimeException> ex, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(operator, "operator");
		LLogicalOperator.throwIf(get(), operator, ex, message, messageParams);
		return self();
	}

	default @Nonnull SELF must(@Nonnull LLogicalBinaryOperator operator, boolean a2, @Nonnull String message) {
		Null.nonNullArg(operator, "operator");
		LLogicalBinaryOperator.throwIfNot(get(), a2, operator, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull SELF must$(@Nonnull LLogicalBinaryOperator operator, boolean a2, @Nonnull String message) {
		Null.nonNullArg(operator, "operator");
		LLogicalBinaryOperator.throwIfNot(get(), a2, operator, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull SELF must$$(@Nonnull LLogicalBinaryOperator operator, boolean a2, @Nonnull String message) {
		Null.nonNullArg(operator, "operator");
		LLogicalBinaryOperator.throwIfNot(get(), a2, operator, checkTraitFactory(), MESSAGE_S_S_S_S_1, checkTraitType(), checkTraitName(), message, a2, get());
		return self();
	}

	default @Nonnull SELF must(@Nonnull LLogicalBinaryOperator operator, boolean a2, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(operator, "operator");
		LLogicalBinaryOperator.throwIfNot(get(), a2, operator, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull SELF mustX(@Nonnull LLogicalBinaryOperator operator, boolean a2, @Nonnull ExMF<RuntimeException> ex, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(operator, "operator");
		LLogicalBinaryOperator.throwIfNot(get(), a2, operator, ex, message, messageParams);
		return self();
	}

	default @Nonnull SELF must(boolean a2, @Nonnull LLogicalBinaryOperator operator, @Nonnull String message) {
		Null.nonNullArg(operator, "operator");
		LLogicalBinaryOperator.throwIfNot(get(), a2, operator, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull SELF must$(boolean a2, @Nonnull LLogicalBinaryOperator operator, @Nonnull String message) {
		Null.nonNullArg(operator, "operator");
		LLogicalBinaryOperator.throwIfNot(get(), a2, operator, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull SELF must$$(boolean a2, @Nonnull LLogicalBinaryOperator operator, @Nonnull String message) {
		Null.nonNullArg(operator, "operator");
		LLogicalBinaryOperator.throwIfNot(get(), a2, operator, checkTraitFactory(), MESSAGE_S_S_S_S_1, checkTraitType(), checkTraitName(), message, a2, get());
		return self();
	}

	default @Nonnull SELF must(boolean a2, @Nonnull LLogicalBinaryOperator operator, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(operator, "operator");
		LLogicalBinaryOperator.throwIfNot(get(), a2, operator, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull SELF mustX(boolean a2, @Nonnull LLogicalBinaryOperator operator, @Nonnull ExMF<RuntimeException> ex, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(operator, "operator");
		LLogicalBinaryOperator.throwIfNot(get(), a2, operator, ex, message, messageParams);
		return self();
	}

	default @Nonnull SELF mustNot(@Nonnull LLogicalBinaryOperator operator, boolean a2, @Nonnull String message) {
		Null.nonNullArg(operator, "operator");
		LLogicalBinaryOperator.throwIf(get(), a2, operator, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull SELF mustNot$(@Nonnull LLogicalBinaryOperator operator, boolean a2, @Nonnull String message) {
		Null.nonNullArg(operator, "operator");
		LLogicalBinaryOperator.throwIf(get(), a2, operator, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull SELF mustNot$$(@Nonnull LLogicalBinaryOperator operator, boolean a2, @Nonnull String message) {
		Null.nonNullArg(operator, "operator");
		LLogicalBinaryOperator.throwIf(get(), a2, operator, checkTraitFactory(), MESSAGE_S_S_S_S_1, checkTraitType(), checkTraitName(), message, a2, get());
		return self();
	}

	default @Nonnull SELF mustNot(@Nonnull LLogicalBinaryOperator operator, boolean a2, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(operator, "operator");
		LLogicalBinaryOperator.throwIf(get(), a2, operator, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull SELF mustXNot(@Nonnull LLogicalBinaryOperator operator, boolean a2, @Nonnull ExMF<RuntimeException> ex, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(operator, "operator");
		LLogicalBinaryOperator.throwIf(get(), a2, operator, ex, message, messageParams);
		return self();
	}

	default @Nonnull SELF mustNot(boolean a2, @Nonnull LLogicalBinaryOperator operator, @Nonnull String message) {
		Null.nonNullArg(operator, "operator");
		LLogicalBinaryOperator.throwIf(get(), a2, operator, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull SELF mustNot$(boolean a2, @Nonnull LLogicalBinaryOperator operator, @Nonnull String message) {
		Null.nonNullArg(operator, "operator");
		LLogicalBinaryOperator.throwIf(get(), a2, operator, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull SELF mustNot$$(boolean a2, @Nonnull LLogicalBinaryOperator operator, @Nonnull String message) {
		Null.nonNullArg(operator, "operator");
		LLogicalBinaryOperator.throwIf(get(), a2, operator, checkTraitFactory(), MESSAGE_S_S_S_S_1, checkTraitType(), checkTraitName(), message, a2, get());
		return self();
	}

	default @Nonnull SELF mustNot(boolean a2, @Nonnull LLogicalBinaryOperator operator, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(operator, "operator");
		LLogicalBinaryOperator.throwIf(get(), a2, operator, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull SELF mustXNot(boolean a2, @Nonnull LLogicalBinaryOperator operator, @Nonnull ExMF<RuntimeException> ex, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(operator, "operator");
		LLogicalBinaryOperator.throwIf(get(), a2, operator, ex, message, messageParams);
		return self();
	}

	default @Nonnull SELF must(@Nonnull LLogicalTernaryOperator operator, boolean a2, boolean a3, @Nonnull String message) {
		Null.nonNullArg(operator, "operator");
		LLogicalTernaryOperator.throwIfNot(get(), a2, a3, operator, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull SELF must$(@Nonnull LLogicalTernaryOperator operator, boolean a2, boolean a3, @Nonnull String message) {
		Null.nonNullArg(operator, "operator");
		LLogicalTernaryOperator.throwIfNot(get(), a2, a3, operator, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull SELF must$$(@Nonnull LLogicalTernaryOperator operator, boolean a2, boolean a3, @Nonnull String message) {
		Null.nonNullArg(operator, "operator");
		LLogicalTernaryOperator.throwIfNot(get(), a2, a3, operator, checkTraitFactory(), MESSAGE_S_S_S_S_2, checkTraitType(), checkTraitName(), message, a2, a3, get());
		return self();
	}

	default @Nonnull SELF must(@Nonnull LLogicalTernaryOperator operator, boolean a2, boolean a3, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(operator, "operator");
		LLogicalTernaryOperator.throwIfNot(get(), a2, a3, operator, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull SELF mustX(@Nonnull LLogicalTernaryOperator operator, boolean a2, boolean a3, @Nonnull ExMF<RuntimeException> ex, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(operator, "operator");
		LLogicalTernaryOperator.throwIfNot(get(), a2, a3, operator, ex, message, messageParams);
		return self();
	}

	default @Nonnull SELF must(boolean a2, boolean a3, @Nonnull LLogicalTernaryOperator operator, @Nonnull String message) {
		Null.nonNullArg(operator, "operator");
		LLogicalTernaryOperator.throwIfNot(get(), a2, a3, operator, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull SELF must$(boolean a2, boolean a3, @Nonnull LLogicalTernaryOperator operator, @Nonnull String message) {
		Null.nonNullArg(operator, "operator");
		LLogicalTernaryOperator.throwIfNot(get(), a2, a3, operator, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull SELF must$$(boolean a2, boolean a3, @Nonnull LLogicalTernaryOperator operator, @Nonnull String message) {
		Null.nonNullArg(operator, "operator");
		LLogicalTernaryOperator.throwIfNot(get(), a2, a3, operator, checkTraitFactory(), MESSAGE_S_S_S_S_2, checkTraitType(), checkTraitName(), message, a2, a3, get());
		return self();
	}

	default @Nonnull SELF must(boolean a2, boolean a3, @Nonnull LLogicalTernaryOperator operator, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(operator, "operator");
		LLogicalTernaryOperator.throwIfNot(get(), a2, a3, operator, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull SELF mustX(boolean a2, boolean a3, @Nonnull LLogicalTernaryOperator operator, @Nonnull ExMF<RuntimeException> ex, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(operator, "operator");
		LLogicalTernaryOperator.throwIfNot(get(), a2, a3, operator, ex, message, messageParams);
		return self();
	}

	default @Nonnull SELF mustNot(@Nonnull LLogicalTernaryOperator operator, boolean a2, boolean a3, @Nonnull String message) {
		Null.nonNullArg(operator, "operator");
		LLogicalTernaryOperator.throwIf(get(), a2, a3, operator, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull SELF mustNot$(@Nonnull LLogicalTernaryOperator operator, boolean a2, boolean a3, @Nonnull String message) {
		Null.nonNullArg(operator, "operator");
		LLogicalTernaryOperator.throwIf(get(), a2, a3, operator, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull SELF mustNot$$(@Nonnull LLogicalTernaryOperator operator, boolean a2, boolean a3, @Nonnull String message) {
		Null.nonNullArg(operator, "operator");
		LLogicalTernaryOperator.throwIf(get(), a2, a3, operator, checkTraitFactory(), MESSAGE_S_S_S_S_2, checkTraitType(), checkTraitName(), message, a2, a3, get());
		return self();
	}

	default @Nonnull SELF mustNot(@Nonnull LLogicalTernaryOperator operator, boolean a2, boolean a3, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(operator, "operator");
		LLogicalTernaryOperator.throwIf(get(), a2, a3, operator, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull SELF mustXNot(@Nonnull LLogicalTernaryOperator operator, boolean a2, boolean a3, @Nonnull ExMF<RuntimeException> ex, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(operator, "operator");
		LLogicalTernaryOperator.throwIf(get(), a2, a3, operator, ex, message, messageParams);
		return self();
	}

	default @Nonnull SELF mustNot(boolean a2, boolean a3, @Nonnull LLogicalTernaryOperator operator, @Nonnull String message) {
		Null.nonNullArg(operator, "operator");
		LLogicalTernaryOperator.throwIf(get(), a2, a3, operator, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull SELF mustNot$(boolean a2, boolean a3, @Nonnull LLogicalTernaryOperator operator, @Nonnull String message) {
		Null.nonNullArg(operator, "operator");
		LLogicalTernaryOperator.throwIf(get(), a2, a3, operator, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull SELF mustNot$$(boolean a2, boolean a3, @Nonnull LLogicalTernaryOperator operator, @Nonnull String message) {
		Null.nonNullArg(operator, "operator");
		LLogicalTernaryOperator.throwIf(get(), a2, a3, operator, checkTraitFactory(), MESSAGE_S_S_S_S_2, checkTraitType(), checkTraitName(), message, a2, a3, get());
		return self();
	}

	default @Nonnull SELF mustNot(boolean a2, boolean a3, @Nonnull LLogicalTernaryOperator operator, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(operator, "operator");
		LLogicalTernaryOperator.throwIf(get(), a2, a3, operator, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull SELF mustXNot(boolean a2, boolean a3, @Nonnull LLogicalTernaryOperator operator, @Nonnull ExMF<RuntimeException> ex, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(operator, "operator");
		LLogicalTernaryOperator.throwIf(get(), a2, a3, operator, ex, message, messageParams);
		return self();
	}

	default @Nonnull SELF mustInt(@Nonnull LBoolIntPredicate operator, int v, @Nonnull String message) {
		Null.nonNullArg(operator, "operator");
		LBoolIntPredicate.throwIfNot(get(), v, operator, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull SELF mustInt$(@Nonnull LBoolIntPredicate operator, int v, @Nonnull String message) {
		Null.nonNullArg(operator, "operator");
		LBoolIntPredicate.throwIfNot(get(), v, operator, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull SELF mustInt$$(@Nonnull LBoolIntPredicate operator, int v, @Nonnull String message) {
		Null.nonNullArg(operator, "operator");
		LBoolIntPredicate.throwIfNot(get(), v, operator, checkTraitFactory(), MESSAGE_S_S_S_S_1, checkTraitType(), checkTraitName(), message, v, get());
		return self();
	}

	default @Nonnull SELF mustInt(@Nonnull LBoolIntPredicate operator, int v, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(operator, "operator");
		LBoolIntPredicate.throwIfNot(get(), v, operator, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull SELF mustXInt(@Nonnull LBoolIntPredicate operator, int v, @Nonnull ExMF<RuntimeException> ex, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(operator, "operator");
		LBoolIntPredicate.throwIfNot(get(), v, operator, ex, message, messageParams);
		return self();
	}

	default @Nonnull SELF mustInt(int v, @Nonnull LBoolIntPredicate operator, @Nonnull String message) {
		Null.nonNullArg(operator, "operator");
		LBoolIntPredicate.throwIfNot(get(), v, operator, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull SELF mustInt$(int v, @Nonnull LBoolIntPredicate operator, @Nonnull String message) {
		Null.nonNullArg(operator, "operator");
		LBoolIntPredicate.throwIfNot(get(), v, operator, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull SELF mustInt$$(int v, @Nonnull LBoolIntPredicate operator, @Nonnull String message) {
		Null.nonNullArg(operator, "operator");
		LBoolIntPredicate.throwIfNot(get(), v, operator, checkTraitFactory(), MESSAGE_S_S_S_S_1, checkTraitType(), checkTraitName(), message, v, get());
		return self();
	}

	default @Nonnull SELF mustInt(int v, @Nonnull LBoolIntPredicate operator, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(operator, "operator");
		LBoolIntPredicate.throwIfNot(get(), v, operator, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull SELF mustXInt(int v, @Nonnull LBoolIntPredicate operator, @Nonnull ExMF<RuntimeException> ex, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(operator, "operator");
		LBoolIntPredicate.throwIfNot(get(), v, operator, ex, message, messageParams);
		return self();
	}

	default @Nonnull SELF mustNotInt(@Nonnull LBoolIntPredicate operator, int v, @Nonnull String message) {
		Null.nonNullArg(operator, "operator");
		LBoolIntPredicate.throwIf(get(), v, operator, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull SELF mustNotInt$(@Nonnull LBoolIntPredicate operator, int v, @Nonnull String message) {
		Null.nonNullArg(operator, "operator");
		LBoolIntPredicate.throwIf(get(), v, operator, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull SELF mustNotInt$$(@Nonnull LBoolIntPredicate operator, int v, @Nonnull String message) {
		Null.nonNullArg(operator, "operator");
		LBoolIntPredicate.throwIf(get(), v, operator, checkTraitFactory(), MESSAGE_S_S_S_S_1, checkTraitType(), checkTraitName(), message, v, get());
		return self();
	}

	default @Nonnull SELF mustNotInt(@Nonnull LBoolIntPredicate operator, int v, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(operator, "operator");
		LBoolIntPredicate.throwIf(get(), v, operator, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull SELF mustXNotInt(@Nonnull LBoolIntPredicate operator, int v, @Nonnull ExMF<RuntimeException> ex, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(operator, "operator");
		LBoolIntPredicate.throwIf(get(), v, operator, ex, message, messageParams);
		return self();
	}

	default @Nonnull SELF mustNotInt(int v, @Nonnull LBoolIntPredicate operator, @Nonnull String message) {
		Null.nonNullArg(operator, "operator");
		LBoolIntPredicate.throwIf(get(), v, operator, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull SELF mustNotInt$(int v, @Nonnull LBoolIntPredicate operator, @Nonnull String message) {
		Null.nonNullArg(operator, "operator");
		LBoolIntPredicate.throwIf(get(), v, operator, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull SELF mustNotInt$$(int v, @Nonnull LBoolIntPredicate operator, @Nonnull String message) {
		Null.nonNullArg(operator, "operator");
		LBoolIntPredicate.throwIf(get(), v, operator, checkTraitFactory(), MESSAGE_S_S_S_S_1, checkTraitType(), checkTraitName(), message, v, get());
		return self();
	}

	default @Nonnull SELF mustNotInt(int v, @Nonnull LBoolIntPredicate operator, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(operator, "operator");
		LBoolIntPredicate.throwIf(get(), v, operator, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull SELF mustXNotInt(int v, @Nonnull LBoolIntPredicate operator, @Nonnull ExMF<RuntimeException> ex, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(operator, "operator");
		LBoolIntPredicate.throwIf(get(), v, operator, ex, message, messageParams);
		return self();
	}

	default @Nonnull <V> SELF must_(@Nonnull LObjBoolPredicate.LBoolObjPred<? super V> operator, V v, @Nonnull String message) {
		Null.nonNullArg(operator, "operator");
		LObjBoolPredicate.throwIfNot(v, get(), operator, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull <V> SELF must_$(@Nonnull LObjBoolPredicate.LBoolObjPred<? super V> operator, V v, @Nonnull String message) {
		Null.nonNullArg(operator, "operator");
		LObjBoolPredicate.throwIfNot(v, get(), operator, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull <V> SELF must_$$(@Nonnull LObjBoolPredicate.LBoolObjPred<? super V> operator, V v, @Nonnull String message) {
		Null.nonNullArg(operator, "operator");
		LObjBoolPredicate.throwIfNot(v, get(), operator, checkTraitFactory(), MESSAGE_S_S_S_S_1, checkTraitType(), checkTraitName(), message, v, get());
		return self();
	}

	default @Nonnull <V> SELF must_(@Nonnull LObjBoolPredicate.LBoolObjPred<? super V> operator, V v, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(operator, "operator");
		LObjBoolPredicate.throwIfNot(v, get(), operator, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull <V> SELF mustX_(@Nonnull LObjBoolPredicate.LBoolObjPred<? super V> operator, V v, @Nonnull ExMF<RuntimeException> ex, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(operator, "operator");
		LObjBoolPredicate.throwIfNot(v, get(), operator, ex, message, messageParams);
		return self();
	}

	default @Nonnull <V> SELF must_(V v, @Nonnull LObjBoolPredicate.LBoolObjPred<? super V> operator, @Nonnull String message) {
		Null.nonNullArg(operator, "operator");
		LObjBoolPredicate.throwIfNot(v, get(), operator, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull <V> SELF must_$(V v, @Nonnull LObjBoolPredicate.LBoolObjPred<? super V> operator, @Nonnull String message) {
		Null.nonNullArg(operator, "operator");
		LObjBoolPredicate.throwIfNot(v, get(), operator, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull <V> SELF must_$$(V v, @Nonnull LObjBoolPredicate.LBoolObjPred<? super V> operator, @Nonnull String message) {
		Null.nonNullArg(operator, "operator");
		LObjBoolPredicate.throwIfNot(v, get(), operator, checkTraitFactory(), MESSAGE_S_S_S_S_1, checkTraitType(), checkTraitName(), message, v, get());
		return self();
	}

	default @Nonnull <V> SELF must_(V v, @Nonnull LObjBoolPredicate.LBoolObjPred<? super V> operator, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(operator, "operator");
		LObjBoolPredicate.throwIfNot(v, get(), operator, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull <V> SELF mustX_(V v, @Nonnull LObjBoolPredicate.LBoolObjPred<? super V> operator, @Nonnull ExMF<RuntimeException> ex, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(operator, "operator");
		LObjBoolPredicate.throwIfNot(v, get(), operator, ex, message, messageParams);
		return self();
	}

	default @Nonnull <V> SELF mustNot_(@Nonnull LObjBoolPredicate.LBoolObjPred<? super V> operator, V v, @Nonnull String message) {
		Null.nonNullArg(operator, "operator");
		LObjBoolPredicate.throwIf(v, get(), operator, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull <V> SELF mustNot_$(@Nonnull LObjBoolPredicate.LBoolObjPred<? super V> operator, V v, @Nonnull String message) {
		Null.nonNullArg(operator, "operator");
		LObjBoolPredicate.throwIf(v, get(), operator, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull <V> SELF mustNot_$$(@Nonnull LObjBoolPredicate.LBoolObjPred<? super V> operator, V v, @Nonnull String message) {
		Null.nonNullArg(operator, "operator");
		LObjBoolPredicate.throwIf(v, get(), operator, checkTraitFactory(), MESSAGE_S_S_S_S_1, checkTraitType(), checkTraitName(), message, v, get());
		return self();
	}

	default @Nonnull <V> SELF mustNot_(@Nonnull LObjBoolPredicate.LBoolObjPred<? super V> operator, V v, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(operator, "operator");
		LObjBoolPredicate.throwIf(v, get(), operator, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull <V> SELF mustXNot_(@Nonnull LObjBoolPredicate.LBoolObjPred<? super V> operator, V v, @Nonnull ExMF<RuntimeException> ex, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(operator, "operator");
		LObjBoolPredicate.throwIf(v, get(), operator, ex, message, messageParams);
		return self();
	}

	default @Nonnull <V> SELF mustNot_(V v, @Nonnull LObjBoolPredicate.LBoolObjPred<? super V> operator, @Nonnull String message) {
		Null.nonNullArg(operator, "operator");
		LObjBoolPredicate.throwIf(v, get(), operator, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull <V> SELF mustNot_$(V v, @Nonnull LObjBoolPredicate.LBoolObjPred<? super V> operator, @Nonnull String message) {
		Null.nonNullArg(operator, "operator");
		LObjBoolPredicate.throwIf(v, get(), operator, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull <V> SELF mustNot_$$(V v, @Nonnull LObjBoolPredicate.LBoolObjPred<? super V> operator, @Nonnull String message) {
		Null.nonNullArg(operator, "operator");
		LObjBoolPredicate.throwIf(v, get(), operator, checkTraitFactory(), MESSAGE_S_S_S_S_1, checkTraitType(), checkTraitName(), message, v, get());
		return self();
	}

	default @Nonnull <V> SELF mustNot_(V v, @Nonnull LObjBoolPredicate.LBoolObjPred<? super V> operator, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(operator, "operator");
		LObjBoolPredicate.throwIf(v, get(), operator, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull <V> SELF mustXNot_(V v, @Nonnull LObjBoolPredicate.LBoolObjPred<? super V> operator, @Nonnull ExMF<RuntimeException> ex, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(operator, "operator");
		LObjBoolPredicate.throwIf(v, get(), operator, ex, message, messageParams);
		return self();
	}

	default @Nonnull <V1> SELF mustWithBool(@Nonnull LObjBoolPredicate<? super V1> operator, V1 with1, @Nonnull String message) {
		Null.nonNullArg(operator, "operator");
		LObjBoolPredicate.throwIfNot(with1, get(), operator, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull <V1> SELF mustWithBool$(@Nonnull LObjBoolPredicate<? super V1> operator, V1 with1, @Nonnull String message) {
		Null.nonNullArg(operator, "operator");
		LObjBoolPredicate.throwIfNot(with1, get(), operator, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull <V1> SELF mustWithBool$$(@Nonnull LObjBoolPredicate<? super V1> operator, V1 with1, @Nonnull String message) {
		Null.nonNullArg(operator, "operator");
		LObjBoolPredicate.throwIfNot(with1, get(), operator, checkTraitFactory(), MESSAGE_S_S_S_S_1, checkTraitType(), checkTraitName(), message, with1, get());
		return self();
	}

	default @Nonnull <V1> SELF mustWithBool(@Nonnull LObjBoolPredicate<? super V1> operator, V1 with1, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(operator, "operator");
		LObjBoolPredicate.throwIfNot(with1, get(), operator, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull <V1> SELF mustXWithBool(@Nonnull LObjBoolPredicate<? super V1> operator, V1 with1, @Nonnull ExMF<RuntimeException> ex, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(operator, "operator");
		LObjBoolPredicate.throwIfNot(with1, get(), operator, ex, message, messageParams);
		return self();
	}

	default @Nonnull <V1> SELF mustWithBool(V1 with1, @Nonnull LObjBoolPredicate<? super V1> operator, @Nonnull String message) {
		Null.nonNullArg(operator, "operator");
		LObjBoolPredicate.throwIfNot(with1, get(), operator, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull <V1> SELF mustWithBool$(V1 with1, @Nonnull LObjBoolPredicate<? super V1> operator, @Nonnull String message) {
		Null.nonNullArg(operator, "operator");
		LObjBoolPredicate.throwIfNot(with1, get(), operator, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull <V1> SELF mustWithBool$$(V1 with1, @Nonnull LObjBoolPredicate<? super V1> operator, @Nonnull String message) {
		Null.nonNullArg(operator, "operator");
		LObjBoolPredicate.throwIfNot(with1, get(), operator, checkTraitFactory(), MESSAGE_S_S_S_S_1, checkTraitType(), checkTraitName(), message, with1, get());
		return self();
	}

	default @Nonnull <V1> SELF mustWithBool(V1 with1, @Nonnull LObjBoolPredicate<? super V1> operator, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(operator, "operator");
		LObjBoolPredicate.throwIfNot(with1, get(), operator, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull <V1> SELF mustXWithBool(V1 with1, @Nonnull LObjBoolPredicate<? super V1> operator, @Nonnull ExMF<RuntimeException> ex, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(operator, "operator");
		LObjBoolPredicate.throwIfNot(with1, get(), operator, ex, message, messageParams);
		return self();
	}

	default @Nonnull <V1> SELF mustNotWithBool(@Nonnull LObjBoolPredicate<? super V1> operator, V1 with1, @Nonnull String message) {
		Null.nonNullArg(operator, "operator");
		LObjBoolPredicate.throwIf(with1, get(), operator, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull <V1> SELF mustNotWithBool$(@Nonnull LObjBoolPredicate<? super V1> operator, V1 with1, @Nonnull String message) {
		Null.nonNullArg(operator, "operator");
		LObjBoolPredicate.throwIf(with1, get(), operator, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull <V1> SELF mustNotWithBool$$(@Nonnull LObjBoolPredicate<? super V1> operator, V1 with1, @Nonnull String message) {
		Null.nonNullArg(operator, "operator");
		LObjBoolPredicate.throwIf(with1, get(), operator, checkTraitFactory(), MESSAGE_S_S_S_S_1, checkTraitType(), checkTraitName(), message, with1, get());
		return self();
	}

	default @Nonnull <V1> SELF mustNotWithBool(@Nonnull LObjBoolPredicate<? super V1> operator, V1 with1, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(operator, "operator");
		LObjBoolPredicate.throwIf(with1, get(), operator, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull <V1> SELF mustXNotWithBool(@Nonnull LObjBoolPredicate<? super V1> operator, V1 with1, @Nonnull ExMF<RuntimeException> ex, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(operator, "operator");
		LObjBoolPredicate.throwIf(with1, get(), operator, ex, message, messageParams);
		return self();
	}

	default @Nonnull <V1> SELF mustNotWithBool(V1 with1, @Nonnull LObjBoolPredicate<? super V1> operator, @Nonnull String message) {
		Null.nonNullArg(operator, "operator");
		LObjBoolPredicate.throwIf(with1, get(), operator, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull <V1> SELF mustNotWithBool$(V1 with1, @Nonnull LObjBoolPredicate<? super V1> operator, @Nonnull String message) {
		Null.nonNullArg(operator, "operator");
		LObjBoolPredicate.throwIf(with1, get(), operator, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull <V1> SELF mustNotWithBool$$(V1 with1, @Nonnull LObjBoolPredicate<? super V1> operator, @Nonnull String message) {
		Null.nonNullArg(operator, "operator");
		LObjBoolPredicate.throwIf(with1, get(), operator, checkTraitFactory(), MESSAGE_S_S_S_S_1, checkTraitType(), checkTraitName(), message, with1, get());
		return self();
	}

	default @Nonnull <V1> SELF mustNotWithBool(V1 with1, @Nonnull LObjBoolPredicate<? super V1> operator, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(operator, "operator");
		LObjBoolPredicate.throwIf(with1, get(), operator, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull <V1> SELF mustXNotWithBool(V1 with1, @Nonnull LObjBoolPredicate<? super V1> operator, @Nonnull ExMF<RuntimeException> ex, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(operator, "operator");
		LObjBoolPredicate.throwIf(with1, get(), operator, ex, message, messageParams);
		return self();
	}

	default @Nonnull <V1, V2> SELF mustWith(@Nonnull LBiObjBoolPredicate<? super V1, ? super V2> operator, V1 with1, V2 with2, @Nonnull String message) {
		Null.nonNullArg(operator, "operator");
		LBiObjBoolPredicate.throwIfNot(with1, with2, get(), operator, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull <V1, V2> SELF mustWith$(@Nonnull LBiObjBoolPredicate<? super V1, ? super V2> operator, V1 with1, V2 with2, @Nonnull String message) {
		Null.nonNullArg(operator, "operator");
		LBiObjBoolPredicate.throwIfNot(with1, with2, get(), operator, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull <V1, V2> SELF mustWith$$(@Nonnull LBiObjBoolPredicate<? super V1, ? super V2> operator, V1 with1, V2 with2, @Nonnull String message) {
		Null.nonNullArg(operator, "operator");
		LBiObjBoolPredicate.throwIfNot(with1, with2, get(), operator, checkTraitFactory(), MESSAGE_S_S_S_S_2, checkTraitType(), checkTraitName(), message, with1, with2, get());
		return self();
	}

	default @Nonnull <V1, V2> SELF mustWith(@Nonnull LBiObjBoolPredicate<? super V1, ? super V2> operator, V1 with1, V2 with2, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(operator, "operator");
		LBiObjBoolPredicate.throwIfNot(with1, with2, get(), operator, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull <V1, V2> SELF mustXWith(@Nonnull LBiObjBoolPredicate<? super V1, ? super V2> operator, V1 with1, V2 with2, @Nonnull ExMF<RuntimeException> ex, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(operator, "operator");
		LBiObjBoolPredicate.throwIfNot(with1, with2, get(), operator, ex, message, messageParams);
		return self();
	}

	default @Nonnull <V1, V2> SELF mustWith(V1 with1, V2 with2, @Nonnull LBiObjBoolPredicate<? super V1, ? super V2> operator, @Nonnull String message) {
		Null.nonNullArg(operator, "operator");
		LBiObjBoolPredicate.throwIfNot(with1, with2, get(), operator, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull <V1, V2> SELF mustWith$(V1 with1, V2 with2, @Nonnull LBiObjBoolPredicate<? super V1, ? super V2> operator, @Nonnull String message) {
		Null.nonNullArg(operator, "operator");
		LBiObjBoolPredicate.throwIfNot(with1, with2, get(), operator, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull <V1, V2> SELF mustWith$$(V1 with1, V2 with2, @Nonnull LBiObjBoolPredicate<? super V1, ? super V2> operator, @Nonnull String message) {
		Null.nonNullArg(operator, "operator");
		LBiObjBoolPredicate.throwIfNot(with1, with2, get(), operator, checkTraitFactory(), MESSAGE_S_S_S_S_2, checkTraitType(), checkTraitName(), message, with1, with2, get());
		return self();
	}

	default @Nonnull <V1, V2> SELF mustWith(V1 with1, V2 with2, @Nonnull LBiObjBoolPredicate<? super V1, ? super V2> operator, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(operator, "operator");
		LBiObjBoolPredicate.throwIfNot(with1, with2, get(), operator, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull <V1, V2> SELF mustXWith(V1 with1, V2 with2, @Nonnull LBiObjBoolPredicate<? super V1, ? super V2> operator, @Nonnull ExMF<RuntimeException> ex, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(operator, "operator");
		LBiObjBoolPredicate.throwIfNot(with1, with2, get(), operator, ex, message, messageParams);
		return self();
	}

	default @Nonnull <V1, V2> SELF mustNotWith(@Nonnull LBiObjBoolPredicate<? super V1, ? super V2> operator, V1 with1, V2 with2, @Nonnull String message) {
		Null.nonNullArg(operator, "operator");
		LBiObjBoolPredicate.throwIf(with1, with2, get(), operator, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull <V1, V2> SELF mustNotWith$(@Nonnull LBiObjBoolPredicate<? super V1, ? super V2> operator, V1 with1, V2 with2, @Nonnull String message) {
		Null.nonNullArg(operator, "operator");
		LBiObjBoolPredicate.throwIf(with1, with2, get(), operator, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull <V1, V2> SELF mustNotWith$$(@Nonnull LBiObjBoolPredicate<? super V1, ? super V2> operator, V1 with1, V2 with2, @Nonnull String message) {
		Null.nonNullArg(operator, "operator");
		LBiObjBoolPredicate.throwIf(with1, with2, get(), operator, checkTraitFactory(), MESSAGE_S_S_S_S_2, checkTraitType(), checkTraitName(), message, with1, with2, get());
		return self();
	}

	default @Nonnull <V1, V2> SELF mustNotWith(@Nonnull LBiObjBoolPredicate<? super V1, ? super V2> operator, V1 with1, V2 with2, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(operator, "operator");
		LBiObjBoolPredicate.throwIf(with1, with2, get(), operator, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull <V1, V2> SELF mustXNotWith(@Nonnull LBiObjBoolPredicate<? super V1, ? super V2> operator, V1 with1, V2 with2, @Nonnull ExMF<RuntimeException> ex, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(operator, "operator");
		LBiObjBoolPredicate.throwIf(with1, with2, get(), operator, ex, message, messageParams);
		return self();
	}

	default @Nonnull <V1, V2> SELF mustNotWith(V1 with1, V2 with2, @Nonnull LBiObjBoolPredicate<? super V1, ? super V2> operator, @Nonnull String message) {
		Null.nonNullArg(operator, "operator");
		LBiObjBoolPredicate.throwIf(with1, with2, get(), operator, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull <V1, V2> SELF mustNotWith$(V1 with1, V2 with2, @Nonnull LBiObjBoolPredicate<? super V1, ? super V2> operator, @Nonnull String message) {
		Null.nonNullArg(operator, "operator");
		LBiObjBoolPredicate.throwIf(with1, with2, get(), operator, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull <V1, V2> SELF mustNotWith$$(V1 with1, V2 with2, @Nonnull LBiObjBoolPredicate<? super V1, ? super V2> operator, @Nonnull String message) {
		Null.nonNullArg(operator, "operator");
		LBiObjBoolPredicate.throwIf(with1, with2, get(), operator, checkTraitFactory(), MESSAGE_S_S_S_S_2, checkTraitType(), checkTraitName(), message, with1, with2, get());
		return self();
	}

	default @Nonnull <V1, V2> SELF mustNotWith(V1 with1, V2 with2, @Nonnull LBiObjBoolPredicate<? super V1, ? super V2> operator, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(operator, "operator");
		LBiObjBoolPredicate.throwIf(with1, with2, get(), operator, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull <V1, V2> SELF mustXNotWith(V1 with1, V2 with2, @Nonnull LBiObjBoolPredicate<? super V1, ? super V2> operator, @Nonnull ExMF<RuntimeException> ex, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(operator, "operator");
		LBiObjBoolPredicate.throwIf(with1, with2, get(), operator, ex, message, messageParams);
		return self();
	}

	default @Nonnull SELF checkWhen(@Nonnull LLogicalOperator pred, @Nonnull LConsumer<SELF> conditionalChecks) {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(conditionalChecks, "conditionalChecks");
		if (pred.apply(get())) {
			conditionalChecks.accept(self());
		};
		return self();
	}

	default @Nonnull SELF checkWhenNot(@Nonnull LLogicalOperator pred, @Nonnull LConsumer<SELF> conditionalChecks) {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(conditionalChecks, "conditionalChecks");
		if (!pred.apply(get())) {
			conditionalChecks.accept(self());
		};
		return self();
	}

	default @Nonnull SELF checkWhen(@Nonnull LLogicalBinaryOperator pred, boolean a2, @Nonnull LConsumer<SELF> conditionalChecks) {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(conditionalChecks, "conditionalChecks");
		if (pred.apply(get(), a2)) {
			conditionalChecks.accept(self());
		};
		return self();
	}

	default @Nonnull SELF checkWhenNot(@Nonnull LLogicalBinaryOperator pred, boolean a2, @Nonnull LConsumer<SELF> conditionalChecks) {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(conditionalChecks, "conditionalChecks");
		if (!pred.apply(get(), a2)) {
			conditionalChecks.accept(self());
		};
		return self();
	}

	default @Nonnull SELF checkWhen(@Nonnull LLogicalTernaryOperator pred, boolean a2, boolean a3, @Nonnull LConsumer<SELF> conditionalChecks) {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(conditionalChecks, "conditionalChecks");
		if (pred.apply(get(), a2, a3)) {
			conditionalChecks.accept(self());
		};
		return self();
	}

	default @Nonnull SELF checkWhenNot(@Nonnull LLogicalTernaryOperator pred, boolean a2, boolean a3, @Nonnull LConsumer<SELF> conditionalChecks) {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(conditionalChecks, "conditionalChecks");
		if (!pred.apply(get(), a2, a3)) {
			conditionalChecks.accept(self());
		};
		return self();
	}

	// </editor-fold>

	default @Nonnull SELF fails(@Nonnull String newMessage) {
		must(LLogicalOperator::alwaysFalse, newMessage);
		return self();
	}

	default @Nonnull SELF fails$(@Nonnull String newMessage) {
		must$(LLogicalOperator::alwaysFalse, newMessage);
		return self();
	}

	default @Nonnull SELF fails(@Nonnull String newMessage, @Nullable Object... messageParams) {
		must(LLogicalOperator::alwaysFalse, newMessage, messageParams);
		return self();
	}

}
