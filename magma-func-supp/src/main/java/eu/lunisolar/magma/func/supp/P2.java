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
 *  *
 * This class contains some predicates that take - and ignore - additional argument(s) that are intended only for building a message.
 *
 * @see {@link Is}{@link Are}{@link Do}{@link Does}{@link Be}{@link Has}{@link Have}{@link P}{@link Predicates}{@link P1}{@link P2}
 */
@MethodReferences
public class P2 implements FluentSyntax {

	// <editor-fold desc="Object">

	/** Predicate: Object <%s> must be the same as <%s>..*/
	public static <MP1, MP2> boolean same(@Nullable Object n, @Nullable Object other, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.same(n, other);
	}

	/** "Special" predicate: Object <%s> must be the same as <%s>. */
	public static <MP1, MP2> @Nullable String same$(@Nullable Object n, @Nullable Object other, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.same$(n, other);
	}
	/** Predicate: Object <%s> must NOT be the same as <%s>..*/
	public static <MP1, MP2> boolean notSame(@Nullable Object n, @Nullable Object other, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.notSame(n, other);
	}

	/** "Special" predicate: Object <%s> must NOT be the same as <%s>. */
	public static <MP1, MP2> @Nullable String notSame$(@Nullable Object n, @Nullable Object other, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.notSame$(n, other);
	}

	/** Predicate: Reference must be null, currently is pointing to <%s>..*/
	public static <MP1, MP2> boolean Null(@Nullable Object n, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.Null(n);
	}

	/** "Special" predicate: Reference must be null, currently is pointing to <%s>. */
	public static <MP1, MP2> @Nullable String Null$(@Nullable Object n, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.Null$(n);
	}
	/** Predicate: Reference must NOT be null, currently is pointing to <%s>..*/
	public static <MP1, MP2> boolean notNull(@Nullable Object n, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.notNull(n);
	}

	/** "Special" predicate: Reference must NOT be null, currently is pointing to <%s>. */
	public static <MP1, MP2> @Nullable String notNull$(@Nullable Object n, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.notNull$(n);
	}

	// </editor-fold>

	// <editor-fold desc="`String`">

	/** Predicate: String <'%s'> must be <%d> characters long..*/
	public static <MP1, MP2> boolean length(@Nonnull CharSequence s, int size, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(s, "s");
		return Predicates.length(s, size);
	}

	/** "Special" predicate: String <'%s'> must be <%d> characters long. */
	public static <MP1, MP2> @Nullable String length$(@Nonnull CharSequence s, int size, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(s, "s");
		return Predicates.length$(s, size);
	}
	/** Predicate: String <'%s'> must NOT be <%d> characters long..*/
	public static <MP1, MP2> boolean notLength(@Nonnull CharSequence s, int size, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(s, "s");
		return Predicates.notLength(s, size);
	}

	/** "Special" predicate: String <'%s'> must NOT be <%d> characters long. */
	public static <MP1, MP2> @Nullable String notLength$(@Nonnull CharSequence s, int size, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(s, "s");
		return Predicates.notLength$(s, size);
	}

	/** Predicate: String <'%s'> must be <%d> characters long..*/
	public static <MP1, MP2> boolean ofLength(@Nonnull CharSequence s, int size, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(s, "s");
		return Predicates.ofLength(s, size);
	}

	/** "Special" predicate: String <'%s'> must be <%d> characters long. */
	public static <MP1, MP2> @Nullable String ofLength$(@Nonnull CharSequence s, int size, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(s, "s");
		return Predicates.ofLength$(s, size);
	}
	/** Predicate: String <'%s'> must NOT be <%d> characters long..*/
	public static <MP1, MP2> boolean notOfLength(@Nonnull CharSequence s, int size, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(s, "s");
		return Predicates.notOfLength(s, size);
	}

	/** "Special" predicate: String <'%s'> must NOT be <%d> characters long. */
	public static <MP1, MP2> @Nullable String notOfLength$(@Nonnull CharSequence s, int size, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(s, "s");
		return Predicates.notOfLength$(s, size);
	}

	/** Predicate: String <'%s'> must start with <'%s'>..*/
	public static <MP1, MP2> boolean startWith(@Nonnull String n, @Nonnull String a1, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(n, "n");
		Null.nonNullArg(a1, "a1");
		return Predicates.startWith(n, a1);
	}

	/** "Special" predicate: String <'%s'> must start with <'%s'>. */
	public static <MP1, MP2> @Nullable String startWith$(@Nonnull String n, @Nonnull String a1, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(n, "n");
		Null.nonNullArg(a1, "a1");
		return Predicates.startWith$(n, a1);
	}
	/** Predicate: String <'%s'> must NOT start with <'%s'>..*/
	public static <MP1, MP2> boolean notStartWith(@Nonnull String n, @Nonnull String a1, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(n, "n");
		Null.nonNullArg(a1, "a1");
		return Predicates.notStartWith(n, a1);
	}

	/** "Special" predicate: String <'%s'> must NOT start with <'%s'>. */
	public static <MP1, MP2> @Nullable String notStartWith$(@Nonnull String n, @Nonnull String a1, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(n, "n");
		Null.nonNullArg(a1, "a1");
		return Predicates.notStartWith$(n, a1);
	}

	/** Predicate: String <'%s'> must end with <'%s'>..*/
	public static <MP1, MP2> boolean endWith(@Nonnull String n, @Nonnull String a1, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(n, "n");
		Null.nonNullArg(a1, "a1");
		return Predicates.endWith(n, a1);
	}

	/** "Special" predicate: String <'%s'> must end with <'%s'>. */
	public static <MP1, MP2> @Nullable String endWith$(@Nonnull String n, @Nonnull String a1, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(n, "n");
		Null.nonNullArg(a1, "a1");
		return Predicates.endWith$(n, a1);
	}
	/** Predicate: String <'%s'> must NOT end with <'%s'>..*/
	public static <MP1, MP2> boolean notEndWith(@Nonnull String n, @Nonnull String a1, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(n, "n");
		Null.nonNullArg(a1, "a1");
		return Predicates.notEndWith(n, a1);
	}

	/** "Special" predicate: String <'%s'> must NOT end with <'%s'>. */
	public static <MP1, MP2> @Nullable String notEndWith$(@Nonnull String n, @Nonnull String a1, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(n, "n");
		Null.nonNullArg(a1, "a1");
		return Predicates.notEndWith$(n, a1);
	}

	/** Predicate: String <'%s'> must contain string <'%s'>. But does not..*/
	public static <MP1, MP2> boolean contain(@Nonnull String n, @Nonnull String a1, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(n, "n");
		Null.nonNullArg(a1, "a1");
		return Predicates.contain(n, a1);
	}

	/** "Special" predicate: String <'%s'> must contain string <'%s'>. But does not. */
	public static <MP1, MP2> @Nullable String contain$(@Nonnull String n, @Nonnull String a1, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(n, "n");
		Null.nonNullArg(a1, "a1");
		return Predicates.contain$(n, a1);
	}
	/** Predicate: String <'%s'> must NOT contain string <'%s'>. But does not..*/
	public static <MP1, MP2> boolean notContain(@Nonnull String n, @Nonnull String a1, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(n, "n");
		Null.nonNullArg(a1, "a1");
		return Predicates.notContain(n, a1);
	}

	/** "Special" predicate: String <'%s'> must NOT contain string <'%s'>. But does not. */
	public static <MP1, MP2> @Nullable String notContain$(@Nonnull String n, @Nonnull String a1, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(n, "n");
		Null.nonNullArg(a1, "a1");
		return Predicates.notContain$(n, a1);
	}

	/** Predicate: String <'%s'> must be empty..*/
	public static <MP1, MP2> boolean empty(@Nonnull String n, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(n, "n");
		return Predicates.empty(n);
	}

	/** "Special" predicate: String <'%s'> must be empty. */
	public static <MP1, MP2> @Nullable String empty$(@Nonnull String n, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(n, "n");
		return Predicates.empty$(n);
	}
	/** Predicate: String <'%s'> must NOT be empty..*/
	public static <MP1, MP2> boolean notEmpty(@Nonnull String n, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(n, "n");
		return Predicates.notEmpty(n);
	}

	/** "Special" predicate: String <'%s'> must NOT be empty. */
	public static <MP1, MP2> @Nullable String notEmpty$(@Nonnull String n, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(n, "n");
		return Predicates.notEmpty$(n);
	}

	/** Predicate: String <'%s'> must be blank (empty or consisting of only white characters)..*/
	public static <MP1, MP2> boolean blank(@Nonnull String n, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(n, "n");
		return Predicates.blank(n);
	}

	/** "Special" predicate: String <'%s'> must be blank (empty or consisting of only white characters). */
	public static <MP1, MP2> @Nullable String blank$(@Nonnull String n, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(n, "n");
		return Predicates.blank$(n);
	}
	/** Predicate: String <'%s'> must NOT be blank (empty or consisting of only white characters)..*/
	public static <MP1, MP2> boolean notBlank(@Nonnull String n, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(n, "n");
		return Predicates.notBlank(n);
	}

	/** "Special" predicate: String <'%s'> must NOT be blank (empty or consisting of only white characters). */
	public static <MP1, MP2> @Nullable String notBlank$(@Nonnull String n, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(n, "n");
		return Predicates.notBlank$(n);
	}

	/** Predicate: String <'%s'> must be null or empty..*/
	public static <MP1, MP2> boolean nullOrEmpty(@Nonnull String n, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.nullOrEmpty(n);
	}

	/** "Special" predicate: String <'%s'> must be null or empty. */
	public static <MP1, MP2> @Nullable String nullOrEmpty$(@Nonnull String n, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.nullOrEmpty$(n);
	}
	/** Predicate: String <'%s'> must NOT be null or empty..*/
	public static <MP1, MP2> boolean notNullNorEmpty(@Nonnull String n, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.notNullNorEmpty(n);
	}

	/** "Special" predicate: String <'%s'> must NOT be null or empty. */
	public static <MP1, MP2> @Nullable String notNullNorEmpty$(@Nonnull String n, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.notNullNorEmpty$(n);
	}

	/** Predicate: String <'%s'> must be null or blank..*/
	public static <MP1, MP2> boolean nullOrBlank(@Nonnull String n, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.nullOrBlank(n);
	}

	/** "Special" predicate: String <'%s'> must be null or blank. */
	public static <MP1, MP2> @Nullable String nullOrBlank$(@Nonnull String n, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.nullOrBlank$(n);
	}
	/** Predicate: String <'%s'> must NOT be null or blank..*/
	public static <MP1, MP2> boolean notNullNorBlank(@Nonnull String n, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.notNullNorBlank(n);
	}

	/** "Special" predicate: String <'%s'> must NOT be null or blank. */
	public static <MP1, MP2> @Nullable String notNullNorBlank$(@Nonnull String n, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.notNullNorBlank$(n);
	}

	// </editor-fold>

	// <editor-fold desc="and/or/xor">

	/** Predicate: AND.*/
	public static <MP1, MP2> boolean and(boolean op1, boolean op2, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.and(op1, op2);
	}

	/** "Special" predicate: AND */
	public static <MP1, MP2> @Nullable String and$(boolean op1, boolean op2, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.and$(op1, op2);
	}

	/** Predicate: OR.*/
	public static <MP1, MP2> boolean or(boolean op1, boolean op2, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.or(op1, op2);
	}

	/** "Special" predicate: OR */
	public static <MP1, MP2> @Nullable String or$(boolean op1, boolean op2, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.or$(op1, op2);
	}

	/** Predicate: XOR.*/
	public static <MP1, MP2> boolean xor(boolean op1, boolean op2, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.xor(op1, op2);
	}

	/** "Special" predicate: XOR */
	public static <MP1, MP2> @Nullable String xor$(boolean op1, boolean op2, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.xor$(op1, op2);
	}

	// </editor-fold>

	// <editor-fold desc="== <= >= < >">

	/** Predicate: <%s> must be equal to <%s>..*/
	public static <MP1, MP2> boolean equal(Object o1, Object o2, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.equal(o1, o2);
	}

	/** "Special" predicate: <%s> must be equal to <%s>. */
	public static <MP1, MP2> @Nullable String equal$(Object o1, Object o2, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.equal$(o1, o2);
	}
	/** Predicate: <%s> must NOT be equal to <%s>..*/
	public static <MP1, MP2> boolean notEqual(Object o1, Object o2, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.notEqual(o1, o2);
	}

	/** "Special" predicate: <%s> must NOT be equal to <%s>. */
	public static <MP1, MP2> @Nullable String notEqual$(Object o1, Object o2, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.notEqual$(o1, o2);
	}

	/** Predicate: <%s> must be equal to <%s>..*/
	public static <MP1, MP2> boolean objEqual(Object o1, Object o2, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.objEqual(o1, o2);
	}

	/** "Special" predicate: <%s> must be equal to <%s>. */
	public static <MP1, MP2> @Nullable String objEqual$(Object o1, Object o2, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.objEqual$(o1, o2);
	}
	/** Predicate: <%s> must NOT be equal to <%s>..*/
	public static <MP1, MP2> boolean objNotEqual(Object o1, Object o2, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.objNotEqual(o1, o2);
	}

	/** "Special" predicate: <%s> must NOT be equal to <%s>. */
	public static <MP1, MP2> @Nullable String objNotEqual$(Object o1, Object o2, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.objNotEqual$(o1, o2);
	}

	/** Predicate: <%s> must be equal to <%s>..*/
	public static <MP1, MP2> boolean equalToObj(byte n, Object o2, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.equalToObj(n, o2);
	}

	/** "Special" predicate: <%s> must be equal to <%s>. */
	public static <MP1, MP2> @Nullable String equalToObj$(byte n, Object o2, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.equalToObj$(n, o2);
	}
	/** Predicate: <%s> must NOT be equal to <%s>..*/
	public static <MP1, MP2> boolean notEqualToObj(byte n, Object o2, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.notEqualToObj(n, o2);
	}

	/** "Special" predicate: <%s> must NOT be equal to <%s>. */
	public static <MP1, MP2> @Nullable String notEqualToObj$(byte n, Object o2, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.notEqualToObj$(n, o2);
	}

	/** Predicate: %s must be equal to %s..*/
	public static <MP1, MP2> boolean equal(byte n, byte a1, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.equal(n, a1);
	}

	/** "Special" predicate: %s must be equal to %s. */
	public static <MP1, MP2> @Nullable String equal$(byte n, byte a1, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.equal$(n, a1);
	}
	/** Predicate: %s must NOT be equal to %s..*/
	public static <MP1, MP2> boolean notEqual(byte n, byte a1, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.notEqual(n, a1);
	}

	/** "Special" predicate: %s must NOT be equal to %s. */
	public static <MP1, MP2> @Nullable String notEqual$(byte n, byte a1, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.notEqual$(n, a1);
	}

	/** Predicate: %s must be > %s..*/
	public static <MP1, MP2> boolean gt(byte n, byte a1, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.gt(n, a1);
	}

	/** "Special" predicate: %s must be > %s. */
	public static <MP1, MP2> @Nullable String gt$(byte n, byte a1, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.gt$(n, a1);
	}
	/** Predicate: %s must NOT be > %s..*/
	public static <MP1, MP2> boolean notGt(byte n, byte a1, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.notGt(n, a1);
	}

	/** "Special" predicate: %s must NOT be > %s. */
	public static <MP1, MP2> @Nullable String notGt$(byte n, byte a1, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.notGt$(n, a1);
	}

	/** Predicate: %s must be < %s..*/
	public static <MP1, MP2> boolean lt(byte n, byte a1, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.lt(n, a1);
	}

	/** "Special" predicate: %s must be < %s. */
	public static <MP1, MP2> @Nullable String lt$(byte n, byte a1, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.lt$(n, a1);
	}
	/** Predicate: %s must NOT be < %s..*/
	public static <MP1, MP2> boolean notLt(byte n, byte a1, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.notLt(n, a1);
	}

	/** "Special" predicate: %s must NOT be < %s. */
	public static <MP1, MP2> @Nullable String notLt$(byte n, byte a1, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.notLt$(n, a1);
	}

	/** Predicate: %s must be >= %s..*/
	public static <MP1, MP2> boolean gtEq(byte n, byte a1, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.gtEq(n, a1);
	}

	/** "Special" predicate: %s must be >= %s. */
	public static <MP1, MP2> @Nullable String gtEq$(byte n, byte a1, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.gtEq$(n, a1);
	}
	/** Predicate: %s must NOT be >= %s..*/
	public static <MP1, MP2> boolean notGtEq(byte n, byte a1, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.notGtEq(n, a1);
	}

	/** "Special" predicate: %s must NOT be >= %s. */
	public static <MP1, MP2> @Nullable String notGtEq$(byte n, byte a1, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.notGtEq$(n, a1);
	}

	/** Predicate: %s must be <= %s..*/
	public static <MP1, MP2> boolean ltEq(byte n, byte a1, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.ltEq(n, a1);
	}

	/** "Special" predicate: %s must be <= %s. */
	public static <MP1, MP2> @Nullable String ltEq$(byte n, byte a1, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.ltEq$(n, a1);
	}
	/** Predicate: %s must NOT be <= %s..*/
	public static <MP1, MP2> boolean notLtEq(byte n, byte a1, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.notLtEq(n, a1);
	}

	/** "Special" predicate: %s must NOT be <= %s. */
	public static <MP1, MP2> @Nullable String notLtEq$(byte n, byte a1, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.notLtEq$(n, a1);
	}

	/** Predicate: %s must be >= 0 (must be non-negative)..*/
	public static <MP1, MP2> boolean nonNegative(byte n, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.nonNegative(n);
	}

	/** "Special" predicate: %s must be >= 0 (must be non-negative). */
	public static <MP1, MP2> @Nullable String nonNegative$(byte n, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.nonNegative$(n);
	}
	/** Predicate: %s must NOT be >= 0 (must be negative)..*/
	public static <MP1, MP2> boolean negative(byte n, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.negative(n);
	}

	/** "Special" predicate: %s must NOT be >= 0 (must be negative). */
	public static <MP1, MP2> @Nullable String negative$(byte n, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.negative$(n);
	}

	/** Predicate: %s must be <= 0 (must be non-positive)..*/
	public static <MP1, MP2> boolean nonPositive(byte n, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.nonPositive(n);
	}

	/** "Special" predicate: %s must be <= 0 (must be non-positive). */
	public static <MP1, MP2> @Nullable String nonPositive$(byte n, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.nonPositive$(n);
	}
	/** Predicate: %s must NOT be <= 0 (must be positive)..*/
	public static <MP1, MP2> boolean positive(byte n, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.positive(n);
	}

	/** "Special" predicate: %s must NOT be <= 0 (must be positive). */
	public static <MP1, MP2> @Nullable String positive$(byte n, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.positive$(n);
	}

	/** Predicate: <%s> must be equal to <%s>..*/
	public static <MP1, MP2> boolean equalToObj(short n, Object o2, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.equalToObj(n, o2);
	}

	/** "Special" predicate: <%s> must be equal to <%s>. */
	public static <MP1, MP2> @Nullable String equalToObj$(short n, Object o2, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.equalToObj$(n, o2);
	}
	/** Predicate: <%s> must NOT be equal to <%s>..*/
	public static <MP1, MP2> boolean notEqualToObj(short n, Object o2, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.notEqualToObj(n, o2);
	}

	/** "Special" predicate: <%s> must NOT be equal to <%s>. */
	public static <MP1, MP2> @Nullable String notEqualToObj$(short n, Object o2, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.notEqualToObj$(n, o2);
	}

	/** Predicate: %s must be equal to %s..*/
	public static <MP1, MP2> boolean equal(short n, short a1, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.equal(n, a1);
	}

	/** "Special" predicate: %s must be equal to %s. */
	public static <MP1, MP2> @Nullable String equal$(short n, short a1, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.equal$(n, a1);
	}
	/** Predicate: %s must NOT be equal to %s..*/
	public static <MP1, MP2> boolean notEqual(short n, short a1, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.notEqual(n, a1);
	}

	/** "Special" predicate: %s must NOT be equal to %s. */
	public static <MP1, MP2> @Nullable String notEqual$(short n, short a1, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.notEqual$(n, a1);
	}

	/** Predicate: %s must be > %s..*/
	public static <MP1, MP2> boolean gt(short n, short a1, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.gt(n, a1);
	}

	/** "Special" predicate: %s must be > %s. */
	public static <MP1, MP2> @Nullable String gt$(short n, short a1, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.gt$(n, a1);
	}
	/** Predicate: %s must NOT be > %s..*/
	public static <MP1, MP2> boolean notGt(short n, short a1, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.notGt(n, a1);
	}

	/** "Special" predicate: %s must NOT be > %s. */
	public static <MP1, MP2> @Nullable String notGt$(short n, short a1, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.notGt$(n, a1);
	}

	/** Predicate: %s must be < %s..*/
	public static <MP1, MP2> boolean lt(short n, short a1, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.lt(n, a1);
	}

	/** "Special" predicate: %s must be < %s. */
	public static <MP1, MP2> @Nullable String lt$(short n, short a1, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.lt$(n, a1);
	}
	/** Predicate: %s must NOT be < %s..*/
	public static <MP1, MP2> boolean notLt(short n, short a1, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.notLt(n, a1);
	}

	/** "Special" predicate: %s must NOT be < %s. */
	public static <MP1, MP2> @Nullable String notLt$(short n, short a1, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.notLt$(n, a1);
	}

	/** Predicate: %s must be >= %s..*/
	public static <MP1, MP2> boolean gtEq(short n, short a1, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.gtEq(n, a1);
	}

	/** "Special" predicate: %s must be >= %s. */
	public static <MP1, MP2> @Nullable String gtEq$(short n, short a1, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.gtEq$(n, a1);
	}
	/** Predicate: %s must NOT be >= %s..*/
	public static <MP1, MP2> boolean notGtEq(short n, short a1, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.notGtEq(n, a1);
	}

	/** "Special" predicate: %s must NOT be >= %s. */
	public static <MP1, MP2> @Nullable String notGtEq$(short n, short a1, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.notGtEq$(n, a1);
	}

	/** Predicate: %s must be <= %s..*/
	public static <MP1, MP2> boolean ltEq(short n, short a1, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.ltEq(n, a1);
	}

	/** "Special" predicate: %s must be <= %s. */
	public static <MP1, MP2> @Nullable String ltEq$(short n, short a1, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.ltEq$(n, a1);
	}
	/** Predicate: %s must NOT be <= %s..*/
	public static <MP1, MP2> boolean notLtEq(short n, short a1, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.notLtEq(n, a1);
	}

	/** "Special" predicate: %s must NOT be <= %s. */
	public static <MP1, MP2> @Nullable String notLtEq$(short n, short a1, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.notLtEq$(n, a1);
	}

	/** Predicate: %s must be >= 0 (must be non-negative)..*/
	public static <MP1, MP2> boolean nonNegative(short n, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.nonNegative(n);
	}

	/** "Special" predicate: %s must be >= 0 (must be non-negative). */
	public static <MP1, MP2> @Nullable String nonNegative$(short n, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.nonNegative$(n);
	}
	/** Predicate: %s must NOT be >= 0 (must be negative)..*/
	public static <MP1, MP2> boolean negative(short n, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.negative(n);
	}

	/** "Special" predicate: %s must NOT be >= 0 (must be negative). */
	public static <MP1, MP2> @Nullable String negative$(short n, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.negative$(n);
	}

	/** Predicate: %s must be <= 0 (must be non-positive)..*/
	public static <MP1, MP2> boolean nonPositive(short n, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.nonPositive(n);
	}

	/** "Special" predicate: %s must be <= 0 (must be non-positive). */
	public static <MP1, MP2> @Nullable String nonPositive$(short n, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.nonPositive$(n);
	}
	/** Predicate: %s must NOT be <= 0 (must be positive)..*/
	public static <MP1, MP2> boolean positive(short n, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.positive(n);
	}

	/** "Special" predicate: %s must NOT be <= 0 (must be positive). */
	public static <MP1, MP2> @Nullable String positive$(short n, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.positive$(n);
	}

	/** Predicate: <%s> must be equal to <%s>..*/
	public static <MP1, MP2> boolean equalToObj(int n, Object o2, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.equalToObj(n, o2);
	}

	/** "Special" predicate: <%s> must be equal to <%s>. */
	public static <MP1, MP2> @Nullable String equalToObj$(int n, Object o2, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.equalToObj$(n, o2);
	}
	/** Predicate: <%s> must NOT be equal to <%s>..*/
	public static <MP1, MP2> boolean notEqualToObj(int n, Object o2, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.notEqualToObj(n, o2);
	}

	/** "Special" predicate: <%s> must NOT be equal to <%s>. */
	public static <MP1, MP2> @Nullable String notEqualToObj$(int n, Object o2, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.notEqualToObj$(n, o2);
	}

	/** Predicate: %s must be equal to %s..*/
	public static <MP1, MP2> boolean equal(int n, int a1, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.equal(n, a1);
	}

	/** "Special" predicate: %s must be equal to %s. */
	public static <MP1, MP2> @Nullable String equal$(int n, int a1, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.equal$(n, a1);
	}
	/** Predicate: %s must NOT be equal to %s..*/
	public static <MP1, MP2> boolean notEqual(int n, int a1, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.notEqual(n, a1);
	}

	/** "Special" predicate: %s must NOT be equal to %s. */
	public static <MP1, MP2> @Nullable String notEqual$(int n, int a1, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.notEqual$(n, a1);
	}

	/** Predicate: %s must be > %s..*/
	public static <MP1, MP2> boolean gt(int n, int a1, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.gt(n, a1);
	}

	/** "Special" predicate: %s must be > %s. */
	public static <MP1, MP2> @Nullable String gt$(int n, int a1, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.gt$(n, a1);
	}
	/** Predicate: %s must NOT be > %s..*/
	public static <MP1, MP2> boolean notGt(int n, int a1, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.notGt(n, a1);
	}

	/** "Special" predicate: %s must NOT be > %s. */
	public static <MP1, MP2> @Nullable String notGt$(int n, int a1, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.notGt$(n, a1);
	}

	/** Predicate: %s must be < %s..*/
	public static <MP1, MP2> boolean lt(int n, int a1, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.lt(n, a1);
	}

	/** "Special" predicate: %s must be < %s. */
	public static <MP1, MP2> @Nullable String lt$(int n, int a1, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.lt$(n, a1);
	}
	/** Predicate: %s must NOT be < %s..*/
	public static <MP1, MP2> boolean notLt(int n, int a1, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.notLt(n, a1);
	}

	/** "Special" predicate: %s must NOT be < %s. */
	public static <MP1, MP2> @Nullable String notLt$(int n, int a1, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.notLt$(n, a1);
	}

	/** Predicate: %s must be >= %s..*/
	public static <MP1, MP2> boolean gtEq(int n, int a1, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.gtEq(n, a1);
	}

	/** "Special" predicate: %s must be >= %s. */
	public static <MP1, MP2> @Nullable String gtEq$(int n, int a1, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.gtEq$(n, a1);
	}
	/** Predicate: %s must NOT be >= %s..*/
	public static <MP1, MP2> boolean notGtEq(int n, int a1, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.notGtEq(n, a1);
	}

	/** "Special" predicate: %s must NOT be >= %s. */
	public static <MP1, MP2> @Nullable String notGtEq$(int n, int a1, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.notGtEq$(n, a1);
	}

	/** Predicate: %s must be <= %s..*/
	public static <MP1, MP2> boolean ltEq(int n, int a1, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.ltEq(n, a1);
	}

	/** "Special" predicate: %s must be <= %s. */
	public static <MP1, MP2> @Nullable String ltEq$(int n, int a1, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.ltEq$(n, a1);
	}
	/** Predicate: %s must NOT be <= %s..*/
	public static <MP1, MP2> boolean notLtEq(int n, int a1, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.notLtEq(n, a1);
	}

	/** "Special" predicate: %s must NOT be <= %s. */
	public static <MP1, MP2> @Nullable String notLtEq$(int n, int a1, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.notLtEq$(n, a1);
	}

	/** Predicate: %s must be >= 0 (must be non-negative)..*/
	public static <MP1, MP2> boolean nonNegative(int n, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.nonNegative(n);
	}

	/** "Special" predicate: %s must be >= 0 (must be non-negative). */
	public static <MP1, MP2> @Nullable String nonNegative$(int n, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.nonNegative$(n);
	}
	/** Predicate: %s must NOT be >= 0 (must be negative)..*/
	public static <MP1, MP2> boolean negative(int n, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.negative(n);
	}

	/** "Special" predicate: %s must NOT be >= 0 (must be negative). */
	public static <MP1, MP2> @Nullable String negative$(int n, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.negative$(n);
	}

	/** Predicate: %s must be <= 0 (must be non-positive)..*/
	public static <MP1, MP2> boolean nonPositive(int n, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.nonPositive(n);
	}

	/** "Special" predicate: %s must be <= 0 (must be non-positive). */
	public static <MP1, MP2> @Nullable String nonPositive$(int n, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.nonPositive$(n);
	}
	/** Predicate: %s must NOT be <= 0 (must be positive)..*/
	public static <MP1, MP2> boolean positive(int n, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.positive(n);
	}

	/** "Special" predicate: %s must NOT be <= 0 (must be positive). */
	public static <MP1, MP2> @Nullable String positive$(int n, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.positive$(n);
	}

	/** Predicate: <%s> must be equal to <%s>..*/
	public static <MP1, MP2> boolean equalToObj(long n, Object o2, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.equalToObj(n, o2);
	}

	/** "Special" predicate: <%s> must be equal to <%s>. */
	public static <MP1, MP2> @Nullable String equalToObj$(long n, Object o2, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.equalToObj$(n, o2);
	}
	/** Predicate: <%s> must NOT be equal to <%s>..*/
	public static <MP1, MP2> boolean notEqualToObj(long n, Object o2, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.notEqualToObj(n, o2);
	}

	/** "Special" predicate: <%s> must NOT be equal to <%s>. */
	public static <MP1, MP2> @Nullable String notEqualToObj$(long n, Object o2, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.notEqualToObj$(n, o2);
	}

	/** Predicate: %s must be equal to %s..*/
	public static <MP1, MP2> boolean equal(long n, long a1, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.equal(n, a1);
	}

	/** "Special" predicate: %s must be equal to %s. */
	public static <MP1, MP2> @Nullable String equal$(long n, long a1, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.equal$(n, a1);
	}
	/** Predicate: %s must NOT be equal to %s..*/
	public static <MP1, MP2> boolean notEqual(long n, long a1, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.notEqual(n, a1);
	}

	/** "Special" predicate: %s must NOT be equal to %s. */
	public static <MP1, MP2> @Nullable String notEqual$(long n, long a1, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.notEqual$(n, a1);
	}

	/** Predicate: %s must be > %s..*/
	public static <MP1, MP2> boolean gt(long n, long a1, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.gt(n, a1);
	}

	/** "Special" predicate: %s must be > %s. */
	public static <MP1, MP2> @Nullable String gt$(long n, long a1, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.gt$(n, a1);
	}
	/** Predicate: %s must NOT be > %s..*/
	public static <MP1, MP2> boolean notGt(long n, long a1, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.notGt(n, a1);
	}

	/** "Special" predicate: %s must NOT be > %s. */
	public static <MP1, MP2> @Nullable String notGt$(long n, long a1, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.notGt$(n, a1);
	}

	/** Predicate: %s must be < %s..*/
	public static <MP1, MP2> boolean lt(long n, long a1, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.lt(n, a1);
	}

	/** "Special" predicate: %s must be < %s. */
	public static <MP1, MP2> @Nullable String lt$(long n, long a1, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.lt$(n, a1);
	}
	/** Predicate: %s must NOT be < %s..*/
	public static <MP1, MP2> boolean notLt(long n, long a1, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.notLt(n, a1);
	}

	/** "Special" predicate: %s must NOT be < %s. */
	public static <MP1, MP2> @Nullable String notLt$(long n, long a1, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.notLt$(n, a1);
	}

	/** Predicate: %s must be >= %s..*/
	public static <MP1, MP2> boolean gtEq(long n, long a1, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.gtEq(n, a1);
	}

	/** "Special" predicate: %s must be >= %s. */
	public static <MP1, MP2> @Nullable String gtEq$(long n, long a1, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.gtEq$(n, a1);
	}
	/** Predicate: %s must NOT be >= %s..*/
	public static <MP1, MP2> boolean notGtEq(long n, long a1, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.notGtEq(n, a1);
	}

	/** "Special" predicate: %s must NOT be >= %s. */
	public static <MP1, MP2> @Nullable String notGtEq$(long n, long a1, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.notGtEq$(n, a1);
	}

	/** Predicate: %s must be <= %s..*/
	public static <MP1, MP2> boolean ltEq(long n, long a1, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.ltEq(n, a1);
	}

	/** "Special" predicate: %s must be <= %s. */
	public static <MP1, MP2> @Nullable String ltEq$(long n, long a1, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.ltEq$(n, a1);
	}
	/** Predicate: %s must NOT be <= %s..*/
	public static <MP1, MP2> boolean notLtEq(long n, long a1, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.notLtEq(n, a1);
	}

	/** "Special" predicate: %s must NOT be <= %s. */
	public static <MP1, MP2> @Nullable String notLtEq$(long n, long a1, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.notLtEq$(n, a1);
	}

	/** Predicate: %s must be >= 0 (must be non-negative)..*/
	public static <MP1, MP2> boolean nonNegative(long n, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.nonNegative(n);
	}

	/** "Special" predicate: %s must be >= 0 (must be non-negative). */
	public static <MP1, MP2> @Nullable String nonNegative$(long n, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.nonNegative$(n);
	}
	/** Predicate: %s must NOT be >= 0 (must be negative)..*/
	public static <MP1, MP2> boolean negative(long n, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.negative(n);
	}

	/** "Special" predicate: %s must NOT be >= 0 (must be negative). */
	public static <MP1, MP2> @Nullable String negative$(long n, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.negative$(n);
	}

	/** Predicate: %s must be <= 0 (must be non-positive)..*/
	public static <MP1, MP2> boolean nonPositive(long n, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.nonPositive(n);
	}

	/** "Special" predicate: %s must be <= 0 (must be non-positive). */
	public static <MP1, MP2> @Nullable String nonPositive$(long n, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.nonPositive$(n);
	}
	/** Predicate: %s must NOT be <= 0 (must be positive)..*/
	public static <MP1, MP2> boolean positive(long n, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.positive(n);
	}

	/** "Special" predicate: %s must NOT be <= 0 (must be positive). */
	public static <MP1, MP2> @Nullable String positive$(long n, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.positive$(n);
	}

	/** Predicate: <%s> must be equal to <%s>..*/
	public static <MP1, MP2> boolean equalToObj(float n, Object o2, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.equalToObj(n, o2);
	}

	/** "Special" predicate: <%s> must be equal to <%s>. */
	public static <MP1, MP2> @Nullable String equalToObj$(float n, Object o2, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.equalToObj$(n, o2);
	}
	/** Predicate: <%s> must NOT be equal to <%s>..*/
	public static <MP1, MP2> boolean notEqualToObj(float n, Object o2, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.notEqualToObj(n, o2);
	}

	/** "Special" predicate: <%s> must NOT be equal to <%s>. */
	public static <MP1, MP2> @Nullable String notEqualToObj$(float n, Object o2, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.notEqualToObj$(n, o2);
	}

	/** Predicate: %s must be equal to %s..*/
	public static <MP1, MP2> boolean equal(float n, float a1, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.equal(n, a1);
	}

	/** "Special" predicate: %s must be equal to %s. */
	public static <MP1, MP2> @Nullable String equal$(float n, float a1, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.equal$(n, a1);
	}
	/** Predicate: %s must NOT be equal to %s..*/
	public static <MP1, MP2> boolean notEqual(float n, float a1, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.notEqual(n, a1);
	}

	/** "Special" predicate: %s must NOT be equal to %s. */
	public static <MP1, MP2> @Nullable String notEqual$(float n, float a1, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.notEqual$(n, a1);
	}

	/** Predicate: %s must be > %s..*/
	public static <MP1, MP2> boolean gt(float n, float a1, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.gt(n, a1);
	}

	/** "Special" predicate: %s must be > %s. */
	public static <MP1, MP2> @Nullable String gt$(float n, float a1, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.gt$(n, a1);
	}
	/** Predicate: %s must NOT be > %s..*/
	public static <MP1, MP2> boolean notGt(float n, float a1, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.notGt(n, a1);
	}

	/** "Special" predicate: %s must NOT be > %s. */
	public static <MP1, MP2> @Nullable String notGt$(float n, float a1, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.notGt$(n, a1);
	}

	/** Predicate: %s must be < %s..*/
	public static <MP1, MP2> boolean lt(float n, float a1, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.lt(n, a1);
	}

	/** "Special" predicate: %s must be < %s. */
	public static <MP1, MP2> @Nullable String lt$(float n, float a1, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.lt$(n, a1);
	}
	/** Predicate: %s must NOT be < %s..*/
	public static <MP1, MP2> boolean notLt(float n, float a1, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.notLt(n, a1);
	}

	/** "Special" predicate: %s must NOT be < %s. */
	public static <MP1, MP2> @Nullable String notLt$(float n, float a1, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.notLt$(n, a1);
	}

	/** Predicate: %s must be >= %s..*/
	public static <MP1, MP2> boolean gtEq(float n, float a1, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.gtEq(n, a1);
	}

	/** "Special" predicate: %s must be >= %s. */
	public static <MP1, MP2> @Nullable String gtEq$(float n, float a1, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.gtEq$(n, a1);
	}
	/** Predicate: %s must NOT be >= %s..*/
	public static <MP1, MP2> boolean notGtEq(float n, float a1, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.notGtEq(n, a1);
	}

	/** "Special" predicate: %s must NOT be >= %s. */
	public static <MP1, MP2> @Nullable String notGtEq$(float n, float a1, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.notGtEq$(n, a1);
	}

	/** Predicate: %s must be <= %s..*/
	public static <MP1, MP2> boolean ltEq(float n, float a1, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.ltEq(n, a1);
	}

	/** "Special" predicate: %s must be <= %s. */
	public static <MP1, MP2> @Nullable String ltEq$(float n, float a1, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.ltEq$(n, a1);
	}
	/** Predicate: %s must NOT be <= %s..*/
	public static <MP1, MP2> boolean notLtEq(float n, float a1, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.notLtEq(n, a1);
	}

	/** "Special" predicate: %s must NOT be <= %s. */
	public static <MP1, MP2> @Nullable String notLtEq$(float n, float a1, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.notLtEq$(n, a1);
	}

	/** Predicate: %s must be >= 0 (must be non-negative)..*/
	public static <MP1, MP2> boolean nonNegative(float n, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.nonNegative(n);
	}

	/** "Special" predicate: %s must be >= 0 (must be non-negative). */
	public static <MP1, MP2> @Nullable String nonNegative$(float n, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.nonNegative$(n);
	}
	/** Predicate: %s must NOT be >= 0 (must be negative)..*/
	public static <MP1, MP2> boolean negative(float n, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.negative(n);
	}

	/** "Special" predicate: %s must NOT be >= 0 (must be negative). */
	public static <MP1, MP2> @Nullable String negative$(float n, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.negative$(n);
	}

	/** Predicate: %s must be <= 0 (must be non-positive)..*/
	public static <MP1, MP2> boolean nonPositive(float n, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.nonPositive(n);
	}

	/** "Special" predicate: %s must be <= 0 (must be non-positive). */
	public static <MP1, MP2> @Nullable String nonPositive$(float n, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.nonPositive$(n);
	}
	/** Predicate: %s must NOT be <= 0 (must be positive)..*/
	public static <MP1, MP2> boolean positive(float n, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.positive(n);
	}

	/** "Special" predicate: %s must NOT be <= 0 (must be positive). */
	public static <MP1, MP2> @Nullable String positive$(float n, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.positive$(n);
	}

	/** Predicate: <%s> must be equal to <%s>..*/
	public static <MP1, MP2> boolean equalToObj(double n, Object o2, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.equalToObj(n, o2);
	}

	/** "Special" predicate: <%s> must be equal to <%s>. */
	public static <MP1, MP2> @Nullable String equalToObj$(double n, Object o2, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.equalToObj$(n, o2);
	}
	/** Predicate: <%s> must NOT be equal to <%s>..*/
	public static <MP1, MP2> boolean notEqualToObj(double n, Object o2, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.notEqualToObj(n, o2);
	}

	/** "Special" predicate: <%s> must NOT be equal to <%s>. */
	public static <MP1, MP2> @Nullable String notEqualToObj$(double n, Object o2, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.notEqualToObj$(n, o2);
	}

	/** Predicate: %s must be equal to %s..*/
	public static <MP1, MP2> boolean equal(double n, double a1, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.equal(n, a1);
	}

	/** "Special" predicate: %s must be equal to %s. */
	public static <MP1, MP2> @Nullable String equal$(double n, double a1, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.equal$(n, a1);
	}
	/** Predicate: %s must NOT be equal to %s..*/
	public static <MP1, MP2> boolean notEqual(double n, double a1, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.notEqual(n, a1);
	}

	/** "Special" predicate: %s must NOT be equal to %s. */
	public static <MP1, MP2> @Nullable String notEqual$(double n, double a1, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.notEqual$(n, a1);
	}

	/** Predicate: %s must be > %s..*/
	public static <MP1, MP2> boolean gt(double n, double a1, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.gt(n, a1);
	}

	/** "Special" predicate: %s must be > %s. */
	public static <MP1, MP2> @Nullable String gt$(double n, double a1, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.gt$(n, a1);
	}
	/** Predicate: %s must NOT be > %s..*/
	public static <MP1, MP2> boolean notGt(double n, double a1, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.notGt(n, a1);
	}

	/** "Special" predicate: %s must NOT be > %s. */
	public static <MP1, MP2> @Nullable String notGt$(double n, double a1, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.notGt$(n, a1);
	}

	/** Predicate: %s must be < %s..*/
	public static <MP1, MP2> boolean lt(double n, double a1, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.lt(n, a1);
	}

	/** "Special" predicate: %s must be < %s. */
	public static <MP1, MP2> @Nullable String lt$(double n, double a1, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.lt$(n, a1);
	}
	/** Predicate: %s must NOT be < %s..*/
	public static <MP1, MP2> boolean notLt(double n, double a1, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.notLt(n, a1);
	}

	/** "Special" predicate: %s must NOT be < %s. */
	public static <MP1, MP2> @Nullable String notLt$(double n, double a1, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.notLt$(n, a1);
	}

	/** Predicate: %s must be >= %s..*/
	public static <MP1, MP2> boolean gtEq(double n, double a1, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.gtEq(n, a1);
	}

	/** "Special" predicate: %s must be >= %s. */
	public static <MP1, MP2> @Nullable String gtEq$(double n, double a1, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.gtEq$(n, a1);
	}
	/** Predicate: %s must NOT be >= %s..*/
	public static <MP1, MP2> boolean notGtEq(double n, double a1, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.notGtEq(n, a1);
	}

	/** "Special" predicate: %s must NOT be >= %s. */
	public static <MP1, MP2> @Nullable String notGtEq$(double n, double a1, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.notGtEq$(n, a1);
	}

	/** Predicate: %s must be <= %s..*/
	public static <MP1, MP2> boolean ltEq(double n, double a1, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.ltEq(n, a1);
	}

	/** "Special" predicate: %s must be <= %s. */
	public static <MP1, MP2> @Nullable String ltEq$(double n, double a1, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.ltEq$(n, a1);
	}
	/** Predicate: %s must NOT be <= %s..*/
	public static <MP1, MP2> boolean notLtEq(double n, double a1, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.notLtEq(n, a1);
	}

	/** "Special" predicate: %s must NOT be <= %s. */
	public static <MP1, MP2> @Nullable String notLtEq$(double n, double a1, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.notLtEq$(n, a1);
	}

	/** Predicate: %s must be >= 0 (must be non-negative)..*/
	public static <MP1, MP2> boolean nonNegative(double n, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.nonNegative(n);
	}

	/** "Special" predicate: %s must be >= 0 (must be non-negative). */
	public static <MP1, MP2> @Nullable String nonNegative$(double n, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.nonNegative$(n);
	}
	/** Predicate: %s must NOT be >= 0 (must be negative)..*/
	public static <MP1, MP2> boolean negative(double n, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.negative(n);
	}

	/** "Special" predicate: %s must NOT be >= 0 (must be negative). */
	public static <MP1, MP2> @Nullable String negative$(double n, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.negative$(n);
	}

	/** Predicate: %s must be <= 0 (must be non-positive)..*/
	public static <MP1, MP2> boolean nonPositive(double n, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.nonPositive(n);
	}

	/** "Special" predicate: %s must be <= 0 (must be non-positive). */
	public static <MP1, MP2> @Nullable String nonPositive$(double n, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.nonPositive$(n);
	}
	/** Predicate: %s must NOT be <= 0 (must be positive)..*/
	public static <MP1, MP2> boolean positive(double n, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.positive(n);
	}

	/** "Special" predicate: %s must NOT be <= 0 (must be positive). */
	public static <MP1, MP2> @Nullable String positive$(double n, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.positive$(n);
	}

	/** Predicate: <%s> must be equal to <%s>..*/
	public static <MP1, MP2> boolean equalToObj(char n, Object o2, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.equalToObj(n, o2);
	}

	/** "Special" predicate: <%s> must be equal to <%s>. */
	public static <MP1, MP2> @Nullable String equalToObj$(char n, Object o2, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.equalToObj$(n, o2);
	}
	/** Predicate: <%s> must NOT be equal to <%s>..*/
	public static <MP1, MP2> boolean notEqualToObj(char n, Object o2, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.notEqualToObj(n, o2);
	}

	/** "Special" predicate: <%s> must NOT be equal to <%s>. */
	public static <MP1, MP2> @Nullable String notEqualToObj$(char n, Object o2, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.notEqualToObj$(n, o2);
	}

	/** Predicate: %s must be equal to %s..*/
	public static <MP1, MP2> boolean equal(char n, char a1, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.equal(n, a1);
	}

	/** "Special" predicate: %s must be equal to %s. */
	public static <MP1, MP2> @Nullable String equal$(char n, char a1, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.equal$(n, a1);
	}
	/** Predicate: %s must NOT be equal to %s..*/
	public static <MP1, MP2> boolean notEqual(char n, char a1, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.notEqual(n, a1);
	}

	/** "Special" predicate: %s must NOT be equal to %s. */
	public static <MP1, MP2> @Nullable String notEqual$(char n, char a1, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.notEqual$(n, a1);
	}

	/** Predicate: %s must be > %s..*/
	public static <MP1, MP2> boolean gt(char n, char a1, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.gt(n, a1);
	}

	/** "Special" predicate: %s must be > %s. */
	public static <MP1, MP2> @Nullable String gt$(char n, char a1, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.gt$(n, a1);
	}
	/** Predicate: %s must NOT be > %s..*/
	public static <MP1, MP2> boolean notGt(char n, char a1, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.notGt(n, a1);
	}

	/** "Special" predicate: %s must NOT be > %s. */
	public static <MP1, MP2> @Nullable String notGt$(char n, char a1, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.notGt$(n, a1);
	}

	/** Predicate: %s must be < %s..*/
	public static <MP1, MP2> boolean lt(char n, char a1, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.lt(n, a1);
	}

	/** "Special" predicate: %s must be < %s. */
	public static <MP1, MP2> @Nullable String lt$(char n, char a1, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.lt$(n, a1);
	}
	/** Predicate: %s must NOT be < %s..*/
	public static <MP1, MP2> boolean notLt(char n, char a1, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.notLt(n, a1);
	}

	/** "Special" predicate: %s must NOT be < %s. */
	public static <MP1, MP2> @Nullable String notLt$(char n, char a1, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.notLt$(n, a1);
	}

	/** Predicate: %s must be >= %s..*/
	public static <MP1, MP2> boolean gtEq(char n, char a1, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.gtEq(n, a1);
	}

	/** "Special" predicate: %s must be >= %s. */
	public static <MP1, MP2> @Nullable String gtEq$(char n, char a1, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.gtEq$(n, a1);
	}
	/** Predicate: %s must NOT be >= %s..*/
	public static <MP1, MP2> boolean notGtEq(char n, char a1, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.notGtEq(n, a1);
	}

	/** "Special" predicate: %s must NOT be >= %s. */
	public static <MP1, MP2> @Nullable String notGtEq$(char n, char a1, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.notGtEq$(n, a1);
	}

	/** Predicate: %s must be <= %s..*/
	public static <MP1, MP2> boolean ltEq(char n, char a1, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.ltEq(n, a1);
	}

	/** "Special" predicate: %s must be <= %s. */
	public static <MP1, MP2> @Nullable String ltEq$(char n, char a1, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.ltEq$(n, a1);
	}
	/** Predicate: %s must NOT be <= %s..*/
	public static <MP1, MP2> boolean notLtEq(char n, char a1, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.notLtEq(n, a1);
	}

	/** "Special" predicate: %s must NOT be <= %s. */
	public static <MP1, MP2> @Nullable String notLtEq$(char n, char a1, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.notLtEq$(n, a1);
	}

	/** Predicate: %s must be >= 0 (must be non-negative)..*/
	public static <MP1, MP2> boolean nonNegative(char n, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.nonNegative(n);
	}

	/** "Special" predicate: %s must be >= 0 (must be non-negative). */
	public static <MP1, MP2> @Nullable String nonNegative$(char n, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.nonNegative$(n);
	}
	/** Predicate: %s must NOT be >= 0 (must be negative)..*/
	public static <MP1, MP2> boolean negative(char n, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.negative(n);
	}

	/** "Special" predicate: %s must NOT be >= 0 (must be negative). */
	public static <MP1, MP2> @Nullable String negative$(char n, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.negative$(n);
	}

	/** Predicate: %s must be <= 0 (must be non-positive)..*/
	public static <MP1, MP2> boolean nonPositive(char n, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.nonPositive(n);
	}

	/** "Special" predicate: %s must be <= 0 (must be non-positive). */
	public static <MP1, MP2> @Nullable String nonPositive$(char n, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.nonPositive$(n);
	}
	/** Predicate: %s must NOT be <= 0 (must be positive)..*/
	public static <MP1, MP2> boolean positive(char n, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.positive(n);
	}

	/** "Special" predicate: %s must NOT be <= 0 (must be positive). */
	public static <MP1, MP2> @Nullable String positive$(char n, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.positive$(n);
	}

	/** Predicate: <%s> must be equal to <%s>..*/
	public static <MP1, MP2> boolean equalToObj(boolean n, Object o2, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.equalToObj(n, o2);
	}

	/** "Special" predicate: <%s> must be equal to <%s>. */
	public static <MP1, MP2> @Nullable String equalToObj$(boolean n, Object o2, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.equalToObj$(n, o2);
	}
	/** Predicate: <%s> must NOT be equal to <%s>..*/
	public static <MP1, MP2> boolean notEqualToObj(boolean n, Object o2, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.notEqualToObj(n, o2);
	}

	/** "Special" predicate: <%s> must NOT be equal to <%s>. */
	public static <MP1, MP2> @Nullable String notEqualToObj$(boolean n, Object o2, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.notEqualToObj$(n, o2);
	}

	/** Predicate: %s must be equal to %s..*/
	public static <MP1, MP2> boolean equal(boolean n, boolean a1, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.equal(n, a1);
	}

	/** "Special" predicate: %s must be equal to %s. */
	public static <MP1, MP2> @Nullable String equal$(boolean n, boolean a1, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.equal$(n, a1);
	}
	/** Predicate: %s must NOT be equal to %s..*/
	public static <MP1, MP2> boolean notEqual(boolean n, boolean a1, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.notEqual(n, a1);
	}

	/** "Special" predicate: %s must NOT be equal to %s. */
	public static <MP1, MP2> @Nullable String notEqual$(boolean n, boolean a1, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.notEqual$(n, a1);
	}

	/** Predicate: %1$s must be: %2$s < %1$s < %3$s..*/
	public static <MP1, MP2> boolean between(byte n, byte a1, byte a2, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.between(n, a1, a2);
	}

	/** "Special" predicate: %1$s must be: %2$s < %1$s < %3$s. */
	public static <MP1, MP2> @Nullable String between$(byte n, byte a1, byte a2, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.between$(n, a1, a2);
	}
	/** Predicate: %1$s must NOT be: %2$s < %1$s < %3$s..*/
	public static <MP1, MP2> boolean notBetween(byte n, byte a1, byte a2, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.notBetween(n, a1, a2);
	}

	/** "Special" predicate: %1$s must NOT be: %2$s < %1$s < %3$s. */
	public static <MP1, MP2> @Nullable String notBetween$(byte n, byte a1, byte a2, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.notBetween$(n, a1, a2);
	}

	/** Predicate: %1$s must be: %2$s <= %1$s <= %3$s..*/
	public static <MP1, MP2> boolean inRange(byte n, byte a1, byte a2, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.inRange(n, a1, a2);
	}

	/** "Special" predicate: %1$s must be: %2$s <= %1$s <= %3$s. */
	public static <MP1, MP2> @Nullable String inRange$(byte n, byte a1, byte a2, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.inRange$(n, a1, a2);
	}
	/** Predicate: %1$s must NOT be: %2$s <= %1$s <= %3$s..*/
	public static <MP1, MP2> boolean notInRange(byte n, byte a1, byte a2, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.notInRange(n, a1, a2);
	}

	/** "Special" predicate: %1$s must NOT be: %2$s <= %1$s <= %3$s. */
	public static <MP1, MP2> @Nullable String notInRange$(byte n, byte a1, byte a2, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.notInRange$(n, a1, a2);
	}

	/** Predicate: %1$s must be: %2$s < %1$s < %3$s..*/
	public static <MP1, MP2> boolean between(short n, short a1, short a2, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.between(n, a1, a2);
	}

	/** "Special" predicate: %1$s must be: %2$s < %1$s < %3$s. */
	public static <MP1, MP2> @Nullable String between$(short n, short a1, short a2, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.between$(n, a1, a2);
	}
	/** Predicate: %1$s must NOT be: %2$s < %1$s < %3$s..*/
	public static <MP1, MP2> boolean notBetween(short n, short a1, short a2, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.notBetween(n, a1, a2);
	}

	/** "Special" predicate: %1$s must NOT be: %2$s < %1$s < %3$s. */
	public static <MP1, MP2> @Nullable String notBetween$(short n, short a1, short a2, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.notBetween$(n, a1, a2);
	}

	/** Predicate: %1$s must be: %2$s <= %1$s <= %3$s..*/
	public static <MP1, MP2> boolean inRange(short n, short a1, short a2, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.inRange(n, a1, a2);
	}

	/** "Special" predicate: %1$s must be: %2$s <= %1$s <= %3$s. */
	public static <MP1, MP2> @Nullable String inRange$(short n, short a1, short a2, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.inRange$(n, a1, a2);
	}
	/** Predicate: %1$s must NOT be: %2$s <= %1$s <= %3$s..*/
	public static <MP1, MP2> boolean notInRange(short n, short a1, short a2, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.notInRange(n, a1, a2);
	}

	/** "Special" predicate: %1$s must NOT be: %2$s <= %1$s <= %3$s. */
	public static <MP1, MP2> @Nullable String notInRange$(short n, short a1, short a2, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.notInRange$(n, a1, a2);
	}

	/** Predicate: %1$s must be: %2$s < %1$s < %3$s..*/
	public static <MP1, MP2> boolean between(int n, int a1, int a2, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.between(n, a1, a2);
	}

	/** "Special" predicate: %1$s must be: %2$s < %1$s < %3$s. */
	public static <MP1, MP2> @Nullable String between$(int n, int a1, int a2, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.between$(n, a1, a2);
	}
	/** Predicate: %1$s must NOT be: %2$s < %1$s < %3$s..*/
	public static <MP1, MP2> boolean notBetween(int n, int a1, int a2, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.notBetween(n, a1, a2);
	}

	/** "Special" predicate: %1$s must NOT be: %2$s < %1$s < %3$s. */
	public static <MP1, MP2> @Nullable String notBetween$(int n, int a1, int a2, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.notBetween$(n, a1, a2);
	}

	/** Predicate: %1$s must be: %2$s <= %1$s <= %3$s..*/
	public static <MP1, MP2> boolean inRange(int n, int a1, int a2, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.inRange(n, a1, a2);
	}

	/** "Special" predicate: %1$s must be: %2$s <= %1$s <= %3$s. */
	public static <MP1, MP2> @Nullable String inRange$(int n, int a1, int a2, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.inRange$(n, a1, a2);
	}
	/** Predicate: %1$s must NOT be: %2$s <= %1$s <= %3$s..*/
	public static <MP1, MP2> boolean notInRange(int n, int a1, int a2, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.notInRange(n, a1, a2);
	}

	/** "Special" predicate: %1$s must NOT be: %2$s <= %1$s <= %3$s. */
	public static <MP1, MP2> @Nullable String notInRange$(int n, int a1, int a2, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.notInRange$(n, a1, a2);
	}

	/** Predicate: %1$s must be: %2$s < %1$s < %3$s..*/
	public static <MP1, MP2> boolean between(long n, long a1, long a2, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.between(n, a1, a2);
	}

	/** "Special" predicate: %1$s must be: %2$s < %1$s < %3$s. */
	public static <MP1, MP2> @Nullable String between$(long n, long a1, long a2, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.between$(n, a1, a2);
	}
	/** Predicate: %1$s must NOT be: %2$s < %1$s < %3$s..*/
	public static <MP1, MP2> boolean notBetween(long n, long a1, long a2, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.notBetween(n, a1, a2);
	}

	/** "Special" predicate: %1$s must NOT be: %2$s < %1$s < %3$s. */
	public static <MP1, MP2> @Nullable String notBetween$(long n, long a1, long a2, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.notBetween$(n, a1, a2);
	}

	/** Predicate: %1$s must be: %2$s <= %1$s <= %3$s..*/
	public static <MP1, MP2> boolean inRange(long n, long a1, long a2, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.inRange(n, a1, a2);
	}

	/** "Special" predicate: %1$s must be: %2$s <= %1$s <= %3$s. */
	public static <MP1, MP2> @Nullable String inRange$(long n, long a1, long a2, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.inRange$(n, a1, a2);
	}
	/** Predicate: %1$s must NOT be: %2$s <= %1$s <= %3$s..*/
	public static <MP1, MP2> boolean notInRange(long n, long a1, long a2, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.notInRange(n, a1, a2);
	}

	/** "Special" predicate: %1$s must NOT be: %2$s <= %1$s <= %3$s. */
	public static <MP1, MP2> @Nullable String notInRange$(long n, long a1, long a2, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.notInRange$(n, a1, a2);
	}

	/** Predicate: %1$s must be: %2$s < %1$s < %3$s..*/
	public static <MP1, MP2> boolean between(float n, float a1, float a2, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.between(n, a1, a2);
	}

	/** "Special" predicate: %1$s must be: %2$s < %1$s < %3$s. */
	public static <MP1, MP2> @Nullable String between$(float n, float a1, float a2, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.between$(n, a1, a2);
	}
	/** Predicate: %1$s must NOT be: %2$s < %1$s < %3$s..*/
	public static <MP1, MP2> boolean notBetween(float n, float a1, float a2, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.notBetween(n, a1, a2);
	}

	/** "Special" predicate: %1$s must NOT be: %2$s < %1$s < %3$s. */
	public static <MP1, MP2> @Nullable String notBetween$(float n, float a1, float a2, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.notBetween$(n, a1, a2);
	}

	/** Predicate: %1$s must be: %2$s <= %1$s <= %3$s..*/
	public static <MP1, MP2> boolean inRange(float n, float a1, float a2, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.inRange(n, a1, a2);
	}

	/** "Special" predicate: %1$s must be: %2$s <= %1$s <= %3$s. */
	public static <MP1, MP2> @Nullable String inRange$(float n, float a1, float a2, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.inRange$(n, a1, a2);
	}
	/** Predicate: %1$s must NOT be: %2$s <= %1$s <= %3$s..*/
	public static <MP1, MP2> boolean notInRange(float n, float a1, float a2, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.notInRange(n, a1, a2);
	}

	/** "Special" predicate: %1$s must NOT be: %2$s <= %1$s <= %3$s. */
	public static <MP1, MP2> @Nullable String notInRange$(float n, float a1, float a2, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.notInRange$(n, a1, a2);
	}

	/** Predicate: %1$s must be: %2$s < %1$s < %3$s..*/
	public static <MP1, MP2> boolean between(double n, double a1, double a2, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.between(n, a1, a2);
	}

	/** "Special" predicate: %1$s must be: %2$s < %1$s < %3$s. */
	public static <MP1, MP2> @Nullable String between$(double n, double a1, double a2, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.between$(n, a1, a2);
	}
	/** Predicate: %1$s must NOT be: %2$s < %1$s < %3$s..*/
	public static <MP1, MP2> boolean notBetween(double n, double a1, double a2, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.notBetween(n, a1, a2);
	}

	/** "Special" predicate: %1$s must NOT be: %2$s < %1$s < %3$s. */
	public static <MP1, MP2> @Nullable String notBetween$(double n, double a1, double a2, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.notBetween$(n, a1, a2);
	}

	/** Predicate: %1$s must be: %2$s <= %1$s <= %3$s..*/
	public static <MP1, MP2> boolean inRange(double n, double a1, double a2, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.inRange(n, a1, a2);
	}

	/** "Special" predicate: %1$s must be: %2$s <= %1$s <= %3$s. */
	public static <MP1, MP2> @Nullable String inRange$(double n, double a1, double a2, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.inRange$(n, a1, a2);
	}
	/** Predicate: %1$s must NOT be: %2$s <= %1$s <= %3$s..*/
	public static <MP1, MP2> boolean notInRange(double n, double a1, double a2, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.notInRange(n, a1, a2);
	}

	/** "Special" predicate: %1$s must NOT be: %2$s <= %1$s <= %3$s. */
	public static <MP1, MP2> @Nullable String notInRange$(double n, double a1, double a2, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.notInRange$(n, a1, a2);
	}

	/** Predicate: %1$s must be: %2$s < %1$s < %3$s..*/
	public static <MP1, MP2> boolean between(char n, char a1, char a2, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.between(n, a1, a2);
	}

	/** "Special" predicate: %1$s must be: %2$s < %1$s < %3$s. */
	public static <MP1, MP2> @Nullable String between$(char n, char a1, char a2, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.between$(n, a1, a2);
	}
	/** Predicate: %1$s must NOT be: %2$s < %1$s < %3$s..*/
	public static <MP1, MP2> boolean notBetween(char n, char a1, char a2, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.notBetween(n, a1, a2);
	}

	/** "Special" predicate: %1$s must NOT be: %2$s < %1$s < %3$s. */
	public static <MP1, MP2> @Nullable String notBetween$(char n, char a1, char a2, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.notBetween$(n, a1, a2);
	}

	/** Predicate: %1$s must be: %2$s <= %1$s <= %3$s..*/
	public static <MP1, MP2> boolean inRange(char n, char a1, char a2, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.inRange(n, a1, a2);
	}

	/** "Special" predicate: %1$s must be: %2$s <= %1$s <= %3$s. */
	public static <MP1, MP2> @Nullable String inRange$(char n, char a1, char a2, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.inRange$(n, a1, a2);
	}
	/** Predicate: %1$s must NOT be: %2$s <= %1$s <= %3$s..*/
	public static <MP1, MP2> boolean notInRange(char n, char a1, char a2, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.notInRange(n, a1, a2);
	}

	/** "Special" predicate: %1$s must NOT be: %2$s <= %1$s <= %3$s. */
	public static <MP1, MP2> @Nullable String notInRange$(char n, char a1, char a2, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.notInRange$(n, a1, a2);
	}

	/** Predicate: <%s> must be true..*/
	public static <MP1, MP2> boolean True(boolean v, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.True(v);
	}

	/** "Special" predicate: <%s> must be true. */
	public static <MP1, MP2> @Nullable String True$(boolean v, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.True$(v);
	}
	/** Predicate: <%s> must be false..*/
	public static <MP1, MP2> boolean False(boolean v, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.False(v);
	}

	/** "Special" predicate: <%s> must be false. */
	public static <MP1, MP2> @Nullable String False$(boolean v, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.False$(v);
	}

	// </editor-fold>

	// <editor-fold desc="arrays">

	/** Predicate: Array <%s> must be of size %s..*/
	public static <MP1, MP2> boolean length(@Nonnull boolean[] array, int i, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(array, "array");
		return Predicates.length(array, i);
	}

	/** "Special" predicate: Array <%s> must be of size %s. */
	public static <MP1, MP2> @Nullable String length$(@Nonnull boolean[] array, int i, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(array, "array");
		return Predicates.length$(array, i);
	}
	/** Predicate: Array <%s> must NOT be of size %s..*/
	public static <MP1, MP2> boolean lengthOtherThan(@Nonnull boolean[] array, int i, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(array, "array");
		return Predicates.lengthOtherThan(array, i);
	}

	/** "Special" predicate: Array <%s> must NOT be of size %s. */
	public static <MP1, MP2> @Nullable String lengthOtherThan$(@Nonnull boolean[] array, int i, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(array, "array");
		return Predicates.lengthOtherThan$(array, i);
	}

	/** Predicate: Array <%s> must be of size %s..*/
	public static <MP1, MP2> boolean ofLength(@Nonnull boolean[] array, int i, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(array, "array");
		return Predicates.ofLength(array, i);
	}

	/** "Special" predicate: Array <%s> must be of size %s. */
	public static <MP1, MP2> @Nullable String ofLength$(@Nonnull boolean[] array, int i, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(array, "array");
		return Predicates.ofLength$(array, i);
	}
	/** Predicate: Array <%s> must NOT be of size %s..*/
	public static <MP1, MP2> boolean notOfLength(@Nonnull boolean[] array, int i, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(array, "array");
		return Predicates.notOfLength(array, i);
	}

	/** "Special" predicate: Array <%s> must NOT be of size %s. */
	public static <MP1, MP2> @Nullable String notOfLength$(@Nonnull boolean[] array, int i, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(array, "array");
		return Predicates.notOfLength$(array, i);
	}

	/** Predicate: Array <%s> must be empty..*/
	public static <MP1, MP2> boolean empty(@Nonnull boolean[] array, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(array, "array");
		return Predicates.empty(array);
	}

	/** "Special" predicate: Array <%s> must be empty. */
	public static <MP1, MP2> @Nullable String empty$(@Nonnull boolean[] array, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(array, "array");
		return Predicates.empty$(array);
	}
	/** Predicate: Array <%s> must NOT be empty..*/
	public static <MP1, MP2> boolean notEmpty(@Nonnull boolean[] array, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(array, "array");
		return Predicates.notEmpty(array);
	}

	/** "Special" predicate: Array <%s> must NOT be empty. */
	public static <MP1, MP2> @Nullable String notEmpty$(@Nonnull boolean[] array, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(array, "array");
		return Predicates.notEmpty$(array);
	}

	/** Predicate: Array <%s> must be null or empty..*/
	public static <MP1, MP2> boolean nullOrEmpty(@Nullable boolean[] array, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.nullOrEmpty(array);
	}

	/** "Special" predicate: Array <%s> must be null or empty. */
	public static <MP1, MP2> @Nullable String nullOrEmpty$(@Nullable boolean[] array, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.nullOrEmpty$(array);
	}
	/** Predicate: Array <%s> must NOT be null or empty..*/
	public static <MP1, MP2> boolean notNullNotEmpty(@Nullable boolean[] array, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.notNullNotEmpty(array);
	}

	/** "Special" predicate: Array <%s> must NOT be null or empty. */
	public static <MP1, MP2> @Nullable String notNullNotEmpty$(@Nullable boolean[] array, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.notNullNotEmpty$(array);
	}

	/** Predicate: Array <%s> must be exactly of size 1 (singleton)..*/
	public static <MP1, MP2> boolean singleton(@Nonnull boolean[] array, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(array, "array");
		return Predicates.singleton(array);
	}

	/** "Special" predicate: Array <%s> must be exactly of size 1 (singleton). */
	public static <MP1, MP2> @Nullable String singleton$(@Nonnull boolean[] array, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(array, "array");
		return Predicates.singleton$(array);
	}
	/** Predicate: Array <%s> must NOT be exactly of size 1 (singleton)..*/
	public static <MP1, MP2> boolean notSingleton(@Nonnull boolean[] array, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(array, "array");
		return Predicates.notSingleton(array);
	}

	/** "Special" predicate: Array <%s> must NOT be exactly of size 1 (singleton). */
	public static <MP1, MP2> @Nullable String notSingleton$(@Nonnull boolean[] array, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(array, "array");
		return Predicates.notSingleton$(array);
	}

	/** Predicate: Array <%s> must be equal to array <%s>..*/
	public static <MP1, MP2> boolean equal(boolean[] a1, boolean[] a2, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.equal(a1, a2);
	}

	/** "Special" predicate: Array <%s> must be equal to array <%s>. */
	public static <MP1, MP2> @Nullable String equal$(boolean[] a1, boolean[] a2, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.equal$(a1, a2);
	}
	/** Predicate: Array <%s> must NOT be equal to array <%s>..*/
	public static <MP1, MP2> boolean notEqual(boolean[] a1, boolean[] a2, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.notEqual(a1, a2);
	}

	/** "Special" predicate: Array <%s> must NOT be equal to array <%s>. */
	public static <MP1, MP2> @Nullable String notEqual$(boolean[] a1, boolean[] a2, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.notEqual$(a1, a2);
	}

	/** Predicate: Array <%s> must be of size %s..*/
	public static <MP1, MP2> boolean length(@Nonnull byte[] array, int i, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(array, "array");
		return Predicates.length(array, i);
	}

	/** "Special" predicate: Array <%s> must be of size %s. */
	public static <MP1, MP2> @Nullable String length$(@Nonnull byte[] array, int i, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(array, "array");
		return Predicates.length$(array, i);
	}
	/** Predicate: Array <%s> must NOT be of size %s..*/
	public static <MP1, MP2> boolean lengthOtherThan(@Nonnull byte[] array, int i, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(array, "array");
		return Predicates.lengthOtherThan(array, i);
	}

	/** "Special" predicate: Array <%s> must NOT be of size %s. */
	public static <MP1, MP2> @Nullable String lengthOtherThan$(@Nonnull byte[] array, int i, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(array, "array");
		return Predicates.lengthOtherThan$(array, i);
	}

	/** Predicate: Array <%s> must be of size %s..*/
	public static <MP1, MP2> boolean ofLength(@Nonnull byte[] array, int i, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(array, "array");
		return Predicates.ofLength(array, i);
	}

	/** "Special" predicate: Array <%s> must be of size %s. */
	public static <MP1, MP2> @Nullable String ofLength$(@Nonnull byte[] array, int i, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(array, "array");
		return Predicates.ofLength$(array, i);
	}
	/** Predicate: Array <%s> must NOT be of size %s..*/
	public static <MP1, MP2> boolean notOfLength(@Nonnull byte[] array, int i, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(array, "array");
		return Predicates.notOfLength(array, i);
	}

	/** "Special" predicate: Array <%s> must NOT be of size %s. */
	public static <MP1, MP2> @Nullable String notOfLength$(@Nonnull byte[] array, int i, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(array, "array");
		return Predicates.notOfLength$(array, i);
	}

	/** Predicate: Array <%s> must be empty..*/
	public static <MP1, MP2> boolean empty(@Nonnull byte[] array, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(array, "array");
		return Predicates.empty(array);
	}

	/** "Special" predicate: Array <%s> must be empty. */
	public static <MP1, MP2> @Nullable String empty$(@Nonnull byte[] array, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(array, "array");
		return Predicates.empty$(array);
	}
	/** Predicate: Array <%s> must NOT be empty..*/
	public static <MP1, MP2> boolean notEmpty(@Nonnull byte[] array, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(array, "array");
		return Predicates.notEmpty(array);
	}

	/** "Special" predicate: Array <%s> must NOT be empty. */
	public static <MP1, MP2> @Nullable String notEmpty$(@Nonnull byte[] array, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(array, "array");
		return Predicates.notEmpty$(array);
	}

	/** Predicate: Array <%s> must be null or empty..*/
	public static <MP1, MP2> boolean nullOrEmpty(@Nullable byte[] array, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.nullOrEmpty(array);
	}

	/** "Special" predicate: Array <%s> must be null or empty. */
	public static <MP1, MP2> @Nullable String nullOrEmpty$(@Nullable byte[] array, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.nullOrEmpty$(array);
	}
	/** Predicate: Array <%s> must NOT be null or empty..*/
	public static <MP1, MP2> boolean notNullNotEmpty(@Nullable byte[] array, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.notNullNotEmpty(array);
	}

	/** "Special" predicate: Array <%s> must NOT be null or empty. */
	public static <MP1, MP2> @Nullable String notNullNotEmpty$(@Nullable byte[] array, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.notNullNotEmpty$(array);
	}

	/** Predicate: Array <%s> must be exactly of size 1 (singleton)..*/
	public static <MP1, MP2> boolean singleton(@Nonnull byte[] array, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(array, "array");
		return Predicates.singleton(array);
	}

	/** "Special" predicate: Array <%s> must be exactly of size 1 (singleton). */
	public static <MP1, MP2> @Nullable String singleton$(@Nonnull byte[] array, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(array, "array");
		return Predicates.singleton$(array);
	}
	/** Predicate: Array <%s> must NOT be exactly of size 1 (singleton)..*/
	public static <MP1, MP2> boolean notSingleton(@Nonnull byte[] array, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(array, "array");
		return Predicates.notSingleton(array);
	}

	/** "Special" predicate: Array <%s> must NOT be exactly of size 1 (singleton). */
	public static <MP1, MP2> @Nullable String notSingleton$(@Nonnull byte[] array, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(array, "array");
		return Predicates.notSingleton$(array);
	}

	/** Predicate: Array <%s> must be equal to array <%s>..*/
	public static <MP1, MP2> boolean equal(byte[] a1, byte[] a2, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.equal(a1, a2);
	}

	/** "Special" predicate: Array <%s> must be equal to array <%s>. */
	public static <MP1, MP2> @Nullable String equal$(byte[] a1, byte[] a2, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.equal$(a1, a2);
	}
	/** Predicate: Array <%s> must NOT be equal to array <%s>..*/
	public static <MP1, MP2> boolean notEqual(byte[] a1, byte[] a2, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.notEqual(a1, a2);
	}

	/** "Special" predicate: Array <%s> must NOT be equal to array <%s>. */
	public static <MP1, MP2> @Nullable String notEqual$(byte[] a1, byte[] a2, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.notEqual$(a1, a2);
	}

	/** Predicate: Array <%s> must be of size %s..*/
	public static <MP1, MP2> boolean length(@Nonnull double[] array, int i, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(array, "array");
		return Predicates.length(array, i);
	}

	/** "Special" predicate: Array <%s> must be of size %s. */
	public static <MP1, MP2> @Nullable String length$(@Nonnull double[] array, int i, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(array, "array");
		return Predicates.length$(array, i);
	}
	/** Predicate: Array <%s> must NOT be of size %s..*/
	public static <MP1, MP2> boolean lengthOtherThan(@Nonnull double[] array, int i, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(array, "array");
		return Predicates.lengthOtherThan(array, i);
	}

	/** "Special" predicate: Array <%s> must NOT be of size %s. */
	public static <MP1, MP2> @Nullable String lengthOtherThan$(@Nonnull double[] array, int i, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(array, "array");
		return Predicates.lengthOtherThan$(array, i);
	}

	/** Predicate: Array <%s> must be of size %s..*/
	public static <MP1, MP2> boolean ofLength(@Nonnull double[] array, int i, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(array, "array");
		return Predicates.ofLength(array, i);
	}

	/** "Special" predicate: Array <%s> must be of size %s. */
	public static <MP1, MP2> @Nullable String ofLength$(@Nonnull double[] array, int i, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(array, "array");
		return Predicates.ofLength$(array, i);
	}
	/** Predicate: Array <%s> must NOT be of size %s..*/
	public static <MP1, MP2> boolean notOfLength(@Nonnull double[] array, int i, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(array, "array");
		return Predicates.notOfLength(array, i);
	}

	/** "Special" predicate: Array <%s> must NOT be of size %s. */
	public static <MP1, MP2> @Nullable String notOfLength$(@Nonnull double[] array, int i, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(array, "array");
		return Predicates.notOfLength$(array, i);
	}

	/** Predicate: Array <%s> must be empty..*/
	public static <MP1, MP2> boolean empty(@Nonnull double[] array, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(array, "array");
		return Predicates.empty(array);
	}

	/** "Special" predicate: Array <%s> must be empty. */
	public static <MP1, MP2> @Nullable String empty$(@Nonnull double[] array, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(array, "array");
		return Predicates.empty$(array);
	}
	/** Predicate: Array <%s> must NOT be empty..*/
	public static <MP1, MP2> boolean notEmpty(@Nonnull double[] array, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(array, "array");
		return Predicates.notEmpty(array);
	}

	/** "Special" predicate: Array <%s> must NOT be empty. */
	public static <MP1, MP2> @Nullable String notEmpty$(@Nonnull double[] array, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(array, "array");
		return Predicates.notEmpty$(array);
	}

	/** Predicate: Array <%s> must be null or empty..*/
	public static <MP1, MP2> boolean nullOrEmpty(@Nullable double[] array, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.nullOrEmpty(array);
	}

	/** "Special" predicate: Array <%s> must be null or empty. */
	public static <MP1, MP2> @Nullable String nullOrEmpty$(@Nullable double[] array, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.nullOrEmpty$(array);
	}
	/** Predicate: Array <%s> must NOT be null or empty..*/
	public static <MP1, MP2> boolean notNullNotEmpty(@Nullable double[] array, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.notNullNotEmpty(array);
	}

	/** "Special" predicate: Array <%s> must NOT be null or empty. */
	public static <MP1, MP2> @Nullable String notNullNotEmpty$(@Nullable double[] array, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.notNullNotEmpty$(array);
	}

	/** Predicate: Array <%s> must be exactly of size 1 (singleton)..*/
	public static <MP1, MP2> boolean singleton(@Nonnull double[] array, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(array, "array");
		return Predicates.singleton(array);
	}

	/** "Special" predicate: Array <%s> must be exactly of size 1 (singleton). */
	public static <MP1, MP2> @Nullable String singleton$(@Nonnull double[] array, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(array, "array");
		return Predicates.singleton$(array);
	}
	/** Predicate: Array <%s> must NOT be exactly of size 1 (singleton)..*/
	public static <MP1, MP2> boolean notSingleton(@Nonnull double[] array, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(array, "array");
		return Predicates.notSingleton(array);
	}

	/** "Special" predicate: Array <%s> must NOT be exactly of size 1 (singleton). */
	public static <MP1, MP2> @Nullable String notSingleton$(@Nonnull double[] array, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(array, "array");
		return Predicates.notSingleton$(array);
	}

	/** Predicate: Array <%s> must be equal to array <%s>..*/
	public static <MP1, MP2> boolean equal(double[] a1, double[] a2, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.equal(a1, a2);
	}

	/** "Special" predicate: Array <%s> must be equal to array <%s>. */
	public static <MP1, MP2> @Nullable String equal$(double[] a1, double[] a2, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.equal$(a1, a2);
	}
	/** Predicate: Array <%s> must NOT be equal to array <%s>..*/
	public static <MP1, MP2> boolean notEqual(double[] a1, double[] a2, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.notEqual(a1, a2);
	}

	/** "Special" predicate: Array <%s> must NOT be equal to array <%s>. */
	public static <MP1, MP2> @Nullable String notEqual$(double[] a1, double[] a2, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.notEqual$(a1, a2);
	}

	/** Predicate: Array <%s> must be of size %s..*/
	public static <MP1, MP2> boolean length(@Nonnull char[] array, int i, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(array, "array");
		return Predicates.length(array, i);
	}

	/** "Special" predicate: Array <%s> must be of size %s. */
	public static <MP1, MP2> @Nullable String length$(@Nonnull char[] array, int i, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(array, "array");
		return Predicates.length$(array, i);
	}
	/** Predicate: Array <%s> must NOT be of size %s..*/
	public static <MP1, MP2> boolean lengthOtherThan(@Nonnull char[] array, int i, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(array, "array");
		return Predicates.lengthOtherThan(array, i);
	}

	/** "Special" predicate: Array <%s> must NOT be of size %s. */
	public static <MP1, MP2> @Nullable String lengthOtherThan$(@Nonnull char[] array, int i, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(array, "array");
		return Predicates.lengthOtherThan$(array, i);
	}

	/** Predicate: Array <%s> must be of size %s..*/
	public static <MP1, MP2> boolean ofLength(@Nonnull char[] array, int i, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(array, "array");
		return Predicates.ofLength(array, i);
	}

	/** "Special" predicate: Array <%s> must be of size %s. */
	public static <MP1, MP2> @Nullable String ofLength$(@Nonnull char[] array, int i, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(array, "array");
		return Predicates.ofLength$(array, i);
	}
	/** Predicate: Array <%s> must NOT be of size %s..*/
	public static <MP1, MP2> boolean notOfLength(@Nonnull char[] array, int i, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(array, "array");
		return Predicates.notOfLength(array, i);
	}

	/** "Special" predicate: Array <%s> must NOT be of size %s. */
	public static <MP1, MP2> @Nullable String notOfLength$(@Nonnull char[] array, int i, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(array, "array");
		return Predicates.notOfLength$(array, i);
	}

	/** Predicate: Array <%s> must be empty..*/
	public static <MP1, MP2> boolean empty(@Nonnull char[] array, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(array, "array");
		return Predicates.empty(array);
	}

	/** "Special" predicate: Array <%s> must be empty. */
	public static <MP1, MP2> @Nullable String empty$(@Nonnull char[] array, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(array, "array");
		return Predicates.empty$(array);
	}
	/** Predicate: Array <%s> must NOT be empty..*/
	public static <MP1, MP2> boolean notEmpty(@Nonnull char[] array, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(array, "array");
		return Predicates.notEmpty(array);
	}

	/** "Special" predicate: Array <%s> must NOT be empty. */
	public static <MP1, MP2> @Nullable String notEmpty$(@Nonnull char[] array, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(array, "array");
		return Predicates.notEmpty$(array);
	}

	/** Predicate: Array <%s> must be null or empty..*/
	public static <MP1, MP2> boolean nullOrEmpty(@Nullable char[] array, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.nullOrEmpty(array);
	}

	/** "Special" predicate: Array <%s> must be null or empty. */
	public static <MP1, MP2> @Nullable String nullOrEmpty$(@Nullable char[] array, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.nullOrEmpty$(array);
	}
	/** Predicate: Array <%s> must NOT be null or empty..*/
	public static <MP1, MP2> boolean notNullNotEmpty(@Nullable char[] array, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.notNullNotEmpty(array);
	}

	/** "Special" predicate: Array <%s> must NOT be null or empty. */
	public static <MP1, MP2> @Nullable String notNullNotEmpty$(@Nullable char[] array, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.notNullNotEmpty$(array);
	}

	/** Predicate: Array <%s> must be exactly of size 1 (singleton)..*/
	public static <MP1, MP2> boolean singleton(@Nonnull char[] array, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(array, "array");
		return Predicates.singleton(array);
	}

	/** "Special" predicate: Array <%s> must be exactly of size 1 (singleton). */
	public static <MP1, MP2> @Nullable String singleton$(@Nonnull char[] array, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(array, "array");
		return Predicates.singleton$(array);
	}
	/** Predicate: Array <%s> must NOT be exactly of size 1 (singleton)..*/
	public static <MP1, MP2> boolean notSingleton(@Nonnull char[] array, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(array, "array");
		return Predicates.notSingleton(array);
	}

	/** "Special" predicate: Array <%s> must NOT be exactly of size 1 (singleton). */
	public static <MP1, MP2> @Nullable String notSingleton$(@Nonnull char[] array, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(array, "array");
		return Predicates.notSingleton$(array);
	}

	/** Predicate: Array <%s> must be equal to array <%s>..*/
	public static <MP1, MP2> boolean equal(char[] a1, char[] a2, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.equal(a1, a2);
	}

	/** "Special" predicate: Array <%s> must be equal to array <%s>. */
	public static <MP1, MP2> @Nullable String equal$(char[] a1, char[] a2, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.equal$(a1, a2);
	}
	/** Predicate: Array <%s> must NOT be equal to array <%s>..*/
	public static <MP1, MP2> boolean notEqual(char[] a1, char[] a2, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.notEqual(a1, a2);
	}

	/** "Special" predicate: Array <%s> must NOT be equal to array <%s>. */
	public static <MP1, MP2> @Nullable String notEqual$(char[] a1, char[] a2, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.notEqual$(a1, a2);
	}

	/** Predicate: Array <%s> must be of size %s..*/
	public static <MP1, MP2> boolean length(@Nonnull short[] array, int i, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(array, "array");
		return Predicates.length(array, i);
	}

	/** "Special" predicate: Array <%s> must be of size %s. */
	public static <MP1, MP2> @Nullable String length$(@Nonnull short[] array, int i, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(array, "array");
		return Predicates.length$(array, i);
	}
	/** Predicate: Array <%s> must NOT be of size %s..*/
	public static <MP1, MP2> boolean lengthOtherThan(@Nonnull short[] array, int i, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(array, "array");
		return Predicates.lengthOtherThan(array, i);
	}

	/** "Special" predicate: Array <%s> must NOT be of size %s. */
	public static <MP1, MP2> @Nullable String lengthOtherThan$(@Nonnull short[] array, int i, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(array, "array");
		return Predicates.lengthOtherThan$(array, i);
	}

	/** Predicate: Array <%s> must be of size %s..*/
	public static <MP1, MP2> boolean ofLength(@Nonnull short[] array, int i, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(array, "array");
		return Predicates.ofLength(array, i);
	}

	/** "Special" predicate: Array <%s> must be of size %s. */
	public static <MP1, MP2> @Nullable String ofLength$(@Nonnull short[] array, int i, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(array, "array");
		return Predicates.ofLength$(array, i);
	}
	/** Predicate: Array <%s> must NOT be of size %s..*/
	public static <MP1, MP2> boolean notOfLength(@Nonnull short[] array, int i, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(array, "array");
		return Predicates.notOfLength(array, i);
	}

	/** "Special" predicate: Array <%s> must NOT be of size %s. */
	public static <MP1, MP2> @Nullable String notOfLength$(@Nonnull short[] array, int i, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(array, "array");
		return Predicates.notOfLength$(array, i);
	}

	/** Predicate: Array <%s> must be empty..*/
	public static <MP1, MP2> boolean empty(@Nonnull short[] array, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(array, "array");
		return Predicates.empty(array);
	}

	/** "Special" predicate: Array <%s> must be empty. */
	public static <MP1, MP2> @Nullable String empty$(@Nonnull short[] array, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(array, "array");
		return Predicates.empty$(array);
	}
	/** Predicate: Array <%s> must NOT be empty..*/
	public static <MP1, MP2> boolean notEmpty(@Nonnull short[] array, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(array, "array");
		return Predicates.notEmpty(array);
	}

	/** "Special" predicate: Array <%s> must NOT be empty. */
	public static <MP1, MP2> @Nullable String notEmpty$(@Nonnull short[] array, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(array, "array");
		return Predicates.notEmpty$(array);
	}

	/** Predicate: Array <%s> must be null or empty..*/
	public static <MP1, MP2> boolean nullOrEmpty(@Nullable short[] array, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.nullOrEmpty(array);
	}

	/** "Special" predicate: Array <%s> must be null or empty. */
	public static <MP1, MP2> @Nullable String nullOrEmpty$(@Nullable short[] array, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.nullOrEmpty$(array);
	}
	/** Predicate: Array <%s> must NOT be null or empty..*/
	public static <MP1, MP2> boolean notNullNotEmpty(@Nullable short[] array, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.notNullNotEmpty(array);
	}

	/** "Special" predicate: Array <%s> must NOT be null or empty. */
	public static <MP1, MP2> @Nullable String notNullNotEmpty$(@Nullable short[] array, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.notNullNotEmpty$(array);
	}

	/** Predicate: Array <%s> must be exactly of size 1 (singleton)..*/
	public static <MP1, MP2> boolean singleton(@Nonnull short[] array, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(array, "array");
		return Predicates.singleton(array);
	}

	/** "Special" predicate: Array <%s> must be exactly of size 1 (singleton). */
	public static <MP1, MP2> @Nullable String singleton$(@Nonnull short[] array, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(array, "array");
		return Predicates.singleton$(array);
	}
	/** Predicate: Array <%s> must NOT be exactly of size 1 (singleton)..*/
	public static <MP1, MP2> boolean notSingleton(@Nonnull short[] array, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(array, "array");
		return Predicates.notSingleton(array);
	}

	/** "Special" predicate: Array <%s> must NOT be exactly of size 1 (singleton). */
	public static <MP1, MP2> @Nullable String notSingleton$(@Nonnull short[] array, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(array, "array");
		return Predicates.notSingleton$(array);
	}

	/** Predicate: Array <%s> must be equal to array <%s>..*/
	public static <MP1, MP2> boolean equal(short[] a1, short[] a2, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.equal(a1, a2);
	}

	/** "Special" predicate: Array <%s> must be equal to array <%s>. */
	public static <MP1, MP2> @Nullable String equal$(short[] a1, short[] a2, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.equal$(a1, a2);
	}
	/** Predicate: Array <%s> must NOT be equal to array <%s>..*/
	public static <MP1, MP2> boolean notEqual(short[] a1, short[] a2, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.notEqual(a1, a2);
	}

	/** "Special" predicate: Array <%s> must NOT be equal to array <%s>. */
	public static <MP1, MP2> @Nullable String notEqual$(short[] a1, short[] a2, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.notEqual$(a1, a2);
	}

	/** Predicate: Array <%s> must be of size %s..*/
	public static <MP1, MP2> boolean length(@Nonnull float[] array, int i, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(array, "array");
		return Predicates.length(array, i);
	}

	/** "Special" predicate: Array <%s> must be of size %s. */
	public static <MP1, MP2> @Nullable String length$(@Nonnull float[] array, int i, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(array, "array");
		return Predicates.length$(array, i);
	}
	/** Predicate: Array <%s> must NOT be of size %s..*/
	public static <MP1, MP2> boolean lengthOtherThan(@Nonnull float[] array, int i, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(array, "array");
		return Predicates.lengthOtherThan(array, i);
	}

	/** "Special" predicate: Array <%s> must NOT be of size %s. */
	public static <MP1, MP2> @Nullable String lengthOtherThan$(@Nonnull float[] array, int i, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(array, "array");
		return Predicates.lengthOtherThan$(array, i);
	}

	/** Predicate: Array <%s> must be of size %s..*/
	public static <MP1, MP2> boolean ofLength(@Nonnull float[] array, int i, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(array, "array");
		return Predicates.ofLength(array, i);
	}

	/** "Special" predicate: Array <%s> must be of size %s. */
	public static <MP1, MP2> @Nullable String ofLength$(@Nonnull float[] array, int i, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(array, "array");
		return Predicates.ofLength$(array, i);
	}
	/** Predicate: Array <%s> must NOT be of size %s..*/
	public static <MP1, MP2> boolean notOfLength(@Nonnull float[] array, int i, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(array, "array");
		return Predicates.notOfLength(array, i);
	}

	/** "Special" predicate: Array <%s> must NOT be of size %s. */
	public static <MP1, MP2> @Nullable String notOfLength$(@Nonnull float[] array, int i, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(array, "array");
		return Predicates.notOfLength$(array, i);
	}

	/** Predicate: Array <%s> must be empty..*/
	public static <MP1, MP2> boolean empty(@Nonnull float[] array, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(array, "array");
		return Predicates.empty(array);
	}

	/** "Special" predicate: Array <%s> must be empty. */
	public static <MP1, MP2> @Nullable String empty$(@Nonnull float[] array, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(array, "array");
		return Predicates.empty$(array);
	}
	/** Predicate: Array <%s> must NOT be empty..*/
	public static <MP1, MP2> boolean notEmpty(@Nonnull float[] array, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(array, "array");
		return Predicates.notEmpty(array);
	}

	/** "Special" predicate: Array <%s> must NOT be empty. */
	public static <MP1, MP2> @Nullable String notEmpty$(@Nonnull float[] array, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(array, "array");
		return Predicates.notEmpty$(array);
	}

	/** Predicate: Array <%s> must be null or empty..*/
	public static <MP1, MP2> boolean nullOrEmpty(@Nullable float[] array, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.nullOrEmpty(array);
	}

	/** "Special" predicate: Array <%s> must be null or empty. */
	public static <MP1, MP2> @Nullable String nullOrEmpty$(@Nullable float[] array, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.nullOrEmpty$(array);
	}
	/** Predicate: Array <%s> must NOT be null or empty..*/
	public static <MP1, MP2> boolean notNullNotEmpty(@Nullable float[] array, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.notNullNotEmpty(array);
	}

	/** "Special" predicate: Array <%s> must NOT be null or empty. */
	public static <MP1, MP2> @Nullable String notNullNotEmpty$(@Nullable float[] array, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.notNullNotEmpty$(array);
	}

	/** Predicate: Array <%s> must be exactly of size 1 (singleton)..*/
	public static <MP1, MP2> boolean singleton(@Nonnull float[] array, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(array, "array");
		return Predicates.singleton(array);
	}

	/** "Special" predicate: Array <%s> must be exactly of size 1 (singleton). */
	public static <MP1, MP2> @Nullable String singleton$(@Nonnull float[] array, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(array, "array");
		return Predicates.singleton$(array);
	}
	/** Predicate: Array <%s> must NOT be exactly of size 1 (singleton)..*/
	public static <MP1, MP2> boolean notSingleton(@Nonnull float[] array, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(array, "array");
		return Predicates.notSingleton(array);
	}

	/** "Special" predicate: Array <%s> must NOT be exactly of size 1 (singleton). */
	public static <MP1, MP2> @Nullable String notSingleton$(@Nonnull float[] array, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(array, "array");
		return Predicates.notSingleton$(array);
	}

	/** Predicate: Array <%s> must be equal to array <%s>..*/
	public static <MP1, MP2> boolean equal(float[] a1, float[] a2, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.equal(a1, a2);
	}

	/** "Special" predicate: Array <%s> must be equal to array <%s>. */
	public static <MP1, MP2> @Nullable String equal$(float[] a1, float[] a2, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.equal$(a1, a2);
	}
	/** Predicate: Array <%s> must NOT be equal to array <%s>..*/
	public static <MP1, MP2> boolean notEqual(float[] a1, float[] a2, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.notEqual(a1, a2);
	}

	/** "Special" predicate: Array <%s> must NOT be equal to array <%s>. */
	public static <MP1, MP2> @Nullable String notEqual$(float[] a1, float[] a2, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.notEqual$(a1, a2);
	}

	/** Predicate: Array <%s> must be of size %s..*/
	public static <MP1, MP2> boolean length(@Nonnull int[] array, int i, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(array, "array");
		return Predicates.length(array, i);
	}

	/** "Special" predicate: Array <%s> must be of size %s. */
	public static <MP1, MP2> @Nullable String length$(@Nonnull int[] array, int i, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(array, "array");
		return Predicates.length$(array, i);
	}
	/** Predicate: Array <%s> must NOT be of size %s..*/
	public static <MP1, MP2> boolean lengthOtherThan(@Nonnull int[] array, int i, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(array, "array");
		return Predicates.lengthOtherThan(array, i);
	}

	/** "Special" predicate: Array <%s> must NOT be of size %s. */
	public static <MP1, MP2> @Nullable String lengthOtherThan$(@Nonnull int[] array, int i, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(array, "array");
		return Predicates.lengthOtherThan$(array, i);
	}

	/** Predicate: Array <%s> must be of size %s..*/
	public static <MP1, MP2> boolean ofLength(@Nonnull int[] array, int i, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(array, "array");
		return Predicates.ofLength(array, i);
	}

	/** "Special" predicate: Array <%s> must be of size %s. */
	public static <MP1, MP2> @Nullable String ofLength$(@Nonnull int[] array, int i, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(array, "array");
		return Predicates.ofLength$(array, i);
	}
	/** Predicate: Array <%s> must NOT be of size %s..*/
	public static <MP1, MP2> boolean notOfLength(@Nonnull int[] array, int i, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(array, "array");
		return Predicates.notOfLength(array, i);
	}

	/** "Special" predicate: Array <%s> must NOT be of size %s. */
	public static <MP1, MP2> @Nullable String notOfLength$(@Nonnull int[] array, int i, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(array, "array");
		return Predicates.notOfLength$(array, i);
	}

	/** Predicate: Array <%s> must be empty..*/
	public static <MP1, MP2> boolean empty(@Nonnull int[] array, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(array, "array");
		return Predicates.empty(array);
	}

	/** "Special" predicate: Array <%s> must be empty. */
	public static <MP1, MP2> @Nullable String empty$(@Nonnull int[] array, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(array, "array");
		return Predicates.empty$(array);
	}
	/** Predicate: Array <%s> must NOT be empty..*/
	public static <MP1, MP2> boolean notEmpty(@Nonnull int[] array, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(array, "array");
		return Predicates.notEmpty(array);
	}

	/** "Special" predicate: Array <%s> must NOT be empty. */
	public static <MP1, MP2> @Nullable String notEmpty$(@Nonnull int[] array, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(array, "array");
		return Predicates.notEmpty$(array);
	}

	/** Predicate: Array <%s> must be null or empty..*/
	public static <MP1, MP2> boolean nullOrEmpty(@Nullable int[] array, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.nullOrEmpty(array);
	}

	/** "Special" predicate: Array <%s> must be null or empty. */
	public static <MP1, MP2> @Nullable String nullOrEmpty$(@Nullable int[] array, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.nullOrEmpty$(array);
	}
	/** Predicate: Array <%s> must NOT be null or empty..*/
	public static <MP1, MP2> boolean notNullNotEmpty(@Nullable int[] array, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.notNullNotEmpty(array);
	}

	/** "Special" predicate: Array <%s> must NOT be null or empty. */
	public static <MP1, MP2> @Nullable String notNullNotEmpty$(@Nullable int[] array, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.notNullNotEmpty$(array);
	}

	/** Predicate: Array <%s> must be exactly of size 1 (singleton)..*/
	public static <MP1, MP2> boolean singleton(@Nonnull int[] array, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(array, "array");
		return Predicates.singleton(array);
	}

	/** "Special" predicate: Array <%s> must be exactly of size 1 (singleton). */
	public static <MP1, MP2> @Nullable String singleton$(@Nonnull int[] array, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(array, "array");
		return Predicates.singleton$(array);
	}
	/** Predicate: Array <%s> must NOT be exactly of size 1 (singleton)..*/
	public static <MP1, MP2> boolean notSingleton(@Nonnull int[] array, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(array, "array");
		return Predicates.notSingleton(array);
	}

	/** "Special" predicate: Array <%s> must NOT be exactly of size 1 (singleton). */
	public static <MP1, MP2> @Nullable String notSingleton$(@Nonnull int[] array, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(array, "array");
		return Predicates.notSingleton$(array);
	}

	/** Predicate: Array <%s> must be equal to array <%s>..*/
	public static <MP1, MP2> boolean equal(int[] a1, int[] a2, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.equal(a1, a2);
	}

	/** "Special" predicate: Array <%s> must be equal to array <%s>. */
	public static <MP1, MP2> @Nullable String equal$(int[] a1, int[] a2, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.equal$(a1, a2);
	}
	/** Predicate: Array <%s> must NOT be equal to array <%s>..*/
	public static <MP1, MP2> boolean notEqual(int[] a1, int[] a2, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.notEqual(a1, a2);
	}

	/** "Special" predicate: Array <%s> must NOT be equal to array <%s>. */
	public static <MP1, MP2> @Nullable String notEqual$(int[] a1, int[] a2, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.notEqual$(a1, a2);
	}

	/** Predicate: Array <%s> must be of size %s..*/
	public static <MP1, MP2> boolean length(@Nonnull long[] array, int i, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(array, "array");
		return Predicates.length(array, i);
	}

	/** "Special" predicate: Array <%s> must be of size %s. */
	public static <MP1, MP2> @Nullable String length$(@Nonnull long[] array, int i, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(array, "array");
		return Predicates.length$(array, i);
	}
	/** Predicate: Array <%s> must NOT be of size %s..*/
	public static <MP1, MP2> boolean lengthOtherThan(@Nonnull long[] array, int i, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(array, "array");
		return Predicates.lengthOtherThan(array, i);
	}

	/** "Special" predicate: Array <%s> must NOT be of size %s. */
	public static <MP1, MP2> @Nullable String lengthOtherThan$(@Nonnull long[] array, int i, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(array, "array");
		return Predicates.lengthOtherThan$(array, i);
	}

	/** Predicate: Array <%s> must be of size %s..*/
	public static <MP1, MP2> boolean ofLength(@Nonnull long[] array, int i, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(array, "array");
		return Predicates.ofLength(array, i);
	}

	/** "Special" predicate: Array <%s> must be of size %s. */
	public static <MP1, MP2> @Nullable String ofLength$(@Nonnull long[] array, int i, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(array, "array");
		return Predicates.ofLength$(array, i);
	}
	/** Predicate: Array <%s> must NOT be of size %s..*/
	public static <MP1, MP2> boolean notOfLength(@Nonnull long[] array, int i, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(array, "array");
		return Predicates.notOfLength(array, i);
	}

	/** "Special" predicate: Array <%s> must NOT be of size %s. */
	public static <MP1, MP2> @Nullable String notOfLength$(@Nonnull long[] array, int i, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(array, "array");
		return Predicates.notOfLength$(array, i);
	}

	/** Predicate: Array <%s> must be empty..*/
	public static <MP1, MP2> boolean empty(@Nonnull long[] array, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(array, "array");
		return Predicates.empty(array);
	}

	/** "Special" predicate: Array <%s> must be empty. */
	public static <MP1, MP2> @Nullable String empty$(@Nonnull long[] array, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(array, "array");
		return Predicates.empty$(array);
	}
	/** Predicate: Array <%s> must NOT be empty..*/
	public static <MP1, MP2> boolean notEmpty(@Nonnull long[] array, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(array, "array");
		return Predicates.notEmpty(array);
	}

	/** "Special" predicate: Array <%s> must NOT be empty. */
	public static <MP1, MP2> @Nullable String notEmpty$(@Nonnull long[] array, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(array, "array");
		return Predicates.notEmpty$(array);
	}

	/** Predicate: Array <%s> must be null or empty..*/
	public static <MP1, MP2> boolean nullOrEmpty(@Nullable long[] array, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.nullOrEmpty(array);
	}

	/** "Special" predicate: Array <%s> must be null or empty. */
	public static <MP1, MP2> @Nullable String nullOrEmpty$(@Nullable long[] array, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.nullOrEmpty$(array);
	}
	/** Predicate: Array <%s> must NOT be null or empty..*/
	public static <MP1, MP2> boolean notNullNotEmpty(@Nullable long[] array, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.notNullNotEmpty(array);
	}

	/** "Special" predicate: Array <%s> must NOT be null or empty. */
	public static <MP1, MP2> @Nullable String notNullNotEmpty$(@Nullable long[] array, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.notNullNotEmpty$(array);
	}

	/** Predicate: Array <%s> must be exactly of size 1 (singleton)..*/
	public static <MP1, MP2> boolean singleton(@Nonnull long[] array, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(array, "array");
		return Predicates.singleton(array);
	}

	/** "Special" predicate: Array <%s> must be exactly of size 1 (singleton). */
	public static <MP1, MP2> @Nullable String singleton$(@Nonnull long[] array, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(array, "array");
		return Predicates.singleton$(array);
	}
	/** Predicate: Array <%s> must NOT be exactly of size 1 (singleton)..*/
	public static <MP1, MP2> boolean notSingleton(@Nonnull long[] array, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(array, "array");
		return Predicates.notSingleton(array);
	}

	/** "Special" predicate: Array <%s> must NOT be exactly of size 1 (singleton). */
	public static <MP1, MP2> @Nullable String notSingleton$(@Nonnull long[] array, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(array, "array");
		return Predicates.notSingleton$(array);
	}

	/** Predicate: Array <%s> must be equal to array <%s>..*/
	public static <MP1, MP2> boolean equal(long[] a1, long[] a2, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.equal(a1, a2);
	}

	/** "Special" predicate: Array <%s> must be equal to array <%s>. */
	public static <MP1, MP2> @Nullable String equal$(long[] a1, long[] a2, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.equal$(a1, a2);
	}
	/** Predicate: Array <%s> must NOT be equal to array <%s>..*/
	public static <MP1, MP2> boolean notEqual(long[] a1, long[] a2, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.notEqual(a1, a2);
	}

	/** "Special" predicate: Array <%s> must NOT be equal to array <%s>. */
	public static <MP1, MP2> @Nullable String notEqual$(long[] a1, long[] a2, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.notEqual$(a1, a2);
	}

	/** Predicate: Array <%s> must be of size %s..*/
	public static <T, MP1, MP2> boolean length(@Nonnull T[] array, int i, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(array, "array");
		return Predicates.length(array, i);
	}

	/** "Special" predicate: Array <%s> must be of size %s. */
	public static <T, MP1, MP2> @Nullable String length$(@Nonnull T[] array, int i, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(array, "array");
		return Predicates.length$(array, i);
	}
	/** Predicate: Array <%s> must NOT be of size %s..*/
	public static <T, MP1, MP2> boolean lengthOtherThan(@Nonnull T[] array, int i, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(array, "array");
		return Predicates.lengthOtherThan(array, i);
	}

	/** "Special" predicate: Array <%s> must NOT be of size %s. */
	public static <T, MP1, MP2> @Nullable String lengthOtherThan$(@Nonnull T[] array, int i, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(array, "array");
		return Predicates.lengthOtherThan$(array, i);
	}

	/** Predicate: Array <%s> must be of size %s..*/
	public static <T, MP1, MP2> boolean ofLength(@Nonnull T[] array, int i, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(array, "array");
		return Predicates.ofLength(array, i);
	}

	/** "Special" predicate: Array <%s> must be of size %s. */
	public static <T, MP1, MP2> @Nullable String ofLength$(@Nonnull T[] array, int i, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(array, "array");
		return Predicates.ofLength$(array, i);
	}
	/** Predicate: Array <%s> must NOT be of size %s..*/
	public static <T, MP1, MP2> boolean notOfLength(@Nonnull T[] array, int i, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(array, "array");
		return Predicates.notOfLength(array, i);
	}

	/** "Special" predicate: Array <%s> must NOT be of size %s. */
	public static <T, MP1, MP2> @Nullable String notOfLength$(@Nonnull T[] array, int i, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(array, "array");
		return Predicates.notOfLength$(array, i);
	}

	/** Predicate: Array <%s> must be empty..*/
	public static <T, MP1, MP2> boolean empty(@Nonnull T[] array, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(array, "array");
		return Predicates.empty(array);
	}

	/** "Special" predicate: Array <%s> must be empty. */
	public static <T, MP1, MP2> @Nullable String empty$(@Nonnull T[] array, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(array, "array");
		return Predicates.empty$(array);
	}
	/** Predicate: Array <%s> must NOT be empty..*/
	public static <T, MP1, MP2> boolean notEmpty(@Nonnull T[] array, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(array, "array");
		return Predicates.notEmpty(array);
	}

	/** "Special" predicate: Array <%s> must NOT be empty. */
	public static <T, MP1, MP2> @Nullable String notEmpty$(@Nonnull T[] array, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(array, "array");
		return Predicates.notEmpty$(array);
	}

	/** Predicate: Array <%s> must be null or empty..*/
	public static <T, MP1, MP2> boolean nullOrEmpty(@Nullable T[] array, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.nullOrEmpty(array);
	}

	/** "Special" predicate: Array <%s> must be null or empty. */
	public static <T, MP1, MP2> @Nullable String nullOrEmpty$(@Nullable T[] array, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.nullOrEmpty$(array);
	}
	/** Predicate: Array <%s> must NOT be null or empty..*/
	public static <T, MP1, MP2> boolean notNullNotEmpty(@Nullable T[] array, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.notNullNotEmpty(array);
	}

	/** "Special" predicate: Array <%s> must NOT be null or empty. */
	public static <T, MP1, MP2> @Nullable String notNullNotEmpty$(@Nullable T[] array, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.notNullNotEmpty$(array);
	}

	/** Predicate: Array <%s> must be exactly of size 1 (singleton)..*/
	public static <T, MP1, MP2> boolean singleton(@Nonnull T[] array, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(array, "array");
		return Predicates.singleton(array);
	}

	/** "Special" predicate: Array <%s> must be exactly of size 1 (singleton). */
	public static <T, MP1, MP2> @Nullable String singleton$(@Nonnull T[] array, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(array, "array");
		return Predicates.singleton$(array);
	}
	/** Predicate: Array <%s> must NOT be exactly of size 1 (singleton)..*/
	public static <T, MP1, MP2> boolean notSingleton(@Nonnull T[] array, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(array, "array");
		return Predicates.notSingleton(array);
	}

	/** "Special" predicate: Array <%s> must NOT be exactly of size 1 (singleton). */
	public static <T, MP1, MP2> @Nullable String notSingleton$(@Nonnull T[] array, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(array, "array");
		return Predicates.notSingleton$(array);
	}

	/** Predicate: Array <%s> must be equal to array <%s>..*/
	public static <T, MP1, MP2> boolean equal(T[] a1, T[] a2, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.equal(a1, a2);
	}

	/** "Special" predicate: Array <%s> must be equal to array <%s>. */
	public static <T, MP1, MP2> @Nullable String equal$(T[] a1, T[] a2, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.equal$(a1, a2);
	}
	/** Predicate: Array <%s> must NOT be equal to array <%s>..*/
	public static <T, MP1, MP2> boolean notEqual(T[] a1, T[] a2, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.notEqual(a1, a2);
	}

	/** "Special" predicate: Array <%s> must NOT be equal to array <%s>. */
	public static <T, MP1, MP2> @Nullable String notEqual$(T[] a1, T[] a2, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.notEqual$(a1, a2);
	}

	// </editor-fold>

	// <editor-fold desc="collections">

	/** Predicate: Collection <%s> must contain element <%s>..*/
	public static <T, MP1, MP2> boolean contain(@Nonnull Collection<? extends T> collection, T element, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(collection, "collection");
		return Predicates.contain(collection, element);
	}

	/** "Special" predicate: Collection <%s> must contain element <%s>. */
	public static <T, MP1, MP2> @Nullable String contain$(@Nonnull Collection<? extends T> collection, T element, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(collection, "collection");
		return Predicates.contain$(collection, element);
	}
	/** Predicate: Collection <%s> must NOT contain element <%s>..*/
	public static <T, MP1, MP2> boolean notContain(@Nonnull Collection<? extends T> collection, T element, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(collection, "collection");
		return Predicates.notContain(collection, element);
	}

	/** "Special" predicate: Collection <%s> must NOT contain element <%s>. */
	public static <T, MP1, MP2> @Nullable String notContain$(@Nonnull Collection<? extends T> collection, T element, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(collection, "collection");
		return Predicates.notContain$(collection, element);
	}

	/** Predicate: Map <%s> must contain key <%s>..*/
	public static <K, MP1, MP2> boolean containKey(@Nonnull Map<? extends K, ?> map, K key, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(map, "map");
		return Predicates.containKey(map, key);
	}

	/** "Special" predicate: Map <%s> must contain key <%s>. */
	public static <K, MP1, MP2> @Nullable String containKey$(@Nonnull Map<? extends K, ?> map, K key, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(map, "map");
		return Predicates.containKey$(map, key);
	}
	/** Predicate: Map <%s> must NOT contain key <%s>..*/
	public static <K, MP1, MP2> boolean notContainKey(@Nonnull Map<? extends K, ?> map, K key, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(map, "map");
		return Predicates.notContainKey(map, key);
	}

	/** "Special" predicate: Map <%s> must NOT contain key <%s>. */
	public static <K, MP1, MP2> @Nullable String notContainKey$(@Nonnull Map<? extends K, ?> map, K key, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(map, "map");
		return Predicates.notContainKey$(map, key);
	}

	/** Predicate: Map <%s> must contain entry with key <%s> and value <%s>..*/
	public static <K, V, MP1, MP2> boolean containEntry(@Nonnull Map<? extends K, ? extends V> map, K key, V value, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(map, "map");
		return Predicates.containEntry(map, key, value);
	}

	/** "Special" predicate: Map <%s> must contain entry with key <%s> and value <%s>. */
	public static <K, V, MP1, MP2> @Nullable String containEntry$(@Nonnull Map<? extends K, ? extends V> map, K key, V value, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(map, "map");
		return Predicates.containEntry$(map, key, value);
	}
	/** Predicate: Map <%s> must NOT contain entry with key <%s> and value <%s>..*/
	public static <K, V, MP1, MP2> boolean notContainEntry(@Nonnull Map<? extends K, ? extends V> map, K key, V value, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(map, "map");
		return Predicates.notContainEntry(map, key, value);
	}

	/** "Special" predicate: Map <%s> must NOT contain entry with key <%s> and value <%s>. */
	public static <K, V, MP1, MP2> @Nullable String notContainEntry$(@Nonnull Map<? extends K, ? extends V> map, K key, V value, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(map, "map");
		return Predicates.notContainEntry$(map, key, value);
	}

	/** Predicate: Collection <%s> must be of size %s..*/
	public static <T, MP1, MP2> boolean size(@Nonnull Collection<? extends T> collection, int i, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(collection, "collection");
		return Predicates.size(collection, i);
	}

	/** "Special" predicate: Collection <%s> must be of size %s. */
	public static <T, MP1, MP2> @Nullable String size$(@Nonnull Collection<? extends T> collection, int i, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(collection, "collection");
		return Predicates.size$(collection, i);
	}
	/** Predicate: Collection <%s> must NOT be of size %s..*/
	public static <T, MP1, MP2> boolean sizeOtherThan(@Nonnull Collection<? extends T> collection, int i, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(collection, "collection");
		return Predicates.sizeOtherThan(collection, i);
	}

	/** "Special" predicate: Collection <%s> must NOT be of size %s. */
	public static <T, MP1, MP2> @Nullable String sizeOtherThan$(@Nonnull Collection<? extends T> collection, int i, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(collection, "collection");
		return Predicates.sizeOtherThan$(collection, i);
	}
	/** Predicate: Collection <%s> must be of size %s..*/
	public static <T, MP1, MP2> boolean ofSize(@Nonnull Collection<? extends T> collection, int i, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(collection, "collection");
		return Predicates.ofSize(collection, i);
	}

	/** "Special" predicate: Collection <%s> must be of size %s. */
	public static <T, MP1, MP2> @Nullable String ofSize$(@Nonnull Collection<? extends T> collection, int i, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(collection, "collection");
		return Predicates.ofSize$(collection, i);
	}
	/** Predicate: Collection <%s> must NOT be of size %s..*/
	public static <T, MP1, MP2> boolean notOfSize(@Nonnull Collection<? extends T> collection, int i, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(collection, "collection");
		return Predicates.notOfSize(collection, i);
	}

	/** "Special" predicate: Collection <%s> must NOT be of size %s. */
	public static <T, MP1, MP2> @Nullable String notOfSize$(@Nonnull Collection<? extends T> collection, int i, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(collection, "collection");
		return Predicates.notOfSize$(collection, i);
	}

	/** Predicate: Map <%s> must be of size %s..*/
	public static <K, V, MP1, MP2> boolean size(@Nonnull Map<? extends K, ? extends V> map, int i, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(map, "map");
		return Predicates.size(map, i);
	}

	/** "Special" predicate: Map <%s> must be of size %s. */
	public static <K, V, MP1, MP2> @Nullable String size$(@Nonnull Map<? extends K, ? extends V> map, int i, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(map, "map");
		return Predicates.size$(map, i);
	}
	/** Predicate: Map <%s> must NOT be of size %s..*/
	public static <K, V, MP1, MP2> boolean sizeOtherThan(@Nonnull Map<? extends K, ? extends V> map, int i, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(map, "map");
		return Predicates.sizeOtherThan(map, i);
	}

	/** "Special" predicate: Map <%s> must NOT be of size %s. */
	public static <K, V, MP1, MP2> @Nullable String sizeOtherThan$(@Nonnull Map<? extends K, ? extends V> map, int i, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(map, "map");
		return Predicates.sizeOtherThan$(map, i);
	}
	/** Predicate: Map <%s> must be of size %s..*/
	public static <K, V, MP1, MP2> boolean ofSize(@Nonnull Map<? extends K, ? extends V> map, int i, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(map, "map");
		return Predicates.ofSize(map, i);
	}

	/** "Special" predicate: Map <%s> must be of size %s. */
	public static <K, V, MP1, MP2> @Nullable String ofSize$(@Nonnull Map<? extends K, ? extends V> map, int i, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(map, "map");
		return Predicates.ofSize$(map, i);
	}
	/** Predicate: Map <%s> must NOT be of size %s..*/
	public static <K, V, MP1, MP2> boolean notOfSize(@Nonnull Map<? extends K, ? extends V> map, int i, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(map, "map");
		return Predicates.notOfSize(map, i);
	}

	/** "Special" predicate: Map <%s> must NOT be of size %s. */
	public static <K, V, MP1, MP2> @Nullable String notOfSize$(@Nonnull Map<? extends K, ? extends V> map, int i, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(map, "map");
		return Predicates.notOfSize$(map, i);
	}

	/** Predicate: <%s> must be part of <%s> collection..*/
	public static <T, MP1, MP2> boolean partOf(T element, @Nonnull Collection<? extends T> collection, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(collection, "collection");
		return Predicates.partOf(element, collection);
	}

	/** "Special" predicate: <%s> must be part of <%s> collection. */
	public static <T, MP1, MP2> @Nullable String partOf$(T element, @Nonnull Collection<? extends T> collection, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(collection, "collection");
		return Predicates.partOf$(element, collection);
	}
	/** Predicate: <%s> must NOT be part of <%s> collection..*/
	public static <T, MP1, MP2> boolean notPartOf(T element, @Nonnull Collection<? extends T> collection, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(collection, "collection");
		return Predicates.notPartOf(element, collection);
	}

	/** "Special" predicate: <%s> must NOT be part of <%s> collection. */
	public static <T, MP1, MP2> @Nullable String notPartOf$(T element, @Nonnull Collection<? extends T> collection, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(collection, "collection");
		return Predicates.notPartOf$(element, collection);
	}

	/** Predicate: <%s> must be key in <%s> map..*/
	public static <K, MP1, MP2> boolean aKeyIn(K key, @Nonnull Map<? extends K, ?> map, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(map, "map");
		return Predicates.aKeyIn(key, map);
	}

	/** "Special" predicate: <%s> must be key in <%s> map. */
	public static <K, MP1, MP2> @Nullable String aKeyIn$(K key, @Nonnull Map<? extends K, ?> map, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(map, "map");
		return Predicates.aKeyIn$(key, map);
	}
	/** Predicate: <%s> must NOT be key in <%s> map..*/
	public static <K, MP1, MP2> boolean notAKeyIn(K key, @Nonnull Map<? extends K, ?> map, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(map, "map");
		return Predicates.notAKeyIn(key, map);
	}

	/** "Special" predicate: <%s> must NOT be key in <%s> map. */
	public static <K, MP1, MP2> @Nullable String notAKeyIn$(K key, @Nonnull Map<? extends K, ?> map, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(map, "map");
		return Predicates.notAKeyIn$(key, map);
	}

	/** Predicate: Collection <%s> must be empty..*/
	public static <T, MP1, MP2> boolean empty(@Nonnull Collection<? extends T> collection, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(collection, "collection");
		return Predicates.empty(collection);
	}

	/** "Special" predicate: Collection <%s> must be empty. */
	public static <T, MP1, MP2> @Nullable String empty$(@Nonnull Collection<? extends T> collection, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(collection, "collection");
		return Predicates.empty$(collection);
	}
	/** Predicate: Collection <%s> must NOT be empty..*/
	public static <T, MP1, MP2> boolean notEmpty(@Nonnull Collection<? extends T> collection, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(collection, "collection");
		return Predicates.notEmpty(collection);
	}

	/** "Special" predicate: Collection <%s> must NOT be empty. */
	public static <T, MP1, MP2> @Nullable String notEmpty$(@Nonnull Collection<? extends T> collection, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(collection, "collection");
		return Predicates.notEmpty$(collection);
	}

	/** Predicate: Collection <%s> must be empty..*/
	public static <T, MP1, MP2> boolean nullOrEmpty(@Nullable Collection<? extends T> collection, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.nullOrEmpty(collection);
	}

	/** "Special" predicate: Collection <%s> must be empty. */
	public static <T, MP1, MP2> @Nullable String nullOrEmpty$(@Nullable Collection<? extends T> collection, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.nullOrEmpty$(collection);
	}
	/** Predicate: Collection <%s> must NOT be empty..*/
	public static <T, MP1, MP2> boolean notNullNorEmpty(@Nullable Collection<? extends T> collection, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.notNullNorEmpty(collection);
	}

	/** "Special" predicate: Collection <%s> must NOT be empty. */
	public static <T, MP1, MP2> @Nullable String notNullNorEmpty$(@Nullable Collection<? extends T> collection, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.notNullNorEmpty$(collection);
	}

	/** Predicate: Collection <%s> must be exactly of size 1 (singleton)...*/
	public static <T, MP1, MP2> boolean singleton(@Nonnull Collection<? extends T> collection, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(collection, "collection");
		return Predicates.singleton(collection);
	}

	/** "Special" predicate: Collection <%s> must be exactly of size 1 (singleton).. */
	public static <T, MP1, MP2> @Nullable String singleton$(@Nonnull Collection<? extends T> collection, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(collection, "collection");
		return Predicates.singleton$(collection);
	}
	/** Predicate: Collection <%s> must NOT be exactly of size 1 (singleton)...*/
	public static <T, MP1, MP2> boolean notSingleton(@Nonnull Collection<? extends T> collection, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(collection, "collection");
		return Predicates.notSingleton(collection);
	}

	/** "Special" predicate: Collection <%s> must NOT be exactly of size 1 (singleton).. */
	public static <T, MP1, MP2> @Nullable String notSingleton$(@Nonnull Collection<? extends T> collection, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(collection, "collection");
		return Predicates.notSingleton$(collection);
	}

	/** Predicate: Map <%s> must be empty..*/
	public static <MP1, MP2> boolean empty(@Nonnull Map<?, ?> map, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(map, "map");
		return Predicates.empty(map);
	}

	/** "Special" predicate: Map <%s> must be empty. */
	public static <MP1, MP2> @Nullable String empty$(@Nonnull Map<?, ?> map, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(map, "map");
		return Predicates.empty$(map);
	}
	/** Predicate: Map <%s> must NOT be empty..*/
	public static <MP1, MP2> boolean notEmpty(@Nonnull Map<?, ?> map, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(map, "map");
		return Predicates.notEmpty(map);
	}

	/** "Special" predicate: Map <%s> must NOT be empty. */
	public static <MP1, MP2> @Nullable String notEmpty$(@Nonnull Map<?, ?> map, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(map, "map");
		return Predicates.notEmpty$(map);
	}

	/** Predicate: Collection <%s> must be empty..*/
	public static <MP1, MP2> boolean nullOrEmpty(@Nullable Map<?, ?> map, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.nullOrEmpty(map);
	}

	/** "Special" predicate: Collection <%s> must be empty. */
	public static <MP1, MP2> @Nullable String nullOrEmpty$(@Nullable Map<?, ?> map, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.nullOrEmpty$(map);
	}
	/** Predicate: Collection <%s> must NOT be empty..*/
	public static <MP1, MP2> boolean notNullNorEmpty(@Nullable Map<?, ?> map, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.notNullNorEmpty(map);
	}

	/** "Special" predicate: Collection <%s> must NOT be empty. */
	public static <MP1, MP2> @Nullable String notNullNorEmpty$(@Nullable Map<?, ?> map, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return Predicates.notNullNorEmpty$(map);
	}

	/** Predicate: Collection <%s> must be exactly of size 1 (singleton)...*/
	public static <MP1, MP2> boolean singleton(@Nonnull Map<?, ?> map, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(map, "map");
		return Predicates.singleton(map);
	}

	/** "Special" predicate: Collection <%s> must be exactly of size 1 (singleton).. */
	public static <MP1, MP2> @Nullable String singleton$(@Nonnull Map<?, ?> map, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(map, "map");
		return Predicates.singleton$(map);
	}
	/** Predicate: Collection <%s> must NOT be exactly of size 1 (singleton)...*/
	public static <MP1, MP2> boolean notSingleton(@Nonnull Map<?, ?> map, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(map, "map");
		return Predicates.notSingleton(map);
	}

	/** "Special" predicate: Collection <%s> must NOT be exactly of size 1 (singleton).. */
	public static <MP1, MP2> @Nullable String notSingleton$(@Nonnull Map<?, ?> map, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(map, "map");
		return Predicates.notSingleton$(map);
	}

	// </editor-fold>

	// <editor-fold desc="object derivatives">

	/** Predicate: Object <%s> of class <%s> must be instance of <%s>..*/
	public static <MP1, MP2> boolean instanceOf(Object object, Class<?> clazz, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(clazz, "clazz");
		return Predicates.instanceOf(object, clazz);
	}

	/** "Special" predicate: Object <%s> of class <%s> must be instance of <%s>. */
	public static <MP1, MP2> @Nullable String instanceOf$(Object object, Class<?> clazz, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(clazz, "clazz");
		return Predicates.instanceOf$(object, clazz);
	}
	/** Predicate: Object <%s> of class <%s> must NOT be instance of <%s>..*/
	public static <MP1, MP2> boolean notInstanceOf(Object object, Class<?> clazz, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(clazz, "clazz");
		return Predicates.notInstanceOf(object, clazz);
	}

	/** "Special" predicate: Object <%s> of class <%s> must NOT be instance of <%s>. */
	public static <MP1, MP2> @Nullable String notInstanceOf$(Object object, Class<?> clazz, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(clazz, "clazz");
		return Predicates.notInstanceOf$(object, clazz);
	}

	/** Predicate: Object <%s> of class <%s> must be exactly instance of <%s>..*/
	public static <MP1, MP2> boolean exactlyInstanceOf(Object object, Class<?> clazz, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(clazz, "clazz");
		return Predicates.exactlyInstanceOf(object, clazz);
	}

	/** "Special" predicate: Object <%s> of class <%s> must be exactly instance of <%s>. */
	public static <MP1, MP2> @Nullable String exactlyInstanceOf$(Object object, Class<?> clazz, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(clazz, "clazz");
		return Predicates.exactlyInstanceOf$(object, clazz);
	}
	/** Predicate: Object <%s> of class <%s> must NOT be exactly instance of <%s>..*/
	public static <MP1, MP2> boolean notExactlyInstanceOf(Object object, Class<?> clazz, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(clazz, "clazz");
		return Predicates.notExactlyInstanceOf(object, clazz);
	}

	/** "Special" predicate: Object <%s> of class <%s> must NOT be exactly instance of <%s>. */
	public static <MP1, MP2> @Nullable String notExactlyInstanceOf$(Object object, Class<?> clazz, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(clazz, "clazz");
		return Predicates.notExactlyInstanceOf$(object, clazz);
	}

	/** Predicate: Class <%s> of class <%s> must ---NOT-- be specialization of <%s>..*/
	public static <MP1, MP2> boolean assignableFrom(Class<?> specialization, Class<?> clazz, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(clazz, "clazz");
		return Predicates.assignableFrom(specialization, clazz);
	}

	/** "Special" predicate: Class <%s> of class <%s> must ---NOT-- be specialization of <%s>. */
	public static <MP1, MP2> @Nullable String assignableFrom$(Class<?> specialization, Class<?> clazz, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(clazz, "clazz");
		return Predicates.assignableFrom$(specialization, clazz);
	}
	/** Predicate: Class <%s> of class <%s> must ---NOT-- be specialization of <%s>..*/
	public static <MP1, MP2> boolean notAssignableFrom(Class<?> specialization, Class<?> clazz, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(clazz, "clazz");
		return Predicates.notAssignableFrom(specialization, clazz);
	}

	/** "Special" predicate: Class <%s> of class <%s> must ---NOT-- be specialization of <%s>. */
	public static <MP1, MP2> @Nullable String notAssignableFrom$(Class<?> specialization, Class<?> clazz, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(clazz, "clazz");
		return Predicates.notAssignableFrom$(specialization, clazz);
	}

	// </editor-fold>

	// <editor-fold desc="Throwables">

	/** Predicate: Exception <%s> must be instance of a RuntimeException..*/
	public static <MP1, MP2> boolean runtime(@Nonnull Throwable e, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(e, "e");
		return Predicates.runtime(e);
	}

	/** "Special" predicate: Exception <%s> must be instance of a RuntimeException. */
	public static <MP1, MP2> @Nullable String runtime$(@Nonnull Throwable e, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(e, "e");
		return Predicates.runtime$(e);
	}
	/** Predicate: Exception <%s> must NOT be instance of a RuntimeException..*/
	public static <MP1, MP2> boolean notRuntime(@Nonnull Throwable e, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(e, "e");
		return Predicates.notRuntime(e);
	}

	/** "Special" predicate: Exception <%s> must NOT be instance of a RuntimeException. */
	public static <MP1, MP2> @Nullable String notRuntime$(@Nonnull Throwable e, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(e, "e");
		return Predicates.notRuntime$(e);
	}

	/** Predicate: Exception <%s> must have cause..*/
	public static <MP1, MP2> boolean cause(@Nonnull Throwable e, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(e, "e");
		return Predicates.cause(e);
	}

	/** "Special" predicate: Exception <%s> must have cause. */
	public static <MP1, MP2> @Nullable String cause$(@Nonnull Throwable e, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(e, "e");
		return Predicates.cause$(e);
	}
	/** Predicate: Exception <%s> must NOT have cause..*/
	public static <MP1, MP2> boolean noCause(@Nonnull Throwable e, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(e, "e");
		return Predicates.noCause(e);
	}

	/** "Special" predicate: Exception <%s> must NOT have cause. */
	public static <MP1, MP2> @Nullable String noCause$(@Nonnull Throwable e, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(e, "e");
		return Predicates.noCause$(e);
	}

	/** Predicate: Exception <%s> must have suspended other exceptions..*/
	public static <MP1, MP2> boolean suspended(@Nonnull Throwable e, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(e, "e");
		return Predicates.suspended(e);
	}

	/** "Special" predicate: Exception <%s> must have suspended other exceptions. */
	public static <MP1, MP2> @Nullable String suspended$(@Nonnull Throwable e, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(e, "e");
		return Predicates.suspended$(e);
	}
	/** Predicate: Exception <%s> must NOT have suspended other exceptions..*/
	public static <MP1, MP2> boolean noSuspended(@Nonnull Throwable e, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(e, "e");
		return Predicates.noSuspended(e);
	}

	/** "Special" predicate: Exception <%s> must NOT have suspended other exceptions. */
	public static <MP1, MP2> @Nullable String noSuspended$(@Nonnull Throwable e, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(e, "e");
		return Predicates.noSuspended$(e);
	}

	/** Predicate: Exception <%s> must have message equal to <'%s>'..*/
	public static <MP1, MP2> boolean msgEqual(@Nonnull Throwable e, String text, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(e, "e");
		return Predicates.msgEqual(e, text);
	}

	/** "Special" predicate: Exception <%s> must have message equal to <'%s>'. */
	public static <MP1, MP2> @Nullable String msgEqual$(@Nonnull Throwable e, String text, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(e, "e");
		return Predicates.msgEqual$(e, text);
	}
	/** Predicate: Exception <%s> must NOT have message equal to <'%s>'..*/
	public static <MP1, MP2> boolean msgNotEqual(@Nonnull Throwable e, String text, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(e, "e");
		return Predicates.msgNotEqual(e, text);
	}

	/** "Special" predicate: Exception <%s> must NOT have message equal to <'%s>'. */
	public static <MP1, MP2> @Nullable String msgNotEqual$(@Nonnull Throwable e, String text, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(e, "e");
		return Predicates.msgNotEqual$(e, text);
	}

	/** Predicate: Exception <%s> must have message equal to <'%s>'..*/
	public static <MP1, MP2> boolean noMsg(@Nonnull Throwable e, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(e, "e");
		return Predicates.noMsg(e);
	}

	/** "Special" predicate: Exception <%s> must have message equal to <'%s>'. */
	public static <MP1, MP2> @Nullable String noMsg$(@Nonnull Throwable e, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(e, "e");
		return Predicates.noMsg$(e);
	}
	/** Predicate: Exception <%s> must NOT have message equal to <'%s>'..*/
	public static <MP1, MP2> boolean msgPresent(@Nonnull Throwable e, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(e, "e");
		return Predicates.msgPresent(e);
	}

	/** "Special" predicate: Exception <%s> must NOT have message equal to <'%s>'. */
	public static <MP1, MP2> @Nullable String msgPresent$(@Nonnull Throwable e, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(e, "e");
		return Predicates.msgPresent$(e);
	}

	/** Predicate: Exception <%s> must have message starting with <'%s>'..*/
	public static <MP1, MP2> boolean msgStartWith(@Nonnull Throwable e, @Nonnull String text, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(e, "e");
		Null.nonNullArg(text, "text");
		return Predicates.msgStartWith(e, text);
	}

	/** "Special" predicate: Exception <%s> must have message starting with <'%s>'. */
	public static <MP1, MP2> @Nullable String msgStartWith$(@Nonnull Throwable e, @Nonnull String text, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(e, "e");
		Null.nonNullArg(text, "text");
		return Predicates.msgStartWith$(e, text);
	}
	/** Predicate: Exception <%s> must NOT have message starting with <'%s>'..*/
	public static <MP1, MP2> boolean msgNotStartWith(@Nonnull Throwable e, @Nonnull String text, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(e, "e");
		Null.nonNullArg(text, "text");
		return Predicates.msgNotStartWith(e, text);
	}

	/** "Special" predicate: Exception <%s> must NOT have message starting with <'%s>'. */
	public static <MP1, MP2> @Nullable String msgNotStartWith$(@Nonnull Throwable e, @Nonnull String text, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(e, "e");
		Null.nonNullArg(text, "text");
		return Predicates.msgNotStartWith$(e, text);
	}

	/** Predicate: Exception <%s> must have message containing <'%s>'..*/
	public static <MP1, MP2> boolean msgContain(@Nonnull Throwable e, @Nonnull String text, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(e, "e");
		Null.nonNullArg(text, "text");
		return Predicates.msgContain(e, text);
	}

	/** "Special" predicate: Exception <%s> must have message containing <'%s>'. */
	public static <MP1, MP2> @Nullable String msgContain$(@Nonnull Throwable e, @Nonnull String text, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(e, "e");
		Null.nonNullArg(text, "text");
		return Predicates.msgContain$(e, text);
	}
	/** Predicate: Exception <%s> must NOT have message containing <'%s>'..*/
	public static <MP1, MP2> boolean msgNotContain(@Nonnull Throwable e, @Nonnull String text, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(e, "e");
		Null.nonNullArg(text, "text");
		return Predicates.msgNotContain(e, text);
	}

	/** "Special" predicate: Exception <%s> must NOT have message containing <'%s>'. */
	public static <MP1, MP2> @Nullable String msgNotContain$(@Nonnull Throwable e, @Nonnull String text, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(e, "e");
		Null.nonNullArg(text, "text");
		return Predicates.msgNotContain$(e, text);
	}

	/** Predicate: Exception <%s> must have message ending with <'%s>'..*/
	public static <MP1, MP2> boolean msgEndWith(@Nonnull Throwable e, @Nonnull String text, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(e, "e");
		Null.nonNullArg(text, "text");
		return Predicates.msgEndWith(e, text);
	}

	/** "Special" predicate: Exception <%s> must have message ending with <'%s>'. */
	public static <MP1, MP2> @Nullable String msgEndWith$(@Nonnull Throwable e, @Nonnull String text, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(e, "e");
		Null.nonNullArg(text, "text");
		return Predicates.msgEndWith$(e, text);
	}
	/** Predicate: Exception <%s> must NOT have message ending with <'%s>'..*/
	public static <MP1, MP2> boolean msgNotEndWith(@Nonnull Throwable e, @Nonnull String text, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(e, "e");
		Null.nonNullArg(text, "text");
		return Predicates.msgNotEndWith(e, text);
	}

	/** "Special" predicate: Exception <%s> must NOT have message ending with <'%s>'. */
	public static <MP1, MP2> @Nullable String msgNotEndWith$(@Nonnull Throwable e, @Nonnull String text, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(e, "e");
		Null.nonNullArg(text, "text");
		return Predicates.msgNotEndWith$(e, text);
	}

	/** Predicate: Exception <%s> must have suppressed other exceptions..*/
	public static <MP1, MP2> boolean suppressing(@Nonnull Throwable e, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(e, "e");
		return Predicates.suppressing(e);
	}

	/** "Special" predicate: Exception <%s> must have suppressed other exceptions. */
	public static <MP1, MP2> @Nullable String suppressing$(@Nonnull Throwable e, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(e, "e");
		return Predicates.suppressing$(e);
	}
	/** Predicate: Exception <%s> must NOT have suppressed other exceptions..*/
	public static <MP1, MP2> boolean notSuppressing(@Nonnull Throwable e, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(e, "e");
		return Predicates.notSuppressing(e);
	}

	/** "Special" predicate: Exception <%s> must NOT have suppressed other exceptions. */
	public static <MP1, MP2> @Nullable String notSuppressing$(@Nonnull Throwable e, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(e, "e");
		return Predicates.notSuppressing$(e);
	}

	// </editor-fold>

	// <editor-fold desc="Value (e.g. Opt )">

	/** Predicate: Optional <%s> must have value..*/
	public static <V, MP1, MP2> boolean valuePresent(@Nonnull OptBoolTrait<?> opt, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(opt, "opt");
		return Predicates.valuePresent(opt);
	}

	/** "Special" predicate: Optional <%s> must have value. */
	public static <V, MP1, MP2> @Nullable String valuePresent$(@Nonnull OptBoolTrait<?> opt, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(opt, "opt");
		return Predicates.valuePresent$(opt);
	}
	/** Predicate: Optional <%s> must NOT have value..*/
	public static <V, MP1, MP2> boolean noValuePresent(@Nonnull OptBoolTrait<?> opt, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(opt, "opt");
		return Predicates.noValuePresent(opt);
	}

	/** "Special" predicate: Optional <%s> must NOT have value. */
	public static <V, MP1, MP2> @Nullable String noValuePresent$(@Nonnull OptBoolTrait<?> opt, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(opt, "opt");
		return Predicates.noValuePresent$(opt);
	}

	/** Predicate: Optional <%s> must be void..*/
	public static <V, MP1, MP2> boolean Void(@Nonnull OptBoolTrait<?> opt, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(opt, "opt");
		return Predicates.Void(opt);
	}

	/** "Special" predicate: Optional <%s> must be void. */
	public static <V, MP1, MP2> @Nullable String Void$(@Nonnull OptBoolTrait<?> opt, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(opt, "opt");
		return Predicates.Void$(opt);
	}
	/** Predicate: Optional <%s> must NOT be void..*/
	public static <V, MP1, MP2> boolean notVoid(@Nonnull OptBoolTrait<?> opt, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(opt, "opt");
		return Predicates.notVoid(opt);
	}

	/** "Special" predicate: Optional <%s> must NOT be void. */
	public static <V, MP1, MP2> @Nullable String notVoid$(@Nonnull OptBoolTrait<?> opt, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(opt, "opt");
		return Predicates.notVoid$(opt);
	}

	/** Predicate: Optional <%s> must have value equal <%s>..*/
	public static <V, MP1, MP2> boolean valueEqual(@Nonnull OptBoolTrait<?> opt, boolean expected, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(opt, "opt");
		return Predicates.valueEqual(opt, expected);
	}

	/** "Special" predicate: Optional <%s> must have value equal <%s>. */
	public static <V, MP1, MP2> @Nullable String valueEqual$(@Nonnull OptBoolTrait<?> opt, boolean expected, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(opt, "opt");
		return Predicates.valueEqual$(opt, expected);
	}
	/** Predicate: Optional <%s> must NOT have value equal <%s>..*/
	public static <V, MP1, MP2> boolean valueNotEqual(@Nonnull OptBoolTrait<?> opt, boolean expected, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(opt, "opt");
		return Predicates.valueNotEqual(opt, expected);
	}

	/** "Special" predicate: Optional <%s> must NOT have value equal <%s>. */
	public static <V, MP1, MP2> @Nullable String valueNotEqual$(@Nonnull OptBoolTrait<?> opt, boolean expected, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(opt, "opt");
		return Predicates.valueNotEqual$(opt, expected);
	}

	/** Predicate: Optional <%s> must have value equal <%s>..*/
	public static <V, MP1, MP2> boolean valueEqual(@Nonnull BoolValueTrait<?> opt, boolean expected, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(opt, "opt");
		return Predicates.valueEqual(opt, expected);
	}

	/** "Special" predicate: Optional <%s> must have value equal <%s>. */
	public static <V, MP1, MP2> @Nullable String valueEqual$(@Nonnull BoolValueTrait<?> opt, boolean expected, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(opt, "opt");
		return Predicates.valueEqual$(opt, expected);
	}
	/** Predicate: Optional <%s> must NOT have value equal <%s>..*/
	public static <V, MP1, MP2> boolean valueNotEqual(@Nonnull BoolValueTrait<?> opt, boolean expected, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(opt, "opt");
		return Predicates.valueNotEqual(opt, expected);
	}

	/** "Special" predicate: Optional <%s> must NOT have value equal <%s>. */
	public static <V, MP1, MP2> @Nullable String valueNotEqual$(@Nonnull BoolValueTrait<?> opt, boolean expected, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(opt, "opt");
		return Predicates.valueNotEqual$(opt, expected);
	}

	/** Predicate: Optional <%s> must have value..*/
	public static <V, MP1, MP2> boolean valuePresent(@Nonnull OptTrait<?, ?> opt, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(opt, "opt");
		return Predicates.valuePresent(opt);
	}

	/** "Special" predicate: Optional <%s> must have value. */
	public static <V, MP1, MP2> @Nullable String valuePresent$(@Nonnull OptTrait<?, ?> opt, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(opt, "opt");
		return Predicates.valuePresent$(opt);
	}
	/** Predicate: Optional <%s> must NOT have value..*/
	public static <V, MP1, MP2> boolean noValuePresent(@Nonnull OptTrait<?, ?> opt, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(opt, "opt");
		return Predicates.noValuePresent(opt);
	}

	/** "Special" predicate: Optional <%s> must NOT have value. */
	public static <V, MP1, MP2> @Nullable String noValuePresent$(@Nonnull OptTrait<?, ?> opt, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(opt, "opt");
		return Predicates.noValuePresent$(opt);
	}

	/** Predicate: Optional <%s> must be void..*/
	public static <V, MP1, MP2> boolean Void(@Nonnull OptTrait<?, ?> opt, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(opt, "opt");
		return Predicates.Void(opt);
	}

	/** "Special" predicate: Optional <%s> must be void. */
	public static <V, MP1, MP2> @Nullable String Void$(@Nonnull OptTrait<?, ?> opt, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(opt, "opt");
		return Predicates.Void$(opt);
	}
	/** Predicate: Optional <%s> must NOT be void..*/
	public static <V, MP1, MP2> boolean notVoid(@Nonnull OptTrait<?, ?> opt, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(opt, "opt");
		return Predicates.notVoid(opt);
	}

	/** "Special" predicate: Optional <%s> must NOT be void. */
	public static <V, MP1, MP2> @Nullable String notVoid$(@Nonnull OptTrait<?, ?> opt, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(opt, "opt");
		return Predicates.notVoid$(opt);
	}

	/** Predicate: Optional <%s> must have value equal <%s>..*/
	public static <V, MP1, MP2> boolean valueEqual(@Nonnull ValueTrait<?, ?> opt, V expected, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(opt, "opt");
		return Predicates.valueEqual(opt, expected);
	}

	/** "Special" predicate: Optional <%s> must have value equal <%s>. */
	public static <V, MP1, MP2> @Nullable String valueEqual$(@Nonnull ValueTrait<?, ?> opt, V expected, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(opt, "opt");
		return Predicates.valueEqual$(opt, expected);
	}
	/** Predicate: Optional <%s> must NOT have value equal <%s>..*/
	public static <V, MP1, MP2> boolean valueNotEqual(@Nonnull ValueTrait<?, ?> opt, V expected, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(opt, "opt");
		return Predicates.valueNotEqual(opt, expected);
	}

	/** "Special" predicate: Optional <%s> must NOT have value equal <%s>. */
	public static <V, MP1, MP2> @Nullable String valueNotEqual$(@Nonnull ValueTrait<?, ?> opt, V expected, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(opt, "opt");
		return Predicates.valueNotEqual$(opt, expected);
	}

	/** Predicate: Optional <%s> must have value equal <%s>..*/
	public static <V, MP1, MP2> boolean sameValue(@Nonnull ValueTrait<?, ?> opt, V expected, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(opt, "opt");
		return Predicates.sameValue(opt, expected);
	}

	/** "Special" predicate: Optional <%s> must have value equal <%s>. */
	public static <V, MP1, MP2> @Nullable String sameValue$(@Nonnull ValueTrait<?, ?> opt, V expected, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(opt, "opt");
		return Predicates.sameValue$(opt, expected);
	}
	/** Predicate: Optional <%s> must NOT have value equal <%s>..*/
	public static <V, MP1, MP2> boolean notSameValue(@Nonnull ValueTrait<?, ?> opt, V expected, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(opt, "opt");
		return Predicates.notSameValue(opt, expected);
	}

	/** "Special" predicate: Optional <%s> must NOT have value equal <%s>. */
	public static <V, MP1, MP2> @Nullable String notSameValue$(@Nonnull ValueTrait<?, ?> opt, V expected, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(opt, "opt");
		return Predicates.notSameValue$(opt, expected);
	}

	/** Predicate: Optional <%s> must have value..*/
	public static <V, MP1, MP2> boolean valuePresent(@Nonnull OptByteTrait<?> opt, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(opt, "opt");
		return Predicates.valuePresent(opt);
	}

	/** "Special" predicate: Optional <%s> must have value. */
	public static <V, MP1, MP2> @Nullable String valuePresent$(@Nonnull OptByteTrait<?> opt, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(opt, "opt");
		return Predicates.valuePresent$(opt);
	}
	/** Predicate: Optional <%s> must NOT have value..*/
	public static <V, MP1, MP2> boolean noValuePresent(@Nonnull OptByteTrait<?> opt, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(opt, "opt");
		return Predicates.noValuePresent(opt);
	}

	/** "Special" predicate: Optional <%s> must NOT have value. */
	public static <V, MP1, MP2> @Nullable String noValuePresent$(@Nonnull OptByteTrait<?> opt, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(opt, "opt");
		return Predicates.noValuePresent$(opt);
	}

	/** Predicate: Optional <%s> must be void..*/
	public static <V, MP1, MP2> boolean Void(@Nonnull OptByteTrait<?> opt, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(opt, "opt");
		return Predicates.Void(opt);
	}

	/** "Special" predicate: Optional <%s> must be void. */
	public static <V, MP1, MP2> @Nullable String Void$(@Nonnull OptByteTrait<?> opt, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(opt, "opt");
		return Predicates.Void$(opt);
	}
	/** Predicate: Optional <%s> must NOT be void..*/
	public static <V, MP1, MP2> boolean notVoid(@Nonnull OptByteTrait<?> opt, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(opt, "opt");
		return Predicates.notVoid(opt);
	}

	/** "Special" predicate: Optional <%s> must NOT be void. */
	public static <V, MP1, MP2> @Nullable String notVoid$(@Nonnull OptByteTrait<?> opt, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(opt, "opt");
		return Predicates.notVoid$(opt);
	}

	/** Predicate: Optional <%s> must have value equal <%s>..*/
	public static <V, MP1, MP2> boolean valueEqual(@Nonnull OptByteTrait<?> opt, byte expected, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(opt, "opt");
		return Predicates.valueEqual(opt, expected);
	}

	/** "Special" predicate: Optional <%s> must have value equal <%s>. */
	public static <V, MP1, MP2> @Nullable String valueEqual$(@Nonnull OptByteTrait<?> opt, byte expected, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(opt, "opt");
		return Predicates.valueEqual$(opt, expected);
	}
	/** Predicate: Optional <%s> must NOT have value equal <%s>..*/
	public static <V, MP1, MP2> boolean valueNotEqual(@Nonnull OptByteTrait<?> opt, byte expected, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(opt, "opt");
		return Predicates.valueNotEqual(opt, expected);
	}

	/** "Special" predicate: Optional <%s> must NOT have value equal <%s>. */
	public static <V, MP1, MP2> @Nullable String valueNotEqual$(@Nonnull OptByteTrait<?> opt, byte expected, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(opt, "opt");
		return Predicates.valueNotEqual$(opt, expected);
	}

	/** Predicate: Optional <%s> must have value equal <%s>..*/
	public static <V, MP1, MP2> boolean valueEqual(@Nonnull ByteValueTrait<?> opt, byte expected, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(opt, "opt");
		return Predicates.valueEqual(opt, expected);
	}

	/** "Special" predicate: Optional <%s> must have value equal <%s>. */
	public static <V, MP1, MP2> @Nullable String valueEqual$(@Nonnull ByteValueTrait<?> opt, byte expected, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(opt, "opt");
		return Predicates.valueEqual$(opt, expected);
	}
	/** Predicate: Optional <%s> must NOT have value equal <%s>..*/
	public static <V, MP1, MP2> boolean valueNotEqual(@Nonnull ByteValueTrait<?> opt, byte expected, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(opt, "opt");
		return Predicates.valueNotEqual(opt, expected);
	}

	/** "Special" predicate: Optional <%s> must NOT have value equal <%s>. */
	public static <V, MP1, MP2> @Nullable String valueNotEqual$(@Nonnull ByteValueTrait<?> opt, byte expected, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(opt, "opt");
		return Predicates.valueNotEqual$(opt, expected);
	}

	/** Predicate: Optional <%s> must have value..*/
	public static <V, MP1, MP2> boolean valuePresent(@Nonnull OptDblTrait<?> opt, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(opt, "opt");
		return Predicates.valuePresent(opt);
	}

	/** "Special" predicate: Optional <%s> must have value. */
	public static <V, MP1, MP2> @Nullable String valuePresent$(@Nonnull OptDblTrait<?> opt, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(opt, "opt");
		return Predicates.valuePresent$(opt);
	}
	/** Predicate: Optional <%s> must NOT have value..*/
	public static <V, MP1, MP2> boolean noValuePresent(@Nonnull OptDblTrait<?> opt, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(opt, "opt");
		return Predicates.noValuePresent(opt);
	}

	/** "Special" predicate: Optional <%s> must NOT have value. */
	public static <V, MP1, MP2> @Nullable String noValuePresent$(@Nonnull OptDblTrait<?> opt, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(opt, "opt");
		return Predicates.noValuePresent$(opt);
	}

	/** Predicate: Optional <%s> must be void..*/
	public static <V, MP1, MP2> boolean Void(@Nonnull OptDblTrait<?> opt, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(opt, "opt");
		return Predicates.Void(opt);
	}

	/** "Special" predicate: Optional <%s> must be void. */
	public static <V, MP1, MP2> @Nullable String Void$(@Nonnull OptDblTrait<?> opt, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(opt, "opt");
		return Predicates.Void$(opt);
	}
	/** Predicate: Optional <%s> must NOT be void..*/
	public static <V, MP1, MP2> boolean notVoid(@Nonnull OptDblTrait<?> opt, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(opt, "opt");
		return Predicates.notVoid(opt);
	}

	/** "Special" predicate: Optional <%s> must NOT be void. */
	public static <V, MP1, MP2> @Nullable String notVoid$(@Nonnull OptDblTrait<?> opt, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(opt, "opt");
		return Predicates.notVoid$(opt);
	}

	/** Predicate: Optional <%s> must have value equal <%s>..*/
	public static <V, MP1, MP2> boolean valueEqual(@Nonnull OptDblTrait<?> opt, double expected, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(opt, "opt");
		return Predicates.valueEqual(opt, expected);
	}

	/** "Special" predicate: Optional <%s> must have value equal <%s>. */
	public static <V, MP1, MP2> @Nullable String valueEqual$(@Nonnull OptDblTrait<?> opt, double expected, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(opt, "opt");
		return Predicates.valueEqual$(opt, expected);
	}
	/** Predicate: Optional <%s> must NOT have value equal <%s>..*/
	public static <V, MP1, MP2> boolean valueNotEqual(@Nonnull OptDblTrait<?> opt, double expected, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(opt, "opt");
		return Predicates.valueNotEqual(opt, expected);
	}

	/** "Special" predicate: Optional <%s> must NOT have value equal <%s>. */
	public static <V, MP1, MP2> @Nullable String valueNotEqual$(@Nonnull OptDblTrait<?> opt, double expected, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(opt, "opt");
		return Predicates.valueNotEqual$(opt, expected);
	}

	/** Predicate: Optional <%s> must have value equal <%s>..*/
	public static <V, MP1, MP2> boolean valueEqual(@Nonnull DblValueTrait<?> opt, double expected, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(opt, "opt");
		return Predicates.valueEqual(opt, expected);
	}

	/** "Special" predicate: Optional <%s> must have value equal <%s>. */
	public static <V, MP1, MP2> @Nullable String valueEqual$(@Nonnull DblValueTrait<?> opt, double expected, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(opt, "opt");
		return Predicates.valueEqual$(opt, expected);
	}
	/** Predicate: Optional <%s> must NOT have value equal <%s>..*/
	public static <V, MP1, MP2> boolean valueNotEqual(@Nonnull DblValueTrait<?> opt, double expected, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(opt, "opt");
		return Predicates.valueNotEqual(opt, expected);
	}

	/** "Special" predicate: Optional <%s> must NOT have value equal <%s>. */
	public static <V, MP1, MP2> @Nullable String valueNotEqual$(@Nonnull DblValueTrait<?> opt, double expected, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(opt, "opt");
		return Predicates.valueNotEqual$(opt, expected);
	}

	/** Predicate: Optional <%s> must have value..*/
	public static <V, MP1, MP2> boolean valuePresent(@Nonnull OptCharTrait<?> opt, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(opt, "opt");
		return Predicates.valuePresent(opt);
	}

	/** "Special" predicate: Optional <%s> must have value. */
	public static <V, MP1, MP2> @Nullable String valuePresent$(@Nonnull OptCharTrait<?> opt, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(opt, "opt");
		return Predicates.valuePresent$(opt);
	}
	/** Predicate: Optional <%s> must NOT have value..*/
	public static <V, MP1, MP2> boolean noValuePresent(@Nonnull OptCharTrait<?> opt, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(opt, "opt");
		return Predicates.noValuePresent(opt);
	}

	/** "Special" predicate: Optional <%s> must NOT have value. */
	public static <V, MP1, MP2> @Nullable String noValuePresent$(@Nonnull OptCharTrait<?> opt, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(opt, "opt");
		return Predicates.noValuePresent$(opt);
	}

	/** Predicate: Optional <%s> must be void..*/
	public static <V, MP1, MP2> boolean Void(@Nonnull OptCharTrait<?> opt, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(opt, "opt");
		return Predicates.Void(opt);
	}

	/** "Special" predicate: Optional <%s> must be void. */
	public static <V, MP1, MP2> @Nullable String Void$(@Nonnull OptCharTrait<?> opt, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(opt, "opt");
		return Predicates.Void$(opt);
	}
	/** Predicate: Optional <%s> must NOT be void..*/
	public static <V, MP1, MP2> boolean notVoid(@Nonnull OptCharTrait<?> opt, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(opt, "opt");
		return Predicates.notVoid(opt);
	}

	/** "Special" predicate: Optional <%s> must NOT be void. */
	public static <V, MP1, MP2> @Nullable String notVoid$(@Nonnull OptCharTrait<?> opt, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(opt, "opt");
		return Predicates.notVoid$(opt);
	}

	/** Predicate: Optional <%s> must have value equal <%s>..*/
	public static <V, MP1, MP2> boolean valueEqual(@Nonnull OptCharTrait<?> opt, char expected, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(opt, "opt");
		return Predicates.valueEqual(opt, expected);
	}

	/** "Special" predicate: Optional <%s> must have value equal <%s>. */
	public static <V, MP1, MP2> @Nullable String valueEqual$(@Nonnull OptCharTrait<?> opt, char expected, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(opt, "opt");
		return Predicates.valueEqual$(opt, expected);
	}
	/** Predicate: Optional <%s> must NOT have value equal <%s>..*/
	public static <V, MP1, MP2> boolean valueNotEqual(@Nonnull OptCharTrait<?> opt, char expected, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(opt, "opt");
		return Predicates.valueNotEqual(opt, expected);
	}

	/** "Special" predicate: Optional <%s> must NOT have value equal <%s>. */
	public static <V, MP1, MP2> @Nullable String valueNotEqual$(@Nonnull OptCharTrait<?> opt, char expected, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(opt, "opt");
		return Predicates.valueNotEqual$(opt, expected);
	}

	/** Predicate: Optional <%s> must have value equal <%s>..*/
	public static <V, MP1, MP2> boolean valueEqual(@Nonnull CharValueTrait<?> opt, char expected, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(opt, "opt");
		return Predicates.valueEqual(opt, expected);
	}

	/** "Special" predicate: Optional <%s> must have value equal <%s>. */
	public static <V, MP1, MP2> @Nullable String valueEqual$(@Nonnull CharValueTrait<?> opt, char expected, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(opt, "opt");
		return Predicates.valueEqual$(opt, expected);
	}
	/** Predicate: Optional <%s> must NOT have value equal <%s>..*/
	public static <V, MP1, MP2> boolean valueNotEqual(@Nonnull CharValueTrait<?> opt, char expected, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(opt, "opt");
		return Predicates.valueNotEqual(opt, expected);
	}

	/** "Special" predicate: Optional <%s> must NOT have value equal <%s>. */
	public static <V, MP1, MP2> @Nullable String valueNotEqual$(@Nonnull CharValueTrait<?> opt, char expected, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(opt, "opt");
		return Predicates.valueNotEqual$(opt, expected);
	}

	/** Predicate: Optional <%s> must have value..*/
	public static <V, MP1, MP2> boolean valuePresent(@Nonnull OptSrtTrait<?> opt, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(opt, "opt");
		return Predicates.valuePresent(opt);
	}

	/** "Special" predicate: Optional <%s> must have value. */
	public static <V, MP1, MP2> @Nullable String valuePresent$(@Nonnull OptSrtTrait<?> opt, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(opt, "opt");
		return Predicates.valuePresent$(opt);
	}
	/** Predicate: Optional <%s> must NOT have value..*/
	public static <V, MP1, MP2> boolean noValuePresent(@Nonnull OptSrtTrait<?> opt, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(opt, "opt");
		return Predicates.noValuePresent(opt);
	}

	/** "Special" predicate: Optional <%s> must NOT have value. */
	public static <V, MP1, MP2> @Nullable String noValuePresent$(@Nonnull OptSrtTrait<?> opt, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(opt, "opt");
		return Predicates.noValuePresent$(opt);
	}

	/** Predicate: Optional <%s> must be void..*/
	public static <V, MP1, MP2> boolean Void(@Nonnull OptSrtTrait<?> opt, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(opt, "opt");
		return Predicates.Void(opt);
	}

	/** "Special" predicate: Optional <%s> must be void. */
	public static <V, MP1, MP2> @Nullable String Void$(@Nonnull OptSrtTrait<?> opt, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(opt, "opt");
		return Predicates.Void$(opt);
	}
	/** Predicate: Optional <%s> must NOT be void..*/
	public static <V, MP1, MP2> boolean notVoid(@Nonnull OptSrtTrait<?> opt, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(opt, "opt");
		return Predicates.notVoid(opt);
	}

	/** "Special" predicate: Optional <%s> must NOT be void. */
	public static <V, MP1, MP2> @Nullable String notVoid$(@Nonnull OptSrtTrait<?> opt, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(opt, "opt");
		return Predicates.notVoid$(opt);
	}

	/** Predicate: Optional <%s> must have value equal <%s>..*/
	public static <V, MP1, MP2> boolean valueEqual(@Nonnull OptSrtTrait<?> opt, short expected, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(opt, "opt");
		return Predicates.valueEqual(opt, expected);
	}

	/** "Special" predicate: Optional <%s> must have value equal <%s>. */
	public static <V, MP1, MP2> @Nullable String valueEqual$(@Nonnull OptSrtTrait<?> opt, short expected, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(opt, "opt");
		return Predicates.valueEqual$(opt, expected);
	}
	/** Predicate: Optional <%s> must NOT have value equal <%s>..*/
	public static <V, MP1, MP2> boolean valueNotEqual(@Nonnull OptSrtTrait<?> opt, short expected, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(opt, "opt");
		return Predicates.valueNotEqual(opt, expected);
	}

	/** "Special" predicate: Optional <%s> must NOT have value equal <%s>. */
	public static <V, MP1, MP2> @Nullable String valueNotEqual$(@Nonnull OptSrtTrait<?> opt, short expected, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(opt, "opt");
		return Predicates.valueNotEqual$(opt, expected);
	}

	/** Predicate: Optional <%s> must have value equal <%s>..*/
	public static <V, MP1, MP2> boolean valueEqual(@Nonnull SrtValueTrait<?> opt, short expected, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(opt, "opt");
		return Predicates.valueEqual(opt, expected);
	}

	/** "Special" predicate: Optional <%s> must have value equal <%s>. */
	public static <V, MP1, MP2> @Nullable String valueEqual$(@Nonnull SrtValueTrait<?> opt, short expected, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(opt, "opt");
		return Predicates.valueEqual$(opt, expected);
	}
	/** Predicate: Optional <%s> must NOT have value equal <%s>..*/
	public static <V, MP1, MP2> boolean valueNotEqual(@Nonnull SrtValueTrait<?> opt, short expected, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(opt, "opt");
		return Predicates.valueNotEqual(opt, expected);
	}

	/** "Special" predicate: Optional <%s> must NOT have value equal <%s>. */
	public static <V, MP1, MP2> @Nullable String valueNotEqual$(@Nonnull SrtValueTrait<?> opt, short expected, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(opt, "opt");
		return Predicates.valueNotEqual$(opt, expected);
	}

	/** Predicate: Optional <%s> must have value..*/
	public static <V, MP1, MP2> boolean valuePresent(@Nonnull OptFltTrait<?> opt, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(opt, "opt");
		return Predicates.valuePresent(opt);
	}

	/** "Special" predicate: Optional <%s> must have value. */
	public static <V, MP1, MP2> @Nullable String valuePresent$(@Nonnull OptFltTrait<?> opt, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(opt, "opt");
		return Predicates.valuePresent$(opt);
	}
	/** Predicate: Optional <%s> must NOT have value..*/
	public static <V, MP1, MP2> boolean noValuePresent(@Nonnull OptFltTrait<?> opt, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(opt, "opt");
		return Predicates.noValuePresent(opt);
	}

	/** "Special" predicate: Optional <%s> must NOT have value. */
	public static <V, MP1, MP2> @Nullable String noValuePresent$(@Nonnull OptFltTrait<?> opt, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(opt, "opt");
		return Predicates.noValuePresent$(opt);
	}

	/** Predicate: Optional <%s> must be void..*/
	public static <V, MP1, MP2> boolean Void(@Nonnull OptFltTrait<?> opt, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(opt, "opt");
		return Predicates.Void(opt);
	}

	/** "Special" predicate: Optional <%s> must be void. */
	public static <V, MP1, MP2> @Nullable String Void$(@Nonnull OptFltTrait<?> opt, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(opt, "opt");
		return Predicates.Void$(opt);
	}
	/** Predicate: Optional <%s> must NOT be void..*/
	public static <V, MP1, MP2> boolean notVoid(@Nonnull OptFltTrait<?> opt, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(opt, "opt");
		return Predicates.notVoid(opt);
	}

	/** "Special" predicate: Optional <%s> must NOT be void. */
	public static <V, MP1, MP2> @Nullable String notVoid$(@Nonnull OptFltTrait<?> opt, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(opt, "opt");
		return Predicates.notVoid$(opt);
	}

	/** Predicate: Optional <%s> must have value equal <%s>..*/
	public static <V, MP1, MP2> boolean valueEqual(@Nonnull OptFltTrait<?> opt, float expected, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(opt, "opt");
		return Predicates.valueEqual(opt, expected);
	}

	/** "Special" predicate: Optional <%s> must have value equal <%s>. */
	public static <V, MP1, MP2> @Nullable String valueEqual$(@Nonnull OptFltTrait<?> opt, float expected, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(opt, "opt");
		return Predicates.valueEqual$(opt, expected);
	}
	/** Predicate: Optional <%s> must NOT have value equal <%s>..*/
	public static <V, MP1, MP2> boolean valueNotEqual(@Nonnull OptFltTrait<?> opt, float expected, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(opt, "opt");
		return Predicates.valueNotEqual(opt, expected);
	}

	/** "Special" predicate: Optional <%s> must NOT have value equal <%s>. */
	public static <V, MP1, MP2> @Nullable String valueNotEqual$(@Nonnull OptFltTrait<?> opt, float expected, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(opt, "opt");
		return Predicates.valueNotEqual$(opt, expected);
	}

	/** Predicate: Optional <%s> must have value equal <%s>..*/
	public static <V, MP1, MP2> boolean valueEqual(@Nonnull FltValueTrait<?> opt, float expected, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(opt, "opt");
		return Predicates.valueEqual(opt, expected);
	}

	/** "Special" predicate: Optional <%s> must have value equal <%s>. */
	public static <V, MP1, MP2> @Nullable String valueEqual$(@Nonnull FltValueTrait<?> opt, float expected, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(opt, "opt");
		return Predicates.valueEqual$(opt, expected);
	}
	/** Predicate: Optional <%s> must NOT have value equal <%s>..*/
	public static <V, MP1, MP2> boolean valueNotEqual(@Nonnull FltValueTrait<?> opt, float expected, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(opt, "opt");
		return Predicates.valueNotEqual(opt, expected);
	}

	/** "Special" predicate: Optional <%s> must NOT have value equal <%s>. */
	public static <V, MP1, MP2> @Nullable String valueNotEqual$(@Nonnull FltValueTrait<?> opt, float expected, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(opt, "opt");
		return Predicates.valueNotEqual$(opt, expected);
	}

	/** Predicate: Optional <%s> must have value..*/
	public static <V, MP1, MP2> boolean valuePresent(@Nonnull OptIntTrait<?> opt, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(opt, "opt");
		return Predicates.valuePresent(opt);
	}

	/** "Special" predicate: Optional <%s> must have value. */
	public static <V, MP1, MP2> @Nullable String valuePresent$(@Nonnull OptIntTrait<?> opt, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(opt, "opt");
		return Predicates.valuePresent$(opt);
	}
	/** Predicate: Optional <%s> must NOT have value..*/
	public static <V, MP1, MP2> boolean noValuePresent(@Nonnull OptIntTrait<?> opt, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(opt, "opt");
		return Predicates.noValuePresent(opt);
	}

	/** "Special" predicate: Optional <%s> must NOT have value. */
	public static <V, MP1, MP2> @Nullable String noValuePresent$(@Nonnull OptIntTrait<?> opt, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(opt, "opt");
		return Predicates.noValuePresent$(opt);
	}

	/** Predicate: Optional <%s> must be void..*/
	public static <V, MP1, MP2> boolean Void(@Nonnull OptIntTrait<?> opt, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(opt, "opt");
		return Predicates.Void(opt);
	}

	/** "Special" predicate: Optional <%s> must be void. */
	public static <V, MP1, MP2> @Nullable String Void$(@Nonnull OptIntTrait<?> opt, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(opt, "opt");
		return Predicates.Void$(opt);
	}
	/** Predicate: Optional <%s> must NOT be void..*/
	public static <V, MP1, MP2> boolean notVoid(@Nonnull OptIntTrait<?> opt, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(opt, "opt");
		return Predicates.notVoid(opt);
	}

	/** "Special" predicate: Optional <%s> must NOT be void. */
	public static <V, MP1, MP2> @Nullable String notVoid$(@Nonnull OptIntTrait<?> opt, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(opt, "opt");
		return Predicates.notVoid$(opt);
	}

	/** Predicate: Optional <%s> must have value equal <%s>..*/
	public static <V, MP1, MP2> boolean valueEqual(@Nonnull OptIntTrait<?> opt, int expected, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(opt, "opt");
		return Predicates.valueEqual(opt, expected);
	}

	/** "Special" predicate: Optional <%s> must have value equal <%s>. */
	public static <V, MP1, MP2> @Nullable String valueEqual$(@Nonnull OptIntTrait<?> opt, int expected, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(opt, "opt");
		return Predicates.valueEqual$(opt, expected);
	}
	/** Predicate: Optional <%s> must NOT have value equal <%s>..*/
	public static <V, MP1, MP2> boolean valueNotEqual(@Nonnull OptIntTrait<?> opt, int expected, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(opt, "opt");
		return Predicates.valueNotEqual(opt, expected);
	}

	/** "Special" predicate: Optional <%s> must NOT have value equal <%s>. */
	public static <V, MP1, MP2> @Nullable String valueNotEqual$(@Nonnull OptIntTrait<?> opt, int expected, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(opt, "opt");
		return Predicates.valueNotEqual$(opt, expected);
	}

	/** Predicate: Optional <%s> must have value equal <%s>..*/
	public static <V, MP1, MP2> boolean valueEqual(@Nonnull IntValueTrait<?> opt, int expected, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(opt, "opt");
		return Predicates.valueEqual(opt, expected);
	}

	/** "Special" predicate: Optional <%s> must have value equal <%s>. */
	public static <V, MP1, MP2> @Nullable String valueEqual$(@Nonnull IntValueTrait<?> opt, int expected, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(opt, "opt");
		return Predicates.valueEqual$(opt, expected);
	}
	/** Predicate: Optional <%s> must NOT have value equal <%s>..*/
	public static <V, MP1, MP2> boolean valueNotEqual(@Nonnull IntValueTrait<?> opt, int expected, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(opt, "opt");
		return Predicates.valueNotEqual(opt, expected);
	}

	/** "Special" predicate: Optional <%s> must NOT have value equal <%s>. */
	public static <V, MP1, MP2> @Nullable String valueNotEqual$(@Nonnull IntValueTrait<?> opt, int expected, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(opt, "opt");
		return Predicates.valueNotEqual$(opt, expected);
	}

	/** Predicate: Optional <%s> must have value..*/
	public static <V, MP1, MP2> boolean valuePresent(@Nonnull OptLongTrait<?> opt, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(opt, "opt");
		return Predicates.valuePresent(opt);
	}

	/** "Special" predicate: Optional <%s> must have value. */
	public static <V, MP1, MP2> @Nullable String valuePresent$(@Nonnull OptLongTrait<?> opt, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(opt, "opt");
		return Predicates.valuePresent$(opt);
	}
	/** Predicate: Optional <%s> must NOT have value..*/
	public static <V, MP1, MP2> boolean noValuePresent(@Nonnull OptLongTrait<?> opt, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(opt, "opt");
		return Predicates.noValuePresent(opt);
	}

	/** "Special" predicate: Optional <%s> must NOT have value. */
	public static <V, MP1, MP2> @Nullable String noValuePresent$(@Nonnull OptLongTrait<?> opt, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(opt, "opt");
		return Predicates.noValuePresent$(opt);
	}

	/** Predicate: Optional <%s> must be void..*/
	public static <V, MP1, MP2> boolean Void(@Nonnull OptLongTrait<?> opt, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(opt, "opt");
		return Predicates.Void(opt);
	}

	/** "Special" predicate: Optional <%s> must be void. */
	public static <V, MP1, MP2> @Nullable String Void$(@Nonnull OptLongTrait<?> opt, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(opt, "opt");
		return Predicates.Void$(opt);
	}
	/** Predicate: Optional <%s> must NOT be void..*/
	public static <V, MP1, MP2> boolean notVoid(@Nonnull OptLongTrait<?> opt, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(opt, "opt");
		return Predicates.notVoid(opt);
	}

	/** "Special" predicate: Optional <%s> must NOT be void. */
	public static <V, MP1, MP2> @Nullable String notVoid$(@Nonnull OptLongTrait<?> opt, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(opt, "opt");
		return Predicates.notVoid$(opt);
	}

	/** Predicate: Optional <%s> must have value equal <%s>..*/
	public static <V, MP1, MP2> boolean valueEqual(@Nonnull OptLongTrait<?> opt, long expected, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(opt, "opt");
		return Predicates.valueEqual(opt, expected);
	}

	/** "Special" predicate: Optional <%s> must have value equal <%s>. */
	public static <V, MP1, MP2> @Nullable String valueEqual$(@Nonnull OptLongTrait<?> opt, long expected, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(opt, "opt");
		return Predicates.valueEqual$(opt, expected);
	}
	/** Predicate: Optional <%s> must NOT have value equal <%s>..*/
	public static <V, MP1, MP2> boolean valueNotEqual(@Nonnull OptLongTrait<?> opt, long expected, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(opt, "opt");
		return Predicates.valueNotEqual(opt, expected);
	}

	/** "Special" predicate: Optional <%s> must NOT have value equal <%s>. */
	public static <V, MP1, MP2> @Nullable String valueNotEqual$(@Nonnull OptLongTrait<?> opt, long expected, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(opt, "opt");
		return Predicates.valueNotEqual$(opt, expected);
	}

	/** Predicate: Optional <%s> must have value equal <%s>..*/
	public static <V, MP1, MP2> boolean valueEqual(@Nonnull LongValueTrait<?> opt, long expected, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(opt, "opt");
		return Predicates.valueEqual(opt, expected);
	}

	/** "Special" predicate: Optional <%s> must have value equal <%s>. */
	public static <V, MP1, MP2> @Nullable String valueEqual$(@Nonnull LongValueTrait<?> opt, long expected, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(opt, "opt");
		return Predicates.valueEqual$(opt, expected);
	}
	/** Predicate: Optional <%s> must NOT have value equal <%s>..*/
	public static <V, MP1, MP2> boolean valueNotEqual(@Nonnull LongValueTrait<?> opt, long expected, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(opt, "opt");
		return Predicates.valueNotEqual(opt, expected);
	}

	/** "Special" predicate: Optional <%s> must NOT have value equal <%s>. */
	public static <V, MP1, MP2> @Nullable String valueNotEqual$(@Nonnull LongValueTrait<?> opt, long expected, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(opt, "opt");
		return Predicates.valueNotEqual$(opt, expected);
	}

	// </editor-fold>

}
