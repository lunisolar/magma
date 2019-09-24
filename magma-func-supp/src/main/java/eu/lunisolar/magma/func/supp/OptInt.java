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

package eu.lunisolar.magma.func.supp;

import javax.annotation.Nonnull; // NOSONAR
import javax.annotation.Nullable; // NOSONAR
import java.util.Objects; // NOSONAR
import eu.lunisolar.magma.basics.*; // NOSONAR
import eu.lunisolar.magma.basics.builder.*; // NOSONAR
import eu.lunisolar.magma.basics.exceptions.*; // NOSONAR
import eu.lunisolar.magma.basics.meta.*; // NOSONAR
import eu.lunisolar.magma.basics.meta.aType.*; // NOSONAR
import eu.lunisolar.magma.basics.meta.functional.*; // NOSONAR
import eu.lunisolar.magma.basics.meta.functional.type.*; // NOSONAR
import eu.lunisolar.magma.basics.meta.functional.domain.*; // NOSONAR
import eu.lunisolar.magma.func.*; // NOSONAR
import eu.lunisolar.magma.func.supp.memento.*; // NOSONAR
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
public final class OptInt implements FluentSyntax, aValue<aInt> {

	private static final OptInt EMPTY = new OptInt();

	private final int value;
	private final boolean isPresent;

	// <editor-fold desc="factories">

	private OptInt() {
		this.value = 0;
		this.isPresent = false;
	}

	private OptInt(int value) {
		this.value = value;
		this.isPresent = true;
	}

	public static OptInt empty() {
		return EMPTY;
	}

	public static OptInt of(int value) {
		return new OptInt(value);
	}

	// </editor-fold>

	public int get() {
		LLogicalOperator.throwIfNot(isPresent, P::isTrue, X::noSuchElement, "No value present.");
		return value;
	}

	public final boolean isPresent() {
		return isPresent;
	}

	public final boolean isEmpty() {
		return !isPresent;
	}

	// <editor-fold desc="isPresent() dependant boolean terminals">

	public boolean is(@Nonnull LIntPredicate predicate) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && predicate.test(value);
	}

	public boolean is(@Nonnull LBiIntPredicate predicate, int a2) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && predicate.test(value, a2);
	}

	public boolean is(int a2, @Nonnull LBiIntPredicate predicate) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && predicate.test(value, a2);
	}

	public boolean is(@Nonnull LTriIntPredicate predicate, int a2, int a3) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && predicate.test(value, a2, a3);
	}

	public boolean is(int a2, int a3, @Nonnull LTriIntPredicate predicate) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && predicate.test(value, a2, a3);
	}

	public boolean is2_(@Nonnull LBoolIntPredicate.LIntBoolPred predicate, boolean v) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && predicate.testIntBool(value, v);
	}

	public boolean is2_(boolean v, @Nonnull LBoolIntPredicate.LIntBoolPred predicate) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && predicate.testIntBool(value, v);
	}

	public boolean is2_(@Nonnull LByteIntPredicate.LIntBytePred predicate, byte v) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && predicate.testIntByte(value, v);
	}

	public boolean is2_(byte v, @Nonnull LByteIntPredicate.LIntBytePred predicate) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && predicate.testIntByte(value, v);
	}

	public boolean is2_(@Nonnull LDblIntPredicate.LIntDblPred predicate, double v) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && predicate.testIntDbl(value, v);
	}

	public boolean is2_(double v, @Nonnull LDblIntPredicate.LIntDblPred predicate) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && predicate.testIntDbl(value, v);
	}

	public boolean is2_(@Nonnull LCharIntPredicate.LIntCharPred predicate, char v) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && predicate.testIntChar(value, v);
	}

	public boolean is2_(char v, @Nonnull LCharIntPredicate.LIntCharPred predicate) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && predicate.testIntChar(value, v);
	}

	public boolean is2_(@Nonnull LSrtIntPredicate.LIntSrtPred predicate, short v) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && predicate.testIntSrt(value, v);
	}

	public boolean is2_(short v, @Nonnull LSrtIntPredicate.LIntSrtPred predicate) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && predicate.testIntSrt(value, v);
	}

	public boolean is2_(@Nonnull LFltIntPredicate.LIntFltPred predicate, float v) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && predicate.testIntFlt(value, v);
	}

	public boolean is2_(float v, @Nonnull LFltIntPredicate.LIntFltPred predicate) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && predicate.testIntFlt(value, v);
	}

	public boolean is2(@Nonnull LBiIntPredicate predicate, int v) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && predicate.test(value, v);
	}

	public boolean is2(int v, @Nonnull LBiIntPredicate predicate) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && predicate.test(value, v);
	}

	public boolean is2_(@Nonnull LLongIntPredicate.LIntLongPred predicate, long v) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && predicate.testIntLong(value, v);
	}

	public boolean is2_(long v, @Nonnull LLongIntPredicate.LIntLongPred predicate) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && predicate.testIntLong(value, v);
	}

	public <V> boolean is2_(@Nonnull LObjIntPredicate.LIntObjPred<? super V> predicate, V v) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && predicate.testIntObj(value, v);
	}

	public <V> boolean is2_(V v, @Nonnull LObjIntPredicate.LIntObjPred<? super V> predicate) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && predicate.testIntObj(value, v);
	}

	// </editor-fold>

	// <editor-fold desc="filtering">

	public OptInt filter(@Nonnull LIntPredicate predicate) {
		Null.nonNullArg(predicate, "predicate");
		return this.is(predicate) ? this : empty();
	}

	public OptInt filter(@Nonnull LBiIntPredicate predicate, int a2) {
		Null.nonNullArg(predicate, "predicate");
		return this.is(predicate, a2) ? this : empty();
	}

	public OptInt filter(int a2, @Nonnull LBiIntPredicate predicate) {
		Null.nonNullArg(predicate, "predicate");
		return this.is(a2, predicate) ? this : empty();
	}

	public OptInt filter(@Nonnull LTriIntPredicate predicate, int a2, int a3) {
		Null.nonNullArg(predicate, "predicate");
		return this.is(predicate, a2, a3) ? this : empty();
	}

	public OptInt filter(int a2, int a3, @Nonnull LTriIntPredicate predicate) {
		Null.nonNullArg(predicate, "predicate");
		return this.is(a2, a3, predicate) ? this : empty();
	}

	public OptInt filter2_(@Nonnull LBoolIntPredicate.LIntBoolPred predicate, boolean v) {
		Null.nonNullArg(predicate, "predicate");
		return this.is2_(predicate, v) ? this : empty();
	}

	public OptInt filter2_(boolean v, @Nonnull LBoolIntPredicate.LIntBoolPred predicate) {
		Null.nonNullArg(predicate, "predicate");
		return this.is2_(v, predicate) ? this : empty();
	}

	public OptInt filter2_(@Nonnull LByteIntPredicate.LIntBytePred predicate, byte v) {
		Null.nonNullArg(predicate, "predicate");
		return this.is2_(predicate, v) ? this : empty();
	}

	public OptInt filter2_(byte v, @Nonnull LByteIntPredicate.LIntBytePred predicate) {
		Null.nonNullArg(predicate, "predicate");
		return this.is2_(v, predicate) ? this : empty();
	}

	public OptInt filter2_(@Nonnull LDblIntPredicate.LIntDblPred predicate, double v) {
		Null.nonNullArg(predicate, "predicate");
		return this.is2_(predicate, v) ? this : empty();
	}

	public OptInt filter2_(double v, @Nonnull LDblIntPredicate.LIntDblPred predicate) {
		Null.nonNullArg(predicate, "predicate");
		return this.is2_(v, predicate) ? this : empty();
	}

	public OptInt filter2_(@Nonnull LCharIntPredicate.LIntCharPred predicate, char v) {
		Null.nonNullArg(predicate, "predicate");
		return this.is2_(predicate, v) ? this : empty();
	}

	public OptInt filter2_(char v, @Nonnull LCharIntPredicate.LIntCharPred predicate) {
		Null.nonNullArg(predicate, "predicate");
		return this.is2_(v, predicate) ? this : empty();
	}

	public OptInt filter2_(@Nonnull LSrtIntPredicate.LIntSrtPred predicate, short v) {
		Null.nonNullArg(predicate, "predicate");
		return this.is2_(predicate, v) ? this : empty();
	}

	public OptInt filter2_(short v, @Nonnull LSrtIntPredicate.LIntSrtPred predicate) {
		Null.nonNullArg(predicate, "predicate");
		return this.is2_(v, predicate) ? this : empty();
	}

	public OptInt filter2_(@Nonnull LFltIntPredicate.LIntFltPred predicate, float v) {
		Null.nonNullArg(predicate, "predicate");
		return this.is2_(predicate, v) ? this : empty();
	}

	public OptInt filter2_(float v, @Nonnull LFltIntPredicate.LIntFltPred predicate) {
		Null.nonNullArg(predicate, "predicate");
		return this.is2_(v, predicate) ? this : empty();
	}

	public OptInt filter2(@Nonnull LBiIntPredicate predicate, int v) {
		Null.nonNullArg(predicate, "predicate");
		return this.is2(predicate, v) ? this : empty();
	}

	public OptInt filter2(int v, @Nonnull LBiIntPredicate predicate) {
		Null.nonNullArg(predicate, "predicate");
		return this.is2(v, predicate) ? this : empty();
	}

	public OptInt filter2_(@Nonnull LLongIntPredicate.LIntLongPred predicate, long v) {
		Null.nonNullArg(predicate, "predicate");
		return this.is2_(predicate, v) ? this : empty();
	}

	public OptInt filter2_(long v, @Nonnull LLongIntPredicate.LIntLongPred predicate) {
		Null.nonNullArg(predicate, "predicate");
		return this.is2_(v, predicate) ? this : empty();
	}

	public <V> OptInt filter2_(@Nonnull LObjIntPredicate.LIntObjPred<? super V> predicate, V v) {
		Null.nonNullArg(predicate, "predicate");
		return this.is2_(predicate, v) ? this : empty();
	}

	public <V> OptInt filter2_(V v, @Nonnull LObjIntPredicate.LIntObjPred<? super V> predicate) {
		Null.nonNullArg(predicate, "predicate");
		return this.is2_(v, predicate) ? this : empty();
	}

	// </editor-fold>

	// <editor-fold desc="map">

	public OptBool mapToBool(@Nonnull LIntPredicate mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptBool.of(mapping.test(value))) : OptBool.empty();
	}

	public <K> OptBool mapToBool_(K a2, @Nonnull LObjIntPredicate.LIntObjPred<? super K> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptBool.of(mapping.testIntObj(value, a2))) : OptBool.empty();
	}

	public <K> OptBool mapToBoolWith(K a1, @Nonnull LObjIntPredicate<? super K> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptBool.of(mapping.test(a1, value))) : OptBool.empty();
	}

	public OptByte mapToByte(@Nonnull LIntToByteFunction mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptByte.of(mapping.applyAsByte(value))) : OptByte.empty();
	}

	public <K> OptByte mapToByte_(K a2, @Nonnull LOiToByteFunction.LIntObjToByteFunc<? super K> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptByte.of(mapping.applyAsByteIntObj(value, a2))) : OptByte.empty();
	}

	public <K> OptByte mapToByteWith(K a1, @Nonnull LOiToByteFunction<? super K> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptByte.of(mapping.applyAsByte(a1, value))) : OptByte.empty();
	}

	public OptDbl mapToDbl(@Nonnull LIntToDblFunction mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptDbl.of(mapping.applyAsDbl(value))) : OptDbl.empty();
	}

	public <K> OptDbl mapToDbl_(K a2, @Nonnull LOiToDblFunction.LIntObjToDblFunc<? super K> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptDbl.of(mapping.applyAsDblIntObj(value, a2))) : OptDbl.empty();
	}

	public <K> OptDbl mapToDblWith(K a1, @Nonnull LOiToDblFunction<? super K> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptDbl.of(mapping.applyAsDbl(a1, value))) : OptDbl.empty();
	}

	public OptChar mapToChar(@Nonnull LIntToCharFunction mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptChar.of(mapping.applyAsChar(value))) : OptChar.empty();
	}

	public <K> OptChar mapToChar_(K a2, @Nonnull LOiToCharFunction.LIntObjToCharFunc<? super K> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptChar.of(mapping.applyAsCharIntObj(value, a2))) : OptChar.empty();
	}

	public <K> OptChar mapToCharWith(K a1, @Nonnull LOiToCharFunction<? super K> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptChar.of(mapping.applyAsChar(a1, value))) : OptChar.empty();
	}

	public OptSrt mapToSrt(@Nonnull LIntToSrtFunction mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptSrt.of(mapping.applyAsSrt(value))) : OptSrt.empty();
	}

	public <K> OptSrt mapToSrt_(K a2, @Nonnull LOiToSrtFunction.LIntObjToSrtFunc<? super K> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptSrt.of(mapping.applyAsSrtIntObj(value, a2))) : OptSrt.empty();
	}

	public <K> OptSrt mapToSrtWith(K a1, @Nonnull LOiToSrtFunction<? super K> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptSrt.of(mapping.applyAsSrt(a1, value))) : OptSrt.empty();
	}

	public OptFlt mapToFlt(@Nonnull LIntToFltFunction mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptFlt.of(mapping.applyAsFlt(value))) : OptFlt.empty();
	}

	public <K> OptFlt mapToFlt_(K a2, @Nonnull LOiToFltFunction.LIntObjToFltFunc<? super K> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptFlt.of(mapping.applyAsFltIntObj(value, a2))) : OptFlt.empty();
	}

	public <K> OptFlt mapToFltWith(K a1, @Nonnull LOiToFltFunction<? super K> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptFlt.of(mapping.applyAsFlt(a1, value))) : OptFlt.empty();
	}

	public OptInt map(@Nonnull LIntUnaryOperator mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptInt.of(mapping.applyAsInt(value))) : OptInt.empty();
	}

	public <K> OptInt map_(K a2, @Nonnull LOiToIntFunction.LIntObjToIntFunc<? super K> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptInt.of(mapping.applyAsIntIntObj(value, a2))) : OptInt.empty();
	}

	public <K> OptInt mapWith(K a1, @Nonnull LOiToIntFunction<? super K> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptInt.of(mapping.applyAsInt(a1, value))) : OptInt.empty();
	}

	public OptLong mapToLong(@Nonnull LIntToLongFunction mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptLong.of(mapping.applyAsLong(value))) : OptLong.empty();
	}

	public <K> OptLong mapToLong_(K a2, @Nonnull LOiToLongFunction.LIntObjToLongFunc<? super K> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptLong.of(mapping.applyAsLongIntObj(value, a2))) : OptLong.empty();
	}

	public <K> OptLong mapToLongWith(K a1, @Nonnull LOiToLongFunction<? super K> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptLong.of(mapping.applyAsLong(a1, value))) : OptLong.empty();
	}

	public <R> Opt<R> mapToObj(@Nonnull LIntFunction<? extends R> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (Opt.of(mapping.apply(value))) : Opt.empty();
	}

	public <R, K> Opt<R> mapToObj_(K a2, @Nonnull LOiFunction.LIntObjFunc<? super K, ? extends R> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (Opt.of(mapping.applyIntObj(value, a2))) : Opt.empty();
	}

	public <R, K> Opt<R> mapToObjWith(K a1, @Nonnull LOiFunction<? super K, ? extends R> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (Opt.of(mapping.apply(a1, value))) : Opt.empty();
	}

	// </editor-fold>

	// <editor-fold desc="flatMap">

	public OptBool flatMapToBool(@Nonnull LIntFunction<? extends OptBool> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? Null.nonNull(mapping.apply(value)) : OptBool.empty();
	}

	public <K> OptBool flatMapToBool_(K a2, @Nonnull LOiFunction.LIntObjFunc<? super K, ? extends OptBool> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? Null.nonNull(mapping.applyIntObj(value, a2)) : OptBool.empty();
	}

	public <K> OptBool flatMapToBoolWith(K a1, @Nonnull LOiFunction<? super K, ? extends OptBool> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? Null.nonNull(mapping.apply(a1, value)) : OptBool.empty();
	}

	public OptByte flatMapToByte(@Nonnull LIntFunction<? extends OptByte> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? Null.nonNull(mapping.apply(value)) : OptByte.empty();
	}

	public <K> OptByte flatMapToByte_(K a2, @Nonnull LOiFunction.LIntObjFunc<? super K, ? extends OptByte> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? Null.nonNull(mapping.applyIntObj(value, a2)) : OptByte.empty();
	}

	public <K> OptByte flatMapToByteWith(K a1, @Nonnull LOiFunction<? super K, ? extends OptByte> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? Null.nonNull(mapping.apply(a1, value)) : OptByte.empty();
	}

	public OptDbl flatMapToDbl(@Nonnull LIntFunction<? extends OptDbl> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? Null.nonNull(mapping.apply(value)) : OptDbl.empty();
	}

	public <K> OptDbl flatMapToDbl_(K a2, @Nonnull LOiFunction.LIntObjFunc<? super K, ? extends OptDbl> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? Null.nonNull(mapping.applyIntObj(value, a2)) : OptDbl.empty();
	}

	public <K> OptDbl flatMapToDblWith(K a1, @Nonnull LOiFunction<? super K, ? extends OptDbl> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? Null.nonNull(mapping.apply(a1, value)) : OptDbl.empty();
	}

	public OptChar flatMapToChar(@Nonnull LIntFunction<? extends OptChar> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? Null.nonNull(mapping.apply(value)) : OptChar.empty();
	}

	public <K> OptChar flatMapToChar_(K a2, @Nonnull LOiFunction.LIntObjFunc<? super K, ? extends OptChar> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? Null.nonNull(mapping.applyIntObj(value, a2)) : OptChar.empty();
	}

	public <K> OptChar flatMapToCharWith(K a1, @Nonnull LOiFunction<? super K, ? extends OptChar> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? Null.nonNull(mapping.apply(a1, value)) : OptChar.empty();
	}

	public OptSrt flatMapToSrt(@Nonnull LIntFunction<? extends OptSrt> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? Null.nonNull(mapping.apply(value)) : OptSrt.empty();
	}

	public <K> OptSrt flatMapToSrt_(K a2, @Nonnull LOiFunction.LIntObjFunc<? super K, ? extends OptSrt> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? Null.nonNull(mapping.applyIntObj(value, a2)) : OptSrt.empty();
	}

	public <K> OptSrt flatMapToSrtWith(K a1, @Nonnull LOiFunction<? super K, ? extends OptSrt> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? Null.nonNull(mapping.apply(a1, value)) : OptSrt.empty();
	}

	public OptFlt flatMapToFlt(@Nonnull LIntFunction<? extends OptFlt> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? Null.nonNull(mapping.apply(value)) : OptFlt.empty();
	}

	public <K> OptFlt flatMapToFlt_(K a2, @Nonnull LOiFunction.LIntObjFunc<? super K, ? extends OptFlt> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? Null.nonNull(mapping.applyIntObj(value, a2)) : OptFlt.empty();
	}

	public <K> OptFlt flatMapToFltWith(K a1, @Nonnull LOiFunction<? super K, ? extends OptFlt> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? Null.nonNull(mapping.apply(a1, value)) : OptFlt.empty();
	}

	public OptInt flatMap(@Nonnull LIntFunction<? extends OptInt> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? Null.nonNull(mapping.apply(value)) : OptInt.empty();
	}

	public <K> OptInt flatMap_(K a2, @Nonnull LOiFunction.LIntObjFunc<? super K, ? extends OptInt> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? Null.nonNull(mapping.applyIntObj(value, a2)) : OptInt.empty();
	}

	public <K> OptInt flatMapWith(K a1, @Nonnull LOiFunction<? super K, ? extends OptInt> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? Null.nonNull(mapping.apply(a1, value)) : OptInt.empty();
	}

	public OptLong flatMapToLong(@Nonnull LIntFunction<? extends OptLong> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? Null.nonNull(mapping.apply(value)) : OptLong.empty();
	}

	public <K> OptLong flatMapToLong_(K a2, @Nonnull LOiFunction.LIntObjFunc<? super K, ? extends OptLong> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? Null.nonNull(mapping.applyIntObj(value, a2)) : OptLong.empty();
	}

	public <K> OptLong flatMapToLongWith(K a1, @Nonnull LOiFunction<? super K, ? extends OptLong> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? Null.nonNull(mapping.apply(a1, value)) : OptLong.empty();
	}

	public <R> Opt<R> flatMapToObj(@Nonnull LIntFunction<? extends Opt<R>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? Null.nonNull(mapping.apply(value)) : Opt.empty();
	}

	public <R, K> Opt<R> flatMapToObj_(K a2, @Nonnull LOiFunction.LIntObjFunc<? super K, ? extends Opt<R>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? Null.nonNull(mapping.applyIntObj(value, a2)) : Opt.empty();
	}

	public <R, K> Opt<R> flatMapToObjWith(K a1, @Nonnull LOiFunction<? super K, ? extends Opt<R>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? Null.nonNull(mapping.apply(a1, value)) : Opt.empty();
	}

	// </editor-fold>

	// <editor-fold desc="ifPresent">

	public OptInt ifPresent(@Nonnull LIntConsumer action) {
		Null.nonNullArg(action, "action");
		if (isPresent()) {
			action.accept(value);
		}
		return this;
	}

	public OptInt ifPresent(@Nonnull LIntConsumer action, @Nonnull LAction emptyAction) {
		Null.nonNullArg(action, "action");
		if (isPresent()) {
			action.accept(value);
		} else {
			emptyAction.execute();
		}
		return this;
	}

	public <K> OptInt ifPresent_(K a1, @Nonnull LObjIntConsumer.LIntObjCons<? super K> action) {
		Null.nonNullArg(action, "action");
		if (isPresent()) {
			action.acceptIntObj(value, a1);
		}
		return this;
	}

	public <K> OptInt ifPresentWith(K a1, @Nonnull LObjIntConsumer<? super K> action) {
		Null.nonNullArg(action, "action");
		if (isPresent()) {
			action.accept(a1, value);
		}
		return this;
	}

	// </editor-fold>

	// <editor-fold desc="orElse">

	public int orElse(int other) {
		return isPresent() ? value : other;
	}

	public int orElseThrow() {
		if (isPresent()) {
			return value;
		}

		throw Handling.create(X::noSuchElement);
	}

	public int orElseThrow(@Nonnull ExF<RuntimeException> fx) {
		if (isPresent()) {
			return value;
		}

		Null.nonNullArg(fx, "fx");
		throw Handling.create(fx);
	}

	public int orElseThrow(@Nonnull ExMF<RuntimeException> fx, @Nullable String msg) {
		if (isPresent()) {
			return value;
		}

		Null.nonNullArg(fx, "fx");
		throw Handling.create(fx, msg);
	}

	public int orElse(@Nonnull LIntSupplier supplier) {
		Null.nonNullArg(supplier, "supplier");
		return isPresent() ? value : supplier.getAsInt();
	}

	public <K> int orElse(K a1, @Nonnull LToIntFunction<? super K> supplier) {
		Null.nonNullArg(supplier, "supplier");
		return isPresent() ? value : supplier.applyAsInt(a1);
	}

	// </editor-fold>

	// <editor-fold desc="equals/hashcode/toString">

	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof OptInt)) {
			return false;
		}

		OptInt other = (OptInt) obj;
		return (isPresent() && other.isPresent()) ? value == other.value : isPresent() == other.isPresent();
	}

	public int hashCode() {
		return isPresent() ? Integer.hashCode(value) : 0;
	}

	public String toString() {
		return isPresent() ? String.format("OptInt[%s]", value) : "OptInt.empty";
	}

	// </editor-fold>

}
