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
public interface OptIntTrait<SELF extends OptIntTrait<SELF>> extends FluentTrait<SELF>, aValue<aInt>, CheckIntTrait<SELF>, FilterIntSingleTrait<SELF>, IsIntTrait<SELF>, DoIfIntSingleTrait<SELF>, UseIntSingleTrait<SELF> {

	// <editor-fold desc="forcing ValueTrait re-implementation">

	@Override
	@Nonnull
	SELF value(int value);

	@Override
	@Nonnull
	SELF voidValue();

	// </editor-fold>

	default @Nonnull SELF valueFrom(@Nonnull OptIntTrait<?> opt) {
		Null.nonNullArg(opt, "opt");
		return opt.isPresent() ? value(opt.value()) : voidValue();
	}

	int get();

	default @Nullable Integer nullable() {
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
	default boolean is(@Nonnull LIntPredicate predicate) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && FilterIntSingleTrait.super.is(predicate);
	}

	@Override
	default boolean isNot(@Nonnull LIntPredicate predicate) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && FilterIntSingleTrait.super.isNot(predicate);
	}

	@Override
	default boolean is(int a2, @Nonnull LBiIntPredicate predicate) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && FilterIntSingleTrait.super.is(a2, predicate);
	}

	@Override
	default boolean isNot(int a2, @Nonnull LBiIntPredicate predicate) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && FilterIntSingleTrait.super.isNot(a2, predicate);
	}

	@Override
	default boolean is(int a2, int a3, @Nonnull LTriIntPredicate predicate) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && FilterIntSingleTrait.super.is(a2, a3, predicate);
	}

	@Override
	default boolean isNot(int a2, int a3, @Nonnull LTriIntPredicate predicate) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && FilterIntSingleTrait.super.isNot(a2, a3, predicate);
	}

	@Override
	default boolean isBool_(boolean v, @Nonnull LBoolIntPredicate.LIntBoolPred predicate) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && FilterIntSingleTrait.super.isBool_(v, predicate);
	}

	@Override
	default boolean isNotBool_(boolean v, @Nonnull LBoolIntPredicate.LIntBoolPred predicate) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && FilterIntSingleTrait.super.isNotBool_(v, predicate);
	}

	@Override
	default boolean isByte_(byte v, @Nonnull LByteIntPredicate.LIntBytePred predicate) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && FilterIntSingleTrait.super.isByte_(v, predicate);
	}

	@Override
	default boolean isNotByte_(byte v, @Nonnull LByteIntPredicate.LIntBytePred predicate) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && FilterIntSingleTrait.super.isNotByte_(v, predicate);
	}

	@Override
	default boolean isDbl_(double v, @Nonnull LDblIntPredicate.LIntDblPred predicate) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && FilterIntSingleTrait.super.isDbl_(v, predicate);
	}

	@Override
	default boolean isNotDbl_(double v, @Nonnull LDblIntPredicate.LIntDblPred predicate) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && FilterIntSingleTrait.super.isNotDbl_(v, predicate);
	}

	@Override
	default boolean isChar_(char v, @Nonnull LCharIntPredicate.LIntCharPred predicate) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && FilterIntSingleTrait.super.isChar_(v, predicate);
	}

	@Override
	default boolean isNotChar_(char v, @Nonnull LCharIntPredicate.LIntCharPred predicate) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && FilterIntSingleTrait.super.isNotChar_(v, predicate);
	}

	@Override
	default boolean isSrt_(short v, @Nonnull LSrtIntPredicate.LIntSrtPred predicate) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && FilterIntSingleTrait.super.isSrt_(v, predicate);
	}

	@Override
	default boolean isNotSrt_(short v, @Nonnull LSrtIntPredicate.LIntSrtPred predicate) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && FilterIntSingleTrait.super.isNotSrt_(v, predicate);
	}

	@Override
	default boolean isFlt_(float v, @Nonnull LFltIntPredicate.LIntFltPred predicate) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && FilterIntSingleTrait.super.isFlt_(v, predicate);
	}

	@Override
	default boolean isNotFlt_(float v, @Nonnull LFltIntPredicate.LIntFltPred predicate) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && FilterIntSingleTrait.super.isNotFlt_(v, predicate);
	}

	@Override
	default boolean isLong_(long v, @Nonnull LLongIntPredicate.LIntLongPred predicate) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && FilterIntSingleTrait.super.isLong_(v, predicate);
	}

	@Override
	default boolean isNotLong_(long v, @Nonnull LLongIntPredicate.LIntLongPred predicate) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && FilterIntSingleTrait.super.isNotLong_(v, predicate);
	}

	@Override
	default <V> boolean is_(V v, @Nonnull LObjIntPredicate.LIntObjPred<? super V> predicate) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && FilterIntSingleTrait.super.is_(v, predicate);
	}

	@Override
	default <V> boolean isNot_(V v, @Nonnull LObjIntPredicate.LIntObjPred<? super V> predicate) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && FilterIntSingleTrait.super.isNot_(v, predicate);
	}

	@Override
	default <V1> boolean isWithInt(V1 with1, @Nonnull LObjIntPredicate<? super V1> predicate) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && FilterIntSingleTrait.super.isWithInt(with1, predicate);
	}

	@Override
	default <V1> boolean isNotWithInt(V1 with1, @Nonnull LObjIntPredicate<? super V1> predicate) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && FilterIntSingleTrait.super.isNotWithInt(with1, predicate);
	}

	@Override
	default <V1, V2> boolean isWith(V1 with1, V2 with2, @Nonnull LBiObjIntPredicate<? super V1, ? super V2> predicate) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && FilterIntSingleTrait.super.isWith(with1, with2, predicate);
	}

	@Override
	default <V1, V2> boolean isNotWith(V1 with1, V2 with2, @Nonnull LBiObjIntPredicate<? super V1, ? super V2> predicate) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && FilterIntSingleTrait.super.isNotWith(with1, with2, predicate);
	}

	// </editor-fold>

	// <editor-fold desc="filtering">

	// </editor-fold>

	// <editor-fold desc="map">

	default @Nonnull OptBool mapToBool(@Nonnull LIntPredicate mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptBool.of(mapping.test(get()))) : OptBool.empty();
	}

	default @Nonnull <K> OptBool mapToBool_(K a1, @Nonnull LObjIntPredicate.LIntObjPred<? super K> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptBool.of(mapping.testIntObj(get(), a1))) : OptBool.empty();
	}

	default @Nonnull <K1, K2> OptBool mapToBool_(K1 a1, K2 a2, @Nonnull LBiObjIntPredicate.LInt2Obj0Obj1Pred<? super K1, ? super K2> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptBool.of(mapping.testInt2Obj0Obj1(get(), a1, a2))) : OptBool.empty();
	}

	default @Nonnull <K> OptBool mapToBoolWith(K a1, @Nonnull LObjIntPredicate<? super K> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptBool.of(mapping.test(a1, get()))) : OptBool.empty();
	}

	default @Nonnull <K1, K2> OptBool mapToBoolWith(K1 a1, K2 a2, @Nonnull LBiObjIntPredicate<? super K1, ? super K2> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptBool.of(mapping.test(a1, a2, get()))) : OptBool.empty();
	}

	default @Nonnull OptByte mapToByte(@Nonnull LIntToByteFunction mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptByte.of(mapping.applyAsByte(get()))) : OptByte.empty();
	}

	default @Nonnull <K> OptByte mapToByte_(K a1, @Nonnull LOiToByteFunction.LIntObjToByteFunc<? super K> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptByte.of(mapping.applyAsByteIntObj(get(), a1))) : OptByte.empty();
	}

	default @Nonnull <K> OptByte mapToByteWith(K a1, @Nonnull LOiToByteFunction<? super K> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptByte.of(mapping.applyAsByte(a1, get()))) : OptByte.empty();
	}

	default @Nonnull OptDbl mapToDbl(@Nonnull LIntToDblFunction mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptDbl.of(mapping.applyAsDbl(get()))) : OptDbl.empty();
	}

	default @Nonnull <K> OptDbl mapToDbl_(K a1, @Nonnull LOiToDblFunction.LIntObjToDblFunc<? super K> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptDbl.of(mapping.applyAsDblIntObj(get(), a1))) : OptDbl.empty();
	}

	default @Nonnull <K> OptDbl mapToDblWith(K a1, @Nonnull LOiToDblFunction<? super K> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptDbl.of(mapping.applyAsDbl(a1, get()))) : OptDbl.empty();
	}

	default @Nonnull OptChar mapToChar(@Nonnull LIntToCharFunction mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptChar.of(mapping.applyAsChar(get()))) : OptChar.empty();
	}

	default @Nonnull <K> OptChar mapToChar_(K a1, @Nonnull LOiToCharFunction.LIntObjToCharFunc<? super K> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptChar.of(mapping.applyAsCharIntObj(get(), a1))) : OptChar.empty();
	}

	default @Nonnull <K> OptChar mapToCharWith(K a1, @Nonnull LOiToCharFunction<? super K> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptChar.of(mapping.applyAsChar(a1, get()))) : OptChar.empty();
	}

	default @Nonnull OptSrt mapToSrt(@Nonnull LIntToSrtFunction mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptSrt.of(mapping.applyAsSrt(get()))) : OptSrt.empty();
	}

	default @Nonnull <K> OptSrt mapToSrt_(K a1, @Nonnull LOiToSrtFunction.LIntObjToSrtFunc<? super K> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptSrt.of(mapping.applyAsSrtIntObj(get(), a1))) : OptSrt.empty();
	}

	default @Nonnull <K> OptSrt mapToSrtWith(K a1, @Nonnull LOiToSrtFunction<? super K> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptSrt.of(mapping.applyAsSrt(a1, get()))) : OptSrt.empty();
	}

	default @Nonnull OptFlt mapToFlt(@Nonnull LIntToFltFunction mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptFlt.of(mapping.applyAsFlt(get()))) : OptFlt.empty();
	}

	default @Nonnull <K> OptFlt mapToFlt_(K a1, @Nonnull LOiToFltFunction.LIntObjToFltFunc<? super K> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptFlt.of(mapping.applyAsFltIntObj(get(), a1))) : OptFlt.empty();
	}

	default @Nonnull <K> OptFlt mapToFltWith(K a1, @Nonnull LOiToFltFunction<? super K> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptFlt.of(mapping.applyAsFlt(a1, get()))) : OptFlt.empty();
	}

	default @Nonnull OptInt map(@Nonnull LIntUnaryOperator mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptInt.of(mapping.applyAsInt(get()))) : OptInt.empty();
	}

	default @Nonnull <K> OptInt map_(K a1, @Nonnull LOiToIntFunction.LIntObjToIntFunc<? super K> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptInt.of(mapping.applyAsIntIntObj(get(), a1))) : OptInt.empty();
	}

	default @Nonnull <K1, K2> OptInt map_(K1 a1, K2 a2, @Nonnull LTieFunction.LInt1BiObj2ToIntFunc<? super K1, ? super K2> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptInt.of(mapping.applyAsIntInt1BiObj2(get(), a1, a2))) : OptInt.empty();
	}

	default @Nonnull <K> OptInt mapWith(K a1, @Nonnull LOiToIntFunction<? super K> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptInt.of(mapping.applyAsInt(a1, get()))) : OptInt.empty();
	}

	default @Nonnull <K1, K2> OptInt mapWith_(K1 a1, K2 a2, @Nonnull LTieFunction.LObj0Obj2Int1ToIntFunc<? super K1, ? super K2> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptInt.of(mapping.applyAsIntObj0Obj2Int1(a1, a2, get()))) : OptInt.empty();
	}

	default @Nonnull OptLong mapToLong(@Nonnull LIntToLongFunction mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptLong.of(mapping.applyAsLong(get()))) : OptLong.empty();
	}

	default @Nonnull <K> OptLong mapToLong_(K a1, @Nonnull LOiToLongFunction.LIntObjToLongFunc<? super K> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptLong.of(mapping.applyAsLongIntObj(get(), a1))) : OptLong.empty();
	}

	default @Nonnull <K> OptLong mapToLongWith(K a1, @Nonnull LOiToLongFunction<? super K> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptLong.of(mapping.applyAsLong(a1, get()))) : OptLong.empty();
	}

	default @Nonnull <R> Opt<R> mapToObj(@Nonnull LIntFunction<? extends R> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (Opt.of(mapping.apply(get()))) : Opt.empty();
	}

	default @Nonnull <R, K> Opt<R> mapToObj_(K a1, @Nonnull LOiFunction.LIntObjFunc<? super K, ? extends R> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (Opt.of(mapping.applyIntObj(get(), a1))) : Opt.empty();
	}

	default @Nonnull <R, K1, K2> Opt<R> mapToObj_(K1 a1, K2 a2, @Nonnull LBiObjIntFunction.LInt2Obj0Obj1Func<? super K1, ? super K2, ? extends R> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (Opt.of(mapping.applyInt2Obj0Obj1(get(), a1, a2))) : Opt.empty();
	}

	default @Nonnull <R, K> Opt<R> mapToObjWith(K a1, @Nonnull LOiFunction<? super K, ? extends R> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (Opt.of(mapping.apply(a1, get()))) : Opt.empty();
	}

	default @Nonnull <R, K1, K2> Opt<R> mapToObjWith(K1 a1, K2 a2, @Nonnull LBiObjIntFunction<? super K1, ? super K2, ? extends R> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (Opt.of(mapping.apply(a1, a2, get()))) : Opt.empty();
	}

	// </editor-fold>

	// <editor-fold desc="flatMap">

	default @Nonnull OptBool flatMapToBool(@Nonnull LIntFunction<? extends OptBoolTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptBool.from(mapping.apply(get())) : OptBool.empty();
	}

	default @Nonnull <K> OptBool flatMapToBool_(K a1, @Nonnull LOiFunction.LIntObjFunc<? super K, ? extends OptBoolTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptBool.from(mapping.applyIntObj(get(), a1)) : OptBool.empty();
	}

	default @Nonnull <K1, K2> OptBool flatMapToBool_(K1 a1, K2 a2, @Nonnull LBiObjIntFunction.LInt2Obj0Obj1Func<? super K1, ? super K2, ? extends OptBoolTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptBool.from(mapping.applyInt2Obj0Obj1(get(), a1, a2)) : OptBool.empty();
	}

	default @Nonnull <K> OptBool flatMapToBoolWith(K a1, @Nonnull LOiFunction<? super K, ? extends OptBoolTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptBool.from(mapping.apply(a1, get())) : OptBool.empty();
	}

	default @Nonnull <K1, K2> OptBool flatMapToBoolWith(K1 a1, K2 a2, @Nonnull LBiObjIntFunction<? super K1, ? super K2, ? extends OptBoolTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptBool.from(mapping.apply(a1, a2, get())) : OptBool.empty();
	}

	default @Nonnull OptByte flatMapToByte(@Nonnull LIntFunction<? extends OptByteTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptByte.from(mapping.apply(get())) : OptByte.empty();
	}

	default @Nonnull <K> OptByte flatMapToByte_(K a1, @Nonnull LOiFunction.LIntObjFunc<? super K, ? extends OptByteTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptByte.from(mapping.applyIntObj(get(), a1)) : OptByte.empty();
	}

	default @Nonnull <K1, K2> OptByte flatMapToByte_(K1 a1, K2 a2, @Nonnull LBiObjIntFunction.LInt2Obj0Obj1Func<? super K1, ? super K2, ? extends OptByteTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptByte.from(mapping.applyInt2Obj0Obj1(get(), a1, a2)) : OptByte.empty();
	}

	default @Nonnull <K> OptByte flatMapToByteWith(K a1, @Nonnull LOiFunction<? super K, ? extends OptByteTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptByte.from(mapping.apply(a1, get())) : OptByte.empty();
	}

	default @Nonnull <K1, K2> OptByte flatMapToByteWith(K1 a1, K2 a2, @Nonnull LBiObjIntFunction<? super K1, ? super K2, ? extends OptByteTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptByte.from(mapping.apply(a1, a2, get())) : OptByte.empty();
	}

	default @Nonnull OptDbl flatMapToDbl(@Nonnull LIntFunction<? extends OptDblTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptDbl.from(mapping.apply(get())) : OptDbl.empty();
	}

	default @Nonnull <K> OptDbl flatMapToDbl_(K a1, @Nonnull LOiFunction.LIntObjFunc<? super K, ? extends OptDblTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptDbl.from(mapping.applyIntObj(get(), a1)) : OptDbl.empty();
	}

	default @Nonnull <K1, K2> OptDbl flatMapToDbl_(K1 a1, K2 a2, @Nonnull LBiObjIntFunction.LInt2Obj0Obj1Func<? super K1, ? super K2, ? extends OptDblTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptDbl.from(mapping.applyInt2Obj0Obj1(get(), a1, a2)) : OptDbl.empty();
	}

	default @Nonnull <K> OptDbl flatMapToDblWith(K a1, @Nonnull LOiFunction<? super K, ? extends OptDblTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptDbl.from(mapping.apply(a1, get())) : OptDbl.empty();
	}

	default @Nonnull <K1, K2> OptDbl flatMapToDblWith(K1 a1, K2 a2, @Nonnull LBiObjIntFunction<? super K1, ? super K2, ? extends OptDblTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptDbl.from(mapping.apply(a1, a2, get())) : OptDbl.empty();
	}

	default @Nonnull OptChar flatMapToChar(@Nonnull LIntFunction<? extends OptCharTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptChar.from(mapping.apply(get())) : OptChar.empty();
	}

	default @Nonnull <K> OptChar flatMapToChar_(K a1, @Nonnull LOiFunction.LIntObjFunc<? super K, ? extends OptCharTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptChar.from(mapping.applyIntObj(get(), a1)) : OptChar.empty();
	}

	default @Nonnull <K1, K2> OptChar flatMapToChar_(K1 a1, K2 a2, @Nonnull LBiObjIntFunction.LInt2Obj0Obj1Func<? super K1, ? super K2, ? extends OptCharTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptChar.from(mapping.applyInt2Obj0Obj1(get(), a1, a2)) : OptChar.empty();
	}

	default @Nonnull <K> OptChar flatMapToCharWith(K a1, @Nonnull LOiFunction<? super K, ? extends OptCharTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptChar.from(mapping.apply(a1, get())) : OptChar.empty();
	}

	default @Nonnull <K1, K2> OptChar flatMapToCharWith(K1 a1, K2 a2, @Nonnull LBiObjIntFunction<? super K1, ? super K2, ? extends OptCharTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptChar.from(mapping.apply(a1, a2, get())) : OptChar.empty();
	}

	default @Nonnull OptSrt flatMapToSrt(@Nonnull LIntFunction<? extends OptSrtTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptSrt.from(mapping.apply(get())) : OptSrt.empty();
	}

	default @Nonnull <K> OptSrt flatMapToSrt_(K a1, @Nonnull LOiFunction.LIntObjFunc<? super K, ? extends OptSrtTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptSrt.from(mapping.applyIntObj(get(), a1)) : OptSrt.empty();
	}

	default @Nonnull <K1, K2> OptSrt flatMapToSrt_(K1 a1, K2 a2, @Nonnull LBiObjIntFunction.LInt2Obj0Obj1Func<? super K1, ? super K2, ? extends OptSrtTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptSrt.from(mapping.applyInt2Obj0Obj1(get(), a1, a2)) : OptSrt.empty();
	}

	default @Nonnull <K> OptSrt flatMapToSrtWith(K a1, @Nonnull LOiFunction<? super K, ? extends OptSrtTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptSrt.from(mapping.apply(a1, get())) : OptSrt.empty();
	}

	default @Nonnull <K1, K2> OptSrt flatMapToSrtWith(K1 a1, K2 a2, @Nonnull LBiObjIntFunction<? super K1, ? super K2, ? extends OptSrtTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptSrt.from(mapping.apply(a1, a2, get())) : OptSrt.empty();
	}

	default @Nonnull OptFlt flatMapToFlt(@Nonnull LIntFunction<? extends OptFltTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptFlt.from(mapping.apply(get())) : OptFlt.empty();
	}

	default @Nonnull <K> OptFlt flatMapToFlt_(K a1, @Nonnull LOiFunction.LIntObjFunc<? super K, ? extends OptFltTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptFlt.from(mapping.applyIntObj(get(), a1)) : OptFlt.empty();
	}

	default @Nonnull <K1, K2> OptFlt flatMapToFlt_(K1 a1, K2 a2, @Nonnull LBiObjIntFunction.LInt2Obj0Obj1Func<? super K1, ? super K2, ? extends OptFltTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptFlt.from(mapping.applyInt2Obj0Obj1(get(), a1, a2)) : OptFlt.empty();
	}

	default @Nonnull <K> OptFlt flatMapToFltWith(K a1, @Nonnull LOiFunction<? super K, ? extends OptFltTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptFlt.from(mapping.apply(a1, get())) : OptFlt.empty();
	}

	default @Nonnull <K1, K2> OptFlt flatMapToFltWith(K1 a1, K2 a2, @Nonnull LBiObjIntFunction<? super K1, ? super K2, ? extends OptFltTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptFlt.from(mapping.apply(a1, a2, get())) : OptFlt.empty();
	}

	default @Nonnull OptInt flatMap(@Nonnull LIntFunction<? extends OptIntTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptInt.from(mapping.apply(get())) : OptInt.empty();
	}

	default @Nonnull <K> OptInt flatMap_(K a1, @Nonnull LOiFunction.LIntObjFunc<? super K, ? extends OptIntTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptInt.from(mapping.applyIntObj(get(), a1)) : OptInt.empty();
	}

	default @Nonnull <K1, K2> OptInt flatMap_(K1 a1, K2 a2, @Nonnull LBiObjIntFunction.LInt2Obj0Obj1Func<? super K1, ? super K2, ? extends OptIntTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptInt.from(mapping.applyInt2Obj0Obj1(get(), a1, a2)) : OptInt.empty();
	}

	default @Nonnull <K> OptInt flatMapWith(K a1, @Nonnull LOiFunction<? super K, ? extends OptIntTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptInt.from(mapping.apply(a1, get())) : OptInt.empty();
	}

	default @Nonnull <K1, K2> OptInt flatMapWith(K1 a1, K2 a2, @Nonnull LBiObjIntFunction<? super K1, ? super K2, ? extends OptIntTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptInt.from(mapping.apply(a1, a2, get())) : OptInt.empty();
	}

	default @Nonnull OptLong flatMapToLong(@Nonnull LIntFunction<? extends OptLongTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptLong.from(mapping.apply(get())) : OptLong.empty();
	}

	default @Nonnull <K> OptLong flatMapToLong_(K a1, @Nonnull LOiFunction.LIntObjFunc<? super K, ? extends OptLongTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptLong.from(mapping.applyIntObj(get(), a1)) : OptLong.empty();
	}

	default @Nonnull <K1, K2> OptLong flatMapToLong_(K1 a1, K2 a2, @Nonnull LBiObjIntFunction.LInt2Obj0Obj1Func<? super K1, ? super K2, ? extends OptLongTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptLong.from(mapping.applyInt2Obj0Obj1(get(), a1, a2)) : OptLong.empty();
	}

	default @Nonnull <K> OptLong flatMapToLongWith(K a1, @Nonnull LOiFunction<? super K, ? extends OptLongTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptLong.from(mapping.apply(a1, get())) : OptLong.empty();
	}

	default @Nonnull <K1, K2> OptLong flatMapToLongWith(K1 a1, K2 a2, @Nonnull LBiObjIntFunction<? super K1, ? super K2, ? extends OptLongTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptLong.from(mapping.apply(a1, a2, get())) : OptLong.empty();
	}

	default @Nonnull <R> Opt<R> flatMapToObj(@Nonnull LIntFunction<? extends OptTrait<? extends R, ?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? Opt.from(mapping.apply(get())) : Opt.empty();
	}

	default @Nonnull <R, K> Opt<R> flatMapToObj_(K a1, @Nonnull LOiFunction.LIntObjFunc<? super K, ? extends OptTrait<? extends R, ?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? Opt.from(mapping.applyIntObj(get(), a1)) : Opt.empty();
	}

	default @Nonnull <R, K1, K2> Opt<R> flatMapToObj_(K1 a1, K2 a2, @Nonnull LBiObjIntFunction.LInt2Obj0Obj1Func<? super K1, ? super K2, ? extends OptTrait<? extends R, ?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? Opt.from(mapping.applyInt2Obj0Obj1(get(), a1, a2)) : Opt.empty();
	}

	default @Nonnull <R, K> Opt<R> flatMapToObjWith(K a1, @Nonnull LOiFunction<? super K, ? extends OptTrait<? extends R, ?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? Opt.from(mapping.apply(a1, get())) : Opt.empty();
	}

	default @Nonnull <R, K1, K2> Opt<R> flatMapToObjWith(K1 a1, K2 a2, @Nonnull LBiObjIntFunction<? super K1, ? super K2, ? extends OptTrait<? extends R, ?>> mapping) {
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

	default SELF ifPresent(@Nonnull LIntConsumer action) {
		Null.nonNullArg(action, "action");
		if (isPresent()) {
			action.accept(get());
		}
		return self();
	}

	default SELF ifPresent(@Nonnull LIntConsumer action, @Nonnull LAction emptyAction) {
		Null.nonNullArg(action, "action");
		if (isPresent()) {
			action.accept(get());
		} else {
			emptyAction.execute();
		}
		return self();
	}

	default <K> SELF ifPresent_(K a1, @Nonnull LObjIntConsumer.LIntObjCons<? super K> action) {
		Null.nonNullArg(action, "action");
		if (isPresent()) {
			action.acceptIntObj(get(), a1);
		}
		return self();
	}

	default <K> SELF ifPresentWith(K a1, @Nonnull LObjIntConsumer<? super K> action) {
		Null.nonNullArg(action, "action");
		if (isPresent()) {
			action.accept(a1, get());
		}
		return self();
	}

	default <K1, K2> SELF ifPresentWith(K1 a1, K2 a2, @Nonnull LBiObjIntConsumer<? super K1, ? super K2> action) {
		Null.nonNullArg(action, "action");
		if (isPresent()) {
			action.accept(a1, a2, get());
		}
		return self();
	}

	// </editor-fold>

	/** Compared to ifPresent it will simply fails if there is no value */
	default @Nonnull SELF visit(@Nonnull LIntConsumer consumer) {
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

	default int orElseThrow() {
		if (isPresent()) {
			return get();
		}

		throw Handling.create(X::noSuchElement);
	}

	default int orElseThrow(@Nonnull ExF<RuntimeException> fx) {
		if (isPresent()) {
			return get();
		}

		Null.nonNullArg(fx, "fx");
		throw Handling.create(fx);
	}

	default int orElseThrow(@Nonnull ExMF<RuntimeException> fx, @Nullable String msg) {
		if (isPresent()) {
			return get();
		}

		Null.nonNullArg(fx, "fx");
		throw Handling.create(fx, msg);
	}

	default int orElseThrow(@Nonnull ExMF<RuntimeException> fx, @Nullable String msg, @Nullable Object param1) {
		if (isPresent()) {
			return get();
		}

		Null.nonNullArg(fx, "fx");
		throw Handling.create(fx, msg, param1);
	}

	default int orElseThrow(@Nonnull ExMF<RuntimeException> fx, @Nullable String msg, @Nullable Object param1, @Nullable Object param2) {
		if (isPresent()) {
			return get();
		}

		Null.nonNullArg(fx, "fx");
		throw Handling.create(fx, msg, param1, param2);
	}

	default int orElseThrow(@Nonnull ExMF<RuntimeException> fx, @Nullable String msg, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		if (isPresent()) {
			return get();
		}

		Null.nonNullArg(fx, "fx");
		throw Handling.create(fx, msg, param1, param2, param3);
	}

	default int orElse(@Nullable int value) {
		return isPresent() ? get() : value;
	}

	default int orElseGet(@Nonnull LIntSupplier supplier) {
		Null.nonNullArg(supplier, "supplier");
		return isPresent() ? get() : supplier.getAsInt();
	}

	default SELF orGet(@Nonnull LIntSupplier supplier) {
		Null.nonNullArg(supplier, "supplier");
		return isPresent() ? self() : value(supplier.getAsInt());
	}

	default SELF orFlatGet(@Nonnull LSupplier<? extends OptIntTrait<?>> supplier) {
		Null.nonNullArg(supplier, "supplier");
		return isPresent() ? self() : valueFrom(supplier.get());
	}

	default SELF or(@Nullable int value) {
		return isPresent() ? self() : value(value);
	}

	default SELF orOpt(@Nonnull OptIntTrait<?> opt) {
		Null.nonNullArg(opt, "opt");
		return isPresent() ? self() : valueFrom(opt);
	}

	default <K> int orElseApply(K a1, @Nonnull LToIntFunction<? super K> supplier) {
		Null.nonNullArg(supplier, "supplier");
		return isPresent() ? get() : supplier.applyAsInt(a1);
	}

	default <K> SELF orApply(K a1, @Nonnull LToIntFunction<? super K> supplier) {
		Null.nonNullArg(supplier, "supplier");
		return isPresent() ? self() : value(supplier.applyAsInt(a1));
	}

	default <K> SELF orFlatApply(K a1, @Nonnull LFunction<? super K, ? extends OptIntTrait<?>> supplier) {
		Null.nonNullArg(supplier, "supplier");
		return isPresent() ? self() : valueFrom(supplier.apply(a1));
	}

	default <K1, K2> int orElseApply(K1 a1, K2 a2, @Nonnull LToIntBiFunction<? super K1, ? super K2> supplier) {
		Null.nonNullArg(supplier, "supplier");
		return isPresent() ? get() : supplier.applyAsInt(a1, a2);
	}

	default <K1, K2, K3> int orElseApply(K1 a1, K2 a2, K3 a3, @Nonnull LToIntTriFunction<? super K1, ? super K2, ? super K3> supplier) {
		Null.nonNullArg(supplier, "supplier");
		return isPresent() ? get() : supplier.applyAsInt(a1, a2, a3);
	}

	default <K1, K2> SELF orApply(K1 a1, K2 a2, @Nonnull LToIntBiFunction<? super K1, ? super K2> supplier) {
		Null.nonNullArg(supplier, "supplier");
		return isPresent() ? self() : value(supplier.applyAsInt(a1, a2));
	}

	default <K1, K2> SELF orFlatApply(K1 a1, K2 a2, @Nonnull LBiFunction<? super K1, ? super K2, ? extends OptIntTrait<?>> supplier) {
		Null.nonNullArg(supplier, "supplier");
		return isPresent() ? self() : valueFrom(supplier.apply(a1, a2));
	}

	default <K1, K2, K3> SELF orFlatApply(K1 a1, K2 a2, K3 a3, @Nonnull LTriFunction<? super K1, ? super K2, ? super K3, ? extends OptIntTrait<?>> supplier) {
		Null.nonNullArg(supplier, "supplier");
		return isPresent() ? self() : valueFrom(supplier.apply(a1, a2, a3));
	}

	// </editor-fold>

	default OptionalInt toOptional() {
		return isPresent() ? OptionalInt.of(value()) : OptionalInt.empty();
	}

}
