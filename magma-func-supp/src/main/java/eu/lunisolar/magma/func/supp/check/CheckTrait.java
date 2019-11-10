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
import eu.lunisolar.magma.func.supp.memento.*; // NOSONAR
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

@ThreadSafe
public interface CheckTrait<T, SELF extends CheckTrait<T, SELF>> extends Fluent<SELF>, aValue<a<T>>, LSingle<T> {

	public static final String MESSAGE_S_S_S = "%s [%s]: %s.";
	public static final String MESSAGE_S_S_S_S = "%s [%s]: %s. Value: %s";

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
		return "Value";
	}

	@Nonnull
	default ExMF<RuntimeException> checkTraitFactory() {
		return X::value;
	}

	default SELF mustNot(@Nonnull LPredicate<T> pred, @Nonnull String newMessage) {
		Null.nonNullArg(pred, "pred");
		LPredicate.throwIf(get(), pred, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), newMessage);
		return self();
	}

	default SELF mustNot$(@Nonnull LPredicate<T> pred, @Nonnull String newMessage) {
		Null.nonNullArg(pred, "pred");
		LPredicate.throwIf(get(), pred, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), newMessage, get());
		return self();
	}

	default SELF mustNot(@Nonnull LPredicate<T> pred, @Nonnull String newMessage, @Nullable Object... messageParams) {
		Null.nonNullArg(pred, "pred");
		LPredicate.throwIf(get(), pred, checkTraitFactory(), newMessage, messageParams);
		return self();
	}

	default SELF must(@Nonnull LPredicate<T> pred, @Nonnull String newMessage) {
		Null.nonNullArg(pred, "pred");
		LPredicate.throwIfNot(get(), pred, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), newMessage);
		return self();
	}

	default SELF must$(@Nonnull LPredicate<T> pred, @Nonnull String newMessage) {
		Null.nonNullArg(pred, "pred");
		LPredicate.throwIfNot(get(), pred, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), newMessage, get());
		return self();
	}

	default SELF must(@Nonnull LPredicate<T> pred, @Nonnull String newMessage, @Nullable Object... messageParams) {
		Null.nonNullArg(pred, "pred");
		LPredicate.throwIfNot(get(), pred, checkTraitFactory(), newMessage, messageParams);
		return self();
	}

	default <T2> SELF mustNot(@Nonnull LBiPredicate<T, T2> pred, T2 a2, @Nonnull String newMessage) {
		Null.nonNullArg(pred, "pred");
		LBiPredicate.throwIf(get(), a2, pred, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), newMessage);
		return self();
	}

	default <T2> SELF mustNot$(@Nonnull LBiPredicate<T, T2> pred, T2 a2, @Nonnull String newMessage) {
		Null.nonNullArg(pred, "pred");
		LBiPredicate.throwIf(get(), a2, pred, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), newMessage, get());
		return self();
	}

	default <T2> SELF mustNot(@Nonnull LBiPredicate<T, T2> pred, T2 a2, @Nonnull String newMessage, @Nullable Object... messageParams) {
		Null.nonNullArg(pred, "pred");
		LBiPredicate.throwIf(get(), a2, pred, checkTraitFactory(), newMessage, messageParams);
		return self();
	}

	default <T2> SELF must(@Nonnull LBiPredicate<T, T2> pred, T2 a2, @Nonnull String newMessage) {
		Null.nonNullArg(pred, "pred");
		LBiPredicate.throwIfNot(get(), a2, pred, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), newMessage);
		return self();
	}

	default <T2> SELF must$(@Nonnull LBiPredicate<T, T2> pred, T2 a2, @Nonnull String newMessage) {
		Null.nonNullArg(pred, "pred");
		LBiPredicate.throwIfNot(get(), a2, pred, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), newMessage, get());
		return self();
	}

	default <T2> SELF must(@Nonnull LBiPredicate<T, T2> pred, T2 a2, @Nonnull String newMessage, @Nullable Object... messageParams) {
		Null.nonNullArg(pred, "pred");
		LBiPredicate.throwIfNot(get(), a2, pred, checkTraitFactory(), newMessage, messageParams);
		return self();
	}

	default <T2, T3> SELF mustNot(@Nonnull LTriPredicate<T, T2, T3> pred, T2 a2, T3 a3, @Nonnull String newMessage) {
		Null.nonNullArg(pred, "pred");
		LTriPredicate.throwIf(get(), a2, a3, pred, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), newMessage);
		return self();
	}

	default <T2, T3> SELF mustNot$(@Nonnull LTriPredicate<T, T2, T3> pred, T2 a2, T3 a3, @Nonnull String newMessage) {
		Null.nonNullArg(pred, "pred");
		LTriPredicate.throwIf(get(), a2, a3, pred, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), newMessage, get());
		return self();
	}

	default <T2, T3> SELF mustNot(@Nonnull LTriPredicate<T, T2, T3> pred, T2 a2, T3 a3, @Nonnull String newMessage, @Nullable Object... messageParams) {
		Null.nonNullArg(pred, "pred");
		LTriPredicate.throwIf(get(), a2, a3, pred, checkTraitFactory(), newMessage, messageParams);
		return self();
	}

	default <T2, T3> SELF must(@Nonnull LTriPredicate<T, T2, T3> pred, T2 a2, T3 a3, @Nonnull String newMessage) {
		Null.nonNullArg(pred, "pred");
		LTriPredicate.throwIfNot(get(), a2, a3, pred, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), newMessage);
		return self();
	}

	default <T2, T3> SELF must$(@Nonnull LTriPredicate<T, T2, T3> pred, T2 a2, T3 a3, @Nonnull String newMessage) {
		Null.nonNullArg(pred, "pred");
		LTriPredicate.throwIfNot(get(), a2, a3, pred, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), newMessage, get());
		return self();
	}

	default <T2, T3> SELF must(@Nonnull LTriPredicate<T, T2, T3> pred, T2 a2, T3 a3, @Nonnull String newMessage, @Nullable Object... messageParams) {
		Null.nonNullArg(pred, "pred");
		LTriPredicate.throwIfNot(get(), a2, a3, pred, checkTraitFactory(), newMessage, messageParams);
		return self();
	}

	default SELF fails(@Nonnull String newMessage) {
		must(LPredicate::alwaysFalse, newMessage);
		return self();
	}

	default SELF fails$(@Nonnull String newMessage) {
		must$(LPredicate::alwaysFalse, newMessage);
		return self();
	}

	default SELF fails(@Nonnull String newMessage, @Nullable Object... messageParams) {
		must(LPredicate::alwaysFalse, newMessage, messageParams);
		return self();
	}

}
