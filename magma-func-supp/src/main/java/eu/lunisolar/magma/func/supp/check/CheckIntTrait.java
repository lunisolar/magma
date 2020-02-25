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

public interface CheckIntTrait<SELF extends CheckIntTrait<SELF>> extends FluentTrait<SELF>, aValue<aInt>, LIntSingle, IntValueTrait<SELF> {

	int get();

	default int value() {
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

	default @Nonnull SELF must(@Nonnull LIntPredicate predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LIntPredicate.throwIfNot(get(), predicate, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull SELF must$(@Nonnull LIntPredicate predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LIntPredicate.throwIfNot(get(), predicate, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull SELF must(@Nonnull LIntPredicate predicate, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LIntPredicate.throwIfNot(get(), predicate, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull SELF mustX(@Nonnull LIntPredicate predicate, @Nonnull ExMF<RuntimeException> ex, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LIntPredicate.throwIfNot(get(), predicate, ex, message, messageParams);
		return self();
	}

	default @Nonnull SELF mustNot(@Nonnull LIntPredicate predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LIntPredicate.throwIf(get(), predicate, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull SELF mustNot$(@Nonnull LIntPredicate predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LIntPredicate.throwIf(get(), predicate, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull SELF mustNot(@Nonnull LIntPredicate predicate, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LIntPredicate.throwIf(get(), predicate, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull SELF mustXNot(@Nonnull LIntPredicate predicate, @Nonnull ExMF<RuntimeException> ex, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LIntPredicate.throwIf(get(), predicate, ex, message, messageParams);
		return self();
	}

	default @Nonnull SELF must(@Nonnull LBiIntPredicate predicate, int a2, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LBiIntPredicate.throwIfNot(get(), a2, predicate, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull SELF must$(@Nonnull LBiIntPredicate predicate, int a2, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LBiIntPredicate.throwIfNot(get(), a2, predicate, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull SELF must$$(@Nonnull LBiIntPredicate predicate, int a2, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LBiIntPredicate.throwIfNot(get(), a2, predicate, checkTraitFactory(), MESSAGE_S_S_S_S_1, checkTraitType(), checkTraitName(), message, a2, get());
		return self();
	}

	default @Nonnull SELF must(@Nonnull LBiIntPredicate predicate, int a2, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LBiIntPredicate.throwIfNot(get(), a2, predicate, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull SELF mustX(@Nonnull LBiIntPredicate predicate, int a2, @Nonnull ExMF<RuntimeException> ex, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LBiIntPredicate.throwIfNot(get(), a2, predicate, ex, message, messageParams);
		return self();
	}

	default @Nonnull SELF must(int a2, @Nonnull LBiIntPredicate predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LBiIntPredicate.throwIfNot(get(), a2, predicate, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull SELF must$(int a2, @Nonnull LBiIntPredicate predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LBiIntPredicate.throwIfNot(get(), a2, predicate, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull SELF must$$(int a2, @Nonnull LBiIntPredicate predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LBiIntPredicate.throwIfNot(get(), a2, predicate, checkTraitFactory(), MESSAGE_S_S_S_S_1, checkTraitType(), checkTraitName(), message, a2, get());
		return self();
	}

	default @Nonnull SELF must(int a2, @Nonnull LBiIntPredicate predicate, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LBiIntPredicate.throwIfNot(get(), a2, predicate, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull SELF mustX(int a2, @Nonnull LBiIntPredicate predicate, @Nonnull ExMF<RuntimeException> ex, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LBiIntPredicate.throwIfNot(get(), a2, predicate, ex, message, messageParams);
		return self();
	}

	default @Nonnull SELF mustNot(@Nonnull LBiIntPredicate predicate, int a2, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LBiIntPredicate.throwIf(get(), a2, predicate, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull SELF mustNot$(@Nonnull LBiIntPredicate predicate, int a2, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LBiIntPredicate.throwIf(get(), a2, predicate, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull SELF mustNot$$(@Nonnull LBiIntPredicate predicate, int a2, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LBiIntPredicate.throwIf(get(), a2, predicate, checkTraitFactory(), MESSAGE_S_S_S_S_1, checkTraitType(), checkTraitName(), message, a2, get());
		return self();
	}

	default @Nonnull SELF mustNot(@Nonnull LBiIntPredicate predicate, int a2, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LBiIntPredicate.throwIf(get(), a2, predicate, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull SELF mustXNot(@Nonnull LBiIntPredicate predicate, int a2, @Nonnull ExMF<RuntimeException> ex, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LBiIntPredicate.throwIf(get(), a2, predicate, ex, message, messageParams);
		return self();
	}

	default @Nonnull SELF mustNot(int a2, @Nonnull LBiIntPredicate predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LBiIntPredicate.throwIf(get(), a2, predicate, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull SELF mustNot$(int a2, @Nonnull LBiIntPredicate predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LBiIntPredicate.throwIf(get(), a2, predicate, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull SELF mustNot$$(int a2, @Nonnull LBiIntPredicate predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LBiIntPredicate.throwIf(get(), a2, predicate, checkTraitFactory(), MESSAGE_S_S_S_S_1, checkTraitType(), checkTraitName(), message, a2, get());
		return self();
	}

	default @Nonnull SELF mustNot(int a2, @Nonnull LBiIntPredicate predicate, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LBiIntPredicate.throwIf(get(), a2, predicate, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull SELF mustXNot(int a2, @Nonnull LBiIntPredicate predicate, @Nonnull ExMF<RuntimeException> ex, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LBiIntPredicate.throwIf(get(), a2, predicate, ex, message, messageParams);
		return self();
	}

	default @Nonnull SELF must(@Nonnull LTriIntPredicate predicate, int a2, int a3, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LTriIntPredicate.throwIfNot(get(), a2, a3, predicate, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull SELF must$(@Nonnull LTriIntPredicate predicate, int a2, int a3, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LTriIntPredicate.throwIfNot(get(), a2, a3, predicate, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull SELF must$$(@Nonnull LTriIntPredicate predicate, int a2, int a3, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LTriIntPredicate.throwIfNot(get(), a2, a3, predicate, checkTraitFactory(), MESSAGE_S_S_S_S_2, checkTraitType(), checkTraitName(), message, a2, a3, get());
		return self();
	}

	default @Nonnull SELF must(@Nonnull LTriIntPredicate predicate, int a2, int a3, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LTriIntPredicate.throwIfNot(get(), a2, a3, predicate, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull SELF mustX(@Nonnull LTriIntPredicate predicate, int a2, int a3, @Nonnull ExMF<RuntimeException> ex, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LTriIntPredicate.throwIfNot(get(), a2, a3, predicate, ex, message, messageParams);
		return self();
	}

	default @Nonnull SELF must(int a2, int a3, @Nonnull LTriIntPredicate predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LTriIntPredicate.throwIfNot(get(), a2, a3, predicate, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull SELF must$(int a2, int a3, @Nonnull LTriIntPredicate predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LTriIntPredicate.throwIfNot(get(), a2, a3, predicate, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull SELF must$$(int a2, int a3, @Nonnull LTriIntPredicate predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LTriIntPredicate.throwIfNot(get(), a2, a3, predicate, checkTraitFactory(), MESSAGE_S_S_S_S_2, checkTraitType(), checkTraitName(), message, a2, a3, get());
		return self();
	}

	default @Nonnull SELF must(int a2, int a3, @Nonnull LTriIntPredicate predicate, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LTriIntPredicate.throwIfNot(get(), a2, a3, predicate, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull SELF mustX(int a2, int a3, @Nonnull LTriIntPredicate predicate, @Nonnull ExMF<RuntimeException> ex, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LTriIntPredicate.throwIfNot(get(), a2, a3, predicate, ex, message, messageParams);
		return self();
	}

	default @Nonnull SELF mustNot(@Nonnull LTriIntPredicate predicate, int a2, int a3, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LTriIntPredicate.throwIf(get(), a2, a3, predicate, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull SELF mustNot$(@Nonnull LTriIntPredicate predicate, int a2, int a3, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LTriIntPredicate.throwIf(get(), a2, a3, predicate, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull SELF mustNot$$(@Nonnull LTriIntPredicate predicate, int a2, int a3, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LTriIntPredicate.throwIf(get(), a2, a3, predicate, checkTraitFactory(), MESSAGE_S_S_S_S_2, checkTraitType(), checkTraitName(), message, a2, a3, get());
		return self();
	}

	default @Nonnull SELF mustNot(@Nonnull LTriIntPredicate predicate, int a2, int a3, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LTriIntPredicate.throwIf(get(), a2, a3, predicate, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull SELF mustXNot(@Nonnull LTriIntPredicate predicate, int a2, int a3, @Nonnull ExMF<RuntimeException> ex, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LTriIntPredicate.throwIf(get(), a2, a3, predicate, ex, message, messageParams);
		return self();
	}

	default @Nonnull SELF mustNot(int a2, int a3, @Nonnull LTriIntPredicate predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LTriIntPredicate.throwIf(get(), a2, a3, predicate, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull SELF mustNot$(int a2, int a3, @Nonnull LTriIntPredicate predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LTriIntPredicate.throwIf(get(), a2, a3, predicate, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull SELF mustNot$$(int a2, int a3, @Nonnull LTriIntPredicate predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LTriIntPredicate.throwIf(get(), a2, a3, predicate, checkTraitFactory(), MESSAGE_S_S_S_S_2, checkTraitType(), checkTraitName(), message, a2, a3, get());
		return self();
	}

	default @Nonnull SELF mustNot(int a2, int a3, @Nonnull LTriIntPredicate predicate, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LTriIntPredicate.throwIf(get(), a2, a3, predicate, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull SELF mustXNot(int a2, int a3, @Nonnull LTriIntPredicate predicate, @Nonnull ExMF<RuntimeException> ex, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LTriIntPredicate.throwIf(get(), a2, a3, predicate, ex, message, messageParams);
		return self();
	}

	default @Nonnull SELF must_Bool(@Nonnull LBoolIntPredicate.LIntBoolPred predicate, boolean v, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LBoolIntPredicate.throwIfNot(v, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull SELF must_Bool$(@Nonnull LBoolIntPredicate.LIntBoolPred predicate, boolean v, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LBoolIntPredicate.throwIfNot(v, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull SELF must_Bool$$(@Nonnull LBoolIntPredicate.LIntBoolPred predicate, boolean v, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LBoolIntPredicate.throwIfNot(v, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S_S_1, checkTraitType(), checkTraitName(), message, v, get());
		return self();
	}

	default @Nonnull SELF must_Bool(@Nonnull LBoolIntPredicate.LIntBoolPred predicate, boolean v, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LBoolIntPredicate.throwIfNot(v, get(), predicate, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull SELF mustX_Bool(@Nonnull LBoolIntPredicate.LIntBoolPred predicate, boolean v, @Nonnull ExMF<RuntimeException> ex, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LBoolIntPredicate.throwIfNot(v, get(), predicate, ex, message, messageParams);
		return self();
	}

	default @Nonnull SELF must_Bool(boolean v, @Nonnull LBoolIntPredicate.LIntBoolPred predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LBoolIntPredicate.throwIfNot(v, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull SELF must_Bool$(boolean v, @Nonnull LBoolIntPredicate.LIntBoolPred predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LBoolIntPredicate.throwIfNot(v, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull SELF must_Bool$$(boolean v, @Nonnull LBoolIntPredicate.LIntBoolPred predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LBoolIntPredicate.throwIfNot(v, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S_S_1, checkTraitType(), checkTraitName(), message, v, get());
		return self();
	}

	default @Nonnull SELF must_Bool(boolean v, @Nonnull LBoolIntPredicate.LIntBoolPred predicate, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LBoolIntPredicate.throwIfNot(v, get(), predicate, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull SELF mustX_Bool(boolean v, @Nonnull LBoolIntPredicate.LIntBoolPred predicate, @Nonnull ExMF<RuntimeException> ex, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LBoolIntPredicate.throwIfNot(v, get(), predicate, ex, message, messageParams);
		return self();
	}

	default @Nonnull SELF mustNot_Bool(@Nonnull LBoolIntPredicate.LIntBoolPred predicate, boolean v, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LBoolIntPredicate.throwIf(v, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull SELF mustNot_Bool$(@Nonnull LBoolIntPredicate.LIntBoolPred predicate, boolean v, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LBoolIntPredicate.throwIf(v, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull SELF mustNot_Bool$$(@Nonnull LBoolIntPredicate.LIntBoolPred predicate, boolean v, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LBoolIntPredicate.throwIf(v, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S_S_1, checkTraitType(), checkTraitName(), message, v, get());
		return self();
	}

	default @Nonnull SELF mustNot_Bool(@Nonnull LBoolIntPredicate.LIntBoolPred predicate, boolean v, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LBoolIntPredicate.throwIf(v, get(), predicate, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull SELF mustXNot_Bool(@Nonnull LBoolIntPredicate.LIntBoolPred predicate, boolean v, @Nonnull ExMF<RuntimeException> ex, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LBoolIntPredicate.throwIf(v, get(), predicate, ex, message, messageParams);
		return self();
	}

	default @Nonnull SELF mustNot_Bool(boolean v, @Nonnull LBoolIntPredicate.LIntBoolPred predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LBoolIntPredicate.throwIf(v, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull SELF mustNot_Bool$(boolean v, @Nonnull LBoolIntPredicate.LIntBoolPred predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LBoolIntPredicate.throwIf(v, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull SELF mustNot_Bool$$(boolean v, @Nonnull LBoolIntPredicate.LIntBoolPred predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LBoolIntPredicate.throwIf(v, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S_S_1, checkTraitType(), checkTraitName(), message, v, get());
		return self();
	}

	default @Nonnull SELF mustNot_Bool(boolean v, @Nonnull LBoolIntPredicate.LIntBoolPred predicate, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LBoolIntPredicate.throwIf(v, get(), predicate, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull SELF mustXNot_Bool(boolean v, @Nonnull LBoolIntPredicate.LIntBoolPred predicate, @Nonnull ExMF<RuntimeException> ex, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LBoolIntPredicate.throwIf(v, get(), predicate, ex, message, messageParams);
		return self();
	}

	default @Nonnull SELF must_Byte(@Nonnull LByteIntPredicate.LIntBytePred predicate, byte v, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LByteIntPredicate.throwIfNot(v, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull SELF must_Byte$(@Nonnull LByteIntPredicate.LIntBytePred predicate, byte v, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LByteIntPredicate.throwIfNot(v, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull SELF must_Byte$$(@Nonnull LByteIntPredicate.LIntBytePred predicate, byte v, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LByteIntPredicate.throwIfNot(v, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S_S_1, checkTraitType(), checkTraitName(), message, v, get());
		return self();
	}

	default @Nonnull SELF must_Byte(@Nonnull LByteIntPredicate.LIntBytePred predicate, byte v, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LByteIntPredicate.throwIfNot(v, get(), predicate, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull SELF mustX_Byte(@Nonnull LByteIntPredicate.LIntBytePred predicate, byte v, @Nonnull ExMF<RuntimeException> ex, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LByteIntPredicate.throwIfNot(v, get(), predicate, ex, message, messageParams);
		return self();
	}

	default @Nonnull SELF must_Byte(byte v, @Nonnull LByteIntPredicate.LIntBytePred predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LByteIntPredicate.throwIfNot(v, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull SELF must_Byte$(byte v, @Nonnull LByteIntPredicate.LIntBytePred predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LByteIntPredicate.throwIfNot(v, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull SELF must_Byte$$(byte v, @Nonnull LByteIntPredicate.LIntBytePred predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LByteIntPredicate.throwIfNot(v, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S_S_1, checkTraitType(), checkTraitName(), message, v, get());
		return self();
	}

	default @Nonnull SELF must_Byte(byte v, @Nonnull LByteIntPredicate.LIntBytePred predicate, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LByteIntPredicate.throwIfNot(v, get(), predicate, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull SELF mustX_Byte(byte v, @Nonnull LByteIntPredicate.LIntBytePred predicate, @Nonnull ExMF<RuntimeException> ex, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LByteIntPredicate.throwIfNot(v, get(), predicate, ex, message, messageParams);
		return self();
	}

	default @Nonnull SELF mustNot_Byte(@Nonnull LByteIntPredicate.LIntBytePred predicate, byte v, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LByteIntPredicate.throwIf(v, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull SELF mustNot_Byte$(@Nonnull LByteIntPredicate.LIntBytePred predicate, byte v, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LByteIntPredicate.throwIf(v, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull SELF mustNot_Byte$$(@Nonnull LByteIntPredicate.LIntBytePred predicate, byte v, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LByteIntPredicate.throwIf(v, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S_S_1, checkTraitType(), checkTraitName(), message, v, get());
		return self();
	}

	default @Nonnull SELF mustNot_Byte(@Nonnull LByteIntPredicate.LIntBytePred predicate, byte v, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LByteIntPredicate.throwIf(v, get(), predicate, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull SELF mustXNot_Byte(@Nonnull LByteIntPredicate.LIntBytePred predicate, byte v, @Nonnull ExMF<RuntimeException> ex, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LByteIntPredicate.throwIf(v, get(), predicate, ex, message, messageParams);
		return self();
	}

	default @Nonnull SELF mustNot_Byte(byte v, @Nonnull LByteIntPredicate.LIntBytePred predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LByteIntPredicate.throwIf(v, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull SELF mustNot_Byte$(byte v, @Nonnull LByteIntPredicate.LIntBytePred predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LByteIntPredicate.throwIf(v, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull SELF mustNot_Byte$$(byte v, @Nonnull LByteIntPredicate.LIntBytePred predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LByteIntPredicate.throwIf(v, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S_S_1, checkTraitType(), checkTraitName(), message, v, get());
		return self();
	}

	default @Nonnull SELF mustNot_Byte(byte v, @Nonnull LByteIntPredicate.LIntBytePred predicate, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LByteIntPredicate.throwIf(v, get(), predicate, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull SELF mustXNot_Byte(byte v, @Nonnull LByteIntPredicate.LIntBytePred predicate, @Nonnull ExMF<RuntimeException> ex, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LByteIntPredicate.throwIf(v, get(), predicate, ex, message, messageParams);
		return self();
	}

	default @Nonnull SELF must_Dbl(@Nonnull LDblIntPredicate.LIntDblPred predicate, double v, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LDblIntPredicate.throwIfNot(v, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull SELF must_Dbl$(@Nonnull LDblIntPredicate.LIntDblPred predicate, double v, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LDblIntPredicate.throwIfNot(v, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull SELF must_Dbl$$(@Nonnull LDblIntPredicate.LIntDblPred predicate, double v, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LDblIntPredicate.throwIfNot(v, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S_S_1, checkTraitType(), checkTraitName(), message, v, get());
		return self();
	}

	default @Nonnull SELF must_Dbl(@Nonnull LDblIntPredicate.LIntDblPred predicate, double v, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LDblIntPredicate.throwIfNot(v, get(), predicate, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull SELF mustX_Dbl(@Nonnull LDblIntPredicate.LIntDblPred predicate, double v, @Nonnull ExMF<RuntimeException> ex, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LDblIntPredicate.throwIfNot(v, get(), predicate, ex, message, messageParams);
		return self();
	}

	default @Nonnull SELF must_Dbl(double v, @Nonnull LDblIntPredicate.LIntDblPred predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LDblIntPredicate.throwIfNot(v, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull SELF must_Dbl$(double v, @Nonnull LDblIntPredicate.LIntDblPred predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LDblIntPredicate.throwIfNot(v, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull SELF must_Dbl$$(double v, @Nonnull LDblIntPredicate.LIntDblPred predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LDblIntPredicate.throwIfNot(v, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S_S_1, checkTraitType(), checkTraitName(), message, v, get());
		return self();
	}

	default @Nonnull SELF must_Dbl(double v, @Nonnull LDblIntPredicate.LIntDblPred predicate, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LDblIntPredicate.throwIfNot(v, get(), predicate, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull SELF mustX_Dbl(double v, @Nonnull LDblIntPredicate.LIntDblPred predicate, @Nonnull ExMF<RuntimeException> ex, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LDblIntPredicate.throwIfNot(v, get(), predicate, ex, message, messageParams);
		return self();
	}

	default @Nonnull SELF mustNot_Dbl(@Nonnull LDblIntPredicate.LIntDblPred predicate, double v, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LDblIntPredicate.throwIf(v, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull SELF mustNot_Dbl$(@Nonnull LDblIntPredicate.LIntDblPred predicate, double v, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LDblIntPredicate.throwIf(v, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull SELF mustNot_Dbl$$(@Nonnull LDblIntPredicate.LIntDblPred predicate, double v, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LDblIntPredicate.throwIf(v, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S_S_1, checkTraitType(), checkTraitName(), message, v, get());
		return self();
	}

	default @Nonnull SELF mustNot_Dbl(@Nonnull LDblIntPredicate.LIntDblPred predicate, double v, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LDblIntPredicate.throwIf(v, get(), predicate, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull SELF mustXNot_Dbl(@Nonnull LDblIntPredicate.LIntDblPred predicate, double v, @Nonnull ExMF<RuntimeException> ex, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LDblIntPredicate.throwIf(v, get(), predicate, ex, message, messageParams);
		return self();
	}

	default @Nonnull SELF mustNot_Dbl(double v, @Nonnull LDblIntPredicate.LIntDblPred predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LDblIntPredicate.throwIf(v, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull SELF mustNot_Dbl$(double v, @Nonnull LDblIntPredicate.LIntDblPred predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LDblIntPredicate.throwIf(v, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull SELF mustNot_Dbl$$(double v, @Nonnull LDblIntPredicate.LIntDblPred predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LDblIntPredicate.throwIf(v, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S_S_1, checkTraitType(), checkTraitName(), message, v, get());
		return self();
	}

	default @Nonnull SELF mustNot_Dbl(double v, @Nonnull LDblIntPredicate.LIntDblPred predicate, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LDblIntPredicate.throwIf(v, get(), predicate, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull SELF mustXNot_Dbl(double v, @Nonnull LDblIntPredicate.LIntDblPred predicate, @Nonnull ExMF<RuntimeException> ex, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LDblIntPredicate.throwIf(v, get(), predicate, ex, message, messageParams);
		return self();
	}

	default @Nonnull SELF must_Char(@Nonnull LCharIntPredicate.LIntCharPred predicate, char v, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LCharIntPredicate.throwIfNot(v, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull SELF must_Char$(@Nonnull LCharIntPredicate.LIntCharPred predicate, char v, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LCharIntPredicate.throwIfNot(v, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull SELF must_Char$$(@Nonnull LCharIntPredicate.LIntCharPred predicate, char v, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LCharIntPredicate.throwIfNot(v, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S_S_1, checkTraitType(), checkTraitName(), message, v, get());
		return self();
	}

	default @Nonnull SELF must_Char(@Nonnull LCharIntPredicate.LIntCharPred predicate, char v, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LCharIntPredicate.throwIfNot(v, get(), predicate, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull SELF mustX_Char(@Nonnull LCharIntPredicate.LIntCharPred predicate, char v, @Nonnull ExMF<RuntimeException> ex, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LCharIntPredicate.throwIfNot(v, get(), predicate, ex, message, messageParams);
		return self();
	}

	default @Nonnull SELF must_Char(char v, @Nonnull LCharIntPredicate.LIntCharPred predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LCharIntPredicate.throwIfNot(v, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull SELF must_Char$(char v, @Nonnull LCharIntPredicate.LIntCharPred predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LCharIntPredicate.throwIfNot(v, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull SELF must_Char$$(char v, @Nonnull LCharIntPredicate.LIntCharPred predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LCharIntPredicate.throwIfNot(v, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S_S_1, checkTraitType(), checkTraitName(), message, v, get());
		return self();
	}

	default @Nonnull SELF must_Char(char v, @Nonnull LCharIntPredicate.LIntCharPred predicate, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LCharIntPredicate.throwIfNot(v, get(), predicate, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull SELF mustX_Char(char v, @Nonnull LCharIntPredicate.LIntCharPred predicate, @Nonnull ExMF<RuntimeException> ex, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LCharIntPredicate.throwIfNot(v, get(), predicate, ex, message, messageParams);
		return self();
	}

	default @Nonnull SELF mustNot_Char(@Nonnull LCharIntPredicate.LIntCharPred predicate, char v, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LCharIntPredicate.throwIf(v, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull SELF mustNot_Char$(@Nonnull LCharIntPredicate.LIntCharPred predicate, char v, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LCharIntPredicate.throwIf(v, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull SELF mustNot_Char$$(@Nonnull LCharIntPredicate.LIntCharPred predicate, char v, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LCharIntPredicate.throwIf(v, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S_S_1, checkTraitType(), checkTraitName(), message, v, get());
		return self();
	}

	default @Nonnull SELF mustNot_Char(@Nonnull LCharIntPredicate.LIntCharPred predicate, char v, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LCharIntPredicate.throwIf(v, get(), predicate, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull SELF mustXNot_Char(@Nonnull LCharIntPredicate.LIntCharPred predicate, char v, @Nonnull ExMF<RuntimeException> ex, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LCharIntPredicate.throwIf(v, get(), predicate, ex, message, messageParams);
		return self();
	}

	default @Nonnull SELF mustNot_Char(char v, @Nonnull LCharIntPredicate.LIntCharPred predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LCharIntPredicate.throwIf(v, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull SELF mustNot_Char$(char v, @Nonnull LCharIntPredicate.LIntCharPred predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LCharIntPredicate.throwIf(v, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull SELF mustNot_Char$$(char v, @Nonnull LCharIntPredicate.LIntCharPred predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LCharIntPredicate.throwIf(v, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S_S_1, checkTraitType(), checkTraitName(), message, v, get());
		return self();
	}

	default @Nonnull SELF mustNot_Char(char v, @Nonnull LCharIntPredicate.LIntCharPred predicate, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LCharIntPredicate.throwIf(v, get(), predicate, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull SELF mustXNot_Char(char v, @Nonnull LCharIntPredicate.LIntCharPred predicate, @Nonnull ExMF<RuntimeException> ex, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LCharIntPredicate.throwIf(v, get(), predicate, ex, message, messageParams);
		return self();
	}

	default @Nonnull SELF must_Srt(@Nonnull LSrtIntPredicate.LIntSrtPred predicate, short v, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LSrtIntPredicate.throwIfNot(v, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull SELF must_Srt$(@Nonnull LSrtIntPredicate.LIntSrtPred predicate, short v, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LSrtIntPredicate.throwIfNot(v, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull SELF must_Srt$$(@Nonnull LSrtIntPredicate.LIntSrtPred predicate, short v, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LSrtIntPredicate.throwIfNot(v, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S_S_1, checkTraitType(), checkTraitName(), message, v, get());
		return self();
	}

	default @Nonnull SELF must_Srt(@Nonnull LSrtIntPredicate.LIntSrtPred predicate, short v, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LSrtIntPredicate.throwIfNot(v, get(), predicate, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull SELF mustX_Srt(@Nonnull LSrtIntPredicate.LIntSrtPred predicate, short v, @Nonnull ExMF<RuntimeException> ex, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LSrtIntPredicate.throwIfNot(v, get(), predicate, ex, message, messageParams);
		return self();
	}

	default @Nonnull SELF must_Srt(short v, @Nonnull LSrtIntPredicate.LIntSrtPred predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LSrtIntPredicate.throwIfNot(v, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull SELF must_Srt$(short v, @Nonnull LSrtIntPredicate.LIntSrtPred predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LSrtIntPredicate.throwIfNot(v, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull SELF must_Srt$$(short v, @Nonnull LSrtIntPredicate.LIntSrtPred predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LSrtIntPredicate.throwIfNot(v, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S_S_1, checkTraitType(), checkTraitName(), message, v, get());
		return self();
	}

	default @Nonnull SELF must_Srt(short v, @Nonnull LSrtIntPredicate.LIntSrtPred predicate, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LSrtIntPredicate.throwIfNot(v, get(), predicate, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull SELF mustX_Srt(short v, @Nonnull LSrtIntPredicate.LIntSrtPred predicate, @Nonnull ExMF<RuntimeException> ex, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LSrtIntPredicate.throwIfNot(v, get(), predicate, ex, message, messageParams);
		return self();
	}

	default @Nonnull SELF mustNot_Srt(@Nonnull LSrtIntPredicate.LIntSrtPred predicate, short v, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LSrtIntPredicate.throwIf(v, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull SELF mustNot_Srt$(@Nonnull LSrtIntPredicate.LIntSrtPred predicate, short v, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LSrtIntPredicate.throwIf(v, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull SELF mustNot_Srt$$(@Nonnull LSrtIntPredicate.LIntSrtPred predicate, short v, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LSrtIntPredicate.throwIf(v, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S_S_1, checkTraitType(), checkTraitName(), message, v, get());
		return self();
	}

	default @Nonnull SELF mustNot_Srt(@Nonnull LSrtIntPredicate.LIntSrtPred predicate, short v, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LSrtIntPredicate.throwIf(v, get(), predicate, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull SELF mustXNot_Srt(@Nonnull LSrtIntPredicate.LIntSrtPred predicate, short v, @Nonnull ExMF<RuntimeException> ex, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LSrtIntPredicate.throwIf(v, get(), predicate, ex, message, messageParams);
		return self();
	}

	default @Nonnull SELF mustNot_Srt(short v, @Nonnull LSrtIntPredicate.LIntSrtPred predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LSrtIntPredicate.throwIf(v, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull SELF mustNot_Srt$(short v, @Nonnull LSrtIntPredicate.LIntSrtPred predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LSrtIntPredicate.throwIf(v, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull SELF mustNot_Srt$$(short v, @Nonnull LSrtIntPredicate.LIntSrtPred predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LSrtIntPredicate.throwIf(v, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S_S_1, checkTraitType(), checkTraitName(), message, v, get());
		return self();
	}

	default @Nonnull SELF mustNot_Srt(short v, @Nonnull LSrtIntPredicate.LIntSrtPred predicate, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LSrtIntPredicate.throwIf(v, get(), predicate, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull SELF mustXNot_Srt(short v, @Nonnull LSrtIntPredicate.LIntSrtPred predicate, @Nonnull ExMF<RuntimeException> ex, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LSrtIntPredicate.throwIf(v, get(), predicate, ex, message, messageParams);
		return self();
	}

	default @Nonnull SELF must_Flt(@Nonnull LFltIntPredicate.LIntFltPred predicate, float v, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LFltIntPredicate.throwIfNot(v, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull SELF must_Flt$(@Nonnull LFltIntPredicate.LIntFltPred predicate, float v, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LFltIntPredicate.throwIfNot(v, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull SELF must_Flt$$(@Nonnull LFltIntPredicate.LIntFltPred predicate, float v, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LFltIntPredicate.throwIfNot(v, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S_S_1, checkTraitType(), checkTraitName(), message, v, get());
		return self();
	}

	default @Nonnull SELF must_Flt(@Nonnull LFltIntPredicate.LIntFltPred predicate, float v, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LFltIntPredicate.throwIfNot(v, get(), predicate, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull SELF mustX_Flt(@Nonnull LFltIntPredicate.LIntFltPred predicate, float v, @Nonnull ExMF<RuntimeException> ex, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LFltIntPredicate.throwIfNot(v, get(), predicate, ex, message, messageParams);
		return self();
	}

	default @Nonnull SELF must_Flt(float v, @Nonnull LFltIntPredicate.LIntFltPred predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LFltIntPredicate.throwIfNot(v, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull SELF must_Flt$(float v, @Nonnull LFltIntPredicate.LIntFltPred predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LFltIntPredicate.throwIfNot(v, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull SELF must_Flt$$(float v, @Nonnull LFltIntPredicate.LIntFltPred predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LFltIntPredicate.throwIfNot(v, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S_S_1, checkTraitType(), checkTraitName(), message, v, get());
		return self();
	}

	default @Nonnull SELF must_Flt(float v, @Nonnull LFltIntPredicate.LIntFltPred predicate, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LFltIntPredicate.throwIfNot(v, get(), predicate, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull SELF mustX_Flt(float v, @Nonnull LFltIntPredicate.LIntFltPred predicate, @Nonnull ExMF<RuntimeException> ex, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LFltIntPredicate.throwIfNot(v, get(), predicate, ex, message, messageParams);
		return self();
	}

	default @Nonnull SELF mustNot_Flt(@Nonnull LFltIntPredicate.LIntFltPred predicate, float v, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LFltIntPredicate.throwIf(v, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull SELF mustNot_Flt$(@Nonnull LFltIntPredicate.LIntFltPred predicate, float v, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LFltIntPredicate.throwIf(v, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull SELF mustNot_Flt$$(@Nonnull LFltIntPredicate.LIntFltPred predicate, float v, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LFltIntPredicate.throwIf(v, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S_S_1, checkTraitType(), checkTraitName(), message, v, get());
		return self();
	}

	default @Nonnull SELF mustNot_Flt(@Nonnull LFltIntPredicate.LIntFltPred predicate, float v, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LFltIntPredicate.throwIf(v, get(), predicate, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull SELF mustXNot_Flt(@Nonnull LFltIntPredicate.LIntFltPred predicate, float v, @Nonnull ExMF<RuntimeException> ex, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LFltIntPredicate.throwIf(v, get(), predicate, ex, message, messageParams);
		return self();
	}

	default @Nonnull SELF mustNot_Flt(float v, @Nonnull LFltIntPredicate.LIntFltPred predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LFltIntPredicate.throwIf(v, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull SELF mustNot_Flt$(float v, @Nonnull LFltIntPredicate.LIntFltPred predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LFltIntPredicate.throwIf(v, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull SELF mustNot_Flt$$(float v, @Nonnull LFltIntPredicate.LIntFltPred predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LFltIntPredicate.throwIf(v, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S_S_1, checkTraitType(), checkTraitName(), message, v, get());
		return self();
	}

	default @Nonnull SELF mustNot_Flt(float v, @Nonnull LFltIntPredicate.LIntFltPred predicate, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LFltIntPredicate.throwIf(v, get(), predicate, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull SELF mustXNot_Flt(float v, @Nonnull LFltIntPredicate.LIntFltPred predicate, @Nonnull ExMF<RuntimeException> ex, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LFltIntPredicate.throwIf(v, get(), predicate, ex, message, messageParams);
		return self();
	}

	default @Nonnull SELF must_Long(@Nonnull LLongIntPredicate.LIntLongPred predicate, long v, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LLongIntPredicate.throwIfNot(v, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull SELF must_Long$(@Nonnull LLongIntPredicate.LIntLongPred predicate, long v, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LLongIntPredicate.throwIfNot(v, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull SELF must_Long$$(@Nonnull LLongIntPredicate.LIntLongPred predicate, long v, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LLongIntPredicate.throwIfNot(v, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S_S_1, checkTraitType(), checkTraitName(), message, v, get());
		return self();
	}

	default @Nonnull SELF must_Long(@Nonnull LLongIntPredicate.LIntLongPred predicate, long v, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LLongIntPredicate.throwIfNot(v, get(), predicate, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull SELF mustX_Long(@Nonnull LLongIntPredicate.LIntLongPred predicate, long v, @Nonnull ExMF<RuntimeException> ex, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LLongIntPredicate.throwIfNot(v, get(), predicate, ex, message, messageParams);
		return self();
	}

	default @Nonnull SELF must_Long(long v, @Nonnull LLongIntPredicate.LIntLongPred predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LLongIntPredicate.throwIfNot(v, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull SELF must_Long$(long v, @Nonnull LLongIntPredicate.LIntLongPred predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LLongIntPredicate.throwIfNot(v, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull SELF must_Long$$(long v, @Nonnull LLongIntPredicate.LIntLongPred predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LLongIntPredicate.throwIfNot(v, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S_S_1, checkTraitType(), checkTraitName(), message, v, get());
		return self();
	}

	default @Nonnull SELF must_Long(long v, @Nonnull LLongIntPredicate.LIntLongPred predicate, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LLongIntPredicate.throwIfNot(v, get(), predicate, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull SELF mustX_Long(long v, @Nonnull LLongIntPredicate.LIntLongPred predicate, @Nonnull ExMF<RuntimeException> ex, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LLongIntPredicate.throwIfNot(v, get(), predicate, ex, message, messageParams);
		return self();
	}

	default @Nonnull SELF mustNot_Long(@Nonnull LLongIntPredicate.LIntLongPred predicate, long v, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LLongIntPredicate.throwIf(v, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull SELF mustNot_Long$(@Nonnull LLongIntPredicate.LIntLongPred predicate, long v, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LLongIntPredicate.throwIf(v, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull SELF mustNot_Long$$(@Nonnull LLongIntPredicate.LIntLongPred predicate, long v, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LLongIntPredicate.throwIf(v, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S_S_1, checkTraitType(), checkTraitName(), message, v, get());
		return self();
	}

	default @Nonnull SELF mustNot_Long(@Nonnull LLongIntPredicate.LIntLongPred predicate, long v, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LLongIntPredicate.throwIf(v, get(), predicate, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull SELF mustXNot_Long(@Nonnull LLongIntPredicate.LIntLongPred predicate, long v, @Nonnull ExMF<RuntimeException> ex, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LLongIntPredicate.throwIf(v, get(), predicate, ex, message, messageParams);
		return self();
	}

	default @Nonnull SELF mustNot_Long(long v, @Nonnull LLongIntPredicate.LIntLongPred predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LLongIntPredicate.throwIf(v, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull SELF mustNot_Long$(long v, @Nonnull LLongIntPredicate.LIntLongPred predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LLongIntPredicate.throwIf(v, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull SELF mustNot_Long$$(long v, @Nonnull LLongIntPredicate.LIntLongPred predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LLongIntPredicate.throwIf(v, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S_S_1, checkTraitType(), checkTraitName(), message, v, get());
		return self();
	}

	default @Nonnull SELF mustNot_Long(long v, @Nonnull LLongIntPredicate.LIntLongPred predicate, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LLongIntPredicate.throwIf(v, get(), predicate, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull SELF mustXNot_Long(long v, @Nonnull LLongIntPredicate.LIntLongPred predicate, @Nonnull ExMF<RuntimeException> ex, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LLongIntPredicate.throwIf(v, get(), predicate, ex, message, messageParams);
		return self();
	}

	default @Nonnull <V> SELF must_(@Nonnull LObjIntPredicate.LIntObjPred<? super V> predicate, V v, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LObjIntPredicate.throwIfNot(v, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull <V> SELF must_$(@Nonnull LObjIntPredicate.LIntObjPred<? super V> predicate, V v, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LObjIntPredicate.throwIfNot(v, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull <V> SELF must_$$(@Nonnull LObjIntPredicate.LIntObjPred<? super V> predicate, V v, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LObjIntPredicate.throwIfNot(v, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S_S_1, checkTraitType(), checkTraitName(), message, v, get());
		return self();
	}

	default @Nonnull <V> SELF must_(@Nonnull LObjIntPredicate.LIntObjPred<? super V> predicate, V v, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LObjIntPredicate.throwIfNot(v, get(), predicate, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull <V> SELF mustX_(@Nonnull LObjIntPredicate.LIntObjPred<? super V> predicate, V v, @Nonnull ExMF<RuntimeException> ex, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LObjIntPredicate.throwIfNot(v, get(), predicate, ex, message, messageParams);
		return self();
	}

	default @Nonnull <V> SELF must_(V v, @Nonnull LObjIntPredicate.LIntObjPred<? super V> predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LObjIntPredicate.throwIfNot(v, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull <V> SELF must_$(V v, @Nonnull LObjIntPredicate.LIntObjPred<? super V> predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LObjIntPredicate.throwIfNot(v, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull <V> SELF must_$$(V v, @Nonnull LObjIntPredicate.LIntObjPred<? super V> predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LObjIntPredicate.throwIfNot(v, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S_S_1, checkTraitType(), checkTraitName(), message, v, get());
		return self();
	}

	default @Nonnull <V> SELF must_(V v, @Nonnull LObjIntPredicate.LIntObjPred<? super V> predicate, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LObjIntPredicate.throwIfNot(v, get(), predicate, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull <V> SELF mustX_(V v, @Nonnull LObjIntPredicate.LIntObjPred<? super V> predicate, @Nonnull ExMF<RuntimeException> ex, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LObjIntPredicate.throwIfNot(v, get(), predicate, ex, message, messageParams);
		return self();
	}

	default @Nonnull <V> SELF mustNot_(@Nonnull LObjIntPredicate.LIntObjPred<? super V> predicate, V v, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LObjIntPredicate.throwIf(v, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull <V> SELF mustNot_$(@Nonnull LObjIntPredicate.LIntObjPred<? super V> predicate, V v, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LObjIntPredicate.throwIf(v, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull <V> SELF mustNot_$$(@Nonnull LObjIntPredicate.LIntObjPred<? super V> predicate, V v, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LObjIntPredicate.throwIf(v, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S_S_1, checkTraitType(), checkTraitName(), message, v, get());
		return self();
	}

	default @Nonnull <V> SELF mustNot_(@Nonnull LObjIntPredicate.LIntObjPred<? super V> predicate, V v, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LObjIntPredicate.throwIf(v, get(), predicate, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull <V> SELF mustXNot_(@Nonnull LObjIntPredicate.LIntObjPred<? super V> predicate, V v, @Nonnull ExMF<RuntimeException> ex, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LObjIntPredicate.throwIf(v, get(), predicate, ex, message, messageParams);
		return self();
	}

	default @Nonnull <V> SELF mustNot_(V v, @Nonnull LObjIntPredicate.LIntObjPred<? super V> predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LObjIntPredicate.throwIf(v, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull <V> SELF mustNot_$(V v, @Nonnull LObjIntPredicate.LIntObjPred<? super V> predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LObjIntPredicate.throwIf(v, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull <V> SELF mustNot_$$(V v, @Nonnull LObjIntPredicate.LIntObjPred<? super V> predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LObjIntPredicate.throwIf(v, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S_S_1, checkTraitType(), checkTraitName(), message, v, get());
		return self();
	}

	default @Nonnull <V> SELF mustNot_(V v, @Nonnull LObjIntPredicate.LIntObjPred<? super V> predicate, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LObjIntPredicate.throwIf(v, get(), predicate, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull <V> SELF mustXNot_(V v, @Nonnull LObjIntPredicate.LIntObjPred<? super V> predicate, @Nonnull ExMF<RuntimeException> ex, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LObjIntPredicate.throwIf(v, get(), predicate, ex, message, messageParams);
		return self();
	}

	default @Nonnull <V1> SELF mustWithInt(@Nonnull LObjIntPredicate<? super V1> predicate, V1 with, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LObjIntPredicate.throwIfNot(with, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull <V1> SELF mustWithInt$(@Nonnull LObjIntPredicate<? super V1> predicate, V1 with, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LObjIntPredicate.throwIfNot(with, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull <V1> SELF mustWithInt$$(@Nonnull LObjIntPredicate<? super V1> predicate, V1 with, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LObjIntPredicate.throwIfNot(with, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S_S_1, checkTraitType(), checkTraitName(), message, with, get());
		return self();
	}

	default @Nonnull <V1> SELF mustWithInt(@Nonnull LObjIntPredicate<? super V1> predicate, V1 with, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LObjIntPredicate.throwIfNot(with, get(), predicate, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull <V1> SELF mustXWithInt(@Nonnull LObjIntPredicate<? super V1> predicate, V1 with, @Nonnull ExMF<RuntimeException> ex, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LObjIntPredicate.throwIfNot(with, get(), predicate, ex, message, messageParams);
		return self();
	}

	default @Nonnull <V1> SELF mustWithInt(V1 with, @Nonnull LObjIntPredicate<? super V1> predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LObjIntPredicate.throwIfNot(with, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull <V1> SELF mustWithInt$(V1 with, @Nonnull LObjIntPredicate<? super V1> predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LObjIntPredicate.throwIfNot(with, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull <V1> SELF mustWithInt$$(V1 with, @Nonnull LObjIntPredicate<? super V1> predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LObjIntPredicate.throwIfNot(with, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S_S_1, checkTraitType(), checkTraitName(), message, with, get());
		return self();
	}

	default @Nonnull <V1> SELF mustWithInt(V1 with, @Nonnull LObjIntPredicate<? super V1> predicate, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LObjIntPredicate.throwIfNot(with, get(), predicate, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull <V1> SELF mustXWithInt(V1 with, @Nonnull LObjIntPredicate<? super V1> predicate, @Nonnull ExMF<RuntimeException> ex, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LObjIntPredicate.throwIfNot(with, get(), predicate, ex, message, messageParams);
		return self();
	}

	default @Nonnull <V1> SELF mustNotWithInt(@Nonnull LObjIntPredicate<? super V1> predicate, V1 with, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LObjIntPredicate.throwIf(with, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull <V1> SELF mustNotWithInt$(@Nonnull LObjIntPredicate<? super V1> predicate, V1 with, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LObjIntPredicate.throwIf(with, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull <V1> SELF mustNotWithInt$$(@Nonnull LObjIntPredicate<? super V1> predicate, V1 with, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LObjIntPredicate.throwIf(with, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S_S_1, checkTraitType(), checkTraitName(), message, with, get());
		return self();
	}

	default @Nonnull <V1> SELF mustNotWithInt(@Nonnull LObjIntPredicate<? super V1> predicate, V1 with, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LObjIntPredicate.throwIf(with, get(), predicate, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull <V1> SELF mustXNotWithInt(@Nonnull LObjIntPredicate<? super V1> predicate, V1 with, @Nonnull ExMF<RuntimeException> ex, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LObjIntPredicate.throwIf(with, get(), predicate, ex, message, messageParams);
		return self();
	}

	default @Nonnull <V1> SELF mustNotWithInt(V1 with, @Nonnull LObjIntPredicate<? super V1> predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LObjIntPredicate.throwIf(with, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull <V1> SELF mustNotWithInt$(V1 with, @Nonnull LObjIntPredicate<? super V1> predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LObjIntPredicate.throwIf(with, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull <V1> SELF mustNotWithInt$$(V1 with, @Nonnull LObjIntPredicate<? super V1> predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LObjIntPredicate.throwIf(with, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S_S_1, checkTraitType(), checkTraitName(), message, with, get());
		return self();
	}

	default @Nonnull <V1> SELF mustNotWithInt(V1 with, @Nonnull LObjIntPredicate<? super V1> predicate, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LObjIntPredicate.throwIf(with, get(), predicate, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull <V1> SELF mustXNotWithInt(V1 with, @Nonnull LObjIntPredicate<? super V1> predicate, @Nonnull ExMF<RuntimeException> ex, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LObjIntPredicate.throwIf(with, get(), predicate, ex, message, messageParams);
		return self();
	}

	default @Nonnull SELF checkWhen(@Nonnull LIntPredicate pred, @Nonnull LConsumer<SELF> conditionalChecks) {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(conditionalChecks, "conditionalChecks");
		if (pred.test(get())) {
			conditionalChecks.accept(self());
		};
		return self();
	}

	default @Nonnull SELF checkWhenNot(@Nonnull LIntPredicate pred, @Nonnull LConsumer<SELF> conditionalChecks) {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(conditionalChecks, "conditionalChecks");
		if (!pred.test(get())) {
			conditionalChecks.accept(self());
		};
		return self();
	}

	default @Nonnull SELF checkWhen(@Nonnull LBiIntPredicate pred, int a2, @Nonnull LConsumer<SELF> conditionalChecks) {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(conditionalChecks, "conditionalChecks");
		if (pred.test(get(), a2)) {
			conditionalChecks.accept(self());
		};
		return self();
	}

	default @Nonnull SELF checkWhenNot(@Nonnull LBiIntPredicate pred, int a2, @Nonnull LConsumer<SELF> conditionalChecks) {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(conditionalChecks, "conditionalChecks");
		if (!pred.test(get(), a2)) {
			conditionalChecks.accept(self());
		};
		return self();
	}

	default @Nonnull SELF checkWhen(@Nonnull LTriIntPredicate pred, int a2, int a3, @Nonnull LConsumer<SELF> conditionalChecks) {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(conditionalChecks, "conditionalChecks");
		if (pred.test(get(), a2, a3)) {
			conditionalChecks.accept(self());
		};
		return self();
	}

	default @Nonnull SELF checkWhenNot(@Nonnull LTriIntPredicate pred, int a2, int a3, @Nonnull LConsumer<SELF> conditionalChecks) {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(conditionalChecks, "conditionalChecks");
		if (!pred.test(get(), a2, a3)) {
			conditionalChecks.accept(self());
		};
		return self();
	}

	// </editor-fold>

	default @Nonnull SELF fails(@Nonnull String newMessage) {
		must(LIntPredicate::alwaysFalse, newMessage);
		return self();
	}

	default @Nonnull SELF fails$(@Nonnull String newMessage) {
		must$(LIntPredicate::alwaysFalse, newMessage);
		return self();
	}

	default @Nonnull SELF fails(@Nonnull String newMessage, @Nullable Object... messageParams) {
		must(LIntPredicate::alwaysFalse, newMessage, messageParams);
		return self();
	}

}
