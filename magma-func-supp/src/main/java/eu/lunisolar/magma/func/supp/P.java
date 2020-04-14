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
	public static String same$(@Nullable Object n, @Nullable Object other) {
		return (n == other) ? null : String.format("Object <%s> must be the same as <%s>.", n, other);
	}

	/** Predicate: Object <%s> must NOT be the same as <%s>. Available in {@link P}, {@link Is}, {@link Be}, {@link P1}, {@link P2}.*/
	public static boolean notSame(@Nullable Object n, @Nullable Object other) {
		return !(n == other);
	}

	/** "Special" predicate: Object <%s> must NOT be the same as <%s>. Available in {@link P}, {@link Is}, {@link Be}, {@link P1}, {@link P2}.*/
	public static String notSame$(@Nullable Object n, @Nullable Object other) {
		return !(n == other) ? null : String.format("Object <%s> must NOT be the same as <%s>.", n, other);
	}

	/** Predicate: Reference must be null, currently is pointing to <%s>. Available in {@link P}, {@link Is}, {@link Be}, {@link P1}, {@link P2}.*/
	public static boolean Null(@Nullable Object n) {
		return n == null;
	}

	/** "Special" predicate: Reference must be null, currently is pointing to <%s>. Available in {@link P}, {@link Is}, {@link Be}, {@link P1}, {@link P2}.*/
	public static String Null$(@Nullable Object n) {
		return (n == null) ? null : String.format("Reference must be null, currently is pointing to <%s>.", n);
	}

	/** Predicate: Reference must NOT be null, currently is pointing to <%s>. Available in {@link P}, {@link Is}, {@link Be}, {@link P1}, {@link P2}.*/
	public static boolean notNull(@Nullable Object n) {
		return !(n == null);
	}

	/** "Special" predicate: Reference must NOT be null, currently is pointing to <%s>. Available in {@link P}, {@link Is}, {@link Be}, {@link P1}, {@link P2}.*/
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

	public static String noneNull$(@Nullable Object... objects) {
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

	public static String anyNull$(@Nullable Object... objects) {
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
	public static String length$(@Nonnull CharSequence s, int size) {
		Null.nonNullArg(s, "s");
		return (size == s.length()) ? null : String.format("String <'%s'> must be <%d> characters long.", s, size);
	}

	/** Predicate: String <'%s'> must NOT be <%d> characters long. Available in {@link P}, {@link Has}, {@link Have}, {@link Is}, {@link Be}.*/
	public static boolean lengthOtherThan(@Nonnull CharSequence s, int size) {
		Null.nonNullArg(s, "s");
		return !(size == s.length());
	}

	/** "Special" predicate: String <'%s'> must NOT be <%d> characters long. Available in {@link P}, {@link Has}, {@link Have}, {@link Is}, {@link Be}.*/
	public static String lengthOtherThan$(@Nonnull CharSequence s, int size) {
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
	public static String startWith$(@Nonnull String n, @Nonnull String a1) {
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
	public static String notStartWith$(@Nonnull String n, @Nonnull String a1) {
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
	public static String endWith$(@Nonnull String n, @Nonnull String a1) {
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
	public static String notEndWith$(@Nonnull String n, @Nonnull String a1) {
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
	public static String contain$(@Nonnull String n, @Nonnull String a1) {
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
	public static String notContain$(@Nonnull String n, @Nonnull String a1) {
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
	public static String empty$(@Nonnull String n) {
		Null.nonNullArg(n, "n");
		return (n.isEmpty()) ? null : String.format("String <'%s'> must be empty.", n);
	}

	/** Predicate: String <'%s'> must NOT be empty. Available in {@link P}, {@link Is}, {@link Be}, {@link P1}, {@link P2}.*/
	public static boolean notEmpty(@Nonnull String n) {
		Null.nonNullArg(n, "n");
		return !(n.isEmpty());
	}

	/** "Special" predicate: String <'%s'> must NOT be empty. Available in {@link P}, {@link Is}, {@link Be}, {@link P1}, {@link P2}.*/
	public static String notEmpty$(@Nonnull String n) {
		Null.nonNullArg(n, "n");
		return !(n.isEmpty()) ? null : String.format("String <'%s'> must NOT be empty.", n);
	}

	/** Predicate: String <'%s'> must be blank (empty or consisting of only white characters). Available in {@link P}, {@link Is}, {@link Be}, {@link P1}, {@link P2}.*/
	public static boolean blank(@Nonnull String n) {
		Null.nonNullArg(n, "n");
		return n.isBlank();
	}

	/** "Special" predicate: String <'%s'> must be blank (empty or consisting of only white characters). Available in {@link P}, {@link Is}, {@link Be}, {@link P1}, {@link P2}.*/
	public static String blank$(@Nonnull String n) {
		Null.nonNullArg(n, "n");
		return (n.isBlank()) ? null : String.format("String <'%s'> must be blank (empty or consisting of only white characters).", n);
	}

	/** Predicate: String <'%s'> must NOT be blank (empty or consisting of only white characters). Available in {@link P}, {@link Is}, {@link Be}, {@link P1}, {@link P2}.*/
	public static boolean notBlank(@Nonnull String n) {
		Null.nonNullArg(n, "n");
		return !(n.isBlank());
	}

	/** "Special" predicate: String <'%s'> must NOT be blank (empty or consisting of only white characters). Available in {@link P}, {@link Is}, {@link Be}, {@link P1}, {@link P2}.*/
	public static String notBlank$(@Nonnull String n) {
		Null.nonNullArg(n, "n");
		return !(n.isBlank()) ? null : String.format("String <'%s'> must NOT be blank (empty or consisting of only white characters).", n);
	}

	/** Predicate: String <'%s'> must be null or empty. Available in {@link P}, {@link Is}, {@link Be}, {@link P1}, {@link P2}.*/
	public static boolean nullOrEmpty(@Nonnull String n) {
		return n == null || n.isEmpty();
	}

	/** "Special" predicate: String <'%s'> must be null or empty. Available in {@link P}, {@link Is}, {@link Be}, {@link P1}, {@link P2}.*/
	public static String nullOrEmpty$(@Nonnull String n) {
		return (n == null || n.isEmpty()) ? null : String.format("String <'%s'> must be null or empty.", n);
	}

	/** Predicate: String <'%s'> must NOT be null or empty. Available in {@link P}, {@link Is}, {@link Be}, {@link P1}, {@link P2}.*/
	public static boolean notNullNorEmpty(@Nonnull String n) {
		return !(n == null || n.isEmpty());
	}

	/** "Special" predicate: String <'%s'> must NOT be null or empty. Available in {@link P}, {@link Is}, {@link Be}, {@link P1}, {@link P2}.*/
	public static String notNullNorEmpty$(@Nonnull String n) {
		return !(n == null || n.isEmpty()) ? null : String.format("String <'%s'> must NOT be null or empty.", n);
	}

	/** Predicate: String <'%s'> must be null or blank. Available in {@link P}, {@link Is}, {@link Be}, {@link P1}, {@link P2}.*/
	public static boolean nullOrBlank(@Nonnull String n) {
		return n == null || n.isBlank();
	}

	/** "Special" predicate: String <'%s'> must be null or blank. Available in {@link P}, {@link Is}, {@link Be}, {@link P1}, {@link P2}.*/
	public static String nullOrBlank$(@Nonnull String n) {
		return (n == null || n.isBlank()) ? null : String.format("String <'%s'> must be null or blank.", n);
	}

	/** Predicate: String <'%s'> must NOT be null or blank. Available in {@link P}, {@link Is}, {@link Be}, {@link P1}, {@link P2}.*/
	public static boolean notNullNorBlank(@Nonnull String n) {
		return !(n == null || n.isBlank());
	}

	/** "Special" predicate: String <'%s'> must NOT be null or blank. Available in {@link P}, {@link Is}, {@link Be}, {@link P1}, {@link P2}.*/
	public static String notNullNorBlank$(@Nonnull String n) {
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

	/** Predicate: Array <%s> must be of size %s. Available in {@link P}, {@link Has}, {@link Have}.*/
	public static boolean length(@Nonnull boolean[] array, int i) {
		Null.nonNullArg(array, "array");
		return Array.getLength(array) == i;
	}

	/** "Special" predicate: Array <%s> must be of size %s. Available in {@link P}, {@link Has}, {@link Have}.*/
	public static String length$(@Nonnull boolean[] array, int i) {
		Null.nonNullArg(array, "array");
		return (Array.getLength(array) == i) ? null : String.format("Array <%s> must be of size %s.", array, i);
	}

	/** Predicate: Array <%s> must NOT be of size %s. Available in {@link P}, {@link Has}, {@link Have}.*/
	public static boolean lengthOtherThan(@Nonnull boolean[] array, int i) {
		Null.nonNullArg(array, "array");
		return !(Array.getLength(array) == i);
	}

	/** "Special" predicate: Array <%s> must NOT be of size %s. Available in {@link P}, {@link Has}, {@link Have}.*/
	public static String lengthOtherThan$(@Nonnull boolean[] array, int i) {
		Null.nonNullArg(array, "array");
		return !(Array.getLength(array) == i) ? null : String.format("Array <%s> must NOT be of size %s.", array, i);
	}

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

	/** Predicate: Array <%s> must be of size %s. Available in {@link P}, {@link Has}, {@link Have}.*/
	public static boolean length(@Nonnull byte[] array, int i) {
		Null.nonNullArg(array, "array");
		return Array.getLength(array) == i;
	}

	/** "Special" predicate: Array <%s> must be of size %s. Available in {@link P}, {@link Has}, {@link Have}.*/
	public static String length$(@Nonnull byte[] array, int i) {
		Null.nonNullArg(array, "array");
		return (Array.getLength(array) == i) ? null : String.format("Array <%s> must be of size %s.", array, i);
	}

	/** Predicate: Array <%s> must NOT be of size %s. Available in {@link P}, {@link Has}, {@link Have}.*/
	public static boolean lengthOtherThan(@Nonnull byte[] array, int i) {
		Null.nonNullArg(array, "array");
		return !(Array.getLength(array) == i);
	}

	/** "Special" predicate: Array <%s> must NOT be of size %s. Available in {@link P}, {@link Has}, {@link Have}.*/
	public static String lengthOtherThan$(@Nonnull byte[] array, int i) {
		Null.nonNullArg(array, "array");
		return !(Array.getLength(array) == i) ? null : String.format("Array <%s> must NOT be of size %s.", array, i);
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

	/** Predicate: Array <%s> must be of size %s. Available in {@link P}, {@link Has}, {@link Have}.*/
	public static boolean length(@Nonnull double[] array, int i) {
		Null.nonNullArg(array, "array");
		return Array.getLength(array) == i;
	}

	/** "Special" predicate: Array <%s> must be of size %s. Available in {@link P}, {@link Has}, {@link Have}.*/
	public static String length$(@Nonnull double[] array, int i) {
		Null.nonNullArg(array, "array");
		return (Array.getLength(array) == i) ? null : String.format("Array <%s> must be of size %s.", array, i);
	}

	/** Predicate: Array <%s> must NOT be of size %s. Available in {@link P}, {@link Has}, {@link Have}.*/
	public static boolean lengthOtherThan(@Nonnull double[] array, int i) {
		Null.nonNullArg(array, "array");
		return !(Array.getLength(array) == i);
	}

	/** "Special" predicate: Array <%s> must NOT be of size %s. Available in {@link P}, {@link Has}, {@link Have}.*/
	public static String lengthOtherThan$(@Nonnull double[] array, int i) {
		Null.nonNullArg(array, "array");
		return !(Array.getLength(array) == i) ? null : String.format("Array <%s> must NOT be of size %s.", array, i);
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

	/** Predicate: Array <%s> must be of size %s. Available in {@link P}, {@link Has}, {@link Have}.*/
	public static boolean length(@Nonnull char[] array, int i) {
		Null.nonNullArg(array, "array");
		return Array.getLength(array) == i;
	}

	/** "Special" predicate: Array <%s> must be of size %s. Available in {@link P}, {@link Has}, {@link Have}.*/
	public static String length$(@Nonnull char[] array, int i) {
		Null.nonNullArg(array, "array");
		return (Array.getLength(array) == i) ? null : String.format("Array <%s> must be of size %s.", array, i);
	}

	/** Predicate: Array <%s> must NOT be of size %s. Available in {@link P}, {@link Has}, {@link Have}.*/
	public static boolean lengthOtherThan(@Nonnull char[] array, int i) {
		Null.nonNullArg(array, "array");
		return !(Array.getLength(array) == i);
	}

	/** "Special" predicate: Array <%s> must NOT be of size %s. Available in {@link P}, {@link Has}, {@link Have}.*/
	public static String lengthOtherThan$(@Nonnull char[] array, int i) {
		Null.nonNullArg(array, "array");
		return !(Array.getLength(array) == i) ? null : String.format("Array <%s> must NOT be of size %s.", array, i);
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

	/** Predicate: Array <%s> must be of size %s. Available in {@link P}, {@link Has}, {@link Have}.*/
	public static boolean length(@Nonnull short[] array, int i) {
		Null.nonNullArg(array, "array");
		return Array.getLength(array) == i;
	}

	/** "Special" predicate: Array <%s> must be of size %s. Available in {@link P}, {@link Has}, {@link Have}.*/
	public static String length$(@Nonnull short[] array, int i) {
		Null.nonNullArg(array, "array");
		return (Array.getLength(array) == i) ? null : String.format("Array <%s> must be of size %s.", array, i);
	}

	/** Predicate: Array <%s> must NOT be of size %s. Available in {@link P}, {@link Has}, {@link Have}.*/
	public static boolean lengthOtherThan(@Nonnull short[] array, int i) {
		Null.nonNullArg(array, "array");
		return !(Array.getLength(array) == i);
	}

	/** "Special" predicate: Array <%s> must NOT be of size %s. Available in {@link P}, {@link Has}, {@link Have}.*/
	public static String lengthOtherThan$(@Nonnull short[] array, int i) {
		Null.nonNullArg(array, "array");
		return !(Array.getLength(array) == i) ? null : String.format("Array <%s> must NOT be of size %s.", array, i);
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

	/** Predicate: Array <%s> must be of size %s. Available in {@link P}, {@link Has}, {@link Have}.*/
	public static boolean length(@Nonnull float[] array, int i) {
		Null.nonNullArg(array, "array");
		return Array.getLength(array) == i;
	}

	/** "Special" predicate: Array <%s> must be of size %s. Available in {@link P}, {@link Has}, {@link Have}.*/
	public static String length$(@Nonnull float[] array, int i) {
		Null.nonNullArg(array, "array");
		return (Array.getLength(array) == i) ? null : String.format("Array <%s> must be of size %s.", array, i);
	}

	/** Predicate: Array <%s> must NOT be of size %s. Available in {@link P}, {@link Has}, {@link Have}.*/
	public static boolean lengthOtherThan(@Nonnull float[] array, int i) {
		Null.nonNullArg(array, "array");
		return !(Array.getLength(array) == i);
	}

	/** "Special" predicate: Array <%s> must NOT be of size %s. Available in {@link P}, {@link Has}, {@link Have}.*/
	public static String lengthOtherThan$(@Nonnull float[] array, int i) {
		Null.nonNullArg(array, "array");
		return !(Array.getLength(array) == i) ? null : String.format("Array <%s> must NOT be of size %s.", array, i);
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

	/** Predicate: Array <%s> must be of size %s. Available in {@link P}, {@link Has}, {@link Have}.*/
	public static boolean length(@Nonnull int[] array, int i) {
		Null.nonNullArg(array, "array");
		return Array.getLength(array) == i;
	}

	/** "Special" predicate: Array <%s> must be of size %s. Available in {@link P}, {@link Has}, {@link Have}.*/
	public static String length$(@Nonnull int[] array, int i) {
		Null.nonNullArg(array, "array");
		return (Array.getLength(array) == i) ? null : String.format("Array <%s> must be of size %s.", array, i);
	}

	/** Predicate: Array <%s> must NOT be of size %s. Available in {@link P}, {@link Has}, {@link Have}.*/
	public static boolean lengthOtherThan(@Nonnull int[] array, int i) {
		Null.nonNullArg(array, "array");
		return !(Array.getLength(array) == i);
	}

	/** "Special" predicate: Array <%s> must NOT be of size %s. Available in {@link P}, {@link Has}, {@link Have}.*/
	public static String lengthOtherThan$(@Nonnull int[] array, int i) {
		Null.nonNullArg(array, "array");
		return !(Array.getLength(array) == i) ? null : String.format("Array <%s> must NOT be of size %s.", array, i);
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

	/** Predicate: Array <%s> must be of size %s. Available in {@link P}, {@link Has}, {@link Have}.*/
	public static boolean length(@Nonnull long[] array, int i) {
		Null.nonNullArg(array, "array");
		return Array.getLength(array) == i;
	}

	/** "Special" predicate: Array <%s> must be of size %s. Available in {@link P}, {@link Has}, {@link Have}.*/
	public static String length$(@Nonnull long[] array, int i) {
		Null.nonNullArg(array, "array");
		return (Array.getLength(array) == i) ? null : String.format("Array <%s> must be of size %s.", array, i);
	}

	/** Predicate: Array <%s> must NOT be of size %s. Available in {@link P}, {@link Has}, {@link Have}.*/
	public static boolean lengthOtherThan(@Nonnull long[] array, int i) {
		Null.nonNullArg(array, "array");
		return !(Array.getLength(array) == i);
	}

	/** "Special" predicate: Array <%s> must NOT be of size %s. Available in {@link P}, {@link Has}, {@link Have}.*/
	public static String lengthOtherThan$(@Nonnull long[] array, int i) {
		Null.nonNullArg(array, "array");
		return !(Array.getLength(array) == i) ? null : String.format("Array <%s> must NOT be of size %s.", array, i);
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

	/** Predicate: Array <%s> must be of size %s. Available in {@link P}, {@link Has}, {@link Have}.*/
	public static <T> boolean length(@Nonnull T[] array, int i) {
		Null.nonNullArg(array, "array");
		return Array.getLength(array) == i;
	}

	/** "Special" predicate: Array <%s> must be of size %s. Available in {@link P}, {@link Has}, {@link Have}.*/
	public static <T> String length$(@Nonnull T[] array, int i) {
		Null.nonNullArg(array, "array");
		return (Array.getLength(array) == i) ? null : String.format("Array <%s> must be of size %s.", array, i);
	}

	/** Predicate: Array <%s> must NOT be of size %s. Available in {@link P}, {@link Has}, {@link Have}.*/
	public static <T> boolean lengthOtherThan(@Nonnull T[] array, int i) {
		Null.nonNullArg(array, "array");
		return !(Array.getLength(array) == i);
	}

	/** "Special" predicate: Array <%s> must NOT be of size %s. Available in {@link P}, {@link Has}, {@link Have}.*/
	public static <T> String lengthOtherThan$(@Nonnull T[] array, int i) {
		Null.nonNullArg(array, "array");
		return !(Array.getLength(array) == i) ? null : String.format("Array <%s> must NOT be of size %s.", array, i);
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

	/** Predicate: Collection <%s> must contain element <%s>. Available in {@link P}, {@link Does}.*/
	public static <T> boolean contain(@Nonnull Collection<T> collection, T element) {
		Null.nonNullArg(collection, "collection");
		return collection.contains(element);
	}

	/** "Special" predicate: Collection <%s> must contain element <%s>. Available in {@link P}, {@link Does}.*/
	public static <T> String contain$(@Nonnull Collection<T> collection, T element) {
		Null.nonNullArg(collection, "collection");
		return (collection.contains(element)) ? null : String.format("Collection <%s> must contain element <%s>.", collection, element);
	}

	/** Predicate: Collection <%s> must NOT contain element <%s>. Available in {@link P}, {@link Does}.*/
	public static <T> boolean notContain(@Nonnull Collection<T> collection, T element) {
		Null.nonNullArg(collection, "collection");
		return !(collection.contains(element));
	}

	/** "Special" predicate: Collection <%s> must NOT contain element <%s>. Available in {@link P}, {@link Does}.*/
	public static <T> String notContain$(@Nonnull Collection<T> collection, T element) {
		Null.nonNullArg(collection, "collection");
		return !(collection.contains(element)) ? null : String.format("Collection <%s> must NOT contain element <%s>.", collection, element);
	}

	/** Predicate: Map <%s> must contain key <%s>. Available in {@link P}, {@link Does}.*/
	public static <K> boolean containKey(@Nonnull Map<K, ?> map, K key) {
		Null.nonNullArg(map, "map");
		return map.containsKey(key);
	}

	/** "Special" predicate: Map <%s> must contain key <%s>. Available in {@link P}, {@link Does}.*/
	public static <K> String containKey$(@Nonnull Map<K, ?> map, K key) {
		Null.nonNullArg(map, "map");
		return (map.containsKey(key)) ? null : String.format("Map <%s> must contain key <%s>.", map, key);
	}

	/** Predicate: Map <%s> must NOT contain key <%s>. Available in {@link P}, {@link Does}.*/
	public static <K> boolean notContainKey(@Nonnull Map<K, ?> map, K key) {
		Null.nonNullArg(map, "map");
		return !(map.containsKey(key));
	}

	/** "Special" predicate: Map <%s> must NOT contain key <%s>. Available in {@link P}, {@link Does}.*/
	public static <K> String notContainKey$(@Nonnull Map<K, ?> map, K key) {
		Null.nonNullArg(map, "map");
		return !(map.containsKey(key)) ? null : String.format("Map <%s> must NOT contain key <%s>.", map, key);
	}

	/** Predicate: Collection <%s> must be of size %s. Available in {@link P}, {@link Has}, {@link Have}.*/
	public static <T> boolean size(@Nonnull Collection<T> collection, int i) {
		Null.nonNullArg(collection, "collection");
		return collection.size() == i;
	}

	/** "Special" predicate: Collection <%s> must be of size %s. Available in {@link P}, {@link Has}, {@link Have}.*/
	public static <T> String size$(@Nonnull Collection<T> collection, int i) {
		Null.nonNullArg(collection, "collection");
		return (collection.size() == i) ? null : String.format("Collection <%s> must be of size %s.", collection, i);
	}

	/** Predicate: Collection <%s> must NOT be of size %s. Available in {@link P}, {@link Has}, {@link Have}.*/
	public static <T> boolean sizeOtherThan(@Nonnull Collection<T> collection, int i) {
		Null.nonNullArg(collection, "collection");
		return !(collection.size() == i);
	}

	/** "Special" predicate: Collection <%s> must NOT be of size %s. Available in {@link P}, {@link Has}, {@link Have}.*/
	public static <T> String sizeOtherThan$(@Nonnull Collection<T> collection, int i) {
		Null.nonNullArg(collection, "collection");
		return !(collection.size() == i) ? null : String.format("Collection <%s> must NOT be of size %s.", collection, i);
	}

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

	/** Predicate: Map <%s> must be of size %s. Available in {@link P}, {@link Has}, {@link Have}.*/
	public static <K, V> boolean size(@Nonnull Map<K, V> map, int i) {
		Null.nonNullArg(map, "map");
		return map.size() == i;
	}

	/** "Special" predicate: Map <%s> must be of size %s. Available in {@link P}, {@link Has}, {@link Have}.*/
	public static <K, V> String size$(@Nonnull Map<K, V> map, int i) {
		Null.nonNullArg(map, "map");
		return (map.size() == i) ? null : String.format("Map <%s> must be of size %s.", map, i);
	}

	/** Predicate: Map <%s> must NOT be of size %s. Available in {@link P}, {@link Has}, {@link Have}.*/
	public static <K, V> boolean sizeOtherThan(@Nonnull Map<K, V> map, int i) {
		Null.nonNullArg(map, "map");
		return !(map.size() == i);
	}

	/** "Special" predicate: Map <%s> must NOT be of size %s. Available in {@link P}, {@link Has}, {@link Have}.*/
	public static <K, V> String sizeOtherThan$(@Nonnull Map<K, V> map, int i) {
		Null.nonNullArg(map, "map");
		return !(map.size() == i) ? null : String.format("Map <%s> must NOT be of size %s.", map, i);
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

	/** Predicate: Object <%s> must be instance of <%s>. Available in {@link P}, {@link Is}, {@link Be}, {@link P1}, {@link P2}.*/
	public static boolean instanceOf(Object object, Class<?> clazz) {
		Null.nonNullArg(clazz, "clazz");
		return clazz.isInstance(object);
	}

	/** "Special" predicate: Object <%s> must be instance of <%s>. Available in {@link P}, {@link Is}, {@link Be}, {@link P1}, {@link P2}.*/
	public static String instanceOf$(Object object, Class<?> clazz) {
		Null.nonNullArg(clazz, "clazz");
		return (clazz.isInstance(object)) ? null : String.format("Object <%s> must be instance of <%s>.", object, clazz);
	}

	/** Predicate: Object <%s> must NOT be instance of <%s>. Available in {@link P}, {@link Is}, {@link Be}, {@link P1}, {@link P2}.*/
	public static boolean notInstanceOf(Object object, Class<?> clazz) {
		Null.nonNullArg(clazz, "clazz");
		return !(clazz.isInstance(object));
	}

	/** "Special" predicate: Object <%s> must NOT be instance of <%s>. Available in {@link P}, {@link Is}, {@link Be}, {@link P1}, {@link P2}.*/
	public static String notInstanceOf$(Object object, Class<?> clazz) {
		Null.nonNullArg(clazz, "clazz");
		return !(clazz.isInstance(object)) ? null : String.format("Object <%s> must NOT be instance of <%s>.", object, clazz);
	}

	/** Predicate: Class <%s> must ---NOT-- be specialization of <%s>. Available in {@link P}, {@link Is}, {@link Be}, {@link P1}, {@link P2}.*/
	public static boolean assignableFrom(Class<?> specialization, Class<?> clazz) {
		Null.nonNullArg(clazz, "clazz");
		return clazz.isAssignableFrom(specialization);
	}

	/** "Special" predicate: Class <%s> must ---NOT-- be specialization of <%s>. Available in {@link P}, {@link Is}, {@link Be}, {@link P1}, {@link P2}.*/
	public static String assignableFrom$(Class<?> specialization, Class<?> clazz) {
		Null.nonNullArg(clazz, "clazz");
		return (clazz.isAssignableFrom(specialization)) ? null : String.format("Class <%s> must ---NOT-- be specialization of <%s>.", specialization, clazz);
	}

	/** Predicate: Class <%s> must ---NOT-- be specialization of <%s>. Available in {@link P}, {@link Is}, {@link Be}, {@link P1}, {@link P2}.*/
	public static boolean notAssignableFrom(Class<?> specialization, Class<?> clazz) {
		Null.nonNullArg(clazz, "clazz");
		return !(clazz.isAssignableFrom(specialization));
	}

	/** "Special" predicate: Class <%s> must ---NOT-- be specialization of <%s>. Available in {@link P}, {@link Is}, {@link Be}, {@link P1}, {@link P2}.*/
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

	/** Predicate: Exception <%s> must have cause. Available in {@link P}, {@link Has}, {@link Have}.*/
	public static boolean cause(@Nonnull Throwable e) {
		Null.nonNullArg(e, "e");
		return e.getCause() != null;
	}

	/** "Special" predicate: Exception <%s> must have cause. Available in {@link P}, {@link Has}, {@link Have}.*/
	public static String cause$(@Nonnull Throwable e) {
		Null.nonNullArg(e, "e");
		return (e.getCause() != null) ? null : String.format("Exception <%s> must have cause.", e);
	}

	/** Predicate: Exception <%s> must NOT have cause. Available in {@link P}, {@link Has}, {@link Have}.*/
	public static boolean noCause(@Nonnull Throwable e) {
		Null.nonNullArg(e, "e");
		return !(e.getCause() != null);
	}

	/** "Special" predicate: Exception <%s> must NOT have cause. Available in {@link P}, {@link Has}, {@link Have}.*/
	public static String noCause$(@Nonnull Throwable e) {
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
	public static String msgStartWith$(@Nonnull Throwable e, @Nonnull String text) {
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
	public static String msgNotStartWith$(@Nonnull Throwable e, @Nonnull String text) {
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
	public static String msgContain$(@Nonnull Throwable e, @Nonnull String text) {
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
	public static String msgNotContain$(@Nonnull Throwable e, @Nonnull String text) {
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
	public static String msgEndWith$(@Nonnull Throwable e, @Nonnull String text) {
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
	public static String msgNotEndWith$(@Nonnull Throwable e, @Nonnull String text) {
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

	// <editor-fold desc="have/has">

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K> LPredicate<K> haveBool(@Nonnull LPredicate<K> extractor, @Nonnull LLogicalOperator operator) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(operator, "operator");
		return k -> operator.apply(extractor.test(k));
	}

	/** "Special" variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K> LFunction<K, String> haveBool$(@Nonnull LPredicate<K> extractor, @Nonnull LBoolFunction<String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(extractor.test(k));
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K> LPredicate<K> hasBool(@Nonnull LPredicate<K> extractor, @Nonnull LLogicalOperator operator) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(operator, "operator");
		return k -> operator.apply(extractor.test(k));
	}

	/** "Special" variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K> LFunction<K, String> hasBool$(@Nonnull LPredicate<K> extractor, @Nonnull LBoolFunction<String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(extractor.test(k));
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K> LPredicate<K> haveBool(@Nonnull LPredicate<K> extractor, boolean a2, @Nonnull LLogicalBinaryOperator operator) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(operator, "operator");
		return k -> operator.apply(extractor.test(k), a2);
	}

	/** "Special" variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K> LFunction<K, String> haveBool$(@Nonnull LPredicate<K> extractor, boolean a2, @Nonnull LBiBoolFunction<String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(extractor.test(k), a2);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K> LPredicate<K> haveBool(@Nonnull LPredicate<K> extractor, @Nonnull LLogicalBinaryOperator operator, boolean a2) {
		return haveBool(extractor, a2, operator);
	}

	/** "Special" variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K> LFunction<K, String> haveBool$(@Nonnull LPredicate<K> extractor, @Nonnull LBiBoolFunction<String> specialPredicate, boolean a2) {
		return haveBool$(extractor, a2, specialPredicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K> LPredicate<K> hasBool(@Nonnull LPredicate<K> extractor, boolean a2, @Nonnull LLogicalBinaryOperator operator) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(operator, "operator");
		return k -> operator.apply(extractor.test(k), a2);
	}

	/** "Special" variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K> LFunction<K, String> hasBool$(@Nonnull LPredicate<K> extractor, boolean a2, @Nonnull LBiBoolFunction<String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(extractor.test(k), a2);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K> LPredicate<K> hasBool(@Nonnull LPredicate<K> extractor, @Nonnull LLogicalBinaryOperator operator, boolean a2) {
		return hasBool(extractor, a2, operator);
	}

	/** "Special" variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K> LFunction<K, String> hasBool$(@Nonnull LPredicate<K> extractor, @Nonnull LBiBoolFunction<String> specialPredicate, boolean a2) {
		return hasBool$(extractor, a2, specialPredicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K> LPredicate<K> haveBool(@Nonnull LPredicate<K> extractor, boolean a2, boolean a3, @Nonnull LLogicalTernaryOperator operator) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(operator, "operator");
		return k -> operator.apply(extractor.test(k), a2, a3);
	}

	/** "Special" variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K> LFunction<K, String> haveBool$(@Nonnull LPredicate<K> extractor, boolean a2, boolean a3, @Nonnull LTriBoolFunction<String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(extractor.test(k), a2, a3);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K> LPredicate<K> haveBool(@Nonnull LPredicate<K> extractor, @Nonnull LLogicalTernaryOperator operator, boolean a2, boolean a3) {
		return haveBool(extractor, a2, a3, operator);
	}

	/** "Special" variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K> LFunction<K, String> haveBool$(@Nonnull LPredicate<K> extractor, @Nonnull LTriBoolFunction<String> specialPredicate, boolean a2, boolean a3) {
		return haveBool$(extractor, a2, a3, specialPredicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K> LPredicate<K> hasBool(@Nonnull LPredicate<K> extractor, boolean a2, boolean a3, @Nonnull LLogicalTernaryOperator operator) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(operator, "operator");
		return k -> operator.apply(extractor.test(k), a2, a3);
	}

	/** "Special" variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K> LFunction<K, String> hasBool$(@Nonnull LPredicate<K> extractor, boolean a2, boolean a3, @Nonnull LTriBoolFunction<String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(extractor.test(k), a2, a3);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K> LPredicate<K> hasBool(@Nonnull LPredicate<K> extractor, @Nonnull LLogicalTernaryOperator operator, boolean a2, boolean a3) {
		return hasBool(extractor, a2, a3, operator);
	}

	/** "Special" variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K> LFunction<K, String> hasBool$(@Nonnull LPredicate<K> extractor, @Nonnull LTriBoolFunction<String> specialPredicate, boolean a2, boolean a3) {
		return hasBool$(extractor, a2, a3, specialPredicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K> LPredicate<K> haveBool$Int(@Nonnull LPredicate<K> extractor, int v, @Nonnull LBoolIntPredicate operator) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(operator, "operator");
		return k -> operator.test(extractor.test(k), v);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K> LPredicate<K> haveBool$Int(@Nonnull LPredicate<K> extractor, @Nonnull LBoolIntPredicate operator, int v) {
		return haveBool$Int(extractor, v, operator);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K> LPredicate<K> hasBool$Int(@Nonnull LPredicate<K> extractor, int v, @Nonnull LBoolIntPredicate operator) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(operator, "operator");
		return k -> operator.test(extractor.test(k), v);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K> LPredicate<K> hasBool$Int(@Nonnull LPredicate<K> extractor, @Nonnull LBoolIntPredicate operator, int v) {
		return hasBool$Int(extractor, v, operator);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, V> LPredicate<K> haveBool(@Nonnull LPredicate<K> extractor, V v, @Nonnull LObjBoolPredicate.LBoolObjPred<? super V> operator) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(operator, "operator");
		return k -> operator.testBoolObj(extractor.test(k), v);
	}

	/** "Special" variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, V> LFunction<K, String> haveBool$(@Nonnull LPredicate<K> extractor, V v, @Nonnull LObjBoolFunction.LBoolObjFunc<? super V, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.applyBoolObj(extractor.test(k), v);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, V> LPredicate<K> haveBool(@Nonnull LPredicate<K> extractor, @Nonnull LObjBoolPredicate.LBoolObjPred<? super V> operator, V v) {
		return haveBool(extractor, v, operator);
	}

	/** "Special" variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, V> LFunction<K, String> haveBool$(@Nonnull LPredicate<K> extractor, @Nonnull LObjBoolFunction.LBoolObjFunc<? super V, String> specialPredicate, V v) {
		return haveBool$(extractor, v, specialPredicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, V> LPredicate<K> hasBool(@Nonnull LPredicate<K> extractor, V v, @Nonnull LObjBoolPredicate.LBoolObjPred<? super V> operator) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(operator, "operator");
		return k -> operator.testBoolObj(extractor.test(k), v);
	}

	/** "Special" variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, V> LFunction<K, String> hasBool$(@Nonnull LPredicate<K> extractor, V v, @Nonnull LObjBoolFunction.LBoolObjFunc<? super V, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.applyBoolObj(extractor.test(k), v);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, V> LPredicate<K> hasBool(@Nonnull LPredicate<K> extractor, @Nonnull LObjBoolPredicate.LBoolObjPred<? super V> operator, V v) {
		return hasBool(extractor, v, operator);
	}

	/** "Special" variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, V> LFunction<K, String> hasBool$(@Nonnull LPredicate<K> extractor, @Nonnull LObjBoolFunction.LBoolObjFunc<? super V, String> specialPredicate, V v) {
		return hasBool$(extractor, v, specialPredicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, V1> LPredicate<K> haveBool$WithBool(@Nonnull LPredicate<K> extractor, V1 with1, @Nonnull LObjBoolPredicate<? super V1> operator) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(operator, "operator");
		return k -> operator.test(with1, extractor.test(k));
	}

	/** "Special" variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, V1> LFunction<K, String> haveBool$WithBool$(@Nonnull LPredicate<K> extractor, V1 with1, @Nonnull LObjBoolFunction<? super V1, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(with1, extractor.test(k));
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, V1> LPredicate<K> haveBool$WithBool(@Nonnull LPredicate<K> extractor, @Nonnull LObjBoolPredicate<? super V1> operator, V1 with1) {
		return haveBool$WithBool(extractor, with1, operator);
	}

	/** "Special" variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, V1> LFunction<K, String> haveBool$WithBool$(@Nonnull LPredicate<K> extractor, @Nonnull LObjBoolFunction<? super V1, String> specialPredicate, V1 with1) {
		return haveBool$WithBool$(extractor, with1, specialPredicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, V1> LPredicate<K> hasBool$WithBool(@Nonnull LPredicate<K> extractor, V1 with1, @Nonnull LObjBoolPredicate<? super V1> operator) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(operator, "operator");
		return k -> operator.test(with1, extractor.test(k));
	}

	/** "Special" variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, V1> LFunction<K, String> hasBool$WithBool$(@Nonnull LPredicate<K> extractor, V1 with1, @Nonnull LObjBoolFunction<? super V1, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(with1, extractor.test(k));
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, V1> LPredicate<K> hasBool$WithBool(@Nonnull LPredicate<K> extractor, @Nonnull LObjBoolPredicate<? super V1> operator, V1 with1) {
		return hasBool$WithBool(extractor, with1, operator);
	}

	/** "Special" variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, V1> LFunction<K, String> hasBool$WithBool$(@Nonnull LPredicate<K> extractor, @Nonnull LObjBoolFunction<? super V1, String> specialPredicate, V1 with1) {
		return hasBool$WithBool$(extractor, with1, specialPredicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, V1, V2> LPredicate<K> haveBool$With(@Nonnull LPredicate<K> extractor, V1 with1, V2 with2, @Nonnull LBiObjBoolPredicate<? super V1, ? super V2> operator) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(operator, "operator");
		return k -> operator.test(with1, with2, extractor.test(k));
	}

	/** "Special" variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, V1, V2> LFunction<K, String> haveBool$With$(@Nonnull LPredicate<K> extractor, V1 with1, V2 with2, @Nonnull LBiObjBoolFunction<? super V1, ? super V2, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(with1, with2, extractor.test(k));
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, V1, V2> LPredicate<K> haveBool$With(@Nonnull LPredicate<K> extractor, @Nonnull LBiObjBoolPredicate<? super V1, ? super V2> operator, V1 with1, V2 with2) {
		return haveBool$With(extractor, with1, with2, operator);
	}

	/** "Special" variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, V1, V2> LFunction<K, String> haveBool$With$(@Nonnull LPredicate<K> extractor, @Nonnull LBiObjBoolFunction<? super V1, ? super V2, String> specialPredicate, V1 with1, V2 with2) {
		return haveBool$With$(extractor, with1, with2, specialPredicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, V1, V2> LPredicate<K> hasBool$With(@Nonnull LPredicate<K> extractor, V1 with1, V2 with2, @Nonnull LBiObjBoolPredicate<? super V1, ? super V2> operator) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(operator, "operator");
		return k -> operator.test(with1, with2, extractor.test(k));
	}

	/** "Special" variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, V1, V2> LFunction<K, String> hasBool$With$(@Nonnull LPredicate<K> extractor, V1 with1, V2 with2, @Nonnull LBiObjBoolFunction<? super V1, ? super V2, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(with1, with2, extractor.test(k));
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, V1, V2> LPredicate<K> hasBool$With(@Nonnull LPredicate<K> extractor, @Nonnull LBiObjBoolPredicate<? super V1, ? super V2> operator, V1 with1, V2 with2) {
		return hasBool$With(extractor, with1, with2, operator);
	}

	/** "Special" variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, V1, V2> LFunction<K, String> hasBool$With$(@Nonnull LPredicate<K> extractor, @Nonnull LBiObjBoolFunction<? super V1, ? super V2, String> specialPredicate, V1 with1, V2 with2) {
		return hasBool$With$(extractor, with1, with2, specialPredicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, T> LPredicate<K> have(@Nonnull LFunction<K, T> extractor, @Nonnull LPredicate<? super T> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.apply(k));
	}

	/** "Special" variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, T> LFunction<K, String> have$(@Nonnull LFunction<K, T> extractor, @Nonnull LFunction<? super T, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(extractor.apply(k));
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, T> LPredicate<K> has(@Nonnull LFunction<K, T> extractor, @Nonnull LPredicate<? super T> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.apply(k));
	}

	/** "Special" variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, T> LFunction<K, String> has$(@Nonnull LFunction<K, T> extractor, @Nonnull LFunction<? super T, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(extractor.apply(k));
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, T> LPredicate<K> uniHave(@Nonnull LFunction<K, T> extractor, T a2, @Nonnull LBiPredicate<? super T, ? super T> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.apply(k), a2);
	}

	/** "Special" variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, T> LFunction<K, String> uniHave$(@Nonnull LFunction<K, T> extractor, T a2, @Nonnull LBiFunction<? super T, ? super T, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(extractor.apply(k), a2);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, T> LPredicate<K> uniHave(@Nonnull LFunction<K, T> extractor, @Nonnull LBiPredicate<? super T, ? super T> predicate, T a2) {
		return uniHave(extractor, a2, predicate);
	}

	/** "Special" variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, T> LFunction<K, String> uniHave$(@Nonnull LFunction<K, T> extractor, @Nonnull LBiFunction<? super T, ? super T, String> specialPredicate, T a2) {
		return uniHave$(extractor, a2, specialPredicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, T> LPredicate<K> uniHas(@Nonnull LFunction<K, T> extractor, T a2, @Nonnull LBiPredicate<? super T, ? super T> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.apply(k), a2);
	}

	/** "Special" variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, T> LFunction<K, String> uniHas$(@Nonnull LFunction<K, T> extractor, T a2, @Nonnull LBiFunction<? super T, ? super T, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(extractor.apply(k), a2);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, T> LPredicate<K> uniHas(@Nonnull LFunction<K, T> extractor, @Nonnull LBiPredicate<? super T, ? super T> predicate, T a2) {
		return uniHas(extractor, a2, predicate);
	}

	/** "Special" variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, T> LFunction<K, String> uniHas$(@Nonnull LFunction<K, T> extractor, @Nonnull LBiFunction<? super T, ? super T, String> specialPredicate, T a2) {
		return uniHas$(extractor, a2, specialPredicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, T> LPredicate<K> uniHave(@Nonnull LFunction<K, T> extractor, T a2, T a3, @Nonnull LTriPredicate<? super T, ? super T, ? super T> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.apply(k), a2, a3);
	}

	/** "Special" variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, T> LFunction<K, String> uniHave$(@Nonnull LFunction<K, T> extractor, T a2, T a3, @Nonnull LTriFunction<? super T, ? super T, ? super T, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(extractor.apply(k), a2, a3);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, T> LPredicate<K> uniHave(@Nonnull LFunction<K, T> extractor, @Nonnull LTriPredicate<? super T, ? super T, ? super T> predicate, T a2, T a3) {
		return uniHave(extractor, a2, a3, predicate);
	}

	/** "Special" variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, T> LFunction<K, String> uniHave$(@Nonnull LFunction<K, T> extractor, @Nonnull LTriFunction<? super T, ? super T, ? super T, String> specialPredicate, T a2, T a3) {
		return uniHave$(extractor, a2, a3, specialPredicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, T> LPredicate<K> uniHas(@Nonnull LFunction<K, T> extractor, T a2, T a3, @Nonnull LTriPredicate<? super T, ? super T, ? super T> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.apply(k), a2, a3);
	}

	/** "Special" variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, T> LFunction<K, String> uniHas$(@Nonnull LFunction<K, T> extractor, T a2, T a3, @Nonnull LTriFunction<? super T, ? super T, ? super T, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(extractor.apply(k), a2, a3);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, T> LPredicate<K> uniHas(@Nonnull LFunction<K, T> extractor, @Nonnull LTriPredicate<? super T, ? super T, ? super T> predicate, T a2, T a3) {
		return uniHas(extractor, a2, a3, predicate);
	}

	/** "Special" variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, T> LFunction<K, String> uniHas$(@Nonnull LFunction<K, T> extractor, @Nonnull LTriFunction<? super T, ? super T, ? super T, String> specialPredicate, T a2, T a3) {
		return uniHas$(extractor, a2, a3, specialPredicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, T> LPredicate<K> uniHave(@Nonnull LFunction<K, T> extractor, T a2, T a3, T a4, @Nonnull LQuadPredicate<? super T, ? super T, ? super T, ? super T> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.apply(k), a2, a3, a4);
	}

	/** "Special" variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, T> LFunction<K, String> uniHave$(@Nonnull LFunction<K, T> extractor, T a2, T a3, T a4, @Nonnull LQuadFunction<? super T, ? super T, ? super T, ? super T, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(extractor.apply(k), a2, a3, a4);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, T> LPredicate<K> uniHave(@Nonnull LFunction<K, T> extractor, @Nonnull LQuadPredicate<? super T, ? super T, ? super T, ? super T> predicate, T a2, T a3, T a4) {
		return uniHave(extractor, a2, a3, a4, predicate);
	}

	/** "Special" variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, T> LFunction<K, String> uniHave$(@Nonnull LFunction<K, T> extractor, @Nonnull LQuadFunction<? super T, ? super T, ? super T, ? super T, String> specialPredicate, T a2, T a3, T a4) {
		return uniHave$(extractor, a2, a3, a4, specialPredicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, T> LPredicate<K> uniHas(@Nonnull LFunction<K, T> extractor, T a2, T a3, T a4, @Nonnull LQuadPredicate<? super T, ? super T, ? super T, ? super T> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.apply(k), a2, a3, a4);
	}

	/** "Special" variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, T> LFunction<K, String> uniHas$(@Nonnull LFunction<K, T> extractor, T a2, T a3, T a4, @Nonnull LQuadFunction<? super T, ? super T, ? super T, ? super T, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(extractor.apply(k), a2, a3, a4);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, T> LPredicate<K> uniHas(@Nonnull LFunction<K, T> extractor, @Nonnull LQuadPredicate<? super T, ? super T, ? super T, ? super T> predicate, T a2, T a3, T a4) {
		return uniHas(extractor, a2, a3, a4, predicate);
	}

	/** "Special" variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, T> LFunction<K, String> uniHas$(@Nonnull LFunction<K, T> extractor, @Nonnull LQuadFunction<? super T, ? super T, ? super T, ? super T, String> specialPredicate, T a2, T a3, T a4) {
		return uniHas$(extractor, a2, a3, a4, specialPredicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, T> LPredicate<K> have$Bool(@Nonnull LFunction<K, T> extractor, boolean v, @Nonnull LObjBoolPredicate<? super T> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.apply(k), v);
	}

	/** "Special" variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, T> LFunction<K, String> have$Bool$(@Nonnull LFunction<K, T> extractor, boolean v, @Nonnull LObjBoolFunction<? super T, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(extractor.apply(k), v);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, T> LPredicate<K> have$Bool(@Nonnull LFunction<K, T> extractor, @Nonnull LObjBoolPredicate<? super T> predicate, boolean v) {
		return have$Bool(extractor, v, predicate);
	}

	/** "Special" variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, T> LFunction<K, String> have$Bool$(@Nonnull LFunction<K, T> extractor, @Nonnull LObjBoolFunction<? super T, String> specialPredicate, boolean v) {
		return have$Bool$(extractor, v, specialPredicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, T> LPredicate<K> has$Bool(@Nonnull LFunction<K, T> extractor, boolean v, @Nonnull LObjBoolPredicate<? super T> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.apply(k), v);
	}

	/** "Special" variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, T> LFunction<K, String> has$Bool$(@Nonnull LFunction<K, T> extractor, boolean v, @Nonnull LObjBoolFunction<? super T, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(extractor.apply(k), v);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, T> LPredicate<K> has$Bool(@Nonnull LFunction<K, T> extractor, @Nonnull LObjBoolPredicate<? super T> predicate, boolean v) {
		return has$Bool(extractor, v, predicate);
	}

	/** "Special" variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, T> LFunction<K, String> has$Bool$(@Nonnull LFunction<K, T> extractor, @Nonnull LObjBoolFunction<? super T, String> specialPredicate, boolean v) {
		return has$Bool$(extractor, v, specialPredicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, T> LPredicate<K> have$Byte(@Nonnull LFunction<K, T> extractor, byte v, @Nonnull LObjBytePredicate<? super T> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.apply(k), v);
	}

	/** "Special" variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, T> LFunction<K, String> have$Byte$(@Nonnull LFunction<K, T> extractor, byte v, @Nonnull LObjByteFunction<? super T, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(extractor.apply(k), v);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, T> LPredicate<K> have$Byte(@Nonnull LFunction<K, T> extractor, @Nonnull LObjBytePredicate<? super T> predicate, byte v) {
		return have$Byte(extractor, v, predicate);
	}

	/** "Special" variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, T> LFunction<K, String> have$Byte$(@Nonnull LFunction<K, T> extractor, @Nonnull LObjByteFunction<? super T, String> specialPredicate, byte v) {
		return have$Byte$(extractor, v, specialPredicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, T> LPredicate<K> has$Byte(@Nonnull LFunction<K, T> extractor, byte v, @Nonnull LObjBytePredicate<? super T> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.apply(k), v);
	}

	/** "Special" variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, T> LFunction<K, String> has$Byte$(@Nonnull LFunction<K, T> extractor, byte v, @Nonnull LObjByteFunction<? super T, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(extractor.apply(k), v);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, T> LPredicate<K> has$Byte(@Nonnull LFunction<K, T> extractor, @Nonnull LObjBytePredicate<? super T> predicate, byte v) {
		return has$Byte(extractor, v, predicate);
	}

	/** "Special" variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, T> LFunction<K, String> has$Byte$(@Nonnull LFunction<K, T> extractor, @Nonnull LObjByteFunction<? super T, String> specialPredicate, byte v) {
		return has$Byte$(extractor, v, specialPredicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, T> LPredicate<K> have$Dbl(@Nonnull LFunction<K, T> extractor, double v, @Nonnull LObjDblPredicate<? super T> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.apply(k), v);
	}

	/** "Special" variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, T> LFunction<K, String> have$Dbl$(@Nonnull LFunction<K, T> extractor, double v, @Nonnull LObjDblFunction<? super T, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(extractor.apply(k), v);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, T> LPredicate<K> have$Dbl(@Nonnull LFunction<K, T> extractor, @Nonnull LObjDblPredicate<? super T> predicate, double v) {
		return have$Dbl(extractor, v, predicate);
	}

	/** "Special" variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, T> LFunction<K, String> have$Dbl$(@Nonnull LFunction<K, T> extractor, @Nonnull LObjDblFunction<? super T, String> specialPredicate, double v) {
		return have$Dbl$(extractor, v, specialPredicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, T> LPredicate<K> has$Dbl(@Nonnull LFunction<K, T> extractor, double v, @Nonnull LObjDblPredicate<? super T> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.apply(k), v);
	}

	/** "Special" variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, T> LFunction<K, String> has$Dbl$(@Nonnull LFunction<K, T> extractor, double v, @Nonnull LObjDblFunction<? super T, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(extractor.apply(k), v);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, T> LPredicate<K> has$Dbl(@Nonnull LFunction<K, T> extractor, @Nonnull LObjDblPredicate<? super T> predicate, double v) {
		return has$Dbl(extractor, v, predicate);
	}

	/** "Special" variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, T> LFunction<K, String> has$Dbl$(@Nonnull LFunction<K, T> extractor, @Nonnull LObjDblFunction<? super T, String> specialPredicate, double v) {
		return has$Dbl$(extractor, v, specialPredicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, T> LPredicate<K> have$Char(@Nonnull LFunction<K, T> extractor, char v, @Nonnull LObjCharPredicate<? super T> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.apply(k), v);
	}

	/** "Special" variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, T> LFunction<K, String> have$Char$(@Nonnull LFunction<K, T> extractor, char v, @Nonnull LObjCharFunction<? super T, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(extractor.apply(k), v);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, T> LPredicate<K> have$Char(@Nonnull LFunction<K, T> extractor, @Nonnull LObjCharPredicate<? super T> predicate, char v) {
		return have$Char(extractor, v, predicate);
	}

	/** "Special" variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, T> LFunction<K, String> have$Char$(@Nonnull LFunction<K, T> extractor, @Nonnull LObjCharFunction<? super T, String> specialPredicate, char v) {
		return have$Char$(extractor, v, specialPredicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, T> LPredicate<K> has$Char(@Nonnull LFunction<K, T> extractor, char v, @Nonnull LObjCharPredicate<? super T> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.apply(k), v);
	}

	/** "Special" variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, T> LFunction<K, String> has$Char$(@Nonnull LFunction<K, T> extractor, char v, @Nonnull LObjCharFunction<? super T, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(extractor.apply(k), v);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, T> LPredicate<K> has$Char(@Nonnull LFunction<K, T> extractor, @Nonnull LObjCharPredicate<? super T> predicate, char v) {
		return has$Char(extractor, v, predicate);
	}

	/** "Special" variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, T> LFunction<K, String> has$Char$(@Nonnull LFunction<K, T> extractor, @Nonnull LObjCharFunction<? super T, String> specialPredicate, char v) {
		return has$Char$(extractor, v, specialPredicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, T> LPredicate<K> have$Srt(@Nonnull LFunction<K, T> extractor, short v, @Nonnull LObjSrtPredicate<? super T> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.apply(k), v);
	}

	/** "Special" variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, T> LFunction<K, String> have$Srt$(@Nonnull LFunction<K, T> extractor, short v, @Nonnull LObjSrtFunction<? super T, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(extractor.apply(k), v);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, T> LPredicate<K> have$Srt(@Nonnull LFunction<K, T> extractor, @Nonnull LObjSrtPredicate<? super T> predicate, short v) {
		return have$Srt(extractor, v, predicate);
	}

	/** "Special" variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, T> LFunction<K, String> have$Srt$(@Nonnull LFunction<K, T> extractor, @Nonnull LObjSrtFunction<? super T, String> specialPredicate, short v) {
		return have$Srt$(extractor, v, specialPredicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, T> LPredicate<K> has$Srt(@Nonnull LFunction<K, T> extractor, short v, @Nonnull LObjSrtPredicate<? super T> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.apply(k), v);
	}

	/** "Special" variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, T> LFunction<K, String> has$Srt$(@Nonnull LFunction<K, T> extractor, short v, @Nonnull LObjSrtFunction<? super T, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(extractor.apply(k), v);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, T> LPredicate<K> has$Srt(@Nonnull LFunction<K, T> extractor, @Nonnull LObjSrtPredicate<? super T> predicate, short v) {
		return has$Srt(extractor, v, predicate);
	}

	/** "Special" variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, T> LFunction<K, String> has$Srt$(@Nonnull LFunction<K, T> extractor, @Nonnull LObjSrtFunction<? super T, String> specialPredicate, short v) {
		return has$Srt$(extractor, v, specialPredicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, T> LPredicate<K> have$Flt(@Nonnull LFunction<K, T> extractor, float v, @Nonnull LObjFltPredicate<? super T> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.apply(k), v);
	}

	/** "Special" variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, T> LFunction<K, String> have$Flt$(@Nonnull LFunction<K, T> extractor, float v, @Nonnull LObjFltFunction<? super T, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(extractor.apply(k), v);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, T> LPredicate<K> have$Flt(@Nonnull LFunction<K, T> extractor, @Nonnull LObjFltPredicate<? super T> predicate, float v) {
		return have$Flt(extractor, v, predicate);
	}

	/** "Special" variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, T> LFunction<K, String> have$Flt$(@Nonnull LFunction<K, T> extractor, @Nonnull LObjFltFunction<? super T, String> specialPredicate, float v) {
		return have$Flt$(extractor, v, specialPredicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, T> LPredicate<K> has$Flt(@Nonnull LFunction<K, T> extractor, float v, @Nonnull LObjFltPredicate<? super T> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.apply(k), v);
	}

	/** "Special" variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, T> LFunction<K, String> has$Flt$(@Nonnull LFunction<K, T> extractor, float v, @Nonnull LObjFltFunction<? super T, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(extractor.apply(k), v);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, T> LPredicate<K> has$Flt(@Nonnull LFunction<K, T> extractor, @Nonnull LObjFltPredicate<? super T> predicate, float v) {
		return has$Flt(extractor, v, predicate);
	}

	/** "Special" variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, T> LFunction<K, String> has$Flt$(@Nonnull LFunction<K, T> extractor, @Nonnull LObjFltFunction<? super T, String> specialPredicate, float v) {
		return has$Flt$(extractor, v, specialPredicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, T> LPredicate<K> have$Int(@Nonnull LFunction<K, T> extractor, int v, @Nonnull LObjIntPredicate<? super T> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.apply(k), v);
	}

	/** "Special" variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, T> LFunction<K, String> have$Int$(@Nonnull LFunction<K, T> extractor, int v, @Nonnull LOiFunction<? super T, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(extractor.apply(k), v);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, T> LPredicate<K> have$Int(@Nonnull LFunction<K, T> extractor, @Nonnull LObjIntPredicate<? super T> predicate, int v) {
		return have$Int(extractor, v, predicate);
	}

	/** "Special" variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, T> LFunction<K, String> have$Int$(@Nonnull LFunction<K, T> extractor, @Nonnull LOiFunction<? super T, String> specialPredicate, int v) {
		return have$Int$(extractor, v, specialPredicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, T> LPredicate<K> has$Int(@Nonnull LFunction<K, T> extractor, int v, @Nonnull LObjIntPredicate<? super T> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.apply(k), v);
	}

	/** "Special" variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, T> LFunction<K, String> has$Int$(@Nonnull LFunction<K, T> extractor, int v, @Nonnull LOiFunction<? super T, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(extractor.apply(k), v);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, T> LPredicate<K> has$Int(@Nonnull LFunction<K, T> extractor, @Nonnull LObjIntPredicate<? super T> predicate, int v) {
		return has$Int(extractor, v, predicate);
	}

	/** "Special" variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, T> LFunction<K, String> has$Int$(@Nonnull LFunction<K, T> extractor, @Nonnull LOiFunction<? super T, String> specialPredicate, int v) {
		return has$Int$(extractor, v, specialPredicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, T> LPredicate<K> have$Long(@Nonnull LFunction<K, T> extractor, long v, @Nonnull LObjLongPredicate<? super T> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.apply(k), v);
	}

	/** "Special" variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, T> LFunction<K, String> have$Long$(@Nonnull LFunction<K, T> extractor, long v, @Nonnull LObjLongFunction<? super T, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(extractor.apply(k), v);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, T> LPredicate<K> have$Long(@Nonnull LFunction<K, T> extractor, @Nonnull LObjLongPredicate<? super T> predicate, long v) {
		return have$Long(extractor, v, predicate);
	}

	/** "Special" variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, T> LFunction<K, String> have$Long$(@Nonnull LFunction<K, T> extractor, @Nonnull LObjLongFunction<? super T, String> specialPredicate, long v) {
		return have$Long$(extractor, v, specialPredicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, T> LPredicate<K> has$Long(@Nonnull LFunction<K, T> extractor, long v, @Nonnull LObjLongPredicate<? super T> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.apply(k), v);
	}

	/** "Special" variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, T> LFunction<K, String> has$Long$(@Nonnull LFunction<K, T> extractor, long v, @Nonnull LObjLongFunction<? super T, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(extractor.apply(k), v);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, T> LPredicate<K> has$Long(@Nonnull LFunction<K, T> extractor, @Nonnull LObjLongPredicate<? super T> predicate, long v) {
		return has$Long(extractor, v, predicate);
	}

	/** "Special" variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, T> LFunction<K, String> has$Long$(@Nonnull LFunction<K, T> extractor, @Nonnull LObjLongFunction<? super T, String> specialPredicate, long v) {
		return has$Long$(extractor, v, specialPredicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, T, V> LPredicate<K> have(@Nonnull LFunction<K, T> extractor, V v, @Nonnull LBiPredicate<? super T, ? super V> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.apply(k), v);
	}

	/** "Special" variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, T, V> LFunction<K, String> have$(@Nonnull LFunction<K, T> extractor, V v, @Nonnull LBiFunction<? super T, ? super V, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(extractor.apply(k), v);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, T, V> LPredicate<K> have(@Nonnull LFunction<K, T> extractor, @Nonnull LBiPredicate<? super T, ? super V> predicate, V v) {
		return have(extractor, v, predicate);
	}

	/** "Special" variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, T, V> LFunction<K, String> have$(@Nonnull LFunction<K, T> extractor, @Nonnull LBiFunction<? super T, ? super V, String> specialPredicate, V v) {
		return have$(extractor, v, specialPredicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, T, V> LPredicate<K> has(@Nonnull LFunction<K, T> extractor, V v, @Nonnull LBiPredicate<? super T, ? super V> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.apply(k), v);
	}

	/** "Special" variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, T, V> LFunction<K, String> has$(@Nonnull LFunction<K, T> extractor, V v, @Nonnull LBiFunction<? super T, ? super V, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(extractor.apply(k), v);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, T, V> LPredicate<K> has(@Nonnull LFunction<K, T> extractor, @Nonnull LBiPredicate<? super T, ? super V> predicate, V v) {
		return has(extractor, v, predicate);
	}

	/** "Special" variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, T, V> LFunction<K, String> has$(@Nonnull LFunction<K, T> extractor, @Nonnull LBiFunction<? super T, ? super V, String> specialPredicate, V v) {
		return has$(extractor, v, specialPredicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, T, V2, V3> LPredicate<K> have(@Nonnull LFunction<K, T> extractor, V2 a2, V3 a3, @Nonnull LTriPredicate<? super T, ? super V2, ? super V3> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.apply(k), a2, a3);
	}

	/** "Special" variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, T, V2, V3> LFunction<K, String> have$(@Nonnull LFunction<K, T> extractor, V2 a2, V3 a3, @Nonnull LTriFunction<? super T, ? super V2, ? super V3, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(extractor.apply(k), a2, a3);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, T, V2, V3> LPredicate<K> have(@Nonnull LFunction<K, T> extractor, @Nonnull LTriPredicate<? super T, ? super V2, ? super V3> predicate, V2 a2, V3 a3) {
		return have(extractor, a2, a3, predicate);
	}

	/** "Special" variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, T, V2, V3> LFunction<K, String> have$(@Nonnull LFunction<K, T> extractor, @Nonnull LTriFunction<? super T, ? super V2, ? super V3, String> specialPredicate, V2 a2, V3 a3) {
		return have$(extractor, a2, a3, specialPredicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, T, V2, V3> LPredicate<K> has(@Nonnull LFunction<K, T> extractor, V2 a2, V3 a3, @Nonnull LTriPredicate<? super T, ? super V2, ? super V3> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.apply(k), a2, a3);
	}

	/** "Special" variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, T, V2, V3> LFunction<K, String> has$(@Nonnull LFunction<K, T> extractor, V2 a2, V3 a3, @Nonnull LTriFunction<? super T, ? super V2, ? super V3, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(extractor.apply(k), a2, a3);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, T, V2, V3> LPredicate<K> has(@Nonnull LFunction<K, T> extractor, @Nonnull LTriPredicate<? super T, ? super V2, ? super V3> predicate, V2 a2, V3 a3) {
		return has(extractor, a2, a3, predicate);
	}

	/** "Special" variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, T, V2, V3> LFunction<K, String> has$(@Nonnull LFunction<K, T> extractor, @Nonnull LTriFunction<? super T, ? super V2, ? super V3, String> specialPredicate, V2 a2, V3 a3) {
		return has$(extractor, a2, a3, specialPredicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, T, V2, V3, V4> LPredicate<K> have(@Nonnull LFunction<K, T> extractor, V2 a2, V3 a3, V4 a4, @Nonnull LQuadPredicate<? super T, ? super V2, ? super V3, ? super V4> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.apply(k), a2, a3, a4);
	}

	/** "Special" variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, T, V2, V3, V4> LFunction<K, String> have$(@Nonnull LFunction<K, T> extractor, V2 a2, V3 a3, V4 a4, @Nonnull LQuadFunction<? super T, ? super V2, ? super V3, ? super V4, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(extractor.apply(k), a2, a3, a4);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, T, V2, V3, V4> LPredicate<K> have(@Nonnull LFunction<K, T> extractor, @Nonnull LQuadPredicate<? super T, ? super V2, ? super V3, ? super V4> predicate, V2 a2, V3 a3, V4 a4) {
		return have(extractor, a2, a3, a4, predicate);
	}

	/** "Special" variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, T, V2, V3, V4> LFunction<K, String> have$(@Nonnull LFunction<K, T> extractor, @Nonnull LQuadFunction<? super T, ? super V2, ? super V3, ? super V4, String> specialPredicate, V2 a2, V3 a3, V4 a4) {
		return have$(extractor, a2, a3, a4, specialPredicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, T, V2, V3, V4> LPredicate<K> has(@Nonnull LFunction<K, T> extractor, V2 a2, V3 a3, V4 a4, @Nonnull LQuadPredicate<? super T, ? super V2, ? super V3, ? super V4> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.apply(k), a2, a3, a4);
	}

	/** "Special" variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, T, V2, V3, V4> LFunction<K, String> has$(@Nonnull LFunction<K, T> extractor, V2 a2, V3 a3, V4 a4, @Nonnull LQuadFunction<? super T, ? super V2, ? super V3, ? super V4, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(extractor.apply(k), a2, a3, a4);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, T, V2, V3, V4> LPredicate<K> has(@Nonnull LFunction<K, T> extractor, @Nonnull LQuadPredicate<? super T, ? super V2, ? super V3, ? super V4> predicate, V2 a2, V3 a3, V4 a4) {
		return has(extractor, a2, a3, a4, predicate);
	}

	/** "Special" variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, T, V2, V3, V4> LFunction<K, String> has$(@Nonnull LFunction<K, T> extractor, @Nonnull LQuadFunction<? super T, ? super V2, ? super V3, ? super V4, String> specialPredicate, V2 a2, V3 a3, V4 a4) {
		return has$(extractor, a2, a3, a4, specialPredicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, T, V1> LPredicate<K> have$With(@Nonnull LFunction<K, T> extractor, V1 with1, @Nonnull LBiPredicate<? super V1, ? super T> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(with1, extractor.apply(k));
	}

	/** "Special" variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, T, V1> LFunction<K, String> have$With$(@Nonnull LFunction<K, T> extractor, V1 with1, @Nonnull LBiFunction<? super V1, ? super T, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(with1, extractor.apply(k));
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, T, V1> LPredicate<K> have$With(@Nonnull LFunction<K, T> extractor, @Nonnull LBiPredicate<? super V1, ? super T> predicate, V1 with1) {
		return have$With(extractor, with1, predicate);
	}

	/** "Special" variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, T, V1> LFunction<K, String> have$With$(@Nonnull LFunction<K, T> extractor, @Nonnull LBiFunction<? super V1, ? super T, String> specialPredicate, V1 with1) {
		return have$With$(extractor, with1, specialPredicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, T, V1> LPredicate<K> has$With(@Nonnull LFunction<K, T> extractor, V1 with1, @Nonnull LBiPredicate<? super V1, ? super T> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(with1, extractor.apply(k));
	}

	/** "Special" variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, T, V1> LFunction<K, String> has$With$(@Nonnull LFunction<K, T> extractor, V1 with1, @Nonnull LBiFunction<? super V1, ? super T, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(with1, extractor.apply(k));
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, T, V1> LPredicate<K> has$With(@Nonnull LFunction<K, T> extractor, @Nonnull LBiPredicate<? super V1, ? super T> predicate, V1 with1) {
		return has$With(extractor, with1, predicate);
	}

	/** "Special" variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, T, V1> LFunction<K, String> has$With$(@Nonnull LFunction<K, T> extractor, @Nonnull LBiFunction<? super V1, ? super T, String> specialPredicate, V1 with1) {
		return has$With$(extractor, with1, specialPredicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, T, V1, V2> LPredicate<K> have$With(@Nonnull LFunction<K, T> extractor, V1 with1, V2 with2, @Nonnull LTriPredicate<? super V1, ? super V2, ? super T> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(with1, with2, extractor.apply(k));
	}

	/** "Special" variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, T, V1, V2> LFunction<K, String> have$With$(@Nonnull LFunction<K, T> extractor, V1 with1, V2 with2, @Nonnull LTriFunction<? super V1, ? super V2, ? super T, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(with1, with2, extractor.apply(k));
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, T, V1, V2> LPredicate<K> have$With(@Nonnull LFunction<K, T> extractor, @Nonnull LTriPredicate<? super V1, ? super V2, ? super T> predicate, V1 with1, V2 with2) {
		return have$With(extractor, with1, with2, predicate);
	}

	/** "Special" variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, T, V1, V2> LFunction<K, String> have$With$(@Nonnull LFunction<K, T> extractor, @Nonnull LTriFunction<? super V1, ? super V2, ? super T, String> specialPredicate, V1 with1, V2 with2) {
		return have$With$(extractor, with1, with2, specialPredicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, T, V1, V2> LPredicate<K> has$With(@Nonnull LFunction<K, T> extractor, V1 with1, V2 with2, @Nonnull LTriPredicate<? super V1, ? super V2, ? super T> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(with1, with2, extractor.apply(k));
	}

	/** "Special" variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, T, V1, V2> LFunction<K, String> has$With$(@Nonnull LFunction<K, T> extractor, V1 with1, V2 with2, @Nonnull LTriFunction<? super V1, ? super V2, ? super T, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(with1, with2, extractor.apply(k));
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, T, V1, V2> LPredicate<K> has$With(@Nonnull LFunction<K, T> extractor, @Nonnull LTriPredicate<? super V1, ? super V2, ? super T> predicate, V1 with1, V2 with2) {
		return has$With(extractor, with1, with2, predicate);
	}

	/** "Special" variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, T, V1, V2> LFunction<K, String> has$With$(@Nonnull LFunction<K, T> extractor, @Nonnull LTriFunction<? super V1, ? super V2, ? super T, String> specialPredicate, V1 with1, V2 with2) {
		return has$With$(extractor, with1, with2, specialPredicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, T> LPredicate<K> uniHave$With(@Nonnull LFunction<K, T> extractor, T with, @Nonnull LBiPredicate<? super T, ? super T> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(with, extractor.apply(k));
	}

	/** "Special" variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, T> LFunction<K, String> uniHave$With$(@Nonnull LFunction<K, T> extractor, T with, @Nonnull LBiFunction<? super T, ? super T, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(with, extractor.apply(k));
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, T> LPredicate<K> uniHave$With(@Nonnull LFunction<K, T> extractor, @Nonnull LBiPredicate<? super T, ? super T> predicate, T with) {
		return uniHave$With(extractor, with, predicate);
	}

	/** "Special" variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, T> LFunction<K, String> uniHave$With$(@Nonnull LFunction<K, T> extractor, @Nonnull LBiFunction<? super T, ? super T, String> specialPredicate, T with) {
		return uniHave$With$(extractor, with, specialPredicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, T> LPredicate<K> uniHas$With(@Nonnull LFunction<K, T> extractor, T with, @Nonnull LBiPredicate<? super T, ? super T> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(with, extractor.apply(k));
	}

	/** "Special" variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, T> LFunction<K, String> uniHas$With$(@Nonnull LFunction<K, T> extractor, T with, @Nonnull LBiFunction<? super T, ? super T, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(with, extractor.apply(k));
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, T> LPredicate<K> uniHas$With(@Nonnull LFunction<K, T> extractor, @Nonnull LBiPredicate<? super T, ? super T> predicate, T with) {
		return uniHas$With(extractor, with, predicate);
	}

	/** "Special" variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, T> LFunction<K, String> uniHas$With$(@Nonnull LFunction<K, T> extractor, @Nonnull LBiFunction<? super T, ? super T, String> specialPredicate, T with) {
		return uniHas$With$(extractor, with, specialPredicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K> LPredicate<K> haveByte(@Nonnull LToByteFunction<K> extractor, @Nonnull LBytePredicate predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.applyAsByte(k));
	}

	/** "Special" variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K> LFunction<K, String> haveByte$(@Nonnull LToByteFunction<K> extractor, @Nonnull LByteFunction<String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(extractor.applyAsByte(k));
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K> LPredicate<K> hasByte(@Nonnull LToByteFunction<K> extractor, @Nonnull LBytePredicate predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.applyAsByte(k));
	}

	/** "Special" variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K> LFunction<K, String> hasByte$(@Nonnull LToByteFunction<K> extractor, @Nonnull LByteFunction<String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(extractor.applyAsByte(k));
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K> LPredicate<K> haveByte(@Nonnull LToByteFunction<K> extractor, byte a2, @Nonnull LBiBytePredicate predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.applyAsByte(k), a2);
	}

	/** "Special" variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K> LFunction<K, String> haveByte$(@Nonnull LToByteFunction<K> extractor, byte a2, @Nonnull LBiByteFunction<String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(extractor.applyAsByte(k), a2);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K> LPredicate<K> haveByte(@Nonnull LToByteFunction<K> extractor, @Nonnull LBiBytePredicate predicate, byte a2) {
		return haveByte(extractor, a2, predicate);
	}

	/** "Special" variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K> LFunction<K, String> haveByte$(@Nonnull LToByteFunction<K> extractor, @Nonnull LBiByteFunction<String> specialPredicate, byte a2) {
		return haveByte$(extractor, a2, specialPredicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K> LPredicate<K> hasByte(@Nonnull LToByteFunction<K> extractor, byte a2, @Nonnull LBiBytePredicate predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.applyAsByte(k), a2);
	}

	/** "Special" variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K> LFunction<K, String> hasByte$(@Nonnull LToByteFunction<K> extractor, byte a2, @Nonnull LBiByteFunction<String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(extractor.applyAsByte(k), a2);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K> LPredicate<K> hasByte(@Nonnull LToByteFunction<K> extractor, @Nonnull LBiBytePredicate predicate, byte a2) {
		return hasByte(extractor, a2, predicate);
	}

	/** "Special" variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K> LFunction<K, String> hasByte$(@Nonnull LToByteFunction<K> extractor, @Nonnull LBiByteFunction<String> specialPredicate, byte a2) {
		return hasByte$(extractor, a2, specialPredicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K> LPredicate<K> haveByte(@Nonnull LToByteFunction<K> extractor, byte a2, byte a3, @Nonnull LTriBytePredicate predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.applyAsByte(k), a2, a3);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K> LPredicate<K> haveByte(@Nonnull LToByteFunction<K> extractor, @Nonnull LTriBytePredicate predicate, byte a2, byte a3) {
		return haveByte(extractor, a2, a3, predicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K> LPredicate<K> hasByte(@Nonnull LToByteFunction<K> extractor, byte a2, byte a3, @Nonnull LTriBytePredicate predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.applyAsByte(k), a2, a3);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K> LPredicate<K> hasByte(@Nonnull LToByteFunction<K> extractor, @Nonnull LTriBytePredicate predicate, byte a2, byte a3) {
		return hasByte(extractor, a2, a3, predicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K> LPredicate<K> haveByte$Int(@Nonnull LToByteFunction<K> extractor, int v, @Nonnull LByteIntPredicate predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.applyAsByte(k), v);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K> LPredicate<K> haveByte$Int(@Nonnull LToByteFunction<K> extractor, @Nonnull LByteIntPredicate predicate, int v) {
		return haveByte$Int(extractor, v, predicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K> LPredicate<K> hasByte$Int(@Nonnull LToByteFunction<K> extractor, int v, @Nonnull LByteIntPredicate predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.applyAsByte(k), v);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K> LPredicate<K> hasByte$Int(@Nonnull LToByteFunction<K> extractor, @Nonnull LByteIntPredicate predicate, int v) {
		return hasByte$Int(extractor, v, predicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, V> LPredicate<K> haveByte(@Nonnull LToByteFunction<K> extractor, V v, @Nonnull LObjBytePredicate.LByteObjPred<? super V> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.testByteObj(extractor.applyAsByte(k), v);
	}

	/** "Special" variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, V> LFunction<K, String> haveByte$(@Nonnull LToByteFunction<K> extractor, V v, @Nonnull LObjByteFunction.LByteObjFunc<? super V, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.applyByteObj(extractor.applyAsByte(k), v);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, V> LPredicate<K> haveByte(@Nonnull LToByteFunction<K> extractor, @Nonnull LObjBytePredicate.LByteObjPred<? super V> predicate, V v) {
		return haveByte(extractor, v, predicate);
	}

	/** "Special" variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, V> LFunction<K, String> haveByte$(@Nonnull LToByteFunction<K> extractor, @Nonnull LObjByteFunction.LByteObjFunc<? super V, String> specialPredicate, V v) {
		return haveByte$(extractor, v, specialPredicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, V> LPredicate<K> hasByte(@Nonnull LToByteFunction<K> extractor, V v, @Nonnull LObjBytePredicate.LByteObjPred<? super V> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.testByteObj(extractor.applyAsByte(k), v);
	}

	/** "Special" variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, V> LFunction<K, String> hasByte$(@Nonnull LToByteFunction<K> extractor, V v, @Nonnull LObjByteFunction.LByteObjFunc<? super V, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.applyByteObj(extractor.applyAsByte(k), v);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, V> LPredicate<K> hasByte(@Nonnull LToByteFunction<K> extractor, @Nonnull LObjBytePredicate.LByteObjPred<? super V> predicate, V v) {
		return hasByte(extractor, v, predicate);
	}

	/** "Special" variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, V> LFunction<K, String> hasByte$(@Nonnull LToByteFunction<K> extractor, @Nonnull LObjByteFunction.LByteObjFunc<? super V, String> specialPredicate, V v) {
		return hasByte$(extractor, v, specialPredicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, V1> LPredicate<K> haveByte$WithByte(@Nonnull LToByteFunction<K> extractor, V1 with1, @Nonnull LObjBytePredicate<? super V1> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(with1, extractor.applyAsByte(k));
	}

	/** "Special" variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, V1> LFunction<K, String> haveByte$WithByte$(@Nonnull LToByteFunction<K> extractor, V1 with1, @Nonnull LObjByteFunction<? super V1, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(with1, extractor.applyAsByte(k));
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, V1> LPredicate<K> haveByte$WithByte(@Nonnull LToByteFunction<K> extractor, @Nonnull LObjBytePredicate<? super V1> predicate, V1 with1) {
		return haveByte$WithByte(extractor, with1, predicate);
	}

	/** "Special" variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, V1> LFunction<K, String> haveByte$WithByte$(@Nonnull LToByteFunction<K> extractor, @Nonnull LObjByteFunction<? super V1, String> specialPredicate, V1 with1) {
		return haveByte$WithByte$(extractor, with1, specialPredicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, V1> LPredicate<K> hasByte$WithByte(@Nonnull LToByteFunction<K> extractor, V1 with1, @Nonnull LObjBytePredicate<? super V1> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(with1, extractor.applyAsByte(k));
	}

	/** "Special" variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, V1> LFunction<K, String> hasByte$WithByte$(@Nonnull LToByteFunction<K> extractor, V1 with1, @Nonnull LObjByteFunction<? super V1, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(with1, extractor.applyAsByte(k));
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, V1> LPredicate<K> hasByte$WithByte(@Nonnull LToByteFunction<K> extractor, @Nonnull LObjBytePredicate<? super V1> predicate, V1 with1) {
		return hasByte$WithByte(extractor, with1, predicate);
	}

	/** "Special" variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, V1> LFunction<K, String> hasByte$WithByte$(@Nonnull LToByteFunction<K> extractor, @Nonnull LObjByteFunction<? super V1, String> specialPredicate, V1 with1) {
		return hasByte$WithByte$(extractor, with1, specialPredicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, V1, V2> LPredicate<K> haveByte$With(@Nonnull LToByteFunction<K> extractor, V1 with1, V2 with2, @Nonnull LBiObjBytePredicate<? super V1, ? super V2> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(with1, with2, extractor.applyAsByte(k));
	}

	/** "Special" variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, V1, V2> LFunction<K, String> haveByte$With$(@Nonnull LToByteFunction<K> extractor, V1 with1, V2 with2, @Nonnull LBiObjByteFunction<? super V1, ? super V2, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(with1, with2, extractor.applyAsByte(k));
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, V1, V2> LPredicate<K> haveByte$With(@Nonnull LToByteFunction<K> extractor, @Nonnull LBiObjBytePredicate<? super V1, ? super V2> predicate, V1 with1, V2 with2) {
		return haveByte$With(extractor, with1, with2, predicate);
	}

	/** "Special" variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, V1, V2> LFunction<K, String> haveByte$With$(@Nonnull LToByteFunction<K> extractor, @Nonnull LBiObjByteFunction<? super V1, ? super V2, String> specialPredicate, V1 with1, V2 with2) {
		return haveByte$With$(extractor, with1, with2, specialPredicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, V1, V2> LPredicate<K> hasByte$With(@Nonnull LToByteFunction<K> extractor, V1 with1, V2 with2, @Nonnull LBiObjBytePredicate<? super V1, ? super V2> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(with1, with2, extractor.applyAsByte(k));
	}

	/** "Special" variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, V1, V2> LFunction<K, String> hasByte$With$(@Nonnull LToByteFunction<K> extractor, V1 with1, V2 with2, @Nonnull LBiObjByteFunction<? super V1, ? super V2, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(with1, with2, extractor.applyAsByte(k));
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, V1, V2> LPredicate<K> hasByte$With(@Nonnull LToByteFunction<K> extractor, @Nonnull LBiObjBytePredicate<? super V1, ? super V2> predicate, V1 with1, V2 with2) {
		return hasByte$With(extractor, with1, with2, predicate);
	}

	/** "Special" variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, V1, V2> LFunction<K, String> hasByte$With$(@Nonnull LToByteFunction<K> extractor, @Nonnull LBiObjByteFunction<? super V1, ? super V2, String> specialPredicate, V1 with1, V2 with2) {
		return hasByte$With$(extractor, with1, with2, specialPredicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K> LPredicate<K> haveDbl(@Nonnull LToDblFunction<K> extractor, @Nonnull LDblPredicate predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.applyAsDbl(k));
	}

	/** "Special" variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K> LFunction<K, String> haveDbl$(@Nonnull LToDblFunction<K> extractor, @Nonnull LDblFunction<String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(extractor.applyAsDbl(k));
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K> LPredicate<K> hasDbl(@Nonnull LToDblFunction<K> extractor, @Nonnull LDblPredicate predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.applyAsDbl(k));
	}

	/** "Special" variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K> LFunction<K, String> hasDbl$(@Nonnull LToDblFunction<K> extractor, @Nonnull LDblFunction<String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(extractor.applyAsDbl(k));
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K> LPredicate<K> haveDbl(@Nonnull LToDblFunction<K> extractor, double a2, @Nonnull LBiDblPredicate predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.applyAsDbl(k), a2);
	}

	/** "Special" variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K> LFunction<K, String> haveDbl$(@Nonnull LToDblFunction<K> extractor, double a2, @Nonnull LBiDblFunction<String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(extractor.applyAsDbl(k), a2);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K> LPredicate<K> haveDbl(@Nonnull LToDblFunction<K> extractor, @Nonnull LBiDblPredicate predicate, double a2) {
		return haveDbl(extractor, a2, predicate);
	}

	/** "Special" variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K> LFunction<K, String> haveDbl$(@Nonnull LToDblFunction<K> extractor, @Nonnull LBiDblFunction<String> specialPredicate, double a2) {
		return haveDbl$(extractor, a2, specialPredicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K> LPredicate<K> hasDbl(@Nonnull LToDblFunction<K> extractor, double a2, @Nonnull LBiDblPredicate predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.applyAsDbl(k), a2);
	}

	/** "Special" variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K> LFunction<K, String> hasDbl$(@Nonnull LToDblFunction<K> extractor, double a2, @Nonnull LBiDblFunction<String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(extractor.applyAsDbl(k), a2);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K> LPredicate<K> hasDbl(@Nonnull LToDblFunction<K> extractor, @Nonnull LBiDblPredicate predicate, double a2) {
		return hasDbl(extractor, a2, predicate);
	}

	/** "Special" variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K> LFunction<K, String> hasDbl$(@Nonnull LToDblFunction<K> extractor, @Nonnull LBiDblFunction<String> specialPredicate, double a2) {
		return hasDbl$(extractor, a2, specialPredicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K> LPredicate<K> haveDbl(@Nonnull LToDblFunction<K> extractor, double a2, double a3, @Nonnull LTriDblPredicate predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.applyAsDbl(k), a2, a3);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K> LPredicate<K> haveDbl(@Nonnull LToDblFunction<K> extractor, @Nonnull LTriDblPredicate predicate, double a2, double a3) {
		return haveDbl(extractor, a2, a3, predicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K> LPredicate<K> hasDbl(@Nonnull LToDblFunction<K> extractor, double a2, double a3, @Nonnull LTriDblPredicate predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.applyAsDbl(k), a2, a3);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K> LPredicate<K> hasDbl(@Nonnull LToDblFunction<K> extractor, @Nonnull LTriDblPredicate predicate, double a2, double a3) {
		return hasDbl(extractor, a2, a3, predicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K> LPredicate<K> haveDbl$Int(@Nonnull LToDblFunction<K> extractor, int v, @Nonnull LDblIntPredicate predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.applyAsDbl(k), v);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K> LPredicate<K> haveDbl$Int(@Nonnull LToDblFunction<K> extractor, @Nonnull LDblIntPredicate predicate, int v) {
		return haveDbl$Int(extractor, v, predicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K> LPredicate<K> hasDbl$Int(@Nonnull LToDblFunction<K> extractor, int v, @Nonnull LDblIntPredicate predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.applyAsDbl(k), v);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K> LPredicate<K> hasDbl$Int(@Nonnull LToDblFunction<K> extractor, @Nonnull LDblIntPredicate predicate, int v) {
		return hasDbl$Int(extractor, v, predicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, V> LPredicate<K> haveDbl(@Nonnull LToDblFunction<K> extractor, V v, @Nonnull LObjDblPredicate.LDblObjPred<? super V> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.testDblObj(extractor.applyAsDbl(k), v);
	}

	/** "Special" variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, V> LFunction<K, String> haveDbl$(@Nonnull LToDblFunction<K> extractor, V v, @Nonnull LObjDblFunction.LDblObjFunc<? super V, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.applyDblObj(extractor.applyAsDbl(k), v);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, V> LPredicate<K> haveDbl(@Nonnull LToDblFunction<K> extractor, @Nonnull LObjDblPredicate.LDblObjPred<? super V> predicate, V v) {
		return haveDbl(extractor, v, predicate);
	}

	/** "Special" variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, V> LFunction<K, String> haveDbl$(@Nonnull LToDblFunction<K> extractor, @Nonnull LObjDblFunction.LDblObjFunc<? super V, String> specialPredicate, V v) {
		return haveDbl$(extractor, v, specialPredicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, V> LPredicate<K> hasDbl(@Nonnull LToDblFunction<K> extractor, V v, @Nonnull LObjDblPredicate.LDblObjPred<? super V> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.testDblObj(extractor.applyAsDbl(k), v);
	}

	/** "Special" variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, V> LFunction<K, String> hasDbl$(@Nonnull LToDblFunction<K> extractor, V v, @Nonnull LObjDblFunction.LDblObjFunc<? super V, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.applyDblObj(extractor.applyAsDbl(k), v);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, V> LPredicate<K> hasDbl(@Nonnull LToDblFunction<K> extractor, @Nonnull LObjDblPredicate.LDblObjPred<? super V> predicate, V v) {
		return hasDbl(extractor, v, predicate);
	}

	/** "Special" variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, V> LFunction<K, String> hasDbl$(@Nonnull LToDblFunction<K> extractor, @Nonnull LObjDblFunction.LDblObjFunc<? super V, String> specialPredicate, V v) {
		return hasDbl$(extractor, v, specialPredicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, V1> LPredicate<K> haveDbl$WithDbl(@Nonnull LToDblFunction<K> extractor, V1 with1, @Nonnull LObjDblPredicate<? super V1> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(with1, extractor.applyAsDbl(k));
	}

	/** "Special" variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, V1> LFunction<K, String> haveDbl$WithDbl$(@Nonnull LToDblFunction<K> extractor, V1 with1, @Nonnull LObjDblFunction<? super V1, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(with1, extractor.applyAsDbl(k));
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, V1> LPredicate<K> haveDbl$WithDbl(@Nonnull LToDblFunction<K> extractor, @Nonnull LObjDblPredicate<? super V1> predicate, V1 with1) {
		return haveDbl$WithDbl(extractor, with1, predicate);
	}

	/** "Special" variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, V1> LFunction<K, String> haveDbl$WithDbl$(@Nonnull LToDblFunction<K> extractor, @Nonnull LObjDblFunction<? super V1, String> specialPredicate, V1 with1) {
		return haveDbl$WithDbl$(extractor, with1, specialPredicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, V1> LPredicate<K> hasDbl$WithDbl(@Nonnull LToDblFunction<K> extractor, V1 with1, @Nonnull LObjDblPredicate<? super V1> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(with1, extractor.applyAsDbl(k));
	}

	/** "Special" variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, V1> LFunction<K, String> hasDbl$WithDbl$(@Nonnull LToDblFunction<K> extractor, V1 with1, @Nonnull LObjDblFunction<? super V1, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(with1, extractor.applyAsDbl(k));
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, V1> LPredicate<K> hasDbl$WithDbl(@Nonnull LToDblFunction<K> extractor, @Nonnull LObjDblPredicate<? super V1> predicate, V1 with1) {
		return hasDbl$WithDbl(extractor, with1, predicate);
	}

	/** "Special" variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, V1> LFunction<K, String> hasDbl$WithDbl$(@Nonnull LToDblFunction<K> extractor, @Nonnull LObjDblFunction<? super V1, String> specialPredicate, V1 with1) {
		return hasDbl$WithDbl$(extractor, with1, specialPredicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, V1, V2> LPredicate<K> haveDbl$With(@Nonnull LToDblFunction<K> extractor, V1 with1, V2 with2, @Nonnull LBiObjDblPredicate<? super V1, ? super V2> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(with1, with2, extractor.applyAsDbl(k));
	}

	/** "Special" variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, V1, V2> LFunction<K, String> haveDbl$With$(@Nonnull LToDblFunction<K> extractor, V1 with1, V2 with2, @Nonnull LBiObjDblFunction<? super V1, ? super V2, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(with1, with2, extractor.applyAsDbl(k));
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, V1, V2> LPredicate<K> haveDbl$With(@Nonnull LToDblFunction<K> extractor, @Nonnull LBiObjDblPredicate<? super V1, ? super V2> predicate, V1 with1, V2 with2) {
		return haveDbl$With(extractor, with1, with2, predicate);
	}

	/** "Special" variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, V1, V2> LFunction<K, String> haveDbl$With$(@Nonnull LToDblFunction<K> extractor, @Nonnull LBiObjDblFunction<? super V1, ? super V2, String> specialPredicate, V1 with1, V2 with2) {
		return haveDbl$With$(extractor, with1, with2, specialPredicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, V1, V2> LPredicate<K> hasDbl$With(@Nonnull LToDblFunction<K> extractor, V1 with1, V2 with2, @Nonnull LBiObjDblPredicate<? super V1, ? super V2> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(with1, with2, extractor.applyAsDbl(k));
	}

	/** "Special" variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, V1, V2> LFunction<K, String> hasDbl$With$(@Nonnull LToDblFunction<K> extractor, V1 with1, V2 with2, @Nonnull LBiObjDblFunction<? super V1, ? super V2, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(with1, with2, extractor.applyAsDbl(k));
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, V1, V2> LPredicate<K> hasDbl$With(@Nonnull LToDblFunction<K> extractor, @Nonnull LBiObjDblPredicate<? super V1, ? super V2> predicate, V1 with1, V2 with2) {
		return hasDbl$With(extractor, with1, with2, predicate);
	}

	/** "Special" variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, V1, V2> LFunction<K, String> hasDbl$With$(@Nonnull LToDblFunction<K> extractor, @Nonnull LBiObjDblFunction<? super V1, ? super V2, String> specialPredicate, V1 with1, V2 with2) {
		return hasDbl$With$(extractor, with1, with2, specialPredicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K> LPredicate<K> haveChar(@Nonnull LToCharFunction<K> extractor, @Nonnull LCharPredicate predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.applyAsChar(k));
	}

	/** "Special" variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K> LFunction<K, String> haveChar$(@Nonnull LToCharFunction<K> extractor, @Nonnull LCharFunction<String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(extractor.applyAsChar(k));
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K> LPredicate<K> hasChar(@Nonnull LToCharFunction<K> extractor, @Nonnull LCharPredicate predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.applyAsChar(k));
	}

	/** "Special" variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K> LFunction<K, String> hasChar$(@Nonnull LToCharFunction<K> extractor, @Nonnull LCharFunction<String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(extractor.applyAsChar(k));
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K> LPredicate<K> haveChar(@Nonnull LToCharFunction<K> extractor, char a2, @Nonnull LBiCharPredicate predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.applyAsChar(k), a2);
	}

	/** "Special" variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K> LFunction<K, String> haveChar$(@Nonnull LToCharFunction<K> extractor, char a2, @Nonnull LBiCharFunction<String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(extractor.applyAsChar(k), a2);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K> LPredicate<K> haveChar(@Nonnull LToCharFunction<K> extractor, @Nonnull LBiCharPredicate predicate, char a2) {
		return haveChar(extractor, a2, predicate);
	}

	/** "Special" variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K> LFunction<K, String> haveChar$(@Nonnull LToCharFunction<K> extractor, @Nonnull LBiCharFunction<String> specialPredicate, char a2) {
		return haveChar$(extractor, a2, specialPredicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K> LPredicate<K> hasChar(@Nonnull LToCharFunction<K> extractor, char a2, @Nonnull LBiCharPredicate predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.applyAsChar(k), a2);
	}

	/** "Special" variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K> LFunction<K, String> hasChar$(@Nonnull LToCharFunction<K> extractor, char a2, @Nonnull LBiCharFunction<String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(extractor.applyAsChar(k), a2);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K> LPredicate<K> hasChar(@Nonnull LToCharFunction<K> extractor, @Nonnull LBiCharPredicate predicate, char a2) {
		return hasChar(extractor, a2, predicate);
	}

	/** "Special" variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K> LFunction<K, String> hasChar$(@Nonnull LToCharFunction<K> extractor, @Nonnull LBiCharFunction<String> specialPredicate, char a2) {
		return hasChar$(extractor, a2, specialPredicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K> LPredicate<K> haveChar(@Nonnull LToCharFunction<K> extractor, char a2, char a3, @Nonnull LTriCharPredicate predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.applyAsChar(k), a2, a3);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K> LPredicate<K> haveChar(@Nonnull LToCharFunction<K> extractor, @Nonnull LTriCharPredicate predicate, char a2, char a3) {
		return haveChar(extractor, a2, a3, predicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K> LPredicate<K> hasChar(@Nonnull LToCharFunction<K> extractor, char a2, char a3, @Nonnull LTriCharPredicate predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.applyAsChar(k), a2, a3);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K> LPredicate<K> hasChar(@Nonnull LToCharFunction<K> extractor, @Nonnull LTriCharPredicate predicate, char a2, char a3) {
		return hasChar(extractor, a2, a3, predicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K> LPredicate<K> haveChar$Int(@Nonnull LToCharFunction<K> extractor, int v, @Nonnull LCharIntPredicate predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.applyAsChar(k), v);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K> LPredicate<K> haveChar$Int(@Nonnull LToCharFunction<K> extractor, @Nonnull LCharIntPredicate predicate, int v) {
		return haveChar$Int(extractor, v, predicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K> LPredicate<K> hasChar$Int(@Nonnull LToCharFunction<K> extractor, int v, @Nonnull LCharIntPredicate predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.applyAsChar(k), v);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K> LPredicate<K> hasChar$Int(@Nonnull LToCharFunction<K> extractor, @Nonnull LCharIntPredicate predicate, int v) {
		return hasChar$Int(extractor, v, predicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, V> LPredicate<K> haveChar(@Nonnull LToCharFunction<K> extractor, V v, @Nonnull LObjCharPredicate.LCharObjPred<? super V> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.testCharObj(extractor.applyAsChar(k), v);
	}

	/** "Special" variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, V> LFunction<K, String> haveChar$(@Nonnull LToCharFunction<K> extractor, V v, @Nonnull LObjCharFunction.LCharObjFunc<? super V, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.applyCharObj(extractor.applyAsChar(k), v);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, V> LPredicate<K> haveChar(@Nonnull LToCharFunction<K> extractor, @Nonnull LObjCharPredicate.LCharObjPred<? super V> predicate, V v) {
		return haveChar(extractor, v, predicate);
	}

	/** "Special" variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, V> LFunction<K, String> haveChar$(@Nonnull LToCharFunction<K> extractor, @Nonnull LObjCharFunction.LCharObjFunc<? super V, String> specialPredicate, V v) {
		return haveChar$(extractor, v, specialPredicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, V> LPredicate<K> hasChar(@Nonnull LToCharFunction<K> extractor, V v, @Nonnull LObjCharPredicate.LCharObjPred<? super V> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.testCharObj(extractor.applyAsChar(k), v);
	}

	/** "Special" variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, V> LFunction<K, String> hasChar$(@Nonnull LToCharFunction<K> extractor, V v, @Nonnull LObjCharFunction.LCharObjFunc<? super V, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.applyCharObj(extractor.applyAsChar(k), v);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, V> LPredicate<K> hasChar(@Nonnull LToCharFunction<K> extractor, @Nonnull LObjCharPredicate.LCharObjPred<? super V> predicate, V v) {
		return hasChar(extractor, v, predicate);
	}

	/** "Special" variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, V> LFunction<K, String> hasChar$(@Nonnull LToCharFunction<K> extractor, @Nonnull LObjCharFunction.LCharObjFunc<? super V, String> specialPredicate, V v) {
		return hasChar$(extractor, v, specialPredicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, V1> LPredicate<K> haveChar$WithChar(@Nonnull LToCharFunction<K> extractor, V1 with1, @Nonnull LObjCharPredicate<? super V1> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(with1, extractor.applyAsChar(k));
	}

	/** "Special" variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, V1> LFunction<K, String> haveChar$WithChar$(@Nonnull LToCharFunction<K> extractor, V1 with1, @Nonnull LObjCharFunction<? super V1, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(with1, extractor.applyAsChar(k));
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, V1> LPredicate<K> haveChar$WithChar(@Nonnull LToCharFunction<K> extractor, @Nonnull LObjCharPredicate<? super V1> predicate, V1 with1) {
		return haveChar$WithChar(extractor, with1, predicate);
	}

	/** "Special" variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, V1> LFunction<K, String> haveChar$WithChar$(@Nonnull LToCharFunction<K> extractor, @Nonnull LObjCharFunction<? super V1, String> specialPredicate, V1 with1) {
		return haveChar$WithChar$(extractor, with1, specialPredicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, V1> LPredicate<K> hasChar$WithChar(@Nonnull LToCharFunction<K> extractor, V1 with1, @Nonnull LObjCharPredicate<? super V1> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(with1, extractor.applyAsChar(k));
	}

	/** "Special" variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, V1> LFunction<K, String> hasChar$WithChar$(@Nonnull LToCharFunction<K> extractor, V1 with1, @Nonnull LObjCharFunction<? super V1, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(with1, extractor.applyAsChar(k));
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, V1> LPredicate<K> hasChar$WithChar(@Nonnull LToCharFunction<K> extractor, @Nonnull LObjCharPredicate<? super V1> predicate, V1 with1) {
		return hasChar$WithChar(extractor, with1, predicate);
	}

	/** "Special" variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, V1> LFunction<K, String> hasChar$WithChar$(@Nonnull LToCharFunction<K> extractor, @Nonnull LObjCharFunction<? super V1, String> specialPredicate, V1 with1) {
		return hasChar$WithChar$(extractor, with1, specialPredicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, V1, V2> LPredicate<K> haveChar$With(@Nonnull LToCharFunction<K> extractor, V1 with1, V2 with2, @Nonnull LBiObjCharPredicate<? super V1, ? super V2> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(with1, with2, extractor.applyAsChar(k));
	}

	/** "Special" variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, V1, V2> LFunction<K, String> haveChar$With$(@Nonnull LToCharFunction<K> extractor, V1 with1, V2 with2, @Nonnull LBiObjCharFunction<? super V1, ? super V2, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(with1, with2, extractor.applyAsChar(k));
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, V1, V2> LPredicate<K> haveChar$With(@Nonnull LToCharFunction<K> extractor, @Nonnull LBiObjCharPredicate<? super V1, ? super V2> predicate, V1 with1, V2 with2) {
		return haveChar$With(extractor, with1, with2, predicate);
	}

	/** "Special" variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, V1, V2> LFunction<K, String> haveChar$With$(@Nonnull LToCharFunction<K> extractor, @Nonnull LBiObjCharFunction<? super V1, ? super V2, String> specialPredicate, V1 with1, V2 with2) {
		return haveChar$With$(extractor, with1, with2, specialPredicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, V1, V2> LPredicate<K> hasChar$With(@Nonnull LToCharFunction<K> extractor, V1 with1, V2 with2, @Nonnull LBiObjCharPredicate<? super V1, ? super V2> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(with1, with2, extractor.applyAsChar(k));
	}

	/** "Special" variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, V1, V2> LFunction<K, String> hasChar$With$(@Nonnull LToCharFunction<K> extractor, V1 with1, V2 with2, @Nonnull LBiObjCharFunction<? super V1, ? super V2, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(with1, with2, extractor.applyAsChar(k));
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, V1, V2> LPredicate<K> hasChar$With(@Nonnull LToCharFunction<K> extractor, @Nonnull LBiObjCharPredicate<? super V1, ? super V2> predicate, V1 with1, V2 with2) {
		return hasChar$With(extractor, with1, with2, predicate);
	}

	/** "Special" variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, V1, V2> LFunction<K, String> hasChar$With$(@Nonnull LToCharFunction<K> extractor, @Nonnull LBiObjCharFunction<? super V1, ? super V2, String> specialPredicate, V1 with1, V2 with2) {
		return hasChar$With$(extractor, with1, with2, specialPredicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K> LPredicate<K> haveSrt(@Nonnull LToSrtFunction<K> extractor, @Nonnull LSrtPredicate predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.applyAsSrt(k));
	}

	/** "Special" variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K> LFunction<K, String> haveSrt$(@Nonnull LToSrtFunction<K> extractor, @Nonnull LSrtFunction<String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(extractor.applyAsSrt(k));
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K> LPredicate<K> hasSrt(@Nonnull LToSrtFunction<K> extractor, @Nonnull LSrtPredicate predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.applyAsSrt(k));
	}

	/** "Special" variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K> LFunction<K, String> hasSrt$(@Nonnull LToSrtFunction<K> extractor, @Nonnull LSrtFunction<String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(extractor.applyAsSrt(k));
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K> LPredicate<K> haveSrt(@Nonnull LToSrtFunction<K> extractor, short a2, @Nonnull LBiSrtPredicate predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.applyAsSrt(k), a2);
	}

	/** "Special" variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K> LFunction<K, String> haveSrt$(@Nonnull LToSrtFunction<K> extractor, short a2, @Nonnull LBiSrtFunction<String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(extractor.applyAsSrt(k), a2);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K> LPredicate<K> haveSrt(@Nonnull LToSrtFunction<K> extractor, @Nonnull LBiSrtPredicate predicate, short a2) {
		return haveSrt(extractor, a2, predicate);
	}

	/** "Special" variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K> LFunction<K, String> haveSrt$(@Nonnull LToSrtFunction<K> extractor, @Nonnull LBiSrtFunction<String> specialPredicate, short a2) {
		return haveSrt$(extractor, a2, specialPredicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K> LPredicate<K> hasSrt(@Nonnull LToSrtFunction<K> extractor, short a2, @Nonnull LBiSrtPredicate predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.applyAsSrt(k), a2);
	}

	/** "Special" variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K> LFunction<K, String> hasSrt$(@Nonnull LToSrtFunction<K> extractor, short a2, @Nonnull LBiSrtFunction<String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(extractor.applyAsSrt(k), a2);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K> LPredicate<K> hasSrt(@Nonnull LToSrtFunction<K> extractor, @Nonnull LBiSrtPredicate predicate, short a2) {
		return hasSrt(extractor, a2, predicate);
	}

	/** "Special" variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K> LFunction<K, String> hasSrt$(@Nonnull LToSrtFunction<K> extractor, @Nonnull LBiSrtFunction<String> specialPredicate, short a2) {
		return hasSrt$(extractor, a2, specialPredicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K> LPredicate<K> haveSrt(@Nonnull LToSrtFunction<K> extractor, short a2, short a3, @Nonnull LTriSrtPredicate predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.applyAsSrt(k), a2, a3);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K> LPredicate<K> haveSrt(@Nonnull LToSrtFunction<K> extractor, @Nonnull LTriSrtPredicate predicate, short a2, short a3) {
		return haveSrt(extractor, a2, a3, predicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K> LPredicate<K> hasSrt(@Nonnull LToSrtFunction<K> extractor, short a2, short a3, @Nonnull LTriSrtPredicate predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.applyAsSrt(k), a2, a3);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K> LPredicate<K> hasSrt(@Nonnull LToSrtFunction<K> extractor, @Nonnull LTriSrtPredicate predicate, short a2, short a3) {
		return hasSrt(extractor, a2, a3, predicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K> LPredicate<K> haveSrt$Int(@Nonnull LToSrtFunction<K> extractor, int v, @Nonnull LSrtIntPredicate predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.applyAsSrt(k), v);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K> LPredicate<K> haveSrt$Int(@Nonnull LToSrtFunction<K> extractor, @Nonnull LSrtIntPredicate predicate, int v) {
		return haveSrt$Int(extractor, v, predicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K> LPredicate<K> hasSrt$Int(@Nonnull LToSrtFunction<K> extractor, int v, @Nonnull LSrtIntPredicate predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.applyAsSrt(k), v);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K> LPredicate<K> hasSrt$Int(@Nonnull LToSrtFunction<K> extractor, @Nonnull LSrtIntPredicate predicate, int v) {
		return hasSrt$Int(extractor, v, predicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, V> LPredicate<K> haveSrt(@Nonnull LToSrtFunction<K> extractor, V v, @Nonnull LObjSrtPredicate.LSrtObjPred<? super V> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.testSrtObj(extractor.applyAsSrt(k), v);
	}

	/** "Special" variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, V> LFunction<K, String> haveSrt$(@Nonnull LToSrtFunction<K> extractor, V v, @Nonnull LObjSrtFunction.LSrtObjFunc<? super V, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.applySrtObj(extractor.applyAsSrt(k), v);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, V> LPredicate<K> haveSrt(@Nonnull LToSrtFunction<K> extractor, @Nonnull LObjSrtPredicate.LSrtObjPred<? super V> predicate, V v) {
		return haveSrt(extractor, v, predicate);
	}

	/** "Special" variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, V> LFunction<K, String> haveSrt$(@Nonnull LToSrtFunction<K> extractor, @Nonnull LObjSrtFunction.LSrtObjFunc<? super V, String> specialPredicate, V v) {
		return haveSrt$(extractor, v, specialPredicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, V> LPredicate<K> hasSrt(@Nonnull LToSrtFunction<K> extractor, V v, @Nonnull LObjSrtPredicate.LSrtObjPred<? super V> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.testSrtObj(extractor.applyAsSrt(k), v);
	}

	/** "Special" variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, V> LFunction<K, String> hasSrt$(@Nonnull LToSrtFunction<K> extractor, V v, @Nonnull LObjSrtFunction.LSrtObjFunc<? super V, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.applySrtObj(extractor.applyAsSrt(k), v);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, V> LPredicate<K> hasSrt(@Nonnull LToSrtFunction<K> extractor, @Nonnull LObjSrtPredicate.LSrtObjPred<? super V> predicate, V v) {
		return hasSrt(extractor, v, predicate);
	}

	/** "Special" variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, V> LFunction<K, String> hasSrt$(@Nonnull LToSrtFunction<K> extractor, @Nonnull LObjSrtFunction.LSrtObjFunc<? super V, String> specialPredicate, V v) {
		return hasSrt$(extractor, v, specialPredicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, V1> LPredicate<K> haveSrt$WithSrt(@Nonnull LToSrtFunction<K> extractor, V1 with1, @Nonnull LObjSrtPredicate<? super V1> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(with1, extractor.applyAsSrt(k));
	}

	/** "Special" variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, V1> LFunction<K, String> haveSrt$WithSrt$(@Nonnull LToSrtFunction<K> extractor, V1 with1, @Nonnull LObjSrtFunction<? super V1, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(with1, extractor.applyAsSrt(k));
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, V1> LPredicate<K> haveSrt$WithSrt(@Nonnull LToSrtFunction<K> extractor, @Nonnull LObjSrtPredicate<? super V1> predicate, V1 with1) {
		return haveSrt$WithSrt(extractor, with1, predicate);
	}

	/** "Special" variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, V1> LFunction<K, String> haveSrt$WithSrt$(@Nonnull LToSrtFunction<K> extractor, @Nonnull LObjSrtFunction<? super V1, String> specialPredicate, V1 with1) {
		return haveSrt$WithSrt$(extractor, with1, specialPredicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, V1> LPredicate<K> hasSrt$WithSrt(@Nonnull LToSrtFunction<K> extractor, V1 with1, @Nonnull LObjSrtPredicate<? super V1> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(with1, extractor.applyAsSrt(k));
	}

	/** "Special" variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, V1> LFunction<K, String> hasSrt$WithSrt$(@Nonnull LToSrtFunction<K> extractor, V1 with1, @Nonnull LObjSrtFunction<? super V1, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(with1, extractor.applyAsSrt(k));
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, V1> LPredicate<K> hasSrt$WithSrt(@Nonnull LToSrtFunction<K> extractor, @Nonnull LObjSrtPredicate<? super V1> predicate, V1 with1) {
		return hasSrt$WithSrt(extractor, with1, predicate);
	}

	/** "Special" variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, V1> LFunction<K, String> hasSrt$WithSrt$(@Nonnull LToSrtFunction<K> extractor, @Nonnull LObjSrtFunction<? super V1, String> specialPredicate, V1 with1) {
		return hasSrt$WithSrt$(extractor, with1, specialPredicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, V1, V2> LPredicate<K> haveSrt$With(@Nonnull LToSrtFunction<K> extractor, V1 with1, V2 with2, @Nonnull LBiObjSrtPredicate<? super V1, ? super V2> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(with1, with2, extractor.applyAsSrt(k));
	}

	/** "Special" variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, V1, V2> LFunction<K, String> haveSrt$With$(@Nonnull LToSrtFunction<K> extractor, V1 with1, V2 with2, @Nonnull LBiObjSrtFunction<? super V1, ? super V2, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(with1, with2, extractor.applyAsSrt(k));
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, V1, V2> LPredicate<K> haveSrt$With(@Nonnull LToSrtFunction<K> extractor, @Nonnull LBiObjSrtPredicate<? super V1, ? super V2> predicate, V1 with1, V2 with2) {
		return haveSrt$With(extractor, with1, with2, predicate);
	}

	/** "Special" variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, V1, V2> LFunction<K, String> haveSrt$With$(@Nonnull LToSrtFunction<K> extractor, @Nonnull LBiObjSrtFunction<? super V1, ? super V2, String> specialPredicate, V1 with1, V2 with2) {
		return haveSrt$With$(extractor, with1, with2, specialPredicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, V1, V2> LPredicate<K> hasSrt$With(@Nonnull LToSrtFunction<K> extractor, V1 with1, V2 with2, @Nonnull LBiObjSrtPredicate<? super V1, ? super V2> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(with1, with2, extractor.applyAsSrt(k));
	}

	/** "Special" variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, V1, V2> LFunction<K, String> hasSrt$With$(@Nonnull LToSrtFunction<K> extractor, V1 with1, V2 with2, @Nonnull LBiObjSrtFunction<? super V1, ? super V2, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(with1, with2, extractor.applyAsSrt(k));
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, V1, V2> LPredicate<K> hasSrt$With(@Nonnull LToSrtFunction<K> extractor, @Nonnull LBiObjSrtPredicate<? super V1, ? super V2> predicate, V1 with1, V2 with2) {
		return hasSrt$With(extractor, with1, with2, predicate);
	}

	/** "Special" variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, V1, V2> LFunction<K, String> hasSrt$With$(@Nonnull LToSrtFunction<K> extractor, @Nonnull LBiObjSrtFunction<? super V1, ? super V2, String> specialPredicate, V1 with1, V2 with2) {
		return hasSrt$With$(extractor, with1, with2, specialPredicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K> LPredicate<K> haveFlt(@Nonnull LToFltFunction<K> extractor, @Nonnull LFltPredicate predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.applyAsFlt(k));
	}

	/** "Special" variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K> LFunction<K, String> haveFlt$(@Nonnull LToFltFunction<K> extractor, @Nonnull LFltFunction<String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(extractor.applyAsFlt(k));
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K> LPredicate<K> hasFlt(@Nonnull LToFltFunction<K> extractor, @Nonnull LFltPredicate predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.applyAsFlt(k));
	}

	/** "Special" variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K> LFunction<K, String> hasFlt$(@Nonnull LToFltFunction<K> extractor, @Nonnull LFltFunction<String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(extractor.applyAsFlt(k));
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K> LPredicate<K> haveFlt(@Nonnull LToFltFunction<K> extractor, float a2, @Nonnull LBiFltPredicate predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.applyAsFlt(k), a2);
	}

	/** "Special" variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K> LFunction<K, String> haveFlt$(@Nonnull LToFltFunction<K> extractor, float a2, @Nonnull LBiFltFunction<String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(extractor.applyAsFlt(k), a2);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K> LPredicate<K> haveFlt(@Nonnull LToFltFunction<K> extractor, @Nonnull LBiFltPredicate predicate, float a2) {
		return haveFlt(extractor, a2, predicate);
	}

	/** "Special" variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K> LFunction<K, String> haveFlt$(@Nonnull LToFltFunction<K> extractor, @Nonnull LBiFltFunction<String> specialPredicate, float a2) {
		return haveFlt$(extractor, a2, specialPredicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K> LPredicate<K> hasFlt(@Nonnull LToFltFunction<K> extractor, float a2, @Nonnull LBiFltPredicate predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.applyAsFlt(k), a2);
	}

	/** "Special" variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K> LFunction<K, String> hasFlt$(@Nonnull LToFltFunction<K> extractor, float a2, @Nonnull LBiFltFunction<String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(extractor.applyAsFlt(k), a2);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K> LPredicate<K> hasFlt(@Nonnull LToFltFunction<K> extractor, @Nonnull LBiFltPredicate predicate, float a2) {
		return hasFlt(extractor, a2, predicate);
	}

	/** "Special" variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K> LFunction<K, String> hasFlt$(@Nonnull LToFltFunction<K> extractor, @Nonnull LBiFltFunction<String> specialPredicate, float a2) {
		return hasFlt$(extractor, a2, specialPredicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K> LPredicate<K> haveFlt(@Nonnull LToFltFunction<K> extractor, float a2, float a3, @Nonnull LTriFltPredicate predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.applyAsFlt(k), a2, a3);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K> LPredicate<K> haveFlt(@Nonnull LToFltFunction<K> extractor, @Nonnull LTriFltPredicate predicate, float a2, float a3) {
		return haveFlt(extractor, a2, a3, predicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K> LPredicate<K> hasFlt(@Nonnull LToFltFunction<K> extractor, float a2, float a3, @Nonnull LTriFltPredicate predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.applyAsFlt(k), a2, a3);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K> LPredicate<K> hasFlt(@Nonnull LToFltFunction<K> extractor, @Nonnull LTriFltPredicate predicate, float a2, float a3) {
		return hasFlt(extractor, a2, a3, predicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K> LPredicate<K> haveFlt$Int(@Nonnull LToFltFunction<K> extractor, int v, @Nonnull LFltIntPredicate predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.applyAsFlt(k), v);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K> LPredicate<K> haveFlt$Int(@Nonnull LToFltFunction<K> extractor, @Nonnull LFltIntPredicate predicate, int v) {
		return haveFlt$Int(extractor, v, predicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K> LPredicate<K> hasFlt$Int(@Nonnull LToFltFunction<K> extractor, int v, @Nonnull LFltIntPredicate predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.applyAsFlt(k), v);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K> LPredicate<K> hasFlt$Int(@Nonnull LToFltFunction<K> extractor, @Nonnull LFltIntPredicate predicate, int v) {
		return hasFlt$Int(extractor, v, predicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, V> LPredicate<K> haveFlt(@Nonnull LToFltFunction<K> extractor, V v, @Nonnull LObjFltPredicate.LFltObjPred<? super V> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.testFltObj(extractor.applyAsFlt(k), v);
	}

	/** "Special" variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, V> LFunction<K, String> haveFlt$(@Nonnull LToFltFunction<K> extractor, V v, @Nonnull LObjFltFunction.LFltObjFunc<? super V, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.applyFltObj(extractor.applyAsFlt(k), v);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, V> LPredicate<K> haveFlt(@Nonnull LToFltFunction<K> extractor, @Nonnull LObjFltPredicate.LFltObjPred<? super V> predicate, V v) {
		return haveFlt(extractor, v, predicate);
	}

	/** "Special" variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, V> LFunction<K, String> haveFlt$(@Nonnull LToFltFunction<K> extractor, @Nonnull LObjFltFunction.LFltObjFunc<? super V, String> specialPredicate, V v) {
		return haveFlt$(extractor, v, specialPredicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, V> LPredicate<K> hasFlt(@Nonnull LToFltFunction<K> extractor, V v, @Nonnull LObjFltPredicate.LFltObjPred<? super V> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.testFltObj(extractor.applyAsFlt(k), v);
	}

	/** "Special" variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, V> LFunction<K, String> hasFlt$(@Nonnull LToFltFunction<K> extractor, V v, @Nonnull LObjFltFunction.LFltObjFunc<? super V, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.applyFltObj(extractor.applyAsFlt(k), v);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, V> LPredicate<K> hasFlt(@Nonnull LToFltFunction<K> extractor, @Nonnull LObjFltPredicate.LFltObjPred<? super V> predicate, V v) {
		return hasFlt(extractor, v, predicate);
	}

	/** "Special" variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, V> LFunction<K, String> hasFlt$(@Nonnull LToFltFunction<K> extractor, @Nonnull LObjFltFunction.LFltObjFunc<? super V, String> specialPredicate, V v) {
		return hasFlt$(extractor, v, specialPredicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, V1> LPredicate<K> haveFlt$WithFlt(@Nonnull LToFltFunction<K> extractor, V1 with1, @Nonnull LObjFltPredicate<? super V1> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(with1, extractor.applyAsFlt(k));
	}

	/** "Special" variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, V1> LFunction<K, String> haveFlt$WithFlt$(@Nonnull LToFltFunction<K> extractor, V1 with1, @Nonnull LObjFltFunction<? super V1, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(with1, extractor.applyAsFlt(k));
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, V1> LPredicate<K> haveFlt$WithFlt(@Nonnull LToFltFunction<K> extractor, @Nonnull LObjFltPredicate<? super V1> predicate, V1 with1) {
		return haveFlt$WithFlt(extractor, with1, predicate);
	}

	/** "Special" variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, V1> LFunction<K, String> haveFlt$WithFlt$(@Nonnull LToFltFunction<K> extractor, @Nonnull LObjFltFunction<? super V1, String> specialPredicate, V1 with1) {
		return haveFlt$WithFlt$(extractor, with1, specialPredicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, V1> LPredicate<K> hasFlt$WithFlt(@Nonnull LToFltFunction<K> extractor, V1 with1, @Nonnull LObjFltPredicate<? super V1> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(with1, extractor.applyAsFlt(k));
	}

	/** "Special" variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, V1> LFunction<K, String> hasFlt$WithFlt$(@Nonnull LToFltFunction<K> extractor, V1 with1, @Nonnull LObjFltFunction<? super V1, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(with1, extractor.applyAsFlt(k));
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, V1> LPredicate<K> hasFlt$WithFlt(@Nonnull LToFltFunction<K> extractor, @Nonnull LObjFltPredicate<? super V1> predicate, V1 with1) {
		return hasFlt$WithFlt(extractor, with1, predicate);
	}

	/** "Special" variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, V1> LFunction<K, String> hasFlt$WithFlt$(@Nonnull LToFltFunction<K> extractor, @Nonnull LObjFltFunction<? super V1, String> specialPredicate, V1 with1) {
		return hasFlt$WithFlt$(extractor, with1, specialPredicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, V1, V2> LPredicate<K> haveFlt$With(@Nonnull LToFltFunction<K> extractor, V1 with1, V2 with2, @Nonnull LBiObjFltPredicate<? super V1, ? super V2> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(with1, with2, extractor.applyAsFlt(k));
	}

	/** "Special" variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, V1, V2> LFunction<K, String> haveFlt$With$(@Nonnull LToFltFunction<K> extractor, V1 with1, V2 with2, @Nonnull LBiObjFltFunction<? super V1, ? super V2, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(with1, with2, extractor.applyAsFlt(k));
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, V1, V2> LPredicate<K> haveFlt$With(@Nonnull LToFltFunction<K> extractor, @Nonnull LBiObjFltPredicate<? super V1, ? super V2> predicate, V1 with1, V2 with2) {
		return haveFlt$With(extractor, with1, with2, predicate);
	}

	/** "Special" variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, V1, V2> LFunction<K, String> haveFlt$With$(@Nonnull LToFltFunction<K> extractor, @Nonnull LBiObjFltFunction<? super V1, ? super V2, String> specialPredicate, V1 with1, V2 with2) {
		return haveFlt$With$(extractor, with1, with2, specialPredicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, V1, V2> LPredicate<K> hasFlt$With(@Nonnull LToFltFunction<K> extractor, V1 with1, V2 with2, @Nonnull LBiObjFltPredicate<? super V1, ? super V2> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(with1, with2, extractor.applyAsFlt(k));
	}

	/** "Special" variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, V1, V2> LFunction<K, String> hasFlt$With$(@Nonnull LToFltFunction<K> extractor, V1 with1, V2 with2, @Nonnull LBiObjFltFunction<? super V1, ? super V2, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(with1, with2, extractor.applyAsFlt(k));
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, V1, V2> LPredicate<K> hasFlt$With(@Nonnull LToFltFunction<K> extractor, @Nonnull LBiObjFltPredicate<? super V1, ? super V2> predicate, V1 with1, V2 with2) {
		return hasFlt$With(extractor, with1, with2, predicate);
	}

	/** "Special" variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, V1, V2> LFunction<K, String> hasFlt$With$(@Nonnull LToFltFunction<K> extractor, @Nonnull LBiObjFltFunction<? super V1, ? super V2, String> specialPredicate, V1 with1, V2 with2) {
		return hasFlt$With$(extractor, with1, with2, specialPredicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K> LPredicate<K> haveInt(@Nonnull LToIntFunction<K> extractor, @Nonnull LIntPredicate predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.applyAsInt(k));
	}

	/** "Special" variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K> LFunction<K, String> haveInt$(@Nonnull LToIntFunction<K> extractor, @Nonnull LIntFunction<String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(extractor.applyAsInt(k));
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K> LPredicate<K> hasInt(@Nonnull LToIntFunction<K> extractor, @Nonnull LIntPredicate predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.applyAsInt(k));
	}

	/** "Special" variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K> LFunction<K, String> hasInt$(@Nonnull LToIntFunction<K> extractor, @Nonnull LIntFunction<String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(extractor.applyAsInt(k));
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K> LPredicate<K> haveInt(@Nonnull LToIntFunction<K> extractor, int a2, @Nonnull LBiIntPredicate predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.applyAsInt(k), a2);
	}

	/** "Special" variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K> LFunction<K, String> haveInt$(@Nonnull LToIntFunction<K> extractor, int a2, @Nonnull LBiIntFunction<String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(extractor.applyAsInt(k), a2);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K> LPredicate<K> haveInt(@Nonnull LToIntFunction<K> extractor, @Nonnull LBiIntPredicate predicate, int a2) {
		return haveInt(extractor, a2, predicate);
	}

	/** "Special" variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K> LFunction<K, String> haveInt$(@Nonnull LToIntFunction<K> extractor, @Nonnull LBiIntFunction<String> specialPredicate, int a2) {
		return haveInt$(extractor, a2, specialPredicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K> LPredicate<K> hasInt(@Nonnull LToIntFunction<K> extractor, int a2, @Nonnull LBiIntPredicate predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.applyAsInt(k), a2);
	}

	/** "Special" variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K> LFunction<K, String> hasInt$(@Nonnull LToIntFunction<K> extractor, int a2, @Nonnull LBiIntFunction<String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(extractor.applyAsInt(k), a2);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K> LPredicate<K> hasInt(@Nonnull LToIntFunction<K> extractor, @Nonnull LBiIntPredicate predicate, int a2) {
		return hasInt(extractor, a2, predicate);
	}

	/** "Special" variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K> LFunction<K, String> hasInt$(@Nonnull LToIntFunction<K> extractor, @Nonnull LBiIntFunction<String> specialPredicate, int a2) {
		return hasInt$(extractor, a2, specialPredicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K> LPredicate<K> haveInt(@Nonnull LToIntFunction<K> extractor, int a2, int a3, @Nonnull LTriIntPredicate predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.applyAsInt(k), a2, a3);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K> LPredicate<K> haveInt(@Nonnull LToIntFunction<K> extractor, @Nonnull LTriIntPredicate predicate, int a2, int a3) {
		return haveInt(extractor, a2, a3, predicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K> LPredicate<K> hasInt(@Nonnull LToIntFunction<K> extractor, int a2, int a3, @Nonnull LTriIntPredicate predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.applyAsInt(k), a2, a3);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K> LPredicate<K> hasInt(@Nonnull LToIntFunction<K> extractor, @Nonnull LTriIntPredicate predicate, int a2, int a3) {
		return hasInt(extractor, a2, a3, predicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K> LPredicate<K> haveInt$Bool_(@Nonnull LToIntFunction<K> extractor, boolean v, @Nonnull LBoolIntPredicate.LIntBoolPred predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.testIntBool(extractor.applyAsInt(k), v);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K> LPredicate<K> haveInt$Bool_(@Nonnull LToIntFunction<K> extractor, @Nonnull LBoolIntPredicate.LIntBoolPred predicate, boolean v) {
		return haveInt$Bool_(extractor, v, predicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K> LPredicate<K> hasInt$Bool_(@Nonnull LToIntFunction<K> extractor, boolean v, @Nonnull LBoolIntPredicate.LIntBoolPred predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.testIntBool(extractor.applyAsInt(k), v);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K> LPredicate<K> hasInt$Bool_(@Nonnull LToIntFunction<K> extractor, @Nonnull LBoolIntPredicate.LIntBoolPred predicate, boolean v) {
		return hasInt$Bool_(extractor, v, predicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K> LPredicate<K> haveInt$Byte_(@Nonnull LToIntFunction<K> extractor, byte v, @Nonnull LByteIntPredicate.LIntBytePred predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.testIntByte(extractor.applyAsInt(k), v);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K> LPredicate<K> haveInt$Byte_(@Nonnull LToIntFunction<K> extractor, @Nonnull LByteIntPredicate.LIntBytePred predicate, byte v) {
		return haveInt$Byte_(extractor, v, predicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K> LPredicate<K> hasInt$Byte_(@Nonnull LToIntFunction<K> extractor, byte v, @Nonnull LByteIntPredicate.LIntBytePred predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.testIntByte(extractor.applyAsInt(k), v);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K> LPredicate<K> hasInt$Byte_(@Nonnull LToIntFunction<K> extractor, @Nonnull LByteIntPredicate.LIntBytePred predicate, byte v) {
		return hasInt$Byte_(extractor, v, predicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K> LPredicate<K> haveInt$Dbl_(@Nonnull LToIntFunction<K> extractor, double v, @Nonnull LDblIntPredicate.LIntDblPred predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.testIntDbl(extractor.applyAsInt(k), v);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K> LPredicate<K> haveInt$Dbl_(@Nonnull LToIntFunction<K> extractor, @Nonnull LDblIntPredicate.LIntDblPred predicate, double v) {
		return haveInt$Dbl_(extractor, v, predicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K> LPredicate<K> hasInt$Dbl_(@Nonnull LToIntFunction<K> extractor, double v, @Nonnull LDblIntPredicate.LIntDblPred predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.testIntDbl(extractor.applyAsInt(k), v);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K> LPredicate<K> hasInt$Dbl_(@Nonnull LToIntFunction<K> extractor, @Nonnull LDblIntPredicate.LIntDblPred predicate, double v) {
		return hasInt$Dbl_(extractor, v, predicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K> LPredicate<K> haveInt$Char_(@Nonnull LToIntFunction<K> extractor, char v, @Nonnull LCharIntPredicate.LIntCharPred predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.testIntChar(extractor.applyAsInt(k), v);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K> LPredicate<K> haveInt$Char_(@Nonnull LToIntFunction<K> extractor, @Nonnull LCharIntPredicate.LIntCharPred predicate, char v) {
		return haveInt$Char_(extractor, v, predicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K> LPredicate<K> hasInt$Char_(@Nonnull LToIntFunction<K> extractor, char v, @Nonnull LCharIntPredicate.LIntCharPred predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.testIntChar(extractor.applyAsInt(k), v);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K> LPredicate<K> hasInt$Char_(@Nonnull LToIntFunction<K> extractor, @Nonnull LCharIntPredicate.LIntCharPred predicate, char v) {
		return hasInt$Char_(extractor, v, predicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K> LPredicate<K> haveInt$Srt_(@Nonnull LToIntFunction<K> extractor, short v, @Nonnull LSrtIntPredicate.LIntSrtPred predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.testIntSrt(extractor.applyAsInt(k), v);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K> LPredicate<K> haveInt$Srt_(@Nonnull LToIntFunction<K> extractor, @Nonnull LSrtIntPredicate.LIntSrtPred predicate, short v) {
		return haveInt$Srt_(extractor, v, predicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K> LPredicate<K> hasInt$Srt_(@Nonnull LToIntFunction<K> extractor, short v, @Nonnull LSrtIntPredicate.LIntSrtPred predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.testIntSrt(extractor.applyAsInt(k), v);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K> LPredicate<K> hasInt$Srt_(@Nonnull LToIntFunction<K> extractor, @Nonnull LSrtIntPredicate.LIntSrtPred predicate, short v) {
		return hasInt$Srt_(extractor, v, predicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K> LPredicate<K> haveInt$Flt_(@Nonnull LToIntFunction<K> extractor, float v, @Nonnull LFltIntPredicate.LIntFltPred predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.testIntFlt(extractor.applyAsInt(k), v);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K> LPredicate<K> haveInt$Flt_(@Nonnull LToIntFunction<K> extractor, @Nonnull LFltIntPredicate.LIntFltPred predicate, float v) {
		return haveInt$Flt_(extractor, v, predicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K> LPredicate<K> hasInt$Flt_(@Nonnull LToIntFunction<K> extractor, float v, @Nonnull LFltIntPredicate.LIntFltPred predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.testIntFlt(extractor.applyAsInt(k), v);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K> LPredicate<K> hasInt$Flt_(@Nonnull LToIntFunction<K> extractor, @Nonnull LFltIntPredicate.LIntFltPred predicate, float v) {
		return hasInt$Flt_(extractor, v, predicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K> LPredicate<K> haveInt$Long_(@Nonnull LToIntFunction<K> extractor, long v, @Nonnull LLongIntPredicate.LIntLongPred predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.testIntLong(extractor.applyAsInt(k), v);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K> LPredicate<K> haveInt$Long_(@Nonnull LToIntFunction<K> extractor, @Nonnull LLongIntPredicate.LIntLongPred predicate, long v) {
		return haveInt$Long_(extractor, v, predicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K> LPredicate<K> hasInt$Long_(@Nonnull LToIntFunction<K> extractor, long v, @Nonnull LLongIntPredicate.LIntLongPred predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.testIntLong(extractor.applyAsInt(k), v);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K> LPredicate<K> hasInt$Long_(@Nonnull LToIntFunction<K> extractor, @Nonnull LLongIntPredicate.LIntLongPred predicate, long v) {
		return hasInt$Long_(extractor, v, predicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, V> LPredicate<K> haveInt(@Nonnull LToIntFunction<K> extractor, V v, @Nonnull LObjIntPredicate.LIntObjPred<? super V> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.testIntObj(extractor.applyAsInt(k), v);
	}

	/** "Special" variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, V> LFunction<K, String> haveInt$(@Nonnull LToIntFunction<K> extractor, V v, @Nonnull LOiFunction.LIntObjFunc<? super V, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.applyIntObj(extractor.applyAsInt(k), v);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, V> LPredicate<K> haveInt(@Nonnull LToIntFunction<K> extractor, @Nonnull LObjIntPredicate.LIntObjPred<? super V> predicate, V v) {
		return haveInt(extractor, v, predicate);
	}

	/** "Special" variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, V> LFunction<K, String> haveInt$(@Nonnull LToIntFunction<K> extractor, @Nonnull LOiFunction.LIntObjFunc<? super V, String> specialPredicate, V v) {
		return haveInt$(extractor, v, specialPredicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, V> LPredicate<K> hasInt(@Nonnull LToIntFunction<K> extractor, V v, @Nonnull LObjIntPredicate.LIntObjPred<? super V> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.testIntObj(extractor.applyAsInt(k), v);
	}

	/** "Special" variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, V> LFunction<K, String> hasInt$(@Nonnull LToIntFunction<K> extractor, V v, @Nonnull LOiFunction.LIntObjFunc<? super V, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.applyIntObj(extractor.applyAsInt(k), v);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, V> LPredicate<K> hasInt(@Nonnull LToIntFunction<K> extractor, @Nonnull LObjIntPredicate.LIntObjPred<? super V> predicate, V v) {
		return hasInt(extractor, v, predicate);
	}

	/** "Special" variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, V> LFunction<K, String> hasInt$(@Nonnull LToIntFunction<K> extractor, @Nonnull LOiFunction.LIntObjFunc<? super V, String> specialPredicate, V v) {
		return hasInt$(extractor, v, specialPredicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, V1> LPredicate<K> haveInt$WithInt(@Nonnull LToIntFunction<K> extractor, V1 with1, @Nonnull LObjIntPredicate<? super V1> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(with1, extractor.applyAsInt(k));
	}

	/** "Special" variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, V1> LFunction<K, String> haveInt$WithInt$(@Nonnull LToIntFunction<K> extractor, V1 with1, @Nonnull LOiFunction<? super V1, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(with1, extractor.applyAsInt(k));
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, V1> LPredicate<K> haveInt$WithInt(@Nonnull LToIntFunction<K> extractor, @Nonnull LObjIntPredicate<? super V1> predicate, V1 with1) {
		return haveInt$WithInt(extractor, with1, predicate);
	}

	/** "Special" variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, V1> LFunction<K, String> haveInt$WithInt$(@Nonnull LToIntFunction<K> extractor, @Nonnull LOiFunction<? super V1, String> specialPredicate, V1 with1) {
		return haveInt$WithInt$(extractor, with1, specialPredicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, V1> LPredicate<K> hasInt$WithInt(@Nonnull LToIntFunction<K> extractor, V1 with1, @Nonnull LObjIntPredicate<? super V1> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(with1, extractor.applyAsInt(k));
	}

	/** "Special" variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, V1> LFunction<K, String> hasInt$WithInt$(@Nonnull LToIntFunction<K> extractor, V1 with1, @Nonnull LOiFunction<? super V1, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(with1, extractor.applyAsInt(k));
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, V1> LPredicate<K> hasInt$WithInt(@Nonnull LToIntFunction<K> extractor, @Nonnull LObjIntPredicate<? super V1> predicate, V1 with1) {
		return hasInt$WithInt(extractor, with1, predicate);
	}

	/** "Special" variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, V1> LFunction<K, String> hasInt$WithInt$(@Nonnull LToIntFunction<K> extractor, @Nonnull LOiFunction<? super V1, String> specialPredicate, V1 with1) {
		return hasInt$WithInt$(extractor, with1, specialPredicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, V1, V2> LPredicate<K> haveInt$With(@Nonnull LToIntFunction<K> extractor, V1 with1, V2 with2, @Nonnull LBiObjIntPredicate<? super V1, ? super V2> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(with1, with2, extractor.applyAsInt(k));
	}

	/** "Special" variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, V1, V2> LFunction<K, String> haveInt$With$(@Nonnull LToIntFunction<K> extractor, V1 with1, V2 with2, @Nonnull LBiObjIntFunction<? super V1, ? super V2, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(with1, with2, extractor.applyAsInt(k));
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, V1, V2> LPredicate<K> haveInt$With(@Nonnull LToIntFunction<K> extractor, @Nonnull LBiObjIntPredicate<? super V1, ? super V2> predicate, V1 with1, V2 with2) {
		return haveInt$With(extractor, with1, with2, predicate);
	}

	/** "Special" variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, V1, V2> LFunction<K, String> haveInt$With$(@Nonnull LToIntFunction<K> extractor, @Nonnull LBiObjIntFunction<? super V1, ? super V2, String> specialPredicate, V1 with1, V2 with2) {
		return haveInt$With$(extractor, with1, with2, specialPredicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, V1, V2> LPredicate<K> hasInt$With(@Nonnull LToIntFunction<K> extractor, V1 with1, V2 with2, @Nonnull LBiObjIntPredicate<? super V1, ? super V2> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(with1, with2, extractor.applyAsInt(k));
	}

	/** "Special" variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, V1, V2> LFunction<K, String> hasInt$With$(@Nonnull LToIntFunction<K> extractor, V1 with1, V2 with2, @Nonnull LBiObjIntFunction<? super V1, ? super V2, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(with1, with2, extractor.applyAsInt(k));
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, V1, V2> LPredicate<K> hasInt$With(@Nonnull LToIntFunction<K> extractor, @Nonnull LBiObjIntPredicate<? super V1, ? super V2> predicate, V1 with1, V2 with2) {
		return hasInt$With(extractor, with1, with2, predicate);
	}

	/** "Special" variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, V1, V2> LFunction<K, String> hasInt$With$(@Nonnull LToIntFunction<K> extractor, @Nonnull LBiObjIntFunction<? super V1, ? super V2, String> specialPredicate, V1 with1, V2 with2) {
		return hasInt$With$(extractor, with1, with2, specialPredicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K> LPredicate<K> haveLong(@Nonnull LToLongFunction<K> extractor, @Nonnull LLongPredicate predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.applyAsLong(k));
	}

	/** "Special" variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K> LFunction<K, String> haveLong$(@Nonnull LToLongFunction<K> extractor, @Nonnull LLongFunction<String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(extractor.applyAsLong(k));
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K> LPredicate<K> hasLong(@Nonnull LToLongFunction<K> extractor, @Nonnull LLongPredicate predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.applyAsLong(k));
	}

	/** "Special" variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K> LFunction<K, String> hasLong$(@Nonnull LToLongFunction<K> extractor, @Nonnull LLongFunction<String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(extractor.applyAsLong(k));
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K> LPredicate<K> haveLong(@Nonnull LToLongFunction<K> extractor, long a2, @Nonnull LBiLongPredicate predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.applyAsLong(k), a2);
	}

	/** "Special" variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K> LFunction<K, String> haveLong$(@Nonnull LToLongFunction<K> extractor, long a2, @Nonnull LBiLongFunction<String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(extractor.applyAsLong(k), a2);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K> LPredicate<K> haveLong(@Nonnull LToLongFunction<K> extractor, @Nonnull LBiLongPredicate predicate, long a2) {
		return haveLong(extractor, a2, predicate);
	}

	/** "Special" variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K> LFunction<K, String> haveLong$(@Nonnull LToLongFunction<K> extractor, @Nonnull LBiLongFunction<String> specialPredicate, long a2) {
		return haveLong$(extractor, a2, specialPredicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K> LPredicate<K> hasLong(@Nonnull LToLongFunction<K> extractor, long a2, @Nonnull LBiLongPredicate predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.applyAsLong(k), a2);
	}

	/** "Special" variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K> LFunction<K, String> hasLong$(@Nonnull LToLongFunction<K> extractor, long a2, @Nonnull LBiLongFunction<String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(extractor.applyAsLong(k), a2);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K> LPredicate<K> hasLong(@Nonnull LToLongFunction<K> extractor, @Nonnull LBiLongPredicate predicate, long a2) {
		return hasLong(extractor, a2, predicate);
	}

	/** "Special" variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K> LFunction<K, String> hasLong$(@Nonnull LToLongFunction<K> extractor, @Nonnull LBiLongFunction<String> specialPredicate, long a2) {
		return hasLong$(extractor, a2, specialPredicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K> LPredicate<K> haveLong(@Nonnull LToLongFunction<K> extractor, long a2, long a3, @Nonnull LTriLongPredicate predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.applyAsLong(k), a2, a3);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K> LPredicate<K> haveLong(@Nonnull LToLongFunction<K> extractor, @Nonnull LTriLongPredicate predicate, long a2, long a3) {
		return haveLong(extractor, a2, a3, predicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K> LPredicate<K> hasLong(@Nonnull LToLongFunction<K> extractor, long a2, long a3, @Nonnull LTriLongPredicate predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.applyAsLong(k), a2, a3);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K> LPredicate<K> hasLong(@Nonnull LToLongFunction<K> extractor, @Nonnull LTriLongPredicate predicate, long a2, long a3) {
		return hasLong(extractor, a2, a3, predicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K> LPredicate<K> haveLong$Int(@Nonnull LToLongFunction<K> extractor, int v, @Nonnull LLongIntPredicate predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.applyAsLong(k), v);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K> LPredicate<K> haveLong$Int(@Nonnull LToLongFunction<K> extractor, @Nonnull LLongIntPredicate predicate, int v) {
		return haveLong$Int(extractor, v, predicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K> LPredicate<K> hasLong$Int(@Nonnull LToLongFunction<K> extractor, int v, @Nonnull LLongIntPredicate predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.applyAsLong(k), v);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K> LPredicate<K> hasLong$Int(@Nonnull LToLongFunction<K> extractor, @Nonnull LLongIntPredicate predicate, int v) {
		return hasLong$Int(extractor, v, predicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, V> LPredicate<K> haveLong(@Nonnull LToLongFunction<K> extractor, V v, @Nonnull LObjLongPredicate.LLongObjPred<? super V> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.testLongObj(extractor.applyAsLong(k), v);
	}

	/** "Special" variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, V> LFunction<K, String> haveLong$(@Nonnull LToLongFunction<K> extractor, V v, @Nonnull LObjLongFunction.LLongObjFunc<? super V, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.applyLongObj(extractor.applyAsLong(k), v);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, V> LPredicate<K> haveLong(@Nonnull LToLongFunction<K> extractor, @Nonnull LObjLongPredicate.LLongObjPred<? super V> predicate, V v) {
		return haveLong(extractor, v, predicate);
	}

	/** "Special" variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, V> LFunction<K, String> haveLong$(@Nonnull LToLongFunction<K> extractor, @Nonnull LObjLongFunction.LLongObjFunc<? super V, String> specialPredicate, V v) {
		return haveLong$(extractor, v, specialPredicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, V> LPredicate<K> hasLong(@Nonnull LToLongFunction<K> extractor, V v, @Nonnull LObjLongPredicate.LLongObjPred<? super V> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.testLongObj(extractor.applyAsLong(k), v);
	}

	/** "Special" variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, V> LFunction<K, String> hasLong$(@Nonnull LToLongFunction<K> extractor, V v, @Nonnull LObjLongFunction.LLongObjFunc<? super V, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.applyLongObj(extractor.applyAsLong(k), v);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, V> LPredicate<K> hasLong(@Nonnull LToLongFunction<K> extractor, @Nonnull LObjLongPredicate.LLongObjPred<? super V> predicate, V v) {
		return hasLong(extractor, v, predicate);
	}

	/** "Special" variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, V> LFunction<K, String> hasLong$(@Nonnull LToLongFunction<K> extractor, @Nonnull LObjLongFunction.LLongObjFunc<? super V, String> specialPredicate, V v) {
		return hasLong$(extractor, v, specialPredicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, V1> LPredicate<K> haveLong$WithLong(@Nonnull LToLongFunction<K> extractor, V1 with1, @Nonnull LObjLongPredicate<? super V1> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(with1, extractor.applyAsLong(k));
	}

	/** "Special" variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, V1> LFunction<K, String> haveLong$WithLong$(@Nonnull LToLongFunction<K> extractor, V1 with1, @Nonnull LObjLongFunction<? super V1, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(with1, extractor.applyAsLong(k));
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, V1> LPredicate<K> haveLong$WithLong(@Nonnull LToLongFunction<K> extractor, @Nonnull LObjLongPredicate<? super V1> predicate, V1 with1) {
		return haveLong$WithLong(extractor, with1, predicate);
	}

	/** "Special" variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, V1> LFunction<K, String> haveLong$WithLong$(@Nonnull LToLongFunction<K> extractor, @Nonnull LObjLongFunction<? super V1, String> specialPredicate, V1 with1) {
		return haveLong$WithLong$(extractor, with1, specialPredicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, V1> LPredicate<K> hasLong$WithLong(@Nonnull LToLongFunction<K> extractor, V1 with1, @Nonnull LObjLongPredicate<? super V1> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(with1, extractor.applyAsLong(k));
	}

	/** "Special" variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, V1> LFunction<K, String> hasLong$WithLong$(@Nonnull LToLongFunction<K> extractor, V1 with1, @Nonnull LObjLongFunction<? super V1, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(with1, extractor.applyAsLong(k));
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, V1> LPredicate<K> hasLong$WithLong(@Nonnull LToLongFunction<K> extractor, @Nonnull LObjLongPredicate<? super V1> predicate, V1 with1) {
		return hasLong$WithLong(extractor, with1, predicate);
	}

	/** "Special" variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, V1> LFunction<K, String> hasLong$WithLong$(@Nonnull LToLongFunction<K> extractor, @Nonnull LObjLongFunction<? super V1, String> specialPredicate, V1 with1) {
		return hasLong$WithLong$(extractor, with1, specialPredicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, V1, V2> LPredicate<K> haveLong$With(@Nonnull LToLongFunction<K> extractor, V1 with1, V2 with2, @Nonnull LBiObjLongPredicate<? super V1, ? super V2> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(with1, with2, extractor.applyAsLong(k));
	}

	/** "Special" variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, V1, V2> LFunction<K, String> haveLong$With$(@Nonnull LToLongFunction<K> extractor, V1 with1, V2 with2, @Nonnull LBiObjLongFunction<? super V1, ? super V2, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(with1, with2, extractor.applyAsLong(k));
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, V1, V2> LPredicate<K> haveLong$With(@Nonnull LToLongFunction<K> extractor, @Nonnull LBiObjLongPredicate<? super V1, ? super V2> predicate, V1 with1, V2 with2) {
		return haveLong$With(extractor, with1, with2, predicate);
	}

	/** "Special" variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, V1, V2> LFunction<K, String> haveLong$With$(@Nonnull LToLongFunction<K> extractor, @Nonnull LBiObjLongFunction<? super V1, ? super V2, String> specialPredicate, V1 with1, V2 with2) {
		return haveLong$With$(extractor, with1, with2, specialPredicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, V1, V2> LPredicate<K> hasLong$With(@Nonnull LToLongFunction<K> extractor, V1 with1, V2 with2, @Nonnull LBiObjLongPredicate<? super V1, ? super V2> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(with1, with2, extractor.applyAsLong(k));
	}

	/** "Special" variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, V1, V2> LFunction<K, String> hasLong$With$(@Nonnull LToLongFunction<K> extractor, V1 with1, V2 with2, @Nonnull LBiObjLongFunction<? super V1, ? super V2, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(with1, with2, extractor.applyAsLong(k));
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, V1, V2> LPredicate<K> hasLong$With(@Nonnull LToLongFunction<K> extractor, @Nonnull LBiObjLongPredicate<? super V1, ? super V2> predicate, V1 with1, V2 with2) {
		return hasLong$With(extractor, with1, with2, predicate);
	}

	/** "Special" variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, V1, V2> LFunction<K, String> hasLong$With$(@Nonnull LToLongFunction<K> extractor, @Nonnull LBiObjLongFunction<? super V1, ? super V2, String> specialPredicate, V1 with1, V2 with2) {
		return hasLong$With$(extractor, with1, with2, specialPredicate);
	}

	// </editor-fold>

}
