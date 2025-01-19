/*
 * This file is part of "lunisolar-magma".
 *
 * (C) Copyright 2014-2023 Lunisolar (http://lunisolar.eu/).
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
 * This class contains some predicates that take - and ignore - additional argument(s) that are intended only for building a message.
 *
 * @see {@link Is}{@link Are}{@link Do}{@link Does}{@link Be}{@link Has}{@link Have}{@link As}{@link P}{@link ꝓ}{@link Predicates}{@link P1}{@link P2}
 */
@MethodReferences
public class P1 implements FluentSyntax {

	//<editor-fold desc="Object">

	/** Predicate: Object <%s> must be the same as <%s>.*/
	public static <MP1> boolean same(@Nullable Object n, @Nullable Object other, MP1 msgParamOnly) {
		return Predicates.same(n, other);
	}

	/** "Special" predicate: Object <%s> must be the same as <%s>.*/
	public static <MP1> @Nullable String sameEx(@Nullable Object n, @Nullable Object other, MP1 msgParamOnly) {
		return Predicates.sameEx(n, other);
	}

	/** Predicate: Object <%s> must NOT be the same as <%s>.*/
	public static <MP1> boolean notSame(@Nullable Object n, @Nullable Object other, MP1 msgParamOnly) {
		return Predicates.notSame(n, other);
	}

	/** "Special" predicate: Object <%s> must NOT be the same as <%s>.*/
	public static <MP1> @Nullable String notSameEx(@Nullable Object n, @Nullable Object other, MP1 msgParamOnly) {
		return Predicates.notSameEx(n, other);
	}

	/** Predicate: Reference must be null, currently is pointing to <%s>.*/
	public static <MP1> boolean Null(@Nullable Object n, MP1 msgParamOnly) {
		return Predicates.Null(n);
	}

	/** "Special" predicate: Reference must be null, currently is pointing to <%s>.*/
	public static <MP1> @Nullable String NullEx(@Nullable Object n, MP1 msgParamOnly) {
		return Predicates.NullEx(n);
	}

	/** Predicate: Reference must NOT be null, currently is pointing to <%s>.*/
	public static <MP1> boolean notNull(@Nullable Object n, MP1 msgParamOnly) {
		return Predicates.notNull(n);
	}

	/** "Special" predicate: Reference must NOT be null, currently is pointing to <%s>.*/
	public static <MP1> @Nullable String notNullEx(@Nullable Object n, MP1 msgParamOnly) {
		return Predicates.notNullEx(n);
	}

	//</editor-fold>

	// <editor-fold desc="'String'">

	/** Predicate: String <%s> must be %d characters long (actual: %d).*/
	public static <MP1> boolean length(@Nonnull CharSequence s, int size, MP1 msgParamOnly) {
		Null.nonNullArg(s, "s");
		return Predicates.length(s, size);
	}

	/** "Special" predicate: String <%s> must be %d characters long (actual: %d).*/
	public static <MP1> @Nullable String lengthEx(@Nonnull CharSequence s, int size, MP1 msgParamOnly) {
		Null.nonNullArg(s, "s");
		return Predicates.lengthEx(s, size);
	}

	/** Predicate: String <%s> must NOT be %d characters long (actual: %d).*/
	public static <MP1> boolean lengthOtherThan(@Nonnull CharSequence s, int size, MP1 msgParamOnly) {
		Null.nonNullArg(s, "s");
		return Predicates.lengthOtherThan(s, size);
	}

	/** "Special" predicate: String <%s> must NOT be %d characters long (actual: %d).*/
	public static <MP1> @Nullable String lengthOtherThanEx(@Nonnull CharSequence s, int size, MP1 msgParamOnly) {
		Null.nonNullArg(s, "s");
		return Predicates.lengthOtherThanEx(s, size);
	}

	/** Predicate: String <'%s'> must start with <'%s'>.*/
	public static <MP1> boolean startWith(@Nonnull String n, @Nonnull String a1, MP1 msgParamOnly) {
		Null.nonNullArg(n, "n");
		Null.nonNullArg(a1, "a1");
		return Predicates.startWith(n, a1);
	}

	/** "Special" predicate: String <'%s'> must start with <'%s'>.*/
	public static <MP1> @Nullable String startWithEx(@Nonnull String n, @Nonnull String a1, MP1 msgParamOnly) {
		Null.nonNullArg(n, "n");
		Null.nonNullArg(a1, "a1");
		return Predicates.startWithEx(n, a1);
	}

	/** Predicate: String <'%s'> must NOT start with <'%s'>.*/
	public static <MP1> boolean notStartWith(@Nonnull String n, @Nonnull String a1, MP1 msgParamOnly) {
		Null.nonNullArg(n, "n");
		Null.nonNullArg(a1, "a1");
		return Predicates.notStartWith(n, a1);
	}

	/** "Special" predicate: String <'%s'> must NOT start with <'%s'>.*/
	public static <MP1> @Nullable String notStartWithEx(@Nonnull String n, @Nonnull String a1, MP1 msgParamOnly) {
		Null.nonNullArg(n, "n");
		Null.nonNullArg(a1, "a1");
		return Predicates.notStartWithEx(n, a1);
	}

	/** Predicate: String <'%s'> must end with <'%s'>.*/
	public static <MP1> boolean endWith(@Nonnull String n, @Nonnull String a1, MP1 msgParamOnly) {
		Null.nonNullArg(n, "n");
		Null.nonNullArg(a1, "a1");
		return Predicates.endWith(n, a1);
	}

	/** "Special" predicate: String <'%s'> must end with <'%s'>.*/
	public static <MP1> @Nullable String endWithEx(@Nonnull String n, @Nonnull String a1, MP1 msgParamOnly) {
		Null.nonNullArg(n, "n");
		Null.nonNullArg(a1, "a1");
		return Predicates.endWithEx(n, a1);
	}

	/** Predicate: String <'%s'> must NOT end with <'%s'>.*/
	public static <MP1> boolean notEndWith(@Nonnull String n, @Nonnull String a1, MP1 msgParamOnly) {
		Null.nonNullArg(n, "n");
		Null.nonNullArg(a1, "a1");
		return Predicates.notEndWith(n, a1);
	}

	/** "Special" predicate: String <'%s'> must NOT end with <'%s'>.*/
	public static <MP1> @Nullable String notEndWithEx(@Nonnull String n, @Nonnull String a1, MP1 msgParamOnly) {
		Null.nonNullArg(n, "n");
		Null.nonNullArg(a1, "a1");
		return Predicates.notEndWithEx(n, a1);
	}

	/** Predicate: String <'%s'> must contain string <'%s'>. But does not.*/
	public static <MP1> boolean contain(@Nonnull String n, @Nonnull String a1, MP1 msgParamOnly) {
		Null.nonNullArg(n, "n");
		Null.nonNullArg(a1, "a1");
		return Predicates.contain(n, a1);
	}

	/** "Special" predicate: String <'%s'> must contain string <'%s'>. But does not.*/
	public static <MP1> @Nullable String containEx(@Nonnull String n, @Nonnull String a1, MP1 msgParamOnly) {
		Null.nonNullArg(n, "n");
		Null.nonNullArg(a1, "a1");
		return Predicates.containEx(n, a1);
	}

	/** Predicate: String <'%s'> must NOT contain string <'%s'>. But does not.*/
	public static <MP1> boolean notContain(@Nonnull String n, @Nonnull String a1, MP1 msgParamOnly) {
		Null.nonNullArg(n, "n");
		Null.nonNullArg(a1, "a1");
		return Predicates.notContain(n, a1);
	}

	/** "Special" predicate: String <'%s'> must NOT contain string <'%s'>. But does not.*/
	public static <MP1> @Nullable String notContainEx(@Nonnull String n, @Nonnull String a1, MP1 msgParamOnly) {
		Null.nonNullArg(n, "n");
		Null.nonNullArg(a1, "a1");
		return Predicates.notContainEx(n, a1);
	}

	/** Predicate: String <%s> must be empty (actual: %d).*/
	public static <MP1> boolean empty(@Nonnull String n, MP1 msgParamOnly) {
		Null.nonNullArg(n, "n");
		return Predicates.empty(n);
	}

	/** "Special" predicate: String <%s> must be empty (actual: %d).*/
	public static <MP1> @Nullable String emptyEx(@Nonnull String n, MP1 msgParamOnly) {
		Null.nonNullArg(n, "n");
		return Predicates.emptyEx(n);
	}

	/** Predicate: String <%s> must NOT be empty (actual: %d).*/
	public static <MP1> boolean notEmpty(@Nonnull String n, MP1 msgParamOnly) {
		Null.nonNullArg(n, "n");
		return Predicates.notEmpty(n);
	}

	/** "Special" predicate: String <%s> must NOT be empty (actual: %d).*/
	public static <MP1> @Nullable String notEmptyEx(@Nonnull String n, MP1 msgParamOnly) {
		Null.nonNullArg(n, "n");
		return Predicates.notEmptyEx(n);
	}

	/** Predicate: String <%s> must be blank (empty or consisting of only white characters; actual: %d).*/
	public static <MP1> boolean blank(@Nonnull String n, MP1 msgParamOnly) {
		Null.nonNullArg(n, "n");
		return Predicates.blank(n);
	}

	/** "Special" predicate: String <%s> must be blank (empty or consisting of only white characters; actual: %d).*/
	public static <MP1> @Nullable String blankEx(@Nonnull String n, MP1 msgParamOnly) {
		Null.nonNullArg(n, "n");
		return Predicates.blankEx(n);
	}

	/** Predicate: String <%s> must NOT be blank (empty or consisting of only white characters; actual: %d).*/
	public static <MP1> boolean notBlank(@Nonnull String n, MP1 msgParamOnly) {
		Null.nonNullArg(n, "n");
		return Predicates.notBlank(n);
	}

	/** "Special" predicate: String <%s> must NOT be blank (empty or consisting of only white characters; actual: %d).*/
	public static <MP1> @Nullable String notBlankEx(@Nonnull String n, MP1 msgParamOnly) {
		Null.nonNullArg(n, "n");
		return Predicates.notBlankEx(n);
	}

	/** Predicate: String <%s> must be null or empty (actual: %d).*/
	public static <MP1> boolean nullOrEmpty(String n, MP1 msgParamOnly) {
		return Predicates.nullOrEmpty(n);
	}

	/** "Special" predicate: String <%s> must be null or empty (actual: %d).*/
	public static <MP1> @Nullable String nullOrEmptyEx(String n, MP1 msgParamOnly) {
		return Predicates.nullOrEmptyEx(n);
	}

	/** Predicate: String <%s> must NOT be null or empty (actual: %d).*/
	public static <MP1> boolean notNullNorEmpty(String n, MP1 msgParamOnly) {
		return Predicates.notNullNorEmpty(n);
	}

	/** "Special" predicate: String <%s> must NOT be null or empty (actual: %d).*/
	public static <MP1> @Nullable String notNullNorEmptyEx(String n, MP1 msgParamOnly) {
		return Predicates.notNullNorEmptyEx(n);
	}

	/** Predicate: String <%s> must be null or blank (actual %d).*/
	public static <MP1> boolean nullOrBlank(String n, MP1 msgParamOnly) {
		return Predicates.nullOrBlank(n);
	}

	/** "Special" predicate: String <%s> must be null or blank (actual %d).*/
	public static <MP1> @Nullable String nullOrBlankEx(String n, MP1 msgParamOnly) {
		return Predicates.nullOrBlankEx(n);
	}

	/** Predicate: String <%s> must NOT be null or blank (actual %d).*/
	public static <MP1> boolean notNullNorBlank(String n, MP1 msgParamOnly) {
		return Predicates.notNullNorBlank(n);
	}

	/** "Special" predicate: String <%s> must NOT be null or blank (actual %d).*/
	public static <MP1> @Nullable String notNullNorBlankEx(String n, MP1 msgParamOnly) {
		return Predicates.notNullNorBlankEx(n);
	}

	// </editor-fold>

	//<editor-fold desc="and/or/xor">

	/** Predicate: AND*/
	public static <MP1> boolean and(boolean op1, boolean op2, MP1 msgParamOnly) {
		return Predicates.and(op1, op2);
	}

	/** "Special" predicate: AND*/
	public static <MP1> @Nullable String andEx(boolean op1, boolean op2, MP1 msgParamOnly) {
		return Predicates.andEx(op1, op2);
	}

	/** Predicate: OR*/
	public static <MP1> boolean or(boolean op1, boolean op2, MP1 msgParamOnly) {
		return Predicates.or(op1, op2);
	}

	/** "Special" predicate: OR*/
	public static <MP1> @Nullable String orEx(boolean op1, boolean op2, MP1 msgParamOnly) {
		return Predicates.orEx(op1, op2);
	}

	/** Predicate: XOR*/
	public static <MP1> boolean xor(boolean op1, boolean op2, MP1 msgParamOnly) {
		return Predicates.xor(op1, op2);
	}

	/** "Special" predicate: XOR*/
	public static <MP1> @Nullable String xorEx(boolean op1, boolean op2, MP1 msgParamOnly) {
		return Predicates.xorEx(op1, op2);
	}

	//</editor-fold>

	//<editor-fold desc="== <= >= < >">

	/** Predicate: <%s> must be equal to <%s>.*/
	public static <MP1> boolean equal(Object o1, Object o2, MP1 msgParamOnly) {
		return Predicates.equal(o1, o2);
	}

	/** "Special" predicate: <%s> must be equal to <%s>.*/
	public static <MP1> @Nullable String equalEx(Object o1, Object o2, MP1 msgParamOnly) {
		return Predicates.equalEx(o1, o2);
	}

	/** Predicate: <%s> must NOT be equal to <%s>.*/
	public static <MP1> boolean notEqual(Object o1, Object o2, MP1 msgParamOnly) {
		return Predicates.notEqual(o1, o2);
	}

	/** "Special" predicate: <%s> must NOT be equal to <%s>.*/
	public static <MP1> @Nullable String notEqualEx(Object o1, Object o2, MP1 msgParamOnly) {
		return Predicates.notEqualEx(o1, o2);
	}

	/** Predicate: <%s> must be equal to <%s>.*/
	public static <MP1> boolean objEqual(Object o1, Object o2, MP1 msgParamOnly) {
		return Predicates.objEqual(o1, o2);
	}

	/** "Special" predicate: <%s> must be equal to <%s>.*/
	public static <MP1> @Nullable String objEqualEx(Object o1, Object o2, MP1 msgParamOnly) {
		return Predicates.objEqualEx(o1, o2);
	}

	/** Predicate: <%s> must NOT be equal to <%s>.*/
	public static <MP1> boolean objNotEqual(Object o1, Object o2, MP1 msgParamOnly) {
		return Predicates.objNotEqual(o1, o2);
	}

	/** "Special" predicate: <%s> must NOT be equal to <%s>.*/
	public static <MP1> @Nullable String objNotEqualEx(Object o1, Object o2, MP1 msgParamOnly) {
		return Predicates.objNotEqualEx(o1, o2);
	}

	/** Predicate: <%s> must be equal to <%s>.*/
	public static <MP1> boolean equalToObj(byte n, Object o2, MP1 msgParamOnly) {
		return Predicates.equalToObj(n, o2);
	}

	/** "Special" predicate: <%s> must be equal to <%s>.*/
	public static <MP1> @Nullable String equalToObjEx(byte n, Object o2, MP1 msgParamOnly) {
		return Predicates.equalToObjEx(n, o2);
	}

	/** Predicate: <%s> must NOT be equal to <%s>.*/
	public static <MP1> boolean notEqualToObj(byte n, Object o2, MP1 msgParamOnly) {
		return Predicates.notEqualToObj(n, o2);
	}

	/** "Special" predicate: <%s> must NOT be equal to <%s>.*/
	public static <MP1> @Nullable String notEqualToObjEx(byte n, Object o2, MP1 msgParamOnly) {
		return Predicates.notEqualToObjEx(n, o2);
	}

	/** Predicate: %s must be equal to %s.*/
	public static <MP1> boolean equal(byte n, byte a1, MP1 msgParamOnly) {
		return Predicates.equal(n, a1);
	}

	/** "Special" predicate: %s must be equal to %s.*/
	public static <MP1> @Nullable String equalEx(byte n, byte a1, MP1 msgParamOnly) {
		return Predicates.equalEx(n, a1);
	}

	/** Predicate: %s must NOT be equal to %s.*/
	public static <MP1> boolean notEqual(byte n, byte a1, MP1 msgParamOnly) {
		return Predicates.notEqual(n, a1);
	}

	/** "Special" predicate: %s must NOT be equal to %s.*/
	public static <MP1> @Nullable String notEqualEx(byte n, byte a1, MP1 msgParamOnly) {
		return Predicates.notEqualEx(n, a1);
	}

	/** Predicate: %s must be > %s.*/
	public static <MP1> boolean gt(byte n, byte a1, MP1 msgParamOnly) {
		return Predicates.gt(n, a1);
	}

	/** "Special" predicate: %s must be > %s.*/
	public static <MP1> @Nullable String gtEx(byte n, byte a1, MP1 msgParamOnly) {
		return Predicates.gtEx(n, a1);
	}

	/** Predicate: %s must NOT be > %s.*/
	public static <MP1> boolean notGt(byte n, byte a1, MP1 msgParamOnly) {
		return Predicates.notGt(n, a1);
	}

	/** "Special" predicate: %s must NOT be > %s.*/
	public static <MP1> @Nullable String notGtEx(byte n, byte a1, MP1 msgParamOnly) {
		return Predicates.notGtEx(n, a1);
	}

	/** Predicate: %s must be < %s.*/
	public static <MP1> boolean lt(byte n, byte a1, MP1 msgParamOnly) {
		return Predicates.lt(n, a1);
	}

	/** "Special" predicate: %s must be < %s.*/
	public static <MP1> @Nullable String ltEx(byte n, byte a1, MP1 msgParamOnly) {
		return Predicates.ltEx(n, a1);
	}

	/** Predicate: %s must NOT be < %s.*/
	public static <MP1> boolean notLt(byte n, byte a1, MP1 msgParamOnly) {
		return Predicates.notLt(n, a1);
	}

	/** "Special" predicate: %s must NOT be < %s.*/
	public static <MP1> @Nullable String notLtEx(byte n, byte a1, MP1 msgParamOnly) {
		return Predicates.notLtEx(n, a1);
	}

	/** Predicate: %s must be >= %s.*/
	public static <MP1> boolean gtEq(byte n, byte a1, MP1 msgParamOnly) {
		return Predicates.gtEq(n, a1);
	}

	/** "Special" predicate: %s must be >= %s.*/
	public static <MP1> @Nullable String gtEqEx(byte n, byte a1, MP1 msgParamOnly) {
		return Predicates.gtEqEx(n, a1);
	}

	/** Predicate: %s must NOT be >= %s.*/
	public static <MP1> boolean notGtEq(byte n, byte a1, MP1 msgParamOnly) {
		return Predicates.notGtEq(n, a1);
	}

	/** "Special" predicate: %s must NOT be >= %s.*/
	public static <MP1> @Nullable String notGtEqEx(byte n, byte a1, MP1 msgParamOnly) {
		return Predicates.notGtEqEx(n, a1);
	}

	/** Predicate: %s must be <= %s.*/
	public static <MP1> boolean ltEq(byte n, byte a1, MP1 msgParamOnly) {
		return Predicates.ltEq(n, a1);
	}

	/** "Special" predicate: %s must be <= %s.*/
	public static <MP1> @Nullable String ltEqEx(byte n, byte a1, MP1 msgParamOnly) {
		return Predicates.ltEqEx(n, a1);
	}

	/** Predicate: %s must NOT be <= %s.*/
	public static <MP1> boolean notLtEq(byte n, byte a1, MP1 msgParamOnly) {
		return Predicates.notLtEq(n, a1);
	}

	/** "Special" predicate: %s must NOT be <= %s.*/
	public static <MP1> @Nullable String notLtEqEx(byte n, byte a1, MP1 msgParamOnly) {
		return Predicates.notLtEqEx(n, a1);
	}

	/** Predicate: %s must be >= 0 (must be non-negative).*/
	public static <MP1> boolean nonNegative(byte n, MP1 msgParamOnly) {
		return Predicates.nonNegative(n);
	}

	/** "Special" predicate: %s must be >= 0 (must be non-negative).*/
	public static <MP1> @Nullable String nonNegativeEx(byte n, MP1 msgParamOnly) {
		return Predicates.nonNegativeEx(n);
	}

	/** Predicate: %s must NOT be >= 0 (must be negative).*/
	public static <MP1> boolean negative(byte n, MP1 msgParamOnly) {
		return Predicates.negative(n);
	}

	/** "Special" predicate: %s must NOT be >= 0 (must be negative).*/
	public static <MP1> @Nullable String negativeEx(byte n, MP1 msgParamOnly) {
		return Predicates.negativeEx(n);
	}

	/** Predicate: %s must be <= 0 (must be non-positive).*/
	public static <MP1> boolean nonPositive(byte n, MP1 msgParamOnly) {
		return Predicates.nonPositive(n);
	}

	/** "Special" predicate: %s must be <= 0 (must be non-positive).*/
	public static <MP1> @Nullable String nonPositiveEx(byte n, MP1 msgParamOnly) {
		return Predicates.nonPositiveEx(n);
	}

	/** Predicate: %s must NOT be <= 0 (must be positive).*/
	public static <MP1> boolean positive(byte n, MP1 msgParamOnly) {
		return Predicates.positive(n);
	}

	/** "Special" predicate: %s must NOT be <= 0 (must be positive).*/
	public static <MP1> @Nullable String positiveEx(byte n, MP1 msgParamOnly) {
		return Predicates.positiveEx(n);
	}

	/** Predicate: <%s> must be equal to <%s>.*/
	public static <MP1> boolean equalToObj(short n, Object o2, MP1 msgParamOnly) {
		return Predicates.equalToObj(n, o2);
	}

	/** "Special" predicate: <%s> must be equal to <%s>.*/
	public static <MP1> @Nullable String equalToObjEx(short n, Object o2, MP1 msgParamOnly) {
		return Predicates.equalToObjEx(n, o2);
	}

	/** Predicate: <%s> must NOT be equal to <%s>.*/
	public static <MP1> boolean notEqualToObj(short n, Object o2, MP1 msgParamOnly) {
		return Predicates.notEqualToObj(n, o2);
	}

	/** "Special" predicate: <%s> must NOT be equal to <%s>.*/
	public static <MP1> @Nullable String notEqualToObjEx(short n, Object o2, MP1 msgParamOnly) {
		return Predicates.notEqualToObjEx(n, o2);
	}

	/** Predicate: %s must be equal to %s.*/
	public static <MP1> boolean equal(short n, short a1, MP1 msgParamOnly) {
		return Predicates.equal(n, a1);
	}

	/** "Special" predicate: %s must be equal to %s.*/
	public static <MP1> @Nullable String equalEx(short n, short a1, MP1 msgParamOnly) {
		return Predicates.equalEx(n, a1);
	}

	/** Predicate: %s must NOT be equal to %s.*/
	public static <MP1> boolean notEqual(short n, short a1, MP1 msgParamOnly) {
		return Predicates.notEqual(n, a1);
	}

	/** "Special" predicate: %s must NOT be equal to %s.*/
	public static <MP1> @Nullable String notEqualEx(short n, short a1, MP1 msgParamOnly) {
		return Predicates.notEqualEx(n, a1);
	}

	/** Predicate: %s must be > %s.*/
	public static <MP1> boolean gt(short n, short a1, MP1 msgParamOnly) {
		return Predicates.gt(n, a1);
	}

	/** "Special" predicate: %s must be > %s.*/
	public static <MP1> @Nullable String gtEx(short n, short a1, MP1 msgParamOnly) {
		return Predicates.gtEx(n, a1);
	}

	/** Predicate: %s must NOT be > %s.*/
	public static <MP1> boolean notGt(short n, short a1, MP1 msgParamOnly) {
		return Predicates.notGt(n, a1);
	}

	/** "Special" predicate: %s must NOT be > %s.*/
	public static <MP1> @Nullable String notGtEx(short n, short a1, MP1 msgParamOnly) {
		return Predicates.notGtEx(n, a1);
	}

	/** Predicate: %s must be < %s.*/
	public static <MP1> boolean lt(short n, short a1, MP1 msgParamOnly) {
		return Predicates.lt(n, a1);
	}

	/** "Special" predicate: %s must be < %s.*/
	public static <MP1> @Nullable String ltEx(short n, short a1, MP1 msgParamOnly) {
		return Predicates.ltEx(n, a1);
	}

	/** Predicate: %s must NOT be < %s.*/
	public static <MP1> boolean notLt(short n, short a1, MP1 msgParamOnly) {
		return Predicates.notLt(n, a1);
	}

	/** "Special" predicate: %s must NOT be < %s.*/
	public static <MP1> @Nullable String notLtEx(short n, short a1, MP1 msgParamOnly) {
		return Predicates.notLtEx(n, a1);
	}

	/** Predicate: %s must be >= %s.*/
	public static <MP1> boolean gtEq(short n, short a1, MP1 msgParamOnly) {
		return Predicates.gtEq(n, a1);
	}

	/** "Special" predicate: %s must be >= %s.*/
	public static <MP1> @Nullable String gtEqEx(short n, short a1, MP1 msgParamOnly) {
		return Predicates.gtEqEx(n, a1);
	}

	/** Predicate: %s must NOT be >= %s.*/
	public static <MP1> boolean notGtEq(short n, short a1, MP1 msgParamOnly) {
		return Predicates.notGtEq(n, a1);
	}

	/** "Special" predicate: %s must NOT be >= %s.*/
	public static <MP1> @Nullable String notGtEqEx(short n, short a1, MP1 msgParamOnly) {
		return Predicates.notGtEqEx(n, a1);
	}

	/** Predicate: %s must be <= %s.*/
	public static <MP1> boolean ltEq(short n, short a1, MP1 msgParamOnly) {
		return Predicates.ltEq(n, a1);
	}

	/** "Special" predicate: %s must be <= %s.*/
	public static <MP1> @Nullable String ltEqEx(short n, short a1, MP1 msgParamOnly) {
		return Predicates.ltEqEx(n, a1);
	}

	/** Predicate: %s must NOT be <= %s.*/
	public static <MP1> boolean notLtEq(short n, short a1, MP1 msgParamOnly) {
		return Predicates.notLtEq(n, a1);
	}

	/** "Special" predicate: %s must NOT be <= %s.*/
	public static <MP1> @Nullable String notLtEqEx(short n, short a1, MP1 msgParamOnly) {
		return Predicates.notLtEqEx(n, a1);
	}

	/** Predicate: %s must be >= 0 (must be non-negative).*/
	public static <MP1> boolean nonNegative(short n, MP1 msgParamOnly) {
		return Predicates.nonNegative(n);
	}

	/** "Special" predicate: %s must be >= 0 (must be non-negative).*/
	public static <MP1> @Nullable String nonNegativeEx(short n, MP1 msgParamOnly) {
		return Predicates.nonNegativeEx(n);
	}

	/** Predicate: %s must NOT be >= 0 (must be negative).*/
	public static <MP1> boolean negative(short n, MP1 msgParamOnly) {
		return Predicates.negative(n);
	}

	/** "Special" predicate: %s must NOT be >= 0 (must be negative).*/
	public static <MP1> @Nullable String negativeEx(short n, MP1 msgParamOnly) {
		return Predicates.negativeEx(n);
	}

	/** Predicate: %s must be <= 0 (must be non-positive).*/
	public static <MP1> boolean nonPositive(short n, MP1 msgParamOnly) {
		return Predicates.nonPositive(n);
	}

	/** "Special" predicate: %s must be <= 0 (must be non-positive).*/
	public static <MP1> @Nullable String nonPositiveEx(short n, MP1 msgParamOnly) {
		return Predicates.nonPositiveEx(n);
	}

	/** Predicate: %s must NOT be <= 0 (must be positive).*/
	public static <MP1> boolean positive(short n, MP1 msgParamOnly) {
		return Predicates.positive(n);
	}

	/** "Special" predicate: %s must NOT be <= 0 (must be positive).*/
	public static <MP1> @Nullable String positiveEx(short n, MP1 msgParamOnly) {
		return Predicates.positiveEx(n);
	}

	/** Predicate: <%s> must be equal to <%s>.*/
	public static <MP1> boolean equalToObj(int n, Object o2, MP1 msgParamOnly) {
		return Predicates.equalToObj(n, o2);
	}

	/** "Special" predicate: <%s> must be equal to <%s>.*/
	public static <MP1> @Nullable String equalToObjEx(int n, Object o2, MP1 msgParamOnly) {
		return Predicates.equalToObjEx(n, o2);
	}

	/** Predicate: <%s> must NOT be equal to <%s>.*/
	public static <MP1> boolean notEqualToObj(int n, Object o2, MP1 msgParamOnly) {
		return Predicates.notEqualToObj(n, o2);
	}

	/** "Special" predicate: <%s> must NOT be equal to <%s>.*/
	public static <MP1> @Nullable String notEqualToObjEx(int n, Object o2, MP1 msgParamOnly) {
		return Predicates.notEqualToObjEx(n, o2);
	}

	/** Predicate: %s must be equal to %s.*/
	public static <MP1> boolean equal(int n, int a1, MP1 msgParamOnly) {
		return Predicates.equal(n, a1);
	}

	/** "Special" predicate: %s must be equal to %s.*/
	public static <MP1> @Nullable String equalEx(int n, int a1, MP1 msgParamOnly) {
		return Predicates.equalEx(n, a1);
	}

	/** Predicate: %s must NOT be equal to %s.*/
	public static <MP1> boolean notEqual(int n, int a1, MP1 msgParamOnly) {
		return Predicates.notEqual(n, a1);
	}

	/** "Special" predicate: %s must NOT be equal to %s.*/
	public static <MP1> @Nullable String notEqualEx(int n, int a1, MP1 msgParamOnly) {
		return Predicates.notEqualEx(n, a1);
	}

	/** Predicate: %s must be > %s.*/
	public static <MP1> boolean gt(int n, int a1, MP1 msgParamOnly) {
		return Predicates.gt(n, a1);
	}

	/** "Special" predicate: %s must be > %s.*/
	public static <MP1> @Nullable String gtEx(int n, int a1, MP1 msgParamOnly) {
		return Predicates.gtEx(n, a1);
	}

	/** Predicate: %s must NOT be > %s.*/
	public static <MP1> boolean notGt(int n, int a1, MP1 msgParamOnly) {
		return Predicates.notGt(n, a1);
	}

	/** "Special" predicate: %s must NOT be > %s.*/
	public static <MP1> @Nullable String notGtEx(int n, int a1, MP1 msgParamOnly) {
		return Predicates.notGtEx(n, a1);
	}

	/** Predicate: %s must be < %s.*/
	public static <MP1> boolean lt(int n, int a1, MP1 msgParamOnly) {
		return Predicates.lt(n, a1);
	}

	/** "Special" predicate: %s must be < %s.*/
	public static <MP1> @Nullable String ltEx(int n, int a1, MP1 msgParamOnly) {
		return Predicates.ltEx(n, a1);
	}

	/** Predicate: %s must NOT be < %s.*/
	public static <MP1> boolean notLt(int n, int a1, MP1 msgParamOnly) {
		return Predicates.notLt(n, a1);
	}

	/** "Special" predicate: %s must NOT be < %s.*/
	public static <MP1> @Nullable String notLtEx(int n, int a1, MP1 msgParamOnly) {
		return Predicates.notLtEx(n, a1);
	}

	/** Predicate: %s must be >= %s.*/
	public static <MP1> boolean gtEq(int n, int a1, MP1 msgParamOnly) {
		return Predicates.gtEq(n, a1);
	}

	/** "Special" predicate: %s must be >= %s.*/
	public static <MP1> @Nullable String gtEqEx(int n, int a1, MP1 msgParamOnly) {
		return Predicates.gtEqEx(n, a1);
	}

	/** Predicate: %s must NOT be >= %s.*/
	public static <MP1> boolean notGtEq(int n, int a1, MP1 msgParamOnly) {
		return Predicates.notGtEq(n, a1);
	}

	/** "Special" predicate: %s must NOT be >= %s.*/
	public static <MP1> @Nullable String notGtEqEx(int n, int a1, MP1 msgParamOnly) {
		return Predicates.notGtEqEx(n, a1);
	}

	/** Predicate: %s must be <= %s.*/
	public static <MP1> boolean ltEq(int n, int a1, MP1 msgParamOnly) {
		return Predicates.ltEq(n, a1);
	}

	/** "Special" predicate: %s must be <= %s.*/
	public static <MP1> @Nullable String ltEqEx(int n, int a1, MP1 msgParamOnly) {
		return Predicates.ltEqEx(n, a1);
	}

	/** Predicate: %s must NOT be <= %s.*/
	public static <MP1> boolean notLtEq(int n, int a1, MP1 msgParamOnly) {
		return Predicates.notLtEq(n, a1);
	}

	/** "Special" predicate: %s must NOT be <= %s.*/
	public static <MP1> @Nullable String notLtEqEx(int n, int a1, MP1 msgParamOnly) {
		return Predicates.notLtEqEx(n, a1);
	}

	/** Predicate: %s must be >= 0 (must be non-negative).*/
	public static <MP1> boolean nonNegative(int n, MP1 msgParamOnly) {
		return Predicates.nonNegative(n);
	}

	/** "Special" predicate: %s must be >= 0 (must be non-negative).*/
	public static <MP1> @Nullable String nonNegativeEx(int n, MP1 msgParamOnly) {
		return Predicates.nonNegativeEx(n);
	}

	/** Predicate: %s must NOT be >= 0 (must be negative).*/
	public static <MP1> boolean negative(int n, MP1 msgParamOnly) {
		return Predicates.negative(n);
	}

	/** "Special" predicate: %s must NOT be >= 0 (must be negative).*/
	public static <MP1> @Nullable String negativeEx(int n, MP1 msgParamOnly) {
		return Predicates.negativeEx(n);
	}

	/** Predicate: %s must be <= 0 (must be non-positive).*/
	public static <MP1> boolean nonPositive(int n, MP1 msgParamOnly) {
		return Predicates.nonPositive(n);
	}

	/** "Special" predicate: %s must be <= 0 (must be non-positive).*/
	public static <MP1> @Nullable String nonPositiveEx(int n, MP1 msgParamOnly) {
		return Predicates.nonPositiveEx(n);
	}

	/** Predicate: %s must NOT be <= 0 (must be positive).*/
	public static <MP1> boolean positive(int n, MP1 msgParamOnly) {
		return Predicates.positive(n);
	}

	/** "Special" predicate: %s must NOT be <= 0 (must be positive).*/
	public static <MP1> @Nullable String positiveEx(int n, MP1 msgParamOnly) {
		return Predicates.positiveEx(n);
	}

	/** Predicate: <%s> must be equal to <%s>.*/
	public static <MP1> boolean equalToObj(long n, Object o2, MP1 msgParamOnly) {
		return Predicates.equalToObj(n, o2);
	}

	/** "Special" predicate: <%s> must be equal to <%s>.*/
	public static <MP1> @Nullable String equalToObjEx(long n, Object o2, MP1 msgParamOnly) {
		return Predicates.equalToObjEx(n, o2);
	}

	/** Predicate: <%s> must NOT be equal to <%s>.*/
	public static <MP1> boolean notEqualToObj(long n, Object o2, MP1 msgParamOnly) {
		return Predicates.notEqualToObj(n, o2);
	}

	/** "Special" predicate: <%s> must NOT be equal to <%s>.*/
	public static <MP1> @Nullable String notEqualToObjEx(long n, Object o2, MP1 msgParamOnly) {
		return Predicates.notEqualToObjEx(n, o2);
	}

	/** Predicate: %s must be equal to %s.*/
	public static <MP1> boolean equal(long n, long a1, MP1 msgParamOnly) {
		return Predicates.equal(n, a1);
	}

	/** "Special" predicate: %s must be equal to %s.*/
	public static <MP1> @Nullable String equalEx(long n, long a1, MP1 msgParamOnly) {
		return Predicates.equalEx(n, a1);
	}

	/** Predicate: %s must NOT be equal to %s.*/
	public static <MP1> boolean notEqual(long n, long a1, MP1 msgParamOnly) {
		return Predicates.notEqual(n, a1);
	}

	/** "Special" predicate: %s must NOT be equal to %s.*/
	public static <MP1> @Nullable String notEqualEx(long n, long a1, MP1 msgParamOnly) {
		return Predicates.notEqualEx(n, a1);
	}

	/** Predicate: %s must be > %s.*/
	public static <MP1> boolean gt(long n, long a1, MP1 msgParamOnly) {
		return Predicates.gt(n, a1);
	}

	/** "Special" predicate: %s must be > %s.*/
	public static <MP1> @Nullable String gtEx(long n, long a1, MP1 msgParamOnly) {
		return Predicates.gtEx(n, a1);
	}

	/** Predicate: %s must NOT be > %s.*/
	public static <MP1> boolean notGt(long n, long a1, MP1 msgParamOnly) {
		return Predicates.notGt(n, a1);
	}

	/** "Special" predicate: %s must NOT be > %s.*/
	public static <MP1> @Nullable String notGtEx(long n, long a1, MP1 msgParamOnly) {
		return Predicates.notGtEx(n, a1);
	}

	/** Predicate: %s must be < %s.*/
	public static <MP1> boolean lt(long n, long a1, MP1 msgParamOnly) {
		return Predicates.lt(n, a1);
	}

	/** "Special" predicate: %s must be < %s.*/
	public static <MP1> @Nullable String ltEx(long n, long a1, MP1 msgParamOnly) {
		return Predicates.ltEx(n, a1);
	}

	/** Predicate: %s must NOT be < %s.*/
	public static <MP1> boolean notLt(long n, long a1, MP1 msgParamOnly) {
		return Predicates.notLt(n, a1);
	}

	/** "Special" predicate: %s must NOT be < %s.*/
	public static <MP1> @Nullable String notLtEx(long n, long a1, MP1 msgParamOnly) {
		return Predicates.notLtEx(n, a1);
	}

	/** Predicate: %s must be >= %s.*/
	public static <MP1> boolean gtEq(long n, long a1, MP1 msgParamOnly) {
		return Predicates.gtEq(n, a1);
	}

	/** "Special" predicate: %s must be >= %s.*/
	public static <MP1> @Nullable String gtEqEx(long n, long a1, MP1 msgParamOnly) {
		return Predicates.gtEqEx(n, a1);
	}

	/** Predicate: %s must NOT be >= %s.*/
	public static <MP1> boolean notGtEq(long n, long a1, MP1 msgParamOnly) {
		return Predicates.notGtEq(n, a1);
	}

	/** "Special" predicate: %s must NOT be >= %s.*/
	public static <MP1> @Nullable String notGtEqEx(long n, long a1, MP1 msgParamOnly) {
		return Predicates.notGtEqEx(n, a1);
	}

	/** Predicate: %s must be <= %s.*/
	public static <MP1> boolean ltEq(long n, long a1, MP1 msgParamOnly) {
		return Predicates.ltEq(n, a1);
	}

	/** "Special" predicate: %s must be <= %s.*/
	public static <MP1> @Nullable String ltEqEx(long n, long a1, MP1 msgParamOnly) {
		return Predicates.ltEqEx(n, a1);
	}

	/** Predicate: %s must NOT be <= %s.*/
	public static <MP1> boolean notLtEq(long n, long a1, MP1 msgParamOnly) {
		return Predicates.notLtEq(n, a1);
	}

	/** "Special" predicate: %s must NOT be <= %s.*/
	public static <MP1> @Nullable String notLtEqEx(long n, long a1, MP1 msgParamOnly) {
		return Predicates.notLtEqEx(n, a1);
	}

	/** Predicate: %s must be >= 0 (must be non-negative).*/
	public static <MP1> boolean nonNegative(long n, MP1 msgParamOnly) {
		return Predicates.nonNegative(n);
	}

	/** "Special" predicate: %s must be >= 0 (must be non-negative).*/
	public static <MP1> @Nullable String nonNegativeEx(long n, MP1 msgParamOnly) {
		return Predicates.nonNegativeEx(n);
	}

	/** Predicate: %s must NOT be >= 0 (must be negative).*/
	public static <MP1> boolean negative(long n, MP1 msgParamOnly) {
		return Predicates.negative(n);
	}

	/** "Special" predicate: %s must NOT be >= 0 (must be negative).*/
	public static <MP1> @Nullable String negativeEx(long n, MP1 msgParamOnly) {
		return Predicates.negativeEx(n);
	}

	/** Predicate: %s must be <= 0 (must be non-positive).*/
	public static <MP1> boolean nonPositive(long n, MP1 msgParamOnly) {
		return Predicates.nonPositive(n);
	}

	/** "Special" predicate: %s must be <= 0 (must be non-positive).*/
	public static <MP1> @Nullable String nonPositiveEx(long n, MP1 msgParamOnly) {
		return Predicates.nonPositiveEx(n);
	}

	/** Predicate: %s must NOT be <= 0 (must be positive).*/
	public static <MP1> boolean positive(long n, MP1 msgParamOnly) {
		return Predicates.positive(n);
	}

	/** "Special" predicate: %s must NOT be <= 0 (must be positive).*/
	public static <MP1> @Nullable String positiveEx(long n, MP1 msgParamOnly) {
		return Predicates.positiveEx(n);
	}

	/** Predicate: <%s> must be equal to <%s>.*/
	public static <MP1> boolean equalToObj(float n, Object o2, MP1 msgParamOnly) {
		return Predicates.equalToObj(n, o2);
	}

	/** "Special" predicate: <%s> must be equal to <%s>.*/
	public static <MP1> @Nullable String equalToObjEx(float n, Object o2, MP1 msgParamOnly) {
		return Predicates.equalToObjEx(n, o2);
	}

	/** Predicate: <%s> must NOT be equal to <%s>.*/
	public static <MP1> boolean notEqualToObj(float n, Object o2, MP1 msgParamOnly) {
		return Predicates.notEqualToObj(n, o2);
	}

	/** "Special" predicate: <%s> must NOT be equal to <%s>.*/
	public static <MP1> @Nullable String notEqualToObjEx(float n, Object o2, MP1 msgParamOnly) {
		return Predicates.notEqualToObjEx(n, o2);
	}

	/** Predicate: %s must be equal to %s.*/
	public static <MP1> boolean equal(float n, float a1, MP1 msgParamOnly) {
		return Predicates.equal(n, a1);
	}

	/** "Special" predicate: %s must be equal to %s.*/
	public static <MP1> @Nullable String equalEx(float n, float a1, MP1 msgParamOnly) {
		return Predicates.equalEx(n, a1);
	}

	/** Predicate: %s must NOT be equal to %s.*/
	public static <MP1> boolean notEqual(float n, float a1, MP1 msgParamOnly) {
		return Predicates.notEqual(n, a1);
	}

	/** "Special" predicate: %s must NOT be equal to %s.*/
	public static <MP1> @Nullable String notEqualEx(float n, float a1, MP1 msgParamOnly) {
		return Predicates.notEqualEx(n, a1);
	}

	/** Predicate: %s must be > %s.*/
	public static <MP1> boolean gt(float n, float a1, MP1 msgParamOnly) {
		return Predicates.gt(n, a1);
	}

	/** "Special" predicate: %s must be > %s.*/
	public static <MP1> @Nullable String gtEx(float n, float a1, MP1 msgParamOnly) {
		return Predicates.gtEx(n, a1);
	}

	/** Predicate: %s must NOT be > %s.*/
	public static <MP1> boolean notGt(float n, float a1, MP1 msgParamOnly) {
		return Predicates.notGt(n, a1);
	}

	/** "Special" predicate: %s must NOT be > %s.*/
	public static <MP1> @Nullable String notGtEx(float n, float a1, MP1 msgParamOnly) {
		return Predicates.notGtEx(n, a1);
	}

	/** Predicate: %s must be < %s.*/
	public static <MP1> boolean lt(float n, float a1, MP1 msgParamOnly) {
		return Predicates.lt(n, a1);
	}

	/** "Special" predicate: %s must be < %s.*/
	public static <MP1> @Nullable String ltEx(float n, float a1, MP1 msgParamOnly) {
		return Predicates.ltEx(n, a1);
	}

	/** Predicate: %s must NOT be < %s.*/
	public static <MP1> boolean notLt(float n, float a1, MP1 msgParamOnly) {
		return Predicates.notLt(n, a1);
	}

	/** "Special" predicate: %s must NOT be < %s.*/
	public static <MP1> @Nullable String notLtEx(float n, float a1, MP1 msgParamOnly) {
		return Predicates.notLtEx(n, a1);
	}

	/** Predicate: %s must be >= %s.*/
	public static <MP1> boolean gtEq(float n, float a1, MP1 msgParamOnly) {
		return Predicates.gtEq(n, a1);
	}

	/** "Special" predicate: %s must be >= %s.*/
	public static <MP1> @Nullable String gtEqEx(float n, float a1, MP1 msgParamOnly) {
		return Predicates.gtEqEx(n, a1);
	}

	/** Predicate: %s must NOT be >= %s.*/
	public static <MP1> boolean notGtEq(float n, float a1, MP1 msgParamOnly) {
		return Predicates.notGtEq(n, a1);
	}

	/** "Special" predicate: %s must NOT be >= %s.*/
	public static <MP1> @Nullable String notGtEqEx(float n, float a1, MP1 msgParamOnly) {
		return Predicates.notGtEqEx(n, a1);
	}

	/** Predicate: %s must be <= %s.*/
	public static <MP1> boolean ltEq(float n, float a1, MP1 msgParamOnly) {
		return Predicates.ltEq(n, a1);
	}

	/** "Special" predicate: %s must be <= %s.*/
	public static <MP1> @Nullable String ltEqEx(float n, float a1, MP1 msgParamOnly) {
		return Predicates.ltEqEx(n, a1);
	}

	/** Predicate: %s must NOT be <= %s.*/
	public static <MP1> boolean notLtEq(float n, float a1, MP1 msgParamOnly) {
		return Predicates.notLtEq(n, a1);
	}

	/** "Special" predicate: %s must NOT be <= %s.*/
	public static <MP1> @Nullable String notLtEqEx(float n, float a1, MP1 msgParamOnly) {
		return Predicates.notLtEqEx(n, a1);
	}

	/** Predicate: %s must be >= 0 (must be non-negative).*/
	public static <MP1> boolean nonNegative(float n, MP1 msgParamOnly) {
		return Predicates.nonNegative(n);
	}

	/** "Special" predicate: %s must be >= 0 (must be non-negative).*/
	public static <MP1> @Nullable String nonNegativeEx(float n, MP1 msgParamOnly) {
		return Predicates.nonNegativeEx(n);
	}

	/** Predicate: %s must NOT be >= 0 (must be negative).*/
	public static <MP1> boolean negative(float n, MP1 msgParamOnly) {
		return Predicates.negative(n);
	}

	/** "Special" predicate: %s must NOT be >= 0 (must be negative).*/
	public static <MP1> @Nullable String negativeEx(float n, MP1 msgParamOnly) {
		return Predicates.negativeEx(n);
	}

	/** Predicate: %s must be <= 0 (must be non-positive).*/
	public static <MP1> boolean nonPositive(float n, MP1 msgParamOnly) {
		return Predicates.nonPositive(n);
	}

	/** "Special" predicate: %s must be <= 0 (must be non-positive).*/
	public static <MP1> @Nullable String nonPositiveEx(float n, MP1 msgParamOnly) {
		return Predicates.nonPositiveEx(n);
	}

	/** Predicate: %s must NOT be <= 0 (must be positive).*/
	public static <MP1> boolean positive(float n, MP1 msgParamOnly) {
		return Predicates.positive(n);
	}

	/** "Special" predicate: %s must NOT be <= 0 (must be positive).*/
	public static <MP1> @Nullable String positiveEx(float n, MP1 msgParamOnly) {
		return Predicates.positiveEx(n);
	}

	/** Predicate: <%s> must be equal to <%s>.*/
	public static <MP1> boolean equalToObj(double n, Object o2, MP1 msgParamOnly) {
		return Predicates.equalToObj(n, o2);
	}

	/** "Special" predicate: <%s> must be equal to <%s>.*/
	public static <MP1> @Nullable String equalToObjEx(double n, Object o2, MP1 msgParamOnly) {
		return Predicates.equalToObjEx(n, o2);
	}

	/** Predicate: <%s> must NOT be equal to <%s>.*/
	public static <MP1> boolean notEqualToObj(double n, Object o2, MP1 msgParamOnly) {
		return Predicates.notEqualToObj(n, o2);
	}

	/** "Special" predicate: <%s> must NOT be equal to <%s>.*/
	public static <MP1> @Nullable String notEqualToObjEx(double n, Object o2, MP1 msgParamOnly) {
		return Predicates.notEqualToObjEx(n, o2);
	}

	/** Predicate: %s must be equal to %s.*/
	public static <MP1> boolean equal(double n, double a1, MP1 msgParamOnly) {
		return Predicates.equal(n, a1);
	}

	/** "Special" predicate: %s must be equal to %s.*/
	public static <MP1> @Nullable String equalEx(double n, double a1, MP1 msgParamOnly) {
		return Predicates.equalEx(n, a1);
	}

	/** Predicate: %s must NOT be equal to %s.*/
	public static <MP1> boolean notEqual(double n, double a1, MP1 msgParamOnly) {
		return Predicates.notEqual(n, a1);
	}

	/** "Special" predicate: %s must NOT be equal to %s.*/
	public static <MP1> @Nullable String notEqualEx(double n, double a1, MP1 msgParamOnly) {
		return Predicates.notEqualEx(n, a1);
	}

	/** Predicate: %s must be > %s.*/
	public static <MP1> boolean gt(double n, double a1, MP1 msgParamOnly) {
		return Predicates.gt(n, a1);
	}

	/** "Special" predicate: %s must be > %s.*/
	public static <MP1> @Nullable String gtEx(double n, double a1, MP1 msgParamOnly) {
		return Predicates.gtEx(n, a1);
	}

	/** Predicate: %s must NOT be > %s.*/
	public static <MP1> boolean notGt(double n, double a1, MP1 msgParamOnly) {
		return Predicates.notGt(n, a1);
	}

	/** "Special" predicate: %s must NOT be > %s.*/
	public static <MP1> @Nullable String notGtEx(double n, double a1, MP1 msgParamOnly) {
		return Predicates.notGtEx(n, a1);
	}

	/** Predicate: %s must be < %s.*/
	public static <MP1> boolean lt(double n, double a1, MP1 msgParamOnly) {
		return Predicates.lt(n, a1);
	}

	/** "Special" predicate: %s must be < %s.*/
	public static <MP1> @Nullable String ltEx(double n, double a1, MP1 msgParamOnly) {
		return Predicates.ltEx(n, a1);
	}

	/** Predicate: %s must NOT be < %s.*/
	public static <MP1> boolean notLt(double n, double a1, MP1 msgParamOnly) {
		return Predicates.notLt(n, a1);
	}

	/** "Special" predicate: %s must NOT be < %s.*/
	public static <MP1> @Nullable String notLtEx(double n, double a1, MP1 msgParamOnly) {
		return Predicates.notLtEx(n, a1);
	}

	/** Predicate: %s must be >= %s.*/
	public static <MP1> boolean gtEq(double n, double a1, MP1 msgParamOnly) {
		return Predicates.gtEq(n, a1);
	}

	/** "Special" predicate: %s must be >= %s.*/
	public static <MP1> @Nullable String gtEqEx(double n, double a1, MP1 msgParamOnly) {
		return Predicates.gtEqEx(n, a1);
	}

	/** Predicate: %s must NOT be >= %s.*/
	public static <MP1> boolean notGtEq(double n, double a1, MP1 msgParamOnly) {
		return Predicates.notGtEq(n, a1);
	}

	/** "Special" predicate: %s must NOT be >= %s.*/
	public static <MP1> @Nullable String notGtEqEx(double n, double a1, MP1 msgParamOnly) {
		return Predicates.notGtEqEx(n, a1);
	}

	/** Predicate: %s must be <= %s.*/
	public static <MP1> boolean ltEq(double n, double a1, MP1 msgParamOnly) {
		return Predicates.ltEq(n, a1);
	}

	/** "Special" predicate: %s must be <= %s.*/
	public static <MP1> @Nullable String ltEqEx(double n, double a1, MP1 msgParamOnly) {
		return Predicates.ltEqEx(n, a1);
	}

	/** Predicate: %s must NOT be <= %s.*/
	public static <MP1> boolean notLtEq(double n, double a1, MP1 msgParamOnly) {
		return Predicates.notLtEq(n, a1);
	}

	/** "Special" predicate: %s must NOT be <= %s.*/
	public static <MP1> @Nullable String notLtEqEx(double n, double a1, MP1 msgParamOnly) {
		return Predicates.notLtEqEx(n, a1);
	}

	/** Predicate: %s must be >= 0 (must be non-negative).*/
	public static <MP1> boolean nonNegative(double n, MP1 msgParamOnly) {
		return Predicates.nonNegative(n);
	}

	/** "Special" predicate: %s must be >= 0 (must be non-negative).*/
	public static <MP1> @Nullable String nonNegativeEx(double n, MP1 msgParamOnly) {
		return Predicates.nonNegativeEx(n);
	}

	/** Predicate: %s must NOT be >= 0 (must be negative).*/
	public static <MP1> boolean negative(double n, MP1 msgParamOnly) {
		return Predicates.negative(n);
	}

	/** "Special" predicate: %s must NOT be >= 0 (must be negative).*/
	public static <MP1> @Nullable String negativeEx(double n, MP1 msgParamOnly) {
		return Predicates.negativeEx(n);
	}

	/** Predicate: %s must be <= 0 (must be non-positive).*/
	public static <MP1> boolean nonPositive(double n, MP1 msgParamOnly) {
		return Predicates.nonPositive(n);
	}

	/** "Special" predicate: %s must be <= 0 (must be non-positive).*/
	public static <MP1> @Nullable String nonPositiveEx(double n, MP1 msgParamOnly) {
		return Predicates.nonPositiveEx(n);
	}

	/** Predicate: %s must NOT be <= 0 (must be positive).*/
	public static <MP1> boolean positive(double n, MP1 msgParamOnly) {
		return Predicates.positive(n);
	}

	/** "Special" predicate: %s must NOT be <= 0 (must be positive).*/
	public static <MP1> @Nullable String positiveEx(double n, MP1 msgParamOnly) {
		return Predicates.positiveEx(n);
	}

	/** Predicate: <%s> must be equal to <%s>.*/
	public static <MP1> boolean equalToObj(char n, Object o2, MP1 msgParamOnly) {
		return Predicates.equalToObj(n, o2);
	}

	/** "Special" predicate: <%s> must be equal to <%s>.*/
	public static <MP1> @Nullable String equalToObjEx(char n, Object o2, MP1 msgParamOnly) {
		return Predicates.equalToObjEx(n, o2);
	}

	/** Predicate: <%s> must NOT be equal to <%s>.*/
	public static <MP1> boolean notEqualToObj(char n, Object o2, MP1 msgParamOnly) {
		return Predicates.notEqualToObj(n, o2);
	}

	/** "Special" predicate: <%s> must NOT be equal to <%s>.*/
	public static <MP1> @Nullable String notEqualToObjEx(char n, Object o2, MP1 msgParamOnly) {
		return Predicates.notEqualToObjEx(n, o2);
	}

	/** Predicate: %s must be equal to %s.*/
	public static <MP1> boolean equal(char n, char a1, MP1 msgParamOnly) {
		return Predicates.equal(n, a1);
	}

	/** "Special" predicate: %s must be equal to %s.*/
	public static <MP1> @Nullable String equalEx(char n, char a1, MP1 msgParamOnly) {
		return Predicates.equalEx(n, a1);
	}

	/** Predicate: %s must NOT be equal to %s.*/
	public static <MP1> boolean notEqual(char n, char a1, MP1 msgParamOnly) {
		return Predicates.notEqual(n, a1);
	}

	/** "Special" predicate: %s must NOT be equal to %s.*/
	public static <MP1> @Nullable String notEqualEx(char n, char a1, MP1 msgParamOnly) {
		return Predicates.notEqualEx(n, a1);
	}

	/** Predicate: %s must be > %s.*/
	public static <MP1> boolean gt(char n, char a1, MP1 msgParamOnly) {
		return Predicates.gt(n, a1);
	}

	/** "Special" predicate: %s must be > %s.*/
	public static <MP1> @Nullable String gtEx(char n, char a1, MP1 msgParamOnly) {
		return Predicates.gtEx(n, a1);
	}

	/** Predicate: %s must NOT be > %s.*/
	public static <MP1> boolean notGt(char n, char a1, MP1 msgParamOnly) {
		return Predicates.notGt(n, a1);
	}

	/** "Special" predicate: %s must NOT be > %s.*/
	public static <MP1> @Nullable String notGtEx(char n, char a1, MP1 msgParamOnly) {
		return Predicates.notGtEx(n, a1);
	}

	/** Predicate: %s must be < %s.*/
	public static <MP1> boolean lt(char n, char a1, MP1 msgParamOnly) {
		return Predicates.lt(n, a1);
	}

	/** "Special" predicate: %s must be < %s.*/
	public static <MP1> @Nullable String ltEx(char n, char a1, MP1 msgParamOnly) {
		return Predicates.ltEx(n, a1);
	}

	/** Predicate: %s must NOT be < %s.*/
	public static <MP1> boolean notLt(char n, char a1, MP1 msgParamOnly) {
		return Predicates.notLt(n, a1);
	}

	/** "Special" predicate: %s must NOT be < %s.*/
	public static <MP1> @Nullable String notLtEx(char n, char a1, MP1 msgParamOnly) {
		return Predicates.notLtEx(n, a1);
	}

	/** Predicate: %s must be >= %s.*/
	public static <MP1> boolean gtEq(char n, char a1, MP1 msgParamOnly) {
		return Predicates.gtEq(n, a1);
	}

	/** "Special" predicate: %s must be >= %s.*/
	public static <MP1> @Nullable String gtEqEx(char n, char a1, MP1 msgParamOnly) {
		return Predicates.gtEqEx(n, a1);
	}

	/** Predicate: %s must NOT be >= %s.*/
	public static <MP1> boolean notGtEq(char n, char a1, MP1 msgParamOnly) {
		return Predicates.notGtEq(n, a1);
	}

	/** "Special" predicate: %s must NOT be >= %s.*/
	public static <MP1> @Nullable String notGtEqEx(char n, char a1, MP1 msgParamOnly) {
		return Predicates.notGtEqEx(n, a1);
	}

	/** Predicate: %s must be <= %s.*/
	public static <MP1> boolean ltEq(char n, char a1, MP1 msgParamOnly) {
		return Predicates.ltEq(n, a1);
	}

	/** "Special" predicate: %s must be <= %s.*/
	public static <MP1> @Nullable String ltEqEx(char n, char a1, MP1 msgParamOnly) {
		return Predicates.ltEqEx(n, a1);
	}

	/** Predicate: %s must NOT be <= %s.*/
	public static <MP1> boolean notLtEq(char n, char a1, MP1 msgParamOnly) {
		return Predicates.notLtEq(n, a1);
	}

	/** "Special" predicate: %s must NOT be <= %s.*/
	public static <MP1> @Nullable String notLtEqEx(char n, char a1, MP1 msgParamOnly) {
		return Predicates.notLtEqEx(n, a1);
	}

	/** Predicate: %s must be >= 0 (must be non-negative).*/
	public static <MP1> boolean nonNegative(char n, MP1 msgParamOnly) {
		return Predicates.nonNegative(n);
	}

	/** "Special" predicate: %s must be >= 0 (must be non-negative).*/
	public static <MP1> @Nullable String nonNegativeEx(char n, MP1 msgParamOnly) {
		return Predicates.nonNegativeEx(n);
	}

	/** Predicate: %s must NOT be >= 0 (must be negative).*/
	public static <MP1> boolean negative(char n, MP1 msgParamOnly) {
		return Predicates.negative(n);
	}

	/** "Special" predicate: %s must NOT be >= 0 (must be negative).*/
	public static <MP1> @Nullable String negativeEx(char n, MP1 msgParamOnly) {
		return Predicates.negativeEx(n);
	}

	/** Predicate: %s must be <= 0 (must be non-positive).*/
	public static <MP1> boolean nonPositive(char n, MP1 msgParamOnly) {
		return Predicates.nonPositive(n);
	}

	/** "Special" predicate: %s must be <= 0 (must be non-positive).*/
	public static <MP1> @Nullable String nonPositiveEx(char n, MP1 msgParamOnly) {
		return Predicates.nonPositiveEx(n);
	}

	/** Predicate: %s must NOT be <= 0 (must be positive).*/
	public static <MP1> boolean positive(char n, MP1 msgParamOnly) {
		return Predicates.positive(n);
	}

	/** "Special" predicate: %s must NOT be <= 0 (must be positive).*/
	public static <MP1> @Nullable String positiveEx(char n, MP1 msgParamOnly) {
		return Predicates.positiveEx(n);
	}

	/** Predicate: <%s> must be equal to <%s>.*/
	public static <MP1> boolean equalToObj(boolean n, Object o2, MP1 msgParamOnly) {
		return Predicates.equalToObj(n, o2);
	}

	/** "Special" predicate: <%s> must be equal to <%s>.*/
	public static <MP1> @Nullable String equalToObjEx(boolean n, Object o2, MP1 msgParamOnly) {
		return Predicates.equalToObjEx(n, o2);
	}

	/** Predicate: <%s> must NOT be equal to <%s>.*/
	public static <MP1> boolean notEqualToObj(boolean n, Object o2, MP1 msgParamOnly) {
		return Predicates.notEqualToObj(n, o2);
	}

	/** "Special" predicate: <%s> must NOT be equal to <%s>.*/
	public static <MP1> @Nullable String notEqualToObjEx(boolean n, Object o2, MP1 msgParamOnly) {
		return Predicates.notEqualToObjEx(n, o2);
	}

	/** Predicate: %s must be equal to %s.*/
	public static <MP1> boolean equal(boolean n, boolean a1, MP1 msgParamOnly) {
		return Predicates.equal(n, a1);
	}

	/** "Special" predicate: %s must be equal to %s.*/
	public static <MP1> @Nullable String equalEx(boolean n, boolean a1, MP1 msgParamOnly) {
		return Predicates.equalEx(n, a1);
	}

	/** Predicate: %s must NOT be equal to %s.*/
	public static <MP1> boolean notEqual(boolean n, boolean a1, MP1 msgParamOnly) {
		return Predicates.notEqual(n, a1);
	}

	/** "Special" predicate: %s must NOT be equal to %s.*/
	public static <MP1> @Nullable String notEqualEx(boolean n, boolean a1, MP1 msgParamOnly) {
		return Predicates.notEqualEx(n, a1);
	}

	/** Predicate: %1$s must be: %2$s < %1$s < %3$s.*/
	public static <MP1> boolean between(byte n, byte a1, byte a2, MP1 msgParamOnly) {
		return Predicates.between(n, a1, a2);
	}

	/** "Special" predicate: %1$s must be: %2$s < %1$s < %3$s.*/
	public static <MP1> @Nullable String betweenEx(byte n, byte a1, byte a2, MP1 msgParamOnly) {
		return Predicates.betweenEx(n, a1, a2);
	}

	/** Predicate: %1$s must NOT be: %2$s < %1$s < %3$s.*/
	public static <MP1> boolean notBetween(byte n, byte a1, byte a2, MP1 msgParamOnly) {
		return Predicates.notBetween(n, a1, a2);
	}

	/** "Special" predicate: %1$s must NOT be: %2$s < %1$s < %3$s.*/
	public static <MP1> @Nullable String notBetweenEx(byte n, byte a1, byte a2, MP1 msgParamOnly) {
		return Predicates.notBetweenEx(n, a1, a2);
	}

	/** Predicate: %1$s must be: %2$s <= %1$s <= %3$s.*/
	public static <MP1> boolean inRange(byte n, byte a1, byte a2, MP1 msgParamOnly) {
		return Predicates.inRange(n, a1, a2);
	}

	/** "Special" predicate: %1$s must be: %2$s <= %1$s <= %3$s.*/
	public static <MP1> @Nullable String inRangeEx(byte n, byte a1, byte a2, MP1 msgParamOnly) {
		return Predicates.inRangeEx(n, a1, a2);
	}

	/** Predicate: %1$s must NOT be: %2$s <= %1$s <= %3$s.*/
	public static <MP1> boolean notInRange(byte n, byte a1, byte a2, MP1 msgParamOnly) {
		return Predicates.notInRange(n, a1, a2);
	}

	/** "Special" predicate: %1$s must NOT be: %2$s <= %1$s <= %3$s.*/
	public static <MP1> @Nullable String notInRangeEx(byte n, byte a1, byte a2, MP1 msgParamOnly) {
		return Predicates.notInRangeEx(n, a1, a2);
	}

	/** Predicate: %1$s must be: %2$s < %1$s < %3$s.*/
	public static <MP1> boolean between(short n, short a1, short a2, MP1 msgParamOnly) {
		return Predicates.between(n, a1, a2);
	}

	/** "Special" predicate: %1$s must be: %2$s < %1$s < %3$s.*/
	public static <MP1> @Nullable String betweenEx(short n, short a1, short a2, MP1 msgParamOnly) {
		return Predicates.betweenEx(n, a1, a2);
	}

	/** Predicate: %1$s must NOT be: %2$s < %1$s < %3$s.*/
	public static <MP1> boolean notBetween(short n, short a1, short a2, MP1 msgParamOnly) {
		return Predicates.notBetween(n, a1, a2);
	}

	/** "Special" predicate: %1$s must NOT be: %2$s < %1$s < %3$s.*/
	public static <MP1> @Nullable String notBetweenEx(short n, short a1, short a2, MP1 msgParamOnly) {
		return Predicates.notBetweenEx(n, a1, a2);
	}

	/** Predicate: %1$s must be: %2$s <= %1$s <= %3$s.*/
	public static <MP1> boolean inRange(short n, short a1, short a2, MP1 msgParamOnly) {
		return Predicates.inRange(n, a1, a2);
	}

	/** "Special" predicate: %1$s must be: %2$s <= %1$s <= %3$s.*/
	public static <MP1> @Nullable String inRangeEx(short n, short a1, short a2, MP1 msgParamOnly) {
		return Predicates.inRangeEx(n, a1, a2);
	}

	/** Predicate: %1$s must NOT be: %2$s <= %1$s <= %3$s.*/
	public static <MP1> boolean notInRange(short n, short a1, short a2, MP1 msgParamOnly) {
		return Predicates.notInRange(n, a1, a2);
	}

	/** "Special" predicate: %1$s must NOT be: %2$s <= %1$s <= %3$s.*/
	public static <MP1> @Nullable String notInRangeEx(short n, short a1, short a2, MP1 msgParamOnly) {
		return Predicates.notInRangeEx(n, a1, a2);
	}

	/** Predicate: %1$s must be: %2$s < %1$s < %3$s.*/
	public static <MP1> boolean between(int n, int a1, int a2, MP1 msgParamOnly) {
		return Predicates.between(n, a1, a2);
	}

	/** "Special" predicate: %1$s must be: %2$s < %1$s < %3$s.*/
	public static <MP1> @Nullable String betweenEx(int n, int a1, int a2, MP1 msgParamOnly) {
		return Predicates.betweenEx(n, a1, a2);
	}

	/** Predicate: %1$s must NOT be: %2$s < %1$s < %3$s.*/
	public static <MP1> boolean notBetween(int n, int a1, int a2, MP1 msgParamOnly) {
		return Predicates.notBetween(n, a1, a2);
	}

	/** "Special" predicate: %1$s must NOT be: %2$s < %1$s < %3$s.*/
	public static <MP1> @Nullable String notBetweenEx(int n, int a1, int a2, MP1 msgParamOnly) {
		return Predicates.notBetweenEx(n, a1, a2);
	}

	/** Predicate: %1$s must be: %2$s <= %1$s <= %3$s.*/
	public static <MP1> boolean inRange(int n, int a1, int a2, MP1 msgParamOnly) {
		return Predicates.inRange(n, a1, a2);
	}

	/** "Special" predicate: %1$s must be: %2$s <= %1$s <= %3$s.*/
	public static <MP1> @Nullable String inRangeEx(int n, int a1, int a2, MP1 msgParamOnly) {
		return Predicates.inRangeEx(n, a1, a2);
	}

	/** Predicate: %1$s must NOT be: %2$s <= %1$s <= %3$s.*/
	public static <MP1> boolean notInRange(int n, int a1, int a2, MP1 msgParamOnly) {
		return Predicates.notInRange(n, a1, a2);
	}

	/** "Special" predicate: %1$s must NOT be: %2$s <= %1$s <= %3$s.*/
	public static <MP1> @Nullable String notInRangeEx(int n, int a1, int a2, MP1 msgParamOnly) {
		return Predicates.notInRangeEx(n, a1, a2);
	}

	/** Predicate: %1$s must be: %2$s < %1$s < %3$s.*/
	public static <MP1> boolean between(long n, long a1, long a2, MP1 msgParamOnly) {
		return Predicates.between(n, a1, a2);
	}

	/** "Special" predicate: %1$s must be: %2$s < %1$s < %3$s.*/
	public static <MP1> @Nullable String betweenEx(long n, long a1, long a2, MP1 msgParamOnly) {
		return Predicates.betweenEx(n, a1, a2);
	}

	/** Predicate: %1$s must NOT be: %2$s < %1$s < %3$s.*/
	public static <MP1> boolean notBetween(long n, long a1, long a2, MP1 msgParamOnly) {
		return Predicates.notBetween(n, a1, a2);
	}

	/** "Special" predicate: %1$s must NOT be: %2$s < %1$s < %3$s.*/
	public static <MP1> @Nullable String notBetweenEx(long n, long a1, long a2, MP1 msgParamOnly) {
		return Predicates.notBetweenEx(n, a1, a2);
	}

	/** Predicate: %1$s must be: %2$s <= %1$s <= %3$s.*/
	public static <MP1> boolean inRange(long n, long a1, long a2, MP1 msgParamOnly) {
		return Predicates.inRange(n, a1, a2);
	}

	/** "Special" predicate: %1$s must be: %2$s <= %1$s <= %3$s.*/
	public static <MP1> @Nullable String inRangeEx(long n, long a1, long a2, MP1 msgParamOnly) {
		return Predicates.inRangeEx(n, a1, a2);
	}

	/** Predicate: %1$s must NOT be: %2$s <= %1$s <= %3$s.*/
	public static <MP1> boolean notInRange(long n, long a1, long a2, MP1 msgParamOnly) {
		return Predicates.notInRange(n, a1, a2);
	}

	/** "Special" predicate: %1$s must NOT be: %2$s <= %1$s <= %3$s.*/
	public static <MP1> @Nullable String notInRangeEx(long n, long a1, long a2, MP1 msgParamOnly) {
		return Predicates.notInRangeEx(n, a1, a2);
	}

	/** Predicate: %1$s must be: %2$s < %1$s < %3$s.*/
	public static <MP1> boolean between(float n, float a1, float a2, MP1 msgParamOnly) {
		return Predicates.between(n, a1, a2);
	}

	/** "Special" predicate: %1$s must be: %2$s < %1$s < %3$s.*/
	public static <MP1> @Nullable String betweenEx(float n, float a1, float a2, MP1 msgParamOnly) {
		return Predicates.betweenEx(n, a1, a2);
	}

	/** Predicate: %1$s must NOT be: %2$s < %1$s < %3$s.*/
	public static <MP1> boolean notBetween(float n, float a1, float a2, MP1 msgParamOnly) {
		return Predicates.notBetween(n, a1, a2);
	}

	/** "Special" predicate: %1$s must NOT be: %2$s < %1$s < %3$s.*/
	public static <MP1> @Nullable String notBetweenEx(float n, float a1, float a2, MP1 msgParamOnly) {
		return Predicates.notBetweenEx(n, a1, a2);
	}

	/** Predicate: %1$s must be: %2$s <= %1$s <= %3$s.*/
	public static <MP1> boolean inRange(float n, float a1, float a2, MP1 msgParamOnly) {
		return Predicates.inRange(n, a1, a2);
	}

	/** "Special" predicate: %1$s must be: %2$s <= %1$s <= %3$s.*/
	public static <MP1> @Nullable String inRangeEx(float n, float a1, float a2, MP1 msgParamOnly) {
		return Predicates.inRangeEx(n, a1, a2);
	}

	/** Predicate: %1$s must NOT be: %2$s <= %1$s <= %3$s.*/
	public static <MP1> boolean notInRange(float n, float a1, float a2, MP1 msgParamOnly) {
		return Predicates.notInRange(n, a1, a2);
	}

	/** "Special" predicate: %1$s must NOT be: %2$s <= %1$s <= %3$s.*/
	public static <MP1> @Nullable String notInRangeEx(float n, float a1, float a2, MP1 msgParamOnly) {
		return Predicates.notInRangeEx(n, a1, a2);
	}

	/** Predicate: %1$s must be: %2$s < %1$s < %3$s.*/
	public static <MP1> boolean between(double n, double a1, double a2, MP1 msgParamOnly) {
		return Predicates.between(n, a1, a2);
	}

	/** "Special" predicate: %1$s must be: %2$s < %1$s < %3$s.*/
	public static <MP1> @Nullable String betweenEx(double n, double a1, double a2, MP1 msgParamOnly) {
		return Predicates.betweenEx(n, a1, a2);
	}

	/** Predicate: %1$s must NOT be: %2$s < %1$s < %3$s.*/
	public static <MP1> boolean notBetween(double n, double a1, double a2, MP1 msgParamOnly) {
		return Predicates.notBetween(n, a1, a2);
	}

	/** "Special" predicate: %1$s must NOT be: %2$s < %1$s < %3$s.*/
	public static <MP1> @Nullable String notBetweenEx(double n, double a1, double a2, MP1 msgParamOnly) {
		return Predicates.notBetweenEx(n, a1, a2);
	}

	/** Predicate: %1$s must be: %2$s <= %1$s <= %3$s.*/
	public static <MP1> boolean inRange(double n, double a1, double a2, MP1 msgParamOnly) {
		return Predicates.inRange(n, a1, a2);
	}

	/** "Special" predicate: %1$s must be: %2$s <= %1$s <= %3$s.*/
	public static <MP1> @Nullable String inRangeEx(double n, double a1, double a2, MP1 msgParamOnly) {
		return Predicates.inRangeEx(n, a1, a2);
	}

	/** Predicate: %1$s must NOT be: %2$s <= %1$s <= %3$s.*/
	public static <MP1> boolean notInRange(double n, double a1, double a2, MP1 msgParamOnly) {
		return Predicates.notInRange(n, a1, a2);
	}

	/** "Special" predicate: %1$s must NOT be: %2$s <= %1$s <= %3$s.*/
	public static <MP1> @Nullable String notInRangeEx(double n, double a1, double a2, MP1 msgParamOnly) {
		return Predicates.notInRangeEx(n, a1, a2);
	}

	/** Predicate: %1$s must be: %2$s < %1$s < %3$s.*/
	public static <MP1> boolean between(char n, char a1, char a2, MP1 msgParamOnly) {
		return Predicates.between(n, a1, a2);
	}

	/** "Special" predicate: %1$s must be: %2$s < %1$s < %3$s.*/
	public static <MP1> @Nullable String betweenEx(char n, char a1, char a2, MP1 msgParamOnly) {
		return Predicates.betweenEx(n, a1, a2);
	}

	/** Predicate: %1$s must NOT be: %2$s < %1$s < %3$s.*/
	public static <MP1> boolean notBetween(char n, char a1, char a2, MP1 msgParamOnly) {
		return Predicates.notBetween(n, a1, a2);
	}

	/** "Special" predicate: %1$s must NOT be: %2$s < %1$s < %3$s.*/
	public static <MP1> @Nullable String notBetweenEx(char n, char a1, char a2, MP1 msgParamOnly) {
		return Predicates.notBetweenEx(n, a1, a2);
	}

	/** Predicate: %1$s must be: %2$s <= %1$s <= %3$s.*/
	public static <MP1> boolean inRange(char n, char a1, char a2, MP1 msgParamOnly) {
		return Predicates.inRange(n, a1, a2);
	}

	/** "Special" predicate: %1$s must be: %2$s <= %1$s <= %3$s.*/
	public static <MP1> @Nullable String inRangeEx(char n, char a1, char a2, MP1 msgParamOnly) {
		return Predicates.inRangeEx(n, a1, a2);
	}

	/** Predicate: %1$s must NOT be: %2$s <= %1$s <= %3$s.*/
	public static <MP1> boolean notInRange(char n, char a1, char a2, MP1 msgParamOnly) {
		return Predicates.notInRange(n, a1, a2);
	}

	/** "Special" predicate: %1$s must NOT be: %2$s <= %1$s <= %3$s.*/
	public static <MP1> @Nullable String notInRangeEx(char n, char a1, char a2, MP1 msgParamOnly) {
		return Predicates.notInRangeEx(n, a1, a2);
	}

	/** Predicate: <%s> must be true.*/
	public static <MP1> boolean True(boolean v, MP1 msgParamOnly) {
		return Predicates.True(v);
	}

	/** "Special" predicate: <%s> must be true.*/
	public static <MP1> @Nullable String TrueEx(boolean v, MP1 msgParamOnly) {
		return Predicates.TrueEx(v);
	}

	/** Predicate: <%s> must be false.*/
	public static <MP1> boolean False(boolean v, MP1 msgParamOnly) {
		return Predicates.False(v);
	}

	/** "Special" predicate: <%s> must be false.*/
	public static <MP1> @Nullable String FalseEx(boolean v, MP1 msgParamOnly) {
		return Predicates.FalseEx(v);
	}

	//</editor-fold>

	//<editor-fold desc="arrays">

	/** Predicate: Array <%s> must be of size %d (actual: %d).*/
	public static <MP1> boolean length(@Nonnull boolean[] array, int i, MP1 msgParamOnly) {
		Null.nonNullArg(array, "array");
		return Predicates.length(array, i);
	}

	/** "Special" predicate: Array <%s> must be of size %d (actual: %d).*/
	public static <MP1> @Nullable String lengthEx(@Nonnull boolean[] array, int i, MP1 msgParamOnly) {
		Null.nonNullArg(array, "array");
		return Predicates.lengthEx(array, i);
	}

	/** Predicate: Array <%s> must NOT be of size %d (actual: %d).*/
	public static <MP1> boolean lengthOtherThan(@Nonnull boolean[] array, int i, MP1 msgParamOnly) {
		Null.nonNullArg(array, "array");
		return Predicates.lengthOtherThan(array, i);
	}

	/** "Special" predicate: Array <%s> must NOT be of size %d (actual: %d).*/
	public static <MP1> @Nullable String lengthOtherThanEx(@Nonnull boolean[] array, int i, MP1 msgParamOnly) {
		Null.nonNullArg(array, "array");
		return Predicates.lengthOtherThanEx(array, i);
	}

	/** Predicate: Array <%s> must be empty (actual: %d).*/
	public static <MP1> boolean empty(@Nonnull boolean[] array, MP1 msgParamOnly) {
		Null.nonNullArg(array, "array");
		return Predicates.empty(array);
	}

	/** "Special" predicate: Array <%s> must be empty (actual: %d).*/
	public static <MP1> @Nullable String emptyEx(@Nonnull boolean[] array, MP1 msgParamOnly) {
		Null.nonNullArg(array, "array");
		return Predicates.emptyEx(array);
	}

	/** Predicate: Array <%s> must NOT be empty (actual: %d).*/
	public static <MP1> boolean notEmpty(@Nonnull boolean[] array, MP1 msgParamOnly) {
		Null.nonNullArg(array, "array");
		return Predicates.notEmpty(array);
	}

	/** "Special" predicate: Array <%s> must NOT be empty (actual: %d).*/
	public static <MP1> @Nullable String notEmptyEx(@Nonnull boolean[] array, MP1 msgParamOnly) {
		Null.nonNullArg(array, "array");
		return Predicates.notEmptyEx(array);
	}

	/** Predicate: Array <%s> must be null or empty (actual: %s).*/
	public static <MP1> boolean nullOrEmpty(@Nullable boolean[] array, MP1 msgParamOnly) {
		return Predicates.nullOrEmpty(array);
	}

	/** "Special" predicate: Array <%s> must be null or empty (actual: %s).*/
	public static <MP1> @Nullable String nullOrEmptyEx(@Nullable boolean[] array, MP1 msgParamOnly) {
		return Predicates.nullOrEmptyEx(array);
	}

	/** Predicate: Array <%s> must NOT be null or empty (actual: %s).*/
	public static <MP1> boolean notNullNotEmpty(@Nullable boolean[] array, MP1 msgParamOnly) {
		return Predicates.notNullNotEmpty(array);
	}

	/** "Special" predicate: Array <%s> must NOT be null or empty (actual: %s).*/
	public static <MP1> @Nullable String notNullNotEmptyEx(@Nullable boolean[] array, MP1 msgParamOnly) {
		return Predicates.notNullNotEmptyEx(array);
	}

	/** Predicate: Array <%s> must be exactly of size 1 (singleton; actual:%d)*/
	public static <MP1> boolean singleton(@Nonnull boolean[] array, MP1 msgParamOnly) {
		Null.nonNullArg(array, "array");
		return Predicates.singleton(array);
	}

	/** "Special" predicate: Array <%s> must be exactly of size 1 (singleton; actual:%d)*/
	public static <MP1> @Nullable String singletonEx(@Nonnull boolean[] array, MP1 msgParamOnly) {
		Null.nonNullArg(array, "array");
		return Predicates.singletonEx(array);
	}

	/** Predicate: Array <%s> must NOT be exactly of size 1 (singleton; actual:%d)*/
	public static <MP1> boolean notSingleton(@Nonnull boolean[] array, MP1 msgParamOnly) {
		Null.nonNullArg(array, "array");
		return Predicates.notSingleton(array);
	}

	/** "Special" predicate: Array <%s> must NOT be exactly of size 1 (singleton; actual:%d)*/
	public static <MP1> @Nullable String notSingletonEx(@Nonnull boolean[] array, MP1 msgParamOnly) {
		Null.nonNullArg(array, "array");
		return Predicates.notSingletonEx(array);
	}

	/** Predicate: Array <%s> must be equal to array <%s>.*/
	public static <MP1> boolean equal(boolean[] a1, boolean[] a2, MP1 msgParamOnly) {
		return Predicates.equal(a1, a2);
	}

	/** "Special" predicate: Array <%s> must be equal to array <%s>.*/
	public static <MP1> @Nullable String equalEx(boolean[] a1, boolean[] a2, MP1 msgParamOnly) {
		return Predicates.equalEx(a1, a2);
	}

	/** Predicate: Array <%s> must NOT be equal to array <%s>.*/
	public static <MP1> boolean notEqual(boolean[] a1, boolean[] a2, MP1 msgParamOnly) {
		return Predicates.notEqual(a1, a2);
	}

	/** "Special" predicate: Array <%s> must NOT be equal to array <%s>.*/
	public static <MP1> @Nullable String notEqualEx(boolean[] a1, boolean[] a2, MP1 msgParamOnly) {
		return Predicates.notEqualEx(a1, a2);
	}

	/** Predicate: Array <%s> must be of size %d (actual: %d).*/
	public static <MP1> boolean length(@Nonnull byte[] array, int i, MP1 msgParamOnly) {
		Null.nonNullArg(array, "array");
		return Predicates.length(array, i);
	}

	/** "Special" predicate: Array <%s> must be of size %d (actual: %d).*/
	public static <MP1> @Nullable String lengthEx(@Nonnull byte[] array, int i, MP1 msgParamOnly) {
		Null.nonNullArg(array, "array");
		return Predicates.lengthEx(array, i);
	}

	/** Predicate: Array <%s> must NOT be of size %d (actual: %d).*/
	public static <MP1> boolean lengthOtherThan(@Nonnull byte[] array, int i, MP1 msgParamOnly) {
		Null.nonNullArg(array, "array");
		return Predicates.lengthOtherThan(array, i);
	}

	/** "Special" predicate: Array <%s> must NOT be of size %d (actual: %d).*/
	public static <MP1> @Nullable String lengthOtherThanEx(@Nonnull byte[] array, int i, MP1 msgParamOnly) {
		Null.nonNullArg(array, "array");
		return Predicates.lengthOtherThanEx(array, i);
	}

	/** Predicate: Array <%s> must be empty (actual: %d).*/
	public static <MP1> boolean empty(@Nonnull byte[] array, MP1 msgParamOnly) {
		Null.nonNullArg(array, "array");
		return Predicates.empty(array);
	}

	/** "Special" predicate: Array <%s> must be empty (actual: %d).*/
	public static <MP1> @Nullable String emptyEx(@Nonnull byte[] array, MP1 msgParamOnly) {
		Null.nonNullArg(array, "array");
		return Predicates.emptyEx(array);
	}

	/** Predicate: Array <%s> must NOT be empty (actual: %d).*/
	public static <MP1> boolean notEmpty(@Nonnull byte[] array, MP1 msgParamOnly) {
		Null.nonNullArg(array, "array");
		return Predicates.notEmpty(array);
	}

	/** "Special" predicate: Array <%s> must NOT be empty (actual: %d).*/
	public static <MP1> @Nullable String notEmptyEx(@Nonnull byte[] array, MP1 msgParamOnly) {
		Null.nonNullArg(array, "array");
		return Predicates.notEmptyEx(array);
	}

	/** Predicate: Array <%s> must be null or empty (actual: %s).*/
	public static <MP1> boolean nullOrEmpty(@Nullable byte[] array, MP1 msgParamOnly) {
		return Predicates.nullOrEmpty(array);
	}

	/** "Special" predicate: Array <%s> must be null or empty (actual: %s).*/
	public static <MP1> @Nullable String nullOrEmptyEx(@Nullable byte[] array, MP1 msgParamOnly) {
		return Predicates.nullOrEmptyEx(array);
	}

	/** Predicate: Array <%s> must NOT be null or empty (actual: %s).*/
	public static <MP1> boolean notNullNotEmpty(@Nullable byte[] array, MP1 msgParamOnly) {
		return Predicates.notNullNotEmpty(array);
	}

	/** "Special" predicate: Array <%s> must NOT be null or empty (actual: %s).*/
	public static <MP1> @Nullable String notNullNotEmptyEx(@Nullable byte[] array, MP1 msgParamOnly) {
		return Predicates.notNullNotEmptyEx(array);
	}

	/** Predicate: Array <%s> must be exactly of size 1 (singleton; actual:%d)*/
	public static <MP1> boolean singleton(@Nonnull byte[] array, MP1 msgParamOnly) {
		Null.nonNullArg(array, "array");
		return Predicates.singleton(array);
	}

	/** "Special" predicate: Array <%s> must be exactly of size 1 (singleton; actual:%d)*/
	public static <MP1> @Nullable String singletonEx(@Nonnull byte[] array, MP1 msgParamOnly) {
		Null.nonNullArg(array, "array");
		return Predicates.singletonEx(array);
	}

	/** Predicate: Array <%s> must NOT be exactly of size 1 (singleton; actual:%d)*/
	public static <MP1> boolean notSingleton(@Nonnull byte[] array, MP1 msgParamOnly) {
		Null.nonNullArg(array, "array");
		return Predicates.notSingleton(array);
	}

	/** "Special" predicate: Array <%s> must NOT be exactly of size 1 (singleton; actual:%d)*/
	public static <MP1> @Nullable String notSingletonEx(@Nonnull byte[] array, MP1 msgParamOnly) {
		Null.nonNullArg(array, "array");
		return Predicates.notSingletonEx(array);
	}

	/** Predicate: Array <%s> must be equal to array <%s>.*/
	public static <MP1> boolean equal(byte[] a1, byte[] a2, MP1 msgParamOnly) {
		return Predicates.equal(a1, a2);
	}

	/** "Special" predicate: Array <%s> must be equal to array <%s>.*/
	public static <MP1> @Nullable String equalEx(byte[] a1, byte[] a2, MP1 msgParamOnly) {
		return Predicates.equalEx(a1, a2);
	}

	/** Predicate: Array <%s> must NOT be equal to array <%s>.*/
	public static <MP1> boolean notEqual(byte[] a1, byte[] a2, MP1 msgParamOnly) {
		return Predicates.notEqual(a1, a2);
	}

	/** "Special" predicate: Array <%s> must NOT be equal to array <%s>.*/
	public static <MP1> @Nullable String notEqualEx(byte[] a1, byte[] a2, MP1 msgParamOnly) {
		return Predicates.notEqualEx(a1, a2);
	}

	/** Predicate: Array <%s> must be of size %d (actual: %d).*/
	public static <MP1> boolean length(@Nonnull double[] array, int i, MP1 msgParamOnly) {
		Null.nonNullArg(array, "array");
		return Predicates.length(array, i);
	}

	/** "Special" predicate: Array <%s> must be of size %d (actual: %d).*/
	public static <MP1> @Nullable String lengthEx(@Nonnull double[] array, int i, MP1 msgParamOnly) {
		Null.nonNullArg(array, "array");
		return Predicates.lengthEx(array, i);
	}

	/** Predicate: Array <%s> must NOT be of size %d (actual: %d).*/
	public static <MP1> boolean lengthOtherThan(@Nonnull double[] array, int i, MP1 msgParamOnly) {
		Null.nonNullArg(array, "array");
		return Predicates.lengthOtherThan(array, i);
	}

	/** "Special" predicate: Array <%s> must NOT be of size %d (actual: %d).*/
	public static <MP1> @Nullable String lengthOtherThanEx(@Nonnull double[] array, int i, MP1 msgParamOnly) {
		Null.nonNullArg(array, "array");
		return Predicates.lengthOtherThanEx(array, i);
	}

	/** Predicate: Array <%s> must be empty (actual: %d).*/
	public static <MP1> boolean empty(@Nonnull double[] array, MP1 msgParamOnly) {
		Null.nonNullArg(array, "array");
		return Predicates.empty(array);
	}

	/** "Special" predicate: Array <%s> must be empty (actual: %d).*/
	public static <MP1> @Nullable String emptyEx(@Nonnull double[] array, MP1 msgParamOnly) {
		Null.nonNullArg(array, "array");
		return Predicates.emptyEx(array);
	}

	/** Predicate: Array <%s> must NOT be empty (actual: %d).*/
	public static <MP1> boolean notEmpty(@Nonnull double[] array, MP1 msgParamOnly) {
		Null.nonNullArg(array, "array");
		return Predicates.notEmpty(array);
	}

	/** "Special" predicate: Array <%s> must NOT be empty (actual: %d).*/
	public static <MP1> @Nullable String notEmptyEx(@Nonnull double[] array, MP1 msgParamOnly) {
		Null.nonNullArg(array, "array");
		return Predicates.notEmptyEx(array);
	}

	/** Predicate: Array <%s> must be null or empty (actual: %s).*/
	public static <MP1> boolean nullOrEmpty(@Nullable double[] array, MP1 msgParamOnly) {
		return Predicates.nullOrEmpty(array);
	}

	/** "Special" predicate: Array <%s> must be null or empty (actual: %s).*/
	public static <MP1> @Nullable String nullOrEmptyEx(@Nullable double[] array, MP1 msgParamOnly) {
		return Predicates.nullOrEmptyEx(array);
	}

	/** Predicate: Array <%s> must NOT be null or empty (actual: %s).*/
	public static <MP1> boolean notNullNotEmpty(@Nullable double[] array, MP1 msgParamOnly) {
		return Predicates.notNullNotEmpty(array);
	}

	/** "Special" predicate: Array <%s> must NOT be null or empty (actual: %s).*/
	public static <MP1> @Nullable String notNullNotEmptyEx(@Nullable double[] array, MP1 msgParamOnly) {
		return Predicates.notNullNotEmptyEx(array);
	}

	/** Predicate: Array <%s> must be exactly of size 1 (singleton; actual:%d)*/
	public static <MP1> boolean singleton(@Nonnull double[] array, MP1 msgParamOnly) {
		Null.nonNullArg(array, "array");
		return Predicates.singleton(array);
	}

	/** "Special" predicate: Array <%s> must be exactly of size 1 (singleton; actual:%d)*/
	public static <MP1> @Nullable String singletonEx(@Nonnull double[] array, MP1 msgParamOnly) {
		Null.nonNullArg(array, "array");
		return Predicates.singletonEx(array);
	}

	/** Predicate: Array <%s> must NOT be exactly of size 1 (singleton; actual:%d)*/
	public static <MP1> boolean notSingleton(@Nonnull double[] array, MP1 msgParamOnly) {
		Null.nonNullArg(array, "array");
		return Predicates.notSingleton(array);
	}

	/** "Special" predicate: Array <%s> must NOT be exactly of size 1 (singleton; actual:%d)*/
	public static <MP1> @Nullable String notSingletonEx(@Nonnull double[] array, MP1 msgParamOnly) {
		Null.nonNullArg(array, "array");
		return Predicates.notSingletonEx(array);
	}

	/** Predicate: Array <%s> must be equal to array <%s>.*/
	public static <MP1> boolean equal(double[] a1, double[] a2, MP1 msgParamOnly) {
		return Predicates.equal(a1, a2);
	}

	/** "Special" predicate: Array <%s> must be equal to array <%s>.*/
	public static <MP1> @Nullable String equalEx(double[] a1, double[] a2, MP1 msgParamOnly) {
		return Predicates.equalEx(a1, a2);
	}

	/** Predicate: Array <%s> must NOT be equal to array <%s>.*/
	public static <MP1> boolean notEqual(double[] a1, double[] a2, MP1 msgParamOnly) {
		return Predicates.notEqual(a1, a2);
	}

	/** "Special" predicate: Array <%s> must NOT be equal to array <%s>.*/
	public static <MP1> @Nullable String notEqualEx(double[] a1, double[] a2, MP1 msgParamOnly) {
		return Predicates.notEqualEx(a1, a2);
	}

	/** Predicate: Array <%s> must be of size %d (actual: %d).*/
	public static <MP1> boolean length(@Nonnull char[] array, int i, MP1 msgParamOnly) {
		Null.nonNullArg(array, "array");
		return Predicates.length(array, i);
	}

	/** "Special" predicate: Array <%s> must be of size %d (actual: %d).*/
	public static <MP1> @Nullable String lengthEx(@Nonnull char[] array, int i, MP1 msgParamOnly) {
		Null.nonNullArg(array, "array");
		return Predicates.lengthEx(array, i);
	}

	/** Predicate: Array <%s> must NOT be of size %d (actual: %d).*/
	public static <MP1> boolean lengthOtherThan(@Nonnull char[] array, int i, MP1 msgParamOnly) {
		Null.nonNullArg(array, "array");
		return Predicates.lengthOtherThan(array, i);
	}

	/** "Special" predicate: Array <%s> must NOT be of size %d (actual: %d).*/
	public static <MP1> @Nullable String lengthOtherThanEx(@Nonnull char[] array, int i, MP1 msgParamOnly) {
		Null.nonNullArg(array, "array");
		return Predicates.lengthOtherThanEx(array, i);
	}

	/** Predicate: Array <%s> must be empty (actual: %d).*/
	public static <MP1> boolean empty(@Nonnull char[] array, MP1 msgParamOnly) {
		Null.nonNullArg(array, "array");
		return Predicates.empty(array);
	}

	/** "Special" predicate: Array <%s> must be empty (actual: %d).*/
	public static <MP1> @Nullable String emptyEx(@Nonnull char[] array, MP1 msgParamOnly) {
		Null.nonNullArg(array, "array");
		return Predicates.emptyEx(array);
	}

	/** Predicate: Array <%s> must NOT be empty (actual: %d).*/
	public static <MP1> boolean notEmpty(@Nonnull char[] array, MP1 msgParamOnly) {
		Null.nonNullArg(array, "array");
		return Predicates.notEmpty(array);
	}

	/** "Special" predicate: Array <%s> must NOT be empty (actual: %d).*/
	public static <MP1> @Nullable String notEmptyEx(@Nonnull char[] array, MP1 msgParamOnly) {
		Null.nonNullArg(array, "array");
		return Predicates.notEmptyEx(array);
	}

	/** Predicate: Array <%s> must be null or empty (actual: %s).*/
	public static <MP1> boolean nullOrEmpty(@Nullable char[] array, MP1 msgParamOnly) {
		return Predicates.nullOrEmpty(array);
	}

	/** "Special" predicate: Array <%s> must be null or empty (actual: %s).*/
	public static <MP1> @Nullable String nullOrEmptyEx(@Nullable char[] array, MP1 msgParamOnly) {
		return Predicates.nullOrEmptyEx(array);
	}

	/** Predicate: Array <%s> must NOT be null or empty (actual: %s).*/
	public static <MP1> boolean notNullNotEmpty(@Nullable char[] array, MP1 msgParamOnly) {
		return Predicates.notNullNotEmpty(array);
	}

	/** "Special" predicate: Array <%s> must NOT be null or empty (actual: %s).*/
	public static <MP1> @Nullable String notNullNotEmptyEx(@Nullable char[] array, MP1 msgParamOnly) {
		return Predicates.notNullNotEmptyEx(array);
	}

	/** Predicate: Array <%s> must be exactly of size 1 (singleton; actual:%d)*/
	public static <MP1> boolean singleton(@Nonnull char[] array, MP1 msgParamOnly) {
		Null.nonNullArg(array, "array");
		return Predicates.singleton(array);
	}

	/** "Special" predicate: Array <%s> must be exactly of size 1 (singleton; actual:%d)*/
	public static <MP1> @Nullable String singletonEx(@Nonnull char[] array, MP1 msgParamOnly) {
		Null.nonNullArg(array, "array");
		return Predicates.singletonEx(array);
	}

	/** Predicate: Array <%s> must NOT be exactly of size 1 (singleton; actual:%d)*/
	public static <MP1> boolean notSingleton(@Nonnull char[] array, MP1 msgParamOnly) {
		Null.nonNullArg(array, "array");
		return Predicates.notSingleton(array);
	}

	/** "Special" predicate: Array <%s> must NOT be exactly of size 1 (singleton; actual:%d)*/
	public static <MP1> @Nullable String notSingletonEx(@Nonnull char[] array, MP1 msgParamOnly) {
		Null.nonNullArg(array, "array");
		return Predicates.notSingletonEx(array);
	}

	/** Predicate: Array <%s> must be equal to array <%s>.*/
	public static <MP1> boolean equal(char[] a1, char[] a2, MP1 msgParamOnly) {
		return Predicates.equal(a1, a2);
	}

	/** "Special" predicate: Array <%s> must be equal to array <%s>.*/
	public static <MP1> @Nullable String equalEx(char[] a1, char[] a2, MP1 msgParamOnly) {
		return Predicates.equalEx(a1, a2);
	}

	/** Predicate: Array <%s> must NOT be equal to array <%s>.*/
	public static <MP1> boolean notEqual(char[] a1, char[] a2, MP1 msgParamOnly) {
		return Predicates.notEqual(a1, a2);
	}

	/** "Special" predicate: Array <%s> must NOT be equal to array <%s>.*/
	public static <MP1> @Nullable String notEqualEx(char[] a1, char[] a2, MP1 msgParamOnly) {
		return Predicates.notEqualEx(a1, a2);
	}

	/** Predicate: Array <%s> must be of size %d (actual: %d).*/
	public static <MP1> boolean length(@Nonnull short[] array, int i, MP1 msgParamOnly) {
		Null.nonNullArg(array, "array");
		return Predicates.length(array, i);
	}

	/** "Special" predicate: Array <%s> must be of size %d (actual: %d).*/
	public static <MP1> @Nullable String lengthEx(@Nonnull short[] array, int i, MP1 msgParamOnly) {
		Null.nonNullArg(array, "array");
		return Predicates.lengthEx(array, i);
	}

	/** Predicate: Array <%s> must NOT be of size %d (actual: %d).*/
	public static <MP1> boolean lengthOtherThan(@Nonnull short[] array, int i, MP1 msgParamOnly) {
		Null.nonNullArg(array, "array");
		return Predicates.lengthOtherThan(array, i);
	}

	/** "Special" predicate: Array <%s> must NOT be of size %d (actual: %d).*/
	public static <MP1> @Nullable String lengthOtherThanEx(@Nonnull short[] array, int i, MP1 msgParamOnly) {
		Null.nonNullArg(array, "array");
		return Predicates.lengthOtherThanEx(array, i);
	}

	/** Predicate: Array <%s> must be empty (actual: %d).*/
	public static <MP1> boolean empty(@Nonnull short[] array, MP1 msgParamOnly) {
		Null.nonNullArg(array, "array");
		return Predicates.empty(array);
	}

	/** "Special" predicate: Array <%s> must be empty (actual: %d).*/
	public static <MP1> @Nullable String emptyEx(@Nonnull short[] array, MP1 msgParamOnly) {
		Null.nonNullArg(array, "array");
		return Predicates.emptyEx(array);
	}

	/** Predicate: Array <%s> must NOT be empty (actual: %d).*/
	public static <MP1> boolean notEmpty(@Nonnull short[] array, MP1 msgParamOnly) {
		Null.nonNullArg(array, "array");
		return Predicates.notEmpty(array);
	}

	/** "Special" predicate: Array <%s> must NOT be empty (actual: %d).*/
	public static <MP1> @Nullable String notEmptyEx(@Nonnull short[] array, MP1 msgParamOnly) {
		Null.nonNullArg(array, "array");
		return Predicates.notEmptyEx(array);
	}

	/** Predicate: Array <%s> must be null or empty (actual: %s).*/
	public static <MP1> boolean nullOrEmpty(@Nullable short[] array, MP1 msgParamOnly) {
		return Predicates.nullOrEmpty(array);
	}

	/** "Special" predicate: Array <%s> must be null or empty (actual: %s).*/
	public static <MP1> @Nullable String nullOrEmptyEx(@Nullable short[] array, MP1 msgParamOnly) {
		return Predicates.nullOrEmptyEx(array);
	}

	/** Predicate: Array <%s> must NOT be null or empty (actual: %s).*/
	public static <MP1> boolean notNullNotEmpty(@Nullable short[] array, MP1 msgParamOnly) {
		return Predicates.notNullNotEmpty(array);
	}

	/** "Special" predicate: Array <%s> must NOT be null or empty (actual: %s).*/
	public static <MP1> @Nullable String notNullNotEmptyEx(@Nullable short[] array, MP1 msgParamOnly) {
		return Predicates.notNullNotEmptyEx(array);
	}

	/** Predicate: Array <%s> must be exactly of size 1 (singleton; actual:%d)*/
	public static <MP1> boolean singleton(@Nonnull short[] array, MP1 msgParamOnly) {
		Null.nonNullArg(array, "array");
		return Predicates.singleton(array);
	}

	/** "Special" predicate: Array <%s> must be exactly of size 1 (singleton; actual:%d)*/
	public static <MP1> @Nullable String singletonEx(@Nonnull short[] array, MP1 msgParamOnly) {
		Null.nonNullArg(array, "array");
		return Predicates.singletonEx(array);
	}

	/** Predicate: Array <%s> must NOT be exactly of size 1 (singleton; actual:%d)*/
	public static <MP1> boolean notSingleton(@Nonnull short[] array, MP1 msgParamOnly) {
		Null.nonNullArg(array, "array");
		return Predicates.notSingleton(array);
	}

	/** "Special" predicate: Array <%s> must NOT be exactly of size 1 (singleton; actual:%d)*/
	public static <MP1> @Nullable String notSingletonEx(@Nonnull short[] array, MP1 msgParamOnly) {
		Null.nonNullArg(array, "array");
		return Predicates.notSingletonEx(array);
	}

	/** Predicate: Array <%s> must be equal to array <%s>.*/
	public static <MP1> boolean equal(short[] a1, short[] a2, MP1 msgParamOnly) {
		return Predicates.equal(a1, a2);
	}

	/** "Special" predicate: Array <%s> must be equal to array <%s>.*/
	public static <MP1> @Nullable String equalEx(short[] a1, short[] a2, MP1 msgParamOnly) {
		return Predicates.equalEx(a1, a2);
	}

	/** Predicate: Array <%s> must NOT be equal to array <%s>.*/
	public static <MP1> boolean notEqual(short[] a1, short[] a2, MP1 msgParamOnly) {
		return Predicates.notEqual(a1, a2);
	}

	/** "Special" predicate: Array <%s> must NOT be equal to array <%s>.*/
	public static <MP1> @Nullable String notEqualEx(short[] a1, short[] a2, MP1 msgParamOnly) {
		return Predicates.notEqualEx(a1, a2);
	}

	/** Predicate: Array <%s> must be of size %d (actual: %d).*/
	public static <MP1> boolean length(@Nonnull float[] array, int i, MP1 msgParamOnly) {
		Null.nonNullArg(array, "array");
		return Predicates.length(array, i);
	}

	/** "Special" predicate: Array <%s> must be of size %d (actual: %d).*/
	public static <MP1> @Nullable String lengthEx(@Nonnull float[] array, int i, MP1 msgParamOnly) {
		Null.nonNullArg(array, "array");
		return Predicates.lengthEx(array, i);
	}

	/** Predicate: Array <%s> must NOT be of size %d (actual: %d).*/
	public static <MP1> boolean lengthOtherThan(@Nonnull float[] array, int i, MP1 msgParamOnly) {
		Null.nonNullArg(array, "array");
		return Predicates.lengthOtherThan(array, i);
	}

	/** "Special" predicate: Array <%s> must NOT be of size %d (actual: %d).*/
	public static <MP1> @Nullable String lengthOtherThanEx(@Nonnull float[] array, int i, MP1 msgParamOnly) {
		Null.nonNullArg(array, "array");
		return Predicates.lengthOtherThanEx(array, i);
	}

	/** Predicate: Array <%s> must be empty (actual: %d).*/
	public static <MP1> boolean empty(@Nonnull float[] array, MP1 msgParamOnly) {
		Null.nonNullArg(array, "array");
		return Predicates.empty(array);
	}

	/** "Special" predicate: Array <%s> must be empty (actual: %d).*/
	public static <MP1> @Nullable String emptyEx(@Nonnull float[] array, MP1 msgParamOnly) {
		Null.nonNullArg(array, "array");
		return Predicates.emptyEx(array);
	}

	/** Predicate: Array <%s> must NOT be empty (actual: %d).*/
	public static <MP1> boolean notEmpty(@Nonnull float[] array, MP1 msgParamOnly) {
		Null.nonNullArg(array, "array");
		return Predicates.notEmpty(array);
	}

	/** "Special" predicate: Array <%s> must NOT be empty (actual: %d).*/
	public static <MP1> @Nullable String notEmptyEx(@Nonnull float[] array, MP1 msgParamOnly) {
		Null.nonNullArg(array, "array");
		return Predicates.notEmptyEx(array);
	}

	/** Predicate: Array <%s> must be null or empty (actual: %s).*/
	public static <MP1> boolean nullOrEmpty(@Nullable float[] array, MP1 msgParamOnly) {
		return Predicates.nullOrEmpty(array);
	}

	/** "Special" predicate: Array <%s> must be null or empty (actual: %s).*/
	public static <MP1> @Nullable String nullOrEmptyEx(@Nullable float[] array, MP1 msgParamOnly) {
		return Predicates.nullOrEmptyEx(array);
	}

	/** Predicate: Array <%s> must NOT be null or empty (actual: %s).*/
	public static <MP1> boolean notNullNotEmpty(@Nullable float[] array, MP1 msgParamOnly) {
		return Predicates.notNullNotEmpty(array);
	}

	/** "Special" predicate: Array <%s> must NOT be null or empty (actual: %s).*/
	public static <MP1> @Nullable String notNullNotEmptyEx(@Nullable float[] array, MP1 msgParamOnly) {
		return Predicates.notNullNotEmptyEx(array);
	}

	/** Predicate: Array <%s> must be exactly of size 1 (singleton; actual:%d)*/
	public static <MP1> boolean singleton(@Nonnull float[] array, MP1 msgParamOnly) {
		Null.nonNullArg(array, "array");
		return Predicates.singleton(array);
	}

	/** "Special" predicate: Array <%s> must be exactly of size 1 (singleton; actual:%d)*/
	public static <MP1> @Nullable String singletonEx(@Nonnull float[] array, MP1 msgParamOnly) {
		Null.nonNullArg(array, "array");
		return Predicates.singletonEx(array);
	}

	/** Predicate: Array <%s> must NOT be exactly of size 1 (singleton; actual:%d)*/
	public static <MP1> boolean notSingleton(@Nonnull float[] array, MP1 msgParamOnly) {
		Null.nonNullArg(array, "array");
		return Predicates.notSingleton(array);
	}

	/** "Special" predicate: Array <%s> must NOT be exactly of size 1 (singleton; actual:%d)*/
	public static <MP1> @Nullable String notSingletonEx(@Nonnull float[] array, MP1 msgParamOnly) {
		Null.nonNullArg(array, "array");
		return Predicates.notSingletonEx(array);
	}

	/** Predicate: Array <%s> must be equal to array <%s>.*/
	public static <MP1> boolean equal(float[] a1, float[] a2, MP1 msgParamOnly) {
		return Predicates.equal(a1, a2);
	}

	/** "Special" predicate: Array <%s> must be equal to array <%s>.*/
	public static <MP1> @Nullable String equalEx(float[] a1, float[] a2, MP1 msgParamOnly) {
		return Predicates.equalEx(a1, a2);
	}

	/** Predicate: Array <%s> must NOT be equal to array <%s>.*/
	public static <MP1> boolean notEqual(float[] a1, float[] a2, MP1 msgParamOnly) {
		return Predicates.notEqual(a1, a2);
	}

	/** "Special" predicate: Array <%s> must NOT be equal to array <%s>.*/
	public static <MP1> @Nullable String notEqualEx(float[] a1, float[] a2, MP1 msgParamOnly) {
		return Predicates.notEqualEx(a1, a2);
	}

	/** Predicate: Array <%s> must be of size %d (actual: %d).*/
	public static <MP1> boolean length(@Nonnull int[] array, int i, MP1 msgParamOnly) {
		Null.nonNullArg(array, "array");
		return Predicates.length(array, i);
	}

	/** "Special" predicate: Array <%s> must be of size %d (actual: %d).*/
	public static <MP1> @Nullable String lengthEx(@Nonnull int[] array, int i, MP1 msgParamOnly) {
		Null.nonNullArg(array, "array");
		return Predicates.lengthEx(array, i);
	}

	/** Predicate: Array <%s> must NOT be of size %d (actual: %d).*/
	public static <MP1> boolean lengthOtherThan(@Nonnull int[] array, int i, MP1 msgParamOnly) {
		Null.nonNullArg(array, "array");
		return Predicates.lengthOtherThan(array, i);
	}

	/** "Special" predicate: Array <%s> must NOT be of size %d (actual: %d).*/
	public static <MP1> @Nullable String lengthOtherThanEx(@Nonnull int[] array, int i, MP1 msgParamOnly) {
		Null.nonNullArg(array, "array");
		return Predicates.lengthOtherThanEx(array, i);
	}

	/** Predicate: Array <%s> must be empty (actual: %d).*/
	public static <MP1> boolean empty(@Nonnull int[] array, MP1 msgParamOnly) {
		Null.nonNullArg(array, "array");
		return Predicates.empty(array);
	}

	/** "Special" predicate: Array <%s> must be empty (actual: %d).*/
	public static <MP1> @Nullable String emptyEx(@Nonnull int[] array, MP1 msgParamOnly) {
		Null.nonNullArg(array, "array");
		return Predicates.emptyEx(array);
	}

	/** Predicate: Array <%s> must NOT be empty (actual: %d).*/
	public static <MP1> boolean notEmpty(@Nonnull int[] array, MP1 msgParamOnly) {
		Null.nonNullArg(array, "array");
		return Predicates.notEmpty(array);
	}

	/** "Special" predicate: Array <%s> must NOT be empty (actual: %d).*/
	public static <MP1> @Nullable String notEmptyEx(@Nonnull int[] array, MP1 msgParamOnly) {
		Null.nonNullArg(array, "array");
		return Predicates.notEmptyEx(array);
	}

	/** Predicate: Array <%s> must be null or empty (actual: %s).*/
	public static <MP1> boolean nullOrEmpty(@Nullable int[] array, MP1 msgParamOnly) {
		return Predicates.nullOrEmpty(array);
	}

	/** "Special" predicate: Array <%s> must be null or empty (actual: %s).*/
	public static <MP1> @Nullable String nullOrEmptyEx(@Nullable int[] array, MP1 msgParamOnly) {
		return Predicates.nullOrEmptyEx(array);
	}

	/** Predicate: Array <%s> must NOT be null or empty (actual: %s).*/
	public static <MP1> boolean notNullNotEmpty(@Nullable int[] array, MP1 msgParamOnly) {
		return Predicates.notNullNotEmpty(array);
	}

	/** "Special" predicate: Array <%s> must NOT be null or empty (actual: %s).*/
	public static <MP1> @Nullable String notNullNotEmptyEx(@Nullable int[] array, MP1 msgParamOnly) {
		return Predicates.notNullNotEmptyEx(array);
	}

	/** Predicate: Array <%s> must be exactly of size 1 (singleton; actual:%d)*/
	public static <MP1> boolean singleton(@Nonnull int[] array, MP1 msgParamOnly) {
		Null.nonNullArg(array, "array");
		return Predicates.singleton(array);
	}

	/** "Special" predicate: Array <%s> must be exactly of size 1 (singleton; actual:%d)*/
	public static <MP1> @Nullable String singletonEx(@Nonnull int[] array, MP1 msgParamOnly) {
		Null.nonNullArg(array, "array");
		return Predicates.singletonEx(array);
	}

	/** Predicate: Array <%s> must NOT be exactly of size 1 (singleton; actual:%d)*/
	public static <MP1> boolean notSingleton(@Nonnull int[] array, MP1 msgParamOnly) {
		Null.nonNullArg(array, "array");
		return Predicates.notSingleton(array);
	}

	/** "Special" predicate: Array <%s> must NOT be exactly of size 1 (singleton; actual:%d)*/
	public static <MP1> @Nullable String notSingletonEx(@Nonnull int[] array, MP1 msgParamOnly) {
		Null.nonNullArg(array, "array");
		return Predicates.notSingletonEx(array);
	}

	/** Predicate: Array <%s> must be equal to array <%s>.*/
	public static <MP1> boolean equal(int[] a1, int[] a2, MP1 msgParamOnly) {
		return Predicates.equal(a1, a2);
	}

	/** "Special" predicate: Array <%s> must be equal to array <%s>.*/
	public static <MP1> @Nullable String equalEx(int[] a1, int[] a2, MP1 msgParamOnly) {
		return Predicates.equalEx(a1, a2);
	}

	/** Predicate: Array <%s> must NOT be equal to array <%s>.*/
	public static <MP1> boolean notEqual(int[] a1, int[] a2, MP1 msgParamOnly) {
		return Predicates.notEqual(a1, a2);
	}

	/** "Special" predicate: Array <%s> must NOT be equal to array <%s>.*/
	public static <MP1> @Nullable String notEqualEx(int[] a1, int[] a2, MP1 msgParamOnly) {
		return Predicates.notEqualEx(a1, a2);
	}

	/** Predicate: Array <%s> must be of size %d (actual: %d).*/
	public static <MP1> boolean length(@Nonnull long[] array, int i, MP1 msgParamOnly) {
		Null.nonNullArg(array, "array");
		return Predicates.length(array, i);
	}

	/** "Special" predicate: Array <%s> must be of size %d (actual: %d).*/
	public static <MP1> @Nullable String lengthEx(@Nonnull long[] array, int i, MP1 msgParamOnly) {
		Null.nonNullArg(array, "array");
		return Predicates.lengthEx(array, i);
	}

	/** Predicate: Array <%s> must NOT be of size %d (actual: %d).*/
	public static <MP1> boolean lengthOtherThan(@Nonnull long[] array, int i, MP1 msgParamOnly) {
		Null.nonNullArg(array, "array");
		return Predicates.lengthOtherThan(array, i);
	}

	/** "Special" predicate: Array <%s> must NOT be of size %d (actual: %d).*/
	public static <MP1> @Nullable String lengthOtherThanEx(@Nonnull long[] array, int i, MP1 msgParamOnly) {
		Null.nonNullArg(array, "array");
		return Predicates.lengthOtherThanEx(array, i);
	}

	/** Predicate: Array <%s> must be empty (actual: %d).*/
	public static <MP1> boolean empty(@Nonnull long[] array, MP1 msgParamOnly) {
		Null.nonNullArg(array, "array");
		return Predicates.empty(array);
	}

	/** "Special" predicate: Array <%s> must be empty (actual: %d).*/
	public static <MP1> @Nullable String emptyEx(@Nonnull long[] array, MP1 msgParamOnly) {
		Null.nonNullArg(array, "array");
		return Predicates.emptyEx(array);
	}

	/** Predicate: Array <%s> must NOT be empty (actual: %d).*/
	public static <MP1> boolean notEmpty(@Nonnull long[] array, MP1 msgParamOnly) {
		Null.nonNullArg(array, "array");
		return Predicates.notEmpty(array);
	}

	/** "Special" predicate: Array <%s> must NOT be empty (actual: %d).*/
	public static <MP1> @Nullable String notEmptyEx(@Nonnull long[] array, MP1 msgParamOnly) {
		Null.nonNullArg(array, "array");
		return Predicates.notEmptyEx(array);
	}

	/** Predicate: Array <%s> must be null or empty (actual: %s).*/
	public static <MP1> boolean nullOrEmpty(@Nullable long[] array, MP1 msgParamOnly) {
		return Predicates.nullOrEmpty(array);
	}

	/** "Special" predicate: Array <%s> must be null or empty (actual: %s).*/
	public static <MP1> @Nullable String nullOrEmptyEx(@Nullable long[] array, MP1 msgParamOnly) {
		return Predicates.nullOrEmptyEx(array);
	}

	/** Predicate: Array <%s> must NOT be null or empty (actual: %s).*/
	public static <MP1> boolean notNullNotEmpty(@Nullable long[] array, MP1 msgParamOnly) {
		return Predicates.notNullNotEmpty(array);
	}

	/** "Special" predicate: Array <%s> must NOT be null or empty (actual: %s).*/
	public static <MP1> @Nullable String notNullNotEmptyEx(@Nullable long[] array, MP1 msgParamOnly) {
		return Predicates.notNullNotEmptyEx(array);
	}

	/** Predicate: Array <%s> must be exactly of size 1 (singleton; actual:%d)*/
	public static <MP1> boolean singleton(@Nonnull long[] array, MP1 msgParamOnly) {
		Null.nonNullArg(array, "array");
		return Predicates.singleton(array);
	}

	/** "Special" predicate: Array <%s> must be exactly of size 1 (singleton; actual:%d)*/
	public static <MP1> @Nullable String singletonEx(@Nonnull long[] array, MP1 msgParamOnly) {
		Null.nonNullArg(array, "array");
		return Predicates.singletonEx(array);
	}

	/** Predicate: Array <%s> must NOT be exactly of size 1 (singleton; actual:%d)*/
	public static <MP1> boolean notSingleton(@Nonnull long[] array, MP1 msgParamOnly) {
		Null.nonNullArg(array, "array");
		return Predicates.notSingleton(array);
	}

	/** "Special" predicate: Array <%s> must NOT be exactly of size 1 (singleton; actual:%d)*/
	public static <MP1> @Nullable String notSingletonEx(@Nonnull long[] array, MP1 msgParamOnly) {
		Null.nonNullArg(array, "array");
		return Predicates.notSingletonEx(array);
	}

	/** Predicate: Array <%s> must be equal to array <%s>.*/
	public static <MP1> boolean equal(long[] a1, long[] a2, MP1 msgParamOnly) {
		return Predicates.equal(a1, a2);
	}

	/** "Special" predicate: Array <%s> must be equal to array <%s>.*/
	public static <MP1> @Nullable String equalEx(long[] a1, long[] a2, MP1 msgParamOnly) {
		return Predicates.equalEx(a1, a2);
	}

	/** Predicate: Array <%s> must NOT be equal to array <%s>.*/
	public static <MP1> boolean notEqual(long[] a1, long[] a2, MP1 msgParamOnly) {
		return Predicates.notEqual(a1, a2);
	}

	/** "Special" predicate: Array <%s> must NOT be equal to array <%s>.*/
	public static <MP1> @Nullable String notEqualEx(long[] a1, long[] a2, MP1 msgParamOnly) {
		return Predicates.notEqualEx(a1, a2);
	}

	/** Predicate: Array <%s> must be of size %d (actual: %d).*/
	public static <T, MP1> boolean length(@Nonnull T[] array, int i, MP1 msgParamOnly) {
		Null.nonNullArg(array, "array");
		return Predicates.length(array, i);
	}

	/** "Special" predicate: Array <%s> must be of size %d (actual: %d).*/
	public static <T, MP1> @Nullable String lengthEx(@Nonnull T[] array, int i, MP1 msgParamOnly) {
		Null.nonNullArg(array, "array");
		return Predicates.lengthEx(array, i);
	}

	/** Predicate: Array <%s> must NOT be of size %d (actual: %d).*/
	public static <T, MP1> boolean lengthOtherThan(@Nonnull T[] array, int i, MP1 msgParamOnly) {
		Null.nonNullArg(array, "array");
		return Predicates.lengthOtherThan(array, i);
	}

	/** "Special" predicate: Array <%s> must NOT be of size %d (actual: %d).*/
	public static <T, MP1> @Nullable String lengthOtherThanEx(@Nonnull T[] array, int i, MP1 msgParamOnly) {
		Null.nonNullArg(array, "array");
		return Predicates.lengthOtherThanEx(array, i);
	}

	/** Predicate: Array <%s> must be empty (actual: %d).*/
	public static <T, MP1> boolean empty(@Nonnull T[] array, MP1 msgParamOnly) {
		Null.nonNullArg(array, "array");
		return Predicates.empty(array);
	}

	/** "Special" predicate: Array <%s> must be empty (actual: %d).*/
	public static <T, MP1> @Nullable String emptyEx(@Nonnull T[] array, MP1 msgParamOnly) {
		Null.nonNullArg(array, "array");
		return Predicates.emptyEx(array);
	}

	/** Predicate: Array <%s> must NOT be empty (actual: %d).*/
	public static <T, MP1> boolean notEmpty(@Nonnull T[] array, MP1 msgParamOnly) {
		Null.nonNullArg(array, "array");
		return Predicates.notEmpty(array);
	}

	/** "Special" predicate: Array <%s> must NOT be empty (actual: %d).*/
	public static <T, MP1> @Nullable String notEmptyEx(@Nonnull T[] array, MP1 msgParamOnly) {
		Null.nonNullArg(array, "array");
		return Predicates.notEmptyEx(array);
	}

	/** Predicate: Array <%s> must be null or empty (actual: %s).*/
	public static <T, MP1> boolean nullOrEmpty(@Nullable T[] array, MP1 msgParamOnly) {
		return Predicates.nullOrEmpty(array);
	}

	/** "Special" predicate: Array <%s> must be null or empty (actual: %s).*/
	public static <T, MP1> @Nullable String nullOrEmptyEx(@Nullable T[] array, MP1 msgParamOnly) {
		return Predicates.nullOrEmptyEx(array);
	}

	/** Predicate: Array <%s> must NOT be null or empty (actual: %s).*/
	public static <T, MP1> boolean notNullNotEmpty(@Nullable T[] array, MP1 msgParamOnly) {
		return Predicates.notNullNotEmpty(array);
	}

	/** "Special" predicate: Array <%s> must NOT be null or empty (actual: %s).*/
	public static <T, MP1> @Nullable String notNullNotEmptyEx(@Nullable T[] array, MP1 msgParamOnly) {
		return Predicates.notNullNotEmptyEx(array);
	}

	/** Predicate: Array <%s> must be exactly of size 1 (singleton; actual:%d)*/
	public static <T, MP1> boolean singleton(@Nonnull T[] array, MP1 msgParamOnly) {
		Null.nonNullArg(array, "array");
		return Predicates.singleton(array);
	}

	/** "Special" predicate: Array <%s> must be exactly of size 1 (singleton; actual:%d)*/
	public static <T, MP1> @Nullable String singletonEx(@Nonnull T[] array, MP1 msgParamOnly) {
		Null.nonNullArg(array, "array");
		return Predicates.singletonEx(array);
	}

	/** Predicate: Array <%s> must NOT be exactly of size 1 (singleton; actual:%d)*/
	public static <T, MP1> boolean notSingleton(@Nonnull T[] array, MP1 msgParamOnly) {
		Null.nonNullArg(array, "array");
		return Predicates.notSingleton(array);
	}

	/** "Special" predicate: Array <%s> must NOT be exactly of size 1 (singleton; actual:%d)*/
	public static <T, MP1> @Nullable String notSingletonEx(@Nonnull T[] array, MP1 msgParamOnly) {
		Null.nonNullArg(array, "array");
		return Predicates.notSingletonEx(array);
	}

	/** Predicate: Array <%s> must be equal to array <%s>.*/
	public static <T, MP1> boolean equal(T[] a1, T[] a2, MP1 msgParamOnly) {
		return Predicates.equal(a1, a2);
	}

	/** "Special" predicate: Array <%s> must be equal to array <%s>.*/
	public static <T, MP1> @Nullable String equalEx(T[] a1, T[] a2, MP1 msgParamOnly) {
		return Predicates.equalEx(a1, a2);
	}

	/** Predicate: Array <%s> must NOT be equal to array <%s>.*/
	public static <T, MP1> boolean notEqual(T[] a1, T[] a2, MP1 msgParamOnly) {
		return Predicates.notEqual(a1, a2);
	}

	/** "Special" predicate: Array <%s> must NOT be equal to array <%s>.*/
	public static <T, MP1> @Nullable String notEqualEx(T[] a1, T[] a2, MP1 msgParamOnly) {
		return Predicates.notEqualEx(a1, a2);
	}

	//</editor-fold>

	//<editor-fold desc="collections">

	/** Predicate: Collection <%s> must contain element <%s>.*/
	public static <T, MP1> boolean contain(@Nonnull Collection<? extends T> collection, T element, MP1 msgParamOnly) {
		Null.nonNullArg(collection, "collection");
		return Predicates.contain(collection, element);
	}

	/** "Special" predicate: Collection <%s> must contain element <%s>.*/
	public static <T, MP1> @Nullable String containEx(@Nonnull Collection<? extends T> collection, T element, MP1 msgParamOnly) {
		Null.nonNullArg(collection, "collection");
		return Predicates.containEx(collection, element);
	}

	/** Predicate: Collection <%s> must NOT contain element <%s>.*/
	public static <T, MP1> boolean notContain(@Nonnull Collection<? extends T> collection, T element, MP1 msgParamOnly) {
		Null.nonNullArg(collection, "collection");
		return Predicates.notContain(collection, element);
	}

	/** "Special" predicate: Collection <%s> must NOT contain element <%s>.*/
	public static <T, MP1> @Nullable String notContainEx(@Nonnull Collection<? extends T> collection, T element, MP1 msgParamOnly) {
		Null.nonNullArg(collection, "collection");
		return Predicates.notContainEx(collection, element);
	}

	/** Predicate: Map <%s> must contain key <%s>.*/
	public static <K, MP1> boolean containKey(@Nonnull Map<? extends K, ?> map, K key, MP1 msgParamOnly) {
		Null.nonNullArg(map, "map");
		return Predicates.containKey(map, key);
	}

	/** "Special" predicate: Map <%s> must contain key <%s>.*/
	public static <K, MP1> @Nullable String containKeyEx(@Nonnull Map<? extends K, ?> map, K key, MP1 msgParamOnly) {
		Null.nonNullArg(map, "map");
		return Predicates.containKeyEx(map, key);
	}

	/** Predicate: Map <%s> must NOT contain key <%s>.*/
	public static <K, MP1> boolean notContainKey(@Nonnull Map<? extends K, ?> map, K key, MP1 msgParamOnly) {
		Null.nonNullArg(map, "map");
		return Predicates.notContainKey(map, key);
	}

	/** "Special" predicate: Map <%s> must NOT contain key <%s>.*/
	public static <K, MP1> @Nullable String notContainKeyEx(@Nonnull Map<? extends K, ?> map, K key, MP1 msgParamOnly) {
		Null.nonNullArg(map, "map");
		return Predicates.notContainKeyEx(map, key);
	}

	/** Predicate: Map <%s> must contain entry with key <%s> and value <%s>.*/
	public static <K, V, MP1> boolean containEntry(@Nonnull Map<? extends K, ? extends V> map, K key, V value, MP1 msgParamOnly) {
		Null.nonNullArg(map, "map");
		return Predicates.containEntry(map, key, value);
	}

	/** "Special" predicate: Map <%s> must contain entry with key <%s> and value <%s>.*/
	public static <K, V, MP1> @Nullable String containEntryEx(@Nonnull Map<? extends K, ? extends V> map, K key, V value, MP1 msgParamOnly) {
		Null.nonNullArg(map, "map");
		return Predicates.containEntryEx(map, key, value);
	}

	/** Predicate: Map <%s> must NOT contain entry with key <%s> and value <%s>.*/
	public static <K, V, MP1> boolean notContainEntry(@Nonnull Map<? extends K, ? extends V> map, K key, V value, MP1 msgParamOnly) {
		Null.nonNullArg(map, "map");
		return Predicates.notContainEntry(map, key, value);
	}

	/** "Special" predicate: Map <%s> must NOT contain entry with key <%s> and value <%s>.*/
	public static <K, V, MP1> @Nullable String notContainEntryEx(@Nonnull Map<? extends K, ? extends V> map, K key, V value, MP1 msgParamOnly) {
		Null.nonNullArg(map, "map");
		return Predicates.notContainEntryEx(map, key, value);
	}

	/** Predicate: Collection <%s> must be of size %s (actual: %d).*/
	public static <T, MP1> boolean size(@Nonnull Collection<? extends T> collection, int i, MP1 msgParamOnly) {
		Null.nonNullArg(collection, "collection");
		return Predicates.size(collection, i);
	}

	/** "Special" predicate: Collection <%s> must be of size %s (actual: %d).*/
	public static <T, MP1> @Nullable String sizeEx(@Nonnull Collection<? extends T> collection, int i, MP1 msgParamOnly) {
		Null.nonNullArg(collection, "collection");
		return Predicates.sizeEx(collection, i);
	}

	/** Predicate: Collection <%s> must NOT be of size %s (actual: %d).*/
	public static <T, MP1> boolean sizeOtherThan(@Nonnull Collection<? extends T> collection, int i, MP1 msgParamOnly) {
		Null.nonNullArg(collection, "collection");
		return Predicates.sizeOtherThan(collection, i);
	}

	/** "Special" predicate: Collection <%s> must NOT be of size %s (actual: %d).*/
	public static <T, MP1> @Nullable String sizeOtherThanEx(@Nonnull Collection<? extends T> collection, int i, MP1 msgParamOnly) {
		Null.nonNullArg(collection, "collection");
		return Predicates.sizeOtherThanEx(collection, i);
	}

	/** Predicate: Map <%s> must be of size %s (actual: %d).*/
	public static <K, V, MP1> boolean size(@Nonnull Map<? extends K, ? extends V> map, int i, MP1 msgParamOnly) {
		Null.nonNullArg(map, "map");
		return Predicates.size(map, i);
	}

	/** "Special" predicate: Map <%s> must be of size %s (actual: %d).*/
	public static <K, V, MP1> @Nullable String sizeEx(@Nonnull Map<? extends K, ? extends V> map, int i, MP1 msgParamOnly) {
		Null.nonNullArg(map, "map");
		return Predicates.sizeEx(map, i);
	}

	/** Predicate: Map <%s> must NOT be of size %s (actual: %d).*/
	public static <K, V, MP1> boolean sizeOtherThan(@Nonnull Map<? extends K, ? extends V> map, int i, MP1 msgParamOnly) {
		Null.nonNullArg(map, "map");
		return Predicates.sizeOtherThan(map, i);
	}

	/** "Special" predicate: Map <%s> must NOT be of size %s (actual: %d).*/
	public static <K, V, MP1> @Nullable String sizeOtherThanEx(@Nonnull Map<? extends K, ? extends V> map, int i, MP1 msgParamOnly) {
		Null.nonNullArg(map, "map");
		return Predicates.sizeOtherThanEx(map, i);
	}

	/** Predicate: <%s> must be part of <%s> collection.*/
	public static <T, MP1> boolean partOf(T element, @Nonnull Collection<? extends T> collection, MP1 msgParamOnly) {
		Null.nonNullArg(collection, "collection");
		return Predicates.partOf(element, collection);
	}

	/** "Special" predicate: <%s> must be part of <%s> collection.*/
	public static <T, MP1> @Nullable String partOfEx(T element, @Nonnull Collection<? extends T> collection, MP1 msgParamOnly) {
		Null.nonNullArg(collection, "collection");
		return Predicates.partOfEx(element, collection);
	}

	/** Predicate: <%s> must NOT be part of <%s> collection.*/
	public static <T, MP1> boolean notPartOf(T element, @Nonnull Collection<? extends T> collection, MP1 msgParamOnly) {
		Null.nonNullArg(collection, "collection");
		return Predicates.notPartOf(element, collection);
	}

	/** "Special" predicate: <%s> must NOT be part of <%s> collection.*/
	public static <T, MP1> @Nullable String notPartOfEx(T element, @Nonnull Collection<? extends T> collection, MP1 msgParamOnly) {
		Null.nonNullArg(collection, "collection");
		return Predicates.notPartOfEx(element, collection);
	}

	/** Predicate: <%s> must be key in <%s> map.*/
	public static <K, MP1> boolean aKeyIn(K key, @Nonnull Map<? extends K, ?> map, MP1 msgParamOnly) {
		Null.nonNullArg(map, "map");
		return Predicates.aKeyIn(key, map);
	}

	/** "Special" predicate: <%s> must be key in <%s> map.*/
	public static <K, MP1> @Nullable String aKeyInEx(K key, @Nonnull Map<? extends K, ?> map, MP1 msgParamOnly) {
		Null.nonNullArg(map, "map");
		return Predicates.aKeyInEx(key, map);
	}

	/** Predicate: <%s> must NOT be key in <%s> map.*/
	public static <K, MP1> boolean notAKeyIn(K key, @Nonnull Map<? extends K, ?> map, MP1 msgParamOnly) {
		Null.nonNullArg(map, "map");
		return Predicates.notAKeyIn(key, map);
	}

	/** "Special" predicate: <%s> must NOT be key in <%s> map.*/
	public static <K, MP1> @Nullable String notAKeyInEx(K key, @Nonnull Map<? extends K, ?> map, MP1 msgParamOnly) {
		Null.nonNullArg(map, "map");
		return Predicates.notAKeyInEx(key, map);
	}

	/** Predicate: Collection <%s> must be empty (actual: %d).*/
	public static <T, MP1> boolean empty(@Nonnull Collection<? extends T> collection, MP1 msgParamOnly) {
		Null.nonNullArg(collection, "collection");
		return Predicates.empty(collection);
	}

	/** "Special" predicate: Collection <%s> must be empty (actual: %d).*/
	public static <T, MP1> @Nullable String emptyEx(@Nonnull Collection<? extends T> collection, MP1 msgParamOnly) {
		Null.nonNullArg(collection, "collection");
		return Predicates.emptyEx(collection);
	}

	/** Predicate: Collection <%s> must NOT be empty (actual: %d).*/
	public static <T, MP1> boolean notEmpty(@Nonnull Collection<? extends T> collection, MP1 msgParamOnly) {
		Null.nonNullArg(collection, "collection");
		return Predicates.notEmpty(collection);
	}

	/** "Special" predicate: Collection <%s> must NOT be empty (actual: %d).*/
	public static <T, MP1> @Nullable String notEmptyEx(@Nonnull Collection<? extends T> collection, MP1 msgParamOnly) {
		Null.nonNullArg(collection, "collection");
		return Predicates.notEmptyEx(collection);
	}

	/** Predicate: Collection <%s> must be null or empty (actual: %d).*/
	public static <T, MP1> boolean nullOrEmpty(@Nullable Collection<? extends T> collection, MP1 msgParamOnly) {
		return Predicates.nullOrEmpty(collection);
	}

	/** "Special" predicate: Collection <%s> must be null or empty (actual: %d).*/
	public static <T, MP1> @Nullable String nullOrEmptyEx(@Nullable Collection<? extends T> collection, MP1 msgParamOnly) {
		return Predicates.nullOrEmptyEx(collection);
	}

	/** Predicate: Collection <%s> must NOT be null or empty (actual: %d).*/
	public static <T, MP1> boolean notNullNorEmpty(@Nullable Collection<? extends T> collection, MP1 msgParamOnly) {
		return Predicates.notNullNorEmpty(collection);
	}

	/** "Special" predicate: Collection <%s> must NOT be null or empty (actual: %d).*/
	public static <T, MP1> @Nullable String notNullNorEmptyEx(@Nullable Collection<? extends T> collection, MP1 msgParamOnly) {
		return Predicates.notNullNorEmptyEx(collection);
	}

	/** Predicate: Collection <%s> must be exactly of size 1 (singleton; actual: %d).*/
	public static <T, MP1> boolean singleton(@Nonnull Collection<? extends T> collection, MP1 msgParamOnly) {
		Null.nonNullArg(collection, "collection");
		return Predicates.singleton(collection);
	}

	/** "Special" predicate: Collection <%s> must be exactly of size 1 (singleton; actual: %d).*/
	public static <T, MP1> @Nullable String singletonEx(@Nonnull Collection<? extends T> collection, MP1 msgParamOnly) {
		Null.nonNullArg(collection, "collection");
		return Predicates.singletonEx(collection);
	}

	/** Predicate: Collection <%s> must NOT be exactly of size 1 (singleton; actual: %d).*/
	public static <T, MP1> boolean notSingleton(@Nonnull Collection<? extends T> collection, MP1 msgParamOnly) {
		Null.nonNullArg(collection, "collection");
		return Predicates.notSingleton(collection);
	}

	/** "Special" predicate: Collection <%s> must NOT be exactly of size 1 (singleton; actual: %d).*/
	public static <T, MP1> @Nullable String notSingletonEx(@Nonnull Collection<? extends T> collection, MP1 msgParamOnly) {
		Null.nonNullArg(collection, "collection");
		return Predicates.notSingletonEx(collection);
	}

	/** Predicate: Map <%s> must be empty (actual: %d).*/
	public static <MP1> boolean empty(@Nonnull Map<?, ?> map, MP1 msgParamOnly) {
		Null.nonNullArg(map, "map");
		return Predicates.empty(map);
	}

	/** "Special" predicate: Map <%s> must be empty (actual: %d).*/
	public static <MP1> @Nullable String emptyEx(@Nonnull Map<?, ?> map, MP1 msgParamOnly) {
		Null.nonNullArg(map, "map");
		return Predicates.emptyEx(map);
	}

	/** Predicate: Map <%s> must NOT be empty (actual: %d).*/
	public static <MP1> boolean notEmpty(@Nonnull Map<?, ?> map, MP1 msgParamOnly) {
		Null.nonNullArg(map, "map");
		return Predicates.notEmpty(map);
	}

	/** "Special" predicate: Map <%s> must NOT be empty (actual: %d).*/
	public static <MP1> @Nullable String notEmptyEx(@Nonnull Map<?, ?> map, MP1 msgParamOnly) {
		Null.nonNullArg(map, "map");
		return Predicates.notEmptyEx(map);
	}

	/** Predicate: Map <%s> must be null or empty (actual: %d).*/
	public static <MP1> boolean nullOrEmpty(@Nullable Map<?, ?> map, MP1 msgParamOnly) {
		return Predicates.nullOrEmpty(map);
	}

	/** "Special" predicate: Map <%s> must be null or empty (actual: %d).*/
	public static <MP1> @Nullable String nullOrEmptyEx(@Nullable Map<?, ?> map, MP1 msgParamOnly) {
		return Predicates.nullOrEmptyEx(map);
	}

	/** Predicate: Map <%s> must NOT be null or empty (actual: %d).*/
	public static <MP1> boolean notNullNorEmpty(@Nullable Map<?, ?> map, MP1 msgParamOnly) {
		return Predicates.notNullNorEmpty(map);
	}

	/** "Special" predicate: Map <%s> must NOT be null or empty (actual: %d).*/
	public static <MP1> @Nullable String notNullNorEmptyEx(@Nullable Map<?, ?> map, MP1 msgParamOnly) {
		return Predicates.notNullNorEmptyEx(map);
	}

	/** Predicate: Map <%s> must be exactly of size 1 (singleton; actual: %d).*/
	public static <MP1> boolean singleton(@Nonnull Map<?, ?> map, MP1 msgParamOnly) {
		Null.nonNullArg(map, "map");
		return Predicates.singleton(map);
	}

	/** "Special" predicate: Map <%s> must be exactly of size 1 (singleton; actual: %d).*/
	public static <MP1> @Nullable String singletonEx(@Nonnull Map<?, ?> map, MP1 msgParamOnly) {
		Null.nonNullArg(map, "map");
		return Predicates.singletonEx(map);
	}

	/** Predicate: Map <%s> must NOT be exactly of size 1 (singleton; actual: %d).*/
	public static <MP1> boolean notSingleton(@Nonnull Map<?, ?> map, MP1 msgParamOnly) {
		Null.nonNullArg(map, "map");
		return Predicates.notSingleton(map);
	}

	/** "Special" predicate: Map <%s> must NOT be exactly of size 1 (singleton; actual: %d).*/
	public static <MP1> @Nullable String notSingletonEx(@Nonnull Map<?, ?> map, MP1 msgParamOnly) {
		Null.nonNullArg(map, "map");
		return Predicates.notSingletonEx(map);
	}

	//</editor-fold>

	//<editor-fold desc="object derivatives">

	/** Predicate: Object <%s> of class <%s> must be instance of <%s>.*/
	public static <MP1> boolean instanceOf(Object object, Class<?> clazz, MP1 msgParamOnly) {
		Null.nonNullArg(clazz, "clazz");
		return Predicates.instanceOf(object, clazz);
	}

	/** "Special" predicate: Object <%s> of class <%s> must be instance of <%s>.*/
	public static <MP1> @Nullable String instanceOfEx(Object object, Class<?> clazz, MP1 msgParamOnly) {
		Null.nonNullArg(clazz, "clazz");
		return Predicates.instanceOfEx(object, clazz);
	}

	/** Predicate: Object <%s> of class <%s> must NOT be instance of <%s>.*/
	public static <MP1> boolean notInstanceOf(Object object, Class<?> clazz, MP1 msgParamOnly) {
		Null.nonNullArg(clazz, "clazz");
		return Predicates.notInstanceOf(object, clazz);
	}

	/** "Special" predicate: Object <%s> of class <%s> must NOT be instance of <%s>.*/
	public static <MP1> @Nullable String notInstanceOfEx(Object object, Class<?> clazz, MP1 msgParamOnly) {
		Null.nonNullArg(clazz, "clazz");
		return Predicates.notInstanceOfEx(object, clazz);
	}

	/** Predicate: Object <%s> of class <%s> must have simple class name equal <%s>.*/
	public static <MP1> boolean classSimpleName(Object object, String name, MP1 msgParamOnly) {
		Null.nonNullArg(name, "name");
		return Predicates.classSimpleName(object, name);
	}

	/** "Special" predicate: Object <%s> of class <%s> must have simple class name equal <%s>.*/
	public static <MP1> @Nullable String classSimpleNameEx(Object object, String name, MP1 msgParamOnly) {
		Null.nonNullArg(name, "name");
		return Predicates.classSimpleNameEx(object, name);
	}

	/** Predicate: Object <%s> of class <%s> must NOT have simple class name equal <%s>.*/
	public static <MP1> boolean classSimpleNameOtherThan(Object object, String name, MP1 msgParamOnly) {
		Null.nonNullArg(name, "name");
		return Predicates.classSimpleNameOtherThan(object, name);
	}

	/** "Special" predicate: Object <%s> of class <%s> must NOT have simple class name equal <%s>.*/
	public static <MP1> @Nullable String classSimpleNameOtherThanEx(Object object, String name, MP1 msgParamOnly) {
		Null.nonNullArg(name, "name");
		return Predicates.classSimpleNameOtherThanEx(object, name);
	}

	/** Predicate: Object <%s> of class <%s> must have class name equal <%s>.*/
	public static <MP1> boolean className(Object object, String name, MP1 msgParamOnly) {
		Null.nonNullArg(name, "name");
		return Predicates.className(object, name);
	}

	/** "Special" predicate: Object <%s> of class <%s> must have class name equal <%s>.*/
	public static <MP1> @Nullable String classNameEx(Object object, String name, MP1 msgParamOnly) {
		Null.nonNullArg(name, "name");
		return Predicates.classNameEx(object, name);
	}

	/** Predicate: Object <%s> of class <%s> must NOT have class name equal <%s>.*/
	public static <MP1> boolean classNameOtherThan(Object object, String name, MP1 msgParamOnly) {
		Null.nonNullArg(name, "name");
		return Predicates.classNameOtherThan(object, name);
	}

	/** "Special" predicate: Object <%s> of class <%s> must NOT have class name equal <%s>.*/
	public static <MP1> @Nullable String classNameOtherThanEx(Object object, String name, MP1 msgParamOnly) {
		Null.nonNullArg(name, "name");
		return Predicates.classNameOtherThanEx(object, name);
	}

	/** Predicate: Object <%s> of class <%s> must be exactly instance of <%s>.*/
	public static <MP1> boolean exactlyInstanceOf(Object object, Class<?> clazz, MP1 msgParamOnly) {
		Null.nonNullArg(clazz, "clazz");
		return Predicates.exactlyInstanceOf(object, clazz);
	}

	/** "Special" predicate: Object <%s> of class <%s> must be exactly instance of <%s>.*/
	public static <MP1> @Nullable String exactlyInstanceOfEx(Object object, Class<?> clazz, MP1 msgParamOnly) {
		Null.nonNullArg(clazz, "clazz");
		return Predicates.exactlyInstanceOfEx(object, clazz);
	}

	/** Predicate: Class <%s> must be assignable from <%s>.*/
	public static <MP1> boolean assignableFrom(Class<?> clazz, Class<?> from, MP1 msgParamOnly) {
		Null.nonNullArg(from, "from");
		Null.nonNullArg(clazz, "clazz");
		return Predicates.assignableFrom(clazz, from);
	}

	/** "Special" predicate: Class <%s> must be assignable from <%s>.*/
	public static <MP1> @Nullable String assignableFromEx(Class<?> clazz, Class<?> from, MP1 msgParamOnly) {
		Null.nonNullArg(from, "from");
		Null.nonNullArg(clazz, "clazz");
		return Predicates.assignableFromEx(clazz, from);
	}

	/** Predicate: Class <%s> must NOT be assignable from <%s>.*/
	public static <MP1> boolean notAssignableFrom(Class<?> clazz, Class<?> from, MP1 msgParamOnly) {
		Null.nonNullArg(from, "from");
		Null.nonNullArg(clazz, "clazz");
		return Predicates.notAssignableFrom(clazz, from);
	}

	/** "Special" predicate: Class <%s> must NOT be assignable from <%s>.*/
	public static <MP1> @Nullable String notAssignableFromEx(Class<?> clazz, Class<?> from, MP1 msgParamOnly) {
		Null.nonNullArg(from, "from");
		Null.nonNullArg(clazz, "clazz");
		return Predicates.notAssignableFromEx(clazz, from);
	}

	/** Predicate: Class <%s> must be assignable to <%s>.*/
	public static <MP1> boolean assignableTo(Class<?> clazz, Class<?> from, MP1 msgParamOnly) {
		Null.nonNullArg(from, "from");
		Null.nonNullArg(clazz, "clazz");
		return Predicates.assignableTo(clazz, from);
	}

	/** "Special" predicate: Class <%s> must be assignable to <%s>.*/
	public static <MP1> @Nullable String assignableToEx(Class<?> clazz, Class<?> from, MP1 msgParamOnly) {
		Null.nonNullArg(from, "from");
		Null.nonNullArg(clazz, "clazz");
		return Predicates.assignableToEx(clazz, from);
	}

	/** Predicate: Class <%s> must NOT be assignable to <%s>.*/
	public static <MP1> boolean notAssignableTo(Class<?> clazz, Class<?> from, MP1 msgParamOnly) {
		Null.nonNullArg(from, "from");
		Null.nonNullArg(clazz, "clazz");
		return Predicates.notAssignableTo(clazz, from);
	}

	/** "Special" predicate: Class <%s> must NOT be assignable to <%s>.*/
	public static <MP1> @Nullable String notAssignableToEx(Class<?> clazz, Class<?> from, MP1 msgParamOnly) {
		Null.nonNullArg(from, "from");
		Null.nonNullArg(clazz, "clazz");
		return Predicates.notAssignableToEx(clazz, from);
	}

	//</editor-fold>

	//<editor-fold desc="Throwables">

	/** Predicate: Exception <%s> must be instance of a RuntimeException.*/
	public static <MP1> boolean runtime(@Nonnull Throwable e, MP1 msgParamOnly) {
		Null.nonNullArg(e, "e");
		return Predicates.runtime(e);
	}

	/** "Special" predicate: Exception <%s> must be instance of a RuntimeException.*/
	public static <MP1> @Nullable String runtimeEx(@Nonnull Throwable e, MP1 msgParamOnly) {
		Null.nonNullArg(e, "e");
		return Predicates.runtimeEx(e);
	}

	/** Predicate: Exception <%s> must NOT be instance of a RuntimeException.*/
	public static <MP1> boolean notRuntime(@Nonnull Throwable e, MP1 msgParamOnly) {
		Null.nonNullArg(e, "e");
		return Predicates.notRuntime(e);
	}

	/** "Special" predicate: Exception <%s> must NOT be instance of a RuntimeException.*/
	public static <MP1> @Nullable String notRuntimeEx(@Nonnull Throwable e, MP1 msgParamOnly) {
		Null.nonNullArg(e, "e");
		return Predicates.notRuntimeEx(e);
	}

	/** Predicate: Exception <%s> must have cause.*/
	public static <MP1> boolean cause(@Nonnull Throwable e, MP1 msgParamOnly) {
		Null.nonNullArg(e, "e");
		return Predicates.cause(e);
	}

	/** "Special" predicate: Exception <%s> must have cause.*/
	public static <MP1> @Nullable String causeEx(@Nonnull Throwable e, MP1 msgParamOnly) {
		Null.nonNullArg(e, "e");
		return Predicates.causeEx(e);
	}

	/** Predicate: Exception <%s> must NOT have cause.*/
	public static <MP1> boolean noCause(@Nonnull Throwable e, MP1 msgParamOnly) {
		Null.nonNullArg(e, "e");
		return Predicates.noCause(e);
	}

	/** "Special" predicate: Exception <%s> must NOT have cause.*/
	public static <MP1> @Nullable String noCauseEx(@Nonnull Throwable e, MP1 msgParamOnly) {
		Null.nonNullArg(e, "e");
		return Predicates.noCauseEx(e);
	}

	/** Predicate: Cause of the exception <%s> must be instance of <%s>.*/
	public static <MP1> boolean causeInstanceOf(@Nonnull Throwable e, Class<?> clazz, MP1 msgParamOnly) {
		Null.nonNullArg(e, "e");
		Null.nonNullArg(clazz, "clazz");
		return Predicates.causeInstanceOf(e, clazz);
	}

	/** "Special" predicate: Cause of the exception <%s> must be instance of <%s>.*/
	public static <MP1> @Nullable String causeInstanceOfEx(@Nonnull Throwable e, Class<?> clazz, MP1 msgParamOnly) {
		Null.nonNullArg(e, "e");
		Null.nonNullArg(clazz, "clazz");
		return Predicates.causeInstanceOfEx(e, clazz);
	}

	/** Predicate: Cause of the exception <%s> must NOT be instance of <%s>.*/
	public static <MP1> boolean causeNotInstanceOf(@Nonnull Throwable e, Class<?> clazz, MP1 msgParamOnly) {
		Null.nonNullArg(e, "e");
		Null.nonNullArg(clazz, "clazz");
		return Predicates.causeNotInstanceOf(e, clazz);
	}

	/** "Special" predicate: Cause of the exception <%s> must NOT be instance of <%s>.*/
	public static <MP1> @Nullable String causeNotInstanceOfEx(@Nonnull Throwable e, Class<?> clazz, MP1 msgParamOnly) {
		Null.nonNullArg(e, "e");
		Null.nonNullArg(clazz, "clazz");
		return Predicates.causeNotInstanceOfEx(e, clazz);
	}

	/** Predicate: Cause of the exception <%s> must be instance exactly of <%s>.*/
	public static <MP1> boolean causeExactlyInstanceOf(@Nonnull Throwable e, Class<?> clazz, MP1 msgParamOnly) {
		Null.nonNullArg(e, "e");
		Null.nonNullArg(clazz, "clazz");
		return Predicates.causeExactlyInstanceOf(e, clazz);
	}

	/** "Special" predicate: Cause of the exception <%s> must be instance exactly of <%s>.*/
	public static <MP1> @Nullable String causeExactlyInstanceOfEx(@Nonnull Throwable e, Class<?> clazz, MP1 msgParamOnly) {
		Null.nonNullArg(e, "e");
		Null.nonNullArg(clazz, "clazz");
		return Predicates.causeExactlyInstanceOfEx(e, clazz);
	}

	/** Predicate: Exception <%s> must have suppressed other exceptions.*/
	public static <MP1> boolean suppressed(@Nonnull Throwable e, MP1 msgParamOnly) {
		Null.nonNullArg(e, "e");
		return Predicates.suppressed(e);
	}

	/** "Special" predicate: Exception <%s> must have suppressed other exceptions.*/
	public static <MP1> @Nullable String suppressedEx(@Nonnull Throwable e, MP1 msgParamOnly) {
		Null.nonNullArg(e, "e");
		return Predicates.suppressedEx(e);
	}

	/** Predicate: Exception <%s> must NOT have suppressed other exceptions.*/
	public static <MP1> boolean noSuppressed(@Nonnull Throwable e, MP1 msgParamOnly) {
		Null.nonNullArg(e, "e");
		return Predicates.noSuppressed(e);
	}

	/** "Special" predicate: Exception <%s> must NOT have suppressed other exceptions.*/
	public static <MP1> @Nullable String noSuppressedEx(@Nonnull Throwable e, MP1 msgParamOnly) {
		Null.nonNullArg(e, "e");
		return Predicates.noSuppressedEx(e);
	}

	/** Predicate: Exception <%s> must have message equal to <'%s>'.*/
	public static <MP1> boolean msgEqual(@Nonnull Throwable e, String text, MP1 msgParamOnly) {
		Null.nonNullArg(e, "e");
		return Predicates.msgEqual(e, text);
	}

	/** "Special" predicate: Exception <%s> must have message equal to <'%s>'.*/
	public static <MP1> @Nullable String msgEqualEx(@Nonnull Throwable e, String text, MP1 msgParamOnly) {
		Null.nonNullArg(e, "e");
		return Predicates.msgEqualEx(e, text);
	}

	/** Predicate: Exception <%s> must NOT have message equal to <'%s>'.*/
	public static <MP1> boolean msgNotEqual(@Nonnull Throwable e, String text, MP1 msgParamOnly) {
		Null.nonNullArg(e, "e");
		return Predicates.msgNotEqual(e, text);
	}

	/** "Special" predicate: Exception <%s> must NOT have message equal to <'%s>'.*/
	public static <MP1> @Nullable String msgNotEqualEx(@Nonnull Throwable e, String text, MP1 msgParamOnly) {
		Null.nonNullArg(e, "e");
		return Predicates.msgNotEqualEx(e, text);
	}

	/** Predicate: Exception <%s> must have message (any).*/
	public static <MP1> boolean msgPresent(@Nonnull Throwable e, MP1 msgParamOnly) {
		Null.nonNullArg(e, "e");
		return Predicates.msgPresent(e);
	}

	/** "Special" predicate: Exception <%s> must have message (any).*/
	public static <MP1> @Nullable String msgPresentEx(@Nonnull Throwable e, MP1 msgParamOnly) {
		Null.nonNullArg(e, "e");
		return Predicates.msgPresentEx(e);
	}

	/** Predicate: Exception <%s> must NOT have message (any).*/
	public static <MP1> boolean noMsg(@Nonnull Throwable e, MP1 msgParamOnly) {
		Null.nonNullArg(e, "e");
		return Predicates.noMsg(e);
	}

	/** "Special" predicate: Exception <%s> must NOT have message (any).*/
	public static <MP1> @Nullable String noMsgEx(@Nonnull Throwable e, MP1 msgParamOnly) {
		Null.nonNullArg(e, "e");
		return Predicates.noMsgEx(e);
	}

	/** Predicate: Exception <%s> must have message starting with <'%s>'.*/
	public static <MP1> boolean msgStartWith(@Nonnull Throwable e, @Nonnull String text, MP1 msgParamOnly) {
		Null.nonNullArg(e, "e");
		Null.nonNullArg(text, "text");
		return Predicates.msgStartWith(e, text);
	}

	/** "Special" predicate: Exception <%s> must have message starting with <'%s>'.*/
	public static <MP1> @Nullable String msgStartWithEx(@Nonnull Throwable e, @Nonnull String text, MP1 msgParamOnly) {
		Null.nonNullArg(e, "e");
		Null.nonNullArg(text, "text");
		return Predicates.msgStartWithEx(e, text);
	}

	/** Predicate: Exception <%s> must NOT have message starting with <'%s>'.*/
	public static <MP1> boolean msgNotStartWith(@Nonnull Throwable e, @Nonnull String text, MP1 msgParamOnly) {
		Null.nonNullArg(e, "e");
		Null.nonNullArg(text, "text");
		return Predicates.msgNotStartWith(e, text);
	}

	/** "Special" predicate: Exception <%s> must NOT have message starting with <'%s>'.*/
	public static <MP1> @Nullable String msgNotStartWithEx(@Nonnull Throwable e, @Nonnull String text, MP1 msgParamOnly) {
		Null.nonNullArg(e, "e");
		Null.nonNullArg(text, "text");
		return Predicates.msgNotStartWithEx(e, text);
	}

	/** Predicate: Exception <%s> must have message containing <'%s>'.*/
	public static <MP1> boolean msgContain(@Nonnull Throwable e, @Nonnull String text, MP1 msgParamOnly) {
		Null.nonNullArg(e, "e");
		Null.nonNullArg(text, "text");
		return Predicates.msgContain(e, text);
	}

	/** "Special" predicate: Exception <%s> must have message containing <'%s>'.*/
	public static <MP1> @Nullable String msgContainEx(@Nonnull Throwable e, @Nonnull String text, MP1 msgParamOnly) {
		Null.nonNullArg(e, "e");
		Null.nonNullArg(text, "text");
		return Predicates.msgContainEx(e, text);
	}

	/** Predicate: Exception <%s> must NOT have message containing <'%s>'.*/
	public static <MP1> boolean msgNotContain(@Nonnull Throwable e, @Nonnull String text, MP1 msgParamOnly) {
		Null.nonNullArg(e, "e");
		Null.nonNullArg(text, "text");
		return Predicates.msgNotContain(e, text);
	}

	/** "Special" predicate: Exception <%s> must NOT have message containing <'%s>'.*/
	public static <MP1> @Nullable String msgNotContainEx(@Nonnull Throwable e, @Nonnull String text, MP1 msgParamOnly) {
		Null.nonNullArg(e, "e");
		Null.nonNullArg(text, "text");
		return Predicates.msgNotContainEx(e, text);
	}

	/** Predicate: Exception <%s> must have message ending with <'%s>'.*/
	public static <MP1> boolean msgEndWith(@Nonnull Throwable e, @Nonnull String text, MP1 msgParamOnly) {
		Null.nonNullArg(e, "e");
		Null.nonNullArg(text, "text");
		return Predicates.msgEndWith(e, text);
	}

	/** "Special" predicate: Exception <%s> must have message ending with <'%s>'.*/
	public static <MP1> @Nullable String msgEndWithEx(@Nonnull Throwable e, @Nonnull String text, MP1 msgParamOnly) {
		Null.nonNullArg(e, "e");
		Null.nonNullArg(text, "text");
		return Predicates.msgEndWithEx(e, text);
	}

	/** Predicate: Exception <%s> must NOT have message ending with <'%s>'.*/
	public static <MP1> boolean msgNotEndWith(@Nonnull Throwable e, @Nonnull String text, MP1 msgParamOnly) {
		Null.nonNullArg(e, "e");
		Null.nonNullArg(text, "text");
		return Predicates.msgNotEndWith(e, text);
	}

	/** "Special" predicate: Exception <%s> must NOT have message ending with <'%s>'.*/
	public static <MP1> @Nullable String msgNotEndWithEx(@Nonnull Throwable e, @Nonnull String text, MP1 msgParamOnly) {
		Null.nonNullArg(e, "e");
		Null.nonNullArg(text, "text");
		return Predicates.msgNotEndWithEx(e, text);
	}

	/** Predicate: Exception <%s> must have suppressed other exceptions.*/
	public static <MP1> boolean suppressing(@Nonnull Throwable e, MP1 msgParamOnly) {
		Null.nonNullArg(e, "e");
		return Predicates.suppressing(e);
	}

	/** "Special" predicate: Exception <%s> must have suppressed other exceptions.*/
	public static <MP1> @Nullable String suppressingEx(@Nonnull Throwable e, MP1 msgParamOnly) {
		Null.nonNullArg(e, "e");
		return Predicates.suppressingEx(e);
	}

	/** Predicate: Exception <%s> must NOT have suppressed other exceptions.*/
	public static <MP1> boolean notSuppressing(@Nonnull Throwable e, MP1 msgParamOnly) {
		Null.nonNullArg(e, "e");
		return Predicates.notSuppressing(e);
	}

	/** "Special" predicate: Exception <%s> must NOT have suppressed other exceptions.*/
	public static <MP1> @Nullable String notSuppressingEx(@Nonnull Throwable e, MP1 msgParamOnly) {
		Null.nonNullArg(e, "e");
		return Predicates.notSuppressingEx(e);
	}

	//</editor-fold>

	//<editor-fold desc="Value (e.g. Opt )">

	/** Predicate: Optional <%s> must have value.*/
	public static <V, MP1> boolean valuePresent(@Nonnull OptBoolTrait<?> opt, MP1 msgParamOnly) {
		Null.nonNullArg(opt, "opt");
		return Predicates.valuePresent(opt);
	}

	/** "Special" predicate: Optional <%s> must have value.*/
	public static <V, MP1> @Nullable String valuePresentEx(@Nonnull OptBoolTrait<?> opt, MP1 msgParamOnly) {
		Null.nonNullArg(opt, "opt");
		return Predicates.valuePresentEx(opt);
	}

	/** Predicate: Optional <%s> must NOT have value.*/
	public static <V, MP1> boolean noValuePresent(@Nonnull OptBoolTrait<?> opt, MP1 msgParamOnly) {
		Null.nonNullArg(opt, "opt");
		return Predicates.noValuePresent(opt);
	}

	/** "Special" predicate: Optional <%s> must NOT have value.*/
	public static <V, MP1> @Nullable String noValuePresentEx(@Nonnull OptBoolTrait<?> opt, MP1 msgParamOnly) {
		Null.nonNullArg(opt, "opt");
		return Predicates.noValuePresentEx(opt);
	}

	/** Predicate: Optional <%s> must be void.*/
	public static <V, MP1> boolean Void(@Nonnull OptBoolTrait<?> opt, MP1 msgParamOnly) {
		Null.nonNullArg(opt, "opt");
		return Predicates.Void(opt);
	}

	/** "Special" predicate: Optional <%s> must be void.*/
	public static <V, MP1> @Nullable String VoidEx(@Nonnull OptBoolTrait<?> opt, MP1 msgParamOnly) {
		Null.nonNullArg(opt, "opt");
		return Predicates.VoidEx(opt);
	}

	/** Predicate: Optional <%s> must NOT be void.*/
	public static <V, MP1> boolean notVoid(@Nonnull OptBoolTrait<?> opt, MP1 msgParamOnly) {
		Null.nonNullArg(opt, "opt");
		return Predicates.notVoid(opt);
	}

	/** "Special" predicate: Optional <%s> must NOT be void.*/
	public static <V, MP1> @Nullable String notVoidEx(@Nonnull OptBoolTrait<?> opt, MP1 msgParamOnly) {
		Null.nonNullArg(opt, "opt");
		return Predicates.notVoidEx(opt);
	}

	/** Predicate: Optional <%s> must have value equal <%s>.*/
	public static <V, MP1> boolean valueEqual(@Nonnull OptBoolTrait<?> opt, boolean expected, MP1 msgParamOnly) {
		Null.nonNullArg(opt, "opt");
		return Predicates.valueEqual(opt, expected);
	}

	/** "Special" predicate: Optional <%s> must have value equal <%s>.*/
	public static <V, MP1> @Nullable String valueEqualEx(@Nonnull OptBoolTrait<?> opt, boolean expected, MP1 msgParamOnly) {
		Null.nonNullArg(opt, "opt");
		return Predicates.valueEqualEx(opt, expected);
	}

	/** Predicate: Optional <%s> must NOT have value equal <%s>.*/
	public static <V, MP1> boolean valueNotEqual(@Nonnull OptBoolTrait<?> opt, boolean expected, MP1 msgParamOnly) {
		Null.nonNullArg(opt, "opt");
		return Predicates.valueNotEqual(opt, expected);
	}

	/** "Special" predicate: Optional <%s> must NOT have value equal <%s>.*/
	public static <V, MP1> @Nullable String valueNotEqualEx(@Nonnull OptBoolTrait<?> opt, boolean expected, MP1 msgParamOnly) {
		Null.nonNullArg(opt, "opt");
		return Predicates.valueNotEqualEx(opt, expected);
	}

	/** Predicate: Optional <%s> must have value equal <%s>.*/
	public static <V, MP1> boolean valueEqual(@Nonnull BoolValueTrait<?> opt, boolean expected, MP1 msgParamOnly) {
		Null.nonNullArg(opt, "opt");
		return Predicates.valueEqual(opt, expected);
	}

	/** "Special" predicate: Optional <%s> must have value equal <%s>.*/
	public static <V, MP1> @Nullable String valueEqualEx(@Nonnull BoolValueTrait<?> opt, boolean expected, MP1 msgParamOnly) {
		Null.nonNullArg(opt, "opt");
		return Predicates.valueEqualEx(opt, expected);
	}

	/** Predicate: Optional <%s> must NOT have value equal <%s>.*/
	public static <V, MP1> boolean valueNotEqual(@Nonnull BoolValueTrait<?> opt, boolean expected, MP1 msgParamOnly) {
		Null.nonNullArg(opt, "opt");
		return Predicates.valueNotEqual(opt, expected);
	}

	/** "Special" predicate: Optional <%s> must NOT have value equal <%s>.*/
	public static <V, MP1> @Nullable String valueNotEqualEx(@Nonnull BoolValueTrait<?> opt, boolean expected, MP1 msgParamOnly) {
		Null.nonNullArg(opt, "opt");
		return Predicates.valueNotEqualEx(opt, expected);
	}

	/** Predicate: Optional <%s> must have value.*/
	public static <V, MP1> boolean valuePresent(@Nonnull OptTrait<?, ?> opt, MP1 msgParamOnly) {
		Null.nonNullArg(opt, "opt");
		return Predicates.valuePresent(opt);
	}

	/** "Special" predicate: Optional <%s> must have value.*/
	public static <V, MP1> @Nullable String valuePresentEx(@Nonnull OptTrait<?, ?> opt, MP1 msgParamOnly) {
		Null.nonNullArg(opt, "opt");
		return Predicates.valuePresentEx(opt);
	}

	/** Predicate: Optional <%s> must NOT have value.*/
	public static <V, MP1> boolean noValuePresent(@Nonnull OptTrait<?, ?> opt, MP1 msgParamOnly) {
		Null.nonNullArg(opt, "opt");
		return Predicates.noValuePresent(opt);
	}

	/** "Special" predicate: Optional <%s> must NOT have value.*/
	public static <V, MP1> @Nullable String noValuePresentEx(@Nonnull OptTrait<?, ?> opt, MP1 msgParamOnly) {
		Null.nonNullArg(opt, "opt");
		return Predicates.noValuePresentEx(opt);
	}

	/** Predicate: Optional <%s> must be void.*/
	public static <V, MP1> boolean Void(@Nonnull OptTrait<?, ?> opt, MP1 msgParamOnly) {
		Null.nonNullArg(opt, "opt");
		return Predicates.Void(opt);
	}

	/** "Special" predicate: Optional <%s> must be void.*/
	public static <V, MP1> @Nullable String VoidEx(@Nonnull OptTrait<?, ?> opt, MP1 msgParamOnly) {
		Null.nonNullArg(opt, "opt");
		return Predicates.VoidEx(opt);
	}

	/** Predicate: Optional <%s> must NOT be void.*/
	public static <V, MP1> boolean notVoid(@Nonnull OptTrait<?, ?> opt, MP1 msgParamOnly) {
		Null.nonNullArg(opt, "opt");
		return Predicates.notVoid(opt);
	}

	/** "Special" predicate: Optional <%s> must NOT be void.*/
	public static <V, MP1> @Nullable String notVoidEx(@Nonnull OptTrait<?, ?> opt, MP1 msgParamOnly) {
		Null.nonNullArg(opt, "opt");
		return Predicates.notVoidEx(opt);
	}

	/** Predicate: Optional <%s> must have value equal <%s>.*/
	public static <V, MP1> boolean valueEqual(@Nonnull ValueTrait<?, ?> opt, V expected, MP1 msgParamOnly) {
		Null.nonNullArg(opt, "opt");
		return Predicates.valueEqual(opt, expected);
	}

	/** "Special" predicate: Optional <%s> must have value equal <%s>.*/
	public static <V, MP1> @Nullable String valueEqualEx(@Nonnull ValueTrait<?, ?> opt, V expected, MP1 msgParamOnly) {
		Null.nonNullArg(opt, "opt");
		return Predicates.valueEqualEx(opt, expected);
	}

	/** Predicate: Optional <%s> must NOT have value equal <%s>.*/
	public static <V, MP1> boolean valueNotEqual(@Nonnull ValueTrait<?, ?> opt, V expected, MP1 msgParamOnly) {
		Null.nonNullArg(opt, "opt");
		return Predicates.valueNotEqual(opt, expected);
	}

	/** "Special" predicate: Optional <%s> must NOT have value equal <%s>.*/
	public static <V, MP1> @Nullable String valueNotEqualEx(@Nonnull ValueTrait<?, ?> opt, V expected, MP1 msgParamOnly) {
		Null.nonNullArg(opt, "opt");
		return Predicates.valueNotEqualEx(opt, expected);
	}

	/** Predicate: Optional <%s> must have value equal <%s>.*/
	public static <V, MP1> boolean sameValue(@Nonnull ValueTrait<?, ?> opt, V expected, MP1 msgParamOnly) {
		Null.nonNullArg(opt, "opt");
		return Predicates.sameValue(opt, expected);
	}

	/** "Special" predicate: Optional <%s> must have value equal <%s>.*/
	public static <V, MP1> @Nullable String sameValueEx(@Nonnull ValueTrait<?, ?> opt, V expected, MP1 msgParamOnly) {
		Null.nonNullArg(opt, "opt");
		return Predicates.sameValueEx(opt, expected);
	}

	/** Predicate: Optional <%s> must NOT have value equal <%s>.*/
	public static <V, MP1> boolean notSameValue(@Nonnull ValueTrait<?, ?> opt, V expected, MP1 msgParamOnly) {
		Null.nonNullArg(opt, "opt");
		return Predicates.notSameValue(opt, expected);
	}

	/** "Special" predicate: Optional <%s> must NOT have value equal <%s>.*/
	public static <V, MP1> @Nullable String notSameValueEx(@Nonnull ValueTrait<?, ?> opt, V expected, MP1 msgParamOnly) {
		Null.nonNullArg(opt, "opt");
		return Predicates.notSameValueEx(opt, expected);
	}

	/** Predicate: Optional <%s> must have value.*/
	public static <V, MP1> boolean valuePresent(@Nonnull OptByteTrait<?> opt, MP1 msgParamOnly) {
		Null.nonNullArg(opt, "opt");
		return Predicates.valuePresent(opt);
	}

	/** "Special" predicate: Optional <%s> must have value.*/
	public static <V, MP1> @Nullable String valuePresentEx(@Nonnull OptByteTrait<?> opt, MP1 msgParamOnly) {
		Null.nonNullArg(opt, "opt");
		return Predicates.valuePresentEx(opt);
	}

	/** Predicate: Optional <%s> must NOT have value.*/
	public static <V, MP1> boolean noValuePresent(@Nonnull OptByteTrait<?> opt, MP1 msgParamOnly) {
		Null.nonNullArg(opt, "opt");
		return Predicates.noValuePresent(opt);
	}

	/** "Special" predicate: Optional <%s> must NOT have value.*/
	public static <V, MP1> @Nullable String noValuePresentEx(@Nonnull OptByteTrait<?> opt, MP1 msgParamOnly) {
		Null.nonNullArg(opt, "opt");
		return Predicates.noValuePresentEx(opt);
	}

	/** Predicate: Optional <%s> must be void.*/
	public static <V, MP1> boolean Void(@Nonnull OptByteTrait<?> opt, MP1 msgParamOnly) {
		Null.nonNullArg(opt, "opt");
		return Predicates.Void(opt);
	}

	/** "Special" predicate: Optional <%s> must be void.*/
	public static <V, MP1> @Nullable String VoidEx(@Nonnull OptByteTrait<?> opt, MP1 msgParamOnly) {
		Null.nonNullArg(opt, "opt");
		return Predicates.VoidEx(opt);
	}

	/** Predicate: Optional <%s> must NOT be void.*/
	public static <V, MP1> boolean notVoid(@Nonnull OptByteTrait<?> opt, MP1 msgParamOnly) {
		Null.nonNullArg(opt, "opt");
		return Predicates.notVoid(opt);
	}

	/** "Special" predicate: Optional <%s> must NOT be void.*/
	public static <V, MP1> @Nullable String notVoidEx(@Nonnull OptByteTrait<?> opt, MP1 msgParamOnly) {
		Null.nonNullArg(opt, "opt");
		return Predicates.notVoidEx(opt);
	}

	/** Predicate: Optional <%s> must have value equal <%s>.*/
	public static <V, MP1> boolean valueEqual(@Nonnull OptByteTrait<?> opt, byte expected, MP1 msgParamOnly) {
		Null.nonNullArg(opt, "opt");
		return Predicates.valueEqual(opt, expected);
	}

	/** "Special" predicate: Optional <%s> must have value equal <%s>.*/
	public static <V, MP1> @Nullable String valueEqualEx(@Nonnull OptByteTrait<?> opt, byte expected, MP1 msgParamOnly) {
		Null.nonNullArg(opt, "opt");
		return Predicates.valueEqualEx(opt, expected);
	}

	/** Predicate: Optional <%s> must NOT have value equal <%s>.*/
	public static <V, MP1> boolean valueNotEqual(@Nonnull OptByteTrait<?> opt, byte expected, MP1 msgParamOnly) {
		Null.nonNullArg(opt, "opt");
		return Predicates.valueNotEqual(opt, expected);
	}

	/** "Special" predicate: Optional <%s> must NOT have value equal <%s>.*/
	public static <V, MP1> @Nullable String valueNotEqualEx(@Nonnull OptByteTrait<?> opt, byte expected, MP1 msgParamOnly) {
		Null.nonNullArg(opt, "opt");
		return Predicates.valueNotEqualEx(opt, expected);
	}

	/** Predicate: Optional <%s> must have value equal <%s>.*/
	public static <V, MP1> boolean valueEqual(@Nonnull ByteValueTrait<?> opt, byte expected, MP1 msgParamOnly) {
		Null.nonNullArg(opt, "opt");
		return Predicates.valueEqual(opt, expected);
	}

	/** "Special" predicate: Optional <%s> must have value equal <%s>.*/
	public static <V, MP1> @Nullable String valueEqualEx(@Nonnull ByteValueTrait<?> opt, byte expected, MP1 msgParamOnly) {
		Null.nonNullArg(opt, "opt");
		return Predicates.valueEqualEx(opt, expected);
	}

	/** Predicate: Optional <%s> must NOT have value equal <%s>.*/
	public static <V, MP1> boolean valueNotEqual(@Nonnull ByteValueTrait<?> opt, byte expected, MP1 msgParamOnly) {
		Null.nonNullArg(opt, "opt");
		return Predicates.valueNotEqual(opt, expected);
	}

	/** "Special" predicate: Optional <%s> must NOT have value equal <%s>.*/
	public static <V, MP1> @Nullable String valueNotEqualEx(@Nonnull ByteValueTrait<?> opt, byte expected, MP1 msgParamOnly) {
		Null.nonNullArg(opt, "opt");
		return Predicates.valueNotEqualEx(opt, expected);
	}

	/** Predicate: Optional <%s> must have value.*/
	public static <V, MP1> boolean valuePresent(@Nonnull OptDblTrait<?> opt, MP1 msgParamOnly) {
		Null.nonNullArg(opt, "opt");
		return Predicates.valuePresent(opt);
	}

	/** "Special" predicate: Optional <%s> must have value.*/
	public static <V, MP1> @Nullable String valuePresentEx(@Nonnull OptDblTrait<?> opt, MP1 msgParamOnly) {
		Null.nonNullArg(opt, "opt");
		return Predicates.valuePresentEx(opt);
	}

	/** Predicate: Optional <%s> must NOT have value.*/
	public static <V, MP1> boolean noValuePresent(@Nonnull OptDblTrait<?> opt, MP1 msgParamOnly) {
		Null.nonNullArg(opt, "opt");
		return Predicates.noValuePresent(opt);
	}

	/** "Special" predicate: Optional <%s> must NOT have value.*/
	public static <V, MP1> @Nullable String noValuePresentEx(@Nonnull OptDblTrait<?> opt, MP1 msgParamOnly) {
		Null.nonNullArg(opt, "opt");
		return Predicates.noValuePresentEx(opt);
	}

	/** Predicate: Optional <%s> must be void.*/
	public static <V, MP1> boolean Void(@Nonnull OptDblTrait<?> opt, MP1 msgParamOnly) {
		Null.nonNullArg(opt, "opt");
		return Predicates.Void(opt);
	}

	/** "Special" predicate: Optional <%s> must be void.*/
	public static <V, MP1> @Nullable String VoidEx(@Nonnull OptDblTrait<?> opt, MP1 msgParamOnly) {
		Null.nonNullArg(opt, "opt");
		return Predicates.VoidEx(opt);
	}

	/** Predicate: Optional <%s> must NOT be void.*/
	public static <V, MP1> boolean notVoid(@Nonnull OptDblTrait<?> opt, MP1 msgParamOnly) {
		Null.nonNullArg(opt, "opt");
		return Predicates.notVoid(opt);
	}

	/** "Special" predicate: Optional <%s> must NOT be void.*/
	public static <V, MP1> @Nullable String notVoidEx(@Nonnull OptDblTrait<?> opt, MP1 msgParamOnly) {
		Null.nonNullArg(opt, "opt");
		return Predicates.notVoidEx(opt);
	}

	/** Predicate: Optional <%s> must have value equal <%s>.*/
	public static <V, MP1> boolean valueEqual(@Nonnull OptDblTrait<?> opt, double expected, MP1 msgParamOnly) {
		Null.nonNullArg(opt, "opt");
		return Predicates.valueEqual(opt, expected);
	}

	/** "Special" predicate: Optional <%s> must have value equal <%s>.*/
	public static <V, MP1> @Nullable String valueEqualEx(@Nonnull OptDblTrait<?> opt, double expected, MP1 msgParamOnly) {
		Null.nonNullArg(opt, "opt");
		return Predicates.valueEqualEx(opt, expected);
	}

	/** Predicate: Optional <%s> must NOT have value equal <%s>.*/
	public static <V, MP1> boolean valueNotEqual(@Nonnull OptDblTrait<?> opt, double expected, MP1 msgParamOnly) {
		Null.nonNullArg(opt, "opt");
		return Predicates.valueNotEqual(opt, expected);
	}

	/** "Special" predicate: Optional <%s> must NOT have value equal <%s>.*/
	public static <V, MP1> @Nullable String valueNotEqualEx(@Nonnull OptDblTrait<?> opt, double expected, MP1 msgParamOnly) {
		Null.nonNullArg(opt, "opt");
		return Predicates.valueNotEqualEx(opt, expected);
	}

	/** Predicate: Optional <%s> must have value equal <%s>.*/
	public static <V, MP1> boolean valueEqual(@Nonnull DblValueTrait<?> opt, double expected, MP1 msgParamOnly) {
		Null.nonNullArg(opt, "opt");
		return Predicates.valueEqual(opt, expected);
	}

	/** "Special" predicate: Optional <%s> must have value equal <%s>.*/
	public static <V, MP1> @Nullable String valueEqualEx(@Nonnull DblValueTrait<?> opt, double expected, MP1 msgParamOnly) {
		Null.nonNullArg(opt, "opt");
		return Predicates.valueEqualEx(opt, expected);
	}

	/** Predicate: Optional <%s> must NOT have value equal <%s>.*/
	public static <V, MP1> boolean valueNotEqual(@Nonnull DblValueTrait<?> opt, double expected, MP1 msgParamOnly) {
		Null.nonNullArg(opt, "opt");
		return Predicates.valueNotEqual(opt, expected);
	}

	/** "Special" predicate: Optional <%s> must NOT have value equal <%s>.*/
	public static <V, MP1> @Nullable String valueNotEqualEx(@Nonnull DblValueTrait<?> opt, double expected, MP1 msgParamOnly) {
		Null.nonNullArg(opt, "opt");
		return Predicates.valueNotEqualEx(opt, expected);
	}

	/** Predicate: Optional <%s> must have value.*/
	public static <V, MP1> boolean valuePresent(@Nonnull OptCharTrait<?> opt, MP1 msgParamOnly) {
		Null.nonNullArg(opt, "opt");
		return Predicates.valuePresent(opt);
	}

	/** "Special" predicate: Optional <%s> must have value.*/
	public static <V, MP1> @Nullable String valuePresentEx(@Nonnull OptCharTrait<?> opt, MP1 msgParamOnly) {
		Null.nonNullArg(opt, "opt");
		return Predicates.valuePresentEx(opt);
	}

	/** Predicate: Optional <%s> must NOT have value.*/
	public static <V, MP1> boolean noValuePresent(@Nonnull OptCharTrait<?> opt, MP1 msgParamOnly) {
		Null.nonNullArg(opt, "opt");
		return Predicates.noValuePresent(opt);
	}

	/** "Special" predicate: Optional <%s> must NOT have value.*/
	public static <V, MP1> @Nullable String noValuePresentEx(@Nonnull OptCharTrait<?> opt, MP1 msgParamOnly) {
		Null.nonNullArg(opt, "opt");
		return Predicates.noValuePresentEx(opt);
	}

	/** Predicate: Optional <%s> must be void.*/
	public static <V, MP1> boolean Void(@Nonnull OptCharTrait<?> opt, MP1 msgParamOnly) {
		Null.nonNullArg(opt, "opt");
		return Predicates.Void(opt);
	}

	/** "Special" predicate: Optional <%s> must be void.*/
	public static <V, MP1> @Nullable String VoidEx(@Nonnull OptCharTrait<?> opt, MP1 msgParamOnly) {
		Null.nonNullArg(opt, "opt");
		return Predicates.VoidEx(opt);
	}

	/** Predicate: Optional <%s> must NOT be void.*/
	public static <V, MP1> boolean notVoid(@Nonnull OptCharTrait<?> opt, MP1 msgParamOnly) {
		Null.nonNullArg(opt, "opt");
		return Predicates.notVoid(opt);
	}

	/** "Special" predicate: Optional <%s> must NOT be void.*/
	public static <V, MP1> @Nullable String notVoidEx(@Nonnull OptCharTrait<?> opt, MP1 msgParamOnly) {
		Null.nonNullArg(opt, "opt");
		return Predicates.notVoidEx(opt);
	}

	/** Predicate: Optional <%s> must have value equal <%s>.*/
	public static <V, MP1> boolean valueEqual(@Nonnull OptCharTrait<?> opt, char expected, MP1 msgParamOnly) {
		Null.nonNullArg(opt, "opt");
		return Predicates.valueEqual(opt, expected);
	}

	/** "Special" predicate: Optional <%s> must have value equal <%s>.*/
	public static <V, MP1> @Nullable String valueEqualEx(@Nonnull OptCharTrait<?> opt, char expected, MP1 msgParamOnly) {
		Null.nonNullArg(opt, "opt");
		return Predicates.valueEqualEx(opt, expected);
	}

	/** Predicate: Optional <%s> must NOT have value equal <%s>.*/
	public static <V, MP1> boolean valueNotEqual(@Nonnull OptCharTrait<?> opt, char expected, MP1 msgParamOnly) {
		Null.nonNullArg(opt, "opt");
		return Predicates.valueNotEqual(opt, expected);
	}

	/** "Special" predicate: Optional <%s> must NOT have value equal <%s>.*/
	public static <V, MP1> @Nullable String valueNotEqualEx(@Nonnull OptCharTrait<?> opt, char expected, MP1 msgParamOnly) {
		Null.nonNullArg(opt, "opt");
		return Predicates.valueNotEqualEx(opt, expected);
	}

	/** Predicate: Optional <%s> must have value equal <%s>.*/
	public static <V, MP1> boolean valueEqual(@Nonnull CharValueTrait<?> opt, char expected, MP1 msgParamOnly) {
		Null.nonNullArg(opt, "opt");
		return Predicates.valueEqual(opt, expected);
	}

	/** "Special" predicate: Optional <%s> must have value equal <%s>.*/
	public static <V, MP1> @Nullable String valueEqualEx(@Nonnull CharValueTrait<?> opt, char expected, MP1 msgParamOnly) {
		Null.nonNullArg(opt, "opt");
		return Predicates.valueEqualEx(opt, expected);
	}

	/** Predicate: Optional <%s> must NOT have value equal <%s>.*/
	public static <V, MP1> boolean valueNotEqual(@Nonnull CharValueTrait<?> opt, char expected, MP1 msgParamOnly) {
		Null.nonNullArg(opt, "opt");
		return Predicates.valueNotEqual(opt, expected);
	}

	/** "Special" predicate: Optional <%s> must NOT have value equal <%s>.*/
	public static <V, MP1> @Nullable String valueNotEqualEx(@Nonnull CharValueTrait<?> opt, char expected, MP1 msgParamOnly) {
		Null.nonNullArg(opt, "opt");
		return Predicates.valueNotEqualEx(opt, expected);
	}

	/** Predicate: Optional <%s> must have value.*/
	public static <V, MP1> boolean valuePresent(@Nonnull OptSrtTrait<?> opt, MP1 msgParamOnly) {
		Null.nonNullArg(opt, "opt");
		return Predicates.valuePresent(opt);
	}

	/** "Special" predicate: Optional <%s> must have value.*/
	public static <V, MP1> @Nullable String valuePresentEx(@Nonnull OptSrtTrait<?> opt, MP1 msgParamOnly) {
		Null.nonNullArg(opt, "opt");
		return Predicates.valuePresentEx(opt);
	}

	/** Predicate: Optional <%s> must NOT have value.*/
	public static <V, MP1> boolean noValuePresent(@Nonnull OptSrtTrait<?> opt, MP1 msgParamOnly) {
		Null.nonNullArg(opt, "opt");
		return Predicates.noValuePresent(opt);
	}

	/** "Special" predicate: Optional <%s> must NOT have value.*/
	public static <V, MP1> @Nullable String noValuePresentEx(@Nonnull OptSrtTrait<?> opt, MP1 msgParamOnly) {
		Null.nonNullArg(opt, "opt");
		return Predicates.noValuePresentEx(opt);
	}

	/** Predicate: Optional <%s> must be void.*/
	public static <V, MP1> boolean Void(@Nonnull OptSrtTrait<?> opt, MP1 msgParamOnly) {
		Null.nonNullArg(opt, "opt");
		return Predicates.Void(opt);
	}

	/** "Special" predicate: Optional <%s> must be void.*/
	public static <V, MP1> @Nullable String VoidEx(@Nonnull OptSrtTrait<?> opt, MP1 msgParamOnly) {
		Null.nonNullArg(opt, "opt");
		return Predicates.VoidEx(opt);
	}

	/** Predicate: Optional <%s> must NOT be void.*/
	public static <V, MP1> boolean notVoid(@Nonnull OptSrtTrait<?> opt, MP1 msgParamOnly) {
		Null.nonNullArg(opt, "opt");
		return Predicates.notVoid(opt);
	}

	/** "Special" predicate: Optional <%s> must NOT be void.*/
	public static <V, MP1> @Nullable String notVoidEx(@Nonnull OptSrtTrait<?> opt, MP1 msgParamOnly) {
		Null.nonNullArg(opt, "opt");
		return Predicates.notVoidEx(opt);
	}

	/** Predicate: Optional <%s> must have value equal <%s>.*/
	public static <V, MP1> boolean valueEqual(@Nonnull OptSrtTrait<?> opt, short expected, MP1 msgParamOnly) {
		Null.nonNullArg(opt, "opt");
		return Predicates.valueEqual(opt, expected);
	}

	/** "Special" predicate: Optional <%s> must have value equal <%s>.*/
	public static <V, MP1> @Nullable String valueEqualEx(@Nonnull OptSrtTrait<?> opt, short expected, MP1 msgParamOnly) {
		Null.nonNullArg(opt, "opt");
		return Predicates.valueEqualEx(opt, expected);
	}

	/** Predicate: Optional <%s> must NOT have value equal <%s>.*/
	public static <V, MP1> boolean valueNotEqual(@Nonnull OptSrtTrait<?> opt, short expected, MP1 msgParamOnly) {
		Null.nonNullArg(opt, "opt");
		return Predicates.valueNotEqual(opt, expected);
	}

	/** "Special" predicate: Optional <%s> must NOT have value equal <%s>.*/
	public static <V, MP1> @Nullable String valueNotEqualEx(@Nonnull OptSrtTrait<?> opt, short expected, MP1 msgParamOnly) {
		Null.nonNullArg(opt, "opt");
		return Predicates.valueNotEqualEx(opt, expected);
	}

	/** Predicate: Optional <%s> must have value equal <%s>.*/
	public static <V, MP1> boolean valueEqual(@Nonnull SrtValueTrait<?> opt, short expected, MP1 msgParamOnly) {
		Null.nonNullArg(opt, "opt");
		return Predicates.valueEqual(opt, expected);
	}

	/** "Special" predicate: Optional <%s> must have value equal <%s>.*/
	public static <V, MP1> @Nullable String valueEqualEx(@Nonnull SrtValueTrait<?> opt, short expected, MP1 msgParamOnly) {
		Null.nonNullArg(opt, "opt");
		return Predicates.valueEqualEx(opt, expected);
	}

	/** Predicate: Optional <%s> must NOT have value equal <%s>.*/
	public static <V, MP1> boolean valueNotEqual(@Nonnull SrtValueTrait<?> opt, short expected, MP1 msgParamOnly) {
		Null.nonNullArg(opt, "opt");
		return Predicates.valueNotEqual(opt, expected);
	}

	/** "Special" predicate: Optional <%s> must NOT have value equal <%s>.*/
	public static <V, MP1> @Nullable String valueNotEqualEx(@Nonnull SrtValueTrait<?> opt, short expected, MP1 msgParamOnly) {
		Null.nonNullArg(opt, "opt");
		return Predicates.valueNotEqualEx(opt, expected);
	}

	/** Predicate: Optional <%s> must have value.*/
	public static <V, MP1> boolean valuePresent(@Nonnull OptFltTrait<?> opt, MP1 msgParamOnly) {
		Null.nonNullArg(opt, "opt");
		return Predicates.valuePresent(opt);
	}

	/** "Special" predicate: Optional <%s> must have value.*/
	public static <V, MP1> @Nullable String valuePresentEx(@Nonnull OptFltTrait<?> opt, MP1 msgParamOnly) {
		Null.nonNullArg(opt, "opt");
		return Predicates.valuePresentEx(opt);
	}

	/** Predicate: Optional <%s> must NOT have value.*/
	public static <V, MP1> boolean noValuePresent(@Nonnull OptFltTrait<?> opt, MP1 msgParamOnly) {
		Null.nonNullArg(opt, "opt");
		return Predicates.noValuePresent(opt);
	}

	/** "Special" predicate: Optional <%s> must NOT have value.*/
	public static <V, MP1> @Nullable String noValuePresentEx(@Nonnull OptFltTrait<?> opt, MP1 msgParamOnly) {
		Null.nonNullArg(opt, "opt");
		return Predicates.noValuePresentEx(opt);
	}

	/** Predicate: Optional <%s> must be void.*/
	public static <V, MP1> boolean Void(@Nonnull OptFltTrait<?> opt, MP1 msgParamOnly) {
		Null.nonNullArg(opt, "opt");
		return Predicates.Void(opt);
	}

	/** "Special" predicate: Optional <%s> must be void.*/
	public static <V, MP1> @Nullable String VoidEx(@Nonnull OptFltTrait<?> opt, MP1 msgParamOnly) {
		Null.nonNullArg(opt, "opt");
		return Predicates.VoidEx(opt);
	}

	/** Predicate: Optional <%s> must NOT be void.*/
	public static <V, MP1> boolean notVoid(@Nonnull OptFltTrait<?> opt, MP1 msgParamOnly) {
		Null.nonNullArg(opt, "opt");
		return Predicates.notVoid(opt);
	}

	/** "Special" predicate: Optional <%s> must NOT be void.*/
	public static <V, MP1> @Nullable String notVoidEx(@Nonnull OptFltTrait<?> opt, MP1 msgParamOnly) {
		Null.nonNullArg(opt, "opt");
		return Predicates.notVoidEx(opt);
	}

	/** Predicate: Optional <%s> must have value equal <%s>.*/
	public static <V, MP1> boolean valueEqual(@Nonnull OptFltTrait<?> opt, float expected, MP1 msgParamOnly) {
		Null.nonNullArg(opt, "opt");
		return Predicates.valueEqual(opt, expected);
	}

	/** "Special" predicate: Optional <%s> must have value equal <%s>.*/
	public static <V, MP1> @Nullable String valueEqualEx(@Nonnull OptFltTrait<?> opt, float expected, MP1 msgParamOnly) {
		Null.nonNullArg(opt, "opt");
		return Predicates.valueEqualEx(opt, expected);
	}

	/** Predicate: Optional <%s> must NOT have value equal <%s>.*/
	public static <V, MP1> boolean valueNotEqual(@Nonnull OptFltTrait<?> opt, float expected, MP1 msgParamOnly) {
		Null.nonNullArg(opt, "opt");
		return Predicates.valueNotEqual(opt, expected);
	}

	/** "Special" predicate: Optional <%s> must NOT have value equal <%s>.*/
	public static <V, MP1> @Nullable String valueNotEqualEx(@Nonnull OptFltTrait<?> opt, float expected, MP1 msgParamOnly) {
		Null.nonNullArg(opt, "opt");
		return Predicates.valueNotEqualEx(opt, expected);
	}

	/** Predicate: Optional <%s> must have value equal <%s>.*/
	public static <V, MP1> boolean valueEqual(@Nonnull FltValueTrait<?> opt, float expected, MP1 msgParamOnly) {
		Null.nonNullArg(opt, "opt");
		return Predicates.valueEqual(opt, expected);
	}

	/** "Special" predicate: Optional <%s> must have value equal <%s>.*/
	public static <V, MP1> @Nullable String valueEqualEx(@Nonnull FltValueTrait<?> opt, float expected, MP1 msgParamOnly) {
		Null.nonNullArg(opt, "opt");
		return Predicates.valueEqualEx(opt, expected);
	}

	/** Predicate: Optional <%s> must NOT have value equal <%s>.*/
	public static <V, MP1> boolean valueNotEqual(@Nonnull FltValueTrait<?> opt, float expected, MP1 msgParamOnly) {
		Null.nonNullArg(opt, "opt");
		return Predicates.valueNotEqual(opt, expected);
	}

	/** "Special" predicate: Optional <%s> must NOT have value equal <%s>.*/
	public static <V, MP1> @Nullable String valueNotEqualEx(@Nonnull FltValueTrait<?> opt, float expected, MP1 msgParamOnly) {
		Null.nonNullArg(opt, "opt");
		return Predicates.valueNotEqualEx(opt, expected);
	}

	/** Predicate: Optional <%s> must have value.*/
	public static <V, MP1> boolean valuePresent(@Nonnull OptIntTrait<?> opt, MP1 msgParamOnly) {
		Null.nonNullArg(opt, "opt");
		return Predicates.valuePresent(opt);
	}

	/** "Special" predicate: Optional <%s> must have value.*/
	public static <V, MP1> @Nullable String valuePresentEx(@Nonnull OptIntTrait<?> opt, MP1 msgParamOnly) {
		Null.nonNullArg(opt, "opt");
		return Predicates.valuePresentEx(opt);
	}

	/** Predicate: Optional <%s> must NOT have value.*/
	public static <V, MP1> boolean noValuePresent(@Nonnull OptIntTrait<?> opt, MP1 msgParamOnly) {
		Null.nonNullArg(opt, "opt");
		return Predicates.noValuePresent(opt);
	}

	/** "Special" predicate: Optional <%s> must NOT have value.*/
	public static <V, MP1> @Nullable String noValuePresentEx(@Nonnull OptIntTrait<?> opt, MP1 msgParamOnly) {
		Null.nonNullArg(opt, "opt");
		return Predicates.noValuePresentEx(opt);
	}

	/** Predicate: Optional <%s> must be void.*/
	public static <V, MP1> boolean Void(@Nonnull OptIntTrait<?> opt, MP1 msgParamOnly) {
		Null.nonNullArg(opt, "opt");
		return Predicates.Void(opt);
	}

	/** "Special" predicate: Optional <%s> must be void.*/
	public static <V, MP1> @Nullable String VoidEx(@Nonnull OptIntTrait<?> opt, MP1 msgParamOnly) {
		Null.nonNullArg(opt, "opt");
		return Predicates.VoidEx(opt);
	}

	/** Predicate: Optional <%s> must NOT be void.*/
	public static <V, MP1> boolean notVoid(@Nonnull OptIntTrait<?> opt, MP1 msgParamOnly) {
		Null.nonNullArg(opt, "opt");
		return Predicates.notVoid(opt);
	}

	/** "Special" predicate: Optional <%s> must NOT be void.*/
	public static <V, MP1> @Nullable String notVoidEx(@Nonnull OptIntTrait<?> opt, MP1 msgParamOnly) {
		Null.nonNullArg(opt, "opt");
		return Predicates.notVoidEx(opt);
	}

	/** Predicate: Optional <%s> must have value equal <%s>.*/
	public static <V, MP1> boolean valueEqual(@Nonnull OptIntTrait<?> opt, int expected, MP1 msgParamOnly) {
		Null.nonNullArg(opt, "opt");
		return Predicates.valueEqual(opt, expected);
	}

	/** "Special" predicate: Optional <%s> must have value equal <%s>.*/
	public static <V, MP1> @Nullable String valueEqualEx(@Nonnull OptIntTrait<?> opt, int expected, MP1 msgParamOnly) {
		Null.nonNullArg(opt, "opt");
		return Predicates.valueEqualEx(opt, expected);
	}

	/** Predicate: Optional <%s> must NOT have value equal <%s>.*/
	public static <V, MP1> boolean valueNotEqual(@Nonnull OptIntTrait<?> opt, int expected, MP1 msgParamOnly) {
		Null.nonNullArg(opt, "opt");
		return Predicates.valueNotEqual(opt, expected);
	}

	/** "Special" predicate: Optional <%s> must NOT have value equal <%s>.*/
	public static <V, MP1> @Nullable String valueNotEqualEx(@Nonnull OptIntTrait<?> opt, int expected, MP1 msgParamOnly) {
		Null.nonNullArg(opt, "opt");
		return Predicates.valueNotEqualEx(opt, expected);
	}

	/** Predicate: Optional <%s> must have value equal <%s>.*/
	public static <V, MP1> boolean valueEqual(@Nonnull IntValueTrait<?> opt, int expected, MP1 msgParamOnly) {
		Null.nonNullArg(opt, "opt");
		return Predicates.valueEqual(opt, expected);
	}

	/** "Special" predicate: Optional <%s> must have value equal <%s>.*/
	public static <V, MP1> @Nullable String valueEqualEx(@Nonnull IntValueTrait<?> opt, int expected, MP1 msgParamOnly) {
		Null.nonNullArg(opt, "opt");
		return Predicates.valueEqualEx(opt, expected);
	}

	/** Predicate: Optional <%s> must NOT have value equal <%s>.*/
	public static <V, MP1> boolean valueNotEqual(@Nonnull IntValueTrait<?> opt, int expected, MP1 msgParamOnly) {
		Null.nonNullArg(opt, "opt");
		return Predicates.valueNotEqual(opt, expected);
	}

	/** "Special" predicate: Optional <%s> must NOT have value equal <%s>.*/
	public static <V, MP1> @Nullable String valueNotEqualEx(@Nonnull IntValueTrait<?> opt, int expected, MP1 msgParamOnly) {
		Null.nonNullArg(opt, "opt");
		return Predicates.valueNotEqualEx(opt, expected);
	}

	/** Predicate: Optional <%s> must have value.*/
	public static <V, MP1> boolean valuePresent(@Nonnull OptLongTrait<?> opt, MP1 msgParamOnly) {
		Null.nonNullArg(opt, "opt");
		return Predicates.valuePresent(opt);
	}

	/** "Special" predicate: Optional <%s> must have value.*/
	public static <V, MP1> @Nullable String valuePresentEx(@Nonnull OptLongTrait<?> opt, MP1 msgParamOnly) {
		Null.nonNullArg(opt, "opt");
		return Predicates.valuePresentEx(opt);
	}

	/** Predicate: Optional <%s> must NOT have value.*/
	public static <V, MP1> boolean noValuePresent(@Nonnull OptLongTrait<?> opt, MP1 msgParamOnly) {
		Null.nonNullArg(opt, "opt");
		return Predicates.noValuePresent(opt);
	}

	/** "Special" predicate: Optional <%s> must NOT have value.*/
	public static <V, MP1> @Nullable String noValuePresentEx(@Nonnull OptLongTrait<?> opt, MP1 msgParamOnly) {
		Null.nonNullArg(opt, "opt");
		return Predicates.noValuePresentEx(opt);
	}

	/** Predicate: Optional <%s> must be void.*/
	public static <V, MP1> boolean Void(@Nonnull OptLongTrait<?> opt, MP1 msgParamOnly) {
		Null.nonNullArg(opt, "opt");
		return Predicates.Void(opt);
	}

	/** "Special" predicate: Optional <%s> must be void.*/
	public static <V, MP1> @Nullable String VoidEx(@Nonnull OptLongTrait<?> opt, MP1 msgParamOnly) {
		Null.nonNullArg(opt, "opt");
		return Predicates.VoidEx(opt);
	}

	/** Predicate: Optional <%s> must NOT be void.*/
	public static <V, MP1> boolean notVoid(@Nonnull OptLongTrait<?> opt, MP1 msgParamOnly) {
		Null.nonNullArg(opt, "opt");
		return Predicates.notVoid(opt);
	}

	/** "Special" predicate: Optional <%s> must NOT be void.*/
	public static <V, MP1> @Nullable String notVoidEx(@Nonnull OptLongTrait<?> opt, MP1 msgParamOnly) {
		Null.nonNullArg(opt, "opt");
		return Predicates.notVoidEx(opt);
	}

	/** Predicate: Optional <%s> must have value equal <%s>.*/
	public static <V, MP1> boolean valueEqual(@Nonnull OptLongTrait<?> opt, long expected, MP1 msgParamOnly) {
		Null.nonNullArg(opt, "opt");
		return Predicates.valueEqual(opt, expected);
	}

	/** "Special" predicate: Optional <%s> must have value equal <%s>.*/
	public static <V, MP1> @Nullable String valueEqualEx(@Nonnull OptLongTrait<?> opt, long expected, MP1 msgParamOnly) {
		Null.nonNullArg(opt, "opt");
		return Predicates.valueEqualEx(opt, expected);
	}

	/** Predicate: Optional <%s> must NOT have value equal <%s>.*/
	public static <V, MP1> boolean valueNotEqual(@Nonnull OptLongTrait<?> opt, long expected, MP1 msgParamOnly) {
		Null.nonNullArg(opt, "opt");
		return Predicates.valueNotEqual(opt, expected);
	}

	/** "Special" predicate: Optional <%s> must NOT have value equal <%s>.*/
	public static <V, MP1> @Nullable String valueNotEqualEx(@Nonnull OptLongTrait<?> opt, long expected, MP1 msgParamOnly) {
		Null.nonNullArg(opt, "opt");
		return Predicates.valueNotEqualEx(opt, expected);
	}

	/** Predicate: Optional <%s> must have value equal <%s>.*/
	public static <V, MP1> boolean valueEqual(@Nonnull LongValueTrait<?> opt, long expected, MP1 msgParamOnly) {
		Null.nonNullArg(opt, "opt");
		return Predicates.valueEqual(opt, expected);
	}

	/** "Special" predicate: Optional <%s> must have value equal <%s>.*/
	public static <V, MP1> @Nullable String valueEqualEx(@Nonnull LongValueTrait<?> opt, long expected, MP1 msgParamOnly) {
		Null.nonNullArg(opt, "opt");
		return Predicates.valueEqualEx(opt, expected);
	}

	/** Predicate: Optional <%s> must NOT have value equal <%s>.*/
	public static <V, MP1> boolean valueNotEqual(@Nonnull LongValueTrait<?> opt, long expected, MP1 msgParamOnly) {
		Null.nonNullArg(opt, "opt");
		return Predicates.valueNotEqual(opt, expected);
	}

	/** "Special" predicate: Optional <%s> must NOT have value equal <%s>.*/
	public static <V, MP1> @Nullable String valueNotEqualEx(@Nonnull LongValueTrait<?> opt, long expected, MP1 msgParamOnly) {
		Null.nonNullArg(opt, "opt");
		return Predicates.valueNotEqualEx(opt, expected);
	}

	//</editor-fold>

	/** Predicate: <%s> must be equal to <%s> (including array elements).*/
	public static <MP1> boolean Equal(Object o1, Object o2, MP1 msgParamOnly) {
		return Predicates.Equal(o1, o2);
	}

	/** "Special" predicate: <%s> must be equal to <%s> (including array elements).*/
	public static <MP1> @Nullable String EqualEx(Object o1, Object o2, MP1 msgParamOnly) {
		return Predicates.EqualEx(o1, o2);
	}

	/** Predicate: <%s> must NOT be equal to <%s> (including array elements).*/
	public static <MP1> boolean NotEqual(Object o1, Object o2, MP1 msgParamOnly) {
		return Predicates.NotEqual(o1, o2);
	}

	/** "Special" predicate: <%s> must NOT be equal to <%s> (including array elements).*/
	public static <MP1> @Nullable String NotEqualEx(Object o1, Object o2, MP1 msgParamOnly) {
		return Predicates.NotEqualEx(o1, o2);
	}

	//<editor-fold desc="Future">

	/** Predicate: <%s> must be done.*/
	public static <MP1> boolean done(@Nonnull Future<?> future, MP1 msgParamOnly) {
		Null.nonNullArg(future, "future");
		return Predicates.done(future);
	}

	/** "Special" predicate: <%s> must be done.*/
	public static <MP1> @Nullable String doneEx(@Nonnull Future<?> future, MP1 msgParamOnly) {
		Null.nonNullArg(future, "future");
		return Predicates.doneEx(future);
	}

	/** Predicate: <%s> must NOT be done.*/
	public static <MP1> boolean notDone(@Nonnull Future<?> future, MP1 msgParamOnly) {
		Null.nonNullArg(future, "future");
		return Predicates.notDone(future);
	}

	/** "Special" predicate: <%s> must NOT be done.*/
	public static <MP1> @Nullable String notDoneEx(@Nonnull Future<?> future, MP1 msgParamOnly) {
		Null.nonNullArg(future, "future");
		return Predicates.notDoneEx(future);
	}

	/** Predicate: <%s> must be cancelled.*/
	public static <MP1> boolean cancelled(@Nonnull Future<?> future, MP1 msgParamOnly) {
		Null.nonNullArg(future, "future");
		return Predicates.cancelled(future);
	}

	/** "Special" predicate: <%s> must be cancelled.*/
	public static <MP1> @Nullable String cancelledEx(@Nonnull Future<?> future, MP1 msgParamOnly) {
		Null.nonNullArg(future, "future");
		return Predicates.cancelledEx(future);
	}

	/** Predicate: <%s> must NOT be cancelled.*/
	public static <MP1> boolean notCancelled(@Nonnull Future<?> future, MP1 msgParamOnly) {
		Null.nonNullArg(future, "future");
		return Predicates.notCancelled(future);
	}

	/** "Special" predicate: <%s> must NOT be cancelled.*/
	public static <MP1> @Nullable String notCancelledEx(@Nonnull Future<?> future, MP1 msgParamOnly) {
		Null.nonNullArg(future, "future");
		return Predicates.notCancelledEx(future);
	}

	/** Predicate: <%s> must be cancelled.*/
	public static <MP1> boolean completedExceptionally(@Nonnull CompletableFuture<?> future, MP1 msgParamOnly) {
		Null.nonNullArg(future, "future");
		return Predicates.completedExceptionally(future);
	}

	/** "Special" predicate: <%s> must be cancelled.*/
	public static <MP1> @Nullable String completedExceptionallyEx(@Nonnull CompletableFuture<?> future, MP1 msgParamOnly) {
		Null.nonNullArg(future, "future");
		return Predicates.completedExceptionallyEx(future);
	}

	/** Predicate: <%s> must NOT be cancelled.*/
	public static <MP1> boolean notCompletedExceptionally(@Nonnull CompletableFuture<?> future, MP1 msgParamOnly) {
		Null.nonNullArg(future, "future");
		return Predicates.notCompletedExceptionally(future);
	}

	/** "Special" predicate: <%s> must NOT be cancelled.*/
	public static <MP1> @Nullable String notCompletedExceptionallyEx(@Nonnull CompletableFuture<?> future, MP1 msgParamOnly) {
		Null.nonNullArg(future, "future");
		return Predicates.notCompletedExceptionallyEx(future);
	}

	/** Predicate: <%s> must produce value equal to <%s>.*/
	public static <MP1> boolean produce(@Nonnull Future<?> future, Object expected, MP1 msgParamOnly) {
		Null.nonNullArg(future, "future");
		return Predicates.produce(future, expected);
	}

	/** "Special" predicate: <%s> must produce value equal to <%s>.*/
	public static <MP1> @Nullable String produceEx(@Nonnull Future<?> future, Object expected, MP1 msgParamOnly) {
		Null.nonNullArg(future, "future");
		return Predicates.produceEx(future, expected);
	}

	/** Predicate: <%s> must NOT produce value equal to <%s>.*/
	public static <MP1> boolean notProduce(@Nonnull Future<?> future, Object expected, MP1 msgParamOnly) {
		Null.nonNullArg(future, "future");
		return Predicates.notProduce(future, expected);
	}

	/** "Special" predicate: <%s> must NOT produce value equal to <%s>.*/
	public static <MP1> @Nullable String notProduceEx(@Nonnull Future<?> future, Object expected, MP1 msgParamOnly) {
		Null.nonNullArg(future, "future");
		return Predicates.notProduceEx(future, expected);
	}

	//</editor-fold>

}
