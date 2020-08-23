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
public interface OptByteTrait<SELF extends OptByteTrait<SELF>> extends FluentTrait<SELF>, aValue<aByte>, CheckByteTrait<SELF>, FilterByteSingleTrait<SELF>, IsByteTrait<SELF>, DoIfByteSingleTrait<SELF>, UseByteSingleTrait<SELF> {

	// <editor-fold desc="forcing ValueTrait re-implementation">

	@Override
	@Nonnull
	SELF value(byte value);

	@Override
	@Nonnull
	SELF voidValue();

	// </editor-fold>

	default @Nonnull SELF valueFrom(@Nonnull OptByteTrait<?> opt) {
		Null.nonNullArg(opt, "opt");
		return opt.isPresent() ? value(opt.value()) : voidValue();
	}

	byte get();

	default @Nullable Byte nullable() {
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

	default OptionalInt toOpt() {
		if (isPresent()) {
			return OptionalInt.of(get());
		} else {
			return OptionalInt.empty();
		}
	}

	// <editor-fold desc="isPresent() dependant boolean terminals">

	@Override
	default boolean is(@Nonnull LBytePredicate predicate) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && FilterByteSingleTrait.super.is(predicate);
	}

	@Override
	default boolean isNot(@Nonnull LBytePredicate predicate) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && FilterByteSingleTrait.super.isNot(predicate);
	}

	@Override
	default boolean is(byte a2, @Nonnull LBiBytePredicate predicate) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && FilterByteSingleTrait.super.is(a2, predicate);
	}

	@Override
	default boolean isNot(byte a2, @Nonnull LBiBytePredicate predicate) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && FilterByteSingleTrait.super.isNot(a2, predicate);
	}

	@Override
	default boolean is(byte a2, byte a3, @Nonnull LTriBytePredicate predicate) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && FilterByteSingleTrait.super.is(a2, a3, predicate);
	}

	@Override
	default boolean isNot(byte a2, byte a3, @Nonnull LTriBytePredicate predicate) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && FilterByteSingleTrait.super.isNot(a2, a3, predicate);
	}

	@Override
	default boolean isInt(int v, @Nonnull LByteIntPredicate predicate) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && FilterByteSingleTrait.super.isInt(v, predicate);
	}

	@Override
	default boolean isNotInt(int v, @Nonnull LByteIntPredicate predicate) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && FilterByteSingleTrait.super.isNotInt(v, predicate);
	}

	@Override
	default <V> boolean is_(V v, @Nonnull LObjBytePredicate.LByteObjPred<? super V> predicate) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && FilterByteSingleTrait.super.is_(v, predicate);
	}

	@Override
	default <V> boolean isNot_(V v, @Nonnull LObjBytePredicate.LByteObjPred<? super V> predicate) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && FilterByteSingleTrait.super.isNot_(v, predicate);
	}

	@Override
	default <V1> boolean isWithByte(V1 with1, @Nonnull LObjBytePredicate<? super V1> predicate) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && FilterByteSingleTrait.super.isWithByte(with1, predicate);
	}

	@Override
	default <V1> boolean isNotWithByte(V1 with1, @Nonnull LObjBytePredicate<? super V1> predicate) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && FilterByteSingleTrait.super.isNotWithByte(with1, predicate);
	}

	@Override
	default <V1, V2> boolean isWith(V1 with1, V2 with2, @Nonnull LBiObjBytePredicate<? super V1, ? super V2> predicate) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && FilterByteSingleTrait.super.isWith(with1, with2, predicate);
	}

	@Override
	default <V1, V2> boolean isNotWith(V1 with1, V2 with2, @Nonnull LBiObjBytePredicate<? super V1, ? super V2> predicate) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && FilterByteSingleTrait.super.isNotWith(with1, with2, predicate);
	}

	// </editor-fold>

	// <editor-fold desc="filtering">

	// </editor-fold>

	// <editor-fold desc="map">

	default @Nonnull OptBool mapToBool(@Nonnull LBytePredicate mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptBool.of(mapping.test(get()))) : OptBool.empty();
	}

	default @Nonnull <K> OptBool mapToBool_(K a1, @Nonnull LObjBytePredicate.LByteObjPred<? super K> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptBool.of(mapping.testByteObj(get(), a1))) : OptBool.empty();
	}

	default @Nonnull <K> OptBool mapToBoolWith(K a1, @Nonnull LObjBytePredicate<? super K> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptBool.of(mapping.test(a1, get()))) : OptBool.empty();
	}

	default @Nonnull <K1, K2> OptBool mapToBool_(K1 a1, K2 a2, @Nonnull LBiObjBytePredicate.LByte2Obj0Obj1Pred<? super K1, ? super K2> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptBool.of(mapping.testByte2Obj0Obj1(get(), a1, a2))) : OptBool.empty();
	}

	default @Nonnull <K1, K2> OptBool mapToBoolWith(K1 a1, K2 a2, @Nonnull LBiObjBytePredicate<? super K1, ? super K2> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptBool.of(mapping.test(a1, a2, get()))) : OptBool.empty();
	}

	default @Nonnull OptByte map(@Nonnull LByteUnaryOperator mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptByte.of(mapping.applyAsByte(get()))) : OptByte.empty();
	}

	default @Nonnull OptDbl mapToDbl(@Nonnull LByteToDblFunction mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptDbl.of(mapping.applyAsDbl(get()))) : OptDbl.empty();
	}

	default @Nonnull OptChar mapToChar(@Nonnull LByteToCharFunction mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptChar.of(mapping.applyAsChar(get()))) : OptChar.empty();
	}

	default @Nonnull OptSrt mapToSrt(@Nonnull LByteToSrtFunction mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptSrt.of(mapping.applyAsSrt(get()))) : OptSrt.empty();
	}

	default @Nonnull OptFlt mapToFlt(@Nonnull LByteToFltFunction mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptFlt.of(mapping.applyAsFlt(get()))) : OptFlt.empty();
	}

	default @Nonnull OptInt mapToInt(@Nonnull LByteToIntFunction mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptInt.of(mapping.applyAsInt(get()))) : OptInt.empty();
	}

	default @Nonnull OptLong mapToLong(@Nonnull LByteToLongFunction mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptLong.of(mapping.applyAsLong(get()))) : OptLong.empty();
	}

	default @Nonnull <R> Opt<R> mapToObj(@Nonnull LByteFunction<? extends R> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (Opt.of(mapping.apply(get()))) : Opt.empty();
	}

	default @Nonnull <R, K> Opt<R> mapToObj_(K a1, @Nonnull LObjByteFunction.LByteObjFunc<? super K, ? extends R> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (Opt.of(mapping.applyByteObj(get(), a1))) : Opt.empty();
	}

	default @Nonnull <R, K> Opt<R> mapToObjWith(K a1, @Nonnull LObjByteFunction<? super K, ? extends R> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (Opt.of(mapping.apply(a1, get()))) : Opt.empty();
	}

	default @Nonnull <R, K1, K2> Opt<R> mapToObj_(K1 a1, K2 a2, @Nonnull LBiObjByteFunction.LByte2Obj0Obj1Func<? super K1, ? super K2, ? extends R> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (Opt.of(mapping.applyByte2Obj0Obj1(get(), a1, a2))) : Opt.empty();
	}

	default @Nonnull <R, K1, K2> Opt<R> mapToObjWith(K1 a1, K2 a2, @Nonnull LBiObjByteFunction<? super K1, ? super K2, ? extends R> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (Opt.of(mapping.apply(a1, a2, get()))) : Opt.empty();
	}

	// </editor-fold>

	// <editor-fold desc="flatMap">

	default @Nonnull OptBool flatMapToBool(@Nonnull LByteFunction<? extends OptBoolTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptBool.from(mapping.apply(get())) : OptBool.empty();
	}

	default @Nonnull <K> OptBool flatMapToBool_(K a1, @Nonnull LObjByteFunction.LByteObjFunc<? super K, ? extends OptBoolTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptBool.from(mapping.applyByteObj(get(), a1)) : OptBool.empty();
	}

	default @Nonnull <K> OptBool flatMapToBoolWith(K a1, @Nonnull LObjByteFunction<? super K, ? extends OptBoolTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptBool.from(mapping.apply(a1, get())) : OptBool.empty();
	}

	default @Nonnull <K1, K2> OptBool flatMapToBool_(K1 a1, K2 a2, @Nonnull LBiObjByteFunction.LByte2Obj0Obj1Func<? super K1, ? super K2, ? extends OptBoolTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptBool.from(mapping.applyByte2Obj0Obj1(get(), a1, a2)) : OptBool.empty();
	}

	default @Nonnull <K1, K2> OptBool flatMapToBoolWith(K1 a1, K2 a2, @Nonnull LBiObjByteFunction<? super K1, ? super K2, ? extends OptBoolTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptBool.from(mapping.apply(a1, a2, get())) : OptBool.empty();
	}

	default @Nonnull OptByte flatMap(@Nonnull LByteFunction<? extends OptByteTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptByte.from(mapping.apply(get())) : OptByte.empty();
	}

	default @Nonnull <K> OptByte flatMap_(K a1, @Nonnull LObjByteFunction.LByteObjFunc<? super K, ? extends OptByteTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptByte.from(mapping.applyByteObj(get(), a1)) : OptByte.empty();
	}

	default @Nonnull <K> OptByte flatMapWith(K a1, @Nonnull LObjByteFunction<? super K, ? extends OptByteTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptByte.from(mapping.apply(a1, get())) : OptByte.empty();
	}

	default @Nonnull <K1, K2> OptByte flatMap_(K1 a1, K2 a2, @Nonnull LBiObjByteFunction.LByte2Obj0Obj1Func<? super K1, ? super K2, ? extends OptByteTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptByte.from(mapping.applyByte2Obj0Obj1(get(), a1, a2)) : OptByte.empty();
	}

	default @Nonnull <K1, K2> OptByte flatMapWith(K1 a1, K2 a2, @Nonnull LBiObjByteFunction<? super K1, ? super K2, ? extends OptByteTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptByte.from(mapping.apply(a1, a2, get())) : OptByte.empty();
	}

	default @Nonnull OptDbl flatMapToDbl(@Nonnull LByteFunction<? extends OptDblTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptDbl.from(mapping.apply(get())) : OptDbl.empty();
	}

	default @Nonnull <K> OptDbl flatMapToDbl_(K a1, @Nonnull LObjByteFunction.LByteObjFunc<? super K, ? extends OptDblTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptDbl.from(mapping.applyByteObj(get(), a1)) : OptDbl.empty();
	}

	default @Nonnull <K> OptDbl flatMapToDblWith(K a1, @Nonnull LObjByteFunction<? super K, ? extends OptDblTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptDbl.from(mapping.apply(a1, get())) : OptDbl.empty();
	}

	default @Nonnull <K1, K2> OptDbl flatMapToDbl_(K1 a1, K2 a2, @Nonnull LBiObjByteFunction.LByte2Obj0Obj1Func<? super K1, ? super K2, ? extends OptDblTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptDbl.from(mapping.applyByte2Obj0Obj1(get(), a1, a2)) : OptDbl.empty();
	}

	default @Nonnull <K1, K2> OptDbl flatMapToDblWith(K1 a1, K2 a2, @Nonnull LBiObjByteFunction<? super K1, ? super K2, ? extends OptDblTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptDbl.from(mapping.apply(a1, a2, get())) : OptDbl.empty();
	}

	default @Nonnull OptChar flatMapToChar(@Nonnull LByteFunction<? extends OptCharTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptChar.from(mapping.apply(get())) : OptChar.empty();
	}

	default @Nonnull <K> OptChar flatMapToChar_(K a1, @Nonnull LObjByteFunction.LByteObjFunc<? super K, ? extends OptCharTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptChar.from(mapping.applyByteObj(get(), a1)) : OptChar.empty();
	}

	default @Nonnull <K> OptChar flatMapToCharWith(K a1, @Nonnull LObjByteFunction<? super K, ? extends OptCharTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptChar.from(mapping.apply(a1, get())) : OptChar.empty();
	}

	default @Nonnull <K1, K2> OptChar flatMapToChar_(K1 a1, K2 a2, @Nonnull LBiObjByteFunction.LByte2Obj0Obj1Func<? super K1, ? super K2, ? extends OptCharTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptChar.from(mapping.applyByte2Obj0Obj1(get(), a1, a2)) : OptChar.empty();
	}

	default @Nonnull <K1, K2> OptChar flatMapToCharWith(K1 a1, K2 a2, @Nonnull LBiObjByteFunction<? super K1, ? super K2, ? extends OptCharTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptChar.from(mapping.apply(a1, a2, get())) : OptChar.empty();
	}

	default @Nonnull OptSrt flatMapToSrt(@Nonnull LByteFunction<? extends OptSrtTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptSrt.from(mapping.apply(get())) : OptSrt.empty();
	}

	default @Nonnull <K> OptSrt flatMapToSrt_(K a1, @Nonnull LObjByteFunction.LByteObjFunc<? super K, ? extends OptSrtTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptSrt.from(mapping.applyByteObj(get(), a1)) : OptSrt.empty();
	}

	default @Nonnull <K> OptSrt flatMapToSrtWith(K a1, @Nonnull LObjByteFunction<? super K, ? extends OptSrtTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptSrt.from(mapping.apply(a1, get())) : OptSrt.empty();
	}

	default @Nonnull <K1, K2> OptSrt flatMapToSrt_(K1 a1, K2 a2, @Nonnull LBiObjByteFunction.LByte2Obj0Obj1Func<? super K1, ? super K2, ? extends OptSrtTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptSrt.from(mapping.applyByte2Obj0Obj1(get(), a1, a2)) : OptSrt.empty();
	}

	default @Nonnull <K1, K2> OptSrt flatMapToSrtWith(K1 a1, K2 a2, @Nonnull LBiObjByteFunction<? super K1, ? super K2, ? extends OptSrtTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptSrt.from(mapping.apply(a1, a2, get())) : OptSrt.empty();
	}

	default @Nonnull OptFlt flatMapToFlt(@Nonnull LByteFunction<? extends OptFltTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptFlt.from(mapping.apply(get())) : OptFlt.empty();
	}

	default @Nonnull <K> OptFlt flatMapToFlt_(K a1, @Nonnull LObjByteFunction.LByteObjFunc<? super K, ? extends OptFltTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptFlt.from(mapping.applyByteObj(get(), a1)) : OptFlt.empty();
	}

	default @Nonnull <K> OptFlt flatMapToFltWith(K a1, @Nonnull LObjByteFunction<? super K, ? extends OptFltTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptFlt.from(mapping.apply(a1, get())) : OptFlt.empty();
	}

	default @Nonnull <K1, K2> OptFlt flatMapToFlt_(K1 a1, K2 a2, @Nonnull LBiObjByteFunction.LByte2Obj0Obj1Func<? super K1, ? super K2, ? extends OptFltTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptFlt.from(mapping.applyByte2Obj0Obj1(get(), a1, a2)) : OptFlt.empty();
	}

	default @Nonnull <K1, K2> OptFlt flatMapToFltWith(K1 a1, K2 a2, @Nonnull LBiObjByteFunction<? super K1, ? super K2, ? extends OptFltTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptFlt.from(mapping.apply(a1, a2, get())) : OptFlt.empty();
	}

	default @Nonnull OptInt flatMapToInt(@Nonnull LByteFunction<? extends OptIntTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptInt.from(mapping.apply(get())) : OptInt.empty();
	}

	default @Nonnull <K> OptInt flatMapToInt_(K a1, @Nonnull LObjByteFunction.LByteObjFunc<? super K, ? extends OptIntTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptInt.from(mapping.applyByteObj(get(), a1)) : OptInt.empty();
	}

	default @Nonnull <K> OptInt flatMapToIntWith(K a1, @Nonnull LObjByteFunction<? super K, ? extends OptIntTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptInt.from(mapping.apply(a1, get())) : OptInt.empty();
	}

	default @Nonnull <K1, K2> OptInt flatMapToInt_(K1 a1, K2 a2, @Nonnull LBiObjByteFunction.LByte2Obj0Obj1Func<? super K1, ? super K2, ? extends OptIntTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptInt.from(mapping.applyByte2Obj0Obj1(get(), a1, a2)) : OptInt.empty();
	}

	default @Nonnull <K1, K2> OptInt flatMapToIntWith(K1 a1, K2 a2, @Nonnull LBiObjByteFunction<? super K1, ? super K2, ? extends OptIntTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptInt.from(mapping.apply(a1, a2, get())) : OptInt.empty();
	}

	default @Nonnull OptLong flatMapToLong(@Nonnull LByteFunction<? extends OptLongTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptLong.from(mapping.apply(get())) : OptLong.empty();
	}

	default @Nonnull <K> OptLong flatMapToLong_(K a1, @Nonnull LObjByteFunction.LByteObjFunc<? super K, ? extends OptLongTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptLong.from(mapping.applyByteObj(get(), a1)) : OptLong.empty();
	}

	default @Nonnull <K> OptLong flatMapToLongWith(K a1, @Nonnull LObjByteFunction<? super K, ? extends OptLongTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptLong.from(mapping.apply(a1, get())) : OptLong.empty();
	}

	default @Nonnull <K1, K2> OptLong flatMapToLong_(K1 a1, K2 a2, @Nonnull LBiObjByteFunction.LByte2Obj0Obj1Func<? super K1, ? super K2, ? extends OptLongTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptLong.from(mapping.applyByte2Obj0Obj1(get(), a1, a2)) : OptLong.empty();
	}

	default @Nonnull <K1, K2> OptLong flatMapToLongWith(K1 a1, K2 a2, @Nonnull LBiObjByteFunction<? super K1, ? super K2, ? extends OptLongTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptLong.from(mapping.apply(a1, a2, get())) : OptLong.empty();
	}

	default @Nonnull <R> Opt<R> flatMapToObj(@Nonnull LByteFunction<? extends OptTrait<? extends R, ?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? Opt.from(mapping.apply(get())) : Opt.empty();
	}

	default @Nonnull <R, K> Opt<R> flatMapToObj_(K a1, @Nonnull LObjByteFunction.LByteObjFunc<? super K, ? extends OptTrait<? extends R, ?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? Opt.from(mapping.applyByteObj(get(), a1)) : Opt.empty();
	}

	default @Nonnull <R, K> Opt<R> flatMapToObjWith(K a1, @Nonnull LObjByteFunction<? super K, ? extends OptTrait<? extends R, ?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? Opt.from(mapping.apply(a1, get())) : Opt.empty();
	}

	default @Nonnull <R, K1, K2> Opt<R> flatMapToObj_(K1 a1, K2 a2, @Nonnull LBiObjByteFunction.LByte2Obj0Obj1Func<? super K1, ? super K2, ? extends OptTrait<? extends R, ?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? Opt.from(mapping.applyByte2Obj0Obj1(get(), a1, a2)) : Opt.empty();
	}

	default @Nonnull <R, K1, K2> Opt<R> flatMapToObjWith(K1 a1, K2 a2, @Nonnull LBiObjByteFunction<? super K1, ? super K2, ? extends OptTrait<? extends R, ?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? Opt.from(mapping.apply(a1, a2, get())) : Opt.empty();
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
		return self();
	}

	default SELF ifPresent(@Nonnull LByteConsumer action) {
		Null.nonNullArg(action, "action");
		if (isPresent()) {
			action.accept(get());
		}
		return self();
	}

	default SELF ifPresent(@Nonnull LByteConsumer action, @Nonnull LAction emptyAction) {
		Null.nonNullArg(action, "action");
		if (isPresent()) {
			action.accept(get());
		} else {
			emptyAction.execute();
		}
		return self();
	}

	default <K> SELF ifPresent_(K a1, @Nonnull LObjByteConsumer.LByteObjCons<? super K> action) {
		Null.nonNullArg(action, "action");
		if (isPresent()) {
			action.acceptByteObj(get(), a1);
		}
		return self();
	}

	default <K> SELF ifPresentWith(K a1, @Nonnull LObjByteConsumer<? super K> action) {
		Null.nonNullArg(action, "action");
		if (isPresent()) {
			action.accept(a1, get());
		}
		return self();
	}

	default <K1, K2> SELF ifPresentWith(K1 a1, K2 a2, @Nonnull LBiObjByteConsumer<? super K1, ? super K2> action) {
		Null.nonNullArg(action, "action");
		if (isPresent()) {
			action.accept(a1, a2, get());
		}
		return self();
	}

	// </editor-fold>

	/** Compared to ifPresent it will simply fails if there is no value */
	default @Nonnull SELF visit(@Nonnull LByteConsumer consumer) {
		Null.nonNullArg(consumer, "consumer");
		consumer.accept(get());
		return self();
	}

	// <editor-fold desc="orElse">

	default @Nonnull SELF orThrow() {
		if (isPresent()) {
			return self();
		}

		throw Handling.create(X::noSuchElement);
	}

	default @Nonnull SELF orThrow(@Nonnull ExF<RuntimeException> fx) {
		if (isPresent()) {
			return self();
		}

		Null.nonNullArg(fx, "fx");
		throw Handling.create(fx);
	}

	default @Nonnull SELF orThrow(@Nonnull ExMF<RuntimeException> fx, @Nullable String msg) {
		if (isPresent()) {
			return self();
		}

		Null.nonNullArg(fx, "fx");
		throw Handling.create(fx, msg);
	}

	default @Nonnull SELF orThrow(@Nonnull ExMF<RuntimeException> fx, @Nullable String msg, @Nullable Object param1) {
		if (isPresent()) {
			return self();
		}

		Null.nonNullArg(fx, "fx");
		throw Handling.create(fx, msg, param1);
	}

	default @Nonnull SELF orThrow(@Nonnull ExMF<RuntimeException> fx, @Nullable String msg, @Nullable Object param1, @Nullable Object param2) {
		if (isPresent()) {
			return self();
		}

		Null.nonNullArg(fx, "fx");
		throw Handling.create(fx, msg, param1, param2);
	}

	default @Nonnull SELF orThrow(@Nonnull ExMF<RuntimeException> fx, @Nullable String msg, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		if (isPresent()) {
			return self();
		}

		Null.nonNullArg(fx, "fx");
		throw Handling.create(fx, msg, param1, param2, param3);
	}

	default byte orElseThrow() {
		if (isPresent()) {
			return get();
		}

		throw Handling.create(X::noSuchElement);
	}

	default byte orElseThrow(@Nonnull ExF<RuntimeException> fx) {
		if (isPresent()) {
			return get();
		}

		Null.nonNullArg(fx, "fx");
		throw Handling.create(fx);
	}

	default byte orElseThrow(@Nonnull ExMF<RuntimeException> fx, @Nullable String msg) {
		if (isPresent()) {
			return get();
		}

		Null.nonNullArg(fx, "fx");
		throw Handling.create(fx, msg);
	}

	default byte orElseThrow(@Nonnull ExMF<RuntimeException> fx, @Nullable String msg, @Nullable Object param1) {
		if (isPresent()) {
			return get();
		}

		Null.nonNullArg(fx, "fx");
		throw Handling.create(fx, msg, param1);
	}

	default byte orElseThrow(@Nonnull ExMF<RuntimeException> fx, @Nullable String msg, @Nullable Object param1, @Nullable Object param2) {
		if (isPresent()) {
			return get();
		}

		Null.nonNullArg(fx, "fx");
		throw Handling.create(fx, msg, param1, param2);
	}

	default byte orElseThrow(@Nonnull ExMF<RuntimeException> fx, @Nullable String msg, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		if (isPresent()) {
			return get();
		}

		Null.nonNullArg(fx, "fx");
		throw Handling.create(fx, msg, param1, param2, param3);
	}

	default byte orElse(@Nullable byte value) {
		return isPresent() ? get() : value;
	}

	default byte orElseGet(@Nonnull LByteSupplier supplier) {
		Null.nonNullArg(supplier, "supplier");
		return isPresent() ? get() : supplier.getAsByte();
	}

	default SELF orGet(@Nonnull LByteSupplier supplier) {
		Null.nonNullArg(supplier, "supplier");
		return isPresent() ? self() : value(supplier.getAsByte());
	}

	default SELF orFlatGet(@Nonnull LSupplier<? extends OptByteTrait<?>> supplier) {
		Null.nonNullArg(supplier, "supplier");
		return isPresent() ? self() : valueFrom(supplier.get());
	}

	default SELF or(@Nullable byte value) {
		return isPresent() ? self() : value(value);
	}

	default SELF orOpt(@Nonnull OptByteTrait<?> opt) {
		Null.nonNullArg(opt, "opt");
		return isPresent() ? self() : valueFrom(opt);
	}

	default <K> byte orElseApply(K a1, @Nonnull LToByteFunction<? super K> supplier) {
		Null.nonNullArg(supplier, "supplier");
		return isPresent() ? get() : supplier.applyAsByte(a1);
	}

	default <K> SELF orApply(K a1, @Nonnull LToByteFunction<? super K> supplier) {
		Null.nonNullArg(supplier, "supplier");
		return isPresent() ? self() : value(supplier.applyAsByte(a1));
	}

	default <K> SELF orFlatApply(K a1, @Nonnull LFunction<? super K, ? extends OptByteTrait<?>> supplier) {
		Null.nonNullArg(supplier, "supplier");
		return isPresent() ? self() : valueFrom(supplier.apply(a1));
	}

	default <K1, K2> byte orElseApply(K1 a1, K2 a2, @Nonnull LToByteBiFunction<? super K1, ? super K2> supplier) {
		Null.nonNullArg(supplier, "supplier");
		return isPresent() ? get() : supplier.applyAsByte(a1, a2);
	}

	default <K1, K2> SELF orApply(K1 a1, K2 a2, @Nonnull LToByteBiFunction<? super K1, ? super K2> supplier) {
		Null.nonNullArg(supplier, "supplier");
		return isPresent() ? self() : value(supplier.applyAsByte(a1, a2));
	}

	default <K1, K2> SELF orFlatApply(K1 a1, K2 a2, @Nonnull LBiFunction<? super K1, ? super K2, ? extends OptByteTrait<?>> supplier) {
		Null.nonNullArg(supplier, "supplier");
		return isPresent() ? self() : valueFrom(supplier.apply(a1, a2));
	}

	default <K1, K2, K3> SELF orFlatApply(K1 a1, K2 a2, K3 a3, @Nonnull LTriFunction<? super K1, ? super K2, ? super K3, ? extends OptByteTrait<?>> supplier) {
		Null.nonNullArg(supplier, "supplier");
		return isPresent() ? self() : valueFrom(supplier.apply(a1, a2, a3));
	}

	// </editor-fold>

	default OptionalInt toOptional() {
		return isPresent() ? OptionalInt.of(value()) : OptionalInt.empty();
	}

}
