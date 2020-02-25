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

package eu.lunisolar.magma.func.supp.check;

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
import eu.lunisolar.magma.func.supp.memento.*; // NOSONAR
import eu.lunisolar.magma.func.supp.traits.*; // NOSONAR
import eu.lunisolar.magma.func.tuple.*; // NOSONAR
import eu.lunisolar.magma.basics.fluent.FluentSyntax;

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

import static eu.lunisolar.magma.func.supp.check.CheckTrait.*;

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

	default @Nonnull SELF must(@Nonnull LLogicalOperator predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LLogicalOperator.throwIfNot(get(), predicate, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull SELF must$(@Nonnull LLogicalOperator predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LLogicalOperator.throwIfNot(get(), predicate, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull SELF must(@Nonnull LLogicalOperator predicate, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LLogicalOperator.throwIfNot(get(), predicate, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull SELF mustX(@Nonnull LLogicalOperator predicate, @Nonnull ExMF<RuntimeException> ex, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LLogicalOperator.throwIfNot(get(), predicate, ex, message, messageParams);
		return self();
	}

	default @Nonnull SELF mustNot(@Nonnull LLogicalOperator predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LLogicalOperator.throwIf(get(), predicate, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull SELF mustNot$(@Nonnull LLogicalOperator predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LLogicalOperator.throwIf(get(), predicate, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull SELF mustNot(@Nonnull LLogicalOperator predicate, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LLogicalOperator.throwIf(get(), predicate, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull SELF mustXNot(@Nonnull LLogicalOperator predicate, @Nonnull ExMF<RuntimeException> ex, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LLogicalOperator.throwIf(get(), predicate, ex, message, messageParams);
		return self();
	}

	default @Nonnull SELF must(@Nonnull LLogicalBinaryOperator predicate, boolean a2, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LLogicalBinaryOperator.throwIfNot(get(), a2, predicate, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull SELF must$(@Nonnull LLogicalBinaryOperator predicate, boolean a2, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LLogicalBinaryOperator.throwIfNot(get(), a2, predicate, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull SELF must$$(@Nonnull LLogicalBinaryOperator predicate, boolean a2, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LLogicalBinaryOperator.throwIfNot(get(), a2, predicate, checkTraitFactory(), MESSAGE_S_S_S_S_1, checkTraitType(), checkTraitName(), message, a2, get());
		return self();
	}

	default @Nonnull SELF must(@Nonnull LLogicalBinaryOperator predicate, boolean a2, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LLogicalBinaryOperator.throwIfNot(get(), a2, predicate, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull SELF mustX(@Nonnull LLogicalBinaryOperator predicate, boolean a2, @Nonnull ExMF<RuntimeException> ex, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LLogicalBinaryOperator.throwIfNot(get(), a2, predicate, ex, message, messageParams);
		return self();
	}

	default @Nonnull SELF must(boolean a2, @Nonnull LLogicalBinaryOperator predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LLogicalBinaryOperator.throwIfNot(get(), a2, predicate, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull SELF must$(boolean a2, @Nonnull LLogicalBinaryOperator predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LLogicalBinaryOperator.throwIfNot(get(), a2, predicate, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull SELF must$$(boolean a2, @Nonnull LLogicalBinaryOperator predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LLogicalBinaryOperator.throwIfNot(get(), a2, predicate, checkTraitFactory(), MESSAGE_S_S_S_S_1, checkTraitType(), checkTraitName(), message, a2, get());
		return self();
	}

	default @Nonnull SELF must(boolean a2, @Nonnull LLogicalBinaryOperator predicate, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LLogicalBinaryOperator.throwIfNot(get(), a2, predicate, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull SELF mustX(boolean a2, @Nonnull LLogicalBinaryOperator predicate, @Nonnull ExMF<RuntimeException> ex, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LLogicalBinaryOperator.throwIfNot(get(), a2, predicate, ex, message, messageParams);
		return self();
	}

	default @Nonnull SELF mustNot(@Nonnull LLogicalBinaryOperator predicate, boolean a2, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LLogicalBinaryOperator.throwIf(get(), a2, predicate, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull SELF mustNot$(@Nonnull LLogicalBinaryOperator predicate, boolean a2, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LLogicalBinaryOperator.throwIf(get(), a2, predicate, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull SELF mustNot$$(@Nonnull LLogicalBinaryOperator predicate, boolean a2, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LLogicalBinaryOperator.throwIf(get(), a2, predicate, checkTraitFactory(), MESSAGE_S_S_S_S_1, checkTraitType(), checkTraitName(), message, a2, get());
		return self();
	}

	default @Nonnull SELF mustNot(@Nonnull LLogicalBinaryOperator predicate, boolean a2, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LLogicalBinaryOperator.throwIf(get(), a2, predicate, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull SELF mustXNot(@Nonnull LLogicalBinaryOperator predicate, boolean a2, @Nonnull ExMF<RuntimeException> ex, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LLogicalBinaryOperator.throwIf(get(), a2, predicate, ex, message, messageParams);
		return self();
	}

	default @Nonnull SELF mustNot(boolean a2, @Nonnull LLogicalBinaryOperator predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LLogicalBinaryOperator.throwIf(get(), a2, predicate, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull SELF mustNot$(boolean a2, @Nonnull LLogicalBinaryOperator predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LLogicalBinaryOperator.throwIf(get(), a2, predicate, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull SELF mustNot$$(boolean a2, @Nonnull LLogicalBinaryOperator predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LLogicalBinaryOperator.throwIf(get(), a2, predicate, checkTraitFactory(), MESSAGE_S_S_S_S_1, checkTraitType(), checkTraitName(), message, a2, get());
		return self();
	}

	default @Nonnull SELF mustNot(boolean a2, @Nonnull LLogicalBinaryOperator predicate, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LLogicalBinaryOperator.throwIf(get(), a2, predicate, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull SELF mustXNot(boolean a2, @Nonnull LLogicalBinaryOperator predicate, @Nonnull ExMF<RuntimeException> ex, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LLogicalBinaryOperator.throwIf(get(), a2, predicate, ex, message, messageParams);
		return self();
	}

	default @Nonnull SELF must(@Nonnull LLogicalTernaryOperator predicate, boolean a2, boolean a3, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LLogicalTernaryOperator.throwIfNot(get(), a2, a3, predicate, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull SELF must$(@Nonnull LLogicalTernaryOperator predicate, boolean a2, boolean a3, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LLogicalTernaryOperator.throwIfNot(get(), a2, a3, predicate, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull SELF must$$(@Nonnull LLogicalTernaryOperator predicate, boolean a2, boolean a3, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LLogicalTernaryOperator.throwIfNot(get(), a2, a3, predicate, checkTraitFactory(), MESSAGE_S_S_S_S_2, checkTraitType(), checkTraitName(), message, a2, a3, get());
		return self();
	}

	default @Nonnull SELF must(@Nonnull LLogicalTernaryOperator predicate, boolean a2, boolean a3, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LLogicalTernaryOperator.throwIfNot(get(), a2, a3, predicate, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull SELF mustX(@Nonnull LLogicalTernaryOperator predicate, boolean a2, boolean a3, @Nonnull ExMF<RuntimeException> ex, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LLogicalTernaryOperator.throwIfNot(get(), a2, a3, predicate, ex, message, messageParams);
		return self();
	}

	default @Nonnull SELF must(boolean a2, boolean a3, @Nonnull LLogicalTernaryOperator predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LLogicalTernaryOperator.throwIfNot(get(), a2, a3, predicate, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull SELF must$(boolean a2, boolean a3, @Nonnull LLogicalTernaryOperator predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LLogicalTernaryOperator.throwIfNot(get(), a2, a3, predicate, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull SELF must$$(boolean a2, boolean a3, @Nonnull LLogicalTernaryOperator predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LLogicalTernaryOperator.throwIfNot(get(), a2, a3, predicate, checkTraitFactory(), MESSAGE_S_S_S_S_2, checkTraitType(), checkTraitName(), message, a2, a3, get());
		return self();
	}

	default @Nonnull SELF must(boolean a2, boolean a3, @Nonnull LLogicalTernaryOperator predicate, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LLogicalTernaryOperator.throwIfNot(get(), a2, a3, predicate, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull SELF mustX(boolean a2, boolean a3, @Nonnull LLogicalTernaryOperator predicate, @Nonnull ExMF<RuntimeException> ex, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LLogicalTernaryOperator.throwIfNot(get(), a2, a3, predicate, ex, message, messageParams);
		return self();
	}

	default @Nonnull SELF mustNot(@Nonnull LLogicalTernaryOperator predicate, boolean a2, boolean a3, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LLogicalTernaryOperator.throwIf(get(), a2, a3, predicate, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull SELF mustNot$(@Nonnull LLogicalTernaryOperator predicate, boolean a2, boolean a3, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LLogicalTernaryOperator.throwIf(get(), a2, a3, predicate, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull SELF mustNot$$(@Nonnull LLogicalTernaryOperator predicate, boolean a2, boolean a3, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LLogicalTernaryOperator.throwIf(get(), a2, a3, predicate, checkTraitFactory(), MESSAGE_S_S_S_S_2, checkTraitType(), checkTraitName(), message, a2, a3, get());
		return self();
	}

	default @Nonnull SELF mustNot(@Nonnull LLogicalTernaryOperator predicate, boolean a2, boolean a3, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LLogicalTernaryOperator.throwIf(get(), a2, a3, predicate, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull SELF mustXNot(@Nonnull LLogicalTernaryOperator predicate, boolean a2, boolean a3, @Nonnull ExMF<RuntimeException> ex, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LLogicalTernaryOperator.throwIf(get(), a2, a3, predicate, ex, message, messageParams);
		return self();
	}

	default @Nonnull SELF mustNot(boolean a2, boolean a3, @Nonnull LLogicalTernaryOperator predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LLogicalTernaryOperator.throwIf(get(), a2, a3, predicate, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull SELF mustNot$(boolean a2, boolean a3, @Nonnull LLogicalTernaryOperator predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LLogicalTernaryOperator.throwIf(get(), a2, a3, predicate, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull SELF mustNot$$(boolean a2, boolean a3, @Nonnull LLogicalTernaryOperator predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LLogicalTernaryOperator.throwIf(get(), a2, a3, predicate, checkTraitFactory(), MESSAGE_S_S_S_S_2, checkTraitType(), checkTraitName(), message, a2, a3, get());
		return self();
	}

	default @Nonnull SELF mustNot(boolean a2, boolean a3, @Nonnull LLogicalTernaryOperator predicate, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LLogicalTernaryOperator.throwIf(get(), a2, a3, predicate, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull SELF mustXNot(boolean a2, boolean a3, @Nonnull LLogicalTernaryOperator predicate, @Nonnull ExMF<RuntimeException> ex, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LLogicalTernaryOperator.throwIf(get(), a2, a3, predicate, ex, message, messageParams);
		return self();
	}

	default @Nonnull SELF mustInt(@Nonnull LBoolIntPredicate predicate, int v, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LBoolIntPredicate.throwIfNot(get(), v, predicate, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull SELF mustInt$(@Nonnull LBoolIntPredicate predicate, int v, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LBoolIntPredicate.throwIfNot(get(), v, predicate, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull SELF mustInt$$(@Nonnull LBoolIntPredicate predicate, int v, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LBoolIntPredicate.throwIfNot(get(), v, predicate, checkTraitFactory(), MESSAGE_S_S_S_S_1, checkTraitType(), checkTraitName(), message, v, get());
		return self();
	}

	default @Nonnull SELF mustInt(@Nonnull LBoolIntPredicate predicate, int v, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LBoolIntPredicate.throwIfNot(get(), v, predicate, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull SELF mustXInt(@Nonnull LBoolIntPredicate predicate, int v, @Nonnull ExMF<RuntimeException> ex, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LBoolIntPredicate.throwIfNot(get(), v, predicate, ex, message, messageParams);
		return self();
	}

	default @Nonnull SELF mustInt(int v, @Nonnull LBoolIntPredicate predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LBoolIntPredicate.throwIfNot(get(), v, predicate, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull SELF mustInt$(int v, @Nonnull LBoolIntPredicate predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LBoolIntPredicate.throwIfNot(get(), v, predicate, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull SELF mustInt$$(int v, @Nonnull LBoolIntPredicate predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LBoolIntPredicate.throwIfNot(get(), v, predicate, checkTraitFactory(), MESSAGE_S_S_S_S_1, checkTraitType(), checkTraitName(), message, v, get());
		return self();
	}

	default @Nonnull SELF mustInt(int v, @Nonnull LBoolIntPredicate predicate, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LBoolIntPredicate.throwIfNot(get(), v, predicate, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull SELF mustXInt(int v, @Nonnull LBoolIntPredicate predicate, @Nonnull ExMF<RuntimeException> ex, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LBoolIntPredicate.throwIfNot(get(), v, predicate, ex, message, messageParams);
		return self();
	}

	default @Nonnull SELF mustNotInt(@Nonnull LBoolIntPredicate predicate, int v, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LBoolIntPredicate.throwIf(get(), v, predicate, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull SELF mustNotInt$(@Nonnull LBoolIntPredicate predicate, int v, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LBoolIntPredicate.throwIf(get(), v, predicate, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull SELF mustNotInt$$(@Nonnull LBoolIntPredicate predicate, int v, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LBoolIntPredicate.throwIf(get(), v, predicate, checkTraitFactory(), MESSAGE_S_S_S_S_1, checkTraitType(), checkTraitName(), message, v, get());
		return self();
	}

	default @Nonnull SELF mustNotInt(@Nonnull LBoolIntPredicate predicate, int v, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LBoolIntPredicate.throwIf(get(), v, predicate, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull SELF mustXNotInt(@Nonnull LBoolIntPredicate predicate, int v, @Nonnull ExMF<RuntimeException> ex, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LBoolIntPredicate.throwIf(get(), v, predicate, ex, message, messageParams);
		return self();
	}

	default @Nonnull SELF mustNotInt(int v, @Nonnull LBoolIntPredicate predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LBoolIntPredicate.throwIf(get(), v, predicate, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull SELF mustNotInt$(int v, @Nonnull LBoolIntPredicate predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LBoolIntPredicate.throwIf(get(), v, predicate, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull SELF mustNotInt$$(int v, @Nonnull LBoolIntPredicate predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LBoolIntPredicate.throwIf(get(), v, predicate, checkTraitFactory(), MESSAGE_S_S_S_S_1, checkTraitType(), checkTraitName(), message, v, get());
		return self();
	}

	default @Nonnull SELF mustNotInt(int v, @Nonnull LBoolIntPredicate predicate, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LBoolIntPredicate.throwIf(get(), v, predicate, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull SELF mustXNotInt(int v, @Nonnull LBoolIntPredicate predicate, @Nonnull ExMF<RuntimeException> ex, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LBoolIntPredicate.throwIf(get(), v, predicate, ex, message, messageParams);
		return self();
	}

	default @Nonnull <V> SELF must_(@Nonnull LObjBoolPredicate.LBoolObjPred<? super V> predicate, V v, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LObjBoolPredicate.throwIfNot(v, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull <V> SELF must_$(@Nonnull LObjBoolPredicate.LBoolObjPred<? super V> predicate, V v, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LObjBoolPredicate.throwIfNot(v, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull <V> SELF must_$$(@Nonnull LObjBoolPredicate.LBoolObjPred<? super V> predicate, V v, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LObjBoolPredicate.throwIfNot(v, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S_S_1, checkTraitType(), checkTraitName(), message, v, get());
		return self();
	}

	default @Nonnull <V> SELF must_(@Nonnull LObjBoolPredicate.LBoolObjPred<? super V> predicate, V v, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LObjBoolPredicate.throwIfNot(v, get(), predicate, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull <V> SELF mustX_(@Nonnull LObjBoolPredicate.LBoolObjPred<? super V> predicate, V v, @Nonnull ExMF<RuntimeException> ex, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LObjBoolPredicate.throwIfNot(v, get(), predicate, ex, message, messageParams);
		return self();
	}

	default @Nonnull <V> SELF must_(V v, @Nonnull LObjBoolPredicate.LBoolObjPred<? super V> predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LObjBoolPredicate.throwIfNot(v, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull <V> SELF must_$(V v, @Nonnull LObjBoolPredicate.LBoolObjPred<? super V> predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LObjBoolPredicate.throwIfNot(v, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull <V> SELF must_$$(V v, @Nonnull LObjBoolPredicate.LBoolObjPred<? super V> predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LObjBoolPredicate.throwIfNot(v, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S_S_1, checkTraitType(), checkTraitName(), message, v, get());
		return self();
	}

	default @Nonnull <V> SELF must_(V v, @Nonnull LObjBoolPredicate.LBoolObjPred<? super V> predicate, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LObjBoolPredicate.throwIfNot(v, get(), predicate, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull <V> SELF mustX_(V v, @Nonnull LObjBoolPredicate.LBoolObjPred<? super V> predicate, @Nonnull ExMF<RuntimeException> ex, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LObjBoolPredicate.throwIfNot(v, get(), predicate, ex, message, messageParams);
		return self();
	}

	default @Nonnull <V> SELF mustNot_(@Nonnull LObjBoolPredicate.LBoolObjPred<? super V> predicate, V v, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LObjBoolPredicate.throwIf(v, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull <V> SELF mustNot_$(@Nonnull LObjBoolPredicate.LBoolObjPred<? super V> predicate, V v, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LObjBoolPredicate.throwIf(v, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull <V> SELF mustNot_$$(@Nonnull LObjBoolPredicate.LBoolObjPred<? super V> predicate, V v, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LObjBoolPredicate.throwIf(v, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S_S_1, checkTraitType(), checkTraitName(), message, v, get());
		return self();
	}

	default @Nonnull <V> SELF mustNot_(@Nonnull LObjBoolPredicate.LBoolObjPred<? super V> predicate, V v, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LObjBoolPredicate.throwIf(v, get(), predicate, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull <V> SELF mustXNot_(@Nonnull LObjBoolPredicate.LBoolObjPred<? super V> predicate, V v, @Nonnull ExMF<RuntimeException> ex, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LObjBoolPredicate.throwIf(v, get(), predicate, ex, message, messageParams);
		return self();
	}

	default @Nonnull <V> SELF mustNot_(V v, @Nonnull LObjBoolPredicate.LBoolObjPred<? super V> predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LObjBoolPredicate.throwIf(v, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull <V> SELF mustNot_$(V v, @Nonnull LObjBoolPredicate.LBoolObjPred<? super V> predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LObjBoolPredicate.throwIf(v, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull <V> SELF mustNot_$$(V v, @Nonnull LObjBoolPredicate.LBoolObjPred<? super V> predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LObjBoolPredicate.throwIf(v, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S_S_1, checkTraitType(), checkTraitName(), message, v, get());
		return self();
	}

	default @Nonnull <V> SELF mustNot_(V v, @Nonnull LObjBoolPredicate.LBoolObjPred<? super V> predicate, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LObjBoolPredicate.throwIf(v, get(), predicate, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull <V> SELF mustXNot_(V v, @Nonnull LObjBoolPredicate.LBoolObjPred<? super V> predicate, @Nonnull ExMF<RuntimeException> ex, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LObjBoolPredicate.throwIf(v, get(), predicate, ex, message, messageParams);
		return self();
	}

	default @Nonnull <V1> SELF mustWithBool(@Nonnull LObjBoolPredicate<? super V1> predicate, V1 with, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LObjBoolPredicate.throwIfNot(with, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull <V1> SELF mustWithBool$(@Nonnull LObjBoolPredicate<? super V1> predicate, V1 with, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LObjBoolPredicate.throwIfNot(with, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull <V1> SELF mustWithBool$$(@Nonnull LObjBoolPredicate<? super V1> predicate, V1 with, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LObjBoolPredicate.throwIfNot(with, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S_S_1, checkTraitType(), checkTraitName(), message, with, get());
		return self();
	}

	default @Nonnull <V1> SELF mustWithBool(@Nonnull LObjBoolPredicate<? super V1> predicate, V1 with, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LObjBoolPredicate.throwIfNot(with, get(), predicate, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull <V1> SELF mustXWithBool(@Nonnull LObjBoolPredicate<? super V1> predicate, V1 with, @Nonnull ExMF<RuntimeException> ex, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LObjBoolPredicate.throwIfNot(with, get(), predicate, ex, message, messageParams);
		return self();
	}

	default @Nonnull <V1> SELF mustWithBool(V1 with, @Nonnull LObjBoolPredicate<? super V1> predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LObjBoolPredicate.throwIfNot(with, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull <V1> SELF mustWithBool$(V1 with, @Nonnull LObjBoolPredicate<? super V1> predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LObjBoolPredicate.throwIfNot(with, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull <V1> SELF mustWithBool$$(V1 with, @Nonnull LObjBoolPredicate<? super V1> predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LObjBoolPredicate.throwIfNot(with, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S_S_1, checkTraitType(), checkTraitName(), message, with, get());
		return self();
	}

	default @Nonnull <V1> SELF mustWithBool(V1 with, @Nonnull LObjBoolPredicate<? super V1> predicate, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LObjBoolPredicate.throwIfNot(with, get(), predicate, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull <V1> SELF mustXWithBool(V1 with, @Nonnull LObjBoolPredicate<? super V1> predicate, @Nonnull ExMF<RuntimeException> ex, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LObjBoolPredicate.throwIfNot(with, get(), predicate, ex, message, messageParams);
		return self();
	}

	default @Nonnull <V1> SELF mustNotWithBool(@Nonnull LObjBoolPredicate<? super V1> predicate, V1 with, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LObjBoolPredicate.throwIf(with, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull <V1> SELF mustNotWithBool$(@Nonnull LObjBoolPredicate<? super V1> predicate, V1 with, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LObjBoolPredicate.throwIf(with, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull <V1> SELF mustNotWithBool$$(@Nonnull LObjBoolPredicate<? super V1> predicate, V1 with, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LObjBoolPredicate.throwIf(with, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S_S_1, checkTraitType(), checkTraitName(), message, with, get());
		return self();
	}

	default @Nonnull <V1> SELF mustNotWithBool(@Nonnull LObjBoolPredicate<? super V1> predicate, V1 with, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LObjBoolPredicate.throwIf(with, get(), predicate, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull <V1> SELF mustXNotWithBool(@Nonnull LObjBoolPredicate<? super V1> predicate, V1 with, @Nonnull ExMF<RuntimeException> ex, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LObjBoolPredicate.throwIf(with, get(), predicate, ex, message, messageParams);
		return self();
	}

	default @Nonnull <V1> SELF mustNotWithBool(V1 with, @Nonnull LObjBoolPredicate<? super V1> predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LObjBoolPredicate.throwIf(with, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull <V1> SELF mustNotWithBool$(V1 with, @Nonnull LObjBoolPredicate<? super V1> predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LObjBoolPredicate.throwIf(with, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull <V1> SELF mustNotWithBool$$(V1 with, @Nonnull LObjBoolPredicate<? super V1> predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LObjBoolPredicate.throwIf(with, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S_S_1, checkTraitType(), checkTraitName(), message, with, get());
		return self();
	}

	default @Nonnull <V1> SELF mustNotWithBool(V1 with, @Nonnull LObjBoolPredicate<? super V1> predicate, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LObjBoolPredicate.throwIf(with, get(), predicate, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull <V1> SELF mustXNotWithBool(V1 with, @Nonnull LObjBoolPredicate<? super V1> predicate, @Nonnull ExMF<RuntimeException> ex, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LObjBoolPredicate.throwIf(with, get(), predicate, ex, message, messageParams);
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
