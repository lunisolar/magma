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

public interface CheckCharTrait<SELF extends CheckCharTrait<SELF>> extends FluentTrait<SELF>, aValue<aChar>, LCharSingle, CharValueTrait<SELF> {

	char get();

	default char value() {
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

	default @Nonnull SELF must(@Nonnull LCharPredicate predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LCharPredicate.throwIfNot(get(), predicate, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull SELF must$(@Nonnull LCharPredicate predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LCharPredicate.throwIfNot(get(), predicate, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull SELF must(@Nonnull LCharPredicate predicate, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LCharPredicate.throwIfNot(get(), predicate, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull SELF mustX(@Nonnull LCharPredicate predicate, @Nonnull ExMF<RuntimeException> ex, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LCharPredicate.throwIfNot(get(), predicate, ex, message, messageParams);
		return self();
	}

	default @Nonnull SELF mustNot(@Nonnull LCharPredicate predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LCharPredicate.throwIf(get(), predicate, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull SELF mustNot$(@Nonnull LCharPredicate predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LCharPredicate.throwIf(get(), predicate, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull SELF mustNot(@Nonnull LCharPredicate predicate, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LCharPredicate.throwIf(get(), predicate, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull SELF mustXNot(@Nonnull LCharPredicate predicate, @Nonnull ExMF<RuntimeException> ex, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LCharPredicate.throwIf(get(), predicate, ex, message, messageParams);
		return self();
	}

	default @Nonnull SELF must(@Nonnull LBiCharPredicate predicate, char a2, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LBiCharPredicate.throwIfNot(get(), a2, predicate, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull SELF must$(@Nonnull LBiCharPredicate predicate, char a2, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LBiCharPredicate.throwIfNot(get(), a2, predicate, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull SELF must$$(@Nonnull LBiCharPredicate predicate, char a2, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LBiCharPredicate.throwIfNot(get(), a2, predicate, checkTraitFactory(), MESSAGE_S_S_S_S_1, checkTraitType(), checkTraitName(), message, a2, get());
		return self();
	}

	default @Nonnull SELF must(@Nonnull LBiCharPredicate predicate, char a2, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LBiCharPredicate.throwIfNot(get(), a2, predicate, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull SELF mustX(@Nonnull LBiCharPredicate predicate, char a2, @Nonnull ExMF<RuntimeException> ex, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LBiCharPredicate.throwIfNot(get(), a2, predicate, ex, message, messageParams);
		return self();
	}

	default @Nonnull SELF must(char a2, @Nonnull LBiCharPredicate predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LBiCharPredicate.throwIfNot(get(), a2, predicate, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull SELF must$(char a2, @Nonnull LBiCharPredicate predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LBiCharPredicate.throwIfNot(get(), a2, predicate, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull SELF must$$(char a2, @Nonnull LBiCharPredicate predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LBiCharPredicate.throwIfNot(get(), a2, predicate, checkTraitFactory(), MESSAGE_S_S_S_S_1, checkTraitType(), checkTraitName(), message, a2, get());
		return self();
	}

	default @Nonnull SELF must(char a2, @Nonnull LBiCharPredicate predicate, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LBiCharPredicate.throwIfNot(get(), a2, predicate, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull SELF mustX(char a2, @Nonnull LBiCharPredicate predicate, @Nonnull ExMF<RuntimeException> ex, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LBiCharPredicate.throwIfNot(get(), a2, predicate, ex, message, messageParams);
		return self();
	}

	default @Nonnull SELF mustNot(@Nonnull LBiCharPredicate predicate, char a2, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LBiCharPredicate.throwIf(get(), a2, predicate, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull SELF mustNot$(@Nonnull LBiCharPredicate predicate, char a2, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LBiCharPredicate.throwIf(get(), a2, predicate, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull SELF mustNot$$(@Nonnull LBiCharPredicate predicate, char a2, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LBiCharPredicate.throwIf(get(), a2, predicate, checkTraitFactory(), MESSAGE_S_S_S_S_1, checkTraitType(), checkTraitName(), message, a2, get());
		return self();
	}

	default @Nonnull SELF mustNot(@Nonnull LBiCharPredicate predicate, char a2, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LBiCharPredicate.throwIf(get(), a2, predicate, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull SELF mustXNot(@Nonnull LBiCharPredicate predicate, char a2, @Nonnull ExMF<RuntimeException> ex, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LBiCharPredicate.throwIf(get(), a2, predicate, ex, message, messageParams);
		return self();
	}

	default @Nonnull SELF mustNot(char a2, @Nonnull LBiCharPredicate predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LBiCharPredicate.throwIf(get(), a2, predicate, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull SELF mustNot$(char a2, @Nonnull LBiCharPredicate predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LBiCharPredicate.throwIf(get(), a2, predicate, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull SELF mustNot$$(char a2, @Nonnull LBiCharPredicate predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LBiCharPredicate.throwIf(get(), a2, predicate, checkTraitFactory(), MESSAGE_S_S_S_S_1, checkTraitType(), checkTraitName(), message, a2, get());
		return self();
	}

	default @Nonnull SELF mustNot(char a2, @Nonnull LBiCharPredicate predicate, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LBiCharPredicate.throwIf(get(), a2, predicate, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull SELF mustXNot(char a2, @Nonnull LBiCharPredicate predicate, @Nonnull ExMF<RuntimeException> ex, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LBiCharPredicate.throwIf(get(), a2, predicate, ex, message, messageParams);
		return self();
	}

	default @Nonnull SELF must(@Nonnull LTriCharPredicate predicate, char a2, char a3, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LTriCharPredicate.throwIfNot(get(), a2, a3, predicate, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull SELF must$(@Nonnull LTriCharPredicate predicate, char a2, char a3, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LTriCharPredicate.throwIfNot(get(), a2, a3, predicate, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull SELF must$$(@Nonnull LTriCharPredicate predicate, char a2, char a3, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LTriCharPredicate.throwIfNot(get(), a2, a3, predicate, checkTraitFactory(), MESSAGE_S_S_S_S_2, checkTraitType(), checkTraitName(), message, a2, a3, get());
		return self();
	}

	default @Nonnull SELF must(@Nonnull LTriCharPredicate predicate, char a2, char a3, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LTriCharPredicate.throwIfNot(get(), a2, a3, predicate, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull SELF mustX(@Nonnull LTriCharPredicate predicate, char a2, char a3, @Nonnull ExMF<RuntimeException> ex, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LTriCharPredicate.throwIfNot(get(), a2, a3, predicate, ex, message, messageParams);
		return self();
	}

	default @Nonnull SELF must(char a2, char a3, @Nonnull LTriCharPredicate predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LTriCharPredicate.throwIfNot(get(), a2, a3, predicate, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull SELF must$(char a2, char a3, @Nonnull LTriCharPredicate predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LTriCharPredicate.throwIfNot(get(), a2, a3, predicate, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull SELF must$$(char a2, char a3, @Nonnull LTriCharPredicate predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LTriCharPredicate.throwIfNot(get(), a2, a3, predicate, checkTraitFactory(), MESSAGE_S_S_S_S_2, checkTraitType(), checkTraitName(), message, a2, a3, get());
		return self();
	}

	default @Nonnull SELF must(char a2, char a3, @Nonnull LTriCharPredicate predicate, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LTriCharPredicate.throwIfNot(get(), a2, a3, predicate, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull SELF mustX(char a2, char a3, @Nonnull LTriCharPredicate predicate, @Nonnull ExMF<RuntimeException> ex, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LTriCharPredicate.throwIfNot(get(), a2, a3, predicate, ex, message, messageParams);
		return self();
	}

	default @Nonnull SELF mustNot(@Nonnull LTriCharPredicate predicate, char a2, char a3, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LTriCharPredicate.throwIf(get(), a2, a3, predicate, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull SELF mustNot$(@Nonnull LTriCharPredicate predicate, char a2, char a3, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LTriCharPredicate.throwIf(get(), a2, a3, predicate, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull SELF mustNot$$(@Nonnull LTriCharPredicate predicate, char a2, char a3, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LTriCharPredicate.throwIf(get(), a2, a3, predicate, checkTraitFactory(), MESSAGE_S_S_S_S_2, checkTraitType(), checkTraitName(), message, a2, a3, get());
		return self();
	}

	default @Nonnull SELF mustNot(@Nonnull LTriCharPredicate predicate, char a2, char a3, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LTriCharPredicate.throwIf(get(), a2, a3, predicate, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull SELF mustXNot(@Nonnull LTriCharPredicate predicate, char a2, char a3, @Nonnull ExMF<RuntimeException> ex, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LTriCharPredicate.throwIf(get(), a2, a3, predicate, ex, message, messageParams);
		return self();
	}

	default @Nonnull SELF mustNot(char a2, char a3, @Nonnull LTriCharPredicate predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LTriCharPredicate.throwIf(get(), a2, a3, predicate, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull SELF mustNot$(char a2, char a3, @Nonnull LTriCharPredicate predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LTriCharPredicate.throwIf(get(), a2, a3, predicate, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull SELF mustNot$$(char a2, char a3, @Nonnull LTriCharPredicate predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LTriCharPredicate.throwIf(get(), a2, a3, predicate, checkTraitFactory(), MESSAGE_S_S_S_S_2, checkTraitType(), checkTraitName(), message, a2, a3, get());
		return self();
	}

	default @Nonnull SELF mustNot(char a2, char a3, @Nonnull LTriCharPredicate predicate, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LTriCharPredicate.throwIf(get(), a2, a3, predicate, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull SELF mustXNot(char a2, char a3, @Nonnull LTriCharPredicate predicate, @Nonnull ExMF<RuntimeException> ex, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LTriCharPredicate.throwIf(get(), a2, a3, predicate, ex, message, messageParams);
		return self();
	}

	default @Nonnull SELF mustInt(@Nonnull LCharIntPredicate predicate, int v, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LCharIntPredicate.throwIfNot(get(), v, predicate, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull SELF mustInt$(@Nonnull LCharIntPredicate predicate, int v, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LCharIntPredicate.throwIfNot(get(), v, predicate, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull SELF mustInt$$(@Nonnull LCharIntPredicate predicate, int v, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LCharIntPredicate.throwIfNot(get(), v, predicate, checkTraitFactory(), MESSAGE_S_S_S_S_1, checkTraitType(), checkTraitName(), message, v, get());
		return self();
	}

	default @Nonnull SELF mustInt(@Nonnull LCharIntPredicate predicate, int v, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LCharIntPredicate.throwIfNot(get(), v, predicate, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull SELF mustXInt(@Nonnull LCharIntPredicate predicate, int v, @Nonnull ExMF<RuntimeException> ex, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LCharIntPredicate.throwIfNot(get(), v, predicate, ex, message, messageParams);
		return self();
	}

	default @Nonnull SELF mustInt(int v, @Nonnull LCharIntPredicate predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LCharIntPredicate.throwIfNot(get(), v, predicate, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull SELF mustInt$(int v, @Nonnull LCharIntPredicate predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LCharIntPredicate.throwIfNot(get(), v, predicate, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull SELF mustInt$$(int v, @Nonnull LCharIntPredicate predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LCharIntPredicate.throwIfNot(get(), v, predicate, checkTraitFactory(), MESSAGE_S_S_S_S_1, checkTraitType(), checkTraitName(), message, v, get());
		return self();
	}

	default @Nonnull SELF mustInt(int v, @Nonnull LCharIntPredicate predicate, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LCharIntPredicate.throwIfNot(get(), v, predicate, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull SELF mustXInt(int v, @Nonnull LCharIntPredicate predicate, @Nonnull ExMF<RuntimeException> ex, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LCharIntPredicate.throwIfNot(get(), v, predicate, ex, message, messageParams);
		return self();
	}

	default @Nonnull SELF mustNotInt(@Nonnull LCharIntPredicate predicate, int v, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LCharIntPredicate.throwIf(get(), v, predicate, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull SELF mustNotInt$(@Nonnull LCharIntPredicate predicate, int v, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LCharIntPredicate.throwIf(get(), v, predicate, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull SELF mustNotInt$$(@Nonnull LCharIntPredicate predicate, int v, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LCharIntPredicate.throwIf(get(), v, predicate, checkTraitFactory(), MESSAGE_S_S_S_S_1, checkTraitType(), checkTraitName(), message, v, get());
		return self();
	}

	default @Nonnull SELF mustNotInt(@Nonnull LCharIntPredicate predicate, int v, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LCharIntPredicate.throwIf(get(), v, predicate, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull SELF mustXNotInt(@Nonnull LCharIntPredicate predicate, int v, @Nonnull ExMF<RuntimeException> ex, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LCharIntPredicate.throwIf(get(), v, predicate, ex, message, messageParams);
		return self();
	}

	default @Nonnull SELF mustNotInt(int v, @Nonnull LCharIntPredicate predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LCharIntPredicate.throwIf(get(), v, predicate, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull SELF mustNotInt$(int v, @Nonnull LCharIntPredicate predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LCharIntPredicate.throwIf(get(), v, predicate, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull SELF mustNotInt$$(int v, @Nonnull LCharIntPredicate predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LCharIntPredicate.throwIf(get(), v, predicate, checkTraitFactory(), MESSAGE_S_S_S_S_1, checkTraitType(), checkTraitName(), message, v, get());
		return self();
	}

	default @Nonnull SELF mustNotInt(int v, @Nonnull LCharIntPredicate predicate, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LCharIntPredicate.throwIf(get(), v, predicate, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull SELF mustXNotInt(int v, @Nonnull LCharIntPredicate predicate, @Nonnull ExMF<RuntimeException> ex, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LCharIntPredicate.throwIf(get(), v, predicate, ex, message, messageParams);
		return self();
	}

	default @Nonnull <V> SELF must_(@Nonnull LObjCharPredicate.LCharObjPred<? super V> predicate, V v, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LObjCharPredicate.throwIfNot(v, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull <V> SELF must_$(@Nonnull LObjCharPredicate.LCharObjPred<? super V> predicate, V v, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LObjCharPredicate.throwIfNot(v, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull <V> SELF must_$$(@Nonnull LObjCharPredicate.LCharObjPred<? super V> predicate, V v, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LObjCharPredicate.throwIfNot(v, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S_S_1, checkTraitType(), checkTraitName(), message, v, get());
		return self();
	}

	default @Nonnull <V> SELF must_(@Nonnull LObjCharPredicate.LCharObjPred<? super V> predicate, V v, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LObjCharPredicate.throwIfNot(v, get(), predicate, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull <V> SELF mustX_(@Nonnull LObjCharPredicate.LCharObjPred<? super V> predicate, V v, @Nonnull ExMF<RuntimeException> ex, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LObjCharPredicate.throwIfNot(v, get(), predicate, ex, message, messageParams);
		return self();
	}

	default @Nonnull <V> SELF must_(V v, @Nonnull LObjCharPredicate.LCharObjPred<? super V> predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LObjCharPredicate.throwIfNot(v, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull <V> SELF must_$(V v, @Nonnull LObjCharPredicate.LCharObjPred<? super V> predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LObjCharPredicate.throwIfNot(v, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull <V> SELF must_$$(V v, @Nonnull LObjCharPredicate.LCharObjPred<? super V> predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LObjCharPredicate.throwIfNot(v, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S_S_1, checkTraitType(), checkTraitName(), message, v, get());
		return self();
	}

	default @Nonnull <V> SELF must_(V v, @Nonnull LObjCharPredicate.LCharObjPred<? super V> predicate, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LObjCharPredicate.throwIfNot(v, get(), predicate, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull <V> SELF mustX_(V v, @Nonnull LObjCharPredicate.LCharObjPred<? super V> predicate, @Nonnull ExMF<RuntimeException> ex, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LObjCharPredicate.throwIfNot(v, get(), predicate, ex, message, messageParams);
		return self();
	}

	default @Nonnull <V> SELF mustNot_(@Nonnull LObjCharPredicate.LCharObjPred<? super V> predicate, V v, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LObjCharPredicate.throwIf(v, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull <V> SELF mustNot_$(@Nonnull LObjCharPredicate.LCharObjPred<? super V> predicate, V v, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LObjCharPredicate.throwIf(v, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull <V> SELF mustNot_$$(@Nonnull LObjCharPredicate.LCharObjPred<? super V> predicate, V v, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LObjCharPredicate.throwIf(v, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S_S_1, checkTraitType(), checkTraitName(), message, v, get());
		return self();
	}

	default @Nonnull <V> SELF mustNot_(@Nonnull LObjCharPredicate.LCharObjPred<? super V> predicate, V v, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LObjCharPredicate.throwIf(v, get(), predicate, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull <V> SELF mustXNot_(@Nonnull LObjCharPredicate.LCharObjPred<? super V> predicate, V v, @Nonnull ExMF<RuntimeException> ex, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LObjCharPredicate.throwIf(v, get(), predicate, ex, message, messageParams);
		return self();
	}

	default @Nonnull <V> SELF mustNot_(V v, @Nonnull LObjCharPredicate.LCharObjPred<? super V> predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LObjCharPredicate.throwIf(v, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull <V> SELF mustNot_$(V v, @Nonnull LObjCharPredicate.LCharObjPred<? super V> predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LObjCharPredicate.throwIf(v, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull <V> SELF mustNot_$$(V v, @Nonnull LObjCharPredicate.LCharObjPred<? super V> predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LObjCharPredicate.throwIf(v, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S_S_1, checkTraitType(), checkTraitName(), message, v, get());
		return self();
	}

	default @Nonnull <V> SELF mustNot_(V v, @Nonnull LObjCharPredicate.LCharObjPred<? super V> predicate, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LObjCharPredicate.throwIf(v, get(), predicate, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull <V> SELF mustXNot_(V v, @Nonnull LObjCharPredicate.LCharObjPred<? super V> predicate, @Nonnull ExMF<RuntimeException> ex, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LObjCharPredicate.throwIf(v, get(), predicate, ex, message, messageParams);
		return self();
	}

	default @Nonnull <V1> SELF mustWithChar(@Nonnull LObjCharPredicate<? super V1> predicate, V1 with1, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LObjCharPredicate.throwIfNot(with1, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull <V1> SELF mustWithChar$(@Nonnull LObjCharPredicate<? super V1> predicate, V1 with1, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LObjCharPredicate.throwIfNot(with1, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull <V1> SELF mustWithChar$$(@Nonnull LObjCharPredicate<? super V1> predicate, V1 with1, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LObjCharPredicate.throwIfNot(with1, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S_S_1, checkTraitType(), checkTraitName(), message, with1, get());
		return self();
	}

	default @Nonnull <V1> SELF mustWithChar(@Nonnull LObjCharPredicate<? super V1> predicate, V1 with1, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LObjCharPredicate.throwIfNot(with1, get(), predicate, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull <V1> SELF mustXWithChar(@Nonnull LObjCharPredicate<? super V1> predicate, V1 with1, @Nonnull ExMF<RuntimeException> ex, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LObjCharPredicate.throwIfNot(with1, get(), predicate, ex, message, messageParams);
		return self();
	}

	default @Nonnull <V1> SELF mustWithChar(V1 with1, @Nonnull LObjCharPredicate<? super V1> predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LObjCharPredicate.throwIfNot(with1, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull <V1> SELF mustWithChar$(V1 with1, @Nonnull LObjCharPredicate<? super V1> predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LObjCharPredicate.throwIfNot(with1, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull <V1> SELF mustWithChar$$(V1 with1, @Nonnull LObjCharPredicate<? super V1> predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LObjCharPredicate.throwIfNot(with1, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S_S_1, checkTraitType(), checkTraitName(), message, with1, get());
		return self();
	}

	default @Nonnull <V1> SELF mustWithChar(V1 with1, @Nonnull LObjCharPredicate<? super V1> predicate, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LObjCharPredicate.throwIfNot(with1, get(), predicate, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull <V1> SELF mustXWithChar(V1 with1, @Nonnull LObjCharPredicate<? super V1> predicate, @Nonnull ExMF<RuntimeException> ex, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LObjCharPredicate.throwIfNot(with1, get(), predicate, ex, message, messageParams);
		return self();
	}

	default @Nonnull <V1> SELF mustNotWithChar(@Nonnull LObjCharPredicate<? super V1> predicate, V1 with1, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LObjCharPredicate.throwIf(with1, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull <V1> SELF mustNotWithChar$(@Nonnull LObjCharPredicate<? super V1> predicate, V1 with1, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LObjCharPredicate.throwIf(with1, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull <V1> SELF mustNotWithChar$$(@Nonnull LObjCharPredicate<? super V1> predicate, V1 with1, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LObjCharPredicate.throwIf(with1, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S_S_1, checkTraitType(), checkTraitName(), message, with1, get());
		return self();
	}

	default @Nonnull <V1> SELF mustNotWithChar(@Nonnull LObjCharPredicate<? super V1> predicate, V1 with1, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LObjCharPredicate.throwIf(with1, get(), predicate, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull <V1> SELF mustXNotWithChar(@Nonnull LObjCharPredicate<? super V1> predicate, V1 with1, @Nonnull ExMF<RuntimeException> ex, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LObjCharPredicate.throwIf(with1, get(), predicate, ex, message, messageParams);
		return self();
	}

	default @Nonnull <V1> SELF mustNotWithChar(V1 with1, @Nonnull LObjCharPredicate<? super V1> predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LObjCharPredicate.throwIf(with1, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull <V1> SELF mustNotWithChar$(V1 with1, @Nonnull LObjCharPredicate<? super V1> predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LObjCharPredicate.throwIf(with1, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull <V1> SELF mustNotWithChar$$(V1 with1, @Nonnull LObjCharPredicate<? super V1> predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LObjCharPredicate.throwIf(with1, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S_S_1, checkTraitType(), checkTraitName(), message, with1, get());
		return self();
	}

	default @Nonnull <V1> SELF mustNotWithChar(V1 with1, @Nonnull LObjCharPredicate<? super V1> predicate, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LObjCharPredicate.throwIf(with1, get(), predicate, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull <V1> SELF mustXNotWithChar(V1 with1, @Nonnull LObjCharPredicate<? super V1> predicate, @Nonnull ExMF<RuntimeException> ex, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LObjCharPredicate.throwIf(with1, get(), predicate, ex, message, messageParams);
		return self();
	}

	default @Nonnull <V1, V2> SELF mustWith(@Nonnull LBiObjCharPredicate<? super V1, ? super V2> predicate, V1 with1, V2 with2, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LBiObjCharPredicate.throwIfNot(with1, with2, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull <V1, V2> SELF mustWith$(@Nonnull LBiObjCharPredicate<? super V1, ? super V2> predicate, V1 with1, V2 with2, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LBiObjCharPredicate.throwIfNot(with1, with2, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull <V1, V2> SELF mustWith$$(@Nonnull LBiObjCharPredicate<? super V1, ? super V2> predicate, V1 with1, V2 with2, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LBiObjCharPredicate.throwIfNot(with1, with2, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S_S_2, checkTraitType(), checkTraitName(), message, with1, with2, get());
		return self();
	}

	default @Nonnull <V1, V2> SELF mustWith(@Nonnull LBiObjCharPredicate<? super V1, ? super V2> predicate, V1 with1, V2 with2, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LBiObjCharPredicate.throwIfNot(with1, with2, get(), predicate, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull <V1, V2> SELF mustXWith(@Nonnull LBiObjCharPredicate<? super V1, ? super V2> predicate, V1 with1, V2 with2, @Nonnull ExMF<RuntimeException> ex, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LBiObjCharPredicate.throwIfNot(with1, with2, get(), predicate, ex, message, messageParams);
		return self();
	}

	default @Nonnull <V1, V2> SELF mustWith(V1 with1, V2 with2, @Nonnull LBiObjCharPredicate<? super V1, ? super V2> predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LBiObjCharPredicate.throwIfNot(with1, with2, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull <V1, V2> SELF mustWith$(V1 with1, V2 with2, @Nonnull LBiObjCharPredicate<? super V1, ? super V2> predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LBiObjCharPredicate.throwIfNot(with1, with2, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull <V1, V2> SELF mustWith$$(V1 with1, V2 with2, @Nonnull LBiObjCharPredicate<? super V1, ? super V2> predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LBiObjCharPredicate.throwIfNot(with1, with2, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S_S_2, checkTraitType(), checkTraitName(), message, with1, with2, get());
		return self();
	}

	default @Nonnull <V1, V2> SELF mustWith(V1 with1, V2 with2, @Nonnull LBiObjCharPredicate<? super V1, ? super V2> predicate, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LBiObjCharPredicate.throwIfNot(with1, with2, get(), predicate, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull <V1, V2> SELF mustXWith(V1 with1, V2 with2, @Nonnull LBiObjCharPredicate<? super V1, ? super V2> predicate, @Nonnull ExMF<RuntimeException> ex, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LBiObjCharPredicate.throwIfNot(with1, with2, get(), predicate, ex, message, messageParams);
		return self();
	}

	default @Nonnull <V1, V2> SELF mustNotWith(@Nonnull LBiObjCharPredicate<? super V1, ? super V2> predicate, V1 with1, V2 with2, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LBiObjCharPredicate.throwIf(with1, with2, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull <V1, V2> SELF mustNotWith$(@Nonnull LBiObjCharPredicate<? super V1, ? super V2> predicate, V1 with1, V2 with2, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LBiObjCharPredicate.throwIf(with1, with2, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull <V1, V2> SELF mustNotWith$$(@Nonnull LBiObjCharPredicate<? super V1, ? super V2> predicate, V1 with1, V2 with2, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LBiObjCharPredicate.throwIf(with1, with2, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S_S_2, checkTraitType(), checkTraitName(), message, with1, with2, get());
		return self();
	}

	default @Nonnull <V1, V2> SELF mustNotWith(@Nonnull LBiObjCharPredicate<? super V1, ? super V2> predicate, V1 with1, V2 with2, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LBiObjCharPredicate.throwIf(with1, with2, get(), predicate, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull <V1, V2> SELF mustXNotWith(@Nonnull LBiObjCharPredicate<? super V1, ? super V2> predicate, V1 with1, V2 with2, @Nonnull ExMF<RuntimeException> ex, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LBiObjCharPredicate.throwIf(with1, with2, get(), predicate, ex, message, messageParams);
		return self();
	}

	default @Nonnull <V1, V2> SELF mustNotWith(V1 with1, V2 with2, @Nonnull LBiObjCharPredicate<? super V1, ? super V2> predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LBiObjCharPredicate.throwIf(with1, with2, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull <V1, V2> SELF mustNotWith$(V1 with1, V2 with2, @Nonnull LBiObjCharPredicate<? super V1, ? super V2> predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LBiObjCharPredicate.throwIf(with1, with2, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull <V1, V2> SELF mustNotWith$$(V1 with1, V2 with2, @Nonnull LBiObjCharPredicate<? super V1, ? super V2> predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LBiObjCharPredicate.throwIf(with1, with2, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S_S_2, checkTraitType(), checkTraitName(), message, with1, with2, get());
		return self();
	}

	default @Nonnull <V1, V2> SELF mustNotWith(V1 with1, V2 with2, @Nonnull LBiObjCharPredicate<? super V1, ? super V2> predicate, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LBiObjCharPredicate.throwIf(with1, with2, get(), predicate, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull <V1, V2> SELF mustXNotWith(V1 with1, V2 with2, @Nonnull LBiObjCharPredicate<? super V1, ? super V2> predicate, @Nonnull ExMF<RuntimeException> ex, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LBiObjCharPredicate.throwIf(with1, with2, get(), predicate, ex, message, messageParams);
		return self();
	}

	default @Nonnull SELF checkWhen(@Nonnull LCharPredicate pred, @Nonnull LConsumer<SELF> conditionalChecks) {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(conditionalChecks, "conditionalChecks");
		if (pred.test(get())) {
			conditionalChecks.accept(self());
		};
		return self();
	}

	default @Nonnull SELF checkWhenNot(@Nonnull LCharPredicate pred, @Nonnull LConsumer<SELF> conditionalChecks) {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(conditionalChecks, "conditionalChecks");
		if (!pred.test(get())) {
			conditionalChecks.accept(self());
		};
		return self();
	}

	default @Nonnull SELF checkWhen(@Nonnull LBiCharPredicate pred, char a2, @Nonnull LConsumer<SELF> conditionalChecks) {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(conditionalChecks, "conditionalChecks");
		if (pred.test(get(), a2)) {
			conditionalChecks.accept(self());
		};
		return self();
	}

	default @Nonnull SELF checkWhenNot(@Nonnull LBiCharPredicate pred, char a2, @Nonnull LConsumer<SELF> conditionalChecks) {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(conditionalChecks, "conditionalChecks");
		if (!pred.test(get(), a2)) {
			conditionalChecks.accept(self());
		};
		return self();
	}

	default @Nonnull SELF checkWhen(@Nonnull LTriCharPredicate pred, char a2, char a3, @Nonnull LConsumer<SELF> conditionalChecks) {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(conditionalChecks, "conditionalChecks");
		if (pred.test(get(), a2, a3)) {
			conditionalChecks.accept(self());
		};
		return self();
	}

	default @Nonnull SELF checkWhenNot(@Nonnull LTriCharPredicate pred, char a2, char a3, @Nonnull LConsumer<SELF> conditionalChecks) {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(conditionalChecks, "conditionalChecks");
		if (!pred.test(get(), a2, a3)) {
			conditionalChecks.accept(self());
		};
		return self();
	}

	// </editor-fold>

	default @Nonnull SELF fails(@Nonnull String newMessage) {
		must(LCharPredicate::alwaysFalse, newMessage);
		return self();
	}

	default @Nonnull SELF fails$(@Nonnull String newMessage) {
		must$(LCharPredicate::alwaysFalse, newMessage);
		return self();
	}

	default @Nonnull SELF fails(@Nonnull String newMessage, @Nullable Object... messageParams) {
		must(LCharPredicate::alwaysFalse, newMessage, messageParams);
		return self();
	}

}
