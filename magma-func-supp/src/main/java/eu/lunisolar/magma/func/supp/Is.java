/*
 * This file is part of "lunisolar-magma".
 *
 * (C) Copyright 2014-2022 Lunisolar (http://lunisolar.eu/).
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
import java.lang.reflect.*; // NOSONAR
import java.util.*; // NOSONAR
import java.util.Objects; // NOSONAR
import eu.lunisolar.magma.basics.*; // NOSONAR
import eu.lunisolar.magma.basics.builder.*; // NOSONAR
import eu.lunisolar.magma.basics.exceptions.*; // NOSONAR
import eu.lunisolar.magma.basics.meta.*; // NOSONAR
import eu.lunisolar.magma.basics.meta.functional.*; // NOSONAR
import eu.lunisolar.magma.basics.meta.functional.type.*; // NOSONAR
import eu.lunisolar.magma.basics.meta.functional.domain.*; // NOSONAR
import eu.lunisolar.magma.func.*; // NOSONAR
import eu.lunisolar.magma.func.supp.opt.*; // NOSONAR
import eu.lunisolar.magma.func.supp.value.*; // NOSONAR
import eu.lunisolar.magma.func.tuple.*; // NOSONAR
import eu.lunisolar.magma.basics.fluent.*; //NOSONAR

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
 * Common predicates (methods to reference)
 *
 * - filter({@link P}::equal, 4)
 * - when({@link Does}::contain, 4)
 * - must({@link Be}::equal, 4)
 * - when({@link Is}::equal, 4) *
 * @see {@link P}, {@link Is}, {@link Does}, {@link Be}, {@link Are}
 */
public final class Is implements FluentSyntax {
	// <editor-fold desc="no instance">
	private Is() {
	}
	// </editor-fold>

	// <editor-fold desc="Object">

	/** Predicate: Object <%s> must be the same as <%s>. Available in {@link P}, {@link Is}, {@link Be}, {@link P1}, {@link P2}.*/
	public static boolean same(@Nullable Object n, @Nullable Object other) {
		return P.same(n, other);
	}

	/** "Special" predicate: Object <%s> must be the same as <%s>. Available in {@link P}, {@link Is}, {@link Be}, {@link P1}, {@link P2}.*/
	public static @Nullable String sameEx(@Nullable Object n, @Nullable Object other) {
		return P.sameEx(n, other);
	}
	/** Predicate: Object <%s> must NOT be the same as <%s>. Available in {@link P}, {@link Is}, {@link Be}, {@link P1}, {@link P2}.*/
	public static boolean notSame(@Nullable Object n, @Nullable Object other) {
		return P.notSame(n, other);
	}

	/** "Special" predicate: Object <%s> must NOT be the same as <%s>. Available in {@link P}, {@link Is}, {@link Be}, {@link P1}, {@link P2}.*/
	public static @Nullable String notSameEx(@Nullable Object n, @Nullable Object other) {
		return P.notSameEx(n, other);
	}

	/** Predicate: Reference must be null, currently is pointing to <%s>. Available in {@link P}, {@link Is}, {@link Be}, {@link P1}, {@link P2}.*/
	public static boolean Null(@Nullable Object n) {
		return P.Null(n);
	}

	/** "Special" predicate: Reference must be null, currently is pointing to <%s>. Available in {@link P}, {@link Is}, {@link Be}, {@link P1}, {@link P2}.*/
	public static @Nullable String NullEx(@Nullable Object n) {
		return P.NullEx(n);
	}
	/** Predicate: Reference must NOT be null, currently is pointing to <%s>. Available in {@link P}, {@link Is}, {@link Be}, {@link P1}, {@link P2}.*/
	public static boolean notNull(@Nullable Object n) {
		return P.notNull(n);
	}

	/** "Special" predicate: Reference must NOT be null, currently is pointing to <%s>. Available in {@link P}, {@link Is}, {@link Be}, {@link P1}, {@link P2}.*/
	public static @Nullable String notNullEx(@Nullable Object n) {
		return P.notNullEx(n);
	}

	/** Predicate: All references must be null. Available in {@link P}, {@link Is}, {@link Are}, {@link Be}, {@link Has}.*/
	public static boolean allNull(@Nullable Object... objects) {
		return P.allNull(objects);
	}

	/** "Special" predicate: All references must be null. Available in {@link P}, {@link Is}, {@link Are}, {@link Be}, {@link Has}.*/
	public static @Nullable String allNullEx(@Nullable Object... objects) {
		return P.allNullEx(objects);
	}
	/** Predicate: All references must be NOT null. Available in {@link P}, {@link Is}, {@link Are}, {@link Be}, {@link Has}.*/
	public static boolean noneNull(@Nullable Object... objects) {
		return P.noneNull(objects);
	}

	/** "Special" predicate: All references must be NOT null. Available in {@link P}, {@link Is}, {@link Are}, {@link Be}, {@link Has}.*/
	public static @Nullable String noneNullEx(@Nullable Object... objects) {
		return P.noneNullEx(objects);
	}

	/** Predicate: At least one references must be null. Available in {@link P}, {@link Is}, {@link Are}, {@link Be}, {@link Has}.*/
	public static boolean anyNull(@Nullable Object... objects) {
		return P.anyNull(objects);
	}

	/** "Special" predicate: At least one references must be null. Available in {@link P}, {@link Is}, {@link Are}, {@link Be}, {@link Has}.*/
	public static @Nullable String anyNullEx(@Nullable Object... objects) {
		return P.anyNullEx(objects);
	}
	/** Predicate: At least one references must be NOT null. Available in {@link P}, {@link Is}, {@link Are}, {@link Be}, {@link Has}.*/
	public static boolean anyNotNull(@Nullable Object... objects) {
		return P.anyNotNull(objects);
	}

	/** "Special" predicate: At least one references must be NOT null. Available in {@link P}, {@link Is}, {@link Are}, {@link Be}, {@link Has}.*/
	public static @Nullable String anyNotNullEx(@Nullable Object... objects) {
		return P.anyNotNullEx(objects);
	}

	// </editor-fold>

	// <editor-fold desc="`String`">

	/** Predicate: String <'%s'> must be <%d> characters long. Available in {@link P}, {@link Has}, {@link Have}, {@link Is}, {@link Be}.*/
	public static boolean ofLength(@Nonnull CharSequence s, int size) {
		Null.nonNullArg(s, "s");
		return P.length(s, size);
	}

	/** "Special" predicate: String <'%s'> must be <%d> characters long. Available in {@link P}, {@link Has}, {@link Have}, {@link Is}, {@link Be}.*/
	public static @Nullable String ofLengthEx(@Nonnull CharSequence s, int size) {
		Null.nonNullArg(s, "s");
		return P.lengthEx(s, size);
	}
	/** Predicate: String <'%s'> must NOT be <%d> characters long. Available in {@link P}, {@link Has}, {@link Have}, {@link Is}, {@link Be}.*/
	public static boolean notOfLength(@Nonnull CharSequence s, int size) {
		Null.nonNullArg(s, "s");
		return P.lengthOtherThan(s, size);
	}

	/** "Special" predicate: String <'%s'> must NOT be <%d> characters long. Available in {@link P}, {@link Has}, {@link Have}, {@link Is}, {@link Be}.*/
	public static @Nullable String notOfLengthEx(@Nonnull CharSequence s, int size) {
		Null.nonNullArg(s, "s");
		return P.lengthOtherThanEx(s, size);
	}

	/** Predicate: String <'%s'> must be empty. Available in {@link P}, {@link Is}, {@link Be}, {@link P1}, {@link P2}.*/
	public static boolean empty(@Nonnull String n) {
		Null.nonNullArg(n, "n");
		return P.empty(n);
	}

	/** "Special" predicate: String <'%s'> must be empty. Available in {@link P}, {@link Is}, {@link Be}, {@link P1}, {@link P2}.*/
	public static @Nullable String emptyEx(@Nonnull String n) {
		Null.nonNullArg(n, "n");
		return P.emptyEx(n);
	}
	/** Predicate: String <'%s'> must NOT be empty. Available in {@link P}, {@link Is}, {@link Be}, {@link P1}, {@link P2}.*/
	public static boolean notEmpty(@Nonnull String n) {
		Null.nonNullArg(n, "n");
		return P.notEmpty(n);
	}

	/** "Special" predicate: String <'%s'> must NOT be empty. Available in {@link P}, {@link Is}, {@link Be}, {@link P1}, {@link P2}.*/
	public static @Nullable String notEmptyEx(@Nonnull String n) {
		Null.nonNullArg(n, "n");
		return P.notEmptyEx(n);
	}

	/** Predicate: String <'%s'> must be blank (empty or consisting of only white characters). Available in {@link P}, {@link Is}, {@link Be}, {@link P1}, {@link P2}.*/
	public static boolean blank(@Nonnull String n) {
		Null.nonNullArg(n, "n");
		return P.blank(n);
	}

	/** "Special" predicate: String <'%s'> must be blank (empty or consisting of only white characters). Available in {@link P}, {@link Is}, {@link Be}, {@link P1}, {@link P2}.*/
	public static @Nullable String blankEx(@Nonnull String n) {
		Null.nonNullArg(n, "n");
		return P.blankEx(n);
	}
	/** Predicate: String <'%s'> must NOT be blank (empty or consisting of only white characters). Available in {@link P}, {@link Is}, {@link Be}, {@link P1}, {@link P2}.*/
	public static boolean notBlank(@Nonnull String n) {
		Null.nonNullArg(n, "n");
		return P.notBlank(n);
	}

	/** "Special" predicate: String <'%s'> must NOT be blank (empty or consisting of only white characters). Available in {@link P}, {@link Is}, {@link Be}, {@link P1}, {@link P2}.*/
	public static @Nullable String notBlankEx(@Nonnull String n) {
		Null.nonNullArg(n, "n");
		return P.notBlankEx(n);
	}

	/** Predicate: String <'%s'> must be null or empty. Available in {@link P}, {@link Is}, {@link Be}, {@link P1}, {@link P2}.*/
	public static boolean nullOrEmpty(@Nonnull String n) {
		return P.nullOrEmpty(n);
	}

	/** "Special" predicate: String <'%s'> must be null or empty. Available in {@link P}, {@link Is}, {@link Be}, {@link P1}, {@link P2}.*/
	public static @Nullable String nullOrEmptyEx(@Nonnull String n) {
		return P.nullOrEmptyEx(n);
	}
	/** Predicate: String <'%s'> must NOT be null or empty. Available in {@link P}, {@link Is}, {@link Be}, {@link P1}, {@link P2}.*/
	public static boolean notNullNorEmpty(@Nonnull String n) {
		return P.notNullNorEmpty(n);
	}

	/** "Special" predicate: String <'%s'> must NOT be null or empty. Available in {@link P}, {@link Is}, {@link Be}, {@link P1}, {@link P2}.*/
	public static @Nullable String notNullNorEmptyEx(@Nonnull String n) {
		return P.notNullNorEmptyEx(n);
	}

	/** Predicate: String <'%s'> must be null or blank. Available in {@link P}, {@link Is}, {@link Be}, {@link P1}, {@link P2}.*/
	public static boolean nullOrBlank(@Nonnull String n) {
		return P.nullOrBlank(n);
	}

	/** "Special" predicate: String <'%s'> must be null or blank. Available in {@link P}, {@link Is}, {@link Be}, {@link P1}, {@link P2}.*/
	public static @Nullable String nullOrBlankEx(@Nonnull String n) {
		return P.nullOrBlankEx(n);
	}
	/** Predicate: String <'%s'> must NOT be null or blank. Available in {@link P}, {@link Is}, {@link Be}, {@link P1}, {@link P2}.*/
	public static boolean notNullNorBlank(@Nonnull String n) {
		return P.notNullNorBlank(n);
	}

	/** "Special" predicate: String <'%s'> must NOT be null or blank. Available in {@link P}, {@link Is}, {@link Be}, {@link P1}, {@link P2}.*/
	public static @Nullable String notNullNorBlankEx(@Nonnull String n) {
		return P.notNullNorBlankEx(n);
	}

	// </editor-fold>

	// <editor-fold desc="and/or/xor">

	// </editor-fold>

	// <editor-fold desc="== <= >= < >">

	/** Predicate: <%s> must be equal to <%s>. Available in {@link P}, {@link Is}, {@link Be}, {@link Does}.*/
	public static boolean equal(Object o1, Object o2) {
		return P.equal(o1, o2);
	}

	/** "Special" predicate: <%s> must be equal to <%s>. Available in {@link P}, {@link Is}, {@link Be}, {@link Does}.*/
	public static @Nullable String equalEx(Object o1, Object o2) {
		return P.equalEx(o1, o2);
	}
	/** Predicate: <%s> must NOT be equal to <%s>. Available in {@link P}, {@link Is}, {@link Be}, {@link Does}.*/
	public static boolean notEqual(Object o1, Object o2) {
		return P.notEqual(o1, o2);
	}

	/** "Special" predicate: <%s> must NOT be equal to <%s>. Available in {@link P}, {@link Is}, {@link Be}, {@link Does}.*/
	public static @Nullable String notEqualEx(Object o1, Object o2) {
		return P.notEqualEx(o1, o2);
	}

	/** Predicate: %s must be equal to %s. Available in {@link P}, {@link Is}, {@link Be}, {@link Does}.*/
	public static boolean equal(byte n, byte a1) {
		return P.equal(n, a1);
	}

	/** "Special" predicate: %s must be equal to %s. Available in {@link P}, {@link Is}, {@link Be}, {@link Does}.*/
	public static @Nullable String equalEx(byte n, byte a1) {
		return P.equalEx(n, a1);
	}
	/** Predicate: %s must NOT be equal to %s. Available in {@link P}, {@link Is}, {@link Be}, {@link Does}.*/
	public static boolean notEqual(byte n, byte a1) {
		return P.notEqual(n, a1);
	}

	/** "Special" predicate: %s must NOT be equal to %s. Available in {@link P}, {@link Is}, {@link Be}, {@link Does}.*/
	public static @Nullable String notEqualEx(byte n, byte a1) {
		return P.notEqualEx(n, a1);
	}

	/** Predicate: %s must be > %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean gt(byte n, byte a1) {
		return P.gt(n, a1);
	}

	/** "Special" predicate: %s must be > %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static @Nullable String gtEx(byte n, byte a1) {
		return P.gtEx(n, a1);
	}
	/** Predicate: %s must NOT be > %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean notGt(byte n, byte a1) {
		return P.notGt(n, a1);
	}

	/** "Special" predicate: %s must NOT be > %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static @Nullable String notGtEx(byte n, byte a1) {
		return P.notGtEx(n, a1);
	}

	/** Predicate: %s must be < %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean lt(byte n, byte a1) {
		return P.lt(n, a1);
	}

	/** "Special" predicate: %s must be < %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static @Nullable String ltEx(byte n, byte a1) {
		return P.ltEx(n, a1);
	}
	/** Predicate: %s must NOT be < %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean notLt(byte n, byte a1) {
		return P.notLt(n, a1);
	}

	/** "Special" predicate: %s must NOT be < %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static @Nullable String notLtEx(byte n, byte a1) {
		return P.notLtEx(n, a1);
	}

	/** Predicate: %s must be >= %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean gtEq(byte n, byte a1) {
		return P.gtEq(n, a1);
	}

	/** "Special" predicate: %s must be >= %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static @Nullable String gtEqEx(byte n, byte a1) {
		return P.gtEqEx(n, a1);
	}
	/** Predicate: %s must NOT be >= %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean notGtEq(byte n, byte a1) {
		return P.notGtEq(n, a1);
	}

	/** "Special" predicate: %s must NOT be >= %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static @Nullable String notGtEqEx(byte n, byte a1) {
		return P.notGtEqEx(n, a1);
	}

	/** Predicate: %s must be <= %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean ltEq(byte n, byte a1) {
		return P.ltEq(n, a1);
	}

	/** "Special" predicate: %s must be <= %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static @Nullable String ltEqEx(byte n, byte a1) {
		return P.ltEqEx(n, a1);
	}
	/** Predicate: %s must NOT be <= %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean notLtEq(byte n, byte a1) {
		return P.notLtEq(n, a1);
	}

	/** "Special" predicate: %s must NOT be <= %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static @Nullable String notLtEqEx(byte n, byte a1) {
		return P.notLtEqEx(n, a1);
	}

	/** Predicate: %s must be >= 0 (must be non-negative). Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean nonNegative(byte n) {
		return P.nonNegative(n);
	}

	/** "Special" predicate: %s must be >= 0 (must be non-negative). Available in {@link P}, {@link Is}, {@link Be}.*/
	public static @Nullable String nonNegativeEx(byte n) {
		return P.nonNegativeEx(n);
	}
	/** Predicate: %s must NOT be >= 0 (must be negative). Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean negative(byte n) {
		return P.negative(n);
	}

	/** "Special" predicate: %s must NOT be >= 0 (must be negative). Available in {@link P}, {@link Is}, {@link Be}.*/
	public static @Nullable String negativeEx(byte n) {
		return P.negativeEx(n);
	}

	/** Predicate: %s must be <= 0 (must be non-positive). Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean nonPositive(byte n) {
		return P.nonPositive(n);
	}

	/** "Special" predicate: %s must be <= 0 (must be non-positive). Available in {@link P}, {@link Is}, {@link Be}.*/
	public static @Nullable String nonPositiveEx(byte n) {
		return P.nonPositiveEx(n);
	}
	/** Predicate: %s must NOT be <= 0 (must be positive). Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean positive(byte n) {
		return P.positive(n);
	}

	/** "Special" predicate: %s must NOT be <= 0 (must be positive). Available in {@link P}, {@link Is}, {@link Be}.*/
	public static @Nullable String positiveEx(byte n) {
		return P.positiveEx(n);
	}

	/** Predicate: %s must be equal to %s. Available in {@link P}, {@link Is}, {@link Be}, {@link Does}.*/
	public static boolean equal(short n, short a1) {
		return P.equal(n, a1);
	}

	/** "Special" predicate: %s must be equal to %s. Available in {@link P}, {@link Is}, {@link Be}, {@link Does}.*/
	public static @Nullable String equalEx(short n, short a1) {
		return P.equalEx(n, a1);
	}
	/** Predicate: %s must NOT be equal to %s. Available in {@link P}, {@link Is}, {@link Be}, {@link Does}.*/
	public static boolean notEqual(short n, short a1) {
		return P.notEqual(n, a1);
	}

	/** "Special" predicate: %s must NOT be equal to %s. Available in {@link P}, {@link Is}, {@link Be}, {@link Does}.*/
	public static @Nullable String notEqualEx(short n, short a1) {
		return P.notEqualEx(n, a1);
	}

	/** Predicate: %s must be > %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean gt(short n, short a1) {
		return P.gt(n, a1);
	}

	/** "Special" predicate: %s must be > %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static @Nullable String gtEx(short n, short a1) {
		return P.gtEx(n, a1);
	}
	/** Predicate: %s must NOT be > %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean notGt(short n, short a1) {
		return P.notGt(n, a1);
	}

	/** "Special" predicate: %s must NOT be > %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static @Nullable String notGtEx(short n, short a1) {
		return P.notGtEx(n, a1);
	}

	/** Predicate: %s must be < %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean lt(short n, short a1) {
		return P.lt(n, a1);
	}

	/** "Special" predicate: %s must be < %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static @Nullable String ltEx(short n, short a1) {
		return P.ltEx(n, a1);
	}
	/** Predicate: %s must NOT be < %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean notLt(short n, short a1) {
		return P.notLt(n, a1);
	}

	/** "Special" predicate: %s must NOT be < %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static @Nullable String notLtEx(short n, short a1) {
		return P.notLtEx(n, a1);
	}

	/** Predicate: %s must be >= %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean gtEq(short n, short a1) {
		return P.gtEq(n, a1);
	}

	/** "Special" predicate: %s must be >= %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static @Nullable String gtEqEx(short n, short a1) {
		return P.gtEqEx(n, a1);
	}
	/** Predicate: %s must NOT be >= %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean notGtEq(short n, short a1) {
		return P.notGtEq(n, a1);
	}

	/** "Special" predicate: %s must NOT be >= %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static @Nullable String notGtEqEx(short n, short a1) {
		return P.notGtEqEx(n, a1);
	}

	/** Predicate: %s must be <= %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean ltEq(short n, short a1) {
		return P.ltEq(n, a1);
	}

	/** "Special" predicate: %s must be <= %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static @Nullable String ltEqEx(short n, short a1) {
		return P.ltEqEx(n, a1);
	}
	/** Predicate: %s must NOT be <= %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean notLtEq(short n, short a1) {
		return P.notLtEq(n, a1);
	}

	/** "Special" predicate: %s must NOT be <= %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static @Nullable String notLtEqEx(short n, short a1) {
		return P.notLtEqEx(n, a1);
	}

	/** Predicate: %s must be >= 0 (must be non-negative). Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean nonNegative(short n) {
		return P.nonNegative(n);
	}

	/** "Special" predicate: %s must be >= 0 (must be non-negative). Available in {@link P}, {@link Is}, {@link Be}.*/
	public static @Nullable String nonNegativeEx(short n) {
		return P.nonNegativeEx(n);
	}
	/** Predicate: %s must NOT be >= 0 (must be negative). Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean negative(short n) {
		return P.negative(n);
	}

	/** "Special" predicate: %s must NOT be >= 0 (must be negative). Available in {@link P}, {@link Is}, {@link Be}.*/
	public static @Nullable String negativeEx(short n) {
		return P.negativeEx(n);
	}

	/** Predicate: %s must be <= 0 (must be non-positive). Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean nonPositive(short n) {
		return P.nonPositive(n);
	}

	/** "Special" predicate: %s must be <= 0 (must be non-positive). Available in {@link P}, {@link Is}, {@link Be}.*/
	public static @Nullable String nonPositiveEx(short n) {
		return P.nonPositiveEx(n);
	}
	/** Predicate: %s must NOT be <= 0 (must be positive). Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean positive(short n) {
		return P.positive(n);
	}

	/** "Special" predicate: %s must NOT be <= 0 (must be positive). Available in {@link P}, {@link Is}, {@link Be}.*/
	public static @Nullable String positiveEx(short n) {
		return P.positiveEx(n);
	}

	/** Predicate: %s must be equal to %s. Available in {@link P}, {@link Is}, {@link Be}, {@link Does}.*/
	public static boolean equal(int n, int a1) {
		return P.equal(n, a1);
	}

	/** "Special" predicate: %s must be equal to %s. Available in {@link P}, {@link Is}, {@link Be}, {@link Does}.*/
	public static @Nullable String equalEx(int n, int a1) {
		return P.equalEx(n, a1);
	}
	/** Predicate: %s must NOT be equal to %s. Available in {@link P}, {@link Is}, {@link Be}, {@link Does}.*/
	public static boolean notEqual(int n, int a1) {
		return P.notEqual(n, a1);
	}

	/** "Special" predicate: %s must NOT be equal to %s. Available in {@link P}, {@link Is}, {@link Be}, {@link Does}.*/
	public static @Nullable String notEqualEx(int n, int a1) {
		return P.notEqualEx(n, a1);
	}

	/** Predicate: %s must be > %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean gt(int n, int a1) {
		return P.gt(n, a1);
	}

	/** "Special" predicate: %s must be > %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static @Nullable String gtEx(int n, int a1) {
		return P.gtEx(n, a1);
	}
	/** Predicate: %s must NOT be > %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean notGt(int n, int a1) {
		return P.notGt(n, a1);
	}

	/** "Special" predicate: %s must NOT be > %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static @Nullable String notGtEx(int n, int a1) {
		return P.notGtEx(n, a1);
	}

	/** Predicate: %s must be < %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean lt(int n, int a1) {
		return P.lt(n, a1);
	}

	/** "Special" predicate: %s must be < %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static @Nullable String ltEx(int n, int a1) {
		return P.ltEx(n, a1);
	}
	/** Predicate: %s must NOT be < %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean notLt(int n, int a1) {
		return P.notLt(n, a1);
	}

	/** "Special" predicate: %s must NOT be < %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static @Nullable String notLtEx(int n, int a1) {
		return P.notLtEx(n, a1);
	}

	/** Predicate: %s must be >= %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean gtEq(int n, int a1) {
		return P.gtEq(n, a1);
	}

	/** "Special" predicate: %s must be >= %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static @Nullable String gtEqEx(int n, int a1) {
		return P.gtEqEx(n, a1);
	}
	/** Predicate: %s must NOT be >= %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean notGtEq(int n, int a1) {
		return P.notGtEq(n, a1);
	}

	/** "Special" predicate: %s must NOT be >= %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static @Nullable String notGtEqEx(int n, int a1) {
		return P.notGtEqEx(n, a1);
	}

	/** Predicate: %s must be <= %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean ltEq(int n, int a1) {
		return P.ltEq(n, a1);
	}

	/** "Special" predicate: %s must be <= %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static @Nullable String ltEqEx(int n, int a1) {
		return P.ltEqEx(n, a1);
	}
	/** Predicate: %s must NOT be <= %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean notLtEq(int n, int a1) {
		return P.notLtEq(n, a1);
	}

	/** "Special" predicate: %s must NOT be <= %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static @Nullable String notLtEqEx(int n, int a1) {
		return P.notLtEqEx(n, a1);
	}

	/** Predicate: %s must be >= 0 (must be non-negative). Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean nonNegative(int n) {
		return P.nonNegative(n);
	}

	/** "Special" predicate: %s must be >= 0 (must be non-negative). Available in {@link P}, {@link Is}, {@link Be}.*/
	public static @Nullable String nonNegativeEx(int n) {
		return P.nonNegativeEx(n);
	}
	/** Predicate: %s must NOT be >= 0 (must be negative). Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean negative(int n) {
		return P.negative(n);
	}

	/** "Special" predicate: %s must NOT be >= 0 (must be negative). Available in {@link P}, {@link Is}, {@link Be}.*/
	public static @Nullable String negativeEx(int n) {
		return P.negativeEx(n);
	}

	/** Predicate: %s must be <= 0 (must be non-positive). Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean nonPositive(int n) {
		return P.nonPositive(n);
	}

	/** "Special" predicate: %s must be <= 0 (must be non-positive). Available in {@link P}, {@link Is}, {@link Be}.*/
	public static @Nullable String nonPositiveEx(int n) {
		return P.nonPositiveEx(n);
	}
	/** Predicate: %s must NOT be <= 0 (must be positive). Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean positive(int n) {
		return P.positive(n);
	}

	/** "Special" predicate: %s must NOT be <= 0 (must be positive). Available in {@link P}, {@link Is}, {@link Be}.*/
	public static @Nullable String positiveEx(int n) {
		return P.positiveEx(n);
	}

	/** Predicate: %s must be equal to %s. Available in {@link P}, {@link Is}, {@link Be}, {@link Does}.*/
	public static boolean equal(long n, long a1) {
		return P.equal(n, a1);
	}

	/** "Special" predicate: %s must be equal to %s. Available in {@link P}, {@link Is}, {@link Be}, {@link Does}.*/
	public static @Nullable String equalEx(long n, long a1) {
		return P.equalEx(n, a1);
	}
	/** Predicate: %s must NOT be equal to %s. Available in {@link P}, {@link Is}, {@link Be}, {@link Does}.*/
	public static boolean notEqual(long n, long a1) {
		return P.notEqual(n, a1);
	}

	/** "Special" predicate: %s must NOT be equal to %s. Available in {@link P}, {@link Is}, {@link Be}, {@link Does}.*/
	public static @Nullable String notEqualEx(long n, long a1) {
		return P.notEqualEx(n, a1);
	}

	/** Predicate: %s must be > %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean gt(long n, long a1) {
		return P.gt(n, a1);
	}

	/** "Special" predicate: %s must be > %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static @Nullable String gtEx(long n, long a1) {
		return P.gtEx(n, a1);
	}
	/** Predicate: %s must NOT be > %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean notGt(long n, long a1) {
		return P.notGt(n, a1);
	}

	/** "Special" predicate: %s must NOT be > %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static @Nullable String notGtEx(long n, long a1) {
		return P.notGtEx(n, a1);
	}

	/** Predicate: %s must be < %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean lt(long n, long a1) {
		return P.lt(n, a1);
	}

	/** "Special" predicate: %s must be < %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static @Nullable String ltEx(long n, long a1) {
		return P.ltEx(n, a1);
	}
	/** Predicate: %s must NOT be < %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean notLt(long n, long a1) {
		return P.notLt(n, a1);
	}

	/** "Special" predicate: %s must NOT be < %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static @Nullable String notLtEx(long n, long a1) {
		return P.notLtEx(n, a1);
	}

	/** Predicate: %s must be >= %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean gtEq(long n, long a1) {
		return P.gtEq(n, a1);
	}

	/** "Special" predicate: %s must be >= %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static @Nullable String gtEqEx(long n, long a1) {
		return P.gtEqEx(n, a1);
	}
	/** Predicate: %s must NOT be >= %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean notGtEq(long n, long a1) {
		return P.notGtEq(n, a1);
	}

	/** "Special" predicate: %s must NOT be >= %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static @Nullable String notGtEqEx(long n, long a1) {
		return P.notGtEqEx(n, a1);
	}

	/** Predicate: %s must be <= %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean ltEq(long n, long a1) {
		return P.ltEq(n, a1);
	}

	/** "Special" predicate: %s must be <= %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static @Nullable String ltEqEx(long n, long a1) {
		return P.ltEqEx(n, a1);
	}
	/** Predicate: %s must NOT be <= %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean notLtEq(long n, long a1) {
		return P.notLtEq(n, a1);
	}

	/** "Special" predicate: %s must NOT be <= %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static @Nullable String notLtEqEx(long n, long a1) {
		return P.notLtEqEx(n, a1);
	}

	/** Predicate: %s must be >= 0 (must be non-negative). Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean nonNegative(long n) {
		return P.nonNegative(n);
	}

	/** "Special" predicate: %s must be >= 0 (must be non-negative). Available in {@link P}, {@link Is}, {@link Be}.*/
	public static @Nullable String nonNegativeEx(long n) {
		return P.nonNegativeEx(n);
	}
	/** Predicate: %s must NOT be >= 0 (must be negative). Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean negative(long n) {
		return P.negative(n);
	}

	/** "Special" predicate: %s must NOT be >= 0 (must be negative). Available in {@link P}, {@link Is}, {@link Be}.*/
	public static @Nullable String negativeEx(long n) {
		return P.negativeEx(n);
	}

	/** Predicate: %s must be <= 0 (must be non-positive). Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean nonPositive(long n) {
		return P.nonPositive(n);
	}

	/** "Special" predicate: %s must be <= 0 (must be non-positive). Available in {@link P}, {@link Is}, {@link Be}.*/
	public static @Nullable String nonPositiveEx(long n) {
		return P.nonPositiveEx(n);
	}
	/** Predicate: %s must NOT be <= 0 (must be positive). Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean positive(long n) {
		return P.positive(n);
	}

	/** "Special" predicate: %s must NOT be <= 0 (must be positive). Available in {@link P}, {@link Is}, {@link Be}.*/
	public static @Nullable String positiveEx(long n) {
		return P.positiveEx(n);
	}

	/** Predicate: %s must be equal to %s. Available in {@link P}, {@link Is}, {@link Be}, {@link Does}.*/
	public static boolean equal(float n, float a1) {
		return P.equal(n, a1);
	}

	/** "Special" predicate: %s must be equal to %s. Available in {@link P}, {@link Is}, {@link Be}, {@link Does}.*/
	public static @Nullable String equalEx(float n, float a1) {
		return P.equalEx(n, a1);
	}
	/** Predicate: %s must NOT be equal to %s. Available in {@link P}, {@link Is}, {@link Be}, {@link Does}.*/
	public static boolean notEqual(float n, float a1) {
		return P.notEqual(n, a1);
	}

	/** "Special" predicate: %s must NOT be equal to %s. Available in {@link P}, {@link Is}, {@link Be}, {@link Does}.*/
	public static @Nullable String notEqualEx(float n, float a1) {
		return P.notEqualEx(n, a1);
	}

	/** Predicate: %s must be > %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean gt(float n, float a1) {
		return P.gt(n, a1);
	}

	/** "Special" predicate: %s must be > %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static @Nullable String gtEx(float n, float a1) {
		return P.gtEx(n, a1);
	}
	/** Predicate: %s must NOT be > %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean notGt(float n, float a1) {
		return P.notGt(n, a1);
	}

	/** "Special" predicate: %s must NOT be > %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static @Nullable String notGtEx(float n, float a1) {
		return P.notGtEx(n, a1);
	}

	/** Predicate: %s must be < %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean lt(float n, float a1) {
		return P.lt(n, a1);
	}

	/** "Special" predicate: %s must be < %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static @Nullable String ltEx(float n, float a1) {
		return P.ltEx(n, a1);
	}
	/** Predicate: %s must NOT be < %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean notLt(float n, float a1) {
		return P.notLt(n, a1);
	}

	/** "Special" predicate: %s must NOT be < %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static @Nullable String notLtEx(float n, float a1) {
		return P.notLtEx(n, a1);
	}

	/** Predicate: %s must be >= %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean gtEq(float n, float a1) {
		return P.gtEq(n, a1);
	}

	/** "Special" predicate: %s must be >= %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static @Nullable String gtEqEx(float n, float a1) {
		return P.gtEqEx(n, a1);
	}
	/** Predicate: %s must NOT be >= %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean notGtEq(float n, float a1) {
		return P.notGtEq(n, a1);
	}

	/** "Special" predicate: %s must NOT be >= %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static @Nullable String notGtEqEx(float n, float a1) {
		return P.notGtEqEx(n, a1);
	}

	/** Predicate: %s must be <= %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean ltEq(float n, float a1) {
		return P.ltEq(n, a1);
	}

	/** "Special" predicate: %s must be <= %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static @Nullable String ltEqEx(float n, float a1) {
		return P.ltEqEx(n, a1);
	}
	/** Predicate: %s must NOT be <= %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean notLtEq(float n, float a1) {
		return P.notLtEq(n, a1);
	}

	/** "Special" predicate: %s must NOT be <= %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static @Nullable String notLtEqEx(float n, float a1) {
		return P.notLtEqEx(n, a1);
	}

	/** Predicate: %s must be >= 0 (must be non-negative). Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean nonNegative(float n) {
		return P.nonNegative(n);
	}

	/** "Special" predicate: %s must be >= 0 (must be non-negative). Available in {@link P}, {@link Is}, {@link Be}.*/
	public static @Nullable String nonNegativeEx(float n) {
		return P.nonNegativeEx(n);
	}
	/** Predicate: %s must NOT be >= 0 (must be negative). Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean negative(float n) {
		return P.negative(n);
	}

	/** "Special" predicate: %s must NOT be >= 0 (must be negative). Available in {@link P}, {@link Is}, {@link Be}.*/
	public static @Nullable String negativeEx(float n) {
		return P.negativeEx(n);
	}

	/** Predicate: %s must be <= 0 (must be non-positive). Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean nonPositive(float n) {
		return P.nonPositive(n);
	}

	/** "Special" predicate: %s must be <= 0 (must be non-positive). Available in {@link P}, {@link Is}, {@link Be}.*/
	public static @Nullable String nonPositiveEx(float n) {
		return P.nonPositiveEx(n);
	}
	/** Predicate: %s must NOT be <= 0 (must be positive). Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean positive(float n) {
		return P.positive(n);
	}

	/** "Special" predicate: %s must NOT be <= 0 (must be positive). Available in {@link P}, {@link Is}, {@link Be}.*/
	public static @Nullable String positiveEx(float n) {
		return P.positiveEx(n);
	}

	/** Predicate: %s must be equal to %s. Available in {@link P}, {@link Is}, {@link Be}, {@link Does}.*/
	public static boolean equal(double n, double a1) {
		return P.equal(n, a1);
	}

	/** "Special" predicate: %s must be equal to %s. Available in {@link P}, {@link Is}, {@link Be}, {@link Does}.*/
	public static @Nullable String equalEx(double n, double a1) {
		return P.equalEx(n, a1);
	}
	/** Predicate: %s must NOT be equal to %s. Available in {@link P}, {@link Is}, {@link Be}, {@link Does}.*/
	public static boolean notEqual(double n, double a1) {
		return P.notEqual(n, a1);
	}

	/** "Special" predicate: %s must NOT be equal to %s. Available in {@link P}, {@link Is}, {@link Be}, {@link Does}.*/
	public static @Nullable String notEqualEx(double n, double a1) {
		return P.notEqualEx(n, a1);
	}

	/** Predicate: %s must be > %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean gt(double n, double a1) {
		return P.gt(n, a1);
	}

	/** "Special" predicate: %s must be > %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static @Nullable String gtEx(double n, double a1) {
		return P.gtEx(n, a1);
	}
	/** Predicate: %s must NOT be > %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean notGt(double n, double a1) {
		return P.notGt(n, a1);
	}

	/** "Special" predicate: %s must NOT be > %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static @Nullable String notGtEx(double n, double a1) {
		return P.notGtEx(n, a1);
	}

	/** Predicate: %s must be < %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean lt(double n, double a1) {
		return P.lt(n, a1);
	}

	/** "Special" predicate: %s must be < %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static @Nullable String ltEx(double n, double a1) {
		return P.ltEx(n, a1);
	}
	/** Predicate: %s must NOT be < %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean notLt(double n, double a1) {
		return P.notLt(n, a1);
	}

	/** "Special" predicate: %s must NOT be < %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static @Nullable String notLtEx(double n, double a1) {
		return P.notLtEx(n, a1);
	}

	/** Predicate: %s must be >= %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean gtEq(double n, double a1) {
		return P.gtEq(n, a1);
	}

	/** "Special" predicate: %s must be >= %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static @Nullable String gtEqEx(double n, double a1) {
		return P.gtEqEx(n, a1);
	}
	/** Predicate: %s must NOT be >= %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean notGtEq(double n, double a1) {
		return P.notGtEq(n, a1);
	}

	/** "Special" predicate: %s must NOT be >= %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static @Nullable String notGtEqEx(double n, double a1) {
		return P.notGtEqEx(n, a1);
	}

	/** Predicate: %s must be <= %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean ltEq(double n, double a1) {
		return P.ltEq(n, a1);
	}

	/** "Special" predicate: %s must be <= %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static @Nullable String ltEqEx(double n, double a1) {
		return P.ltEqEx(n, a1);
	}
	/** Predicate: %s must NOT be <= %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean notLtEq(double n, double a1) {
		return P.notLtEq(n, a1);
	}

	/** "Special" predicate: %s must NOT be <= %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static @Nullable String notLtEqEx(double n, double a1) {
		return P.notLtEqEx(n, a1);
	}

	/** Predicate: %s must be >= 0 (must be non-negative). Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean nonNegative(double n) {
		return P.nonNegative(n);
	}

	/** "Special" predicate: %s must be >= 0 (must be non-negative). Available in {@link P}, {@link Is}, {@link Be}.*/
	public static @Nullable String nonNegativeEx(double n) {
		return P.nonNegativeEx(n);
	}
	/** Predicate: %s must NOT be >= 0 (must be negative). Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean negative(double n) {
		return P.negative(n);
	}

	/** "Special" predicate: %s must NOT be >= 0 (must be negative). Available in {@link P}, {@link Is}, {@link Be}.*/
	public static @Nullable String negativeEx(double n) {
		return P.negativeEx(n);
	}

	/** Predicate: %s must be <= 0 (must be non-positive). Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean nonPositive(double n) {
		return P.nonPositive(n);
	}

	/** "Special" predicate: %s must be <= 0 (must be non-positive). Available in {@link P}, {@link Is}, {@link Be}.*/
	public static @Nullable String nonPositiveEx(double n) {
		return P.nonPositiveEx(n);
	}
	/** Predicate: %s must NOT be <= 0 (must be positive). Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean positive(double n) {
		return P.positive(n);
	}

	/** "Special" predicate: %s must NOT be <= 0 (must be positive). Available in {@link P}, {@link Is}, {@link Be}.*/
	public static @Nullable String positiveEx(double n) {
		return P.positiveEx(n);
	}

	/** Predicate: %s must be equal to %s. Available in {@link P}, {@link Is}, {@link Be}, {@link Does}.*/
	public static boolean equal(char n, char a1) {
		return P.equal(n, a1);
	}

	/** "Special" predicate: %s must be equal to %s. Available in {@link P}, {@link Is}, {@link Be}, {@link Does}.*/
	public static @Nullable String equalEx(char n, char a1) {
		return P.equalEx(n, a1);
	}
	/** Predicate: %s must NOT be equal to %s. Available in {@link P}, {@link Is}, {@link Be}, {@link Does}.*/
	public static boolean notEqual(char n, char a1) {
		return P.notEqual(n, a1);
	}

	/** "Special" predicate: %s must NOT be equal to %s. Available in {@link P}, {@link Is}, {@link Be}, {@link Does}.*/
	public static @Nullable String notEqualEx(char n, char a1) {
		return P.notEqualEx(n, a1);
	}

	/** Predicate: %s must be > %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean gt(char n, char a1) {
		return P.gt(n, a1);
	}

	/** "Special" predicate: %s must be > %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static @Nullable String gtEx(char n, char a1) {
		return P.gtEx(n, a1);
	}
	/** Predicate: %s must NOT be > %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean notGt(char n, char a1) {
		return P.notGt(n, a1);
	}

	/** "Special" predicate: %s must NOT be > %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static @Nullable String notGtEx(char n, char a1) {
		return P.notGtEx(n, a1);
	}

	/** Predicate: %s must be < %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean lt(char n, char a1) {
		return P.lt(n, a1);
	}

	/** "Special" predicate: %s must be < %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static @Nullable String ltEx(char n, char a1) {
		return P.ltEx(n, a1);
	}
	/** Predicate: %s must NOT be < %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean notLt(char n, char a1) {
		return P.notLt(n, a1);
	}

	/** "Special" predicate: %s must NOT be < %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static @Nullable String notLtEx(char n, char a1) {
		return P.notLtEx(n, a1);
	}

	/** Predicate: %s must be >= %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean gtEq(char n, char a1) {
		return P.gtEq(n, a1);
	}

	/** "Special" predicate: %s must be >= %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static @Nullable String gtEqEx(char n, char a1) {
		return P.gtEqEx(n, a1);
	}
	/** Predicate: %s must NOT be >= %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean notGtEq(char n, char a1) {
		return P.notGtEq(n, a1);
	}

	/** "Special" predicate: %s must NOT be >= %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static @Nullable String notGtEqEx(char n, char a1) {
		return P.notGtEqEx(n, a1);
	}

	/** Predicate: %s must be <= %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean ltEq(char n, char a1) {
		return P.ltEq(n, a1);
	}

	/** "Special" predicate: %s must be <= %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static @Nullable String ltEqEx(char n, char a1) {
		return P.ltEqEx(n, a1);
	}
	/** Predicate: %s must NOT be <= %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean notLtEq(char n, char a1) {
		return P.notLtEq(n, a1);
	}

	/** "Special" predicate: %s must NOT be <= %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static @Nullable String notLtEqEx(char n, char a1) {
		return P.notLtEqEx(n, a1);
	}

	/** Predicate: %s must be >= 0 (must be non-negative). Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean nonNegative(char n) {
		return P.nonNegative(n);
	}

	/** "Special" predicate: %s must be >= 0 (must be non-negative). Available in {@link P}, {@link Is}, {@link Be}.*/
	public static @Nullable String nonNegativeEx(char n) {
		return P.nonNegativeEx(n);
	}
	/** Predicate: %s must NOT be >= 0 (must be negative). Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean negative(char n) {
		return P.negative(n);
	}

	/** "Special" predicate: %s must NOT be >= 0 (must be negative). Available in {@link P}, {@link Is}, {@link Be}.*/
	public static @Nullable String negativeEx(char n) {
		return P.negativeEx(n);
	}

	/** Predicate: %s must be <= 0 (must be non-positive). Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean nonPositive(char n) {
		return P.nonPositive(n);
	}

	/** "Special" predicate: %s must be <= 0 (must be non-positive). Available in {@link P}, {@link Is}, {@link Be}.*/
	public static @Nullable String nonPositiveEx(char n) {
		return P.nonPositiveEx(n);
	}
	/** Predicate: %s must NOT be <= 0 (must be positive). Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean positive(char n) {
		return P.positive(n);
	}

	/** "Special" predicate: %s must NOT be <= 0 (must be positive). Available in {@link P}, {@link Is}, {@link Be}.*/
	public static @Nullable String positiveEx(char n) {
		return P.positiveEx(n);
	}

	/** Predicate: %1$s must be: %2$s < %1$s < %3$s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean between(byte n, byte a1, byte a2) {
		return P.between(n, a1, a2);
	}

	/** "Special" predicate: %1$s must be: %2$s < %1$s < %3$s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static @Nullable String betweenEx(byte n, byte a1, byte a2) {
		return P.betweenEx(n, a1, a2);
	}
	/** Predicate: %1$s must NOT be: %2$s < %1$s < %3$s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean notBetween(byte n, byte a1, byte a2) {
		return P.notBetween(n, a1, a2);
	}

	/** "Special" predicate: %1$s must NOT be: %2$s < %1$s < %3$s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static @Nullable String notBetweenEx(byte n, byte a1, byte a2) {
		return P.notBetweenEx(n, a1, a2);
	}

	/** Predicate: %1$s must be: %2$s <= %1$s <= %3$s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean inRange(byte n, byte a1, byte a2) {
		return P.inRange(n, a1, a2);
	}

	/** "Special" predicate: %1$s must be: %2$s <= %1$s <= %3$s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static @Nullable String inRangeEx(byte n, byte a1, byte a2) {
		return P.inRangeEx(n, a1, a2);
	}
	/** Predicate: %1$s must NOT be: %2$s <= %1$s <= %3$s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean notInRange(byte n, byte a1, byte a2) {
		return P.notInRange(n, a1, a2);
	}

	/** "Special" predicate: %1$s must NOT be: %2$s <= %1$s <= %3$s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static @Nullable String notInRangeEx(byte n, byte a1, byte a2) {
		return P.notInRangeEx(n, a1, a2);
	}

	/** Predicate: %1$s must be: %2$s < %1$s < %3$s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean between(short n, short a1, short a2) {
		return P.between(n, a1, a2);
	}

	/** "Special" predicate: %1$s must be: %2$s < %1$s < %3$s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static @Nullable String betweenEx(short n, short a1, short a2) {
		return P.betweenEx(n, a1, a2);
	}
	/** Predicate: %1$s must NOT be: %2$s < %1$s < %3$s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean notBetween(short n, short a1, short a2) {
		return P.notBetween(n, a1, a2);
	}

	/** "Special" predicate: %1$s must NOT be: %2$s < %1$s < %3$s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static @Nullable String notBetweenEx(short n, short a1, short a2) {
		return P.notBetweenEx(n, a1, a2);
	}

	/** Predicate: %1$s must be: %2$s <= %1$s <= %3$s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean inRange(short n, short a1, short a2) {
		return P.inRange(n, a1, a2);
	}

	/** "Special" predicate: %1$s must be: %2$s <= %1$s <= %3$s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static @Nullable String inRangeEx(short n, short a1, short a2) {
		return P.inRangeEx(n, a1, a2);
	}
	/** Predicate: %1$s must NOT be: %2$s <= %1$s <= %3$s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean notInRange(short n, short a1, short a2) {
		return P.notInRange(n, a1, a2);
	}

	/** "Special" predicate: %1$s must NOT be: %2$s <= %1$s <= %3$s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static @Nullable String notInRangeEx(short n, short a1, short a2) {
		return P.notInRangeEx(n, a1, a2);
	}

	/** Predicate: %1$s must be: %2$s < %1$s < %3$s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean between(int n, int a1, int a2) {
		return P.between(n, a1, a2);
	}

	/** "Special" predicate: %1$s must be: %2$s < %1$s < %3$s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static @Nullable String betweenEx(int n, int a1, int a2) {
		return P.betweenEx(n, a1, a2);
	}
	/** Predicate: %1$s must NOT be: %2$s < %1$s < %3$s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean notBetween(int n, int a1, int a2) {
		return P.notBetween(n, a1, a2);
	}

	/** "Special" predicate: %1$s must NOT be: %2$s < %1$s < %3$s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static @Nullable String notBetweenEx(int n, int a1, int a2) {
		return P.notBetweenEx(n, a1, a2);
	}

	/** Predicate: %1$s must be: %2$s <= %1$s <= %3$s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean inRange(int n, int a1, int a2) {
		return P.inRange(n, a1, a2);
	}

	/** "Special" predicate: %1$s must be: %2$s <= %1$s <= %3$s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static @Nullable String inRangeEx(int n, int a1, int a2) {
		return P.inRangeEx(n, a1, a2);
	}
	/** Predicate: %1$s must NOT be: %2$s <= %1$s <= %3$s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean notInRange(int n, int a1, int a2) {
		return P.notInRange(n, a1, a2);
	}

	/** "Special" predicate: %1$s must NOT be: %2$s <= %1$s <= %3$s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static @Nullable String notInRangeEx(int n, int a1, int a2) {
		return P.notInRangeEx(n, a1, a2);
	}

	/** Predicate: %1$s must be: %2$s < %1$s < %3$s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean between(long n, long a1, long a2) {
		return P.between(n, a1, a2);
	}

	/** "Special" predicate: %1$s must be: %2$s < %1$s < %3$s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static @Nullable String betweenEx(long n, long a1, long a2) {
		return P.betweenEx(n, a1, a2);
	}
	/** Predicate: %1$s must NOT be: %2$s < %1$s < %3$s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean notBetween(long n, long a1, long a2) {
		return P.notBetween(n, a1, a2);
	}

	/** "Special" predicate: %1$s must NOT be: %2$s < %1$s < %3$s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static @Nullable String notBetweenEx(long n, long a1, long a2) {
		return P.notBetweenEx(n, a1, a2);
	}

	/** Predicate: %1$s must be: %2$s <= %1$s <= %3$s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean inRange(long n, long a1, long a2) {
		return P.inRange(n, a1, a2);
	}

	/** "Special" predicate: %1$s must be: %2$s <= %1$s <= %3$s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static @Nullable String inRangeEx(long n, long a1, long a2) {
		return P.inRangeEx(n, a1, a2);
	}
	/** Predicate: %1$s must NOT be: %2$s <= %1$s <= %3$s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean notInRange(long n, long a1, long a2) {
		return P.notInRange(n, a1, a2);
	}

	/** "Special" predicate: %1$s must NOT be: %2$s <= %1$s <= %3$s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static @Nullable String notInRangeEx(long n, long a1, long a2) {
		return P.notInRangeEx(n, a1, a2);
	}

	/** Predicate: %1$s must be: %2$s < %1$s < %3$s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean between(float n, float a1, float a2) {
		return P.between(n, a1, a2);
	}

	/** "Special" predicate: %1$s must be: %2$s < %1$s < %3$s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static @Nullable String betweenEx(float n, float a1, float a2) {
		return P.betweenEx(n, a1, a2);
	}
	/** Predicate: %1$s must NOT be: %2$s < %1$s < %3$s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean notBetween(float n, float a1, float a2) {
		return P.notBetween(n, a1, a2);
	}

	/** "Special" predicate: %1$s must NOT be: %2$s < %1$s < %3$s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static @Nullable String notBetweenEx(float n, float a1, float a2) {
		return P.notBetweenEx(n, a1, a2);
	}

	/** Predicate: %1$s must be: %2$s <= %1$s <= %3$s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean inRange(float n, float a1, float a2) {
		return P.inRange(n, a1, a2);
	}

	/** "Special" predicate: %1$s must be: %2$s <= %1$s <= %3$s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static @Nullable String inRangeEx(float n, float a1, float a2) {
		return P.inRangeEx(n, a1, a2);
	}
	/** Predicate: %1$s must NOT be: %2$s <= %1$s <= %3$s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean notInRange(float n, float a1, float a2) {
		return P.notInRange(n, a1, a2);
	}

	/** "Special" predicate: %1$s must NOT be: %2$s <= %1$s <= %3$s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static @Nullable String notInRangeEx(float n, float a1, float a2) {
		return P.notInRangeEx(n, a1, a2);
	}

	/** Predicate: %1$s must be: %2$s < %1$s < %3$s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean between(double n, double a1, double a2) {
		return P.between(n, a1, a2);
	}

	/** "Special" predicate: %1$s must be: %2$s < %1$s < %3$s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static @Nullable String betweenEx(double n, double a1, double a2) {
		return P.betweenEx(n, a1, a2);
	}
	/** Predicate: %1$s must NOT be: %2$s < %1$s < %3$s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean notBetween(double n, double a1, double a2) {
		return P.notBetween(n, a1, a2);
	}

	/** "Special" predicate: %1$s must NOT be: %2$s < %1$s < %3$s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static @Nullable String notBetweenEx(double n, double a1, double a2) {
		return P.notBetweenEx(n, a1, a2);
	}

	/** Predicate: %1$s must be: %2$s <= %1$s <= %3$s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean inRange(double n, double a1, double a2) {
		return P.inRange(n, a1, a2);
	}

	/** "Special" predicate: %1$s must be: %2$s <= %1$s <= %3$s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static @Nullable String inRangeEx(double n, double a1, double a2) {
		return P.inRangeEx(n, a1, a2);
	}
	/** Predicate: %1$s must NOT be: %2$s <= %1$s <= %3$s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean notInRange(double n, double a1, double a2) {
		return P.notInRange(n, a1, a2);
	}

	/** "Special" predicate: %1$s must NOT be: %2$s <= %1$s <= %3$s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static @Nullable String notInRangeEx(double n, double a1, double a2) {
		return P.notInRangeEx(n, a1, a2);
	}

	/** Predicate: %1$s must be: %2$s < %1$s < %3$s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean between(char n, char a1, char a2) {
		return P.between(n, a1, a2);
	}

	/** "Special" predicate: %1$s must be: %2$s < %1$s < %3$s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static @Nullable String betweenEx(char n, char a1, char a2) {
		return P.betweenEx(n, a1, a2);
	}
	/** Predicate: %1$s must NOT be: %2$s < %1$s < %3$s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean notBetween(char n, char a1, char a2) {
		return P.notBetween(n, a1, a2);
	}

	/** "Special" predicate: %1$s must NOT be: %2$s < %1$s < %3$s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static @Nullable String notBetweenEx(char n, char a1, char a2) {
		return P.notBetweenEx(n, a1, a2);
	}

	/** Predicate: %1$s must be: %2$s <= %1$s <= %3$s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean inRange(char n, char a1, char a2) {
		return P.inRange(n, a1, a2);
	}

	/** "Special" predicate: %1$s must be: %2$s <= %1$s <= %3$s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static @Nullable String inRangeEx(char n, char a1, char a2) {
		return P.inRangeEx(n, a1, a2);
	}
	/** Predicate: %1$s must NOT be: %2$s <= %1$s <= %3$s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean notInRange(char n, char a1, char a2) {
		return P.notInRange(n, a1, a2);
	}

	/** "Special" predicate: %1$s must NOT be: %2$s <= %1$s <= %3$s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static @Nullable String notInRangeEx(char n, char a1, char a2) {
		return P.notInRangeEx(n, a1, a2);
	}

	/** Predicate: <%s> must be true. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean True(boolean v) {
		return P.True(v);
	}

	/** "Special" predicate: <%s> must be true. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static @Nullable String TrueEx(boolean v) {
		return P.TrueEx(v);
	}
	/** Predicate: <%s> must be false. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean False(boolean v) {
		return P.False(v);
	}

	/** "Special" predicate: <%s> must be false. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static @Nullable String FalseEx(boolean v) {
		return P.FalseEx(v);
	}

	// </editor-fold>

	// <editor-fold desc="arrays">

	/** Predicate: Array <%s> must be of size %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean ofLength(@Nonnull boolean[] array, int i) {
		Null.nonNullArg(array, "array");
		return P.ofLength(array, i);
	}

	/** "Special" predicate: Array <%s> must be of size %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static @Nullable String ofLengthEx(@Nonnull boolean[] array, int i) {
		Null.nonNullArg(array, "array");
		return P.ofLengthEx(array, i);
	}
	/** Predicate: Array <%s> must NOT be of size %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean notOfLength(@Nonnull boolean[] array, int i) {
		Null.nonNullArg(array, "array");
		return P.notOfLength(array, i);
	}

	/** "Special" predicate: Array <%s> must NOT be of size %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static @Nullable String notOfLengthEx(@Nonnull boolean[] array, int i) {
		Null.nonNullArg(array, "array");
		return P.notOfLengthEx(array, i);
	}

	/** Predicate: Array <%s> must be empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean empty(@Nonnull boolean[] array) {
		Null.nonNullArg(array, "array");
		return P.empty(array);
	}

	/** "Special" predicate: Array <%s> must be empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static @Nullable String emptyEx(@Nonnull boolean[] array) {
		Null.nonNullArg(array, "array");
		return P.emptyEx(array);
	}
	/** Predicate: Array <%s> must NOT be empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean notEmpty(@Nonnull boolean[] array) {
		Null.nonNullArg(array, "array");
		return P.notEmpty(array);
	}

	/** "Special" predicate: Array <%s> must NOT be empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static @Nullable String notEmptyEx(@Nonnull boolean[] array) {
		Null.nonNullArg(array, "array");
		return P.notEmptyEx(array);
	}

	/** Predicate: Array <%s> must be null or empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean nullOrEmpty(@Nullable boolean[] array) {
		return P.nullOrEmpty(array);
	}

	/** "Special" predicate: Array <%s> must be null or empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static @Nullable String nullOrEmptyEx(@Nullable boolean[] array) {
		return P.nullOrEmptyEx(array);
	}
	/** Predicate: Array <%s> must NOT be null or empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean notNullNotEmpty(@Nullable boolean[] array) {
		return P.notNullNotEmpty(array);
	}

	/** "Special" predicate: Array <%s> must NOT be null or empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static @Nullable String notNullNotEmptyEx(@Nullable boolean[] array) {
		return P.notNullNotEmptyEx(array);
	}

	/** Predicate: Array <%s> must be exactly of size 1 (singleton). Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean singleton(@Nonnull boolean[] array) {
		Null.nonNullArg(array, "array");
		return P.singleton(array);
	}

	/** "Special" predicate: Array <%s> must be exactly of size 1 (singleton). Available in {@link P}, {@link Is}, {@link Be}.*/
	public static @Nullable String singletonEx(@Nonnull boolean[] array) {
		Null.nonNullArg(array, "array");
		return P.singletonEx(array);
	}
	/** Predicate: Array <%s> must NOT be exactly of size 1 (singleton). Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean notSingleton(@Nonnull boolean[] array) {
		Null.nonNullArg(array, "array");
		return P.notSingleton(array);
	}

	/** "Special" predicate: Array <%s> must NOT be exactly of size 1 (singleton). Available in {@link P}, {@link Is}, {@link Be}.*/
	public static @Nullable String notSingletonEx(@Nonnull boolean[] array) {
		Null.nonNullArg(array, "array");
		return P.notSingletonEx(array);
	}

	/** Predicate: Array <%s> must be equal to array <%s>. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean equal(boolean[] a1, boolean[] a2) {
		return P.equal(a1, a2);
	}

	/** "Special" predicate: Array <%s> must be equal to array <%s>. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static @Nullable String equalEx(boolean[] a1, boolean[] a2) {
		return P.equalEx(a1, a2);
	}
	/** Predicate: Array <%s> must NOT be equal to array <%s>. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean notEqual(boolean[] a1, boolean[] a2) {
		return P.notEqual(a1, a2);
	}

	/** "Special" predicate: Array <%s> must NOT be equal to array <%s>. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static @Nullable String notEqualEx(boolean[] a1, boolean[] a2) {
		return P.notEqualEx(a1, a2);
	}

	/** Predicate: Array <%s> must be of size %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean ofLength(@Nonnull byte[] array, int i) {
		Null.nonNullArg(array, "array");
		return P.ofLength(array, i);
	}

	/** "Special" predicate: Array <%s> must be of size %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static @Nullable String ofLengthEx(@Nonnull byte[] array, int i) {
		Null.nonNullArg(array, "array");
		return P.ofLengthEx(array, i);
	}
	/** Predicate: Array <%s> must NOT be of size %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean notOfLength(@Nonnull byte[] array, int i) {
		Null.nonNullArg(array, "array");
		return P.notOfLength(array, i);
	}

	/** "Special" predicate: Array <%s> must NOT be of size %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static @Nullable String notOfLengthEx(@Nonnull byte[] array, int i) {
		Null.nonNullArg(array, "array");
		return P.notOfLengthEx(array, i);
	}

	/** Predicate: Array <%s> must be empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean empty(@Nonnull byte[] array) {
		Null.nonNullArg(array, "array");
		return P.empty(array);
	}

	/** "Special" predicate: Array <%s> must be empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static @Nullable String emptyEx(@Nonnull byte[] array) {
		Null.nonNullArg(array, "array");
		return P.emptyEx(array);
	}
	/** Predicate: Array <%s> must NOT be empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean notEmpty(@Nonnull byte[] array) {
		Null.nonNullArg(array, "array");
		return P.notEmpty(array);
	}

	/** "Special" predicate: Array <%s> must NOT be empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static @Nullable String notEmptyEx(@Nonnull byte[] array) {
		Null.nonNullArg(array, "array");
		return P.notEmptyEx(array);
	}

	/** Predicate: Array <%s> must be null or empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean nullOrEmpty(@Nullable byte[] array) {
		return P.nullOrEmpty(array);
	}

	/** "Special" predicate: Array <%s> must be null or empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static @Nullable String nullOrEmptyEx(@Nullable byte[] array) {
		return P.nullOrEmptyEx(array);
	}
	/** Predicate: Array <%s> must NOT be null or empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean notNullNotEmpty(@Nullable byte[] array) {
		return P.notNullNotEmpty(array);
	}

	/** "Special" predicate: Array <%s> must NOT be null or empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static @Nullable String notNullNotEmptyEx(@Nullable byte[] array) {
		return P.notNullNotEmptyEx(array);
	}

	/** Predicate: Array <%s> must be exactly of size 1 (singleton). Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean singleton(@Nonnull byte[] array) {
		Null.nonNullArg(array, "array");
		return P.singleton(array);
	}

	/** "Special" predicate: Array <%s> must be exactly of size 1 (singleton). Available in {@link P}, {@link Is}, {@link Be}.*/
	public static @Nullable String singletonEx(@Nonnull byte[] array) {
		Null.nonNullArg(array, "array");
		return P.singletonEx(array);
	}
	/** Predicate: Array <%s> must NOT be exactly of size 1 (singleton). Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean notSingleton(@Nonnull byte[] array) {
		Null.nonNullArg(array, "array");
		return P.notSingleton(array);
	}

	/** "Special" predicate: Array <%s> must NOT be exactly of size 1 (singleton). Available in {@link P}, {@link Is}, {@link Be}.*/
	public static @Nullable String notSingletonEx(@Nonnull byte[] array) {
		Null.nonNullArg(array, "array");
		return P.notSingletonEx(array);
	}

	/** Predicate: Array <%s> must be equal to array <%s>. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean equal(byte[] a1, byte[] a2) {
		return P.equal(a1, a2);
	}

	/** "Special" predicate: Array <%s> must be equal to array <%s>. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static @Nullable String equalEx(byte[] a1, byte[] a2) {
		return P.equalEx(a1, a2);
	}
	/** Predicate: Array <%s> must NOT be equal to array <%s>. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean notEqual(byte[] a1, byte[] a2) {
		return P.notEqual(a1, a2);
	}

	/** "Special" predicate: Array <%s> must NOT be equal to array <%s>. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static @Nullable String notEqualEx(byte[] a1, byte[] a2) {
		return P.notEqualEx(a1, a2);
	}

	/** Predicate: Array <%s> must be of size %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean ofLength(@Nonnull double[] array, int i) {
		Null.nonNullArg(array, "array");
		return P.ofLength(array, i);
	}

	/** "Special" predicate: Array <%s> must be of size %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static @Nullable String ofLengthEx(@Nonnull double[] array, int i) {
		Null.nonNullArg(array, "array");
		return P.ofLengthEx(array, i);
	}
	/** Predicate: Array <%s> must NOT be of size %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean notOfLength(@Nonnull double[] array, int i) {
		Null.nonNullArg(array, "array");
		return P.notOfLength(array, i);
	}

	/** "Special" predicate: Array <%s> must NOT be of size %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static @Nullable String notOfLengthEx(@Nonnull double[] array, int i) {
		Null.nonNullArg(array, "array");
		return P.notOfLengthEx(array, i);
	}

	/** Predicate: Array <%s> must be empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean empty(@Nonnull double[] array) {
		Null.nonNullArg(array, "array");
		return P.empty(array);
	}

	/** "Special" predicate: Array <%s> must be empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static @Nullable String emptyEx(@Nonnull double[] array) {
		Null.nonNullArg(array, "array");
		return P.emptyEx(array);
	}
	/** Predicate: Array <%s> must NOT be empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean notEmpty(@Nonnull double[] array) {
		Null.nonNullArg(array, "array");
		return P.notEmpty(array);
	}

	/** "Special" predicate: Array <%s> must NOT be empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static @Nullable String notEmptyEx(@Nonnull double[] array) {
		Null.nonNullArg(array, "array");
		return P.notEmptyEx(array);
	}

	/** Predicate: Array <%s> must be null or empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean nullOrEmpty(@Nullable double[] array) {
		return P.nullOrEmpty(array);
	}

	/** "Special" predicate: Array <%s> must be null or empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static @Nullable String nullOrEmptyEx(@Nullable double[] array) {
		return P.nullOrEmptyEx(array);
	}
	/** Predicate: Array <%s> must NOT be null or empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean notNullNotEmpty(@Nullable double[] array) {
		return P.notNullNotEmpty(array);
	}

	/** "Special" predicate: Array <%s> must NOT be null or empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static @Nullable String notNullNotEmptyEx(@Nullable double[] array) {
		return P.notNullNotEmptyEx(array);
	}

	/** Predicate: Array <%s> must be exactly of size 1 (singleton). Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean singleton(@Nonnull double[] array) {
		Null.nonNullArg(array, "array");
		return P.singleton(array);
	}

	/** "Special" predicate: Array <%s> must be exactly of size 1 (singleton). Available in {@link P}, {@link Is}, {@link Be}.*/
	public static @Nullable String singletonEx(@Nonnull double[] array) {
		Null.nonNullArg(array, "array");
		return P.singletonEx(array);
	}
	/** Predicate: Array <%s> must NOT be exactly of size 1 (singleton). Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean notSingleton(@Nonnull double[] array) {
		Null.nonNullArg(array, "array");
		return P.notSingleton(array);
	}

	/** "Special" predicate: Array <%s> must NOT be exactly of size 1 (singleton). Available in {@link P}, {@link Is}, {@link Be}.*/
	public static @Nullable String notSingletonEx(@Nonnull double[] array) {
		Null.nonNullArg(array, "array");
		return P.notSingletonEx(array);
	}

	/** Predicate: Array <%s> must be equal to array <%s>. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean equal(double[] a1, double[] a2) {
		return P.equal(a1, a2);
	}

	/** "Special" predicate: Array <%s> must be equal to array <%s>. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static @Nullable String equalEx(double[] a1, double[] a2) {
		return P.equalEx(a1, a2);
	}
	/** Predicate: Array <%s> must NOT be equal to array <%s>. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean notEqual(double[] a1, double[] a2) {
		return P.notEqual(a1, a2);
	}

	/** "Special" predicate: Array <%s> must NOT be equal to array <%s>. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static @Nullable String notEqualEx(double[] a1, double[] a2) {
		return P.notEqualEx(a1, a2);
	}

	/** Predicate: Array <%s> must be of size %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean ofLength(@Nonnull char[] array, int i) {
		Null.nonNullArg(array, "array");
		return P.ofLength(array, i);
	}

	/** "Special" predicate: Array <%s> must be of size %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static @Nullable String ofLengthEx(@Nonnull char[] array, int i) {
		Null.nonNullArg(array, "array");
		return P.ofLengthEx(array, i);
	}
	/** Predicate: Array <%s> must NOT be of size %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean notOfLength(@Nonnull char[] array, int i) {
		Null.nonNullArg(array, "array");
		return P.notOfLength(array, i);
	}

	/** "Special" predicate: Array <%s> must NOT be of size %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static @Nullable String notOfLengthEx(@Nonnull char[] array, int i) {
		Null.nonNullArg(array, "array");
		return P.notOfLengthEx(array, i);
	}

	/** Predicate: Array <%s> must be empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean empty(@Nonnull char[] array) {
		Null.nonNullArg(array, "array");
		return P.empty(array);
	}

	/** "Special" predicate: Array <%s> must be empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static @Nullable String emptyEx(@Nonnull char[] array) {
		Null.nonNullArg(array, "array");
		return P.emptyEx(array);
	}
	/** Predicate: Array <%s> must NOT be empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean notEmpty(@Nonnull char[] array) {
		Null.nonNullArg(array, "array");
		return P.notEmpty(array);
	}

	/** "Special" predicate: Array <%s> must NOT be empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static @Nullable String notEmptyEx(@Nonnull char[] array) {
		Null.nonNullArg(array, "array");
		return P.notEmptyEx(array);
	}

	/** Predicate: Array <%s> must be null or empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean nullOrEmpty(@Nullable char[] array) {
		return P.nullOrEmpty(array);
	}

	/** "Special" predicate: Array <%s> must be null or empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static @Nullable String nullOrEmptyEx(@Nullable char[] array) {
		return P.nullOrEmptyEx(array);
	}
	/** Predicate: Array <%s> must NOT be null or empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean notNullNotEmpty(@Nullable char[] array) {
		return P.notNullNotEmpty(array);
	}

	/** "Special" predicate: Array <%s> must NOT be null or empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static @Nullable String notNullNotEmptyEx(@Nullable char[] array) {
		return P.notNullNotEmptyEx(array);
	}

	/** Predicate: Array <%s> must be exactly of size 1 (singleton). Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean singleton(@Nonnull char[] array) {
		Null.nonNullArg(array, "array");
		return P.singleton(array);
	}

	/** "Special" predicate: Array <%s> must be exactly of size 1 (singleton). Available in {@link P}, {@link Is}, {@link Be}.*/
	public static @Nullable String singletonEx(@Nonnull char[] array) {
		Null.nonNullArg(array, "array");
		return P.singletonEx(array);
	}
	/** Predicate: Array <%s> must NOT be exactly of size 1 (singleton). Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean notSingleton(@Nonnull char[] array) {
		Null.nonNullArg(array, "array");
		return P.notSingleton(array);
	}

	/** "Special" predicate: Array <%s> must NOT be exactly of size 1 (singleton). Available in {@link P}, {@link Is}, {@link Be}.*/
	public static @Nullable String notSingletonEx(@Nonnull char[] array) {
		Null.nonNullArg(array, "array");
		return P.notSingletonEx(array);
	}

	/** Predicate: Array <%s> must be equal to array <%s>. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean equal(char[] a1, char[] a2) {
		return P.equal(a1, a2);
	}

	/** "Special" predicate: Array <%s> must be equal to array <%s>. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static @Nullable String equalEx(char[] a1, char[] a2) {
		return P.equalEx(a1, a2);
	}
	/** Predicate: Array <%s> must NOT be equal to array <%s>. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean notEqual(char[] a1, char[] a2) {
		return P.notEqual(a1, a2);
	}

	/** "Special" predicate: Array <%s> must NOT be equal to array <%s>. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static @Nullable String notEqualEx(char[] a1, char[] a2) {
		return P.notEqualEx(a1, a2);
	}

	/** Predicate: Array <%s> must be of size %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean ofLength(@Nonnull short[] array, int i) {
		Null.nonNullArg(array, "array");
		return P.ofLength(array, i);
	}

	/** "Special" predicate: Array <%s> must be of size %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static @Nullable String ofLengthEx(@Nonnull short[] array, int i) {
		Null.nonNullArg(array, "array");
		return P.ofLengthEx(array, i);
	}
	/** Predicate: Array <%s> must NOT be of size %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean notOfLength(@Nonnull short[] array, int i) {
		Null.nonNullArg(array, "array");
		return P.notOfLength(array, i);
	}

	/** "Special" predicate: Array <%s> must NOT be of size %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static @Nullable String notOfLengthEx(@Nonnull short[] array, int i) {
		Null.nonNullArg(array, "array");
		return P.notOfLengthEx(array, i);
	}

	/** Predicate: Array <%s> must be empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean empty(@Nonnull short[] array) {
		Null.nonNullArg(array, "array");
		return P.empty(array);
	}

	/** "Special" predicate: Array <%s> must be empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static @Nullable String emptyEx(@Nonnull short[] array) {
		Null.nonNullArg(array, "array");
		return P.emptyEx(array);
	}
	/** Predicate: Array <%s> must NOT be empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean notEmpty(@Nonnull short[] array) {
		Null.nonNullArg(array, "array");
		return P.notEmpty(array);
	}

	/** "Special" predicate: Array <%s> must NOT be empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static @Nullable String notEmptyEx(@Nonnull short[] array) {
		Null.nonNullArg(array, "array");
		return P.notEmptyEx(array);
	}

	/** Predicate: Array <%s> must be null or empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean nullOrEmpty(@Nullable short[] array) {
		return P.nullOrEmpty(array);
	}

	/** "Special" predicate: Array <%s> must be null or empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static @Nullable String nullOrEmptyEx(@Nullable short[] array) {
		return P.nullOrEmptyEx(array);
	}
	/** Predicate: Array <%s> must NOT be null or empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean notNullNotEmpty(@Nullable short[] array) {
		return P.notNullNotEmpty(array);
	}

	/** "Special" predicate: Array <%s> must NOT be null or empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static @Nullable String notNullNotEmptyEx(@Nullable short[] array) {
		return P.notNullNotEmptyEx(array);
	}

	/** Predicate: Array <%s> must be exactly of size 1 (singleton). Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean singleton(@Nonnull short[] array) {
		Null.nonNullArg(array, "array");
		return P.singleton(array);
	}

	/** "Special" predicate: Array <%s> must be exactly of size 1 (singleton). Available in {@link P}, {@link Is}, {@link Be}.*/
	public static @Nullable String singletonEx(@Nonnull short[] array) {
		Null.nonNullArg(array, "array");
		return P.singletonEx(array);
	}
	/** Predicate: Array <%s> must NOT be exactly of size 1 (singleton). Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean notSingleton(@Nonnull short[] array) {
		Null.nonNullArg(array, "array");
		return P.notSingleton(array);
	}

	/** "Special" predicate: Array <%s> must NOT be exactly of size 1 (singleton). Available in {@link P}, {@link Is}, {@link Be}.*/
	public static @Nullable String notSingletonEx(@Nonnull short[] array) {
		Null.nonNullArg(array, "array");
		return P.notSingletonEx(array);
	}

	/** Predicate: Array <%s> must be equal to array <%s>. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean equal(short[] a1, short[] a2) {
		return P.equal(a1, a2);
	}

	/** "Special" predicate: Array <%s> must be equal to array <%s>. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static @Nullable String equalEx(short[] a1, short[] a2) {
		return P.equalEx(a1, a2);
	}
	/** Predicate: Array <%s> must NOT be equal to array <%s>. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean notEqual(short[] a1, short[] a2) {
		return P.notEqual(a1, a2);
	}

	/** "Special" predicate: Array <%s> must NOT be equal to array <%s>. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static @Nullable String notEqualEx(short[] a1, short[] a2) {
		return P.notEqualEx(a1, a2);
	}

	/** Predicate: Array <%s> must be of size %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean ofLength(@Nonnull float[] array, int i) {
		Null.nonNullArg(array, "array");
		return P.ofLength(array, i);
	}

	/** "Special" predicate: Array <%s> must be of size %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static @Nullable String ofLengthEx(@Nonnull float[] array, int i) {
		Null.nonNullArg(array, "array");
		return P.ofLengthEx(array, i);
	}
	/** Predicate: Array <%s> must NOT be of size %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean notOfLength(@Nonnull float[] array, int i) {
		Null.nonNullArg(array, "array");
		return P.notOfLength(array, i);
	}

	/** "Special" predicate: Array <%s> must NOT be of size %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static @Nullable String notOfLengthEx(@Nonnull float[] array, int i) {
		Null.nonNullArg(array, "array");
		return P.notOfLengthEx(array, i);
	}

	/** Predicate: Array <%s> must be empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean empty(@Nonnull float[] array) {
		Null.nonNullArg(array, "array");
		return P.empty(array);
	}

	/** "Special" predicate: Array <%s> must be empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static @Nullable String emptyEx(@Nonnull float[] array) {
		Null.nonNullArg(array, "array");
		return P.emptyEx(array);
	}
	/** Predicate: Array <%s> must NOT be empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean notEmpty(@Nonnull float[] array) {
		Null.nonNullArg(array, "array");
		return P.notEmpty(array);
	}

	/** "Special" predicate: Array <%s> must NOT be empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static @Nullable String notEmptyEx(@Nonnull float[] array) {
		Null.nonNullArg(array, "array");
		return P.notEmptyEx(array);
	}

	/** Predicate: Array <%s> must be null or empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean nullOrEmpty(@Nullable float[] array) {
		return P.nullOrEmpty(array);
	}

	/** "Special" predicate: Array <%s> must be null or empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static @Nullable String nullOrEmptyEx(@Nullable float[] array) {
		return P.nullOrEmptyEx(array);
	}
	/** Predicate: Array <%s> must NOT be null or empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean notNullNotEmpty(@Nullable float[] array) {
		return P.notNullNotEmpty(array);
	}

	/** "Special" predicate: Array <%s> must NOT be null or empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static @Nullable String notNullNotEmptyEx(@Nullable float[] array) {
		return P.notNullNotEmptyEx(array);
	}

	/** Predicate: Array <%s> must be exactly of size 1 (singleton). Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean singleton(@Nonnull float[] array) {
		Null.nonNullArg(array, "array");
		return P.singleton(array);
	}

	/** "Special" predicate: Array <%s> must be exactly of size 1 (singleton). Available in {@link P}, {@link Is}, {@link Be}.*/
	public static @Nullable String singletonEx(@Nonnull float[] array) {
		Null.nonNullArg(array, "array");
		return P.singletonEx(array);
	}
	/** Predicate: Array <%s> must NOT be exactly of size 1 (singleton). Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean notSingleton(@Nonnull float[] array) {
		Null.nonNullArg(array, "array");
		return P.notSingleton(array);
	}

	/** "Special" predicate: Array <%s> must NOT be exactly of size 1 (singleton). Available in {@link P}, {@link Is}, {@link Be}.*/
	public static @Nullable String notSingletonEx(@Nonnull float[] array) {
		Null.nonNullArg(array, "array");
		return P.notSingletonEx(array);
	}

	/** Predicate: Array <%s> must be equal to array <%s>. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean equal(float[] a1, float[] a2) {
		return P.equal(a1, a2);
	}

	/** "Special" predicate: Array <%s> must be equal to array <%s>. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static @Nullable String equalEx(float[] a1, float[] a2) {
		return P.equalEx(a1, a2);
	}
	/** Predicate: Array <%s> must NOT be equal to array <%s>. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean notEqual(float[] a1, float[] a2) {
		return P.notEqual(a1, a2);
	}

	/** "Special" predicate: Array <%s> must NOT be equal to array <%s>. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static @Nullable String notEqualEx(float[] a1, float[] a2) {
		return P.notEqualEx(a1, a2);
	}

	/** Predicate: Array <%s> must be of size %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean ofLength(@Nonnull int[] array, int i) {
		Null.nonNullArg(array, "array");
		return P.ofLength(array, i);
	}

	/** "Special" predicate: Array <%s> must be of size %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static @Nullable String ofLengthEx(@Nonnull int[] array, int i) {
		Null.nonNullArg(array, "array");
		return P.ofLengthEx(array, i);
	}
	/** Predicate: Array <%s> must NOT be of size %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean notOfLength(@Nonnull int[] array, int i) {
		Null.nonNullArg(array, "array");
		return P.notOfLength(array, i);
	}

	/** "Special" predicate: Array <%s> must NOT be of size %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static @Nullable String notOfLengthEx(@Nonnull int[] array, int i) {
		Null.nonNullArg(array, "array");
		return P.notOfLengthEx(array, i);
	}

	/** Predicate: Array <%s> must be empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean empty(@Nonnull int[] array) {
		Null.nonNullArg(array, "array");
		return P.empty(array);
	}

	/** "Special" predicate: Array <%s> must be empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static @Nullable String emptyEx(@Nonnull int[] array) {
		Null.nonNullArg(array, "array");
		return P.emptyEx(array);
	}
	/** Predicate: Array <%s> must NOT be empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean notEmpty(@Nonnull int[] array) {
		Null.nonNullArg(array, "array");
		return P.notEmpty(array);
	}

	/** "Special" predicate: Array <%s> must NOT be empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static @Nullable String notEmptyEx(@Nonnull int[] array) {
		Null.nonNullArg(array, "array");
		return P.notEmptyEx(array);
	}

	/** Predicate: Array <%s> must be null or empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean nullOrEmpty(@Nullable int[] array) {
		return P.nullOrEmpty(array);
	}

	/** "Special" predicate: Array <%s> must be null or empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static @Nullable String nullOrEmptyEx(@Nullable int[] array) {
		return P.nullOrEmptyEx(array);
	}
	/** Predicate: Array <%s> must NOT be null or empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean notNullNotEmpty(@Nullable int[] array) {
		return P.notNullNotEmpty(array);
	}

	/** "Special" predicate: Array <%s> must NOT be null or empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static @Nullable String notNullNotEmptyEx(@Nullable int[] array) {
		return P.notNullNotEmptyEx(array);
	}

	/** Predicate: Array <%s> must be exactly of size 1 (singleton). Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean singleton(@Nonnull int[] array) {
		Null.nonNullArg(array, "array");
		return P.singleton(array);
	}

	/** "Special" predicate: Array <%s> must be exactly of size 1 (singleton). Available in {@link P}, {@link Is}, {@link Be}.*/
	public static @Nullable String singletonEx(@Nonnull int[] array) {
		Null.nonNullArg(array, "array");
		return P.singletonEx(array);
	}
	/** Predicate: Array <%s> must NOT be exactly of size 1 (singleton). Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean notSingleton(@Nonnull int[] array) {
		Null.nonNullArg(array, "array");
		return P.notSingleton(array);
	}

	/** "Special" predicate: Array <%s> must NOT be exactly of size 1 (singleton). Available in {@link P}, {@link Is}, {@link Be}.*/
	public static @Nullable String notSingletonEx(@Nonnull int[] array) {
		Null.nonNullArg(array, "array");
		return P.notSingletonEx(array);
	}

	/** Predicate: Array <%s> must be equal to array <%s>. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean equal(int[] a1, int[] a2) {
		return P.equal(a1, a2);
	}

	/** "Special" predicate: Array <%s> must be equal to array <%s>. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static @Nullable String equalEx(int[] a1, int[] a2) {
		return P.equalEx(a1, a2);
	}
	/** Predicate: Array <%s> must NOT be equal to array <%s>. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean notEqual(int[] a1, int[] a2) {
		return P.notEqual(a1, a2);
	}

	/** "Special" predicate: Array <%s> must NOT be equal to array <%s>. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static @Nullable String notEqualEx(int[] a1, int[] a2) {
		return P.notEqualEx(a1, a2);
	}

	/** Predicate: Array <%s> must be of size %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean ofLength(@Nonnull long[] array, int i) {
		Null.nonNullArg(array, "array");
		return P.ofLength(array, i);
	}

	/** "Special" predicate: Array <%s> must be of size %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static @Nullable String ofLengthEx(@Nonnull long[] array, int i) {
		Null.nonNullArg(array, "array");
		return P.ofLengthEx(array, i);
	}
	/** Predicate: Array <%s> must NOT be of size %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean notOfLength(@Nonnull long[] array, int i) {
		Null.nonNullArg(array, "array");
		return P.notOfLength(array, i);
	}

	/** "Special" predicate: Array <%s> must NOT be of size %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static @Nullable String notOfLengthEx(@Nonnull long[] array, int i) {
		Null.nonNullArg(array, "array");
		return P.notOfLengthEx(array, i);
	}

	/** Predicate: Array <%s> must be empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean empty(@Nonnull long[] array) {
		Null.nonNullArg(array, "array");
		return P.empty(array);
	}

	/** "Special" predicate: Array <%s> must be empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static @Nullable String emptyEx(@Nonnull long[] array) {
		Null.nonNullArg(array, "array");
		return P.emptyEx(array);
	}
	/** Predicate: Array <%s> must NOT be empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean notEmpty(@Nonnull long[] array) {
		Null.nonNullArg(array, "array");
		return P.notEmpty(array);
	}

	/** "Special" predicate: Array <%s> must NOT be empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static @Nullable String notEmptyEx(@Nonnull long[] array) {
		Null.nonNullArg(array, "array");
		return P.notEmptyEx(array);
	}

	/** Predicate: Array <%s> must be null or empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean nullOrEmpty(@Nullable long[] array) {
		return P.nullOrEmpty(array);
	}

	/** "Special" predicate: Array <%s> must be null or empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static @Nullable String nullOrEmptyEx(@Nullable long[] array) {
		return P.nullOrEmptyEx(array);
	}
	/** Predicate: Array <%s> must NOT be null or empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean notNullNotEmpty(@Nullable long[] array) {
		return P.notNullNotEmpty(array);
	}

	/** "Special" predicate: Array <%s> must NOT be null or empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static @Nullable String notNullNotEmptyEx(@Nullable long[] array) {
		return P.notNullNotEmptyEx(array);
	}

	/** Predicate: Array <%s> must be exactly of size 1 (singleton). Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean singleton(@Nonnull long[] array) {
		Null.nonNullArg(array, "array");
		return P.singleton(array);
	}

	/** "Special" predicate: Array <%s> must be exactly of size 1 (singleton). Available in {@link P}, {@link Is}, {@link Be}.*/
	public static @Nullable String singletonEx(@Nonnull long[] array) {
		Null.nonNullArg(array, "array");
		return P.singletonEx(array);
	}
	/** Predicate: Array <%s> must NOT be exactly of size 1 (singleton). Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean notSingleton(@Nonnull long[] array) {
		Null.nonNullArg(array, "array");
		return P.notSingleton(array);
	}

	/** "Special" predicate: Array <%s> must NOT be exactly of size 1 (singleton). Available in {@link P}, {@link Is}, {@link Be}.*/
	public static @Nullable String notSingletonEx(@Nonnull long[] array) {
		Null.nonNullArg(array, "array");
		return P.notSingletonEx(array);
	}

	/** Predicate: Array <%s> must be equal to array <%s>. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean equal(long[] a1, long[] a2) {
		return P.equal(a1, a2);
	}

	/** "Special" predicate: Array <%s> must be equal to array <%s>. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static @Nullable String equalEx(long[] a1, long[] a2) {
		return P.equalEx(a1, a2);
	}
	/** Predicate: Array <%s> must NOT be equal to array <%s>. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean notEqual(long[] a1, long[] a2) {
		return P.notEqual(a1, a2);
	}

	/** "Special" predicate: Array <%s> must NOT be equal to array <%s>. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static @Nullable String notEqualEx(long[] a1, long[] a2) {
		return P.notEqualEx(a1, a2);
	}

	/** Predicate: Array <%s> must be of size %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static <T> boolean ofLength(@Nonnull T[] array, int i) {
		Null.nonNullArg(array, "array");
		return P.ofLength(array, i);
	}

	/** "Special" predicate: Array <%s> must be of size %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static <T> @Nullable String ofLengthEx(@Nonnull T[] array, int i) {
		Null.nonNullArg(array, "array");
		return P.ofLengthEx(array, i);
	}
	/** Predicate: Array <%s> must NOT be of size %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static <T> boolean notOfLength(@Nonnull T[] array, int i) {
		Null.nonNullArg(array, "array");
		return P.notOfLength(array, i);
	}

	/** "Special" predicate: Array <%s> must NOT be of size %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static <T> @Nullable String notOfLengthEx(@Nonnull T[] array, int i) {
		Null.nonNullArg(array, "array");
		return P.notOfLengthEx(array, i);
	}

	/** Predicate: Array <%s> must be empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static <T> boolean empty(@Nonnull T[] array) {
		Null.nonNullArg(array, "array");
		return P.empty(array);
	}

	/** "Special" predicate: Array <%s> must be empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static <T> @Nullable String emptyEx(@Nonnull T[] array) {
		Null.nonNullArg(array, "array");
		return P.emptyEx(array);
	}
	/** Predicate: Array <%s> must NOT be empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static <T> boolean notEmpty(@Nonnull T[] array) {
		Null.nonNullArg(array, "array");
		return P.notEmpty(array);
	}

	/** "Special" predicate: Array <%s> must NOT be empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static <T> @Nullable String notEmptyEx(@Nonnull T[] array) {
		Null.nonNullArg(array, "array");
		return P.notEmptyEx(array);
	}

	/** Predicate: Array <%s> must be null or empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static <T> boolean nullOrEmpty(@Nullable T[] array) {
		return P.nullOrEmpty(array);
	}

	/** "Special" predicate: Array <%s> must be null or empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static <T> @Nullable String nullOrEmptyEx(@Nullable T[] array) {
		return P.nullOrEmptyEx(array);
	}
	/** Predicate: Array <%s> must NOT be null or empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static <T> boolean notNullNotEmpty(@Nullable T[] array) {
		return P.notNullNotEmpty(array);
	}

	/** "Special" predicate: Array <%s> must NOT be null or empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static <T> @Nullable String notNullNotEmptyEx(@Nullable T[] array) {
		return P.notNullNotEmptyEx(array);
	}

	/** Predicate: Array <%s> must be exactly of size 1 (singleton). Available in {@link P}, {@link Is}, {@link Be}.*/
	public static <T> boolean singleton(@Nonnull T[] array) {
		Null.nonNullArg(array, "array");
		return P.singleton(array);
	}

	/** "Special" predicate: Array <%s> must be exactly of size 1 (singleton). Available in {@link P}, {@link Is}, {@link Be}.*/
	public static <T> @Nullable String singletonEx(@Nonnull T[] array) {
		Null.nonNullArg(array, "array");
		return P.singletonEx(array);
	}
	/** Predicate: Array <%s> must NOT be exactly of size 1 (singleton). Available in {@link P}, {@link Is}, {@link Be}.*/
	public static <T> boolean notSingleton(@Nonnull T[] array) {
		Null.nonNullArg(array, "array");
		return P.notSingleton(array);
	}

	/** "Special" predicate: Array <%s> must NOT be exactly of size 1 (singleton). Available in {@link P}, {@link Is}, {@link Be}.*/
	public static <T> @Nullable String notSingletonEx(@Nonnull T[] array) {
		Null.nonNullArg(array, "array");
		return P.notSingletonEx(array);
	}

	/** Predicate: Array <%s> must be equal to array <%s>. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static <T> boolean equal(T[] a1, T[] a2) {
		return P.equal(a1, a2);
	}

	/** "Special" predicate: Array <%s> must be equal to array <%s>. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static <T> @Nullable String equalEx(T[] a1, T[] a2) {
		return P.equalEx(a1, a2);
	}
	/** Predicate: Array <%s> must NOT be equal to array <%s>. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static <T> boolean notEqual(T[] a1, T[] a2) {
		return P.notEqual(a1, a2);
	}

	/** "Special" predicate: Array <%s> must NOT be equal to array <%s>. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static <T> @Nullable String notEqualEx(T[] a1, T[] a2) {
		return P.notEqualEx(a1, a2);
	}

	// </editor-fold>

	// <editor-fold desc="collections">

	/** Predicate: Collection <%s> must be of size %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static <T> boolean ofSize(@Nonnull Collection<T> collection, int i) {
		Null.nonNullArg(collection, "collection");
		return P.ofSize(collection, i);
	}

	/** "Special" predicate: Collection <%s> must be of size %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static <T> @Nullable String ofSizeEx(@Nonnull Collection<T> collection, int i) {
		Null.nonNullArg(collection, "collection");
		return P.ofSizeEx(collection, i);
	}
	/** Predicate: Collection <%s> must NOT be of size %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static <T> boolean notOfSize(@Nonnull Collection<T> collection, int i) {
		Null.nonNullArg(collection, "collection");
		return P.notOfSize(collection, i);
	}

	/** "Special" predicate: Collection <%s> must NOT be of size %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static <T> @Nullable String notOfSizeEx(@Nonnull Collection<T> collection, int i) {
		Null.nonNullArg(collection, "collection");
		return P.notOfSizeEx(collection, i);
	}

	/** Predicate: Map <%s> must be of size %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static <K, V> boolean ofSize(@Nonnull Map<K, V> map, int i) {
		Null.nonNullArg(map, "map");
		return P.ofSize(map, i);
	}

	/** "Special" predicate: Map <%s> must be of size %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static <K, V> @Nullable String ofSizeEx(@Nonnull Map<K, V> map, int i) {
		Null.nonNullArg(map, "map");
		return P.ofSizeEx(map, i);
	}
	/** Predicate: Map <%s> must NOT be of size %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static <K, V> boolean notOfSize(@Nonnull Map<K, V> map, int i) {
		Null.nonNullArg(map, "map");
		return P.notOfSize(map, i);
	}

	/** "Special" predicate: Map <%s> must NOT be of size %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static <K, V> @Nullable String notOfSizeEx(@Nonnull Map<K, V> map, int i) {
		Null.nonNullArg(map, "map");
		return P.notOfSizeEx(map, i);
	}

	/** Predicate: <%s> must be part of <%s> collection. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static <T> boolean partOf(T element, @Nonnull Collection<T> collection) {
		Null.nonNullArg(collection, "collection");
		return P.partOf(element, collection);
	}

	/** "Special" predicate: <%s> must be part of <%s> collection. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static <T> @Nullable String partOfEx(T element, @Nonnull Collection<T> collection) {
		Null.nonNullArg(collection, "collection");
		return P.partOfEx(element, collection);
	}
	/** Predicate: <%s> must NOT be part of <%s> collection. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static <T> boolean notPartOf(T element, @Nonnull Collection<T> collection) {
		Null.nonNullArg(collection, "collection");
		return P.notPartOf(element, collection);
	}

	/** "Special" predicate: <%s> must NOT be part of <%s> collection. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static <T> @Nullable String notPartOfEx(T element, @Nonnull Collection<T> collection) {
		Null.nonNullArg(collection, "collection");
		return P.notPartOfEx(element, collection);
	}

	/** Predicate: <%s> must be key in <%s> map. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static <K> boolean aKeyIn(K key, @Nonnull Map<K, ?> map) {
		Null.nonNullArg(map, "map");
		return P.aKeyIn(key, map);
	}

	/** "Special" predicate: <%s> must be key in <%s> map. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static <K> @Nullable String aKeyInEx(K key, @Nonnull Map<K, ?> map) {
		Null.nonNullArg(map, "map");
		return P.aKeyInEx(key, map);
	}
	/** Predicate: <%s> must NOT be key in <%s> map. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static <K> boolean notAKeyIn(K key, @Nonnull Map<K, ?> map) {
		Null.nonNullArg(map, "map");
		return P.notAKeyIn(key, map);
	}

	/** "Special" predicate: <%s> must NOT be key in <%s> map. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static <K> @Nullable String notAKeyInEx(K key, @Nonnull Map<K, ?> map) {
		Null.nonNullArg(map, "map");
		return P.notAKeyInEx(key, map);
	}

	/** Predicate: Collection <%s> must be empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static <T> boolean empty(@Nonnull Collection<T> collection) {
		Null.nonNullArg(collection, "collection");
		return P.empty(collection);
	}

	/** "Special" predicate: Collection <%s> must be empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static <T> @Nullable String emptyEx(@Nonnull Collection<T> collection) {
		Null.nonNullArg(collection, "collection");
		return P.emptyEx(collection);
	}
	/** Predicate: Collection <%s> must NOT be empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static <T> boolean notEmpty(@Nonnull Collection<T> collection) {
		Null.nonNullArg(collection, "collection");
		return P.notEmpty(collection);
	}

	/** "Special" predicate: Collection <%s> must NOT be empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static <T> @Nullable String notEmptyEx(@Nonnull Collection<T> collection) {
		Null.nonNullArg(collection, "collection");
		return P.notEmptyEx(collection);
	}

	/** Predicate: Collection <%s> must be empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static <T> boolean nullOrEmpty(@Nullable Collection<T> collection) {
		return P.nullOrEmpty(collection);
	}

	/** "Special" predicate: Collection <%s> must be empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static <T> @Nullable String nullOrEmptyEx(@Nullable Collection<T> collection) {
		return P.nullOrEmptyEx(collection);
	}
	/** Predicate: Collection <%s> must NOT be empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static <T> boolean notNullNorEmpty(@Nullable Collection<T> collection) {
		return P.notNullNorEmpty(collection);
	}

	/** "Special" predicate: Collection <%s> must NOT be empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static <T> @Nullable String notNullNorEmptyEx(@Nullable Collection<T> collection) {
		return P.notNullNorEmptyEx(collection);
	}

	/** Predicate: Collection <%s> must be exactly of size 1 (singleton).. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static <T> boolean singleton(@Nonnull Collection<T> collection) {
		Null.nonNullArg(collection, "collection");
		return P.singleton(collection);
	}

	/** "Special" predicate: Collection <%s> must be exactly of size 1 (singleton).. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static <T> @Nullable String singletonEx(@Nonnull Collection<T> collection) {
		Null.nonNullArg(collection, "collection");
		return P.singletonEx(collection);
	}
	/** Predicate: Collection <%s> must NOT be exactly of size 1 (singleton).. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static <T> boolean notSingleton(@Nonnull Collection<T> collection) {
		Null.nonNullArg(collection, "collection");
		return P.notSingleton(collection);
	}

	/** "Special" predicate: Collection <%s> must NOT be exactly of size 1 (singleton).. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static <T> @Nullable String notSingletonEx(@Nonnull Collection<T> collection) {
		Null.nonNullArg(collection, "collection");
		return P.notSingletonEx(collection);
	}

	/** Predicate: Map <%s> must be empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean empty(@Nonnull Map<?, ?> map) {
		Null.nonNullArg(map, "map");
		return P.empty(map);
	}

	/** "Special" predicate: Map <%s> must be empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static @Nullable String emptyEx(@Nonnull Map<?, ?> map) {
		Null.nonNullArg(map, "map");
		return P.emptyEx(map);
	}
	/** Predicate: Map <%s> must NOT be empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean notEmpty(@Nonnull Map<?, ?> map) {
		Null.nonNullArg(map, "map");
		return P.notEmpty(map);
	}

	/** "Special" predicate: Map <%s> must NOT be empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static @Nullable String notEmptyEx(@Nonnull Map<?, ?> map) {
		Null.nonNullArg(map, "map");
		return P.notEmptyEx(map);
	}

	/** Predicate: Collection <%s> must be empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean nullOrEmpty(@Nullable Map<?, ?> map) {
		return P.nullOrEmpty(map);
	}

	/** "Special" predicate: Collection <%s> must be empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static @Nullable String nullOrEmptyEx(@Nullable Map<?, ?> map) {
		return P.nullOrEmptyEx(map);
	}
	/** Predicate: Collection <%s> must NOT be empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean notNullNorEmpty(@Nullable Map<?, ?> map) {
		return P.notNullNorEmpty(map);
	}

	/** "Special" predicate: Collection <%s> must NOT be empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static @Nullable String notNullNorEmptyEx(@Nullable Map<?, ?> map) {
		return P.notNullNorEmptyEx(map);
	}

	/** Predicate: Collection <%s> must be exactly of size 1 (singleton).. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean singleton(@Nonnull Map<?, ?> map) {
		Null.nonNullArg(map, "map");
		return P.singleton(map);
	}

	/** "Special" predicate: Collection <%s> must be exactly of size 1 (singleton).. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static @Nullable String singletonEx(@Nonnull Map<?, ?> map) {
		Null.nonNullArg(map, "map");
		return P.singletonEx(map);
	}
	/** Predicate: Collection <%s> must NOT be exactly of size 1 (singleton).. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean notSingleton(@Nonnull Map<?, ?> map) {
		Null.nonNullArg(map, "map");
		return P.notSingleton(map);
	}

	/** "Special" predicate: Collection <%s> must NOT be exactly of size 1 (singleton).. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static @Nullable String notSingletonEx(@Nonnull Map<?, ?> map) {
		Null.nonNullArg(map, "map");
		return P.notSingletonEx(map);
	}

	// </editor-fold>

	// <editor-fold desc="object derivatives">

	/** Predicate: Object <%s> of class <%s> must be instance of <%s>. Available in {@link P}, {@link Is}, {@link Be}, {@link P1}, {@link P2}.*/
	public static boolean instanceOf(Object object, Class<?> clazz) {
		Null.nonNullArg(clazz, "clazz");
		return P.instanceOf(object, clazz);
	}

	/** "Special" predicate: Object <%s> of class <%s> must be instance of <%s>. Available in {@link P}, {@link Is}, {@link Be}, {@link P1}, {@link P2}.*/
	public static @Nullable String instanceOfEx(Object object, Class<?> clazz) {
		Null.nonNullArg(clazz, "clazz");
		return P.instanceOfEx(object, clazz);
	}
	/** Predicate: Object <%s> of class <%s> must NOT be instance of <%s>. Available in {@link P}, {@link Is}, {@link Be}, {@link P1}, {@link P2}.*/
	public static boolean notInstanceOf(Object object, Class<?> clazz) {
		Null.nonNullArg(clazz, "clazz");
		return P.notInstanceOf(object, clazz);
	}

	/** "Special" predicate: Object <%s> of class <%s> must NOT be instance of <%s>. Available in {@link P}, {@link Is}, {@link Be}, {@link P1}, {@link P2}.*/
	public static @Nullable String notInstanceOfEx(Object object, Class<?> clazz) {
		Null.nonNullArg(clazz, "clazz");
		return P.notInstanceOfEx(object, clazz);
	}

	/** Predicate: Object <%s> of class <%s> must be exactly instance of <%s>. Available in {@link P}, {@link Is}, {@link Be}, {@link P1}, {@link P2}.*/
	public static boolean exactlyInstanceOf(Object object, Class<?> clazz) {
		Null.nonNullArg(clazz, "clazz");
		return P.exactlyInstanceOf(object, clazz);
	}

	/** "Special" predicate: Object <%s> of class <%s> must be exactly instance of <%s>. Available in {@link P}, {@link Is}, {@link Be}, {@link P1}, {@link P2}.*/
	public static @Nullable String exactlyInstanceOfEx(Object object, Class<?> clazz) {
		Null.nonNullArg(clazz, "clazz");
		return P.exactlyInstanceOfEx(object, clazz);
	}
	/** Predicate: Object <%s> of class <%s> must NOT be exactly instance of <%s>. Available in {@link P}, {@link Is}, {@link Be}, {@link P1}, {@link P2}.*/
	public static boolean notExactlyInstanceOf(Object object, Class<?> clazz) {
		Null.nonNullArg(clazz, "clazz");
		return P.notExactlyInstanceOf(object, clazz);
	}

	/** "Special" predicate: Object <%s> of class <%s> must NOT be exactly instance of <%s>. Available in {@link P}, {@link Is}, {@link Be}, {@link P1}, {@link P2}.*/
	public static @Nullable String notExactlyInstanceOfEx(Object object, Class<?> clazz) {
		Null.nonNullArg(clazz, "clazz");
		return P.notExactlyInstanceOfEx(object, clazz);
	}

	/** Predicate: Class <%s> of class <%s> must ---NOT-- be specialization of <%s>. Available in {@link P}, {@link Is}, {@link Be}, {@link P1}, {@link P2}.*/
	public static boolean assignableFrom(Class<?> specialization, Class<?> clazz) {
		Null.nonNullArg(clazz, "clazz");
		return P.assignableFrom(specialization, clazz);
	}

	/** "Special" predicate: Class <%s> of class <%s> must ---NOT-- be specialization of <%s>. Available in {@link P}, {@link Is}, {@link Be}, {@link P1}, {@link P2}.*/
	public static @Nullable String assignableFromEx(Class<?> specialization, Class<?> clazz) {
		Null.nonNullArg(clazz, "clazz");
		return P.assignableFromEx(specialization, clazz);
	}
	/** Predicate: Class <%s> of class <%s> must ---NOT-- be specialization of <%s>. Available in {@link P}, {@link Is}, {@link Be}, {@link P1}, {@link P2}.*/
	public static boolean notAssignableFrom(Class<?> specialization, Class<?> clazz) {
		Null.nonNullArg(clazz, "clazz");
		return P.notAssignableFrom(specialization, clazz);
	}

	/** "Special" predicate: Class <%s> of class <%s> must ---NOT-- be specialization of <%s>. Available in {@link P}, {@link Is}, {@link Be}, {@link P1}, {@link P2}.*/
	public static @Nullable String notAssignableFromEx(Class<?> specialization, Class<?> clazz) {
		Null.nonNullArg(clazz, "clazz");
		return P.notAssignableFromEx(specialization, clazz);
	}

	// </editor-fold>

	// <editor-fold desc="Throwables">

	/** Predicate: Exception <%s> must be instance of a RuntimeException. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean runtime(@Nonnull Throwable e) {
		Null.nonNullArg(e, "e");
		return P.runtime(e);
	}

	/** "Special" predicate: Exception <%s> must be instance of a RuntimeException. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static @Nullable String runtimeEx(@Nonnull Throwable e) {
		Null.nonNullArg(e, "e");
		return P.runtimeEx(e);
	}
	/** Predicate: Exception <%s> must NOT be instance of a RuntimeException. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean notRuntime(@Nonnull Throwable e) {
		Null.nonNullArg(e, "e");
		return P.notRuntime(e);
	}

	/** "Special" predicate: Exception <%s> must NOT be instance of a RuntimeException. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static @Nullable String notRuntimeEx(@Nonnull Throwable e) {
		Null.nonNullArg(e, "e");
		return P.notRuntimeEx(e);
	}

	/** Predicate: Exception <%s> must have suppressed other exceptions. Available in {@link P}, {@link Is}, {@link Has}, {@link Have}.*/
	public static boolean suppressing(@Nonnull Throwable e) {
		Null.nonNullArg(e, "e");
		return P.suppressing(e);
	}

	/** "Special" predicate: Exception <%s> must have suppressed other exceptions. Available in {@link P}, {@link Is}, {@link Has}, {@link Have}.*/
	public static @Nullable String suppressingEx(@Nonnull Throwable e) {
		Null.nonNullArg(e, "e");
		return P.suppressingEx(e);
	}
	/** Predicate: Exception <%s> must NOT have suppressed other exceptions. Available in {@link P}, {@link Is}, {@link Has}, {@link Have}.*/
	public static boolean notSuppressing(@Nonnull Throwable e) {
		Null.nonNullArg(e, "e");
		return P.notSuppressing(e);
	}

	/** "Special" predicate: Exception <%s> must NOT have suppressed other exceptions. Available in {@link P}, {@link Is}, {@link Has}, {@link Have}.*/
	public static @Nullable String notSuppressingEx(@Nonnull Throwable e) {
		Null.nonNullArg(e, "e");
		return P.notSuppressingEx(e);
	}

	// </editor-fold>

	// <editor-fold desc="Value (e.g. Opt )">

	/** Predicate: Optional <%s> must be void. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static <V> boolean Void(@Nonnull OptBoolTrait<?> opt) {
		Null.nonNullArg(opt, "opt");
		return P.Void(opt);
	}

	/** "Special" predicate: Optional <%s> must be void. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static <V> @Nullable String VoidEx(@Nonnull OptBoolTrait<?> opt) {
		Null.nonNullArg(opt, "opt");
		return P.VoidEx(opt);
	}
	/** Predicate: Optional <%s> must NOT be void. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static <V> boolean notVoid(@Nonnull OptBoolTrait<?> opt) {
		Null.nonNullArg(opt, "opt");
		return P.notVoid(opt);
	}

	/** "Special" predicate: Optional <%s> must NOT be void. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static <V> @Nullable String notVoidEx(@Nonnull OptBoolTrait<?> opt) {
		Null.nonNullArg(opt, "opt");
		return P.notVoidEx(opt);
	}

	/** Predicate: Optional <%s> must be void. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static <V> boolean Void(@Nonnull OptTrait<?, ?> opt) {
		Null.nonNullArg(opt, "opt");
		return P.Void(opt);
	}

	/** "Special" predicate: Optional <%s> must be void. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static <V> @Nullable String VoidEx(@Nonnull OptTrait<?, ?> opt) {
		Null.nonNullArg(opt, "opt");
		return P.VoidEx(opt);
	}
	/** Predicate: Optional <%s> must NOT be void. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static <V> boolean notVoid(@Nonnull OptTrait<?, ?> opt) {
		Null.nonNullArg(opt, "opt");
		return P.notVoid(opt);
	}

	/** "Special" predicate: Optional <%s> must NOT be void. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static <V> @Nullable String notVoidEx(@Nonnull OptTrait<?, ?> opt) {
		Null.nonNullArg(opt, "opt");
		return P.notVoidEx(opt);
	}

	/** Predicate: Optional <%s> must have value equal <%s>. Available in {@link P}, {@link Is}, {@link Has}, {@link Have}.*/
	public static <V> boolean sameValue(@Nonnull ValueTrait<?, ?> opt, V expected) {
		Null.nonNullArg(opt, "opt");
		return P.sameValue(opt, expected);
	}

	/** "Special" predicate: Optional <%s> must have value equal <%s>. Available in {@link P}, {@link Is}, {@link Has}, {@link Have}.*/
	public static <V> @Nullable String sameValueEx(@Nonnull ValueTrait<?, ?> opt, V expected) {
		Null.nonNullArg(opt, "opt");
		return P.sameValueEx(opt, expected);
	}
	/** Predicate: Optional <%s> must NOT have value equal <%s>. Available in {@link P}, {@link Is}, {@link Has}, {@link Have}.*/
	public static <V> boolean notSameValue(@Nonnull ValueTrait<?, ?> opt, V expected) {
		Null.nonNullArg(opt, "opt");
		return P.notSameValue(opt, expected);
	}

	/** "Special" predicate: Optional <%s> must NOT have value equal <%s>. Available in {@link P}, {@link Is}, {@link Has}, {@link Have}.*/
	public static <V> @Nullable String notSameValueEx(@Nonnull ValueTrait<?, ?> opt, V expected) {
		Null.nonNullArg(opt, "opt");
		return P.notSameValueEx(opt, expected);
	}

	/** Predicate: Optional <%s> must be void. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static <V> boolean Void(@Nonnull OptByteTrait<?> opt) {
		Null.nonNullArg(opt, "opt");
		return P.Void(opt);
	}

	/** "Special" predicate: Optional <%s> must be void. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static <V> @Nullable String VoidEx(@Nonnull OptByteTrait<?> opt) {
		Null.nonNullArg(opt, "opt");
		return P.VoidEx(opt);
	}
	/** Predicate: Optional <%s> must NOT be void. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static <V> boolean notVoid(@Nonnull OptByteTrait<?> opt) {
		Null.nonNullArg(opt, "opt");
		return P.notVoid(opt);
	}

	/** "Special" predicate: Optional <%s> must NOT be void. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static <V> @Nullable String notVoidEx(@Nonnull OptByteTrait<?> opt) {
		Null.nonNullArg(opt, "opt");
		return P.notVoidEx(opt);
	}

	/** Predicate: Optional <%s> must be void. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static <V> boolean Void(@Nonnull OptDblTrait<?> opt) {
		Null.nonNullArg(opt, "opt");
		return P.Void(opt);
	}

	/** "Special" predicate: Optional <%s> must be void. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static <V> @Nullable String VoidEx(@Nonnull OptDblTrait<?> opt) {
		Null.nonNullArg(opt, "opt");
		return P.VoidEx(opt);
	}
	/** Predicate: Optional <%s> must NOT be void. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static <V> boolean notVoid(@Nonnull OptDblTrait<?> opt) {
		Null.nonNullArg(opt, "opt");
		return P.notVoid(opt);
	}

	/** "Special" predicate: Optional <%s> must NOT be void. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static <V> @Nullable String notVoidEx(@Nonnull OptDblTrait<?> opt) {
		Null.nonNullArg(opt, "opt");
		return P.notVoidEx(opt);
	}

	/** Predicate: Optional <%s> must be void. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static <V> boolean Void(@Nonnull OptCharTrait<?> opt) {
		Null.nonNullArg(opt, "opt");
		return P.Void(opt);
	}

	/** "Special" predicate: Optional <%s> must be void. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static <V> @Nullable String VoidEx(@Nonnull OptCharTrait<?> opt) {
		Null.nonNullArg(opt, "opt");
		return P.VoidEx(opt);
	}
	/** Predicate: Optional <%s> must NOT be void. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static <V> boolean notVoid(@Nonnull OptCharTrait<?> opt) {
		Null.nonNullArg(opt, "opt");
		return P.notVoid(opt);
	}

	/** "Special" predicate: Optional <%s> must NOT be void. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static <V> @Nullable String notVoidEx(@Nonnull OptCharTrait<?> opt) {
		Null.nonNullArg(opt, "opt");
		return P.notVoidEx(opt);
	}

	/** Predicate: Optional <%s> must be void. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static <V> boolean Void(@Nonnull OptSrtTrait<?> opt) {
		Null.nonNullArg(opt, "opt");
		return P.Void(opt);
	}

	/** "Special" predicate: Optional <%s> must be void. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static <V> @Nullable String VoidEx(@Nonnull OptSrtTrait<?> opt) {
		Null.nonNullArg(opt, "opt");
		return P.VoidEx(opt);
	}
	/** Predicate: Optional <%s> must NOT be void. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static <V> boolean notVoid(@Nonnull OptSrtTrait<?> opt) {
		Null.nonNullArg(opt, "opt");
		return P.notVoid(opt);
	}

	/** "Special" predicate: Optional <%s> must NOT be void. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static <V> @Nullable String notVoidEx(@Nonnull OptSrtTrait<?> opt) {
		Null.nonNullArg(opt, "opt");
		return P.notVoidEx(opt);
	}

	/** Predicate: Optional <%s> must be void. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static <V> boolean Void(@Nonnull OptFltTrait<?> opt) {
		Null.nonNullArg(opt, "opt");
		return P.Void(opt);
	}

	/** "Special" predicate: Optional <%s> must be void. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static <V> @Nullable String VoidEx(@Nonnull OptFltTrait<?> opt) {
		Null.nonNullArg(opt, "opt");
		return P.VoidEx(opt);
	}
	/** Predicate: Optional <%s> must NOT be void. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static <V> boolean notVoid(@Nonnull OptFltTrait<?> opt) {
		Null.nonNullArg(opt, "opt");
		return P.notVoid(opt);
	}

	/** "Special" predicate: Optional <%s> must NOT be void. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static <V> @Nullable String notVoidEx(@Nonnull OptFltTrait<?> opt) {
		Null.nonNullArg(opt, "opt");
		return P.notVoidEx(opt);
	}

	/** Predicate: Optional <%s> must be void. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static <V> boolean Void(@Nonnull OptIntTrait<?> opt) {
		Null.nonNullArg(opt, "opt");
		return P.Void(opt);
	}

	/** "Special" predicate: Optional <%s> must be void. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static <V> @Nullable String VoidEx(@Nonnull OptIntTrait<?> opt) {
		Null.nonNullArg(opt, "opt");
		return P.VoidEx(opt);
	}
	/** Predicate: Optional <%s> must NOT be void. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static <V> boolean notVoid(@Nonnull OptIntTrait<?> opt) {
		Null.nonNullArg(opt, "opt");
		return P.notVoid(opt);
	}

	/** "Special" predicate: Optional <%s> must NOT be void. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static <V> @Nullable String notVoidEx(@Nonnull OptIntTrait<?> opt) {
		Null.nonNullArg(opt, "opt");
		return P.notVoidEx(opt);
	}

	/** Predicate: Optional <%s> must be void. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static <V> boolean Void(@Nonnull OptLongTrait<?> opt) {
		Null.nonNullArg(opt, "opt");
		return P.Void(opt);
	}

	/** "Special" predicate: Optional <%s> must be void. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static <V> @Nullable String VoidEx(@Nonnull OptLongTrait<?> opt) {
		Null.nonNullArg(opt, "opt");
		return P.VoidEx(opt);
	}
	/** Predicate: Optional <%s> must NOT be void. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static <V> boolean notVoid(@Nonnull OptLongTrait<?> opt) {
		Null.nonNullArg(opt, "opt");
		return P.notVoid(opt);
	}

	/** "Special" predicate: Optional <%s> must NOT be void. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static <V> @Nullable String notVoidEx(@Nonnull OptLongTrait<?> opt) {
		Null.nonNullArg(opt, "opt");
		return P.notVoidEx(opt);
	}

	// </editor-fold>

}
