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
public final class OptByte implements FluentSyntax, aValue<aByte> {

	private static final OptByte EMPTY = new OptByte();

	private final byte value;
	private final boolean isPresent;

	// <editor-fold desc="factories">

	private OptByte() {
		this.value = (byte) 0;
		this.isPresent = false;
	}

	private OptByte(byte value) {
		this.value = value;
		this.isPresent = true;
	}

	public static OptByte empty() {
		return EMPTY;
	}

	public static OptByte of(byte value) {
		return new OptByte(value);
	}

	// </editor-fold>

	public byte get() {
		LLogicalOperator.throwIfNot(isPresent, Is::True, X::noSuchElement, "No value present.");
		return value;
	}

	public final boolean isPresent() {
		return isPresent;
	}

	public final boolean isEmpty() {
		return !isPresent;
	}

	public OptionalInt toOpt() {
		if (isPresent()) {
			return OptionalInt.of(value);
		} else {
			return OptionalInt.empty();
		}
	}

	// <editor-fold desc="isPresent() dependant boolean terminals">

	public boolean is(@Nonnull LBytePredicate predicate) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && predicate.test(value);
	}

	public boolean is(@Nonnull LBiBytePredicate predicate, byte a2) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && predicate.test(value, a2);
	}

	public boolean is(byte a2, @Nonnull LBiBytePredicate predicate) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && predicate.test(value, a2);
	}

	public boolean is(@Nonnull LTriBytePredicate predicate, byte a2, byte a3) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && predicate.test(value, a2, a3);
	}

	public boolean is(byte a2, byte a3, @Nonnull LTriBytePredicate predicate) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && predicate.test(value, a2, a3);
	}

	public boolean is2(@Nonnull LBiBytePredicate predicate, byte v) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && predicate.test(value, v);
	}

	public boolean is2(byte v, @Nonnull LBiBytePredicate predicate) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && predicate.test(value, v);
	}

	public boolean is2(@Nonnull LByteIntPredicate predicate, int v) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && predicate.test(value, v);
	}

	public boolean is2(int v, @Nonnull LByteIntPredicate predicate) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && predicate.test(value, v);
	}

	public <V> boolean is2_(@Nonnull LObjBytePredicate.LByteObjPred<? super V> predicate, V v) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && predicate.testByteObj(value, v);
	}

	public <V> boolean is2_(V v, @Nonnull LObjBytePredicate.LByteObjPred<? super V> predicate) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && predicate.testByteObj(value, v);
	}

	// </editor-fold>

	// <editor-fold desc="filtering">

	public OptByte filter(@Nonnull LBytePredicate predicate) {
		Null.nonNullArg(predicate, "predicate");
		return this.is(predicate) ? this : empty();
	}

	public OptByte filter(@Nonnull LBiBytePredicate predicate, byte a2) {
		Null.nonNullArg(predicate, "predicate");
		return this.is(predicate, a2) ? this : empty();
	}

	public OptByte filter(byte a2, @Nonnull LBiBytePredicate predicate) {
		Null.nonNullArg(predicate, "predicate");
		return this.is(a2, predicate) ? this : empty();
	}

	public OptByte filter(@Nonnull LTriBytePredicate predicate, byte a2, byte a3) {
		Null.nonNullArg(predicate, "predicate");
		return this.is(predicate, a2, a3) ? this : empty();
	}

	public OptByte filter(byte a2, byte a3, @Nonnull LTriBytePredicate predicate) {
		Null.nonNullArg(predicate, "predicate");
		return this.is(a2, a3, predicate) ? this : empty();
	}

	public OptByte filter2(@Nonnull LBiBytePredicate predicate, byte v) {
		Null.nonNullArg(predicate, "predicate");
		return this.is2(predicate, v) ? this : empty();
	}

	public OptByte filter2(byte v, @Nonnull LBiBytePredicate predicate) {
		Null.nonNullArg(predicate, "predicate");
		return this.is2(v, predicate) ? this : empty();
	}

	public OptByte filter2(@Nonnull LByteIntPredicate predicate, int v) {
		Null.nonNullArg(predicate, "predicate");
		return this.is2(predicate, v) ? this : empty();
	}

	public OptByte filter2(int v, @Nonnull LByteIntPredicate predicate) {
		Null.nonNullArg(predicate, "predicate");
		return this.is2(v, predicate) ? this : empty();
	}

	public <V> OptByte filter2_(@Nonnull LObjBytePredicate.LByteObjPred<? super V> predicate, V v) {
		Null.nonNullArg(predicate, "predicate");
		return this.is2_(predicate, v) ? this : empty();
	}

	public <V> OptByte filter2_(V v, @Nonnull LObjBytePredicate.LByteObjPred<? super V> predicate) {
		Null.nonNullArg(predicate, "predicate");
		return this.is2_(v, predicate) ? this : empty();
	}

	// </editor-fold>

	// <editor-fold desc="map">

	public OptBool mapToBool(@Nonnull LBytePredicate mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptBool.of(mapping.test(value))) : OptBool.empty();
	}

	public <K> OptBool mapToBool_(K a2, @Nonnull LObjBytePredicate.LByteObjPred<? super K> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptBool.of(mapping.testByteObj(value, a2))) : OptBool.empty();
	}

	public <K> OptBool mapToBoolWith(K a1, @Nonnull LObjBytePredicate<? super K> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptBool.of(mapping.test(a1, value))) : OptBool.empty();
	}

	public OptByte map(@Nonnull LByteUnaryOperator mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptByte.of(mapping.applyAsByte(value))) : OptByte.empty();
	}

	public OptDbl mapToDbl(@Nonnull LByteToDblFunction mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptDbl.of(mapping.applyAsDbl(value))) : OptDbl.empty();
	}

	public OptChar mapToChar(@Nonnull LByteToCharFunction mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptChar.of(mapping.applyAsChar(value))) : OptChar.empty();
	}

	public OptSrt mapToSrt(@Nonnull LByteToSrtFunction mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptSrt.of(mapping.applyAsSrt(value))) : OptSrt.empty();
	}

	public OptFlt mapToFlt(@Nonnull LByteToFltFunction mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptFlt.of(mapping.applyAsFlt(value))) : OptFlt.empty();
	}

	public OptInt mapToInt(@Nonnull LByteToIntFunction mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptInt.of(mapping.applyAsInt(value))) : OptInt.empty();
	}

	public OptLong mapToLong(@Nonnull LByteToLongFunction mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptLong.of(mapping.applyAsLong(value))) : OptLong.empty();
	}

	public <R> Opt<R> mapToObj(@Nonnull LByteFunction<? extends R> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (Opt.of(mapping.apply(value))) : Opt.empty();
	}

	public <R, K> Opt<R> mapToObj_(K a2, @Nonnull LObjByteFunction.LByteObjFunc<? super K, ? extends R> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (Opt.of(mapping.applyByteObj(value, a2))) : Opt.empty();
	}

	public <R, K> Opt<R> mapToObjWith(K a1, @Nonnull LObjByteFunction<? super K, ? extends R> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (Opt.of(mapping.apply(a1, value))) : Opt.empty();
	}

	// </editor-fold>

	// <editor-fold desc="flatMap">

	public OptBool flatMapToBool(@Nonnull LByteFunction<? extends OptBool> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptBool) Null.nonNull(mapping.apply(value)) : OptBool.empty();
	}

	public <K> OptBool flatMapToBool_(K a2, @Nonnull LObjByteFunction.LByteObjFunc<? super K, ? extends OptBool> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptBool) Null.nonNull(mapping.applyByteObj(value, a2)) : OptBool.empty();
	}

	public <K> OptBool flatMapToBoolWith(K a1, @Nonnull LObjByteFunction<? super K, ? extends OptBool> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptBool) Null.nonNull(mapping.apply(a1, value)) : OptBool.empty();
	}

	public OptByte flatMap(@Nonnull LByteFunction<? extends OptByte> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptByte) Null.nonNull(mapping.apply(value)) : OptByte.empty();
	}

	public <K> OptByte flatMap_(K a2, @Nonnull LObjByteFunction.LByteObjFunc<? super K, ? extends OptByte> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptByte) Null.nonNull(mapping.applyByteObj(value, a2)) : OptByte.empty();
	}

	public <K> OptByte flatMapWith(K a1, @Nonnull LObjByteFunction<? super K, ? extends OptByte> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptByte) Null.nonNull(mapping.apply(a1, value)) : OptByte.empty();
	}

	public OptDbl flatMapToDbl(@Nonnull LByteFunction<? extends OptDbl> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptDbl) Null.nonNull(mapping.apply(value)) : OptDbl.empty();
	}

	public <K> OptDbl flatMapToDbl_(K a2, @Nonnull LObjByteFunction.LByteObjFunc<? super K, ? extends OptDbl> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptDbl) Null.nonNull(mapping.applyByteObj(value, a2)) : OptDbl.empty();
	}

	public <K> OptDbl flatMapToDblWith(K a1, @Nonnull LObjByteFunction<? super K, ? extends OptDbl> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptDbl) Null.nonNull(mapping.apply(a1, value)) : OptDbl.empty();
	}

	public OptChar flatMapToChar(@Nonnull LByteFunction<? extends OptChar> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptChar) Null.nonNull(mapping.apply(value)) : OptChar.empty();
	}

	public <K> OptChar flatMapToChar_(K a2, @Nonnull LObjByteFunction.LByteObjFunc<? super K, ? extends OptChar> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptChar) Null.nonNull(mapping.applyByteObj(value, a2)) : OptChar.empty();
	}

	public <K> OptChar flatMapToCharWith(K a1, @Nonnull LObjByteFunction<? super K, ? extends OptChar> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptChar) Null.nonNull(mapping.apply(a1, value)) : OptChar.empty();
	}

	public OptSrt flatMapToSrt(@Nonnull LByteFunction<? extends OptSrt> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptSrt) Null.nonNull(mapping.apply(value)) : OptSrt.empty();
	}

	public <K> OptSrt flatMapToSrt_(K a2, @Nonnull LObjByteFunction.LByteObjFunc<? super K, ? extends OptSrt> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptSrt) Null.nonNull(mapping.applyByteObj(value, a2)) : OptSrt.empty();
	}

	public <K> OptSrt flatMapToSrtWith(K a1, @Nonnull LObjByteFunction<? super K, ? extends OptSrt> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptSrt) Null.nonNull(mapping.apply(a1, value)) : OptSrt.empty();
	}

	public OptFlt flatMapToFlt(@Nonnull LByteFunction<? extends OptFlt> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptFlt) Null.nonNull(mapping.apply(value)) : OptFlt.empty();
	}

	public <K> OptFlt flatMapToFlt_(K a2, @Nonnull LObjByteFunction.LByteObjFunc<? super K, ? extends OptFlt> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptFlt) Null.nonNull(mapping.applyByteObj(value, a2)) : OptFlt.empty();
	}

	public <K> OptFlt flatMapToFltWith(K a1, @Nonnull LObjByteFunction<? super K, ? extends OptFlt> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptFlt) Null.nonNull(mapping.apply(a1, value)) : OptFlt.empty();
	}

	public OptInt flatMapToInt(@Nonnull LByteFunction<? extends OptInt> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptInt) Null.nonNull(mapping.apply(value)) : OptInt.empty();
	}

	public <K> OptInt flatMapToInt_(K a2, @Nonnull LObjByteFunction.LByteObjFunc<? super K, ? extends OptInt> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptInt) Null.nonNull(mapping.applyByteObj(value, a2)) : OptInt.empty();
	}

	public <K> OptInt flatMapToIntWith(K a1, @Nonnull LObjByteFunction<? super K, ? extends OptInt> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptInt) Null.nonNull(mapping.apply(a1, value)) : OptInt.empty();
	}

	public OptLong flatMapToLong(@Nonnull LByteFunction<? extends OptLong> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptLong) Null.nonNull(mapping.apply(value)) : OptLong.empty();
	}

	public <K> OptLong flatMapToLong_(K a2, @Nonnull LObjByteFunction.LByteObjFunc<? super K, ? extends OptLong> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptLong) Null.nonNull(mapping.applyByteObj(value, a2)) : OptLong.empty();
	}

	public <K> OptLong flatMapToLongWith(K a1, @Nonnull LObjByteFunction<? super K, ? extends OptLong> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptLong) Null.nonNull(mapping.apply(a1, value)) : OptLong.empty();
	}

	public <R> Opt<R> flatMapToObj(@Nonnull LByteFunction<? extends Opt<? extends R>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (Opt<R>) Null.nonNull(mapping.apply(value)) : Opt.empty();
	}

	public <R, K> Opt<R> flatMapToObj_(K a2, @Nonnull LObjByteFunction.LByteObjFunc<? super K, ? extends Opt<? extends R>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (Opt<R>) Null.nonNull(mapping.applyByteObj(value, a2)) : Opt.empty();
	}

	public <R, K> Opt<R> flatMapToObjWith(K a1, @Nonnull LObjByteFunction<? super K, ? extends Opt<? extends R>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (Opt<R>) Null.nonNull(mapping.apply(a1, value)) : Opt.empty();
	}

	// </editor-fold>

	// <editor-fold desc="ifPresent">

	public OptByte ifPresent(@Nonnull LByteConsumer action) {
		Null.nonNullArg(action, "action");
		if (isPresent()) {
			action.accept(value);
		}
		return this;
	}

	public OptByte ifPresent(@Nonnull LByteConsumer action, @Nonnull LAction emptyAction) {
		Null.nonNullArg(action, "action");
		if (isPresent()) {
			action.accept(value);
		} else {
			emptyAction.execute();
		}
		return this;
	}

	public <K> OptByte ifPresent_(K a1, @Nonnull LObjByteConsumer.LByteObjCons<? super K> action) {
		Null.nonNullArg(action, "action");
		if (isPresent()) {
			action.acceptByteObj(value, a1);
		}
		return this;
	}

	public <K> OptByte ifPresentWith(K a1, @Nonnull LObjByteConsumer<? super K> action) {
		Null.nonNullArg(action, "action");
		if (isPresent()) {
			action.accept(a1, value);
		}
		return this;
	}

	// </editor-fold>

	// <editor-fold desc="orElse">

	public byte orElse(byte other) {
		return isPresent() ? value : other;
	}

	public byte orElseThrow() {
		if (isPresent()) {
			return value;
		}

		throw Handling.create(X::noSuchElement);
	}

	public byte orElseThrow(@Nonnull ExF<RuntimeException> fx) {
		if (isPresent()) {
			return value;
		}

		Null.nonNullArg(fx, "fx");
		throw Handling.create(fx);
	}

	public byte orElseThrow(@Nonnull ExMF<RuntimeException> fx, @Nullable String msg) {
		if (isPresent()) {
			return value;
		}

		Null.nonNullArg(fx, "fx");
		throw Handling.create(fx, msg);
	}

	public byte orElseGet(@Nonnull LByteSupplier supplier) {
		Null.nonNullArg(supplier, "supplier");
		return isPresent() ? value : supplier.getAsByte();
	}

	public OptByte or(@Nonnull LSupplier<? extends OptByte> supplier) {
		Null.nonNullArg(supplier, "supplier");
		return isPresent() ? this : (OptByte) Null.nonNull(supplier.get());
	}

	public <K> byte orElseGet(K a1, @Nonnull LToByteFunction<? super K> supplier) {
		Null.nonNullArg(supplier, "supplier");
		return isPresent() ? value : supplier.applyAsByte(a1);
	}

	// </editor-fold>

	// <editor-fold desc="equals/hashcode/toString">

	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof OptByte)) {
			return false;
		}

		OptByte other = (OptByte) obj;
		return (isPresent() && other.isPresent()) ? value == other.value : isPresent() == other.isPresent();
	}

	public int hashCode() {
		return isPresent() ? Byte.hashCode(value) : 0;
	}

	public String toString() {
		return isPresent() ? String.format("OptByte[%s]", value) : "OptByte.empty";
	}

	// </editor-fold>

}
