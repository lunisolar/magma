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
public interface OptTrait<T, SELF extends OptTrait<T, SELF>> extends Fluent<SELF>, aValue<a<T>>, CheckTrait<T, SELF> {

	// <editor-fold desc="forcing ValueTrait re-implementation">

	@Nonnull
	SELF value(@Nullable T value);
	@Nonnull
	SELF voidValue();

	// </editor-fold>

	default @Nonnull T get() {
		LPredicate.throwIfNot(this, OptTrait::isPresent, X::noSuchElement, "No value present.");
		return nullable();
	}

	@Nonnull
	T nullable();

	@Nonnull
	default String checkTraitType() {
		return this.getClass().getSimpleName();
	}

	default boolean isPresent() {
		return nullable() != null;
	}

	default boolean isVoid() {
		return !isPresent();
	}

	default Optional<T> toOpt() {
		if (isPresent()) {
			return Optional.of(get());
		} else {
			return Optional.empty();
		}
	}

	// <editor-fold desc="isPresent() dependant boolean terminals">

	public default boolean is(@Nonnull LPredicate<? super T> predicate) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && predicate.test(get());
	}

	public default boolean is(@Nonnull LBiPredicate<? super T, ? super T> predicate, T a2) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && predicate.test(get(), a2);
	}

	public default boolean is(T a2, @Nonnull LBiPredicate<? super T, ? super T> predicate) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && predicate.test(get(), a2);
	}

	public default boolean is(@Nonnull LTriPredicate<? super T, ? super T, ? super T> predicate, T a2, T a3) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && predicate.test(get(), a2, a3);
	}

	public default boolean is(T a2, T a3, @Nonnull LTriPredicate<? super T, ? super T, ? super T> predicate) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && predicate.test(get(), a2, a3);
	}

	public default boolean is2(@Nonnull LObjBoolPredicate<? super T> predicate, boolean v) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && predicate.test(get(), v);
	}

	public default boolean is2(boolean v, @Nonnull LObjBoolPredicate<? super T> predicate) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && predicate.test(get(), v);
	}

	public default boolean is2(@Nonnull LObjBytePredicate<? super T> predicate, byte v) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && predicate.test(get(), v);
	}

	public default boolean is2(byte v, @Nonnull LObjBytePredicate<? super T> predicate) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && predicate.test(get(), v);
	}

	public default boolean is2(@Nonnull LObjDblPredicate<? super T> predicate, double v) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && predicate.test(get(), v);
	}

	public default boolean is2(double v, @Nonnull LObjDblPredicate<? super T> predicate) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && predicate.test(get(), v);
	}

	public default boolean is2(@Nonnull LObjCharPredicate<? super T> predicate, char v) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && predicate.test(get(), v);
	}

	public default boolean is2(char v, @Nonnull LObjCharPredicate<? super T> predicate) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && predicate.test(get(), v);
	}

	public default boolean is2(@Nonnull LObjSrtPredicate<? super T> predicate, short v) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && predicate.test(get(), v);
	}

	public default boolean is2(short v, @Nonnull LObjSrtPredicate<? super T> predicate) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && predicate.test(get(), v);
	}

	public default boolean is2(@Nonnull LObjFltPredicate<? super T> predicate, float v) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && predicate.test(get(), v);
	}

	public default boolean is2(float v, @Nonnull LObjFltPredicate<? super T> predicate) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && predicate.test(get(), v);
	}

	public default boolean is2(@Nonnull LObjIntPredicate<? super T> predicate, int v) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && predicate.test(get(), v);
	}

	public default boolean is2(int v, @Nonnull LObjIntPredicate<? super T> predicate) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && predicate.test(get(), v);
	}

	public default boolean is2(@Nonnull LObjLongPredicate<? super T> predicate, long v) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && predicate.test(get(), v);
	}

	public default boolean is2(long v, @Nonnull LObjLongPredicate<? super T> predicate) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && predicate.test(get(), v);
	}

	public default <V> boolean is2(@Nonnull LBiPredicate<? super T, ? super V> predicate, V v) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && predicate.test(get(), v);
	}

	public default <V> boolean is2(V v, @Nonnull LBiPredicate<? super T, ? super V> predicate) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && predicate.test(get(), v);
	}

	// </editor-fold>

	// <editor-fold desc="filtering">

	public default SELF filter(@Nonnull LPredicate<? super T> predicate) {
		Null.nonNullArg(predicate, "predicate");
		return this.is(predicate) ? self() : voidValue();
	}

	public default SELF filter(@Nonnull LBiPredicate<? super T, ? super T> predicate, T a2) {
		Null.nonNullArg(predicate, "predicate");
		return this.is(predicate, a2) ? self() : voidValue();
	}

	public default SELF filter(T a2, @Nonnull LBiPredicate<? super T, ? super T> predicate) {
		Null.nonNullArg(predicate, "predicate");
		return this.is(a2, predicate) ? self() : voidValue();
	}

	public default SELF filter(@Nonnull LTriPredicate<? super T, ? super T, ? super T> predicate, T a2, T a3) {
		Null.nonNullArg(predicate, "predicate");
		return this.is(predicate, a2, a3) ? self() : voidValue();
	}

	public default SELF filter(T a2, T a3, @Nonnull LTriPredicate<? super T, ? super T, ? super T> predicate) {
		Null.nonNullArg(predicate, "predicate");
		return this.is(a2, a3, predicate) ? self() : voidValue();
	}

	public default SELF filter2(@Nonnull LObjBoolPredicate<? super T> predicate, boolean v) {
		Null.nonNullArg(predicate, "predicate");
		return this.is2(predicate, v) ? self() : voidValue();
	}

	public default SELF filter2(boolean v, @Nonnull LObjBoolPredicate<? super T> predicate) {
		Null.nonNullArg(predicate, "predicate");
		return this.is2(v, predicate) ? self() : voidValue();
	}

	public default SELF filter2(@Nonnull LObjBytePredicate<? super T> predicate, byte v) {
		Null.nonNullArg(predicate, "predicate");
		return this.is2(predicate, v) ? self() : voidValue();
	}

	public default SELF filter2(byte v, @Nonnull LObjBytePredicate<? super T> predicate) {
		Null.nonNullArg(predicate, "predicate");
		return this.is2(v, predicate) ? self() : voidValue();
	}

	public default SELF filter2(@Nonnull LObjDblPredicate<? super T> predicate, double v) {
		Null.nonNullArg(predicate, "predicate");
		return this.is2(predicate, v) ? self() : voidValue();
	}

	public default SELF filter2(double v, @Nonnull LObjDblPredicate<? super T> predicate) {
		Null.nonNullArg(predicate, "predicate");
		return this.is2(v, predicate) ? self() : voidValue();
	}

	public default SELF filter2(@Nonnull LObjCharPredicate<? super T> predicate, char v) {
		Null.nonNullArg(predicate, "predicate");
		return this.is2(predicate, v) ? self() : voidValue();
	}

	public default SELF filter2(char v, @Nonnull LObjCharPredicate<? super T> predicate) {
		Null.nonNullArg(predicate, "predicate");
		return this.is2(v, predicate) ? self() : voidValue();
	}

	public default SELF filter2(@Nonnull LObjSrtPredicate<? super T> predicate, short v) {
		Null.nonNullArg(predicate, "predicate");
		return this.is2(predicate, v) ? self() : voidValue();
	}

	public default SELF filter2(short v, @Nonnull LObjSrtPredicate<? super T> predicate) {
		Null.nonNullArg(predicate, "predicate");
		return this.is2(v, predicate) ? self() : voidValue();
	}

	public default SELF filter2(@Nonnull LObjFltPredicate<? super T> predicate, float v) {
		Null.nonNullArg(predicate, "predicate");
		return this.is2(predicate, v) ? self() : voidValue();
	}

	public default SELF filter2(float v, @Nonnull LObjFltPredicate<? super T> predicate) {
		Null.nonNullArg(predicate, "predicate");
		return this.is2(v, predicate) ? self() : voidValue();
	}

	public default SELF filter2(@Nonnull LObjIntPredicate<? super T> predicate, int v) {
		Null.nonNullArg(predicate, "predicate");
		return this.is2(predicate, v) ? self() : voidValue();
	}

	public default SELF filter2(int v, @Nonnull LObjIntPredicate<? super T> predicate) {
		Null.nonNullArg(predicate, "predicate");
		return this.is2(v, predicate) ? self() : voidValue();
	}

	public default SELF filter2(@Nonnull LObjLongPredicate<? super T> predicate, long v) {
		Null.nonNullArg(predicate, "predicate");
		return this.is2(predicate, v) ? self() : voidValue();
	}

	public default SELF filter2(long v, @Nonnull LObjLongPredicate<? super T> predicate) {
		Null.nonNullArg(predicate, "predicate");
		return this.is2(v, predicate) ? self() : voidValue();
	}

	public default <V> SELF filter2(@Nonnull LBiPredicate<? super T, ? super V> predicate, V v) {
		Null.nonNullArg(predicate, "predicate");
		return this.is2(predicate, v) ? self() : voidValue();
	}

	public default <V> SELF filter2(V v, @Nonnull LBiPredicate<? super T, ? super V> predicate) {
		Null.nonNullArg(predicate, "predicate");
		return this.is2(v, predicate) ? self() : voidValue();
	}

	// </editor-fold>

	public default <R> Opt<R> filterAndMap(@Nonnull Class<R> clazz) {
		Null.nonNullArg(clazz, "clazz");
		return (isPresent() && clazz.isInstance(this.get())) ? (Opt) this : Opt.empty();
	}

	public default <R, O extends OptTrait<R, O>> O alwaysMap(@Nonnull LFunction<? super T, O> mapping) {
		Null.nonNullArg(mapping, "mapping");
		O result = mapping.apply(isPresent() ? get() : null);
		return Objects.requireNonNull(result);
	}

	public default <R, O extends OptTrait<R, O>, K> O alwaysMap(K a1, @Nonnull LBiFunction<? super T, ? super K, O> mapping) {
		Null.nonNullArg(mapping, "mapping");
		O result = mapping.apply(isPresent() ? get() : null, a1);
		return Objects.requireNonNull(result);
	}

	public default <R, O extends OptTrait<R, O>, K> O alwaysMapWith(K a1, @Nonnull LBiFunction<? super K, ? super T, O> mapping) {
		Null.nonNullArg(mapping, "mapping");
		O result = mapping.apply(a1, isPresent() ? get() : null);
		return Objects.requireNonNull(result);
	}

	public default <R, O extends OptTrait<R, O>> O alwaysFlatMap(@Nonnull LFunction<SELF, O> mapping) {
		Null.nonNullArg(mapping, "mapping");
		O result = mapping.apply(self());
		return Objects.requireNonNull(result);
	}

	public default <R, O extends OptTrait<R, O>, K> O alwaysFlatMap(K a1, @Nonnull LBiFunction<SELF, ? super K, O> mapping) {
		Null.nonNullArg(mapping, "mapping");
		O result = mapping.apply(self(), a1);
		return Objects.requireNonNull(result);
	}

	public default <R, O extends OptTrait<R, O>, K> O alwaysFlatMapWith(K a1, @Nonnull LBiFunction<? super K, SELF, O> mapping) {
		Null.nonNullArg(mapping, "mapping");
		O result = mapping.apply(a1, self());
		return Objects.requireNonNull(result);
	}

	// <editor-fold desc="map">

	public default OptBool mapToBool(@Nonnull LPredicate<? super T> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptBool.of(mapping.test(get()))) : OptBool.empty();
	}

	public default <K> OptBool mapToBool(K a2, @Nonnull LBiPredicate<? super T, ? super K> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptBool.of(mapping.test(get(), a2))) : OptBool.empty();
	}

	public default <K> OptBool mapToBoolWith(K a1, @Nonnull LBiPredicate<? super K, ? super T> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptBool.of(mapping.test(a1, get()))) : OptBool.empty();
	}

	public default OptByte mapToByte(@Nonnull LToByteFunction<? super T> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptByte.of(mapping.applyAsByte(get()))) : OptByte.empty();
	}

	public default <K> OptByte mapToByte(K a2, @Nonnull LToByteBiFunction<? super T, ? super K> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptByte.of(mapping.applyAsByte(get(), a2))) : OptByte.empty();
	}

	public default <K> OptByte mapToByteWith(K a1, @Nonnull LToByteBiFunction<? super K, ? super T> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptByte.of(mapping.applyAsByte(a1, get()))) : OptByte.empty();
	}

	public default OptDbl mapToDbl(@Nonnull LToDblFunction<? super T> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptDbl.of(mapping.applyAsDbl(get()))) : OptDbl.empty();
	}

	public default <K> OptDbl mapToDbl(K a2, @Nonnull LToDblBiFunction<? super T, ? super K> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptDbl.of(mapping.applyAsDbl(get(), a2))) : OptDbl.empty();
	}

	public default <K> OptDbl mapToDblWith(K a1, @Nonnull LToDblBiFunction<? super K, ? super T> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptDbl.of(mapping.applyAsDbl(a1, get()))) : OptDbl.empty();
	}

	public default OptChar mapToChar(@Nonnull LToCharFunction<? super T> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptChar.of(mapping.applyAsChar(get()))) : OptChar.empty();
	}

	public default <K> OptChar mapToChar(K a2, @Nonnull LToCharBiFunction<? super T, ? super K> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptChar.of(mapping.applyAsChar(get(), a2))) : OptChar.empty();
	}

	public default <K> OptChar mapToCharWith(K a1, @Nonnull LToCharBiFunction<? super K, ? super T> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptChar.of(mapping.applyAsChar(a1, get()))) : OptChar.empty();
	}

	public default OptSrt mapToSrt(@Nonnull LToSrtFunction<? super T> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptSrt.of(mapping.applyAsSrt(get()))) : OptSrt.empty();
	}

	public default <K> OptSrt mapToSrt(K a2, @Nonnull LToSrtBiFunction<? super T, ? super K> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptSrt.of(mapping.applyAsSrt(get(), a2))) : OptSrt.empty();
	}

	public default <K> OptSrt mapToSrtWith(K a1, @Nonnull LToSrtBiFunction<? super K, ? super T> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptSrt.of(mapping.applyAsSrt(a1, get()))) : OptSrt.empty();
	}

	public default OptFlt mapToFlt(@Nonnull LToFltFunction<? super T> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptFlt.of(mapping.applyAsFlt(get()))) : OptFlt.empty();
	}

	public default <K> OptFlt mapToFlt(K a2, @Nonnull LToFltBiFunction<? super T, ? super K> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptFlt.of(mapping.applyAsFlt(get(), a2))) : OptFlt.empty();
	}

	public default <K> OptFlt mapToFltWith(K a1, @Nonnull LToFltBiFunction<? super K, ? super T> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptFlt.of(mapping.applyAsFlt(a1, get()))) : OptFlt.empty();
	}

	public default OptInt mapToInt(@Nonnull LToIntFunction<? super T> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptInt.of(mapping.applyAsInt(get()))) : OptInt.empty();
	}

	public default <K> OptInt mapToInt(K a2, @Nonnull LToIntBiFunction<? super T, ? super K> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptInt.of(mapping.applyAsInt(get(), a2))) : OptInt.empty();
	}

	public default <K> OptInt mapToIntWith(K a1, @Nonnull LToIntBiFunction<? super K, ? super T> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptInt.of(mapping.applyAsInt(a1, get()))) : OptInt.empty();
	}

	public default OptLong mapToLong(@Nonnull LToLongFunction<? super T> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptLong.of(mapping.applyAsLong(get()))) : OptLong.empty();
	}

	public default <K> OptLong mapToLong(K a2, @Nonnull LToLongBiFunction<? super T, ? super K> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptLong.of(mapping.applyAsLong(get(), a2))) : OptLong.empty();
	}

	public default <K> OptLong mapToLongWith(K a1, @Nonnull LToLongBiFunction<? super K, ? super T> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptLong.of(mapping.applyAsLong(a1, get()))) : OptLong.empty();
	}

	public default <R> Opt<R> map(@Nonnull LFunction<? super T, ? extends R> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (Opt.of(mapping.apply(get()))) : Opt.empty();
	}

	public default SELF perform(@Nonnull LUnaryOperator<T> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? value(mapping.apply(get())) : voidValue();
	}

	public default <R, K> Opt<R> map(K a2, @Nonnull LBiFunction<? super T, ? super K, ? extends R> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (Opt.of(mapping.apply(get(), a2))) : Opt.empty();
	}

	public default <R, K> Opt<R> mapWith(K a1, @Nonnull LBiFunction<? super K, ? super T, ? extends R> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (Opt.of(mapping.apply(a1, get()))) : Opt.empty();
	}

	public default SELF performWith(T a1, @Nonnull LBinaryOperator<T> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? value(mapping.apply(a1, get())) : voidValue();
	}

	// </editor-fold>

	// <editor-fold desc="flatMap">

	public default OptBool flatMapToBool(@Nonnull LFunction<? super T, ? extends OptBoolTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptBool.from(mapping.apply(get())) : OptBool.empty();
	}

	public default <K> OptBool flatMapToBool(K a2, @Nonnull LBiFunction<? super T, ? super K, ? extends OptBoolTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptBool.from(mapping.apply(get(), a2)) : OptBool.empty();
	}

	public default <K> OptBool flatMapToBoolWith(K a1, @Nonnull LBiFunction<? super K, ? super T, ? extends OptBoolTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptBool.from(mapping.apply(a1, get())) : OptBool.empty();
	}

	public default OptByte flatMapToByte(@Nonnull LFunction<? super T, ? extends OptByteTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptByte.from(mapping.apply(get())) : OptByte.empty();
	}

	public default <K> OptByte flatMapToByte(K a2, @Nonnull LBiFunction<? super T, ? super K, ? extends OptByteTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptByte.from(mapping.apply(get(), a2)) : OptByte.empty();
	}

	public default <K> OptByte flatMapToByteWith(K a1, @Nonnull LBiFunction<? super K, ? super T, ? extends OptByteTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptByte.from(mapping.apply(a1, get())) : OptByte.empty();
	}

	public default OptDbl flatMapToDbl(@Nonnull LFunction<? super T, ? extends OptDblTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptDbl.from(mapping.apply(get())) : OptDbl.empty();
	}

	public default <K> OptDbl flatMapToDbl(K a2, @Nonnull LBiFunction<? super T, ? super K, ? extends OptDblTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptDbl.from(mapping.apply(get(), a2)) : OptDbl.empty();
	}

	public default <K> OptDbl flatMapToDblWith(K a1, @Nonnull LBiFunction<? super K, ? super T, ? extends OptDblTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptDbl.from(mapping.apply(a1, get())) : OptDbl.empty();
	}

	public default OptChar flatMapToChar(@Nonnull LFunction<? super T, ? extends OptCharTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptChar.from(mapping.apply(get())) : OptChar.empty();
	}

	public default <K> OptChar flatMapToChar(K a2, @Nonnull LBiFunction<? super T, ? super K, ? extends OptCharTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptChar.from(mapping.apply(get(), a2)) : OptChar.empty();
	}

	public default <K> OptChar flatMapToCharWith(K a1, @Nonnull LBiFunction<? super K, ? super T, ? extends OptCharTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptChar.from(mapping.apply(a1, get())) : OptChar.empty();
	}

	public default OptSrt flatMapToSrt(@Nonnull LFunction<? super T, ? extends OptSrtTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptSrt.from(mapping.apply(get())) : OptSrt.empty();
	}

	public default <K> OptSrt flatMapToSrt(K a2, @Nonnull LBiFunction<? super T, ? super K, ? extends OptSrtTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptSrt.from(mapping.apply(get(), a2)) : OptSrt.empty();
	}

	public default <K> OptSrt flatMapToSrtWith(K a1, @Nonnull LBiFunction<? super K, ? super T, ? extends OptSrtTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptSrt.from(mapping.apply(a1, get())) : OptSrt.empty();
	}

	public default OptFlt flatMapToFlt(@Nonnull LFunction<? super T, ? extends OptFltTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptFlt.from(mapping.apply(get())) : OptFlt.empty();
	}

	public default <K> OptFlt flatMapToFlt(K a2, @Nonnull LBiFunction<? super T, ? super K, ? extends OptFltTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptFlt.from(mapping.apply(get(), a2)) : OptFlt.empty();
	}

	public default <K> OptFlt flatMapToFltWith(K a1, @Nonnull LBiFunction<? super K, ? super T, ? extends OptFltTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptFlt.from(mapping.apply(a1, get())) : OptFlt.empty();
	}

	public default OptInt flatMapToInt(@Nonnull LFunction<? super T, ? extends OptIntTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptInt.from(mapping.apply(get())) : OptInt.empty();
	}

	public default <K> OptInt flatMapToInt(K a2, @Nonnull LBiFunction<? super T, ? super K, ? extends OptIntTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptInt.from(mapping.apply(get(), a2)) : OptInt.empty();
	}

	public default <K> OptInt flatMapToIntWith(K a1, @Nonnull LBiFunction<? super K, ? super T, ? extends OptIntTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptInt.from(mapping.apply(a1, get())) : OptInt.empty();
	}

	public default OptLong flatMapToLong(@Nonnull LFunction<? super T, ? extends OptLongTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptLong.from(mapping.apply(get())) : OptLong.empty();
	}

	public default <K> OptLong flatMapToLong(K a2, @Nonnull LBiFunction<? super T, ? super K, ? extends OptLongTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptLong.from(mapping.apply(get(), a2)) : OptLong.empty();
	}

	public default <K> OptLong flatMapToLongWith(K a1, @Nonnull LBiFunction<? super K, ? super T, ? extends OptLongTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptLong.from(mapping.apply(a1, get())) : OptLong.empty();
	}

	public default <R> Opt<R> flatMap(@Nonnull LFunction<? super T, ? extends OptTrait<? extends R, ?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? Opt.from(mapping.apply(get())) : Opt.empty();
	}

	public default SELF perform(@Nonnull LFunction<T, ? extends OptTrait<? extends T, ? extends SELF>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? valueFrom(mapping.apply(get())) : voidValue();
	}

	public default <R, K> Opt<R> flatMap(K a2, @Nonnull LBiFunction<? super T, ? super K, ? extends OptTrait<? extends R, ?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? Opt.from(mapping.apply(get(), a2)) : Opt.empty();
	}

	public default <R, K> Opt<R> flatMapWith(K a1, @Nonnull LBiFunction<? super K, ? super T, ? extends OptTrait<? extends R, ?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? Opt.from(mapping.apply(a1, get())) : Opt.empty();
	}

	public default SELF flatPerformWith(T a1, @Nonnull LBiFunction<T, T, ? extends OptTrait<? extends T, ? extends SELF>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? valueFrom(mapping.apply(a1, get())) : voidValue();
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

	public default SELF doIf(@Nonnull LPredicate<? super T> predicate, LConsumer<SELF> action) {
		Null.nonNullArg(predicate, "predicate");
		if (is(predicate))
			action.accept(self());
		return self();
	}

	public default SELF doIf(@Nonnull LBiPredicate<? super T, ? super T> predicate, T a2, LConsumer<SELF> action) {
		Null.nonNullArg(predicate, "predicate");
		if (is(predicate, a2))
			action.accept(self());
		return self();
	}

	public default SELF doIf(T a2, @Nonnull LBiPredicate<? super T, ? super T> predicate, LConsumer<SELF> action) {
		Null.nonNullArg(predicate, "predicate");
		if (is(a2, predicate))
			action.accept(self());
		return self();
	}

	public default SELF doIf(@Nonnull LTriPredicate<? super T, ? super T, ? super T> predicate, T a2, T a3, LConsumer<SELF> action) {
		Null.nonNullArg(predicate, "predicate");
		if (is(predicate, a2, a3))
			action.accept(self());
		return self();
	}

	public default SELF doIf(T a2, T a3, @Nonnull LTriPredicate<? super T, ? super T, ? super T> predicate, LConsumer<SELF> action) {
		Null.nonNullArg(predicate, "predicate");
		if (is(a2, a3, predicate))
			action.accept(self());
		return self();
	}

	public default SELF doIf2(@Nonnull LObjBoolPredicate<? super T> predicate, boolean v, LConsumer<SELF> action) {
		Null.nonNullArg(predicate, "predicate");
		if (is2(predicate, v))
			action.accept(self());
		return self();
	}

	public default SELF doIf2(boolean v, @Nonnull LObjBoolPredicate<? super T> predicate, LConsumer<SELF> action) {
		Null.nonNullArg(predicate, "predicate");
		if (is2(v, predicate))
			action.accept(self());
		return self();
	}

	public default SELF doIf2(@Nonnull LObjBytePredicate<? super T> predicate, byte v, LConsumer<SELF> action) {
		Null.nonNullArg(predicate, "predicate");
		if (is2(predicate, v))
			action.accept(self());
		return self();
	}

	public default SELF doIf2(byte v, @Nonnull LObjBytePredicate<? super T> predicate, LConsumer<SELF> action) {
		Null.nonNullArg(predicate, "predicate");
		if (is2(v, predicate))
			action.accept(self());
		return self();
	}

	public default SELF doIf2(@Nonnull LObjDblPredicate<? super T> predicate, double v, LConsumer<SELF> action) {
		Null.nonNullArg(predicate, "predicate");
		if (is2(predicate, v))
			action.accept(self());
		return self();
	}

	public default SELF doIf2(double v, @Nonnull LObjDblPredicate<? super T> predicate, LConsumer<SELF> action) {
		Null.nonNullArg(predicate, "predicate");
		if (is2(v, predicate))
			action.accept(self());
		return self();
	}

	public default SELF doIf2(@Nonnull LObjCharPredicate<? super T> predicate, char v, LConsumer<SELF> action) {
		Null.nonNullArg(predicate, "predicate");
		if (is2(predicate, v))
			action.accept(self());
		return self();
	}

	public default SELF doIf2(char v, @Nonnull LObjCharPredicate<? super T> predicate, LConsumer<SELF> action) {
		Null.nonNullArg(predicate, "predicate");
		if (is2(v, predicate))
			action.accept(self());
		return self();
	}

	public default SELF doIf2(@Nonnull LObjSrtPredicate<? super T> predicate, short v, LConsumer<SELF> action) {
		Null.nonNullArg(predicate, "predicate");
		if (is2(predicate, v))
			action.accept(self());
		return self();
	}

	public default SELF doIf2(short v, @Nonnull LObjSrtPredicate<? super T> predicate, LConsumer<SELF> action) {
		Null.nonNullArg(predicate, "predicate");
		if (is2(v, predicate))
			action.accept(self());
		return self();
	}

	public default SELF doIf2(@Nonnull LObjFltPredicate<? super T> predicate, float v, LConsumer<SELF> action) {
		Null.nonNullArg(predicate, "predicate");
		if (is2(predicate, v))
			action.accept(self());
		return self();
	}

	public default SELF doIf2(float v, @Nonnull LObjFltPredicate<? super T> predicate, LConsumer<SELF> action) {
		Null.nonNullArg(predicate, "predicate");
		if (is2(v, predicate))
			action.accept(self());
		return self();
	}

	public default SELF doIf2(@Nonnull LObjIntPredicate<? super T> predicate, int v, LConsumer<SELF> action) {
		Null.nonNullArg(predicate, "predicate");
		if (is2(predicate, v))
			action.accept(self());
		return self();
	}

	public default SELF doIf2(int v, @Nonnull LObjIntPredicate<? super T> predicate, LConsumer<SELF> action) {
		Null.nonNullArg(predicate, "predicate");
		if (is2(v, predicate))
			action.accept(self());
		return self();
	}

	public default SELF doIf2(@Nonnull LObjLongPredicate<? super T> predicate, long v, LConsumer<SELF> action) {
		Null.nonNullArg(predicate, "predicate");
		if (is2(predicate, v))
			action.accept(self());
		return self();
	}

	public default SELF doIf2(long v, @Nonnull LObjLongPredicate<? super T> predicate, LConsumer<SELF> action) {
		Null.nonNullArg(predicate, "predicate");
		if (is2(v, predicate))
			action.accept(self());
		return self();
	}

	public default <V> SELF doIf2(@Nonnull LBiPredicate<? super T, ? super V> predicate, V v, LConsumer<SELF> action) {
		Null.nonNullArg(predicate, "predicate");
		if (is2(predicate, v))
			action.accept(self());
		return self();
	}

	public default <V> SELF doIf2(V v, @Nonnull LBiPredicate<? super T, ? super V> predicate, LConsumer<SELF> action) {
		Null.nonNullArg(predicate, "predicate");
		if (is2(v, predicate))
			action.accept(self());
		return self();
	}

	public default SELF ifPresent(@Nonnull LConsumer<? super T> action) {
		Null.nonNullArg(action, "action");
		if (isPresent()) {
			action.accept(get());
		}
		return self();
	}

	public default SELF ifPresent(@Nonnull LConsumer<? super T> action, @Nonnull LAction emptyAction) {
		Null.nonNullArg(action, "action");
		if (isPresent()) {
			action.accept(get());
		} else {
			emptyAction.execute();
		}
		return self();
	}

	public default <K> SELF ifPresent(K a1, @Nonnull LBiConsumer<? super T, ? super K> action) {
		Null.nonNullArg(action, "action");
		if (isPresent()) {
			action.accept(get(), a1);
		}
		return self();
	}

	public default <K> SELF ifPresentWith(K a1, @Nonnull LBiConsumer<? super K, ? super T> action) {
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

	default T orElseThrow() {
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

	default T orElseThrow(@Nonnull ExF<RuntimeException> fx) {
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

	default T orElseThrow(@Nonnull ExMF<RuntimeException> fx, @Nullable String msg) {
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

	default T orElseThrow(@Nonnull ExMF<RuntimeException> fx, @Nullable String msg, Object... args) {
		if (isPresent()) {
			return get();
		}

		Null.nonNullArg(fx, "fx");
		throw Handling.create(fx, msg, args);
	}

	public default T orElse(@Nullable T value) {
		return isPresent() ? get() : value;
	}

	public default T orElseGet(@Nonnull LSupplier<? extends T> supplier) {
		Null.nonNullArg(supplier, "supplier");
		return isPresent() ? get() : supplier.get();
	}

	public default SELF orGet(@Nonnull LSupplier<? extends OptTrait<? extends T, ?>> supplier) {
		Null.nonNullArg(supplier, "supplier");
		return isPresent() ? self() : valueFrom(supplier.get());
	}

	public default SELF or(@Nullable T value) {
		return isPresent() ? self() : value(value);
	}

	public default SELF orOpt(@Nonnull OptTrait<? extends T, ?> opt) {
		Null.nonNullArg(opt, "opt");
		return isPresent() ? self() : valueFrom(opt);
	}

	public default <K> T orElseApply(K a1, @Nonnull LFunction<? super K, ? extends T> supplier) {
		Null.nonNullArg(supplier, "supplier");
		return isPresent() ? get() : supplier.apply(a1);
	}

	public default <K> SELF orApply(K a1, @Nonnull LFunction<? super K, ? extends OptTrait<? extends T, ?>> supplier) {
		Null.nonNullArg(supplier, "supplier");
		return isPresent() ? self() : valueFrom(supplier.apply(a1));
	}

	// </editor-fold>
}
