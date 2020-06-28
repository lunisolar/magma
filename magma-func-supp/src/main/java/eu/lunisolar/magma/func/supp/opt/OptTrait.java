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
public interface OptTrait<T, SELF extends OptTrait<T, SELF>> extends FluentTrait<SELF>, aValue<a<T>>, CheckTrait<T, SELF>, FilterSingleTrait<T, SELF>, IsTrait<T, SELF>, DoIfSingleTrait<T, SELF>, UseSingleTrait<T, SELF> {

	// <editor-fold desc="forcing ValueTrait re-implementation">

	@Override
	@Nonnull
	SELF value(@Nullable T value);

	@Override
	@Nonnull
	SELF voidValue();

	// </editor-fold>

	default @Nonnull SELF valueFrom(@Nonnull OptTrait<? extends T, ?> opt) {
		Null.nonNullArg(opt, "opt");
		return value(opt.nullable());
	}

	default @Nonnull T get() {
		LPredicate.throwIfNot(this, OptTrait::isPresent, X::noSuchElement, "No value present.");
		return nullable();
	}

	@Override
	default @Nonnull T nonnull() {
		return get();
	}

	@Nullable
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

	@Override
	default boolean is(@Nonnull LPredicate<? super T> predicate) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && FilterSingleTrait.super.is(predicate);
	}

	@Override
	default boolean isNot(@Nonnull LPredicate<? super T> predicate) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && FilterSingleTrait.super.isNot(predicate);
	}

	@Override
	default boolean uniIs(T a2, @Nonnull LBiPredicate<? super T, ? super T> predicate) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && FilterSingleTrait.super.uniIs(a2, predicate);
	}

	@Override
	default boolean uniIsNot(T a2, @Nonnull LBiPredicate<? super T, ? super T> predicate) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && FilterSingleTrait.super.uniIsNot(a2, predicate);
	}

	@Override
	default boolean uniIs(T a2, T a3, @Nonnull LTriPredicate<? super T, ? super T, ? super T> predicate) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && FilterSingleTrait.super.uniIs(a2, a3, predicate);
	}

	@Override
	default boolean uniIsNot(T a2, T a3, @Nonnull LTriPredicate<? super T, ? super T, ? super T> predicate) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && FilterSingleTrait.super.uniIsNot(a2, a3, predicate);
	}

	@Override
	default boolean uniIs(T a2, T a3, T a4, @Nonnull LQuadPredicate<? super T, ? super T, ? super T, ? super T> predicate) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && FilterSingleTrait.super.uniIs(a2, a3, a4, predicate);
	}

	@Override
	default boolean uniIsNot(T a2, T a3, T a4, @Nonnull LQuadPredicate<? super T, ? super T, ? super T, ? super T> predicate) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && FilterSingleTrait.super.uniIsNot(a2, a3, a4, predicate);
	}

	@Override
	default boolean isBool(boolean v, @Nonnull LObjBoolPredicate<? super T> predicate) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && FilterSingleTrait.super.isBool(v, predicate);
	}

	@Override
	default boolean isNotBool(boolean v, @Nonnull LObjBoolPredicate<? super T> predicate) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && FilterSingleTrait.super.isNotBool(v, predicate);
	}

	@Override
	default boolean isByte(byte v, @Nonnull LObjBytePredicate<? super T> predicate) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && FilterSingleTrait.super.isByte(v, predicate);
	}

	@Override
	default boolean isNotByte(byte v, @Nonnull LObjBytePredicate<? super T> predicate) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && FilterSingleTrait.super.isNotByte(v, predicate);
	}

	@Override
	default boolean isDbl(double v, @Nonnull LObjDblPredicate<? super T> predicate) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && FilterSingleTrait.super.isDbl(v, predicate);
	}

	@Override
	default boolean isNotDbl(double v, @Nonnull LObjDblPredicate<? super T> predicate) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && FilterSingleTrait.super.isNotDbl(v, predicate);
	}

	@Override
	default boolean isChar(char v, @Nonnull LObjCharPredicate<? super T> predicate) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && FilterSingleTrait.super.isChar(v, predicate);
	}

	@Override
	default boolean isNotChar(char v, @Nonnull LObjCharPredicate<? super T> predicate) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && FilterSingleTrait.super.isNotChar(v, predicate);
	}

	@Override
	default boolean isSrt(short v, @Nonnull LObjSrtPredicate<? super T> predicate) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && FilterSingleTrait.super.isSrt(v, predicate);
	}

	@Override
	default boolean isNotSrt(short v, @Nonnull LObjSrtPredicate<? super T> predicate) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && FilterSingleTrait.super.isNotSrt(v, predicate);
	}

	@Override
	default boolean isFlt(float v, @Nonnull LObjFltPredicate<? super T> predicate) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && FilterSingleTrait.super.isFlt(v, predicate);
	}

	@Override
	default boolean isNotFlt(float v, @Nonnull LObjFltPredicate<? super T> predicate) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && FilterSingleTrait.super.isNotFlt(v, predicate);
	}

	@Override
	default boolean isInt(int v, @Nonnull LObjIntPredicate<? super T> predicate) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && FilterSingleTrait.super.isInt(v, predicate);
	}

	@Override
	default boolean isNotInt(int v, @Nonnull LObjIntPredicate<? super T> predicate) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && FilterSingleTrait.super.isNotInt(v, predicate);
	}

	@Override
	default boolean isLong(long v, @Nonnull LObjLongPredicate<? super T> predicate) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && FilterSingleTrait.super.isLong(v, predicate);
	}

	@Override
	default boolean isNotLong(long v, @Nonnull LObjLongPredicate<? super T> predicate) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && FilterSingleTrait.super.isNotLong(v, predicate);
	}

	@Override
	default <V> boolean is(V v, @Nonnull LBiPredicate<? super T, ? super V> predicate) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && FilterSingleTrait.super.is(v, predicate);
	}

	@Override
	default <V> boolean isNot(V v, @Nonnull LBiPredicate<? super T, ? super V> predicate) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && FilterSingleTrait.super.isNot(v, predicate);
	}

	@Override
	default <V2, V3> boolean is(V2 a2, V3 a3, @Nonnull LTriPredicate<? super T, ? super V2, ? super V3> predicate) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && FilterSingleTrait.super.is(a2, a3, predicate);
	}

	@Override
	default <V2, V3> boolean isNot(V2 a2, V3 a3, @Nonnull LTriPredicate<? super T, ? super V2, ? super V3> predicate) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && FilterSingleTrait.super.isNot(a2, a3, predicate);
	}

	@Override
	default <V2, V3, V4> boolean is(V2 a2, V3 a3, V4 a4, @Nonnull LQuadPredicate<? super T, ? super V2, ? super V3, ? super V4> predicate) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && FilterSingleTrait.super.is(a2, a3, a4, predicate);
	}

	@Override
	default <V2, V3, V4> boolean isNot(V2 a2, V3 a3, V4 a4, @Nonnull LQuadPredicate<? super T, ? super V2, ? super V3, ? super V4> predicate) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && FilterSingleTrait.super.isNot(a2, a3, a4, predicate);
	}

	@Override
	default <V1> boolean isWith(V1 with1, @Nonnull LBiPredicate<? super V1, ? super T> predicate) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && FilterSingleTrait.super.isWith(with1, predicate);
	}

	@Override
	default <V1> boolean isNotWith(V1 with1, @Nonnull LBiPredicate<? super V1, ? super T> predicate) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && FilterSingleTrait.super.isNotWith(with1, predicate);
	}

	@Override
	default <V1, V2> boolean isWith(V1 with1, V2 with2, @Nonnull LTriPredicate<? super V1, ? super V2, ? super T> predicate) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && FilterSingleTrait.super.isWith(with1, with2, predicate);
	}

	@Override
	default <V1, V2> boolean isNotWith(V1 with1, V2 with2, @Nonnull LTriPredicate<? super V1, ? super V2, ? super T> predicate) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && FilterSingleTrait.super.isNotWith(with1, with2, predicate);
	}

	@Override
	default boolean uniIsWith(T with, @Nonnull LBiPredicate<? super T, ? super T> predicate) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && FilterSingleTrait.super.uniIsWith(with, predicate);
	}

	@Override
	default boolean uniIsNotWith(T with, @Nonnull LBiPredicate<? super T, ? super T> predicate) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && FilterSingleTrait.super.uniIsNotWith(with, predicate);
	}

	// </editor-fold>

	// <editor-fold desc="filtering">

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

	default @Nonnull OptBool mapToBool(@Nonnull LPredicate<? super T> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptBool.of(mapping.test(get()))) : OptBool.empty();
	}

	default @Nonnull <K> OptBool mapToBool(K a1, @Nonnull LBiPredicate<? super T, ? super K> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptBool.of(mapping.test(get(), a1))) : OptBool.empty();
	}

	default @Nonnull <K1, K2> OptBool mapToBool(K1 a1, K2 a2, @Nonnull LTriPredicate<? super T, ? super K1, ? super K2> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptBool.of(mapping.test(get(), a1, a2))) : OptBool.empty();
	}

	default @Nonnull <K1, K2, K3> OptBool mapToBool(K1 a1, K2 a2, K3 a3, @Nonnull LQuadPredicate<? super T, ? super K1, ? super K2, ? super K3> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptBool.of(mapping.test(get(), a1, a2, a3))) : OptBool.empty();
	}

	default @Nonnull <K> OptBool mapToBoolWith(K a1, @Nonnull LBiPredicate<? super K, ? super T> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptBool.of(mapping.test(a1, get()))) : OptBool.empty();
	}

	default @Nonnull <K1, K2> OptBool mapToBoolWith(K1 a1, K2 a2, @Nonnull LTriPredicate<? super K1, ? super K2, ? super T> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptBool.of(mapping.test(a1, a2, get()))) : OptBool.empty();
	}

	default @Nonnull <K1, K2, K3> OptBool mapToBoolWith(K1 a1, K2 a2, K3 a3, @Nonnull LQuadPredicate<? super K1, ? super K2, ? super K3, ? super T> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptBool.of(mapping.test(a1, a2, a3, get()))) : OptBool.empty();
	}

	default @Nonnull OptByte mapToByte(@Nonnull LToByteFunction<? super T> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptByte.of(mapping.applyAsByte(get()))) : OptByte.empty();
	}

	default @Nonnull <K> OptByte mapToByte(K a1, @Nonnull LToByteBiFunction<? super T, ? super K> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptByte.of(mapping.applyAsByte(get(), a1))) : OptByte.empty();
	}

	default @Nonnull <K> OptByte mapToByteWith(K a1, @Nonnull LToByteBiFunction<? super K, ? super T> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptByte.of(mapping.applyAsByte(a1, get()))) : OptByte.empty();
	}

	default @Nonnull OptDbl mapToDbl(@Nonnull LToDblFunction<? super T> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptDbl.of(mapping.applyAsDbl(get()))) : OptDbl.empty();
	}

	default @Nonnull <K> OptDbl mapToDbl(K a1, @Nonnull LToDblBiFunction<? super T, ? super K> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptDbl.of(mapping.applyAsDbl(get(), a1))) : OptDbl.empty();
	}

	default @Nonnull <K> OptDbl mapToDblWith(K a1, @Nonnull LToDblBiFunction<? super K, ? super T> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptDbl.of(mapping.applyAsDbl(a1, get()))) : OptDbl.empty();
	}

	default @Nonnull OptChar mapToChar(@Nonnull LToCharFunction<? super T> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptChar.of(mapping.applyAsChar(get()))) : OptChar.empty();
	}

	default @Nonnull <K> OptChar mapToChar(K a1, @Nonnull LToCharBiFunction<? super T, ? super K> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptChar.of(mapping.applyAsChar(get(), a1))) : OptChar.empty();
	}

	default @Nonnull <K> OptChar mapToCharWith(K a1, @Nonnull LToCharBiFunction<? super K, ? super T> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptChar.of(mapping.applyAsChar(a1, get()))) : OptChar.empty();
	}

	default @Nonnull OptSrt mapToSrt(@Nonnull LToSrtFunction<? super T> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptSrt.of(mapping.applyAsSrt(get()))) : OptSrt.empty();
	}

	default @Nonnull <K> OptSrt mapToSrt(K a1, @Nonnull LToSrtBiFunction<? super T, ? super K> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptSrt.of(mapping.applyAsSrt(get(), a1))) : OptSrt.empty();
	}

	default @Nonnull <K> OptSrt mapToSrtWith(K a1, @Nonnull LToSrtBiFunction<? super K, ? super T> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptSrt.of(mapping.applyAsSrt(a1, get()))) : OptSrt.empty();
	}

	default @Nonnull OptFlt mapToFlt(@Nonnull LToFltFunction<? super T> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptFlt.of(mapping.applyAsFlt(get()))) : OptFlt.empty();
	}

	default @Nonnull <K> OptFlt mapToFlt(K a1, @Nonnull LToFltBiFunction<? super T, ? super K> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptFlt.of(mapping.applyAsFlt(get(), a1))) : OptFlt.empty();
	}

	default @Nonnull <K> OptFlt mapToFltWith(K a1, @Nonnull LToFltBiFunction<? super K, ? super T> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptFlt.of(mapping.applyAsFlt(a1, get()))) : OptFlt.empty();
	}

	default @Nonnull OptInt mapToInt(@Nonnull LToIntFunction<? super T> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptInt.of(mapping.applyAsInt(get()))) : OptInt.empty();
	}

	default @Nonnull <K> OptInt mapToInt(K a1, @Nonnull LToIntBiFunction<? super T, ? super K> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptInt.of(mapping.applyAsInt(get(), a1))) : OptInt.empty();
	}

	default @Nonnull <K1, K2> OptInt mapToInt(K1 a1, K2 a2, @Nonnull LToIntTriFunction<? super T, ? super K1, ? super K2> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptInt.of(mapping.applyAsInt(get(), a1, a2))) : OptInt.empty();
	}

	default @Nonnull <K> OptInt mapToIntWith(K a1, @Nonnull LToIntBiFunction<? super K, ? super T> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptInt.of(mapping.applyAsInt(a1, get()))) : OptInt.empty();
	}

	default @Nonnull <K1, K2> OptInt mapToIntWith(K1 a1, K2 a2, @Nonnull LToIntTriFunction<? super K1, ? super K2, ? super T> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptInt.of(mapping.applyAsInt(a1, a2, get()))) : OptInt.empty();
	}

	default @Nonnull OptLong mapToLong(@Nonnull LToLongFunction<? super T> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptLong.of(mapping.applyAsLong(get()))) : OptLong.empty();
	}

	default @Nonnull <K> OptLong mapToLong(K a1, @Nonnull LToLongBiFunction<? super T, ? super K> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptLong.of(mapping.applyAsLong(get(), a1))) : OptLong.empty();
	}

	default @Nonnull <K> OptLong mapToLongWith(K a1, @Nonnull LToLongBiFunction<? super K, ? super T> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptLong.of(mapping.applyAsLong(a1, get()))) : OptLong.empty();
	}

	default @Nonnull <R> Opt<R> map(@Nonnull LFunction<? super T, ? extends R> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (Opt.of(mapping.apply(get()))) : Opt.empty();
	}

	default @Nonnull SELF uniMap(@Nonnull LUnaryOperator<T> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? value(mapping.apply(get())) : voidValue();
	}

	default @Nonnull <R, K> Opt<R> map(K a1, @Nonnull LBiFunction<? super T, ? super K, ? extends R> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (Opt.of(mapping.apply(get(), a1))) : Opt.empty();
	}

	default @Nonnull <R, K1, K2> Opt<R> map(K1 a1, K2 a2, @Nonnull LTriFunction<? super T, ? super K1, ? super K2, ? extends R> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (Opt.of(mapping.apply(get(), a1, a2))) : Opt.empty();
	}

	default @Nonnull <R, K1, K2, K3> Opt<R> map(K1 a1, K2 a2, K3 a3, @Nonnull LQuadFunction<? super T, ? super K1, ? super K2, ? super K3, ? extends R> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (Opt.of(mapping.apply(get(), a1, a2, a3))) : Opt.empty();
	}

	default @Nonnull <R, K> Opt<R> mapWith(K a1, @Nonnull LBiFunction<? super K, ? super T, ? extends R> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (Opt.of(mapping.apply(a1, get()))) : Opt.empty();
	}

	default @Nonnull <R, K1, K2> Opt<R> mapWith(K1 a1, K2 a2, @Nonnull LTriFunction<? super K1, ? super K2, ? super T, ? extends R> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (Opt.of(mapping.apply(a1, a2, get()))) : Opt.empty();
	}

	default @Nonnull <R, K1, K2, K3> Opt<R> mapWith(K1 a1, K2 a2, K3 a3, @Nonnull LQuadFunction<? super K1, ? super K2, ? super K3, ? super T, ? extends R> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (Opt.of(mapping.apply(a1, a2, a3, get()))) : Opt.empty();
	}

	default @Nonnull SELF uniMapWith(T a1, @Nonnull LBinaryOperator<T> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? value(mapping.apply(a1, get())) : voidValue();
	}

	// </editor-fold>

	// <editor-fold desc="flatMap">

	default @Nonnull OptBool flatMapToBool(@Nonnull LFunction<? super T, ? extends OptBoolTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptBool.from(mapping.apply(get())) : OptBool.empty();
	}

	default @Nonnull <K> OptBool flatMapToBool(K a1, @Nonnull LBiFunction<? super T, ? super K, ? extends OptBoolTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptBool.from(mapping.apply(get(), a1)) : OptBool.empty();
	}

	default @Nonnull <K1, K2> OptBool flatMapToBool(K1 a1, K2 a2, @Nonnull LTriFunction<? super T, ? super K1, ? super K2, ? extends OptBoolTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptBool.from(mapping.apply(get(), a1, a2)) : OptBool.empty();
	}

	default @Nonnull <K1, K2, K3> OptBool flatMapToBool(K1 a1, K2 a2, K3 a3, @Nonnull LQuadFunction<? super T, ? super K1, ? super K2, ? super K3, ? extends OptBoolTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptBool.from(mapping.apply(get(), a1, a2, a3)) : OptBool.empty();
	}

	default @Nonnull <K> OptBool flatMapToBoolWith(K a1, @Nonnull LBiFunction<? super K, ? super T, ? extends OptBoolTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptBool.from(mapping.apply(a1, get())) : OptBool.empty();
	}

	default @Nonnull <K1, K2> OptBool flatMapToBoolWith(K1 a1, K2 a2, @Nonnull LTriFunction<? super K1, ? super K2, ? super T, ? extends OptBoolTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptBool.from(mapping.apply(a1, a2, get())) : OptBool.empty();
	}

	default @Nonnull <K1, K2, K3> OptBool flatMapToBoolWith(K1 a1, K2 a2, K3 a3, @Nonnull LQuadFunction<? super K1, ? super K2, ? super K3, ? super T, ? extends OptBoolTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptBool.from(mapping.apply(a1, a2, a3, get())) : OptBool.empty();
	}

	default @Nonnull OptByte flatMapToByte(@Nonnull LFunction<? super T, ? extends OptByteTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptByte.from(mapping.apply(get())) : OptByte.empty();
	}

	default @Nonnull <K> OptByte flatMapToByte(K a1, @Nonnull LBiFunction<? super T, ? super K, ? extends OptByteTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptByte.from(mapping.apply(get(), a1)) : OptByte.empty();
	}

	default @Nonnull <K1, K2> OptByte flatMapToByte(K1 a1, K2 a2, @Nonnull LTriFunction<? super T, ? super K1, ? super K2, ? extends OptByteTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptByte.from(mapping.apply(get(), a1, a2)) : OptByte.empty();
	}

	default @Nonnull <K1, K2, K3> OptByte flatMapToByte(K1 a1, K2 a2, K3 a3, @Nonnull LQuadFunction<? super T, ? super K1, ? super K2, ? super K3, ? extends OptByteTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptByte.from(mapping.apply(get(), a1, a2, a3)) : OptByte.empty();
	}

	default @Nonnull <K> OptByte flatMapToByteWith(K a1, @Nonnull LBiFunction<? super K, ? super T, ? extends OptByteTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptByte.from(mapping.apply(a1, get())) : OptByte.empty();
	}

	default @Nonnull <K1, K2> OptByte flatMapToByteWith(K1 a1, K2 a2, @Nonnull LTriFunction<? super K1, ? super K2, ? super T, ? extends OptByteTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptByte.from(mapping.apply(a1, a2, get())) : OptByte.empty();
	}

	default @Nonnull <K1, K2, K3> OptByte flatMapToByteWith(K1 a1, K2 a2, K3 a3, @Nonnull LQuadFunction<? super K1, ? super K2, ? super K3, ? super T, ? extends OptByteTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptByte.from(mapping.apply(a1, a2, a3, get())) : OptByte.empty();
	}

	default @Nonnull OptDbl flatMapToDbl(@Nonnull LFunction<? super T, ? extends OptDblTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptDbl.from(mapping.apply(get())) : OptDbl.empty();
	}

	default @Nonnull <K> OptDbl flatMapToDbl(K a1, @Nonnull LBiFunction<? super T, ? super K, ? extends OptDblTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptDbl.from(mapping.apply(get(), a1)) : OptDbl.empty();
	}

	default @Nonnull <K1, K2> OptDbl flatMapToDbl(K1 a1, K2 a2, @Nonnull LTriFunction<? super T, ? super K1, ? super K2, ? extends OptDblTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptDbl.from(mapping.apply(get(), a1, a2)) : OptDbl.empty();
	}

	default @Nonnull <K1, K2, K3> OptDbl flatMapToDbl(K1 a1, K2 a2, K3 a3, @Nonnull LQuadFunction<? super T, ? super K1, ? super K2, ? super K3, ? extends OptDblTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptDbl.from(mapping.apply(get(), a1, a2, a3)) : OptDbl.empty();
	}

	default @Nonnull <K> OptDbl flatMapToDblWith(K a1, @Nonnull LBiFunction<? super K, ? super T, ? extends OptDblTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptDbl.from(mapping.apply(a1, get())) : OptDbl.empty();
	}

	default @Nonnull <K1, K2> OptDbl flatMapToDblWith(K1 a1, K2 a2, @Nonnull LTriFunction<? super K1, ? super K2, ? super T, ? extends OptDblTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptDbl.from(mapping.apply(a1, a2, get())) : OptDbl.empty();
	}

	default @Nonnull <K1, K2, K3> OptDbl flatMapToDblWith(K1 a1, K2 a2, K3 a3, @Nonnull LQuadFunction<? super K1, ? super K2, ? super K3, ? super T, ? extends OptDblTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptDbl.from(mapping.apply(a1, a2, a3, get())) : OptDbl.empty();
	}

	default @Nonnull OptChar flatMapToChar(@Nonnull LFunction<? super T, ? extends OptCharTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptChar.from(mapping.apply(get())) : OptChar.empty();
	}

	default @Nonnull <K> OptChar flatMapToChar(K a1, @Nonnull LBiFunction<? super T, ? super K, ? extends OptCharTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptChar.from(mapping.apply(get(), a1)) : OptChar.empty();
	}

	default @Nonnull <K1, K2> OptChar flatMapToChar(K1 a1, K2 a2, @Nonnull LTriFunction<? super T, ? super K1, ? super K2, ? extends OptCharTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptChar.from(mapping.apply(get(), a1, a2)) : OptChar.empty();
	}

	default @Nonnull <K1, K2, K3> OptChar flatMapToChar(K1 a1, K2 a2, K3 a3, @Nonnull LQuadFunction<? super T, ? super K1, ? super K2, ? super K3, ? extends OptCharTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptChar.from(mapping.apply(get(), a1, a2, a3)) : OptChar.empty();
	}

	default @Nonnull <K> OptChar flatMapToCharWith(K a1, @Nonnull LBiFunction<? super K, ? super T, ? extends OptCharTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptChar.from(mapping.apply(a1, get())) : OptChar.empty();
	}

	default @Nonnull <K1, K2> OptChar flatMapToCharWith(K1 a1, K2 a2, @Nonnull LTriFunction<? super K1, ? super K2, ? super T, ? extends OptCharTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptChar.from(mapping.apply(a1, a2, get())) : OptChar.empty();
	}

	default @Nonnull <K1, K2, K3> OptChar flatMapToCharWith(K1 a1, K2 a2, K3 a3, @Nonnull LQuadFunction<? super K1, ? super K2, ? super K3, ? super T, ? extends OptCharTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptChar.from(mapping.apply(a1, a2, a3, get())) : OptChar.empty();
	}

	default @Nonnull OptSrt flatMapToSrt(@Nonnull LFunction<? super T, ? extends OptSrtTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptSrt.from(mapping.apply(get())) : OptSrt.empty();
	}

	default @Nonnull <K> OptSrt flatMapToSrt(K a1, @Nonnull LBiFunction<? super T, ? super K, ? extends OptSrtTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptSrt.from(mapping.apply(get(), a1)) : OptSrt.empty();
	}

	default @Nonnull <K1, K2> OptSrt flatMapToSrt(K1 a1, K2 a2, @Nonnull LTriFunction<? super T, ? super K1, ? super K2, ? extends OptSrtTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptSrt.from(mapping.apply(get(), a1, a2)) : OptSrt.empty();
	}

	default @Nonnull <K1, K2, K3> OptSrt flatMapToSrt(K1 a1, K2 a2, K3 a3, @Nonnull LQuadFunction<? super T, ? super K1, ? super K2, ? super K3, ? extends OptSrtTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptSrt.from(mapping.apply(get(), a1, a2, a3)) : OptSrt.empty();
	}

	default @Nonnull <K> OptSrt flatMapToSrtWith(K a1, @Nonnull LBiFunction<? super K, ? super T, ? extends OptSrtTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptSrt.from(mapping.apply(a1, get())) : OptSrt.empty();
	}

	default @Nonnull <K1, K2> OptSrt flatMapToSrtWith(K1 a1, K2 a2, @Nonnull LTriFunction<? super K1, ? super K2, ? super T, ? extends OptSrtTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptSrt.from(mapping.apply(a1, a2, get())) : OptSrt.empty();
	}

	default @Nonnull <K1, K2, K3> OptSrt flatMapToSrtWith(K1 a1, K2 a2, K3 a3, @Nonnull LQuadFunction<? super K1, ? super K2, ? super K3, ? super T, ? extends OptSrtTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptSrt.from(mapping.apply(a1, a2, a3, get())) : OptSrt.empty();
	}

	default @Nonnull OptFlt flatMapToFlt(@Nonnull LFunction<? super T, ? extends OptFltTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptFlt.from(mapping.apply(get())) : OptFlt.empty();
	}

	default @Nonnull <K> OptFlt flatMapToFlt(K a1, @Nonnull LBiFunction<? super T, ? super K, ? extends OptFltTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptFlt.from(mapping.apply(get(), a1)) : OptFlt.empty();
	}

	default @Nonnull <K1, K2> OptFlt flatMapToFlt(K1 a1, K2 a2, @Nonnull LTriFunction<? super T, ? super K1, ? super K2, ? extends OptFltTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptFlt.from(mapping.apply(get(), a1, a2)) : OptFlt.empty();
	}

	default @Nonnull <K1, K2, K3> OptFlt flatMapToFlt(K1 a1, K2 a2, K3 a3, @Nonnull LQuadFunction<? super T, ? super K1, ? super K2, ? super K3, ? extends OptFltTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptFlt.from(mapping.apply(get(), a1, a2, a3)) : OptFlt.empty();
	}

	default @Nonnull <K> OptFlt flatMapToFltWith(K a1, @Nonnull LBiFunction<? super K, ? super T, ? extends OptFltTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptFlt.from(mapping.apply(a1, get())) : OptFlt.empty();
	}

	default @Nonnull <K1, K2> OptFlt flatMapToFltWith(K1 a1, K2 a2, @Nonnull LTriFunction<? super K1, ? super K2, ? super T, ? extends OptFltTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptFlt.from(mapping.apply(a1, a2, get())) : OptFlt.empty();
	}

	default @Nonnull <K1, K2, K3> OptFlt flatMapToFltWith(K1 a1, K2 a2, K3 a3, @Nonnull LQuadFunction<? super K1, ? super K2, ? super K3, ? super T, ? extends OptFltTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptFlt.from(mapping.apply(a1, a2, a3, get())) : OptFlt.empty();
	}

	default @Nonnull OptInt flatMapToInt(@Nonnull LFunction<? super T, ? extends OptIntTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptInt.from(mapping.apply(get())) : OptInt.empty();
	}

	default @Nonnull <K> OptInt flatMapToInt(K a1, @Nonnull LBiFunction<? super T, ? super K, ? extends OptIntTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptInt.from(mapping.apply(get(), a1)) : OptInt.empty();
	}

	default @Nonnull <K1, K2> OptInt flatMapToInt(K1 a1, K2 a2, @Nonnull LTriFunction<? super T, ? super K1, ? super K2, ? extends OptIntTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptInt.from(mapping.apply(get(), a1, a2)) : OptInt.empty();
	}

	default @Nonnull <K1, K2, K3> OptInt flatMapToInt(K1 a1, K2 a2, K3 a3, @Nonnull LQuadFunction<? super T, ? super K1, ? super K2, ? super K3, ? extends OptIntTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptInt.from(mapping.apply(get(), a1, a2, a3)) : OptInt.empty();
	}

	default @Nonnull <K> OptInt flatMapToIntWith(K a1, @Nonnull LBiFunction<? super K, ? super T, ? extends OptIntTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptInt.from(mapping.apply(a1, get())) : OptInt.empty();
	}

	default @Nonnull <K1, K2> OptInt flatMapToIntWith(K1 a1, K2 a2, @Nonnull LTriFunction<? super K1, ? super K2, ? super T, ? extends OptIntTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptInt.from(mapping.apply(a1, a2, get())) : OptInt.empty();
	}

	default @Nonnull <K1, K2, K3> OptInt flatMapToIntWith(K1 a1, K2 a2, K3 a3, @Nonnull LQuadFunction<? super K1, ? super K2, ? super K3, ? super T, ? extends OptIntTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptInt.from(mapping.apply(a1, a2, a3, get())) : OptInt.empty();
	}

	default @Nonnull OptLong flatMapToLong(@Nonnull LFunction<? super T, ? extends OptLongTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptLong.from(mapping.apply(get())) : OptLong.empty();
	}

	default @Nonnull <K> OptLong flatMapToLong(K a1, @Nonnull LBiFunction<? super T, ? super K, ? extends OptLongTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptLong.from(mapping.apply(get(), a1)) : OptLong.empty();
	}

	default @Nonnull <K1, K2> OptLong flatMapToLong(K1 a1, K2 a2, @Nonnull LTriFunction<? super T, ? super K1, ? super K2, ? extends OptLongTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptLong.from(mapping.apply(get(), a1, a2)) : OptLong.empty();
	}

	default @Nonnull <K1, K2, K3> OptLong flatMapToLong(K1 a1, K2 a2, K3 a3, @Nonnull LQuadFunction<? super T, ? super K1, ? super K2, ? super K3, ? extends OptLongTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptLong.from(mapping.apply(get(), a1, a2, a3)) : OptLong.empty();
	}

	default @Nonnull <K> OptLong flatMapToLongWith(K a1, @Nonnull LBiFunction<? super K, ? super T, ? extends OptLongTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptLong.from(mapping.apply(a1, get())) : OptLong.empty();
	}

	default @Nonnull <K1, K2> OptLong flatMapToLongWith(K1 a1, K2 a2, @Nonnull LTriFunction<? super K1, ? super K2, ? super T, ? extends OptLongTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptLong.from(mapping.apply(a1, a2, get())) : OptLong.empty();
	}

	default @Nonnull <K1, K2, K3> OptLong flatMapToLongWith(K1 a1, K2 a2, K3 a3, @Nonnull LQuadFunction<? super K1, ? super K2, ? super K3, ? super T, ? extends OptLongTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptLong.from(mapping.apply(a1, a2, a3, get())) : OptLong.empty();
	}

	default @Nonnull <R> Opt<R> flatMap(@Nonnull LFunction<? super T, ? extends OptTrait<? extends R, ?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? Opt.from(mapping.apply(get())) : Opt.empty();
	}

	default @Nonnull SELF flatUniMap(@Nonnull LFunction<T, ? extends OptTrait<? extends T, ? extends SELF>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? valueFrom(mapping.apply(get())) : voidValue();
	}

	default @Nonnull <R, K> Opt<R> flatMap(K a1, @Nonnull LBiFunction<? super T, ? super K, ? extends OptTrait<? extends R, ?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? Opt.from(mapping.apply(get(), a1)) : Opt.empty();
	}

	default @Nonnull <R, K1, K2> Opt<R> flatMap(K1 a1, K2 a2, @Nonnull LTriFunction<? super T, ? super K1, ? super K2, ? extends OptTrait<? extends R, ?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? Opt.from(mapping.apply(get(), a1, a2)) : Opt.empty();
	}

	default @Nonnull <R, K1, K2, K3> Opt<R> flatMap(K1 a1, K2 a2, K3 a3, @Nonnull LQuadFunction<? super T, ? super K1, ? super K2, ? super K3, ? extends OptTrait<? extends R, ?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? Opt.from(mapping.apply(get(), a1, a2, a3)) : Opt.empty();
	}

	default @Nonnull <R, K> Opt<R> flatMapWith(K a1, @Nonnull LBiFunction<? super K, ? super T, ? extends OptTrait<? extends R, ?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? Opt.from(mapping.apply(a1, get())) : Opt.empty();
	}

	default @Nonnull <R, K1, K2> Opt<R> flatMapWith(K1 a1, K2 a2, @Nonnull LTriFunction<? super K1, ? super K2, ? super T, ? extends OptTrait<? extends R, ?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? Opt.from(mapping.apply(a1, a2, get())) : Opt.empty();
	}

	default @Nonnull <R, K1, K2, K3> Opt<R> flatMapWith(K1 a1, K2 a2, K3 a3, @Nonnull LQuadFunction<? super K1, ? super K2, ? super K3, ? super T, ? extends OptTrait<? extends R, ?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? Opt.from(mapping.apply(a1, a2, a3, get())) : Opt.empty();
	}

	default @Nonnull SELF flatUniMapWith(T a1, @Nonnull LBiFunction<T, T, ? extends OptTrait<? extends T, ? extends SELF>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? valueFrom(mapping.apply(a1, get())) : voidValue();
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

	default SELF ifPresent(@Nonnull LConsumer<? super T> action) {
		Null.nonNullArg(action, "action");
		if (isPresent()) {
			action.accept(get());
		}
		return self();
	}

	default SELF ifPresent(@Nonnull LConsumer<? super T> action, @Nonnull LAction emptyAction) {
		Null.nonNullArg(action, "action");
		if (isPresent()) {
			action.accept(get());
		} else {
			emptyAction.execute();
		}
		return self();
	}

	default <K> SELF ifPresent(K a1, @Nonnull LBiConsumer<? super T, ? super K> action) {
		Null.nonNullArg(action, "action");
		if (isPresent()) {
			action.accept(get(), a1);
		}
		return self();
	}

	default <K> SELF ifPresentWith(K a1, @Nonnull LBiConsumer<? super K, ? super T> action) {
		Null.nonNullArg(action, "action");
		if (isPresent()) {
			action.accept(a1, get());
		}
		return self();
	}

	default <K1, K2> SELF ifPresentWith(K1 a1, K2 a2, @Nonnull LTriConsumer<? super K1, ? super K2, ? super T> action) {
		Null.nonNullArg(action, "action");
		if (isPresent()) {
			action.accept(a1, a2, get());
		}
		return self();
	}

	// </editor-fold>

	/** Compared to ifPresent it will simply fails if there is no value */
	default @Nonnull SELF visit(@Nonnull LConsumer<T> consumer) {
		Null.nonNullArg(consumer, "consumer");
		consumer.accept(get());
		return self();
	}

	/** Compared to ifPresent if no value is present then it will be substituted with null.  */
	default @Nonnull SELF visitNullable(@Nonnull LConsumer<T> consumer) {
		Null.nonNullArg(consumer, "consumer");
		consumer.accept(nullable());
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

	default @Nonnull T orElseThrow() {
		if (isPresent()) {
			return get();
		}

		throw Handling.create(X::noSuchElement);
	}

	default @Nonnull T orElseThrow(@Nonnull ExF<RuntimeException> fx) {
		if (isPresent()) {
			return get();
		}

		Null.nonNullArg(fx, "fx");
		throw Handling.create(fx);
	}

	default @Nonnull T orElseThrow(@Nonnull ExMF<RuntimeException> fx, @Nullable String msg) {
		if (isPresent()) {
			return get();
		}

		Null.nonNullArg(fx, "fx");
		throw Handling.create(fx, msg);
	}

	default @Nonnull T orElseThrow(@Nonnull ExMF<RuntimeException> fx, @Nullable String msg, @Nullable Object param1) {
		if (isPresent()) {
			return get();
		}

		Null.nonNullArg(fx, "fx");
		throw Handling.create(fx, msg, param1);
	}

	default @Nonnull T orElseThrow(@Nonnull ExMF<RuntimeException> fx, @Nullable String msg, @Nullable Object param1, @Nullable Object param2) {
		if (isPresent()) {
			return get();
		}

		Null.nonNullArg(fx, "fx");
		throw Handling.create(fx, msg, param1, param2);
	}

	default @Nonnull T orElseThrow(@Nonnull ExMF<RuntimeException> fx, @Nullable String msg, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		if (isPresent()) {
			return get();
		}

		Null.nonNullArg(fx, "fx");
		throw Handling.create(fx, msg, param1, param2, param3);
	}

	default T orElse(@Nullable T value) {
		return isPresent() ? get() : value;
	}

	default T orElseGet(@Nonnull LSupplier<? extends T> supplier) {
		Null.nonNullArg(supplier, "supplier");
		return isPresent() ? get() : supplier.get();
	}

	default SELF orGet(@Nonnull LSupplier<? extends T> supplier) {
		Null.nonNullArg(supplier, "supplier");
		return isPresent() ? self() : value(supplier.get());
	}

	default SELF orFlatGet(@Nonnull LSupplier<? extends OptTrait<? extends T, ?>> supplier) {
		Null.nonNullArg(supplier, "supplier");
		return isPresent() ? self() : valueFrom(supplier.get());
	}

	default SELF or(@Nullable T value) {
		return isPresent() ? self() : value(value);
	}

	default SELF orOpt(@Nonnull OptTrait<? extends T, ?> opt) {
		Null.nonNullArg(opt, "opt");
		return isPresent() ? self() : valueFrom(opt);
	}

	default <K> T orElseApply(K a1, @Nonnull LFunction<? super K, ? extends T> supplier) {
		Null.nonNullArg(supplier, "supplier");
		return isPresent() ? get() : supplier.apply(a1);
	}

	default <K> SELF orApply(K a1, @Nonnull LFunction<? super K, ? extends T> supplier) {
		Null.nonNullArg(supplier, "supplier");
		return isPresent() ? self() : value(supplier.apply(a1));
	}

	default <K> SELF orFlatApply(K a1, @Nonnull LFunction<? super K, ? extends OptTrait<? extends T, ?>> supplier) {
		Null.nonNullArg(supplier, "supplier");
		return isPresent() ? self() : valueFrom(supplier.apply(a1));
	}

	default <K1, K2> T orElseApply(K1 a1, K2 a2, @Nonnull LBiFunction<? super K1, ? super K2, ? extends T> supplier) {
		Null.nonNullArg(supplier, "supplier");
		return isPresent() ? get() : supplier.apply(a1, a2);
	}

	default <K1, K2> SELF orApply(K1 a1, K2 a2, @Nonnull LBiFunction<? super K1, ? super K2, ? extends T> supplier) {
		Null.nonNullArg(supplier, "supplier");
		return isPresent() ? self() : value(supplier.apply(a1, a2));
	}

	default <K1, K2> SELF orFlatApply(K1 a1, K2 a2, @Nonnull LBiFunction<? super K1, ? super K2, ? extends OptTrait<? extends T, ?>> supplier) {
		Null.nonNullArg(supplier, "supplier");
		return isPresent() ? self() : valueFrom(supplier.apply(a1, a2));
	}

	// </editor-fold>

	default Optional<T> toOptional() {
		return isPresent() ? Optional.of(value()) : Optional.empty();
	}

}
