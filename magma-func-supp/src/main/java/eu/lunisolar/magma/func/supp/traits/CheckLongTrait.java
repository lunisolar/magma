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

public interface CheckLongTrait<SELF extends CheckLongTrait<SELF>> extends FluentTrait<SELF>, aValue<aLong>, LLongSingle, LongValueTrait<SELF> {

	long get();

	default long value() {
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

	default @Nonnull SELF must(@Nonnull LLongPredicate predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LLongPredicate.throwIfNot(get(), predicate, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull SELF must$(@Nonnull LLongPredicate predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LLongPredicate.throwIfNot(get(), predicate, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull SELF must(@Nonnull LLongPredicate predicate, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LLongPredicate.throwIfNot(get(), predicate, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull SELF mustX(@Nonnull LLongPredicate predicate, @Nonnull ExMF<RuntimeException> ex, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LLongPredicate.throwIfNot(get(), predicate, ex, message, messageParams);
		return self();
	}

	default @Nonnull SELF mustNot(@Nonnull LLongPredicate predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LLongPredicate.throwIf(get(), predicate, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull SELF mustNot$(@Nonnull LLongPredicate predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LLongPredicate.throwIf(get(), predicate, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull SELF mustNot(@Nonnull LLongPredicate predicate, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LLongPredicate.throwIf(get(), predicate, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull SELF mustXNot(@Nonnull LLongPredicate predicate, @Nonnull ExMF<RuntimeException> ex, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LLongPredicate.throwIf(get(), predicate, ex, message, messageParams);
		return self();
	}

	default @Nonnull SELF must(@Nonnull LBiLongPredicate predicate, long a2, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LBiLongPredicate.throwIfNot(get(), a2, predicate, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull SELF must$(@Nonnull LBiLongPredicate predicate, long a2, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LBiLongPredicate.throwIfNot(get(), a2, predicate, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull SELF must$$(@Nonnull LBiLongPredicate predicate, long a2, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LBiLongPredicate.throwIfNot(get(), a2, predicate, checkTraitFactory(), MESSAGE_S_S_S_S_1, checkTraitType(), checkTraitName(), message, a2, get());
		return self();
	}

	default @Nonnull SELF must(@Nonnull LBiLongPredicate predicate, long a2, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LBiLongPredicate.throwIfNot(get(), a2, predicate, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull SELF mustX(@Nonnull LBiLongPredicate predicate, long a2, @Nonnull ExMF<RuntimeException> ex, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LBiLongPredicate.throwIfNot(get(), a2, predicate, ex, message, messageParams);
		return self();
	}

	default @Nonnull SELF must(long a2, @Nonnull LBiLongPredicate predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LBiLongPredicate.throwIfNot(get(), a2, predicate, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull SELF must$(long a2, @Nonnull LBiLongPredicate predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LBiLongPredicate.throwIfNot(get(), a2, predicate, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull SELF must$$(long a2, @Nonnull LBiLongPredicate predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LBiLongPredicate.throwIfNot(get(), a2, predicate, checkTraitFactory(), MESSAGE_S_S_S_S_1, checkTraitType(), checkTraitName(), message, a2, get());
		return self();
	}

	default @Nonnull SELF must(long a2, @Nonnull LBiLongPredicate predicate, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LBiLongPredicate.throwIfNot(get(), a2, predicate, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull SELF mustX(long a2, @Nonnull LBiLongPredicate predicate, @Nonnull ExMF<RuntimeException> ex, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LBiLongPredicate.throwIfNot(get(), a2, predicate, ex, message, messageParams);
		return self();
	}

	default @Nonnull SELF mustNot(@Nonnull LBiLongPredicate predicate, long a2, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LBiLongPredicate.throwIf(get(), a2, predicate, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull SELF mustNot$(@Nonnull LBiLongPredicate predicate, long a2, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LBiLongPredicate.throwIf(get(), a2, predicate, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull SELF mustNot$$(@Nonnull LBiLongPredicate predicate, long a2, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LBiLongPredicate.throwIf(get(), a2, predicate, checkTraitFactory(), MESSAGE_S_S_S_S_1, checkTraitType(), checkTraitName(), message, a2, get());
		return self();
	}

	default @Nonnull SELF mustNot(@Nonnull LBiLongPredicate predicate, long a2, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LBiLongPredicate.throwIf(get(), a2, predicate, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull SELF mustXNot(@Nonnull LBiLongPredicate predicate, long a2, @Nonnull ExMF<RuntimeException> ex, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LBiLongPredicate.throwIf(get(), a2, predicate, ex, message, messageParams);
		return self();
	}

	default @Nonnull SELF mustNot(long a2, @Nonnull LBiLongPredicate predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LBiLongPredicate.throwIf(get(), a2, predicate, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull SELF mustNot$(long a2, @Nonnull LBiLongPredicate predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LBiLongPredicate.throwIf(get(), a2, predicate, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull SELF mustNot$$(long a2, @Nonnull LBiLongPredicate predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LBiLongPredicate.throwIf(get(), a2, predicate, checkTraitFactory(), MESSAGE_S_S_S_S_1, checkTraitType(), checkTraitName(), message, a2, get());
		return self();
	}

	default @Nonnull SELF mustNot(long a2, @Nonnull LBiLongPredicate predicate, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LBiLongPredicate.throwIf(get(), a2, predicate, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull SELF mustXNot(long a2, @Nonnull LBiLongPredicate predicate, @Nonnull ExMF<RuntimeException> ex, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LBiLongPredicate.throwIf(get(), a2, predicate, ex, message, messageParams);
		return self();
	}

	default @Nonnull SELF must(@Nonnull LTriLongPredicate predicate, long a2, long a3, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LTriLongPredicate.throwIfNot(get(), a2, a3, predicate, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull SELF must$(@Nonnull LTriLongPredicate predicate, long a2, long a3, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LTriLongPredicate.throwIfNot(get(), a2, a3, predicate, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull SELF must$$(@Nonnull LTriLongPredicate predicate, long a2, long a3, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LTriLongPredicate.throwIfNot(get(), a2, a3, predicate, checkTraitFactory(), MESSAGE_S_S_S_S_2, checkTraitType(), checkTraitName(), message, a2, a3, get());
		return self();
	}

	default @Nonnull SELF must(@Nonnull LTriLongPredicate predicate, long a2, long a3, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LTriLongPredicate.throwIfNot(get(), a2, a3, predicate, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull SELF mustX(@Nonnull LTriLongPredicate predicate, long a2, long a3, @Nonnull ExMF<RuntimeException> ex, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LTriLongPredicate.throwIfNot(get(), a2, a3, predicate, ex, message, messageParams);
		return self();
	}

	default @Nonnull SELF must(long a2, long a3, @Nonnull LTriLongPredicate predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LTriLongPredicate.throwIfNot(get(), a2, a3, predicate, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull SELF must$(long a2, long a3, @Nonnull LTriLongPredicate predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LTriLongPredicate.throwIfNot(get(), a2, a3, predicate, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull SELF must$$(long a2, long a3, @Nonnull LTriLongPredicate predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LTriLongPredicate.throwIfNot(get(), a2, a3, predicate, checkTraitFactory(), MESSAGE_S_S_S_S_2, checkTraitType(), checkTraitName(), message, a2, a3, get());
		return self();
	}

	default @Nonnull SELF must(long a2, long a3, @Nonnull LTriLongPredicate predicate, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LTriLongPredicate.throwIfNot(get(), a2, a3, predicate, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull SELF mustX(long a2, long a3, @Nonnull LTriLongPredicate predicate, @Nonnull ExMF<RuntimeException> ex, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LTriLongPredicate.throwIfNot(get(), a2, a3, predicate, ex, message, messageParams);
		return self();
	}

	default @Nonnull SELF mustNot(@Nonnull LTriLongPredicate predicate, long a2, long a3, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LTriLongPredicate.throwIf(get(), a2, a3, predicate, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull SELF mustNot$(@Nonnull LTriLongPredicate predicate, long a2, long a3, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LTriLongPredicate.throwIf(get(), a2, a3, predicate, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull SELF mustNot$$(@Nonnull LTriLongPredicate predicate, long a2, long a3, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LTriLongPredicate.throwIf(get(), a2, a3, predicate, checkTraitFactory(), MESSAGE_S_S_S_S_2, checkTraitType(), checkTraitName(), message, a2, a3, get());
		return self();
	}

	default @Nonnull SELF mustNot(@Nonnull LTriLongPredicate predicate, long a2, long a3, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LTriLongPredicate.throwIf(get(), a2, a3, predicate, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull SELF mustXNot(@Nonnull LTriLongPredicate predicate, long a2, long a3, @Nonnull ExMF<RuntimeException> ex, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LTriLongPredicate.throwIf(get(), a2, a3, predicate, ex, message, messageParams);
		return self();
	}

	default @Nonnull SELF mustNot(long a2, long a3, @Nonnull LTriLongPredicate predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LTriLongPredicate.throwIf(get(), a2, a3, predicate, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull SELF mustNot$(long a2, long a3, @Nonnull LTriLongPredicate predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LTriLongPredicate.throwIf(get(), a2, a3, predicate, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull SELF mustNot$$(long a2, long a3, @Nonnull LTriLongPredicate predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LTriLongPredicate.throwIf(get(), a2, a3, predicate, checkTraitFactory(), MESSAGE_S_S_S_S_2, checkTraitType(), checkTraitName(), message, a2, a3, get());
		return self();
	}

	default @Nonnull SELF mustNot(long a2, long a3, @Nonnull LTriLongPredicate predicate, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LTriLongPredicate.throwIf(get(), a2, a3, predicate, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull SELF mustXNot(long a2, long a3, @Nonnull LTriLongPredicate predicate, @Nonnull ExMF<RuntimeException> ex, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LTriLongPredicate.throwIf(get(), a2, a3, predicate, ex, message, messageParams);
		return self();
	}

	default @Nonnull SELF mustInt(@Nonnull LLongIntPredicate predicate, int v, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LLongIntPredicate.throwIfNot(get(), v, predicate, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull SELF mustInt$(@Nonnull LLongIntPredicate predicate, int v, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LLongIntPredicate.throwIfNot(get(), v, predicate, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull SELF mustInt$$(@Nonnull LLongIntPredicate predicate, int v, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LLongIntPredicate.throwIfNot(get(), v, predicate, checkTraitFactory(), MESSAGE_S_S_S_S_1, checkTraitType(), checkTraitName(), message, v, get());
		return self();
	}

	default @Nonnull SELF mustInt(@Nonnull LLongIntPredicate predicate, int v, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LLongIntPredicate.throwIfNot(get(), v, predicate, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull SELF mustXInt(@Nonnull LLongIntPredicate predicate, int v, @Nonnull ExMF<RuntimeException> ex, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LLongIntPredicate.throwIfNot(get(), v, predicate, ex, message, messageParams);
		return self();
	}

	default @Nonnull SELF mustInt(int v, @Nonnull LLongIntPredicate predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LLongIntPredicate.throwIfNot(get(), v, predicate, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull SELF mustInt$(int v, @Nonnull LLongIntPredicate predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LLongIntPredicate.throwIfNot(get(), v, predicate, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull SELF mustInt$$(int v, @Nonnull LLongIntPredicate predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LLongIntPredicate.throwIfNot(get(), v, predicate, checkTraitFactory(), MESSAGE_S_S_S_S_1, checkTraitType(), checkTraitName(), message, v, get());
		return self();
	}

	default @Nonnull SELF mustInt(int v, @Nonnull LLongIntPredicate predicate, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LLongIntPredicate.throwIfNot(get(), v, predicate, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull SELF mustXInt(int v, @Nonnull LLongIntPredicate predicate, @Nonnull ExMF<RuntimeException> ex, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LLongIntPredicate.throwIfNot(get(), v, predicate, ex, message, messageParams);
		return self();
	}

	default @Nonnull SELF mustNotInt(@Nonnull LLongIntPredicate predicate, int v, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LLongIntPredicate.throwIf(get(), v, predicate, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull SELF mustNotInt$(@Nonnull LLongIntPredicate predicate, int v, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LLongIntPredicate.throwIf(get(), v, predicate, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull SELF mustNotInt$$(@Nonnull LLongIntPredicate predicate, int v, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LLongIntPredicate.throwIf(get(), v, predicate, checkTraitFactory(), MESSAGE_S_S_S_S_1, checkTraitType(), checkTraitName(), message, v, get());
		return self();
	}

	default @Nonnull SELF mustNotInt(@Nonnull LLongIntPredicate predicate, int v, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LLongIntPredicate.throwIf(get(), v, predicate, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull SELF mustXNotInt(@Nonnull LLongIntPredicate predicate, int v, @Nonnull ExMF<RuntimeException> ex, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LLongIntPredicate.throwIf(get(), v, predicate, ex, message, messageParams);
		return self();
	}

	default @Nonnull SELF mustNotInt(int v, @Nonnull LLongIntPredicate predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LLongIntPredicate.throwIf(get(), v, predicate, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull SELF mustNotInt$(int v, @Nonnull LLongIntPredicate predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LLongIntPredicate.throwIf(get(), v, predicate, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull SELF mustNotInt$$(int v, @Nonnull LLongIntPredicate predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LLongIntPredicate.throwIf(get(), v, predicate, checkTraitFactory(), MESSAGE_S_S_S_S_1, checkTraitType(), checkTraitName(), message, v, get());
		return self();
	}

	default @Nonnull SELF mustNotInt(int v, @Nonnull LLongIntPredicate predicate, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LLongIntPredicate.throwIf(get(), v, predicate, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull SELF mustXNotInt(int v, @Nonnull LLongIntPredicate predicate, @Nonnull ExMF<RuntimeException> ex, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LLongIntPredicate.throwIf(get(), v, predicate, ex, message, messageParams);
		return self();
	}

	default @Nonnull <V> SELF must_(@Nonnull LObjLongPredicate.LLongObjPred<? super V> predicate, V v, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LObjLongPredicate.throwIfNot(v, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull <V> SELF must_$(@Nonnull LObjLongPredicate.LLongObjPred<? super V> predicate, V v, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LObjLongPredicate.throwIfNot(v, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull <V> SELF must_$$(@Nonnull LObjLongPredicate.LLongObjPred<? super V> predicate, V v, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LObjLongPredicate.throwIfNot(v, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S_S_1, checkTraitType(), checkTraitName(), message, v, get());
		return self();
	}

	default @Nonnull <V> SELF must_(@Nonnull LObjLongPredicate.LLongObjPred<? super V> predicate, V v, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LObjLongPredicate.throwIfNot(v, get(), predicate, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull <V> SELF mustX_(@Nonnull LObjLongPredicate.LLongObjPred<? super V> predicate, V v, @Nonnull ExMF<RuntimeException> ex, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LObjLongPredicate.throwIfNot(v, get(), predicate, ex, message, messageParams);
		return self();
	}

	default @Nonnull <V> SELF must_(V v, @Nonnull LObjLongPredicate.LLongObjPred<? super V> predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LObjLongPredicate.throwIfNot(v, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull <V> SELF must_$(V v, @Nonnull LObjLongPredicate.LLongObjPred<? super V> predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LObjLongPredicate.throwIfNot(v, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull <V> SELF must_$$(V v, @Nonnull LObjLongPredicate.LLongObjPred<? super V> predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LObjLongPredicate.throwIfNot(v, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S_S_1, checkTraitType(), checkTraitName(), message, v, get());
		return self();
	}

	default @Nonnull <V> SELF must_(V v, @Nonnull LObjLongPredicate.LLongObjPred<? super V> predicate, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LObjLongPredicate.throwIfNot(v, get(), predicate, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull <V> SELF mustX_(V v, @Nonnull LObjLongPredicate.LLongObjPred<? super V> predicate, @Nonnull ExMF<RuntimeException> ex, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LObjLongPredicate.throwIfNot(v, get(), predicate, ex, message, messageParams);
		return self();
	}

	default @Nonnull <V> SELF mustNot_(@Nonnull LObjLongPredicate.LLongObjPred<? super V> predicate, V v, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LObjLongPredicate.throwIf(v, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull <V> SELF mustNot_$(@Nonnull LObjLongPredicate.LLongObjPred<? super V> predicate, V v, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LObjLongPredicate.throwIf(v, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull <V> SELF mustNot_$$(@Nonnull LObjLongPredicate.LLongObjPred<? super V> predicate, V v, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LObjLongPredicate.throwIf(v, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S_S_1, checkTraitType(), checkTraitName(), message, v, get());
		return self();
	}

	default @Nonnull <V> SELF mustNot_(@Nonnull LObjLongPredicate.LLongObjPred<? super V> predicate, V v, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LObjLongPredicate.throwIf(v, get(), predicate, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull <V> SELF mustXNot_(@Nonnull LObjLongPredicate.LLongObjPred<? super V> predicate, V v, @Nonnull ExMF<RuntimeException> ex, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LObjLongPredicate.throwIf(v, get(), predicate, ex, message, messageParams);
		return self();
	}

	default @Nonnull <V> SELF mustNot_(V v, @Nonnull LObjLongPredicate.LLongObjPred<? super V> predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LObjLongPredicate.throwIf(v, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull <V> SELF mustNot_$(V v, @Nonnull LObjLongPredicate.LLongObjPred<? super V> predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LObjLongPredicate.throwIf(v, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull <V> SELF mustNot_$$(V v, @Nonnull LObjLongPredicate.LLongObjPred<? super V> predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LObjLongPredicate.throwIf(v, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S_S_1, checkTraitType(), checkTraitName(), message, v, get());
		return self();
	}

	default @Nonnull <V> SELF mustNot_(V v, @Nonnull LObjLongPredicate.LLongObjPred<? super V> predicate, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LObjLongPredicate.throwIf(v, get(), predicate, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull <V> SELF mustXNot_(V v, @Nonnull LObjLongPredicate.LLongObjPred<? super V> predicate, @Nonnull ExMF<RuntimeException> ex, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LObjLongPredicate.throwIf(v, get(), predicate, ex, message, messageParams);
		return self();
	}

	default @Nonnull <V1> SELF mustWithLong(@Nonnull LObjLongPredicate<? super V1> predicate, V1 with1, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LObjLongPredicate.throwIfNot(with1, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull <V1> SELF mustWithLong$(@Nonnull LObjLongPredicate<? super V1> predicate, V1 with1, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LObjLongPredicate.throwIfNot(with1, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull <V1> SELF mustWithLong$$(@Nonnull LObjLongPredicate<? super V1> predicate, V1 with1, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LObjLongPredicate.throwIfNot(with1, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S_S_1, checkTraitType(), checkTraitName(), message, with1, get());
		return self();
	}

	default @Nonnull <V1> SELF mustWithLong(@Nonnull LObjLongPredicate<? super V1> predicate, V1 with1, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LObjLongPredicate.throwIfNot(with1, get(), predicate, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull <V1> SELF mustXWithLong(@Nonnull LObjLongPredicate<? super V1> predicate, V1 with1, @Nonnull ExMF<RuntimeException> ex, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LObjLongPredicate.throwIfNot(with1, get(), predicate, ex, message, messageParams);
		return self();
	}

	default @Nonnull <V1> SELF mustWithLong(V1 with1, @Nonnull LObjLongPredicate<? super V1> predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LObjLongPredicate.throwIfNot(with1, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull <V1> SELF mustWithLong$(V1 with1, @Nonnull LObjLongPredicate<? super V1> predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LObjLongPredicate.throwIfNot(with1, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull <V1> SELF mustWithLong$$(V1 with1, @Nonnull LObjLongPredicate<? super V1> predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LObjLongPredicate.throwIfNot(with1, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S_S_1, checkTraitType(), checkTraitName(), message, with1, get());
		return self();
	}

	default @Nonnull <V1> SELF mustWithLong(V1 with1, @Nonnull LObjLongPredicate<? super V1> predicate, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LObjLongPredicate.throwIfNot(with1, get(), predicate, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull <V1> SELF mustXWithLong(V1 with1, @Nonnull LObjLongPredicate<? super V1> predicate, @Nonnull ExMF<RuntimeException> ex, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LObjLongPredicate.throwIfNot(with1, get(), predicate, ex, message, messageParams);
		return self();
	}

	default @Nonnull <V1> SELF mustNotWithLong(@Nonnull LObjLongPredicate<? super V1> predicate, V1 with1, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LObjLongPredicate.throwIf(with1, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull <V1> SELF mustNotWithLong$(@Nonnull LObjLongPredicate<? super V1> predicate, V1 with1, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LObjLongPredicate.throwIf(with1, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull <V1> SELF mustNotWithLong$$(@Nonnull LObjLongPredicate<? super V1> predicate, V1 with1, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LObjLongPredicate.throwIf(with1, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S_S_1, checkTraitType(), checkTraitName(), message, with1, get());
		return self();
	}

	default @Nonnull <V1> SELF mustNotWithLong(@Nonnull LObjLongPredicate<? super V1> predicate, V1 with1, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LObjLongPredicate.throwIf(with1, get(), predicate, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull <V1> SELF mustXNotWithLong(@Nonnull LObjLongPredicate<? super V1> predicate, V1 with1, @Nonnull ExMF<RuntimeException> ex, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LObjLongPredicate.throwIf(with1, get(), predicate, ex, message, messageParams);
		return self();
	}

	default @Nonnull <V1> SELF mustNotWithLong(V1 with1, @Nonnull LObjLongPredicate<? super V1> predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LObjLongPredicate.throwIf(with1, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull <V1> SELF mustNotWithLong$(V1 with1, @Nonnull LObjLongPredicate<? super V1> predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LObjLongPredicate.throwIf(with1, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull <V1> SELF mustNotWithLong$$(V1 with1, @Nonnull LObjLongPredicate<? super V1> predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LObjLongPredicate.throwIf(with1, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S_S_1, checkTraitType(), checkTraitName(), message, with1, get());
		return self();
	}

	default @Nonnull <V1> SELF mustNotWithLong(V1 with1, @Nonnull LObjLongPredicate<? super V1> predicate, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LObjLongPredicate.throwIf(with1, get(), predicate, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull <V1> SELF mustXNotWithLong(V1 with1, @Nonnull LObjLongPredicate<? super V1> predicate, @Nonnull ExMF<RuntimeException> ex, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LObjLongPredicate.throwIf(with1, get(), predicate, ex, message, messageParams);
		return self();
	}

	default @Nonnull <V1, V2> SELF mustWith(@Nonnull LBiObjLongPredicate<? super V1, ? super V2> predicate, V1 with1, V2 with2, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LBiObjLongPredicate.throwIfNot(with1, with2, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull <V1, V2> SELF mustWith$(@Nonnull LBiObjLongPredicate<? super V1, ? super V2> predicate, V1 with1, V2 with2, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LBiObjLongPredicate.throwIfNot(with1, with2, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull <V1, V2> SELF mustWith$$(@Nonnull LBiObjLongPredicate<? super V1, ? super V2> predicate, V1 with1, V2 with2, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LBiObjLongPredicate.throwIfNot(with1, with2, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S_S_2, checkTraitType(), checkTraitName(), message, with1, with2, get());
		return self();
	}

	default @Nonnull <V1, V2> SELF mustWith(@Nonnull LBiObjLongPredicate<? super V1, ? super V2> predicate, V1 with1, V2 with2, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LBiObjLongPredicate.throwIfNot(with1, with2, get(), predicate, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull <V1, V2> SELF mustXWith(@Nonnull LBiObjLongPredicate<? super V1, ? super V2> predicate, V1 with1, V2 with2, @Nonnull ExMF<RuntimeException> ex, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LBiObjLongPredicate.throwIfNot(with1, with2, get(), predicate, ex, message, messageParams);
		return self();
	}

	default @Nonnull <V1, V2> SELF mustWith(V1 with1, V2 with2, @Nonnull LBiObjLongPredicate<? super V1, ? super V2> predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LBiObjLongPredicate.throwIfNot(with1, with2, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull <V1, V2> SELF mustWith$(V1 with1, V2 with2, @Nonnull LBiObjLongPredicate<? super V1, ? super V2> predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LBiObjLongPredicate.throwIfNot(with1, with2, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull <V1, V2> SELF mustWith$$(V1 with1, V2 with2, @Nonnull LBiObjLongPredicate<? super V1, ? super V2> predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LBiObjLongPredicate.throwIfNot(with1, with2, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S_S_2, checkTraitType(), checkTraitName(), message, with1, with2, get());
		return self();
	}

	default @Nonnull <V1, V2> SELF mustWith(V1 with1, V2 with2, @Nonnull LBiObjLongPredicate<? super V1, ? super V2> predicate, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LBiObjLongPredicate.throwIfNot(with1, with2, get(), predicate, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull <V1, V2> SELF mustXWith(V1 with1, V2 with2, @Nonnull LBiObjLongPredicate<? super V1, ? super V2> predicate, @Nonnull ExMF<RuntimeException> ex, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LBiObjLongPredicate.throwIfNot(with1, with2, get(), predicate, ex, message, messageParams);
		return self();
	}

	default @Nonnull <V1, V2> SELF mustNotWith(@Nonnull LBiObjLongPredicate<? super V1, ? super V2> predicate, V1 with1, V2 with2, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LBiObjLongPredicate.throwIf(with1, with2, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull <V1, V2> SELF mustNotWith$(@Nonnull LBiObjLongPredicate<? super V1, ? super V2> predicate, V1 with1, V2 with2, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LBiObjLongPredicate.throwIf(with1, with2, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull <V1, V2> SELF mustNotWith$$(@Nonnull LBiObjLongPredicate<? super V1, ? super V2> predicate, V1 with1, V2 with2, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LBiObjLongPredicate.throwIf(with1, with2, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S_S_2, checkTraitType(), checkTraitName(), message, with1, with2, get());
		return self();
	}

	default @Nonnull <V1, V2> SELF mustNotWith(@Nonnull LBiObjLongPredicate<? super V1, ? super V2> predicate, V1 with1, V2 with2, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LBiObjLongPredicate.throwIf(with1, with2, get(), predicate, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull <V1, V2> SELF mustXNotWith(@Nonnull LBiObjLongPredicate<? super V1, ? super V2> predicate, V1 with1, V2 with2, @Nonnull ExMF<RuntimeException> ex, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LBiObjLongPredicate.throwIf(with1, with2, get(), predicate, ex, message, messageParams);
		return self();
	}

	default @Nonnull <V1, V2> SELF mustNotWith(V1 with1, V2 with2, @Nonnull LBiObjLongPredicate<? super V1, ? super V2> predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LBiObjLongPredicate.throwIf(with1, with2, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull <V1, V2> SELF mustNotWith$(V1 with1, V2 with2, @Nonnull LBiObjLongPredicate<? super V1, ? super V2> predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LBiObjLongPredicate.throwIf(with1, with2, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull <V1, V2> SELF mustNotWith$$(V1 with1, V2 with2, @Nonnull LBiObjLongPredicate<? super V1, ? super V2> predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LBiObjLongPredicate.throwIf(with1, with2, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S_S_2, checkTraitType(), checkTraitName(), message, with1, with2, get());
		return self();
	}

	default @Nonnull <V1, V2> SELF mustNotWith(V1 with1, V2 with2, @Nonnull LBiObjLongPredicate<? super V1, ? super V2> predicate, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LBiObjLongPredicate.throwIf(with1, with2, get(), predicate, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull <V1, V2> SELF mustXNotWith(V1 with1, V2 with2, @Nonnull LBiObjLongPredicate<? super V1, ? super V2> predicate, @Nonnull ExMF<RuntimeException> ex, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LBiObjLongPredicate.throwIf(with1, with2, get(), predicate, ex, message, messageParams);
		return self();
	}

	default @Nonnull SELF checkWhen(@Nonnull LLongPredicate pred, @Nonnull LConsumer<SELF> conditionalChecks) {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(conditionalChecks, "conditionalChecks");
		if (pred.test(get())) {
			conditionalChecks.accept(self());
		};
		return self();
	}

	default @Nonnull SELF checkWhenNot(@Nonnull LLongPredicate pred, @Nonnull LConsumer<SELF> conditionalChecks) {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(conditionalChecks, "conditionalChecks");
		if (!pred.test(get())) {
			conditionalChecks.accept(self());
		};
		return self();
	}

	default @Nonnull SELF checkWhen(@Nonnull LBiLongPredicate pred, long a2, @Nonnull LConsumer<SELF> conditionalChecks) {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(conditionalChecks, "conditionalChecks");
		if (pred.test(get(), a2)) {
			conditionalChecks.accept(self());
		};
		return self();
	}

	default @Nonnull SELF checkWhenNot(@Nonnull LBiLongPredicate pred, long a2, @Nonnull LConsumer<SELF> conditionalChecks) {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(conditionalChecks, "conditionalChecks");
		if (!pred.test(get(), a2)) {
			conditionalChecks.accept(self());
		};
		return self();
	}

	default @Nonnull SELF checkWhen(@Nonnull LTriLongPredicate pred, long a2, long a3, @Nonnull LConsumer<SELF> conditionalChecks) {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(conditionalChecks, "conditionalChecks");
		if (pred.test(get(), a2, a3)) {
			conditionalChecks.accept(self());
		};
		return self();
	}

	default @Nonnull SELF checkWhenNot(@Nonnull LTriLongPredicate pred, long a2, long a3, @Nonnull LConsumer<SELF> conditionalChecks) {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(conditionalChecks, "conditionalChecks");
		if (!pred.test(get(), a2, a3)) {
			conditionalChecks.accept(self());
		};
		return self();
	}

	// </editor-fold>

	default @Nonnull SELF fails(@Nonnull String newMessage) {
		must(LLongPredicate::alwaysFalse, newMessage);
		return self();
	}

	default @Nonnull SELF fails$(@Nonnull String newMessage) {
		must$(LLongPredicate::alwaysFalse, newMessage);
		return self();
	}

	default @Nonnull SELF fails(@Nonnull String newMessage, @Nullable Object... messageParams) {
		must(LLongPredicate::alwaysFalse, newMessage, messageParams);
		return self();
	}

}
