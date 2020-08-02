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
public final class Be implements FluentSyntax {
	// <editor-fold desc="no instance">
	private Be() {
	}
	// </editor-fold>

	// <editor-fold desc="Object">

	/** Predicate: Object <%s> must be the same as <%s>. Available in {@link P}, {@link Is}, {@link Be}, {@link P1}, {@link P2}.*/
	public static boolean same(@Nullable Object n, @Nullable Object other) {
		return n == other;
	}

	/** "Special" predicate: Object <%s> must be the same as <%s>. Available in {@link P}, {@link Is}, {@link Be}, {@link P1}, {@link P2}.*/
	public static String sameEx(@Nullable Object n, @Nullable Object other) {
		return (n == other) ? null : String.format("Object <%s> must be the same as <%s>.", n, other);
	}

	/** Predicate: Object <%s> must NOT be the same as <%s>. Available in {@link P}, {@link Is}, {@link Be}, {@link P1}, {@link P2}.*/
	public static boolean notSame(@Nullable Object n, @Nullable Object other) {
		return !(n == other);
	}

	/** "Special" predicate: Object <%s> must NOT be the same as <%s>. Available in {@link P}, {@link Is}, {@link Be}, {@link P1}, {@link P2}.*/
	public static String notSameEx(@Nullable Object n, @Nullable Object other) {
		return !(n == other) ? null : String.format("Object <%s> must NOT be the same as <%s>.", n, other);
	}

	/** Predicate: Reference must be null, currently is pointing to <%s>. Available in {@link P}, {@link Is}, {@link Be}, {@link P1}, {@link P2}.*/
	public static boolean Null(@Nullable Object n) {
		return n == null;
	}

	/** "Special" predicate: Reference must be null, currently is pointing to <%s>. Available in {@link P}, {@link Is}, {@link Be}, {@link P1}, {@link P2}.*/
	public static String NullEx(@Nullable Object n) {
		return (n == null) ? null : String.format("Reference must be null, currently is pointing to <%s>.", n);
	}

	/** Predicate: Reference must NOT be null, currently is pointing to <%s>. Available in {@link P}, {@link Is}, {@link Be}, {@link P1}, {@link P2}.*/
	public static boolean notNull(@Nullable Object n) {
		return !(n == null);
	}

	/** "Special" predicate: Reference must NOT be null, currently is pointing to <%s>. Available in {@link P}, {@link Is}, {@link Be}, {@link P1}, {@link P2}.*/
	public static String notNullEx(@Nullable Object n) {
		return !(n == null) ? null : String.format("Reference must NOT be null, currently is pointing to <%s>.", n);
	}

	public static boolean allNull(@Nullable Object... objects) {
		if (objects == null) {
			return true;
		}
		for (Object o : objects) {
			if (o != null) {
				return false;
			}
		}

		return true;
	}

	public static String allNullEx(@Nullable Object... objects) {
		return allNull(objects) ? null : String.format("All references must be null.");
	}

	public static boolean noneNull(@Nullable Object... objects) {
		if (objects == null) {
			return false;
		}
		for (Object o : objects) {
			if (o == null) {
				return false;
			}
		}

		return true;
	}

	public static String noneNullEx(@Nullable Object... objects) {
		return noneNull(objects) ? null : String.format("All references must be NOT null.");
	}

	public static boolean anyNull(@Nullable Object... objects) {
		if (objects == null) {
			return true;
		}
		for (Object o : objects) {
			if (o == null) {
				return true;
			}
		}

		return false;
	}

	public static String anyNullEx(@Nullable Object... objects) {
		return anyNull(objects) ? null : String.format("At least one references must be null.");
	}

	// </editor-fold>

	// <editor-fold desc="`String`">

	/** Predicate: String <'%s'> must be <%d> characters long. Available in {@link P}, {@link Has}, {@link Have}, {@link Is}, {@link Be}.*/
	public static boolean ofLength(@Nonnull CharSequence s, int size) {
		Null.nonNullArg(s, "s");
		return size == s.length();
	}

	/** "Special" predicate: String <'%s'> must be <%d> characters long. Available in {@link P}, {@link Has}, {@link Have}, {@link Is}, {@link Be}.*/
	public static String ofLengthEx(@Nonnull CharSequence s, int size) {
		Null.nonNullArg(s, "s");
		return (size == s.length()) ? null : String.format("String <'%s'> must be <%d> characters long.", s, size);
	}

	/** Predicate: String <'%s'> must NOT be <%d> characters long. Available in {@link P}, {@link Has}, {@link Have}, {@link Is}, {@link Be}.*/
	public static boolean notOfLength(@Nonnull CharSequence s, int size) {
		Null.nonNullArg(s, "s");
		return !(size == s.length());
	}

	/** "Special" predicate: String <'%s'> must NOT be <%d> characters long. Available in {@link P}, {@link Has}, {@link Have}, {@link Is}, {@link Be}.*/
	public static String notOfLengthEx(@Nonnull CharSequence s, int size) {
		Null.nonNullArg(s, "s");
		return !(size == s.length()) ? null : String.format("String <'%s'> must NOT be <%d> characters long.", s, size);
	}

	/** Predicate: String <'%s'> must be empty. Available in {@link P}, {@link Is}, {@link Be}, {@link P1}, {@link P2}.*/
	public static boolean empty(@Nonnull String n) {
		Null.nonNullArg(n, "n");
		return n.isEmpty();
	}

	/** "Special" predicate: String <'%s'> must be empty. Available in {@link P}, {@link Is}, {@link Be}, {@link P1}, {@link P2}.*/
	public static String emptyEx(@Nonnull String n) {
		Null.nonNullArg(n, "n");
		return (n.isEmpty()) ? null : String.format("String <'%s'> must be empty.", n);
	}

	/** Predicate: String <'%s'> must NOT be empty. Available in {@link P}, {@link Is}, {@link Be}, {@link P1}, {@link P2}.*/
	public static boolean notEmpty(@Nonnull String n) {
		Null.nonNullArg(n, "n");
		return !(n.isEmpty());
	}

	/** "Special" predicate: String <'%s'> must NOT be empty. Available in {@link P}, {@link Is}, {@link Be}, {@link P1}, {@link P2}.*/
	public static String notEmptyEx(@Nonnull String n) {
		Null.nonNullArg(n, "n");
		return !(n.isEmpty()) ? null : String.format("String <'%s'> must NOT be empty.", n);
	}

	/** Predicate: String <'%s'> must be blank (empty or consisting of only white characters). Available in {@link P}, {@link Is}, {@link Be}, {@link P1}, {@link P2}.*/
	public static boolean blank(@Nonnull String n) {
		Null.nonNullArg(n, "n");
		return n.isBlank();
	}

	/** "Special" predicate: String <'%s'> must be blank (empty or consisting of only white characters). Available in {@link P}, {@link Is}, {@link Be}, {@link P1}, {@link P2}.*/
	public static String blankEx(@Nonnull String n) {
		Null.nonNullArg(n, "n");
		return (n.isBlank()) ? null : String.format("String <'%s'> must be blank (empty or consisting of only white characters).", n);
	}

	/** Predicate: String <'%s'> must NOT be blank (empty or consisting of only white characters). Available in {@link P}, {@link Is}, {@link Be}, {@link P1}, {@link P2}.*/
	public static boolean notBlank(@Nonnull String n) {
		Null.nonNullArg(n, "n");
		return !(n.isBlank());
	}

	/** "Special" predicate: String <'%s'> must NOT be blank (empty or consisting of only white characters). Available in {@link P}, {@link Is}, {@link Be}, {@link P1}, {@link P2}.*/
	public static String notBlankEx(@Nonnull String n) {
		Null.nonNullArg(n, "n");
		return !(n.isBlank()) ? null : String.format("String <'%s'> must NOT be blank (empty or consisting of only white characters).", n);
	}

	/** Predicate: String <'%s'> must be null or empty. Available in {@link P}, {@link Is}, {@link Be}, {@link P1}, {@link P2}.*/
	public static boolean nullOrEmpty(@Nonnull String n) {
		return n == null || n.isEmpty();
	}

	/** "Special" predicate: String <'%s'> must be null or empty. Available in {@link P}, {@link Is}, {@link Be}, {@link P1}, {@link P2}.*/
	public static String nullOrEmptyEx(@Nonnull String n) {
		return (n == null || n.isEmpty()) ? null : String.format("String <'%s'> must be null or empty.", n);
	}

	/** Predicate: String <'%s'> must NOT be null or empty. Available in {@link P}, {@link Is}, {@link Be}, {@link P1}, {@link P2}.*/
	public static boolean notNullNorEmpty(@Nonnull String n) {
		return !(n == null || n.isEmpty());
	}

	/** "Special" predicate: String <'%s'> must NOT be null or empty. Available in {@link P}, {@link Is}, {@link Be}, {@link P1}, {@link P2}.*/
	public static String notNullNorEmptyEx(@Nonnull String n) {
		return !(n == null || n.isEmpty()) ? null : String.format("String <'%s'> must NOT be null or empty.", n);
	}

	/** Predicate: String <'%s'> must be null or blank. Available in {@link P}, {@link Is}, {@link Be}, {@link P1}, {@link P2}.*/
	public static boolean nullOrBlank(@Nonnull String n) {
		return n == null || n.isBlank();
	}

	/** "Special" predicate: String <'%s'> must be null or blank. Available in {@link P}, {@link Is}, {@link Be}, {@link P1}, {@link P2}.*/
	public static String nullOrBlankEx(@Nonnull String n) {
		return (n == null || n.isBlank()) ? null : String.format("String <'%s'> must be null or blank.", n);
	}

	/** Predicate: String <'%s'> must NOT be null or blank. Available in {@link P}, {@link Is}, {@link Be}, {@link P1}, {@link P2}.*/
	public static boolean notNullNorBlank(@Nonnull String n) {
		return !(n == null || n.isBlank());
	}

	/** "Special" predicate: String <'%s'> must NOT be null or blank. Available in {@link P}, {@link Is}, {@link Be}, {@link P1}, {@link P2}.*/
	public static String notNullNorBlankEx(@Nonnull String n) {
		return !(n == null || n.isBlank()) ? null : String.format("String <'%s'> must NOT be null or blank.", n);
	}

	// </editor-fold>

	// <editor-fold desc="and/or/xor">

	// </editor-fold>

	// <editor-fold desc="== <= >= < >">

	/** Predicate: <%s> must be equal to <%s>. Available in {@link P}, {@link Is}, {@link Be}, {@link Does}.*/
	public static boolean equal(Object o1, Object o2) {
		return Objects.equals(o1, o2);
	}

	/** "Special" predicate: <%s> must be equal to <%s>. Available in {@link P}, {@link Is}, {@link Be}, {@link Does}.*/
	public static String equalEx(Object o1, Object o2) {
		return (Objects.equals(o1, o2)) ? null : String.format("<%s> must be equal to <%s>.", o1, o2);
	}

	/** Predicate: <%s> must NOT be equal to <%s>. Available in {@link P}, {@link Is}, {@link Be}, {@link Does}.*/
	public static boolean notEqual(Object o1, Object o2) {
		return !(Objects.equals(o1, o2));
	}

	/** "Special" predicate: <%s> must NOT be equal to <%s>. Available in {@link P}, {@link Is}, {@link Be}, {@link Does}.*/
	public static String notEqualEx(Object o1, Object o2) {
		return !(Objects.equals(o1, o2)) ? null : String.format("<%s> must NOT be equal to <%s>.", o1, o2);
	}

	/** Predicate: %s must be equal to %s. Available in {@link P}, {@link Is}, {@link Be}, {@link Does}.*/
	public static boolean equal(byte n, byte a1) {
		return n == a1;
	}

	/** "Special" predicate: %s must be equal to %s. Available in {@link P}, {@link Is}, {@link Be}, {@link Does}.*/
	public static String equalEx(byte n, byte a1) {
		return (n == a1) ? null : String.format("%s must be equal to %s.", n, a1);
	}

	/** Predicate: %s must NOT be equal to %s. Available in {@link P}, {@link Is}, {@link Be}, {@link Does}.*/
	public static boolean notEqual(byte n, byte a1) {
		return n != a1;
	}

	/** "Special" predicate: %s must NOT be equal to %s. Available in {@link P}, {@link Is}, {@link Be}, {@link Does}.*/
	public static String notEqualEx(byte n, byte a1) {
		return (n != a1) ? null : String.format("%s must NOT be equal to %s.", n, a1);
	}

	/** Predicate: %s must be > %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean gt(byte n, byte a1) {
		return n > a1;
	}

	/** "Special" predicate: %s must be > %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String gtEx(byte n, byte a1) {
		return (n > a1) ? null : String.format("%s must be > %s.", n, a1);
	}

	/** Predicate: %s must NOT be > %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean notGt(byte n, byte a1) {
		return !(n > a1);
	}

	/** "Special" predicate: %s must NOT be > %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String notGtEx(byte n, byte a1) {
		return !(n > a1) ? null : String.format("%s must NOT be > %s.", n, a1);
	}

	/** Predicate: %s must be < %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean lt(byte n, byte a1) {
		return n < a1;
	}

	/** "Special" predicate: %s must be < %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String ltEx(byte n, byte a1) {
		return (n < a1) ? null : String.format("%s must be < %s.", n, a1);
	}

	/** Predicate: %s must NOT be < %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean notLt(byte n, byte a1) {
		return !(n < a1);
	}

	/** "Special" predicate: %s must NOT be < %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String notLtEx(byte n, byte a1) {
		return !(n < a1) ? null : String.format("%s must NOT be < %s.", n, a1);
	}

	/** Predicate: %s must be >= %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean gtEq(byte n, byte a1) {
		return n >= a1;
	}

	/** "Special" predicate: %s must be >= %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String gtEqEx(byte n, byte a1) {
		return (n >= a1) ? null : String.format("%s must be >= %s.", n, a1);
	}

	/** Predicate: %s must NOT be >= %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean notGtEq(byte n, byte a1) {
		return !(n >= a1);
	}

	/** "Special" predicate: %s must NOT be >= %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String notGtEqEx(byte n, byte a1) {
		return !(n >= a1) ? null : String.format("%s must NOT be >= %s.", n, a1);
	}

	/** Predicate: %s must be <= %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean ltEq(byte n, byte a1) {
		return n <= a1;
	}

	/** "Special" predicate: %s must be <= %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String ltEqEx(byte n, byte a1) {
		return (n <= a1) ? null : String.format("%s must be <= %s.", n, a1);
	}

	/** Predicate: %s must NOT be <= %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean notLtEq(byte n, byte a1) {
		return !(n <= a1);
	}

	/** "Special" predicate: %s must NOT be <= %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String notLtEqEx(byte n, byte a1) {
		return !(n <= a1) ? null : String.format("%s must NOT be <= %s.", n, a1);
	}

	/** Predicate: %s must be equal to %s. Available in {@link P}, {@link Is}, {@link Be}, {@link Does}.*/
	public static boolean equal(short n, short a1) {
		return n == a1;
	}

	/** "Special" predicate: %s must be equal to %s. Available in {@link P}, {@link Is}, {@link Be}, {@link Does}.*/
	public static String equalEx(short n, short a1) {
		return (n == a1) ? null : String.format("%s must be equal to %s.", n, a1);
	}

	/** Predicate: %s must NOT be equal to %s. Available in {@link P}, {@link Is}, {@link Be}, {@link Does}.*/
	public static boolean notEqual(short n, short a1) {
		return n != a1;
	}

	/** "Special" predicate: %s must NOT be equal to %s. Available in {@link P}, {@link Is}, {@link Be}, {@link Does}.*/
	public static String notEqualEx(short n, short a1) {
		return (n != a1) ? null : String.format("%s must NOT be equal to %s.", n, a1);
	}

	/** Predicate: %s must be > %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean gt(short n, short a1) {
		return n > a1;
	}

	/** "Special" predicate: %s must be > %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String gtEx(short n, short a1) {
		return (n > a1) ? null : String.format("%s must be > %s.", n, a1);
	}

	/** Predicate: %s must NOT be > %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean notGt(short n, short a1) {
		return !(n > a1);
	}

	/** "Special" predicate: %s must NOT be > %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String notGtEx(short n, short a1) {
		return !(n > a1) ? null : String.format("%s must NOT be > %s.", n, a1);
	}

	/** Predicate: %s must be < %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean lt(short n, short a1) {
		return n < a1;
	}

	/** "Special" predicate: %s must be < %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String ltEx(short n, short a1) {
		return (n < a1) ? null : String.format("%s must be < %s.", n, a1);
	}

	/** Predicate: %s must NOT be < %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean notLt(short n, short a1) {
		return !(n < a1);
	}

	/** "Special" predicate: %s must NOT be < %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String notLtEx(short n, short a1) {
		return !(n < a1) ? null : String.format("%s must NOT be < %s.", n, a1);
	}

	/** Predicate: %s must be >= %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean gtEq(short n, short a1) {
		return n >= a1;
	}

	/** "Special" predicate: %s must be >= %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String gtEqEx(short n, short a1) {
		return (n >= a1) ? null : String.format("%s must be >= %s.", n, a1);
	}

	/** Predicate: %s must NOT be >= %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean notGtEq(short n, short a1) {
		return !(n >= a1);
	}

	/** "Special" predicate: %s must NOT be >= %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String notGtEqEx(short n, short a1) {
		return !(n >= a1) ? null : String.format("%s must NOT be >= %s.", n, a1);
	}

	/** Predicate: %s must be <= %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean ltEq(short n, short a1) {
		return n <= a1;
	}

	/** "Special" predicate: %s must be <= %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String ltEqEx(short n, short a1) {
		return (n <= a1) ? null : String.format("%s must be <= %s.", n, a1);
	}

	/** Predicate: %s must NOT be <= %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean notLtEq(short n, short a1) {
		return !(n <= a1);
	}

	/** "Special" predicate: %s must NOT be <= %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String notLtEqEx(short n, short a1) {
		return !(n <= a1) ? null : String.format("%s must NOT be <= %s.", n, a1);
	}

	/** Predicate: %s must be equal to %s. Available in {@link P}, {@link Is}, {@link Be}, {@link Does}.*/
	public static boolean equal(int n, int a1) {
		return n == a1;
	}

	/** "Special" predicate: %s must be equal to %s. Available in {@link P}, {@link Is}, {@link Be}, {@link Does}.*/
	public static String equalEx(int n, int a1) {
		return (n == a1) ? null : String.format("%s must be equal to %s.", n, a1);
	}

	/** Predicate: %s must NOT be equal to %s. Available in {@link P}, {@link Is}, {@link Be}, {@link Does}.*/
	public static boolean notEqual(int n, int a1) {
		return n != a1;
	}

	/** "Special" predicate: %s must NOT be equal to %s. Available in {@link P}, {@link Is}, {@link Be}, {@link Does}.*/
	public static String notEqualEx(int n, int a1) {
		return (n != a1) ? null : String.format("%s must NOT be equal to %s.", n, a1);
	}

	/** Predicate: %s must be > %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean gt(int n, int a1) {
		return n > a1;
	}

	/** "Special" predicate: %s must be > %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String gtEx(int n, int a1) {
		return (n > a1) ? null : String.format("%s must be > %s.", n, a1);
	}

	/** Predicate: %s must NOT be > %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean notGt(int n, int a1) {
		return !(n > a1);
	}

	/** "Special" predicate: %s must NOT be > %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String notGtEx(int n, int a1) {
		return !(n > a1) ? null : String.format("%s must NOT be > %s.", n, a1);
	}

	/** Predicate: %s must be < %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean lt(int n, int a1) {
		return n < a1;
	}

	/** "Special" predicate: %s must be < %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String ltEx(int n, int a1) {
		return (n < a1) ? null : String.format("%s must be < %s.", n, a1);
	}

	/** Predicate: %s must NOT be < %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean notLt(int n, int a1) {
		return !(n < a1);
	}

	/** "Special" predicate: %s must NOT be < %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String notLtEx(int n, int a1) {
		return !(n < a1) ? null : String.format("%s must NOT be < %s.", n, a1);
	}

	/** Predicate: %s must be >= %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean gtEq(int n, int a1) {
		return n >= a1;
	}

	/** "Special" predicate: %s must be >= %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String gtEqEx(int n, int a1) {
		return (n >= a1) ? null : String.format("%s must be >= %s.", n, a1);
	}

	/** Predicate: %s must NOT be >= %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean notGtEq(int n, int a1) {
		return !(n >= a1);
	}

	/** "Special" predicate: %s must NOT be >= %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String notGtEqEx(int n, int a1) {
		return !(n >= a1) ? null : String.format("%s must NOT be >= %s.", n, a1);
	}

	/** Predicate: %s must be <= %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean ltEq(int n, int a1) {
		return n <= a1;
	}

	/** "Special" predicate: %s must be <= %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String ltEqEx(int n, int a1) {
		return (n <= a1) ? null : String.format("%s must be <= %s.", n, a1);
	}

	/** Predicate: %s must NOT be <= %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean notLtEq(int n, int a1) {
		return !(n <= a1);
	}

	/** "Special" predicate: %s must NOT be <= %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String notLtEqEx(int n, int a1) {
		return !(n <= a1) ? null : String.format("%s must NOT be <= %s.", n, a1);
	}

	/** Predicate: %s must be equal to %s. Available in {@link P}, {@link Is}, {@link Be}, {@link Does}.*/
	public static boolean equal(long n, long a1) {
		return n == a1;
	}

	/** "Special" predicate: %s must be equal to %s. Available in {@link P}, {@link Is}, {@link Be}, {@link Does}.*/
	public static String equalEx(long n, long a1) {
		return (n == a1) ? null : String.format("%s must be equal to %s.", n, a1);
	}

	/** Predicate: %s must NOT be equal to %s. Available in {@link P}, {@link Is}, {@link Be}, {@link Does}.*/
	public static boolean notEqual(long n, long a1) {
		return n != a1;
	}

	/** "Special" predicate: %s must NOT be equal to %s. Available in {@link P}, {@link Is}, {@link Be}, {@link Does}.*/
	public static String notEqualEx(long n, long a1) {
		return (n != a1) ? null : String.format("%s must NOT be equal to %s.", n, a1);
	}

	/** Predicate: %s must be > %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean gt(long n, long a1) {
		return n > a1;
	}

	/** "Special" predicate: %s must be > %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String gtEx(long n, long a1) {
		return (n > a1) ? null : String.format("%s must be > %s.", n, a1);
	}

	/** Predicate: %s must NOT be > %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean notGt(long n, long a1) {
		return !(n > a1);
	}

	/** "Special" predicate: %s must NOT be > %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String notGtEx(long n, long a1) {
		return !(n > a1) ? null : String.format("%s must NOT be > %s.", n, a1);
	}

	/** Predicate: %s must be < %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean lt(long n, long a1) {
		return n < a1;
	}

	/** "Special" predicate: %s must be < %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String ltEx(long n, long a1) {
		return (n < a1) ? null : String.format("%s must be < %s.", n, a1);
	}

	/** Predicate: %s must NOT be < %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean notLt(long n, long a1) {
		return !(n < a1);
	}

	/** "Special" predicate: %s must NOT be < %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String notLtEx(long n, long a1) {
		return !(n < a1) ? null : String.format("%s must NOT be < %s.", n, a1);
	}

	/** Predicate: %s must be >= %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean gtEq(long n, long a1) {
		return n >= a1;
	}

	/** "Special" predicate: %s must be >= %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String gtEqEx(long n, long a1) {
		return (n >= a1) ? null : String.format("%s must be >= %s.", n, a1);
	}

	/** Predicate: %s must NOT be >= %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean notGtEq(long n, long a1) {
		return !(n >= a1);
	}

	/** "Special" predicate: %s must NOT be >= %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String notGtEqEx(long n, long a1) {
		return !(n >= a1) ? null : String.format("%s must NOT be >= %s.", n, a1);
	}

	/** Predicate: %s must be <= %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean ltEq(long n, long a1) {
		return n <= a1;
	}

	/** "Special" predicate: %s must be <= %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String ltEqEx(long n, long a1) {
		return (n <= a1) ? null : String.format("%s must be <= %s.", n, a1);
	}

	/** Predicate: %s must NOT be <= %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean notLtEq(long n, long a1) {
		return !(n <= a1);
	}

	/** "Special" predicate: %s must NOT be <= %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String notLtEqEx(long n, long a1) {
		return !(n <= a1) ? null : String.format("%s must NOT be <= %s.", n, a1);
	}

	/** Predicate: %s must be equal to %s. Available in {@link P}, {@link Is}, {@link Be}, {@link Does}.*/
	public static boolean equal(float n, float a1) {
		return n == a1;
	}

	/** "Special" predicate: %s must be equal to %s. Available in {@link P}, {@link Is}, {@link Be}, {@link Does}.*/
	public static String equalEx(float n, float a1) {
		return (n == a1) ? null : String.format("%s must be equal to %s.", n, a1);
	}

	/** Predicate: %s must NOT be equal to %s. Available in {@link P}, {@link Is}, {@link Be}, {@link Does}.*/
	public static boolean notEqual(float n, float a1) {
		return n != a1;
	}

	/** "Special" predicate: %s must NOT be equal to %s. Available in {@link P}, {@link Is}, {@link Be}, {@link Does}.*/
	public static String notEqualEx(float n, float a1) {
		return (n != a1) ? null : String.format("%s must NOT be equal to %s.", n, a1);
	}

	/** Predicate: %s must be > %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean gt(float n, float a1) {
		return n > a1;
	}

	/** "Special" predicate: %s must be > %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String gtEx(float n, float a1) {
		return (n > a1) ? null : String.format("%s must be > %s.", n, a1);
	}

	/** Predicate: %s must NOT be > %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean notGt(float n, float a1) {
		return !(n > a1);
	}

	/** "Special" predicate: %s must NOT be > %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String notGtEx(float n, float a1) {
		return !(n > a1) ? null : String.format("%s must NOT be > %s.", n, a1);
	}

	/** Predicate: %s must be < %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean lt(float n, float a1) {
		return n < a1;
	}

	/** "Special" predicate: %s must be < %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String ltEx(float n, float a1) {
		return (n < a1) ? null : String.format("%s must be < %s.", n, a1);
	}

	/** Predicate: %s must NOT be < %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean notLt(float n, float a1) {
		return !(n < a1);
	}

	/** "Special" predicate: %s must NOT be < %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String notLtEx(float n, float a1) {
		return !(n < a1) ? null : String.format("%s must NOT be < %s.", n, a1);
	}

	/** Predicate: %s must be >= %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean gtEq(float n, float a1) {
		return n >= a1;
	}

	/** "Special" predicate: %s must be >= %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String gtEqEx(float n, float a1) {
		return (n >= a1) ? null : String.format("%s must be >= %s.", n, a1);
	}

	/** Predicate: %s must NOT be >= %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean notGtEq(float n, float a1) {
		return !(n >= a1);
	}

	/** "Special" predicate: %s must NOT be >= %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String notGtEqEx(float n, float a1) {
		return !(n >= a1) ? null : String.format("%s must NOT be >= %s.", n, a1);
	}

	/** Predicate: %s must be <= %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean ltEq(float n, float a1) {
		return n <= a1;
	}

	/** "Special" predicate: %s must be <= %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String ltEqEx(float n, float a1) {
		return (n <= a1) ? null : String.format("%s must be <= %s.", n, a1);
	}

	/** Predicate: %s must NOT be <= %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean notLtEq(float n, float a1) {
		return !(n <= a1);
	}

	/** "Special" predicate: %s must NOT be <= %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String notLtEqEx(float n, float a1) {
		return !(n <= a1) ? null : String.format("%s must NOT be <= %s.", n, a1);
	}

	/** Predicate: %s must be equal to %s. Available in {@link P}, {@link Is}, {@link Be}, {@link Does}.*/
	public static boolean equal(double n, double a1) {
		return n == a1;
	}

	/** "Special" predicate: %s must be equal to %s. Available in {@link P}, {@link Is}, {@link Be}, {@link Does}.*/
	public static String equalEx(double n, double a1) {
		return (n == a1) ? null : String.format("%s must be equal to %s.", n, a1);
	}

	/** Predicate: %s must NOT be equal to %s. Available in {@link P}, {@link Is}, {@link Be}, {@link Does}.*/
	public static boolean notEqual(double n, double a1) {
		return n != a1;
	}

	/** "Special" predicate: %s must NOT be equal to %s. Available in {@link P}, {@link Is}, {@link Be}, {@link Does}.*/
	public static String notEqualEx(double n, double a1) {
		return (n != a1) ? null : String.format("%s must NOT be equal to %s.", n, a1);
	}

	/** Predicate: %s must be > %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean gt(double n, double a1) {
		return n > a1;
	}

	/** "Special" predicate: %s must be > %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String gtEx(double n, double a1) {
		return (n > a1) ? null : String.format("%s must be > %s.", n, a1);
	}

	/** Predicate: %s must NOT be > %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean notGt(double n, double a1) {
		return !(n > a1);
	}

	/** "Special" predicate: %s must NOT be > %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String notGtEx(double n, double a1) {
		return !(n > a1) ? null : String.format("%s must NOT be > %s.", n, a1);
	}

	/** Predicate: %s must be < %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean lt(double n, double a1) {
		return n < a1;
	}

	/** "Special" predicate: %s must be < %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String ltEx(double n, double a1) {
		return (n < a1) ? null : String.format("%s must be < %s.", n, a1);
	}

	/** Predicate: %s must NOT be < %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean notLt(double n, double a1) {
		return !(n < a1);
	}

	/** "Special" predicate: %s must NOT be < %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String notLtEx(double n, double a1) {
		return !(n < a1) ? null : String.format("%s must NOT be < %s.", n, a1);
	}

	/** Predicate: %s must be >= %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean gtEq(double n, double a1) {
		return n >= a1;
	}

	/** "Special" predicate: %s must be >= %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String gtEqEx(double n, double a1) {
		return (n >= a1) ? null : String.format("%s must be >= %s.", n, a1);
	}

	/** Predicate: %s must NOT be >= %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean notGtEq(double n, double a1) {
		return !(n >= a1);
	}

	/** "Special" predicate: %s must NOT be >= %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String notGtEqEx(double n, double a1) {
		return !(n >= a1) ? null : String.format("%s must NOT be >= %s.", n, a1);
	}

	/** Predicate: %s must be <= %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean ltEq(double n, double a1) {
		return n <= a1;
	}

	/** "Special" predicate: %s must be <= %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String ltEqEx(double n, double a1) {
		return (n <= a1) ? null : String.format("%s must be <= %s.", n, a1);
	}

	/** Predicate: %s must NOT be <= %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean notLtEq(double n, double a1) {
		return !(n <= a1);
	}

	/** "Special" predicate: %s must NOT be <= %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String notLtEqEx(double n, double a1) {
		return !(n <= a1) ? null : String.format("%s must NOT be <= %s.", n, a1);
	}

	/** Predicate: %s must be equal to %s. Available in {@link P}, {@link Is}, {@link Be}, {@link Does}.*/
	public static boolean equal(char n, char a1) {
		return n == a1;
	}

	/** "Special" predicate: %s must be equal to %s. Available in {@link P}, {@link Is}, {@link Be}, {@link Does}.*/
	public static String equalEx(char n, char a1) {
		return (n == a1) ? null : String.format("%s must be equal to %s.", n, a1);
	}

	/** Predicate: %s must NOT be equal to %s. Available in {@link P}, {@link Is}, {@link Be}, {@link Does}.*/
	public static boolean notEqual(char n, char a1) {
		return n != a1;
	}

	/** "Special" predicate: %s must NOT be equal to %s. Available in {@link P}, {@link Is}, {@link Be}, {@link Does}.*/
	public static String notEqualEx(char n, char a1) {
		return (n != a1) ? null : String.format("%s must NOT be equal to %s.", n, a1);
	}

	/** Predicate: %s must be > %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean gt(char n, char a1) {
		return n > a1;
	}

	/** "Special" predicate: %s must be > %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String gtEx(char n, char a1) {
		return (n > a1) ? null : String.format("%s must be > %s.", n, a1);
	}

	/** Predicate: %s must NOT be > %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean notGt(char n, char a1) {
		return !(n > a1);
	}

	/** "Special" predicate: %s must NOT be > %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String notGtEx(char n, char a1) {
		return !(n > a1) ? null : String.format("%s must NOT be > %s.", n, a1);
	}

	/** Predicate: %s must be < %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean lt(char n, char a1) {
		return n < a1;
	}

	/** "Special" predicate: %s must be < %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String ltEx(char n, char a1) {
		return (n < a1) ? null : String.format("%s must be < %s.", n, a1);
	}

	/** Predicate: %s must NOT be < %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean notLt(char n, char a1) {
		return !(n < a1);
	}

	/** "Special" predicate: %s must NOT be < %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String notLtEx(char n, char a1) {
		return !(n < a1) ? null : String.format("%s must NOT be < %s.", n, a1);
	}

	/** Predicate: %s must be >= %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean gtEq(char n, char a1) {
		return n >= a1;
	}

	/** "Special" predicate: %s must be >= %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String gtEqEx(char n, char a1) {
		return (n >= a1) ? null : String.format("%s must be >= %s.", n, a1);
	}

	/** Predicate: %s must NOT be >= %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean notGtEq(char n, char a1) {
		return !(n >= a1);
	}

	/** "Special" predicate: %s must NOT be >= %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String notGtEqEx(char n, char a1) {
		return !(n >= a1) ? null : String.format("%s must NOT be >= %s.", n, a1);
	}

	/** Predicate: %s must be <= %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean ltEq(char n, char a1) {
		return n <= a1;
	}

	/** "Special" predicate: %s must be <= %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String ltEqEx(char n, char a1) {
		return (n <= a1) ? null : String.format("%s must be <= %s.", n, a1);
	}

	/** Predicate: %s must NOT be <= %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean notLtEq(char n, char a1) {
		return !(n <= a1);
	}

	/** "Special" predicate: %s must NOT be <= %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String notLtEqEx(char n, char a1) {
		return !(n <= a1) ? null : String.format("%s must NOT be <= %s.", n, a1);
	}

	/** Predicate: %0$s must be: %1$s < %0$s < %2$s Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean between(byte n, byte a1, byte a2) {
		return a1 < n && n < a2;
	}

	/** "Special" predicate: %0$s must be: %1$s < %0$s < %2$s Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String betweenEx(byte n, byte a1, byte a2) {
		return (a1 < n && n < a2) ? null : String.format("%0$s must be: %1$s < %0$s < %2$s", n, a1, a2);
	}

	/** Predicate: %0$s must NOT be: %1$s < %0$s < %2$s Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean notBetween(byte n, byte a1, byte a2) {
		return !(a1 < n && n < a2);
	}

	/** "Special" predicate: %0$s must NOT be: %1$s < %0$s < %2$s Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String notBetweenEx(byte n, byte a1, byte a2) {
		return !(a1 < n && n < a2) ? null : String.format("%0$s must NOT be: %1$s < %0$s < %2$s", n, a1, a2);
	}

	/** Predicate: %0$s must be: %1$s <= %0$s <= %2$s Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean inRange(byte n, byte a1, byte a2) {
		return a1 <= n && n <= a2;
	}

	/** "Special" predicate: %0$s must be: %1$s <= %0$s <= %2$s Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String inRangeEx(byte n, byte a1, byte a2) {
		return (a1 <= n && n <= a2) ? null : String.format("%0$s must be: %1$s <= %0$s <= %2$s", n, a1, a2);
	}

	/** Predicate: %0$s must NOT be: %1$s <= %0$s <= %2$s Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean notInRange(byte n, byte a1, byte a2) {
		return !(a1 <= n && n <= a2);
	}

	/** "Special" predicate: %0$s must NOT be: %1$s <= %0$s <= %2$s Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String notInRangeEx(byte n, byte a1, byte a2) {
		return !(a1 <= n && n <= a2) ? null : String.format("%0$s must NOT be: %1$s <= %0$s <= %2$s", n, a1, a2);
	}

	/** Predicate: %0$s must be: %1$s < %0$s < %2$s Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean between(short n, short a1, short a2) {
		return a1 < n && n < a2;
	}

	/** "Special" predicate: %0$s must be: %1$s < %0$s < %2$s Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String betweenEx(short n, short a1, short a2) {
		return (a1 < n && n < a2) ? null : String.format("%0$s must be: %1$s < %0$s < %2$s", n, a1, a2);
	}

	/** Predicate: %0$s must NOT be: %1$s < %0$s < %2$s Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean notBetween(short n, short a1, short a2) {
		return !(a1 < n && n < a2);
	}

	/** "Special" predicate: %0$s must NOT be: %1$s < %0$s < %2$s Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String notBetweenEx(short n, short a1, short a2) {
		return !(a1 < n && n < a2) ? null : String.format("%0$s must NOT be: %1$s < %0$s < %2$s", n, a1, a2);
	}

	/** Predicate: %0$s must be: %1$s <= %0$s <= %2$s Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean inRange(short n, short a1, short a2) {
		return a1 <= n && n <= a2;
	}

	/** "Special" predicate: %0$s must be: %1$s <= %0$s <= %2$s Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String inRangeEx(short n, short a1, short a2) {
		return (a1 <= n && n <= a2) ? null : String.format("%0$s must be: %1$s <= %0$s <= %2$s", n, a1, a2);
	}

	/** Predicate: %0$s must NOT be: %1$s <= %0$s <= %2$s Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean notInRange(short n, short a1, short a2) {
		return !(a1 <= n && n <= a2);
	}

	/** "Special" predicate: %0$s must NOT be: %1$s <= %0$s <= %2$s Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String notInRangeEx(short n, short a1, short a2) {
		return !(a1 <= n && n <= a2) ? null : String.format("%0$s must NOT be: %1$s <= %0$s <= %2$s", n, a1, a2);
	}

	/** Predicate: %0$s must be: %1$s < %0$s < %2$s Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean between(int n, int a1, int a2) {
		return a1 < n && n < a2;
	}

	/** "Special" predicate: %0$s must be: %1$s < %0$s < %2$s Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String betweenEx(int n, int a1, int a2) {
		return (a1 < n && n < a2) ? null : String.format("%0$s must be: %1$s < %0$s < %2$s", n, a1, a2);
	}

	/** Predicate: %0$s must NOT be: %1$s < %0$s < %2$s Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean notBetween(int n, int a1, int a2) {
		return !(a1 < n && n < a2);
	}

	/** "Special" predicate: %0$s must NOT be: %1$s < %0$s < %2$s Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String notBetweenEx(int n, int a1, int a2) {
		return !(a1 < n && n < a2) ? null : String.format("%0$s must NOT be: %1$s < %0$s < %2$s", n, a1, a2);
	}

	/** Predicate: %0$s must be: %1$s <= %0$s <= %2$s Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean inRange(int n, int a1, int a2) {
		return a1 <= n && n <= a2;
	}

	/** "Special" predicate: %0$s must be: %1$s <= %0$s <= %2$s Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String inRangeEx(int n, int a1, int a2) {
		return (a1 <= n && n <= a2) ? null : String.format("%0$s must be: %1$s <= %0$s <= %2$s", n, a1, a2);
	}

	/** Predicate: %0$s must NOT be: %1$s <= %0$s <= %2$s Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean notInRange(int n, int a1, int a2) {
		return !(a1 <= n && n <= a2);
	}

	/** "Special" predicate: %0$s must NOT be: %1$s <= %0$s <= %2$s Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String notInRangeEx(int n, int a1, int a2) {
		return !(a1 <= n && n <= a2) ? null : String.format("%0$s must NOT be: %1$s <= %0$s <= %2$s", n, a1, a2);
	}

	/** Predicate: %0$s must be: %1$s < %0$s < %2$s Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean between(long n, long a1, long a2) {
		return a1 < n && n < a2;
	}

	/** "Special" predicate: %0$s must be: %1$s < %0$s < %2$s Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String betweenEx(long n, long a1, long a2) {
		return (a1 < n && n < a2) ? null : String.format("%0$s must be: %1$s < %0$s < %2$s", n, a1, a2);
	}

	/** Predicate: %0$s must NOT be: %1$s < %0$s < %2$s Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean notBetween(long n, long a1, long a2) {
		return !(a1 < n && n < a2);
	}

	/** "Special" predicate: %0$s must NOT be: %1$s < %0$s < %2$s Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String notBetweenEx(long n, long a1, long a2) {
		return !(a1 < n && n < a2) ? null : String.format("%0$s must NOT be: %1$s < %0$s < %2$s", n, a1, a2);
	}

	/** Predicate: %0$s must be: %1$s <= %0$s <= %2$s Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean inRange(long n, long a1, long a2) {
		return a1 <= n && n <= a2;
	}

	/** "Special" predicate: %0$s must be: %1$s <= %0$s <= %2$s Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String inRangeEx(long n, long a1, long a2) {
		return (a1 <= n && n <= a2) ? null : String.format("%0$s must be: %1$s <= %0$s <= %2$s", n, a1, a2);
	}

	/** Predicate: %0$s must NOT be: %1$s <= %0$s <= %2$s Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean notInRange(long n, long a1, long a2) {
		return !(a1 <= n && n <= a2);
	}

	/** "Special" predicate: %0$s must NOT be: %1$s <= %0$s <= %2$s Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String notInRangeEx(long n, long a1, long a2) {
		return !(a1 <= n && n <= a2) ? null : String.format("%0$s must NOT be: %1$s <= %0$s <= %2$s", n, a1, a2);
	}

	/** Predicate: %0$s must be: %1$s < %0$s < %2$s Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean between(float n, float a1, float a2) {
		return a1 < n && n < a2;
	}

	/** "Special" predicate: %0$s must be: %1$s < %0$s < %2$s Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String betweenEx(float n, float a1, float a2) {
		return (a1 < n && n < a2) ? null : String.format("%0$s must be: %1$s < %0$s < %2$s", n, a1, a2);
	}

	/** Predicate: %0$s must NOT be: %1$s < %0$s < %2$s Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean notBetween(float n, float a1, float a2) {
		return !(a1 < n && n < a2);
	}

	/** "Special" predicate: %0$s must NOT be: %1$s < %0$s < %2$s Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String notBetweenEx(float n, float a1, float a2) {
		return !(a1 < n && n < a2) ? null : String.format("%0$s must NOT be: %1$s < %0$s < %2$s", n, a1, a2);
	}

	/** Predicate: %0$s must be: %1$s <= %0$s <= %2$s Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean inRange(float n, float a1, float a2) {
		return a1 <= n && n <= a2;
	}

	/** "Special" predicate: %0$s must be: %1$s <= %0$s <= %2$s Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String inRangeEx(float n, float a1, float a2) {
		return (a1 <= n && n <= a2) ? null : String.format("%0$s must be: %1$s <= %0$s <= %2$s", n, a1, a2);
	}

	/** Predicate: %0$s must NOT be: %1$s <= %0$s <= %2$s Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean notInRange(float n, float a1, float a2) {
		return !(a1 <= n && n <= a2);
	}

	/** "Special" predicate: %0$s must NOT be: %1$s <= %0$s <= %2$s Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String notInRangeEx(float n, float a1, float a2) {
		return !(a1 <= n && n <= a2) ? null : String.format("%0$s must NOT be: %1$s <= %0$s <= %2$s", n, a1, a2);
	}

	/** Predicate: %0$s must be: %1$s < %0$s < %2$s Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean between(double n, double a1, double a2) {
		return a1 < n && n < a2;
	}

	/** "Special" predicate: %0$s must be: %1$s < %0$s < %2$s Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String betweenEx(double n, double a1, double a2) {
		return (a1 < n && n < a2) ? null : String.format("%0$s must be: %1$s < %0$s < %2$s", n, a1, a2);
	}

	/** Predicate: %0$s must NOT be: %1$s < %0$s < %2$s Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean notBetween(double n, double a1, double a2) {
		return !(a1 < n && n < a2);
	}

	/** "Special" predicate: %0$s must NOT be: %1$s < %0$s < %2$s Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String notBetweenEx(double n, double a1, double a2) {
		return !(a1 < n && n < a2) ? null : String.format("%0$s must NOT be: %1$s < %0$s < %2$s", n, a1, a2);
	}

	/** Predicate: %0$s must be: %1$s <= %0$s <= %2$s Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean inRange(double n, double a1, double a2) {
		return a1 <= n && n <= a2;
	}

	/** "Special" predicate: %0$s must be: %1$s <= %0$s <= %2$s Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String inRangeEx(double n, double a1, double a2) {
		return (a1 <= n && n <= a2) ? null : String.format("%0$s must be: %1$s <= %0$s <= %2$s", n, a1, a2);
	}

	/** Predicate: %0$s must NOT be: %1$s <= %0$s <= %2$s Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean notInRange(double n, double a1, double a2) {
		return !(a1 <= n && n <= a2);
	}

	/** "Special" predicate: %0$s must NOT be: %1$s <= %0$s <= %2$s Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String notInRangeEx(double n, double a1, double a2) {
		return !(a1 <= n && n <= a2) ? null : String.format("%0$s must NOT be: %1$s <= %0$s <= %2$s", n, a1, a2);
	}

	/** Predicate: %0$s must be: %1$s < %0$s < %2$s Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean between(char n, char a1, char a2) {
		return a1 < n && n < a2;
	}

	/** "Special" predicate: %0$s must be: %1$s < %0$s < %2$s Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String betweenEx(char n, char a1, char a2) {
		return (a1 < n && n < a2) ? null : String.format("%0$s must be: %1$s < %0$s < %2$s", n, a1, a2);
	}

	/** Predicate: %0$s must NOT be: %1$s < %0$s < %2$s Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean notBetween(char n, char a1, char a2) {
		return !(a1 < n && n < a2);
	}

	/** "Special" predicate: %0$s must NOT be: %1$s < %0$s < %2$s Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String notBetweenEx(char n, char a1, char a2) {
		return !(a1 < n && n < a2) ? null : String.format("%0$s must NOT be: %1$s < %0$s < %2$s", n, a1, a2);
	}

	/** Predicate: %0$s must be: %1$s <= %0$s <= %2$s Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean inRange(char n, char a1, char a2) {
		return a1 <= n && n <= a2;
	}

	/** "Special" predicate: %0$s must be: %1$s <= %0$s <= %2$s Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String inRangeEx(char n, char a1, char a2) {
		return (a1 <= n && n <= a2) ? null : String.format("%0$s must be: %1$s <= %0$s <= %2$s", n, a1, a2);
	}

	/** Predicate: %0$s must NOT be: %1$s <= %0$s <= %2$s Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean notInRange(char n, char a1, char a2) {
		return !(a1 <= n && n <= a2);
	}

	/** "Special" predicate: %0$s must NOT be: %1$s <= %0$s <= %2$s Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String notInRangeEx(char n, char a1, char a2) {
		return !(a1 <= n && n <= a2) ? null : String.format("%0$s must NOT be: %1$s <= %0$s <= %2$s", n, a1, a2);
	}

	/** Predicate: <%s> must be true. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean True(boolean v) {
		return v;
	}

	/** "Special" predicate: <%s> must be true. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String TrueEx(boolean v) {
		return (v) ? null : String.format("<%s> must be true.", v);
	}

	/** Predicate: <%s> must be false. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean False(boolean v) {
		return !v;
	}

	/** "Special" predicate: <%s> must be false. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String FalseEx(boolean v) {
		return (!v) ? null : String.format("<%s> must be false.", v);
	}

	// </editor-fold>

	// <editor-fold desc="arrays">

	/** Predicate: Array <%s> must be of size %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean ofLength(@Nonnull boolean[] array, int i) {
		Null.nonNullArg(array, "array");
		return Array.getLength(array) == i;
	}

	/** "Special" predicate: Array <%s> must be of size %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String ofLengthEx(@Nonnull boolean[] array, int i) {
		Null.nonNullArg(array, "array");
		return (Array.getLength(array) == i) ? null : String.format("Array <%s> must be of size %s.", array, i);
	}

	/** Predicate: Array <%s> must NOT be of size %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean notOfLength(@Nonnull boolean[] array, int i) {
		Null.nonNullArg(array, "array");
		return !(Array.getLength(array) == i);
	}

	/** "Special" predicate: Array <%s> must NOT be of size %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String notOfLengthEx(@Nonnull boolean[] array, int i) {
		Null.nonNullArg(array, "array");
		return !(Array.getLength(array) == i) ? null : String.format("Array <%s> must NOT be of size %s.", array, i);
	}

	/** Predicate: Array <%s> must be empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean empty(@Nonnull boolean[] array) {
		Null.nonNullArg(array, "array");
		return Array.getLength(array) == 0;
	}

	/** "Special" predicate: Array <%s> must be empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String emptyEx(@Nonnull boolean[] array) {
		Null.nonNullArg(array, "array");
		return (Array.getLength(array) == 0) ? null : String.format("Array <%s> must be empty.", (Object) array);
	}

	/** Predicate: Array <%s> must NOT be empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean notEmpty(@Nonnull boolean[] array) {
		Null.nonNullArg(array, "array");
		return !(Array.getLength(array) == 0);
	}

	/** "Special" predicate: Array <%s> must NOT be empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String notEmptyEx(@Nonnull boolean[] array) {
		Null.nonNullArg(array, "array");
		return !(Array.getLength(array) == 0) ? null : String.format("Array <%s> must NOT be empty.", (Object) array);
	}

	/** Predicate: Array <%s> must be null or empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean nullOrEmpty(@Nullable boolean[] array) {
		return array == null || Array.getLength(array) == 0;
	}

	/** "Special" predicate: Array <%s> must be null or empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String nullOrEmptyEx(@Nullable boolean[] array) {
		return (array == null || Array.getLength(array) == 0) ? null : String.format("Array <%s> must be null or empty.", (Object) array);
	}

	/** Predicate: Array <%s> must NOT be null or empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean notNullNotEmpty(@Nullable boolean[] array) {
		return !(array == null || Array.getLength(array) == 0);
	}

	/** "Special" predicate: Array <%s> must NOT be null or empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String notNullNotEmptyEx(@Nullable boolean[] array) {
		return !(array == null || Array.getLength(array) == 0) ? null : String.format("Array <%s> must NOT be null or empty.", (Object) array);
	}

	/** Predicate: Array <%s> must be exactly of size 1 (singleton). Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean singleton(@Nonnull boolean[] array) {
		Null.nonNullArg(array, "array");
		return Array.getLength(array) == 1;
	}

	/** "Special" predicate: Array <%s> must be exactly of size 1 (singleton). Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String singletonEx(@Nonnull boolean[] array) {
		Null.nonNullArg(array, "array");
		return (Array.getLength(array) == 1) ? null : String.format("Array <%s> must be exactly of size 1 (singleton).", (Object) array);
	}

	/** Predicate: Array <%s> must NOT be exactly of size 1 (singleton). Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean notSingleton(@Nonnull boolean[] array) {
		Null.nonNullArg(array, "array");
		return !(Array.getLength(array) == 1);
	}

	/** "Special" predicate: Array <%s> must NOT be exactly of size 1 (singleton). Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String notSingletonEx(@Nonnull boolean[] array) {
		Null.nonNullArg(array, "array");
		return !(Array.getLength(array) == 1) ? null : String.format("Array <%s> must NOT be exactly of size 1 (singleton).", (Object) array);
	}

	/** Predicate: Array <%s> must be of size %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean ofLength(@Nonnull byte[] array, int i) {
		Null.nonNullArg(array, "array");
		return Array.getLength(array) == i;
	}

	/** "Special" predicate: Array <%s> must be of size %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String ofLengthEx(@Nonnull byte[] array, int i) {
		Null.nonNullArg(array, "array");
		return (Array.getLength(array) == i) ? null : String.format("Array <%s> must be of size %s.", array, i);
	}

	/** Predicate: Array <%s> must NOT be of size %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean notOfLength(@Nonnull byte[] array, int i) {
		Null.nonNullArg(array, "array");
		return !(Array.getLength(array) == i);
	}

	/** "Special" predicate: Array <%s> must NOT be of size %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String notOfLengthEx(@Nonnull byte[] array, int i) {
		Null.nonNullArg(array, "array");
		return !(Array.getLength(array) == i) ? null : String.format("Array <%s> must NOT be of size %s.", array, i);
	}

	/** Predicate: Array <%s> must be empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean empty(@Nonnull byte[] array) {
		Null.nonNullArg(array, "array");
		return Array.getLength(array) == 0;
	}

	/** "Special" predicate: Array <%s> must be empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String emptyEx(@Nonnull byte[] array) {
		Null.nonNullArg(array, "array");
		return (Array.getLength(array) == 0) ? null : String.format("Array <%s> must be empty.", (Object) array);
	}

	/** Predicate: Array <%s> must NOT be empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean notEmpty(@Nonnull byte[] array) {
		Null.nonNullArg(array, "array");
		return !(Array.getLength(array) == 0);
	}

	/** "Special" predicate: Array <%s> must NOT be empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String notEmptyEx(@Nonnull byte[] array) {
		Null.nonNullArg(array, "array");
		return !(Array.getLength(array) == 0) ? null : String.format("Array <%s> must NOT be empty.", (Object) array);
	}

	/** Predicate: Array <%s> must be null or empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean nullOrEmpty(@Nullable byte[] array) {
		return array == null || Array.getLength(array) == 0;
	}

	/** "Special" predicate: Array <%s> must be null or empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String nullOrEmptyEx(@Nullable byte[] array) {
		return (array == null || Array.getLength(array) == 0) ? null : String.format("Array <%s> must be null or empty.", (Object) array);
	}

	/** Predicate: Array <%s> must NOT be null or empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean notNullNotEmpty(@Nullable byte[] array) {
		return !(array == null || Array.getLength(array) == 0);
	}

	/** "Special" predicate: Array <%s> must NOT be null or empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String notNullNotEmptyEx(@Nullable byte[] array) {
		return !(array == null || Array.getLength(array) == 0) ? null : String.format("Array <%s> must NOT be null or empty.", (Object) array);
	}

	/** Predicate: Array <%s> must be exactly of size 1 (singleton). Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean singleton(@Nonnull byte[] array) {
		Null.nonNullArg(array, "array");
		return Array.getLength(array) == 1;
	}

	/** "Special" predicate: Array <%s> must be exactly of size 1 (singleton). Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String singletonEx(@Nonnull byte[] array) {
		Null.nonNullArg(array, "array");
		return (Array.getLength(array) == 1) ? null : String.format("Array <%s> must be exactly of size 1 (singleton).", (Object) array);
	}

	/** Predicate: Array <%s> must NOT be exactly of size 1 (singleton). Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean notSingleton(@Nonnull byte[] array) {
		Null.nonNullArg(array, "array");
		return !(Array.getLength(array) == 1);
	}

	/** "Special" predicate: Array <%s> must NOT be exactly of size 1 (singleton). Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String notSingletonEx(@Nonnull byte[] array) {
		Null.nonNullArg(array, "array");
		return !(Array.getLength(array) == 1) ? null : String.format("Array <%s> must NOT be exactly of size 1 (singleton).", (Object) array);
	}

	/** Predicate: Array <%s> must be of size %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean ofLength(@Nonnull double[] array, int i) {
		Null.nonNullArg(array, "array");
		return Array.getLength(array) == i;
	}

	/** "Special" predicate: Array <%s> must be of size %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String ofLengthEx(@Nonnull double[] array, int i) {
		Null.nonNullArg(array, "array");
		return (Array.getLength(array) == i) ? null : String.format("Array <%s> must be of size %s.", array, i);
	}

	/** Predicate: Array <%s> must NOT be of size %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean notOfLength(@Nonnull double[] array, int i) {
		Null.nonNullArg(array, "array");
		return !(Array.getLength(array) == i);
	}

	/** "Special" predicate: Array <%s> must NOT be of size %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String notOfLengthEx(@Nonnull double[] array, int i) {
		Null.nonNullArg(array, "array");
		return !(Array.getLength(array) == i) ? null : String.format("Array <%s> must NOT be of size %s.", array, i);
	}

	/** Predicate: Array <%s> must be empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean empty(@Nonnull double[] array) {
		Null.nonNullArg(array, "array");
		return Array.getLength(array) == 0;
	}

	/** "Special" predicate: Array <%s> must be empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String emptyEx(@Nonnull double[] array) {
		Null.nonNullArg(array, "array");
		return (Array.getLength(array) == 0) ? null : String.format("Array <%s> must be empty.", (Object) array);
	}

	/** Predicate: Array <%s> must NOT be empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean notEmpty(@Nonnull double[] array) {
		Null.nonNullArg(array, "array");
		return !(Array.getLength(array) == 0);
	}

	/** "Special" predicate: Array <%s> must NOT be empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String notEmptyEx(@Nonnull double[] array) {
		Null.nonNullArg(array, "array");
		return !(Array.getLength(array) == 0) ? null : String.format("Array <%s> must NOT be empty.", (Object) array);
	}

	/** Predicate: Array <%s> must be null or empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean nullOrEmpty(@Nullable double[] array) {
		return array == null || Array.getLength(array) == 0;
	}

	/** "Special" predicate: Array <%s> must be null or empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String nullOrEmptyEx(@Nullable double[] array) {
		return (array == null || Array.getLength(array) == 0) ? null : String.format("Array <%s> must be null or empty.", (Object) array);
	}

	/** Predicate: Array <%s> must NOT be null or empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean notNullNotEmpty(@Nullable double[] array) {
		return !(array == null || Array.getLength(array) == 0);
	}

	/** "Special" predicate: Array <%s> must NOT be null or empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String notNullNotEmptyEx(@Nullable double[] array) {
		return !(array == null || Array.getLength(array) == 0) ? null : String.format("Array <%s> must NOT be null or empty.", (Object) array);
	}

	/** Predicate: Array <%s> must be exactly of size 1 (singleton). Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean singleton(@Nonnull double[] array) {
		Null.nonNullArg(array, "array");
		return Array.getLength(array) == 1;
	}

	/** "Special" predicate: Array <%s> must be exactly of size 1 (singleton). Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String singletonEx(@Nonnull double[] array) {
		Null.nonNullArg(array, "array");
		return (Array.getLength(array) == 1) ? null : String.format("Array <%s> must be exactly of size 1 (singleton).", (Object) array);
	}

	/** Predicate: Array <%s> must NOT be exactly of size 1 (singleton). Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean notSingleton(@Nonnull double[] array) {
		Null.nonNullArg(array, "array");
		return !(Array.getLength(array) == 1);
	}

	/** "Special" predicate: Array <%s> must NOT be exactly of size 1 (singleton). Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String notSingletonEx(@Nonnull double[] array) {
		Null.nonNullArg(array, "array");
		return !(Array.getLength(array) == 1) ? null : String.format("Array <%s> must NOT be exactly of size 1 (singleton).", (Object) array);
	}

	/** Predicate: Array <%s> must be of size %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean ofLength(@Nonnull char[] array, int i) {
		Null.nonNullArg(array, "array");
		return Array.getLength(array) == i;
	}

	/** "Special" predicate: Array <%s> must be of size %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String ofLengthEx(@Nonnull char[] array, int i) {
		Null.nonNullArg(array, "array");
		return (Array.getLength(array) == i) ? null : String.format("Array <%s> must be of size %s.", array, i);
	}

	/** Predicate: Array <%s> must NOT be of size %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean notOfLength(@Nonnull char[] array, int i) {
		Null.nonNullArg(array, "array");
		return !(Array.getLength(array) == i);
	}

	/** "Special" predicate: Array <%s> must NOT be of size %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String notOfLengthEx(@Nonnull char[] array, int i) {
		Null.nonNullArg(array, "array");
		return !(Array.getLength(array) == i) ? null : String.format("Array <%s> must NOT be of size %s.", array, i);
	}

	/** Predicate: Array <%s> must be empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean empty(@Nonnull char[] array) {
		Null.nonNullArg(array, "array");
		return Array.getLength(array) == 0;
	}

	/** "Special" predicate: Array <%s> must be empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String emptyEx(@Nonnull char[] array) {
		Null.nonNullArg(array, "array");
		return (Array.getLength(array) == 0) ? null : String.format("Array <%s> must be empty.", (Object) array);
	}

	/** Predicate: Array <%s> must NOT be empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean notEmpty(@Nonnull char[] array) {
		Null.nonNullArg(array, "array");
		return !(Array.getLength(array) == 0);
	}

	/** "Special" predicate: Array <%s> must NOT be empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String notEmptyEx(@Nonnull char[] array) {
		Null.nonNullArg(array, "array");
		return !(Array.getLength(array) == 0) ? null : String.format("Array <%s> must NOT be empty.", (Object) array);
	}

	/** Predicate: Array <%s> must be null or empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean nullOrEmpty(@Nullable char[] array) {
		return array == null || Array.getLength(array) == 0;
	}

	/** "Special" predicate: Array <%s> must be null or empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String nullOrEmptyEx(@Nullable char[] array) {
		return (array == null || Array.getLength(array) == 0) ? null : String.format("Array <%s> must be null or empty.", (Object) array);
	}

	/** Predicate: Array <%s> must NOT be null or empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean notNullNotEmpty(@Nullable char[] array) {
		return !(array == null || Array.getLength(array) == 0);
	}

	/** "Special" predicate: Array <%s> must NOT be null or empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String notNullNotEmptyEx(@Nullable char[] array) {
		return !(array == null || Array.getLength(array) == 0) ? null : String.format("Array <%s> must NOT be null or empty.", (Object) array);
	}

	/** Predicate: Array <%s> must be exactly of size 1 (singleton). Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean singleton(@Nonnull char[] array) {
		Null.nonNullArg(array, "array");
		return Array.getLength(array) == 1;
	}

	/** "Special" predicate: Array <%s> must be exactly of size 1 (singleton). Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String singletonEx(@Nonnull char[] array) {
		Null.nonNullArg(array, "array");
		return (Array.getLength(array) == 1) ? null : String.format("Array <%s> must be exactly of size 1 (singleton).", (Object) array);
	}

	/** Predicate: Array <%s> must NOT be exactly of size 1 (singleton). Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean notSingleton(@Nonnull char[] array) {
		Null.nonNullArg(array, "array");
		return !(Array.getLength(array) == 1);
	}

	/** "Special" predicate: Array <%s> must NOT be exactly of size 1 (singleton). Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String notSingletonEx(@Nonnull char[] array) {
		Null.nonNullArg(array, "array");
		return !(Array.getLength(array) == 1) ? null : String.format("Array <%s> must NOT be exactly of size 1 (singleton).", (Object) array);
	}

	/** Predicate: Array <%s> must be of size %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean ofLength(@Nonnull short[] array, int i) {
		Null.nonNullArg(array, "array");
		return Array.getLength(array) == i;
	}

	/** "Special" predicate: Array <%s> must be of size %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String ofLengthEx(@Nonnull short[] array, int i) {
		Null.nonNullArg(array, "array");
		return (Array.getLength(array) == i) ? null : String.format("Array <%s> must be of size %s.", array, i);
	}

	/** Predicate: Array <%s> must NOT be of size %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean notOfLength(@Nonnull short[] array, int i) {
		Null.nonNullArg(array, "array");
		return !(Array.getLength(array) == i);
	}

	/** "Special" predicate: Array <%s> must NOT be of size %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String notOfLengthEx(@Nonnull short[] array, int i) {
		Null.nonNullArg(array, "array");
		return !(Array.getLength(array) == i) ? null : String.format("Array <%s> must NOT be of size %s.", array, i);
	}

	/** Predicate: Array <%s> must be empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean empty(@Nonnull short[] array) {
		Null.nonNullArg(array, "array");
		return Array.getLength(array) == 0;
	}

	/** "Special" predicate: Array <%s> must be empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String emptyEx(@Nonnull short[] array) {
		Null.nonNullArg(array, "array");
		return (Array.getLength(array) == 0) ? null : String.format("Array <%s> must be empty.", (Object) array);
	}

	/** Predicate: Array <%s> must NOT be empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean notEmpty(@Nonnull short[] array) {
		Null.nonNullArg(array, "array");
		return !(Array.getLength(array) == 0);
	}

	/** "Special" predicate: Array <%s> must NOT be empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String notEmptyEx(@Nonnull short[] array) {
		Null.nonNullArg(array, "array");
		return !(Array.getLength(array) == 0) ? null : String.format("Array <%s> must NOT be empty.", (Object) array);
	}

	/** Predicate: Array <%s> must be null or empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean nullOrEmpty(@Nullable short[] array) {
		return array == null || Array.getLength(array) == 0;
	}

	/** "Special" predicate: Array <%s> must be null or empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String nullOrEmptyEx(@Nullable short[] array) {
		return (array == null || Array.getLength(array) == 0) ? null : String.format("Array <%s> must be null or empty.", (Object) array);
	}

	/** Predicate: Array <%s> must NOT be null or empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean notNullNotEmpty(@Nullable short[] array) {
		return !(array == null || Array.getLength(array) == 0);
	}

	/** "Special" predicate: Array <%s> must NOT be null or empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String notNullNotEmptyEx(@Nullable short[] array) {
		return !(array == null || Array.getLength(array) == 0) ? null : String.format("Array <%s> must NOT be null or empty.", (Object) array);
	}

	/** Predicate: Array <%s> must be exactly of size 1 (singleton). Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean singleton(@Nonnull short[] array) {
		Null.nonNullArg(array, "array");
		return Array.getLength(array) == 1;
	}

	/** "Special" predicate: Array <%s> must be exactly of size 1 (singleton). Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String singletonEx(@Nonnull short[] array) {
		Null.nonNullArg(array, "array");
		return (Array.getLength(array) == 1) ? null : String.format("Array <%s> must be exactly of size 1 (singleton).", (Object) array);
	}

	/** Predicate: Array <%s> must NOT be exactly of size 1 (singleton). Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean notSingleton(@Nonnull short[] array) {
		Null.nonNullArg(array, "array");
		return !(Array.getLength(array) == 1);
	}

	/** "Special" predicate: Array <%s> must NOT be exactly of size 1 (singleton). Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String notSingletonEx(@Nonnull short[] array) {
		Null.nonNullArg(array, "array");
		return !(Array.getLength(array) == 1) ? null : String.format("Array <%s> must NOT be exactly of size 1 (singleton).", (Object) array);
	}

	/** Predicate: Array <%s> must be of size %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean ofLength(@Nonnull float[] array, int i) {
		Null.nonNullArg(array, "array");
		return Array.getLength(array) == i;
	}

	/** "Special" predicate: Array <%s> must be of size %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String ofLengthEx(@Nonnull float[] array, int i) {
		Null.nonNullArg(array, "array");
		return (Array.getLength(array) == i) ? null : String.format("Array <%s> must be of size %s.", array, i);
	}

	/** Predicate: Array <%s> must NOT be of size %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean notOfLength(@Nonnull float[] array, int i) {
		Null.nonNullArg(array, "array");
		return !(Array.getLength(array) == i);
	}

	/** "Special" predicate: Array <%s> must NOT be of size %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String notOfLengthEx(@Nonnull float[] array, int i) {
		Null.nonNullArg(array, "array");
		return !(Array.getLength(array) == i) ? null : String.format("Array <%s> must NOT be of size %s.", array, i);
	}

	/** Predicate: Array <%s> must be empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean empty(@Nonnull float[] array) {
		Null.nonNullArg(array, "array");
		return Array.getLength(array) == 0;
	}

	/** "Special" predicate: Array <%s> must be empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String emptyEx(@Nonnull float[] array) {
		Null.nonNullArg(array, "array");
		return (Array.getLength(array) == 0) ? null : String.format("Array <%s> must be empty.", (Object) array);
	}

	/** Predicate: Array <%s> must NOT be empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean notEmpty(@Nonnull float[] array) {
		Null.nonNullArg(array, "array");
		return !(Array.getLength(array) == 0);
	}

	/** "Special" predicate: Array <%s> must NOT be empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String notEmptyEx(@Nonnull float[] array) {
		Null.nonNullArg(array, "array");
		return !(Array.getLength(array) == 0) ? null : String.format("Array <%s> must NOT be empty.", (Object) array);
	}

	/** Predicate: Array <%s> must be null or empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean nullOrEmpty(@Nullable float[] array) {
		return array == null || Array.getLength(array) == 0;
	}

	/** "Special" predicate: Array <%s> must be null or empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String nullOrEmptyEx(@Nullable float[] array) {
		return (array == null || Array.getLength(array) == 0) ? null : String.format("Array <%s> must be null or empty.", (Object) array);
	}

	/** Predicate: Array <%s> must NOT be null or empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean notNullNotEmpty(@Nullable float[] array) {
		return !(array == null || Array.getLength(array) == 0);
	}

	/** "Special" predicate: Array <%s> must NOT be null or empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String notNullNotEmptyEx(@Nullable float[] array) {
		return !(array == null || Array.getLength(array) == 0) ? null : String.format("Array <%s> must NOT be null or empty.", (Object) array);
	}

	/** Predicate: Array <%s> must be exactly of size 1 (singleton). Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean singleton(@Nonnull float[] array) {
		Null.nonNullArg(array, "array");
		return Array.getLength(array) == 1;
	}

	/** "Special" predicate: Array <%s> must be exactly of size 1 (singleton). Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String singletonEx(@Nonnull float[] array) {
		Null.nonNullArg(array, "array");
		return (Array.getLength(array) == 1) ? null : String.format("Array <%s> must be exactly of size 1 (singleton).", (Object) array);
	}

	/** Predicate: Array <%s> must NOT be exactly of size 1 (singleton). Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean notSingleton(@Nonnull float[] array) {
		Null.nonNullArg(array, "array");
		return !(Array.getLength(array) == 1);
	}

	/** "Special" predicate: Array <%s> must NOT be exactly of size 1 (singleton). Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String notSingletonEx(@Nonnull float[] array) {
		Null.nonNullArg(array, "array");
		return !(Array.getLength(array) == 1) ? null : String.format("Array <%s> must NOT be exactly of size 1 (singleton).", (Object) array);
	}

	/** Predicate: Array <%s> must be of size %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean ofLength(@Nonnull int[] array, int i) {
		Null.nonNullArg(array, "array");
		return Array.getLength(array) == i;
	}

	/** "Special" predicate: Array <%s> must be of size %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String ofLengthEx(@Nonnull int[] array, int i) {
		Null.nonNullArg(array, "array");
		return (Array.getLength(array) == i) ? null : String.format("Array <%s> must be of size %s.", array, i);
	}

	/** Predicate: Array <%s> must NOT be of size %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean notOfLength(@Nonnull int[] array, int i) {
		Null.nonNullArg(array, "array");
		return !(Array.getLength(array) == i);
	}

	/** "Special" predicate: Array <%s> must NOT be of size %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String notOfLengthEx(@Nonnull int[] array, int i) {
		Null.nonNullArg(array, "array");
		return !(Array.getLength(array) == i) ? null : String.format("Array <%s> must NOT be of size %s.", array, i);
	}

	/** Predicate: Array <%s> must be empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean empty(@Nonnull int[] array) {
		Null.nonNullArg(array, "array");
		return Array.getLength(array) == 0;
	}

	/** "Special" predicate: Array <%s> must be empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String emptyEx(@Nonnull int[] array) {
		Null.nonNullArg(array, "array");
		return (Array.getLength(array) == 0) ? null : String.format("Array <%s> must be empty.", (Object) array);
	}

	/** Predicate: Array <%s> must NOT be empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean notEmpty(@Nonnull int[] array) {
		Null.nonNullArg(array, "array");
		return !(Array.getLength(array) == 0);
	}

	/** "Special" predicate: Array <%s> must NOT be empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String notEmptyEx(@Nonnull int[] array) {
		Null.nonNullArg(array, "array");
		return !(Array.getLength(array) == 0) ? null : String.format("Array <%s> must NOT be empty.", (Object) array);
	}

	/** Predicate: Array <%s> must be null or empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean nullOrEmpty(@Nullable int[] array) {
		return array == null || Array.getLength(array) == 0;
	}

	/** "Special" predicate: Array <%s> must be null or empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String nullOrEmptyEx(@Nullable int[] array) {
		return (array == null || Array.getLength(array) == 0) ? null : String.format("Array <%s> must be null or empty.", (Object) array);
	}

	/** Predicate: Array <%s> must NOT be null or empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean notNullNotEmpty(@Nullable int[] array) {
		return !(array == null || Array.getLength(array) == 0);
	}

	/** "Special" predicate: Array <%s> must NOT be null or empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String notNullNotEmptyEx(@Nullable int[] array) {
		return !(array == null || Array.getLength(array) == 0) ? null : String.format("Array <%s> must NOT be null or empty.", (Object) array);
	}

	/** Predicate: Array <%s> must be exactly of size 1 (singleton). Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean singleton(@Nonnull int[] array) {
		Null.nonNullArg(array, "array");
		return Array.getLength(array) == 1;
	}

	/** "Special" predicate: Array <%s> must be exactly of size 1 (singleton). Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String singletonEx(@Nonnull int[] array) {
		Null.nonNullArg(array, "array");
		return (Array.getLength(array) == 1) ? null : String.format("Array <%s> must be exactly of size 1 (singleton).", (Object) array);
	}

	/** Predicate: Array <%s> must NOT be exactly of size 1 (singleton). Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean notSingleton(@Nonnull int[] array) {
		Null.nonNullArg(array, "array");
		return !(Array.getLength(array) == 1);
	}

	/** "Special" predicate: Array <%s> must NOT be exactly of size 1 (singleton). Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String notSingletonEx(@Nonnull int[] array) {
		Null.nonNullArg(array, "array");
		return !(Array.getLength(array) == 1) ? null : String.format("Array <%s> must NOT be exactly of size 1 (singleton).", (Object) array);
	}

	/** Predicate: Array <%s> must be of size %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean ofLength(@Nonnull long[] array, int i) {
		Null.nonNullArg(array, "array");
		return Array.getLength(array) == i;
	}

	/** "Special" predicate: Array <%s> must be of size %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String ofLengthEx(@Nonnull long[] array, int i) {
		Null.nonNullArg(array, "array");
		return (Array.getLength(array) == i) ? null : String.format("Array <%s> must be of size %s.", array, i);
	}

	/** Predicate: Array <%s> must NOT be of size %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean notOfLength(@Nonnull long[] array, int i) {
		Null.nonNullArg(array, "array");
		return !(Array.getLength(array) == i);
	}

	/** "Special" predicate: Array <%s> must NOT be of size %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String notOfLengthEx(@Nonnull long[] array, int i) {
		Null.nonNullArg(array, "array");
		return !(Array.getLength(array) == i) ? null : String.format("Array <%s> must NOT be of size %s.", array, i);
	}

	/** Predicate: Array <%s> must be empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean empty(@Nonnull long[] array) {
		Null.nonNullArg(array, "array");
		return Array.getLength(array) == 0;
	}

	/** "Special" predicate: Array <%s> must be empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String emptyEx(@Nonnull long[] array) {
		Null.nonNullArg(array, "array");
		return (Array.getLength(array) == 0) ? null : String.format("Array <%s> must be empty.", (Object) array);
	}

	/** Predicate: Array <%s> must NOT be empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean notEmpty(@Nonnull long[] array) {
		Null.nonNullArg(array, "array");
		return !(Array.getLength(array) == 0);
	}

	/** "Special" predicate: Array <%s> must NOT be empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String notEmptyEx(@Nonnull long[] array) {
		Null.nonNullArg(array, "array");
		return !(Array.getLength(array) == 0) ? null : String.format("Array <%s> must NOT be empty.", (Object) array);
	}

	/** Predicate: Array <%s> must be null or empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean nullOrEmpty(@Nullable long[] array) {
		return array == null || Array.getLength(array) == 0;
	}

	/** "Special" predicate: Array <%s> must be null or empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String nullOrEmptyEx(@Nullable long[] array) {
		return (array == null || Array.getLength(array) == 0) ? null : String.format("Array <%s> must be null or empty.", (Object) array);
	}

	/** Predicate: Array <%s> must NOT be null or empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean notNullNotEmpty(@Nullable long[] array) {
		return !(array == null || Array.getLength(array) == 0);
	}

	/** "Special" predicate: Array <%s> must NOT be null or empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String notNullNotEmptyEx(@Nullable long[] array) {
		return !(array == null || Array.getLength(array) == 0) ? null : String.format("Array <%s> must NOT be null or empty.", (Object) array);
	}

	/** Predicate: Array <%s> must be exactly of size 1 (singleton). Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean singleton(@Nonnull long[] array) {
		Null.nonNullArg(array, "array");
		return Array.getLength(array) == 1;
	}

	/** "Special" predicate: Array <%s> must be exactly of size 1 (singleton). Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String singletonEx(@Nonnull long[] array) {
		Null.nonNullArg(array, "array");
		return (Array.getLength(array) == 1) ? null : String.format("Array <%s> must be exactly of size 1 (singleton).", (Object) array);
	}

	/** Predicate: Array <%s> must NOT be exactly of size 1 (singleton). Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean notSingleton(@Nonnull long[] array) {
		Null.nonNullArg(array, "array");
		return !(Array.getLength(array) == 1);
	}

	/** "Special" predicate: Array <%s> must NOT be exactly of size 1 (singleton). Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String notSingletonEx(@Nonnull long[] array) {
		Null.nonNullArg(array, "array");
		return !(Array.getLength(array) == 1) ? null : String.format("Array <%s> must NOT be exactly of size 1 (singleton).", (Object) array);
	}

	/** Predicate: Array <%s> must be of size %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static <T> boolean ofLength(@Nonnull T[] array, int i) {
		Null.nonNullArg(array, "array");
		return Array.getLength(array) == i;
	}

	/** "Special" predicate: Array <%s> must be of size %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static <T> String ofLengthEx(@Nonnull T[] array, int i) {
		Null.nonNullArg(array, "array");
		return (Array.getLength(array) == i) ? null : String.format("Array <%s> must be of size %s.", array, i);
	}

	/** Predicate: Array <%s> must NOT be of size %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static <T> boolean notOfLength(@Nonnull T[] array, int i) {
		Null.nonNullArg(array, "array");
		return !(Array.getLength(array) == i);
	}

	/** "Special" predicate: Array <%s> must NOT be of size %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static <T> String notOfLengthEx(@Nonnull T[] array, int i) {
		Null.nonNullArg(array, "array");
		return !(Array.getLength(array) == i) ? null : String.format("Array <%s> must NOT be of size %s.", array, i);
	}

	/** Predicate: Array <%s> must be empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static <T> boolean empty(@Nonnull T[] array) {
		Null.nonNullArg(array, "array");
		return Array.getLength(array) == 0;
	}

	/** "Special" predicate: Array <%s> must be empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static <T> String emptyEx(@Nonnull T[] array) {
		Null.nonNullArg(array, "array");
		return (Array.getLength(array) == 0) ? null : String.format("Array <%s> must be empty.", (Object) array);
	}

	/** Predicate: Array <%s> must NOT be empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static <T> boolean notEmpty(@Nonnull T[] array) {
		Null.nonNullArg(array, "array");
		return !(Array.getLength(array) == 0);
	}

	/** "Special" predicate: Array <%s> must NOT be empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static <T> String notEmptyEx(@Nonnull T[] array) {
		Null.nonNullArg(array, "array");
		return !(Array.getLength(array) == 0) ? null : String.format("Array <%s> must NOT be empty.", (Object) array);
	}

	/** Predicate: Array <%s> must be null or empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static <T> boolean nullOrEmpty(@Nullable T[] array) {
		return array == null || Array.getLength(array) == 0;
	}

	/** "Special" predicate: Array <%s> must be null or empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static <T> String nullOrEmptyEx(@Nullable T[] array) {
		return (array == null || Array.getLength(array) == 0) ? null : String.format("Array <%s> must be null or empty.", (Object) array);
	}

	/** Predicate: Array <%s> must NOT be null or empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static <T> boolean notNullNotEmpty(@Nullable T[] array) {
		return !(array == null || Array.getLength(array) == 0);
	}

	/** "Special" predicate: Array <%s> must NOT be null or empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static <T> String notNullNotEmptyEx(@Nullable T[] array) {
		return !(array == null || Array.getLength(array) == 0) ? null : String.format("Array <%s> must NOT be null or empty.", (Object) array);
	}

	/** Predicate: Array <%s> must be exactly of size 1 (singleton). Available in {@link P}, {@link Is}, {@link Be}.*/
	public static <T> boolean singleton(@Nonnull T[] array) {
		Null.nonNullArg(array, "array");
		return Array.getLength(array) == 1;
	}

	/** "Special" predicate: Array <%s> must be exactly of size 1 (singleton). Available in {@link P}, {@link Is}, {@link Be}.*/
	public static <T> String singletonEx(@Nonnull T[] array) {
		Null.nonNullArg(array, "array");
		return (Array.getLength(array) == 1) ? null : String.format("Array <%s> must be exactly of size 1 (singleton).", (Object) array);
	}

	/** Predicate: Array <%s> must NOT be exactly of size 1 (singleton). Available in {@link P}, {@link Is}, {@link Be}.*/
	public static <T> boolean notSingleton(@Nonnull T[] array) {
		Null.nonNullArg(array, "array");
		return !(Array.getLength(array) == 1);
	}

	/** "Special" predicate: Array <%s> must NOT be exactly of size 1 (singleton). Available in {@link P}, {@link Is}, {@link Be}.*/
	public static <T> String notSingletonEx(@Nonnull T[] array) {
		Null.nonNullArg(array, "array");
		return !(Array.getLength(array) == 1) ? null : String.format("Array <%s> must NOT be exactly of size 1 (singleton).", (Object) array);
	}

	// </editor-fold>

	// <editor-fold desc="collections">

	/** Predicate: Collection <%s> must be of size %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static <T> boolean ofSize(@Nonnull Collection<T> collection, int i) {
		Null.nonNullArg(collection, "collection");
		return collection.size() == i;
	}

	/** "Special" predicate: Collection <%s> must be of size %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static <T> String ofSizeEx(@Nonnull Collection<T> collection, int i) {
		Null.nonNullArg(collection, "collection");
		return (collection.size() == i) ? null : String.format("Collection <%s> must be of size %s.", collection, i);
	}

	/** Predicate: Collection <%s> must NOT be of size %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static <T> boolean notOfSize(@Nonnull Collection<T> collection, int i) {
		Null.nonNullArg(collection, "collection");
		return !(collection.size() == i);
	}

	/** "Special" predicate: Collection <%s> must NOT be of size %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static <T> String notOfSizeEx(@Nonnull Collection<T> collection, int i) {
		Null.nonNullArg(collection, "collection");
		return !(collection.size() == i) ? null : String.format("Collection <%s> must NOT be of size %s.", collection, i);
	}

	/** Predicate: Map <%s> must be of size %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static <K, V> boolean ofSize(@Nonnull Map<K, V> map, int i) {
		Null.nonNullArg(map, "map");
		return map.size() == i;
	}

	/** "Special" predicate: Map <%s> must be of size %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static <K, V> String ofSizeEx(@Nonnull Map<K, V> map, int i) {
		Null.nonNullArg(map, "map");
		return (map.size() == i) ? null : String.format("Map <%s> must be of size %s.", map, i);
	}

	/** Predicate: Map <%s> must NOT be of size %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static <K, V> boolean notOfSize(@Nonnull Map<K, V> map, int i) {
		Null.nonNullArg(map, "map");
		return !(map.size() == i);
	}

	/** "Special" predicate: Map <%s> must NOT be of size %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static <K, V> String notOfSizeEx(@Nonnull Map<K, V> map, int i) {
		Null.nonNullArg(map, "map");
		return !(map.size() == i) ? null : String.format("Map <%s> must NOT be of size %s.", map, i);
	}

	/** Predicate: <%s> must be part of <%s> collection. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static <T> boolean partOf(T element, @Nonnull Collection<T> collection) {
		Null.nonNullArg(collection, "collection");
		return collection.contains(element);
	}

	/** "Special" predicate: <%s> must be part of <%s> collection. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static <T> String partOfEx(T element, @Nonnull Collection<T> collection) {
		Null.nonNullArg(collection, "collection");
		return (collection.contains(element)) ? null : String.format("<%s> must be part of <%s> collection.", element, collection);
	}

	/** Predicate: <%s> must NOT be part of <%s> collection. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static <T> boolean notPartOf(T element, @Nonnull Collection<T> collection) {
		Null.nonNullArg(collection, "collection");
		return !(collection.contains(element));
	}

	/** "Special" predicate: <%s> must NOT be part of <%s> collection. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static <T> String notPartOfEx(T element, @Nonnull Collection<T> collection) {
		Null.nonNullArg(collection, "collection");
		return !(collection.contains(element)) ? null : String.format("<%s> must NOT be part of <%s> collection.", element, collection);
	}

	/** Predicate: <%s> must be key in <%s> map. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static <K> boolean aKeyIn(K key, @Nonnull Map<K, ?> map) {
		Null.nonNullArg(map, "map");
		return map.containsKey(key);
	}

	/** "Special" predicate: <%s> must be key in <%s> map. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static <K> String aKeyInEx(K key, @Nonnull Map<K, ?> map) {
		Null.nonNullArg(map, "map");
		return (map.containsKey(key)) ? null : String.format("<%s> must be key in <%s> map.", key, map);
	}

	/** Predicate: <%s> must NOT be key in <%s> map. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static <K> boolean notAKeyIn(K key, @Nonnull Map<K, ?> map) {
		Null.nonNullArg(map, "map");
		return !(map.containsKey(key));
	}

	/** "Special" predicate: <%s> must NOT be key in <%s> map. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static <K> String notAKeyInEx(K key, @Nonnull Map<K, ?> map) {
		Null.nonNullArg(map, "map");
		return !(map.containsKey(key)) ? null : String.format("<%s> must NOT be key in <%s> map.", key, map);
	}

	/** Predicate: Collection <%s> must be empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static <T> boolean empty(@Nonnull Collection<T> collection) {
		Null.nonNullArg(collection, "collection");
		return collection.isEmpty();
	}

	/** "Special" predicate: Collection <%s> must be empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static <T> String emptyEx(@Nonnull Collection<T> collection) {
		Null.nonNullArg(collection, "collection");
		return (collection.isEmpty()) ? null : String.format("Collection <%s> must be empty.", collection);
	}

	/** Predicate: Collection <%s> must NOT be empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static <T> boolean notEmpty(@Nonnull Collection<T> collection) {
		Null.nonNullArg(collection, "collection");
		return !(collection.isEmpty());
	}

	/** "Special" predicate: Collection <%s> must NOT be empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static <T> String notEmptyEx(@Nonnull Collection<T> collection) {
		Null.nonNullArg(collection, "collection");
		return !(collection.isEmpty()) ? null : String.format("Collection <%s> must NOT be empty.", collection);
	}

	/** Predicate: Collection <%s> must be empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static <T> boolean nullOrEmpty(@Nullable Collection<T> collection) {
		return collection == null || collection.isEmpty();
	}

	/** "Special" predicate: Collection <%s> must be empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static <T> String nullOrEmptyEx(@Nullable Collection<T> collection) {
		return (collection == null || collection.isEmpty()) ? null : String.format("Collection <%s> must be empty.", collection);
	}

	/** Predicate: Collection <%s> must NOT be empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static <T> boolean notNullNorEmpty(@Nullable Collection<T> collection) {
		return !(collection == null || collection.isEmpty());
	}

	/** "Special" predicate: Collection <%s> must NOT be empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static <T> String notNullNorEmptyEx(@Nullable Collection<T> collection) {
		return !(collection == null || collection.isEmpty()) ? null : String.format("Collection <%s> must NOT be empty.", collection);
	}

	/** Predicate: Collection <%s> must be exactly of size 1 (singleton).. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static <T> boolean singleton(@Nonnull Collection<T> collection) {
		Null.nonNullArg(collection, "collection");
		return collection.size() == 1;
	}

	/** "Special" predicate: Collection <%s> must be exactly of size 1 (singleton).. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static <T> String singletonEx(@Nonnull Collection<T> collection) {
		Null.nonNullArg(collection, "collection");
		return (collection.size() == 1) ? null : String.format("Collection <%s> must be exactly of size 1 (singleton)..", collection);
	}

	/** Predicate: Collection <%s> must NOT be exactly of size 1 (singleton).. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static <T> boolean notSingleton(@Nonnull Collection<T> collection) {
		Null.nonNullArg(collection, "collection");
		return !(collection.size() == 1);
	}

	/** "Special" predicate: Collection <%s> must NOT be exactly of size 1 (singleton).. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static <T> String notSingletonEx(@Nonnull Collection<T> collection) {
		Null.nonNullArg(collection, "collection");
		return !(collection.size() == 1) ? null : String.format("Collection <%s> must NOT be exactly of size 1 (singleton)..", collection);
	}

	/** Predicate: Map <%s> must be empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean empty(@Nonnull Map<?, ?> map) {
		Null.nonNullArg(map, "map");
		return map.isEmpty();
	}

	/** "Special" predicate: Map <%s> must be empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String emptyEx(@Nonnull Map<?, ?> map) {
		Null.nonNullArg(map, "map");
		return (map.isEmpty()) ? null : String.format("Map <%s> must be empty.", map);
	}

	/** Predicate: Map <%s> must NOT be empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean notEmpty(@Nonnull Map<?, ?> map) {
		Null.nonNullArg(map, "map");
		return !(map.isEmpty());
	}

	/** "Special" predicate: Map <%s> must NOT be empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String notEmptyEx(@Nonnull Map<?, ?> map) {
		Null.nonNullArg(map, "map");
		return !(map.isEmpty()) ? null : String.format("Map <%s> must NOT be empty.", map);
	}

	/** Predicate: Collection <%s> must be empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean nullOrEmpty(@Nullable Map<?, ?> map) {
		return map == null || map.isEmpty();
	}

	/** "Special" predicate: Collection <%s> must be empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String nullOrEmptyEx(@Nullable Map<?, ?> map) {
		return (map == null || map.isEmpty()) ? null : String.format("Collection <%s> must be empty.", map);
	}

	/** Predicate: Collection <%s> must NOT be empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean notNullNorEmpty(@Nullable Map<?, ?> map) {
		return !(map == null || map.isEmpty());
	}

	/** "Special" predicate: Collection <%s> must NOT be empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String notNullNorEmptyEx(@Nullable Map<?, ?> map) {
		return !(map == null || map.isEmpty()) ? null : String.format("Collection <%s> must NOT be empty.", map);
	}

	/** Predicate: Collection <%s> must be exactly of size 1 (singleton).. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean singleton(@Nonnull Map<?, ?> map) {
		Null.nonNullArg(map, "map");
		return map.size() == 1;
	}

	/** "Special" predicate: Collection <%s> must be exactly of size 1 (singleton).. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String singletonEx(@Nonnull Map<?, ?> map) {
		Null.nonNullArg(map, "map");
		return (map.size() == 1) ? null : String.format("Collection <%s> must be exactly of size 1 (singleton)..", map);
	}

	/** Predicate: Collection <%s> must NOT be exactly of size 1 (singleton).. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean notSingleton(@Nonnull Map<?, ?> map) {
		Null.nonNullArg(map, "map");
		return !(map.size() == 1);
	}

	/** "Special" predicate: Collection <%s> must NOT be exactly of size 1 (singleton).. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String notSingletonEx(@Nonnull Map<?, ?> map) {
		Null.nonNullArg(map, "map");
		return !(map.size() == 1) ? null : String.format("Collection <%s> must NOT be exactly of size 1 (singleton)..", map);
	}

	// </editor-fold>

	// <editor-fold desc="object derivatives">

	/** Predicate: Object <%s> must be instance of <%s>. Available in {@link P}, {@link Is}, {@link Be}, {@link P1}, {@link P2}.*/
	public static boolean instanceOf(Object object, Class<?> clazz) {
		Null.nonNullArg(clazz, "clazz");
		return clazz.isInstance(object);
	}

	/** "Special" predicate: Object <%s> must be instance of <%s>. Available in {@link P}, {@link Is}, {@link Be}, {@link P1}, {@link P2}.*/
	public static String instanceOfEx(Object object, Class<?> clazz) {
		Null.nonNullArg(clazz, "clazz");
		return (clazz.isInstance(object)) ? null : String.format("Object <%s> must be instance of <%s>.", object, clazz);
	}

	/** Predicate: Object <%s> must NOT be instance of <%s>. Available in {@link P}, {@link Is}, {@link Be}, {@link P1}, {@link P2}.*/
	public static boolean notInstanceOf(Object object, Class<?> clazz) {
		Null.nonNullArg(clazz, "clazz");
		return !(clazz.isInstance(object));
	}

	/** "Special" predicate: Object <%s> must NOT be instance of <%s>. Available in {@link P}, {@link Is}, {@link Be}, {@link P1}, {@link P2}.*/
	public static String notInstanceOfEx(Object object, Class<?> clazz) {
		Null.nonNullArg(clazz, "clazz");
		return !(clazz.isInstance(object)) ? null : String.format("Object <%s> must NOT be instance of <%s>.", object, clazz);
	}

	/** Predicate: Class <%s> must ---NOT-- be specialization of <%s>. Available in {@link P}, {@link Is}, {@link Be}, {@link P1}, {@link P2}.*/
	public static boolean assignableFrom(Class<?> specialization, Class<?> clazz) {
		Null.nonNullArg(clazz, "clazz");
		return clazz.isAssignableFrom(specialization);
	}

	/** "Special" predicate: Class <%s> must ---NOT-- be specialization of <%s>. Available in {@link P}, {@link Is}, {@link Be}, {@link P1}, {@link P2}.*/
	public static String assignableFromEx(Class<?> specialization, Class<?> clazz) {
		Null.nonNullArg(clazz, "clazz");
		return (clazz.isAssignableFrom(specialization)) ? null : String.format("Class <%s> must ---NOT-- be specialization of <%s>.", specialization, clazz);
	}

	/** Predicate: Class <%s> must ---NOT-- be specialization of <%s>. Available in {@link P}, {@link Is}, {@link Be}, {@link P1}, {@link P2}.*/
	public static boolean notAssignableFrom(Class<?> specialization, Class<?> clazz) {
		Null.nonNullArg(clazz, "clazz");
		return !(clazz.isAssignableFrom(specialization));
	}

	/** "Special" predicate: Class <%s> must ---NOT-- be specialization of <%s>. Available in {@link P}, {@link Is}, {@link Be}, {@link P1}, {@link P2}.*/
	public static String notAssignableFromEx(Class<?> specialization, Class<?> clazz) {
		Null.nonNullArg(clazz, "clazz");
		return !(clazz.isAssignableFrom(specialization)) ? null : String.format("Class <%s> must ---NOT-- be specialization of <%s>.", specialization, clazz);
	}

	// </editor-fold>

	// <editor-fold desc="Throwables">

	/** Predicate: Exception <%s> must be instance of a RuntimeException. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean runtime(@Nonnull Throwable e) {
		Null.nonNullArg(e, "e");
		return e instanceof RuntimeException;
	}

	/** "Special" predicate: Exception <%s> must be instance of a RuntimeException. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String runtimeEx(@Nonnull Throwable e) {
		Null.nonNullArg(e, "e");
		return (e instanceof RuntimeException) ? null : String.format("Exception <%s> must be instance of a RuntimeException.", e);
	}

	/** Predicate: Exception <%s> must NOT be instance of a RuntimeException. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean notRuntime(@Nonnull Throwable e) {
		Null.nonNullArg(e, "e");
		return !(e instanceof RuntimeException);
	}

	/** "Special" predicate: Exception <%s> must NOT be instance of a RuntimeException. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String notRuntimeEx(@Nonnull Throwable e) {
		Null.nonNullArg(e, "e");
		return !(e instanceof RuntimeException) ? null : String.format("Exception <%s> must NOT be instance of a RuntimeException.", e);
	}

	// </editor-fold>

}
