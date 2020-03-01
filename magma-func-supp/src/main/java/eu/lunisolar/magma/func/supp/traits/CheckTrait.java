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

public interface CheckTrait<T, SELF extends CheckTrait<T, SELF>> extends FluentTrait<SELF>, aValue<a<T>>, LSingle<T>, ValueTrait<T, SELF> {

	public static final String MESSAGE_S_S_S = "%s [%s]: %s";
	public static final String MESSAGE_S_S_S_S = "%s [%s]: %s [Value: `%s`]";
	public static final String MESSAGE_S_S_S_S_1 = "%s [%s]: %s [Param: `%s`; Value: `%s`]";
	public static final String MESSAGE_S_S_S_S_2 = "%s [%s]: %s [Params: `%s`, `%s`; Value: `%s`]";
	public static final String MESSAGE_S_S_S_S_3 = "%s [%s]: %s [Params: `%s`, `%s`, `%s`; Value: `%s`]";

	@Nullable
	T get();

	default @Nullable T value() {
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

	default @Nonnull SELF must(@Nonnull LPredicate<? super T> predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LPredicate.throwIfNot(get(), predicate, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull SELF must$(@Nonnull LPredicate<? super T> predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LPredicate.throwIfNot(get(), predicate, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull SELF must(@Nonnull LPredicate<? super T> predicate, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LPredicate.throwIfNot(get(), predicate, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull SELF mustX(@Nonnull LPredicate<? super T> predicate, @Nonnull ExMF<RuntimeException> ex, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LPredicate.throwIfNot(get(), predicate, ex, message, messageParams);
		return self();
	}

	default @Nonnull SELF mustNot(@Nonnull LPredicate<? super T> predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LPredicate.throwIf(get(), predicate, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull SELF mustNot$(@Nonnull LPredicate<? super T> predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LPredicate.throwIf(get(), predicate, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull SELF mustNot(@Nonnull LPredicate<? super T> predicate, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LPredicate.throwIf(get(), predicate, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull SELF mustXNot(@Nonnull LPredicate<? super T> predicate, @Nonnull ExMF<RuntimeException> ex, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LPredicate.throwIf(get(), predicate, ex, message, messageParams);
		return self();
	}

	default @Nonnull SELF uniMust(@Nonnull LBiPredicate<? super T, ? super T> predicate, T a2, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LBiPredicate.throwIfNot(get(), a2, predicate, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull SELF uniMust$(@Nonnull LBiPredicate<? super T, ? super T> predicate, T a2, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LBiPredicate.throwIfNot(get(), a2, predicate, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull SELF uniMust$$(@Nonnull LBiPredicate<? super T, ? super T> predicate, T a2, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LBiPredicate.throwIfNot(get(), a2, predicate, checkTraitFactory(), MESSAGE_S_S_S_S_1, checkTraitType(), checkTraitName(), message, a2, get());
		return self();
	}

	default @Nonnull SELF uniMust(@Nonnull LBiPredicate<? super T, ? super T> predicate, T a2, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LBiPredicate.throwIfNot(get(), a2, predicate, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull SELF uniMust(@Nonnull LBiPredicate<? super T, ? super T> predicate, T a2, @Nonnull ExMF<RuntimeException> ex, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LBiPredicate.throwIfNot(get(), a2, predicate, ex, message, messageParams);
		return self();
	}

	default @Nonnull SELF uniMust(T a2, @Nonnull LBiPredicate<? super T, ? super T> predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LBiPredicate.throwIfNot(get(), a2, predicate, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull SELF uniMust$(T a2, @Nonnull LBiPredicate<? super T, ? super T> predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LBiPredicate.throwIfNot(get(), a2, predicate, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull SELF uniMust$$(T a2, @Nonnull LBiPredicate<? super T, ? super T> predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LBiPredicate.throwIfNot(get(), a2, predicate, checkTraitFactory(), MESSAGE_S_S_S_S_1, checkTraitType(), checkTraitName(), message, a2, get());
		return self();
	}

	default @Nonnull SELF uniMust(T a2, @Nonnull LBiPredicate<? super T, ? super T> predicate, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LBiPredicate.throwIfNot(get(), a2, predicate, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull SELF uniMust(T a2, @Nonnull LBiPredicate<? super T, ? super T> predicate, @Nonnull ExMF<RuntimeException> ex, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LBiPredicate.throwIfNot(get(), a2, predicate, ex, message, messageParams);
		return self();
	}

	default @Nonnull SELF uniMustNot(@Nonnull LBiPredicate<? super T, ? super T> predicate, T a2, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LBiPredicate.throwIf(get(), a2, predicate, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull SELF uniMustNot$(@Nonnull LBiPredicate<? super T, ? super T> predicate, T a2, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LBiPredicate.throwIf(get(), a2, predicate, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull SELF uniMustNot$$(@Nonnull LBiPredicate<? super T, ? super T> predicate, T a2, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LBiPredicate.throwIf(get(), a2, predicate, checkTraitFactory(), MESSAGE_S_S_S_S_1, checkTraitType(), checkTraitName(), message, a2, get());
		return self();
	}

	default @Nonnull SELF uniMustNot(@Nonnull LBiPredicate<? super T, ? super T> predicate, T a2, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LBiPredicate.throwIf(get(), a2, predicate, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull SELF uniMustNot(@Nonnull LBiPredicate<? super T, ? super T> predicate, T a2, @Nonnull ExMF<RuntimeException> ex, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LBiPredicate.throwIf(get(), a2, predicate, ex, message, messageParams);
		return self();
	}

	default @Nonnull SELF uniMustNot(T a2, @Nonnull LBiPredicate<? super T, ? super T> predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LBiPredicate.throwIf(get(), a2, predicate, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull SELF uniMustNot$(T a2, @Nonnull LBiPredicate<? super T, ? super T> predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LBiPredicate.throwIf(get(), a2, predicate, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull SELF uniMustNot$$(T a2, @Nonnull LBiPredicate<? super T, ? super T> predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LBiPredicate.throwIf(get(), a2, predicate, checkTraitFactory(), MESSAGE_S_S_S_S_1, checkTraitType(), checkTraitName(), message, a2, get());
		return self();
	}

	default @Nonnull SELF uniMustNot(T a2, @Nonnull LBiPredicate<? super T, ? super T> predicate, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LBiPredicate.throwIf(get(), a2, predicate, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull SELF uniMustNot(T a2, @Nonnull LBiPredicate<? super T, ? super T> predicate, @Nonnull ExMF<RuntimeException> ex, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LBiPredicate.throwIf(get(), a2, predicate, ex, message, messageParams);
		return self();
	}

	default @Nonnull SELF uniMust(@Nonnull LTriPredicate<? super T, ? super T, ? super T> predicate, T a2, T a3, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LTriPredicate.throwIfNot(get(), a2, a3, predicate, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull SELF uniMust$(@Nonnull LTriPredicate<? super T, ? super T, ? super T> predicate, T a2, T a3, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LTriPredicate.throwIfNot(get(), a2, a3, predicate, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull SELF uniMust$$(@Nonnull LTriPredicate<? super T, ? super T, ? super T> predicate, T a2, T a3, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LTriPredicate.throwIfNot(get(), a2, a3, predicate, checkTraitFactory(), MESSAGE_S_S_S_S_2, checkTraitType(), checkTraitName(), message, a2, a3, get());
		return self();
	}

	default @Nonnull SELF uniMust(@Nonnull LTriPredicate<? super T, ? super T, ? super T> predicate, T a2, T a3, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LTriPredicate.throwIfNot(get(), a2, a3, predicate, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull SELF uniMust(@Nonnull LTriPredicate<? super T, ? super T, ? super T> predicate, T a2, T a3, @Nonnull ExMF<RuntimeException> ex, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LTriPredicate.throwIfNot(get(), a2, a3, predicate, ex, message, messageParams);
		return self();
	}

	default @Nonnull SELF uniMust(T a2, T a3, @Nonnull LTriPredicate<? super T, ? super T, ? super T> predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LTriPredicate.throwIfNot(get(), a2, a3, predicate, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull SELF uniMust$(T a2, T a3, @Nonnull LTriPredicate<? super T, ? super T, ? super T> predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LTriPredicate.throwIfNot(get(), a2, a3, predicate, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull SELF uniMust$$(T a2, T a3, @Nonnull LTriPredicate<? super T, ? super T, ? super T> predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LTriPredicate.throwIfNot(get(), a2, a3, predicate, checkTraitFactory(), MESSAGE_S_S_S_S_2, checkTraitType(), checkTraitName(), message, a2, a3, get());
		return self();
	}

	default @Nonnull SELF uniMust(T a2, T a3, @Nonnull LTriPredicate<? super T, ? super T, ? super T> predicate, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LTriPredicate.throwIfNot(get(), a2, a3, predicate, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull SELF uniMust(T a2, T a3, @Nonnull LTriPredicate<? super T, ? super T, ? super T> predicate, @Nonnull ExMF<RuntimeException> ex, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LTriPredicate.throwIfNot(get(), a2, a3, predicate, ex, message, messageParams);
		return self();
	}

	default @Nonnull SELF uniMustNot(@Nonnull LTriPredicate<? super T, ? super T, ? super T> predicate, T a2, T a3, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LTriPredicate.throwIf(get(), a2, a3, predicate, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull SELF uniMustNot$(@Nonnull LTriPredicate<? super T, ? super T, ? super T> predicate, T a2, T a3, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LTriPredicate.throwIf(get(), a2, a3, predicate, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull SELF uniMustNot$$(@Nonnull LTriPredicate<? super T, ? super T, ? super T> predicate, T a2, T a3, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LTriPredicate.throwIf(get(), a2, a3, predicate, checkTraitFactory(), MESSAGE_S_S_S_S_2, checkTraitType(), checkTraitName(), message, a2, a3, get());
		return self();
	}

	default @Nonnull SELF uniMustNot(@Nonnull LTriPredicate<? super T, ? super T, ? super T> predicate, T a2, T a3, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LTriPredicate.throwIf(get(), a2, a3, predicate, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull SELF uniMustNot(@Nonnull LTriPredicate<? super T, ? super T, ? super T> predicate, T a2, T a3, @Nonnull ExMF<RuntimeException> ex, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LTriPredicate.throwIf(get(), a2, a3, predicate, ex, message, messageParams);
		return self();
	}

	default @Nonnull SELF uniMustNot(T a2, T a3, @Nonnull LTriPredicate<? super T, ? super T, ? super T> predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LTriPredicate.throwIf(get(), a2, a3, predicate, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull SELF uniMustNot$(T a2, T a3, @Nonnull LTriPredicate<? super T, ? super T, ? super T> predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LTriPredicate.throwIf(get(), a2, a3, predicate, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull SELF uniMustNot$$(T a2, T a3, @Nonnull LTriPredicate<? super T, ? super T, ? super T> predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LTriPredicate.throwIf(get(), a2, a3, predicate, checkTraitFactory(), MESSAGE_S_S_S_S_2, checkTraitType(), checkTraitName(), message, a2, a3, get());
		return self();
	}

	default @Nonnull SELF uniMustNot(T a2, T a3, @Nonnull LTriPredicate<? super T, ? super T, ? super T> predicate, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LTriPredicate.throwIf(get(), a2, a3, predicate, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull SELF uniMustNot(T a2, T a3, @Nonnull LTriPredicate<? super T, ? super T, ? super T> predicate, @Nonnull ExMF<RuntimeException> ex, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LTriPredicate.throwIf(get(), a2, a3, predicate, ex, message, messageParams);
		return self();
	}

	default @Nonnull SELF uniMust(@Nonnull LQuadPredicate<? super T, ? super T, ? super T, ? super T> predicate, T a2, T a3, T a4, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LQuadPredicate.throwIfNot(get(), a2, a3, a4, predicate, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull SELF uniMust$(@Nonnull LQuadPredicate<? super T, ? super T, ? super T, ? super T> predicate, T a2, T a3, T a4, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LQuadPredicate.throwIfNot(get(), a2, a3, a4, predicate, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull SELF uniMust$$(@Nonnull LQuadPredicate<? super T, ? super T, ? super T, ? super T> predicate, T a2, T a3, T a4, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LQuadPredicate.throwIfNot(get(), a2, a3, a4, predicate, checkTraitFactory(), MESSAGE_S_S_S_S_3, checkTraitType(), checkTraitName(), message, a2, a3, a4, get());
		return self();
	}

	default @Nonnull SELF uniMust(@Nonnull LQuadPredicate<? super T, ? super T, ? super T, ? super T> predicate, T a2, T a3, T a4, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LQuadPredicate.throwIfNot(get(), a2, a3, a4, predicate, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull SELF uniMust(@Nonnull LQuadPredicate<? super T, ? super T, ? super T, ? super T> predicate, T a2, T a3, T a4, @Nonnull ExMF<RuntimeException> ex, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LQuadPredicate.throwIfNot(get(), a2, a3, a4, predicate, ex, message, messageParams);
		return self();
	}

	default @Nonnull SELF uniMust(T a2, T a3, T a4, @Nonnull LQuadPredicate<? super T, ? super T, ? super T, ? super T> predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LQuadPredicate.throwIfNot(get(), a2, a3, a4, predicate, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull SELF uniMust$(T a2, T a3, T a4, @Nonnull LQuadPredicate<? super T, ? super T, ? super T, ? super T> predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LQuadPredicate.throwIfNot(get(), a2, a3, a4, predicate, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull SELF uniMust$$(T a2, T a3, T a4, @Nonnull LQuadPredicate<? super T, ? super T, ? super T, ? super T> predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LQuadPredicate.throwIfNot(get(), a2, a3, a4, predicate, checkTraitFactory(), MESSAGE_S_S_S_S_3, checkTraitType(), checkTraitName(), message, a2, a3, a4, get());
		return self();
	}

	default @Nonnull SELF uniMust(T a2, T a3, T a4, @Nonnull LQuadPredicate<? super T, ? super T, ? super T, ? super T> predicate, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LQuadPredicate.throwIfNot(get(), a2, a3, a4, predicate, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull SELF uniMust(T a2, T a3, T a4, @Nonnull LQuadPredicate<? super T, ? super T, ? super T, ? super T> predicate, @Nonnull ExMF<RuntimeException> ex, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LQuadPredicate.throwIfNot(get(), a2, a3, a4, predicate, ex, message, messageParams);
		return self();
	}

	default @Nonnull SELF uniMustNot(@Nonnull LQuadPredicate<? super T, ? super T, ? super T, ? super T> predicate, T a2, T a3, T a4, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LQuadPredicate.throwIf(get(), a2, a3, a4, predicate, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull SELF uniMustNot$(@Nonnull LQuadPredicate<? super T, ? super T, ? super T, ? super T> predicate, T a2, T a3, T a4, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LQuadPredicate.throwIf(get(), a2, a3, a4, predicate, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull SELF uniMustNot$$(@Nonnull LQuadPredicate<? super T, ? super T, ? super T, ? super T> predicate, T a2, T a3, T a4, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LQuadPredicate.throwIf(get(), a2, a3, a4, predicate, checkTraitFactory(), MESSAGE_S_S_S_S_3, checkTraitType(), checkTraitName(), message, a2, a3, a4, get());
		return self();
	}

	default @Nonnull SELF uniMustNot(@Nonnull LQuadPredicate<? super T, ? super T, ? super T, ? super T> predicate, T a2, T a3, T a4, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LQuadPredicate.throwIf(get(), a2, a3, a4, predicate, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull SELF uniMustNot(@Nonnull LQuadPredicate<? super T, ? super T, ? super T, ? super T> predicate, T a2, T a3, T a4, @Nonnull ExMF<RuntimeException> ex, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LQuadPredicate.throwIf(get(), a2, a3, a4, predicate, ex, message, messageParams);
		return self();
	}

	default @Nonnull SELF uniMustNot(T a2, T a3, T a4, @Nonnull LQuadPredicate<? super T, ? super T, ? super T, ? super T> predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LQuadPredicate.throwIf(get(), a2, a3, a4, predicate, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull SELF uniMustNot$(T a2, T a3, T a4, @Nonnull LQuadPredicate<? super T, ? super T, ? super T, ? super T> predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LQuadPredicate.throwIf(get(), a2, a3, a4, predicate, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull SELF uniMustNot$$(T a2, T a3, T a4, @Nonnull LQuadPredicate<? super T, ? super T, ? super T, ? super T> predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LQuadPredicate.throwIf(get(), a2, a3, a4, predicate, checkTraitFactory(), MESSAGE_S_S_S_S_3, checkTraitType(), checkTraitName(), message, a2, a3, a4, get());
		return self();
	}

	default @Nonnull SELF uniMustNot(T a2, T a3, T a4, @Nonnull LQuadPredicate<? super T, ? super T, ? super T, ? super T> predicate, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LQuadPredicate.throwIf(get(), a2, a3, a4, predicate, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull SELF uniMustNot(T a2, T a3, T a4, @Nonnull LQuadPredicate<? super T, ? super T, ? super T, ? super T> predicate, @Nonnull ExMF<RuntimeException> ex, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LQuadPredicate.throwIf(get(), a2, a3, a4, predicate, ex, message, messageParams);
		return self();
	}

	default @Nonnull SELF mustBool(@Nonnull LObjBoolPredicate<? super T> predicate, boolean v, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LObjBoolPredicate.throwIfNot(get(), v, predicate, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull SELF mustBool$(@Nonnull LObjBoolPredicate<? super T> predicate, boolean v, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LObjBoolPredicate.throwIfNot(get(), v, predicate, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull SELF mustBool$$(@Nonnull LObjBoolPredicate<? super T> predicate, boolean v, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LObjBoolPredicate.throwIfNot(get(), v, predicate, checkTraitFactory(), MESSAGE_S_S_S_S_1, checkTraitType(), checkTraitName(), message, v, get());
		return self();
	}

	default @Nonnull SELF mustBool(@Nonnull LObjBoolPredicate<? super T> predicate, boolean v, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LObjBoolPredicate.throwIfNot(get(), v, predicate, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull SELF mustXBool(@Nonnull LObjBoolPredicate<? super T> predicate, boolean v, @Nonnull ExMF<RuntimeException> ex, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LObjBoolPredicate.throwIfNot(get(), v, predicate, ex, message, messageParams);
		return self();
	}

	default @Nonnull SELF mustBool(boolean v, @Nonnull LObjBoolPredicate<? super T> predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LObjBoolPredicate.throwIfNot(get(), v, predicate, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull SELF mustBool$(boolean v, @Nonnull LObjBoolPredicate<? super T> predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LObjBoolPredicate.throwIfNot(get(), v, predicate, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull SELF mustBool$$(boolean v, @Nonnull LObjBoolPredicate<? super T> predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LObjBoolPredicate.throwIfNot(get(), v, predicate, checkTraitFactory(), MESSAGE_S_S_S_S_1, checkTraitType(), checkTraitName(), message, v, get());
		return self();
	}

	default @Nonnull SELF mustBool(boolean v, @Nonnull LObjBoolPredicate<? super T> predicate, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LObjBoolPredicate.throwIfNot(get(), v, predicate, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull SELF mustXBool(boolean v, @Nonnull LObjBoolPredicate<? super T> predicate, @Nonnull ExMF<RuntimeException> ex, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LObjBoolPredicate.throwIfNot(get(), v, predicate, ex, message, messageParams);
		return self();
	}

	default @Nonnull SELF mustNotBool(@Nonnull LObjBoolPredicate<? super T> predicate, boolean v, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LObjBoolPredicate.throwIf(get(), v, predicate, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull SELF mustNotBool$(@Nonnull LObjBoolPredicate<? super T> predicate, boolean v, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LObjBoolPredicate.throwIf(get(), v, predicate, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull SELF mustNotBool$$(@Nonnull LObjBoolPredicate<? super T> predicate, boolean v, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LObjBoolPredicate.throwIf(get(), v, predicate, checkTraitFactory(), MESSAGE_S_S_S_S_1, checkTraitType(), checkTraitName(), message, v, get());
		return self();
	}

	default @Nonnull SELF mustNotBool(@Nonnull LObjBoolPredicate<? super T> predicate, boolean v, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LObjBoolPredicate.throwIf(get(), v, predicate, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull SELF mustXNotBool(@Nonnull LObjBoolPredicate<? super T> predicate, boolean v, @Nonnull ExMF<RuntimeException> ex, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LObjBoolPredicate.throwIf(get(), v, predicate, ex, message, messageParams);
		return self();
	}

	default @Nonnull SELF mustNotBool(boolean v, @Nonnull LObjBoolPredicate<? super T> predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LObjBoolPredicate.throwIf(get(), v, predicate, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull SELF mustNotBool$(boolean v, @Nonnull LObjBoolPredicate<? super T> predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LObjBoolPredicate.throwIf(get(), v, predicate, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull SELF mustNotBool$$(boolean v, @Nonnull LObjBoolPredicate<? super T> predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LObjBoolPredicate.throwIf(get(), v, predicate, checkTraitFactory(), MESSAGE_S_S_S_S_1, checkTraitType(), checkTraitName(), message, v, get());
		return self();
	}

	default @Nonnull SELF mustNotBool(boolean v, @Nonnull LObjBoolPredicate<? super T> predicate, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LObjBoolPredicate.throwIf(get(), v, predicate, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull SELF mustXNotBool(boolean v, @Nonnull LObjBoolPredicate<? super T> predicate, @Nonnull ExMF<RuntimeException> ex, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LObjBoolPredicate.throwIf(get(), v, predicate, ex, message, messageParams);
		return self();
	}

	default @Nonnull SELF mustByte(@Nonnull LObjBytePredicate<? super T> predicate, byte v, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LObjBytePredicate.throwIfNot(get(), v, predicate, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull SELF mustByte$(@Nonnull LObjBytePredicate<? super T> predicate, byte v, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LObjBytePredicate.throwIfNot(get(), v, predicate, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull SELF mustByte$$(@Nonnull LObjBytePredicate<? super T> predicate, byte v, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LObjBytePredicate.throwIfNot(get(), v, predicate, checkTraitFactory(), MESSAGE_S_S_S_S_1, checkTraitType(), checkTraitName(), message, v, get());
		return self();
	}

	default @Nonnull SELF mustByte(@Nonnull LObjBytePredicate<? super T> predicate, byte v, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LObjBytePredicate.throwIfNot(get(), v, predicate, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull SELF mustXByte(@Nonnull LObjBytePredicate<? super T> predicate, byte v, @Nonnull ExMF<RuntimeException> ex, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LObjBytePredicate.throwIfNot(get(), v, predicate, ex, message, messageParams);
		return self();
	}

	default @Nonnull SELF mustByte(byte v, @Nonnull LObjBytePredicate<? super T> predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LObjBytePredicate.throwIfNot(get(), v, predicate, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull SELF mustByte$(byte v, @Nonnull LObjBytePredicate<? super T> predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LObjBytePredicate.throwIfNot(get(), v, predicate, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull SELF mustByte$$(byte v, @Nonnull LObjBytePredicate<? super T> predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LObjBytePredicate.throwIfNot(get(), v, predicate, checkTraitFactory(), MESSAGE_S_S_S_S_1, checkTraitType(), checkTraitName(), message, v, get());
		return self();
	}

	default @Nonnull SELF mustByte(byte v, @Nonnull LObjBytePredicate<? super T> predicate, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LObjBytePredicate.throwIfNot(get(), v, predicate, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull SELF mustXByte(byte v, @Nonnull LObjBytePredicate<? super T> predicate, @Nonnull ExMF<RuntimeException> ex, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LObjBytePredicate.throwIfNot(get(), v, predicate, ex, message, messageParams);
		return self();
	}

	default @Nonnull SELF mustNotByte(@Nonnull LObjBytePredicate<? super T> predicate, byte v, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LObjBytePredicate.throwIf(get(), v, predicate, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull SELF mustNotByte$(@Nonnull LObjBytePredicate<? super T> predicate, byte v, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LObjBytePredicate.throwIf(get(), v, predicate, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull SELF mustNotByte$$(@Nonnull LObjBytePredicate<? super T> predicate, byte v, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LObjBytePredicate.throwIf(get(), v, predicate, checkTraitFactory(), MESSAGE_S_S_S_S_1, checkTraitType(), checkTraitName(), message, v, get());
		return self();
	}

	default @Nonnull SELF mustNotByte(@Nonnull LObjBytePredicate<? super T> predicate, byte v, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LObjBytePredicate.throwIf(get(), v, predicate, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull SELF mustXNotByte(@Nonnull LObjBytePredicate<? super T> predicate, byte v, @Nonnull ExMF<RuntimeException> ex, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LObjBytePredicate.throwIf(get(), v, predicate, ex, message, messageParams);
		return self();
	}

	default @Nonnull SELF mustNotByte(byte v, @Nonnull LObjBytePredicate<? super T> predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LObjBytePredicate.throwIf(get(), v, predicate, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull SELF mustNotByte$(byte v, @Nonnull LObjBytePredicate<? super T> predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LObjBytePredicate.throwIf(get(), v, predicate, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull SELF mustNotByte$$(byte v, @Nonnull LObjBytePredicate<? super T> predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LObjBytePredicate.throwIf(get(), v, predicate, checkTraitFactory(), MESSAGE_S_S_S_S_1, checkTraitType(), checkTraitName(), message, v, get());
		return self();
	}

	default @Nonnull SELF mustNotByte(byte v, @Nonnull LObjBytePredicate<? super T> predicate, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LObjBytePredicate.throwIf(get(), v, predicate, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull SELF mustXNotByte(byte v, @Nonnull LObjBytePredicate<? super T> predicate, @Nonnull ExMF<RuntimeException> ex, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LObjBytePredicate.throwIf(get(), v, predicate, ex, message, messageParams);
		return self();
	}

	default @Nonnull SELF mustDbl(@Nonnull LObjDblPredicate<? super T> predicate, double v, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LObjDblPredicate.throwIfNot(get(), v, predicate, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull SELF mustDbl$(@Nonnull LObjDblPredicate<? super T> predicate, double v, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LObjDblPredicate.throwIfNot(get(), v, predicate, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull SELF mustDbl$$(@Nonnull LObjDblPredicate<? super T> predicate, double v, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LObjDblPredicate.throwIfNot(get(), v, predicate, checkTraitFactory(), MESSAGE_S_S_S_S_1, checkTraitType(), checkTraitName(), message, v, get());
		return self();
	}

	default @Nonnull SELF mustDbl(@Nonnull LObjDblPredicate<? super T> predicate, double v, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LObjDblPredicate.throwIfNot(get(), v, predicate, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull SELF mustXDbl(@Nonnull LObjDblPredicate<? super T> predicate, double v, @Nonnull ExMF<RuntimeException> ex, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LObjDblPredicate.throwIfNot(get(), v, predicate, ex, message, messageParams);
		return self();
	}

	default @Nonnull SELF mustDbl(double v, @Nonnull LObjDblPredicate<? super T> predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LObjDblPredicate.throwIfNot(get(), v, predicate, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull SELF mustDbl$(double v, @Nonnull LObjDblPredicate<? super T> predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LObjDblPredicate.throwIfNot(get(), v, predicate, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull SELF mustDbl$$(double v, @Nonnull LObjDblPredicate<? super T> predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LObjDblPredicate.throwIfNot(get(), v, predicate, checkTraitFactory(), MESSAGE_S_S_S_S_1, checkTraitType(), checkTraitName(), message, v, get());
		return self();
	}

	default @Nonnull SELF mustDbl(double v, @Nonnull LObjDblPredicate<? super T> predicate, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LObjDblPredicate.throwIfNot(get(), v, predicate, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull SELF mustXDbl(double v, @Nonnull LObjDblPredicate<? super T> predicate, @Nonnull ExMF<RuntimeException> ex, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LObjDblPredicate.throwIfNot(get(), v, predicate, ex, message, messageParams);
		return self();
	}

	default @Nonnull SELF mustNotDbl(@Nonnull LObjDblPredicate<? super T> predicate, double v, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LObjDblPredicate.throwIf(get(), v, predicate, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull SELF mustNotDbl$(@Nonnull LObjDblPredicate<? super T> predicate, double v, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LObjDblPredicate.throwIf(get(), v, predicate, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull SELF mustNotDbl$$(@Nonnull LObjDblPredicate<? super T> predicate, double v, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LObjDblPredicate.throwIf(get(), v, predicate, checkTraitFactory(), MESSAGE_S_S_S_S_1, checkTraitType(), checkTraitName(), message, v, get());
		return self();
	}

	default @Nonnull SELF mustNotDbl(@Nonnull LObjDblPredicate<? super T> predicate, double v, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LObjDblPredicate.throwIf(get(), v, predicate, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull SELF mustXNotDbl(@Nonnull LObjDblPredicate<? super T> predicate, double v, @Nonnull ExMF<RuntimeException> ex, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LObjDblPredicate.throwIf(get(), v, predicate, ex, message, messageParams);
		return self();
	}

	default @Nonnull SELF mustNotDbl(double v, @Nonnull LObjDblPredicate<? super T> predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LObjDblPredicate.throwIf(get(), v, predicate, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull SELF mustNotDbl$(double v, @Nonnull LObjDblPredicate<? super T> predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LObjDblPredicate.throwIf(get(), v, predicate, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull SELF mustNotDbl$$(double v, @Nonnull LObjDblPredicate<? super T> predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LObjDblPredicate.throwIf(get(), v, predicate, checkTraitFactory(), MESSAGE_S_S_S_S_1, checkTraitType(), checkTraitName(), message, v, get());
		return self();
	}

	default @Nonnull SELF mustNotDbl(double v, @Nonnull LObjDblPredicate<? super T> predicate, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LObjDblPredicate.throwIf(get(), v, predicate, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull SELF mustXNotDbl(double v, @Nonnull LObjDblPredicate<? super T> predicate, @Nonnull ExMF<RuntimeException> ex, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LObjDblPredicate.throwIf(get(), v, predicate, ex, message, messageParams);
		return self();
	}

	default @Nonnull SELF mustChar(@Nonnull LObjCharPredicate<? super T> predicate, char v, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LObjCharPredicate.throwIfNot(get(), v, predicate, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull SELF mustChar$(@Nonnull LObjCharPredicate<? super T> predicate, char v, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LObjCharPredicate.throwIfNot(get(), v, predicate, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull SELF mustChar$$(@Nonnull LObjCharPredicate<? super T> predicate, char v, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LObjCharPredicate.throwIfNot(get(), v, predicate, checkTraitFactory(), MESSAGE_S_S_S_S_1, checkTraitType(), checkTraitName(), message, v, get());
		return self();
	}

	default @Nonnull SELF mustChar(@Nonnull LObjCharPredicate<? super T> predicate, char v, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LObjCharPredicate.throwIfNot(get(), v, predicate, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull SELF mustXChar(@Nonnull LObjCharPredicate<? super T> predicate, char v, @Nonnull ExMF<RuntimeException> ex, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LObjCharPredicate.throwIfNot(get(), v, predicate, ex, message, messageParams);
		return self();
	}

	default @Nonnull SELF mustChar(char v, @Nonnull LObjCharPredicate<? super T> predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LObjCharPredicate.throwIfNot(get(), v, predicate, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull SELF mustChar$(char v, @Nonnull LObjCharPredicate<? super T> predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LObjCharPredicate.throwIfNot(get(), v, predicate, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull SELF mustChar$$(char v, @Nonnull LObjCharPredicate<? super T> predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LObjCharPredicate.throwIfNot(get(), v, predicate, checkTraitFactory(), MESSAGE_S_S_S_S_1, checkTraitType(), checkTraitName(), message, v, get());
		return self();
	}

	default @Nonnull SELF mustChar(char v, @Nonnull LObjCharPredicate<? super T> predicate, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LObjCharPredicate.throwIfNot(get(), v, predicate, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull SELF mustXChar(char v, @Nonnull LObjCharPredicate<? super T> predicate, @Nonnull ExMF<RuntimeException> ex, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LObjCharPredicate.throwIfNot(get(), v, predicate, ex, message, messageParams);
		return self();
	}

	default @Nonnull SELF mustNotChar(@Nonnull LObjCharPredicate<? super T> predicate, char v, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LObjCharPredicate.throwIf(get(), v, predicate, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull SELF mustNotChar$(@Nonnull LObjCharPredicate<? super T> predicate, char v, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LObjCharPredicate.throwIf(get(), v, predicate, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull SELF mustNotChar$$(@Nonnull LObjCharPredicate<? super T> predicate, char v, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LObjCharPredicate.throwIf(get(), v, predicate, checkTraitFactory(), MESSAGE_S_S_S_S_1, checkTraitType(), checkTraitName(), message, v, get());
		return self();
	}

	default @Nonnull SELF mustNotChar(@Nonnull LObjCharPredicate<? super T> predicate, char v, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LObjCharPredicate.throwIf(get(), v, predicate, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull SELF mustXNotChar(@Nonnull LObjCharPredicate<? super T> predicate, char v, @Nonnull ExMF<RuntimeException> ex, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LObjCharPredicate.throwIf(get(), v, predicate, ex, message, messageParams);
		return self();
	}

	default @Nonnull SELF mustNotChar(char v, @Nonnull LObjCharPredicate<? super T> predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LObjCharPredicate.throwIf(get(), v, predicate, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull SELF mustNotChar$(char v, @Nonnull LObjCharPredicate<? super T> predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LObjCharPredicate.throwIf(get(), v, predicate, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull SELF mustNotChar$$(char v, @Nonnull LObjCharPredicate<? super T> predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LObjCharPredicate.throwIf(get(), v, predicate, checkTraitFactory(), MESSAGE_S_S_S_S_1, checkTraitType(), checkTraitName(), message, v, get());
		return self();
	}

	default @Nonnull SELF mustNotChar(char v, @Nonnull LObjCharPredicate<? super T> predicate, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LObjCharPredicate.throwIf(get(), v, predicate, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull SELF mustXNotChar(char v, @Nonnull LObjCharPredicate<? super T> predicate, @Nonnull ExMF<RuntimeException> ex, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LObjCharPredicate.throwIf(get(), v, predicate, ex, message, messageParams);
		return self();
	}

	default @Nonnull SELF mustSrt(@Nonnull LObjSrtPredicate<? super T> predicate, short v, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LObjSrtPredicate.throwIfNot(get(), v, predicate, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull SELF mustSrt$(@Nonnull LObjSrtPredicate<? super T> predicate, short v, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LObjSrtPredicate.throwIfNot(get(), v, predicate, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull SELF mustSrt$$(@Nonnull LObjSrtPredicate<? super T> predicate, short v, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LObjSrtPredicate.throwIfNot(get(), v, predicate, checkTraitFactory(), MESSAGE_S_S_S_S_1, checkTraitType(), checkTraitName(), message, v, get());
		return self();
	}

	default @Nonnull SELF mustSrt(@Nonnull LObjSrtPredicate<? super T> predicate, short v, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LObjSrtPredicate.throwIfNot(get(), v, predicate, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull SELF mustXSrt(@Nonnull LObjSrtPredicate<? super T> predicate, short v, @Nonnull ExMF<RuntimeException> ex, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LObjSrtPredicate.throwIfNot(get(), v, predicate, ex, message, messageParams);
		return self();
	}

	default @Nonnull SELF mustSrt(short v, @Nonnull LObjSrtPredicate<? super T> predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LObjSrtPredicate.throwIfNot(get(), v, predicate, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull SELF mustSrt$(short v, @Nonnull LObjSrtPredicate<? super T> predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LObjSrtPredicate.throwIfNot(get(), v, predicate, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull SELF mustSrt$$(short v, @Nonnull LObjSrtPredicate<? super T> predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LObjSrtPredicate.throwIfNot(get(), v, predicate, checkTraitFactory(), MESSAGE_S_S_S_S_1, checkTraitType(), checkTraitName(), message, v, get());
		return self();
	}

	default @Nonnull SELF mustSrt(short v, @Nonnull LObjSrtPredicate<? super T> predicate, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LObjSrtPredicate.throwIfNot(get(), v, predicate, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull SELF mustXSrt(short v, @Nonnull LObjSrtPredicate<? super T> predicate, @Nonnull ExMF<RuntimeException> ex, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LObjSrtPredicate.throwIfNot(get(), v, predicate, ex, message, messageParams);
		return self();
	}

	default @Nonnull SELF mustNotSrt(@Nonnull LObjSrtPredicate<? super T> predicate, short v, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LObjSrtPredicate.throwIf(get(), v, predicate, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull SELF mustNotSrt$(@Nonnull LObjSrtPredicate<? super T> predicate, short v, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LObjSrtPredicate.throwIf(get(), v, predicate, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull SELF mustNotSrt$$(@Nonnull LObjSrtPredicate<? super T> predicate, short v, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LObjSrtPredicate.throwIf(get(), v, predicate, checkTraitFactory(), MESSAGE_S_S_S_S_1, checkTraitType(), checkTraitName(), message, v, get());
		return self();
	}

	default @Nonnull SELF mustNotSrt(@Nonnull LObjSrtPredicate<? super T> predicate, short v, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LObjSrtPredicate.throwIf(get(), v, predicate, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull SELF mustXNotSrt(@Nonnull LObjSrtPredicate<? super T> predicate, short v, @Nonnull ExMF<RuntimeException> ex, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LObjSrtPredicate.throwIf(get(), v, predicate, ex, message, messageParams);
		return self();
	}

	default @Nonnull SELF mustNotSrt(short v, @Nonnull LObjSrtPredicate<? super T> predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LObjSrtPredicate.throwIf(get(), v, predicate, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull SELF mustNotSrt$(short v, @Nonnull LObjSrtPredicate<? super T> predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LObjSrtPredicate.throwIf(get(), v, predicate, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull SELF mustNotSrt$$(short v, @Nonnull LObjSrtPredicate<? super T> predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LObjSrtPredicate.throwIf(get(), v, predicate, checkTraitFactory(), MESSAGE_S_S_S_S_1, checkTraitType(), checkTraitName(), message, v, get());
		return self();
	}

	default @Nonnull SELF mustNotSrt(short v, @Nonnull LObjSrtPredicate<? super T> predicate, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LObjSrtPredicate.throwIf(get(), v, predicate, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull SELF mustXNotSrt(short v, @Nonnull LObjSrtPredicate<? super T> predicate, @Nonnull ExMF<RuntimeException> ex, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LObjSrtPredicate.throwIf(get(), v, predicate, ex, message, messageParams);
		return self();
	}

	default @Nonnull SELF mustFlt(@Nonnull LObjFltPredicate<? super T> predicate, float v, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LObjFltPredicate.throwIfNot(get(), v, predicate, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull SELF mustFlt$(@Nonnull LObjFltPredicate<? super T> predicate, float v, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LObjFltPredicate.throwIfNot(get(), v, predicate, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull SELF mustFlt$$(@Nonnull LObjFltPredicate<? super T> predicate, float v, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LObjFltPredicate.throwIfNot(get(), v, predicate, checkTraitFactory(), MESSAGE_S_S_S_S_1, checkTraitType(), checkTraitName(), message, v, get());
		return self();
	}

	default @Nonnull SELF mustFlt(@Nonnull LObjFltPredicate<? super T> predicate, float v, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LObjFltPredicate.throwIfNot(get(), v, predicate, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull SELF mustXFlt(@Nonnull LObjFltPredicate<? super T> predicate, float v, @Nonnull ExMF<RuntimeException> ex, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LObjFltPredicate.throwIfNot(get(), v, predicate, ex, message, messageParams);
		return self();
	}

	default @Nonnull SELF mustFlt(float v, @Nonnull LObjFltPredicate<? super T> predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LObjFltPredicate.throwIfNot(get(), v, predicate, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull SELF mustFlt$(float v, @Nonnull LObjFltPredicate<? super T> predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LObjFltPredicate.throwIfNot(get(), v, predicate, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull SELF mustFlt$$(float v, @Nonnull LObjFltPredicate<? super T> predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LObjFltPredicate.throwIfNot(get(), v, predicate, checkTraitFactory(), MESSAGE_S_S_S_S_1, checkTraitType(), checkTraitName(), message, v, get());
		return self();
	}

	default @Nonnull SELF mustFlt(float v, @Nonnull LObjFltPredicate<? super T> predicate, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LObjFltPredicate.throwIfNot(get(), v, predicate, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull SELF mustXFlt(float v, @Nonnull LObjFltPredicate<? super T> predicate, @Nonnull ExMF<RuntimeException> ex, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LObjFltPredicate.throwIfNot(get(), v, predicate, ex, message, messageParams);
		return self();
	}

	default @Nonnull SELF mustNotFlt(@Nonnull LObjFltPredicate<? super T> predicate, float v, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LObjFltPredicate.throwIf(get(), v, predicate, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull SELF mustNotFlt$(@Nonnull LObjFltPredicate<? super T> predicate, float v, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LObjFltPredicate.throwIf(get(), v, predicate, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull SELF mustNotFlt$$(@Nonnull LObjFltPredicate<? super T> predicate, float v, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LObjFltPredicate.throwIf(get(), v, predicate, checkTraitFactory(), MESSAGE_S_S_S_S_1, checkTraitType(), checkTraitName(), message, v, get());
		return self();
	}

	default @Nonnull SELF mustNotFlt(@Nonnull LObjFltPredicate<? super T> predicate, float v, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LObjFltPredicate.throwIf(get(), v, predicate, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull SELF mustXNotFlt(@Nonnull LObjFltPredicate<? super T> predicate, float v, @Nonnull ExMF<RuntimeException> ex, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LObjFltPredicate.throwIf(get(), v, predicate, ex, message, messageParams);
		return self();
	}

	default @Nonnull SELF mustNotFlt(float v, @Nonnull LObjFltPredicate<? super T> predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LObjFltPredicate.throwIf(get(), v, predicate, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull SELF mustNotFlt$(float v, @Nonnull LObjFltPredicate<? super T> predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LObjFltPredicate.throwIf(get(), v, predicate, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull SELF mustNotFlt$$(float v, @Nonnull LObjFltPredicate<? super T> predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LObjFltPredicate.throwIf(get(), v, predicate, checkTraitFactory(), MESSAGE_S_S_S_S_1, checkTraitType(), checkTraitName(), message, v, get());
		return self();
	}

	default @Nonnull SELF mustNotFlt(float v, @Nonnull LObjFltPredicate<? super T> predicate, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LObjFltPredicate.throwIf(get(), v, predicate, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull SELF mustXNotFlt(float v, @Nonnull LObjFltPredicate<? super T> predicate, @Nonnull ExMF<RuntimeException> ex, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LObjFltPredicate.throwIf(get(), v, predicate, ex, message, messageParams);
		return self();
	}

	default @Nonnull SELF mustInt(@Nonnull LObjIntPredicate<? super T> predicate, int v, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LObjIntPredicate.throwIfNot(get(), v, predicate, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull SELF mustInt$(@Nonnull LObjIntPredicate<? super T> predicate, int v, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LObjIntPredicate.throwIfNot(get(), v, predicate, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull SELF mustInt$$(@Nonnull LObjIntPredicate<? super T> predicate, int v, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LObjIntPredicate.throwIfNot(get(), v, predicate, checkTraitFactory(), MESSAGE_S_S_S_S_1, checkTraitType(), checkTraitName(), message, v, get());
		return self();
	}

	default @Nonnull SELF mustInt(@Nonnull LObjIntPredicate<? super T> predicate, int v, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LObjIntPredicate.throwIfNot(get(), v, predicate, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull SELF mustXInt(@Nonnull LObjIntPredicate<? super T> predicate, int v, @Nonnull ExMF<RuntimeException> ex, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LObjIntPredicate.throwIfNot(get(), v, predicate, ex, message, messageParams);
		return self();
	}

	default @Nonnull SELF mustInt(int v, @Nonnull LObjIntPredicate<? super T> predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LObjIntPredicate.throwIfNot(get(), v, predicate, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull SELF mustInt$(int v, @Nonnull LObjIntPredicate<? super T> predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LObjIntPredicate.throwIfNot(get(), v, predicate, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull SELF mustInt$$(int v, @Nonnull LObjIntPredicate<? super T> predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LObjIntPredicate.throwIfNot(get(), v, predicate, checkTraitFactory(), MESSAGE_S_S_S_S_1, checkTraitType(), checkTraitName(), message, v, get());
		return self();
	}

	default @Nonnull SELF mustInt(int v, @Nonnull LObjIntPredicate<? super T> predicate, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LObjIntPredicate.throwIfNot(get(), v, predicate, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull SELF mustXInt(int v, @Nonnull LObjIntPredicate<? super T> predicate, @Nonnull ExMF<RuntimeException> ex, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LObjIntPredicate.throwIfNot(get(), v, predicate, ex, message, messageParams);
		return self();
	}

	default @Nonnull SELF mustNotInt(@Nonnull LObjIntPredicate<? super T> predicate, int v, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LObjIntPredicate.throwIf(get(), v, predicate, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull SELF mustNotInt$(@Nonnull LObjIntPredicate<? super T> predicate, int v, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LObjIntPredicate.throwIf(get(), v, predicate, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull SELF mustNotInt$$(@Nonnull LObjIntPredicate<? super T> predicate, int v, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LObjIntPredicate.throwIf(get(), v, predicate, checkTraitFactory(), MESSAGE_S_S_S_S_1, checkTraitType(), checkTraitName(), message, v, get());
		return self();
	}

	default @Nonnull SELF mustNotInt(@Nonnull LObjIntPredicate<? super T> predicate, int v, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LObjIntPredicate.throwIf(get(), v, predicate, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull SELF mustXNotInt(@Nonnull LObjIntPredicate<? super T> predicate, int v, @Nonnull ExMF<RuntimeException> ex, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LObjIntPredicate.throwIf(get(), v, predicate, ex, message, messageParams);
		return self();
	}

	default @Nonnull SELF mustNotInt(int v, @Nonnull LObjIntPredicate<? super T> predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LObjIntPredicate.throwIf(get(), v, predicate, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull SELF mustNotInt$(int v, @Nonnull LObjIntPredicate<? super T> predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LObjIntPredicate.throwIf(get(), v, predicate, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull SELF mustNotInt$$(int v, @Nonnull LObjIntPredicate<? super T> predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LObjIntPredicate.throwIf(get(), v, predicate, checkTraitFactory(), MESSAGE_S_S_S_S_1, checkTraitType(), checkTraitName(), message, v, get());
		return self();
	}

	default @Nonnull SELF mustNotInt(int v, @Nonnull LObjIntPredicate<? super T> predicate, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LObjIntPredicate.throwIf(get(), v, predicate, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull SELF mustXNotInt(int v, @Nonnull LObjIntPredicate<? super T> predicate, @Nonnull ExMF<RuntimeException> ex, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LObjIntPredicate.throwIf(get(), v, predicate, ex, message, messageParams);
		return self();
	}

	default @Nonnull SELF mustLong(@Nonnull LObjLongPredicate<? super T> predicate, long v, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LObjLongPredicate.throwIfNot(get(), v, predicate, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull SELF mustLong$(@Nonnull LObjLongPredicate<? super T> predicate, long v, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LObjLongPredicate.throwIfNot(get(), v, predicate, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull SELF mustLong$$(@Nonnull LObjLongPredicate<? super T> predicate, long v, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LObjLongPredicate.throwIfNot(get(), v, predicate, checkTraitFactory(), MESSAGE_S_S_S_S_1, checkTraitType(), checkTraitName(), message, v, get());
		return self();
	}

	default @Nonnull SELF mustLong(@Nonnull LObjLongPredicate<? super T> predicate, long v, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LObjLongPredicate.throwIfNot(get(), v, predicate, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull SELF mustXLong(@Nonnull LObjLongPredicate<? super T> predicate, long v, @Nonnull ExMF<RuntimeException> ex, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LObjLongPredicate.throwIfNot(get(), v, predicate, ex, message, messageParams);
		return self();
	}

	default @Nonnull SELF mustLong(long v, @Nonnull LObjLongPredicate<? super T> predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LObjLongPredicate.throwIfNot(get(), v, predicate, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull SELF mustLong$(long v, @Nonnull LObjLongPredicate<? super T> predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LObjLongPredicate.throwIfNot(get(), v, predicate, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull SELF mustLong$$(long v, @Nonnull LObjLongPredicate<? super T> predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LObjLongPredicate.throwIfNot(get(), v, predicate, checkTraitFactory(), MESSAGE_S_S_S_S_1, checkTraitType(), checkTraitName(), message, v, get());
		return self();
	}

	default @Nonnull SELF mustLong(long v, @Nonnull LObjLongPredicate<? super T> predicate, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LObjLongPredicate.throwIfNot(get(), v, predicate, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull SELF mustXLong(long v, @Nonnull LObjLongPredicate<? super T> predicate, @Nonnull ExMF<RuntimeException> ex, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LObjLongPredicate.throwIfNot(get(), v, predicate, ex, message, messageParams);
		return self();
	}

	default @Nonnull SELF mustNotLong(@Nonnull LObjLongPredicate<? super T> predicate, long v, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LObjLongPredicate.throwIf(get(), v, predicate, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull SELF mustNotLong$(@Nonnull LObjLongPredicate<? super T> predicate, long v, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LObjLongPredicate.throwIf(get(), v, predicate, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull SELF mustNotLong$$(@Nonnull LObjLongPredicate<? super T> predicate, long v, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LObjLongPredicate.throwIf(get(), v, predicate, checkTraitFactory(), MESSAGE_S_S_S_S_1, checkTraitType(), checkTraitName(), message, v, get());
		return self();
	}

	default @Nonnull SELF mustNotLong(@Nonnull LObjLongPredicate<? super T> predicate, long v, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LObjLongPredicate.throwIf(get(), v, predicate, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull SELF mustXNotLong(@Nonnull LObjLongPredicate<? super T> predicate, long v, @Nonnull ExMF<RuntimeException> ex, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LObjLongPredicate.throwIf(get(), v, predicate, ex, message, messageParams);
		return self();
	}

	default @Nonnull SELF mustNotLong(long v, @Nonnull LObjLongPredicate<? super T> predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LObjLongPredicate.throwIf(get(), v, predicate, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull SELF mustNotLong$(long v, @Nonnull LObjLongPredicate<? super T> predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LObjLongPredicate.throwIf(get(), v, predicate, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull SELF mustNotLong$$(long v, @Nonnull LObjLongPredicate<? super T> predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LObjLongPredicate.throwIf(get(), v, predicate, checkTraitFactory(), MESSAGE_S_S_S_S_1, checkTraitType(), checkTraitName(), message, v, get());
		return self();
	}

	default @Nonnull SELF mustNotLong(long v, @Nonnull LObjLongPredicate<? super T> predicate, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LObjLongPredicate.throwIf(get(), v, predicate, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull SELF mustXNotLong(long v, @Nonnull LObjLongPredicate<? super T> predicate, @Nonnull ExMF<RuntimeException> ex, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LObjLongPredicate.throwIf(get(), v, predicate, ex, message, messageParams);
		return self();
	}

	default @Nonnull <V> SELF must(@Nonnull LBiPredicate<? super T, ? super V> predicate, V v, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LBiPredicate.throwIfNot(get(), v, predicate, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull <V> SELF must$(@Nonnull LBiPredicate<? super T, ? super V> predicate, V v, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LBiPredicate.throwIfNot(get(), v, predicate, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull <V> SELF must$$(@Nonnull LBiPredicate<? super T, ? super V> predicate, V v, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LBiPredicate.throwIfNot(get(), v, predicate, checkTraitFactory(), MESSAGE_S_S_S_S_1, checkTraitType(), checkTraitName(), message, v, get());
		return self();
	}

	default @Nonnull <V> SELF must(@Nonnull LBiPredicate<? super T, ? super V> predicate, V v, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LBiPredicate.throwIfNot(get(), v, predicate, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull <V> SELF mustX(@Nonnull LBiPredicate<? super T, ? super V> predicate, V v, @Nonnull ExMF<RuntimeException> ex, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LBiPredicate.throwIfNot(get(), v, predicate, ex, message, messageParams);
		return self();
	}

	default @Nonnull <V> SELF must(V v, @Nonnull LBiPredicate<? super T, ? super V> predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LBiPredicate.throwIfNot(get(), v, predicate, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull <V> SELF must$(V v, @Nonnull LBiPredicate<? super T, ? super V> predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LBiPredicate.throwIfNot(get(), v, predicate, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull <V> SELF must$$(V v, @Nonnull LBiPredicate<? super T, ? super V> predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LBiPredicate.throwIfNot(get(), v, predicate, checkTraitFactory(), MESSAGE_S_S_S_S_1, checkTraitType(), checkTraitName(), message, v, get());
		return self();
	}

	default @Nonnull <V> SELF must(V v, @Nonnull LBiPredicate<? super T, ? super V> predicate, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LBiPredicate.throwIfNot(get(), v, predicate, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull <V> SELF mustX(V v, @Nonnull LBiPredicate<? super T, ? super V> predicate, @Nonnull ExMF<RuntimeException> ex, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LBiPredicate.throwIfNot(get(), v, predicate, ex, message, messageParams);
		return self();
	}

	default @Nonnull <V> SELF mustNot(@Nonnull LBiPredicate<? super T, ? super V> predicate, V v, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LBiPredicate.throwIf(get(), v, predicate, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull <V> SELF mustNot$(@Nonnull LBiPredicate<? super T, ? super V> predicate, V v, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LBiPredicate.throwIf(get(), v, predicate, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull <V> SELF mustNot$$(@Nonnull LBiPredicate<? super T, ? super V> predicate, V v, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LBiPredicate.throwIf(get(), v, predicate, checkTraitFactory(), MESSAGE_S_S_S_S_1, checkTraitType(), checkTraitName(), message, v, get());
		return self();
	}

	default @Nonnull <V> SELF mustNot(@Nonnull LBiPredicate<? super T, ? super V> predicate, V v, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LBiPredicate.throwIf(get(), v, predicate, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull <V> SELF mustXNot(@Nonnull LBiPredicate<? super T, ? super V> predicate, V v, @Nonnull ExMF<RuntimeException> ex, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LBiPredicate.throwIf(get(), v, predicate, ex, message, messageParams);
		return self();
	}

	default @Nonnull <V> SELF mustNot(V v, @Nonnull LBiPredicate<? super T, ? super V> predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LBiPredicate.throwIf(get(), v, predicate, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull <V> SELF mustNot$(V v, @Nonnull LBiPredicate<? super T, ? super V> predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LBiPredicate.throwIf(get(), v, predicate, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull <V> SELF mustNot$$(V v, @Nonnull LBiPredicate<? super T, ? super V> predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LBiPredicate.throwIf(get(), v, predicate, checkTraitFactory(), MESSAGE_S_S_S_S_1, checkTraitType(), checkTraitName(), message, v, get());
		return self();
	}

	default @Nonnull <V> SELF mustNot(V v, @Nonnull LBiPredicate<? super T, ? super V> predicate, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LBiPredicate.throwIf(get(), v, predicate, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull <V> SELF mustXNot(V v, @Nonnull LBiPredicate<? super T, ? super V> predicate, @Nonnull ExMF<RuntimeException> ex, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LBiPredicate.throwIf(get(), v, predicate, ex, message, messageParams);
		return self();
	}

	default @Nonnull <V2, V3> SELF must(@Nonnull LTriPredicate<? super T, ? super V2, ? super V3> predicate, V2 a2, V3 a3, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LTriPredicate.throwIfNot(get(), a2, a3, predicate, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull <V2, V3> SELF must$(@Nonnull LTriPredicate<? super T, ? super V2, ? super V3> predicate, V2 a2, V3 a3, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LTriPredicate.throwIfNot(get(), a2, a3, predicate, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull <V2, V3> SELF must$$(@Nonnull LTriPredicate<? super T, ? super V2, ? super V3> predicate, V2 a2, V3 a3, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LTriPredicate.throwIfNot(get(), a2, a3, predicate, checkTraitFactory(), MESSAGE_S_S_S_S_2, checkTraitType(), checkTraitName(), message, a2, a3, get());
		return self();
	}

	default @Nonnull <V2, V3> SELF must(@Nonnull LTriPredicate<? super T, ? super V2, ? super V3> predicate, V2 a2, V3 a3, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LTriPredicate.throwIfNot(get(), a2, a3, predicate, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull <V2, V3> SELF mustX(@Nonnull LTriPredicate<? super T, ? super V2, ? super V3> predicate, V2 a2, V3 a3, @Nonnull ExMF<RuntimeException> ex, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LTriPredicate.throwIfNot(get(), a2, a3, predicate, ex, message, messageParams);
		return self();
	}

	default @Nonnull <V2, V3> SELF must(V2 a2, V3 a3, @Nonnull LTriPredicate<? super T, ? super V2, ? super V3> predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LTriPredicate.throwIfNot(get(), a2, a3, predicate, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull <V2, V3> SELF must$(V2 a2, V3 a3, @Nonnull LTriPredicate<? super T, ? super V2, ? super V3> predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LTriPredicate.throwIfNot(get(), a2, a3, predicate, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull <V2, V3> SELF must$$(V2 a2, V3 a3, @Nonnull LTriPredicate<? super T, ? super V2, ? super V3> predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LTriPredicate.throwIfNot(get(), a2, a3, predicate, checkTraitFactory(), MESSAGE_S_S_S_S_2, checkTraitType(), checkTraitName(), message, a2, a3, get());
		return self();
	}

	default @Nonnull <V2, V3> SELF must(V2 a2, V3 a3, @Nonnull LTriPredicate<? super T, ? super V2, ? super V3> predicate, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LTriPredicate.throwIfNot(get(), a2, a3, predicate, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull <V2, V3> SELF mustX(V2 a2, V3 a3, @Nonnull LTriPredicate<? super T, ? super V2, ? super V3> predicate, @Nonnull ExMF<RuntimeException> ex, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LTriPredicate.throwIfNot(get(), a2, a3, predicate, ex, message, messageParams);
		return self();
	}

	default @Nonnull <V2, V3> SELF mustNot(@Nonnull LTriPredicate<? super T, ? super V2, ? super V3> predicate, V2 a2, V3 a3, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LTriPredicate.throwIf(get(), a2, a3, predicate, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull <V2, V3> SELF mustNot$(@Nonnull LTriPredicate<? super T, ? super V2, ? super V3> predicate, V2 a2, V3 a3, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LTriPredicate.throwIf(get(), a2, a3, predicate, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull <V2, V3> SELF mustNot$$(@Nonnull LTriPredicate<? super T, ? super V2, ? super V3> predicate, V2 a2, V3 a3, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LTriPredicate.throwIf(get(), a2, a3, predicate, checkTraitFactory(), MESSAGE_S_S_S_S_2, checkTraitType(), checkTraitName(), message, a2, a3, get());
		return self();
	}

	default @Nonnull <V2, V3> SELF mustNot(@Nonnull LTriPredicate<? super T, ? super V2, ? super V3> predicate, V2 a2, V3 a3, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LTriPredicate.throwIf(get(), a2, a3, predicate, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull <V2, V3> SELF mustXNot(@Nonnull LTriPredicate<? super T, ? super V2, ? super V3> predicate, V2 a2, V3 a3, @Nonnull ExMF<RuntimeException> ex, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LTriPredicate.throwIf(get(), a2, a3, predicate, ex, message, messageParams);
		return self();
	}

	default @Nonnull <V2, V3> SELF mustNot(V2 a2, V3 a3, @Nonnull LTriPredicate<? super T, ? super V2, ? super V3> predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LTriPredicate.throwIf(get(), a2, a3, predicate, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull <V2, V3> SELF mustNot$(V2 a2, V3 a3, @Nonnull LTriPredicate<? super T, ? super V2, ? super V3> predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LTriPredicate.throwIf(get(), a2, a3, predicate, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull <V2, V3> SELF mustNot$$(V2 a2, V3 a3, @Nonnull LTriPredicate<? super T, ? super V2, ? super V3> predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LTriPredicate.throwIf(get(), a2, a3, predicate, checkTraitFactory(), MESSAGE_S_S_S_S_2, checkTraitType(), checkTraitName(), message, a2, a3, get());
		return self();
	}

	default @Nonnull <V2, V3> SELF mustNot(V2 a2, V3 a3, @Nonnull LTriPredicate<? super T, ? super V2, ? super V3> predicate, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LTriPredicate.throwIf(get(), a2, a3, predicate, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull <V2, V3> SELF mustXNot(V2 a2, V3 a3, @Nonnull LTriPredicate<? super T, ? super V2, ? super V3> predicate, @Nonnull ExMF<RuntimeException> ex, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LTriPredicate.throwIf(get(), a2, a3, predicate, ex, message, messageParams);
		return self();
	}

	default @Nonnull <V2, V3, V4> SELF must(@Nonnull LQuadPredicate<? super T, ? super V2, ? super V3, ? super V4> predicate, V2 a2, V3 a3, V4 a4, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LQuadPredicate.throwIfNot(get(), a2, a3, a4, predicate, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull <V2, V3, V4> SELF must$(@Nonnull LQuadPredicate<? super T, ? super V2, ? super V3, ? super V4> predicate, V2 a2, V3 a3, V4 a4, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LQuadPredicate.throwIfNot(get(), a2, a3, a4, predicate, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull <V2, V3, V4> SELF must$$(@Nonnull LQuadPredicate<? super T, ? super V2, ? super V3, ? super V4> predicate, V2 a2, V3 a3, V4 a4, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LQuadPredicate.throwIfNot(get(), a2, a3, a4, predicate, checkTraitFactory(), MESSAGE_S_S_S_S_3, checkTraitType(), checkTraitName(), message, a2, a3, a4, get());
		return self();
	}

	default @Nonnull <V2, V3, V4> SELF must(@Nonnull LQuadPredicate<? super T, ? super V2, ? super V3, ? super V4> predicate, V2 a2, V3 a3, V4 a4, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LQuadPredicate.throwIfNot(get(), a2, a3, a4, predicate, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull <V2, V3, V4> SELF mustX(@Nonnull LQuadPredicate<? super T, ? super V2, ? super V3, ? super V4> predicate, V2 a2, V3 a3, V4 a4, @Nonnull ExMF<RuntimeException> ex, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LQuadPredicate.throwIfNot(get(), a2, a3, a4, predicate, ex, message, messageParams);
		return self();
	}

	default @Nonnull <V2, V3, V4> SELF must(V2 a2, V3 a3, V4 a4, @Nonnull LQuadPredicate<? super T, ? super V2, ? super V3, ? super V4> predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LQuadPredicate.throwIfNot(get(), a2, a3, a4, predicate, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull <V2, V3, V4> SELF must$(V2 a2, V3 a3, V4 a4, @Nonnull LQuadPredicate<? super T, ? super V2, ? super V3, ? super V4> predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LQuadPredicate.throwIfNot(get(), a2, a3, a4, predicate, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull <V2, V3, V4> SELF must$$(V2 a2, V3 a3, V4 a4, @Nonnull LQuadPredicate<? super T, ? super V2, ? super V3, ? super V4> predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LQuadPredicate.throwIfNot(get(), a2, a3, a4, predicate, checkTraitFactory(), MESSAGE_S_S_S_S_3, checkTraitType(), checkTraitName(), message, a2, a3, a4, get());
		return self();
	}

	default @Nonnull <V2, V3, V4> SELF must(V2 a2, V3 a3, V4 a4, @Nonnull LQuadPredicate<? super T, ? super V2, ? super V3, ? super V4> predicate, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LQuadPredicate.throwIfNot(get(), a2, a3, a4, predicate, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull <V2, V3, V4> SELF mustX(V2 a2, V3 a3, V4 a4, @Nonnull LQuadPredicate<? super T, ? super V2, ? super V3, ? super V4> predicate, @Nonnull ExMF<RuntimeException> ex, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LQuadPredicate.throwIfNot(get(), a2, a3, a4, predicate, ex, message, messageParams);
		return self();
	}

	default @Nonnull <V2, V3, V4> SELF mustNot(@Nonnull LQuadPredicate<? super T, ? super V2, ? super V3, ? super V4> predicate, V2 a2, V3 a3, V4 a4, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LQuadPredicate.throwIf(get(), a2, a3, a4, predicate, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull <V2, V3, V4> SELF mustNot$(@Nonnull LQuadPredicate<? super T, ? super V2, ? super V3, ? super V4> predicate, V2 a2, V3 a3, V4 a4, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LQuadPredicate.throwIf(get(), a2, a3, a4, predicate, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull <V2, V3, V4> SELF mustNot$$(@Nonnull LQuadPredicate<? super T, ? super V2, ? super V3, ? super V4> predicate, V2 a2, V3 a3, V4 a4, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LQuadPredicate.throwIf(get(), a2, a3, a4, predicate, checkTraitFactory(), MESSAGE_S_S_S_S_3, checkTraitType(), checkTraitName(), message, a2, a3, a4, get());
		return self();
	}

	default @Nonnull <V2, V3, V4> SELF mustNot(@Nonnull LQuadPredicate<? super T, ? super V2, ? super V3, ? super V4> predicate, V2 a2, V3 a3, V4 a4, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LQuadPredicate.throwIf(get(), a2, a3, a4, predicate, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull <V2, V3, V4> SELF mustXNot(@Nonnull LQuadPredicate<? super T, ? super V2, ? super V3, ? super V4> predicate, V2 a2, V3 a3, V4 a4, @Nonnull ExMF<RuntimeException> ex, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LQuadPredicate.throwIf(get(), a2, a3, a4, predicate, ex, message, messageParams);
		return self();
	}

	default @Nonnull <V2, V3, V4> SELF mustNot(V2 a2, V3 a3, V4 a4, @Nonnull LQuadPredicate<? super T, ? super V2, ? super V3, ? super V4> predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LQuadPredicate.throwIf(get(), a2, a3, a4, predicate, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull <V2, V3, V4> SELF mustNot$(V2 a2, V3 a3, V4 a4, @Nonnull LQuadPredicate<? super T, ? super V2, ? super V3, ? super V4> predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LQuadPredicate.throwIf(get(), a2, a3, a4, predicate, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull <V2, V3, V4> SELF mustNot$$(V2 a2, V3 a3, V4 a4, @Nonnull LQuadPredicate<? super T, ? super V2, ? super V3, ? super V4> predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LQuadPredicate.throwIf(get(), a2, a3, a4, predicate, checkTraitFactory(), MESSAGE_S_S_S_S_3, checkTraitType(), checkTraitName(), message, a2, a3, a4, get());
		return self();
	}

	default @Nonnull <V2, V3, V4> SELF mustNot(V2 a2, V3 a3, V4 a4, @Nonnull LQuadPredicate<? super T, ? super V2, ? super V3, ? super V4> predicate, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LQuadPredicate.throwIf(get(), a2, a3, a4, predicate, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull <V2, V3, V4> SELF mustXNot(V2 a2, V3 a3, V4 a4, @Nonnull LQuadPredicate<? super T, ? super V2, ? super V3, ? super V4> predicate, @Nonnull ExMF<RuntimeException> ex, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LQuadPredicate.throwIf(get(), a2, a3, a4, predicate, ex, message, messageParams);
		return self();
	}

	default @Nonnull <V1> SELF mustWith(@Nonnull LBiPredicate<? super V1, ? super T> predicate, V1 with1, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LBiPredicate.throwIfNot(with1, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull <V1> SELF mustWith$(@Nonnull LBiPredicate<? super V1, ? super T> predicate, V1 with1, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LBiPredicate.throwIfNot(with1, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull <V1> SELF mustWith$$(@Nonnull LBiPredicate<? super V1, ? super T> predicate, V1 with1, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LBiPredicate.throwIfNot(with1, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S_S_1, checkTraitType(), checkTraitName(), message, with1, get());
		return self();
	}

	default @Nonnull <V1> SELF mustWith(@Nonnull LBiPredicate<? super V1, ? super T> predicate, V1 with1, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LBiPredicate.throwIfNot(with1, get(), predicate, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull <V1> SELF mustXWith(@Nonnull LBiPredicate<? super V1, ? super T> predicate, V1 with1, @Nonnull ExMF<RuntimeException> ex, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LBiPredicate.throwIfNot(with1, get(), predicate, ex, message, messageParams);
		return self();
	}

	default @Nonnull <V1> SELF mustWith(V1 with1, @Nonnull LBiPredicate<? super V1, ? super T> predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LBiPredicate.throwIfNot(with1, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull <V1> SELF mustWith$(V1 with1, @Nonnull LBiPredicate<? super V1, ? super T> predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LBiPredicate.throwIfNot(with1, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull <V1> SELF mustWith$$(V1 with1, @Nonnull LBiPredicate<? super V1, ? super T> predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LBiPredicate.throwIfNot(with1, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S_S_1, checkTraitType(), checkTraitName(), message, with1, get());
		return self();
	}

	default @Nonnull <V1> SELF mustWith(V1 with1, @Nonnull LBiPredicate<? super V1, ? super T> predicate, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LBiPredicate.throwIfNot(with1, get(), predicate, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull <V1> SELF mustXWith(V1 with1, @Nonnull LBiPredicate<? super V1, ? super T> predicate, @Nonnull ExMF<RuntimeException> ex, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LBiPredicate.throwIfNot(with1, get(), predicate, ex, message, messageParams);
		return self();
	}

	default @Nonnull <V1> SELF mustNotWith(@Nonnull LBiPredicate<? super V1, ? super T> predicate, V1 with1, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LBiPredicate.throwIf(with1, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull <V1> SELF mustNotWith$(@Nonnull LBiPredicate<? super V1, ? super T> predicate, V1 with1, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LBiPredicate.throwIf(with1, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull <V1> SELF mustNotWith$$(@Nonnull LBiPredicate<? super V1, ? super T> predicate, V1 with1, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LBiPredicate.throwIf(with1, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S_S_1, checkTraitType(), checkTraitName(), message, with1, get());
		return self();
	}

	default @Nonnull <V1> SELF mustNotWith(@Nonnull LBiPredicate<? super V1, ? super T> predicate, V1 with1, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LBiPredicate.throwIf(with1, get(), predicate, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull <V1> SELF mustXNotWith(@Nonnull LBiPredicate<? super V1, ? super T> predicate, V1 with1, @Nonnull ExMF<RuntimeException> ex, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LBiPredicate.throwIf(with1, get(), predicate, ex, message, messageParams);
		return self();
	}

	default @Nonnull <V1> SELF mustNotWith(V1 with1, @Nonnull LBiPredicate<? super V1, ? super T> predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LBiPredicate.throwIf(with1, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull <V1> SELF mustNotWith$(V1 with1, @Nonnull LBiPredicate<? super V1, ? super T> predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LBiPredicate.throwIf(with1, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull <V1> SELF mustNotWith$$(V1 with1, @Nonnull LBiPredicate<? super V1, ? super T> predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LBiPredicate.throwIf(with1, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S_S_1, checkTraitType(), checkTraitName(), message, with1, get());
		return self();
	}

	default @Nonnull <V1> SELF mustNotWith(V1 with1, @Nonnull LBiPredicate<? super V1, ? super T> predicate, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LBiPredicate.throwIf(with1, get(), predicate, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull <V1> SELF mustXNotWith(V1 with1, @Nonnull LBiPredicate<? super V1, ? super T> predicate, @Nonnull ExMF<RuntimeException> ex, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LBiPredicate.throwIf(with1, get(), predicate, ex, message, messageParams);
		return self();
	}

	default @Nonnull <V1, V2> SELF mustWith(@Nonnull LTriPredicate<? super V1, ? super V2, ? super T> predicate, V1 with1, V2 with2, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LTriPredicate.throwIfNot(with1, with2, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull <V1, V2> SELF mustWith$(@Nonnull LTriPredicate<? super V1, ? super V2, ? super T> predicate, V1 with1, V2 with2, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LTriPredicate.throwIfNot(with1, with2, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull <V1, V2> SELF mustWith$$(@Nonnull LTriPredicate<? super V1, ? super V2, ? super T> predicate, V1 with1, V2 with2, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LTriPredicate.throwIfNot(with1, with2, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S_S_2, checkTraitType(), checkTraitName(), message, with1, with2, get());
		return self();
	}

	default @Nonnull <V1, V2> SELF mustWith(@Nonnull LTriPredicate<? super V1, ? super V2, ? super T> predicate, V1 with1, V2 with2, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LTriPredicate.throwIfNot(with1, with2, get(), predicate, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull <V1, V2> SELF mustXWith(@Nonnull LTriPredicate<? super V1, ? super V2, ? super T> predicate, V1 with1, V2 with2, @Nonnull ExMF<RuntimeException> ex, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LTriPredicate.throwIfNot(with1, with2, get(), predicate, ex, message, messageParams);
		return self();
	}

	default @Nonnull <V1, V2> SELF mustWith(V1 with1, V2 with2, @Nonnull LTriPredicate<? super V1, ? super V2, ? super T> predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LTriPredicate.throwIfNot(with1, with2, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull <V1, V2> SELF mustWith$(V1 with1, V2 with2, @Nonnull LTriPredicate<? super V1, ? super V2, ? super T> predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LTriPredicate.throwIfNot(with1, with2, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull <V1, V2> SELF mustWith$$(V1 with1, V2 with2, @Nonnull LTriPredicate<? super V1, ? super V2, ? super T> predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LTriPredicate.throwIfNot(with1, with2, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S_S_2, checkTraitType(), checkTraitName(), message, with1, with2, get());
		return self();
	}

	default @Nonnull <V1, V2> SELF mustWith(V1 with1, V2 with2, @Nonnull LTriPredicate<? super V1, ? super V2, ? super T> predicate, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LTriPredicate.throwIfNot(with1, with2, get(), predicate, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull <V1, V2> SELF mustXWith(V1 with1, V2 with2, @Nonnull LTriPredicate<? super V1, ? super V2, ? super T> predicate, @Nonnull ExMF<RuntimeException> ex, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LTriPredicate.throwIfNot(with1, with2, get(), predicate, ex, message, messageParams);
		return self();
	}

	default @Nonnull <V1, V2> SELF mustNotWith(@Nonnull LTriPredicate<? super V1, ? super V2, ? super T> predicate, V1 with1, V2 with2, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LTriPredicate.throwIf(with1, with2, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull <V1, V2> SELF mustNotWith$(@Nonnull LTriPredicate<? super V1, ? super V2, ? super T> predicate, V1 with1, V2 with2, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LTriPredicate.throwIf(with1, with2, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull <V1, V2> SELF mustNotWith$$(@Nonnull LTriPredicate<? super V1, ? super V2, ? super T> predicate, V1 with1, V2 with2, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LTriPredicate.throwIf(with1, with2, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S_S_2, checkTraitType(), checkTraitName(), message, with1, with2, get());
		return self();
	}

	default @Nonnull <V1, V2> SELF mustNotWith(@Nonnull LTriPredicate<? super V1, ? super V2, ? super T> predicate, V1 with1, V2 with2, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LTriPredicate.throwIf(with1, with2, get(), predicate, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull <V1, V2> SELF mustXNotWith(@Nonnull LTriPredicate<? super V1, ? super V2, ? super T> predicate, V1 with1, V2 with2, @Nonnull ExMF<RuntimeException> ex, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LTriPredicate.throwIf(with1, with2, get(), predicate, ex, message, messageParams);
		return self();
	}

	default @Nonnull <V1, V2> SELF mustNotWith(V1 with1, V2 with2, @Nonnull LTriPredicate<? super V1, ? super V2, ? super T> predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LTriPredicate.throwIf(with1, with2, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull <V1, V2> SELF mustNotWith$(V1 with1, V2 with2, @Nonnull LTriPredicate<? super V1, ? super V2, ? super T> predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LTriPredicate.throwIf(with1, with2, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull <V1, V2> SELF mustNotWith$$(V1 with1, V2 with2, @Nonnull LTriPredicate<? super V1, ? super V2, ? super T> predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LTriPredicate.throwIf(with1, with2, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S_S_2, checkTraitType(), checkTraitName(), message, with1, with2, get());
		return self();
	}

	default @Nonnull <V1, V2> SELF mustNotWith(V1 with1, V2 with2, @Nonnull LTriPredicate<? super V1, ? super V2, ? super T> predicate, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LTriPredicate.throwIf(with1, with2, get(), predicate, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull <V1, V2> SELF mustXNotWith(V1 with1, V2 with2, @Nonnull LTriPredicate<? super V1, ? super V2, ? super T> predicate, @Nonnull ExMF<RuntimeException> ex, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LTriPredicate.throwIf(with1, with2, get(), predicate, ex, message, messageParams);
		return self();
	}

	default @Nonnull SELF uniMustWith(@Nonnull LBiPredicate<? super T, ? super T> predicate, T with, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LBiPredicate.throwIfNot(with, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull SELF uniMustWith$(@Nonnull LBiPredicate<? super T, ? super T> predicate, T with, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LBiPredicate.throwIfNot(with, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull SELF uniMustWith$$(@Nonnull LBiPredicate<? super T, ? super T> predicate, T with, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LBiPredicate.throwIfNot(with, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S_S_1, checkTraitType(), checkTraitName(), message, with, get());
		return self();
	}

	default @Nonnull SELF uniMustWith(@Nonnull LBiPredicate<? super T, ? super T> predicate, T with, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LBiPredicate.throwIfNot(with, get(), predicate, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull SELF uniMustWith(@Nonnull LBiPredicate<? super T, ? super T> predicate, T with, @Nonnull ExMF<RuntimeException> ex, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LBiPredicate.throwIfNot(with, get(), predicate, ex, message, messageParams);
		return self();
	}

	default @Nonnull SELF uniMustWith(T with, @Nonnull LBiPredicate<? super T, ? super T> predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LBiPredicate.throwIfNot(with, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull SELF uniMustWith$(T with, @Nonnull LBiPredicate<? super T, ? super T> predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LBiPredicate.throwIfNot(with, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull SELF uniMustWith$$(T with, @Nonnull LBiPredicate<? super T, ? super T> predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LBiPredicate.throwIfNot(with, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S_S_1, checkTraitType(), checkTraitName(), message, with, get());
		return self();
	}

	default @Nonnull SELF uniMustWith(T with, @Nonnull LBiPredicate<? super T, ? super T> predicate, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LBiPredicate.throwIfNot(with, get(), predicate, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull SELF uniMustWith(T with, @Nonnull LBiPredicate<? super T, ? super T> predicate, @Nonnull ExMF<RuntimeException> ex, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LBiPredicate.throwIfNot(with, get(), predicate, ex, message, messageParams);
		return self();
	}

	default @Nonnull SELF uniMustNotWith(@Nonnull LBiPredicate<? super T, ? super T> predicate, T with, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LBiPredicate.throwIf(with, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull SELF uniMustNotWith$(@Nonnull LBiPredicate<? super T, ? super T> predicate, T with, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LBiPredicate.throwIf(with, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull SELF uniMustNotWith$$(@Nonnull LBiPredicate<? super T, ? super T> predicate, T with, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LBiPredicate.throwIf(with, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S_S_1, checkTraitType(), checkTraitName(), message, with, get());
		return self();
	}

	default @Nonnull SELF uniMustNotWith(@Nonnull LBiPredicate<? super T, ? super T> predicate, T with, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LBiPredicate.throwIf(with, get(), predicate, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull SELF uniMustNotWith(@Nonnull LBiPredicate<? super T, ? super T> predicate, T with, @Nonnull ExMF<RuntimeException> ex, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LBiPredicate.throwIf(with, get(), predicate, ex, message, messageParams);
		return self();
	}

	default @Nonnull SELF uniMustNotWith(T with, @Nonnull LBiPredicate<? super T, ? super T> predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LBiPredicate.throwIf(with, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull SELF uniMustNotWith$(T with, @Nonnull LBiPredicate<? super T, ? super T> predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LBiPredicate.throwIf(with, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull SELF uniMustNotWith$$(T with, @Nonnull LBiPredicate<? super T, ? super T> predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LBiPredicate.throwIf(with, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S_S_1, checkTraitType(), checkTraitName(), message, with, get());
		return self();
	}

	default @Nonnull SELF uniMustNotWith(T with, @Nonnull LBiPredicate<? super T, ? super T> predicate, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LBiPredicate.throwIf(with, get(), predicate, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull SELF uniMustNotWith(T with, @Nonnull LBiPredicate<? super T, ? super T> predicate, @Nonnull ExMF<RuntimeException> ex, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LBiPredicate.throwIf(with, get(), predicate, ex, message, messageParams);
		return self();
	}

	default @Nonnull SELF checkWhen(@Nonnull LPredicate<T> pred, @Nonnull LConsumer<SELF> conditionalChecks) {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(conditionalChecks, "conditionalChecks");
		if (pred.test(get())) {
			conditionalChecks.accept(self());
		};
		return self();
	}

	default @Nonnull SELF checkWhenNot(@Nonnull LPredicate<T> pred, @Nonnull LConsumer<SELF> conditionalChecks) {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(conditionalChecks, "conditionalChecks");
		if (!pred.test(get())) {
			conditionalChecks.accept(self());
		};
		return self();
	}

	default @Nonnull <T2> SELF checkWhen(@Nonnull LBiPredicate<T, T2> pred, T2 a2, @Nonnull LConsumer<SELF> conditionalChecks) {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(conditionalChecks, "conditionalChecks");
		if (pred.test(get(), a2)) {
			conditionalChecks.accept(self());
		};
		return self();
	}

	default @Nonnull <T2> SELF checkWhenNot(@Nonnull LBiPredicate<T, T2> pred, T2 a2, @Nonnull LConsumer<SELF> conditionalChecks) {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(conditionalChecks, "conditionalChecks");
		if (!pred.test(get(), a2)) {
			conditionalChecks.accept(self());
		};
		return self();
	}

	default @Nonnull <T2, T3> SELF checkWhen(@Nonnull LTriPredicate<T, T2, T3> pred, T2 a2, T3 a3, @Nonnull LConsumer<SELF> conditionalChecks) {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(conditionalChecks, "conditionalChecks");
		if (pred.test(get(), a2, a3)) {
			conditionalChecks.accept(self());
		};
		return self();
	}

	default @Nonnull <T2, T3> SELF checkWhenNot(@Nonnull LTriPredicate<T, T2, T3> pred, T2 a2, T3 a3, @Nonnull LConsumer<SELF> conditionalChecks) {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(conditionalChecks, "conditionalChecks");
		if (!pred.test(get(), a2, a3)) {
			conditionalChecks.accept(self());
		};
		return self();
	}

	// </editor-fold>

	default @Nonnull T nonnull() {
		must(Be::notNull, "Value cannot be null!");
		return value();
	}

	default @Nonnull T nullable() {
		must(Be::notNull, "Value cannot be null!");
		return value();
	}

	default @Nonnull SELF checkBool(@Nonnull LPredicate<T> func, LConsumer<Checks.CheckBool> checks) {
		Null.nonNullArg(func, "func");
		Null.nonNullArg(checks, "checks");
		checks.accept(Checks.check(func.test(get()), checkTraitFactory()));
		return self();
	}

	default @Nonnull SELF checkBool(@Nonnull LPredicate<T> func, @Nullable String name, LConsumer<Checks.CheckBool> checks) {
		Null.nonNullArg(func, "func");
		Null.nonNullArg(checks, "checks");
		checks.accept(Checks.check(func.test(get()), name, checkTraitFactory()));
		return self();
	}

	default @Nonnull SELF checkByte(@Nonnull LToByteFunction<T> func, LConsumer<Checks.CheckByte> checks) {
		Null.nonNullArg(func, "func");
		Null.nonNullArg(checks, "checks");
		checks.accept(Checks.check(func.applyAsByte(get()), checkTraitFactory()));
		return self();
	}

	default @Nonnull SELF checkByte(@Nonnull LToByteFunction<T> func, @Nullable String name, LConsumer<Checks.CheckByte> checks) {
		Null.nonNullArg(func, "func");
		Null.nonNullArg(checks, "checks");
		checks.accept(Checks.check(func.applyAsByte(get()), name, checkTraitFactory()));
		return self();
	}

	default @Nonnull SELF checkDbl(@Nonnull LToDblFunction<T> func, LConsumer<Checks.CheckDbl> checks) {
		Null.nonNullArg(func, "func");
		Null.nonNullArg(checks, "checks");
		checks.accept(Checks.check(func.applyAsDbl(get()), checkTraitFactory()));
		return self();
	}

	default @Nonnull SELF checkDbl(@Nonnull LToDblFunction<T> func, @Nullable String name, LConsumer<Checks.CheckDbl> checks) {
		Null.nonNullArg(func, "func");
		Null.nonNullArg(checks, "checks");
		checks.accept(Checks.check(func.applyAsDbl(get()), name, checkTraitFactory()));
		return self();
	}

	default @Nonnull SELF checkChar(@Nonnull LToCharFunction<T> func, LConsumer<Checks.CheckChar> checks) {
		Null.nonNullArg(func, "func");
		Null.nonNullArg(checks, "checks");
		checks.accept(Checks.check(func.applyAsChar(get()), checkTraitFactory()));
		return self();
	}

	default @Nonnull SELF checkChar(@Nonnull LToCharFunction<T> func, @Nullable String name, LConsumer<Checks.CheckChar> checks) {
		Null.nonNullArg(func, "func");
		Null.nonNullArg(checks, "checks");
		checks.accept(Checks.check(func.applyAsChar(get()), name, checkTraitFactory()));
		return self();
	}

	default @Nonnull SELF checkSrt(@Nonnull LToSrtFunction<T> func, LConsumer<Checks.CheckSrt> checks) {
		Null.nonNullArg(func, "func");
		Null.nonNullArg(checks, "checks");
		checks.accept(Checks.check(func.applyAsSrt(get()), checkTraitFactory()));
		return self();
	}

	default @Nonnull SELF checkSrt(@Nonnull LToSrtFunction<T> func, @Nullable String name, LConsumer<Checks.CheckSrt> checks) {
		Null.nonNullArg(func, "func");
		Null.nonNullArg(checks, "checks");
		checks.accept(Checks.check(func.applyAsSrt(get()), name, checkTraitFactory()));
		return self();
	}

	default @Nonnull SELF checkFlt(@Nonnull LToFltFunction<T> func, LConsumer<Checks.CheckFlt> checks) {
		Null.nonNullArg(func, "func");
		Null.nonNullArg(checks, "checks");
		checks.accept(Checks.check(func.applyAsFlt(get()), checkTraitFactory()));
		return self();
	}

	default @Nonnull SELF checkFlt(@Nonnull LToFltFunction<T> func, @Nullable String name, LConsumer<Checks.CheckFlt> checks) {
		Null.nonNullArg(func, "func");
		Null.nonNullArg(checks, "checks");
		checks.accept(Checks.check(func.applyAsFlt(get()), name, checkTraitFactory()));
		return self();
	}

	default @Nonnull SELF checkInt(@Nonnull LToIntFunction<T> func, LConsumer<Checks.CheckInt> checks) {
		Null.nonNullArg(func, "func");
		Null.nonNullArg(checks, "checks");
		checks.accept(Checks.check(func.applyAsInt(get()), checkTraitFactory()));
		return self();
	}

	default @Nonnull SELF checkInt(@Nonnull LToIntFunction<T> func, @Nullable String name, LConsumer<Checks.CheckInt> checks) {
		Null.nonNullArg(func, "func");
		Null.nonNullArg(checks, "checks");
		checks.accept(Checks.check(func.applyAsInt(get()), name, checkTraitFactory()));
		return self();
	}

	default @Nonnull SELF checkLong(@Nonnull LToLongFunction<T> func, LConsumer<Checks.CheckLong> checks) {
		Null.nonNullArg(func, "func");
		Null.nonNullArg(checks, "checks");
		checks.accept(Checks.check(func.applyAsLong(get()), checkTraitFactory()));
		return self();
	}

	default @Nonnull SELF checkLong(@Nonnull LToLongFunction<T> func, @Nullable String name, LConsumer<Checks.CheckLong> checks) {
		Null.nonNullArg(func, "func");
		Null.nonNullArg(checks, "checks");
		checks.accept(Checks.check(func.applyAsLong(get()), name, checkTraitFactory()));
		return self();
	}

	default @Nonnull <R> SELF check(@Nonnull LFunction<T, R> func, LConsumer<Checks.Check<R>> checks) {
		Null.nonNullArg(func, "func");
		Null.nonNullArg(checks, "checks");
		checks.accept(Checks.check(func.apply(get()), checkTraitFactory()));
		return self();
	}

	default @Nonnull <R> SELF check(@Nonnull LFunction<T, R> func, @Nullable String name, LConsumer<Checks.Check<R>> checks) {
		Null.nonNullArg(func, "func");
		Null.nonNullArg(checks, "checks");
		checks.accept(Checks.check(func.apply(get()), name, checkTraitFactory()));
		return self();
	}

	default @Nonnull SELF fails(@Nonnull String newMessage) {
		must(LPredicate::alwaysFalse, newMessage);
		return self();
	}

	default @Nonnull SELF fails$(@Nonnull String newMessage) {
		must$(LPredicate::alwaysFalse, newMessage);
		return self();
	}

	default @Nonnull SELF fails(@Nonnull String newMessage, @Nullable Object... messageParams) {
		must(LPredicate::alwaysFalse, newMessage, messageParams);
		return self();
	}

}
