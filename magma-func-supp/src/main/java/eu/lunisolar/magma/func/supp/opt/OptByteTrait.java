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
public interface OptByteTrait<SELF extends OptByteTrait<SELF>> extends Fluent<SELF>, aValue<aByte>, CheckByteTrait<SELF> {

	// <editor-fold desc="forcing ValueTrait re-implementation">

	@Nonnull
	SELF value(byte value);
	@Nonnull
	SELF voidValue();

	// </editor-fold>

	byte get();

	default @Nullable Byte nullable() {
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

	public default boolean is(@Nonnull LBytePredicate predicate) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && predicate.test(get());
	}

	public default boolean is(@Nonnull LBiBytePredicate predicate, byte a2) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && predicate.test(get(), a2);
	}

	public default boolean is(byte a2, @Nonnull LBiBytePredicate predicate) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && predicate.test(get(), a2);
	}

	public default boolean is(@Nonnull LTriBytePredicate predicate, byte a2, byte a3) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && predicate.test(get(), a2, a3);
	}

	public default boolean is(byte a2, byte a3, @Nonnull LTriBytePredicate predicate) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && predicate.test(get(), a2, a3);
	}

	public default boolean is2(@Nonnull LBiBytePredicate predicate, byte v) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && predicate.test(get(), v);
	}

	public default boolean is2(byte v, @Nonnull LBiBytePredicate predicate) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && predicate.test(get(), v);
	}

	public default boolean is2(@Nonnull LByteIntPredicate predicate, int v) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && predicate.test(get(), v);
	}

	public default boolean is2(int v, @Nonnull LByteIntPredicate predicate) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && predicate.test(get(), v);
	}

	public default <V> boolean is2_(@Nonnull LObjBytePredicate.LByteObjPred<? super V> predicate, V v) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && predicate.testByteObj(get(), v);
	}

	public default <V> boolean is2_(V v, @Nonnull LObjBytePredicate.LByteObjPred<? super V> predicate) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && predicate.testByteObj(get(), v);
	}

	// </editor-fold>

	// <editor-fold desc="filtering">

	public default SELF filter(@Nonnull LBytePredicate predicate) {
		Null.nonNullArg(predicate, "predicate");
		return this.is(predicate) ? self() : voidValue();
	}

	public default SELF filter(@Nonnull LBiBytePredicate predicate, byte a2) {
		Null.nonNullArg(predicate, "predicate");
		return this.is(predicate, a2) ? self() : voidValue();
	}

	public default SELF filter(byte a2, @Nonnull LBiBytePredicate predicate) {
		Null.nonNullArg(predicate, "predicate");
		return this.is(a2, predicate) ? self() : voidValue();
	}

	public default SELF filter(@Nonnull LTriBytePredicate predicate, byte a2, byte a3) {
		Null.nonNullArg(predicate, "predicate");
		return this.is(predicate, a2, a3) ? self() : voidValue();
	}

	public default SELF filter(byte a2, byte a3, @Nonnull LTriBytePredicate predicate) {
		Null.nonNullArg(predicate, "predicate");
		return this.is(a2, a3, predicate) ? self() : voidValue();
	}

	public default SELF filter2(@Nonnull LBiBytePredicate predicate, byte v) {
		Null.nonNullArg(predicate, "predicate");
		return this.is2(predicate, v) ? self() : voidValue();
	}

	public default SELF filter2(byte v, @Nonnull LBiBytePredicate predicate) {
		Null.nonNullArg(predicate, "predicate");
		return this.is2(v, predicate) ? self() : voidValue();
	}

	public default SELF filter2(@Nonnull LByteIntPredicate predicate, int v) {
		Null.nonNullArg(predicate, "predicate");
		return this.is2(predicate, v) ? self() : voidValue();
	}

	public default SELF filter2(int v, @Nonnull LByteIntPredicate predicate) {
		Null.nonNullArg(predicate, "predicate");
		return this.is2(v, predicate) ? self() : voidValue();
	}

	public default <V> SELF filter2_(@Nonnull LObjBytePredicate.LByteObjPred<? super V> predicate, V v) {
		Null.nonNullArg(predicate, "predicate");
		return this.is2_(predicate, v) ? self() : voidValue();
	}

	public default <V> SELF filter2_(V v, @Nonnull LObjBytePredicate.LByteObjPred<? super V> predicate) {
		Null.nonNullArg(predicate, "predicate");
		return this.is2_(v, predicate) ? self() : voidValue();
	}

	// </editor-fold>

	// <editor-fold desc="map">

	public default OptBool mapToBool(@Nonnull LBytePredicate mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptBool.of(mapping.test(get()))) : OptBool.empty();
	}

	public default <K> OptBool mapToBool_(K a2, @Nonnull LObjBytePredicate.LByteObjPred<? super K> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptBool.of(mapping.testByteObj(get(), a2))) : OptBool.empty();
	}

	public default <K> OptBool mapToBoolWith(K a1, @Nonnull LObjBytePredicate<? super K> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptBool.of(mapping.test(a1, get()))) : OptBool.empty();
	}

	public default OptByte map(@Nonnull LByteUnaryOperator mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptByte.of(mapping.applyAsByte(get()))) : OptByte.empty();
	}

	public default SELF perform(@Nonnull LByteUnaryOperator mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? value(mapping.applyAsByte(get())) : voidValue();
	}

	public default SELF performWith(byte a1, @Nonnull LByteBinaryOperator mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? value(mapping.applyAsByte(a1, get())) : voidValue();
	}

	public default OptDbl mapToDbl(@Nonnull LByteToDblFunction mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptDbl.of(mapping.applyAsDbl(get()))) : OptDbl.empty();
	}

	public default OptChar mapToChar(@Nonnull LByteToCharFunction mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptChar.of(mapping.applyAsChar(get()))) : OptChar.empty();
	}

	public default OptSrt mapToSrt(@Nonnull LByteToSrtFunction mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptSrt.of(mapping.applyAsSrt(get()))) : OptSrt.empty();
	}

	public default OptFlt mapToFlt(@Nonnull LByteToFltFunction mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptFlt.of(mapping.applyAsFlt(get()))) : OptFlt.empty();
	}

	public default OptInt mapToInt(@Nonnull LByteToIntFunction mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptInt.of(mapping.applyAsInt(get()))) : OptInt.empty();
	}

	public default OptLong mapToLong(@Nonnull LByteToLongFunction mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptLong.of(mapping.applyAsLong(get()))) : OptLong.empty();
	}

	public default <R> Opt<R> mapToObj(@Nonnull LByteFunction<? extends R> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (Opt.of(mapping.apply(get()))) : Opt.empty();
	}

	public default <R, K> Opt<R> mapToObj_(K a2, @Nonnull LObjByteFunction.LByteObjFunc<? super K, ? extends R> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (Opt.of(mapping.applyByteObj(get(), a2))) : Opt.empty();
	}

	public default <R, K> Opt<R> mapToObjWith(K a1, @Nonnull LObjByteFunction<? super K, ? extends R> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (Opt.of(mapping.apply(a1, get()))) : Opt.empty();
	}

	// </editor-fold>

	// <editor-fold desc="flatMap">

	public default OptBool flatMapToBool(@Nonnull LByteFunction<? extends OptBoolTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptBool.toOpt(mapping.apply(get())) : OptBool.empty();
	}

	public default <K> OptBool flatMapToBool_(K a2, @Nonnull LObjByteFunction.LByteObjFunc<? super K, ? extends OptBoolTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptBool.toOpt(mapping.applyByteObj(get(), a2)) : OptBool.empty();
	}

	public default <K> OptBool flatMapToBoolWith(K a1, @Nonnull LObjByteFunction<? super K, ? extends OptBoolTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptBool.toOpt(mapping.apply(a1, get())) : OptBool.empty();
	}

	public default OptByte flatMap(@Nonnull LByteFunction<? extends OptByteTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptByte.toOpt(mapping.apply(get())) : OptByte.empty();
	}

	public default SELF perform(@Nonnull LByteFunction<? extends OptByteTrait<? extends SELF>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? fromOpt(mapping.apply(get())) : voidValue();
	}

	public default <K> OptByte flatMap_(K a2, @Nonnull LObjByteFunction.LByteObjFunc<? super K, ? extends OptByteTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptByte.toOpt(mapping.applyByteObj(get(), a2)) : OptByte.empty();
	}

	public default <K> OptByte flatMapWith(K a1, @Nonnull LObjByteFunction<? super K, ? extends OptByteTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptByte.toOpt(mapping.apply(a1, get())) : OptByte.empty();
	}

	public default SELF flatPerformWith(byte a1, @Nonnull LBiByteFunction<? extends OptByteTrait<? extends SELF>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? fromOpt(mapping.apply(a1, get())) : voidValue();
	}

	public default OptDbl flatMapToDbl(@Nonnull LByteFunction<? extends OptDblTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptDbl.toOpt(mapping.apply(get())) : OptDbl.empty();
	}

	public default <K> OptDbl flatMapToDbl_(K a2, @Nonnull LObjByteFunction.LByteObjFunc<? super K, ? extends OptDblTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptDbl.toOpt(mapping.applyByteObj(get(), a2)) : OptDbl.empty();
	}

	public default <K> OptDbl flatMapToDblWith(K a1, @Nonnull LObjByteFunction<? super K, ? extends OptDblTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptDbl.toOpt(mapping.apply(a1, get())) : OptDbl.empty();
	}

	public default OptChar flatMapToChar(@Nonnull LByteFunction<? extends OptCharTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptChar.toOpt(mapping.apply(get())) : OptChar.empty();
	}

	public default <K> OptChar flatMapToChar_(K a2, @Nonnull LObjByteFunction.LByteObjFunc<? super K, ? extends OptCharTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptChar.toOpt(mapping.applyByteObj(get(), a2)) : OptChar.empty();
	}

	public default <K> OptChar flatMapToCharWith(K a1, @Nonnull LObjByteFunction<? super K, ? extends OptCharTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptChar.toOpt(mapping.apply(a1, get())) : OptChar.empty();
	}

	public default OptSrt flatMapToSrt(@Nonnull LByteFunction<? extends OptSrtTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptSrt.toOpt(mapping.apply(get())) : OptSrt.empty();
	}

	public default <K> OptSrt flatMapToSrt_(K a2, @Nonnull LObjByteFunction.LByteObjFunc<? super K, ? extends OptSrtTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptSrt.toOpt(mapping.applyByteObj(get(), a2)) : OptSrt.empty();
	}

	public default <K> OptSrt flatMapToSrtWith(K a1, @Nonnull LObjByteFunction<? super K, ? extends OptSrtTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptSrt.toOpt(mapping.apply(a1, get())) : OptSrt.empty();
	}

	public default OptFlt flatMapToFlt(@Nonnull LByteFunction<? extends OptFltTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptFlt.toOpt(mapping.apply(get())) : OptFlt.empty();
	}

	public default <K> OptFlt flatMapToFlt_(K a2, @Nonnull LObjByteFunction.LByteObjFunc<? super K, ? extends OptFltTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptFlt.toOpt(mapping.applyByteObj(get(), a2)) : OptFlt.empty();
	}

	public default <K> OptFlt flatMapToFltWith(K a1, @Nonnull LObjByteFunction<? super K, ? extends OptFltTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptFlt.toOpt(mapping.apply(a1, get())) : OptFlt.empty();
	}

	public default OptInt flatMapToInt(@Nonnull LByteFunction<? extends OptIntTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptInt.toOpt(mapping.apply(get())) : OptInt.empty();
	}

	public default <K> OptInt flatMapToInt_(K a2, @Nonnull LObjByteFunction.LByteObjFunc<? super K, ? extends OptIntTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptInt.toOpt(mapping.applyByteObj(get(), a2)) : OptInt.empty();
	}

	public default <K> OptInt flatMapToIntWith(K a1, @Nonnull LObjByteFunction<? super K, ? extends OptIntTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptInt.toOpt(mapping.apply(a1, get())) : OptInt.empty();
	}

	public default OptLong flatMapToLong(@Nonnull LByteFunction<? extends OptLongTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptLong.toOpt(mapping.apply(get())) : OptLong.empty();
	}

	public default <K> OptLong flatMapToLong_(K a2, @Nonnull LObjByteFunction.LByteObjFunc<? super K, ? extends OptLongTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptLong.toOpt(mapping.applyByteObj(get(), a2)) : OptLong.empty();
	}

	public default <K> OptLong flatMapToLongWith(K a1, @Nonnull LObjByteFunction<? super K, ? extends OptLongTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptLong.toOpt(mapping.apply(a1, get())) : OptLong.empty();
	}

	public default <R> Opt<R> flatMapToObj(@Nonnull LByteFunction<? extends OptTrait<? extends R, ?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? Opt.toOpt(mapping.apply(get())) : Opt.empty();
	}

	public default <R, K> Opt<R> flatMapToObj_(K a2, @Nonnull LObjByteFunction.LByteObjFunc<? super K, ? extends OptTrait<? extends R, ?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? Opt.toOpt(mapping.applyByteObj(get(), a2)) : Opt.empty();
	}

	public default <R, K> Opt<R> flatMapToObjWith(K a1, @Nonnull LObjByteFunction<? super K, ? extends OptTrait<? extends R, ?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? Opt.toOpt(mapping.apply(a1, get())) : Opt.empty();
	}

	// </editor-fold>

	// <editor-fold desc="ifPresent">

	public default SELF ifPresent(@Nonnull LByteConsumer action) {
		Null.nonNullArg(action, "action");
		if (isPresent()) {
			action.accept(get());
		}
		return self();
	}

	public default SELF ifPresent(@Nonnull LByteConsumer action, @Nonnull LAction emptyAction) {
		Null.nonNullArg(action, "action");
		if (isPresent()) {
			action.accept(get());
		} else {
			emptyAction.execute();
		}
		return self();
	}

	public default <K> SELF ifPresent_(K a1, @Nonnull LObjByteConsumer.LByteObjCons<? super K> action) {
		Null.nonNullArg(action, "action");
		if (isPresent()) {
			action.acceptByteObj(get(), a1);
		}
		return self();
	}

	public default <K> SELF ifPresentWith(K a1, @Nonnull LObjByteConsumer<? super K> action) {
		Null.nonNullArg(action, "action");
		if (isPresent()) {
			action.accept(a1, get());
		}
		return self();
	}

	// </editor-fold>

	// <editor-fold desc="orElse">

	default byte orElseThrow() {
		if (isPresent()) {
			return get();
		}

		throw Handling.create(X::noSuchElement);
	}

	default byte orElseThrow(@Nonnull ExF<RuntimeException> fx) {
		if (isPresent()) {
			return get();
		}

		Null.nonNullArg(fx, "fx");
		throw Handling.create(fx);
	}

	default byte orElseThrow(@Nonnull ExMF<RuntimeException> fx, @Nullable String msg) {
		if (isPresent()) {
			return get();
		}

		Null.nonNullArg(fx, "fx");
		throw Handling.create(fx, msg);
	}

	public default byte orElse(@Nullable byte value) {
		return isPresent() ? get() : value;
	}

	public default byte orElseGet(@Nonnull LByteSupplier supplier) {
		Null.nonNullArg(supplier, "supplier");
		return isPresent() ? get() : supplier.getAsByte();
	}

	public default SELF orGet(@Nonnull LSupplier<? extends OptByteTrait<?>> supplier) {
		Null.nonNullArg(supplier, "supplier");
		return isPresent() ? self() : fromOpt(supplier.get());
	}

	public default SELF or(@Nullable byte value) {
		return isPresent() ? self() : value(value);
	}

	public default SELF orOpt(@Nonnull OptByteTrait<?> opt) {
		Null.nonNullArg(opt, "opt");
		return isPresent() ? self() : fromOpt(opt);
	}

	public default <K> byte orElseApply(K a1, @Nonnull LToByteFunction<? super K> supplier) {
		Null.nonNullArg(supplier, "supplier");
		return isPresent() ? get() : supplier.applyAsByte(a1);
	}

	public default <K> SELF orApply(K a1, @Nonnull LFunction<? super K, ? extends OptByteTrait<?>> supplier) {
		Null.nonNullArg(supplier, "supplier");
		return isPresent() ? self() : fromOpt(supplier.apply(a1));
	}

	// </editor-fold>
}
