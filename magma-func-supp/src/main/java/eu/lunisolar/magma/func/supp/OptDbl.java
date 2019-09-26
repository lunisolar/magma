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
public final class OptDbl implements FluentSyntax, aValue<aDouble> {

	private static final OptDbl EMPTY = new OptDbl();

	private final double value;
	private final boolean isPresent;

	// <editor-fold desc="factories">

	private OptDbl() {
		this.value = 0d;
		this.isPresent = false;
	}

	private OptDbl(double value) {
		this.value = value;
		this.isPresent = true;
	}

	public static OptDbl empty() {
		return EMPTY;
	}

	public static OptDbl of(double value) {
		return new OptDbl(value);
	}

	public static OptDbl toOpt(@Nonnull OptionalDouble optional) {
		Null.nonNullArg(optional, "optional");
		return optional.isPresent() ? OptDbl.of(optional.getAsDouble()) : empty();
	}

	// </editor-fold>

	public double get() {
		LLogicalOperator.throwIfNot(isPresent, Is::True, X::noSuchElement, "No value present.");
		return value;
	}

	public final boolean isPresent() {
		return isPresent;
	}

	public final boolean isEmpty() {
		return !isPresent;
	}

	public OptionalDouble toOpt() {
		if (isPresent()) {
			return OptionalDouble.of(value);
		} else {
			return OptionalDouble.empty();
		}
	}

	// <editor-fold desc="isPresent() dependant boolean terminals">

	public boolean is(@Nonnull LDblPredicate predicate) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && predicate.test(value);
	}

	public boolean is(@Nonnull LBiDblPredicate predicate, double a2) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && predicate.test(value, a2);
	}

	public boolean is(double a2, @Nonnull LBiDblPredicate predicate) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && predicate.test(value, a2);
	}

	public boolean is(@Nonnull LTriDblPredicate predicate, double a2, double a3) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && predicate.test(value, a2, a3);
	}

	public boolean is(double a2, double a3, @Nonnull LTriDblPredicate predicate) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && predicate.test(value, a2, a3);
	}

	public boolean is2(@Nonnull LBiDblPredicate predicate, double v) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && predicate.test(value, v);
	}

	public boolean is2(double v, @Nonnull LBiDblPredicate predicate) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && predicate.test(value, v);
	}

	public boolean is2(@Nonnull LDblIntPredicate predicate, int v) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && predicate.test(value, v);
	}

	public boolean is2(int v, @Nonnull LDblIntPredicate predicate) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && predicate.test(value, v);
	}

	public <V> boolean is2_(@Nonnull LObjDblPredicate.LDblObjPred<? super V> predicate, V v) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && predicate.testDblObj(value, v);
	}

	public <V> boolean is2_(V v, @Nonnull LObjDblPredicate.LDblObjPred<? super V> predicate) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && predicate.testDblObj(value, v);
	}

	// </editor-fold>

	// <editor-fold desc="filtering">

	public OptDbl filter(@Nonnull LDblPredicate predicate) {
		Null.nonNullArg(predicate, "predicate");
		return this.is(predicate) ? this : empty();
	}

	public OptDbl filter(@Nonnull LBiDblPredicate predicate, double a2) {
		Null.nonNullArg(predicate, "predicate");
		return this.is(predicate, a2) ? this : empty();
	}

	public OptDbl filter(double a2, @Nonnull LBiDblPredicate predicate) {
		Null.nonNullArg(predicate, "predicate");
		return this.is(a2, predicate) ? this : empty();
	}

	public OptDbl filter(@Nonnull LTriDblPredicate predicate, double a2, double a3) {
		Null.nonNullArg(predicate, "predicate");
		return this.is(predicate, a2, a3) ? this : empty();
	}

	public OptDbl filter(double a2, double a3, @Nonnull LTriDblPredicate predicate) {
		Null.nonNullArg(predicate, "predicate");
		return this.is(a2, a3, predicate) ? this : empty();
	}

	public OptDbl filter2(@Nonnull LBiDblPredicate predicate, double v) {
		Null.nonNullArg(predicate, "predicate");
		return this.is2(predicate, v) ? this : empty();
	}

	public OptDbl filter2(double v, @Nonnull LBiDblPredicate predicate) {
		Null.nonNullArg(predicate, "predicate");
		return this.is2(v, predicate) ? this : empty();
	}

	public OptDbl filter2(@Nonnull LDblIntPredicate predicate, int v) {
		Null.nonNullArg(predicate, "predicate");
		return this.is2(predicate, v) ? this : empty();
	}

	public OptDbl filter2(int v, @Nonnull LDblIntPredicate predicate) {
		Null.nonNullArg(predicate, "predicate");
		return this.is2(v, predicate) ? this : empty();
	}

	public <V> OptDbl filter2_(@Nonnull LObjDblPredicate.LDblObjPred<? super V> predicate, V v) {
		Null.nonNullArg(predicate, "predicate");
		return this.is2_(predicate, v) ? this : empty();
	}

	public <V> OptDbl filter2_(V v, @Nonnull LObjDblPredicate.LDblObjPred<? super V> predicate) {
		Null.nonNullArg(predicate, "predicate");
		return this.is2_(v, predicate) ? this : empty();
	}

	// </editor-fold>

	// <editor-fold desc="map">

	public OptBool mapToBool(@Nonnull LDblPredicate mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptBool.of(mapping.test(value))) : OptBool.empty();
	}

	public <K> OptBool mapToBool_(K a2, @Nonnull LObjDblPredicate.LDblObjPred<? super K> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptBool.of(mapping.testDblObj(value, a2))) : OptBool.empty();
	}

	public <K> OptBool mapToBoolWith(K a1, @Nonnull LObjDblPredicate<? super K> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptBool.of(mapping.test(a1, value))) : OptBool.empty();
	}

	public OptByte mapToByte(@Nonnull LDblToByteFunction mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptByte.of(mapping.applyAsByte(value))) : OptByte.empty();
	}

	public OptDbl map(@Nonnull LDblUnaryOperator mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptDbl.of(mapping.applyAsDbl(value))) : OptDbl.empty();
	}

	public OptChar mapToChar(@Nonnull LDblToCharFunction mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptChar.of(mapping.applyAsChar(value))) : OptChar.empty();
	}

	public OptSrt mapToSrt(@Nonnull LDblToSrtFunction mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptSrt.of(mapping.applyAsSrt(value))) : OptSrt.empty();
	}

	public OptFlt mapToFlt(@Nonnull LDblToFltFunction mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptFlt.of(mapping.applyAsFlt(value))) : OptFlt.empty();
	}

	public OptInt mapToInt(@Nonnull LDblToIntFunction mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptInt.of(mapping.applyAsInt(value))) : OptInt.empty();
	}

	public OptLong mapToLong(@Nonnull LDblToLongFunction mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptLong.of(mapping.applyAsLong(value))) : OptLong.empty();
	}

	public <R> Opt<R> mapToObj(@Nonnull LDblFunction<? extends R> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (Opt.of(mapping.apply(value))) : Opt.empty();
	}

	public <R, K> Opt<R> mapToObj_(K a2, @Nonnull LObjDblFunction.LDblObjFunc<? super K, ? extends R> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (Opt.of(mapping.applyDblObj(value, a2))) : Opt.empty();
	}

	public <R, K> Opt<R> mapToObjWith(K a1, @Nonnull LObjDblFunction<? super K, ? extends R> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (Opt.of(mapping.apply(a1, value))) : Opt.empty();
	}

	// </editor-fold>

	// <editor-fold desc="flatMap">

	public OptBool flatMapToBool(@Nonnull LDblFunction<? extends OptBool> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptBool) Null.nonNull(mapping.apply(value)) : OptBool.empty();
	}

	public <K> OptBool flatMapToBool_(K a2, @Nonnull LObjDblFunction.LDblObjFunc<? super K, ? extends OptBool> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptBool) Null.nonNull(mapping.applyDblObj(value, a2)) : OptBool.empty();
	}

	public <K> OptBool flatMapToBoolWith(K a1, @Nonnull LObjDblFunction<? super K, ? extends OptBool> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptBool) Null.nonNull(mapping.apply(a1, value)) : OptBool.empty();
	}

	public OptByte flatMapToByte(@Nonnull LDblFunction<? extends OptByte> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptByte) Null.nonNull(mapping.apply(value)) : OptByte.empty();
	}

	public <K> OptByte flatMapToByte_(K a2, @Nonnull LObjDblFunction.LDblObjFunc<? super K, ? extends OptByte> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptByte) Null.nonNull(mapping.applyDblObj(value, a2)) : OptByte.empty();
	}

	public <K> OptByte flatMapToByteWith(K a1, @Nonnull LObjDblFunction<? super K, ? extends OptByte> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptByte) Null.nonNull(mapping.apply(a1, value)) : OptByte.empty();
	}

	public OptDbl flatMap(@Nonnull LDblFunction<? extends OptDbl> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptDbl) Null.nonNull(mapping.apply(value)) : OptDbl.empty();
	}

	public <K> OptDbl flatMap_(K a2, @Nonnull LObjDblFunction.LDblObjFunc<? super K, ? extends OptDbl> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptDbl) Null.nonNull(mapping.applyDblObj(value, a2)) : OptDbl.empty();
	}

	public <K> OptDbl flatMapWith(K a1, @Nonnull LObjDblFunction<? super K, ? extends OptDbl> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptDbl) Null.nonNull(mapping.apply(a1, value)) : OptDbl.empty();
	}

	public OptChar flatMapToChar(@Nonnull LDblFunction<? extends OptChar> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptChar) Null.nonNull(mapping.apply(value)) : OptChar.empty();
	}

	public <K> OptChar flatMapToChar_(K a2, @Nonnull LObjDblFunction.LDblObjFunc<? super K, ? extends OptChar> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptChar) Null.nonNull(mapping.applyDblObj(value, a2)) : OptChar.empty();
	}

	public <K> OptChar flatMapToCharWith(K a1, @Nonnull LObjDblFunction<? super K, ? extends OptChar> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptChar) Null.nonNull(mapping.apply(a1, value)) : OptChar.empty();
	}

	public OptSrt flatMapToSrt(@Nonnull LDblFunction<? extends OptSrt> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptSrt) Null.nonNull(mapping.apply(value)) : OptSrt.empty();
	}

	public <K> OptSrt flatMapToSrt_(K a2, @Nonnull LObjDblFunction.LDblObjFunc<? super K, ? extends OptSrt> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptSrt) Null.nonNull(mapping.applyDblObj(value, a2)) : OptSrt.empty();
	}

	public <K> OptSrt flatMapToSrtWith(K a1, @Nonnull LObjDblFunction<? super K, ? extends OptSrt> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptSrt) Null.nonNull(mapping.apply(a1, value)) : OptSrt.empty();
	}

	public OptFlt flatMapToFlt(@Nonnull LDblFunction<? extends OptFlt> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptFlt) Null.nonNull(mapping.apply(value)) : OptFlt.empty();
	}

	public <K> OptFlt flatMapToFlt_(K a2, @Nonnull LObjDblFunction.LDblObjFunc<? super K, ? extends OptFlt> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptFlt) Null.nonNull(mapping.applyDblObj(value, a2)) : OptFlt.empty();
	}

	public <K> OptFlt flatMapToFltWith(K a1, @Nonnull LObjDblFunction<? super K, ? extends OptFlt> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptFlt) Null.nonNull(mapping.apply(a1, value)) : OptFlt.empty();
	}

	public OptInt flatMapToInt(@Nonnull LDblFunction<? extends OptInt> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptInt) Null.nonNull(mapping.apply(value)) : OptInt.empty();
	}

	public <K> OptInt flatMapToInt_(K a2, @Nonnull LObjDblFunction.LDblObjFunc<? super K, ? extends OptInt> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptInt) Null.nonNull(mapping.applyDblObj(value, a2)) : OptInt.empty();
	}

	public <K> OptInt flatMapToIntWith(K a1, @Nonnull LObjDblFunction<? super K, ? extends OptInt> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptInt) Null.nonNull(mapping.apply(a1, value)) : OptInt.empty();
	}

	public OptLong flatMapToLong(@Nonnull LDblFunction<? extends OptLong> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptLong) Null.nonNull(mapping.apply(value)) : OptLong.empty();
	}

	public <K> OptLong flatMapToLong_(K a2, @Nonnull LObjDblFunction.LDblObjFunc<? super K, ? extends OptLong> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptLong) Null.nonNull(mapping.applyDblObj(value, a2)) : OptLong.empty();
	}

	public <K> OptLong flatMapToLongWith(K a1, @Nonnull LObjDblFunction<? super K, ? extends OptLong> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptLong) Null.nonNull(mapping.apply(a1, value)) : OptLong.empty();
	}

	public <R> Opt<R> flatMapToObj(@Nonnull LDblFunction<? extends Opt<? extends R>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (Opt<R>) Null.nonNull(mapping.apply(value)) : Opt.empty();
	}

	public <R, K> Opt<R> flatMapToObj_(K a2, @Nonnull LObjDblFunction.LDblObjFunc<? super K, ? extends Opt<? extends R>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (Opt<R>) Null.nonNull(mapping.applyDblObj(value, a2)) : Opt.empty();
	}

	public <R, K> Opt<R> flatMapToObjWith(K a1, @Nonnull LObjDblFunction<? super K, ? extends Opt<? extends R>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (Opt<R>) Null.nonNull(mapping.apply(a1, value)) : Opt.empty();
	}

	// </editor-fold>

	// <editor-fold desc="ifPresent">

	public OptDbl ifPresent(@Nonnull LDblConsumer action) {
		Null.nonNullArg(action, "action");
		if (isPresent()) {
			action.accept(value);
		}
		return this;
	}

	public OptDbl ifPresent(@Nonnull LDblConsumer action, @Nonnull LAction emptyAction) {
		Null.nonNullArg(action, "action");
		if (isPresent()) {
			action.accept(value);
		} else {
			emptyAction.execute();
		}
		return this;
	}

	public <K> OptDbl ifPresent_(K a1, @Nonnull LObjDblConsumer.LDblObjCons<? super K> action) {
		Null.nonNullArg(action, "action");
		if (isPresent()) {
			action.acceptDblObj(value, a1);
		}
		return this;
	}

	public <K> OptDbl ifPresentWith(K a1, @Nonnull LObjDblConsumer<? super K> action) {
		Null.nonNullArg(action, "action");
		if (isPresent()) {
			action.accept(a1, value);
		}
		return this;
	}

	// </editor-fold>

	// <editor-fold desc="orElse">

	public double orElse(double other) {
		return isPresent() ? value : other;
	}

	public double orElseThrow() {
		if (isPresent()) {
			return value;
		}

		throw Handling.create(X::noSuchElement);
	}

	public double orElseThrow(@Nonnull ExF<RuntimeException> fx) {
		if (isPresent()) {
			return value;
		}

		Null.nonNullArg(fx, "fx");
		throw Handling.create(fx);
	}

	public double orElseThrow(@Nonnull ExMF<RuntimeException> fx, @Nullable String msg) {
		if (isPresent()) {
			return value;
		}

		Null.nonNullArg(fx, "fx");
		throw Handling.create(fx, msg);
	}

	public double orElseGet(@Nonnull LDblSupplier supplier) {
		Null.nonNullArg(supplier, "supplier");
		return isPresent() ? value : supplier.getAsDbl();
	}

	public OptDbl or(@Nonnull LSupplier<? extends OptDbl> supplier) {
		Null.nonNullArg(supplier, "supplier");
		return isPresent() ? this : (OptDbl) Null.nonNull(supplier.get());
	}

	public <K> double orElseGet(K a1, @Nonnull LToDblFunction<? super K> supplier) {
		Null.nonNullArg(supplier, "supplier");
		return isPresent() ? value : supplier.applyAsDbl(a1);
	}

	// </editor-fold>

	// <editor-fold desc="equals/hashcode/toString">

	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof OptDbl)) {
			return false;
		}

		OptDbl other = (OptDbl) obj;
		return (isPresent() && other.isPresent()) ? value == other.value : isPresent() == other.isPresent();
	}

	public int hashCode() {
		return isPresent() ? Double.hashCode(value) : 0;
	}

	public String toString() {
		return isPresent() ? String.format("OptDbl[%s]", value) : "OptDbl.empty";
	}

	// </editor-fold>

}
