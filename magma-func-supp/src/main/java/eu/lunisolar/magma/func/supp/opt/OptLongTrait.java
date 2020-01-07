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
public interface OptLongTrait<SELF extends OptLongTrait<SELF>> extends Fluent<SELF>, aValue<aLong>, CheckLongTrait<SELF> {

	// <editor-fold desc="forcing ValueTrait re-implementation">

	@Nonnull
	SELF value(long value);
	@Nonnull
	SELF voidValue();

	// </editor-fold>

	long get();

	default @Nullable Long nullable() {
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

	default OptionalLong toOpt() {
		if (isPresent()) {
			return OptionalLong.of(get());
		} else {
			return OptionalLong.empty();
		}
	}

	// <editor-fold desc="isPresent() dependant boolean terminals">

	default boolean is(@Nonnull LLongPredicate predicate) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && predicate.test(get());
	}

	default boolean is(@Nonnull LBiLongPredicate predicate, long a2) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && predicate.test(get(), a2);
	}

	default boolean is(long a2, @Nonnull LBiLongPredicate predicate) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && predicate.test(get(), a2);
	}

	default boolean is(@Nonnull LTriLongPredicate predicate, long a2, long a3) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && predicate.test(get(), a2, a3);
	}

	default boolean is(long a2, long a3, @Nonnull LTriLongPredicate predicate) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && predicate.test(get(), a2, a3);
	}

	default boolean is2(@Nonnull LLongIntPredicate predicate, int v) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && predicate.test(get(), v);
	}

	default boolean is2(int v, @Nonnull LLongIntPredicate predicate) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && predicate.test(get(), v);
	}

	default <V> boolean is2_(@Nonnull LObjLongPredicate.LLongObjPred<? super V> predicate, V v) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && predicate.testLongObj(get(), v);
	}

	default <V> boolean is2_(V v, @Nonnull LObjLongPredicate.LLongObjPred<? super V> predicate) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && predicate.testLongObj(get(), v);
	}

	// </editor-fold>

	// <editor-fold desc="filtering">

	default SELF filter(@Nonnull LLongPredicate predicate) {
		Null.nonNullArg(predicate, "predicate");
		return this.is(predicate) ? self() : voidValue();
	}

	default SELF filter(@Nonnull LBiLongPredicate predicate, long a2) {
		Null.nonNullArg(predicate, "predicate");
		return this.is(predicate, a2) ? self() : voidValue();
	}

	default SELF filter(long a2, @Nonnull LBiLongPredicate predicate) {
		Null.nonNullArg(predicate, "predicate");
		return this.is(a2, predicate) ? self() : voidValue();
	}

	default SELF filter(@Nonnull LTriLongPredicate predicate, long a2, long a3) {
		Null.nonNullArg(predicate, "predicate");
		return this.is(predicate, a2, a3) ? self() : voidValue();
	}

	default SELF filter(long a2, long a3, @Nonnull LTriLongPredicate predicate) {
		Null.nonNullArg(predicate, "predicate");
		return this.is(a2, a3, predicate) ? self() : voidValue();
	}

	default SELF filter2(@Nonnull LLongIntPredicate predicate, int v) {
		Null.nonNullArg(predicate, "predicate");
		return this.is2(predicate, v) ? self() : voidValue();
	}

	default SELF filter2(int v, @Nonnull LLongIntPredicate predicate) {
		Null.nonNullArg(predicate, "predicate");
		return this.is2(v, predicate) ? self() : voidValue();
	}

	default <V> SELF filter2_(@Nonnull LObjLongPredicate.LLongObjPred<? super V> predicate, V v) {
		Null.nonNullArg(predicate, "predicate");
		return this.is2_(predicate, v) ? self() : voidValue();
	}

	default <V> SELF filter2_(V v, @Nonnull LObjLongPredicate.LLongObjPred<? super V> predicate) {
		Null.nonNullArg(predicate, "predicate");
		return this.is2_(v, predicate) ? self() : voidValue();
	}

	// </editor-fold>

	// <editor-fold desc="map">

	default OptBool mapToBool(@Nonnull LLongPredicate mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptBool.of(mapping.test(get()))) : OptBool.empty();
	}

	default <K> OptBool mapToBool_(K a2, @Nonnull LObjLongPredicate.LLongObjPred<? super K> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptBool.of(mapping.testLongObj(get(), a2))) : OptBool.empty();
	}

	default <K> OptBool mapToBoolWith(K a1, @Nonnull LObjLongPredicate<? super K> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptBool.of(mapping.test(a1, get()))) : OptBool.empty();
	}

	default OptByte mapToByte(@Nonnull LLongToByteFunction mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptByte.of(mapping.applyAsByte(get()))) : OptByte.empty();
	}

	default OptDbl mapToDbl(@Nonnull LLongToDblFunction mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptDbl.of(mapping.applyAsDbl(get()))) : OptDbl.empty();
	}

	default OptChar mapToChar(@Nonnull LLongToCharFunction mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptChar.of(mapping.applyAsChar(get()))) : OptChar.empty();
	}

	default OptSrt mapToSrt(@Nonnull LLongToSrtFunction mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptSrt.of(mapping.applyAsSrt(get()))) : OptSrt.empty();
	}

	default OptFlt mapToFlt(@Nonnull LLongToFltFunction mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptFlt.of(mapping.applyAsFlt(get()))) : OptFlt.empty();
	}

	default OptInt mapToInt(@Nonnull LLongToIntFunction mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptInt.of(mapping.applyAsInt(get()))) : OptInt.empty();
	}

	default OptLong map(@Nonnull LLongUnaryOperator mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptLong.of(mapping.applyAsLong(get()))) : OptLong.empty();
	}

	default SELF perform(@Nonnull LLongUnaryOperator mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? value(mapping.applyAsLong(get())) : voidValue();
	}

	default SELF performWith(long a1, @Nonnull LLongBinaryOperator mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? value(mapping.applyAsLong(a1, get())) : voidValue();
	}

	default <R> Opt<R> mapToObj(@Nonnull LLongFunction<? extends R> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (Opt.of(mapping.apply(get()))) : Opt.empty();
	}

	default <R, K> Opt<R> mapToObj_(K a2, @Nonnull LObjLongFunction.LLongObjFunc<? super K, ? extends R> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (Opt.of(mapping.applyLongObj(get(), a2))) : Opt.empty();
	}

	default <R, K> Opt<R> mapToObjWith(K a1, @Nonnull LObjLongFunction<? super K, ? extends R> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (Opt.of(mapping.apply(a1, get()))) : Opt.empty();
	}

	// </editor-fold>

	// <editor-fold desc="flatMap">

	default OptBool flatMapToBool(@Nonnull LLongFunction<? extends OptBoolTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptBool.from(mapping.apply(get())) : OptBool.empty();
	}

	default <K> OptBool flatMapToBool_(K a2, @Nonnull LObjLongFunction.LLongObjFunc<? super K, ? extends OptBoolTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptBool.from(mapping.applyLongObj(get(), a2)) : OptBool.empty();
	}

	default <K> OptBool flatMapToBoolWith(K a1, @Nonnull LObjLongFunction<? super K, ? extends OptBoolTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptBool.from(mapping.apply(a1, get())) : OptBool.empty();
	}

	default OptByte flatMapToByte(@Nonnull LLongFunction<? extends OptByteTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptByte.from(mapping.apply(get())) : OptByte.empty();
	}

	default <K> OptByte flatMapToByte_(K a2, @Nonnull LObjLongFunction.LLongObjFunc<? super K, ? extends OptByteTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptByte.from(mapping.applyLongObj(get(), a2)) : OptByte.empty();
	}

	default <K> OptByte flatMapToByteWith(K a1, @Nonnull LObjLongFunction<? super K, ? extends OptByteTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptByte.from(mapping.apply(a1, get())) : OptByte.empty();
	}

	default OptDbl flatMapToDbl(@Nonnull LLongFunction<? extends OptDblTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptDbl.from(mapping.apply(get())) : OptDbl.empty();
	}

	default <K> OptDbl flatMapToDbl_(K a2, @Nonnull LObjLongFunction.LLongObjFunc<? super K, ? extends OptDblTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptDbl.from(mapping.applyLongObj(get(), a2)) : OptDbl.empty();
	}

	default <K> OptDbl flatMapToDblWith(K a1, @Nonnull LObjLongFunction<? super K, ? extends OptDblTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptDbl.from(mapping.apply(a1, get())) : OptDbl.empty();
	}

	default OptChar flatMapToChar(@Nonnull LLongFunction<? extends OptCharTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptChar.from(mapping.apply(get())) : OptChar.empty();
	}

	default <K> OptChar flatMapToChar_(K a2, @Nonnull LObjLongFunction.LLongObjFunc<? super K, ? extends OptCharTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptChar.from(mapping.applyLongObj(get(), a2)) : OptChar.empty();
	}

	default <K> OptChar flatMapToCharWith(K a1, @Nonnull LObjLongFunction<? super K, ? extends OptCharTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptChar.from(mapping.apply(a1, get())) : OptChar.empty();
	}

	default OptSrt flatMapToSrt(@Nonnull LLongFunction<? extends OptSrtTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptSrt.from(mapping.apply(get())) : OptSrt.empty();
	}

	default <K> OptSrt flatMapToSrt_(K a2, @Nonnull LObjLongFunction.LLongObjFunc<? super K, ? extends OptSrtTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptSrt.from(mapping.applyLongObj(get(), a2)) : OptSrt.empty();
	}

	default <K> OptSrt flatMapToSrtWith(K a1, @Nonnull LObjLongFunction<? super K, ? extends OptSrtTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptSrt.from(mapping.apply(a1, get())) : OptSrt.empty();
	}

	default OptFlt flatMapToFlt(@Nonnull LLongFunction<? extends OptFltTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptFlt.from(mapping.apply(get())) : OptFlt.empty();
	}

	default <K> OptFlt flatMapToFlt_(K a2, @Nonnull LObjLongFunction.LLongObjFunc<? super K, ? extends OptFltTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptFlt.from(mapping.applyLongObj(get(), a2)) : OptFlt.empty();
	}

	default <K> OptFlt flatMapToFltWith(K a1, @Nonnull LObjLongFunction<? super K, ? extends OptFltTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptFlt.from(mapping.apply(a1, get())) : OptFlt.empty();
	}

	default OptInt flatMapToInt(@Nonnull LLongFunction<? extends OptIntTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptInt.from(mapping.apply(get())) : OptInt.empty();
	}

	default <K> OptInt flatMapToInt_(K a2, @Nonnull LObjLongFunction.LLongObjFunc<? super K, ? extends OptIntTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptInt.from(mapping.applyLongObj(get(), a2)) : OptInt.empty();
	}

	default <K> OptInt flatMapToIntWith(K a1, @Nonnull LObjLongFunction<? super K, ? extends OptIntTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptInt.from(mapping.apply(a1, get())) : OptInt.empty();
	}

	default OptLong flatMap(@Nonnull LLongFunction<? extends OptLongTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptLong.from(mapping.apply(get())) : OptLong.empty();
	}

	default SELF perform(@Nonnull LLongFunction<? extends OptLongTrait<? extends SELF>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? valueFrom(mapping.apply(get())) : voidValue();
	}

	default <K> OptLong flatMap_(K a2, @Nonnull LObjLongFunction.LLongObjFunc<? super K, ? extends OptLongTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptLong.from(mapping.applyLongObj(get(), a2)) : OptLong.empty();
	}

	default <K> OptLong flatMapWith(K a1, @Nonnull LObjLongFunction<? super K, ? extends OptLongTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptLong.from(mapping.apply(a1, get())) : OptLong.empty();
	}

	default SELF flatPerformWith(long a1, @Nonnull LBiLongFunction<? extends OptLongTrait<? extends SELF>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? valueFrom(mapping.apply(a1, get())) : voidValue();
	}

	default <R> Opt<R> flatMapToObj(@Nonnull LLongFunction<? extends OptTrait<? extends R, ?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? Opt.from(mapping.apply(get())) : Opt.empty();
	}

	default <R, K> Opt<R> flatMapToObj_(K a2, @Nonnull LObjLongFunction.LLongObjFunc<? super K, ? extends OptTrait<? extends R, ?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? Opt.from(mapping.applyLongObj(get(), a2)) : Opt.empty();
	}

	default <R, K> Opt<R> flatMapToObjWith(K a1, @Nonnull LObjLongFunction<? super K, ? extends OptTrait<? extends R, ?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? Opt.from(mapping.apply(a1, get())) : Opt.empty();
	}

	// </editor-fold>

	// <editor-fold desc="ifPresent">

	default @Nonnull SELF ifVoid(@Nonnull LAction action) {
		Null.nonNullArg(action, "action");
		if (isVoid()) {
			action.execute();
		}
		return self();
	}

	default SELF doIf(@Nonnull LLongPredicate predicate, LConsumer<SELF> action) {
		Null.nonNullArg(predicate, "predicate");
		if (is(predicate))
			action.accept(self());
		return self();
	}

	default SELF doIfNot(@Nonnull LLongPredicate predicate, LConsumer<SELF> action) {
		Null.nonNullArg(predicate, "predicate");
		if (!is(predicate))
			action.accept(self());
		return self();
	}

	default SELF doIf(@Nonnull LBiLongPredicate predicate, long a2, LConsumer<SELF> action) {
		Null.nonNullArg(predicate, "predicate");
		if (is(predicate, a2))
			action.accept(self());
		return self();
	}

	default SELF doIfNot(@Nonnull LBiLongPredicate predicate, long a2, LConsumer<SELF> action) {
		Null.nonNullArg(predicate, "predicate");
		if (!is(predicate, a2))
			action.accept(self());
		return self();
	}

	default SELF doIf(long a2, @Nonnull LBiLongPredicate predicate, LConsumer<SELF> action) {
		Null.nonNullArg(predicate, "predicate");
		if (is(a2, predicate))
			action.accept(self());
		return self();
	}

	default SELF doIfNot(long a2, @Nonnull LBiLongPredicate predicate, LConsumer<SELF> action) {
		Null.nonNullArg(predicate, "predicate");
		if (!is(a2, predicate))
			action.accept(self());
		return self();
	}

	default SELF doIf(@Nonnull LTriLongPredicate predicate, long a2, long a3, LConsumer<SELF> action) {
		Null.nonNullArg(predicate, "predicate");
		if (is(predicate, a2, a3))
			action.accept(self());
		return self();
	}

	default SELF doIfNot(@Nonnull LTriLongPredicate predicate, long a2, long a3, LConsumer<SELF> action) {
		Null.nonNullArg(predicate, "predicate");
		if (!is(predicate, a2, a3))
			action.accept(self());
		return self();
	}

	default SELF doIf(long a2, long a3, @Nonnull LTriLongPredicate predicate, LConsumer<SELF> action) {
		Null.nonNullArg(predicate, "predicate");
		if (is(a2, a3, predicate))
			action.accept(self());
		return self();
	}

	default SELF doIfNot(long a2, long a3, @Nonnull LTriLongPredicate predicate, LConsumer<SELF> action) {
		Null.nonNullArg(predicate, "predicate");
		if (!is(a2, a3, predicate))
			action.accept(self());
		return self();
	}

	default SELF doIf2(@Nonnull LLongIntPredicate predicate, int v, LConsumer<SELF> action) {
		Null.nonNullArg(predicate, "predicate");
		if (is2(predicate, v))
			action.accept(self());
		return self();
	}

	default SELF doIfNot2(@Nonnull LLongIntPredicate predicate, int v, LConsumer<SELF> action) {
		Null.nonNullArg(predicate, "predicate");
		if (!is2(predicate, v))
			action.accept(self());
		return self();
	}

	default SELF doIf2(int v, @Nonnull LLongIntPredicate predicate, LConsumer<SELF> action) {
		Null.nonNullArg(predicate, "predicate");
		if (is2(v, predicate))
			action.accept(self());
		return self();
	}

	default SELF doIfNot2(int v, @Nonnull LLongIntPredicate predicate, LConsumer<SELF> action) {
		Null.nonNullArg(predicate, "predicate");
		if (!is2(v, predicate))
			action.accept(self());
		return self();
	}

	default <V> SELF doIf2_(@Nonnull LObjLongPredicate.LLongObjPred<? super V> predicate, V v, LConsumer<SELF> action) {
		Null.nonNullArg(predicate, "predicate");
		if (is2_(predicate, v))
			action.accept(self());
		return self();
	}

	default <V> SELF doIfNot2_(@Nonnull LObjLongPredicate.LLongObjPred<? super V> predicate, V v, LConsumer<SELF> action) {
		Null.nonNullArg(predicate, "predicate");
		if (!is2_(predicate, v))
			action.accept(self());
		return self();
	}

	default <V> SELF doIf2_(V v, @Nonnull LObjLongPredicate.LLongObjPred<? super V> predicate, LConsumer<SELF> action) {
		Null.nonNullArg(predicate, "predicate");
		if (is2_(v, predicate))
			action.accept(self());
		return self();
	}

	default <V> SELF doIfNot2_(V v, @Nonnull LObjLongPredicate.LLongObjPred<? super V> predicate, LConsumer<SELF> action) {
		Null.nonNullArg(predicate, "predicate");
		if (!is2_(v, predicate))
			action.accept(self());
		return self();
	}

	default SELF ifPresent(@Nonnull LLongConsumer action) {
		Null.nonNullArg(action, "action");
		if (isPresent()) {
			action.accept(get());
		}
		return self();
	}

	default SELF ifPresent(@Nonnull LLongConsumer action, @Nonnull LAction emptyAction) {
		Null.nonNullArg(action, "action");
		if (isPresent()) {
			action.accept(get());
		} else {
			emptyAction.execute();
		}
		return self();
	}

	default <K> SELF ifPresent_(K a1, @Nonnull LObjLongConsumer.LLongObjCons<? super K> action) {
		Null.nonNullArg(action, "action");
		if (isPresent()) {
			action.acceptLongObj(get(), a1);
		}
		return self();
	}

	default <K> SELF ifPresentWith(K a1, @Nonnull LObjLongConsumer<? super K> action) {
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

	default long orElseThrow() {
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

	default long orElseThrow(@Nonnull ExF<RuntimeException> fx) {
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

	default long orElseThrow(@Nonnull ExMF<RuntimeException> fx, @Nullable String msg) {
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

	default long orElseThrow(@Nonnull ExMF<RuntimeException> fx, @Nullable String msg, Object... args) {
		if (isPresent()) {
			return get();
		}

		Null.nonNullArg(fx, "fx");
		throw Handling.create(fx, msg, args);
	}

	default long orElse(@Nullable long value) {
		return isPresent() ? get() : value;
	}

	default long orElseGet(@Nonnull LLongSupplier supplier) {
		Null.nonNullArg(supplier, "supplier");
		return isPresent() ? get() : supplier.getAsLong();
	}

	default SELF orGet(@Nonnull LSupplier<? extends OptLongTrait<?>> supplier) {
		Null.nonNullArg(supplier, "supplier");
		return isPresent() ? self() : valueFrom(supplier.get());
	}

	default SELF or(@Nullable long value) {
		return isPresent() ? self() : value(value);
	}

	default SELF orOpt(@Nonnull OptLongTrait<?> opt) {
		Null.nonNullArg(opt, "opt");
		return isPresent() ? self() : valueFrom(opt);
	}

	default <K> long orElseApply(K a1, @Nonnull LToLongFunction<? super K> supplier) {
		Null.nonNullArg(supplier, "supplier");
		return isPresent() ? get() : supplier.applyAsLong(a1);
	}

	default <K> SELF orApply(K a1, @Nonnull LFunction<? super K, ? extends OptLongTrait<?>> supplier) {
		Null.nonNullArg(supplier, "supplier");
		return isPresent() ? self() : valueFrom(supplier.apply(a1));
	}

	// </editor-fold>
}
