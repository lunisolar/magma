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
public interface OptDblTrait<SELF extends OptDblTrait<SELF>> extends Fluent<SELF>, aValue<aDouble>, CheckDblTrait<SELF>, FilterDblTrait<SELF>, IsDblTrait<SELF> {

	// <editor-fold desc="forcing ValueTrait re-implementation">

	@Nonnull
	SELF value(double value);
	@Nonnull
	SELF voidValue();

	// </editor-fold>

	double get();

	default @Nullable Double nullable() {
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

	default OptionalDouble toOpt() {
		if (isPresent()) {
			return OptionalDouble.of(get());
		} else {
			return OptionalDouble.empty();
		}
	}

	// <editor-fold desc="isPresent() dependant boolean terminals">

	@Override
	default boolean is(@Nonnull LDblPredicate predicate) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && predicate.test(get());
	}

	@Override
	default boolean is(double a2, @Nonnull LBiDblPredicate predicate) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && predicate.test(get(), a2);
	}

	@Override
	default boolean is(double a2, double a3, @Nonnull LTriDblPredicate predicate) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && predicate.test(get(), a2, a3);
	}

	@Override
	default boolean is2(int v, @Nonnull LDblIntPredicate predicate) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && predicate.test(get(), v);
	}

	@Override
	default <V> boolean is2_(V v, @Nonnull LObjDblPredicate.LDblObjPred<? super V> predicate) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && predicate.testDblObj(get(), v);
	}

	// </editor-fold>

	// <editor-fold desc="filtering">

	@Override
	default SELF filter(@Nonnull LDblPredicate predicate) {
		Null.nonNullArg(predicate, "predicate");
		return this.is(predicate) ? self() : voidValue();
	}

	@Override
	default SELF filter(double a2, @Nonnull LBiDblPredicate predicate) {
		Null.nonNullArg(predicate, "predicate");
		return this.is(a2, predicate) ? self() : voidValue();
	}

	@Override
	default SELF filter(double a2, double a3, @Nonnull LTriDblPredicate predicate) {
		Null.nonNullArg(predicate, "predicate");
		return this.is(a2, a3, predicate) ? self() : voidValue();
	}

	@Override
	default SELF filter2(int v, @Nonnull LDblIntPredicate predicate) {
		Null.nonNullArg(predicate, "predicate");
		return this.is2(v, predicate) ? self() : voidValue();
	}

	@Override
	default <V> SELF filter2_(V v, @Nonnull LObjDblPredicate.LDblObjPred<? super V> predicate) {
		Null.nonNullArg(predicate, "predicate");
		return this.is2_(v, predicate) ? self() : voidValue();
	}

	// </editor-fold>

	// <editor-fold desc="map">

	default OptBool mapToBool(@Nonnull LDblPredicate mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptBool.of(mapping.test(get()))) : OptBool.empty();
	}

	default <K> OptBool mapToBool_(K a2, @Nonnull LObjDblPredicate.LDblObjPred<? super K> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptBool.of(mapping.testDblObj(get(), a2))) : OptBool.empty();
	}

	default <K> OptBool mapToBoolWith(K a1, @Nonnull LObjDblPredicate<? super K> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptBool.of(mapping.test(a1, get()))) : OptBool.empty();
	}

	default OptByte mapToByte(@Nonnull LDblToByteFunction mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptByte.of(mapping.applyAsByte(get()))) : OptByte.empty();
	}

	default OptDbl map(@Nonnull LDblUnaryOperator mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptDbl.of(mapping.applyAsDbl(get()))) : OptDbl.empty();
	}

	default SELF perform(@Nonnull LDblUnaryOperator mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? value(mapping.applyAsDbl(get())) : voidValue();
	}

	default SELF performWith(double a1, @Nonnull LDblBinaryOperator mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? value(mapping.applyAsDbl(a1, get())) : voidValue();
	}

	default OptChar mapToChar(@Nonnull LDblToCharFunction mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptChar.of(mapping.applyAsChar(get()))) : OptChar.empty();
	}

	default OptSrt mapToSrt(@Nonnull LDblToSrtFunction mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptSrt.of(mapping.applyAsSrt(get()))) : OptSrt.empty();
	}

	default OptFlt mapToFlt(@Nonnull LDblToFltFunction mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptFlt.of(mapping.applyAsFlt(get()))) : OptFlt.empty();
	}

	default OptInt mapToInt(@Nonnull LDblToIntFunction mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptInt.of(mapping.applyAsInt(get()))) : OptInt.empty();
	}

	default OptLong mapToLong(@Nonnull LDblToLongFunction mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptLong.of(mapping.applyAsLong(get()))) : OptLong.empty();
	}

	default <R> Opt<R> mapToObj(@Nonnull LDblFunction<? extends R> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (Opt.of(mapping.apply(get()))) : Opt.empty();
	}

	default <R, K> Opt<R> mapToObj_(K a2, @Nonnull LObjDblFunction.LDblObjFunc<? super K, ? extends R> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (Opt.of(mapping.applyDblObj(get(), a2))) : Opt.empty();
	}

	default <R, K> Opt<R> mapToObjWith(K a1, @Nonnull LObjDblFunction<? super K, ? extends R> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (Opt.of(mapping.apply(a1, get()))) : Opt.empty();
	}

	// </editor-fold>

	// <editor-fold desc="flatMap">

	default OptBool flatMapToBool(@Nonnull LDblFunction<? extends OptBoolTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptBool.from(mapping.apply(get())) : OptBool.empty();
	}

	default <K> OptBool flatMapToBool_(K a2, @Nonnull LObjDblFunction.LDblObjFunc<? super K, ? extends OptBoolTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptBool.from(mapping.applyDblObj(get(), a2)) : OptBool.empty();
	}

	default <K> OptBool flatMapToBoolWith(K a1, @Nonnull LObjDblFunction<? super K, ? extends OptBoolTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptBool.from(mapping.apply(a1, get())) : OptBool.empty();
	}

	default OptByte flatMapToByte(@Nonnull LDblFunction<? extends OptByteTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptByte.from(mapping.apply(get())) : OptByte.empty();
	}

	default <K> OptByte flatMapToByte_(K a2, @Nonnull LObjDblFunction.LDblObjFunc<? super K, ? extends OptByteTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptByte.from(mapping.applyDblObj(get(), a2)) : OptByte.empty();
	}

	default <K> OptByte flatMapToByteWith(K a1, @Nonnull LObjDblFunction<? super K, ? extends OptByteTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptByte.from(mapping.apply(a1, get())) : OptByte.empty();
	}

	default OptDbl flatMap(@Nonnull LDblFunction<? extends OptDblTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptDbl.from(mapping.apply(get())) : OptDbl.empty();
	}

	default SELF perform(@Nonnull LDblFunction<? extends OptDblTrait<? extends SELF>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? valueFrom(mapping.apply(get())) : voidValue();
	}

	default <K> OptDbl flatMap_(K a2, @Nonnull LObjDblFunction.LDblObjFunc<? super K, ? extends OptDblTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptDbl.from(mapping.applyDblObj(get(), a2)) : OptDbl.empty();
	}

	default <K> OptDbl flatMapWith(K a1, @Nonnull LObjDblFunction<? super K, ? extends OptDblTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptDbl.from(mapping.apply(a1, get())) : OptDbl.empty();
	}

	default SELF flatPerformWith(double a1, @Nonnull LBiDblFunction<? extends OptDblTrait<? extends SELF>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? valueFrom(mapping.apply(a1, get())) : voidValue();
	}

	default OptChar flatMapToChar(@Nonnull LDblFunction<? extends OptCharTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptChar.from(mapping.apply(get())) : OptChar.empty();
	}

	default <K> OptChar flatMapToChar_(K a2, @Nonnull LObjDblFunction.LDblObjFunc<? super K, ? extends OptCharTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptChar.from(mapping.applyDblObj(get(), a2)) : OptChar.empty();
	}

	default <K> OptChar flatMapToCharWith(K a1, @Nonnull LObjDblFunction<? super K, ? extends OptCharTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptChar.from(mapping.apply(a1, get())) : OptChar.empty();
	}

	default OptSrt flatMapToSrt(@Nonnull LDblFunction<? extends OptSrtTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptSrt.from(mapping.apply(get())) : OptSrt.empty();
	}

	default <K> OptSrt flatMapToSrt_(K a2, @Nonnull LObjDblFunction.LDblObjFunc<? super K, ? extends OptSrtTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptSrt.from(mapping.applyDblObj(get(), a2)) : OptSrt.empty();
	}

	default <K> OptSrt flatMapToSrtWith(K a1, @Nonnull LObjDblFunction<? super K, ? extends OptSrtTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptSrt.from(mapping.apply(a1, get())) : OptSrt.empty();
	}

	default OptFlt flatMapToFlt(@Nonnull LDblFunction<? extends OptFltTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptFlt.from(mapping.apply(get())) : OptFlt.empty();
	}

	default <K> OptFlt flatMapToFlt_(K a2, @Nonnull LObjDblFunction.LDblObjFunc<? super K, ? extends OptFltTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptFlt.from(mapping.applyDblObj(get(), a2)) : OptFlt.empty();
	}

	default <K> OptFlt flatMapToFltWith(K a1, @Nonnull LObjDblFunction<? super K, ? extends OptFltTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptFlt.from(mapping.apply(a1, get())) : OptFlt.empty();
	}

	default OptInt flatMapToInt(@Nonnull LDblFunction<? extends OptIntTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptInt.from(mapping.apply(get())) : OptInt.empty();
	}

	default <K> OptInt flatMapToInt_(K a2, @Nonnull LObjDblFunction.LDblObjFunc<? super K, ? extends OptIntTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptInt.from(mapping.applyDblObj(get(), a2)) : OptInt.empty();
	}

	default <K> OptInt flatMapToIntWith(K a1, @Nonnull LObjDblFunction<? super K, ? extends OptIntTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptInt.from(mapping.apply(a1, get())) : OptInt.empty();
	}

	default OptLong flatMapToLong(@Nonnull LDblFunction<? extends OptLongTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptLong.from(mapping.apply(get())) : OptLong.empty();
	}

	default <K> OptLong flatMapToLong_(K a2, @Nonnull LObjDblFunction.LDblObjFunc<? super K, ? extends OptLongTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptLong.from(mapping.applyDblObj(get(), a2)) : OptLong.empty();
	}

	default <K> OptLong flatMapToLongWith(K a1, @Nonnull LObjDblFunction<? super K, ? extends OptLongTrait<?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? OptLong.from(mapping.apply(a1, get())) : OptLong.empty();
	}

	default <R> Opt<R> flatMapToObj(@Nonnull LDblFunction<? extends OptTrait<? extends R, ?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? Opt.from(mapping.apply(get())) : Opt.empty();
	}

	default <R, K> Opt<R> flatMapToObj_(K a2, @Nonnull LObjDblFunction.LDblObjFunc<? super K, ? extends OptTrait<? extends R, ?>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? Opt.from(mapping.applyDblObj(get(), a2)) : Opt.empty();
	}

	default <R, K> Opt<R> flatMapToObjWith(K a1, @Nonnull LObjDblFunction<? super K, ? extends OptTrait<? extends R, ?>> mapping) {
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

	default SELF doIf(@Nonnull LDblPredicate predicate, LConsumer<SELF> action) {
		Null.nonNullArg(predicate, "predicate");
		if (is(predicate))
			action.accept(self());
		return self();
	}

	default SELF doIfNot(@Nonnull LDblPredicate predicate, LConsumer<SELF> action) {
		Null.nonNullArg(predicate, "predicate");
		if (!is(predicate))
			action.accept(self());
		return self();
	}

	default SELF doIf(@Nonnull LBiDblPredicate predicate, double a2, LConsumer<SELF> action) {
		Null.nonNullArg(predicate, "predicate");
		if (is(predicate, a2))
			action.accept(self());
		return self();
	}

	default SELF doIfNot(@Nonnull LBiDblPredicate predicate, double a2, LConsumer<SELF> action) {
		Null.nonNullArg(predicate, "predicate");
		if (!is(predicate, a2))
			action.accept(self());
		return self();
	}

	default SELF doIf(double a2, @Nonnull LBiDblPredicate predicate, LConsumer<SELF> action) {
		Null.nonNullArg(predicate, "predicate");
		if (is(a2, predicate))
			action.accept(self());
		return self();
	}

	default SELF doIfNot(double a2, @Nonnull LBiDblPredicate predicate, LConsumer<SELF> action) {
		Null.nonNullArg(predicate, "predicate");
		if (!is(a2, predicate))
			action.accept(self());
		return self();
	}

	default SELF doIf(@Nonnull LTriDblPredicate predicate, double a2, double a3, LConsumer<SELF> action) {
		Null.nonNullArg(predicate, "predicate");
		if (is(predicate, a2, a3))
			action.accept(self());
		return self();
	}

	default SELF doIfNot(@Nonnull LTriDblPredicate predicate, double a2, double a3, LConsumer<SELF> action) {
		Null.nonNullArg(predicate, "predicate");
		if (!is(predicate, a2, a3))
			action.accept(self());
		return self();
	}

	default SELF doIf(double a2, double a3, @Nonnull LTriDblPredicate predicate, LConsumer<SELF> action) {
		Null.nonNullArg(predicate, "predicate");
		if (is(a2, a3, predicate))
			action.accept(self());
		return self();
	}

	default SELF doIfNot(double a2, double a3, @Nonnull LTriDblPredicate predicate, LConsumer<SELF> action) {
		Null.nonNullArg(predicate, "predicate");
		if (!is(a2, a3, predicate))
			action.accept(self());
		return self();
	}

	default SELF doIf2(@Nonnull LDblIntPredicate predicate, int v, LConsumer<SELF> action) {
		Null.nonNullArg(predicate, "predicate");
		if (is2(predicate, v))
			action.accept(self());
		return self();
	}

	default SELF doIfNot2(@Nonnull LDblIntPredicate predicate, int v, LConsumer<SELF> action) {
		Null.nonNullArg(predicate, "predicate");
		if (!is2(predicate, v))
			action.accept(self());
		return self();
	}

	default SELF doIf2(int v, @Nonnull LDblIntPredicate predicate, LConsumer<SELF> action) {
		Null.nonNullArg(predicate, "predicate");
		if (is2(v, predicate))
			action.accept(self());
		return self();
	}

	default SELF doIfNot2(int v, @Nonnull LDblIntPredicate predicate, LConsumer<SELF> action) {
		Null.nonNullArg(predicate, "predicate");
		if (!is2(v, predicate))
			action.accept(self());
		return self();
	}

	default <V> SELF doIf2_(@Nonnull LObjDblPredicate.LDblObjPred<? super V> predicate, V v, LConsumer<SELF> action) {
		Null.nonNullArg(predicate, "predicate");
		if (is2_(predicate, v))
			action.accept(self());
		return self();
	}

	default <V> SELF doIfNot2_(@Nonnull LObjDblPredicate.LDblObjPred<? super V> predicate, V v, LConsumer<SELF> action) {
		Null.nonNullArg(predicate, "predicate");
		if (!is2_(predicate, v))
			action.accept(self());
		return self();
	}

	default <V> SELF doIf2_(V v, @Nonnull LObjDblPredicate.LDblObjPred<? super V> predicate, LConsumer<SELF> action) {
		Null.nonNullArg(predicate, "predicate");
		if (is2_(v, predicate))
			action.accept(self());
		return self();
	}

	default <V> SELF doIfNot2_(V v, @Nonnull LObjDblPredicate.LDblObjPred<? super V> predicate, LConsumer<SELF> action) {
		Null.nonNullArg(predicate, "predicate");
		if (!is2_(v, predicate))
			action.accept(self());
		return self();
	}

	default SELF ifPresent(@Nonnull LDblConsumer action) {
		Null.nonNullArg(action, "action");
		if (isPresent()) {
			action.accept(get());
		}
		return self();
	}

	default SELF ifPresent(@Nonnull LDblConsumer action, @Nonnull LAction emptyAction) {
		Null.nonNullArg(action, "action");
		if (isPresent()) {
			action.accept(get());
		} else {
			emptyAction.execute();
		}
		return self();
	}

	default <K> SELF ifPresent_(K a1, @Nonnull LObjDblConsumer.LDblObjCons<? super K> action) {
		Null.nonNullArg(action, "action");
		if (isPresent()) {
			action.acceptDblObj(get(), a1);
		}
		return self();
	}

	default <K> SELF ifPresentWith(K a1, @Nonnull LObjDblConsumer<? super K> action) {
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

	default double orElseThrow() {
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

	default double orElseThrow(@Nonnull ExF<RuntimeException> fx) {
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

	default double orElseThrow(@Nonnull ExMF<RuntimeException> fx, @Nullable String msg) {
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

	default double orElseThrow(@Nonnull ExMF<RuntimeException> fx, @Nullable String msg, Object... args) {
		if (isPresent()) {
			return get();
		}

		Null.nonNullArg(fx, "fx");
		throw Handling.create(fx, msg, args);
	}

	default double orElse(@Nullable double value) {
		return isPresent() ? get() : value;
	}

	default double orElseGet(@Nonnull LDblSupplier supplier) {
		Null.nonNullArg(supplier, "supplier");
		return isPresent() ? get() : supplier.getAsDbl();
	}

	default SELF orGet(@Nonnull LSupplier<? extends OptDblTrait<?>> supplier) {
		Null.nonNullArg(supplier, "supplier");
		return isPresent() ? self() : valueFrom(supplier.get());
	}

	default SELF or(@Nullable double value) {
		return isPresent() ? self() : value(value);
	}

	default SELF orOpt(@Nonnull OptDblTrait<?> opt) {
		Null.nonNullArg(opt, "opt");
		return isPresent() ? self() : valueFrom(opt);
	}

	default <K> double orElseApply(K a1, @Nonnull LToDblFunction<? super K> supplier) {
		Null.nonNullArg(supplier, "supplier");
		return isPresent() ? get() : supplier.applyAsDbl(a1);
	}

	default <K> SELF orApply(K a1, @Nonnull LFunction<? super K, ? extends OptDblTrait<?>> supplier) {
		Null.nonNullArg(supplier, "supplier");
		return isPresent() ? self() : valueFrom(supplier.apply(a1));
	}

	// </editor-fold>
}
