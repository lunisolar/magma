/*
 * This file is part of "lunisolar-magma".
 *
 * (C) Copyright 2014-2022 Lunisolar (http://lunisolar.eu/).
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
public interface OptCharTrait<SELF extends OptCharTrait<SELF>> extends FluentTrait<SELF>, aValue<aChar>, CheckCharTrait<SELF>, FilterCharSingleTrait<SELF>, IsCharTrait<SELF>, DoIfCharSingleTrait<SELF>, UseCharSingleTrait<SELF>, UniMapCharTrait<SELF> {

	// <editor-fold desc="forcing ValueTrait re-implementation">

	@Override
	@Nonnull
	SELF value(char value);

	@Override
	@Nonnull
	SELF voidValue();

	// </editor-fold>

	char get();

	default @Nullable Character nullable() {
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
	default boolean is(@Nonnull LCharPredicate predicate) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && FilterCharSingleTrait.super.is(predicate);
	}

	@Override
	default boolean isNot(@Nonnull LCharPredicate predicate) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && FilterCharSingleTrait.super.isNot(predicate);
	}

	@Override
	default boolean is(char a2, @Nonnull LBiCharPredicate predicate) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && FilterCharSingleTrait.super.is(a2, predicate);
	}

	@Override
	default boolean isNot(char a2, @Nonnull LBiCharPredicate predicate) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && FilterCharSingleTrait.super.isNot(a2, predicate);
	}

	@Override
	default boolean is(char a2, char a3, @Nonnull LTriCharPredicate predicate) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && FilterCharSingleTrait.super.is(a2, a3, predicate);
	}

	@Override
	default boolean isNot(char a2, char a3, @Nonnull LTriCharPredicate predicate) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && FilterCharSingleTrait.super.isNot(a2, a3, predicate);
	}

	@Override
	default boolean isInt(int v, @Nonnull LCharIntPredicate predicate) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && FilterCharSingleTrait.super.isInt(v, predicate);
	}

	@Override
	default boolean isNotInt(int v, @Nonnull LCharIntPredicate predicate) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && FilterCharSingleTrait.super.isNotInt(v, predicate);
	}

	@Override
	default <V> boolean is_(V v, @Nonnull LObjCharPredicate.LCharObjPred<? super V> predicate) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && FilterCharSingleTrait.super.is_(v, predicate);
	}

	@Override
	default <V> boolean isNot_(V v, @Nonnull LObjCharPredicate.LCharObjPred<? super V> predicate) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && FilterCharSingleTrait.super.isNot_(v, predicate);
	}

	@Override
	default <V1> boolean isWithChar(V1 with1, @Nonnull LObjCharPredicate<? super V1> predicate) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && FilterCharSingleTrait.super.isWithChar(with1, predicate);
	}

	@Override
	default <V1> boolean isNotWithChar(V1 with1, @Nonnull LObjCharPredicate<? super V1> predicate) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && FilterCharSingleTrait.super.isNotWithChar(with1, predicate);
	}

	@Override
	default <V1, V2> boolean isWith(V1 with1, V2 with2, @Nonnull LBiObjCharPredicate<? super V1, ? super V2> predicate) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && FilterCharSingleTrait.super.isWith(with1, with2, predicate);
	}

	@Override
	default <V1, V2> boolean isNotWith(V1 with1, V2 with2, @Nonnull LBiObjCharPredicate<? super V1, ? super V2> predicate) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && FilterCharSingleTrait.super.isNotWith(with1, with2, predicate);
	}

	// </editor-fold>

	// <editor-fold desc="filtering">

	// </editor-fold>

	default SELF butNot(char value) {
		return isPresent() ? (value() == value ? voidValue() : fluentCtx()) : voidValue();
	}

	// <editor-fold desc="uniMap">

	default @Nonnull SELF map(@Nonnull LCharUnaryOperator mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? value(mapping.applyAsChar(get())) : voidValue();
	}

	default @Nonnull SELF map(char a1, @Nonnull LCharBinaryOperator mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? value(mapping.applyAsChar(get(), a1)) : voidValue();
	}

	default @Nonnull SELF map(char a1, char a2, @Nonnull LCharTernaryOperator mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? value(mapping.applyAsChar(get(), a1, a2)) : voidValue();
	}

	// </editor-fold>

	// <editor-fold desc="map">

	default @Nonnull OptBool mapToBool(@Nonnull LCharPredicate mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptBool.of(mapping.test(get()))) : OptBool.empty();
	}

	default @Nonnull <K> OptBool mapToBool_(K a1, @Nonnull LObjCharPredicate.LCharObjPred<? super K> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptBool.of(mapping.testCharObj(get(), a1))) : OptBool.empty();
	}

	default @Nonnull <K> OptBool mapToBoolWith(K a1, @Nonnull LObjCharPredicate<? super K> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptBool.of(mapping.test(a1, get()))) : OptBool.empty();
	}

	default @Nonnull <K1, K2> OptBool mapToBool_(K1 a1, K2 a2, @Nonnull LBiObjCharPredicate.LChar2Obj0Obj1Pred<? super K1, ? super K2> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptBool.of(mapping.testChar2Obj0Obj1(get(), a1, a2))) : OptBool.empty();
	}

	default @Nonnull <K1, K2> OptBool mapToBoolWith(K1 a1, K2 a2, @Nonnull LBiObjCharPredicate<? super K1, ? super K2> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptBool.of(mapping.test(a1, a2, get()))) : OptBool.empty();
	}

	default @Nonnull OptBool mapToBool(char a1, @Nonnull LBiCharPredicate mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptBool.of(mapping.test(get(), a1))) : OptBool.empty();
	}

	default @Nonnull OptBool mapToBool(char a1, char a2, @Nonnull LTriCharPredicate mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptBool.of(mapping.test(get(), a1, a2))) : OptBool.empty();
	}

	default @Nonnull OptByte mapToByte(@Nonnull LCharToByteFunction mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptByte.of(mapping.applyAsByte(get()))) : OptByte.empty();
	}

	default @Nonnull OptDbl mapToDbl(@Nonnull LCharToDblFunction mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptDbl.of(mapping.applyAsDbl(get()))) : OptDbl.empty();
	}

	default @Nonnull OptSrt mapToSrt(@Nonnull LCharToSrtFunction mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptSrt.of(mapping.applyAsSrt(get()))) : OptSrt.empty();
	}

	default @Nonnull OptFlt mapToFlt(@Nonnull LCharToFltFunction mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptFlt.of(mapping.applyAsFlt(get()))) : OptFlt.empty();
	}

	default @Nonnull OptInt mapToInt(@Nonnull LCharToIntFunction mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptInt.of(mapping.applyAsInt(get()))) : OptInt.empty();
	}

	default @Nonnull OptLong mapToLong(@Nonnull LCharToLongFunction mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptLong.of(mapping.applyAsLong(get()))) : OptLong.empty();
	}

	default @Nonnull <R> Opt<R> mapToObj(@Nonnull LCharFunction<? extends R> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (Opt.of(mapping.apply(get()))) : Opt.empty();
	}

	default @Nonnull <R, K> Opt<R> mapToObj_(K a1, @Nonnull LObjCharFunction.LCharObjFunc<? super K, ? extends R> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (Opt.of(mapping.applyCharObj(get(), a1))) : Opt.empty();
	}

	default @Nonnull <R, K> Opt<R> mapToObjWith(K a1, @Nonnull LObjCharFunction<? super K, ? extends R> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (Opt.of(mapping.apply(a1, get()))) : Opt.empty();
	}

	default @Nonnull <R, K1, K2> Opt<R> mapToObj_(K1 a1, K2 a2, @Nonnull LBiObjCharFunction.LChar2Obj0Obj1Func<? super K1, ? super K2, ? extends R> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (Opt.of(mapping.applyChar2Obj0Obj1(get(), a1, a2))) : Opt.empty();
	}

	default @Nonnull <R, K1, K2> Opt<R> mapToObjWith(K1 a1, K2 a2, @Nonnull LBiObjCharFunction<? super K1, ? super K2, ? extends R> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (Opt.of(mapping.apply(a1, a2, get()))) : Opt.empty();
	}

	default @Nonnull <R> Opt<R> mapToObj(char a1, @Nonnull LBiCharFunction<? extends R> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (Opt.of(mapping.apply(get(), a1))) : Opt.empty();
	}

	default @Nonnull <R> Opt<R> mapToObj(char a1, char a2, @Nonnull LTriCharFunction<? extends R> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (Opt.of(mapping.apply(get(), a1, a2))) : Opt.empty();
	}

	// </editor-fold>

	// <editor-fold desc="flatMap">

	default @Nonnull OptBool flatMapToBool(@Nonnull LCharFunction<? extends OptBoolTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptBool.from(mapping.apply(get())) : OptBool.empty();
	}

	default @Nonnull <K> OptBool flatMapToBool_(K a1, @Nonnull LObjCharFunction.LCharObjFunc<? super K, ? extends OptBoolTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptBool.from(mapping.applyCharObj(get(), a1)) : OptBool.empty();
	}

	default @Nonnull <K> OptBool flatMapToBoolWith(K a1, @Nonnull LObjCharFunction<? super K, ? extends OptBoolTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptBool.from(mapping.apply(a1, get())) : OptBool.empty();
	}

	default @Nonnull <K1, K2> OptBool flatMapToBool_(K1 a1, K2 a2, @Nonnull LBiObjCharFunction.LChar2Obj0Obj1Func<? super K1, ? super K2, ? extends OptBoolTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptBool.from(mapping.applyChar2Obj0Obj1(get(), a1, a2)) : OptBool.empty();
	}

	default @Nonnull <K1, K2> OptBool flatMapToBoolWith(K1 a1, K2 a2, @Nonnull LBiObjCharFunction<? super K1, ? super K2, ? extends OptBoolTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptBool.from(mapping.apply(a1, a2, get())) : OptBool.empty();
	}

	default @Nonnull OptBool flatMapToBool(char a1, @Nonnull LBiCharFunction<? extends OptBoolTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptBool.from(mapping.apply(get(), a1)) : OptBool.empty();
	}

	default @Nonnull OptBool flatMapToBool(char a1, char a2, @Nonnull LTriCharFunction<? extends OptBoolTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptBool.from(mapping.apply(get(), a1, a2)) : OptBool.empty();
	}

	default @Nonnull OptByte flatMapToByte(@Nonnull LCharFunction<? extends OptByteTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptByte.from(mapping.apply(get())) : OptByte.empty();
	}

	default @Nonnull <K> OptByte flatMapToByte_(K a1, @Nonnull LObjCharFunction.LCharObjFunc<? super K, ? extends OptByteTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptByte.from(mapping.applyCharObj(get(), a1)) : OptByte.empty();
	}

	default @Nonnull <K> OptByte flatMapToByteWith(K a1, @Nonnull LObjCharFunction<? super K, ? extends OptByteTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptByte.from(mapping.apply(a1, get())) : OptByte.empty();
	}

	default @Nonnull <K1, K2> OptByte flatMapToByte_(K1 a1, K2 a2, @Nonnull LBiObjCharFunction.LChar2Obj0Obj1Func<? super K1, ? super K2, ? extends OptByteTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptByte.from(mapping.applyChar2Obj0Obj1(get(), a1, a2)) : OptByte.empty();
	}

	default @Nonnull <K1, K2> OptByte flatMapToByteWith(K1 a1, K2 a2, @Nonnull LBiObjCharFunction<? super K1, ? super K2, ? extends OptByteTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptByte.from(mapping.apply(a1, a2, get())) : OptByte.empty();
	}

	default @Nonnull OptByte flatMapToByte(char a1, @Nonnull LBiCharFunction<? extends OptByteTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptByte.from(mapping.apply(get(), a1)) : OptByte.empty();
	}

	default @Nonnull OptByte flatMapToByte(char a1, char a2, @Nonnull LTriCharFunction<? extends OptByteTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptByte.from(mapping.apply(get(), a1, a2)) : OptByte.empty();
	}

	default @Nonnull OptDbl flatMapToDbl(@Nonnull LCharFunction<? extends OptDblTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptDbl.from(mapping.apply(get())) : OptDbl.empty();
	}

	default @Nonnull <K> OptDbl flatMapToDbl_(K a1, @Nonnull LObjCharFunction.LCharObjFunc<? super K, ? extends OptDblTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptDbl.from(mapping.applyCharObj(get(), a1)) : OptDbl.empty();
	}

	default @Nonnull <K> OptDbl flatMapToDblWith(K a1, @Nonnull LObjCharFunction<? super K, ? extends OptDblTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptDbl.from(mapping.apply(a1, get())) : OptDbl.empty();
	}

	default @Nonnull <K1, K2> OptDbl flatMapToDbl_(K1 a1, K2 a2, @Nonnull LBiObjCharFunction.LChar2Obj0Obj1Func<? super K1, ? super K2, ? extends OptDblTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptDbl.from(mapping.applyChar2Obj0Obj1(get(), a1, a2)) : OptDbl.empty();
	}

	default @Nonnull <K1, K2> OptDbl flatMapToDblWith(K1 a1, K2 a2, @Nonnull LBiObjCharFunction<? super K1, ? super K2, ? extends OptDblTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptDbl.from(mapping.apply(a1, a2, get())) : OptDbl.empty();
	}

	default @Nonnull OptDbl flatMapToDbl(char a1, @Nonnull LBiCharFunction<? extends OptDblTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptDbl.from(mapping.apply(get(), a1)) : OptDbl.empty();
	}

	default @Nonnull OptDbl flatMapToDbl(char a1, char a2, @Nonnull LTriCharFunction<? extends OptDblTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptDbl.from(mapping.apply(get(), a1, a2)) : OptDbl.empty();
	}

	default @Nonnull SELF flatMap(@Nonnull LCharFunction<? extends OptCharTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? valueFrom(mapping.apply(get())) : voidValue();
	}

	default @Nonnull <K> SELF flatMap_(K a1, @Nonnull LObjCharFunction.LCharObjFunc<? super K, ? extends OptCharTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? valueFrom(mapping.applyCharObj(get(), a1)) : voidValue();
	}

	default @Nonnull <K> SELF flatMapWith(K a1, @Nonnull LObjCharFunction<? super K, ? extends OptCharTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? valueFrom(mapping.apply(a1, get())) : voidValue();
	}

	default @Nonnull <K1, K2> SELF flatMap_(K1 a1, K2 a2, @Nonnull LBiObjCharFunction.LChar2Obj0Obj1Func<? super K1, ? super K2, ? extends OptCharTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? valueFrom(mapping.applyChar2Obj0Obj1(get(), a1, a2)) : voidValue();
	}

	default @Nonnull <K1, K2> SELF flatMapWith(K1 a1, K2 a2, @Nonnull LBiObjCharFunction<? super K1, ? super K2, ? extends OptCharTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? valueFrom(mapping.apply(a1, a2, get())) : voidValue();
	}

	default @Nonnull SELF flatMap(char a1, @Nonnull LBiCharFunction<? extends OptCharTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? valueFrom(mapping.apply(get(), a1)) : voidValue();
	}

	default @Nonnull SELF flatMap(char a1, char a2, @Nonnull LTriCharFunction<? extends OptCharTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? valueFrom(mapping.apply(get(), a1, a2)) : voidValue();
	}

	default @Nonnull OptSrt flatMapToSrt(@Nonnull LCharFunction<? extends OptSrtTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptSrt.from(mapping.apply(get())) : OptSrt.empty();
	}

	default @Nonnull <K> OptSrt flatMapToSrt_(K a1, @Nonnull LObjCharFunction.LCharObjFunc<? super K, ? extends OptSrtTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptSrt.from(mapping.applyCharObj(get(), a1)) : OptSrt.empty();
	}

	default @Nonnull <K> OptSrt flatMapToSrtWith(K a1, @Nonnull LObjCharFunction<? super K, ? extends OptSrtTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptSrt.from(mapping.apply(a1, get())) : OptSrt.empty();
	}

	default @Nonnull <K1, K2> OptSrt flatMapToSrt_(K1 a1, K2 a2, @Nonnull LBiObjCharFunction.LChar2Obj0Obj1Func<? super K1, ? super K2, ? extends OptSrtTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptSrt.from(mapping.applyChar2Obj0Obj1(get(), a1, a2)) : OptSrt.empty();
	}

	default @Nonnull <K1, K2> OptSrt flatMapToSrtWith(K1 a1, K2 a2, @Nonnull LBiObjCharFunction<? super K1, ? super K2, ? extends OptSrtTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptSrt.from(mapping.apply(a1, a2, get())) : OptSrt.empty();
	}

	default @Nonnull OptSrt flatMapToSrt(char a1, @Nonnull LBiCharFunction<? extends OptSrtTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptSrt.from(mapping.apply(get(), a1)) : OptSrt.empty();
	}

	default @Nonnull OptSrt flatMapToSrt(char a1, char a2, @Nonnull LTriCharFunction<? extends OptSrtTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptSrt.from(mapping.apply(get(), a1, a2)) : OptSrt.empty();
	}

	default @Nonnull OptFlt flatMapToFlt(@Nonnull LCharFunction<? extends OptFltTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptFlt.from(mapping.apply(get())) : OptFlt.empty();
	}

	default @Nonnull <K> OptFlt flatMapToFlt_(K a1, @Nonnull LObjCharFunction.LCharObjFunc<? super K, ? extends OptFltTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptFlt.from(mapping.applyCharObj(get(), a1)) : OptFlt.empty();
	}

	default @Nonnull <K> OptFlt flatMapToFltWith(K a1, @Nonnull LObjCharFunction<? super K, ? extends OptFltTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptFlt.from(mapping.apply(a1, get())) : OptFlt.empty();
	}

	default @Nonnull <K1, K2> OptFlt flatMapToFlt_(K1 a1, K2 a2, @Nonnull LBiObjCharFunction.LChar2Obj0Obj1Func<? super K1, ? super K2, ? extends OptFltTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptFlt.from(mapping.applyChar2Obj0Obj1(get(), a1, a2)) : OptFlt.empty();
	}

	default @Nonnull <K1, K2> OptFlt flatMapToFltWith(K1 a1, K2 a2, @Nonnull LBiObjCharFunction<? super K1, ? super K2, ? extends OptFltTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptFlt.from(mapping.apply(a1, a2, get())) : OptFlt.empty();
	}

	default @Nonnull OptFlt flatMapToFlt(char a1, @Nonnull LBiCharFunction<? extends OptFltTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptFlt.from(mapping.apply(get(), a1)) : OptFlt.empty();
	}

	default @Nonnull OptFlt flatMapToFlt(char a1, char a2, @Nonnull LTriCharFunction<? extends OptFltTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptFlt.from(mapping.apply(get(), a1, a2)) : OptFlt.empty();
	}

	default @Nonnull OptInt flatMapToInt(@Nonnull LCharFunction<? extends OptIntTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptInt.from(mapping.apply(get())) : OptInt.empty();
	}

	default @Nonnull <K> OptInt flatMapToInt_(K a1, @Nonnull LObjCharFunction.LCharObjFunc<? super K, ? extends OptIntTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptInt.from(mapping.applyCharObj(get(), a1)) : OptInt.empty();
	}

	default @Nonnull <K> OptInt flatMapToIntWith(K a1, @Nonnull LObjCharFunction<? super K, ? extends OptIntTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptInt.from(mapping.apply(a1, get())) : OptInt.empty();
	}

	default @Nonnull <K1, K2> OptInt flatMapToInt_(K1 a1, K2 a2, @Nonnull LBiObjCharFunction.LChar2Obj0Obj1Func<? super K1, ? super K2, ? extends OptIntTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptInt.from(mapping.applyChar2Obj0Obj1(get(), a1, a2)) : OptInt.empty();
	}

	default @Nonnull <K1, K2> OptInt flatMapToIntWith(K1 a1, K2 a2, @Nonnull LBiObjCharFunction<? super K1, ? super K2, ? extends OptIntTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptInt.from(mapping.apply(a1, a2, get())) : OptInt.empty();
	}

	default @Nonnull OptInt flatMapToInt(char a1, @Nonnull LBiCharFunction<? extends OptIntTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptInt.from(mapping.apply(get(), a1)) : OptInt.empty();
	}

	default @Nonnull OptInt flatMapToInt(char a1, char a2, @Nonnull LTriCharFunction<? extends OptIntTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptInt.from(mapping.apply(get(), a1, a2)) : OptInt.empty();
	}

	default @Nonnull OptLong flatMapToLong(@Nonnull LCharFunction<? extends OptLongTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptLong.from(mapping.apply(get())) : OptLong.empty();
	}

	default @Nonnull <K> OptLong flatMapToLong_(K a1, @Nonnull LObjCharFunction.LCharObjFunc<? super K, ? extends OptLongTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptLong.from(mapping.applyCharObj(get(), a1)) : OptLong.empty();
	}

	default @Nonnull <K> OptLong flatMapToLongWith(K a1, @Nonnull LObjCharFunction<? super K, ? extends OptLongTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptLong.from(mapping.apply(a1, get())) : OptLong.empty();
	}

	default @Nonnull <K1, K2> OptLong flatMapToLong_(K1 a1, K2 a2, @Nonnull LBiObjCharFunction.LChar2Obj0Obj1Func<? super K1, ? super K2, ? extends OptLongTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptLong.from(mapping.applyChar2Obj0Obj1(get(), a1, a2)) : OptLong.empty();
	}

	default @Nonnull <K1, K2> OptLong flatMapToLongWith(K1 a1, K2 a2, @Nonnull LBiObjCharFunction<? super K1, ? super K2, ? extends OptLongTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptLong.from(mapping.apply(a1, a2, get())) : OptLong.empty();
	}

	default @Nonnull OptLong flatMapToLong(char a1, @Nonnull LBiCharFunction<? extends OptLongTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptLong.from(mapping.apply(get(), a1)) : OptLong.empty();
	}

	default @Nonnull OptLong flatMapToLong(char a1, char a2, @Nonnull LTriCharFunction<? extends OptLongTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptLong.from(mapping.apply(get(), a1, a2)) : OptLong.empty();
	}

	default @Nonnull <R> Opt<R> flatMapToObj(@Nonnull LCharFunction<? extends ValueTrait<? extends R, ?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? Opt.from(mapping.apply(get())) : Opt.empty();
	}

	default @Nonnull <R, K> Opt<R> flatMapToObj_(K a1, @Nonnull LObjCharFunction.LCharObjFunc<? super K, ? extends ValueTrait<? extends R, ?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? Opt.from(mapping.applyCharObj(get(), a1)) : Opt.empty();
	}

	default @Nonnull <R, K> Opt<R> flatMapToObjWith(K a1, @Nonnull LObjCharFunction<? super K, ? extends ValueTrait<? extends R, ?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? Opt.from(mapping.apply(a1, get())) : Opt.empty();
	}

	default @Nonnull <R, K1, K2> Opt<R> flatMapToObj_(K1 a1, K2 a2, @Nonnull LBiObjCharFunction.LChar2Obj0Obj1Func<? super K1, ? super K2, ? extends ValueTrait<? extends R, ?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? Opt.from(mapping.applyChar2Obj0Obj1(get(), a1, a2)) : Opt.empty();
	}

	default @Nonnull <R, K1, K2> Opt<R> flatMapToObjWith(K1 a1, K2 a2, @Nonnull LBiObjCharFunction<? super K1, ? super K2, ? extends ValueTrait<? extends R, ?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? Opt.from(mapping.apply(a1, a2, get())) : Opt.empty();
	}

	default @Nonnull <R> Opt<R> flatMapToObj(char a1, @Nonnull LBiCharFunction<? extends ValueTrait<? extends R, ?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? Opt.from(mapping.apply(get(), a1)) : Opt.empty();
	}

	default @Nonnull <R> Opt<R> flatMapToObj(char a1, char a2, @Nonnull LTriCharFunction<? extends ValueTrait<? extends R, ?>> mapping) {
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

	default SELF ifPresent(@Nonnull LCharConsumer action) {
		Null.nonNullArg(action, "action");
		if (isPresent()) {
			action.accept(get());
		}
		return fluentCtx();
	}

	default SELF ifPresentOr(@Nonnull LCharConsumer action, @Nonnull LAction emptyAction) {
		Null.nonNullArg(action, "action");
		if (isPresent()) {
			action.accept(get());
		} else {
			emptyAction.execute();
		}
		return fluentCtx();
	}

	default <K1> SELF ifPresent_(K1 a2, @Nonnull LObjCharConsumer.LCharObjCons<? super K1> action) {
		Null.nonNullArg(action, "action");
		if (isPresent()) {
			action.acceptCharObj(get(), a2);
		}
		return fluentCtx();
	}

	default <K1> SELF ifPresentOr_(K1 a2, @Nonnull LObjCharConsumer.LCharObjCons<? super K1> action, @Nonnull LAction emptyAction) {
		Null.nonNullArg(action, "action");
		if (isPresent()) {
			action.acceptCharObj(get(), a2);
		} else {
			emptyAction.execute();
		}
		return fluentCtx();
	}

	default <K1, K2> SELF ifPresent_(K1 a2, K2 a3, @Nonnull LBiObjCharConsumer.LChar2Obj0Obj1Cons<? super K1, ? super K2> action) {
		Null.nonNullArg(action, "action");
		if (isPresent()) {
			action.acceptChar2Obj0Obj1(get(), a2, a3);
		}
		return fluentCtx();
	}

	default <K1, K2> SELF ifPresentOr_(K1 a2, K2 a3, @Nonnull LBiObjCharConsumer.LChar2Obj0Obj1Cons<? super K1, ? super K2> action, @Nonnull LAction emptyAction) {
		Null.nonNullArg(action, "action");
		if (isPresent()) {
			action.acceptChar2Obj0Obj1(get(), a2, a3);
		} else {
			emptyAction.execute();
		}
		return fluentCtx();
	}

	default SELF ifPresentWith(@Nonnull LCharConsumer action) {
		Null.nonNullArg(action, "action");
		if (isPresent()) {
			action.accept(get());
		}
		return fluentCtx();
	}

	default SELF ifPresentOrWith(@Nonnull LCharConsumer action, @Nonnull LAction emptyAction) {
		Null.nonNullArg(action, "action");
		if (isPresent()) {
			action.accept(get());
		} else {
			emptyAction.execute();
		}
		return fluentCtx();
	}

	default <K1> SELF ifPresentWith(K1 a1, @Nonnull LObjCharConsumer<? super K1> action) {
		Null.nonNullArg(action, "action");
		if (isPresent()) {
			action.accept(a1, get());
		}
		return fluentCtx();
	}

	default <K1> SELF ifPresentOrWith(K1 a1, @Nonnull LObjCharConsumer<? super K1> action, @Nonnull LAction emptyAction) {
		Null.nonNullArg(action, "action");
		if (isPresent()) {
			action.accept(a1, get());
		} else {
			emptyAction.execute();
		}
		return fluentCtx();
	}

	default <K1, K2> SELF ifPresentWith(K1 a1, K2 a2, @Nonnull LBiObjCharConsumer<? super K1, ? super K2> action) {
		Null.nonNullArg(action, "action");
		if (isPresent()) {
			action.accept(a1, a2, get());
		}
		return fluentCtx();
	}

	default <K1, K2> SELF ifPresentOrWith(K1 a1, K2 a2, @Nonnull LBiObjCharConsumer<? super K1, ? super K2> action, @Nonnull LAction emptyAction) {
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

	/** Compared to ifPresent it will simply fails if there is no value */
	default @Nonnull SELF visit(@Nonnull LCharConsumer consumer) {
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

	default char orElseThrow() {
		if (isPresent()) {
			return get();
		}

		throw Handling.create(X::noSuchElement);
	}

	default char orElseThrow(@Nonnull ExF<RuntimeException> fx) {
		if (isPresent()) {
			return get();
		}

		Null.nonNullArg(fx, "fx");
		throw Handling.create(fx);
	}

	default char orElseThrow(@Nonnull ExMF<RuntimeException> fx, @Nullable String msg) {
		if (isPresent()) {
			return get();
		}

		Null.nonNullArg(fx, "fx");
		throw Handling.create(fx, msg);
	}

	default char orElseThrow(@Nonnull ExMF<RuntimeException> fx, @Nullable String msg, @Nullable Object param1) {
		if (isPresent()) {
			return get();
		}

		Null.nonNullArg(fx, "fx");
		throw Handling.create(fx, msg, param1);
	}

	default char orElseThrow(@Nonnull ExMF<RuntimeException> fx, @Nullable String msg, @Nullable Object param1, @Nullable Object param2) {
		if (isPresent()) {
			return get();
		}

		Null.nonNullArg(fx, "fx");
		throw Handling.create(fx, msg, param1, param2);
	}

	default char orElseThrow(@Nonnull ExMF<RuntimeException> fx, @Nullable String msg, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		if (isPresent()) {
			return get();
		}

		Null.nonNullArg(fx, "fx");
		throw Handling.create(fx, msg, param1, param2, param3);
	}

	default char orElse(@Nullable char value) {
		return isPresent() ? get() : value;
	}

	default char orElseGet(@Nonnull LCharSupplier supplier) {
		Null.nonNullArg(supplier, "supplier");
		return isPresent() ? get() : supplier.getAsChar();
	}

	default SELF orGet(@Nonnull LCharSupplier supplier) {
		Null.nonNullArg(supplier, "supplier");
		return isPresent() ? fluentCtx() : value(supplier.getAsChar());
	}

	default SELF orFlatGet(@Nonnull LSupplier<? extends OptCharTrait<?>> supplier) {
		Null.nonNullArg(supplier, "supplier");
		return isPresent() ? fluentCtx() : valueFrom(supplier.get());
	}

	default SELF or(@Nullable char value) {
		return isPresent() ? fluentCtx() : value(value);
	}

	default SELF orOpt(@Nonnull OptCharTrait<?> opt) {
		Null.nonNullArg(opt, "opt");
		return isPresent() ? fluentCtx() : valueFrom(opt);
	}

	default SELF orValue(@Nonnull CharValueTrait<?> value) {
		Null.nonNullArg(value, "value");
		return isPresent() ? fluentCtx() : value(value.value());
	}

	default <K> char orElseApply(K a1, @Nonnull LToCharFunction<? super K> supplier) {
		Null.nonNullArg(supplier, "supplier");
		return isPresent() ? get() : supplier.applyAsChar(a1);
	}

	default <K> SELF orApply(K a1, @Nonnull LToCharFunction<? super K> supplier) {
		Null.nonNullArg(supplier, "supplier");
		return isPresent() ? fluentCtx() : value(supplier.applyAsChar(a1));
	}

	default <K> SELF orFlatApply(K a1, @Nonnull LFunction<? super K, ? extends OptCharTrait<?>> supplier) {
		Null.nonNullArg(supplier, "supplier");
		return isPresent() ? fluentCtx() : valueFrom(supplier.apply(a1));
	}

	default <K1, K2> char orElseApply(K1 a1, K2 a2, @Nonnull LToCharBiFunction<? super K1, ? super K2> supplier) {
		Null.nonNullArg(supplier, "supplier");
		return isPresent() ? get() : supplier.applyAsChar(a1, a2);
	}

	default <K1, K2> SELF orApply(K1 a1, K2 a2, @Nonnull LToCharBiFunction<? super K1, ? super K2> supplier) {
		Null.nonNullArg(supplier, "supplier");
		return isPresent() ? fluentCtx() : value(supplier.applyAsChar(a1, a2));
	}

	default <K1, K2> SELF orFlatApply(K1 a1, K2 a2, @Nonnull LBiFunction<? super K1, ? super K2, ? extends OptCharTrait<?>> supplier) {
		Null.nonNullArg(supplier, "supplier");
		return isPresent() ? fluentCtx() : valueFrom(supplier.apply(a1, a2));
	}

	default <K1, K2, K3> SELF orFlatApply(K1 a1, K2 a2, K3 a3, @Nonnull LTriFunction<? super K1, ? super K2, ? super K3, ? extends OptCharTrait<?>> supplier) {
		Null.nonNullArg(supplier, "supplier");
		return isPresent() ? fluentCtx() : valueFrom(supplier.apply(a1, a2, a3));
	}

	// </editor-fold>

	default OptionalInt toOptional() {
		return isPresent() ? OptionalInt.of(value()) : OptionalInt.empty();
	}

	default @Nonnull OptChar op(@Nonnull OptCharTrait<?> opt2, @Nonnull LCharBinaryOperator both, @Nonnull LCharUnaryOperator first, @Nonnull LCharUnaryOperator second, @Nonnull LCharSupplier none) {
		return op(this, opt2, both, first, second, none);
	}

	public static @Nonnull OptChar op(@Nonnull OptCharTrait<?> opt1, @Nonnull OptCharTrait<?> opt2, @Nonnull LCharBinaryOperator both, @Nonnull LCharUnaryOperator first, @Nonnull LCharUnaryOperator second, @Nonnull LCharSupplier none) {
		Null.nonNullArg(opt1, "opt1");
		Null.nonNullArg(opt2, "opt2");

		if (opt1.isPresent()) {
			if (opt2.isPresent()) {
				Null.nonNullArg(both, "both");
				return Opt.of(both.applyAsChar(opt1.get(), opt2.get()));
			} else {
				Null.nonNullArg(first, "first");
				return Opt.of(first.applyAsChar(opt1.get()));
			}
		} else {
			if (opt2.isPresent()) {
				Null.nonNullArg(second, "second");
				return Opt.of(second.applyAsChar(opt2.get()));
			} else {
				Null.nonNullArg(none, "none");
				return Opt.of(none.getAsChar());
			}
		}
	}

	default @Nonnull OptChar simpleOp(@Nonnull OptCharTrait<?> opt2, char defaultInput, @Nonnull LCharBinaryOperator operation) {
		return simpleOp((OptCharTrait) this, opt2, defaultInput, operation);
	}

	public static @Nonnull OptChar simpleOp(@Nonnull OptCharTrait<?> opt1, @Nonnull OptCharTrait<?> opt2, char defaultInput, @Nonnull LCharBinaryOperator operation) {
		Null.nonNullArg(opt1, "opt1");
		Null.nonNullArg(opt2, "opt2");

		return Opt.of(operation.applyAsChar(opt1.orElse(Clazz.the(defaultInput)), opt2.orElse(Clazz.the(defaultInput))));
	}

	default @Nonnull OptChar simpleOp(@Nonnull OptCharTrait<?> opt2, @Nonnull LCharBinaryOperator operation) {
		return simpleOp((OptCharTrait) this, opt2, operation);
	}

	public static @Nonnull OptChar simpleOp(@Nonnull OptCharTrait<?> opt1, @Nonnull OptCharTrait<?> opt2, @Nonnull LCharBinaryOperator operation) {
		Null.nonNullArg(opt1, "opt1");
		Null.nonNullArg(opt2, "opt2");

		if (opt1.isPresent() && opt2.isPresent()) {
			return Opt.of(operation.applyAsChar(opt1.get(), opt2.get()));
		}

		return OptChar.empty();
	}

	default @Nonnull OptChar flatOp(@Nonnull OptCharTrait<?> opt2, @Nonnull LBiCharFunction<? extends OptCharTrait<?>> both, @Nonnull LCharFunction<? extends OptCharTrait<?>> first, @Nonnull LCharFunction<? extends OptCharTrait<?>> second,
			@Nonnull LSupplier<? extends OptCharTrait<?>> none) {
		return flatOp((OptCharTrait) this, opt2, both, first, second, none);
	}

	public static @Nonnull OptChar flatOp(@Nonnull OptCharTrait<?> opt1, @Nonnull OptCharTrait<?> opt2, @Nonnull LBiCharFunction<? extends OptCharTrait<?>> both, @Nonnull LCharFunction<? extends OptCharTrait<?>> first,
			@Nonnull LCharFunction<? extends OptCharTrait<?>> second, @Nonnull LSupplier<? extends OptCharTrait<?>> none) {
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
