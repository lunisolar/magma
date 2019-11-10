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

	long get();

	public boolean isPresent();

	default boolean isEmpty() {
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

	public default boolean is(@Nonnull LLongPredicate predicate) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && predicate.test(get());
	}

	public default boolean is(@Nonnull LBiLongPredicate predicate, long a2) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && predicate.test(get(), a2);
	}

	public default boolean is(long a2, @Nonnull LBiLongPredicate predicate) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && predicate.test(get(), a2);
	}

	public default boolean is(@Nonnull LTriLongPredicate predicate, long a2, long a3) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && predicate.test(get(), a2, a3);
	}

	public default boolean is(long a2, long a3, @Nonnull LTriLongPredicate predicate) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && predicate.test(get(), a2, a3);
	}

	public default boolean is2(@Nonnull LLongIntPredicate predicate, int v) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && predicate.test(get(), v);
	}

	public default boolean is2(int v, @Nonnull LLongIntPredicate predicate) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && predicate.test(get(), v);
	}

	public default boolean is2(@Nonnull LBiLongPredicate predicate, long v) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && predicate.test(get(), v);
	}

	public default boolean is2(long v, @Nonnull LBiLongPredicate predicate) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && predicate.test(get(), v);
	}

	public default <V> boolean is2_(@Nonnull LObjLongPredicate.LLongObjPred<? super V> predicate, V v) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && predicate.testLongObj(get(), v);
	}

	public default <V> boolean is2_(V v, @Nonnull LObjLongPredicate.LLongObjPred<? super V> predicate) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && predicate.testLongObj(get(), v);
	}

	// </editor-fold>

	// <editor-fold desc="filtering">

	public default OptLong filter(@Nonnull LLongPredicate predicate) {
		Null.nonNullArg(predicate, "predicate");
		return this.is(predicate) ? OptLong.toOpt(self()) : OptLong.empty();
	}

	public default OptLong filter(@Nonnull LBiLongPredicate predicate, long a2) {
		Null.nonNullArg(predicate, "predicate");
		return this.is(predicate, a2) ? OptLong.toOpt(self()) : OptLong.empty();
	}

	public default OptLong filter(long a2, @Nonnull LBiLongPredicate predicate) {
		Null.nonNullArg(predicate, "predicate");
		return this.is(a2, predicate) ? OptLong.toOpt(self()) : OptLong.empty();
	}

	public default OptLong filter(@Nonnull LTriLongPredicate predicate, long a2, long a3) {
		Null.nonNullArg(predicate, "predicate");
		return this.is(predicate, a2, a3) ? OptLong.toOpt(self()) : OptLong.empty();
	}

	public default OptLong filter(long a2, long a3, @Nonnull LTriLongPredicate predicate) {
		Null.nonNullArg(predicate, "predicate");
		return this.is(a2, a3, predicate) ? OptLong.toOpt(self()) : OptLong.empty();
	}

	public default OptLong filter2(@Nonnull LLongIntPredicate predicate, int v) {
		Null.nonNullArg(predicate, "predicate");
		return this.is2(predicate, v) ? OptLong.toOpt(self()) : OptLong.empty();
	}

	public default OptLong filter2(int v, @Nonnull LLongIntPredicate predicate) {
		Null.nonNullArg(predicate, "predicate");
		return this.is2(v, predicate) ? OptLong.toOpt(self()) : OptLong.empty();
	}

	public default OptLong filter2(@Nonnull LBiLongPredicate predicate, long v) {
		Null.nonNullArg(predicate, "predicate");
		return this.is2(predicate, v) ? OptLong.toOpt(self()) : OptLong.empty();
	}

	public default OptLong filter2(long v, @Nonnull LBiLongPredicate predicate) {
		Null.nonNullArg(predicate, "predicate");
		return this.is2(v, predicate) ? OptLong.toOpt(self()) : OptLong.empty();
	}

	public default <V> OptLong filter2_(@Nonnull LObjLongPredicate.LLongObjPred<? super V> predicate, V v) {
		Null.nonNullArg(predicate, "predicate");
		return this.is2_(predicate, v) ? OptLong.toOpt(self()) : OptLong.empty();
	}

	public default <V> OptLong filter2_(V v, @Nonnull LObjLongPredicate.LLongObjPred<? super V> predicate) {
		Null.nonNullArg(predicate, "predicate");
		return this.is2_(v, predicate) ? OptLong.toOpt(self()) : OptLong.empty();
	}

	// </editor-fold>

	// <editor-fold desc="map">

	public default OptBool mapToBool(@Nonnull LLongPredicate mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptBool.of(mapping.test(get()))) : OptBool.empty();
	}

	public default <K> OptBool mapToBool_(K a2, @Nonnull LObjLongPredicate.LLongObjPred<? super K> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptBool.of(mapping.testLongObj(get(), a2))) : OptBool.empty();
	}

	public default <K> OptBool mapToBoolWith(K a1, @Nonnull LObjLongPredicate<? super K> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptBool.of(mapping.test(a1, get()))) : OptBool.empty();
	}

	public default OptByte mapToByte(@Nonnull LLongToByteFunction mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptByte.of(mapping.applyAsByte(get()))) : OptByte.empty();
	}

	public default OptDbl mapToDbl(@Nonnull LLongToDblFunction mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptDbl.of(mapping.applyAsDbl(get()))) : OptDbl.empty();
	}

	public default OptChar mapToChar(@Nonnull LLongToCharFunction mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptChar.of(mapping.applyAsChar(get()))) : OptChar.empty();
	}

	public default OptSrt mapToSrt(@Nonnull LLongToSrtFunction mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptSrt.of(mapping.applyAsSrt(get()))) : OptSrt.empty();
	}

	public default OptFlt mapToFlt(@Nonnull LLongToFltFunction mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptFlt.of(mapping.applyAsFlt(get()))) : OptFlt.empty();
	}

	public default OptInt mapToInt(@Nonnull LLongToIntFunction mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptInt.of(mapping.applyAsInt(get()))) : OptInt.empty();
	}

	public default OptLong map(@Nonnull LLongUnaryOperator mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptLong.of(mapping.applyAsLong(get()))) : OptLong.empty();
	}

	public default <R> Opt<R> mapToObj(@Nonnull LLongFunction<? extends R> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (Opt.of(mapping.apply(get()))) : Opt.empty();
	}

	public default <R, K> Opt<R> mapToObj_(K a2, @Nonnull LObjLongFunction.LLongObjFunc<? super K, ? extends R> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (Opt.of(mapping.applyLongObj(get(), a2))) : Opt.empty();
	}

	public default <R, K> Opt<R> mapToObjWith(K a1, @Nonnull LObjLongFunction<? super K, ? extends R> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (Opt.of(mapping.apply(a1, get()))) : Opt.empty();
	}

	// </editor-fold>

	// <editor-fold desc="flatMap">

	public default OptBool flatMapToBool(@Nonnull LLongFunction<? extends OptBoolTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptBool.toOpt(mapping.apply(get())) : OptBool.empty();
	}

	public default <K> OptBool flatMapToBool_(K a2, @Nonnull LObjLongFunction.LLongObjFunc<? super K, ? extends OptBoolTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptBool.toOpt(mapping.applyLongObj(get(), a2)) : OptBool.empty();
	}

	public default <K> OptBool flatMapToBoolWith(K a1, @Nonnull LObjLongFunction<? super K, ? extends OptBoolTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptBool.toOpt(mapping.apply(a1, get())) : OptBool.empty();
	}

	public default OptByte flatMapToByte(@Nonnull LLongFunction<? extends OptByteTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptByte.toOpt(mapping.apply(get())) : OptByte.empty();
	}

	public default <K> OptByte flatMapToByte_(K a2, @Nonnull LObjLongFunction.LLongObjFunc<? super K, ? extends OptByteTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptByte.toOpt(mapping.applyLongObj(get(), a2)) : OptByte.empty();
	}

	public default <K> OptByte flatMapToByteWith(K a1, @Nonnull LObjLongFunction<? super K, ? extends OptByteTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptByte.toOpt(mapping.apply(a1, get())) : OptByte.empty();
	}

	public default OptDbl flatMapToDbl(@Nonnull LLongFunction<? extends OptDblTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptDbl.toOpt(mapping.apply(get())) : OptDbl.empty();
	}

	public default <K> OptDbl flatMapToDbl_(K a2, @Nonnull LObjLongFunction.LLongObjFunc<? super K, ? extends OptDblTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptDbl.toOpt(mapping.applyLongObj(get(), a2)) : OptDbl.empty();
	}

	public default <K> OptDbl flatMapToDblWith(K a1, @Nonnull LObjLongFunction<? super K, ? extends OptDblTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptDbl.toOpt(mapping.apply(a1, get())) : OptDbl.empty();
	}

	public default OptChar flatMapToChar(@Nonnull LLongFunction<? extends OptCharTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptChar.toOpt(mapping.apply(get())) : OptChar.empty();
	}

	public default <K> OptChar flatMapToChar_(K a2, @Nonnull LObjLongFunction.LLongObjFunc<? super K, ? extends OptCharTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptChar.toOpt(mapping.applyLongObj(get(), a2)) : OptChar.empty();
	}

	public default <K> OptChar flatMapToCharWith(K a1, @Nonnull LObjLongFunction<? super K, ? extends OptCharTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptChar.toOpt(mapping.apply(a1, get())) : OptChar.empty();
	}

	public default OptSrt flatMapToSrt(@Nonnull LLongFunction<? extends OptSrtTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptSrt.toOpt(mapping.apply(get())) : OptSrt.empty();
	}

	public default <K> OptSrt flatMapToSrt_(K a2, @Nonnull LObjLongFunction.LLongObjFunc<? super K, ? extends OptSrtTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptSrt.toOpt(mapping.applyLongObj(get(), a2)) : OptSrt.empty();
	}

	public default <K> OptSrt flatMapToSrtWith(K a1, @Nonnull LObjLongFunction<? super K, ? extends OptSrtTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptSrt.toOpt(mapping.apply(a1, get())) : OptSrt.empty();
	}

	public default OptFlt flatMapToFlt(@Nonnull LLongFunction<? extends OptFltTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptFlt.toOpt(mapping.apply(get())) : OptFlt.empty();
	}

	public default <K> OptFlt flatMapToFlt_(K a2, @Nonnull LObjLongFunction.LLongObjFunc<? super K, ? extends OptFltTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptFlt.toOpt(mapping.applyLongObj(get(), a2)) : OptFlt.empty();
	}

	public default <K> OptFlt flatMapToFltWith(K a1, @Nonnull LObjLongFunction<? super K, ? extends OptFltTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptFlt.toOpt(mapping.apply(a1, get())) : OptFlt.empty();
	}

	public default OptInt flatMapToInt(@Nonnull LLongFunction<? extends OptIntTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptInt.toOpt(mapping.apply(get())) : OptInt.empty();
	}

	public default <K> OptInt flatMapToInt_(K a2, @Nonnull LObjLongFunction.LLongObjFunc<? super K, ? extends OptIntTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptInt.toOpt(mapping.applyLongObj(get(), a2)) : OptInt.empty();
	}

	public default <K> OptInt flatMapToIntWith(K a1, @Nonnull LObjLongFunction<? super K, ? extends OptIntTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptInt.toOpt(mapping.apply(a1, get())) : OptInt.empty();
	}

	public default OptLong flatMap(@Nonnull LLongFunction<? extends OptLongTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptLong.toOpt(mapping.apply(get())) : OptLong.empty();
	}

	public default <K> OptLong flatMap_(K a2, @Nonnull LObjLongFunction.LLongObjFunc<? super K, ? extends OptLongTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptLong.toOpt(mapping.applyLongObj(get(), a2)) : OptLong.empty();
	}

	public default <K> OptLong flatMapWith(K a1, @Nonnull LObjLongFunction<? super K, ? extends OptLongTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptLong.toOpt(mapping.apply(a1, get())) : OptLong.empty();
	}

	public default <R> Opt<R> flatMapToObj(@Nonnull LLongFunction<? extends OptTrait<? extends R, ?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? Opt.toOpt(mapping.apply(get())) : Opt.empty();
	}

	public default <R, K> Opt<R> flatMapToObj_(K a2, @Nonnull LObjLongFunction.LLongObjFunc<? super K, ? extends OptTrait<? extends R, ?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? Opt.toOpt(mapping.applyLongObj(get(), a2)) : Opt.empty();
	}

	public default <R, K> Opt<R> flatMapToObjWith(K a1, @Nonnull LObjLongFunction<? super K, ? extends OptTrait<? extends R, ?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? Opt.toOpt(mapping.apply(a1, get())) : Opt.empty();
	}

	// </editor-fold>

	// <editor-fold desc="ifPresent">

	public default SELF ifPresent(@Nonnull LLongConsumer action) {
		Null.nonNullArg(action, "action");
		if (isPresent()) {
			action.accept(get());
		}
		return self();
	}

	public default SELF ifPresent(@Nonnull LLongConsumer action, @Nonnull LAction emptyAction) {
		Null.nonNullArg(action, "action");
		if (isPresent()) {
			action.accept(get());
		} else {
			emptyAction.execute();
		}
		return self();
	}

	public default <K> SELF ifPresent_(K a1, @Nonnull LObjLongConsumer.LLongObjCons<? super K> action) {
		Null.nonNullArg(action, "action");
		if (isPresent()) {
			action.acceptLongObj(get(), a1);
		}
		return self();
	}

	public default <K> SELF ifPresentWith(K a1, @Nonnull LObjLongConsumer<? super K> action) {
		Null.nonNullArg(action, "action");
		if (isPresent()) {
			action.accept(a1, get());
		}
		return self();
	}

	// </editor-fold>

	// <editor-fold desc="orElse">

	default long orElseThrow() {
		if (isPresent()) {
			return get();
		}

		throw Handling.create(X::noSuchElement);
	}

	default long orElseThrow(@Nonnull ExF<RuntimeException> fx) {
		if (isPresent()) {
			return get();
		}

		Null.nonNullArg(fx, "fx");
		throw Handling.create(fx);
	}

	default long orElseThrow(@Nonnull ExMF<RuntimeException> fx, @Nullable String msg) {
		if (isPresent()) {
			return get();
		}

		Null.nonNullArg(fx, "fx");
		throw Handling.create(fx, msg);
	}

	public default long orElse(@Nullable long value) {
		return isPresent() ? get() : value;
	}

	public default long orElseGet(@Nonnull LLongSupplier supplier) {
		Null.nonNullArg(supplier, "supplier");
		return isPresent() ? get() : supplier.getAsLong();
	}

	public default OptLong orGet(@Nonnull LSupplier<? extends OptLongTrait<?>> supplier) {
		Null.nonNullArg(supplier, "supplier");
		return isPresent() ? OptLong.toOpt(self()) : OptLong.toOpt(supplier.get());
	}

	public default OptLong or(@Nullable long value) {
		return isPresent() ? OptLong.toOpt(self()) : OptLong.of(value);
	}

	public default OptLong orOpt(@Nonnull OptLongTrait<?> opt) {
		Null.nonNullArg(opt, "opt");
		return isPresent() ? OptLong.toOpt(self()) : OptLong.toOpt(opt);
	}

	public default <K> long orElseApply(K a1, @Nonnull LToLongFunction<? super K> supplier) {
		Null.nonNullArg(supplier, "supplier");
		return isPresent() ? get() : supplier.applyAsLong(a1);
	}

	public default <K> OptLong orApply(K a1, @Nonnull LFunction<? super K, ? extends OptLongTrait<?>> supplier) {
		Null.nonNullArg(supplier, "supplier");
		return isPresent() ? OptLong.toOpt(self()) : OptLong.toOpt(supplier.apply(a1));
	}

	// </editor-fold>
}
