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

	default @Nonnull SELF must(@Nonnull LIntPredicate predicate, LIntFunction<String> msgFunc) {
		Null.nonNullArg(predicate, "predicate");
		LIntPredicate.throwIfNot(get(), predicate, checkTraitFactory(), msgFunc);
		return self();
	}

	default @Nonnull SELF must$(LIntFunction<String> specialPredicate) {
		LIntPredicate.throwIfNot$(get(), specialPredicate, checkTraitFactory());
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

	default @Nonnull SELF mustNot(@Nonnull LIntPredicate predicate, LIntFunction<String> msgFunc) {
		Null.nonNullArg(predicate, "predicate");
		LIntPredicate.throwIf(get(), predicate, checkTraitFactory(), msgFunc);
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

	default @Nonnull SELF must(@Nonnull LBiIntPredicate predicate, int a2, LBiIntFunction<String> msgFunc) {
		Null.nonNullArg(predicate, "predicate");
		LBiIntPredicate.throwIfNot(get(), a2, predicate, checkTraitFactory(), msgFunc);
		return self();
	}

	default @Nonnull SELF must$(LBiIntFunction<String> specialPredicate, int a2) {
		LBiIntPredicate.throwIfNot$(get(), a2, specialPredicate, checkTraitFactory());
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

	default @Nonnull SELF must(int a2, @Nonnull LBiIntPredicate predicate, LBiIntFunction<String> msgFunc) {
		Null.nonNullArg(predicate, "predicate");
		LBiIntPredicate.throwIfNot(get(), a2, predicate, checkTraitFactory(), msgFunc);
		return self();
	}

	default @Nonnull SELF must$(int a2, LBiIntFunction<String> specialPredicate) {
		LBiIntPredicate.throwIfNot$(get(), a2, specialPredicate, checkTraitFactory());
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

	default @Nonnull SELF mustNot(@Nonnull LBiIntPredicate predicate, int a2, LBiIntFunction<String> msgFunc) {
		Null.nonNullArg(predicate, "predicate");
		LBiIntPredicate.throwIf(get(), a2, predicate, checkTraitFactory(), msgFunc);
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

	default @Nonnull SELF mustNot(int a2, @Nonnull LBiIntPredicate predicate, LBiIntFunction<String> msgFunc) {
		Null.nonNullArg(predicate, "predicate");
		LBiIntPredicate.throwIf(get(), a2, predicate, checkTraitFactory(), msgFunc);
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

	default @Nonnull SELF mustBool_(@Nonnull LBoolIntPredicate.LIntBoolPred predicate, boolean v, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LBoolIntPredicate.throwIfNot(v, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull SELF mustBool_$(@Nonnull LBoolIntPredicate.LIntBoolPred predicate, boolean v, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LBoolIntPredicate.throwIfNot(v, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull SELF mustBool_$$(@Nonnull LBoolIntPredicate.LIntBoolPred predicate, boolean v, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LBoolIntPredicate.throwIfNot(v, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S_S_1, checkTraitType(), checkTraitName(), message, v, get());
		return self();
	}

	default @Nonnull SELF mustBool_(@Nonnull LBoolIntPredicate.LIntBoolPred predicate, boolean v, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LBoolIntPredicate.throwIfNot(v, get(), predicate, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull SELF mustXBool_(@Nonnull LBoolIntPredicate.LIntBoolPred predicate, boolean v, @Nonnull ExMF<RuntimeException> ex, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LBoolIntPredicate.throwIfNot(v, get(), predicate, ex, message, messageParams);
		return self();
	}

	default @Nonnull SELF mustBool_(boolean v, @Nonnull LBoolIntPredicate.LIntBoolPred predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LBoolIntPredicate.throwIfNot(v, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull SELF mustBool_$(boolean v, @Nonnull LBoolIntPredicate.LIntBoolPred predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LBoolIntPredicate.throwIfNot(v, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull SELF mustBool_$$(boolean v, @Nonnull LBoolIntPredicate.LIntBoolPred predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LBoolIntPredicate.throwIfNot(v, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S_S_1, checkTraitType(), checkTraitName(), message, v, get());
		return self();
	}

	default @Nonnull SELF mustBool_(boolean v, @Nonnull LBoolIntPredicate.LIntBoolPred predicate, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LBoolIntPredicate.throwIfNot(v, get(), predicate, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull SELF mustXBool_(boolean v, @Nonnull LBoolIntPredicate.LIntBoolPred predicate, @Nonnull ExMF<RuntimeException> ex, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LBoolIntPredicate.throwIfNot(v, get(), predicate, ex, message, messageParams);
		return self();
	}

	default @Nonnull SELF mustNotBool_(@Nonnull LBoolIntPredicate.LIntBoolPred predicate, boolean v, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LBoolIntPredicate.throwIf(v, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull SELF mustNotBool_$(@Nonnull LBoolIntPredicate.LIntBoolPred predicate, boolean v, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LBoolIntPredicate.throwIf(v, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull SELF mustNotBool_$$(@Nonnull LBoolIntPredicate.LIntBoolPred predicate, boolean v, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LBoolIntPredicate.throwIf(v, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S_S_1, checkTraitType(), checkTraitName(), message, v, get());
		return self();
	}

	default @Nonnull SELF mustNotBool_(@Nonnull LBoolIntPredicate.LIntBoolPred predicate, boolean v, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LBoolIntPredicate.throwIf(v, get(), predicate, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull SELF mustXNotBool_(@Nonnull LBoolIntPredicate.LIntBoolPred predicate, boolean v, @Nonnull ExMF<RuntimeException> ex, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LBoolIntPredicate.throwIf(v, get(), predicate, ex, message, messageParams);
		return self();
	}

	default @Nonnull SELF mustNotBool_(boolean v, @Nonnull LBoolIntPredicate.LIntBoolPred predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LBoolIntPredicate.throwIf(v, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull SELF mustNotBool_$(boolean v, @Nonnull LBoolIntPredicate.LIntBoolPred predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LBoolIntPredicate.throwIf(v, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull SELF mustNotBool_$$(boolean v, @Nonnull LBoolIntPredicate.LIntBoolPred predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LBoolIntPredicate.throwIf(v, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S_S_1, checkTraitType(), checkTraitName(), message, v, get());
		return self();
	}

	default @Nonnull SELF mustNotBool_(boolean v, @Nonnull LBoolIntPredicate.LIntBoolPred predicate, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LBoolIntPredicate.throwIf(v, get(), predicate, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull SELF mustXNotBool_(boolean v, @Nonnull LBoolIntPredicate.LIntBoolPred predicate, @Nonnull ExMF<RuntimeException> ex, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LBoolIntPredicate.throwIf(v, get(), predicate, ex, message, messageParams);
		return self();
	}

	default @Nonnull SELF mustByte_(@Nonnull LByteIntPredicate.LIntBytePred predicate, byte v, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LByteIntPredicate.throwIfNot(v, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull SELF mustByte_$(@Nonnull LByteIntPredicate.LIntBytePred predicate, byte v, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LByteIntPredicate.throwIfNot(v, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull SELF mustByte_$$(@Nonnull LByteIntPredicate.LIntBytePred predicate, byte v, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LByteIntPredicate.throwIfNot(v, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S_S_1, checkTraitType(), checkTraitName(), message, v, get());
		return self();
	}

	default @Nonnull SELF mustByte_(@Nonnull LByteIntPredicate.LIntBytePred predicate, byte v, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LByteIntPredicate.throwIfNot(v, get(), predicate, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull SELF mustXByte_(@Nonnull LByteIntPredicate.LIntBytePred predicate, byte v, @Nonnull ExMF<RuntimeException> ex, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LByteIntPredicate.throwIfNot(v, get(), predicate, ex, message, messageParams);
		return self();
	}

	default @Nonnull SELF mustByte_(byte v, @Nonnull LByteIntPredicate.LIntBytePred predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LByteIntPredicate.throwIfNot(v, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull SELF mustByte_$(byte v, @Nonnull LByteIntPredicate.LIntBytePred predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LByteIntPredicate.throwIfNot(v, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull SELF mustByte_$$(byte v, @Nonnull LByteIntPredicate.LIntBytePred predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LByteIntPredicate.throwIfNot(v, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S_S_1, checkTraitType(), checkTraitName(), message, v, get());
		return self();
	}

	default @Nonnull SELF mustByte_(byte v, @Nonnull LByteIntPredicate.LIntBytePred predicate, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LByteIntPredicate.throwIfNot(v, get(), predicate, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull SELF mustXByte_(byte v, @Nonnull LByteIntPredicate.LIntBytePred predicate, @Nonnull ExMF<RuntimeException> ex, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LByteIntPredicate.throwIfNot(v, get(), predicate, ex, message, messageParams);
		return self();
	}

	default @Nonnull SELF mustNotByte_(@Nonnull LByteIntPredicate.LIntBytePred predicate, byte v, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LByteIntPredicate.throwIf(v, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull SELF mustNotByte_$(@Nonnull LByteIntPredicate.LIntBytePred predicate, byte v, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LByteIntPredicate.throwIf(v, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull SELF mustNotByte_$$(@Nonnull LByteIntPredicate.LIntBytePred predicate, byte v, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LByteIntPredicate.throwIf(v, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S_S_1, checkTraitType(), checkTraitName(), message, v, get());
		return self();
	}

	default @Nonnull SELF mustNotByte_(@Nonnull LByteIntPredicate.LIntBytePred predicate, byte v, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LByteIntPredicate.throwIf(v, get(), predicate, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull SELF mustXNotByte_(@Nonnull LByteIntPredicate.LIntBytePred predicate, byte v, @Nonnull ExMF<RuntimeException> ex, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LByteIntPredicate.throwIf(v, get(), predicate, ex, message, messageParams);
		return self();
	}

	default @Nonnull SELF mustNotByte_(byte v, @Nonnull LByteIntPredicate.LIntBytePred predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LByteIntPredicate.throwIf(v, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull SELF mustNotByte_$(byte v, @Nonnull LByteIntPredicate.LIntBytePred predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LByteIntPredicate.throwIf(v, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull SELF mustNotByte_$$(byte v, @Nonnull LByteIntPredicate.LIntBytePred predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LByteIntPredicate.throwIf(v, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S_S_1, checkTraitType(), checkTraitName(), message, v, get());
		return self();
	}

	default @Nonnull SELF mustNotByte_(byte v, @Nonnull LByteIntPredicate.LIntBytePred predicate, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LByteIntPredicate.throwIf(v, get(), predicate, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull SELF mustXNotByte_(byte v, @Nonnull LByteIntPredicate.LIntBytePred predicate, @Nonnull ExMF<RuntimeException> ex, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LByteIntPredicate.throwIf(v, get(), predicate, ex, message, messageParams);
		return self();
	}

	default @Nonnull SELF mustDbl_(@Nonnull LDblIntPredicate.LIntDblPred predicate, double v, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LDblIntPredicate.throwIfNot(v, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull SELF mustDbl_$(@Nonnull LDblIntPredicate.LIntDblPred predicate, double v, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LDblIntPredicate.throwIfNot(v, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull SELF mustDbl_$$(@Nonnull LDblIntPredicate.LIntDblPred predicate, double v, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LDblIntPredicate.throwIfNot(v, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S_S_1, checkTraitType(), checkTraitName(), message, v, get());
		return self();
	}

	default @Nonnull SELF mustDbl_(@Nonnull LDblIntPredicate.LIntDblPred predicate, double v, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LDblIntPredicate.throwIfNot(v, get(), predicate, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull SELF mustXDbl_(@Nonnull LDblIntPredicate.LIntDblPred predicate, double v, @Nonnull ExMF<RuntimeException> ex, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LDblIntPredicate.throwIfNot(v, get(), predicate, ex, message, messageParams);
		return self();
	}

	default @Nonnull SELF mustDbl_(double v, @Nonnull LDblIntPredicate.LIntDblPred predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LDblIntPredicate.throwIfNot(v, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull SELF mustDbl_$(double v, @Nonnull LDblIntPredicate.LIntDblPred predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LDblIntPredicate.throwIfNot(v, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull SELF mustDbl_$$(double v, @Nonnull LDblIntPredicate.LIntDblPred predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LDblIntPredicate.throwIfNot(v, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S_S_1, checkTraitType(), checkTraitName(), message, v, get());
		return self();
	}

	default @Nonnull SELF mustDbl_(double v, @Nonnull LDblIntPredicate.LIntDblPred predicate, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LDblIntPredicate.throwIfNot(v, get(), predicate, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull SELF mustXDbl_(double v, @Nonnull LDblIntPredicate.LIntDblPred predicate, @Nonnull ExMF<RuntimeException> ex, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LDblIntPredicate.throwIfNot(v, get(), predicate, ex, message, messageParams);
		return self();
	}

	default @Nonnull SELF mustNotDbl_(@Nonnull LDblIntPredicate.LIntDblPred predicate, double v, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LDblIntPredicate.throwIf(v, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull SELF mustNotDbl_$(@Nonnull LDblIntPredicate.LIntDblPred predicate, double v, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LDblIntPredicate.throwIf(v, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull SELF mustNotDbl_$$(@Nonnull LDblIntPredicate.LIntDblPred predicate, double v, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LDblIntPredicate.throwIf(v, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S_S_1, checkTraitType(), checkTraitName(), message, v, get());
		return self();
	}

	default @Nonnull SELF mustNotDbl_(@Nonnull LDblIntPredicate.LIntDblPred predicate, double v, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LDblIntPredicate.throwIf(v, get(), predicate, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull SELF mustXNotDbl_(@Nonnull LDblIntPredicate.LIntDblPred predicate, double v, @Nonnull ExMF<RuntimeException> ex, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LDblIntPredicate.throwIf(v, get(), predicate, ex, message, messageParams);
		return self();
	}

	default @Nonnull SELF mustNotDbl_(double v, @Nonnull LDblIntPredicate.LIntDblPred predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LDblIntPredicate.throwIf(v, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull SELF mustNotDbl_$(double v, @Nonnull LDblIntPredicate.LIntDblPred predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LDblIntPredicate.throwIf(v, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull SELF mustNotDbl_$$(double v, @Nonnull LDblIntPredicate.LIntDblPred predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LDblIntPredicate.throwIf(v, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S_S_1, checkTraitType(), checkTraitName(), message, v, get());
		return self();
	}

	default @Nonnull SELF mustNotDbl_(double v, @Nonnull LDblIntPredicate.LIntDblPred predicate, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LDblIntPredicate.throwIf(v, get(), predicate, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull SELF mustXNotDbl_(double v, @Nonnull LDblIntPredicate.LIntDblPred predicate, @Nonnull ExMF<RuntimeException> ex, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LDblIntPredicate.throwIf(v, get(), predicate, ex, message, messageParams);
		return self();
	}

	default @Nonnull SELF mustChar_(@Nonnull LCharIntPredicate.LIntCharPred predicate, char v, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LCharIntPredicate.throwIfNot(v, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull SELF mustChar_$(@Nonnull LCharIntPredicate.LIntCharPred predicate, char v, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LCharIntPredicate.throwIfNot(v, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull SELF mustChar_$$(@Nonnull LCharIntPredicate.LIntCharPred predicate, char v, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LCharIntPredicate.throwIfNot(v, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S_S_1, checkTraitType(), checkTraitName(), message, v, get());
		return self();
	}

	default @Nonnull SELF mustChar_(@Nonnull LCharIntPredicate.LIntCharPred predicate, char v, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LCharIntPredicate.throwIfNot(v, get(), predicate, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull SELF mustXChar_(@Nonnull LCharIntPredicate.LIntCharPred predicate, char v, @Nonnull ExMF<RuntimeException> ex, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LCharIntPredicate.throwIfNot(v, get(), predicate, ex, message, messageParams);
		return self();
	}

	default @Nonnull SELF mustChar_(char v, @Nonnull LCharIntPredicate.LIntCharPred predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LCharIntPredicate.throwIfNot(v, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull SELF mustChar_$(char v, @Nonnull LCharIntPredicate.LIntCharPred predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LCharIntPredicate.throwIfNot(v, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull SELF mustChar_$$(char v, @Nonnull LCharIntPredicate.LIntCharPred predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LCharIntPredicate.throwIfNot(v, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S_S_1, checkTraitType(), checkTraitName(), message, v, get());
		return self();
	}

	default @Nonnull SELF mustChar_(char v, @Nonnull LCharIntPredicate.LIntCharPred predicate, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LCharIntPredicate.throwIfNot(v, get(), predicate, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull SELF mustXChar_(char v, @Nonnull LCharIntPredicate.LIntCharPred predicate, @Nonnull ExMF<RuntimeException> ex, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LCharIntPredicate.throwIfNot(v, get(), predicate, ex, message, messageParams);
		return self();
	}

	default @Nonnull SELF mustNotChar_(@Nonnull LCharIntPredicate.LIntCharPred predicate, char v, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LCharIntPredicate.throwIf(v, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull SELF mustNotChar_$(@Nonnull LCharIntPredicate.LIntCharPred predicate, char v, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LCharIntPredicate.throwIf(v, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull SELF mustNotChar_$$(@Nonnull LCharIntPredicate.LIntCharPred predicate, char v, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LCharIntPredicate.throwIf(v, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S_S_1, checkTraitType(), checkTraitName(), message, v, get());
		return self();
	}

	default @Nonnull SELF mustNotChar_(@Nonnull LCharIntPredicate.LIntCharPred predicate, char v, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LCharIntPredicate.throwIf(v, get(), predicate, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull SELF mustXNotChar_(@Nonnull LCharIntPredicate.LIntCharPred predicate, char v, @Nonnull ExMF<RuntimeException> ex, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LCharIntPredicate.throwIf(v, get(), predicate, ex, message, messageParams);
		return self();
	}

	default @Nonnull SELF mustNotChar_(char v, @Nonnull LCharIntPredicate.LIntCharPred predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LCharIntPredicate.throwIf(v, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull SELF mustNotChar_$(char v, @Nonnull LCharIntPredicate.LIntCharPred predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LCharIntPredicate.throwIf(v, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull SELF mustNotChar_$$(char v, @Nonnull LCharIntPredicate.LIntCharPred predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LCharIntPredicate.throwIf(v, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S_S_1, checkTraitType(), checkTraitName(), message, v, get());
		return self();
	}

	default @Nonnull SELF mustNotChar_(char v, @Nonnull LCharIntPredicate.LIntCharPred predicate, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LCharIntPredicate.throwIf(v, get(), predicate, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull SELF mustXNotChar_(char v, @Nonnull LCharIntPredicate.LIntCharPred predicate, @Nonnull ExMF<RuntimeException> ex, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LCharIntPredicate.throwIf(v, get(), predicate, ex, message, messageParams);
		return self();
	}

	default @Nonnull SELF mustSrt_(@Nonnull LSrtIntPredicate.LIntSrtPred predicate, short v, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LSrtIntPredicate.throwIfNot(v, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull SELF mustSrt_$(@Nonnull LSrtIntPredicate.LIntSrtPred predicate, short v, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LSrtIntPredicate.throwIfNot(v, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull SELF mustSrt_$$(@Nonnull LSrtIntPredicate.LIntSrtPred predicate, short v, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LSrtIntPredicate.throwIfNot(v, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S_S_1, checkTraitType(), checkTraitName(), message, v, get());
		return self();
	}

	default @Nonnull SELF mustSrt_(@Nonnull LSrtIntPredicate.LIntSrtPred predicate, short v, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LSrtIntPredicate.throwIfNot(v, get(), predicate, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull SELF mustXSrt_(@Nonnull LSrtIntPredicate.LIntSrtPred predicate, short v, @Nonnull ExMF<RuntimeException> ex, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LSrtIntPredicate.throwIfNot(v, get(), predicate, ex, message, messageParams);
		return self();
	}

	default @Nonnull SELF mustSrt_(short v, @Nonnull LSrtIntPredicate.LIntSrtPred predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LSrtIntPredicate.throwIfNot(v, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull SELF mustSrt_$(short v, @Nonnull LSrtIntPredicate.LIntSrtPred predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LSrtIntPredicate.throwIfNot(v, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull SELF mustSrt_$$(short v, @Nonnull LSrtIntPredicate.LIntSrtPred predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LSrtIntPredicate.throwIfNot(v, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S_S_1, checkTraitType(), checkTraitName(), message, v, get());
		return self();
	}

	default @Nonnull SELF mustSrt_(short v, @Nonnull LSrtIntPredicate.LIntSrtPred predicate, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LSrtIntPredicate.throwIfNot(v, get(), predicate, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull SELF mustXSrt_(short v, @Nonnull LSrtIntPredicate.LIntSrtPred predicate, @Nonnull ExMF<RuntimeException> ex, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LSrtIntPredicate.throwIfNot(v, get(), predicate, ex, message, messageParams);
		return self();
	}

	default @Nonnull SELF mustNotSrt_(@Nonnull LSrtIntPredicate.LIntSrtPred predicate, short v, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LSrtIntPredicate.throwIf(v, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull SELF mustNotSrt_$(@Nonnull LSrtIntPredicate.LIntSrtPred predicate, short v, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LSrtIntPredicate.throwIf(v, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull SELF mustNotSrt_$$(@Nonnull LSrtIntPredicate.LIntSrtPred predicate, short v, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LSrtIntPredicate.throwIf(v, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S_S_1, checkTraitType(), checkTraitName(), message, v, get());
		return self();
	}

	default @Nonnull SELF mustNotSrt_(@Nonnull LSrtIntPredicate.LIntSrtPred predicate, short v, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LSrtIntPredicate.throwIf(v, get(), predicate, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull SELF mustXNotSrt_(@Nonnull LSrtIntPredicate.LIntSrtPred predicate, short v, @Nonnull ExMF<RuntimeException> ex, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LSrtIntPredicate.throwIf(v, get(), predicate, ex, message, messageParams);
		return self();
	}

	default @Nonnull SELF mustNotSrt_(short v, @Nonnull LSrtIntPredicate.LIntSrtPred predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LSrtIntPredicate.throwIf(v, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull SELF mustNotSrt_$(short v, @Nonnull LSrtIntPredicate.LIntSrtPred predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LSrtIntPredicate.throwIf(v, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull SELF mustNotSrt_$$(short v, @Nonnull LSrtIntPredicate.LIntSrtPred predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LSrtIntPredicate.throwIf(v, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S_S_1, checkTraitType(), checkTraitName(), message, v, get());
		return self();
	}

	default @Nonnull SELF mustNotSrt_(short v, @Nonnull LSrtIntPredicate.LIntSrtPred predicate, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LSrtIntPredicate.throwIf(v, get(), predicate, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull SELF mustXNotSrt_(short v, @Nonnull LSrtIntPredicate.LIntSrtPred predicate, @Nonnull ExMF<RuntimeException> ex, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LSrtIntPredicate.throwIf(v, get(), predicate, ex, message, messageParams);
		return self();
	}

	default @Nonnull SELF mustFlt_(@Nonnull LFltIntPredicate.LIntFltPred predicate, float v, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LFltIntPredicate.throwIfNot(v, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull SELF mustFlt_$(@Nonnull LFltIntPredicate.LIntFltPred predicate, float v, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LFltIntPredicate.throwIfNot(v, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull SELF mustFlt_$$(@Nonnull LFltIntPredicate.LIntFltPred predicate, float v, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LFltIntPredicate.throwIfNot(v, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S_S_1, checkTraitType(), checkTraitName(), message, v, get());
		return self();
	}

	default @Nonnull SELF mustFlt_(@Nonnull LFltIntPredicate.LIntFltPred predicate, float v, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LFltIntPredicate.throwIfNot(v, get(), predicate, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull SELF mustXFlt_(@Nonnull LFltIntPredicate.LIntFltPred predicate, float v, @Nonnull ExMF<RuntimeException> ex, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LFltIntPredicate.throwIfNot(v, get(), predicate, ex, message, messageParams);
		return self();
	}

	default @Nonnull SELF mustFlt_(float v, @Nonnull LFltIntPredicate.LIntFltPred predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LFltIntPredicate.throwIfNot(v, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull SELF mustFlt_$(float v, @Nonnull LFltIntPredicate.LIntFltPred predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LFltIntPredicate.throwIfNot(v, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull SELF mustFlt_$$(float v, @Nonnull LFltIntPredicate.LIntFltPred predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LFltIntPredicate.throwIfNot(v, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S_S_1, checkTraitType(), checkTraitName(), message, v, get());
		return self();
	}

	default @Nonnull SELF mustFlt_(float v, @Nonnull LFltIntPredicate.LIntFltPred predicate, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LFltIntPredicate.throwIfNot(v, get(), predicate, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull SELF mustXFlt_(float v, @Nonnull LFltIntPredicate.LIntFltPred predicate, @Nonnull ExMF<RuntimeException> ex, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LFltIntPredicate.throwIfNot(v, get(), predicate, ex, message, messageParams);
		return self();
	}

	default @Nonnull SELF mustNotFlt_(@Nonnull LFltIntPredicate.LIntFltPred predicate, float v, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LFltIntPredicate.throwIf(v, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull SELF mustNotFlt_$(@Nonnull LFltIntPredicate.LIntFltPred predicate, float v, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LFltIntPredicate.throwIf(v, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull SELF mustNotFlt_$$(@Nonnull LFltIntPredicate.LIntFltPred predicate, float v, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LFltIntPredicate.throwIf(v, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S_S_1, checkTraitType(), checkTraitName(), message, v, get());
		return self();
	}

	default @Nonnull SELF mustNotFlt_(@Nonnull LFltIntPredicate.LIntFltPred predicate, float v, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LFltIntPredicate.throwIf(v, get(), predicate, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull SELF mustXNotFlt_(@Nonnull LFltIntPredicate.LIntFltPred predicate, float v, @Nonnull ExMF<RuntimeException> ex, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LFltIntPredicate.throwIf(v, get(), predicate, ex, message, messageParams);
		return self();
	}

	default @Nonnull SELF mustNotFlt_(float v, @Nonnull LFltIntPredicate.LIntFltPred predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LFltIntPredicate.throwIf(v, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull SELF mustNotFlt_$(float v, @Nonnull LFltIntPredicate.LIntFltPred predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LFltIntPredicate.throwIf(v, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull SELF mustNotFlt_$$(float v, @Nonnull LFltIntPredicate.LIntFltPred predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LFltIntPredicate.throwIf(v, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S_S_1, checkTraitType(), checkTraitName(), message, v, get());
		return self();
	}

	default @Nonnull SELF mustNotFlt_(float v, @Nonnull LFltIntPredicate.LIntFltPred predicate, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LFltIntPredicate.throwIf(v, get(), predicate, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull SELF mustXNotFlt_(float v, @Nonnull LFltIntPredicate.LIntFltPred predicate, @Nonnull ExMF<RuntimeException> ex, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LFltIntPredicate.throwIf(v, get(), predicate, ex, message, messageParams);
		return self();
	}

	default @Nonnull SELF mustLong_(@Nonnull LLongIntPredicate.LIntLongPred predicate, long v, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LLongIntPredicate.throwIfNot(v, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull SELF mustLong_$(@Nonnull LLongIntPredicate.LIntLongPred predicate, long v, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LLongIntPredicate.throwIfNot(v, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull SELF mustLong_$$(@Nonnull LLongIntPredicate.LIntLongPred predicate, long v, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LLongIntPredicate.throwIfNot(v, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S_S_1, checkTraitType(), checkTraitName(), message, v, get());
		return self();
	}

	default @Nonnull SELF mustLong_(@Nonnull LLongIntPredicate.LIntLongPred predicate, long v, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LLongIntPredicate.throwIfNot(v, get(), predicate, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull SELF mustXLong_(@Nonnull LLongIntPredicate.LIntLongPred predicate, long v, @Nonnull ExMF<RuntimeException> ex, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LLongIntPredicate.throwIfNot(v, get(), predicate, ex, message, messageParams);
		return self();
	}

	default @Nonnull SELF mustLong_(long v, @Nonnull LLongIntPredicate.LIntLongPred predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LLongIntPredicate.throwIfNot(v, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull SELF mustLong_$(long v, @Nonnull LLongIntPredicate.LIntLongPred predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LLongIntPredicate.throwIfNot(v, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull SELF mustLong_$$(long v, @Nonnull LLongIntPredicate.LIntLongPred predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LLongIntPredicate.throwIfNot(v, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S_S_1, checkTraitType(), checkTraitName(), message, v, get());
		return self();
	}

	default @Nonnull SELF mustLong_(long v, @Nonnull LLongIntPredicate.LIntLongPred predicate, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LLongIntPredicate.throwIfNot(v, get(), predicate, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull SELF mustXLong_(long v, @Nonnull LLongIntPredicate.LIntLongPred predicate, @Nonnull ExMF<RuntimeException> ex, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LLongIntPredicate.throwIfNot(v, get(), predicate, ex, message, messageParams);
		return self();
	}

	default @Nonnull SELF mustNotLong_(@Nonnull LLongIntPredicate.LIntLongPred predicate, long v, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LLongIntPredicate.throwIf(v, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull SELF mustNotLong_$(@Nonnull LLongIntPredicate.LIntLongPred predicate, long v, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LLongIntPredicate.throwIf(v, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull SELF mustNotLong_$$(@Nonnull LLongIntPredicate.LIntLongPred predicate, long v, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LLongIntPredicate.throwIf(v, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S_S_1, checkTraitType(), checkTraitName(), message, v, get());
		return self();
	}

	default @Nonnull SELF mustNotLong_(@Nonnull LLongIntPredicate.LIntLongPred predicate, long v, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LLongIntPredicate.throwIf(v, get(), predicate, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull SELF mustXNotLong_(@Nonnull LLongIntPredicate.LIntLongPred predicate, long v, @Nonnull ExMF<RuntimeException> ex, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LLongIntPredicate.throwIf(v, get(), predicate, ex, message, messageParams);
		return self();
	}

	default @Nonnull SELF mustNotLong_(long v, @Nonnull LLongIntPredicate.LIntLongPred predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LLongIntPredicate.throwIf(v, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull SELF mustNotLong_$(long v, @Nonnull LLongIntPredicate.LIntLongPred predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LLongIntPredicate.throwIf(v, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull SELF mustNotLong_$$(long v, @Nonnull LLongIntPredicate.LIntLongPred predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LLongIntPredicate.throwIf(v, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S_S_1, checkTraitType(), checkTraitName(), message, v, get());
		return self();
	}

	default @Nonnull SELF mustNotLong_(long v, @Nonnull LLongIntPredicate.LIntLongPred predicate, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LLongIntPredicate.throwIf(v, get(), predicate, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull SELF mustXNotLong_(long v, @Nonnull LLongIntPredicate.LIntLongPred predicate, @Nonnull ExMF<RuntimeException> ex, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LLongIntPredicate.throwIf(v, get(), predicate, ex, message, messageParams);
		return self();
	}

	default @Nonnull <V> SELF must_(@Nonnull LObjIntPredicate.LIntObjPred<? super V> predicate, V v, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LObjIntPredicate.throwIfNot(v, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull <V> SELF must_(@Nonnull LObjIntPredicate.LIntObjPred<? super V> predicate, V v, LOiFunction.LIntObjFunc<? super V, String> msgFunc) {
		Null.nonNullArg(predicate, "predicate");
		LObjIntPredicate.throwIfNot(v, get(), predicate, checkTraitFactory(), msgFunc);
		return self();
	}

	default @Nonnull <V> SELF must_$(LOiFunction.LIntObjFunc<? super V, String> specialPredicate, V v) {
		LObjIntPredicate.throwIfNot$(v, get(), specialPredicate, checkTraitFactory());
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

	default @Nonnull <V> SELF must_(V v, @Nonnull LObjIntPredicate.LIntObjPred<? super V> predicate, LOiFunction.LIntObjFunc<? super V, String> msgFunc) {
		Null.nonNullArg(predicate, "predicate");
		LObjIntPredicate.throwIfNot(v, get(), predicate, checkTraitFactory(), msgFunc);
		return self();
	}

	default @Nonnull <V> SELF must_$(V v, LOiFunction.LIntObjFunc<? super V, String> specialPredicate) {
		LObjIntPredicate.throwIfNot$(v, get(), specialPredicate, checkTraitFactory());
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

	default @Nonnull <V> SELF mustNot_(@Nonnull LObjIntPredicate.LIntObjPred<? super V> predicate, V v, LOiFunction.LIntObjFunc<? super V, String> msgFunc) {
		Null.nonNullArg(predicate, "predicate");
		LObjIntPredicate.throwIf(v, get(), predicate, checkTraitFactory(), msgFunc);
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

	default @Nonnull <V> SELF mustNot_(V v, @Nonnull LObjIntPredicate.LIntObjPred<? super V> predicate, LOiFunction.LIntObjFunc<? super V, String> msgFunc) {
		Null.nonNullArg(predicate, "predicate");
		LObjIntPredicate.throwIf(v, get(), predicate, checkTraitFactory(), msgFunc);
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

	default @Nonnull <V1> SELF mustWithInt(@Nonnull LObjIntPredicate<? super V1> predicate, V1 with1, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LObjIntPredicate.throwIfNot(with1, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull <V1> SELF mustWithInt(@Nonnull LObjIntPredicate<? super V1> predicate, V1 with1, LOiFunction<? super V1, String> msgFunc) {
		Null.nonNullArg(predicate, "predicate");
		LObjIntPredicate.throwIfNot(with1, get(), predicate, checkTraitFactory(), msgFunc);
		return self();
	}

	default @Nonnull <V1> SELF mustWithInt$(LOiFunction<? super V1, String> specialPredicate, V1 with1) {
		LObjIntPredicate.throwIfNot$(with1, get(), specialPredicate, checkTraitFactory());
		return self();
	}

	default @Nonnull <V1> SELF mustWithInt$(@Nonnull LObjIntPredicate<? super V1> predicate, V1 with1, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LObjIntPredicate.throwIfNot(with1, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull <V1> SELF mustWithInt$$(@Nonnull LObjIntPredicate<? super V1> predicate, V1 with1, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LObjIntPredicate.throwIfNot(with1, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S_S_1, checkTraitType(), checkTraitName(), message, with1, get());
		return self();
	}

	default @Nonnull <V1> SELF mustWithInt(@Nonnull LObjIntPredicate<? super V1> predicate, V1 with1, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LObjIntPredicate.throwIfNot(with1, get(), predicate, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull <V1> SELF mustXWithInt(@Nonnull LObjIntPredicate<? super V1> predicate, V1 with1, @Nonnull ExMF<RuntimeException> ex, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LObjIntPredicate.throwIfNot(with1, get(), predicate, ex, message, messageParams);
		return self();
	}

	default @Nonnull <V1> SELF mustWithInt(V1 with1, @Nonnull LObjIntPredicate<? super V1> predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LObjIntPredicate.throwIfNot(with1, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull <V1> SELF mustWithInt(V1 with1, @Nonnull LObjIntPredicate<? super V1> predicate, LOiFunction<? super V1, String> msgFunc) {
		Null.nonNullArg(predicate, "predicate");
		LObjIntPredicate.throwIfNot(with1, get(), predicate, checkTraitFactory(), msgFunc);
		return self();
	}

	default @Nonnull <V1> SELF mustWithInt$(V1 with1, LOiFunction<? super V1, String> specialPredicate) {
		LObjIntPredicate.throwIfNot$(with1, get(), specialPredicate, checkTraitFactory());
		return self();
	}

	default @Nonnull <V1> SELF mustWithInt$(V1 with1, @Nonnull LObjIntPredicate<? super V1> predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LObjIntPredicate.throwIfNot(with1, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull <V1> SELF mustWithInt$$(V1 with1, @Nonnull LObjIntPredicate<? super V1> predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LObjIntPredicate.throwIfNot(with1, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S_S_1, checkTraitType(), checkTraitName(), message, with1, get());
		return self();
	}

	default @Nonnull <V1> SELF mustWithInt(V1 with1, @Nonnull LObjIntPredicate<? super V1> predicate, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LObjIntPredicate.throwIfNot(with1, get(), predicate, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull <V1> SELF mustXWithInt(V1 with1, @Nonnull LObjIntPredicate<? super V1> predicate, @Nonnull ExMF<RuntimeException> ex, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LObjIntPredicate.throwIfNot(with1, get(), predicate, ex, message, messageParams);
		return self();
	}

	default @Nonnull <V1> SELF mustNotWithInt(@Nonnull LObjIntPredicate<? super V1> predicate, V1 with1, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LObjIntPredicate.throwIf(with1, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull <V1> SELF mustNotWithInt(@Nonnull LObjIntPredicate<? super V1> predicate, V1 with1, LOiFunction<? super V1, String> msgFunc) {
		Null.nonNullArg(predicate, "predicate");
		LObjIntPredicate.throwIf(with1, get(), predicate, checkTraitFactory(), msgFunc);
		return self();
	}

	default @Nonnull <V1> SELF mustNotWithInt$(@Nonnull LObjIntPredicate<? super V1> predicate, V1 with1, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LObjIntPredicate.throwIf(with1, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull <V1> SELF mustNotWithInt$$(@Nonnull LObjIntPredicate<? super V1> predicate, V1 with1, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LObjIntPredicate.throwIf(with1, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S_S_1, checkTraitType(), checkTraitName(), message, with1, get());
		return self();
	}

	default @Nonnull <V1> SELF mustNotWithInt(@Nonnull LObjIntPredicate<? super V1> predicate, V1 with1, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LObjIntPredicate.throwIf(with1, get(), predicate, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull <V1> SELF mustXNotWithInt(@Nonnull LObjIntPredicate<? super V1> predicate, V1 with1, @Nonnull ExMF<RuntimeException> ex, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LObjIntPredicate.throwIf(with1, get(), predicate, ex, message, messageParams);
		return self();
	}

	default @Nonnull <V1> SELF mustNotWithInt(V1 with1, @Nonnull LObjIntPredicate<? super V1> predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LObjIntPredicate.throwIf(with1, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull <V1> SELF mustNotWithInt(V1 with1, @Nonnull LObjIntPredicate<? super V1> predicate, LOiFunction<? super V1, String> msgFunc) {
		Null.nonNullArg(predicate, "predicate");
		LObjIntPredicate.throwIf(with1, get(), predicate, checkTraitFactory(), msgFunc);
		return self();
	}

	default @Nonnull <V1> SELF mustNotWithInt$(V1 with1, @Nonnull LObjIntPredicate<? super V1> predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LObjIntPredicate.throwIf(with1, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull <V1> SELF mustNotWithInt$$(V1 with1, @Nonnull LObjIntPredicate<? super V1> predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LObjIntPredicate.throwIf(with1, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S_S_1, checkTraitType(), checkTraitName(), message, with1, get());
		return self();
	}

	default @Nonnull <V1> SELF mustNotWithInt(V1 with1, @Nonnull LObjIntPredicate<? super V1> predicate, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LObjIntPredicate.throwIf(with1, get(), predicate, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull <V1> SELF mustXNotWithInt(V1 with1, @Nonnull LObjIntPredicate<? super V1> predicate, @Nonnull ExMF<RuntimeException> ex, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LObjIntPredicate.throwIf(with1, get(), predicate, ex, message, messageParams);
		return self();
	}

	default @Nonnull <V1, V2> SELF mustWith(@Nonnull LBiObjIntPredicate<? super V1, ? super V2> predicate, V1 with1, V2 with2, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LBiObjIntPredicate.throwIfNot(with1, with2, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull <V1, V2> SELF mustWith(@Nonnull LBiObjIntPredicate<? super V1, ? super V2> predicate, V1 with1, V2 with2, LBiObjIntFunction<? super V1, ? super V2, String> msgFunc) {
		Null.nonNullArg(predicate, "predicate");
		LBiObjIntPredicate.throwIfNot(with1, with2, get(), predicate, checkTraitFactory(), msgFunc);
		return self();
	}

	default @Nonnull <V1, V2> SELF mustWith$(LBiObjIntFunction<? super V1, ? super V2, String> specialPredicate, V1 with1, V2 with2) {
		LBiObjIntPredicate.throwIfNot$(with1, with2, get(), specialPredicate, checkTraitFactory());
		return self();
	}

	default @Nonnull <V1, V2> SELF mustWith$(@Nonnull LBiObjIntPredicate<? super V1, ? super V2> predicate, V1 with1, V2 with2, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LBiObjIntPredicate.throwIfNot(with1, with2, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull <V1, V2> SELF mustWith$$(@Nonnull LBiObjIntPredicate<? super V1, ? super V2> predicate, V1 with1, V2 with2, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LBiObjIntPredicate.throwIfNot(with1, with2, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S_S_2, checkTraitType(), checkTraitName(), message, with1, with2, get());
		return self();
	}

	default @Nonnull <V1, V2> SELF mustWith(@Nonnull LBiObjIntPredicate<? super V1, ? super V2> predicate, V1 with1, V2 with2, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LBiObjIntPredicate.throwIfNot(with1, with2, get(), predicate, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull <V1, V2> SELF mustXWith(@Nonnull LBiObjIntPredicate<? super V1, ? super V2> predicate, V1 with1, V2 with2, @Nonnull ExMF<RuntimeException> ex, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LBiObjIntPredicate.throwIfNot(with1, with2, get(), predicate, ex, message, messageParams);
		return self();
	}

	default @Nonnull <V1, V2> SELF mustWith(V1 with1, V2 with2, @Nonnull LBiObjIntPredicate<? super V1, ? super V2> predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LBiObjIntPredicate.throwIfNot(with1, with2, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull <V1, V2> SELF mustWith(V1 with1, V2 with2, @Nonnull LBiObjIntPredicate<? super V1, ? super V2> predicate, LBiObjIntFunction<? super V1, ? super V2, String> msgFunc) {
		Null.nonNullArg(predicate, "predicate");
		LBiObjIntPredicate.throwIfNot(with1, with2, get(), predicate, checkTraitFactory(), msgFunc);
		return self();
	}

	default @Nonnull <V1, V2> SELF mustWith$(V1 with1, V2 with2, LBiObjIntFunction<? super V1, ? super V2, String> specialPredicate) {
		LBiObjIntPredicate.throwIfNot$(with1, with2, get(), specialPredicate, checkTraitFactory());
		return self();
	}

	default @Nonnull <V1, V2> SELF mustWith$(V1 with1, V2 with2, @Nonnull LBiObjIntPredicate<? super V1, ? super V2> predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LBiObjIntPredicate.throwIfNot(with1, with2, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull <V1, V2> SELF mustWith$$(V1 with1, V2 with2, @Nonnull LBiObjIntPredicate<? super V1, ? super V2> predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LBiObjIntPredicate.throwIfNot(with1, with2, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S_S_2, checkTraitType(), checkTraitName(), message, with1, with2, get());
		return self();
	}

	default @Nonnull <V1, V2> SELF mustWith(V1 with1, V2 with2, @Nonnull LBiObjIntPredicate<? super V1, ? super V2> predicate, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LBiObjIntPredicate.throwIfNot(with1, with2, get(), predicate, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull <V1, V2> SELF mustXWith(V1 with1, V2 with2, @Nonnull LBiObjIntPredicate<? super V1, ? super V2> predicate, @Nonnull ExMF<RuntimeException> ex, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LBiObjIntPredicate.throwIfNot(with1, with2, get(), predicate, ex, message, messageParams);
		return self();
	}

	default @Nonnull <V1, V2> SELF mustNotWith(@Nonnull LBiObjIntPredicate<? super V1, ? super V2> predicate, V1 with1, V2 with2, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LBiObjIntPredicate.throwIf(with1, with2, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull <V1, V2> SELF mustNotWith(@Nonnull LBiObjIntPredicate<? super V1, ? super V2> predicate, V1 with1, V2 with2, LBiObjIntFunction<? super V1, ? super V2, String> msgFunc) {
		Null.nonNullArg(predicate, "predicate");
		LBiObjIntPredicate.throwIf(with1, with2, get(), predicate, checkTraitFactory(), msgFunc);
		return self();
	}

	default @Nonnull <V1, V2> SELF mustNotWith$(@Nonnull LBiObjIntPredicate<? super V1, ? super V2> predicate, V1 with1, V2 with2, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LBiObjIntPredicate.throwIf(with1, with2, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull <V1, V2> SELF mustNotWith$$(@Nonnull LBiObjIntPredicate<? super V1, ? super V2> predicate, V1 with1, V2 with2, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LBiObjIntPredicate.throwIf(with1, with2, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S_S_2, checkTraitType(), checkTraitName(), message, with1, with2, get());
		return self();
	}

	default @Nonnull <V1, V2> SELF mustNotWith(@Nonnull LBiObjIntPredicate<? super V1, ? super V2> predicate, V1 with1, V2 with2, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LBiObjIntPredicate.throwIf(with1, with2, get(), predicate, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull <V1, V2> SELF mustXNotWith(@Nonnull LBiObjIntPredicate<? super V1, ? super V2> predicate, V1 with1, V2 with2, @Nonnull ExMF<RuntimeException> ex, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LBiObjIntPredicate.throwIf(with1, with2, get(), predicate, ex, message, messageParams);
		return self();
	}

	default @Nonnull <V1, V2> SELF mustNotWith(V1 with1, V2 with2, @Nonnull LBiObjIntPredicate<? super V1, ? super V2> predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LBiObjIntPredicate.throwIf(with1, with2, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull <V1, V2> SELF mustNotWith(V1 with1, V2 with2, @Nonnull LBiObjIntPredicate<? super V1, ? super V2> predicate, LBiObjIntFunction<? super V1, ? super V2, String> msgFunc) {
		Null.nonNullArg(predicate, "predicate");
		LBiObjIntPredicate.throwIf(with1, with2, get(), predicate, checkTraitFactory(), msgFunc);
		return self();
	}

	default @Nonnull <V1, V2> SELF mustNotWith$(V1 with1, V2 with2, @Nonnull LBiObjIntPredicate<? super V1, ? super V2> predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LBiObjIntPredicate.throwIf(with1, with2, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull <V1, V2> SELF mustNotWith$$(V1 with1, V2 with2, @Nonnull LBiObjIntPredicate<? super V1, ? super V2> predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LBiObjIntPredicate.throwIf(with1, with2, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S_S_2, checkTraitType(), checkTraitName(), message, with1, with2, get());
		return self();
	}

	default @Nonnull <V1, V2> SELF mustNotWith(V1 with1, V2 with2, @Nonnull LBiObjIntPredicate<? super V1, ? super V2> predicate, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LBiObjIntPredicate.throwIf(with1, with2, get(), predicate, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull <V1, V2> SELF mustXNotWith(V1 with1, V2 with2, @Nonnull LBiObjIntPredicate<? super V1, ? super V2> predicate, @Nonnull ExMF<RuntimeException> ex, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LBiObjIntPredicate.throwIf(with1, with2, get(), predicate, ex, message, messageParams);
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
