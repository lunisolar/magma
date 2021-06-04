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
public final class P implements FluentSyntax {
	// <editor-fold desc="no instance">
	private P() {
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
	public static boolean length(@Nonnull CharSequence s, int size) {
		Null.nonNullArg(s, "s");
		return size == s.length();
	}

	/** "Special" predicate: String <'%s'> must be <%d> characters long. Available in {@link P}, {@link Has}, {@link Have}, {@link Is}, {@link Be}.*/
	public static String lengthEx(@Nonnull CharSequence s, int size) {
		Null.nonNullArg(s, "s");
		return (size == s.length()) ? null : String.format("String <'%s'> must be <%d> characters long.", s, size);
	}

	/** Predicate: String <'%s'> must NOT be <%d> characters long. Available in {@link P}, {@link Has}, {@link Have}, {@link Is}, {@link Be}.*/
	public static boolean lengthOtherThan(@Nonnull CharSequence s, int size) {
		Null.nonNullArg(s, "s");
		return !(size == s.length());
	}

	/** "Special" predicate: String <'%s'> must NOT be <%d> characters long. Available in {@link P}, {@link Has}, {@link Have}, {@link Is}, {@link Be}.*/
	public static String lengthOtherThanEx(@Nonnull CharSequence s, int size) {
		Null.nonNullArg(s, "s");
		return !(size == s.length()) ? null : String.format("String <'%s'> must NOT be <%d> characters long.", s, size);
	}

	/** Predicate: String <'%s'> must start with <'%s'>. Available in {@link P}, {@link Does}.*/
	public static boolean startWith(@Nonnull String n, @Nonnull String a1) {
		Null.nonNullArg(n, "n");
		Null.nonNullArg(a1, "a1");
		return n.startsWith(a1);
	}

	/** "Special" predicate: String <'%s'> must start with <'%s'>. Available in {@link P}, {@link Does}.*/
	public static String startWithEx(@Nonnull String n, @Nonnull String a1) {
		Null.nonNullArg(n, "n");
		Null.nonNullArg(a1, "a1");
		return (n.startsWith(a1)) ? null : String.format("String <'%s'> must start with <'%s'>.", n, a1);
	}

	/** Predicate: String <'%s'> must NOT start with <'%s'>. Available in {@link P}, {@link Does}.*/
	public static boolean notStartWith(@Nonnull String n, @Nonnull String a1) {
		Null.nonNullArg(n, "n");
		Null.nonNullArg(a1, "a1");
		return !(n.startsWith(a1));
	}

	/** "Special" predicate: String <'%s'> must NOT start with <'%s'>. Available in {@link P}, {@link Does}.*/
	public static String notStartWithEx(@Nonnull String n, @Nonnull String a1) {
		Null.nonNullArg(n, "n");
		Null.nonNullArg(a1, "a1");
		return !(n.startsWith(a1)) ? null : String.format("String <'%s'> must NOT start with <'%s'>.", n, a1);
	}

	/** Predicate: String <'%s'> must end with <'%s'>. Available in {@link P}, {@link Does}.*/
	public static boolean endWith(@Nonnull String n, @Nonnull String a1) {
		Null.nonNullArg(n, "n");
		Null.nonNullArg(a1, "a1");
		return n.endsWith(a1);
	}

	/** "Special" predicate: String <'%s'> must end with <'%s'>. Available in {@link P}, {@link Does}.*/
	public static String endWithEx(@Nonnull String n, @Nonnull String a1) {
		Null.nonNullArg(n, "n");
		Null.nonNullArg(a1, "a1");
		return (n.endsWith(a1)) ? null : String.format("String <'%s'> must end with <'%s'>.", n, a1);
	}

	/** Predicate: String <'%s'> must NOT end with <'%s'>. Available in {@link P}, {@link Does}.*/
	public static boolean notEndWith(@Nonnull String n, @Nonnull String a1) {
		Null.nonNullArg(n, "n");
		Null.nonNullArg(a1, "a1");
		return !(n.endsWith(a1));
	}

	/** "Special" predicate: String <'%s'> must NOT end with <'%s'>. Available in {@link P}, {@link Does}.*/
	public static String notEndWithEx(@Nonnull String n, @Nonnull String a1) {
		Null.nonNullArg(n, "n");
		Null.nonNullArg(a1, "a1");
		return !(n.endsWith(a1)) ? null : String.format("String <'%s'> must NOT end with <'%s'>.", n, a1);
	}

	/** Predicate: String <'%s'> must contain string <'%s'>. But does not. Available in {@link P}, {@link Does}.*/
	public static boolean contain(@Nonnull String n, @Nonnull String a1) {
		Null.nonNullArg(n, "n");
		Null.nonNullArg(a1, "a1");
		return n.contains(a1);
	}

	/** "Special" predicate: String <'%s'> must contain string <'%s'>. But does not. Available in {@link P}, {@link Does}.*/
	public static String containEx(@Nonnull String n, @Nonnull String a1) {
		Null.nonNullArg(n, "n");
		Null.nonNullArg(a1, "a1");
		return (n.contains(a1)) ? null : String.format("String <'%s'> must contain string <'%s'>. But does not.", n, a1);
	}

	/** Predicate: String <'%s'> must NOT contain string <'%s'>. But does not. Available in {@link P}, {@link Does}.*/
	public static boolean notContain(@Nonnull String n, @Nonnull String a1) {
		Null.nonNullArg(n, "n");
		Null.nonNullArg(a1, "a1");
		return !(n.contains(a1));
	}

	/** "Special" predicate: String <'%s'> must NOT contain string <'%s'>. But does not. Available in {@link P}, {@link Does}.*/
	public static String notContainEx(@Nonnull String n, @Nonnull String a1) {
		Null.nonNullArg(n, "n");
		Null.nonNullArg(a1, "a1");
		return !(n.contains(a1)) ? null : String.format("String <'%s'> must NOT contain string <'%s'>. But does not.", n, a1);
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

	public static boolean and(boolean operand1, boolean operand2) {
		return operand1 && operand2;
	}

	public static boolean or(boolean operand1, boolean operand2) {
		return operand1 || operand2;
	}

	public static boolean xor(boolean operand1, boolean operand2) {
		return operand1 ^ operand2;
	}

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

	/** Predicate: Array <%s> must be of size %s. Available in {@link P}, {@link Has}, {@link Have}.*/
	public static boolean length(@Nonnull boolean[] array, int i) {
		Null.nonNullArg(array, "array");
		return Array.getLength(array) == i;
	}

	/** "Special" predicate: Array <%s> must be of size %s. Available in {@link P}, {@link Has}, {@link Have}.*/
	public static String lengthEx(@Nonnull boolean[] array, int i) {
		Null.nonNullArg(array, "array");
		return (Array.getLength(array) == i) ? null : String.format("Array <%s> must be of size %s.", array, i);
	}

	/** Predicate: Array <%s> must NOT be of size %s. Available in {@link P}, {@link Has}, {@link Have}.*/
	public static boolean lengthOtherThan(@Nonnull boolean[] array, int i) {
		Null.nonNullArg(array, "array");
		return !(Array.getLength(array) == i);
	}

	/** "Special" predicate: Array <%s> must NOT be of size %s. Available in {@link P}, {@link Has}, {@link Have}.*/
	public static String lengthOtherThanEx(@Nonnull boolean[] array, int i) {
		Null.nonNullArg(array, "array");
		return !(Array.getLength(array) == i) ? null : String.format("Array <%s> must NOT be of size %s.", array, i);
	}

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

	/** Predicate: Array <%s> must be equal to array <%s>. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean equal(boolean[] a1, boolean[] a2) {
		return Arrays.equals(a1, a2);
	}

	/** "Special" predicate: Array <%s> must be equal to array <%s>. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String equalEx(boolean[] a1, boolean[] a2) {
		return (Arrays.equals(a1, a2)) ? null : String.format("Array <%s> must be equal to array <%s>.", a1, a2);
	}

	/** Predicate: Array <%s> must NOT be equal to array <%s>. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean notEqual(boolean[] a1, boolean[] a2) {
		return !(Arrays.equals(a1, a2));
	}

	/** "Special" predicate: Array <%s> must NOT be equal to array <%s>. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String notEqualEx(boolean[] a1, boolean[] a2) {
		return !(Arrays.equals(a1, a2)) ? null : String.format("Array <%s> must NOT be equal to array <%s>.", a1, a2);
	}

	/** Predicate: Array <%s> must be of size %s. Available in {@link P}, {@link Has}, {@link Have}.*/
	public static boolean length(@Nonnull byte[] array, int i) {
		Null.nonNullArg(array, "array");
		return Array.getLength(array) == i;
	}

	/** "Special" predicate: Array <%s> must be of size %s. Available in {@link P}, {@link Has}, {@link Have}.*/
	public static String lengthEx(@Nonnull byte[] array, int i) {
		Null.nonNullArg(array, "array");
		return (Array.getLength(array) == i) ? null : String.format("Array <%s> must be of size %s.", array, i);
	}

	/** Predicate: Array <%s> must NOT be of size %s. Available in {@link P}, {@link Has}, {@link Have}.*/
	public static boolean lengthOtherThan(@Nonnull byte[] array, int i) {
		Null.nonNullArg(array, "array");
		return !(Array.getLength(array) == i);
	}

	/** "Special" predicate: Array <%s> must NOT be of size %s. Available in {@link P}, {@link Has}, {@link Have}.*/
	public static String lengthOtherThanEx(@Nonnull byte[] array, int i) {
		Null.nonNullArg(array, "array");
		return !(Array.getLength(array) == i) ? null : String.format("Array <%s> must NOT be of size %s.", array, i);
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

	/** Predicate: Array <%s> must be equal to array <%s>. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean equal(byte[] a1, byte[] a2) {
		return Arrays.equals(a1, a2);
	}

	/** "Special" predicate: Array <%s> must be equal to array <%s>. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String equalEx(byte[] a1, byte[] a2) {
		return (Arrays.equals(a1, a2)) ? null : String.format("Array <%s> must be equal to array <%s>.", a1, a2);
	}

	/** Predicate: Array <%s> must NOT be equal to array <%s>. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean notEqual(byte[] a1, byte[] a2) {
		return !(Arrays.equals(a1, a2));
	}

	/** "Special" predicate: Array <%s> must NOT be equal to array <%s>. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String notEqualEx(byte[] a1, byte[] a2) {
		return !(Arrays.equals(a1, a2)) ? null : String.format("Array <%s> must NOT be equal to array <%s>.", a1, a2);
	}

	/** Predicate: Array <%s> must be of size %s. Available in {@link P}, {@link Has}, {@link Have}.*/
	public static boolean length(@Nonnull double[] array, int i) {
		Null.nonNullArg(array, "array");
		return Array.getLength(array) == i;
	}

	/** "Special" predicate: Array <%s> must be of size %s. Available in {@link P}, {@link Has}, {@link Have}.*/
	public static String lengthEx(@Nonnull double[] array, int i) {
		Null.nonNullArg(array, "array");
		return (Array.getLength(array) == i) ? null : String.format("Array <%s> must be of size %s.", array, i);
	}

	/** Predicate: Array <%s> must NOT be of size %s. Available in {@link P}, {@link Has}, {@link Have}.*/
	public static boolean lengthOtherThan(@Nonnull double[] array, int i) {
		Null.nonNullArg(array, "array");
		return !(Array.getLength(array) == i);
	}

	/** "Special" predicate: Array <%s> must NOT be of size %s. Available in {@link P}, {@link Has}, {@link Have}.*/
	public static String lengthOtherThanEx(@Nonnull double[] array, int i) {
		Null.nonNullArg(array, "array");
		return !(Array.getLength(array) == i) ? null : String.format("Array <%s> must NOT be of size %s.", array, i);
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

	/** Predicate: Array <%s> must be equal to array <%s>. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean equal(double[] a1, double[] a2) {
		return Arrays.equals(a1, a2);
	}

	/** "Special" predicate: Array <%s> must be equal to array <%s>. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String equalEx(double[] a1, double[] a2) {
		return (Arrays.equals(a1, a2)) ? null : String.format("Array <%s> must be equal to array <%s>.", a1, a2);
	}

	/** Predicate: Array <%s> must NOT be equal to array <%s>. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean notEqual(double[] a1, double[] a2) {
		return !(Arrays.equals(a1, a2));
	}

	/** "Special" predicate: Array <%s> must NOT be equal to array <%s>. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String notEqualEx(double[] a1, double[] a2) {
		return !(Arrays.equals(a1, a2)) ? null : String.format("Array <%s> must NOT be equal to array <%s>.", a1, a2);
	}

	/** Predicate: Array <%s> must be of size %s. Available in {@link P}, {@link Has}, {@link Have}.*/
	public static boolean length(@Nonnull char[] array, int i) {
		Null.nonNullArg(array, "array");
		return Array.getLength(array) == i;
	}

	/** "Special" predicate: Array <%s> must be of size %s. Available in {@link P}, {@link Has}, {@link Have}.*/
	public static String lengthEx(@Nonnull char[] array, int i) {
		Null.nonNullArg(array, "array");
		return (Array.getLength(array) == i) ? null : String.format("Array <%s> must be of size %s.", array, i);
	}

	/** Predicate: Array <%s> must NOT be of size %s. Available in {@link P}, {@link Has}, {@link Have}.*/
	public static boolean lengthOtherThan(@Nonnull char[] array, int i) {
		Null.nonNullArg(array, "array");
		return !(Array.getLength(array) == i);
	}

	/** "Special" predicate: Array <%s> must NOT be of size %s. Available in {@link P}, {@link Has}, {@link Have}.*/
	public static String lengthOtherThanEx(@Nonnull char[] array, int i) {
		Null.nonNullArg(array, "array");
		return !(Array.getLength(array) == i) ? null : String.format("Array <%s> must NOT be of size %s.", array, i);
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

	/** Predicate: Array <%s> must be equal to array <%s>. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean equal(char[] a1, char[] a2) {
		return Arrays.equals(a1, a2);
	}

	/** "Special" predicate: Array <%s> must be equal to array <%s>. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String equalEx(char[] a1, char[] a2) {
		return (Arrays.equals(a1, a2)) ? null : String.format("Array <%s> must be equal to array <%s>.", a1, a2);
	}

	/** Predicate: Array <%s> must NOT be equal to array <%s>. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean notEqual(char[] a1, char[] a2) {
		return !(Arrays.equals(a1, a2));
	}

	/** "Special" predicate: Array <%s> must NOT be equal to array <%s>. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String notEqualEx(char[] a1, char[] a2) {
		return !(Arrays.equals(a1, a2)) ? null : String.format("Array <%s> must NOT be equal to array <%s>.", a1, a2);
	}

	/** Predicate: Array <%s> must be of size %s. Available in {@link P}, {@link Has}, {@link Have}.*/
	public static boolean length(@Nonnull short[] array, int i) {
		Null.nonNullArg(array, "array");
		return Array.getLength(array) == i;
	}

	/** "Special" predicate: Array <%s> must be of size %s. Available in {@link P}, {@link Has}, {@link Have}.*/
	public static String lengthEx(@Nonnull short[] array, int i) {
		Null.nonNullArg(array, "array");
		return (Array.getLength(array) == i) ? null : String.format("Array <%s> must be of size %s.", array, i);
	}

	/** Predicate: Array <%s> must NOT be of size %s. Available in {@link P}, {@link Has}, {@link Have}.*/
	public static boolean lengthOtherThan(@Nonnull short[] array, int i) {
		Null.nonNullArg(array, "array");
		return !(Array.getLength(array) == i);
	}

	/** "Special" predicate: Array <%s> must NOT be of size %s. Available in {@link P}, {@link Has}, {@link Have}.*/
	public static String lengthOtherThanEx(@Nonnull short[] array, int i) {
		Null.nonNullArg(array, "array");
		return !(Array.getLength(array) == i) ? null : String.format("Array <%s> must NOT be of size %s.", array, i);
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

	/** Predicate: Array <%s> must be equal to array <%s>. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean equal(short[] a1, short[] a2) {
		return Arrays.equals(a1, a2);
	}

	/** "Special" predicate: Array <%s> must be equal to array <%s>. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String equalEx(short[] a1, short[] a2) {
		return (Arrays.equals(a1, a2)) ? null : String.format("Array <%s> must be equal to array <%s>.", a1, a2);
	}

	/** Predicate: Array <%s> must NOT be equal to array <%s>. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean notEqual(short[] a1, short[] a2) {
		return !(Arrays.equals(a1, a2));
	}

	/** "Special" predicate: Array <%s> must NOT be equal to array <%s>. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String notEqualEx(short[] a1, short[] a2) {
		return !(Arrays.equals(a1, a2)) ? null : String.format("Array <%s> must NOT be equal to array <%s>.", a1, a2);
	}

	/** Predicate: Array <%s> must be of size %s. Available in {@link P}, {@link Has}, {@link Have}.*/
	public static boolean length(@Nonnull float[] array, int i) {
		Null.nonNullArg(array, "array");
		return Array.getLength(array) == i;
	}

	/** "Special" predicate: Array <%s> must be of size %s. Available in {@link P}, {@link Has}, {@link Have}.*/
	public static String lengthEx(@Nonnull float[] array, int i) {
		Null.nonNullArg(array, "array");
		return (Array.getLength(array) == i) ? null : String.format("Array <%s> must be of size %s.", array, i);
	}

	/** Predicate: Array <%s> must NOT be of size %s. Available in {@link P}, {@link Has}, {@link Have}.*/
	public static boolean lengthOtherThan(@Nonnull float[] array, int i) {
		Null.nonNullArg(array, "array");
		return !(Array.getLength(array) == i);
	}

	/** "Special" predicate: Array <%s> must NOT be of size %s. Available in {@link P}, {@link Has}, {@link Have}.*/
	public static String lengthOtherThanEx(@Nonnull float[] array, int i) {
		Null.nonNullArg(array, "array");
		return !(Array.getLength(array) == i) ? null : String.format("Array <%s> must NOT be of size %s.", array, i);
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

	/** Predicate: Array <%s> must be equal to array <%s>. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean equal(float[] a1, float[] a2) {
		return Arrays.equals(a1, a2);
	}

	/** "Special" predicate: Array <%s> must be equal to array <%s>. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String equalEx(float[] a1, float[] a2) {
		return (Arrays.equals(a1, a2)) ? null : String.format("Array <%s> must be equal to array <%s>.", a1, a2);
	}

	/** Predicate: Array <%s> must NOT be equal to array <%s>. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean notEqual(float[] a1, float[] a2) {
		return !(Arrays.equals(a1, a2));
	}

	/** "Special" predicate: Array <%s> must NOT be equal to array <%s>. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String notEqualEx(float[] a1, float[] a2) {
		return !(Arrays.equals(a1, a2)) ? null : String.format("Array <%s> must NOT be equal to array <%s>.", a1, a2);
	}

	/** Predicate: Array <%s> must be of size %s. Available in {@link P}, {@link Has}, {@link Have}.*/
	public static boolean length(@Nonnull int[] array, int i) {
		Null.nonNullArg(array, "array");
		return Array.getLength(array) == i;
	}

	/** "Special" predicate: Array <%s> must be of size %s. Available in {@link P}, {@link Has}, {@link Have}.*/
	public static String lengthEx(@Nonnull int[] array, int i) {
		Null.nonNullArg(array, "array");
		return (Array.getLength(array) == i) ? null : String.format("Array <%s> must be of size %s.", array, i);
	}

	/** Predicate: Array <%s> must NOT be of size %s. Available in {@link P}, {@link Has}, {@link Have}.*/
	public static boolean lengthOtherThan(@Nonnull int[] array, int i) {
		Null.nonNullArg(array, "array");
		return !(Array.getLength(array) == i);
	}

	/** "Special" predicate: Array <%s> must NOT be of size %s. Available in {@link P}, {@link Has}, {@link Have}.*/
	public static String lengthOtherThanEx(@Nonnull int[] array, int i) {
		Null.nonNullArg(array, "array");
		return !(Array.getLength(array) == i) ? null : String.format("Array <%s> must NOT be of size %s.", array, i);
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

	/** Predicate: Array <%s> must be equal to array <%s>. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean equal(int[] a1, int[] a2) {
		return Arrays.equals(a1, a2);
	}

	/** "Special" predicate: Array <%s> must be equal to array <%s>. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String equalEx(int[] a1, int[] a2) {
		return (Arrays.equals(a1, a2)) ? null : String.format("Array <%s> must be equal to array <%s>.", a1, a2);
	}

	/** Predicate: Array <%s> must NOT be equal to array <%s>. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean notEqual(int[] a1, int[] a2) {
		return !(Arrays.equals(a1, a2));
	}

	/** "Special" predicate: Array <%s> must NOT be equal to array <%s>. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String notEqualEx(int[] a1, int[] a2) {
		return !(Arrays.equals(a1, a2)) ? null : String.format("Array <%s> must NOT be equal to array <%s>.", a1, a2);
	}

	/** Predicate: Array <%s> must be of size %s. Available in {@link P}, {@link Has}, {@link Have}.*/
	public static boolean length(@Nonnull long[] array, int i) {
		Null.nonNullArg(array, "array");
		return Array.getLength(array) == i;
	}

	/** "Special" predicate: Array <%s> must be of size %s. Available in {@link P}, {@link Has}, {@link Have}.*/
	public static String lengthEx(@Nonnull long[] array, int i) {
		Null.nonNullArg(array, "array");
		return (Array.getLength(array) == i) ? null : String.format("Array <%s> must be of size %s.", array, i);
	}

	/** Predicate: Array <%s> must NOT be of size %s. Available in {@link P}, {@link Has}, {@link Have}.*/
	public static boolean lengthOtherThan(@Nonnull long[] array, int i) {
		Null.nonNullArg(array, "array");
		return !(Array.getLength(array) == i);
	}

	/** "Special" predicate: Array <%s> must NOT be of size %s. Available in {@link P}, {@link Has}, {@link Have}.*/
	public static String lengthOtherThanEx(@Nonnull long[] array, int i) {
		Null.nonNullArg(array, "array");
		return !(Array.getLength(array) == i) ? null : String.format("Array <%s> must NOT be of size %s.", array, i);
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

	/** Predicate: Array <%s> must be equal to array <%s>. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean equal(long[] a1, long[] a2) {
		return Arrays.equals(a1, a2);
	}

	/** "Special" predicate: Array <%s> must be equal to array <%s>. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String equalEx(long[] a1, long[] a2) {
		return (Arrays.equals(a1, a2)) ? null : String.format("Array <%s> must be equal to array <%s>.", a1, a2);
	}

	/** Predicate: Array <%s> must NOT be equal to array <%s>. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static boolean notEqual(long[] a1, long[] a2) {
		return !(Arrays.equals(a1, a2));
	}

	/** "Special" predicate: Array <%s> must NOT be equal to array <%s>. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static String notEqualEx(long[] a1, long[] a2) {
		return !(Arrays.equals(a1, a2)) ? null : String.format("Array <%s> must NOT be equal to array <%s>.", a1, a2);
	}

	/** Predicate: Array <%s> must be of size %s. Available in {@link P}, {@link Has}, {@link Have}.*/
	public static <T> boolean length(@Nonnull T[] array, int i) {
		Null.nonNullArg(array, "array");
		return Array.getLength(array) == i;
	}

	/** "Special" predicate: Array <%s> must be of size %s. Available in {@link P}, {@link Has}, {@link Have}.*/
	public static <T> String lengthEx(@Nonnull T[] array, int i) {
		Null.nonNullArg(array, "array");
		return (Array.getLength(array) == i) ? null : String.format("Array <%s> must be of size %s.", array, i);
	}

	/** Predicate: Array <%s> must NOT be of size %s. Available in {@link P}, {@link Has}, {@link Have}.*/
	public static <T> boolean lengthOtherThan(@Nonnull T[] array, int i) {
		Null.nonNullArg(array, "array");
		return !(Array.getLength(array) == i);
	}

	/** "Special" predicate: Array <%s> must NOT be of size %s. Available in {@link P}, {@link Has}, {@link Have}.*/
	public static <T> String lengthOtherThanEx(@Nonnull T[] array, int i) {
		Null.nonNullArg(array, "array");
		return !(Array.getLength(array) == i) ? null : String.format("Array <%s> must NOT be of size %s.", array, i);
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

	/** Predicate: Array <%s> must be equal to array <%s>. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static <T> boolean equal(T[] a1, T[] a2) {
		return Arrays.equals(a1, a2);
	}

	/** "Special" predicate: Array <%s> must be equal to array <%s>. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static <T> String equalEx(T[] a1, T[] a2) {
		return (Arrays.equals(a1, a2)) ? null : String.format("Array <%s> must be equal to array <%s>.", a1, a2);
	}

	/** Predicate: Array <%s> must NOT be equal to array <%s>. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static <T> boolean notEqual(T[] a1, T[] a2) {
		return !(Arrays.equals(a1, a2));
	}

	/** "Special" predicate: Array <%s> must NOT be equal to array <%s>. Available in {@link P}, {@link Is}, {@link Be}.*/
	public static <T> String notEqualEx(T[] a1, T[] a2) {
		return !(Arrays.equals(a1, a2)) ? null : String.format("Array <%s> must NOT be equal to array <%s>.", a1, a2);
	}

	// </editor-fold>

	// <editor-fold desc="collections">

	/** Predicate: Collection <%s> must contain element <%s>. Available in {@link P}, {@link Does}.*/
	public static <T> boolean contain(@Nonnull Collection<T> collection, T element) {
		Null.nonNullArg(collection, "collection");
		return collection.contains(element);
	}

	/** "Special" predicate: Collection <%s> must contain element <%s>. Available in {@link P}, {@link Does}.*/
	public static <T> String containEx(@Nonnull Collection<T> collection, T element) {
		Null.nonNullArg(collection, "collection");
		return (collection.contains(element)) ? null : String.format("Collection <%s> must contain element <%s>.", collection, element);
	}

	/** Predicate: Collection <%s> must NOT contain element <%s>. Available in {@link P}, {@link Does}.*/
	public static <T> boolean notContain(@Nonnull Collection<T> collection, T element) {
		Null.nonNullArg(collection, "collection");
		return !(collection.contains(element));
	}

	/** "Special" predicate: Collection <%s> must NOT contain element <%s>. Available in {@link P}, {@link Does}.*/
	public static <T> String notContainEx(@Nonnull Collection<T> collection, T element) {
		Null.nonNullArg(collection, "collection");
		return !(collection.contains(element)) ? null : String.format("Collection <%s> must NOT contain element <%s>.", collection, element);
	}

	/** Predicate: Map <%s> must contain key <%s>. Available in {@link P}, {@link Does}.*/
	public static <K> boolean containKey(@Nonnull Map<K, ?> map, K key) {
		Null.nonNullArg(map, "map");
		return map.containsKey(key);
	}

	/** "Special" predicate: Map <%s> must contain key <%s>. Available in {@link P}, {@link Does}.*/
	public static <K> String containKeyEx(@Nonnull Map<K, ?> map, K key) {
		Null.nonNullArg(map, "map");
		return (map.containsKey(key)) ? null : String.format("Map <%s> must contain key <%s>.", map, key);
	}

	/** Predicate: Map <%s> must NOT contain key <%s>. Available in {@link P}, {@link Does}.*/
	public static <K> boolean notContainKey(@Nonnull Map<K, ?> map, K key) {
		Null.nonNullArg(map, "map");
		return !(map.containsKey(key));
	}

	/** "Special" predicate: Map <%s> must NOT contain key <%s>. Available in {@link P}, {@link Does}.*/
	public static <K> String notContainKeyEx(@Nonnull Map<K, ?> map, K key) {
		Null.nonNullArg(map, "map");
		return !(map.containsKey(key)) ? null : String.format("Map <%s> must NOT contain key <%s>.", map, key);
	}

	/** Predicate: Collection <%s> must be of size %s. Available in {@link P}, {@link Has}, {@link Have}.*/
	public static <T> boolean size(@Nonnull Collection<T> collection, int i) {
		Null.nonNullArg(collection, "collection");
		return collection.size() == i;
	}

	/** "Special" predicate: Collection <%s> must be of size %s. Available in {@link P}, {@link Has}, {@link Have}.*/
	public static <T> String sizeEx(@Nonnull Collection<T> collection, int i) {
		Null.nonNullArg(collection, "collection");
		return (collection.size() == i) ? null : String.format("Collection <%s> must be of size %s.", collection, i);
	}

	/** Predicate: Collection <%s> must NOT be of size %s. Available in {@link P}, {@link Has}, {@link Have}.*/
	public static <T> boolean sizeOtherThan(@Nonnull Collection<T> collection, int i) {
		Null.nonNullArg(collection, "collection");
		return !(collection.size() == i);
	}

	/** "Special" predicate: Collection <%s> must NOT be of size %s. Available in {@link P}, {@link Has}, {@link Have}.*/
	public static <T> String sizeOtherThanEx(@Nonnull Collection<T> collection, int i) {
		Null.nonNullArg(collection, "collection");
		return !(collection.size() == i) ? null : String.format("Collection <%s> must NOT be of size %s.", collection, i);
	}

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

	/** Predicate: Map <%s> must be of size %s. Available in {@link P}, {@link Has}, {@link Have}.*/
	public static <K, V> boolean size(@Nonnull Map<K, V> map, int i) {
		Null.nonNullArg(map, "map");
		return map.size() == i;
	}

	/** "Special" predicate: Map <%s> must be of size %s. Available in {@link P}, {@link Has}, {@link Have}.*/
	public static <K, V> String sizeEx(@Nonnull Map<K, V> map, int i) {
		Null.nonNullArg(map, "map");
		return (map.size() == i) ? null : String.format("Map <%s> must be of size %s.", map, i);
	}

	/** Predicate: Map <%s> must NOT be of size %s. Available in {@link P}, {@link Has}, {@link Have}.*/
	public static <K, V> boolean sizeOtherThan(@Nonnull Map<K, V> map, int i) {
		Null.nonNullArg(map, "map");
		return !(map.size() == i);
	}

	/** "Special" predicate: Map <%s> must NOT be of size %s. Available in {@link P}, {@link Has}, {@link Have}.*/
	public static <K, V> String sizeOtherThanEx(@Nonnull Map<K, V> map, int i) {
		Null.nonNullArg(map, "map");
		return !(map.size() == i) ? null : String.format("Map <%s> must NOT be of size %s.", map, i);
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

	/** Predicate: Object <%s> of class <%s> must be instance of <%s>. Available in {@link P}, {@link Is}, {@link Be}, {@link P1}, {@link P2}.*/
	public static boolean instanceOf(Object object, Class<?> clazz) {
		Null.nonNullArg(clazz, "clazz");
		return clazz.isInstance(object);
	}

	/** "Special" predicate: Object <%s> of class <%s> must be instance of <%s>. Available in {@link P}, {@link Is}, {@link Be}, {@link P1}, {@link P2}.*/
	public static String instanceOfEx(Object object, Class<?> clazz) {
		Null.nonNullArg(clazz, "clazz");
		return (clazz.isInstance(object)) ? null : String.format("Object <%s> of class <%s> must be instance of <%s>.", object, object != null ? object.getClass() : null, clazz);
	}

	/** Predicate: Object <%s> of class <%s> must NOT be instance of <%s>. Available in {@link P}, {@link Is}, {@link Be}, {@link P1}, {@link P2}.*/
	public static boolean notInstanceOf(Object object, Class<?> clazz) {
		Null.nonNullArg(clazz, "clazz");
		return !(clazz.isInstance(object));
	}

	/** "Special" predicate: Object <%s> of class <%s> must NOT be instance of <%s>. Available in {@link P}, {@link Is}, {@link Be}, {@link P1}, {@link P2}.*/
	public static String notInstanceOfEx(Object object, Class<?> clazz) {
		Null.nonNullArg(clazz, "clazz");
		return !(clazz.isInstance(object)) ? null : String.format("Object <%s> of class <%s> must NOT be instance of <%s>.", object, object != null ? object.getClass() : null, clazz);
	}

	/** Predicate: Class <%s> of class <%s> must ---NOT-- be specialization of <%s>. Available in {@link P}, {@link Is}, {@link Be}, {@link P1}, {@link P2}.*/
	public static boolean assignableFrom(Class<?> specialization, Class<?> clazz) {
		Null.nonNullArg(clazz, "clazz");
		return clazz.isAssignableFrom(specialization);
	}

	/** "Special" predicate: Class <%s> of class <%s> must ---NOT-- be specialization of <%s>. Available in {@link P}, {@link Is}, {@link Be}, {@link P1}, {@link P2}.*/
	public static String assignableFromEx(Class<?> specialization, Class<?> clazz) {
		Null.nonNullArg(clazz, "clazz");
		return (clazz.isAssignableFrom(specialization)) ? null : String.format("Class <%s> of class <%s> must ---NOT-- be specialization of <%s>.", specialization, specialization != null ? specialization.getClass() : null, clazz);
	}

	/** Predicate: Class <%s> of class <%s> must ---NOT-- be specialization of <%s>. Available in {@link P}, {@link Is}, {@link Be}, {@link P1}, {@link P2}.*/
	public static boolean notAssignableFrom(Class<?> specialization, Class<?> clazz) {
		Null.nonNullArg(clazz, "clazz");
		return !(clazz.isAssignableFrom(specialization));
	}

	/** "Special" predicate: Class <%s> of class <%s> must ---NOT-- be specialization of <%s>. Available in {@link P}, {@link Is}, {@link Be}, {@link P1}, {@link P2}.*/
	public static String notAssignableFromEx(Class<?> specialization, Class<?> clazz) {
		Null.nonNullArg(clazz, "clazz");
		return !(clazz.isAssignableFrom(specialization)) ? null : String.format("Class <%s> of class <%s> must ---NOT-- be specialization of <%s>.", specialization, specialization != null ? specialization.getClass() : null, clazz);
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

	/** Predicate: Exception <%s> must have cause. Available in {@link P}, {@link Has}, {@link Have}.*/
	public static boolean cause(@Nonnull Throwable e) {
		Null.nonNullArg(e, "e");
		return e.getCause() != null;
	}

	/** "Special" predicate: Exception <%s> must have cause. Available in {@link P}, {@link Has}, {@link Have}.*/
	public static String causeEx(@Nonnull Throwable e) {
		Null.nonNullArg(e, "e");
		return (e.getCause() != null) ? null : String.format("Exception <%s> must have cause.", e);
	}

	/** Predicate: Exception <%s> must NOT have cause. Available in {@link P}, {@link Has}, {@link Have}.*/
	public static boolean noCause(@Nonnull Throwable e) {
		Null.nonNullArg(e, "e");
		return !(e.getCause() != null);
	}

	/** "Special" predicate: Exception <%s> must NOT have cause. Available in {@link P}, {@link Has}, {@link Have}.*/
	public static String noCauseEx(@Nonnull Throwable e) {
		Null.nonNullArg(e, "e");
		return !(e.getCause() != null) ? null : String.format("Exception <%s> must NOT have cause.", e);
	}

	/** Predicate: Exception <%s> must have message starting with <'%s>'. Available in {@link P}, {@link Has}, {@link Have}.*/
	public static boolean msgStartWith(@Nonnull Throwable e, @Nonnull String text) {
		Null.nonNullArg(e, "e");
		Null.nonNullArg(text, "text");
		return e.getMessage() != null && e.getMessage().startsWith(text);
	}

	/** "Special" predicate: Exception <%s> must have message starting with <'%s>'. Available in {@link P}, {@link Has}, {@link Have}.*/
	public static String msgStartWithEx(@Nonnull Throwable e, @Nonnull String text) {
		Null.nonNullArg(e, "e");
		Null.nonNullArg(text, "text");
		return (e.getMessage() != null && e.getMessage().startsWith(text)) ? null : String.format("Exception <%s> must have message starting with <'%s>'.", e, text);
	}

	/** Predicate: Exception <%s> must NOT have message starting with <'%s>'. Available in {@link P}, {@link Has}, {@link Have}.*/
	public static boolean msgNotStartWith(@Nonnull Throwable e, @Nonnull String text) {
		Null.nonNullArg(e, "e");
		Null.nonNullArg(text, "text");
		return !(e.getMessage() != null && e.getMessage().startsWith(text));
	}

	/** "Special" predicate: Exception <%s> must NOT have message starting with <'%s>'. Available in {@link P}, {@link Has}, {@link Have}.*/
	public static String msgNotStartWithEx(@Nonnull Throwable e, @Nonnull String text) {
		Null.nonNullArg(e, "e");
		Null.nonNullArg(text, "text");
		return !(e.getMessage() != null && e.getMessage().startsWith(text)) ? null : String.format("Exception <%s> must NOT have message starting with <'%s>'.", e, text);
	}

	/** Predicate: Exception <%s> must have message containing <'%s>'. Available in {@link P}, {@link Has}, {@link Have}.*/
	public static boolean msgContain(@Nonnull Throwable e, @Nonnull String text) {
		Null.nonNullArg(e, "e");
		Null.nonNullArg(text, "text");
		return e.getMessage() != null && e.getMessage().contains(text);
	}

	/** "Special" predicate: Exception <%s> must have message containing <'%s>'. Available in {@link P}, {@link Has}, {@link Have}.*/
	public static String msgContainEx(@Nonnull Throwable e, @Nonnull String text) {
		Null.nonNullArg(e, "e");
		Null.nonNullArg(text, "text");
		return (e.getMessage() != null && e.getMessage().contains(text)) ? null : String.format("Exception <%s> must have message containing <'%s>'.", e, text);
	}

	/** Predicate: Exception <%s> must NOT have message containing <'%s>'. Available in {@link P}, {@link Has}, {@link Have}.*/
	public static boolean msgNotContain(@Nonnull Throwable e, @Nonnull String text) {
		Null.nonNullArg(e, "e");
		Null.nonNullArg(text, "text");
		return !(e.getMessage() != null && e.getMessage().contains(text));
	}

	/** "Special" predicate: Exception <%s> must NOT have message containing <'%s>'. Available in {@link P}, {@link Has}, {@link Have}.*/
	public static String msgNotContainEx(@Nonnull Throwable e, @Nonnull String text) {
		Null.nonNullArg(e, "e");
		Null.nonNullArg(text, "text");
		return !(e.getMessage() != null && e.getMessage().contains(text)) ? null : String.format("Exception <%s> must NOT have message containing <'%s>'.", e, text);
	}

	/** Predicate: Exception <%s> must have message ending with <'%s>'. Available in {@link P}, {@link Has}, {@link Have}.*/
	public static boolean msgEndWith(@Nonnull Throwable e, @Nonnull String text) {
		Null.nonNullArg(e, "e");
		Null.nonNullArg(text, "text");
		return e.getMessage() != null && e.getMessage().endsWith(text);
	}

	/** "Special" predicate: Exception <%s> must have message ending with <'%s>'. Available in {@link P}, {@link Has}, {@link Have}.*/
	public static String msgEndWithEx(@Nonnull Throwable e, @Nonnull String text) {
		Null.nonNullArg(e, "e");
		Null.nonNullArg(text, "text");
		return (e.getMessage() != null && e.getMessage().endsWith(text)) ? null : String.format("Exception <%s> must have message ending with <'%s>'.", e, text);
	}

	/** Predicate: Exception <%s> must NOT have message ending with <'%s>'. Available in {@link P}, {@link Has}, {@link Have}.*/
	public static boolean msgNotEndWith(@Nonnull Throwable e, @Nonnull String text) {
		Null.nonNullArg(e, "e");
		Null.nonNullArg(text, "text");
		return !(e.getMessage() != null && e.getMessage().endsWith(text));
	}

	/** "Special" predicate: Exception <%s> must NOT have message ending with <'%s>'. Available in {@link P}, {@link Has}, {@link Have}.*/
	public static String msgNotEndWithEx(@Nonnull Throwable e, @Nonnull String text) {
		Null.nonNullArg(e, "e");
		Null.nonNullArg(text, "text");
		return !(e.getMessage() != null && e.getMessage().endsWith(text)) ? null : String.format("Exception <%s> must NOT have message ending with <'%s>'.", e, text);
	}

	/** Predicate: Exception <%s> must have suppressed other exceptions. Available in {@link P}, {@link Is}, {@link Has}, {@link Have}.*/
	public static boolean suppressing(@Nonnull Throwable e) {
		Null.nonNullArg(e, "e");
		return e.getSuppressed().length > 0;
	}

	/** "Special" predicate: Exception <%s> must have suppressed other exceptions. Available in {@link P}, {@link Is}, {@link Has}, {@link Have}.*/
	public static String suppressingEx(@Nonnull Throwable e) {
		Null.nonNullArg(e, "e");
		return (e.getSuppressed().length > 0) ? null : String.format("Exception <%s> must have suppressed other exceptions.", e);
	}

	/** Predicate: Exception <%s> must NOT have suppressed other exceptions. Available in {@link P}, {@link Is}, {@link Has}, {@link Have}.*/
	public static boolean notSuppressing(@Nonnull Throwable e) {
		Null.nonNullArg(e, "e");
		return !(e.getSuppressed().length > 0);
	}

	/** "Special" predicate: Exception <%s> must NOT have suppressed other exceptions. Available in {@link P}, {@link Is}, {@link Has}, {@link Have}.*/
	public static String notSuppressingEx(@Nonnull Throwable e) {
		Null.nonNullArg(e, "e");
		return !(e.getSuppressed().length > 0) ? null : String.format("Exception <%s> must NOT have suppressed other exceptions.", e);
	}

	// </editor-fold>

	// <editor-fold desc="have/has">

	/** 'ToBool' - first, actual value will be converted to primitive type (contrary to the object). */
	public static @Nonnull <K> LPredicate<K> haveToBool(@Nonnull LPredicate<K> extractor, @Nonnull LLogicalOperator operator) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(operator, "operator");
		return k -> operator.apply(extractor.test(k));
	}

	/**
	* 'ToBool' - first, actual value will be converted to primitive type (contrary to the object).
	* 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K> LFunction<K, String> haveToBoolEx(@Nonnull LPredicate<K> extractor, @Nonnull LBoolFunction<String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(extractor.test(k));
	}

	/** 'ToBool' - first, actual value will be converted to primitive type (contrary to the object). */
	public static @Nonnull <K> LPredicate<K> hasToBool(@Nonnull LPredicate<K> extractor, @Nonnull LLogicalOperator operator) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(operator, "operator");
		return k -> operator.apply(extractor.test(k));
	}

	/**
	* 'ToBool' - first, actual value will be converted to primitive type (contrary to the object).
	* 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K> LFunction<K, String> hasToBoolEx(@Nonnull LPredicate<K> extractor, @Nonnull LBoolFunction<String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(extractor.test(k));
	}

	/** 'ToBool' - first, actual value will be converted to primitive type (contrary to the object). */
	public static @Nonnull <K> LPredicate<K> haveToBool(@Nonnull LPredicate<K> extractor, boolean a2, @Nonnull LLogicalBinaryOperator operator) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(operator, "operator");
		return k -> operator.apply(extractor.test(k), a2);
	}

	/**
	* 'ToBool' - first, actual value will be converted to primitive type (contrary to the object).
	* 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K> LFunction<K, String> haveToBoolEx(@Nonnull LPredicate<K> extractor, boolean a2, @Nonnull LBiBoolFunction<String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(extractor.test(k), a2);
	}

	/** 'ToBool' - first, actual value will be converted to primitive type (contrary to the object). */
	public static @Nonnull <K> LPredicate<K> haveToBool(@Nonnull LPredicate<K> extractor, @Nonnull LLogicalBinaryOperator operator, boolean a2) {
		return haveToBool(extractor, a2, operator);
	}

	/**
	* 'ToBool' - first, actual value will be converted to primitive type (contrary to the object).
	* 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K> LFunction<K, String> haveToBoolEx(@Nonnull LPredicate<K> extractor, @Nonnull LBiBoolFunction<String> specialPredicate, boolean a2) {
		return haveToBoolEx(extractor, a2, specialPredicate);
	}

	/** 'ToBool' - first, actual value will be converted to primitive type (contrary to the object). */
	public static @Nonnull <K> LPredicate<K> hasToBool(@Nonnull LPredicate<K> extractor, boolean a2, @Nonnull LLogicalBinaryOperator operator) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(operator, "operator");
		return k -> operator.apply(extractor.test(k), a2);
	}

	/**
	* 'ToBool' - first, actual value will be converted to primitive type (contrary to the object).
	* 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K> LFunction<K, String> hasToBoolEx(@Nonnull LPredicate<K> extractor, boolean a2, @Nonnull LBiBoolFunction<String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(extractor.test(k), a2);
	}

	/** 'ToBool' - first, actual value will be converted to primitive type (contrary to the object). */
	public static @Nonnull <K> LPredicate<K> hasToBool(@Nonnull LPredicate<K> extractor, @Nonnull LLogicalBinaryOperator operator, boolean a2) {
		return hasToBool(extractor, a2, operator);
	}

	/**
	* 'ToBool' - first, actual value will be converted to primitive type (contrary to the object).
	* 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K> LFunction<K, String> hasToBoolEx(@Nonnull LPredicate<K> extractor, @Nonnull LBiBoolFunction<String> specialPredicate, boolean a2) {
		return hasToBoolEx(extractor, a2, specialPredicate);
	}

	/** 'ToBool' - first, actual value will be converted to primitive type (contrary to the object). */
	public static @Nonnull <K> LPredicate<K> haveToBool(@Nonnull LPredicate<K> extractor, boolean a2, boolean a3, @Nonnull LLogicalTernaryOperator operator) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(operator, "operator");
		return k -> operator.apply(extractor.test(k), a2, a3);
	}

	/**
	* 'ToBool' - first, actual value will be converted to primitive type (contrary to the object).
	* 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K> LFunction<K, String> haveToBoolEx(@Nonnull LPredicate<K> extractor, boolean a2, boolean a3, @Nonnull LTriBoolFunction<String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(extractor.test(k), a2, a3);
	}

	/** 'ToBool' - first, actual value will be converted to primitive type (contrary to the object). */
	public static @Nonnull <K> LPredicate<K> haveToBool(@Nonnull LPredicate<K> extractor, @Nonnull LLogicalTernaryOperator operator, boolean a2, boolean a3) {
		return haveToBool(extractor, a2, a3, operator);
	}

	/**
	* 'ToBool' - first, actual value will be converted to primitive type (contrary to the object).
	* 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K> LFunction<K, String> haveToBoolEx(@Nonnull LPredicate<K> extractor, @Nonnull LTriBoolFunction<String> specialPredicate, boolean a2, boolean a3) {
		return haveToBoolEx(extractor, a2, a3, specialPredicate);
	}

	/** 'ToBool' - first, actual value will be converted to primitive type (contrary to the object). */
	public static @Nonnull <K> LPredicate<K> hasToBool(@Nonnull LPredicate<K> extractor, boolean a2, boolean a3, @Nonnull LLogicalTernaryOperator operator) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(operator, "operator");
		return k -> operator.apply(extractor.test(k), a2, a3);
	}

	/**
	* 'ToBool' - first, actual value will be converted to primitive type (contrary to the object).
	* 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K> LFunction<K, String> hasToBoolEx(@Nonnull LPredicate<K> extractor, boolean a2, boolean a3, @Nonnull LTriBoolFunction<String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(extractor.test(k), a2, a3);
	}

	/** 'ToBool' - first, actual value will be converted to primitive type (contrary to the object). */
	public static @Nonnull <K> LPredicate<K> hasToBool(@Nonnull LPredicate<K> extractor, @Nonnull LLogicalTernaryOperator operator, boolean a2, boolean a3) {
		return hasToBool(extractor, a2, a3, operator);
	}

	/**
	* 'ToBool' - first, actual value will be converted to primitive type (contrary to the object).
	* 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K> LFunction<K, String> hasToBoolEx(@Nonnull LPredicate<K> extractor, @Nonnull LTriBoolFunction<String> specialPredicate, boolean a2, boolean a3) {
		return hasToBoolEx(extractor, a2, a3, specialPredicate);
	}

	/** 'ToBool' - first, actual value will be converted to primitive type (contrary to the object). */
	public static @Nonnull <K> LPredicate<K> haveToBoolInt(@Nonnull LPredicate<K> extractor, int v, @Nonnull LBoolIntPredicate operator) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(operator, "operator");
		return k -> operator.test(extractor.test(k), v);
	}

	/** 'ToBool' - first, actual value will be converted to primitive type (contrary to the object). */
	public static @Nonnull <K> LPredicate<K> haveToBoolInt(@Nonnull LPredicate<K> extractor, @Nonnull LBoolIntPredicate operator, int v) {
		return haveToBoolInt(extractor, v, operator);
	}

	/** 'ToBool' - first, actual value will be converted to primitive type (contrary to the object). */
	public static @Nonnull <K> LPredicate<K> hasToBoolInt(@Nonnull LPredicate<K> extractor, int v, @Nonnull LBoolIntPredicate operator) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(operator, "operator");
		return k -> operator.test(extractor.test(k), v);
	}

	/** 'ToBool' - first, actual value will be converted to primitive type (contrary to the object). */
	public static @Nonnull <K> LPredicate<K> hasToBoolInt(@Nonnull LPredicate<K> extractor, @Nonnull LBoolIntPredicate operator, int v) {
		return hasToBoolInt(extractor, v, operator);
	}

	/**
	* 'ToBool' - first, actual value will be converted to primitive type (contrary to the object).
	* '_' - the predicate function is a variant or arguments represented by other function.
	*/
	public static @Nonnull <K, V> LPredicate<K> haveToBool_(@Nonnull LPredicate<K> extractor, V v, @Nonnull LObjBoolPredicate.LBoolObjPred<? super V> operator) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(operator, "operator");
		return k -> operator.testBoolObj(extractor.test(k), v);
	}

	/**
	* 'ToBool' - first, actual value will be converted to primitive type (contrary to the object).
	* 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	* '_' - the predicate function is a variant or arguments represented by other function.
	*/
	public static @Nonnull <K, V> LFunction<K, String> haveToBoolEx_(@Nonnull LPredicate<K> extractor, V v, @Nonnull LObjBoolFunction.LBoolObjFunc<? super V, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.applyBoolObj(extractor.test(k), v);
	}

	/**
	* 'ToBool' - first, actual value will be converted to primitive type (contrary to the object).
	* '_' - the predicate function is a variant or arguments represented by other function.
	*/
	public static @Nonnull <K, V> LPredicate<K> haveToBool_(@Nonnull LPredicate<K> extractor, @Nonnull LObjBoolPredicate.LBoolObjPred<? super V> operator, V v) {
		return haveToBool_(extractor, v, operator);
	}

	/**
	* 'ToBool' - first, actual value will be converted to primitive type (contrary to the object).
	* 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	* '_' - the predicate function is a variant or arguments represented by other function.
	*/
	public static @Nonnull <K, V> LFunction<K, String> haveToBoolEx_(@Nonnull LPredicate<K> extractor, @Nonnull LObjBoolFunction.LBoolObjFunc<? super V, String> specialPredicate, V v) {
		return haveToBoolEx_(extractor, v, specialPredicate);
	}

	/**
	* 'ToBool' - first, actual value will be converted to primitive type (contrary to the object).
	* '_' - the predicate function is a variant or arguments represented by other function.
	*/
	public static @Nonnull <K, V> LPredicate<K> hasToBool_(@Nonnull LPredicate<K> extractor, V v, @Nonnull LObjBoolPredicate.LBoolObjPred<? super V> operator) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(operator, "operator");
		return k -> operator.testBoolObj(extractor.test(k), v);
	}

	/**
	* 'ToBool' - first, actual value will be converted to primitive type (contrary to the object).
	* 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	* '_' - the predicate function is a variant or arguments represented by other function.
	*/
	public static @Nonnull <K, V> LFunction<K, String> hasToBoolEx_(@Nonnull LPredicate<K> extractor, V v, @Nonnull LObjBoolFunction.LBoolObjFunc<? super V, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.applyBoolObj(extractor.test(k), v);
	}

	/**
	* 'ToBool' - first, actual value will be converted to primitive type (contrary to the object).
	* '_' - the predicate function is a variant or arguments represented by other function.
	*/
	public static @Nonnull <K, V> LPredicate<K> hasToBool_(@Nonnull LPredicate<K> extractor, @Nonnull LObjBoolPredicate.LBoolObjPred<? super V> operator, V v) {
		return hasToBool_(extractor, v, operator);
	}

	/**
	* 'ToBool' - first, actual value will be converted to primitive type (contrary to the object).
	* 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	* '_' - the predicate function is a variant or arguments represented by other function.
	*/
	public static @Nonnull <K, V> LFunction<K, String> hasToBoolEx_(@Nonnull LPredicate<K> extractor, @Nonnull LObjBoolFunction.LBoolObjFunc<? super V, String> specialPredicate, V v) {
		return hasToBoolEx_(extractor, v, specialPredicate);
	}

	/**
	* 'ToBool' - first, actual value will be converted to primitive type (contrary to the object).
	* 'With' - call of main predicate will be prefixed (contrary to affixed) with additional argument(s).
	*/
	public static @Nonnull <K, V1> LPredicate<K> haveToBoolWith(@Nonnull LPredicate<K> extractor, V1 with1, @Nonnull LObjBoolPredicate<? super V1> operator) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(operator, "operator");
		return k -> operator.test(with1, extractor.test(k));
	}

	/**
	* 'ToBool' - first, actual value will be converted to primitive type (contrary to the object).
	* 'With' - call of main predicate will be prefixed (contrary to affixed) with additional argument(s).
	* 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K, V1> LFunction<K, String> haveToBoolWithEx(@Nonnull LPredicate<K> extractor, V1 with1, @Nonnull LObjBoolFunction<? super V1, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(with1, extractor.test(k));
	}

	/**
	* 'ToBool' - first, actual value will be converted to primitive type (contrary to the object).
	* 'With' - call of main predicate will be prefixed (contrary to affixed) with additional argument(s).
	*/
	public static @Nonnull <K, V1> LPredicate<K> haveToBoolWith(@Nonnull LPredicate<K> extractor, @Nonnull LObjBoolPredicate<? super V1> operator, V1 with1) {
		return haveToBoolWith(extractor, with1, operator);
	}

	/**
	* 'ToBool' - first, actual value will be converted to primitive type (contrary to the object).
	* 'With' - call of main predicate will be prefixed (contrary to affixed) with additional argument(s).
	* 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K, V1> LFunction<K, String> haveToBoolWithEx(@Nonnull LPredicate<K> extractor, @Nonnull LObjBoolFunction<? super V1, String> specialPredicate, V1 with1) {
		return haveToBoolWithEx(extractor, with1, specialPredicate);
	}

	/**
	* 'ToBool' - first, actual value will be converted to primitive type (contrary to the object).
	* 'With' - call of main predicate will be prefixed (contrary to affixed) with additional argument(s).
	*/
	public static @Nonnull <K, V1> LPredicate<K> hasToBoolWith(@Nonnull LPredicate<K> extractor, V1 with1, @Nonnull LObjBoolPredicate<? super V1> operator) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(operator, "operator");
		return k -> operator.test(with1, extractor.test(k));
	}

	/**
	* 'ToBool' - first, actual value will be converted to primitive type (contrary to the object).
	* 'With' - call of main predicate will be prefixed (contrary to affixed) with additional argument(s).
	* 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K, V1> LFunction<K, String> hasToBoolWithEx(@Nonnull LPredicate<K> extractor, V1 with1, @Nonnull LObjBoolFunction<? super V1, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(with1, extractor.test(k));
	}

	/**
	* 'ToBool' - first, actual value will be converted to primitive type (contrary to the object).
	* 'With' - call of main predicate will be prefixed (contrary to affixed) with additional argument(s).
	*/
	public static @Nonnull <K, V1> LPredicate<K> hasToBoolWith(@Nonnull LPredicate<K> extractor, @Nonnull LObjBoolPredicate<? super V1> operator, V1 with1) {
		return hasToBoolWith(extractor, with1, operator);
	}

	/**
	* 'ToBool' - first, actual value will be converted to primitive type (contrary to the object).
	* 'With' - call of main predicate will be prefixed (contrary to affixed) with additional argument(s).
	* 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K, V1> LFunction<K, String> hasToBoolWithEx(@Nonnull LPredicate<K> extractor, @Nonnull LObjBoolFunction<? super V1, String> specialPredicate, V1 with1) {
		return hasToBoolWithEx(extractor, with1, specialPredicate);
	}

	/**
	* 'ToBool' - first, actual value will be converted to primitive type (contrary to the object).
	* 'With' - call of main predicate will be prefixed (contrary to affixed) with additional argument(s).
	*/
	public static @Nonnull <K, V1, V2> LPredicate<K> haveToBoolWith(@Nonnull LPredicate<K> extractor, V1 with1, V2 with2, @Nonnull LBiObjBoolPredicate<? super V1, ? super V2> operator) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(operator, "operator");
		return k -> operator.test(with1, with2, extractor.test(k));
	}

	/**
	* 'ToBool' - first, actual value will be converted to primitive type (contrary to the object).
	* 'With' - call of main predicate will be prefixed (contrary to affixed) with additional argument(s).
	* 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K, V1, V2> LFunction<K, String> haveToBoolWithEx(@Nonnull LPredicate<K> extractor, V1 with1, V2 with2, @Nonnull LBiObjBoolFunction<? super V1, ? super V2, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(with1, with2, extractor.test(k));
	}

	/**
	* 'ToBool' - first, actual value will be converted to primitive type (contrary to the object).
	* 'With' - call of main predicate will be prefixed (contrary to affixed) with additional argument(s).
	*/
	public static @Nonnull <K, V1, V2> LPredicate<K> haveToBoolWith(@Nonnull LPredicate<K> extractor, @Nonnull LBiObjBoolPredicate<? super V1, ? super V2> operator, V1 with1, V2 with2) {
		return haveToBoolWith(extractor, with1, with2, operator);
	}

	/**
	* 'ToBool' - first, actual value will be converted to primitive type (contrary to the object).
	* 'With' - call of main predicate will be prefixed (contrary to affixed) with additional argument(s).
	* 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K, V1, V2> LFunction<K, String> haveToBoolWithEx(@Nonnull LPredicate<K> extractor, @Nonnull LBiObjBoolFunction<? super V1, ? super V2, String> specialPredicate, V1 with1, V2 with2) {
		return haveToBoolWithEx(extractor, with1, with2, specialPredicate);
	}

	/**
	* 'ToBool' - first, actual value will be converted to primitive type (contrary to the object).
	* 'With' - call of main predicate will be prefixed (contrary to affixed) with additional argument(s).
	*/
	public static @Nonnull <K, V1, V2> LPredicate<K> hasToBoolWith(@Nonnull LPredicate<K> extractor, V1 with1, V2 with2, @Nonnull LBiObjBoolPredicate<? super V1, ? super V2> operator) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(operator, "operator");
		return k -> operator.test(with1, with2, extractor.test(k));
	}

	/**
	* 'ToBool' - first, actual value will be converted to primitive type (contrary to the object).
	* 'With' - call of main predicate will be prefixed (contrary to affixed) with additional argument(s).
	* 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K, V1, V2> LFunction<K, String> hasToBoolWithEx(@Nonnull LPredicate<K> extractor, V1 with1, V2 with2, @Nonnull LBiObjBoolFunction<? super V1, ? super V2, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(with1, with2, extractor.test(k));
	}

	/**
	* 'ToBool' - first, actual value will be converted to primitive type (contrary to the object).
	* 'With' - call of main predicate will be prefixed (contrary to affixed) with additional argument(s).
	*/
	public static @Nonnull <K, V1, V2> LPredicate<K> hasToBoolWith(@Nonnull LPredicate<K> extractor, @Nonnull LBiObjBoolPredicate<? super V1, ? super V2> operator, V1 with1, V2 with2) {
		return hasToBoolWith(extractor, with1, with2, operator);
	}

	/**
	* 'ToBool' - first, actual value will be converted to primitive type (contrary to the object).
	* 'With' - call of main predicate will be prefixed (contrary to affixed) with additional argument(s).
	* 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K, V1, V2> LFunction<K, String> hasToBoolWithEx(@Nonnull LPredicate<K> extractor, @Nonnull LBiObjBoolFunction<? super V1, ? super V2, String> specialPredicate, V1 with1, V2 with2) {
		return hasToBoolWithEx(extractor, with1, with2, specialPredicate);
	}

	public static @Nonnull <K, T> LPredicate<K> have(@Nonnull LFunction<K, T> extractor, @Nonnull LPredicate<? super T> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.apply(k));
	}

	/** 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied). */
	public static @Nonnull <K, T> LFunction<K, String> haveEx(@Nonnull LFunction<K, T> extractor, @Nonnull LFunction<? super T, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(extractor.apply(k));
	}

	public static @Nonnull <K, T> LPredicate<K> has(@Nonnull LFunction<K, T> extractor, @Nonnull LPredicate<? super T> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.apply(k));
	}

	/** 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied). */
	public static @Nonnull <K, T> LFunction<K, String> hasEx(@Nonnull LFunction<K, T> extractor, @Nonnull LFunction<? super T, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(extractor.apply(k));
	}

	public static @Nonnull <K, T> LPredicate<K> uniHave(@Nonnull LFunction<K, T> extractor, T a2, @Nonnull LBiPredicate<? super T, ? super T> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.apply(k), a2);
	}

	/** 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied). */
	public static @Nonnull <K, T> LFunction<K, String> uniHaveEx(@Nonnull LFunction<K, T> extractor, T a2, @Nonnull LBiFunction<? super T, ? super T, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(extractor.apply(k), a2);
	}

	public static @Nonnull <K, T> LPredicate<K> uniHave(@Nonnull LFunction<K, T> extractor, @Nonnull LBiPredicate<? super T, ? super T> predicate, T a2) {
		return uniHave(extractor, a2, predicate);
	}

	/** 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied). */
	public static @Nonnull <K, T> LFunction<K, String> uniHaveEx(@Nonnull LFunction<K, T> extractor, @Nonnull LBiFunction<? super T, ? super T, String> specialPredicate, T a2) {
		return uniHaveEx(extractor, a2, specialPredicate);
	}

	public static @Nonnull <K, T> LPredicate<K> uniHas(@Nonnull LFunction<K, T> extractor, T a2, @Nonnull LBiPredicate<? super T, ? super T> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.apply(k), a2);
	}

	/** 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied). */
	public static @Nonnull <K, T> LFunction<K, String> uniHasEx(@Nonnull LFunction<K, T> extractor, T a2, @Nonnull LBiFunction<? super T, ? super T, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(extractor.apply(k), a2);
	}

	public static @Nonnull <K, T> LPredicate<K> uniHas(@Nonnull LFunction<K, T> extractor, @Nonnull LBiPredicate<? super T, ? super T> predicate, T a2) {
		return uniHas(extractor, a2, predicate);
	}

	/** 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied). */
	public static @Nonnull <K, T> LFunction<K, String> uniHasEx(@Nonnull LFunction<K, T> extractor, @Nonnull LBiFunction<? super T, ? super T, String> specialPredicate, T a2) {
		return uniHasEx(extractor, a2, specialPredicate);
	}

	public static @Nonnull <K, T> LPredicate<K> uniHave(@Nonnull LFunction<K, T> extractor, T a2, T a3, @Nonnull LTriPredicate<? super T, ? super T, ? super T> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.apply(k), a2, a3);
	}

	/** 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied). */
	public static @Nonnull <K, T> LFunction<K, String> uniHaveEx(@Nonnull LFunction<K, T> extractor, T a2, T a3, @Nonnull LTriFunction<? super T, ? super T, ? super T, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(extractor.apply(k), a2, a3);
	}

	public static @Nonnull <K, T> LPredicate<K> uniHave(@Nonnull LFunction<K, T> extractor, @Nonnull LTriPredicate<? super T, ? super T, ? super T> predicate, T a2, T a3) {
		return uniHave(extractor, a2, a3, predicate);
	}

	/** 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied). */
	public static @Nonnull <K, T> LFunction<K, String> uniHaveEx(@Nonnull LFunction<K, T> extractor, @Nonnull LTriFunction<? super T, ? super T, ? super T, String> specialPredicate, T a2, T a3) {
		return uniHaveEx(extractor, a2, a3, specialPredicate);
	}

	public static @Nonnull <K, T> LPredicate<K> uniHas(@Nonnull LFunction<K, T> extractor, T a2, T a3, @Nonnull LTriPredicate<? super T, ? super T, ? super T> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.apply(k), a2, a3);
	}

	/** 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied). */
	public static @Nonnull <K, T> LFunction<K, String> uniHasEx(@Nonnull LFunction<K, T> extractor, T a2, T a3, @Nonnull LTriFunction<? super T, ? super T, ? super T, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(extractor.apply(k), a2, a3);
	}

	public static @Nonnull <K, T> LPredicate<K> uniHas(@Nonnull LFunction<K, T> extractor, @Nonnull LTriPredicate<? super T, ? super T, ? super T> predicate, T a2, T a3) {
		return uniHas(extractor, a2, a3, predicate);
	}

	/** 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied). */
	public static @Nonnull <K, T> LFunction<K, String> uniHasEx(@Nonnull LFunction<K, T> extractor, @Nonnull LTriFunction<? super T, ? super T, ? super T, String> specialPredicate, T a2, T a3) {
		return uniHasEx(extractor, a2, a3, specialPredicate);
	}

	public static @Nonnull <K, T> LPredicate<K> uniHave(@Nonnull LFunction<K, T> extractor, T a2, T a3, T a4, @Nonnull LQuadPredicate<? super T, ? super T, ? super T, ? super T> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.apply(k), a2, a3, a4);
	}

	/** 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied). */
	public static @Nonnull <K, T> LFunction<K, String> uniHaveEx(@Nonnull LFunction<K, T> extractor, T a2, T a3, T a4, @Nonnull LQuadFunction<? super T, ? super T, ? super T, ? super T, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(extractor.apply(k), a2, a3, a4);
	}

	public static @Nonnull <K, T> LPredicate<K> uniHave(@Nonnull LFunction<K, T> extractor, @Nonnull LQuadPredicate<? super T, ? super T, ? super T, ? super T> predicate, T a2, T a3, T a4) {
		return uniHave(extractor, a2, a3, a4, predicate);
	}

	/** 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied). */
	public static @Nonnull <K, T> LFunction<K, String> uniHaveEx(@Nonnull LFunction<K, T> extractor, @Nonnull LQuadFunction<? super T, ? super T, ? super T, ? super T, String> specialPredicate, T a2, T a3, T a4) {
		return uniHaveEx(extractor, a2, a3, a4, specialPredicate);
	}

	public static @Nonnull <K, T> LPredicate<K> uniHas(@Nonnull LFunction<K, T> extractor, T a2, T a3, T a4, @Nonnull LQuadPredicate<? super T, ? super T, ? super T, ? super T> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.apply(k), a2, a3, a4);
	}

	/** 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied). */
	public static @Nonnull <K, T> LFunction<K, String> uniHasEx(@Nonnull LFunction<K, T> extractor, T a2, T a3, T a4, @Nonnull LQuadFunction<? super T, ? super T, ? super T, ? super T, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(extractor.apply(k), a2, a3, a4);
	}

	public static @Nonnull <K, T> LPredicate<K> uniHas(@Nonnull LFunction<K, T> extractor, @Nonnull LQuadPredicate<? super T, ? super T, ? super T, ? super T> predicate, T a2, T a3, T a4) {
		return uniHas(extractor, a2, a3, a4, predicate);
	}

	/** 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied). */
	public static @Nonnull <K, T> LFunction<K, String> uniHasEx(@Nonnull LFunction<K, T> extractor, @Nonnull LQuadFunction<? super T, ? super T, ? super T, ? super T, String> specialPredicate, T a2, T a3, T a4) {
		return uniHasEx(extractor, a2, a3, a4, specialPredicate);
	}

	public static @Nonnull <K, T> LPredicate<K> haveBool(@Nonnull LFunction<K, T> extractor, boolean v, @Nonnull LObjBoolPredicate<? super T> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.apply(k), v);
	}

	/** 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied). */
	public static @Nonnull <K, T> LFunction<K, String> haveBoolEx(@Nonnull LFunction<K, T> extractor, boolean v, @Nonnull LObjBoolFunction<? super T, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(extractor.apply(k), v);
	}

	public static @Nonnull <K, T> LPredicate<K> haveBool(@Nonnull LFunction<K, T> extractor, @Nonnull LObjBoolPredicate<? super T> predicate, boolean v) {
		return haveBool(extractor, v, predicate);
	}

	/** 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied). */
	public static @Nonnull <K, T> LFunction<K, String> haveBoolEx(@Nonnull LFunction<K, T> extractor, @Nonnull LObjBoolFunction<? super T, String> specialPredicate, boolean v) {
		return haveBoolEx(extractor, v, specialPredicate);
	}

	public static @Nonnull <K, T> LPredicate<K> hasBool(@Nonnull LFunction<K, T> extractor, boolean v, @Nonnull LObjBoolPredicate<? super T> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.apply(k), v);
	}

	/** 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied). */
	public static @Nonnull <K, T> LFunction<K, String> hasBoolEx(@Nonnull LFunction<K, T> extractor, boolean v, @Nonnull LObjBoolFunction<? super T, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(extractor.apply(k), v);
	}

	public static @Nonnull <K, T> LPredicate<K> hasBool(@Nonnull LFunction<K, T> extractor, @Nonnull LObjBoolPredicate<? super T> predicate, boolean v) {
		return hasBool(extractor, v, predicate);
	}

	/** 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied). */
	public static @Nonnull <K, T> LFunction<K, String> hasBoolEx(@Nonnull LFunction<K, T> extractor, @Nonnull LObjBoolFunction<? super T, String> specialPredicate, boolean v) {
		return hasBoolEx(extractor, v, specialPredicate);
	}

	public static @Nonnull <K, T> LPredicate<K> haveByte(@Nonnull LFunction<K, T> extractor, byte v, @Nonnull LObjBytePredicate<? super T> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.apply(k), v);
	}

	/** 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied). */
	public static @Nonnull <K, T> LFunction<K, String> haveByteEx(@Nonnull LFunction<K, T> extractor, byte v, @Nonnull LObjByteFunction<? super T, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(extractor.apply(k), v);
	}

	public static @Nonnull <K, T> LPredicate<K> haveByte(@Nonnull LFunction<K, T> extractor, @Nonnull LObjBytePredicate<? super T> predicate, byte v) {
		return haveByte(extractor, v, predicate);
	}

	/** 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied). */
	public static @Nonnull <K, T> LFunction<K, String> haveByteEx(@Nonnull LFunction<K, T> extractor, @Nonnull LObjByteFunction<? super T, String> specialPredicate, byte v) {
		return haveByteEx(extractor, v, specialPredicate);
	}

	public static @Nonnull <K, T> LPredicate<K> hasByte(@Nonnull LFunction<K, T> extractor, byte v, @Nonnull LObjBytePredicate<? super T> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.apply(k), v);
	}

	/** 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied). */
	public static @Nonnull <K, T> LFunction<K, String> hasByteEx(@Nonnull LFunction<K, T> extractor, byte v, @Nonnull LObjByteFunction<? super T, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(extractor.apply(k), v);
	}

	public static @Nonnull <K, T> LPredicate<K> hasByte(@Nonnull LFunction<K, T> extractor, @Nonnull LObjBytePredicate<? super T> predicate, byte v) {
		return hasByte(extractor, v, predicate);
	}

	/** 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied). */
	public static @Nonnull <K, T> LFunction<K, String> hasByteEx(@Nonnull LFunction<K, T> extractor, @Nonnull LObjByteFunction<? super T, String> specialPredicate, byte v) {
		return hasByteEx(extractor, v, specialPredicate);
	}

	public static @Nonnull <K, T> LPredicate<K> haveDbl(@Nonnull LFunction<K, T> extractor, double v, @Nonnull LObjDblPredicate<? super T> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.apply(k), v);
	}

	/** 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied). */
	public static @Nonnull <K, T> LFunction<K, String> haveDblEx(@Nonnull LFunction<K, T> extractor, double v, @Nonnull LObjDblFunction<? super T, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(extractor.apply(k), v);
	}

	public static @Nonnull <K, T> LPredicate<K> haveDbl(@Nonnull LFunction<K, T> extractor, @Nonnull LObjDblPredicate<? super T> predicate, double v) {
		return haveDbl(extractor, v, predicate);
	}

	/** 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied). */
	public static @Nonnull <K, T> LFunction<K, String> haveDblEx(@Nonnull LFunction<K, T> extractor, @Nonnull LObjDblFunction<? super T, String> specialPredicate, double v) {
		return haveDblEx(extractor, v, specialPredicate);
	}

	public static @Nonnull <K, T> LPredicate<K> hasDbl(@Nonnull LFunction<K, T> extractor, double v, @Nonnull LObjDblPredicate<? super T> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.apply(k), v);
	}

	/** 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied). */
	public static @Nonnull <K, T> LFunction<K, String> hasDblEx(@Nonnull LFunction<K, T> extractor, double v, @Nonnull LObjDblFunction<? super T, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(extractor.apply(k), v);
	}

	public static @Nonnull <K, T> LPredicate<K> hasDbl(@Nonnull LFunction<K, T> extractor, @Nonnull LObjDblPredicate<? super T> predicate, double v) {
		return hasDbl(extractor, v, predicate);
	}

	/** 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied). */
	public static @Nonnull <K, T> LFunction<K, String> hasDblEx(@Nonnull LFunction<K, T> extractor, @Nonnull LObjDblFunction<? super T, String> specialPredicate, double v) {
		return hasDblEx(extractor, v, specialPredicate);
	}

	public static @Nonnull <K, T> LPredicate<K> haveChar(@Nonnull LFunction<K, T> extractor, char v, @Nonnull LObjCharPredicate<? super T> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.apply(k), v);
	}

	/** 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied). */
	public static @Nonnull <K, T> LFunction<K, String> haveCharEx(@Nonnull LFunction<K, T> extractor, char v, @Nonnull LObjCharFunction<? super T, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(extractor.apply(k), v);
	}

	public static @Nonnull <K, T> LPredicate<K> haveChar(@Nonnull LFunction<K, T> extractor, @Nonnull LObjCharPredicate<? super T> predicate, char v) {
		return haveChar(extractor, v, predicate);
	}

	/** 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied). */
	public static @Nonnull <K, T> LFunction<K, String> haveCharEx(@Nonnull LFunction<K, T> extractor, @Nonnull LObjCharFunction<? super T, String> specialPredicate, char v) {
		return haveCharEx(extractor, v, specialPredicate);
	}

	public static @Nonnull <K, T> LPredicate<K> hasChar(@Nonnull LFunction<K, T> extractor, char v, @Nonnull LObjCharPredicate<? super T> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.apply(k), v);
	}

	/** 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied). */
	public static @Nonnull <K, T> LFunction<K, String> hasCharEx(@Nonnull LFunction<K, T> extractor, char v, @Nonnull LObjCharFunction<? super T, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(extractor.apply(k), v);
	}

	public static @Nonnull <K, T> LPredicate<K> hasChar(@Nonnull LFunction<K, T> extractor, @Nonnull LObjCharPredicate<? super T> predicate, char v) {
		return hasChar(extractor, v, predicate);
	}

	/** 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied). */
	public static @Nonnull <K, T> LFunction<K, String> hasCharEx(@Nonnull LFunction<K, T> extractor, @Nonnull LObjCharFunction<? super T, String> specialPredicate, char v) {
		return hasCharEx(extractor, v, specialPredicate);
	}

	public static @Nonnull <K, T> LPredicate<K> haveSrt(@Nonnull LFunction<K, T> extractor, short v, @Nonnull LObjSrtPredicate<? super T> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.apply(k), v);
	}

	/** 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied). */
	public static @Nonnull <K, T> LFunction<K, String> haveSrtEx(@Nonnull LFunction<K, T> extractor, short v, @Nonnull LObjSrtFunction<? super T, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(extractor.apply(k), v);
	}

	public static @Nonnull <K, T> LPredicate<K> haveSrt(@Nonnull LFunction<K, T> extractor, @Nonnull LObjSrtPredicate<? super T> predicate, short v) {
		return haveSrt(extractor, v, predicate);
	}

	/** 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied). */
	public static @Nonnull <K, T> LFunction<K, String> haveSrtEx(@Nonnull LFunction<K, T> extractor, @Nonnull LObjSrtFunction<? super T, String> specialPredicate, short v) {
		return haveSrtEx(extractor, v, specialPredicate);
	}

	public static @Nonnull <K, T> LPredicate<K> hasSrt(@Nonnull LFunction<K, T> extractor, short v, @Nonnull LObjSrtPredicate<? super T> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.apply(k), v);
	}

	/** 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied). */
	public static @Nonnull <K, T> LFunction<K, String> hasSrtEx(@Nonnull LFunction<K, T> extractor, short v, @Nonnull LObjSrtFunction<? super T, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(extractor.apply(k), v);
	}

	public static @Nonnull <K, T> LPredicate<K> hasSrt(@Nonnull LFunction<K, T> extractor, @Nonnull LObjSrtPredicate<? super T> predicate, short v) {
		return hasSrt(extractor, v, predicate);
	}

	/** 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied). */
	public static @Nonnull <K, T> LFunction<K, String> hasSrtEx(@Nonnull LFunction<K, T> extractor, @Nonnull LObjSrtFunction<? super T, String> specialPredicate, short v) {
		return hasSrtEx(extractor, v, specialPredicate);
	}

	public static @Nonnull <K, T> LPredicate<K> haveFlt(@Nonnull LFunction<K, T> extractor, float v, @Nonnull LObjFltPredicate<? super T> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.apply(k), v);
	}

	/** 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied). */
	public static @Nonnull <K, T> LFunction<K, String> haveFltEx(@Nonnull LFunction<K, T> extractor, float v, @Nonnull LObjFltFunction<? super T, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(extractor.apply(k), v);
	}

	public static @Nonnull <K, T> LPredicate<K> haveFlt(@Nonnull LFunction<K, T> extractor, @Nonnull LObjFltPredicate<? super T> predicate, float v) {
		return haveFlt(extractor, v, predicate);
	}

	/** 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied). */
	public static @Nonnull <K, T> LFunction<K, String> haveFltEx(@Nonnull LFunction<K, T> extractor, @Nonnull LObjFltFunction<? super T, String> specialPredicate, float v) {
		return haveFltEx(extractor, v, specialPredicate);
	}

	public static @Nonnull <K, T> LPredicate<K> hasFlt(@Nonnull LFunction<K, T> extractor, float v, @Nonnull LObjFltPredicate<? super T> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.apply(k), v);
	}

	/** 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied). */
	public static @Nonnull <K, T> LFunction<K, String> hasFltEx(@Nonnull LFunction<K, T> extractor, float v, @Nonnull LObjFltFunction<? super T, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(extractor.apply(k), v);
	}

	public static @Nonnull <K, T> LPredicate<K> hasFlt(@Nonnull LFunction<K, T> extractor, @Nonnull LObjFltPredicate<? super T> predicate, float v) {
		return hasFlt(extractor, v, predicate);
	}

	/** 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied). */
	public static @Nonnull <K, T> LFunction<K, String> hasFltEx(@Nonnull LFunction<K, T> extractor, @Nonnull LObjFltFunction<? super T, String> specialPredicate, float v) {
		return hasFltEx(extractor, v, specialPredicate);
	}

	public static @Nonnull <K, T> LPredicate<K> haveInt(@Nonnull LFunction<K, T> extractor, int v, @Nonnull LObjIntPredicate<? super T> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.apply(k), v);
	}

	/** 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied). */
	public static @Nonnull <K, T> LFunction<K, String> haveIntEx(@Nonnull LFunction<K, T> extractor, int v, @Nonnull LOiFunction<? super T, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(extractor.apply(k), v);
	}

	public static @Nonnull <K, T> LPredicate<K> haveInt(@Nonnull LFunction<K, T> extractor, @Nonnull LObjIntPredicate<? super T> predicate, int v) {
		return haveInt(extractor, v, predicate);
	}

	/** 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied). */
	public static @Nonnull <K, T> LFunction<K, String> haveIntEx(@Nonnull LFunction<K, T> extractor, @Nonnull LOiFunction<? super T, String> specialPredicate, int v) {
		return haveIntEx(extractor, v, specialPredicate);
	}

	public static @Nonnull <K, T> LPredicate<K> hasInt(@Nonnull LFunction<K, T> extractor, int v, @Nonnull LObjIntPredicate<? super T> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.apply(k), v);
	}

	/** 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied). */
	public static @Nonnull <K, T> LFunction<K, String> hasIntEx(@Nonnull LFunction<K, T> extractor, int v, @Nonnull LOiFunction<? super T, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(extractor.apply(k), v);
	}

	public static @Nonnull <K, T> LPredicate<K> hasInt(@Nonnull LFunction<K, T> extractor, @Nonnull LObjIntPredicate<? super T> predicate, int v) {
		return hasInt(extractor, v, predicate);
	}

	/** 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied). */
	public static @Nonnull <K, T> LFunction<K, String> hasIntEx(@Nonnull LFunction<K, T> extractor, @Nonnull LOiFunction<? super T, String> specialPredicate, int v) {
		return hasIntEx(extractor, v, specialPredicate);
	}

	public static @Nonnull <K, T> LPredicate<K> haveLong(@Nonnull LFunction<K, T> extractor, long v, @Nonnull LObjLongPredicate<? super T> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.apply(k), v);
	}

	/** 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied). */
	public static @Nonnull <K, T> LFunction<K, String> haveLongEx(@Nonnull LFunction<K, T> extractor, long v, @Nonnull LObjLongFunction<? super T, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(extractor.apply(k), v);
	}

	public static @Nonnull <K, T> LPredicate<K> haveLong(@Nonnull LFunction<K, T> extractor, @Nonnull LObjLongPredicate<? super T> predicate, long v) {
		return haveLong(extractor, v, predicate);
	}

	/** 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied). */
	public static @Nonnull <K, T> LFunction<K, String> haveLongEx(@Nonnull LFunction<K, T> extractor, @Nonnull LObjLongFunction<? super T, String> specialPredicate, long v) {
		return haveLongEx(extractor, v, specialPredicate);
	}

	public static @Nonnull <K, T> LPredicate<K> hasLong(@Nonnull LFunction<K, T> extractor, long v, @Nonnull LObjLongPredicate<? super T> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.apply(k), v);
	}

	/** 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied). */
	public static @Nonnull <K, T> LFunction<K, String> hasLongEx(@Nonnull LFunction<K, T> extractor, long v, @Nonnull LObjLongFunction<? super T, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(extractor.apply(k), v);
	}

	public static @Nonnull <K, T> LPredicate<K> hasLong(@Nonnull LFunction<K, T> extractor, @Nonnull LObjLongPredicate<? super T> predicate, long v) {
		return hasLong(extractor, v, predicate);
	}

	/** 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied). */
	public static @Nonnull <K, T> LFunction<K, String> hasLongEx(@Nonnull LFunction<K, T> extractor, @Nonnull LObjLongFunction<? super T, String> specialPredicate, long v) {
		return hasLongEx(extractor, v, specialPredicate);
	}

	public static @Nonnull <K, T, V> LPredicate<K> have(@Nonnull LFunction<K, T> extractor, V v, @Nonnull LBiPredicate<? super T, ? super V> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.apply(k), v);
	}

	/** 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied). */
	public static @Nonnull <K, T, V> LFunction<K, String> haveEx(@Nonnull LFunction<K, T> extractor, V v, @Nonnull LBiFunction<? super T, ? super V, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(extractor.apply(k), v);
	}

	public static @Nonnull <K, T, V> LPredicate<K> have(@Nonnull LFunction<K, T> extractor, @Nonnull LBiPredicate<? super T, ? super V> predicate, V v) {
		return have(extractor, v, predicate);
	}

	/** 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied). */
	public static @Nonnull <K, T, V> LFunction<K, String> haveEx(@Nonnull LFunction<K, T> extractor, @Nonnull LBiFunction<? super T, ? super V, String> specialPredicate, V v) {
		return haveEx(extractor, v, specialPredicate);
	}

	public static @Nonnull <K, T, V> LPredicate<K> has(@Nonnull LFunction<K, T> extractor, V v, @Nonnull LBiPredicate<? super T, ? super V> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.apply(k), v);
	}

	/** 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied). */
	public static @Nonnull <K, T, V> LFunction<K, String> hasEx(@Nonnull LFunction<K, T> extractor, V v, @Nonnull LBiFunction<? super T, ? super V, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(extractor.apply(k), v);
	}

	public static @Nonnull <K, T, V> LPredicate<K> has(@Nonnull LFunction<K, T> extractor, @Nonnull LBiPredicate<? super T, ? super V> predicate, V v) {
		return has(extractor, v, predicate);
	}

	/** 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied). */
	public static @Nonnull <K, T, V> LFunction<K, String> hasEx(@Nonnull LFunction<K, T> extractor, @Nonnull LBiFunction<? super T, ? super V, String> specialPredicate, V v) {
		return hasEx(extractor, v, specialPredicate);
	}

	public static @Nonnull <K, T, V2, V3> LPredicate<K> have(@Nonnull LFunction<K, T> extractor, V2 a2, V3 a3, @Nonnull LTriPredicate<? super T, ? super V2, ? super V3> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.apply(k), a2, a3);
	}

	/** 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied). */
	public static @Nonnull <K, T, V2, V3> LFunction<K, String> haveEx(@Nonnull LFunction<K, T> extractor, V2 a2, V3 a3, @Nonnull LTriFunction<? super T, ? super V2, ? super V3, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(extractor.apply(k), a2, a3);
	}

	public static @Nonnull <K, T, V2, V3> LPredicate<K> have(@Nonnull LFunction<K, T> extractor, @Nonnull LTriPredicate<? super T, ? super V2, ? super V3> predicate, V2 a2, V3 a3) {
		return have(extractor, a2, a3, predicate);
	}

	/** 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied). */
	public static @Nonnull <K, T, V2, V3> LFunction<K, String> haveEx(@Nonnull LFunction<K, T> extractor, @Nonnull LTriFunction<? super T, ? super V2, ? super V3, String> specialPredicate, V2 a2, V3 a3) {
		return haveEx(extractor, a2, a3, specialPredicate);
	}

	public static @Nonnull <K, T, V2, V3> LPredicate<K> has(@Nonnull LFunction<K, T> extractor, V2 a2, V3 a3, @Nonnull LTriPredicate<? super T, ? super V2, ? super V3> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.apply(k), a2, a3);
	}

	/** 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied). */
	public static @Nonnull <K, T, V2, V3> LFunction<K, String> hasEx(@Nonnull LFunction<K, T> extractor, V2 a2, V3 a3, @Nonnull LTriFunction<? super T, ? super V2, ? super V3, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(extractor.apply(k), a2, a3);
	}

	public static @Nonnull <K, T, V2, V3> LPredicate<K> has(@Nonnull LFunction<K, T> extractor, @Nonnull LTriPredicate<? super T, ? super V2, ? super V3> predicate, V2 a2, V3 a3) {
		return has(extractor, a2, a3, predicate);
	}

	/** 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied). */
	public static @Nonnull <K, T, V2, V3> LFunction<K, String> hasEx(@Nonnull LFunction<K, T> extractor, @Nonnull LTriFunction<? super T, ? super V2, ? super V3, String> specialPredicate, V2 a2, V3 a3) {
		return hasEx(extractor, a2, a3, specialPredicate);
	}

	public static @Nonnull <K, T, V2, V3, V4> LPredicate<K> have(@Nonnull LFunction<K, T> extractor, V2 a2, V3 a3, V4 a4, @Nonnull LQuadPredicate<? super T, ? super V2, ? super V3, ? super V4> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.apply(k), a2, a3, a4);
	}

	/** 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied). */
	public static @Nonnull <K, T, V2, V3, V4> LFunction<K, String> haveEx(@Nonnull LFunction<K, T> extractor, V2 a2, V3 a3, V4 a4, @Nonnull LQuadFunction<? super T, ? super V2, ? super V3, ? super V4, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(extractor.apply(k), a2, a3, a4);
	}

	public static @Nonnull <K, T, V2, V3, V4> LPredicate<K> have(@Nonnull LFunction<K, T> extractor, @Nonnull LQuadPredicate<? super T, ? super V2, ? super V3, ? super V4> predicate, V2 a2, V3 a3, V4 a4) {
		return have(extractor, a2, a3, a4, predicate);
	}

	/** 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied). */
	public static @Nonnull <K, T, V2, V3, V4> LFunction<K, String> haveEx(@Nonnull LFunction<K, T> extractor, @Nonnull LQuadFunction<? super T, ? super V2, ? super V3, ? super V4, String> specialPredicate, V2 a2, V3 a3, V4 a4) {
		return haveEx(extractor, a2, a3, a4, specialPredicate);
	}

	public static @Nonnull <K, T, V2, V3, V4> LPredicate<K> has(@Nonnull LFunction<K, T> extractor, V2 a2, V3 a3, V4 a4, @Nonnull LQuadPredicate<? super T, ? super V2, ? super V3, ? super V4> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.apply(k), a2, a3, a4);
	}

	/** 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied). */
	public static @Nonnull <K, T, V2, V3, V4> LFunction<K, String> hasEx(@Nonnull LFunction<K, T> extractor, V2 a2, V3 a3, V4 a4, @Nonnull LQuadFunction<? super T, ? super V2, ? super V3, ? super V4, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(extractor.apply(k), a2, a3, a4);
	}

	public static @Nonnull <K, T, V2, V3, V4> LPredicate<K> has(@Nonnull LFunction<K, T> extractor, @Nonnull LQuadPredicate<? super T, ? super V2, ? super V3, ? super V4> predicate, V2 a2, V3 a3, V4 a4) {
		return has(extractor, a2, a3, a4, predicate);
	}

	/** 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied). */
	public static @Nonnull <K, T, V2, V3, V4> LFunction<K, String> hasEx(@Nonnull LFunction<K, T> extractor, @Nonnull LQuadFunction<? super T, ? super V2, ? super V3, ? super V4, String> specialPredicate, V2 a2, V3 a3, V4 a4) {
		return hasEx(extractor, a2, a3, a4, specialPredicate);
	}

	/** 'With' - call of main predicate will be prefixed (contrary to affixed) with additional argument(s). */
	public static @Nonnull <K, T, V1> LPredicate<K> haveWith(@Nonnull LFunction<K, T> extractor, V1 with1, @Nonnull LBiPredicate<? super V1, ? super T> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(with1, extractor.apply(k));
	}

	/**
	* 'With' - call of main predicate will be prefixed (contrary to affixed) with additional argument(s).
	* 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K, T, V1> LFunction<K, String> haveWithEx(@Nonnull LFunction<K, T> extractor, V1 with1, @Nonnull LBiFunction<? super V1, ? super T, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(with1, extractor.apply(k));
	}

	/** 'With' - call of main predicate will be prefixed (contrary to affixed) with additional argument(s). */
	public static @Nonnull <K, T, V1> LPredicate<K> haveWith(@Nonnull LFunction<K, T> extractor, @Nonnull LBiPredicate<? super V1, ? super T> predicate, V1 with1) {
		return haveWith(extractor, with1, predicate);
	}

	/**
	* 'With' - call of main predicate will be prefixed (contrary to affixed) with additional argument(s).
	* 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K, T, V1> LFunction<K, String> haveWithEx(@Nonnull LFunction<K, T> extractor, @Nonnull LBiFunction<? super V1, ? super T, String> specialPredicate, V1 with1) {
		return haveWithEx(extractor, with1, specialPredicate);
	}

	/** 'With' - call of main predicate will be prefixed (contrary to affixed) with additional argument(s). */
	public static @Nonnull <K, T, V1> LPredicate<K> hasWith(@Nonnull LFunction<K, T> extractor, V1 with1, @Nonnull LBiPredicate<? super V1, ? super T> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(with1, extractor.apply(k));
	}

	/**
	* 'With' - call of main predicate will be prefixed (contrary to affixed) with additional argument(s).
	* 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K, T, V1> LFunction<K, String> hasWithEx(@Nonnull LFunction<K, T> extractor, V1 with1, @Nonnull LBiFunction<? super V1, ? super T, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(with1, extractor.apply(k));
	}

	/** 'With' - call of main predicate will be prefixed (contrary to affixed) with additional argument(s). */
	public static @Nonnull <K, T, V1> LPredicate<K> hasWith(@Nonnull LFunction<K, T> extractor, @Nonnull LBiPredicate<? super V1, ? super T> predicate, V1 with1) {
		return hasWith(extractor, with1, predicate);
	}

	/**
	* 'With' - call of main predicate will be prefixed (contrary to affixed) with additional argument(s).
	* 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K, T, V1> LFunction<K, String> hasWithEx(@Nonnull LFunction<K, T> extractor, @Nonnull LBiFunction<? super V1, ? super T, String> specialPredicate, V1 with1) {
		return hasWithEx(extractor, with1, specialPredicate);
	}

	/** 'With' - call of main predicate will be prefixed (contrary to affixed) with additional argument(s). */
	public static @Nonnull <K, T, V1, V2> LPredicate<K> haveWith(@Nonnull LFunction<K, T> extractor, V1 with1, V2 with2, @Nonnull LTriPredicate<? super V1, ? super V2, ? super T> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(with1, with2, extractor.apply(k));
	}

	/**
	* 'With' - call of main predicate will be prefixed (contrary to affixed) with additional argument(s).
	* 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K, T, V1, V2> LFunction<K, String> haveWithEx(@Nonnull LFunction<K, T> extractor, V1 with1, V2 with2, @Nonnull LTriFunction<? super V1, ? super V2, ? super T, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(with1, with2, extractor.apply(k));
	}

	/** 'With' - call of main predicate will be prefixed (contrary to affixed) with additional argument(s). */
	public static @Nonnull <K, T, V1, V2> LPredicate<K> haveWith(@Nonnull LFunction<K, T> extractor, @Nonnull LTriPredicate<? super V1, ? super V2, ? super T> predicate, V1 with1, V2 with2) {
		return haveWith(extractor, with1, with2, predicate);
	}

	/**
	* 'With' - call of main predicate will be prefixed (contrary to affixed) with additional argument(s).
	* 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K, T, V1, V2> LFunction<K, String> haveWithEx(@Nonnull LFunction<K, T> extractor, @Nonnull LTriFunction<? super V1, ? super V2, ? super T, String> specialPredicate, V1 with1, V2 with2) {
		return haveWithEx(extractor, with1, with2, specialPredicate);
	}

	/** 'With' - call of main predicate will be prefixed (contrary to affixed) with additional argument(s). */
	public static @Nonnull <K, T, V1, V2> LPredicate<K> hasWith(@Nonnull LFunction<K, T> extractor, V1 with1, V2 with2, @Nonnull LTriPredicate<? super V1, ? super V2, ? super T> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(with1, with2, extractor.apply(k));
	}

	/**
	* 'With' - call of main predicate will be prefixed (contrary to affixed) with additional argument(s).
	* 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K, T, V1, V2> LFunction<K, String> hasWithEx(@Nonnull LFunction<K, T> extractor, V1 with1, V2 with2, @Nonnull LTriFunction<? super V1, ? super V2, ? super T, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(with1, with2, extractor.apply(k));
	}

	/** 'With' - call of main predicate will be prefixed (contrary to affixed) with additional argument(s). */
	public static @Nonnull <K, T, V1, V2> LPredicate<K> hasWith(@Nonnull LFunction<K, T> extractor, @Nonnull LTriPredicate<? super V1, ? super V2, ? super T> predicate, V1 with1, V2 with2) {
		return hasWith(extractor, with1, with2, predicate);
	}

	/**
	* 'With' - call of main predicate will be prefixed (contrary to affixed) with additional argument(s).
	* 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K, T, V1, V2> LFunction<K, String> hasWithEx(@Nonnull LFunction<K, T> extractor, @Nonnull LTriFunction<? super V1, ? super V2, ? super T, String> specialPredicate, V1 with1, V2 with2) {
		return hasWithEx(extractor, with1, with2, specialPredicate);
	}

	/** 'With' - call of main predicate will be prefixed (contrary to affixed) with additional argument(s). */
	public static @Nonnull <K, T> LPredicate<K> uniHaveWith(@Nonnull LFunction<K, T> extractor, T with, @Nonnull LBiPredicate<? super T, ? super T> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(with, extractor.apply(k));
	}

	/**
	* 'With' - call of main predicate will be prefixed (contrary to affixed) with additional argument(s).
	* 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K, T> LFunction<K, String> uniHaveWithEx(@Nonnull LFunction<K, T> extractor, T with, @Nonnull LBiFunction<? super T, ? super T, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(with, extractor.apply(k));
	}

	/** 'With' - call of main predicate will be prefixed (contrary to affixed) with additional argument(s). */
	public static @Nonnull <K, T> LPredicate<K> uniHaveWith(@Nonnull LFunction<K, T> extractor, @Nonnull LBiPredicate<? super T, ? super T> predicate, T with) {
		return uniHaveWith(extractor, with, predicate);
	}

	/**
	* 'With' - call of main predicate will be prefixed (contrary to affixed) with additional argument(s).
	* 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K, T> LFunction<K, String> uniHaveWithEx(@Nonnull LFunction<K, T> extractor, @Nonnull LBiFunction<? super T, ? super T, String> specialPredicate, T with) {
		return uniHaveWithEx(extractor, with, specialPredicate);
	}

	/** 'With' - call of main predicate will be prefixed (contrary to affixed) with additional argument(s). */
	public static @Nonnull <K, T> LPredicate<K> uniHasWith(@Nonnull LFunction<K, T> extractor, T with, @Nonnull LBiPredicate<? super T, ? super T> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(with, extractor.apply(k));
	}

	/**
	* 'With' - call of main predicate will be prefixed (contrary to affixed) with additional argument(s).
	* 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K, T> LFunction<K, String> uniHasWithEx(@Nonnull LFunction<K, T> extractor, T with, @Nonnull LBiFunction<? super T, ? super T, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(with, extractor.apply(k));
	}

	/** 'With' - call of main predicate will be prefixed (contrary to affixed) with additional argument(s). */
	public static @Nonnull <K, T> LPredicate<K> uniHasWith(@Nonnull LFunction<K, T> extractor, @Nonnull LBiPredicate<? super T, ? super T> predicate, T with) {
		return uniHasWith(extractor, with, predicate);
	}

	/**
	* 'With' - call of main predicate will be prefixed (contrary to affixed) with additional argument(s).
	* 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K, T> LFunction<K, String> uniHasWithEx(@Nonnull LFunction<K, T> extractor, @Nonnull LBiFunction<? super T, ? super T, String> specialPredicate, T with) {
		return uniHasWithEx(extractor, with, specialPredicate);
	}

	/** 'ToByte' - first, actual value will be converted to primitive type (contrary to the object). */
	public static @Nonnull <K> LPredicate<K> haveToByte(@Nonnull LToByteFunction<K> extractor, @Nonnull LBytePredicate predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.applyAsByte(k));
	}

	/**
	* 'ToByte' - first, actual value will be converted to primitive type (contrary to the object).
	* 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K> LFunction<K, String> haveToByteEx(@Nonnull LToByteFunction<K> extractor, @Nonnull LByteFunction<String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(extractor.applyAsByte(k));
	}

	/** 'ToByte' - first, actual value will be converted to primitive type (contrary to the object). */
	public static @Nonnull <K> LPredicate<K> hasToByte(@Nonnull LToByteFunction<K> extractor, @Nonnull LBytePredicate predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.applyAsByte(k));
	}

	/**
	* 'ToByte' - first, actual value will be converted to primitive type (contrary to the object).
	* 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K> LFunction<K, String> hasToByteEx(@Nonnull LToByteFunction<K> extractor, @Nonnull LByteFunction<String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(extractor.applyAsByte(k));
	}

	/** 'ToByte' - first, actual value will be converted to primitive type (contrary to the object). */
	public static @Nonnull <K> LPredicate<K> haveToByte(@Nonnull LToByteFunction<K> extractor, byte a2, @Nonnull LBiBytePredicate predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.applyAsByte(k), a2);
	}

	/**
	* 'ToByte' - first, actual value will be converted to primitive type (contrary to the object).
	* 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K> LFunction<K, String> haveToByteEx(@Nonnull LToByteFunction<K> extractor, byte a2, @Nonnull LBiByteFunction<String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(extractor.applyAsByte(k), a2);
	}

	/** 'ToByte' - first, actual value will be converted to primitive type (contrary to the object). */
	public static @Nonnull <K> LPredicate<K> haveToByte(@Nonnull LToByteFunction<K> extractor, @Nonnull LBiBytePredicate predicate, byte a2) {
		return haveToByte(extractor, a2, predicate);
	}

	/**
	* 'ToByte' - first, actual value will be converted to primitive type (contrary to the object).
	* 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K> LFunction<K, String> haveToByteEx(@Nonnull LToByteFunction<K> extractor, @Nonnull LBiByteFunction<String> specialPredicate, byte a2) {
		return haveToByteEx(extractor, a2, specialPredicate);
	}

	/** 'ToByte' - first, actual value will be converted to primitive type (contrary to the object). */
	public static @Nonnull <K> LPredicate<K> hasToByte(@Nonnull LToByteFunction<K> extractor, byte a2, @Nonnull LBiBytePredicate predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.applyAsByte(k), a2);
	}

	/**
	* 'ToByte' - first, actual value will be converted to primitive type (contrary to the object).
	* 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K> LFunction<K, String> hasToByteEx(@Nonnull LToByteFunction<K> extractor, byte a2, @Nonnull LBiByteFunction<String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(extractor.applyAsByte(k), a2);
	}

	/** 'ToByte' - first, actual value will be converted to primitive type (contrary to the object). */
	public static @Nonnull <K> LPredicate<K> hasToByte(@Nonnull LToByteFunction<K> extractor, @Nonnull LBiBytePredicate predicate, byte a2) {
		return hasToByte(extractor, a2, predicate);
	}

	/**
	* 'ToByte' - first, actual value will be converted to primitive type (contrary to the object).
	* 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K> LFunction<K, String> hasToByteEx(@Nonnull LToByteFunction<K> extractor, @Nonnull LBiByteFunction<String> specialPredicate, byte a2) {
		return hasToByteEx(extractor, a2, specialPredicate);
	}

	/** 'ToByte' - first, actual value will be converted to primitive type (contrary to the object). */
	public static @Nonnull <K> LPredicate<K> haveToByte(@Nonnull LToByteFunction<K> extractor, byte a2, byte a3, @Nonnull LTriBytePredicate predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.applyAsByte(k), a2, a3);
	}

	/** 'ToByte' - first, actual value will be converted to primitive type (contrary to the object). */
	public static @Nonnull <K> LPredicate<K> haveToByte(@Nonnull LToByteFunction<K> extractor, @Nonnull LTriBytePredicate predicate, byte a2, byte a3) {
		return haveToByte(extractor, a2, a3, predicate);
	}

	/** 'ToByte' - first, actual value will be converted to primitive type (contrary to the object). */
	public static @Nonnull <K> LPredicate<K> hasToByte(@Nonnull LToByteFunction<K> extractor, byte a2, byte a3, @Nonnull LTriBytePredicate predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.applyAsByte(k), a2, a3);
	}

	/** 'ToByte' - first, actual value will be converted to primitive type (contrary to the object). */
	public static @Nonnull <K> LPredicate<K> hasToByte(@Nonnull LToByteFunction<K> extractor, @Nonnull LTriBytePredicate predicate, byte a2, byte a3) {
		return hasToByte(extractor, a2, a3, predicate);
	}

	/** 'ToByte' - first, actual value will be converted to primitive type (contrary to the object). */
	public static @Nonnull <K> LPredicate<K> haveToByteInt(@Nonnull LToByteFunction<K> extractor, int v, @Nonnull LByteIntPredicate predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.applyAsByte(k), v);
	}

	/** 'ToByte' - first, actual value will be converted to primitive type (contrary to the object). */
	public static @Nonnull <K> LPredicate<K> haveToByteInt(@Nonnull LToByteFunction<K> extractor, @Nonnull LByteIntPredicate predicate, int v) {
		return haveToByteInt(extractor, v, predicate);
	}

	/** 'ToByte' - first, actual value will be converted to primitive type (contrary to the object). */
	public static @Nonnull <K> LPredicate<K> hasToByteInt(@Nonnull LToByteFunction<K> extractor, int v, @Nonnull LByteIntPredicate predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.applyAsByte(k), v);
	}

	/** 'ToByte' - first, actual value will be converted to primitive type (contrary to the object). */
	public static @Nonnull <K> LPredicate<K> hasToByteInt(@Nonnull LToByteFunction<K> extractor, @Nonnull LByteIntPredicate predicate, int v) {
		return hasToByteInt(extractor, v, predicate);
	}

	/**
	* 'ToByte' - first, actual value will be converted to primitive type (contrary to the object).
	* '_' - the predicate function is a variant or arguments represented by other function.
	*/
	public static @Nonnull <K, V> LPredicate<K> haveToByte_(@Nonnull LToByteFunction<K> extractor, V v, @Nonnull LObjBytePredicate.LByteObjPred<? super V> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.testByteObj(extractor.applyAsByte(k), v);
	}

	/**
	* 'ToByte' - first, actual value will be converted to primitive type (contrary to the object).
	* 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	* '_' - the predicate function is a variant or arguments represented by other function.
	*/
	public static @Nonnull <K, V> LFunction<K, String> haveToByteEx_(@Nonnull LToByteFunction<K> extractor, V v, @Nonnull LObjByteFunction.LByteObjFunc<? super V, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.applyByteObj(extractor.applyAsByte(k), v);
	}

	/**
	* 'ToByte' - first, actual value will be converted to primitive type (contrary to the object).
	* '_' - the predicate function is a variant or arguments represented by other function.
	*/
	public static @Nonnull <K, V> LPredicate<K> haveToByte_(@Nonnull LToByteFunction<K> extractor, @Nonnull LObjBytePredicate.LByteObjPred<? super V> predicate, V v) {
		return haveToByte_(extractor, v, predicate);
	}

	/**
	* 'ToByte' - first, actual value will be converted to primitive type (contrary to the object).
	* 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	* '_' - the predicate function is a variant or arguments represented by other function.
	*/
	public static @Nonnull <K, V> LFunction<K, String> haveToByteEx_(@Nonnull LToByteFunction<K> extractor, @Nonnull LObjByteFunction.LByteObjFunc<? super V, String> specialPredicate, V v) {
		return haveToByteEx_(extractor, v, specialPredicate);
	}

	/**
	* 'ToByte' - first, actual value will be converted to primitive type (contrary to the object).
	* '_' - the predicate function is a variant or arguments represented by other function.
	*/
	public static @Nonnull <K, V> LPredicate<K> hasToByte_(@Nonnull LToByteFunction<K> extractor, V v, @Nonnull LObjBytePredicate.LByteObjPred<? super V> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.testByteObj(extractor.applyAsByte(k), v);
	}

	/**
	* 'ToByte' - first, actual value will be converted to primitive type (contrary to the object).
	* 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	* '_' - the predicate function is a variant or arguments represented by other function.
	*/
	public static @Nonnull <K, V> LFunction<K, String> hasToByteEx_(@Nonnull LToByteFunction<K> extractor, V v, @Nonnull LObjByteFunction.LByteObjFunc<? super V, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.applyByteObj(extractor.applyAsByte(k), v);
	}

	/**
	* 'ToByte' - first, actual value will be converted to primitive type (contrary to the object).
	* '_' - the predicate function is a variant or arguments represented by other function.
	*/
	public static @Nonnull <K, V> LPredicate<K> hasToByte_(@Nonnull LToByteFunction<K> extractor, @Nonnull LObjBytePredicate.LByteObjPred<? super V> predicate, V v) {
		return hasToByte_(extractor, v, predicate);
	}

	/**
	* 'ToByte' - first, actual value will be converted to primitive type (contrary to the object).
	* 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	* '_' - the predicate function is a variant or arguments represented by other function.
	*/
	public static @Nonnull <K, V> LFunction<K, String> hasToByteEx_(@Nonnull LToByteFunction<K> extractor, @Nonnull LObjByteFunction.LByteObjFunc<? super V, String> specialPredicate, V v) {
		return hasToByteEx_(extractor, v, specialPredicate);
	}

	/**
	* 'ToByte' - first, actual value will be converted to primitive type (contrary to the object).
	* 'With' - call of main predicate will be prefixed (contrary to affixed) with additional argument(s).
	*/
	public static @Nonnull <K, V1> LPredicate<K> haveToByteWith(@Nonnull LToByteFunction<K> extractor, V1 with1, @Nonnull LObjBytePredicate<? super V1> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(with1, extractor.applyAsByte(k));
	}

	/**
	* 'ToByte' - first, actual value will be converted to primitive type (contrary to the object).
	* 'With' - call of main predicate will be prefixed (contrary to affixed) with additional argument(s).
	* 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K, V1> LFunction<K, String> haveToByteWithEx(@Nonnull LToByteFunction<K> extractor, V1 with1, @Nonnull LObjByteFunction<? super V1, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(with1, extractor.applyAsByte(k));
	}

	/**
	* 'ToByte' - first, actual value will be converted to primitive type (contrary to the object).
	* 'With' - call of main predicate will be prefixed (contrary to affixed) with additional argument(s).
	*/
	public static @Nonnull <K, V1> LPredicate<K> haveToByteWith(@Nonnull LToByteFunction<K> extractor, @Nonnull LObjBytePredicate<? super V1> predicate, V1 with1) {
		return haveToByteWith(extractor, with1, predicate);
	}

	/**
	* 'ToByte' - first, actual value will be converted to primitive type (contrary to the object).
	* 'With' - call of main predicate will be prefixed (contrary to affixed) with additional argument(s).
	* 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K, V1> LFunction<K, String> haveToByteWithEx(@Nonnull LToByteFunction<K> extractor, @Nonnull LObjByteFunction<? super V1, String> specialPredicate, V1 with1) {
		return haveToByteWithEx(extractor, with1, specialPredicate);
	}

	/**
	* 'ToByte' - first, actual value will be converted to primitive type (contrary to the object).
	* 'With' - call of main predicate will be prefixed (contrary to affixed) with additional argument(s).
	*/
	public static @Nonnull <K, V1> LPredicate<K> hasToByteWith(@Nonnull LToByteFunction<K> extractor, V1 with1, @Nonnull LObjBytePredicate<? super V1> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(with1, extractor.applyAsByte(k));
	}

	/**
	* 'ToByte' - first, actual value will be converted to primitive type (contrary to the object).
	* 'With' - call of main predicate will be prefixed (contrary to affixed) with additional argument(s).
	* 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K, V1> LFunction<K, String> hasToByteWithEx(@Nonnull LToByteFunction<K> extractor, V1 with1, @Nonnull LObjByteFunction<? super V1, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(with1, extractor.applyAsByte(k));
	}

	/**
	* 'ToByte' - first, actual value will be converted to primitive type (contrary to the object).
	* 'With' - call of main predicate will be prefixed (contrary to affixed) with additional argument(s).
	*/
	public static @Nonnull <K, V1> LPredicate<K> hasToByteWith(@Nonnull LToByteFunction<K> extractor, @Nonnull LObjBytePredicate<? super V1> predicate, V1 with1) {
		return hasToByteWith(extractor, with1, predicate);
	}

	/**
	* 'ToByte' - first, actual value will be converted to primitive type (contrary to the object).
	* 'With' - call of main predicate will be prefixed (contrary to affixed) with additional argument(s).
	* 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K, V1> LFunction<K, String> hasToByteWithEx(@Nonnull LToByteFunction<K> extractor, @Nonnull LObjByteFunction<? super V1, String> specialPredicate, V1 with1) {
		return hasToByteWithEx(extractor, with1, specialPredicate);
	}

	/**
	* 'ToByte' - first, actual value will be converted to primitive type (contrary to the object).
	* 'With' - call of main predicate will be prefixed (contrary to affixed) with additional argument(s).
	*/
	public static @Nonnull <K, V1, V2> LPredicate<K> haveToByteWith(@Nonnull LToByteFunction<K> extractor, V1 with1, V2 with2, @Nonnull LBiObjBytePredicate<? super V1, ? super V2> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(with1, with2, extractor.applyAsByte(k));
	}

	/**
	* 'ToByte' - first, actual value will be converted to primitive type (contrary to the object).
	* 'With' - call of main predicate will be prefixed (contrary to affixed) with additional argument(s).
	* 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K, V1, V2> LFunction<K, String> haveToByteWithEx(@Nonnull LToByteFunction<K> extractor, V1 with1, V2 with2, @Nonnull LBiObjByteFunction<? super V1, ? super V2, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(with1, with2, extractor.applyAsByte(k));
	}

	/**
	* 'ToByte' - first, actual value will be converted to primitive type (contrary to the object).
	* 'With' - call of main predicate will be prefixed (contrary to affixed) with additional argument(s).
	*/
	public static @Nonnull <K, V1, V2> LPredicate<K> haveToByteWith(@Nonnull LToByteFunction<K> extractor, @Nonnull LBiObjBytePredicate<? super V1, ? super V2> predicate, V1 with1, V2 with2) {
		return haveToByteWith(extractor, with1, with2, predicate);
	}

	/**
	* 'ToByte' - first, actual value will be converted to primitive type (contrary to the object).
	* 'With' - call of main predicate will be prefixed (contrary to affixed) with additional argument(s).
	* 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K, V1, V2> LFunction<K, String> haveToByteWithEx(@Nonnull LToByteFunction<K> extractor, @Nonnull LBiObjByteFunction<? super V1, ? super V2, String> specialPredicate, V1 with1, V2 with2) {
		return haveToByteWithEx(extractor, with1, with2, specialPredicate);
	}

	/**
	* 'ToByte' - first, actual value will be converted to primitive type (contrary to the object).
	* 'With' - call of main predicate will be prefixed (contrary to affixed) with additional argument(s).
	*/
	public static @Nonnull <K, V1, V2> LPredicate<K> hasToByteWith(@Nonnull LToByteFunction<K> extractor, V1 with1, V2 with2, @Nonnull LBiObjBytePredicate<? super V1, ? super V2> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(with1, with2, extractor.applyAsByte(k));
	}

	/**
	* 'ToByte' - first, actual value will be converted to primitive type (contrary to the object).
	* 'With' - call of main predicate will be prefixed (contrary to affixed) with additional argument(s).
	* 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K, V1, V2> LFunction<K, String> hasToByteWithEx(@Nonnull LToByteFunction<K> extractor, V1 with1, V2 with2, @Nonnull LBiObjByteFunction<? super V1, ? super V2, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(with1, with2, extractor.applyAsByte(k));
	}

	/**
	* 'ToByte' - first, actual value will be converted to primitive type (contrary to the object).
	* 'With' - call of main predicate will be prefixed (contrary to affixed) with additional argument(s).
	*/
	public static @Nonnull <K, V1, V2> LPredicate<K> hasToByteWith(@Nonnull LToByteFunction<K> extractor, @Nonnull LBiObjBytePredicate<? super V1, ? super V2> predicate, V1 with1, V2 with2) {
		return hasToByteWith(extractor, with1, with2, predicate);
	}

	/**
	* 'ToByte' - first, actual value will be converted to primitive type (contrary to the object).
	* 'With' - call of main predicate will be prefixed (contrary to affixed) with additional argument(s).
	* 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K, V1, V2> LFunction<K, String> hasToByteWithEx(@Nonnull LToByteFunction<K> extractor, @Nonnull LBiObjByteFunction<? super V1, ? super V2, String> specialPredicate, V1 with1, V2 with2) {
		return hasToByteWithEx(extractor, with1, with2, specialPredicate);
	}

	/** 'ToDbl' - first, actual value will be converted to primitive type (contrary to the object). */
	public static @Nonnull <K> LPredicate<K> haveToDbl(@Nonnull LToDblFunction<K> extractor, @Nonnull LDblPredicate predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.applyAsDbl(k));
	}

	/**
	* 'ToDbl' - first, actual value will be converted to primitive type (contrary to the object).
	* 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K> LFunction<K, String> haveToDblEx(@Nonnull LToDblFunction<K> extractor, @Nonnull LDblFunction<String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(extractor.applyAsDbl(k));
	}

	/** 'ToDbl' - first, actual value will be converted to primitive type (contrary to the object). */
	public static @Nonnull <K> LPredicate<K> hasToDbl(@Nonnull LToDblFunction<K> extractor, @Nonnull LDblPredicate predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.applyAsDbl(k));
	}

	/**
	* 'ToDbl' - first, actual value will be converted to primitive type (contrary to the object).
	* 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K> LFunction<K, String> hasToDblEx(@Nonnull LToDblFunction<K> extractor, @Nonnull LDblFunction<String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(extractor.applyAsDbl(k));
	}

	/** 'ToDbl' - first, actual value will be converted to primitive type (contrary to the object). */
	public static @Nonnull <K> LPredicate<K> haveToDbl(@Nonnull LToDblFunction<K> extractor, double a2, @Nonnull LBiDblPredicate predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.applyAsDbl(k), a2);
	}

	/**
	* 'ToDbl' - first, actual value will be converted to primitive type (contrary to the object).
	* 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K> LFunction<K, String> haveToDblEx(@Nonnull LToDblFunction<K> extractor, double a2, @Nonnull LBiDblFunction<String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(extractor.applyAsDbl(k), a2);
	}

	/** 'ToDbl' - first, actual value will be converted to primitive type (contrary to the object). */
	public static @Nonnull <K> LPredicate<K> haveToDbl(@Nonnull LToDblFunction<K> extractor, @Nonnull LBiDblPredicate predicate, double a2) {
		return haveToDbl(extractor, a2, predicate);
	}

	/**
	* 'ToDbl' - first, actual value will be converted to primitive type (contrary to the object).
	* 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K> LFunction<K, String> haveToDblEx(@Nonnull LToDblFunction<K> extractor, @Nonnull LBiDblFunction<String> specialPredicate, double a2) {
		return haveToDblEx(extractor, a2, specialPredicate);
	}

	/** 'ToDbl' - first, actual value will be converted to primitive type (contrary to the object). */
	public static @Nonnull <K> LPredicate<K> hasToDbl(@Nonnull LToDblFunction<K> extractor, double a2, @Nonnull LBiDblPredicate predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.applyAsDbl(k), a2);
	}

	/**
	* 'ToDbl' - first, actual value will be converted to primitive type (contrary to the object).
	* 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K> LFunction<K, String> hasToDblEx(@Nonnull LToDblFunction<K> extractor, double a2, @Nonnull LBiDblFunction<String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(extractor.applyAsDbl(k), a2);
	}

	/** 'ToDbl' - first, actual value will be converted to primitive type (contrary to the object). */
	public static @Nonnull <K> LPredicate<K> hasToDbl(@Nonnull LToDblFunction<K> extractor, @Nonnull LBiDblPredicate predicate, double a2) {
		return hasToDbl(extractor, a2, predicate);
	}

	/**
	* 'ToDbl' - first, actual value will be converted to primitive type (contrary to the object).
	* 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K> LFunction<K, String> hasToDblEx(@Nonnull LToDblFunction<K> extractor, @Nonnull LBiDblFunction<String> specialPredicate, double a2) {
		return hasToDblEx(extractor, a2, specialPredicate);
	}

	/** 'ToDbl' - first, actual value will be converted to primitive type (contrary to the object). */
	public static @Nonnull <K> LPredicate<K> haveToDbl(@Nonnull LToDblFunction<K> extractor, double a2, double a3, @Nonnull LTriDblPredicate predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.applyAsDbl(k), a2, a3);
	}

	/** 'ToDbl' - first, actual value will be converted to primitive type (contrary to the object). */
	public static @Nonnull <K> LPredicate<K> haveToDbl(@Nonnull LToDblFunction<K> extractor, @Nonnull LTriDblPredicate predicate, double a2, double a3) {
		return haveToDbl(extractor, a2, a3, predicate);
	}

	/** 'ToDbl' - first, actual value will be converted to primitive type (contrary to the object). */
	public static @Nonnull <K> LPredicate<K> hasToDbl(@Nonnull LToDblFunction<K> extractor, double a2, double a3, @Nonnull LTriDblPredicate predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.applyAsDbl(k), a2, a3);
	}

	/** 'ToDbl' - first, actual value will be converted to primitive type (contrary to the object). */
	public static @Nonnull <K> LPredicate<K> hasToDbl(@Nonnull LToDblFunction<K> extractor, @Nonnull LTriDblPredicate predicate, double a2, double a3) {
		return hasToDbl(extractor, a2, a3, predicate);
	}

	/** 'ToDbl' - first, actual value will be converted to primitive type (contrary to the object). */
	public static @Nonnull <K> LPredicate<K> haveToDblInt(@Nonnull LToDblFunction<K> extractor, int v, @Nonnull LDblIntPredicate predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.applyAsDbl(k), v);
	}

	/** 'ToDbl' - first, actual value will be converted to primitive type (contrary to the object). */
	public static @Nonnull <K> LPredicate<K> haveToDblInt(@Nonnull LToDblFunction<K> extractor, @Nonnull LDblIntPredicate predicate, int v) {
		return haveToDblInt(extractor, v, predicate);
	}

	/** 'ToDbl' - first, actual value will be converted to primitive type (contrary to the object). */
	public static @Nonnull <K> LPredicate<K> hasToDblInt(@Nonnull LToDblFunction<K> extractor, int v, @Nonnull LDblIntPredicate predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.applyAsDbl(k), v);
	}

	/** 'ToDbl' - first, actual value will be converted to primitive type (contrary to the object). */
	public static @Nonnull <K> LPredicate<K> hasToDblInt(@Nonnull LToDblFunction<K> extractor, @Nonnull LDblIntPredicate predicate, int v) {
		return hasToDblInt(extractor, v, predicate);
	}

	/**
	* 'ToDbl' - first, actual value will be converted to primitive type (contrary to the object).
	* '_' - the predicate function is a variant or arguments represented by other function.
	*/
	public static @Nonnull <K, V> LPredicate<K> haveToDbl_(@Nonnull LToDblFunction<K> extractor, V v, @Nonnull LObjDblPredicate.LDblObjPred<? super V> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.testDblObj(extractor.applyAsDbl(k), v);
	}

	/**
	* 'ToDbl' - first, actual value will be converted to primitive type (contrary to the object).
	* 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	* '_' - the predicate function is a variant or arguments represented by other function.
	*/
	public static @Nonnull <K, V> LFunction<K, String> haveToDblEx_(@Nonnull LToDblFunction<K> extractor, V v, @Nonnull LObjDblFunction.LDblObjFunc<? super V, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.applyDblObj(extractor.applyAsDbl(k), v);
	}

	/**
	* 'ToDbl' - first, actual value will be converted to primitive type (contrary to the object).
	* '_' - the predicate function is a variant or arguments represented by other function.
	*/
	public static @Nonnull <K, V> LPredicate<K> haveToDbl_(@Nonnull LToDblFunction<K> extractor, @Nonnull LObjDblPredicate.LDblObjPred<? super V> predicate, V v) {
		return haveToDbl_(extractor, v, predicate);
	}

	/**
	* 'ToDbl' - first, actual value will be converted to primitive type (contrary to the object).
	* 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	* '_' - the predicate function is a variant or arguments represented by other function.
	*/
	public static @Nonnull <K, V> LFunction<K, String> haveToDblEx_(@Nonnull LToDblFunction<K> extractor, @Nonnull LObjDblFunction.LDblObjFunc<? super V, String> specialPredicate, V v) {
		return haveToDblEx_(extractor, v, specialPredicate);
	}

	/**
	* 'ToDbl' - first, actual value will be converted to primitive type (contrary to the object).
	* '_' - the predicate function is a variant or arguments represented by other function.
	*/
	public static @Nonnull <K, V> LPredicate<K> hasToDbl_(@Nonnull LToDblFunction<K> extractor, V v, @Nonnull LObjDblPredicate.LDblObjPred<? super V> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.testDblObj(extractor.applyAsDbl(k), v);
	}

	/**
	* 'ToDbl' - first, actual value will be converted to primitive type (contrary to the object).
	* 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	* '_' - the predicate function is a variant or arguments represented by other function.
	*/
	public static @Nonnull <K, V> LFunction<K, String> hasToDblEx_(@Nonnull LToDblFunction<K> extractor, V v, @Nonnull LObjDblFunction.LDblObjFunc<? super V, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.applyDblObj(extractor.applyAsDbl(k), v);
	}

	/**
	* 'ToDbl' - first, actual value will be converted to primitive type (contrary to the object).
	* '_' - the predicate function is a variant or arguments represented by other function.
	*/
	public static @Nonnull <K, V> LPredicate<K> hasToDbl_(@Nonnull LToDblFunction<K> extractor, @Nonnull LObjDblPredicate.LDblObjPred<? super V> predicate, V v) {
		return hasToDbl_(extractor, v, predicate);
	}

	/**
	* 'ToDbl' - first, actual value will be converted to primitive type (contrary to the object).
	* 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	* '_' - the predicate function is a variant or arguments represented by other function.
	*/
	public static @Nonnull <K, V> LFunction<K, String> hasToDblEx_(@Nonnull LToDblFunction<K> extractor, @Nonnull LObjDblFunction.LDblObjFunc<? super V, String> specialPredicate, V v) {
		return hasToDblEx_(extractor, v, specialPredicate);
	}

	/**
	* 'ToDbl' - first, actual value will be converted to primitive type (contrary to the object).
	* 'With' - call of main predicate will be prefixed (contrary to affixed) with additional argument(s).
	*/
	public static @Nonnull <K, V1> LPredicate<K> haveToDblWith(@Nonnull LToDblFunction<K> extractor, V1 with1, @Nonnull LObjDblPredicate<? super V1> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(with1, extractor.applyAsDbl(k));
	}

	/**
	* 'ToDbl' - first, actual value will be converted to primitive type (contrary to the object).
	* 'With' - call of main predicate will be prefixed (contrary to affixed) with additional argument(s).
	* 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K, V1> LFunction<K, String> haveToDblWithEx(@Nonnull LToDblFunction<K> extractor, V1 with1, @Nonnull LObjDblFunction<? super V1, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(with1, extractor.applyAsDbl(k));
	}

	/**
	* 'ToDbl' - first, actual value will be converted to primitive type (contrary to the object).
	* 'With' - call of main predicate will be prefixed (contrary to affixed) with additional argument(s).
	*/
	public static @Nonnull <K, V1> LPredicate<K> haveToDblWith(@Nonnull LToDblFunction<K> extractor, @Nonnull LObjDblPredicate<? super V1> predicate, V1 with1) {
		return haveToDblWith(extractor, with1, predicate);
	}

	/**
	* 'ToDbl' - first, actual value will be converted to primitive type (contrary to the object).
	* 'With' - call of main predicate will be prefixed (contrary to affixed) with additional argument(s).
	* 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K, V1> LFunction<K, String> haveToDblWithEx(@Nonnull LToDblFunction<K> extractor, @Nonnull LObjDblFunction<? super V1, String> specialPredicate, V1 with1) {
		return haveToDblWithEx(extractor, with1, specialPredicate);
	}

	/**
	* 'ToDbl' - first, actual value will be converted to primitive type (contrary to the object).
	* 'With' - call of main predicate will be prefixed (contrary to affixed) with additional argument(s).
	*/
	public static @Nonnull <K, V1> LPredicate<K> hasToDblWith(@Nonnull LToDblFunction<K> extractor, V1 with1, @Nonnull LObjDblPredicate<? super V1> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(with1, extractor.applyAsDbl(k));
	}

	/**
	* 'ToDbl' - first, actual value will be converted to primitive type (contrary to the object).
	* 'With' - call of main predicate will be prefixed (contrary to affixed) with additional argument(s).
	* 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K, V1> LFunction<K, String> hasToDblWithEx(@Nonnull LToDblFunction<K> extractor, V1 with1, @Nonnull LObjDblFunction<? super V1, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(with1, extractor.applyAsDbl(k));
	}

	/**
	* 'ToDbl' - first, actual value will be converted to primitive type (contrary to the object).
	* 'With' - call of main predicate will be prefixed (contrary to affixed) with additional argument(s).
	*/
	public static @Nonnull <K, V1> LPredicate<K> hasToDblWith(@Nonnull LToDblFunction<K> extractor, @Nonnull LObjDblPredicate<? super V1> predicate, V1 with1) {
		return hasToDblWith(extractor, with1, predicate);
	}

	/**
	* 'ToDbl' - first, actual value will be converted to primitive type (contrary to the object).
	* 'With' - call of main predicate will be prefixed (contrary to affixed) with additional argument(s).
	* 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K, V1> LFunction<K, String> hasToDblWithEx(@Nonnull LToDblFunction<K> extractor, @Nonnull LObjDblFunction<? super V1, String> specialPredicate, V1 with1) {
		return hasToDblWithEx(extractor, with1, specialPredicate);
	}

	/**
	* 'ToDbl' - first, actual value will be converted to primitive type (contrary to the object).
	* 'With' - call of main predicate will be prefixed (contrary to affixed) with additional argument(s).
	*/
	public static @Nonnull <K, V1, V2> LPredicate<K> haveToDblWith(@Nonnull LToDblFunction<K> extractor, V1 with1, V2 with2, @Nonnull LBiObjDblPredicate<? super V1, ? super V2> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(with1, with2, extractor.applyAsDbl(k));
	}

	/**
	* 'ToDbl' - first, actual value will be converted to primitive type (contrary to the object).
	* 'With' - call of main predicate will be prefixed (contrary to affixed) with additional argument(s).
	* 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K, V1, V2> LFunction<K, String> haveToDblWithEx(@Nonnull LToDblFunction<K> extractor, V1 with1, V2 with2, @Nonnull LBiObjDblFunction<? super V1, ? super V2, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(with1, with2, extractor.applyAsDbl(k));
	}

	/**
	* 'ToDbl' - first, actual value will be converted to primitive type (contrary to the object).
	* 'With' - call of main predicate will be prefixed (contrary to affixed) with additional argument(s).
	*/
	public static @Nonnull <K, V1, V2> LPredicate<K> haveToDblWith(@Nonnull LToDblFunction<K> extractor, @Nonnull LBiObjDblPredicate<? super V1, ? super V2> predicate, V1 with1, V2 with2) {
		return haveToDblWith(extractor, with1, with2, predicate);
	}

	/**
	* 'ToDbl' - first, actual value will be converted to primitive type (contrary to the object).
	* 'With' - call of main predicate will be prefixed (contrary to affixed) with additional argument(s).
	* 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K, V1, V2> LFunction<K, String> haveToDblWithEx(@Nonnull LToDblFunction<K> extractor, @Nonnull LBiObjDblFunction<? super V1, ? super V2, String> specialPredicate, V1 with1, V2 with2) {
		return haveToDblWithEx(extractor, with1, with2, specialPredicate);
	}

	/**
	* 'ToDbl' - first, actual value will be converted to primitive type (contrary to the object).
	* 'With' - call of main predicate will be prefixed (contrary to affixed) with additional argument(s).
	*/
	public static @Nonnull <K, V1, V2> LPredicate<K> hasToDblWith(@Nonnull LToDblFunction<K> extractor, V1 with1, V2 with2, @Nonnull LBiObjDblPredicate<? super V1, ? super V2> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(with1, with2, extractor.applyAsDbl(k));
	}

	/**
	* 'ToDbl' - first, actual value will be converted to primitive type (contrary to the object).
	* 'With' - call of main predicate will be prefixed (contrary to affixed) with additional argument(s).
	* 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K, V1, V2> LFunction<K, String> hasToDblWithEx(@Nonnull LToDblFunction<K> extractor, V1 with1, V2 with2, @Nonnull LBiObjDblFunction<? super V1, ? super V2, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(with1, with2, extractor.applyAsDbl(k));
	}

	/**
	* 'ToDbl' - first, actual value will be converted to primitive type (contrary to the object).
	* 'With' - call of main predicate will be prefixed (contrary to affixed) with additional argument(s).
	*/
	public static @Nonnull <K, V1, V2> LPredicate<K> hasToDblWith(@Nonnull LToDblFunction<K> extractor, @Nonnull LBiObjDblPredicate<? super V1, ? super V2> predicate, V1 with1, V2 with2) {
		return hasToDblWith(extractor, with1, with2, predicate);
	}

	/**
	* 'ToDbl' - first, actual value will be converted to primitive type (contrary to the object).
	* 'With' - call of main predicate will be prefixed (contrary to affixed) with additional argument(s).
	* 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K, V1, V2> LFunction<K, String> hasToDblWithEx(@Nonnull LToDblFunction<K> extractor, @Nonnull LBiObjDblFunction<? super V1, ? super V2, String> specialPredicate, V1 with1, V2 with2) {
		return hasToDblWithEx(extractor, with1, with2, specialPredicate);
	}

	/** 'ToChar' - first, actual value will be converted to primitive type (contrary to the object). */
	public static @Nonnull <K> LPredicate<K> haveToChar(@Nonnull LToCharFunction<K> extractor, @Nonnull LCharPredicate predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.applyAsChar(k));
	}

	/**
	* 'ToChar' - first, actual value will be converted to primitive type (contrary to the object).
	* 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K> LFunction<K, String> haveToCharEx(@Nonnull LToCharFunction<K> extractor, @Nonnull LCharFunction<String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(extractor.applyAsChar(k));
	}

	/** 'ToChar' - first, actual value will be converted to primitive type (contrary to the object). */
	public static @Nonnull <K> LPredicate<K> hasToChar(@Nonnull LToCharFunction<K> extractor, @Nonnull LCharPredicate predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.applyAsChar(k));
	}

	/**
	* 'ToChar' - first, actual value will be converted to primitive type (contrary to the object).
	* 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K> LFunction<K, String> hasToCharEx(@Nonnull LToCharFunction<K> extractor, @Nonnull LCharFunction<String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(extractor.applyAsChar(k));
	}

	/** 'ToChar' - first, actual value will be converted to primitive type (contrary to the object). */
	public static @Nonnull <K> LPredicate<K> haveToChar(@Nonnull LToCharFunction<K> extractor, char a2, @Nonnull LBiCharPredicate predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.applyAsChar(k), a2);
	}

	/**
	* 'ToChar' - first, actual value will be converted to primitive type (contrary to the object).
	* 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K> LFunction<K, String> haveToCharEx(@Nonnull LToCharFunction<K> extractor, char a2, @Nonnull LBiCharFunction<String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(extractor.applyAsChar(k), a2);
	}

	/** 'ToChar' - first, actual value will be converted to primitive type (contrary to the object). */
	public static @Nonnull <K> LPredicate<K> haveToChar(@Nonnull LToCharFunction<K> extractor, @Nonnull LBiCharPredicate predicate, char a2) {
		return haveToChar(extractor, a2, predicate);
	}

	/**
	* 'ToChar' - first, actual value will be converted to primitive type (contrary to the object).
	* 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K> LFunction<K, String> haveToCharEx(@Nonnull LToCharFunction<K> extractor, @Nonnull LBiCharFunction<String> specialPredicate, char a2) {
		return haveToCharEx(extractor, a2, specialPredicate);
	}

	/** 'ToChar' - first, actual value will be converted to primitive type (contrary to the object). */
	public static @Nonnull <K> LPredicate<K> hasToChar(@Nonnull LToCharFunction<K> extractor, char a2, @Nonnull LBiCharPredicate predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.applyAsChar(k), a2);
	}

	/**
	* 'ToChar' - first, actual value will be converted to primitive type (contrary to the object).
	* 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K> LFunction<K, String> hasToCharEx(@Nonnull LToCharFunction<K> extractor, char a2, @Nonnull LBiCharFunction<String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(extractor.applyAsChar(k), a2);
	}

	/** 'ToChar' - first, actual value will be converted to primitive type (contrary to the object). */
	public static @Nonnull <K> LPredicate<K> hasToChar(@Nonnull LToCharFunction<K> extractor, @Nonnull LBiCharPredicate predicate, char a2) {
		return hasToChar(extractor, a2, predicate);
	}

	/**
	* 'ToChar' - first, actual value will be converted to primitive type (contrary to the object).
	* 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K> LFunction<K, String> hasToCharEx(@Nonnull LToCharFunction<K> extractor, @Nonnull LBiCharFunction<String> specialPredicate, char a2) {
		return hasToCharEx(extractor, a2, specialPredicate);
	}

	/** 'ToChar' - first, actual value will be converted to primitive type (contrary to the object). */
	public static @Nonnull <K> LPredicate<K> haveToChar(@Nonnull LToCharFunction<K> extractor, char a2, char a3, @Nonnull LTriCharPredicate predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.applyAsChar(k), a2, a3);
	}

	/** 'ToChar' - first, actual value will be converted to primitive type (contrary to the object). */
	public static @Nonnull <K> LPredicate<K> haveToChar(@Nonnull LToCharFunction<K> extractor, @Nonnull LTriCharPredicate predicate, char a2, char a3) {
		return haveToChar(extractor, a2, a3, predicate);
	}

	/** 'ToChar' - first, actual value will be converted to primitive type (contrary to the object). */
	public static @Nonnull <K> LPredicate<K> hasToChar(@Nonnull LToCharFunction<K> extractor, char a2, char a3, @Nonnull LTriCharPredicate predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.applyAsChar(k), a2, a3);
	}

	/** 'ToChar' - first, actual value will be converted to primitive type (contrary to the object). */
	public static @Nonnull <K> LPredicate<K> hasToChar(@Nonnull LToCharFunction<K> extractor, @Nonnull LTriCharPredicate predicate, char a2, char a3) {
		return hasToChar(extractor, a2, a3, predicate);
	}

	/** 'ToChar' - first, actual value will be converted to primitive type (contrary to the object). */
	public static @Nonnull <K> LPredicate<K> haveToCharInt(@Nonnull LToCharFunction<K> extractor, int v, @Nonnull LCharIntPredicate predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.applyAsChar(k), v);
	}

	/** 'ToChar' - first, actual value will be converted to primitive type (contrary to the object). */
	public static @Nonnull <K> LPredicate<K> haveToCharInt(@Nonnull LToCharFunction<K> extractor, @Nonnull LCharIntPredicate predicate, int v) {
		return haveToCharInt(extractor, v, predicate);
	}

	/** 'ToChar' - first, actual value will be converted to primitive type (contrary to the object). */
	public static @Nonnull <K> LPredicate<K> hasToCharInt(@Nonnull LToCharFunction<K> extractor, int v, @Nonnull LCharIntPredicate predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.applyAsChar(k), v);
	}

	/** 'ToChar' - first, actual value will be converted to primitive type (contrary to the object). */
	public static @Nonnull <K> LPredicate<K> hasToCharInt(@Nonnull LToCharFunction<K> extractor, @Nonnull LCharIntPredicate predicate, int v) {
		return hasToCharInt(extractor, v, predicate);
	}

	/**
	* 'ToChar' - first, actual value will be converted to primitive type (contrary to the object).
	* '_' - the predicate function is a variant or arguments represented by other function.
	*/
	public static @Nonnull <K, V> LPredicate<K> haveToChar_(@Nonnull LToCharFunction<K> extractor, V v, @Nonnull LObjCharPredicate.LCharObjPred<? super V> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.testCharObj(extractor.applyAsChar(k), v);
	}

	/**
	* 'ToChar' - first, actual value will be converted to primitive type (contrary to the object).
	* 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	* '_' - the predicate function is a variant or arguments represented by other function.
	*/
	public static @Nonnull <K, V> LFunction<K, String> haveToCharEx_(@Nonnull LToCharFunction<K> extractor, V v, @Nonnull LObjCharFunction.LCharObjFunc<? super V, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.applyCharObj(extractor.applyAsChar(k), v);
	}

	/**
	* 'ToChar' - first, actual value will be converted to primitive type (contrary to the object).
	* '_' - the predicate function is a variant or arguments represented by other function.
	*/
	public static @Nonnull <K, V> LPredicate<K> haveToChar_(@Nonnull LToCharFunction<K> extractor, @Nonnull LObjCharPredicate.LCharObjPred<? super V> predicate, V v) {
		return haveToChar_(extractor, v, predicate);
	}

	/**
	* 'ToChar' - first, actual value will be converted to primitive type (contrary to the object).
	* 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	* '_' - the predicate function is a variant or arguments represented by other function.
	*/
	public static @Nonnull <K, V> LFunction<K, String> haveToCharEx_(@Nonnull LToCharFunction<K> extractor, @Nonnull LObjCharFunction.LCharObjFunc<? super V, String> specialPredicate, V v) {
		return haveToCharEx_(extractor, v, specialPredicate);
	}

	/**
	* 'ToChar' - first, actual value will be converted to primitive type (contrary to the object).
	* '_' - the predicate function is a variant or arguments represented by other function.
	*/
	public static @Nonnull <K, V> LPredicate<K> hasToChar_(@Nonnull LToCharFunction<K> extractor, V v, @Nonnull LObjCharPredicate.LCharObjPred<? super V> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.testCharObj(extractor.applyAsChar(k), v);
	}

	/**
	* 'ToChar' - first, actual value will be converted to primitive type (contrary to the object).
	* 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	* '_' - the predicate function is a variant or arguments represented by other function.
	*/
	public static @Nonnull <K, V> LFunction<K, String> hasToCharEx_(@Nonnull LToCharFunction<K> extractor, V v, @Nonnull LObjCharFunction.LCharObjFunc<? super V, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.applyCharObj(extractor.applyAsChar(k), v);
	}

	/**
	* 'ToChar' - first, actual value will be converted to primitive type (contrary to the object).
	* '_' - the predicate function is a variant or arguments represented by other function.
	*/
	public static @Nonnull <K, V> LPredicate<K> hasToChar_(@Nonnull LToCharFunction<K> extractor, @Nonnull LObjCharPredicate.LCharObjPred<? super V> predicate, V v) {
		return hasToChar_(extractor, v, predicate);
	}

	/**
	* 'ToChar' - first, actual value will be converted to primitive type (contrary to the object).
	* 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	* '_' - the predicate function is a variant or arguments represented by other function.
	*/
	public static @Nonnull <K, V> LFunction<K, String> hasToCharEx_(@Nonnull LToCharFunction<K> extractor, @Nonnull LObjCharFunction.LCharObjFunc<? super V, String> specialPredicate, V v) {
		return hasToCharEx_(extractor, v, specialPredicate);
	}

	/**
	* 'ToChar' - first, actual value will be converted to primitive type (contrary to the object).
	* 'With' - call of main predicate will be prefixed (contrary to affixed) with additional argument(s).
	*/
	public static @Nonnull <K, V1> LPredicate<K> haveToCharWith(@Nonnull LToCharFunction<K> extractor, V1 with1, @Nonnull LObjCharPredicate<? super V1> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(with1, extractor.applyAsChar(k));
	}

	/**
	* 'ToChar' - first, actual value will be converted to primitive type (contrary to the object).
	* 'With' - call of main predicate will be prefixed (contrary to affixed) with additional argument(s).
	* 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K, V1> LFunction<K, String> haveToCharWithEx(@Nonnull LToCharFunction<K> extractor, V1 with1, @Nonnull LObjCharFunction<? super V1, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(with1, extractor.applyAsChar(k));
	}

	/**
	* 'ToChar' - first, actual value will be converted to primitive type (contrary to the object).
	* 'With' - call of main predicate will be prefixed (contrary to affixed) with additional argument(s).
	*/
	public static @Nonnull <K, V1> LPredicate<K> haveToCharWith(@Nonnull LToCharFunction<K> extractor, @Nonnull LObjCharPredicate<? super V1> predicate, V1 with1) {
		return haveToCharWith(extractor, with1, predicate);
	}

	/**
	* 'ToChar' - first, actual value will be converted to primitive type (contrary to the object).
	* 'With' - call of main predicate will be prefixed (contrary to affixed) with additional argument(s).
	* 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K, V1> LFunction<K, String> haveToCharWithEx(@Nonnull LToCharFunction<K> extractor, @Nonnull LObjCharFunction<? super V1, String> specialPredicate, V1 with1) {
		return haveToCharWithEx(extractor, with1, specialPredicate);
	}

	/**
	* 'ToChar' - first, actual value will be converted to primitive type (contrary to the object).
	* 'With' - call of main predicate will be prefixed (contrary to affixed) with additional argument(s).
	*/
	public static @Nonnull <K, V1> LPredicate<K> hasToCharWith(@Nonnull LToCharFunction<K> extractor, V1 with1, @Nonnull LObjCharPredicate<? super V1> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(with1, extractor.applyAsChar(k));
	}

	/**
	* 'ToChar' - first, actual value will be converted to primitive type (contrary to the object).
	* 'With' - call of main predicate will be prefixed (contrary to affixed) with additional argument(s).
	* 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K, V1> LFunction<K, String> hasToCharWithEx(@Nonnull LToCharFunction<K> extractor, V1 with1, @Nonnull LObjCharFunction<? super V1, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(with1, extractor.applyAsChar(k));
	}

	/**
	* 'ToChar' - first, actual value will be converted to primitive type (contrary to the object).
	* 'With' - call of main predicate will be prefixed (contrary to affixed) with additional argument(s).
	*/
	public static @Nonnull <K, V1> LPredicate<K> hasToCharWith(@Nonnull LToCharFunction<K> extractor, @Nonnull LObjCharPredicate<? super V1> predicate, V1 with1) {
		return hasToCharWith(extractor, with1, predicate);
	}

	/**
	* 'ToChar' - first, actual value will be converted to primitive type (contrary to the object).
	* 'With' - call of main predicate will be prefixed (contrary to affixed) with additional argument(s).
	* 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K, V1> LFunction<K, String> hasToCharWithEx(@Nonnull LToCharFunction<K> extractor, @Nonnull LObjCharFunction<? super V1, String> specialPredicate, V1 with1) {
		return hasToCharWithEx(extractor, with1, specialPredicate);
	}

	/**
	* 'ToChar' - first, actual value will be converted to primitive type (contrary to the object).
	* 'With' - call of main predicate will be prefixed (contrary to affixed) with additional argument(s).
	*/
	public static @Nonnull <K, V1, V2> LPredicate<K> haveToCharWith(@Nonnull LToCharFunction<K> extractor, V1 with1, V2 with2, @Nonnull LBiObjCharPredicate<? super V1, ? super V2> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(with1, with2, extractor.applyAsChar(k));
	}

	/**
	* 'ToChar' - first, actual value will be converted to primitive type (contrary to the object).
	* 'With' - call of main predicate will be prefixed (contrary to affixed) with additional argument(s).
	* 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K, V1, V2> LFunction<K, String> haveToCharWithEx(@Nonnull LToCharFunction<K> extractor, V1 with1, V2 with2, @Nonnull LBiObjCharFunction<? super V1, ? super V2, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(with1, with2, extractor.applyAsChar(k));
	}

	/**
	* 'ToChar' - first, actual value will be converted to primitive type (contrary to the object).
	* 'With' - call of main predicate will be prefixed (contrary to affixed) with additional argument(s).
	*/
	public static @Nonnull <K, V1, V2> LPredicate<K> haveToCharWith(@Nonnull LToCharFunction<K> extractor, @Nonnull LBiObjCharPredicate<? super V1, ? super V2> predicate, V1 with1, V2 with2) {
		return haveToCharWith(extractor, with1, with2, predicate);
	}

	/**
	* 'ToChar' - first, actual value will be converted to primitive type (contrary to the object).
	* 'With' - call of main predicate will be prefixed (contrary to affixed) with additional argument(s).
	* 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K, V1, V2> LFunction<K, String> haveToCharWithEx(@Nonnull LToCharFunction<K> extractor, @Nonnull LBiObjCharFunction<? super V1, ? super V2, String> specialPredicate, V1 with1, V2 with2) {
		return haveToCharWithEx(extractor, with1, with2, specialPredicate);
	}

	/**
	* 'ToChar' - first, actual value will be converted to primitive type (contrary to the object).
	* 'With' - call of main predicate will be prefixed (contrary to affixed) with additional argument(s).
	*/
	public static @Nonnull <K, V1, V2> LPredicate<K> hasToCharWith(@Nonnull LToCharFunction<K> extractor, V1 with1, V2 with2, @Nonnull LBiObjCharPredicate<? super V1, ? super V2> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(with1, with2, extractor.applyAsChar(k));
	}

	/**
	* 'ToChar' - first, actual value will be converted to primitive type (contrary to the object).
	* 'With' - call of main predicate will be prefixed (contrary to affixed) with additional argument(s).
	* 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K, V1, V2> LFunction<K, String> hasToCharWithEx(@Nonnull LToCharFunction<K> extractor, V1 with1, V2 with2, @Nonnull LBiObjCharFunction<? super V1, ? super V2, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(with1, with2, extractor.applyAsChar(k));
	}

	/**
	* 'ToChar' - first, actual value will be converted to primitive type (contrary to the object).
	* 'With' - call of main predicate will be prefixed (contrary to affixed) with additional argument(s).
	*/
	public static @Nonnull <K, V1, V2> LPredicate<K> hasToCharWith(@Nonnull LToCharFunction<K> extractor, @Nonnull LBiObjCharPredicate<? super V1, ? super V2> predicate, V1 with1, V2 with2) {
		return hasToCharWith(extractor, with1, with2, predicate);
	}

	/**
	* 'ToChar' - first, actual value will be converted to primitive type (contrary to the object).
	* 'With' - call of main predicate will be prefixed (contrary to affixed) with additional argument(s).
	* 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K, V1, V2> LFunction<K, String> hasToCharWithEx(@Nonnull LToCharFunction<K> extractor, @Nonnull LBiObjCharFunction<? super V1, ? super V2, String> specialPredicate, V1 with1, V2 with2) {
		return hasToCharWithEx(extractor, with1, with2, specialPredicate);
	}

	/** 'ToSrt' - first, actual value will be converted to primitive type (contrary to the object). */
	public static @Nonnull <K> LPredicate<K> haveToSrt(@Nonnull LToSrtFunction<K> extractor, @Nonnull LSrtPredicate predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.applyAsSrt(k));
	}

	/**
	* 'ToSrt' - first, actual value will be converted to primitive type (contrary to the object).
	* 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K> LFunction<K, String> haveToSrtEx(@Nonnull LToSrtFunction<K> extractor, @Nonnull LSrtFunction<String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(extractor.applyAsSrt(k));
	}

	/** 'ToSrt' - first, actual value will be converted to primitive type (contrary to the object). */
	public static @Nonnull <K> LPredicate<K> hasToSrt(@Nonnull LToSrtFunction<K> extractor, @Nonnull LSrtPredicate predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.applyAsSrt(k));
	}

	/**
	* 'ToSrt' - first, actual value will be converted to primitive type (contrary to the object).
	* 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K> LFunction<K, String> hasToSrtEx(@Nonnull LToSrtFunction<K> extractor, @Nonnull LSrtFunction<String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(extractor.applyAsSrt(k));
	}

	/** 'ToSrt' - first, actual value will be converted to primitive type (contrary to the object). */
	public static @Nonnull <K> LPredicate<K> haveToSrt(@Nonnull LToSrtFunction<K> extractor, short a2, @Nonnull LBiSrtPredicate predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.applyAsSrt(k), a2);
	}

	/**
	* 'ToSrt' - first, actual value will be converted to primitive type (contrary to the object).
	* 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K> LFunction<K, String> haveToSrtEx(@Nonnull LToSrtFunction<K> extractor, short a2, @Nonnull LBiSrtFunction<String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(extractor.applyAsSrt(k), a2);
	}

	/** 'ToSrt' - first, actual value will be converted to primitive type (contrary to the object). */
	public static @Nonnull <K> LPredicate<K> haveToSrt(@Nonnull LToSrtFunction<K> extractor, @Nonnull LBiSrtPredicate predicate, short a2) {
		return haveToSrt(extractor, a2, predicate);
	}

	/**
	* 'ToSrt' - first, actual value will be converted to primitive type (contrary to the object).
	* 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K> LFunction<K, String> haveToSrtEx(@Nonnull LToSrtFunction<K> extractor, @Nonnull LBiSrtFunction<String> specialPredicate, short a2) {
		return haveToSrtEx(extractor, a2, specialPredicate);
	}

	/** 'ToSrt' - first, actual value will be converted to primitive type (contrary to the object). */
	public static @Nonnull <K> LPredicate<K> hasToSrt(@Nonnull LToSrtFunction<K> extractor, short a2, @Nonnull LBiSrtPredicate predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.applyAsSrt(k), a2);
	}

	/**
	* 'ToSrt' - first, actual value will be converted to primitive type (contrary to the object).
	* 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K> LFunction<K, String> hasToSrtEx(@Nonnull LToSrtFunction<K> extractor, short a2, @Nonnull LBiSrtFunction<String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(extractor.applyAsSrt(k), a2);
	}

	/** 'ToSrt' - first, actual value will be converted to primitive type (contrary to the object). */
	public static @Nonnull <K> LPredicate<K> hasToSrt(@Nonnull LToSrtFunction<K> extractor, @Nonnull LBiSrtPredicate predicate, short a2) {
		return hasToSrt(extractor, a2, predicate);
	}

	/**
	* 'ToSrt' - first, actual value will be converted to primitive type (contrary to the object).
	* 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K> LFunction<K, String> hasToSrtEx(@Nonnull LToSrtFunction<K> extractor, @Nonnull LBiSrtFunction<String> specialPredicate, short a2) {
		return hasToSrtEx(extractor, a2, specialPredicate);
	}

	/** 'ToSrt' - first, actual value will be converted to primitive type (contrary to the object). */
	public static @Nonnull <K> LPredicate<K> haveToSrt(@Nonnull LToSrtFunction<K> extractor, short a2, short a3, @Nonnull LTriSrtPredicate predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.applyAsSrt(k), a2, a3);
	}

	/** 'ToSrt' - first, actual value will be converted to primitive type (contrary to the object). */
	public static @Nonnull <K> LPredicate<K> haveToSrt(@Nonnull LToSrtFunction<K> extractor, @Nonnull LTriSrtPredicate predicate, short a2, short a3) {
		return haveToSrt(extractor, a2, a3, predicate);
	}

	/** 'ToSrt' - first, actual value will be converted to primitive type (contrary to the object). */
	public static @Nonnull <K> LPredicate<K> hasToSrt(@Nonnull LToSrtFunction<K> extractor, short a2, short a3, @Nonnull LTriSrtPredicate predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.applyAsSrt(k), a2, a3);
	}

	/** 'ToSrt' - first, actual value will be converted to primitive type (contrary to the object). */
	public static @Nonnull <K> LPredicate<K> hasToSrt(@Nonnull LToSrtFunction<K> extractor, @Nonnull LTriSrtPredicate predicate, short a2, short a3) {
		return hasToSrt(extractor, a2, a3, predicate);
	}

	/** 'ToSrt' - first, actual value will be converted to primitive type (contrary to the object). */
	public static @Nonnull <K> LPredicate<K> haveToSrtInt(@Nonnull LToSrtFunction<K> extractor, int v, @Nonnull LSrtIntPredicate predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.applyAsSrt(k), v);
	}

	/** 'ToSrt' - first, actual value will be converted to primitive type (contrary to the object). */
	public static @Nonnull <K> LPredicate<K> haveToSrtInt(@Nonnull LToSrtFunction<K> extractor, @Nonnull LSrtIntPredicate predicate, int v) {
		return haveToSrtInt(extractor, v, predicate);
	}

	/** 'ToSrt' - first, actual value will be converted to primitive type (contrary to the object). */
	public static @Nonnull <K> LPredicate<K> hasToSrtInt(@Nonnull LToSrtFunction<K> extractor, int v, @Nonnull LSrtIntPredicate predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.applyAsSrt(k), v);
	}

	/** 'ToSrt' - first, actual value will be converted to primitive type (contrary to the object). */
	public static @Nonnull <K> LPredicate<K> hasToSrtInt(@Nonnull LToSrtFunction<K> extractor, @Nonnull LSrtIntPredicate predicate, int v) {
		return hasToSrtInt(extractor, v, predicate);
	}

	/**
	* 'ToSrt' - first, actual value will be converted to primitive type (contrary to the object).
	* '_' - the predicate function is a variant or arguments represented by other function.
	*/
	public static @Nonnull <K, V> LPredicate<K> haveToSrt_(@Nonnull LToSrtFunction<K> extractor, V v, @Nonnull LObjSrtPredicate.LSrtObjPred<? super V> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.testSrtObj(extractor.applyAsSrt(k), v);
	}

	/**
	* 'ToSrt' - first, actual value will be converted to primitive type (contrary to the object).
	* 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	* '_' - the predicate function is a variant or arguments represented by other function.
	*/
	public static @Nonnull <K, V> LFunction<K, String> haveToSrtEx_(@Nonnull LToSrtFunction<K> extractor, V v, @Nonnull LObjSrtFunction.LSrtObjFunc<? super V, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.applySrtObj(extractor.applyAsSrt(k), v);
	}

	/**
	* 'ToSrt' - first, actual value will be converted to primitive type (contrary to the object).
	* '_' - the predicate function is a variant or arguments represented by other function.
	*/
	public static @Nonnull <K, V> LPredicate<K> haveToSrt_(@Nonnull LToSrtFunction<K> extractor, @Nonnull LObjSrtPredicate.LSrtObjPred<? super V> predicate, V v) {
		return haveToSrt_(extractor, v, predicate);
	}

	/**
	* 'ToSrt' - first, actual value will be converted to primitive type (contrary to the object).
	* 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	* '_' - the predicate function is a variant or arguments represented by other function.
	*/
	public static @Nonnull <K, V> LFunction<K, String> haveToSrtEx_(@Nonnull LToSrtFunction<K> extractor, @Nonnull LObjSrtFunction.LSrtObjFunc<? super V, String> specialPredicate, V v) {
		return haveToSrtEx_(extractor, v, specialPredicate);
	}

	/**
	* 'ToSrt' - first, actual value will be converted to primitive type (contrary to the object).
	* '_' - the predicate function is a variant or arguments represented by other function.
	*/
	public static @Nonnull <K, V> LPredicate<K> hasToSrt_(@Nonnull LToSrtFunction<K> extractor, V v, @Nonnull LObjSrtPredicate.LSrtObjPred<? super V> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.testSrtObj(extractor.applyAsSrt(k), v);
	}

	/**
	* 'ToSrt' - first, actual value will be converted to primitive type (contrary to the object).
	* 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	* '_' - the predicate function is a variant or arguments represented by other function.
	*/
	public static @Nonnull <K, V> LFunction<K, String> hasToSrtEx_(@Nonnull LToSrtFunction<K> extractor, V v, @Nonnull LObjSrtFunction.LSrtObjFunc<? super V, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.applySrtObj(extractor.applyAsSrt(k), v);
	}

	/**
	* 'ToSrt' - first, actual value will be converted to primitive type (contrary to the object).
	* '_' - the predicate function is a variant or arguments represented by other function.
	*/
	public static @Nonnull <K, V> LPredicate<K> hasToSrt_(@Nonnull LToSrtFunction<K> extractor, @Nonnull LObjSrtPredicate.LSrtObjPred<? super V> predicate, V v) {
		return hasToSrt_(extractor, v, predicate);
	}

	/**
	* 'ToSrt' - first, actual value will be converted to primitive type (contrary to the object).
	* 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	* '_' - the predicate function is a variant or arguments represented by other function.
	*/
	public static @Nonnull <K, V> LFunction<K, String> hasToSrtEx_(@Nonnull LToSrtFunction<K> extractor, @Nonnull LObjSrtFunction.LSrtObjFunc<? super V, String> specialPredicate, V v) {
		return hasToSrtEx_(extractor, v, specialPredicate);
	}

	/**
	* 'ToSrt' - first, actual value will be converted to primitive type (contrary to the object).
	* 'With' - call of main predicate will be prefixed (contrary to affixed) with additional argument(s).
	*/
	public static @Nonnull <K, V1> LPredicate<K> haveToSrtWith(@Nonnull LToSrtFunction<K> extractor, V1 with1, @Nonnull LObjSrtPredicate<? super V1> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(with1, extractor.applyAsSrt(k));
	}

	/**
	* 'ToSrt' - first, actual value will be converted to primitive type (contrary to the object).
	* 'With' - call of main predicate will be prefixed (contrary to affixed) with additional argument(s).
	* 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K, V1> LFunction<K, String> haveToSrtWithEx(@Nonnull LToSrtFunction<K> extractor, V1 with1, @Nonnull LObjSrtFunction<? super V1, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(with1, extractor.applyAsSrt(k));
	}

	/**
	* 'ToSrt' - first, actual value will be converted to primitive type (contrary to the object).
	* 'With' - call of main predicate will be prefixed (contrary to affixed) with additional argument(s).
	*/
	public static @Nonnull <K, V1> LPredicate<K> haveToSrtWith(@Nonnull LToSrtFunction<K> extractor, @Nonnull LObjSrtPredicate<? super V1> predicate, V1 with1) {
		return haveToSrtWith(extractor, with1, predicate);
	}

	/**
	* 'ToSrt' - first, actual value will be converted to primitive type (contrary to the object).
	* 'With' - call of main predicate will be prefixed (contrary to affixed) with additional argument(s).
	* 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K, V1> LFunction<K, String> haveToSrtWithEx(@Nonnull LToSrtFunction<K> extractor, @Nonnull LObjSrtFunction<? super V1, String> specialPredicate, V1 with1) {
		return haveToSrtWithEx(extractor, with1, specialPredicate);
	}

	/**
	* 'ToSrt' - first, actual value will be converted to primitive type (contrary to the object).
	* 'With' - call of main predicate will be prefixed (contrary to affixed) with additional argument(s).
	*/
	public static @Nonnull <K, V1> LPredicate<K> hasToSrtWith(@Nonnull LToSrtFunction<K> extractor, V1 with1, @Nonnull LObjSrtPredicate<? super V1> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(with1, extractor.applyAsSrt(k));
	}

	/**
	* 'ToSrt' - first, actual value will be converted to primitive type (contrary to the object).
	* 'With' - call of main predicate will be prefixed (contrary to affixed) with additional argument(s).
	* 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K, V1> LFunction<K, String> hasToSrtWithEx(@Nonnull LToSrtFunction<K> extractor, V1 with1, @Nonnull LObjSrtFunction<? super V1, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(with1, extractor.applyAsSrt(k));
	}

	/**
	* 'ToSrt' - first, actual value will be converted to primitive type (contrary to the object).
	* 'With' - call of main predicate will be prefixed (contrary to affixed) with additional argument(s).
	*/
	public static @Nonnull <K, V1> LPredicate<K> hasToSrtWith(@Nonnull LToSrtFunction<K> extractor, @Nonnull LObjSrtPredicate<? super V1> predicate, V1 with1) {
		return hasToSrtWith(extractor, with1, predicate);
	}

	/**
	* 'ToSrt' - first, actual value will be converted to primitive type (contrary to the object).
	* 'With' - call of main predicate will be prefixed (contrary to affixed) with additional argument(s).
	* 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K, V1> LFunction<K, String> hasToSrtWithEx(@Nonnull LToSrtFunction<K> extractor, @Nonnull LObjSrtFunction<? super V1, String> specialPredicate, V1 with1) {
		return hasToSrtWithEx(extractor, with1, specialPredicate);
	}

	/**
	* 'ToSrt' - first, actual value will be converted to primitive type (contrary to the object).
	* 'With' - call of main predicate will be prefixed (contrary to affixed) with additional argument(s).
	*/
	public static @Nonnull <K, V1, V2> LPredicate<K> haveToSrtWith(@Nonnull LToSrtFunction<K> extractor, V1 with1, V2 with2, @Nonnull LBiObjSrtPredicate<? super V1, ? super V2> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(with1, with2, extractor.applyAsSrt(k));
	}

	/**
	* 'ToSrt' - first, actual value will be converted to primitive type (contrary to the object).
	* 'With' - call of main predicate will be prefixed (contrary to affixed) with additional argument(s).
	* 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K, V1, V2> LFunction<K, String> haveToSrtWithEx(@Nonnull LToSrtFunction<K> extractor, V1 with1, V2 with2, @Nonnull LBiObjSrtFunction<? super V1, ? super V2, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(with1, with2, extractor.applyAsSrt(k));
	}

	/**
	* 'ToSrt' - first, actual value will be converted to primitive type (contrary to the object).
	* 'With' - call of main predicate will be prefixed (contrary to affixed) with additional argument(s).
	*/
	public static @Nonnull <K, V1, V2> LPredicate<K> haveToSrtWith(@Nonnull LToSrtFunction<K> extractor, @Nonnull LBiObjSrtPredicate<? super V1, ? super V2> predicate, V1 with1, V2 with2) {
		return haveToSrtWith(extractor, with1, with2, predicate);
	}

	/**
	* 'ToSrt' - first, actual value will be converted to primitive type (contrary to the object).
	* 'With' - call of main predicate will be prefixed (contrary to affixed) with additional argument(s).
	* 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K, V1, V2> LFunction<K, String> haveToSrtWithEx(@Nonnull LToSrtFunction<K> extractor, @Nonnull LBiObjSrtFunction<? super V1, ? super V2, String> specialPredicate, V1 with1, V2 with2) {
		return haveToSrtWithEx(extractor, with1, with2, specialPredicate);
	}

	/**
	* 'ToSrt' - first, actual value will be converted to primitive type (contrary to the object).
	* 'With' - call of main predicate will be prefixed (contrary to affixed) with additional argument(s).
	*/
	public static @Nonnull <K, V1, V2> LPredicate<K> hasToSrtWith(@Nonnull LToSrtFunction<K> extractor, V1 with1, V2 with2, @Nonnull LBiObjSrtPredicate<? super V1, ? super V2> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(with1, with2, extractor.applyAsSrt(k));
	}

	/**
	* 'ToSrt' - first, actual value will be converted to primitive type (contrary to the object).
	* 'With' - call of main predicate will be prefixed (contrary to affixed) with additional argument(s).
	* 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K, V1, V2> LFunction<K, String> hasToSrtWithEx(@Nonnull LToSrtFunction<K> extractor, V1 with1, V2 with2, @Nonnull LBiObjSrtFunction<? super V1, ? super V2, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(with1, with2, extractor.applyAsSrt(k));
	}

	/**
	* 'ToSrt' - first, actual value will be converted to primitive type (contrary to the object).
	* 'With' - call of main predicate will be prefixed (contrary to affixed) with additional argument(s).
	*/
	public static @Nonnull <K, V1, V2> LPredicate<K> hasToSrtWith(@Nonnull LToSrtFunction<K> extractor, @Nonnull LBiObjSrtPredicate<? super V1, ? super V2> predicate, V1 with1, V2 with2) {
		return hasToSrtWith(extractor, with1, with2, predicate);
	}

	/**
	* 'ToSrt' - first, actual value will be converted to primitive type (contrary to the object).
	* 'With' - call of main predicate will be prefixed (contrary to affixed) with additional argument(s).
	* 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K, V1, V2> LFunction<K, String> hasToSrtWithEx(@Nonnull LToSrtFunction<K> extractor, @Nonnull LBiObjSrtFunction<? super V1, ? super V2, String> specialPredicate, V1 with1, V2 with2) {
		return hasToSrtWithEx(extractor, with1, with2, specialPredicate);
	}

	/** 'ToFlt' - first, actual value will be converted to primitive type (contrary to the object). */
	public static @Nonnull <K> LPredicate<K> haveToFlt(@Nonnull LToFltFunction<K> extractor, @Nonnull LFltPredicate predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.applyAsFlt(k));
	}

	/**
	* 'ToFlt' - first, actual value will be converted to primitive type (contrary to the object).
	* 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K> LFunction<K, String> haveToFltEx(@Nonnull LToFltFunction<K> extractor, @Nonnull LFltFunction<String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(extractor.applyAsFlt(k));
	}

	/** 'ToFlt' - first, actual value will be converted to primitive type (contrary to the object). */
	public static @Nonnull <K> LPredicate<K> hasToFlt(@Nonnull LToFltFunction<K> extractor, @Nonnull LFltPredicate predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.applyAsFlt(k));
	}

	/**
	* 'ToFlt' - first, actual value will be converted to primitive type (contrary to the object).
	* 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K> LFunction<K, String> hasToFltEx(@Nonnull LToFltFunction<K> extractor, @Nonnull LFltFunction<String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(extractor.applyAsFlt(k));
	}

	/** 'ToFlt' - first, actual value will be converted to primitive type (contrary to the object). */
	public static @Nonnull <K> LPredicate<K> haveToFlt(@Nonnull LToFltFunction<K> extractor, float a2, @Nonnull LBiFltPredicate predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.applyAsFlt(k), a2);
	}

	/**
	* 'ToFlt' - first, actual value will be converted to primitive type (contrary to the object).
	* 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K> LFunction<K, String> haveToFltEx(@Nonnull LToFltFunction<K> extractor, float a2, @Nonnull LBiFltFunction<String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(extractor.applyAsFlt(k), a2);
	}

	/** 'ToFlt' - first, actual value will be converted to primitive type (contrary to the object). */
	public static @Nonnull <K> LPredicate<K> haveToFlt(@Nonnull LToFltFunction<K> extractor, @Nonnull LBiFltPredicate predicate, float a2) {
		return haveToFlt(extractor, a2, predicate);
	}

	/**
	* 'ToFlt' - first, actual value will be converted to primitive type (contrary to the object).
	* 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K> LFunction<K, String> haveToFltEx(@Nonnull LToFltFunction<K> extractor, @Nonnull LBiFltFunction<String> specialPredicate, float a2) {
		return haveToFltEx(extractor, a2, specialPredicate);
	}

	/** 'ToFlt' - first, actual value will be converted to primitive type (contrary to the object). */
	public static @Nonnull <K> LPredicate<K> hasToFlt(@Nonnull LToFltFunction<K> extractor, float a2, @Nonnull LBiFltPredicate predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.applyAsFlt(k), a2);
	}

	/**
	* 'ToFlt' - first, actual value will be converted to primitive type (contrary to the object).
	* 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K> LFunction<K, String> hasToFltEx(@Nonnull LToFltFunction<K> extractor, float a2, @Nonnull LBiFltFunction<String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(extractor.applyAsFlt(k), a2);
	}

	/** 'ToFlt' - first, actual value will be converted to primitive type (contrary to the object). */
	public static @Nonnull <K> LPredicate<K> hasToFlt(@Nonnull LToFltFunction<K> extractor, @Nonnull LBiFltPredicate predicate, float a2) {
		return hasToFlt(extractor, a2, predicate);
	}

	/**
	* 'ToFlt' - first, actual value will be converted to primitive type (contrary to the object).
	* 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K> LFunction<K, String> hasToFltEx(@Nonnull LToFltFunction<K> extractor, @Nonnull LBiFltFunction<String> specialPredicate, float a2) {
		return hasToFltEx(extractor, a2, specialPredicate);
	}

	/** 'ToFlt' - first, actual value will be converted to primitive type (contrary to the object). */
	public static @Nonnull <K> LPredicate<K> haveToFlt(@Nonnull LToFltFunction<K> extractor, float a2, float a3, @Nonnull LTriFltPredicate predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.applyAsFlt(k), a2, a3);
	}

	/** 'ToFlt' - first, actual value will be converted to primitive type (contrary to the object). */
	public static @Nonnull <K> LPredicate<K> haveToFlt(@Nonnull LToFltFunction<K> extractor, @Nonnull LTriFltPredicate predicate, float a2, float a3) {
		return haveToFlt(extractor, a2, a3, predicate);
	}

	/** 'ToFlt' - first, actual value will be converted to primitive type (contrary to the object). */
	public static @Nonnull <K> LPredicate<K> hasToFlt(@Nonnull LToFltFunction<K> extractor, float a2, float a3, @Nonnull LTriFltPredicate predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.applyAsFlt(k), a2, a3);
	}

	/** 'ToFlt' - first, actual value will be converted to primitive type (contrary to the object). */
	public static @Nonnull <K> LPredicate<K> hasToFlt(@Nonnull LToFltFunction<K> extractor, @Nonnull LTriFltPredicate predicate, float a2, float a3) {
		return hasToFlt(extractor, a2, a3, predicate);
	}

	/** 'ToFlt' - first, actual value will be converted to primitive type (contrary to the object). */
	public static @Nonnull <K> LPredicate<K> haveToFltInt(@Nonnull LToFltFunction<K> extractor, int v, @Nonnull LFltIntPredicate predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.applyAsFlt(k), v);
	}

	/** 'ToFlt' - first, actual value will be converted to primitive type (contrary to the object). */
	public static @Nonnull <K> LPredicate<K> haveToFltInt(@Nonnull LToFltFunction<K> extractor, @Nonnull LFltIntPredicate predicate, int v) {
		return haveToFltInt(extractor, v, predicate);
	}

	/** 'ToFlt' - first, actual value will be converted to primitive type (contrary to the object). */
	public static @Nonnull <K> LPredicate<K> hasToFltInt(@Nonnull LToFltFunction<K> extractor, int v, @Nonnull LFltIntPredicate predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.applyAsFlt(k), v);
	}

	/** 'ToFlt' - first, actual value will be converted to primitive type (contrary to the object). */
	public static @Nonnull <K> LPredicate<K> hasToFltInt(@Nonnull LToFltFunction<K> extractor, @Nonnull LFltIntPredicate predicate, int v) {
		return hasToFltInt(extractor, v, predicate);
	}

	/**
	* 'ToFlt' - first, actual value will be converted to primitive type (contrary to the object).
	* '_' - the predicate function is a variant or arguments represented by other function.
	*/
	public static @Nonnull <K, V> LPredicate<K> haveToFlt_(@Nonnull LToFltFunction<K> extractor, V v, @Nonnull LObjFltPredicate.LFltObjPred<? super V> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.testFltObj(extractor.applyAsFlt(k), v);
	}

	/**
	* 'ToFlt' - first, actual value will be converted to primitive type (contrary to the object).
	* 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	* '_' - the predicate function is a variant or arguments represented by other function.
	*/
	public static @Nonnull <K, V> LFunction<K, String> haveToFltEx_(@Nonnull LToFltFunction<K> extractor, V v, @Nonnull LObjFltFunction.LFltObjFunc<? super V, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.applyFltObj(extractor.applyAsFlt(k), v);
	}

	/**
	* 'ToFlt' - first, actual value will be converted to primitive type (contrary to the object).
	* '_' - the predicate function is a variant or arguments represented by other function.
	*/
	public static @Nonnull <K, V> LPredicate<K> haveToFlt_(@Nonnull LToFltFunction<K> extractor, @Nonnull LObjFltPredicate.LFltObjPred<? super V> predicate, V v) {
		return haveToFlt_(extractor, v, predicate);
	}

	/**
	* 'ToFlt' - first, actual value will be converted to primitive type (contrary to the object).
	* 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	* '_' - the predicate function is a variant or arguments represented by other function.
	*/
	public static @Nonnull <K, V> LFunction<K, String> haveToFltEx_(@Nonnull LToFltFunction<K> extractor, @Nonnull LObjFltFunction.LFltObjFunc<? super V, String> specialPredicate, V v) {
		return haveToFltEx_(extractor, v, specialPredicate);
	}

	/**
	* 'ToFlt' - first, actual value will be converted to primitive type (contrary to the object).
	* '_' - the predicate function is a variant or arguments represented by other function.
	*/
	public static @Nonnull <K, V> LPredicate<K> hasToFlt_(@Nonnull LToFltFunction<K> extractor, V v, @Nonnull LObjFltPredicate.LFltObjPred<? super V> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.testFltObj(extractor.applyAsFlt(k), v);
	}

	/**
	* 'ToFlt' - first, actual value will be converted to primitive type (contrary to the object).
	* 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	* '_' - the predicate function is a variant or arguments represented by other function.
	*/
	public static @Nonnull <K, V> LFunction<K, String> hasToFltEx_(@Nonnull LToFltFunction<K> extractor, V v, @Nonnull LObjFltFunction.LFltObjFunc<? super V, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.applyFltObj(extractor.applyAsFlt(k), v);
	}

	/**
	* 'ToFlt' - first, actual value will be converted to primitive type (contrary to the object).
	* '_' - the predicate function is a variant or arguments represented by other function.
	*/
	public static @Nonnull <K, V> LPredicate<K> hasToFlt_(@Nonnull LToFltFunction<K> extractor, @Nonnull LObjFltPredicate.LFltObjPred<? super V> predicate, V v) {
		return hasToFlt_(extractor, v, predicate);
	}

	/**
	* 'ToFlt' - first, actual value will be converted to primitive type (contrary to the object).
	* 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	* '_' - the predicate function is a variant or arguments represented by other function.
	*/
	public static @Nonnull <K, V> LFunction<K, String> hasToFltEx_(@Nonnull LToFltFunction<K> extractor, @Nonnull LObjFltFunction.LFltObjFunc<? super V, String> specialPredicate, V v) {
		return hasToFltEx_(extractor, v, specialPredicate);
	}

	/**
	* 'ToFlt' - first, actual value will be converted to primitive type (contrary to the object).
	* 'With' - call of main predicate will be prefixed (contrary to affixed) with additional argument(s).
	*/
	public static @Nonnull <K, V1> LPredicate<K> haveToFltWith(@Nonnull LToFltFunction<K> extractor, V1 with1, @Nonnull LObjFltPredicate<? super V1> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(with1, extractor.applyAsFlt(k));
	}

	/**
	* 'ToFlt' - first, actual value will be converted to primitive type (contrary to the object).
	* 'With' - call of main predicate will be prefixed (contrary to affixed) with additional argument(s).
	* 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K, V1> LFunction<K, String> haveToFltWithEx(@Nonnull LToFltFunction<K> extractor, V1 with1, @Nonnull LObjFltFunction<? super V1, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(with1, extractor.applyAsFlt(k));
	}

	/**
	* 'ToFlt' - first, actual value will be converted to primitive type (contrary to the object).
	* 'With' - call of main predicate will be prefixed (contrary to affixed) with additional argument(s).
	*/
	public static @Nonnull <K, V1> LPredicate<K> haveToFltWith(@Nonnull LToFltFunction<K> extractor, @Nonnull LObjFltPredicate<? super V1> predicate, V1 with1) {
		return haveToFltWith(extractor, with1, predicate);
	}

	/**
	* 'ToFlt' - first, actual value will be converted to primitive type (contrary to the object).
	* 'With' - call of main predicate will be prefixed (contrary to affixed) with additional argument(s).
	* 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K, V1> LFunction<K, String> haveToFltWithEx(@Nonnull LToFltFunction<K> extractor, @Nonnull LObjFltFunction<? super V1, String> specialPredicate, V1 with1) {
		return haveToFltWithEx(extractor, with1, specialPredicate);
	}

	/**
	* 'ToFlt' - first, actual value will be converted to primitive type (contrary to the object).
	* 'With' - call of main predicate will be prefixed (contrary to affixed) with additional argument(s).
	*/
	public static @Nonnull <K, V1> LPredicate<K> hasToFltWith(@Nonnull LToFltFunction<K> extractor, V1 with1, @Nonnull LObjFltPredicate<? super V1> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(with1, extractor.applyAsFlt(k));
	}

	/**
	* 'ToFlt' - first, actual value will be converted to primitive type (contrary to the object).
	* 'With' - call of main predicate will be prefixed (contrary to affixed) with additional argument(s).
	* 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K, V1> LFunction<K, String> hasToFltWithEx(@Nonnull LToFltFunction<K> extractor, V1 with1, @Nonnull LObjFltFunction<? super V1, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(with1, extractor.applyAsFlt(k));
	}

	/**
	* 'ToFlt' - first, actual value will be converted to primitive type (contrary to the object).
	* 'With' - call of main predicate will be prefixed (contrary to affixed) with additional argument(s).
	*/
	public static @Nonnull <K, V1> LPredicate<K> hasToFltWith(@Nonnull LToFltFunction<K> extractor, @Nonnull LObjFltPredicate<? super V1> predicate, V1 with1) {
		return hasToFltWith(extractor, with1, predicate);
	}

	/**
	* 'ToFlt' - first, actual value will be converted to primitive type (contrary to the object).
	* 'With' - call of main predicate will be prefixed (contrary to affixed) with additional argument(s).
	* 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K, V1> LFunction<K, String> hasToFltWithEx(@Nonnull LToFltFunction<K> extractor, @Nonnull LObjFltFunction<? super V1, String> specialPredicate, V1 with1) {
		return hasToFltWithEx(extractor, with1, specialPredicate);
	}

	/**
	* 'ToFlt' - first, actual value will be converted to primitive type (contrary to the object).
	* 'With' - call of main predicate will be prefixed (contrary to affixed) with additional argument(s).
	*/
	public static @Nonnull <K, V1, V2> LPredicate<K> haveToFltWith(@Nonnull LToFltFunction<K> extractor, V1 with1, V2 with2, @Nonnull LBiObjFltPredicate<? super V1, ? super V2> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(with1, with2, extractor.applyAsFlt(k));
	}

	/**
	* 'ToFlt' - first, actual value will be converted to primitive type (contrary to the object).
	* 'With' - call of main predicate will be prefixed (contrary to affixed) with additional argument(s).
	* 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K, V1, V2> LFunction<K, String> haveToFltWithEx(@Nonnull LToFltFunction<K> extractor, V1 with1, V2 with2, @Nonnull LBiObjFltFunction<? super V1, ? super V2, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(with1, with2, extractor.applyAsFlt(k));
	}

	/**
	* 'ToFlt' - first, actual value will be converted to primitive type (contrary to the object).
	* 'With' - call of main predicate will be prefixed (contrary to affixed) with additional argument(s).
	*/
	public static @Nonnull <K, V1, V2> LPredicate<K> haveToFltWith(@Nonnull LToFltFunction<K> extractor, @Nonnull LBiObjFltPredicate<? super V1, ? super V2> predicate, V1 with1, V2 with2) {
		return haveToFltWith(extractor, with1, with2, predicate);
	}

	/**
	* 'ToFlt' - first, actual value will be converted to primitive type (contrary to the object).
	* 'With' - call of main predicate will be prefixed (contrary to affixed) with additional argument(s).
	* 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K, V1, V2> LFunction<K, String> haveToFltWithEx(@Nonnull LToFltFunction<K> extractor, @Nonnull LBiObjFltFunction<? super V1, ? super V2, String> specialPredicate, V1 with1, V2 with2) {
		return haveToFltWithEx(extractor, with1, with2, specialPredicate);
	}

	/**
	* 'ToFlt' - first, actual value will be converted to primitive type (contrary to the object).
	* 'With' - call of main predicate will be prefixed (contrary to affixed) with additional argument(s).
	*/
	public static @Nonnull <K, V1, V2> LPredicate<K> hasToFltWith(@Nonnull LToFltFunction<K> extractor, V1 with1, V2 with2, @Nonnull LBiObjFltPredicate<? super V1, ? super V2> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(with1, with2, extractor.applyAsFlt(k));
	}

	/**
	* 'ToFlt' - first, actual value will be converted to primitive type (contrary to the object).
	* 'With' - call of main predicate will be prefixed (contrary to affixed) with additional argument(s).
	* 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K, V1, V2> LFunction<K, String> hasToFltWithEx(@Nonnull LToFltFunction<K> extractor, V1 with1, V2 with2, @Nonnull LBiObjFltFunction<? super V1, ? super V2, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(with1, with2, extractor.applyAsFlt(k));
	}

	/**
	* 'ToFlt' - first, actual value will be converted to primitive type (contrary to the object).
	* 'With' - call of main predicate will be prefixed (contrary to affixed) with additional argument(s).
	*/
	public static @Nonnull <K, V1, V2> LPredicate<K> hasToFltWith(@Nonnull LToFltFunction<K> extractor, @Nonnull LBiObjFltPredicate<? super V1, ? super V2> predicate, V1 with1, V2 with2) {
		return hasToFltWith(extractor, with1, with2, predicate);
	}

	/**
	* 'ToFlt' - first, actual value will be converted to primitive type (contrary to the object).
	* 'With' - call of main predicate will be prefixed (contrary to affixed) with additional argument(s).
	* 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K, V1, V2> LFunction<K, String> hasToFltWithEx(@Nonnull LToFltFunction<K> extractor, @Nonnull LBiObjFltFunction<? super V1, ? super V2, String> specialPredicate, V1 with1, V2 with2) {
		return hasToFltWithEx(extractor, with1, with2, specialPredicate);
	}

	/** 'ToInt' - first, actual value will be converted to primitive type (contrary to the object). */
	public static @Nonnull <K> LPredicate<K> haveToInt(@Nonnull LToIntFunction<K> extractor, @Nonnull LIntPredicate predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.applyAsInt(k));
	}

	/**
	* 'ToInt' - first, actual value will be converted to primitive type (contrary to the object).
	* 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K> LFunction<K, String> haveToIntEx(@Nonnull LToIntFunction<K> extractor, @Nonnull LIntFunction<String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(extractor.applyAsInt(k));
	}

	/** 'ToInt' - first, actual value will be converted to primitive type (contrary to the object). */
	public static @Nonnull <K> LPredicate<K> hasToInt(@Nonnull LToIntFunction<K> extractor, @Nonnull LIntPredicate predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.applyAsInt(k));
	}

	/**
	* 'ToInt' - first, actual value will be converted to primitive type (contrary to the object).
	* 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K> LFunction<K, String> hasToIntEx(@Nonnull LToIntFunction<K> extractor, @Nonnull LIntFunction<String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(extractor.applyAsInt(k));
	}

	/** 'ToInt' - first, actual value will be converted to primitive type (contrary to the object). */
	public static @Nonnull <K> LPredicate<K> haveToInt(@Nonnull LToIntFunction<K> extractor, int a2, @Nonnull LBiIntPredicate predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.applyAsInt(k), a2);
	}

	/**
	* 'ToInt' - first, actual value will be converted to primitive type (contrary to the object).
	* 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K> LFunction<K, String> haveToIntEx(@Nonnull LToIntFunction<K> extractor, int a2, @Nonnull LBiIntFunction<String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(extractor.applyAsInt(k), a2);
	}

	/** 'ToInt' - first, actual value will be converted to primitive type (contrary to the object). */
	public static @Nonnull <K> LPredicate<K> haveToInt(@Nonnull LToIntFunction<K> extractor, @Nonnull LBiIntPredicate predicate, int a2) {
		return haveToInt(extractor, a2, predicate);
	}

	/**
	* 'ToInt' - first, actual value will be converted to primitive type (contrary to the object).
	* 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K> LFunction<K, String> haveToIntEx(@Nonnull LToIntFunction<K> extractor, @Nonnull LBiIntFunction<String> specialPredicate, int a2) {
		return haveToIntEx(extractor, a2, specialPredicate);
	}

	/** 'ToInt' - first, actual value will be converted to primitive type (contrary to the object). */
	public static @Nonnull <K> LPredicate<K> hasToInt(@Nonnull LToIntFunction<K> extractor, int a2, @Nonnull LBiIntPredicate predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.applyAsInt(k), a2);
	}

	/**
	* 'ToInt' - first, actual value will be converted to primitive type (contrary to the object).
	* 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K> LFunction<K, String> hasToIntEx(@Nonnull LToIntFunction<K> extractor, int a2, @Nonnull LBiIntFunction<String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(extractor.applyAsInt(k), a2);
	}

	/** 'ToInt' - first, actual value will be converted to primitive type (contrary to the object). */
	public static @Nonnull <K> LPredicate<K> hasToInt(@Nonnull LToIntFunction<K> extractor, @Nonnull LBiIntPredicate predicate, int a2) {
		return hasToInt(extractor, a2, predicate);
	}

	/**
	* 'ToInt' - first, actual value will be converted to primitive type (contrary to the object).
	* 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K> LFunction<K, String> hasToIntEx(@Nonnull LToIntFunction<K> extractor, @Nonnull LBiIntFunction<String> specialPredicate, int a2) {
		return hasToIntEx(extractor, a2, specialPredicate);
	}

	/** 'ToInt' - first, actual value will be converted to primitive type (contrary to the object). */
	public static @Nonnull <K> LPredicate<K> haveToInt(@Nonnull LToIntFunction<K> extractor, int a2, int a3, @Nonnull LTriIntPredicate predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.applyAsInt(k), a2, a3);
	}

	/** 'ToInt' - first, actual value will be converted to primitive type (contrary to the object). */
	public static @Nonnull <K> LPredicate<K> haveToInt(@Nonnull LToIntFunction<K> extractor, @Nonnull LTriIntPredicate predicate, int a2, int a3) {
		return haveToInt(extractor, a2, a3, predicate);
	}

	/** 'ToInt' - first, actual value will be converted to primitive type (contrary to the object). */
	public static @Nonnull <K> LPredicate<K> hasToInt(@Nonnull LToIntFunction<K> extractor, int a2, int a3, @Nonnull LTriIntPredicate predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.applyAsInt(k), a2, a3);
	}

	/** 'ToInt' - first, actual value will be converted to primitive type (contrary to the object). */
	public static @Nonnull <K> LPredicate<K> hasToInt(@Nonnull LToIntFunction<K> extractor, @Nonnull LTriIntPredicate predicate, int a2, int a3) {
		return hasToInt(extractor, a2, a3, predicate);
	}

	/**
	* 'ToInt' - first, actual value will be converted to primitive type (contrary to the object).
	* '_' - the predicate function is a variant or arguments represented by other function.
	*/
	public static @Nonnull <K> LPredicate<K> haveToIntBool_(@Nonnull LToIntFunction<K> extractor, boolean v, @Nonnull LBoolIntPredicate.LIntBoolPred predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.testIntBool(extractor.applyAsInt(k), v);
	}

	/**
	* 'ToInt' - first, actual value will be converted to primitive type (contrary to the object).
	* '_' - the predicate function is a variant or arguments represented by other function.
	*/
	public static @Nonnull <K> LPredicate<K> haveToIntBool_(@Nonnull LToIntFunction<K> extractor, @Nonnull LBoolIntPredicate.LIntBoolPred predicate, boolean v) {
		return haveToIntBool_(extractor, v, predicate);
	}

	/**
	* 'ToInt' - first, actual value will be converted to primitive type (contrary to the object).
	* '_' - the predicate function is a variant or arguments represented by other function.
	*/
	public static @Nonnull <K> LPredicate<K> hasToIntBool_(@Nonnull LToIntFunction<K> extractor, boolean v, @Nonnull LBoolIntPredicate.LIntBoolPred predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.testIntBool(extractor.applyAsInt(k), v);
	}

	/**
	* 'ToInt' - first, actual value will be converted to primitive type (contrary to the object).
	* '_' - the predicate function is a variant or arguments represented by other function.
	*/
	public static @Nonnull <K> LPredicate<K> hasToIntBool_(@Nonnull LToIntFunction<K> extractor, @Nonnull LBoolIntPredicate.LIntBoolPred predicate, boolean v) {
		return hasToIntBool_(extractor, v, predicate);
	}

	/**
	* 'ToInt' - first, actual value will be converted to primitive type (contrary to the object).
	* '_' - the predicate function is a variant or arguments represented by other function.
	*/
	public static @Nonnull <K> LPredicate<K> haveToIntByte_(@Nonnull LToIntFunction<K> extractor, byte v, @Nonnull LByteIntPredicate.LIntBytePred predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.testIntByte(extractor.applyAsInt(k), v);
	}

	/**
	* 'ToInt' - first, actual value will be converted to primitive type (contrary to the object).
	* '_' - the predicate function is a variant or arguments represented by other function.
	*/
	public static @Nonnull <K> LPredicate<K> haveToIntByte_(@Nonnull LToIntFunction<K> extractor, @Nonnull LByteIntPredicate.LIntBytePred predicate, byte v) {
		return haveToIntByte_(extractor, v, predicate);
	}

	/**
	* 'ToInt' - first, actual value will be converted to primitive type (contrary to the object).
	* '_' - the predicate function is a variant or arguments represented by other function.
	*/
	public static @Nonnull <K> LPredicate<K> hasToIntByte_(@Nonnull LToIntFunction<K> extractor, byte v, @Nonnull LByteIntPredicate.LIntBytePred predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.testIntByte(extractor.applyAsInt(k), v);
	}

	/**
	* 'ToInt' - first, actual value will be converted to primitive type (contrary to the object).
	* '_' - the predicate function is a variant or arguments represented by other function.
	*/
	public static @Nonnull <K> LPredicate<K> hasToIntByte_(@Nonnull LToIntFunction<K> extractor, @Nonnull LByteIntPredicate.LIntBytePred predicate, byte v) {
		return hasToIntByte_(extractor, v, predicate);
	}

	/**
	* 'ToInt' - first, actual value will be converted to primitive type (contrary to the object).
	* '_' - the predicate function is a variant or arguments represented by other function.
	*/
	public static @Nonnull <K> LPredicate<K> haveToIntDbl_(@Nonnull LToIntFunction<K> extractor, double v, @Nonnull LDblIntPredicate.LIntDblPred predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.testIntDbl(extractor.applyAsInt(k), v);
	}

	/**
	* 'ToInt' - first, actual value will be converted to primitive type (contrary to the object).
	* '_' - the predicate function is a variant or arguments represented by other function.
	*/
	public static @Nonnull <K> LPredicate<K> haveToIntDbl_(@Nonnull LToIntFunction<K> extractor, @Nonnull LDblIntPredicate.LIntDblPred predicate, double v) {
		return haveToIntDbl_(extractor, v, predicate);
	}

	/**
	* 'ToInt' - first, actual value will be converted to primitive type (contrary to the object).
	* '_' - the predicate function is a variant or arguments represented by other function.
	*/
	public static @Nonnull <K> LPredicate<K> hasToIntDbl_(@Nonnull LToIntFunction<K> extractor, double v, @Nonnull LDblIntPredicate.LIntDblPred predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.testIntDbl(extractor.applyAsInt(k), v);
	}

	/**
	* 'ToInt' - first, actual value will be converted to primitive type (contrary to the object).
	* '_' - the predicate function is a variant or arguments represented by other function.
	*/
	public static @Nonnull <K> LPredicate<K> hasToIntDbl_(@Nonnull LToIntFunction<K> extractor, @Nonnull LDblIntPredicate.LIntDblPred predicate, double v) {
		return hasToIntDbl_(extractor, v, predicate);
	}

	/**
	* 'ToInt' - first, actual value will be converted to primitive type (contrary to the object).
	* '_' - the predicate function is a variant or arguments represented by other function.
	*/
	public static @Nonnull <K> LPredicate<K> haveToIntChar_(@Nonnull LToIntFunction<K> extractor, char v, @Nonnull LCharIntPredicate.LIntCharPred predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.testIntChar(extractor.applyAsInt(k), v);
	}

	/**
	* 'ToInt' - first, actual value will be converted to primitive type (contrary to the object).
	* '_' - the predicate function is a variant or arguments represented by other function.
	*/
	public static @Nonnull <K> LPredicate<K> haveToIntChar_(@Nonnull LToIntFunction<K> extractor, @Nonnull LCharIntPredicate.LIntCharPred predicate, char v) {
		return haveToIntChar_(extractor, v, predicate);
	}

	/**
	* 'ToInt' - first, actual value will be converted to primitive type (contrary to the object).
	* '_' - the predicate function is a variant or arguments represented by other function.
	*/
	public static @Nonnull <K> LPredicate<K> hasToIntChar_(@Nonnull LToIntFunction<K> extractor, char v, @Nonnull LCharIntPredicate.LIntCharPred predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.testIntChar(extractor.applyAsInt(k), v);
	}

	/**
	* 'ToInt' - first, actual value will be converted to primitive type (contrary to the object).
	* '_' - the predicate function is a variant or arguments represented by other function.
	*/
	public static @Nonnull <K> LPredicate<K> hasToIntChar_(@Nonnull LToIntFunction<K> extractor, @Nonnull LCharIntPredicate.LIntCharPred predicate, char v) {
		return hasToIntChar_(extractor, v, predicate);
	}

	/**
	* 'ToInt' - first, actual value will be converted to primitive type (contrary to the object).
	* '_' - the predicate function is a variant or arguments represented by other function.
	*/
	public static @Nonnull <K> LPredicate<K> haveToIntSrt_(@Nonnull LToIntFunction<K> extractor, short v, @Nonnull LSrtIntPredicate.LIntSrtPred predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.testIntSrt(extractor.applyAsInt(k), v);
	}

	/**
	* 'ToInt' - first, actual value will be converted to primitive type (contrary to the object).
	* '_' - the predicate function is a variant or arguments represented by other function.
	*/
	public static @Nonnull <K> LPredicate<K> haveToIntSrt_(@Nonnull LToIntFunction<K> extractor, @Nonnull LSrtIntPredicate.LIntSrtPred predicate, short v) {
		return haveToIntSrt_(extractor, v, predicate);
	}

	/**
	* 'ToInt' - first, actual value will be converted to primitive type (contrary to the object).
	* '_' - the predicate function is a variant or arguments represented by other function.
	*/
	public static @Nonnull <K> LPredicate<K> hasToIntSrt_(@Nonnull LToIntFunction<K> extractor, short v, @Nonnull LSrtIntPredicate.LIntSrtPred predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.testIntSrt(extractor.applyAsInt(k), v);
	}

	/**
	* 'ToInt' - first, actual value will be converted to primitive type (contrary to the object).
	* '_' - the predicate function is a variant or arguments represented by other function.
	*/
	public static @Nonnull <K> LPredicate<K> hasToIntSrt_(@Nonnull LToIntFunction<K> extractor, @Nonnull LSrtIntPredicate.LIntSrtPred predicate, short v) {
		return hasToIntSrt_(extractor, v, predicate);
	}

	/**
	* 'ToInt' - first, actual value will be converted to primitive type (contrary to the object).
	* '_' - the predicate function is a variant or arguments represented by other function.
	*/
	public static @Nonnull <K> LPredicate<K> haveToIntFlt_(@Nonnull LToIntFunction<K> extractor, float v, @Nonnull LFltIntPredicate.LIntFltPred predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.testIntFlt(extractor.applyAsInt(k), v);
	}

	/**
	* 'ToInt' - first, actual value will be converted to primitive type (contrary to the object).
	* '_' - the predicate function is a variant or arguments represented by other function.
	*/
	public static @Nonnull <K> LPredicate<K> haveToIntFlt_(@Nonnull LToIntFunction<K> extractor, @Nonnull LFltIntPredicate.LIntFltPred predicate, float v) {
		return haveToIntFlt_(extractor, v, predicate);
	}

	/**
	* 'ToInt' - first, actual value will be converted to primitive type (contrary to the object).
	* '_' - the predicate function is a variant or arguments represented by other function.
	*/
	public static @Nonnull <K> LPredicate<K> hasToIntFlt_(@Nonnull LToIntFunction<K> extractor, float v, @Nonnull LFltIntPredicate.LIntFltPred predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.testIntFlt(extractor.applyAsInt(k), v);
	}

	/**
	* 'ToInt' - first, actual value will be converted to primitive type (contrary to the object).
	* '_' - the predicate function is a variant or arguments represented by other function.
	*/
	public static @Nonnull <K> LPredicate<K> hasToIntFlt_(@Nonnull LToIntFunction<K> extractor, @Nonnull LFltIntPredicate.LIntFltPred predicate, float v) {
		return hasToIntFlt_(extractor, v, predicate);
	}

	/**
	* 'ToInt' - first, actual value will be converted to primitive type (contrary to the object).
	* '_' - the predicate function is a variant or arguments represented by other function.
	*/
	public static @Nonnull <K> LPredicate<K> haveToIntLong_(@Nonnull LToIntFunction<K> extractor, long v, @Nonnull LLongIntPredicate.LIntLongPred predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.testIntLong(extractor.applyAsInt(k), v);
	}

	/**
	* 'ToInt' - first, actual value will be converted to primitive type (contrary to the object).
	* '_' - the predicate function is a variant or arguments represented by other function.
	*/
	public static @Nonnull <K> LPredicate<K> haveToIntLong_(@Nonnull LToIntFunction<K> extractor, @Nonnull LLongIntPredicate.LIntLongPred predicate, long v) {
		return haveToIntLong_(extractor, v, predicate);
	}

	/**
	* 'ToInt' - first, actual value will be converted to primitive type (contrary to the object).
	* '_' - the predicate function is a variant or arguments represented by other function.
	*/
	public static @Nonnull <K> LPredicate<K> hasToIntLong_(@Nonnull LToIntFunction<K> extractor, long v, @Nonnull LLongIntPredicate.LIntLongPred predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.testIntLong(extractor.applyAsInt(k), v);
	}

	/**
	* 'ToInt' - first, actual value will be converted to primitive type (contrary to the object).
	* '_' - the predicate function is a variant or arguments represented by other function.
	*/
	public static @Nonnull <K> LPredicate<K> hasToIntLong_(@Nonnull LToIntFunction<K> extractor, @Nonnull LLongIntPredicate.LIntLongPred predicate, long v) {
		return hasToIntLong_(extractor, v, predicate);
	}

	/**
	* 'ToInt' - first, actual value will be converted to primitive type (contrary to the object).
	* '_' - the predicate function is a variant or arguments represented by other function.
	*/
	public static @Nonnull <K, V> LPredicate<K> haveToInt_(@Nonnull LToIntFunction<K> extractor, V v, @Nonnull LObjIntPredicate.LIntObjPred<? super V> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.testIntObj(extractor.applyAsInt(k), v);
	}

	/**
	* 'ToInt' - first, actual value will be converted to primitive type (contrary to the object).
	* 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	* '_' - the predicate function is a variant or arguments represented by other function.
	*/
	public static @Nonnull <K, V> LFunction<K, String> haveToIntEx_(@Nonnull LToIntFunction<K> extractor, V v, @Nonnull LOiFunction.LIntObjFunc<? super V, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.applyIntObj(extractor.applyAsInt(k), v);
	}

	/**
	* 'ToInt' - first, actual value will be converted to primitive type (contrary to the object).
	* '_' - the predicate function is a variant or arguments represented by other function.
	*/
	public static @Nonnull <K, V> LPredicate<K> haveToInt_(@Nonnull LToIntFunction<K> extractor, @Nonnull LObjIntPredicate.LIntObjPred<? super V> predicate, V v) {
		return haveToInt_(extractor, v, predicate);
	}

	/**
	* 'ToInt' - first, actual value will be converted to primitive type (contrary to the object).
	* 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	* '_' - the predicate function is a variant or arguments represented by other function.
	*/
	public static @Nonnull <K, V> LFunction<K, String> haveToIntEx_(@Nonnull LToIntFunction<K> extractor, @Nonnull LOiFunction.LIntObjFunc<? super V, String> specialPredicate, V v) {
		return haveToIntEx_(extractor, v, specialPredicate);
	}

	/**
	* 'ToInt' - first, actual value will be converted to primitive type (contrary to the object).
	* '_' - the predicate function is a variant or arguments represented by other function.
	*/
	public static @Nonnull <K, V> LPredicate<K> hasToInt_(@Nonnull LToIntFunction<K> extractor, V v, @Nonnull LObjIntPredicate.LIntObjPred<? super V> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.testIntObj(extractor.applyAsInt(k), v);
	}

	/**
	* 'ToInt' - first, actual value will be converted to primitive type (contrary to the object).
	* 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	* '_' - the predicate function is a variant or arguments represented by other function.
	*/
	public static @Nonnull <K, V> LFunction<K, String> hasToIntEx_(@Nonnull LToIntFunction<K> extractor, V v, @Nonnull LOiFunction.LIntObjFunc<? super V, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.applyIntObj(extractor.applyAsInt(k), v);
	}

	/**
	* 'ToInt' - first, actual value will be converted to primitive type (contrary to the object).
	* '_' - the predicate function is a variant or arguments represented by other function.
	*/
	public static @Nonnull <K, V> LPredicate<K> hasToInt_(@Nonnull LToIntFunction<K> extractor, @Nonnull LObjIntPredicate.LIntObjPred<? super V> predicate, V v) {
		return hasToInt_(extractor, v, predicate);
	}

	/**
	* 'ToInt' - first, actual value will be converted to primitive type (contrary to the object).
	* 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	* '_' - the predicate function is a variant or arguments represented by other function.
	*/
	public static @Nonnull <K, V> LFunction<K, String> hasToIntEx_(@Nonnull LToIntFunction<K> extractor, @Nonnull LOiFunction.LIntObjFunc<? super V, String> specialPredicate, V v) {
		return hasToIntEx_(extractor, v, specialPredicate);
	}

	/**
	* 'ToInt' - first, actual value will be converted to primitive type (contrary to the object).
	* 'With' - call of main predicate will be prefixed (contrary to affixed) with additional argument(s).
	*/
	public static @Nonnull <K, V1> LPredicate<K> haveToIntWith(@Nonnull LToIntFunction<K> extractor, V1 with1, @Nonnull LObjIntPredicate<? super V1> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(with1, extractor.applyAsInt(k));
	}

	/**
	* 'ToInt' - first, actual value will be converted to primitive type (contrary to the object).
	* 'With' - call of main predicate will be prefixed (contrary to affixed) with additional argument(s).
	* 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K, V1> LFunction<K, String> haveToIntWithEx(@Nonnull LToIntFunction<K> extractor, V1 with1, @Nonnull LOiFunction<? super V1, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(with1, extractor.applyAsInt(k));
	}

	/**
	* 'ToInt' - first, actual value will be converted to primitive type (contrary to the object).
	* 'With' - call of main predicate will be prefixed (contrary to affixed) with additional argument(s).
	*/
	public static @Nonnull <K, V1> LPredicate<K> haveToIntWith(@Nonnull LToIntFunction<K> extractor, @Nonnull LObjIntPredicate<? super V1> predicate, V1 with1) {
		return haveToIntWith(extractor, with1, predicate);
	}

	/**
	* 'ToInt' - first, actual value will be converted to primitive type (contrary to the object).
	* 'With' - call of main predicate will be prefixed (contrary to affixed) with additional argument(s).
	* 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K, V1> LFunction<K, String> haveToIntWithEx(@Nonnull LToIntFunction<K> extractor, @Nonnull LOiFunction<? super V1, String> specialPredicate, V1 with1) {
		return haveToIntWithEx(extractor, with1, specialPredicate);
	}

	/**
	* 'ToInt' - first, actual value will be converted to primitive type (contrary to the object).
	* 'With' - call of main predicate will be prefixed (contrary to affixed) with additional argument(s).
	*/
	public static @Nonnull <K, V1> LPredicate<K> hasToIntWith(@Nonnull LToIntFunction<K> extractor, V1 with1, @Nonnull LObjIntPredicate<? super V1> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(with1, extractor.applyAsInt(k));
	}

	/**
	* 'ToInt' - first, actual value will be converted to primitive type (contrary to the object).
	* 'With' - call of main predicate will be prefixed (contrary to affixed) with additional argument(s).
	* 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K, V1> LFunction<K, String> hasToIntWithEx(@Nonnull LToIntFunction<K> extractor, V1 with1, @Nonnull LOiFunction<? super V1, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(with1, extractor.applyAsInt(k));
	}

	/**
	* 'ToInt' - first, actual value will be converted to primitive type (contrary to the object).
	* 'With' - call of main predicate will be prefixed (contrary to affixed) with additional argument(s).
	*/
	public static @Nonnull <K, V1> LPredicate<K> hasToIntWith(@Nonnull LToIntFunction<K> extractor, @Nonnull LObjIntPredicate<? super V1> predicate, V1 with1) {
		return hasToIntWith(extractor, with1, predicate);
	}

	/**
	* 'ToInt' - first, actual value will be converted to primitive type (contrary to the object).
	* 'With' - call of main predicate will be prefixed (contrary to affixed) with additional argument(s).
	* 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K, V1> LFunction<K, String> hasToIntWithEx(@Nonnull LToIntFunction<K> extractor, @Nonnull LOiFunction<? super V1, String> specialPredicate, V1 with1) {
		return hasToIntWithEx(extractor, with1, specialPredicate);
	}

	/**
	* 'ToInt' - first, actual value will be converted to primitive type (contrary to the object).
	* 'With' - call of main predicate will be prefixed (contrary to affixed) with additional argument(s).
	*/
	public static @Nonnull <K, V1, V2> LPredicate<K> haveToIntWith(@Nonnull LToIntFunction<K> extractor, V1 with1, V2 with2, @Nonnull LBiObjIntPredicate<? super V1, ? super V2> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(with1, with2, extractor.applyAsInt(k));
	}

	/**
	* 'ToInt' - first, actual value will be converted to primitive type (contrary to the object).
	* 'With' - call of main predicate will be prefixed (contrary to affixed) with additional argument(s).
	* 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K, V1, V2> LFunction<K, String> haveToIntWithEx(@Nonnull LToIntFunction<K> extractor, V1 with1, V2 with2, @Nonnull LBiObjIntFunction<? super V1, ? super V2, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(with1, with2, extractor.applyAsInt(k));
	}

	/**
	* 'ToInt' - first, actual value will be converted to primitive type (contrary to the object).
	* 'With' - call of main predicate will be prefixed (contrary to affixed) with additional argument(s).
	*/
	public static @Nonnull <K, V1, V2> LPredicate<K> haveToIntWith(@Nonnull LToIntFunction<K> extractor, @Nonnull LBiObjIntPredicate<? super V1, ? super V2> predicate, V1 with1, V2 with2) {
		return haveToIntWith(extractor, with1, with2, predicate);
	}

	/**
	* 'ToInt' - first, actual value will be converted to primitive type (contrary to the object).
	* 'With' - call of main predicate will be prefixed (contrary to affixed) with additional argument(s).
	* 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K, V1, V2> LFunction<K, String> haveToIntWithEx(@Nonnull LToIntFunction<K> extractor, @Nonnull LBiObjIntFunction<? super V1, ? super V2, String> specialPredicate, V1 with1, V2 with2) {
		return haveToIntWithEx(extractor, with1, with2, specialPredicate);
	}

	/**
	* 'ToInt' - first, actual value will be converted to primitive type (contrary to the object).
	* 'With' - call of main predicate will be prefixed (contrary to affixed) with additional argument(s).
	*/
	public static @Nonnull <K, V1, V2> LPredicate<K> hasToIntWith(@Nonnull LToIntFunction<K> extractor, V1 with1, V2 with2, @Nonnull LBiObjIntPredicate<? super V1, ? super V2> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(with1, with2, extractor.applyAsInt(k));
	}

	/**
	* 'ToInt' - first, actual value will be converted to primitive type (contrary to the object).
	* 'With' - call of main predicate will be prefixed (contrary to affixed) with additional argument(s).
	* 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K, V1, V2> LFunction<K, String> hasToIntWithEx(@Nonnull LToIntFunction<K> extractor, V1 with1, V2 with2, @Nonnull LBiObjIntFunction<? super V1, ? super V2, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(with1, with2, extractor.applyAsInt(k));
	}

	/**
	* 'ToInt' - first, actual value will be converted to primitive type (contrary to the object).
	* 'With' - call of main predicate will be prefixed (contrary to affixed) with additional argument(s).
	*/
	public static @Nonnull <K, V1, V2> LPredicate<K> hasToIntWith(@Nonnull LToIntFunction<K> extractor, @Nonnull LBiObjIntPredicate<? super V1, ? super V2> predicate, V1 with1, V2 with2) {
		return hasToIntWith(extractor, with1, with2, predicate);
	}

	/**
	* 'ToInt' - first, actual value will be converted to primitive type (contrary to the object).
	* 'With' - call of main predicate will be prefixed (contrary to affixed) with additional argument(s).
	* 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K, V1, V2> LFunction<K, String> hasToIntWithEx(@Nonnull LToIntFunction<K> extractor, @Nonnull LBiObjIntFunction<? super V1, ? super V2, String> specialPredicate, V1 with1, V2 with2) {
		return hasToIntWithEx(extractor, with1, with2, specialPredicate);
	}

	/** 'ToLong' - first, actual value will be converted to primitive type (contrary to the object). */
	public static @Nonnull <K> LPredicate<K> haveToLong(@Nonnull LToLongFunction<K> extractor, @Nonnull LLongPredicate predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.applyAsLong(k));
	}

	/**
	* 'ToLong' - first, actual value will be converted to primitive type (contrary to the object).
	* 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K> LFunction<K, String> haveToLongEx(@Nonnull LToLongFunction<K> extractor, @Nonnull LLongFunction<String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(extractor.applyAsLong(k));
	}

	/** 'ToLong' - first, actual value will be converted to primitive type (contrary to the object). */
	public static @Nonnull <K> LPredicate<K> hasToLong(@Nonnull LToLongFunction<K> extractor, @Nonnull LLongPredicate predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.applyAsLong(k));
	}

	/**
	* 'ToLong' - first, actual value will be converted to primitive type (contrary to the object).
	* 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K> LFunction<K, String> hasToLongEx(@Nonnull LToLongFunction<K> extractor, @Nonnull LLongFunction<String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(extractor.applyAsLong(k));
	}

	/** 'ToLong' - first, actual value will be converted to primitive type (contrary to the object). */
	public static @Nonnull <K> LPredicate<K> haveToLong(@Nonnull LToLongFunction<K> extractor, long a2, @Nonnull LBiLongPredicate predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.applyAsLong(k), a2);
	}

	/**
	* 'ToLong' - first, actual value will be converted to primitive type (contrary to the object).
	* 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K> LFunction<K, String> haveToLongEx(@Nonnull LToLongFunction<K> extractor, long a2, @Nonnull LBiLongFunction<String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(extractor.applyAsLong(k), a2);
	}

	/** 'ToLong' - first, actual value will be converted to primitive type (contrary to the object). */
	public static @Nonnull <K> LPredicate<K> haveToLong(@Nonnull LToLongFunction<K> extractor, @Nonnull LBiLongPredicate predicate, long a2) {
		return haveToLong(extractor, a2, predicate);
	}

	/**
	* 'ToLong' - first, actual value will be converted to primitive type (contrary to the object).
	* 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K> LFunction<K, String> haveToLongEx(@Nonnull LToLongFunction<K> extractor, @Nonnull LBiLongFunction<String> specialPredicate, long a2) {
		return haveToLongEx(extractor, a2, specialPredicate);
	}

	/** 'ToLong' - first, actual value will be converted to primitive type (contrary to the object). */
	public static @Nonnull <K> LPredicate<K> hasToLong(@Nonnull LToLongFunction<K> extractor, long a2, @Nonnull LBiLongPredicate predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.applyAsLong(k), a2);
	}

	/**
	* 'ToLong' - first, actual value will be converted to primitive type (contrary to the object).
	* 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K> LFunction<K, String> hasToLongEx(@Nonnull LToLongFunction<K> extractor, long a2, @Nonnull LBiLongFunction<String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(extractor.applyAsLong(k), a2);
	}

	/** 'ToLong' - first, actual value will be converted to primitive type (contrary to the object). */
	public static @Nonnull <K> LPredicate<K> hasToLong(@Nonnull LToLongFunction<K> extractor, @Nonnull LBiLongPredicate predicate, long a2) {
		return hasToLong(extractor, a2, predicate);
	}

	/**
	* 'ToLong' - first, actual value will be converted to primitive type (contrary to the object).
	* 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K> LFunction<K, String> hasToLongEx(@Nonnull LToLongFunction<K> extractor, @Nonnull LBiLongFunction<String> specialPredicate, long a2) {
		return hasToLongEx(extractor, a2, specialPredicate);
	}

	/** 'ToLong' - first, actual value will be converted to primitive type (contrary to the object). */
	public static @Nonnull <K> LPredicate<K> haveToLong(@Nonnull LToLongFunction<K> extractor, long a2, long a3, @Nonnull LTriLongPredicate predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.applyAsLong(k), a2, a3);
	}

	/** 'ToLong' - first, actual value will be converted to primitive type (contrary to the object). */
	public static @Nonnull <K> LPredicate<K> haveToLong(@Nonnull LToLongFunction<K> extractor, @Nonnull LTriLongPredicate predicate, long a2, long a3) {
		return haveToLong(extractor, a2, a3, predicate);
	}

	/** 'ToLong' - first, actual value will be converted to primitive type (contrary to the object). */
	public static @Nonnull <K> LPredicate<K> hasToLong(@Nonnull LToLongFunction<K> extractor, long a2, long a3, @Nonnull LTriLongPredicate predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.applyAsLong(k), a2, a3);
	}

	/** 'ToLong' - first, actual value will be converted to primitive type (contrary to the object). */
	public static @Nonnull <K> LPredicate<K> hasToLong(@Nonnull LToLongFunction<K> extractor, @Nonnull LTriLongPredicate predicate, long a2, long a3) {
		return hasToLong(extractor, a2, a3, predicate);
	}

	/** 'ToLong' - first, actual value will be converted to primitive type (contrary to the object). */
	public static @Nonnull <K> LPredicate<K> haveToLongInt(@Nonnull LToLongFunction<K> extractor, int v, @Nonnull LLongIntPredicate predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.applyAsLong(k), v);
	}

	/** 'ToLong' - first, actual value will be converted to primitive type (contrary to the object). */
	public static @Nonnull <K> LPredicate<K> haveToLongInt(@Nonnull LToLongFunction<K> extractor, @Nonnull LLongIntPredicate predicate, int v) {
		return haveToLongInt(extractor, v, predicate);
	}

	/** 'ToLong' - first, actual value will be converted to primitive type (contrary to the object). */
	public static @Nonnull <K> LPredicate<K> hasToLongInt(@Nonnull LToLongFunction<K> extractor, int v, @Nonnull LLongIntPredicate predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.applyAsLong(k), v);
	}

	/** 'ToLong' - first, actual value will be converted to primitive type (contrary to the object). */
	public static @Nonnull <K> LPredicate<K> hasToLongInt(@Nonnull LToLongFunction<K> extractor, @Nonnull LLongIntPredicate predicate, int v) {
		return hasToLongInt(extractor, v, predicate);
	}

	/**
	* 'ToLong' - first, actual value will be converted to primitive type (contrary to the object).
	* '_' - the predicate function is a variant or arguments represented by other function.
	*/
	public static @Nonnull <K, V> LPredicate<K> haveToLong_(@Nonnull LToLongFunction<K> extractor, V v, @Nonnull LObjLongPredicate.LLongObjPred<? super V> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.testLongObj(extractor.applyAsLong(k), v);
	}

	/**
	* 'ToLong' - first, actual value will be converted to primitive type (contrary to the object).
	* 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	* '_' - the predicate function is a variant or arguments represented by other function.
	*/
	public static @Nonnull <K, V> LFunction<K, String> haveToLongEx_(@Nonnull LToLongFunction<K> extractor, V v, @Nonnull LObjLongFunction.LLongObjFunc<? super V, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.applyLongObj(extractor.applyAsLong(k), v);
	}

	/**
	* 'ToLong' - first, actual value will be converted to primitive type (contrary to the object).
	* '_' - the predicate function is a variant or arguments represented by other function.
	*/
	public static @Nonnull <K, V> LPredicate<K> haveToLong_(@Nonnull LToLongFunction<K> extractor, @Nonnull LObjLongPredicate.LLongObjPred<? super V> predicate, V v) {
		return haveToLong_(extractor, v, predicate);
	}

	/**
	* 'ToLong' - first, actual value will be converted to primitive type (contrary to the object).
	* 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	* '_' - the predicate function is a variant or arguments represented by other function.
	*/
	public static @Nonnull <K, V> LFunction<K, String> haveToLongEx_(@Nonnull LToLongFunction<K> extractor, @Nonnull LObjLongFunction.LLongObjFunc<? super V, String> specialPredicate, V v) {
		return haveToLongEx_(extractor, v, specialPredicate);
	}

	/**
	* 'ToLong' - first, actual value will be converted to primitive type (contrary to the object).
	* '_' - the predicate function is a variant or arguments represented by other function.
	*/
	public static @Nonnull <K, V> LPredicate<K> hasToLong_(@Nonnull LToLongFunction<K> extractor, V v, @Nonnull LObjLongPredicate.LLongObjPred<? super V> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.testLongObj(extractor.applyAsLong(k), v);
	}

	/**
	* 'ToLong' - first, actual value will be converted to primitive type (contrary to the object).
	* 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	* '_' - the predicate function is a variant or arguments represented by other function.
	*/
	public static @Nonnull <K, V> LFunction<K, String> hasToLongEx_(@Nonnull LToLongFunction<K> extractor, V v, @Nonnull LObjLongFunction.LLongObjFunc<? super V, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.applyLongObj(extractor.applyAsLong(k), v);
	}

	/**
	* 'ToLong' - first, actual value will be converted to primitive type (contrary to the object).
	* '_' - the predicate function is a variant or arguments represented by other function.
	*/
	public static @Nonnull <K, V> LPredicate<K> hasToLong_(@Nonnull LToLongFunction<K> extractor, @Nonnull LObjLongPredicate.LLongObjPred<? super V> predicate, V v) {
		return hasToLong_(extractor, v, predicate);
	}

	/**
	* 'ToLong' - first, actual value will be converted to primitive type (contrary to the object).
	* 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	* '_' - the predicate function is a variant or arguments represented by other function.
	*/
	public static @Nonnull <K, V> LFunction<K, String> hasToLongEx_(@Nonnull LToLongFunction<K> extractor, @Nonnull LObjLongFunction.LLongObjFunc<? super V, String> specialPredicate, V v) {
		return hasToLongEx_(extractor, v, specialPredicate);
	}

	/**
	* 'ToLong' - first, actual value will be converted to primitive type (contrary to the object).
	* 'With' - call of main predicate will be prefixed (contrary to affixed) with additional argument(s).
	*/
	public static @Nonnull <K, V1> LPredicate<K> haveToLongWith(@Nonnull LToLongFunction<K> extractor, V1 with1, @Nonnull LObjLongPredicate<? super V1> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(with1, extractor.applyAsLong(k));
	}

	/**
	* 'ToLong' - first, actual value will be converted to primitive type (contrary to the object).
	* 'With' - call of main predicate will be prefixed (contrary to affixed) with additional argument(s).
	* 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K, V1> LFunction<K, String> haveToLongWithEx(@Nonnull LToLongFunction<K> extractor, V1 with1, @Nonnull LObjLongFunction<? super V1, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(with1, extractor.applyAsLong(k));
	}

	/**
	* 'ToLong' - first, actual value will be converted to primitive type (contrary to the object).
	* 'With' - call of main predicate will be prefixed (contrary to affixed) with additional argument(s).
	*/
	public static @Nonnull <K, V1> LPredicate<K> haveToLongWith(@Nonnull LToLongFunction<K> extractor, @Nonnull LObjLongPredicate<? super V1> predicate, V1 with1) {
		return haveToLongWith(extractor, with1, predicate);
	}

	/**
	* 'ToLong' - first, actual value will be converted to primitive type (contrary to the object).
	* 'With' - call of main predicate will be prefixed (contrary to affixed) with additional argument(s).
	* 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K, V1> LFunction<K, String> haveToLongWithEx(@Nonnull LToLongFunction<K> extractor, @Nonnull LObjLongFunction<? super V1, String> specialPredicate, V1 with1) {
		return haveToLongWithEx(extractor, with1, specialPredicate);
	}

	/**
	* 'ToLong' - first, actual value will be converted to primitive type (contrary to the object).
	* 'With' - call of main predicate will be prefixed (contrary to affixed) with additional argument(s).
	*/
	public static @Nonnull <K, V1> LPredicate<K> hasToLongWith(@Nonnull LToLongFunction<K> extractor, V1 with1, @Nonnull LObjLongPredicate<? super V1> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(with1, extractor.applyAsLong(k));
	}

	/**
	* 'ToLong' - first, actual value will be converted to primitive type (contrary to the object).
	* 'With' - call of main predicate will be prefixed (contrary to affixed) with additional argument(s).
	* 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K, V1> LFunction<K, String> hasToLongWithEx(@Nonnull LToLongFunction<K> extractor, V1 with1, @Nonnull LObjLongFunction<? super V1, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(with1, extractor.applyAsLong(k));
	}

	/**
	* 'ToLong' - first, actual value will be converted to primitive type (contrary to the object).
	* 'With' - call of main predicate will be prefixed (contrary to affixed) with additional argument(s).
	*/
	public static @Nonnull <K, V1> LPredicate<K> hasToLongWith(@Nonnull LToLongFunction<K> extractor, @Nonnull LObjLongPredicate<? super V1> predicate, V1 with1) {
		return hasToLongWith(extractor, with1, predicate);
	}

	/**
	* 'ToLong' - first, actual value will be converted to primitive type (contrary to the object).
	* 'With' - call of main predicate will be prefixed (contrary to affixed) with additional argument(s).
	* 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K, V1> LFunction<K, String> hasToLongWithEx(@Nonnull LToLongFunction<K> extractor, @Nonnull LObjLongFunction<? super V1, String> specialPredicate, V1 with1) {
		return hasToLongWithEx(extractor, with1, specialPredicate);
	}

	/**
	* 'ToLong' - first, actual value will be converted to primitive type (contrary to the object).
	* 'With' - call of main predicate will be prefixed (contrary to affixed) with additional argument(s).
	*/
	public static @Nonnull <K, V1, V2> LPredicate<K> haveToLongWith(@Nonnull LToLongFunction<K> extractor, V1 with1, V2 with2, @Nonnull LBiObjLongPredicate<? super V1, ? super V2> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(with1, with2, extractor.applyAsLong(k));
	}

	/**
	* 'ToLong' - first, actual value will be converted to primitive type (contrary to the object).
	* 'With' - call of main predicate will be prefixed (contrary to affixed) with additional argument(s).
	* 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K, V1, V2> LFunction<K, String> haveToLongWithEx(@Nonnull LToLongFunction<K> extractor, V1 with1, V2 with2, @Nonnull LBiObjLongFunction<? super V1, ? super V2, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(with1, with2, extractor.applyAsLong(k));
	}

	/**
	* 'ToLong' - first, actual value will be converted to primitive type (contrary to the object).
	* 'With' - call of main predicate will be prefixed (contrary to affixed) with additional argument(s).
	*/
	public static @Nonnull <K, V1, V2> LPredicate<K> haveToLongWith(@Nonnull LToLongFunction<K> extractor, @Nonnull LBiObjLongPredicate<? super V1, ? super V2> predicate, V1 with1, V2 with2) {
		return haveToLongWith(extractor, with1, with2, predicate);
	}

	/**
	* 'ToLong' - first, actual value will be converted to primitive type (contrary to the object).
	* 'With' - call of main predicate will be prefixed (contrary to affixed) with additional argument(s).
	* 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K, V1, V2> LFunction<K, String> haveToLongWithEx(@Nonnull LToLongFunction<K> extractor, @Nonnull LBiObjLongFunction<? super V1, ? super V2, String> specialPredicate, V1 with1, V2 with2) {
		return haveToLongWithEx(extractor, with1, with2, specialPredicate);
	}

	/**
	* 'ToLong' - first, actual value will be converted to primitive type (contrary to the object).
	* 'With' - call of main predicate will be prefixed (contrary to affixed) with additional argument(s).
	*/
	public static @Nonnull <K, V1, V2> LPredicate<K> hasToLongWith(@Nonnull LToLongFunction<K> extractor, V1 with1, V2 with2, @Nonnull LBiObjLongPredicate<? super V1, ? super V2> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(with1, with2, extractor.applyAsLong(k));
	}

	/**
	* 'ToLong' - first, actual value will be converted to primitive type (contrary to the object).
	* 'With' - call of main predicate will be prefixed (contrary to affixed) with additional argument(s).
	* 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K, V1, V2> LFunction<K, String> hasToLongWithEx(@Nonnull LToLongFunction<K> extractor, V1 with1, V2 with2, @Nonnull LBiObjLongFunction<? super V1, ? super V2, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(with1, with2, extractor.applyAsLong(k));
	}

	/**
	* 'ToLong' - first, actual value will be converted to primitive type (contrary to the object).
	* 'With' - call of main predicate will be prefixed (contrary to affixed) with additional argument(s).
	*/
	public static @Nonnull <K, V1, V2> LPredicate<K> hasToLongWith(@Nonnull LToLongFunction<K> extractor, @Nonnull LBiObjLongPredicate<? super V1, ? super V2> predicate, V1 with1, V2 with2) {
		return hasToLongWith(extractor, with1, with2, predicate);
	}

	/**
	* 'ToLong' - first, actual value will be converted to primitive type (contrary to the object).
	* 'With' - call of main predicate will be prefixed (contrary to affixed) with additional argument(s).
	* 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K, V1, V2> LFunction<K, String> hasToLongWithEx(@Nonnull LToLongFunction<K> extractor, @Nonnull LBiObjLongFunction<? super V1, ? super V2, String> specialPredicate, V1 with1, V2 with2) {
		return hasToLongWithEx(extractor, with1, with2, specialPredicate);
	}

	// </editor-fold>

}
