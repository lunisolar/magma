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
import eu.lunisolar.magma.func.supp.memento.*; // NOSONAR
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
public interface OptIntTrait<SELF extends OptIntTrait<SELF>> extends Fluent<SELF>, aValue<aInt>, CheckIntTrait<SELF>, FilterIntSingleTrait<SELF>, IsIntTrait<SELF>, DoIfIntTrait<SELF> {

	// <editor-fold desc="forcing ValueTrait re-implementation">

	@Override
	@Nonnull
	SELF value(int value);
	@Override
	@Nonnull
	SELF voidValue();

	// </editor-fold>

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
		return isPresent() && predicate.test(get());
	}

	@Override
	default boolean isNot(@Nonnull LIntPredicate predicate) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && predicate.test(get());
	}

	@Override
	default boolean is(int a2, @Nonnull LBiIntPredicate predicate) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && predicate.test(get(), a2);
	}

	@Override
	default boolean isNot(int a2, @Nonnull LBiIntPredicate predicate) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && predicate.test(get(), a2);
	}

	@Override
	default boolean is(int a2, int a3, @Nonnull LTriIntPredicate predicate) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && predicate.test(get(), a2, a3);
	}

	@Override
	default boolean isNot(int a2, int a3, @Nonnull LTriIntPredicate predicate) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && predicate.test(get(), a2, a3);
	}

	@Override
	default boolean is2_(boolean v, @Nonnull LBoolIntPredicate.LIntBoolPred predicate) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && predicate.testIntBool(get(), v);
	}

	@Override
	default boolean isNot2_(boolean v, @Nonnull LBoolIntPredicate.LIntBoolPred predicate) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && predicate.testIntBool(get(), v);
	}

	@Override
	default boolean is2_(byte v, @Nonnull LByteIntPredicate.LIntBytePred predicate) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && predicate.testIntByte(get(), v);
	}

	@Override
	default boolean isNot2_(byte v, @Nonnull LByteIntPredicate.LIntBytePred predicate) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && predicate.testIntByte(get(), v);
	}

	@Override
	default boolean is2_(double v, @Nonnull LDblIntPredicate.LIntDblPred predicate) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && predicate.testIntDbl(get(), v);
	}

	@Override
	default boolean isNot2_(double v, @Nonnull LDblIntPredicate.LIntDblPred predicate) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && predicate.testIntDbl(get(), v);
	}

	@Override
	default boolean is2_(char v, @Nonnull LCharIntPredicate.LIntCharPred predicate) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && predicate.testIntChar(get(), v);
	}

	@Override
	default boolean isNot2_(char v, @Nonnull LCharIntPredicate.LIntCharPred predicate) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && predicate.testIntChar(get(), v);
	}

	@Override
	default boolean is2_(short v, @Nonnull LSrtIntPredicate.LIntSrtPred predicate) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && predicate.testIntSrt(get(), v);
	}

	@Override
	default boolean isNot2_(short v, @Nonnull LSrtIntPredicate.LIntSrtPred predicate) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && predicate.testIntSrt(get(), v);
	}

	@Override
	default boolean is2_(float v, @Nonnull LFltIntPredicate.LIntFltPred predicate) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && predicate.testIntFlt(get(), v);
	}

	@Override
	default boolean isNot2_(float v, @Nonnull LFltIntPredicate.LIntFltPred predicate) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && predicate.testIntFlt(get(), v);
	}

	@Override
	default boolean is2_(long v, @Nonnull LLongIntPredicate.LIntLongPred predicate) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && predicate.testIntLong(get(), v);
	}

	@Override
	default boolean isNot2_(long v, @Nonnull LLongIntPredicate.LIntLongPred predicate) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && predicate.testIntLong(get(), v);
	}

	@Override
	default <V> boolean is2_(V v, @Nonnull LObjIntPredicate.LIntObjPred<? super V> predicate) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && predicate.testIntObj(get(), v);
	}

	@Override
	default <V> boolean isNot2_(V v, @Nonnull LObjIntPredicate.LIntObjPred<? super V> predicate) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && predicate.testIntObj(get(), v);
	}

	// </editor-fold>

	// <editor-fold desc="filtering">

	// </editor-fold>

	// <editor-fold desc="map">

	default OptBool mapToBool(@Nonnull LIntPredicate mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptBool.of(mapping.test(get()))) : OptBool.empty();
	}

	default <K> OptBool mapToBool_(K a2, @Nonnull LObjIntPredicate.LIntObjPred<? super K> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptBool.of(mapping.testIntObj(get(), a2))) : OptBool.empty();
	}

	default <K> OptBool mapToBoolWith(K a1, @Nonnull LObjIntPredicate<? super K> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptBool.of(mapping.test(a1, get()))) : OptBool.empty();
	}

	default OptByte mapToByte(@Nonnull LIntToByteFunction mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptByte.of(mapping.applyAsByte(get()))) : OptByte.empty();
	}

	default <K> OptByte mapToByte_(K a2, @Nonnull LOiToByteFunction.LIntObjToByteFunc<? super K> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptByte.of(mapping.applyAsByteIntObj(get(), a2))) : OptByte.empty();
	}

	default <K> OptByte mapToByteWith(K a1, @Nonnull LOiToByteFunction<? super K> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptByte.of(mapping.applyAsByte(a1, get()))) : OptByte.empty();
	}

	default OptDbl mapToDbl(@Nonnull LIntToDblFunction mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptDbl.of(mapping.applyAsDbl(get()))) : OptDbl.empty();
	}

	default <K> OptDbl mapToDbl_(K a2, @Nonnull LOiToDblFunction.LIntObjToDblFunc<? super K> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptDbl.of(mapping.applyAsDblIntObj(get(), a2))) : OptDbl.empty();
	}

	default <K> OptDbl mapToDblWith(K a1, @Nonnull LOiToDblFunction<? super K> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptDbl.of(mapping.applyAsDbl(a1, get()))) : OptDbl.empty();
	}

	default OptChar mapToChar(@Nonnull LIntToCharFunction mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptChar.of(mapping.applyAsChar(get()))) : OptChar.empty();
	}

	default <K> OptChar mapToChar_(K a2, @Nonnull LOiToCharFunction.LIntObjToCharFunc<? super K> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptChar.of(mapping.applyAsCharIntObj(get(), a2))) : OptChar.empty();
	}

	default <K> OptChar mapToCharWith(K a1, @Nonnull LOiToCharFunction<? super K> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptChar.of(mapping.applyAsChar(a1, get()))) : OptChar.empty();
	}

	default OptSrt mapToSrt(@Nonnull LIntToSrtFunction mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptSrt.of(mapping.applyAsSrt(get()))) : OptSrt.empty();
	}

	default <K> OptSrt mapToSrt_(K a2, @Nonnull LOiToSrtFunction.LIntObjToSrtFunc<? super K> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptSrt.of(mapping.applyAsSrtIntObj(get(), a2))) : OptSrt.empty();
	}

	default <K> OptSrt mapToSrtWith(K a1, @Nonnull LOiToSrtFunction<? super K> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptSrt.of(mapping.applyAsSrt(a1, get()))) : OptSrt.empty();
	}

	default OptFlt mapToFlt(@Nonnull LIntToFltFunction mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptFlt.of(mapping.applyAsFlt(get()))) : OptFlt.empty();
	}

	default <K> OptFlt mapToFlt_(K a2, @Nonnull LOiToFltFunction.LIntObjToFltFunc<? super K> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptFlt.of(mapping.applyAsFltIntObj(get(), a2))) : OptFlt.empty();
	}

	default <K> OptFlt mapToFltWith(K a1, @Nonnull LOiToFltFunction<? super K> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptFlt.of(mapping.applyAsFlt(a1, get()))) : OptFlt.empty();
	}

	default OptInt map(@Nonnull LIntUnaryOperator mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptInt.of(mapping.applyAsInt(get()))) : OptInt.empty();
	}

	default <K> OptInt map_(K a2, @Nonnull LOiToIntFunction.LIntObjToIntFunc<? super K> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptInt.of(mapping.applyAsIntIntObj(get(), a2))) : OptInt.empty();
	}

	default <K> OptInt mapWith(K a1, @Nonnull LOiToIntFunction<? super K> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptInt.of(mapping.applyAsInt(a1, get()))) : OptInt.empty();
	}

	default OptLong mapToLong(@Nonnull LIntToLongFunction mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptLong.of(mapping.applyAsLong(get()))) : OptLong.empty();
	}

	default <K> OptLong mapToLong_(K a2, @Nonnull LOiToLongFunction.LIntObjToLongFunc<? super K> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptLong.of(mapping.applyAsLongIntObj(get(), a2))) : OptLong.empty();
	}

	default <K> OptLong mapToLongWith(K a1, @Nonnull LOiToLongFunction<? super K> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptLong.of(mapping.applyAsLong(a1, get()))) : OptLong.empty();
	}

	default <R> Opt<R> mapToObj(@Nonnull LIntFunction<? extends R> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (Opt.of(mapping.apply(get()))) : Opt.empty();
	}

	default <R, K> Opt<R> mapToObj_(K a2, @Nonnull LOiFunction.LIntObjFunc<? super K, ? extends R> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (Opt.of(mapping.applyIntObj(get(), a2))) : Opt.empty();
	}

	default <R, K> Opt<R> mapToObjWith(K a1, @Nonnull LOiFunction<? super K, ? extends R> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (Opt.of(mapping.apply(a1, get()))) : Opt.empty();
	}

	// </editor-fold>

	// <editor-fold desc="flatMap">

	default OptBool flatMapToBool(@Nonnull LIntFunction<? extends OptBoolTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptBool.from(mapping.apply(get())) : OptBool.empty();
	}

	default <K> OptBool flatMapToBool_(K a2, @Nonnull LOiFunction.LIntObjFunc<? super K, ? extends OptBoolTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptBool.from(mapping.applyIntObj(get(), a2)) : OptBool.empty();
	}

	default <K> OptBool flatMapToBoolWith(K a1, @Nonnull LOiFunction<? super K, ? extends OptBoolTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptBool.from(mapping.apply(a1, get())) : OptBool.empty();
	}

	default OptByte flatMapToByte(@Nonnull LIntFunction<? extends OptByteTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptByte.from(mapping.apply(get())) : OptByte.empty();
	}

	default <K> OptByte flatMapToByte_(K a2, @Nonnull LOiFunction.LIntObjFunc<? super K, ? extends OptByteTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptByte.from(mapping.applyIntObj(get(), a2)) : OptByte.empty();
	}

	default <K> OptByte flatMapToByteWith(K a1, @Nonnull LOiFunction<? super K, ? extends OptByteTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptByte.from(mapping.apply(a1, get())) : OptByte.empty();
	}

	default OptDbl flatMapToDbl(@Nonnull LIntFunction<? extends OptDblTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptDbl.from(mapping.apply(get())) : OptDbl.empty();
	}

	default <K> OptDbl flatMapToDbl_(K a2, @Nonnull LOiFunction.LIntObjFunc<? super K, ? extends OptDblTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptDbl.from(mapping.applyIntObj(get(), a2)) : OptDbl.empty();
	}

	default <K> OptDbl flatMapToDblWith(K a1, @Nonnull LOiFunction<? super K, ? extends OptDblTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptDbl.from(mapping.apply(a1, get())) : OptDbl.empty();
	}

	default OptChar flatMapToChar(@Nonnull LIntFunction<? extends OptCharTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptChar.from(mapping.apply(get())) : OptChar.empty();
	}

	default <K> OptChar flatMapToChar_(K a2, @Nonnull LOiFunction.LIntObjFunc<? super K, ? extends OptCharTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptChar.from(mapping.applyIntObj(get(), a2)) : OptChar.empty();
	}

	default <K> OptChar flatMapToCharWith(K a1, @Nonnull LOiFunction<? super K, ? extends OptCharTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptChar.from(mapping.apply(a1, get())) : OptChar.empty();
	}

	default OptSrt flatMapToSrt(@Nonnull LIntFunction<? extends OptSrtTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptSrt.from(mapping.apply(get())) : OptSrt.empty();
	}

	default <K> OptSrt flatMapToSrt_(K a2, @Nonnull LOiFunction.LIntObjFunc<? super K, ? extends OptSrtTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptSrt.from(mapping.applyIntObj(get(), a2)) : OptSrt.empty();
	}

	default <K> OptSrt flatMapToSrtWith(K a1, @Nonnull LOiFunction<? super K, ? extends OptSrtTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptSrt.from(mapping.apply(a1, get())) : OptSrt.empty();
	}

	default OptFlt flatMapToFlt(@Nonnull LIntFunction<? extends OptFltTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptFlt.from(mapping.apply(get())) : OptFlt.empty();
	}

	default <K> OptFlt flatMapToFlt_(K a2, @Nonnull LOiFunction.LIntObjFunc<? super K, ? extends OptFltTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptFlt.from(mapping.applyIntObj(get(), a2)) : OptFlt.empty();
	}

	default <K> OptFlt flatMapToFltWith(K a1, @Nonnull LOiFunction<? super K, ? extends OptFltTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptFlt.from(mapping.apply(a1, get())) : OptFlt.empty();
	}

	default OptInt flatMap(@Nonnull LIntFunction<? extends OptIntTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptInt.from(mapping.apply(get())) : OptInt.empty();
	}

	default <K> OptInt flatMap_(K a2, @Nonnull LOiFunction.LIntObjFunc<? super K, ? extends OptIntTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptInt.from(mapping.applyIntObj(get(), a2)) : OptInt.empty();
	}

	default <K> OptInt flatMapWith(K a1, @Nonnull LOiFunction<? super K, ? extends OptIntTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptInt.from(mapping.apply(a1, get())) : OptInt.empty();
	}

	default OptLong flatMapToLong(@Nonnull LIntFunction<? extends OptLongTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptLong.from(mapping.apply(get())) : OptLong.empty();
	}

	default <K> OptLong flatMapToLong_(K a2, @Nonnull LOiFunction.LIntObjFunc<? super K, ? extends OptLongTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptLong.from(mapping.applyIntObj(get(), a2)) : OptLong.empty();
	}

	default <K> OptLong flatMapToLongWith(K a1, @Nonnull LOiFunction<? super K, ? extends OptLongTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptLong.from(mapping.apply(a1, get())) : OptLong.empty();
	}

	default <R> Opt<R> flatMapToObj(@Nonnull LIntFunction<? extends OptTrait<? extends R, ?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? Opt.from(mapping.apply(get())) : Opt.empty();
	}

	default <R, K> Opt<R> flatMapToObj_(K a2, @Nonnull LOiFunction.LIntObjFunc<? super K, ? extends OptTrait<? extends R, ?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? Opt.from(mapping.applyIntObj(get(), a2)) : Opt.empty();
	}

	default <R, K> Opt<R> flatMapToObjWith(K a1, @Nonnull LOiFunction<? super K, ? extends OptTrait<? extends R, ?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? Opt.from(mapping.apply(a1, get())) : Opt.empty();
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

	// </editor-fold>

	// <editor-fold desc="orElse">

	default SELF orThrow() {
		if (isPresent()) {
			return self();
		}

		throw Handling.create(X::noSuchElement);
	}

	default int orElseThrow() {
		if (isPresent()) {
			return get();
		}

		throw Handling.create(X::noSuchElement);
	}

	default SELF orThrow(@Nonnull ExF<RuntimeException> fx) {
		if (isPresent()) {
			return self();
		}

		Null.nonNullArg(fx, "fx");
		throw Handling.create(fx);
	}

	default int orElseThrow(@Nonnull ExF<RuntimeException> fx) {
		if (isPresent()) {
			return get();
		}

		Null.nonNullArg(fx, "fx");
		throw Handling.create(fx);
	}

	default SELF orThrow(@Nonnull ExMF<RuntimeException> fx, @Nullable String msg) {
		if (isPresent()) {
			return self();
		}

		Null.nonNullArg(fx, "fx");
		throw Handling.create(fx, msg);
	}

	default int orElseThrow(@Nonnull ExMF<RuntimeException> fx, @Nullable String msg) {
		if (isPresent()) {
			return get();
		}

		Null.nonNullArg(fx, "fx");
		throw Handling.create(fx, msg);
	}

	default SELF orThrow(@Nonnull ExMF<RuntimeException> fx, @Nullable String msg, Object... args) {
		if (isPresent()) {
			return self();
		}

		Null.nonNullArg(fx, "fx");
		throw Handling.create(fx, msg, args);
	}

	default int orElseThrow(@Nonnull ExMF<RuntimeException> fx, @Nullable String msg, Object... args) {
		if (isPresent()) {
			return get();
		}

		Null.nonNullArg(fx, "fx");
		throw Handling.create(fx, msg, args);
	}

	default int orElse(@Nullable int value) {
		return isPresent() ? get() : value;
	}

	default int orElseGet(@Nonnull LIntSupplier supplier) {
		Null.nonNullArg(supplier, "supplier");
		return isPresent() ? get() : supplier.getAsInt();
	}

	default SELF orGet(@Nonnull LSupplier<? extends OptIntTrait<?>> supplier) {
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

	default <K> SELF orApply(K a1, @Nonnull LFunction<? super K, ? extends OptIntTrait<?>> supplier) {
		Null.nonNullArg(supplier, "supplier");
		return isPresent() ? self() : valueFrom(supplier.apply(a1));
	}

	// </editor-fold>
}
