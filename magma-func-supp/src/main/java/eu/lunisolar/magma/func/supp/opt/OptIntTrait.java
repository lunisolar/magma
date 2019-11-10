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
public interface OptIntTrait<SELF extends OptIntTrait<SELF>> extends Fluent<SELF>, aValue<aInt>, CheckIntTrait<SELF> {

	int get();

	public boolean isPresent();

	default boolean isEmpty() {
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

	public default boolean is(@Nonnull LIntPredicate predicate) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && predicate.test(get());
	}

	public default boolean is(@Nonnull LBiIntPredicate predicate, int a2) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && predicate.test(get(), a2);
	}

	public default boolean is(int a2, @Nonnull LBiIntPredicate predicate) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && predicate.test(get(), a2);
	}

	public default boolean is(@Nonnull LTriIntPredicate predicate, int a2, int a3) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && predicate.test(get(), a2, a3);
	}

	public default boolean is(int a2, int a3, @Nonnull LTriIntPredicate predicate) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && predicate.test(get(), a2, a3);
	}

	public default boolean is2_(@Nonnull LBoolIntPredicate.LIntBoolPred predicate, boolean v) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && predicate.testIntBool(get(), v);
	}

	public default boolean is2_(boolean v, @Nonnull LBoolIntPredicate.LIntBoolPred predicate) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && predicate.testIntBool(get(), v);
	}

	public default boolean is2_(@Nonnull LByteIntPredicate.LIntBytePred predicate, byte v) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && predicate.testIntByte(get(), v);
	}

	public default boolean is2_(byte v, @Nonnull LByteIntPredicate.LIntBytePred predicate) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && predicate.testIntByte(get(), v);
	}

	public default boolean is2_(@Nonnull LDblIntPredicate.LIntDblPred predicate, double v) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && predicate.testIntDbl(get(), v);
	}

	public default boolean is2_(double v, @Nonnull LDblIntPredicate.LIntDblPred predicate) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && predicate.testIntDbl(get(), v);
	}

	public default boolean is2_(@Nonnull LCharIntPredicate.LIntCharPred predicate, char v) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && predicate.testIntChar(get(), v);
	}

	public default boolean is2_(char v, @Nonnull LCharIntPredicate.LIntCharPred predicate) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && predicate.testIntChar(get(), v);
	}

	public default boolean is2_(@Nonnull LSrtIntPredicate.LIntSrtPred predicate, short v) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && predicate.testIntSrt(get(), v);
	}

	public default boolean is2_(short v, @Nonnull LSrtIntPredicate.LIntSrtPred predicate) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && predicate.testIntSrt(get(), v);
	}

	public default boolean is2_(@Nonnull LFltIntPredicate.LIntFltPred predicate, float v) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && predicate.testIntFlt(get(), v);
	}

	public default boolean is2_(float v, @Nonnull LFltIntPredicate.LIntFltPred predicate) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && predicate.testIntFlt(get(), v);
	}

	public default boolean is2(@Nonnull LBiIntPredicate predicate, int v) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && predicate.test(get(), v);
	}

	public default boolean is2(int v, @Nonnull LBiIntPredicate predicate) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && predicate.test(get(), v);
	}

	public default boolean is2_(@Nonnull LLongIntPredicate.LIntLongPred predicate, long v) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && predicate.testIntLong(get(), v);
	}

	public default boolean is2_(long v, @Nonnull LLongIntPredicate.LIntLongPred predicate) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && predicate.testIntLong(get(), v);
	}

	public default <V> boolean is2_(@Nonnull LObjIntPredicate.LIntObjPred<? super V> predicate, V v) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && predicate.testIntObj(get(), v);
	}

	public default <V> boolean is2_(V v, @Nonnull LObjIntPredicate.LIntObjPred<? super V> predicate) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && predicate.testIntObj(get(), v);
	}

	// </editor-fold>

	// <editor-fold desc="filtering">

	public default OptInt filter(@Nonnull LIntPredicate predicate) {
		Null.nonNullArg(predicate, "predicate");
		return this.is(predicate) ? OptInt.toOpt(self()) : OptInt.empty();
	}

	public default OptInt filter(@Nonnull LBiIntPredicate predicate, int a2) {
		Null.nonNullArg(predicate, "predicate");
		return this.is(predicate, a2) ? OptInt.toOpt(self()) : OptInt.empty();
	}

	public default OptInt filter(int a2, @Nonnull LBiIntPredicate predicate) {
		Null.nonNullArg(predicate, "predicate");
		return this.is(a2, predicate) ? OptInt.toOpt(self()) : OptInt.empty();
	}

	public default OptInt filter(@Nonnull LTriIntPredicate predicate, int a2, int a3) {
		Null.nonNullArg(predicate, "predicate");
		return this.is(predicate, a2, a3) ? OptInt.toOpt(self()) : OptInt.empty();
	}

	public default OptInt filter(int a2, int a3, @Nonnull LTriIntPredicate predicate) {
		Null.nonNullArg(predicate, "predicate");
		return this.is(a2, a3, predicate) ? OptInt.toOpt(self()) : OptInt.empty();
	}

	public default OptInt filter2_(@Nonnull LBoolIntPredicate.LIntBoolPred predicate, boolean v) {
		Null.nonNullArg(predicate, "predicate");
		return this.is2_(predicate, v) ? OptInt.toOpt(self()) : OptInt.empty();
	}

	public default OptInt filter2_(boolean v, @Nonnull LBoolIntPredicate.LIntBoolPred predicate) {
		Null.nonNullArg(predicate, "predicate");
		return this.is2_(v, predicate) ? OptInt.toOpt(self()) : OptInt.empty();
	}

	public default OptInt filter2_(@Nonnull LByteIntPredicate.LIntBytePred predicate, byte v) {
		Null.nonNullArg(predicate, "predicate");
		return this.is2_(predicate, v) ? OptInt.toOpt(self()) : OptInt.empty();
	}

	public default OptInt filter2_(byte v, @Nonnull LByteIntPredicate.LIntBytePred predicate) {
		Null.nonNullArg(predicate, "predicate");
		return this.is2_(v, predicate) ? OptInt.toOpt(self()) : OptInt.empty();
	}

	public default OptInt filter2_(@Nonnull LDblIntPredicate.LIntDblPred predicate, double v) {
		Null.nonNullArg(predicate, "predicate");
		return this.is2_(predicate, v) ? OptInt.toOpt(self()) : OptInt.empty();
	}

	public default OptInt filter2_(double v, @Nonnull LDblIntPredicate.LIntDblPred predicate) {
		Null.nonNullArg(predicate, "predicate");
		return this.is2_(v, predicate) ? OptInt.toOpt(self()) : OptInt.empty();
	}

	public default OptInt filter2_(@Nonnull LCharIntPredicate.LIntCharPred predicate, char v) {
		Null.nonNullArg(predicate, "predicate");
		return this.is2_(predicate, v) ? OptInt.toOpt(self()) : OptInt.empty();
	}

	public default OptInt filter2_(char v, @Nonnull LCharIntPredicate.LIntCharPred predicate) {
		Null.nonNullArg(predicate, "predicate");
		return this.is2_(v, predicate) ? OptInt.toOpt(self()) : OptInt.empty();
	}

	public default OptInt filter2_(@Nonnull LSrtIntPredicate.LIntSrtPred predicate, short v) {
		Null.nonNullArg(predicate, "predicate");
		return this.is2_(predicate, v) ? OptInt.toOpt(self()) : OptInt.empty();
	}

	public default OptInt filter2_(short v, @Nonnull LSrtIntPredicate.LIntSrtPred predicate) {
		Null.nonNullArg(predicate, "predicate");
		return this.is2_(v, predicate) ? OptInt.toOpt(self()) : OptInt.empty();
	}

	public default OptInt filter2_(@Nonnull LFltIntPredicate.LIntFltPred predicate, float v) {
		Null.nonNullArg(predicate, "predicate");
		return this.is2_(predicate, v) ? OptInt.toOpt(self()) : OptInt.empty();
	}

	public default OptInt filter2_(float v, @Nonnull LFltIntPredicate.LIntFltPred predicate) {
		Null.nonNullArg(predicate, "predicate");
		return this.is2_(v, predicate) ? OptInt.toOpt(self()) : OptInt.empty();
	}

	public default OptInt filter2(@Nonnull LBiIntPredicate predicate, int v) {
		Null.nonNullArg(predicate, "predicate");
		return this.is2(predicate, v) ? OptInt.toOpt(self()) : OptInt.empty();
	}

	public default OptInt filter2(int v, @Nonnull LBiIntPredicate predicate) {
		Null.nonNullArg(predicate, "predicate");
		return this.is2(v, predicate) ? OptInt.toOpt(self()) : OptInt.empty();
	}

	public default OptInt filter2_(@Nonnull LLongIntPredicate.LIntLongPred predicate, long v) {
		Null.nonNullArg(predicate, "predicate");
		return this.is2_(predicate, v) ? OptInt.toOpt(self()) : OptInt.empty();
	}

	public default OptInt filter2_(long v, @Nonnull LLongIntPredicate.LIntLongPred predicate) {
		Null.nonNullArg(predicate, "predicate");
		return this.is2_(v, predicate) ? OptInt.toOpt(self()) : OptInt.empty();
	}

	public default <V> OptInt filter2_(@Nonnull LObjIntPredicate.LIntObjPred<? super V> predicate, V v) {
		Null.nonNullArg(predicate, "predicate");
		return this.is2_(predicate, v) ? OptInt.toOpt(self()) : OptInt.empty();
	}

	public default <V> OptInt filter2_(V v, @Nonnull LObjIntPredicate.LIntObjPred<? super V> predicate) {
		Null.nonNullArg(predicate, "predicate");
		return this.is2_(v, predicate) ? OptInt.toOpt(self()) : OptInt.empty();
	}

	// </editor-fold>

	// <editor-fold desc="map">

	public default OptBool mapToBool(@Nonnull LIntPredicate mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptBool.of(mapping.test(get()))) : OptBool.empty();
	}

	public default <K> OptBool mapToBool_(K a2, @Nonnull LObjIntPredicate.LIntObjPred<? super K> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptBool.of(mapping.testIntObj(get(), a2))) : OptBool.empty();
	}

	public default <K> OptBool mapToBoolWith(K a1, @Nonnull LObjIntPredicate<? super K> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptBool.of(mapping.test(a1, get()))) : OptBool.empty();
	}

	public default OptByte mapToByte(@Nonnull LIntToByteFunction mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptByte.of(mapping.applyAsByte(get()))) : OptByte.empty();
	}

	public default <K> OptByte mapToByte_(K a2, @Nonnull LOiToByteFunction.LIntObjToByteFunc<? super K> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptByte.of(mapping.applyAsByteIntObj(get(), a2))) : OptByte.empty();
	}

	public default <K> OptByte mapToByteWith(K a1, @Nonnull LOiToByteFunction<? super K> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptByte.of(mapping.applyAsByte(a1, get()))) : OptByte.empty();
	}

	public default OptDbl mapToDbl(@Nonnull LIntToDblFunction mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptDbl.of(mapping.applyAsDbl(get()))) : OptDbl.empty();
	}

	public default <K> OptDbl mapToDbl_(K a2, @Nonnull LOiToDblFunction.LIntObjToDblFunc<? super K> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptDbl.of(mapping.applyAsDblIntObj(get(), a2))) : OptDbl.empty();
	}

	public default <K> OptDbl mapToDblWith(K a1, @Nonnull LOiToDblFunction<? super K> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptDbl.of(mapping.applyAsDbl(a1, get()))) : OptDbl.empty();
	}

	public default OptChar mapToChar(@Nonnull LIntToCharFunction mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptChar.of(mapping.applyAsChar(get()))) : OptChar.empty();
	}

	public default <K> OptChar mapToChar_(K a2, @Nonnull LOiToCharFunction.LIntObjToCharFunc<? super K> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptChar.of(mapping.applyAsCharIntObj(get(), a2))) : OptChar.empty();
	}

	public default <K> OptChar mapToCharWith(K a1, @Nonnull LOiToCharFunction<? super K> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptChar.of(mapping.applyAsChar(a1, get()))) : OptChar.empty();
	}

	public default OptSrt mapToSrt(@Nonnull LIntToSrtFunction mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptSrt.of(mapping.applyAsSrt(get()))) : OptSrt.empty();
	}

	public default <K> OptSrt mapToSrt_(K a2, @Nonnull LOiToSrtFunction.LIntObjToSrtFunc<? super K> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptSrt.of(mapping.applyAsSrtIntObj(get(), a2))) : OptSrt.empty();
	}

	public default <K> OptSrt mapToSrtWith(K a1, @Nonnull LOiToSrtFunction<? super K> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptSrt.of(mapping.applyAsSrt(a1, get()))) : OptSrt.empty();
	}

	public default OptFlt mapToFlt(@Nonnull LIntToFltFunction mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptFlt.of(mapping.applyAsFlt(get()))) : OptFlt.empty();
	}

	public default <K> OptFlt mapToFlt_(K a2, @Nonnull LOiToFltFunction.LIntObjToFltFunc<? super K> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptFlt.of(mapping.applyAsFltIntObj(get(), a2))) : OptFlt.empty();
	}

	public default <K> OptFlt mapToFltWith(K a1, @Nonnull LOiToFltFunction<? super K> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptFlt.of(mapping.applyAsFlt(a1, get()))) : OptFlt.empty();
	}

	public default OptInt map(@Nonnull LIntUnaryOperator mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptInt.of(mapping.applyAsInt(get()))) : OptInt.empty();
	}

	public default <K> OptInt map_(K a2, @Nonnull LOiToIntFunction.LIntObjToIntFunc<? super K> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptInt.of(mapping.applyAsIntIntObj(get(), a2))) : OptInt.empty();
	}

	public default <K> OptInt mapWith(K a1, @Nonnull LOiToIntFunction<? super K> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptInt.of(mapping.applyAsInt(a1, get()))) : OptInt.empty();
	}

	public default OptLong mapToLong(@Nonnull LIntToLongFunction mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptLong.of(mapping.applyAsLong(get()))) : OptLong.empty();
	}

	public default <K> OptLong mapToLong_(K a2, @Nonnull LOiToLongFunction.LIntObjToLongFunc<? super K> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptLong.of(mapping.applyAsLongIntObj(get(), a2))) : OptLong.empty();
	}

	public default <K> OptLong mapToLongWith(K a1, @Nonnull LOiToLongFunction<? super K> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptLong.of(mapping.applyAsLong(a1, get()))) : OptLong.empty();
	}

	public default <R> Opt<R> mapToObj(@Nonnull LIntFunction<? extends R> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (Opt.of(mapping.apply(get()))) : Opt.empty();
	}

	public default <R, K> Opt<R> mapToObj_(K a2, @Nonnull LOiFunction.LIntObjFunc<? super K, ? extends R> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (Opt.of(mapping.applyIntObj(get(), a2))) : Opt.empty();
	}

	public default <R, K> Opt<R> mapToObjWith(K a1, @Nonnull LOiFunction<? super K, ? extends R> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (Opt.of(mapping.apply(a1, get()))) : Opt.empty();
	}

	// </editor-fold>

	// <editor-fold desc="flatMap">

	public default OptBool flatMapToBool(@Nonnull LIntFunction<? extends OptBoolTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptBool.toOpt(mapping.apply(get())) : OptBool.empty();
	}

	public default <K> OptBool flatMapToBool_(K a2, @Nonnull LOiFunction.LIntObjFunc<? super K, ? extends OptBoolTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptBool.toOpt(mapping.applyIntObj(get(), a2)) : OptBool.empty();
	}

	public default <K> OptBool flatMapToBoolWith(K a1, @Nonnull LOiFunction<? super K, ? extends OptBoolTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptBool.toOpt(mapping.apply(a1, get())) : OptBool.empty();
	}

	public default OptByte flatMapToByte(@Nonnull LIntFunction<? extends OptByteTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptByte.toOpt(mapping.apply(get())) : OptByte.empty();
	}

	public default <K> OptByte flatMapToByte_(K a2, @Nonnull LOiFunction.LIntObjFunc<? super K, ? extends OptByteTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptByte.toOpt(mapping.applyIntObj(get(), a2)) : OptByte.empty();
	}

	public default <K> OptByte flatMapToByteWith(K a1, @Nonnull LOiFunction<? super K, ? extends OptByteTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptByte.toOpt(mapping.apply(a1, get())) : OptByte.empty();
	}

	public default OptDbl flatMapToDbl(@Nonnull LIntFunction<? extends OptDblTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptDbl.toOpt(mapping.apply(get())) : OptDbl.empty();
	}

	public default <K> OptDbl flatMapToDbl_(K a2, @Nonnull LOiFunction.LIntObjFunc<? super K, ? extends OptDblTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptDbl.toOpt(mapping.applyIntObj(get(), a2)) : OptDbl.empty();
	}

	public default <K> OptDbl flatMapToDblWith(K a1, @Nonnull LOiFunction<? super K, ? extends OptDblTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptDbl.toOpt(mapping.apply(a1, get())) : OptDbl.empty();
	}

	public default OptChar flatMapToChar(@Nonnull LIntFunction<? extends OptCharTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptChar.toOpt(mapping.apply(get())) : OptChar.empty();
	}

	public default <K> OptChar flatMapToChar_(K a2, @Nonnull LOiFunction.LIntObjFunc<? super K, ? extends OptCharTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptChar.toOpt(mapping.applyIntObj(get(), a2)) : OptChar.empty();
	}

	public default <K> OptChar flatMapToCharWith(K a1, @Nonnull LOiFunction<? super K, ? extends OptCharTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptChar.toOpt(mapping.apply(a1, get())) : OptChar.empty();
	}

	public default OptSrt flatMapToSrt(@Nonnull LIntFunction<? extends OptSrtTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptSrt.toOpt(mapping.apply(get())) : OptSrt.empty();
	}

	public default <K> OptSrt flatMapToSrt_(K a2, @Nonnull LOiFunction.LIntObjFunc<? super K, ? extends OptSrtTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptSrt.toOpt(mapping.applyIntObj(get(), a2)) : OptSrt.empty();
	}

	public default <K> OptSrt flatMapToSrtWith(K a1, @Nonnull LOiFunction<? super K, ? extends OptSrtTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptSrt.toOpt(mapping.apply(a1, get())) : OptSrt.empty();
	}

	public default OptFlt flatMapToFlt(@Nonnull LIntFunction<? extends OptFltTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptFlt.toOpt(mapping.apply(get())) : OptFlt.empty();
	}

	public default <K> OptFlt flatMapToFlt_(K a2, @Nonnull LOiFunction.LIntObjFunc<? super K, ? extends OptFltTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptFlt.toOpt(mapping.applyIntObj(get(), a2)) : OptFlt.empty();
	}

	public default <K> OptFlt flatMapToFltWith(K a1, @Nonnull LOiFunction<? super K, ? extends OptFltTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptFlt.toOpt(mapping.apply(a1, get())) : OptFlt.empty();
	}

	public default OptInt flatMap(@Nonnull LIntFunction<? extends OptIntTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptInt.toOpt(mapping.apply(get())) : OptInt.empty();
	}

	public default <K> OptInt flatMap_(K a2, @Nonnull LOiFunction.LIntObjFunc<? super K, ? extends OptIntTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptInt.toOpt(mapping.applyIntObj(get(), a2)) : OptInt.empty();
	}

	public default <K> OptInt flatMapWith(K a1, @Nonnull LOiFunction<? super K, ? extends OptIntTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptInt.toOpt(mapping.apply(a1, get())) : OptInt.empty();
	}

	public default OptLong flatMapToLong(@Nonnull LIntFunction<? extends OptLongTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptLong.toOpt(mapping.apply(get())) : OptLong.empty();
	}

	public default <K> OptLong flatMapToLong_(K a2, @Nonnull LOiFunction.LIntObjFunc<? super K, ? extends OptLongTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptLong.toOpt(mapping.applyIntObj(get(), a2)) : OptLong.empty();
	}

	public default <K> OptLong flatMapToLongWith(K a1, @Nonnull LOiFunction<? super K, ? extends OptLongTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptLong.toOpt(mapping.apply(a1, get())) : OptLong.empty();
	}

	public default <R> Opt<R> flatMapToObj(@Nonnull LIntFunction<? extends OptTrait<? extends R, ?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? Opt.toOpt(mapping.apply(get())) : Opt.empty();
	}

	public default <R, K> Opt<R> flatMapToObj_(K a2, @Nonnull LOiFunction.LIntObjFunc<? super K, ? extends OptTrait<? extends R, ?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? Opt.toOpt(mapping.applyIntObj(get(), a2)) : Opt.empty();
	}

	public default <R, K> Opt<R> flatMapToObjWith(K a1, @Nonnull LOiFunction<? super K, ? extends OptTrait<? extends R, ?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? Opt.toOpt(mapping.apply(a1, get())) : Opt.empty();
	}

	// </editor-fold>

	// <editor-fold desc="ifPresent">

	public default SELF ifPresent(@Nonnull LIntConsumer action) {
		Null.nonNullArg(action, "action");
		if (isPresent()) {
			action.accept(get());
		}
		return self();
	}

	public default SELF ifPresent(@Nonnull LIntConsumer action, @Nonnull LAction emptyAction) {
		Null.nonNullArg(action, "action");
		if (isPresent()) {
			action.accept(get());
		} else {
			emptyAction.execute();
		}
		return self();
	}

	public default <K> SELF ifPresent_(K a1, @Nonnull LObjIntConsumer.LIntObjCons<? super K> action) {
		Null.nonNullArg(action, "action");
		if (isPresent()) {
			action.acceptIntObj(get(), a1);
		}
		return self();
	}

	public default <K> SELF ifPresentWith(K a1, @Nonnull LObjIntConsumer<? super K> action) {
		Null.nonNullArg(action, "action");
		if (isPresent()) {
			action.accept(a1, get());
		}
		return self();
	}

	// </editor-fold>

	// <editor-fold desc="orElse">

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

	public default int orElse(@Nullable int value) {
		return isPresent() ? get() : value;
	}

	public default int orElseGet(@Nonnull LIntSupplier supplier) {
		Null.nonNullArg(supplier, "supplier");
		return isPresent() ? get() : supplier.getAsInt();
	}

	public default OptInt orGet(@Nonnull LSupplier<? extends OptIntTrait<?>> supplier) {
		Null.nonNullArg(supplier, "supplier");
		return isPresent() ? OptInt.toOpt(self()) : OptInt.toOpt(supplier.get());
	}

	public default OptInt or(@Nullable int value) {
		return isPresent() ? OptInt.toOpt(self()) : OptInt.of(value);
	}

	public default OptInt orOpt(@Nonnull OptIntTrait<?> opt) {
		Null.nonNullArg(opt, "opt");
		return isPresent() ? OptInt.toOpt(self()) : OptInt.toOpt(opt);
	}

	public default <K> int orElseApply(K a1, @Nonnull LToIntFunction<? super K> supplier) {
		Null.nonNullArg(supplier, "supplier");
		return isPresent() ? get() : supplier.applyAsInt(a1);
	}

	public default <K> OptInt orApply(K a1, @Nonnull LFunction<? super K, ? extends OptIntTrait<?>> supplier) {
		Null.nonNullArg(supplier, "supplier");
		return isPresent() ? OptInt.toOpt(self()) : OptInt.toOpt(supplier.apply(a1));
	}

	// </editor-fold>
}
