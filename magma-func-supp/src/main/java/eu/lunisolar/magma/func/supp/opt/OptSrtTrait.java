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
public interface OptSrtTrait<SELF extends OptSrtTrait<SELF>> extends FluentTrait<SELF>, aValue<aShort>, CheckSrtTrait<SELF>, FilterSrtSingleTrait<SELF>, IsSrtTrait<SELF>, DoIfSrtSingleTrait<SELF>, UseSrtSingleTrait<SELF> {

	// <editor-fold desc="forcing ValueTrait re-implementation">

	@Override
	@Nonnull
	SELF value(short value);

	@Override
	@Nonnull
	SELF voidValue();

	// </editor-fold>

	short get();

	default @Nullable Short nullable() {
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
	default boolean is(@Nonnull LSrtPredicate predicate) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && FilterSrtSingleTrait.super.is(predicate);
	}

	@Override
	default boolean isNot(@Nonnull LSrtPredicate predicate) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && FilterSrtSingleTrait.super.isNot(predicate);
	}

	@Override
	default boolean is(short a2, @Nonnull LBiSrtPredicate predicate) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && FilterSrtSingleTrait.super.is(a2, predicate);
	}

	@Override
	default boolean isNot(short a2, @Nonnull LBiSrtPredicate predicate) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && FilterSrtSingleTrait.super.isNot(a2, predicate);
	}

	@Override
	default boolean is(short a2, short a3, @Nonnull LTriSrtPredicate predicate) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && FilterSrtSingleTrait.super.is(a2, a3, predicate);
	}

	@Override
	default boolean isNot(short a2, short a3, @Nonnull LTriSrtPredicate predicate) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && FilterSrtSingleTrait.super.isNot(a2, a3, predicate);
	}

	@Override
	default boolean isInt(int v, @Nonnull LSrtIntPredicate predicate) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && FilterSrtSingleTrait.super.isInt(v, predicate);
	}

	@Override
	default boolean isNotInt(int v, @Nonnull LSrtIntPredicate predicate) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && FilterSrtSingleTrait.super.isNotInt(v, predicate);
	}

	@Override
	default <V> boolean is_(V v, @Nonnull LObjSrtPredicate.LSrtObjPred<? super V> predicate) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && FilterSrtSingleTrait.super.is_(v, predicate);
	}

	@Override
	default <V> boolean isNot_(V v, @Nonnull LObjSrtPredicate.LSrtObjPred<? super V> predicate) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && FilterSrtSingleTrait.super.isNot_(v, predicate);
	}

	@Override
	default <V1> boolean isWithSrt(V1 with1, @Nonnull LObjSrtPredicate<? super V1> predicate) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && FilterSrtSingleTrait.super.isWithSrt(with1, predicate);
	}

	@Override
	default <V1> boolean isNotWithSrt(V1 with1, @Nonnull LObjSrtPredicate<? super V1> predicate) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && FilterSrtSingleTrait.super.isNotWithSrt(with1, predicate);
	}

	@Override
	default <V1, V2> boolean isWith(V1 with1, V2 with2, @Nonnull LBiObjSrtPredicate<? super V1, ? super V2> predicate) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && FilterSrtSingleTrait.super.isWith(with1, with2, predicate);
	}

	@Override
	default <V1, V2> boolean isNotWith(V1 with1, V2 with2, @Nonnull LBiObjSrtPredicate<? super V1, ? super V2> predicate) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && FilterSrtSingleTrait.super.isNotWith(with1, with2, predicate);
	}

	// </editor-fold>

	// <editor-fold desc="filtering">

	// </editor-fold>

	default SELF butNot(short value) {
		return isPresent() ? (value() == value ? voidValue() : self()) : voidValue();
	}

	// <editor-fold desc="map">

	default @Nonnull OptBool mapToBool(@Nonnull LSrtPredicate mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptBool.of(mapping.test(get()))) : OptBool.empty();
	}

	default @Nonnull <K> OptBool mapToBool_(K a1, @Nonnull LObjSrtPredicate.LSrtObjPred<? super K> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptBool.of(mapping.testSrtObj(get(), a1))) : OptBool.empty();
	}

	default @Nonnull <K> OptBool mapToBoolWith(K a1, @Nonnull LObjSrtPredicate<? super K> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptBool.of(mapping.test(a1, get()))) : OptBool.empty();
	}

	default @Nonnull <K1, K2> OptBool mapToBool_(K1 a1, K2 a2, @Nonnull LBiObjSrtPredicate.LSrt2Obj0Obj1Pred<? super K1, ? super K2> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptBool.of(mapping.testSrt2Obj0Obj1(get(), a1, a2))) : OptBool.empty();
	}

	default @Nonnull <K1, K2> OptBool mapToBoolWith(K1 a1, K2 a2, @Nonnull LBiObjSrtPredicate<? super K1, ? super K2> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptBool.of(mapping.test(a1, a2, get()))) : OptBool.empty();
	}

	default @Nonnull OptByte mapToByte(@Nonnull LSrtToByteFunction mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptByte.of(mapping.applyAsByte(get()))) : OptByte.empty();
	}

	default @Nonnull OptDbl mapToDbl(@Nonnull LSrtToDblFunction mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptDbl.of(mapping.applyAsDbl(get()))) : OptDbl.empty();
	}

	default @Nonnull OptChar mapToChar(@Nonnull LSrtToCharFunction mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptChar.of(mapping.applyAsChar(get()))) : OptChar.empty();
	}

	default @Nonnull OptSrt map(@Nonnull LSrtUnaryOperator mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptSrt.of(mapping.applyAsSrt(get()))) : OptSrt.empty();
	}

	default @Nonnull OptFlt mapToFlt(@Nonnull LSrtToFltFunction mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptFlt.of(mapping.applyAsFlt(get()))) : OptFlt.empty();
	}

	default @Nonnull OptInt mapToInt(@Nonnull LSrtToIntFunction mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptInt.of(mapping.applyAsInt(get()))) : OptInt.empty();
	}

	default @Nonnull OptLong mapToLong(@Nonnull LSrtToLongFunction mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptLong.of(mapping.applyAsLong(get()))) : OptLong.empty();
	}

	default @Nonnull <R> Opt<R> mapToObj(@Nonnull LSrtFunction<? extends R> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (Opt.of(mapping.apply(get()))) : Opt.empty();
	}

	default @Nonnull <R, K> Opt<R> mapToObj_(K a1, @Nonnull LObjSrtFunction.LSrtObjFunc<? super K, ? extends R> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (Opt.of(mapping.applySrtObj(get(), a1))) : Opt.empty();
	}

	default @Nonnull <R, K> Opt<R> mapToObjWith(K a1, @Nonnull LObjSrtFunction<? super K, ? extends R> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (Opt.of(mapping.apply(a1, get()))) : Opt.empty();
	}

	default @Nonnull <R, K1, K2> Opt<R> mapToObj_(K1 a1, K2 a2, @Nonnull LBiObjSrtFunction.LSrt2Obj0Obj1Func<? super K1, ? super K2, ? extends R> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (Opt.of(mapping.applySrt2Obj0Obj1(get(), a1, a2))) : Opt.empty();
	}

	default @Nonnull <R, K1, K2> Opt<R> mapToObjWith(K1 a1, K2 a2, @Nonnull LBiObjSrtFunction<? super K1, ? super K2, ? extends R> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (Opt.of(mapping.apply(a1, a2, get()))) : Opt.empty();
	}

	// </editor-fold>

	// <editor-fold desc="flatMap">

	default @Nonnull OptBool flatMapToBool(@Nonnull LSrtFunction<? extends OptBoolTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptBool.from(mapping.apply(get())) : OptBool.empty();
	}

	default @Nonnull <K> OptBool flatMapToBool_(K a1, @Nonnull LObjSrtFunction.LSrtObjFunc<? super K, ? extends OptBoolTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptBool.from(mapping.applySrtObj(get(), a1)) : OptBool.empty();
	}

	default @Nonnull <K> OptBool flatMapToBoolWith(K a1, @Nonnull LObjSrtFunction<? super K, ? extends OptBoolTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptBool.from(mapping.apply(a1, get())) : OptBool.empty();
	}

	default @Nonnull <K1, K2> OptBool flatMapToBool_(K1 a1, K2 a2, @Nonnull LBiObjSrtFunction.LSrt2Obj0Obj1Func<? super K1, ? super K2, ? extends OptBoolTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptBool.from(mapping.applySrt2Obj0Obj1(get(), a1, a2)) : OptBool.empty();
	}

	default @Nonnull <K1, K2> OptBool flatMapToBoolWith(K1 a1, K2 a2, @Nonnull LBiObjSrtFunction<? super K1, ? super K2, ? extends OptBoolTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptBool.from(mapping.apply(a1, a2, get())) : OptBool.empty();
	}

	default @Nonnull OptByte flatMapToByte(@Nonnull LSrtFunction<? extends OptByteTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptByte.from(mapping.apply(get())) : OptByte.empty();
	}

	default @Nonnull <K> OptByte flatMapToByte_(K a1, @Nonnull LObjSrtFunction.LSrtObjFunc<? super K, ? extends OptByteTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptByte.from(mapping.applySrtObj(get(), a1)) : OptByte.empty();
	}

	default @Nonnull <K> OptByte flatMapToByteWith(K a1, @Nonnull LObjSrtFunction<? super K, ? extends OptByteTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptByte.from(mapping.apply(a1, get())) : OptByte.empty();
	}

	default @Nonnull <K1, K2> OptByte flatMapToByte_(K1 a1, K2 a2, @Nonnull LBiObjSrtFunction.LSrt2Obj0Obj1Func<? super K1, ? super K2, ? extends OptByteTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptByte.from(mapping.applySrt2Obj0Obj1(get(), a1, a2)) : OptByte.empty();
	}

	default @Nonnull <K1, K2> OptByte flatMapToByteWith(K1 a1, K2 a2, @Nonnull LBiObjSrtFunction<? super K1, ? super K2, ? extends OptByteTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptByte.from(mapping.apply(a1, a2, get())) : OptByte.empty();
	}

	default @Nonnull OptDbl flatMapToDbl(@Nonnull LSrtFunction<? extends OptDblTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptDbl.from(mapping.apply(get())) : OptDbl.empty();
	}

	default @Nonnull <K> OptDbl flatMapToDbl_(K a1, @Nonnull LObjSrtFunction.LSrtObjFunc<? super K, ? extends OptDblTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptDbl.from(mapping.applySrtObj(get(), a1)) : OptDbl.empty();
	}

	default @Nonnull <K> OptDbl flatMapToDblWith(K a1, @Nonnull LObjSrtFunction<? super K, ? extends OptDblTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptDbl.from(mapping.apply(a1, get())) : OptDbl.empty();
	}

	default @Nonnull <K1, K2> OptDbl flatMapToDbl_(K1 a1, K2 a2, @Nonnull LBiObjSrtFunction.LSrt2Obj0Obj1Func<? super K1, ? super K2, ? extends OptDblTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptDbl.from(mapping.applySrt2Obj0Obj1(get(), a1, a2)) : OptDbl.empty();
	}

	default @Nonnull <K1, K2> OptDbl flatMapToDblWith(K1 a1, K2 a2, @Nonnull LBiObjSrtFunction<? super K1, ? super K2, ? extends OptDblTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptDbl.from(mapping.apply(a1, a2, get())) : OptDbl.empty();
	}

	default @Nonnull OptChar flatMapToChar(@Nonnull LSrtFunction<? extends OptCharTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptChar.from(mapping.apply(get())) : OptChar.empty();
	}

	default @Nonnull <K> OptChar flatMapToChar_(K a1, @Nonnull LObjSrtFunction.LSrtObjFunc<? super K, ? extends OptCharTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptChar.from(mapping.applySrtObj(get(), a1)) : OptChar.empty();
	}

	default @Nonnull <K> OptChar flatMapToCharWith(K a1, @Nonnull LObjSrtFunction<? super K, ? extends OptCharTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptChar.from(mapping.apply(a1, get())) : OptChar.empty();
	}

	default @Nonnull <K1, K2> OptChar flatMapToChar_(K1 a1, K2 a2, @Nonnull LBiObjSrtFunction.LSrt2Obj0Obj1Func<? super K1, ? super K2, ? extends OptCharTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptChar.from(mapping.applySrt2Obj0Obj1(get(), a1, a2)) : OptChar.empty();
	}

	default @Nonnull <K1, K2> OptChar flatMapToCharWith(K1 a1, K2 a2, @Nonnull LBiObjSrtFunction<? super K1, ? super K2, ? extends OptCharTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptChar.from(mapping.apply(a1, a2, get())) : OptChar.empty();
	}

	default @Nonnull OptSrt flatMap(@Nonnull LSrtFunction<? extends OptSrtTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptSrt.from(mapping.apply(get())) : OptSrt.empty();
	}

	default @Nonnull <K> OptSrt flatMap_(K a1, @Nonnull LObjSrtFunction.LSrtObjFunc<? super K, ? extends OptSrtTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptSrt.from(mapping.applySrtObj(get(), a1)) : OptSrt.empty();
	}

	default @Nonnull <K> OptSrt flatMapWith(K a1, @Nonnull LObjSrtFunction<? super K, ? extends OptSrtTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptSrt.from(mapping.apply(a1, get())) : OptSrt.empty();
	}

	default @Nonnull <K1, K2> OptSrt flatMap_(K1 a1, K2 a2, @Nonnull LBiObjSrtFunction.LSrt2Obj0Obj1Func<? super K1, ? super K2, ? extends OptSrtTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptSrt.from(mapping.applySrt2Obj0Obj1(get(), a1, a2)) : OptSrt.empty();
	}

	default @Nonnull <K1, K2> OptSrt flatMapWith(K1 a1, K2 a2, @Nonnull LBiObjSrtFunction<? super K1, ? super K2, ? extends OptSrtTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptSrt.from(mapping.apply(a1, a2, get())) : OptSrt.empty();
	}

	default @Nonnull OptFlt flatMapToFlt(@Nonnull LSrtFunction<? extends OptFltTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptFlt.from(mapping.apply(get())) : OptFlt.empty();
	}

	default @Nonnull <K> OptFlt flatMapToFlt_(K a1, @Nonnull LObjSrtFunction.LSrtObjFunc<? super K, ? extends OptFltTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptFlt.from(mapping.applySrtObj(get(), a1)) : OptFlt.empty();
	}

	default @Nonnull <K> OptFlt flatMapToFltWith(K a1, @Nonnull LObjSrtFunction<? super K, ? extends OptFltTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptFlt.from(mapping.apply(a1, get())) : OptFlt.empty();
	}

	default @Nonnull <K1, K2> OptFlt flatMapToFlt_(K1 a1, K2 a2, @Nonnull LBiObjSrtFunction.LSrt2Obj0Obj1Func<? super K1, ? super K2, ? extends OptFltTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptFlt.from(mapping.applySrt2Obj0Obj1(get(), a1, a2)) : OptFlt.empty();
	}

	default @Nonnull <K1, K2> OptFlt flatMapToFltWith(K1 a1, K2 a2, @Nonnull LBiObjSrtFunction<? super K1, ? super K2, ? extends OptFltTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptFlt.from(mapping.apply(a1, a2, get())) : OptFlt.empty();
	}

	default @Nonnull OptInt flatMapToInt(@Nonnull LSrtFunction<? extends OptIntTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptInt.from(mapping.apply(get())) : OptInt.empty();
	}

	default @Nonnull <K> OptInt flatMapToInt_(K a1, @Nonnull LObjSrtFunction.LSrtObjFunc<? super K, ? extends OptIntTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptInt.from(mapping.applySrtObj(get(), a1)) : OptInt.empty();
	}

	default @Nonnull <K> OptInt flatMapToIntWith(K a1, @Nonnull LObjSrtFunction<? super K, ? extends OptIntTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptInt.from(mapping.apply(a1, get())) : OptInt.empty();
	}

	default @Nonnull <K1, K2> OptInt flatMapToInt_(K1 a1, K2 a2, @Nonnull LBiObjSrtFunction.LSrt2Obj0Obj1Func<? super K1, ? super K2, ? extends OptIntTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptInt.from(mapping.applySrt2Obj0Obj1(get(), a1, a2)) : OptInt.empty();
	}

	default @Nonnull <K1, K2> OptInt flatMapToIntWith(K1 a1, K2 a2, @Nonnull LBiObjSrtFunction<? super K1, ? super K2, ? extends OptIntTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptInt.from(mapping.apply(a1, a2, get())) : OptInt.empty();
	}

	default @Nonnull OptLong flatMapToLong(@Nonnull LSrtFunction<? extends OptLongTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptLong.from(mapping.apply(get())) : OptLong.empty();
	}

	default @Nonnull <K> OptLong flatMapToLong_(K a1, @Nonnull LObjSrtFunction.LSrtObjFunc<? super K, ? extends OptLongTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptLong.from(mapping.applySrtObj(get(), a1)) : OptLong.empty();
	}

	default @Nonnull <K> OptLong flatMapToLongWith(K a1, @Nonnull LObjSrtFunction<? super K, ? extends OptLongTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptLong.from(mapping.apply(a1, get())) : OptLong.empty();
	}

	default @Nonnull <K1, K2> OptLong flatMapToLong_(K1 a1, K2 a2, @Nonnull LBiObjSrtFunction.LSrt2Obj0Obj1Func<? super K1, ? super K2, ? extends OptLongTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptLong.from(mapping.applySrt2Obj0Obj1(get(), a1, a2)) : OptLong.empty();
	}

	default @Nonnull <K1, K2> OptLong flatMapToLongWith(K1 a1, K2 a2, @Nonnull LBiObjSrtFunction<? super K1, ? super K2, ? extends OptLongTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptLong.from(mapping.apply(a1, a2, get())) : OptLong.empty();
	}

	default @Nonnull <R> Opt<R> flatMapToObj(@Nonnull LSrtFunction<? extends ValueTrait<? extends R, ?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? Opt.from(mapping.apply(get())) : Opt.empty();
	}

	default @Nonnull <R, K> Opt<R> flatMapToObj_(K a1, @Nonnull LObjSrtFunction.LSrtObjFunc<? super K, ? extends ValueTrait<? extends R, ?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? Opt.from(mapping.applySrtObj(get(), a1)) : Opt.empty();
	}

	default @Nonnull <R, K> Opt<R> flatMapToObjWith(K a1, @Nonnull LObjSrtFunction<? super K, ? extends ValueTrait<? extends R, ?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? Opt.from(mapping.apply(a1, get())) : Opt.empty();
	}

	default @Nonnull <R, K1, K2> Opt<R> flatMapToObj_(K1 a1, K2 a2, @Nonnull LBiObjSrtFunction.LSrt2Obj0Obj1Func<? super K1, ? super K2, ? extends ValueTrait<? extends R, ?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? Opt.from(mapping.applySrt2Obj0Obj1(get(), a1, a2)) : Opt.empty();
	}

	default @Nonnull <R, K1, K2> Opt<R> flatMapToObjWith(K1 a1, K2 a2, @Nonnull LBiObjSrtFunction<? super K1, ? super K2, ? extends ValueTrait<? extends R, ?>> mapping) {
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

	default SELF ifPresent(@Nonnull LSrtConsumer action) {
		Null.nonNullArg(action, "action");
		if (isPresent()) {
			action.accept(get());
		}
		return self();
	}

	default SELF ifPresent(@Nonnull LSrtConsumer action, @Nonnull LAction emptyAction) {
		Null.nonNullArg(action, "action");
		if (isPresent()) {
			action.accept(get());
		} else {
			emptyAction.execute();
		}
		return self();
	}

	default <K1> SELF ifPresent_(K1 a2, @Nonnull LObjSrtConsumer.LSrtObjCons<? super K1> action) {
		Null.nonNullArg(action, "action");
		if (isPresent()) {
			action.acceptSrtObj(get(), a2);
		}
		return self();
	}

	default <K1> SELF ifPresent_(K1 a2, @Nonnull LObjSrtConsumer.LSrtObjCons<? super K1> action, @Nonnull LAction emptyAction) {
		Null.nonNullArg(action, "action");
		if (isPresent()) {
			action.acceptSrtObj(get(), a2);
		} else {
			emptyAction.execute();
		}
		return self();
	}

	default <K1, K2> SELF ifPresent_(K1 a2, K2 a3, @Nonnull LBiObjSrtConsumer.LSrt2Obj0Obj1Cons<? super K1, ? super K2> action) {
		Null.nonNullArg(action, "action");
		if (isPresent()) {
			action.acceptSrt2Obj0Obj1(get(), a2, a3);
		}
		return self();
	}

	default <K1, K2> SELF ifPresent_(K1 a2, K2 a3, @Nonnull LBiObjSrtConsumer.LSrt2Obj0Obj1Cons<? super K1, ? super K2> action, @Nonnull LAction emptyAction) {
		Null.nonNullArg(action, "action");
		if (isPresent()) {
			action.acceptSrt2Obj0Obj1(get(), a2, a3);
		} else {
			emptyAction.execute();
		}
		return self();
	}

	default SELF ifPresentWith(@Nonnull LSrtConsumer action) {
		Null.nonNullArg(action, "action");
		if (isPresent()) {
			action.accept(get());
		}
		return self();
	}

	default SELF ifPresentWith(@Nonnull LSrtConsumer action, @Nonnull LAction emptyAction) {
		Null.nonNullArg(action, "action");
		if (isPresent()) {
			action.accept(get());
		} else {
			emptyAction.execute();
		}
		return self();
	}

	default <K1> SELF ifPresentWith(K1 a1, @Nonnull LObjSrtConsumer<? super K1> action) {
		Null.nonNullArg(action, "action");
		if (isPresent()) {
			action.accept(a1, get());
		}
		return self();
	}

	default <K1> SELF ifPresentWith(K1 a1, @Nonnull LObjSrtConsumer<? super K1> action, @Nonnull LAction emptyAction) {
		Null.nonNullArg(action, "action");
		if (isPresent()) {
			action.accept(a1, get());
		} else {
			emptyAction.execute();
		}
		return self();
	}

	default <K1, K2> SELF ifPresentWith(K1 a1, K2 a2, @Nonnull LBiObjSrtConsumer<? super K1, ? super K2> action) {
		Null.nonNullArg(action, "action");
		if (isPresent()) {
			action.accept(a1, a2, get());
		}
		return self();
	}

	default <K1, K2> SELF ifPresentWith(K1 a1, K2 a2, @Nonnull LBiObjSrtConsumer<? super K1, ? super K2> action, @Nonnull LAction emptyAction) {
		Null.nonNullArg(action, "action");
		if (isPresent()) {
			action.accept(a1, a2, get());
		} else {
			emptyAction.execute();
		}
		return self();
	}

	default SELF ifExists(@Nonnull LConsumer<? super SELF> action) {
		Null.nonNullArg(action, "action");
		if (isPresent()) {
			action.accept(self());
		}
		return self();
	}

	default <T2> SELF ifExists(T2 a2, @Nonnull LBiConsumer<? super SELF, ? super T2> action) {
		Null.nonNullArg(action, "action");
		if (isPresent()) {
			action.accept(self(), a2);
		}
		return self();
	}

	default <T2, T3> SELF ifExists(T2 a2, T3 a3, @Nonnull LTriConsumer<? super SELF, ? super T2, ? super T3> action) {
		Null.nonNullArg(action, "action");
		if (isPresent()) {
			action.accept(self(), a2, a3);
		}
		return self();
	}

	default <T2, T4, T5> SELF ifExists(T2 a2, T4 a3, T5 a4, @Nonnull LQuadConsumer<? super SELF, ? super T2, ? super T4, ? super T5> action) {
		Null.nonNullArg(action, "action");
		if (isPresent()) {
			action.accept(self(), a2, a3, a4);
		}
		return self();
	}

	// </editor-fold>

	/** Compared to ifPresent it will simply fails if there is no value */
	default @Nonnull SELF visit(@Nonnull LSrtConsumer consumer) {
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

	default short orElseThrow() {
		if (isPresent()) {
			return get();
		}

		throw Handling.create(X::noSuchElement);
	}

	default short orElseThrow(@Nonnull ExF<RuntimeException> fx) {
		if (isPresent()) {
			return get();
		}

		Null.nonNullArg(fx, "fx");
		throw Handling.create(fx);
	}

	default short orElseThrow(@Nonnull ExMF<RuntimeException> fx, @Nullable String msg) {
		if (isPresent()) {
			return get();
		}

		Null.nonNullArg(fx, "fx");
		throw Handling.create(fx, msg);
	}

	default short orElseThrow(@Nonnull ExMF<RuntimeException> fx, @Nullable String msg, @Nullable Object param1) {
		if (isPresent()) {
			return get();
		}

		Null.nonNullArg(fx, "fx");
		throw Handling.create(fx, msg, param1);
	}

	default short orElseThrow(@Nonnull ExMF<RuntimeException> fx, @Nullable String msg, @Nullable Object param1, @Nullable Object param2) {
		if (isPresent()) {
			return get();
		}

		Null.nonNullArg(fx, "fx");
		throw Handling.create(fx, msg, param1, param2);
	}

	default short orElseThrow(@Nonnull ExMF<RuntimeException> fx, @Nullable String msg, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		if (isPresent()) {
			return get();
		}

		Null.nonNullArg(fx, "fx");
		throw Handling.create(fx, msg, param1, param2, param3);
	}

	default short orElse(@Nullable short value) {
		return isPresent() ? get() : value;
	}

	default short orElseGet(@Nonnull LSrtSupplier supplier) {
		Null.nonNullArg(supplier, "supplier");
		return isPresent() ? get() : supplier.getAsSrt();
	}

	default SELF orGet(@Nonnull LSrtSupplier supplier) {
		Null.nonNullArg(supplier, "supplier");
		return isPresent() ? self() : value(supplier.getAsSrt());
	}

	default SELF orFlatGet(@Nonnull LSupplier<? extends OptSrtTrait<?>> supplier) {
		Null.nonNullArg(supplier, "supplier");
		return isPresent() ? self() : valueFrom(supplier.get());
	}

	default SELF or(@Nullable short value) {
		return isPresent() ? self() : value(value);
	}

	default SELF orOpt(@Nonnull OptSrtTrait<?> opt) {
		Null.nonNullArg(opt, "opt");
		return isPresent() ? self() : valueFrom(opt);
	}

	default SELF orValue(@Nonnull SrtValueTrait<?> value) {
		Null.nonNullArg(value, "value");
		return isPresent() ? self() : value(value.value());
	}

	default <K> short orElseApply(K a1, @Nonnull LToSrtFunction<? super K> supplier) {
		Null.nonNullArg(supplier, "supplier");
		return isPresent() ? get() : supplier.applyAsSrt(a1);
	}

	default <K> SELF orApply(K a1, @Nonnull LToSrtFunction<? super K> supplier) {
		Null.nonNullArg(supplier, "supplier");
		return isPresent() ? self() : value(supplier.applyAsSrt(a1));
	}

	default <K> SELF orFlatApply(K a1, @Nonnull LFunction<? super K, ? extends OptSrtTrait<?>> supplier) {
		Null.nonNullArg(supplier, "supplier");
		return isPresent() ? self() : valueFrom(supplier.apply(a1));
	}

	default <K1, K2> short orElseApply(K1 a1, K2 a2, @Nonnull LToSrtBiFunction<? super K1, ? super K2> supplier) {
		Null.nonNullArg(supplier, "supplier");
		return isPresent() ? get() : supplier.applyAsSrt(a1, a2);
	}

	default <K1, K2> SELF orApply(K1 a1, K2 a2, @Nonnull LToSrtBiFunction<? super K1, ? super K2> supplier) {
		Null.nonNullArg(supplier, "supplier");
		return isPresent() ? self() : value(supplier.applyAsSrt(a1, a2));
	}

	default <K1, K2> SELF orFlatApply(K1 a1, K2 a2, @Nonnull LBiFunction<? super K1, ? super K2, ? extends OptSrtTrait<?>> supplier) {
		Null.nonNullArg(supplier, "supplier");
		return isPresent() ? self() : valueFrom(supplier.apply(a1, a2));
	}

	default <K1, K2, K3> SELF orFlatApply(K1 a1, K2 a2, K3 a3, @Nonnull LTriFunction<? super K1, ? super K2, ? super K3, ? extends OptSrtTrait<?>> supplier) {
		Null.nonNullArg(supplier, "supplier");
		return isPresent() ? self() : valueFrom(supplier.apply(a1, a2, a3));
	}

	// </editor-fold>

	default OptionalInt toOptional() {
		return isPresent() ? OptionalInt.of(value()) : OptionalInt.empty();
	}

	public static @Nonnull OptSrt op(@Nonnull OptSrtTrait<?> opt1, @Nonnull OptSrtTrait<?> opt2, @Nonnull LSrtBinaryOperator both, @Nonnull LSrtUnaryOperator first, @Nonnull LSrtUnaryOperator second, @Nonnull LSrtSupplier none) {
		Null.nonNullArg(opt1, "opt1");
		Null.nonNullArg(opt2, "opt2");

		if (opt1.isPresent()) {
			if (opt2.isPresent()) {
				Null.nonNullArg(both, "both");
				return Opt.of(both.applyAsSrt(opt1.get(), opt2.get()));
			} else {
				Null.nonNullArg(first, "first");
				return Opt.of(first.applyAsSrt(opt1.get()));
			}
		} else {
			if (opt2.isPresent()) {
				Null.nonNullArg(second, "second");
				return Opt.of(second.applyAsSrt(opt2.get()));
			} else {
				Null.nonNullArg(none, "none");
				return Opt.of(none.getAsSrt());
			}
		}
	}

	public static @Nonnull OptSrt simpleOp(@Nonnull OptSrt opt1, @Nonnull OptSrt opt2, short defaultInput, @Nonnull LSrtBinaryOperator operation) {
		Null.nonNullArg(opt1, "opt1");
		Null.nonNullArg(opt2, "opt2");

		return Opt.of(operation.applyAsSrt(opt1.orElse(defaultInput), opt2.orElse(defaultInput)));
	}

	public static @Nonnull OptSrt simpleOp(@Nonnull OptSrt opt1, @Nonnull OptSrt opt2, @Nonnull LSrtBinaryOperator operation) {
		Null.nonNullArg(opt1, "opt1");
		Null.nonNullArg(opt2, "opt2");

		if (opt1.isPresent() && opt2.isPresent()) {
			return Opt.of(operation.applyAsSrt(opt1.get(), opt2.get()));
		}

		return OptSrt.empty();
	}

	public static @Nonnull OptSrt flatOp(@Nonnull OptSrtTrait<?> opt1, @Nonnull OptSrtTrait<?> opt2, @Nonnull LBiSrtFunction<? extends OptSrtTrait<?>> both, @Nonnull LSrtFunction<? extends OptSrtTrait<?>> first,
			@Nonnull LSrtFunction<? extends OptSrtTrait<?>> second, @Nonnull LSupplier<? extends OptSrtTrait<?>> none) {
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
