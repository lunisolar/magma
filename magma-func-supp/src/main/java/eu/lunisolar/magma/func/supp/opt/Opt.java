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
public final class Opt<T> implements OptTrait<T, Opt<T>> {

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

	public static <T> Opt<T> empty() {
		return (Opt) EMPTY;
	}

	public static <T> Opt<T> toOpt(@Nonnull OptTrait<? extends T, ?> opt) {
		Null.nonNullArg(opt, "opt");
		return Clazz.assuredClass(Opt.class, opt, o -> o.isPresent() ? of(o.get()) : empty());
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

	public static OptByte valueOf(byte value) {
		return of(value);
	}

	public static OptSrt of(short value) {
		return OptSrt.of(value);
	}

	public static OptSrt valueOf(short value) {
		return of(value);
	}

	public static OptInt of(int value) {
		return OptInt.of(value);
	}

	public static OptInt valueOf(int value) {
		return of(value);
	}

	public static OptLong of(long value) {
		return OptLong.of(value);
	}

	public static OptLong valueOf(long value) {
		return of(value);
	}

	public static OptFlt of(float value) {
		return OptFlt.of(value);
	}

	public static OptFlt valueOf(float value) {
		return of(value);
	}

	public static OptDbl of(double value) {
		return OptDbl.of(value);
	}

	public static OptDbl valueOf(double value) {
		return of(value);
	}

	public static OptChar of(char value) {
		return OptChar.of(value);
	}

	public static OptChar valueOf(char value) {
		return of(value);
	}

	public static OptBool of(boolean value) {
		return OptBool.of(value);
	}

	public static OptBool valueOf(boolean value) {
		return of(value);
	}

	public static <T> Opt<T> toOpt(@Nonnull Optional<T> optional) {
		Null.nonNullArg(optional, "optional");
		return optional.isPresent() ? Opt.of(optional.get()) : empty();
	}

	// </editor-fold>

	public @Nullable T nullable() {
		return value;
	}

	public final boolean isVoid() {
		return value == null;
	}

	public Optional<T> toOpt() {
		if (isPresent()) {
			return Optional.of(value);
		} else {
			return Optional.empty();
		}
	}

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
