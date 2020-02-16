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

public interface CheckByteTrait<SELF extends CheckByteTrait<SELF>> extends FluentTrait<SELF>, aValue<aByte>, LByteSingle, ByteValueTrait<SELF> {

	byte get();

	default byte value() {
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

	default @Nonnull SELF must(@Nonnull LBytePredicate predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LBytePredicate.throwIfNot(get(), predicate, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull SELF must$(@Nonnull LBytePredicate predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LBytePredicate.throwIfNot(get(), predicate, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull SELF must(@Nonnull LBytePredicate predicate, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LBytePredicate.throwIfNot(get(), predicate, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull SELF mustNot(@Nonnull LBytePredicate predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LBytePredicate.throwIf(get(), predicate, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull SELF mustNot$(@Nonnull LBytePredicate predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LBytePredicate.throwIf(get(), predicate, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull SELF mustNot(@Nonnull LBytePredicate predicate, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LBytePredicate.throwIf(get(), predicate, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull SELF must(@Nonnull LBiBytePredicate predicate, byte a2, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LBiBytePredicate.throwIfNot(get(), a2, predicate, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull SELF must$(@Nonnull LBiBytePredicate predicate, byte a2, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LBiBytePredicate.throwIfNot(get(), a2, predicate, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull SELF must$$(@Nonnull LBiBytePredicate predicate, byte a2, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LBiBytePredicate.throwIfNot(get(), a2, predicate, checkTraitFactory(), MESSAGE_S_S_S_S_1, checkTraitType(), checkTraitName(), message, a2, get());
		return self();
	}

	default @Nonnull SELF must(@Nonnull LBiBytePredicate predicate, byte a2, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LBiBytePredicate.throwIfNot(get(), a2, predicate, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull SELF mustNot(@Nonnull LBiBytePredicate predicate, byte a2, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LBiBytePredicate.throwIf(get(), a2, predicate, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull SELF mustNot$(@Nonnull LBiBytePredicate predicate, byte a2, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LBiBytePredicate.throwIf(get(), a2, predicate, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull SELF mustNot$$(@Nonnull LBiBytePredicate predicate, byte a2, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LBiBytePredicate.throwIf(get(), a2, predicate, checkTraitFactory(), MESSAGE_S_S_S_S_1, checkTraitType(), checkTraitName(), message, a2, get());
		return self();
	}

	default @Nonnull SELF mustNot(@Nonnull LBiBytePredicate predicate, byte a2, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LBiBytePredicate.throwIf(get(), a2, predicate, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull SELF must(byte a2, @Nonnull LBiBytePredicate predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LBiBytePredicate.throwIfNot(get(), a2, predicate, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull SELF must$(byte a2, @Nonnull LBiBytePredicate predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LBiBytePredicate.throwIfNot(get(), a2, predicate, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull SELF must$$(byte a2, @Nonnull LBiBytePredicate predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LBiBytePredicate.throwIfNot(get(), a2, predicate, checkTraitFactory(), MESSAGE_S_S_S_S_1, checkTraitType(), checkTraitName(), message, a2, get());
		return self();
	}

	default @Nonnull SELF must(byte a2, @Nonnull LBiBytePredicate predicate, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LBiBytePredicate.throwIfNot(get(), a2, predicate, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull SELF mustNot(byte a2, @Nonnull LBiBytePredicate predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LBiBytePredicate.throwIf(get(), a2, predicate, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull SELF mustNot$(byte a2, @Nonnull LBiBytePredicate predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LBiBytePredicate.throwIf(get(), a2, predicate, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull SELF mustNot$$(byte a2, @Nonnull LBiBytePredicate predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LBiBytePredicate.throwIf(get(), a2, predicate, checkTraitFactory(), MESSAGE_S_S_S_S_1, checkTraitType(), checkTraitName(), message, a2, get());
		return self();
	}

	default @Nonnull SELF mustNot(byte a2, @Nonnull LBiBytePredicate predicate, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LBiBytePredicate.throwIf(get(), a2, predicate, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull SELF must(@Nonnull LTriBytePredicate predicate, byte a2, byte a3, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LTriBytePredicate.throwIfNot(get(), a2, a3, predicate, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull SELF must$(@Nonnull LTriBytePredicate predicate, byte a2, byte a3, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LTriBytePredicate.throwIfNot(get(), a2, a3, predicate, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull SELF must$$(@Nonnull LTriBytePredicate predicate, byte a2, byte a3, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LTriBytePredicate.throwIfNot(get(), a2, a3, predicate, checkTraitFactory(), MESSAGE_S_S_S_S_2, checkTraitType(), checkTraitName(), message, a2, a3, get());
		return self();
	}

	default @Nonnull SELF must(@Nonnull LTriBytePredicate predicate, byte a2, byte a3, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LTriBytePredicate.throwIfNot(get(), a2, a3, predicate, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull SELF mustNot(@Nonnull LTriBytePredicate predicate, byte a2, byte a3, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LTriBytePredicate.throwIf(get(), a2, a3, predicate, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull SELF mustNot$(@Nonnull LTriBytePredicate predicate, byte a2, byte a3, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LTriBytePredicate.throwIf(get(), a2, a3, predicate, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull SELF mustNot$$(@Nonnull LTriBytePredicate predicate, byte a2, byte a3, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LTriBytePredicate.throwIf(get(), a2, a3, predicate, checkTraitFactory(), MESSAGE_S_S_S_S_2, checkTraitType(), checkTraitName(), message, a2, a3, get());
		return self();
	}

	default @Nonnull SELF mustNot(@Nonnull LTriBytePredicate predicate, byte a2, byte a3, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LTriBytePredicate.throwIf(get(), a2, a3, predicate, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull SELF must(byte a2, byte a3, @Nonnull LTriBytePredicate predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LTriBytePredicate.throwIfNot(get(), a2, a3, predicate, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull SELF must$(byte a2, byte a3, @Nonnull LTriBytePredicate predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LTriBytePredicate.throwIfNot(get(), a2, a3, predicate, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull SELF must$$(byte a2, byte a3, @Nonnull LTriBytePredicate predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LTriBytePredicate.throwIfNot(get(), a2, a3, predicate, checkTraitFactory(), MESSAGE_S_S_S_S_2, checkTraitType(), checkTraitName(), message, a2, a3, get());
		return self();
	}

	default @Nonnull SELF must(byte a2, byte a3, @Nonnull LTriBytePredicate predicate, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LTriBytePredicate.throwIfNot(get(), a2, a3, predicate, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull SELF mustNot(byte a2, byte a3, @Nonnull LTriBytePredicate predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LTriBytePredicate.throwIf(get(), a2, a3, predicate, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull SELF mustNot$(byte a2, byte a3, @Nonnull LTriBytePredicate predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LTriBytePredicate.throwIf(get(), a2, a3, predicate, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull SELF mustNot$$(byte a2, byte a3, @Nonnull LTriBytePredicate predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LTriBytePredicate.throwIf(get(), a2, a3, predicate, checkTraitFactory(), MESSAGE_S_S_S_S_2, checkTraitType(), checkTraitName(), message, a2, a3, get());
		return self();
	}

	default @Nonnull SELF mustNot(byte a2, byte a3, @Nonnull LTriBytePredicate predicate, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LTriBytePredicate.throwIf(get(), a2, a3, predicate, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull SELF mustInt(@Nonnull LByteIntPredicate predicate, int v, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LByteIntPredicate.throwIfNot(get(), v, predicate, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull SELF mustInt$(@Nonnull LByteIntPredicate predicate, int v, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LByteIntPredicate.throwIfNot(get(), v, predicate, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull SELF mustInt$$(@Nonnull LByteIntPredicate predicate, int v, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LByteIntPredicate.throwIfNot(get(), v, predicate, checkTraitFactory(), MESSAGE_S_S_S_S_1, checkTraitType(), checkTraitName(), message, v, get());
		return self();
	}

	default @Nonnull SELF mustInt(@Nonnull LByteIntPredicate predicate, int v, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LByteIntPredicate.throwIfNot(get(), v, predicate, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull SELF mustNotInt(@Nonnull LByteIntPredicate predicate, int v, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LByteIntPredicate.throwIf(get(), v, predicate, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull SELF mustNotInt$(@Nonnull LByteIntPredicate predicate, int v, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LByteIntPredicate.throwIf(get(), v, predicate, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull SELF mustNotInt$$(@Nonnull LByteIntPredicate predicate, int v, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LByteIntPredicate.throwIf(get(), v, predicate, checkTraitFactory(), MESSAGE_S_S_S_S_1, checkTraitType(), checkTraitName(), message, v, get());
		return self();
	}

	default @Nonnull SELF mustNotInt(@Nonnull LByteIntPredicate predicate, int v, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LByteIntPredicate.throwIf(get(), v, predicate, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull SELF mustInt(int v, @Nonnull LByteIntPredicate predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LByteIntPredicate.throwIfNot(get(), v, predicate, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull SELF mustInt$(int v, @Nonnull LByteIntPredicate predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LByteIntPredicate.throwIfNot(get(), v, predicate, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull SELF mustInt$$(int v, @Nonnull LByteIntPredicate predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LByteIntPredicate.throwIfNot(get(), v, predicate, checkTraitFactory(), MESSAGE_S_S_S_S_1, checkTraitType(), checkTraitName(), message, v, get());
		return self();
	}

	default @Nonnull SELF mustInt(int v, @Nonnull LByteIntPredicate predicate, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LByteIntPredicate.throwIfNot(get(), v, predicate, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull SELF mustNotInt(int v, @Nonnull LByteIntPredicate predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LByteIntPredicate.throwIf(get(), v, predicate, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull SELF mustNotInt$(int v, @Nonnull LByteIntPredicate predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LByteIntPredicate.throwIf(get(), v, predicate, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull SELF mustNotInt$$(int v, @Nonnull LByteIntPredicate predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LByteIntPredicate.throwIf(get(), v, predicate, checkTraitFactory(), MESSAGE_S_S_S_S_1, checkTraitType(), checkTraitName(), message, v, get());
		return self();
	}

	default @Nonnull SELF mustNotInt(int v, @Nonnull LByteIntPredicate predicate, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LByteIntPredicate.throwIf(get(), v, predicate, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull <V> SELF must_(@Nonnull LObjBytePredicate.LByteObjPred<? super V> predicate, V v, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LObjBytePredicate.throwIfNot(v, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull <V> SELF must_$(@Nonnull LObjBytePredicate.LByteObjPred<? super V> predicate, V v, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LObjBytePredicate.throwIfNot(v, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull <V> SELF must_$$(@Nonnull LObjBytePredicate.LByteObjPred<? super V> predicate, V v, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LObjBytePredicate.throwIfNot(v, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S_S_1, checkTraitType(), checkTraitName(), message, v, get());
		return self();
	}

	default @Nonnull <V> SELF must_(@Nonnull LObjBytePredicate.LByteObjPred<? super V> predicate, V v, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LObjBytePredicate.throwIfNot(v, get(), predicate, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull <V> SELF mustNot_(@Nonnull LObjBytePredicate.LByteObjPred<? super V> predicate, V v, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LObjBytePredicate.throwIf(v, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull <V> SELF mustNot_$(@Nonnull LObjBytePredicate.LByteObjPred<? super V> predicate, V v, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LObjBytePredicate.throwIf(v, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull <V> SELF mustNot_$$(@Nonnull LObjBytePredicate.LByteObjPred<? super V> predicate, V v, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LObjBytePredicate.throwIf(v, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S_S_1, checkTraitType(), checkTraitName(), message, v, get());
		return self();
	}

	default @Nonnull <V> SELF mustNot_(@Nonnull LObjBytePredicate.LByteObjPred<? super V> predicate, V v, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LObjBytePredicate.throwIf(v, get(), predicate, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull <V> SELF must_(V v, @Nonnull LObjBytePredicate.LByteObjPred<? super V> predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LObjBytePredicate.throwIfNot(v, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull <V> SELF must_$(V v, @Nonnull LObjBytePredicate.LByteObjPred<? super V> predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LObjBytePredicate.throwIfNot(v, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull <V> SELF must_$$(V v, @Nonnull LObjBytePredicate.LByteObjPred<? super V> predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LObjBytePredicate.throwIfNot(v, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S_S_1, checkTraitType(), checkTraitName(), message, v, get());
		return self();
	}

	default @Nonnull <V> SELF must_(V v, @Nonnull LObjBytePredicate.LByteObjPred<? super V> predicate, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LObjBytePredicate.throwIfNot(v, get(), predicate, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull <V> SELF mustNot_(V v, @Nonnull LObjBytePredicate.LByteObjPred<? super V> predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LObjBytePredicate.throwIf(v, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull <V> SELF mustNot_$(V v, @Nonnull LObjBytePredicate.LByteObjPred<? super V> predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LObjBytePredicate.throwIf(v, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull <V> SELF mustNot_$$(V v, @Nonnull LObjBytePredicate.LByteObjPred<? super V> predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LObjBytePredicate.throwIf(v, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S_S_1, checkTraitType(), checkTraitName(), message, v, get());
		return self();
	}

	default @Nonnull <V> SELF mustNot_(V v, @Nonnull LObjBytePredicate.LByteObjPred<? super V> predicate, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LObjBytePredicate.throwIf(v, get(), predicate, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull <V1> SELF mustWithByte(@Nonnull LObjBytePredicate<? super V1> predicate, V1 with, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LObjBytePredicate.throwIfNot(with, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull <V1> SELF mustWithByte$(@Nonnull LObjBytePredicate<? super V1> predicate, V1 with, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LObjBytePredicate.throwIfNot(with, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull <V1> SELF mustWithByte$$(@Nonnull LObjBytePredicate<? super V1> predicate, V1 with, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LObjBytePredicate.throwIfNot(with, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S_S_1, checkTraitType(), checkTraitName(), message, with, get());
		return self();
	}

	default @Nonnull <V1> SELF mustWithByte(@Nonnull LObjBytePredicate<? super V1> predicate, V1 with, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LObjBytePredicate.throwIfNot(with, get(), predicate, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull <V1> SELF mustNotWithByte(@Nonnull LObjBytePredicate<? super V1> predicate, V1 with, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LObjBytePredicate.throwIf(with, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull <V1> SELF mustNotWithByte$(@Nonnull LObjBytePredicate<? super V1> predicate, V1 with, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LObjBytePredicate.throwIf(with, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull <V1> SELF mustNotWithByte$$(@Nonnull LObjBytePredicate<? super V1> predicate, V1 with, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LObjBytePredicate.throwIf(with, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S_S_1, checkTraitType(), checkTraitName(), message, with, get());
		return self();
	}

	default @Nonnull <V1> SELF mustNotWithByte(@Nonnull LObjBytePredicate<? super V1> predicate, V1 with, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LObjBytePredicate.throwIf(with, get(), predicate, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull <V1> SELF mustWithByte(V1 with, @Nonnull LObjBytePredicate<? super V1> predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LObjBytePredicate.throwIfNot(with, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull <V1> SELF mustWithByte$(V1 with, @Nonnull LObjBytePredicate<? super V1> predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LObjBytePredicate.throwIfNot(with, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull <V1> SELF mustWithByte$$(V1 with, @Nonnull LObjBytePredicate<? super V1> predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LObjBytePredicate.throwIfNot(with, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S_S_1, checkTraitType(), checkTraitName(), message, with, get());
		return self();
	}

	default @Nonnull <V1> SELF mustWithByte(V1 with, @Nonnull LObjBytePredicate<? super V1> predicate, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LObjBytePredicate.throwIfNot(with, get(), predicate, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull <V1> SELF mustNotWithByte(V1 with, @Nonnull LObjBytePredicate<? super V1> predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LObjBytePredicate.throwIf(with, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S, checkTraitType(), checkTraitName(), message);
		return self();
	}

	default @Nonnull <V1> SELF mustNotWithByte$(V1 with, @Nonnull LObjBytePredicate<? super V1> predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LObjBytePredicate.throwIf(with, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S_S, checkTraitType(), checkTraitName(), message, get());
		return self();
	}

	default @Nonnull <V1> SELF mustNotWithByte$$(V1 with, @Nonnull LObjBytePredicate<? super V1> predicate, @Nonnull String message) {
		Null.nonNullArg(predicate, "predicate");
		LObjBytePredicate.throwIf(with, get(), predicate, checkTraitFactory(), MESSAGE_S_S_S_S_1, checkTraitType(), checkTraitName(), message, with, get());
		return self();
	}

	default @Nonnull <V1> SELF mustNotWithByte(V1 with, @Nonnull LObjBytePredicate<? super V1> predicate, @Nonnull String message, @Nullable Object... messageParams) {
		Null.nonNullArg(predicate, "predicate");
		LObjBytePredicate.throwIf(with, get(), predicate, checkTraitFactory(), message, messageParams);
		return self();
	}

	default @Nonnull SELF checkWhen(@Nonnull LBytePredicate pred, @Nonnull LConsumer<SELF> conditionalChecks) {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(conditionalChecks, "conditionalChecks");
		if (pred.test(get())) {
			conditionalChecks.accept(self());
		};
		return self();
	}

	default @Nonnull SELF checkWhenNot(@Nonnull LBytePredicate pred, @Nonnull LConsumer<SELF> conditionalChecks) {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(conditionalChecks, "conditionalChecks");
		if (!pred.test(get())) {
			conditionalChecks.accept(self());
		};
		return self();
	}

	default @Nonnull SELF checkWhen(@Nonnull LBiBytePredicate pred, byte a2, @Nonnull LConsumer<SELF> conditionalChecks) {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(conditionalChecks, "conditionalChecks");
		if (pred.test(get(), a2)) {
			conditionalChecks.accept(self());
		};
		return self();
	}

	default @Nonnull SELF checkWhenNot(@Nonnull LBiBytePredicate pred, byte a2, @Nonnull LConsumer<SELF> conditionalChecks) {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(conditionalChecks, "conditionalChecks");
		if (!pred.test(get(), a2)) {
			conditionalChecks.accept(self());
		};
		return self();
	}

	default @Nonnull SELF checkWhen(@Nonnull LTriBytePredicate pred, byte a2, byte a3, @Nonnull LConsumer<SELF> conditionalChecks) {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(conditionalChecks, "conditionalChecks");
		if (pred.test(get(), a2, a3)) {
			conditionalChecks.accept(self());
		};
		return self();
	}

	default @Nonnull SELF checkWhenNot(@Nonnull LTriBytePredicate pred, byte a2, byte a3, @Nonnull LConsumer<SELF> conditionalChecks) {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(conditionalChecks, "conditionalChecks");
		if (!pred.test(get(), a2, a3)) {
			conditionalChecks.accept(self());
		};
		return self();
	}

	// </editor-fold>

	default @Nonnull SELF fails(@Nonnull String newMessage) {
		must(LBytePredicate::alwaysFalse, newMessage);
		return self();
	}

	default @Nonnull SELF fails$(@Nonnull String newMessage) {
		must$(LBytePredicate::alwaysFalse, newMessage);
		return self();
	}

	default @Nonnull SELF fails(@Nonnull String newMessage, @Nullable Object... messageParams) {
		must(LBytePredicate::alwaysFalse, newMessage, messageParams);
		return self();
	}

}
