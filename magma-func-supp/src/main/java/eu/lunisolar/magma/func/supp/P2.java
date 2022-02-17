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
 * This class contains some predicates that take - and ignore - additional argument(s) that are intended only for building a message.
 *
 * @see {@link P}, {@link Is}, {@link Does}, {@link Be}, {@link Are}
 */
public final class P2 implements FluentSyntax {
	// <editor-fold desc="no instance">
	private P2() {
	}
	// </editor-fold>

	// <editor-fold desc="Object">

	/** Predicate: Object <%s> must be the same as <%s>. Available in {@link P}, {@link Is}, {@link Be}, {@link P1}, {@link P2}.*/
	public static <MP1, MP2> boolean same(@Nullable Object n, @Nullable Object other, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return P.same(n, other);
	}

	/** "Special" predicate: Object <%s> must be the same as <%s>. Available in {@link P}, {@link Is}, {@link Be}, {@link P1}, {@link P2}.*/
	public static <MP1, MP2> @Nullable String sameEx(@Nullable Object n, @Nullable Object other, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return P.sameEx(n, other);
	}
	/** Predicate: Object <%s> must NOT be the same as <%s>. Available in {@link P}, {@link Is}, {@link Be}, {@link P1}, {@link P2}.*/
	public static <MP1, MP2> boolean notSame(@Nullable Object n, @Nullable Object other, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return P.notSame(n, other);
	}

	/** "Special" predicate: Object <%s> must NOT be the same as <%s>. Available in {@link P}, {@link Is}, {@link Be}, {@link P1}, {@link P2}.*/
	public static <MP1, MP2> @Nullable String notSameEx(@Nullable Object n, @Nullable Object other, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return P.notSameEx(n, other);
	}

	/** Predicate: Reference must be null, currently is pointing to <%s>. Available in {@link P}, {@link Is}, {@link Be}, {@link P1}, {@link P2}.*/
	public static <MP1, MP2> boolean Null(@Nullable Object n, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return P.Null(n);
	}

	/** "Special" predicate: Reference must be null, currently is pointing to <%s>. Available in {@link P}, {@link Is}, {@link Be}, {@link P1}, {@link P2}.*/
	public static <MP1, MP2> @Nullable String NullEx(@Nullable Object n, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return P.NullEx(n);
	}
	/** Predicate: Reference must NOT be null, currently is pointing to <%s>. Available in {@link P}, {@link Is}, {@link Be}, {@link P1}, {@link P2}.*/
	public static <MP1, MP2> boolean notNull(@Nullable Object n, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return P.notNull(n);
	}

	/** "Special" predicate: Reference must NOT be null, currently is pointing to <%s>. Available in {@link P}, {@link Is}, {@link Be}, {@link P1}, {@link P2}.*/
	public static <MP1, MP2> @Nullable String notNullEx(@Nullable Object n, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return P.notNullEx(n);
	}

	// </editor-fold>

	// <editor-fold desc="`String`">

	/** Predicate: String <'%s'> must be empty. Available in {@link P}, {@link Is}, {@link Be}, {@link P1}, {@link P2}.*/
	public static <MP1, MP2> boolean empty(@Nonnull String n, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(n, "n");
		return P.empty(n);
	}

	/** "Special" predicate: String <'%s'> must be empty. Available in {@link P}, {@link Is}, {@link Be}, {@link P1}, {@link P2}.*/
	public static <MP1, MP2> @Nullable String emptyEx(@Nonnull String n, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(n, "n");
		return P.emptyEx(n);
	}
	/** Predicate: String <'%s'> must NOT be empty. Available in {@link P}, {@link Is}, {@link Be}, {@link P1}, {@link P2}.*/
	public static <MP1, MP2> boolean notEmpty(@Nonnull String n, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(n, "n");
		return P.notEmpty(n);
	}

	/** "Special" predicate: String <'%s'> must NOT be empty. Available in {@link P}, {@link Is}, {@link Be}, {@link P1}, {@link P2}.*/
	public static <MP1, MP2> @Nullable String notEmptyEx(@Nonnull String n, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(n, "n");
		return P.notEmptyEx(n);
	}

	/** Predicate: String <'%s'> must be blank (empty or consisting of only white characters). Available in {@link P}, {@link Is}, {@link Be}, {@link P1}, {@link P2}.*/
	public static <MP1, MP2> boolean blank(@Nonnull String n, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(n, "n");
		return P.blank(n);
	}

	/** "Special" predicate: String <'%s'> must be blank (empty or consisting of only white characters). Available in {@link P}, {@link Is}, {@link Be}, {@link P1}, {@link P2}.*/
	public static <MP1, MP2> @Nullable String blankEx(@Nonnull String n, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(n, "n");
		return P.blankEx(n);
	}
	/** Predicate: String <'%s'> must NOT be blank (empty or consisting of only white characters). Available in {@link P}, {@link Is}, {@link Be}, {@link P1}, {@link P2}.*/
	public static <MP1, MP2> boolean notBlank(@Nonnull String n, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(n, "n");
		return P.notBlank(n);
	}

	/** "Special" predicate: String <'%s'> must NOT be blank (empty or consisting of only white characters). Available in {@link P}, {@link Is}, {@link Be}, {@link P1}, {@link P2}.*/
	public static <MP1, MP2> @Nullable String notBlankEx(@Nonnull String n, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(n, "n");
		return P.notBlankEx(n);
	}

	/** Predicate: String <'%s'> must be null or empty. Available in {@link P}, {@link Is}, {@link Be}, {@link P1}, {@link P2}.*/
	public static <MP1, MP2> boolean nullOrEmpty(@Nonnull String n, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return P.nullOrEmpty(n);
	}

	/** "Special" predicate: String <'%s'> must be null or empty. Available in {@link P}, {@link Is}, {@link Be}, {@link P1}, {@link P2}.*/
	public static <MP1, MP2> @Nullable String nullOrEmptyEx(@Nonnull String n, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return P.nullOrEmptyEx(n);
	}
	/** Predicate: String <'%s'> must NOT be null or empty. Available in {@link P}, {@link Is}, {@link Be}, {@link P1}, {@link P2}.*/
	public static <MP1, MP2> boolean notNullNorEmpty(@Nonnull String n, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return P.notNullNorEmpty(n);
	}

	/** "Special" predicate: String <'%s'> must NOT be null or empty. Available in {@link P}, {@link Is}, {@link Be}, {@link P1}, {@link P2}.*/
	public static <MP1, MP2> @Nullable String notNullNorEmptyEx(@Nonnull String n, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return P.notNullNorEmptyEx(n);
	}

	/** Predicate: String <'%s'> must be null or blank. Available in {@link P}, {@link Is}, {@link Be}, {@link P1}, {@link P2}.*/
	public static <MP1, MP2> boolean nullOrBlank(@Nonnull String n, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return P.nullOrBlank(n);
	}

	/** "Special" predicate: String <'%s'> must be null or blank. Available in {@link P}, {@link Is}, {@link Be}, {@link P1}, {@link P2}.*/
	public static <MP1, MP2> @Nullable String nullOrBlankEx(@Nonnull String n, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return P.nullOrBlankEx(n);
	}
	/** Predicate: String <'%s'> must NOT be null or blank. Available in {@link P}, {@link Is}, {@link Be}, {@link P1}, {@link P2}.*/
	public static <MP1, MP2> boolean notNullNorBlank(@Nonnull String n, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return P.notNullNorBlank(n);
	}

	/** "Special" predicate: String <'%s'> must NOT be null or blank. Available in {@link P}, {@link Is}, {@link Be}, {@link P1}, {@link P2}.*/
	public static <MP1, MP2> @Nullable String notNullNorBlankEx(@Nonnull String n, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		return P.notNullNorBlankEx(n);
	}

	// </editor-fold>

	// <editor-fold desc="and/or/xor">

	// </editor-fold>

	// <editor-fold desc="== <= >= < >">

	// </editor-fold>

	// <editor-fold desc="arrays">

	// </editor-fold>

	// <editor-fold desc="collections">

	// </editor-fold>

	// <editor-fold desc="object derivatives">

	/** Predicate: Object <%s> of class <%s> must be instance of <%s>. Available in {@link P}, {@link Is}, {@link Be}, {@link P1}, {@link P2}.*/
	public static <MP1, MP2> boolean instanceOf(Object object, Class<?> clazz, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(clazz, "clazz");
		return P.instanceOf(object, clazz);
	}

	/** "Special" predicate: Object <%s> of class <%s> must be instance of <%s>. Available in {@link P}, {@link Is}, {@link Be}, {@link P1}, {@link P2}.*/
	public static <MP1, MP2> @Nullable String instanceOfEx(Object object, Class<?> clazz, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(clazz, "clazz");
		return P.instanceOfEx(object, clazz);
	}
	/** Predicate: Object <%s> of class <%s> must NOT be instance of <%s>. Available in {@link P}, {@link Is}, {@link Be}, {@link P1}, {@link P2}.*/
	public static <MP1, MP2> boolean notInstanceOf(Object object, Class<?> clazz, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(clazz, "clazz");
		return P.notInstanceOf(object, clazz);
	}

	/** "Special" predicate: Object <%s> of class <%s> must NOT be instance of <%s>. Available in {@link P}, {@link Is}, {@link Be}, {@link P1}, {@link P2}.*/
	public static <MP1, MP2> @Nullable String notInstanceOfEx(Object object, Class<?> clazz, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(clazz, "clazz");
		return P.notInstanceOfEx(object, clazz);
	}

	/** Predicate: Object <%s> of class <%s> must be exactly instance of <%s>. Available in {@link P}, {@link Is}, {@link Be}, {@link P1}, {@link P2}.*/
	public static <MP1, MP2> boolean exactlyInstanceOf(Object object, Class<?> clazz, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(clazz, "clazz");
		return P.exactlyInstanceOf(object, clazz);
	}

	/** "Special" predicate: Object <%s> of class <%s> must be exactly instance of <%s>. Available in {@link P}, {@link Is}, {@link Be}, {@link P1}, {@link P2}.*/
	public static <MP1, MP2> @Nullable String exactlyInstanceOfEx(Object object, Class<?> clazz, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(clazz, "clazz");
		return P.exactlyInstanceOfEx(object, clazz);
	}
	/** Predicate: Object <%s> of class <%s> must NOT be exactly instance of <%s>. Available in {@link P}, {@link Is}, {@link Be}, {@link P1}, {@link P2}.*/
	public static <MP1, MP2> boolean notExactlyInstanceOf(Object object, Class<?> clazz, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(clazz, "clazz");
		return P.notExactlyInstanceOf(object, clazz);
	}

	/** "Special" predicate: Object <%s> of class <%s> must NOT be exactly instance of <%s>. Available in {@link P}, {@link Is}, {@link Be}, {@link P1}, {@link P2}.*/
	public static <MP1, MP2> @Nullable String notExactlyInstanceOfEx(Object object, Class<?> clazz, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(clazz, "clazz");
		return P.notExactlyInstanceOfEx(object, clazz);
	}

	/** Predicate: Class <%s> of class <%s> must ---NOT-- be specialization of <%s>. Available in {@link P}, {@link Is}, {@link Be}, {@link P1}, {@link P2}.*/
	public static <MP1, MP2> boolean assignableFrom(Class<?> specialization, Class<?> clazz, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(clazz, "clazz");
		return P.assignableFrom(specialization, clazz);
	}

	/** "Special" predicate: Class <%s> of class <%s> must ---NOT-- be specialization of <%s>. Available in {@link P}, {@link Is}, {@link Be}, {@link P1}, {@link P2}.*/
	public static <MP1, MP2> @Nullable String assignableFromEx(Class<?> specialization, Class<?> clazz, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(clazz, "clazz");
		return P.assignableFromEx(specialization, clazz);
	}
	/** Predicate: Class <%s> of class <%s> must ---NOT-- be specialization of <%s>. Available in {@link P}, {@link Is}, {@link Be}, {@link P1}, {@link P2}.*/
	public static <MP1, MP2> boolean notAssignableFrom(Class<?> specialization, Class<?> clazz, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(clazz, "clazz");
		return P.notAssignableFrom(specialization, clazz);
	}

	/** "Special" predicate: Class <%s> of class <%s> must ---NOT-- be specialization of <%s>. Available in {@link P}, {@link Is}, {@link Be}, {@link P1}, {@link P2}.*/
	public static <MP1, MP2> @Nullable String notAssignableFromEx(Class<?> specialization, Class<?> clazz, MP1 msgParamOnly1, MP2 msgParamOnly2) {
		Null.nonNullArg(clazz, "clazz");
		return P.notAssignableFromEx(specialization, clazz);
	}

	// </editor-fold>

	// <editor-fold desc="Throwables">

	// </editor-fold>

}
