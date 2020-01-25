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

public interface CheckTrait<T, SELF extends CheckTrait<T, SELF>> extends FluentTrait<SELF>, aValue<a<T>>, LSingle<T>, ValueTrait<T, SELF> {

	public static final String MESSAGE_S_S_S = "%s [%s]: %s";
	public static final String MESSAGE_S_S_S_S = "%s [%s]: %s [Value: `%s`]";
	public static final String MESSAGE_S_S_S_S_1 = "%s [%s]: %s [Param: `%s`; Value: `%s`]";
	public static final String MESSAGE_S_S_S_S_2 = "%s [%s]: %s [Params: `%s`, `%s`; Value: `%s`]";

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

	default @Nonnull SELF must(@Nonnull LPredicate<T> pred, @Nonnull String message) {
		Null.nonNullArg(pred, "pred");
		LPredicate.throwIfNot(get(), pred, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull SELF must$(@Nonnull LPredicate<T> pred, @Nonnull String message) {
		Null.nonNullArg(pred, "pred");
		LPredicate.throwIfNot(get(), pred, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull SELF must(@Nonnull LPredicate<T> pred, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(pred, "pred");
		LPredicate.throwIfNot(get(), pred, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull SELF mustNot(@Nonnull LPredicate<T> pred, @Nonnull String message) {
		Null.nonNullArg(pred, "pred");
		LPredicate.throwIf(get(), pred, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull SELF mustNot$(@Nonnull LPredicate<T> pred, @Nonnull String message) {
		Null.nonNullArg(pred, "pred");
		LPredicate.throwIf(get(), pred, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull SELF mustNot(@Nonnull LPredicate<T> pred, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(pred, "pred");
		LPredicate.throwIf(get(), pred, checkTraitFactory(), message, messageParams);
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

	default @Nonnull <T2> SELF must(@Nonnull LBiPredicate<T, T2> pred, T2 a2, @Nonnull String message) {
		Null.nonNullArg(pred, "pred");
		LBiPredicate.throwIfNot(get(), a2, pred, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull <T2> SELF must$(@Nonnull LBiPredicate<T, T2> pred, T2 a2, @Nonnull String message) {
		Null.nonNullArg(pred, "pred");
		LBiPredicate.throwIfNot(get(), a2, pred, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull <T2> SELF must$$(@Nonnull LBiPredicate<T, T2> pred, T2 a2, @Nonnull String message) {
		Null.nonNullArg(pred, "pred");
		LBiPredicate.throwIfNot(get(), a2, pred, checkTraitFactory(), MESSAGE_S_S_S_S_1, checkTraitType(), checkTraitName(), message, a2, get());
		return self();
	}

	default @Nonnull <T2> SELF must(@Nonnull LBiPredicate<T, T2> pred, T2 a2, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(pred, "pred");
		LBiPredicate.throwIfNot(get(), a2, pred, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull <T2> SELF mustNot(@Nonnull LBiPredicate<T, T2> pred, T2 a2, @Nonnull String message) {
		Null.nonNullArg(pred, "pred");
		LBiPredicate.throwIf(get(), a2, pred, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull <T2> SELF mustNot$(@Nonnull LBiPredicate<T, T2> pred, T2 a2, @Nonnull String message) {
		Null.nonNullArg(pred, "pred");
		LBiPredicate.throwIf(get(), a2, pred, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull <T2> SELF mustNot$$(@Nonnull LBiPredicate<T, T2> pred, T2 a2, @Nonnull String message) {
		Null.nonNullArg(pred, "pred");
		LBiPredicate.throwIf(get(), a2, pred, checkTraitFactory(), MESSAGE_S_S_S_S_1, checkTraitType(), checkTraitName(), message, a2, get());
		return self();
	}

	default @Nonnull <T2> SELF mustNot(@Nonnull LBiPredicate<T, T2> pred, T2 a2, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(pred, "pred");
		LBiPredicate.throwIf(get(), a2, pred, checkTraitFactory(), message, messageParams);
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

	default @Nonnull <T2, T3> SELF must(@Nonnull LTriPredicate<T, T2, T3> pred, T2 a2, T3 a3, @Nonnull String message) {
		Null.nonNullArg(pred, "pred");
		LTriPredicate.throwIfNot(get(), a2, a3, pred, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull <T2, T3> SELF must$(@Nonnull LTriPredicate<T, T2, T3> pred, T2 a2, T3 a3, @Nonnull String message) {
		Null.nonNullArg(pred, "pred");
		LTriPredicate.throwIfNot(get(), a2, a3, pred, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull <T2, T3> SELF must$$(@Nonnull LTriPredicate<T, T2, T3> pred, T2 a2, T3 a3, @Nonnull String message) {
		Null.nonNullArg(pred, "pred");
		LTriPredicate.throwIfNot(get(), a2, a3, pred, checkTraitFactory(), MESSAGE_S_S_S_S_2, checkTraitType(), checkTraitName(), message, a2, a3, get());
		return self();
	}

	default @Nonnull <T2, T3> SELF must(@Nonnull LTriPredicate<T, T2, T3> pred, T2 a2, T3 a3, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(pred, "pred");
		LTriPredicate.throwIfNot(get(), a2, a3, pred, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull <T2, T3> SELF mustNot(@Nonnull LTriPredicate<T, T2, T3> pred, T2 a2, T3 a3, @Nonnull String message) {
		Null.nonNullArg(pred, "pred");
		LTriPredicate.throwIf(get(), a2, a3, pred, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull <T2, T3> SELF mustNot$(@Nonnull LTriPredicate<T, T2, T3> pred, T2 a2, T3 a3, @Nonnull String message) {
		Null.nonNullArg(pred, "pred");
		LTriPredicate.throwIf(get(), a2, a3, pred, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull <T2, T3> SELF mustNot$$(@Nonnull LTriPredicate<T, T2, T3> pred, T2 a2, T3 a3, @Nonnull String message) {
		Null.nonNullArg(pred, "pred");
		LTriPredicate.throwIf(get(), a2, a3, pred, checkTraitFactory(), MESSAGE_S_S_S_S_2, checkTraitType(), checkTraitName(), message, a2, a3, get());
		return self();
	}

	default @Nonnull <T2, T3> SELF mustNot(@Nonnull LTriPredicate<T, T2, T3> pred, T2 a2, T3 a3, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(pred, "pred");
		LTriPredicate.throwIf(get(), a2, a3, pred, checkTraitFactory(), message, messageParams);
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

	default @Nonnull <K> SELF mustWith(K a1, @Nonnull LBiPredicate<K, T> pred, @Nonnull String message) {
		Null.nonNullArg(pred, "pred");
		LBiPredicate.throwIfNot(a1, get(), pred, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull <K> SELF mustWith$(K a1, @Nonnull LBiPredicate<K, T> pred, @Nonnull String message) {
		Null.nonNullArg(pred, "pred");
		LBiPredicate.throwIfNot(a1, get(), pred, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull <K> SELF mustWith$$(K a1, @Nonnull LBiPredicate<K, T> pred, @Nonnull String message) {
		Null.nonNullArg(pred, "pred");
		LBiPredicate.throwIfNot(a1, get(), pred, checkTraitFactory(), MESSAGE_S_S_S_S_1, checkTraitType(), checkTraitName(), message, a1, get());
		return self();
	}

	default @Nonnull <K> SELF mustWith(K a1, @Nonnull LBiPredicate<K, T> pred, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(pred, "pred");
		LBiPredicate.throwIfNot(a1, get(), pred, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull <K> SELF mustNotWith(K a1, @Nonnull LBiPredicate<K, T> pred, @Nonnull String message) {
		Null.nonNullArg(pred, "pred");
		LBiPredicate.throwIf(a1, get(), pred, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull <K> SELF mustNotWith$(K a1, @Nonnull LBiPredicate<K, T> pred, @Nonnull String message) {
		Null.nonNullArg(pred, "pred");
		LBiPredicate.throwIf(a1, get(), pred, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull <K> SELF mustNotWith$$(K a1, @Nonnull LBiPredicate<K, T> pred, @Nonnull String message) {
		Null.nonNullArg(pred, "pred");
		LBiPredicate.throwIf(a1, get(), pred, checkTraitFactory(), MESSAGE_S_S_S_S_1, checkTraitType(), checkTraitName(), message, a1, get());
		return self();
	}

	default @Nonnull <K> SELF mustNotWith(K a1, @Nonnull LBiPredicate<K, T> pred, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(pred, "pred");
		LBiPredicate.throwIf(a1, get(), pred, checkTraitFactory(), message, messageParams);
		return self();
	}

	// </editor-fold>

	default @Nonnull <R> Checks.Check<R> mustBeInstanceOf(@Nonnull Class<R> clazz, @Nonnull String message) {
		Null.nonNullArg(clazz, "clazz");
		return (Checks.Check) must(Be::instanceOf, clazz, message);
	}

	default @Nonnull T nonnull() {
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
