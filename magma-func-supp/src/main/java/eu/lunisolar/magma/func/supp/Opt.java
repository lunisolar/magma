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
public final class Opt<T> implements FluentSyntax, aValue<a<T>> {

	private static final Opt<?> EMPTY = new Opt();

	private final @Nullable T value;

	// <editor-fold desc="factories">

	private Opt() {
		this.value = null;
	}

	private Opt(T value) {
		Null.nonNullArg(value, "value");
		this.value = value;
	}

	public static Opt empty() {
		return EMPTY;
	}

	public static <T> Opt<T> of(@Nullable T value) {
		return value == null ? empty() : new Opt(value);
	}

	/** If you want to force Opt<T> */
	public static <T> Opt<T> obj(@Nullable T value) {
		return value == null ? empty() : new Opt(value);
	}

	public static OptByte of(byte value) {
		return OptByte.of(value);
	}

	public static OptSrt of(short value) {
		return OptSrt.of(value);
	}

	public static OptInt of(int value) {
		return OptInt.of(value);
	}

	public static OptLong of(long value) {
		return OptLong.of(value);
	}

	public static OptFlt of(float value) {
		return OptFlt.of(value);
	}

	public static OptDbl of(double value) {
		return OptDbl.of(value);
	}

	public static OptChar of(char value) {
		return OptChar.of(value);
	}

	public static OptBool of(boolean value) {
		return OptBool.of(value);
	}

	// </editor-fold>

	public T get() {
		LPredicate.throwIfNot(this, Opt::isPresent, X::noSuchElement, "No value present.");
		return value;
	}

	public final boolean isPresent() {
		return value != null;
	}

	public final boolean isEmpty() {
		return value == null;
	}

	// <editor-fold desc="isPresent() dependant boolean terminals">

	public boolean is(@Nonnull LPredicate<? super T> predicate) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && predicate.test(value);
	}

	public boolean is(@Nonnull LBiPredicate<? super T, ? super T> predicate, T a2) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && predicate.test(value, a2);
	}

	public boolean is(T a2, @Nonnull LBiPredicate<? super T, ? super T> predicate) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && predicate.test(value, a2);
	}

	public boolean is(@Nonnull LTriPredicate<? super T, ? super T, ? super T> predicate, T a2, T a3) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && predicate.test(value, a2, a3);
	}

	public boolean is(T a2, T a3, @Nonnull LTriPredicate<? super T, ? super T, ? super T> predicate) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && predicate.test(value, a2, a3);
	}

	public boolean is2(@Nonnull LObjBoolPredicate<? super T> predicate, boolean v) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && predicate.test(value, v);
	}

	public boolean is2(boolean v, @Nonnull LObjBoolPredicate<? super T> predicate) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && predicate.test(value, v);
	}

	public boolean is2(@Nonnull LObjBytePredicate<? super T> predicate, byte v) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && predicate.test(value, v);
	}

	public boolean is2(byte v, @Nonnull LObjBytePredicate<? super T> predicate) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && predicate.test(value, v);
	}

	public boolean is2(@Nonnull LObjDblPredicate<? super T> predicate, double v) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && predicate.test(value, v);
	}

	public boolean is2(double v, @Nonnull LObjDblPredicate<? super T> predicate) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && predicate.test(value, v);
	}

	public boolean is2(@Nonnull LObjCharPredicate<? super T> predicate, char v) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && predicate.test(value, v);
	}

	public boolean is2(char v, @Nonnull LObjCharPredicate<? super T> predicate) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && predicate.test(value, v);
	}

	public boolean is2(@Nonnull LObjSrtPredicate<? super T> predicate, short v) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && predicate.test(value, v);
	}

	public boolean is2(short v, @Nonnull LObjSrtPredicate<? super T> predicate) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && predicate.test(value, v);
	}

	public boolean is2(@Nonnull LObjFltPredicate<? super T> predicate, float v) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && predicate.test(value, v);
	}

	public boolean is2(float v, @Nonnull LObjFltPredicate<? super T> predicate) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && predicate.test(value, v);
	}

	public boolean is2(@Nonnull LObjIntPredicate<? super T> predicate, int v) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && predicate.test(value, v);
	}

	public boolean is2(int v, @Nonnull LObjIntPredicate<? super T> predicate) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && predicate.test(value, v);
	}

	public boolean is2(@Nonnull LObjLongPredicate<? super T> predicate, long v) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && predicate.test(value, v);
	}

	public boolean is2(long v, @Nonnull LObjLongPredicate<? super T> predicate) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && predicate.test(value, v);
	}

	public <V> boolean is2(@Nonnull LBiPredicate<? super T, ? super V> predicate, V v) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && predicate.test(value, v);
	}

	public <V> boolean is2(V v, @Nonnull LBiPredicate<? super T, ? super V> predicate) {
		Null.nonNullArg(predicate, "predicate");
		return isPresent() && predicate.test(value, v);
	}

	// </editor-fold>

	// <editor-fold desc="filtering">

	public Opt<T> filter(@Nonnull LPredicate<? super T> predicate) {
		Null.nonNullArg(predicate, "predicate");
		return this.is(predicate) ? this : empty();
	}

	public Opt<T> filter(@Nonnull LBiPredicate<? super T, ? super T> predicate, T a2) {
		Null.nonNullArg(predicate, "predicate");
		return this.is(predicate, a2) ? this : empty();
	}

	public Opt<T> filter(T a2, @Nonnull LBiPredicate<? super T, ? super T> predicate) {
		Null.nonNullArg(predicate, "predicate");
		return this.is(a2, predicate) ? this : empty();
	}

	public Opt<T> filter(@Nonnull LTriPredicate<? super T, ? super T, ? super T> predicate, T a2, T a3) {
		Null.nonNullArg(predicate, "predicate");
		return this.is(predicate, a2, a3) ? this : empty();
	}

	public Opt<T> filter(T a2, T a3, @Nonnull LTriPredicate<? super T, ? super T, ? super T> predicate) {
		Null.nonNullArg(predicate, "predicate");
		return this.is(a2, a3, predicate) ? this : empty();
	}

	public Opt<T> filter2(@Nonnull LObjBoolPredicate<? super T> predicate, boolean v) {
		Null.nonNullArg(predicate, "predicate");
		return this.is2(predicate, v) ? this : empty();
	}

	public Opt<T> filter2(boolean v, @Nonnull LObjBoolPredicate<? super T> predicate) {
		Null.nonNullArg(predicate, "predicate");
		return this.is2(v, predicate) ? this : empty();
	}

	public Opt<T> filter2(@Nonnull LObjBytePredicate<? super T> predicate, byte v) {
		Null.nonNullArg(predicate, "predicate");
		return this.is2(predicate, v) ? this : empty();
	}

	public Opt<T> filter2(byte v, @Nonnull LObjBytePredicate<? super T> predicate) {
		Null.nonNullArg(predicate, "predicate");
		return this.is2(v, predicate) ? this : empty();
	}

	public Opt<T> filter2(@Nonnull LObjDblPredicate<? super T> predicate, double v) {
		Null.nonNullArg(predicate, "predicate");
		return this.is2(predicate, v) ? this : empty();
	}

	public Opt<T> filter2(double v, @Nonnull LObjDblPredicate<? super T> predicate) {
		Null.nonNullArg(predicate, "predicate");
		return this.is2(v, predicate) ? this : empty();
	}

	public Opt<T> filter2(@Nonnull LObjCharPredicate<? super T> predicate, char v) {
		Null.nonNullArg(predicate, "predicate");
		return this.is2(predicate, v) ? this : empty();
	}

	public Opt<T> filter2(char v, @Nonnull LObjCharPredicate<? super T> predicate) {
		Null.nonNullArg(predicate, "predicate");
		return this.is2(v, predicate) ? this : empty();
	}

	public Opt<T> filter2(@Nonnull LObjSrtPredicate<? super T> predicate, short v) {
		Null.nonNullArg(predicate, "predicate");
		return this.is2(predicate, v) ? this : empty();
	}

	public Opt<T> filter2(short v, @Nonnull LObjSrtPredicate<? super T> predicate) {
		Null.nonNullArg(predicate, "predicate");
		return this.is2(v, predicate) ? this : empty();
	}

	public Opt<T> filter2(@Nonnull LObjFltPredicate<? super T> predicate, float v) {
		Null.nonNullArg(predicate, "predicate");
		return this.is2(predicate, v) ? this : empty();
	}

	public Opt<T> filter2(float v, @Nonnull LObjFltPredicate<? super T> predicate) {
		Null.nonNullArg(predicate, "predicate");
		return this.is2(v, predicate) ? this : empty();
	}

	public Opt<T> filter2(@Nonnull LObjIntPredicate<? super T> predicate, int v) {
		Null.nonNullArg(predicate, "predicate");
		return this.is2(predicate, v) ? this : empty();
	}

	public Opt<T> filter2(int v, @Nonnull LObjIntPredicate<? super T> predicate) {
		Null.nonNullArg(predicate, "predicate");
		return this.is2(v, predicate) ? this : empty();
	}

	public Opt<T> filter2(@Nonnull LObjLongPredicate<? super T> predicate, long v) {
		Null.nonNullArg(predicate, "predicate");
		return this.is2(predicate, v) ? this : empty();
	}

	public Opt<T> filter2(long v, @Nonnull LObjLongPredicate<? super T> predicate) {
		Null.nonNullArg(predicate, "predicate");
		return this.is2(v, predicate) ? this : empty();
	}

	public <V> Opt<T> filter2(@Nonnull LBiPredicate<? super T, ? super V> predicate, V v) {
		Null.nonNullArg(predicate, "predicate");
		return this.is2(predicate, v) ? this : empty();
	}

	public <V> Opt<T> filter2(V v, @Nonnull LBiPredicate<? super T, ? super V> predicate) {
		Null.nonNullArg(predicate, "predicate");
		return this.is2(v, predicate) ? this : empty();
	}

	// </editor-fold>

	// <editor-fold desc="map">

	public OptBool mapToBool(@Nonnull LPredicate<? super T> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptBool.of(mapping.test(value))) : OptBool.empty();
	}

	public <K> OptBool mapToBool(K a2, @Nonnull LBiPredicate<? super T, ? super K> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptBool.of(mapping.test(value, a2))) : OptBool.empty();
	}

	public <K> OptBool mapToBoolWith(K a1, @Nonnull LBiPredicate<? super K, ? super T> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptBool.of(mapping.test(a1, value))) : OptBool.empty();
	}

	public OptByte mapToByte(@Nonnull LToByteFunction<? super T> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptByte.of(mapping.applyAsByte(value))) : OptByte.empty();
	}

	public <K> OptByte mapToByte(K a2, @Nonnull LToByteBiFunction<? super T, ? super K> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptByte.of(mapping.applyAsByte(value, a2))) : OptByte.empty();
	}

	public <K> OptByte mapToByteWith(K a1, @Nonnull LToByteBiFunction<? super K, ? super T> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptByte.of(mapping.applyAsByte(a1, value))) : OptByte.empty();
	}

	public OptDbl mapToDbl(@Nonnull LToDblFunction<? super T> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptDbl.of(mapping.applyAsDbl(value))) : OptDbl.empty();
	}

	public <K> OptDbl mapToDbl(K a2, @Nonnull LToDblBiFunction<? super T, ? super K> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptDbl.of(mapping.applyAsDbl(value, a2))) : OptDbl.empty();
	}

	public <K> OptDbl mapToDblWith(K a1, @Nonnull LToDblBiFunction<? super K, ? super T> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptDbl.of(mapping.applyAsDbl(a1, value))) : OptDbl.empty();
	}

	public OptChar mapToChar(@Nonnull LToCharFunction<? super T> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptChar.of(mapping.applyAsChar(value))) : OptChar.empty();
	}

	public <K> OptChar mapToChar(K a2, @Nonnull LToCharBiFunction<? super T, ? super K> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptChar.of(mapping.applyAsChar(value, a2))) : OptChar.empty();
	}

	public <K> OptChar mapToCharWith(K a1, @Nonnull LToCharBiFunction<? super K, ? super T> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptChar.of(mapping.applyAsChar(a1, value))) : OptChar.empty();
	}

	public OptSrt mapToSrt(@Nonnull LToSrtFunction<? super T> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptSrt.of(mapping.applyAsSrt(value))) : OptSrt.empty();
	}

	public <K> OptSrt mapToSrt(K a2, @Nonnull LToSrtBiFunction<? super T, ? super K> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptSrt.of(mapping.applyAsSrt(value, a2))) : OptSrt.empty();
	}

	public <K> OptSrt mapToSrtWith(K a1, @Nonnull LToSrtBiFunction<? super K, ? super T> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptSrt.of(mapping.applyAsSrt(a1, value))) : OptSrt.empty();
	}

	public OptFlt mapToFlt(@Nonnull LToFltFunction<? super T> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptFlt.of(mapping.applyAsFlt(value))) : OptFlt.empty();
	}

	public <K> OptFlt mapToFlt(K a2, @Nonnull LToFltBiFunction<? super T, ? super K> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptFlt.of(mapping.applyAsFlt(value, a2))) : OptFlt.empty();
	}

	public <K> OptFlt mapToFltWith(K a1, @Nonnull LToFltBiFunction<? super K, ? super T> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptFlt.of(mapping.applyAsFlt(a1, value))) : OptFlt.empty();
	}

	public OptInt mapToInt(@Nonnull LToIntFunction<? super T> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptInt.of(mapping.applyAsInt(value))) : OptInt.empty();
	}

	public <K> OptInt mapToInt(K a2, @Nonnull LToIntBiFunction<? super T, ? super K> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptInt.of(mapping.applyAsInt(value, a2))) : OptInt.empty();
	}

	public <K> OptInt mapToIntWith(K a1, @Nonnull LToIntBiFunction<? super K, ? super T> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptInt.of(mapping.applyAsInt(a1, value))) : OptInt.empty();
	}

	public OptLong mapToLong(@Nonnull LToLongFunction<? super T> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptLong.of(mapping.applyAsLong(value))) : OptLong.empty();
	}

	public <K> OptLong mapToLong(K a2, @Nonnull LToLongBiFunction<? super T, ? super K> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptLong.of(mapping.applyAsLong(value, a2))) : OptLong.empty();
	}

	public <K> OptLong mapToLongWith(K a1, @Nonnull LToLongBiFunction<? super K, ? super T> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (OptLong.of(mapping.applyAsLong(a1, value))) : OptLong.empty();
	}

	public <R> Opt<R> mapToObj(@Nonnull LFunction<? super T, ? extends R> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (Opt.of(mapping.apply(value))) : Opt.empty();
	}

	public <R, K> Opt<R> mapToObj(K a2, @Nonnull LBiFunction<? super T, ? super K, ? extends R> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (Opt.of(mapping.apply(value, a2))) : Opt.empty();
	}

	public <R, K> Opt<R> mapToObjWith(K a1, @Nonnull LBiFunction<? super K, ? super T, ? extends R> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? (Opt.of(mapping.apply(a1, value))) : Opt.empty();
	}

	// </editor-fold>

	// <editor-fold desc="flatMap">

	public OptBool flatMapToBool(@Nonnull LFunction<? super T, ? extends OptBool> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? Null.nonNull(mapping.apply(value)) : OptBool.empty();
	}

	public <K> OptBool flatMapToBool(K a2, @Nonnull LBiFunction<? super T, ? super K, ? extends OptBool> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? Null.nonNull(mapping.apply(value, a2)) : OptBool.empty();
	}

	public <K> OptBool flatMapToBoolWith(K a1, @Nonnull LBiFunction<? super K, ? super T, ? extends OptBool> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? Null.nonNull(mapping.apply(a1, value)) : OptBool.empty();
	}

	public OptByte flatMapToByte(@Nonnull LFunction<? super T, ? extends OptByte> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? Null.nonNull(mapping.apply(value)) : OptByte.empty();
	}

	public <K> OptByte flatMapToByte(K a2, @Nonnull LBiFunction<? super T, ? super K, ? extends OptByte> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? Null.nonNull(mapping.apply(value, a2)) : OptByte.empty();
	}

	public <K> OptByte flatMapToByteWith(K a1, @Nonnull LBiFunction<? super K, ? super T, ? extends OptByte> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? Null.nonNull(mapping.apply(a1, value)) : OptByte.empty();
	}

	public OptDbl flatMapToDbl(@Nonnull LFunction<? super T, ? extends OptDbl> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? Null.nonNull(mapping.apply(value)) : OptDbl.empty();
	}

	public <K> OptDbl flatMapToDbl(K a2, @Nonnull LBiFunction<? super T, ? super K, ? extends OptDbl> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? Null.nonNull(mapping.apply(value, a2)) : OptDbl.empty();
	}

	public <K> OptDbl flatMapToDblWith(K a1, @Nonnull LBiFunction<? super K, ? super T, ? extends OptDbl> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? Null.nonNull(mapping.apply(a1, value)) : OptDbl.empty();
	}

	public OptChar flatMapToChar(@Nonnull LFunction<? super T, ? extends OptChar> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? Null.nonNull(mapping.apply(value)) : OptChar.empty();
	}

	public <K> OptChar flatMapToChar(K a2, @Nonnull LBiFunction<? super T, ? super K, ? extends OptChar> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? Null.nonNull(mapping.apply(value, a2)) : OptChar.empty();
	}

	public <K> OptChar flatMapToCharWith(K a1, @Nonnull LBiFunction<? super K, ? super T, ? extends OptChar> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? Null.nonNull(mapping.apply(a1, value)) : OptChar.empty();
	}

	public OptSrt flatMapToSrt(@Nonnull LFunction<? super T, ? extends OptSrt> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? Null.nonNull(mapping.apply(value)) : OptSrt.empty();
	}

	public <K> OptSrt flatMapToSrt(K a2, @Nonnull LBiFunction<? super T, ? super K, ? extends OptSrt> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? Null.nonNull(mapping.apply(value, a2)) : OptSrt.empty();
	}

	public <K> OptSrt flatMapToSrtWith(K a1, @Nonnull LBiFunction<? super K, ? super T, ? extends OptSrt> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? Null.nonNull(mapping.apply(a1, value)) : OptSrt.empty();
	}

	public OptFlt flatMapToFlt(@Nonnull LFunction<? super T, ? extends OptFlt> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? Null.nonNull(mapping.apply(value)) : OptFlt.empty();
	}

	public <K> OptFlt flatMapToFlt(K a2, @Nonnull LBiFunction<? super T, ? super K, ? extends OptFlt> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? Null.nonNull(mapping.apply(value, a2)) : OptFlt.empty();
	}

	public <K> OptFlt flatMapToFltWith(K a1, @Nonnull LBiFunction<? super K, ? super T, ? extends OptFlt> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? Null.nonNull(mapping.apply(a1, value)) : OptFlt.empty();
	}

	public OptInt flatMapToInt(@Nonnull LFunction<? super T, ? extends OptInt> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? Null.nonNull(mapping.apply(value)) : OptInt.empty();
	}

	public <K> OptInt flatMapToInt(K a2, @Nonnull LBiFunction<? super T, ? super K, ? extends OptInt> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? Null.nonNull(mapping.apply(value, a2)) : OptInt.empty();
	}

	public <K> OptInt flatMapToIntWith(K a1, @Nonnull LBiFunction<? super K, ? super T, ? extends OptInt> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? Null.nonNull(mapping.apply(a1, value)) : OptInt.empty();
	}

	public OptLong flatMapToLong(@Nonnull LFunction<? super T, ? extends OptLong> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? Null.nonNull(mapping.apply(value)) : OptLong.empty();
	}

	public <K> OptLong flatMapToLong(K a2, @Nonnull LBiFunction<? super T, ? super K, ? extends OptLong> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? Null.nonNull(mapping.apply(value, a2)) : OptLong.empty();
	}

	public <K> OptLong flatMapToLongWith(K a1, @Nonnull LBiFunction<? super K, ? super T, ? extends OptLong> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? Null.nonNull(mapping.apply(a1, value)) : OptLong.empty();
	}

	public <R> Opt<R> flatMapToObj(@Nonnull LFunction<? super T, ? extends Opt<R>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? Null.nonNull(mapping.apply(value)) : Opt.empty();
	}

	public <R, K> Opt<R> flatMapToObj(K a2, @Nonnull LBiFunction<? super T, ? super K, ? extends Opt<R>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? Null.nonNull(mapping.apply(value, a2)) : Opt.empty();
	}

	public <R, K> Opt<R> flatMapToObjWith(K a1, @Nonnull LBiFunction<? super K, ? super T, ? extends Opt<R>> mapping) {
		Null.nonNullArg(mapping, "mapping");
		return isPresent() ? Null.nonNull(mapping.apply(a1, value)) : Opt.empty();
	}

	// </editor-fold>

	// <editor-fold desc="ifPresent">

	public Opt<T> ifPresent(@Nonnull LConsumer<? super T> action) {
		Null.nonNullArg(action, "action");
		if (isPresent()) {
			action.accept(value);
		}
		return this;
	}

	public Opt<T> ifPresent(@Nonnull LConsumer<? super T> action, @Nonnull LAction emptyAction) {
		Null.nonNullArg(action, "action");
		if (isPresent()) {
			action.accept(value);
		} else {
			emptyAction.execute();
		}
		return this;
	}

	public <K> Opt<T> ifPresent(K a1, @Nonnull LBiConsumer<? super T, ? super K> action) {
		Null.nonNullArg(action, "action");
		if (isPresent()) {
			action.accept(value, a1);
		}
		return this;
	}

	public <K> Opt<T> ifPresentWith(K a1, @Nonnull LBiConsumer<? super K, ? super T> action) {
		Null.nonNullArg(action, "action");
		if (isPresent()) {
			action.accept(a1, value);
		}
		return this;
	}

	// </editor-fold>

	// <editor-fold desc="orElse">

	public T orElse(T other) {
		return isPresent() ? value : other;
	}

	public T orElseThrow() {
		if (isPresent()) {
			return value;
		}

		throw Handling.create(X::noSuchElement);
	}

	public T orElseThrow(@Nonnull ExF<RuntimeException> fx) {
		if (isPresent()) {
			return value;
		}

		Null.nonNullArg(fx, "fx");
		throw Handling.create(fx);
	}

	public T orElseThrow(@Nonnull ExMF<RuntimeException> fx, @Nullable String msg) {
		if (isPresent()) {
			return value;
		}

		Null.nonNullArg(fx, "fx");
		throw Handling.create(fx, msg);
	}

	public T orElse(@Nonnull LSupplier<? extends T> supplier) {
		Null.nonNullArg(supplier, "supplier");
		return isPresent() ? value : supplier.get();
	}

	public <K> T orElse(K a1, @Nonnull LFunction<? super K, ? extends T> supplier) {
		Null.nonNullArg(supplier, "supplier");
		return isPresent() ? value : supplier.apply(a1);
	}

	// </editor-fold>

	// <editor-fold desc="equals/hashcode/toString">

	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof Opt)) {
			return false;
		}

		Opt other = (Opt) obj;
		return (isPresent() && other.isPresent()) ? value.equals(other.value) : isPresent() == other.isPresent();
	}

	public int hashCode() {
		return Objects.hashCode(value);
	}

	public String toString() {
		return isPresent() ? String.format("Opt[%s]", value) : "Opt.empty";
	}

	// </editor-fold>

}
