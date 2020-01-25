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

	default @Nonnull SELF must(@Nonnull LLongPredicate pred, @Nonnull String message) {
		Null.nonNullArg(pred, "pred");
		LLongPredicate.throwIfNot(get(), pred, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull SELF must$(@Nonnull LLongPredicate pred, @Nonnull String message) {
		Null.nonNullArg(pred, "pred");
		LLongPredicate.throwIfNot(get(), pred, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull SELF must(@Nonnull LLongPredicate pred, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(pred, "pred");
		LLongPredicate.throwIfNot(get(), pred, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull SELF mustNot(@Nonnull LLongPredicate pred, @Nonnull String message) {
		Null.nonNullArg(pred, "pred");
		LLongPredicate.throwIf(get(), pred, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull SELF mustNot$(@Nonnull LLongPredicate pred, @Nonnull String message) {
		Null.nonNullArg(pred, "pred");
		LLongPredicate.throwIf(get(), pred, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull SELF mustNot(@Nonnull LLongPredicate pred, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(pred, "pred");
		LLongPredicate.throwIf(get(), pred, checkTraitFactory(), message, messageParams);
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

	default @Nonnull SELF must(@Nonnull LBiLongPredicate pred, long a2, @Nonnull String message) {
		Null.nonNullArg(pred, "pred");
		LBiLongPredicate.throwIfNot(get(), a2, pred, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull SELF must$(@Nonnull LBiLongPredicate pred, long a2, @Nonnull String message) {
		Null.nonNullArg(pred, "pred");
		LBiLongPredicate.throwIfNot(get(), a2, pred, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull SELF must$$(@Nonnull LBiLongPredicate pred, long a2, @Nonnull String message) {
		Null.nonNullArg(pred, "pred");
		LBiLongPredicate.throwIfNot(get(), a2, pred, checkTraitFactory(), MESSAGE_S_S_S_S_1, checkTraitType(), checkTraitName(), message, a2, get());
		return self();
	}

	default @Nonnull SELF must(@Nonnull LBiLongPredicate pred, long a2, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(pred, "pred");
		LBiLongPredicate.throwIfNot(get(), a2, pred, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull SELF mustNot(@Nonnull LBiLongPredicate pred, long a2, @Nonnull String message) {
		Null.nonNullArg(pred, "pred");
		LBiLongPredicate.throwIf(get(), a2, pred, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull SELF mustNot$(@Nonnull LBiLongPredicate pred, long a2, @Nonnull String message) {
		Null.nonNullArg(pred, "pred");
		LBiLongPredicate.throwIf(get(), a2, pred, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull SELF mustNot$$(@Nonnull LBiLongPredicate pred, long a2, @Nonnull String message) {
		Null.nonNullArg(pred, "pred");
		LBiLongPredicate.throwIf(get(), a2, pred, checkTraitFactory(), MESSAGE_S_S_S_S_1, checkTraitType(), checkTraitName(), message, a2, get());
		return self();
	}

	default @Nonnull SELF mustNot(@Nonnull LBiLongPredicate pred, long a2, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(pred, "pred");
		LBiLongPredicate.throwIf(get(), a2, pred, checkTraitFactory(), message, messageParams);
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

	default @Nonnull SELF must(@Nonnull LTriLongPredicate pred, long a2, long a3, @Nonnull String message) {
		Null.nonNullArg(pred, "pred");
		LTriLongPredicate.throwIfNot(get(), a2, a3, pred, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull SELF must$(@Nonnull LTriLongPredicate pred, long a2, long a3, @Nonnull String message) {
		Null.nonNullArg(pred, "pred");
		LTriLongPredicate.throwIfNot(get(), a2, a3, pred, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull SELF must$$(@Nonnull LTriLongPredicate pred, long a2, long a3, @Nonnull String message) {
		Null.nonNullArg(pred, "pred");
		LTriLongPredicate.throwIfNot(get(), a2, a3, pred, checkTraitFactory(), MESSAGE_S_S_S_S_2, checkTraitType(), checkTraitName(), message, a2, a3, get());
		return self();
	}

	default @Nonnull SELF must(@Nonnull LTriLongPredicate pred, long a2, long a3, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(pred, "pred");
		LTriLongPredicate.throwIfNot(get(), a2, a3, pred, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull SELF mustNot(@Nonnull LTriLongPredicate pred, long a2, long a3, @Nonnull String message) {
		Null.nonNullArg(pred, "pred");
		LTriLongPredicate.throwIf(get(), a2, a3, pred, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull SELF mustNot$(@Nonnull LTriLongPredicate pred, long a2, long a3, @Nonnull String message) {
		Null.nonNullArg(pred, "pred");
		LTriLongPredicate.throwIf(get(), a2, a3, pred, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull SELF mustNot$$(@Nonnull LTriLongPredicate pred, long a2, long a3, @Nonnull String message) {
		Null.nonNullArg(pred, "pred");
		LTriLongPredicate.throwIf(get(), a2, a3, pred, checkTraitFactory(), MESSAGE_S_S_S_S_2, checkTraitType(), checkTraitName(), message, a2, a3, get());
		return self();
	}

	default @Nonnull SELF mustNot(@Nonnull LTriLongPredicate pred, long a2, long a3, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(pred, "pred");
		LTriLongPredicate.throwIf(get(), a2, a3, pred, checkTraitFactory(), message, messageParams);
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

	default @Nonnull <K> SELF mustWith(K a1, @Nonnull LObjLongPredicate<K> pred, @Nonnull String message) {
		Null.nonNullArg(pred, "pred");
		LObjLongPredicate.throwIfNot(a1, get(), pred, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull <K> SELF mustWith$(K a1, @Nonnull LObjLongPredicate<K> pred, @Nonnull String message) {
		Null.nonNullArg(pred, "pred");
		LObjLongPredicate.throwIfNot(a1, get(), pred, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull <K> SELF mustWith$$(K a1, @Nonnull LObjLongPredicate<K> pred, @Nonnull String message) {
		Null.nonNullArg(pred, "pred");
		LObjLongPredicate.throwIfNot(a1, get(), pred, checkTraitFactory(), MESSAGE_S_S_S_S_1, checkTraitType(), checkTraitName(), message, a1, get());
		return self();
	}

	default @Nonnull <K> SELF mustWith(K a1, @Nonnull LObjLongPredicate<K> pred, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(pred, "pred");
		LObjLongPredicate.throwIfNot(a1, get(), pred, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull <K> SELF mustNotWith(K a1, @Nonnull LObjLongPredicate<K> pred, @Nonnull String message) {
		Null.nonNullArg(pred, "pred");
		LObjLongPredicate.throwIf(a1, get(), pred, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull <K> SELF mustNotWith$(K a1, @Nonnull LObjLongPredicate<K> pred, @Nonnull String message) {
		Null.nonNullArg(pred, "pred");
		LObjLongPredicate.throwIf(a1, get(), pred, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull <K> SELF mustNotWith$$(K a1, @Nonnull LObjLongPredicate<K> pred, @Nonnull String message) {
		Null.nonNullArg(pred, "pred");
		LObjLongPredicate.throwIf(a1, get(), pred, checkTraitFactory(), MESSAGE_S_S_S_S_1, checkTraitType(), checkTraitName(), message, a1, get());
		return self();
	}

	default @Nonnull <K> SELF mustNotWith(K a1, @Nonnull LObjLongPredicate<K> pred, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(pred, "pred");
		LObjLongPredicate.throwIf(a1, get(), pred, checkTraitFactory(), message, messageParams);
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
