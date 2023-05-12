/*
 * This file is part of "lunisolar-magma".
 *
 * (C) Copyright 2014-2023 Lunisolar (http://lunisolar.eu/).
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

package eu.lunisolar.magma.func.supp.opt;

import javax.annotation.Nonnull; // NOSONAR
import javax.annotation.Nullable; // NOSONAR
import java.util.*; // NOSONAR
import eu.lunisolar.magma.basics.*; // NOSONAR
import eu.lunisolar.magma.basics.builder.*; // NOSONAR
import eu.lunisolar.magma.basics.exceptions.*; // NOSONAR
import eu.lunisolar.magma.basics.meta.*; // NOSONAR
import eu.lunisolar.magma.basics.meta.aType.*; // NOSONAR
import eu.lunisolar.magma.basics.meta.functional.*; // NOSONAR
import eu.lunisolar.magma.basics.meta.functional.type.*; // NOSONAR
import eu.lunisolar.magma.basics.meta.functional.domain.*; // NOSONAR
import eu.lunisolar.magma.func.*; // NOSONAR
import eu.lunisolar.magma.func.supp.*; // NOSONAR
import eu.lunisolar.magma.func.supp.check.*; // NOSONAR
import eu.lunisolar.magma.func.supp.traits.*; // NOSONAR
import eu.lunisolar.magma.func.supp.value.*; // NOSONAR
import eu.lunisolar.magma.func.tuple.*; // NOSONAR
import eu.lunisolar.magma.basics.fluent.*; // NOSONAR

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

/**
 * Extension over the Optional/OptionalInt/OptionalLong/OptionalDouble. You can use them as 'optional' response. But this class mainly aims to provide more
 * flexible possibility to have further transformations.
 *
 * Why?
 * - To introduce more possibility for cases where simply referencing existing method (that can be provided by editor completion) just will make the job done.
 * - That means more possibility to build 'sentences' that actually tell what happens.
 * - Performance wise, if use correctly, both Java's Optional and this class work similar way. Given that escape analysis, and 'stack allocation' will not be
 * blocked to provide full optimization, even capturing lambdas will be fully optimized by JVM. So 'allocating" and using Optional/Opt locally is not as much
 * costly as one would expected (in correct circumstances).
 */
public interface OptFltTrait<SELF extends OptFltTrait<SELF>> extends FluentTrait<SELF>, aValue<aFloat>, CheckFltTrait<SELF>, FilterFltSingleTrait<SELF>, IsFltTrait<SELF>, DoIfFltSingleTrait<SELF>, UseFltSingleTrait<SELF>, UniMapFltTrait<SELF> {

	// <editor-fold desc="forcing ValueTrait re-implementation">

	@Override
	@Nonnull
	SELF value(float value);

	@Override
	@Nonnull
	SELF voidValue();

	// </editor-fold>

	float get();

	default @Nullable Float nullable() {
		return isPresent() ? get() : null;
	}

	@Nonnull
	default String checkTraitType() {
		return this.getClass().getSimpleName();
	}

	public boolean isPresent();

	default boolean isVoid() {
		return !isPresent();
	}

	default OptionalDouble toOpt() {
		if (isPresent()) {
			return OptionalDouble.of(get());
		} else {
			return OptionalDouble.empty();
		}
	}

	// <editor-fold desc="isPresent() dependant boolean terminals">

	@Override
	default boolean is(@Nonnull LFltPredicate predicate) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && FilterFltSingleTrait.super.is(predicate);
	}

	@Override
	default boolean isNot(@Nonnull LFltPredicate predicate) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && FilterFltSingleTrait.super.isNot(predicate);
	}

	@Override
	default boolean is(float a2, @Nonnull LBiFltPredicate predicate) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && FilterFltSingleTrait.super.is(a2, predicate);
	}

	@Override
	default boolean isNot(float a2, @Nonnull LBiFltPredicate predicate) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && FilterFltSingleTrait.super.isNot(a2, predicate);
	}

	@Override
	default boolean is(float a2, float a3, @Nonnull LTriFltPredicate predicate) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && FilterFltSingleTrait.super.is(a2, a3, predicate);
	}

	@Override
	default boolean isNot(float a2, float a3, @Nonnull LTriFltPredicate predicate) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && FilterFltSingleTrait.super.isNot(a2, a3, predicate);
	}

	@Override
	default boolean isInt(int v, @Nonnull LFltIntPredicate predicate) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && FilterFltSingleTrait.super.isInt(v, predicate);
	}

	@Override
	default boolean isNotInt(int v, @Nonnull LFltIntPredicate predicate) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && FilterFltSingleTrait.super.isNotInt(v, predicate);
	}

	@Override
	default <V1> boolean isWithFlt(V1 with1, @Nonnull LObjFltPredicate<? super V1> predicate) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && FilterFltSingleTrait.super.isWithFlt(with1, predicate);
	}

	@Override
	default <V1> boolean isNotWithFlt(V1 with1, @Nonnull LObjFltPredicate<? super V1> predicate) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && FilterFltSingleTrait.super.isNotWithFlt(with1, predicate);
	}

	@Override
	default <V1, V2> boolean isWith(V1 with1, V2 with2, @Nonnull LBiObjFltPredicate<? super V1, ? super V2> predicate) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && FilterFltSingleTrait.super.isWith(with1, with2, predicate);
	}

	@Override
	default <V1, V2> boolean isNotWith(V1 with1, V2 with2, @Nonnull LBiObjFltPredicate<? super V1, ? super V2> predicate) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && FilterFltSingleTrait.super.isNotWith(with1, with2, predicate);
	}

	// </editor-fold>

	// <editor-fold desc="filtering">

	// </editor-fold>

	default SELF butNot(float value) {
		return isPresent() ? (value() == value ? voidValue() : fluentCtx()) : voidValue();
	}

	// <editor-fold desc="uniMap">

	default @Nonnull SELF map(@Nonnull LFltUnaryOperator mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? value(mapping.applyAsFlt(get())) : voidValue();
	}

	default @Nonnull SELF map(float a1, @Nonnull LFltBinaryOperator mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? value(mapping.applyAsFlt(get(), a1)) : voidValue();
	}

	default @Nonnull SELF map(float a1, float a2, @Nonnull LFltTernaryOperator mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? value(mapping.applyAsFlt(get(), a1, a2)) : voidValue();
	}

	// </editor-fold>

	// <editor-fold desc="map">

	default @Nonnull OptBool mapToBool(@Nonnull LFltPredicate mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptBool.of(mapping.test(get()))) : OptBool.empty();
	}

	default @Nonnull <K> OptBool mapToBoolWith(K a1, @Nonnull LObjFltPredicate<? super K> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptBool.of(mapping.test(a1, get()))) : OptBool.empty();
	}

	default @Nonnull <K1, K2> OptBool mapToBoolWith(K1 a1, K2 a2, @Nonnull LBiObjFltPredicate<? super K1, ? super K2> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptBool.of(mapping.test(a1, a2, get()))) : OptBool.empty();
	}

	default @Nonnull OptBool mapToBool(float a1, @Nonnull LBiFltPredicate mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptBool.of(mapping.test(get(), a1))) : OptBool.empty();
	}

	default @Nonnull OptBool mapToBool(float a1, float a2, @Nonnull LTriFltPredicate mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptBool.of(mapping.test(get(), a1, a2))) : OptBool.empty();
	}

	default @Nonnull OptByte mapToByte(@Nonnull LFltToByteFunction mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptByte.of(mapping.applyAsByte(get()))) : OptByte.empty();
	}

	default @Nonnull OptDbl mapToDbl(@Nonnull LFltToDblFunction mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptDbl.of(mapping.applyAsDbl(get()))) : OptDbl.empty();
	}

	default @Nonnull OptChar mapToChar(@Nonnull LFltToCharFunction mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptChar.of(mapping.applyAsChar(get()))) : OptChar.empty();
	}

	default @Nonnull OptSrt mapToSrt(@Nonnull LFltToSrtFunction mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptSrt.of(mapping.applyAsSrt(get()))) : OptSrt.empty();
	}

	default @Nonnull OptInt mapToInt(@Nonnull LFltToIntFunction mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptInt.of(mapping.applyAsInt(get()))) : OptInt.empty();
	}

	default @Nonnull OptLong mapToLong(@Nonnull LFltToLongFunction mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptLong.of(mapping.applyAsLong(get()))) : OptLong.empty();
	}

	default @Nonnull <R> Opt<R> mapToObj(@Nonnull LFltFunction<? extends R> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (Opt.of(mapping.apply(get()))) : Opt.empty();
	}

	default @Nonnull <K, R> Opt<R> mapToObjWith(K a1, @Nonnull LObjFltFunction<? super K, ? extends R> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (Opt.of(mapping.apply(a1, get()))) : Opt.empty();
	}

	default @Nonnull <K1, K2, R> Opt<R> mapToObjWith(K1 a1, K2 a2, @Nonnull LBiObjFltFunction<? super K1, ? super K2, ? extends R> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (Opt.of(mapping.apply(a1, a2, get()))) : Opt.empty();
	}

	default @Nonnull <R> Opt<R> mapToObj(float a1, @Nonnull LBiFltFunction<? extends R> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (Opt.of(mapping.apply(get(), a1))) : Opt.empty();
	}

	default @Nonnull <R> Opt<R> mapToObj(float a1, float a2, @Nonnull LTriFltFunction<? extends R> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (Opt.of(mapping.apply(get(), a1, a2))) : Opt.empty();
	}

	// </editor-fold>

	// <editor-fold desc="flatMap">

	default @Nonnull OptBool flatMapToBool(@Nonnull LFltFunction<? extends OptBoolTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptBool.from(mapping.apply(get())) : OptBool.empty();
	}

	default @Nonnull <K> OptBool flatMapToBoolWith(K a1, @Nonnull LObjFltFunction<? super K, ? extends OptBoolTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptBool.from(mapping.apply(a1, get())) : OptBool.empty();
	}

	default @Nonnull <K1, K2> OptBool flatMapToBoolWith(K1 a1, K2 a2, @Nonnull LBiObjFltFunction<? super K1, ? super K2, ? extends OptBoolTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptBool.from(mapping.apply(a1, a2, get())) : OptBool.empty();
	}

	default @Nonnull OptBool flatMapToBool(float a1, @Nonnull LBiFltFunction<? extends OptBoolTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptBool.from(mapping.apply(get(), a1)) : OptBool.empty();
	}

	default @Nonnull OptBool flatMapToBool(float a1, float a2, @Nonnull LTriFltFunction<? extends OptBoolTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptBool.from(mapping.apply(get(), a1, a2)) : OptBool.empty();
	}

	default @Nonnull OptByte flatMapToByte(@Nonnull LFltFunction<? extends OptByteTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptByte.from(mapping.apply(get())) : OptByte.empty();
	}

	default @Nonnull <K> OptByte flatMapToByteWith(K a1, @Nonnull LObjFltFunction<? super K, ? extends OptByteTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptByte.from(mapping.apply(a1, get())) : OptByte.empty();
	}

	default @Nonnull <K1, K2> OptByte flatMapToByteWith(K1 a1, K2 a2, @Nonnull LBiObjFltFunction<? super K1, ? super K2, ? extends OptByteTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptByte.from(mapping.apply(a1, a2, get())) : OptByte.empty();
	}

	default @Nonnull OptByte flatMapToByte(float a1, @Nonnull LBiFltFunction<? extends OptByteTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptByte.from(mapping.apply(get(), a1)) : OptByte.empty();
	}

	default @Nonnull OptByte flatMapToByte(float a1, float a2, @Nonnull LTriFltFunction<? extends OptByteTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptByte.from(mapping.apply(get(), a1, a2)) : OptByte.empty();
	}

	default @Nonnull OptDbl flatMapToDbl(@Nonnull LFltFunction<? extends OptDblTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptDbl.from(mapping.apply(get())) : OptDbl.empty();
	}

	default @Nonnull <K> OptDbl flatMapToDblWith(K a1, @Nonnull LObjFltFunction<? super K, ? extends OptDblTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptDbl.from(mapping.apply(a1, get())) : OptDbl.empty();
	}

	default @Nonnull <K1, K2> OptDbl flatMapToDblWith(K1 a1, K2 a2, @Nonnull LBiObjFltFunction<? super K1, ? super K2, ? extends OptDblTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptDbl.from(mapping.apply(a1, a2, get())) : OptDbl.empty();
	}

	default @Nonnull OptDbl flatMapToDbl(float a1, @Nonnull LBiFltFunction<? extends OptDblTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptDbl.from(mapping.apply(get(), a1)) : OptDbl.empty();
	}

	default @Nonnull OptDbl flatMapToDbl(float a1, float a2, @Nonnull LTriFltFunction<? extends OptDblTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptDbl.from(mapping.apply(get(), a1, a2)) : OptDbl.empty();
	}

	default @Nonnull OptChar flatMapToChar(@Nonnull LFltFunction<? extends OptCharTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptChar.from(mapping.apply(get())) : OptChar.empty();
	}

	default @Nonnull <K> OptChar flatMapToCharWith(K a1, @Nonnull LObjFltFunction<? super K, ? extends OptCharTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptChar.from(mapping.apply(a1, get())) : OptChar.empty();
	}

	default @Nonnull <K1, K2> OptChar flatMapToCharWith(K1 a1, K2 a2, @Nonnull LBiObjFltFunction<? super K1, ? super K2, ? extends OptCharTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptChar.from(mapping.apply(a1, a2, get())) : OptChar.empty();
	}

	default @Nonnull OptChar flatMapToChar(float a1, @Nonnull LBiFltFunction<? extends OptCharTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptChar.from(mapping.apply(get(), a1)) : OptChar.empty();
	}

	default @Nonnull OptChar flatMapToChar(float a1, float a2, @Nonnull LTriFltFunction<? extends OptCharTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptChar.from(mapping.apply(get(), a1, a2)) : OptChar.empty();
	}

	default @Nonnull OptSrt flatMapToSrt(@Nonnull LFltFunction<? extends OptSrtTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptSrt.from(mapping.apply(get())) : OptSrt.empty();
	}

	default @Nonnull <K> OptSrt flatMapToSrtWith(K a1, @Nonnull LObjFltFunction<? super K, ? extends OptSrtTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptSrt.from(mapping.apply(a1, get())) : OptSrt.empty();
	}

	default @Nonnull <K1, K2> OptSrt flatMapToSrtWith(K1 a1, K2 a2, @Nonnull LBiObjFltFunction<? super K1, ? super K2, ? extends OptSrtTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptSrt.from(mapping.apply(a1, a2, get())) : OptSrt.empty();
	}

	default @Nonnull OptSrt flatMapToSrt(float a1, @Nonnull LBiFltFunction<? extends OptSrtTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptSrt.from(mapping.apply(get(), a1)) : OptSrt.empty();
	}

	default @Nonnull OptSrt flatMapToSrt(float a1, float a2, @Nonnull LTriFltFunction<? extends OptSrtTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptSrt.from(mapping.apply(get(), a1, a2)) : OptSrt.empty();
	}

	default @Nonnull SELF flatMap(@Nonnull LFltFunction<? extends OptFltTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? valueFrom(mapping.apply(get())) : voidValue();
	}

	default @Nonnull <K> SELF flatMapWith(K a1, @Nonnull LObjFltFunction<? super K, ? extends OptFltTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? valueFrom(mapping.apply(a1, get())) : voidValue();
	}

	default @Nonnull <K1, K2> SELF flatMapWith(K1 a1, K2 a2, @Nonnull LBiObjFltFunction<? super K1, ? super K2, ? extends OptFltTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? valueFrom(mapping.apply(a1, a2, get())) : voidValue();
	}

	default @Nonnull SELF flatMap(float a1, @Nonnull LBiFltFunction<? extends OptFltTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? valueFrom(mapping.apply(get(), a1)) : voidValue();
	}

	default @Nonnull SELF flatMap(float a1, float a2, @Nonnull LTriFltFunction<? extends OptFltTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? valueFrom(mapping.apply(get(), a1, a2)) : voidValue();
	}

	default @Nonnull OptInt flatMapToInt(@Nonnull LFltFunction<? extends OptIntTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptInt.from(mapping.apply(get())) : OptInt.empty();
	}

	default @Nonnull <K> OptInt flatMapToIntWith(K a1, @Nonnull LObjFltFunction<? super K, ? extends OptIntTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptInt.from(mapping.apply(a1, get())) : OptInt.empty();
	}

	default @Nonnull <K1, K2> OptInt flatMapToIntWith(K1 a1, K2 a2, @Nonnull LBiObjFltFunction<? super K1, ? super K2, ? extends OptIntTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptInt.from(mapping.apply(a1, a2, get())) : OptInt.empty();
	}

	default @Nonnull OptInt flatMapToInt(float a1, @Nonnull LBiFltFunction<? extends OptIntTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptInt.from(mapping.apply(get(), a1)) : OptInt.empty();
	}

	default @Nonnull OptInt flatMapToInt(float a1, float a2, @Nonnull LTriFltFunction<? extends OptIntTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptInt.from(mapping.apply(get(), a1, a2)) : OptInt.empty();
	}

	default @Nonnull OptLong flatMapToLong(@Nonnull LFltFunction<? extends OptLongTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptLong.from(mapping.apply(get())) : OptLong.empty();
	}

	default @Nonnull <K> OptLong flatMapToLongWith(K a1, @Nonnull LObjFltFunction<? super K, ? extends OptLongTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptLong.from(mapping.apply(a1, get())) : OptLong.empty();
	}

	default @Nonnull <K1, K2> OptLong flatMapToLongWith(K1 a1, K2 a2, @Nonnull LBiObjFltFunction<? super K1, ? super K2, ? extends OptLongTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptLong.from(mapping.apply(a1, a2, get())) : OptLong.empty();
	}

	default @Nonnull OptLong flatMapToLong(float a1, @Nonnull LBiFltFunction<? extends OptLongTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptLong.from(mapping.apply(get(), a1)) : OptLong.empty();
	}

	default @Nonnull OptLong flatMapToLong(float a1, float a2, @Nonnull LTriFltFunction<? extends OptLongTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptLong.from(mapping.apply(get(), a1, a2)) : OptLong.empty();
	}

	default @Nonnull <R> Opt<R> flatMapToObj(@Nonnull LFltFunction<? extends ValueTrait<? extends R, ?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? Opt.from(mapping.apply(get())) : Opt.empty();
	}

	default @Nonnull <K, R> Opt<R> flatMapToObjWith(K a1, @Nonnull LObjFltFunction<? super K, ? extends ValueTrait<? extends R, ?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? Opt.from(mapping.apply(a1, get())) : Opt.empty();
	}

	default @Nonnull <K1, K2, R> Opt<R> flatMapToObjWith(K1 a1, K2 a2, @Nonnull LBiObjFltFunction<? super K1, ? super K2, ? extends ValueTrait<? extends R, ?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? Opt.from(mapping.apply(a1, a2, get())) : Opt.empty();
	}

	default @Nonnull <R> Opt<R> flatMapToObj(float a1, @Nonnull LBiFltFunction<? extends ValueTrait<? extends R, ?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? Opt.from(mapping.apply(get(), a1)) : Opt.empty();
	}

	default @Nonnull <R> Opt<R> flatMapToObj(float a1, float a2, @Nonnull LTriFltFunction<? extends ValueTrait<? extends R, ?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? Opt.from(mapping.apply(get(), a1, a2)) : Opt.empty();
	}

	// </editor-fold>

	// <editor-fold desc="doIf">

	// </editor-fold>

	// <editor-fold desc="ifPresent">

	default @Nonnull SELF ifVoid(@Nonnull LAction action) {
		Null.nonNullArg(action, "action");
		if (isVoid()) {
			action.execute();
		}
		return fluentCtx();
	}

	default SELF ifPresent(@Nonnull LFltConsumer action) {
		Null.nonNullArg(action, "action");
		if (isPresent()) {
			action.accept(get());
		}
		return fluentCtx();
	}

	default SELF ifPresentOr(@Nonnull LFltConsumer action, @Nonnull LAction emptyAction) {
		Null.nonNullArg(action, "action");
		if (isPresent()) {
			action.accept(get());
		} else {
			emptyAction.execute();
		}
		return fluentCtx();
	}

	default SELF ifPresentWith(@Nonnull LFltConsumer action) {
		Null.nonNullArg(action, "action");
		if (isPresent()) {
			action.accept(get());
		}
		return fluentCtx();
	}

	default SELF ifPresentOrWith(@Nonnull LFltConsumer action, @Nonnull LAction emptyAction) {
		Null.nonNullArg(action, "action");
		if (isPresent()) {
			action.accept(get());
		} else {
			emptyAction.execute();
		}
		return fluentCtx();
	}

	default <K1> SELF ifPresentWith(K1 a1, @Nonnull LObjFltConsumer<? super K1> action) {
		Null.nonNullArg(action, "action");
		if (isPresent()) {
			action.accept(a1, get());
		}
		return fluentCtx();
	}

	default <K1> SELF ifPresentOrWith(K1 a1, @Nonnull LObjFltConsumer<? super K1> action, @Nonnull LAction emptyAction) {
		Null.nonNullArg(action, "action");
		if (isPresent()) {
			action.accept(a1, get());
		} else {
			emptyAction.execute();
		}
		return fluentCtx();
	}

	default <K1, K2> SELF ifPresentWith(K1 a1, K2 a2, @Nonnull LBiObjFltConsumer<? super K1, ? super K2> action) {
		Null.nonNullArg(action, "action");
		if (isPresent()) {
			action.accept(a1, a2, get());
		}
		return fluentCtx();
	}

	default <K1, K2> SELF ifPresentOrWith(K1 a1, K2 a2, @Nonnull LBiObjFltConsumer<? super K1, ? super K2> action, @Nonnull LAction emptyAction) {
		Null.nonNullArg(action, "action");
		if (isPresent()) {
			action.accept(a1, a2, get());
		} else {
			emptyAction.execute();
		}
		return fluentCtx();
	}

	default SELF ifExists(@Nonnull LConsumer<? super SELF> action) {
		Null.nonNullArg(action, "action");
		if (isPresent()) {
			action.accept(fluentCtx());
		}
		return fluentCtx();
	}

	default <T2> SELF ifExists(T2 a2, @Nonnull LBiConsumer<? super SELF, ? super T2> action) {
		Null.nonNullArg(action, "action");
		if (isPresent()) {
			action.accept(fluentCtx(), a2);
		}
		return fluentCtx();
	}

	default <T2, T3> SELF ifExists(T2 a2, T3 a3, @Nonnull LTriConsumer<? super SELF, ? super T2, ? super T3> action) {
		Null.nonNullArg(action, "action");
		if (isPresent()) {
			action.accept(fluentCtx(), a2, a3);
		}
		return fluentCtx();
	}

	default <T2, T4, T5> SELF ifExists(T2 a2, T4 a3, T5 a4, @Nonnull LQuadConsumer<? super SELF, ? super T2, ? super T4, ? super T5> action) {
		Null.nonNullArg(action, "action");
		if (isPresent()) {
			action.accept(fluentCtx(), a2, a3, a4);
		}
		return fluentCtx();
	}

	// </editor-fold>

	/** Compared to ifPresent it will simply fail if there is no value */
	default @Nonnull SELF visit(@Nonnull LFltConsumer consumer) {
		Null.nonNullArg(consumer, "consumer");
		consumer.accept(get());
		return fluentCtx();
	}

	// <editor-fold desc="orElse">

	default @Nonnull SELF orThrow() {
		if (isPresent()) {
			return fluentCtx();
		}

		throw Handling.create(X::noSuchElement);
	}

	default @Nonnull SELF orThrow(@Nonnull ExF<RuntimeException> fx) {
		if (isPresent()) {
			return fluentCtx();
		}

		Null.nonNullArg(fx, "fx");
		throw Handling.create(fx);
	}

	default @Nonnull SELF orThrow(@Nonnull ExMF<RuntimeException> fx, @Nullable String msg) {
		if (isPresent()) {
			return fluentCtx();
		}

		Null.nonNullArg(fx, "fx");
		throw Handling.create(fx, msg);
	}

	default @Nonnull SELF orThrow(@Nonnull ExMF<RuntimeException> fx, @Nullable String msg, @Nullable Object param1) {
		if (isPresent()) {
			return fluentCtx();
		}

		Null.nonNullArg(fx, "fx");
		throw Handling.create(fx, msg, param1);
	}

	default @Nonnull SELF orThrow(@Nonnull ExMF<RuntimeException> fx, @Nullable String msg, @Nullable Object param1, @Nullable Object param2) {
		if (isPresent()) {
			return fluentCtx();
		}

		Null.nonNullArg(fx, "fx");
		throw Handling.create(fx, msg, param1, param2);
	}

	default @Nonnull SELF orThrow(@Nonnull ExMF<RuntimeException> fx, @Nullable String msg, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		if (isPresent()) {
			return fluentCtx();
		}

		Null.nonNullArg(fx, "fx");
		throw Handling.create(fx, msg, param1, param2, param3);
	}

	default float orElseThrow() {
		if (isPresent()) {
			return get();
		}

		throw Handling.create(X::noSuchElement);
	}

	default float orElseThrow(@Nonnull ExF<RuntimeException> fx) {
		if (isPresent()) {
			return get();
		}

		Null.nonNullArg(fx, "fx");
		throw Handling.create(fx);
	}

	default float orElseThrow(@Nonnull ExMF<RuntimeException> fx, @Nullable String msg) {
		if (isPresent()) {
			return get();
		}

		Null.nonNullArg(fx, "fx");
		throw Handling.create(fx, msg);
	}

	default float orElseThrow(@Nonnull ExMF<RuntimeException> fx, @Nullable String msg, @Nullable Object param1) {
		if (isPresent()) {
			return get();
		}

		Null.nonNullArg(fx, "fx");
		throw Handling.create(fx, msg, param1);
	}

	default float orElseThrow(@Nonnull ExMF<RuntimeException> fx, @Nullable String msg, @Nullable Object param1, @Nullable Object param2) {
		if (isPresent()) {
			return get();
		}

		Null.nonNullArg(fx, "fx");
		throw Handling.create(fx, msg, param1, param2);
	}

	default float orElseThrow(@Nonnull ExMF<RuntimeException> fx, @Nullable String msg, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		if (isPresent()) {
			return get();
		}

		Null.nonNullArg(fx, "fx");
		throw Handling.create(fx, msg, param1, param2, param3);
	}

	default float orElse(@Nullable float value) {
		return isPresent() ? get() : value;
	}

	default float orElseGet(@Nonnull LFltSupplier supplier) {
		Null.nonNullArg(supplier, "supplier");
		return isPresent() ? get() : supplier.getAsFlt();
	}

	default SELF orGet(@Nonnull LFltSupplier supplier) {
		Null.nonNullArg(supplier, "supplier");
		return isPresent() ? fluentCtx() : value(supplier.getAsFlt());
	}

	default SELF orFlatGet(@Nonnull LSupplier<? extends OptFltTrait<?>> supplier) {
		Null.nonNullArg(supplier, "supplier");
		return isPresent() ? fluentCtx() : valueFrom(supplier.get());
	}

	default SELF or(@Nullable float value) {
		return isPresent() ? fluentCtx() : value(value);
	}

	default SELF orOpt(@Nonnull OptFltTrait<?> opt) {
		Null.nonNullArg(opt, "opt");
		return isPresent() ? fluentCtx() : valueFrom(opt);
	}

	default SELF orValue(@Nonnull FltValueTrait<?> value) {
		Null.nonNullArg(value, "value");
		return isPresent() ? fluentCtx() : value(value.value());
	}

	default <K> float orElseApply(K a1, @Nonnull LToFltFunction<? super K> supplier) {
		Null.nonNullArg(supplier, "supplier");
		return isPresent() ? get() : supplier.applyAsFlt(a1);
	}

	default <K> SELF orApply(K a1, @Nonnull LToFltFunction<? super K> supplier) {
		Null.nonNullArg(supplier, "supplier");
		return isPresent() ? fluentCtx() : value(supplier.applyAsFlt(a1));
	}

	default <K> SELF orFlatApply(K a1, @Nonnull LFunction<? super K, ? extends OptFltTrait<?>> supplier) {
		Null.nonNullArg(supplier, "supplier");
		return isPresent() ? fluentCtx() : valueFrom(supplier.apply(a1));
	}

	default <K1, K2> float orElseApply(K1 a1, K2 a2, @Nonnull LToFltBiFunction<? super K1, ? super K2> supplier) {
		Null.nonNullArg(supplier, "supplier");
		return isPresent() ? get() : supplier.applyAsFlt(a1, a2);
	}

	default <K1, K2> SELF orApply(K1 a1, K2 a2, @Nonnull LToFltBiFunction<? super K1, ? super K2> supplier) {
		Null.nonNullArg(supplier, "supplier");
		return isPresent() ? fluentCtx() : value(supplier.applyAsFlt(a1, a2));
	}

	default <K1, K2> SELF orFlatApply(K1 a1, K2 a2, @Nonnull LBiFunction<? super K1, ? super K2, ? extends OptFltTrait<?>> supplier) {
		Null.nonNullArg(supplier, "supplier");
		return isPresent() ? fluentCtx() : valueFrom(supplier.apply(a1, a2));
	}

	default <K1, K2, K3> SELF orFlatApply(K1 a1, K2 a2, K3 a3, @Nonnull LTriFunction<? super K1, ? super K2, ? super K3, ? extends OptFltTrait<?>> supplier) {
		Null.nonNullArg(supplier, "supplier");
		return isPresent() ? fluentCtx() : valueFrom(supplier.apply(a1, a2, a3));
	}

	// </editor-fold>

	default OptionalDouble toOptional() {
		return isPresent() ? OptionalDouble.of(value()) : OptionalDouble.empty();
	}

	default @Nonnull OptFlt op(@Nonnull OptFltTrait<?> opt2, @Nonnull LFltBinaryOperator both, @Nonnull LFltUnaryOperator first, @Nonnull LFltUnaryOperator second, @Nonnull LFltSupplier none) {
		return op(this, opt2, both, first, second, none);
	}

	public static @Nonnull OptFlt op(@Nonnull OptFltTrait<?> opt1, @Nonnull OptFltTrait<?> opt2, @Nonnull LFltBinaryOperator both, @Nonnull LFltUnaryOperator first, @Nonnull LFltUnaryOperator second, @Nonnull LFltSupplier none) {
		Null.nonNullArg(opt1, "opt1");
		Null.nonNullArg(opt2, "opt2");

		if (opt1.isPresent()) {
			if (opt2.isPresent()) {
				Null.nonNullArg(both, "both");
				return Opt.of(both.applyAsFlt(opt1.get(), opt2.get()));
			} else {
				Null.nonNullArg(first, "first");
				return Opt.of(first.applyAsFlt(opt1.get()));
			}
		} else {
			if (opt2.isPresent()) {
				Null.nonNullArg(second, "second");
				return Opt.of(second.applyAsFlt(opt2.get()));
			} else {
				Null.nonNullArg(none, "none");
				return Opt.of(none.getAsFlt());
			}
		}
	}

	default @Nonnull OptFlt simpleOp(@Nonnull OptFltTrait<?> opt2, float defaultInput, @Nonnull LFltBinaryOperator operation) {
		return simpleOp((OptFltTrait) this, opt2, defaultInput, operation);
	}

	public static @Nonnull OptFlt simpleOp(@Nonnull OptFltTrait<?> opt1, @Nonnull OptFltTrait<?> opt2, float defaultInput, @Nonnull LFltBinaryOperator operation) {
		Null.nonNullArg(opt1, "opt1");
		Null.nonNullArg(opt2, "opt2");

		return Opt.of(operation.applyAsFlt(opt1.orElse(Clazz.the(defaultInput)), opt2.orElse(Clazz.the(defaultInput))));
	}

	default @Nonnull OptFlt simpleOp(@Nonnull OptFltTrait<?> opt2, @Nonnull LFltBinaryOperator operation) {
		return simpleOp((OptFltTrait) this, opt2, operation);
	}

	public static @Nonnull OptFlt simpleOp(@Nonnull OptFltTrait<?> opt1, @Nonnull OptFltTrait<?> opt2, @Nonnull LFltBinaryOperator operation) {
		Null.nonNullArg(opt1, "opt1");
		Null.nonNullArg(opt2, "opt2");

		if (opt1.isPresent() && opt2.isPresent()) {
			return Opt.of(operation.applyAsFlt(opt1.get(), opt2.get()));
		}

		return OptFlt.empty();
	}

	default @Nonnull OptFlt flatOp(@Nonnull OptFltTrait<?> opt2, @Nonnull LBiFltFunction<? extends OptFltTrait<?>> both, @Nonnull LFltFunction<? extends OptFltTrait<?>> first, @Nonnull LFltFunction<? extends OptFltTrait<?>> second,
			@Nonnull LSupplier<? extends OptFltTrait<?>> none) {
		return flatOp((OptFltTrait) this, opt2, both, first, second, none);
	}

	public static @Nonnull OptFlt flatOp(@Nonnull OptFltTrait<?> opt1, @Nonnull OptFltTrait<?> opt2, @Nonnull LBiFltFunction<? extends OptFltTrait<?>> both, @Nonnull LFltFunction<? extends OptFltTrait<?>> first,
			@Nonnull LFltFunction<? extends OptFltTrait<?>> second, @Nonnull LSupplier<? extends OptFltTrait<?>> none) {
		Null.nonNullArg(opt1, "opt1");
		Null.nonNullArg(opt2, "opt2");

		if (opt1.isPresent()) {
			if (opt2.isPresent()) {
				Null.nonNullArg(both, "both");
				return Opt.from(both.apply(opt1.get(), opt2.get()));
			} else {
				Null.nonNullArg(first, "first");
				return Opt.from(first.apply(opt1.get()));
			}
		} else {
			if (opt2.isPresent()) {
				Null.nonNullArg(second, "second");
				return Opt.from(second.apply(opt2.get()));
			} else {
				Null.nonNullArg(none, "none");
				return Opt.from(none.get());
			}
		}
	}

}
