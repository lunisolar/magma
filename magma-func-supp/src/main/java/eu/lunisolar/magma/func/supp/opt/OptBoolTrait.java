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
public interface OptBoolTrait<SELF extends OptBoolTrait<SELF>> extends FluentTrait<SELF>, aValue<aBool>, CheckBoolTrait<SELF>, FilterBoolSingleTrait<SELF>, IsBoolTrait<SELF>, DoIfBoolSingleTrait<SELF>, UseBoolSingleTrait<SELF>, UniMapBoolTrait<SELF> {

	// <editor-fold desc="forcing ValueTrait re-implementation">

	@Override
	@Nonnull
	SELF value(boolean value);

	@Override
	@Nonnull
	SELF voidValue();

	// </editor-fold>

	boolean get();

	default @Nullable Boolean nullable() {
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

	// <editor-fold desc="isPresent() dependant boolean terminals">

	@Override
	default boolean is(@Nonnull LLogicalOperator operator) {
		Null.nonNullArg(operator, "operator");
		return isPresent() && FilterBoolSingleTrait.super.is(operator);
	}

	@Override
	default boolean isNot(@Nonnull LLogicalOperator operator) {
		Null.nonNullArg(operator, "operator");
		return isPresent() && FilterBoolSingleTrait.super.isNot(operator);
	}

	@Override
	default boolean is(boolean a2, @Nonnull LLogicalBinaryOperator operator) {
		Null.nonNullArg(operator, "operator");
		return isPresent() && FilterBoolSingleTrait.super.is(a2, operator);
	}

	@Override
	default boolean isNot(boolean a2, @Nonnull LLogicalBinaryOperator operator) {
		Null.nonNullArg(operator, "operator");
		return isPresent() && FilterBoolSingleTrait.super.isNot(a2, operator);
	}

	@Override
	default boolean is(boolean a2, boolean a3, @Nonnull LLogicalTernaryOperator operator) {
		Null.nonNullArg(operator, "operator");
		return isPresent() && FilterBoolSingleTrait.super.is(a2, a3, operator);
	}

	@Override
	default boolean isNot(boolean a2, boolean a3, @Nonnull LLogicalTernaryOperator operator) {
		Null.nonNullArg(operator, "operator");
		return isPresent() && FilterBoolSingleTrait.super.isNot(a2, a3, operator);
	}

	@Override
	default boolean isInt(int v, @Nonnull LBoolIntPredicate operator) {
		Null.nonNullArg(operator, "operator");
		return isPresent() && FilterBoolSingleTrait.super.isInt(v, operator);
	}

	@Override
	default boolean isNotInt(int v, @Nonnull LBoolIntPredicate operator) {
		Null.nonNullArg(operator, "operator");
		return isPresent() && FilterBoolSingleTrait.super.isNotInt(v, operator);
	}

	@Override
	default <V> boolean is_(V v, @Nonnull LObjBoolPredicate.LBoolObjPred<? super V> operator) {
		Null.nonNullArg(operator, "operator");
		return isPresent() && FilterBoolSingleTrait.super.is_(v, operator);
	}

	@Override
	default <V> boolean isNot_(V v, @Nonnull LObjBoolPredicate.LBoolObjPred<? super V> operator) {
		Null.nonNullArg(operator, "operator");
		return isPresent() && FilterBoolSingleTrait.super.isNot_(v, operator);
	}

	@Override
	default <V1> boolean isWithBool(V1 with1, @Nonnull LObjBoolPredicate<? super V1> operator) {
		Null.nonNullArg(operator, "operator");
		return isPresent() && FilterBoolSingleTrait.super.isWithBool(with1, operator);
	}

	@Override
	default <V1> boolean isNotWithBool(V1 with1, @Nonnull LObjBoolPredicate<? super V1> operator) {
		Null.nonNullArg(operator, "operator");
		return isPresent() && FilterBoolSingleTrait.super.isNotWithBool(with1, operator);
	}

	@Override
	default <V1, V2> boolean isWith(V1 with1, V2 with2, @Nonnull LBiObjBoolPredicate<? super V1, ? super V2> operator) {
		Null.nonNullArg(operator, "operator");
		return isPresent() && FilterBoolSingleTrait.super.isWith(with1, with2, operator);
	}

	@Override
	default <V1, V2> boolean isNotWith(V1 with1, V2 with2, @Nonnull LBiObjBoolPredicate<? super V1, ? super V2> operator) {
		Null.nonNullArg(operator, "operator");
		return isPresent() && FilterBoolSingleTrait.super.isNotWith(with1, with2, operator);
	}

	// </editor-fold>

	// <editor-fold desc="filtering">

	// </editor-fold>

	default SELF butNot(boolean value) {
		return isPresent() ? (value() == value ? voidValue() : fluentCtx()) : voidValue();
	}

	// <editor-fold desc="uniMap">

	default @Nonnull SELF map(@Nonnull LLogicalOperator mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? value(mapping.apply(get())) : voidValue();
	}

	default @Nonnull <K> SELF map_(K a1, @Nonnull LObjBoolPredicate.LBoolObjPred<? super K> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? value(mapping.testBoolObj(get(), a1)) : voidValue();
	}

	default @Nonnull <K> SELF mapWith(K a1, @Nonnull LObjBoolPredicate<? super K> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? value(mapping.test(a1, get())) : voidValue();
	}

	default @Nonnull <K1, K2> SELF map_(K1 a1, K2 a2, @Nonnull LBiObjBoolPredicate.LBool2Obj0Obj1Pred<? super K1, ? super K2> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? value(mapping.testBool2Obj0Obj1(get(), a1, a2)) : voidValue();
	}

	default @Nonnull <K1, K2> SELF mapWith(K1 a1, K2 a2, @Nonnull LBiObjBoolPredicate<? super K1, ? super K2> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? value(mapping.test(a1, a2, get())) : voidValue();
	}

	default @Nonnull SELF map(boolean a1, @Nonnull LLogicalBinaryOperator mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? value(mapping.apply(get(), a1)) : voidValue();
	}

	default @Nonnull SELF map(boolean a1, boolean a2, @Nonnull LLogicalTernaryOperator mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? value(mapping.apply(get(), a1, a2)) : voidValue();
	}

	// </editor-fold>

	// <editor-fold desc="map">

	default @Nonnull OptByte mapToByte(@Nonnull LBoolToByteFunction mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptByte.of(mapping.applyAsByte(get()))) : OptByte.empty();
	}

	default @Nonnull OptDbl mapToDbl(@Nonnull LBoolToDblFunction mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptDbl.of(mapping.applyAsDbl(get()))) : OptDbl.empty();
	}

	default @Nonnull OptChar mapToChar(@Nonnull LBoolToCharFunction mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptChar.of(mapping.applyAsChar(get()))) : OptChar.empty();
	}

	default @Nonnull OptSrt mapToSrt(@Nonnull LBoolToSrtFunction mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptSrt.of(mapping.applyAsSrt(get()))) : OptSrt.empty();
	}

	default @Nonnull OptFlt mapToFlt(@Nonnull LBoolToFltFunction mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptFlt.of(mapping.applyAsFlt(get()))) : OptFlt.empty();
	}

	default @Nonnull OptInt mapToInt(@Nonnull LBoolToIntFunction mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptInt.of(mapping.applyAsInt(get()))) : OptInt.empty();
	}

	default @Nonnull OptLong mapToLong(@Nonnull LBoolToLongFunction mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptLong.of(mapping.applyAsLong(get()))) : OptLong.empty();
	}

	default @Nonnull <R> Opt<R> mapToObj(@Nonnull LBoolFunction<? extends R> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (Opt.of(mapping.apply(get()))) : Opt.empty();
	}

	default @Nonnull <R, K> Opt<R> mapToObj_(K a1, @Nonnull LObjBoolFunction.LBoolObjFunc<? super K, ? extends R> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (Opt.of(mapping.applyBoolObj(get(), a1))) : Opt.empty();
	}

	default @Nonnull <R, K> Opt<R> mapToObjWith(K a1, @Nonnull LObjBoolFunction<? super K, ? extends R> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (Opt.of(mapping.apply(a1, get()))) : Opt.empty();
	}

	default @Nonnull <R, K1, K2> Opt<R> mapToObj_(K1 a1, K2 a2, @Nonnull LBiObjBoolFunction.LBool2Obj0Obj1Func<? super K1, ? super K2, ? extends R> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (Opt.of(mapping.applyBool2Obj0Obj1(get(), a1, a2))) : Opt.empty();
	}

	default @Nonnull <R, K1, K2> Opt<R> mapToObjWith(K1 a1, K2 a2, @Nonnull LBiObjBoolFunction<? super K1, ? super K2, ? extends R> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (Opt.of(mapping.apply(a1, a2, get()))) : Opt.empty();
	}

	default @Nonnull <R> Opt<R> mapToObj(boolean a1, @Nonnull LBiBoolFunction<? extends R> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (Opt.of(mapping.apply(get(), a1))) : Opt.empty();
	}

	default @Nonnull <R> Opt<R> mapToObj(boolean a1, boolean a2, @Nonnull LTriBoolFunction<? extends R> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (Opt.of(mapping.apply(get(), a1, a2))) : Opt.empty();
	}

	// </editor-fold>

	// <editor-fold desc="flatMap">

	default @Nonnull SELF flatMap(@Nonnull LBoolFunction<? extends OptBoolTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? valueFrom(mapping.apply(get())) : voidValue();
	}

	default @Nonnull <K> SELF flatMap_(K a1, @Nonnull LObjBoolFunction.LBoolObjFunc<? super K, ? extends OptBoolTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? valueFrom(mapping.applyBoolObj(get(), a1)) : voidValue();
	}

	default @Nonnull <K> SELF flatMapWith(K a1, @Nonnull LObjBoolFunction<? super K, ? extends OptBoolTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? valueFrom(mapping.apply(a1, get())) : voidValue();
	}

	default @Nonnull <K1, K2> SELF flatMap_(K1 a1, K2 a2, @Nonnull LBiObjBoolFunction.LBool2Obj0Obj1Func<? super K1, ? super K2, ? extends OptBoolTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? valueFrom(mapping.applyBool2Obj0Obj1(get(), a1, a2)) : voidValue();
	}

	default @Nonnull <K1, K2> SELF flatMapWith(K1 a1, K2 a2, @Nonnull LBiObjBoolFunction<? super K1, ? super K2, ? extends OptBoolTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? valueFrom(mapping.apply(a1, a2, get())) : voidValue();
	}

	default @Nonnull SELF flatMap(boolean a1, @Nonnull LBiBoolFunction<? extends OptBoolTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? valueFrom(mapping.apply(get(), a1)) : voidValue();
	}

	default @Nonnull SELF flatMap(boolean a1, boolean a2, @Nonnull LTriBoolFunction<? extends OptBoolTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? valueFrom(mapping.apply(get(), a1, a2)) : voidValue();
	}

	default @Nonnull OptByte flatMapToByte(@Nonnull LBoolFunction<? extends OptByteTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptByte.from(mapping.apply(get())) : OptByte.empty();
	}

	default @Nonnull <K> OptByte flatMapToByte_(K a1, @Nonnull LObjBoolFunction.LBoolObjFunc<? super K, ? extends OptByteTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptByte.from(mapping.applyBoolObj(get(), a1)) : OptByte.empty();
	}

	default @Nonnull <K> OptByte flatMapToByteWith(K a1, @Nonnull LObjBoolFunction<? super K, ? extends OptByteTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptByte.from(mapping.apply(a1, get())) : OptByte.empty();
	}

	default @Nonnull <K1, K2> OptByte flatMapToByte_(K1 a1, K2 a2, @Nonnull LBiObjBoolFunction.LBool2Obj0Obj1Func<? super K1, ? super K2, ? extends OptByteTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptByte.from(mapping.applyBool2Obj0Obj1(get(), a1, a2)) : OptByte.empty();
	}

	default @Nonnull <K1, K2> OptByte flatMapToByteWith(K1 a1, K2 a2, @Nonnull LBiObjBoolFunction<? super K1, ? super K2, ? extends OptByteTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptByte.from(mapping.apply(a1, a2, get())) : OptByte.empty();
	}

	default @Nonnull OptByte flatMapToByte(boolean a1, @Nonnull LBiBoolFunction<? extends OptByteTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptByte.from(mapping.apply(get(), a1)) : OptByte.empty();
	}

	default @Nonnull OptByte flatMapToByte(boolean a1, boolean a2, @Nonnull LTriBoolFunction<? extends OptByteTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptByte.from(mapping.apply(get(), a1, a2)) : OptByte.empty();
	}

	default @Nonnull OptDbl flatMapToDbl(@Nonnull LBoolFunction<? extends OptDblTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptDbl.from(mapping.apply(get())) : OptDbl.empty();
	}

	default @Nonnull <K> OptDbl flatMapToDbl_(K a1, @Nonnull LObjBoolFunction.LBoolObjFunc<? super K, ? extends OptDblTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptDbl.from(mapping.applyBoolObj(get(), a1)) : OptDbl.empty();
	}

	default @Nonnull <K> OptDbl flatMapToDblWith(K a1, @Nonnull LObjBoolFunction<? super K, ? extends OptDblTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptDbl.from(mapping.apply(a1, get())) : OptDbl.empty();
	}

	default @Nonnull <K1, K2> OptDbl flatMapToDbl_(K1 a1, K2 a2, @Nonnull LBiObjBoolFunction.LBool2Obj0Obj1Func<? super K1, ? super K2, ? extends OptDblTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptDbl.from(mapping.applyBool2Obj0Obj1(get(), a1, a2)) : OptDbl.empty();
	}

	default @Nonnull <K1, K2> OptDbl flatMapToDblWith(K1 a1, K2 a2, @Nonnull LBiObjBoolFunction<? super K1, ? super K2, ? extends OptDblTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptDbl.from(mapping.apply(a1, a2, get())) : OptDbl.empty();
	}

	default @Nonnull OptDbl flatMapToDbl(boolean a1, @Nonnull LBiBoolFunction<? extends OptDblTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptDbl.from(mapping.apply(get(), a1)) : OptDbl.empty();
	}

	default @Nonnull OptDbl flatMapToDbl(boolean a1, boolean a2, @Nonnull LTriBoolFunction<? extends OptDblTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptDbl.from(mapping.apply(get(), a1, a2)) : OptDbl.empty();
	}

	default @Nonnull OptChar flatMapToChar(@Nonnull LBoolFunction<? extends OptCharTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptChar.from(mapping.apply(get())) : OptChar.empty();
	}

	default @Nonnull <K> OptChar flatMapToChar_(K a1, @Nonnull LObjBoolFunction.LBoolObjFunc<? super K, ? extends OptCharTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptChar.from(mapping.applyBoolObj(get(), a1)) : OptChar.empty();
	}

	default @Nonnull <K> OptChar flatMapToCharWith(K a1, @Nonnull LObjBoolFunction<? super K, ? extends OptCharTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptChar.from(mapping.apply(a1, get())) : OptChar.empty();
	}

	default @Nonnull <K1, K2> OptChar flatMapToChar_(K1 a1, K2 a2, @Nonnull LBiObjBoolFunction.LBool2Obj0Obj1Func<? super K1, ? super K2, ? extends OptCharTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptChar.from(mapping.applyBool2Obj0Obj1(get(), a1, a2)) : OptChar.empty();
	}

	default @Nonnull <K1, K2> OptChar flatMapToCharWith(K1 a1, K2 a2, @Nonnull LBiObjBoolFunction<? super K1, ? super K2, ? extends OptCharTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptChar.from(mapping.apply(a1, a2, get())) : OptChar.empty();
	}

	default @Nonnull OptChar flatMapToChar(boolean a1, @Nonnull LBiBoolFunction<? extends OptCharTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptChar.from(mapping.apply(get(), a1)) : OptChar.empty();
	}

	default @Nonnull OptChar flatMapToChar(boolean a1, boolean a2, @Nonnull LTriBoolFunction<? extends OptCharTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptChar.from(mapping.apply(get(), a1, a2)) : OptChar.empty();
	}

	default @Nonnull OptSrt flatMapToSrt(@Nonnull LBoolFunction<? extends OptSrtTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptSrt.from(mapping.apply(get())) : OptSrt.empty();
	}

	default @Nonnull <K> OptSrt flatMapToSrt_(K a1, @Nonnull LObjBoolFunction.LBoolObjFunc<? super K, ? extends OptSrtTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptSrt.from(mapping.applyBoolObj(get(), a1)) : OptSrt.empty();
	}

	default @Nonnull <K> OptSrt flatMapToSrtWith(K a1, @Nonnull LObjBoolFunction<? super K, ? extends OptSrtTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptSrt.from(mapping.apply(a1, get())) : OptSrt.empty();
	}

	default @Nonnull <K1, K2> OptSrt flatMapToSrt_(K1 a1, K2 a2, @Nonnull LBiObjBoolFunction.LBool2Obj0Obj1Func<? super K1, ? super K2, ? extends OptSrtTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptSrt.from(mapping.applyBool2Obj0Obj1(get(), a1, a2)) : OptSrt.empty();
	}

	default @Nonnull <K1, K2> OptSrt flatMapToSrtWith(K1 a1, K2 a2, @Nonnull LBiObjBoolFunction<? super K1, ? super K2, ? extends OptSrtTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptSrt.from(mapping.apply(a1, a2, get())) : OptSrt.empty();
	}

	default @Nonnull OptSrt flatMapToSrt(boolean a1, @Nonnull LBiBoolFunction<? extends OptSrtTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptSrt.from(mapping.apply(get(), a1)) : OptSrt.empty();
	}

	default @Nonnull OptSrt flatMapToSrt(boolean a1, boolean a2, @Nonnull LTriBoolFunction<? extends OptSrtTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptSrt.from(mapping.apply(get(), a1, a2)) : OptSrt.empty();
	}

	default @Nonnull OptFlt flatMapToFlt(@Nonnull LBoolFunction<? extends OptFltTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptFlt.from(mapping.apply(get())) : OptFlt.empty();
	}

	default @Nonnull <K> OptFlt flatMapToFlt_(K a1, @Nonnull LObjBoolFunction.LBoolObjFunc<? super K, ? extends OptFltTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptFlt.from(mapping.applyBoolObj(get(), a1)) : OptFlt.empty();
	}

	default @Nonnull <K> OptFlt flatMapToFltWith(K a1, @Nonnull LObjBoolFunction<? super K, ? extends OptFltTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptFlt.from(mapping.apply(a1, get())) : OptFlt.empty();
	}

	default @Nonnull <K1, K2> OptFlt flatMapToFlt_(K1 a1, K2 a2, @Nonnull LBiObjBoolFunction.LBool2Obj0Obj1Func<? super K1, ? super K2, ? extends OptFltTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptFlt.from(mapping.applyBool2Obj0Obj1(get(), a1, a2)) : OptFlt.empty();
	}

	default @Nonnull <K1, K2> OptFlt flatMapToFltWith(K1 a1, K2 a2, @Nonnull LBiObjBoolFunction<? super K1, ? super K2, ? extends OptFltTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptFlt.from(mapping.apply(a1, a2, get())) : OptFlt.empty();
	}

	default @Nonnull OptFlt flatMapToFlt(boolean a1, @Nonnull LBiBoolFunction<? extends OptFltTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptFlt.from(mapping.apply(get(), a1)) : OptFlt.empty();
	}

	default @Nonnull OptFlt flatMapToFlt(boolean a1, boolean a2, @Nonnull LTriBoolFunction<? extends OptFltTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptFlt.from(mapping.apply(get(), a1, a2)) : OptFlt.empty();
	}

	default @Nonnull OptInt flatMapToInt(@Nonnull LBoolFunction<? extends OptIntTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptInt.from(mapping.apply(get())) : OptInt.empty();
	}

	default @Nonnull <K> OptInt flatMapToInt_(K a1, @Nonnull LObjBoolFunction.LBoolObjFunc<? super K, ? extends OptIntTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptInt.from(mapping.applyBoolObj(get(), a1)) : OptInt.empty();
	}

	default @Nonnull <K> OptInt flatMapToIntWith(K a1, @Nonnull LObjBoolFunction<? super K, ? extends OptIntTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptInt.from(mapping.apply(a1, get())) : OptInt.empty();
	}

	default @Nonnull <K1, K2> OptInt flatMapToInt_(K1 a1, K2 a2, @Nonnull LBiObjBoolFunction.LBool2Obj0Obj1Func<? super K1, ? super K2, ? extends OptIntTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptInt.from(mapping.applyBool2Obj0Obj1(get(), a1, a2)) : OptInt.empty();
	}

	default @Nonnull <K1, K2> OptInt flatMapToIntWith(K1 a1, K2 a2, @Nonnull LBiObjBoolFunction<? super K1, ? super K2, ? extends OptIntTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptInt.from(mapping.apply(a1, a2, get())) : OptInt.empty();
	}

	default @Nonnull OptInt flatMapToInt(boolean a1, @Nonnull LBiBoolFunction<? extends OptIntTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptInt.from(mapping.apply(get(), a1)) : OptInt.empty();
	}

	default @Nonnull OptInt flatMapToInt(boolean a1, boolean a2, @Nonnull LTriBoolFunction<? extends OptIntTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptInt.from(mapping.apply(get(), a1, a2)) : OptInt.empty();
	}

	default @Nonnull OptLong flatMapToLong(@Nonnull LBoolFunction<? extends OptLongTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptLong.from(mapping.apply(get())) : OptLong.empty();
	}

	default @Nonnull <K> OptLong flatMapToLong_(K a1, @Nonnull LObjBoolFunction.LBoolObjFunc<? super K, ? extends OptLongTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptLong.from(mapping.applyBoolObj(get(), a1)) : OptLong.empty();
	}

	default @Nonnull <K> OptLong flatMapToLongWith(K a1, @Nonnull LObjBoolFunction<? super K, ? extends OptLongTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptLong.from(mapping.apply(a1, get())) : OptLong.empty();
	}

	default @Nonnull <K1, K2> OptLong flatMapToLong_(K1 a1, K2 a2, @Nonnull LBiObjBoolFunction.LBool2Obj0Obj1Func<? super K1, ? super K2, ? extends OptLongTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptLong.from(mapping.applyBool2Obj0Obj1(get(), a1, a2)) : OptLong.empty();
	}

	default @Nonnull <K1, K2> OptLong flatMapToLongWith(K1 a1, K2 a2, @Nonnull LBiObjBoolFunction<? super K1, ? super K2, ? extends OptLongTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptLong.from(mapping.apply(a1, a2, get())) : OptLong.empty();
	}

	default @Nonnull OptLong flatMapToLong(boolean a1, @Nonnull LBiBoolFunction<? extends OptLongTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptLong.from(mapping.apply(get(), a1)) : OptLong.empty();
	}

	default @Nonnull OptLong flatMapToLong(boolean a1, boolean a2, @Nonnull LTriBoolFunction<? extends OptLongTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptLong.from(mapping.apply(get(), a1, a2)) : OptLong.empty();
	}

	default @Nonnull <R> Opt<R> flatMapToObj(@Nonnull LBoolFunction<? extends ValueTrait<? extends R, ?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? Opt.from(mapping.apply(get())) : Opt.empty();
	}

	default @Nonnull <R, K> Opt<R> flatMapToObj_(K a1, @Nonnull LObjBoolFunction.LBoolObjFunc<? super K, ? extends ValueTrait<? extends R, ?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? Opt.from(mapping.applyBoolObj(get(), a1)) : Opt.empty();
	}

	default @Nonnull <R, K> Opt<R> flatMapToObjWith(K a1, @Nonnull LObjBoolFunction<? super K, ? extends ValueTrait<? extends R, ?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? Opt.from(mapping.apply(a1, get())) : Opt.empty();
	}

	default @Nonnull <R, K1, K2> Opt<R> flatMapToObj_(K1 a1, K2 a2, @Nonnull LBiObjBoolFunction.LBool2Obj0Obj1Func<? super K1, ? super K2, ? extends ValueTrait<? extends R, ?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? Opt.from(mapping.applyBool2Obj0Obj1(get(), a1, a2)) : Opt.empty();
	}

	default @Nonnull <R, K1, K2> Opt<R> flatMapToObjWith(K1 a1, K2 a2, @Nonnull LBiObjBoolFunction<? super K1, ? super K2, ? extends ValueTrait<? extends R, ?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? Opt.from(mapping.apply(a1, a2, get())) : Opt.empty();
	}

	default @Nonnull <R> Opt<R> flatMapToObj(boolean a1, @Nonnull LBiBoolFunction<? extends ValueTrait<? extends R, ?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? Opt.from(mapping.apply(get(), a1)) : Opt.empty();
	}

	default @Nonnull <R> Opt<R> flatMapToObj(boolean a1, boolean a2, @Nonnull LTriBoolFunction<? extends ValueTrait<? extends R, ?>> mapping) {
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

	default SELF ifPresent(@Nonnull LBoolConsumer action) {
		Null.nonNullArg(action, "action");
		if (isPresent()) {
			action.accept(get());
		}
		return fluentCtx();
	}

	default SELF ifPresentOr(@Nonnull LBoolConsumer action, @Nonnull LAction emptyAction) {
		Null.nonNullArg(action, "action");
		if (isPresent()) {
			action.accept(get());
		} else {
			emptyAction.execute();
		}
		return fluentCtx();
	}

	default <K1> SELF ifPresent_(K1 a2, @Nonnull LObjBoolConsumer.LBoolObjCons<? super K1> action) {
		Null.nonNullArg(action, "action");
		if (isPresent()) {
			action.acceptBoolObj(get(), a2);
		}
		return fluentCtx();
	}

	default <K1> SELF ifPresentOr_(K1 a2, @Nonnull LObjBoolConsumer.LBoolObjCons<? super K1> action, @Nonnull LAction emptyAction) {
		Null.nonNullArg(action, "action");
		if (isPresent()) {
			action.acceptBoolObj(get(), a2);
		} else {
			emptyAction.execute();
		}
		return fluentCtx();
	}

	default <K1, K2> SELF ifPresent_(K1 a2, K2 a3, @Nonnull LBiObjBoolConsumer.LBool2Obj0Obj1Cons<? super K1, ? super K2> action) {
		Null.nonNullArg(action, "action");
		if (isPresent()) {
			action.acceptBool2Obj0Obj1(get(), a2, a3);
		}
		return fluentCtx();
	}

	default <K1, K2> SELF ifPresentOr_(K1 a2, K2 a3, @Nonnull LBiObjBoolConsumer.LBool2Obj0Obj1Cons<? super K1, ? super K2> action, @Nonnull LAction emptyAction) {
		Null.nonNullArg(action, "action");
		if (isPresent()) {
			action.acceptBool2Obj0Obj1(get(), a2, a3);
		} else {
			emptyAction.execute();
		}
		return fluentCtx();
	}

	default SELF ifPresentWith(@Nonnull LBoolConsumer action) {
		Null.nonNullArg(action, "action");
		if (isPresent()) {
			action.accept(get());
		}
		return fluentCtx();
	}

	default SELF ifPresentOrWith(@Nonnull LBoolConsumer action, @Nonnull LAction emptyAction) {
		Null.nonNullArg(action, "action");
		if (isPresent()) {
			action.accept(get());
		} else {
			emptyAction.execute();
		}
		return fluentCtx();
	}

	default <K1> SELF ifPresentWith(K1 a1, @Nonnull LObjBoolConsumer<? super K1> action) {
		Null.nonNullArg(action, "action");
		if (isPresent()) {
			action.accept(a1, get());
		}
		return fluentCtx();
	}

	default <K1> SELF ifPresentOrWith(K1 a1, @Nonnull LObjBoolConsumer<? super K1> action, @Nonnull LAction emptyAction) {
		Null.nonNullArg(action, "action");
		if (isPresent()) {
			action.accept(a1, get());
		} else {
			emptyAction.execute();
		}
		return fluentCtx();
	}

	default <K1, K2> SELF ifPresentWith(K1 a1, K2 a2, @Nonnull LBiObjBoolConsumer<? super K1, ? super K2> action) {
		Null.nonNullArg(action, "action");
		if (isPresent()) {
			action.accept(a1, a2, get());
		}
		return fluentCtx();
	}

	default <K1, K2> SELF ifPresentOrWith(K1 a1, K2 a2, @Nonnull LBiObjBoolConsumer<? super K1, ? super K2> action, @Nonnull LAction emptyAction) {
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
	default @Nonnull SELF visit(@Nonnull LBoolConsumer consumer) {
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

	default boolean orElseThrow() {
		if (isPresent()) {
			return get();
		}

		throw Handling.create(X::noSuchElement);
	}

	default boolean orElseThrow(@Nonnull ExF<RuntimeException> fx) {
		if (isPresent()) {
			return get();
		}

		Null.nonNullArg(fx, "fx");
		throw Handling.create(fx);
	}

	default boolean orElseThrow(@Nonnull ExMF<RuntimeException> fx, @Nullable String msg) {
		if (isPresent()) {
			return get();
		}

		Null.nonNullArg(fx, "fx");
		throw Handling.create(fx, msg);
	}

	default boolean orElseThrow(@Nonnull ExMF<RuntimeException> fx, @Nullable String msg, @Nullable Object param1) {
		if (isPresent()) {
			return get();
		}

		Null.nonNullArg(fx, "fx");
		throw Handling.create(fx, msg, param1);
	}

	default boolean orElseThrow(@Nonnull ExMF<RuntimeException> fx, @Nullable String msg, @Nullable Object param1, @Nullable Object param2) {
		if (isPresent()) {
			return get();
		}

		Null.nonNullArg(fx, "fx");
		throw Handling.create(fx, msg, param1, param2);
	}

	default boolean orElseThrow(@Nonnull ExMF<RuntimeException> fx, @Nullable String msg, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		if (isPresent()) {
			return get();
		}

		Null.nonNullArg(fx, "fx");
		throw Handling.create(fx, msg, param1, param2, param3);
	}

	default boolean orElse(@Nullable boolean value) {
		return isPresent() ? get() : value;
	}

	default boolean orElseGet(@Nonnull LBoolSupplier supplier) {
		Null.nonNullArg(supplier, "supplier");
		return isPresent() ? get() : supplier.getAsBool();
	}

	default SELF orGet(@Nonnull LBoolSupplier supplier) {
		Null.nonNullArg(supplier, "supplier");
		return isPresent() ? fluentCtx() : value(supplier.getAsBool());
	}

	default SELF orFlatGet(@Nonnull LSupplier<? extends OptBoolTrait<?>> supplier) {
		Null.nonNullArg(supplier, "supplier");
		return isPresent() ? fluentCtx() : valueFrom(supplier.get());
	}

	default SELF or(@Nullable boolean value) {
		return isPresent() ? fluentCtx() : value(value);
	}

	default SELF orOpt(@Nonnull OptBoolTrait<?> opt) {
		Null.nonNullArg(opt, "opt");
		return isPresent() ? fluentCtx() : valueFrom(opt);
	}

	default SELF orValue(@Nonnull BoolValueTrait<?> value) {
		Null.nonNullArg(value, "value");
		return isPresent() ? fluentCtx() : value(value.value());
	}

	default <K> boolean orElseApply(K a1, @Nonnull LPredicate<? super K> supplier) {
		Null.nonNullArg(supplier, "supplier");
		return isPresent() ? get() : supplier.test(a1);
	}

	default <K> SELF orApply(K a1, @Nonnull LPredicate<? super K> supplier) {
		Null.nonNullArg(supplier, "supplier");
		return isPresent() ? fluentCtx() : value(supplier.test(a1));
	}

	default <K> SELF orFlatApply(K a1, @Nonnull LFunction<? super K, ? extends OptBoolTrait<?>> supplier) {
		Null.nonNullArg(supplier, "supplier");
		return isPresent() ? fluentCtx() : valueFrom(supplier.apply(a1));
	}

	default <K1, K2> boolean orElseApply(K1 a1, K2 a2, @Nonnull LBiPredicate<? super K1, ? super K2> supplier) {
		Null.nonNullArg(supplier, "supplier");
		return isPresent() ? get() : supplier.test(a1, a2);
	}

	default <K1, K2, K3> boolean orElseApply(K1 a1, K2 a2, K3 a3, @Nonnull LTriPredicate<? super K1, ? super K2, ? super K3> supplier) {
		Null.nonNullArg(supplier, "supplier");
		return isPresent() ? get() : supplier.test(a1, a2, a3);
	}

	default <K1, K2> SELF orApply(K1 a1, K2 a2, @Nonnull LBiPredicate<? super K1, ? super K2> supplier) {
		Null.nonNullArg(supplier, "supplier");
		return isPresent() ? fluentCtx() : value(supplier.test(a1, a2));
	}

	default <K1, K2, K3> SELF orApply(K1 a1, K2 a2, K3 a3, @Nonnull LTriPredicate<? super K1, ? super K2, ? super K3> supplier) {
		Null.nonNullArg(supplier, "supplier");
		return isPresent() ? fluentCtx() : value(supplier.test(a1, a2, a3));
	}

	default <K1, K2> SELF orFlatApply(K1 a1, K2 a2, @Nonnull LBiFunction<? super K1, ? super K2, ? extends OptBoolTrait<?>> supplier) {
		Null.nonNullArg(supplier, "supplier");
		return isPresent() ? fluentCtx() : valueFrom(supplier.apply(a1, a2));
	}

	default <K1, K2, K3> SELF orFlatApply(K1 a1, K2 a2, K3 a3, @Nonnull LTriFunction<? super K1, ? super K2, ? super K3, ? extends OptBoolTrait<?>> supplier) {
		Null.nonNullArg(supplier, "supplier");
		return isPresent() ? fluentCtx() : valueFrom(supplier.apply(a1, a2, a3));
	}

	// </editor-fold>

	default @Nonnull OptBool op(@Nonnull OptBoolTrait<?> opt2, @Nonnull LLogicalBinaryOperator both, @Nonnull LLogicalOperator first, @Nonnull LLogicalOperator second, @Nonnull LBoolSupplier none) {
		return op(this, opt2, both, first, second, none);
	}

	public static @Nonnull OptBool op(@Nonnull OptBoolTrait<?> opt1, @Nonnull OptBoolTrait<?> opt2, @Nonnull LLogicalBinaryOperator both, @Nonnull LLogicalOperator first, @Nonnull LLogicalOperator second, @Nonnull LBoolSupplier none) {
		Null.nonNullArg(opt1, "opt1");
		Null.nonNullArg(opt2, "opt2");

		if (opt1.isPresent()) {
			if (opt2.isPresent()) {
				Null.nonNullArg(both, "both");
				return Opt.of(both.apply(opt1.get(), opt2.get()));
			} else {
				Null.nonNullArg(first, "first");
				return Opt.of(first.apply(opt1.get()));
			}
		} else {
			if (opt2.isPresent()) {
				Null.nonNullArg(second, "second");
				return Opt.of(second.apply(opt2.get()));
			} else {
				Null.nonNullArg(none, "none");
				return Opt.of(none.getAsBool());
			}
		}
	}

	default @Nonnull OptBool simpleOp(@Nonnull OptBoolTrait<?> opt2, boolean defaultInput, @Nonnull LLogicalBinaryOperator operation) {
		return simpleOp((OptBoolTrait) this, opt2, defaultInput, operation);
	}

	public static @Nonnull OptBool simpleOp(@Nonnull OptBoolTrait<?> opt1, @Nonnull OptBoolTrait<?> opt2, boolean defaultInput, @Nonnull LLogicalBinaryOperator operation) {
		Null.nonNullArg(opt1, "opt1");
		Null.nonNullArg(opt2, "opt2");

		return Opt.of(operation.apply(opt1.orElse(Clazz.the(defaultInput)), opt2.orElse(Clazz.the(defaultInput))));
	}

	default @Nonnull OptBool simpleOp(@Nonnull OptBoolTrait<?> opt2, @Nonnull LLogicalBinaryOperator operation) {
		return simpleOp((OptBoolTrait) this, opt2, operation);
	}

	public static @Nonnull OptBool simpleOp(@Nonnull OptBoolTrait<?> opt1, @Nonnull OptBoolTrait<?> opt2, @Nonnull LLogicalBinaryOperator operation) {
		Null.nonNullArg(opt1, "opt1");
		Null.nonNullArg(opt2, "opt2");

		if (opt1.isPresent() && opt2.isPresent()) {
			return Opt.of(operation.apply(opt1.get(), opt2.get()));
		}

		return OptBool.empty();
	}

	default @Nonnull OptBool flatOp(@Nonnull OptBoolTrait<?> opt2, @Nonnull LBiBoolFunction<? extends OptBoolTrait<?>> both, @Nonnull LBoolFunction<? extends OptBoolTrait<?>> first, @Nonnull LBoolFunction<? extends OptBoolTrait<?>> second,
			@Nonnull LSupplier<? extends OptBoolTrait<?>> none) {
		return flatOp((OptBoolTrait) this, opt2, both, first, second, none);
	}

	public static @Nonnull OptBool flatOp(@Nonnull OptBoolTrait<?> opt1, @Nonnull OptBoolTrait<?> opt2, @Nonnull LBiBoolFunction<? extends OptBoolTrait<?>> both, @Nonnull LBoolFunction<? extends OptBoolTrait<?>> first,
			@Nonnull LBoolFunction<? extends OptBoolTrait<?>> second, @Nonnull LSupplier<? extends OptBoolTrait<?>> none) {
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
