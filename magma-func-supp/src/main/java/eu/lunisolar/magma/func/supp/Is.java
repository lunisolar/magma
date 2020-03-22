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
import eu.lunisolar.magma.func.supp.memento.*; // NOSONAR
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
 * - when({@link Is}::equal, 4)
 *
 * @see {@link P}, {@link Is}, {@link Does}, {@link Be}, {@link Are}
 */
public final class Is implements FluentSyntax {
	// <editor-fold desc="no instance">
	private Is() {
	}
	// </editor-fold>

	// <editor-fold desc="Object">

	/** Predicate: Object <%s> must be the same as <%s>. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean same(@Nullable Object n, @Nullable Object other) {
		return n == other;
	}

	/** "Special" predicate: Object <%s> must be the same as <%s>. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String same$(@Nullable Object n, @Nullable Object other) {
		return (n == other) ? null : String.format("Object <%s> must be the same as <%s>.", n, other);
	}

	/** Predicate: Object <%s> must NOT be the same as <%s>. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean notSame(@Nullable Object n, @Nullable Object other) {
		return !(n == other);
	}

	/** "Special" predicate: Object <%s> must NOT be the same as <%s>. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String notSame$(@Nullable Object n, @Nullable Object other) {
		return !(n == other) ? null : String.format("Object <%s> must NOT be the same as <%s>.", n, other);
	}

	/** Predicate: Reference must be null, currently is pointing to <%s>. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean Null(@Nullable Object n) {
		return n == null;
	}

	/** "Special" predicate: Reference must be null, currently is pointing to <%s>. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String Null$(@Nullable Object n) {
		return (n == null) ? null : String.format("Reference must be null, currently is pointing to <%s>.", n);
	}

	/** Predicate: Reference must NOT be null, currently is pointing to <%s>. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean notNull(@Nullable Object n) {
		return !(n == null);
	}

	/** "Special" predicate: Reference must NOT be null, currently is pointing to <%s>. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String notNull$(@Nullable Object n) {
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

	public static String allNull$(@Nullable Object... objects) {
		return allNull(objects) ? null : String.format("All references must be null.");
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

	public static String anyNull$(@Nullable Object... objects) {
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
	public static String ofLength$(@Nonnull CharSequence s, int size) {
		Null.nonNullArg(s, "s");
		return (size == s.length()) ? null : String.format("String <'%s'> must be <%d> characters long.", s, size);
	}

	/** Predicate: String <'%s'> must NOT be <%d> characters long. Available in {@link P}, {@link Has}, {@link Have}, {@link Is}, {@link Be}.*/
	public static boolean notOfLength(@Nonnull CharSequence s, int size) {
		Null.nonNullArg(s, "s");
		return !(size == s.length());
	}

	/** "Special" predicate: String <'%s'> must NOT be <%d> characters long. Available in {@link P}, {@link Has}, {@link Have}, {@link Is}, {@link Be}.*/
	public static String notOfLength$(@Nonnull CharSequence s, int size) {
		Null.nonNullArg(s, "s");
		return !(size == s.length()) ? null : String.format("String <'%s'> must NOT be <%d> characters long.", s, size);
	}

	/** Predicate: String <'%s'> must be empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean empty(@Nonnull String n) {
		Null.nonNullArg(n, "n");
		return n.isEmpty();
	}

	/** "Special" predicate: String <'%s'> must be empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String empty$(@Nonnull String n) {
		Null.nonNullArg(n, "n");
		return (n.isEmpty()) ? null : String.format("String <'%s'> must be empty.", n);
	}

	/** Predicate: String <'%s'> must NOT be empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean notEmpty(@Nonnull String n) {
		Null.nonNullArg(n, "n");
		return !(n.isEmpty());
	}

	/** "Special" predicate: String <'%s'> must NOT be empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String notEmpty$(@Nonnull String n) {
		Null.nonNullArg(n, "n");
		return !(n.isEmpty()) ? null : String.format("String <'%s'> must NOT be empty.", n);
	}

	/** Predicate: String <'%s'> must be blank (empty or consisting of only white characters). Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean blank(@Nonnull String n) {
		Null.nonNullArg(n, "n");
		return n.isBlank();
	}

	/** "Special" predicate: String <'%s'> must be blank (empty or consisting of only white characters). Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String blank$(@Nonnull String n) {
		Null.nonNullArg(n, "n");
		return (n.isBlank()) ? null : String.format("String <'%s'> must be blank (empty or consisting of only white characters).", n);
	}

	/** Predicate: String <'%s'> must NOT be blank (empty or consisting of only white characters). Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean notBlank(@Nonnull String n) {
		Null.nonNullArg(n, "n");
		return !(n.isBlank());
	}

	/** "Special" predicate: String <'%s'> must NOT be blank (empty or consisting of only white characters). Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String notBlank$(@Nonnull String n) {
		Null.nonNullArg(n, "n");
		return !(n.isBlank()) ? null : String.format("String <'%s'> must NOT be blank (empty or consisting of only white characters).", n);
	}

	/** Predicate: String <'%s'> must be null or empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean nullOrEmpty(@Nonnull String n) {
		return n == null || n.isEmpty();
	}

	/** "Special" predicate: String <'%s'> must be null or empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String nullOrEmpty$(@Nonnull String n) {
		return (n == null || n.isEmpty()) ? null : String.format("String <'%s'> must be null or empty.", n);
	}

	/** Predicate: String <'%s'> must NOT be null or empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean notNullNorEmpty(@Nonnull String n) {
		return !(n == null || n.isEmpty());
	}

	/** "Special" predicate: String <'%s'> must NOT be null or empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String notNullNorEmpty$(@Nonnull String n) {
		return !(n == null || n.isEmpty()) ? null : String.format("String <'%s'> must NOT be null or empty.", n);
	}

	/** Predicate: String <'%s'> must be null or blank. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean nullOrBlank(@Nonnull String n) {
		return n == null || n.isBlank();
	}

	/** "Special" predicate: String <'%s'> must be null or blank. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String nullOrBlank$(@Nonnull String n) {
		return (n == null || n.isBlank()) ? null : String.format("String <'%s'> must be null or blank.", n);
	}

	/** Predicate: String <'%s'> must NOT be null or blank. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean notNullNorBlank(@Nonnull String n) {
		return !(n == null || n.isBlank());
	}

	/** "Special" predicate: String <'%s'> must NOT be null or blank. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String notNullNorBlank$(@Nonnull String n) {
		return !(n == null || n.isBlank()) ? null : String.format("String <'%s'> must NOT be null or blank.", n);
	}

	// </editor-fold>

	// <editor-fold desc="and/or/xor">

	// </editor-fold>

	// <editor-fold desc="== <= >= < >">

	/** Predicate: <%s> must be equal to <%s>. Available in {@link P}, {@link Is}, {@link Be}, {@link Does}.*/
	public static boolean equal(Object o, Object a1) {
		return Objects.equals(o, a1);
	}

	/** "Special" predicate: <%s> must be equal to <%s>. Available in {@link P}, {@link Is}, {@link Be}, {@link Does}.*/
	public static String equal$(Object o, Object a1) {
		return (Objects.equals(o, a1)) ? null : String.format("<%s> must be equal to <%s>.", o, a1);
	}

	/** Predicate: <%s> must NOT be equal to <%s>. Available in {@link P}, {@link Is}, {@link Be}, {@link Does}.*/
	public static boolean notEqual(Object o, Object a1) {
		return !(Objects.equals(o, a1));
	}

	/** "Special" predicate: <%s> must NOT be equal to <%s>. Available in {@link P}, {@link Is}, {@link Be}, {@link Does}.*/
	public static String notEqual$(Object o, Object a1) {
		return !(Objects.equals(o, a1)) ? null : String.format("<%s> must NOT be equal to <%s>.", o, a1);
	}

	/** Predicate: %s must be equal to %s. Available in {@link P}, {@link Is}, {@link Be}, {@link Does}.*/
	public static boolean equal(byte n, byte a1) {
		return n == a1;
	}

	/** "Special" predicate: %s must be equal to %s. Available in {@link P}, {@link Is}, {@link Be}, {@link Does}.*/
	public static String equal$(byte n, byte a1) {
		return (n == a1) ? null : String.format("%s must be equal to %s.", n, a1);
	}

	/** Predicate: %s must NOT be equal to %s. Available in {@link P}, {@link Is}, {@link Be}, {@link Does}.*/
	public static boolean notEqual(byte n, byte a1) {
		return n != a1;
	}

	/** "Special" predicate: %s must NOT be equal to %s. Available in {@link P}, {@link Is}, {@link Be}, {@link Does}.*/
	public static String notEqual$(byte n, byte a1) {
		return (n != a1) ? null : String.format("%s must NOT be equal to %s.", n, a1);
	}

	/** Predicate: %s must be > %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean gt(byte n, byte a1) {
		return n > a1;
	}

	/** "Special" predicate: %s must be > %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String gt$(byte n, byte a1) {
		return (n > a1) ? null : String.format("%s must be > %s.", n, a1);
	}

	/** Predicate: %s must be < %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean lt(byte n, byte a1) {
		return n < a1;
	}

	/** "Special" predicate: %s must be < %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String lt$(byte n, byte a1) {
		return (n < a1) ? null : String.format("%s must be < %s.", n, a1);
	}

	/** Predicate: %s must be >= %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean gtEq(byte n, byte a1) {
		return n >= a1;
	}

	/** "Special" predicate: %s must be >= %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String gtEq$(byte n, byte a1) {
		return (n >= a1) ? null : String.format("%s must be >= %s.", n, a1);
	}

	/** Predicate: %s must be <= %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean ltEq(byte n, byte a1) {
		return n <= a1;
	}

	/** "Special" predicate: %s must be <= %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String ltEq$(byte n, byte a1) {
		return (n <= a1) ? null : String.format("%s must be <= %s.", n, a1);
	}

	/** Predicate: %s must be equal to %s. Available in {@link P}, {@link Is}, {@link Be}, {@link Does}.*/
	public static boolean equal(short n, short a1) {
		return n == a1;
	}

	/** "Special" predicate: %s must be equal to %s. Available in {@link P}, {@link Is}, {@link Be}, {@link Does}.*/
	public static String equal$(short n, short a1) {
		return (n == a1) ? null : String.format("%s must be equal to %s.", n, a1);
	}

	/** Predicate: %s must NOT be equal to %s. Available in {@link P}, {@link Is}, {@link Be}, {@link Does}.*/
	public static boolean notEqual(short n, short a1) {
		return n != a1;
	}

	/** "Special" predicate: %s must NOT be equal to %s. Available in {@link P}, {@link Is}, {@link Be}, {@link Does}.*/
	public static String notEqual$(short n, short a1) {
		return (n != a1) ? null : String.format("%s must NOT be equal to %s.", n, a1);
	}

	/** Predicate: %s must be > %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean gt(short n, short a1) {
		return n > a1;
	}

	/** "Special" predicate: %s must be > %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String gt$(short n, short a1) {
		return (n > a1) ? null : String.format("%s must be > %s.", n, a1);
	}

	/** Predicate: %s must be < %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean lt(short n, short a1) {
		return n < a1;
	}

	/** "Special" predicate: %s must be < %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String lt$(short n, short a1) {
		return (n < a1) ? null : String.format("%s must be < %s.", n, a1);
	}

	/** Predicate: %s must be >= %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean gtEq(short n, short a1) {
		return n >= a1;
	}

	/** "Special" predicate: %s must be >= %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String gtEq$(short n, short a1) {
		return (n >= a1) ? null : String.format("%s must be >= %s.", n, a1);
	}

	/** Predicate: %s must be <= %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean ltEq(short n, short a1) {
		return n <= a1;
	}

	/** "Special" predicate: %s must be <= %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String ltEq$(short n, short a1) {
		return (n <= a1) ? null : String.format("%s must be <= %s.", n, a1);
	}

	/** Predicate: %s must be equal to %s. Available in {@link P}, {@link Is}, {@link Be}, {@link Does}.*/
	public static boolean equal(int n, int a1) {
		return n == a1;
	}

	/** "Special" predicate: %s must be equal to %s. Available in {@link P}, {@link Is}, {@link Be}, {@link Does}.*/
	public static String equal$(int n, int a1) {
		return (n == a1) ? null : String.format("%s must be equal to %s.", n, a1);
	}

	/** Predicate: %s must NOT be equal to %s. Available in {@link P}, {@link Is}, {@link Be}, {@link Does}.*/
	public static boolean notEqual(int n, int a1) {
		return n != a1;
	}

	/** "Special" predicate: %s must NOT be equal to %s. Available in {@link P}, {@link Is}, {@link Be}, {@link Does}.*/
	public static String notEqual$(int n, int a1) {
		return (n != a1) ? null : String.format("%s must NOT be equal to %s.", n, a1);
	}

	/** Predicate: %s must be > %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean gt(int n, int a1) {
		return n > a1;
	}

	/** "Special" predicate: %s must be > %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String gt$(int n, int a1) {
		return (n > a1) ? null : String.format("%s must be > %s.", n, a1);
	}

	/** Predicate: %s must be < %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean lt(int n, int a1) {
		return n < a1;
	}

	/** "Special" predicate: %s must be < %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String lt$(int n, int a1) {
		return (n < a1) ? null : String.format("%s must be < %s.", n, a1);
	}

	/** Predicate: %s must be >= %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean gtEq(int n, int a1) {
		return n >= a1;
	}

	/** "Special" predicate: %s must be >= %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String gtEq$(int n, int a1) {
		return (n >= a1) ? null : String.format("%s must be >= %s.", n, a1);
	}

	/** Predicate: %s must be <= %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean ltEq(int n, int a1) {
		return n <= a1;
	}

	/** "Special" predicate: %s must be <= %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String ltEq$(int n, int a1) {
		return (n <= a1) ? null : String.format("%s must be <= %s.", n, a1);
	}

	/** Predicate: %s must be equal to %s. Available in {@link P}, {@link Is}, {@link Be}, {@link Does}.*/
	public static boolean equal(long n, long a1) {
		return n == a1;
	}

	/** "Special" predicate: %s must be equal to %s. Available in {@link P}, {@link Is}, {@link Be}, {@link Does}.*/
	public static String equal$(long n, long a1) {
		return (n == a1) ? null : String.format("%s must be equal to %s.", n, a1);
	}

	/** Predicate: %s must NOT be equal to %s. Available in {@link P}, {@link Is}, {@link Be}, {@link Does}.*/
	public static boolean notEqual(long n, long a1) {
		return n != a1;
	}

	/** "Special" predicate: %s must NOT be equal to %s. Available in {@link P}, {@link Is}, {@link Be}, {@link Does}.*/
	public static String notEqual$(long n, long a1) {
		return (n != a1) ? null : String.format("%s must NOT be equal to %s.", n, a1);
	}

	/** Predicate: %s must be > %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean gt(long n, long a1) {
		return n > a1;
	}

	/** "Special" predicate: %s must be > %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String gt$(long n, long a1) {
		return (n > a1) ? null : String.format("%s must be > %s.", n, a1);
	}

	/** Predicate: %s must be < %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean lt(long n, long a1) {
		return n < a1;
	}

	/** "Special" predicate: %s must be < %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String lt$(long n, long a1) {
		return (n < a1) ? null : String.format("%s must be < %s.", n, a1);
	}

	/** Predicate: %s must be >= %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean gtEq(long n, long a1) {
		return n >= a1;
	}

	/** "Special" predicate: %s must be >= %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String gtEq$(long n, long a1) {
		return (n >= a1) ? null : String.format("%s must be >= %s.", n, a1);
	}

	/** Predicate: %s must be <= %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean ltEq(long n, long a1) {
		return n <= a1;
	}

	/** "Special" predicate: %s must be <= %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String ltEq$(long n, long a1) {
		return (n <= a1) ? null : String.format("%s must be <= %s.", n, a1);
	}

	/** Predicate: %s must be equal to %s. Available in {@link P}, {@link Is}, {@link Be}, {@link Does}.*/
	public static boolean equal(float n, float a1) {
		return n == a1;
	}

	/** "Special" predicate: %s must be equal to %s. Available in {@link P}, {@link Is}, {@link Be}, {@link Does}.*/
	public static String equal$(float n, float a1) {
		return (n == a1) ? null : String.format("%s must be equal to %s.", n, a1);
	}

	/** Predicate: %s must NOT be equal to %s. Available in {@link P}, {@link Is}, {@link Be}, {@link Does}.*/
	public static boolean notEqual(float n, float a1) {
		return n != a1;
	}

	/** "Special" predicate: %s must NOT be equal to %s. Available in {@link P}, {@link Is}, {@link Be}, {@link Does}.*/
	public static String notEqual$(float n, float a1) {
		return (n != a1) ? null : String.format("%s must NOT be equal to %s.", n, a1);
	}

	/** Predicate: %s must be > %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean gt(float n, float a1) {
		return n > a1;
	}

	/** "Special" predicate: %s must be > %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String gt$(float n, float a1) {
		return (n > a1) ? null : String.format("%s must be > %s.", n, a1);
	}

	/** Predicate: %s must be < %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean lt(float n, float a1) {
		return n < a1;
	}

	/** "Special" predicate: %s must be < %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String lt$(float n, float a1) {
		return (n < a1) ? null : String.format("%s must be < %s.", n, a1);
	}

	/** Predicate: %s must be >= %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean gtEq(float n, float a1) {
		return n >= a1;
	}

	/** "Special" predicate: %s must be >= %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String gtEq$(float n, float a1) {
		return (n >= a1) ? null : String.format("%s must be >= %s.", n, a1);
	}

	/** Predicate: %s must be <= %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean ltEq(float n, float a1) {
		return n <= a1;
	}

	/** "Special" predicate: %s must be <= %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String ltEq$(float n, float a1) {
		return (n <= a1) ? null : String.format("%s must be <= %s.", n, a1);
	}

	/** Predicate: %s must be equal to %s. Available in {@link P}, {@link Is}, {@link Be}, {@link Does}.*/
	public static boolean equal(double n, double a1) {
		return n == a1;
	}

	/** "Special" predicate: %s must be equal to %s. Available in {@link P}, {@link Is}, {@link Be}, {@link Does}.*/
	public static String equal$(double n, double a1) {
		return (n == a1) ? null : String.format("%s must be equal to %s.", n, a1);
	}

	/** Predicate: %s must NOT be equal to %s. Available in {@link P}, {@link Is}, {@link Be}, {@link Does}.*/
	public static boolean notEqual(double n, double a1) {
		return n != a1;
	}

	/** "Special" predicate: %s must NOT be equal to %s. Available in {@link P}, {@link Is}, {@link Be}, {@link Does}.*/
	public static String notEqual$(double n, double a1) {
		return (n != a1) ? null : String.format("%s must NOT be equal to %s.", n, a1);
	}

	/** Predicate: %s must be > %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean gt(double n, double a1) {
		return n > a1;
	}

	/** "Special" predicate: %s must be > %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String gt$(double n, double a1) {
		return (n > a1) ? null : String.format("%s must be > %s.", n, a1);
	}

	/** Predicate: %s must be < %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean lt(double n, double a1) {
		return n < a1;
	}

	/** "Special" predicate: %s must be < %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String lt$(double n, double a1) {
		return (n < a1) ? null : String.format("%s must be < %s.", n, a1);
	}

	/** Predicate: %s must be >= %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean gtEq(double n, double a1) {
		return n >= a1;
	}

	/** "Special" predicate: %s must be >= %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String gtEq$(double n, double a1) {
		return (n >= a1) ? null : String.format("%s must be >= %s.", n, a1);
	}

	/** Predicate: %s must be <= %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean ltEq(double n, double a1) {
		return n <= a1;
	}

	/** "Special" predicate: %s must be <= %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String ltEq$(double n, double a1) {
		return (n <= a1) ? null : String.format("%s must be <= %s.", n, a1);
	}

	/** Predicate: %s must be equal to %s. Available in {@link P}, {@link Is}, {@link Be}, {@link Does}.*/
	public static boolean equal(char n, char a1) {
		return n == a1;
	}

	/** "Special" predicate: %s must be equal to %s. Available in {@link P}, {@link Is}, {@link Be}, {@link Does}.*/
	public static String equal$(char n, char a1) {
		return (n == a1) ? null : String.format("%s must be equal to %s.", n, a1);
	}

	/** Predicate: %s must NOT be equal to %s. Available in {@link P}, {@link Is}, {@link Be}, {@link Does}.*/
	public static boolean notEqual(char n, char a1) {
		return n != a1;
	}

	/** "Special" predicate: %s must NOT be equal to %s. Available in {@link P}, {@link Is}, {@link Be}, {@link Does}.*/
	public static String notEqual$(char n, char a1) {
		return (n != a1) ? null : String.format("%s must NOT be equal to %s.", n, a1);
	}

	/** Predicate: %s must be > %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean gt(char n, char a1) {
		return n > a1;
	}

	/** "Special" predicate: %s must be > %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String gt$(char n, char a1) {
		return (n > a1) ? null : String.format("%s must be > %s.", n, a1);
	}

	/** Predicate: %s must be < %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean lt(char n, char a1) {
		return n < a1;
	}

	/** "Special" predicate: %s must be < %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String lt$(char n, char a1) {
		return (n < a1) ? null : String.format("%s must be < %s.", n, a1);
	}

	/** Predicate: %s must be >= %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean gtEq(char n, char a1) {
		return n >= a1;
	}

	/** "Special" predicate: %s must be >= %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String gtEq$(char n, char a1) {
		return (n >= a1) ? null : String.format("%s must be >= %s.", n, a1);
	}

	/** Predicate: %s must be <= %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean ltEq(char n, char a1) {
		return n <= a1;
	}

	/** "Special" predicate: %s must be <= %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String ltEq$(char n, char a1) {
		return (n <= a1) ? null : String.format("%s must be <= %s.", n, a1);
	}

	/** Predicate: %0$s must be: %1$s < %0$s < %2$s Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean between(byte n, byte a1, byte a2) {
		return a1 < n && n < a2;
	}

	/** "Special" predicate: %0$s must be: %1$s < %0$s < %2$s Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String between$(byte n, byte a1, byte a2) {
		return (a1 < n && n < a2) ? null : String.format("%0$s must be: %1$s < %0$s < %2$s", n, a1, a2);
	}

	/** Predicate: %0$s must NOT be: %1$s < %0$s < %2$s Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean notBetween(byte n, byte a1, byte a2) {
		return !(a1 < n && n < a2);
	}

	/** "Special" predicate: %0$s must NOT be: %1$s < %0$s < %2$s Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String notBetween$(byte n, byte a1, byte a2) {
		return !(a1 < n && n < a2) ? null : String.format("%0$s must NOT be: %1$s < %0$s < %2$s", n, a1, a2);
	}

	/** Predicate: %0$s must be: %1$s <= %0$s <= %2$s Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean inRange(byte n, byte a1, byte a2) {
		return a1 <= n && n <= a2;
	}

	/** "Special" predicate: %0$s must be: %1$s <= %0$s <= %2$s Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String inRange$(byte n, byte a1, byte a2) {
		return (a1 <= n && n <= a2) ? null : String.format("%0$s must be: %1$s <= %0$s <= %2$s", n, a1, a2);
	}

	/** Predicate: %0$s must NOT be: %1$s <= %0$s <= %2$s Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean notInRange(byte n, byte a1, byte a2) {
		return !(a1 <= n && n <= a2);
	}

	/** "Special" predicate: %0$s must NOT be: %1$s <= %0$s <= %2$s Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String notInRange$(byte n, byte a1, byte a2) {
		return !(a1 <= n && n <= a2) ? null : String.format("%0$s must NOT be: %1$s <= %0$s <= %2$s", n, a1, a2);
	}

	/** Predicate: %0$s must be: %1$s < %0$s < %2$s Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean between(short n, short a1, short a2) {
		return a1 < n && n < a2;
	}

	/** "Special" predicate: %0$s must be: %1$s < %0$s < %2$s Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String between$(short n, short a1, short a2) {
		return (a1 < n && n < a2) ? null : String.format("%0$s must be: %1$s < %0$s < %2$s", n, a1, a2);
	}

	/** Predicate: %0$s must NOT be: %1$s < %0$s < %2$s Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean notBetween(short n, short a1, short a2) {
		return !(a1 < n && n < a2);
	}

	/** "Special" predicate: %0$s must NOT be: %1$s < %0$s < %2$s Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String notBetween$(short n, short a1, short a2) {
		return !(a1 < n && n < a2) ? null : String.format("%0$s must NOT be: %1$s < %0$s < %2$s", n, a1, a2);
	}

	/** Predicate: %0$s must be: %1$s <= %0$s <= %2$s Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean inRange(short n, short a1, short a2) {
		return a1 <= n && n <= a2;
	}

	/** "Special" predicate: %0$s must be: %1$s <= %0$s <= %2$s Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String inRange$(short n, short a1, short a2) {
		return (a1 <= n && n <= a2) ? null : String.format("%0$s must be: %1$s <= %0$s <= %2$s", n, a1, a2);
	}

	/** Predicate: %0$s must NOT be: %1$s <= %0$s <= %2$s Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean notInRange(short n, short a1, short a2) {
		return !(a1 <= n && n <= a2);
	}

	/** "Special" predicate: %0$s must NOT be: %1$s <= %0$s <= %2$s Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String notInRange$(short n, short a1, short a2) {
		return !(a1 <= n && n <= a2) ? null : String.format("%0$s must NOT be: %1$s <= %0$s <= %2$s", n, a1, a2);
	}

	/** Predicate: %0$s must be: %1$s < %0$s < %2$s Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean between(int n, int a1, int a2) {
		return a1 < n && n < a2;
	}

	/** "Special" predicate: %0$s must be: %1$s < %0$s < %2$s Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String between$(int n, int a1, int a2) {
		return (a1 < n && n < a2) ? null : String.format("%0$s must be: %1$s < %0$s < %2$s", n, a1, a2);
	}

	/** Predicate: %0$s must NOT be: %1$s < %0$s < %2$s Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean notBetween(int n, int a1, int a2) {
		return !(a1 < n && n < a2);
	}

	/** "Special" predicate: %0$s must NOT be: %1$s < %0$s < %2$s Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String notBetween$(int n, int a1, int a2) {
		return !(a1 < n && n < a2) ? null : String.format("%0$s must NOT be: %1$s < %0$s < %2$s", n, a1, a2);
	}

	/** Predicate: %0$s must be: %1$s <= %0$s <= %2$s Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean inRange(int n, int a1, int a2) {
		return a1 <= n && n <= a2;
	}

	/** "Special" predicate: %0$s must be: %1$s <= %0$s <= %2$s Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String inRange$(int n, int a1, int a2) {
		return (a1 <= n && n <= a2) ? null : String.format("%0$s must be: %1$s <= %0$s <= %2$s", n, a1, a2);
	}

	/** Predicate: %0$s must NOT be: %1$s <= %0$s <= %2$s Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean notInRange(int n, int a1, int a2) {
		return !(a1 <= n && n <= a2);
	}

	/** "Special" predicate: %0$s must NOT be: %1$s <= %0$s <= %2$s Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String notInRange$(int n, int a1, int a2) {
		return !(a1 <= n && n <= a2) ? null : String.format("%0$s must NOT be: %1$s <= %0$s <= %2$s", n, a1, a2);
	}

	/** Predicate: %0$s must be: %1$s < %0$s < %2$s Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean between(long n, long a1, long a2) {
		return a1 < n && n < a2;
	}

	/** "Special" predicate: %0$s must be: %1$s < %0$s < %2$s Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String between$(long n, long a1, long a2) {
		return (a1 < n && n < a2) ? null : String.format("%0$s must be: %1$s < %0$s < %2$s", n, a1, a2);
	}

	/** Predicate: %0$s must NOT be: %1$s < %0$s < %2$s Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean notBetween(long n, long a1, long a2) {
		return !(a1 < n && n < a2);
	}

	/** "Special" predicate: %0$s must NOT be: %1$s < %0$s < %2$s Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String notBetween$(long n, long a1, long a2) {
		return !(a1 < n && n < a2) ? null : String.format("%0$s must NOT be: %1$s < %0$s < %2$s", n, a1, a2);
	}

	/** Predicate: %0$s must be: %1$s <= %0$s <= %2$s Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean inRange(long n, long a1, long a2) {
		return a1 <= n && n <= a2;
	}

	/** "Special" predicate: %0$s must be: %1$s <= %0$s <= %2$s Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String inRange$(long n, long a1, long a2) {
		return (a1 <= n && n <= a2) ? null : String.format("%0$s must be: %1$s <= %0$s <= %2$s", n, a1, a2);
	}

	/** Predicate: %0$s must NOT be: %1$s <= %0$s <= %2$s Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean notInRange(long n, long a1, long a2) {
		return !(a1 <= n && n <= a2);
	}

	/** "Special" predicate: %0$s must NOT be: %1$s <= %0$s <= %2$s Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String notInRange$(long n, long a1, long a2) {
		return !(a1 <= n && n <= a2) ? null : String.format("%0$s must NOT be: %1$s <= %0$s <= %2$s", n, a1, a2);
	}

	/** Predicate: %0$s must be: %1$s < %0$s < %2$s Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean between(float n, float a1, float a2) {
		return a1 < n && n < a2;
	}

	/** "Special" predicate: %0$s must be: %1$s < %0$s < %2$s Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String between$(float n, float a1, float a2) {
		return (a1 < n && n < a2) ? null : String.format("%0$s must be: %1$s < %0$s < %2$s", n, a1, a2);
	}

	/** Predicate: %0$s must NOT be: %1$s < %0$s < %2$s Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean notBetween(float n, float a1, float a2) {
		return !(a1 < n && n < a2);
	}

	/** "Special" predicate: %0$s must NOT be: %1$s < %0$s < %2$s Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String notBetween$(float n, float a1, float a2) {
		return !(a1 < n && n < a2) ? null : String.format("%0$s must NOT be: %1$s < %0$s < %2$s", n, a1, a2);
	}

	/** Predicate: %0$s must be: %1$s <= %0$s <= %2$s Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean inRange(float n, float a1, float a2) {
		return a1 <= n && n <= a2;
	}

	/** "Special" predicate: %0$s must be: %1$s <= %0$s <= %2$s Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String inRange$(float n, float a1, float a2) {
		return (a1 <= n && n <= a2) ? null : String.format("%0$s must be: %1$s <= %0$s <= %2$s", n, a1, a2);
	}

	/** Predicate: %0$s must NOT be: %1$s <= %0$s <= %2$s Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean notInRange(float n, float a1, float a2) {
		return !(a1 <= n && n <= a2);
	}

	/** "Special" predicate: %0$s must NOT be: %1$s <= %0$s <= %2$s Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String notInRange$(float n, float a1, float a2) {
		return !(a1 <= n && n <= a2) ? null : String.format("%0$s must NOT be: %1$s <= %0$s <= %2$s", n, a1, a2);
	}

	/** Predicate: %0$s must be: %1$s < %0$s < %2$s Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean between(double n, double a1, double a2) {
		return a1 < n && n < a2;
	}

	/** "Special" predicate: %0$s must be: %1$s < %0$s < %2$s Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String between$(double n, double a1, double a2) {
		return (a1 < n && n < a2) ? null : String.format("%0$s must be: %1$s < %0$s < %2$s", n, a1, a2);
	}

	/** Predicate: %0$s must NOT be: %1$s < %0$s < %2$s Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean notBetween(double n, double a1, double a2) {
		return !(a1 < n && n < a2);
	}

	/** "Special" predicate: %0$s must NOT be: %1$s < %0$s < %2$s Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String notBetween$(double n, double a1, double a2) {
		return !(a1 < n && n < a2) ? null : String.format("%0$s must NOT be: %1$s < %0$s < %2$s", n, a1, a2);
	}

	/** Predicate: %0$s must be: %1$s <= %0$s <= %2$s Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean inRange(double n, double a1, double a2) {
		return a1 <= n && n <= a2;
	}

	/** "Special" predicate: %0$s must be: %1$s <= %0$s <= %2$s Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String inRange$(double n, double a1, double a2) {
		return (a1 <= n && n <= a2) ? null : String.format("%0$s must be: %1$s <= %0$s <= %2$s", n, a1, a2);
	}

	/** Predicate: %0$s must NOT be: %1$s <= %0$s <= %2$s Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean notInRange(double n, double a1, double a2) {
		return !(a1 <= n && n <= a2);
	}

	/** "Special" predicate: %0$s must NOT be: %1$s <= %0$s <= %2$s Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String notInRange$(double n, double a1, double a2) {
		return !(a1 <= n && n <= a2) ? null : String.format("%0$s must NOT be: %1$s <= %0$s <= %2$s", n, a1, a2);
	}

	/** Predicate: %0$s must be: %1$s < %0$s < %2$s Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean between(char n, char a1, char a2) {
		return a1 < n && n < a2;
	}

	/** "Special" predicate: %0$s must be: %1$s < %0$s < %2$s Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String between$(char n, char a1, char a2) {
		return (a1 < n && n < a2) ? null : String.format("%0$s must be: %1$s < %0$s < %2$s", n, a1, a2);
	}

	/** Predicate: %0$s must NOT be: %1$s < %0$s < %2$s Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean notBetween(char n, char a1, char a2) {
		return !(a1 < n && n < a2);
	}

	/** "Special" predicate: %0$s must NOT be: %1$s < %0$s < %2$s Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String notBetween$(char n, char a1, char a2) {
		return !(a1 < n && n < a2) ? null : String.format("%0$s must NOT be: %1$s < %0$s < %2$s", n, a1, a2);
	}

	/** Predicate: %0$s must be: %1$s <= %0$s <= %2$s Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean inRange(char n, char a1, char a2) {
		return a1 <= n && n <= a2;
	}

	/** "Special" predicate: %0$s must be: %1$s <= %0$s <= %2$s Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String inRange$(char n, char a1, char a2) {
		return (a1 <= n && n <= a2) ? null : String.format("%0$s must be: %1$s <= %0$s <= %2$s", n, a1, a2);
	}

	/** Predicate: %0$s must NOT be: %1$s <= %0$s <= %2$s Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean notInRange(char n, char a1, char a2) {
		return !(a1 <= n && n <= a2);
	}

	/** "Special" predicate: %0$s must NOT be: %1$s <= %0$s <= %2$s Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String notInRange$(char n, char a1, char a2) {
		return !(a1 <= n && n <= a2) ? null : String.format("%0$s must NOT be: %1$s <= %0$s <= %2$s", n, a1, a2);
	}

	/** Predicate: <%s> must be true. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean True(boolean v) {
		return v;
	}

	/** "Special" predicate: <%s> must be true. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String True$(boolean v) {
		return (v) ? null : String.format("<%s> must be true.", v);
	}

	/** Predicate: <%s> must be false. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean False(boolean v) {
		return !v;
	}

	/** "Special" predicate: <%s> must be false. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String False$(boolean v) {
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
	public static String ofLength$(@Nonnull boolean[] array, int i) {
		Null.nonNullArg(array, "array");
		return (Array.getLength(array) == i) ? null : String.format("Array <%s> must be of size %s.", array, i);
	}

	/** Predicate: Array <%s> must NOT be of size %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean notOfLength(@Nonnull boolean[] array, int i) {
		Null.nonNullArg(array, "array");
		return !(Array.getLength(array) == i);
	}

	/** "Special" predicate: Array <%s> must NOT be of size %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String notOfLength$(@Nonnull boolean[] array, int i) {
		Null.nonNullArg(array, "array");
		return !(Array.getLength(array) == i) ? null : String.format("Array <%s> must NOT be of size %s.", array, i);
	}

	/** Predicate: Array <%s> must be empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean empty(@Nonnull boolean[] array) {
		Null.nonNullArg(array, "array");
		return Array.getLength(array) == 0;
	}

	/** "Special" predicate: Array <%s> must be empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String empty$(@Nonnull boolean[] array) {
		Null.nonNullArg(array, "array");
		return (Array.getLength(array) == 0) ? null : String.format("Array <%s> must be empty.", array);
	}

	/** Predicate: Array <%s> must NOT be empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean notEmpty(@Nonnull boolean[] array) {
		Null.nonNullArg(array, "array");
		return !(Array.getLength(array) == 0);
	}

	/** "Special" predicate: Array <%s> must NOT be empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String notEmpty$(@Nonnull boolean[] array) {
		Null.nonNullArg(array, "array");
		return !(Array.getLength(array) == 0) ? null : String.format("Array <%s> must NOT be empty.", array);
	}

	/** Predicate: Array <%s> must be null or empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean nullOrEmpty(@Nullable boolean[] array) {
		return array == null || Array.getLength(array) == 0;
	}

	/** "Special" predicate: Array <%s> must be null or empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String nullOrEmpty$(@Nullable boolean[] array) {
		return (array == null || Array.getLength(array) == 0) ? null : String.format("Array <%s> must be null or empty.", array);
	}

	/** Predicate: Array <%s> must NOT be null or empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean notNullNotEmpty(@Nullable boolean[] array) {
		return !(array == null || Array.getLength(array) == 0);
	}

	/** "Special" predicate: Array <%s> must NOT be null or empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String notNullNotEmpty$(@Nullable boolean[] array) {
		return !(array == null || Array.getLength(array) == 0) ? null : String.format("Array <%s> must NOT be null or empty.", array);
	}

	/** Predicate: Array <%s> must be exactly of size 1 (singleton). Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean singleton(@Nonnull boolean[] array) {
		Null.nonNullArg(array, "array");
		return Array.getLength(array) == 1;
	}

	/** "Special" predicate: Array <%s> must be exactly of size 1 (singleton). Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String singleton$(@Nonnull boolean[] array) {
		Null.nonNullArg(array, "array");
		return (Array.getLength(array) == 1) ? null : String.format("Array <%s> must be exactly of size 1 (singleton).", array);
	}

	/** Predicate: Array <%s> must NOT be exactly of size 1 (singleton). Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean notSingleton(@Nonnull boolean[] array) {
		Null.nonNullArg(array, "array");
		return !(Array.getLength(array) == 1);
	}

	/** "Special" predicate: Array <%s> must NOT be exactly of size 1 (singleton). Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String notSingleton$(@Nonnull boolean[] array) {
		Null.nonNullArg(array, "array");
		return !(Array.getLength(array) == 1) ? null : String.format("Array <%s> must NOT be exactly of size 1 (singleton).", array);
	}

	/** Predicate: Array <%s> must be of size %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean ofLength(@Nonnull byte[] array, int i) {
		Null.nonNullArg(array, "array");
		return Array.getLength(array) == i;
	}

	/** "Special" predicate: Array <%s> must be of size %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String ofLength$(@Nonnull byte[] array, int i) {
		Null.nonNullArg(array, "array");
		return (Array.getLength(array) == i) ? null : String.format("Array <%s> must be of size %s.", array, i);
	}

	/** Predicate: Array <%s> must NOT be of size %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean notOfLength(@Nonnull byte[] array, int i) {
		Null.nonNullArg(array, "array");
		return !(Array.getLength(array) == i);
	}

	/** "Special" predicate: Array <%s> must NOT be of size %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String notOfLength$(@Nonnull byte[] array, int i) {
		Null.nonNullArg(array, "array");
		return !(Array.getLength(array) == i) ? null : String.format("Array <%s> must NOT be of size %s.", array, i);
	}

	/** Predicate: Array <%s> must be empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean empty(@Nonnull byte[] array) {
		Null.nonNullArg(array, "array");
		return Array.getLength(array) == 0;
	}

	/** "Special" predicate: Array <%s> must be empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String empty$(@Nonnull byte[] array) {
		Null.nonNullArg(array, "array");
		return (Array.getLength(array) == 0) ? null : String.format("Array <%s> must be empty.", array);
	}

	/** Predicate: Array <%s> must NOT be empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean notEmpty(@Nonnull byte[] array) {
		Null.nonNullArg(array, "array");
		return !(Array.getLength(array) == 0);
	}

	/** "Special" predicate: Array <%s> must NOT be empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String notEmpty$(@Nonnull byte[] array) {
		Null.nonNullArg(array, "array");
		return !(Array.getLength(array) == 0) ? null : String.format("Array <%s> must NOT be empty.", array);
	}

	/** Predicate: Array <%s> must be null or empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean nullOrEmpty(@Nullable byte[] array) {
		return array == null || Array.getLength(array) == 0;
	}

	/** "Special" predicate: Array <%s> must be null or empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String nullOrEmpty$(@Nullable byte[] array) {
		return (array == null || Array.getLength(array) == 0) ? null : String.format("Array <%s> must be null or empty.", array);
	}

	/** Predicate: Array <%s> must NOT be null or empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean notNullNotEmpty(@Nullable byte[] array) {
		return !(array == null || Array.getLength(array) == 0);
	}

	/** "Special" predicate: Array <%s> must NOT be null or empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String notNullNotEmpty$(@Nullable byte[] array) {
		return !(array == null || Array.getLength(array) == 0) ? null : String.format("Array <%s> must NOT be null or empty.", array);
	}

	/** Predicate: Array <%s> must be exactly of size 1 (singleton). Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean singleton(@Nonnull byte[] array) {
		Null.nonNullArg(array, "array");
		return Array.getLength(array) == 1;
	}

	/** "Special" predicate: Array <%s> must be exactly of size 1 (singleton). Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String singleton$(@Nonnull byte[] array) {
		Null.nonNullArg(array, "array");
		return (Array.getLength(array) == 1) ? null : String.format("Array <%s> must be exactly of size 1 (singleton).", array);
	}

	/** Predicate: Array <%s> must NOT be exactly of size 1 (singleton). Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean notSingleton(@Nonnull byte[] array) {
		Null.nonNullArg(array, "array");
		return !(Array.getLength(array) == 1);
	}

	/** "Special" predicate: Array <%s> must NOT be exactly of size 1 (singleton). Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String notSingleton$(@Nonnull byte[] array) {
		Null.nonNullArg(array, "array");
		return !(Array.getLength(array) == 1) ? null : String.format("Array <%s> must NOT be exactly of size 1 (singleton).", array);
	}

	/** Predicate: Array <%s> must be of size %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean ofLength(@Nonnull double[] array, int i) {
		Null.nonNullArg(array, "array");
		return Array.getLength(array) == i;
	}

	/** "Special" predicate: Array <%s> must be of size %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String ofLength$(@Nonnull double[] array, int i) {
		Null.nonNullArg(array, "array");
		return (Array.getLength(array) == i) ? null : String.format("Array <%s> must be of size %s.", array, i);
	}

	/** Predicate: Array <%s> must NOT be of size %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean notOfLength(@Nonnull double[] array, int i) {
		Null.nonNullArg(array, "array");
		return !(Array.getLength(array) == i);
	}

	/** "Special" predicate: Array <%s> must NOT be of size %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String notOfLength$(@Nonnull double[] array, int i) {
		Null.nonNullArg(array, "array");
		return !(Array.getLength(array) == i) ? null : String.format("Array <%s> must NOT be of size %s.", array, i);
	}

	/** Predicate: Array <%s> must be empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean empty(@Nonnull double[] array) {
		Null.nonNullArg(array, "array");
		return Array.getLength(array) == 0;
	}

	/** "Special" predicate: Array <%s> must be empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String empty$(@Nonnull double[] array) {
		Null.nonNullArg(array, "array");
		return (Array.getLength(array) == 0) ? null : String.format("Array <%s> must be empty.", array);
	}

	/** Predicate: Array <%s> must NOT be empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean notEmpty(@Nonnull double[] array) {
		Null.nonNullArg(array, "array");
		return !(Array.getLength(array) == 0);
	}

	/** "Special" predicate: Array <%s> must NOT be empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String notEmpty$(@Nonnull double[] array) {
		Null.nonNullArg(array, "array");
		return !(Array.getLength(array) == 0) ? null : String.format("Array <%s> must NOT be empty.", array);
	}

	/** Predicate: Array <%s> must be null or empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean nullOrEmpty(@Nullable double[] array) {
		return array == null || Array.getLength(array) == 0;
	}

	/** "Special" predicate: Array <%s> must be null or empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String nullOrEmpty$(@Nullable double[] array) {
		return (array == null || Array.getLength(array) == 0) ? null : String.format("Array <%s> must be null or empty.", array);
	}

	/** Predicate: Array <%s> must NOT be null or empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean notNullNotEmpty(@Nullable double[] array) {
		return !(array == null || Array.getLength(array) == 0);
	}

	/** "Special" predicate: Array <%s> must NOT be null or empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String notNullNotEmpty$(@Nullable double[] array) {
		return !(array == null || Array.getLength(array) == 0) ? null : String.format("Array <%s> must NOT be null or empty.", array);
	}

	/** Predicate: Array <%s> must be exactly of size 1 (singleton). Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean singleton(@Nonnull double[] array) {
		Null.nonNullArg(array, "array");
		return Array.getLength(array) == 1;
	}

	/** "Special" predicate: Array <%s> must be exactly of size 1 (singleton). Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String singleton$(@Nonnull double[] array) {
		Null.nonNullArg(array, "array");
		return (Array.getLength(array) == 1) ? null : String.format("Array <%s> must be exactly of size 1 (singleton).", array);
	}

	/** Predicate: Array <%s> must NOT be exactly of size 1 (singleton). Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean notSingleton(@Nonnull double[] array) {
		Null.nonNullArg(array, "array");
		return !(Array.getLength(array) == 1);
	}

	/** "Special" predicate: Array <%s> must NOT be exactly of size 1 (singleton). Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String notSingleton$(@Nonnull double[] array) {
		Null.nonNullArg(array, "array");
		return !(Array.getLength(array) == 1) ? null : String.format("Array <%s> must NOT be exactly of size 1 (singleton).", array);
	}

	/** Predicate: Array <%s> must be of size %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean ofLength(@Nonnull char[] array, int i) {
		Null.nonNullArg(array, "array");
		return Array.getLength(array) == i;
	}

	/** "Special" predicate: Array <%s> must be of size %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String ofLength$(@Nonnull char[] array, int i) {
		Null.nonNullArg(array, "array");
		return (Array.getLength(array) == i) ? null : String.format("Array <%s> must be of size %s.", array, i);
	}

	/** Predicate: Array <%s> must NOT be of size %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean notOfLength(@Nonnull char[] array, int i) {
		Null.nonNullArg(array, "array");
		return !(Array.getLength(array) == i);
	}

	/** "Special" predicate: Array <%s> must NOT be of size %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String notOfLength$(@Nonnull char[] array, int i) {
		Null.nonNullArg(array, "array");
		return !(Array.getLength(array) == i) ? null : String.format("Array <%s> must NOT be of size %s.", array, i);
	}

	/** Predicate: Array <%s> must be empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean empty(@Nonnull char[] array) {
		Null.nonNullArg(array, "array");
		return Array.getLength(array) == 0;
	}

	/** "Special" predicate: Array <%s> must be empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String empty$(@Nonnull char[] array) {
		Null.nonNullArg(array, "array");
		return (Array.getLength(array) == 0) ? null : String.format("Array <%s> must be empty.", array);
	}

	/** Predicate: Array <%s> must NOT be empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean notEmpty(@Nonnull char[] array) {
		Null.nonNullArg(array, "array");
		return !(Array.getLength(array) == 0);
	}

	/** "Special" predicate: Array <%s> must NOT be empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String notEmpty$(@Nonnull char[] array) {
		Null.nonNullArg(array, "array");
		return !(Array.getLength(array) == 0) ? null : String.format("Array <%s> must NOT be empty.", array);
	}

	/** Predicate: Array <%s> must be null or empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean nullOrEmpty(@Nullable char[] array) {
		return array == null || Array.getLength(array) == 0;
	}

	/** "Special" predicate: Array <%s> must be null or empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String nullOrEmpty$(@Nullable char[] array) {
		return (array == null || Array.getLength(array) == 0) ? null : String.format("Array <%s> must be null or empty.", array);
	}

	/** Predicate: Array <%s> must NOT be null or empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean notNullNotEmpty(@Nullable char[] array) {
		return !(array == null || Array.getLength(array) == 0);
	}

	/** "Special" predicate: Array <%s> must NOT be null or empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String notNullNotEmpty$(@Nullable char[] array) {
		return !(array == null || Array.getLength(array) == 0) ? null : String.format("Array <%s> must NOT be null or empty.", array);
	}

	/** Predicate: Array <%s> must be exactly of size 1 (singleton). Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean singleton(@Nonnull char[] array) {
		Null.nonNullArg(array, "array");
		return Array.getLength(array) == 1;
	}

	/** "Special" predicate: Array <%s> must be exactly of size 1 (singleton). Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String singleton$(@Nonnull char[] array) {
		Null.nonNullArg(array, "array");
		return (Array.getLength(array) == 1) ? null : String.format("Array <%s> must be exactly of size 1 (singleton).", array);
	}

	/** Predicate: Array <%s> must NOT be exactly of size 1 (singleton). Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean notSingleton(@Nonnull char[] array) {
		Null.nonNullArg(array, "array");
		return !(Array.getLength(array) == 1);
	}

	/** "Special" predicate: Array <%s> must NOT be exactly of size 1 (singleton). Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String notSingleton$(@Nonnull char[] array) {
		Null.nonNullArg(array, "array");
		return !(Array.getLength(array) == 1) ? null : String.format("Array <%s> must NOT be exactly of size 1 (singleton).", array);
	}

	/** Predicate: Array <%s> must be of size %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean ofLength(@Nonnull short[] array, int i) {
		Null.nonNullArg(array, "array");
		return Array.getLength(array) == i;
	}

	/** "Special" predicate: Array <%s> must be of size %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String ofLength$(@Nonnull short[] array, int i) {
		Null.nonNullArg(array, "array");
		return (Array.getLength(array) == i) ? null : String.format("Array <%s> must be of size %s.", array, i);
	}

	/** Predicate: Array <%s> must NOT be of size %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean notOfLength(@Nonnull short[] array, int i) {
		Null.nonNullArg(array, "array");
		return !(Array.getLength(array) == i);
	}

	/** "Special" predicate: Array <%s> must NOT be of size %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String notOfLength$(@Nonnull short[] array, int i) {
		Null.nonNullArg(array, "array");
		return !(Array.getLength(array) == i) ? null : String.format("Array <%s> must NOT be of size %s.", array, i);
	}

	/** Predicate: Array <%s> must be empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean empty(@Nonnull short[] array) {
		Null.nonNullArg(array, "array");
		return Array.getLength(array) == 0;
	}

	/** "Special" predicate: Array <%s> must be empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String empty$(@Nonnull short[] array) {
		Null.nonNullArg(array, "array");
		return (Array.getLength(array) == 0) ? null : String.format("Array <%s> must be empty.", array);
	}

	/** Predicate: Array <%s> must NOT be empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean notEmpty(@Nonnull short[] array) {
		Null.nonNullArg(array, "array");
		return !(Array.getLength(array) == 0);
	}

	/** "Special" predicate: Array <%s> must NOT be empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String notEmpty$(@Nonnull short[] array) {
		Null.nonNullArg(array, "array");
		return !(Array.getLength(array) == 0) ? null : String.format("Array <%s> must NOT be empty.", array);
	}

	/** Predicate: Array <%s> must be null or empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean nullOrEmpty(@Nullable short[] array) {
		return array == null || Array.getLength(array) == 0;
	}

	/** "Special" predicate: Array <%s> must be null or empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String nullOrEmpty$(@Nullable short[] array) {
		return (array == null || Array.getLength(array) == 0) ? null : String.format("Array <%s> must be null or empty.", array);
	}

	/** Predicate: Array <%s> must NOT be null or empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean notNullNotEmpty(@Nullable short[] array) {
		return !(array == null || Array.getLength(array) == 0);
	}

	/** "Special" predicate: Array <%s> must NOT be null or empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String notNullNotEmpty$(@Nullable short[] array) {
		return !(array == null || Array.getLength(array) == 0) ? null : String.format("Array <%s> must NOT be null or empty.", array);
	}

	/** Predicate: Array <%s> must be exactly of size 1 (singleton). Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean singleton(@Nonnull short[] array) {
		Null.nonNullArg(array, "array");
		return Array.getLength(array) == 1;
	}

	/** "Special" predicate: Array <%s> must be exactly of size 1 (singleton). Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String singleton$(@Nonnull short[] array) {
		Null.nonNullArg(array, "array");
		return (Array.getLength(array) == 1) ? null : String.format("Array <%s> must be exactly of size 1 (singleton).", array);
	}

	/** Predicate: Array <%s> must NOT be exactly of size 1 (singleton). Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean notSingleton(@Nonnull short[] array) {
		Null.nonNullArg(array, "array");
		return !(Array.getLength(array) == 1);
	}

	/** "Special" predicate: Array <%s> must NOT be exactly of size 1 (singleton). Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String notSingleton$(@Nonnull short[] array) {
		Null.nonNullArg(array, "array");
		return !(Array.getLength(array) == 1) ? null : String.format("Array <%s> must NOT be exactly of size 1 (singleton).", array);
	}

	/** Predicate: Array <%s> must be of size %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean ofLength(@Nonnull float[] array, int i) {
		Null.nonNullArg(array, "array");
		return Array.getLength(array) == i;
	}

	/** "Special" predicate: Array <%s> must be of size %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String ofLength$(@Nonnull float[] array, int i) {
		Null.nonNullArg(array, "array");
		return (Array.getLength(array) == i) ? null : String.format("Array <%s> must be of size %s.", array, i);
	}

	/** Predicate: Array <%s> must NOT be of size %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean notOfLength(@Nonnull float[] array, int i) {
		Null.nonNullArg(array, "array");
		return !(Array.getLength(array) == i);
	}

	/** "Special" predicate: Array <%s> must NOT be of size %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String notOfLength$(@Nonnull float[] array, int i) {
		Null.nonNullArg(array, "array");
		return !(Array.getLength(array) == i) ? null : String.format("Array <%s> must NOT be of size %s.", array, i);
	}

	/** Predicate: Array <%s> must be empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean empty(@Nonnull float[] array) {
		Null.nonNullArg(array, "array");
		return Array.getLength(array) == 0;
	}

	/** "Special" predicate: Array <%s> must be empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String empty$(@Nonnull float[] array) {
		Null.nonNullArg(array, "array");
		return (Array.getLength(array) == 0) ? null : String.format("Array <%s> must be empty.", array);
	}

	/** Predicate: Array <%s> must NOT be empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean notEmpty(@Nonnull float[] array) {
		Null.nonNullArg(array, "array");
		return !(Array.getLength(array) == 0);
	}

	/** "Special" predicate: Array <%s> must NOT be empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String notEmpty$(@Nonnull float[] array) {
		Null.nonNullArg(array, "array");
		return !(Array.getLength(array) == 0) ? null : String.format("Array <%s> must NOT be empty.", array);
	}

	/** Predicate: Array <%s> must be null or empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean nullOrEmpty(@Nullable float[] array) {
		return array == null || Array.getLength(array) == 0;
	}

	/** "Special" predicate: Array <%s> must be null or empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String nullOrEmpty$(@Nullable float[] array) {
		return (array == null || Array.getLength(array) == 0) ? null : String.format("Array <%s> must be null or empty.", array);
	}

	/** Predicate: Array <%s> must NOT be null or empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean notNullNotEmpty(@Nullable float[] array) {
		return !(array == null || Array.getLength(array) == 0);
	}

	/** "Special" predicate: Array <%s> must NOT be null or empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String notNullNotEmpty$(@Nullable float[] array) {
		return !(array == null || Array.getLength(array) == 0) ? null : String.format("Array <%s> must NOT be null or empty.", array);
	}

	/** Predicate: Array <%s> must be exactly of size 1 (singleton). Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean singleton(@Nonnull float[] array) {
		Null.nonNullArg(array, "array");
		return Array.getLength(array) == 1;
	}

	/** "Special" predicate: Array <%s> must be exactly of size 1 (singleton). Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String singleton$(@Nonnull float[] array) {
		Null.nonNullArg(array, "array");
		return (Array.getLength(array) == 1) ? null : String.format("Array <%s> must be exactly of size 1 (singleton).", array);
	}

	/** Predicate: Array <%s> must NOT be exactly of size 1 (singleton). Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean notSingleton(@Nonnull float[] array) {
		Null.nonNullArg(array, "array");
		return !(Array.getLength(array) == 1);
	}

	/** "Special" predicate: Array <%s> must NOT be exactly of size 1 (singleton). Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String notSingleton$(@Nonnull float[] array) {
		Null.nonNullArg(array, "array");
		return !(Array.getLength(array) == 1) ? null : String.format("Array <%s> must NOT be exactly of size 1 (singleton).", array);
	}

	/** Predicate: Array <%s> must be of size %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean ofLength(@Nonnull int[] array, int i) {
		Null.nonNullArg(array, "array");
		return Array.getLength(array) == i;
	}

	/** "Special" predicate: Array <%s> must be of size %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String ofLength$(@Nonnull int[] array, int i) {
		Null.nonNullArg(array, "array");
		return (Array.getLength(array) == i) ? null : String.format("Array <%s> must be of size %s.", array, i);
	}

	/** Predicate: Array <%s> must NOT be of size %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean notOfLength(@Nonnull int[] array, int i) {
		Null.nonNullArg(array, "array");
		return !(Array.getLength(array) == i);
	}

	/** "Special" predicate: Array <%s> must NOT be of size %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String notOfLength$(@Nonnull int[] array, int i) {
		Null.nonNullArg(array, "array");
		return !(Array.getLength(array) == i) ? null : String.format("Array <%s> must NOT be of size %s.", array, i);
	}

	/** Predicate: Array <%s> must be empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean empty(@Nonnull int[] array) {
		Null.nonNullArg(array, "array");
		return Array.getLength(array) == 0;
	}

	/** "Special" predicate: Array <%s> must be empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String empty$(@Nonnull int[] array) {
		Null.nonNullArg(array, "array");
		return (Array.getLength(array) == 0) ? null : String.format("Array <%s> must be empty.", array);
	}

	/** Predicate: Array <%s> must NOT be empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean notEmpty(@Nonnull int[] array) {
		Null.nonNullArg(array, "array");
		return !(Array.getLength(array) == 0);
	}

	/** "Special" predicate: Array <%s> must NOT be empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String notEmpty$(@Nonnull int[] array) {
		Null.nonNullArg(array, "array");
		return !(Array.getLength(array) == 0) ? null : String.format("Array <%s> must NOT be empty.", array);
	}

	/** Predicate: Array <%s> must be null or empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean nullOrEmpty(@Nullable int[] array) {
		return array == null || Array.getLength(array) == 0;
	}

	/** "Special" predicate: Array <%s> must be null or empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String nullOrEmpty$(@Nullable int[] array) {
		return (array == null || Array.getLength(array) == 0) ? null : String.format("Array <%s> must be null or empty.", array);
	}

	/** Predicate: Array <%s> must NOT be null or empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean notNullNotEmpty(@Nullable int[] array) {
		return !(array == null || Array.getLength(array) == 0);
	}

	/** "Special" predicate: Array <%s> must NOT be null or empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String notNullNotEmpty$(@Nullable int[] array) {
		return !(array == null || Array.getLength(array) == 0) ? null : String.format("Array <%s> must NOT be null or empty.", array);
	}

	/** Predicate: Array <%s> must be exactly of size 1 (singleton). Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean singleton(@Nonnull int[] array) {
		Null.nonNullArg(array, "array");
		return Array.getLength(array) == 1;
	}

	/** "Special" predicate: Array <%s> must be exactly of size 1 (singleton). Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String singleton$(@Nonnull int[] array) {
		Null.nonNullArg(array, "array");
		return (Array.getLength(array) == 1) ? null : String.format("Array <%s> must be exactly of size 1 (singleton).", array);
	}

	/** Predicate: Array <%s> must NOT be exactly of size 1 (singleton). Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean notSingleton(@Nonnull int[] array) {
		Null.nonNullArg(array, "array");
		return !(Array.getLength(array) == 1);
	}

	/** "Special" predicate: Array <%s> must NOT be exactly of size 1 (singleton). Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String notSingleton$(@Nonnull int[] array) {
		Null.nonNullArg(array, "array");
		return !(Array.getLength(array) == 1) ? null : String.format("Array <%s> must NOT be exactly of size 1 (singleton).", array);
	}

	/** Predicate: Array <%s> must be of size %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean ofLength(@Nonnull long[] array, int i) {
		Null.nonNullArg(array, "array");
		return Array.getLength(array) == i;
	}

	/** "Special" predicate: Array <%s> must be of size %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String ofLength$(@Nonnull long[] array, int i) {
		Null.nonNullArg(array, "array");
		return (Array.getLength(array) == i) ? null : String.format("Array <%s> must be of size %s.", array, i);
	}

	/** Predicate: Array <%s> must NOT be of size %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean notOfLength(@Nonnull long[] array, int i) {
		Null.nonNullArg(array, "array");
		return !(Array.getLength(array) == i);
	}

	/** "Special" predicate: Array <%s> must NOT be of size %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String notOfLength$(@Nonnull long[] array, int i) {
		Null.nonNullArg(array, "array");
		return !(Array.getLength(array) == i) ? null : String.format("Array <%s> must NOT be of size %s.", array, i);
	}

	/** Predicate: Array <%s> must be empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean empty(@Nonnull long[] array) {
		Null.nonNullArg(array, "array");
		return Array.getLength(array) == 0;
	}

	/** "Special" predicate: Array <%s> must be empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String empty$(@Nonnull long[] array) {
		Null.nonNullArg(array, "array");
		return (Array.getLength(array) == 0) ? null : String.format("Array <%s> must be empty.", array);
	}

	/** Predicate: Array <%s> must NOT be empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean notEmpty(@Nonnull long[] array) {
		Null.nonNullArg(array, "array");
		return !(Array.getLength(array) == 0);
	}

	/** "Special" predicate: Array <%s> must NOT be empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String notEmpty$(@Nonnull long[] array) {
		Null.nonNullArg(array, "array");
		return !(Array.getLength(array) == 0) ? null : String.format("Array <%s> must NOT be empty.", array);
	}

	/** Predicate: Array <%s> must be null or empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean nullOrEmpty(@Nullable long[] array) {
		return array == null || Array.getLength(array) == 0;
	}

	/** "Special" predicate: Array <%s> must be null or empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String nullOrEmpty$(@Nullable long[] array) {
		return (array == null || Array.getLength(array) == 0) ? null : String.format("Array <%s> must be null or empty.", array);
	}

	/** Predicate: Array <%s> must NOT be null or empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean notNullNotEmpty(@Nullable long[] array) {
		return !(array == null || Array.getLength(array) == 0);
	}

	/** "Special" predicate: Array <%s> must NOT be null or empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String notNullNotEmpty$(@Nullable long[] array) {
		return !(array == null || Array.getLength(array) == 0) ? null : String.format("Array <%s> must NOT be null or empty.", array);
	}

	/** Predicate: Array <%s> must be exactly of size 1 (singleton). Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean singleton(@Nonnull long[] array) {
		Null.nonNullArg(array, "array");
		return Array.getLength(array) == 1;
	}

	/** "Special" predicate: Array <%s> must be exactly of size 1 (singleton). Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String singleton$(@Nonnull long[] array) {
		Null.nonNullArg(array, "array");
		return (Array.getLength(array) == 1) ? null : String.format("Array <%s> must be exactly of size 1 (singleton).", array);
	}

	/** Predicate: Array <%s> must NOT be exactly of size 1 (singleton). Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean notSingleton(@Nonnull long[] array) {
		Null.nonNullArg(array, "array");
		return !(Array.getLength(array) == 1);
	}

	/** "Special" predicate: Array <%s> must NOT be exactly of size 1 (singleton). Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String notSingleton$(@Nonnull long[] array) {
		Null.nonNullArg(array, "array");
		return !(Array.getLength(array) == 1) ? null : String.format("Array <%s> must NOT be exactly of size 1 (singleton).", array);
	}

	/** Predicate: Array <%s> must be of size %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static <T> boolean ofLength(@Nonnull T[] array, int i) {
		Null.nonNullArg(array, "array");
		return Array.getLength(array) == i;
	}

	/** "Special" predicate: Array <%s> must be of size %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static <T> String ofLength$(@Nonnull T[] array, int i) {
		Null.nonNullArg(array, "array");
		return (Array.getLength(array) == i) ? null : String.format("Array <%s> must be of size %s.", array, i);
	}

	/** Predicate: Array <%s> must NOT be of size %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static <T> boolean notOfLength(@Nonnull T[] array, int i) {
		Null.nonNullArg(array, "array");
		return !(Array.getLength(array) == i);
	}

	/** "Special" predicate: Array <%s> must NOT be of size %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static <T> String notOfLength$(@Nonnull T[] array, int i) {
		Null.nonNullArg(array, "array");
		return !(Array.getLength(array) == i) ? null : String.format("Array <%s> must NOT be of size %s.", array, i);
	}

	/** Predicate: Array <%s> must be empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static <T> boolean empty(@Nonnull T[] array) {
		Null.nonNullArg(array, "array");
		return Array.getLength(array) == 0;
	}

	/** "Special" predicate: Array <%s> must be empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static <T> String empty$(@Nonnull T[] array) {
		Null.nonNullArg(array, "array");
		return (Array.getLength(array) == 0) ? null : String.format("Array <%s> must be empty.", array);
	}

	/** Predicate: Array <%s> must NOT be empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static <T> boolean notEmpty(@Nonnull T[] array) {
		Null.nonNullArg(array, "array");
		return !(Array.getLength(array) == 0);
	}

	/** "Special" predicate: Array <%s> must NOT be empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static <T> String notEmpty$(@Nonnull T[] array) {
		Null.nonNullArg(array, "array");
		return !(Array.getLength(array) == 0) ? null : String.format("Array <%s> must NOT be empty.", array);
	}

	/** Predicate: Array <%s> must be null or empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static <T> boolean nullOrEmpty(@Nullable T[] array) {
		return array == null || Array.getLength(array) == 0;
	}

	/** "Special" predicate: Array <%s> must be null or empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static <T> String nullOrEmpty$(@Nullable T[] array) {
		return (array == null || Array.getLength(array) == 0) ? null : String.format("Array <%s> must be null or empty.", array);
	}

	/** Predicate: Array <%s> must NOT be null or empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static <T> boolean notNullNotEmpty(@Nullable T[] array) {
		return !(array == null || Array.getLength(array) == 0);
	}

	/** "Special" predicate: Array <%s> must NOT be null or empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static <T> String notNullNotEmpty$(@Nullable T[] array) {
		return !(array == null || Array.getLength(array) == 0) ? null : String.format("Array <%s> must NOT be null or empty.", array);
	}

	/** Predicate: Array <%s> must be exactly of size 1 (singleton). Available in {@link P}, {@link Is}, {@link Be}.*/
	public static <T> boolean singleton(@Nonnull T[] array) {
		Null.nonNullArg(array, "array");
		return Array.getLength(array) == 1;
	}

	/** "Special" predicate: Array <%s> must be exactly of size 1 (singleton). Available in {@link P}, {@link Is}, {@link Be}.*/
	public static <T> String singleton$(@Nonnull T[] array) {
		Null.nonNullArg(array, "array");
		return (Array.getLength(array) == 1) ? null : String.format("Array <%s> must be exactly of size 1 (singleton).", array);
	}

	/** Predicate: Array <%s> must NOT be exactly of size 1 (singleton). Available in {@link P}, {@link Is}, {@link Be}.*/
	public static <T> boolean notSingleton(@Nonnull T[] array) {
		Null.nonNullArg(array, "array");
		return !(Array.getLength(array) == 1);
	}

	/** "Special" predicate: Array <%s> must NOT be exactly of size 1 (singleton). Available in {@link P}, {@link Is}, {@link Be}.*/
	public static <T> String notSingleton$(@Nonnull T[] array) {
		Null.nonNullArg(array, "array");
		return !(Array.getLength(array) == 1) ? null : String.format("Array <%s> must NOT be exactly of size 1 (singleton).", array);
	}

	// </editor-fold>

	// <editor-fold desc="collections">

	/** Predicate: Collection <%s> must be of size %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static <T> boolean ofSize(@Nonnull Collection<T> collection, int i) {
		Null.nonNullArg(collection, "collection");
		return collection.size() == i;
	}

	/** "Special" predicate: Collection <%s> must be of size %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static <T> String ofSize$(@Nonnull Collection<T> collection, int i) {
		Null.nonNullArg(collection, "collection");
		return (collection.size() == i) ? null : String.format("Collection <%s> must be of size %s.", collection, i);
	}

	/** Predicate: Collection <%s> must NOT be of size %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static <T> boolean notOfSize(@Nonnull Collection<T> collection, int i) {
		Null.nonNullArg(collection, "collection");
		return !(collection.size() == i);
	}

	/** "Special" predicate: Collection <%s> must NOT be of size %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static <T> String notOfSize$(@Nonnull Collection<T> collection, int i) {
		Null.nonNullArg(collection, "collection");
		return !(collection.size() == i) ? null : String.format("Collection <%s> must NOT be of size %s.", collection, i);
	}

	/** Predicate: Map <%s> must be of size %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static <K, V> boolean ofSize(@Nonnull Map<K, V> map, int i) {
		Null.nonNullArg(map, "map");
		return map.size() == i;
	}

	/** "Special" predicate: Map <%s> must be of size %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static <K, V> String ofSize$(@Nonnull Map<K, V> map, int i) {
		Null.nonNullArg(map, "map");
		return (map.size() == i) ? null : String.format("Map <%s> must be of size %s.", map, i);
	}

	/** Predicate: Map <%s> must NOT be of size %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static <K, V> boolean notOfSize(@Nonnull Map<K, V> map, int i) {
		Null.nonNullArg(map, "map");
		return !(map.size() == i);
	}

	/** "Special" predicate: Map <%s> must NOT be of size %s. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static <K, V> String notOfSize$(@Nonnull Map<K, V> map, int i) {
		Null.nonNullArg(map, "map");
		return !(map.size() == i) ? null : String.format("Map <%s> must NOT be of size %s.", map, i);
	}

	/** Predicate: <%s> must be part of <%s> collection. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static <T> boolean partOf(T element, @Nonnull Collection<T> collection) {
		Null.nonNullArg(collection, "collection");
		return collection.contains(element);
	}

	/** "Special" predicate: <%s> must be part of <%s> collection. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static <T> String partOf$(T element, @Nonnull Collection<T> collection) {
		Null.nonNullArg(collection, "collection");
		return (collection.contains(element)) ? null : String.format("<%s> must be part of <%s> collection.", element, collection);
	}

	/** Predicate: <%s> must NOT be part of <%s> collection. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static <T> boolean notPartOf(T element, @Nonnull Collection<T> collection) {
		Null.nonNullArg(collection, "collection");
		return !(collection.contains(element));
	}

	/** "Special" predicate: <%s> must NOT be part of <%s> collection. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static <T> String notPartOf$(T element, @Nonnull Collection<T> collection) {
		Null.nonNullArg(collection, "collection");
		return !(collection.contains(element)) ? null : String.format("<%s> must NOT be part of <%s> collection.", element, collection);
	}

	/** Predicate: <%s> must be key in <%s> map. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static <K> boolean aKeyIn(K key, @Nonnull Map<K, ?> map) {
		Null.nonNullArg(map, "map");
		return map.containsKey(key);
	}

	/** "Special" predicate: <%s> must be key in <%s> map. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static <K> String aKeyIn$(K key, @Nonnull Map<K, ?> map) {
		Null.nonNullArg(map, "map");
		return (map.containsKey(key)) ? null : String.format("<%s> must be key in <%s> map.", key, map);
	}

	/** Predicate: <%s> must NOT be key in <%s> map. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static <K> boolean notAKeyIn(K key, @Nonnull Map<K, ?> map) {
		Null.nonNullArg(map, "map");
		return !(map.containsKey(key));
	}

	/** "Special" predicate: <%s> must NOT be key in <%s> map. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static <K> String notAKeyIn$(K key, @Nonnull Map<K, ?> map) {
		Null.nonNullArg(map, "map");
		return !(map.containsKey(key)) ? null : String.format("<%s> must NOT be key in <%s> map.", key, map);
	}

	/** Predicate: Collection <%s> must be empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static <T> boolean empty(@Nonnull Collection<T> collection) {
		Null.nonNullArg(collection, "collection");
		return collection.isEmpty();
	}

	/** "Special" predicate: Collection <%s> must be empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static <T> String empty$(@Nonnull Collection<T> collection) {
		Null.nonNullArg(collection, "collection");
		return (collection.isEmpty()) ? null : String.format("Collection <%s> must be empty.", collection);
	}

	/** Predicate: Collection <%s> must NOT be empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static <T> boolean notEmpty(@Nonnull Collection<T> collection) {
		Null.nonNullArg(collection, "collection");
		return !(collection.isEmpty());
	}

	/** "Special" predicate: Collection <%s> must NOT be empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static <T> String notEmpty$(@Nonnull Collection<T> collection) {
		Null.nonNullArg(collection, "collection");
		return !(collection.isEmpty()) ? null : String.format("Collection <%s> must NOT be empty.", collection);
	}

	/** Predicate: Collection <%s> must be empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static <T> boolean nullOrEmpty(@Nullable Collection<T> collection) {
		return collection == null || collection.isEmpty();
	}

	/** "Special" predicate: Collection <%s> must be empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static <T> String nullOrEmpty$(@Nullable Collection<T> collection) {
		return (collection == null || collection.isEmpty()) ? null : String.format("Collection <%s> must be empty.", collection);
	}

	/** Predicate: Collection <%s> must NOT be empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static <T> boolean notNullNorEmpty(@Nullable Collection<T> collection) {
		return !(collection == null || collection.isEmpty());
	}

	/** "Special" predicate: Collection <%s> must NOT be empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static <T> String notNullNorEmpty$(@Nullable Collection<T> collection) {
		return !(collection == null || collection.isEmpty()) ? null : String.format("Collection <%s> must NOT be empty.", collection);
	}

	/** Predicate: Collection <%s> must be exactly of size 1 (singleton).. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static <T> boolean singleton(@Nonnull Collection<T> collection) {
		Null.nonNullArg(collection, "collection");
		return collection.size() == 1;
	}

	/** "Special" predicate: Collection <%s> must be exactly of size 1 (singleton).. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static <T> String singleton$(@Nonnull Collection<T> collection) {
		Null.nonNullArg(collection, "collection");
		return (collection.size() == 1) ? null : String.format("Collection <%s> must be exactly of size 1 (singleton)..", collection);
	}

	/** Predicate: Collection <%s> must NOT be exactly of size 1 (singleton).. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static <T> boolean notSingleton(@Nonnull Collection<T> collection) {
		Null.nonNullArg(collection, "collection");
		return !(collection.size() == 1);
	}

	/** "Special" predicate: Collection <%s> must NOT be exactly of size 1 (singleton).. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static <T> String notSingleton$(@Nonnull Collection<T> collection) {
		Null.nonNullArg(collection, "collection");
		return !(collection.size() == 1) ? null : String.format("Collection <%s> must NOT be exactly of size 1 (singleton)..", collection);
	}

	/** Predicate: Map <%s> must be empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean empty(@Nonnull Map<?, ?> map) {
		Null.nonNullArg(map, "map");
		return map.isEmpty();
	}

	/** "Special" predicate: Map <%s> must be empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String empty$(@Nonnull Map<?, ?> map) {
		Null.nonNullArg(map, "map");
		return (map.isEmpty()) ? null : String.format("Map <%s> must be empty.", map);
	}

	/** Predicate: Map <%s> must NOT be empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean notEmpty(@Nonnull Map<?, ?> map) {
		Null.nonNullArg(map, "map");
		return !(map.isEmpty());
	}

	/** "Special" predicate: Map <%s> must NOT be empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String notEmpty$(@Nonnull Map<?, ?> map) {
		Null.nonNullArg(map, "map");
		return !(map.isEmpty()) ? null : String.format("Map <%s> must NOT be empty.", map);
	}

	/** Predicate: Collection <%s> must be empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean nullOrEmpty(@Nullable Map<?, ?> map) {
		return map == null || map.isEmpty();
	}

	/** "Special" predicate: Collection <%s> must be empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String nullOrEmpty$(@Nullable Map<?, ?> map) {
		return (map == null || map.isEmpty()) ? null : String.format("Collection <%s> must be empty.", map);
	}

	/** Predicate: Collection <%s> must NOT be empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean notNullNorEmpty(@Nullable Map<?, ?> map) {
		return !(map == null || map.isEmpty());
	}

	/** "Special" predicate: Collection <%s> must NOT be empty. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String notNullNorEmpty$(@Nullable Map<?, ?> map) {
		return !(map == null || map.isEmpty()) ? null : String.format("Collection <%s> must NOT be empty.", map);
	}

	/** Predicate: Collection <%s> must be exactly of size 1 (singleton).. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean singleton(@Nonnull Map<?, ?> map) {
		Null.nonNullArg(map, "map");
		return map.size() == 1;
	}

	/** "Special" predicate: Collection <%s> must be exactly of size 1 (singleton).. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String singleton$(@Nonnull Map<?, ?> map) {
		Null.nonNullArg(map, "map");
		return (map.size() == 1) ? null : String.format("Collection <%s> must be exactly of size 1 (singleton)..", map);
	}

	/** Predicate: Collection <%s> must NOT be exactly of size 1 (singleton).. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean notSingleton(@Nonnull Map<?, ?> map) {
		Null.nonNullArg(map, "map");
		return !(map.size() == 1);
	}

	/** "Special" predicate: Collection <%s> must NOT be exactly of size 1 (singleton).. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String notSingleton$(@Nonnull Map<?, ?> map) {
		Null.nonNullArg(map, "map");
		return !(map.size() == 1) ? null : String.format("Collection <%s> must NOT be exactly of size 1 (singleton)..", map);
	}

	// </editor-fold>

	// <editor-fold desc="object derivatives">

	/** Predicate: Object <%s> must be instance of <%s>. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean instanceOf(Object object, Class<?> clazz) {
		Null.nonNullArg(clazz, "clazz");
		return clazz.isInstance(object);
	}

	/** "Special" predicate: Object <%s> must be instance of <%s>. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String instanceOf$(Object object, Class<?> clazz) {
		Null.nonNullArg(clazz, "clazz");
		return (clazz.isInstance(object)) ? null : String.format("Object <%s> must be instance of <%s>.", object, clazz);
	}

	/** Predicate: Object <%s> must NOT be instance of <%s>. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean notInstanceOf(Object object, Class<?> clazz) {
		Null.nonNullArg(clazz, "clazz");
		return !(clazz.isInstance(object));
	}

	/** "Special" predicate: Object <%s> must NOT be instance of <%s>. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String notInstanceOf$(Object object, Class<?> clazz) {
		Null.nonNullArg(clazz, "clazz");
		return !(clazz.isInstance(object)) ? null : String.format("Object <%s> must NOT be instance of <%s>.", object, clazz);
	}

	/** Predicate: Class <%s> must ---NOT-- be specialization of <%s>. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean assignableFrom(Class<?> specialization, Class<?> clazz) {
		Null.nonNullArg(clazz, "clazz");
		return clazz.isAssignableFrom(specialization);
	}

	/** "Special" predicate: Class <%s> must ---NOT-- be specialization of <%s>. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String assignableFrom$(Class<?> specialization, Class<?> clazz) {
		Null.nonNullArg(clazz, "clazz");
		return (clazz.isAssignableFrom(specialization)) ? null : String.format("Class <%s> must ---NOT-- be specialization of <%s>.", specialization, clazz);
	}

	/** Predicate: Class <%s> must ---NOT-- be specialization of <%s>. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean notAssignableFrom(Class<?> specialization, Class<?> clazz) {
		Null.nonNullArg(clazz, "clazz");
		return !(clazz.isAssignableFrom(specialization));
	}

	/** "Special" predicate: Class <%s> must ---NOT-- be specialization of <%s>. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String notAssignableFrom$(Class<?> specialization, Class<?> clazz) {
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
	public static String runtime$(@Nonnull Throwable e) {
		Null.nonNullArg(e, "e");
		return (e instanceof RuntimeException) ? null : String.format("Exception <%s> must be instance of a RuntimeException.", e);
	}

	/** Predicate: Exception <%s> must NOT be instance of a RuntimeException. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean notRuntime(@Nonnull Throwable e) {
		Null.nonNullArg(e, "e");
		return !(e instanceof RuntimeException);
	}

	/** "Special" predicate: Exception <%s> must NOT be instance of a RuntimeException. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String notRuntime$(@Nonnull Throwable e) {
		Null.nonNullArg(e, "e");
		return !(e instanceof RuntimeException) ? null : String.format("Exception <%s> must NOT be instance of a RuntimeException.", e);
	}

	/** Predicate: Exception <%s> must have suppressed other exceptions. Available in {@link P}, {@link Is}, {@link Has}, {@link Have}.*/
	public static boolean suppressing(@Nonnull Throwable e) {
		Null.nonNullArg(e, "e");
		return e.getSuppressed().length > 0;
	}

	/** "Special" predicate: Exception <%s> must have suppressed other exceptions. Available in {@link P}, {@link Is}, {@link Has}, {@link Have}.*/
	public static String suppressing$(@Nonnull Throwable e) {
		Null.nonNullArg(e, "e");
		return (e.getSuppressed().length > 0) ? null : String.format("Exception <%s> must have suppressed other exceptions.", e);
	}

	/** Predicate: Exception <%s> must NOT have suppressed other exceptions. Available in {@link P}, {@link Is}, {@link Has}, {@link Have}.*/
	public static boolean notSuppressing(@Nonnull Throwable e) {
		Null.nonNullArg(e, "e");
		return !(e.getSuppressed().length > 0);
	}

	/** "Special" predicate: Exception <%s> must NOT have suppressed other exceptions. Available in {@link P}, {@link Is}, {@link Has}, {@link Have}.*/
	public static String notSuppressing$(@Nonnull Throwable e) {
		Null.nonNullArg(e, "e");
		return !(e.getSuppressed().length > 0) ? null : String.format("Exception <%s> must NOT have suppressed other exceptions.", e);
	}

	// </editor-fold>

}
