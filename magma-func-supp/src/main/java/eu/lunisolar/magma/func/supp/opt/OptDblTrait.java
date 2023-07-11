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
public interface OptDblTrait<SELF extends OptDblTrait<SELF>> extends FluentTrait<SELF>, aValue<aDouble>, CheckDblTrait<SELF>, FilterDblSingleTrait<SELF>, IsDblTrait<SELF>, DoIfDblSingleTrait<SELF>, UseDblSingleTrait<SELF>, UniMapDblTrait<SELF> {

	// <editor-fold desc="forcing ValueTrait re-implementation">

	@Override
	@Nonnull
	SELF value(double value);

	@Override
	@Nonnull
	SELF voidValue();

	// </editor-fold>

	double get();

	default @Nullable Double nullable() {
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
	default boolean is(@Nonnull LDblPredicate predicate) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && FilterDblSingleTrait.super.is(predicate);
	}

	@Override
	default boolean isNot(@Nonnull LDblPredicate predicate) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && FilterDblSingleTrait.super.isNot(predicate);
	}

	@Override
	default boolean is(double a2, @Nonnull LBiDblPredicate predicate) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && FilterDblSingleTrait.super.is(a2, predicate);
	}

	@Override
	default boolean isNot(double a2, @Nonnull LBiDblPredicate predicate) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && FilterDblSingleTrait.super.isNot(a2, predicate);
	}

	@Override
	default boolean is(double a2, double a3, @Nonnull LTriDblPredicate predicate) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && FilterDblSingleTrait.super.is(a2, a3, predicate);
	}

	@Override
	default boolean isNot(double a2, double a3, @Nonnull LTriDblPredicate predicate) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && FilterDblSingleTrait.super.isNot(a2, a3, predicate);
	}

	@Override
	default boolean isInt(int v, @Nonnull LDblIntPredicate predicate) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && FilterDblSingleTrait.super.isInt(v, predicate);
	}

	@Override
	default boolean isNotInt(int v, @Nonnull LDblIntPredicate predicate) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && FilterDblSingleTrait.super.isNotInt(v, predicate);
	}

	@Override
	default <V1> boolean isWithDbl(V1 with1, @Nonnull LObjDblPredicate<? super V1> predicate) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && FilterDblSingleTrait.super.isWithDbl(with1, predicate);
	}

	@Override
	default <V1> boolean isNotWithDbl(V1 with1, @Nonnull LObjDblPredicate<? super V1> predicate) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && FilterDblSingleTrait.super.isNotWithDbl(with1, predicate);
	}

	@Override
	default <V1, V2> boolean isWith(V1 with1, V2 with2, @Nonnull LBiObjDblPredicate<? super V1, ? super V2> predicate) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && FilterDblSingleTrait.super.isWith(with1, with2, predicate);
	}

	@Override
	default <V1, V2> boolean isNotWith(V1 with1, V2 with2, @Nonnull LBiObjDblPredicate<? super V1, ? super V2> predicate) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && FilterDblSingleTrait.super.isNotWith(with1, with2, predicate);
	}

	// </editor-fold>

	// <editor-fold desc="filtering">

	// </editor-fold>

	default SELF butNot(double value) {
		return isPresent() ? (value() == value ? voidValue() : fluentCtx()) : voidValue();
	}

	// <editor-fold desc="uniMap">

	default @Nonnull SELF map(@Nonnull LDblUnaryOperator mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? value(mapping.applyAsDbl(get())) : voidValue();
	}

	default @Nonnull SELF map(double a1, @Nonnull LDblBinaryOperator mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? value(mapping.applyAsDbl(get(), a1)) : voidValue();
	}

	default @Nonnull SELF map(double a1, double a2, @Nonnull LDblTernaryOperator mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? value(mapping.applyAsDbl(get(), a1, a2)) : voidValue();
	}

	// </editor-fold>

	// <editor-fold desc="map">

	default @Nonnull OptBool mapToBool(@Nonnull LDblPredicate mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptBool.of(mapping.test(get()))) : OptBool.empty();
	}

	default @Nonnull <K> OptBool mapToBoolWith(K a1, @Nonnull LObjDblPredicate<? super K> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptBool.of(mapping.test(a1, get()))) : OptBool.empty();
	}

	default @Nonnull <K1, K2> OptBool mapToBoolWith(K1 a1, K2 a2, @Nonnull LBiObjDblPredicate<? super K1, ? super K2> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptBool.of(mapping.test(a1, a2, get()))) : OptBool.empty();
	}

	default @Nonnull OptBool mapToBool(double a1, @Nonnull LBiDblPredicate mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptBool.of(mapping.test(get(), a1))) : OptBool.empty();
	}

	default @Nonnull OptBool mapToBool(double a1, double a2, @Nonnull LTriDblPredicate mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptBool.of(mapping.test(get(), a1, a2))) : OptBool.empty();
	}

	default @Nonnull OptByte mapToByte(@Nonnull LDblToByteFunction mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptByte.of(mapping.applyAsByte(get()))) : OptByte.empty();
	}

	default @Nonnull OptChar mapToChar(@Nonnull LDblToCharFunction mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptChar.of(mapping.applyAsChar(get()))) : OptChar.empty();
	}

	default @Nonnull OptSrt mapToSrt(@Nonnull LDblToSrtFunction mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptSrt.of(mapping.applyAsSrt(get()))) : OptSrt.empty();
	}

	default @Nonnull OptFlt mapToFlt(@Nonnull LDblToFltFunction mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptFlt.of(mapping.applyAsFlt(get()))) : OptFlt.empty();
	}

	default @Nonnull OptInt mapToInt(@Nonnull LDblToIntFunction mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptInt.of(mapping.applyAsInt(get()))) : OptInt.empty();
	}

	default @Nonnull OptLong mapToLong(@Nonnull LDblToLongFunction mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptLong.of(mapping.applyAsLong(get()))) : OptLong.empty();
	}

	default @Nonnull <R> Opt<R> mapToObj(@Nonnull LDblFunction<? extends R> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (Opt.of(mapping.apply(get()))) : Opt.empty();
	}

	default @Nonnull <K, R> Opt<R> mapToObjWith(K a1, @Nonnull LObjDblFunction<? super K, ? extends R> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (Opt.of(mapping.apply(a1, get()))) : Opt.empty();
	}

	default @Nonnull <K1, K2, R> Opt<R> mapToObjWith(K1 a1, K2 a2, @Nonnull LBiObjDblFunction<? super K1, ? super K2, ? extends R> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (Opt.of(mapping.apply(a1, a2, get()))) : Opt.empty();
	}

	default @Nonnull <R> Opt<R> mapToObj(double a1, @Nonnull LBiDblFunction<? extends R> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (Opt.of(mapping.apply(get(), a1))) : Opt.empty();
	}

	default @Nonnull <R> Opt<R> mapToObj(double a1, double a2, @Nonnull LTriDblFunction<? extends R> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (Opt.of(mapping.apply(get(), a1, a2))) : Opt.empty();
	}

	// </editor-fold>

	// <editor-fold desc="flatMap">

	default @Nonnull OptBool flatMapToBool(@Nonnull LDblFunction<? extends OptBoolTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptBool.from(mapping.apply(get())) : OptBool.empty();
	}

	default @Nonnull <K> OptBool flatMapToBoolWith(K a1, @Nonnull LObjDblFunction<? super K, ? extends OptBoolTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptBool.from(mapping.apply(a1, get())) : OptBool.empty();
	}

	default @Nonnull <K1, K2> OptBool flatMapToBoolWith(K1 a1, K2 a2, @Nonnull LBiObjDblFunction<? super K1, ? super K2, ? extends OptBoolTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptBool.from(mapping.apply(a1, a2, get())) : OptBool.empty();
	}

	default @Nonnull OptBool flatMapToBool(double a1, @Nonnull LBiDblFunction<? extends OptBoolTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptBool.from(mapping.apply(get(), a1)) : OptBool.empty();
	}

	default @Nonnull OptBool flatMapToBool(double a1, double a2, @Nonnull LTriDblFunction<? extends OptBoolTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptBool.from(mapping.apply(get(), a1, a2)) : OptBool.empty();
	}

	default @Nonnull OptByte flatMapToByte(@Nonnull LDblFunction<? extends OptByteTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptByte.from(mapping.apply(get())) : OptByte.empty();
	}

	default @Nonnull <K> OptByte flatMapToByteWith(K a1, @Nonnull LObjDblFunction<? super K, ? extends OptByteTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptByte.from(mapping.apply(a1, get())) : OptByte.empty();
	}

	default @Nonnull <K1, K2> OptByte flatMapToByteWith(K1 a1, K2 a2, @Nonnull LBiObjDblFunction<? super K1, ? super K2, ? extends OptByteTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptByte.from(mapping.apply(a1, a2, get())) : OptByte.empty();
	}

	default @Nonnull OptByte flatMapToByte(double a1, @Nonnull LBiDblFunction<? extends OptByteTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptByte.from(mapping.apply(get(), a1)) : OptByte.empty();
	}

	default @Nonnull OptByte flatMapToByte(double a1, double a2, @Nonnull LTriDblFunction<? extends OptByteTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptByte.from(mapping.apply(get(), a1, a2)) : OptByte.empty();
	}

	default @Nonnull SELF flatMap(@Nonnull LDblFunction<? extends OptDblTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? valueFrom(mapping.apply(get())) : voidValue();
	}

	default @Nonnull <K> SELF flatMapWith(K a1, @Nonnull LObjDblFunction<? super K, ? extends OptDblTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? valueFrom(mapping.apply(a1, get())) : voidValue();
	}

	default @Nonnull <K1, K2> SELF flatMapWith(K1 a1, K2 a2, @Nonnull LBiObjDblFunction<? super K1, ? super K2, ? extends OptDblTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? valueFrom(mapping.apply(a1, a2, get())) : voidValue();
	}

	default @Nonnull SELF flatMap(double a1, @Nonnull LBiDblFunction<? extends OptDblTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? valueFrom(mapping.apply(get(), a1)) : voidValue();
	}

	default @Nonnull SELF flatMap(double a1, double a2, @Nonnull LTriDblFunction<? extends OptDblTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? valueFrom(mapping.apply(get(), a1, a2)) : voidValue();
	}

	default @Nonnull OptChar flatMapToChar(@Nonnull LDblFunction<? extends OptCharTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptChar.from(mapping.apply(get())) : OptChar.empty();
	}

	default @Nonnull <K> OptChar flatMapToCharWith(K a1, @Nonnull LObjDblFunction<? super K, ? extends OptCharTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptChar.from(mapping.apply(a1, get())) : OptChar.empty();
	}

	default @Nonnull <K1, K2> OptChar flatMapToCharWith(K1 a1, K2 a2, @Nonnull LBiObjDblFunction<? super K1, ? super K2, ? extends OptCharTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptChar.from(mapping.apply(a1, a2, get())) : OptChar.empty();
	}

	default @Nonnull OptChar flatMapToChar(double a1, @Nonnull LBiDblFunction<? extends OptCharTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptChar.from(mapping.apply(get(), a1)) : OptChar.empty();
	}

	default @Nonnull OptChar flatMapToChar(double a1, double a2, @Nonnull LTriDblFunction<? extends OptCharTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptChar.from(mapping.apply(get(), a1, a2)) : OptChar.empty();
	}

	default @Nonnull OptSrt flatMapToSrt(@Nonnull LDblFunction<? extends OptSrtTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptSrt.from(mapping.apply(get())) : OptSrt.empty();
	}

	default @Nonnull <K> OptSrt flatMapToSrtWith(K a1, @Nonnull LObjDblFunction<? super K, ? extends OptSrtTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptSrt.from(mapping.apply(a1, get())) : OptSrt.empty();
	}

	default @Nonnull <K1, K2> OptSrt flatMapToSrtWith(K1 a1, K2 a2, @Nonnull LBiObjDblFunction<? super K1, ? super K2, ? extends OptSrtTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptSrt.from(mapping.apply(a1, a2, get())) : OptSrt.empty();
	}

	default @Nonnull OptSrt flatMapToSrt(double a1, @Nonnull LBiDblFunction<? extends OptSrtTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptSrt.from(mapping.apply(get(), a1)) : OptSrt.empty();
	}

	default @Nonnull OptSrt flatMapToSrt(double a1, double a2, @Nonnull LTriDblFunction<? extends OptSrtTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptSrt.from(mapping.apply(get(), a1, a2)) : OptSrt.empty();
	}

	default @Nonnull OptFlt flatMapToFlt(@Nonnull LDblFunction<? extends OptFltTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptFlt.from(mapping.apply(get())) : OptFlt.empty();
	}

	default @Nonnull <K> OptFlt flatMapToFltWith(K a1, @Nonnull LObjDblFunction<? super K, ? extends OptFltTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptFlt.from(mapping.apply(a1, get())) : OptFlt.empty();
	}

	default @Nonnull <K1, K2> OptFlt flatMapToFltWith(K1 a1, K2 a2, @Nonnull LBiObjDblFunction<? super K1, ? super K2, ? extends OptFltTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptFlt.from(mapping.apply(a1, a2, get())) : OptFlt.empty();
	}

	default @Nonnull OptFlt flatMapToFlt(double a1, @Nonnull LBiDblFunction<? extends OptFltTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptFlt.from(mapping.apply(get(), a1)) : OptFlt.empty();
	}

	default @Nonnull OptFlt flatMapToFlt(double a1, double a2, @Nonnull LTriDblFunction<? extends OptFltTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptFlt.from(mapping.apply(get(), a1, a2)) : OptFlt.empty();
	}

	default @Nonnull OptInt flatMapToInt(@Nonnull LDblFunction<? extends OptIntTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptInt.from(mapping.apply(get())) : OptInt.empty();
	}

	default @Nonnull <K> OptInt flatMapToIntWith(K a1, @Nonnull LObjDblFunction<? super K, ? extends OptIntTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptInt.from(mapping.apply(a1, get())) : OptInt.empty();
	}

	default @Nonnull <K1, K2> OptInt flatMapToIntWith(K1 a1, K2 a2, @Nonnull LBiObjDblFunction<? super K1, ? super K2, ? extends OptIntTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptInt.from(mapping.apply(a1, a2, get())) : OptInt.empty();
	}

	default @Nonnull OptInt flatMapToInt(double a1, @Nonnull LBiDblFunction<? extends OptIntTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptInt.from(mapping.apply(get(), a1)) : OptInt.empty();
	}

	default @Nonnull OptInt flatMapToInt(double a1, double a2, @Nonnull LTriDblFunction<? extends OptIntTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptInt.from(mapping.apply(get(), a1, a2)) : OptInt.empty();
	}

	default @Nonnull OptLong flatMapToLong(@Nonnull LDblFunction<? extends OptLongTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptLong.from(mapping.apply(get())) : OptLong.empty();
	}

	default @Nonnull <K> OptLong flatMapToLongWith(K a1, @Nonnull LObjDblFunction<? super K, ? extends OptLongTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptLong.from(mapping.apply(a1, get())) : OptLong.empty();
	}

	default @Nonnull <K1, K2> OptLong flatMapToLongWith(K1 a1, K2 a2, @Nonnull LBiObjDblFunction<? super K1, ? super K2, ? extends OptLongTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptLong.from(mapping.apply(a1, a2, get())) : OptLong.empty();
	}

	default @Nonnull OptLong flatMapToLong(double a1, @Nonnull LBiDblFunction<? extends OptLongTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptLong.from(mapping.apply(get(), a1)) : OptLong.empty();
	}

	default @Nonnull OptLong flatMapToLong(double a1, double a2, @Nonnull LTriDblFunction<? extends OptLongTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptLong.from(mapping.apply(get(), a1, a2)) : OptLong.empty();
	}

	default @Nonnull <R> Opt<R> flatMapToObj(@Nonnull LDblFunction<? extends ValueTrait<? extends R, ?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? Opt.from(mapping.apply(get())) : Opt.empty();
	}

	default @Nonnull <K, R> Opt<R> flatMapToObjWith(K a1, @Nonnull LObjDblFunction<? super K, ? extends ValueTrait<? extends R, ?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? Opt.from(mapping.apply(a1, get())) : Opt.empty();
	}

	default @Nonnull <K1, K2, R> Opt<R> flatMapToObjWith(K1 a1, K2 a2, @Nonnull LBiObjDblFunction<? super K1, ? super K2, ? extends ValueTrait<? extends R, ?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? Opt.from(mapping.apply(a1, a2, get())) : Opt.empty();
	}

	default @Nonnull <R> Opt<R> flatMapToObj(double a1, @Nonnull LBiDblFunction<? extends ValueTrait<? extends R, ?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? Opt.from(mapping.apply(get(), a1)) : Opt.empty();
	}

	default @Nonnull <R> Opt<R> flatMapToObj(double a1, double a2, @Nonnull LTriDblFunction<? extends ValueTrait<? extends R, ?>> mapping) {
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

	default SELF ifPresent(@Nonnull LDblConsumer action) {
		Null.nonNullArg(action, "action");
		if (isPresent()) {
			action.accept(get());
		}
		return fluentCtx();
	}

	default SELF ifPresentOr(@Nonnull LDblConsumer action, @Nonnull LAction emptyAction) {
		Null.nonNullArg(action, "action");
		if (isPresent()) {
			action.accept(get());
		} else {
			emptyAction.execute();
		}
		return fluentCtx();
	}

	default SELF ifPresentWith(@Nonnull LDblConsumer action) {
		Null.nonNullArg(action, "action");
		if (isPresent()) {
			action.accept(get());
		}
		return fluentCtx();
	}

	default SELF ifPresentOrWith(@Nonnull LDblConsumer action, @Nonnull LAction emptyAction) {
		Null.nonNullArg(action, "action");
		if (isPresent()) {
			action.accept(get());
		} else {
			emptyAction.execute();
		}
		return fluentCtx();
	}

	default <K1> SELF ifPresentWith(K1 a1, @Nonnull LObjDblConsumer<? super K1> action) {
		Null.nonNullArg(action, "action");
		if (isPresent()) {
			action.accept(a1, get());
		}
		return fluentCtx();
	}

	default <K1> SELF ifPresentOrWith(K1 a1, @Nonnull LObjDblConsumer<? super K1> action, @Nonnull LAction emptyAction) {
		Null.nonNullArg(action, "action");
		if (isPresent()) {
			action.accept(a1, get());
		} else {
			emptyAction.execute();
		}
		return fluentCtx();
	}

	default <K1> SELF ifPresentOrWith(K1 a1, @Nonnull LObjDblConsumer<? super K1> action, @Nonnull LConsumer<K1> emptyAction) {
		Null.nonNullArg(action, "action");
		if (isPresent()) {
			action.accept(a1, get());
		} else {
			emptyAction.accept(a1);
		}
		return fluentCtx();
	}

	default <K1, K2> SELF ifPresentWith(K1 a1, K2 a2, @Nonnull LBiObjDblConsumer<? super K1, ? super K2> action) {
		Null.nonNullArg(action, "action");
		if (isPresent()) {
			action.accept(a1, a2, get());
		}
		return fluentCtx();
	}

	default <K1, K2> SELF ifPresentOrWith(K1 a1, K2 a2, @Nonnull LBiObjDblConsumer<? super K1, ? super K2> action, @Nonnull LAction emptyAction) {
		Null.nonNullArg(action, "action");
		if (isPresent()) {
			action.accept(a1, a2, get());
		} else {
			emptyAction.execute();
		}
		return fluentCtx();
	}

	default <K1, K2> SELF ifPresentOrWith(K1 a1, K2 a2, @Nonnull LBiObjDblConsumer<? super K1, ? super K2> action, @Nonnull LBiConsumer<K1, K2> emptyAction) {
		Null.nonNullArg(action, "action");
		if (isPresent()) {
			action.accept(a1, a2, get());
		} else {
			emptyAction.accept(a1, a2);
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
	default @Nonnull SELF visit(@Nonnull LDblConsumer consumer) {
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

	default double orElseThrow() {
		if (isPresent()) {
			return get();
		}

		throw Handling.create(X::noSuchElement);
	}

	default double orElseThrow(@Nonnull ExF<RuntimeException> fx) {
		if (isPresent()) {
			return get();
		}

		Null.nonNullArg(fx, "fx");
		throw Handling.create(fx);
	}

	default double orElseThrow(@Nonnull ExMF<RuntimeException> fx, @Nullable String msg) {
		if (isPresent()) {
			return get();
		}

		Null.nonNullArg(fx, "fx");
		throw Handling.create(fx, msg);
	}

	default double orElseThrow(@Nonnull ExMF<RuntimeException> fx, @Nullable String msg, @Nullable Object param1) {
		if (isPresent()) {
			return get();
		}

		Null.nonNullArg(fx, "fx");
		throw Handling.create(fx, msg, param1);
	}

	default double orElseThrow(@Nonnull ExMF<RuntimeException> fx, @Nullable String msg, @Nullable Object param1, @Nullable Object param2) {
		if (isPresent()) {
			return get();
		}

		Null.nonNullArg(fx, "fx");
		throw Handling.create(fx, msg, param1, param2);
	}

	default double orElseThrow(@Nonnull ExMF<RuntimeException> fx, @Nullable String msg, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		if (isPresent()) {
			return get();
		}

		Null.nonNullArg(fx, "fx");
		throw Handling.create(fx, msg, param1, param2, param3);
	}

	default double orElse(@Nullable double value) {
		return isPresent() ? get() : value;
	}

	default double orElseGet(@Nonnull LDblSupplier supplier) {
		Null.nonNullArg(supplier, "supplier");
		return isPresent() ? get() : supplier.getAsDbl();
	}

	default SELF orGet(@Nonnull LDblSupplier supplier) {
		Null.nonNullArg(supplier, "supplier");
		return isPresent() ? fluentCtx() : value(supplier.getAsDbl());
	}

	default SELF orFlatGet(@Nonnull LSupplier<? extends OptDblTrait<?>> supplier) {
		Null.nonNullArg(supplier, "supplier");
		return isPresent() ? fluentCtx() : valueFrom(supplier.get());
	}

	default SELF or(@Nullable double value) {
		return isPresent() ? fluentCtx() : value(value);
	}

	default SELF orOpt(@Nonnull OptDblTrait<?> opt) {
		Null.nonNullArg(opt, "opt");
		return isPresent() ? fluentCtx() : valueFrom(opt);
	}

	default SELF orValue(@Nonnull DblValueTrait<?> value) {
		Null.nonNullArg(value, "value");
		return isPresent() ? fluentCtx() : value(value.value());
	}

	default <K> double orElseApply(K a1, @Nonnull LToDblFunction<? super K> supplier) {
		Null.nonNullArg(supplier, "supplier");
		return isPresent() ? get() : supplier.applyAsDbl(a1);
	}

	default <K> SELF orApply(K a1, @Nonnull LToDblFunction<? super K> supplier) {
		Null.nonNullArg(supplier, "supplier");
		return isPresent() ? fluentCtx() : value(supplier.applyAsDbl(a1));
	}

	default <K> SELF orFlatApply(K a1, @Nonnull LFunction<? super K, ? extends OptDblTrait<?>> supplier) {
		Null.nonNullArg(supplier, "supplier");
		return isPresent() ? fluentCtx() : valueFrom(supplier.apply(a1));
	}

	default <K1, K2> double orElseApply(K1 a1, K2 a2, @Nonnull LToDblBiFunction<? super K1, ? super K2> supplier) {
		Null.nonNullArg(supplier, "supplier");
		return isPresent() ? get() : supplier.applyAsDbl(a1, a2);
	}

	default <K1, K2> SELF orApply(K1 a1, K2 a2, @Nonnull LToDblBiFunction<? super K1, ? super K2> supplier) {
		Null.nonNullArg(supplier, "supplier");
		return isPresent() ? fluentCtx() : value(supplier.applyAsDbl(a1, a2));
	}

	default <K1, K2> SELF orFlatApply(K1 a1, K2 a2, @Nonnull LBiFunction<? super K1, ? super K2, ? extends OptDblTrait<?>> supplier) {
		Null.nonNullArg(supplier, "supplier");
		return isPresent() ? fluentCtx() : valueFrom(supplier.apply(a1, a2));
	}

	default <K1, K2, K3> SELF orFlatApply(K1 a1, K2 a2, K3 a3, @Nonnull LTriFunction<? super K1, ? super K2, ? super K3, ? extends OptDblTrait<?>> supplier) {
		Null.nonNullArg(supplier, "supplier");
		return isPresent() ? fluentCtx() : valueFrom(supplier.apply(a1, a2, a3));
	}

	// </editor-fold>

	default OptionalDouble toOptional() {
		return isPresent() ? OptionalDouble.of(value()) : OptionalDouble.empty();
	}

	default @Nonnull OptDbl op(@Nonnull OptDblTrait<?> opt2, @Nonnull LDblBinaryOperator both, @Nonnull LDblUnaryOperator first, @Nonnull LDblUnaryOperator second, @Nonnull LDblSupplier none) {
		return op(this, opt2, both, first, second, none);
	}

	public static @Nonnull OptDbl op(@Nonnull OptDblTrait<?> opt1, @Nonnull OptDblTrait<?> opt2, @Nonnull LDblBinaryOperator both, @Nonnull LDblUnaryOperator first, @Nonnull LDblUnaryOperator second, @Nonnull LDblSupplier none) {
		Null.nonNullArg(opt1, "opt1");
		Null.nonNullArg(opt2, "opt2");

		if (opt1.isPresent()) {
			if (opt2.isPresent()) {
				Null.nonNullArg(both, "both");
				return Opt.of(both.applyAsDbl(opt1.get(), opt2.get()));
			} else {
				Null.nonNullArg(first, "first");
				return Opt.of(first.applyAsDbl(opt1.get()));
			}
		} else {
			if (opt2.isPresent()) {
				Null.nonNullArg(second, "second");
				return Opt.of(second.applyAsDbl(opt2.get()));
			} else {
				Null.nonNullArg(none, "none");
				return Opt.of(none.getAsDbl());
			}
		}
	}

	default @Nonnull OptDbl simpleOp(@Nonnull OptDblTrait<?> opt2, double defaultInput, @Nonnull LDblBinaryOperator operation) {
		return simpleOp((OptDblTrait) this, opt2, defaultInput, operation);
	}

	public static @Nonnull OptDbl simpleOp(@Nonnull OptDblTrait<?> opt1, @Nonnull OptDblTrait<?> opt2, double defaultInput, @Nonnull LDblBinaryOperator operation) {
		Null.nonNullArg(opt1, "opt1");
		Null.nonNullArg(opt2, "opt2");

		return Opt.of(operation.applyAsDbl(opt1.orElse(Clazz.the(defaultInput)), opt2.orElse(Clazz.the(defaultInput))));
	}

	default @Nonnull OptDbl simpleOp(@Nonnull OptDblTrait<?> opt2, @Nonnull LDblBinaryOperator operation) {
		return simpleOp((OptDblTrait) this, opt2, operation);
	}

	public static @Nonnull OptDbl simpleOp(@Nonnull OptDblTrait<?> opt1, @Nonnull OptDblTrait<?> opt2, @Nonnull LDblBinaryOperator operation) {
		Null.nonNullArg(opt1, "opt1");
		Null.nonNullArg(opt2, "opt2");

		if (opt1.isPresent() && opt2.isPresent()) {
			return Opt.of(operation.applyAsDbl(opt1.get(), opt2.get()));
		}

		return OptDbl.empty();
	}

	default @Nonnull OptDbl flatOp(@Nonnull OptDblTrait<?> opt2, @Nonnull LBiDblFunction<? extends OptDblTrait<?>> both, @Nonnull LDblFunction<? extends OptDblTrait<?>> first, @Nonnull LDblFunction<? extends OptDblTrait<?>> second,
			@Nonnull LSupplier<? extends OptDblTrait<?>> none) {
		return flatOp((OptDblTrait) this, opt2, both, first, second, none);
	}

	public static @Nonnull OptDbl flatOp(@Nonnull OptDblTrait<?> opt1, @Nonnull OptDblTrait<?> opt2, @Nonnull LBiDblFunction<? extends OptDblTrait<?>> both, @Nonnull LDblFunction<? extends OptDblTrait<?>> first,
			@Nonnull LDblFunction<? extends OptDblTrait<?>> second, @Nonnull LSupplier<? extends OptDblTrait<?>> none) {
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
