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
public interface OptSrtTrait<SELF extends OptSrtTrait<SELF>> extends Fluent<SELF>, aValue<aShort>, CheckSrtTrait<SELF> {

	short get();

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

	public default boolean is(@Nonnull LSrtPredicate predicate) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && predicate.test(get());
	}

	public default boolean is(@Nonnull LBiSrtPredicate predicate, short a2) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && predicate.test(get(), a2);
	}

	public default boolean is(short a2, @Nonnull LBiSrtPredicate predicate) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && predicate.test(get(), a2);
	}

	public default boolean is(@Nonnull LTriSrtPredicate predicate, short a2, short a3) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && predicate.test(get(), a2, a3);
	}

	public default boolean is(short a2, short a3, @Nonnull LTriSrtPredicate predicate) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && predicate.test(get(), a2, a3);
	}

	public default boolean is2(@Nonnull LBiSrtPredicate predicate, short v) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && predicate.test(get(), v);
	}

	public default boolean is2(short v, @Nonnull LBiSrtPredicate predicate) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && predicate.test(get(), v);
	}

	public default boolean is2(@Nonnull LSrtIntPredicate predicate, int v) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && predicate.test(get(), v);
	}

	public default boolean is2(int v, @Nonnull LSrtIntPredicate predicate) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && predicate.test(get(), v);
	}

	public default <V> boolean is2_(@Nonnull LObjSrtPredicate.LSrtObjPred<? super V> predicate, V v) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && predicate.testSrtObj(get(), v);
	}

	public default <V> boolean is2_(V v, @Nonnull LObjSrtPredicate.LSrtObjPred<? super V> predicate) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && predicate.testSrtObj(get(), v);
	}

	// </editor-fold>

	// <editor-fold desc="filtering">

	public default OptSrt filter(@Nonnull LSrtPredicate predicate) {
		Null.nonNullArg(predicate, "predicate");
		return this.is(predicate) ? OptSrt.toOpt(self()) : OptSrt.empty();
	}

	public default OptSrt filter(@Nonnull LBiSrtPredicate predicate, short a2) {
		Null.nonNullArg(predicate, "predicate");
		return this.is(predicate, a2) ? OptSrt.toOpt(self()) : OptSrt.empty();
	}

	public default OptSrt filter(short a2, @Nonnull LBiSrtPredicate predicate) {
		Null.nonNullArg(predicate, "predicate");
		return this.is(a2, predicate) ? OptSrt.toOpt(self()) : OptSrt.empty();
	}

	public default OptSrt filter(@Nonnull LTriSrtPredicate predicate, short a2, short a3) {
		Null.nonNullArg(predicate, "predicate");
		return this.is(predicate, a2, a3) ? OptSrt.toOpt(self()) : OptSrt.empty();
	}

	public default OptSrt filter(short a2, short a3, @Nonnull LTriSrtPredicate predicate) {
		Null.nonNullArg(predicate, "predicate");
		return this.is(a2, a3, predicate) ? OptSrt.toOpt(self()) : OptSrt.empty();
	}

	public default OptSrt filter2(@Nonnull LBiSrtPredicate predicate, short v) {
		Null.nonNullArg(predicate, "predicate");
		return this.is2(predicate, v) ? OptSrt.toOpt(self()) : OptSrt.empty();
	}

	public default OptSrt filter2(short v, @Nonnull LBiSrtPredicate predicate) {
		Null.nonNullArg(predicate, "predicate");
		return this.is2(v, predicate) ? OptSrt.toOpt(self()) : OptSrt.empty();
	}

	public default OptSrt filter2(@Nonnull LSrtIntPredicate predicate, int v) {
		Null.nonNullArg(predicate, "predicate");
		return this.is2(predicate, v) ? OptSrt.toOpt(self()) : OptSrt.empty();
	}

	public default OptSrt filter2(int v, @Nonnull LSrtIntPredicate predicate) {
		Null.nonNullArg(predicate, "predicate");
		return this.is2(v, predicate) ? OptSrt.toOpt(self()) : OptSrt.empty();
	}

	public default <V> OptSrt filter2_(@Nonnull LObjSrtPredicate.LSrtObjPred<? super V> predicate, V v) {
		Null.nonNullArg(predicate, "predicate");
		return this.is2_(predicate, v) ? OptSrt.toOpt(self()) : OptSrt.empty();
	}

	public default <V> OptSrt filter2_(V v, @Nonnull LObjSrtPredicate.LSrtObjPred<? super V> predicate) {
		Null.nonNullArg(predicate, "predicate");
		return this.is2_(v, predicate) ? OptSrt.toOpt(self()) : OptSrt.empty();
	}

	// </editor-fold>

	// <editor-fold desc="map">

	public default OptBool mapToBool(@Nonnull LSrtPredicate mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptBool.of(mapping.test(get()))) : OptBool.empty();
	}

	public default <K> OptBool mapToBool_(K a2, @Nonnull LObjSrtPredicate.LSrtObjPred<? super K> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptBool.of(mapping.testSrtObj(get(), a2))) : OptBool.empty();
	}

	public default <K> OptBool mapToBoolWith(K a1, @Nonnull LObjSrtPredicate<? super K> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptBool.of(mapping.test(a1, get()))) : OptBool.empty();
	}

	public default OptByte mapToByte(@Nonnull LSrtToByteFunction mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptByte.of(mapping.applyAsByte(get()))) : OptByte.empty();
	}

	public default OptDbl mapToDbl(@Nonnull LSrtToDblFunction mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptDbl.of(mapping.applyAsDbl(get()))) : OptDbl.empty();
	}

	public default OptChar mapToChar(@Nonnull LSrtToCharFunction mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptChar.of(mapping.applyAsChar(get()))) : OptChar.empty();
	}

	public default OptSrt map(@Nonnull LSrtUnaryOperator mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptSrt.of(mapping.applyAsSrt(get()))) : OptSrt.empty();
	}

	public default OptFlt mapToFlt(@Nonnull LSrtToFltFunction mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptFlt.of(mapping.applyAsFlt(get()))) : OptFlt.empty();
	}

	public default OptInt mapToInt(@Nonnull LSrtToIntFunction mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptInt.of(mapping.applyAsInt(get()))) : OptInt.empty();
	}

	public default OptLong mapToLong(@Nonnull LSrtToLongFunction mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptLong.of(mapping.applyAsLong(get()))) : OptLong.empty();
	}

	public default <R> Opt<R> mapToObj(@Nonnull LSrtFunction<? extends R> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (Opt.of(mapping.apply(get()))) : Opt.empty();
	}

	public default <R, K> Opt<R> mapToObj_(K a2, @Nonnull LObjSrtFunction.LSrtObjFunc<? super K, ? extends R> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (Opt.of(mapping.applySrtObj(get(), a2))) : Opt.empty();
	}

	public default <R, K> Opt<R> mapToObjWith(K a1, @Nonnull LObjSrtFunction<? super K, ? extends R> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (Opt.of(mapping.apply(a1, get()))) : Opt.empty();
	}

	// </editor-fold>

	// <editor-fold desc="flatMap">

	public default OptBool flatMapToBool(@Nonnull LSrtFunction<? extends OptBoolTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptBool.toOpt(mapping.apply(get())) : OptBool.empty();
	}

	public default <K> OptBool flatMapToBool_(K a2, @Nonnull LObjSrtFunction.LSrtObjFunc<? super K, ? extends OptBoolTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptBool.toOpt(mapping.applySrtObj(get(), a2)) : OptBool.empty();
	}

	public default <K> OptBool flatMapToBoolWith(K a1, @Nonnull LObjSrtFunction<? super K, ? extends OptBoolTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptBool.toOpt(mapping.apply(a1, get())) : OptBool.empty();
	}

	public default OptByte flatMapToByte(@Nonnull LSrtFunction<? extends OptByteTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptByte.toOpt(mapping.apply(get())) : OptByte.empty();
	}

	public default <K> OptByte flatMapToByte_(K a2, @Nonnull LObjSrtFunction.LSrtObjFunc<? super K, ? extends OptByteTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptByte.toOpt(mapping.applySrtObj(get(), a2)) : OptByte.empty();
	}

	public default <K> OptByte flatMapToByteWith(K a1, @Nonnull LObjSrtFunction<? super K, ? extends OptByteTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptByte.toOpt(mapping.apply(a1, get())) : OptByte.empty();
	}

	public default OptDbl flatMapToDbl(@Nonnull LSrtFunction<? extends OptDblTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptDbl.toOpt(mapping.apply(get())) : OptDbl.empty();
	}

	public default <K> OptDbl flatMapToDbl_(K a2, @Nonnull LObjSrtFunction.LSrtObjFunc<? super K, ? extends OptDblTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptDbl.toOpt(mapping.applySrtObj(get(), a2)) : OptDbl.empty();
	}

	public default <K> OptDbl flatMapToDblWith(K a1, @Nonnull LObjSrtFunction<? super K, ? extends OptDblTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptDbl.toOpt(mapping.apply(a1, get())) : OptDbl.empty();
	}

	public default OptChar flatMapToChar(@Nonnull LSrtFunction<? extends OptCharTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptChar.toOpt(mapping.apply(get())) : OptChar.empty();
	}

	public default <K> OptChar flatMapToChar_(K a2, @Nonnull LObjSrtFunction.LSrtObjFunc<? super K, ? extends OptCharTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptChar.toOpt(mapping.applySrtObj(get(), a2)) : OptChar.empty();
	}

	public default <K> OptChar flatMapToCharWith(K a1, @Nonnull LObjSrtFunction<? super K, ? extends OptCharTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptChar.toOpt(mapping.apply(a1, get())) : OptChar.empty();
	}

	public default OptSrt flatMap(@Nonnull LSrtFunction<? extends OptSrtTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptSrt.toOpt(mapping.apply(get())) : OptSrt.empty();
	}

	public default <K> OptSrt flatMap_(K a2, @Nonnull LObjSrtFunction.LSrtObjFunc<? super K, ? extends OptSrtTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptSrt.toOpt(mapping.applySrtObj(get(), a2)) : OptSrt.empty();
	}

	public default <K> OptSrt flatMapWith(K a1, @Nonnull LObjSrtFunction<? super K, ? extends OptSrtTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptSrt.toOpt(mapping.apply(a1, get())) : OptSrt.empty();
	}

	public default OptFlt flatMapToFlt(@Nonnull LSrtFunction<? extends OptFltTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptFlt.toOpt(mapping.apply(get())) : OptFlt.empty();
	}

	public default <K> OptFlt flatMapToFlt_(K a2, @Nonnull LObjSrtFunction.LSrtObjFunc<? super K, ? extends OptFltTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptFlt.toOpt(mapping.applySrtObj(get(), a2)) : OptFlt.empty();
	}

	public default <K> OptFlt flatMapToFltWith(K a1, @Nonnull LObjSrtFunction<? super K, ? extends OptFltTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptFlt.toOpt(mapping.apply(a1, get())) : OptFlt.empty();
	}

	public default OptInt flatMapToInt(@Nonnull LSrtFunction<? extends OptIntTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptInt.toOpt(mapping.apply(get())) : OptInt.empty();
	}

	public default <K> OptInt flatMapToInt_(K a2, @Nonnull LObjSrtFunction.LSrtObjFunc<? super K, ? extends OptIntTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptInt.toOpt(mapping.applySrtObj(get(), a2)) : OptInt.empty();
	}

	public default <K> OptInt flatMapToIntWith(K a1, @Nonnull LObjSrtFunction<? super K, ? extends OptIntTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptInt.toOpt(mapping.apply(a1, get())) : OptInt.empty();
	}

	public default OptLong flatMapToLong(@Nonnull LSrtFunction<? extends OptLongTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptLong.toOpt(mapping.apply(get())) : OptLong.empty();
	}

	public default <K> OptLong flatMapToLong_(K a2, @Nonnull LObjSrtFunction.LSrtObjFunc<? super K, ? extends OptLongTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptLong.toOpt(mapping.applySrtObj(get(), a2)) : OptLong.empty();
	}

	public default <K> OptLong flatMapToLongWith(K a1, @Nonnull LObjSrtFunction<? super K, ? extends OptLongTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptLong.toOpt(mapping.apply(a1, get())) : OptLong.empty();
	}

	public default <R> Opt<R> flatMapToObj(@Nonnull LSrtFunction<? extends OptTrait<? extends R, ?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? Opt.toOpt(mapping.apply(get())) : Opt.empty();
	}

	public default <R, K> Opt<R> flatMapToObj_(K a2, @Nonnull LObjSrtFunction.LSrtObjFunc<? super K, ? extends OptTrait<? extends R, ?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? Opt.toOpt(mapping.applySrtObj(get(), a2)) : Opt.empty();
	}

	public default <R, K> Opt<R> flatMapToObjWith(K a1, @Nonnull LObjSrtFunction<? super K, ? extends OptTrait<? extends R, ?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? Opt.toOpt(mapping.apply(a1, get())) : Opt.empty();
	}

	// </editor-fold>

	// <editor-fold desc="ifPresent">

	public default SELF ifPresent(@Nonnull LSrtConsumer action) {
		Null.nonNullArg(action, "action");
		if (isPresent()) {
			action.accept(get());
		}
		return self();
	}

	public default SELF ifPresent(@Nonnull LSrtConsumer action, @Nonnull LAction emptyAction) {
		Null.nonNullArg(action, "action");
		if (isPresent()) {
			action.accept(get());
		} else {
			emptyAction.execute();
		}
		return self();
	}

	public default <K> SELF ifPresent_(K a1, @Nonnull LObjSrtConsumer.LSrtObjCons<? super K> action) {
		Null.nonNullArg(action, "action");
		if (isPresent()) {
			action.acceptSrtObj(get(), a1);
		}
		return self();
	}

	public default <K> SELF ifPresentWith(K a1, @Nonnull LObjSrtConsumer<? super K> action) {
		Null.nonNullArg(action, "action");
		if (isPresent()) {
			action.accept(a1, get());
		}
		return self();
	}

	// </editor-fold>

	// <editor-fold desc="orElse">

	default short orElseThrow() {
		if (isPresent()) {
			return get();
		}

		throw Handling.create(X::noSuchElement);
	}

	default short orElseThrow(@Nonnull ExF<RuntimeException> fx) {
		if (isPresent()) {
			return get();
		}

		Null.nonNullArg(fx, "fx");
		throw Handling.create(fx);
	}

	default short orElseThrow(@Nonnull ExMF<RuntimeException> fx, @Nullable String msg) {
		if (isPresent()) {
			return get();
		}

		Null.nonNullArg(fx, "fx");
		throw Handling.create(fx, msg);
	}

	public default short orElse(@Nullable short value) {
		return isPresent() ? get() : value;
	}

	public default short orElseGet(@Nonnull LSrtSupplier supplier) {
		Null.nonNullArg(supplier, "supplier");
		return isPresent() ? get() : supplier.getAsSrt();
	}

	public default OptSrt orGet(@Nonnull LSupplier<? extends OptSrtTrait<?>> supplier) {
		Null.nonNullArg(supplier, "supplier");
		return isPresent() ? OptSrt.toOpt(self()) : OptSrt.toOpt(supplier.get());
	}

	public default OptSrt or(@Nullable short value) {
		return isPresent() ? OptSrt.toOpt(self()) : OptSrt.of(value);
	}

	public default OptSrt orOpt(@Nonnull OptSrtTrait<?> opt) {
		Null.nonNullArg(opt, "opt");
		return isPresent() ? OptSrt.toOpt(self()) : OptSrt.toOpt(opt);
	}

	public default <K> short orElseApply(K a1, @Nonnull LToSrtFunction<? super K> supplier) {
		Null.nonNullArg(supplier, "supplier");
		return isPresent() ? get() : supplier.applyAsSrt(a1);
	}

	public default <K> OptSrt orApply(K a1, @Nonnull LFunction<? super K, ? extends OptSrtTrait<?>> supplier) {
		Null.nonNullArg(supplier, "supplier");
		return isPresent() ? OptSrt.toOpt(self()) : OptSrt.toOpt(supplier.apply(a1));
	}

	// </editor-fold>
}
