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
import javax.annotation.concurrent.ThreadSafe; // NOSONAR
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
@ThreadSafe
public final class OptChar implements OptCharTrait<OptChar> {

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

	public @Nonnull OptChar value(char value) {
		return of(value);
	}

	public @Nonnull OptChar voidValue() {
		return empty();
	}

	public static OptChar empty() {
		return EMPTY;
	}

	public static OptChar toOpt(@Nonnull OptCharTrait<?> opt) {
		Null.nonNullArg(opt, "opt");
		return Clazz.assuredClass(OptChar.class, opt, o -> o.isPresent() ? of(o.get()) : empty());
	}

	public static OptChar of(char value) {
		return new OptChar(value);
	}

	public static OptChar valueOf(char value) {
		return of(value);
	}

	// </editor-fold>

	public char get() {
		LLogicalOperator.throwIfNot(isPresent, Is::True, X::noSuchElement, "No value present.");
		return value;
	}

	public final boolean isPresent() {
		return isPresent;
	}

	public final boolean isVoid() {
		return !isPresent;
	}

	public OptionalInt toOpt() {
		if (isPresent()) {
			return OptionalInt.of(value);
		} else {
			return OptionalInt.empty();
		}
	}

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
