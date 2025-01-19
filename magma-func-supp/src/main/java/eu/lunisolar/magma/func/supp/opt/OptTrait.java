/*
 * This file is part of "lunisolar-magma".
 *
 * (C) Copyright 2014-2023 Lunisolar (http://lunisolar.eu/).
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
public interface OptTrait<T, SELF extends OptTrait<T, SELF>>
		extends
			FluentTrait<SELF>,
			aValue<a<T>>,
			CheckTrait<T, SELF>,
			FilterSingleTrait<T, SELF>,
			IsTrait<T, SELF>,
			DoIfSingleTrait<T, SELF>,
			UseSingleTrait<T, SELF>,
			UniMapTrait<T, SELF>,
			AutoCloseable {

	//<editor-fold desc="forcing ValueTrait re-implementation">

	@Override
	@Nonnull
	SELF value(@Nullable T value);

	@Override
	@Nonnull
	SELF voidValue();

	//</editor-fold>

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

	//<editor-fold desc="isPresent() dependant boolean terminals">

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
	default <V> boolean isA(V[] a2, @Nonnull LBiPredicate<T, V[]> predicate) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && FilterSingleTrait.super.isA(a2, predicate);
	}

	@Override
	default <V> boolean isNotA(V[] a2, @Nonnull LBiPredicate<T, V[]> predicate) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && FilterSingleTrait.super.isNotA(a2, predicate);
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

	//</editor-fold>

	//<editor-fold desc="filtering">

	//</editor-fold>

	default SELF butNot(char value) {
		return isPresent() ? (Is.equal(value(), value) ? voidValue() : fluentCtx()) : voidValue();
	}

	public default <R> Opt<R> filterAndMap(@Nonnull Class<R> clazz) {
		Null.nonNullArg(clazz, "clazz");
		return (isPresent() && clazz.isInstance(this.get())) ? (Opt) this : Opt.empty();
	}

	public default <R> Opt<R> alwaysMap(@Nonnull LFunction<? super T, R> mapping) {
		Null.nonNullArg(mapping, "mapping");
		R result = mapping.apply(isPresent() ? get() : null);
		return Opt.of(result);
	}

	public default <R, O extends OptTrait<R, O>> O alwaysFlatMap(@Nonnull LFunction<? super T, O> mapping) {
		Null.nonNullArg(mapping, "mapping");
		O result = mapping.apply(isPresent() ? get() : null);
		return Objects.requireNonNull(result);
	}

	public default <R, O extends OptTrait<R, O>> O alwaysSelfFlatMap(@Nonnull LFunction<SELF, O> mapping) {
		Null.nonNullArg(mapping, "mapping");
		O result = mapping.apply(fluentCtx());
		return Objects.requireNonNull(result);
	}

	public default <R, K> Opt<R> alwaysMap(K a1, @Nonnull LBiFunction<? super T, ? super K, R> mapping) {
		Null.nonNullArg(mapping, "mapping");
		R result = mapping.apply(isPresent() ? get() : null, a1);
		return Opt.of(result);
	}

	public default <R, O extends OptTrait<R, O>, K> O alwaysFlatMap(K a1, @Nonnull LBiFunction<? super T, ? super K, O> mapping) {
		Null.nonNullArg(mapping, "mapping");
		O result = mapping.apply(isPresent() ? get() : null, a1);
		return Objects.requireNonNull(result);
	}

	public default <R, O extends OptTrait<R, O>, K> O alwaysSelfFlatMap(K a1, @Nonnull LBiFunction<SELF, ? super K, O> mapping) {
		Null.nonNullArg(mapping, "mapping");
		O result = mapping.apply(fluentCtx(), a1);
		return Objects.requireNonNull(result);
	}

	public default <R, K> Opt<R> alwaysMapWith(K a1, @Nonnull LBiFunction<? super K, ? super T, R> mapping) {
		Null.nonNullArg(mapping, "mapping");
		R result = mapping.apply(a1, isPresent() ? get() : null);
		return Opt.of(result);
	}

	public default <R, O extends OptTrait<R, O>, K> O alwaysFlatMapWith(K a1, @Nonnull LBiFunction<? super K, T, O> mapping) {
		Null.nonNullArg(mapping, "mapping");
		O result = mapping.apply(a1, isPresent() ? get() : null);
		return Objects.requireNonNull(result);
	}

	public default <R, O extends OptTrait<R, O>, K> O alwaysSelfFlatMapWith(K a1, @Nonnull LBiFunction<? super K, SELF, O> mapping) {
		Null.nonNullArg(mapping, "mapping");
		O result = mapping.apply(a1, fluentCtx());
		return Objects.requireNonNull(result);
	}

	//<editor-fold desc="uniMap">

	default @Nonnull SELF uniMap(@Nonnull LUnaryOperator<T> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? value(mapping.apply(get())) : voidValue();
	}

	default @Nonnull <K> SELF uniMap(K a1, @Nonnull LBiFunction<T, K, T> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? value(mapping.apply(get(), a1)) : voidValue();
	}

	default @Nonnull <K> SELF uniMapWith(K a1, @Nonnull LBiFunction<K, T, T> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? value(mapping.apply(a1, get())) : voidValue();
	}

	default @Nonnull <K1, K2> SELF uniMap(K1 a1, K2 a2, @Nonnull LTriFunction<T, K1, K2, T> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? value(mapping.apply(get(), a1, a2)) : voidValue();
	}

	default @Nonnull <K1, K2> SELF uniMapWith(K1 a1, K2 a2, @Nonnull LTriFunction<K1, K2, T, T> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? value(mapping.apply(a1, a2, get())) : voidValue();
	}

	default @Nonnull <K1, K2, K3> SELF uniMap(K1 a1, K2 a2, K3 a3, @Nonnull LQuadFunction<T, K1, K2, K3, T> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? value(mapping.apply(get(), a1, a2, a3)) : voidValue();
	}

	default @Nonnull <K1, K2, K3> SELF uniMapWith(K1 a1, K2 a2, K3 a3, @Nonnull LQuadFunction<K1, K2, K3, T, T> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? value(mapping.apply(a1, a2, a3, get())) : voidValue();
	}

	default @Nonnull <K1, K2, K3, K4> SELF uniMap(K1 a1, K2 a2, K3 a3, K4 a4, @Nonnull LQuintFunction<T, K1, K2, K3, K4, T> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? value(mapping.apply(get(), a1, a2, a3, a4)) : voidValue();
	}

	default @Nonnull <K1, K2, K3, K4> SELF uniMapWith(K1 a1, K2 a2, K3 a3, K4 a4, @Nonnull LQuintFunction<K1, K2, K3, K4, T, T> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? value(mapping.apply(a1, a2, a3, a4, get())) : voidValue();
	}

	//</editor-fold>

	//<editor-fold desc="map">

	default @Nonnull OptBool mapToBool(@Nonnull LPredicate<? super T> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptBool.of(mapping.test(get()))) : OptBool.empty();
	}

	default @Nonnull <K> OptBool mapToBool(K a1, @Nonnull LBiPredicate<? super T, ? super K> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptBool.of(mapping.test(get(), a1))) : OptBool.empty();
	}

	default @Nonnull <K> OptBool mapToBoolWith(K a1, @Nonnull LBiPredicate<? super K, ? super T> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptBool.of(mapping.test(a1, get()))) : OptBool.empty();
	}

	default @Nonnull <K1, K2> OptBool mapToBool(K1 a1, K2 a2, @Nonnull LTriPredicate<? super T, ? super K1, ? super K2> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptBool.of(mapping.test(get(), a1, a2))) : OptBool.empty();
	}

	default @Nonnull <K1, K2> OptBool mapToBoolWith(K1 a1, K2 a2, @Nonnull LTriPredicate<? super K1, ? super K2, ? super T> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptBool.of(mapping.test(a1, a2, get()))) : OptBool.empty();
	}

	default @Nonnull <K1, K2, K3> OptBool mapToBool(K1 a1, K2 a2, K3 a3, @Nonnull LQuadPredicate<? super T, ? super K1, ? super K2, ? super K3> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptBool.of(mapping.test(get(), a1, a2, a3))) : OptBool.empty();
	}

	default @Nonnull <K1, K2, K3> OptBool mapToBoolWith(K1 a1, K2 a2, K3 a3, @Nonnull LQuadPredicate<? super K1, ? super K2, ? super K3, ? super T> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptBool.of(mapping.test(a1, a2, a3, get()))) : OptBool.empty();
	}

	default @Nonnull <K1, K2, K3, K4> OptBool mapToBool(K1 a1, K2 a2, K3 a3, K4 a4, @Nonnull LQuintPredicate<? super T, ? super K1, ? super K2, ? super K3, ? super K4> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptBool.of(mapping.test(get(), a1, a2, a3, a4))) : OptBool.empty();
	}

	default @Nonnull <K1, K2, K3, K4> OptBool mapToBoolWith(K1 a1, K2 a2, K3 a3, K4 a4, @Nonnull LQuintPredicate<? super K1, ? super K2, ? super K3, ? super K4, ? super T> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptBool.of(mapping.test(a1, a2, a3, a4, get()))) : OptBool.empty();
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

	default @Nonnull <K> OptInt mapToIntWith(K a1, @Nonnull LToIntBiFunction<? super K, ? super T> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptInt.of(mapping.applyAsInt(a1, get()))) : OptInt.empty();
	}

	default @Nonnull <K1, K2> OptInt mapToInt(K1 a1, K2 a2, @Nonnull LToIntTriFunction<? super T, ? super K1, ? super K2> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptInt.of(mapping.applyAsInt(get(), a1, a2))) : OptInt.empty();
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

	default @Nonnull <K, R> Opt<R> map(K a1, @Nonnull LBiFunction<? super T, ? super K, ? extends R> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (Opt.of(mapping.apply(get(), a1))) : Opt.empty();
	}

	default @Nonnull <K, R> Opt<R> mapWith(K a1, @Nonnull LBiFunction<? super K, ? super T, ? extends R> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (Opt.of(mapping.apply(a1, get()))) : Opt.empty();
	}

	default @Nonnull <K1, K2, R> Opt<R> map(K1 a1, K2 a2, @Nonnull LTriFunction<? super T, ? super K1, ? super K2, ? extends R> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (Opt.of(mapping.apply(get(), a1, a2))) : Opt.empty();
	}

	default @Nonnull <K1, K2, R> Opt<R> mapWith(K1 a1, K2 a2, @Nonnull LTriFunction<? super K1, ? super K2, ? super T, ? extends R> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (Opt.of(mapping.apply(a1, a2, get()))) : Opt.empty();
	}

	default @Nonnull <K1, K2, K3, R> Opt<R> map(K1 a1, K2 a2, K3 a3, @Nonnull LQuadFunction<? super T, ? super K1, ? super K2, ? super K3, ? extends R> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (Opt.of(mapping.apply(get(), a1, a2, a3))) : Opt.empty();
	}

	default @Nonnull <K1, K2, K3, R> Opt<R> mapWith(K1 a1, K2 a2, K3 a3, @Nonnull LQuadFunction<? super K1, ? super K2, ? super K3, ? super T, ? extends R> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (Opt.of(mapping.apply(a1, a2, a3, get()))) : Opt.empty();
	}

	default @Nonnull <K1, K2, K3, K4, R> Opt<R> map(K1 a1, K2 a2, K3 a3, K4 a4, @Nonnull LQuintFunction<? super T, ? super K1, ? super K2, ? super K3, ? super K4, ? extends R> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (Opt.of(mapping.apply(get(), a1, a2, a3, a4))) : Opt.empty();
	}

	default @Nonnull <K1, K2, K3, K4, R> Opt<R> mapWith(K1 a1, K2 a2, K3 a3, K4 a4, @Nonnull LQuintFunction<? super K1, ? super K2, ? super K3, ? super K4, ? super T, ? extends R> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (Opt.of(mapping.apply(a1, a2, a3, a4, get()))) : Opt.empty();
	}

	//</editor-fold>

	//<editor-fold desc="flatMap">

	default @Nonnull OptBool flatMapToBool(@Nonnull LFunction<? super T, ? extends OptBoolTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptBool.from(mapping.apply(get())) : OptBool.empty();
	}

	default @Nonnull <K> OptBool flatMapToBool(K a1, @Nonnull LBiFunction<? super T, ? super K, ? extends OptBoolTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptBool.from(mapping.apply(get(), a1)) : OptBool.empty();
	}

	default @Nonnull <K> OptBool flatMapToBoolWith(K a1, @Nonnull LBiFunction<? super K, ? super T, ? extends OptBoolTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptBool.from(mapping.apply(a1, get())) : OptBool.empty();
	}

	default @Nonnull <K1, K2> OptBool flatMapToBool(K1 a1, K2 a2, @Nonnull LTriFunction<? super T, ? super K1, ? super K2, ? extends OptBoolTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptBool.from(mapping.apply(get(), a1, a2)) : OptBool.empty();
	}

	default @Nonnull <K1, K2> OptBool flatMapToBoolWith(K1 a1, K2 a2, @Nonnull LTriFunction<? super K1, ? super K2, ? super T, ? extends OptBoolTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptBool.from(mapping.apply(a1, a2, get())) : OptBool.empty();
	}

	default @Nonnull <K1, K2, K3> OptBool flatMapToBool(K1 a1, K2 a2, K3 a3, @Nonnull LQuadFunction<? super T, ? super K1, ? super K2, ? super K3, ? extends OptBoolTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptBool.from(mapping.apply(get(), a1, a2, a3)) : OptBool.empty();
	}

	default @Nonnull <K1, K2, K3> OptBool flatMapToBoolWith(K1 a1, K2 a2, K3 a3, @Nonnull LQuadFunction<? super K1, ? super K2, ? super K3, ? super T, ? extends OptBoolTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptBool.from(mapping.apply(a1, a2, a3, get())) : OptBool.empty();
	}

	default @Nonnull <K1, K2, K3, K4> OptBool flatMapToBool(K1 a1, K2 a2, K3 a3, K4 a4, @Nonnull LQuintFunction<? super T, ? super K1, ? super K2, ? super K3, ? super K4, ? extends OptBoolTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptBool.from(mapping.apply(get(), a1, a2, a3, a4)) : OptBool.empty();
	}

	default @Nonnull <K1, K2, K3, K4> OptBool flatMapToBoolWith(K1 a1, K2 a2, K3 a3, K4 a4, @Nonnull LQuintFunction<? super K1, ? super K2, ? super K3, ? super K4, ? super T, ? extends OptBoolTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptBool.from(mapping.apply(a1, a2, a3, a4, get())) : OptBool.empty();
	}

	default @Nonnull OptByte flatMapToByte(@Nonnull LFunction<? super T, ? extends OptByteTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptByte.from(mapping.apply(get())) : OptByte.empty();
	}

	default @Nonnull <K> OptByte flatMapToByte(K a1, @Nonnull LBiFunction<? super T, ? super K, ? extends OptByteTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptByte.from(mapping.apply(get(), a1)) : OptByte.empty();
	}

	default @Nonnull <K> OptByte flatMapToByteWith(K a1, @Nonnull LBiFunction<? super K, ? super T, ? extends OptByteTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptByte.from(mapping.apply(a1, get())) : OptByte.empty();
	}

	default @Nonnull <K1, K2> OptByte flatMapToByte(K1 a1, K2 a2, @Nonnull LTriFunction<? super T, ? super K1, ? super K2, ? extends OptByteTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptByte.from(mapping.apply(get(), a1, a2)) : OptByte.empty();
	}

	default @Nonnull <K1, K2> OptByte flatMapToByteWith(K1 a1, K2 a2, @Nonnull LTriFunction<? super K1, ? super K2, ? super T, ? extends OptByteTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptByte.from(mapping.apply(a1, a2, get())) : OptByte.empty();
	}

	default @Nonnull <K1, K2, K3> OptByte flatMapToByte(K1 a1, K2 a2, K3 a3, @Nonnull LQuadFunction<? super T, ? super K1, ? super K2, ? super K3, ? extends OptByteTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptByte.from(mapping.apply(get(), a1, a2, a3)) : OptByte.empty();
	}

	default @Nonnull <K1, K2, K3> OptByte flatMapToByteWith(K1 a1, K2 a2, K3 a3, @Nonnull LQuadFunction<? super K1, ? super K2, ? super K3, ? super T, ? extends OptByteTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptByte.from(mapping.apply(a1, a2, a3, get())) : OptByte.empty();
	}

	default @Nonnull <K1, K2, K3, K4> OptByte flatMapToByte(K1 a1, K2 a2, K3 a3, K4 a4, @Nonnull LQuintFunction<? super T, ? super K1, ? super K2, ? super K3, ? super K4, ? extends OptByteTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptByte.from(mapping.apply(get(), a1, a2, a3, a4)) : OptByte.empty();
	}

	default @Nonnull <K1, K2, K3, K4> OptByte flatMapToByteWith(K1 a1, K2 a2, K3 a3, K4 a4, @Nonnull LQuintFunction<? super K1, ? super K2, ? super K3, ? super K4, ? super T, ? extends OptByteTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptByte.from(mapping.apply(a1, a2, a3, a4, get())) : OptByte.empty();
	}

	default @Nonnull OptDbl flatMapToDbl(@Nonnull LFunction<? super T, ? extends OptDblTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptDbl.from(mapping.apply(get())) : OptDbl.empty();
	}

	default @Nonnull <K> OptDbl flatMapToDbl(K a1, @Nonnull LBiFunction<? super T, ? super K, ? extends OptDblTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptDbl.from(mapping.apply(get(), a1)) : OptDbl.empty();
	}

	default @Nonnull <K> OptDbl flatMapToDblWith(K a1, @Nonnull LBiFunction<? super K, ? super T, ? extends OptDblTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptDbl.from(mapping.apply(a1, get())) : OptDbl.empty();
	}

	default @Nonnull <K1, K2> OptDbl flatMapToDbl(K1 a1, K2 a2, @Nonnull LTriFunction<? super T, ? super K1, ? super K2, ? extends OptDblTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptDbl.from(mapping.apply(get(), a1, a2)) : OptDbl.empty();
	}

	default @Nonnull <K1, K2> OptDbl flatMapToDblWith(K1 a1, K2 a2, @Nonnull LTriFunction<? super K1, ? super K2, ? super T, ? extends OptDblTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptDbl.from(mapping.apply(a1, a2, get())) : OptDbl.empty();
	}

	default @Nonnull <K1, K2, K3> OptDbl flatMapToDbl(K1 a1, K2 a2, K3 a3, @Nonnull LQuadFunction<? super T, ? super K1, ? super K2, ? super K3, ? extends OptDblTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptDbl.from(mapping.apply(get(), a1, a2, a3)) : OptDbl.empty();
	}

	default @Nonnull <K1, K2, K3> OptDbl flatMapToDblWith(K1 a1, K2 a2, K3 a3, @Nonnull LQuadFunction<? super K1, ? super K2, ? super K3, ? super T, ? extends OptDblTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptDbl.from(mapping.apply(a1, a2, a3, get())) : OptDbl.empty();
	}

	default @Nonnull <K1, K2, K3, K4> OptDbl flatMapToDbl(K1 a1, K2 a2, K3 a3, K4 a4, @Nonnull LQuintFunction<? super T, ? super K1, ? super K2, ? super K3, ? super K4, ? extends OptDblTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptDbl.from(mapping.apply(get(), a1, a2, a3, a4)) : OptDbl.empty();
	}

	default @Nonnull <K1, K2, K3, K4> OptDbl flatMapToDblWith(K1 a1, K2 a2, K3 a3, K4 a4, @Nonnull LQuintFunction<? super K1, ? super K2, ? super K3, ? super K4, ? super T, ? extends OptDblTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptDbl.from(mapping.apply(a1, a2, a3, a4, get())) : OptDbl.empty();
	}

	default @Nonnull OptChar flatMapToChar(@Nonnull LFunction<? super T, ? extends OptCharTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptChar.from(mapping.apply(get())) : OptChar.empty();
	}

	default @Nonnull <K> OptChar flatMapToChar(K a1, @Nonnull LBiFunction<? super T, ? super K, ? extends OptCharTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptChar.from(mapping.apply(get(), a1)) : OptChar.empty();
	}

	default @Nonnull <K> OptChar flatMapToCharWith(K a1, @Nonnull LBiFunction<? super K, ? super T, ? extends OptCharTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptChar.from(mapping.apply(a1, get())) : OptChar.empty();
	}

	default @Nonnull <K1, K2> OptChar flatMapToChar(K1 a1, K2 a2, @Nonnull LTriFunction<? super T, ? super K1, ? super K2, ? extends OptCharTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptChar.from(mapping.apply(get(), a1, a2)) : OptChar.empty();
	}

	default @Nonnull <K1, K2> OptChar flatMapToCharWith(K1 a1, K2 a2, @Nonnull LTriFunction<? super K1, ? super K2, ? super T, ? extends OptCharTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptChar.from(mapping.apply(a1, a2, get())) : OptChar.empty();
	}

	default @Nonnull <K1, K2, K3> OptChar flatMapToChar(K1 a1, K2 a2, K3 a3, @Nonnull LQuadFunction<? super T, ? super K1, ? super K2, ? super K3, ? extends OptCharTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptChar.from(mapping.apply(get(), a1, a2, a3)) : OptChar.empty();
	}

	default @Nonnull <K1, K2, K3> OptChar flatMapToCharWith(K1 a1, K2 a2, K3 a3, @Nonnull LQuadFunction<? super K1, ? super K2, ? super K3, ? super T, ? extends OptCharTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptChar.from(mapping.apply(a1, a2, a3, get())) : OptChar.empty();
	}

	default @Nonnull <K1, K2, K3, K4> OptChar flatMapToChar(K1 a1, K2 a2, K3 a3, K4 a4, @Nonnull LQuintFunction<? super T, ? super K1, ? super K2, ? super K3, ? super K4, ? extends OptCharTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptChar.from(mapping.apply(get(), a1, a2, a3, a4)) : OptChar.empty();
	}

	default @Nonnull <K1, K2, K3, K4> OptChar flatMapToCharWith(K1 a1, K2 a2, K3 a3, K4 a4, @Nonnull LQuintFunction<? super K1, ? super K2, ? super K3, ? super K4, ? super T, ? extends OptCharTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptChar.from(mapping.apply(a1, a2, a3, a4, get())) : OptChar.empty();
	}

	default @Nonnull OptSrt flatMapToSrt(@Nonnull LFunction<? super T, ? extends OptSrtTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptSrt.from(mapping.apply(get())) : OptSrt.empty();
	}

	default @Nonnull <K> OptSrt flatMapToSrt(K a1, @Nonnull LBiFunction<? super T, ? super K, ? extends OptSrtTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptSrt.from(mapping.apply(get(), a1)) : OptSrt.empty();
	}

	default @Nonnull <K> OptSrt flatMapToSrtWith(K a1, @Nonnull LBiFunction<? super K, ? super T, ? extends OptSrtTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptSrt.from(mapping.apply(a1, get())) : OptSrt.empty();
	}

	default @Nonnull <K1, K2> OptSrt flatMapToSrt(K1 a1, K2 a2, @Nonnull LTriFunction<? super T, ? super K1, ? super K2, ? extends OptSrtTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptSrt.from(mapping.apply(get(), a1, a2)) : OptSrt.empty();
	}

	default @Nonnull <K1, K2> OptSrt flatMapToSrtWith(K1 a1, K2 a2, @Nonnull LTriFunction<? super K1, ? super K2, ? super T, ? extends OptSrtTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptSrt.from(mapping.apply(a1, a2, get())) : OptSrt.empty();
	}

	default @Nonnull <K1, K2, K3> OptSrt flatMapToSrt(K1 a1, K2 a2, K3 a3, @Nonnull LQuadFunction<? super T, ? super K1, ? super K2, ? super K3, ? extends OptSrtTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptSrt.from(mapping.apply(get(), a1, a2, a3)) : OptSrt.empty();
	}

	default @Nonnull <K1, K2, K3> OptSrt flatMapToSrtWith(K1 a1, K2 a2, K3 a3, @Nonnull LQuadFunction<? super K1, ? super K2, ? super K3, ? super T, ? extends OptSrtTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptSrt.from(mapping.apply(a1, a2, a3, get())) : OptSrt.empty();
	}

	default @Nonnull <K1, K2, K3, K4> OptSrt flatMapToSrt(K1 a1, K2 a2, K3 a3, K4 a4, @Nonnull LQuintFunction<? super T, ? super K1, ? super K2, ? super K3, ? super K4, ? extends OptSrtTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptSrt.from(mapping.apply(get(), a1, a2, a3, a4)) : OptSrt.empty();
	}

	default @Nonnull <K1, K2, K3, K4> OptSrt flatMapToSrtWith(K1 a1, K2 a2, K3 a3, K4 a4, @Nonnull LQuintFunction<? super K1, ? super K2, ? super K3, ? super K4, ? super T, ? extends OptSrtTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptSrt.from(mapping.apply(a1, a2, a3, a4, get())) : OptSrt.empty();
	}

	default @Nonnull OptFlt flatMapToFlt(@Nonnull LFunction<? super T, ? extends OptFltTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptFlt.from(mapping.apply(get())) : OptFlt.empty();
	}

	default @Nonnull <K> OptFlt flatMapToFlt(K a1, @Nonnull LBiFunction<? super T, ? super K, ? extends OptFltTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptFlt.from(mapping.apply(get(), a1)) : OptFlt.empty();
	}

	default @Nonnull <K> OptFlt flatMapToFltWith(K a1, @Nonnull LBiFunction<? super K, ? super T, ? extends OptFltTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptFlt.from(mapping.apply(a1, get())) : OptFlt.empty();
	}

	default @Nonnull <K1, K2> OptFlt flatMapToFlt(K1 a1, K2 a2, @Nonnull LTriFunction<? super T, ? super K1, ? super K2, ? extends OptFltTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptFlt.from(mapping.apply(get(), a1, a2)) : OptFlt.empty();
	}

	default @Nonnull <K1, K2> OptFlt flatMapToFltWith(K1 a1, K2 a2, @Nonnull LTriFunction<? super K1, ? super K2, ? super T, ? extends OptFltTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptFlt.from(mapping.apply(a1, a2, get())) : OptFlt.empty();
	}

	default @Nonnull <K1, K2, K3> OptFlt flatMapToFlt(K1 a1, K2 a2, K3 a3, @Nonnull LQuadFunction<? super T, ? super K1, ? super K2, ? super K3, ? extends OptFltTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptFlt.from(mapping.apply(get(), a1, a2, a3)) : OptFlt.empty();
	}

	default @Nonnull <K1, K2, K3> OptFlt flatMapToFltWith(K1 a1, K2 a2, K3 a3, @Nonnull LQuadFunction<? super K1, ? super K2, ? super K3, ? super T, ? extends OptFltTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptFlt.from(mapping.apply(a1, a2, a3, get())) : OptFlt.empty();
	}

	default @Nonnull <K1, K2, K3, K4> OptFlt flatMapToFlt(K1 a1, K2 a2, K3 a3, K4 a4, @Nonnull LQuintFunction<? super T, ? super K1, ? super K2, ? super K3, ? super K4, ? extends OptFltTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptFlt.from(mapping.apply(get(), a1, a2, a3, a4)) : OptFlt.empty();
	}

	default @Nonnull <K1, K2, K3, K4> OptFlt flatMapToFltWith(K1 a1, K2 a2, K3 a3, K4 a4, @Nonnull LQuintFunction<? super K1, ? super K2, ? super K3, ? super K4, ? super T, ? extends OptFltTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptFlt.from(mapping.apply(a1, a2, a3, a4, get())) : OptFlt.empty();
	}

	default @Nonnull OptInt flatMapToInt(@Nonnull LFunction<? super T, ? extends OptIntTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptInt.from(mapping.apply(get())) : OptInt.empty();
	}

	default @Nonnull <K> OptInt flatMapToInt(K a1, @Nonnull LBiFunction<? super T, ? super K, ? extends OptIntTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptInt.from(mapping.apply(get(), a1)) : OptInt.empty();
	}

	default @Nonnull <K> OptInt flatMapToIntWith(K a1, @Nonnull LBiFunction<? super K, ? super T, ? extends OptIntTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptInt.from(mapping.apply(a1, get())) : OptInt.empty();
	}

	default @Nonnull <K1, K2> OptInt flatMapToInt(K1 a1, K2 a2, @Nonnull LTriFunction<? super T, ? super K1, ? super K2, ? extends OptIntTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptInt.from(mapping.apply(get(), a1, a2)) : OptInt.empty();
	}

	default @Nonnull <K1, K2> OptInt flatMapToIntWith(K1 a1, K2 a2, @Nonnull LTriFunction<? super K1, ? super K2, ? super T, ? extends OptIntTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptInt.from(mapping.apply(a1, a2, get())) : OptInt.empty();
	}

	default @Nonnull <K1, K2, K3> OptInt flatMapToInt(K1 a1, K2 a2, K3 a3, @Nonnull LQuadFunction<? super T, ? super K1, ? super K2, ? super K3, ? extends OptIntTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptInt.from(mapping.apply(get(), a1, a2, a3)) : OptInt.empty();
	}

	default @Nonnull <K1, K2, K3> OptInt flatMapToIntWith(K1 a1, K2 a2, K3 a3, @Nonnull LQuadFunction<? super K1, ? super K2, ? super K3, ? super T, ? extends OptIntTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptInt.from(mapping.apply(a1, a2, a3, get())) : OptInt.empty();
	}

	default @Nonnull <K1, K2, K3, K4> OptInt flatMapToInt(K1 a1, K2 a2, K3 a3, K4 a4, @Nonnull LQuintFunction<? super T, ? super K1, ? super K2, ? super K3, ? super K4, ? extends OptIntTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptInt.from(mapping.apply(get(), a1, a2, a3, a4)) : OptInt.empty();
	}

	default @Nonnull <K1, K2, K3, K4> OptInt flatMapToIntWith(K1 a1, K2 a2, K3 a3, K4 a4, @Nonnull LQuintFunction<? super K1, ? super K2, ? super K3, ? super K4, ? super T, ? extends OptIntTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptInt.from(mapping.apply(a1, a2, a3, a4, get())) : OptInt.empty();
	}

	default @Nonnull OptLong flatMapToLong(@Nonnull LFunction<? super T, ? extends OptLongTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptLong.from(mapping.apply(get())) : OptLong.empty();
	}

	default @Nonnull <K> OptLong flatMapToLong(K a1, @Nonnull LBiFunction<? super T, ? super K, ? extends OptLongTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptLong.from(mapping.apply(get(), a1)) : OptLong.empty();
	}

	default @Nonnull <K> OptLong flatMapToLongWith(K a1, @Nonnull LBiFunction<? super K, ? super T, ? extends OptLongTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptLong.from(mapping.apply(a1, get())) : OptLong.empty();
	}

	default @Nonnull <K1, K2> OptLong flatMapToLong(K1 a1, K2 a2, @Nonnull LTriFunction<? super T, ? super K1, ? super K2, ? extends OptLongTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptLong.from(mapping.apply(get(), a1, a2)) : OptLong.empty();
	}

	default @Nonnull <K1, K2> OptLong flatMapToLongWith(K1 a1, K2 a2, @Nonnull LTriFunction<? super K1, ? super K2, ? super T, ? extends OptLongTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptLong.from(mapping.apply(a1, a2, get())) : OptLong.empty();
	}

	default @Nonnull <K1, K2, K3> OptLong flatMapToLong(K1 a1, K2 a2, K3 a3, @Nonnull LQuadFunction<? super T, ? super K1, ? super K2, ? super K3, ? extends OptLongTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptLong.from(mapping.apply(get(), a1, a2, a3)) : OptLong.empty();
	}

	default @Nonnull <K1, K2, K3> OptLong flatMapToLongWith(K1 a1, K2 a2, K3 a3, @Nonnull LQuadFunction<? super K1, ? super K2, ? super K3, ? super T, ? extends OptLongTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptLong.from(mapping.apply(a1, a2, a3, get())) : OptLong.empty();
	}

	default @Nonnull <K1, K2, K3, K4> OptLong flatMapToLong(K1 a1, K2 a2, K3 a3, K4 a4, @Nonnull LQuintFunction<? super T, ? super K1, ? super K2, ? super K3, ? super K4, ? extends OptLongTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptLong.from(mapping.apply(get(), a1, a2, a3, a4)) : OptLong.empty();
	}

	default @Nonnull <K1, K2, K3, K4> OptLong flatMapToLongWith(K1 a1, K2 a2, K3 a3, K4 a4, @Nonnull LQuintFunction<? super K1, ? super K2, ? super K3, ? super K4, ? super T, ? extends OptLongTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptLong.from(mapping.apply(a1, a2, a3, a4, get())) : OptLong.empty();
	}

	default @Nonnull <R> Opt<R> flatMap(@Nonnull LFunction<? super T, ? extends ValueTrait<? extends R, ?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? Opt.from(mapping.apply(get())) : Opt.empty();
	}

	default @Nonnull SELF flatUniMap(@Nonnull LFunction<T, ? extends ValueTrait<? extends T, ?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? valueFrom(mapping.apply(get())) : voidValue();
	}

	default @Nonnull <K, R> Opt<R> flatMap(K a1, @Nonnull LBiFunction<? super T, ? super K, ? extends ValueTrait<? extends R, ?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? Opt.from(mapping.apply(get(), a1)) : Opt.empty();
	}

	default @Nonnull <K, R> Opt<R> flatMapWith(K a1, @Nonnull LBiFunction<? super K, ? super T, ? extends ValueTrait<? extends R, ?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? Opt.from(mapping.apply(a1, get())) : Opt.empty();
	}

	default @Nonnull <K> SELF flatUniMap(K a1, @Nonnull LBiFunction<T, K, ? extends ValueTrait<? extends T, ?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? valueFrom(mapping.apply(get(), a1)) : voidValue();
	}

	default @Nonnull <K> SELF flatUniMapWith(K a1, @Nonnull LBiFunction<K, T, ? extends ValueTrait<? extends T, ?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? valueFrom(mapping.apply(a1, get())) : voidValue();
	}

	default @Nonnull <K1, K2, R> Opt<R> flatMap(K1 a1, K2 a2, @Nonnull LTriFunction<? super T, ? super K1, ? super K2, ? extends ValueTrait<? extends R, ?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? Opt.from(mapping.apply(get(), a1, a2)) : Opt.empty();
	}

	default @Nonnull <K1, K2, R> Opt<R> flatMapWith(K1 a1, K2 a2, @Nonnull LTriFunction<? super K1, ? super K2, ? super T, ? extends ValueTrait<? extends R, ?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? Opt.from(mapping.apply(a1, a2, get())) : Opt.empty();
	}

	default @Nonnull <K1, K2> SELF flatUniMap(K1 a1, K2 a2, @Nonnull LTriFunction<T, K1, K2, ? extends ValueTrait<? extends T, ?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? valueFrom(mapping.apply(get(), a1, a2)) : voidValue();
	}

	default @Nonnull <K1, K2> SELF flatUniMapWith(K1 a1, K2 a2, @Nonnull LTriFunction<K1, K2, T, ? extends ValueTrait<? extends T, ?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? valueFrom(mapping.apply(a1, a2, get())) : voidValue();
	}

	default @Nonnull <K1, K2, K3, R> Opt<R> flatMap(K1 a1, K2 a2, K3 a3, @Nonnull LQuadFunction<? super T, ? super K1, ? super K2, ? super K3, ? extends ValueTrait<? extends R, ?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? Opt.from(mapping.apply(get(), a1, a2, a3)) : Opt.empty();
	}

	default @Nonnull <K1, K2, K3, R> Opt<R> flatMapWith(K1 a1, K2 a2, K3 a3, @Nonnull LQuadFunction<? super K1, ? super K2, ? super K3, ? super T, ? extends ValueTrait<? extends R, ?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? Opt.from(mapping.apply(a1, a2, a3, get())) : Opt.empty();
	}

	default @Nonnull <K1, K2, K3> SELF flatUniMap(K1 a1, K2 a2, K3 a3, @Nonnull LQuadFunction<T, K1, K2, K3, ? extends ValueTrait<? extends T, ?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? valueFrom(mapping.apply(get(), a1, a2, a3)) : voidValue();
	}

	default @Nonnull <K1, K2, K3> SELF flatUniMapWith(K1 a1, K2 a2, K3 a3, @Nonnull LQuadFunction<K1, K2, K3, T, ? extends ValueTrait<? extends T, ?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? valueFrom(mapping.apply(a1, a2, a3, get())) : voidValue();
	}

	default @Nonnull <K1, K2, K3, K4, R> Opt<R> flatMap(K1 a1, K2 a2, K3 a3, K4 a4, @Nonnull LQuintFunction<? super T, ? super K1, ? super K2, ? super K3, ? super K4, ? extends ValueTrait<? extends R, ?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? Opt.from(mapping.apply(get(), a1, a2, a3, a4)) : Opt.empty();
	}

	default @Nonnull <K1, K2, K3, K4, R> Opt<R> flatMapWith(K1 a1, K2 a2, K3 a3, K4 a4, @Nonnull LQuintFunction<? super K1, ? super K2, ? super K3, ? super K4, ? super T, ? extends ValueTrait<? extends R, ?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? Opt.from(mapping.apply(a1, a2, a3, a4, get())) : Opt.empty();
	}

	default @Nonnull <K1, K2, K3, K4> SELF flatUniMap(K1 a1, K2 a2, K3 a3, K4 a4, @Nonnull LQuintFunction<T, K1, K2, K3, K4, ? extends ValueTrait<? extends T, ?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? valueFrom(mapping.apply(get(), a1, a2, a3, a4)) : voidValue();
	}

	default @Nonnull <K1, K2, K3, K4> SELF flatUniMapWith(K1 a1, K2 a2, K3 a3, K4 a4, @Nonnull LQuintFunction<K1, K2, K3, K4, T, ? extends ValueTrait<? extends T, ?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? valueFrom(mapping.apply(a1, a2, a3, a4, get())) : voidValue();
	}

	//</editor-fold>

	//<editor-fold desc="doIf">

	//</editor-fold>

	//<editor-fold desc="ifPresent">

	default @Nonnull SELF ifVoid(@Nonnull LAction action) {
		Null.nonNullArg(action, "action");
		if (isVoid()) {
			action.execute();
		}
		return fluentCtx();
	}

	default SELF ifPresent(@Nonnull LConsumer<? super T> action) {
		Null.nonNullArg(action, "action");
		if (isPresent()) {
			action.accept(get());
		}
		return fluentCtx();
	}

	default SELF ifPresentOr(@Nonnull LConsumer<? super T> action, @Nonnull LAction emptyAction) {
		Null.nonNullArg(action, "action");
		if (isPresent()) {
			action.accept(get());
		} else {
			emptyAction.execute();
		}
		return fluentCtx();
	}

	default <K1> SELF ifPresent(K1 a2, @Nonnull LBiConsumer<? super T, ? super K1> action) {
		Null.nonNullArg(action, "action");
		if (isPresent()) {
			action.accept(get(), a2);
		}
		return fluentCtx();
	}

	default <K1> SELF ifPresentOr(K1 a2, @Nonnull LBiConsumer<? super T, ? super K1> action, @Nonnull LAction emptyAction) {
		Null.nonNullArg(action, "action");
		if (isPresent()) {
			action.accept(get(), a2);
		} else {
			emptyAction.execute();
		}
		return fluentCtx();
	}

	default <K1> SELF ifPresentOr(K1 a2, @Nonnull LBiConsumer<? super T, ? super K1> action, @Nonnull LConsumer<K1> emptyAction) {
		Null.nonNullArg(action, "action");
		if (isPresent()) {
			action.accept(get(), a2);
		} else {
			emptyAction.accept(a2);
		}
		return fluentCtx();
	}

	default <K1, K2> SELF ifPresent(K1 a2, K2 a3, @Nonnull LTriConsumer<? super T, ? super K1, ? super K2> action) {
		Null.nonNullArg(action, "action");
		if (isPresent()) {
			action.accept(get(), a2, a3);
		}
		return fluentCtx();
	}

	default <K1, K2> SELF ifPresentOr(K1 a2, K2 a3, @Nonnull LTriConsumer<? super T, ? super K1, ? super K2> action, @Nonnull LAction emptyAction) {
		Null.nonNullArg(action, "action");
		if (isPresent()) {
			action.accept(get(), a2, a3);
		} else {
			emptyAction.execute();
		}
		return fluentCtx();
	}

	default <K1, K2> SELF ifPresentOr(K1 a2, K2 a3, @Nonnull LTriConsumer<? super T, ? super K1, ? super K2> action, @Nonnull LBiConsumer<K1, K2> emptyAction) {
		Null.nonNullArg(action, "action");
		if (isPresent()) {
			action.accept(get(), a2, a3);
		} else {
			emptyAction.accept(a2, a3);
		}
		return fluentCtx();
	}

	default <K1, K3, K4> SELF ifPresent(K1 a2, K3 a3, K4 a4, @Nonnull LQuadConsumer<? super T, ? super K1, ? super K3, ? super K4> action) {
		Null.nonNullArg(action, "action");
		if (isPresent()) {
			action.accept(get(), a2, a3, a4);
		}
		return fluentCtx();
	}

	default <K1, K3, K4> SELF ifPresentOr(K1 a2, K3 a3, K4 a4, @Nonnull LQuadConsumer<? super T, ? super K1, ? super K3, ? super K4> action, @Nonnull LAction emptyAction) {
		Null.nonNullArg(action, "action");
		if (isPresent()) {
			action.accept(get(), a2, a3, a4);
		} else {
			emptyAction.execute();
		}
		return fluentCtx();
	}

	default <K1, K3, K4> SELF ifPresentOr(K1 a2, K3 a3, K4 a4, @Nonnull LQuadConsumer<? super T, ? super K1, ? super K3, ? super K4> action, @Nonnull LTriConsumer<K1, K3, K4> emptyAction) {
		Null.nonNullArg(action, "action");
		if (isPresent()) {
			action.accept(get(), a2, a3, a4);
		} else {
			emptyAction.accept(a2, a3, a4);
		}
		return fluentCtx();
	}

	default SELF ifPresentWith(@Nonnull LConsumer<? super T> action) {
		Null.nonNullArg(action, "action");
		if (isPresent()) {
			action.accept(get());
		}
		return fluentCtx();
	}

	default SELF ifPresentOrWith(@Nonnull LConsumer<? super T> action, @Nonnull LAction emptyAction) {
		Null.nonNullArg(action, "action");
		if (isPresent()) {
			action.accept(get());
		} else {
			emptyAction.execute();
		}
		return fluentCtx();
	}

	default <K1> SELF ifPresentWith(K1 a1, @Nonnull LBiConsumer<? super K1, ? super T> action) {
		Null.nonNullArg(action, "action");
		if (isPresent()) {
			action.accept(a1, get());
		}
		return fluentCtx();
	}

	default <K1> SELF ifPresentOrWith(K1 a1, @Nonnull LBiConsumer<? super K1, ? super T> action, @Nonnull LAction emptyAction) {
		Null.nonNullArg(action, "action");
		if (isPresent()) {
			action.accept(a1, get());
		} else {
			emptyAction.execute();
		}
		return fluentCtx();
	}

	default <K1> SELF ifPresentOrWith(K1 a1, @Nonnull LBiConsumer<? super K1, ? super T> action, @Nonnull LConsumer<K1> emptyAction) {
		Null.nonNullArg(action, "action");
		if (isPresent()) {
			action.accept(a1, get());
		} else {
			emptyAction.accept(a1);
		}
		return fluentCtx();
	}

	default <K1, K2> SELF ifPresentWith(K1 a1, K2 a2, @Nonnull LTriConsumer<? super K1, ? super K2, ? super T> action) {
		Null.nonNullArg(action, "action");
		if (isPresent()) {
			action.accept(a1, a2, get());
		}
		return fluentCtx();
	}

	default <K1, K2> SELF ifPresentOrWith(K1 a1, K2 a2, @Nonnull LTriConsumer<? super K1, ? super K2, ? super T> action, @Nonnull LAction emptyAction) {
		Null.nonNullArg(action, "action");
		if (isPresent()) {
			action.accept(a1, a2, get());
		} else {
			emptyAction.execute();
		}
		return fluentCtx();
	}

	default <K1, K2> SELF ifPresentOrWith(K1 a1, K2 a2, @Nonnull LTriConsumer<? super K1, ? super K2, ? super T> action, @Nonnull LBiConsumer<K1, K2> emptyAction) {
		Null.nonNullArg(action, "action");
		if (isPresent()) {
			action.accept(a1, a2, get());
		} else {
			emptyAction.accept(a1, a2);
		}
		return fluentCtx();
	}

	default <K1, K3, K4> SELF ifPresentWith(K1 a1, K3 a2, K4 a3, @Nonnull LQuadConsumer<? super K1, ? super K3, ? super K4, ? super T> action) {
		Null.nonNullArg(action, "action");
		if (isPresent()) {
			action.accept(a1, a2, a3, get());
		}
		return fluentCtx();
	}

	default <K1, K3, K4> SELF ifPresentOrWith(K1 a1, K3 a2, K4 a3, @Nonnull LQuadConsumer<? super K1, ? super K3, ? super K4, ? super T> action, @Nonnull LAction emptyAction) {
		Null.nonNullArg(action, "action");
		if (isPresent()) {
			action.accept(a1, a2, a3, get());
		} else {
			emptyAction.execute();
		}
		return fluentCtx();
	}

	default <K1, K3, K4> SELF ifPresentOrWith(K1 a1, K3 a2, K4 a3, @Nonnull LQuadConsumer<? super K1, ? super K3, ? super K4, ? super T> action, @Nonnull LTriConsumer<K1, K3, K4> emptyAction) {
		Null.nonNullArg(action, "action");
		if (isPresent()) {
			action.accept(a1, a2, a3, get());
		} else {
			emptyAction.accept(a1, a2, a3);
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

	//</editor-fold>

	/** Compared to ifPresent it will simply fail if there is no value */
	default @Nonnull SELF visit(@Nonnull LConsumer<T> consumer) {
		Null.nonNullArg(consumer, "consumer");
		consumer.accept(get());
		return fluentCtx();
	}

	/** Compared to ifPresent if no value is present then it will be substituted with null.  */
	default @Nonnull SELF visitNullable(@Nonnull LConsumer<T> consumer) {
		Null.nonNullArg(consumer, "consumer");
		consumer.accept(nullable());
		return fluentCtx();
	}

	//<editor-fold desc="orElse">

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

	@Override
	default T orElse(@Nullable T value) {
		return isPresent() ? get() : value;
	}

	default T orElseGet(@Nonnull LSupplier<? extends T> supplier) {
		Null.nonNullArg(supplier, "supplier");
		return isPresent() ? get() : supplier.get();
	}

	default SELF orGet(@Nonnull LSupplier<? extends T> supplier) {
		Null.nonNullArg(supplier, "supplier");
		return isPresent() ? fluentCtx() : value(supplier.get());
	}

	default SELF orFlatGet(@Nonnull LSupplier<? extends ValueTrait<? extends T, ?>> supplier) {
		Null.nonNullArg(supplier, "supplier");
		return isPresent() ? fluentCtx() : valueFrom(supplier.get());
	}

	default SELF or(@Nullable T value) {
		return isPresent() ? fluentCtx() : value(value);
	}

	default SELF orOpt(@Nonnull OptTrait<? extends T, ?> opt) {
		Null.nonNullArg(opt, "opt");
		return isPresent() ? fluentCtx() : valueFrom(opt);
	}

	default SELF orValue(@Nonnull ValueTrait<? extends T, ?> value) {
		Null.nonNullArg(value, "value");
		return isPresent() ? fluentCtx() : value(value.value());
	}

	default <K> T orElseApply(K a1, @Nonnull LFunction<? super K, ? extends T> supplier) {
		Null.nonNullArg(supplier, "supplier");
		return isPresent() ? get() : supplier.apply(a1);
	}

	default <K> SELF orApply(K a1, @Nonnull LFunction<? super K, ? extends T> supplier) {
		Null.nonNullArg(supplier, "supplier");
		return isPresent() ? fluentCtx() : value(supplier.apply(a1));
	}

	default <K> SELF orFlatApply(K a1, @Nonnull LFunction<? super K, ? extends ValueTrait<? extends T, ?>> supplier) {
		Null.nonNullArg(supplier, "supplier");
		return isPresent() ? fluentCtx() : valueFrom(supplier.apply(a1));
	}

	default <K1, K2> T orElseApply(K1 a1, K2 a2, @Nonnull LBiFunction<? super K1, ? super K2, ? extends T> supplier) {
		Null.nonNullArg(supplier, "supplier");
		return isPresent() ? get() : supplier.apply(a1, a2);
	}

	default <K1, K2, K3> T orElseApply(K1 a1, K2 a2, K3 a3, @Nonnull LTriFunction<? super K1, ? super K2, ? super K3, ? extends T> supplier) {
		Null.nonNullArg(supplier, "supplier");
		return isPresent() ? get() : supplier.apply(a1, a2, a3);
	}

	default <K1, K2> SELF orApply(K1 a1, K2 a2, @Nonnull LBiFunction<? super K1, ? super K2, ? extends T> supplier) {
		Null.nonNullArg(supplier, "supplier");
		return isPresent() ? fluentCtx() : value(supplier.apply(a1, a2));
	}

	default <K1, K2, K3> SELF orApply(K1 a1, K2 a2, K3 a3, @Nonnull LTriFunction<? super K1, ? super K2, ? super K3, ? extends T> supplier) {
		Null.nonNullArg(supplier, "supplier");
		return isPresent() ? fluentCtx() : value(supplier.apply(a1, a2, a3));
	}

	default <K1, K2> SELF orFlatApply(K1 a1, K2 a2, @Nonnull LBiFunction<? super K1, ? super K2, ? extends ValueTrait<? extends T, ?>> supplier) {
		Null.nonNullArg(supplier, "supplier");
		return isPresent() ? fluentCtx() : valueFrom(supplier.apply(a1, a2));
	}

	default <K1, K2, K3> SELF orFlatApply(K1 a1, K2 a2, K3 a3, @Nonnull LTriFunction<? super K1, ? super K2, ? super K3, ? extends ValueTrait<? extends T, ?>> supplier) {
		Null.nonNullArg(supplier, "supplier");
		return isPresent() ? fluentCtx() : valueFrom(supplier.apply(a1, a2, a3));
	}

	//</editor-fold>

	default Optional<T> toOptional() {
		return isPresent() ? Optional.of(value()) : Optional.empty();
	}

	@Override
	default void close() {
		filterAndMap(AutoCloseable.class).ifPresent(AutoCloseable::close); // any exception will be nested
	}

	default @Nonnull <K, R> Opt<R> op(@Nonnull OptTrait<? extends K, ?> opt2, @Nonnull LBiFunction<? super T, ? super K, ? extends R> both, @Nonnull LFunction<? super T, ? extends R> first, @Nonnull LFunction<? super K, ? extends R> second,
			@Nonnull LSupplier<? extends R> none) {
		return op(this, opt2, both, first, second, none);
	}

	public static @Nonnull <T1, T2, R> Opt<R> op(@Nonnull OptTrait<? extends T1, ?> opt1, @Nonnull OptTrait<? extends T2, ?> opt2, @Nonnull LBiFunction<? super T1, ? super T2, ? extends R> both, @Nonnull LFunction<? super T1, ? extends R> first,
			@Nonnull LFunction<? super T2, ? extends R> second, @Nonnull LSupplier<? extends R> none) {
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
				return Opt.of(none.get());
			}
		}
	}

	default @Nonnull <K, R> Opt<R> simpleOp(@Nonnull OptTrait<? extends T, ?> opt2, T defaultInput, @Nonnull LBiFunction<? super T, ? super T, ? extends R> operation) {
		return simpleOp((OptTrait) this, opt2, defaultInput, operation);
	}

	public static @Nonnull <T, R> Opt<R> simpleOp(@Nonnull OptTrait<? extends T, ?> opt1, @Nonnull OptTrait<? extends T, ?> opt2, T defaultInput, @Nonnull LBiFunction<? super T, ? super T, ? extends R> operation) {
		Null.nonNullArg(opt1, "opt1");
		Null.nonNullArg(opt2, "opt2");
		Null.nonNullArg(defaultInput, "defaultInput");
		return Opt.of(operation.apply(opt1.orElse(Clazz.the(defaultInput)), opt2.orElse(Clazz.the(defaultInput))));
	}

	default @Nonnull <K, R> Opt<R> simpleOp(@Nonnull OptTrait<? extends K, ?> opt2, @Nonnull LBiFunction<? super T, ? super K, ? extends R> operation) {
		return simpleOp((OptTrait) this, opt2, operation);
	}

	public static @Nonnull <T1, T2, R> Opt<R> simpleOp(@Nonnull OptTrait<? extends T1, ?> opt1, @Nonnull OptTrait<? extends T2, ?> opt2, @Nonnull LBiFunction<? super T1, ? super T2, ? extends R> operation) {
		Null.nonNullArg(opt1, "opt1");
		Null.nonNullArg(opt2, "opt2");

		if (opt1.isPresent() && opt2.isPresent()) {
			return Opt.of(operation.apply(opt1.get(), opt2.get()));
		}

		return Opt.empty();
	}

	default @Nonnull <K, R> Opt<R> flatOp(@Nonnull OptTrait<? extends K, ?> opt2, @Nonnull LBiFunction<? super T, ? super K, ? extends ValueTrait<? extends R, ?>> both, @Nonnull LFunction<? super T, ? extends ValueTrait<? extends R, ?>> first,
			@Nonnull LFunction<? super K, ? extends ValueTrait<? extends R, ?>> second, @Nonnull LSupplier<? extends ValueTrait<? extends R, ?>> none) {
		return flatOp((OptTrait) this, opt2, both, first, second, none);
	}

	public static @Nonnull <T1, T2, R> Opt<R> flatOp(@Nonnull OptTrait<? extends T1, ?> opt1, @Nonnull OptTrait<? extends T2, ?> opt2, @Nonnull LBiFunction<? super T1, ? super T2, ? extends ValueTrait<? extends R, ?>> both,
			@Nonnull LFunction<? super T1, ? extends ValueTrait<? extends R, ?>> first, @Nonnull LFunction<? super T2, ? extends ValueTrait<? extends R, ?>> second, @Nonnull LSupplier<? extends ValueTrait<? extends R, ?>> none) {
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

	default @Nonnull Iterator<T> iterator() {
		if (isPresent()) {
			final var value = this.get();
			return new Iterator<T>() {
				private boolean spent = false;
				@Override
				public boolean hasNext() {
					return !spent;
				}

				@Override
				public T next() {
					if (spent)
						throw X.noSuchElement();
					spent = true;
					return value;
				}
			};
		} else {
			return new Iterator<T>() {
				@Override
				public boolean hasNext() {
					return false;
				}

				@Override
				public T next() {
					throw X.noSuchElement();
				}
			};
		}
	}

}
