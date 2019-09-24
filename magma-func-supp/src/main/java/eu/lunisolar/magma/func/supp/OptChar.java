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
import java.util.Objects; // NOSONAR
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
public final class OptChar implements FluentSyntax, aValue<aChar> {

	private static final OptChar EMPTY = new OptChar();

	private final char value;
	private final boolean isPresent;

	// <editor-fold desc="factories">

	private OptChar() {
		this.value = '\u0000';
		this.isPresent = false;
	}

	private OptChar(char value) {
		this.value = value;
		this.isPresent = true;
	}

	public static OptChar empty() {
		return EMPTY;
	}

	public static OptChar of(char value) {
		return new OptChar(value);
	}

	// </editor-fold>

	public char get() {
		LLogicalOperator.throwIfNot(isPresent, P::isTrue, X::noSuchElement, "No value present.");
		return value;
	}

	public final boolean isPresent() {
		return isPresent;
	}

	public final boolean isEmpty() {
		return !isPresent;
	}

	// <editor-fold desc="isPresent() dependant boolean terminals">

	public boolean is(@Nonnull LCharPredicate predicate) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && predicate.test(value);
	}

	public boolean is(@Nonnull LBiCharPredicate predicate, char a2) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && predicate.test(value, a2);
	}

	public boolean is(char a2, @Nonnull LBiCharPredicate predicate) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && predicate.test(value, a2);
	}

	public boolean is(@Nonnull LTriCharPredicate predicate, char a2, char a3) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && predicate.test(value, a2, a3);
	}

	public boolean is(char a2, char a3, @Nonnull LTriCharPredicate predicate) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && predicate.test(value, a2, a3);
	}

	public boolean is2(@Nonnull LBiCharPredicate predicate, char v) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && predicate.test(value, v);
	}

	public boolean is2(char v, @Nonnull LBiCharPredicate predicate) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && predicate.test(value, v);
	}

	public boolean is2(@Nonnull LCharIntPredicate predicate, int v) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && predicate.test(value, v);
	}

	public boolean is2(int v, @Nonnull LCharIntPredicate predicate) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && predicate.test(value, v);
	}

	public <V> boolean is2_(@Nonnull LObjCharPredicate.LCharObjPred<? super V> predicate, V v) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && predicate.testCharObj(value, v);
	}

	public <V> boolean is2_(V v, @Nonnull LObjCharPredicate.LCharObjPred<? super V> predicate) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && predicate.testCharObj(value, v);
	}

	// </editor-fold>

	// <editor-fold desc="filtering">

	public OptChar filter(@Nonnull LCharPredicate predicate) {
		Null.nonNullArg(predicate, "predicate");
		return this.is(predicate) ? this : empty();
	}

	public OptChar filter(@Nonnull LBiCharPredicate predicate, char a2) {
		Null.nonNullArg(predicate, "predicate");
		return this.is(predicate, a2) ? this : empty();
	}

	public OptChar filter(char a2, @Nonnull LBiCharPredicate predicate) {
		Null.nonNullArg(predicate, "predicate");
		return this.is(a2, predicate) ? this : empty();
	}

	public OptChar filter(@Nonnull LTriCharPredicate predicate, char a2, char a3) {
		Null.nonNullArg(predicate, "predicate");
		return this.is(predicate, a2, a3) ? this : empty();
	}

	public OptChar filter(char a2, char a3, @Nonnull LTriCharPredicate predicate) {
		Null.nonNullArg(predicate, "predicate");
		return this.is(a2, a3, predicate) ? this : empty();
	}

	public OptChar filter2(@Nonnull LBiCharPredicate predicate, char v) {
		Null.nonNullArg(predicate, "predicate");
		return this.is2(predicate, v) ? this : empty();
	}

	public OptChar filter2(char v, @Nonnull LBiCharPredicate predicate) {
		Null.nonNullArg(predicate, "predicate");
		return this.is2(v, predicate) ? this : empty();
	}

	public OptChar filter2(@Nonnull LCharIntPredicate predicate, int v) {
		Null.nonNullArg(predicate, "predicate");
		return this.is2(predicate, v) ? this : empty();
	}

	public OptChar filter2(int v, @Nonnull LCharIntPredicate predicate) {
		Null.nonNullArg(predicate, "predicate");
		return this.is2(v, predicate) ? this : empty();
	}

	public <V> OptChar filter2_(@Nonnull LObjCharPredicate.LCharObjPred<? super V> predicate, V v) {
		Null.nonNullArg(predicate, "predicate");
		return this.is2_(predicate, v) ? this : empty();
	}

	public <V> OptChar filter2_(V v, @Nonnull LObjCharPredicate.LCharObjPred<? super V> predicate) {
		Null.nonNullArg(predicate, "predicate");
		return this.is2_(v, predicate) ? this : empty();
	}

	// </editor-fold>

	// <editor-fold desc="map">

	public OptBool mapToBool(@Nonnull LCharPredicate mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptBool.of(mapping.test(value))) : OptBool.empty();
	}

	public <K> OptBool mapToBool_(K a2, @Nonnull LObjCharPredicate.LCharObjPred<? super K> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptBool.of(mapping.testCharObj(value, a2))) : OptBool.empty();
	}

	public <K> OptBool mapToBoolWith(K a1, @Nonnull LObjCharPredicate<? super K> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptBool.of(mapping.test(a1, value))) : OptBool.empty();
	}

	public OptByte mapToByte(@Nonnull LCharToByteFunction mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptByte.of(mapping.applyAsByte(value))) : OptByte.empty();
	}

	public OptDbl mapToDbl(@Nonnull LCharToDblFunction mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptDbl.of(mapping.applyAsDbl(value))) : OptDbl.empty();
	}

	public OptChar map(@Nonnull LCharUnaryOperator mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptChar.of(mapping.applyAsChar(value))) : OptChar.empty();
	}

	public OptSrt mapToSrt(@Nonnull LCharToSrtFunction mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptSrt.of(mapping.applyAsSrt(value))) : OptSrt.empty();
	}

	public OptFlt mapToFlt(@Nonnull LCharToFltFunction mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptFlt.of(mapping.applyAsFlt(value))) : OptFlt.empty();
	}

	public OptInt mapToInt(@Nonnull LCharToIntFunction mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptInt.of(mapping.applyAsInt(value))) : OptInt.empty();
	}

	public OptLong mapToLong(@Nonnull LCharToLongFunction mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptLong.of(mapping.applyAsLong(value))) : OptLong.empty();
	}

	public <R> Opt<R> mapToObj(@Nonnull LCharFunction<? extends R> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (Opt.of(mapping.apply(value))) : Opt.empty();
	}

	public <R, K> Opt<R> mapToObj_(K a2, @Nonnull LObjCharFunction.LCharObjFunc<? super K, ? extends R> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (Opt.of(mapping.applyCharObj(value, a2))) : Opt.empty();
	}

	public <R, K> Opt<R> mapToObjWith(K a1, @Nonnull LObjCharFunction<? super K, ? extends R> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (Opt.of(mapping.apply(a1, value))) : Opt.empty();
	}

	// </editor-fold>

	// <editor-fold desc="flatMap">

	public OptBool flatMapToBool(@Nonnull LCharFunction<? extends OptBool> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? Null.nonNull(mapping.apply(value)) : OptBool.empty();
	}

	public <K> OptBool flatMapToBool_(K a2, @Nonnull LObjCharFunction.LCharObjFunc<? super K, ? extends OptBool> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? Null.nonNull(mapping.applyCharObj(value, a2)) : OptBool.empty();
	}

	public <K> OptBool flatMapToBoolWith(K a1, @Nonnull LObjCharFunction<? super K, ? extends OptBool> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? Null.nonNull(mapping.apply(a1, value)) : OptBool.empty();
	}

	public OptByte flatMapToByte(@Nonnull LCharFunction<? extends OptByte> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? Null.nonNull(mapping.apply(value)) : OptByte.empty();
	}

	public <K> OptByte flatMapToByte_(K a2, @Nonnull LObjCharFunction.LCharObjFunc<? super K, ? extends OptByte> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? Null.nonNull(mapping.applyCharObj(value, a2)) : OptByte.empty();
	}

	public <K> OptByte flatMapToByteWith(K a1, @Nonnull LObjCharFunction<? super K, ? extends OptByte> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? Null.nonNull(mapping.apply(a1, value)) : OptByte.empty();
	}

	public OptDbl flatMapToDbl(@Nonnull LCharFunction<? extends OptDbl> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? Null.nonNull(mapping.apply(value)) : OptDbl.empty();
	}

	public <K> OptDbl flatMapToDbl_(K a2, @Nonnull LObjCharFunction.LCharObjFunc<? super K, ? extends OptDbl> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? Null.nonNull(mapping.applyCharObj(value, a2)) : OptDbl.empty();
	}

	public <K> OptDbl flatMapToDblWith(K a1, @Nonnull LObjCharFunction<? super K, ? extends OptDbl> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? Null.nonNull(mapping.apply(a1, value)) : OptDbl.empty();
	}

	public OptChar flatMap(@Nonnull LCharFunction<? extends OptChar> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? Null.nonNull(mapping.apply(value)) : OptChar.empty();
	}

	public <K> OptChar flatMap_(K a2, @Nonnull LObjCharFunction.LCharObjFunc<? super K, ? extends OptChar> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? Null.nonNull(mapping.applyCharObj(value, a2)) : OptChar.empty();
	}

	public <K> OptChar flatMapWith(K a1, @Nonnull LObjCharFunction<? super K, ? extends OptChar> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? Null.nonNull(mapping.apply(a1, value)) : OptChar.empty();
	}

	public OptSrt flatMapToSrt(@Nonnull LCharFunction<? extends OptSrt> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? Null.nonNull(mapping.apply(value)) : OptSrt.empty();
	}

	public <K> OptSrt flatMapToSrt_(K a2, @Nonnull LObjCharFunction.LCharObjFunc<? super K, ? extends OptSrt> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? Null.nonNull(mapping.applyCharObj(value, a2)) : OptSrt.empty();
	}

	public <K> OptSrt flatMapToSrtWith(K a1, @Nonnull LObjCharFunction<? super K, ? extends OptSrt> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? Null.nonNull(mapping.apply(a1, value)) : OptSrt.empty();
	}

	public OptFlt flatMapToFlt(@Nonnull LCharFunction<? extends OptFlt> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? Null.nonNull(mapping.apply(value)) : OptFlt.empty();
	}

	public <K> OptFlt flatMapToFlt_(K a2, @Nonnull LObjCharFunction.LCharObjFunc<? super K, ? extends OptFlt> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? Null.nonNull(mapping.applyCharObj(value, a2)) : OptFlt.empty();
	}

	public <K> OptFlt flatMapToFltWith(K a1, @Nonnull LObjCharFunction<? super K, ? extends OptFlt> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? Null.nonNull(mapping.apply(a1, value)) : OptFlt.empty();
	}

	public OptInt flatMapToInt(@Nonnull LCharFunction<? extends OptInt> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? Null.nonNull(mapping.apply(value)) : OptInt.empty();
	}

	public <K> OptInt flatMapToInt_(K a2, @Nonnull LObjCharFunction.LCharObjFunc<? super K, ? extends OptInt> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? Null.nonNull(mapping.applyCharObj(value, a2)) : OptInt.empty();
	}

	public <K> OptInt flatMapToIntWith(K a1, @Nonnull LObjCharFunction<? super K, ? extends OptInt> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? Null.nonNull(mapping.apply(a1, value)) : OptInt.empty();
	}

	public OptLong flatMapToLong(@Nonnull LCharFunction<? extends OptLong> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? Null.nonNull(mapping.apply(value)) : OptLong.empty();
	}

	public <K> OptLong flatMapToLong_(K a2, @Nonnull LObjCharFunction.LCharObjFunc<? super K, ? extends OptLong> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? Null.nonNull(mapping.applyCharObj(value, a2)) : OptLong.empty();
	}

	public <K> OptLong flatMapToLongWith(K a1, @Nonnull LObjCharFunction<? super K, ? extends OptLong> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? Null.nonNull(mapping.apply(a1, value)) : OptLong.empty();
	}

	public <R> Opt<R> flatMapToObj(@Nonnull LCharFunction<? extends Opt<R>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? Null.nonNull(mapping.apply(value)) : Opt.empty();
	}

	public <R, K> Opt<R> flatMapToObj_(K a2, @Nonnull LObjCharFunction.LCharObjFunc<? super K, ? extends Opt<R>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? Null.nonNull(mapping.applyCharObj(value, a2)) : Opt.empty();
	}

	public <R, K> Opt<R> flatMapToObjWith(K a1, @Nonnull LObjCharFunction<? super K, ? extends Opt<R>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? Null.nonNull(mapping.apply(a1, value)) : Opt.empty();
	}

	// </editor-fold>

	// <editor-fold desc="ifPresent">

	public OptChar ifPresent(@Nonnull LCharConsumer action) {
		Null.nonNullArg(action, "action");
		if (isPresent()) {
			action.accept(value);
		}
		return this;
	}

	public OptChar ifPresent(@Nonnull LCharConsumer action, @Nonnull LAction emptyAction) {
		Null.nonNullArg(action, "action");
		if (isPresent()) {
			action.accept(value);
		} else {
			emptyAction.execute();
		}
		return this;
	}

	public <K> OptChar ifPresent_(K a1, @Nonnull LObjCharConsumer.LCharObjCons<? super K> action) {
		Null.nonNullArg(action, "action");
		if (isPresent()) {
			action.acceptCharObj(value, a1);
		}
		return this;
	}

	public <K> OptChar ifPresentWith(K a1, @Nonnull LObjCharConsumer<? super K> action) {
		Null.nonNullArg(action, "action");
		if (isPresent()) {
			action.accept(a1, value);
		}
		return this;
	}

	// </editor-fold>

	// <editor-fold desc="orElse">

	public char orElse(char other) {
		return isPresent() ? value : other;
	}

	public char orElseThrow() {
		if (isPresent()) {
			return value;
		}

		throw Handling.create(X::noSuchElement);
	}

	public char orElseThrow(@Nonnull ExF<RuntimeException> fx) {
		if (isPresent()) {
			return value;
		}

		Null.nonNullArg(fx, "fx");
		throw Handling.create(fx);
	}

	public char orElseThrow(@Nonnull ExMF<RuntimeException> fx, @Nullable String msg) {
		if (isPresent()) {
			return value;
		}

		Null.nonNullArg(fx, "fx");
		throw Handling.create(fx, msg);
	}

	public char orElse(@Nonnull LCharSupplier supplier) {
		Null.nonNullArg(supplier, "supplier");
		return isPresent() ? value : supplier.getAsChar();
	}

	public <K> char orElse(K a1, @Nonnull LToCharFunction<? super K> supplier) {
		Null.nonNullArg(supplier, "supplier");
		return isPresent() ? value : supplier.applyAsChar(a1);
	}

	// </editor-fold>

	// <editor-fold desc="equals/hashcode/toString">

	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof OptChar)) {
			return false;
		}

		OptChar other = (OptChar) obj;
		return (isPresent() && other.isPresent()) ? value == other.value : isPresent() == other.isPresent();
	}

	public int hashCode() {
		return isPresent() ? Character.hashCode(value) : 0;
	}

	public String toString() {
		return isPresent() ? String.format("OptChar[%s]", value) : "OptChar.empty";
	}

	// </editor-fold>

}
