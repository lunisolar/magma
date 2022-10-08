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
import java.util.concurrent.*; // NOSONAR
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
 *  *
 * @see {@link Is}{@link Are}{@link Do}{@link Does}{@link Be}{@link Has}{@link Have}{@link As}{@link P}{@link Predicates}{@link P1}{@link P2}
 */
@MethodReferences
public class Predicates implements FluentSyntax {

	public static class Is extends Predicates {
	}
	public static class Are extends Predicates {
	}
	public static class Do extends Predicates {
	}
	public static class Does extends Predicates {
	}
	public static class Be extends Predicates {
	}
	public static class Has extends Predicates {
	}
	public static class Have extends Predicates {
	}
	public static class As extends Predicates {
	}

	// <editor-fold desc="Object">

	/** Predicate: Object <%s> must be the same as <%s>.*/
	public static boolean same(@Nullable Object n, @Nullable Object other) {
		return n == other;
	}

	/** "Special" predicate: Object <%s> must be the same as <%s>. */
	public static @Nullable String same$(@Nullable Object n, @Nullable Object other) {
		return same(n, other) ? null : String.format("Object <%s> must be the same as <%s>.", n, other);
	}
	/** Predicate: Object <%s> must NOT be the same as <%s>..*/
	public static boolean notSame(@Nullable Object n, @Nullable Object other) {
		return !same(n, other);
	}

	/** "Special" predicate: Object <%s> must NOT be the same as <%s>. */
	public static @Nullable String notSame$(@Nullable Object n, @Nullable Object other) {
		return notSame(n, other) ? null : String.format("Object <%s> must NOT be the same as <%s>.", n, other);
	}

	/** Predicate: Reference must be null, currently is pointing to <%s>.*/
	public static boolean Null(@Nullable Object n) {
		return n == null;
	}

	/** "Special" predicate: Reference must be null, currently is pointing to <%s>. */
	public static @Nullable String Null$(@Nullable Object n) {
		return Null(n) ? null : String.format("Reference must be null, currently is pointing to <%s>.", n);
	}
	/** Predicate: Reference must NOT be null, currently is pointing to <%s>..*/
	public static boolean notNull(@Nullable Object n) {
		return !Null(n);
	}

	/** "Special" predicate: Reference must NOT be null, currently is pointing to <%s>. */
	public static @Nullable String notNull$(@Nullable Object n) {
		return notNull(n) ? null : String.format("Reference must NOT be null, currently is pointing to <%s>.", n);
	}

	/** Predicate: All references must be null.*/
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

	/** "Special" predicate: All references must be null. */
	public static @Nullable String allNull$(@Nullable Object... objects) {
		return allNull(objects) ? null : String.format("All references must be null.");
	}
	/** Predicate: All references must be NOT null..*/
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

	/** "Special" predicate: All references must be NOT null. */
	public static @Nullable String noneNull$(@Nullable Object... objects) {
		return noneNull(objects) ? null : String.format("All references must be NOT null.");
	}

	/** Predicate: At least one references must be null.*/
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

	/** "Special" predicate: At least one references must be null. */
	public static @Nullable String anyNull$(@Nullable Object... objects) {
		return anyNull(objects) ? null : String.format("At least one references must be null.");
	}
	/** Predicate: At least one references must be NOT null..*/
	public static boolean anyNotNull(@Nullable Object... objects) {
		if (objects == null) {
			return false;
		}
		for (Object o : objects) {
			if (o != null) {
				return true;
			}
		}

		return false;
	}

	/** "Special" predicate: At least one references must be NOT null. */
	public static @Nullable String anyNotNull$(@Nullable Object... objects) {
		return anyNotNull(objects) ? null : String.format("At least one references must be NOT null.");
	}

	// </editor-fold>

	// <editor-fold desc="`String`">

	/** Predicate: String <'%s'> must be <%d> characters long.*/
	public static boolean length(@Nonnull CharSequence s, int size) {
		Null.nonNullArg(s, "s");
		return size == s.length();
	}

	/** "Special" predicate: String <'%s'> must be <%d> characters long. */
	public static @Nullable String length$(@Nonnull CharSequence s, int size) {
		Null.nonNullArg(s, "s");
		return length(s, size) ? null : String.format("String <'%s'> must be <%d> characters long.", s, size);
	}
	/** Predicate: String <'%s'> must NOT be <%d> characters long..*/
	public static boolean notLength(@Nonnull CharSequence s, int size) {
		Null.nonNullArg(s, "s");
		return !length(s, size);
	}

	/** "Special" predicate: String <'%s'> must NOT be <%d> characters long. */
	public static @Nullable String notLength$(@Nonnull CharSequence s, int size) {
		Null.nonNullArg(s, "s");
		return notLength(s, size) ? null : String.format("String <'%s'> must NOT be <%d> characters long.", s, size);
	}

	/** Predicate: String <'%s'> must be <%d> characters long.*/
	public static boolean ofLength(@Nonnull CharSequence s, int size) {
		Null.nonNullArg(s, "s");
		return size == s.length();
	}

	/** "Special" predicate: String <'%s'> must be <%d> characters long. */
	public static @Nullable String ofLength$(@Nonnull CharSequence s, int size) {
		Null.nonNullArg(s, "s");
		return ofLength(s, size) ? null : String.format("String <'%s'> must be <%d> characters long.", s, size);
	}
	/** Predicate: String <'%s'> must NOT be <%d> characters long..*/
	public static boolean notOfLength(@Nonnull CharSequence s, int size) {
		Null.nonNullArg(s, "s");
		return !ofLength(s, size);
	}

	/** "Special" predicate: String <'%s'> must NOT be <%d> characters long. */
	public static @Nullable String notOfLength$(@Nonnull CharSequence s, int size) {
		Null.nonNullArg(s, "s");
		return notOfLength(s, size) ? null : String.format("String <'%s'> must NOT be <%d> characters long.", s, size);
	}

	/** Predicate: String <'%s'> must start with <'%s'>.*/
	public static boolean startWith(@Nonnull String n, @Nonnull String a1) {
		Null.nonNullArg(n, "n");
		Null.nonNullArg(a1, "a1");
		return n.startsWith(a1);
	}

	/** "Special" predicate: String <'%s'> must start with <'%s'>. */
	public static @Nullable String startWith$(@Nonnull String n, @Nonnull String a1) {
		Null.nonNullArg(n, "n");
		Null.nonNullArg(a1, "a1");
		return startWith(n, a1) ? null : String.format("String <'%s'> must start with <'%s'>.", n, a1);
	}
	/** Predicate: String <'%s'> must NOT start with <'%s'>..*/
	public static boolean notStartWith(@Nonnull String n, @Nonnull String a1) {
		Null.nonNullArg(n, "n");
		Null.nonNullArg(a1, "a1");
		return !startWith(n, a1);
	}

	/** "Special" predicate: String <'%s'> must NOT start with <'%s'>. */
	public static @Nullable String notStartWith$(@Nonnull String n, @Nonnull String a1) {
		Null.nonNullArg(n, "n");
		Null.nonNullArg(a1, "a1");
		return notStartWith(n, a1) ? null : String.format("String <'%s'> must NOT start with <'%s'>.", n, a1);
	}

	/** Predicate: String <'%s'> must end with <'%s'>.*/
	public static boolean endWith(@Nonnull String n, @Nonnull String a1) {
		Null.nonNullArg(n, "n");
		Null.nonNullArg(a1, "a1");
		return n.endsWith(a1);
	}

	/** "Special" predicate: String <'%s'> must end with <'%s'>. */
	public static @Nullable String endWith$(@Nonnull String n, @Nonnull String a1) {
		Null.nonNullArg(n, "n");
		Null.nonNullArg(a1, "a1");
		return endWith(n, a1) ? null : String.format("String <'%s'> must end with <'%s'>.", n, a1);
	}
	/** Predicate: String <'%s'> must NOT end with <'%s'>..*/
	public static boolean notEndWith(@Nonnull String n, @Nonnull String a1) {
		Null.nonNullArg(n, "n");
		Null.nonNullArg(a1, "a1");
		return !endWith(n, a1);
	}

	/** "Special" predicate: String <'%s'> must NOT end with <'%s'>. */
	public static @Nullable String notEndWith$(@Nonnull String n, @Nonnull String a1) {
		Null.nonNullArg(n, "n");
		Null.nonNullArg(a1, "a1");
		return notEndWith(n, a1) ? null : String.format("String <'%s'> must NOT end with <'%s'>.", n, a1);
	}

	/** Predicate: String <'%s'> must contain string <'%s'>. But does not.*/
	public static boolean contain(@Nonnull String n, @Nonnull String a1) {
		Null.nonNullArg(n, "n");
		Null.nonNullArg(a1, "a1");
		return n.contains(a1);
	}

	/** "Special" predicate: String <'%s'> must contain string <'%s'>. But does not. */
	public static @Nullable String contain$(@Nonnull String n, @Nonnull String a1) {
		Null.nonNullArg(n, "n");
		Null.nonNullArg(a1, "a1");
		return contain(n, a1) ? null : String.format("String <'%s'> must contain string <'%s'>. But does not.", n, a1);
	}
	/** Predicate: String <'%s'> must NOT contain string <'%s'>. But does not..*/
	public static boolean notContain(@Nonnull String n, @Nonnull String a1) {
		Null.nonNullArg(n, "n");
		Null.nonNullArg(a1, "a1");
		return !contain(n, a1);
	}

	/** "Special" predicate: String <'%s'> must NOT contain string <'%s'>. But does not. */
	public static @Nullable String notContain$(@Nonnull String n, @Nonnull String a1) {
		Null.nonNullArg(n, "n");
		Null.nonNullArg(a1, "a1");
		return notContain(n, a1) ? null : String.format("String <'%s'> must NOT contain string <'%s'>. But does not.", n, a1);
	}

	/** Predicate: String <'%s'> must be empty.*/
	public static boolean empty(@Nonnull String n) {
		Null.nonNullArg(n, "n");
		return n.isEmpty();
	}

	/** "Special" predicate: String <'%s'> must be empty. */
	public static @Nullable String empty$(@Nonnull String n) {
		Null.nonNullArg(n, "n");
		return empty(n) ? null : String.format("String <'%s'> must be empty.", n);
	}
	/** Predicate: String <'%s'> must NOT be empty..*/
	public static boolean notEmpty(@Nonnull String n) {
		Null.nonNullArg(n, "n");
		return !empty(n);
	}

	/** "Special" predicate: String <'%s'> must NOT be empty. */
	public static @Nullable String notEmpty$(@Nonnull String n) {
		Null.nonNullArg(n, "n");
		return notEmpty(n) ? null : String.format("String <'%s'> must NOT be empty.", n);
	}

	/** Predicate: String <'%s'> must be blank (empty or consisting of only white characters).*/
	public static boolean blank(@Nonnull String n) {
		Null.nonNullArg(n, "n");
		return n.isBlank();
	}

	/** "Special" predicate: String <'%s'> must be blank (empty or consisting of only white characters). */
	public static @Nullable String blank$(@Nonnull String n) {
		Null.nonNullArg(n, "n");
		return blank(n) ? null : String.format("String <'%s'> must be blank (empty or consisting of only white characters).", n);
	}
	/** Predicate: String <'%s'> must NOT be blank (empty or consisting of only white characters)..*/
	public static boolean notBlank(@Nonnull String n) {
		Null.nonNullArg(n, "n");
		return !blank(n);
	}

	/** "Special" predicate: String <'%s'> must NOT be blank (empty or consisting of only white characters). */
	public static @Nullable String notBlank$(@Nonnull String n) {
		Null.nonNullArg(n, "n");
		return notBlank(n) ? null : String.format("String <'%s'> must NOT be blank (empty or consisting of only white characters).", n);
	}

	/** Predicate: String <'%s'> must be null or empty.*/
	public static boolean nullOrEmpty(@Nonnull String n) {
		return n == null || n.isEmpty();
	}

	/** "Special" predicate: String <'%s'> must be null or empty. */
	public static @Nullable String nullOrEmpty$(@Nonnull String n) {
		return nullOrEmpty(n) ? null : String.format("String <'%s'> must be null or empty.", n);
	}
	/** Predicate: String <'%s'> must NOT be null or empty..*/
	public static boolean notNullNorEmpty(@Nonnull String n) {
		return !nullOrEmpty(n);
	}

	/** "Special" predicate: String <'%s'> must NOT be null or empty. */
	public static @Nullable String notNullNorEmpty$(@Nonnull String n) {
		return notNullNorEmpty(n) ? null : String.format("String <'%s'> must NOT be null or empty.", n);
	}

	/** Predicate: String <'%s'> must be null or blank.*/
	public static boolean nullOrBlank(@Nonnull String n) {
		return n == null || n.isBlank();
	}

	/** "Special" predicate: String <'%s'> must be null or blank. */
	public static @Nullable String nullOrBlank$(@Nonnull String n) {
		return nullOrBlank(n) ? null : String.format("String <'%s'> must be null or blank.", n);
	}
	/** Predicate: String <'%s'> must NOT be null or blank..*/
	public static boolean notNullNorBlank(@Nonnull String n) {
		return !nullOrBlank(n);
	}

	/** "Special" predicate: String <'%s'> must NOT be null or blank. */
	public static @Nullable String notNullNorBlank$(@Nonnull String n) {
		return notNullNorBlank(n) ? null : String.format("String <'%s'> must NOT be null or blank.", n);
	}

	// </editor-fold>

	// <editor-fold desc="and/or/xor">

	/** Predicate: AND*/
	public static boolean and(boolean op1, boolean op2) {
		return op1 && op2;
	}

	/** "Special" predicate: AND */
	public static @Nullable String and$(boolean op1, boolean op2) {
		return and(op1, op2) ? null : String.format("AND", op1, op2);
	}

	/** Predicate: OR*/
	public static boolean or(boolean op1, boolean op2) {
		return op1 || op2;
	}

	/** "Special" predicate: OR */
	public static @Nullable String or$(boolean op1, boolean op2) {
		return or(op1, op2) ? null : String.format("OR", op1, op2);
	}

	/** Predicate: XOR*/
	public static boolean xor(boolean op1, boolean op2) {
		return op1 ^ op2;
	}

	/** "Special" predicate: XOR */
	public static @Nullable String xor$(boolean op1, boolean op2) {
		return xor(op1, op2) ? null : String.format("XOR", op1, op2);
	}

	// </editor-fold>

	// <editor-fold desc="== <= >= < >">

	/** Predicate: <%s> must be equal to <%s>.*/
	public static boolean equal(Object o1, Object o2) {
		return Objects.equals(o1, o2);
	}

	/** "Special" predicate: <%s> must be equal to <%s>. */
	public static @Nullable String equal$(Object o1, Object o2) {
		return equal(o1, o2) ? null : String.format("<%s> must be equal to <%s>.", o1, o2);
	}
	/** Predicate: <%s> must NOT be equal to <%s>..*/
	public static boolean notEqual(Object o1, Object o2) {
		return !equal(o1, o2);
	}

	/** "Special" predicate: <%s> must NOT be equal to <%s>. */
	public static @Nullable String notEqual$(Object o1, Object o2) {
		return notEqual(o1, o2) ? null : String.format("<%s> must NOT be equal to <%s>.", o1, o2);
	}

	/** Predicate: <%s> must be equal to <%s>.*/
	public static boolean objEqual(Object o1, Object o2) {
		return Objects.equals(o1, o2);
	}

	/** "Special" predicate: <%s> must be equal to <%s>. */
	public static @Nullable String objEqual$(Object o1, Object o2) {
		return objEqual(o1, o2) ? null : String.format("<%s> must be equal to <%s>.", o1, o2);
	}
	/** Predicate: <%s> must NOT be equal to <%s>..*/
	public static boolean objNotEqual(Object o1, Object o2) {
		return !objEqual(o1, o2);
	}

	/** "Special" predicate: <%s> must NOT be equal to <%s>. */
	public static @Nullable String objNotEqual$(Object o1, Object o2) {
		return objNotEqual(o1, o2) ? null : String.format("<%s> must NOT be equal to <%s>.", o1, o2);
	}

	/** Predicate: <%s> must be equal to <%s>.*/
	public static boolean equalToObj(byte n, Object o2) {
		return Objects.equals(n, o2);
	}

	/** "Special" predicate: <%s> must be equal to <%s>. */
	public static @Nullable String equalToObj$(byte n, Object o2) {
		return equalToObj(n, o2) ? null : String.format("<%s> must be equal to <%s>.", n, o2);
	}
	/** Predicate: <%s> must NOT be equal to <%s>..*/
	public static boolean notEqualToObj(byte n, Object o2) {
		return !equalToObj(n, o2);
	}

	/** "Special" predicate: <%s> must NOT be equal to <%s>. */
	public static @Nullable String notEqualToObj$(byte n, Object o2) {
		return notEqualToObj(n, o2) ? null : String.format("<%s> must NOT be equal to <%s>.", n, o2);
	}

	/** Predicate: %s must be equal to %s.*/
	public static boolean equal(byte n, byte a1) {
		return n == a1;
	}

	/** "Special" predicate: %s must be equal to %s. */
	public static @Nullable String equal$(byte n, byte a1) {
		return equal(n, a1) ? null : String.format("%s must be equal to %s.", n, a1);
	}
	/** Predicate: %s must NOT be equal to %s..*/
	public static boolean notEqual(byte n, byte a1) {
		return !equal(n, a1);
	}

	/** "Special" predicate: %s must NOT be equal to %s. */
	public static @Nullable String notEqual$(byte n, byte a1) {
		return notEqual(n, a1) ? null : String.format("%s must NOT be equal to %s.", n, a1);
	}

	/** Predicate: %s must be > %s.*/
	public static boolean gt(byte n, byte a1) {
		return n > a1;
	}

	/** "Special" predicate: %s must be > %s. */
	public static @Nullable String gt$(byte n, byte a1) {
		return gt(n, a1) ? null : String.format("%s must be > %s.", n, a1);
	}
	/** Predicate: %s must NOT be > %s..*/
	public static boolean notGt(byte n, byte a1) {
		return !gt(n, a1);
	}

	/** "Special" predicate: %s must NOT be > %s. */
	public static @Nullable String notGt$(byte n, byte a1) {
		return notGt(n, a1) ? null : String.format("%s must NOT be > %s.", n, a1);
	}

	/** Predicate: %s must be < %s.*/
	public static boolean lt(byte n, byte a1) {
		return n < a1;
	}

	/** "Special" predicate: %s must be < %s. */
	public static @Nullable String lt$(byte n, byte a1) {
		return lt(n, a1) ? null : String.format("%s must be < %s.", n, a1);
	}
	/** Predicate: %s must NOT be < %s..*/
	public static boolean notLt(byte n, byte a1) {
		return !lt(n, a1);
	}

	/** "Special" predicate: %s must NOT be < %s. */
	public static @Nullable String notLt$(byte n, byte a1) {
		return notLt(n, a1) ? null : String.format("%s must NOT be < %s.", n, a1);
	}

	/** Predicate: %s must be >= %s.*/
	public static boolean gtEq(byte n, byte a1) {
		return n >= a1;
	}

	/** "Special" predicate: %s must be >= %s. */
	public static @Nullable String gtEq$(byte n, byte a1) {
		return gtEq(n, a1) ? null : String.format("%s must be >= %s.", n, a1);
	}
	/** Predicate: %s must NOT be >= %s..*/
	public static boolean notGtEq(byte n, byte a1) {
		return !gtEq(n, a1);
	}

	/** "Special" predicate: %s must NOT be >= %s. */
	public static @Nullable String notGtEq$(byte n, byte a1) {
		return notGtEq(n, a1) ? null : String.format("%s must NOT be >= %s.", n, a1);
	}

	/** Predicate: %s must be <= %s.*/
	public static boolean ltEq(byte n, byte a1) {
		return n <= a1;
	}

	/** "Special" predicate: %s must be <= %s. */
	public static @Nullable String ltEq$(byte n, byte a1) {
		return ltEq(n, a1) ? null : String.format("%s must be <= %s.", n, a1);
	}
	/** Predicate: %s must NOT be <= %s..*/
	public static boolean notLtEq(byte n, byte a1) {
		return !ltEq(n, a1);
	}

	/** "Special" predicate: %s must NOT be <= %s. */
	public static @Nullable String notLtEq$(byte n, byte a1) {
		return notLtEq(n, a1) ? null : String.format("%s must NOT be <= %s.", n, a1);
	}

	/** Predicate: %s must be >= 0 (must be non-negative).*/
	public static boolean nonNegative(byte n) {
		return n >= 0;
	}

	/** "Special" predicate: %s must be >= 0 (must be non-negative). */
	public static @Nullable String nonNegative$(byte n) {
		return nonNegative(n) ? null : String.format("%s must be >= 0 (must be non-negative).", n);
	}
	/** Predicate: %s must NOT be >= 0 (must be negative)..*/
	public static boolean negative(byte n) {
		return !nonNegative(n);
	}

	/** "Special" predicate: %s must NOT be >= 0 (must be negative). */
	public static @Nullable String negative$(byte n) {
		return negative(n) ? null : String.format("%s must NOT be >= 0 (must be negative).", n);
	}

	/** Predicate: %s must be <= 0 (must be non-positive).*/
	public static boolean nonPositive(byte n) {
		return n <= 0;
	}

	/** "Special" predicate: %s must be <= 0 (must be non-positive). */
	public static @Nullable String nonPositive$(byte n) {
		return nonPositive(n) ? null : String.format("%s must be <= 0 (must be non-positive).", n);
	}
	/** Predicate: %s must NOT be <= 0 (must be positive)..*/
	public static boolean positive(byte n) {
		return !nonPositive(n);
	}

	/** "Special" predicate: %s must NOT be <= 0 (must be positive). */
	public static @Nullable String positive$(byte n) {
		return positive(n) ? null : String.format("%s must NOT be <= 0 (must be positive).", n);
	}

	/** Predicate: <%s> must be equal to <%s>.*/
	public static boolean equalToObj(short n, Object o2) {
		return Objects.equals(n, o2);
	}

	/** "Special" predicate: <%s> must be equal to <%s>. */
	public static @Nullable String equalToObj$(short n, Object o2) {
		return equalToObj(n, o2) ? null : String.format("<%s> must be equal to <%s>.", n, o2);
	}
	/** Predicate: <%s> must NOT be equal to <%s>..*/
	public static boolean notEqualToObj(short n, Object o2) {
		return !equalToObj(n, o2);
	}

	/** "Special" predicate: <%s> must NOT be equal to <%s>. */
	public static @Nullable String notEqualToObj$(short n, Object o2) {
		return notEqualToObj(n, o2) ? null : String.format("<%s> must NOT be equal to <%s>.", n, o2);
	}

	/** Predicate: %s must be equal to %s.*/
	public static boolean equal(short n, short a1) {
		return n == a1;
	}

	/** "Special" predicate: %s must be equal to %s. */
	public static @Nullable String equal$(short n, short a1) {
		return equal(n, a1) ? null : String.format("%s must be equal to %s.", n, a1);
	}
	/** Predicate: %s must NOT be equal to %s..*/
	public static boolean notEqual(short n, short a1) {
		return !equal(n, a1);
	}

	/** "Special" predicate: %s must NOT be equal to %s. */
	public static @Nullable String notEqual$(short n, short a1) {
		return notEqual(n, a1) ? null : String.format("%s must NOT be equal to %s.", n, a1);
	}

	/** Predicate: %s must be > %s.*/
	public static boolean gt(short n, short a1) {
		return n > a1;
	}

	/** "Special" predicate: %s must be > %s. */
	public static @Nullable String gt$(short n, short a1) {
		return gt(n, a1) ? null : String.format("%s must be > %s.", n, a1);
	}
	/** Predicate: %s must NOT be > %s..*/
	public static boolean notGt(short n, short a1) {
		return !gt(n, a1);
	}

	/** "Special" predicate: %s must NOT be > %s. */
	public static @Nullable String notGt$(short n, short a1) {
		return notGt(n, a1) ? null : String.format("%s must NOT be > %s.", n, a1);
	}

	/** Predicate: %s must be < %s.*/
	public static boolean lt(short n, short a1) {
		return n < a1;
	}

	/** "Special" predicate: %s must be < %s. */
	public static @Nullable String lt$(short n, short a1) {
		return lt(n, a1) ? null : String.format("%s must be < %s.", n, a1);
	}
	/** Predicate: %s must NOT be < %s..*/
	public static boolean notLt(short n, short a1) {
		return !lt(n, a1);
	}

	/** "Special" predicate: %s must NOT be < %s. */
	public static @Nullable String notLt$(short n, short a1) {
		return notLt(n, a1) ? null : String.format("%s must NOT be < %s.", n, a1);
	}

	/** Predicate: %s must be >= %s.*/
	public static boolean gtEq(short n, short a1) {
		return n >= a1;
	}

	/** "Special" predicate: %s must be >= %s. */
	public static @Nullable String gtEq$(short n, short a1) {
		return gtEq(n, a1) ? null : String.format("%s must be >= %s.", n, a1);
	}
	/** Predicate: %s must NOT be >= %s..*/
	public static boolean notGtEq(short n, short a1) {
		return !gtEq(n, a1);
	}

	/** "Special" predicate: %s must NOT be >= %s. */
	public static @Nullable String notGtEq$(short n, short a1) {
		return notGtEq(n, a1) ? null : String.format("%s must NOT be >= %s.", n, a1);
	}

	/** Predicate: %s must be <= %s.*/
	public static boolean ltEq(short n, short a1) {
		return n <= a1;
	}

	/** "Special" predicate: %s must be <= %s. */
	public static @Nullable String ltEq$(short n, short a1) {
		return ltEq(n, a1) ? null : String.format("%s must be <= %s.", n, a1);
	}
	/** Predicate: %s must NOT be <= %s..*/
	public static boolean notLtEq(short n, short a1) {
		return !ltEq(n, a1);
	}

	/** "Special" predicate: %s must NOT be <= %s. */
	public static @Nullable String notLtEq$(short n, short a1) {
		return notLtEq(n, a1) ? null : String.format("%s must NOT be <= %s.", n, a1);
	}

	/** Predicate: %s must be >= 0 (must be non-negative).*/
	public static boolean nonNegative(short n) {
		return n >= 0;
	}

	/** "Special" predicate: %s must be >= 0 (must be non-negative). */
	public static @Nullable String nonNegative$(short n) {
		return nonNegative(n) ? null : String.format("%s must be >= 0 (must be non-negative).", n);
	}
	/** Predicate: %s must NOT be >= 0 (must be negative)..*/
	public static boolean negative(short n) {
		return !nonNegative(n);
	}

	/** "Special" predicate: %s must NOT be >= 0 (must be negative). */
	public static @Nullable String negative$(short n) {
		return negative(n) ? null : String.format("%s must NOT be >= 0 (must be negative).", n);
	}

	/** Predicate: %s must be <= 0 (must be non-positive).*/
	public static boolean nonPositive(short n) {
		return n <= 0;
	}

	/** "Special" predicate: %s must be <= 0 (must be non-positive). */
	public static @Nullable String nonPositive$(short n) {
		return nonPositive(n) ? null : String.format("%s must be <= 0 (must be non-positive).", n);
	}
	/** Predicate: %s must NOT be <= 0 (must be positive)..*/
	public static boolean positive(short n) {
		return !nonPositive(n);
	}

	/** "Special" predicate: %s must NOT be <= 0 (must be positive). */
	public static @Nullable String positive$(short n) {
		return positive(n) ? null : String.format("%s must NOT be <= 0 (must be positive).", n);
	}

	/** Predicate: <%s> must be equal to <%s>.*/
	public static boolean equalToObj(int n, Object o2) {
		return Objects.equals(n, o2);
	}

	/** "Special" predicate: <%s> must be equal to <%s>. */
	public static @Nullable String equalToObj$(int n, Object o2) {
		return equalToObj(n, o2) ? null : String.format("<%s> must be equal to <%s>.", n, o2);
	}
	/** Predicate: <%s> must NOT be equal to <%s>..*/
	public static boolean notEqualToObj(int n, Object o2) {
		return !equalToObj(n, o2);
	}

	/** "Special" predicate: <%s> must NOT be equal to <%s>. */
	public static @Nullable String notEqualToObj$(int n, Object o2) {
		return notEqualToObj(n, o2) ? null : String.format("<%s> must NOT be equal to <%s>.", n, o2);
	}

	/** Predicate: %s must be equal to %s.*/
	public static boolean equal(int n, int a1) {
		return n == a1;
	}

	/** "Special" predicate: %s must be equal to %s. */
	public static @Nullable String equal$(int n, int a1) {
		return equal(n, a1) ? null : String.format("%s must be equal to %s.", n, a1);
	}
	/** Predicate: %s must NOT be equal to %s..*/
	public static boolean notEqual(int n, int a1) {
		return !equal(n, a1);
	}

	/** "Special" predicate: %s must NOT be equal to %s. */
	public static @Nullable String notEqual$(int n, int a1) {
		return notEqual(n, a1) ? null : String.format("%s must NOT be equal to %s.", n, a1);
	}

	/** Predicate: %s must be > %s.*/
	public static boolean gt(int n, int a1) {
		return n > a1;
	}

	/** "Special" predicate: %s must be > %s. */
	public static @Nullable String gt$(int n, int a1) {
		return gt(n, a1) ? null : String.format("%s must be > %s.", n, a1);
	}
	/** Predicate: %s must NOT be > %s..*/
	public static boolean notGt(int n, int a1) {
		return !gt(n, a1);
	}

	/** "Special" predicate: %s must NOT be > %s. */
	public static @Nullable String notGt$(int n, int a1) {
		return notGt(n, a1) ? null : String.format("%s must NOT be > %s.", n, a1);
	}

	/** Predicate: %s must be < %s.*/
	public static boolean lt(int n, int a1) {
		return n < a1;
	}

	/** "Special" predicate: %s must be < %s. */
	public static @Nullable String lt$(int n, int a1) {
		return lt(n, a1) ? null : String.format("%s must be < %s.", n, a1);
	}
	/** Predicate: %s must NOT be < %s..*/
	public static boolean notLt(int n, int a1) {
		return !lt(n, a1);
	}

	/** "Special" predicate: %s must NOT be < %s. */
	public static @Nullable String notLt$(int n, int a1) {
		return notLt(n, a1) ? null : String.format("%s must NOT be < %s.", n, a1);
	}

	/** Predicate: %s must be >= %s.*/
	public static boolean gtEq(int n, int a1) {
		return n >= a1;
	}

	/** "Special" predicate: %s must be >= %s. */
	public static @Nullable String gtEq$(int n, int a1) {
		return gtEq(n, a1) ? null : String.format("%s must be >= %s.", n, a1);
	}
	/** Predicate: %s must NOT be >= %s..*/
	public static boolean notGtEq(int n, int a1) {
		return !gtEq(n, a1);
	}

	/** "Special" predicate: %s must NOT be >= %s. */
	public static @Nullable String notGtEq$(int n, int a1) {
		return notGtEq(n, a1) ? null : String.format("%s must NOT be >= %s.", n, a1);
	}

	/** Predicate: %s must be <= %s.*/
	public static boolean ltEq(int n, int a1) {
		return n <= a1;
	}

	/** "Special" predicate: %s must be <= %s. */
	public static @Nullable String ltEq$(int n, int a1) {
		return ltEq(n, a1) ? null : String.format("%s must be <= %s.", n, a1);
	}
	/** Predicate: %s must NOT be <= %s..*/
	public static boolean notLtEq(int n, int a1) {
		return !ltEq(n, a1);
	}

	/** "Special" predicate: %s must NOT be <= %s. */
	public static @Nullable String notLtEq$(int n, int a1) {
		return notLtEq(n, a1) ? null : String.format("%s must NOT be <= %s.", n, a1);
	}

	/** Predicate: %s must be >= 0 (must be non-negative).*/
	public static boolean nonNegative(int n) {
		return n >= 0;
	}

	/** "Special" predicate: %s must be >= 0 (must be non-negative). */
	public static @Nullable String nonNegative$(int n) {
		return nonNegative(n) ? null : String.format("%s must be >= 0 (must be non-negative).", n);
	}
	/** Predicate: %s must NOT be >= 0 (must be negative)..*/
	public static boolean negative(int n) {
		return !nonNegative(n);
	}

	/** "Special" predicate: %s must NOT be >= 0 (must be negative). */
	public static @Nullable String negative$(int n) {
		return negative(n) ? null : String.format("%s must NOT be >= 0 (must be negative).", n);
	}

	/** Predicate: %s must be <= 0 (must be non-positive).*/
	public static boolean nonPositive(int n) {
		return n <= 0;
	}

	/** "Special" predicate: %s must be <= 0 (must be non-positive). */
	public static @Nullable String nonPositive$(int n) {
		return nonPositive(n) ? null : String.format("%s must be <= 0 (must be non-positive).", n);
	}
	/** Predicate: %s must NOT be <= 0 (must be positive)..*/
	public static boolean positive(int n) {
		return !nonPositive(n);
	}

	/** "Special" predicate: %s must NOT be <= 0 (must be positive). */
	public static @Nullable String positive$(int n) {
		return positive(n) ? null : String.format("%s must NOT be <= 0 (must be positive).", n);
	}

	/** Predicate: <%s> must be equal to <%s>.*/
	public static boolean equalToObj(long n, Object o2) {
		return Objects.equals(n, o2);
	}

	/** "Special" predicate: <%s> must be equal to <%s>. */
	public static @Nullable String equalToObj$(long n, Object o2) {
		return equalToObj(n, o2) ? null : String.format("<%s> must be equal to <%s>.", n, o2);
	}
	/** Predicate: <%s> must NOT be equal to <%s>..*/
	public static boolean notEqualToObj(long n, Object o2) {
		return !equalToObj(n, o2);
	}

	/** "Special" predicate: <%s> must NOT be equal to <%s>. */
	public static @Nullable String notEqualToObj$(long n, Object o2) {
		return notEqualToObj(n, o2) ? null : String.format("<%s> must NOT be equal to <%s>.", n, o2);
	}

	/** Predicate: %s must be equal to %s.*/
	public static boolean equal(long n, long a1) {
		return n == a1;
	}

	/** "Special" predicate: %s must be equal to %s. */
	public static @Nullable String equal$(long n, long a1) {
		return equal(n, a1) ? null : String.format("%s must be equal to %s.", n, a1);
	}
	/** Predicate: %s must NOT be equal to %s..*/
	public static boolean notEqual(long n, long a1) {
		return !equal(n, a1);
	}

	/** "Special" predicate: %s must NOT be equal to %s. */
	public static @Nullable String notEqual$(long n, long a1) {
		return notEqual(n, a1) ? null : String.format("%s must NOT be equal to %s.", n, a1);
	}

	/** Predicate: %s must be > %s.*/
	public static boolean gt(long n, long a1) {
		return n > a1;
	}

	/** "Special" predicate: %s must be > %s. */
	public static @Nullable String gt$(long n, long a1) {
		return gt(n, a1) ? null : String.format("%s must be > %s.", n, a1);
	}
	/** Predicate: %s must NOT be > %s..*/
	public static boolean notGt(long n, long a1) {
		return !gt(n, a1);
	}

	/** "Special" predicate: %s must NOT be > %s. */
	public static @Nullable String notGt$(long n, long a1) {
		return notGt(n, a1) ? null : String.format("%s must NOT be > %s.", n, a1);
	}

	/** Predicate: %s must be < %s.*/
	public static boolean lt(long n, long a1) {
		return n < a1;
	}

	/** "Special" predicate: %s must be < %s. */
	public static @Nullable String lt$(long n, long a1) {
		return lt(n, a1) ? null : String.format("%s must be < %s.", n, a1);
	}
	/** Predicate: %s must NOT be < %s..*/
	public static boolean notLt(long n, long a1) {
		return !lt(n, a1);
	}

	/** "Special" predicate: %s must NOT be < %s. */
	public static @Nullable String notLt$(long n, long a1) {
		return notLt(n, a1) ? null : String.format("%s must NOT be < %s.", n, a1);
	}

	/** Predicate: %s must be >= %s.*/
	public static boolean gtEq(long n, long a1) {
		return n >= a1;
	}

	/** "Special" predicate: %s must be >= %s. */
	public static @Nullable String gtEq$(long n, long a1) {
		return gtEq(n, a1) ? null : String.format("%s must be >= %s.", n, a1);
	}
	/** Predicate: %s must NOT be >= %s..*/
	public static boolean notGtEq(long n, long a1) {
		return !gtEq(n, a1);
	}

	/** "Special" predicate: %s must NOT be >= %s. */
	public static @Nullable String notGtEq$(long n, long a1) {
		return notGtEq(n, a1) ? null : String.format("%s must NOT be >= %s.", n, a1);
	}

	/** Predicate: %s must be <= %s.*/
	public static boolean ltEq(long n, long a1) {
		return n <= a1;
	}

	/** "Special" predicate: %s must be <= %s. */
	public static @Nullable String ltEq$(long n, long a1) {
		return ltEq(n, a1) ? null : String.format("%s must be <= %s.", n, a1);
	}
	/** Predicate: %s must NOT be <= %s..*/
	public static boolean notLtEq(long n, long a1) {
		return !ltEq(n, a1);
	}

	/** "Special" predicate: %s must NOT be <= %s. */
	public static @Nullable String notLtEq$(long n, long a1) {
		return notLtEq(n, a1) ? null : String.format("%s must NOT be <= %s.", n, a1);
	}

	/** Predicate: %s must be >= 0 (must be non-negative).*/
	public static boolean nonNegative(long n) {
		return n >= 0;
	}

	/** "Special" predicate: %s must be >= 0 (must be non-negative). */
	public static @Nullable String nonNegative$(long n) {
		return nonNegative(n) ? null : String.format("%s must be >= 0 (must be non-negative).", n);
	}
	/** Predicate: %s must NOT be >= 0 (must be negative)..*/
	public static boolean negative(long n) {
		return !nonNegative(n);
	}

	/** "Special" predicate: %s must NOT be >= 0 (must be negative). */
	public static @Nullable String negative$(long n) {
		return negative(n) ? null : String.format("%s must NOT be >= 0 (must be negative).", n);
	}

	/** Predicate: %s must be <= 0 (must be non-positive).*/
	public static boolean nonPositive(long n) {
		return n <= 0;
	}

	/** "Special" predicate: %s must be <= 0 (must be non-positive). */
	public static @Nullable String nonPositive$(long n) {
		return nonPositive(n) ? null : String.format("%s must be <= 0 (must be non-positive).", n);
	}
	/** Predicate: %s must NOT be <= 0 (must be positive)..*/
	public static boolean positive(long n) {
		return !nonPositive(n);
	}

	/** "Special" predicate: %s must NOT be <= 0 (must be positive). */
	public static @Nullable String positive$(long n) {
		return positive(n) ? null : String.format("%s must NOT be <= 0 (must be positive).", n);
	}

	/** Predicate: <%s> must be equal to <%s>.*/
	public static boolean equalToObj(float n, Object o2) {
		return Objects.equals(n, o2);
	}

	/** "Special" predicate: <%s> must be equal to <%s>. */
	public static @Nullable String equalToObj$(float n, Object o2) {
		return equalToObj(n, o2) ? null : String.format("<%s> must be equal to <%s>.", n, o2);
	}
	/** Predicate: <%s> must NOT be equal to <%s>..*/
	public static boolean notEqualToObj(float n, Object o2) {
		return !equalToObj(n, o2);
	}

	/** "Special" predicate: <%s> must NOT be equal to <%s>. */
	public static @Nullable String notEqualToObj$(float n, Object o2) {
		return notEqualToObj(n, o2) ? null : String.format("<%s> must NOT be equal to <%s>.", n, o2);
	}

	/** Predicate: %s must be equal to %s.*/
	public static boolean equal(float n, float a1) {
		return n == a1;
	}

	/** "Special" predicate: %s must be equal to %s. */
	public static @Nullable String equal$(float n, float a1) {
		return equal(n, a1) ? null : String.format("%s must be equal to %s.", n, a1);
	}
	/** Predicate: %s must NOT be equal to %s..*/
	public static boolean notEqual(float n, float a1) {
		return !equal(n, a1);
	}

	/** "Special" predicate: %s must NOT be equal to %s. */
	public static @Nullable String notEqual$(float n, float a1) {
		return notEqual(n, a1) ? null : String.format("%s must NOT be equal to %s.", n, a1);
	}

	/** Predicate: %s must be > %s.*/
	public static boolean gt(float n, float a1) {
		return n > a1;
	}

	/** "Special" predicate: %s must be > %s. */
	public static @Nullable String gt$(float n, float a1) {
		return gt(n, a1) ? null : String.format("%s must be > %s.", n, a1);
	}
	/** Predicate: %s must NOT be > %s..*/
	public static boolean notGt(float n, float a1) {
		return !gt(n, a1);
	}

	/** "Special" predicate: %s must NOT be > %s. */
	public static @Nullable String notGt$(float n, float a1) {
		return notGt(n, a1) ? null : String.format("%s must NOT be > %s.", n, a1);
	}

	/** Predicate: %s must be < %s.*/
	public static boolean lt(float n, float a1) {
		return n < a1;
	}

	/** "Special" predicate: %s must be < %s. */
	public static @Nullable String lt$(float n, float a1) {
		return lt(n, a1) ? null : String.format("%s must be < %s.", n, a1);
	}
	/** Predicate: %s must NOT be < %s..*/
	public static boolean notLt(float n, float a1) {
		return !lt(n, a1);
	}

	/** "Special" predicate: %s must NOT be < %s. */
	public static @Nullable String notLt$(float n, float a1) {
		return notLt(n, a1) ? null : String.format("%s must NOT be < %s.", n, a1);
	}

	/** Predicate: %s must be >= %s.*/
	public static boolean gtEq(float n, float a1) {
		return n >= a1;
	}

	/** "Special" predicate: %s must be >= %s. */
	public static @Nullable String gtEq$(float n, float a1) {
		return gtEq(n, a1) ? null : String.format("%s must be >= %s.", n, a1);
	}
	/** Predicate: %s must NOT be >= %s..*/
	public static boolean notGtEq(float n, float a1) {
		return !gtEq(n, a1);
	}

	/** "Special" predicate: %s must NOT be >= %s. */
	public static @Nullable String notGtEq$(float n, float a1) {
		return notGtEq(n, a1) ? null : String.format("%s must NOT be >= %s.", n, a1);
	}

	/** Predicate: %s must be <= %s.*/
	public static boolean ltEq(float n, float a1) {
		return n <= a1;
	}

	/** "Special" predicate: %s must be <= %s. */
	public static @Nullable String ltEq$(float n, float a1) {
		return ltEq(n, a1) ? null : String.format("%s must be <= %s.", n, a1);
	}
	/** Predicate: %s must NOT be <= %s..*/
	public static boolean notLtEq(float n, float a1) {
		return !ltEq(n, a1);
	}

	/** "Special" predicate: %s must NOT be <= %s. */
	public static @Nullable String notLtEq$(float n, float a1) {
		return notLtEq(n, a1) ? null : String.format("%s must NOT be <= %s.", n, a1);
	}

	/** Predicate: %s must be >= 0 (must be non-negative).*/
	public static boolean nonNegative(float n) {
		return n >= 0;
	}

	/** "Special" predicate: %s must be >= 0 (must be non-negative). */
	public static @Nullable String nonNegative$(float n) {
		return nonNegative(n) ? null : String.format("%s must be >= 0 (must be non-negative).", n);
	}
	/** Predicate: %s must NOT be >= 0 (must be negative)..*/
	public static boolean negative(float n) {
		return !nonNegative(n);
	}

	/** "Special" predicate: %s must NOT be >= 0 (must be negative). */
	public static @Nullable String negative$(float n) {
		return negative(n) ? null : String.format("%s must NOT be >= 0 (must be negative).", n);
	}

	/** Predicate: %s must be <= 0 (must be non-positive).*/
	public static boolean nonPositive(float n) {
		return n <= 0;
	}

	/** "Special" predicate: %s must be <= 0 (must be non-positive). */
	public static @Nullable String nonPositive$(float n) {
		return nonPositive(n) ? null : String.format("%s must be <= 0 (must be non-positive).", n);
	}
	/** Predicate: %s must NOT be <= 0 (must be positive)..*/
	public static boolean positive(float n) {
		return !nonPositive(n);
	}

	/** "Special" predicate: %s must NOT be <= 0 (must be positive). */
	public static @Nullable String positive$(float n) {
		return positive(n) ? null : String.format("%s must NOT be <= 0 (must be positive).", n);
	}

	/** Predicate: <%s> must be equal to <%s>.*/
	public static boolean equalToObj(double n, Object o2) {
		return Objects.equals(n, o2);
	}

	/** "Special" predicate: <%s> must be equal to <%s>. */
	public static @Nullable String equalToObj$(double n, Object o2) {
		return equalToObj(n, o2) ? null : String.format("<%s> must be equal to <%s>.", n, o2);
	}
	/** Predicate: <%s> must NOT be equal to <%s>..*/
	public static boolean notEqualToObj(double n, Object o2) {
		return !equalToObj(n, o2);
	}

	/** "Special" predicate: <%s> must NOT be equal to <%s>. */
	public static @Nullable String notEqualToObj$(double n, Object o2) {
		return notEqualToObj(n, o2) ? null : String.format("<%s> must NOT be equal to <%s>.", n, o2);
	}

	/** Predicate: %s must be equal to %s.*/
	public static boolean equal(double n, double a1) {
		return n == a1;
	}

	/** "Special" predicate: %s must be equal to %s. */
	public static @Nullable String equal$(double n, double a1) {
		return equal(n, a1) ? null : String.format("%s must be equal to %s.", n, a1);
	}
	/** Predicate: %s must NOT be equal to %s..*/
	public static boolean notEqual(double n, double a1) {
		return !equal(n, a1);
	}

	/** "Special" predicate: %s must NOT be equal to %s. */
	public static @Nullable String notEqual$(double n, double a1) {
		return notEqual(n, a1) ? null : String.format("%s must NOT be equal to %s.", n, a1);
	}

	/** Predicate: %s must be > %s.*/
	public static boolean gt(double n, double a1) {
		return n > a1;
	}

	/** "Special" predicate: %s must be > %s. */
	public static @Nullable String gt$(double n, double a1) {
		return gt(n, a1) ? null : String.format("%s must be > %s.", n, a1);
	}
	/** Predicate: %s must NOT be > %s..*/
	public static boolean notGt(double n, double a1) {
		return !gt(n, a1);
	}

	/** "Special" predicate: %s must NOT be > %s. */
	public static @Nullable String notGt$(double n, double a1) {
		return notGt(n, a1) ? null : String.format("%s must NOT be > %s.", n, a1);
	}

	/** Predicate: %s must be < %s.*/
	public static boolean lt(double n, double a1) {
		return n < a1;
	}

	/** "Special" predicate: %s must be < %s. */
	public static @Nullable String lt$(double n, double a1) {
		return lt(n, a1) ? null : String.format("%s must be < %s.", n, a1);
	}
	/** Predicate: %s must NOT be < %s..*/
	public static boolean notLt(double n, double a1) {
		return !lt(n, a1);
	}

	/** "Special" predicate: %s must NOT be < %s. */
	public static @Nullable String notLt$(double n, double a1) {
		return notLt(n, a1) ? null : String.format("%s must NOT be < %s.", n, a1);
	}

	/** Predicate: %s must be >= %s.*/
	public static boolean gtEq(double n, double a1) {
		return n >= a1;
	}

	/** "Special" predicate: %s must be >= %s. */
	public static @Nullable String gtEq$(double n, double a1) {
		return gtEq(n, a1) ? null : String.format("%s must be >= %s.", n, a1);
	}
	/** Predicate: %s must NOT be >= %s..*/
	public static boolean notGtEq(double n, double a1) {
		return !gtEq(n, a1);
	}

	/** "Special" predicate: %s must NOT be >= %s. */
	public static @Nullable String notGtEq$(double n, double a1) {
		return notGtEq(n, a1) ? null : String.format("%s must NOT be >= %s.", n, a1);
	}

	/** Predicate: %s must be <= %s.*/
	public static boolean ltEq(double n, double a1) {
		return n <= a1;
	}

	/** "Special" predicate: %s must be <= %s. */
	public static @Nullable String ltEq$(double n, double a1) {
		return ltEq(n, a1) ? null : String.format("%s must be <= %s.", n, a1);
	}
	/** Predicate: %s must NOT be <= %s..*/
	public static boolean notLtEq(double n, double a1) {
		return !ltEq(n, a1);
	}

	/** "Special" predicate: %s must NOT be <= %s. */
	public static @Nullable String notLtEq$(double n, double a1) {
		return notLtEq(n, a1) ? null : String.format("%s must NOT be <= %s.", n, a1);
	}

	/** Predicate: %s must be >= 0 (must be non-negative).*/
	public static boolean nonNegative(double n) {
		return n >= 0;
	}

	/** "Special" predicate: %s must be >= 0 (must be non-negative). */
	public static @Nullable String nonNegative$(double n) {
		return nonNegative(n) ? null : String.format("%s must be >= 0 (must be non-negative).", n);
	}
	/** Predicate: %s must NOT be >= 0 (must be negative)..*/
	public static boolean negative(double n) {
		return !nonNegative(n);
	}

	/** "Special" predicate: %s must NOT be >= 0 (must be negative). */
	public static @Nullable String negative$(double n) {
		return negative(n) ? null : String.format("%s must NOT be >= 0 (must be negative).", n);
	}

	/** Predicate: %s must be <= 0 (must be non-positive).*/
	public static boolean nonPositive(double n) {
		return n <= 0;
	}

	/** "Special" predicate: %s must be <= 0 (must be non-positive). */
	public static @Nullable String nonPositive$(double n) {
		return nonPositive(n) ? null : String.format("%s must be <= 0 (must be non-positive).", n);
	}
	/** Predicate: %s must NOT be <= 0 (must be positive)..*/
	public static boolean positive(double n) {
		return !nonPositive(n);
	}

	/** "Special" predicate: %s must NOT be <= 0 (must be positive). */
	public static @Nullable String positive$(double n) {
		return positive(n) ? null : String.format("%s must NOT be <= 0 (must be positive).", n);
	}

	/** Predicate: <%s> must be equal to <%s>.*/
	public static boolean equalToObj(char n, Object o2) {
		return Objects.equals(n, o2);
	}

	/** "Special" predicate: <%s> must be equal to <%s>. */
	public static @Nullable String equalToObj$(char n, Object o2) {
		return equalToObj(n, o2) ? null : String.format("<%s> must be equal to <%s>.", n, o2);
	}
	/** Predicate: <%s> must NOT be equal to <%s>..*/
	public static boolean notEqualToObj(char n, Object o2) {
		return !equalToObj(n, o2);
	}

	/** "Special" predicate: <%s> must NOT be equal to <%s>. */
	public static @Nullable String notEqualToObj$(char n, Object o2) {
		return notEqualToObj(n, o2) ? null : String.format("<%s> must NOT be equal to <%s>.", n, o2);
	}

	/** Predicate: %s must be equal to %s.*/
	public static boolean equal(char n, char a1) {
		return n == a1;
	}

	/** "Special" predicate: %s must be equal to %s. */
	public static @Nullable String equal$(char n, char a1) {
		return equal(n, a1) ? null : String.format("%s must be equal to %s.", n, a1);
	}
	/** Predicate: %s must NOT be equal to %s..*/
	public static boolean notEqual(char n, char a1) {
		return !equal(n, a1);
	}

	/** "Special" predicate: %s must NOT be equal to %s. */
	public static @Nullable String notEqual$(char n, char a1) {
		return notEqual(n, a1) ? null : String.format("%s must NOT be equal to %s.", n, a1);
	}

	/** Predicate: %s must be > %s.*/
	public static boolean gt(char n, char a1) {
		return n > a1;
	}

	/** "Special" predicate: %s must be > %s. */
	public static @Nullable String gt$(char n, char a1) {
		return gt(n, a1) ? null : String.format("%s must be > %s.", n, a1);
	}
	/** Predicate: %s must NOT be > %s..*/
	public static boolean notGt(char n, char a1) {
		return !gt(n, a1);
	}

	/** "Special" predicate: %s must NOT be > %s. */
	public static @Nullable String notGt$(char n, char a1) {
		return notGt(n, a1) ? null : String.format("%s must NOT be > %s.", n, a1);
	}

	/** Predicate: %s must be < %s.*/
	public static boolean lt(char n, char a1) {
		return n < a1;
	}

	/** "Special" predicate: %s must be < %s. */
	public static @Nullable String lt$(char n, char a1) {
		return lt(n, a1) ? null : String.format("%s must be < %s.", n, a1);
	}
	/** Predicate: %s must NOT be < %s..*/
	public static boolean notLt(char n, char a1) {
		return !lt(n, a1);
	}

	/** "Special" predicate: %s must NOT be < %s. */
	public static @Nullable String notLt$(char n, char a1) {
		return notLt(n, a1) ? null : String.format("%s must NOT be < %s.", n, a1);
	}

	/** Predicate: %s must be >= %s.*/
	public static boolean gtEq(char n, char a1) {
		return n >= a1;
	}

	/** "Special" predicate: %s must be >= %s. */
	public static @Nullable String gtEq$(char n, char a1) {
		return gtEq(n, a1) ? null : String.format("%s must be >= %s.", n, a1);
	}
	/** Predicate: %s must NOT be >= %s..*/
	public static boolean notGtEq(char n, char a1) {
		return !gtEq(n, a1);
	}

	/** "Special" predicate: %s must NOT be >= %s. */
	public static @Nullable String notGtEq$(char n, char a1) {
		return notGtEq(n, a1) ? null : String.format("%s must NOT be >= %s.", n, a1);
	}

	/** Predicate: %s must be <= %s.*/
	public static boolean ltEq(char n, char a1) {
		return n <= a1;
	}

	/** "Special" predicate: %s must be <= %s. */
	public static @Nullable String ltEq$(char n, char a1) {
		return ltEq(n, a1) ? null : String.format("%s must be <= %s.", n, a1);
	}
	/** Predicate: %s must NOT be <= %s..*/
	public static boolean notLtEq(char n, char a1) {
		return !ltEq(n, a1);
	}

	/** "Special" predicate: %s must NOT be <= %s. */
	public static @Nullable String notLtEq$(char n, char a1) {
		return notLtEq(n, a1) ? null : String.format("%s must NOT be <= %s.", n, a1);
	}

	/** Predicate: %s must be >= 0 (must be non-negative).*/
	public static boolean nonNegative(char n) {
		return n >= 0;
	}

	/** "Special" predicate: %s must be >= 0 (must be non-negative). */
	public static @Nullable String nonNegative$(char n) {
		return nonNegative(n) ? null : String.format("%s must be >= 0 (must be non-negative).", n);
	}
	/** Predicate: %s must NOT be >= 0 (must be negative)..*/
	public static boolean negative(char n) {
		return !nonNegative(n);
	}

	/** "Special" predicate: %s must NOT be >= 0 (must be negative). */
	public static @Nullable String negative$(char n) {
		return negative(n) ? null : String.format("%s must NOT be >= 0 (must be negative).", n);
	}

	/** Predicate: %s must be <= 0 (must be non-positive).*/
	public static boolean nonPositive(char n) {
		return n <= 0;
	}

	/** "Special" predicate: %s must be <= 0 (must be non-positive). */
	public static @Nullable String nonPositive$(char n) {
		return nonPositive(n) ? null : String.format("%s must be <= 0 (must be non-positive).", n);
	}
	/** Predicate: %s must NOT be <= 0 (must be positive)..*/
	public static boolean positive(char n) {
		return !nonPositive(n);
	}

	/** "Special" predicate: %s must NOT be <= 0 (must be positive). */
	public static @Nullable String positive$(char n) {
		return positive(n) ? null : String.format("%s must NOT be <= 0 (must be positive).", n);
	}

	/** Predicate: <%s> must be equal to <%s>.*/
	public static boolean equalToObj(boolean n, Object o2) {
		return Objects.equals(n, o2);
	}

	/** "Special" predicate: <%s> must be equal to <%s>. */
	public static @Nullable String equalToObj$(boolean n, Object o2) {
		return equalToObj(n, o2) ? null : String.format("<%s> must be equal to <%s>.", n, o2);
	}
	/** Predicate: <%s> must NOT be equal to <%s>..*/
	public static boolean notEqualToObj(boolean n, Object o2) {
		return !equalToObj(n, o2);
	}

	/** "Special" predicate: <%s> must NOT be equal to <%s>. */
	public static @Nullable String notEqualToObj$(boolean n, Object o2) {
		return notEqualToObj(n, o2) ? null : String.format("<%s> must NOT be equal to <%s>.", n, o2);
	}

	/** Predicate: %s must be equal to %s.*/
	public static boolean equal(boolean n, boolean a1) {
		return n == a1;
	}

	/** "Special" predicate: %s must be equal to %s. */
	public static @Nullable String equal$(boolean n, boolean a1) {
		return equal(n, a1) ? null : String.format("%s must be equal to %s.", n, a1);
	}
	/** Predicate: %s must NOT be equal to %s..*/
	public static boolean notEqual(boolean n, boolean a1) {
		return !equal(n, a1);
	}

	/** "Special" predicate: %s must NOT be equal to %s. */
	public static @Nullable String notEqual$(boolean n, boolean a1) {
		return notEqual(n, a1) ? null : String.format("%s must NOT be equal to %s.", n, a1);
	}

	/** Predicate: %1$s must be: %2$s < %1$s < %3$s.*/
	public static boolean between(byte n, byte a1, byte a2) {
		return a1 < n && n < a2;
	}

	/** "Special" predicate: %1$s must be: %2$s < %1$s < %3$s. */
	public static @Nullable String between$(byte n, byte a1, byte a2) {
		return between(n, a1, a2) ? null : String.format("%1$s must be: %2$s < %1$s < %3$s.", n, a1, a2);
	}
	/** Predicate: %1$s must NOT be: %2$s < %1$s < %3$s..*/
	public static boolean notBetween(byte n, byte a1, byte a2) {
		return !between(n, a1, a2);
	}

	/** "Special" predicate: %1$s must NOT be: %2$s < %1$s < %3$s. */
	public static @Nullable String notBetween$(byte n, byte a1, byte a2) {
		return notBetween(n, a1, a2) ? null : String.format("%1$s must NOT be: %2$s < %1$s < %3$s.", n, a1, a2);
	}

	/** Predicate: %1$s must be: %2$s <= %1$s <= %3$s.*/
	public static boolean inRange(byte n, byte a1, byte a2) {
		return a1 <= n && n <= a2;
	}

	/** "Special" predicate: %1$s must be: %2$s <= %1$s <= %3$s. */
	public static @Nullable String inRange$(byte n, byte a1, byte a2) {
		return inRange(n, a1, a2) ? null : String.format("%1$s must be: %2$s <= %1$s <= %3$s.", n, a1, a2);
	}
	/** Predicate: %1$s must NOT be: %2$s <= %1$s <= %3$s..*/
	public static boolean notInRange(byte n, byte a1, byte a2) {
		return !inRange(n, a1, a2);
	}

	/** "Special" predicate: %1$s must NOT be: %2$s <= %1$s <= %3$s. */
	public static @Nullable String notInRange$(byte n, byte a1, byte a2) {
		return notInRange(n, a1, a2) ? null : String.format("%1$s must NOT be: %2$s <= %1$s <= %3$s.", n, a1, a2);
	}

	/** Predicate: %1$s must be: %2$s < %1$s < %3$s.*/
	public static boolean between(short n, short a1, short a2) {
		return a1 < n && n < a2;
	}

	/** "Special" predicate: %1$s must be: %2$s < %1$s < %3$s. */
	public static @Nullable String between$(short n, short a1, short a2) {
		return between(n, a1, a2) ? null : String.format("%1$s must be: %2$s < %1$s < %3$s.", n, a1, a2);
	}
	/** Predicate: %1$s must NOT be: %2$s < %1$s < %3$s..*/
	public static boolean notBetween(short n, short a1, short a2) {
		return !between(n, a1, a2);
	}

	/** "Special" predicate: %1$s must NOT be: %2$s < %1$s < %3$s. */
	public static @Nullable String notBetween$(short n, short a1, short a2) {
		return notBetween(n, a1, a2) ? null : String.format("%1$s must NOT be: %2$s < %1$s < %3$s.", n, a1, a2);
	}

	/** Predicate: %1$s must be: %2$s <= %1$s <= %3$s.*/
	public static boolean inRange(short n, short a1, short a2) {
		return a1 <= n && n <= a2;
	}

	/** "Special" predicate: %1$s must be: %2$s <= %1$s <= %3$s. */
	public static @Nullable String inRange$(short n, short a1, short a2) {
		return inRange(n, a1, a2) ? null : String.format("%1$s must be: %2$s <= %1$s <= %3$s.", n, a1, a2);
	}
	/** Predicate: %1$s must NOT be: %2$s <= %1$s <= %3$s..*/
	public static boolean notInRange(short n, short a1, short a2) {
		return !inRange(n, a1, a2);
	}

	/** "Special" predicate: %1$s must NOT be: %2$s <= %1$s <= %3$s. */
	public static @Nullable String notInRange$(short n, short a1, short a2) {
		return notInRange(n, a1, a2) ? null : String.format("%1$s must NOT be: %2$s <= %1$s <= %3$s.", n, a1, a2);
	}

	/** Predicate: %1$s must be: %2$s < %1$s < %3$s.*/
	public static boolean between(int n, int a1, int a2) {
		return a1 < n && n < a2;
	}

	/** "Special" predicate: %1$s must be: %2$s < %1$s < %3$s. */
	public static @Nullable String between$(int n, int a1, int a2) {
		return between(n, a1, a2) ? null : String.format("%1$s must be: %2$s < %1$s < %3$s.", n, a1, a2);
	}
	/** Predicate: %1$s must NOT be: %2$s < %1$s < %3$s..*/
	public static boolean notBetween(int n, int a1, int a2) {
		return !between(n, a1, a2);
	}

	/** "Special" predicate: %1$s must NOT be: %2$s < %1$s < %3$s. */
	public static @Nullable String notBetween$(int n, int a1, int a2) {
		return notBetween(n, a1, a2) ? null : String.format("%1$s must NOT be: %2$s < %1$s < %3$s.", n, a1, a2);
	}

	/** Predicate: %1$s must be: %2$s <= %1$s <= %3$s.*/
	public static boolean inRange(int n, int a1, int a2) {
		return a1 <= n && n <= a2;
	}

	/** "Special" predicate: %1$s must be: %2$s <= %1$s <= %3$s. */
	public static @Nullable String inRange$(int n, int a1, int a2) {
		return inRange(n, a1, a2) ? null : String.format("%1$s must be: %2$s <= %1$s <= %3$s.", n, a1, a2);
	}
	/** Predicate: %1$s must NOT be: %2$s <= %1$s <= %3$s..*/
	public static boolean notInRange(int n, int a1, int a2) {
		return !inRange(n, a1, a2);
	}

	/** "Special" predicate: %1$s must NOT be: %2$s <= %1$s <= %3$s. */
	public static @Nullable String notInRange$(int n, int a1, int a2) {
		return notInRange(n, a1, a2) ? null : String.format("%1$s must NOT be: %2$s <= %1$s <= %3$s.", n, a1, a2);
	}

	/** Predicate: %1$s must be: %2$s < %1$s < %3$s.*/
	public static boolean between(long n, long a1, long a2) {
		return a1 < n && n < a2;
	}

	/** "Special" predicate: %1$s must be: %2$s < %1$s < %3$s. */
	public static @Nullable String between$(long n, long a1, long a2) {
		return between(n, a1, a2) ? null : String.format("%1$s must be: %2$s < %1$s < %3$s.", n, a1, a2);
	}
	/** Predicate: %1$s must NOT be: %2$s < %1$s < %3$s..*/
	public static boolean notBetween(long n, long a1, long a2) {
		return !between(n, a1, a2);
	}

	/** "Special" predicate: %1$s must NOT be: %2$s < %1$s < %3$s. */
	public static @Nullable String notBetween$(long n, long a1, long a2) {
		return notBetween(n, a1, a2) ? null : String.format("%1$s must NOT be: %2$s < %1$s < %3$s.", n, a1, a2);
	}

	/** Predicate: %1$s must be: %2$s <= %1$s <= %3$s.*/
	public static boolean inRange(long n, long a1, long a2) {
		return a1 <= n && n <= a2;
	}

	/** "Special" predicate: %1$s must be: %2$s <= %1$s <= %3$s. */
	public static @Nullable String inRange$(long n, long a1, long a2) {
		return inRange(n, a1, a2) ? null : String.format("%1$s must be: %2$s <= %1$s <= %3$s.", n, a1, a2);
	}
	/** Predicate: %1$s must NOT be: %2$s <= %1$s <= %3$s..*/
	public static boolean notInRange(long n, long a1, long a2) {
		return !inRange(n, a1, a2);
	}

	/** "Special" predicate: %1$s must NOT be: %2$s <= %1$s <= %3$s. */
	public static @Nullable String notInRange$(long n, long a1, long a2) {
		return notInRange(n, a1, a2) ? null : String.format("%1$s must NOT be: %2$s <= %1$s <= %3$s.", n, a1, a2);
	}

	/** Predicate: %1$s must be: %2$s < %1$s < %3$s.*/
	public static boolean between(float n, float a1, float a2) {
		return a1 < n && n < a2;
	}

	/** "Special" predicate: %1$s must be: %2$s < %1$s < %3$s. */
	public static @Nullable String between$(float n, float a1, float a2) {
		return between(n, a1, a2) ? null : String.format("%1$s must be: %2$s < %1$s < %3$s.", n, a1, a2);
	}
	/** Predicate: %1$s must NOT be: %2$s < %1$s < %3$s..*/
	public static boolean notBetween(float n, float a1, float a2) {
		return !between(n, a1, a2);
	}

	/** "Special" predicate: %1$s must NOT be: %2$s < %1$s < %3$s. */
	public static @Nullable String notBetween$(float n, float a1, float a2) {
		return notBetween(n, a1, a2) ? null : String.format("%1$s must NOT be: %2$s < %1$s < %3$s.", n, a1, a2);
	}

	/** Predicate: %1$s must be: %2$s <= %1$s <= %3$s.*/
	public static boolean inRange(float n, float a1, float a2) {
		return a1 <= n && n <= a2;
	}

	/** "Special" predicate: %1$s must be: %2$s <= %1$s <= %3$s. */
	public static @Nullable String inRange$(float n, float a1, float a2) {
		return inRange(n, a1, a2) ? null : String.format("%1$s must be: %2$s <= %1$s <= %3$s.", n, a1, a2);
	}
	/** Predicate: %1$s must NOT be: %2$s <= %1$s <= %3$s..*/
	public static boolean notInRange(float n, float a1, float a2) {
		return !inRange(n, a1, a2);
	}

	/** "Special" predicate: %1$s must NOT be: %2$s <= %1$s <= %3$s. */
	public static @Nullable String notInRange$(float n, float a1, float a2) {
		return notInRange(n, a1, a2) ? null : String.format("%1$s must NOT be: %2$s <= %1$s <= %3$s.", n, a1, a2);
	}

	/** Predicate: %1$s must be: %2$s < %1$s < %3$s.*/
	public static boolean between(double n, double a1, double a2) {
		return a1 < n && n < a2;
	}

	/** "Special" predicate: %1$s must be: %2$s < %1$s < %3$s. */
	public static @Nullable String between$(double n, double a1, double a2) {
		return between(n, a1, a2) ? null : String.format("%1$s must be: %2$s < %1$s < %3$s.", n, a1, a2);
	}
	/** Predicate: %1$s must NOT be: %2$s < %1$s < %3$s..*/
	public static boolean notBetween(double n, double a1, double a2) {
		return !between(n, a1, a2);
	}

	/** "Special" predicate: %1$s must NOT be: %2$s < %1$s < %3$s. */
	public static @Nullable String notBetween$(double n, double a1, double a2) {
		return notBetween(n, a1, a2) ? null : String.format("%1$s must NOT be: %2$s < %1$s < %3$s.", n, a1, a2);
	}

	/** Predicate: %1$s must be: %2$s <= %1$s <= %3$s.*/
	public static boolean inRange(double n, double a1, double a2) {
		return a1 <= n && n <= a2;
	}

	/** "Special" predicate: %1$s must be: %2$s <= %1$s <= %3$s. */
	public static @Nullable String inRange$(double n, double a1, double a2) {
		return inRange(n, a1, a2) ? null : String.format("%1$s must be: %2$s <= %1$s <= %3$s.", n, a1, a2);
	}
	/** Predicate: %1$s must NOT be: %2$s <= %1$s <= %3$s..*/
	public static boolean notInRange(double n, double a1, double a2) {
		return !inRange(n, a1, a2);
	}

	/** "Special" predicate: %1$s must NOT be: %2$s <= %1$s <= %3$s. */
	public static @Nullable String notInRange$(double n, double a1, double a2) {
		return notInRange(n, a1, a2) ? null : String.format("%1$s must NOT be: %2$s <= %1$s <= %3$s.", n, a1, a2);
	}

	/** Predicate: %1$s must be: %2$s < %1$s < %3$s.*/
	public static boolean between(char n, char a1, char a2) {
		return a1 < n && n < a2;
	}

	/** "Special" predicate: %1$s must be: %2$s < %1$s < %3$s. */
	public static @Nullable String between$(char n, char a1, char a2) {
		return between(n, a1, a2) ? null : String.format("%1$s must be: %2$s < %1$s < %3$s.", n, a1, a2);
	}
	/** Predicate: %1$s must NOT be: %2$s < %1$s < %3$s..*/
	public static boolean notBetween(char n, char a1, char a2) {
		return !between(n, a1, a2);
	}

	/** "Special" predicate: %1$s must NOT be: %2$s < %1$s < %3$s. */
	public static @Nullable String notBetween$(char n, char a1, char a2) {
		return notBetween(n, a1, a2) ? null : String.format("%1$s must NOT be: %2$s < %1$s < %3$s.", n, a1, a2);
	}

	/** Predicate: %1$s must be: %2$s <= %1$s <= %3$s.*/
	public static boolean inRange(char n, char a1, char a2) {
		return a1 <= n && n <= a2;
	}

	/** "Special" predicate: %1$s must be: %2$s <= %1$s <= %3$s. */
	public static @Nullable String inRange$(char n, char a1, char a2) {
		return inRange(n, a1, a2) ? null : String.format("%1$s must be: %2$s <= %1$s <= %3$s.", n, a1, a2);
	}
	/** Predicate: %1$s must NOT be: %2$s <= %1$s <= %3$s..*/
	public static boolean notInRange(char n, char a1, char a2) {
		return !inRange(n, a1, a2);
	}

	/** "Special" predicate: %1$s must NOT be: %2$s <= %1$s <= %3$s. */
	public static @Nullable String notInRange$(char n, char a1, char a2) {
		return notInRange(n, a1, a2) ? null : String.format("%1$s must NOT be: %2$s <= %1$s <= %3$s.", n, a1, a2);
	}

	/** Predicate: <%s> must be true.*/
	public static boolean True(boolean v) {
		return v;
	}

	/** "Special" predicate: <%s> must be true. */
	public static @Nullable String True$(boolean v) {
		return True(v) ? null : String.format("<%s> must be true.", v);
	}
	/** Predicate: <%s> must be false..*/
	public static boolean False(boolean v) {
		return !True(v);
	}

	/** "Special" predicate: <%s> must be false. */
	public static @Nullable String False$(boolean v) {
		return False(v) ? null : String.format("<%s> must be false.", v);
	}

	// </editor-fold>

	// <editor-fold desc="arrays">

	/** Predicate: Array <%s> must be of size %s.*/
	public static boolean length(@Nonnull boolean[] array, int i) {
		Null.nonNullArg(array, "array");
		return Array.getLength(array) == i;
	}

	/** "Special" predicate: Array <%s> must be of size %s. */
	public static @Nullable String length$(@Nonnull boolean[] array, int i) {
		Null.nonNullArg(array, "array");
		return length(array, i) ? null : String.format("Array <%s> must be of size %s.", Arrays.toString(array), i);
	}
	/** Predicate: Array <%s> must NOT be of size %s..*/
	public static boolean lengthOtherThan(@Nonnull boolean[] array, int i) {
		Null.nonNullArg(array, "array");
		return !length(array, i);
	}

	/** "Special" predicate: Array <%s> must NOT be of size %s. */
	public static @Nullable String lengthOtherThan$(@Nonnull boolean[] array, int i) {
		Null.nonNullArg(array, "array");
		return lengthOtherThan(array, i) ? null : String.format("Array <%s> must NOT be of size %s.", Arrays.toString(array), i);
	}

	/** Predicate: Array <%s> must be of size %s.*/
	public static boolean ofLength(@Nonnull boolean[] array, int i) {
		Null.nonNullArg(array, "array");
		return Array.getLength(array) == i;
	}

	/** "Special" predicate: Array <%s> must be of size %s. */
	public static @Nullable String ofLength$(@Nonnull boolean[] array, int i) {
		Null.nonNullArg(array, "array");
		return ofLength(array, i) ? null : String.format("Array <%s> must be of size %s.", Arrays.toString(array), i);
	}
	/** Predicate: Array <%s> must NOT be of size %s..*/
	public static boolean notOfLength(@Nonnull boolean[] array, int i) {
		Null.nonNullArg(array, "array");
		return !ofLength(array, i);
	}

	/** "Special" predicate: Array <%s> must NOT be of size %s. */
	public static @Nullable String notOfLength$(@Nonnull boolean[] array, int i) {
		Null.nonNullArg(array, "array");
		return notOfLength(array, i) ? null : String.format("Array <%s> must NOT be of size %s.", Arrays.toString(array), i);
	}

	/** Predicate: Array <%s> must be empty.*/
	public static boolean empty(@Nonnull boolean[] array) {
		Null.nonNullArg(array, "array");
		return Array.getLength(array) == 0;
	}

	/** "Special" predicate: Array <%s> must be empty. */
	public static @Nullable String empty$(@Nonnull boolean[] array) {
		Null.nonNullArg(array, "array");
		return empty(array) ? null : String.format("Array <%s> must be empty.", Arrays.toString(array));
	}
	/** Predicate: Array <%s> must NOT be empty..*/
	public static boolean notEmpty(@Nonnull boolean[] array) {
		Null.nonNullArg(array, "array");
		return !empty(array);
	}

	/** "Special" predicate: Array <%s> must NOT be empty. */
	public static @Nullable String notEmpty$(@Nonnull boolean[] array) {
		Null.nonNullArg(array, "array");
		return notEmpty(array) ? null : String.format("Array <%s> must NOT be empty.", Arrays.toString(array));
	}

	/** Predicate: Array <%s> must be null or empty.*/
	public static boolean nullOrEmpty(@Nullable boolean[] array) {
		return array == null || Array.getLength(array) == 0;
	}

	/** "Special" predicate: Array <%s> must be null or empty. */
	public static @Nullable String nullOrEmpty$(@Nullable boolean[] array) {
		return nullOrEmpty(array) ? null : String.format("Array <%s> must be null or empty.", Arrays.toString(array));
	}
	/** Predicate: Array <%s> must NOT be null or empty..*/
	public static boolean notNullNotEmpty(@Nullable boolean[] array) {
		return !nullOrEmpty(array);
	}

	/** "Special" predicate: Array <%s> must NOT be null or empty. */
	public static @Nullable String notNullNotEmpty$(@Nullable boolean[] array) {
		return notNullNotEmpty(array) ? null : String.format("Array <%s> must NOT be null or empty.", Arrays.toString(array));
	}

	/** Predicate: Array <%s> must be exactly of size 1 (singleton).*/
	public static boolean singleton(@Nonnull boolean[] array) {
		Null.nonNullArg(array, "array");
		return Array.getLength(array) == 1;
	}

	/** "Special" predicate: Array <%s> must be exactly of size 1 (singleton). */
	public static @Nullable String singleton$(@Nonnull boolean[] array) {
		Null.nonNullArg(array, "array");
		return singleton(array) ? null : String.format("Array <%s> must be exactly of size 1 (singleton).", Arrays.toString(array));
	}
	/** Predicate: Array <%s> must NOT be exactly of size 1 (singleton)..*/
	public static boolean notSingleton(@Nonnull boolean[] array) {
		Null.nonNullArg(array, "array");
		return !singleton(array);
	}

	/** "Special" predicate: Array <%s> must NOT be exactly of size 1 (singleton). */
	public static @Nullable String notSingleton$(@Nonnull boolean[] array) {
		Null.nonNullArg(array, "array");
		return notSingleton(array) ? null : String.format("Array <%s> must NOT be exactly of size 1 (singleton).", Arrays.toString(array));
	}

	/** Predicate: Array <%s> must be equal to array <%s>.*/
	public static boolean equal(boolean[] a1, boolean[] a2) {
		return Arrays.equals(a1, a2);
	}

	/** "Special" predicate: Array <%s> must be equal to array <%s>. */
	public static @Nullable String equal$(boolean[] a1, boolean[] a2) {
		return equal(a1, a2) ? null : String.format("Array <%s> must be equal to array <%s>.", Arrays.toString(a1), Arrays.toString(a2));
	}
	/** Predicate: Array <%s> must NOT be equal to array <%s>..*/
	public static boolean notEqual(boolean[] a1, boolean[] a2) {
		return !equal(a1, a2);
	}

	/** "Special" predicate: Array <%s> must NOT be equal to array <%s>. */
	public static @Nullable String notEqual$(boolean[] a1, boolean[] a2) {
		return notEqual(a1, a2) ? null : String.format("Array <%s> must NOT be equal to array <%s>.", Arrays.toString(a1), Arrays.toString(a2));
	}

	static boolean containsExactly_privately(@Nonnull boolean[] array, boolean... elementsInOrder) {

		int size = array.length;
		if (size != elementsInOrder.length) { // fast track
			return false;
		}

		for (int i = 0; i < size; i++) {
			if (!equal(array[i], elementsInOrder[i])) {
				return false;
			}
		}

		return true;
	}

	/** Predicate: Array <%s> must contain exactly elements in order: <%s>.*/
	public static <T> boolean containExactly(@Nonnull boolean[] array, boolean... elementsInOrder) {
		Null.nonNullArg(array, "array");
		Null.nonNullArg(elementsInOrder, "elementsInOrder");
		return containsExactly_privately(array, elementsInOrder);
	}

	/** "Special" predicate: Array <%s> must contain exactly elements in order: <%s>. */
	public static <T> @Nullable String containExactly$(@Nonnull boolean[] array, boolean... elementsInOrder) {
		Null.nonNullArg(array, "array");
		Null.nonNullArg(elementsInOrder, "elementsInOrder");
		return containExactly(array, elementsInOrder) ? null : String.format("Array <%s> must contain exactly elements in order: <%s>.", Arrays.toString(array), Arrays.toString(elementsInOrder));
	}
	/** Predicate: Array <%s> must NOT contain exactly elements in order: <%s>..*/
	public static <T> boolean notContainExactly(@Nonnull boolean[] array, boolean... elementsInOrder) {
		Null.nonNullArg(array, "array");
		Null.nonNullArg(elementsInOrder, "elementsInOrder");
		return !containExactly(array, elementsInOrder);
	}

	/** "Special" predicate: Array <%s> must NOT contain exactly elements in order: <%s>. */
	public static <T> @Nullable String notContainExactly$(@Nonnull boolean[] array, boolean... elementsInOrder) {
		Null.nonNullArg(array, "array");
		Null.nonNullArg(elementsInOrder, "elementsInOrder");
		return notContainExactly(array, elementsInOrder) ? null : String.format("Array <%s> must NOT contain exactly elements in order: <%s>.", Arrays.toString(array), Arrays.toString(elementsInOrder));
	}

	/** Predicate: Array <%s> must be of size %s.*/
	public static boolean length(@Nonnull byte[] array, int i) {
		Null.nonNullArg(array, "array");
		return Array.getLength(array) == i;
	}

	/** "Special" predicate: Array <%s> must be of size %s. */
	public static @Nullable String length$(@Nonnull byte[] array, int i) {
		Null.nonNullArg(array, "array");
		return length(array, i) ? null : String.format("Array <%s> must be of size %s.", Arrays.toString(array), i);
	}
	/** Predicate: Array <%s> must NOT be of size %s..*/
	public static boolean lengthOtherThan(@Nonnull byte[] array, int i) {
		Null.nonNullArg(array, "array");
		return !length(array, i);
	}

	/** "Special" predicate: Array <%s> must NOT be of size %s. */
	public static @Nullable String lengthOtherThan$(@Nonnull byte[] array, int i) {
		Null.nonNullArg(array, "array");
		return lengthOtherThan(array, i) ? null : String.format("Array <%s> must NOT be of size %s.", Arrays.toString(array), i);
	}

	/** Predicate: Array <%s> must be of size %s.*/
	public static boolean ofLength(@Nonnull byte[] array, int i) {
		Null.nonNullArg(array, "array");
		return Array.getLength(array) == i;
	}

	/** "Special" predicate: Array <%s> must be of size %s. */
	public static @Nullable String ofLength$(@Nonnull byte[] array, int i) {
		Null.nonNullArg(array, "array");
		return ofLength(array, i) ? null : String.format("Array <%s> must be of size %s.", Arrays.toString(array), i);
	}
	/** Predicate: Array <%s> must NOT be of size %s..*/
	public static boolean notOfLength(@Nonnull byte[] array, int i) {
		Null.nonNullArg(array, "array");
		return !ofLength(array, i);
	}

	/** "Special" predicate: Array <%s> must NOT be of size %s. */
	public static @Nullable String notOfLength$(@Nonnull byte[] array, int i) {
		Null.nonNullArg(array, "array");
		return notOfLength(array, i) ? null : String.format("Array <%s> must NOT be of size %s.", Arrays.toString(array), i);
	}

	/** Predicate: Array <%s> must be empty.*/
	public static boolean empty(@Nonnull byte[] array) {
		Null.nonNullArg(array, "array");
		return Array.getLength(array) == 0;
	}

	/** "Special" predicate: Array <%s> must be empty. */
	public static @Nullable String empty$(@Nonnull byte[] array) {
		Null.nonNullArg(array, "array");
		return empty(array) ? null : String.format("Array <%s> must be empty.", Arrays.toString(array));
	}
	/** Predicate: Array <%s> must NOT be empty..*/
	public static boolean notEmpty(@Nonnull byte[] array) {
		Null.nonNullArg(array, "array");
		return !empty(array);
	}

	/** "Special" predicate: Array <%s> must NOT be empty. */
	public static @Nullable String notEmpty$(@Nonnull byte[] array) {
		Null.nonNullArg(array, "array");
		return notEmpty(array) ? null : String.format("Array <%s> must NOT be empty.", Arrays.toString(array));
	}

	/** Predicate: Array <%s> must be null or empty.*/
	public static boolean nullOrEmpty(@Nullable byte[] array) {
		return array == null || Array.getLength(array) == 0;
	}

	/** "Special" predicate: Array <%s> must be null or empty. */
	public static @Nullable String nullOrEmpty$(@Nullable byte[] array) {
		return nullOrEmpty(array) ? null : String.format("Array <%s> must be null or empty.", Arrays.toString(array));
	}
	/** Predicate: Array <%s> must NOT be null or empty..*/
	public static boolean notNullNotEmpty(@Nullable byte[] array) {
		return !nullOrEmpty(array);
	}

	/** "Special" predicate: Array <%s> must NOT be null or empty. */
	public static @Nullable String notNullNotEmpty$(@Nullable byte[] array) {
		return notNullNotEmpty(array) ? null : String.format("Array <%s> must NOT be null or empty.", Arrays.toString(array));
	}

	/** Predicate: Array <%s> must be exactly of size 1 (singleton).*/
	public static boolean singleton(@Nonnull byte[] array) {
		Null.nonNullArg(array, "array");
		return Array.getLength(array) == 1;
	}

	/** "Special" predicate: Array <%s> must be exactly of size 1 (singleton). */
	public static @Nullable String singleton$(@Nonnull byte[] array) {
		Null.nonNullArg(array, "array");
		return singleton(array) ? null : String.format("Array <%s> must be exactly of size 1 (singleton).", Arrays.toString(array));
	}
	/** Predicate: Array <%s> must NOT be exactly of size 1 (singleton)..*/
	public static boolean notSingleton(@Nonnull byte[] array) {
		Null.nonNullArg(array, "array");
		return !singleton(array);
	}

	/** "Special" predicate: Array <%s> must NOT be exactly of size 1 (singleton). */
	public static @Nullable String notSingleton$(@Nonnull byte[] array) {
		Null.nonNullArg(array, "array");
		return notSingleton(array) ? null : String.format("Array <%s> must NOT be exactly of size 1 (singleton).", Arrays.toString(array));
	}

	/** Predicate: Array <%s> must be equal to array <%s>.*/
	public static boolean equal(byte[] a1, byte[] a2) {
		return Arrays.equals(a1, a2);
	}

	/** "Special" predicate: Array <%s> must be equal to array <%s>. */
	public static @Nullable String equal$(byte[] a1, byte[] a2) {
		return equal(a1, a2) ? null : String.format("Array <%s> must be equal to array <%s>.", Arrays.toString(a1), Arrays.toString(a2));
	}
	/** Predicate: Array <%s> must NOT be equal to array <%s>..*/
	public static boolean notEqual(byte[] a1, byte[] a2) {
		return !equal(a1, a2);
	}

	/** "Special" predicate: Array <%s> must NOT be equal to array <%s>. */
	public static @Nullable String notEqual$(byte[] a1, byte[] a2) {
		return notEqual(a1, a2) ? null : String.format("Array <%s> must NOT be equal to array <%s>.", Arrays.toString(a1), Arrays.toString(a2));
	}

	static boolean containsExactly_privately(@Nonnull byte[] array, byte... elementsInOrder) {

		int size = array.length;
		if (size != elementsInOrder.length) { // fast track
			return false;
		}

		for (int i = 0; i < size; i++) {
			if (!equal(array[i], elementsInOrder[i])) {
				return false;
			}
		}

		return true;
	}

	/** Predicate: Array <%s> must contain exactly elements in order: <%s>.*/
	public static <T> boolean containExactly(@Nonnull byte[] array, byte... elementsInOrder) {
		Null.nonNullArg(array, "array");
		Null.nonNullArg(elementsInOrder, "elementsInOrder");
		return containsExactly_privately(array, elementsInOrder);
	}

	/** "Special" predicate: Array <%s> must contain exactly elements in order: <%s>. */
	public static <T> @Nullable String containExactly$(@Nonnull byte[] array, byte... elementsInOrder) {
		Null.nonNullArg(array, "array");
		Null.nonNullArg(elementsInOrder, "elementsInOrder");
		return containExactly(array, elementsInOrder) ? null : String.format("Array <%s> must contain exactly elements in order: <%s>.", Arrays.toString(array), Arrays.toString(elementsInOrder));
	}
	/** Predicate: Array <%s> must NOT contain exactly elements in order: <%s>..*/
	public static <T> boolean notContainExactly(@Nonnull byte[] array, byte... elementsInOrder) {
		Null.nonNullArg(array, "array");
		Null.nonNullArg(elementsInOrder, "elementsInOrder");
		return !containExactly(array, elementsInOrder);
	}

	/** "Special" predicate: Array <%s> must NOT contain exactly elements in order: <%s>. */
	public static <T> @Nullable String notContainExactly$(@Nonnull byte[] array, byte... elementsInOrder) {
		Null.nonNullArg(array, "array");
		Null.nonNullArg(elementsInOrder, "elementsInOrder");
		return notContainExactly(array, elementsInOrder) ? null : String.format("Array <%s> must NOT contain exactly elements in order: <%s>.", Arrays.toString(array), Arrays.toString(elementsInOrder));
	}

	/** Predicate: Array <%s> must be of size %s.*/
	public static boolean length(@Nonnull double[] array, int i) {
		Null.nonNullArg(array, "array");
		return Array.getLength(array) == i;
	}

	/** "Special" predicate: Array <%s> must be of size %s. */
	public static @Nullable String length$(@Nonnull double[] array, int i) {
		Null.nonNullArg(array, "array");
		return length(array, i) ? null : String.format("Array <%s> must be of size %s.", Arrays.toString(array), i);
	}
	/** Predicate: Array <%s> must NOT be of size %s..*/
	public static boolean lengthOtherThan(@Nonnull double[] array, int i) {
		Null.nonNullArg(array, "array");
		return !length(array, i);
	}

	/** "Special" predicate: Array <%s> must NOT be of size %s. */
	public static @Nullable String lengthOtherThan$(@Nonnull double[] array, int i) {
		Null.nonNullArg(array, "array");
		return lengthOtherThan(array, i) ? null : String.format("Array <%s> must NOT be of size %s.", Arrays.toString(array), i);
	}

	/** Predicate: Array <%s> must be of size %s.*/
	public static boolean ofLength(@Nonnull double[] array, int i) {
		Null.nonNullArg(array, "array");
		return Array.getLength(array) == i;
	}

	/** "Special" predicate: Array <%s> must be of size %s. */
	public static @Nullable String ofLength$(@Nonnull double[] array, int i) {
		Null.nonNullArg(array, "array");
		return ofLength(array, i) ? null : String.format("Array <%s> must be of size %s.", Arrays.toString(array), i);
	}
	/** Predicate: Array <%s> must NOT be of size %s..*/
	public static boolean notOfLength(@Nonnull double[] array, int i) {
		Null.nonNullArg(array, "array");
		return !ofLength(array, i);
	}

	/** "Special" predicate: Array <%s> must NOT be of size %s. */
	public static @Nullable String notOfLength$(@Nonnull double[] array, int i) {
		Null.nonNullArg(array, "array");
		return notOfLength(array, i) ? null : String.format("Array <%s> must NOT be of size %s.", Arrays.toString(array), i);
	}

	/** Predicate: Array <%s> must be empty.*/
	public static boolean empty(@Nonnull double[] array) {
		Null.nonNullArg(array, "array");
		return Array.getLength(array) == 0;
	}

	/** "Special" predicate: Array <%s> must be empty. */
	public static @Nullable String empty$(@Nonnull double[] array) {
		Null.nonNullArg(array, "array");
		return empty(array) ? null : String.format("Array <%s> must be empty.", Arrays.toString(array));
	}
	/** Predicate: Array <%s> must NOT be empty..*/
	public static boolean notEmpty(@Nonnull double[] array) {
		Null.nonNullArg(array, "array");
		return !empty(array);
	}

	/** "Special" predicate: Array <%s> must NOT be empty. */
	public static @Nullable String notEmpty$(@Nonnull double[] array) {
		Null.nonNullArg(array, "array");
		return notEmpty(array) ? null : String.format("Array <%s> must NOT be empty.", Arrays.toString(array));
	}

	/** Predicate: Array <%s> must be null or empty.*/
	public static boolean nullOrEmpty(@Nullable double[] array) {
		return array == null || Array.getLength(array) == 0;
	}

	/** "Special" predicate: Array <%s> must be null or empty. */
	public static @Nullable String nullOrEmpty$(@Nullable double[] array) {
		return nullOrEmpty(array) ? null : String.format("Array <%s> must be null or empty.", Arrays.toString(array));
	}
	/** Predicate: Array <%s> must NOT be null or empty..*/
	public static boolean notNullNotEmpty(@Nullable double[] array) {
		return !nullOrEmpty(array);
	}

	/** "Special" predicate: Array <%s> must NOT be null or empty. */
	public static @Nullable String notNullNotEmpty$(@Nullable double[] array) {
		return notNullNotEmpty(array) ? null : String.format("Array <%s> must NOT be null or empty.", Arrays.toString(array));
	}

	/** Predicate: Array <%s> must be exactly of size 1 (singleton).*/
	public static boolean singleton(@Nonnull double[] array) {
		Null.nonNullArg(array, "array");
		return Array.getLength(array) == 1;
	}

	/** "Special" predicate: Array <%s> must be exactly of size 1 (singleton). */
	public static @Nullable String singleton$(@Nonnull double[] array) {
		Null.nonNullArg(array, "array");
		return singleton(array) ? null : String.format("Array <%s> must be exactly of size 1 (singleton).", Arrays.toString(array));
	}
	/** Predicate: Array <%s> must NOT be exactly of size 1 (singleton)..*/
	public static boolean notSingleton(@Nonnull double[] array) {
		Null.nonNullArg(array, "array");
		return !singleton(array);
	}

	/** "Special" predicate: Array <%s> must NOT be exactly of size 1 (singleton). */
	public static @Nullable String notSingleton$(@Nonnull double[] array) {
		Null.nonNullArg(array, "array");
		return notSingleton(array) ? null : String.format("Array <%s> must NOT be exactly of size 1 (singleton).", Arrays.toString(array));
	}

	/** Predicate: Array <%s> must be equal to array <%s>.*/
	public static boolean equal(double[] a1, double[] a2) {
		return Arrays.equals(a1, a2);
	}

	/** "Special" predicate: Array <%s> must be equal to array <%s>. */
	public static @Nullable String equal$(double[] a1, double[] a2) {
		return equal(a1, a2) ? null : String.format("Array <%s> must be equal to array <%s>.", Arrays.toString(a1), Arrays.toString(a2));
	}
	/** Predicate: Array <%s> must NOT be equal to array <%s>..*/
	public static boolean notEqual(double[] a1, double[] a2) {
		return !equal(a1, a2);
	}

	/** "Special" predicate: Array <%s> must NOT be equal to array <%s>. */
	public static @Nullable String notEqual$(double[] a1, double[] a2) {
		return notEqual(a1, a2) ? null : String.format("Array <%s> must NOT be equal to array <%s>.", Arrays.toString(a1), Arrays.toString(a2));
	}

	static boolean containsExactly_privately(@Nonnull double[] array, double... elementsInOrder) {

		int size = array.length;
		if (size != elementsInOrder.length) { // fast track
			return false;
		}

		for (int i = 0; i < size; i++) {
			if (!equal(array[i], elementsInOrder[i])) {
				return false;
			}
		}

		return true;
	}

	/** Predicate: Array <%s> must contain exactly elements in order: <%s>.*/
	public static <T> boolean containExactly(@Nonnull double[] array, double... elementsInOrder) {
		Null.nonNullArg(array, "array");
		Null.nonNullArg(elementsInOrder, "elementsInOrder");
		return containsExactly_privately(array, elementsInOrder);
	}

	/** "Special" predicate: Array <%s> must contain exactly elements in order: <%s>. */
	public static <T> @Nullable String containExactly$(@Nonnull double[] array, double... elementsInOrder) {
		Null.nonNullArg(array, "array");
		Null.nonNullArg(elementsInOrder, "elementsInOrder");
		return containExactly(array, elementsInOrder) ? null : String.format("Array <%s> must contain exactly elements in order: <%s>.", Arrays.toString(array), Arrays.toString(elementsInOrder));
	}
	/** Predicate: Array <%s> must NOT contain exactly elements in order: <%s>..*/
	public static <T> boolean notContainExactly(@Nonnull double[] array, double... elementsInOrder) {
		Null.nonNullArg(array, "array");
		Null.nonNullArg(elementsInOrder, "elementsInOrder");
		return !containExactly(array, elementsInOrder);
	}

	/** "Special" predicate: Array <%s> must NOT contain exactly elements in order: <%s>. */
	public static <T> @Nullable String notContainExactly$(@Nonnull double[] array, double... elementsInOrder) {
		Null.nonNullArg(array, "array");
		Null.nonNullArg(elementsInOrder, "elementsInOrder");
		return notContainExactly(array, elementsInOrder) ? null : String.format("Array <%s> must NOT contain exactly elements in order: <%s>.", Arrays.toString(array), Arrays.toString(elementsInOrder));
	}

	/** Predicate: Array <%s> must be of size %s.*/
	public static boolean length(@Nonnull char[] array, int i) {
		Null.nonNullArg(array, "array");
		return Array.getLength(array) == i;
	}

	/** "Special" predicate: Array <%s> must be of size %s. */
	public static @Nullable String length$(@Nonnull char[] array, int i) {
		Null.nonNullArg(array, "array");
		return length(array, i) ? null : String.format("Array <%s> must be of size %s.", Arrays.toString(array), i);
	}
	/** Predicate: Array <%s> must NOT be of size %s..*/
	public static boolean lengthOtherThan(@Nonnull char[] array, int i) {
		Null.nonNullArg(array, "array");
		return !length(array, i);
	}

	/** "Special" predicate: Array <%s> must NOT be of size %s. */
	public static @Nullable String lengthOtherThan$(@Nonnull char[] array, int i) {
		Null.nonNullArg(array, "array");
		return lengthOtherThan(array, i) ? null : String.format("Array <%s> must NOT be of size %s.", Arrays.toString(array), i);
	}

	/** Predicate: Array <%s> must be of size %s.*/
	public static boolean ofLength(@Nonnull char[] array, int i) {
		Null.nonNullArg(array, "array");
		return Array.getLength(array) == i;
	}

	/** "Special" predicate: Array <%s> must be of size %s. */
	public static @Nullable String ofLength$(@Nonnull char[] array, int i) {
		Null.nonNullArg(array, "array");
		return ofLength(array, i) ? null : String.format("Array <%s> must be of size %s.", Arrays.toString(array), i);
	}
	/** Predicate: Array <%s> must NOT be of size %s..*/
	public static boolean notOfLength(@Nonnull char[] array, int i) {
		Null.nonNullArg(array, "array");
		return !ofLength(array, i);
	}

	/** "Special" predicate: Array <%s> must NOT be of size %s. */
	public static @Nullable String notOfLength$(@Nonnull char[] array, int i) {
		Null.nonNullArg(array, "array");
		return notOfLength(array, i) ? null : String.format("Array <%s> must NOT be of size %s.", Arrays.toString(array), i);
	}

	/** Predicate: Array <%s> must be empty.*/
	public static boolean empty(@Nonnull char[] array) {
		Null.nonNullArg(array, "array");
		return Array.getLength(array) == 0;
	}

	/** "Special" predicate: Array <%s> must be empty. */
	public static @Nullable String empty$(@Nonnull char[] array) {
		Null.nonNullArg(array, "array");
		return empty(array) ? null : String.format("Array <%s> must be empty.", Arrays.toString(array));
	}
	/** Predicate: Array <%s> must NOT be empty..*/
	public static boolean notEmpty(@Nonnull char[] array) {
		Null.nonNullArg(array, "array");
		return !empty(array);
	}

	/** "Special" predicate: Array <%s> must NOT be empty. */
	public static @Nullable String notEmpty$(@Nonnull char[] array) {
		Null.nonNullArg(array, "array");
		return notEmpty(array) ? null : String.format("Array <%s> must NOT be empty.", Arrays.toString(array));
	}

	/** Predicate: Array <%s> must be null or empty.*/
	public static boolean nullOrEmpty(@Nullable char[] array) {
		return array == null || Array.getLength(array) == 0;
	}

	/** "Special" predicate: Array <%s> must be null or empty. */
	public static @Nullable String nullOrEmpty$(@Nullable char[] array) {
		return nullOrEmpty(array) ? null : String.format("Array <%s> must be null or empty.", Arrays.toString(array));
	}
	/** Predicate: Array <%s> must NOT be null or empty..*/
	public static boolean notNullNotEmpty(@Nullable char[] array) {
		return !nullOrEmpty(array);
	}

	/** "Special" predicate: Array <%s> must NOT be null or empty. */
	public static @Nullable String notNullNotEmpty$(@Nullable char[] array) {
		return notNullNotEmpty(array) ? null : String.format("Array <%s> must NOT be null or empty.", Arrays.toString(array));
	}

	/** Predicate: Array <%s> must be exactly of size 1 (singleton).*/
	public static boolean singleton(@Nonnull char[] array) {
		Null.nonNullArg(array, "array");
		return Array.getLength(array) == 1;
	}

	/** "Special" predicate: Array <%s> must be exactly of size 1 (singleton). */
	public static @Nullable String singleton$(@Nonnull char[] array) {
		Null.nonNullArg(array, "array");
		return singleton(array) ? null : String.format("Array <%s> must be exactly of size 1 (singleton).", Arrays.toString(array));
	}
	/** Predicate: Array <%s> must NOT be exactly of size 1 (singleton)..*/
	public static boolean notSingleton(@Nonnull char[] array) {
		Null.nonNullArg(array, "array");
		return !singleton(array);
	}

	/** "Special" predicate: Array <%s> must NOT be exactly of size 1 (singleton). */
	public static @Nullable String notSingleton$(@Nonnull char[] array) {
		Null.nonNullArg(array, "array");
		return notSingleton(array) ? null : String.format("Array <%s> must NOT be exactly of size 1 (singleton).", Arrays.toString(array));
	}

	/** Predicate: Array <%s> must be equal to array <%s>.*/
	public static boolean equal(char[] a1, char[] a2) {
		return Arrays.equals(a1, a2);
	}

	/** "Special" predicate: Array <%s> must be equal to array <%s>. */
	public static @Nullable String equal$(char[] a1, char[] a2) {
		return equal(a1, a2) ? null : String.format("Array <%s> must be equal to array <%s>.", Arrays.toString(a1), Arrays.toString(a2));
	}
	/** Predicate: Array <%s> must NOT be equal to array <%s>..*/
	public static boolean notEqual(char[] a1, char[] a2) {
		return !equal(a1, a2);
	}

	/** "Special" predicate: Array <%s> must NOT be equal to array <%s>. */
	public static @Nullable String notEqual$(char[] a1, char[] a2) {
		return notEqual(a1, a2) ? null : String.format("Array <%s> must NOT be equal to array <%s>.", Arrays.toString(a1), Arrays.toString(a2));
	}

	static boolean containsExactly_privately(@Nonnull char[] array, char... elementsInOrder) {

		int size = array.length;
		if (size != elementsInOrder.length) { // fast track
			return false;
		}

		for (int i = 0; i < size; i++) {
			if (!equal(array[i], elementsInOrder[i])) {
				return false;
			}
		}

		return true;
	}

	/** Predicate: Array <%s> must contain exactly elements in order: <%s>.*/
	public static <T> boolean containExactly(@Nonnull char[] array, char... elementsInOrder) {
		Null.nonNullArg(array, "array");
		Null.nonNullArg(elementsInOrder, "elementsInOrder");
		return containsExactly_privately(array, elementsInOrder);
	}

	/** "Special" predicate: Array <%s> must contain exactly elements in order: <%s>. */
	public static <T> @Nullable String containExactly$(@Nonnull char[] array, char... elementsInOrder) {
		Null.nonNullArg(array, "array");
		Null.nonNullArg(elementsInOrder, "elementsInOrder");
		return containExactly(array, elementsInOrder) ? null : String.format("Array <%s> must contain exactly elements in order: <%s>.", Arrays.toString(array), Arrays.toString(elementsInOrder));
	}
	/** Predicate: Array <%s> must NOT contain exactly elements in order: <%s>..*/
	public static <T> boolean notContainExactly(@Nonnull char[] array, char... elementsInOrder) {
		Null.nonNullArg(array, "array");
		Null.nonNullArg(elementsInOrder, "elementsInOrder");
		return !containExactly(array, elementsInOrder);
	}

	/** "Special" predicate: Array <%s> must NOT contain exactly elements in order: <%s>. */
	public static <T> @Nullable String notContainExactly$(@Nonnull char[] array, char... elementsInOrder) {
		Null.nonNullArg(array, "array");
		Null.nonNullArg(elementsInOrder, "elementsInOrder");
		return notContainExactly(array, elementsInOrder) ? null : String.format("Array <%s> must NOT contain exactly elements in order: <%s>.", Arrays.toString(array), Arrays.toString(elementsInOrder));
	}

	/** Predicate: Array <%s> must be of size %s.*/
	public static boolean length(@Nonnull short[] array, int i) {
		Null.nonNullArg(array, "array");
		return Array.getLength(array) == i;
	}

	/** "Special" predicate: Array <%s> must be of size %s. */
	public static @Nullable String length$(@Nonnull short[] array, int i) {
		Null.nonNullArg(array, "array");
		return length(array, i) ? null : String.format("Array <%s> must be of size %s.", Arrays.toString(array), i);
	}
	/** Predicate: Array <%s> must NOT be of size %s..*/
	public static boolean lengthOtherThan(@Nonnull short[] array, int i) {
		Null.nonNullArg(array, "array");
		return !length(array, i);
	}

	/** "Special" predicate: Array <%s> must NOT be of size %s. */
	public static @Nullable String lengthOtherThan$(@Nonnull short[] array, int i) {
		Null.nonNullArg(array, "array");
		return lengthOtherThan(array, i) ? null : String.format("Array <%s> must NOT be of size %s.", Arrays.toString(array), i);
	}

	/** Predicate: Array <%s> must be of size %s.*/
	public static boolean ofLength(@Nonnull short[] array, int i) {
		Null.nonNullArg(array, "array");
		return Array.getLength(array) == i;
	}

	/** "Special" predicate: Array <%s> must be of size %s. */
	public static @Nullable String ofLength$(@Nonnull short[] array, int i) {
		Null.nonNullArg(array, "array");
		return ofLength(array, i) ? null : String.format("Array <%s> must be of size %s.", Arrays.toString(array), i);
	}
	/** Predicate: Array <%s> must NOT be of size %s..*/
	public static boolean notOfLength(@Nonnull short[] array, int i) {
		Null.nonNullArg(array, "array");
		return !ofLength(array, i);
	}

	/** "Special" predicate: Array <%s> must NOT be of size %s. */
	public static @Nullable String notOfLength$(@Nonnull short[] array, int i) {
		Null.nonNullArg(array, "array");
		return notOfLength(array, i) ? null : String.format("Array <%s> must NOT be of size %s.", Arrays.toString(array), i);
	}

	/** Predicate: Array <%s> must be empty.*/
	public static boolean empty(@Nonnull short[] array) {
		Null.nonNullArg(array, "array");
		return Array.getLength(array) == 0;
	}

	/** "Special" predicate: Array <%s> must be empty. */
	public static @Nullable String empty$(@Nonnull short[] array) {
		Null.nonNullArg(array, "array");
		return empty(array) ? null : String.format("Array <%s> must be empty.", Arrays.toString(array));
	}
	/** Predicate: Array <%s> must NOT be empty..*/
	public static boolean notEmpty(@Nonnull short[] array) {
		Null.nonNullArg(array, "array");
		return !empty(array);
	}

	/** "Special" predicate: Array <%s> must NOT be empty. */
	public static @Nullable String notEmpty$(@Nonnull short[] array) {
		Null.nonNullArg(array, "array");
		return notEmpty(array) ? null : String.format("Array <%s> must NOT be empty.", Arrays.toString(array));
	}

	/** Predicate: Array <%s> must be null or empty.*/
	public static boolean nullOrEmpty(@Nullable short[] array) {
		return array == null || Array.getLength(array) == 0;
	}

	/** "Special" predicate: Array <%s> must be null or empty. */
	public static @Nullable String nullOrEmpty$(@Nullable short[] array) {
		return nullOrEmpty(array) ? null : String.format("Array <%s> must be null or empty.", Arrays.toString(array));
	}
	/** Predicate: Array <%s> must NOT be null or empty..*/
	public static boolean notNullNotEmpty(@Nullable short[] array) {
		return !nullOrEmpty(array);
	}

	/** "Special" predicate: Array <%s> must NOT be null or empty. */
	public static @Nullable String notNullNotEmpty$(@Nullable short[] array) {
		return notNullNotEmpty(array) ? null : String.format("Array <%s> must NOT be null or empty.", Arrays.toString(array));
	}

	/** Predicate: Array <%s> must be exactly of size 1 (singleton).*/
	public static boolean singleton(@Nonnull short[] array) {
		Null.nonNullArg(array, "array");
		return Array.getLength(array) == 1;
	}

	/** "Special" predicate: Array <%s> must be exactly of size 1 (singleton). */
	public static @Nullable String singleton$(@Nonnull short[] array) {
		Null.nonNullArg(array, "array");
		return singleton(array) ? null : String.format("Array <%s> must be exactly of size 1 (singleton).", Arrays.toString(array));
	}
	/** Predicate: Array <%s> must NOT be exactly of size 1 (singleton)..*/
	public static boolean notSingleton(@Nonnull short[] array) {
		Null.nonNullArg(array, "array");
		return !singleton(array);
	}

	/** "Special" predicate: Array <%s> must NOT be exactly of size 1 (singleton). */
	public static @Nullable String notSingleton$(@Nonnull short[] array) {
		Null.nonNullArg(array, "array");
		return notSingleton(array) ? null : String.format("Array <%s> must NOT be exactly of size 1 (singleton).", Arrays.toString(array));
	}

	/** Predicate: Array <%s> must be equal to array <%s>.*/
	public static boolean equal(short[] a1, short[] a2) {
		return Arrays.equals(a1, a2);
	}

	/** "Special" predicate: Array <%s> must be equal to array <%s>. */
	public static @Nullable String equal$(short[] a1, short[] a2) {
		return equal(a1, a2) ? null : String.format("Array <%s> must be equal to array <%s>.", Arrays.toString(a1), Arrays.toString(a2));
	}
	/** Predicate: Array <%s> must NOT be equal to array <%s>..*/
	public static boolean notEqual(short[] a1, short[] a2) {
		return !equal(a1, a2);
	}

	/** "Special" predicate: Array <%s> must NOT be equal to array <%s>. */
	public static @Nullable String notEqual$(short[] a1, short[] a2) {
		return notEqual(a1, a2) ? null : String.format("Array <%s> must NOT be equal to array <%s>.", Arrays.toString(a1), Arrays.toString(a2));
	}

	static boolean containsExactly_privately(@Nonnull short[] array, short... elementsInOrder) {

		int size = array.length;
		if (size != elementsInOrder.length) { // fast track
			return false;
		}

		for (int i = 0; i < size; i++) {
			if (!equal(array[i], elementsInOrder[i])) {
				return false;
			}
		}

		return true;
	}

	/** Predicate: Array <%s> must contain exactly elements in order: <%s>.*/
	public static <T> boolean containExactly(@Nonnull short[] array, short... elementsInOrder) {
		Null.nonNullArg(array, "array");
		Null.nonNullArg(elementsInOrder, "elementsInOrder");
		return containsExactly_privately(array, elementsInOrder);
	}

	/** "Special" predicate: Array <%s> must contain exactly elements in order: <%s>. */
	public static <T> @Nullable String containExactly$(@Nonnull short[] array, short... elementsInOrder) {
		Null.nonNullArg(array, "array");
		Null.nonNullArg(elementsInOrder, "elementsInOrder");
		return containExactly(array, elementsInOrder) ? null : String.format("Array <%s> must contain exactly elements in order: <%s>.", Arrays.toString(array), Arrays.toString(elementsInOrder));
	}
	/** Predicate: Array <%s> must NOT contain exactly elements in order: <%s>..*/
	public static <T> boolean notContainExactly(@Nonnull short[] array, short... elementsInOrder) {
		Null.nonNullArg(array, "array");
		Null.nonNullArg(elementsInOrder, "elementsInOrder");
		return !containExactly(array, elementsInOrder);
	}

	/** "Special" predicate: Array <%s> must NOT contain exactly elements in order: <%s>. */
	public static <T> @Nullable String notContainExactly$(@Nonnull short[] array, short... elementsInOrder) {
		Null.nonNullArg(array, "array");
		Null.nonNullArg(elementsInOrder, "elementsInOrder");
		return notContainExactly(array, elementsInOrder) ? null : String.format("Array <%s> must NOT contain exactly elements in order: <%s>.", Arrays.toString(array), Arrays.toString(elementsInOrder));
	}

	/** Predicate: Array <%s> must be of size %s.*/
	public static boolean length(@Nonnull float[] array, int i) {
		Null.nonNullArg(array, "array");
		return Array.getLength(array) == i;
	}

	/** "Special" predicate: Array <%s> must be of size %s. */
	public static @Nullable String length$(@Nonnull float[] array, int i) {
		Null.nonNullArg(array, "array");
		return length(array, i) ? null : String.format("Array <%s> must be of size %s.", Arrays.toString(array), i);
	}
	/** Predicate: Array <%s> must NOT be of size %s..*/
	public static boolean lengthOtherThan(@Nonnull float[] array, int i) {
		Null.nonNullArg(array, "array");
		return !length(array, i);
	}

	/** "Special" predicate: Array <%s> must NOT be of size %s. */
	public static @Nullable String lengthOtherThan$(@Nonnull float[] array, int i) {
		Null.nonNullArg(array, "array");
		return lengthOtherThan(array, i) ? null : String.format("Array <%s> must NOT be of size %s.", Arrays.toString(array), i);
	}

	/** Predicate: Array <%s> must be of size %s.*/
	public static boolean ofLength(@Nonnull float[] array, int i) {
		Null.nonNullArg(array, "array");
		return Array.getLength(array) == i;
	}

	/** "Special" predicate: Array <%s> must be of size %s. */
	public static @Nullable String ofLength$(@Nonnull float[] array, int i) {
		Null.nonNullArg(array, "array");
		return ofLength(array, i) ? null : String.format("Array <%s> must be of size %s.", Arrays.toString(array), i);
	}
	/** Predicate: Array <%s> must NOT be of size %s..*/
	public static boolean notOfLength(@Nonnull float[] array, int i) {
		Null.nonNullArg(array, "array");
		return !ofLength(array, i);
	}

	/** "Special" predicate: Array <%s> must NOT be of size %s. */
	public static @Nullable String notOfLength$(@Nonnull float[] array, int i) {
		Null.nonNullArg(array, "array");
		return notOfLength(array, i) ? null : String.format("Array <%s> must NOT be of size %s.", Arrays.toString(array), i);
	}

	/** Predicate: Array <%s> must be empty.*/
	public static boolean empty(@Nonnull float[] array) {
		Null.nonNullArg(array, "array");
		return Array.getLength(array) == 0;
	}

	/** "Special" predicate: Array <%s> must be empty. */
	public static @Nullable String empty$(@Nonnull float[] array) {
		Null.nonNullArg(array, "array");
		return empty(array) ? null : String.format("Array <%s> must be empty.", Arrays.toString(array));
	}
	/** Predicate: Array <%s> must NOT be empty..*/
	public static boolean notEmpty(@Nonnull float[] array) {
		Null.nonNullArg(array, "array");
		return !empty(array);
	}

	/** "Special" predicate: Array <%s> must NOT be empty. */
	public static @Nullable String notEmpty$(@Nonnull float[] array) {
		Null.nonNullArg(array, "array");
		return notEmpty(array) ? null : String.format("Array <%s> must NOT be empty.", Arrays.toString(array));
	}

	/** Predicate: Array <%s> must be null or empty.*/
	public static boolean nullOrEmpty(@Nullable float[] array) {
		return array == null || Array.getLength(array) == 0;
	}

	/** "Special" predicate: Array <%s> must be null or empty. */
	public static @Nullable String nullOrEmpty$(@Nullable float[] array) {
		return nullOrEmpty(array) ? null : String.format("Array <%s> must be null or empty.", Arrays.toString(array));
	}
	/** Predicate: Array <%s> must NOT be null or empty..*/
	public static boolean notNullNotEmpty(@Nullable float[] array) {
		return !nullOrEmpty(array);
	}

	/** "Special" predicate: Array <%s> must NOT be null or empty. */
	public static @Nullable String notNullNotEmpty$(@Nullable float[] array) {
		return notNullNotEmpty(array) ? null : String.format("Array <%s> must NOT be null or empty.", Arrays.toString(array));
	}

	/** Predicate: Array <%s> must be exactly of size 1 (singleton).*/
	public static boolean singleton(@Nonnull float[] array) {
		Null.nonNullArg(array, "array");
		return Array.getLength(array) == 1;
	}

	/** "Special" predicate: Array <%s> must be exactly of size 1 (singleton). */
	public static @Nullable String singleton$(@Nonnull float[] array) {
		Null.nonNullArg(array, "array");
		return singleton(array) ? null : String.format("Array <%s> must be exactly of size 1 (singleton).", Arrays.toString(array));
	}
	/** Predicate: Array <%s> must NOT be exactly of size 1 (singleton)..*/
	public static boolean notSingleton(@Nonnull float[] array) {
		Null.nonNullArg(array, "array");
		return !singleton(array);
	}

	/** "Special" predicate: Array <%s> must NOT be exactly of size 1 (singleton). */
	public static @Nullable String notSingleton$(@Nonnull float[] array) {
		Null.nonNullArg(array, "array");
		return notSingleton(array) ? null : String.format("Array <%s> must NOT be exactly of size 1 (singleton).", Arrays.toString(array));
	}

	/** Predicate: Array <%s> must be equal to array <%s>.*/
	public static boolean equal(float[] a1, float[] a2) {
		return Arrays.equals(a1, a2);
	}

	/** "Special" predicate: Array <%s> must be equal to array <%s>. */
	public static @Nullable String equal$(float[] a1, float[] a2) {
		return equal(a1, a2) ? null : String.format("Array <%s> must be equal to array <%s>.", Arrays.toString(a1), Arrays.toString(a2));
	}
	/** Predicate: Array <%s> must NOT be equal to array <%s>..*/
	public static boolean notEqual(float[] a1, float[] a2) {
		return !equal(a1, a2);
	}

	/** "Special" predicate: Array <%s> must NOT be equal to array <%s>. */
	public static @Nullable String notEqual$(float[] a1, float[] a2) {
		return notEqual(a1, a2) ? null : String.format("Array <%s> must NOT be equal to array <%s>.", Arrays.toString(a1), Arrays.toString(a2));
	}

	static boolean containsExactly_privately(@Nonnull float[] array, float... elementsInOrder) {

		int size = array.length;
		if (size != elementsInOrder.length) { // fast track
			return false;
		}

		for (int i = 0; i < size; i++) {
			if (!equal(array[i], elementsInOrder[i])) {
				return false;
			}
		}

		return true;
	}

	/** Predicate: Array <%s> must contain exactly elements in order: <%s>.*/
	public static <T> boolean containExactly(@Nonnull float[] array, float... elementsInOrder) {
		Null.nonNullArg(array, "array");
		Null.nonNullArg(elementsInOrder, "elementsInOrder");
		return containsExactly_privately(array, elementsInOrder);
	}

	/** "Special" predicate: Array <%s> must contain exactly elements in order: <%s>. */
	public static <T> @Nullable String containExactly$(@Nonnull float[] array, float... elementsInOrder) {
		Null.nonNullArg(array, "array");
		Null.nonNullArg(elementsInOrder, "elementsInOrder");
		return containExactly(array, elementsInOrder) ? null : String.format("Array <%s> must contain exactly elements in order: <%s>.", Arrays.toString(array), Arrays.toString(elementsInOrder));
	}
	/** Predicate: Array <%s> must NOT contain exactly elements in order: <%s>..*/
	public static <T> boolean notContainExactly(@Nonnull float[] array, float... elementsInOrder) {
		Null.nonNullArg(array, "array");
		Null.nonNullArg(elementsInOrder, "elementsInOrder");
		return !containExactly(array, elementsInOrder);
	}

	/** "Special" predicate: Array <%s> must NOT contain exactly elements in order: <%s>. */
	public static <T> @Nullable String notContainExactly$(@Nonnull float[] array, float... elementsInOrder) {
		Null.nonNullArg(array, "array");
		Null.nonNullArg(elementsInOrder, "elementsInOrder");
		return notContainExactly(array, elementsInOrder) ? null : String.format("Array <%s> must NOT contain exactly elements in order: <%s>.", Arrays.toString(array), Arrays.toString(elementsInOrder));
	}

	/** Predicate: Array <%s> must be of size %s.*/
	public static boolean length(@Nonnull int[] array, int i) {
		Null.nonNullArg(array, "array");
		return Array.getLength(array) == i;
	}

	/** "Special" predicate: Array <%s> must be of size %s. */
	public static @Nullable String length$(@Nonnull int[] array, int i) {
		Null.nonNullArg(array, "array");
		return length(array, i) ? null : String.format("Array <%s> must be of size %s.", Arrays.toString(array), i);
	}
	/** Predicate: Array <%s> must NOT be of size %s..*/
	public static boolean lengthOtherThan(@Nonnull int[] array, int i) {
		Null.nonNullArg(array, "array");
		return !length(array, i);
	}

	/** "Special" predicate: Array <%s> must NOT be of size %s. */
	public static @Nullable String lengthOtherThan$(@Nonnull int[] array, int i) {
		Null.nonNullArg(array, "array");
		return lengthOtherThan(array, i) ? null : String.format("Array <%s> must NOT be of size %s.", Arrays.toString(array), i);
	}

	/** Predicate: Array <%s> must be of size %s.*/
	public static boolean ofLength(@Nonnull int[] array, int i) {
		Null.nonNullArg(array, "array");
		return Array.getLength(array) == i;
	}

	/** "Special" predicate: Array <%s> must be of size %s. */
	public static @Nullable String ofLength$(@Nonnull int[] array, int i) {
		Null.nonNullArg(array, "array");
		return ofLength(array, i) ? null : String.format("Array <%s> must be of size %s.", Arrays.toString(array), i);
	}
	/** Predicate: Array <%s> must NOT be of size %s..*/
	public static boolean notOfLength(@Nonnull int[] array, int i) {
		Null.nonNullArg(array, "array");
		return !ofLength(array, i);
	}

	/** "Special" predicate: Array <%s> must NOT be of size %s. */
	public static @Nullable String notOfLength$(@Nonnull int[] array, int i) {
		Null.nonNullArg(array, "array");
		return notOfLength(array, i) ? null : String.format("Array <%s> must NOT be of size %s.", Arrays.toString(array), i);
	}

	/** Predicate: Array <%s> must be empty.*/
	public static boolean empty(@Nonnull int[] array) {
		Null.nonNullArg(array, "array");
		return Array.getLength(array) == 0;
	}

	/** "Special" predicate: Array <%s> must be empty. */
	public static @Nullable String empty$(@Nonnull int[] array) {
		Null.nonNullArg(array, "array");
		return empty(array) ? null : String.format("Array <%s> must be empty.", Arrays.toString(array));
	}
	/** Predicate: Array <%s> must NOT be empty..*/
	public static boolean notEmpty(@Nonnull int[] array) {
		Null.nonNullArg(array, "array");
		return !empty(array);
	}

	/** "Special" predicate: Array <%s> must NOT be empty. */
	public static @Nullable String notEmpty$(@Nonnull int[] array) {
		Null.nonNullArg(array, "array");
		return notEmpty(array) ? null : String.format("Array <%s> must NOT be empty.", Arrays.toString(array));
	}

	/** Predicate: Array <%s> must be null or empty.*/
	public static boolean nullOrEmpty(@Nullable int[] array) {
		return array == null || Array.getLength(array) == 0;
	}

	/** "Special" predicate: Array <%s> must be null or empty. */
	public static @Nullable String nullOrEmpty$(@Nullable int[] array) {
		return nullOrEmpty(array) ? null : String.format("Array <%s> must be null or empty.", Arrays.toString(array));
	}
	/** Predicate: Array <%s> must NOT be null or empty..*/
	public static boolean notNullNotEmpty(@Nullable int[] array) {
		return !nullOrEmpty(array);
	}

	/** "Special" predicate: Array <%s> must NOT be null or empty. */
	public static @Nullable String notNullNotEmpty$(@Nullable int[] array) {
		return notNullNotEmpty(array) ? null : String.format("Array <%s> must NOT be null or empty.", Arrays.toString(array));
	}

	/** Predicate: Array <%s> must be exactly of size 1 (singleton).*/
	public static boolean singleton(@Nonnull int[] array) {
		Null.nonNullArg(array, "array");
		return Array.getLength(array) == 1;
	}

	/** "Special" predicate: Array <%s> must be exactly of size 1 (singleton). */
	public static @Nullable String singleton$(@Nonnull int[] array) {
		Null.nonNullArg(array, "array");
		return singleton(array) ? null : String.format("Array <%s> must be exactly of size 1 (singleton).", Arrays.toString(array));
	}
	/** Predicate: Array <%s> must NOT be exactly of size 1 (singleton)..*/
	public static boolean notSingleton(@Nonnull int[] array) {
		Null.nonNullArg(array, "array");
		return !singleton(array);
	}

	/** "Special" predicate: Array <%s> must NOT be exactly of size 1 (singleton). */
	public static @Nullable String notSingleton$(@Nonnull int[] array) {
		Null.nonNullArg(array, "array");
		return notSingleton(array) ? null : String.format("Array <%s> must NOT be exactly of size 1 (singleton).", Arrays.toString(array));
	}

	/** Predicate: Array <%s> must be equal to array <%s>.*/
	public static boolean equal(int[] a1, int[] a2) {
		return Arrays.equals(a1, a2);
	}

	/** "Special" predicate: Array <%s> must be equal to array <%s>. */
	public static @Nullable String equal$(int[] a1, int[] a2) {
		return equal(a1, a2) ? null : String.format("Array <%s> must be equal to array <%s>.", Arrays.toString(a1), Arrays.toString(a2));
	}
	/** Predicate: Array <%s> must NOT be equal to array <%s>..*/
	public static boolean notEqual(int[] a1, int[] a2) {
		return !equal(a1, a2);
	}

	/** "Special" predicate: Array <%s> must NOT be equal to array <%s>. */
	public static @Nullable String notEqual$(int[] a1, int[] a2) {
		return notEqual(a1, a2) ? null : String.format("Array <%s> must NOT be equal to array <%s>.", Arrays.toString(a1), Arrays.toString(a2));
	}

	static boolean containsExactly_privately(@Nonnull int[] array, int... elementsInOrder) {

		int size = array.length;
		if (size != elementsInOrder.length) { // fast track
			return false;
		}

		for (int i = 0; i < size; i++) {
			if (!equal(array[i], elementsInOrder[i])) {
				return false;
			}
		}

		return true;
	}

	/** Predicate: Array <%s> must contain exactly elements in order: <%s>.*/
	public static <T> boolean containExactly(@Nonnull int[] array, int... elementsInOrder) {
		Null.nonNullArg(array, "array");
		Null.nonNullArg(elementsInOrder, "elementsInOrder");
		return containsExactly_privately(array, elementsInOrder);
	}

	/** "Special" predicate: Array <%s> must contain exactly elements in order: <%s>. */
	public static <T> @Nullable String containExactly$(@Nonnull int[] array, int... elementsInOrder) {
		Null.nonNullArg(array, "array");
		Null.nonNullArg(elementsInOrder, "elementsInOrder");
		return containExactly(array, elementsInOrder) ? null : String.format("Array <%s> must contain exactly elements in order: <%s>.", Arrays.toString(array), Arrays.toString(elementsInOrder));
	}
	/** Predicate: Array <%s> must NOT contain exactly elements in order: <%s>..*/
	public static <T> boolean notContainExactly(@Nonnull int[] array, int... elementsInOrder) {
		Null.nonNullArg(array, "array");
		Null.nonNullArg(elementsInOrder, "elementsInOrder");
		return !containExactly(array, elementsInOrder);
	}

	/** "Special" predicate: Array <%s> must NOT contain exactly elements in order: <%s>. */
	public static <T> @Nullable String notContainExactly$(@Nonnull int[] array, int... elementsInOrder) {
		Null.nonNullArg(array, "array");
		Null.nonNullArg(elementsInOrder, "elementsInOrder");
		return notContainExactly(array, elementsInOrder) ? null : String.format("Array <%s> must NOT contain exactly elements in order: <%s>.", Arrays.toString(array), Arrays.toString(elementsInOrder));
	}

	/** Predicate: Array <%s> must be of size %s.*/
	public static boolean length(@Nonnull long[] array, int i) {
		Null.nonNullArg(array, "array");
		return Array.getLength(array) == i;
	}

	/** "Special" predicate: Array <%s> must be of size %s. */
	public static @Nullable String length$(@Nonnull long[] array, int i) {
		Null.nonNullArg(array, "array");
		return length(array, i) ? null : String.format("Array <%s> must be of size %s.", Arrays.toString(array), i);
	}
	/** Predicate: Array <%s> must NOT be of size %s..*/
	public static boolean lengthOtherThan(@Nonnull long[] array, int i) {
		Null.nonNullArg(array, "array");
		return !length(array, i);
	}

	/** "Special" predicate: Array <%s> must NOT be of size %s. */
	public static @Nullable String lengthOtherThan$(@Nonnull long[] array, int i) {
		Null.nonNullArg(array, "array");
		return lengthOtherThan(array, i) ? null : String.format("Array <%s> must NOT be of size %s.", Arrays.toString(array), i);
	}

	/** Predicate: Array <%s> must be of size %s.*/
	public static boolean ofLength(@Nonnull long[] array, int i) {
		Null.nonNullArg(array, "array");
		return Array.getLength(array) == i;
	}

	/** "Special" predicate: Array <%s> must be of size %s. */
	public static @Nullable String ofLength$(@Nonnull long[] array, int i) {
		Null.nonNullArg(array, "array");
		return ofLength(array, i) ? null : String.format("Array <%s> must be of size %s.", Arrays.toString(array), i);
	}
	/** Predicate: Array <%s> must NOT be of size %s..*/
	public static boolean notOfLength(@Nonnull long[] array, int i) {
		Null.nonNullArg(array, "array");
		return !ofLength(array, i);
	}

	/** "Special" predicate: Array <%s> must NOT be of size %s. */
	public static @Nullable String notOfLength$(@Nonnull long[] array, int i) {
		Null.nonNullArg(array, "array");
		return notOfLength(array, i) ? null : String.format("Array <%s> must NOT be of size %s.", Arrays.toString(array), i);
	}

	/** Predicate: Array <%s> must be empty.*/
	public static boolean empty(@Nonnull long[] array) {
		Null.nonNullArg(array, "array");
		return Array.getLength(array) == 0;
	}

	/** "Special" predicate: Array <%s> must be empty. */
	public static @Nullable String empty$(@Nonnull long[] array) {
		Null.nonNullArg(array, "array");
		return empty(array) ? null : String.format("Array <%s> must be empty.", Arrays.toString(array));
	}
	/** Predicate: Array <%s> must NOT be empty..*/
	public static boolean notEmpty(@Nonnull long[] array) {
		Null.nonNullArg(array, "array");
		return !empty(array);
	}

	/** "Special" predicate: Array <%s> must NOT be empty. */
	public static @Nullable String notEmpty$(@Nonnull long[] array) {
		Null.nonNullArg(array, "array");
		return notEmpty(array) ? null : String.format("Array <%s> must NOT be empty.", Arrays.toString(array));
	}

	/** Predicate: Array <%s> must be null or empty.*/
	public static boolean nullOrEmpty(@Nullable long[] array) {
		return array == null || Array.getLength(array) == 0;
	}

	/** "Special" predicate: Array <%s> must be null or empty. */
	public static @Nullable String nullOrEmpty$(@Nullable long[] array) {
		return nullOrEmpty(array) ? null : String.format("Array <%s> must be null or empty.", Arrays.toString(array));
	}
	/** Predicate: Array <%s> must NOT be null or empty..*/
	public static boolean notNullNotEmpty(@Nullable long[] array) {
		return !nullOrEmpty(array);
	}

	/** "Special" predicate: Array <%s> must NOT be null or empty. */
	public static @Nullable String notNullNotEmpty$(@Nullable long[] array) {
		return notNullNotEmpty(array) ? null : String.format("Array <%s> must NOT be null or empty.", Arrays.toString(array));
	}

	/** Predicate: Array <%s> must be exactly of size 1 (singleton).*/
	public static boolean singleton(@Nonnull long[] array) {
		Null.nonNullArg(array, "array");
		return Array.getLength(array) == 1;
	}

	/** "Special" predicate: Array <%s> must be exactly of size 1 (singleton). */
	public static @Nullable String singleton$(@Nonnull long[] array) {
		Null.nonNullArg(array, "array");
		return singleton(array) ? null : String.format("Array <%s> must be exactly of size 1 (singleton).", Arrays.toString(array));
	}
	/** Predicate: Array <%s> must NOT be exactly of size 1 (singleton)..*/
	public static boolean notSingleton(@Nonnull long[] array) {
		Null.nonNullArg(array, "array");
		return !singleton(array);
	}

	/** "Special" predicate: Array <%s> must NOT be exactly of size 1 (singleton). */
	public static @Nullable String notSingleton$(@Nonnull long[] array) {
		Null.nonNullArg(array, "array");
		return notSingleton(array) ? null : String.format("Array <%s> must NOT be exactly of size 1 (singleton).", Arrays.toString(array));
	}

	/** Predicate: Array <%s> must be equal to array <%s>.*/
	public static boolean equal(long[] a1, long[] a2) {
		return Arrays.equals(a1, a2);
	}

	/** "Special" predicate: Array <%s> must be equal to array <%s>. */
	public static @Nullable String equal$(long[] a1, long[] a2) {
		return equal(a1, a2) ? null : String.format("Array <%s> must be equal to array <%s>.", Arrays.toString(a1), Arrays.toString(a2));
	}
	/** Predicate: Array <%s> must NOT be equal to array <%s>..*/
	public static boolean notEqual(long[] a1, long[] a2) {
		return !equal(a1, a2);
	}

	/** "Special" predicate: Array <%s> must NOT be equal to array <%s>. */
	public static @Nullable String notEqual$(long[] a1, long[] a2) {
		return notEqual(a1, a2) ? null : String.format("Array <%s> must NOT be equal to array <%s>.", Arrays.toString(a1), Arrays.toString(a2));
	}

	static boolean containsExactly_privately(@Nonnull long[] array, long... elementsInOrder) {

		int size = array.length;
		if (size != elementsInOrder.length) { // fast track
			return false;
		}

		for (int i = 0; i < size; i++) {
			if (!equal(array[i], elementsInOrder[i])) {
				return false;
			}
		}

		return true;
	}

	/** Predicate: Array <%s> must contain exactly elements in order: <%s>.*/
	public static <T> boolean containExactly(@Nonnull long[] array, long... elementsInOrder) {
		Null.nonNullArg(array, "array");
		Null.nonNullArg(elementsInOrder, "elementsInOrder");
		return containsExactly_privately(array, elementsInOrder);
	}

	/** "Special" predicate: Array <%s> must contain exactly elements in order: <%s>. */
	public static <T> @Nullable String containExactly$(@Nonnull long[] array, long... elementsInOrder) {
		Null.nonNullArg(array, "array");
		Null.nonNullArg(elementsInOrder, "elementsInOrder");
		return containExactly(array, elementsInOrder) ? null : String.format("Array <%s> must contain exactly elements in order: <%s>.", Arrays.toString(array), Arrays.toString(elementsInOrder));
	}
	/** Predicate: Array <%s> must NOT contain exactly elements in order: <%s>..*/
	public static <T> boolean notContainExactly(@Nonnull long[] array, long... elementsInOrder) {
		Null.nonNullArg(array, "array");
		Null.nonNullArg(elementsInOrder, "elementsInOrder");
		return !containExactly(array, elementsInOrder);
	}

	/** "Special" predicate: Array <%s> must NOT contain exactly elements in order: <%s>. */
	public static <T> @Nullable String notContainExactly$(@Nonnull long[] array, long... elementsInOrder) {
		Null.nonNullArg(array, "array");
		Null.nonNullArg(elementsInOrder, "elementsInOrder");
		return notContainExactly(array, elementsInOrder) ? null : String.format("Array <%s> must NOT contain exactly elements in order: <%s>.", Arrays.toString(array), Arrays.toString(elementsInOrder));
	}

	/** Predicate: Array <%s> must be of size %s.*/
	public static <T> boolean length(@Nonnull T[] array, int i) {
		Null.nonNullArg(array, "array");
		return Array.getLength(array) == i;
	}

	/** "Special" predicate: Array <%s> must be of size %s. */
	public static <T> @Nullable String length$(@Nonnull T[] array, int i) {
		Null.nonNullArg(array, "array");
		return length(array, i) ? null : String.format("Array <%s> must be of size %s.", Arrays.toString(array), i);
	}
	/** Predicate: Array <%s> must NOT be of size %s..*/
	public static <T> boolean lengthOtherThan(@Nonnull T[] array, int i) {
		Null.nonNullArg(array, "array");
		return !length(array, i);
	}

	/** "Special" predicate: Array <%s> must NOT be of size %s. */
	public static <T> @Nullable String lengthOtherThan$(@Nonnull T[] array, int i) {
		Null.nonNullArg(array, "array");
		return lengthOtherThan(array, i) ? null : String.format("Array <%s> must NOT be of size %s.", Arrays.toString(array), i);
	}

	/** Predicate: Array <%s> must be of size %s.*/
	public static <T> boolean ofLength(@Nonnull T[] array, int i) {
		Null.nonNullArg(array, "array");
		return Array.getLength(array) == i;
	}

	/** "Special" predicate: Array <%s> must be of size %s. */
	public static <T> @Nullable String ofLength$(@Nonnull T[] array, int i) {
		Null.nonNullArg(array, "array");
		return ofLength(array, i) ? null : String.format("Array <%s> must be of size %s.", Arrays.toString(array), i);
	}
	/** Predicate: Array <%s> must NOT be of size %s..*/
	public static <T> boolean notOfLength(@Nonnull T[] array, int i) {
		Null.nonNullArg(array, "array");
		return !ofLength(array, i);
	}

	/** "Special" predicate: Array <%s> must NOT be of size %s. */
	public static <T> @Nullable String notOfLength$(@Nonnull T[] array, int i) {
		Null.nonNullArg(array, "array");
		return notOfLength(array, i) ? null : String.format("Array <%s> must NOT be of size %s.", Arrays.toString(array), i);
	}

	/** Predicate: Array <%s> must be empty.*/
	public static <T> boolean empty(@Nonnull T[] array) {
		Null.nonNullArg(array, "array");
		return Array.getLength(array) == 0;
	}

	/** "Special" predicate: Array <%s> must be empty. */
	public static <T> @Nullable String empty$(@Nonnull T[] array) {
		Null.nonNullArg(array, "array");
		return empty(array) ? null : String.format("Array <%s> must be empty.", Arrays.toString(array));
	}
	/** Predicate: Array <%s> must NOT be empty..*/
	public static <T> boolean notEmpty(@Nonnull T[] array) {
		Null.nonNullArg(array, "array");
		return !empty(array);
	}

	/** "Special" predicate: Array <%s> must NOT be empty. */
	public static <T> @Nullable String notEmpty$(@Nonnull T[] array) {
		Null.nonNullArg(array, "array");
		return notEmpty(array) ? null : String.format("Array <%s> must NOT be empty.", Arrays.toString(array));
	}

	/** Predicate: Array <%s> must be null or empty.*/
	public static <T> boolean nullOrEmpty(@Nullable T[] array) {
		return array == null || Array.getLength(array) == 0;
	}

	/** "Special" predicate: Array <%s> must be null or empty. */
	public static <T> @Nullable String nullOrEmpty$(@Nullable T[] array) {
		return nullOrEmpty(array) ? null : String.format("Array <%s> must be null or empty.", Arrays.toString(array));
	}
	/** Predicate: Array <%s> must NOT be null or empty..*/
	public static <T> boolean notNullNotEmpty(@Nullable T[] array) {
		return !nullOrEmpty(array);
	}

	/** "Special" predicate: Array <%s> must NOT be null or empty. */
	public static <T> @Nullable String notNullNotEmpty$(@Nullable T[] array) {
		return notNullNotEmpty(array) ? null : String.format("Array <%s> must NOT be null or empty.", Arrays.toString(array));
	}

	/** Predicate: Array <%s> must be exactly of size 1 (singleton).*/
	public static <T> boolean singleton(@Nonnull T[] array) {
		Null.nonNullArg(array, "array");
		return Array.getLength(array) == 1;
	}

	/** "Special" predicate: Array <%s> must be exactly of size 1 (singleton). */
	public static <T> @Nullable String singleton$(@Nonnull T[] array) {
		Null.nonNullArg(array, "array");
		return singleton(array) ? null : String.format("Array <%s> must be exactly of size 1 (singleton).", Arrays.toString(array));
	}
	/** Predicate: Array <%s> must NOT be exactly of size 1 (singleton)..*/
	public static <T> boolean notSingleton(@Nonnull T[] array) {
		Null.nonNullArg(array, "array");
		return !singleton(array);
	}

	/** "Special" predicate: Array <%s> must NOT be exactly of size 1 (singleton). */
	public static <T> @Nullable String notSingleton$(@Nonnull T[] array) {
		Null.nonNullArg(array, "array");
		return notSingleton(array) ? null : String.format("Array <%s> must NOT be exactly of size 1 (singleton).", Arrays.toString(array));
	}

	/** Predicate: Array <%s> must be equal to array <%s>.*/
	public static <T> boolean equal(T[] a1, T[] a2) {
		return Arrays.equals(a1, a2);
	}

	/** "Special" predicate: Array <%s> must be equal to array <%s>. */
	public static <T> @Nullable String equal$(T[] a1, T[] a2) {
		return equal(a1, a2) ? null : String.format("Array <%s> must be equal to array <%s>.", Arrays.toString(a1), Arrays.toString(a2));
	}
	/** Predicate: Array <%s> must NOT be equal to array <%s>..*/
	public static <T> boolean notEqual(T[] a1, T[] a2) {
		return !equal(a1, a2);
	}

	/** "Special" predicate: Array <%s> must NOT be equal to array <%s>. */
	public static <T> @Nullable String notEqual$(T[] a1, T[] a2) {
		return notEqual(a1, a2) ? null : String.format("Array <%s> must NOT be equal to array <%s>.", Arrays.toString(a1), Arrays.toString(a2));
	}

	static <T> boolean containsExactly_privately(@Nonnull T[] array, T... elementsInOrder) {

		int size = array.length;
		if (size != elementsInOrder.length) { // fast track
			return false;
		}

		for (int i = 0; i < size; i++) {
			if (!equal(array[i], elementsInOrder[i])) {
				return false;
			}
		}

		return true;
	}

	/** Predicate: Array <%s> must contain exactly elements in order: <%s>.*/
	public static <T> boolean containExactly(@Nonnull T[] array, T... elementsInOrder) {
		Null.nonNullArg(array, "array");
		Null.nonNullArg(elementsInOrder, "elementsInOrder");
		return containsExactly_privately(array, elementsInOrder);
	}

	/** "Special" predicate: Array <%s> must contain exactly elements in order: <%s>. */
	public static <T> @Nullable String containExactly$(@Nonnull T[] array, T... elementsInOrder) {
		Null.nonNullArg(array, "array");
		Null.nonNullArg(elementsInOrder, "elementsInOrder");
		return containExactly(array, elementsInOrder) ? null : String.format("Array <%s> must contain exactly elements in order: <%s>.", Arrays.toString(array), Arrays.toString(elementsInOrder));
	}
	/** Predicate: Array <%s> must NOT contain exactly elements in order: <%s>..*/
	public static <T> boolean notContainExactly(@Nonnull T[] array, T... elementsInOrder) {
		Null.nonNullArg(array, "array");
		Null.nonNullArg(elementsInOrder, "elementsInOrder");
		return !containExactly(array, elementsInOrder);
	}

	/** "Special" predicate: Array <%s> must NOT contain exactly elements in order: <%s>. */
	public static <T> @Nullable String notContainExactly$(@Nonnull T[] array, T... elementsInOrder) {
		Null.nonNullArg(array, "array");
		Null.nonNullArg(elementsInOrder, "elementsInOrder");
		return notContainExactly(array, elementsInOrder) ? null : String.format("Array <%s> must NOT contain exactly elements in order: <%s>.", Arrays.toString(array), Arrays.toString(elementsInOrder));
	}

	// </editor-fold>

	// <editor-fold desc="collections">

	/** Predicate: Collection <%s> must contain element <%s>.*/
	public static <T> boolean contain(@Nonnull Collection<? extends T> collection, T element) {
		Null.nonNullArg(collection, "collection");
		return collection.contains(element);
	}

	/** "Special" predicate: Collection <%s> must contain element <%s>. */
	public static <T> @Nullable String contain$(@Nonnull Collection<? extends T> collection, T element) {
		Null.nonNullArg(collection, "collection");
		return contain(collection, element) ? null : String.format("Collection <%s> must contain element <%s>.", collection, element);
	}
	/** Predicate: Collection <%s> must NOT contain element <%s>..*/
	public static <T> boolean notContain(@Nonnull Collection<? extends T> collection, T element) {
		Null.nonNullArg(collection, "collection");
		return !contain(collection, element);
	}

	/** "Special" predicate: Collection <%s> must NOT contain element <%s>. */
	public static <T> @Nullable String notContain$(@Nonnull Collection<? extends T> collection, T element) {
		Null.nonNullArg(collection, "collection");
		return notContain(collection, element) ? null : String.format("Collection <%s> must NOT contain element <%s>.", collection, element);
	}

	/** Predicate: Collection <%s> must contain elements <%s>.*/
	public static <T> boolean contain(@Nonnull Collection<? extends T> collection, T... elements) {
		Null.nonNullArg(collection, "collection");

		for (Object element : elements) {
			if (!collection.contains(element)) {
				return false;
			};
		}

		return containsAndEmptiness(true, collection.isEmpty(), elements);
	}

	/** "Special" predicate: Collection <%s> must contain elements <%s>. */
	public static <T> @Nullable String contain$(@Nonnull Collection<? extends T> collection, T... elements) {
		Null.nonNullArg(collection, "collection");
		return contain(collection, elements) ? null : String.format("Collection <%s> must contain elements <%s>.", collection, Arrays.toString(elements));
	}
	/** Predicate: Collection <%s> must NOT contain elements <%s>..*/
	public static <T> boolean notContain(@Nonnull Collection<? extends T> collection, T... elements) {
		Null.nonNullArg(collection, "collection");

		for (Object element : elements) {
			if (collection.contains(element)) {
				return false;
			};
		}

		return containsAndEmptiness(false, collection.isEmpty(), elements);
	}

	/** "Special" predicate: Collection <%s> must NOT contain elements <%s>. */
	public static <T> @Nullable String notContain$(@Nonnull Collection<? extends T> collection, T... elements) {
		Null.nonNullArg(collection, "collection");
		return notContain(collection, elements) ? null : String.format("Collection <%s> must NOT contain elements <%s>.", collection, Arrays.toString(elements));
	}

	/** Predicate: Collection <%s> must contain element <%s>.*/
	public static <T> boolean containAny(@Nonnull Collection<? extends T> collection, T... elements) {
		Null.nonNullArg(collection, "collection");

		for (Object element : elements) {
			if (collection.contains(element)) {
				return true;
			};
		}

		return false;
	}

	/** "Special" predicate: Collection <%s> must contain element <%s>. */
	public static <T> @Nullable String containAny$(@Nonnull Collection<? extends T> collection, T... elements) {
		Null.nonNullArg(collection, "collection");
		return containAny(collection, elements) ? null : String.format("Collection <%s> must contain element <%s>.", collection, Arrays.toString(elements));
	}

	static <T> boolean containsExactly_privately(@Nonnull Collection<? extends T> collection, T... elementsInOrder) {

		if (collection.size() != elementsInOrder.length) { // fast track
			return false;
		}

		Iterator<?> iterator = collection.iterator();

		for (Object element : elementsInOrder) {
			if (!iterator.hasNext()) {
				return false; // lets not fail because of that (concurrent modification)
			}

			var collectionElement = iterator.next();
			if (!equal(collectionElement, element)) {
				return false;
			}
		}

		return true;
	}

	/** Predicate: Collection <%s> must contain exactly elements in order: <%s>.*/
	public static <T> boolean containExactly(@Nonnull Collection<? extends T> collection, T... elementsInOrder) {
		Null.nonNullArg(collection, "collection");
		Null.nonNullArg(elementsInOrder, "elementsInOrder");
		return containsExactly_privately(collection, elementsInOrder);
	}

	/** "Special" predicate: Collection <%s> must contain exactly elements in order: <%s>. */
	public static <T> @Nullable String containExactly$(@Nonnull Collection<? extends T> collection, T... elementsInOrder) {
		Null.nonNullArg(collection, "collection");
		Null.nonNullArg(elementsInOrder, "elementsInOrder");
		return containExactly(collection, elementsInOrder) ? null : String.format("Collection <%s> must contain exactly elements in order: <%s>.", collection, Arrays.toString(elementsInOrder));
	}
	/** Predicate: Collection <%s> must NOT contain exactly elements in order: <%s>..*/
	public static <T> boolean notContainExactly(@Nonnull Collection<? extends T> collection, T... elementsInOrder) {
		Null.nonNullArg(collection, "collection");
		Null.nonNullArg(elementsInOrder, "elementsInOrder");
		return !containExactly(collection, elementsInOrder);
	}

	/** "Special" predicate: Collection <%s> must NOT contain exactly elements in order: <%s>. */
	public static <T> @Nullable String notContainExactly$(@Nonnull Collection<? extends T> collection, T... elementsInOrder) {
		Null.nonNullArg(collection, "collection");
		Null.nonNullArg(elementsInOrder, "elementsInOrder");
		return notContainExactly(collection, elementsInOrder) ? null : String.format("Collection <%s> must NOT contain exactly elements in order: <%s>.", collection, Arrays.toString(elementsInOrder));
	}

	/** Predicate: Map <%s> must contain key <%s>.*/
	public static <K> boolean containKey(@Nonnull Map<? extends K, ?> map, K key) {
		Null.nonNullArg(map, "map");
		return map.containsKey(key);
	}

	/** "Special" predicate: Map <%s> must contain key <%s>. */
	public static <K> @Nullable String containKey$(@Nonnull Map<? extends K, ?> map, K key) {
		Null.nonNullArg(map, "map");
		return containKey(map, key) ? null : String.format("Map <%s> must contain key <%s>.", map, key);
	}
	/** Predicate: Map <%s> must NOT contain key <%s>..*/
	public static <K> boolean notContainKey(@Nonnull Map<? extends K, ?> map, K key) {
		Null.nonNullArg(map, "map");
		return !containKey(map, key);
	}

	/** "Special" predicate: Map <%s> must NOT contain key <%s>. */
	public static <K> @Nullable String notContainKey$(@Nonnull Map<? extends K, ?> map, K key) {
		Null.nonNullArg(map, "map");
		return notContainKey(map, key) ? null : String.format("Map <%s> must NOT contain key <%s>.", map, key);
	}

	/** Predicate: Map <%s> must contain entry with key <%s> and value <%s>.*/
	public static <K, V> boolean containEntry(@Nonnull Map<? extends K, ? extends V> map, K key, V value) {
		Null.nonNullArg(map, "map");
		return map.containsKey(key) && Objects.equals(map.get(key), value);
	}

	/** "Special" predicate: Map <%s> must contain entry with key <%s> and value <%s>. */
	public static <K, V> @Nullable String containEntry$(@Nonnull Map<? extends K, ? extends V> map, K key, V value) {
		Null.nonNullArg(map, "map");
		return containEntry(map, key, value) ? null : String.format("Map <%s> must contain entry with key <%s> and value <%s>.", map, key, value);
	}
	/** Predicate: Map <%s> must NOT contain entry with key <%s> and value <%s>..*/
	public static <K, V> boolean notContainEntry(@Nonnull Map<? extends K, ? extends V> map, K key, V value) {
		Null.nonNullArg(map, "map");
		return !containEntry(map, key, value);
	}

	/** "Special" predicate: Map <%s> must NOT contain entry with key <%s> and value <%s>. */
	public static <K, V> @Nullable String notContainEntry$(@Nonnull Map<? extends K, ? extends V> map, K key, V value) {
		Null.nonNullArg(map, "map");
		return notContainEntry(map, key, value) ? null : String.format("Map <%s> must NOT contain entry with key <%s> and value <%s>.", map, key, value);
	}

	/** Predicate: Map <%s> must contain keys <%s>.*/
	public static <K> boolean containKeys(@Nonnull Map<? extends K, ?> map, K... keys) {
		Null.nonNullArg(map, "map");

		for (K key : keys) {
			if (!map.containsKey(key)) {
				return false;
			}
		}

		return containsAndEmptiness(true, map.isEmpty(), keys);
	}

	/** "Special" predicate: Map <%s> must contain keys <%s>. */
	public static <K> @Nullable String containKeys$(@Nonnull Map<? extends K, ?> map, K... keys) {
		Null.nonNullArg(map, "map");
		return containKeys(map, keys) ? null : String.format("Map <%s> must contain keys <%s>.", map, Arrays.toString(keys));
	}
	/** Predicate: Map <%s> must NOT contain keys <%s>..*/
	public static <K> boolean notContainKeys(@Nonnull Map<? extends K, ?> map, K... keys) {
		Null.nonNullArg(map, "map");

		for (K key : keys) {
			if (map.containsKey(key)) {
				return false;
			}
		}

		return containsAndEmptiness(false, map.isEmpty(), keys);
	}

	/** "Special" predicate: Map <%s> must NOT contain keys <%s>. */
	public static <K> @Nullable String notContainKeys$(@Nonnull Map<? extends K, ?> map, K... keys) {
		Null.nonNullArg(map, "map");
		return notContainKeys(map, keys) ? null : String.format("Map <%s> must NOT contain keys <%s>.", map, Arrays.toString(keys));
	}

	/** Predicate: Map <%s> must contain any key from <%s>.*/
	public static <K> boolean containAnyKey(@Nonnull Map<? extends K, ?> map, K... keys) {
		Null.nonNullArg(map, "map");
		for (K key : keys) {
			if (map.containsKey(key)) {
				return true;
			}
		}
		return false;
	}

	/** "Special" predicate: Map <%s> must contain any key from <%s>. */
	public static <K> @Nullable String containAnyKey$(@Nonnull Map<? extends K, ?> map, K... keys) {
		Null.nonNullArg(map, "map");
		return containAnyKey(map, keys) ? null : String.format("Map <%s> must contain any key from <%s>.", map, Arrays.toString(keys));
	}

	/** Predicate: Collection <%s> must be of size %s.*/
	public static <T> boolean size(@Nonnull Collection<? extends T> collection, int i) {
		Null.nonNullArg(collection, "collection");
		return collection.size() == i;
	}

	/** "Special" predicate: Collection <%s> must be of size %s. */
	public static <T> @Nullable String size$(@Nonnull Collection<? extends T> collection, int i) {
		Null.nonNullArg(collection, "collection");
		return size(collection, i) ? null : String.format("Collection <%s> must be of size %s.", collection, i);
	}
	/** Predicate: Collection <%s> must NOT be of size %s..*/
	public static <T> boolean sizeOtherThan(@Nonnull Collection<? extends T> collection, int i) {
		Null.nonNullArg(collection, "collection");
		return !size(collection, i);
	}

	/** "Special" predicate: Collection <%s> must NOT be of size %s. */
	public static <T> @Nullable String sizeOtherThan$(@Nonnull Collection<? extends T> collection, int i) {
		Null.nonNullArg(collection, "collection");
		return sizeOtherThan(collection, i) ? null : String.format("Collection <%s> must NOT be of size %s.", collection, i);
	}
	/** Predicate: Collection <%s> must be of size %s.*/
	public static <T> boolean ofSize(@Nonnull Collection<? extends T> collection, int i) {
		Null.nonNullArg(collection, "collection");
		return collection.size() == i;
	}

	/** "Special" predicate: Collection <%s> must be of size %s. */
	public static <T> @Nullable String ofSize$(@Nonnull Collection<? extends T> collection, int i) {
		Null.nonNullArg(collection, "collection");
		return ofSize(collection, i) ? null : String.format("Collection <%s> must be of size %s.", collection, i);
	}
	/** Predicate: Collection <%s> must NOT be of size %s..*/
	public static <T> boolean notOfSize(@Nonnull Collection<? extends T> collection, int i) {
		Null.nonNullArg(collection, "collection");
		return !ofSize(collection, i);
	}

	/** "Special" predicate: Collection <%s> must NOT be of size %s. */
	public static <T> @Nullable String notOfSize$(@Nonnull Collection<? extends T> collection, int i) {
		Null.nonNullArg(collection, "collection");
		return notOfSize(collection, i) ? null : String.format("Collection <%s> must NOT be of size %s.", collection, i);
	}

	/** Predicate: Map <%s> must be of size %s.*/
	public static <K, V> boolean size(@Nonnull Map<? extends K, ? extends V> map, int i) {
		Null.nonNullArg(map, "map");
		return map.size() == i;
	}

	/** "Special" predicate: Map <%s> must be of size %s. */
	public static <K, V> @Nullable String size$(@Nonnull Map<? extends K, ? extends V> map, int i) {
		Null.nonNullArg(map, "map");
		return size(map, i) ? null : String.format("Map <%s> must be of size %s.", map, i);
	}
	/** Predicate: Map <%s> must NOT be of size %s..*/
	public static <K, V> boolean sizeOtherThan(@Nonnull Map<? extends K, ? extends V> map, int i) {
		Null.nonNullArg(map, "map");
		return !size(map, i);
	}

	/** "Special" predicate: Map <%s> must NOT be of size %s. */
	public static <K, V> @Nullable String sizeOtherThan$(@Nonnull Map<? extends K, ? extends V> map, int i) {
		Null.nonNullArg(map, "map");
		return sizeOtherThan(map, i) ? null : String.format("Map <%s> must NOT be of size %s.", map, i);
	}
	/** Predicate: Map <%s> must be of size %s.*/
	public static <K, V> boolean ofSize(@Nonnull Map<? extends K, ? extends V> map, int i) {
		Null.nonNullArg(map, "map");
		return map.size() == i;
	}

	/** "Special" predicate: Map <%s> must be of size %s. */
	public static <K, V> @Nullable String ofSize$(@Nonnull Map<? extends K, ? extends V> map, int i) {
		Null.nonNullArg(map, "map");
		return ofSize(map, i) ? null : String.format("Map <%s> must be of size %s.", map, i);
	}
	/** Predicate: Map <%s> must NOT be of size %s..*/
	public static <K, V> boolean notOfSize(@Nonnull Map<? extends K, ? extends V> map, int i) {
		Null.nonNullArg(map, "map");
		return !ofSize(map, i);
	}

	/** "Special" predicate: Map <%s> must NOT be of size %s. */
	public static <K, V> @Nullable String notOfSize$(@Nonnull Map<? extends K, ? extends V> map, int i) {
		Null.nonNullArg(map, "map");
		return notOfSize(map, i) ? null : String.format("Map <%s> must NOT be of size %s.", map, i);
	}

	/** Predicate: <%s> must be part of <%s> collection.*/
	public static <T> boolean partOf(T element, @Nonnull Collection<? extends T> collection) {
		Null.nonNullArg(collection, "collection");
		return collection.contains(element);
	}

	/** "Special" predicate: <%s> must be part of <%s> collection. */
	public static <T> @Nullable String partOf$(T element, @Nonnull Collection<? extends T> collection) {
		Null.nonNullArg(collection, "collection");
		return partOf(element, collection) ? null : String.format("<%s> must be part of <%s> collection.", element, collection);
	}
	/** Predicate: <%s> must NOT be part of <%s> collection..*/
	public static <T> boolean notPartOf(T element, @Nonnull Collection<? extends T> collection) {
		Null.nonNullArg(collection, "collection");
		return !partOf(element, collection);
	}

	/** "Special" predicate: <%s> must NOT be part of <%s> collection. */
	public static <T> @Nullable String notPartOf$(T element, @Nonnull Collection<? extends T> collection) {
		Null.nonNullArg(collection, "collection");
		return notPartOf(element, collection) ? null : String.format("<%s> must NOT be part of <%s> collection.", element, collection);
	}

	/** Predicate: <%s> must be key in <%s> map.*/
	public static <K> boolean aKeyIn(K key, @Nonnull Map<? extends K, ?> map) {
		Null.nonNullArg(map, "map");
		return map.containsKey(key);
	}

	/** "Special" predicate: <%s> must be key in <%s> map. */
	public static <K> @Nullable String aKeyIn$(K key, @Nonnull Map<? extends K, ?> map) {
		Null.nonNullArg(map, "map");
		return aKeyIn(key, map) ? null : String.format("<%s> must be key in <%s> map.", key, map);
	}
	/** Predicate: <%s> must NOT be key in <%s> map..*/
	public static <K> boolean notAKeyIn(K key, @Nonnull Map<? extends K, ?> map) {
		Null.nonNullArg(map, "map");
		return !aKeyIn(key, map);
	}

	/** "Special" predicate: <%s> must NOT be key in <%s> map. */
	public static <K> @Nullable String notAKeyIn$(K key, @Nonnull Map<? extends K, ?> map) {
		Null.nonNullArg(map, "map");
		return notAKeyIn(key, map) ? null : String.format("<%s> must NOT be key in <%s> map.", key, map);
	}

	/** Predicate: Collection <%s> must be empty.*/
	public static <T> boolean empty(@Nonnull Collection<? extends T> collection) {
		Null.nonNullArg(collection, "collection");
		return collection.isEmpty();
	}

	/** "Special" predicate: Collection <%s> must be empty. */
	public static <T> @Nullable String empty$(@Nonnull Collection<? extends T> collection) {
		Null.nonNullArg(collection, "collection");
		return empty(collection) ? null : String.format("Collection <%s> must be empty.", collection);
	}
	/** Predicate: Collection <%s> must NOT be empty..*/
	public static <T> boolean notEmpty(@Nonnull Collection<? extends T> collection) {
		Null.nonNullArg(collection, "collection");
		return !empty(collection);
	}

	/** "Special" predicate: Collection <%s> must NOT be empty. */
	public static <T> @Nullable String notEmpty$(@Nonnull Collection<? extends T> collection) {
		Null.nonNullArg(collection, "collection");
		return notEmpty(collection) ? null : String.format("Collection <%s> must NOT be empty.", collection);
	}

	/** Predicate: Collection <%s> must be empty.*/
	public static <T> boolean nullOrEmpty(@Nullable Collection<? extends T> collection) {
		return collection == null || collection.isEmpty();
	}

	/** "Special" predicate: Collection <%s> must be empty. */
	public static <T> @Nullable String nullOrEmpty$(@Nullable Collection<? extends T> collection) {
		return nullOrEmpty(collection) ? null : String.format("Collection <%s> must be empty.", collection);
	}
	/** Predicate: Collection <%s> must NOT be empty..*/
	public static <T> boolean notNullNorEmpty(@Nullable Collection<? extends T> collection) {
		return !nullOrEmpty(collection);
	}

	/** "Special" predicate: Collection <%s> must NOT be empty. */
	public static <T> @Nullable String notNullNorEmpty$(@Nullable Collection<? extends T> collection) {
		return notNullNorEmpty(collection) ? null : String.format("Collection <%s> must NOT be empty.", collection);
	}

	/** Predicate: Collection <%s> must be exactly of size 1 (singleton)..*/
	public static <T> boolean singleton(@Nonnull Collection<? extends T> collection) {
		Null.nonNullArg(collection, "collection");
		return collection.size() == 1;
	}

	/** "Special" predicate: Collection <%s> must be exactly of size 1 (singleton).. */
	public static <T> @Nullable String singleton$(@Nonnull Collection<? extends T> collection) {
		Null.nonNullArg(collection, "collection");
		return singleton(collection) ? null : String.format("Collection <%s> must be exactly of size 1 (singleton)..", collection);
	}
	/** Predicate: Collection <%s> must NOT be exactly of size 1 (singleton)...*/
	public static <T> boolean notSingleton(@Nonnull Collection<? extends T> collection) {
		Null.nonNullArg(collection, "collection");
		return !singleton(collection);
	}

	/** "Special" predicate: Collection <%s> must NOT be exactly of size 1 (singleton).. */
	public static <T> @Nullable String notSingleton$(@Nonnull Collection<? extends T> collection) {
		Null.nonNullArg(collection, "collection");
		return notSingleton(collection) ? null : String.format("Collection <%s> must NOT be exactly of size 1 (singleton)..", collection);
	}

	/** Predicate: Map <%s> must be empty.*/
	public static boolean empty(@Nonnull Map<?, ?> map) {
		Null.nonNullArg(map, "map");
		return map.isEmpty();
	}

	/** "Special" predicate: Map <%s> must be empty. */
	public static @Nullable String empty$(@Nonnull Map<?, ?> map) {
		Null.nonNullArg(map, "map");
		return empty(map) ? null : String.format("Map <%s> must be empty.", map);
	}
	/** Predicate: Map <%s> must NOT be empty..*/
	public static boolean notEmpty(@Nonnull Map<?, ?> map) {
		Null.nonNullArg(map, "map");
		return !empty(map);
	}

	/** "Special" predicate: Map <%s> must NOT be empty. */
	public static @Nullable String notEmpty$(@Nonnull Map<?, ?> map) {
		Null.nonNullArg(map, "map");
		return notEmpty(map) ? null : String.format("Map <%s> must NOT be empty.", map);
	}

	/** Predicate: Collection <%s> must be empty.*/
	public static boolean nullOrEmpty(@Nullable Map<?, ?> map) {
		return map == null || map.isEmpty();
	}

	/** "Special" predicate: Collection <%s> must be empty. */
	public static @Nullable String nullOrEmpty$(@Nullable Map<?, ?> map) {
		return nullOrEmpty(map) ? null : String.format("Collection <%s> must be empty.", map);
	}
	/** Predicate: Collection <%s> must NOT be empty..*/
	public static boolean notNullNorEmpty(@Nullable Map<?, ?> map) {
		return !nullOrEmpty(map);
	}

	/** "Special" predicate: Collection <%s> must NOT be empty. */
	public static @Nullable String notNullNorEmpty$(@Nullable Map<?, ?> map) {
		return notNullNorEmpty(map) ? null : String.format("Collection <%s> must NOT be empty.", map);
	}

	/** Predicate: Collection <%s> must be exactly of size 1 (singleton).*/
	public static boolean singleton(@Nonnull Map<?, ?> map) {
		Null.nonNullArg(map, "map");
		return map.size() == 1;
	}

	/** "Special" predicate: Collection <%s> must be exactly of size 1 (singleton). */
	public static @Nullable String singleton$(@Nonnull Map<?, ?> map) {
		Null.nonNullArg(map, "map");
		return singleton(map) ? null : String.format("Collection <%s> must be exactly of size 1 (singleton).", map);
	}
	/** Predicate: Collection <%s> must NOT be exactly of size 1 (singleton)..*/
	public static boolean notSingleton(@Nonnull Map<?, ?> map) {
		Null.nonNullArg(map, "map");
		return !singleton(map);
	}

	/** "Special" predicate: Collection <%s> must NOT be exactly of size 1 (singleton). */
	public static @Nullable String notSingleton$(@Nonnull Map<?, ?> map) {
		Null.nonNullArg(map, "map");
		return notSingleton(map) ? null : String.format("Collection <%s> must NOT be exactly of size 1 (singleton).", map);
	}

	// </editor-fold>

	// <editor-fold desc="object derivatives">

	/** Predicate: Object <%s> of class <%s> must be instance of <%s>.*/
	public static boolean instanceOf(Object object, Class<?> clazz) {
		Null.nonNullArg(clazz, "clazz");
		return clazz.isInstance(object);
	}

	/** "Special" predicate: Object <%s> of class <%s> must be instance of <%s>. */
	public static @Nullable String instanceOf$(Object object, Class<?> clazz) {
		Null.nonNullArg(clazz, "clazz");
		return instanceOf(object, clazz) ? null : String.format("Object <%s> of class <%s> must be instance of <%s>.", object, object != null ? object.getClass() : null, clazz);
	}
	/** Predicate: Object <%s> of class <%s> must NOT be instance of <%s>..*/
	public static boolean notInstanceOf(Object object, Class<?> clazz) {
		Null.nonNullArg(clazz, "clazz");
		return !instanceOf(object, clazz);
	}

	/** "Special" predicate: Object <%s> of class <%s> must NOT be instance of <%s>. */
	public static @Nullable String notInstanceOf$(Object object, Class<?> clazz) {
		Null.nonNullArg(clazz, "clazz");
		return notInstanceOf(object, clazz) ? null : String.format("Object <%s> of class <%s> must NOT be instance of <%s>.", object, object != null ? object.getClass() : null, clazz);
	}

	/** Predicate: Object <%s> of class <%s> must be instance of any <%s>.*/
	public static boolean instanceOfAny(Object object, Class<?>... classes) {
		Null.nonNullArg(classes, "classes");
		for (Class<?> c : classes) {
			if (c.isInstance(object)) {
				return true;
			}
		}

		return false;
	}

	/** "Special" predicate: Object <%s> of class <%s> must be instance of any <%s>. */
	public static @Nullable String instanceOfAny$(Object object, Class<?>... classes) {
		Null.nonNullArg(classes, "classes");
		return instanceOfAny(object, classes) ? null : String.format("Object <%s> of class <%s> must be instance of any <%s>.", object, object != null ? object.getClass() : null, Arrays.toString(classes));
	}
	/** Predicate: Object <%s> of class <%s> must NOT be instance of any <%s>..*/
	public static boolean notInstanceOfAny(Object object, Class<?>... classes) {
		Null.nonNullArg(classes, "classes");
		return !instanceOfAny(object, classes);
	}

	/** "Special" predicate: Object <%s> of class <%s> must NOT be instance of any <%s>. */
	public static @Nullable String notInstanceOfAny$(Object object, Class<?>... classes) {
		Null.nonNullArg(classes, "classes");
		return notInstanceOfAny(object, classes) ? null : String.format("Object <%s> of class <%s> must NOT be instance of any <%s>.", object, object != null ? object.getClass() : null, Arrays.toString(classes));
	}

	/** Predicate: Object <%s> of class <%s> must be exactly instance of <%s>.*/
	public static boolean exactlyInstanceOf(Object object, Class<?> clazz) {
		Null.nonNullArg(clazz, "clazz");
		return object != null && clazz == object.getClass();
	}

	/** "Special" predicate: Object <%s> of class <%s> must be exactly instance of <%s>. */
	public static @Nullable String exactlyInstanceOf$(Object object, Class<?> clazz) {
		Null.nonNullArg(clazz, "clazz");
		return exactlyInstanceOf(object, clazz) ? null : String.format("Object <%s> of class <%s> must be exactly instance of <%s>.", object, object != null ? object.getClass() : null, clazz);
	}
	/** Predicate: Object <%s> of class <%s> must NOT be exactly instance of <%s>..*/
	public static boolean notExactlyInstanceOf(Object object, Class<?> clazz) {
		Null.nonNullArg(clazz, "clazz");
		return !exactlyInstanceOf(object, clazz);
	}

	/** "Special" predicate: Object <%s> of class <%s> must NOT be exactly instance of <%s>. */
	public static @Nullable String notExactlyInstanceOf$(Object object, Class<?> clazz) {
		Null.nonNullArg(clazz, "clazz");
		return notExactlyInstanceOf(object, clazz) ? null : String.format("Object <%s> of class <%s> must NOT be exactly instance of <%s>.", object, object != null ? object.getClass() : null, clazz);
	}

	/** Predicate: Class <%s> must be assignable from <%s>.*/
	public static boolean assignableFrom(Class<?> clazz, Class<?> from) {
		Null.nonNullArg(from, "from");
		return clazz.isAssignableFrom(from);
	}

	/** "Special" predicate: Class <%s> must be assignable from <%s>. */
	public static @Nullable String assignableFrom$(Class<?> clazz, Class<?> from) {
		Null.nonNullArg(from, "from");
		return assignableFrom(clazz, from) ? null : String.format("Class <%s> must be assignable from <%s>.", clazz, from);
	}
	/** Predicate: Class <%s> must NOT be assignable from <%s>..*/
	public static boolean notAssignableFrom(Class<?> clazz, Class<?> from) {
		Null.nonNullArg(from, "from");
		return !assignableFrom(clazz, from);
	}

	/** "Special" predicate: Class <%s> must NOT be assignable from <%s>. */
	public static @Nullable String notAssignableFrom$(Class<?> clazz, Class<?> from) {
		Null.nonNullArg(from, "from");
		return notAssignableFrom(clazz, from) ? null : String.format("Class <%s> must NOT be assignable from <%s>.", clazz, from);
	}

	/** Predicate: Class <%s> must be assignable to <%s>.*/
	public static boolean assignableTo(Class<?> clazz, Class<?> from) {
		Null.nonNullArg(from, "from");
		return from.isAssignableFrom(clazz);
	}

	/** "Special" predicate: Class <%s> must be assignable to <%s>. */
	public static @Nullable String assignableTo$(Class<?> clazz, Class<?> from) {
		Null.nonNullArg(from, "from");
		return assignableTo(clazz, from) ? null : String.format("Class <%s> must be assignable to <%s>.", clazz, from);
	}
	/** Predicate: Class <%s> must NOT be assignable to <%s>..*/
	public static boolean notAssignableTo(Class<?> clazz, Class<?> from) {
		Null.nonNullArg(from, "from");
		return !assignableTo(clazz, from);
	}

	/** "Special" predicate: Class <%s> must NOT be assignable to <%s>. */
	public static @Nullable String notAssignableTo$(Class<?> clazz, Class<?> from) {
		Null.nonNullArg(from, "from");
		return notAssignableTo(clazz, from) ? null : String.format("Class <%s> must NOT be assignable to <%s>.", clazz, from);
	}

	// </editor-fold>

	// <editor-fold desc="Throwables">

	/** Predicate: Exception <%s> must be instance of a RuntimeException.*/
	public static boolean runtime(@Nonnull Throwable e) {
		Null.nonNullArg(e, "e");
		return e instanceof RuntimeException;
	}

	/** "Special" predicate: Exception <%s> must be instance of a RuntimeException. */
	public static @Nullable String runtime$(@Nonnull Throwable e) {
		Null.nonNullArg(e, "e");
		return runtime(e) ? null : String.format("Exception <%s> must be instance of a RuntimeException.", e);
	}
	/** Predicate: Exception <%s> must NOT be instance of a RuntimeException..*/
	public static boolean notRuntime(@Nonnull Throwable e) {
		Null.nonNullArg(e, "e");
		return !runtime(e);
	}

	/** "Special" predicate: Exception <%s> must NOT be instance of a RuntimeException. */
	public static @Nullable String notRuntime$(@Nonnull Throwable e) {
		Null.nonNullArg(e, "e");
		return notRuntime(e) ? null : String.format("Exception <%s> must NOT be instance of a RuntimeException.", e);
	}

	/** Predicate: Exception <%s> must have cause.*/
	public static boolean cause(@Nonnull Throwable e) {
		Null.nonNullArg(e, "e");
		return e.getCause() != null;
	}

	/** "Special" predicate: Exception <%s> must have cause. */
	public static @Nullable String cause$(@Nonnull Throwable e) {
		Null.nonNullArg(e, "e");
		return cause(e) ? null : String.format("Exception <%s> must have cause.", e);
	}
	/** Predicate: Exception <%s> must NOT have cause..*/
	public static boolean noCause(@Nonnull Throwable e) {
		Null.nonNullArg(e, "e");
		return !cause(e);
	}

	/** "Special" predicate: Exception <%s> must NOT have cause. */
	public static @Nullable String noCause$(@Nonnull Throwable e) {
		Null.nonNullArg(e, "e");
		return noCause(e) ? null : String.format("Exception <%s> must NOT have cause.", e);
	}

	/** Predicate: Exception <%s> must have suspended other exceptions.*/
	public static boolean suspended(@Nonnull Throwable e) {
		Null.nonNullArg(e, "e");
		return e.getSuppressed().length != 0;
	}

	/** "Special" predicate: Exception <%s> must have suspended other exceptions. */
	public static @Nullable String suspended$(@Nonnull Throwable e) {
		Null.nonNullArg(e, "e");
		return suspended(e) ? null : String.format("Exception <%s> must have suspended other exceptions.", e);
	}
	/** Predicate: Exception <%s> must NOT have suspended other exceptions..*/
	public static boolean noSuspended(@Nonnull Throwable e) {
		Null.nonNullArg(e, "e");
		return !suspended(e);
	}

	/** "Special" predicate: Exception <%s> must NOT have suspended other exceptions. */
	public static @Nullable String noSuspended$(@Nonnull Throwable e) {
		Null.nonNullArg(e, "e");
		return noSuspended(e) ? null : String.format("Exception <%s> must NOT have suspended other exceptions.", e);
	}

	/** Predicate: Exception <%s> must have message equal to <'%s>'.*/
	public static boolean msgEqual(@Nonnull Throwable e, String text) {
		Null.nonNullArg(e, "e");
		return Objects.equals(e.getMessage(), text);
	}

	/** "Special" predicate: Exception <%s> must have message equal to <'%s>'. */
	public static @Nullable String msgEqual$(@Nonnull Throwable e, String text) {
		Null.nonNullArg(e, "e");
		return msgEqual(e, text) ? null : String.format("Exception <%s> must have message equal to <'%s>'.", e, text);
	}
	/** Predicate: Exception <%s> must NOT have message equal to <'%s>'..*/
	public static boolean msgNotEqual(@Nonnull Throwable e, String text) {
		Null.nonNullArg(e, "e");
		return !msgEqual(e, text);
	}

	/** "Special" predicate: Exception <%s> must NOT have message equal to <'%s>'. */
	public static @Nullable String msgNotEqual$(@Nonnull Throwable e, String text) {
		Null.nonNullArg(e, "e");
		return msgNotEqual(e, text) ? null : String.format("Exception <%s> must NOT have message equal to <'%s>'.", e, text);
	}

	/** Predicate: Exception <%s> must have message equal to <'%s>'.*/
	public static boolean noMsg(@Nonnull Throwable e) {
		Null.nonNullArg(e, "e");
		return e.getMessage() == null;
	}

	/** "Special" predicate: Exception <%s> must have message equal to <'%s>'. */
	public static @Nullable String noMsg$(@Nonnull Throwable e) {
		Null.nonNullArg(e, "e");
		return noMsg(e) ? null : String.format("Exception <%s> must have message equal to <'%s>'.", e);
	}
	/** Predicate: Exception <%s> must NOT have message equal to <'%s>'..*/
	public static boolean msgPresent(@Nonnull Throwable e) {
		Null.nonNullArg(e, "e");
		return !noMsg(e);
	}

	/** "Special" predicate: Exception <%s> must NOT have message equal to <'%s>'. */
	public static @Nullable String msgPresent$(@Nonnull Throwable e) {
		Null.nonNullArg(e, "e");
		return msgPresent(e) ? null : String.format("Exception <%s> must NOT have message equal to <'%s>'.", e);
	}

	/** Predicate: Exception <%s> must have message starting with <'%s>'.*/
	public static boolean msgStartWith(@Nonnull Throwable e, @Nonnull String text) {
		Null.nonNullArg(e, "e");
		Null.nonNullArg(text, "text");
		return e.getMessage() != null && e.getMessage().startsWith(text);
	}

	/** "Special" predicate: Exception <%s> must have message starting with <'%s>'. */
	public static @Nullable String msgStartWith$(@Nonnull Throwable e, @Nonnull String text) {
		Null.nonNullArg(e, "e");
		Null.nonNullArg(text, "text");
		return msgStartWith(e, text) ? null : String.format("Exception <%s> must have message starting with <'%s>'.", e, text);
	}
	/** Predicate: Exception <%s> must NOT have message starting with <'%s>'..*/
	public static boolean msgNotStartWith(@Nonnull Throwable e, @Nonnull String text) {
		Null.nonNullArg(e, "e");
		Null.nonNullArg(text, "text");
		return !msgStartWith(e, text);
	}

	/** "Special" predicate: Exception <%s> must NOT have message starting with <'%s>'. */
	public static @Nullable String msgNotStartWith$(@Nonnull Throwable e, @Nonnull String text) {
		Null.nonNullArg(e, "e");
		Null.nonNullArg(text, "text");
		return msgNotStartWith(e, text) ? null : String.format("Exception <%s> must NOT have message starting with <'%s>'.", e, text);
	}

	/** Predicate: Exception <%s> must have message containing <'%s>'.*/
	public static boolean msgContain(@Nonnull Throwable e, @Nonnull String text) {
		Null.nonNullArg(e, "e");
		Null.nonNullArg(text, "text");
		return e.getMessage() != null && e.getMessage().contains(text);
	}

	/** "Special" predicate: Exception <%s> must have message containing <'%s>'. */
	public static @Nullable String msgContain$(@Nonnull Throwable e, @Nonnull String text) {
		Null.nonNullArg(e, "e");
		Null.nonNullArg(text, "text");
		return msgContain(e, text) ? null : String.format("Exception <%s> must have message containing <'%s>'.", e, text);
	}
	/** Predicate: Exception <%s> must NOT have message containing <'%s>'..*/
	public static boolean msgNotContain(@Nonnull Throwable e, @Nonnull String text) {
		Null.nonNullArg(e, "e");
		Null.nonNullArg(text, "text");
		return !msgContain(e, text);
	}

	/** "Special" predicate: Exception <%s> must NOT have message containing <'%s>'. */
	public static @Nullable String msgNotContain$(@Nonnull Throwable e, @Nonnull String text) {
		Null.nonNullArg(e, "e");
		Null.nonNullArg(text, "text");
		return msgNotContain(e, text) ? null : String.format("Exception <%s> must NOT have message containing <'%s>'.", e, text);
	}

	/** Predicate: Exception <%s> must have message ending with <'%s>'.*/
	public static boolean msgEndWith(@Nonnull Throwable e, @Nonnull String text) {
		Null.nonNullArg(e, "e");
		Null.nonNullArg(text, "text");
		return e.getMessage() != null && e.getMessage().endsWith(text);
	}

	/** "Special" predicate: Exception <%s> must have message ending with <'%s>'. */
	public static @Nullable String msgEndWith$(@Nonnull Throwable e, @Nonnull String text) {
		Null.nonNullArg(e, "e");
		Null.nonNullArg(text, "text");
		return msgEndWith(e, text) ? null : String.format("Exception <%s> must have message ending with <'%s>'.", e, text);
	}
	/** Predicate: Exception <%s> must NOT have message ending with <'%s>'..*/
	public static boolean msgNotEndWith(@Nonnull Throwable e, @Nonnull String text) {
		Null.nonNullArg(e, "e");
		Null.nonNullArg(text, "text");
		return !msgEndWith(e, text);
	}

	/** "Special" predicate: Exception <%s> must NOT have message ending with <'%s>'. */
	public static @Nullable String msgNotEndWith$(@Nonnull Throwable e, @Nonnull String text) {
		Null.nonNullArg(e, "e");
		Null.nonNullArg(text, "text");
		return msgNotEndWith(e, text) ? null : String.format("Exception <%s> must NOT have message ending with <'%s>'.", e, text);
	}

	/** Predicate: Exception <%s> must have suppressed other exceptions.*/
	public static boolean suppressing(@Nonnull Throwable e) {
		Null.nonNullArg(e, "e");
		return e.getSuppressed().length > 0;
	}

	/** "Special" predicate: Exception <%s> must have suppressed other exceptions. */
	public static @Nullable String suppressing$(@Nonnull Throwable e) {
		Null.nonNullArg(e, "e");
		return suppressing(e) ? null : String.format("Exception <%s> must have suppressed other exceptions.", e);
	}
	/** Predicate: Exception <%s> must NOT have suppressed other exceptions..*/
	public static boolean notSuppressing(@Nonnull Throwable e) {
		Null.nonNullArg(e, "e");
		return !suppressing(e);
	}

	/** "Special" predicate: Exception <%s> must NOT have suppressed other exceptions. */
	public static @Nullable String notSuppressing$(@Nonnull Throwable e) {
		Null.nonNullArg(e, "e");
		return notSuppressing(e) ? null : String.format("Exception <%s> must NOT have suppressed other exceptions.", e);
	}

	// </editor-fold>

	// <editor-fold desc="Value (e.g. Opt )">

	/** Predicate: Optional <%s> must have value.*/
	public static <V> boolean valuePresent(@Nonnull OptBoolTrait<?> opt) {
		Null.nonNullArg(opt, "opt");
		return opt.isPresent();
	}

	/** "Special" predicate: Optional <%s> must have value. */
	public static <V> @Nullable String valuePresent$(@Nonnull OptBoolTrait<?> opt) {
		Null.nonNullArg(opt, "opt");
		return valuePresent(opt) ? null : String.format("Optional <%s> must have value.", opt);
	}
	/** Predicate: Optional <%s> must NOT have value..*/
	public static <V> boolean noValuePresent(@Nonnull OptBoolTrait<?> opt) {
		Null.nonNullArg(opt, "opt");
		return !valuePresent(opt);
	}

	/** "Special" predicate: Optional <%s> must NOT have value. */
	public static <V> @Nullable String noValuePresent$(@Nonnull OptBoolTrait<?> opt) {
		Null.nonNullArg(opt, "opt");
		return noValuePresent(opt) ? null : String.format("Optional <%s> must NOT have value.", opt);
	}

	/** Predicate: Optional <%s> must be void.*/
	public static <V> boolean Void(@Nonnull OptBoolTrait<?> opt) {
		Null.nonNullArg(opt, "opt");
		return opt.isVoid();
	}

	/** "Special" predicate: Optional <%s> must be void. */
	public static <V> @Nullable String Void$(@Nonnull OptBoolTrait<?> opt) {
		Null.nonNullArg(opt, "opt");
		return Void(opt) ? null : String.format("Optional <%s> must be void.", opt);
	}
	/** Predicate: Optional <%s> must NOT be void..*/
	public static <V> boolean notVoid(@Nonnull OptBoolTrait<?> opt) {
		Null.nonNullArg(opt, "opt");
		return !Void(opt);
	}

	/** "Special" predicate: Optional <%s> must NOT be void. */
	public static <V> @Nullable String notVoid$(@Nonnull OptBoolTrait<?> opt) {
		Null.nonNullArg(opt, "opt");
		return notVoid(opt) ? null : String.format("Optional <%s> must NOT be void.", opt);
	}

	/** Predicate: Optional <%s> must have value equal <%s>.*/
	public static <V> boolean valueEqual(@Nonnull OptBoolTrait<?> opt, boolean expected) {
		Null.nonNullArg(opt, "opt");
		return opt.isPresent() && opt.value() == expected;
	}

	/** "Special" predicate: Optional <%s> must have value equal <%s>. */
	public static <V> @Nullable String valueEqual$(@Nonnull OptBoolTrait<?> opt, boolean expected) {
		Null.nonNullArg(opt, "opt");
		return valueEqual(opt, expected) ? null : String.format("Optional <%s> must have value equal <%s>.", opt, expected);
	}
	/** Predicate: Optional <%s> must NOT have value equal <%s>..*/
	public static <V> boolean valueNotEqual(@Nonnull OptBoolTrait<?> opt, boolean expected) {
		Null.nonNullArg(opt, "opt");
		return !valueEqual(opt, expected);
	}

	/** "Special" predicate: Optional <%s> must NOT have value equal <%s>. */
	public static <V> @Nullable String valueNotEqual$(@Nonnull OptBoolTrait<?> opt, boolean expected) {
		Null.nonNullArg(opt, "opt");
		return valueNotEqual(opt, expected) ? null : String.format("Optional <%s> must NOT have value equal <%s>.", opt, expected);
	}

	/** Predicate: Optional <%s> must have value equal <%s>.*/
	public static <V> boolean valueEqual(@Nonnull BoolValueTrait<?> opt, boolean expected) {
		Null.nonNullArg(opt, "opt");
		return opt.value() == expected;
	}

	/** "Special" predicate: Optional <%s> must have value equal <%s>. */
	public static <V> @Nullable String valueEqual$(@Nonnull BoolValueTrait<?> opt, boolean expected) {
		Null.nonNullArg(opt, "opt");
		return valueEqual(opt, expected) ? null : String.format("Optional <%s> must have value equal <%s>.", opt, expected);
	}
	/** Predicate: Optional <%s> must NOT have value equal <%s>..*/
	public static <V> boolean valueNotEqual(@Nonnull BoolValueTrait<?> opt, boolean expected) {
		Null.nonNullArg(opt, "opt");
		return !valueEqual(opt, expected);
	}

	/** "Special" predicate: Optional <%s> must NOT have value equal <%s>. */
	public static <V> @Nullable String valueNotEqual$(@Nonnull BoolValueTrait<?> opt, boolean expected) {
		Null.nonNullArg(opt, "opt");
		return valueNotEqual(opt, expected) ? null : String.format("Optional <%s> must NOT have value equal <%s>.", opt, expected);
	}

	/** Predicate: Optional <%s> must have value.*/
	public static <V> boolean valuePresent(@Nonnull OptTrait<?, ?> opt) {
		Null.nonNullArg(opt, "opt");
		return opt.isPresent();
	}

	/** "Special" predicate: Optional <%s> must have value. */
	public static <V> @Nullable String valuePresent$(@Nonnull OptTrait<?, ?> opt) {
		Null.nonNullArg(opt, "opt");
		return valuePresent(opt) ? null : String.format("Optional <%s> must have value.", opt);
	}
	/** Predicate: Optional <%s> must NOT have value..*/
	public static <V> boolean noValuePresent(@Nonnull OptTrait<?, ?> opt) {
		Null.nonNullArg(opt, "opt");
		return !valuePresent(opt);
	}

	/** "Special" predicate: Optional <%s> must NOT have value. */
	public static <V> @Nullable String noValuePresent$(@Nonnull OptTrait<?, ?> opt) {
		Null.nonNullArg(opt, "opt");
		return noValuePresent(opt) ? null : String.format("Optional <%s> must NOT have value.", opt);
	}

	/** Predicate: Optional <%s> must be void.*/
	public static <V> boolean Void(@Nonnull OptTrait<?, ?> opt) {
		Null.nonNullArg(opt, "opt");
		return opt.isVoid();
	}

	/** "Special" predicate: Optional <%s> must be void. */
	public static <V> @Nullable String Void$(@Nonnull OptTrait<?, ?> opt) {
		Null.nonNullArg(opt, "opt");
		return Void(opt) ? null : String.format("Optional <%s> must be void.", opt);
	}
	/** Predicate: Optional <%s> must NOT be void..*/
	public static <V> boolean notVoid(@Nonnull OptTrait<?, ?> opt) {
		Null.nonNullArg(opt, "opt");
		return !Void(opt);
	}

	/** "Special" predicate: Optional <%s> must NOT be void. */
	public static <V> @Nullable String notVoid$(@Nonnull OptTrait<?, ?> opt) {
		Null.nonNullArg(opt, "opt");
		return notVoid(opt) ? null : String.format("Optional <%s> must NOT be void.", opt);
	}

	/** Predicate: Optional <%s> must have value equal <%s>.*/
	public static <V> boolean valueEqual(@Nonnull ValueTrait<?, ?> opt, V expected) {
		Null.nonNullArg(opt, "opt");
		return Objects.equals(opt.nullable(), expected);
	}

	/** "Special" predicate: Optional <%s> must have value equal <%s>. */
	public static <V> @Nullable String valueEqual$(@Nonnull ValueTrait<?, ?> opt, V expected) {
		Null.nonNullArg(opt, "opt");
		return valueEqual(opt, expected) ? null : String.format("Optional <%s> must have value equal <%s>.", opt, expected);
	}
	/** Predicate: Optional <%s> must NOT have value equal <%s>..*/
	public static <V> boolean valueNotEqual(@Nonnull ValueTrait<?, ?> opt, V expected) {
		Null.nonNullArg(opt, "opt");
		return !valueEqual(opt, expected);
	}

	/** "Special" predicate: Optional <%s> must NOT have value equal <%s>. */
	public static <V> @Nullable String valueNotEqual$(@Nonnull ValueTrait<?, ?> opt, V expected) {
		Null.nonNullArg(opt, "opt");
		return valueNotEqual(opt, expected) ? null : String.format("Optional <%s> must NOT have value equal <%s>.", opt, expected);
	}

	/** Predicate: Optional <%s> must have value equal <%s>.*/
	public static <V> boolean sameValue(@Nonnull ValueTrait<?, ?> opt, V expected) {
		Null.nonNullArg(opt, "opt");
		return opt.nullable() == expected;
	}

	/** "Special" predicate: Optional <%s> must have value equal <%s>. */
	public static <V> @Nullable String sameValue$(@Nonnull ValueTrait<?, ?> opt, V expected) {
		Null.nonNullArg(opt, "opt");
		return sameValue(opt, expected) ? null : String.format("Optional <%s> must have value equal <%s>.", opt, expected);
	}
	/** Predicate: Optional <%s> must NOT have value equal <%s>..*/
	public static <V> boolean notSameValue(@Nonnull ValueTrait<?, ?> opt, V expected) {
		Null.nonNullArg(opt, "opt");
		return !sameValue(opt, expected);
	}

	/** "Special" predicate: Optional <%s> must NOT have value equal <%s>. */
	public static <V> @Nullable String notSameValue$(@Nonnull ValueTrait<?, ?> opt, V expected) {
		Null.nonNullArg(opt, "opt");
		return notSameValue(opt, expected) ? null : String.format("Optional <%s> must NOT have value equal <%s>.", opt, expected);
	}

	/** Predicate: Optional <%s> must have value.*/
	public static <V> boolean valuePresent(@Nonnull OptByteTrait<?> opt) {
		Null.nonNullArg(opt, "opt");
		return opt.isPresent();
	}

	/** "Special" predicate: Optional <%s> must have value. */
	public static <V> @Nullable String valuePresent$(@Nonnull OptByteTrait<?> opt) {
		Null.nonNullArg(opt, "opt");
		return valuePresent(opt) ? null : String.format("Optional <%s> must have value.", opt);
	}
	/** Predicate: Optional <%s> must NOT have value..*/
	public static <V> boolean noValuePresent(@Nonnull OptByteTrait<?> opt) {
		Null.nonNullArg(opt, "opt");
		return !valuePresent(opt);
	}

	/** "Special" predicate: Optional <%s> must NOT have value. */
	public static <V> @Nullable String noValuePresent$(@Nonnull OptByteTrait<?> opt) {
		Null.nonNullArg(opt, "opt");
		return noValuePresent(opt) ? null : String.format("Optional <%s> must NOT have value.", opt);
	}

	/** Predicate: Optional <%s> must be void.*/
	public static <V> boolean Void(@Nonnull OptByteTrait<?> opt) {
		Null.nonNullArg(opt, "opt");
		return opt.isVoid();
	}

	/** "Special" predicate: Optional <%s> must be void. */
	public static <V> @Nullable String Void$(@Nonnull OptByteTrait<?> opt) {
		Null.nonNullArg(opt, "opt");
		return Void(opt) ? null : String.format("Optional <%s> must be void.", opt);
	}
	/** Predicate: Optional <%s> must NOT be void..*/
	public static <V> boolean notVoid(@Nonnull OptByteTrait<?> opt) {
		Null.nonNullArg(opt, "opt");
		return !Void(opt);
	}

	/** "Special" predicate: Optional <%s> must NOT be void. */
	public static <V> @Nullable String notVoid$(@Nonnull OptByteTrait<?> opt) {
		Null.nonNullArg(opt, "opt");
		return notVoid(opt) ? null : String.format("Optional <%s> must NOT be void.", opt);
	}

	/** Predicate: Optional <%s> must have value equal <%s>.*/
	public static <V> boolean valueEqual(@Nonnull OptByteTrait<?> opt, byte expected) {
		Null.nonNullArg(opt, "opt");
		return opt.isPresent() && opt.value() == expected;
	}

	/** "Special" predicate: Optional <%s> must have value equal <%s>. */
	public static <V> @Nullable String valueEqual$(@Nonnull OptByteTrait<?> opt, byte expected) {
		Null.nonNullArg(opt, "opt");
		return valueEqual(opt, expected) ? null : String.format("Optional <%s> must have value equal <%s>.", opt, expected);
	}
	/** Predicate: Optional <%s> must NOT have value equal <%s>..*/
	public static <V> boolean valueNotEqual(@Nonnull OptByteTrait<?> opt, byte expected) {
		Null.nonNullArg(opt, "opt");
		return !valueEqual(opt, expected);
	}

	/** "Special" predicate: Optional <%s> must NOT have value equal <%s>. */
	public static <V> @Nullable String valueNotEqual$(@Nonnull OptByteTrait<?> opt, byte expected) {
		Null.nonNullArg(opt, "opt");
		return valueNotEqual(opt, expected) ? null : String.format("Optional <%s> must NOT have value equal <%s>.", opt, expected);
	}

	/** Predicate: Optional <%s> must have value equal <%s>.*/
	public static <V> boolean valueEqual(@Nonnull ByteValueTrait<?> opt, byte expected) {
		Null.nonNullArg(opt, "opt");
		return opt.value() == expected;
	}

	/** "Special" predicate: Optional <%s> must have value equal <%s>. */
	public static <V> @Nullable String valueEqual$(@Nonnull ByteValueTrait<?> opt, byte expected) {
		Null.nonNullArg(opt, "opt");
		return valueEqual(opt, expected) ? null : String.format("Optional <%s> must have value equal <%s>.", opt, expected);
	}
	/** Predicate: Optional <%s> must NOT have value equal <%s>..*/
	public static <V> boolean valueNotEqual(@Nonnull ByteValueTrait<?> opt, byte expected) {
		Null.nonNullArg(opt, "opt");
		return !valueEqual(opt, expected);
	}

	/** "Special" predicate: Optional <%s> must NOT have value equal <%s>. */
	public static <V> @Nullable String valueNotEqual$(@Nonnull ByteValueTrait<?> opt, byte expected) {
		Null.nonNullArg(opt, "opt");
		return valueNotEqual(opt, expected) ? null : String.format("Optional <%s> must NOT have value equal <%s>.", opt, expected);
	}

	/** Predicate: Optional <%s> must have value.*/
	public static <V> boolean valuePresent(@Nonnull OptDblTrait<?> opt) {
		Null.nonNullArg(opt, "opt");
		return opt.isPresent();
	}

	/** "Special" predicate: Optional <%s> must have value. */
	public static <V> @Nullable String valuePresent$(@Nonnull OptDblTrait<?> opt) {
		Null.nonNullArg(opt, "opt");
		return valuePresent(opt) ? null : String.format("Optional <%s> must have value.", opt);
	}
	/** Predicate: Optional <%s> must NOT have value..*/
	public static <V> boolean noValuePresent(@Nonnull OptDblTrait<?> opt) {
		Null.nonNullArg(opt, "opt");
		return !valuePresent(opt);
	}

	/** "Special" predicate: Optional <%s> must NOT have value. */
	public static <V> @Nullable String noValuePresent$(@Nonnull OptDblTrait<?> opt) {
		Null.nonNullArg(opt, "opt");
		return noValuePresent(opt) ? null : String.format("Optional <%s> must NOT have value.", opt);
	}

	/** Predicate: Optional <%s> must be void.*/
	public static <V> boolean Void(@Nonnull OptDblTrait<?> opt) {
		Null.nonNullArg(opt, "opt");
		return opt.isVoid();
	}

	/** "Special" predicate: Optional <%s> must be void. */
	public static <V> @Nullable String Void$(@Nonnull OptDblTrait<?> opt) {
		Null.nonNullArg(opt, "opt");
		return Void(opt) ? null : String.format("Optional <%s> must be void.", opt);
	}
	/** Predicate: Optional <%s> must NOT be void..*/
	public static <V> boolean notVoid(@Nonnull OptDblTrait<?> opt) {
		Null.nonNullArg(opt, "opt");
		return !Void(opt);
	}

	/** "Special" predicate: Optional <%s> must NOT be void. */
	public static <V> @Nullable String notVoid$(@Nonnull OptDblTrait<?> opt) {
		Null.nonNullArg(opt, "opt");
		return notVoid(opt) ? null : String.format("Optional <%s> must NOT be void.", opt);
	}

	/** Predicate: Optional <%s> must have value equal <%s>.*/
	public static <V> boolean valueEqual(@Nonnull OptDblTrait<?> opt, double expected) {
		Null.nonNullArg(opt, "opt");
		return opt.isPresent() && opt.value() == expected;
	}

	/** "Special" predicate: Optional <%s> must have value equal <%s>. */
	public static <V> @Nullable String valueEqual$(@Nonnull OptDblTrait<?> opt, double expected) {
		Null.nonNullArg(opt, "opt");
		return valueEqual(opt, expected) ? null : String.format("Optional <%s> must have value equal <%s>.", opt, expected);
	}
	/** Predicate: Optional <%s> must NOT have value equal <%s>..*/
	public static <V> boolean valueNotEqual(@Nonnull OptDblTrait<?> opt, double expected) {
		Null.nonNullArg(opt, "opt");
		return !valueEqual(opt, expected);
	}

	/** "Special" predicate: Optional <%s> must NOT have value equal <%s>. */
	public static <V> @Nullable String valueNotEqual$(@Nonnull OptDblTrait<?> opt, double expected) {
		Null.nonNullArg(opt, "opt");
		return valueNotEqual(opt, expected) ? null : String.format("Optional <%s> must NOT have value equal <%s>.", opt, expected);
	}

	/** Predicate: Optional <%s> must have value equal <%s>.*/
	public static <V> boolean valueEqual(@Nonnull DblValueTrait<?> opt, double expected) {
		Null.nonNullArg(opt, "opt");
		return opt.value() == expected;
	}

	/** "Special" predicate: Optional <%s> must have value equal <%s>. */
	public static <V> @Nullable String valueEqual$(@Nonnull DblValueTrait<?> opt, double expected) {
		Null.nonNullArg(opt, "opt");
		return valueEqual(opt, expected) ? null : String.format("Optional <%s> must have value equal <%s>.", opt, expected);
	}
	/** Predicate: Optional <%s> must NOT have value equal <%s>..*/
	public static <V> boolean valueNotEqual(@Nonnull DblValueTrait<?> opt, double expected) {
		Null.nonNullArg(opt, "opt");
		return !valueEqual(opt, expected);
	}

	/** "Special" predicate: Optional <%s> must NOT have value equal <%s>. */
	public static <V> @Nullable String valueNotEqual$(@Nonnull DblValueTrait<?> opt, double expected) {
		Null.nonNullArg(opt, "opt");
		return valueNotEqual(opt, expected) ? null : String.format("Optional <%s> must NOT have value equal <%s>.", opt, expected);
	}

	/** Predicate: Optional <%s> must have value.*/
	public static <V> boolean valuePresent(@Nonnull OptCharTrait<?> opt) {
		Null.nonNullArg(opt, "opt");
		return opt.isPresent();
	}

	/** "Special" predicate: Optional <%s> must have value. */
	public static <V> @Nullable String valuePresent$(@Nonnull OptCharTrait<?> opt) {
		Null.nonNullArg(opt, "opt");
		return valuePresent(opt) ? null : String.format("Optional <%s> must have value.", opt);
	}
	/** Predicate: Optional <%s> must NOT have value..*/
	public static <V> boolean noValuePresent(@Nonnull OptCharTrait<?> opt) {
		Null.nonNullArg(opt, "opt");
		return !valuePresent(opt);
	}

	/** "Special" predicate: Optional <%s> must NOT have value. */
	public static <V> @Nullable String noValuePresent$(@Nonnull OptCharTrait<?> opt) {
		Null.nonNullArg(opt, "opt");
		return noValuePresent(opt) ? null : String.format("Optional <%s> must NOT have value.", opt);
	}

	/** Predicate: Optional <%s> must be void.*/
	public static <V> boolean Void(@Nonnull OptCharTrait<?> opt) {
		Null.nonNullArg(opt, "opt");
		return opt.isVoid();
	}

	/** "Special" predicate: Optional <%s> must be void. */
	public static <V> @Nullable String Void$(@Nonnull OptCharTrait<?> opt) {
		Null.nonNullArg(opt, "opt");
		return Void(opt) ? null : String.format("Optional <%s> must be void.", opt);
	}
	/** Predicate: Optional <%s> must NOT be void..*/
	public static <V> boolean notVoid(@Nonnull OptCharTrait<?> opt) {
		Null.nonNullArg(opt, "opt");
		return !Void(opt);
	}

	/** "Special" predicate: Optional <%s> must NOT be void. */
	public static <V> @Nullable String notVoid$(@Nonnull OptCharTrait<?> opt) {
		Null.nonNullArg(opt, "opt");
		return notVoid(opt) ? null : String.format("Optional <%s> must NOT be void.", opt);
	}

	/** Predicate: Optional <%s> must have value equal <%s>.*/
	public static <V> boolean valueEqual(@Nonnull OptCharTrait<?> opt, char expected) {
		Null.nonNullArg(opt, "opt");
		return opt.isPresent() && opt.value() == expected;
	}

	/** "Special" predicate: Optional <%s> must have value equal <%s>. */
	public static <V> @Nullable String valueEqual$(@Nonnull OptCharTrait<?> opt, char expected) {
		Null.nonNullArg(opt, "opt");
		return valueEqual(opt, expected) ? null : String.format("Optional <%s> must have value equal <%s>.", opt, expected);
	}
	/** Predicate: Optional <%s> must NOT have value equal <%s>..*/
	public static <V> boolean valueNotEqual(@Nonnull OptCharTrait<?> opt, char expected) {
		Null.nonNullArg(opt, "opt");
		return !valueEqual(opt, expected);
	}

	/** "Special" predicate: Optional <%s> must NOT have value equal <%s>. */
	public static <V> @Nullable String valueNotEqual$(@Nonnull OptCharTrait<?> opt, char expected) {
		Null.nonNullArg(opt, "opt");
		return valueNotEqual(opt, expected) ? null : String.format("Optional <%s> must NOT have value equal <%s>.", opt, expected);
	}

	/** Predicate: Optional <%s> must have value equal <%s>.*/
	public static <V> boolean valueEqual(@Nonnull CharValueTrait<?> opt, char expected) {
		Null.nonNullArg(opt, "opt");
		return opt.value() == expected;
	}

	/** "Special" predicate: Optional <%s> must have value equal <%s>. */
	public static <V> @Nullable String valueEqual$(@Nonnull CharValueTrait<?> opt, char expected) {
		Null.nonNullArg(opt, "opt");
		return valueEqual(opt, expected) ? null : String.format("Optional <%s> must have value equal <%s>.", opt, expected);
	}
	/** Predicate: Optional <%s> must NOT have value equal <%s>..*/
	public static <V> boolean valueNotEqual(@Nonnull CharValueTrait<?> opt, char expected) {
		Null.nonNullArg(opt, "opt");
		return !valueEqual(opt, expected);
	}

	/** "Special" predicate: Optional <%s> must NOT have value equal <%s>. */
	public static <V> @Nullable String valueNotEqual$(@Nonnull CharValueTrait<?> opt, char expected) {
		Null.nonNullArg(opt, "opt");
		return valueNotEqual(opt, expected) ? null : String.format("Optional <%s> must NOT have value equal <%s>.", opt, expected);
	}

	/** Predicate: Optional <%s> must have value.*/
	public static <V> boolean valuePresent(@Nonnull OptSrtTrait<?> opt) {
		Null.nonNullArg(opt, "opt");
		return opt.isPresent();
	}

	/** "Special" predicate: Optional <%s> must have value. */
	public static <V> @Nullable String valuePresent$(@Nonnull OptSrtTrait<?> opt) {
		Null.nonNullArg(opt, "opt");
		return valuePresent(opt) ? null : String.format("Optional <%s> must have value.", opt);
	}
	/** Predicate: Optional <%s> must NOT have value..*/
	public static <V> boolean noValuePresent(@Nonnull OptSrtTrait<?> opt) {
		Null.nonNullArg(opt, "opt");
		return !valuePresent(opt);
	}

	/** "Special" predicate: Optional <%s> must NOT have value. */
	public static <V> @Nullable String noValuePresent$(@Nonnull OptSrtTrait<?> opt) {
		Null.nonNullArg(opt, "opt");
		return noValuePresent(opt) ? null : String.format("Optional <%s> must NOT have value.", opt);
	}

	/** Predicate: Optional <%s> must be void.*/
	public static <V> boolean Void(@Nonnull OptSrtTrait<?> opt) {
		Null.nonNullArg(opt, "opt");
		return opt.isVoid();
	}

	/** "Special" predicate: Optional <%s> must be void. */
	public static <V> @Nullable String Void$(@Nonnull OptSrtTrait<?> opt) {
		Null.nonNullArg(opt, "opt");
		return Void(opt) ? null : String.format("Optional <%s> must be void.", opt);
	}
	/** Predicate: Optional <%s> must NOT be void..*/
	public static <V> boolean notVoid(@Nonnull OptSrtTrait<?> opt) {
		Null.nonNullArg(opt, "opt");
		return !Void(opt);
	}

	/** "Special" predicate: Optional <%s> must NOT be void. */
	public static <V> @Nullable String notVoid$(@Nonnull OptSrtTrait<?> opt) {
		Null.nonNullArg(opt, "opt");
		return notVoid(opt) ? null : String.format("Optional <%s> must NOT be void.", opt);
	}

	/** Predicate: Optional <%s> must have value equal <%s>.*/
	public static <V> boolean valueEqual(@Nonnull OptSrtTrait<?> opt, short expected) {
		Null.nonNullArg(opt, "opt");
		return opt.isPresent() && opt.value() == expected;
	}

	/** "Special" predicate: Optional <%s> must have value equal <%s>. */
	public static <V> @Nullable String valueEqual$(@Nonnull OptSrtTrait<?> opt, short expected) {
		Null.nonNullArg(opt, "opt");
		return valueEqual(opt, expected) ? null : String.format("Optional <%s> must have value equal <%s>.", opt, expected);
	}
	/** Predicate: Optional <%s> must NOT have value equal <%s>..*/
	public static <V> boolean valueNotEqual(@Nonnull OptSrtTrait<?> opt, short expected) {
		Null.nonNullArg(opt, "opt");
		return !valueEqual(opt, expected);
	}

	/** "Special" predicate: Optional <%s> must NOT have value equal <%s>. */
	public static <V> @Nullable String valueNotEqual$(@Nonnull OptSrtTrait<?> opt, short expected) {
		Null.nonNullArg(opt, "opt");
		return valueNotEqual(opt, expected) ? null : String.format("Optional <%s> must NOT have value equal <%s>.", opt, expected);
	}

	/** Predicate: Optional <%s> must have value equal <%s>.*/
	public static <V> boolean valueEqual(@Nonnull SrtValueTrait<?> opt, short expected) {
		Null.nonNullArg(opt, "opt");
		return opt.value() == expected;
	}

	/** "Special" predicate: Optional <%s> must have value equal <%s>. */
	public static <V> @Nullable String valueEqual$(@Nonnull SrtValueTrait<?> opt, short expected) {
		Null.nonNullArg(opt, "opt");
		return valueEqual(opt, expected) ? null : String.format("Optional <%s> must have value equal <%s>.", opt, expected);
	}
	/** Predicate: Optional <%s> must NOT have value equal <%s>..*/
	public static <V> boolean valueNotEqual(@Nonnull SrtValueTrait<?> opt, short expected) {
		Null.nonNullArg(opt, "opt");
		return !valueEqual(opt, expected);
	}

	/** "Special" predicate: Optional <%s> must NOT have value equal <%s>. */
	public static <V> @Nullable String valueNotEqual$(@Nonnull SrtValueTrait<?> opt, short expected) {
		Null.nonNullArg(opt, "opt");
		return valueNotEqual(opt, expected) ? null : String.format("Optional <%s> must NOT have value equal <%s>.", opt, expected);
	}

	/** Predicate: Optional <%s> must have value.*/
	public static <V> boolean valuePresent(@Nonnull OptFltTrait<?> opt) {
		Null.nonNullArg(opt, "opt");
		return opt.isPresent();
	}

	/** "Special" predicate: Optional <%s> must have value. */
	public static <V> @Nullable String valuePresent$(@Nonnull OptFltTrait<?> opt) {
		Null.nonNullArg(opt, "opt");
		return valuePresent(opt) ? null : String.format("Optional <%s> must have value.", opt);
	}
	/** Predicate: Optional <%s> must NOT have value..*/
	public static <V> boolean noValuePresent(@Nonnull OptFltTrait<?> opt) {
		Null.nonNullArg(opt, "opt");
		return !valuePresent(opt);
	}

	/** "Special" predicate: Optional <%s> must NOT have value. */
	public static <V> @Nullable String noValuePresent$(@Nonnull OptFltTrait<?> opt) {
		Null.nonNullArg(opt, "opt");
		return noValuePresent(opt) ? null : String.format("Optional <%s> must NOT have value.", opt);
	}

	/** Predicate: Optional <%s> must be void.*/
	public static <V> boolean Void(@Nonnull OptFltTrait<?> opt) {
		Null.nonNullArg(opt, "opt");
		return opt.isVoid();
	}

	/** "Special" predicate: Optional <%s> must be void. */
	public static <V> @Nullable String Void$(@Nonnull OptFltTrait<?> opt) {
		Null.nonNullArg(opt, "opt");
		return Void(opt) ? null : String.format("Optional <%s> must be void.", opt);
	}
	/** Predicate: Optional <%s> must NOT be void..*/
	public static <V> boolean notVoid(@Nonnull OptFltTrait<?> opt) {
		Null.nonNullArg(opt, "opt");
		return !Void(opt);
	}

	/** "Special" predicate: Optional <%s> must NOT be void. */
	public static <V> @Nullable String notVoid$(@Nonnull OptFltTrait<?> opt) {
		Null.nonNullArg(opt, "opt");
		return notVoid(opt) ? null : String.format("Optional <%s> must NOT be void.", opt);
	}

	/** Predicate: Optional <%s> must have value equal <%s>.*/
	public static <V> boolean valueEqual(@Nonnull OptFltTrait<?> opt, float expected) {
		Null.nonNullArg(opt, "opt");
		return opt.isPresent() && opt.value() == expected;
	}

	/** "Special" predicate: Optional <%s> must have value equal <%s>. */
	public static <V> @Nullable String valueEqual$(@Nonnull OptFltTrait<?> opt, float expected) {
		Null.nonNullArg(opt, "opt");
		return valueEqual(opt, expected) ? null : String.format("Optional <%s> must have value equal <%s>.", opt, expected);
	}
	/** Predicate: Optional <%s> must NOT have value equal <%s>..*/
	public static <V> boolean valueNotEqual(@Nonnull OptFltTrait<?> opt, float expected) {
		Null.nonNullArg(opt, "opt");
		return !valueEqual(opt, expected);
	}

	/** "Special" predicate: Optional <%s> must NOT have value equal <%s>. */
	public static <V> @Nullable String valueNotEqual$(@Nonnull OptFltTrait<?> opt, float expected) {
		Null.nonNullArg(opt, "opt");
		return valueNotEqual(opt, expected) ? null : String.format("Optional <%s> must NOT have value equal <%s>.", opt, expected);
	}

	/** Predicate: Optional <%s> must have value equal <%s>.*/
	public static <V> boolean valueEqual(@Nonnull FltValueTrait<?> opt, float expected) {
		Null.nonNullArg(opt, "opt");
		return opt.value() == expected;
	}

	/** "Special" predicate: Optional <%s> must have value equal <%s>. */
	public static <V> @Nullable String valueEqual$(@Nonnull FltValueTrait<?> opt, float expected) {
		Null.nonNullArg(opt, "opt");
		return valueEqual(opt, expected) ? null : String.format("Optional <%s> must have value equal <%s>.", opt, expected);
	}
	/** Predicate: Optional <%s> must NOT have value equal <%s>..*/
	public static <V> boolean valueNotEqual(@Nonnull FltValueTrait<?> opt, float expected) {
		Null.nonNullArg(opt, "opt");
		return !valueEqual(opt, expected);
	}

	/** "Special" predicate: Optional <%s> must NOT have value equal <%s>. */
	public static <V> @Nullable String valueNotEqual$(@Nonnull FltValueTrait<?> opt, float expected) {
		Null.nonNullArg(opt, "opt");
		return valueNotEqual(opt, expected) ? null : String.format("Optional <%s> must NOT have value equal <%s>.", opt, expected);
	}

	/** Predicate: Optional <%s> must have value.*/
	public static <V> boolean valuePresent(@Nonnull OptIntTrait<?> opt) {
		Null.nonNullArg(opt, "opt");
		return opt.isPresent();
	}

	/** "Special" predicate: Optional <%s> must have value. */
	public static <V> @Nullable String valuePresent$(@Nonnull OptIntTrait<?> opt) {
		Null.nonNullArg(opt, "opt");
		return valuePresent(opt) ? null : String.format("Optional <%s> must have value.", opt);
	}
	/** Predicate: Optional <%s> must NOT have value..*/
	public static <V> boolean noValuePresent(@Nonnull OptIntTrait<?> opt) {
		Null.nonNullArg(opt, "opt");
		return !valuePresent(opt);
	}

	/** "Special" predicate: Optional <%s> must NOT have value. */
	public static <V> @Nullable String noValuePresent$(@Nonnull OptIntTrait<?> opt) {
		Null.nonNullArg(opt, "opt");
		return noValuePresent(opt) ? null : String.format("Optional <%s> must NOT have value.", opt);
	}

	/** Predicate: Optional <%s> must be void.*/
	public static <V> boolean Void(@Nonnull OptIntTrait<?> opt) {
		Null.nonNullArg(opt, "opt");
		return opt.isVoid();
	}

	/** "Special" predicate: Optional <%s> must be void. */
	public static <V> @Nullable String Void$(@Nonnull OptIntTrait<?> opt) {
		Null.nonNullArg(opt, "opt");
		return Void(opt) ? null : String.format("Optional <%s> must be void.", opt);
	}
	/** Predicate: Optional <%s> must NOT be void..*/
	public static <V> boolean notVoid(@Nonnull OptIntTrait<?> opt) {
		Null.nonNullArg(opt, "opt");
		return !Void(opt);
	}

	/** "Special" predicate: Optional <%s> must NOT be void. */
	public static <V> @Nullable String notVoid$(@Nonnull OptIntTrait<?> opt) {
		Null.nonNullArg(opt, "opt");
		return notVoid(opt) ? null : String.format("Optional <%s> must NOT be void.", opt);
	}

	/** Predicate: Optional <%s> must have value equal <%s>.*/
	public static <V> boolean valueEqual(@Nonnull OptIntTrait<?> opt, int expected) {
		Null.nonNullArg(opt, "opt");
		return opt.isPresent() && opt.value() == expected;
	}

	/** "Special" predicate: Optional <%s> must have value equal <%s>. */
	public static <V> @Nullable String valueEqual$(@Nonnull OptIntTrait<?> opt, int expected) {
		Null.nonNullArg(opt, "opt");
		return valueEqual(opt, expected) ? null : String.format("Optional <%s> must have value equal <%s>.", opt, expected);
	}
	/** Predicate: Optional <%s> must NOT have value equal <%s>..*/
	public static <V> boolean valueNotEqual(@Nonnull OptIntTrait<?> opt, int expected) {
		Null.nonNullArg(opt, "opt");
		return !valueEqual(opt, expected);
	}

	/** "Special" predicate: Optional <%s> must NOT have value equal <%s>. */
	public static <V> @Nullable String valueNotEqual$(@Nonnull OptIntTrait<?> opt, int expected) {
		Null.nonNullArg(opt, "opt");
		return valueNotEqual(opt, expected) ? null : String.format("Optional <%s> must NOT have value equal <%s>.", opt, expected);
	}

	/** Predicate: Optional <%s> must have value equal <%s>.*/
	public static <V> boolean valueEqual(@Nonnull IntValueTrait<?> opt, int expected) {
		Null.nonNullArg(opt, "opt");
		return opt.value() == expected;
	}

	/** "Special" predicate: Optional <%s> must have value equal <%s>. */
	public static <V> @Nullable String valueEqual$(@Nonnull IntValueTrait<?> opt, int expected) {
		Null.nonNullArg(opt, "opt");
		return valueEqual(opt, expected) ? null : String.format("Optional <%s> must have value equal <%s>.", opt, expected);
	}
	/** Predicate: Optional <%s> must NOT have value equal <%s>..*/
	public static <V> boolean valueNotEqual(@Nonnull IntValueTrait<?> opt, int expected) {
		Null.nonNullArg(opt, "opt");
		return !valueEqual(opt, expected);
	}

	/** "Special" predicate: Optional <%s> must NOT have value equal <%s>. */
	public static <V> @Nullable String valueNotEqual$(@Nonnull IntValueTrait<?> opt, int expected) {
		Null.nonNullArg(opt, "opt");
		return valueNotEqual(opt, expected) ? null : String.format("Optional <%s> must NOT have value equal <%s>.", opt, expected);
	}

	/** Predicate: Optional <%s> must have value.*/
	public static <V> boolean valuePresent(@Nonnull OptLongTrait<?> opt) {
		Null.nonNullArg(opt, "opt");
		return opt.isPresent();
	}

	/** "Special" predicate: Optional <%s> must have value. */
	public static <V> @Nullable String valuePresent$(@Nonnull OptLongTrait<?> opt) {
		Null.nonNullArg(opt, "opt");
		return valuePresent(opt) ? null : String.format("Optional <%s> must have value.", opt);
	}
	/** Predicate: Optional <%s> must NOT have value..*/
	public static <V> boolean noValuePresent(@Nonnull OptLongTrait<?> opt) {
		Null.nonNullArg(opt, "opt");
		return !valuePresent(opt);
	}

	/** "Special" predicate: Optional <%s> must NOT have value. */
	public static <V> @Nullable String noValuePresent$(@Nonnull OptLongTrait<?> opt) {
		Null.nonNullArg(opt, "opt");
		return noValuePresent(opt) ? null : String.format("Optional <%s> must NOT have value.", opt);
	}

	/** Predicate: Optional <%s> must be void.*/
	public static <V> boolean Void(@Nonnull OptLongTrait<?> opt) {
		Null.nonNullArg(opt, "opt");
		return opt.isVoid();
	}

	/** "Special" predicate: Optional <%s> must be void. */
	public static <V> @Nullable String Void$(@Nonnull OptLongTrait<?> opt) {
		Null.nonNullArg(opt, "opt");
		return Void(opt) ? null : String.format("Optional <%s> must be void.", opt);
	}
	/** Predicate: Optional <%s> must NOT be void..*/
	public static <V> boolean notVoid(@Nonnull OptLongTrait<?> opt) {
		Null.nonNullArg(opt, "opt");
		return !Void(opt);
	}

	/** "Special" predicate: Optional <%s> must NOT be void. */
	public static <V> @Nullable String notVoid$(@Nonnull OptLongTrait<?> opt) {
		Null.nonNullArg(opt, "opt");
		return notVoid(opt) ? null : String.format("Optional <%s> must NOT be void.", opt);
	}

	/** Predicate: Optional <%s> must have value equal <%s>.*/
	public static <V> boolean valueEqual(@Nonnull OptLongTrait<?> opt, long expected) {
		Null.nonNullArg(opt, "opt");
		return opt.isPresent() && opt.value() == expected;
	}

	/** "Special" predicate: Optional <%s> must have value equal <%s>. */
	public static <V> @Nullable String valueEqual$(@Nonnull OptLongTrait<?> opt, long expected) {
		Null.nonNullArg(opt, "opt");
		return valueEqual(opt, expected) ? null : String.format("Optional <%s> must have value equal <%s>.", opt, expected);
	}
	/** Predicate: Optional <%s> must NOT have value equal <%s>..*/
	public static <V> boolean valueNotEqual(@Nonnull OptLongTrait<?> opt, long expected) {
		Null.nonNullArg(opt, "opt");
		return !valueEqual(opt, expected);
	}

	/** "Special" predicate: Optional <%s> must NOT have value equal <%s>. */
	public static <V> @Nullable String valueNotEqual$(@Nonnull OptLongTrait<?> opt, long expected) {
		Null.nonNullArg(opt, "opt");
		return valueNotEqual(opt, expected) ? null : String.format("Optional <%s> must NOT have value equal <%s>.", opt, expected);
	}

	/** Predicate: Optional <%s> must have value equal <%s>.*/
	public static <V> boolean valueEqual(@Nonnull LongValueTrait<?> opt, long expected) {
		Null.nonNullArg(opt, "opt");
		return opt.value() == expected;
	}

	/** "Special" predicate: Optional <%s> must have value equal <%s>. */
	public static <V> @Nullable String valueEqual$(@Nonnull LongValueTrait<?> opt, long expected) {
		Null.nonNullArg(opt, "opt");
		return valueEqual(opt, expected) ? null : String.format("Optional <%s> must have value equal <%s>.", opt, expected);
	}
	/** Predicate: Optional <%s> must NOT have value equal <%s>..*/
	public static <V> boolean valueNotEqual(@Nonnull LongValueTrait<?> opt, long expected) {
		Null.nonNullArg(opt, "opt");
		return !valueEqual(opt, expected);
	}

	/** "Special" predicate: Optional <%s> must NOT have value equal <%s>. */
	public static <V> @Nullable String valueNotEqual$(@Nonnull LongValueTrait<?> opt, long expected) {
		Null.nonNullArg(opt, "opt");
		return valueNotEqual(opt, expected) ? null : String.format("Optional <%s> must NOT have value equal <%s>.", opt, expected);
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
	* '$' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K> LFunction<K, String> haveToBool$(@Nonnull LPredicate<K> extractor, @Nonnull LBoolFunction<String> specialPredicate) {
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
	* '$' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K> LFunction<K, String> hasToBool$(@Nonnull LPredicate<K> extractor, @Nonnull LBoolFunction<String> specialPredicate) {
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
	* '$' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K> LFunction<K, String> haveToBool$(@Nonnull LPredicate<K> extractor, boolean a2, @Nonnull LBiBoolFunction<String> specialPredicate) {
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
	* '$' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K> LFunction<K, String> haveToBool$(@Nonnull LPredicate<K> extractor, @Nonnull LBiBoolFunction<String> specialPredicate, boolean a2) {
		return haveToBool$(extractor, a2, specialPredicate);
	}

	/** 'ToBool' - first, actual value will be converted to primitive type (contrary to the object). */
	public static @Nonnull <K> LPredicate<K> hasToBool(@Nonnull LPredicate<K> extractor, boolean a2, @Nonnull LLogicalBinaryOperator operator) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(operator, "operator");
		return k -> operator.apply(extractor.test(k), a2);
	}

	/**
	* 'ToBool' - first, actual value will be converted to primitive type (contrary to the object).
	* '$' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K> LFunction<K, String> hasToBool$(@Nonnull LPredicate<K> extractor, boolean a2, @Nonnull LBiBoolFunction<String> specialPredicate) {
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
	* '$' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K> LFunction<K, String> hasToBool$(@Nonnull LPredicate<K> extractor, @Nonnull LBiBoolFunction<String> specialPredicate, boolean a2) {
		return hasToBool$(extractor, a2, specialPredicate);
	}

	/** 'ToBool' - first, actual value will be converted to primitive type (contrary to the object). */
	public static @Nonnull <K> LPredicate<K> haveToBool(@Nonnull LPredicate<K> extractor, boolean a2, boolean a3, @Nonnull LLogicalTernaryOperator operator) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(operator, "operator");
		return k -> operator.apply(extractor.test(k), a2, a3);
	}

	/**
	* 'ToBool' - first, actual value will be converted to primitive type (contrary to the object).
	* '$' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K> LFunction<K, String> haveToBool$(@Nonnull LPredicate<K> extractor, boolean a2, boolean a3, @Nonnull LTriBoolFunction<String> specialPredicate) {
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
	* '$' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K> LFunction<K, String> haveToBool$(@Nonnull LPredicate<K> extractor, @Nonnull LTriBoolFunction<String> specialPredicate, boolean a2, boolean a3) {
		return haveToBool$(extractor, a2, a3, specialPredicate);
	}

	/** 'ToBool' - first, actual value will be converted to primitive type (contrary to the object). */
	public static @Nonnull <K> LPredicate<K> hasToBool(@Nonnull LPredicate<K> extractor, boolean a2, boolean a3, @Nonnull LLogicalTernaryOperator operator) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(operator, "operator");
		return k -> operator.apply(extractor.test(k), a2, a3);
	}

	/**
	* 'ToBool' - first, actual value will be converted to primitive type (contrary to the object).
	* '$' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K> LFunction<K, String> hasToBool$(@Nonnull LPredicate<K> extractor, boolean a2, boolean a3, @Nonnull LTriBoolFunction<String> specialPredicate) {
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
	* '$' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K> LFunction<K, String> hasToBool$(@Nonnull LPredicate<K> extractor, @Nonnull LTriBoolFunction<String> specialPredicate, boolean a2, boolean a3) {
		return hasToBool$(extractor, a2, a3, specialPredicate);
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
	* '$' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K, V1> LFunction<K, String> haveToBoolWith$(@Nonnull LPredicate<K> extractor, V1 with1, @Nonnull LObjBoolFunction<? super V1, String> specialPredicate) {
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
	* '$' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K, V1> LFunction<K, String> haveToBoolWith$(@Nonnull LPredicate<K> extractor, @Nonnull LObjBoolFunction<? super V1, String> specialPredicate, V1 with1) {
		return haveToBoolWith$(extractor, with1, specialPredicate);
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
	* '$' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K, V1> LFunction<K, String> hasToBoolWith$(@Nonnull LPredicate<K> extractor, V1 with1, @Nonnull LObjBoolFunction<? super V1, String> specialPredicate) {
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
	* '$' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K, V1> LFunction<K, String> hasToBoolWith$(@Nonnull LPredicate<K> extractor, @Nonnull LObjBoolFunction<? super V1, String> specialPredicate, V1 with1) {
		return hasToBoolWith$(extractor, with1, specialPredicate);
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
	* '$' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K, V1, V2> LFunction<K, String> haveToBoolWith$(@Nonnull LPredicate<K> extractor, V1 with1, V2 with2, @Nonnull LBiObjBoolFunction<? super V1, ? super V2, String> specialPredicate) {
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
	* '$' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K, V1, V2> LFunction<K, String> haveToBoolWith$(@Nonnull LPredicate<K> extractor, @Nonnull LBiObjBoolFunction<? super V1, ? super V2, String> specialPredicate, V1 with1, V2 with2) {
		return haveToBoolWith$(extractor, with1, with2, specialPredicate);
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
	* '$' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K, V1, V2> LFunction<K, String> hasToBoolWith$(@Nonnull LPredicate<K> extractor, V1 with1, V2 with2, @Nonnull LBiObjBoolFunction<? super V1, ? super V2, String> specialPredicate) {
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
	* '$' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K, V1, V2> LFunction<K, String> hasToBoolWith$(@Nonnull LPredicate<K> extractor, @Nonnull LBiObjBoolFunction<? super V1, ? super V2, String> specialPredicate, V1 with1, V2 with2) {
		return hasToBoolWith$(extractor, with1, with2, specialPredicate);
	}

	public static @Nonnull <K, T> LPredicate<K> have(@Nonnull LFunction<K, T> extractor, @Nonnull LPredicate<? super T> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.apply(k));
	}

	/** '$' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied). */
	public static @Nonnull <K, T> LFunction<K, String> have$(@Nonnull LFunction<K, T> extractor, @Nonnull LFunction<? super T, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(extractor.apply(k));
	}

	public static @Nonnull <K, T> LPredicate<K> has(@Nonnull LFunction<K, T> extractor, @Nonnull LPredicate<? super T> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.apply(k));
	}

	/** '$' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied). */
	public static @Nonnull <K, T> LFunction<K, String> has$(@Nonnull LFunction<K, T> extractor, @Nonnull LFunction<? super T, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(extractor.apply(k));
	}

	public static @Nonnull <K, T> LPredicate<K> uniHave(@Nonnull LFunction<K, T> extractor, T a2, @Nonnull LBiPredicate<? super T, ? super T> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.apply(k), a2);
	}

	/** '$' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied). */
	public static @Nonnull <K, T> LFunction<K, String> uniHave$(@Nonnull LFunction<K, T> extractor, T a2, @Nonnull LBiFunction<? super T, ? super T, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(extractor.apply(k), a2);
	}

	public static @Nonnull <K, T> LPredicate<K> uniHave(@Nonnull LFunction<K, T> extractor, @Nonnull LBiPredicate<? super T, ? super T> predicate, T a2) {
		return uniHave(extractor, a2, predicate);
	}

	/** '$' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied). */
	public static @Nonnull <K, T> LFunction<K, String> uniHave$(@Nonnull LFunction<K, T> extractor, @Nonnull LBiFunction<? super T, ? super T, String> specialPredicate, T a2) {
		return uniHave$(extractor, a2, specialPredicate);
	}

	public static @Nonnull <K, T> LPredicate<K> uniHas(@Nonnull LFunction<K, T> extractor, T a2, @Nonnull LBiPredicate<? super T, ? super T> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.apply(k), a2);
	}

	/** '$' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied). */
	public static @Nonnull <K, T> LFunction<K, String> uniHas$(@Nonnull LFunction<K, T> extractor, T a2, @Nonnull LBiFunction<? super T, ? super T, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(extractor.apply(k), a2);
	}

	public static @Nonnull <K, T> LPredicate<K> uniHas(@Nonnull LFunction<K, T> extractor, @Nonnull LBiPredicate<? super T, ? super T> predicate, T a2) {
		return uniHas(extractor, a2, predicate);
	}

	/** '$' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied). */
	public static @Nonnull <K, T> LFunction<K, String> uniHas$(@Nonnull LFunction<K, T> extractor, @Nonnull LBiFunction<? super T, ? super T, String> specialPredicate, T a2) {
		return uniHas$(extractor, a2, specialPredicate);
	}

	public static @Nonnull <K, T> LPredicate<K> uniHave(@Nonnull LFunction<K, T> extractor, T a2, T a3, @Nonnull LTriPredicate<? super T, ? super T, ? super T> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.apply(k), a2, a3);
	}

	/** '$' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied). */
	public static @Nonnull <K, T> LFunction<K, String> uniHave$(@Nonnull LFunction<K, T> extractor, T a2, T a3, @Nonnull LTriFunction<? super T, ? super T, ? super T, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(extractor.apply(k), a2, a3);
	}

	public static @Nonnull <K, T> LPredicate<K> uniHave(@Nonnull LFunction<K, T> extractor, @Nonnull LTriPredicate<? super T, ? super T, ? super T> predicate, T a2, T a3) {
		return uniHave(extractor, a2, a3, predicate);
	}

	/** '$' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied). */
	public static @Nonnull <K, T> LFunction<K, String> uniHave$(@Nonnull LFunction<K, T> extractor, @Nonnull LTriFunction<? super T, ? super T, ? super T, String> specialPredicate, T a2, T a3) {
		return uniHave$(extractor, a2, a3, specialPredicate);
	}

	public static @Nonnull <K, T> LPredicate<K> uniHas(@Nonnull LFunction<K, T> extractor, T a2, T a3, @Nonnull LTriPredicate<? super T, ? super T, ? super T> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.apply(k), a2, a3);
	}

	/** '$' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied). */
	public static @Nonnull <K, T> LFunction<K, String> uniHas$(@Nonnull LFunction<K, T> extractor, T a2, T a3, @Nonnull LTriFunction<? super T, ? super T, ? super T, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(extractor.apply(k), a2, a3);
	}

	public static @Nonnull <K, T> LPredicate<K> uniHas(@Nonnull LFunction<K, T> extractor, @Nonnull LTriPredicate<? super T, ? super T, ? super T> predicate, T a2, T a3) {
		return uniHas(extractor, a2, a3, predicate);
	}

	/** '$' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied). */
	public static @Nonnull <K, T> LFunction<K, String> uniHas$(@Nonnull LFunction<K, T> extractor, @Nonnull LTriFunction<? super T, ? super T, ? super T, String> specialPredicate, T a2, T a3) {
		return uniHas$(extractor, a2, a3, specialPredicate);
	}

	public static @Nonnull <K, T> LPredicate<K> uniHave(@Nonnull LFunction<K, T> extractor, T a2, T a3, T a4, @Nonnull LQuadPredicate<? super T, ? super T, ? super T, ? super T> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.apply(k), a2, a3, a4);
	}

	/** '$' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied). */
	public static @Nonnull <K, T> LFunction<K, String> uniHave$(@Nonnull LFunction<K, T> extractor, T a2, T a3, T a4, @Nonnull LQuadFunction<? super T, ? super T, ? super T, ? super T, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(extractor.apply(k), a2, a3, a4);
	}

	public static @Nonnull <K, T> LPredicate<K> uniHave(@Nonnull LFunction<K, T> extractor, @Nonnull LQuadPredicate<? super T, ? super T, ? super T, ? super T> predicate, T a2, T a3, T a4) {
		return uniHave(extractor, a2, a3, a4, predicate);
	}

	/** '$' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied). */
	public static @Nonnull <K, T> LFunction<K, String> uniHave$(@Nonnull LFunction<K, T> extractor, @Nonnull LQuadFunction<? super T, ? super T, ? super T, ? super T, String> specialPredicate, T a2, T a3, T a4) {
		return uniHave$(extractor, a2, a3, a4, specialPredicate);
	}

	public static @Nonnull <K, T> LPredicate<K> uniHas(@Nonnull LFunction<K, T> extractor, T a2, T a3, T a4, @Nonnull LQuadPredicate<? super T, ? super T, ? super T, ? super T> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.apply(k), a2, a3, a4);
	}

	/** '$' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied). */
	public static @Nonnull <K, T> LFunction<K, String> uniHas$(@Nonnull LFunction<K, T> extractor, T a2, T a3, T a4, @Nonnull LQuadFunction<? super T, ? super T, ? super T, ? super T, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(extractor.apply(k), a2, a3, a4);
	}

	public static @Nonnull <K, T> LPredicate<K> uniHas(@Nonnull LFunction<K, T> extractor, @Nonnull LQuadPredicate<? super T, ? super T, ? super T, ? super T> predicate, T a2, T a3, T a4) {
		return uniHas(extractor, a2, a3, a4, predicate);
	}

	/** '$' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied). */
	public static @Nonnull <K, T> LFunction<K, String> uniHas$(@Nonnull LFunction<K, T> extractor, @Nonnull LQuadFunction<? super T, ? super T, ? super T, ? super T, String> specialPredicate, T a2, T a3, T a4) {
		return uniHas$(extractor, a2, a3, a4, specialPredicate);
	}

	public static @Nonnull <K, T, V> LPredicate<K> haveA(@Nonnull LFunction<K, T> extractor, V[] a2, @Nonnull LBiPredicate<T, V[]> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.apply(k), a2);
	}

	/** '$' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied). */
	public static @Nonnull <K, T, V> LFunction<K, String> haveA$(@Nonnull LFunction<K, T> extractor, V[] a2, @Nonnull LBiFunction<T, V[], String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(extractor.apply(k), a2);
	}

	public static @Nonnull <K, T, V> LPredicate<K> haveA(@Nonnull LFunction<K, T> extractor, @Nonnull LBiPredicate<T, V[]> predicate, V... a2) {
		return haveA(extractor, a2, predicate);
	}

	/** '$' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied). */
	public static @Nonnull <K, T, V> LFunction<K, String> haveA$(@Nonnull LFunction<K, T> extractor, @Nonnull LBiFunction<T, V[], String> specialPredicate, V... a2) {
		return haveA$(extractor, a2, specialPredicate);
	}

	public static @Nonnull <K, T, V> LPredicate<K> hasA(@Nonnull LFunction<K, T> extractor, V[] a2, @Nonnull LBiPredicate<T, V[]> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.apply(k), a2);
	}

	/** '$' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied). */
	public static @Nonnull <K, T, V> LFunction<K, String> hasA$(@Nonnull LFunction<K, T> extractor, V[] a2, @Nonnull LBiFunction<T, V[], String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(extractor.apply(k), a2);
	}

	public static @Nonnull <K, T, V> LPredicate<K> hasA(@Nonnull LFunction<K, T> extractor, @Nonnull LBiPredicate<T, V[]> predicate, V... a2) {
		return hasA(extractor, a2, predicate);
	}

	/** '$' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied). */
	public static @Nonnull <K, T, V> LFunction<K, String> hasA$(@Nonnull LFunction<K, T> extractor, @Nonnull LBiFunction<T, V[], String> specialPredicate, V... a2) {
		return hasA$(extractor, a2, specialPredicate);
	}

	public static @Nonnull <K, T> LPredicate<K> haveBool(@Nonnull LFunction<K, T> extractor, boolean v, @Nonnull LObjBoolPredicate<? super T> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.apply(k), v);
	}

	/** '$' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied). */
	public static @Nonnull <K, T> LFunction<K, String> haveBool$(@Nonnull LFunction<K, T> extractor, boolean v, @Nonnull LObjBoolFunction<? super T, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(extractor.apply(k), v);
	}

	public static @Nonnull <K, T> LPredicate<K> haveBool(@Nonnull LFunction<K, T> extractor, @Nonnull LObjBoolPredicate<? super T> predicate, boolean v) {
		return haveBool(extractor, v, predicate);
	}

	/** '$' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied). */
	public static @Nonnull <K, T> LFunction<K, String> haveBool$(@Nonnull LFunction<K, T> extractor, @Nonnull LObjBoolFunction<? super T, String> specialPredicate, boolean v) {
		return haveBool$(extractor, v, specialPredicate);
	}

	public static @Nonnull <K, T> LPredicate<K> hasBool(@Nonnull LFunction<K, T> extractor, boolean v, @Nonnull LObjBoolPredicate<? super T> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.apply(k), v);
	}

	/** '$' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied). */
	public static @Nonnull <K, T> LFunction<K, String> hasBool$(@Nonnull LFunction<K, T> extractor, boolean v, @Nonnull LObjBoolFunction<? super T, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(extractor.apply(k), v);
	}

	public static @Nonnull <K, T> LPredicate<K> hasBool(@Nonnull LFunction<K, T> extractor, @Nonnull LObjBoolPredicate<? super T> predicate, boolean v) {
		return hasBool(extractor, v, predicate);
	}

	/** '$' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied). */
	public static @Nonnull <K, T> LFunction<K, String> hasBool$(@Nonnull LFunction<K, T> extractor, @Nonnull LObjBoolFunction<? super T, String> specialPredicate, boolean v) {
		return hasBool$(extractor, v, specialPredicate);
	}

	public static @Nonnull <K, T> LPredicate<K> haveByte(@Nonnull LFunction<K, T> extractor, byte v, @Nonnull LObjBytePredicate<? super T> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.apply(k), v);
	}

	/** '$' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied). */
	public static @Nonnull <K, T> LFunction<K, String> haveByte$(@Nonnull LFunction<K, T> extractor, byte v, @Nonnull LObjByteFunction<? super T, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(extractor.apply(k), v);
	}

	public static @Nonnull <K, T> LPredicate<K> haveByte(@Nonnull LFunction<K, T> extractor, @Nonnull LObjBytePredicate<? super T> predicate, byte v) {
		return haveByte(extractor, v, predicate);
	}

	/** '$' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied). */
	public static @Nonnull <K, T> LFunction<K, String> haveByte$(@Nonnull LFunction<K, T> extractor, @Nonnull LObjByteFunction<? super T, String> specialPredicate, byte v) {
		return haveByte$(extractor, v, specialPredicate);
	}

	public static @Nonnull <K, T> LPredicate<K> hasByte(@Nonnull LFunction<K, T> extractor, byte v, @Nonnull LObjBytePredicate<? super T> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.apply(k), v);
	}

	/** '$' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied). */
	public static @Nonnull <K, T> LFunction<K, String> hasByte$(@Nonnull LFunction<K, T> extractor, byte v, @Nonnull LObjByteFunction<? super T, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(extractor.apply(k), v);
	}

	public static @Nonnull <K, T> LPredicate<K> hasByte(@Nonnull LFunction<K, T> extractor, @Nonnull LObjBytePredicate<? super T> predicate, byte v) {
		return hasByte(extractor, v, predicate);
	}

	/** '$' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied). */
	public static @Nonnull <K, T> LFunction<K, String> hasByte$(@Nonnull LFunction<K, T> extractor, @Nonnull LObjByteFunction<? super T, String> specialPredicate, byte v) {
		return hasByte$(extractor, v, specialPredicate);
	}

	public static @Nonnull <K, T> LPredicate<K> haveDbl(@Nonnull LFunction<K, T> extractor, double v, @Nonnull LObjDblPredicate<? super T> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.apply(k), v);
	}

	/** '$' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied). */
	public static @Nonnull <K, T> LFunction<K, String> haveDbl$(@Nonnull LFunction<K, T> extractor, double v, @Nonnull LObjDblFunction<? super T, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(extractor.apply(k), v);
	}

	public static @Nonnull <K, T> LPredicate<K> haveDbl(@Nonnull LFunction<K, T> extractor, @Nonnull LObjDblPredicate<? super T> predicate, double v) {
		return haveDbl(extractor, v, predicate);
	}

	/** '$' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied). */
	public static @Nonnull <K, T> LFunction<K, String> haveDbl$(@Nonnull LFunction<K, T> extractor, @Nonnull LObjDblFunction<? super T, String> specialPredicate, double v) {
		return haveDbl$(extractor, v, specialPredicate);
	}

	public static @Nonnull <K, T> LPredicate<K> hasDbl(@Nonnull LFunction<K, T> extractor, double v, @Nonnull LObjDblPredicate<? super T> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.apply(k), v);
	}

	/** '$' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied). */
	public static @Nonnull <K, T> LFunction<K, String> hasDbl$(@Nonnull LFunction<K, T> extractor, double v, @Nonnull LObjDblFunction<? super T, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(extractor.apply(k), v);
	}

	public static @Nonnull <K, T> LPredicate<K> hasDbl(@Nonnull LFunction<K, T> extractor, @Nonnull LObjDblPredicate<? super T> predicate, double v) {
		return hasDbl(extractor, v, predicate);
	}

	/** '$' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied). */
	public static @Nonnull <K, T> LFunction<K, String> hasDbl$(@Nonnull LFunction<K, T> extractor, @Nonnull LObjDblFunction<? super T, String> specialPredicate, double v) {
		return hasDbl$(extractor, v, specialPredicate);
	}

	public static @Nonnull <K, T> LPredicate<K> haveChar(@Nonnull LFunction<K, T> extractor, char v, @Nonnull LObjCharPredicate<? super T> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.apply(k), v);
	}

	/** '$' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied). */
	public static @Nonnull <K, T> LFunction<K, String> haveChar$(@Nonnull LFunction<K, T> extractor, char v, @Nonnull LObjCharFunction<? super T, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(extractor.apply(k), v);
	}

	public static @Nonnull <K, T> LPredicate<K> haveChar(@Nonnull LFunction<K, T> extractor, @Nonnull LObjCharPredicate<? super T> predicate, char v) {
		return haveChar(extractor, v, predicate);
	}

	/** '$' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied). */
	public static @Nonnull <K, T> LFunction<K, String> haveChar$(@Nonnull LFunction<K, T> extractor, @Nonnull LObjCharFunction<? super T, String> specialPredicate, char v) {
		return haveChar$(extractor, v, specialPredicate);
	}

	public static @Nonnull <K, T> LPredicate<K> hasChar(@Nonnull LFunction<K, T> extractor, char v, @Nonnull LObjCharPredicate<? super T> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.apply(k), v);
	}

	/** '$' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied). */
	public static @Nonnull <K, T> LFunction<K, String> hasChar$(@Nonnull LFunction<K, T> extractor, char v, @Nonnull LObjCharFunction<? super T, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(extractor.apply(k), v);
	}

	public static @Nonnull <K, T> LPredicate<K> hasChar(@Nonnull LFunction<K, T> extractor, @Nonnull LObjCharPredicate<? super T> predicate, char v) {
		return hasChar(extractor, v, predicate);
	}

	/** '$' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied). */
	public static @Nonnull <K, T> LFunction<K, String> hasChar$(@Nonnull LFunction<K, T> extractor, @Nonnull LObjCharFunction<? super T, String> specialPredicate, char v) {
		return hasChar$(extractor, v, specialPredicate);
	}

	public static @Nonnull <K, T> LPredicate<K> haveSrt(@Nonnull LFunction<K, T> extractor, short v, @Nonnull LObjSrtPredicate<? super T> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.apply(k), v);
	}

	/** '$' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied). */
	public static @Nonnull <K, T> LFunction<K, String> haveSrt$(@Nonnull LFunction<K, T> extractor, short v, @Nonnull LObjSrtFunction<? super T, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(extractor.apply(k), v);
	}

	public static @Nonnull <K, T> LPredicate<K> haveSrt(@Nonnull LFunction<K, T> extractor, @Nonnull LObjSrtPredicate<? super T> predicate, short v) {
		return haveSrt(extractor, v, predicate);
	}

	/** '$' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied). */
	public static @Nonnull <K, T> LFunction<K, String> haveSrt$(@Nonnull LFunction<K, T> extractor, @Nonnull LObjSrtFunction<? super T, String> specialPredicate, short v) {
		return haveSrt$(extractor, v, specialPredicate);
	}

	public static @Nonnull <K, T> LPredicate<K> hasSrt(@Nonnull LFunction<K, T> extractor, short v, @Nonnull LObjSrtPredicate<? super T> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.apply(k), v);
	}

	/** '$' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied). */
	public static @Nonnull <K, T> LFunction<K, String> hasSrt$(@Nonnull LFunction<K, T> extractor, short v, @Nonnull LObjSrtFunction<? super T, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(extractor.apply(k), v);
	}

	public static @Nonnull <K, T> LPredicate<K> hasSrt(@Nonnull LFunction<K, T> extractor, @Nonnull LObjSrtPredicate<? super T> predicate, short v) {
		return hasSrt(extractor, v, predicate);
	}

	/** '$' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied). */
	public static @Nonnull <K, T> LFunction<K, String> hasSrt$(@Nonnull LFunction<K, T> extractor, @Nonnull LObjSrtFunction<? super T, String> specialPredicate, short v) {
		return hasSrt$(extractor, v, specialPredicate);
	}

	public static @Nonnull <K, T> LPredicate<K> haveFlt(@Nonnull LFunction<K, T> extractor, float v, @Nonnull LObjFltPredicate<? super T> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.apply(k), v);
	}

	/** '$' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied). */
	public static @Nonnull <K, T> LFunction<K, String> haveFlt$(@Nonnull LFunction<K, T> extractor, float v, @Nonnull LObjFltFunction<? super T, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(extractor.apply(k), v);
	}

	public static @Nonnull <K, T> LPredicate<K> haveFlt(@Nonnull LFunction<K, T> extractor, @Nonnull LObjFltPredicate<? super T> predicate, float v) {
		return haveFlt(extractor, v, predicate);
	}

	/** '$' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied). */
	public static @Nonnull <K, T> LFunction<K, String> haveFlt$(@Nonnull LFunction<K, T> extractor, @Nonnull LObjFltFunction<? super T, String> specialPredicate, float v) {
		return haveFlt$(extractor, v, specialPredicate);
	}

	public static @Nonnull <K, T> LPredicate<K> hasFlt(@Nonnull LFunction<K, T> extractor, float v, @Nonnull LObjFltPredicate<? super T> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.apply(k), v);
	}

	/** '$' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied). */
	public static @Nonnull <K, T> LFunction<K, String> hasFlt$(@Nonnull LFunction<K, T> extractor, float v, @Nonnull LObjFltFunction<? super T, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(extractor.apply(k), v);
	}

	public static @Nonnull <K, T> LPredicate<K> hasFlt(@Nonnull LFunction<K, T> extractor, @Nonnull LObjFltPredicate<? super T> predicate, float v) {
		return hasFlt(extractor, v, predicate);
	}

	/** '$' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied). */
	public static @Nonnull <K, T> LFunction<K, String> hasFlt$(@Nonnull LFunction<K, T> extractor, @Nonnull LObjFltFunction<? super T, String> specialPredicate, float v) {
		return hasFlt$(extractor, v, specialPredicate);
	}

	public static @Nonnull <K, T> LPredicate<K> haveInt(@Nonnull LFunction<K, T> extractor, int v, @Nonnull LObjIntPredicate<? super T> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.apply(k), v);
	}

	/** '$' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied). */
	public static @Nonnull <K, T> LFunction<K, String> haveInt$(@Nonnull LFunction<K, T> extractor, int v, @Nonnull LOiFunction<? super T, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(extractor.apply(k), v);
	}

	public static @Nonnull <K, T> LPredicate<K> haveInt(@Nonnull LFunction<K, T> extractor, @Nonnull LObjIntPredicate<? super T> predicate, int v) {
		return haveInt(extractor, v, predicate);
	}

	/** '$' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied). */
	public static @Nonnull <K, T> LFunction<K, String> haveInt$(@Nonnull LFunction<K, T> extractor, @Nonnull LOiFunction<? super T, String> specialPredicate, int v) {
		return haveInt$(extractor, v, specialPredicate);
	}

	public static @Nonnull <K, T> LPredicate<K> hasInt(@Nonnull LFunction<K, T> extractor, int v, @Nonnull LObjIntPredicate<? super T> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.apply(k), v);
	}

	/** '$' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied). */
	public static @Nonnull <K, T> LFunction<K, String> hasInt$(@Nonnull LFunction<K, T> extractor, int v, @Nonnull LOiFunction<? super T, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(extractor.apply(k), v);
	}

	public static @Nonnull <K, T> LPredicate<K> hasInt(@Nonnull LFunction<K, T> extractor, @Nonnull LObjIntPredicate<? super T> predicate, int v) {
		return hasInt(extractor, v, predicate);
	}

	/** '$' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied). */
	public static @Nonnull <K, T> LFunction<K, String> hasInt$(@Nonnull LFunction<K, T> extractor, @Nonnull LOiFunction<? super T, String> specialPredicate, int v) {
		return hasInt$(extractor, v, specialPredicate);
	}

	public static @Nonnull <K, T> LPredicate<K> haveLong(@Nonnull LFunction<K, T> extractor, long v, @Nonnull LObjLongPredicate<? super T> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.apply(k), v);
	}

	/** '$' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied). */
	public static @Nonnull <K, T> LFunction<K, String> haveLong$(@Nonnull LFunction<K, T> extractor, long v, @Nonnull LObjLongFunction<? super T, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(extractor.apply(k), v);
	}

	public static @Nonnull <K, T> LPredicate<K> haveLong(@Nonnull LFunction<K, T> extractor, @Nonnull LObjLongPredicate<? super T> predicate, long v) {
		return haveLong(extractor, v, predicate);
	}

	/** '$' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied). */
	public static @Nonnull <K, T> LFunction<K, String> haveLong$(@Nonnull LFunction<K, T> extractor, @Nonnull LObjLongFunction<? super T, String> specialPredicate, long v) {
		return haveLong$(extractor, v, specialPredicate);
	}

	public static @Nonnull <K, T> LPredicate<K> hasLong(@Nonnull LFunction<K, T> extractor, long v, @Nonnull LObjLongPredicate<? super T> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.apply(k), v);
	}

	/** '$' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied). */
	public static @Nonnull <K, T> LFunction<K, String> hasLong$(@Nonnull LFunction<K, T> extractor, long v, @Nonnull LObjLongFunction<? super T, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(extractor.apply(k), v);
	}

	public static @Nonnull <K, T> LPredicate<K> hasLong(@Nonnull LFunction<K, T> extractor, @Nonnull LObjLongPredicate<? super T> predicate, long v) {
		return hasLong(extractor, v, predicate);
	}

	/** '$' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied). */
	public static @Nonnull <K, T> LFunction<K, String> hasLong$(@Nonnull LFunction<K, T> extractor, @Nonnull LObjLongFunction<? super T, String> specialPredicate, long v) {
		return hasLong$(extractor, v, specialPredicate);
	}

	public static @Nonnull <K, T, V> LPredicate<K> have(@Nonnull LFunction<K, T> extractor, V v, @Nonnull LBiPredicate<? super T, ? super V> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.apply(k), v);
	}

	/** '$' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied). */
	public static @Nonnull <K, T, V> LFunction<K, String> have$(@Nonnull LFunction<K, T> extractor, V v, @Nonnull LBiFunction<? super T, ? super V, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(extractor.apply(k), v);
	}

	public static @Nonnull <K, T, V> LPredicate<K> have(@Nonnull LFunction<K, T> extractor, @Nonnull LBiPredicate<? super T, ? super V> predicate, V v) {
		return have(extractor, v, predicate);
	}

	/** '$' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied). */
	public static @Nonnull <K, T, V> LFunction<K, String> have$(@Nonnull LFunction<K, T> extractor, @Nonnull LBiFunction<? super T, ? super V, String> specialPredicate, V v) {
		return have$(extractor, v, specialPredicate);
	}

	public static @Nonnull <K, T, V> LPredicate<K> has(@Nonnull LFunction<K, T> extractor, V v, @Nonnull LBiPredicate<? super T, ? super V> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.apply(k), v);
	}

	/** '$' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied). */
	public static @Nonnull <K, T, V> LFunction<K, String> has$(@Nonnull LFunction<K, T> extractor, V v, @Nonnull LBiFunction<? super T, ? super V, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(extractor.apply(k), v);
	}

	public static @Nonnull <K, T, V> LPredicate<K> has(@Nonnull LFunction<K, T> extractor, @Nonnull LBiPredicate<? super T, ? super V> predicate, V v) {
		return has(extractor, v, predicate);
	}

	/** '$' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied). */
	public static @Nonnull <K, T, V> LFunction<K, String> has$(@Nonnull LFunction<K, T> extractor, @Nonnull LBiFunction<? super T, ? super V, String> specialPredicate, V v) {
		return has$(extractor, v, specialPredicate);
	}

	public static @Nonnull <K, T, V2, V3> LPredicate<K> have(@Nonnull LFunction<K, T> extractor, V2 a2, V3 a3, @Nonnull LTriPredicate<? super T, ? super V2, ? super V3> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.apply(k), a2, a3);
	}

	/** '$' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied). */
	public static @Nonnull <K, T, V2, V3> LFunction<K, String> have$(@Nonnull LFunction<K, T> extractor, V2 a2, V3 a3, @Nonnull LTriFunction<? super T, ? super V2, ? super V3, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(extractor.apply(k), a2, a3);
	}

	public static @Nonnull <K, T, V2, V3> LPredicate<K> have(@Nonnull LFunction<K, T> extractor, @Nonnull LTriPredicate<? super T, ? super V2, ? super V3> predicate, V2 a2, V3 a3) {
		return have(extractor, a2, a3, predicate);
	}

	/** '$' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied). */
	public static @Nonnull <K, T, V2, V3> LFunction<K, String> have$(@Nonnull LFunction<K, T> extractor, @Nonnull LTriFunction<? super T, ? super V2, ? super V3, String> specialPredicate, V2 a2, V3 a3) {
		return have$(extractor, a2, a3, specialPredicate);
	}

	public static @Nonnull <K, T, V2, V3> LPredicate<K> has(@Nonnull LFunction<K, T> extractor, V2 a2, V3 a3, @Nonnull LTriPredicate<? super T, ? super V2, ? super V3> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.apply(k), a2, a3);
	}

	/** '$' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied). */
	public static @Nonnull <K, T, V2, V3> LFunction<K, String> has$(@Nonnull LFunction<K, T> extractor, V2 a2, V3 a3, @Nonnull LTriFunction<? super T, ? super V2, ? super V3, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(extractor.apply(k), a2, a3);
	}

	public static @Nonnull <K, T, V2, V3> LPredicate<K> has(@Nonnull LFunction<K, T> extractor, @Nonnull LTriPredicate<? super T, ? super V2, ? super V3> predicate, V2 a2, V3 a3) {
		return has(extractor, a2, a3, predicate);
	}

	/** '$' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied). */
	public static @Nonnull <K, T, V2, V3> LFunction<K, String> has$(@Nonnull LFunction<K, T> extractor, @Nonnull LTriFunction<? super T, ? super V2, ? super V3, String> specialPredicate, V2 a2, V3 a3) {
		return has$(extractor, a2, a3, specialPredicate);
	}

	public static @Nonnull <K, T, V2, V3, V4> LPredicate<K> have(@Nonnull LFunction<K, T> extractor, V2 a2, V3 a3, V4 a4, @Nonnull LQuadPredicate<? super T, ? super V2, ? super V3, ? super V4> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.apply(k), a2, a3, a4);
	}

	/** '$' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied). */
	public static @Nonnull <K, T, V2, V3, V4> LFunction<K, String> have$(@Nonnull LFunction<K, T> extractor, V2 a2, V3 a3, V4 a4, @Nonnull LQuadFunction<? super T, ? super V2, ? super V3, ? super V4, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(extractor.apply(k), a2, a3, a4);
	}

	public static @Nonnull <K, T, V2, V3, V4> LPredicate<K> have(@Nonnull LFunction<K, T> extractor, @Nonnull LQuadPredicate<? super T, ? super V2, ? super V3, ? super V4> predicate, V2 a2, V3 a3, V4 a4) {
		return have(extractor, a2, a3, a4, predicate);
	}

	/** '$' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied). */
	public static @Nonnull <K, T, V2, V3, V4> LFunction<K, String> have$(@Nonnull LFunction<K, T> extractor, @Nonnull LQuadFunction<? super T, ? super V2, ? super V3, ? super V4, String> specialPredicate, V2 a2, V3 a3, V4 a4) {
		return have$(extractor, a2, a3, a4, specialPredicate);
	}

	public static @Nonnull <K, T, V2, V3, V4> LPredicate<K> has(@Nonnull LFunction<K, T> extractor, V2 a2, V3 a3, V4 a4, @Nonnull LQuadPredicate<? super T, ? super V2, ? super V3, ? super V4> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.apply(k), a2, a3, a4);
	}

	/** '$' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied). */
	public static @Nonnull <K, T, V2, V3, V4> LFunction<K, String> has$(@Nonnull LFunction<K, T> extractor, V2 a2, V3 a3, V4 a4, @Nonnull LQuadFunction<? super T, ? super V2, ? super V3, ? super V4, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(extractor.apply(k), a2, a3, a4);
	}

	public static @Nonnull <K, T, V2, V3, V4> LPredicate<K> has(@Nonnull LFunction<K, T> extractor, @Nonnull LQuadPredicate<? super T, ? super V2, ? super V3, ? super V4> predicate, V2 a2, V3 a3, V4 a4) {
		return has(extractor, a2, a3, a4, predicate);
	}

	/** '$' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied). */
	public static @Nonnull <K, T, V2, V3, V4> LFunction<K, String> has$(@Nonnull LFunction<K, T> extractor, @Nonnull LQuadFunction<? super T, ? super V2, ? super V3, ? super V4, String> specialPredicate, V2 a2, V3 a3, V4 a4) {
		return has$(extractor, a2, a3, a4, specialPredicate);
	}

	/** 'With' - call of main predicate will be prefixed (contrary to affixed) with additional argument(s). */
	public static @Nonnull <K, T, V1> LPredicate<K> haveWith(@Nonnull LFunction<K, T> extractor, V1 with1, @Nonnull LBiPredicate<? super V1, ? super T> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(with1, extractor.apply(k));
	}

	/**
	* 'With' - call of main predicate will be prefixed (contrary to affixed) with additional argument(s).
	* '$' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K, T, V1> LFunction<K, String> haveWith$(@Nonnull LFunction<K, T> extractor, V1 with1, @Nonnull LBiFunction<? super V1, ? super T, String> specialPredicate) {
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
	* '$' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K, T, V1> LFunction<K, String> haveWith$(@Nonnull LFunction<K, T> extractor, @Nonnull LBiFunction<? super V1, ? super T, String> specialPredicate, V1 with1) {
		return haveWith$(extractor, with1, specialPredicate);
	}

	/** 'With' - call of main predicate will be prefixed (contrary to affixed) with additional argument(s). */
	public static @Nonnull <K, T, V1> LPredicate<K> hasWith(@Nonnull LFunction<K, T> extractor, V1 with1, @Nonnull LBiPredicate<? super V1, ? super T> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(with1, extractor.apply(k));
	}

	/**
	* 'With' - call of main predicate will be prefixed (contrary to affixed) with additional argument(s).
	* '$' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K, T, V1> LFunction<K, String> hasWith$(@Nonnull LFunction<K, T> extractor, V1 with1, @Nonnull LBiFunction<? super V1, ? super T, String> specialPredicate) {
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
	* '$' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K, T, V1> LFunction<K, String> hasWith$(@Nonnull LFunction<K, T> extractor, @Nonnull LBiFunction<? super V1, ? super T, String> specialPredicate, V1 with1) {
		return hasWith$(extractor, with1, specialPredicate);
	}

	/** 'With' - call of main predicate will be prefixed (contrary to affixed) with additional argument(s). */
	public static @Nonnull <K, T, V1, V2> LPredicate<K> haveWith(@Nonnull LFunction<K, T> extractor, V1 with1, V2 with2, @Nonnull LTriPredicate<? super V1, ? super V2, ? super T> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(with1, with2, extractor.apply(k));
	}

	/**
	* 'With' - call of main predicate will be prefixed (contrary to affixed) with additional argument(s).
	* '$' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K, T, V1, V2> LFunction<K, String> haveWith$(@Nonnull LFunction<K, T> extractor, V1 with1, V2 with2, @Nonnull LTriFunction<? super V1, ? super V2, ? super T, String> specialPredicate) {
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
	* '$' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K, T, V1, V2> LFunction<K, String> haveWith$(@Nonnull LFunction<K, T> extractor, @Nonnull LTriFunction<? super V1, ? super V2, ? super T, String> specialPredicate, V1 with1, V2 with2) {
		return haveWith$(extractor, with1, with2, specialPredicate);
	}

	/** 'With' - call of main predicate will be prefixed (contrary to affixed) with additional argument(s). */
	public static @Nonnull <K, T, V1, V2> LPredicate<K> hasWith(@Nonnull LFunction<K, T> extractor, V1 with1, V2 with2, @Nonnull LTriPredicate<? super V1, ? super V2, ? super T> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(with1, with2, extractor.apply(k));
	}

	/**
	* 'With' - call of main predicate will be prefixed (contrary to affixed) with additional argument(s).
	* '$' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K, T, V1, V2> LFunction<K, String> hasWith$(@Nonnull LFunction<K, T> extractor, V1 with1, V2 with2, @Nonnull LTriFunction<? super V1, ? super V2, ? super T, String> specialPredicate) {
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
	* '$' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K, T, V1, V2> LFunction<K, String> hasWith$(@Nonnull LFunction<K, T> extractor, @Nonnull LTriFunction<? super V1, ? super V2, ? super T, String> specialPredicate, V1 with1, V2 with2) {
		return hasWith$(extractor, with1, with2, specialPredicate);
	}

	/** 'With' - call of main predicate will be prefixed (contrary to affixed) with additional argument(s). */
	public static @Nonnull <K, T> LPredicate<K> uniHaveWith(@Nonnull LFunction<K, T> extractor, T with, @Nonnull LBiPredicate<? super T, ? super T> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(with, extractor.apply(k));
	}

	/**
	* 'With' - call of main predicate will be prefixed (contrary to affixed) with additional argument(s).
	* '$' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K, T> LFunction<K, String> uniHaveWith$(@Nonnull LFunction<K, T> extractor, T with, @Nonnull LBiFunction<? super T, ? super T, String> specialPredicate) {
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
	* '$' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K, T> LFunction<K, String> uniHaveWith$(@Nonnull LFunction<K, T> extractor, @Nonnull LBiFunction<? super T, ? super T, String> specialPredicate, T with) {
		return uniHaveWith$(extractor, with, specialPredicate);
	}

	/** 'With' - call of main predicate will be prefixed (contrary to affixed) with additional argument(s). */
	public static @Nonnull <K, T> LPredicate<K> uniHasWith(@Nonnull LFunction<K, T> extractor, T with, @Nonnull LBiPredicate<? super T, ? super T> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(with, extractor.apply(k));
	}

	/**
	* 'With' - call of main predicate will be prefixed (contrary to affixed) with additional argument(s).
	* '$' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K, T> LFunction<K, String> uniHasWith$(@Nonnull LFunction<K, T> extractor, T with, @Nonnull LBiFunction<? super T, ? super T, String> specialPredicate) {
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
	* '$' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K, T> LFunction<K, String> uniHasWith$(@Nonnull LFunction<K, T> extractor, @Nonnull LBiFunction<? super T, ? super T, String> specialPredicate, T with) {
		return uniHasWith$(extractor, with, specialPredicate);
	}

	/** 'ToByte' - first, actual value will be converted to primitive type (contrary to the object). */
	public static @Nonnull <K> LPredicate<K> haveToByte(@Nonnull LToByteFunction<K> extractor, @Nonnull LBytePredicate predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.applyAsByte(k));
	}

	/**
	* 'ToByte' - first, actual value will be converted to primitive type (contrary to the object).
	* '$' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K> LFunction<K, String> haveToByte$(@Nonnull LToByteFunction<K> extractor, @Nonnull LByteFunction<String> specialPredicate) {
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
	* '$' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K> LFunction<K, String> hasToByte$(@Nonnull LToByteFunction<K> extractor, @Nonnull LByteFunction<String> specialPredicate) {
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
	* '$' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K> LFunction<K, String> haveToByte$(@Nonnull LToByteFunction<K> extractor, byte a2, @Nonnull LBiByteFunction<String> specialPredicate) {
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
	* '$' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K> LFunction<K, String> haveToByte$(@Nonnull LToByteFunction<K> extractor, @Nonnull LBiByteFunction<String> specialPredicate, byte a2) {
		return haveToByte$(extractor, a2, specialPredicate);
	}

	/** 'ToByte' - first, actual value will be converted to primitive type (contrary to the object). */
	public static @Nonnull <K> LPredicate<K> hasToByte(@Nonnull LToByteFunction<K> extractor, byte a2, @Nonnull LBiBytePredicate predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.applyAsByte(k), a2);
	}

	/**
	* 'ToByte' - first, actual value will be converted to primitive type (contrary to the object).
	* '$' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K> LFunction<K, String> hasToByte$(@Nonnull LToByteFunction<K> extractor, byte a2, @Nonnull LBiByteFunction<String> specialPredicate) {
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
	* '$' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K> LFunction<K, String> hasToByte$(@Nonnull LToByteFunction<K> extractor, @Nonnull LBiByteFunction<String> specialPredicate, byte a2) {
		return hasToByte$(extractor, a2, specialPredicate);
	}

	/** 'ToByte' - first, actual value will be converted to primitive type (contrary to the object). */
	public static @Nonnull <K> LPredicate<K> haveToByte(@Nonnull LToByteFunction<K> extractor, byte a2, byte a3, @Nonnull LTriBytePredicate predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.applyAsByte(k), a2, a3);
	}

	/**
	* 'ToByte' - first, actual value will be converted to primitive type (contrary to the object).
	* '$' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K> LFunction<K, String> haveToByte$(@Nonnull LToByteFunction<K> extractor, byte a2, byte a3, @Nonnull LTriByteFunction<String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(extractor.applyAsByte(k), a2, a3);
	}

	/** 'ToByte' - first, actual value will be converted to primitive type (contrary to the object). */
	public static @Nonnull <K> LPredicate<K> haveToByte(@Nonnull LToByteFunction<K> extractor, @Nonnull LTriBytePredicate predicate, byte a2, byte a3) {
		return haveToByte(extractor, a2, a3, predicate);
	}

	/**
	* 'ToByte' - first, actual value will be converted to primitive type (contrary to the object).
	* '$' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K> LFunction<K, String> haveToByte$(@Nonnull LToByteFunction<K> extractor, @Nonnull LTriByteFunction<String> specialPredicate, byte a2, byte a3) {
		return haveToByte$(extractor, a2, a3, specialPredicate);
	}

	/** 'ToByte' - first, actual value will be converted to primitive type (contrary to the object). */
	public static @Nonnull <K> LPredicate<K> hasToByte(@Nonnull LToByteFunction<K> extractor, byte a2, byte a3, @Nonnull LTriBytePredicate predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.applyAsByte(k), a2, a3);
	}

	/**
	* 'ToByte' - first, actual value will be converted to primitive type (contrary to the object).
	* '$' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K> LFunction<K, String> hasToByte$(@Nonnull LToByteFunction<K> extractor, byte a2, byte a3, @Nonnull LTriByteFunction<String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(extractor.applyAsByte(k), a2, a3);
	}

	/** 'ToByte' - first, actual value will be converted to primitive type (contrary to the object). */
	public static @Nonnull <K> LPredicate<K> hasToByte(@Nonnull LToByteFunction<K> extractor, @Nonnull LTriBytePredicate predicate, byte a2, byte a3) {
		return hasToByte(extractor, a2, a3, predicate);
	}

	/**
	* 'ToByte' - first, actual value will be converted to primitive type (contrary to the object).
	* '$' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K> LFunction<K, String> hasToByte$(@Nonnull LToByteFunction<K> extractor, @Nonnull LTriByteFunction<String> specialPredicate, byte a2, byte a3) {
		return hasToByte$(extractor, a2, a3, specialPredicate);
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
	* '$' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K, V1> LFunction<K, String> haveToByteWith$(@Nonnull LToByteFunction<K> extractor, V1 with1, @Nonnull LObjByteFunction<? super V1, String> specialPredicate) {
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
	* '$' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K, V1> LFunction<K, String> haveToByteWith$(@Nonnull LToByteFunction<K> extractor, @Nonnull LObjByteFunction<? super V1, String> specialPredicate, V1 with1) {
		return haveToByteWith$(extractor, with1, specialPredicate);
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
	* '$' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K, V1> LFunction<K, String> hasToByteWith$(@Nonnull LToByteFunction<K> extractor, V1 with1, @Nonnull LObjByteFunction<? super V1, String> specialPredicate) {
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
	* '$' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K, V1> LFunction<K, String> hasToByteWith$(@Nonnull LToByteFunction<K> extractor, @Nonnull LObjByteFunction<? super V1, String> specialPredicate, V1 with1) {
		return hasToByteWith$(extractor, with1, specialPredicate);
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
	* '$' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K, V1, V2> LFunction<K, String> haveToByteWith$(@Nonnull LToByteFunction<K> extractor, V1 with1, V2 with2, @Nonnull LBiObjByteFunction<? super V1, ? super V2, String> specialPredicate) {
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
	* '$' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K, V1, V2> LFunction<K, String> haveToByteWith$(@Nonnull LToByteFunction<K> extractor, @Nonnull LBiObjByteFunction<? super V1, ? super V2, String> specialPredicate, V1 with1, V2 with2) {
		return haveToByteWith$(extractor, with1, with2, specialPredicate);
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
	* '$' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K, V1, V2> LFunction<K, String> hasToByteWith$(@Nonnull LToByteFunction<K> extractor, V1 with1, V2 with2, @Nonnull LBiObjByteFunction<? super V1, ? super V2, String> specialPredicate) {
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
	* '$' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K, V1, V2> LFunction<K, String> hasToByteWith$(@Nonnull LToByteFunction<K> extractor, @Nonnull LBiObjByteFunction<? super V1, ? super V2, String> specialPredicate, V1 with1, V2 with2) {
		return hasToByteWith$(extractor, with1, with2, specialPredicate);
	}

	/** 'ToDbl' - first, actual value will be converted to primitive type (contrary to the object). */
	public static @Nonnull <K> LPredicate<K> haveToDbl(@Nonnull LToDblFunction<K> extractor, @Nonnull LDblPredicate predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.applyAsDbl(k));
	}

	/**
	* 'ToDbl' - first, actual value will be converted to primitive type (contrary to the object).
	* '$' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K> LFunction<K, String> haveToDbl$(@Nonnull LToDblFunction<K> extractor, @Nonnull LDblFunction<String> specialPredicate) {
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
	* '$' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K> LFunction<K, String> hasToDbl$(@Nonnull LToDblFunction<K> extractor, @Nonnull LDblFunction<String> specialPredicate) {
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
	* '$' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K> LFunction<K, String> haveToDbl$(@Nonnull LToDblFunction<K> extractor, double a2, @Nonnull LBiDblFunction<String> specialPredicate) {
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
	* '$' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K> LFunction<K, String> haveToDbl$(@Nonnull LToDblFunction<K> extractor, @Nonnull LBiDblFunction<String> specialPredicate, double a2) {
		return haveToDbl$(extractor, a2, specialPredicate);
	}

	/** 'ToDbl' - first, actual value will be converted to primitive type (contrary to the object). */
	public static @Nonnull <K> LPredicate<K> hasToDbl(@Nonnull LToDblFunction<K> extractor, double a2, @Nonnull LBiDblPredicate predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.applyAsDbl(k), a2);
	}

	/**
	* 'ToDbl' - first, actual value will be converted to primitive type (contrary to the object).
	* '$' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K> LFunction<K, String> hasToDbl$(@Nonnull LToDblFunction<K> extractor, double a2, @Nonnull LBiDblFunction<String> specialPredicate) {
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
	* '$' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K> LFunction<K, String> hasToDbl$(@Nonnull LToDblFunction<K> extractor, @Nonnull LBiDblFunction<String> specialPredicate, double a2) {
		return hasToDbl$(extractor, a2, specialPredicate);
	}

	/** 'ToDbl' - first, actual value will be converted to primitive type (contrary to the object). */
	public static @Nonnull <K> LPredicate<K> haveToDbl(@Nonnull LToDblFunction<K> extractor, double a2, double a3, @Nonnull LTriDblPredicate predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.applyAsDbl(k), a2, a3);
	}

	/**
	* 'ToDbl' - first, actual value will be converted to primitive type (contrary to the object).
	* '$' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K> LFunction<K, String> haveToDbl$(@Nonnull LToDblFunction<K> extractor, double a2, double a3, @Nonnull LTriDblFunction<String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(extractor.applyAsDbl(k), a2, a3);
	}

	/** 'ToDbl' - first, actual value will be converted to primitive type (contrary to the object). */
	public static @Nonnull <K> LPredicate<K> haveToDbl(@Nonnull LToDblFunction<K> extractor, @Nonnull LTriDblPredicate predicate, double a2, double a3) {
		return haveToDbl(extractor, a2, a3, predicate);
	}

	/**
	* 'ToDbl' - first, actual value will be converted to primitive type (contrary to the object).
	* '$' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K> LFunction<K, String> haveToDbl$(@Nonnull LToDblFunction<K> extractor, @Nonnull LTriDblFunction<String> specialPredicate, double a2, double a3) {
		return haveToDbl$(extractor, a2, a3, specialPredicate);
	}

	/** 'ToDbl' - first, actual value will be converted to primitive type (contrary to the object). */
	public static @Nonnull <K> LPredicate<K> hasToDbl(@Nonnull LToDblFunction<K> extractor, double a2, double a3, @Nonnull LTriDblPredicate predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.applyAsDbl(k), a2, a3);
	}

	/**
	* 'ToDbl' - first, actual value will be converted to primitive type (contrary to the object).
	* '$' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K> LFunction<K, String> hasToDbl$(@Nonnull LToDblFunction<K> extractor, double a2, double a3, @Nonnull LTriDblFunction<String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(extractor.applyAsDbl(k), a2, a3);
	}

	/** 'ToDbl' - first, actual value will be converted to primitive type (contrary to the object). */
	public static @Nonnull <K> LPredicate<K> hasToDbl(@Nonnull LToDblFunction<K> extractor, @Nonnull LTriDblPredicate predicate, double a2, double a3) {
		return hasToDbl(extractor, a2, a3, predicate);
	}

	/**
	* 'ToDbl' - first, actual value will be converted to primitive type (contrary to the object).
	* '$' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K> LFunction<K, String> hasToDbl$(@Nonnull LToDblFunction<K> extractor, @Nonnull LTriDblFunction<String> specialPredicate, double a2, double a3) {
		return hasToDbl$(extractor, a2, a3, specialPredicate);
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
	* '$' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K, V1> LFunction<K, String> haveToDblWith$(@Nonnull LToDblFunction<K> extractor, V1 with1, @Nonnull LObjDblFunction<? super V1, String> specialPredicate) {
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
	* '$' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K, V1> LFunction<K, String> haveToDblWith$(@Nonnull LToDblFunction<K> extractor, @Nonnull LObjDblFunction<? super V1, String> specialPredicate, V1 with1) {
		return haveToDblWith$(extractor, with1, specialPredicate);
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
	* '$' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K, V1> LFunction<K, String> hasToDblWith$(@Nonnull LToDblFunction<K> extractor, V1 with1, @Nonnull LObjDblFunction<? super V1, String> specialPredicate) {
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
	* '$' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K, V1> LFunction<K, String> hasToDblWith$(@Nonnull LToDblFunction<K> extractor, @Nonnull LObjDblFunction<? super V1, String> specialPredicate, V1 with1) {
		return hasToDblWith$(extractor, with1, specialPredicate);
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
	* '$' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K, V1, V2> LFunction<K, String> haveToDblWith$(@Nonnull LToDblFunction<K> extractor, V1 with1, V2 with2, @Nonnull LBiObjDblFunction<? super V1, ? super V2, String> specialPredicate) {
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
	* '$' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K, V1, V2> LFunction<K, String> haveToDblWith$(@Nonnull LToDblFunction<K> extractor, @Nonnull LBiObjDblFunction<? super V1, ? super V2, String> specialPredicate, V1 with1, V2 with2) {
		return haveToDblWith$(extractor, with1, with2, specialPredicate);
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
	* '$' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K, V1, V2> LFunction<K, String> hasToDblWith$(@Nonnull LToDblFunction<K> extractor, V1 with1, V2 with2, @Nonnull LBiObjDblFunction<? super V1, ? super V2, String> specialPredicate) {
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
	* '$' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K, V1, V2> LFunction<K, String> hasToDblWith$(@Nonnull LToDblFunction<K> extractor, @Nonnull LBiObjDblFunction<? super V1, ? super V2, String> specialPredicate, V1 with1, V2 with2) {
		return hasToDblWith$(extractor, with1, with2, specialPredicate);
	}

	/** 'ToChar' - first, actual value will be converted to primitive type (contrary to the object). */
	public static @Nonnull <K> LPredicate<K> haveToChar(@Nonnull LToCharFunction<K> extractor, @Nonnull LCharPredicate predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.applyAsChar(k));
	}

	/**
	* 'ToChar' - first, actual value will be converted to primitive type (contrary to the object).
	* '$' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K> LFunction<K, String> haveToChar$(@Nonnull LToCharFunction<K> extractor, @Nonnull LCharFunction<String> specialPredicate) {
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
	* '$' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K> LFunction<K, String> hasToChar$(@Nonnull LToCharFunction<K> extractor, @Nonnull LCharFunction<String> specialPredicate) {
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
	* '$' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K> LFunction<K, String> haveToChar$(@Nonnull LToCharFunction<K> extractor, char a2, @Nonnull LBiCharFunction<String> specialPredicate) {
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
	* '$' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K> LFunction<K, String> haveToChar$(@Nonnull LToCharFunction<K> extractor, @Nonnull LBiCharFunction<String> specialPredicate, char a2) {
		return haveToChar$(extractor, a2, specialPredicate);
	}

	/** 'ToChar' - first, actual value will be converted to primitive type (contrary to the object). */
	public static @Nonnull <K> LPredicate<K> hasToChar(@Nonnull LToCharFunction<K> extractor, char a2, @Nonnull LBiCharPredicate predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.applyAsChar(k), a2);
	}

	/**
	* 'ToChar' - first, actual value will be converted to primitive type (contrary to the object).
	* '$' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K> LFunction<K, String> hasToChar$(@Nonnull LToCharFunction<K> extractor, char a2, @Nonnull LBiCharFunction<String> specialPredicate) {
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
	* '$' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K> LFunction<K, String> hasToChar$(@Nonnull LToCharFunction<K> extractor, @Nonnull LBiCharFunction<String> specialPredicate, char a2) {
		return hasToChar$(extractor, a2, specialPredicate);
	}

	/** 'ToChar' - first, actual value will be converted to primitive type (contrary to the object). */
	public static @Nonnull <K> LPredicate<K> haveToChar(@Nonnull LToCharFunction<K> extractor, char a2, char a3, @Nonnull LTriCharPredicate predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.applyAsChar(k), a2, a3);
	}

	/**
	* 'ToChar' - first, actual value will be converted to primitive type (contrary to the object).
	* '$' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K> LFunction<K, String> haveToChar$(@Nonnull LToCharFunction<K> extractor, char a2, char a3, @Nonnull LTriCharFunction<String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(extractor.applyAsChar(k), a2, a3);
	}

	/** 'ToChar' - first, actual value will be converted to primitive type (contrary to the object). */
	public static @Nonnull <K> LPredicate<K> haveToChar(@Nonnull LToCharFunction<K> extractor, @Nonnull LTriCharPredicate predicate, char a2, char a3) {
		return haveToChar(extractor, a2, a3, predicate);
	}

	/**
	* 'ToChar' - first, actual value will be converted to primitive type (contrary to the object).
	* '$' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K> LFunction<K, String> haveToChar$(@Nonnull LToCharFunction<K> extractor, @Nonnull LTriCharFunction<String> specialPredicate, char a2, char a3) {
		return haveToChar$(extractor, a2, a3, specialPredicate);
	}

	/** 'ToChar' - first, actual value will be converted to primitive type (contrary to the object). */
	public static @Nonnull <K> LPredicate<K> hasToChar(@Nonnull LToCharFunction<K> extractor, char a2, char a3, @Nonnull LTriCharPredicate predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.applyAsChar(k), a2, a3);
	}

	/**
	* 'ToChar' - first, actual value will be converted to primitive type (contrary to the object).
	* '$' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K> LFunction<K, String> hasToChar$(@Nonnull LToCharFunction<K> extractor, char a2, char a3, @Nonnull LTriCharFunction<String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(extractor.applyAsChar(k), a2, a3);
	}

	/** 'ToChar' - first, actual value will be converted to primitive type (contrary to the object). */
	public static @Nonnull <K> LPredicate<K> hasToChar(@Nonnull LToCharFunction<K> extractor, @Nonnull LTriCharPredicate predicate, char a2, char a3) {
		return hasToChar(extractor, a2, a3, predicate);
	}

	/**
	* 'ToChar' - first, actual value will be converted to primitive type (contrary to the object).
	* '$' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K> LFunction<K, String> hasToChar$(@Nonnull LToCharFunction<K> extractor, @Nonnull LTriCharFunction<String> specialPredicate, char a2, char a3) {
		return hasToChar$(extractor, a2, a3, specialPredicate);
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
	* '$' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K, V1> LFunction<K, String> haveToCharWith$(@Nonnull LToCharFunction<K> extractor, V1 with1, @Nonnull LObjCharFunction<? super V1, String> specialPredicate) {
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
	* '$' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K, V1> LFunction<K, String> haveToCharWith$(@Nonnull LToCharFunction<K> extractor, @Nonnull LObjCharFunction<? super V1, String> specialPredicate, V1 with1) {
		return haveToCharWith$(extractor, with1, specialPredicate);
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
	* '$' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K, V1> LFunction<K, String> hasToCharWith$(@Nonnull LToCharFunction<K> extractor, V1 with1, @Nonnull LObjCharFunction<? super V1, String> specialPredicate) {
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
	* '$' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K, V1> LFunction<K, String> hasToCharWith$(@Nonnull LToCharFunction<K> extractor, @Nonnull LObjCharFunction<? super V1, String> specialPredicate, V1 with1) {
		return hasToCharWith$(extractor, with1, specialPredicate);
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
	* '$' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K, V1, V2> LFunction<K, String> haveToCharWith$(@Nonnull LToCharFunction<K> extractor, V1 with1, V2 with2, @Nonnull LBiObjCharFunction<? super V1, ? super V2, String> specialPredicate) {
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
	* '$' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K, V1, V2> LFunction<K, String> haveToCharWith$(@Nonnull LToCharFunction<K> extractor, @Nonnull LBiObjCharFunction<? super V1, ? super V2, String> specialPredicate, V1 with1, V2 with2) {
		return haveToCharWith$(extractor, with1, with2, specialPredicate);
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
	* '$' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K, V1, V2> LFunction<K, String> hasToCharWith$(@Nonnull LToCharFunction<K> extractor, V1 with1, V2 with2, @Nonnull LBiObjCharFunction<? super V1, ? super V2, String> specialPredicate) {
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
	* '$' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K, V1, V2> LFunction<K, String> hasToCharWith$(@Nonnull LToCharFunction<K> extractor, @Nonnull LBiObjCharFunction<? super V1, ? super V2, String> specialPredicate, V1 with1, V2 with2) {
		return hasToCharWith$(extractor, with1, with2, specialPredicate);
	}

	/** 'ToSrt' - first, actual value will be converted to primitive type (contrary to the object). */
	public static @Nonnull <K> LPredicate<K> haveToSrt(@Nonnull LToSrtFunction<K> extractor, @Nonnull LSrtPredicate predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.applyAsSrt(k));
	}

	/**
	* 'ToSrt' - first, actual value will be converted to primitive type (contrary to the object).
	* '$' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K> LFunction<K, String> haveToSrt$(@Nonnull LToSrtFunction<K> extractor, @Nonnull LSrtFunction<String> specialPredicate) {
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
	* '$' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K> LFunction<K, String> hasToSrt$(@Nonnull LToSrtFunction<K> extractor, @Nonnull LSrtFunction<String> specialPredicate) {
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
	* '$' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K> LFunction<K, String> haveToSrt$(@Nonnull LToSrtFunction<K> extractor, short a2, @Nonnull LBiSrtFunction<String> specialPredicate) {
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
	* '$' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K> LFunction<K, String> haveToSrt$(@Nonnull LToSrtFunction<K> extractor, @Nonnull LBiSrtFunction<String> specialPredicate, short a2) {
		return haveToSrt$(extractor, a2, specialPredicate);
	}

	/** 'ToSrt' - first, actual value will be converted to primitive type (contrary to the object). */
	public static @Nonnull <K> LPredicate<K> hasToSrt(@Nonnull LToSrtFunction<K> extractor, short a2, @Nonnull LBiSrtPredicate predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.applyAsSrt(k), a2);
	}

	/**
	* 'ToSrt' - first, actual value will be converted to primitive type (contrary to the object).
	* '$' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K> LFunction<K, String> hasToSrt$(@Nonnull LToSrtFunction<K> extractor, short a2, @Nonnull LBiSrtFunction<String> specialPredicate) {
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
	* '$' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K> LFunction<K, String> hasToSrt$(@Nonnull LToSrtFunction<K> extractor, @Nonnull LBiSrtFunction<String> specialPredicate, short a2) {
		return hasToSrt$(extractor, a2, specialPredicate);
	}

	/** 'ToSrt' - first, actual value will be converted to primitive type (contrary to the object). */
	public static @Nonnull <K> LPredicate<K> haveToSrt(@Nonnull LToSrtFunction<K> extractor, short a2, short a3, @Nonnull LTriSrtPredicate predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.applyAsSrt(k), a2, a3);
	}

	/**
	* 'ToSrt' - first, actual value will be converted to primitive type (contrary to the object).
	* '$' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K> LFunction<K, String> haveToSrt$(@Nonnull LToSrtFunction<K> extractor, short a2, short a3, @Nonnull LTriSrtFunction<String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(extractor.applyAsSrt(k), a2, a3);
	}

	/** 'ToSrt' - first, actual value will be converted to primitive type (contrary to the object). */
	public static @Nonnull <K> LPredicate<K> haveToSrt(@Nonnull LToSrtFunction<K> extractor, @Nonnull LTriSrtPredicate predicate, short a2, short a3) {
		return haveToSrt(extractor, a2, a3, predicate);
	}

	/**
	* 'ToSrt' - first, actual value will be converted to primitive type (contrary to the object).
	* '$' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K> LFunction<K, String> haveToSrt$(@Nonnull LToSrtFunction<K> extractor, @Nonnull LTriSrtFunction<String> specialPredicate, short a2, short a3) {
		return haveToSrt$(extractor, a2, a3, specialPredicate);
	}

	/** 'ToSrt' - first, actual value will be converted to primitive type (contrary to the object). */
	public static @Nonnull <K> LPredicate<K> hasToSrt(@Nonnull LToSrtFunction<K> extractor, short a2, short a3, @Nonnull LTriSrtPredicate predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.applyAsSrt(k), a2, a3);
	}

	/**
	* 'ToSrt' - first, actual value will be converted to primitive type (contrary to the object).
	* '$' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K> LFunction<K, String> hasToSrt$(@Nonnull LToSrtFunction<K> extractor, short a2, short a3, @Nonnull LTriSrtFunction<String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(extractor.applyAsSrt(k), a2, a3);
	}

	/** 'ToSrt' - first, actual value will be converted to primitive type (contrary to the object). */
	public static @Nonnull <K> LPredicate<K> hasToSrt(@Nonnull LToSrtFunction<K> extractor, @Nonnull LTriSrtPredicate predicate, short a2, short a3) {
		return hasToSrt(extractor, a2, a3, predicate);
	}

	/**
	* 'ToSrt' - first, actual value will be converted to primitive type (contrary to the object).
	* '$' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K> LFunction<K, String> hasToSrt$(@Nonnull LToSrtFunction<K> extractor, @Nonnull LTriSrtFunction<String> specialPredicate, short a2, short a3) {
		return hasToSrt$(extractor, a2, a3, specialPredicate);
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
	* '$' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K, V1> LFunction<K, String> haveToSrtWith$(@Nonnull LToSrtFunction<K> extractor, V1 with1, @Nonnull LObjSrtFunction<? super V1, String> specialPredicate) {
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
	* '$' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K, V1> LFunction<K, String> haveToSrtWith$(@Nonnull LToSrtFunction<K> extractor, @Nonnull LObjSrtFunction<? super V1, String> specialPredicate, V1 with1) {
		return haveToSrtWith$(extractor, with1, specialPredicate);
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
	* '$' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K, V1> LFunction<K, String> hasToSrtWith$(@Nonnull LToSrtFunction<K> extractor, V1 with1, @Nonnull LObjSrtFunction<? super V1, String> specialPredicate) {
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
	* '$' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K, V1> LFunction<K, String> hasToSrtWith$(@Nonnull LToSrtFunction<K> extractor, @Nonnull LObjSrtFunction<? super V1, String> specialPredicate, V1 with1) {
		return hasToSrtWith$(extractor, with1, specialPredicate);
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
	* '$' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K, V1, V2> LFunction<K, String> haveToSrtWith$(@Nonnull LToSrtFunction<K> extractor, V1 with1, V2 with2, @Nonnull LBiObjSrtFunction<? super V1, ? super V2, String> specialPredicate) {
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
	* '$' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K, V1, V2> LFunction<K, String> haveToSrtWith$(@Nonnull LToSrtFunction<K> extractor, @Nonnull LBiObjSrtFunction<? super V1, ? super V2, String> specialPredicate, V1 with1, V2 with2) {
		return haveToSrtWith$(extractor, with1, with2, specialPredicate);
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
	* '$' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K, V1, V2> LFunction<K, String> hasToSrtWith$(@Nonnull LToSrtFunction<K> extractor, V1 with1, V2 with2, @Nonnull LBiObjSrtFunction<? super V1, ? super V2, String> specialPredicate) {
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
	* '$' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K, V1, V2> LFunction<K, String> hasToSrtWith$(@Nonnull LToSrtFunction<K> extractor, @Nonnull LBiObjSrtFunction<? super V1, ? super V2, String> specialPredicate, V1 with1, V2 with2) {
		return hasToSrtWith$(extractor, with1, with2, specialPredicate);
	}

	/** 'ToFlt' - first, actual value will be converted to primitive type (contrary to the object). */
	public static @Nonnull <K> LPredicate<K> haveToFlt(@Nonnull LToFltFunction<K> extractor, @Nonnull LFltPredicate predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.applyAsFlt(k));
	}

	/**
	* 'ToFlt' - first, actual value will be converted to primitive type (contrary to the object).
	* '$' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K> LFunction<K, String> haveToFlt$(@Nonnull LToFltFunction<K> extractor, @Nonnull LFltFunction<String> specialPredicate) {
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
	* '$' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K> LFunction<K, String> hasToFlt$(@Nonnull LToFltFunction<K> extractor, @Nonnull LFltFunction<String> specialPredicate) {
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
	* '$' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K> LFunction<K, String> haveToFlt$(@Nonnull LToFltFunction<K> extractor, float a2, @Nonnull LBiFltFunction<String> specialPredicate) {
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
	* '$' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K> LFunction<K, String> haveToFlt$(@Nonnull LToFltFunction<K> extractor, @Nonnull LBiFltFunction<String> specialPredicate, float a2) {
		return haveToFlt$(extractor, a2, specialPredicate);
	}

	/** 'ToFlt' - first, actual value will be converted to primitive type (contrary to the object). */
	public static @Nonnull <K> LPredicate<K> hasToFlt(@Nonnull LToFltFunction<K> extractor, float a2, @Nonnull LBiFltPredicate predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.applyAsFlt(k), a2);
	}

	/**
	* 'ToFlt' - first, actual value will be converted to primitive type (contrary to the object).
	* '$' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K> LFunction<K, String> hasToFlt$(@Nonnull LToFltFunction<K> extractor, float a2, @Nonnull LBiFltFunction<String> specialPredicate) {
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
	* '$' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K> LFunction<K, String> hasToFlt$(@Nonnull LToFltFunction<K> extractor, @Nonnull LBiFltFunction<String> specialPredicate, float a2) {
		return hasToFlt$(extractor, a2, specialPredicate);
	}

	/** 'ToFlt' - first, actual value will be converted to primitive type (contrary to the object). */
	public static @Nonnull <K> LPredicate<K> haveToFlt(@Nonnull LToFltFunction<K> extractor, float a2, float a3, @Nonnull LTriFltPredicate predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.applyAsFlt(k), a2, a3);
	}

	/**
	* 'ToFlt' - first, actual value will be converted to primitive type (contrary to the object).
	* '$' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K> LFunction<K, String> haveToFlt$(@Nonnull LToFltFunction<K> extractor, float a2, float a3, @Nonnull LTriFltFunction<String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(extractor.applyAsFlt(k), a2, a3);
	}

	/** 'ToFlt' - first, actual value will be converted to primitive type (contrary to the object). */
	public static @Nonnull <K> LPredicate<K> haveToFlt(@Nonnull LToFltFunction<K> extractor, @Nonnull LTriFltPredicate predicate, float a2, float a3) {
		return haveToFlt(extractor, a2, a3, predicate);
	}

	/**
	* 'ToFlt' - first, actual value will be converted to primitive type (contrary to the object).
	* '$' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K> LFunction<K, String> haveToFlt$(@Nonnull LToFltFunction<K> extractor, @Nonnull LTriFltFunction<String> specialPredicate, float a2, float a3) {
		return haveToFlt$(extractor, a2, a3, specialPredicate);
	}

	/** 'ToFlt' - first, actual value will be converted to primitive type (contrary to the object). */
	public static @Nonnull <K> LPredicate<K> hasToFlt(@Nonnull LToFltFunction<K> extractor, float a2, float a3, @Nonnull LTriFltPredicate predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.applyAsFlt(k), a2, a3);
	}

	/**
	* 'ToFlt' - first, actual value will be converted to primitive type (contrary to the object).
	* '$' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K> LFunction<K, String> hasToFlt$(@Nonnull LToFltFunction<K> extractor, float a2, float a3, @Nonnull LTriFltFunction<String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(extractor.applyAsFlt(k), a2, a3);
	}

	/** 'ToFlt' - first, actual value will be converted to primitive type (contrary to the object). */
	public static @Nonnull <K> LPredicate<K> hasToFlt(@Nonnull LToFltFunction<K> extractor, @Nonnull LTriFltPredicate predicate, float a2, float a3) {
		return hasToFlt(extractor, a2, a3, predicate);
	}

	/**
	* 'ToFlt' - first, actual value will be converted to primitive type (contrary to the object).
	* '$' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K> LFunction<K, String> hasToFlt$(@Nonnull LToFltFunction<K> extractor, @Nonnull LTriFltFunction<String> specialPredicate, float a2, float a3) {
		return hasToFlt$(extractor, a2, a3, specialPredicate);
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
	* '$' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K, V1> LFunction<K, String> haveToFltWith$(@Nonnull LToFltFunction<K> extractor, V1 with1, @Nonnull LObjFltFunction<? super V1, String> specialPredicate) {
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
	* '$' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K, V1> LFunction<K, String> haveToFltWith$(@Nonnull LToFltFunction<K> extractor, @Nonnull LObjFltFunction<? super V1, String> specialPredicate, V1 with1) {
		return haveToFltWith$(extractor, with1, specialPredicate);
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
	* '$' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K, V1> LFunction<K, String> hasToFltWith$(@Nonnull LToFltFunction<K> extractor, V1 with1, @Nonnull LObjFltFunction<? super V1, String> specialPredicate) {
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
	* '$' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K, V1> LFunction<K, String> hasToFltWith$(@Nonnull LToFltFunction<K> extractor, @Nonnull LObjFltFunction<? super V1, String> specialPredicate, V1 with1) {
		return hasToFltWith$(extractor, with1, specialPredicate);
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
	* '$' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K, V1, V2> LFunction<K, String> haveToFltWith$(@Nonnull LToFltFunction<K> extractor, V1 with1, V2 with2, @Nonnull LBiObjFltFunction<? super V1, ? super V2, String> specialPredicate) {
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
	* '$' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K, V1, V2> LFunction<K, String> haveToFltWith$(@Nonnull LToFltFunction<K> extractor, @Nonnull LBiObjFltFunction<? super V1, ? super V2, String> specialPredicate, V1 with1, V2 with2) {
		return haveToFltWith$(extractor, with1, with2, specialPredicate);
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
	* '$' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K, V1, V2> LFunction<K, String> hasToFltWith$(@Nonnull LToFltFunction<K> extractor, V1 with1, V2 with2, @Nonnull LBiObjFltFunction<? super V1, ? super V2, String> specialPredicate) {
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
	* '$' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K, V1, V2> LFunction<K, String> hasToFltWith$(@Nonnull LToFltFunction<K> extractor, @Nonnull LBiObjFltFunction<? super V1, ? super V2, String> specialPredicate, V1 with1, V2 with2) {
		return hasToFltWith$(extractor, with1, with2, specialPredicate);
	}

	/** 'ToInt' - first, actual value will be converted to primitive type (contrary to the object). */
	public static @Nonnull <K> LPredicate<K> haveToInt(@Nonnull LToIntFunction<K> extractor, @Nonnull LIntPredicate predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.applyAsInt(k));
	}

	/**
	* 'ToInt' - first, actual value will be converted to primitive type (contrary to the object).
	* '$' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K> LFunction<K, String> haveToInt$(@Nonnull LToIntFunction<K> extractor, @Nonnull LIntFunction<String> specialPredicate) {
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
	* '$' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K> LFunction<K, String> hasToInt$(@Nonnull LToIntFunction<K> extractor, @Nonnull LIntFunction<String> specialPredicate) {
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
	* '$' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K> LFunction<K, String> haveToInt$(@Nonnull LToIntFunction<K> extractor, int a2, @Nonnull LBiIntFunction<String> specialPredicate) {
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
	* '$' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K> LFunction<K, String> haveToInt$(@Nonnull LToIntFunction<K> extractor, @Nonnull LBiIntFunction<String> specialPredicate, int a2) {
		return haveToInt$(extractor, a2, specialPredicate);
	}

	/** 'ToInt' - first, actual value will be converted to primitive type (contrary to the object). */
	public static @Nonnull <K> LPredicate<K> hasToInt(@Nonnull LToIntFunction<K> extractor, int a2, @Nonnull LBiIntPredicate predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.applyAsInt(k), a2);
	}

	/**
	* 'ToInt' - first, actual value will be converted to primitive type (contrary to the object).
	* '$' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K> LFunction<K, String> hasToInt$(@Nonnull LToIntFunction<K> extractor, int a2, @Nonnull LBiIntFunction<String> specialPredicate) {
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
	* '$' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K> LFunction<K, String> hasToInt$(@Nonnull LToIntFunction<K> extractor, @Nonnull LBiIntFunction<String> specialPredicate, int a2) {
		return hasToInt$(extractor, a2, specialPredicate);
	}

	/** 'ToInt' - first, actual value will be converted to primitive type (contrary to the object). */
	public static @Nonnull <K> LPredicate<K> haveToInt(@Nonnull LToIntFunction<K> extractor, int a2, int a3, @Nonnull LTriIntPredicate predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.applyAsInt(k), a2, a3);
	}

	/**
	* 'ToInt' - first, actual value will be converted to primitive type (contrary to the object).
	* '$' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K> LFunction<K, String> haveToInt$(@Nonnull LToIntFunction<K> extractor, int a2, int a3, @Nonnull LTriIntFunction<String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(extractor.applyAsInt(k), a2, a3);
	}

	/** 'ToInt' - first, actual value will be converted to primitive type (contrary to the object). */
	public static @Nonnull <K> LPredicate<K> haveToInt(@Nonnull LToIntFunction<K> extractor, @Nonnull LTriIntPredicate predicate, int a2, int a3) {
		return haveToInt(extractor, a2, a3, predicate);
	}

	/**
	* 'ToInt' - first, actual value will be converted to primitive type (contrary to the object).
	* '$' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K> LFunction<K, String> haveToInt$(@Nonnull LToIntFunction<K> extractor, @Nonnull LTriIntFunction<String> specialPredicate, int a2, int a3) {
		return haveToInt$(extractor, a2, a3, specialPredicate);
	}

	/** 'ToInt' - first, actual value will be converted to primitive type (contrary to the object). */
	public static @Nonnull <K> LPredicate<K> hasToInt(@Nonnull LToIntFunction<K> extractor, int a2, int a3, @Nonnull LTriIntPredicate predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.applyAsInt(k), a2, a3);
	}

	/**
	* 'ToInt' - first, actual value will be converted to primitive type (contrary to the object).
	* '$' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K> LFunction<K, String> hasToInt$(@Nonnull LToIntFunction<K> extractor, int a2, int a3, @Nonnull LTriIntFunction<String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(extractor.applyAsInt(k), a2, a3);
	}

	/** 'ToInt' - first, actual value will be converted to primitive type (contrary to the object). */
	public static @Nonnull <K> LPredicate<K> hasToInt(@Nonnull LToIntFunction<K> extractor, @Nonnull LTriIntPredicate predicate, int a2, int a3) {
		return hasToInt(extractor, a2, a3, predicate);
	}

	/**
	* 'ToInt' - first, actual value will be converted to primitive type (contrary to the object).
	* '$' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K> LFunction<K, String> hasToInt$(@Nonnull LToIntFunction<K> extractor, @Nonnull LTriIntFunction<String> specialPredicate, int a2, int a3) {
		return hasToInt$(extractor, a2, a3, specialPredicate);
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
	* '$' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K, V1> LFunction<K, String> haveToIntWith$(@Nonnull LToIntFunction<K> extractor, V1 with1, @Nonnull LOiFunction<? super V1, String> specialPredicate) {
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
	* '$' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K, V1> LFunction<K, String> haveToIntWith$(@Nonnull LToIntFunction<K> extractor, @Nonnull LOiFunction<? super V1, String> specialPredicate, V1 with1) {
		return haveToIntWith$(extractor, with1, specialPredicate);
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
	* '$' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K, V1> LFunction<K, String> hasToIntWith$(@Nonnull LToIntFunction<K> extractor, V1 with1, @Nonnull LOiFunction<? super V1, String> specialPredicate) {
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
	* '$' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K, V1> LFunction<K, String> hasToIntWith$(@Nonnull LToIntFunction<K> extractor, @Nonnull LOiFunction<? super V1, String> specialPredicate, V1 with1) {
		return hasToIntWith$(extractor, with1, specialPredicate);
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
	* '$' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K, V1, V2> LFunction<K, String> haveToIntWith$(@Nonnull LToIntFunction<K> extractor, V1 with1, V2 with2, @Nonnull LBiObjIntFunction<? super V1, ? super V2, String> specialPredicate) {
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
	* '$' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K, V1, V2> LFunction<K, String> haveToIntWith$(@Nonnull LToIntFunction<K> extractor, @Nonnull LBiObjIntFunction<? super V1, ? super V2, String> specialPredicate, V1 with1, V2 with2) {
		return haveToIntWith$(extractor, with1, with2, specialPredicate);
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
	* '$' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K, V1, V2> LFunction<K, String> hasToIntWith$(@Nonnull LToIntFunction<K> extractor, V1 with1, V2 with2, @Nonnull LBiObjIntFunction<? super V1, ? super V2, String> specialPredicate) {
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
	* '$' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K, V1, V2> LFunction<K, String> hasToIntWith$(@Nonnull LToIntFunction<K> extractor, @Nonnull LBiObjIntFunction<? super V1, ? super V2, String> specialPredicate, V1 with1, V2 with2) {
		return hasToIntWith$(extractor, with1, with2, specialPredicate);
	}

	/** 'ToLong' - first, actual value will be converted to primitive type (contrary to the object). */
	public static @Nonnull <K> LPredicate<K> haveToLong(@Nonnull LToLongFunction<K> extractor, @Nonnull LLongPredicate predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.applyAsLong(k));
	}

	/**
	* 'ToLong' - first, actual value will be converted to primitive type (contrary to the object).
	* '$' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K> LFunction<K, String> haveToLong$(@Nonnull LToLongFunction<K> extractor, @Nonnull LLongFunction<String> specialPredicate) {
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
	* '$' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K> LFunction<K, String> hasToLong$(@Nonnull LToLongFunction<K> extractor, @Nonnull LLongFunction<String> specialPredicate) {
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
	* '$' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K> LFunction<K, String> haveToLong$(@Nonnull LToLongFunction<K> extractor, long a2, @Nonnull LBiLongFunction<String> specialPredicate) {
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
	* '$' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K> LFunction<K, String> haveToLong$(@Nonnull LToLongFunction<K> extractor, @Nonnull LBiLongFunction<String> specialPredicate, long a2) {
		return haveToLong$(extractor, a2, specialPredicate);
	}

	/** 'ToLong' - first, actual value will be converted to primitive type (contrary to the object). */
	public static @Nonnull <K> LPredicate<K> hasToLong(@Nonnull LToLongFunction<K> extractor, long a2, @Nonnull LBiLongPredicate predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.applyAsLong(k), a2);
	}

	/**
	* 'ToLong' - first, actual value will be converted to primitive type (contrary to the object).
	* '$' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K> LFunction<K, String> hasToLong$(@Nonnull LToLongFunction<K> extractor, long a2, @Nonnull LBiLongFunction<String> specialPredicate) {
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
	* '$' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K> LFunction<K, String> hasToLong$(@Nonnull LToLongFunction<K> extractor, @Nonnull LBiLongFunction<String> specialPredicate, long a2) {
		return hasToLong$(extractor, a2, specialPredicate);
	}

	/** 'ToLong' - first, actual value will be converted to primitive type (contrary to the object). */
	public static @Nonnull <K> LPredicate<K> haveToLong(@Nonnull LToLongFunction<K> extractor, long a2, long a3, @Nonnull LTriLongPredicate predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.applyAsLong(k), a2, a3);
	}

	/**
	* 'ToLong' - first, actual value will be converted to primitive type (contrary to the object).
	* '$' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K> LFunction<K, String> haveToLong$(@Nonnull LToLongFunction<K> extractor, long a2, long a3, @Nonnull LTriLongFunction<String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(extractor.applyAsLong(k), a2, a3);
	}

	/** 'ToLong' - first, actual value will be converted to primitive type (contrary to the object). */
	public static @Nonnull <K> LPredicate<K> haveToLong(@Nonnull LToLongFunction<K> extractor, @Nonnull LTriLongPredicate predicate, long a2, long a3) {
		return haveToLong(extractor, a2, a3, predicate);
	}

	/**
	* 'ToLong' - first, actual value will be converted to primitive type (contrary to the object).
	* '$' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K> LFunction<K, String> haveToLong$(@Nonnull LToLongFunction<K> extractor, @Nonnull LTriLongFunction<String> specialPredicate, long a2, long a3) {
		return haveToLong$(extractor, a2, a3, specialPredicate);
	}

	/** 'ToLong' - first, actual value will be converted to primitive type (contrary to the object). */
	public static @Nonnull <K> LPredicate<K> hasToLong(@Nonnull LToLongFunction<K> extractor, long a2, long a3, @Nonnull LTriLongPredicate predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.applyAsLong(k), a2, a3);
	}

	/**
	* 'ToLong' - first, actual value will be converted to primitive type (contrary to the object).
	* '$' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K> LFunction<K, String> hasToLong$(@Nonnull LToLongFunction<K> extractor, long a2, long a3, @Nonnull LTriLongFunction<String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(extractor.applyAsLong(k), a2, a3);
	}

	/** 'ToLong' - first, actual value will be converted to primitive type (contrary to the object). */
	public static @Nonnull <K> LPredicate<K> hasToLong(@Nonnull LToLongFunction<K> extractor, @Nonnull LTriLongPredicate predicate, long a2, long a3) {
		return hasToLong(extractor, a2, a3, predicate);
	}

	/**
	* 'ToLong' - first, actual value will be converted to primitive type (contrary to the object).
	* '$' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K> LFunction<K, String> hasToLong$(@Nonnull LToLongFunction<K> extractor, @Nonnull LTriLongFunction<String> specialPredicate, long a2, long a3) {
		return hasToLong$(extractor, a2, a3, specialPredicate);
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
	* '$' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K, V1> LFunction<K, String> haveToLongWith$(@Nonnull LToLongFunction<K> extractor, V1 with1, @Nonnull LObjLongFunction<? super V1, String> specialPredicate) {
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
	* '$' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K, V1> LFunction<K, String> haveToLongWith$(@Nonnull LToLongFunction<K> extractor, @Nonnull LObjLongFunction<? super V1, String> specialPredicate, V1 with1) {
		return haveToLongWith$(extractor, with1, specialPredicate);
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
	* '$' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K, V1> LFunction<K, String> hasToLongWith$(@Nonnull LToLongFunction<K> extractor, V1 with1, @Nonnull LObjLongFunction<? super V1, String> specialPredicate) {
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
	* '$' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K, V1> LFunction<K, String> hasToLongWith$(@Nonnull LToLongFunction<K> extractor, @Nonnull LObjLongFunction<? super V1, String> specialPredicate, V1 with1) {
		return hasToLongWith$(extractor, with1, specialPredicate);
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
	* '$' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K, V1, V2> LFunction<K, String> haveToLongWith$(@Nonnull LToLongFunction<K> extractor, V1 with1, V2 with2, @Nonnull LBiObjLongFunction<? super V1, ? super V2, String> specialPredicate) {
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
	* '$' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K, V1, V2> LFunction<K, String> haveToLongWith$(@Nonnull LToLongFunction<K> extractor, @Nonnull LBiObjLongFunction<? super V1, ? super V2, String> specialPredicate, V1 with1, V2 with2) {
		return haveToLongWith$(extractor, with1, with2, specialPredicate);
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
	* '$' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K, V1, V2> LFunction<K, String> hasToLongWith$(@Nonnull LToLongFunction<K> extractor, V1 with1, V2 with2, @Nonnull LBiObjLongFunction<? super V1, ? super V2, String> specialPredicate) {
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
	* '$' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K, V1, V2> LFunction<K, String> hasToLongWith$(@Nonnull LToLongFunction<K> extractor, @Nonnull LBiObjLongFunction<? super V1, ? super V2, String> specialPredicate, V1 with1, V2 with2) {
		return hasToLongWith$(extractor, with1, with2, specialPredicate);
	}

	// </editor-fold>

	static <T> boolean containsAndEmptiness(boolean positive, boolean collectionEmpty, T... elements) {
		var argsEmpty = elements.length == 0;
		if (collectionEmpty && argsEmpty) {
			return positive;
		}

		if (!collectionEmpty && argsEmpty) {
			return !positive;
		}
		return true;
	}

	/** Predicate: <%s> must be equal to <%s> (including array elements).*/
	public static boolean Equal(Object o1, Object o2) {
		if (o1 != null && o1.getClass().isArray() && o2 != null && o1.getClass() == o2.getClass()) {
			var arrayClass = o1.getClass();

			if (arrayClass == Object[].class) {
				return Arrays.equals((Object[]) o1, (Object[]) o2);
			}

			if (arrayClass == byte[].class) {
				return Arrays.equals((byte[]) o1, (byte[]) o2);
			}

			if (arrayClass == short[].class) {
				return Arrays.equals((short[]) o1, (short[]) o2);
			}

			if (arrayClass == int[].class) {
				return Arrays.equals((int[]) o1, (int[]) o2);
			}

			if (arrayClass == long[].class) {
				return Arrays.equals((long[]) o1, (long[]) o2);
			}

			if (arrayClass == float[].class) {
				return Arrays.equals((float[]) o1, (float[]) o2);
			}

			if (arrayClass == double[].class) {
				return Arrays.equals((double[]) o1, (double[]) o2);
			}

			if (arrayClass == char[].class) {
				return Arrays.equals((char[]) o1, (char[]) o2);
			}

			if (arrayClass == boolean[].class) {
				return Arrays.equals((boolean[]) o1, (boolean[]) o2);
			}

		}
		return Objects.equals(o1, o2);
	}

	/** "Special" predicate: <%s> must be equal to <%s> (including array elements). */
	public static @Nullable String Equal$(Object o1, Object o2) {
		return Equal(o1, o2) ? null : String.format("<%s> must be equal to <%s> (including array elements).", o1, o2);
	}
	/** Predicate: <%s> must NOT be equal to <%s> (including array elements)..*/
	public static boolean NotEqual(Object o1, Object o2) {
		return !Equal(o1, o2);
	}

	/** "Special" predicate: <%s> must NOT be equal to <%s> (including array elements). */
	public static @Nullable String NotEqual$(Object o1, Object o2) {
		return NotEqual(o1, o2) ? null : String.format("<%s> must NOT be equal to <%s> (including array elements).", o1, o2);
	}

	// <editor-fold desc="Future">

	/** Predicate: <%s> must be done.*/
	public static boolean done(@Nonnull Future<?> future) {
		Null.nonNullArg(future, "future");
		return future.isDone();
	}

	/** "Special" predicate: <%s> must be done. */
	public static @Nullable String done$(@Nonnull Future<?> future) {
		Null.nonNullArg(future, "future");
		return done(future) ? null : String.format("<%s> must be done.", future);
	}
	/** Predicate: <%s> must NOT be done..*/
	public static boolean notDone(@Nonnull Future<?> future) {
		Null.nonNullArg(future, "future");
		return !done(future);
	}

	/** "Special" predicate: <%s> must NOT be done. */
	public static @Nullable String notDone$(@Nonnull Future<?> future) {
		Null.nonNullArg(future, "future");
		return notDone(future) ? null : String.format("<%s> must NOT be done.", future);
	}

	/** Predicate: <%s> must be cancelled.*/
	public static boolean cancelled(@Nonnull Future<?> future) {
		Null.nonNullArg(future, "future");
		return future.isCancelled();
	}

	/** "Special" predicate: <%s> must be cancelled. */
	public static @Nullable String cancelled$(@Nonnull Future<?> future) {
		Null.nonNullArg(future, "future");
		return cancelled(future) ? null : String.format("<%s> must be cancelled.", future);
	}
	/** Predicate: <%s> must NOT be cancelled..*/
	public static boolean notCancelled(@Nonnull Future<?> future) {
		Null.nonNullArg(future, "future");
		return !cancelled(future);
	}

	/** "Special" predicate: <%s> must NOT be cancelled. */
	public static @Nullable String notCancelled$(@Nonnull Future<?> future) {
		Null.nonNullArg(future, "future");
		return notCancelled(future) ? null : String.format("<%s> must NOT be cancelled.", future);
	}

	/** Predicate: <%s> must be cancelled.*/
	public static boolean completedExceptionally(@Nonnull CompletableFuture<?> future) {
		Null.nonNullArg(future, "future");
		return future.isCompletedExceptionally();
	}

	/** "Special" predicate: <%s> must be cancelled. */
	public static @Nullable String completedExceptionally$(@Nonnull CompletableFuture<?> future) {
		Null.nonNullArg(future, "future");
		return completedExceptionally(future) ? null : String.format("<%s> must be cancelled.", future);
	}
	/** Predicate: <%s> must NOT be cancelled..*/
	public static boolean notCompletedExceptionally(@Nonnull CompletableFuture<?> future) {
		Null.nonNullArg(future, "future");
		return !completedExceptionally(future);
	}

	/** "Special" predicate: <%s> must NOT be cancelled. */
	public static @Nullable String notCompletedExceptionally$(@Nonnull CompletableFuture<?> future) {
		Null.nonNullArg(future, "future");
		return notCompletedExceptionally(future) ? null : String.format("<%s> must NOT be cancelled.", future);
	}

	/** Predicate: <%s> must produce value equal to <%s>.*/
	public static boolean produce(@Nonnull Future<?> future, Object expected) {
		Null.nonNullArg(future, "future");
		Object actual = null;
		try {
			actual = future.get();
		} catch (InterruptedException | ExecutionException | CancellationException e) {
			return false;
		}
		return done(future) && Objects.equals(actual, expected);
	}

	/** "Special" predicate: <%s> must produce value equal to <%s>. */
	public static @Nullable String produce$(@Nonnull Future<?> future, Object expected) {
		Null.nonNullArg(future, "future");
		return produce(future, expected) ? null : String.format("<%s> must produce value equal to <%s>.", future, expected);
	}
	/** Predicate: <%s> must NOT produce value equal to <%s>..*/
	public static boolean notProduce(@Nonnull Future<?> future, Object expected) {
		Null.nonNullArg(future, "future");
		return !produce(future, expected);
	}

	/** "Special" predicate: <%s> must NOT produce value equal to <%s>. */
	public static @Nullable String notProduce$(@Nonnull Future<?> future, Object expected) {
		Null.nonNullArg(future, "future");
		return notProduce(future, expected) ? null : String.format("<%s> must NOT produce value equal to <%s>.", future, expected);
	}

	// </editor-fold>

}
